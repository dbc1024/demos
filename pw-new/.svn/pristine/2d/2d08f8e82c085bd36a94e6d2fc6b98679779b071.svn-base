package com.ectrip.ticket.salesmanager.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.salesmanager.dao.IReserveDateDAO;
import com.ectrip.ticket.salesmanager.service.IReserveDateService;

public class ReserveDateService implements IReserveDateService{

	private IReserveDateDAO reservedateDao;

	public IReserveDateDAO getReservedateDao() {
		return reservedateDao;
	}

	public void setReservedateDao(IReserveDateDAO reservedateDao) {
		this.reservedateDao = reservedateDao;
	}

	/**
	 * *
	 * Describe:查询出年卡类型的产品
	 * @see com.ectrip.system.salesmanager.dao.idao.IReserveDateDAO#getListedmticketype(java.lang.String)
	 * @param scentics
	 * @return
	 * @author lijingrui
	 * Date:2012-12-26
	 */
	public List getListedmticketype(String scentics){
		return reservedateDao.getListedmticketype(scentics);
	}

	/**
	 * *
	 * Describe:查询出售出有效的年卡票
	 * @see com.ectrip.system.salesmanager.service.iservice.IReserveDateService#showAllvoucherticket(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-12-27
	 */
	public PaginationSupport showAllvoucherticket(Long itickettypeid,int page,int pageSize,String url,String manyouno){
		return reservedateDao.showAllvoucherticket(itickettypeid,page,pageSize,url,manyouno);
	}

	/**
	 * *
	 * Describe:年卡延期保存
	 * @see com.ectrip.system.salesmanager.service.iservice.IReserveDateService#insertupReserve(java.lang.String[], java.lang.String)
	 * @param vouerids
	 * @param stdate
	 * @author lijingrui
	 * Date:2012-12-27
	 */
	public void insertupReserve(String [] vouerids,String stdate,Syslog syslog){
		reservedateDao.insertupReserve(vouerids, stdate,syslog);
	}
	/**
	 *
	 * Describe:
	 * @author:chenxinhao
	 * @param myzj 证件号码
	 * @param name1 姓名
	 * @param manyouno 卡号
	 * @param szticketprintno 票号
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-1-6
	 */
	public PaginationSupport showCradInfo(String myzj,String name1,String manyouno,String szticketprintno,int page,int pageSize,String url){
		return this.reservedateDao.showCradInfo(myzj, name1, manyouno, szticketprintno, page, pageSize, url);
	}

	public void editIDCard(Stssoldtickettab saleinfo,Syslog syslog){
		this.reservedateDao.editIDCard(saleinfo, syslog);
	}
}

