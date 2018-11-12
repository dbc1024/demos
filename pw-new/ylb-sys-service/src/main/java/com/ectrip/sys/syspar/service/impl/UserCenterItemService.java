package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenteritem;
import com.ectrip.sys.syspar.dao.IUserCenterItemDao;
import com.ectrip.sys.syspar.service.IUserCenterItemService;
/**
 * 
* @ClassName: UserCenterItemService 
* @Description: �û����� �˵� ҵ�� �ӿ�ʵ����
* @author Dicky
* @date 2011-11-22 ����03:40:35 
*
 */
public class UserCenterItemService implements IUserCenterItemService{
    IUserCenterItemDao userCenterItemDAO;
 

	public IUserCenterItemDao getUserCenterItemDAO() {
		return userCenterItemDAO;
	}

	public void setUserCenterItemDAO(IUserCenterItemDao userCenterItemDAO) {
		this.userCenterItemDAO = userCenterItemDAO;
	}
    /**
     * (非 Javadoc) 
    * <p>Title: deleteItem</p> 
    * <p>Description:删除菜单 </p> 
    * @param item
    * @param syslog 
    * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#deleteItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
     */
	public void deleteItem(Usercenteritem item, Syslog syslog) {
		userCenterItemDAO.deleteItem(item, syslog);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: getItemdetaliview</p> 
    * <p>Description:获取要删除的菜单对象 </p> 
    * @param itemid
    * @return 
    * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#getItemdetaliview(java.lang.String)
     */
	public Usercenteritem getItemdetaliview(String itemid) {
		return userCenterItemDAO.getItemdetaliview(itemid);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: getlistItem</p> 
    * <p>Description: 菜单分页</p> 
    * @param sql
    * @param pageSize
    * @param startIndex
    * @param url
    * @return 
    * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#getlistItem(java.lang.String, int, int, java.lang.String)
     */
	public PaginationSupport getlistItem(String sql,String pid, int pageSize,
			int startIndex, String url) {
		return userCenterItemDAO.getlistItem(sql, pid,pageSize, startIndex, url);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: insertItem</p> 
    * <p>Description:增加菜单 </p> 
    * @param item
    * @param syslog 
    * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#insertItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
     */
	public void insertItem(Usercenteritem item, Syslog syslog) {
		userCenterItemDAO.insertItem(item, syslog);
	}
     /**
      * (非 Javadoc) 
     * <p>Title: updateItem</p> 
     * <p>Description:修改菜单 </p> 
     * @param item
     * @param syslog 
     * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#updateItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
      */
	public void updateItem(Usercenteritem item, Syslog syslog) {
		userCenterItemDAO.updateItem(item, syslog);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: getItemByName</p> 
    * <p>Description: 根据名称获取 用户中心 菜单 </p> 
    * @param itemName
    * @return 
    * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#getItemByName(java.lang.String)
     */
	public boolean getItemByName(String itemName) {
		 return userCenterItemDAO.getItemByName(itemName);
	}
  /**
   * (非 Javadoc) 
  * <p>Title: getAllItem</p> 
  * <p>Description: 获取所有菜单项</p> 
  * @return 
  * @see com.ectrip.system.syspar.service.iservice.IUserCenterItemService#getAllItem()
   */
	public List getAllItem(String pid,String roleid) {
		return userCenterItemDAO.getAllItem(pid,roleid);
	}
	
	
	/**
	 * 
	 * Describe:查询菜单下是否存在子菜单
	 * @author:huxingwei
	 * @param pid
	 * @return
	 * return:List
	 * Date:2014-12-25
	 */
	public boolean getItemByParentId(Long pid){
		return userCenterItemDAO.getItemByParentId(pid);
	}
	

}

