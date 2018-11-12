package com.ectrip.ticket.permitenter.service;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.permitenter.dao.INumjifenSetDAO;
import com.ectrip.ticket.permitenter.service.iservice.INumjifenSetService;

@Service
public class NumjifenSetService implements INumjifenSetService{
	
	private INumjifenSetDAO numjifenDao;
	
	public INumjifenSetDAO getNumjifenDao() {
		return numjifenDao;
	}

	public void setNumjifenDao(INumjifenSetDAO numjifenDao) {
		this.numjifenDao = numjifenDao;
	}

	/**
	 * *
	 * Describe:��ʾ�����еĹ�����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#showAllviewNumjifen(java.lang.Long, int, int, java.lang.String)
	 * @param jflb
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public PaginationSupport showAllviewNumjifen(String scenics,Long jflb,int pageSize,int startIndex, String url){
		return numjifenDao.showAllviewNumjifen(scenics,jflb, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��ӹ���
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#insertNumjifen(com.ectrip.model.permitenter.Numjifenset)
	 * @param numset
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void insertNumjifen(Numjifenset numset){
		numjifenDao.insertNumjifen(numset);
	}
	
	/**
	 * *
	 * Describe:�޸Ĺ���
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#updateNumjifen(com.ectrip.model.permitenter.Numjifenset)
	 * @param numset
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void updateNumjifen(Numjifenset numset){
		numjifenDao.updateNumjifen(numset);
	}
	
	/**
	 * *
	 * Describe:ɾ������
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#deleteNumjifen(java.lang.Long)
	 * @param nid
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void deleteNumjifen(Long nid){
		numjifenDao.deleteNumjifen(nid);
	}
	
	/**
	 * *
	 * Describe:�鿴����
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#viewNumjifen(java.lang.Long)
	 * @param nid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public Numjifenset viewNumjifen(Long nid) throws Exception{
		return numjifenDao.viewNumjifen(nid);
	}
}

