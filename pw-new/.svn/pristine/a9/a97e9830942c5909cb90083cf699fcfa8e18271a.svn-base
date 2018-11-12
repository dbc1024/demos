package com.ectrip.sys.employee.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IEsfEmployeeTabService extends IGenericService{
	
	/**
	 * Describe:根据部门ID 职责ID 员工名称 查询员工列表并分页
	 * @auth:yangguang
	 * @param deptid 部门ID
	 * @param ipostsid 职责
	 * @param szemployeename 员工名称(模糊查询,没有则全部)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-13
	 */
	public PaginationSupport getEmployeeList(Long deptid, String szemployeename, int pageSize,
			int startIndex, String url);
	
	/**
	 * 
	 * Describe:保存员工信息
	 * @auth:yangguang
	 * @param employee 员工对象
	 * return:void
	 * Date:2011-9-15
	 */
	public void saveEmployee(Esfemployeetab employee,String[] ipostsids,Syslog syslog);
	
	
	/**
	 * 
	 * Describe:更新员工信息
	 * @auth:yangguang
	 * @param employee
	 * return:void
	 * Date:2011-9-15
	 */
	public void updateEmployee(Esfemployeetab employee,String[] ipostsids,Syslog syslog);
	
	
	/**
	 * 
	 * Describe:删除员工信息
	 * @auth:lijingrui
	 * @param employee
	 * return:void
	 * Date:2011-9-23
	 */
	public void deleteEmployee(Esfemployeetab employee,Syslog syslog);
	
	
	/**
	 * 根据部门ID获取岗位列表
	 * Describe:
	 * @auth:yangguang
	 * @param deptid
	 * @return
	 * return:List
	 * Date:2011-9-16
	 */
	public List<Esppoststab> getPostlist(Long deptid);
	
	/**
	 * 
	 * Describe:从客源地表中获取籍贯信息 某个下级的信息
	 * @auth:lijingrui
	 * @param regionid
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List getSourceRegionJson(Long regionid);
	
	/**
	 * 
	 * Describe:获取员工岗位关联的岗位信息
	 * @auth:lijingrui
	 * @param iemployeepostid
	 * @return
	 * return:List
	 * Date:2011-9-22
	 */
	public List<Esppoststab> getPostEmployeelist(Long iemployeepostid);
	
	
	/**
	 * 
	 * Describe:从客源地表中获取籍贯信息
	 * @auth:lijingrui
	 * @return
	 * return:List<Galsourceregiontab>
	 * Date:2011-9-23
	 */
	public List<Galsourceregiontab> getSourceregion();
	
	/**
	 * 
	 * Describe:添加员工的时候 判断是否同名
	 * @auth:lijingrui
	 * @param empid
	 * @return
	 * return:boolean
	 * Date:2011-9-23
	 */
	public boolean genEmpId(String empid);
	
	/**
	 * 
	 * Describe:根据员工ID查看员工基本信息
	 * @auth:lijingrui
	 * @param iemployeeid
	 * @return
	 * return:Esfemployeetab
	 * Date:2011-9-24
	 */
	public Esfemployeetab reviters(Long iemployeeid) throws IllegalAccessException, InvocationTargetException ;
	
	
	/**
	 * 
	 * Describe:初始化密码
	 * @auth:lijingrui
	 * @param emp
	 * @param pwd
	 * return:void
	 * Date:2011-9-26
	 */
	public void updateEmppassword(Esfemployeetab emp,String pwd);
	
	/**
	 * 
	 * Describe:登陆次数初始化
	 * @auth:lijingrui
	 * @param iemployee
	 * return:void
	 * Date:2011-10-17
	 */
	public void updateEmpdnum(Long iemployeeid);
	
	/**
	 *  得到有效的员工列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeid登录用户id
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getEmployeeAllList(Long employeeid);
	
	
	/**
	 * 得到所有出票员工列表
	 * Describe:
	 * @auth:yuanchengjun
	 * @param employeeid
	 * @return
	 * return:List
	 * Date:2012-8-23
	 */
	public List getEmployeeAll(Long employeeid);
	
	/**
	 * 描述：获取员工的按钮权限
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public List getEmployeeBtnDuty(String empId) throws Exception;
	
	/**
	 * 描述：根据登录名和密码获取员工信息
	 * @param empId
	 * @param empPwd
	 * @return
	 * @throws Exception
	 */
	public Esfemployeetab getEmployeeInfoByEmpIdAndPwd(String empId,String empPwd) throws Exception;
	
	/**
	 * 描述：根据登录名获取员工信息
	 * @param empId
	 * @param empPwd
	 * @return
	 * @throws Exception
	 */
	public Esfemployeetab getEmployeeInfoByEmpId(String empId) throws Exception;
	
	public List getEmployeeListByCondition(Long iemployeeid, Long icompanyinfoid, String szemployeename);

	public List getEmployeeListByIemployeeid(String iemployeeids,Long icompanyinfoid);
}