package com.ectrip.ticket.checkstssold.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.order.Stssalesvouchertab;

public interface ICheckStssoldDAO extends IGenericDao{

	/**
	 *
	 * Describe:判断此票印刷号是否存在
	 * @auth:lijingrui
	 * @param szticketprintno
	 * @return
	 * return:boolean
	 * Date:Mar 13, 2012
	 */
	public boolean getStssoldticketLookview(String szticketprintno);

	/**
	 *
	 * Describe:判断此票是否检票
	 * @auth:lijingrui
	 * @param szticketprintno
	 * @return
	 * return:boolean
	 * Date:Mar 13, 2012
	 */
	public boolean getQuerystssold(String szticketprintno);

	/**
	 *
	 * Describe:显示此票的检票信息
	 * @auth:lijingrui
	 * @param szticketprintno
	 * @return
	 * return:Stssoldtickettab
	 * Date:Mar 13, 2012
	 */
	public Stssalesvouchertab showViewStssoldticket(String szticketprintno) throws Exception ;

}

