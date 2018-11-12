package com.ectrip.sys.employee.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.employee.dao.IEmployeeDAO;
import com.ectrip.sys.model.employee.Employee;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class EmployeeDAO extends GenericDao implements IEmployeeDAO {
	
/**
 * 根据用户名查询用户信息*
 * Describe:
 * @see com.ectrip.employee.dao.idao.IEmployeeDAO#retrieve(java.lang.String)
 * @param empid
 * @return
 * @author huangyuqi
 * Date:2011-11-10
 */
	public Employee retrieve(String empid) {
		String sql = " from Employee  e  where e.empid='" + empid + "' ";

		Employee employee = null;
		try {
			List emplist = this.find(sql);

			if (emplist != null && emplist.size() > 0) {
				employee = (Employee) emplist.get(0);
				
				if (employee.getEmptype() == 1) {// 表示服务商用户
					
					String sql1 = "select p.iscenicid,p.szscenicname from Companyscenic coms,Esbscenicareatab p where p.iscenicid = coms.iscenicid and coms.icompanyinfoid= "+employee.getIcompanyinfoid();
					List emplist2 = this.find(sql1);
					
					StringBuffer empdno = new StringBuffer(); 
					if (emplist2 != null && emplist2.size() > 0) {
						for (int i = 0; i < emplist2.size(); i++) {
							String[] stremp = (String[]) emplist2.get(i);
							if(i==emplist2.size()-1){
								empdno.append(stremp[0]);
							}else{
								empdno.append(stremp[0]+"'");
							}
						}
						
						employee.setEmpdno(empdno.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}
	
	/**
	 * 修改用户信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param e
	 * @param syslog
	 * return:void
	 * Date:2011-11-10
	 */
	public void updateEmployeeInfo(Employee e,Syslog syslog){
		
		this.update(e);
		
		syslog.setIemployeeid(e.getIemployeeid());
		syslog.setNote("用户"+e.getEmpid()+"修改用户信息");
		syslog.setStlg("0020");
		syslog.setBrief("用户"+e.getEmpid()+"修改用户信息：用户姓名："+e.getSzemployeename()+",电话:"+e.getSzcellphone()+",邮箱："+e.getSzemail()+",地址："+e.getAddr());
		syslog.setLogdatetime(Tools.getDays());
		
		this.save(syslog);
	}
	
	/**
	 * 修改后台用户认证码
	 * Describe:
	 * @auth:huangyuqi
	 * @param e
	 * @param syslog
	 * return:void
	 * Date:2011-11-10
	 */
	public void updateEmployeeRzPwd(Employee e,Syslog syslog){
		
		this.update(e);
		
		syslog.setIemployeeid(e.getIemployeeid());
		syslog.setNote("用户"+e.getEmpid()+"修改认证码");
		syslog.setStlg("0159");
		syslog.setBrief("用户"+e.getEmpid()+"修改认证码");
		syslog.setLogdatetime(Tools.getDays());
		
		this.save(syslog);
	}
	
	/**
	 * 修改后台用户密码
	 * Describe:
	 * @auth:huangyuqi
	 * @param e
	 * @param syslog
	 * return:void
	 * Date:2011-11-10
	 */
	public void updateEmployeePassword(Employee e,Syslog syslog){
		this.update(e);
		
		syslog.setIemployeeid(e.getIemployeeid());
		syslog.setNote("用户"+e.getEmpid()+"修改密码");
		syslog.setStlg("0160");
		syslog.setBrief("用户"+e.getEmpid()+"修改密码");
		syslog.setLogdatetime(Tools.getDays());
		
		this.save(syslog);
	}
	
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicid(Long scenicId){
		String hql = "from Esfemployeetab e where e.byisuse=1 and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))";
	
		List<Esfemployeetab> list = this.find(hql, new Object[]{scenicId});
		
		return list;
	}
	
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicids(Long iscenicid,String keys){
		String hql = "from Esfemployeetab e where e.byisuse=1 and e.szemployeename like ? and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))";
	
		List<Esfemployeetab> list = this.find(hql, new Object[]{keys, iscenicid});
		
		return list;
	}
	
	
}
