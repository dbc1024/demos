package com.ectrip.ticket.sale.service.iservice;

import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public interface ISaleCheckupService {

	/**
	 *
	 * Describe:显示圆门和检票次数
	 * @auth:lijingrui
	 * @param iscenicid   服务商ID
	 * @param iticktypeid 产品ID
	 * @param tickettypeid 子产品ID
	 * @return
	 * return:ResultBean
	 * Date:2012-9-5
	 */
	public ResultBean getGateTicketCount(Long iscenicid,Long iticktypeid,Long tickettypeid);
	/**
	 *
	 * Describe:增加写IC卡日志的类
	 * @author:chenxinhao
	 * @param szTicketPrintNo	票号
	 * @param Optype	1--售票，2--检票
	 * @param EquipmentID	机器编号
	 * @param ICBLOCK	块编号
	 * @param ICContent	内容
	 * @return
	 * return:int
	 * Date:2012-9-6
	 */
	public int IcWriteLog(String szTicketPrintNo, int Optype, int EquipmentID, String ICBLOCK, String ICContent);
	/**
	 *
	 * Describe:读取写入IC卡的内容
	 * @author:chenxinhao
	 * @param szTicketPrintNo	 卡号
	 * @param type	1--当天日志	2--历史日志
	 * @return
	 * return:ResultBean
	 * Date:2012-9-7
	 */
	public ResultBean GetICData(String szTicketPrintNo,int type);

	/**
	 *
	 * Describe:对IC卡票 修改售出门票表中的票号 并激活
	 * @auth:lijingrui
	 * @param iscenicid  服务商
	 * @param oldszticketno 旧票号
	 * @param newszticketno 新票号
	 * @return
	 * return:boolean
	 * Date:2012-9-13
	 */
	public boolean getVaildcheckCodeup(Long iscenicid,String oldszticketno,String newszticketno,Long iscz);

	/**
	 *
	 * Describe:酒店订单确认打印
	 * @auth:lijingrui
	 * @param orid
	 * @param iscenicid
	 * @param iemployeeid
	 * @param iticketwinid
	 * @param maxid
	 * @return
	 * @throws Exception
	 * return:ResultBean
	 * Date:2012-11-23
	 */
	public ResultBean savehoteltorder(String orid,Long iscenicid, Long iemployeeid, Long iticketwinid, Long maxid,String param)throws Exception;

	/**
	 *
	 * Describe:服务商信息显示
	 * @auth:lijingrui
	 * @param iscenicid
	 * @return
	 * return:Esbscenicareatab
	 * Date:2012-11-23
	 */
	public Esbscenicareatab getEsbscenicidshowup(Long iscenicid);

	/**
	 *
	 * Describe:查询出某人的站内短信公告
	 * @auth:lijingrui
	 * @param iemployeeid
	 * @return
	 * return:ResultBean
	 * Date:2013-1-23
	 */
	public ResultBean checkListwebinfo(Long iemployeeid);



	/**
	 * 身份证出票 激活数据
	 * @param iscenicid
	 * @param szticketno
	 * @param name
	 * @param zjno
	 * @return
	 */
	public boolean jihuobyzjno(Long iscenicid, String szticketno, String name,
							   String zjno);

	/**
	 *
	 * Describe:获取订单信息
	 * @author:lijingrui
	 * @param orid
	 * @return
	 * return:MOrder
	 * Date:2014-3-3
	 */
	public MOrder getMordercheck(String orid);

}

