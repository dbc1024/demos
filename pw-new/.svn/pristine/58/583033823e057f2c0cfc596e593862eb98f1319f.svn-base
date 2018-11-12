package com.ectrip.sys.syspar.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Usercenteritem;
import com.ectrip.sys.syspar.dao.IUserCenterItemDao;
/**
* 
* @ClassName: UserCenterItemDAO 
* @Description:前台用户中心菜单   dao
* @author Dicky
* @date 2011-11-22 下午03:34:55 
*
*/
public class UserCenterItemDAO extends GenericDao implements IUserCenterItemDao {
    /**
     * (非 Javadoc) 
    * <p>Title: deleteItem</p> 
    * <p>Description: 删除菜单</p> 
    * @param item
    * @param syslog 
    * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#deleteItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
     */
	public void deleteItem(Usercenteritem item, Syslog syslog) {
		Usercenteritem hd=(Usercenteritem) super.get(Usercenteritem.class, item.getItemid());
		this.delete(hd);
		String sql=" from Usercenteritem u where u.itemparent="+item.getItemid();
		List lst=this.find(sql);
		if(lst.size()>0&&lst!=null){
			for(int i=0;i<lst.size();i++){
				Usercenteritem esf=(Usercenteritem) lst.get(i);
				this.delete(esf);
			}
		}
		syslog.setStlg("0042");
		syslog.setBrief("用户中心菜单：" +item.getItemid());
		syslog.setNote("删除用户中心菜单：" +item.getItemname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
   /**
    * (非 Javadoc) 
   * <p>Title: getItemdetaliview</p> 
   * <p>Description: 获取要删除的菜单对象</p> 
   * @param itemid
   * @return 
   * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#getItemdetaliview(java.lang.String)
    */
	public Usercenteritem getItemdetaliview(String itemid) {
		String sql=" from Usercenteritem hs where hs.itemid="+itemid;
		List list = this.find(sql);
		if(list.size() >0){
			return (Usercenteritem)list.get(0);	
		}
		return null;
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
   * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#getlistItem(java.lang.String, int, int, java.lang.String)
    */
	public PaginationSupport getlistItem(String sql,String pid, int pageSize,
			int startIndex, String url) {
		StringBuffer sb=new StringBuffer();
		sb.append(" from Usercenteritem u where 1=1");
		if(sql!=null && !sql.equals("")){
			sb.append(" and u.itemname like '%"+sql+"%'");
		}
		if(pid != null){
			sb.append(" and u.itemparent = "+pid);
		}else{
			sb.append(" and u.itemparent is null");
		}
		return this.findPage(sb.toString(), pageSize, startIndex, url);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: insertItem</p> 
    * <p>Description: 增加菜单</p> 
    * @param item
    * @param syslog 
    * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#insertItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
     */
	public void insertItem(Usercenteritem item, Syslog syslog) {
		String sql="select max(itemid) from Usercenteritem";
		List list = this.find(sql);
		Long id = null;
		if (list != null && list.size() >= 1 && list.get(0) != null) {
			id = (Long) list.get(0) + 1;
		} else {
			id = new Long(1);
		}
		item.setItemid(id);
		this.save(item);
		
		syslog.setStlg("0040");
		syslog.setBrief("用户中心菜单：" +item.getItemid());
		syslog.setNote("添加用户中心菜单：" +item.getItemname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
    /**
     * (非 Javadoc) 
    * <p>Title: updateItem</p> 
    * <p>Description:修改菜单 </p> 
    * @param item
    * @param syslog 
    * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#updateItem(com.ectrip.model.syspar.Usercenteritem, com.ectrip.model.syspar.Syslog)
     */
	public void updateItem(Usercenteritem item, Syslog syslog) {
		this.update(item);
		syslog.setStlg("0041");
		syslog.setBrief("用户中心菜单：" +item.getItemid());
		syslog.setNote("修改用户中心菜单：" +item.getItemname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * (非 Javadoc) 
	* <p>Title: getItemByName</p> 
	* <p>Description: 根据名称获取 用户中心 菜单</p> 
	* @param itemName
	* @return 
	* @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#getItemByName(java.lang.String)
	 */
	public boolean getItemByName(String itemName) {
		String sql=" from Usercenteritem hs where hs.itemname='"+itemName+"'";
		List list = this.find(sql);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	/**
	 * (非 Javadoc) 
	* <p>Title: getAllItem</p> 
	* <p>Description: 获取所有菜单项</p> 
	* @return 
	* @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#getAllItem()
	 */
	
	public List getAllItem(String pid,String roleid) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(" from Usercenteritem u where ");
		if(pid!=null){
			sb.append(" u.itemparent = "+pid);
		}else{
			sb.append(" u.itemparent is null ");
		}
		if(roleid!=null){
			sb.append(" and u.itemauthority like '%"+roleid+"%'");
		}
		sb.append(" and itemstatus=1 order by itemid");
		return this.find(sb.toString());
	}
	
	/**
	 * *
	 * Describe:查询菜单下是否存在子菜单
	 * @see com.ectrip.system.syspar.dao.idao.IUserCenterItemDao#getItemByParentId(java.lang.Long)
	 * @param pid
	 * @return
	 * @author huxingwei
	 * Date:2014-12-25
	 */
	public boolean getItemByParentId(Long pid){
		String sql=" from Usercenteritem  where itemparent="+pid;
		List list=this.find(sql);
		boolean ok=false;
		if(list!=null&&list.size()>0){
			ok=true;
		}
		return ok;
	}
}

