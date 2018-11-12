package com.ectrip.ticket.warehouse.service.iservice;

import java.util.List;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;


public interface IWareHouseChangeService {

	/**
	 * Describe:  根据仓库ID查找仓库有的票类型
	 * @auth:ChaoYuHang
	 * @param ihouseid	仓库ID
	 * @return	仓库对象List
	 * return:List
	 * Date:2012-7-16
	 */
	public List getListTickByihouseid(Long ihouseid);

	/**
	 * Describe:  根据仓库ID和票ID显示票的详细信息
	 * @auth:ChaoYuHang
	 * @param ihouseid  仓库ID
	 * @param iticketetypeid  票ID
	 * @return    票详细信息List
	 * return:List
	 * Date:2012-7-16
	 */
	public List getDetailTick(Long ihouseid,Long iticketetypeid);

	/**
	 * Describe: 取得票规则
	 * @auth:ChaoYuHang
	 * @param itickettypeid 票类型ID
	 * @return  票规则对象List
	 * return:List
	 * Date:2012-7-24
	 */
	public List getTicketRule(Long itickettypeid);

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
	 * Describe: 根据起始票号和截止票号得到票数量
	 * @auth:ChaoYuHang
	 * @param startticketcode  起始票号
	 * @param endticketcode    截止票号
	 * @param itickettypeid    票类型ID
	 * @return    票数量
	 * return:String
	 * Date:2012-7-24
	 */
	public String reIamountforStartCode(String startticketcode,String endticketcode,Long itickettypeid);

	/**
	 * Describe: 根据流水号得到票号
	 * @auth:ChaoYuHang
	 * @param ticketstartcode  起始票号
	 * @param iamount          票数量
	 * @param itickettypeid    票类型ID
	 * @return  截止票号
	 * return:String
	 * Date:2012-7-24
	 */
	public String reTicketEndcode(String ticketstartcode, Long iamount,Long itickettypeid);

	/**
	 * Describe: 根据仓库ID查找仓库对象
	 * @auth:ChaoYuHang
	 * @param idetailsid  仓库ID
	 * @return     仓库对象
	 * return:Iomstationticketdetails
	 * Date:2012-7-24
	 */
	public Iomstationticketdetails findIomstationdetailsbyid(Long idetailsid);

	/**
	 * Describe:  检查票段是否在仓库中
	 * @auth:ChaoYuHang
	 * @param iomstocksbilldetails  单据详细对象
	 * @param iwarehouseid          仓库ID
	 * @param iamount               票数量
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(Iomstocksbilldetails iomstocksbilldetails,Long iwarehouseid,Long iamount);

	/**
	 * Describe:  检查票数量是否超出范围
	 * @auth:ChaoYuHang
	 * @param szstartcode  起始票号
	 * @param szendcode    截止票号
	 * @param iwarehouseid  仓库ID
	 * @param itickettypeid  票类型ID
	 * @param iamount       票数量
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkTicketIamount(String szstartcode,String szendcode,Long iwarehouseid,Long itickettypeid,Long iamount);

	/**
	 * Describe:  检查输入票段是否有重复交叉
	 * @auth:ChaoYuHang
	 * @param startcode  起始票段
	 * @param endcode    截止票段
	 * @param startcodeII  起始票段II
	 * @param endcodeII    截止票段II
	 * @param itickettypeid  票类型
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 */
	public boolean checkStartcodeandEndcode(String startcode, String endcode,String startcodeII, String endcodeII,Long itickettypeid);

	/**
	 * Describe:  保存操作
	 * @auth:ChaoYuHang
	 * @param iomstocksbilldetails 单据详细对象List
	 * @param iomstocksbill        单据对象
	 * @param syslog               系统日志对象
	 * @param bystockswayid        操作方式ID
	 * @return
	 * return:boolean
	 * Date:2012-7-24
	 * @throws Exception
	 */
	public boolean saveTicketChange(List<Iomstocksbilldetails> iomstocksbilldetails,Iomstocksbill iomstocksbill,Syslog syslog,Long bystockswayid) throws Exception;
}