package com.ectrip.ec.custom.service.iservice;

import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Vcitable;
import com.ectrip.sys.model.syspar.Customlog;
/**
 * 
* @ClassName: ICustomService 
* @Description: 我的账户-用户信息  业务处理类
* @author Dicky
* @date 2011-10-17 下午02:51:55 
*
 */
public interface ICustomService extends IGenericService{
    public void saveCustom(Custom custom, Customlog log);
    public Custom getCustom(String usid);
    public boolean updateCustom(Custom custom,Customlog log);
    public int executeHQL(String oldpassword,String newpassword,String usid,Customlog log);
    public int executeHQL2(String oldpassword,String newpassword,String usid,Customlog log);
    public PaginationSupport getDaoYouViewList(String usid,String hql,int pageSize,
    		int startIndex, String url);
    public List getChildCustom(String usid);
    public List getDaoYouViewList(String usdj);
    public PaginationSupport getDaoYouPageList(String usdj,int pageSize,int startIndex,String url);
    public PaginationSupport getChildCustomViewList(String usid,String hql,int pageSize,int startIndex,String url);
    
    /**
	 * 增加用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param customlog前台用户操作日志
	 * return:void
	 * Date:2011-10-7
	 */
	public void insertCustom(Custom custom,Customlog customlog);

	/**
	 * 根据用户编号查询用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid用户编号 
	 * @return
	 * return:Custom
	 * Date:2011-10-8
	 */
	public Custom retrieve(String usid);

	/**
	 * 根据主用户查询子用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid主用户编号
	 * @param bs
	 * @return当前主用户及所有子用户列表，如(a,b,c,d)
	 * return:String 
	 * Date:2011-10-31
	 */
	public String getUsids(String usid,StringBuffer bs);
	
	
	/**
	 * 
	 * Describe:验证常用导游唯一
	 * @auth:yangguang
	 * @param usid
	 * @param dyid
	 * @param zjhm
	 * @return
	 * return:boolean
	 * Date:2011-12-8
	 */
	public boolean volidateDaoyou(String usid,String dyid,String zjhm);
	/**
	 * 判断用户密码答案是否正确
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @param mmqt
	 * @param mmda
	 * @return
	 * return:int
	 * Date:2012-1-9
	 */
	public int getCustomMmda(String usid,String mmqt,String mmda);
	/**
	 * 新增验证码标识
	 * addVciTable(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth:hejiahua
	 * @param vcitable 
	 * void
	 * @exception 
	 * @date:2013-9-27 上午09:05:09
	 * @since  1.0.0
	 */
	public void addVciTable(Vcitable vcitable);
	/**
	 * 查询激活用户标识
	 * findVciTable(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth:hejiahua
	 * @param code
	 * @return 
	 * Vcitable
	 * @exception 
	 * @date:2013-9-27 上午11:39:23
	 * @since  1.0.0
	 */
	public Vcitable findVciTable(String code);
	/**
	 * 检查用户是否存在
	 * checkUser(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param tip
	 * @param username
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-10-12 下午02:38:17
	 * @since  1.0.0
	 */
	public boolean checkUser(int tip,String username);
	/**
	 * 检查用户是否激活
	 * checkStatus(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param emailOrMobile
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-10-12 下午02:38:28
	 * @since  1.0.0
	 */
	public boolean checkStatus(String emailOrMobile);
	
	public String sendEmailTemp(String type, String[] content);
	
	
	/***
	 * 生成用户根据用户类别随机
	 * @param ustp  注册类型 01邮箱02手机03QQ04新浪微博05支付宝06匿名订单
	 * @param value  根据ustp的值不同,value 可是邮箱  QQ 支付宝等
	 * @return
	 */
	public Custom anonymityUserCreate(String createType,String value);
	
	/**
	 * 
	 * Describe:
	 * @author:chenxinhao
	 * @param username	用户名
	 * @param pwd	密码
	 * @param type	类别 01 邮箱 02 手机
	 * @return
	 * return:Custom
	 * Date:2013-12-3
	 */
	public Custom createCustom(String username,String pwd,String type) ;
	
	/**
	 * 通过手机查询用户
	 * findByEmail(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param email
	 * @return 
	 * Custom
	 * @exception 
	 * @date:2013-10-24 下午05:41:48
	 * @since  1.0.0
	 */
	public Custom findByMobile(String mobile);
	
	public Map<String,String> checkMessageCode(Custom custom,String code) throws Exception;

	public Custom findByUsername(String username,String type);
	
	/**
	 * 
	 * Describe:
	 * @author:chenxinhao
	 * @param usid	用户主键
	 * @param type	验证码类别
	 * @param username	用户名
	 * @param code	验证码
	 * @return
	 * return:Vcitable
	 * Date:2013-12-3
	 */
	public Vcitable findVcitable(String usid,String type,String username,String code);
	/**
	 * 
	 * Describe:
	 * @author:chenxinhao
	 * @param usid	用户主键
	 * @param type	验证码类别
	 * @param username	用户名
	 * @param code	验证码
	 * @return
	 * return:
	 * Date:2013-12-3
	 */
	public void deleteVcitable(String usid,String type,String username,String code);
	
	public Custom AddCustomByWx(String createType, String value, String openId);
	
	public Map<String,String> checkMessageCode(Custom custom,String code,String type) throws Exception;
	
	public Custom queryByOrderId(String orderId);
	public Custom getCustomBymobile(String mobile);
	
	public List getCustomsByUserIds(String userIds);
	
	public List<?> queryCustomByCondition(Custom custom) throws Exception;
	public void updateCustom(Custom custom);
}
