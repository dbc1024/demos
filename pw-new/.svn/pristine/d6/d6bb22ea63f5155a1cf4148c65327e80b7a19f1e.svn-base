package com.ectrip.ticket.afcset.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.afcset.Esbgardengatetickettab;

public interface IEsbgardengateTicketService extends IGenericService{

	/**
	 * 园门同检票分页列表
	 * Describe:
	 * @author:luoxin
	 * @param igardengateid
	 * @param pageSize
	 * @param page
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-8-28
	 */
	PaginationSupport showGardenGateTicketViewList(Long igardengateid,
												   int pageSize, int page, String url);

	/**
	 * 获取产品
	 * Describe:
	 * @author:luoxin
	 * @param iscenicid
	 * @param bycategorytype
	 * @param byusage
	 * @param sztickettypename
	 * @param pageSize
	 * @param page
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-8-28
	 */
	PaginationSupport queryTicket(Long iscenicid, String bycategorytype,
								  Long byusage, String sztickettypename, int pageSize,
								  int page, String url);

	/**
	 * 保存园门同检票
	 * Describe:
	 * @author:luoxin
	 * @param gardengateticket
	 * return:void
	 * Date:2015-8-28
	 */
	void saveGardenGateTicket(Esbgardengatetickettab gardengateticket);

	/**
	 * 搜索同检产品
	 * Describe:
	 * @author:luoxin
	 * @param seq
	 * @param igardengateid
	 * @param itickettypeoneid
	 * @param itickettypetwoid
	 * @return
	 * return:List
	 * Date:2015-8-28
	 */
	List searchGardengateTicket(Long seq, Long igardengateid, Long itickettypeoneid,
								Long itickettypetwoid);

	/**
	 * 修改园门同步检票
	 * Describe:
	 * @author:luoxin
	 * @param gardengateticket
	 * return:void
	 * Date:2015-8-28
	 */
	void updateGardenGateTicket(Esbgardengatetickettab gardengateticket);

	/**
	 * 获取园门同检票
	 * Describe:
	 * @author:luoxin
	 * @param seq
	 * @return
	 * return:Esbgardengatetickettab
	 * Date:2015-8-28
	 * @throws Exception
	 */
	Esbgardengatetickettab getGardenGateTicket(Long seq) throws Exception;

}

