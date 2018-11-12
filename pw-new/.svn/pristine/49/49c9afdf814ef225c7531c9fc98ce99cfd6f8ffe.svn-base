package com.ectrip.sys.employee.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IESPPostsTabDao;
import com.ectrip.sys.employee.service.IESPPostsTabService;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class ESPPostsTabService extends GenericService implements IESPPostsTabService{
	
	IESPPostsTabDao  postDao;
	@Autowired
	public void setpostDao(IESPPostsTabDao postDao) {
		super.setGenericDao(postDao);
		this.postDao = postDao;
	}
	/**
	 * 新增岗位
	 */
	public void insertEspposts(Esppoststab post,Syslog syslog){
		postDao.insertEspposts(post,syslog);
	}
	
	/**
	 * 修改岗位信息
	 */
	public void updateEspposts(Esppoststab post,Syslog syslog){
		postDao.updateEspposts(post,syslog);
	}

	/**
	 * 删除岗位
	 */
	public void deleteEspposts(Esppoststab post,Syslog syslog){
		postDao.deleteEspposts(post,syslog);
	}
	
	/**
	 * 根据编号得到岗位基本信息
	 */
	public Esppoststab findEspposts(Long ipostsid) throws Exception{
		return postDao.findEspposts(ipostsid);
	}

	/**
	 * 显示所有的岗位信息
	 */
	public PaginationSupport getlistEspposts(String szpostname,String posttype,int pageSize, int startIndex, String url) {
		return postDao.getlistEspposts(szpostname,posttype,pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:判断某岗位与部门是否关联
	 * @see com.ectrip.system.employee.service.iservice.IESPPostsTabService#getEsfdeptpost(java.lang.Long)
	 * @param ipostsid
	 * @return
	 * @author yangguang
	 * Date:2011-9-23
	 */
	public boolean getEsfdeptpost(Long ipostsid) {
		return postDao.getEsfdeptpost(ipostsid);
	}
	/**
	 * 根据登录用户id读取有效的岗位
	 * Describe:如果登录用户为平台用户，则读出所有岗位，如果为景区用户，则只出相应景区中的岗位
	 * @auth:huangyuqi
	 * @param employeeId
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getAllPost(Long employeeId){
		return postDao.getAllPost(employeeId);
	}
	
}
