package com.ectrip.ec.user.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Customvip;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.ProviderCompany;
/**
 * 用户信息
 * @author huangyuqi
 */
public interface ICustomInfoService {
	/**
	 * 审核用户
	 * 
	 * @param custom
	 */
	public void updateCustomInfo(Custom custom,Syslog syslog);
	
	/**
	 * 
	 * Describe:初始化密码和初始化错误登录次数
	 * @auth:lijingrui
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:Apr 12, 2012
	 */
	public void updateCustom(Custom custom,Syslog syslog);

	/**
	 * 根据编号获取用户信息
	 * 
	 * @return
	 */
	public Custom getCustionById(String usid);

	/**
	 * 获取用户列表
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param lgtp
	 * @param path
	 * @return
	 */
	public PaginationSupport getCustomViewList(String lgtp,String ttlb, int pageSize,
			int startIndex, String path);
	
	/**
	 * 获取子用户列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid主用户名
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-25
	 */
	public PaginationSupport getSonUserList(String usid, int pageSize,
			int startIndex, String path);
	
	/**
	 * 获取用户列表
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param lgtp
	 * @param path
	 * @return
	 */
	public PaginationSupport getCustomViewList(String lgtp, int pageSize,
			int startIndex, String path);

	/**
	 * 查询用户列表
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @param radiobutton查询方式
	 * @param custom用户类
	 * @return
	 */
	public PaginationSupport searchCustom(int pageSize, int startIndex, String url,int radiobutton,Custom custom);
	
	/**
	 * 修改用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void addCustomInfo(Custom custom,Syslog syslog );

	/**
	 * 新增用户信息
	 * @param custom
	 * @param pc
	 * @param syslog
	 */
	public void addCustomInfo(Custom custom, ProviderCompany pc, Syslog syslog );

	/**
	 * 新增用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void editCustomInfo(Custom custom,Syslog syslog );
	/**
	 * 删除用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void deleteCustomInfo(String usid,Syslog syslog );
	/**
	 * 判断用户在订单中是否存在数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2011-10-24
	 */
	public boolean queryCustomByorder(String usid);
	
	
	public boolean queryDaoyouByorder(String usid);
	/**
	 * 它是否是主用户，并且下面是否有子用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2011-10-24
	 */
	public boolean queryCustomBysusid(String usid);
	/**
	 * 根据子用户查询上级用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:String
	 * Date:2011-12-3
	 */
	public String queryParentUsid(String usid);
	/**
	 * 查询用户列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param lgtp注册类别 01散客 02团体
	 * @param ttlb团休类别   01旅行社   02 导游  
	 * @param ustp用户类别   01总社 02分社 03部门
	 * @return
	 * return:List
	 * Date:2011-12-31
	 */
	public List queryCustomList(String lgtp,String ttlb,String ustp);
	/**
	 * 根据主用户以及子用户（用于团体）
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid主用户名
	 * @return
	 * return:String
	 * Date:2011-12-31
	 */
	public String queryCustoms(String usid);
	/**
	 * 根据子用户得到根用户（最高级用户）
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:String
	 * Date:2011-12-31
	 */
	public String queryRootUsid(String usid);
	
	
	public void initdaoyouzhiwen(String usid,Syslog syslog);
	
	/**
	 * 根据主用户查询子用户(用于json)
	 * Describe:
	 * @auth:袁成军
	 * @param usid主用户名
	 * @return
	 * return:String
	 * Date:2011-12-31
	 */
	public List queryCustomjson(String usid);
	
	
	/**
	 * 根据业务查询用户
	 * Describe:
	 * @auth:yuanchengjun
	 * @param ibusinessid
	 * @param ustp　当　ibusinessid＝２　时　00 表示所有　01 主旅行社　02 分社
	 * @return
	 * return:List
	 * Date:2012-3-20
	 */
	public List querylxsList(Long ibusinessid,String ustp);
	/**
	 * 区分旅行社里面的接待和旅行社用户
	 * Describe:
	 * @auth:yuanchengjun
	 * @param lgtp
	 * @param ttlb
	 * @param ibusinessid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-4-7
	 */
	public PaginationSupport getCustomViewList(String lgtp, String ttlb,Long ibusinessid,
			int pageSize, int startIndex, String url);
	/**
	 * 
	 * Describe:查看某个ＶＩＰ属性信息
	 * @auth:lijingrui
	 * @param usid
	 * @return
	 * return:Customvip
	 * Date:Apr 12, 2012
	 */
	public Customvip getlookvipcustom(String usid);

	/**
	 * 
	 * Describe:根据vip类型查出金额和人数
	 * @auth:lijingrui
	 * @param cvtype
	 * @return
	 * return:String
	 * Date:Apr 12, 2012
	 */
	public Sysparv5 getviewvip(String cvtype)throws Exception;
	
	/**
	 * 
	 * Describe:判读审核后的VIP用户的卡号的唯一性
	 * @auth:lijingrui
	 * @param cvtedest1
	 * @return
	 * return:boolean
	 * Date:May 14, 2012
	 */
	public boolean getQueckcvtesst(String cvtedest1);

	/**
	 * Describe: 座位锁定-获取公司名称(旅行社名称)
	 * @author luoxin
	 * Date：2013-12-03
	 * */
	public List getCustomList();

	public ProviderCompany findProviderCompanyByUisd(String usid);
	
}
