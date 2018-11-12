package com.ectrip.ticket.permitenter.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IUpdatesalevoucherService {

	/**
	 * 
	 * Describe:����Ʊ�Ų�ѯ������ƾ֤�еĽ�����Ϣ
	 * @auth:lijingrui
	 * @param ticketno
	 * @return
	 * return:List
	 * Date:2013-5-21
	 */
	public List checkListsetlement(String ticketno);
	
	/**
	 * 
	 * Describe:���� ��������Ϣ
	 * @auth:lijingrui
	 * @param tieketno
	 * @param zffs
	 * return:void
	 * Date:2013-5-21
	 */
	public void insertSetlement(String tieketno,String zffs,Esfemployeetab esfemployeetab);
	
	/**
	 * 
	 * Describe:��������ѯ
	 * @auth:lijingrui
	 * @param iemployeeid
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2013-6-3
	 */
	public PaginationSupport querySalementList(Long iemployeeid,String rzti,String ldti,String type,int page,int pageSize,String url);
	
}

