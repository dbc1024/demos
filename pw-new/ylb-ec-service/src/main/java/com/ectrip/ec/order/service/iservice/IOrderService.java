package com.ectrip.ec.order.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.cyt.CYTDto;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;

/**
 * 
 * @ClassName: IOrderService
 * @Description:订票业务 ISERVICE
 * @author Dicky
 * @date Oct 11, 2011 2:20:55 PM
 * 
 */
public interface IOrderService extends IGenericService {
	public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder,
                             List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist, List<TRealname> rlist)
			throws IllegalAccessException, InvocationTargetException;
	public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder,
                             List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist)
			throws IllegalAccessException, InvocationTargetException;

	public PaginationSupport getOrderViewList(String usid, QueryOrder query, int pageSize,
                                              int page, String url);
	//查询企业下的所有订单
	public PaginationSupport getOrderViewListByGroupId(String usid, QueryOrder query, int pageSize,
			int page, String url,String groupId);
		public TOrderlist getTOrderList(String torderlistid, String iscenicid, String orid);

	public List getMOrderList(String orid);

	public List getTOrderList(String orid);
	public List getTOrderLists(String orid);

//	public CYTDto getRealnemeList(String iscenicid,String idcard);
	
	public List getTOrderListList(String orid, String iscenicid)throws IllegalAccessException, InvocationTargetException;

	public List getYOrderList(String orid);

	public List getYOrderListList(String orid, String iscenicid)throws IllegalAccessException, InvocationTargetException;

	public List getYOrderChildList(String orid);

	public List getYOrderListChildList(String orid, String iscenicid);

	public void updateTOrderList(TOrderlist torderlist, Orderlog log);

	public void updateTOrder(TOrder torder, Orderlog log) throws Exception;

	public TOrder getTOrder(String orid, String iscenicid) throws Exception;
	
	public List<Map> getTOrder(String orid);
	public List<TOrder> getTOrdersByOrid(String orid);
	
	public MOrder getMorder(String orid);

	public MOrder getRefundMOrder(String srid);

	public void updateMOrder(MOrder morder, Orderlog log);

	public int getLastYOrderListId();

	public String getMaxNo();
	
	public Custom queryDaoyouByOrhm(String orhm) throws IllegalAccessException, InvocationTargetException;
	
	public List getTZorderlist(Long orderlistid, String orid, Long iscenicid);
	
	public List getTOrderList(String orid, String iscenicid);
	
	/**
	 * ?????
	 * Describe:
	 * @auth:yangguang
	 * @param itickettypeid
	 * @param tourdate
	 * @param num
	 * @return
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * return:boolean
	 * Date:2011-12-6
	 */
	public boolean volidateControl(String itickettypeid, String tourdate, int num)throws IllegalAccessException, InvocationTargetException;


	/**
	 *
	 * Describe:???????
	 * @auth:yangguang
	 * @param itickettypeid
	 * @param tourdate
	 * @param ivenueid
	 * @param ivenueareaid
	 * @param tripid
	 * @param num
	 * @return
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * return:boolean
	 * Date:2011-12-6
	 */
	public boolean volidateControl(String itickettypeid, String tourdate, String ivenueid, String ivenueareaid, String tripid, int num)throws IllegalAccessException, InvocationTargetException;



	public int updateOrderInfo(TOrderlist newInfo, TOrderlist tagerInfo, String usid) throws Exception ;

	public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid) throws Exception;

	public Map editOrderCenterOld(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid) throws Exception;


	public Map validateOrderInfo(String orid, List<TOrderlist> orderInfo, List<OrderPojo> newInfo, String usid, String stdt, String ibusinessid, String iscenicid) throws IllegalAccessException, InvocationTargetException;

	public List getOrderLogByOrid(String orid);

	public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) throws IllegalAccessException, InvocationTargetException;

	public List<TOrderlist> getTOrderlists(String orid);

	public List daoyoulist(String usid);

	public PaginationSupport queryAllMsOrder(String usid, QueryOrder order, int page, int pageSize, String url);
	
	public PaginationSupport queryAllMsOrderByGroupId(String usid, QueryOrder order, int page, int pageSize, String url,String groupId);

	/**
	 *
	 * Describe:????????????
	 * @auth:yangguang
	 * @param orid
	 * @param stdt
	 * @return
	 * return:boolean
	 * Date:2012-4-7
	 */
	public boolean iscanedit(String orid, String stdt, String iscenicid, String ibusiness);
	/**
	 *
	 * Describe:??????????????
	 * @auth:yangguang
	 * @param orid
	 * @param stdt
	 * return:void
	 * Date:2012-4-7
	 */
	public void updateProductDate(String orid, String stdt, String iscenicid);

	/**
	 *
	 * Describe:?????????????
	 * @auth:lijingrui
	 * @param orderlistInfo
	 * @param neworderlist
	 * @param orids
	 * @param oldorid
	 * @param iscenicid
	 * @param stdt
	 * @param ibusiness
	 * @param usid
	 * @return
	 * @throws Exception
	 * return:Map
	 * Date:May 3, 2012
	 */
	public Map editOrderSankeCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
                                    String iscenicid, String stdt, String ibusiness, String usid) throws Exception;
	public Map editOrderSankeCenterl(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
									String iscenicid, String stdt, String ibusiness, String usid) throws Exception;


	/**
	 *
	 * Describe:???????????????????
	 * @auth:lijingrui
	 * @param orid
	 * @return
	 * return:List
	 * Date:May 3, 2012
	 */
	public List showscenicidview(String orid);


	public List showscenicidview(String orid, String iscenicid) ;

	/**
	 *
	 * Describe:??????????????????????????????????
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param date
	 * @param ziticketid
	 * @param usid
	 * @return
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * return:List
	 * Date:May 4, 2012
	 */
	public List getRafttripInfoList(String itickettypeid, String date, String ziticketid, String usid) throws IllegalAccessException,
    InvocationTargetException ;

	/**
	 *
	 * Describe:????????????????????????????????????
	 * @auth:lijingrui
	 * @param iticketid
	 * @param date
	 * @param controlltype
	 * @return
	 * return:List
	 * Date:May 4, 2012
	 */
	public List getNumberControllData(String iticketid, String date, String controlltype);

	/**
	 *
	 * Describe:?????????????????
	 * @auth:lijingrui
	 * @param orderlistInfo
	 * @param orids
	 * @param oldorid
	 * @param stdt
	 * @param ibusiness
	 * @param usid
	 * @return
	 * @throws Exception
	 * return:Map
	 * Date:May 9, 2012
	 */
	public Map orderEditCenter(List<TOrderlist> orderlistInfo, String[] orids, String orid,
                               String stdt, String ibusiness, String usid) throws Exception;


	public String getFsusid(String usid) throws Exception;
	public void updateOrderZtByZeroProduct(String orid, Long iscenicid, String ddzt);


	/**
	 *
	 * Describe:?????
	 * @auth:yangguang
	 * @param orids
	 * @param oldorid
	 * @param iscenicid
	 * @param stdt
	 * @param ibussiness
	 * @param usid
	 * @return
	 * return:Map
	 * Date:2012-6-12
	 */
	public Map backOrder(String[] orids, String oldorid, String iscenicid, String stdt, String ibussiness, String usid);
	public Map validateInfoIntegrity(List<OrderPojo> newticketinfo, String ibussiness, String iscenicid, String orid, String groupno);
	/**
	 *
	 * Describe:???????????????????????????????????
	 * @auth:yangguang
	 * @param orid
	 * @return
	 * @throws java.text.ParseException
	 * return:Map
	 * Date:2012-8-7
	 */
	public Map validTorderInfo(String orid) throws Exception;

	public PaginationSupport getOrderMoneyChangeViewlist(String usid, QueryOrder query, int pageSize, int startIndex, String url);
	public PaginationSupport getOrderMoneyChangeViewlistByGroupid(String usid, QueryOrder query, int pageSize, int startIndex, String url,String groupId);

	/**
	 * ??????????????????
	 * @param orid
	 * @return
	 */
	public List getOrderOpearHistory(String orid);

	public List<TZorderlist> getTZorderlist(String orid, Long torderlistid, long iscenicid);

	/**
	 * ?????????
	 * @throws Exception
	 * @throws WriterException
	 * @throws java.io.IOException
	 */
	public String makeBarcode(String contents) throws Exception;
	
	/**
	 * 
	 * Describe:?????????????????(????????)
	 * 
	 * @auth:lijingrui
	 * @param orderlistInfo
	 * @param neworderlist
	 * @param orids
	 * @param oldorid
	 * @param iscenicid
	 * @param stdt
	 * @param ibusiness
	 * @param usid
	 * @param scenictype //??????????
	 * @return
	 * @throws Exception
	 *             return:Map Date:May 3, 2012
	 */
	public Map orderEditCenter(List<TOrderlist> orderlistInfo, String[] orids, String orid, String stdt, String ibusiness, String usid, String scenictype) throws Exception;

	/**
	 * ??????????
	 * updateNotea(???????????????????????????)
	 * (??????????????????????? ?C ???)
	 * @auth hejiahua
	 * @param orid ??????
	 * @param notea ???
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2014-5-12 ????11:44:46
	 * @since  1.0.0
	 */
	public boolean updateNotea(String orid, String notea);
	
	public List getYorderlists(String orid, String iscenicid);
    public void changDdzt(String orda, String orti);

	List<MOrder> findConsumeOrder();

    List<LOrder> findConsumeAutoSaleOrder();
	
	List<MOrder> findRefundOrder();
	
	//查询退款订单
	String findRefundOrderId(String orid);
	public void saveStock(TimeSharingTicketTab tst) throws Exception;

	public Map editOrderSankeCenterOld(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
									String iscenicid, String stdt, String ibusiness, String usid) throws Exception;

	public List findSendHotelMsg(String orid, Long iscenicid);
	
	public boolean saveShopCartOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist) throws IllegalAccessException,
    InvocationTargetException ;

	public List<MOrder> getMorderListInfo(String orid);

	/**
	* @Title: getOrderListInfo  
	* @Description: TODO 查询订单信息通过   
	* @param @param param 电话号码或身份证号或订单号
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */

	public List<Map> getOrderListInfo(String param) throws RuntimeException,Exception;

	public List<Map> getOrderListInfo(String param,String msql) throws RuntimeException,Exception;
	/**
	 * 更新订单状态
	 * @param orid
	 * @param ddzt
	 */
	public void updateOrderDdzt(String orid, String ddzt);

	public List<TOrder> findTorderValues(String idcard);

	public List<TOrder> getTOrdersList(String orid);
	
	public void updateTOrder(TOrder torder) ;
	
	public List<Map<String, Object>> getTOrderInfos(String orid, String iscenicids);
	
	public List<Map<String, Object>> getZOrderInfos(String orid, String iscenicids, Long orderlistid);
	public List getSatordertab(String orderId, String iscenicid, String orderlistid, String zorderlistid);

	/**
    * @Title: getTOrderMapList  
    * @Description: TODO 查询网上订票销售订单明细Map  
    * @param @return
    * @param @throws Exception    参数  
    * @return List<Map>    返回类型  
    * @throws
    */
	public List<Map> getTOrderMapList(String orId,String iscennicId) throws Exception;

	
	public List<Map> getTorderauto(String carno,  Long iscenicid);
	
	public List<TZorderlist> getTzOrderList(String orid, String iscenicids);
	
	public List<Map> getTzOrderLists(String orid, String iscenicids);

	public List<Map> getTZOrderMapList(String orId,String iscenicid,String orderlistid) throws Exception;
	
	public List<Map> getTZOrderMapListByOrIdAndIscenicid(String orId,String iscenicid) throws Exception;
	
	public YOrder getYOrderInfoById(String orId,Long iscenicid) throws Exception; 
	
	public void updateYOrder(YOrder yorder) throws Exception;
	
	/**
	* @Title: getTOrderForListByOrid  
	* @Description: TODO 查询TOrder列表  
	* @param @param orid
	* @param @return
	* @param @throws Exception    参数  
	* @return List<TOrder>    返回类型  
	* @throws
	 */
	public List<TOrder> getTOrderForListByOrid(String orid) throws Exception;
	
	 public ResultBean updateT_order(String orid, Long iscenicid,Long iemployeeid, Double mont) ;

}