package com.ectrip.ec.report.system.order.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.Reservecontrol;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

public interface IQueryOrderDAO extends IGenericDao {
	/**
	 * 查询逾期未领的订单数
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab
	 * @return
	 * return:int
	 * Date:2011-10-31
	 */
	public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab);
	/**
	 * 根据条件查询订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab登录人信息
	 * @param order订单查询条件类
	 * @param page页码
	 * @param pageSize毎页显示数
	 * @param url访问地址
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-31
	 */
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url);
	public List queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order);
	/**
	 * 根据条件查询退订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-1
	 */
	public PaginationSupport queryAllMsOrder(Esfemployeetab esfemployeetab,QueryOrder order, int page, int pageSize,String url);
	
	/**
	 * 查询停排订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-4
	 */
	public PaginationSupport queryTingPaiOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url);
	
	
	/**
	 * 
	 * Describe:获取所有需要审核的订单
	 * @auth:yangguang
	 * @return
	 * return:PaginationSupport
	 * Date:2012-1-7
	 */
	public PaginationSupport queryAllAuditOrder(String iscenicid,int pagesize,int startIndex,String url,QueryOrder order);
	
	public List getTOrderDetail(String orid,String iscenicid);
	
	
	public List queryTOrderlistDetail(String orid,String iscenicid);
	
	/**
	 * 
	 * Describe:根据订单编号获取订单中趟次分组及预订的数量
	 * @auth:yangguang
	 * @param orid
	 * @param iscenicid
	 * @return
	 * return:List
	 * Date:2012-1-8
	 */
	public List getTripNumberByOrid(String orid,String iscenicid);
	
	
	public Productcontrol getProductControlByTripInfo(String date,String tripid,String itickettypeid);
	
	
	public void updateAuditOrderDdzt(String orid, String iscenicid,Long employeeid,String ddzt,String tporid) throws Exception;
	
	public PaginationSupport getOrderByRaftTrip(String orid,String itickettypeid,String date,Long tripid,String iscenicid,int pageSize,int starIndex,String url,String zfzt);
	
	
	
	public List getTZorderlist(String orid);
	
	public List getTZorderlistByOrid(String orid) ;
	
	public void updateDayControl(String orid, String date, String itickettypeid, String iscenicid,int numb);
	
	public void updateStock(String orid, TZorderlist zorderlist);
	
	public List getTorderlists(Long iscenicid,String orid);
	
	public List getTzorderlists(Long iscenicid,String orid,Long orderlistid);
	
	public List getAuditNum(QueryOrder order);
	
	public List getAllbussiness();
	public Reservecontrol getLxsreserve(Long ibssiness,String usid,Long productcontrolid);
	
	/**
	 * 
	 * Describe:现场支付订单查询
	 * @author:lijingrui
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-3-11
	 */
	public PaginationSupport xczfQueryAllOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url);
	
	/**
	 * 
	 * Describe:订单人数统计
	 * @author:zhangwubin
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-1-28
	 */
	public PaginationSupport orderNumbQuery(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url);
	
	/**
	 * 导出excel操作，获取订单信息
	 * Describe:
	 * @author:xiaogaoxiang
	 * @param esfemployeetab
	 * @param order
	 * @return
	 * return:List
	 * Date:2014-10-6
	 */
	public List getAllOrderInfo(Esfemployeetab esfemployeetab, QueryOrder order);
}

