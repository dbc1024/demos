package com.ectrip.sys.employee.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IGALCompanyInfoTabDAO;
import com.ectrip.sys.employee.service.IGALCompanyInfoService;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfdepttab;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
/**
 * 
 * @author yangyang
 * @version 企业管理操作类
 */
@Service
public class GALCompanyInfoService extends GenericService implements IGALCompanyInfoService {

	IGALCompanyInfoTabDAO companyInfoDao;
	@Autowired
	public void setCompanyInfoDao(IGALCompanyInfoTabDAO companyInfoDao) {
		super.setGenericDao(companyInfoDao);
		this.companyInfoDao = companyInfoDao;
	}
	
	
	

	/**
	 * 功能 ： 删除企业信息
	 * 
	 * @param id
	 *          企业ID
	 */
	public void deletecompany(Galcompanyinfotab comp) {
		// 删除企业的时，要先在企业与服务商的关系表中删除相应的数据
		companyInfoDao.deletecompany(comp);
		// 添加日志信息
		/*Esfemployeetab emp = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");*/
		Esfemployeetab emp = null;
		Syslog log = new Syslog();
		log.setLogid(companyInfoDao.getMaxPk("logid", "Syslog") + 1);

		log.setIemployeeid(emp.getIemployeeid());

		log.setStlg(comp.getIcompanyinfoid() + "");
		log.setBrief("企业管理:" + comp.getSzcompanyname());
		log.setNote("删除企业:" + comp.getSzcompanyname());

		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.setLogdatetime(format.format(d));
		companyInfoDao.save(log);
	}

	public List select() {
		String sql = " from Galcompanyinfotab";
		return companyInfoDao.find(sql);
	}

	/**
	 * * Describe:
	 * 
	 * @see com.ectrip.system.employee.service.iservice.IGALCompanyInfoService#companyQueryList(java.lang.String,
	 *      int, int, int, int, java.lang.String)
	 * @param mark
	 *          控制 上一次 还是下一层
	 * @param comid
	 *          企业的ID 即获取此企业的下级列表
	 * @param szinfocomid
	 *          //上级企业ID
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 * @author yangguang Date:2011-9-19
	 */
	public PaginationSupport companyQueryList(String mark, Long comid, int page,
			int pagesize, String url) throws Exception{
		return companyInfoDao.companyQueryList(mark, comid, page, pagesize, url) ;
	}
	public PaginationSupport companyQueryListbycomid(String mark, Long comid, int page,
			int pagesize, String url) {
		return companyInfoDao.companyQueryListbycomid(mark, comid, page, pagesize, url);
	}
	/**
	 * 功能 ：按条件模糊查询
	 * 
	 * @param flag
	 *          查询条件类别
	 * @param parentid
	 *          上级企业ID
	 * @param queryid
	 *          企业编号
	 * @param queryMess
	 *          企业名称所包含的字符
	 */
	public PaginationSupport companyQueryListTJ(String flag, int parentid,
			String queryid, String queryMess, int page, int pagesize, String url) {
		
		return companyInfoDao.companyQueryListTJ(flag, parentid, queryid, queryMess, page, pagesize, url);
	}

	/**
	 * 功能 :查询是否含有子级
	 * 
	 * @param icompanyinfoid
	 * @return true 有子级 false 没子级
	 */
	public boolean isHaveSon(String icompanyinfoid) {
		String hql = "select count(*) from Galcompanyinfotab company where company.szinfocomid="
				+ icompanyinfoid + "";
		List list = companyInfoDao.find(hql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询是否
	 * 
	 * @param icompanyinfoid
	 * @return false 不是平台企业 true 平台企业
	 */
	public boolean isPlatform(String icompanyinfoid) {
		String hql = "from Galcompanyinfotab company where company.icompanyinfoid="
				+ icompanyinfoid + "";
		List list = companyInfoDao.find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ishavedep(String icompanyinfoid) {
		String hql = "from Esfdepttab  where icompanyinfoid=" + icompanyinfoid + "";
		List list = companyInfoDao.find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能 ：添加新的企业
	 * 
	 * @param comp
	 *          新企业信息
	 * @param parent
	 *          上级企业信息
	 * @param scenisc
	 *          服务商列表
	 */
	public void addNewCompany(Galcompanyinfotab comp) {
		
		companyInfoDao.addNewCompany(comp);

		// 添加日志信息
		/*Esfemployeetab emp = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");*/
		Esfemployeetab emp = null;
		Syslog log = new Syslog();
		log.setLogid(companyInfoDao.getMaxPk("logid", "Syslog") + 1);

		log.setStlg(comp.getIcompanyinfoid() + "");

		log.setIemployeeid(emp.getIemployeeid());


		log.setStlg(comp.getIcompanyinfoid() + "");
		log.setBrief("企业管理:" + comp.getSzcompanyname());
		log.setNote("添加新企业：" + comp.getSzcompanyname());

		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.setLogdatetime(format.format(d));
		companyInfoDao.save(log);
		
	}

	/**
	 * 功能 ：根据ID获取公司信息
	 * 
	 * @param icompanyinfoid
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Galcompanyinfotab getCompanyById(Long icompanyinfoid)
			throws IllegalAccessException, InvocationTargetException {
		return companyInfoDao.getCompanyById(icompanyinfoid);
	}

	/**
	 * 功能 ：修改公司信息
	 * 
	 * @param comp
	 * @param parent
	 */
	public void updateCompany(Galcompanyinfotab comp) {
/*		comp.setIversion(new Long(1));
		companyInfoDao.update(comp);
		
		if (comp.getCompanytype().equals("02")) {
			Long[] iscenics = comp.getIscenicids();
			StringBuffer scenics=new StringBuffer();
			for (int k = 0; k < iscenics.length; k++) {
				scenics.append(iscenics[k]);
				if(k!=iscenics.length-1){
					scenics.append(",");
				}
			}
			
			String msql = " from Companyscenic where id.icompanyinfoid="+ comp.getIcompanyinfoid()+" and id.iscenicid not in ("+scenics+")";
			List lst=companyInfoDao.find(msql);
			if(lst!=null&&lst.size()>0){
				for(int x=0;x<lst.size();x++){
					Companyscenic compay = (Companyscenic) lst.get(x);
					companyInfoDao.delete(compay);
				}
			}
			
			String hsql = " from Companyscenic where id.icompanyinfoid="+ comp.getIcompanyinfoid();
			List list = companyInfoDao.find(hsql);
			
			for (int k = 0; k < iscenics.length; k++) {
				Long iscenicid=Long.parseLong(iscenics[k].toString());
				boolean b=false;
				for (int i = 0; i < list.size(); i++) {
					Companyscenic s = (Companyscenic) list.get(i);
					if(s.getId().getIscenicid().equals(iscenicid) ){
						b=false;
						break;
					}else{
						b=true;
					}
				}
				System.out.println("++b:"+b);
				if(b){
					Companyscenic cs = new Companyscenic();
					CompanyscenicId csid = new CompanyscenicId();
					csid.setIcompanyinfoid(comp.getIcompanyinfoid());
					csid.setIscenicid(iscenicid);
					cs.setId(csid);
					cs.setCytonly("0");
					companyInfoDao.save(cs);
				}
				
			}
			
		}*/

		companyInfoDao.updateCompany(comp);

		// 添加日志信息
		/*Esfemployeetab emp = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");*/
		Esfemployeetab emp = null;
		Syslog log = new Syslog();
		log.setLogid(companyInfoDao.getMaxPk("logid", "Syslog") + 1);

		log.setStlg(comp.getIcompanyinfoid() + "");

		log.setIemployeeid(emp.getIemployeeid());

		log.setStlg(comp.getIcompanyinfoid() + "");
		log.setBrief("企业管理:" + comp.getSzcompanyname());
		log.setNote("修改企业信息：" + comp.getSzcompanyname());

		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.setLogdatetime(format.format(d));
		companyInfoDao.save(log);
	}

	/**
	 * 功能 ：根据企业类型获取企业数量
	 * 
	 * @param companytype
	 *          企业类型
	 */
	public int getPlatformCount(String companytype) {
		String hql = "select count(*) from Galcompanyinfotab company where company.companytype='"
				+ companytype + "'";
		List list = companyInfoDao.find(hql);
		if (list != null && list.size() > 0) {
			int count = Integer.parseInt(list.get(0).toString());
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * 功能 ： 查询企业服务商
	 */
	public List getCompanyScenics(List scids) {
		return companyInfoDao.getCompanyScenics(scids);
	}

	/**
	 * 功能 ：保存企业与服务商
	 * 
	 * @param comid
	 *          企业编号
	 */
	public void saveCompanyScenics(Galcompanyinfotab comp) {
		// 在企业与服务商关系表中添加数据
		companyInfoDao.saveCompanyScenics(comp);
	}

	/**
	 * 功能 ：根据ID查找出它所有的下属
	 * 
	 * @param iscenicid
	 *          服务商ID
	 */
	public List getScenicsByOneid(Long parentid) {

		return companyInfoDao.getScenicsByOneid(parentid);
	}

	/**
	 * 功能 ：在关系表中查询企业所选择的服务商
	 * 
	 * @param comid
	 *          企业编号
	 */
	public List getSelectedComscenics(Long comid) {
	//	String sql = " from Companyscenic where icompanyinfoid=" + comid;
		return companyInfoDao.getSelectedComscenics(comid);
	}

	public List getComscenics(Long comid) {
		
		return companyInfoDao.getComscenics(comid);
	}
	
	public List getComscenicsId(Long comid) {		
		return companyInfoDao.getComscenicsId(comid);
	}
	
	public List getHotelCom(Long comid,String domainname) {		
		return companyInfoDao.getHotelCom(comid,domainname);
	}
	
	public List getComscenicsName(Long comid) {		
		return companyInfoDao.getComscenicsName(comid);
	}

	/**
	 * 首页获取景区服务商
	 * @param comid
	 * @return
	 */
	public List getComsName(Long comid) {		
		return companyInfoDao.getComsName(comid);
	}
	/**
	 * Describe: 用户关联的服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iemployee
	 * @return list
	 */
	public List getEmployeeScenicbyIemployeeid(Long iemployeeid) {
		// TODO Auto-generated method stub
		return companyInfoDao.getEmployeeScenicbyIemployeeid(iemployeeid);
	}
	
	/**
	 * Describe: 服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iscenicid
	 * @return Esbscenicareatab
	 */
	public Esbscenicareatab getScenic(Long iscenicid) {
		// TODO Auto-generated method stub
		return companyInfoDao.getScenic(iscenicid);
	}

	@Override
	public List getIscenicidsByIcompanyinfoid(Long icompanyinfoid) {
		// TODO Auto-generated method stub
		return companyInfoDao.getIscenicidsByIcompanyinfoid(icompanyinfoid);
	}
	
	
	/**
	 * 
	 * Describe:获取组织架构书
	 * 
	 * @auth:yangguang
	 * @param id
	 *            节点ID
	 * @param type
	 *            节点类型 0:公司 1:部门 2:岗位
	 * @param icompanyid
	 *            公司ID 只在查询员工时用到
	 * @param deptid
	 *            部门ID 只在查询员工时用到
	 * @return return:List Date:2011-10-22
	 */
	public List getCompanyTree(String id, String type, String icompanyid, String deptid) {
		// 1 如果是平台企业则 显示全部 企业
		// 2 如果是景区企业则显示企业及下级企业以及部门
		StringBuffer hql = new StringBuffer();
		StringBuffer hql1 = new StringBuffer();
		StringBuffer hql2 = new StringBuffer();
		StringBuffer hql3 = new StringBuffer();
		List list = null;
		List list1 = null;
		List list2 = null;
		List list3 = null;
		if (type.equals("0")) {// 企业
			hql.append("select new map(icompanyinfoid as id,szcompanyname as name,0 as type,'true' as hasnext,'icon01' as iconSkin) from Galcompanyinfotab where 1=1");
			if (!id.equals("0")) {
				Galcompanyinfotab company = (Galcompanyinfotab) companyInfoDao.get(Galcompanyinfotab.class, new Long(id));
				
				if (company != null) {
					hql.append(" and szinfocomid=" + id);
				} else {
					hql.append(" and icompanyinfoid=" + id);
				}
			} else {
				hql.append(" and szinfocomid=0");
			}
			// 查询企业
			list = companyInfoDao.find(hql.toString());

			if (list != null && list.size() > 0) {
				Map dateMap = null;
				for (int i = 0; i < list.size(); i++) {
					hql1.delete(0, hql1.length());
					hql2.delete(0, hql2.length());
					hql3.delete(0, hql3.length());
					dateMap = (Map) list.get(i);
					// 查询子企业
					hql1.append("select new map(icompanyinfoid as id,szcompanyname as name,szinfocomid as pid,0 as type,'true' as hasnext,'icon01' as iconSkin) "
									+ " from Galcompanyinfotab where 1=1 and szinfocomid="+ Integer.parseInt(dateMap.get("id").toString()));

					// 查询部门
					hql2.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) "
									+ " from Esfdepttab where bisdelete=0 and  iparentid=0 and byisuse=1 and icompanyinfoid="+ Integer.parseInt(dateMap.get("id").toString()));
					// 部门列表
					list2 = companyInfoDao.find(hql2.toString());
					// 企业列表
					list1 = companyInfoDao.find(hql1.toString());

					// 如果不是第一级,需要把查询出来的子企业和部门合并 他们属于同一级别
					if (!id.equals("0")) {
						if (list2 != null && list2.size() > 0) {
							list.addAll(list2);
						}
					}
				}
			}
			hql1.delete(0, hql1.length());
			hql1.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) "
					+ " from Esfdepttab where bisdelete=0  and  iparentid=0 and byisuse=1 and icompanyinfoid="+ Integer.parseInt(id.toString()));
			
			list1 = companyInfoDao.find(hql1.toString());
			// 如果部门不为空 则循环判断部门下的岗位
			if (list1 != null) {
				list.addAll(list1);
			}
		} else if (type.equals("1")) {// 部门
			// 根据企业编号查询部门
			hql.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) "
					+ " from  Esfdepttab where bisdelete=0 and byisuse=1 and iparentid="+ id);
			
			// 部门列表
			list = companyInfoDao.find(hql.toString());
			// 如果查询的是一级部门 因为是根据公司ID获取的,所以不应该查询岗位列表
			Esfdepttab dept = (Esfdepttab) companyInfoDao.get(Esfdepttab.class, new Long(id));

			// 根据部门编号查询岗位列表
			hql1.append("select new map(post.ipostsid as id,post.szpostsname as name,3 as type,dp.icompanyinfoid as icompanyid,dp.ideptid as deptid,'true' as hasnext,'icon02' as iconSkin) "
					+ " from Esfdeptpoststab  dept,Esppoststab  post,Esfdepttab dp where dp.ideptid=dept.ideptid and dept.ipostsid=post.ipostsid and dp.ideptid="+ id);
			// 岗位列表
			
			list1 = companyInfoDao.find(hql1.toString());
			// hql2.append("select new map(ideptid as ideptid,szdeptname as szdeptname,1 as type) from Esfdepttab where isdelete=0 and byisuse=1 and iparentid=0 and icompanyinfoid="+id);
			// 如果部门下有岗位则加上去
			if (list1 != null && list1.size() > 0) {
				list.addAll(list1);
			}
		} else {// 员工
			// 根据岗位编号 公司编号 部门编号获取员工列表
			hql1.append("select new map(emp.iemployeeid as id,emp.szemployeename as name,'icon03' as iconSkin) "
					+ " from Esfemployeetab emp,Esfemployeepoststab empost,Esfdeptpoststab deptpost where emp.icompanyinfoid="+ icompanyid
					+ " and emp.ideptid="+ deptid
					+ " and emp.ideptid=deptpost.ideptid and empost.iemployeeid=emp.iemployeeid and deptpost.ipostsid=empost.ipostsid and empost.ipostsid="+ id);
			
			list = companyInfoDao.find(hql1.toString());
		}
		return list;
	}
	
	public Galcompanyinfotab getGalcompanyinfo( Long iscenicid ) {
		List<Companyscenic> csl = this.find(" from Companyscenic c where c.id.iscenicid=? and c.cytonly='1'",
				new Object[] {iscenicid });
		if (csl != null && csl.size() > 0) {
			return (Galcompanyinfotab) this.get(Galcompanyinfotab.class,
					csl.get(0).getId().getIcompanyinfoid());
		}
		
		return null;
	}
	
}
