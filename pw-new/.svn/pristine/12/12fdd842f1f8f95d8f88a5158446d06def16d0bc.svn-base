package com.ectrip.ec.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Customvip;
import com.ectrip.ec.user.dao.idao.ICustomInfoDAO;
import com.ectrip.ec.user.service.iservice.ICustomInfoService;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.ProviderCompany;

/**
 * 用户信息servive
 * @author huangyuqi
 */
@Service
public class CustomInfoService implements ICustomInfoService {

	@Autowired
	private ICustomInfoDAO  custominfoDao;
	

	/**
	 * 根据编号查询用户信息
	 */
	public Custom getCustionById(String usid) {
		Custom custionById = null;
		try {
			custionById = this.custominfoDao.getCustionById(usid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custionById;
	}

	/**
	 * 获取所有用户信息*
	 * Describe:
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getCustomViewList(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param lgtp注册类别
	 * @param ttlb团体类别（01旅行社02导游）
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author huangyuqi
	 * Date:2011-10-27
	 */
	public PaginationSupport getCustomViewList(String lgtp,String ttlb, int pageSize,
			int startIndex, String url) {
		return this.custominfoDao.getCustomViewList(lgtp,ttlb, pageSize, startIndex,
				url);
	}
	
	public PaginationSupport getCustomViewList(String lgtp, String ttlb,Long ibusinessid,
			int pageSize, int startIndex, String url){
		return this.custominfoDao.getCustomViewList(lgtp,ttlb,ibusinessid, pageSize, startIndex,
				url);
	}
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
			int startIndex, String path){
		return custominfoDao.getSonUserList(usid, pageSize, startIndex, path);
	}
	/**
	 * 获取所有用户信息*
	 * Describe:
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getCustomViewList(java.lang.String, int, int, java.lang.String)
	 * @param lgtp注册类型
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author huangyuqi
	 * Date:2011-10-27
	 */
	public PaginationSupport getCustomViewList(String lgtp, int pageSize,
			int startIndex, String url) {
		return this.custominfoDao.getCustomViewList(lgtp, pageSize, startIndex,
				url);
	}

	/**
	 * 审核用户
	 */
	public void updateCustomInfo(Custom custom,Syslog syslog) {
		this.custominfoDao.updateCustomInfo(custom,syslog);
	}
	
	/**
	 * *
	 * Describe:初始化密码和初始化错误登录次数
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#updateCustom(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Apr 12, 2012
	 */
	public void updateCustom(Custom custom,Syslog syslog){
		this.custominfoDao.updateCustom(custom, syslog);
	}

	/**
	 * 查询用户列表
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @param radiobutton查询方式
	 * @param custom用户类
	 * @return
	 */
	public PaginationSupport searchCustom(int pageSize, int startIndex, String url,int radiobutton,Custom custom){
		return this.custominfoDao.searchCustom( pageSize, startIndex,url,radiobutton,custom);
	}
	/**
	 * 新增用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void addCustomInfo(Custom custom,Syslog syslog ){
		custominfoDao.addCustomInfo(custom,syslog );
	}

	/**
	 * 新增用户信息
	 * @param custom
	 * @param pc
	 * @param syslog
     */
	public void addCustomInfo(Custom custom, ProviderCompany pc, Syslog syslog ){
		custominfoDao.addCustomInfo(custom,pc,syslog );
	}

	/**
	 * 修改用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void editCustomInfo(Custom custom,Syslog syslog ){
		custominfoDao.editCustomInfo(custom, syslog);
	}
	/**
	 * 删除用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void
	 * Date:2011-10-24
	 */
	public void deleteCustomInfo(String usid,Syslog syslog ){
		custominfoDao.deleteCustomInfo(usid, syslog);
	}
	/**
	 * 判断用户在订单中是否存在数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2011-10-24
	 */
	public boolean queryCustomByorder(String usid){
		return custominfoDao.queryCustomByorder(usid);
	}
	
	
	/**
	 * 查看导游是否含有带团订单
	 * @param usid
	 * @return
	 */
	public boolean queryDaoyouByorder(String usid) {
		return custominfoDao.queryDaoyouByorder(usid);
	}
	/**
	 * 它是否是主用户，并且下面是否有子用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2011-10-24
	 */
	public boolean queryCustomBysusid(String usid){
		return custominfoDao.queryCustomBysusid(usid);
	}
	/**
	 * 根据子用户查询上级用户
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:String
	 * Date:2011-12-3
	 */
	public String queryParentUsid(String usid){
		return custominfoDao.queryParentUsid(usid);
	}
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
	public List queryCustomList(String lgtp,String ttlb,String ustp){
		return custominfoDao.queryCustomList(lgtp, ttlb, ustp);
	}
	/**
	 * 根据主用户以及子用户（用于团体）
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid主用户名
	 * @return
	 * return:String
	 * Date:2011-12-31
	 */
	public String queryCustoms(String usid){
		return custominfoDao.queryCustoms(usid);
	}
	/**
	 * 根据子用户得到根用户（最高级用户）
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:String
	 * Date:2011-12-31
	 */
	public String queryRootUsid(String usid){
		return custominfoDao.queryRootUsid(usid);
	}
	
public void initdaoyouzhiwen(String usid,Syslog syslog){
	  custominfoDao.initdaoyouzhiwen(usid,syslog);
	}


/**
 * 根据主用户查询子用户(用于json)
 * Describe:
 * @auth:袁成军
 * @param usid主用户名
 * @return
 * return:String
 * Date:2011-12-31
 */
public List queryCustomjson(String usid){
	return custominfoDao.queryCustomjson(usid);
}
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
public List querylxsList(Long ibusinessid,String ustp){
	return custominfoDao.querylxsList(ibusinessid,ustp);
}

/**
 * *
 * Describe:查看某个ＶＩＰ属性信息
 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getlookvipcustom(java.lang.String)
 * @param usid
 * @return
 * @author lijingrui
 * Date:Apr 12, 2012
 */
public Customvip getlookvipcustom(String usid){
	return custominfoDao.getlookvipcustom(usid);
}

/**
 * *
 * Describe:根据vip类型查出金额和人数
 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getviewvip(java.lang.String)
 * @param cvtype
 * @return
 * @author lijingrui
 * Date:Apr 12, 2012
 */
public Sysparv5 getviewvip(String cvtype)throws Exception{
	return custominfoDao.getviewvip(cvtype);
}

/**
 * 
 * Describe:判读审核后的VIP用户的卡号的唯一性
 * @auth:lijingrui
 * @param cvtedest1
 * @return
 * return:boolean
 * Date:May 14, 2012
 */
public boolean getQueckcvtesst(String cvtedest1){
	boolean b=false;
	String hsql="select new map(ct.usid as usid,ct.password as password,cp.cvnum as cvnum,cp.cvmoney as cvmoney,cp.cvterno1 as cvterno1) from Custom ct,Customvip cp where ct.usid=cp.usid and ct.status='01' and cp.cvtedest1='"+cvtedest1+"' and cp.byisuse=1 ";
	List list=custominfoDao.find(hsql);
	if(list!=null&&list.size()>0){
		b=true;
	}else{
		b=false;
	}
	return b;
}

	/**
	 * Describe: 座位锁定-获取公司名称(旅行社名称)
	 * @author luoxin
	 * Date：2013-12-03
	 * */
	public List getCustomList() {
		// TODO Auto-generated method stub
		return custominfoDao.getCustomList();
	}

	public ProviderCompany findProviderCompanyByUisd(String usid){
		return custominfoDao.findProviderCompanyByUisd(usid);
	}
}
