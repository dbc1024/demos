package com.ectrip.sys.employee.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfdepttab;

public interface IESFDeptTabService extends IGenericService{
	//添加新部门    
	public void insertDept(Esfdepttab dept,Long companyId,Long parentid,List postsList);
	//修改部门信息
	public void update(Esfdepttab dept,Object id, List selList);
	
	public List allPosts(String comid);//岗位列表
	public List allPostsById(Long deptid);
	//删除部门
	public void deleteDept(Class cla,Object id);
	public List selectEmpFromDept(Long did);
	public List selectNextDept(Long deptid);
	
	//查询数据库中是否有已存在的部门
	public List selectDeptByName(String dname);
	
	//分页
	public PaginationSupport findPage(Long deptid,Long comid,int pageSize,int startIndex, String url);
	//显示上一级部门
	public PaginationSupport findPage2(Long levelid,Long comid,int pageSize, int startIndex, String url) ;
	//模糊查询 
	public PaginationSupport findPage3(String queryid,String queryMess,Esfdepttab dept,int pageSize, int startIndex, String url);
}
