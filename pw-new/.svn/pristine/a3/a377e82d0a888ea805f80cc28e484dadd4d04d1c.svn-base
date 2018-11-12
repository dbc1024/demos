package com.ectrip.sys.syspar.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenterrole;
import com.ectrip.sys.syspar.dao.IUserCenterRoleDao;

/**
 * 
* @ClassName: UserCenterRoleDAO 
* @Description: 前台用户角色 dao
* @author Dicky
* @date 2011-11-22 下午03:35:25 
*
 */
public class UserCenterRoleDAO extends GenericDao implements IUserCenterRoleDao {
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deleteRole
	 * </p>
	 * <p>
	 * Description:删除角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#deleteRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void deleteRole(Usercenterrole role, Syslog syslog) {
		Usercenterrole hd = (Usercenterrole) super.get(Usercenterrole.class, role.getRoleid());
		this.delete(hd);

		syslog.setStlg("0042");
		syslog.setBrief("用户中心角色：" + role.getRoleid());
		syslog.setNote("删除用户中心角色：" + role.getRolename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
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
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#getRoledetaliview(java.lang.String)
	 */
	public Usercenterrole getRoledetaliview(String roleid) {
		String sql = " from Usercenterrole hs where hs.roleid=" + roleid;
		List list = this.find(sql);
		if (list.size() > 0) {
			return (Usercenterrole) list.get(0);
		}
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getlistRole
	 * </p>
	 * <p>
	 * Description: 角色对象分页
	 * </p>
	 * 
	 * @param sql
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#getlistRole(java.lang.String,
	 *      int, int, java.lang.String)
	 */
	public PaginationSupport getlistRole(String sql, int pageSize, int startIndex, String url) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Usercenterrole u where 1=1");
		if (sql != null && !sql.equals("")) {
			sb.append(" and u.rolename like '%" + sql + "%'");
		}
		return this.findPage(sb.toString(), pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: insertRole
	 * </p>
	 * <p>
	 * Description: 增加新角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#insertRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void insertRole(Usercenterrole role, Syslog syslog) {
		String sql = "select max(roleid) from Usercenterrole";
		List list = this.find(sql);
		Long id = null;
		if (list != null && list.size() >= 1 && list.get(0) != null) {
			id = (Long) list.get(0) + 1;
		} else {
			id = new Long(1);
		}
		role.setRoleid(id);
		this.save(role);

		syslog.setStlg("0040");
		syslog.setBrief("用户中心角色：" + role.getRoleid());
		syslog.setNote("添加用户中心角色：" + role.getRolename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateRole
	 * </p>
	 * <p>
	 * Description: 修改角色
	 * </p>
	 * 
	 * @param role
	 * @param syslog
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#updateRole(com.ectrip.model.syspar.Usercenterrole,
	 *      com.ectrip.model.syspar.Syslog)
	 */
	public void updateRole(Usercenterrole role, Syslog syslog) {
		this.update(role);
		syslog.setStlg("0041");
		syslog.setBrief("用户中心角色：" + role.getRoleid());
		syslog.setNote("修改用户中心角色：" + role.getRolename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
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
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#getRoleByName(java.lang.String)
	 */
	public boolean getRoleByName(String roleName) {
		String sql = " from Usercenterrole hs where hs.rolename='" + roleName + "'";
		List list = this.find(sql);
		if (list.size() > 0) {
			return true;
		}
		return false;
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
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#getAllRole()
	 */
	public List getAllRole() {
		String sql = " from Usercenterrole hs where 1=1";
		return this.find(sql);
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
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterRoleDao#getRoleByNameTwo(java.lang.String)
	 */
	public Usercenterrole getRoleByNameTwo(String roleName) {
		String sql = " from Usercenterrole hs where hs.rolename='" + roleName + "'";
		List list = this.find(sql);
		if (list.size() > 0) {
			return (Usercenterrole) list.get(0);
		}
		return null;
	}

}

