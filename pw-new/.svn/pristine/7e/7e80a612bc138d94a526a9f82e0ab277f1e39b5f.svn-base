package com.ectrip.tourcard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.dao.ITourcardBindUserDAO;
import com.ectrip.tourcard.model.TourcardBindUserQuery;
import com.ectrip.tourcard.service.ITourcardBindUserService;


@Service
public class TourcardBindUserService extends GenericService implements ITourcardBindUserService{
	
	private ITourcardBindUserDAO tourcardBindUserDao;
	
	@Autowired
	public void setTourcardBindUserDao(ITourcardBindUserDAO tourcardBindUserDao) {
		this.tourcardBindUserDao = tourcardBindUserDao;
		super.setGenericDao(tourcardBindUserDao);
	}

	/**
	 * 分页查询旅游卡绑定用户信息
	 */
	public PaginationSupport showlistTourcardBindUser(int pageSize, int startInt, String url,TourcardBindUserQuery query) throws RuntimeException {
		
		PaginationSupport showlistTourcardBindUser = tourcardBindUserDao.showlistTourcardBindUser(pageSize, startInt, url,query);
		
		return showlistTourcardBindUser;
	}

	@SuppressWarnings("rawtypes")
	public List findTourcardBindUserInfo(String userId) throws RuntimeException, Exception {
		
		return tourcardBindUserDao.findTourcardBindUserInfo(userId);
	}

	@SuppressWarnings("rawtypes")
	public List findTourcardDetailInfoByUserId(String userId) throws RuntimeException, Exception {
		
		return tourcardBindUserDao.findTourcardDetailInfoByUserId(userId);
	}
	
}
