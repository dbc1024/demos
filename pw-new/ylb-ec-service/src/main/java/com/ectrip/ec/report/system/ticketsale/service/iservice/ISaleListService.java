package com.ectrip.ec.report.system.ticketsale.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;

public interface ISaleListService {
	/**
	 * ��ѯ��Ʊ��ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usid
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport querySaleAllList(String rzti, String ldti, String usid, String types, int page, int pageSize, String url);
	
	/**
	 * ��ѯ��Ʊ�˶���ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usid
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport queryTuiDingAllList(String rzti, String ldti, String usid, String types, int page, int pageSize, String url);
	
	/**
	 * ��ѯͣ����ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usid
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport queryTingPaiAllList(String rzti, String ldti, String usid, String types, int page, int pageSize, String url);
	public List querySaleAllList(String rzti, String ldti, String usids, String types);
	
}

