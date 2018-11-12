package com.ectrip.sys.employee.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IEsfEmployeeTabDAO;
import com.ectrip.sys.employee.service.IEsfEmployeeTabService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Syslog;
@Service
public class EsfEmployeeTabService extends GenericService implements IEsfEmployeeTabService{
	
	
	private IEsfEmployeeTabDAO employeeDao;
	@Autowired
	public void setEmployeeDao(IEsfEmployeeTabDAO employeeDao) {
		this.employeeDao = employeeDao;
		super.setGenericDao(employeeDao);   
	}
	/**
	 * Describe:根据部门ID 职责ID 员工名称 查询员工列表并分页
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#getEmployeeList(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param deptid
	 * @param ipostsid
	 * @param szemployeename
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author yangguang
	 * Date:2011-9-13
	 */
	public PaginationSupport getEmployeeList(Long deptid,String szemployeename, int pageSize,
			int startIndex, String url) {
		return employeeDao.getEmployeeList(deptid,szemployeename, pageSize, startIndex, url);
	}

	
	/**
	 * 保存员工对象*
	 * Describe:
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#saveEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang
	 * Date:2011-9-15
	 */
	public void saveEmployee(Esfemployeetab employee,String[] ipostsids,Syslog syslog) {
		employeeDao.saveEmployee(employee,ipostsids,syslog);
	}
	
	/**
	 * *
	 * Describe:更新员工信息
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#updateEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang
	 * Date:2011-9-15
	 */
	public void updateEmployee(Esfemployeetab employee,String[] ipostsids,Syslog syslog) {
		employeeDao.updateEmployee(employee, ipostsids,syslog);
	}
	
	/**
	 * *
	 * Describe:删除员工信息
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#deleteEmployee(com.ectrip.model.employee.Esfemployeetab)
	 * @param employee
	 * @author yangguang
	 * Date:2011-9-26
	 */
	public void deleteEmployee(Esfemployeetab employee,Syslog syslog) {
		employeeDao.deleteEmployee(employee,syslog);
	}
	
	/**
	 * 根据部门ID获取岗位列表*
	 * Describe:
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#getPostlist(java.lang.Long)
	 * @param deptid
	 * @return
	 * @author yangguang
	 * Date:2011-9-16
	 */
	public List<Esppoststab> getPostlist(Long deptid) {
		return employeeDao.getPostlist(deptid);
	}
	
	/**
	 * *
	 * Describe:获取员工岗位关联的岗位信息
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getPostEmployeelist(java.lang.Long)
	 * @param iemployeepostid
	 * @return
	 * @author yangguang
	 * Date:2011-9-22
	 */
	public List<Esppoststab> getPostEmployeelist(Long iemployeepostid) {
		return employeeDao.getPostEmployeelist(iemployeepostid);
	}
	
	/**
	 * *
	 * Describe:从客源地表中获取籍贯信息
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#getSourceregion()
	 * @return
	 * @author yangguang
	 * Date:2011-9-23
	 */
	public List<Galsourceregiontab> getSourceregion() {
		return employeeDao.getSourceregion();
	}
	
	/**
	 * 
	 * Describe:组织树状结构  籍贯 下级
	 * @auth:lijingrui
	 * @param regionid
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List getSourceRegionJson(Long regionid){
		return employeeDao.getSourceRegionJson(regionid);
	}

	/**
	 * *
	 * Describe:添加员工的时候 判断是否同名
	 * @see com.ectrip.system.employee.dao.idao.IEsfEmployeeTabDAO#genEmpId(java.lang.String)
	 * @param empid
	 * @return
	 * @author yangguang
	 * Date:2011-9-23
	 */
	public boolean genEmpId(String empid) {
		return employeeDao.genEmpId(empid);
	}
	
	/**
	 * *
	 * Describe:根据员工ID查看员工基本信息
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#reviters(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author yangguang
	 * Date:2011-9-24
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Esfemployeetab reviters(Long iemployeeid) throws IllegalAccessException, InvocationTargetException{
		return employeeDao.reviters(iemployeeid);
	}
	
	/**
	 * *
	 * Describe:初始化密码
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#updateEmppassword(com.ectrip.model.employee.Esfemployeetab, java.lang.String)
	 * @param emp
	 * @param pwd
	 * @author yangguang
	 * Date:2011-9-26
	 */
	public void updateEmppassword(Esfemployeetab emp, String pwd) {
		employeeDao.updateEmppassword(emp, pwd);
	}
	
	/**
	 * *
	 * Describe:登录次数初始化
	 * @see com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService#updateEmpdnum(java.lang.Long)
	 * @param iemployeeid
	 * @author lijingrui
	 * Date:2011-10-17
	 */
	public void updateEmpdnum(Long iemployeeid) {
		employeeDao.updateEmpdnum(iemployeeid);
	}
	
	/**
	 *  得到有效的员工列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeid登录用户id
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getEmployeeAllList(Long employeeid){
		return employeeDao.getEmployeeAllList(employeeid);
	}
	
	/**
	 *  得到所有的员工列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeid登录用户id
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getEmployeeAll(Long employeeid){
		return employeeDao.getEmployeeAllList(employeeid);
	}
	
	/**
	 * 
	 */
	public List getEmployeeBtnDuty(String empId) throws Exception {
		
		return employeeDao.getEmployeeBtnDuty(empId);
	}



	@Override
	public Esfemployeetab getEmployeeInfoByEmpIdAndPwd(String empId, String empPwd) throws Exception {
		
		return employeeDao.getEmployeeInfoByEmpIdAndPwd(empId, empPwd);
	}
	@Override
	public Esfemployeetab getEmployeeInfoByEmpId(String empId) throws Exception {
		
		return employeeDao.getEmployeeInfoByEmpId(empId);
	}
	@Override
	public List getEmployeeListByCondition(Long iemployeeid, Long icompanyinfoid, String szemployeename) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeListByCondition(iemployeeid,icompanyinfoid,szemployeename);
	}
	@Override
	public List getEmployeeListByIemployeeid(String iemployeeids,Long icompanyinfoid) {
		return employeeDao.getEmployeeListByIemployeeid(iemployeeids,icompanyinfoid);
	}
}

