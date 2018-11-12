package com.ectrip.ticket.permitenter.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifenset;

public interface INumjifenSetDAO extends IGenericDao{
	
	/**
	 * 
	 * Describe:��ʾ�����еĹ�����Ϣ
	 * @auth:lijingrui
	 * @param jflb
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 29, 2012
	 */
	public PaginationSupport showAllviewNumjifen(String scenics,Long jflb,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:��ӹ���
	 * @auth:lijingrui
	 * @param numset
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void insertNumjifen(Numjifenset numset);
	
	/**
	 * 
	 * Describe:�޸Ĺ���
	 * @auth:lijingrui
	 * @param numset
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void updateNumjifen(Numjifenset numset);
	
	/**
	 * 
	 * Describe:ɾ������
	 * @auth:lijingrui
	 * @param nid
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void deleteNumjifen(Long nid);
	
	/**
	 * 
	 * Describe:�鿴����
	 * @auth:lijingrui
	 * @param nid
	 * @return
	 * @throws Exception
	 * return:Numjifenset
	 * Date:Mar 29, 2012
	 */
	public Numjifenset viewNumjifen(Long nid) throws Exception;
	
}

