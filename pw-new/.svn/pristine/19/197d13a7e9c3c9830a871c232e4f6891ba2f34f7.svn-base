package com.ectrip.sys.employee.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.employee.dao.IEsfEmployeeTabDAO;
import com.ectrip.sys.model.employee.Esfemployeepoststab;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Hsyslog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;

@Repository
public class EsfEmployeeTabDAO extends GenericDao implements IEsfEmployeeTabDAO {
	
	@Resource
    public void setSessionFactoryOverride(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	/**
	 * * Describe:根据部门ID 员工名称 查询员工列表并分页
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getEmployeeList(java.lang.String,java.lang.String,
	 *      int, int, java.lang.String)
	 * @param deptid
	 *            部门ID
	 * @param ipostsid
	 *            职务ID
	 * @param szemployeename
	 *            员工名称
	 * @param pageSize
	 *            第几页
	 * @param startIndex
	 *            其实数
	 * @param url
	 *            分页url
	 * @return 分页类
	 * @author yangguang Date:2011-9-13
	 */
	public PaginationSupport getEmployeeList(Long ideptid,
			String szemployeename, int pageSize, int startIndex, String url) {
		StringBuffer hql = new StringBuffer();
		hql.append("select new map(emp.iemployeeid as iemployeeid,emp.bissysuser as bissysuser,emp.szpassword as szpassword,emp.empid as empid,emp.szemployeename as szemployeename,emp.isex as isex,emp.byisuse as byisuse,emp.szcardid as szcardid,emp.dtbirthdate as dtbirthdate,emp.bismarry as bismarry,emp.dtentrydate as dtentrydate "
				+ ",emp.szcellphone as szcellphone,emp.szemail as szemail,emp.szaccountlocation as szaccountlocation,emp.szhomeaddress as szhomeaddress,emp.szschool as szschool,emp.dtgraduatedate as dtgraduatedate,emp.szmemo as szmemo,emp.photoupid as photoupid,emp.dnum as dnum,emp.ztimes as ztimes,emp.logintime as logintime,emp.ipaddress as ipaddress,sys1.pmva as byloginitype,emp.educational as educational,emp.major as major,"
				+ "emp.sznation as sznation,emp.szpost as szpost,emp.professional as professional,emp.iborthaddress as iborthaddress) from Esfemployeetab emp,Sysparv5 sys1 where emp.byloginitype=sys1.id.pmcd and sys1.id.pmky='DLTP' and emp.ideptid="
				+ ideptid);
		if (szemployeename != null && !szemployeename.equals("")) {
			hql.append(" and emp.szemployeename like '%" + szemployeename
					+ "%'");
		}
		hql.append(" order by emp.iemployeeid ");
		return this.findPage(hql.toString(), pageSize, startIndex, url);
	}

	/**
	 * * Describe:保存员工对象
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#saveEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang Date:2011-9-15
	 */
	public void saveEmployee(Esfemployeetab employee, String[] ipostsids,
			Syslog syslog) {
		// 照片
		// employee.setPhotoupid(new Long(0));
		// 总登录次数
		employee.setDnum(new Long(5));
		// 当日错误登录次数
		employee.setZtimes(new Long(0));
		// 最后登录时间
		employee.setLogintime("0");
		// 最后登录次数
		employee.setIpaddress("0");
		Long maxid = this.getMaxPk("iemployeeid", "Esfemployeetab");
		employee.setIemployeeid(maxid + 1);
		this.save(employee);

		for (int i = 0; i < ipostsids.length; i++) {
			Esfemployeepoststab esfemppost = new Esfemployeepoststab();
			esfemppost.setIpostsid(new Long(ipostsids[i]));

			String sql = "select max(iemployeepostsid) from Esfemployeepoststab";
			List list = this.find(sql);
			Long id = null;
			if (list != null && list.size() >= 1 && list.get(0) != null) {
				id = (Long) list.get(0) + 1;
			} else {
				id = new Long(1);
			}
			esfemppost.setIemployeepostsid(id);
			esfemppost.setIemployeeid(maxid + 1);
			esfemppost.setIversion(new Long(57));
			this.save(esfemppost);
		}

		syslog.setStlg("0019");
		syslog.setBrief("员工：" + employee.getIemployeeid());
		syslog.setNote("添加员工：" + employee.getEmpid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	/**
	 * * Describe:更新员工信息
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#updateEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang Date:2011-9-15
	 */
	public void updateEmployee(Esfemployeetab employee, String[] ipostsids,
			Syslog syslog) {
		Esfemployeetab emp = (Esfemployeetab) super.get(Esfemployeetab.class,
				employee.getIemployeeid());
		emp.setBissysuser(employee.getBissysuser());
		emp.setByloginitype(employee.getByloginitype());
		emp.setByisuse(employee.getByisuse());
		emp.setSzemployeename(employee.getSzemployeename());
		emp.setDtbirthdate(employee.getDtbirthdate());
		emp.setSzcardid(employee.getSzcardid());
		emp.setSzcellphone(employee.getSzcellphone());
		emp.setIsex(employee.getIsex());
		emp.setBismarry(employee.getBismarry());
		emp.setSznation(employee.getSznation());
		emp.setIborthaddress(employee.getIborthaddress());
		emp.setSzaccountlocation(employee.getSzaccountlocation());
		emp.setSzhomeaddress(employee.getSzhomeaddress());
		emp.setSzschool(employee.getSzschool());
		emp.setDtgraduatedate(employee.getDtgraduatedate());
		emp.setEducational(employee.getEducational());
		emp.setMajor(employee.getMajor());
		emp.setSzpost(employee.getSzpost());
		emp.setProfessional(employee.getProfessional());
		emp.setSzemail(employee.getSzemail());
		emp.setDtentrydate(employee.getDtentrydate());
		emp.setSzmemo(employee.getSzmemo());
		emp.setPhotoupid(employee.getPhotoupid());
		String hql = " from Esfemployeepoststab es where es.iemployeeid="
				+ employee.getIemployeeid();
		List lst = this.find(hql);
		if (lst.size() > 0 && lst != null) {
			for (int i = 0; i < lst.size(); i++) {
				Esfemployeepoststab esf = (Esfemployeepoststab) lst.get(i);
				this.delete(esf);
			}
		}

		if (ipostsids != null && ipostsids.length > 0) {
			for (int i = 0; i < ipostsids.length; i++) {
				Esfemployeepoststab esfemppost = new Esfemployeepoststab();
				esfemppost.setIpostsid(new Long(ipostsids[i]));

				String sql = "select max(iemployeepostsid) from Esfemployeepoststab";
				List list = this.find(sql);
				Long id = null;
				if (list != null && list.size() >= 1 && list.get(0) != null) {
					id = (Long) list.get(0) + 1;
				} else {
					id = new Long(1);
				}
				esfemppost.setIemployeepostsid(id);
				esfemppost.setIemployeeid(employee.getIemployeeid());
				esfemppost.setIversion(new Long(57));
				this.save(esfemppost);
			}
		}
		this.update(emp);

		String psql = " from Opcemployeecardtab o where iemployeeid="
				+ employee.getIemployeeid();
		List oplist = this.find(psql);
		if (oplist != null && oplist.size() > 0) {
			for (int i = 0; i < oplist.size(); i++) {
				Opcemployeecardtab op = (Opcemployeecardtab) oplist.get(i);
				if (emp.getByisuse() == 0) {
					op.setByticketstate("1");
				} else {
					op.setByticketstate("0");
				}
				this.update(op);
			}
		}
		syslog.setStlg("0020");
		syslog.setBrief("员工：" + employee.getIemployeeid());
		syslog.setNote("修改员工：" + emp.getEmpid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * * Describe:删除员工信息
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#deleteEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang Date:2011-9-23
	 */
	public void deleteEmployee(Esfemployeetab employee, Syslog syslog) {
		String hsql = " from Syslog where iemployeeid="
				+ employee.getIemployeeid();
		List syslist = this.find(hsql);
		if (syslist != null && syslist.size() > 0) {
			for (int x = 0; x < syslist.size(); x++) {
				Syslog log = (Syslog) syslist.get(x);
				this.delete(log);
			}
		}

		String msql = " from Hsyslog where iemployeeid="
				+ employee.getIemployeeid();
		List hsyslist = this.find(msql);
		if (hsyslist != null && hsyslist.size() > 0) {
			for (int x = 0; x < hsyslist.size(); x++) {
				Hsyslog log = (Hsyslog) hsyslist.get(x);
				this.delete(log);
			}
		}

		String sql = " from Esfemployeepoststab es where es.iemployeeid="
				+ employee.getIemployeeid();
		List list = this.find(sql);
		for (int i = 0; i < list.size(); i++) {
			Esfemployeepoststab emp = (Esfemployeepoststab) list.get(i);
			this.delete(emp);
		}
		String psql = " from Opcemployeecardtab o where iemployeeid="
				+ employee.getIemployeeid();
		List oplist = this.find(psql);
		if (oplist != null && oplist.size() > 0) {
			for (int i = 0; i < oplist.size(); i++) {
				Opcemployeecardtab op = (Opcemployeecardtab) oplist.get(i);
				String opsql = " from Opcemployeecardsubtab where iemployeecardid="
						+ op.getIemployeecardid();
				List opslist = this.find(opsql);
				for (int j = 0; j < opslist.size(); i++) {
					Opcemployeecardsubtab ops = (Opcemployeecardsubtab) opslist
							.get(j);
					this.delete(ops);
				}
				this.delete(op);
			}
		}
		syslog.setStlg("0021");
		syslog.setBrief("员工：" + employee.getIemployeeid());
		syslog.setNote("删除员工：" + employee.getEmpid() + "  "
				+ employee.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		this.delete(employee);
	}

	/**
	 * * Describe:根据部门ID获取岗位列表
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getPostlist(java.lang.Long)
	 * @param deptid
	 * @return
	 * @author yangguang Date:2011-9-22
	 */
	public List<Esppoststab> getPostlist(Long deptid) {
		String hql = " from Esppoststab  where ipostsid in (select depost.ipostsid as ipostsid from Esfdeptpoststab depost where depost.ideptid="
				+ deptid + ")";
		return this.find(hql);
	}

	/**
	 * * Describe:获取员工岗位关联的岗位信息
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getPostEmployeelist(java.lang.Long)
	 * @param iemployeepostid
	 * @return
	 * @author yangguang Date:2011-9-22
	 */
	public List<Esppoststab> getPostEmployeelist(Long iemployeepostid) {
		String hql = " from Esppoststab  where ipostsid in (select pe.ipostsid as ipostsid from Esfemployeepoststab pe where pe.iemployeeid="
				+ iemployeepostid + ")";
		return this.find(hql);
	}

	/**
	 * * Describe:从客源地表中获取籍贯信息
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getSourceregion()
	 * @return
	 * @author yangguang Date:2011-9-23
	 */
	public List<Galsourceregiontab> getSourceregion() {
		// String
		// sql="select gs.iregionalid as iregionalid,gs.szregionalname as szregionalname,gs.ilevel as ilevel from Galsourceregiontab gs where gs.byisuse=1 start with gs.iregionalid in (select es.iregionalid from Galsourceregiontab es  where es.ilevel=1 and es.byisuse=1 ) connect by prior  gs.iregionalid=gs.iparentid ";
		// return this.getSession().createSQLQuery(sql).list();
		String sql = " from Galsourceregiontab where ilevel=2 and irootid = (select iregionalid from Galsourceregiontab where szregionalname='中国') ";
		return this.find(sql);
	}

	public List<Galsourceregiontab> getSourceregionList() {
		// String
		// sql="select gs.iregionalid as iregionalid,gs.szregionalname as szregionalname,gs.ilevel as ilevel from Galsourceregiontab gs where gs.byisuse=1 start with gs.iregionalid in (select es.iregionalid from Galsourceregiontab es  where es.ilevel=1 and es.byisuse=1 ) connect by prior  gs.iregionalid=gs.iparentid ";
		// return this.getSession().createSQLQuery(sql).list();
		String sql = " from Galsourceregiontab ";
		return this.find(sql);
	}

	/**
	 * 
	 * Describe:组织树状结构 籍贯 下级
	 * 
	 * @auth:lijingrui
	 * @param regionid
	 * @return return:List Date:2011-9-28
	 */
	public List getSourceRegionJson(Long regionid) {
		String hsql = " from Galsourceregiontab where iparentid=" + regionid;
		return this.find(hsql);
	}

	/**
	 * * Describe:添加员工的时候 判断是否同名
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#genEmpId(java.lang.String)
	 * @param empid
	 * @return
	 * @author yangguang Date:2011-9-23
	 */
	public boolean genEmpId(String empid) {
		String sql = "select count(es.iemployeeid) from Esfemployeetab es where es.empid='"
				+ empid + "'";
		List list = super.find(sql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * * Describe:根据员工ID查看员工基本信息
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#reviters(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author yangguang Date:2011-9-24
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esfemployeetab reviters(Long iemployeeid)
			throws IllegalAccessException, InvocationTargetException {
		String hql = "select new map(emp.iemployeeid as iemployeeid,emp.bissysuser as bissysuser,emp.szpassword as szpassword,emp.empid as empid,emp.szemployeename as szemployeename,emp.isex as isex,emp.byisuse as byisuse,emp.szcardid as szcardid,emp.dtbirthdate as dtbirthdate,emp.bismarry as bismarry,emp.dtentrydate as dtentrydate"
				+ ",emp.szcellphone as szcellphone,emp.szemail as szemail,emp.szaccountlocation as szaccountlocation,emp.szhomeaddress as szhomeaddress,emp.szschool as szschool,emp.dtgraduatedate as dtgraduatedate,emp.szmemo as szmemo,emp.photoupid as photoupid,emp.dnum as dnum,emp.ztimes as ztimes,emp.logintime as logintime,emp.ipaddress as ipaddress,sys1.pmva as byloginitype,sys2.pmva as educational,sys3.pmva as major,"
				+ "sys4.pmva as sznation,sys5.pmva as szpost,sys6.pmva as professional,emp.iborthaddress as iborthaddress ) from Esfemployeetab emp,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4,Sysparv5 sys5,Sysparv5 sys6 where emp.byloginitype=sys1.id.pmcd and sys1.id.pmky='DLTP' and sys2.id.pmcd=emp.educational and sys2.id.pmky='XLTP' and sys3.id.pmcd=emp.major and sys3.id.pmky='ZYTP' and "
				+ "sys4.id.pmcd=emp.sznation and sys4.id.pmky='MNZH'and sys5.id.pmcd=emp.szpost and sys5.id.pmky='ZWTP'and sys6.id.pmcd=emp.professional and sys6.id.pmky='ZCTP'and emp.iemployeeid="
				+ iemployeeid;
		List list = super.find(hql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esfemployeetab employee = new Esfemployeetab();
			BeanUtils.populate(employee, (Map) list.get(0));
			return employee;
		}
	}

	/**
	 * * Describe:初始化密码保存
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#updateEmppassword(com.ectrip.model.employee.Esfemployeetab,
	 *      java.lang.String)
	 * @param emp
	 * @param pwd
	 * @author yangguang Date:2011-9-26
	 */
	public void updateEmppassword(Esfemployeetab emp, String pwd) {
		Esfemployeetab esf = (Esfemployeetab) this.get(Esfemployeetab.class,
				emp.getIemployeeid());
		esf.setSzpassword(pwd);
		this.update(esf);
	}

	/**
	 * * Describe:登录次数初始化
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#updateEmpdnum(java.lang.Long)
	 * @param iemployeeid
	 * @author lijingrui Date:2011-10-17
	 */
	public void updateEmpdnum(Long iemployeeid) {
		Esfemployeetab esf = (Esfemployeetab) this.get(Esfemployeetab.class,
				iemployeeid);
		esf.setDnum(new Long(5));
		esf.setZtimes(new Long(0));
		this.update(esf);
	}

	/**
	 * 得到有效的且有景区销售权限的员工列表* Describe:
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getEmployeeAllList(java.lang.Long)
	 * @param employeeId登录用户id
	 * @return
	 * @author huangyuqi Date:2011-10-9
	 */
	public List getEmployeeAllList(Long employeeId) {
		List list = new ArrayList();
		String sql = " from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
				+ employeeId + ")";
		String hsql = "";
		List companylist = this.find(sql);
		if (companylist.size() >= 1) {
			Galcompanyinfotab company = (Galcompanyinfotab) companylist.get(0);
			if ("01".equals(company.getCompanytype())) {// 平台管理
				hsql = " from Esfemployeetab where byisuse=1 and iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
			}
			if ("02".equals(company.getCompanytype())) {// 景区企业
				hsql = " from Esfemployeetab where byisuse=1 and icompanyinfoid in (select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
						+ employeeId
						+ ") and iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
			}
		}

		list = this.find(hsql);

		return list;
	}

	/**
	 * 得到所有的且有景区销售权限的员工列表* Describe:
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getEmployeeAllList(java.lang.Long)
	 * @param employeeId登录用户id
	 * @return
	 * @author huangyuqi Date:2011-10-9
	 */
	public List getEmployeeAll(Long employeeId) {
		List list = new ArrayList();
		String sql = " from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
				+ employeeId + ")";
		String hsql = "";
		List companylist = this.find(sql);
		if (companylist.size() >= 1) {
			Galcompanyinfotab company = (Galcompanyinfotab) companylist.get(0);
			if ("01".equals(company.getCompanytype())) {// 平台管理
				hsql = " from Esfemployeetab where  iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
			}
			if ("02".equals(company.getCompanytype())) {// 景区企业
				hsql = " from Esfemployeetab where  icompanyinfoid in (select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
						+ employeeId
						+ ") and iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
			}
		}

		list = this.find(hsql);

		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getEmployeeBtnDuty(String empId) throws Exception {
		String hql = "select duty.szdutycode as szdutycode,duty.szdutyname as szdutyname \r\n" + 
				"from espdutytab duty,esfemployeepoststab employeepost,esfemployeetab employee,esppostsbtndutytab postduty \r\n" + 
				"where employee.empid = '"+empId+"' and employee.iemployeeid = employeepost.iemployeeid \r\n" + 
				"and employeepost.ipostsid = postduty.bipostsid and postduty.bidutyid = duty.idutyid";
		List list = this.findBySqlToMap(hql, new Object[] {});
		return list;
	}
	@Override
	public Esfemployeetab getEmployeeInfoByEmpIdAndPwd(String empId, String empPwd) throws Exception {
		Esfemployeetab esfemployeetab = null;
		String hql = "from Esfemployeetab where  empid = ? and szpassword = ? ";
		List<?> employeeTabList = this.find(hql, new Object[] {empId,empPwd});
		if(employeeTabList != null && employeeTabList.size() > 0) {
			esfemployeetab = (Esfemployeetab) employeeTabList.get(0);
		}
		return esfemployeetab;
	}
	@Override
	public Esfemployeetab getEmployeeInfoByEmpId(String empId) throws Exception {
		Esfemployeetab esfemployeetab = null;
		String hql = "from Esfemployeetab where  empid = ? ";
		List<?> employeeTabList = this.find(hql, new Object[] {empId});
		if(employeeTabList != null && employeeTabList.size() > 0) {
			esfemployeetab = (Esfemployeetab) employeeTabList.get(0);
		}
		return esfemployeetab;
	}
	
	@Override
	public List getEmployeeListByCondition(Long iemployeeid, Long icompanyinfoid, String szemployeename) {
		StringBuilder sql = new StringBuilder();
		sql.append("select new map(emp.iemployeeid as iemployeeid, emp.szemployeename as szemployeename, company.szcompanyname as szcompanyname) "
				+ " from Esfemployeetab emp, Galcompanyinfotab company "
				+ " where emp.icompanyinfoid=company.icompanyinfoid");
		
		
		//判断是否是平台企业
        Galcompanyinfotab company=(Galcompanyinfotab) get(Galcompanyinfotab.class, icompanyinfoid);
        if(company.getCompanytype().equals("02")){//景区企业
            sql.append(" and emp.icompanyinfoid="+company.getIcompanyinfoid() );
        }
		
		
		if(null != iemployeeid) {
			sql.append(" and emp.iemployeeid="+ iemployeeid);
		}
		if(null != szemployeename && !"".equals(szemployeename)) {
			sql.append(" and emp.szemployeename like '%"+ szemployeename +"%'");
		}
		
		List list = this.find(sql.toString());
		
		return list;
	}
	
	/*
	 * 根据员工id获取员工信息
	 * @see com.ectrip.sys.employee.dao.IEsfEmployeeTabDAO#getEmployeeListByIemployeeid(java.lang.String)
	 */
	public List getEmployeeListByIemployeeid(String iemployeeids,Long icompanyinfoid) {
		if(!"".equals(iemployeeids)&&null!=iemployeeids) {
			StringBuffer hql = new StringBuffer();
			hql.append("select new map(emp.iemployeeid as iemployeeid,emp.szemployeename as szemployeename) from Esfemployeetab emp where emp.iemployeeid in ("+iemployeeids+")");
			if(null!=icompanyinfoid) {
				hql.append(" and emp.icompanyinfoid="+icompanyinfoid);
			}
			List EsfemployeetabList = this.find(hql.toString());
			return EsfemployeetabList;
		}
		return null;
	}
}
