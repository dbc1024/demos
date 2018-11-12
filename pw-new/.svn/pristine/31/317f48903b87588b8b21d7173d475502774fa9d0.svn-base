package com.ectrip.ec.order.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
/**
 * 
* @ClassName: ITOrderListDAO 
* @Description: 网上订票销售订单明细 
* @author Dicky
* @date Oct 11, 2011 2:39:51 PM 
*
 */
public interface ITOrderListDAO extends IGenericDao{
     public void saveTOrderList(TOrderlist torderlist);
     public List getTOrderListList(String orid,String iscenicid) throws IllegalAccessException, InvocationTargetException;
     public TOrderlist getTOrderList(String torderlistid, String iscenicid,
    			String orid);
 	public void updateTOrderList(TOrderlist torderlist);

    public TOrderlist getTorderlistByProductInfo(Long itickettypeid,String orid,Long iscenicid,Long icrowdkindid,String dtstartdate,String dtenddate,String tourdate,Long productcontrolid);
    public boolean delZeroTicketInOrder(String orid);
    public List<TOrderlist> getTOrderlists(String orid,Long iscenicid);
    public Integer getMaxCanEditDate(String orid);
    public boolean validationEditDate(String orid,String stdt,String enddate,String iscenicid);
    public List<TOrderlist> getTOrderlists(String orid);
    public List getTZorderlists(String orid,TOrderlistId id);
    public boolean validationEditDatePrice(String orid, String stdt, String iscenicid,String ibussiness);
    public boolean validationEditScheme(String orid, String stdt, String iscenicid,String ibussiness);
    public List getTOrderList(String orid,String ddzt);
    public List<TOrderlist> getTOrderlists(String orid,String iscenicid);
    public List findSendHotelMsg(String orid,Long iscenicid);
    public List<Map<String, Object>> getTOrderInfos(String orid, String iscenicids);
    public List<Map<String, Object>> getZOrderInfos(String orid, String iscenicids, Long orderlistid);
    /**
    * @Title: getTOrderMapList  
    * @Description: TODO 查询网上订票销售订单明细Map  
    * @param @return
    * @param @throws Exception    参数  
    * @return List<Map>    返回类型  
    * @throws
     */
    public List<Map> getTOrderMapList(String orId,String iscennicId) throws Exception;
    
    /**
    * @Title: getTZOrderMapList  
    * @Description: TODO 获取订单拆分信息  
    * @param @param orId
    * @param @param iscenicid
    * @param @param orderlistid
    * @param @return
    * @param @throws Exception    参数  
    * @return List<Map>    返回类型  
    * @throws
     */
    public List<Map> getTZOrderMapList(String orId, String iscenicid, String orderlistid) throws Exception;
    /**
     * 查询网上订票销售订单明细
     * @param orid
     * @param iscenicid
     * @return
     */
	public List<TOrderlist> findTOrderList(String orid, Long iscenicid);
    
    public List<Map> getTZOrderMapListByOrIdAndIscenicid(String orId, String iscenicid) throws Exception;
    
}
