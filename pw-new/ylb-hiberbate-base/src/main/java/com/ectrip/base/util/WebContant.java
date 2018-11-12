package com.ectrip.base.util;

import java.util.Map;

/**
 * 在ectirp.xml 文件中增加了值后,不用在增曾加变量. 
 * 直接通过: GetKeyValue(String) 进行访问. 不要轻意改运这个类. 
 * 现在改为用监听进行别载 没有采用SERVLET进行别载如果没有值请检查WEB.XML
 * 文件中是否有: <listener> <listener-class> 
 * com.ectrip.struts2.common.util.ContextListener 
 * </listener-class> </listener>
 * 
 * @author LIJIN
 * 
 * 李进于 2013-01-10 日进行修改，增加了LIC的检查，为了
 * 保持版本统一 增加了
 * 
 */
public class WebContant {
	public static String domain = "http://192.168.0.105:8081"; // 域名
	public static String DOMAIN = "http://192.168.0.105:8081"; // 域名
	public static String DatabaseType = "oracle"; // //数据库类型；db2--IBM db2;sql-- MS sql server 2000; oracle--oracle
	public static String EMAIL = ""; // 发送邮件的EMAIL邮箱
	public static String SMTPEMAIL = ""; // SMTP服务器地址
	public static String USERNAME = ""; // 邮件用户名
	public static String PASSWORD = ""; // 邮件密码
	public static String DOMAINAME = ""; // 网站名称
	public static String AREA = "";// 系统默认区域
	public static String PRVCODE = ""; // 系统默认省
	
	public static String JNDI = "";

	public static String PASSWORDORDER = ""; // 出纳更改订单状态的密码
	public static String VIEWORDER = ""; // 后台查询订单详情的密码
	public static String HOTELLOGIN = ""; // 订房中心登陆校验码
	public static String BACKLOGIN = ""; // 后台用户登陆校验码
	public static String CHUPIAOLOGIN = ""; // 出票系统用户登陆校验码
	public static String YUFUKUANCODE = ""; // 手工扣除预付款密码
	public static String MANAGECODE = ""; // 管理XML的密码
	public static String HOUTAITITLE = ""; // 网站后台标题
	public static String NOTEISAUDIT = ""; // 留言是否需要审核后再发布
	public static String CPSYSPASSWORD = ""; // 出票程序系统设置密码
	public static String QIANGTUIPASSWORD = ""; // 后台强行退订用户订单密码
	public static String CORPNAME = ""; // 电子商务公司名称

	public static String REALPATH = ""; // 应用相对路径
	public static String FILEREALPATH = ""; // 配置文件实际的路径
	public static String CERPATH = ""; // 证书文件实际的路径
	public static String BOARDINFONUM = ""; // 允许目的地自定义目栏目数量
	public static String BBSFILTER = ""; // 论坛发贴回帖过滤

	public static String NEEDAUDIT = "";// 文章评论是否需要审核
	public static String ACTSMID = "";// 英日韩对应的栏目
	public static String TICKETCODE = ""; // 门票防伪码

	public static String STATNUM = ""; // 统计时保存数量
	public static String BBSUSER = "0"; // 统计时保存数量
	public static String TGFS = "";
	public static String DOMAINJX = "";// 网站简写
	public static Map<String, Object> KeyMap; // 统计时保存数量
	public static String CANNOTYUDINGUSER = "0"; // 判断可以预定用户 1、散客不能预定 2、团体不能预定 3、都不能预定
	// public static String CANNOTYUDINGUSER = "CANNOTYUDINGUSER";
	public static String YUDINGJUYUAN = "0";
	public static String OPER = "";
	public static String NJLB = "";
	public static String ExpritDate = "";
	 
	/**
	 * 读取ECTRIP.XML 文件件.
	 * 
	 * @param ls_key
	 *            这个KEY
	 * @param DefaultValues
	 *            缺省值
	 * @return
	 */
	public static String GetKeyValue(String ls_key) {

		String ls_values = "";
		// System.out.println("KEY=" + ls_key);
		try {
			if (KeyMap.containsKey(ls_key.toUpperCase())) {

				ls_values = KeyMap.get(ls_key.toUpperCase()).toString();
				// System.out.println("ls_values=" + ls_values);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ectrip.xml 文件中没有定义" + ls_key);
			ls_values = "";
		}
		return ls_values == null ? "" : ls_values;

	}

	/**
	 * 读取ECTRIP.XML 文件件.
	 * 
	 * @param ls_key
	 *            这个KEY
	 * @param DefaultValues
	 *            缺省值
	 * @return
	 */
	public static String GetKeyValue(String ls_key, String DefaultValues) {
		String ls_values = DefaultValues;

		try {
			if (KeyMap.containsKey(ls_key.toUpperCase())) {

				ls_values = (String) KeyMap.get(ls_key.toUpperCase());
			} else {
				ls_values = DefaultValues;
			}
		} catch (Exception e) {
			System.out.println("ectrip.xml 文件中没有定义" + ls_key);
			ls_values = DefaultValues;
		}
		return ls_values;
	}
}
