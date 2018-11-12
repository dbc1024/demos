package com.ectrip.ec.hotelcenter.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;

public interface IHotelOrderCenterDAO extends IGenericDao {
	
	/**
	 * 根据订单查询要审核的订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid 订单号
	 * @param stdt 入住时间
	 * @param iticketId 产品编号
	 * @return
	 * return:List
	 * Date:2012-3-4
	 */
	public List queryHotelOrder(String orid,String stdt,Long iticketId);
	/**
	 * 审核订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid 订单号
	 * @param ddzt 订单状态
	 * @param orderlist 订单列表
	 * @param syslog 系统日志
	 * @param orderlog 订单操作日志
	 * return:void
	 * Date:2012-3-5
	 */
	public void updateHotelOrder(String orid,String ddzt,Long iticketId,String hycode,Syslog syslog,Orderlog orderlog);
	
	/**
	 * 
	 * Describe:根据条件查询订单 
	 * @auth:lijingrui
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-6-29
	 */
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order,String pdtp, int page, int pageSize, String url);
	
	/**
	 * 
	 * Describe:查看订单基本信息
	 * @auth:lijingrui
	 * @param orid
	 * @return
	 * return:MOrder
	 * Date:2012-7-2
	 */
	public MOrder getMOrderList(String orid);
	
	/**
	 * 
	 * Describe:查看订单预订信息
	 * @auth:lijingrui
	 * @param orid
	 * @return
	 * return:List
	 * Date:2012-7-2
	 */
	public List getTOrderList(String orid,Long iscenicid);
	
	/**
	 * 
	 * Describe:查看订单明细
	 * @auth:lijingrui
	 * @param orid
	 * @return
	 * return:List<TOrderlist>
	 * Date:2012-7-2
	 */
	public List<TOrderlist> getTOrderlists(String orid,Long iscenicid);
	
	/**
	 * 
	 * Describe:获取退订金额
	 * @auth:lijingrui
	 * @param orid
	 * @return
	 * return:Double
	 * Date:2012-7-3
	 */
	public Double lookUpzfmont(String orid,Long iscenicid);
	
	/**
	 * 
	 * Describe:订单强制退订
	 * @auth:lijingrui
	 * @param orid
	 * @param iscenicid
	 * @param totalmont
	 * @param esfemployeetab
	 * return:void
	 * Date:2012-7-3
	 */
	public void showViewzfmont(String orid,Long iscenicid,Double totalmont,Esfemployeetab esfemployeetab,String usid,Syslog syslog,Orderlog orderlog)throws Exception;
	
	/**
	 * 
	 * Describe:查询逾期未领的订单数
	 * @auth:lijingrui
	 * @param esfemployeetab
	 * @param scenictype
	 * @return
	 * return:int
	 * Date:2012-12-4
	 */
	 public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab,String scenictype);
}

