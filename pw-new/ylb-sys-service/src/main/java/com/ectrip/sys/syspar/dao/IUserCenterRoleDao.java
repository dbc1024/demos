package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenterrole;
/**
 * 
* @ClassName: IUserCenterRoleDao 
* @Description: 前台用户中心 角色 idao
* @author Dicky
* @date 2011-11-22 下午03:36:41 
*
 */
public interface IUserCenterRoleDao extends IGenericDao{
	public PaginationSupport getlistRole(String sql,int pageSize,int startIndex, String url); 
	public void insertRole(Usercenterrole role,Syslog syslog);
	public void updateRole(Usercenterrole role,Syslog syslog);
	public void deleteRole(Usercenterrole role,Syslog syslog);
	public Usercenterrole getRoledetaliview(String roleid);
	public boolean getRoleByName(String roleName);
	public List getAllRole();
	public Usercenterrole getRoleByNameTwo(String roleName);
}

