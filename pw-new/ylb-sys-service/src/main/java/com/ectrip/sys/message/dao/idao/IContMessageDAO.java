package com.ectrip.sys.message.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Contmessage;

public interface IContMessageDAO extends IGenericDao{
	
	/**
	 * 
	 * Describe:��ʾ�����еĶ��ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 10, 2012
	 */
	public PaginationSupport showALLContMessage(int page,int pageSize,String url);
	
	/**
	 * 
	 * Describe:��Ӷ��ŷ���������Ϣ
	 * @auth:lijingrui
	 * @param contage
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void addContMessage(Contmessage contage);
	
	/**
	 * 
	 * Describe:�޸Ķ��ŷ���������Ϣ
	 * @auth:lijingrui
	 * @param contage
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void editContMessage(Contmessage contage);
	
	/**
	 * 
	 * Describe:ɾ�����ŷ���������Ϣ
	 * @auth:lijingrui
	 * @param contid
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void delContMessage(Long contid);
	
	/**
	 * 
	 * Describe:�鿴���ŷ���������Ϣ
	 * @auth:lijingrui
	 * @param contid
	 * @return
	 * @throws Exception
	 * return:Contmessage
	 * Date:Mar 21, 2012
	 */
	public Contmessage viewContMessage(Long contid) throws Exception;
	
}

