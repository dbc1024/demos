package com.ectrip.ec.order.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.hotelcenter.service.iservice.IHotelOrderCenterService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("hotelOrder")
@Api(tags = "网上订单-酒店订单管理相关接口")
public class HotelOrderAuditController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelOrderAuditController.class);
	
	
	@Autowired
	private SysparService sysService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private IHotelOrderCenterService hotelordercenterService;
	
	/**
	 * 
	 * Describe:酒店订单查询 
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2012-6-28
	 */
	@PostMapping("v1/list")
    @ApiOperation(value = "酒店订单列表查询接口")
	public ResponseBean queryhotelAllOrderSearch(@RequestBody(required=false) QueryOrder order, @RequestParam(required=false) String pdtp, 
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		
		if (pageSize == null) {pageSize = PAGE_SIZE;}
		if (page == null) {page = 1;}
		
		if(order == null || order.getStartDate() == null) {
			order = new QueryOrder();
			order.setRadiobutton1(0);// 用户选择方式
			order.setRadiobutton2(0);// 日期选择方式
			order.setRadiobutton3(0);// 其它
			order.setStartDate(Tools.getTodayString());// 开始日期
			order.setEndDate(Tools.getTodayString());// 结束日期
			order.setDdzt("99");// 表示所有订单状态
		}
		
		
		Map<String, Object> data = new HashMap<String, Object>();//封装返回结果数据结构
		
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			// 订单状态
			List ddztlist = new ArrayList();
			ddztlist = sysService.queryByPmkyPmcds("DDZT","pmcd in('99','00','02','11','95')");
//			getRequest().setAttribute("ddztlist", ddztlist);
			data.put("ddztlist", ddztlist);

			
			//改为在服务商模块单独调用酒店下拉列表接口
//			List scenicList = esbticketService.findListesbticket(esfemployeetab, pdtp, 0, "05");
//			Esbscenicareatab provider = new Esbscenicareatab();
//			provider.setIscenicid(0L);
//			provider.setSzscenicname("所有服务商");
//			scenicList.add(0, provider);
//			getRequest().setAttribute("scenictList", scenicList);

			// 查询逾期未领的订单数
			int size = hotelordercenterService.queryYuQiOrderNumb(esfemployeetab,pdtp);
//			getRequest().setAttribute("yuqinumb", size);
			data.put("yuqinumb", size);

			// 显示趟次
			if (1 == order.getRadiobutton1()) {// 用户
				if (order.getUsid() == null || "".equals(order.getUsid())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.usid.required");// 指定下单用户名
				}
			}

			// 验检日期
			if (0 == order.getRadiobutton2() || 1 == order.getRadiobutton2()
					|| 2 == order.getRadiobutton2()) {// 日期
				if (order.getStartDate() == null || "".equals(order.getStartDate())) {
					return new ResponseBean(ERROR_CODE_400, "error.date.rzti.required");// 请输入开始日期
				}
				if (order.getEndDate() == null || "".equals(order.getEndDate())) {
					return new ResponseBean(ERROR_CODE_400, "error.date.ldti.required");// 请输入结束日期
				}
				if (order.getStartDate() != null && !order.getStartDate().equals("")) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(order.getStartDate()).matches();
					if (b == false) {
						return new ResponseBean(ERROR_CODE_400, "error.rzti.duplicate");// 起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期
					}
				}
				if (order.getEndDate() != null && !order.getEndDate().equals("")) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(order.getEndDate()).matches();
					if (b == false) {

						return new ResponseBean(ERROR_CODE_400, "error.ldti.duplicate");// 截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期
					}
				}
				if (order.getStartDate().compareTo(order.getEndDate()) > 0) {
					return new ResponseBean(ERROR_CODE_400, "error.date.rzti.big.ldti");// 起始日期不能大于截止日期"
				}

				long daynumb = Tools.getDayNumb(order.getStartDate(), order.getEndDate()) - 1;
				if (daynumb > 30) {
					return new ResponseBean(ERROR_CODE_400, "error.date.maxday");// 日期时间段间隔最多为30天
				}
			}

			if (order.getDdzt() == null || "".equals(order.getDdzt())) {
				order.setDdzt("99");
			}
		
			PaginationSupport ps = hotelordercenterService.queryAllOrder(esfemployeetab, order, pdtp, page, pageSize, null);
			data.put("ps", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("酒店订单查询列表异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "酒店订单列表查询异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功！", data);
		
	}
	
	
	
	/**
	 * 
	 * Describe:订单其它方式查询 
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2012-6-28
	 */
	@PostMapping("v1/listBy")
    @ApiOperation(value = "按订单或联系人查询接口")
	public ResponseBean queryhotelAllOrderBySearch(@RequestBody(required=false) QueryOrder order, @RequestParam(required=false) String pdtp, 
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		
		if (pageSize == null) {pageSize = PAGE_SIZE;}
		if (page == null) {page = 1;}
		
		
		Map<String, Object> data = new HashMap<String, Object>();//封装返回结果数据结构
		
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			// 订单状态
			List ddztlist = new ArrayList();
			ddztlist = sysService.queryByPmkyPmcds("DDZT","pmcd in('99','00','02','11','18','20','21','95','98')");
//			getRequest().setAttribute("ddztlist", ddztlist);
			data.put("ddztlist", ddztlist);

			//改为在服务商模块单独调用酒店下拉列表接口
//			List scenicList = esbticketService.findListesbticket(esfemployeetab, "06", 0, "04");
//			Esbscenicareatab provider = new Esbscenicareatab();
//			provider.setIscenicid(0L);
//			provider.setSzscenicname("所有服务商");
//			scenicList.add(0, provider);
//			getRequest().setAttribute("scenictList", scenicList);

			// 查询逾期未领的订单数
			int size = hotelordercenterService.queryYuQiOrderNumb(esfemployeetab,pdtp);
//			getRequest().setAttribute("yuqinumb", size);
			data.put("yuqinumb", size);

			if (0 == order.getRadiobutton3()) {// 订单号
				if (order.getOrid() == null || "".equals(order.getOrid())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.orid.required");// 请输入订单号
				}
				if (order.getOrid() != null && !"".equals(order.getOrid())) {
					if (order.getOrid().length() != 17) {
						return new ResponseBean(ERROR_CODE_400, "error.order.orid.length.duplicate");// "请输入17位订单号码
					}
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(order.getOrid().substring(0, 8)).matches();
					if (b == false) {
						return new ResponseBean(ERROR_CODE_400, "error.order.orid.date.duplicate");// 订单号的前八位是预定时的日期，日期格式错误
					}
					p = Pattern.compile("^[A-Za-z0-9]+$");
					b = p.matcher(order.getOrid().substring(8)).matches();
					if (b == false) {
						return new ResponseBean(ERROR_CODE_400, "error.order.orid.last.duplicate");// 订单号的后九位是订单的流水号，请输入数字
					}
				}

			} else if (1 == order.getRadiobutton3()) {// 联系人姓名
				if (order.getOrnm() == null || "".equals(order.getOrnm())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.ornm.required");// 请输入联系人
				}
				order.setStrornm(order.getOrnm());

			} else if (2 == order.getRadiobutton3()) {// 联系人证件号码
				if (order.getOrhm() == null || "".equals(order.getOrhm())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.orhm.required");// 请输入联系人证件号码
				}
			} else if (3 == order.getRadiobutton3()) {// 逾期未领订单

			}else if (5 == order.getRadiobutton3()) { // 支付订单号
				if (order.getPayorid() == null || "".equals(order.getPayorid())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.payid.required");// 请输入支付单号
				}
			}

			
			PaginationSupport ps = hotelordercenterService.queryAllOrder(esfemployeetab, order,pdtp, page, pageSize, null);
			data.put("ps", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("按订单或联系人查询酒店列表异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "按订单或联系人查询酒店列表异常", "异常信息："+ e.getMessage());
		}
		
		

		return new ResponseBean(SUCCESS_CODE_200, "操作成功！", data);
	}
	
	
	/**
	 * 酒店订单审核进入
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * Date:2012-3-4
	 */
	@GetMapping("v1/hotelOrderAuditEnter")
	@ApiOperation("酒店订单审核进入")
	public ResponseBean hotelOrderAuditEnter(@RequestParam String orid, @RequestParam Long iticketId, @RequestParam String stdt){
		
		
		Map<String, Object> data = new HashMap<String, Object>();//封装返回结果数据结构
		
		try {
			if(orid==null || "".equals(orid)){
				return new ResponseBean(ERROR_CODE_400, "error.orid.required");//订单号不能为空
			}
			if(iticketId==null || "".equals(iticketId) || 0L==iticketId){
				return new ResponseBean(ERROR_CODE_400, "error.productid.required");//服务商编号不能为空
			}
			if(stdt==null || "".equals(stdt)){
				return new ResponseBean(ERROR_CODE_400, "error.hotel.rzti.required");//入住日期不能为空
			}
			
			MOrder morder = (MOrder)this.hotelordercenterService.get(MOrder.class, orid);
			if(morder==null){
				return new ResponseBean(ERROR_CODE_400, "error.orid.isnot.duplicate");//此订单系统中不存在
			}else{
				//查询要审核的订单
				List hotelorderlist = hotelordercenterService.queryHotelOrder(orid, stdt, iticketId);
//				getRequest().setAttribute("hotelorderlist", hotelorderlist);
				data.put("hotelorderlist", hotelorderlist);
				
			}
			//订单状态
			String strddzts = queryddzt(orid, morder.getDdzt(), morder.getZffs(),iticketId);
			if(strddzts == null || strddzts == "") {
				return new ResponseBean(ERROR_CODE_400, "垃圾作废订单无法审核");
			}
			List ddztlist = sysService.queryByPmkyPmcds("ORCZ", strddzts);
			getRequest().setAttribute("ddztlist", ddztlist);
			data.put("ddztlist", ddztlist);
			
			TOrderId tid=new TOrderId(orid, iticketId);
			TOrder torder=(TOrder) hotelordercenterService.get(TOrder.class, tid);
			
			data.put("torder", torder);
			
			String hycode=torder.getNoteh();
			
			data.put("hycode", hycode);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("酒店订单审核进入异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "酒店订单审核进入异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功！", data);
	}

	/**
	 * 酒店订单审核保存
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-4
	 */
	@GetMapping("v1/hotelOrderAuditSave")
	@ApiOperation("酒店订单审核保存")
	public ResponseBean hotelOrderAuditSave(@RequestParam String orid, @RequestParam Long iticketId, @RequestParam String stdt, 
			@RequestParam String scenictype, @RequestParam String hycode, @RequestParam String orcz){
		
		
		Esfemployeetab esfemployeetab = sysService.currentUser();
		
		if(orid==null || "".equals(orid)){
			return new ResponseBean(ERROR_CODE_400, "error.orid.required");//订单号不能为空
		}	
		if(iticketId==null || 0L==iticketId){
			return new ResponseBean(ERROR_CODE_400, "error.productid.required");//服务商编号不能为空
		}
		
		if("08".equals(scenictype)){
			if(hycode==null||"".equals(hycode)){
				return new ResponseBean(ERROR_CODE_400, "商品货运号不能为空！");
			}
		}
		
		MOrder morder = (MOrder)this.hotelordercenterService.get(MOrder.class, orid);
		
		if(morder==null){
			return new ResponseBean(ERROR_CODE_400, "error.orid.isnot.duplicate");//此订单系统中不存在
		}else{
//			if("95".equals(morder.getDdzt())){
				//修改订单状态
				TOrder torder = (TOrder)this.hotelordercenterService.get(TOrder.class, new TOrderId(orid,iticketId));
				if(torder==null){
					return new ResponseBean(ERROR_CODE_400, "error.orid.isnot.duplicate");//此订单系统中不存在
				}
				String nowDt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(nowDt.compareTo(torder.getDtstartdate())<0){
					return new ResponseBean(ERROR_CODE_400, "未到入住时间，不能进行审核！");
				}
				Syslog syslog = new Syslog();
				Orderlog orderlogs = new Orderlog();
				syslog.setIemployeeid(esfemployeetab.getIemployeeid());
				orderlogs.setIemployeeid(esfemployeetab.getIemployeeid());
				
				hotelordercenterService.updateHotelOrder(orid, orcz, iticketId,hycode, syslog, orderlogs);
//				mbmessageService.sendMessageNew(torder.getOrph(), "0008", new String[] { orid });
//				this.addActionMessage("订单审核操作成功！");
				return new ResponseBean(SUCCESS_CODE_200, "订单审核操作成功！");
//			}else{
//				this.addActionError("订单已经审核，不需再次审核！");
//				return INPUT;
//			}
		}
	}
	
	
	
	public String queryddzt(String orid, String ddzt, String zffs,Long iscenicid) {
//		Esbscenicareatab esb=(Esbscenicareatab) this.genericService.get(Esbscenicareatab.class, iscenicid);
		
		List providers = ticketService.getProvidersByIds(iscenicid.toString());
		if(providers==null || providers.size()==0) {
			return "";
		}
		Map provider = (Map)providers.get(0);
		String scenictype = provider.get("scenictype").toString();
		
		//服务商为酒店的时候
		if(scenictype.equals("06")){
			if (ddzt.equals("95")) {// 需审核
				if ("01".equals(zffs)) {// 网上支付
					return "pmcd in ('01','04','06')";
				} else if ("05".equals(zffs)) {// 前台现付
					return "pmcd in ('01','02','06')";
				} else if ("11".equals(zffs)) {// 担保付款
					return "pmcd in ('01','03','06')";
				} else
				{
					return "pmcd in ('01','04','06')";	
				}
			} else if (ddzt.equals("00") || ddzt.equals("18")) {// 未付款
				if ("01".equals(zffs)) {// 网上支付
					return "pmcd in ('05','06')";
				} else if ("05".equals(zffs)) {// 前台现付
					return "pmcd in ('08','06')";
				} else if ("11".equals(zffs)) {// 担保付款
					return "pmcd in ('09','06')";
				} else
				{
					return "pmcd in ('04','05','06')";	
				}
			} else if (ddzt.equals("02") || "01".equals(ddzt)) {// 已付款
				if ("01".equals(zffs) || "06".equals(zffs)) {// 网上支付
					return "pmcd in ('05','07')";
				}else{
					return "pmcd in ('05','07')";
				}
			} else if (ddzt.equals("20")) {
				return "pmcd in ('06')";
			}else if(ddzt.equals("11")){
				if ("01".equals(zffs)|| "06".equals(zffs)) {// 网上支付
					return "pmcd in ('07')";
				} else if ("05".equals(zffs)) {// 前台现付
					return "pmcd in ('08')";
				} else if ("11".equals(zffs)) {// 担保付款
					return "pmcd in ('09')";
				} else
				{
					return "pmcd in ('07','08','09')";	
				}
			}
		}else  if(scenictype.equals("08")){//商品 
			if (ddzt.equals("02") || "01".equals(ddzt)) {// 已付款
				if ("01".equals(zffs) || "06".equals(zffs)) {// 网上支付
					return "pmcd in ('10','11')";
				}else{
					return "pmcd in ('10','11')";
				}
			}else if(ddzt.equals("10")){
				return "pmcd in ('11')";
			}else if(ddzt.equals("00")){
				return "pmcd in ('12','10','11')";
			}else{
				return "pmcd in ('10','11')";
			}
		}
		
		return "";
	}
}
