package com.ectrip.ec.custom.dao.idao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Vcitable;
/**
 * 
* @ClassName: ICustomDAO 
* @Description: 我的账户-用户信息 
* @author Dicky
* @date 2011-10-17 下午02:49:19 
*
 */

public interface ICustomDAO extends IGenericDao{
	public void saveCustom(Custom custom);
    public Custom getCustom(String usid);
    public boolean updateCustom(Custom custom);
    public int executeHQL(String queryString);
    public PaginationSupport getDaoYouViewList(String usid,String hql,int pageSize,
 			int startIndex, String url);
    public List getDaoYouViewList(String usdj);
    public PaginationSupport getDaoYouPageList(String usdj,int pageSize,int startIndex,String url);
    public PaginationSupport getChildCustomViewList(String usid,String hql,int pageSize,int startIndex,String url);
    
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
	 * @param vcitable 验证码标识对象
	 * void
	 * @exception 
	 * @date:2013-9-27 上午08:54:13
	 * @since  1.0.0
	 */
	public void addVciTable(Vcitable vcitable);
	/**
	 * 查询激活用户的标识
	 * findVciTable(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth:hejiahua
	 * @param code
	 * @return 
	 * String
	 * @exception 
	 * @date:2013-9-27 上午11:37:01
	 * @since  1.0.0
	 */
	public Vcitable findVciTable(String code);
	/**
	 * *
	 * Describe:
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#checkUser(int, java.lang.String)
	 * @param tip	1为邮箱，2为手机
	 * @param username 用户名，邮箱或者手机
	 * @return
	 * @author chenxinhao
	 * Date:2013-10-9
	 */
	public boolean checkUser(int tip,String username);
	/**
	 * 检查用户状态是否激活
	 * checkStatus(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param emailOrMobile邮箱或者手机
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-10-12 下午02:27:29
	 * @since  1.0.0
	 */
	public boolean checkStatus(String emailOrMobile);
	
	public String sendEmailTemp(String type, String[] content);
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
	
	public Custom findByUsername(String username,String type);
	
	/**
	 * 根据订单号获取账户
	 * @param orderId
	 * @return
	 */
	public Custom queryByOrderId(String orderId);

	/**
	 * 根据手机号获取用户
	 * @param mobile
	 * @return
	 */
	public Custom getCustomBymobile(String mobile);
	
	public List getCustomsByUserIds(String userIds);
	
	public List<?> queryCustomByCondition(Custom custom) throws Exception;
}
