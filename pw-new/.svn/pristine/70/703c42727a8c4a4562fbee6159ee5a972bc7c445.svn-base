package com.ectrip.ticket.service;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.ticket.sale.service.SaleService;
import com.ectrip.ticket.service.model.PrintSet;
import com.ectrip.ticket.service.model.Stssoldtickettab;
import com.ectrip.ticket.service.util.Encrypt;
import com.ectrip.ticket.service.util.SaleUtil;
import com.ectrip.ticket.service.util.StringBcdUtil;

/**
 * 自动取票机所用的到服务端 用JAVA实现所有的逻辑
 * 
 * @author LiuJianwen
 * 
 */
public class AutoGetTicket implements IAutoGetTicket{

	public static final String COMMA = ",";//半角
	public static final String SEMICOLON = ";";//半角
	public static final String  SUPERSCRIPT = "|";//上标
	public static final String  SPLIT = "[|]";

	public static final String COMMA_ = "，";//全角
	public static final String SEMICOLON_ = "；";//全角
	public static final String MAIL = "@";
	public static final String JINGHAO = "#";
	public static final String GANTANHAO = "!";
	public static final String AND = "&";

	private SaleService saleCenterService = new SaleService();
	private ResultBean config;


	{
		config = saleCenterService.getWebContant();

	}

	/**
	 * 获取所有服务商
	 * @return 失败:0,无服务商
	 * 			成功:1,成功,00000000000000000000000000000000,2,1|武夷山风景名胜区;9|印象大红袍
	 * 			说明:成功代码,信息,校验码,服务商数量,服务商id|服务商名称;服务商id|服务商名称
	 */
	@SuppressWarnings("finally")
	public String getscenic(){
		StringBuffer returnValue = new StringBuffer();
		try {
			ResultBean bean = saleCenterService.getscenic();
			if(bean == null || bean.getRowsCount() == 0) {
				returnValue.append("0,无服务商");
			}else{
				returnValue.append("1,成功,00000000000000000000000000000000,");
				returnValue.append(bean.getRowsCount());
				returnValue.append(COMMA);
				for(int i = 0 ; i < bean.getRowsCount(); i ++){
					returnValue.append(bean.getResult(i, "iscenicid"));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(i, "szscenicname").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
					returnValue.append(SEMICOLON);
				}
			}
		} catch (Exception e) {
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}
	}



	/**
	 * 自助设备注册信息检测，
	 * 
	 * @param iscenicid  服务商编号
	 * @param macaddr    自助售票机MAC地址
	 * @return 失败:0,本机未授权
	 *          成功:1,成功,00000000000000000000000000000000,44|自助取票窗|1|1|景区南入口
	 *          说明:成功代码，成功信息，校验码,窗口id|窗口名称|取票点id|取票点代码|取票点名称
	 */

	@SuppressWarnings("finally")
	public String CTReg(long iscenicid, String macaddr){
		StringBuffer returnValue = new StringBuffer();
		try {
			ResultBean bean = saleCenterService.getEsbticketwintabByIP(iscenicid, macaddr, 1L);
			if(bean == null || bean.getRowsCount() == 0) {
				returnValue.append("0,本机未授权");
			}else{
				returnValue.append("1,成功,00000000000000000000000000000000,");
				returnValue.append(bean.getResult(0, "iticketwinid"));
				returnValue.append(SUPERSCRIPT);
				returnValue.append(bean.getResult(0, "szticketwinname").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
				returnValue.append(SUPERSCRIPT);
				returnValue.append(bean.getResult(0, "iticketstationid"));
				bean = saleCenterService.getEsbticketstationtabByID(Long.parseLong(bean.getResult(0, "iticketstationid")), 1l);
				returnValue.append(SUPERSCRIPT);
				returnValue.append(bean.getResult(0, "szstationcode"));
				returnValue.append(SUPERSCRIPT);
				returnValue.append(bean.getResult(0, "szstationname").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
			}
		} catch (Exception e) {
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}
	}





	/**
	 * 售票员登录
	 * 
	 * @param userid
	 * @param password
	 * @return 失败:0,登陆失败
	 * 			成功:1,成功,00000000000000000000000000000000,141,测试km008
	 * 			说明:成功代码,成功信息,校验码,操作员id|操作员名称
	 */

	@SuppressWarnings("finally")
	public String emplogin(Long iscenicid, String userid, String password) {
		StringBuffer returnValue = new StringBuffer();
		try {
			ResultBean bean = saleCenterService.emplogin(iscenicid, userid, password);
			if(bean == null || bean.getRowsCount() == 0) {
				returnValue.append("0,登陆失败");
			}else{
				if("true".equals(bean.getResult(0, 0))){
					bean = saleCenterService.getEmployee(userid);
					returnValue.append("1,成功,00000000000000000000000000000000,");
					returnValue.append(bean.getResult(0, "iemployeeid"));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(0, "szemployeename").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
				}else{
					returnValue.append(0);
					returnValue.append(COMMA);
					returnValue.append(bean.getResult(0, 1).replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
				}
			}
		} catch (Exception e) {
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}
	}

	/**
	 * 根据身份证和服务商编号读取服务商网上已付款的订单信息
	 * 
	 * @param idno
	 *            身份证号
	 * @param iscenicid
	 *            服务商编号
	 * @return 失败： 0,订单不存在 
				成功：1,成功,00000000000000000000000000000000,2,20120531000000006|029124EDC5E180631627E3AE44108724|订单号码:20120531000000006   已支付金额:￥456  订单日期:2012-06-01|李进|全面测试分社一,中国，湖南，株洲，石峰区|2;20120531000000006|029124EDC5E180631627E3AE44108724|订单号码:20120531000000006   已支付金额:￥456|2012-06-01|李进|全面测试分社一,中国，湖南，株洲，石峰区|2;
				说明:成功代码,成功信息,校验码,订单数,订单号|密码(当为0时不需要输入密码直接出票)|终端显示信息|导游名称|单位名称|客源地(其中的逗号是全角)|业务id;订单号|密码|终端显示信息|导游名称|单位名称|客源地(其中的逗号是全角)|业务id
	 */

	@SuppressWarnings("finally")
	public String CTCXService(String idno, Long iscenicid,String url) {
		StringBuffer returnValue = new StringBuffer();
		try {
			ResultBean bean = saleCenterService.getT_order(idno, iscenicid,url);
			if(bean == null || bean.getRowsCount() == 0) {
				returnValue.append("0,订单不存在!");
			}else{
				returnValue.append("1,成功,00000000000000000000000000000000,");
				returnValue.append(bean.getRowsCount());
				returnValue.append(COMMA);
				for(int i = 0; i < bean.getRowsCount(); i ++){
					returnValue.append(bean.getResult(i, "orid"));
					returnValue.append(SUPERSCRIPT);
					if(!"0".equals(config.getResult(0, "passwordcontrol"))){//如果不为0,则要求输入密码
						String dyusid = bean.getResult(i, "dyusid");
						if("".equals(dyusid) ||"null".equals(dyusid) || dyusid == null)
							returnValue.append(Encrypt.addPwd(bean.getResult(i,"notea")));
						else{
							returnValue.append(bean.getResult(i,"password"));
						}
					}else{
						returnValue.append(0);
					}
					returnValue.append(SUPERSCRIPT);
					returnValue.append("订单号码:");
					returnValue.append(bean.getResult(i, "orid"));
					returnValue.append("    已支付金额:￥");
					returnValue.append(bean.getResult(i, "zfmont").replaceAll(COMMA, COMMA_));
					returnValue.append("    订单有效期:");
					returnValue.append(bean.getResult(i, "dtenddate"));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(i, "ornm").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(i, "corpname").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(i, "szregionalname").replaceAll(COMMA, COMMA_).replaceAll(SEMICOLON, SEMICOLON_));
					returnValue.append(SUPERSCRIPT);
					returnValue.append(bean.getResult(i, "ibusinessid"));
					returnValue.append(SEMICOLON);
				}
			}
		} catch (Exception e) {
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}
	}

	/**
	 * 当前订单出票,条码类型暂未实现
	 * @param orid 订单号
	 * @param iscenicid 服务商id
	 * @param szstationmsg 自助售票机信息，格式:取票窗id,取票窗名称,取票点id,取票点代码,取票点名称
	 * @param szemployeemsg 售票员信息，格式:售票员id,售票员名称
	 * @param bookmsg 订单信息，格式:导游名称,单位名称,客源地,业务id
	 * @return  失败: "0,服务异常请联系管理员"  
	 *           成功: 1,成功,00000000000000000000000000000000,3,1|1W1A160378WW0C|15423433|团队景车票(全票)|流水号:15376992  (1/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;1,1W1A160378WX75|团队景车票(全票)|流水号:导游名称:李进5376993  (2/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;1|1W1A160378WW0C|15423433|团队景车票(全票)|流水号:15376992  (1/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;1,1W1A160378WX75|团队景车票(全票)|流水号:导游名称:李进5376993  (2/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|
	 */

	@SuppressWarnings("finally")
	public String CTQPService(String orid,Long iscenicid, String szstationmsg,
			String szemployeemsg, String bookmsg) {
		StringBuffer returnValue = new StringBuffer();
		String[] printInfoArray;
		String[] arr;
		try {
			printInfoArray = szstationmsg.split(SPLIT);//自助售票机信息
			arr = szemployeemsg.split(SPLIT);
			ResultBean bean = saleCenterService.savetorder(orid, iscenicid, Long.parseLong(printInfoArray[0]), Long.parseLong(arr[0]), "4.2","");
			if(bean == null || bean.getRowsCount() == 0){
				returnValue.append("0,保存失败!");
			}else{
				if("true".equals(bean.getResult(0, 0))){
					try {
						List<Stssoldtickettab> list = SaleUtil.convertResultBeanToList(
								saleCenterService.soldticketlist(Long.parseLong(bean.getResult(0, 1)), Long.parseLong(printInfoArray[2])), Stssoldtickettab.class);
						if(list.size() == 0){
							returnValue.append("0,此订单已保存成功,但未获取到打印数据");
						}else{
							arr = bookmsg.split(SPLIT);
							List<PrintSet> pslist = SaleUtil.convertResultBeanToList(saleCenterService.getprintmanage(iscenicid, Long.parseLong(arr[3])), PrintSet.class);
							int i = 0;
							returnValue.append("1,成功,00000000000000000000000000000000,");
							returnValue.append(list.size());
							returnValue.append(COMMA);
							for (Stssoldtickettab s : list) {
								if("01".equals(s.getBymaketicketway())) continue;
								returnValue.append("0|");//条码类型
								returnValue.append(s.getSzticketprintno());
								returnValue.append(SUPERSCRIPT);
								returnValue.append(s.getIserialnum());
								returnValue.append(SUPERSCRIPT);
								s.setOrnm(arr[0]);
								s.setCorpname(arr[1]);
								s.setSzregionalname(arr[2]);
								String[] printInfoArrays = getPrintInfos(pslist,s,"  ("+(i+1)+"/"+list.size()+")",printInfoArray[3]);
								for (String cell : printInfoArrays) {
									returnValue.append(cell);
									returnValue.append(SUPERSCRIPT);
								}
								returnValue.append(SEMICOLON);
								i ++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnValue.append("0,此订单已保存成功,但获取打印数据时出现异常,无法再次出票，请及时联系管理人员。对此造成的不便，我们深表歉意。");
					}
				}else{
					returnValue.append("0,");
					returnValue.append(bean.getResult(0, 1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}
	}


	/**
	 * 订单撤消
	 * 
	 * @param orid
	 *            订单号
	 * @return
	 */
	public String CTQPCXService(String orid) {
		return StringBcdUtil.bytesToHexString("1,成功,".getBytes());
	}



	/**
	 * 获取订单数据
	 * @param orid 订单号
	 * @param iscenicid 服务商id
	 * @return 成功: 成功代码,成功信息,校验码,订单数据条数,票数量|票名|人群种类|单价|生效日期|结束日期|订单信息（cancelt_order 的参数 message）;
	 * 													    票数量|票名|人群种类|单价|生效日期|结束日期|订单信息（cancelt_order 的参数 message）
	 * 		      如:1,成功,00000000000000000000000000000000,2,2|团队景车票|半票|115|2012-06-15|2012-06-17|message;
	 * 														   6|团队景车票|全票|230|2012-06-15|2012-06-17|message
	 *				message格式:17,84,235,4,2012-06-19,2012-06-21#4,4,2012-06-19 00:00:00,2012-06-21 23:59:59,0&12,4,2012-06-19 00:00:00,2012-06-21 23:59:59,0&
	                           @16,82,335,3,2012-06-19,2012-06-21#1,3,2012-06-19 16:10:00,2012-06-19 17:40:00,8&4,3,2012-06-19 00:00:00,2012-06-21 23:59:59,0&12,3,2012-06-19 00:00:00,2012-06-21 23:59:59,0&@6,141,10,1,2012-06-19,2012-06-20#6,1,2012-06-19 00:00:00,2012-06-20 23:59:59,0&@
	 * 			失败: 0,此订单无数据
	 */
	@SuppressWarnings("finally")
	public String getT_orderlist(String orid, Long iscenicid,String url){

		StringBuffer returnValue = new StringBuffer();
		try {
			
			ResultBean bean = saleCenterService.getT_orderlist(orid, iscenicid,url);
			if(bean == null || bean.getRowsCount() == 0) {
				returnValue.append("0,此订单无数据!");
			}else{
				returnValue.append("1,成功,00000000000000000000000000000000,");
				returnValue.append(bean.getRowsCount()+1);
				returnValue.append(COMMA);
				//名称  1 -15， 
				//数量 16 - 25
				//单价 25 - 35
				//日期 36 - 50
				//竹筏趟次时间  51
				// 名称 数量之间 16 个空格， 数量和单价之间 5 个空格，单价日 期 8 个空格，日 期和竹筏趟次时间 12 个空格。
				returnValue.append("名称　　　　　　　　数量      单价        日期            竹筏趟次时间");
				returnValue.append(SEMICOLON);
				String[] args;
				String[] arg;
				
				for(int i = 0; i < bean.getRowsCount(); i ++){
					//returnValue.append("类型:");
					//returnValue.append(bean.getResult(i, "szcrowdkindname"));
					//returnValue.append(COMMA);
				//	returnValue.append("名称:");
					//returnValue.append(bean.getResult(i, "sztickettypename"));
					//returnValue.append("             ");
					String sztickettypename=bean.getResult(i, "sztickettypename");
					String kongge="";
				
					if(sztickettypename.length()<10){
						for(int j=sztickettypename.length();j<10;j++){
							kongge=kongge+"　";
						}
					}
					returnValue.append(sztickettypename);
					returnValue.append(kongge);
					
					String numb=bean.getResult(i, "numb");
					if(numb.length()<11){
						for(int j=numb.length();j<11;j++){
							numb=numb+" ";
						}
					}
					returnValue.append(numb);
					//returnValue.append(bean.getResult(i, "numb"));
				//	returnValue.append("        ");
					
					//returnValue.append(SUPERSCRIPT);
				//	returnValue.append("价格:");
				//	returnValue.append(bean.getResult(i, "pric"));
					String pric=bean.getResult(i, "pric");
					if(pric.length()<12){
						for(int j=pric.length();j<12;j++){
							pric=pric+" ";
						}
					}
					returnValue.append(pric);
				//	returnValue.append("     ");
				//	returnValue.append(SUPERSCRIPT);
				//	returnValue.append("有效期:");
					//returnValue.append(bean.getResult(i, "dtstartdate"));
					String dtstartdate=bean.getResult(i, "dtstartdate");
					returnValue.append(dtstartdate+"      ");
					//returnValue.append(SUPERSCRIPT);
					//returnValue.append(bean.getResult(i, "dtenddate"));
					//returnValue.append(SUPERSCRIPT);
					//returnValue.append(bean.getResult(i, "itickettypeid"));
					//returnValue.append(COMMA_);
					//returnValue.append(bean.getResult(i, "icrowdkindpriceid"));
					//returnValue.append(COMMA_);
					//returnValue.append(bean.getResult(i, "pric"));
					//returnValue.append(COMMA_);
					//returnValue.append(SEMICOLON_);//修改后的数量用全角；替换
					//returnValue.append(COMMA_);
					//returnValue.append("~");
					//returnValue.append(bean.getResult(i, "dtstartdate").length() > 0 ? bean.getResult(i, "dtstartdate").substring(5):bean.getResult(i, "dtstartdate"));
					//returnValue.append(COMMA_);
					//returnValue.append(bean.getResult(i, "dtenddate"));
					//returnValue.append(JINGHAO);
					args =  bean.getResult(i, "zzdail").split(GANTANHAO);//分解子票
					for (String string : args) {
						arg = string.split(AND);
						//					    1&竹筏票&8&第八趟&2012-06-19 16:10:00&2012-06-19 17:40:00&1&正常!
						//						4&三日游景点票&0&&2012-06-19 00:00:00&2012-06-21 23:59:59&1&正常!
						//						12&三日游车票&0&&2012-06-19 00:00:00&2012-06-21 23:59:59&1&正常						arg = string.split("&");
						//returnValue.append(arg[0]);
						//returnValue.append(COMMA_);
						//returnValue.append(SEMICOLON_);//数量用全角分号
						//returnValue.append(COMMA_);
						if (! arg[2].equals("0" ))
						{
							    //returnValue.append(COMMA);
								//returnValue.append(arg[1]);  //竹伐数量
								//returnValue.append(COMMA);
							 	//returnValue.append(arg[0]);  //票名
							    //returnValue.append("   ");
								returnValue.append(arg[3]);  //趟次
								returnValue.append("   ");
								returnValue.append(arg[4]);   //时间
						//returnValue.append(COMMA_);
						//returnValue.append(arg[5]);
						//returnValue.append(COMMA_);
						//returnValue.append(arg[2]);
						//returnValue.append(AND);
						}
					}
					returnValue.append(SEMICOLON);
				}
			}
		} catch (Exception e) {
			returnValue = new StringBuffer();
			returnValue.append("0,服务器异常");
		}finally{
			return StringBcdUtil.bytesToHexString(returnValue.toString().getBytes());
		}


	};



	/**
	 * 
	 * @param orid 订单号
	 * @param iscenicid 服务商id
	 * @param szstationmsg 自助售票机信息，格式:取票窗id|取票窗名称|取票点id|取票点代码|取票点名称
	 * @param szemployeemsg 售票员信息，格式:售票员id|售票员名称
	 * @param bookmsg 订单信息
	 * @param message 修改后新的数量信息，格式为: 新数量|message(getT_orderlist返回的参数); 新数量|message(getT_orderlist返回的参数)
	 *                                         如:1|message;5|message
	 * @param isqt 暂时填0
	 * @param forceemid 暂时填0
	 * @return
	 */
	public String cancelt_order(
			String orid,
			Long iscenicid,
			String szstationmsg,
			String szemployeemsg,
			String bookmsg,
			String message,
			Long isqt,
			Long forceemid,
			String url){
		StringBuffer returnValue = new StringBuffer();
		String[] arr;
		double mont = 0;
		try {
			String[] messages = message.split(SEMICOLON);
			for (String string : messages) {
				arr = string.split(SPLIT);
				arr[1] = arr[1].replaceAll(COMMA_, COMMA).replaceAll(SEMICOLON_, arr[0]);
				mont += Double.parseDouble(arr[1].split(COMMA)[2])*Integer.parseInt(arr[0]);
				returnValue.append(arr[1]);
				returnValue.append(MAIL);
			}
			ResultBean bean = saleCenterService.cancelt_order(
					orid, iscenicid, mont, Long.parseLong(szstationmsg.split(SPLIT)[0]), 
					Long.parseLong(szemployeemsg.split(SPLIT)[0]), returnValue.toString(), isqt, forceemid,url);
			if(bean == null || bean.getRowsCount() == 0){
				return StringBcdUtil.bytesToHexString("0,服务器异常".getBytes());
			}else{
				if("true".equals(bean.getResult(0, 0))){
					return this.CTQPService(orid, iscenicid, szstationmsg, szemployeemsg, bookmsg);
				}else{
					return StringBcdUtil.bytesToHexString(("0," + bean.getResult(0, 1)).getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return StringBcdUtil.bytesToHexString("0,服务器出现异常".getBytes());
		}

	};
	
	/**
	 * 获取服务器时间
	 * @return 2012-09-09 19:09:09
	 */
	public String getServerTimes() {
		return saleCenterService.getDayTimes();
	}
	
	private String[] getPrintInfos(List<PrintSet> pslist,Stssoldtickettab s,String numberFractionCount,String szstationcode){
		String zhuFaShiJian = null;
		String zhuFaTangCi = null;
		String childs = "";
		PrintSet ps;
		PrintSet ps1;
		String label = "";
		String temp_ = "";
		String tempInfo;
		List<String> printInfos = new ArrayList<String>();//打印信息数组
		try {
			String[] arr = s.getZdail().split("@");
			for (int i = 0; i < arr.length; i ++) {
				String[] df =arr[i].split("&");
				if(!"0".equals(df[1])){
					zhuFaShiJian = df[3];
					zhuFaTangCi = df[2];
				}
				childs += (df[0] + (i==arr.length-1?"":COMMA));
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		printInfos.clear();//打印信息数组
		int size = pslist.size();
		for(int j = 0 ; j < size ; j ++){
			try {
				ps = pslist.get(j);
				ps1 = pslist.get(j==size-1?j:j+1);
				label = ps.getSzprintno()+":";
				if(ps != ps1 && ps.getOrdernum().equalsIgnoreCase(ps1.getOrdernum())){
					temp_ = getPrintRow(s, ps1.getSzprintno()+":", Integer.parseInt(ps1.getPrintno()),
							numberFractionCount,zhuFaShiJian,zhuFaTangCi ,szstationcode,childs);
					printInfos.add(
							getPrintRow(s, label, Integer.parseInt(ps.getPrintno()), 
									numberFractionCount,zhuFaShiJian,zhuFaTangCi ,szstationcode,childs)
									+ (temp_==null?"":(" "+temp_)));
					j += 1;
				}else{
					tempInfo = getPrintRow(s, label, Integer.parseInt(ps.getPrintno()),
							numberFractionCount,zhuFaShiJian,zhuFaTangCi ,szstationcode,childs);
					if(tempInfo == null || "".equals(tempInfo)) continue;
					printInfos.add(tempInfo);
				}

			} catch (Exception e) {
			}
		}
		return printInfos.toArray(new String[]{});
	}
	private String getPrintRow(Stssoldtickettab s,
			String label,
			int printno,
			String numberFractionCount,
			String zhuFaShiJian,
			String zhuFaTangCi ,
			String szstationcode,
			String childs
			) throws ParseException{
		String row = null;
		switch (printno) {
		case 1:
			row = (("".equals(s.getSztickettypename()) || "null".equals(s.getSztickettypename()) )?
					null : s.getSztickettypename());//票种名
			break;
		case 2:
			row = (("".equals(s.getIplayerperticket()) || "null".equals(s.getIplayerperticket()) )? null : label+s.getIplayerperticket()+"人次/张");
			break;
		case 3:
			row = (("".equals(s.getMactualsaleprice()) || "null".equals(s.getMactualsaleprice()) )? null :label+"￥"+s.getMactualsaleprice());//价格
			break;
		case 4:
			row = (("".equals(s.getIserialnum()) || "null".equals(s.getIserialnum()) )? null :label+szstationcode+s.getIserialnum() +numberFractionCount) ;//流水号
			break;
		case 5:
			row = (label+SaleUtil.format1.format(SaleUtil.format3.parse(s.getDtstartdate()))
					+"-"+SaleUtil.format1.format(SaleUtil.format3.parse(s.getDtenddate())));//有效期
			break;
		case 6:
			row =(((zhuFaShiJian == null)?null: label + zhuFaShiJian));//竹筏时间
			break;
		case 7:
			row = ((zhuFaTangCi == null)?null:label + zhuFaTangCi);//竹筏趟次
			break;
		case 8:
			//						printInfos[j] =isisAgain? label +s.getOrnm(): ("".equals(daoYou.getSzemployeename()) || "null".equals(daoYou.getSzemployeename()) )? null : label + daoYou.getSzemployeename();//导游名称
			row = (("".equals(s.getOrnm()) || "null".equals(s.getOrnm()) )? null : label + s.getOrnm());//导游名称
			break;
		case 9:
			//						printInfos[j] =isisAgain? label +s.getCorpname():("".equals(daoYou.getCompany()) || "null".equals(daoYou.getCompany()) )? null : label + daoYou.getCompany();//旅行社名称
			row = (("".equals(s.getCorpname()) || "null".equals(s.getCorpname()) )? null : label + s.getCorpname());
			break;
		case 10:
			//						printInfos[j] =isisAgain? label +s.getSzregionalname():(szregionalname==null)?null: label + szregionalname;//客源地
			row = (("".equals(s.getSzregionalname()) || "null".equals(s.getSzregionalname()) )? null : label + s.getSzregionalname());
			break;
		case 11:
			row =(("".equals(s.getSzsalesvoucherno()) || "null".equals(s.getSzsalesvoucherno()) )?null: label + s.getSzsalesvoucherno());//订单号
			break;
		case 12:
			row =((("".equals(s.getSzcrowdkindname()) || "null".equals(s.getSzcrowdkindname()) )?null:  label + s.getSzcrowdkindname()));//人群种类
			break;
		case 13:
			row =(("".equals(childs) )?null:  label + childs);//有效票类
			break;
		default:
			break;
		}
		return row;
	}

	public static void main(String[] args) {
	    int money=1000000000;
	    System.out.println(money);
	}







}
