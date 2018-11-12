package com.ectrip.ticket.warehouse.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;


public interface IWarehouseObsoleteDAO extends IGenericDao{

	/**
	 *
	 * Describe:计算票数量
	 * @author:wangling
	 * @param ticketid
	 * @param startcode
	 * @param endcode
	 * @return
	 * return:long
	 * Date:2012-7-23
	 */
	public long showiamount(long ticketid,String startcode, String endcode);

	/**
	 *
	 * Describe:仓库明细表
	 * @author:wangling
	 * @param stockList
	 * return:void
	 * Date:2012-7-23
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList);

	/**
	 *
	 * Describe:判断仓库结存明细表中是否存在该票段
	 * @author:wangling
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * return:boolean
	 * Date:2012-7-23
	 */

	public boolean retriedStadetails(Iomstationticketdetails stadeta,long istationoutid) ;
	/**
	 *
	 * Describe:保存单据信息操作
	 * @author:wangling
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @throws Exception
	 * return:boolean
	 * Date:2012-7-23
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstationticketdetails> detailList, Syslog syslog) ;

}

