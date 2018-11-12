package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.permitenter.dao.IUpdatesalevoucherDAO;
import com.ectrip.ticket.permitenter.service.iservice.IUpdatesalevoucherService;

public class UpdatesalevoucherService implements IUpdatesalevoucherService{
	
	private IUpdatesalevoucherDAO upsalevoucherDao;

	public IUpdatesalevoucherDAO getUpsalevoucherDao() {
		return upsalevoucherDao;
	}

	public void setUpsalevoucherDao(IUpdatesalevoucherDAO upsalevoucherDao) {
		this.upsalevoucherDao = upsalevoucherDao;
	}
	
	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ������ƾ֤�еĽ�����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#checkListsetlement(java.lang.String)
	 * @param ticketno
	 * @return
	 * @author lijingrui
	 * Date:2013-5-21
	 */
	public List checkListsetlement(String ticketno){
		return upsalevoucherDao.checkListsetlement(ticketno);
	}
	
	/**
	 * *
	 * Describe:���� ��������Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#insertSetlement(java.lang.String, java.lang.String)
	 * @param tieketno
	 * @param zffs
	 * @author lijingrui
	 * Date:2013-5-21
	 */
	public void insertSetlement(String tieketno,String zffs,Esfemployeetab esfemployeetab){
		upsalevoucherDao.insertSetlement(tieketno, zffs,esfemployeetab);
	}
	
	/**
	 * *
	 * Describe:��������ѯ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#querySalementList(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-6-3
	 */
	public PaginationSupport querySalementList(Long iemployeeid,String rzti,String ldti,String type,int page,int pageSize,String url){
		return upsalevoucherDao.querySalementList(iemployeeid, rzti, ldti, type, page, pageSize, url);
	}
	
}

