package com.ectrip.sys.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.sys.employee.dao.IEmployeeDAO;
import com.ectrip.sys.employee.service.IEmployeeService;
import com.ectrip.sys.model.employee.Employee;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
 
@Service
public class EmployeeService extends GenericService  implements IEmployeeService {
	IEmployeeDAO employeeDAO;

	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	@Autowired
	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
		setGenericDao(employeeDAO);
	}

	/**
	 * 根据用户名查询用户信息*
	 * Describe:
	 * @see com.ectrip.employee.service.iservice.IEmployeeService#retrieve(java.lang.String)
	 * @param empid
	 * @return
	 * @author huangyuqi
	 * Date:2011-11-10
	 */
	public Employee retrieve(String empid){
		return employeeDAO.retrieve(empid);
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
		employeeDAO.updateEmployeeRzPwd(e, syslog);
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
		employeeDAO.updateEmployeePassword(e, syslog);
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
		employeeDAO.updateEmployeeInfo(e,syslog);
	}
	
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicid(Long scenicId){
		return employeeDAO.getEsfemployeeByGalcompanyScenicid(scenicId);
	}
	
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicids(Long iscenicid,String keys){
		return employeeDAO.getEsfemployeeByGalcompanyScenicids( iscenicid, keys);
	}
}
