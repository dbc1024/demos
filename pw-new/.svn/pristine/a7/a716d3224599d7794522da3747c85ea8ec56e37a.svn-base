package com.ectrip.ticket.iccard.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.iccard.dao.idao.IICcardDao;
import com.ectrip.ticket.iccard.service.iservice.IICcardService;

public class ICcardService extends GenericService implements IICcardService {
	private IICcardDao iICcardDao;
	
	public IICcardDao getiICcardDao() {
		return iICcardDao;
	}

	public void setiICcardDao(IICcardDao iICcardDao) {
		this.iICcardDao = iICcardDao;
	}
	/**
	 * *
	 * Describe:ȡ����ز�����ICID
	 * @see com.ectrip.iccard.service.iservice.IICcardService#getICID(java.lang.String, java.lang.Long)
	 * @param typeid	01--Ʊ��,02--ҵ��,03--��Ⱥ,04--��Ʊ��,05--��ƱԱ
	 * @param id
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-29
	 */
	public String getICID(String typeid, Long id) {
		return this.iICcardDao.getICID(typeid, id);
	}
	
}

