package com.ectrip.ticket.warehouse.service;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseDAO;
import com.ectrip.ticket.warehouse.service.iservice.IWarehouseService;

public class WarehouseService implements IWarehouseService{

	IWarehouseDAO warestockDao;

	public void setWarestockDao(IWarehouseDAO warestockDao) {
		this.warestockDao = warestockDao;
	}

	/**
	 * *
	 * Describe:显示仓库信息
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getListIomware(java.lang.String, java.lang.String)
	 * @param scenics 服务商ID
	 * @param isonlyby 是否显示总库   01显示总库和分库  02分库  03 总库
	 * @return
	 * @author lijingrui
	 * Date:2012-7-10
	 */
	public List showListIomware(String scenics, String isonlyby) {
		return warestockDao.showListIomware(scenics, isonlyby);
	}

	/**
	 * *
	 * Describe:根据仓库ID来查询某服务商下的产品信息
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getListticket(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-10
	 */
	public List showListticket(Long iwarehouseid) {
		return warestockDao.showListticket(iwarehouseid);
	}



	/**
	 * *
	 * Describe:保存 初始入库信息
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-7-12
	 */
	public boolean insertstockbill(Iomstocksbill stocks, List<Iomstocksbilldetails> datailList,
								   Syslog syslog,Long bystockswayid) throws Exception {
		return warestockDao.insertstockbill(stocks, datailList, syslog, bystockswayid);
	}

	/**
	 * *
	 * Describe:初始入库时 判断添加相同产品时 输入票号是否重复
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#LookstartorendCode(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param szstartticketcode
	 * @param szendticketcode
	 * @param startcode
	 * @param endcode
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-19
	 */
	public boolean LookstartorendCode(String szstartticketcode,
									  String szendticketcode, String startcode, String endcode,
									  Long itickettypeid) {
		return warestockDao.LookstartorendCode(szstartticketcode, szendticketcode, startcode, endcode, itickettypeid);
	}
}

