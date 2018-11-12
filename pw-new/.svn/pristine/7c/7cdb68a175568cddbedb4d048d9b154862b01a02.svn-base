package com.ectrip.ticket.common.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;
import com.ectrip.ticket.model.stock.Stocktab;

/**
 * Created by cxh on 2015/11/26.
 */
public interface IStockDao extends IGenericDao {
    public List<Stocktab> selectCustomStockById(String usid, String customStockType, String customStockId, String date);
    public Stockdetailtab selectStockDetail(StockOrderInfo stockOrderInfo);
    public List<Stocktab> selectStockById(String stockType, String id, String date);
    public Long sumStockById(String stockType, String id, String rzti, String ldti, String noInclude);
    public Long sumCustomStock(String usid, String stockType, String id, String rzti, String ldti, String noInclude);
    public void deleteStockDetails(String orid, Long providerid, Long seq, String noInclude);
}
