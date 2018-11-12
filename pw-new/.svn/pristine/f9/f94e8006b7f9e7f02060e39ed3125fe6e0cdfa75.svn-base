package com.ectrip.ticket.service;

import java.rmi.RemoteException;

/**
 * 自动取票机所用的到服务端 用JAVA实现所有的逻辑
 * 李进于2012-07-20 日 晚上修改了 getT_orderlist 方法 ，现在用于订单详情接口显示不做为修改订单
 * 接口处理，修改时再新加方法。
 *
 * @author LiuJianwen
 *
 */
public interface IAutoGetTicket {



	/**
	 * 获取所有服务商
	 * @return 失败:0,无服务商
	 * 			成功:1,成功,00000000000000000000000000000000,2,1|武夷山风景名胜区;9|印象大红袍
	 * 			说明:成功代码,信息,校验码,服务商数量,服务商id|服务商名称;服务商id|服务商名称
	 */
	public String getscenic();

	/**
	 * 订单撤消
	 *
	 * @param orid
	 *            订单号
	 * @return
	 */
	public String CTQPCXService(String orid);



	/**
	 * 自助设备注册信息检测，
	 *
	 * @param iscenicid  服务商编号
	 * @param macaddr    自助售票机MAC地址
	 * @return 失败:0,本机未授权
	 *          成功:1,成功,00000000000000000000000000000000,44|自助取票窗|1|1|景区南入口
	 *          说明:成功代码，成功信息，校验码,窗口id|窗口名称|取票点id|取票点代码|取票点名称
	 */

	public String CTReg(long iscenicid, String macaddr);


	/**
	 * 获取服务器时间
	 * @return 2012-09-09 19:09:09
	 */
	public String getServerTimes();



	/**
	 * 售票员登录
	 *
	 * @param userid
	 * @param password
	 * @return 失败:0,登陆失败
	 * 			成功:1,成功,00000000000000000000000000000000,141,测试km008
	 * 			说明:成功代码,成功信息,校验码,操作员id|操作员名称
	 */

	public String emplogin(Long iscenicid, String userid, String password) ;

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

	public String CTCXService(String idno, Long iscenicid,String url);

	/**
	 * 当前订单出票
	 * @param orid 订单号
	 * @param iscenicid 服务商id
	 * @param szstationmsg 自助售票机信息，格式:取票窗id|取票窗名称|取票点id|取票点代码|取票点名称
	 * @param szemployeemsg 售票员信息，格式:售票员id|售票员名称
	 * @param bookmsg 订单信息，格式:导游名称|单位名称|客源地|业务id|订单数据信息
	 * @return  失败: "0,服务异常请联系管理员"
	 *           成功: 1,成功,00000000000000000000000000000000,3,1|1W1A160378WW0C|团队景车票(全票)|流水号:15376992  (1/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;
	 *                                                           1,1W1A160378WX75|团队景车票(全票)|流水号:15376993  (2/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;
	 *                                                           1|1W1A160378WW0C|团队景车票(全票)|流水号:15376994  (1/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|;
	 *                                                           1,1W1A160378WX75|团队景车票(全票)|流水号:15376995  (2/2)|有效期:2012.06.02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖，宝坻县|有效票类:三日游景点票,三日游车票|
	 */

	public String CTQPService(String orid,Long iscenicid, String szstationmsg,
							  String szemployeemsg, String bookmsg);





	/**
	 * 获取订单明细数据 ，返回数据格式者地了调整。
	 * @param orid 订单号
	 * @param iscenicid 服务商id
	 * @return 成功: 成功代码,成功信息,校验码
	 * cano=1,成功,00000000000000000000000000000000,3,
	 * 名称              数量      单价        日期            竹筏趟次时间;
	 * 团队景车票         2        235     2012-07-20~07-20;
	 * 团队景车票         1        115     2012-07-20~07-20;
	 * 团队三联票         3        335     2012-07-20~07-20   第一趟   2012-07-21 06:40:00;
	 *
	 *											   6|团队景车票|全票|230|2012-06-15|2012-06-17|message
	 * 			失败: 0,此订单无数据
	 */
	public String getT_orderlist(String orid, Long iscenicid,String url);

	/**
	 * 根据订单号查
	 * @param orid
	 * @param iscenicid
	 * @return
	 */



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
			String url);


}
