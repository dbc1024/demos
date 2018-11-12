package com.ectrip.ticket.warehouse.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;

public interface IWarehouseChangeDAO extends IGenericDao{

	/**
	 * Describe: 根据仓库ID查找仓库有的票类型
	 * @auth:ChaoYuHang
	 * @param ihouseid  仓库ID
	 * @return  仓库对象List
	 * return:List
	 * Date:2012-7-16
	 */
	public List getListTickByihouse(Long ihouseid);

	/**
	 * Describe: 根据仓库ID和票ID详细该票的相信信息
	 * @auth:ChaoYuHang
	 * @param iwarehouseid	仓库ID
	 * @param itickettypeid 票ID
	 * @return   票对象List
	 * return:List
	 * Date:2012-7-16
	 */
	public List getDetailTicks(Long iwarehouseid,Long itickettypeid);

	/**
	 * Describe: 获得票规则的属性
	 * @auth:ChaoYuHang
	 * @param iticketid 票的ID
	 * @return	票规则对象List
	 * return:List
	 * Date:2012-7-24
	 */
	public List getTicketRule(Long iticketid);

	/**
	 * Describe: 检查流水号是否存在非数字
	 * @auth:ChaoYuHang
	 * @param ticketcode  票号
	 * @param itickettypeid 票类型ID
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkIserial(String ticketcode,Long itickettypeid);

	/**
	 * Describe: 根据起始票号和票数量计算票的截止票号
	 * @auth:ChaoYuHang
	 * @param ticketcode  票的起始票号
	 * @param iamount	     票的数量
	 * @param itickettypeid 票类型ID
	 * @return  票的截止票号
	 * return:String
	 * Date:2012-7-24
	 */
	public String reTicketEndcode(String ticketcode, Long iamount,Long itickettypeid);

	/**
	 * Describe: 根据起始票号和截止票号计算数量
	 * @auth:ChaoYuHang
	 * @param startticketcode 起始票号
	 * @param endticketcode   截止票号
	 * @param itickettypeid   票类型ID
	 * @return  票数量
	 * return:String
	 * Date:2012-7-24
	 */
	public String reIamountforStartCode(String startticketcode,String endticketcode,Long itickettypeid);

	/**
	 * Describe:  根据仓库库存ID查找库存
	 * @auth:ChaoYuHang
	 * @param idetailsid  库存ID
	 * @return    库存对象
	 * return:Iomstationticketdetails
	 * Date:2012-7-24
	 */
	public Iomstationticketdetails findIomstationdetailsbyid(Long idetailsid);

	/**
	 * Describe: 根据单据对象检查票段是否在仓库中
	 * @auth:ChaoYuHang
	 * @param iomstocksbilldetails 单据对象
	 * @param iwarehouseid   仓库ID
	 * @param iamount        票数量
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(Iomstocksbilldetails iomstocksbilldetails,Long iwarehouseid,Long iamount);

	/**
	 * Describe: 根据起始票号和截止票号检查票段是否在仓库中
	 * @auth:ChaoYuHang
	 * @param szstartcode  起始票段
	 * @param szendcode    截止票段
	 * @param iwarehouseid 仓库ID
	 * @param itickettypeid 票类型ID
	 * @param iamount      票数量
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(String szstartcode,String szendcode,Long iwarehouseid,Long itickettypeid,Long iamount);

	/**
	 * Describe:  根据流水号和票类型返回票号
	 * @auth:ChaoYuHang
	 * @param ticketserial  票流水号
	 * @param itickettypeid 票类型ID
	 * @return  票号
	 * return:String
	 * Date:2012-7-24
	 */
	public String reNomralTicket(Long ticketserial,Long itickettypeid,String ticketcode);

	/**
	 * Describe:  检查是否存在重复票段
	 * @auth:ChaoYuHang
	 * @param startcode  起始票号
	 * @param endcode    截止票号
	 * @param startcodeII 起始票号II
	 * @param endcodeII   截止票号II
	 * @param itickettypeid  票类型ID
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkStartcodeandEndcode(String startcode,String endcode,String startcodeII,String endcodeII,Long itickettypeid);

	/**
	 * Describe: 保存单据和单据明细
	 * @auth:ChaoYuHang
	 * @param datailList  单据详细对象List
	 * @param iomstocksbill 单据对象
	 * @param syslog		系统日志对象
	 * @param bystockswayid  操作方式ID
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean insertstockbill(List<Iomstocksbilldetails> datailList,Iomstocksbill iomstocksbill,Syslog syslog,Long bystockswayid)throws Exception;

	/**
	 * Describe:  对取出的票段进行分割
	 * @auth:ChaoYuHang
	 * @param iomstocksbilldetails 单据详细对象List
	 * @param iomstocksbill        单据对象
	 * return:void
	 * Date:2012-7-24
	 */
	public void saveTicket(List<Iomstocksbilldetails> iomstocksbilldetails,Iomstocksbill iomstocksbill);

	/**
	 * Describe:  对保存的票段进行整理
	 * @auth:ChaoYuHang
	 * @param iomstationticketdetails  仓库对象List
	 * @param iomstocksbill            单据对象
	 * return:void
	 * Date:2012-7-24
	 */
	public void saveTicketinhouse(List<Iomstationticketdetails> iomstationticketdetails,Iomstocksbill iomstocksbill);
}