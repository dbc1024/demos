/*package com.ectrip.ticket.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.session.Session;
import org.apache.axis.transport.http.HTTPConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.util.EctripMd5;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.service.dao.ApiDao;
import com.ectrip.ticket.service.dao.idao.IMbMsGDAO;
import com.ectrip.ticket.service.service.ApiDaoService;

public class ApiService {
	public static String MD5STR = "029124EDC5E180631627E3AE44108724";
	public String macAddrss = "";
	private static String WEBUSER = "webserviceuser";
	private static String WEBPASSWORD = "webservicepassword";
	private static ApiDao apidao;
	
	@Autowired
	private EcService ecService;

	protected static ApiDao getApidao() {
		return apidao;
	}

	protected static void setApidao(ApiDao apidao) {
		ApiService.apidao = apidao;
	}

	public String test() {

		return "通讯测试成功";

	}

	*//**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 *//*
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	*//**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 *//*
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String fromccda = "2012-08-17 12:11:11";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date day1 = sdf.parse(fromccda);
			Date day2 = addDate(day1, -30);
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String result = df.format(day2);
			System.out.println(result);
		} catch (Exception e) {

		}

	}

	*//**
	 * 读取职责
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public List<Espdutytab> getEspdutytab() throws Exception

	{
		ApiDaoService apiDaoService = (ApiDaoService) SpringUtil.getBean("apiDaoService");
		return apiDaoService.getEspdutytab();
	}

	public String downTicketStation() {
		return "";

	}

	*//**
	 * 生成SESSONMD5
	 * 
	 * @return
	 * @throws Exception
	 *//*
	private String getSssionMD5STR(String macAddrss) throws Exception {
		if (macAddrss == null || macAddrss.equals("")) {
			throw new Exception("MAC 为空，请传入MAC地址");
		}
		StringBuffer keyBuf = new StringBuffer();

		for (int i = 0; i < 10; i++) {
			int ri = (int) (Math.random() * 10);
			keyBuf.append(ri);

		}
		String srcstr = macAddrss + keyBuf.toString() + "ectrip";
		System.out.println("srcstr=" + srcstr);
		EctripMd5 md5 = new EctripMd5(srcstr);
		md5.calc();

		return md5.toString();
	}

	*//**
	 * 取客户端的IP地址
	 * 
	 * @return
	 *//*
	private String getClientIp() {

		MessageContext mc = MessageContext.getCurrentContext();
		HttpServletRequest request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		// System.out.println("remote  ip:  " + request.getRemoteAddr());
		return request.getRemoteAddr();
	}

	*//**
	 * 取客户端保存在SESSION中的KEY
	 * 
	 * @return
	 * @throws Exception
	 *//*
	private String getMD5STR() throws Exception {

		MessageContext mc = MessageContext.getCurrentContext();
		Session session = mc.getSession();

		String md5 = (String) session.get("MD5STR");
		// System.out.println("Sessionmd5="+md5);
		if (md5 == null || md5.equals("")) {
			throw new Exception("Session 为空，请重建Session    ");
		}
		return md5;

	}

	*//**
	 * 读取服务务
	 * 
	 * @param validcode
	 * @return
	 * @throws Exception
	 *//*
	public String getProvider(String validcode) throws Exception {

		String ls_value = "";
		if (validcode.equals(getMD5STR()) == false)
			throw new Exception("MD5 验证码错误。");

		return "验证成功";
	}

	*//**
	 * 测试ResultBean
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public ResultBean getResultBean() throws Exception {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "a", "b" });
		rs.addRow(new String[] { "123", "1243" });
		return rs;
	}

	*//**
	 * 
	 * Describe:根据订单验证日控制
	 * 
	 * @auth:yangguang
	 * @param orid
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             return:List Date:2011-12-1
	 *//*
	public boolean validationDayControl(String orid,String usid) throws IllegalAccessException, InvocationTargetException {
		//IPayOrderService payservice = (IPayOrderService) SpringUtil.getBean("payorderService");
		List list = ecService.volidateTicketDayControl(orid);
		if (list != null && list.size() > 0) {
		    System.out.println("-->day false");
			return false;
		} else {
		    System.out.println("-->day true");
			return true;
		}
	}

	*//**
	 * 
	 * Describe:根据订单验证趟次控制
	 * 
	 * @auth:yangguang
	 * @param orid
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             return:List Date:2011-12-1
	 *//*
	public boolean validationTripControl(String orid) throws IllegalAccessException, InvocationTargetException {
		try {
			//IPayOrderService payservice = (IPayOrderService) SpringUtil.getBean("payorderService");
			List list = ecService.volidateTicketTripControl(orid);
			if (list != null && list.size() > 0) {
			    System.out.println("-->trip false");
				return false;
			} else {
			    System.out.println("-->trip true");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=============>>验证竹筏趟次失败" + e.getMessage());
			return false;
		}
	}

	public String checkOrderIsZhifu(String orid) throws Exception {
		//IPayOrderService payservice = (IPayOrderService) SpringUtil.getBean("payorderService");
		List list = ecService.volidateTicketDayControl(orid);
		if (list != null && list.size() > 0) {
			return "-1,您预订的竹筏没有坐位,请重新申请!";
		} else {
			list = ecService.volidateTicketDayControl(orid);
			if (list != null && list.size() > 0) {
				return "-1,您预订的竹筏没有坐位,请重新申请!";
			}
		}
		
		
//		IOrderService orderservice = (IOrderService) SpringUtil.getBean("orderService");
		
		
		
		
		Map map = ecService.validTorderInfo(orid);
		if (map != null && map.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("1,");
			for (int i = 0; i < map.size(); i++) {
				sb.append(map.get(i).toString());
				if (i != map.size() - 1) {
					sb.append(";");
				}
			}
			return sb.toString();
		}
		
//		MOrder morder   =  (MOrder)orderservice.get(MOrder.class, orid);
//		//判断订单是否存在；不存在就返回错误；
//		if ( morder ==null)
//		 {
//			return "-1,你回调商务网时订音查询不到!";
//		 }
//		IStocksWareService stockswareService = (IStocksWareService) SpringUtil.getBean("stockswareService");
//		//产品库存判断  如果库存不足 支付不了
//		List<TOrderlist> torderList=orderservice.getTOrderlists(orid);
//		List<Object []> jectlist=new ArrayList<Object[]>();
//		if(torderList!=null&&torderList.size()>0){
//			for(int x=0;x<torderList.size();x++){
//				TOrderlist torder=torderList.get(x);
//
//				Object [] obj=new Object []{torder.getId().getIscenicid(),torder.getItickettypeid(),torder.getNumb(),torder.getDtstartdate(),torder.getDtenddate()};
//				jectlist.add(obj);
//			}
//		}
//
//		//判断用户库存
//		Custom cstm=(Custom)orderservice.get(Custom.class, morder.getUsid());
//		//下订单用户是操作员,需要获取分社信息
//		String stockusid=cstm.getUsid();
//		if(cstm.getLgtp().equals("02")&&cstm.getTtlb().equals("01")&&cstm.getUstp().equals("02")&&cstm.getUsqx().equals("01110000000000000000")){
//			stockusid=cstm.getSusid();
//		}
//
//		int yhkc=stockswareService.checkTicketwareCustom(jectlist, stockusid);
//		if (yhkc == 0) {
//			return "-1,您购买的票产品库存数量不足!";
//		}else{
//			int kcpd= stockswareService.checkTicketware(jectlist);
//			if(kcpd==0){
//				return "-1,您购买的票产品库存数量不足!";
//			}
//		}
		
		
		//判断是否允许现场支付
		List torderlist=orderservice.getTOrderList(orid);
		if(torderlist!=null&&torderlist.size()>0){
			for(int i=0;i<torderlist.size();i++){
				Map hotelmap=(Map) torderlist.get(i);
				String iscenicid=hotelmap.get("iscenicid").toString();
				Hotelprovider hotelprovide = (Hotelprovider)orderservice.get(Hotelprovider.class, Long.parseLong(iscenicid));
				if (hotelprovide!=null) {
					if(hotelprovide.getInoteger7()!=null){
						if(hotelprovide.getInoteger7()==0){
							return "-1,您购买的产品不能进行现场支付,请重新选择支付方式!";
						}
					}else{
						return "-1,您购买的产品不能进行现场支付,请重新选择支付方式!";
					}
				}else{
					return "-1,您购买的产品不能进行现场支付,请重新选择支付方式!";
				}
			}
		}
		
		return "1,ok";
	}

	*//**
	 * 日期
	 * 
	 * @param fromccda
	 *            开始时间
	 * @param toccda
	 *            结束时间
	 * @param md5str
	 * @return
	 * @throws Exception
	 *//*
	public ResultBean getAllWaitSendInfo(String fromccda, String toccda, String md5str) throws Exception {
		{
			com.ectrip.base.util.Encrypt en = new com.ectrip.base.util.Encrypt();
			if (en.MD5Encrypt(fromccda + "&" + toccda).equals(md5str) == false) {
				// throw new Exception("MD5 验证不通过");
			}

			// 最多重试三次
			IMbMsGDAO imbmsgdao = (IMbMsGDAO) SpringUtil.getBean("mbMsGDAO");

			com.ectrip.base.util.ListToResultBean ltrb = new com.ectrip.base.util.ListToResultBean();

			ResultBean rb = null;
			List list = imbmsgdao.getAllWaitSendInfo(fromccda, toccda, md5str);
			if (list.size() > 0) {
				rb = ltrb.ToResultBean(list);
			} else {
				rb = new ResultBean();
				rb.addRow(new String[] { "0", "1", "error" });
			}
			return rb;
		}
	}

	*//**
	 * 发送结果保存
	 * 
	 * @param seq
	 * @param isok
	 * @return
	 * @throws Exception
	 *//*
	public int sendInfoOk(Long seq, int isok) throws Exception {
		int li_rc = 0;
		try {
			IMbMsGDAO imbmsgdao = (IMbMsGDAO) SpringUtil.getBean("mbMsGDAO");
			if (isok == 1)
				imbmsgdao.UpdateSendOK(seq);
			else
				imbmsgdao.UpdateSendError(seq);
		} catch (Exception e) {
			li_rc = -1;
		}
		return li_rc;
	}
	
	*//**
	 * 对外服务主方法
	 * @param method
	 * @param requestParam
	 * @return
	 * @throws Exception
	 *//*
	
	*//**
	 * 
	 * @param method
	 * @param requestParam
	 * @return
	 * @throws Exception
	 *//*
	public String getMessInfo(String method, String requestParam,String url) throws Exception {
		AutoWebGetTicket  awgt = new AutoWebGetTicket();
		return awgt.getMessInfo(method, requestParam,url);
	}
	
	*//**
	 * Pay 结算中心;
	 * 结算中心回调些方法，防止因为用户关闭浏览器造成预付款扣款，而订单状态没有修改成功；
	 * @param xmlstring 结算中心传入XML，这个串需要经过 URL 解码
	 * @return
	 *//*
	
//	public int payTwoCheck(String xmldata,String md5) throws Exception 
//	{
//	   //结算中心回调些方法，防止因为用户关闭浏览器造成预付款扣款，而订单状态没有修改成功；
//		com.ectrip.sys.service.service.EndOrderPayService  eps = new com.ectrip.sys.service.service.EndOrderPayService();
//	    return eps.PayOrderService(java.net.URLDecoder.decode(xmldata), md5);
//	}
}
*/