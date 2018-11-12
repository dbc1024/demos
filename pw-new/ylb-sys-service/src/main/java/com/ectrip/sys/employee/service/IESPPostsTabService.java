package com.ectrip.sys.employee.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;

/**
 * 岗位管理
 * @author lijingrui
 */
public interface IESPPostsTabService extends IGenericService{
	/**
	 * 添加岗位
	 * lijingrui
	 * @param post
	 * @param ideptids   岗位所属部门的 部门ID
	 */
	public void insertEspposts(Esppoststab post,Syslog syslog);
	
	/**
	 * 修改岗位
	 * @param post
	 * @param ideptids
	 */
	public void updateEspposts(Esppoststab post,Syslog syslog);  
	
	/**
	 * 删除岗位
	 * @param post
	 */
	public void deleteEspposts(Esppoststab post,Syslog syslog);
	
	/**
	 * 根据编号查看岗位基本信息
	 * @param ipostsid
	 * @return
	 */
	public Esppoststab findEspposts(Long ipostsid) throws Exception;  
	
	/**
	 * 根据岗位名称查询
	 * 列表显示所有的岗位信息
	 */
	public PaginationSupport getlistEspposts(String szpostname,String posttype,int pageSize,
			int startIndex, String url); 
	
	/**
	 * 
	 * Describe:判断某岗位与部门是否关联
	 * @auth:lijingrui
	 * @param ipostsid
	 * @return
	 * return:boolean
	 * Date:2011-9-23
	 */
	public boolean getEsfdeptpost(Long ipostsid);
	/**
	 * 根据登录用户id读取有效的岗位
	 * Describe:如果登录用户为平台用户，则读出所有岗位，如果为景区用户，则只出相应景区中的岗位
	 * @auth:huangyuqi
	 * @param employeeId
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getAllPost(Long employeeId);
	
}
