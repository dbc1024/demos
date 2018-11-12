package com.ectrip.ticket.stocks.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;
import com.ectrip.ticket.model.stock.Stocktab;
import com.ectrip.ticket.stocks.dao.idao.IStockDao;

/**
 * Created by cxh on 2015/11/26.
 */
public class StockDao extends GenericDao implements IStockDao {

    /**
     * 查询库存明细
     * @param stockOrderInfo
     * @return
     */
    public Stockdetailtab selectStockDetail(StockOrderInfo stockOrderInfo){
        String hsql = "from Stockdetailtab s where s.orid = '"+stockOrderInfo.getOrid()+"'" +
                " and s.providerid="+stockOrderInfo.getProviderId()+" and s.productid = "+stockOrderInfo.getProductId() +
                " and s.priceid = "+stockOrderInfo.getPriceId() +" and s.usid = '"+stockOrderInfo.getUsid()+"' ";
        List<Stockdetailtab> stockdetails = this.find(hsql);
        if(stockdetails != null && !stockdetails.isEmpty()){
            return stockdetails.get(0);
        }
        return null;
    }

    public List<Stocktab> selectCustomStockById(String usid,String customStockType,String customStockId,String date){
        String hsql = "from Stocktab s where s.stocktype='04' and s.id='"+usid+"' " +
                "and s.startdate <='"+date+"' and s.enddate>='"+date+"' and s.customStockType='"+customStockType+"' " +
                "and s.customStockId='"+customStockId+"' ";
        List<Stocktab> list = this.find(hsql);
        return list;
    }

    /**
     * 查看库存信息
     * @param stockType
     * @param id
     * @param date
     * @return
     */
    public List<Stocktab> selectStockById(String stockType,String id,String date){
        String hsql = "from Stocktab s where s.stocktype='"+stockType+"' and s.id='"+id+"' " +
                "and s.startdate <='"+date+"' and s.enddate>='"+date+"' ";
        List<Stocktab> list = this.find(hsql);
        return list;
    }

    /**
     * 合计库存数量
     * @param stockType  库存类型
     * @param id    服务商ID，产品ID，价格ID
     * @param rzti  库存起始日期
     * @param ldti  库存截止日期
     * @param noInclude 不包含订单号
     * @return
     */
    public Long sumStockById(String stockType,String id,String rzti,String ldti,String noInclude){
        StringBuffer hsql = new StringBuffer("select new map(nvl(sum(s.numb),0) as numb) from Stockdetailtab s where s.consumedate >= '"+rzti+"' " +
                "and s.consumedate <= '"+ldti+"' ");
        if(stockType.equals("01")){
            hsql.append(" and s.providerid = '"+id+"' ");
        }else if(stockType.equals("02")){
            hsql.append(" and s.productid = '"+id+"' ");
        }else if(stockType.equals("03")){
            hsql.append(" and s.priceid = '"+id+"' ");
        }
        if(!StringUtils.isBlank(noInclude)){
            hsql.append(" and s.orid <> '"+noInclude+"'");
        }
        List list = this.find(hsql.toString());
        if(list != null && !list.isEmpty()){
            Map map = (Map) list.get(0);
            return Long.parseLong(map.get("numb").toString());
        }
        return 0L;
    }

    /**
     * 判断用户库存数量
     * @param usid  用户ID
     * @param stockType 库存类型 01 服务商库存  02 产品库存  03 价格库存
     * @param id    对应服务商id 产品id 价格id
     * @param rzti  库存起始日期
     * @param ldti  库存截止日期
     * @param noInclude 不包含订单号
     * @return
     */
    public Long sumCustomStock(String usid,String stockType,String id,String rzti,String ldti,String noInclude){
        StringBuffer hsql = new StringBuffer("select new map(nvl(sum(s.numb),0) as numb) from Stockdetailtab s where s.consumedate >= '"+rzti+"' " +
                "and s.consumedate <= '"+ldti+"' and s.usid = '"+usid+"' ");
        if(stockType.equals("01")){
            hsql.append(" and s.providerid = '"+id+"' ");
        }else if(stockType.equals("02")){
            hsql.append(" and s.productid = '"+id+"' ");
        }else if(stockType.equals("03")){
            hsql.append(" and s.priceid = '"+id+"' ");
        }
        if(!StringUtils.isBlank(noInclude)){
            hsql.append(" and s.orid <> '"+noInclude+"'");
        }
        List list = this.find(hsql.toString());
        if(list != null && !list.isEmpty()){
            Map map = (Map) list.get(0);
            return Long.parseLong(map.get("numb").toString());
        }
        return 0L;
    }

    /**
     * 删除不存的库存数据
     * @param orid  订单号
     * @param providerid    服务商ID
     * @param seq   主键--可不填
     * @param noInclude 不包含的数据--可不填
     */
    public void deleteStockDetails(String orid,Long providerid,Long seq,String noInclude){
        StringBuffer hsql = new StringBuffer();
        hsql.append("from Stockdetailtab s where s.orid='"+orid+"' ");
        if(providerid != null){
            hsql.append(" and s.providerid="+providerid);
        }
        if(seq != null){
            hsql.append(" and s.seq = "+seq);
        }
        if(!StringUtils.isBlank(noInclude)){
            hsql.append(" and seq not in ("+noInclude+") ");
        }
        List<Stockdetailtab> list = this.find(hsql.toString());
        if(list != null && !list.isEmpty()){
            for (Stockdetailtab stockdetailtab : list){
                this.delete(stockdetailtab);
            }
        }
    }
}
