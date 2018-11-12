package com.ectrip.sys.employee.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IESFDeptTabDAO;
import com.ectrip.sys.employee.service.IESFDeptTabService;
import com.ectrip.sys.model.employee.Esfdepttab;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
/**
 * @author  yangyang
 * @version 部门管理操作类
 */
@Service
public class ESFDeptTabService extends GenericService implements IESFDeptTabService{
	

	private IESFDeptTabDAO deptTabDao;
	@Autowired
	public void setDeptTabDao(IESFDeptTabDAO deptTabDao) {
		super.setGenericDao(deptTabDao);
		this.deptTabDao = deptTabDao;
	}
	/**
	 * 功能 ： 删除部门信息<BR/>
	 *       (Warning:  需判断部门下是否有员工或下属部门，若有，则不能删除)
	 */
	@SuppressWarnings("rawtypes")
	public void deleteDept(Class cla, Object id) {
		
		deptTabDao.deleteDept(cla, id);
	}
	//查询部门下是否还有员工
	/**
	 * 功能 ： 查询部门下是否还有员工
	 */
	public List selectEmpFromDept(Long did){
		//Esfdepttab edept=(Esfdepttab)deptTabDao.get(Esfdepttab.class, did);
		//String sql="select new map(dept.ideptid as ideptid,dept.szdeptname as szdeptname,emp.iemployeeid as iemployeeid,emp.szemployeename as szemployeename) from Esfdepttab dept,Esfemployeetab emp where dept.ideptid="+did+" and dept.ideptid=emp.esfdepttab.ideptid";
		return deptTabDao.selectEmpFromDept(did);
	}
	//查询部门下是否还有下属部门
	/**
	 * 功能 ： 查询部门下是否还有下属部门
	 */
	public List selectNextDept(Long deptid){
		String sql=" from Esfdepttab where iparentid="+deptid;
		return deptTabDao.find(sql);
	}
	//添加一新部门
	/**
	 * 功能 ： 添加一新部门
	 * @param  dept      要添加的新部门对象
	 * @param  companyId 新部门的所属公司ID
	 * @param  parentid  新部门的所属部门ID
	 * @param  postsList 新部门所添加的岗位集合
	 */
	@SuppressWarnings("rawtypes")
	public void insertDept(Esfdepttab dept,Long companyId,Long parentid,List postsList) {
		deptTabDao.insertDept(dept, companyId, parentid, postsList);
	}

	/**
	 * 功能 ： 修改部门信息
	 * @param   dept    要修改的部门对象 
	 * @param   id      部门ID
	 * @param   selList 部门下的岗位集合
	 */
	@SuppressWarnings("rawtypes")
	public void update(Esfdepttab dept, Object id, List selList) {
		
		deptTabDao.update(dept, id, selList);
	}
	
	//所有部门列表
	/**
	 * 功能 ： 显示该公司下的所有部门（不显示已删除的）
	 * @param   deptid   部门ID
	 * @param   comid    公司ID
	 */
	public PaginationSupport findPage(Long deptid,Long comid,int pageSize, int startIndex, String url) {
		
		return deptTabDao.findPage(deptid, comid, pageSize, startIndex, url);
	}
	//显示上一级部门
	/**
	 * 功能 ： 显示上一层级的部门
	 * @param  levelid   层级
	 * @param  comid     公司ID
	 */
	public PaginationSupport findPage2(Long levelid,Long comid,int pageSize, int startIndex, String url) {
		
		return deptTabDao.findPage2(levelid, comid, pageSize, startIndex, url);
	}
	//按条件模糊查询
	/**
	 * 功能 ： 按条件模糊查询
	 * @param queryid   部门ID
	 * @param queryMess 部门名称中所包含的字符
	 * @param dept      在当前层级查询时，上一层级的部门对象
	 */
	public PaginationSupport findPage3(String queryid,String queryMess,Esfdepttab dept,int pageSize, int startIndex, String url) {

		return deptTabDao.findPage3(queryid, queryMess, dept, pageSize, startIndex, url);
	}
	
	/**
	 * 功能 ：  显示对应的岗位（是景区企业对应景区岗位；平台企业对应平台岗位）
	 * @param 公司ID
	 */
	public List allPosts(String comid){
		return deptTabDao.allPosts(comid);
	}
	/**
	 * 功能 ： 按部门ID列出关系表中的数据
	 * @param  deptid  部门ID
	 */
	public List allPostsById(Long deptid){
		return deptTabDao.allPostsById(deptid);
	}
	/** 
	 * 查询数据库中是否有已存在的部门
	 */
	public List selectDeptByName(String dname) {
		
		return deptTabDao.selectDeptByName(dname);
	}


}
