package com.ectrip.ticket.permitenter.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;
import com.ectrip.ticket.permitenter.dao.IEsbtripequiptabDAO;
import com.ectrip.ticket.permitenter.service.iservice.IEsbtripequiptabService;

public class EsbtripequiptabService implements IEsbtripequiptabService{
	
	IEsbtripequiptabDAO esbtripDao;
	
	/**
	 * *
	 * Describe:�б���ʾ�����еľ����г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getlistesbtripequip(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public PaginationSupport getlistesbtripequip(int pageSize, int startIndex,
			String url) {
		return esbtripDao.getlistesbtripequip(pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:����г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#addesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void addesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		esbtripDao.addesbtripequip(esbtrip, syslog);
	}
	
	/**
	 * *
	 * Describe:�޸��г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#editesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void editesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		esbtripDao.editesbtripequip(esbtrip, syslog);
	}

	/**
	 * *
	 * Describe:ɾ���г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#delesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void delesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		esbtripDao.delesbtripequip(esbtrip, syslog);
	}

	/**
	 * *
	 * Describe:�鿴�г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getviewesbtripid(java.lang.Long)
	 * @param itripid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public Esbtripequiptab getviewesbtripid(Long itripid) throws Exception {
		return esbtripDao.getviewesbtripid(itripid);
	}
	
	/**
	 * *
	 * Describe:��ʾ����A��Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getListequip(java.lang.String)
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public String getListequip(String scenics,Long byisstend) {
		return esbtripDao.getListequip(scenics,byisstend);
	}
	
	/**
	 * *
	 * Describe:��ʾ����B��Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbtripequiptabService#getListgardip(java.lang.String, java.lang.Long)
	 * @param scenics
	 * @param byisvenue
	 * @return
	 * @author lijingrui
	 * Date:Dec 26, 2011
	 */
	public String getListgardip(String scenics, Long byisvenue) {
		return esbtripDao.getListgardip(scenics, byisvenue);
	}
	
	/**
	 * *
	 * Describe:��ʾ����������ʱ
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbtripequiptabService#getViewszdate(java.lang.String)
	 * @param scenicname
	 * @return
	 * @author lijingrui
	 * Date:Dec 26, 2011
	 */
	public String getViewszdate(Long scenicid){
		return esbtripDao.getViewszdate(scenicid);
	}

	/**
	 * *
	 * Describe:�ж϶������ʱ�Ƿ����
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getshopbeantak(java.lang.Long, java.lang.Long)
	 * @param iscenicaid
	 * @param iscenicbid
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public boolean getshopbeantak(Long iscenicaid, Long iscenicbid) {
		return esbtripDao.getshopbeantak(iscenicaid, iscenicbid);
	}

	public IEsbtripequiptabDAO getEsbtripDao() {
		return esbtripDao;
	}

	public void setEsbtripDao(IEsbtripequiptabDAO esbtripDao) {
		this.esbtripDao = esbtripDao;
	}
	
}

