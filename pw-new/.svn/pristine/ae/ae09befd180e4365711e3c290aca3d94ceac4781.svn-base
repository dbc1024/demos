package com.ectrip.ec;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.cyt.CYTDto;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;
import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.model.order.LZorderlist;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.Vcitable;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.CustomRealName;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.ec.model.user.Userbank;


//@RequestMapping("ec")
public interface EcServiceApi {

	/*
	 * 根据用户ID集合获取用户信息列表,userIds数据形式userIds="12,541,625,51"
	 */
	@GetMapping(value = "ec/custom/v1/list")
	public List getCustomsByUserIds(@RequestParam("userIds") String userIds);
	/**
	 * 根据对象删除
	 * @param userIds
	 * @return
	 */
	@DeleteMapping(value = "ec/v1/deleteCustom")
	public void deleteCustom(@RequestBody Custom custom);
	/**
	 * 更新Custom
	 * @param userIds
	 * @return
	 */
	@PostMapping(value = "ec/v1/updateCustom")
	public void updateCustom(@RequestBody Custom custom);
	
	
	/*
	 * 根据实名用户ID集合获取实名信息列表,userIds数据形式userIds="12,541,625,51"
	 */
	@GetMapping(value = "customRealName/v1/list")
	public List getRealByUserIds(@RequestParam("userIds") String userIds);
	
	
	@RequestMapping("/search/findUserByPwd")
	public Map findUserByPwd(@RequestParam("usid")String usid, @RequestParam("pwd")String pwd);
	
	@RequestMapping("/search/getValidate")
	public Vcitable getValidate(@RequestParam("revmbno")String revmbno);
	
	@RequestMapping("/search/valilogin")
	public String valilogin(@RequestParam("usid")String usid, @RequestParam("pwd")String pwd);
	
	@RequestMapping("/search/findUserByPhone")
	public Map findUserByPhone(@RequestParam("usid")String usid, @RequestParam("pwd")String pwd);
	
	@RequestMapping("/search/getTourCardInfo")
	public Map<String, Object> getTourCardInfo(@RequestParam("carNo")Object carNo);
	
	@RequestMapping("/search/getSenice")
	public List<Map<String, Object>> getSenice(@RequestParam("senice")Object senice);
	
	@RequestMapping("/search/getTicketInfo")
	public Map<String, Object> getTicketInfo(@RequestParam("scienid")String scienid);
	
	@RequestMapping("/search/getScenceImg")
	List<Map<String, Object>> getScenceImg(@RequestParam("scienid")String scienid);
	
	@RequestMapping("/search/findUser")
	public Map findUser(@RequestParam("usid")String usid, @RequestParam("pwd")String pwd);
	
	@RequestMapping("/search/getHoliday")
	public boolean getHoliday(@RequestParam("date")String date);
	
	@RequestMapping("/search/getLykOrderList")
	public Map<String, Object> getLykOrderList(@RequestParam("itickettypeid") String itickettypeid ,
											   @RequestParam("icrowdkindid")Long icrowdkindid ,
											   @RequestParam("isceniceId")Long isceniceId,
											   @RequestParam("playDate")String playDate,
											   @RequestParam("userId")String userId);
	
	@RequestMapping("/search/useTourTicket")
	public void useTourTicket(@RequestParam("outNumber")String outNumber,@RequestParam("userid")String userid)  throws Exception;
	
	@RequestMapping("/search/getCustomByPhone")
	public Custom getCustomByPhone(@RequestParam("phone")String phone);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	@GetMapping("ec/custom/getCustomByUserId")
	public Custom getCustomByUserId(@RequestParam("userId")String userId);
	
	@RequestMapping("/search/getUserCard")
	public List<Map<String, Object>> getUserCard(@RequestParam("userId")String userId,@RequestParam("isecienceId")String isecienceId);
	
	@RequestMapping("/search/refuseTourTicket")
	public void refuseTourTicket(@RequestParam("outNumber")String outNumber,@RequestParam("userid")String userid)  throws Exception;
	
	@RequestMapping("/search/getOrderLists")
	public List getOrderLists(@RequestParam("sql")String sql);
	
	
	
	@RequestMapping("/customRealName/get")
	public CustomRealName get(@RequestParam("id")String id);
		
	@RequestMapping("/customRealName/oneTimePatchUserBank")
    public void oneTimePatchUserBank();
	
	
	
	@GetMapping("ec/custom/findByMobile")
	public Custom findByMobile(@RequestParam("mobile") String mobile);
								
	@RequestMapping(value = "ec/custom/anonymityUserCreate")
	public Custom anonymityUserCreate(@RequestParam("createType")String createType,@RequestParam("value")String value);
	
	@RequestMapping("/orderSaveTrans/saveOrder")
	public boolean SaveOrder(@RequestParam("dto")OrderCombinDTO dto);
	
	@RequestMapping("/order/getTOrderByOrid")
	public List<Map> getTOrderByOrid(@RequestParam("orid") String orid);
	
	@RequestMapping("/order/getTOrdersByOrid")
	public List<TOrder> getTOrdersByOrid(@RequestParam("orid") String orid);
	
	@PostMapping(value="/ec/order/getTOrderByIdcard")
	public String getTOrderByIdcard(@RequestParam("iscenicid") Long iscenicid,@RequestParam("idcard") String idcard);
	
	@RequestMapping("/order/getTOrderlists")
	public List<TOrderlist> getTOrderlists(@RequestParam("orid")String orid, @RequestParam("iscenicid")Long iscenicid);
	
	@RequestMapping("/order/getTOrderlistByOrid")
	public List<TOrderlist> getTOrderlistByOrid(@RequestParam("orid")String orid);
	
	@RequestMapping("/order/getMOrderList")
	public List getMOrderList(@RequestParam("orid")String orid);
	
	@RequestMapping("/order/editOrderSankeCenterl")
	public Map editOrderSankeCenterl(@RequestParam("orderlistInfo")List<TOrderlist> orderlistInfo, @RequestParam("neworderlist")List<?> neworderlist, @RequestParam("orids")String[] orids, @RequestParam("oldorid")String oldorid,
			@RequestParam("iscenicid")String iscenicid, @RequestParam("stdt")String stdt, @RequestParam("ibusiness")String ibusiness, @RequestParam("usid")String usid) throws Exception;
	
	@RequestMapping("/order/getYOrderListChildList")
	public List getYOrderListChildList(@RequestParam("orid")String orid, @RequestParam("iscenicid")String iscenicid);
	
	@RequestMapping("/order/getYOrderChildList")
	public List getYOrderChildList(@RequestParam("orid")String orid);
	
	@RequestMapping("/order/validTorderInfo")
	public Map validTorderInfo(@RequestParam("orid")String orid) throws Exception;
	
	@RequestMapping("/payOrder/updateOrderStatus")
	public int updateOrderStatus(@RequestParam("orid")String orid, @RequestParam("payid")String payid, @RequestParam("mont")String mont, @RequestParam("bank")String bank, @RequestParam("isok")int isok, @RequestParam("ddzt")String ddzt, 
			@RequestParam("orderType")String orderType, @RequestParam("zffs")String zffs,@RequestParam("note")String note, @RequestParam("zhusid")String zhusid);
	
	@RequestMapping("/payOrder/volidateTicketDayControl")
	public List volidateTicketDayControl(@RequestParam("orid")String orid) throws IllegalAccessException, InvocationTargetException;
	
	@RequestMapping("/payOrder/volidateTicketTripControl")
	public List volidateTicketTripControl(@RequestParam("orid")String orid) throws IllegalAccessException, InvocationTargetException, ParseException;
	
	@RequestMapping(value = "order/v1/updateOrderDdzt")
	public void updateOrderDdzt(@RequestParam("orid")String orid,@RequestParam("ddzt")String ddzt);
	
	
	@RequestMapping("/userbank/findUserBank")
	public Userbank findUserBank(@RequestParam("userId")String userId);

	@GetMapping(value = "/order/getMorder/")
	public MOrder getMorderInfo(@RequestParam("orid")String orid);
	
	@GetMapping(value = "/order/getMorderListInfo/")
	public List<MOrder> getMorderListInfo(@RequestParam("orid")String orid);
	
	@RequestMapping("/order/showscenicidview")
	public List<?> showscenicidview(@RequestParam("orid")String orid,@RequestParam("iscenicid")String iscenicid);
	
	@RequestMapping("/order/showscenicidviewByOrid")
	public List<?> showscenicidview(@RequestParam("orid") String orid);
	
	/**
	 * 获取torder信息
	 * @param orid
	 * @param scenicId
	 * @return
	 */
	@GetMapping("/order/getTOrderInfo")
	public TOrder getTOrderInfo(@RequestParam("orid") String orid,@RequestParam("scenicId") String scenicId);

	@RequestMapping("/order/validateOrderInfo")
	public Map validateOrderInfo(@RequestParam("orid") String orid,
			@RequestParam("orderInfo") List<TOrderlist> orderInfo,
			@RequestParam("newInfo") List<OrderPojo> newInfo,
			@RequestParam("usid") String usid,
			@RequestParam("stdt") String stdt,
			@RequestParam("ibusinessid") String ibusinessid,
			@RequestParam("iscenicid") String iscenicid);

	@RequestMapping("/order/editOrderSankeCenter")
	public Map editOrderSankeCenter(@RequestParam("orderlistInfo")String orderlistInfoVal, @RequestParam(name="neworderlist",required=false)String neworderlistVal, @RequestParam("orids")String oridsVal, @RequestParam("oldorid")String oldorid,
			@RequestParam("iscenicid")String iscenicid, @RequestParam("stdt")String stdt, @RequestParam("ibusiness")String ibusiness, @RequestParam("usid")String usid);
	
	@RequestMapping("/order/editOrderCenter")
	public Map editOrderCenter(@RequestParam("orderlistInfo")List<TOrderlist> orderlistInfo, 
			@RequestParam("neworderlist")List<OrderPojo> neworderlist, 
			@RequestParam("orids")String[] orids, 
			@RequestParam("oldorid")String oldorid, 
			@RequestParam("iscenicid")String iscenicid, 
			@RequestParam("stdt")String stdt, 
			@RequestParam("ibusiness")String ibusiness, 
			@RequestParam("usid")String usid) throws Exception;
	
	
	@RequestMapping("/order/getYorderlists")
	public List getYorderlists(@RequestParam("orid")String orid, @RequestParam("iscenicid")String iscenicid);
	
	@RequestMapping("/order/validateInfoIntegrity")
	public Map validateInfoIntegrity(@RequestParam("newticketinfo")List<OrderPojo> newticketinfo, 
			@RequestParam("ibussiness")String ibussiness, 
			@RequestParam("iscenicid")String iscenicid, 
			@RequestParam("orid")String orid, 
			@RequestParam("groupno")String groupno);

	/**
	 * *
	 * Describe:查询业务类型
	 * @param iscenicid
	 * @return
	 * @author huying
	 * Date:2015-4-8
	 */
	@RequestMapping("/order/findList")
	public List findList(@RequestParam("iscenicid")Long iscenicid,@RequestParam("ibusinessid")Long ibusinessid);
	
	/**
     * 
     * Describe:查询价格分组
     * @author:lijingrui
     * @param usid
     * @param iscenicid
     * @return
     * return:String
     * Date:2014-4-16
     */
	@RequestMapping("/order/searchJgfz")
    public String searchJgfz(@RequestParam("usid")String usid,@RequestParam("iscenicid")Long iscenicid);
    
	@RequestMapping("/order/autoChooseRandomSeat")
	public List<?> autoChooseRandomSeat(@RequestParam("date")String date, 
			@RequestParam("ivenueid")Long ivenueid, 
			@RequestParam("ivenueareaid")Long ivenueareaid, 
			@RequestParam("tripid")Long tripid, 
			@RequestParam("bookNum")Long bookNum);

	@RequestMapping("/order/getFilmProduct")
	public Long getFilmProduct(@RequestParam("ivenueareaid")Long ivenueareaid,@RequestParam("itripprdcontrolid")Long itripprdcontrolid);
	
	@RequestMapping("/order/combinationOrder1")
	public OrderCombinDTO combinationOrder(@RequestParam("orid")String orid, @RequestParam("filmproduct")List filmproduct, @RequestParam("custom")Custom custom, @RequestParam("lpr")LprPojo lpr);
	
	@RequestMapping("/order/combinationOrder")
	public Map combinationOrder(@RequestParam("custom")Custom custom, @RequestParam("tickets")List<OrderPojo> tickets, @RequestParam("note")String note, @RequestParam("lprlist")List<LprPojo> lprlist, @RequestParam("isjl")int isjl);
	
	@RequestMapping("/order/splitAllTicketList")
	public Map splitAllTicketList(@RequestParam("list")List<OrderPojo> list);
	
	@RequestMapping("/order/packagingScheme")
    public Map packagingScheme(@RequestParam("ticketlist")List<OrderPojo> ticketlist,@RequestParam("ibusinessid")long ibusinessid,@RequestParam("usid")String usid,@RequestParam("lprlist")List<LprPojo> lprlist) throws IllegalAccessException, InvocationTargetException;

	@RequestMapping("/order/fillLprInfo")
    public void fillLprInfo(@RequestParam("lprs")List<LprPojo> lprs);
    
	@RequestMapping("/order/getTicketPricelist")
    public List<?> getTicketPricelist(@RequestParam("ticketid")Long ticketid, @RequestParam("icrowdkindid")Long icrowdkindid, @RequestParam("tourdate")String tourdate, @RequestParam("ibusinessid")String ibusinessid, @RequestParam("groupno")String groupno);
	
    /**
     * Describe:
     *
     * @param icrowkondpriceid
     * @return return:List Date:2012-2-7
     * @auth:yangguang
     */
	@RequestMapping("/order/getSonticketlist")
    public List getSonticketlist(@RequestParam("icrowkondpriceid")Long icrowkondpriceid);
    
	@RequestMapping("/order/getTripInfo")
    public String getTripInfo(@RequestParam("tripid")Long tripid, @RequestParam("ivenueid")Long ivenueid, @RequestParam("ivenueareaid")Long ivenueareaid, @RequestParam("tourdate")String tourdate, @RequestParam("iscenicid")String iscenicid,
    		@RequestParam("itickettypeid")String itickettypeid);
    
	@RequestMapping("/order/getNumberControl")
    public String getNumberControl(@RequestParam("tripid")Long tripid, @RequestParam("ivenueid")Long ivenueid, @RequestParam("ivenueareaid")Long ivenueareaid, @RequestParam("tourdate")String tourdate);

	@RequestMapping("/order/getTicketPrice")
    public double getTicketPrice(@RequestParam("itickettypeid")String itickettypeid, @RequestParam("tourDate")String tourDate, @RequestParam("icrowdkindpriceid")String icrowdkindpriceid, @RequestParam("ibusinessid")String ibusinessid);

	@RequestMapping("/order/ticketbackrule")
    public  String ticketbackrule(@RequestParam("itickettypeid")Long itickettypeid,@RequestParam("iscenicid")Long iscenicid,@RequestParam("lgtp")String lgtp);
    
	@RequestMapping("/order/getflByTime")
    public String getflByTime(@RequestParam("gzid")Long gzid,@RequestParam("time")String time,@RequestParam("jsfs")String jsfs);
    
	@RequestMapping("/order/getTorderlistByProductInfo")
    public TOrderlist getTorderlistByProductInfo(@RequestParam("itickettypeid")Long itickettypeid,@RequestParam("orid")String orid,@RequestParam("iscenicid")Long iscenicid,@RequestParam("icrowdkindid")Long icrowdkindid,
    		@RequestParam("dtstartdate")String dtstartdate,@RequestParam("dtenddate")String dtenddate,@RequestParam("tourdate")String tourdate,@RequestParam("productcontrolid")Long productcontrolid);

	@RequestMapping("/order/getNumberControllData")
    public List getNumberControllData(@RequestParam("iticketid")String iticketid, @RequestParam("date")String date, @RequestParam("controlltype")String controlltype);
    
	@RequestMapping("/order/validationEditDatePrice")
    public boolean validationEditDatePrice(@RequestParam("orid")String orid, @RequestParam("stdt")String stdt, @RequestParam("iscenicid")String iscenicid,@RequestParam("ibussiness")String ibussiness);
    
	@RequestMapping("/order/validationEditDate")
    public boolean validationEditDate(@RequestParam("orid")String orid,@RequestParam("stdt")String stdt,@RequestParam("enddate")String enddate,@RequestParam("iscenicid")String iscenicid);
    
	@RequestMapping("/order/validationEditScheme")
    public boolean validationEditScheme(@RequestParam("orid")String orid, @RequestParam("stdt")String stdt, @RequestParam("iscenicid")String iscenicid,@RequestParam("ibussiness")String ibussiness);
    
	@RequestMapping("/order/getMaxCanEditDate")
    public Integer getMaxCanEditDate(@RequestParam("orid")String orid);
    
	@RequestMapping("/order/getTZorderlists")
    public List getTZorderlists(@RequestParam("orid")String orid,@RequestParam("id")TOrderlistId id);
    
    @PostMapping("/order/getRealnemeList")
	public List getRealnemeList(@RequestParam("iscenicid")String iscenicid,@RequestParam("idcard")String idcard);

	@PostMapping("/home/getDomain")
	public List<Domain> getDomain(@RequestParam("url")String url,@RequestParam("s")String s);
	
	/**
	 * 根据用户已经存在的信息，查询用户信息
	 * @return
	 */
	@PostMapping(value = "/custom/v1/getCustomByCondition")
	public List<?> getCustomByCondition(@RequestBody Custom custom);
	
	/**
	 * 获取最大订单号
	 * @param saleid
	 * @return
	 */
	@GetMapping(value = "/order/getMaxNo")
	public String getMaxNo(@RequestParam("saleid") String saleid);
	
	/**
	 * 根据TorderList主键Id获取TOrderList信息
	 * @param orderlistid
	 * @param orid
	 * @param iscenicid
	 * @return
	 */
	@GetMapping(value = "/order/getTOrderList")
	public TOrderlist getTOrderList(@RequestParam("orderlistid") String orderlistid,@RequestParam("orid") String orid,@RequestParam("iscenicid") Long iscenicid);
	/**
	 * 保存订单
	 * @param lorder
	 * @return
	 */
	@PutMapping(value="lOrder/v1/saveLorder")
	public void saveLorder(@RequestBody LOrder lorder);
	/**
	 * 通过orid获取订单
	 * @param orid
	 * @return
	 */
	@GetMapping(value="lOrder/v1/getLorderByOrid")
	public List<LOrder> getLorderByOrid(@RequestParam("orid") String orid);
	/**
	 * 根据主键id获取Lorder实体
	 * @param lorderId
	 * @return
	 */
	@PostMapping(value="lOrder/v1/getLorderByLOrderId")
	public Object getLorderByLOrderId(@RequestBody LOrderId lorderId);
	/**
	 * 更新LOrder实体
	 * @param lorderId
	 */
	@PostMapping(value="lOrder/v1/updateLOrderId")
	public void updateLOrder(@RequestBody LOrder lorder);
	
	/**
	 * 保存LorderList实体
	 * @param lorderList
	 * @return
	 */
	@PutMapping(value="lorderList/v1/saveLorderList")
	public void saveLorderList(@RequestBody LOrderlist lorderList);
	/**
	 * 通过orid和iscenicid获取实体
	 * @param orid
	 * @param iscenicid
	 * @return
	 */
	@GetMapping(value="lorderList/v1/getLOrderlistByids")
	public List<LOrderlist> getLOrderlistByids(@RequestParam("orid")String orid,@RequestParam("iscenicid")Long iscenicid);
	/**
	 * 根据参数获取LZorderlist
	 * @param orid
	 * @param iscenicid
	 * @param orderlistid
	 * @return
	 */
	@GetMapping(value="lzorderlist/v1/getLZorderlistByParam")
	public List getLZorderlistByParam(@RequestParam("orid")String orid,@RequestParam("iscenicid")Long iscenicid,@RequestParam("orderlistid")Long orderlistid);
	/**
	 * 保存LZorderlist实例
	 * @param lzorderlist
	 */
	@PutMapping(value="lzorderlist/v1/saveLZorderlist")
	public void saveLZorderlist(@RequestBody LZorderlist lzorderlist);
	
	@GetMapping(value="v1/getPaymentBillByOrid")
	public List getPaymentBillByOrid(@RequestParam("orid") String orid);
	
	/**
	 * Describe:根据积分规则增加用户积分
	 * @param usid 用户名称
	 * @param jfcode 积分规则代码
	 * @param amount 消费数量(不是积分数量)
	 * @return return:boolean Date:2012-8-25
	 */
	@PostMapping("/userjf/addUserJf")
	public boolean addUserJf(@RequestParam("usid") String usid, @RequestParam("jfcode") String jfcode,
			@RequestParam("amount") Long amount);
	
	/**
	* @Title: getOrderInfo  
	* @Description: TODO 根据条件查询订单信息
	* @param @param param 订单号 	手机号 	身份证号
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@GetMapping(value="/order/v1/getOrderListInfo")
	public List<Map> getOrderListInfo(@RequestParam("param")String param,@RequestParam("msql") String msql);
	
	/**
	* @Title: updateTOrder  
	* @Description: TODO 修改TOrder订单信息  
	* @param @param tOrder
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	@PostMapping(value = "/tOrder/v1/updateTOrder")
	public Boolean updateTOrder(@RequestBody TOrder tOrder);
	
	/**
	* @Title: getTOrderListMapInfoByOridAndIscenicid  
	* @Description: TODO 查询查询网上订单明细信息 
	* @param @param orId
	* @param @param iscenicid
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/tOrder/v1/getTOrderListMapInfoByOridAndIscenicid")
	public List<Map> getTOrderListMapInfoByOridAndIscenicid(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid);

	
	@PostMapping(value="order/findTorderValues")
	public List<TOrder> findTorderValues(@RequestParam("idcard")String idcard);
	
	@GetMapping("/order/getTOrdersList")
	public List<TOrder> getTOrdersList(@RequestParam("orid") String orid);
	
	@PostMapping(value="/order/getMorder")
	public MOrder getMorder(@RequestParam("orid") String orid);
	
	@PostMapping(value="/order/updateTorder")
	public void updateTorder(@RequestBody TOrder torder);
	
	
	@PostMapping("/order/getTOrderInfos")
	public List<Map<String, Object>> getTOrderInfos(@RequestParam("orid") String orid, @RequestParam("iscenicids") String iscenicids);
	
	@PostMapping("/order/getZOrderInfos")
	public List<Map<String, Object>> getZOrderInfos(@RequestParam("orid") String orid, @RequestParam("iscenicids") String iscenicids,@RequestParam("orderlistid") Long orderlistid);
	
	@PostMapping("/order/getSatordertabList")
	public List getSatordertabList(@RequestParam("orderId")String orderId,@RequestParam("iscenicid") String iscenicid,@RequestParam("orderlistid") String orderlistid,@RequestParam("zorderlistid") String zorderlistid);

	@PostMapping("/order/getTorderauto")
	public List<Map> getTorderauto(@RequestParam("carno") String carno, @RequestParam("iscenicid") Long iscenicid);

	
	/**
	* @Title: getTZOrderMapList  
	* @Description: TODO 获取订单拆分信息 
	* @param @param orId 订单id
	* @param @param iscenicid 景区id
	* @param @param orderlistid 订单明细id
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/tOrder/v1/getTZOrderMapList")
	public List<Map> getTZOrderMapList(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid,@RequestParam("orderlistid")String orderlistid);

	@PostMapping("/order/getTzOrderList")
	public List<TZorderlist> getTzOrderList(@RequestParam("orid")String orid,@RequestParam("iscenicids") String iscenicids);
	
	@PostMapping("/order/getTzOrderLists")
	public List<Map> getTzOrderLists(@RequestParam("orid")String orid,@RequestParam("iscenicids") String iscenicids);
	
	@PostMapping("/order/updateT_order")
	public ResultBean updateT_order(@RequestParam("orid")String orid, @RequestParam("iscenicid")Long iscenicid,
			@RequestParam("iemployeeid") Long iemployeeid,@RequestParam("mont") Double mont);

	
	/**
	 * 根据hql获取电商信息
	 * @param query
	 * @return
	 */
	@GetMapping(value = "userjf/v1/find")
	public List find(@RequestParam("query") String query);
	
	/**
	* @Title: getTZOrderMapListByOrIdAndIscenicid  
	* @Description: TODO 查询网上订单出票明细信息  
	* @param @param orId
	* @param @param iscenicid
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@GetMapping(value = "/lzorderlist/v1/getTZOrderMapListByOrIdAndIscenicid")
	public List<Map> getTZOrderMapListByOrIdAndIscenicid(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid);
	
	/**
	* @Title: getYOrderInfoById  
	* @Description: TODO 根据YOrder对象信息 
	* @param @param orId
	* @param @param iscenicid
	* @param @return    参数  
	* @return YOrder    返回类型  
	* @throws
	 */
	@GetMapping(value = "/order/v1/getYOrderInfoById")
	public YOrder getYOrderInfoById(@RequestParam("orId")String orId,@RequestParam("iscenicid")Long iscenicid );
	
	@PostMapping(value = "/order/v1/updateYOrder")
	public Boolean updateYOrder(@RequestBody YOrder yOrder);
	
	/**
	* @Title: updateMOrder  
	* @Description: TODO 修改MOrder主订单信息 
	* @param @param morder
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	@PostMapping(value = "/order/v1/updateMOrder")
	public Boolean updateMOrder(@RequestBody MOrder morder);
	
	@RequestMapping("/order/getTOrderForListByOrid")
	public List<TOrder> getTOrderForListByOrid(@RequestParam("orid") String orid);
	/**
	 * 更新Morder信息
	 * @param mOrder
	 * @return
	 */
	@PostMapping(value = "mOrder/v1/updateMOrder")
	public Boolean updateMOrder1(@RequestBody MOrder mOrder);
	
	/**
	 * 更新YOrder信息
	 * @param yOrder 
	 * @return
	 */
	@PostMapping(value = "yOrder/v1/updateYOrder")
	public Boolean updateYOrder1(@RequestBody YOrder yOrder);
	/**
	 * 	查找TOrderList信息
	 * @param orderlistid
	 * @param orid
	 * @param iscenicid
	 * @return
	 */
	@GetMapping(value = "tOrderList/v1/findTOrderList")
	public List<TOrderlist> findTOrderList(@RequestParam("orid") String orid,@RequestParam("iscenicid") Long iscenicid);
	
	@GetMapping(value = "tZOrderList/v1/updateYOrder")
	public List<TZorderlist> getTZorderlist(@RequestParam("orderlistid") Long orderlistid,@RequestParam("orid")String orid,@RequestParam("iscenicid")Long iscenicid);
}
