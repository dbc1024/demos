package com.ectrip.ec.report.system.order.service.iservice;

import java.util.List;

import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IAllTicketsalecountService {

	/**
	 * 
	 * Describe:�����Ⱥ����
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2013-5-26
	 */
	public List getCrowdKindList();
	
	/**
	 * 
	 * Describe:ҵ������
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2013-5-26
	 */
	public List getBusinessList();
	
	/**
	 * 
	 * Describe:Э�鵥λ
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2013-5-26
	 */
	public List getCustomList(Long ibusinessid);
	
	/**
	 * 
	 * Describe:��ȡ���Ʊ�е���·
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-5-26
	 */
	public String getAllticket();
	
	/**
	 * 
	 * Describe:��ȡ������ķ������µ����Ʊ
	 * @auth:lijingrui
	 * @param esfemployee
	 * @param type  0 ��ȡ��Ʒ����������Ʊ����Ĳ�Ʒ   1 ��ȡ�������Ʊ
	 * @param bycate  0 ����   1 ����    2���Ʊ
	 * @return
	 * return:List
	 * Date:2013-5-29
	 */
	public List getListedmticketes(Esfemployeetab esfemployee,String type,String bycate);
	
}

