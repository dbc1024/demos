package com.ectrip.ec.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.JSONUtil;
import com.ectrip.base.util.MD5Util;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.app.dao.idao.ISearchDAO;
import com.ectrip.ec.app.model.AppVersion;
import com.ectrip.ec.app.service.iservice.IAppVersionService;
import com.ectrip.ec.book.hotel.model.HotelDTO;
import com.ectrip.ec.book.hotel.service.iservice.IHotelService;
import com.ectrip.ec.book.shopcart.service.iservice.IShopCartService;
import com.ectrip.ec.book.ticket.dao.idao.ITicketBookDAO;
import com.ectrip.ec.common.CommonUtil;
import com.ectrip.ec.custom.service.iservice.ICustomService;
import com.ectrip.ec.custom.service.iservice.ILingPiaoUserService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.app.AndroidStateCode;
import com.ectrip.ec.model.app.BaseModel;
import com.ectrip.ec.model.app.ObjectParse;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.OrderInfo;
import com.ectrip.ec.model.order.OrderToDTO;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Lingpiaouser;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;
import com.ectrip.ec.order.dao.idao.ITOrderDAO;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.ClientRequest;
import com.ectrip.hqyt.model.request.GuaranteeRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.response.JSONClient;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.sys.model.balance.Zhifu;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.tourcard.model.TourCardOrder;

public class DataAnalysisService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalysisService.class);
	
	
	private IOrderSaveTrans ordersaveTrans;
	
	@Autowired
	private TicketService ticketService;	//票务服务api
//	private IProviderService providerService;
	
	private ILingPiaoUserService lingpiaouserService;
	private IShopCartService shopcartService;
	private IHotelService hotelService;//酒店服务接口
	private IOrderSaveTrans hotelOrderTrans ;
	private IOrderService orderService;
	private ICustomService cusService=(ICustomService) SpringUtil.getBean("cusService");
	public IGenericService genericService=(IGenericService)SpringUtil.getBean("genericService");
	private ITicketBookDAO ticketbookDao;
	private ISearchDAO searchDao =(ISearchDAO) SpringUtil.getBean("searchDao");
	public ITOrderDAO torderdao=(ITOrderDAO)SpringUtil.getBean("torderDao");
	public IAppVersionService appVersionService =(IAppVersionService)SpringUtil.getBean("appVersionService");


	private BaseModel validateUser(String usid, String pwd) {
		BaseModel model = new BaseModel();
		model.setUsid(usid);
		//status：1-用户不存在      2-密码错误    0-登录成功
		String status = searchDao.login(usid, pwd);
		System.out.println("用户登录："+status);
		if (status.equals("0")) {
			Map map = searchDao.findUser(usid, pwd);
			System.out.println("map="+map);
			if (map == null) {
				model.setMsgtp(AndroidStateCode.LOGIN_CANTBOOK);
			}else{
				if (map.get("lname") == null || map.get("lname").equals("")) {
					model.setUsername("");
				} else {
					model.setUsername(map.get("lname").toString());
				}
				if (map.get("usid") == null || map.get("usid").equals("")) {
					model.setUsername("");
				} else {
					model.setUsid(map.get("usid").toString());
				}
			}
		} else if(status.equals("1")){
			model.setMsgtp(AndroidStateCode.LOGIN_NOUSER);
		}else if(status.equals("2")){
			model.setMsgtp(AndroidStateCode.LOGIN_PWDERROR);
		}
	
		if(status.equals("1")||status.equals("2")){
			model.setLogonstatus("0");//登录失败，状态离线
		}else{
			model.setLogonstatus("1");//登录成功，状态在线
		}
		
		return model;
	}

	public String login(String usid, String pwd) throws Exception {
		System.out.println("登录进入111111");
		BaseModel model = validateUser(usid, pwd);
		ObjectParse parse = new ObjectParse();
		System.out.println(parse.ObjectToXml(model));
		return parse.ObjectToXml(model);
	}

	public String getUserInfo(String usid, String pwd) throws Exception {
		BaseModel model = validateUser(usid, pwd);
		if (model.getLogonstatus().equals("0")) {
			Map map = searchDao.findUser(usid, pwd);
			ArrayList<Map> list = new ArrayList<Map>();
			list.add(map);
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}

	@SuppressWarnings("unchecked")
	public String providerList(String usid, String pwd,String url,String keyword) throws Exception {
		BaseModel model = new BaseModel();
			model.setLogonstatus("1");
			model.setUsid(usid);
			model.setPwd(pwd);
			model.setNodes(searchDao.getProviderList(url,keyword));
		ObjectParse parse = new ObjectParse();
		System.out.println("门票列表信息返回："+parse.ObjectToXml(model));
		return parse.ObjectToXml(model);
	}
	/**
	 * 分页返回
	 * @param usid
	 * @param pwd
	 * @param url
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String providerList(String usid, String pwd,String url,Integer pageSize,Integer page) throws Exception {
		String result = null;
		Map<String,Object> resultMap = new HashMap<String,Object>(); 
		Integer status = 1;
		String message = "请求景区服务商失败";
		PaginationSupport providerList = searchDao.getProviderList(url,pageSize,page);
		if(providerList != null) {
			status = 0;
			resultMap.put("data", providerList);
			message = "请求成功";
		}
		resultMap.put("status", status);
		resultMap.put("message", message);
		result = JSON.toJSONString(resultMap);
		LOGGER.debug("分页请求景区服务商的结果---"+result);
		return result;
	}
	
	public String ticketList(String usid,String pwd,long iscenicid,String url) throws Exception{
		if(url==null||url.length()<0){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		BaseModel model = validateUser(usid, pwd);
		if (model.getLogonstatus().equals("0")) {
			Map map=searchDao.findUser(usid, pwd);
			List list=searchDao.getTicketList(map.get("ibusinessid").toString(),iscenicid, map.get("groupno").toString());
			for(int i=0;i<list.size();i++){
				Map ticket=(Map) list.get(i);
				String picadder=searchDao.getTicketPicAdder(Long.parseLong(ticket.get("ticketid").toString()));
				if("".equals("picadder")){
					ticket.put("picadder", "");
				}else{
					System.out.println(url+"xxxxxxxxxxxxxxxxxx");
					ticket.put("picadder", url+picadder);
				}
				
			}
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	
	public String saveOrder(String xml,String orid,String url) throws Exception{
		ObjectParse parse = new ObjectParse();
		BaseModel model = parse.XmlToObject(xml);
		BaseModel newModel=validateUser(model.getUsid(), model.getPwd());
		newModel.setPwd(model.getPwd());
		newModel.setNodes(model.getNodes());
		if (newModel.getLogonstatus().equals("0")) {
			Map result=searchDao.saveOrder(newModel,orid,url);
			if(result.get("status").toString().equals("true")){
				List list=new ArrayList();
				Map map=new HashMap();
				map.put("orid", orid);
				map.put("mont", result.get("mont").toString());
				list.add(map);
				newModel.setMsgtp(AndroidStateCode.BOOK_SUCCESS);
				newModel.setLogonstatus("1");
				newModel.setNodes(list);
			}else{
				newModel.setLogonstatus("0");
				newModel.setMsgtp(result.get("errtp").toString());
			}
		}
		newModel.setPwd("");
		return parse.ObjectToXml(newModel);
	}
	
	public String queryPrice(String usid,String pwd,long ticketid,String date) throws Exception{
		BaseModel model = validateUser(usid, pwd);
		if (model.getLogonstatus().equals("0")) {
			Map map=searchDao.findUser(usid, pwd);
			List list=searchDao.getPriceListByDate(ticketid, map.get("ibusinessid").toString(), date, map.get("groupno").toString());
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	public String orderDetail(String usid,String pwd,String orid,String iscenicid) throws Exception{
		BaseModel model = validateUser(usid, pwd);
		if (model.getLogonstatus().equals("0")) {
			Map map=searchDao.findUser(usid, pwd);
			List list=searchDao.orderDetail(orid, iscenicid);
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	public String orderList(String usid,String pwd,String date) throws Exception{
		BaseModel model = validateUser(usid, pwd);
		if (model.getLogonstatus().equals("0")) {
			List list=searchDao.getOridList(date, usid);
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	
	public String viewArticle(Long amid) throws Exception{
		Map map=searchDao.viewArticle(amid);
		BaseModel model=new BaseModel();
		model.setUsid("guest");
		model.setPwd("****");
		model.setLogonstatus("1");
		if(map!=null){
			model.setNodes(new ArrayList<Map>());
			model.getNodes().add(map);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	public String showAllArticles(Long cmid,int num) throws Exception{
		List list=searchDao.showAllArticles(cmid, num);
		BaseModel model=new BaseModel();
		model.setUsid("guest");
		model.setPwd("****");
		model.setLogonstatus("1");
		if(list!=null&&list.size()>0){
			model.setNodes(list);
		}
		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	public String saveZhuCeUser(String revmbno, String password,
			String password2, String random, boolean flag) {
		return searchDao.saveZhuCeUser(revmbno, password, password2, random,
				flag);
	}
	
	public String sendMessage(String revmbno) {
		return searchDao.sendMessage(revmbno);
	}
	
	public String showAllproduct(String usid, String pwd, String scenicid,
			String date,String url) throws Exception {
		BaseModel model = new BaseModel();
		model.setLogonstatus("1");
		model.setUsid(usid);
		model.setPwd(pwd);
		/*if (model.getLogonstatus().equals("0")) {
			//List list = searchDao.productList2(usid, scenicid, date);

		}*/
		List list = searchDao.searchTicketByProid(Long.valueOf(scenicid), date, 1L,url);
		System.out.println(list.toString());
		model.setNodes(list);

		ObjectParse parse = new ObjectParse();
		return parse.ObjectToXml(model);
	}
	
	public String showRetivere(String usid, String pwd) {
		return searchDao.showRetivere(usid, pwd).toString();
	}
	
	//新门票订单保存
	public String newSaveOrder(String xml, String orid) throws Exception{
	 ObjectParse parse = new ObjectParse();
	 BaseModel model = parse.XmlToObject(xml);
	 BaseModel newModel = null;
//	 List<TOrderlist> torderList = orderService.getTOrderlists(orid);
	 Long iscenicid = 0L;
	 String hqyatuDate = null;
	 MOrder m = (MOrder) searchDao.get(MOrder.class, orid);
	 List<ProductRequest> products = new ArrayList<ProductRequest>();
	 String hqytUrl = "";
	 String OwnerNumber = "";
	 double totalFee = 0;
	 String subject = "";
	 
	 try{
		 newModel = validateUser(model.getUsid(), model.getPwd());
		 newModel.setLogonstatus(model.getLogonstatus());
		 newModel.setPwd(model.getPwd());
		 newModel.setNodes(model.getNodes());
		 if("NULL".equals(model.getObject())||model.getObject()==null){
				 newModel.setLogonstatus("0");
				 newModel.setMsgtp("联系人信息不可为空!");
			    return JSON.toJSONString(newModel);	
		 }
	     if (newModel.getLogonstatus().equals("1")) {
			   Map user = searchDao.findUser(model.getUsid(), model.getPwd());
			   String usid = user.get("usid").toString();		
		       LprPojo lpr = new LprPojo();
		       Map object = (Map)model.getObject();
		         object.put("state",0);
		         object.put("mont",0);
		         object.put("isSync","NULL");
		         object.put("zforid","1");
		       newModel.setObject(object);
		       List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
			     Long providerid = Long.valueOf(object.get("providerid").toString());
			     String holdername = (String)object.get("holdername");lpr.setDaoyou(holdername);
				 String telphone = (String)object.get("telphone");lpr.setMobile(telphone);
				 String printpwd = (String)object.get("printpwd");lpr.setPassword(printpwd);
				 String orzj = (String)object.get("orzj");lpr.setZjlb(orzj);
				 String cardno = (String)object.get("cardno");lpr.setZjhm(cardno);
				 String seq = "".equals(object.get("seq"))?"":(String)object.get("seq");lpr.setSeq(seq);
				 String cyuser = "".equals(object.get("cyuser"))?"":(String)object.get("cyuser");//是否设为常用联系人  
				 if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
					newModel.setLogonstatus("0");
					newModel.setMsgtp("联系人姓名不能为空!");
					return JSON.toJSONString(newModel);
				 }
				 if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
					newModel.setLogonstatus("0");
					newModel.setMsgtp("联系电话不能为空!");
					return JSON.toJSONString(newModel);
				 }else {
					Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
					boolean c = p1.matcher(lpr.getMobile()).matches();
					OwnerNumber = lpr.getMobile();
					if (!c) {
						newModel.setLogonstatus("0");
						newModel.setMsgtp("请输入正确的手机号码");
						return JSON.toJSONString(newModel);
					}
				 }
				 if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
					newModel.setLogonstatus("0");
					newModel.setMsgtp("证件号码不能为空");
					return JSON.toJSONString(newModel);
				 } else {
					  if (lpr.getZjlb() != null && lpr.getZjlb().equals("01")) {
						Pattern p1 = Pattern
								.compile("^\\d{15}|(\\d{17}(\\d|X|x))$");
						boolean c = p1.matcher(lpr.getZjhm()).matches();
						if (!c) {
							newModel.setLogonstatus("0");
							newModel.setMsgtp("请输入正确的身份证号码");
							return JSON.toJSONString(newModel);
						}
					  }
				 }


			 //实名制
			 List<TRealname> listRealname = new ArrayList<TRealname>();
		         if (newModel.getNodes() == null || newModel.getNodes().size() == 0) {
				      newModel.setLogonstatus("0");
					  newModel.setMsgtp("请选择购买产品!");
					  return JSON.toJSONString(newModel);
		         }else{
			           Map n = (Map)newModel.getNodes().get(0);	 
//			           if(n.get("iscenicid")!=null&&"1".equals(n.get("iscenicid"))){
//			        	   String errorinfo = MatcherID.IDCardValidate(lpr.getZjhm());
//						   if (errorinfo.trim().length() > 0) {
//								newModel.setLogonstatus("0");
//								newModel.setMsgtp("预定九寨沟门票必须使用身份证预定。");
//								return JSON.toJSONString(newModel);
//						   }
//					   } 
			 	       for (Map note : newModel.getNodes()) {
			 	    	   OrderInfo orderinfo = new OrderInfo();
				            iscenicid = Long.valueOf(note.get("iscenicid").toString());orderinfo.setIscenicid(iscenicid);
				            Long itickettypeid = Long.valueOf(note.get("itickettypeid").toString());orderinfo.setItickettypeid(itickettypeid);
				            String playtim = (String)note.get("playtime");orderinfo.setPlaytime(playtim);
				            Long num = Long.valueOf(note.get("num").toString());orderinfo.setNum(num);
				            Long icrowdkindid = Long.valueOf(note.get("icrowdkindid").toString());orderinfo.setIcrowdkindid(icrowdkindid);
				            String note1 = (String)note.get("note1");orderinfo.setNote1(note1);
				            
//				            if(note1!=null && "9999".equals(note1)){
//							   Long snumb = this.shopcartService.checkStock(orderinfo.getItickettypeid(),orderinfo.getIcrowdkindpriceid(),orderinfo.getPlaytime());
//							   if(orderinfo.getNum()>snumb){
//								 newModel.setLogonstatus("0");
//								 newModel.setMsgtp("库存不足,预定失败");
//								 return JSON.toJSONString(newModel);
//						 	   }
//						    }



				           orderInfos.add(orderinfo);
						   //实名制票用户一天只能买一张 zyj
				           Map<String, Object> lykOrderList = searchDao.getLykOrderList(note.get("itickettypeid").toString(), Long.parseLong(note.get("icrowdkindid").toString()),
								   Long.valueOf(note.get("iscenicid").toString()), playtim, usid);

						   if(lykOrderList!=null&&lykOrderList.size()>0){
							   newModel.setLogonstatus("0");
							   newModel.setMsgtp("游玩时间"+playtim+"已有门票，请重新选择游玩时间!");
							   return JSON.toJSONString(newModel);
						   }
						   //添加实名信息
						   String real = String.valueOf(note.get("real"));
						   if(real!=null&&!"".equals(real)&&!"null".equals(real)&&!"NULL".equals(real)){
							   JSONArray jsonArray = JSONArray.parseArray(real);
							   //只能购买一张
							   if(jsonArray.size()>1){
								   newModel.setLogonstatus("0");
								   newModel.setMsgtp("只能购买一张门票!");
								   return JSON.toJSONString(newModel);
							   }

							   for(int mm=0,length=jsonArray.size();mm<length;mm++){
								   TRealname tRealname = new TRealname();
								   JSONObject jsonObject = (JSONObject) jsonArray.get(mm);
								   String realname = jsonObject.getString("realname");
								   String realnumber = jsonObject.getString("realnumber");

								   tRealname.setItickettypeid(Long.valueOf(note.get("itickettypeid").toString()));
								   tRealname.setIcrowdkindid(Long.parseLong(note.get("icrowdkindid").toString()));
								   tRealname.setIscenicid(Long.valueOf(note.get("iscenicid").toString()));
								   tRealname.setCname(realname);
								   tRealname.setIdcard(realnumber);
								   tRealname.setIschild(1L);
								   tRealname.setZjtp("01");
								   listRealname.add(tRealname);
							   }
						   }
				       }
	             }
	            String playtime = orderInfos.get(0).getPlaytime();
			    Esbscenicareatab provider = (Esbscenicareatab)searchDao.get(Esbscenicareatab.class, providerid);
			    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    if (sdf.parse(playtime).before(sdf.parse(Tools.getDays()))) {
				  newModel.setLogonstatus("0");
				  newModel.setMsgtp("预定日期不能小于当前日期");
				  return JSON.toJSONString(newModel);
			    }
			    Calendar c1 = Calendar.getInstance();
			    Calendar c2 = Calendar.getInstance();
			      c1.setTime(sf.parse(Tools.getNowString()));
			      c2.setTime(sf.parse(Tools.getDays() + " "+ provider.getSzlasttime() + ":00"));
			     System.out.println(c1.compareTo(c2));
			    if (playtime.equals(Tools.getDays()) && c1.compareTo(c2) >= 0) {
				   newModel.setLogonstatus("0");
				   newModel.setMsgtp("当日预订截止时间为" + provider.getSzlasttime() + "");
				   return JSON.toJSONString(newModel);
			    }



			    // 领票人集合
			    List<LprPojo> lprs = new ArrayList<LprPojo>();
			      lpr.setRzti(playtime);
			      lpr.setIscenicid(new String().valueOf(orderInfos.get(0).getIscenicid()));
			      lprs.add(lpr);
                //lizhaodong  2017-08-21 新增开始  添加了验证条件为系统不存在领票人手机号时才进行注册
				 Custom custom=cusService.findByMobile(lpr.getMobile());
				 if(custom==null)
				 {
					 custom = cusService.anonymityUserCreate("02", lpr.getMobile());
					 System.out.println("anonymityUserCreate");
					 //同步注册结算
					 ClientRequest clientRequest = new ClientRequest();

					 clientRequest.setRegistrationName(custom.getNote6());
					 clientRequest.setBusinessTel(lpr.getMobile());
					 clientRequest.setPassword(custom.getPassword()); //明文给结算-keycloak

					 HqytClient client = new HqytClient();
					 JSONClient jsonClient = null;
					 try {
						 jsonClient = client.createClient(clientRequest);
					 } catch(Exception e) {
						 //结算失败，删除电商记录
						 cusService.delete(custom);
						 //抛出结算接口异常
						 newModel.setLogonstatus("0");
						 newModel.setMsgtp("HQYT:" + e.getMessage());
					 }

					 if(jsonClient!=null) {
						 //TODO写入结算系统的regeistrationName和ID
						 custom.setNote6(jsonClient.getRegistrationName()); //结算client的RegistrationName
						 custom.setNote9(jsonClient.getId().toString());  //结算client的ID
						 cusService.update(custom);
					 }else {
						 newModel.setLogonstatus("0");
						 newModel.setMsgtp("HQYT:" + "结算系统接口返回异常");
					 }
				 }



				 //lizhaodong  2017-08-21 新增结束
	//		    String note = (String) getRequest().getSession().getAttribute("note");
	//				if (cyuser != null && !"".equals(cyuser)&&lpr != null) {
						// 抓异常 不要影响订单
	//					try {
	//						if (lpr.getSeq() == null || lpr.getSeq().equals("")) {
	//							lingpiaouserService.addLingPiaoUser(new Lingpiaouser(null,
	//									user.get("usid").toString(), lpr.getDaoyou(), lpr.getZjlb(),
	//									lpr.getZjhm(), lpr.getMobile(), "", 0),new Customlog());
	//						}
	//					} catch (Exception e) {
	//						 newModel.setLogonstatus("0");
	//						 newModel.setMsgtp("保存领票人信息失败，请与客服联系!");
	//						 return JSON.toJSONString(newModel);
	//					}
	//				}
		//		// 得到DTO对象
					OrderCombinDTO orderCombinDTO = OrderToDTO.toOrderCombinDTO(orderInfos,
							null,user.get("usid").toString(), listRealname, null, lprs, orid);
	//				if (note != null && !note.equals("")) {
	//					orderCombinDTO.setNote(note);
	//				}
		//		// 保存
					//暂时注释订单来源字段
//			        orderCombinDTO.setOrderSource("a_ds");
					boolean result = ordersaveTrans.SaveOrder(orderCombinDTO);
					System.out.println("orid:" + orid);
					String casher = "0";	boolean isSync = true;// 是否进行订单同步
					if (result) {
						m = (MOrder) searchDao.get(MOrder.class, orid);
					 // 产品库存判断 如果库存不足 支付不了
	//			        List<TOrderlist> torderList = orderService.getTOrderlists(orid);
	//					//实名制
	//					List<TOrderlist> tlist = new ArrayList<TOrderlist>();
	//					for (int i = 0; i < torderList.size(); i++) {
	//						TOrderlist orderlist = (TOrderlist) torderList.get(i);
	//						Esbscenicareatab scenice = (Esbscenicareatab) searchDao.get(Esbscenicareatab.class, orderlist.getId().getIscenicid());
	//						Edmtickettypetab product = (Edmtickettypetab) searchDao.get(Edmtickettypetab.class, orderlist.getItickettypeid());
	//						Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) searchDao.get(Edmcrowdkindpricetab.class, orderlist.getIcrowdkindpriceid());
	//						Edpcrowdkindtab crowd = (Edpcrowdkindtab) searchDao.get(Edpcrowdkindtab.class, price.getIcrowdkindid());
	//						 orderlist.setSzscenicname(scenice.getSzscenicname());
	//						 orderlist.setSztickettypename(product.getSztickettypename());
	//						 orderlist.setSzcrowdkindname(crowd.getSzcrowdkindname());
	//						//以价格判断实名制
	//						if(price.getIpeoplenumrange()==1){
	//							if (product.getByusage() != 1) {
	//								tlist.add(orderlist);
	//							}
	//						}
	//
	//					}
			
						int size = 0;// 需同步产品种类
						// B2B同步判断
	//					B2bsyncinfotab tab = this.b2bSyncService.getSyncInfo(providerid, "01");
	//					if (tab != null) {
	//						Long ibusinessid = 1L;
	//						if (user != null) {
	//							ibusinessid = Long.valueOf(user.get("ibusinessid").toString());
	//						}
	//						for (int i = 0; i < orderInfos.size(); i++) {
	//							OrderInfo info = orderInfos.get(i);
	//							if (info.getNum() == null || info.getNum() < 1) {
	//								orderInfos.remove(info);
	//								i--;
	//								continue;
	//							} else {
	//								boolean b = this.shopcartService.syncPrice(info.getItickettypeid(),info.getIcrowdkindid(), ibusinessid,playtime);
	//								if (b) {
	//									size++;
	//								}
	//							}
	//						}
	//						if (size != 0) {
	//							isSync = true;
	//						}
	//						if (size != 0 && size != orderInfos.size()) {
	//							 newModel.setLogonstatus("0");
	//							 newModel.setMsgtp("同步产品与非同步产品不可同时预定");
	//							return JSON.toJSONString(newModel);
	//						}
	//					}
//					if (isSync) {
//						Map map = b2bSyncService.orderxml(orid, lpr.getMobile());
//						String morderxml = map.get("morderxml").toString();
//						String torderxml = map.get("torderxml").toString();
//						String torderlistxml = map.get("torderlistxml").toString();
//						B2BOrderService service = new B2BOrderService();
//						String b2borid = service.saveOrder(morderxml, torderxml,
//								torderlistxml);
//						System.out.println("b2borid:" + b2borid);
//						if (b2borid == null || b2borid.equals("")) {
//							b2bSyncService.deleteOrder(orid);
//							newModel.setLogonstatus("0");
//							newModel.setMsgtp("订单保存失败(同步失败),请重新尝试");
//						   return JSON.toJSONString(newModel);
//						} else {
//							Syslog syslog = new Syslog();
//							syslog.setIemployeeid(1L);
//							b2bSyncService.ordersync(orid, b2borid, syslog);
//						}
//					}
					boolean b = true;
					//canCasher(orid);
					if (b) {
						casher = "1";
					} else {
						casher = "0";
					} 
				  }else {
						newModel.setLogonstatus("0");
					    newModel.setMsgtp("订单保存失败,请重新尝试");
						return JSON.toJSONString(newModel);
			      }
		  object.put("mont",m.getZfmont());      
		  String zforid = findByOrderid(orid, "10");
		  if ("-1".equals(zforid) || "0".equals(zforid)) {
//			    searchDao.delete(m);
//			    b2bSyncService.deleteOrder(orid);
			    object.put("mont",0);
				newModel.setLogonstatus("0");
			    newModel.setMsgtp("获取支付号失败!");
				return JSON.toJSONString(newModel);
		  } else {
				object.put("zforid", zforid);
		  }
		  object.put("isSync",isSync);
		  object.put("state", "1");
		  object.put("casher",casher);
		}else{
			newModel.setLogonstatus("0");
			newModel.setMsgtp("登录错误，请重新登录!");
			return JSON.toJSONString(newModel);
		}
	   }catch (Exception e) {
		 e.printStackTrace();
		 newModel.setLogonstatus("0");
		 newModel.setMsgtp("订单保存失败!");
		 return JSON.toJSONString(newModel);
	   }
	 //------------------------------------------------------------------//
	 try{
         List<TOrderlist> torderList = orderService.getTOrderlists(orid);
         List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
         if(torderList != null && !torderList.isEmpty()){
             for (TOrderlist tl : torderList){
                 iscenicid = tl.getId().getIscenicid();
                 hqyatuDate = tl.getDtenddate();
                 StockOrderInfo stock = new StockOrderInfo();
                 stock.setOrid(tl.getId().getOrid());
                 stock.setProviderId(tl.getId().getIscenicid());
                 stock.setProductId(tl.getItickettypeid());
                 stock.setPriceId(tl.getIcrowdkindpriceid());
//                 stock.setUsid(custom.getUsid());
                 stock.setNumb(tl.getNumb());
                 stock.setStockDate(tl.getDtstartdate());
                 stocks.add(stock);

                 ProductRequest productRequest = new ProductRequest();
                 productRequest.setExternalId(tl.getItickettypeid());
                 Edmtickettypetab ticket = (Edmtickettypetab) genericService.get(Edmtickettypetab.class,tl.getItickettypeid());
                 if(ticket!=null)
				 {
					 productRequest.setName(ticket.getSztickettypename());
				 }
                 productRequest.setPrice(tl.getPric());
                 productRequest.setNumb(tl.getNumb());
                 products.add(productRequest);
                 totalFee = tl.getPric();
//                 subject = ticket.getSztickettypename();
             }
         }
//         stockService.saveStockDetails(stocks,true);
     }catch (Exception e){
         //作废订单，并删除实名制信息
		 e.printStackTrace();
         this.shopcartService.updateOrderDdzt(orid,"98");
		 newModel.setLogonstatus("0");
		 newModel.setMsgtp(e.getMessage());
		 return JSON.toJSONString(newModel);
     }
	 //-----------------------------------------------------------------------------------------------//
	 GuaranteeRequest request = new GuaranteeRequest();
	  request.setOrid(orid);
	  request.setUsid(model.getUsid());
	  request.setTotalMoney(m.getMont());
	  request.setUsername(model.getUsername());
	  request.setPhone(OwnerNumber);
	  ProviderCompany pc = ticketService.queryProviderCompany(iscenicid);
	  if(pc != null){
         request.setIssuerId(pc.getHqytId().toString());
     }else{
         this.shopcartService.updateOrderDdzt(orid,"98");
         newModel.setLogonstatus("0");
         newModel.setMsgtp("服务商未开户不可预定");
         return JSON.toJSONString(newModel);
     }
	  request.setProducts(products);
	  request.setConsumeTimeLimit(hqyatuDate);
	  HqytClient client = new HqytClient();
	  //Custom custom    = (Custom)searchDao.F(Custom.class,model.getUsid());
		Map  map= searchDao.findUser(model.getUsid(),model.getPwd());
	  LegalPersonRequest receiver = new LegalPersonRequest();
     if(map!=null && map.get("note9")==null) {
     	 this.shopcartService.updateOrderDdzt(orid,"98");  //电商-订单作废
		 newModel.setLogonstatus("0");
		 newModel.setMsgtp("用户没有同步结算系统的用户ID");
		 return JSON.toJSONString(newModel);
     }
     receiver.setId(Long.valueOf(map.get("note9").toString()));
     request.setReceiver(receiver);
	  
	  JSONInvoice invoice = null;
	  try {
		  request.setTitle("散客订单");
		  invoice = client.appGuaranteeSk(request);
		  //lizhaodong新增开始  2017-08-17
		  if(invoice != null){
			  m.setNoteh(invoice.getId().toString());
			  torderdao.update(m);
		  }else{
			  throw new RuntimeException("创建支付订单失败");
		  }
		  //lizhaodong新增结束 2017-08-17
     } catch(Exception e) {
          this.shopcartService.updateOrderDdzt(orid,"98");
          e.printStackTrace();
     	  //抛出结算接口异常
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("HQYT:" + e.getMessage());
		  return JSON.toJSONString(newModel);
     }
	  if(invoice != null){
         m.setNoteh(invoice.getId().toString());
//         genericService.update(m);
     }else{
         this.shopcartService.updateOrderDdzt(orid,"98");
         newModel.setLogonstatus("0");
         newModel.setMsgtp("创建支付订单失败");
         return JSON.toJSONString(newModel);
     }

	  
	  Map<String, String> merchant = CommonUtil.findMerchant();
     SortedMap<String, Object> params = new TreeMap<String, Object>();
     params.put("merchantId", merchant.get("merchantId"));
     params.put("outTradeNo", m.getOrid());
     
     
     
     //结算回调地址
     String returnUrl  = "";
     params.put("returnUrl", "http://"+WebContant.GetKeyValue("DOMAIN")+"/alpay/orderAppView.action");//actin
     
//     params.put("returnUrl", "w1.rtzhisheng.com/scanCode/orderDetails.jsp");
     
     
     
     params.put("totalFee", totalFee);
//     params.put("subject", subject);
     
     String sign = MD5Util.createSign(params, merchant.get("key"), "UTF-8");
     params.put("sign", sign);
     //访问链接
     hqytUrl =  CommonUtil.findPayUrld() + "/wapcounter?";//CommonUtil.findWxPayUrl() + ""; //hqcounter
     
//     String url = "https://pub.upaypal.cn/business-platform.integration.alipay/tp/alipay";
//     hqytUrl = "w1.rtzhisheng.com/scanCode/orderDetail.jsp" + "?";
     List<String> keys = new ArrayList<String>(params.keySet());
     for (int i = 0; i < keys.size(); i++) {
         String key = keys.get(i);
         String value = String.valueOf(params.get(key));

         //拼接时，不包括最后一个&字符
         if (i == keys.size() - 1) {
             hqytUrl = hqytUrl + key + "=" + value;
         } else {
             hqytUrl = hqytUrl + key + "=" + value + "&";
         }
         newModel.setPayUrl(hqytUrl);
     }
	 
	 //-----------------------------------------------------------------------------------------------//
		newModel.setLogonstatus("1");
	    newModel.setMsgtp("订单保存成功!");
	   return JSON.toJSONString(newModel);
	}

	/**
	 * 315 酒店详情
	 * @param userid 用户手机号
	 * @param pwd 密码
	 * @param iscenicid 酒店id
	 * @return
	 * @throws Exception
	 */
	public Map appGetHotelProduct(String userid, String pwd, String iscenicid) throws Exception {
		Map map = searchDao.findUser(userid, pwd);
		String lgtp = "";
		String groupno = "";
		String ttlb = "";

		if (map != null) {
			lgtp = (String)map.get("lgtp");
			groupno = (String)map.get("groupno");
			ttlb = (String)map.get("ttlb");
			// 导游 预订时显示散客的价格 lijingrui 2012-08-02
			if ("02".equals(lgtp) &&"02".equals(ttlb)) {
				lgtp = "01";
				groupno = "0000";
			}
		}
		if (map == null) {
			lgtp = "01";
			groupno = "0000";
		}
		String rzti = DateUtils.formatDate(new Date(), DateUtils.DATE_PATTERN);
		String ldti = DateUtils.formatDate(DateUtils.addDay(new Date(), 1), DateUtils.DATE_PATTERN);

		return (Map)appGetHotelProductList(null,null, rzti,ldti,null,null, lgtp, groupno,null, iscenicid).get(0);
	}

	/**
	 * 手机app查询酒店
	 * @param gowhere
	 * @param keyword
	 * @param rzti
	 * @param ldti
	 * @param gratestr
	 * @param pricestr
	 * @param lgtp
	 * @param groupno
	 * @return
	 * @throws Exception
	 */
	public List appGetHotelProductList(String gowhere,String keyword, String rzti,String ldti,String gratestr,String pricestr, String lgtp, String groupno,String url) throws Exception {
		return appGetHotelProductList(gowhere,keyword, rzti,ldti,gratestr,pricestr, lgtp, groupno,url, null);
	}

	/**
	 * 手机app查询酒店
	 * @param gowhere
	 * @param keyword
	 * @param rzti
	 * @param ldti
	 * @param gratestr
	 * @param pricestr
	 * @param lgtp
	 * @param groupno
	 * @param iscid iscenicid
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List appGetHotelProductList(String gowhere,String keyword, String rzti,String ldti,String gratestr,String pricestr, String lgtp, String groupno,String url, String iscid) throws Exception {
		if(url==null||url.length()<0){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		List list = null;
		String priceLow = "",priceHeig = "";
		if(!"".equals(pricestr)&&pricestr!=null){
			String[] str =  pricestr.split("~");
			priceLow = str[0]==null?"":str[0];
			priceHeig = str.length>=2?str[1]:"";
		}
		String ssql = "select distinct new map(p.iscenicid as iscenicid,min(price.mactualsaleprice) as mactualsaleprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p  where price.itickettypeid = product.itickettypeid and price.ibusinessid ="
			+ Long.parseLong(lgtp)+ " and price.note1 = '0000' "
			+ "and p.iscenicid = product.iscenicid and p.scenictype = '06' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
			+ Long.parseLong(lgtp)+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
			+ Long.parseLong(lgtp)+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='"
			+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
			+ Long.parseLong(lgtp)+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='"
			+ ldti+ "' ) and '"+ rzti+ "' between price.startdata and price.enddata ";
		if (priceLow != null && !"".equals(priceLow)) {
			System.out.println("priceLow:"+priceLow.length());
			ssql += " and price.mactualsaleprice>="+ Double.parseDouble(priceLow);
		}
		if (priceHeig != null && !"".equals(priceHeig)) {
			ssql += " and price.mactualsaleprice<="+ Double.parseDouble(priceHeig);
		}
		if(StringUtil.isNotEmpty(iscid)){
			ssql += " and p.iscenicid = '"+iscid+"'";
		}
		ssql += " group by p.iscenicid";
		ssql += " order by min(price.mactualsaleprice)";
		List slist = searchDao.find(ssql);
		StringBuffer order1 = new StringBuffer();
		if (slist != null && !slist.isEmpty()) {
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < slist.size(); i++) {
				Map smap = (Map) slist.get(i);
				order1.append(smap.get("iscenicid").toString() + "," + (i + 1) + ",");
				s.append(smap.get("iscenicid").toString() + ",");
			}
			String iscenicids = s.toString().substring(0,s.toString().length() - 1);
			StringBuffer hsql = new StringBuffer();
			hsql.append(" select distinct new map(pro.iscenicid as iscenicid,pro.imaxdata as imaxdata,pro.szscenicname as providername,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as areaname,pro.iordernumber as iordernumber, s1.pmva as strgrade,pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude,hp.zxjb as zxjb,substr(s2.pmva, 1, length(s2.pmva)-2) as strzxjb, hp.hotelservice as hotelservice) " +
					     "from  Esbscenicareatab pro,Hotelprovider hp,Sysparv5 s1,Sysparv5 s2,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.iscenicid = hp.iscenicid and pro.byisuse=1 and pro.isjd =0 and s1.id.pmcd=pro.szgrade and s1.id.pmky='DENJ' and s2.id.pmcd=hp.zxjb and s2.id.pmky='HOTP' and pro.iscenicid in (" + iscenicids + ") ");
			if (gowhere != null && !"".equals(gowhere)) {
				hsql.append(" and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iregionalid ="+ Long.parseLong(gowhere) + " or g.iparentid =" + Long.parseLong(gowhere) + ") ");
			}
			if (keyword!= null && !"".equals(keyword)) {
				hsql.append(" and ( pro.szscenicname like '%" + keyword + "%' or pro.szsimpleintroduction like '%" + keyword + "%' or hp.noted1 like '%" + keyword + "%' or pro.szlocation like '%" + keyword + "%'  or pro.szaddress like '%" +keyword + "%' or gal.szinnername like '%"+ keyword + "%'  )");
			}
			if (gratestr != null && !"".equals(gratestr)) {
				String[] grades = gratestr.replace(" ", "").split(",");
				StringBuffer grade = new StringBuffer();
				for (int i = 0; i < grades.length; i++) {
					grade.append("'" + grades[i] + "',");
				}
				hsql.append(" and pro.szgrade in ("+ grade.toString().substring(0,grade.toString().length() - 1) + ") ");
			}
			//ps = findPage(hsql.toString(), pageSize, page, url);
            //List list = ps.getItems();
		    list = searchDao.find(hsql.toString());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					if (map.get("iscenicid") != null) {
						Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
						//查询酒店历史已经多少房间
						String numbSql = "select sum(numb) as numb from t_orderlist tlist,m_order m where tlist.iscenicid = ? and m.orid = tlist.orid and m.ddzt in ('02','11')";
						List<Map> findBySqlToMap = searchDao.findBySqlToMap(numbSql, new Object[] {iscenicid});
						if(findBySqlToMap != null && findBySqlToMap.size()>0) {
							Map map2 = findBySqlToMap.get(0);
							Object numb = map2.get("NUMB");
							if(numb == null) {
								numb = 0;
							}
							map.put("numb", numb);
						}
						// 查找产品
						String hsql2 = "select new map(prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename) from Edmtickettypetab prd,Edmcrowdkindpricetab p where prd.bycategorytype!='120' and prd.byisuse=1 and prd.note2 = '01' and prd.iscenicid="
								+ iscenicid + " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid=" + Long.parseLong(lgtp)
								+ " and pri.note1 = '" + groupno + "' and pri.note4 = '01' " + " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
								+ Long.parseLong(lgtp) + " and edm.note1 = '" + groupno + "' and edm.note4='01' " + " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti + "' "
								+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid=" + Long.parseLong(lgtp) + " and edm.note1 = '" + groupno + "' and edm.note4 = '01' "
								+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti + "' ) and p.itickettypeid = prd.itickettypeid and p.note1='" + groupno + "' and p.ibusinessid=" + Long.parseLong(lgtp) + " and '" + rzti + "' between p.startdata and p.enddata ";
						if (priceLow != null && !"".equals(priceLow)) {
							hsql2 += " and p.mactualsaleprice >=" + Double.parseDouble(priceLow);
						}
						if (priceHeig != null&& !"".equals(priceHeig)) {
							hsql2 += " and p.mactualsaleprice <="+ Double.parseDouble(priceHeig);
						}
						hsql2 += " order by prd.sztickettypename,p.mactualsaleprice";
						System.out.println(">>>" + hsql2);
//						List productList = this.find(hsql2);
//						List productList2 = new ArrayList();
//						if (productList != null && productList.size() > 0) {
//							for (int j = 0; j < productList.size(); j++) {
//								Edmtickettypetab product = new Edmtickettypetab();
//								Map promap = (Map) productList.get(j);
//								BeanUtils.populate(product, promap);
//								String[] names = product.getSztickettypename().split("-");
//								product.setSztickettypename(names[0]);
//								if (names.length > 1) {
//									product.setBname(names[1]);
//								}
//								// 酒店产品其它属性
//								Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,product.getItickettypeid());
//                                if (hotelproduct != null) {
//									Sysparv5Id sys = new Sysparv5Id();
//									sys.setPmcd(hotelproduct.getBedtype());
//									sys.setPmky("BETP");
//									Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
//									hotelproduct.setStrbedtype(syspar.getPmva());
//									product.setHotelproduct(hotelproduct);
//								}
//								// /判断选择的入住日期是否都有价格
//								long num = getDayNumb(rzti, ldti);
//								double totalprice = 0;
//								List pricelist = new ArrayList();
//								Date enddate = null;
//								Edmcrowdkindpricetab price = null;
//								double singleprice = 0;
//								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//								for (int x = 0; x < num; x++) {
//									if (enddate != null&& enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
//										singleprice = price.getMactualsaleprice();
//										if (hotelproduct != null) {
//											switch (hotelproduct.getWeektype()) {
//											case 0:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 1:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 2:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 3:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											default:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}	
//												break;
//											}
//										}
//										totalprice += singleprice;
//										pricelist.add(singleprice);
//									} else {
//										String hsqlend = "select new map(pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
//												+ Long.parseLong(lgtp)+ " and pri.note1 = '"+ groupno+ "' and pri.note4 = '01' "+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
//												+ Long.parseLong(lgtp)+ " and edm.note1 = '"+ groupno+ "' and edm.note4='01' "+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()
//												+ ") <='"+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="+ Long.parseLong(lgtp)+ " and edm.note1 = '"+ groupno
//												+ "' and edm.note4='01' "+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()
//												+ "))>='"+ ldti+ "' and pri.itickettypeid="+ product.getItickettypeid()+ "  and pri.startdata<='"+ Tools.getDate(rzti, x)+ "' and pri.enddata>='"+ Tools.getDate(rzti, x) + "' ";
//										List lst = this.find(hsqlend);
//										if (lst != null && lst.size() > 0) {
//											Map cmap = (Map) lst.get(0);
//											price = new Edmcrowdkindpricetab();
//											BeanUtils.populate(price, cmap);
//											enddate = sf.parse(price.getEnddata());
//											singleprice = price.getMactualsaleprice();
//											if (hotelproduct != null) {
//												switch (hotelproduct.getWeektype()) {
//												case 0:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												case 1:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												case 2:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//												    }
//													break;
//												case 3:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												default:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												}
//											}
//											totalprice += singleprice;
//											pricelist.add(singleprice);
//										}
//									}
//								}
//								product.setPriceList(pricelist);
//								double jprice = Math.ceil(totalprice / num);
//								product.setJprice(jprice);
//								productList2.add(product);
//							}
//						}
//						map.put("productList", productList2);

						// 查询服务商图片
						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
								+ iscenicid
								+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
						//List piclist = searchDao.findTopNumb(sql, 1);只取一张
						List piclist = searchDao.find(sql);
						ArrayList arrayList = new ArrayList();
						if (piclist != null && piclist.size() > 0){
							for (int j = 0; j < piclist.size(); j++) {
								Map picmap = (Map) piclist.get(j);
								arrayList.add("http://"
										+ url
										+ picmap.get("upadder").toString()
										+ picmap.get("upfilename").toString());
							}
						}
						map.put("picaddrList",arrayList );
						
						
						
						/*String upadder = "",upfilename = "",address = "http://"+WebContant.GetKeyValue("CenterUrl");
						if (piclist != null && !piclist.isEmpty()) {
							Map picMap = (Map) piclist.get(0);
							upadder = picMap.get("upadder").toString();
							upfilename = picMap.get("upfilename").toString();
						}
						map.put("upadder", address+upadder+upfilename);*/
						//map.put("upfilename", upfilename);
						//this.find(sql);
						//map.put("piclist", piclist);

						Map lowprice = apphotelgetMinPrice(iscenicid, rzti,Long.parseLong(lgtp), groupno);
						double listingprice = 0L;
						double sprice = 0L;
						if (lowprice != null) {
							listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// 挂牌价
							sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// 实际售价
						}
						map.put("lowprice", sprice);
						map.put("height", listingprice);
						map.put("discount",String.valueOf(Math.round(sprice / listingprice* 100) / 10));

						// 酒店设施
						if (StringUtil.isNotEmpty((String)map.get("hotelservice"))) {
							String[] services = ((String)map.get("hotelservice")).split(" ");
							String[] serviceids = new String[services.length];
							for (int m = 0; m < services.length; m++) {
								serviceids[m] = ticketService.findpmcd("HTSS", services[m]);
							}
							map.put("serviceids", serviceids);
						}

						//最近订单
						List<Map> orderTimeList= this.searchDao.findBySqlToMap("select dtmakedate from t_orderlist where iscenicid=? order by dtmakedate desc", new Object[]{iscenicid});
						if(orderTimeList != null && !orderTimeList.isEmpty()){
							//最近订单距离当前的时间差
							map.put("latestordertime", (DateUtils.getTodayDate().getTime() - DateUtils.convertDate((String)orderTimeList.get(0).get("DTMAKEDATE"), DateUtils.DATETIME_PATTERN).getTime())/1000);
						}
//						int total = 0;
//						int htotal = 0;
//						String comsql = " from Hscomment where oeid=" + iscenicid + " and status='01' ";
//						List clist = this.find(comsql);
//						if (clist != null && !clist.isEmpty()) {
//							total = clist.size();
//						}
//						comsql += " and  remarknum>=3 ";
//						clist = this.find(comsql);
//						if (clist != null && !clist.isEmpty()) {
//							htotal = clist.size();
//						}
//						map.put("ctotal", total);
//						if (total == 0 || htotal == 0) {
//							map.put("tjs", "100");
//						} else {
//							map.put("tjs", Math.ceil(total / htotal * 100));
//						}
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 手机APP
	 * 查询产品
	 * @param gowhere
	 * @param keyword
	 * @param rzti
	 * @param ldti
	 * @param gratestr
	 * @param pricestr
	 * @param lgtp
	 * @param groupno
	 * @return
	 * @throws Exception
	 */
	 
	public List appGetBaseProductList(String gowhere,String keyword, String rzti,String ldti,String gratestr,String pricestr, String lgtp, String groupno,String scenictype,String url) throws Exception {
		if(url==null||url.length()<0){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		List list = null;
		String priceLow = "",priceHeig = "";
		if(!"".equals(pricestr)){
			String[] str =  pricestr.split("~");
			priceLow = str[0];
			priceHeig = str[1];
		}
		String ssql = "select distinct new map(p.iscenicid as iscenicid,min(price.mactualsaleprice) as mactualsaleprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p  where price.itickettypeid = product.itickettypeid and price.ibusinessid ="
			+ Long.parseLong(lgtp)
			+ " and p.iscenicid = product.iscenicid and p.scenictype = '"+scenictype+"' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
			+ Long.parseLong(lgtp)+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
			+ Long.parseLong(lgtp)+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='"
			+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
			+ Long.parseLong(lgtp)+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='"
			+ ldti+ "' ) and '"+ rzti+ "' between price.startdata and price.enddata ";
		if (priceLow != null && !priceLow.equals("")) {
			ssql += " and price.mactualsaleprice>="+ Double.parseDouble(priceLow);
		}
		if (priceHeig != null && !priceHeig.equals("")) {
			ssql += " and price.mactualsaleprice<="+ Double.parseDouble(priceHeig);
		}
		ssql += " group by p.iscenicid";
		ssql += " order by min(price.mactualsaleprice)";
		List slist = searchDao.find(ssql);
		StringBuffer order1 = new StringBuffer();
		if (slist != null && !slist.isEmpty()) {
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < slist.size(); i++) {
				Map smap = (Map) slist.get(i);
				order1.append(smap.get("iscenicid").toString() + "," + (i + 1) + ",");
				s.append(smap.get("iscenicid").toString() + ",");
			}
			String iscenicids = s.toString().substring(0,s.toString().length() - 1);
			StringBuffer hsql = new StringBuffer();
			hsql.append(" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as providername,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as areaname,pro.iordernumber as iordernumber, pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude ) "
					+ "from  Esbscenicareatab pro,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.byisuse=1 and pro.isjd =0 and pro.iscenicid in ("
					+ iscenicids + ") ");
			if (gowhere != null && !"".equals(gowhere)) {
				hsql.append(" and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iregionalid ="+ Long.parseLong(gowhere) + " or g.iparentid =" + Long.parseLong(gowhere) + ") ");
			}
			if (keyword!= null && !"".equals(keyword)) {
				hsql.append(" and ( pro.szscenicname like '%" + keyword + "%' or hp.noted1 like '%" + keyword + "%' or pro.szlocation like '%" + keyword + "%'  or pro.szaddress like '%" +keyword + "%' or gal.szinnername like '%"+ keyword + "%'  )");
			}
			if (gratestr != null && !"".equals(gratestr)) {
				String[] grades = gratestr.replace(" ", "").split(",");
				StringBuffer grade = new StringBuffer();
				for (int i = 0; i < grades.length; i++) {
					grade.append("'" + grades[i] + "',");
				}
				hsql.append(" and pro.szgrade in ("+ grade.toString().substring(0,grade.toString().length() - 1) + ") ");
			}
			//ps = findPage(hsql.toString(), pageSize, page, url);
            //List list = ps.getItems();
		    list = searchDao.find(hsql.toString());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					if (map.get("iscenicid") != null) {
						Long iscenicid = Long.parseLong(map.get("iscenicid").toString());

						// 查找产品
//						String hsql2 = "select new map(prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename) from Edmtickettypetab prd,Edmcrowdkindpricetab p where prd.bycategorytype!='120' and prd.byisuse=1 and prd.note2 = '01' and prd.iscenicid="
//								+ iscenicid + " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid=" + Long.parseLong(lgtp)
//								+ " and pri.note1 = '" + groupno + "' and pri.note4 = '01' " + " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
//								+ Long.parseLong(lgtp) + " and edm.note1 = '" + groupno + "' and edm.note4='01' " + " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti + "' "
//								+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid=" + Long.parseLong(lgtp) + " and edm.note1 = '" + groupno + "' and edm.note4 = '01' "
//								+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti + "' ) and p.itickettypeid = prd.itickettypeid and p.note1='" + groupno + "' and p.ibusinessid=" + Long.parseLong(lgtp) + " and '" + rzti + "' between p.startdata and p.enddata ";
//						if (priceLow != null && !"".equals(priceLow)) {
//							hsql2 += " and p.mactualsaleprice >=" + Double.parseDouble(priceLow);
//						}
//						if (priceHeig != null&& !"".equals(priceHeig)) {
//							hsql2 += " and p.mactualsaleprice <="+ Double.parseDouble(priceHeig);
//						}
//						hsql2 += " order by prd.sztickettypename,p.mactualsaleprice";
						String productnamesql = "select new map(t.sztickettypename as sztickettypename) from Edmtickettypetab t, Esbscenicareatab s ";
						       productnamesql += "where s.scenictype='08' and s.byisuse=1 and t.byisuse=1 and s.isjd =0 and s.iscenicid = t.iscenicid and s.iscenicid="+ iscenicid;
						System.out.println(">>>" + productnamesql);
						List productList = searchDao.find(productnamesql);
//						List productList2 = new ArrayList();
//						if (productList != null && productList.size() > 0) {
//							for (int j = 0; j < productList.size(); j++) {
//								Edmtickettypetab product = new Edmtickettypetab();
//								Map promap = (Map) productList.get(j);
//								BeanUtils.populate(product, promap);
//								String[] names = product.getSztickettypename().split("-");
//								product.setSztickettypename(names[0]);
//								if (names.length > 1) {
//									product.setBname(names[1]);
//								}
//								// 酒店产品其它属性
//								Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,product.getItickettypeid());
//                                if (hotelproduct != null) {
//									Sysparv5Id sys = new Sysparv5Id();
//									sys.setPmcd(hotelproduct.getBedtype());
//									sys.setPmky("BETP");
//									Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
//									hotelproduct.setStrbedtype(syspar.getPmva());
//									product.setHotelproduct(hotelproduct);
//								}
//								// /判断选择的入住日期是否都有价格
//								long num = getDayNumb(rzti, ldti);
//								double totalprice = 0;
//								List pricelist = new ArrayList();
//								Date enddate = null;
//								Edmcrowdkindpricetab price = null;
//								double singleprice = 0;
//								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//								for (int x = 0; x < num; x++) {
//									if (enddate != null&& enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
//										singleprice = price.getMactualsaleprice();
//										if (hotelproduct != null) {
//											switch (hotelproduct.getWeektype()) {
//											case 0:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 1:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 2:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											case 3:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}
//												break;
//											default:
//												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//													singleprice = price.getWeekendprice();
//												}	
//												break;
//											}
//										}
//										totalprice += singleprice;
//										pricelist.add(singleprice);
//									} else {
//										String hsqlend = "select new map(pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
//												+ Long.parseLong(lgtp)+ " and pri.note1 = '"+ groupno+ "' and pri.note4 = '01' "+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
//												+ Long.parseLong(lgtp)+ " and edm.note1 = '"+ groupno+ "' and edm.note4='01' "+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()
//												+ ") <='"+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="+ Long.parseLong(lgtp)+ " and edm.note1 = '"+ groupno
//												+ "' and edm.note4='01' "+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()
//												+ "))>='"+ ldti+ "' and pri.itickettypeid="+ product.getItickettypeid()+ "  and pri.startdata<='"+ Tools.getDate(rzti, x)+ "' and pri.enddata>='"+ Tools.getDate(rzti, x) + "' ";
//										List lst = this.find(hsqlend);
//										if (lst != null && lst.size() > 0) {
//											Map cmap = (Map) lst.get(0);
//											price = new Edmcrowdkindpricetab();
//											BeanUtils.populate(price, cmap);
//											enddate = sf.parse(price.getEnddata());
//											singleprice = price.getMactualsaleprice();
//											if (hotelproduct != null) {
//												switch (hotelproduct.getWeektype()) {
//												case 0:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												case 1:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												case 2:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//												    }
//													break;
//												case 3:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 1|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 5|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												default:
//													if (Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 6|| Tools.getDayOfWeek(Tools.getDate(rzti,x)) == 7){
//														singleprice = price.getWeekendprice();
//													}
//													break;
//												}
//											}
//											totalprice += singleprice;
//											pricelist.add(singleprice);
//										}
//									}
//								}
//								product.setPriceList(pricelist);
//								double jprice = Math.ceil(totalprice / num);
//								product.setJprice(jprice);
//								productList2.add(product);
//							}
//						}
						map.put("productList", productList);

						// 查询服务商图片
						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
								+ iscenicid
								+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
						List piclist = searchDao.findTopNumb(sql, 1);
						String upadder = "",upfilename = "",address = "http://"+url;
						if (piclist != null && !piclist.isEmpty()) {
							Map picMap = (Map) piclist.get(0);
							upadder = picMap.get("upadder").toString();
							upfilename = picMap.get("upfilename").toString();
						}
						map.put("upadder", address+upadder+upfilename);
						//map.put("upfilename", upfilename);
						//this.find(sql);
						//map.put("piclist", piclist);

						Map lowprice = apphotelgetMinPrice(iscenicid, rzti,Long.parseLong(lgtp), groupno);
						double listingprice = 0L;
						double sprice = 0L;
						if (lowprice != null) {
							listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// 挂牌价
							sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// 实际售价
						}
						map.put("lowprice", sprice);
						map.put("height", listingprice);
						map.put("discount",String.valueOf(Math.round(sprice / listingprice* 100) / 10));
						
//						int total = 0;
//						int htotal = 0;
//						String comsql = " from Hscomment where oeid=" + iscenicid + " and status='01' ";
//						List clist = this.find(comsql);
//						if (clist != null && !clist.isEmpty()) {
//							total = clist.size();
//						}
//						comsql += " and  remarknum>=3 ";
//						clist = this.find(comsql);
//						if (clist != null && !clist.isEmpty()) {
//							htotal = clist.size();
//						}
//						map.put("ctotal", total);
//						if (total == 0 || htotal == 0) {
//							map.put("tjs", "100");
//						} else {
//							map.put("tjs", Math.ceil(total / htotal * 100));
//						}
					}
				}
			}
		}
		return list;
	}
	
	public Map apphotelgetMinPrice(Long iscenicid, String rzti, Long ibusinessid,String groupno) {
		String hql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab     p  where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
			+ iscenicid
			+ " and p.iscenicid = product.iscenicid and '"
			+ rzti
			+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";
		List list = searchDao.find(hql);
		Map pricemap = null;
		if (list != null && list.size() > 0) {
			pricemap = (Map) list.get(0);
		}
		return pricemap;
	}
	
	//酒店订单保存
	public String saveHotelOrder(String xml, String orid){
	 ObjectParse parse = new ObjectParse();
	 BaseModel model = null;
	 BaseModel newModel = null;
	 Long iscenicid = 0L;
	 String hqyatuDate = "";
	 MOrder m = (MOrder) searchDao.get(MOrder.class, orid);
	 List<ProductRequest> products = new ArrayList<ProductRequest>();
	 String hqytUrl = "";
	 String OwnerNumber = "";
	 Custom custom = null;
	 /*Custom custom = (Custom) getRequest().getSession().getAttribute(
             "userbean");*/
	 //------------------------------------------------------------------------//
	 
//----------------------------------------------------------------------------//	 
	 try{
		 model = parse.XmlToObject(xml);
		 newModel = validateUser(model.getUsid(), model.getPwd());
		 newModel.setPwd(model.getPwd());
		 newModel.setNodes(model.getNodes());
		 
		 
		 if("NULL".equals(model.getObject())||model.getObject()==null){
				 newModel.setLogonstatus("0");
				 newModel.setMsgtp("联系人信息不可为空!");
			    return JSON.toJSONString(newModel);	
		 }
//	     if (newModel.getLogonstatus().equals("0")) {
			     Map user = searchDao.findUser(model.getUsid(), model.getPwd());
			     String usid = user.get("usid").toString();		
		         LprPojo lpr = new LprPojo();
		         Map object = (Map)model.getObject();
		           object.put("state",0);
		           object.put("mont",0);
		           object.put("isSync","NULL");
		           object.put("zforid","1");
		         newModel.setObject(object);
		         //Long providerid = Long.valueOf(object.get("providerid").toString());
				 //String orzj = (String)object.get("orzj");lpr.setZjlb(orzj);
				 //String cardno = (String)object.get("cardno");lpr.setZjhm(cardno);
			     String holdername = (String)object.get("holdername");lpr.setDaoyou(holdername);
				 String telphone = (String)object.get("telphone");lpr.setMobile(telphone);
                 String seq = "".equals(object.get("seq"))?"":(String)object.get("seq");lpr.setSeq(seq);
				 String cyuser = "".equals(object.get("cyuser"))?"":(String)object.get("cyuser");//是否设为常用联系人

		 		if(StringUtil.isNotEmpty((String)object.get("checkinTime"))){
					lpr.setOrnote2((String)object.get("checkinTime"));//到店时间
				}
				 if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
					newModel.setLogonstatus("0");
					newModel.setMsgtp("联系人姓名不能为空!");
					return JSON.toJSONString(newModel);
				 }
				 if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
					newModel.setLogonstatus("0");
					newModel.setMsgtp("联系电话不能为空!");
					return JSON.toJSONString(newModel);
				 }else {
					Pattern p1 = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
					boolean c = p1.matcher(lpr.getMobile()).matches();
					if (!c) {
						newModel.setLogonstatus("0");
						newModel.setMsgtp("请输入正确的手机号码");
						return JSON.toJSONString(newModel);
					}
				 }
//				 if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
//					newModel.setLogonstatus("0");
//					newModel.setMsgtp("证件号码不能为空");
//					return JSON.toJSONString(newModel);
//				 } else {
//					  if (lpr.getZjlb() != null && lpr.getZjlb().equals("01")) {
//						Pattern p1 = Pattern
//								.compile("^\\d{15}|(\\d{17}(\\d|X|x))$");
//						boolean c = p1.matcher(lpr.getZjhm()).matches();
//						if (!c) {
//							newModel.setLogonstatus("0");
//							newModel.setMsgtp("请输入正确的身份证号码");
//							return JSON.toJSONString(newModel);
//						}
//					  }
//				 }
				 if(cyuser!=null&&!cyuser.equals("")){
						try {
							if (lpr.getSeq() == null || lpr.getSeq().equals("")) {
								lingpiaouserService.addLingPiaoUser(new Lingpiaouser(null,usid, lpr.getDaoyou(), lpr.getZjlb(),lpr.getZjhm(), lpr.getMobile(), "", 0),new Customlog());
							}
						} catch (Exception e) {
							e.printStackTrace();
							newModel.setLogonstatus("0");
							newModel.setMsgtp("保存联系人失败!");
							return JSON.toJSONString(newModel);
						}
				 }
				 List<HotelDTO> hotels = new ArrayList<HotelDTO>();
				 if (newModel.getNodes() == null || newModel.getNodes().size() == 0) {
				      newModel.setLogonstatus("0");
					  newModel.setMsgtp("请选择预订酒店!");
					  return JSON.toJSONString(newModel);
		         }else{
		            for (Map hotelm : newModel.getNodes()) {
		            	HotelDTO hotel = new HotelDTO();
		            	String productid = hotelm.get("itickettypeid").toString();hotel.setProductid(productid);
		            	//String icrowdkindpriceid = hotelm.get("icrowdkindid").toString();hotel.setIcrowdkindpriceid(icrowdkindpriceid);
		            	String rzdate = hotelm.get("rzdate").toString();hotel.setRzdate(rzdate);
		            	String tddate = hotelm.get("tddate").toString();hotel.setTddate(tddate);
		            	String num = hotelm.get("num").toString();hotel.setNum(num);
		            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        		if(sdf.parse(rzdate).before(sdf.parse(Tools.getDays()))){
		        			newModel.setLogonstatus("0");
						    newModel.setMsgtp("预定日期不能小于当前日期");
						   return JSON.toJSONString(newModel);
		        		}
		        		if(sdf.parse(tddate).before(sdf.parse(Tools.getDays()))){
		        			newModel.setLogonstatus("0");
						    newModel.setMsgtp( "离店日期不能小于当前日期");
						   return JSON.toJSONString(newModel);
		        		}
//		            	String groupno = hotelm.get("note2").toString();hotel.setNote2(groupno);
//		            	if(groupno!=null && "9999".equals(groupno)){
//		    				Long snumb = this.shopcartService.checkStock(Long.parseLong(hotel.getProductid()), Long.parseLong(hotel.getIcrowdkindpriceid()), hotel.getRzdate());
//		    				if(Long.parseLong(hotel.getNum())>snumb){
//		    					newModel.setLogonstatus("0");
//								newModel.setMsgtp("库存不足,预定失败");
//								return JSON.toJSONString(newModel);
//		    				}
//		    			}else{
//		    				Long numb = getDayNumb(hotel.getRzdate(), hotel.getTddate());
//		    				for(int i=0;i<numb;i++){
//		    					Long stocknum = this.shopcartService.checkHotelStock(Long.parseLong(hotel.getProductid()), Tools.getDate(hotel.getRzdate(), i));
//		    					if(stocknum==null){
//		    						continue;
//		    					}else{
//		    						if(Long.parseLong(hotel.getNum())>stocknum){
//		    							newModel.setLogonstatus("0");
//										newModel.setMsgtp(Tools.getDate(hotel.getRzdate(), i)+"库存不足，预定失败");
//										return JSON.toJSONString(newModel);
//		    						}
//		    					}
//		    				}
//		    			}
		    			hotels.add(hotel);
					 } 
		          }
				 
				 custom    = (Custom)searchDao.get(Custom.class,usid);
				  OrderCombinDTO dto  = hotelService.combinationOrder(orid, custom,hotels, lpr);
				  
				//  hotelOrderTrans =(IOrderSaveTrans) SpringUtil.getBean("hotelOrderTrans") ;	
				
				  
				  boolean result      = hotelOrderTrans.SaveOrder(dto);
				  m = (MOrder) searchDao.get(MOrder.class, orid);
				  if (result) {
				  	List list = searchDao.find(" from TOrder t where t.id.orid='"+orid+"' ");
					if(list!=null&&!list.isEmpty()){
						TOrder torder = (TOrder) list.get(0);
						iscenicid = torder.getId().getIscenicid();
						searchDao.bulkUpdate("update Esbscenicareatab s set s.popupoint = s.popupoint+10 where s.iscenicid = "+ torder.getId().getIscenicid());
					}
						OwnerNumber = lpr.getMobile();
//						mbmessageService.sendMessageNew(lpr.getMobile(), "0016", new String[]{orid});
				  }
				  String zforid = findByOrderid(orid, "10");
				  if ("-1".equals(zforid) || "0".equals(zforid)) {
						newModel.setLogonstatus("0");
					    newModel.setMsgtp("获取支付号失败!");
						return JSON.toJSONString(newModel);
				  } else {
						object.put("zforid", zforid);
				  }
				  object.put("state",1);
				  object.put("mont",m.getZfmont());
//		 }else{
//	    	 newModel.setLogonstatus("0");
//			 newModel.setMsgtp("登录信息错误，请重新登录!");
//		    return JSON.toJSONString(newModel);
//	     }
	  }catch (Exception e) {
		  e.printStackTrace();
		  this.shopcartService.updateOrderDdzt(orid,"98");
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("酒店订单保存失败,请重试或与客服联系!");
		  return JSON.toJSONString(newModel);
	  }  
	  newModel.setMsgtp("酒店订单保存成功!");
	  //------------------------------------------------------------------------------//
	  try{
	         List<TOrderlist> torderList = orderService.getTOrderlists(orid);
	         List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
	         if(torderList != null && !torderList.isEmpty()){
	             for (TOrderlist tl : torderList){
	                 iscenicid = tl.getId().getIscenicid();
	                 hqyatuDate = tl.getDtenddate();
	                 StockOrderInfo stock = new StockOrderInfo();
	                 stock.setOrid(tl.getId().getOrid());
	                 stock.setProviderId(tl.getId().getIscenicid());
	                 stock.setProductId(tl.getItickettypeid());
	                 stock.setPriceId(tl.getIcrowdkindpriceid());
//	                 stock.setUsid(custom.getUsid());
	                 stock.setNumb(tl.getNumb());
	                 stock.setStockDate(tl.getDtstartdate());
	                 stocks.add(stock);

	                 ProductRequest productRequest = new ProductRequest();
	                 productRequest.setExternalId(tl.getItickettypeid());
	                 Edmtickettypetab ticket = (Edmtickettypetab) genericService.get(Edmtickettypetab.class,tl.getItickettypeid());
	                 productRequest.setName(ticket.getSztickettypename());
	                 productRequest.setPrice(tl.getPric());
	                 productRequest.setNumb(tl.getNumb());
	                 products.add(productRequest);
	             }
	         }
//	         stockService.saveStockDetails(stocks,true);
	     }catch (Exception e){
	         //作废订单，并删除实名制信息
	         this.shopcartService.updateOrderDdzt(orid,"98");
	         newModel.setLogonstatus("0");
	         newModel.setMsgtp(e.getMessage());
	         return JSON.toJSONString(newModel);
	     }
	  //------------------------------------------------------------------------------//
	  GuaranteeRequest request = new GuaranteeRequest();
	  request.setOrid(orid);
	  request.setUsid(model.getUsid());
	  request.setTotalMoney(m.getMont());
	  request.setUsername(model.getUsername());
	  request.setPhone(custom.getMobile());
	  ProviderCompany pc = ticketService.queryProviderCompany(iscenicid);
	  if(pc != null){
          request.setIssuerId(pc.getHqytId().toString());
      }else{
          this.shopcartService.updateOrderDdzt(orid,"98");
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("服务商未开户不可预定");
		  return JSON.toJSONString(newModel);
      }
	  request.setProducts(products);
	  request.setConsumeTimeLimit(hqyatuDate);
	  HqytClient client = new HqytClient();
	 
	  LegalPersonRequest receiver = new LegalPersonRequest();
      if(custom.getNote9()==null) {
        this.shopcartService.updateOrderDdzt(orid,"98");
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("用户没有同步结算系统的用户ID");
		  return JSON.toJSONString(newModel);
      }
      receiver.setId(Long.valueOf(custom.getNote9()));
      request.setReceiver(receiver);
	  
	  JSONInvoice invoice = null;
	  try {
      	request.setTitle("散客订单");
      	invoice = client.appGuaranteeSk(request);
      } catch(Exception e) {
          this.shopcartService.updateOrderDdzt(orid,"98");
      	//抛出结算接口异常
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("HQYT:" + e.getMessage());
		  return JSON.toJSONString(newModel);
      }
	  if(invoice != null){
          m.setNoteh(invoice.getId().toString());
//          genericService.update(m);
      }else{
          this.shopcartService.updateOrderDdzt(orid,"98");
		  newModel.setLogonstatus("0");
		  newModel.setMsgtp("创建支付订单失败");
		  return JSON.toJSONString(newModel);
      }
	  
	  
	  
	  Map<String, String> merchant = CommonUtil.findMerchant();
      SortedMap<String, Object> params = new TreeMap<String, Object>();
      params.put("merchantId", merchant.get("merchantId"));
      params.put("outTradeNo", m.getOrid());
      //结算回调地址
      params.put("returnUrl", CommonUtil.findReturnUrle());
      
      String sign = MD5Util.createSign(params, merchant.get("key"), "UTF-8");
      params.put("sign", sign);
      //访问链接
      hqytUrl = CommonUtil.findPayUrld() + "/wapcounter?";
      List<String> keys = new ArrayList<String>(params.keySet());
      for (int i = 0; i < keys.size(); i++) {
          String key = keys.get(i);
          String value = String.valueOf(params.get(key));

          //拼接时，不包括最后一个&字符
          if (i == keys.size() - 1) {
              hqytUrl = hqytUrl + key + "=" + value;
          } else {
              hqytUrl = hqytUrl + key + "=" + value + "&";
          }
          newModel.setPayUrl(hqytUrl);
      }
	  
	  
	  
	  //-------------------------------------------------------------------------------//
	  return JSON.toJSONString(newModel);
    }
	
	
	//酒店订单保存
		public BaseModel wxSaveHotelOrder(String xml, String orid){
		 ObjectParse parse = new ObjectParse();
		 BaseModel model = null;
		 BaseModel newModel = null;
		 Long iscenicid = 0L;
		 String hqyatuDate = "";
		 MOrder m = (MOrder) searchDao.get(MOrder.class, orid);
		 List<ProductRequest> products = new ArrayList<ProductRequest>();
		 String hqytUrl = "";
		 String OwnerNumber = "";
		 Custom custom = null;
		 /*Custom custom = (Custom) getRequest().getSession().getAttribute(
	             "userbean");*/
		 //------------------------------------------------------------------------//
		 
	//----------------------------------------------------------------------------//	 
		 try{
			 model = parse.XmlToObject(xml);
			 newModel = validateUser(model.getUsid(), model.getPwd());
			 newModel.setPwd(model.getPwd());
			 newModel.setNodes(model.getNodes());
			 
			 
			 if("NULL".equals(model.getObject())||model.getObject()==null){
					 newModel.setLogonstatus("0");
					 newModel.setMsgtp("联系人信息不可为空!");
				    return newModel;	
			 }
//		     if (newModel.getLogonstatus().equals("0")) {
				     Map user = searchDao.findUser(model.getUsid(), model.getPwd());
				     String usid = user.get("usid").toString();		
			         LprPojo lpr = new LprPojo();
			         Map object = (Map)model.getObject();
			           object.put("state",0);
			           object.put("mont",0);
			           object.put("isSync","NULL");
			           object.put("zforid","1");
			         newModel.setObject(object);
			         //Long providerid = Long.valueOf(object.get("providerid").toString());
					 //String orzj = (String)object.get("orzj");lpr.setZjlb(orzj);
					 //String cardno = (String)object.get("cardno");lpr.setZjhm(cardno);
				     String holdername = (String)object.get("holdername");lpr.setDaoyou(holdername);
					 String telphone = (String)object.get("telphone");lpr.setMobile(telphone);
	                 String seq = "".equals(object.get("seq"))?"":(String)object.get("seq");lpr.setSeq(seq);
					 String cyuser = "".equals(object.get("cyuser"))?"":(String)object.get("cyuser");//是否设为常用联系人

			 		if(StringUtil.isNotEmpty((String)object.get("checkinTime"))){
						lpr.setOrnote2((String)object.get("checkinTime"));//到店时间
					}
					 if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
						newModel.setLogonstatus("0");
						newModel.setMsgtp("联系人姓名不能为空!");
						return newModel;
					 }
					 if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
						newModel.setLogonstatus("0");
						newModel.setMsgtp("联系电话不能为空!");
						return newModel;
					 }else {
						Pattern p1 = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
						boolean c = p1.matcher(lpr.getMobile()).matches();
						if (!c) {
							newModel.setLogonstatus("0");
							newModel.setMsgtp("请输入正确的手机号码");
							return newModel;
						}
					 }
//					 if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
//						newModel.setLogonstatus("0");
//						newModel.setMsgtp("证件号码不能为空");
//						return JSON.toJSONString(newModel);
//					 } else {
//						  if (lpr.getZjlb() != null && lpr.getZjlb().equals("01")) {
//							Pattern p1 = Pattern
//									.compile("^\\d{15}|(\\d{17}(\\d|X|x))$");
//							boolean c = p1.matcher(lpr.getZjhm()).matches();
//							if (!c) {
//								newModel.setLogonstatus("0");
//								newModel.setMsgtp("请输入正确的身份证号码");
//								return JSON.toJSONString(newModel);
//							}
//						  }
//					 }
					 if(cyuser!=null&&!cyuser.equals("")){
							try {
								if (lpr.getSeq() == null || lpr.getSeq().equals("")) {
									lingpiaouserService.addLingPiaoUser(new Lingpiaouser(null,usid, lpr.getDaoyou(), lpr.getZjlb(),lpr.getZjhm(), lpr.getMobile(), "", 0),new Customlog());
								}
							} catch (Exception e) {
								e.printStackTrace();
								newModel.setLogonstatus("0");
								newModel.setMsgtp("保存联系人失败!");
								return newModel;
							}
					 }
					 List<HotelDTO> hotels = new ArrayList<HotelDTO>();
					 if (newModel.getNodes() == null || newModel.getNodes().size() == 0) {
					      newModel.setLogonstatus("0");
						  newModel.setMsgtp("请选择预订酒店!");
						  return newModel;
			         }else{
			            for (Map hotelm : newModel.getNodes()) {
			            	HotelDTO hotel = new HotelDTO();
			            	String productid = hotelm.get("itickettypeid").toString();hotel.setProductid(productid);
			            	//String icrowdkindpriceid = hotelm.get("icrowdkindid").toString();hotel.setIcrowdkindpriceid(icrowdkindpriceid);
			            	String rzdate = hotelm.get("rzdate").toString();hotel.setRzdate(rzdate);
			            	String tddate = hotelm.get("tddate").toString();hotel.setTddate(tddate);
			            	String num = hotelm.get("num").toString();hotel.setNum(num);
			            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        		if(sdf.parse(rzdate).before(sdf.parse(Tools.getDays()))){
			        			newModel.setLogonstatus("0");
							    newModel.setMsgtp("预定日期不能小于当前日期");
							   return newModel;
			        		}
			        		if(sdf.parse(tddate).before(sdf.parse(Tools.getDays()))){
			        			newModel.setLogonstatus("0");
							    newModel.setMsgtp( "离店日期不能小于当前日期");
							   return newModel;
			        		}
//			            	String groupno = hotelm.get("note2").toString();hotel.setNote2(groupno);
//			            	if(groupno!=null && "9999".equals(groupno)){
//			    				Long snumb = this.shopcartService.checkStock(Long.parseLong(hotel.getProductid()), Long.parseLong(hotel.getIcrowdkindpriceid()), hotel.getRzdate());
//			    				if(Long.parseLong(hotel.getNum())>snumb){
//			    					newModel.setLogonstatus("0");
//									newModel.setMsgtp("库存不足,预定失败");
//									return JSON.toJSONString(newModel);
//			    				}
//			    			}else{
//			    				Long numb = getDayNumb(hotel.getRzdate(), hotel.getTddate());
//			    				for(int i=0;i<numb;i++){
//			    					Long stocknum = this.shopcartService.checkHotelStock(Long.parseLong(hotel.getProductid()), Tools.getDate(hotel.getRzdate(), i));
//			    					if(stocknum==null){
//			    						continue;
//			    					}else{
//			    						if(Long.parseLong(hotel.getNum())>stocknum){
//			    							newModel.setLogonstatus("0");
//											newModel.setMsgtp(Tools.getDate(hotel.getRzdate(), i)+"库存不足，预定失败");
//											return JSON.toJSONString(newModel);
//			    						}
//			    					}
//			    				}
//			    			}
			    			hotels.add(hotel);
						 } 
			          }
					 
					 custom    = (Custom)searchDao.get(Custom.class,usid);
					  OrderCombinDTO dto  = hotelService.combinationOrder(orid, custom,hotels, lpr);
					  
					//  hotelOrderTrans =(IOrderSaveTrans) SpringUtil.getBean("hotelOrderTrans") ;	
					
					  
					  boolean result      = hotelOrderTrans.SaveOrder(dto);
					  m = (MOrder) searchDao.get(MOrder.class, orid);
					  if (result) {
					  	List list = searchDao.find(" from TOrder t where t.id.orid='"+orid+"' ");
						if(list!=null&&!list.isEmpty()){
							TOrder torder = (TOrder) list.get(0);
							iscenicid = torder.getId().getIscenicid();
							searchDao.bulkUpdate("update Esbscenicareatab s set s.popupoint = s.popupoint+10 where s.iscenicid = "+ torder.getId().getIscenicid());
						}
							OwnerNumber = lpr.getMobile();
//							mbmessageService.sendMessageNew(lpr.getMobile(), "0016", new String[]{orid});
					  }
					  String zforid = findByOrderid(orid, "10");
					  if ("-1".equals(zforid) || "0".equals(zforid)) {
							newModel.setLogonstatus("0");
						    newModel.setMsgtp("获取支付号失败!");
							return newModel;
					  } else {
							object.put("zforid", zforid);
					  }
					  object.put("state",1);
					  object.put("mont",m.getZfmont());
//			 }else{
//		    	 newModel.setLogonstatus("0");
//				 newModel.setMsgtp("登录信息错误，请重新登录!");
//			    return JSON.toJSONString(newModel);
//		     }

		  }catch (Exception e) {
			 this.shopcartService.updateOrderDdzt(orid,"98");
			  e.printStackTrace();
			  newModel.setLogonstatus("0");
			  newModel.setMsgtp("酒店订单保存失败,请重试或与客服联系!");
			  return newModel;
		  }  
		  newModel.setMsgtp("酒店订单保存成功!");
		  //------------------------------------------------------------------------------//
		  try{
		         List<TOrderlist> torderList = orderService.getTOrderlists(orid);
		         List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
		         if(torderList != null && !torderList.isEmpty()){
		             for (TOrderlist tl : torderList){
		                 iscenicid = tl.getId().getIscenicid();
		                 hqyatuDate = tl.getDtenddate();
		                 StockOrderInfo stock = new StockOrderInfo();
		                 stock.setOrid(tl.getId().getOrid());
		                 stock.setProviderId(tl.getId().getIscenicid());
		                 stock.setProductId(tl.getItickettypeid());
		                 stock.setPriceId(tl.getIcrowdkindpriceid());
//		                 stock.setUsid(custom.getUsid());
		                 stock.setNumb(tl.getNumb());
		                 stock.setStockDate(tl.getDtstartdate());
		                 stocks.add(stock);

		                 ProductRequest productRequest = new ProductRequest();
		                 productRequest.setExternalId(tl.getItickettypeid());
		                 Edmtickettypetab ticket = (Edmtickettypetab) genericService.get(Edmtickettypetab.class,tl.getItickettypeid());
		                 productRequest.setName(ticket.getSztickettypename());
		                 productRequest.setPrice(tl.getPric());
		                 productRequest.setNumb(tl.getNumb());
		                 products.add(productRequest);
		             }
		         }
//		         stockService.saveStockDetails(stocks,true);
		     }catch (Exception e){
		         //作废订单，并删除实名制信息
		         this.shopcartService.updateOrderDdzt(orid,"98");
		         newModel.setLogonstatus("0");
		         newModel.setMsgtp(e.getMessage());
		         return newModel;
		     }
		  //------------------------------------------------------------------------------//
		  GuaranteeRequest request = new GuaranteeRequest();
		  request.setOrid(orid);
		  request.setUsid(model.getUsid());
		  request.setTotalMoney(m.getMont());
		  request.setUsername(model.getUsername());
		  request.setPhone(custom.getMobile());
		  ProviderCompany pc = ticketService.queryProviderCompany(iscenicid);
		  if(pc != null){
	          request.setIssuerId(pc.getHqytId().toString());
	      }else{
	          this.shopcartService.updateOrderDdzt(orid,"98");
			  newModel.setLogonstatus("0");
			  newModel.setMsgtp("服务商未开户不可预订");
			  return newModel;
	      }
		  request.setProducts(products);
		  request.setConsumeTimeLimit(hqyatuDate);
		  HqytClient client = new HqytClient();
		 
		  LegalPersonRequest receiver = new LegalPersonRequest();
	      if(custom.getNote9()==null) {
	        this.shopcartService.updateOrderDdzt(orid,"98");
			  newModel.setLogonstatus("0");
			  newModel.setMsgtp("用户没有同步结算系统的用户ID");
			  return newModel;
	      }
	      receiver.setId(Long.valueOf(custom.getNote9()));
	      request.setReceiver(receiver);
		  
		  JSONInvoice invoice = null;
		  try {
	      	request.setTitle("散客订单");
	      	invoice = client.appGuaranteeSk(request);
	      } catch(Exception e) {
	          this.shopcartService.updateOrderDdzt(orid,"98");
	      	//抛出结算接口异常
			  newModel.setLogonstatus("0");
			  newModel.setMsgtp("HQYT:" + e.getMessage());
			  return newModel;
	      }
		  if(invoice != null){
	          m.setNoteh(invoice.getId().toString());
//	          genericService.update(m);
	      }else{
	          this.shopcartService.updateOrderDdzt(orid,"98");
			  newModel.setLogonstatus("0");
			  newModel.setMsgtp("创建支付订单失败");
			  return newModel;
	      }
		  
		  
		  
		  Map<String, String> merchant = CommonUtil.findMerchant();
	      SortedMap<String, Object> params = new TreeMap<String, Object>();
	      params.put("merchantId", merchant.get("merchantId"));
	      params.put("outTradeNo", m.getOrid());
	      //结算回调地址
	      params.put("returnUrl", CommonUtil.findReturnUrle());
	      
	      String sign = MD5Util.createSign(params, merchant.get("key"), "UTF-8");
	      params.put("sign", sign);
	      //访问链接
	      //hqytUrl = CommonUtil.findPayUrld() + "/wapcounter?";
	      hqytUrl = CommonUtil.findWxPayUrl() + "?";
	      
	      List<String> keys = new ArrayList<String>(params.keySet());
	      for (int i = 0; i < keys.size(); i++) {
	          String key = keys.get(i);
	          String value = String.valueOf(params.get(key));

	          //拼接时，不包括最后一个&字符
	          if (i == keys.size() - 1) {
	              hqytUrl = hqytUrl + key + "=" + value;
	          } else {
	              hqytUrl = hqytUrl + key + "=" + value + "&";
	          }
	          newModel.setPayUrl(hqytUrl);
	      }
		  
		  
		  
		  //-------------------------------------------------------------------------------//
		  return newModel;
	    }
	
	public long getDayNumb(String rzti, String ldti) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(rzti);
		Date enddate = sdf.parse(ldti);
		long time = enddate.getTime() - startdate.getTime();
		long time2 = 24 * 3600 * 1000;
		return time / time2;
	}
	
	public String findByOrderid(String orid, String bank) {
		MOrder mo = (MOrder) this.searchDao.get(MOrder.class, orid);
		if (mo != null) {
			String orti = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			Zhifu zf = new Zhifu();
			try {
				zf.setOrid(orid);
				zf.setUsid(mo.getUsid());
				zf.setMont(Double.valueOf(mo.getMont()));
				zf.setPayid(getZhifuNewMaxNo());
				zf.setOrti(orti);
				int seq = this.zhifumaxseq();
				zf.setSeq(seq);
				zf.setIsok(0);
				zf.setBank(bank);
				zf.setTdmont(Double.valueOf("0"));
				this.searchDao.save(zf);// 保存支付信息
				return zf.getPayid();
			} catch (Exception e) {
				e.printStackTrace();
				return "-1";
			}
		} else {
			return "0";
		}
	}
	
	public int zhifumaxseq(){
		int seq = 0;
		List list = this.searchDao.find("select nvl(max(z.seq),0) as seq from Zhifu z");
		if(list!=null&&list.size()>0){
			seq = (Integer)list.get(0);
		}
		return seq+1;
	}
	
	public List findOrderAll(String usid, String pwd, String orid,
			String iscenictype, String ddzt) {

		List list = null;
		List listzf = new ArrayList();
		//productType字段：01 门票，02 旅游卡, num 购票数量
		StringBuffer sql = new StringBuffer(
				"select new map(m.orid as orid, t.id.iscenicid as iscenicid, t.scenictype as scenictype, t.orfl as orfl, " +
						"m.usid as usid, t.iregionalid as iregionalid, m.ddzt as ddzt,m.orda as orda,m.orti as orti, " +
						"t.dtstartdate as dtstartdate, t.dtenddate as dtenddate,t.dtmakedate as dtmakedate,t.dyusid as dyusid, " +
						"t.orph as orph, t.faxno as faxno, m.mont as mont, m.yhamnt as yhamnt,m.zfmont as zfmont, m.isallcp as isallcp, " +
						"t.isjfjf as isjfjf, t.ischupiao as ischupiao, t.fempid as fempid, t.forcedrefund as forcedrefund,t.notea as notea, " +
						"t.ornm as ornm, t.orzj as orzj, t.orhm as orhm,esb.szscenicname as szscenicname,sysv.pmva as pmva, " +
						"m.ordersource as ordersource,m.notee as notee, '01' as productType, ol.numb as num) " +
						"from TOrder t,MOrder m,Esbscenicareatab esb,TOrderlist ol,Sysparv5 sysv " +
						"where t.id.orid=m.orid and esb.iscenicid=t.id.iscenicid and t.id.orid=ol.id.orid and ol.id.iscenicid=t.id.iscenicid and sysv.id.pmky='DDZT' and sysv.id.pmcd=m.ddzt ");

		if (usid != null && !"".equals(usid.trim())) {
			sql.append(" and t.usid='" + usid + "'");
			//System.out.println("=======sql=========" + sql);
		} else {
			//System.out.println("=======sql=========" + sql);
			return null;
		}
		if (ddzt != null && !"".equals(ddzt.trim())&& !"''".equals(ddzt.trim())) {
			sql.append(" and m.ddzt='" + ddzt + "'");
		}
		if (orid != null && !"".equals(orid.trim())
				&& !"''".equals(orid.trim())) {
			sql.append(" and t.id.orid ='" + orid + "'");
		}
		if (iscenictype != null && !"".equals(iscenictype.trim())
				&& !"''".equals(iscenictype.trim())) {
			    //李进 读取所有订单；
				sql.append(" and t.scenictype='" + iscenictype + "'");
			
		}
		sql.append(" order by t.dtmakedate desc");
		//System.out.println("=======sql=========" + sql);
		list = searchDao.find(sql.toString());
		//System.out.println("=======21122121" + list.size());
		if (!list.isEmpty() && list != null) {
			//System.out.println("======55554");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("======111");
				Map map = (Map) list.get(i);
				String zfsql = "select new map(t.orid as orid,t.usid as usid,t.payid as payid,t.mont as mont) from Zhifu t where t.usid='"
						+ map.get("usid").toString()+ "' and t.orid='"
						+ map.get("orid").toString() + "' order by t.seq desc";
				listzf = searchDao.find(zfsql);
				//System.out.println("======2222");
				if (!listzf.isEmpty() && listzf != null) {
					Map map1 = (Map) listzf.get(0);
					map.put("zf", map1.get("payid"));
				}
				//System.out.println("======333333");
				if (map.get("orid") != null) {
					List yclist = orderService.getYOrderChildList(map.get(
							"orid").toString());
					//System.out.println("======44444444");
					//System.out.println("==yclist.size()===44444444"							+ yclist.size());
					if (yclist != null && yclist.size() > 0) {
						
						//System.out.println("ffdfdf"+map.get(								"orid").toString());
						
						//System.out.println("======55555555555");
						Map ymap = (Map) yclist.get(0);
					
						String yddzt = ymap.get("ddzt") ==null?"" :ymap.get("ddzt").toString();
						//System.out.println("yddzt"+yddzt);
						if (yddzt .equals("") !=false)
						{
						List li = query("DDZT", " pmcd='" + yddzt + "'");
						if (li != null && li.size() > 0) {
							Map sys = (Map) query("DDZT",
									" pmcd='" + yddzt + "'").get(0);
							//System.out.println("sys" + sys);
							map.put("ddzt", yddzt);
							map.put("pmva", sys.get("pmva").toString());
						}
						}
						//System.out.println("yddzt65555555");
					}
				}
			}
		}
		//合并旅游卡订单
		List tourCardOrderList = findTourCardOrder(usid, ddzt);
		if(tourCardOrderList != null && !tourCardOrderList.isEmpty()) {
			list.addAll(tourCardOrderList);
			//按时间排序
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					Map map1 = (Map) o1;
					Map map2 = (Map) o2;
					String date1 = formatCreateDate(map1);
					String date2 = formatCreateDate(map2);
					if (date2 != null) {
						return date2.compareTo(date1);
					}
					return -1;
				}
			});
		}
		return list;
	}

	public String formatCreateDate(Map map){
		Object date = map.get("dtmakedate");
		if(date == null){
			return "";
		}
		return date instanceof String ? (String)date: DateUtils.formatDate((Date)date, DateUtils.DATETIME_PATTERN);
	}

	/**
	 * 查找未失效的旅游卡订单列表
	 * @param userId 用户ID
	 * @param payStatusStr 原订单支付状态码
	 * @return
	 */
	public  List findTourCardOrder(String userId, String payStatusStr){
		if("null".equalsIgnoreCase(payStatusStr)){
			payStatusStr = null;
		}
		Long payStatus = "00".equals(payStatusStr)|| "01".equals(payStatusStr) ? Long.valueOf(payStatusStr.substring(0, 1)) : -1L;
		String merchantId = CommonUtil.getMerchantId();
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("select new map(tco.orderID as orid, tco.userId as usid, tco.createDate as dtmakedate, ");
		stringBuilder.append("case when tco.payStatus=1 then '已支付' else '未付款' end as pmva, '0'||tco.payStatus as ddzt, tco.price as zfmont, tco.cardName as szscenicname, '02' as productType, 1 as num, ");
		stringBuilder.append(merchantId);
		stringBuilder.append(" as merchantId)");
		stringBuilder.append(" from TourCardOrder tco where tco.userId=? and (tco.payStatus=? or 'ALL'=?) and ((tco.payStatus=0 and tco.createDate>?) or tco.payStatus=1)");

		List list = this.searchDao.find(stringBuilder.toString(),
				new Object[]{userId, payStatus, StringUtil.isEmpty(payStatusStr) ? "ALL" : payStatus.toString(), DateUtils.addMinute(new Date(), -30)});
		return list;
	}
	
	/**
	 * 取最大支付号
	 */
	public String getZhifuNewMaxNo() throws Exception {
		String zfqz = this.retrieveSysparKey("ZFQZ");
		if (zfqz == null || zfqz.equals("")) {
			zfqz = "ZF";
		} else {
			if (zfqz.length() > 2) {
				zfqz = zfqz.substring(0, 2);
			}
		}
		return this.searchDao.getZhifuMaxNo().replaceAll("ZF", zfqz).trim();
	}
	
	private String SYSPAR_HSQL = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where ";
	
	/**
	 * 读取支付前缀参数，无表示没有
	 * 
	 * @param pmky
	 * @return
	 */
	String retrieveSysparKey(String pmky) {

		try {
			List list = new ArrayList();
			String hsql = SYSPAR_HSQL;
			hsql += " sys.id.pmky='" + pmky
					+ "' and sys.id.pmcd not like'%*%' order by sys.id.pmcd";
			list = this.searchDao.find(hsql);

			// List list = this.retrieveSysparKey("ZFQZ") ; //读取支付前缀
			String zfqz = "";
			if (list.size() > 0) {
				Map v5 = (Map) list.get(0);
				if (v5.get("pmcd").equals("0001")) // 是否判断预付款
				{
					zfqz = (String) v5.get("pmva");
				} else {
					zfqz = "";
				}
			}
			return zfqz;
		} catch (Exception e) {
			return "";
		}
	}
	
	public Map productXuzhi(Long iscenicid) throws Exception{
		Map maprovider = new HashMap();
		Esbscenicareatab provider = new Esbscenicareatab();
		provider = ticketService.query(iscenicid);
		if (provider.getSznote() != null && !"".equals(provider.getSznote())) {
			String note = Tools.getStrByBlob(provider.getSznote());
			provider.setNote(note);
		}
		if (provider.getBookdescription() != null && !"".equals(provider.getBookdescription())) {
			String strbookdescription = Tools.getStrByBlob(provider.getBookdescription());
			provider.setStrbookdescription(strbookdescription);
		}
		maprovider.put("note", provider.getNote());
		maprovider.put("strbookdescription", provider.getStrbookdescription());
		maprovider.put("imaxdata", provider.getImaxdata());
		maprovider.put("szlasttime", provider.getSzlasttime());
		return maprovider;
	}
	
	public List query(String pmky, String pmcdwhere) {
		List list = new ArrayList();
		String hsql = SYSPAR_HSQL;
		 hsql += " sys.id.pmky='"+ pmky + "' and " + pmcdwhere;
		list = searchDao.find(hsql);
		return list;
    }
	/**
	 * 读取产品详情
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param ibusinessid
	 * @return
	 */
	public Object getHotelProductDail(Long iscenicid, String rzti, String ldti,
			String lgtp, Long ibusinessid,String url) {
		try {
			//李进 2014-09-27 修改
			
		    //Spring 注入有问题；
			Esbscenicareatab hotel = hotelService.getHotelTicketduct(iscenicid,rzti, ldti,ibusinessid);
			Esbscenicareatab hotel2 = (Esbscenicareatab) searchDao.get(Esbscenicareatab.class, iscenicid);
			if (hotel2.getSznote() != null && !"".equals(hotel2.getSznote())) {
				hotel.setNote(Tools.getStrByBlob(hotel2.getSznote()));
			}
			if (hotel.getSzgrade() == null || hotel.getSzgrade().equals("")) {
				hotel.setSzgrade("99");
			}
			Sysparv5 sysparv5 = (Sysparv5) searchDao.get(Sysparv5.class,
					new Sysparv5Id("DENJ", hotel.getSzgrade()));
			hotel.setStrgrade(sysparv5.getPmva());

			List prolist = searchDao.searchProduct(iscenicid, rzti,url);
			// System.out.println("prolist" + prolist);
			return prolist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getBaseProductDail(Long iscenicid, String rzti, String ldti,
			String lgtp, Long ibusinessid) {
		try {
			//李进 2014-09-27 修改
			
		    //Spring 注入有问题；
			/*
			Esbscenicareatab hotel = hotelService.getHotelTicketduct(iscenicid,rzti, ldti,ibusinessid);
			Esbscenicareatab hotel2 = (Esbscenicareatab) searchDao.get(Esbscenicareatab.class, iscenicid);
			if (hotel2.getSznote() != null && !"".equals(hotel2.getSznote())) {
				hotel.setNote(Tools.getStrByBlob(hotel2.getSznote()));
			}
			if (hotel.getSzgrade() == null || hotel.getSzgrade().equals("")) {
				hotel.setSzgrade("99");
			}
			Sysparv5 sysparv5 = (Sysparv5) searchDao.get(Sysparv5.class,
					new Sysparv5Id("DENJ", hotel.getSzgrade()));
			hotel.setStrgrade(sysparv5.getPmva());
            */
			List prolist = searchDao.searchBaseProduct(iscenicid, rzti);
			// System.out.println("prolist" + prolist);
			return prolist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询产品详情信息
	public String getProductDetailXq(String iscenicid46,String itickettypeid46,String url){
		if(url==null||url.length()<0){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		String message = "查询成功!";
		boolean status = true;
		Map<String,Object> obj = new HashMap<String,Object>();
		try {
			Long itickettypeid = Long.valueOf(itickettypeid46);
			Edmtickettypetab product =  (Edmtickettypetab)searchDao.get(Edmtickettypetab.class, itickettypeid);
			 Sysparv5 sys = (Sysparv5) searchDao.get(Sysparv5.class,new Sysparv5Id("PRTP",product.getBycategorytype()));
			 product.setStrbycategorytype(sys.getPmva());
			//Edmtickettypetab product = hotelService.getHotel(itickettypeid);
			Esbscenicareatab scenic =  (Esbscenicareatab)searchDao.get(Esbscenicareatab.class, product.getIscenicid());
			scenic.setBookdescription(null);scenic.setSznote(null);
			Hotelproduct hotel = null;
			String strbedtype = "不详",breakfasttype = "无",widebandtype = "无";
			if("06".equals(scenic.getScenictype())||"08".equals(scenic.getScenictype())){
				hotel = (Hotelproduct)searchDao.get(Hotelproduct.class, itickettypeid);  
				if (hotel == null) {
					hotel = new Hotelproduct();
					hotel.setBreakfasttype(0);// 早餐类型
					hotel.setWidebandtype(0);// 宽带类型
					hotel.setWeektype(0);// 周未类型
					hotel.setDeposittype(1);// 定金方式  1表示全额支付、0标识定金支付
					hotel.setNumter1(0);
					hotel.setInoteger1(0L);
				}else{
				  if("06".equals(scenic.getScenictype())){	
					Sysparv5 sysparv5 = (Sysparv5) searchDao.get(Sysparv5.class,new Sysparv5Id("BETP",hotel.getBedtype()));
					hotel.setStrbedtype(sysparv5.getPmva());
					strbedtype = sysparv5.getPmva();
				  }
				}
				if(hotel.getBreakfasttype() == 1){
					breakfasttype = "单早";
				}else if(hotel.getBreakfasttype() == 2){
					breakfasttype = "双早";
				}
				if(hotel.getWidebandtype() == 1){
					widebandtype = "免费宽带";
				}else if(hotel.getWidebandtype() == 2){
					widebandtype = "收费宽带";
				}
			}
			// 图片
			String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
					       + itickettypeid + " and p.upid = u.upid order by p.isecenicpictureid";
			List piclist = searchDao.find(sql);
			String prodaddr = "http://";
			if (piclist != null && !piclist.isEmpty()) {
				Map map = (Map) piclist.get(0);
				prodaddr = prodaddr + url + map.get("upadder").toString() + map.get("upfilename").toString();
				map.remove("upadder");map.remove("upfilename");map.remove("upname");
			} else {
				sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ product.getIscenicid() + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				piclist = searchDao.findTopNumb(sql, 1);
				if (piclist != null && !piclist.isEmpty()) {
					Map map = (Map) piclist.get(0);
					prodaddr = prodaddr + url + map.get("upadder").toString() + map.get("upfilename").toString();
					map.remove("upadder");map.remove("upfilename");map.remove("upname");
				}
			}
			String sqlprice = "select new map(p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.icrowdkindpriceid as icrowdkindpriceid,p.startdata as startdata,p.enddata as enddata) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
				              + " where p.isnet = 1 and p.byisuse = 1 and p.icrowdkindid = c.icrowdkindid and p.itickettypeid ="+itickettypeid + " order by p.icrowdkindid";
		    List priceList = searchDao.find(sqlprice);
//		    obj.put("product", product);//产品信息表
//		    obj.put("prodaddr", prodaddr);//产品图片地址
//			obj.put("priceList", priceList);//产品价格列表
//			obj.put("scenic", scenic);//产品服务商信息表
//			obj.put("hotel", hotel);//酒店产品属性信息表
			
			obj.put("itickettypeid", itickettypeid);//产品id
			obj.put("sztickettypename",product.getSztickettypename());//产品名称
			obj.put("iscenicid", scenic.getIscenicid());//服务商id
			obj.put("szscenicname", scenic.getSzscenicname());//服务商名称，即酒店名称
			obj.put("szaddress", scenic.getSzaddress());//地址
			obj.put("prodaddr", prodaddr);//产品图片地址
			obj.put("szgrade", "99".equals(scenic.getSzgrade())?"经济型":"");//
			obj.put("strbycategorytype", product.getStrbycategorytype());//房型、特产产品类型
			obj.put("strbedtype", strbedtype);//床型
			obj.put("breakfasttype",breakfasttype);//早餐类型
			obj.put("widebandtype",widebandtype);//宽带类型
			obj.put("szbookdescription", scenic.getSzbookdescription());//预订须知
			obj.put("priceList", priceList);//产品价格列表
			obj.put("szmemo", product.getSzmemo());//产品备注
			obj.put("note1", hotel.getNoted1());//特产售后服务
			obj.put("note2", hotel.getNoted2());//特产支付事项
			obj.put("note3", hotel.getNoted3());//特产关于优惠
		} catch (Exception e) {
			status = false;
			message = "查询失败!";
			e.printStackTrace();
		}
		  obj.put("message", message);//返回信息：成功、失败
		  obj.put("status", status);//返回状态：true,成功；false，失败
		  String returnstr = "";
		  try {
			  returnstr =  JSON.toJSONString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		return returnstr;
	}
	/**
	 * 领导票人管理
	 * @param usid
	 * @param lxoption
	 * @param lr
	 * @return
	 */
public 	String lxrManger(String usid, String lxoption, String lr,String seq) 
 {
		String rc_str = "";
		Map map = new HashMap();
		try {
			
			JSONObject jsonObjectlr = (JSONObject) JSONObject.parse(lr);
			

			// 姓名|证件类型|证件号码|手机号码
			// 1，增加，2，读取，3，修改，4删除；
			Lingpiaouser lpuser = new Lingpiaouser();
			Customlog customlog = new Customlog();
			if (lxoption.equals("1")) // 增加
			{
				lpuser.setMobile(jsonObjectlr.getString("mobile"));
				lpuser.setZjlb(jsonObjectlr.getString("zjlb"));
				lpuser.setUsername(jsonObjectlr.getString("username"));
				lpuser.setZjhm(jsonObjectlr.getString("zjhm"));
				lpuser.setUsid(usid);
				lingpiaouserService.addLingPiaoUser(lpuser, customlog);
				map.put("status", "1");
				map.put("message", "增加联系人成功");

				map.put("data", "");
			}
			if (lxoption.equals("2")) {

				StringBuffer sb = new StringBuffer();
				
				sb.append(" select new map(l.seq as seq, l.usid as usid, l.username as username, l.zjlb as zjlb, l.zjhm as zjhm, l.mobile as mobile, l.faxno as faxno,sysv5.pmva as zjlbstr) ");
				sb.append("  from Lingpiaouser l, Sysparv5 sysv5 ");
				sb.append(" where sysv5.id.pmky = 'ZJTP' ");
				sb.append(" and   l.usid = '"+usid+"' ");
				sb.append("   and l.zjlb = sysv5.id.pmcd ");

				   
				List list = searchDao.find(sb.toString());
				map.put("status", "1");
				map.put("message", "读取联系人成功");
				map.put("data", list);
			}
			if (lxoption.equals("3")) {
				lpuser.setMobile(jsonObjectlr.getString("mobile"));
				lpuser.setZjlb(jsonObjectlr.getString("zjlb"));
				lpuser.setUsername(jsonObjectlr.getString("username"));
				lpuser.setZjhm(jsonObjectlr.getString("zjhm"));
				lpuser.setUsid(usid);
				lpuser.setSeq(Long.valueOf(seq).longValue());
				lingpiaouserService.updateLingPiaoUser(lpuser, customlog);
				map.put("status", "1");
				map.put("message", "修改联系人成功");
				map.put("data", "");
				

			}
			if (lxoption.equals("4")) {

				lingpiaouserService.deleteLingPiaoUser(Long.valueOf(seq)
						.longValue(), customlog);
				map.put("status", "1");
				map.put("message", "删除联系人成功");
				map.put("data", "");
			}

		} catch (Exception e) {
			map = new HashMap();
			map.put("status", "0");
			map.put("data", "操作联系人出错");

		}
		return rc_str = JSON.toJSONString(map);
	}
	
	public List getTorder(String orid){
		String hsql = "from TOrder t where t.id.orid = '"+orid+"' ";
		return this.orderService.find(hsql);
	}

	/**
	 * 314 订单逻辑删除
	 * @param mobile //手机号
	 * @param psw 	//密码
	 * @param orid  //订单号
	 * @param productType 01普通订单， 02购买旅游卡订单
	 * @return
	 */
	public String logicDeleteMOrder(String mobile, String psw, String orid, String productType){
		try {
			BaseModel baseModel = this.validateUser(mobile, psw);
			if (!"0".equals(baseModel.getLogonstatus())) {
				return JSONUtil.toErrorJsonStr("用户未登录");
			}
			if("01".equals(productType)) {
				MOrder order = orderService.getMorder(orid);
				if (order == null) {
					return JSONUtil.toErrorJsonStr("订单不存在");
				}

				//验证订单状态 ： 00  11  27-1 这三种状态才支持删除
				if ("00".equals(order.getDdzt()) || "11".equals(order.getDdzt())) {//未支付，已出票
				} else if ("27".equals(order.getDdzt())) {
					MOrder mOrder = orderService.getRefundMOrder(order.getOrid());
					if (mOrder != null && !"1".equals(mOrder.getNotec())) {//未完成退订的订单
						return JSONUtil.toErrorJsonStr("该状态订单不支持删除");
					}
				} else {
					LOGGER.warn("订单状态：" + order.getDdzt());
					return JSONUtil.toErrorJsonStr("该状态订单不支持删除");
				}
				order.setIsDeleted(1L);//删除

				Orderlog log = new Orderlog();
				log.setLogid(orderService.getMaxPk("logid", "Orderlog") + 1);
				log.setOrid(orid);
				log.setStlg("0158");
				log.setNote("用户删除订单！");
				log.setBrief(log.getNote());
				log.setIemployeeid(null);
				log.setUsid(baseModel.getUsid());
				log.setLogtype(Long.parseLong("0"));
				log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
				this.orderService.updateMOrder(order, log);
			}else if("02".equals(productType)){
				TourCardOrder order = (TourCardOrder)genericService.get(TourCardOrder.class, orid);
				if(order == null){
					return JSONUtil.toErrorJsonStr("订单不存在");
				}
				if(0L == order.getPayStatus()){
					order.setPayStatus(-1L);//删除
					genericService.saveOrUpdate(order);
				}else{
					return JSONUtil.toErrorJsonStr("该状态订单不支持删除");
				}

			}else{
				LOGGER.error("productType 参数不正确 ： " + productType);
				return JSONUtil.toErrorJsonStr("参数不正确");
			}
			return JSONUtil.toSuccessJsonStr("订单删除成功");
		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
			return JSONUtil.toSuccessJsonStr("订单删除失败");
		}
	}

	/**
	 * 318 客户端版本升级接口
	 * @param versionId 版本id（第三个参数）
	 * @param clientType 客户端类型 0 安卓，1 IOS
	 * @return
	 */
	public String getLatestAppVersion(String versionId, String clientType){
		AppVersion appVersion = appVersionService.getLatestAppVersion(versionId, clientType);
		if(appVersion == null){
			AppVersion tmpVersion = new AppVersion();
			tmpVersion.setUpgradeNotes("已是最新版本");
			return JSONUtil.toSuccessJsonStr(tmpVersion);
		}else{
			appVersion.setDownloadUrl(HqytClient.APP_DOWNLOAD_URL);
			return JSONUtil.toSuccessJsonStr(appVersion);
		}
	}

}
