package com.ectrip.ec.custom.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custom.dao.idao.ICustomDAO;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Vcitable;
import com.ectrip.sys.model.syspar.Contmessage;

/**
 * 
 * @ClassName: CustomDAO
 * @Description: 我的账户-用户信息
 * @author Dicky
 * @date 2011-10-17 下午02:50:38
 * 
 */
@Repository
public class CustomDAO extends GenericDao implements ICustomDAO {
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getCustom
	 * </p>
	 * <p>
	 * Description:根据用户id查询单个用户信息
	 * </p>
	 * 
	 * @param usid
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#getCustom(java.lang.String)
	 */
	public Custom getCustom(String usid) {
		return (Custom) this.get(Custom.class, usid);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateCustom
	 * </p>
	 * <p>
	 * Description: 修改用户信息
	 * </p>
	 * 
	 * @param custom
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#updateCustom(com.ectrip.model.user.Custom)
	 */
	public boolean updateCustom(Custom custom) {
		try {
			this.update(custom);
			return true;
		} catch (Exception se) {
			return false;
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: executeHQL
	 * </p>
	 * <p>
	 * Description:执行hql语句 带参数的 修改 删除操作
	 * </p>
	 * 
	 * @param queryString
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#executeHQL(java.lang.String, java.lang.Object[])
	 */
	public int executeHQL(String queryString) {
		return this.bulkUpdate(queryString);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getDaoYouViewList
	 * </p>
	 * <p>
	 * Description: 分页 系统导游
	 * </p>
	 * 
	 * @param hql
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#getDaoYouViewList(java.lang.String, int, int,
	 *      java.lang.String)
	 */
	public PaginationSupport getDaoYouViewList(String usid, String hql, int pageSize,
			int startIndex, String url) {
		String hsql = "  FROM Custom d  WHERE d.usid  not in (select trim(a.id.dyusid) from Daoyou a where a.id.usid='"
				+ usid + "') and d.lgtp='02' and d.ttlb='02' and d.status='01'" + hql;
		// String hsql =
		// "   FROM Custom d WHERE d.lgtp='02' and d.ttlb='02' and d.status='01' "+hql;
		return this.findPage(hsql, pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getDaoYouPageList
	 * </p>
	 * <p>
	 * Description: 分类之后的导游分页列表
	 * </p>
	 * 
	 * @param usdj
	 *            类别
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#getDaoYouPageList(java.lang.String, int, int,
	 *      java.lang.String)
	 */
	public PaginationSupport getDaoYouPageList(String usdj, int pageSize, int startIndex, String url) {
		StringBuffer hql = new StringBuffer(
				"select new map(c.usid as usid,c.lname as lname,u.upadder as adder,u.upfilename as fname) from Custom c,Upfile u where c.inote1=u.upid and c.lgtp='02' and c.ttlb='02' and c.status='01'");
		if (usdj != null && !usdj.equals("")) {
			hql.append(" and c.usdj=" + usdj);
		}
		return this.findPage(hql.toString(), pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getDaoYouViewList
	 * </p>
	 * <p>
	 * Description:导游首页列表 含分类 每个分类10条数据
	 * </p>
	 * 
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#getDaoYouViewList()
	 */
	public List getDaoYouViewList(String usdj) {
		return this
				.find("select new map(c.usid as usid,c.lname as lname,u.upadder as adder,u.upfilename as fname) from Custom c,Upfile u where c.inote1 = u.upid and  rownum<=10 and lgtp='02' and ttlb='02' and status='01' and usdj="
						+ usdj);
	}

	/**
	 * 
	 * @Title: getChildCustomViewList
	 * @Description: 子用户分页
	 * @param @param hql
	 * @param @param pageSize
	 * @param @param startIndex
	 * @param @param url
	 * @param @return 设定文件
	 * @return PaginationSupport 返回类型
	 * @throws
	 */
	public PaginationSupport getChildCustomViewList(String usid, String hql, int pageSize,
			int startIndex, String url) {
		StringBuffer sb = new StringBuffer(" from Custom c where c.susid='" + usid + "' order by c.bname");

		return this.findPage(sb.toString(), pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: saveCustom
	 * </p>
	 * <p>
	 * Description: 保存新用户
	 * </p>
	 * 
	 * @param custom
	 * @return
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#saveCustom(com.ectrip.model.user.Custom)
	 */
	public void saveCustom(Custom custom) {
		this.save(custom);
	}

	/**
	 * 根据用户编号查询用户信息 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid用户编号
	 * @return return:Custom Date:2011-10-8
	 */
	public Custom retrieve(String usid) {
		Custom custom = null;
		custom = (Custom) this.get(Custom.class, usid);
		return custom;
	}

	public List getSonCustom(List list, String usid) {
		String hsql = " from Custom where susid = '" + usid + "' ";
		List list1 = this.find(hsql);
		if (list1 != null && list1.size() >= 1) {
			for (int i = 0; i < list1.size(); i++) {
				Custom custom = (Custom) list1.get(i);
				list.add(custom.getUsid());
				list = getSonCustom(list, custom.getUsid());
			}
		}
		return list;
	}
	/**
	 * 根据主用户查询子用户 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid主用户编号
	 * @param bs
	 * @return所有子用户列表，如('a','b','c','d') return:String Date:2011-10-31
	 */
	public String getUsids(String usid, StringBuffer bs) {
		
		
		List list = new ArrayList();
		list.add(usid);
		
		list = getSonCustom(list,usid);
		if(list!=null && list.size()>=1){
			for (int i = 0; i <list.size(); i++) {
				if(i==list.size()-1){
					bs.append("'"+list.get(i)+"'");
				}else{
					bs.append("'"+list.get(i)+"'"+",");
				}
			}
		}

		
		return bs.toString();
	}

	
	
	/**
	 * *
	 * Describe:验证常用导游唯一
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#volidateDaoyou(java.lang.String, java.lang.String, java.lang.String)
	 * @param usid
	 * @param dyid
	 * @param zjhm
	 * @return
	 * @author yangguang
	 * Date:2011-12-8
	 */
	public boolean volidateDaoyou(String usid, String dyid, String zjhm) {
		String hql = " from Daoyou where usid='" + usid + "' and dyusid='" + dyid + "'";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return false;
		} 
		return true;
	}
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
	public int getCustomMmda(String usid,String mmqt,String mmda){
		int num =0 ;
		String hsql =" from Custom where usid = '"+usid+"' and mmqt ='"+mmqt+"' and mmda='"+mmda+"' ";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			num = 1;
		}else{
			num = 0;
		}
		return num;
		
	}
	/*
	 * 新增验证码标识(non-Javadoc)
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#addVciTable(com.ectrip.model.user.Vcitable)
	 */
	public void addVciTable(Vcitable vcitable) {
		save(vcitable); 
	}
	/*
	 * 查询激活用户标识(non-Javadoc)
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#findVciTable(java.lang.String)
	 */
	public Vcitable findVciTable(String code) {
		List list =find("from Vcitable where code='"+code+"'");
		if(list==null||list.isEmpty()){
			return null;
		}
		return (Vcitable) list.get(0);
	}
	/*
	 * 检查用户是否存在(non-Javadoc)
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#checkUser(int, java.lang.String)
	 */
	public boolean checkUser(int tip,String username){
		List list = new ArrayList();
		if(tip==1){
			list =  this.find(" from Custom c where c.email = '"+username+"'");
		}
		if(tip==2){
			list = this.find(" from Custom c where c.mobile = '"+username+"' and c.note4='1'");
		}
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	/*
	 * 检查邮箱用户是否激活(non-Javadoc)
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#checkStatus(java.lang.String)
	 */
	public boolean checkStatus(String emailOrMobile){
		StringBuffer sql = new StringBuffer();
		// 手机号码匹配
		Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
		boolean c = p1.matcher(emailOrMobile).matches();
		if(c){
			sql.append("from Custom where mobile='"+emailOrMobile+"'");
		}
		if(Tools.isEmail(emailOrMobile)){
			sql.append("from Custom where email='"+emailOrMobile+"'");
		}
		List list = find(sql.toString());
		if (list.size()>0) {
			Custom custom = (Custom) list.get(0);
				if (c) { //手机
					if(custom.getNote4()=="1"){
						return true;
					}else{
						return false;
					}
				}
				else {//邮箱
					if(custom.getNote3()=="1"){
						return true;
					}else{
						return false;
					}
				}
		}else {
			return false;
		}
	}
	
	public String sendEmailTemp(String type, String[] content){
    	StringBuffer sends = new StringBuffer();
		String hql = "from Contmessage where controlpoin='" + type + "' and byisuse=1";
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
		    	Contmessage templates = (Contmessage) list.get(0);
		    	String sendmsg = templates.getTemplates();
		    	if (content.length > 0) {
		    		String[] strarr = sendmsg.split("@");
		    		if (strarr != null && strarr.length > 0) {
		    			for (int i = 0; i < strarr.length; i++) {
		    				sends.append(strarr[i]);
		    				if (i < content.length) {
		    					sends.append(content[i]);
		    				}
		    			}
		    		}
		    	}
			}
		return sends.toString();
		}
	/*
	 * 手机查询用户(non-Javadoc)
	 * @see com.ectrip.custom.dao.idao.ICustomDAO#findByEmail(java.lang.String)
	 */
	public Custom findByMobile(String mobile) {
		List list = find("from Custom where mobile='"+mobile+"' and note4='1' ");
		if (list.size()>0) {
			return (Custom) list.get(0);
		}else {
			return null;
		}
	}
	
	public Custom findByUsername(String username,String type){
		List list = null;
		list = this.find("from Custom c where c.email='"+username+"' or c.mobile='"+username+"'");
		if(list!=null&&!list.isEmpty()){
			if(type.equals("01")){
				list =  this.find(" from Custom c where c.email = '"+username+"' and c.note3='1'");
			}
			if(type.equals("02")){
				list = this.find(" from Custom c where c.mobile = '"+username+"' and c.note4='1'");
			}
			if(list!=null&&!list.isEmpty()){
				Custom custom = (Custom) list.get(0);
				return custom;
			}else{
				return new Custom();
			}
		}else{
			return null;
		}
	}
	
	//根据订单id获取custom
	public Custom queryByOrderId(String orderId){
		String hql = "select t from Custom t ,MOrder m where t.usid=m.usid and m.orid='"+orderId+"'";
		List list = this.find(hql);
		Custom custom = null;
		if(list!=null&&!list.isEmpty()){
			custom = (Custom) list.get(0);
		}
		return custom;
	}

	public Custom getCustomBymobile(String mobile) {

		List list = find("from Custom where mobile='"+mobile+"' ");
		if (list.size()>0) {
			return (Custom) list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List getCustomsByUserIds(String userIds) {
		String[] split = userIds.split(",");
		
		StringBuilder newUserIds = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			if(i!=0) {
				newUserIds.append(",");
			}
			
			newUserIds.append("'"+ split[i]+ "'");
		}
		
		String sql = "select distinct new map(usid as usid, mobile as mobile) from Custom where usid in("+ newUserIds +")";
		
		List list = this.find(sql);
		
		return list;
	}

	@Override
	public List<?> queryCustomByCondition(Custom custom) throws Exception {
		StringBuffer hql = new StringBuffer("from Custom cus where 1 = 1 ");
		//用户id
		if(StringUtil.isNotEmpty(custom.getUsid())) {
			
			hql.append(" and cus.usid = "+custom.getUsid());
		}
		//用户业务类型
		if(custom.getIbusinessid() != null) {
			
			hql.append(" and cus.ibusinessid = "+custom.getIbusinessid());
		}
		//用户名(注册人姓名)
		if(StringUtil.isNotEmpty(custom.getLname())) {
			
			hql.append(" and cus.lname like '%"+custom.getLname()+"%'");
		}
		//公司名称
		if(StringUtil.isEmpty(custom.getCorpname())) {
			hql.append(" and cus.lname like '%"+custom.getCorpname()+"%'");
		}
		List<?> list = this.find(hql.toString());
		return list;
	}
}
