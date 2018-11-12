package com.ectrip.ticket.common.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.common.dao.idao.IStockDao;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;
import com.ectrip.ticket.model.stock.Stocktab;

/**
 * Created by cxh on 2015/11/26.
 */
@Service
public class StockService extends GenericService implements IStockService{
    
    private IStockDao stockDao;
    @Autowired
    public void setStockDao(IStockDao stockDao) {
    	setGenericDao(stockDao);
        this.stockDao = stockDao;
    }
    @Autowired
    private EcService ecService;
    @Autowired
    private SysService sysService;
    /**
     *
     * @param stockType 01:服务商库存 02:产品库存 03:价格库存
     * @param id    服务商ID、产品ID、价格ID
     * @param rzti  库存起始日期
     * @param ldti  库存截止日期
     * @param usid  用户库存时填写，用户ID
     * @return
     */
    public Long sumStockNumb(String stockType,String id,String rzti,String ldti,String usid){
        if(StringUtils.isBlank(usid)){
            return this.stockDao.sumStockById(stockType,id,rzti,ldti,null);
        }else{
            return this.stockDao.sumCustomStock(usid,stockType,id,rzti,ldti,null);
        }
    }

    public Long sumJdStock(String usid,String iscenicid){
        String sql = "from Stocktab where stocktype = '04' and id = '"+usid+"' and customStockType = '01' " +
                "and customStockId = '"+iscenicid+"' and startdate <='"+Tools.getDays()+"' and enddate >='"+Tools.getDays()+"' ";
        List list = this.stockDao.find(sql);
        if(list != null && !list.isEmpty()){
            Stocktab stocktab = (Stocktab) list.get(0);
            Long sale = sumStockNumb("04",iscenicid,Tools.getDays(),Tools.getDays(),usid);
            return stocktab.getStocknumb().longValue() - sale.longValue();
        }else{
            return null;
        }
    }

    /**
     * 检查用户库存
     * @param stockOrderInfos
     * @return
     */
    public String checkCustomStock(List<StockOrderInfo> stockOrderInfos){
        if(stockOrderInfos != null && !stockOrderInfos.isEmpty()){
            String date = stockOrderInfos.get(0).getStockDate();//首日浏览日期
            String orid = stockOrderInfos.get(0).getOrid();//订单号
            //根据用户分类数据
            Map<String,List<StockOrderInfo>> customStocks = new HashMap<String, List<StockOrderInfo>>();
            for(StockOrderInfo stockOrderInfo : stockOrderInfos){
                if(!customStocks.containsKey(stockOrderInfo.getUsid())){
                    List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
                    stocks.add(stockOrderInfo);
                    customStocks.put(stockOrderInfo.getUsid(),stocks);
                }else{
                    List<StockOrderInfo> stocks = customStocks.get(stockOrderInfo.getUsid());
                    stocks.add(stockOrderInfo);
                    customStocks.put(stockOrderInfo.getUsid(),stocks);
                }
            }
            //判断用户库存
            for(String key : customStocks.keySet()){
//                Custom custom = (Custom) genericDao.get(Custom.class,key);
                Custom custom = ecService.getCustomByUserId(key);
                boolean isCheck = true;//是否校验库存
                boolean isKz = true;//未设置库存是否可销售
                if(custom.getIbusinessid().longValue() == 3L){
//                    Sysparv5 sys = (Sysparv5) genericDao.get(Sysparv5.class,new Sysparv5Id("JDKC","0001"));
                    Sysparv5 sys = sysService.findOne("JDKC","0001");
                    if(sys != null){
                        if(sys.getIsb().intValue() == 0){//接待用户不配库存是否允许销售 1：可以  0：不可以
                            isKz = false;
                        }
                        if(sys.getIsa().intValue() == 1){//接待用户是否可超量销售  1：可以  0：不可以
                            isCheck = false;//可以超量销售，没必要验证库存信息了
                        }
                    }
                }
                List<StockOrderInfo> stocks = customStocks.get(key);
                if(stocks != null && !stocks.isEmpty()){
                    Map<String,Long> providerStock = new HashMap<String, Long>();//服务商库存
                    Map<String,Long> productStock = new HashMap<String, Long>();//产品库存
                    for(StockOrderInfo stockOrderInfo : stocks){
                        //分配产品库存
                        if(!productStock.containsKey(stockOrderInfo.getProductId().toString())){
                            productStock.put(stockOrderInfo.getProductId().toString(),stockOrderInfo.getNumb());
                        }else{
                            productStock.put(stockOrderInfo.getProductId().toString(),productStock.get(stockOrderInfo.getProductId().toString())+stockOrderInfo.getNumb());
                        }
                        //分配服务商库存
                        if(!providerStock.containsKey(stockOrderInfo.getProviderId().toString())){
                            providerStock.put(stockOrderInfo.getProviderId().toString(),stockOrderInfo.getNumb());
                        }else{
                            providerStock.put(stockOrderInfo.getProviderId().toString(),providerStock.get(stockOrderInfo.getProviderId().toString())+stockOrderInfo.getNumb());
                        }
                    }
                    //判断用户定向产品库存
                    for(String key2 : productStock.keySet()){
                        List<Stocktab> stocktabs = this.stockDao.selectCustomStockById(key,"02",key2,date);
                        if(stocktabs != null && !stocktabs.isEmpty()){
                            Stocktab stocktab = stocktabs.get(0);
                            Long saleNumb = this.stockDao.sumCustomStock(key,stocktab.getCustomStockType(),
                                    stocktab.getCustomStockId(),stocktab.getStartdate(),stocktab.getEnddate(),orid);
                            Long totalNumb = saleNumb + productStock.get(key2);
                            if(totalNumb.longValue() > stocktab.getStocknumb()){
                                if(isCheck){
                                    return "用户定向产品库存不足";
                                }
                            }
                        }
                    }
                    //判断用户定向服务商库存
                    for(String key2 : providerStock.keySet()){
                        List<Stocktab> stocktabs = this.stockDao.selectCustomStockById(key,"01",key2,date);
                        if(stocktabs != null && !stocktabs.isEmpty()){
                            Stocktab stocktab = stocktabs.get(0);
                            Long saleNumb = this.stockDao.sumCustomStock(key,stocktab.getCustomStockType(),
                                    stocktab.getCustomStockId(),stocktab.getStartdate(),stocktab.getEnddate(),orid);
                            Long totalNumb = saleNumb + providerStock.get(key2);
                            if(totalNumb.longValue() > stocktab.getStocknumb()){
                                if(isCheck){
                                    return "用户定向服务商库存不足";
                                }
                            }
                        }else{
                            if(!isKz){
                                return "未设置库存不可销售";
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * 检查库存信息
     * @param stockOrderInfos
     * @return
     */
    public String checkStock(List<StockOrderInfo> stockOrderInfos){
        if(stockOrderInfos != null && !stockOrderInfos.isEmpty()){
            String date = stockOrderInfos.get(0).getStockDate();
            String orid = stockOrderInfos.get(0).getOrid();
            Map<String,Long> providerStock = new HashMap<String, Long>();//服务商库存
            Map<String,Long> productStock = new HashMap<String, Long>();//产品库存
            Map<String,Long> priceStock = new HashMap<String, Long>();//价格库存
            for(StockOrderInfo stockOrderInfo : stockOrderInfos){
                //分配价格库存
                if(!priceStock.containsKey(stockOrderInfo.getPriceId().toString())){
                    priceStock.put(stockOrderInfo.getPriceId().toString(),stockOrderInfo.getNumb());
                }else{
                    priceStock.put(stockOrderInfo.getPriceId().toString(),priceStock.get(stockOrderInfo.getPriceId().toString())+stockOrderInfo.getNumb());
                }
                //分配产品库存
                if(!productStock.containsKey(stockOrderInfo.getProductId().toString())){
                    productStock.put(stockOrderInfo.getProductId().toString(),stockOrderInfo.getNumb());
                }else{
                    productStock.put(stockOrderInfo.getProductId().toString(),productStock.get(stockOrderInfo.getProductId().toString())+stockOrderInfo.getNumb());
                }
                //分配服务商库存
                if(!providerStock.containsKey(stockOrderInfo.getProviderId().toString())){
                    providerStock.put(stockOrderInfo.getProviderId().toString(),stockOrderInfo.getNumb());
                }else{
                    providerStock.put(stockOrderInfo.getProviderId().toString(),providerStock.get(stockOrderInfo.getProviderId().toString())+stockOrderInfo.getNumb());
                }
            }
            //判断价格库存
            for(String key : priceStock.keySet()){
                List<Stocktab> stocktabs = stockDao.selectStockById("03",key,date);
                if(stocktabs != null && !stocktabs.isEmpty()){
                    Stocktab stocktab = stocktabs.get(0);
                    Long saleNumb = stockDao.sumStockById(stocktab.getStocktype(),key,stocktab.getStartdate(),stocktab.getEnddate(),orid);
                    Long totalNumb = saleNumb + priceStock.get(key);
                    if(totalNumb.longValue() > stocktab.getStocknumb().longValue()){
                        return "价格库存不足";
                    }
                }
            }
            //判断产品库存
            for(String key : productStock.keySet()){
                List<Stocktab> stocktabs = stockDao.selectStockById("02",key,date);
                if(stocktabs != null && !stocktabs.isEmpty()){
                    Stocktab stocktab = stocktabs.get(0);
                    Long saleNumb = stockDao.sumStockById(stocktab.getStocktype(),key,stocktab.getStartdate(),stocktab.getEnddate(),orid);
                    Long totalNumb = saleNumb + productStock.get(key);
                    if(totalNumb.longValue() > stocktab.getStocknumb().longValue()){
                        return "产品库存不足";
                    }
                }
            }
            //判断服务商库存
            for(String key : providerStock.keySet()){
                List<Stocktab> stocktabs = stockDao.selectStockById("01",key,date);
                if(stocktabs != null && !stocktabs.isEmpty()){
                    Stocktab stocktab = stocktabs.get(0);
                    Long saleNumb = stockDao.sumStockById(stocktab.getStocktype(),key,stocktab.getStartdate(),stocktab.getEnddate(),orid);
                    Long totalNumb = saleNumb + providerStock.get(key);
                    if(totalNumb.longValue() > stocktab.getStocknumb().longValue()){
                        return "服务商库存不足";
                    }
                }
            }
        }
        return "";
    }

    /**
     *保存库存信息
     * @param stockOrderInfos 为最新的订单数据
     */
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public synchronized void  saveStockDetails(List<StockOrderInfo> stockOrderInfos,boolean isCheck){
        if(isCheck){
            String stockMessage = checkCustomStock(stockOrderInfos);
            if(!StringUtils.isBlank(stockMessage)){
                throw new RuntimeException(stockMessage);
            }
            stockMessage = checkStock(stockOrderInfos);
            if(!StringUtils.isBlank(stockMessage)){
                throw new RuntimeException(stockMessage);
            }
        }
        if(stockOrderInfos != null && !stockOrderInfos.isEmpty()){
            StringBuffer seqs = new StringBuffer();
            for(StockOrderInfo stockOrderInfo : stockOrderInfos){
                //判断库存明细是否存在
                Stockdetailtab stockdetailtab = stockDao.selectStockDetail(stockOrderInfo);
                if(stockdetailtab == null){//无库存明细信息
                	Long seq = stockDao.getMaxPk("seq", "Stockdetailtab");
                    stockdetailtab = new Stockdetailtab();
                    stockdetailtab.setSeq((seq+1));
                    stockdetailtab.setOrid(stockOrderInfo.getOrid());
                    stockdetailtab.setProviderid(stockOrderInfo.getProviderId());
                    stockdetailtab.setProductid(stockOrderInfo.getProductId());
                    stockdetailtab.setPriceid(stockOrderInfo.getPriceId());
                    stockdetailtab.setUsid(stockOrderInfo.getUsid());
                    stockdetailtab.setNumb(stockOrderInfo.getNumb());
                    stockdetailtab.setConsumedate(stockOrderInfo.getStockDate());
                    stockdetailtab.setDtmakedate(Tools.getDayTimes());
                    stockdetailtab.setTimeId(stockOrderInfo.getTimeId());
                    this.stockDao.save(stockdetailtab);
                    stockdetailtab = stockDao.selectStockDetail(stockOrderInfo);
                    if(stockdetailtab != null) {
                    	seqs.append(stockdetailtab.getSeq()+",");
                    }
                }else{//有库存明细信息
                    seqs.append(stockdetailtab.getSeq()+",");
                    if(stockdetailtab.getNumb().longValue() < 0){
                        stockdetailtab.setNumb(stockdetailtab.getNumb()+stockOrderInfo.getNumb());
                    }else{
                        stockdetailtab.setNumb(stockOrderInfo.getNumb());
                    }
                    this.stockDao.update(stockdetailtab);
                }
            }
            String noinclude = "";
            if(!StringUtils.isBlank(seqs.toString())){
                noinclude  = seqs.toString().substring(0, seqs.toString().length()-1);
            }
            this.stockDao.deleteStockDetails(stockOrderInfos.get(0).getOrid(),stockOrderInfos.get(0).getProviderId(),null,noinclude);
        }
    }

    public void deleteStockDetails(String orid,Long providerid,Long seq,String noInclude){
        this.stockDao.deleteStockDetails(orid, providerid, seq, noInclude);
    }

	@Override
	public Stockdetailtab selectStockDetail(StockOrderInfo stockOrderInfo) {
		return stockDao.selectStockDetail(stockOrderInfo);
	}
}
