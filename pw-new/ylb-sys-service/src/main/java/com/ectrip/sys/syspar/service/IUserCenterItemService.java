package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenteritem;
/**
 * 
* @ClassName: IUserCenterItemService 
* @Description: 用户中心 菜单 业务 接口
* @author Dicky
* @date 2011-11-22 下午03:38:06 
*
 */
public interface IUserCenterItemService {
	public PaginationSupport getlistItem(String sql,String pid,int pageSize,int startIndex, String url); 
	public void insertItem(Usercenteritem item,Syslog syslog);
	public void updateItem(Usercenteritem item,Syslog syslog);
	public void deleteItem(Usercenteritem item,Syslog syslog);
	public Usercenteritem getItemdetaliview(String itemid);
	public boolean getItemByName(String itemName);
	public List getAllItem(String pid,String roleid);
	
	/**
	 * 
	 * Describe:查询菜单下是否存在子菜单
	 * @author:huxingwei
	 * @param pid
	 * @return
	 * return:List
	 * Date:2014-12-25
	 */
	public boolean getItemByParentId(Long pid);
}

