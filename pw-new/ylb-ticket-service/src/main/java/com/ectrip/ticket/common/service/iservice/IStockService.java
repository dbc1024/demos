package com.ectrip.ticket.common.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;

/**
 * Created by cxh on 2015/11/26.
 */
public interface IStockService extends IGenericService{

    public Long sumStockNumb(String stockType,String id,String rzti,String ldti,String usid);
    public Long sumJdStock(String usid,String iscenicid);
    public String checkCustomStock(List<StockOrderInfo> stockOrderInfos);
    public String checkStock(List<StockOrderInfo> stockOrderInfos);

    /**
     * 保存库存信息
     * @param stockOrderInfos 订单数据列表
     */
    public void saveStockDetails(List<StockOrderInfo> stockOrderInfos,boolean isCheck);
    public void deleteStockDetails(String orid,Long providerid,Long seq,String noInclude);
    
    public Stockdetailtab selectStockDetail(StockOrderInfo stockOrderInfo);
}
