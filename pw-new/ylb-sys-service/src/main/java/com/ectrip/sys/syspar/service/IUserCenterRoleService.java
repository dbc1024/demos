package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenterrole;
/**
 * 
* @ClassName: IUserCenterRoleService 
* @Description: 用户中心  角色 业务接口
* @author Dicky
* @date 2011-11-22 下午03:37:48 
*
 */
public interface IUserCenterRoleService {
	public PaginationSupport getlistRole(String sql,int pageSize,int startIndex, String url); 
	public void insertRole(Usercenterrole role,Syslog syslog);
	public void updateRole(Usercenterrole role,Syslog syslog);
	public void deleteRole(Usercenterrole role,Syslog syslog);
	public Usercenterrole getRoledetaliview(String roleid);
	public boolean getRoleByName(String roleName);
	public Usercenterrole getRoleByNameTwo(String roleName);
	public List getAllRole();
}

