package com.ectrip.ticket.permitenter.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;

public interface INumjifenSetlistDAO extends IGenericDao {
	
	/**
	 * 
	 * Describe:��ʾ��ĳ�����µ�������������ͳ��
	 * @auth:lijingrui
	 * @param nid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 30, 2012
	 */
	public PaginationSupport showAllListnumjifen(Long nid,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:�����������ͳ��
	 * @auth:lijingrui
	 * @param numsetlist
	 * return:void
	 * Date:Mar 30, 2012
	 */
	public void insertnumjifensetlist(Numjifensetlist numsetlist);
	
	/**
	 * 
	 * Describe:�޸���������ͳ��
	 * @auth:lijingrui
	 * @param numsetlist
	 * return:void
	 * Date:Mar 30, 2012
	 */
	public void updatenumjifensetlist(Numjifensetlist numsetlist);
	
	/**
	 * 
	 * Describe:ɾ����������ͳ��
	 * @auth:lijingrui
	 * @param numsetlist
	 * return:void
	 * Date:Mar 30, 2012
	 */
	public void deletenumjifensetlist(Numjifensetlist numsetlist);
	
	/**
	 * 
	 * Describe:�鿴��������ͳ��
	 * @auth:lijingrui
	 * @param numsetlist
	 * return:void
	 * Date:Mar 30, 2012
	 */
	public Numjifensetlist viewnumjifensetlist(Numjifensetlist numsetlist) throws Exception;
	
	/**
	 * 
	 * Describe:��ʾ��ĳ�������µĲ�Ʒ��Ϣ
	 * @auth:lijingrui
	 * @param iscenicid
	 * @return
	 * return:List
	 * Date:Mar 30, 2012
	 */
	public List getEdmticketlist(Long iscenicid);
	
}

