package com.ectrip.tourcard.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.TourcardBindUserQuery;

public interface ITourcardBindUserDAO extends IGenericDao{
	
	/**
	 * 描述：分页查询旅游卡绑定用户简要信息
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @throws RuntimeException
	 */
	PaginationSupport showlistTourcardBindUser(int pageSize, int startInt, String url,TourcardBindUserQuery query) throws RuntimeException;
	
	/**
	 * 描述：根据用户id查询旅游卡绑定用户
	 * @param userId
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	List findTourcardBindUserInfo(String userId) throws RuntimeException,Exception;
	
	/**
	 * 描述：根据用户id查询旅游卡明细信息
	 * @param userId
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	List findTourcardDetailInfoByUserId(String userId) throws RuntimeException,Exception;
	
	List<?> findTourcardBindUserInfoByMutiCondition(List<String> list) throws  RuntimeException,Exception;
	
	
}
