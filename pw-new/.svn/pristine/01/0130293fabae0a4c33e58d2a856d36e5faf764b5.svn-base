package com.ectrip.ticket.provider.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.provider.service.IBusinessService;
import com.ectrip.ticket.provider.service.ICrowdKindPriceService;
import com.ectrip.ticket.provider.service.ICrowdKindService;
import com.ectrip.ticket.provider.service.ITicketTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "crowdKindPrice")
@Api(tags = "票务服务-票务信息设置-价格管理")
public class CrowdKindPriceController {
	
	private static final Logger LOGGER = LogManager.getLogger(CrowdKindPriceController.class);
	@Autowired
	private ICrowdKindPriceService crowdkindpriceService;
	@Autowired
	private ICrowdKindService crowdkindService;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private ITicketTypeService tickettypeService;
	
	@Autowired
	private SysService sysService;
	
	/**
	 * 售价列表 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-9-30
	 */
	@ApiOperation(value = "价格管理列表接口")
	@GetMapping(value = "/v1/crowdKindPriceViewList")
	public ResponseBean crowdKindPriceViewList(@RequestParam Long itickettypeid,@RequestParam int page,@RequestParam int pageSize) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			ps = crowdkindpriceService.getCrowskindPriceList(itickettypeid, page, pageSize,
					null);
			Edmtickettypetab product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, itickettypeid);
			/*String istechan = this.getRequest().getParameter("istechan");
			if(istechan!=null && "1".equals(istechan)){
				return "techan";
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("请求价格分组列表接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(ps);
		return responseBean;
	}
	/**
	 * Describe:售价增加页面数据初始化接口
	 * @auth:jiyong
	 */
	@ApiOperation(value = "价格管理新增价格数据初始化接口")
	@GetMapping(value = "/v1/crowdKindPriceAddInitData")
	public ResponseBean crowdKindPriceAdd(@RequestParam Long iTicketTypeId) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Edmcrowdkindpricetab crowdkindprice = null;
		// 根据产品编号查询出产品
		try {
			Edmtickettypetab product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, iTicketTypeId);
			Esbscenicareatab provider = null;
			crowdkindprice = new Edmcrowdkindpricetab();
			if (product != null) {
				provider = (Esbscenicareatab) this.crowdkindpriceService.get(
						Esbscenicareatab.class, product.getIscenicid());
				crowdkindprice.setScenictype(provider.getScenictype());//服务商类别
				crowdkindprice.setStrtickettype(product.getSztickettypename());//产品名称
				crowdkindprice.setIcrowdkindpricecode(product.getSztickettypecode()); //价格编号
				if(provider.getScenictype().equals("01")){
					/*List crowdkindList = crowdkindService.crowdKindList();
					getRequest().setAttribute("crowdkindlist", crowdkindList);
					getRequest().setAttribute("jsList", this.sysparService.retrieve("JSFZ"));*/
				}else if(provider.getScenictype().equals("06")||provider.getScenictype().equals("07")||provider.getScenictype().equals("08")||provider.getScenictype().equals("09")||provider.getScenictype().equals("10")){
					List crowdkindList =new ArrayList();
					Edpcrowdkindtab edpcrowdkindtab=new Edpcrowdkindtab();
					edpcrowdkindtab.setIcrowdkindid(1L);
					edpcrowdkindtab.setSzcrowdkindname("成人");
					crowdkindList.add(edpcrowdkindtab);
					//getRequest().setAttribute("crowdkindlist", crowdkindList);
					//getRequest().setAttribute("jsList", this.sysparService.query("JSFZ", " id.pmcd in ('0000')"));
				}else{
					List crowdkindList =new ArrayList();
					Edpcrowdkindtab edpcrowdkindtab=new Edpcrowdkindtab();
					edpcrowdkindtab.setIcrowdkindid(1L);
					edpcrowdkindtab.setSzcrowdkindname("成人");
					crowdkindList.add(edpcrowdkindtab);
					//getRequest().setAttribute("crowdkindlist", crowdkindList);
					//getRequest().setAttribute("jsList", this.sysparService.query("JSFZ", " id.pmcd in ('0000')"));
				}
			}

			// 查出人群种类
			

			// 查出业务类型
			List businessList = businessService.businessList();
//		getRequest().setAttribute("businesslist", businessList);
			
			//获取用户级别
//		getRequest().setAttribute("jsList", this.sysparService.retrieve("JSFZ"));
			
			crowdkindprice.setItickettypeid(crowdkindprice.getItickettypeid());//产品id
			crowdkindprice.setStartdata(Tools.getTodayString());// 开始时间
			crowdkindprice.setEnddata(Tools.getDate(Tools.getTodayString(), 1));// 结束时间
			crowdkindprice.setIsequence(0L);// 排序
			crowdkindprice.setByisuse(1L);// 状态
			crowdkindprice.setWeekendprice(0.0);//周未售价
			crowdkindprice.setListingprice(0.0);//挂牌价
			crowdkindprice.setMactualsaleprice(0.0);//实际售价
			crowdkindprice.setJsprice(0.0);//结算价
			crowdkindprice.setIsnet(new Long(0));
			crowdkindprice.setIsscene(new Long(0));
			crowdkindprice.setIsscancode(new Long(0));
			crowdkindprice.setNote3("0");
			crowdkindprice.setIpeoplenumrange(0L);//基数
			crowdkindprice.setInote1(0L);
			crowdkindprice.setInote3(0L);//是否允许成团人数
			
			//判断此产品的产品类别是否是产品附加服务(编号为120),如果是，那么价格属性中的是否网上销售必须是否
			if(product.getBycategorytype().equals("120")){
				crowdkindprice.setIsnet(0L);//是否可网上销售
			}else{
				crowdkindprice.setIsnet(1L);//是否可网上销售
			}
			
			crowdkindprice.setIsscene(1L);//现场销售
			Sysparv5 sysparv5 = sysService.findOne("PRTP", "套票");
			String rpdtp = "";
			if(sysparv5==null){
				rpdtp="0010";//套票
			}else{
				rpdtp=sysparv5.getPmcd();
			}		
			if (product.getBycategorytype().equals(rpdtp)) {//套票 
				
				
				List prolist = crowdkindpriceService.QureyProviderProductList(crowdkindprice.getItickettypeid());
				
				List productlist = new ArrayList();
				if(prolist.size()>=1){
					for (int i = 0; i < prolist.size(); i++) {
						Edmtickettypetab ticketype = new Edmtickettypetab();
						try {
							BeanUtils.populate(ticketype, (Map)prolist.get(i)) ;
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} 
						Edmticketcomposepricetab composeprice = new Edmticketcomposepricetab();
						
						composeprice.setSzscenicname(ticketype.getSzscenicname());//服务商名称
						composeprice.setSztickettypename(ticketype.getSztickettypename());//产品名称
						composeprice.setItickettypeid(ticketype.getItickettypeid());//票类型id（产品id）
						composeprice.setMactualsaleprice(0.0);//单价
						composeprice.setNumb(new Long(1));//数量
						composeprice.setJsprice(0.0);//结算价
						
						productlist.add(composeprice);
					}
				}
//			getRequest().setAttribute("prolist", productlist);
			}
			
			
//			List priceList = crowdkindpriceService.getpriceListbyprno(product.getItickettypeid());
//		getRequest().setAttribute("isuse", priceList.size());
			
			
			/*String techan = this.getRequest().getParameter("istechan");
			if(techan!=null && "1".equals(techan)){
				return "techan";
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("请求价格分组添加页面数据初始化接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(crowdkindprice);
		return responseBean;

	}
	
	/**
	 * 获取人群种类，业务类型 价格分组初始化数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "价格管理新增价格数据初始化接口,获取人群种类，业务类型 价格分组初始化数据")
	@GetMapping(value = "/v1/getCrowdKindPriceSelectData")
	public ResponseBean getCrowdKindPriceSelectData() {
		ResponseBean responseBean = new ResponseBean();
		Map<String,Object> result = null;
		int code = 200;
		String msg = "请求成功";
		try {
			//人群种类信息
			List crowdkindList = crowdkindService.crowdKindList();
			//价格分组（用户级别）
			List retrieve = sysService.retrieve("JSFZ");
			//业务类型
			List businessList = businessService.businessList();
			result = new HashMap<String,Object>();
			result.put("crowdkindList", crowdkindList);
			result.put("retrieve", retrieve);
			result.put("businessList", businessList);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("人群种类，业务类型 价格分组初始化数据接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(result);
		return responseBean;
	}
	/**
	 * 售价修改初始化页面 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-9-30
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "价格管理修改页面初始化数据接口")
	@GetMapping(value = "/v1/crowdKindPriceEditInitData")
	public ResponseBean crowdKindPriceEditInitData(@RequestParam Long iCrowdKindPriceid) {
		/*Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}*/

		/*if (crowdkindprice.getIcrowdkindpriceid() == null
				|| "".equals(crowdkindprice.getIcrowdkindpriceid())) {
			this.addActionError(getText("crowdkindprice.priceid.required"));// 价格编号不能为空
			return INPUT;
		}*/
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Edmcrowdkindpricetab crowdkindprice = null;
		try {
			crowdkindprice = crowdkindpriceService
					.queryCrowdKindPrice(iCrowdKindPriceid);
			// 根据产品编号查询出产品
			Edmtickettypetab product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, crowdkindprice.getItickettypeid());
			Esbscenicareatab provider = null;
			if (product != null) {
				provider = (Esbscenicareatab) this.crowdkindpriceService.get(
						Esbscenicareatab.class, product.getIscenicid());
				crowdkindprice.setScenictype(provider.getScenictype());// 服务商类别
				if(provider.getScenictype().equals("01")){
					/*List crowdkindList = crowdkindService.crowdKindList();
					getRequest().setAttribute("crowdkindlist", crowdkindList);
					getRequest().setAttribute("jsList", this.sysparService.retrieve("JSFZ"));*/
				}else if(provider.getScenictype().equals("06")||provider.getScenictype().equals("07")||provider.getScenictype().equals("08")||provider.getScenictype().equals("09")||provider.getScenictype().equals("10")){
					List crowdkindList =new ArrayList();
					Edpcrowdkindtab edpcrowdkindtab=new Edpcrowdkindtab();
					edpcrowdkindtab.setIcrowdkindid(1L);
					edpcrowdkindtab.setSzcrowdkindname("成人");
					crowdkindList.add(edpcrowdkindtab);
//				getRequest().setAttribute("crowdkindlist", crowdkindList);
//				getRequest().setAttribute("jsList", this.sysparService.query("JSFZ", " id.pmcd in ('0000')"));
				}else{
					List crowdkindList =new ArrayList();
					Edpcrowdkindtab edpcrowdkindtab=new Edpcrowdkindtab();
					edpcrowdkindtab.setIcrowdkindid(1L);
					edpcrowdkindtab.setSzcrowdkindname("成人");
					crowdkindList.add(edpcrowdkindtab);
//				getRequest().setAttribute("crowdkindlist", crowdkindList);
//				getRequest().setAttribute("jsList", this.sysparService.query("JSFZ", " id.pmcd in ('0000')"));
				}
			}

			// 查出人群种类
			// 查出业务类型
			List businessList = businessService.businessList();
//		getRequest().setAttribute("businesslist", businessList);

			// 获取用户级别
			Sysparv5 sysparv5 = sysService.findOne("PRTP", "套票");
			String rpdtp = "";
			if (sysparv5 == null) {
				rpdtp = "0010";// 套票
			} else {
				rpdtp = sysparv5.getPmcd();
			}

			if (product.getBycategorytype().equals(rpdtp)) {// 套票
				// 查询出产品列表
				List prolist = crowdkindpriceService.QureyProviderProductList(
						crowdkindprice.getItickettypeid(),
						crowdkindprice.getIcrowdkindpriceid());
				List productlist = new ArrayList();
				// 查询出价格拆分表数据
				List composepriceList = crowdkindpriceService
						.QureyComposePriceList(crowdkindprice
								.getIcrowdkindpriceid());
				Long[] itickettypeids = new Long[composepriceList.size()];

				if (prolist.size() >= 1) {
					int s = 0;
					for (int i = 0; i < prolist.size(); i++) {
						Edmtickettypetab ticketype = new Edmtickettypetab();
						try {
							BeanUtils.populate(ticketype, (Map) prolist.get(i));// 转化成票类型对象（产品）
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						Edmticketcomposepricetab composeprice = new Edmticketcomposepricetab();// 价格拆分表

						composeprice.setSzscenicname(ticketype.getSzscenicname());// 服务商名称
						composeprice.setSztickettypename(ticketype
								.getSztickettypename());// 产品名称
						composeprice.setItickettypeid(ticketype.getItickettypeid());// 票类型id（产品id）

						for (int m = 0; m < composepriceList.size(); m++) {
							Edmticketcomposepricetab prices = (Edmticketcomposepricetab) composepriceList
									.get(m);

							if (prices.getItickettypeid().equals(
									ticketype.getItickettypeid())) {
								itickettypeids[m] = prices.getItickettypeid();// 票类id
								composeprice.setMactualsaleprice(prices
										.getMactualsaleprice());// 单价
								composeprice.setNumb(new Long(prices.getNumb()));// 数量
								composeprice.setJsprice(prices.getJsprice());// 结算价
								composeprice.setItickettypeids(itickettypeids);
								composeprice.setItickettypeidsTemp(true);
								break;
							} else {
								composeprice.setItickettypeidsTemp(false);
							}

						}
						productlist.add(composeprice);

					}

				}
			}

			List priceList = crowdkindpriceService.getpriceListbyprno(product
					.getItickettypeid());
			/*if (priceList.size() == 1) {
				getRequest().setAttribute("isuse", 0);
			} else {
				getRequest().setAttribute("isuse", priceList.size());
			}*/
			
			/*String techan = this.getRequest().getParameter("istechan");
			if(techan!=null && "1".equals(techan)){
				return "techan";
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("请求价格管理修改页面数据初始化接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(crowdkindprice);
		return responseBean;
	}
	/**
	 * 售价删除 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-9-30
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "价格管理删除页面初始化接口")
	@DeleteMapping(value = "/v1/crowdKindPriceDel")
	public ResponseBean crowdKindPriceDel(@RequestParam Long iCrowdKindPriceId) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Edmcrowdkindpricetab crowdkindprice = null;
		try {
			crowdkindprice = crowdkindpriceService
					.queryCrowdKindPrice(iCrowdKindPriceId);

			Sysparv5 sysparv5 = sysService.findOne("PRTP", "套票");
			String rpdtp = "";
			if (sysparv5 == null) {
				rpdtp = "0010";// 套票
			} else {
				rpdtp = sysparv5.getPmcd();
			}

			// 得到价格级别
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd(crowdkindprice.getNote1());
			sysid.setPmky("JSFZ");
			Sysparv5 sys1 = (Sysparv5) this.crowdkindpriceService.get(Sysparv5.class,
					sysid);
			if (sys1 != null) {
				crowdkindprice.setNote1(sys1.getPmva());
			}

			// 根据产品编号查询出产品
			Edmtickettypetab product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, crowdkindprice.getItickettypeid());
			Esbscenicareatab provider = null;
			if (product != null) {
				provider = (Esbscenicareatab) this.crowdkindpriceService.get(
						Esbscenicareatab.class, product.getIscenicid());
				crowdkindprice.setScenictype(provider.getScenictype());// 服务商类别
			}
			if (product.getBycategorytype().equals(rpdtp)) {// 套票
				List productlist = new ArrayList();
				// 查询出价格拆分表数据
				List composepriceList = crowdkindpriceService
						.QureyComposePriceList(crowdkindprice
								.getIcrowdkindpriceid());
				for (int m = 0; m < composepriceList.size(); m++) {
					Edmticketcomposepricetab prices = (Edmticketcomposepricetab) composepriceList
							.get(m);// 价格拆分表
					Edmtickettypetab product1 = (Edmtickettypetab) this.crowdkindpriceService
							.get(Edmtickettypetab.class, prices.getItickettypeid());
					if (product1 != null) {
						prices.setSztickettypename(product1.getSztickettypename());// 产品名称
						Esbscenicareatab scenicarea = (Esbscenicareatab) this.crowdkindpriceService
								.get(Esbscenicareatab.class, product1.getIscenicid());
						prices.setSzscenicname(scenicarea.getSzscenicname());// 服务商名称
					}
					productlist.add(prices);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("请求价格管理删除页面数据初始化接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(crowdkindprice);
		return responseBean;
	}

	/**
	 * 售价查看 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-9-30
	 */
	@ApiOperation(value = "价格管理查看页面初始化接口")
	@GetMapping(value = "/v1/crowdKindPriceView")
	public ResponseBean crowdKindPriceView(Long iCrowdKindPriceId) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Edmcrowdkindpricetab crowdkindprice = null;
		try {
			crowdkindprice = crowdkindpriceService.queryCrowdKindPrice(iCrowdKindPriceId);
			Sysparv5 sysparv5 = sysService.findOne("PRTP", "套票");
			String rpdtp = "";
			if (sysparv5 == null) {
				rpdtp = "0010";// 套票
			} else {
				rpdtp = sysparv5.getPmcd();
			}

			// 得到价格级别
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd(crowdkindprice.getNote1());
			sysid.setPmky("JSFZ");
			Sysparv5 sys1 = (Sysparv5) this.crowdkindpriceService.get(Sysparv5.class,
					sysid);
			if (sys1 != null) {
				crowdkindprice.setNote1(sys1.getPmva());
			}

			// 根据产品编号查询出产品
			Edmtickettypetab product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, crowdkindprice.getItickettypeid());
			Esbscenicareatab provider = null;
			if (product != null) {
				provider = (Esbscenicareatab) this.crowdkindpriceService.get(
						Esbscenicareatab.class, product.getIscenicid());
				crowdkindprice.setScenictype(provider.getScenictype());// 服务商类别
			}

			if (product.getBycategorytype().equals(rpdtp)) {// 套票
				List productlist = new ArrayList();
				// 查询出价格拆分表数据
				List composepriceList = crowdkindpriceService
						.QureyComposePriceList(crowdkindprice
								.getIcrowdkindpriceid());
				for (int m = 0; m < composepriceList.size(); m++) {
					Edmticketcomposepricetab prices = (Edmticketcomposepricetab) composepriceList
							.get(m);// 价格拆分表
					Edmtickettypetab product1 = (Edmtickettypetab) this.crowdkindpriceService
							.get(Edmtickettypetab.class, prices.getItickettypeid());
					if (product1 != null) {
						prices.setSztickettypename(product1.getSztickettypename());// 产品名称
						Esbscenicareatab scenicarea = (Esbscenicareatab) this.crowdkindpriceService
								.get(Esbscenicareatab.class, product1.getIscenicid());
						prices.setSzscenicname(scenicarea.getSzscenicname());// 服务商名称
					}
					productlist.add(prices);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("请求价格管理查看页面数据初始化接口异常", e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(crowdkindprice);
		return responseBean;
	}
	/**
	 * 售价保存 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:String Date:2011-9-30
	 */
	@ApiOperation(value = "价格管理新增、修改、删除操作")
	@PostMapping(value = "/v1/crowdKindPriceSave")
	public ResponseBean crowdKindPriceSave(@ApiParam(value = "1：新增，2：修改，3：删除") @RequestParam int act,@RequestBody Edmcrowdkindpricetab crowdkindprice) {
		
		/*Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}*/
		List Xpricelist = new ArrayList();
		Edmtickettypetab product = null;
		if (act == 1 || act == 2) {

			
			product = (Edmtickettypetab) this.crowdkindpriceService.get(
						Edmtickettypetab.class,crowdkindprice.getItickettypeid());
				
				//添加是否旅游卡购票限制逻辑：一个景区下只有一张票允许旅游卡购票
				if("1".equals(crowdkindprice.getNote3())){//前提：当前操作对象为旅游卡票，才做查重才有意义
					
					PaginationSupport typePs = tickettypeService.getTickettypeList(null, product.getIscenicid(), 1, 99, null, "01");
					List<Map> typeList = (List<Map>)typePs.getItems();
					
					for (Map tickettype : typeList) {
						Long itickettypeid = (Long)tickettype.get("itickettypeid");
						
						PaginationSupport pricePs = crowdkindpriceService.getCrowskindPriceList(itickettypeid, 1, 99,null);
						List<Map> priceList = (List<Map>)pricePs.getItems();
						
						for (Map map : priceList) {
							
							if (act == 2) {
								//修改时判断逻辑：除开本修改对象，还有其他可旅游卡购买的票，既不满足条件
								if(((Long)map.get("icrowdkindpriceid")).intValue() != crowdkindprice.getIcrowdkindpriceid().intValue()){
									
									String isTourcardBuy = (String)map.get("note3");//是否旅游卡购票,0否，1是
									if("1".equals(isTourcardBuy)){
										/*this.addActionError(getText("该服务商已存在旅游卡票"));
										return INPUT;*/
										return new ResponseBean(400, "该服务商已存在旅游卡票");
									}
								}
							}else {
								//新增时判断逻辑：查询所有票，若有旅游卡票既不满足条件
								String isTourcardBuy = (String)map.get("note3");//是否旅游卡购票,0否，1是
								if("1".equals(isTourcardBuy)){
									/*this.addActionError(getText("该服务商已存在旅游卡票"));
									return INPUT;*/
									return new ResponseBean(400, "该服务商已存在旅游卡票");
								}
							}
						}
					}
			}

			/*if (act = 2) {
				if (crowdkindprice.getIcrowdkindpriceid() == null
						|| "".equals(crowdkindprice.getIcrowdkindpriceid())) {
					this.addActionError(getText("crowdkindprice.priceid.required"));// 价格编号不能为空
				}
			}
			if (crowdkindprice.getIcrowdkindpricecode() == null
					|| "".equals(crowdkindprice.getIcrowdkindpricecode())) {
				this.addActionError(getText("crowdkindprice.icrowdkindpricecode.required"));
			}*/
			if (crowdkindprice.getIcrowdkindpricecode() != null
					&& !"".equals(crowdkindprice.getIcrowdkindpricecode())) {
				Pattern p = Pattern.compile("^[A-Za-z0-9]{1,10}$");
				boolean a = p.matcher(crowdkindprice.getIcrowdkindpricecode())
						.matches();
				if (a == false) {
					return new ResponseBean(400, "价格编码格式不对");
//					this.addActionError(getText("crowdkindprice.icrowdkindpricecode.duplicate"));// 价格编码格式不对，只能输入由数字或大写英文字母组成的5位字符
				}

				if (crowdkindprice.getIcrowdkindpricecode().length() > 10) {
					return new ResponseBean(400, "价格编码长度不能只能为10位字符");
//					this.addActionError(getText("crowdkindprice.icrowdkindpricecode.length.duplicate"));// 价格编码长度不能只能为10位字符
				}
				if (act == 1) {
					if (this.crowdkindpriceService.getcrowpricecode(
							crowdkindprice.getIcrowdkindpricecode(),
							product.getIscenicid())) {
						return new ResponseBean(400, "价格编码重复");
//						this.addActionError(getText("crowdkindprice.icrowdkindpricecode.kindcueot")); // 价格编码重复
					}
				}
				if (act == 2) {
					Edmcrowdkindpricetab esb = (Edmcrowdkindpricetab) this.crowdkindpriceService
							.get(Edmcrowdkindpricetab.class,
									crowdkindprice.getIcrowdkindpriceid());
					if (!crowdkindprice.getIcrowdkindpriceid().equals(
							esb.getIcrowdkindpriceid())) {
						boolean isuse = this.crowdkindpriceService
								.getcrowpricecode(
										crowdkindprice.getIcrowdkindpricecode(),
										product.getIscenicid());
						if (isuse) {
							return new ResponseBean(400, "价格编码已经存在");
						}
					}
				}
			}

			if (crowdkindprice.getStartdata() == null
					|| "".equals(crowdkindprice.getStartdata())) {
				return new ResponseBean(400, "结束日期不能为空");
//				this.addActionError(getText("error.startdate.required"));// 结束日期不能为空
			}
			if (crowdkindprice.getEnddata() == null
					|| "".equals(crowdkindprice.getEnddata())) {
				return new ResponseBean(400, "结束日期不能为空");
//				this.addActionError(getText("error.enddate.required"));// 结束日期不能为空
			}
			if (crowdkindprice.getStartdata() != null
					&& !"".equals(crowdkindprice.getStartdata())
					&& crowdkindprice.getEnddata() != null
					&& !"".equals(crowdkindprice.getEnddata())) {
				// 判断起止日期的大小
				if (crowdkindprice.getStartdata().compareTo(
						crowdkindprice.getEnddata()) > 0) {
					/*this.addActionError(getText("error.startdate.big.enddate"));// 起始日期不能大于截止日期
					return INPUT;*/
					return new ResponseBean(400, "起始日期不能大于截止日期");
				}
				// 校验时间格式
				if (crowdkindprice.getStartdata() != null
						&& !"".equals(crowdkindprice.getStartdata())) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(crowdkindprice.getStartdata())
							.matches();
					if (b == false) {
						return new ResponseBean(400, "起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期");
//						this.addActionError(getText("error.startdate"));// 起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期
					}
				}
				if (crowdkindprice.getEnddata() != null
						&& !"".equals(crowdkindprice.getEnddata())) {
					Pattern p = Pattern
							.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
					boolean b = p.matcher(crowdkindprice.getEnddata())
							.matches();
					if (b == false) {
//						this.addActionError(getText("error.enddate"));// 截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期
						return new ResponseBean(400, "截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期");
					}
				}
			}
			if (crowdkindprice.getIpeoplenumrange() == null
					|| "".equals(crowdkindprice.getIpeoplenumrange())) {
				return new ResponseBean(400, "基数不能为空");
//				this.addActionError(getText("crowdkindprice.range.required"));// 基数不能为空
			} else {
				if (product.getByusage() != null && product.getByusage() == 1) {
					if (crowdkindprice.getIpeoplenumrange() != null
							&& !"".equals(crowdkindprice.getIpeoplenumrange())
							&& crowdkindprice.getIpeoplenumrange() == 1) {
						return new ResponseBean(400, product
								.getSztickettypename() + "是一票多人的,不能实名制!");
						/*this.addActionError(getText(product
								.getSztickettypename() + "是一票多人的,不能实名制!"));*/
					}
				}
				 //增加了，IC卡是否可以实名制；
				if (product.getBymaketicketway() != null
						&& product.getBymaketicketway().equals("01")&&product.getBymediatype()!=null&&product.getBymediatype().equals("00")) {
					if (crowdkindprice.getIpeoplenumrange() != null
							&& !"".equals(crowdkindprice.getIpeoplenumrange())
							&& crowdkindprice.getIpeoplenumrange() == 1) {
						/*this.addActionError(getText(product
								.getSztickettypename() + "是预制票,不支持实名制!"));*/
						return new ResponseBean(400, product
								.getSztickettypename() + "是预制票,不支持实名制!");
					}
				}
			}
			if (crowdkindprice.getListingprice() == null
					|| "".equals(crowdkindprice.getListingprice())|| crowdkindprice.getListingprice()<=0) {
//				this.addActionError("挂牌价必须大于0!");// 挂牌价不能为空
				return new ResponseBean(400, "挂牌价必须大于0!");
			} else {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(
						crowdkindprice.getListingprice().toString()).matches();
				if (b == false) {
//					this.addActionError(getText("error.price"));// 格式不正确，最多可精确到小数点后两位
					return new ResponseBean(400, "格式不正确，最多可精确到小数点后两位");
				}
			}
			if (crowdkindprice.getMactualsaleprice() == null
					|| "".equals(crowdkindprice.getMactualsaleprice())) {
//				this.addActionError(getText("crowdkindprice.saleprice.required"));// 实际售价不能为空
				return new ResponseBean(400, "实际售价不能为空");
			} else {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(
						crowdkindprice.getMactualsaleprice().toString())
						.matches();
				if (b == false) {
					return new ResponseBean(400, "格式不正确，最多可精确到小数点后两位");
//					this.addActionError(getText("error.price"));// 格式不正确，最多可精确到小数点后两位
				}
			}
			if (crowdkindprice.getJsprice() == null
					|| "".equals(crowdkindprice.getJsprice())
					|| crowdkindprice.getJsprice() == 0) {
				crowdkindprice.setJsprice(crowdkindprice.getMactualsaleprice());
			} else {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(crowdkindprice.getJsprice().toString())
						.matches();
				if (b == false) {
//					this.addActionError(getText("error.price"));// 格式不正确，最多可精确到小数点后两位
					return new ResponseBean(400, "格式不正确，最多可精确到小数点后两位");
				}
			}
			if (crowdkindprice.getWeekendprice() == null
					|| "".equals(crowdkindprice.getWeekendprice())) {
//				this.addActionError(getText("crowdkindprice.weekprice.required"));// 周末价不能为空
				return new ResponseBean(400, "周末价不能为空");
			} else {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(
						crowdkindprice.getWeekendprice().toString()).matches();
				if (b == false) {
//					this.addActionError(getText("error.price"));// 格式不正确，最多可精确到小数点后两位
					return new ResponseBean(400, "格式不正确，最多可精确到小数点后两位");
				}
			}
			if (crowdkindprice.getIsequence() == null
					|| "".equals(crowdkindprice.getIsequence())) {// 排序
				crowdkindprice.setIsequence(0L);
			} else {
				Pattern p = Pattern.compile("^[0-9]{1,20}$");
				boolean b = p.matcher(crowdkindprice.getIsequence().toString())
						.matches();
				if (b == false) {
					/*this.addActionError(getText("error.number"));// 格式不对
					return INPUT;*/
					return new ResponseBean(400, "格式不对");
				}
			}

			// 组合票
			// 根据产品编号查询出产品
			product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, crowdkindprice.getItickettypeid());
			Esbscenicareatab provider = null;
			if (product != null) {
				provider = (Esbscenicareatab) this.crowdkindpriceService.get(
						Esbscenicareatab.class, product.getIscenicid());

				if (provider.getScenictype() != null
						&& provider.getScenictype().equals("10")) {
					if (crowdkindprice.getIcrowdkindid() != null
							&& crowdkindprice.getIcrowdkindid() != 1) {
						/*this.addActionError(getText("租车产品价格中只能添加成人票(人群)价格!"));
						return INPUT;*/
						return new ResponseBean(400, "租车产品价格中只能添加成人票(人群)价格!");
					}
				}

			}

			Edmticketcomposepricetab edmcomposeprice = null;
			double sumpric = 0.00;
			double sumjspric = 0.00;

			if ("0010".equals(product.getBycategorytype())) {/*// 套票
				if (composeprice == null) {
					this.addActionError("请选择子产品");
					return INPUT;
				}
				Long[] zprno = composeprice.getItickettypeids();// 产品数组
				if (zprno == null || "".equals(zprno)) {
					this.addActionError("请选择子产品");
					return INPUT;
				}
				int numb = 0;// 数量
				double pric = 0;// 单价
				double jspric = 0;// 结算价

				for (int i = 0; i < zprno.length; i++) {
					edmcomposeprice = new Edmticketcomposepricetab();// 价格拆分表

					Edmtickettypetab tickettype = (Edmtickettypetab) this.genericService
							.get(Edmtickettypetab.class, zprno[i]);
					edmcomposeprice.setZpdno(tickettype.getIscenicid());
					if (getRequest().getParameter("numb" + zprno[i]) == null
							|| getRequest().getParameter("numb" + zprno[i])
									.equals("")) {

						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]数量不能为空");
						return INPUT;
					}
					try {

						numb = Integer.parseInt(getRequest().getParameter(
								"numb" + zprno[i]));
					} catch (Exception e) {

						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]数量是正整数");
						return INPUT;
					}
					if (numb < 0) {

						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]数量是正整数");
						return INPUT;
					}
					if (getRequest().getParameter("pric" + zprno[i]) == null
							|| getRequest().getParameter("pric" + zprno[i])
									.equals("")) {
						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]价格不能为空");
						return INPUT;
					}
					if (getRequest().getParameter("jspric" + zprno[i]) == null
							|| getRequest().getParameter("pric" + zprno[i])
									.equals("")) {
						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]结算价格不能为空");
						return INPUT;
					}

					try {
						pric = Double.parseDouble(getRequest().getParameter(
								"pric" + zprno[i]));

					} catch (Exception e) {

						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]价格必须为大于零的整数或小数");
						return INPUT;

					}
					try {
						jspric = Double.parseDouble(getRequest().getParameter(
								"jspric" + zprno[i]));

					} catch (Exception e) {

						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]结算价格必须为大于零的整数或小数");
						return INPUT;
						return new ResponseBean(400,"子产品[" + provider.getSzscenicname()
						+ "__" + tickettype.getSztickettypename()
						+ "]结算价格必须为大于零的整数或小数");

					}
					if (pric < 0) {
						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]价格必须为大于零的整数或小数");
						return INPUT;
						return new ResponseBean(400, "子产品[" + provider.getSzscenicname()
						+ "__" + tickettype.getSztickettypename()
						+ "]价格必须为大于零的整数或小数");

					}
					if (jspric < 0) {
						this.addActionError("子产品[" + provider.getSzscenicname()
								+ "__" + tickettype.getSztickettypename()
								+ "]结算价格必须为大于零的整数或小数");
						return INPUT;
						return new ResponseBean(400, "子产品[" + provider.getSzscenicname()
						+ "__" + tickettype.getSztickettypename()
						+ "]结算价格必须为大于零的整数或小数");

					}

					edmcomposeprice.setItickettypeid(zprno[i]);// 产品id
					edmcomposeprice.setNumb(Long.parseLong(getRequest()
							.getParameter("numb" + zprno[i])));// 数量
					edmcomposeprice.setMactualsaleprice(pric);// 单价
					edmcomposeprice.setJsprice(jspric);// 结算价

					sumpric = sumpric + pric * edmcomposeprice.getNumb();// 总价
					sumjspric = sumjspric + jspric * edmcomposeprice.getNumb();// 总结算价

					Xpricelist.add(edmcomposeprice);

				}

				sumpric = Double.parseDouble(String.format("%.2f", sumpric));
				if (crowdkindprice.getMactualsaleprice() != sumpric) {// 实际售价与子产品总价不等时
					return new ResponseBean(400, "子产品的价格总和必须等于套票的价格");
					this.addActionError("子产品的价格总和必须等于套票的价格");
					return INPUT;
				}

				sumjspric = Double
						.parseDouble(String.format("%.2f", sumjspric));
				if (crowdkindprice.getJsprice() != sumjspric) {// 实际结算价与子产品结算价不等时
					return new ResponseBean(400, "子产品的结算价格总和必须等于套票的结算价格");
					this.addActionError("子产品的结算价格总和必须等于套票的结算价格");
					return INPUT;
				}

			*/} else {// 非组全票
				edmcomposeprice = new Edmticketcomposepricetab();// 价格拆分表
				edmcomposeprice.setItickettypeid(crowdkindprice
						.getItickettypeid());// 产品id
				edmcomposeprice.setNumb(new Long(1));// 数量
				edmcomposeprice.setMactualsaleprice(crowdkindprice
						.getMactualsaleprice());// 单价
				edmcomposeprice.setJsprice(crowdkindprice.getJsprice());// 结算价
				// edmcomposeprice.setZpdno(crowdkindprice.getit)
				Xpricelist.add(edmcomposeprice);
			}

			// 判断此产品的产品类别是否是产品附加服务(编号为120),如果是，那么价格属性中的是否网上销售必须是否
			product = (Edmtickettypetab) this.crowdkindpriceService.get(
					Edmtickettypetab.class, crowdkindprice.getItickettypeid());

			if (product.getBycategorytype().equals("120")) {
				if (crowdkindprice.getIsnet() == 1
						|| crowdkindprice.getIsnet().equals("1")) {
					return new ResponseBean(400, "是否可网上销售");
//					this.addActionError(getText("crowdkindprice.isnet.required"));// 是否可网上销售
				}
			}

			if (crowdkindprice.getNote1() == null
					|| crowdkindprice.getNote1().equals("")) {
				return new ResponseBean(400, "请选择价格级别!");
//				this.addActionError(getText("请选择价格级别!"));
			}else{
				if(crowdkindprice.getIbusinessid()==1L && !"0000".equals(crowdkindprice.getNote1())){
					return new ResponseBean(400, "散客业务只能选择公共组");
//					this.addActionError(getText("散客业务只能选择公共组"));
				}
			}

		}

		/*if (act == 3) {
			if (crowdkindprice.getIcrowdkindpriceid() == null
					|| "".equals(crowdkindprice.getIcrowdkindpriceid())) {
				this.addActionError(getText("crowdkindprice.priceid.required"));// 价格编号不能为空
			}
		}*/

		/*if (hasActionErrors()) {
			return INPUT;
		}*/
		Syslog syslog = new Syslog();
		Esfemployeetab currentUser = sysService.currentUser();
		syslog.setIemployeeid(currentUser.getIemployeeid());

		if (act == 1) {// 增加

			// 得到最大主键
			long maxpriceseq = this.crowdkindpriceService.getMaxPk(
					"icrowdkindpriceid", "Edmcrowdkindpricetab");
			crowdkindprice.setIcrowdkindpriceid(maxpriceseq + 1);

			// 根据产品编号，价格日期判断日期是否有重复
			boolean isusedate = crowdkindpriceService
					.retriePriceIsuse(crowdkindprice);
			if (isusedate) {
				return new ResponseBean(400, "保存失败，价格日期重复");
				/*this.addActionMessage(getText("success.crowdkindprice.add.false")
						+ WebContant.DOMAINAME);// 保存失败，价格日期重复
				return SUCCESS;*/
			} else {
				// 保存
				crowdkindpriceService.insertCrowdKindPirce(crowdkindprice,
						Xpricelist, syslog);
				return new ResponseBean(200, "新增成功");
				/*this.addActionMessage(getText("success.crowdkindprice.add")
						+ WebContant.DOMAINAME);*/// 新增成功
			}
		}
		if (act == 2) {// 修改
			// 判断日期是否有重复
			boolean isusedate = crowdkindpriceService
					.updatePriceIsuse(crowdkindprice);
			if (isusedate) {
				return new ResponseBean(400, "修改失败，价格日期重复");
				/*this.addActionMessage(getText("success.crowdkindprice.edit.false")
						+ WebContant.DOMAINAME);// 修改失败，价格日期重复
				return SUCCESS;*/
			} else {
				// 修改
				crowdkindpriceService.updateCrowdKindPirce(crowdkindprice,
						Xpricelist, syslog);
				return new ResponseBean(200, "修改成功");
				/*this.addActionMessage(getText("success.crowdkindprice.edit")
						+ WebContant.DOMAINAME);*/// 修改成功
			}
		}
		if (act == 3) {// 删除
			// 判断订单是否存在此价格的数据
			boolean isuse = crowdkindpriceService
					.queryPriceIsUse(crowdkindprice.getIcrowdkindpriceid());
			if (isuse) {
				/*this.addActionError(getText("error.crowdkindprice.isuse.by.order"));// 此价格在订单明细中存在数据，无法删除
				return INPUT;*/
				return new ResponseBean(400, "此价格在订单明细中存在数据，无法删除");
			} else {
				// 删除数据

				crowdkindpriceService.deleteCrowdKindPirce(
						crowdkindprice.getIcrowdkindpriceid(), syslog);
				// 删除成功
				return new ResponseBean(200, "删除成功");
			}
		}
		return null;
	}
}
