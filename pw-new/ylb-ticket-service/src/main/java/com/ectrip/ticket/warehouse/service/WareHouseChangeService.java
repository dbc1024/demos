package com.ectrip.ticket.warehouse.service;


import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseChangeDAO;
import com.ectrip.ticket.warehouse.service.iservice.IWareHouseChangeService;

public class WareHouseChangeService implements IWareHouseChangeService {

	IWarehouseChangeDAO warehouseChangeDao;	//仓库操作DAO借口

	public IWarehouseChangeDAO getWarehouseChangeDao() {
		return warehouseChangeDao;
	}

	public void setWarehouseChangeDao(IWarehouseChangeDAO warehouseChangeDao) {
		this.warehouseChangeDao = warehouseChangeDao;
	}


	/***
	 * Describe:  根据仓库ID查找仓库有的票类型
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#getListTickByihouseid(java.lang.Long)
	 * @param ihouseid 仓库ID
	 * @return  仓库ID
	 * @author ChaoYuHang
	 * Date:2012-7-16
	 */
	public List getListTickByihouseid(Long ihouseid){
		return warehouseChangeDao.getListTickByihouse(ihouseid);
	}

	/***
	 * Describe: 根据仓库ID和票ID显示票的详细信息
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#getDetailTick(java.lang.Long, java.lang.Long)
	 * @param ihouseid 仓库ID
	 * @param iticketetypeid 仓库ID
	 * @return 票详细信息List
	 * @author ChaoYuHang
	 * Date:2012-7-16
	 */
	public List getDetailTick(Long ihouseid, Long iticketetypeid) {
		return warehouseChangeDao.getDetailTicks(ihouseid, iticketetypeid);
	}

	/***
	 * Describe:  取得票规则
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#getTicketRule(java.lang.Long)
	 * @param itickettypeid 票类型ID
	 * @return  票规则对象List
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public List getTicketRule(Long itickettypeid){
		return warehouseChangeDao.getTicketRule(itickettypeid);
	}

	/***
	 * Describe:  检查流水号是否存在非数字
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#checkIserial(java.lang.String, java.lang.Long)
	 * @param ticketcode 票号
	 * @param itickettypeid 票类型ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkIserial(String ticketcode,Long itickettypeid){
		return warehouseChangeDao.checkIserial(ticketcode, itickettypeid);
	}

	/***
	 * Describe:  根据起始票号和截止票号得到票数量
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#reIamountforStartCode(java.lang.String, java.lang.String, java.lang.Long)
	 * @param startticketcode 起始票号
	 * @param endticketcode  截止票号
	 * @param itickettypeid  票类型ID
	 * @return  票数量
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public String reIamountforStartCode(String startticketcode,String endticketcode,Long itickettypeid){
		return warehouseChangeDao.reIamountforStartCode(startticketcode, endticketcode, itickettypeid);
	}

	/***
	 * Describe:  根据流水号得到票号
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#reTicketEndcode(java.lang.String, java.lang.Long, java.lang.Long)
	 * @param ticketstartcode 起始票号
	 * @param iamount  票数量
	 * @param itickettypeid 票类型ID
	 * @return  截止票号
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public String reTicketEndcode(String ticketstartcode, Long iamount,Long itickettypeid){
		return warehouseChangeDao.reTicketEndcode(ticketstartcode,iamount, itickettypeid);
	}

	/***
	 * Describe: 根据仓库ID查找仓库对象
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#findIomstationdetailsbyid(java.lang.Long)
	 * @param idetailsid  仓库ID
	 * @return  仓库对象
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public Iomstationticketdetails findIomstationdetailsbyid(Long idetailsid){
		return warehouseChangeDao.findIomstationdetailsbyid(idetailsid);
	}

	/***
	 * Describe:  检查票段是否在仓库中
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#checkTicketInihouse(com.ectrip.model.warehouse.Iomstocksbilldetails, java.lang.Long, java.lang.Long)
	 * @param iomstocksbilldetails 单据详细对象
	 * @param iwarehouseid 仓库ID
	 * @param iamount  票数量
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(Iomstocksbilldetails iomstocksbilldetails, Long iwarehouseid,Long iamount) {
		return warehouseChangeDao.checkTicketInihouse(iomstocksbilldetails, iwarehouseid, iamount);
	}

	/***
	 * Describe: 检查票数量是否超出范围
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#checkTicketIamount(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param szstartcode  起始票号
	 * @param szendcode  截止票号
	 * @param iwarehouseid  仓库ID
	 * @param itickettypeid  票类型ID
	 * @param iamount  票数量
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkTicketIamount(String szstartcode,String szendcode,Long iwarehouseid,Long itickettypeid,Long iamount){
		return warehouseChangeDao.checkTicketInihouse(szstartcode, szendcode, iwarehouseid, itickettypeid, iamount);
	}

	/***
	 * Describe:  检查输入票段是否有重复交叉
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#checkStartcodeandEndcode(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 * @param startcode  起始票段
	 * @param endcode   截止票段
	 * @param startcodeII  起始票段II
	 * @param endcodeII  截止票段II
	 * @param itickettypeid  票类型
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkStartcodeandEndcode(String startcode, String endcode,String startcodeII, String endcodeII, Long itickettypeid) {
		return warehouseChangeDao.checkStartcodeandEndcode(startcode, endcode, startcodeII, endcodeII, itickettypeid);
	}

	/***
	 * Describe:  保存操作
	 * @see com.ectrip.system.warehouse.service.iservice.IWareHouseChangeService#saveTicketChange(java.util.List, com.ectrip.model.warehouse.Iomstocksbill, com.ectrip.model.syspar.Syslog, java.lang.Long)
	 * @param iomstocksbilldetails  单据详细对象List
	 * @param iomstocksbill  单据对象
	 * @param syslog  系统日志对象
	 * @param bystockswayid  操作方式ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 * @throws Exception
	 */
	public boolean saveTicketChange(List<Iomstocksbilldetails> iomstocksbilldetails,Iomstocksbill iomstocksbill,Syslog syslog,Long bystockswayid) throws Exception{
		return warehouseChangeDao.insertstockbill(iomstocksbilldetails, iomstocksbill,syslog,bystockswayid);
	}


}
