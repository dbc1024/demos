package com.ectrip.ec.order.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.MD5Util;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.QrCode;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.balance.service.BalanceCenterService;
import com.ectrip.ec.common.CommonUtil;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.RaftComparepojo;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Credit;
import com.ectrip.ec.model.user.Creditdetail;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ec.model.usernumjf.Usernumjflist;
import com.ectrip.ec.model.usernumjf.UsernumjflistId;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.ec.order.dao.idao.IPayOrderDAO;
import com.ectrip.ec.order.dao.idao.ITOrderDAO;
import com.ectrip.ec.order.dao.idao.ITOrderListDAO;
import com.ectrip.ec.order.dao.idao.IYOrderDAO;
import com.ectrip.ec.order.dao.idao.IYOrderListDAO;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.ec.order.service.iservice.ITZOrderListService;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.GuaranteeRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.request.RefundbillsRequest;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.hqyt.model.response.JSONRefundBill;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Trip;

//import sun.java2d.SunGraphicsEnvironment.T1Filter;

/**
 * @author Dicky
 * @ClassName: OrderService
 * @Description:订票 业务 service
 * @date Oct 11, 2011 2:21:34 PM
 */
@Service
public class OrderService extends GenericService implements IOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    public IMOrderDAO morderdao;
    @Autowired
    public void setMorderdao(IMOrderDAO morderdao) {
		this.morderdao = morderdao;
		setGenericDao(morderdao);
	}
    @Autowired
    private BalanceCenterService balanceCenterService;
    
    public ITOrderDAO torderdao;
    @Autowired
    public void setTorderdao(ITOrderDAO torderdao) {
		this.torderdao = torderdao;
		setGenericDao(torderdao);
	}
	public ITOrderListDAO torderlistdao;
	 @Autowired
    public void setTorderlistdao(ITOrderListDAO torderlistdao) {
		this.torderlistdao = torderlistdao;
		setGenericDao(torderlistdao);
	}
	public IYOrderDAO yorderdao;
	 @Autowired
	public void setYorderdao(IYOrderDAO yorderdao) {
		this.yorderdao = yorderdao;
		setGenericDao(yorderdao);
	}
	 public IYOrderListDAO yorderlistdao;
	 @Autowired
	public void setYorderlistdao(IYOrderListDAO yorderlistdao) {
		this.yorderlistdao = yorderlistdao;
		setGenericDao(yorderlistdao);
	}
	 private IPayOrderDAO payorderDao;
	 @Autowired
	public void setPayorderDao(IPayOrderDAO payorderDao) {
		this.payorderDao = payorderDao;
		setGenericDao(payorderDao);
	}
	 @Autowired
	 private ITicketDAO ticketDao;

    @Autowired
    private SysparService sysparService;//系统服务API
   
    
    @Autowired
    private TicketService ticketService;//票务服务API
   
    @Autowired
    private ITZOrderListService tZOrderListService;
    
	public List getTZorderlist(Long orderlistid, String orid, Long iscenicid) {
        return tZOrderListService.getTZorderlist(orderlistid, orid, iscenicid);
    }
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist, List<TRealname> rlist)
            throws IllegalAccessException, InvocationTargetException {
        if (rlist == null || rlist.size() == 0) {
            return saveOrder(orid, morder, yorder, yorderlist, torder, torderlist);
        } else {
            return saveOrder(orid, morder, yorder, yorderlist, torder, torderlist) && saveRealname(rlist, orid);
        }
    }
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public boolean saveRealname(List<TRealname> list, String orid) {
        for (int i = 0; i < list.size(); i++) {
            try {
                TRealname tRealname = new TRealname();
                tRealname = (TRealname) list.get(i);
                Long seq = this.getSequenceId("realname_sequence");
                tRealname.setOrid(orid);
                tRealname.setSeq(seq);
                this.save(tRealname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: saveOrder
     * </p>
     * <p>
     * Description: 添加订单 同步以及事务控制 判断钱数是否正确之后在做操作
     * </p>
     *
     * @param morder
     * @param yorder
     * @param yorderlist
     * @param torder
     * @param torderlist
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see com.ectrip.order.service.iservice.IOrderService#saveOrder(com.ectrip.model.order.MOrder,
     * java.util.List, java.util.List, java.util.List, java.util.List)
     */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist) throws IllegalAccessException,
            InvocationTargetException {
        YZorderlist y_zordderlist = null;
        TOrder t_order = null;
        TOrderlist t_orderlist = null;
        List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
        GuaranteeRequest request = new GuaranteeRequest();
        List<ProductRequest> products = new ArrayList<ProductRequest>();
        //获取库存用户
        String stockUsid = morder.getUsid();
        Custom c = (Custom) morderdao.get(Custom.class, stockUsid);
        if (c.getIbusinessid() == 2 && c.getLgtp().equals("02") && c.getUstp().startsWith("0111")) {//操作员
            stockUsid = c.getSusid();//分社名称
        }
        /** 判断 金额 是否 一致 start **/
        // 计算销售订单总钱数 与 主订单的金额比较
        Double sum = 0.00;
        for (int i = 0; i < torder.size(); i++) {
            sum += torder.get(i).getZfmont();
        }
        // 判断总订单MOrder与分订单Torder的金额是否一致
        boolean ismoney = false;// 预定订单 与 销售 订单 金额是否一致 boolean 表示变量
        if (morder.getZfmont().doubleValue() == sum) {
            ismoney = true;
        }
        // 判断 销售订单 t_order 与 t_orderlist 销售订单明细 金额是否一致
        boolean isSum = false;// 销售的 订单与订单明细 金额是否一致 boolean 表示变量
        double mon = 0.00;
        double yhmont = 0.0;
        if (ismoney) {
            for (int i = 0; i < torder.size(); i++) {
                t_order = torder.get(i);
                mon = 0.0;
                for (int j = 0; j < torderlist.size(); j++) {
                    t_orderlist = torderlist.get(j);
                    if (t_orderlist.getId().getIscenicid().intValue() == t_order.getId().getIscenicid().intValue()) {
                        mon += t_orderlist.getAmnt();
                        yhmont += t_orderlist.getYhamnt();
                    }
                }
                if (t_order.getZfmont().doubleValue() == mon - yhmont) {
                    isSum = true;
                } else {
                    isSum = false;
                    break;
                }
            }
        }
        /** end **/
        if (isSum) {
            String[] dateArr = new String[torder.size()];
            for (int i = 0; i < torder.size(); i++) {
                TOrder t = torder.get(i);
                dateArr[i] = t.getDtstartdate();
            }
            dateArr = getStartAndEndDate(dateArr);
            // 插入预定订单
            morder.setOrid(orid);
            morder.setStdt(dateArr[dateArr.length - 1]);
//            morder.setOrdersource("ds");
            morderdao.saveMOrder(morder);
            request.setOrid(morder.getOrid());
            request.setTotalMoney(morder.getZfmont());
            // 插入销售订单
            for (int i = 0; i < torder.size(); i++) {
                torder.get(i).getId().setOrid(orid);
                torderdao.saveTOrder(torder.get(i));
            }
            // 插入销售订单
            for (int i = 0; i < yorder.size(); i++) {
                yorder.get(i).getId().setOrid(orid);
                torderdao.save(yorder.get(i));
            }

            for (int i = 0; i < yorderlist.size(); i++) {
                yorderlist.get(i).getId().setOrid(orid);
                torderdao.save(yorderlist.get(i));
            }
            String hqyatuEndDate = null;
            // 插入销售订单明细
            for (int i = 0; i < torderlist.size(); i++) {
                t_orderlist = torderlist.get(i);
                t_orderlist.getId().setOrid(orid);
                torderlistdao.saveTOrderList(t_orderlist);
                if (t_orderlist != null && t_orderlist.getZorderlist() != null && t_orderlist.getZorderlist().size() > 0) {
                    for (TZorderlist zorderlist : t_orderlist.getZorderlist()) {
                        zorderlist.getId().setOrid(orid);
                        torderdao.save(zorderlist);
                        y_zordderlist = new YZorderlist();
                        y_zordderlist.setId(new YZorderlistId(zorderlist.getId().getZorderlistid(), zorderlist.getId().getOrderlistid(), zorderlist.getId().getOrid(), zorderlist.getId()
                                .getIscenicid()));
                        y_zordderlist.setIcrowdkindpriceid(zorderlist.getIcrowdkindpriceid());
                        y_zordderlist.setIcrowdkindid(zorderlist.getIcrowdkindid());
                        y_zordderlist.setItickettypeid(zorderlist.getItickettypeid());
                        y_zordderlist.setIztickettypeid(zorderlist.getIztickettypeid());
                        y_zordderlist.setDtstartdate(zorderlist.getDtstartdate());
                        y_zordderlist.setDtenddate(zorderlist.getDtenddate());
                        //同步hqyatu的消费有效期
                        hqyatuEndDate = zorderlist.getDtenddate(); 
                        
                        y_zordderlist.setTripid(zorderlist.getTripid());
                        y_zordderlist.setIvenueid(zorderlist.getIvenueid());
                        y_zordderlist.setIvenueareaid(zorderlist.getIvenueareaid());
                        y_zordderlist.setIvenueseatsid(zorderlist.getIvenueseatsid());
                        y_zordderlist.setZpric(zorderlist.getZpric());
                        y_zordderlist.setZnumb(zorderlist.getZnumb());
                        y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
                        y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
                        y_zordderlist.setZamnt(zorderlist.getZamnt());
                        y_zordderlist.setJsprice(zorderlist.getJsprice());
                        y_zordderlist.setIsa(0l);
                        y_zordderlist.setIsb(0l);
                        y_zordderlist.setIsc(0l);
                        y_zordderlist.setIsd(0l);
                        y_zordderlist.setIse(0l);
                        y_zordderlist.setIsf(0l);
                        y_zordderlist.setIsg(0l);
                        y_zordderlist.setIsh(0l);
                        y_zordderlist.setIsi(0l);
                        y_zordderlist.setIsj(0l);
                        torderdao.save(y_zordderlist);
                    }
                }
                StockOrderInfo stock = new StockOrderInfo();
                stock.setOrid(t_orderlist.getId().getOrid());
                stock.setProviderId(t_orderlist.getId().getIscenicid());
                stock.setProductId(t_orderlist.getItickettypeid());
                stock.setPriceId(t_orderlist.getIcrowdkindpriceid());
                stock.setUsid(stockUsid);
                stock.setStockDate(t_orderlist.getDtstartdate());
                stock.setNumb(t_orderlist.getNumb());
                stock.setTimeId(t_orderlist.getTimeid());
                stocks.add(stock);

                ProductRequest productRequest = new ProductRequest();
                productRequest.setExternalId(t_orderlist.getItickettypeid());
                Edmtickettypetab ticket = (Edmtickettypetab) torderdao.get(Edmtickettypetab.class,t_orderlist.getItickettypeid());
                productRequest.setName(ticket.getSztickettypename());
                productRequest.setPrice(t_orderlist.getPric());
                productRequest.setNumb(t_orderlist.getNumb());
                products.add(productRequest);
            }

            //保存库存信息
            try {
//                ticketService.saveStockDetails(stocks, true);
            	ticketService.saveStockDetails(JSON.toJSONString(stocks), true);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            boolean hyqt = CommonUtil.isHqyt();
            if(hyqt){
                String hsql = "from ProviderCompany where usid in ('"+c.getUsid()+"','"+c.getSusid()+"') and type = '01' ";
                List list = torderdao.find(hsql);
                if(list != null && !list.isEmpty()){
                    ProviderCompany pc = (ProviderCompany) list.get(0);
                    request.setUsid(pc.getHqytId().toString());
                }else{
                    throw new RuntimeException("旅行社未开户不可预定");
                }
                hsql = "from ProviderCompany where iscenicid = "+torder.get(0).getId().getIscenicid()+" and type = '02' ";
                list = torderdao.find(hsql);
                if(list != null && !list.isEmpty()){
                    ProviderCompany pc = (ProviderCompany) list.get(0);
                    request.setIssuerId(pc.getHqytId().toString());
                }else{
                    throw new RuntimeException("服务商未开户不可预定");
                }
                HqytClient client = new HqytClient();
                request.setProducts(products);
                request.setConsumeTimeLimit(hqyatuEndDate);
                request.setTitle("B2B订单");
                JSONInvoice invoice = client.createGuarantee(request);
                if(invoice != null){
                    morder.setNoteh(invoice.getId().toString());
                    torderdao.update(morder);
                }else{
                    throw new RuntimeException("创建支付订单失败");
                }
            }

            // 0156 新增订单 0157 修改订单 0158 删除订单
            Orderlog log = new Orderlog();
            log.setLogid(getMaxPk("logid", "Orderlog") + 1);
            log.setOrid(orid);
            log.setStlg("0157");
            log.setBrief("预订成功");
            log.setNote("预订成功");
            log.setIemployeeid(null);
            log.setUsid(morder.getUsid());
            log.setLogtype(Long.parseLong("0"));
            log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
            torderdao.save(log);
            if (torder.get(0).getOrph() != null && !torder.get(0).getOrph().equals("")) {
            	sysparService.sendMessageNew(torder.get(0).getOrph(), "0001", morder.getOrid());// 订单保存发送短信
            }
            return true;
        } else {
            return false;
        }
    }
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public boolean saveShopCartOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist) throws IllegalAccessException,
    InvocationTargetException {
    	

        YZorderlist y_zordderlist = null;
        TOrder t_order = null;
        TOrderlist t_orderlist = null;
        List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
        GuaranteeRequest request = new GuaranteeRequest();
        List<ProductRequest> products = new ArrayList<ProductRequest>();
        //获取库存用户
        String stockUsid = morder.getUsid();
        Custom c = (Custom) morderdao.get(Custom.class, stockUsid);
        if (c.getIbusinessid() == 2 && c.getLgtp().equals("02") && c.getUstp().startsWith("0111")) {//操作员
            stockUsid = c.getSusid();//分社名称
        }
        /** 判断 金额 是否 一致 start **/
        // 计算销售订单总钱数 与 主订单的金额比较
        Double sum = 0.00;
        for (int i = 0; i < torder.size(); i++) {
            sum += torder.get(i).getZfmont();
        }
        // 判断总订单MOrder与分订单Torder的金额是否一致
        boolean ismoney = false;// 预定订单 与 销售 订单 金额是否一致 boolean 表示变量
        if (morder.getZfmont().doubleValue() == sum) {
            ismoney = true;
        }
        // 判断 销售订单 t_order 与 t_orderlist 销售订单明细 金额是否一致
        boolean isSum = false;// 销售的 订单与订单明细 金额是否一致 boolean 表示变量
        double mon = 0.00;
        double yhmont = 0.0;
        if (ismoney) {
            for (int i = 0; i < torder.size(); i++) {
                t_order = torder.get(i);
                mon = 0.0;
                for (int j = 0; j < torderlist.size(); j++) {
                    t_orderlist = torderlist.get(j);
                    if (t_orderlist.getId().getIscenicid().intValue() == t_order.getId().getIscenicid().intValue()) {
                        mon += t_orderlist.getAmnt();
                        yhmont += t_orderlist.getYhamnt();
                    }
                }
                if (t_order.getZfmont().doubleValue() == mon - yhmont) {
                    isSum = true;
                } else {
                    isSum = false;
                    break;
                }
            }
        }
        /** end **/
        if (isSum) {
            String[] dateArr = new String[torder.size()];
            for (int i = 0; i < torder.size(); i++) {
                TOrder t = torder.get(i);
                dateArr[i] = t.getDtstartdate();
            }
            dateArr = getStartAndEndDate(dateArr);
            // 插入预定订单
            morder.setOrid(orid);
            morder.setStdt(dateArr[dateArr.length - 1]);
//            morder.setOrdersource("ds");
            morderdao.saveMOrder(morder);
            request.setOrid(morder.getOrid());
            request.setTotalMoney(morder.getZfmont());
            // 插入销售订单
            for (int i = 0; i < torder.size(); i++) {
                torder.get(i).getId().setOrid(orid);
                torderdao.saveTOrder(torder.get(i));
            }
            // 插入销售订单
            for (int i = 0; i < yorder.size(); i++) {
                yorder.get(i).getId().setOrid(orid);
                torderdao.save(yorder.get(i));
            }

            for (int i = 0; i < yorderlist.size(); i++) {
                yorderlist.get(i).getId().setOrid(orid);
                torderdao.save(yorderlist.get(i));
            }
            String hqyatuEndDate = null;
            // 插入销售订单明细
            for (int i = 0; i < torderlist.size(); i++) {
                t_orderlist = torderlist.get(i);
                t_orderlist.getId().setOrid(orid);
                torderlistdao.saveTOrderList(t_orderlist);
                if (t_orderlist != null && t_orderlist.getZorderlist() != null && t_orderlist.getZorderlist().size() > 0) {
                    for (TZorderlist zorderlist : t_orderlist.getZorderlist()) {
                        zorderlist.getId().setOrid(orid);
                        torderdao.save(zorderlist);
                        y_zordderlist = new YZorderlist();
                        y_zordderlist.setId(new YZorderlistId(zorderlist.getId().getZorderlistid(), zorderlist.getId().getOrderlistid(), zorderlist.getId().getOrid(), zorderlist.getId()
                                .getIscenicid()));
                        y_zordderlist.setIcrowdkindpriceid(zorderlist.getIcrowdkindpriceid());
                        y_zordderlist.setIcrowdkindid(zorderlist.getIcrowdkindid());
                        y_zordderlist.setItickettypeid(zorderlist.getItickettypeid());
                        y_zordderlist.setIztickettypeid(zorderlist.getIztickettypeid());
                        y_zordderlist.setDtstartdate(zorderlist.getDtstartdate());
                        y_zordderlist.setDtenddate(zorderlist.getDtenddate());
                        //同步hqyatu的消费有效期
                        hqyatuEndDate = zorderlist.getDtenddate(); 
                        
                        y_zordderlist.setTripid(zorderlist.getTripid());
                        y_zordderlist.setIvenueid(zorderlist.getIvenueid());
                        y_zordderlist.setIvenueareaid(zorderlist.getIvenueareaid());
                        y_zordderlist.setIvenueseatsid(zorderlist.getIvenueseatsid());
                        y_zordderlist.setZpric(zorderlist.getZpric());
                        y_zordderlist.setZnumb(zorderlist.getZnumb());
                        y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
                        y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
                        y_zordderlist.setZamnt(zorderlist.getZamnt());
                        y_zordderlist.setJsprice(zorderlist.getJsprice());
                        y_zordderlist.setIsa(0l);
                        y_zordderlist.setIsb(0l);
                        y_zordderlist.setIsc(0l);
                        y_zordderlist.setIsd(0l);
                        y_zordderlist.setIse(0l);
                        y_zordderlist.setIsf(0l);
                        y_zordderlist.setIsg(0l);
                        y_zordderlist.setIsh(0l);
                        y_zordderlist.setIsi(0l);
                        y_zordderlist.setIsj(0l);
                        torderdao.save(y_zordderlist);
                    }
                }
                StockOrderInfo stock = new StockOrderInfo();
                stock.setOrid(t_orderlist.getId().getOrid());
                stock.setProviderId(t_orderlist.getId().getIscenicid());
                stock.setProductId(t_orderlist.getItickettypeid());
                stock.setPriceId(t_orderlist.getIcrowdkindpriceid());
                stock.setUsid(stockUsid);
                stock.setStockDate(t_orderlist.getDtstartdate());
                stock.setNumb(t_orderlist.getNumb());
                stock.setTimeId(t_orderlist.getTimeid());
                stocks.add(stock);

                ProductRequest productRequest = new ProductRequest();
                productRequest.setExternalId(t_orderlist.getItickettypeid());
                Edmtickettypetab ticket = (Edmtickettypetab) torderdao.get(Edmtickettypetab.class,t_orderlist.getItickettypeid());
                productRequest.setName(ticket.getSztickettypename());
                productRequest.setPrice(t_orderlist.getPric());
                productRequest.setNumb(t_orderlist.getNumb());
                products.add(productRequest);
            }

            //保存库存信息
            try {
//                ticketService.saveStockDetails(stocks, true);
            	ticketService.saveStockDetails(JSON.toJSONString(stocks), true);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            boolean hyqt = CommonUtil.isHqyt();
            if(hyqt){
                String hsql = "from ProviderCompany where usid in ('"+c.getUsid()+"','"+c.getSusid()+"') and type = '01' ";
                List list = torderdao.find(hsql);
                
                for(int i=0;i<torder.size();i++){
	                hsql = "from ProviderCompany where iscenicid = "+torder.get(i).getId().getIscenicid()+" and type = '02' ";
	                list = torderdao.find(hsql);
	                if(list != null && !list.isEmpty()){
	                    ProviderCompany pc = (ProviderCompany) list.get(0);
	                    request.setIssuerId(pc.getHqytId().toString());
	                }else{
	                    throw new RuntimeException("服务商未开户不可预定");
	                }
                }
                HqytClient client = new HqytClient();
                request.setProducts(products);
                request.setConsumeTimeLimit(hqyatuEndDate);
                request.setTitle("B2C订单");
                request.setUsid(c.getUsid());
                request.setUsername(c.getUsername());
                request.setPhone(c.getMobile());
                LegalPersonRequest receiver = new LegalPersonRequest();
                receiver.setId(Long.valueOf(c.getNote9()));
                request.setReceiver(receiver);
                
                JSONInvoice invoice = client.createGuaranteeSk(request);
                if(invoice != null){
                    morder.setNoteh(invoice.getId().toString());
                    torderdao.update(morder);
                }else{
                    throw new RuntimeException("创建支付订单失败");
                }
            }

            // 0156 新增订单 0157 修改订单 0158 删除订单
            Orderlog log = new Orderlog();
            log.setLogid(getMaxPk("logid", "Orderlog") + 1);
            log.setOrid(orid);
            log.setStlg("0157");
            log.setBrief("预订成功");
            log.setNote("预订成功");
            log.setIemployeeid(null);
            log.setUsid(morder.getUsid());
            log.setLogtype(Long.parseLong("0"));
            log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
            torderdao.save(log);
            if (torder.get(0).getOrph() != null && !torder.get(0).getOrph().equals("")) {
            	sysparService.sendMessageNew(torder.get(0).getOrph(), "0001", morder.getOrid());// 订单保存发送短信
            }
            return true;
        } else {
            return false;
        }
    
    }
    /**
     * (非 Javadoc)
     * <p>
     * Title: getOrderViewList
     * </p>
     * <p>
     * Description: 订单 分页
     * </p>
     *
     * @param usid
     * @param hql
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getOrderViewList(java.lang.String,
     * java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getOrderViewList(String usid, QueryOrder query, int pageSize, int startIndex, String url) {
        return morderdao.getOrderViewList(usid, query, pageSize, startIndex, url);
    }
    public PaginationSupport getOrderViewListByGroupId(String usid, QueryOrder query, int pageSize, int startIndex, String url,String groupId) {
    	return morderdao.getOrderViewListByGroupId(usid, query, pageSize, startIndex, url,groupId);
    }

    public PaginationSupport getOrderMoneyChangeViewlist(String usid, QueryOrder query, int pageSize, int startIndex, String url) {
        return morderdao.getOrderMoneyChangeViewlist(usid, query, pageSize, startIndex, url);
    }

    public PaginationSupport getOrderMoneyChangeViewlistByGroupid(String usid, QueryOrder query, int pageSize, int startIndex, String url,String groupId) {
        return morderdao.getOrderMoneyChangeViewlistByGroupid(usid, query, pageSize, startIndex, url,groupId);
    }
    /**
     * @param @param  orid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @Title: getMOrderList
     * @Description: 网上预定订单
     */
    public List getMOrderList(String orid) {
        return morderdao.getMOrderList(orid);
    }

    /**
     * @param @param  orid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @Title: getTOrderList
     * @Description: 网上订单服务商出票信息
     */
    public List getTOrderList(String orid) {
        return torderdao.getTOrderList(orid);
    }
    public List getTOrderLists(String orid){
    	return torderdao.getTOrderLists(orid);
    }

    
    //检票相关方法，已移至票务模块CheckService
//	public CYTDto getRealnemeList(String iscenicid,String idcard) {
//		CYTDto dto = new CYTDto();
//		try {
//			// TODO Auto-generated method stub
//			//
//			
//			//Long iscenicid = null;
//			List realnemeList = torderdao.getRealnemeList(iscenicid,idcard);
//			String orderid = null;
//			Object ps[] = new Object[] { idcard };
//			
//			if (realnemeList != null && realnemeList.size() > 0) {
//				orderid = ((Map)realnemeList.get(0)).get("orid").toString();
//				dto = this.getCYTDto(dto, orderid);
//				
//				//将身份证号作为游客证件号时，判断该票是否已检过，如果已检过，再将身份证号作为领票人证件号继续查询
//				List<Stssalesvouchertab> stssalesvouchertabs = this.find(" from Stssalesvouchertab s  where s.szsalesvoucherno='"+orderid+"' order by s.dtmakedate desc ");
//				if (stssalesvouchertabs !=null && stssalesvouchertabs.size()>0) {
//					List<Stssoldticketsubtab> stssoldticketsubtabs = this.find(" from Stssoldticketsubtab t where t.id.isalesvoucherid = '"+stssalesvouchertabs.get(0).getId().getIsalesvoucherid()+"'  order by t.dtmakedate desc ");
//					if (stssoldticketsubtabs.get(0).getIpasstimes() - stssoldticketsubtabs.get(0).getIpassedtimes() == 0) {
//						dto = new CYTDto();
//					}
//				}
//				
//				
//			}
//			
//			if(dto.morder == null || orderid == null) {
//				//将身份证号作为领票人证件号
//				if(idcard.length()==18){
//					List<TOrder> t = this.find(" from TOrder t where t.orhm = ?  order by t.id.orid desc", ps);
//					List<TOrderlist> tl = this.find(" from TOrderlist tlist where tlist.id.orid = "+t.get(0).getId().getOrid());
//					List<Edmcrowdkindpricetab> edmcrowdkindpricetabs = this.find(" from Edmcrowdkindpricetab where icrowdkindpriceid = "+ tl.get(0).getIcrowdkindpriceid());
//					//判断是否属于实名制票，如果是 ，则不可用领票人证件号检票
//					if(edmcrowdkindpricetabs.get(0).getIpeoplenumrange() == 1){
//						return dto;
//					}else {
//						List<TOrder> ts = this.find("from TOrder t where t.orhm=? order by t.id.orid desc ", ps);
//						if (ts != null && ts.size() > 0){
//							dto.torder = ts.get(0);
//							orderid = ts.get(0).getId().getOrid();
//						}
//					}
//				}else{
//					orderid = idcard;
//				}
//				dto = this.getCYTDto(dto, orderid);	
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			
//			return dto;
//		}
//		
//	}
//    private CYTDto  getCYTDto(CYTDto dto,String orderid) {
//    	dto.isGoOn = true;
//		
//		List<TOrderlist> tll = this.find(" from TOrderlist t where t.id.orid=?", new Object[] {orderid});
//		if (tll != null && tll.size() == 1)
//			dto.tOrderlist = tll.get(0);
//		
//		dto.sysparv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id("NPTK", "01"));
//		
//		if (dto.isGoOn && tll != null) {
//			List<MOrder> ml = this.find(
//					" from MOrder m where m.orid=? and (m.ddzt='02' or m.ddzt='11' or (m.ddzt='00' and m.zffs='09'))",
//					new Object[] {orderid});
//			if (ml != null && ml.size() > 0) {
//				dto.morder = ml.get(0);
//				List<TOrder> tl = this.find(" from TOrder t where t.id.orid=? ", new Object[] {orderid});
//				if (tl != null && tl.size() > 0)
//					dto.torder = tl.get(0);
//			}
//		}
//		if (dto.torder != null) {
//			List<Companyscenic> csl = this.find(" from Companyscenic c where c.id.iscenicid=? and c.cytonly='1'",
//					new Object[] {dto.torder.getId().getIscenicid() });
//			if (csl != null && csl.size() > 0) {
//				dto.Galcompanyinfotab = (Galcompanyinfotab) this.get(Galcompanyinfotab.class,
//						csl.get(0).getId().getIcompanyinfoid());
//			}
//		}
//		return dto;
//    }
    public List getTOrderList(String orid, String iscenicid) {
        return torderdao.getTOrderList(orid, iscenicid);
    }

    /**
     * @param @param  orid
     * @param @param  iscenicid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws
     * @Title: getTOrderListList
     * @Description: 网上订单服务商出票明细
     */
    public List getTOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
        return torderlistdao.getTOrderListList(orid, iscenicid);
    }

    /**
     * @param @param  orid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @Title: getYOrderList
     * @Description:网上预订服务商订单
     */
    public List getYOrderList(String orid) {
        return yorderdao.getYOrderList(orid);
    }

    /**
     * @param @param  orid
     * @param @param  iscenicid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @Title: getYOrderListList
     * @Description: 网上预定服务商订单明细List
     */
    public List getYOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
        return yorderlistdao.getYOrderListList(orid, iscenicid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getTOrderList
     * </p>
     * <p>
     * Description: 获取网上订单服务商出票明细
     * </p>
     *
     * @param orderlistid
     * @param iscenicid
     * @param orid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see com.ectrip.order.service.iservice.IOrderService#getTOrderList(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public TOrderlist getTOrderList(String torderlistid, String iscenicid, String orid) {
        return torderlistdao.getTOrderList(torderlistid, iscenicid, orid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: updateTOrderList
     * </p>
     * <p>
     * Description: 更新torderlist
     * </p>
     *
     * @param torderlist
     * @see com.ectrip.order.service.iservice.IOrderService#updateTOrderList(com.ectrip.model.order.TOrderlist)
     */
    public void updateTOrderList(TOrderlist torderlist, Orderlog log) {
        torderlistdao.updateTOrderList(torderlist);
        sysparService.insertOrderlog(log);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getTOrder
     * </p>
     * <p>
     * Description:获取单个TOrder信息
     * </p>
     *
     * @param orid
     * @param iscenicid
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getTOrder(java.lang.String,
     * java.lang.String)
     */
    public TOrder getTOrder(String orid, String iscenicid) throws Exception{
        return torderdao.getTOrder(orid, iscenicid);
    }
    
    public List<Map> getTOrder(String orid) {
        return torderdao.getTOrder(orid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: updateTOrder
     * </p>
     * <p>
     * Description:更新TOrder
     * </p>
     *
     * @param torder
     * @see com.ectrip.order.service.iservice.IOrderService#updateTOrder(com.ectrip.model.order.TOrder)
     */
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void updateTOrder(TOrder torder, Orderlog log) throws Exception{
        torderdao.updateTOrder(torder);
//        sysparService.insertOrderlog(log);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getMorder
     * </p>
     * <p>
     * Description: 获取订单
     * </p>
     *
     * @param orid
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getMorder(java.lang.String)
     */
    public MOrder getMorder(String orid) {
        return morderdao.getMOrder(orid);
    }

    /**
     * 查询退订订单详情
     * @param srid 原订单号
     * @return
     */
    public MOrder getRefundMOrder(String srid) {
        return morderdao.getRefundMOrder(srid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: updateMOrder
     * </p>
     * <p>
     * Description: 修改订单
     * </p>
     *
     * @param morder
     * @see com.ectrip.order.service.iservice.IOrderService#updateMOrder(com.ectrip.model.order.MOrder)
     */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void updateMOrder(MOrder morder, Orderlog log) {
        morderdao.updateMOrder(morder);
//        sysparService.insertOrderlog(log);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getLastYOrderListId
     * </p>
     * <p>
     * Description: 获取最大的yorderlistid
     * </p>
     *
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getLastYOrderListId()
     */
    public int getLastYOrderListId() {
        return yorderlistdao.getLastYOrderListId();
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getMaxNo
     * </p>
     * <p>
     * Description: 获取最大订单号
     * </p>
     *
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getMaxNo()
     */
    public String getMaxNo() {
        try {
            return morderdao.getMaxNo("000");
        } catch (Exception se) {
            return null;
        }
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getYOrderChildList
     * </p>
     * <p>
     * Description:获取 orid订单的子订单的增量、退订 网上预订服务商订单 信息
     * </p>
     *
     * @param orid
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getYOrderChildList(java.lang.String)
     */
    public List getYOrderChildList(String orid) {
        return yorderdao.getYOrderChildList(orid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getYOrderListChildList
     * </p>
     * <p>
     * Description:获取 orid订单的子订单的增量、退订 网上预订服务商订单 详细信息
     * </p>
     *
     * @param orid
     * @param iscenicid
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getYOrderListChildList(java.lang.String,
     * java.lang.String)
     */
    public List getYOrderListChildList(String orid, String iscenicid) {
        return yorderlistdao.getYOrderListChildList(orid, iscenicid);
    }

    /**
     * 获取订单明细
     *
     * @param orid
     * @return
     */
    public List getOrderOpearHistory(String orid) {
        return yorderlistdao.getOrderOpearHistory(orid);
    }

    /**
     * Describe:根据身份证号码获取导游信息
     *
     * @return return:Custom Date:2011-11-30
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @auth:yangguang
     * @param:orhm
     */
    public Custom queryDaoyouByOrhm(String orhm) throws IllegalAccessException, InvocationTargetException {
        return yorderlistdao.queryDaoyouByOrhm(orhm);
    }

    /**
     * * Describe:验证数量控制
     *
     * @param itickettypeid
     * @param tourdate
     * @param ivenueid
     * @param ivenueareaid
     * @param tripid
     * @param num
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author yangguang Date:2011-12-6
     * @see com.ectrip.order.service.iservice.IOrderService#volidateControl(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, int)
     */
    public boolean volidateControl(String itickettypeid, String tourdate, int num) throws IllegalAccessException, InvocationTargetException {
        Productcontrol daycontrol = payorderDao.getProductcontrol(itickettypeid, tourdate);
        if (daycontrol == null) {
            return true;
        } else {
            if (daycontrol.getSalablenumber() < new Long(num)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean volidateControl(String itickettypeid, String tourdate, String ivenueid, String ivenueareaid, String tripid, int num) throws IllegalAccessException, InvocationTargetException {
        Productcontrol tripcontrol = payorderDao.getProductcontrol(itickettypeid, tourdate, ivenueid, ivenueareaid, tripid);
        if (tripcontrol.getSalablenumber() < new Long(num)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * * Describe:导游新增修改订单
     *
     * @param newInfo
     * @param tagerInfo
     * @param usid
     * @return
     * @throws Exception
     * @author yangguang Date:2011-12-6
     * @see com.ectrip.order.service.iservice.IOrderService#updateOrderInfo(com.ectrip.model.order.TOrderlist,
     * com.ectrip.model.order.TOrderlist, java.lang.String)
     */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public int updateOrderInfo(TOrderlist newInfo, TOrderlist tagerInfo, String usid) throws Exception {
        Double mont_new = tagerInfo.getNnumb() * newInfo.getPric();// 改变数量的价格
        MOrder morder_z = null;
        Edmtickettypetab ticket = null;
        Edpcrowdkindtab kind = null;
        Useryfk yfk = null;
        morder_z = (MOrder) yorderlistdao.get(MOrder.class, tagerInfo.getId().getOrid());
        if (morder_z.getDdzt().equals("02")) {
            // 判断预付款是否足够支付新增订单
            if (tagerInfo.getZj().equals("z")) {
                Double sum_money = 0.00;
                if (morder_z != null) {
                    if (morder_z.getZfusid() != null && !morder_z.getZfusid().equals("")) {
                        sum_money = Double.valueOf(sysparService.getsumMoney(morder_z.getZfusid()) + "");// 预付款余额
                    }
                }
                if ((sum_money - mont_new) < 0) {
                    return -1;// 预付款不足
                } else {
                    yfk = new Useryfk();
                    int seqs = 0;
                    seqs = this.sysparService.getMaxSeq("Useryfk", "seq");
                    yfk.setUsid(morder_z.getZfusid());
                    yfk.setBdate(Tools.getTodayString());
                    yfk.setNote("消费预付款");
                    yfk.setSzmemo("导游:" + usid + " 修改订单,增加票数量");
                    yfk.setOrderid(morder_z.getOrid());
                    yfk.setPoint(mont_new);
                    yfk.setYfklb(-1);
                    yfk.setYfksc("03"); // 消费预付款
                    yfk.setCztp(0);
                    yfk.setSeq(seqs + 1);
                    balanceCenterService.useryfksave(yfk);
                }
            } else {
                if (morder_z.getDdzt().equals("02") || morder_z.getDdzt().equals("23")) {// 网上支付状态----》
                    // 退钱
                    // 计算预付款
                    yfk = new Useryfk();
                    int seqs = 0;
                    seqs = this.sysparService.getMaxSeq("Useryfk", "seq");
                    yfk.setUsid(morder_z.getZfusid());
                    yfk.setBdate(Tools.getTodayString());
                    yfk.setNote("退订转预付款");
                    yfk.setSzmemo("导游:" + usid + " 修改订单,减少票数量");
                    yfk.setOrderid(morder_z.getOrid());
                    yfk.setPoint(mont_new);
                    yfk.setYfklb(1);
                    yfk.setYfksc("02"); // 退订转预付款
                    yfk.setCztp(0);
                    yfk.setSeq(seqs + 1);
                    balanceCenterService.useryfksave(yfk);
                }
            }
        }
        if (tagerInfo.getZj().equals("z")) {
            // 更新服务商出票明细
            newInfo.setNumb(newInfo.getNumb() + tagerInfo.getNnumb());// 更改数量
            newInfo.setAmnt(newInfo.getAmnt() + mont_new);// 更新金额
        } else {
            // 更新服务商出票明细
            newInfo.setNumb(newInfo.getNumb() - tagerInfo.getNnumb());// 更改数量
            newInfo.setAmnt(newInfo.getAmnt() - mont_new);// 更新金额
        }
        // 0156 新增订单 0157 修改订单 0158 删除订单

        Orderlog log = new Orderlog();
        log.setLogid(getMaxPk("logid", "Orderlog") + 1);
        log.setOrid(newInfo.getId().getOrid());
        log.setStlg("0157");
        ticket = (Edmtickettypetab) torderdao.get(Edmtickettypetab.class, newInfo.getItickettypeid());
        kind = (Edpcrowdkindtab) torderdao.get(Edpcrowdkindtab.class, newInfo.getIcrowdkindid());
        if (tagerInfo.getZj().equals("z")) {
            log.setBrief(usid + " 修改订单:" + newInfo.getId().getOrid() + ",增加 [" + kind.getSzcrowdkindname() + ticket.getSztickettypename() + "] " + tagerInfo.getNnumb() + "张");
            log.setNote(usid + " 修改订单:" + newInfo.getId().getOrid() + ",增加 [" + kind.getSzcrowdkindname() + ticket.getSztickettypename() + "] " + tagerInfo.getNnumb() + "张");
        } else {
            log.setBrief(usid + " 修改订单:" + newInfo.getId().getOrid() + ",退订 [" + kind.getSzcrowdkindname() + ticket.getSztickettypename() + "] " + tagerInfo.getNnumb() + "张");
            log.setNote(usid + " 修改订单:" + newInfo.getId().getOrid() + ",退订 [" + kind.getSzcrowdkindname() + ticket.getSztickettypename() + "] " + tagerInfo.getNnumb() + "张");
        }

        log.setIemployeeid(null);
        log.setUsid(usid);
        log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
        log.setLogtype(Long.parseLong("0"));
        torderdao.update(newInfo);
        torderdao.save(log);
        // 更新服务商出票信息
        TOrder torder = (TOrder) torderdao.get(TOrder.class, new TOrderId(newInfo.getId().getOrid(), new Long(newInfo.getId().getIscenicid())));
        if (tagerInfo.getZj().equals("z")) {
            torder.setMont(torder.getMont() + mont_new);// 订单金额
            torder.setZfmont(torder.getZfmont() + mont_new);// 支付金额
            morder_z.setZfmont(morder_z.getZfmont() + mont_new);
            morder_z.setMont(morder_z.getMont() + mont_new);
        }
        if (tagerInfo.getZj().equals("j")) {
            torder.setMont(torder.getMont() - mont_new);// 订单金额
            torder.setZfmont(torder.getZfmont() - mont_new);// 支付金额
            morder_z.setZfmont(morder_z.getZfmont() - mont_new);
            morder_z.setMont(morder_z.getMont() - mont_new);
        }
        torderdao.update(torder);
        torderdao.update(morder_z);
        if (morder_z.getDdzt().equals("02")) {
            /****** morder ***/
            // 添加新订单
            String s = morderdao.getMaxNo("000");
            // 获取最大订单号+1
            Long new_no = Long.parseLong(s) + 1;
            String orid_new = new_no + ""; // 新订单id
            Long iscenicid_new = newInfo.getId().getIscenicid();// 服务商id
            MOrder morder = new MOrder();
            BeanUtils.copyProperties(morder, morder_z);
            morder.setOrid(orid_new);
            if (tagerInfo.getZj().equals("z")) {
                morder.setOrtp("03");
            } else {
                morder.setOrtp("02");
                morder.setDdzt("06");
            }
            morder.setMont(mont_new);
            morder.setYhamnt(0.00);
            morder.setZfmont(mont_new);
            morder.setSrid(newInfo.getId().getOrid());
            morder.setOrda(Tools.getDays());
            morder.setOrti(Tools.getNowTime());
            morder.setBankdata(Tools.getDays());
            morder.setBanktime(Tools.getNowTime());
            morderdao.save(morder);
            /****** morder end ***/
            /****** yorder start ***/
            YOrder yorder = new YOrder();
            yorder.setId(new YOrderId(orid_new, iscenicid_new));
            yorder.setMont(mont_new);
            yorder.setYhamnt(0.00);
            yorder.setZfmont(mont_new);
            yorder.setScenictype(torder.getScenictype());
            yorder.setUsid(torder.getUsid());
            yorder.setIbusinessid(torder.getIbusinessid());
            yorder.setSztravelbillno(torder.getSztravelbillno());
            yorder.setIregionalid(torder.getIregionalid());
            yorder.setTdlx(torder.getTdlx());
            if (tagerInfo.getZj().equals("z")) {
                yorder.setDdzt("02");
            } else {
                yorder.setDdzt("06");
            }
            yorder.setDtstartdate(torder.getDtstartdate());
            yorder.setDtenddate(torder.getDtenddate());
            yorder.setDyusid(torder.getDyusid());
            yorder.setOrnm(torder.getOrnm());
            yorder.setOrzj(torder.getOrzj());
            yorder.setOrhm(torder.getOrhm());
            yorder.setFaxno(torder.getFaxno());
            yorder.setOrph(torder.getOrph());
            yorder.setYoufei(torder.getYoufei());
            yorderdao.save(yorder);
            /****** yorder end ***/
            // 添加服务商订单明细
            /****** yorderlist start ***/
            int last_id = yorderlistdao.getLastYOrderListId();
            YOrderlist yorderList = new YOrderlist();
            yorderList.setId(new YOrderlistId(new Long(last_id + 1), orid_new, iscenicid_new));
            yorderList.setNumb(tagerInfo.getNnumb());
            yorderList.setYhnumb(new Long("0"));
            yorderList.setYhamnt(0.00);
            yorderList.setAmnt(mont_new);
            yorderList.setItickettypeid(newInfo.getItickettypeid());
            yorderList.setIcrowdkindid(newInfo.getIcrowdkindid());
            yorderList.setIcrowdkindpriceid(newInfo.getIcrowdkindpriceid());
            yorderList.setDtstartdate(newInfo.getDtstartdate());
            yorderList.setDtenddate(newInfo.getDtenddate());
            yorderList.setPric(newInfo.getPric());
            yorderList.setIoffersschemeid(newInfo.getIoffersschemeid());
            yorderlistdao.save(yorderList);
            /****** yorder end ***/
        }

        // 获取原订单
        List sonlist = tZOrderListService.getTZorderlist(newInfo.getId().getOrderlistid(), newInfo.getId().getOrid(), newInfo.getId().getIscenicid());
        TZorderlist tzorderlist = null;
        YZorderlist yzorderlist = null;
        Long maxZoid = null;
        // Prdtripvenuemanage pm=null;
        Productcontrol p = null;
        maxZoid = yorderlistdao.getMaxPk("id.zorderlistid", new String[]{"id.orderlistid", "id.orid", "id.iscenicid"}, new String[]{newInfo.getId().getOrderlistid().toString(),
                newInfo.getId().getOrid(), newInfo.getId().getIscenicid().toString()}, "YZorderlist");
        for (int i = 0; i < sonlist.size(); i++) {
            tzorderlist = (TZorderlist) sonlist.get(i);
            if (morder_z.getDdzt().equals("02")) {
                yzorderlist = new YZorderlist();
                yzorderlist.setId(new YZorderlistId(++maxZoid, newInfo.getId().getOrderlistid(), newInfo.getId().getOrid(), newInfo.getId().getIscenicid()));
                yzorderlist.setIcrowdkindpriceid(tzorderlist.getIcrowdkindpriceid());
                yzorderlist.setIcrowdkindid(tzorderlist.getIcrowdkindid());
                yzorderlist.setItickettypeid(tzorderlist.getItickettypeid());
                yzorderlist.setIztickettypeid(tzorderlist.getIztickettypeid());
                yzorderlist.setZpric(tzorderlist.getZpric());
                yzorderlist.setZnumb(tagerInfo.getNnumb());
                yzorderlist.setZamnt(yzorderlist.getZnumb() * yzorderlist.getZpric());
                yzorderlist.setZyhnumb(0l);
                yzorderlist.setZyhamnt(0.0);
                if (tzorderlist.getTripid() != null && !tzorderlist.getTripid().equals("") && tzorderlist.getTripid() != 0) {
                    yzorderlist.setDtstartdate(tzorderlist.getDtstartdate());
                    yzorderlist.setDtenddate(tzorderlist.getDtenddate());
                    yzorderlist.setTripid(tzorderlist.getTripid());
                    yzorderlist.setIvenueid(tzorderlist.getIvenueid());
                    yzorderlist.setIvenueareaid(tzorderlist.getIvenueareaid());
                    yzorderlist.setIvenueseatsid(new Long(0));
                    p = payorderDao.getProductcontrol(tzorderlist.getItickettypeid().toString(), tzorderlist.getDtstartdate().substring(0, 9), tzorderlist.getIvenueid().toString(), tzorderlist
                            .getIvenueareaid().toString(), tzorderlist.getTripid().toString());
                    if (p != null) {
                        if (tagerInfo.getZj().equals("z")) {
                            p.setSalablenumber(p.getSalablenumber() - tagerInfo.getNnumb());
                            p.setSoldnumber(p.getSoldnumber() + tagerInfo.getNnumb());
                        } else {
                            p.setSalablenumber(p.getSalablenumber() + tagerInfo.getNnumb());
                            p.setSoldnumber(p.getSoldnumber() - tagerInfo.getNnumb());
                        }
                    }
                } else {
                    yzorderlist.setDtstartdate(tzorderlist.getDtstartdate());
                    yzorderlist.setDtenddate(tzorderlist.getDtenddate());
                    yzorderlist.setTripid(new Long(0));
                    yzorderlist.setIvenueid(new Long(0));
                    yzorderlist.setIvenueareaid(new Long(0));
                    yzorderlist.setIvenueseatsid(new Long(0));
                    p = payorderDao.getProductcontrol(tzorderlist.getItickettypeid().toString(), tzorderlist.getDtstartdate().substring(0, 9));
                    if (p != null) {
                        if (tagerInfo.getZj().equals("z")) {
                            p.setSoldnumber(p.getSoldnumber() + tagerInfo.getNnumb());
                        } else {
                            p.setSoldnumber(p.getSoldnumber() - tagerInfo.getNnumb());
                        }
                    }
                }
                yorderlistdao.save(yzorderlist);
                if (p != null) {
                    payorderDao.update(p);
                }
            }
            // 子票减少
            if (tagerInfo.getZj().equals("z")) {
                tzorderlist.setZnumb(tzorderlist.getZnumb() + tagerInfo.getNnumb());
                tzorderlist.setZamnt(tzorderlist.getZamnt() - mont_new);
            } else {
                tzorderlist.setZnumb(tzorderlist.getZnumb() - tagerInfo.getNnumb());
                tzorderlist.setZamnt(tzorderlist.getZamnt() + mont_new);
            }
            // 更新
            yorderlistdao.update(tzorderlist);
        }
        return 1;
    }

    public List getOrderLogByOrid(String orid) {
        return morderdao.getOrderLogByOrid(orid);
    }

    /**
     * * Describe:
     *
     * @param newInfo
     * @param stdt
     * @param usid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author yangguang Date:2012-2-8
     * @see com.ectrip.order.service.iservice.IOrderService#calculateNewOrderInfo(java.util.List,
     * java.lang.String, java.lang.String)
     */
    private Map calculateNewOrderInfo(List newInfo, String stdt, String usid, long iscenicid) throws IllegalAccessException, InvocationTargetException {
        Custom custom = (Custom) ticketDao.get(Custom.class, usid);
        String ibusinessid = custom.getIbusinessid().toString();
        // 统计需要增加的竹筏量和金额
        Map map = new HashMap();
        List finallist = new ArrayList();// 合并同样的票
        List addraft = new ArrayList();// 按照同种票同一天进行分组 过滤没有竹筏日期的
        List daylist = new ArrayList();
        List newsonticketlist = new ArrayList();
        List sonticket = null;
        // 合并新增的集合中同样的票
        for (int i = 0; i < newInfo.size(); i++) {
            OrderPojo orderlist = (OrderPojo) newInfo.get(i);
            // 剔除无数量的产品
            if (orderlist.getNumb() == null || orderlist.getNumb().equals("") || orderlist.getNumb().equals("0")) {
                newInfo.remove(i);
                i = i - 1;
                continue;
            }
            // 合并产品相同且价格相同的产品 (有竹筏的需竹筏日期趟次相同的才能合并)
            if (finallist.size() < 1) {
                OrderPojo neworderlist = new OrderPojo();
                BeanUtils.copyProperties(neworderlist, orderlist);
                finallist.add(neworderlist);
            } else {
                for (int a = 0; a < finallist.size(); a++) {
                    OrderPojo compare = (OrderPojo) finallist.get(a);
                    if ((orderlist.getTourdate() == null || orderlist.getTourdate().equals("")) && (orderlist.getItickettypeid().equals(compare.getItickettypeid()))
                            && (orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid()))) {// 套票不包含竹筏票时只要日期为空且编号相等则叠加数量
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate()) && orderlist.getItickettypeid().equals(compare.getItickettypeid())
                            && orderlist.getProductcontrolid().equals(compare.getProductcontrolid()) && orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid())) {
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else {
                        if (a == finallist.size() - 1) {
                            OrderPojo neworderlist = new OrderPojo();
                            BeanUtils.copyProperties(neworderlist, orderlist);
                            finallist.add(neworderlist);
                            break;
                        }
                    }
                }
            }
            // 合并
            if (daylist.size() < 1) {
                if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                    OrderPojo orderlist1 = new OrderPojo();
                    BeanUtils.copyProperties(orderlist1, orderlist);
                    daylist.add(orderlist1);
                }
            } else {
                for (int j = 0; j < daylist.size(); j++) {
                    OrderPojo compare = (OrderPojo) daylist.get(j);
                    if (orderlist.getItickettypeid().equals(compare.getItickettypeid()) && orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate())) {// 过滤出含有游览日得票
                        // 一般都含有竹筏
                        // 然后根据日期合并，部分趟次
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (j == daylist.size() - 1) {
                        if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                            OrderPojo orderlist1 = new OrderPojo();
                            BeanUtils.copyProperties(orderlist1, orderlist);
                            daylist.add(orderlist1);
                            break;
                        }
                    }
                }
            }
        }
        double amont = 0.0;
        // 根据主产品取出所有的子产品
        for (int i = 0; i < finallist.size(); i++) {
            Productcontrol tripinfo = null;
            boolean israft = false;
            OrderPojo orderlist = (OrderPojo) finallist.get(i);

            //获取价格分组
            String groupno = ticketDao.searchJgfz(usid, iscenicid);
            List pricelist = ticketDao.getTicketPricelist(Long.parseLong(orderlist.getItickettypeid()), Long.parseLong(orderlist.getIcrowdkindid()), stdt, custom.getIbusinessid().toString(),
                    groupno);
            Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
            double price = ticketDao.getTicketPrice(orderlist.getItickettypeid().toString(), stdt, kindprice.getIcrowdkindpriceid().toString(), ibusinessid);
            Edpofferschemetab schema = ticketDao.getScheme(iscenicid, custom.getIbusinessid(), Long.parseLong(orderlist.getIcrowdkindid()), Long.parseLong(orderlist.getItickettypeid()), stdt);
            if (schema != null) {
                amont += price * (Integer.parseInt(orderlist.getNumb()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue());
            } else {
                amont += price * Integer.parseInt(orderlist.getNumb());
            }
            sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
            if (sonticket != null && sonticket.size() > 0) {// 如果有子产品
                for (int j = 0; j < sonticket.size(); j++) {// 遍历子产品 标记是否含有竹筏
                    // 把子产品合并到集合
                    Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(j);
                    Edmtickettypetab ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, pricepost.getItickettypeid());
                    if (newsonticketlist.size() < 1) {
                        RaftComparepojo raft = new RaftComparepojo();
                        raft.setIticketid(ticket.getItickettypeid());
                        if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                            raft.setTripid(tripinfo.getTripid());
                            raft.setTourdate(tripinfo.getStdata());
                            raft.setIvenueid(tripinfo.getIvenueid());
                            raft.setIvenueareaid(tripinfo.getIvenueareaid());
                        }
                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                        raft.setPrice(pricepost.getMactualsaleprice());
                        newsonticketlist.add(raft);
                    } else {
                        for (int x = 0; x < newsonticketlist.size(); x++) {
                            RaftComparepojo soncompare = (RaftComparepojo) newsonticketlist.get(x);
                            if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                                tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                                if (soncompare.getTourdate() != null && !soncompare.equals("") && soncompare.getTourdate().equals(tripinfo.getStdata())
                                        && soncompare.getTripid().intValue() == tripinfo.getTripid().intValue() && soncompare.getIvenueid().intValue() == tripinfo.getIvenueid().intValue()
                                        && soncompare.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()
                                        && (ticket.getItickettypeid().intValue() == soncompare.getIticketid().intValue())) {
                                    soncompare.setNumb(soncompare.getNumb() + pricepost.getNumb() * Long.parseLong(orderlist.getNumb()));
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
                                        raft.setIticketid(tripinfo.getItickettypeid());
                                        raft.setTripid(tripinfo.getTripid());
                                        raft.setTourdate(tripinfo.getStdata());
                                        raft.setIvenueid(tripinfo.getIvenueid());
                                        raft.setIvenueareaid(tripinfo.getIvenueareaid());
                                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                        raft.setPrice(pricepost.getMactualsaleprice());
                                        newsonticketlist.add(raft);
                                        break;
                                    }
                                }
                            } else {
                                if (soncompare.getIticketid().intValue() == ticket.getItickettypeid().intValue() && pricepost.getMactualsaleprice().doubleValue() == soncompare.getPrice()) {
                                    soncompare.setNumb(soncompare.getNumb() + Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
                                        raft.setIticketid(ticket.getItickettypeid());
                                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                        raft.setPrice(pricepost.getMactualsaleprice());
                                        newsonticketlist.add(raft);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (ticket.getBycategorytype().equals("0003")) {// 子产品为竹筏
                        israft = true;
                    }
                }
                if (israft) {
                    if (addraft.size() < 1) {
                        RaftComparepojo raft = new RaftComparepojo();
                        if (tripinfo != null && tripinfo.getProductcontrolid() != null && !tripinfo.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                            raft.setIticketid(tripinfo.getItickettypeid());
                            raft.setTripid(tripinfo.getTripid());
                            raft.setTourdate(tripinfo.getStdata());
                            raft.setIvenueid(tripinfo.getIvenueid());
                            raft.setIvenueareaid(tripinfo.getIvenueareaid());
                        }
                        raft.setNumb(Long.parseLong(orderlist.getNumb()));
                        addraft.add(raft);
                    } else {
                        tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                        // 要把同产品 同日起 同趟次的合并
                        for (int x = 0; x < addraft.size(); x++) {
                            RaftComparepojo raft = (RaftComparepojo) addraft.get(x);
                            if (raft.getTourdate().equals(tripinfo.getStdata()) && raft.getTripid().intValue() == tripinfo.getTripid().intValue()
                                    && raft.getIvenueid().intValue() == tripinfo.getIvenueid().intValue() && raft.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()) {
                                raft.setNumb(raft.getNumb() + Long.parseLong(orderlist.getNumb()));
                                break;
                            } else {
                                if (x == addraft.size() - 1) {
                                    RaftComparepojo raft1 = new RaftComparepojo();
                                    raft1.setTripid(tripinfo.getTripid());
                                    raft1.setTourdate(tripinfo.getStdata());
                                    raft1.setIvenueid(tripinfo.getIvenueid());
                                    raft1.setIvenueareaid(tripinfo.getIvenueareaid());
                                    raft1.setNumb(Long.parseLong(orderlist.getNumb()));
                                    raft1.setIticketid(tripinfo.getItickettypeid());
                                    addraft.add(raft1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        map.put("amont", amont);// 新增产品所需的金额
        map.put("raftlist", addraft);// 新增的按照日期、竹筏趟次统计出的需要新增的竹筏列表及数量
        map.put("daylist", daylist);// 新增的按照产品、日期统计出的产品列表
        map.put("addzproduct", newsonticketlist);// 把新增的产品全部拆成子票
        return map;
    }

    private Map calculateNewOrderInfoSanke(List newInfo, String stdt, String usid, long iscenicid) throws IllegalAccessException, InvocationTargetException {
        Custom custom = (Custom) ticketDao.get(Custom.class, usid);
        String ibusinessid = custom.getIbusinessid().toString();
        //获取价格分组
        String groupno = ticketDao.searchJgfz(usid, iscenicid);
        // 统计需要增加的竹筏量和金额
        Map map = new HashMap();
        List finallist = new ArrayList();// 合并同样的票
        List addraft = new ArrayList();// 按照同种票同一天进行分组 过滤没有竹筏日期的
        List daylist = new ArrayList();
        List newsonticketlist = new ArrayList();
        List sonticket = null;
        // 合并新增的集合中同样的票
        for (int i = 0; i < newInfo.size(); i++) {
            OrderPojo orderlist = (OrderPojo) newInfo.get(i);
            // 剔除无数量的产品
            if (orderlist.getNumb() == null || orderlist.getNumb().equals("") || orderlist.getNumb().equals("0")) {
                newInfo.remove(i);
                i = i - 1;
                continue;
            }
            // 合并产品相同且价格相同的产品 (有竹筏的需竹筏日期趟次相同的才能合并)
            if (finallist.size() < 1) {
                OrderPojo neworderlist = new OrderPojo();
                BeanUtils.copyProperties(neworderlist, orderlist);
                finallist.add(neworderlist);
            } else {
                for (int a = 0; a < finallist.size(); a++) {
                    OrderPojo compare = (OrderPojo) finallist.get(a);
                    if ((orderlist.getTourdate() == null || orderlist.getTourdate().equals("")) && (orderlist.getItickettypeid().equals(compare.getItickettypeid()))
                            && (orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid()))) {// 套票不包含竹筏票时只要日期为空且编号相等则叠加数量
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate()) && orderlist.getItickettypeid().equals(compare.getItickettypeid())
                            && orderlist.getProductcontrolid().equals(compare.getProductcontrolid()) && orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid())) {
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else {
                        if (a == finallist.size() - 1) {
                            OrderPojo neworderlist = new OrderPojo();
                            BeanUtils.copyProperties(neworderlist, orderlist);
                            finallist.add(neworderlist);
                            break;
                        }
                    }
                }
            }
            // 合并
            if (daylist.size() < 1) {
                if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                    OrderPojo orderlist1 = new OrderPojo();
                    BeanUtils.copyProperties(orderlist1, orderlist);
                    daylist.add(orderlist1);
                }
            } else {
                for (int j = 0; j < daylist.size(); j++) {
                    OrderPojo compare = (OrderPojo) daylist.get(j);
                    if (orderlist.getItickettypeid().equals(compare.getItickettypeid()) && orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate())) {// 过滤出含有游览日得票
                        // 一般都含有竹筏
                        // 然后根据日期合并，部分趟次
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (j == daylist.size() - 1) {
                        if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                            OrderPojo orderlist1 = new OrderPojo();
                            BeanUtils.copyProperties(orderlist1, orderlist);
                            daylist.add(orderlist1);
                            break;
                        }
                    }
                }
            }
        }
        double amont = 0.0;
        // 根据主产品取出所有的子产品
        for (int i = 0; i < finallist.size(); i++) {
            Productcontrol tripinfo = null;
            boolean israft = false;
            OrderPojo orderlist = (OrderPojo) finallist.get(i);
            List pricelist = ticketDao.getTicketPricelist(Long.parseLong(orderlist.getItickettypeid()), Long.parseLong(orderlist.getIcrowdkindid()), stdt, custom.getIbusinessid().toString(),
                    groupno);
            Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
            double price = ticketDao.getTicketPrice(orderlist.getItickettypeid().toString(), stdt, kindprice.getIcrowdkindpriceid().toString(), ibusinessid);
            amont += price * Integer.parseInt(orderlist.getNumb());
            sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
            if (sonticket != null && sonticket.size() > 0) {// 如果有子产品
                for (int j = 0; j < sonticket.size(); j++) {// 遍历子产品 标记是否含有竹筏
                    // 把子产品合并到集合
                    Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(j);
                    Edmtickettypetab ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, pricepost.getItickettypeid());
                    if (newsonticketlist.size() < 1) {
                        RaftComparepojo raft = new RaftComparepojo();
                        raft.setIticketid(ticket.getItickettypeid());
                        if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                            raft.setTripid(tripinfo.getTripid());
                            raft.setTourdate(tripinfo.getStdata());
                            raft.setIvenueid(tripinfo.getIvenueid());
                            raft.setIvenueareaid(tripinfo.getIvenueareaid());
                        }
                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                        raft.setPrice(pricepost.getMactualsaleprice());
                        newsonticketlist.add(raft);
                    } else {
                        for (int x = 0; x < newsonticketlist.size(); x++) {
                            RaftComparepojo soncompare = (RaftComparepojo) newsonticketlist.get(x);
                            if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                                tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                                if (soncompare.getTourdate() != null && !soncompare.equals("") && soncompare.getTourdate().equals(tripinfo.getStdata())
                                        && soncompare.getTripid().intValue() == tripinfo.getTripid().intValue() && soncompare.getIvenueid().intValue() == tripinfo.getIvenueid().intValue()
                                        && soncompare.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()
                                        && (ticket.getItickettypeid().intValue() == soncompare.getIticketid().intValue())) {
                                    soncompare.setNumb(soncompare.getNumb() + pricepost.getNumb() * Long.parseLong(orderlist.getNumb()));
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
                                        raft.setIticketid(tripinfo.getItickettypeid());
                                        raft.setTripid(tripinfo.getTripid());
                                        raft.setTourdate(tripinfo.getStdata());
                                        raft.setIvenueid(tripinfo.getIvenueid());
                                        raft.setIvenueareaid(tripinfo.getIvenueareaid());
                                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                        raft.setPrice(pricepost.getMactualsaleprice());
                                        newsonticketlist.add(raft);
                                        break;
                                    }
                                }
                            } else {
                                if (soncompare.getIticketid().intValue() == ticket.getItickettypeid().intValue() && pricepost.getMactualsaleprice().doubleValue() == soncompare.getPrice()) {
                                    soncompare.setNumb(soncompare.getNumb() + Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
                                        raft.setIticketid(ticket.getItickettypeid());
                                        raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                        raft.setPrice(pricepost.getMactualsaleprice());
                                        newsonticketlist.add(raft);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (ticket.getBycategorytype().equals("0003")) {// 子产品为竹筏
                        israft = true;
                    }
                }
                if (israft) {
                    if (addraft.size() < 1) {
                        RaftComparepojo raft = new RaftComparepojo();
                        if (tripinfo != null && tripinfo.getProductcontrolid() != null && !tripinfo.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                            raft.setIticketid(tripinfo.getItickettypeid());
                            raft.setTripid(tripinfo.getTripid());
                            raft.setTourdate(tripinfo.getStdata());
                            raft.setIvenueid(tripinfo.getIvenueid());
                            raft.setIvenueareaid(tripinfo.getIvenueareaid());
                        }
                        raft.setNumb(Long.parseLong(orderlist.getNumb()));
                        addraft.add(raft);
                    } else {
                        tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                        //  要把同产品 同日起 同趟次的合并
                        for (int x = 0; x < addraft.size(); x++) {
                            RaftComparepojo raft = (RaftComparepojo) addraft.get(x);
                            if (raft.getTourdate().equals(tripinfo.getStdata()) && raft.getTripid().intValue() == tripinfo.getTripid().intValue()
                                    && raft.getIvenueid().intValue() == tripinfo.getIvenueid().intValue() && raft.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()) {
                                raft.setNumb(raft.getNumb() + Long.parseLong(orderlist.getNumb()));
                                break;
                            } else {
                                if (x == addraft.size() - 1) {
                                    RaftComparepojo raft1 = new RaftComparepojo();
                                    raft1.setTripid(tripinfo.getTripid());
                                    raft1.setTourdate(tripinfo.getStdata());
                                    raft1.setIvenueid(tripinfo.getIvenueid());
                                    raft1.setIvenueareaid(tripinfo.getIvenueareaid());
                                    raft1.setNumb(Long.parseLong(orderlist.getNumb()));
                                    raft1.setIticketid(tripinfo.getItickettypeid());
                                    addraft.add(raft1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        map.put("amont", amont);// 新增产品所需的金额
        map.put("raftlist", addraft);// 新增的按照日期、竹筏趟次统计出的需要新增的竹筏列表及数量
        map.put("daylist", daylist);// 新增的按照产品、日期统计出的产品列表
        map.put("addzproduct", newsonticketlist);// 把新增的产品全部拆成子票
        return map;
    }

    private Map calculateOldOrder(String orid, String iscenicid, List orderInfo) throws IllegalAccessException, InvocationTargetException {
        // 获取原始订单
        List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
        List chalist = new ArrayList();
        for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
            TOrderlist oldtorderlist = new TOrderlist();
            BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
            for (int j = 0; j < orderInfo.size(); j++) {// 修改后的订单
                TOrderlist newtorderlist = (TOrderlist) orderInfo.get(j);
                if (newtorderlist.getOrderlistid().equals(oldtorderlist.getOrderlistid()) && newtorderlist.getIscenicid().equals(oldtorderlist.getIscenicid())) {
                    if (newtorderlist.getNumb().intValue() != oldtorderlist.getNumb().intValue()) {
                        TOrderlist ctorderlist = new TOrderlist();
                        BeanUtils.copyProperties(ctorderlist, oldtorderlist);
                        // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                        ctorderlist.setNumb(newtorderlist.getNumb() - oldtorderlist.getNumb());
                        ctorderlist.setId(new TOrderlistId(Long.parseLong(oldtorderlist.getOrderlistid()), oldtorderlist.getOrid(), Long.parseLong(iscenicid)));
                        ctorderlist.setAmnt(oldtorderlist.getPric() * (newtorderlist.getNumb() - oldtorderlist.getNumb()));
                        ctorderlist.setYhnumb(oldtorderlist.getYhnumb());
                        chalist.add(ctorderlist);
                    }
                }
            }
        }
        // 按照日期趟次码头分组竹筏
        List raftlist = new ArrayList();
        List daylist = new ArrayList();// 按照竹筏日期分组
        List allzlist = new ArrayList();
        double amount = 0.0;
        MOrder morder = (MOrder) yorderlistdao.get(MOrder.class, orid);
        Custom c = (Custom) yorderlistdao.get(Custom.class, morder.getZfusid());
        String groupno = ticketDao.searchJgfz(c.getUsid(), Long.parseLong(iscenicid));
        // 遍历修改后和修改前订单对比出来的产品列表
        for (int i = 0; i < chalist.size(); i++) {
            TOrderlist ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
            TOrderlist oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid)));
            //  获取到优惠策略 计算差价
            Edmcrowdkindpricetab price = ticketDao.getProductPrice(ctorderlist.getItickettypeid().toString(), c.getIbusinessid().toString(), oldtorderlist.getDtstartdate().substring(0, 10),
                    ctorderlist.getIcrowdkindid().toString(), groupno);
            ctorderlist.setIcrowdkindpriceid(price.getIcrowdkindpriceid());
            Edpofferschemetab schema = ticketDao.getScheme(Long.parseLong(iscenicid), c.getIbusinessid(), oldtorderlist.getIcrowdkindid(), oldtorderlist.getItickettypeid(), oldtorderlist
                    .getDtstartdate().substring(0, 10));
            int cnum = 0;// 重新计算应优惠数量
            if (schema != null) {
                // 计算出修改之后总数量可有优惠的数量
                cnum = (oldtorderlist.getNumb().intValue() + ctorderlist.getNumb().intValue()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                // 计算次本次修改之后需要收费产品的数量
                cnum = ctorderlist.getNumb().intValue() - (cnum - oldtorderlist.getYhnumb().intValue());
            } else {
                cnum = ctorderlist.getNumb().intValue();
            }
            amount += oldtorderlist.getPric() * cnum;// 叠加价格
            // 需找子产品
            List tzlist = tZOrderListService.getTZorderlist(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid));
            // 拿出原订单的对应产品
            for (int j = 0; j < tzlist.size(); j++) {// 循环有差异的子票列表
                TZorderlist tzorderlist = (TZorderlist) tzlist.get(j);
                // 子产品对象
                Edmtickettypetab checkticket = (Edmtickettypetab) yorderlistdao.get(Edmtickettypetab.class, tzorderlist.getIztickettypeid());
                RaftComparepojo newraft = new RaftComparepojo();
                newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                newraft.setTripid(tzorderlist.getTripid());
                newraft.setIvenueid(tzorderlist.getIvenueid());
                newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                newraft.setParentticketid(oldtorderlist.getItickettypeid());
                newraft.setEnddate(tzorderlist.getDtenddate());
                newraft.setIcrowdid(tzorderlist.getIcrowdkindid());
                // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                newraft.setNumb(ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                newraft.setIticketid(tzorderlist.getIztickettypeid());
                newraft.setPrice(tzorderlist.getZpric());
                newraft.setYhnum(tzorderlist.getZyhnumb().intValue());// 设置优惠
                // 挑选出针对原订单新增和退订的子产品列表
                if (allzlist.size() < 1) {
                    RaftComparepojo newraft1 = new RaftComparepojo();
                    BeanUtils.copyProperties(newraft1, newraft);
                    allzlist.add(newraft1);
                } else {
                    for (int x = 0; x < allzlist.size(); x++) {
                        RaftComparepojo checkraft = (RaftComparepojo) allzlist.get(x);
                        if (checkticket.getBycategorytype().equals("0003")) {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft.getTripid())
                                    && checkraft.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        } else {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == tzorderlist.getIztickettypeid().intValue()
                                    && checkraft.getPrice() == tzorderlist.getZpric().doubleValue()) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        }

                    }
                }
                // 子票是竹筏再检测 不是竹筏直接跳过
                if (checkticket.getBycategorytype().equals("0003")) {
                    // 按照竹筏日期及趟次挑选票及数量
                    if (raftlist.size() < 1) {
                        // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                        RaftComparepojo newraft1 = new RaftComparepojo();
                        BeanUtils.copyProperties(newraft1, newraft);
                        raftlist.add(newraft1);
                    } else {
                        for (int x = 0; x < raftlist.size(); x++) {// 竹筏趟次根据日期趟次码头分组后的集合
                            RaftComparepojo checkraft1 = (RaftComparepojo) raftlist.get(x);
                            // 如果集合中已存在则只叠加数量 这里忽略子产品对应的主产品只计算竹筏的数量
                            if (checkraft1.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft1.getTripid())
                                    && checkraft1.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft1.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {
                                checkraft1.setNumb(checkraft1.getNumb() + tzorderlist.getZnumb() / oldtorderlist.getNumb() * ctorderlist.getNumb());
                                break;// 叠加完之后跳出循环
                            } else {
                                if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    // 跳出循环
                                    // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    raftlist.add(newraft);
                                    break;
                                }
                            }
                        }
                    }
                    // 只统计日控制的话
                    // 只需要判断是否含有竹筏就行所以也就只需要循环一次
                    // 直接跳出.
                    // 根据日期挑选出来的需要新增和退订的竹筏列表
                    if (daylist.size() < 1) {
                        newraft = new RaftComparepojo();
                        newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                        newraft.setTripid(tzorderlist.getTripid());
                        newraft.setIvenueid(tzorderlist.getIvenueid());
                        newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                        newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                        newraft.setParentticketid(oldtorderlist.getItickettypeid());
                        // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                        newraft.setNumb(ctorderlist.getNumb());
                        newraft.setIticketid(oldtorderlist.getItickettypeid());
                        daylist.add(newraft);
                    } else {
                        for (int x = 0; x < daylist.size(); x++) {
                            RaftComparepojo checkraft = (RaftComparepojo) daylist.get(x);
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == oldtorderlist.getItickettypeid().intValue()
                                    && checkraft.getTripid().intValue() == tzorderlist.getTripid().intValue()) {
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;// 叠加完之后跳出循环
                            } else {
                                if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    // 跳出循环
                                    newraft = new RaftComparepojo();
                                    newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                                    newraft.setTripid(tzorderlist.getTripid());
                                    newraft.setIvenueid(tzorderlist.getIvenueid());
                                    newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                                    newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                                    newraft.setParentticketid(oldtorderlist.getItickettypeid());
                                    // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                                    newraft.setNumb(ctorderlist.getNumb());
                                    newraft.setIticketid(oldtorderlist.getItickettypeid());
                                    daylist.add(newraft);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        Map map = new HashMap();
        map.put("chaticket", chalist);// 订单修改前后对比出的产品差异列表
        map.put("amount", amount);// 修改订单之后的差异金额，正则需要用户支付，负则退还用户
        map.put("charraft", raftlist);// 订单修改前后统计出的竹筏趟次 新增与退订的列表
        map.put("chaday", daylist);// 订单修改前后统计的产品按照日期产品分组的列表
        map.put("allzlist", allzlist);// 所有要增减的子票列表
        return map;
    }

    private Map calculateOldOrderSanke(String orid, String iscenicid, List orderInfo) throws IllegalAccessException, InvocationTargetException {
        // 获取原始订单
        List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
        List chalist = new ArrayList();
        for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
            TOrderlist oldtorderlist = new TOrderlist();
            BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
            for (int j = 0; j < orderInfo.size(); j++) {// 修改后的订单
                TOrderlist newtorderlist = (TOrderlist) orderInfo.get(j);
                if (newtorderlist.getOrderlistid().equals(oldtorderlist.getOrderlistid()) && newtorderlist.getIscenicid().equals(oldtorderlist.getIscenicid())) {
                    if (newtorderlist.getNumb().intValue() != oldtorderlist.getNumb().intValue()) {
                        TOrderlist ctorderlist = new TOrderlist();
                        BeanUtils.copyProperties(ctorderlist, oldtorderlist);
                        // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                        ctorderlist.setNumb(newtorderlist.getNumb() - oldtorderlist.getNumb());
                        ctorderlist.setId(new TOrderlistId(Long.parseLong(oldtorderlist.getOrderlistid()), oldtorderlist.getOrid(), Long.parseLong(iscenicid)));
                        ctorderlist.setAmnt(oldtorderlist.getPric() * (newtorderlist.getNumb() - oldtorderlist.getNumb()));
                        ctorderlist.setYhnumb(oldtorderlist.getYhnumb());
                        chalist.add(ctorderlist);
                    }
                }
            }
        }
        // 按照日期趟次码头分组竹筏
        List raftlist = new ArrayList();
        List daylist = new ArrayList();// 按照竹筏日期分组
        List allzlist = new ArrayList();
        double amount = 0.0;
        MOrder morder = (MOrder) yorderlistdao.get(MOrder.class, orid);
        Custom c = (Custom) yorderlistdao.get(Custom.class, morder.getZfusid());

        String groupno = ticketDao.searchJgfz(c.getUsid(), Long.parseLong(iscenicid));
        // 遍历修改后和修改前订单对比出来的产品列表
        for (int i = 0; i < chalist.size(); i++) {
            TOrderlist ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
            TOrderlist oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid)));
            //  获取到优惠策略 计算差价
            Edmcrowdkindpricetab price = ticketDao.getProductPrice(ctorderlist.getItickettypeid().toString(), c.getIbusinessid().toString(), oldtorderlist.getDtstartdate().substring(0, 10),
                    ctorderlist.getIcrowdkindid().toString(), groupno);
            ctorderlist.setIcrowdkindpriceid(price.getIcrowdkindpriceid());
            int cnum = 0;// 重新计算应优惠数量
            cnum = ctorderlist.getNumb().intValue();
            amount += oldtorderlist.getPric() * cnum;// 叠加价格
            // 需找子产品
            List tzlist = tZOrderListService.getTZorderlist(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid));
            // 拿出原订单的对应产品
            for (int j = 0; j < tzlist.size(); j++) {// 循环有差异的子票列表
                TZorderlist tzorderlist = (TZorderlist) tzlist.get(j);
                // 子产品对象
                Edmtickettypetab checkticket = (Edmtickettypetab) yorderlistdao.get(Edmtickettypetab.class, tzorderlist.getIztickettypeid());
                RaftComparepojo newraft = new RaftComparepojo();
                newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                newraft.setTripid(tzorderlist.getTripid());
                newraft.setIvenueid(tzorderlist.getIvenueid());
                newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                newraft.setParentticketid(oldtorderlist.getItickettypeid());
                newraft.setEnddate(tzorderlist.getDtenddate());
                newraft.setIcrowdid(tzorderlist.getIcrowdkindid());
                // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                newraft.setNumb(ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                newraft.setIticketid(tzorderlist.getIztickettypeid());
                newraft.setPrice(tzorderlist.getZpric());
                newraft.setYhnum(tzorderlist.getZyhnumb().intValue());// 设置优惠
                // 挑选出针对原订单新增和退订的子产品列表
                if (allzlist.size() < 1) {
                    RaftComparepojo newraft1 = new RaftComparepojo();
                    BeanUtils.copyProperties(newraft1, newraft);
                    allzlist.add(newraft1);
                } else {
                    for (int x = 0; x < allzlist.size(); x++) {
                        RaftComparepojo checkraft = (RaftComparepojo) allzlist.get(x);
                        if (checkticket.getBycategorytype().equals("0003")) {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft.getTripid())
                                    && checkraft.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        } else {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == tzorderlist.getIztickettypeid().intValue()
                                    && checkraft.getPrice() == tzorderlist.getZpric().doubleValue()) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        }

                    }
                }
                // 子票是竹筏再检测 不是竹筏直接跳过
                if (checkticket.getBycategorytype().equals("0003")) {
                    // 按照竹筏日期及趟次挑选票及数量
                    if (raftlist.size() < 1) {
                        // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                        RaftComparepojo newraft1 = new RaftComparepojo();
                        BeanUtils.copyProperties(newraft1, newraft);
                        raftlist.add(newraft1);
                    } else {
                        for (int x = 0; x < raftlist.size(); x++) {// 竹筏趟次根据日期趟次码头分组后的集合
                            RaftComparepojo checkraft1 = (RaftComparepojo) raftlist.get(x);
                            // 如果集合中已存在则只叠加数量 这里忽略子产品对应的主产品只计算竹筏的数量
                            if (checkraft1.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft1.getTripid())
                                    && checkraft1.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft1.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {
                                checkraft1.setNumb(checkraft1.getNumb() + tzorderlist.getZnumb() / oldtorderlist.getNumb() * ctorderlist.getNumb());
                                break;// 叠加完之后跳出循环
                            } else {
                                if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    // 跳出循环
                                    // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    raftlist.add(newraft);
                                    break;
                                }
                            }
                        }
                    }
                    // 只统计日控制的话
                    // 只需要判断是否含有竹筏就行所以也就只需要循环一次
                    // 直接跳出.
                    // 根据日期挑选出来的需要新增和退订的竹筏列表
                    if (daylist.size() < 1) {
                        newraft = new RaftComparepojo();
                        newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                        newraft.setTripid(tzorderlist.getTripid());
                        newraft.setIvenueid(tzorderlist.getIvenueid());
                        newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                        newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                        newraft.setParentticketid(oldtorderlist.getItickettypeid());
                        // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                        newraft.setNumb(ctorderlist.getNumb());
                        newraft.setIticketid(oldtorderlist.getItickettypeid());
                        daylist.add(newraft);
                    } else {
                        for (int x = 0; x < daylist.size(); x++) {
                            RaftComparepojo checkraft = (RaftComparepojo) daylist.get(x);
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == oldtorderlist.getItickettypeid().intValue()
                                    && checkraft.getTripid().intValue() == tzorderlist.getTripid().intValue()) {
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;// 叠加完之后跳出循环
                            } else {
                                if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    // 跳出循环
                                    newraft = new RaftComparepojo();
                                    newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                                    newraft.setTripid(tzorderlist.getTripid());
                                    newraft.setIvenueid(tzorderlist.getIvenueid());
                                    newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                                    newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                                    newraft.setParentticketid(oldtorderlist.getItickettypeid());
                                    // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                                    newraft.setNumb(ctorderlist.getNumb());
                                    newraft.setIticketid(oldtorderlist.getItickettypeid());
                                    daylist.add(newraft);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        Map map = new HashMap();
        map.put("chaticket", chalist);// 订单修改前后对比出的产品差异列表
        map.put("amount", amount);// 修改订单之后的差异金额，正则需要用户支付，负则退还用户
        map.put("charraft", raftlist);// 订单修改前后统计出的竹筏趟次 新增与退订的列表
        map.put("chaday", daylist);// 订单修改前后统计的产品按照日期产品分组的列表
        map.put("allzlist", allzlist);// 所有要增减的子票列表
        return map;
    }

    public Map validateInfoIntegrity(List<OrderPojo> newticketinfo, String ibusiness, String iscenicid, String orid, String groupno) {
        TOrder torder = (TOrder) ticketDao.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
        Map errmap = null;
        if (newticketinfo != null && newticketinfo.size() > 0) {
            Map map = new HashMap();
            for (OrderPojo ticket : newticketinfo) {
                if (ticket.getIcrowdkindid() == null || ticket.getIcrowdkindid().equals("")) {
                    errmap = new HashMap();
                    errmap.put("errtp", "9");
                    return errmap;
                } else {
                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(ticket.getItickettypeid()), Long.parseLong(ticket.getIcrowdkindid()), torder.getDtstartdate(), ibusiness, groupno);
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    ticket.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid().intValue());
                }
                List list = ticketDao.getSonticketlist(ticket.getIcrowdkindpriceid().longValue());
                if (list != null && list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        Edmticketcomposepricetab sprice = (Edmticketcomposepricetab) list.get(j);
                        Edmtickettypetab sticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, sprice.getItickettypeid());
                        if (sticket.getBycategorytype().equals("0003") && ticket.getProductcontrolid() != null && ticket.getNumb() != null && !ticket.getNumb().equals("")
                                && Integer.parseInt(ticket.getNumb()) > 0) {
                            if (ticket.getProductcontrolid() == null || ticket.getProductcontrolid().equals("")) {
                                errmap = new HashMap();
                                errmap.put("errtp", "9");
                                return errmap;
                            }
                        }
                    }
                }
            }
        }
        return errmap;
    }

    public Map validateOrderInfo(String orid, List orderInfo, List newInfo, String usid, String stdt, String ibusinessid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
        List newOrderInfo = new ArrayList();
        if (newInfo != null && newInfo.size() > 0) {
            newOrderInfo.addAll(newInfo);
        }
        List oldOrderInfo = new ArrayList();
        if (orderInfo != null && orderInfo.size() > 0) {
            oldOrderInfo.addAll(orderInfo);
        }
        Map map = new HashMap();
        Map newInfoMap = null;
        Double needminus = 0.0;
        List newraftlist = null;
        if (newOrderInfo != null && newOrderInfo.size() > 0) {
            newOrderInfo = mergenewInfo(newOrderInfo);
            newInfoMap = calculateNewOrderInfo(newOrderInfo, stdt, usid, Long.parseLong(iscenicid));
            needminus = (Double) newInfoMap.get("amont");// 新增产品的金额
            newraftlist = (List) newInfoMap.get("raftlist");// 新增产品所需的竹筏趟次列表及数量
        }
        Map oldInfoMap = calculateOldOrder(orid, iscenicid, oldOrderInfo);
        List chalist = (List) oldInfoMap.get("chaticket");// 根据产品算出的需要加减的产品列表
        if ((chalist == null || chalist.size() < 1) && (newOrderInfo == null || newOrderInfo.size() < 1)) {
            map.put("result", false);
            map.put("errtp", "5");// 日控制
            return map;
        }
        List charraft = (List) oldInfoMap.get("charraft");// 根据竹筏信息算出来的需要加减的竹筏量
        List newdayslist = null;
        List olddayslist = null;
        if (newInfoMap != null) {
            newdayslist = (List) newInfoMap.get("daylist");
        }
        if (oldInfoMap != null) {
            olddayslist = (List) oldInfoMap.get("chaday");
        }
        List exceptiondays = validateDay(newdayslist, olddayslist);
        if (exceptiondays != null && exceptiondays.size() > 0) {
            map.put("result", false);
            map.put("errtp", "0");// 日控制
            map.put("errlist", exceptiondays);
            return map;
        } else {
            // 首先判断竹筏量是否满足,即原订单中退订的票种含有的竹筏量与修改订单之后新增的竹筏量相比较
            List exceptionlist = validateRaft(newraftlist, charraft);
            if (exceptionlist != null && exceptionlist.size() > 0) {
                map.put("result", false);
                map.put("errtp", "1");// 趟次控制
                map.put("errlist", exceptionlist);
                return map;
            }
        }
        boolean hyqt = CommonUtil.isHqyt();
        Double oldneedmount = (Double) oldInfoMap.get("amount");
        if(!hyqt) {
            Float pointes = getsumjifen(usid);  //获取用户
            // 比较两个map中的值，计算是否可以修改 不可修改返回false
            if (pointes.doubleValue() < oldneedmount + needminus) {
                map.put("result", false);
                map.put("errtp", "2");// 余额不足
                return map;
            }
        }

        Sysparv5 syspar = (Sysparv5) this.payorderDao.get(Sysparv5.class, new Sysparv5Id("TDXG", "****"));
        if (syspar != null) {
            boolean b = Boolean.parseBoolean(syspar.getPmvb());
            if (Tools.getDayNumbCha(Tools.getDays(), stdt) == 0) {
                if (syspar.getPmvc() != null && !syspar.getPmvc().equals("")) {
                    boolean bs = Boolean.parseBoolean(syspar.getPmvc());
                    if (!bs) {
                        if (oldneedmount + needminus > 0) {
                            map.put("result", false);
                            map.put("errtp", "20");// 订单修改只允许金额减少,不能增加
                            return map;
                        }
                    }
                }
            } else {
                if (!b) {
                    if (oldneedmount + needminus > 0) {
                        map.put("result", false);
                        map.put("errtp", "20");// 订单修改只允许金额减少,不能增加
                        return map;
                    }
                }
            }

        }

        map.put("result", true);
        map.put("needmoney", oldneedmount + needminus);
        return map;
    }
    //TODO 退订修改订单
    public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid)
            throws Exception {
        boolean hqyt = CommonUtil.isHqyt();
        Map returnmap = new HashMap();
        try {
            Custom custom = (Custom) morderdao.get(Custom.class, usid);
            MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
            Map newmap = null;
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfo(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrder(oldorid, iscenicid, orderlistInfo);
            //计算优惠
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && addsonProductlist.size() > 0 && oldaddlist != null && oldaddlist.size() > 0) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue()) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (addsonProductlist != null) {
                oldaddlist.addAll(addsonProductlist);
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())
                                    && compare.getPrice() == compare1.getPrice()) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())
                                    && compare.getPrice() == compare1.getPrice()) {
                                compare.setNumb(compare1.getNumb() + compare.getNumb());
                                break;
                            }
                        }
                    }
                }
            }
            // 原订单修改后的差异集合
            List chalist = (List) oldmap.get("chaticket");
            List addlist = new ArrayList();
            List backlist = new ArrayList();
            for (int i = 0; i < chalist.size(); i++) {
                TOrderlist orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }

            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();

                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    String groupno = ticketDao.searchJgfz(custom.getUsid(), ticket.getIscenicid());

                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                /*	pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
					kindprice = (Edmcrowdkindpricetab) pricelist.get(0);*/
                    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                    newproduct.setDtstartdate(stdt);
					/*ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));*/
                    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                    newproduct.setPric(kindprice.getMactualsaleprice());
                    newproduct.setJsprice(kindprice.getJsprice());
                    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                    newproduct.setYhnumb(0l);
                    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                    newproduct.setYhamnt(0.0);
                    List sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            newproduct.setWharfdate(control.getStdata());
                            newproduct.setPdctrolid(control.getProductcontrolid());
                            Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString());
                            tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                            tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                            tzorderlist.setTripid(control.getTripid());
                            tzorderlist.setIvenueid(prd.getIvenueid());
                            tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                        } else {
                            tzorderlist.setDtstartdate(stdt + " 00:00:00");
                            tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                        }
                        tzorderlist.setZpric(pricepost.getMactualsaleprice());
                        tzorderlist.setJsprice(pricepost.getJsprice());
                        tzorderlist.setZnumb(pricepost.getNumb() * newproduct.getNumb());
                        tzorderlist.setZyhnumb(0l);
                        tzorderlist.setZyhamnt(0.0);
                        tzorderlist.setZamnt(tzorderlist.getZnumb() * pricepost.getMactualsaleprice());
                        newproduct.getZorderlist().add(tzorderlist);
                    }
                    addlist.add(newproduct);
                }
                for (int j = 0; j < addlist.size(); j++) {// 原订单新增的票
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = null;
                    if (orderlist.getOrderlistid() != null && !orderlist.getOrderlistid().equals("")) {
                        tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    } else {
                        tzlist = orderlist.getZorderlist();
                    }
                    orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        List<Edmticketcomposepricetab> sprices = yorderlistdao.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    if (orderlist.getZorderlist() == null) {
                        orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    }
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        Edmtickettypetab ticket = (Edmtickettypetab) yorderlistdao.get(Edmtickettypetab.class, newz.getIztickettypeid());
                        if (ticket.getBycategorytype().equals("0003")) {
                            orderlist.setHasraft(1);
                            Productcontrol cntrol = ticketDao.getNumberControl(zlist.getTripid(), zlist.getIvenueid(), zlist.getIvenueareaid(), zlist.getDtstartdate().substring(0, 10));
                            orderlist.setPdctrolid(cntrol.getProductcontrolid());
                            orderlist.setWharfdate(cntrol.getStdata());
                        }
                        List<Edmticketcomposepricetab> sprices = yorderlistdao.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();

                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    String groupno = ticketDao.searchJgfz(custom.getUsid(), ticket.getIscenicid());

                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                    newproduct.setDtstartdate(stdt);
//					ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                    newproduct.setPric(kindprice.getMactualsaleprice());
                    newproduct.setJsprice(kindprice.getJsprice());
                    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                    newproduct.setYhnumb(0l);
                    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                    newproduct.setYhamnt(0.0);
                    List sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString());
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            newproduct.setPdctrolid(control.getProductcontrolid());
                            tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                            tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                            tzorderlist.setTripid(control.getTripid());
                            tzorderlist.setIvenueid(prd.getIvenueid());
                            tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                        } else {
                            tzorderlist.setDtstartdate(stdt + " 00:00:00");
                            tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                        }
                        tzorderlist.setZpric(pricepost.getMactualsaleprice());
                        tzorderlist.setJsprice(pricepost.getJsprice());
                        tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
                        tzorderlist.setZyhnumb(0l);
                        tzorderlist.setZyhamnt(0.0);
                        tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
                        newproduct.getZorderlist().add(tzorderlist);
                    }
                    addlist.add(newproduct);
                }
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
            }
            double totalmont = 0;
            double tdmont = 0;
            boolean bs = false;
            double yhmoney = 0;
            double ysmoney = 0;  //原始订单优惠
            List fwyhlist = new ArrayList();  //服务商优惠列表
            //判断服务商优惠
            Edpofferschemetab pscheme = this.ticketDao.checkEdpschemet(Long.parseLong(iscenicid), stdt, Long.parseLong(ibusiness));

            if (pscheme != null) {
                //服务商优惠在这里进行判断，重载calculateAddMont方法。
                bs = true;
                List schemelist = new ArrayList();

                Long xsnums = 0L;
                Double xsmoney = 0D; //实际销售总金额
                Double ydmoney = 0D; //原订单销售总金额
                Double tdmoney = 0D; //退订金额
                List torderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
                //获取修改后此订单某服务商的销售信息
                //原订单
                if (torderlists != null && torderlists.size() > 0) {
                    for (int i = 0; i < torderlists.size(); i++) {
                        TOrderlist torder = (TOrderlist) torderlists.get(i);

                        TOrderlist newtorderlist = new TOrderlist();
                        BeanUtils.copyProperties(newtorderlist, torder);

                        xsnums += torder.getNumb();
                        xsmoney += torder.getAmnt();
                        ydmoney += torder.getAmnt();
                        ysmoney += torder.getYhamnt();

                        schemelist.add(newtorderlist);
                    }
                }
                //新增订单
                if (addlist != null && addlist.size() > 0) {
                    for (int x = 0; x < addlist.size(); x++) {
                        TOrderlist torderlist = (TOrderlist) addlist.get(x);
                        boolean b = false;
                        for (int y = 0; y < schemelist.size(); y++) {
                            TOrderlist order = (TOrderlist) schemelist.get(y);
                            if (order != null && order.getItickettypeid() == torderlist.getItickettypeid() && order.getIcrowdkindpriceid() == torderlist.getIcrowdkindpriceid()) {
                                b = true;
                                order.setNumb(order.getNumb() + torderlist.getNumb());
                                order.setYhnumb(order.getYhnumb() + torderlist.getYhnumb());
                                order.setAmnt(order.getAmnt() + torderlist.getAmnt());
                                order.setYhamnt(order.getYhamnt() + torderlist.getYhamnt());
                                break;
                            }
                        }
                        torderlist.setYhamnt(0D);
                        torderlist.setYhnumb(0L);

                        xsnums += torderlist.getNumb();
                        xsmoney += torderlist.getAmnt();
                        if (!b) {
                            schemelist.add(torderlist);
                        }
                    }
                }
                //退订订单
                if (backlist != null && backlist.size() > 0) {
                    for (int x = 0; x < backlist.size(); x++) {
                        TOrderlist torderlist = (TOrderlist) backlist.get(x);

                        for (int y = 0; y < schemelist.size(); y++) {
                            TOrderlist order = (TOrderlist) schemelist.get(y);

                            if (order.getItickettypeid().longValue() == torderlist.getItickettypeid().longValue() && order.getIcrowdkindpriceid().longValue() == torderlist.getIcrowdkindpriceid().longValue()) {
                                if (Math.abs(order.getNumb()) == Math.abs(torderlist.getNumb())) {
                                    schemelist.remove(order);
                                } else {
                                    order.setNumb(order.getNumb() + torderlist.getNumb());
                                    order.setAmnt(order.getAmnt() + torderlist.getAmnt());
                                }

                                break;
                            }
                        }

                        xsnums += torderlist.getNumb();
                        tdmoney += torderlist.getAmnt();
                        xsmoney += torderlist.getAmnt();
                    }
                }

                //优惠数量
                Long yhnum = (xsnums / pscheme.getImultiples()) * pscheme.getIoffernum();
                if (yhnum > 0) {
                    String yhlx = "0";//获取优惠方式  0 -最高价  1-最低价
                    Hotelprovider hotel = (Hotelprovider) this.ticketDao.get(Hotelprovider.class, Long.parseLong(iscenicid));
                    if (hotel != null && hotel.getInoteger8() != null) {
                        yhlx = hotel.getInoteger8().toString();
                    }

                    //最高价优惠 排序
                    if (yhlx.equals("0")) {
                        Collections.sort(schemelist, new Comparator<TOrderlist>() {
                            public int compare(TOrderlist o1, TOrderlist o2) {
                                double price1 = o1.getPric();
                                double price2 = o2.getPric();
                                if (price2 > price1) {
                                    return 1;
                                } else if (price2 == price1) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        });
                    }
                    //最低价优惠 排序
                    if (yhlx.equals("1")) {
                        Collections.sort(schemelist, new Comparator<TOrderlist>() {
                            public int compare(TOrderlist o1, TOrderlist o2) {
                                double price1 = o1.getPric();
                                double price2 = o2.getPric();
                                if (price1 > price2) {
                                    return 1;
                                } else if (price1 == price2) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        });
                    }

                    for (int j = 0; j < schemelist.size(); j++) {
                        TOrderlist t = (TOrderlist) schemelist.get(j);

                        if (yhnum > 0) {
                            if (yhnum > t.getNumb()) {
                                t.setYhamnt(t.getNumb() * t.getPric());
                                t.setYhnumb(t.getNumb());
                                t.setIoffersschemeid(pscheme.getIoffersschemeid());

                                yhmoney += t.getNumb() * t.getPric();
                                yhnum -= t.getNumb();

                            } else {
                                t.setYhamnt(yhnum * t.getPric());
                                t.setYhnumb(yhnum);
                                t.setIoffersschemeid(pscheme.getIoffersschemeid());

                                yhmoney += yhnum * t.getPric();
                                yhnum = 0L;

                            }

                            fwyhlist.add(t);  //服务商优惠列表
                        }
                    }

                    //新增订单的优惠信息
                    if (addlist != null && addlist.size() > 0) {
                        for (int x = 0; x < addlist.size(); x++) {
                            TOrderlist torderlist = (TOrderlist) addlist.get(x);
                            for (int y = 0; y < fwyhlist.size(); y++) {
                                TOrderlist order = (TOrderlist) schemelist.get(y);
                                if (order != null && order.getItickettypeid().longValue() == torderlist.getItickettypeid().longValue() && order.getIcrowdkindpriceid().longValue() == torderlist.getIcrowdkindpriceid().longValue()) {
                                    torderlist.setYhnumb(order.getYhnumb());
                                    torderlist.setYhamnt(order.getYhamnt());
                                    torderlist.setIoffersschemeid(pscheme.getIoffersschemeid());
                                }
                            }

                        }
                    }

                    // 计算出需要增加的金额
                    totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                    // 需要退订的钱
                    tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));

                    if ((yhmoney - ysmoney) < 0) {
                        tdmont = tdmont - (yhmoney - ysmoney);
                    } else if ((yhmoney - ysmoney) > 0) {
                        totalmont = totalmont - (yhmoney - ysmoney);
                    }

                } else {
                    // 计算出需要增加的金额
                    totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                    // 需要退订的钱
                    tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));

                    if ((yhmoney - ysmoney) < 0) {
                        tdmont = tdmont - (yhmoney - ysmoney);
                    } else if ((yhmoney - ysmoney) > 0) {
                        totalmont = totalmont - (yhmoney - ysmoney);
                    }

                }

            } else {
                // 计算出需要增加的金额
                totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                // 需要退订的钱
                tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
            }

            double totalyhmont = 0.0;

            if (bs) {//服务商优惠金额  lijingrui 
                totalyhmont = yhmoney - ysmoney;  //优惠金额变动
            } else {
                for (int x = 0; x < addlist.size(); x++) {
                    TOrderlist orderlist = (TOrderlist) addlist.get(x);
                    totalyhmont += orderlist.getPric() * orderlist.getYhnumb();
                }
                for (int x = 0; x < backlist.size(); x++) {
                    TOrderlist orderlist = (TOrderlist) backlist.get(x);
                    totalyhmont -= orderlist.getPric() * orderlist.getYhnumb();
                }
            }

            double userMoney = CommonUtil.getUserMoney(oldmorder.getZfusid());

            //比较两个map中的值，计算是否可以修改 不可修改返回false
            if(!hqyt) {
                if (tdmont > 0) {
                    if (userMoney < Math.abs(tdsx) + totalmont - tdmont) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "2");// 余额不足
                        return returnmap;
                    }
                } else {
                    if (userMoney < Math.abs(tdsx) + totalmont + tdmont) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "2");// 余额不足
                        return returnmap;
                    }
                }
            }

            List charraftlist = null;
            List newdaylist = null;
            List olddaylist = null;
            int istoprat = 0;
            if (oldmap != null) {
                charraftlist = (List) oldmap.get("charraft");
                olddaylist = (List) oldmap.get("chaday");
            }
            List newraft = null;
            if (newmap != null) {
                newdaylist = (List) newmap.get("daylist");
                newraft = (List) newmap.get("raftlist");
            }
            long totalmonthjf = 0;
            long totalyearjf = 0;
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                long addmonthjf = 0;
                long addyearjf = 0;
                Numjifenset set = ticketService.getNumjifenset(iscenicid);
                if (addlist != null && addlist.size() > 0) {
                    for (int i = 0; i < addlist.size(); i++) {
                        TOrderlist torderlist = (TOrderlist) addlist.get(i);
                        TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                                torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                        Numjifensetlist detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), custom.getIbusinessid(),
                                torderlist.getDtstartdate());
                        if (detail != null) {
                            if (detail.getXffs().equals("03")) {// 月积分
                                long jf = 0;
                                if (old != null) {// 如果订单里面没有此产品
                                    if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                    }
                                    jf = jf - old.getIsi();
                                } else {
                                    if (torderlist.getNumb() % detail.getIint3() > 0) {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4();
                                    }
                                }
                                addmonthjf += jf;
                            } else {
                                long jf = 0;
                                if (old != null) {// 如果订单里面没有此产品
                                    if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                    }
                                    jf = jf - old.getIsh();
                                } else {
                                    if (torderlist.getNumb() % detail.getIint3() > 0) {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4();
                                    }
                                }
                                addyearjf += jf;
                            }
                        }
                    }
                }
                long tdmonthjf = 0;
                long tdyearjf = 0;
                if (backlist != null && backlist.size() > 0) {
                    for (int i = 0; i < backlist.size(); i++) {
                        TOrderlist torderlist = (TOrderlist) backlist.get(i);
                        TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                                torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                        Numjifensetlist detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), custom.getIbusinessid(),
                                torderlist.getDtstartdate());
                        if (detail != null) {
                            if (detail.getXffs().equals("03")) {// 月积分
                                long jf = 0;
                                if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                } else {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                }
                                jf = jf - old.getIsi();// 一定会是0或者负数
                                tdmonthjf += jf;
                            } else {
                                long jf = 0;
                                if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                } else {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                }
                                jf = jf - old.getIsh();// 一定会是0或者负数
                                tdyearjf += jf;
                            }
                        }
                    }
                }
                totalmonthjf = addmonthjf - Math.abs(tdmonthjf);
                totalyearjf = addyearjf - Math.abs(tdyearjf);
                TOrder oldtorder = (TOrder) get(TOrder.class, new TOrderId(oldmorder.getOrid(), Long.parseLong(iscenicid)));
                Map map = DateUtils.getMonthDate(Integer.parseInt(oldtorder.getDtstartdate().substring(0, 4)), Integer.parseInt(oldtorder.getDtstartdate().substring(5, 7)));
//                Usernumjf usermonthjifen = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), oldmorder.getZfusid(), 1l, Long.parseLong(iscenicid));
                String usermonthjifenJSON = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), oldmorder.getZfusid(), 1l, Long.parseLong(iscenicid));
                Usernumjf usermonthjifen = JSON.parseObject(usermonthjifenJSON, Usernumjf.class);
                
                
                String useryearjifenJSON = ticketService.getJifenByUser(map.get("startDate").toString().substring(0, 4) + "-01-01", map.get("endDate").toString().substring(0, 4) + "-12-31", oldmorder.getZfusid(), 2l, Long.parseLong(iscenicid));
                Usernumjf useryearjifen = JSON.parseObject(useryearjifenJSON, Usernumjf.class);
                
                if (totalmonthjf > 0) {
                    if (totalmonthjf > usermonthjifen.getPoint().intValue() - usermonthjifen.getYpoint().intValue()) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "7");// 积分不足
                        returnmap.put("ordernum", totalmonthjf);
                        returnmap.put("usernum", usermonthjifen.getPoint().intValue() - usermonthjifen.getYpoint().intValue());
                        returnmap.put("numtype", "03");//年
                        return returnmap;
                    }
                }
                if (totalyearjf > 0) {
                    if (totalyearjf > useryearjifen.getPoint().intValue() - useryearjifen.getYpoint().intValue()) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "7");// 积分不足
                        returnmap.put("ordernum", totalyearjf);
                        returnmap.put("usernum", useryearjifen.getPoint().intValue() - useryearjifen.getYpoint().intValue());
                        returnmap.put("numtype", "04");//年
                        return returnmap;
                    }
                }

            }
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);
            Sysparv5 maxcredit = (Sysparv5) morderdao.get(Sysparv5.class, new Sysparv5Id("YDJF", "07"));
            // 根据订单用户获取需要消耗信用度的用户
            String credusid = getFsusid(oldmorder.getUsid());
            // 计算信用度
            Long creditnum = calculateCredit(credusid, charraftlist, iscenicid);
            Credit credit = (Credit) morderdao.get(Credit.class, credusid);
            boolean ispositive = queryPositiveraft(charraftlist, iscenicid);
            if (!oldmorder.getDdzt().equals("23")) {
                if (credit != null) {
                    if (credit.getCreditnumb().intValue() + creditnum.intValue() > Integer.parseInt(maxcredit.getPmvb()) && ispositive) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "6");// 信用度不够订竹筏为正数的趟次
                        return returnmap;
                    } else {
                        Creditdetail creditdetail = new Creditdetail();
                        creditdetail.setUsid(credusid);
                        creditdetail.setCreditnumb(creditnum);
                        creditdetail.setZusid(oldmorder.getUsid());
                        creditdetail.setCtype("01");
                        creditdetail.setOrid(oldmorder.getOrid());
                        creditdetail.setCseq(payorderDao.getMaxPk("cseq", "Creditdetail") + 1);
                        payorderDao.save(creditdetail);
                        credit.setCreditnumb(credit.getCreditnumb() + creditnum);
                        payorderDao.update(credit);
                    }
                }

            }
            istoprat = checkStopRaft(charraftlist, iscenicid);
            // 新增订单 返回要消费的积分
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null);
            //退订单返回要退订的积分
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist);
            // 这里以后oldtdlist中的值将不能使用,因为在封装退订单的时候里面的值已经改变
            TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont + totalyhmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            oldtorder.setYhamnt(oldtorder.getYhamnt() + totalyhmont);
            //	oldmorder.setYhamnt(oldmorder.getYhamnt() + totalyhmont);
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldmorder.getTpmont() != null) {
                if (tdmont > 0) {
                    oldmorder.setTpmont(oldmorder.getTpmont() - totalmont - tdmont);
                } else {
                    oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
                }

            } else {
                if (tdmont > 0) {
                    oldmorder.setTpmont(0 - totalmont - tdmont);
                } else {
                    oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
                }

            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            oldmorder.setIsf(1l);
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                if (oldtorder.getIsi() == null) {
                    oldtorder.setIsi(0l);
                }
                if (oldtorder.getIsh() == null) {
                    oldtorder.setIsh(0l);
                }
                if (oldmorder.getIsi() == null) {
                    oldmorder.setIsi(0l);
                }
                if (oldmorder.getIsh() == null) {
                    oldmorder.setIsh(0l);
                }
                oldtorder.setIsi(oldtorder.getIsi() + totalmonthjf);
                oldmorder.setIsi(oldmorder.getIsi() + totalmonthjf);
                oldtorder.setIsh(oldtorder.getIsh() + totalyearjf);
                oldmorder.setIsh(oldmorder.getIsh() + totalyearjf);
            }
            yorderlistdao.update(oldtorder);
            yorderlistdao.update(oldmorder);
            calculateCredit(oldmorder.getUsid(), charraftlist, iscenicid);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);
            if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
            	sysparService.sendMessageNew(oldtorder.getOrph(), "0004", oldtorder.getId().getOrid());
            }
            //
            this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "27");
            this.updateMOrderStatusByZeroProduct(oldorid, "27");

            //lijingrui 修改 服务商优惠 修改原订单中的优惠数量、金额
            List xgtList = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            if (xgtList != null && xgtList.size() > 0) {
                for (int i = 0; i < xgtList.size(); i++) {
                    TOrderlist torders = (TOrderlist) xgtList.get(i);

                    if (fwyhlist != null && fwyhlist.size() > 0) {
                        for (int m = 0; m < fwyhlist.size(); m++) {
                            TOrderlist torder = (TOrderlist) fwyhlist.get(m);
                            if (torder.getItickettypeid().longValue() == torders.getItickettypeid().longValue() && torder.getIcrowdkindpriceid().longValue() == torders.getIcrowdkindpriceid().longValue()) {
                                torders.setYhamnt(torder.getYhamnt());
                                torders.setYhnumb(torder.getYhnumb());
                                torders.setIoffersschemeid(torder.getIoffersschemeid());

                                List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                for (int z = 0; z < list.size(); z++) {
                                    TZorderlist newzlist = (TZorderlist) list.get(z);
                                    newzlist.setZyhamnt(torder.getYhnumb() * newzlist.getZpric());
                                    newzlist.setZyhnumb(torder.getYhnumb());

                                    yorderlistdao.update(newzlist);
                                }

                                yorderlistdao.update(torders);
                            } else {
                                if (torders.getYhnumb() > 0) {
                                    torders.setYhamnt(0D);
                                    torders.setYhnumb(0L);
                                    torders.setIoffersschemeid(0L);

                                    List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                    for (int z = 0; z < list.size(); z++) {
                                        TZorderlist newzlist = (TZorderlist) list.get(z);
                                        newzlist.setZyhamnt(0D);
                                        newzlist.setZyhnumb(0L);

                                        yorderlistdao.update(newzlist);
                                    }

                                    yorderlistdao.update(torders);

                                } else if (torders.getYhnumb() != null && torders.getIoffersschemeid() != null && torders.getYhnumb() == 0 && torders.getIoffersschemeid() > 0) {
                                    torders.setIoffersschemeid(0L);
                                    yorderlistdao.update(torders);
                                }
                            }

                        }
                    } else {
                        if (bs) {
                            if (torders.getYhnumb() > 0) {
                                torders.setYhamnt(0D);
                                torders.setYhnumb(0L);
                                torders.setIoffersschemeid(0L);

                                List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                for (int z = 0; z < list.size(); z++) {
                                    TZorderlist newzlist = (TZorderlist) list.get(z);
                                    newzlist.setZyhamnt(0D);
                                    newzlist.setZyhnumb(0L);

                                    yorderlistdao.update(newzlist);
                                }

                                yorderlistdao.update(torders);

                            } else if (torders.getYhnumb() != null && torders.getIoffersschemeid() != null && torders.getYhnumb() == 0 && torders.getIoffersschemeid() > 0) {
                                torders.setIoffersschemeid(0L);
                                yorderlistdao.update(torders);
                            }
                        }

                    }

                }
            }
            //判断库存信息
            List<TOrderlist> newTorderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            if (custom.getIbusinessid() == 2L && custom.getUstp().equals("02") && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getUsid();
            }
            if (newTorderlists != null && !newTorderlists.isEmpty()) {//部分修改
                for (TOrderlist ntl : newTorderlists) {
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
//                ticketService.saveStockDetails(stockOrderInfos, true);
                ticketService.saveStockDetails(JSON.toJSONString(stockOrderInfos), true);
            } else {//全退订
            	ticketService.deleteStockDetails(oldorid, Long.parseLong(iscenicid), null, null);
            }

            if(StringUtils.isBlank(oldmorder.getNoteh())){
                throw new RuntimeException("支付号不存在");
            }
            if(hqyt){
                MOrder morder = (MOrder) ticketDao.get(MOrder.class,orids[1]);
                HqytClient client = new HqytClient();
                RefundbillsRequest request = new RefundbillsRequest();
                request.setId(Long.parseLong(oldmorder.getNoteh()));
                request.setRefundMoney(Math.abs(tdmont)-Math.abs(tdsx));
                request.setRefundOrid(orids[1]);
                request.setReason(custom.getUsid()+"操作网上退订");
                request.setMemo("退款金额:"+Math.abs(tdmont)+";手续费:"+Math.abs(tdsx));
                try{
                    JSONRefundBill refundBill = client.refundbills(request);
                    if(refundBill != null){
                        morder.setNoteh(refundBill.getInvoice().getId().toString());
                        morder.setNotec("2");//标识退款状态为退款中 0：退款失败 1：退款成功 2：退款中
                        morder.setDdzt("31");
                        ticketDao.update(morder);
                    }else{
                        throw new RuntimeException("退订失败:申请退订失败");
                    }
                }catch (Exception e){
                    throw new RuntimeException("退订失败:"+e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")) {
                throw new RuntimeException(e.getMessage());
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        return returnmap;
    }

    public void backOrderJf(String orid, String usid, int year, int month, String backorid, Long iscenicid) {
//        Map map = StatisticsService.getMonthDate(year, month);
        try {
			Map map = DateUtils.getMonthDate(year, month);
			String userjfJSON = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), usid, 1l, iscenicid);
			Usernumjf userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
			MOrder morder = morderdao.getMOrder(orid);
			TOrder torder = torderdao.getTOrder(orid, iscenicid.toString());
			Numjifenset set = ticketService.getNumjifenset(torder.getId().getIscenicid().toString());
			Usernumjflist jifenlist = new Usernumjflist();
			jifenlist.setId(new UsernumjflistId(backorid, morder.getZfusid()));
			jifenlist.setItickettypeid(BigDecimal.valueOf(0));
			jifenlist.setItickettypeid2(BigDecimal.valueOf(0));
			jifenlist.setNid(BigDecimal.valueOf(set.getNid()));
			jifenlist.setIscenicid(BigDecimal.valueOf(torder.getId().getIscenicid()));
			jifenlist.setStdt2(Tools.getDays());
			jifenlist.setEtdt(Tools.getDays());
			jifenlist.setJflb(BigDecimal.valueOf(-1));
			jifenlist.setPoint(Double.valueOf(torder.getIsi()));
			jifenlist.setStdt(Tools.getDays());
			jifenlist.setZusid(morder.getZfusid());
			jifenlist.setIsvalid(BigDecimal.valueOf(1));
			this.save(jifenlist);
			morder.setIsi(morder.getIsi() - torder.getIsi());
			userjf.setPoint(userjf.getPoint() + torder.getIsi());
			torder.setIsi(0l);
			torderdao.update(torder);
			morderdao.update(morder);
			this.update(userjf);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Describe:从底层往上层查询旅行社分社usid
     *
     * @param usid
     * @return
     * @throws Exception return:String Date:2012-4-8
     * @auth:yangguang
     */
    public String getFsusid(String usid) throws Exception {
        Custom custom = null;
        Custom pcustom = null;
        String orderusid = usid;
        String returnusid = "";
        while (true) {
            custom = (Custom) payorderDao.get(Custom.class, usid);
            if (custom.getSusid() == null || custom.getSusid().equals("") || custom.getUsid().equals(custom.getSusid())) {
                throw new Exception("数据异常,请检查用户[" + usid + "]的信息是否正确");
            } else {
                pcustom = (Custom) payorderDao.get(Custom.class, custom.getSusid());
                if (custom.getLgtp().equals("02") && custom.getUstp().equals("02") && pcustom.getUstp().equals("01")) {
                    returnusid = custom.getUsid();
                    break;
                } else {
                    usid = pcustom.getUsid();
                    continue;
                }
            }
        }
        return returnusid;
    }

    private int checkStopRaft(List charraftlist, String iscenicid) {
        Productcontrol control = null;
        RaftComparepojo c1 = null;
        Prdtripvenuemanage prd = null;
        int result = 0;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                control = ticketDao.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());
                if (control.getBystate() == 0) {
                    result = 1;
                }
            }
        }
        return result;
    }

    // 计算传入的list的金额
    private double calculateAddMont(List list, Long iscenicid, String orid, Long ibusinessid) {
        double totalmont = 0.0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TOrderlist torderlist = (TOrderlist) list.get(i);
                TOrderlist oldobj = torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                        torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                int cnum = 0;
                int thistime = 0;
                Edpofferschemetab schema = ticketDao.getScheme(iscenicid, ibusinessid, torderlist.getIcrowdkindid(), torderlist.getItickettypeid(), torderlist.getDtstartdate().substring(0, 10));
                if (schema != null) {
                    if (oldobj != null) {
                        // 计算出修改之后总数量可有优惠的数量
                        cnum = (oldobj.getNumb().intValue() + torderlist.getNumb().intValue()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                        thistime = cnum - oldobj.getYhnumb().intValue();
                        // 计算次本次修改之后需要收费产品的数量
                        cnum = torderlist.getNumb().intValue() - thistime;
                    } else {
                        thistime = torderlist.getNumb().intValue() / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                        cnum = torderlist.getNumb().intValue() - thistime;
                    }
                    torderlist.setIoffersschemeid(schema.getIoffersschemeid());
                    torderlist.setYhnumb(new Long(Math.abs(thistime)));
                } else {
                    cnum = torderlist.getNumb().intValue();
                }
                totalmont += torderlist.getPric() * cnum;
            }
        }
        return totalmont;
    }

    // 计算传入的list的金额
    private double calculateAddMont(List list, Long iscenicid, String orid) {
        double totalmont = 0.0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TOrderlist torderlist = (TOrderlist) list.get(i);
                TOrderlist oldobj = torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                        torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                int cnum = 0;
                int thistime = 0;
                cnum = torderlist.getNumb().intValue();
                totalmont += torderlist.getPric() * cnum;
            }
        }
        return totalmont;
    }

    // 新增订单(退订单，或者增量订单)
    private double addNewOrder(List addlist, String oldorid, String neworid, String iscenicid, String currentusid, MOrder oldmorder, double totalmont, String ortp, int stopraft, double sxf,
                               List oldtdlist) throws Exception {
        Custom user = (Custom) yorderdao.get(Custom.class, oldmorder.getUsid());
        // 开始封装订单
        double totaltdsx = 0.0;
        if (addlist != null && addlist.size() > 0) {
            //  增量订单neworderlist 和 orderlistInfo与 原订单中中数量为对比后 挑选出来的数量为正数的产品
            oldmorder = (MOrder) ticketDao.get(MOrder.class, oldorid);
//            Map map = StatisticsService.getMonthDate(Integer.parseInt(oldmorder.getStdt().substring(0, 4)), Integer.parseInt(oldmorder.getStdt().substring(5, 7)));
            Map map = DateUtils.getMonthDate(Integer.parseInt(oldmorder.getStdt().substring(0, 4)), Integer.parseInt(oldmorder.getStdt().substring(5, 7)));
            MOrder morder = new MOrder();
            YOrder yorder = new YOrder();
            morder.setOrid(neworid);
            morder.setOrtp(ortp);// 订单类型 03：增量订单
            morder.setUsid(oldmorder.getUsid());
            morder.setOrda(Tools.getDays());
            morder.setOrti(Tools.getNowString().substring(11, Tools.getNowString().length()));
            morder.setIsj(0l);
            morder.setYhamnt(0.0);// 优惠金额
            morder.setMont(0.0);
            morder.setZfmont(0.0);// 支付的金额
            morder.setIsallcp(0l);// 是否全部出票
            morder.setZffs("06");// 支付方式
            morder.setBank("92");// 支付银行
            morder.setZfusid(oldmorder.getZfusid());// 订单支付人
            morder.setPayorid("");// 支付订单号
            morder.setBankdata(Tools.getDays());// 支付日期
            morder.setBanktime(Tools.getNowTimeString());// 支付时间
            morder.setSrid(oldorid);// 对应原订单号
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                morder.setIsjl(1l);
            } else {
                morder.setIsjl(0l);
            }
            morder.setTpfs("02");
            ticketDao.save(morder);
            YOrder oldyorder = (YOrder) ticketDao.get(YOrder.class, new YOrderId(oldorid, Long.parseLong(iscenicid)));
            yorder.setId(new YOrderId(morder.getOrid(), Long.parseLong(iscenicid)));
            // 根据编号找服务商类型
            Esbscenicareatab esb = (Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(iscenicid));
            yorder.setScenictype(esb.getScenictype());// 服务商类别 01 景区 06酒店 07 旅行社
            // 08 超市 09 租赁公司
            yorder.setUsid(morder.getUsid());// 游客编号
            yorder.setIbusinessid(user.getIbusinessid());// 业务id
            yorder.setSztravelbillno("");// 行程单号
            yorder.setIregionalid(0l);// 客源地id
            yorder.setTdlx("");// 团队性质
            yorder.setDtstartdate(oldyorder.getDtstartdate());// 游览开始日期
            yorder.setDtenddate(oldyorder.getDtenddate());// 游览结束日期
            yorder.setDyusid("");// 导游id
            yorder.setOrnm("");// 领票人姓名
            yorder.setOrzj("");// 领票人证件类别
            yorder.setOrhm("");// 领票人证件号码
            yorder.setFaxno("");// 传真号
            yorder.setOrph("");// 领票人电话
            yorder.setMont(0.0);
            yorder.setYhamnt(0.0);// 优惠金额
            yorder.setZfmont(0.0);// 支付的金额
            yorder.setYoufei(0.0);// 邮费
            yorder.setTpfs("02");
            // ticketDao.save(torder);
            ticketDao.save(yorder);
            long indexpk = getMaxPk("id.orderlistid", new String[]{"id.iscenicid"}, new Long[]{Long.parseLong(iscenicid)}, new String[]{"id.orid"}, new String[]{oldorid}, "TOrderlist");
            int yhnum = 0;// 这个积分的..
            double yhamnt = 0.0;// 这个是优惠方案的
            int pk = 0;
            DecimalFormat format = new DecimalFormat("0.00");
            Numjifenset set = ticketService.getNumjifenset(iscenicid);
            long totalmonthjf = 0;
            long totalyearjf = 0;
            for (int i = 0; i < addlist.size(); i++) {
                TOrderlist torderlist = (TOrderlist) addlist.get(i);
                YOrderlist yorderlist = new YOrderlist();
                yorderlist.setId(new YOrderlistId(new Long(i) + 1, morder.getOrid(), Long.parseLong(iscenicid)));
                yorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());
                yorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());
                yorderlist.setItickettypeid(torderlist.getItickettypeid());
                yorderlist.setDtstartdate(torderlist.getDtstartdate());// 开始日期
                yorderlist.setDtenddate(torderlist.getDtenddate());// 结束日期
                yorderlist.setPric(torderlist.getPric());// 单价
                yorderlist.setJsprice(torderlist.getJsprice());// 结算价
                yorderlist.setNumb(Math.abs(torderlist.getNumb()));// 数量
                yorderlist.setYhnumb(torderlist.getYhnumb());// 优惠数量
                yorderlist.setAmnt(Math.abs(torderlist.getNumb() * torderlist.getPric()));// 金额
                yorderlist.setYhamnt(Math.abs(torderlist.getYhnumb() * torderlist.getPric()));// 优惠金额
                yorderlist.setIoffersschemeid(torderlist.getIoffersschemeid());// 优惠方案ID
                yorderlistdao.save(yorderlist);
                TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                        torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                Numjifensetlist detail = null;
                if (set != null) {
                    detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), user.getIbusinessid(),
                            torderlist.getDtstartdate());
                }
                if (old != null) {// 退订时ID 肯定有, 如果是增量 那么ID可能有
                    // 可能没有有就修改 没有就新增
                    //  先获取原先的信息 然后再修改
                    long jf = 0;
                    if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                        if (detail.getXffs().equals("03")) {
                            // 计算数量变化后的积分
                            if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
                            } else {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4();
                            }
                            jf = jf - old.getIsi();
                            totalmonthjf += jf;
                            yorderlist.setIsi(Math.abs(jf));
                        } else {
                            // 计算数量变化后的积分
                            if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
                            } else {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4();
                            }
                            jf = jf - old.getIsh();
                            totalyearjf += jf;
                            yorderlist.setIsh(Math.abs(jf));
                        }
                    }
                    List list = tZOrderListService.getTZorderlist(old.getId().getOrderlistid(), old.getId().getOrid(), old.getId().getIscenicid());
                    for (int y = 0; y < list.size(); y++) {
                        double fl = 0.0;
                        TZorderlist newzlist = (TZorderlist) list.get(y);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
                        yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
                        yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
                        yzorderlist.setItickettypeid(newzlist.getItickettypeid());
                        yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
                        yzorderlist.setDtstartdate(newzlist.getDtstartdate());
                        yzorderlist.setDtenddate(newzlist.getDtenddate());
                        yzorderlist.setTripid(newzlist.getTripid());
                        yzorderlist.setIvenueid(newzlist.getIvenueid());
                        yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
                        yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
                        yzorderlist.setZpric(newzlist.getZpric());
                        yzorderlist.setZnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        yzorderlist.setZyhnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getYhnumb()));
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        yzorderlist.setJsprice(newzlist.getJsprice());
                        if (torderlist.getNumb().intValue() > 0) {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() + (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        } else {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() - (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        }
                        // 这里有可能是退订单，也可能是增量 根据原tzorderlist的子票数量除以torderlist的数量
                        // 再乘以修改后的数量(增量为正，退订为负),再乘以单价，然后加到原有的总价中(增量为证，退订为负)
                        newzlist.setZamnt(Math.abs(newzlist.getZamnt() + newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        double a = newzlist.getZamnt() + newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb();
                        //分时预约退订，返回库存
                        if(old.getTimeid() != null && old.getTimeid() != 0) {
//                        	TimeSharingService _SharingService = (TimeSharingService) SpringUtil.getBean("timeSharingService");
                            TimeSharingTicketTab _SharingTicketTab = (TimeSharingTicketTab) this.get(TimeSharingTicketTab.class, old.getTimeid());
                            int nNumb = torderlist.getNumb().intValue();
                            ticketService.UpdateStock(_SharingTicketTab.getId(), _SharingTicketTab.getProductId(), Math.abs(nNumb), "add");
                        }
                        newzlist.setZnumb(Math.abs(newzlist.getZnumb() + newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        Productcontrol control = ticketDao.getTripControl(newzlist.getTripid(), newzlist.getIvenueid(), newzlist.getIvenueareaid(), newzlist.getDtstartdate().substring(0, 10));
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {// 退订且原订单状态不为申请单
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                fl = gettdfl(yzorderlist, oldmorder.getUsid());
                                yzorderlist.setMhandcharge(yzorderlist.getZpric() * fl * yzorderlist.getZnumb());
                                yzorderlist.setTdfl(fl);
                                RaftComparepojo tdcompare = null;
                                for (int x = 0; x < oldtdlist.size(); x++) {
                                    tdcompare = (RaftComparepojo) oldtdlist.get(x);
                                    if (yzorderlist.getTripid().intValue() > 0) {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10))
                                                && tdcompare.getTripid().intValue() == yzorderlist.getTripid().intValue() && control != null && control.getBystate().intValue() == 1 && fl > 0) {
                                            //  这里比较数量 若需要退订的大于当前的数量 那么全部收取
                                            // 若小于则填充要收取的数量
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    } else {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10)) && fl > 0
                                                && tdcompare.getPrice() == yzorderlist.getZpric().doubleValue()) {
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    }
                                }
                            } else {
                                yzorderlist.setMhandcharge(0.0);
                                yzorderlist.setSqnumber(0l);
                            }
                        } else {
                            yzorderlist.setMhandcharge(0.0);
                            yzorderlist.setSqnumber(0l);
                        }
                        yorderlistdao.save(yzorderlist);
                        if (newzlist.getZnumb().intValue() < 1) {
                            yorderlistdao.delete(newzlist);
                        } else {
                            yorderlistdao.update(newzlist);
                        }
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                if (yorderlist.getMhandcharge() != null && !yorderlist.getMhandcharge().equals("")) {
                                    yorderlist.setMhandcharge(yorderlist.getMhandcharge() + yzorderlist.getMhandcharge());
                                } else {
                                    yorderlist.setMhandcharge(yzorderlist.getMhandcharge());
                                }
                            } else {
                                yorderlist.setMhandcharge(0.0);
                            }
                        } else {
                            yorderlist.setMhandcharge(0.0);
                        }
                    }
                    old.setNumb(Math.abs(old.getNumb() + torderlist.getNumb()));
                    old.setAmnt(old.getNumb() * old.getPric());
                    if (torderlist.getNumb().intValue() > 0) {
                        old.setYhnumb(old.getYhnumb() + torderlist.getYhnumb());
                    } else {
                        old.setYhnumb(old.getYhnumb() - torderlist.getYhnumb());
                    }
                    old.setYhamnt(old.getYhnumb() * old.getPric());
                    if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                        if (detail.getXffs().equals("03")) {//月消费
                            old.setIsi(old.getIsi() + jf);
                        } else {
                            old.setIsh(old.getIsh() + jf);
                        }
                    }
                    if (old.getNumb().intValue() < 1) {
                        yorderlistdao.delete(old);
                    } else {
                        yorderlistdao.update(old);
                    }
                } else {
                    pk++;
                    TOrderlist newtorderlist = new TOrderlist();
                    BeanUtils.copyProperties(newtorderlist, torderlist);
                    newtorderlist.setId(new TOrderlistId(new Long(indexpk + pk), oldorid, Long.parseLong(iscenicid)));
                    newtorderlist.setAmnt(Math.abs(newtorderlist.getAmnt()));
                    newtorderlist.setNumb(Math.abs(newtorderlist.getNumb()));
                    newtorderlist.setJsprice(Math.abs(newtorderlist.getJsprice()));
                    long jf = 0;
                    if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                        jf = torderlist.getNumb() / detail.getIint3().intValue() * detail.getIint4();
                        if (detail.getXffs().equals("03")) {
                            totalmonthjf += jf;
                            yorderlist.setIsi(jf);
                            newtorderlist.setIsi(jf);
                        } else {
                            totalyearjf += jf;
                            yorderlist.setIsh(jf);
                            newtorderlist.setIsh(jf);
                        }
                    }
                    newtorderlist.setYhnumb(torderlist.getYhnumb());
                    newtorderlist.setYhamnt(newtorderlist.getPric() * torderlist.getYhnumb());
                    yorderlistdao.saveOrUpdate(newtorderlist);
                    int indexpk2 = 0;
                    List<TZorderlist> tzorderlists = newtorderlist.getZorderlist();
                    for (int y = 0; y < tzorderlists.size(); y++) {// 如果走到这里肯定是新增的票,所以tzorderlist的数量实在传入时已经封装好的,无需再次封装
                        TZorderlist newzlist = new TZorderlist();
                        BeanUtils.copyProperties(newzlist, tzorderlists.get(y));
                        newzlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        newzlist.setId(new TZorderlistId(new Long(y + 1), newtorderlist.getId().getOrderlistid(), oldorid, newtorderlist.getId().getIscenicid()));
                        yorderlistdao.save(newzlist);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
                        yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
                        yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
                        yzorderlist.setItickettypeid(newzlist.getItickettypeid());
                        yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
                        yzorderlist.setDtstartdate(newzlist.getDtstartdate());
                        yzorderlist.setDtenddate(newzlist.getDtenddate());
                        yzorderlist.setTripid(newzlist.getTripid());
                        yzorderlist.setIvenueid(newzlist.getIvenueid());
                        yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
                        yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
                        yzorderlist.setZpric(newzlist.getZpric());
                        yzorderlist.setZnumb(Math.abs(newzlist.getZnumb()));
                        yzorderlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZamnt()));
                        yzorderlist.setJsprice(Math.abs(newzlist.getJsprice()));
                        yorderlistdao.save(yzorderlist);
                    }
                }
                if (ortp.equals("02") && !oldmorder.getDdzt().equals("23") && Math.abs(sxf) > 0) {// 如果是退订单
                    totaltdsx += yorderlist.getMhandcharge();
                }
                yorder.setYhamnt(yorder.getYhamnt() + yorderlist.getYhamnt());
                yorder.setMont(yorderlist.getAmnt() + yorder.getMont());
                yorder.setZfmont(yorder.getZfmont() + yorderlist.getAmnt() - yorderlist.getYhamnt());
            }
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                yorder.setIsi(totalmonthjf);
                morder.setIsi(totalmonthjf);
                yorder.setIsh(totalyearjf);
                morder.setIsh(totalyearjf);
            }
            if (ortp.equals("02")) {// 退订
                morder.setDdzt("06");// 订单状态 06 退订已退款
                morder.setTpmont(Math.abs(totalmont));
                morder.setNotef("00");
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setTpsx(Math.abs(sxf));
                yorder.setNotef("00");
                yorder.setTpmont(Math.abs(totalmont));
                yorder.setDdzt("06");// 订单状态 06 退订已退款
                yorder.setTpdate(morder.getTpdate());
                yorder.setTpsx(Math.abs(sxf));
            } else if (ortp.equals("03")) {// 增量
                morder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                morder.setTpsx(0.0);
                morder.setTpmont(totalmont);
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setNotef("00");
                yorder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                yorder.setTpsx(0.0);
                yorder.setTpmont(totalmont);
                yorder.setTpdate(morder.getTpdate());
            }
            morder.setYhamnt(yorder.getYhamnt());
            morder.setZfmont(yorder.getZfmont());
            morder.setMont(yorder.getMont());
            ticketDao.update(morder);
            ticketDao.update(yorder);
            boolean b = true;
            boolean hqyt = CommonUtil.isHqyt();
            if (ortp.equals("02")) {
                // 计算预付款,退订手续费tdsx一般都是负数
                if(hqyt){
                    if(totalmont > 0){
                        throw new RuntimeException("修改后订单金额不可大于原订单金额");
                    }
                }else{
                    if (totalmont > 0) {
                        b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), Math.abs(sxf), "03");
                    } else {
                        b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), Math.abs(sxf), ortp);
                    }
                }
            } else {
                if(hqyt){
                    throw new RuntimeException("修改订单不可出现增量订单");
                }else{
                    // 增量订单手续费传0过去
                    if (totalmont > 0) {
                        b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), 0, ortp);
                    } else {
                        b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), 0, "02");
                    }
                }

            }
            if (!b) {
                System.out.println("预付款金额异常");
                throw new RuntimeException("预付款金额异常");
            }
            if (morder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                Usernumjflist usernumjifenlist = new Usernumjflist();
                usernumjifenlist.setId(new UsernumjflistId(morder.getOrid(), oldmorder.getZfusid()));
                usernumjifenlist.setNid(BigDecimal.valueOf(set.getNid()));
                usernumjifenlist.setJftp("03");//月
                usernumjifenlist.setEtdt(Tools.getDays());
                usernumjifenlist.setStdt(Tools.getDays());
                usernumjifenlist.setStdt2(Tools.getDays());
                usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
                usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
                usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
                if (ortp.equals("02")) {
                    usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
                } else {
                    usernumjifenlist.setJflb(BigDecimal.valueOf(1));
                }
                usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(totalmonthjf)));
                usernumjifenlist.setStdt(Tools.getDays());
                usernumjifenlist.setZusid(morder.getZfusid());
                usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
                usernumjifenlist.setZusid(morder.getZfusid());
                this.save(usernumjifenlist);
                Usernumjf userjf = null;
                if (usernumjifenlist.getJftp().equals("03")) {//月
//                    userjf = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), morder.getZfusid(), new Long(1), Long.parseLong(iscenicid));
                    
                	String userjfJSON = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), morder.getZfusid(), new Long(1), Long.parseLong(iscenicid));
                    userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
                    
                    
                } else {
//                    userjf = ticketService.getJifenByUser(map.get("startDate").toString().substring(0, 4) + "-01-01", map.get("endDate").toString().substring(0, 4) + "-12-31", morder.getZfusid(), new Long(2), Long.parseLong(iscenicid));
                
                	String userjfJSON = ticketService.getJifenByUser(map.get("startDate").toString().substring(0, 4) + "-01-01", map.get("endDate").toString().substring(0, 4) + "-12-31", morder.getZfusid(), new Long(2), Long.parseLong(iscenicid));
                    userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
                
                }
                if (usernumjifenlist.getJftp().equals("03")) {//月
                    userjf.setYpoint(userjf.getYpoint() + totalmonthjf);
                } else {
                    userjf.setYpoint(userjf.getYpoint() + totalyearjf);
                }
                this.update(userjf);
            }
        }
        return totaltdsx;
    }

    public List mergeRaft(List charaftlist, List newraft) throws IllegalAccessException, InvocationTargetException {
        if (newraft != null && newraft.size() > 0 && charaftlist != null && charaftlist.size() > 0) {
            for (int i = 0; i < newraft.size(); i++) {
                RaftComparepojo c1 = (RaftComparepojo) newraft.get(i);
                for (int j = 0; j < charaftlist.size(); j++) {// 以这个循环为对象比较外围对象
                    // 缺少的添加进去
                    RaftComparepojo c2 = (RaftComparepojo) charaftlist.get(j);
                    if (c2.getIticketid().intValue() == c1.getIticketid().intValue() && c2.getTourdate().equals(c1.getTourdate()) && c2.getTripid().intValue() == c1.getTripid().intValue()) {
                        c2.setNumb(c2.getNumb() + c1.getNumb());
                        break;
                    } else {
                        if (j == charaftlist.size() - 1) {
                            RaftComparepojo addcharraft = new RaftComparepojo();
                            BeanUtils.copyProperties(addcharraft, c1);
                            charaftlist.add(addcharraft);
                            break;
                        }
                    }
                }
            }
        } else {
            if (charaftlist == null || charaftlist.size() < 1) {
                if (newraft != null) {
                    charaftlist.addAll(newraft);
                }
            }
        }
        return charaftlist;
    }

    // 计算信用积分(订票用)
    private Long calculateCredit(String usid, List charraftlist, String iscenicid) {
        //  先查询出信用度，再计算出退票需要
        RaftComparepojo c1 = null;
        RaftComparepojo c2 = null;
        Prdtripvenuemanage prd = null;
        Long editnum = 0l;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                if (c1.getNumb() > 0) {
                    editnum += prd.getIshot() * c1.getNumb();
                } else {
                    editnum += prd.getIshot() * c1.getNumb();
                }
            }
        }
        return editnum;
    }

    private boolean queryPositiveraft(List charraftlist, String iscenicid) {
        //  先查询出信用度，再计算出退票需要
        RaftComparepojo c1 = null;
        RaftComparepojo c2 = null;
        Prdtripvenuemanage prd = null;
        boolean positive = false;// 是否大于0
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                if (prd.getIshot() > 0) {
                    positive = true;
                }
            }
        }
        return positive;
    }

    // 减掉(或增加)竹筏量
    private boolean minusRaftCount(List charraftlist, String iscenicid) {
        Productcontrol control = null;
        RaftComparepojo c1 = null;
        Prdtripvenuemanage prd = null;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                control = ticketDao.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());
                control.setSoldnumber(control.getSoldnumber() + c1.getNumb());
                ticketDao.update(control);
            }
        }
        return true;
    }

    // 减掉(或增加)竹筏量
    private boolean minusDayCount(List newdaylist, List olddaylist, String iscenicid) throws IllegalAccessException, InvocationTargetException {
        RaftComparepojo checkpojo1 = null;
        OrderPojo checkpojo = null;
        Productcontrol control = null;
        if (newdaylist != null && newdaylist.size() > 0) {
            for (int i = 0; i < newdaylist.size(); i++) {
                checkpojo = (OrderPojo) newdaylist.get(i);
                if (olddaylist == null || olddaylist.size() < 1) {
                    checkpojo1 = new RaftComparepojo();
                    control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
                    checkpojo1.setIvenueid(control.getIvenueid());
                    checkpojo1.setIvenueareaid(control.getIvenueareaid());
                    checkpojo1.setTripid(control.getTripid());
                    checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
                    checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
                    // checkpojo1.setErrtp(errtp);
                    checkpojo1.setTourdate(checkpojo.getTourdate());
                    olddaylist.add(checkpojo1);
                } else {
                    for (int j = 0; j < olddaylist.size(); j++) {
                        checkpojo1 = (RaftComparepojo) olddaylist.get(j);
                        if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid()) && checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
                            checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
                            break;
                        } else {
                            if (i == newdaylist.size() - 1) {
                                checkpojo1 = new RaftComparepojo();
                                control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
                                checkpojo1.setTourdate(checkpojo.getTourdate());
                                checkpojo1.setIvenueid(control.getIvenueid());
                                checkpojo1.setIvenueareaid(control.getIvenueareaid());
                                checkpojo1.setTripid(control.getTripid());
                                checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
                                checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
                                olddaylist.add(checkpojo1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (olddaylist != null && olddaylist.size() > 0) {
            control = null;
            List list = null;
            Edmtickettypetab checkticket = null;
            Trip checktrip = null;
            for (int i = 0; i < olddaylist.size(); i++) {
                checkpojo1 = (RaftComparepojo) olddaylist.get(i);
                list = ticketDao.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
                if (list != null && list.size() > 0) {
                    control = new Productcontrol();
                    BeanUtils.populate(control, (Map) list.get(0));
                    control = (Productcontrol) ticketDao.get(Productcontrol.class, control.getProductcontrolid());
                    control.setSoldnumber(control.getSoldnumber() + checkpojo1.getNumb());
                    ticketDao.update(control);
                }
            }
        }
        return true;
    }

    /**
     * 李进修改 2016-01-06
     *
     * @param currentusid 当前用户
     * @param zfusid      支付用户
     * @param orid        订单号
     * @param mont        订单金额
     * @param tdsx        退订手续费用
     * @param type        类别
     * @return
     */
    private synchronized boolean minusUserYfk(String currentusid, String zfusid, String orid, double mont, double tdsx, String type) {
        // 用户预付款 先增加全退订金额
        //李进修改，检查预付款总表与明细表
        //构造 预付款检查方法
        UserYfkCheck userYfkCheck = new UserYfkCheck(this);
        int currentyfkzfseq = 0;
        try {
            if (userYfkCheck.yfk_blance_bj(zfusid) != 1) {
                Debug.print("ZhiFuDao yfkzf： 比交用户余额和明细合计出现异常！");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }


        Useryfk yfk = new Useryfk();
        Integer seqs = this.sysparService.getMaxSeq("Useryfk", "seq");
        yfk.setUsid(zfusid);
        yfk.setBdate(Tools.getTodayString());
        yfk.setSzmemo("用户  [" + currentusid + "]修改订单");
        yfk.setOrderid(orid);
        yfk.setPoint(mont);
        if (type.equals("03")) {
            yfk.setYfklb(-1);
            yfk.setYfksc("03"); // 消费预付款
            yfk.setNote("订单消费");
        } else {
            yfk.setYfklb(1);// 退订转预付款
            yfk.setYfksc("02");
            yfk.setNote("退订转预付款");
        }
        yfk.setCztp(0);
        yfk.setSeq(seqs + 1);
        balanceCenterService.useryfksave(yfk);

        // 平台预付款 先从平台预付款转出
        List sysList = morderdao.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;

        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setSeq(sysparService.getMaxSeq("Useryfk", "seq") + 1);
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(mont);
        if (type.equals("03")) {
            ptyfk.setYfklb(1);
            ptyfk.setYfksc("14"); // 用户消费预付款 那平台就是用户预付款转入
            ptyfk.setNote("用户订单消费");
        } else {
            ptyfk.setYfklb(-1);// 预付款转出
            ptyfk.setYfksc("13");
            ptyfk.setNote("用户退订退款");
        }
        ptyfk.setCztp(0);
        balanceCenterService.useryfksave(ptyfk);

        if (tdsx > 0) {
            // 然后收取用户手续费
            Useryfk yfk1 = new Useryfk();
            Integer seqs1 = this.sysparService.getMaxSeq("Useryfk", "seq");
            yfk1.setUsid(zfusid);
            yfk1.setBdate(Tools.getTodayString());
            yfk1.setSzmemo("用户  [" + currentusid + "]修改订单");
            yfk1.setOrderid(orid);
            yfk1.setPoint(tdsx);
            yfk1.setYfklb(-1);
            yfk1.setYfksc("17"); // 消费预付款
            yfk1.setNote("退订收取手续费");
            yfk1.setCztp(0);
            yfk1.setSeq(seqs1 + 1);
            balanceCenterService.useryfksave(yfk1);

            // 平台用户收入手续
            Useryfk ptyfk1 = new Useryfk();
            ptyfk1.setSeq(sysparService.getMaxSeq("Useryfk", "seq") + 1);
            ptyfk1.setUsid(sys.getPmva());// 用户
            ptyfk1.setBdate(Tools.getTodayString());
            ptyfk1.setOrderid(orid);
            ptyfk1.setPoint(tdsx);
            ptyfk1.setYfklb(1);
            ptyfk1.setYfksc("16"); // 用户消费预付款 那平台就是用户预付款转入
            ptyfk1.setNote("退订收入手续费");
            ptyfk1.setCztp(0);
            balanceCenterService.useryfksave(ptyfk1);
        }

        try {
            // yfk //查出当前的SEQ 编号
            BigDecimal a = new BigDecimal(yfk.getPoint());
            double af = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            List list = this.find(" from Useryfk u where u.orderid  = '" + yfk.getOrderid() + "' and u.usid='" + yfk.getUsid() + "' and u.yfklb =" + yfk.getYfklb() + " and u.yfksc='" + yfk.getYfksc() + " ' and u.point=" + af);
            if (list == null || list.size() == 0) {
                throw new RuntimeException("预付款扣减失败。");
            } else {
                Useryfk useryfk = (Useryfk) list.get(0);
                currentyfkzfseq = useryfk.getSeq();

            }

            //找出 当前预付对应的SEQ

            if (userYfkCheck.ValideYfkPay(orid, mont, 0) < 1) {
                System.out.println("ZhiFuDao yfkzf： 比交用户余额和明细合计出现异常！");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }


        return true;
    }

    // 计算退订手续费
    private double calculatetdmont(List oldtdlist, String usid, String iscenicid) throws ParseException {
        Custom custom = (Custom) morderdao.get(Custom.class, usid);
        double tdmont = 0.0;

        // 计算出退订的手续费
        for (int i = 0; i < oldtdlist.size(); i++) {
            Changebackrate fl = null;
            Prdtripvenuemanage triptime = null;
            RaftComparepojo compare = (RaftComparepojo) oldtdlist.get(i);
            int cnum = 0;
            if (compare.getYhnum() < Math.abs(compare.getNumb().intValue())) {
                cnum = Math.abs(compare.getNumb().intValue()) - compare.getYhnum();
            }
            Productcontrol control = ticketDao.getTripControl(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare.getTourdate());
            if (control == null || control.getBystate().intValue() == 1) {// 没有竹筏控制或者竹筏状态正常时
                Ticketxgz tdrule = morderdao.ticketbackrule(compare.getIticketid(), Long.parseLong(iscenicid), custom.getLgtp());
                double tdfl = 0.0;
                if (tdrule != null) {// 如果退订规则不为空
                    if (tdrule.getJsfs().equals("0001")) {// 按小时
                        triptime = ticketDao.getTripInfo(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare.getTourdate(), iscenicid, compare.getIticketid().toString());
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
                            long diff = d1.getTime() - d2.getTime();
                            long hours = diff / (1000 * 60 * 60);
                            fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
                            if (fl == null) {
                                tdfl = 0.0;
                            } else {
                                tdfl = fl.getTdfl();
                            }
                        }
                    } else if (tdrule.getJsfs().equals("0002")) {// 按天
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
                            long diff = d1.getTime() - d2.getTime();
                            long days = diff / (1000 * 60 * 60 * 24);
                            fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
                            if (fl == null) {
                                tdfl = 0.0;
                            } else {
                                tdfl = fl.getTdfl();
                            }
                        }
                    } else {// 常年
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
                            fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
                            tdfl = fl.getTdfl();
                        }
                    }
                    tdmont += compare.getPrice() * tdfl * cnum;
                }
            }
        }
        return tdmont;
    }

    public double gettdfl(YZorderlist zorderlist, String usid) throws ParseException {
        Productcontrol control = null;
        Ticketxgz tdrule = null;
        double tdfl = 0.0;
        Custom custom = (Custom) morderdao.get(Custom.class, usid);
        Changebackrate fl = null;
        double tdmont = 0.0;
        control = ticketDao.getTripControl(zorderlist.getTripid(), zorderlist.getIvenueid(), zorderlist.getIvenueareaid(), zorderlist.getDtstartdate().substring(0, 10));
        if (control == null || control.getBystate().intValue() == 1) {
            tdrule = morderdao.ticketbackrule(zorderlist.getIztickettypeid(), zorderlist.getId().getIscenicid(), custom.getLgtp());
            if (tdrule != null) {// 如果退订规则不为空
                if (tdrule.getJsfs().equals("0001")) {// 按小时
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long hours = diff / (1000 * 60 * 60);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
                        if (fl == null) {
                            tdfl = 0.0;
                        } else {
                            tdfl = fl.getTdfl();
                        }
                    }
                } else if (tdrule.getJsfs().equals("0002")) {// 按天
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
                        tdfl = fl.getTdfl();
                    }
                } else {// 常年
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
                        tdfl = fl.getTdfl();
                    }
                }
            }
        }
        return tdfl;
    }

    public double gettdsx(YZorderlist zorderlist, String usid) throws ParseException {
        Productcontrol control = null;
        Ticketxgz tdrule = null;
        Custom custom = (Custom) morderdao.get(Custom.class, usid);
        Changebackrate fl = null;
        double tdmont = 0.0;
        control = ticketDao.getTripControl(zorderlist.getTripid(), zorderlist.getIvenueid(), zorderlist.getIvenueareaid(), zorderlist.getDtstartdate().substring(0, 10));
        if (control == null || control.getBystate().intValue() == 1) {
            tdrule = morderdao.ticketbackrule(zorderlist.getIztickettypeid(), zorderlist.getId().getIscenicid(), custom.getLgtp());
            if (tdrule != null) {// 如果退订规则不为空
                double tdfl = 0.0;
                if (tdrule.getJsfs().equals("0001")) {// 按小时
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long hours = diff / (1000 * 60 * 60);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
                        if (fl == null) {
                            tdfl = 0.0;
                        } else {
                            tdfl = fl.getTdfl();
                        }
                    }
                } else if (tdrule.getJsfs().equals("0002")) {// 按天
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
                        tdfl = fl.getTdfl();
                    }
                } else {// 常年
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
                        tdfl = fl.getTdfl();
                    }
                }
                tdmont += zorderlist.getZpric() * tdfl * zorderlist.getZnumb();
            }
        }
        return tdmont;
    }

    /**
     * Describe:验证竹筏,返回竹筏不足的趟次
     *
     * @param newraftlist
     * @param charraft
     * @return return:List Date:2012-2-9
     * @auth:yangguang
     */
    private List validateRaft(List newraftlist, List charraft) {
        //  两个参数list进行合并
        List validatelist = null;
        RaftComparepojo checkpojo = null;
        if (newraftlist != null && newraftlist.size() > 0 && charraft != null && charraft.size() > 0) {
            List list1 = new ArrayList();
            List list2 = new ArrayList();
            RaftComparepojo c1 = null;
            RaftComparepojo c2 = null;
            if (newraftlist.size() >= charraft.size()) {
                list1.addAll(newraftlist);
                list2.addAll(charraft);
            } else {
                list1.addAll(charraft);
                list2.addAll(newraftlist);
            }
            for (int i = 0; i < list1.size(); i++) {
                c1 = (RaftComparepojo) list1.get(i);
                for (int j = 0; j < list2.size(); j++) {// 以这个循环为对象比较外围对象
                    // 缺少的添加进去
                    c2 = (RaftComparepojo) list2.get(j);
                    if (c2.compare(c1)) {
                        c2.setNumb(c2.getNumb() + c1.getNumb());
                    } else if (j == list2.size() - 1) {
                        list2.add(c1);
                        break;
                    }
                }
            }
            validatelist = list2;
        } else if (newraftlist == null || newraftlist.size() < 1) {
            validatelist = charraft;
        } else if (charraft == null || charraft.size() < 1) {
            validatelist = newraftlist;
        } else {
            return null;
        }
        Productcontrol control = null;
        Edmtickettypetab checkticket = null;
        Trip checktrip = null;
        for (int i = 0; i < validatelist.size(); i++) {
            checkpojo = (RaftComparepojo) validatelist.get(i);
            control = ticketDao.getTripControl(checkpojo.getTripid(), checkpojo.getIvenueid(), checkpojo.getIvenueareaid(), checkpojo.getTourdate());
            if (control == null || control.getBystate() == -1) {
                checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
                checktrip = (Trip) ticketDao.get(Trip.class, checkpojo.getTripid());
                checkpojo.setErrtp("0");// 停排
                checkpojo.setTicketname(checkticket.getSztickettypename());
                checkpojo.setTripname(checktrip.getTripname());
            } else {
                if (control.getSalablenumber() - control.getSoldnumber() >= checkpojo.getNumb()) {
                    validatelist.remove(checkpojo);
                    i = i - 1;
                } else {
                    checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
                    checktrip = (Trip) ticketDao.get(Trip.class, checkpojo.getTripid());
                    checkpojo.setErrtp("1");// 数量不足
                    checkpojo.setTicketname(checkticket.getSztickettypename());
                    checkpojo.setTripname(checktrip.getTripname());
                }
            }
        }
        return validatelist;
    }

    // 验证含有竹筏票的日控制
    public List validateDay(List newdaylist, List olddaylist) throws IllegalAccessException, InvocationTargetException {
        OrderPojo checkpojo = null;
        RaftComparepojo checkpojo1 = null;
        Productcontrol control = null;
        if (newdaylist != null && newdaylist.size() > 0) {
            for (int i = 0; i < newdaylist.size(); i++) {
                checkpojo = (OrderPojo) newdaylist.get(i);
                if (olddaylist == null || olddaylist.size() < 1) {
                    checkpojo1 = new RaftComparepojo();
                    control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
                    checkpojo1.setIvenueid(control.getIvenueid());
                    checkpojo1.setIvenueareaid(control.getIvenueareaid());
                    checkpojo1.setTripid(control.getTripid());
                    checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
                    checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
                    // checkpojo1.setErrtp(errtp);
                    checkpojo1.setTourdate(checkpojo.getTourdate());
                    olddaylist.add(checkpojo1);
                } else {
                    for (int j = 0; j < olddaylist.size(); j++) {
                        checkpojo1 = (RaftComparepojo) olddaylist.get(j);
                        if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid()) && checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
                            checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
                        } else {
                            if (i == newdaylist.size() - 1) {
                                checkpojo1 = new RaftComparepojo();
                                control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
                                checkpojo1.setTourdate(checkpojo.getTourdate());
                                checkpojo1.setIvenueid(control.getIvenueid());
                                checkpojo1.setIvenueareaid(control.getIvenueareaid());
                                checkpojo1.setTripid(control.getTripid());
                                checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
                                checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
                                // checkpojo1.setErrtp(errtp);
                                olddaylist.add(checkpojo1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (olddaylist != null && olddaylist.size() > 0) {
            control = null;
            List list = null;
            Edmtickettypetab checkticket = null;
            Trip checktrip = null;
            for (int i = 0; i < olddaylist.size(); i++) {
                checkpojo1 = (RaftComparepojo) olddaylist.get(i);
                list = ticketDao.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
                if (list != null && list.size() > 0) {
                    control = new Productcontrol();
                    BeanUtils.populate(control, (Map) list.get(0));
                    if (control.getSalablenumber() - control.getSoldnumber() > checkpojo1.getNumb()) {
                        olddaylist.remove(checkpojo1);
                        i = i - 1;
                    } else {
                        checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
                        checktrip = (Trip) ticketDao.get(Trip.class, checkpojo1.getTripid());
                        checkpojo1.setErrtp("2");// 已售完
                        checkpojo1.setTicketname(checkticket.getSztickettypename());
                        checkpojo1.setTripname(checktrip.getTripname());
                    }
                } else {
                    checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
                    checktrip = (Trip) ticketDao.get(Trip.class, checkpojo1.getTripid());
                    checkpojo1.setErrtp("3");// 暂时不可销售
                    checkpojo1.setTicketname(checkticket.getSztickettypename());
                    if (checktrip != null) {
                        checkpojo1.setTripname(checktrip.getTripname());
                    }
                }
            }
        }

        return olddaylist;
    }

    public List mergenewInfo(List newInfo) throws IllegalAccessException, InvocationTargetException {
        List mergelist = new ArrayList();
        for (int i = 0; i < newInfo.size(); i++) {
            OrderPojo pojo = (OrderPojo) newInfo.get(i);
            if (mergelist.size() < 1) {// 首次进入 加入第一条
                OrderPojo pojo1 = new OrderPojo();
                BeanUtils.copyProperties(pojo1, pojo);
                mergelist.add(pojo1);
            } else {
                for (int j = 0; j < mergelist.size(); j++) {// 比较
                    OrderPojo pojo1 = (OrderPojo) mergelist.get(j);
                    if (pojo.getProductcontrolid() != null && !pojo.getProductcontrolid().equals("")) {
                        if (pojo.getItickettypeid().equals(pojo1.getItickettypeid()) && pojo.getProductcontrolid().equals(pojo1.getProductcontrolid())
                                && pojo.getTourdate().equals(pojo1.getTourdate()) && pojo.getIcrowdkindid().equals(pojo1.getIcrowdkindid())) {// 产品、日期、趟次相同则合并
                            // 这里是含有竹筏趟次时间的
                            pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
                            break;
                        } else {
                            if (j == mergelist.size() - 1) {// 最后一个加入
                                pojo1 = new OrderPojo();
                                BeanUtils.copyProperties(pojo1, pojo);
                                mergelist.add(pojo1);
                                break;
                            }
                        }
                    } else {// 无竹筏的在这里处理 只判断产品和日期
                        if (pojo.getItickettypeid().equals(pojo1.getItickettypeid()) && pojo.getIcrowdkindid().equals(pojo1.getIcrowdkindid())) {
                            pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
                            break;
                        } else {
                            if (j == mergelist.size() - 1) {// 最后一个加入
                                OrderPojo pojonew = new OrderPojo();
                                BeanUtils.copyProperties(pojonew, pojo);
                                mergelist.add(pojonew);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return mergelist;
    }

    public Map backOrder(String[] orids, String oldorid, String iscenicid, String stdt, String ibussiness, String usid) {
        Map map = null;
        try {
            List<TOrderlist> torderlists = getTOrderlists(oldorid, Long.parseLong(iscenicid));
            map = this.editOrderCenter(torderlists, null, orids, oldorid, iscenicid, stdt, ibussiness, usid);
            if (map != null && map.size() > 0) {
                if ((Boolean) map.get("result")) {
                    MOrder morder = morderdao.getMOrder(oldorid);
                    if (morder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                        backOrderJf(oldorid, usid, Integer.parseInt(stdt.substring(0, 4)), Integer.parseInt(stdt.substring(5, 7)), orids[1], Long.parseLong(iscenicid));
                    }
                    updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "27");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("=========>>>>退订订单失败,订单号:" + oldorid + "======>>message:" + e.getMessage());
        }
        return map;
    }

    /**
     * Describe:根据订单号，服务商编号获取订单产品列表
     *
     * @param orid
     * @param iscenicid
     * @return return:List<TOrderlist> Date:2012-3-31
     * @throws InvocationTargetException
     * @throws IllegalAccessExceptio     throws IllegalAccessException, InvocationTargetExceptionn
     * @auth:yangguang
     */
    public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) throws IllegalAccessException, InvocationTargetException {
        List list = torderlistdao.getTOrderListList(orid, iscenicid.toString());
        TOrderlist torderlist = null;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                torderlist = new TOrderlist();
                BeanUtils.populate(torderlist, (Map) list.get(i));
                torderlist.setNumb(0l);
                list.set(i, torderlist);
            }
        }
        return list;
    }

    /**
     * *
     * Describe:根据订单号，服务商编号获取订单产品列表
     *
     * @param orid
     * @return
     * @author lijingrui
     * Date:2013-9-17
     * @see com.ectrip.order.service.iservice.IOrderService#getTOrderlists(java.lang.String)
     */
    public List<TOrderlist> getTOrderlists(String orid) {
        return torderlistdao.getTOrderlists(orid);
    }

    public List daoyoulist(String usid) {
        return morderdao.daoyoulist(usid);
    }

    public PaginationSupport queryAllMsOrder(String usid, QueryOrder order, int page, int pageSize, String url) {
        return morderdao.queryAllMsOrder(usid, order, page, pageSize, url);
    }

	public PaginationSupport queryAllMsOrderByGroupId(String usid, QueryOrder order, int page, int pageSize, String url,
			String groupId) {
		// TODO Auto-generated method stub
		return morderdao.queryAllMsOrderByGroupId(usid, order, page, pageSize, url, groupId);
	}

    /**
     * Describe:验证是否可以修改首次游览日期
     *
     * @param orid
     * @param stdt
     * @return return:boolean Date:2012-4-7
     * @auth:yangguang
     */
    public boolean iscanedit(String orid, String stdt, String iscenicid, String ibussiness) {
        Integer validdays = torderlistdao.getMaxCanEditDate(orid);
        if (validdays == 0) {
            return false;
        } else {
            boolean schemaedit = torderlistdao.validationEditScheme(orid, stdt, iscenicid, ibussiness);
            boolean canedit = torderlistdao.validationEditDate(orid, stdt, Tools.getDate(stdt, validdays - 1), iscenicid);
            boolean sameprice = torderlistdao.validationEditDatePrice(orid, stdt, iscenicid, ibussiness);
            if (canedit && sameprice && schemaedit) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void updateProductDate(String orid, String stdt, String iscenicid) {
        List torders = torderdao.getTOrderList(orid);
        String[] dateArr = new String[torders.size()];
        for (int i = 0; i < torders.size(); i++) {
            Map map = (Map) torders.get(i);
            dateArr[i] = map.get("dtstartdate").toString();
        }
        dateArr = getStartAndEndDate(dateArr);
        MOrder morder = (MOrder) torderlistdao.get(MOrder.class, orid);
        morder.setStdt(stdt);
        torderlistdao.update(morder);
        TOrder torder = (TOrder) torderlistdao.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
        torder.setDtstartdate(stdt);
        torder.setDtenddate(stdt);
        torder.setIsa(torder.getIsa() + 1L);
        torderlistdao.update(torder);
        //获取所有排除竹筏的torderlist和tzorderlist
        List torderlists = torderlistdao.getTOrderlists(orid, Long.parseLong(iscenicid));
        List tzorderlists = null;
        TOrderlist torderlist = null;
        TZorderlist zorderlist = null;
        Edmtickettypetab ticket = null;
        Object[] obj = null;
        if (torderlists != null) {
            List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
            String stockUsid = morder.getUsid();
            Custom custom = (Custom) torderdao.get(Custom.class, stockUsid);
            if (custom.getIbusinessid().longValue() == 2L && custom.getUstp().equals("02")
                    && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getSusid();
            } else if (custom.getIbusinessid().longValue() == 3L && custom.getUstp().equals("02")) {
                stockUsid = custom.getSusid();
            }
            for (int i = 0; i < torderlists.size(); i++) {
                torderlist = (TOrderlist) torderlists.get(i);
                ticket = (Edmtickettypetab) get(Edmtickettypetab.class, torderlist.getItickettypeid());
                torderlist.setDtstartdate(stdt);
                torderlist.setDtenddate(Tools.getDate(stdt, Integer.parseInt(ticket.getValidityday().toString()) - 1));
                tzorderlists = torderlistdao.getTZorderlists(orid, torderlist.getId());
                torderdao.update(torderlist);
                for (int j = 0; j < tzorderlists.size(); j++) {
                    obj = (Object[]) tzorderlists.get(j);
                    zorderlist = (TZorderlist) obj[0];
                    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, zorderlist.getIztickettypeid());
                    zorderlist.setDtstartdate(stdt + " " + "00:00:00");
                    zorderlist.setDtenddate(Tools.getDate(stdt, Integer.parseInt(ticket.getValidityday().toString()) - 1) + " " + "23:59:59");
                    torderlistdao.update(zorderlist);
                }
                StockOrderInfo stockOrderInfo = new StockOrderInfo();
                stockOrderInfo.setOrid(torderlist.getId().getOrid());
                stockOrderInfo.setProviderId(torderlist.getId().getIscenicid());
                stockOrderInfo.setProductId(torderlist.getItickettypeid());
                stockOrderInfo.setPriceId(torderlist.getIcrowdkindpriceid());
                stockOrderInfo.setUsid(stockUsid);
                stockOrderInfo.setStockDate(torderlist.getDtstartdate());
                stockOrderInfo.setNumb(torderlist.getNumb());
                stocks.add(stockOrderInfo);
            }
            String message = ticketService.checkCustomStock(JSON.toJSONString(stocks));
            if (StringUtils.isBlank(message)) {
                message = ticketService.checkStock(JSON.toJSONString(stocks));
            }
            if (!StringUtils.isBlank(message)) {
                throw new RuntimeException(message);
            }
            ticketService.deleteStockDetails(orid, Long.parseLong(iscenicid), null, null);
            try {
//                ticketService.saveStockDetails(stocks, false);
            	ticketService.saveStockDetails(JSON.toJSONString(stocks), true);
            } catch (Exception e) {
                throw new RuntimeException("更新库存失败，无法修改浏览日期");
            }
        }

    }

    /**
     * Describe:散客全单退订保存
     *
     * @param orderlistInfo
     * @param neworderlist
     * @param orids
     * @param oldorid
     * @param iscenicid
     * @param stdt
     * @param ibusiness
     * @param usid
     * @return
     * @throws Exception return:Map Date:May 3, 2012
     * @auth:lijingrui
     */
    public Map editOrderSankeCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid)
            throws Exception {
        boolean hqyt = CommonUtil.isHqyt();
        Map returnmap = new HashMap();
        Map newmap = null;
        try {
            MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfoSanke(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrderSanke(oldorid, iscenicid, orderlistInfo);
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            RaftComparepojo compare1 = null;
            RaftComparepojo compare = null;
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && oldaddlist != null) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare1.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                                break;
                            }
                        }
                    }
                }
            }
            // 原订单修改后的差异集合
            List chalist = (List) oldmap.get("chaticket");
            List addlist = new ArrayList();
            List backlist = new ArrayList();
            TOrderlist orderlist = null;
            OrderPojo newTicket = null;
            for (int i = 0; i < chalist.size(); i++) {
                orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }
            List sonticket = null;
            Edmcrowdkindpricetab kindprice = null;
            Edmtickettypetab ticket = null;
            Edmtickettypetab zticket = null;
            List pricelist = null;
            List<TZorderlist> tzlist = null;
            Edmticketcomposepricetab pricepost = null;
            TOrderlist newproduct = null;
            TOrderlist oldtorderlist = null;
            TZorderlist tzorderlist = null;
            Productcontrol tripinfo = null;
            boolean isadd = false;
            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    for (int j = 0; j < addlist.size(); j++) {
                        orderlist = (TOrderlist) addlist.get(j);
                        // 如果产品相同
                        if (orderlist.getItickettypeid().intValue() == Integer.parseInt(newTicket.getItickettypeid())) {
                            tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                            // 且newTicket有浏览日期(有浏览日期说明票含竹筏)
                            if (newTicket.getTourdate() != null) {
                                // 且浏览日期相同
                                tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                // 拿出原订单的对应产品
                                oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid)));
                                //比较子 票的趟次编号和新增票的趟次编号
                                for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
                                    if (zlist.getTripid() != null && zlist.getTripid() != 0 && tripinfo.getTripid().equals(zlist.getTripid())
                                            && orderlist.getIcrowdkindid().equals(newTicket.getIcrowdkindid()) && tripinfo.getStdata().equals(newTicket.getTourdate())) {
                                        orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                        isadd = true;
                                    }
                                }
                            } else {
                                orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                isadd = true;
                            }
                            if (orderlist.getZorderlist() == null || orderlist.getZorderlist().size() < 1) {
                                orderlist.setZorderlist(tzlist);
                            } else {
                                for (TZorderlist zlist : orderlist.getZorderlist()) {// 循环有差异的子票列表
                                    zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / orderlist.getNumb() * Integer.parseInt(newTicket.getNumb()));
                                    zlist.setZamnt(zlist.getZpric() * zlist.getZnumb());
                                }
                            }
                        }
                        if (!isadd && j == addlist.size() - 1) {
                            newproduct = new TOrderlist();
                            TOrderlist comp = null;
                            pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                            kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                            ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                            if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                            } else {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                            }
                            if (comp != null) {
                                BeanUtils.copyProperties(newproduct, comp);
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                                addlist.add(newproduct);
                                break;
                            } else {
                                newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                                pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                                kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                                newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                newproduct.setDtstartdate(stdt);
                                ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                                newproduct.setPric(kindprice.getMactualsaleprice());
                                newproduct.setJsprice(kindprice.getJsprice());
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setYhnumb(0l);
                                newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                                newproduct.setYhamnt(0.0);
                                sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                                newproduct.setZorderlist(new ArrayList<TZorderlist>());
                                for (int y = 0; y < sonticket.size(); y++) {
                                    pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                                    tzorderlist = new TZorderlist();
                                    tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                                    tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                    tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                                    tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                                    zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                                    if (zticket.getBycategorytype().equals("0003")) {
                                        Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                        Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid,
                                                zticket.getItickettypeid().toString());
                                        tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                        tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                        tzorderlist.setTripid(control.getTripid());
                                        tzorderlist.setIvenueid(prd.getIvenueid());
                                        tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                                    } else {
                                        tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                        tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                                    }
                                    tzorderlist.setZpric(pricepost.getMactualsaleprice());
                                    tzorderlist.setJsprice(pricepost.getJsprice());
                                    tzorderlist.setZnumb(pricepost.getNumb());
                                    tzorderlist.setZyhnumb(0l);
                                    tzorderlist.setZyhamnt(0.0);
                                    tzorderlist.setZamnt(0.0);
                                    newproduct.getZorderlist().add(tzorderlist);
                                }
                                addlist.add(newproduct);
                                break;
                            }
                        }
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    orderlist = (TOrderlist) addlist.get(j);
                    tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    orderlist.setZorderlist(tzlist);
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                TOrderlist comp = null;
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    newproduct = new TOrderlist();
                    pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                    kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                    } else {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                    }
                    if (comp != null) {
                        BeanUtils.copyProperties(newproduct, comp);
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                        addlist.add(newproduct);
                    } else {
                        newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                        newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        newproduct.setDtstartdate(stdt);
                        ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                        newproduct.setPric(kindprice.getMactualsaleprice());
                        newproduct.setJsprice(kindprice.getJsprice());
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setYhnumb(0l);
                        newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                        newproduct.setYhamnt(0.0);
                        sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                        newproduct.setZorderlist(new ArrayList<TZorderlist>());
                        for (int y = 0; y < sonticket.size(); y++) {
                            pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                            tzorderlist = new TZorderlist();
                            tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                            tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                            tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                            tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                            zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                            if (zticket.getBycategorytype().equals("0003")) {
                                Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                        .getItickettypeid().toString());
                                tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                tzorderlist.setTripid(control.getTripid());
                                tzorderlist.setIvenueid(prd.getIvenueid());
                                tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                            } else {
                                tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                            }
                            tzorderlist.setZpric(pricepost.getMactualsaleprice());
                            tzorderlist.setJsprice(pricepost.getJsprice());
                            tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
                            tzorderlist.setZyhnumb(0l);
                            tzorderlist.setZyhamnt(0.0);
                            tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
                            newproduct.getZorderlist().add(tzorderlist);
                        }
                        addlist.add(newproduct);
                    }
                }
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            //  全部通过的话,再修改订单,否则返回错误信息
            //  增量 退订 减少或者增加预付款 计算信用度
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
            }
            // 计算出需要增加的金额
            double totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid);
            // 需要退订的钱
            double tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid);

            double userMoney = CommonUtil.getUserMoney(oldmorder.getZfusid());

            if(!hqyt) {
                // 比较两个map中的值，计算是否可以修改 不可修改返回false
                if (userMoney < Math.abs(tdsx) + totalmont + tdmont) {
                    returnmap.put("result", false);
                    returnmap.put("errtp", "2");// 余额不足
                    return returnmap;
                }
            }
            List charraftlist = null;
            List newdaylist = null;
            List olddaylist = null;
            int istoprat = 0;
            if (oldmap != null) {
                charraftlist = (List) oldmap.get("charraft");
                olddaylist = (List) oldmap.get("chaday");
            }
            List newraft = null;
            if (newmap != null) {
                newdaylist = (List) newmap.get("daylist");
                newraft = (List) newmap.get("raftlist");
            }
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);

            istoprat = checkStopRaft(charraftlist, iscenicid);

            // 新增订单
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null);
            // 退订单
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist);

            TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            YOrder oldyorder = (YOrder) yorderlistdao.get(YOrder.class, new YOrderId(oldtorder.getId().getOrid(), oldtorder.getId().getIscenicid()));
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldyorder.getTpmont() != null) {
                oldyorder.setTpmont(oldyorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldyorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldyorder.getTpsx() != null) {
                    oldyorder.setTpsx(oldyorder.getTpsx() + Math.abs(tdsx));
                } else {
                    oldyorder.setTpsx(Math.abs(tdsx));
                }
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            if (oldmorder.getTpmont() != null) {
                oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            oldmorder.setIsf(1l);
            oldyorder.setIsf(1l);
            oldtorder.setIsf(1l);
            yorderlistdao.update(oldyorder);
            yorderlistdao.update(oldtorder);
            yorderlistdao.update(oldmorder);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);
            this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "27");
            this.updateMOrderStatusByZeroProduct(oldorid, "27");
            //判断库存信息
            List<TOrderlist> newTorderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            Custom custom = (Custom) this.morderdao.get(Custom.class, stockUsid);
            if (custom.getIbusinessid() == 2L && custom.getUstp().equals("02") && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getUsid();
            }
            if (newTorderlists != null && !newTorderlists.isEmpty()) {//部分修改
                for (TOrderlist ntl : newTorderlists) {
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
//                ticketService.saveStockDetails(stockOrderInfos, true);
                ticketService.saveStockDetails(JSON.toJSONString(stockOrderInfos), true);
            } else {//全退订
                ticketService.deleteStockDetails(oldorid, Long.parseLong(iscenicid), null, null);
            }

            if(StringUtils.isBlank(oldmorder.getNoteh())){
                throw new RuntimeException("支付号不存在");
            }
            if(hqyt){
                MOrder morder = (MOrder) ticketDao.get(MOrder.class,orids[1]);
                HqytClient client = new HqytClient();
                RefundbillsRequest request = new RefundbillsRequest();
                request.setId(Long.parseLong(oldmorder.getNoteh()));
                request.setRefundMoney(Math.abs(tdmont)-Math.abs(tdsx));
                request.setRefundOrid(orids[1]);
                request.setReason(custom.getUsid()+"操作网上退订");
                String orderResource = morder.getOrdersource();
                if(orderResource!=null&&"lykgp".equals(orderResource)){
                    request.setMemo("0.0");
                }else{
                    request.setMemo("退款金额:"+Math.abs(totalmont)+";手续费:"+Math.abs(tdsx));
                }

                try{
                    JSONRefundBill refundBill = client.refundbills(request);
                    if(refundBill != null){
                        morder.setNoteh(refundBill.getInvoice().getId().toString());
                        morder.setNotec("2");//标识退款状态为退款中 0：退款失败 1：退款成功 2：退款中
                        morder.setDdzt("31");
                        ticketDao.update(morder);
                    }else{
                        throw new RuntimeException("退订失败:申请退订失败");
                    }
                }catch (Exception e){
                    throw new RuntimeException("退订失败:"+e.getMessage());
                }
            }
            if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
            	sysparService.sendMessageNew(oldtorder.getOrph(), "0004", oldtorder.getId().getOrid());
            }
        } catch (Exception e) {
            if (!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")) {
                throw new RuntimeException(e.getMessage());
            } else {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        return returnmap;

    }

    /**
     * Describe:旅游卡取消预定门票
     *
     * @param orderlistInfo
     * @param neworderlist
     * @param orids
     * @param oldorid
     * @param iscenicid
     * @param stdt
     * @param ibusiness
     * @param usid
     * @return
     * @throws Exception return:Map Date:May 3, 2012
     * @auth:lijingrui
     */
    public Map editOrderSankeCenterl(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid)
            throws Exception {
        boolean hqyt = CommonUtil.isHqyt();
        Map returnmap = new HashMap();
        Map newmap = null;
        try {
            MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfoSanke(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrderSanke(oldorid, iscenicid, orderlistInfo);
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            RaftComparepojo compare1 = null;
            RaftComparepojo compare = null;
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && oldaddlist != null) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare1.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                                break;
                            }
                        }
                    }
                }
            }
            // 原订单修改后的差异集合
            List chalist = (List) oldmap.get("chaticket");
            List addlist = new ArrayList();
            List backlist = new ArrayList();
            TOrderlist orderlist = null;
            OrderPojo newTicket = null;
            for (int i = 0; i < chalist.size(); i++) {
                orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }
            List sonticket = null;
            Edmcrowdkindpricetab kindprice = null;
            Edmtickettypetab ticket = null;
            Edmtickettypetab zticket = null;
            List pricelist = null;
            List<TZorderlist> tzlist = null;
            Edmticketcomposepricetab pricepost = null;
            TOrderlist newproduct = null;
            TOrderlist oldtorderlist = null;
            TZorderlist tzorderlist = null;
            Productcontrol tripinfo = null;
            boolean isadd = false;
            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    for (int j = 0; j < addlist.size(); j++) {
                        orderlist = (TOrderlist) addlist.get(j);
                        // 如果产品相同
                        if (orderlist.getItickettypeid().intValue() == Integer.parseInt(newTicket.getItickettypeid())) {
                            tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                            // 且newTicket有浏览日期(有浏览日期说明票含竹筏)
                            if (newTicket.getTourdate() != null) {
                                // 且浏览日期相同
                                tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                // 拿出原订单的对应产品
                                oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid)));
                                //比较子 票的趟次编号和新增票的趟次编号
                                for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
                                    if (zlist.getTripid() != null && zlist.getTripid() != 0 && tripinfo.getTripid().equals(zlist.getTripid())
                                            && orderlist.getIcrowdkindid().equals(newTicket.getIcrowdkindid()) && tripinfo.getStdata().equals(newTicket.getTourdate())) {
                                        orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                        isadd = true;
                                    }
                                }
                            } else {
                                orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                isadd = true;
                            }
                            if (orderlist.getZorderlist() == null || orderlist.getZorderlist().size() < 1) {
                                orderlist.setZorderlist(tzlist);
                            } else {
                                for (TZorderlist zlist : orderlist.getZorderlist()) {// 循环有差异的子票列表
                                    zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / orderlist.getNumb() * Integer.parseInt(newTicket.getNumb()));
                                    zlist.setZamnt(zlist.getZpric() * zlist.getZnumb());
                                }
                            }
                        }
                        if (!isadd && j == addlist.size() - 1) {
                            newproduct = new TOrderlist();
                            TOrderlist comp = null;
                            pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                            kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                            ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                            if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                            } else {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                            }
                            if (comp != null) {
                                BeanUtils.copyProperties(newproduct, comp);
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                                addlist.add(newproduct);
                                break;
                            } else {
                                newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                                pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                                kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                                newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                newproduct.setDtstartdate(stdt);
                                ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                                newproduct.setPric(kindprice.getMactualsaleprice());
                                newproduct.setJsprice(kindprice.getJsprice());
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setYhnumb(0l);
                                newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                                newproduct.setYhamnt(0.0);
                                sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                                newproduct.setZorderlist(new ArrayList<TZorderlist>());
                                for (int y = 0; y < sonticket.size(); y++) {
                                    pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                                    tzorderlist = new TZorderlist();
                                    tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                                    tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                    tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                                    tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                                    zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                                    if (zticket.getBycategorytype().equals("0003")) {
                                        Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                        Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid,
                                                zticket.getItickettypeid().toString());
                                        tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                        tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                        tzorderlist.setTripid(control.getTripid());
                                        tzorderlist.setIvenueid(prd.getIvenueid());
                                        tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                                    } else {
                                        tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                        tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                                    }
                                    tzorderlist.setZpric(pricepost.getMactualsaleprice());
                                    tzorderlist.setJsprice(pricepost.getJsprice());
                                    tzorderlist.setZnumb(pricepost.getNumb());
                                    tzorderlist.setZyhnumb(0l);
                                    tzorderlist.setZyhamnt(0.0);
                                    tzorderlist.setZamnt(0.0);
                                    newproduct.getZorderlist().add(tzorderlist);
                                }
                                addlist.add(newproduct);
                                break;
                            }
                        }
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    orderlist = (TOrderlist) addlist.get(j);
                    tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    orderlist.setZorderlist(tzlist);
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                TOrderlist comp = null;
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    newproduct = new TOrderlist();
                    pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                    kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                    } else {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                    }
                    if (comp != null) {
                        BeanUtils.copyProperties(newproduct, comp);
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                        addlist.add(newproduct);
                    } else {
                        newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                        newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        newproduct.setDtstartdate(stdt);
                        ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                        newproduct.setPric(kindprice.getMactualsaleprice());
                        newproduct.setJsprice(kindprice.getJsprice());
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setYhnumb(0l);
                        newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                        newproduct.setYhamnt(0.0);
                        sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                        newproduct.setZorderlist(new ArrayList<TZorderlist>());
                        for (int y = 0; y < sonticket.size(); y++) {
                            pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                            tzorderlist = new TZorderlist();
                            tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                            tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                            tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                            tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                            zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                            if (zticket.getBycategorytype().equals("0003")) {
                                Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                        .getItickettypeid().toString());
                                tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                tzorderlist.setTripid(control.getTripid());
                                tzorderlist.setIvenueid(prd.getIvenueid());
                                tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                            } else {
                                tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                            }
                            tzorderlist.setZpric(pricepost.getMactualsaleprice());
                            tzorderlist.setJsprice(pricepost.getJsprice());
                            tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
                            tzorderlist.setZyhnumb(0l);
                            tzorderlist.setZyhamnt(0.0);
                            tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
                            newproduct.getZorderlist().add(tzorderlist);
                        }
                        addlist.add(newproduct);
                    }
                }
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            //  全部通过的话,再修改订单,否则返回错误信息
            //  增量 退订 减少或者增加预付款 计算信用度
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
            }
            // 计算出需要增加的金额
            double totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid);
            // 需要退订的钱
            double tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid);

            double userMoney = CommonUtil.getUserMoney(oldmorder.getZfusid());

            if(!hqyt) {
                // 比较两个map中的值，计算是否可以修改 不可修改返回false
                if (userMoney < Math.abs(tdsx) + totalmont + tdmont) {
                    returnmap.put("result", false);
                    returnmap.put("errtp", "2");// 余额不足
                    return returnmap;
                }
            }
            List charraftlist = null;
            List newdaylist = null;
            List olddaylist = null;
            int istoprat = 0;
            if (oldmap != null) {
                charraftlist = (List) oldmap.get("charraft");
                olddaylist = (List) oldmap.get("chaday");
            }
            List newraft = null;
            if (newmap != null) {
                newdaylist = (List) newmap.get("daylist");
                newraft = (List) newmap.get("raftlist");
            }
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);

            istoprat = checkStopRaft(charraftlist, iscenicid);

            // 新增订单
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null);
            // 退订单
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist);

            TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            YOrder oldyorder = (YOrder) yorderlistdao.get(YOrder.class, new YOrderId(oldtorder.getId().getOrid(), oldtorder.getId().getIscenicid()));
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldyorder.getTpmont() != null) {
                oldyorder.setTpmont(oldyorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldyorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldyorder.getTpsx() != null) {
                    oldyorder.setTpsx(oldyorder.getTpsx() + Math.abs(tdsx));
                } else {
                    oldyorder.setTpsx(Math.abs(tdsx));
                }
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            if (oldmorder.getTpmont() != null) {
                oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            oldmorder.setIsf(1l);
            oldyorder.setIsf(1l);
            oldtorder.setIsf(1l);
            yorderlistdao.update(oldyorder);
            yorderlistdao.update(oldtorder);
            yorderlistdao.update(oldmorder);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);
            this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "27");
            this.updateMOrderStatusByZeroProduct(oldorid, "27");
            //判断库存信息
            List<TOrderlist> newTorderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            Custom custom = (Custom) this.morderdao.get(Custom.class, stockUsid);
            if (custom.getIbusinessid() == 2L && custom.getUstp().equals("02") && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getUsid();
            }
            if (newTorderlists != null && !newTorderlists.isEmpty()) {//部分修改
                for (TOrderlist ntl : newTorderlists) {
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
//                ticketService.saveStockDetails(stockOrderInfos, true);
                ticketService.saveStockDetails(JSON.toJSONString(stockOrderInfos), true);
            } else {//全退订
                ticketService.deleteStockDetails(oldorid, Long.parseLong(iscenicid), null, null);
            }

            if(StringUtils.isBlank(oldmorder.getNoteh())){
                throw new RuntimeException("支付号不存在");
            }
            if(hqyt){
                MOrder morder = (MOrder) ticketDao.get(MOrder.class,orids[1]);
                HqytClient client = new HqytClient();
                Map<String, String> merchant = CommonUtil.findMerchant();
                SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
                sortMap.put("merchantId", merchant.get("merchantId"));
                sortMap.put("outTradeNo", oldorid);

                //请求字符窜
                StringBuffer sb = new StringBuffer();
                Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, Object> next = iterator.next();
                    sb.append(next.getKey());
                    sb.append("=");
                    sb.append(next.getValue());
                    sb.append("&");
                }
                String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
                sb.append("sign="+sign2);

                try{
                    JSONObject refundBill = client.cancelOrder(sb.toString());
                    if(refundBill != null){
                        //morder.setNoteh(refundBill.getInvoice().getId().toString());
                        morder.setNotec("1");//标识退款状态为退款中 0：退款失败 1：退款成功 2：退款中
                        morder.setDdzt("27");
                        ticketDao.update(morder);
                    }else{
                        throw new RuntimeException("取消预定失败:申请取消预定失败");
                    }
                }catch (Exception e){
                    throw new RuntimeException("取消预定失败:"+e.getMessage());
                }
            }
            //发送短信
//            if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
//               mbmessageDao.sendMessageNew(oldtorder.getOrph(), "0004", new String[]{oldtorder.getId().getOrid()});
//            }
        } catch (Exception e) {
            if (!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")) {
                throw new RuntimeException(e.getMessage());
            } else {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        return returnmap;

    }

    /**
     * Describe:根据订单号查找服务商编号
     *
     * @param orid
     * @return return:List Date:May 3, 2012
     * @auth:lijingrui
     */
    public List showscenicidview(String orid) {
        String sql = " from TOrder where id.orid='" + orid + "' and mont>0";
        List list = this.find(sql);
        return list;
    }

    public List showscenicidview(String orid, String iscenicid) {
        String sql = " from TOrder where id.orid='" + orid + "' and id.iscenicid=" + iscenicid + " and mont>0";
        List list = this.find(sql);
        return list;
    }

    /**
     * Describe:根据产品编号、日期来显示每趟次可预订的竹筏量
     *
     * @param itickettypeid
     * @param date
     * @param ziticketid
     * @param usid
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException return:List Date:May 4, 2012
     * @auth:lijingrui
     */
    public List getRafttripInfoList(String itickettypeid, String date, String ziticketid, String usid) throws IllegalAccessException, InvocationTargetException {
        Sysparv5 sys = (Sysparv5) get(Sysparv5.class, new Sysparv5Id("YDJF", "02"));
        List list = getNumberControllData(itickettypeid, date, "02");
        StringBuffer hql = new StringBuffer();
        if (list == null || list.size() < 1) {
            return null;
        } else {
            hql.append("select new map(numcontroll.productcontrolid as productcontrolid, tm.productmanageid as productmanageid,trip.tripname as tripname,tm.starttime as starttime,tm.endtime as endtime,tm.startdata as startdata,tm.enddata as enddata,wharf.ivenueareaname as ivenueareaname,trip.tripid as tripid,");
            if (list == null || list.size() < 1) {
                hql.append("(numcontroll.salablenumber-numcontroll.soldnumber) as salablenumber,");
            } else {
                Productcontrol pc = new Productcontrol();
                BeanUtils.populate(pc, (Map) list.get(0));
                int raftnumber = pc.getSalablenumber().intValue() - pc.getSoldnumber().intValue();
                hql.append("(select (case when (p.salablenumber-p.soldnumber)>" + raftnumber + " then " + raftnumber
                        + "  else (p.salablenumber-p.soldnumber) end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");
            }
            hql.append(" ticket.iscenicid as iscenicid) from Prdtripvenuemanage tm,Edmtickettypetab ticket,Trip trip,Venuearea wharf,Productcontrol numcontroll"
                    + " where tm.itickettypeid = ticket.itickettypeid and numcontroll.bystate=1  and numcontroll.byisuse=1 and trip.iscenicid = tm.iscenicid and tm.tripid = trip.tripid and ticket.itickettypeid ="
                    + ziticketid
                    + " and ticket.byisuse=1 and   trip.byisuse=1  and tm.startdata <= '"
                    + date
                    + "' and tm.enddata >= '"
                    + date
                    + "' and tm.iscenicid=wharf.iscenicid and tm.ivenueareaid=wharf.ivenueareaid and tm.iscenicid=numcontroll.iscenicid and ticket.itickettypeid=numcontroll.itickettypeid and trip.tripid=numcontroll.tripid and tm.ivenueareaid=numcontroll.ivenueareaid and tm.ivenueid=numcontroll.ivenueid and numcontroll.stdata='"
                    + date + "'");
            if (Tools.getDayNumb(date, Tools.getDays()) == 1) {
                hql.append(" and tm.starttime>='" + Tools.getNowTime().substring(0, 5) + "'");
            }
            hql.append(" group by numcontroll.salablenumber,numcontroll.productcontrolid,tm.productmanageid,trip.tripname,tm.starttime,tm.endtime,tm.startdata,tm.enddata,wharf.ivenueareaname,ticket.iscenicid,ticket.itickettypeid,trip.tripid order by tm.starttime");

        }
        return find(hql.toString());
    }

    /**
     * * Describe:根据时间、产品、控制模式获取产品数量控制数据
     *
     * @param iticketid
     * @param date
     * @param controlltype 01：总数控制 02:日控制 03:趟次控制 04:趟次区域控制 05：座位控制
     * @return
     * @author yangguang Date:2011-10-29
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getNumberControllData(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public List getNumberControllData(String iticketid, String date, String controlltype) {
        String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.controltype as controltype) from Productcontrol pc where itickettypeid="
                + iticketid + " and stdata='" + date + "' and byisuse=1 and bystate=1 and pc.controltype='" + controlltype + "'";
        if (controlltype.equals("02")) {
            hql += " and tripid is null";
        }
        return find(hql);
    }

    /**
     * Describe:审核前散客修改订单保存
     *
     * @param orderlistInfo
     * @param neworderlist
     * @param orids
     * @param oldorid
     * @param iscenicid
     * @param stdt
     * @param ibusiness
     * @param usid
     * @return
     * @throws Exception return:Map Date:May 3, 2012
     * @auth:lijingrui
     */
    public Map orderEditCenter(List<TOrderlist> orderlistInfo, String[] orids, String orid, String stdt, String ibusiness, String usid) throws Exception {
        Map returnmap = new HashMap();
        // 判断票务订单的数量控制
        // Map map = validateQuckorder(orderlistInfo, orid, usid);
        // Boolean result = (Boolean) map.get("result");
        // if (!result.booleanValue()) {
        // returnmap = map;
        // return returnmap;
        // }
        List tlist = showscenicidview(orid);
        // 判断修改后的订单金额与预付款
        if (tlist != null && tlist.size() > 0) {
            for (int x = 0; x < tlist.size(); x++) {
                TOrder td = (TOrder) tlist.get(x);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
                List chalist = new ArrayList();
                TOrderlist newtorderlist = null;
                TOrderlist oldtorderlist = null;
                TOrderlist ctorderlist = null;
                for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
                    oldtorderlist = new TOrderlist();
                    BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
                    for (int j = 0; j < orderlistInfo.size(); j++) {// 修改后的订单
                        newtorderlist = (TOrderlist) orderlistInfo.get(j);
                        if (newtorderlist.getOrderlistid().equals(
                                oldtorderlist.getOrderlistid())
                                && newtorderlist.getIscenicid().equals(
                                oldtorderlist.getIscenicid())) {
                            if (newtorderlist.getNumb().intValue() != oldtorderlist
                                    .getNumb().intValue()) {
                                ctorderlist = new TOrderlist();
                                BeanUtils.copyProperties(ctorderlist,
                                        oldtorderlist);
                                // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                                ctorderlist.setNumb(newtorderlist.getNumb()
                                        - oldtorderlist.getNumb());
                                ctorderlist.setId(new TOrderlistId(Long
                                        .parseLong(oldtorderlist
                                                .getOrderlistid()),
                                        oldtorderlist.getOrid(), Long
                                        .parseLong(iscenicid)));
                                ctorderlist
                                        .setAmnt(oldtorderlist.getPric()
                                                * (newtorderlist.getNumb() - oldtorderlist
                                                .getNumb()));
                                chalist.add(ctorderlist);

                            }

                        }
                    }
                }

                double amount = 0.0;
                if (chalist != null && chalist.size() > 0) {
                    // 遍历修改后和修改前订单对比出来的产品列表
                    for (int i = 0; i < chalist.size(); i++) {
                        ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
                        oldtorderlist = (TOrderlist) yorderlistdao.get(
                                TOrderlist.class, new TOrderlistId(
                                        Long.parseLong(ctorderlist
                                                .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        amount += oldtorderlist.getPric()
                                * ctorderlist.getNumb();// 叠加价格

                    }
                    /**
                     Vipbalance balance = (Vipbalance) torderdao.get(
                     Vipbalance.class, usid);
                     // 比较两个map中的值，计算是否可以修改 不可修改返回false
                     if (balance.getPoint() < amount) {
                     returnmap.put("result", false);
                     returnmap.put("errtp", "2");// 余额不足
                     return returnmap;
                     }
                     **/
                }

            }
        }

        // 修改订单数量
        if (tlist != null && tlist.size() > 0) {
            for (int x = 0; x < tlist.size(); x++) {
                TOrder td = (TOrder) tlist.get(x);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
                List chalist = new ArrayList();
                TOrderlist newtorderlist = null;
                TOrderlist oldtorderlist = null;
                TOrderlist ctorderlist = null;
                Double deposit = getdeposit(orid, iscenicid);
                for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
                    oldtorderlist = new TOrderlist();
                    BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));

                    for (int j = 0; j < orderlistInfo.size(); j++) {// 修改后的订单
                        newtorderlist = (TOrderlist) orderlistInfo.get(j);
                        if (newtorderlist.getOrderlistid().equals(
                                oldtorderlist.getOrderlistid())
                                && newtorderlist.getIscenicid().equals(
                                oldtorderlist.getIscenicid())) {
                            if (newtorderlist.getNumb().intValue() != oldtorderlist
                                    .getNumb().intValue()) {
                                ctorderlist = new TOrderlist();
                                BeanUtils.copyProperties(ctorderlist,
                                        oldtorderlist);

                                // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                                ctorderlist.setNumb(newtorderlist.getNumb()
                                        - oldtorderlist.getNumb());
                                ctorderlist.setId(new TOrderlistId(Long
                                        .parseLong(oldtorderlist
                                                .getOrderlistid()),
                                        oldtorderlist.getOrid(), Long
                                        .parseLong(iscenicid)));
                                ctorderlist
                                        .setAmnt(oldtorderlist.getPric()
                                                * (newtorderlist.getNumb() - oldtorderlist
                                                .getNumb()));
                                chalist.add(ctorderlist);

                                // 修改数量 Torderlist
                                TOrderlist endtorderlist = (TOrderlist) torderlistdao
                                        .get(
                                                TOrderlist.class,
                                                new TOrderlistId(
                                                        Long
                                                                .parseLong(oldtorderlist
                                                                        .getOrderlistid()),
                                                        oldtorderlist.getOrid(),
                                                        Long
                                                                .parseLong(iscenicid)));
                                endtorderlist.setNumb(newtorderlist.getNumb());
                                endtorderlist.setAmnt(new Double(oldtorderlist
                                        .getPric()
                                        * newtorderlist.getNumb()));
                                this.update(endtorderlist);

                                // 修改 TZorderlist
                                String sql = " from TZorderlist where id.orderlistid="
                                        + Long.parseLong(oldtorderlist
                                        .getOrderlistid())
                                        + " and id.orid='"
                                        + oldtorderlist.getOrid()
                                        + "' and id.iscenicid="
                                        + Long.parseLong(iscenicid);
                                TZorderlist endtzorder = (TZorderlist) this
                                        .find(sql).get(0);
                                endtzorder.setZnumb(newtorderlist.getNumb());
                                endtzorder.setZamnt(new Double(endtzorder
                                        .getZnumb() * endtzorder.getZpric()));
                                this.update(endtzorder);

                                // 修改Yorderlist
                                YOrderlist endorderlist = (YOrderlist) torderlistdao
                                        .get(
                                                YOrderlist.class,
                                                new YOrderlistId(
                                                        Long
                                                                .parseLong(oldtorderlist
                                                                        .getOrderlistid()),
                                                        oldtorderlist.getOrid(),
                                                        Long
                                                                .parseLong(iscenicid)));
                                endorderlist.setNumb(newtorderlist.getNumb());
                                endorderlist.setAmnt(new Double(oldtorderlist
                                        .getPric()
                                        * newtorderlist.getNumb()));
                                this.update(endorderlist);

                                // 修改 YZorderlist
                                String hql = " from YZorderlist where id.orderlistid="
                                        + Long.parseLong(oldtorderlist
                                        .getOrderlistid())
                                        + " and id.orid='"
                                        + oldtorderlist.getOrid()
                                        + "' and id.iscenicid="
                                        + Long.parseLong(iscenicid);
                                YZorderlist endyzorder = (YZorderlist) this
                                        .find(hql).get(0);
                                endyzorder.setZnumb(newtorderlist.getNumb());
                                endyzorder.setZamnt(new Double(endyzorder
                                        .getZnumb() * endyzorder.getZpric()));
                                this.update(endyzorder);

                            }

                        }
                    }
                }

                double amount = 0.0;
                int maxnum = getTorderlistMaxnum(orid, iscenicid);
                if (chalist != null && chalist.size() > 0) {
                    // 遍历修改后和修改前订单对比出来的产品列表
                    for (int i = 0; i < chalist.size(); i++) {
                        ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
                        oldtorderlist = (TOrderlist) yorderlistdao.get(
                                TOrderlist.class, new TOrderlistId(
                                        Long.parseLong(ctorderlist
                                                .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        amount += oldtorderlist.getPric()
                                * ctorderlist.getNumb();// 叠加价格

                        // 修改 TOrder
                        TOrder endtorder = (TOrder) this.get(TOrder.class,
                                new TOrderId(orid, Long.parseLong(iscenicid)));
                        endtorder.setMont(new Double(endtorder.getMont()
                                + oldtorderlist.getPric()
                                * ctorderlist.getNumb()));
                        if (endtorder.getNoted() != null
                                && endtorder.getNoted().equals("02")) {// 定金支付
                            endtorder.setZfmont(maxnum * deposit);
                        } else {
                            endtorder.setZfmont(new Double(endtorder
                                    .getZfmont()
                                    + oldtorderlist.getPric()
                                    * ctorderlist.getNumb()));
                        }
                        if (endtorder.getMont() == 0) {// 若酒店的产品数量都为0时，即Torder中酒店服务商的付款金额为0时，修改其订单状态
                            if (endtorder.getDdzt() != "00") {
                                endtorder.setDdzt("00");
                            }
                        }
                        this.update(endtorder);

                        // 修改 YOrder
                        YOrder endyorder = (YOrder) this.get(YOrder.class,
                                new YOrderId(orid, Long.parseLong(iscenicid)));
                        endyorder.setMont(new Double(endyorder.getMont()
                                + oldtorderlist.getPric()
                                * ctorderlist.getNumb()));
                        if (endtorder.getNoted() != null && endtorder.getNoted().equals("02")) {//定金支付
                            endyorder.setZfmont(endtorder.getZfmont());
                        } else {
                            endyorder.setZfmont(new Double(endyorder.getZfmont()
                                    + oldtorderlist.getPric()
                                    * ctorderlist.getNumb()));
                        }
                        this.update(endyorder);
                    }

                    MOrder moder = (MOrder) this.get(MOrder.class, orid);
                    moder.setMont(moder.getMont() + amount);
                    if (moder.getNoted() != null
                            && moder.getNoted().equals("02")) {
                        String hsql = "select sum(zfmont) from TOrder where id.orid='"
                                + orid + "' and scenictype='06' ";
                        List hslist = this.find(hsql);
                        Double zfmontsum = (Double) hslist.get(0);
                        moder.setZfmont(zfmontsum);
                    } else {
                        moder.setZfmont(moder.getZfmont() + amount);
                    }
                    if (moder.getDdzt() != "00") {
                        String hsql = "select sum(mont) from TOrder where id.orid='"
                                + orid + "' and scenictype='06' ";
                        List hslist = this.find(hsql);
                        Double montsum = (Double) hslist.get(0);
                        if (montsum == 0) {
                            moder.setDdzt("00");
                        }
                    }
                    this.update(moder);
                }
            }
        }

        // 删除 订单中数量为0的数据
        if (tlist != null && tlist.size() > 0) {
            for (int y = 0; y < tlist.size(); y++) {
                TOrder td = (TOrder) tlist.get(y);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List list = torderlistdao.getTOrderListList(orid, iscenicid);
                TOrderlist oldlist = null;
                for (int j = 0; j < list.size(); j++) {
                    oldlist = new TOrderlist();
                    BeanUtils.populate(oldlist, (Map) list.get(j));
                    if (oldlist.getNumb() <= 0) {
                        String sql = " from TZorderlist where id.orderlistid="
                                + Long.parseLong(oldlist.getOrderlistid())
                                + " and id.orid='" + oldlist.getOrid()
                                + "' and id.iscenicid="
                                + Long.parseLong(iscenicid);
                        TZorderlist endtzorder = (TZorderlist) this.find(sql)
                                .get(0);
                        this.delete(endtzorder);

                        TOrderlist tt = (TOrderlist) this.get(TOrderlist.class,
                                new TOrderlistId(Long.parseLong(oldlist
                                        .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        this.delete(tt);
                    }
                }

                // 获取原始订单
                List ylist = yorderlistdao.getYOrderListList(orid, iscenicid);
                YOrderlist oldylist = null;
                for (int j = 0; j < ylist.size(); j++) {
                    oldylist = new YOrderlist();
                    BeanUtils.populate(oldylist, (Map) ylist.get(j));
                    if (oldylist.getNumb() <= 0) {
                        String sql = " from YZorderlist where id.orderlistid="
                                + Long.parseLong(oldylist.getOrderlistid())
                                + " and id.orid='" + oldylist.getOrid()
                                + "' and id.iscenicid="
                                + Long.parseLong(iscenicid);
                        YZorderlist endyzorder = (YZorderlist) this.find(sql)
                                .get(0);
                        this.delete(endyzorder);

                        YOrderlist tt = (YOrderlist) this.get(YOrderlist.class,
                                new YOrderlistId(Long.parseLong(oldylist
                                        .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        this.delete(tt);
                    }
                }

                this.updateOrderZtByZeroProduct(orid,
                        Long.parseLong(iscenicid), "27");
            }
            this.updateMOrderStatusByZeroProduct(orid, "27");
        }
        returnmap.put("result", true);
        returnmap.put("errtp", null);// 成功
        return returnmap;
    }

    /**
     * Describe:判断票务订单中修改数量时 产品包含竹筏的判断余量
     *
     * @param orderlistInfo
     * @param orid
     * @param usid
     * @return
     * @throws Exception return:Map Date:May 11, 2012
     * @auth:lijingrui
     */
    public Map validateQuckorder(List<TOrderlist> orderlistInfo, String orid, String usid) throws Exception {
        Map returnmap = new HashMap();
        List scenlist = showscenicidview(orid);
        if (scenlist != null && scenlist.size() > 0) {
            for (int i = 0; i < scenlist.size(); i++) {
                TOrder td = (TOrder) scenlist.get(i);
                if (td.getScenictype().equals("01")) {
                    String iscenicid = td.getId().getIscenicid().toString();

                    Double needminus = 0.0;
                    Map oldInfoMap = calculateOldOrder(orid, iscenicid, orderlistInfo);
                    List chalist = (List) oldInfoMap.get("chaticket");// 根据产品算出的需要加减的产品列表
                    // 票务订单无修改
                    if (chalist == null || chalist.size() <= 0) {
                        returnmap.put("result", true);
                        return returnmap;
                    }
                    List charraft = (List) oldInfoMap.get("charraft");// 根据竹筏信息算出来的需要加减的竹筏量
                    List olddayslist = null;
                    if (oldInfoMap != null) {
                        olddayslist = (List) oldInfoMap.get("chaday");
                    }
                    List exceptiondays = validateDay(null, olddayslist);
                    if (exceptiondays != null && exceptiondays.size() > 0) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "0");// 日控制
                        returnmap.put("errlist", exceptiondays);
                        return returnmap;
                    } else {
                        //  首先判断竹筏量是否满足,即原订单中退订的票种含有的竹筏量与修改订单之后新增的竹筏量相比较
                        List exceptionlist = validateRaft(null, charraft);
                        if (exceptionlist != null && exceptionlist.size() > 0) {
                            returnmap.put("result", false);
                            returnmap.put("errtp", "1");// 趟次控制
                            returnmap.put("errlist", exceptionlist);
                            return returnmap;
                        }
                    }
                    Double oldneedmount = (Double) oldInfoMap.get("amount");
                    Vipbalance balance = (Vipbalance) torderdao.get(Vipbalance.class, usid);
                    //  比较两个map中的值，计算是否可以修改 不可修改返回false
                    if (balance.getPoint() < oldneedmount + needminus) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "2");// 余额不足
                        return returnmap;
                    }
                }

            }
        }

        returnmap.put("result", true);
        return returnmap;
    }

    public void updateOrderZtByZeroProduct(String orid, Long iscenicid, String ddzt) {
        List list = torderlistdao.getTOrderlists(orid, iscenicid);
        if (list == null || list.size() < 1) {
            TOrder torder = (TOrder) torderlistdao.get(TOrder.class, new TOrderId(orid, iscenicid));
            torder.setDdzt(ddzt);
            torderlistdao.update(torder);
            YOrder yorder = (YOrder) torderlistdao.get(YOrder.class, new YOrderId(orid, iscenicid));
            yorder.setDdzt(ddzt);
            torderlistdao.update(yorder);
        }
    }

    private void updateMOrderStatusByZeroProduct(String orid, String ddzt) {
        List list1 = torderlistdao.getTOrderlists(orid);
        if (list1 == null || list1.size() < 1) {
            MOrder morder = (MOrder) torderlistdao.get(MOrder.class, orid);
            morder.setDdzt(ddzt);
            torderlistdao.update(morder);
        }
    }

    public Map validTorderInfo(String orid) throws Exception {
        Map errMsg = new HashMap();
        try {
            List torderlist = torderdao.getTOrderList(orid);
            int x = 0;
            for (int i = 0; i < torderlist.size(); i++) {
                TOrder torder = new TOrder();
                BeanUtils.populate(torder, (Map) torderlist.get(i));
                Esbscenicareatab provider = (Esbscenicareatab) torderdao.get(Esbscenicareatab.class, Long.parseLong(torder.getIscenicid()));
                // 获取服务商信息中设置的可提前预定日期
                String providerDay = Tools.getDate(Tools.getDays(), provider.getImaxdata().intValue());
                // 获取服务商当天最晚预定时间
                String providetime = provider.getSzlasttime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // 验证首次游览日期是否满足服务商所设置的最大提前预定日期
                String today = Tools.getDays();
                Date d1 = sdf.parse(today);// 当天
                Date d2 = sdf.parse(providerDay);// 可以最大提前预定的日期
                if (!d1.before(d2)) {
                    errMsg.put(x, "[" + provider.getSzscenicname() + "]的首次游览日期已超过最大提前预定时间");
                    x += 1;
                } else {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    StringBuffer endtime = new StringBuffer(torder.getDtstartdate() + " " + provider.getSzlasttime() + ":00");
                    Date d3 = sdf1.parse(endtime.toString());
                    Date d4 = sdf1.parse(Tools.getDayTimes());
                    if (d4.after(d3)) {
                        errMsg.put(x, "已超过[" + provider.getSzscenicname() + "]的当天最晚预定时间");
                        x += 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return errMsg;
    }

    private String[] getStartAndEndDate(String[] dateArray) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < dateArray.length; i++) {
                for (int j = 0; j < dateArray.length - 1; j++) {
                    Date d1 = sdf.parse(dateArray[j]);
                    Date d2 = sdf.parse(dateArray[j + 1]);
                    if (d1.before(d2)) {
                        String change = dateArray[j];
                        dateArray[j] = dateArray[j + 1];
                        dateArray[j + 1] = change;
                    }
                }
            }
        } catch (ParseException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }
        return dateArray;
    }

    public List<TZorderlist> getTZorderlist(String orid, Long torderlistid, long iscenicid) {
        return tZOrderListService.getTZorderlist(torderlistid, orid, iscenicid);
    }


    // 新增订单(退订单，或者增量订单)  服务商优惠处理
    private double addScheamNewOrder(List addlist, String oldorid, String neworid, String iscenicid, String currentusid, MOrder oldmorder, double totalmont, String ortp, int stopraft, double sxf,
                                     List oldtdlist, List scheamList) throws IllegalAccessException, InvocationTargetException, ParseException {
        Custom user = (Custom) yorderdao.get(Custom.class, oldmorder.getUsid());
        // 开始封装订单
        double totaltdsx = 0.0;
        if (addlist != null && addlist.size() > 0) {
            //  增量订单neworderlist 和 orderlistInfo与 原订单中中数量为对比后 挑选出来的数量为正数的产品
            oldmorder = (MOrder) ticketDao.get(MOrder.class, oldorid);
            Map map = DateUtils.getMonthDate(Integer.parseInt(oldmorder.getStdt().substring(0, 4)), Integer.parseInt(oldmorder.getStdt().substring(5, 7)));
            MOrder morder = new MOrder();
            YOrder yorder = new YOrder();
            morder.setOrid(neworid);
            morder.setOrtp(ortp);// 订单类型 03：增量订单
            morder.setUsid(oldmorder.getUsid());
            morder.setOrda(Tools.getDays());
            morder.setOrti(Tools.getNowString().substring(11, Tools.getNowString().length()));
            morder.setIsj(0l);
            morder.setYhamnt(0.0);// 优惠金额
            morder.setMont(0.0);
            morder.setZfmont(0.0);// 支付的金额
            morder.setIsallcp(0l);// 是否全部出票
            morder.setZffs("06");// 支付方式
            morder.setBank("92");// 支付银行
            morder.setZfusid(oldmorder.getZfusid());// 订单支付人
            morder.setPayorid("");// 支付订单号
            morder.setBankdata(Tools.getDays());// 支付日期
            morder.setBanktime(Tools.getNowTimeString());// 支付时间
            morder.setSrid(oldorid);// 对应原订单号
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                morder.setIsjl(1l);
            } else {
                morder.setIsjl(0l);
            }
            morder.setTpfs("02");
            ticketDao.save(morder);
            YOrder oldyorder = (YOrder) ticketDao.get(YOrder.class, new YOrderId(oldorid, Long.parseLong(iscenicid)));
            yorder.setId(new YOrderId(morder.getOrid(), Long.parseLong(iscenicid)));
            // 根据编号找服务商类型
            Esbscenicareatab esb = (Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(iscenicid));
            yorder.setScenictype(esb.getScenictype());// 服务商类别 01 景区 06酒店 07 旅行社
            // 08 超市 09 租赁公司
            yorder.setUsid(morder.getUsid());// 游客编号
            yorder.setIbusinessid(user.getIbusinessid());// 业务id
            yorder.setSztravelbillno("");// 行程单号
            yorder.setIregionalid(0l);// 客源地id
            yorder.setTdlx("");// 团队性质
            yorder.setDtstartdate(oldyorder.getDtstartdate());// 游览开始日期
            yorder.setDtenddate(oldyorder.getDtenddate());// 游览结束日期
            yorder.setDyusid("");// 导游id
            yorder.setOrnm("");// 领票人姓名
            yorder.setOrzj("");// 领票人证件类别
            yorder.setOrhm("");// 领票人证件号码
            yorder.setFaxno("");// 传真号
            yorder.setOrph("");// 领票人电话
            yorder.setMont(0.0);
            yorder.setYhamnt(0.0);// 优惠金额
            yorder.setZfmont(0.0);// 支付的金额
            yorder.setYoufei(0.0);// 邮费
            yorder.setTpfs("02");
            // ticketDao.save(torder);
            ticketDao.save(yorder);
            long indexpk = getMaxPk("id.orderlistid", new String[]{"id.iscenicid"}, new Long[]{Long.parseLong(iscenicid)}, new String[]{"id.orid"}, new String[]{oldorid}, "TOrderlist");
            int yhnum = 0;// 这个积分的..
            double yhamnt = 0.0;// 这个是优惠方案的
            int pk = 0;
            DecimalFormat format = new DecimalFormat("0.00");
            Numjifenset set = ticketService.getNumjifenset(iscenicid);
            long totalmonthjf = 0;
            long totalyearjf = 0;
            for (int i = 0; i < addlist.size(); i++) {
                TOrderlist torderlist = (TOrderlist) addlist.get(i);
                YOrderlist yorderlist = new YOrderlist();
                yorderlist.setId(new YOrderlistId(new Long(i) + 1, morder.getOrid(), Long.parseLong(iscenicid)));
                yorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());
                yorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());
                yorderlist.setItickettypeid(torderlist.getItickettypeid());
                yorderlist.setDtstartdate(torderlist.getDtstartdate());// 开始日期
                yorderlist.setDtenddate(torderlist.getDtenddate());// 结束日期
                yorderlist.setPric(torderlist.getPric());// 单价
                yorderlist.setJsprice(torderlist.getJsprice());// 结算价
                yorderlist.setNumb(Math.abs(torderlist.getNumb()));// 数量
                yorderlist.setYhnumb(torderlist.getYhnumb());// 优惠数量
                yorderlist.setAmnt(Math.abs(torderlist.getNumb() * torderlist.getPric()));// 金额
                yorderlist.setYhamnt(Math.abs(torderlist.getYhnumb() * torderlist.getPric()));// 优惠金额
                yorderlist.setIoffersschemeid(torderlist.getIoffersschemeid());// 优惠方案ID
                yorderlistdao.save(yorderlist);
                TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                        torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                Numjifensetlist detail = null;
                if (set != null) {
                    detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), user.getIbusinessid(),
                            torderlist.getDtstartdate());
                }
                if (old != null) {// 退订时ID 肯定有, 如果是增量 那么ID可能有
                    // 可能没有有就修改 没有就新增
                    //  先获取原先的信息 然后再修改
                    long jf = 0;
                    if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                        if (detail.getXffs().equals("03")) {
                            // 计算数量变化后的积分
                            if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
                            } else {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4();
                            }
                            jf = jf - old.getIsi();
                            totalmonthjf += jf;
                            yorderlist.setIsi(Math.abs(jf));
                        } else {
                            // 计算数量变化后的积分
                            if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
                            } else {
                                jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4();
                            }
                            jf = jf - old.getIsh();
                            totalyearjf += jf;
                            yorderlist.setIsh(Math.abs(jf));
                        }
                    }
                    List list = tZOrderListService.getTZorderlist(old.getId().getOrderlistid(), old.getId().getOrid(), old.getId().getIscenicid());
                    for (int y = 0; y < list.size(); y++) {
                        double fl = 0.0;
                        TZorderlist newzlist = (TZorderlist) list.get(y);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
                        yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
                        yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
                        yzorderlist.setItickettypeid(newzlist.getItickettypeid());
                        yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
                        yzorderlist.setDtstartdate(newzlist.getDtstartdate());
                        yzorderlist.setDtenddate(newzlist.getDtenddate());
                        yzorderlist.setTripid(newzlist.getTripid());
                        yzorderlist.setIvenueid(newzlist.getIvenueid());
                        yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
                        yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
                        yzorderlist.setZpric(newzlist.getZpric());
                        yzorderlist.setZnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        yzorderlist.setZyhnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getYhnumb()));
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        yzorderlist.setJsprice(newzlist.getJsprice());
                        if (torderlist.getNumb().intValue() > 0) {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() + (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        } else {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() - (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        }
                        // 这里有可能是退订单，也可能是增量 根据原tzorderlist的子票数量除以torderlist的数量
                        // 再乘以修改后的数量(增量为正，退订为负),再乘以单价，然后加到原有的总价中(增量为证，退订为负)
                        newzlist.setZamnt(Math.abs(newzlist.getZamnt() + newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        newzlist.setZnumb(Math.abs(newzlist.getZnumb() + newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        Productcontrol control = ticketDao.getTripControl(newzlist.getTripid(), newzlist.getIvenueid(), newzlist.getIvenueareaid(), newzlist.getDtstartdate().substring(0, 10));
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {// 退订且原订单状态不为申请单
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                fl = gettdfl(yzorderlist, oldmorder.getUsid());
                                yzorderlist.setMhandcharge(yzorderlist.getZpric() * fl * yzorderlist.getZnumb());
                                yzorderlist.setTdfl(fl);
                                RaftComparepojo tdcompare = null;
                                for (int x = 0; x < oldtdlist.size(); x++) {
                                    tdcompare = (RaftComparepojo) oldtdlist.get(x);
                                    if (yzorderlist.getTripid().intValue() > 0) {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10))
                                                && tdcompare.getTripid().intValue() == yzorderlist.getTripid().intValue() && control != null && control.getBystate().intValue() == 1 && fl > 0) {
                                            //  这里比较数量 若需要退订的大于当前的数量 那么全部收取
                                            // 若小于则填充要收取的数量
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    } else {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10)) && fl > 0
                                                && tdcompare.getPrice() == yzorderlist.getZpric().doubleValue()) {
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    }
                                }
                            } else {
                                yzorderlist.setMhandcharge(0.0);
                                yzorderlist.setSqnumber(0l);
                            }
                        } else {
                            yzorderlist.setMhandcharge(0.0);
                            yzorderlist.setSqnumber(0l);
                        }
                        yorderlistdao.save(yzorderlist);
                        if (newzlist.getZnumb().intValue() < 1) {
                            yorderlistdao.delete(newzlist);
                        } else {
                            yorderlistdao.update(newzlist);
                        }
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                if (yorderlist.getMhandcharge() != null && !yorderlist.getMhandcharge().equals("")) {
                                    yorderlist.setMhandcharge(yorderlist.getMhandcharge() + yzorderlist.getMhandcharge());
                                } else {
                                    yorderlist.setMhandcharge(yzorderlist.getMhandcharge());
                                }
                            } else {
                                yorderlist.setMhandcharge(0.0);
                            }
                        } else {
                            yorderlist.setMhandcharge(0.0);
                        }
                    }
                    old.setNumb(Math.abs(old.getNumb() + torderlist.getNumb()));
                    old.setAmnt(old.getNumb() * old.getPric());
                    if (torderlist.getNumb().intValue() > 0) {
                        old.setYhnumb(old.getYhnumb() + torderlist.getYhnumb());
                    } else {
                        old.setYhnumb(old.getYhnumb() - torderlist.getYhnumb());
                    }
                    old.setYhamnt(old.getYhnumb() * old.getPric());
                    if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                        if (detail.getXffs().equals("03")) {//月消费
                            old.setIsi(old.getIsi() + jf);
                        } else {
                            old.setIsh(old.getIsh() + jf);
                        }
                    }
                    if (old.getNumb().intValue() < 1) {
                        yorderlistdao.delete(old);
                    } else {
                        yorderlistdao.update(old);
                    }
                } else {
                    pk++;
                    TOrderlist newtorderlist = new TOrderlist();
                    BeanUtils.copyProperties(newtorderlist, torderlist);
                    newtorderlist.setId(new TOrderlistId(new Long(indexpk + pk), oldorid, Long.parseLong(iscenicid)));
                    newtorderlist.setAmnt(Math.abs(newtorderlist.getAmnt()));
                    newtorderlist.setNumb(Math.abs(newtorderlist.getNumb()));
                    newtorderlist.setJsprice(Math.abs(newtorderlist.getJsprice()));
                    long jf = 0;
                    if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
                        jf = torderlist.getNumb() / detail.getIint3().intValue() * detail.getIint4();
                        if (detail.getXffs().equals("03")) {
                            totalmonthjf += jf;
                            yorderlist.setIsi(jf);
                            newtorderlist.setIsi(jf);
                        } else {
                            totalyearjf += jf;
                            yorderlist.setIsh(jf);
                            newtorderlist.setIsh(jf);
                        }
                    }
                    newtorderlist.setYhnumb(torderlist.getYhnumb());
                    newtorderlist.setYhamnt(newtorderlist.getPric() * torderlist.getYhnumb());
                    yorderlistdao.saveOrUpdate(newtorderlist);
                    int indexpk2 = 0;
                    List<TZorderlist> tzorderlists = newtorderlist.getZorderlist();
                    for (int y = 0; y < tzorderlists.size(); y++) {// 如果走到这里肯定是新增的票,所以tzorderlist的数量实在传入时已经封装好的,无需再次封装
                        TZorderlist newzlist = new TZorderlist();
                        BeanUtils.copyProperties(newzlist, tzorderlists.get(y));
                        newzlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        newzlist.setId(new TZorderlistId(new Long(y + 1), newtorderlist.getId().getOrderlistid(), oldorid, newtorderlist.getId().getIscenicid()));
                        yorderlistdao.save(newzlist);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
                        yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
                        yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
                        yzorderlist.setItickettypeid(newzlist.getItickettypeid());
                        yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
                        yzorderlist.setDtstartdate(newzlist.getDtstartdate());
                        yzorderlist.setDtenddate(newzlist.getDtenddate());
                        yzorderlist.setTripid(newzlist.getTripid());
                        yzorderlist.setIvenueid(newzlist.getIvenueid());
                        yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
                        yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
                        yzorderlist.setZpric(newzlist.getZpric());
                        yzorderlist.setZnumb(Math.abs(newzlist.getZnumb()));
                        yzorderlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZamnt()));
                        yzorderlist.setJsprice(Math.abs(newzlist.getJsprice()));
                        yorderlistdao.save(yzorderlist);
                    }
                }
                if (ortp.equals("02") && !oldmorder.getDdzt().equals("23") && Math.abs(sxf) > 0) {// 如果是退订单
                    totaltdsx += yorderlist.getMhandcharge();
                }
                yorder.setYhamnt(yorder.getYhamnt() + yorderlist.getYhamnt());
                yorder.setMont(yorderlist.getAmnt() + yorder.getMont());
                yorder.setZfmont(yorder.getZfmont() + yorderlist.getAmnt() - yorderlist.getYhamnt());
            }
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                yorder.setIsi(totalmonthjf);
                morder.setIsi(totalmonthjf);
                yorder.setIsh(totalyearjf);
                morder.setIsh(totalyearjf);
            }
            if (ortp.equals("02")) {// 退订
                morder.setDdzt("06");// 订单状态 06 退订已退款
                morder.setTpmont(Math.abs(totalmont));
                morder.setNotef("00");
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setTpsx(Math.abs(sxf));
                yorder.setNotef("00");
                yorder.setTpmont(Math.abs(totalmont));
                yorder.setDdzt("06");// 订单状态 06 退订已退款
                yorder.setTpdate(morder.getTpdate());
                yorder.setTpsx(Math.abs(sxf));
            } else if (ortp.equals("03")) {// 增量
                morder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                morder.setTpsx(0.0);
                morder.setTpmont(totalmont);
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setNotef("00");
                yorder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                yorder.setTpsx(0.0);
                yorder.setTpmont(totalmont);
                yorder.setTpdate(morder.getTpdate());
            }
            morder.setYhamnt(yorder.getYhamnt());
            morder.setZfmont(yorder.getZfmont());
            morder.setMont(yorder.getMont());
            ticketDao.update(morder);
            ticketDao.update(yorder);
            boolean b = true;
            if (ortp.equals("02")) {
                // 计算预付款,退订手续费tdsx一般都是负数
                if (totalmont > 0) {
                    b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), Math.abs(sxf), "03");
                } else {
                    b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), Math.abs(sxf), ortp);
                }
            } else {
                // 增量订单手续费传0过去
                if (totalmont > 0) {
                    b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), 0, ortp);
                } else {
                    b = minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), 0, "02");
                }
            }
            if (!b) {
                System.out.println("预付款金额异常");
                throw new RuntimeException("预付款金额异常");
            }
            if (morder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
//					Usernumjflist usernumjifenlist = new Usernumjflist();
//					usernumjifenlist.setId(new UsernumjflistId(morder.getOrid(), oldmorder.getZfusid()));
//					usernumjifenlist.setNid(BigDecimal.valueOf(set.getNid()));
//					usernumjifenlist.setJftp("04");//年
//					usernumjifenlist.setEtdt(Tools.getDays());
//					usernumjifenlist.setStdt(Tools.getDays());
//					usernumjifenlist.setStdt2(Tools.getDays());
//					usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
//					usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
//					usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
//					if (ortp.equals("02")) {
//						usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
//					} else {
//						usernumjifenlist.setJflb(BigDecimal.valueOf(1));
//					}
//					usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(totalyearjf)));
//					usernumjifenlist.setStdt(Tools.getDays());
//					usernumjifenlist.setZusid(morder.getZfusid());
//					usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
//					usernumjifenlist.setZusid(morder.getZfusid());
//					ticketService.save(usernumjifenlist);
                Usernumjflist usernumjifenlist = new Usernumjflist();
                usernumjifenlist.setId(new UsernumjflistId(morder.getOrid(), oldmorder.getZfusid()));
                usernumjifenlist.setNid(BigDecimal.valueOf(set.getNid()));
                usernumjifenlist.setJftp("03");//月
                usernumjifenlist.setEtdt(Tools.getDays());
                usernumjifenlist.setStdt(Tools.getDays());
                usernumjifenlist.setStdt2(Tools.getDays());
                usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
                usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
                usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
                if (ortp.equals("02")) {
                    usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
                } else {
                    usernumjifenlist.setJflb(BigDecimal.valueOf(1));
                }
                usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(totalmonthjf)));
                usernumjifenlist.setStdt(Tools.getDays());
                usernumjifenlist.setZusid(morder.getZfusid());
                usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
                usernumjifenlist.setZusid(morder.getZfusid());
                this.save(usernumjifenlist);
                Usernumjf userjf = null;
                if (usernumjifenlist.getJftp().equals("03")) {//月
                    String userjfJSON = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), morder.getZfusid(), new Long(1), Long.parseLong(iscenicid));
                    userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
                } else {
                    String userjfJSON = ticketService.getJifenByUser(map.get("startDate").toString().substring(0, 4) + "-01-01", map.get("endDate").toString().substring(0, 4) + "-12-31", morder.getZfusid(), new Long(2), Long.parseLong(iscenicid));
                    userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
                }
                if (usernumjifenlist.getJftp().equals("03")) {//月
                    userjf.setYpoint(userjf.getYpoint() + totalmonthjf);
                } else {
                    userjf.setYpoint(userjf.getYpoint() + totalyearjf);
                }
                this.update(userjf);
            }
        }
        return totaltdsx;
    }

    /**
     * 制作二维码
     *
     * @throws Exception
     * @throws Exception
     * @throws WriterException
     * @throws IOException
     */
    public String makeBarcode(String contents) throws Exception {
        String filename = UUID.randomUUID().toString().replace("-", "") + ".png";
        Tools.createDirs(Tools.replaceAll("/upfiles/" + Tools.getDays() + "/", "//", "/"));
        String urlpath = Tools.getRealPath() + Tools.replaceAll("/upfiles/" + Tools.getDays() + "/", "//", "/") + filename;
        QrCode qr = new QrCode();
        String ls_str = QrCode.EncodeCodeB64String(contents);
        BufferedImage image = qr.DecoderCodeB64Image(ls_str);
        File f = new File(urlpath);
        try {
            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/upfiles/" + Tools.getDays() + "/" + filename;
    }

    /*
	 * 修改租车或线路订单(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.order.service.iservice.IOrderService#orderEditCenter(java.
	 * util.List, java.lang.String[], java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
    public Map orderEditCenter(List<TOrderlist> orderlistInfo, String[] orids,
                               String orid, String stdt, String ibusiness, String usid,
                               String scenictype) throws Exception {
        Map returnmap = new HashMap();

        Long update_count = 0L; //修改的数量

        // 判断票务订单的数量控制
        // Map map = validateQuckorder(orderlistInfo, orid, usid);
        // Boolean result = (Boolean) map.get("result");
        // if (!result.booleanValue()) {
        // returnmap = map;
        // return returnmap;
        // }
        List tlist = showscenicidview(orid);
        // 判断修改后的订单金额与预付款
        if (tlist != null && tlist.size() > 0) {
            for (int x = 0; x < tlist.size(); x++) {
                TOrder td = (TOrder) tlist.get(x);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
                List chalist = new ArrayList();
                TOrderlist newtorderlist = null;
                TOrderlist oldtorderlist = null;
                TOrderlist ctorderlist = null;
                for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
                    oldtorderlist = new TOrderlist();
                    BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
                    for (int j = 0; j < orderlistInfo.size(); j++) {// 修改后的订单
                        newtorderlist = (TOrderlist) orderlistInfo.get(j);

                        if (newtorderlist.getOrderlistid().equals(
                                oldtorderlist.getOrderlistid())
                                && newtorderlist.getIscenicid().equals(
                                oldtorderlist.getIscenicid())) {
                            if (newtorderlist.getNumb().intValue() != oldtorderlist
                                    .getNumb().intValue()) {
                                ctorderlist = new TOrderlist();
                                BeanUtils.copyProperties(ctorderlist,
                                        oldtorderlist);

                                //线路才执行
                                if (scenictype.equals("07")) {
									/*if (oldtorderlist.getIcrowdkindid()==1L) {
										update_count = newtorderlist.getNumb()
										- oldtorderlist.getNumb();
									}
									if (oldtorderlist.getIcrowdkindid()==13L) {
										update_count = newtorderlist.getNumb()
										- oldtorderlist.getNumb();
									}*/
                                    update_count = newtorderlist.getNumb()
                                            - oldtorderlist.getNumb();
                                    //返回库存
                                    Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) get(
                                            Edmcrowdkindpricetab.class, oldtorderlist.getIcrowdkindpriceid());

                                    if (edmcrowdkindpricetab.getIcrowdkindid().intValue() == 1 && newtorderlist.getNumb().intValue() == 0) {
                                        returnmap.put("result", false);//成人数量不能为0
                                        returnmap.put("errtp", 124);// 成功
                                        return returnmap;
                                    }


                                    if (edmcrowdkindpricetab.getInote4().intValue() < update_count) {
                                        returnmap.put("result", false);//数量不足，增加失败
                                        returnmap.put("errtp", 123);// 成功
                                        return returnmap;
                                    }

                                    edmcrowdkindpricetab.setInote4(edmcrowdkindpricetab.getInote4() - update_count);
                                    //更新
                                    update(edmcrowdkindpricetab);

                                }

                                // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                                ctorderlist.setNumb(newtorderlist.getNumb()
                                        - oldtorderlist.getNumb());
                                ctorderlist.setId(new TOrderlistId(Long
                                        .parseLong(oldtorderlist
                                                .getOrderlistid()),
                                        oldtorderlist.getOrid(), Long
                                        .parseLong(iscenicid)));
                                ctorderlist
                                        .setAmnt(oldtorderlist.getPric()
                                                * (newtorderlist.getNumb() - oldtorderlist
                                                .getNumb()));
                                chalist.add(ctorderlist);

                            }

                        }
                    }
                }

                double amount = 0.0;
                if (chalist != null && chalist.size() > 0) {
                    // 遍历修改后和修改前订单对比出来的产品列表
                    for (int i = 0; i < chalist.size(); i++) {
                        ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
                        oldtorderlist = (TOrderlist) yorderlistdao.get(
                                TOrderlist.class, new TOrderlistId(
                                        Long.parseLong(ctorderlist
                                                .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        amount += oldtorderlist.getPric()
                                * ctorderlist.getNumb();// 叠加价格

                    }

					/*Vipbalance balance = (Vipbalance) torderdao.get(
							Vipbalance.class, usid);
					//  比较两个map中的值，计算是否可以修改 不可修改返回false
					if (balance.getPoint() < amount) {
						returnmap.put("result", false);
						returnmap.put("errtp", "2");// 余额不足
						return returnmap;
					}*/
                }

            }
        }

        // 修改订单数量
        if (tlist != null && tlist.size() > 0) {
            for (int x = 0; x < tlist.size(); x++) {
                TOrder td = (TOrder) tlist.get(x);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
                List chalist = new ArrayList();
                TOrderlist newtorderlist = null;
                TOrderlist oldtorderlist = null;
                TOrderlist ctorderlist = null;
                Double deposit = getdeposit_Line_Retal(orid, iscenicid);
                for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
                    oldtorderlist = new TOrderlist();
                    BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));

                    for (int j = 0; j < orderlistInfo.size(); j++) {// 修改后的订单
                        newtorderlist = (TOrderlist) orderlistInfo.get(j);
                        if (newtorderlist.getOrderlistid().equals(
                                oldtorderlist.getOrderlistid())
                                && newtorderlist.getIscenicid().equals(
                                oldtorderlist.getIscenicid())) {
                            if (newtorderlist.getNumb().intValue() != oldtorderlist
                                    .getNumb().intValue()) {
                                ctorderlist = new TOrderlist();
                                BeanUtils.copyProperties(ctorderlist,
                                        oldtorderlist);

                                // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                                ctorderlist.setNumb(newtorderlist.getNumb()
                                        - oldtorderlist.getNumb());
                                ctorderlist.setId(new TOrderlistId(Long
                                        .parseLong(oldtorderlist
                                                .getOrderlistid()),
                                        oldtorderlist.getOrid(), Long
                                        .parseLong(iscenicid)));
                                ctorderlist
                                        .setAmnt(oldtorderlist.getPric()
                                                * (newtorderlist.getNumb() - oldtorderlist
                                                .getNumb()));
                                chalist.add(ctorderlist);

                                // 修改数量 Torderlist
                                TOrderlist endtorderlist = (TOrderlist) torderlistdao
                                        .get(
                                                TOrderlist.class,
                                                new TOrderlistId(
                                                        Long
                                                                .parseLong(oldtorderlist
                                                                        .getOrderlistid()),
                                                        oldtorderlist.getOrid(),
                                                        Long
                                                                .parseLong(iscenicid)));
                                endtorderlist.setNumb(newtorderlist.getNumb());
                                endtorderlist.setAmnt(new Double(oldtorderlist
                                        .getPric()
                                        * newtorderlist.getNumb()));
                                this.update(endtorderlist);

                                // 修改 TZorderlist
                                String sql = " from TZorderlist where id.orderlistid="
                                        + Long.parseLong(oldtorderlist
                                        .getOrderlistid())
                                        + " and id.orid='"
                                        + oldtorderlist.getOrid()
                                        + "' and id.iscenicid="
                                        + Long.parseLong(iscenicid);
                                TZorderlist endtzorder = (TZorderlist) this
                                        .find(sql).get(0);
                                endtzorder.setZnumb(newtorderlist.getNumb());
                                endtzorder.setZamnt(new Double(endtzorder
                                        .getZnumb()
                                        / oldtorderlist.getNumb()
                                        * newtorderlist.getNumb()));
                                this.update(endtzorder);

                                // 修改Yorderlist
                                YOrderlist endorderlist = (YOrderlist) torderlistdao
                                        .get(
                                                YOrderlist.class,
                                                new YOrderlistId(
                                                        Long
                                                                .parseLong(oldtorderlist
                                                                        .getOrderlistid()),
                                                        oldtorderlist.getOrid(),
                                                        Long
                                                                .parseLong(iscenicid)));
                                endorderlist.setNumb(newtorderlist.getNumb());
                                endorderlist.setAmnt(new Double(oldtorderlist
                                        .getPric()
                                        * newtorderlist.getNumb()));
                                this.update(endorderlist);

                                // 修改 YZorderlist
                                String hql = " from YZorderlist where id.orderlistid="
                                        + Long.parseLong(oldtorderlist
                                        .getOrderlistid())
                                        + " and id.orid='"
                                        + oldtorderlist.getOrid()
                                        + "' and id.iscenicid="
                                        + Long.parseLong(iscenicid);
                                YZorderlist endyzorder = (YZorderlist) this
                                        .find(hql).get(0);
                                endyzorder.setZnumb(newtorderlist.getNumb());
                                endyzorder.setZamnt(new Double(endyzorder
                                        .getZnumb()
                                        / oldtorderlist.getNumb()
                                        * newtorderlist.getNumb()));
                                this.update(endyzorder);

                            }

                        }
                    }
                }

                double amount = 0.0;
                int maxnum = getTorderlistMaxnum_Line_Retal(orid, iscenicid);
                if (chalist != null && chalist.size() > 0) {
                    // 遍历修改后和修改前订单对比出来的产品列表
                    for (int i = 0; i < chalist.size(); i++) {
                        ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
                        oldtorderlist = (TOrderlist) yorderlistdao.get(
                                TOrderlist.class, new TOrderlistId(
                                        Long.parseLong(ctorderlist
                                                .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        amount += oldtorderlist.getPric()
                                * ctorderlist.getNumb();// 叠加价格

                        // 修改 TOrder
                        TOrder endtorder = (TOrder) this.get(TOrder.class,
                                new TOrderId(orid, Long.parseLong(iscenicid)));
                        endtorder.setMont(new Double(endtorder.getMont()
                                + oldtorderlist.getPric()
                                * ctorderlist.getNumb()));
                        if (endtorder.getNoted() != null
                                && endtorder.getNoted().equals("02")) {// 定金支付
                            endtorder.setZfmont(maxnum * deposit);
                        } else {
                            endtorder.setZfmont(new Double(endtorder
                                    .getZfmont()
                                    + oldtorderlist.getPric()
                                    * ctorderlist.getNumb()));
                        }
                        if (endtorder.getMont() == 0) {// 若酒店的产品数量都为0时，即Torder中酒店服务商的付款金额为0时，修改其订单状态
                            if (endtorder.getDdzt() != "00") {
                                endtorder.setDdzt("00");
                            }
                        }
                        this.update(endtorder);

                        // 修改 YOrder
                        YOrder endyorder = (YOrder) this.get(YOrder.class,
                                new YOrderId(orid, Long.parseLong(iscenicid)));
                        endyorder.setMont(new Double(endyorder.getMont()
                                + oldtorderlist.getPric()
                                * ctorderlist.getNumb()));
                        // if(endtorder.getNoted()!=null&&endtorder.getNoted().equals("02")){//定金支付
                        // endyorder.setZfmont(endtorder.getZfmont());
                        // }else{
                        endyorder.setZfmont(new Double(endyorder.getZfmont()
                                + oldtorderlist.getPric()
                                * ctorderlist.getNumb()));
                        // }
                        this.update(endyorder);
                    }

                    MOrder moder = (MOrder) this.get(MOrder.class, orid);
                    moder.setMont(moder.getMont() + amount);
                    if (moder.getNoted() != null
                            && moder.getNoted().equals("02")) {
                        String hsql = "select sum(zfmont) from TOrder where id.orid='"
                                + orid + "' and scenictype='" + scenictype + "' ";
                        List hslist = this.find(hsql);
                        Double zfmontsum = (Double) hslist.get(0);
                        moder.setZfmont(zfmontsum);
                    } else {
                        moder.setZfmont(moder.getZfmont() + amount);
                    }
                    if (moder.getDdzt() != "00") {
                        String hsql = "select sum(mont) from TOrder where id.orid='"
                                + orid + "' and scenictype='" + scenictype + "' ";
                        List hslist = this.find(hsql);
                        Double montsum = (Double) hslist.get(0);
                        if (montsum == 0) {
                            moder.setDdzt("00");
                        }
                    }
                    this.update(moder);
                }
            }
        }

        // 删除 订单中数量为0的数据
        if (tlist != null && tlist.size() > 0) {
            for (int y = 0; y < tlist.size(); y++) {
                TOrder td = (TOrder) tlist.get(y);
                String iscenicid = td.getId().getIscenicid().toString();

                // 获取原始订单
                List list = torderlistdao.getTOrderListList(orid, iscenicid);
                TOrderlist oldlist = null;
                for (int j = 0; j < list.size(); j++) {
                    oldlist = new TOrderlist();
                    BeanUtils.populate(oldlist, (Map) list.get(j));
                    if (oldlist.getNumb() <= 0) {
                        String sql = " from TZorderlist where id.orderlistid="
                                + Long.parseLong(oldlist.getOrderlistid())
                                + " and id.orid='" + oldlist.getOrid()
                                + "' and id.iscenicid="
                                + Long.parseLong(iscenicid);
                        TZorderlist endtzorder = (TZorderlist) this.find(sql)
                                .get(0);
                        this.delete(endtzorder);

                        TOrderlist tt = (TOrderlist) this.get(TOrderlist.class,
                                new TOrderlistId(Long.parseLong(oldlist
                                        .getOrderlistid()), orid, Long
                                        .parseLong(iscenicid)));
                        this.delete(tt);
                    }
                }
                this.updateOrderZtByZeroProduct(orid,
                        Long.parseLong(iscenicid), "27");
            }
            this.updateMOrderStatusByZeroProduct(orid, "27");
        }

        returnmap.put("result", true);
        returnmap.put("errtp", null);// 成功
        return returnmap;
    }

    public int getTorderlistMaxnum_Line_Retal(String orid, String iscenicid) {
        List list = this
                .find("select sum(tl.numb) from TOrderlist tl where tl.id.orid='"
                        + orid
                        + "' and tl.id.iscenicid="
                        + Long.parseLong(iscenicid));
        if (list != null && !list.isEmpty()) {
            int num = Integer.parseInt(list.get(0).toString());
            return num;
        }
        return 0;
    }

    public double getdeposit_Line_Retal(String orid, String iscenicid) {
        TOrder tOrder = (TOrder) this.get(TOrder.class, new TOrderId(orid, Long
                .parseLong(iscenicid)));
        if (tOrder.getNoted() != null && tOrder.getNoted().equals("02")) {// 定金支付
            List list = this
                    .find("select sum(tl.numb) from TOrderlist tl where tl.id.orid='"
                            + orid
                            + "' and tl.id.iscenicid="
                            + Long.parseLong(iscenicid));
            if (list != null && !list.isEmpty()) {
                int num = Integer.parseInt(list.get(0).toString());
                return tOrder.getZfmont() / num;
            }
        }
        return 0.0;
    }

    public double getdeposit(String orid, String iscenicid) {
        TOrder tOrder = (TOrder) this.get(TOrder.class, new TOrderId(orid, Long
                .parseLong(iscenicid)));
        if (tOrder.getNoted() != null && tOrder.getNoted().equals("02")) {// 定金支付
            List list = this
                    .find("select max(tl.numb) from TOrderlist tl where tl.id.orid='"
                            + orid
                            + "' and tl.id.iscenicid="
                            + Long.parseLong(iscenicid));
            if (list != null && !list.isEmpty()) {
                int num = Integer.parseInt(list.get(0).toString());
                return tOrder.getZfmont() / num;
            }
        }
        return 0.0;
    }

    public int getTorderlistMaxnum(String orid, String iscenicid) {
        List list = this
                .find("select max(tl.numb) from TOrderlist tl where tl.id.orid='"
                        + orid
                        + "' and tl.id.iscenicid="
                        + Long.parseLong(iscenicid));
        if (list != null && !list.isEmpty()) {
            int num = Integer.parseInt(list.get(0).toString());
            return num;
        }
        return 0;
    }

    public boolean updateNotea(String orid, String notea) {
        return this.morderdao.updateNotea(orid, notea);
    }

    public List getYorderlists(String orid, String iscenicid) {
        String hsql = "from YOrderlist y where y.id.orid = '" + orid + "' and y.id.iscenicid =" + Long.parseLong(iscenicid);
        return this.find(hsql);
    }

    public float getsumjifen(String usid) {
        // 计算当前余额
        Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, usid);

        if (vipbalance == null) {
            return 0F;
        }
        // 默认查询出来的list里存放的是一个Object数组，但是在这里list里存放的不再是默认的Object数组了，而是Map集合了
        float rc_float = vipbalance.getPoint().floatValue();
        float v2 = 0;

        // 除去提现未审核的金额
        List list = this.find("from Yfktoxj where ddzt='97' and usid='" + usid + "'");
        if (list != null && list.size() > 0) {
            list = this.find("select sum(mont) as mont from Yfktoxj where ddzt='97' and usid='" + usid + "'");
            v2 = (list != null && list.size() > 0 ? ((Double) list.get(0)).floatValue() : new Float(0));
        }

        BigDecimal b1 = new BigDecimal(Float.toString(rc_float));

        BigDecimal b2 = new BigDecimal(Float.toString(v2));

        return b1.subtract(b2).floatValue();

    }

    public void changDdzt(String orda, String orti) {
        try {
			String hsql = "select new map(m.orid as orid) from MOrder m,TOrder t,Custom c " +
			        "where c.usid=m.usid and c.ttlb != '99' and m.orda='" + orda + "' and m.orti<'" + orti + "' " +
			        "and m.ddzt='00' and m.orid = t.id.orid and t.orfl in('01','02') ";
			List mlist = this.find(hsql);
			if (mlist != null && !mlist.isEmpty()) {
			    for (int i = 0; i < mlist.size(); i++) {
			        Map mmap = (Map) mlist.get(i);
			        MOrder morder = (MOrder) this.get(MOrder.class, mmap.get("orid").toString());
			        List tlist = this.getTOrderList(morder.getOrid());
			        if (tlist != null && !tlist.isEmpty()) {
			            for (int j = 0; j < tlist.size(); j++) {
			                Map map = (Map) tlist.get(j);
			                String iscenicid = map.get("iscenicid").toString();
			                String orid = map.get("orid").toString();
			                TOrder torder = (TOrder) torderlistdao.get(TOrder.class,
			                        new TOrderId(orid, Long.parseLong(iscenicid)));
			                if (torder != null) {
			                    torder.setDdzt("98");
			                    torderlistdao.update(torder);
			                }
			                YOrder yorder = (YOrder) torderlistdao.get(YOrder.class,
			                        new YOrderId(orid, Long.parseLong(iscenicid)));
			                if (yorder != null) {
			                    yorder.setDdzt("98");
			                    torderlistdao.update(yorder);
			                }
			                //通知结算系统订单撤销
			                LOGGER.info("通知结算系统撤销订单{}",orid);
			                HqytClient client = new HqytClient();
			                Map<String, String> merchant = CommonUtil.findMerchant();
			                SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
			                sortMap.put("merchantId", merchant.get("merchantId"));
			                sortMap.put("outTradeNo", morder.getOrid());
			                sortMap.put("cancelReason", "下错单");
			                //请求字符窜
			                StringBuffer sb = new StringBuffer();
			                Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
			                while (iterator.hasNext()){
			                    Map.Entry<String, Object> next = iterator.next();
			                    sb.append(next.getKey());
			                    sb.append("=");
			                    sb.append(next.getValue());
			                    sb.append("&");
			                }
			                String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
			                sb.append("sign="+sign2);
			                try{
			                    JSONObject refundBill = client.cancelOrder(sb.toString());
			                    if(refundBill != null){
			                        LOGGER.info("未支付订单，通知结算系统撤销订单成功");
			                    }
			                }catch (Exception e){
			                	LOGGER.info("取消预定失败:"+e.getMessage());
			                    throw new RuntimeException("取消预定失败:"+e.getMessage());
			                }
			                List tOrderListList = torderlistdao.getTOrderListList(orid, iscenicid);
			                for (Object object : tOrderListList) {
			                	 Map map1 = (Map)object;
			                	 Object object2 = map1.get("timeid");
			                	 //分时预约 归还库存
			                	 Object object3 = map1.get("numb");
			                	 if(object2 != null) {
			                		Long timeId = Long.parseLong(object2.toString());
			                		String hql2 = "from TimeSharingTicketTab where id = "+timeId;
//			                		TimeSharingService _TimeSharingService = (TimeSharingService)SpringUtil.getBean("timeSharingService");
			             			List find2 = this.find(hql2);
			             			if(find2 != null && find2.size()>0) {
			             				TimeSharingTicketTab _SharingTicketTab =(TimeSharingTicketTab)find2.get(0);
			             				ticketService.UpdateStock(timeId, _SharingTicketTab.getProductId(), Integer.parseInt(object3.toString()), "add");
			             			}
			                	 }
								
							}
			            }
			        }
			        morder.setDdzt("98");
			        torderlistdao.update(morder);
			        
			        ticketService.deleteStockDetails(morder.getOrid(), null, null, null);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
     * 需要核销的电商订单、扫码购订单、电商APP订单
     */
    public List<MOrder> findConsumeOrder(){
        String today = Tools.getDate(Tools.getDays(), -1);
        //String hsq = "from MOrder where ddzt = '11' and zfmont > 0 and isb <> 1 and noteh is not null and ((substr(orid,9,3) = '000') or (substr(orid,9,3) = '236') or (substr(orid,9,3) = '777')) and isd < 5 ";
        String hsq = "from MOrder where ddzt = '11'  and isb <> 1 and noteh is not null and (((substr(orid,9,3) = '000' or substr(orid,9,3) = '236' or (substr(orid,9,3) = '777' and (ordersource!='lykgp' or ordersource is null ))) and zfmont > 0) or (substr(orid,9,3) = '777' and ordersource='lykgp' and zfmont=0)) and isd < 5 ";
        List<MOrder> list=morderdao.find(hsq);
        List lists =morderdao.getNeedConsumeMOrderList(today);
        if(lists!=null&&lists.size()>0){
            for(int i=0;i<lists.size();i++){
                Map map=(Map)lists.get(i);
                String orid=map.get("orid").toString();
                String hsql="from MOrder where orid='"+orid+"' ";
                List<MOrder> list1= morderdao.find(hsql);
                list.addAll(list1);
            }

        }
        return list;
    }

    /**
     * 需要核销的自助机订单
     */
    public List<LOrder> findConsumeAutoSaleOrder(){
        String today = Tools.getDate(Tools.getDays(), -1);
        String hsq = "from LOrder where ddzt = '11' and zfmont > 0 and  (isb is null or isb <> 1) and noteh is not null " +
                "and (isd is null or isd < 5) ";
        List<LOrder> list=morderdao.find(hsq);
        List lists =morderdao.getNeedConsumeLOrderList(today);
        if(lists!=null&&lists.size()>0){
            for(int i=0;i<lists.size();i++){
                Map map=(Map)lists.get(i);
                String orid=map.get("orid").toString();
                String hsql="from LOrder where id.orid='"+orid+"' ";
                List<LOrder> list1= morderdao.find(hsql);
                list.addAll(list1);
            }
        }
        return list;
    }
	
	public List<MOrder> findRefundOrder(){
		//全退订
    	String hsql = "from MOrder where ddzt in ('27','02') and ortp = '01' and zfmont > 0 and noteh is not null and substr(orid,9,3) in ('000','236') and srid is not null order by orid";
    	//已退订订单
    	List<MOrder> MOrders = morderdao.find(hsql);
    	List<MOrder> refundMOrders = new ArrayList<MOrder>();
    	//新已退订订单，退订订单号
    	for (MOrder mOrder : MOrders) {
    		String hsqls = "from MOrder where (notec is null or notec = '0')  and srid = '"+mOrder.getOrid()+"'";
    		List<MOrder> find = morderdao.find(hsqls);
    		if(find != null && find.size()>0) {
    			refundMOrders.add(find.get(0));
    		}
		}
    	return refundMOrders;
    }
	
	public List<MOrder> findRefundOrder1(String srid){
		//根据原订单号，查询退款失败的订单
    	String hsql = "from MOrder where notec='0' and srid = '"+srid+"'";
    	//已退订订单
    	List<MOrder> MOrders = morderdao.find(hsql);
    	
    	return null;
    }

	public String findRefundOrderId(String orid) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void saveStock(TimeSharingTicketTab tst) {
		torderlistdao.saveOrUpdate(tst);
		
	}


    /**
     * 原退订方法
     */
    public Map editOrderCenterOld(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid)
            throws Exception {
        Map returnmap = new HashMap();
        try {
            Custom custom = (Custom) morderdao.get(Custom.class, usid);
            MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
            Map newmap = null;
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfo(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrder(oldorid, iscenicid, orderlistInfo);
            //计算优惠
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && addsonProductlist.size() > 0 && oldaddlist != null && oldaddlist.size() > 0) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue()) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (addsonProductlist != null) {
                oldaddlist.addAll(addsonProductlist);
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())
                                    && compare.getPrice() == compare1.getPrice()) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())
                                    && compare.getPrice() == compare1.getPrice()) {
                                compare.setNumb(compare1.getNumb() + compare.getNumb());
                                break;
                            }
                        }
                    }
                }
            }
            // 原订单修改后的差异集合
            List chalist = (List) oldmap.get("chaticket");
            List addlist = new ArrayList();
            List backlist = new ArrayList();
            for (int i = 0; i < chalist.size(); i++) {
                TOrderlist orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }

            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();

                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    String groupno = ticketDao.searchJgfz(custom.getUsid(), ticket.getIscenicid());

                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                /*	pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
					kindprice = (Edmcrowdkindpricetab) pricelist.get(0);*/
                    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                    newproduct.setDtstartdate(stdt);
					/*ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));*/
                    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                    newproduct.setPric(kindprice.getMactualsaleprice());
                    newproduct.setJsprice(kindprice.getJsprice());
                    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                    newproduct.setYhnumb(0l);
                    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                    newproduct.setYhamnt(0.0);
                    List sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            newproduct.setWharfdate(control.getStdata());
                            newproduct.setPdctrolid(control.getProductcontrolid());
                            Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString());
                            tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                            tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                            tzorderlist.setTripid(control.getTripid());
                            tzorderlist.setIvenueid(prd.getIvenueid());
                            tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                        } else {
                            tzorderlist.setDtstartdate(stdt + " 00:00:00");
                            tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                        }
                        tzorderlist.setZpric(pricepost.getMactualsaleprice());
                        tzorderlist.setJsprice(pricepost.getJsprice());
                        tzorderlist.setZnumb(pricepost.getNumb() * newproduct.getNumb());
                        tzorderlist.setZyhnumb(0l);
                        tzorderlist.setZyhamnt(0.0);
                        tzorderlist.setZamnt(tzorderlist.getZnumb() * pricepost.getMactualsaleprice());
                        newproduct.getZorderlist().add(tzorderlist);
                    }
                    addlist.add(newproduct);
                }
                for (int j = 0; j < addlist.size(); j++) {// 原订单新增的票
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = null;
                    if (orderlist.getOrderlistid() != null && !orderlist.getOrderlistid().equals("")) {
                        tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    } else {
                        tzlist = orderlist.getZorderlist();
                    }
                    orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        List<Edmticketcomposepricetab> sprices = yorderlistdao.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    if (orderlist.getZorderlist() == null) {
                        orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    }
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        Edmtickettypetab ticket = (Edmtickettypetab) yorderlistdao.get(Edmtickettypetab.class, newz.getIztickettypeid());
                        if (ticket.getBycategorytype().equals("0003")) {
                            orderlist.setHasraft(1);
                            Productcontrol cntrol = ticketDao.getNumberControl(zlist.getTripid(), zlist.getIvenueid(), zlist.getIvenueareaid(), zlist.getDtstartdate().substring(0, 10));
                            orderlist.setPdctrolid(cntrol.getProductcontrolid());
                            orderlist.setWharfdate(cntrol.getStdata());
                        }
                        List<Edmticketcomposepricetab> sprices = yorderlistdao.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();

                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    String groupno = ticketDao.searchJgfz(custom.getUsid(), ticket.getIscenicid());

                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, groupno);
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                    newproduct.setDtstartdate(stdt);
//					ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                    newproduct.setPric(kindprice.getMactualsaleprice());
                    newproduct.setJsprice(kindprice.getJsprice());
                    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                    newproduct.setYhnumb(0l);
                    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                    newproduct.setYhamnt(0.0);
                    List sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString());
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            newproduct.setPdctrolid(control.getProductcontrolid());
                            tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                            tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                            tzorderlist.setTripid(control.getTripid());
                            tzorderlist.setIvenueid(prd.getIvenueid());
                            tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                        } else {
                            tzorderlist.setDtstartdate(stdt + " 00:00:00");
                            tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                        }
                        tzorderlist.setZpric(pricepost.getMactualsaleprice());
                        tzorderlist.setJsprice(pricepost.getJsprice());
                        tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
                        tzorderlist.setZyhnumb(0l);
                        tzorderlist.setZyhamnt(0.0);
                        tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
                        newproduct.getZorderlist().add(tzorderlist);
                    }
                    addlist.add(newproduct);
                }
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
            }
            double totalmont = 0;
            double tdmont = 0;
            boolean bs = false;
            double yhmoney = 0;
            double ysmoney = 0;  //原始订单优惠
            List fwyhlist = new ArrayList();  //服务商优惠列表
            //判断服务商优惠
            Edpofferschemetab pscheme = this.ticketDao.checkEdpschemet(Long.parseLong(iscenicid), stdt, Long.parseLong(ibusiness));

            if (pscheme != null) {
                //服务商优惠在这里进行判断，重载calculateAddMont方法。
                bs = true;
                List schemelist = new ArrayList();

                Long xsnums = 0L;
                Double xsmoney = 0D; //实际销售总金额
                Double ydmoney = 0D; //原订单销售总金额
                Double tdmoney = 0D; //退订金额
                List torderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
                //获取修改后此订单某服务商的销售信息
                //原订单
                if (torderlists != null && torderlists.size() > 0) {
                    for (int i = 0; i < torderlists.size(); i++) {
                        TOrderlist torder = (TOrderlist) torderlists.get(i);

                        TOrderlist newtorderlist = new TOrderlist();
                        BeanUtils.copyProperties(newtorderlist, torder);

                        xsnums += torder.getNumb();
                        xsmoney += torder.getAmnt();
                        ydmoney += torder.getAmnt();
                        ysmoney += torder.getYhamnt();

                        schemelist.add(newtorderlist);
                    }
                }
                //新增订单
                if (addlist != null && addlist.size() > 0) {
                    for (int x = 0; x < addlist.size(); x++) {
                        TOrderlist torderlist = (TOrderlist) addlist.get(x);
                        boolean b = false;
                        for (int y = 0; y < schemelist.size(); y++) {
                            TOrderlist order = (TOrderlist) schemelist.get(y);
                            if (order != null && order.getItickettypeid() == torderlist.getItickettypeid() && order.getIcrowdkindpriceid() == torderlist.getIcrowdkindpriceid()) {
                                b = true;
                                order.setNumb(order.getNumb() + torderlist.getNumb());
                                order.setYhnumb(order.getYhnumb() + torderlist.getYhnumb());
                                order.setAmnt(order.getAmnt() + torderlist.getAmnt());
                                order.setYhamnt(order.getYhamnt() + torderlist.getYhamnt());
                                break;
                            }
                        }
                        torderlist.setYhamnt(0D);
                        torderlist.setYhnumb(0L);

                        xsnums += torderlist.getNumb();
                        xsmoney += torderlist.getAmnt();
                        if (!b) {
                            schemelist.add(torderlist);
                        }
                    }
                }
                //退订订单
                if (backlist != null && backlist.size() > 0) {
                    for (int x = 0; x < backlist.size(); x++) {
                        TOrderlist torderlist = (TOrderlist) backlist.get(x);

                        for (int y = 0; y < schemelist.size(); y++) {
                            TOrderlist order = (TOrderlist) schemelist.get(y);

                            if (order.getItickettypeid().longValue() == torderlist.getItickettypeid().longValue() && order.getIcrowdkindpriceid().longValue() == torderlist.getIcrowdkindpriceid().longValue()) {
                                if (Math.abs(order.getNumb()) == Math.abs(torderlist.getNumb())) {
                                    schemelist.remove(order);
                                } else {
                                    order.setNumb(order.getNumb() + torderlist.getNumb());
                                    order.setAmnt(order.getAmnt() + torderlist.getAmnt());
                                }

                                break;
                            }
                        }

                        xsnums += torderlist.getNumb();
                        tdmoney += torderlist.getAmnt();
                        xsmoney += torderlist.getAmnt();
                    }
                }

                //优惠数量
                Long yhnum = (xsnums / pscheme.getImultiples()) * pscheme.getIoffernum();
                if (yhnum > 0) {
                    String yhlx = "0";//获取优惠方式  0 -最高价  1-最低价
                    Hotelprovider hotel = (Hotelprovider) this.ticketDao.get(Hotelprovider.class, Long.parseLong(iscenicid));
                    if (hotel != null && hotel.getInoteger8() != null) {
                        yhlx = hotel.getInoteger8().toString();
                    }

                    //最高价优惠 排序
                    if (yhlx.equals("0")) {
                        Collections.sort(schemelist, new Comparator<TOrderlist>() {
                            public int compare(TOrderlist o1, TOrderlist o2) {
                                double price1 = o1.getPric();
                                double price2 = o2.getPric();
                                if (price2 > price1) {
                                    return 1;
                                } else if (price2 == price1) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        });
                    }
                    //最低价优惠 排序
                    if (yhlx.equals("1")) {
                        Collections.sort(schemelist, new Comparator<TOrderlist>() {
                            public int compare(TOrderlist o1, TOrderlist o2) {
                                double price1 = o1.getPric();
                                double price2 = o2.getPric();
                                if (price1 > price2) {
                                    return 1;
                                } else if (price1 == price2) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        });
                    }

                    for (int j = 0; j < schemelist.size(); j++) {
                        TOrderlist t = (TOrderlist) schemelist.get(j);

                        if (yhnum > 0) {
                            if (yhnum > t.getNumb()) {
                                t.setYhamnt(t.getNumb() * t.getPric());
                                t.setYhnumb(t.getNumb());
                                t.setIoffersschemeid(pscheme.getIoffersschemeid());

                                yhmoney += t.getNumb() * t.getPric();
                                yhnum -= t.getNumb();

                            } else {
                                t.setYhamnt(yhnum * t.getPric());
                                t.setYhnumb(yhnum);
                                t.setIoffersschemeid(pscheme.getIoffersschemeid());

                                yhmoney += yhnum * t.getPric();
                                yhnum = 0L;

                            }

                            fwyhlist.add(t);  //服务商优惠列表
                        }
                    }

                    //新增订单的优惠信息
                    if (addlist != null && addlist.size() > 0) {
                        for (int x = 0; x < addlist.size(); x++) {
                            TOrderlist torderlist = (TOrderlist) addlist.get(x);
                            for (int y = 0; y < fwyhlist.size(); y++) {
                                TOrderlist order = (TOrderlist) schemelist.get(y);
                                if (order != null && order.getItickettypeid().longValue() == torderlist.getItickettypeid().longValue() && order.getIcrowdkindpriceid().longValue() == torderlist.getIcrowdkindpriceid().longValue()) {
                                    torderlist.setYhnumb(order.getYhnumb());
                                    torderlist.setYhamnt(order.getYhamnt());
                                    torderlist.setIoffersschemeid(pscheme.getIoffersschemeid());
                                }
                            }

                        }
                    }

                    // 计算出需要增加的金额
                    totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                    // 需要退订的钱
                    tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));

                    if ((yhmoney - ysmoney) < 0) {
                        tdmont = tdmont - (yhmoney - ysmoney);
                    } else if ((yhmoney - ysmoney) > 0) {
                        totalmont = totalmont - (yhmoney - ysmoney);
                    }

                } else {
                    // 计算出需要增加的金额
                    totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                    // 需要退订的钱
                    tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));

                    if ((yhmoney - ysmoney) < 0) {
                        tdmont = tdmont - (yhmoney - ysmoney);
                    } else if ((yhmoney - ysmoney) > 0) {
                        totalmont = totalmont - (yhmoney - ysmoney);
                    }

                }

            } else {
                // 计算出需要增加的金额
                totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
                // 需要退订的钱
                tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
            }

            double totalyhmont = 0.0;

            if (bs) {//服务商优惠金额  lijingrui
                totalyhmont = yhmoney - ysmoney;  //优惠金额变动
            } else {
                for (int x = 0; x < addlist.size(); x++) {
                    TOrderlist orderlist = (TOrderlist) addlist.get(x);
                    totalyhmont += orderlist.getPric() * orderlist.getYhnumb();
                }
                for (int x = 0; x < backlist.size(); x++) {
                    TOrderlist orderlist = (TOrderlist) backlist.get(x);
                    totalyhmont -= orderlist.getPric() * orderlist.getYhnumb();
                }
            }

            double userMoney = CommonUtil.getUserMoney(oldmorder.getZfusid());

            //比较两个map中的值，计算是否可以修改 不可修改返回false
            if (tdmont > 0) {
                if (userMoney < Math.abs(tdsx) + totalmont - tdmont) {
                    returnmap.put("result", false);
                    returnmap.put("errtp", "2");// 余额不足
                    return returnmap;
                }
            } else {
                if (userMoney < Math.abs(tdsx) + totalmont + tdmont) {
                    returnmap.put("result", false);
                    returnmap.put("errtp", "2");// 余额不足
                    return returnmap;
                }
            }

            List charraftlist = null;
            List newdaylist = null;
            List olddaylist = null;
            int istoprat = 0;
            if (oldmap != null) {
                charraftlist = (List) oldmap.get("charraft");
                olddaylist = (List) oldmap.get("chaday");
            }
            List newraft = null;
            if (newmap != null) {
                newdaylist = (List) newmap.get("daylist");
                newraft = (List) newmap.get("raftlist");
            }
            long totalmonthjf = 0;
            long totalyearjf = 0;
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                long addmonthjf = 0;
                long addyearjf = 0;
                Numjifenset set = ticketService.getNumjifenset(iscenicid);
                if (addlist != null && addlist.size() > 0) {
                    for (int i = 0; i < addlist.size(); i++) {
                        TOrderlist torderlist = (TOrderlist) addlist.get(i);
                        TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                                torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                        Numjifensetlist detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), custom.getIbusinessid(),
                                torderlist.getDtstartdate());
                        if (detail != null) {
                            if (detail.getXffs().equals("03")) {// 月积分
                                long jf = 0;
                                if (old != null) {// 如果订单里面没有此产品
                                    if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                    }
                                    jf = jf - old.getIsi();
                                } else {
                                    if (torderlist.getNumb() % detail.getIint3() > 0) {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4();
                                    }
                                }
                                addmonthjf += jf;
                            } else {
                                long jf = 0;
                                if (old != null) {// 如果订单里面没有此产品
                                    if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                    }
                                    jf = jf - old.getIsh();
                                } else {
                                    if (torderlist.getNumb() % detail.getIint3() > 0) {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                    } else {
                                        jf = torderlist.getNumb() / detail.getIint3() * detail.getIint4();
                                    }
                                }
                                addyearjf += jf;
                            }
                        }
                    }
                }
                long tdmonthjf = 0;
                long tdyearjf = 0;
                if (backlist != null && backlist.size() > 0) {
                    for (int i = 0; i < backlist.size(); i++) {
                        TOrderlist torderlist = (TOrderlist) backlist.get(i);
                        TOrderlist old = (TOrderlist) torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                                torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                        Numjifensetlist detail = ticketService.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), custom.getIbusinessid(),
                                torderlist.getDtstartdate());
                        if (detail != null) {
                            if (detail.getXffs().equals("03")) {// 月积分
                                long jf = 0;
                                if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                } else {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                }
                                jf = jf - old.getIsi();// 一定会是0或者负数
                                tdmonthjf += jf;
                            } else {
                                long jf = 0;
                                if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4() + detail.getIint4();
                                } else {
                                    jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3() * detail.getIint4();
                                }
                                jf = jf - old.getIsh();// 一定会是0或者负数
                                tdyearjf += jf;
                            }
                        }
                    }
                }
                totalmonthjf = addmonthjf - Math.abs(tdmonthjf);
                totalyearjf = addyearjf - Math.abs(tdyearjf);
                TOrder oldtorder = (TOrder) get(TOrder.class, new TOrderId(oldmorder.getOrid(), Long.parseLong(iscenicid)));
                Map map = DateUtils.getMonthDate(Integer.parseInt(oldtorder.getDtstartdate().substring(0, 4)), Integer.parseInt(oldtorder.getDtstartdate().substring(5, 7)));
                String usermonthjifenJSON = ticketService.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), oldmorder.getZfusid(), 1l, Long.parseLong(iscenicid));
                Usernumjf usermonthjifen = JSON.parseObject(usermonthjifenJSON, Usernumjf.class);
                
                
                String useryearjifenJSON = ticketService.getJifenByUser(map.get("startDate").toString().substring(0, 4) + "-01-01", map.get("endDate").toString().substring(0, 4) + "-12-31", oldmorder.getZfusid(), 2l, Long.parseLong(iscenicid));
                Usernumjf useryearjifen = JSON.parseObject(useryearjifenJSON, Usernumjf.class);
                
                
                if (totalmonthjf > 0) {
                    if (totalmonthjf > usermonthjifen.getPoint().intValue() - usermonthjifen.getYpoint().intValue()) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "7");// 积分不足
                        returnmap.put("ordernum", totalmonthjf);
                        returnmap.put("usernum", usermonthjifen.getPoint().intValue() - usermonthjifen.getYpoint().intValue());
                        returnmap.put("numtype", "03");//年
                        return returnmap;
                    }
                }
                if (totalyearjf > 0) {
                    if (totalyearjf > useryearjifen.getPoint().intValue() - useryearjifen.getYpoint().intValue()) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "7");// 积分不足
                        returnmap.put("ordernum", totalyearjf);
                        returnmap.put("usernum", useryearjifen.getPoint().intValue() - useryearjifen.getYpoint().intValue());
                        returnmap.put("numtype", "04");//年
                        return returnmap;
                    }
                }

            }
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);
            Sysparv5 maxcredit = (Sysparv5) morderdao.get(Sysparv5.class, new Sysparv5Id("YDJF", "07"));
            // 根据订单用户获取需要消耗信用度的用户
            String credusid = getFsusid(oldmorder.getUsid());
            // 计算信用度
            Long creditnum = calculateCredit(credusid, charraftlist, iscenicid);
            Credit credit = (Credit) morderdao.get(Credit.class, credusid);
            boolean ispositive = queryPositiveraft(charraftlist, iscenicid);
            if (!oldmorder.getDdzt().equals("23")) {
                if (credit != null) {
                    if (credit.getCreditnumb().intValue() + creditnum.intValue() > Integer.parseInt(maxcredit.getPmvb()) && ispositive) {
                        returnmap.put("result", false);
                        returnmap.put("errtp", "6");// 信用度不够订竹筏为正数的趟次
                        return returnmap;
                    } else {
                        Creditdetail creditdetail = new Creditdetail();
                        creditdetail.setUsid(credusid);
                        creditdetail.setCreditnumb(creditnum);
                        creditdetail.setZusid(oldmorder.getUsid());
                        creditdetail.setCtype("01");
                        creditdetail.setOrid(oldmorder.getOrid());
                        creditdetail.setCseq(payorderDao.getMaxPk("cseq", "Creditdetail") + 1);
                        payorderDao.save(creditdetail);
                        credit.setCreditnumb(credit.getCreditnumb() + creditnum);
                        payorderDao.update(credit);
                    }
                }

            }
            istoprat = checkStopRaft(charraftlist, iscenicid);
            // 新增订单 返回要消费的积分
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null);
            //退订单返回要退订的积分
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist);
            // 这里以后oldtdlist中的值将不能使用,因为在封装退订单的时候里面的值已经改变
            TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont + totalyhmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            oldtorder.setYhamnt(oldtorder.getYhamnt() + totalyhmont);
            //	oldmorder.setYhamnt(oldmorder.getYhamnt() + totalyhmont);
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldmorder.getTpmont() != null) {
                if (tdmont > 0) {
                    oldmorder.setTpmont(oldmorder.getTpmont() - totalmont - tdmont);
                } else {
                    oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
                }

            } else {
                if (tdmont > 0) {
                    oldmorder.setTpmont(0 - totalmont - tdmont);
                } else {
                    oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
                }

            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            oldmorder.setIsf(1l);
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                if (oldtorder.getIsi() == null) {
                    oldtorder.setIsi(0l);
                }
                if (oldtorder.getIsh() == null) {
                    oldtorder.setIsh(0l);
                }
                if (oldmorder.getIsi() == null) {
                    oldmorder.setIsi(0l);
                }
                if (oldmorder.getIsh() == null) {
                    oldmorder.setIsh(0l);
                }
                oldtorder.setIsi(oldtorder.getIsi() + totalmonthjf);
                oldmorder.setIsi(oldmorder.getIsi() + totalmonthjf);
                oldtorder.setIsh(oldtorder.getIsh() + totalyearjf);
                oldmorder.setIsh(oldmorder.getIsh() + totalyearjf);
            }
            yorderlistdao.update(oldtorder);
            yorderlistdao.update(oldmorder);
            calculateCredit(oldmorder.getUsid(), charraftlist, iscenicid);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);
            if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
            	sysparService.sendMessageNew(oldtorder.getOrph(), "0004", oldtorder.getId().getOrid());
            }
            this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "11");
            this.updateMOrderStatusByZeroProduct(oldorid, "11");

            //lijingrui 修改 服务商优惠 修改原订单中的优惠数量、金额
            List xgtList = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            if (xgtList != null && xgtList.size() > 0) {
                for (int i = 0; i < xgtList.size(); i++) {
                    TOrderlist torders = (TOrderlist) xgtList.get(i);

                    if (fwyhlist != null && fwyhlist.size() > 0) {
                        for (int m = 0; m < fwyhlist.size(); m++) {
                            TOrderlist torder = (TOrderlist) fwyhlist.get(m);
                            if (torder.getItickettypeid().longValue() == torders.getItickettypeid().longValue() && torder.getIcrowdkindpriceid().longValue() == torders.getIcrowdkindpriceid().longValue()) {
                                torders.setYhamnt(torder.getYhamnt());
                                torders.setYhnumb(torder.getYhnumb());
                                torders.setIoffersschemeid(torder.getIoffersschemeid());

                                List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                for (int z = 0; z < list.size(); z++) {
                                    TZorderlist newzlist = (TZorderlist) list.get(z);
                                    newzlist.setZyhamnt(torder.getYhnumb() * newzlist.getZpric());
                                    newzlist.setZyhnumb(torder.getYhnumb());

                                    yorderlistdao.update(newzlist);
                                }

                                yorderlistdao.update(torders);
                            } else {
                                if (torders.getYhnumb() > 0) {
                                    torders.setYhamnt(0D);
                                    torders.setYhnumb(0L);
                                    torders.setIoffersschemeid(0L);

                                    List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                    for (int z = 0; z < list.size(); z++) {
                                        TZorderlist newzlist = (TZorderlist) list.get(z);
                                        newzlist.setZyhamnt(0D);
                                        newzlist.setZyhnumb(0L);

                                        yorderlistdao.update(newzlist);
                                    }

                                    yorderlistdao.update(torders);

                                } else if (torders.getYhnumb() != null && torders.getIoffersschemeid() != null && torders.getYhnumb() == 0 && torders.getIoffersschemeid() > 0) {
                                    torders.setIoffersschemeid(0L);
                                    yorderlistdao.update(torders);
                                }
                            }

                        }
                    } else {
                        if (bs) {
                            if (torders.getYhnumb() > 0) {
                                torders.setYhamnt(0D);
                                torders.setYhnumb(0L);
                                torders.setIoffersschemeid(0L);

                                List list = tZOrderListService.getTZorderlist(torders.getId().getOrderlistid(), torders.getId().getOrid(), torders.getId().getIscenicid());
                                for (int z = 0; z < list.size(); z++) {
                                    TZorderlist newzlist = (TZorderlist) list.get(z);
                                    newzlist.setZyhamnt(0D);
                                    newzlist.setZyhnumb(0L);

                                    yorderlistdao.update(newzlist);
                                }

                                yorderlistdao.update(torders);

                            } else if (torders.getYhnumb() != null && torders.getIoffersschemeid() != null && torders.getYhnumb() == 0 && torders.getIoffersschemeid() > 0) {
                                torders.setIoffersschemeid(0L);
                                yorderlistdao.update(torders);
                            }
                        }

                    }

                }
            }
            //判断库存信息
            List<TOrderlist> newTorderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            if (custom.getIbusinessid() == 2L && custom.getUstp().equals("02") && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getUsid();
            }
            if (newTorderlists != null && !newTorderlists.isEmpty()) {//部分修改
                for (TOrderlist ntl : newTorderlists) {
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
//                ticketService.saveStockDetails(stockOrderInfos, true);
                ticketService.saveStockDetails(JSON.toJSONString(stockOrderInfos), true);
            } else {//全退订
                ticketService.deleteStockDetails(oldorid, Long.parseLong(iscenicid), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")) {
                throw new RuntimeException(e.getMessage());
            } else {
                throw new RuntimeException("修改订单失败");
            }
        }
        return returnmap;
    }


    public Map editOrderSankeCenterOld(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid)
            throws Exception {
        Map returnmap = new HashMap();
        Map newmap = null;
        try {
            MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfoSanke(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrderSanke(oldorid, iscenicid, orderlistInfo);
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            RaftComparepojo compare1 = null;
            RaftComparepojo compare = null;
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && oldaddlist != null) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare1.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                                break;
                            }
                        }
                    }
                }
            }
            // 原订单修改后的差异集合
            List chalist = (List) oldmap.get("chaticket");
            List addlist = new ArrayList();
            List backlist = new ArrayList();
            TOrderlist orderlist = null;
            OrderPojo newTicket = null;
            for (int i = 0; i < chalist.size(); i++) {
                orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }
            List sonticket = null;
            Edmcrowdkindpricetab kindprice = null;
            Edmtickettypetab ticket = null;
            Edmtickettypetab zticket = null;
            List pricelist = null;
            List<TZorderlist> tzlist = null;
            Edmticketcomposepricetab pricepost = null;
            TOrderlist newproduct = null;
            TOrderlist oldtorderlist = null;
            TZorderlist tzorderlist = null;
            Productcontrol tripinfo = null;
            boolean isadd = false;
            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    for (int j = 0; j < addlist.size(); j++) {
                        orderlist = (TOrderlist) addlist.get(j);
                        // 如果产品相同
                        if (orderlist.getItickettypeid().intValue() == Integer.parseInt(newTicket.getItickettypeid())) {
                            tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                            // 且newTicket有浏览日期(有浏览日期说明票含竹筏)
                            if (newTicket.getTourdate() != null) {
                                // 且浏览日期相同
                                tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                // 拿出原订单的对应产品
                                oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid)));
                                //比较子 票的趟次编号和新增票的趟次编号
                                for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
                                    if (zlist.getTripid() != null && zlist.getTripid() != 0 && tripinfo.getTripid().equals(zlist.getTripid())
                                            && orderlist.getIcrowdkindid().equals(newTicket.getIcrowdkindid()) && tripinfo.getStdata().equals(newTicket.getTourdate())) {
                                        orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                        isadd = true;
                                    }
                                }
                            } else {
                                orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
                                isadd = true;
                            }
                            if (orderlist.getZorderlist() == null || orderlist.getZorderlist().size() < 1) {
                                orderlist.setZorderlist(tzlist);
                            } else {
                                for (TZorderlist zlist : orderlist.getZorderlist()) {// 循环有差异的子票列表
                                    zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / orderlist.getNumb() * Integer.parseInt(newTicket.getNumb()));
                                    zlist.setZamnt(zlist.getZpric() * zlist.getZnumb());
                                }
                            }
                        }
                        if (!isadd && j == addlist.size() - 1) {
                            newproduct = new TOrderlist();
                            TOrderlist comp = null;
                            pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                            kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                            ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                            if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                            } else {
                                comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                        Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                            }
                            if (comp != null) {
                                BeanUtils.copyProperties(newproduct, comp);
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                                addlist.add(newproduct);
                                break;
                            } else {
                                newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                                pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                                kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                                newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                newproduct.setDtstartdate(stdt);
                                ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                                newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                                newproduct.setPric(kindprice.getMactualsaleprice());
                                newproduct.setJsprice(kindprice.getJsprice());
                                newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                                newproduct.setYhnumb(0l);
                                newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                                newproduct.setYhamnt(0.0);
                                sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                                newproduct.setZorderlist(new ArrayList<TZorderlist>());
                                for (int y = 0; y < sonticket.size(); y++) {
                                    pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                                    tzorderlist = new TZorderlist();
                                    tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                                    tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                                    tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                                    tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                                    zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                                    if (zticket.getBycategorytype().equals("0003")) {
                                        Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                        Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid,
                                                zticket.getItickettypeid().toString());
                                        tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                        tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                        tzorderlist.setTripid(control.getTripid());
                                        tzorderlist.setIvenueid(prd.getIvenueid());
                                        tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                                    } else {
                                        tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                        tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                                    }
                                    tzorderlist.setZpric(pricepost.getMactualsaleprice());
                                    tzorderlist.setJsprice(pricepost.getJsprice());
                                    tzorderlist.setZnumb(pricepost.getNumb());
                                    tzorderlist.setZyhnumb(0l);
                                    tzorderlist.setZyhamnt(0.0);
                                    tzorderlist.setZamnt(0.0);
                                    newproduct.getZorderlist().add(tzorderlist);
                                }
                                addlist.add(newproduct);
                                break;
                            }
                        }
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    orderlist = (TOrderlist) addlist.get(j);
                    tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    orderlist.setZorderlist(tzlist);
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                TOrderlist comp = null;
                for (int i = 0; i < neworderlist.size(); i++) {
                    newTicket = (OrderPojo) neworderlist.get(i);
                    newproduct = new TOrderlist();
                    pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness);
                    kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
                    } else {
                        comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long.parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt,
                                Tools.getDate(stdt, ticket.getValidityday().intValue() - 1), oldmorder.getStdt(), null);
                    }
                    if (comp != null) {
                        BeanUtils.copyProperties(newproduct, comp);
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
                        addlist.add(newproduct);
                    } else {
                        newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                        newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        newproduct.setDtstartdate(stdt);
                        ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                        newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
                        newproduct.setPric(kindprice.getMactualsaleprice());
                        newproduct.setJsprice(kindprice.getJsprice());
                        newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
                        newproduct.setYhnumb(0l);
                        newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
                        newproduct.setYhamnt(0.0);
                        sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                        newproduct.setZorderlist(new ArrayList<TZorderlist>());
                        for (int y = 0; y < sonticket.size(); y++) {
                            pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                            tzorderlist = new TZorderlist();
                            tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                            tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                            tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                            tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                            zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                            if (zticket.getBycategorytype().equals("0003")) {
                                Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                                Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                        .getItickettypeid().toString());
                                tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
                                tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
                                tzorderlist.setTripid(control.getTripid());
                                tzorderlist.setIvenueid(prd.getIvenueid());
                                tzorderlist.setIvenueareaid(prd.getIvenueareaid());
                            } else {
                                tzorderlist.setDtstartdate(stdt + " 00:00:00");
                                tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
                            }
                            tzorderlist.setZpric(pricepost.getMactualsaleprice());
                            tzorderlist.setJsprice(pricepost.getJsprice());
                            tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
                            tzorderlist.setZyhnumb(0l);
                            tzorderlist.setZyhamnt(0.0);
                            tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
                            newproduct.getZorderlist().add(tzorderlist);
                        }
                        addlist.add(newproduct);
                    }
                }
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            //  全部通过的话,再修改订单,否则返回错误信息
            //  增量 退订 减少或者增加预付款 计算信用度
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
            }
            // 计算出需要增加的金额
            double totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid);
            // 需要退订的钱
            double tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid);

            double userMoney = CommonUtil.getUserMoney(oldmorder.getZfusid());
            // 比较两个map中的值，计算是否可以修改 不可修改返回false
            if (userMoney < Math.abs(tdsx) + totalmont + tdmont) {
                returnmap.put("result", false);
                returnmap.put("errtp", "2");// 余额不足
                return returnmap;
            }

            List charraftlist = null;
            List newdaylist = null;
            List olddaylist = null;
            int istoprat = 0;
            if (oldmap != null) {
                charraftlist = (List) oldmap.get("charraft");
                olddaylist = (List) oldmap.get("chaday");
            }
            List newraft = null;
            if (newmap != null) {
                newdaylist = (List) newmap.get("daylist");
                newraft = (List) newmap.get("raftlist");
            }
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);

            istoprat = checkStopRaft(charraftlist, iscenicid);

            // 新增订单
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null);
            // 退订单
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist);

            TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            YOrder oldyorder = (YOrder) yorderlistdao.get(YOrder.class, new YOrderId(oldtorder.getId().getOrid(), oldtorder.getId().getIscenicid()));
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldyorder.getTpmont() != null) {
                oldyorder.setTpmont(oldyorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldyorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldyorder.getTpsx() != null) {
                    oldyorder.setTpsx(oldyorder.getTpsx() + Math.abs(tdsx));
                } else {
                    oldyorder.setTpsx(Math.abs(tdsx));
                }
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            if (oldmorder.getTpmont() != null) {
                oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            oldmorder.setIsf(1l);
            oldyorder.setIsf(1l);
            oldtorder.setIsf(1l);
            yorderlistdao.update(oldyorder);
            yorderlistdao.update(oldtorder);
            yorderlistdao.update(oldmorder);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);
            this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "11");
            this.updateMOrderStatusByZeroProduct(oldorid, "11");
            //判断库存信息
            List<TOrderlist> newTorderlists = torderlistdao.getTOrderlists(oldorid, Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            Custom custom = (Custom) this.morderdao.get(Custom.class, stockUsid);
            if (custom.getIbusinessid() == 2L && custom.getUstp().equals("02") && custom.getUsqx().startsWith("0111")) {
                stockUsid = custom.getUsid();
            }
            
            if (newTorderlists != null && !newTorderlists.isEmpty()) {//部分修改
                for (TOrderlist ntl : newTorderlists) {
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
//                ticketService.saveStockDetails(stockOrderInfos, true);
                ticketService.saveStockDetails(JSON.toJSONString(stockOrderInfos), true);
            } else {//全退订
                ticketService.deleteStockDetails(oldorid, Long.parseLong(iscenicid), null, null);
            }

            if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
            	sysparService.sendMessageNew(oldtorder.getOrph(), "0004", oldtorder.getId().getOrid());
            }
        } catch (Exception e) {
            if (!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")) {
                throw new RuntimeException(e.getMessage());
            } else {
                e.printStackTrace();
                throw new RuntimeException("散客修改订单  保存失败--->>>订单号:" + oldorid);
            }
        }

        return returnmap;

    }

	public List findSendHotelMsg(String orid, Long iscenicid) {
		return torderlistdao.findSendHotelMsg(orid, iscenicid);
	}


	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateOrderDdzt(String orid, String ddzt) {
		morderdao.updateOrderDdzt(orid,ddzt);
		
	}

	@Override
	public List<Map> getTZOrderMapList(String orId, String iscenicid, String orderlistid) throws Exception {
		
		return torderlistdao.getTZOrderMapList(orId, iscenicid, orderlistid);
	}

	@Override
	public List<Map> getTZOrderMapListByOrIdAndIscenicid(String orId, String iscenicid) throws Exception {
		
		return torderlistdao.getTZOrderMapListByOrIdAndIscenicid(orId,iscenicid);
	}

	@Override
	public YOrder getYOrderInfoById(String orId, Long iscenicid) throws Exception {
		
		return yorderdao.getYOrderInfoById(orId, iscenicid);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	@Override
	public void updateYOrder(YOrder yorder) throws Exception {
		yorderdao.update(yorder);
	}

	@Override
	public List<TOrder> getTOrderForListByOrid(String orid) throws Exception {
		
		return torderdao.getTOrderForListByOrid(orid);
	}

	@Override
	public List<Map> getOrderListInfo(String param,String msql) throws RuntimeException,Exception{
		return morderdao.getOrderListInfo(param,msql);
	}

	@Override
	public List<Map> getTOrderMapList(String orId, String iscennicId) throws Exception {
		
		return torderlistdao.getTOrderMapList(orId, iscennicId);
	}
	public List<MOrder> getMorderListInfo(String orid){
		return morderdao.getMorderListInfo(orid);
	}

	

	public List<TOrder> findTorderValues(String idcard){

		List<TOrder> t = this.find(" from TOrder t where t.orhm = '"+idcard+"'  order by t.id.orid desc");
		List<TOrderlist> tl = this.find(" from TOrderlist tlist where tlist.id.orid = "+t.get(0).getId().getOrid());
		List<Edmcrowdkindpricetab> edmcrowdkindpricetabs = this.find(" from Edmcrowdkindpricetab where icrowdkindpriceid = "+ tl.get(0).getIcrowdkindpriceid());
		//判断是否属于实名制票，如果是 ，则不可用领票人证件号检票
		if(edmcrowdkindpricetabs.get(0).getIpeoplenumrange() == 1){
			return null;
		}else {
			List<TOrder> ts = this.find("from TOrder t where t.orhm='"+idcard+"' order by t.id.orid desc ");
			return ts;
		}
	
	}
	
	
	public List<TOrder> getTOrdersList(String orid){
		List<TOrder> ts = this.find(" from TOrder t where t.id.orid=? ", new Object[] {orid});
		return ts;
	}
	
	@Transactional
	public void updateTOrder(TOrder torder) {
		torderdao.saveOrUpdate(torder);
	}
	
	public List<Map<String, Object>> getTOrderInfos(String orid, String iscenicids){
		return torderlistdao.getTOrderInfos(orid, iscenicids);
	}
	
	public List<Map<String, Object>> getZOrderInfos(String orid, String iscenicids, Long orderlistid){
		return torderlistdao.getZOrderInfos(orid, iscenicids, orderlistid);
	}
	
	public List getSatordertab(String orderId, String iscenicid, String orderlistid, String zorderlistid) {
		return torderdao.getSatordertab( orderId,  iscenicid,  orderlistid,  zorderlistid);
	}

	
	
	public List<TOrder> getTOrdersByOrid(String orid){
    	return torderdao.getTOrdersByOrid(orid);
    }

	@Override
	public List<Map> getOrderListInfo(String param) throws RuntimeException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Map> getTorderauto(String carno,  Long iscenicid) {
		// 从后台参数获取 是否允许提前出票 是否允许过期出票 参数值
        String start = Tools.getDays();
        String msql = "";
        System.out.println("------->>>>>>>>>sql：1");

        System.out.println("------->>>>>>>>>sql：2");
        try {
            Sysparv5 sys1 = sysparService.findOne("SFGQ", "1"); //(Sysparv5) this.timeSharingDao.find(" from Sysparv5 where id.pmky='SFGQ' and id.pmcd='1' ").get(0); // 是否允许过期出票
            Sysparv5 sys2 = sysparService.findOne("SFYX", "1"); //(Sysparv5) this.timeSharingDao.find(" from Sysparv5 where id.pmky='SFYX' and id.pmcd='1' ").get(0); // 是否允许提前出票

            if (sys1.getPmva().equals("1")){//0:允许，1:不允许
                msql =msql+ " and t.dtenddate>='" + start + "' ";
            }
            if (sys2.getPmva().equals("1")){//0:允许，1:不允许
                msql =msql+ " and t.dtstartdate<='" + start + "' ";
            }
        } catch (Exception e) {
            System.out
                    .println("如果想控制过期票以及是否提前出票请检查： Sysparv5 中 KEY SFGQ 及  SFYX PMCD = 1 表示允许0不允许");
            msql = "";
        }
        if (iscenicid != 0) {
            msql = msql + " and t.iscenicid=" + iscenicid;
        }
        String sql = " select CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.usid,t.ibusinessid,t.sztravelbillno,t.iregionalid,t.tdlx,cast(t.ddzt as varchar(2)) as ddzt,t.dtenddate,t.dtstartdate,t.dyusid,t.ornm,t.orhm,t.mont,t.yhamnt,t.zfmont,t.ischupiao,t.notea,c.corpname,c.lname,dy.password,m.isjl as isjl,m.zfusid as zfusid,m.zffs as zffs,t.ornote1,t.ornote2,t.ornote3,t.ornote4,t.ornote5,t.ornote6,t.ornote7,t.ornote8,t.ornote9,t.ornote10,c.note2 as jsfz from T_order t  left outer join custom dy on dy.usid=t.dyusid and dy.status='01',custom c,m_order m where  ( upper(trim(orhm))='"
                + carno.toUpperCase()
                + "' or CASt(t.orid as varchar(17))='"
                + carno
                + "' )  and  t.ddzt='02'   and m.orid=t.orid "
                + msql
                + "  and c.usid=t.usid   order by orid desc";
        List<Map> list = new ArrayList();
        System.out.println("sql=" + sql);
        try {
            list = morderdao.findBySqlToMap(sql);
            for(int i=0;i<list.size();i++) {
            	Map mapv = list.get(i);
            	Long iregionalid = Long.parseLong(String.valueOf(mapv.get("IREGIONALID")));
            	if(iregionalid!=null&&iregionalid!=0) {//客源地
            		Galsourceregiontab galsourceregiontab = sysparService.getSourceregionById(iregionalid);
            		mapv.put("SZINNERNAME", galsourceregiontab.getSzinnername());
            	}
            	
            	String pmcd = String.valueOf(mapv.get("ZFFS"));
            	if(pmcd!=null&&!"".equals(pmcd)) {
            		Sysparv5 sys5 = sysparService.findOne("ZFFS", pmcd);
            		mapv.put("STRZFFS", sys5.getPmva());
            	}
            	
            	list.get(i).putAll(mapv);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
	}
	
	public List<TZorderlist> getTzOrderList(String orid, String iscenicids){
		return torderdao.getTzOrderList(orid, iscenicids);
	}
	
	public List<Map> getTzOrderLists(String orid, String iscenicids){
		return torderdao.getTzOrderLists(orid, iscenicids);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	 public ResultBean updateT_order(String orid, Long iscenicid,
             Long iemployeeid, Double mont) {
		 return torderdao.updateT_order( orid, iscenicid, iemployeeid, mont);
	 }
}