package com.ectrip.ec.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custom.service.iservice.ICustomService;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.home.service.iservice.IHomeService;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.ec.report.system.order.service.iservice.IQueryOrderService;
import com.ectrip.ec.user.service.iservice.ICustomInfoService;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("allOrderManage")
@Api(tags = "网上订单-预定订单查询相关接口")
public class AllOrderManageController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(AllOrderManageController.class);
	
	@Autowired
	private SysparService sysService;
	
	@Autowired
	private TicketService ticketService;
	
	
	@Autowired
	private IHomeService homeService;
	
	@Autowired
	private ICustomService customService;
	
	@Autowired
	private ICustomInfoService custominfoService;
	
	@Autowired
	private IQueryOrderService queryorderService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 订单查询列表查询
	 * 
	 */
	@PostMapping("v1/list")
    @ApiOperation(value = "订单查询列表查询接口")
	public ResponseBean queryAllOrderSearch(@RequestBody(required=false) QueryOrder order, @RequestParam(required=false) String otausid, 
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
			order.setIscenicid(0L);
		}
		
		Map<String, Object> data = new HashMap<String, Object>();//封装返回结果数据结构
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			// 订单状态
			List ddztlist = new ArrayList();
			ddztlist = sysService.queryByPmkyPmcds("DDZT", "pmcd in('99','00','02','11','23','24','95','27')");
//			getRequest().setAttribute("ddztlist", ddztlist);
			data.put("ddztlist", ddztlist);
			
			
			//获取域名
			String serverNamer = request.getServerName();
	        List<Domain> listDomain = homeService.getDomain(serverNamer,"1");
	        List listScenic = null;
	        boolean isHqyatu = false;
	        Domain domain = listDomain.get(0);
	        if(1==domain.getSeq() || 2 == domain.getSeq()){
	        	isHqyatu = true;
	        }else{
//	        	listScenic = companyInfoService.getComscenicsId(Long.parseLong(domain.getGroupId()));
	        }
//			List scenicList = providerService.getScenicList(esfemployeetab, "", 0, "01",domain.getGroupId(),isHqyatu);
	        List scenicList = ticketService.getScenicList(JSONArray.toJSONString(esfemployeetab), "", 0, "01",domain.getGroupId(),isHqyatu);
			
	        
	        
	        Esbscenicareatab provider = new Esbscenicareatab();
			provider.setIscenicid(0L);
			provider.setSzscenicname("所有服务商");
			scenicList.add(0, provider);
//			getRequest().setAttribute("scenictList", scenicList);
			data.put("scenictList", scenicList);
			
			//OTA用户
			List clist = this.customService.find("from Custom c where c.ttlb='99' and c.status='01' order by c.usid ");
//			getRequest().setAttribute("clist", clist);
			data.put("clist", clist);
		
			if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1()|| 3 == order.getRadiobutton1()) {// 用户
				if (order.getUsid() == null || "".equals(order.getUsid())) {
//					this.addActionError(getText("error.order.usid.required"));// 指定下单用户名
					return new ResponseBean(ERROR_CODE_400, "error.order.usid.required");
				}
			}
			
			if (5 == order.getRadiobutton1()) {// ota用户
				if (order.getUsid() == null || "".equals(order.getUsid())) {
					if(null==clist || clist.isEmpty()){
//						this.addActionError("没有可查询的OTA用户");
						return new ResponseBean(ERROR_CODE_400, "error.order.usid.required");
					}
				}
			}

			// 验检日期
			if (0 == order.getRadiobutton2() || 1 == order.getRadiobutton2()
					|| 2 == order.getRadiobutton2() || 3 == order.getRadiobutton2()|| 4 == order.getRadiobutton2()) {// 日期
				if (order.getStartDate() == null || "".equals(order.getStartDate())) {
//					this.addActionError(getText("error.date.rzti.required"));// 请输入开始日期
					return new ResponseBean(ERROR_CODE_400, "error.date.rzti.required");
				}
				if (order.getEndDate() == null || "".equals(order.getEndDate())) {
//					this.addActionError(getText("error.date.ldti.required"));// 请输入结束日期
					return new ResponseBean(ERROR_CODE_400, "error.date.ldti.required");
				}
				if (order.getStartDate() != null && !order.getStartDate().equals("")) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(order.getStartDate()).matches();
					if (b == false) {

//						this.addActionError(getText("error.rzti.duplicate"));// 起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期
						return new ResponseBean(ERROR_CODE_400, "error.rzti.duplicate");
					}
				}
				if (order.getEndDate() != null && !order.getEndDate().equals("")) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(order.getEndDate()).matches();
					if (b == false) {

//						this.addActionError(getText("error.ldti.duplicate"));// 截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期
						return new ResponseBean(ERROR_CODE_400, "error.ldti.duplicate");
					}
				}
				if (order.getStartDate().compareTo(order.getEndDate()) > 0) {
//					this.addActionError(getText("error.date.rzti.big.ldti"));// 起始日期不能大于截止日期"
					return new ResponseBean(ERROR_CODE_400, "error.date.rzti.big.ldti");
				}
			}

			if (order.getDdzt() == null || "".equals(order.getDdzt())) {
				order.setDdzt("99");
			}

			if (2 == order.getRadiobutton1()||1 == order.getRadiobutton1()) {// 主用户
				// 得到主用户下的子用户
				String usids = custominfoService.queryCustoms(order.getUsid());
				order.setUsids(usids);
			}
			if(5 == order.getRadiobutton1()){
				StringBuffer usids = new StringBuffer();
				//ota用户
				if(null!=otausid && !"".equals(otausid)){
					usids.append("'"+otausid+"'");
				}else{
					//OTA用户
					if(null!=clist && !clist.isEmpty()){
						for(int i=0;i<clist.size();i++){
							Custom c = (Custom)clist.get(i);
							if(i>0){
								usids.append(",'"+c.getUsid()+"' ");
							}else{
								usids.append("'"+c.getUsid()+"'");
							}
						}
					}
				}
				order.setUsids(usids.toString());
			}
			
			PaginationSupport ps = queryorderService.queryAllOrder(esfemployeetab, order, page, pageSize, null);
			data.put("ps", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单查询列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单查询列表查询异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功！", data);
	}
	
	
	/**
	 * 订单其它方式查询 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-10-31
	 */
	@PostMapping("v1/listBy")
    @ApiOperation(value = "按订单或联系人查询接口")
	public ResponseBean queryAllOrderBySearch(@RequestBody QueryOrder order, @RequestParam(required=false) String otausid, 
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		
		if (pageSize == null) {pageSize = PAGE_SIZE;}
		if (page == null) {page = 1;}
		
		
		Map<String, Object> data = new HashMap<String, Object>();//封装返回结果数据结构
		
		try {
			
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			// 订单状态
			List ddztlist = new ArrayList();
			ddztlist = sysService.queryByPmkyPmcds("DDZT", "pmcd in('99','00','02','11','23','24','95','27')");
//			getRequest().setAttribute("ddztlist", ddztlist);
			data.put("ddztlist", ddztlist);
			
			
			//获取域名
			String serverNamer = request.getServerName();
	        List<Domain> listDomain = homeService.getDomain(serverNamer,"1");
	        List listScenic = null;
	        boolean isHqyatu = false;
	        Domain domain = listDomain.get(0);
	        if(1==domain.getSeq() || 2 == domain.getSeq()){
	        	isHqyatu = true;
	        }else{
//	        	listScenic = companyInfoService.getComscenicsId(Long.parseLong(domain.getGroupId()));
	        }
//			List scenicList = providerService.getScenicList(esfemployeetab, "", 0, "01",domain.getGroupId(),isHqyatu);
	        List scenicList = ticketService.getScenicList(JSONArray.toJSONString(esfemployeetab), "", 0, "01",domain.getGroupId(),isHqyatu);
			
	        
	        
	        Esbscenicareatab provider = new Esbscenicareatab();
			provider.setIscenicid(0L);
			provider.setSzscenicname("所有服务商");
			scenicList.add(0, provider);
//			getRequest().setAttribute("scenictList", scenicList);
			data.put("scenictList", scenicList);
			
			//OTA用户
			List clist = this.customService.find("from Custom c where c.ttlb='99' and c.status='01' order by c.usid ");
//			getRequest().setAttribute("clist", clist);
			data.put("clist", clist);


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

			} else if (2 == order.getRadiobutton3()) {// 联系人证件号码
				if (order.getOrhm() == null || "".equals(order.getOrhm())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.orhm.required");// 请输入联系人证件号码
				}
			} else if (3 == order.getRadiobutton3()) {// 逾期未领订单

			} else if (4 == order.getRadiobutton3()) {// 行程单号
				if (order.getSztravelbillno() == null || "".equals(order.getSztravelbillno())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.xcid.required");// 请输入行程单号
				}
			} else if (5 == order.getRadiobutton3()) { // 支付订单号
				if (order.getPayorid() == null || "".equals(order.getPayorid())) {
					return new ResponseBean(ERROR_CODE_400, "error.order.payid.required");// 请输入支付单号
				}
			}

			
			PaginationSupport ps = queryorderService.queryAllOrder(esfemployeetab, order, page, pageSize, null);
			data.put("ps", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单查询列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单查询列表查询异常", "异常信息："+ e.getMessage());
		}
		

		return new ResponseBean(SUCCESS_CODE_200, "操作成功！", data);
	}
	
	
	/**
	 * 订单解锁
	 * @return
	 */
	@GetMapping("v1/initOrderStatus")
	@ApiOperation("订单解锁")
	public ResponseBean initOrderStatus(@RequestParam String orid, @RequestParam String iscenicid){
		
		try {
			if(!StringUtils.isBlank(orid) && !StringUtils.isBlank(iscenicid)){
				
				TOrder torder = (TOrder) orderService.get(TOrder.class, new TOrderId(orid,Long.parseLong(iscenicid)));
				torder.setIschupiao(0L);
				orderService.update(torder);
			}
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单解锁异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单解锁异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(SUCCESS_CODE_200, "解锁成功");
	}
	
	
}
