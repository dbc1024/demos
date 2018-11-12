package com.ectrip.ticket.permitenter.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Esbrefundrules;
import com.ectrip.ticket.permitenter.dao.IEsbrefundrulesDAO;
import com.ectrip.ticket.permitenter.service.iservice.IEsbrefundrulesService;

public class EsbrefundrulesService implements IEsbrefundrulesService{
	
	IEsbrefundrulesDAO esbrefundruleDao;

	public IEsbrefundrulesDAO getEsbrefundruleDao() {
		return esbrefundruleDao;
	}

	public void setEsbrefundruleDao(IEsbrefundrulesDAO esbrefundruleDao) {
		this.esbrefundruleDao = esbrefundruleDao;
	}
	
	/**
	 * *
	 * Describe:��ȡ���з�������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#checkListRefundrule(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public PaginationSupport checkListRefundrule(String scenics,int pageSize,int startIndex, String url){
		return esbrefundruleDao.checkListRefundrule(scenics, pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:���� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#insertRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void insertRefundrule(Esbrefundrules refundrule,Syslog syslog){
		esbrefundruleDao.insertRefundrule(refundrule, syslog);
	}
	
	/**
	 * *
	 * Describe:�޸� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#updateRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void updateRefundrule(Esbrefundrules refundrule,Syslog syslog){
		esbrefundruleDao.updateRefundrule(refundrule, syslog);
	}
	
	/**
	 * *
	 * Describe:ɾ�� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#delRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void delRefundrule(Esbrefundrules refundrule,Syslog syslog){
		esbrefundruleDao.delRefundrule(refundrule, syslog);
	}
	
	/**
	 * *
	 * Describe:�鿴 ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#viewRefundrule(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public Esbrefundrules viewRefundrule(Long seq)throws Exception{
		return esbrefundruleDao.viewRefundrule(seq);
	}

}

