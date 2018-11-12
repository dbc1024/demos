package com.ectrip.ticket.warehouse.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;

public interface IHouseTicketoutDao extends IGenericDao {
	/**
	 *
	 * Describe:所有仓库结存明细信息
	 * @author:chenxinhao
	 * @param istationoutid	仓库ID
	 * @param itickettypeid	票类型ID
	 * @return
	 * return:List
	 * Date:2012-7-13
	 */
	public List findAllStationticket(long istationoutid,long itickettypeid);
	/**
	 *
	 * Describe:检查票号是否在该票段范围内
	 * @author:chenxinhao
	 * @param ticketid	   票ID
	 * @param ticketCode 待检查票号
	 * @param idetailsid 仓库结存明细ID
	 * @return
	 * return:boolean
	 * Date:2012-7-17
	 */
	public boolean checkTicketcodeRange(long ticketid,String ticketCode,long idetailsid);
	/**
	 *
	 * Describe:计算票数量
	 * @author:chenxinhao
	 * @param ticketid	票ID
	 * @param startcode	起始票号
	 * @param endcode	截止票号
	 * @return
	 * return:long
	 * Date:2012-7-19
	 */
	public long showiamount(long ticketid,String startcode,String endcode);
	/**
	 *
	 * Describe:判断票段是否存在
	 * @author:chenxinhao
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * return:boolean
	 * Date:2012-7-20
	 */
	public boolean retriedStadetails(Iomstationticketdetails stadeta,long istationoutid);
	/**
	 *
	 * Describe:领票出库 保存
	 * @author:chenxinhao
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @throws Exception
	 * return:boolean
	 * Date:2012-7-20
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstationticketdetails> detailList,Syslog syslog) throws Exception;
	/**
	 *
	 * Describe:仓库明细表 个人明细表 添加和票段整理
	 * @author:chenxinhao
	 * @param stockList
	 * return:void
	 * Date:2012-7-20
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList);
	/**
	 *
	 * Describe:获取截止票号
	 * @author:chenxinhao
	 * @param ticketcode
	 * @param iamount
	 * @param itickettypeid
	 * @return
	 * return:String
	 * Date:2012-7-23
	 */
	public String showViewupendcode(String ticketcode, Long iamount,Long itickettypeid);
	/**
	 *
	 * Describe:根据服务商id查找子票信息
	 * @author:chenxinhao
	 * @param iscenicid
	 * @return
	 * return:List
	 * Date:2012-8-1
	 */
	public List showAllzticket(Long iscenicid);
}

