package com.ectrip.ec.report.system.ticketsale.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ISaleListDAO;
import com.ectrip.ec.report.system.ticketsale.service.iservice.ISaleListService;

public class SaleListService implements ISaleListService {

	private ISaleListDAO salelistDao;
	
	public ISaleListDAO getSalelistDao() {
		return salelistDao;
	}

	public void setSalelistDao(ISaleListDAO salelistDao) {
		this.salelistDao = salelistDao;
	}

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
	public PaginationSupport querySaleAllList(String rzti,String ldti,String usid,String types,int page,int pageSize,String url){
		return salelistDao.querySaleAllList(rzti, ldti, usid, types, page, pageSize, url);
	}

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
	public PaginationSupport queryTuiDingAllList(String rzti,String ldti,String usid,String types,int page,int pageSize,String url){
		return salelistDao.queryTuiDingAllList(rzti, ldti, usid, types, page, pageSize, url);
	}
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
	public PaginationSupport queryTingPaiAllList(String rzti,String ldti,String usid,String types,int page,int pageSize,String url){
		return salelistDao.queryTingPaiAllList(rzti, ldti, usid, types, page, pageSize, url);
	}
	
	public List querySaleAllList(String rzti,String ldti,String usids,String types){
		return salelistDao.querySaleAllList(rzti, ldti, usids, types);

	}
	
}

