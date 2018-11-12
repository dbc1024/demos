package com.ectrip.tourcard.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.TourcardBindUserQuery;

public interface ITourcardBindUserService {

	/**
	 * ��������ҳ��ѯ���ο����û���Ҫ��Ϣ
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @throws RuntimeException
	 */
	PaginationSupport showlistTourcardBindUser(int pageSize, int startInt, String url,TourcardBindUserQuery query) throws RuntimeException;
	
	/**
	 * �����������û�id��ѯ���ο����û�
	 * @param userId
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	List findTourcardBindUserInfo(String userId) throws RuntimeException,Exception;
	
	/**
	 * �����������û�id��ѯ���ο���ϸ��Ϣ
	 * @param userId
	 * @return
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	List findTourcardDetailInfoByUserId(String userId) throws RuntimeException,Exception;
	
	
}
