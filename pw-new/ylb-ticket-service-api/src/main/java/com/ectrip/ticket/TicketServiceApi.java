package com.ectrip.ticket;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;

//@RequestMapping("ticket")
public interface TicketServiceApi {
	
	/**
     * 根据登录人得到服务商列表
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
	@GetMapping(value = "provider/v1/getScenicList")
    public List getScenicList(@RequestParam("esfemployeetab") String esfemployeetab, 
    		@RequestParam("scenictype") String scenictype, 
    		@RequestParam("isjd") int isjd, 
    		@RequestParam("isonlyjq") String isonlyjq, 
    		@RequestParam("groupid") String groupid, 
    		@RequestParam("isHqyatu") boolean isHqyatu);
	/**
	 * 根据服务商ID集合获取服务商信息列表
	 * ids数据格式[12,541,625,51]
	 */
	@GetMapping(value = "/provider/v1/listByIds")
	public List getProvidersByIds(@RequestParam("ids") String ids);
	
	/**
	 * 根据票种id集合获取票种信息
	 * itickettypeids数据格式[1,2,3,4]
	 */
	@GetMapping(value = "ticket/v1/ticketTypeListByIds")
	public List getTicketTypeListByIds(@RequestParam("itickettypeids") String itickettypeids);
	
	/**
	 * 根据票种id集合获取人群种类信息
	 * icrowdkindids数据格式[1,2,3,4]
	 */
	@GetMapping(value = "/crowdKind/v1/crowdKindListByIds")
	public List crowdKindListByIds(@RequestParam("icrowdkindids") String icrowdkindids);
	
	@GetMapping(value = "/provider/queryProviderList")
	public List<?> queryProviderList();
	
	@PostMapping("/provider/queryProviderCompany")
	public ProviderCompany queryProviderCompany(@RequestParam("iscenicid")Long iscenicid);
	
	@PostMapping("/provider/findpmcd")
	public String findpmcd(@RequestParam("pmky")String pmky, @RequestParam("pmva")String pmva);
	
	@PostMapping("/provider/query")
    public Esbscenicareatab query(@RequestParam("scenicId")Long scenicId);
	
	@PostMapping("/statisics/getNumjifenset")
	public Numjifenset getNumjifenset(@RequestParam("iscenicid")String iscenicid);
	
	@PostMapping("/statisics/getSalesRule")
	public Numjifensetlist getSalesRule(@RequestParam("itickettypeid")Long itickettypeid, @RequestParam("nid")Long nid,@RequestParam("icrowid")Long icrowid,@RequestParam("ibusinessid")Long ibusinessid,@RequestParam("date")String date);
	
	@PostMapping("/statisics/getSalesRule2")
	public Numjifensetlist getSalesRule(@RequestParam("itickettypeid")String itickettypeid,@RequestParam("nid")String nid);
	
	@PostMapping("/statisics/getJifenByUser")
	public String getJifenByUser(@RequestParam("starttime")String starttime,@RequestParam("endtime")String endtime,@RequestParam("usid")String usid,@RequestParam("jflb")Long jflb,@RequestParam("isecnicid")Long isecnicid);
	
	/**
     * 保存库存信息
     * @param stockOrderInfos 订单数据列表
     */
	@PostMapping("/stock/saveStockDetails")
	public void saveStockDetails(@RequestParam("stockOrderInfos") String stockOrderInfos,@RequestParam("isCheck") boolean isCheck);
	
	/**
     * 删除不存的库存数据
     * @param orid  订单号
     * @param providerid    服务商ID
     * @param seq   主键--可不填
     * @param noInclude 不包含的数据--可不填
     */
	@PostMapping("/stock/deleteStockDetails")
    public void deleteStockDetails(@RequestParam("orid") String orid, @RequestParam("providerid") Long providerid, @RequestParam("seq") Long seq, @RequestParam("noInclude") String noInclude);
	
	/**
     * 检查用户库存
     * @param stockOrderInfos
     * @return
     */
	@PostMapping("/stock/checkCustomStock")
    public String checkCustomStock(@RequestParam("stockOrderInfos") String stockOrderInfos);
	
	/**
     * 检查库存信息
     * @param stockOrderInfos
     * @return
     */
	@PostMapping("/stock/checkStock")
    public String checkStock(@RequestParam("stockOrderInfos") String stockOrderInfos);
	
	@PostMapping("/stock/selectStockDetail")
	public Stockdetailtab selectStockDetail(@RequestBody StockOrderInfo stockOrderInfo);
	
	@PostMapping("/timeSharing/UpdateStock")
	public void UpdateStock(@RequestParam("timeId")Long timeId,@RequestParam("productId")String productId,@RequestParam("count")int count,@RequestParam("flag")String flag) throws Exception;
	
	@GetMapping(value="trip/v1/getTripByTripids")
	public List getTripByTripids(@RequestParam("tripids") String tripids);
	
	@GetMapping(value="venuearea/v1/getVenueareaByIvenueids")
	public List getVenueareaByIvenueids(@RequestParam("ivenueids") String ivenueids);

}

