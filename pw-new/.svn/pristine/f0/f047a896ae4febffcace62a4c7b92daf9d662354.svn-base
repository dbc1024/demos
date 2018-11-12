package com.ectrip.ec.hotelcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.hotelcenter.dao.idao.IHotelOrderCenterDAO;
import com.ectrip.ec.hotelcenter.service.iservice.IHotelOrderCenterService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class HotelOrderCenterService extends GenericService implements IHotelOrderCenterService {
	
	
	private IHotelOrderCenterDAO hotelordercenterDao;
	@Autowired
	public void setHotelordercenterDao(IHotelOrderCenterDAO hotelordercenterDao) {
		this.hotelordercenterDao = hotelordercenterDao;
		super.setGenericDao(hotelordercenterDao);
	}

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
	public List queryHotelOrder(String orid,String stdt,Long iticketId){
		return hotelordercenterDao.queryHotelOrder(orid, stdt,iticketId);
	}
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
	public void updateHotelOrder(String orid,String ddzt,Long iticketId,String hycode,Syslog syslog,Orderlog orderlog){
		hotelordercenterDao.updateHotelOrder(orid, ddzt, iticketId,hycode, syslog, orderlog);
	}
	
	/**
	 * *
	 * Describe:根据条件查询订单 
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#queryAllOrder(com.ectrip.model.employee.Esfemployeetab, com.ectrip.model.order.QueryOrder, int, int, java.lang.String)
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-6-29
	 */
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab,
			QueryOrder order,String pdtp, int page, int pageSize, String url) {
		return hotelordercenterDao.queryAllOrder(esfemployeetab, order,pdtp, page, pageSize, url);
	}
	
	/**
	 * *
	 * Describe:查看订单基本信息
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getMOrderList(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public MOrder getMOrderList(String orid) {
		return hotelordercenterDao.getMOrderList(orid);
	}
	
	/**
	 * *
	 * Describe:查看订单预订信息
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getTOrderList(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public List getTOrderList(String orid,Long iscenicid) {
	    return hotelordercenterDao.getTOrderList(orid,iscenicid);
	}
	
	
	/**
	 * *
	 * Describe:查看订单明细
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getTOrderlists(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public List<TOrderlist> getTOrderlists(String orid,Long iscenicid) {
		return hotelordercenterDao.getTOrderlists(orid,iscenicid);
	}
	
	/**
	 * *
	 * Describe:获取退订金额
	 * @see com.ectrip.system.hotelcenter.dao.idao.IHotelOrderCenterDAO#lookUpzfmont(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-3
	 */
	public Double lookUpzfmont(String orid,Long iscenicid){
		return hotelordercenterDao.lookUpzfmont(orid,iscenicid);
	}
	/**
	 * *
	 * Describe:订单强制退订
	 * @see com.ectrip.system.hotelcenter.dao.idao.IHotelOrderCenterDAO#showViewzfmont(java.lang.String, java.lang.Long, java.lang.Double, com.ectrip.model.employee.Esfemployeetab)
	 * @param orid
	 * @param iscenicid
	 * @param totalmont
	 * @param esfemployeetab
	 * @author lijingrui
	 * Date:2012-7-3
	 */
	public void showViewzfmont(String orid,Long iscenicid,Double totalmont,Esfemployeetab esfemployeetab,String usid,Syslog syslog,Orderlog orderlog)throws Exception{
		hotelordercenterDao.showViewzfmont(orid, iscenicid, totalmont, esfemployeetab,usid,syslog,orderlog);
	}
	
	/**
	 * *
	 * Describe:查询逾期未领的订单数
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#queryYuQiOrderNumb(com.ectrip.model.employee.Esfemployeetab, java.lang.String)
	 * @param esfemployeetab
	 * @param scenictype
	 * @return
	 * @author lijingrui
	 * Date:2012-12-4
	 */
	 public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab,String scenictype){
		 return hotelordercenterDao.queryYuQiOrderNumb(esfemployeetab, scenictype);
	 }
}

