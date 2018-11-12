package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenterrole;
import com.ectrip.sys.syspar.dao.IUserCenterRoleDao;
import com.ectrip.sys.syspar.service.IUserCenterRoleService;

/**
 * 
* @ClassName: UserCenterRoleService 
* @Description: 用户中心 菜单 业务 接口实现类
* @author Dicky
* @date 2011-11-22 下午03:40:50 
*
 */
public class UserCenterRoleService implements IUserCenterRoleService {
	IUserCenterRoleDao userCenterRoleDAO;

	public IUserCenterRoleDao getUserCenterRoleDAO() {
		return userCenterRoleDAO;
	}

	public void setUserCenterRoleDAO(IUserCenterRoleDao userCenterRoleDAO) {
		this.userCenterRoleDAO = userCenterRoleDAO;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deleteRole
	 * </p>
	 * <p>
	 * Description: 删除角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#deleteRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void deleteRole(Usercenterrole role, Syslog syslog) {
		userCenterRoleDAO.deleteRole(role, syslog);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getRoledetaliview
	 * </p>
	 * <p>
	 * Description: 获取要删除的角色对象
	 * </p>
	 * 
	 * @param roleid
	 * @return
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#getRoledetaliview(java.lang.String)
	 */
	public Usercenterrole getRoledetaliview(String roleid) {
		return userCenterRoleDAO.getRoledetaliview(roleid);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getlistRole
	 * </p>
	 * <p>
	 * Description:角色对象分页
	 * </p>
	 * 
	 * @param sql
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#getlistRole(java.lang.String,
	 *      int, int, java.lang.String)
	 */
	public PaginationSupport getlistRole(String sql, int pageSize, int startIndex, String url) {
		return userCenterRoleDAO.getlistRole(sql, pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: insertRole
	 * </p>
	 * <p>
	 * Description:增加新角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#insertRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void insertRole(Usercenterrole role, Syslog syslog) {
		userCenterRoleDAO.insertRole(role, syslog);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateRole
	 * </p>
	 * <p>
	 * Description:修改角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#updateRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void updateRole(Usercenterrole role, Syslog syslog) {
		userCenterRoleDAO.updateRole(role, syslog);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getRoleByName
	 * </p>
	 * <p>
	 * Description: 根据名称 获取 用户中心 角色
	 * </p>
	 * 
	 * @param roleName
	 * @return
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#getRoleByName(java.lang.String)
	 */
	public boolean getRoleByName(String roleName) {
		return userCenterRoleDAO.getRoleByName(roleName);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getAllRole
	 * </p>
	 * <p>
	 * Description: 获取所有角色
	 * </p>
	 * 
	 * @return
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#getAllRole()
	 */
	public List getAllRole() {
		return userCenterRoleDAO.getAllRole();
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getRoleByNameTwo
	 * </p>
	 * <p>
	 * Description: 根据名称获取角色对象
	 * </p>
	 * 
	 * @param roleName
	 * @return
	 * @see com.ectrip.system.syspar.service.iservice.IUserCenterRoleService#getRoleByNameTwo(java.lang.String)
	 */
	public Usercenterrole getRoleByNameTwo(String roleName) {
		return userCenterRoleDAO.getRoleByNameTwo(roleName);
	}

}
