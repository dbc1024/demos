package com.ectrip.ec.order.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TZorderlist;
/**
 * 
* @ClassName: ITOrderDAO 
* @Description: ?????????????
* @author Dicky
* @date Oct 11, 2011 2:40:45 PM 
*
 */
public interface ITOrderDAO extends IGenericDao{
  public void saveTOrder(TOrder torder);
  public List getTOrderList(String orid);
  public List getTOrderLists(String orid);
  public void updateTOrder(TOrder torder);
  public TOrder getTOrder(String orid,String iscenicid) throws Exception;
  public List getTOrderList(String orid,String iscenicid);
  public List getRealnemeList(String iscenicid,String idcard);
  public List<Map> getTOrder(String orid);
  
  public String getOrderIdByIdcard(Long iscenicid,String idcard);
  
  public List<TOrder> getTOrderForListByOrid(String orid) throws Exception;
  public MOrder getMorderInfo(String orderId);
  
  public List<TOrder> getTOrdersByOrid(String orderId);
  
  public List getSatordertab(String orderId, String iscenicid, String orderlistid, String zorderlistid);
  
  public List<TZorderlist> getTzOrderList(String orid, String iscenicids);
  
  public List<Map> getTzOrderLists(String orid, String iscenicids);
  
  public ResultBean updateT_order(String orid, Long iscenicid,
          Long iemployeeid, Double mont) ;
}
