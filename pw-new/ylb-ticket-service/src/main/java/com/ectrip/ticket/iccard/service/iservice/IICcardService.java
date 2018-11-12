package com.ectrip.ticket.iccard.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;

public interface IICcardService extends IGenericService {
	/**
	 * 
	 * Describe:ȡ����ز�����ICID
	 * @author:chenxinhao
	 * @param typeid	01--Ʊ��,02--ҵ��,03--��Ⱥ,04--��Ʊ��,05--��ƱԱ
	 * @param id
	 * @return
	 * return:String
	 * Date:2012-8-29
	 */
	public String getICID(String typeid,Long id);
}

