package com.ectrip.ticket.warehouse.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;

public interface IWarehouseDAO extends IGenericDao {

	/**
	 *
	 * Describe:显示仓库信息
	 * @auth:lijingrui
	 * @param scenics 服务商ID
	 * @param isonlyby  是否显示总库   01显示总库和分库  02分库  03 总库
	 * @return
	 * return:List
	 * Date:2012-7-10
	 */
	public List showListIomware(String scenics,String isonlyby);

	/**
	 *
	 * Describe:根据仓库ID来查询某服务商下的产品信息
	 * @auth:lijingrui
	 * @param iwarehouseid
	 * @return
	 * return:String
	 * Date:2012-7-10
	 */
	public List showListticket(Long iwarehouseid);

	/**
	 *
	 * Describe:保存 初始入库
	 * @auth:lijingrui
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * return:void
	 * Date:2012-7-12
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstocksbilldetails> datailList,Syslog syslog,Long bystockswayid) throws Exception;

	/**
	 *
	 * Describe:初始入库时 判断添加相同产品时 输入票号是否重复
	 * @auth:lijingrui
	 * @param szstartticketcode
	 * @param szendticketcode
	 * @param startcode
	 * @param endcode
	 * @param itickettypeid
	 * @return
	 * return:boolean
	 * Date:2012-7-19
	 */
	public boolean LookstartorendCode(String szstartticketcode,String szendticketcode,String startcode,String endcode,Long itickettypeid);

}

