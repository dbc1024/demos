package com.ectrip.ticket.salesmanager.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.order.Stssoldtickettab;

public interface IReserveDateService {

	/**
	 *
	 * Describe:查询出年卡类型的产品
	 * @auth:lijingrui
	 * @param scentics
	 * @return
	 * return:List
	 * Date:2012-12-26
	 */
	public List getListedmticketype(String scentics);

	/**
	 *
	 * Describe:查询出售出有效的年卡票
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-12-27
	 */
	public PaginationSupport showAllvoucherticket(Long itickettypeid,int page,int pageSize,String url,String manyouno);

	/**
	 *
	 * Describe:年卡延期保存
	 * @auth:lijingrui
	 * @param vouerids
	 * @param stdate
	 * return:void
	 * Date:2012-12-27
	 */
	public void insertupReserve(String [] vouerids,String stdate,Syslog syslog);
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
	public PaginationSupport showCradInfo(String myzj,String name1,String manyouno,String szticketprintno,int page,int pageSize,String url);

	public void editIDCard(Stssoldtickettab saleinfo,Syslog syslog);
}

