package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.permitenter.dao.IVoucherDAO;
import com.ectrip.ticket.permitenter.service.iservice.IVoucherService;

public class VoucherService implements IVoucherService{
	
	IVoucherDAO voucherDao;
	
	public IVoucherDAO getVoucherDao() {
		return voucherDao;
	}
	
	public void setVoucherDao(IVoucherDAO voucherDao) {
		this.voucherDao = voucherDao;
	}

	/**
	 * *
	 * Describe:根据服务商查询可现场销售的产品
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showAllticket(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:2012-10-23
	 */
	public List showAllticket(Long iscenicid,Long ibusinessid,String startdate) {
		return voucherDao.showAllticket(iscenicid,ibusinessid,startdate);
	}
	
	/**
	 * *
	 * Describe:查出所有业务类型
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#businessList()
	 * @return
	 * @author lijingrui
	 * Date:2012-10-26
	 */
	public List businessList(Long ibusinessid){
		return voucherDao.businessList(ibusinessid);
	}
	
	/**
	 * *
	 * Describe:显示某产品某业务的价格
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showpriceviewup(java.lang.Long, java.lang.Long)
	 * @param itickettypeid
	 * @param ibusinessid
	 * @return
	 * @author lijingrui
	 * Date:2012-10-26
	 */
	public List showpriceviewup(Long iscenicid,Long itickettypeid,Long ibusinessid,String startdate) throws Exception{
		return voucherDao.showpriceviewup(iscenicid,itickettypeid, ibusinessid,startdate);
	}
	
	/**
	 * *
	 * Describe:根据业务类型获取相应的用户
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#getCustomview(java.lang.Long)
	 * @param ibusinessid
	 * @return
	 * @author lijingrui
	 * Date:2012-11-6
	 */
	public List getCustomview(Long ibusinessid){
		return voucherDao.getCustomview(ibusinessid);
	}
	
	/**
	 * *
	 * Describe:获取销售凭证
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#showSalevouchers(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Double)
	 * @param iscenicid
	 * @param iemployeeid
	 * @param ibusinessid
	 * @param jsprice
	 * @return
	 * @author lijingrui
	 * Date:2012-10-29
	 */
	public String showSalevouchers(Long iscenicid,Long iemployeeid,Long ibusinessid,Double jsprice,String usid,String zzlb,Esfemployeetab esfemployeetab){
		return voucherDao.showSalevouchers(iscenicid, iemployeeid, ibusinessid, jsprice,usid,zzlb,esfemployeetab);
	}
	
	/**
	 * *
	 * Describe:销售凭证明细显示
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#showSalevoucherDetail(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * @author lijingrui
	 * Date:2012-10-29
	 */
	public String showSalevoucherDetail(Long itickettypeid,Long icrowdkindpriceid,Long numble,String startdate){
		return voucherDao.showSalevoucherDetail(itickettypeid, icrowdkindpriceid, numble, startdate);
	}
	
	/**
	 * *
	 * Describe:子票明细显示
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#showComticketsaleDetail(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * @author lijingrui
	 * Date:2012-10-29
	 */
	public String showComticketsaleDetail(Long itickettypeid,Long icrowdkindpriceid,Long numble,String startdate){
		return voucherDao.showComticketsaleDetail(itickettypeid, icrowdkindpriceid, numble, startdate);
	}
	
	/**
	 * *
	 * Describe:冲正凭证保存
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#saveOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @param startdate
	 * @return
	 * @author lijingrui
	 * Date:2012-11-6
	 */
	public ResultBean saveOrder(String salesvouchers, String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols,String startdate,Syslog syslog){
		return voucherDao.saveOrder(salesvouchers, salesvoucherdetails, comticketsalesdetails, productcontrols, startdate,syslog);
	}
	
	/**
	 * 
	 * Describe:保存
	 * @auth:lijingrui
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @param startdate
	 * @return
	 * return:ResultBean
	 * Date:2012-11-7
	 */
	public ResultBean saveorder41(String salesvouchers, String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols,String startdate,Syslog syslog,String url) {
		return voucherDao.saveorder41(salesvouchers, salesvoucherdetails, comticketsalesdetails, productcontrols, startdate,syslog,url);
	}
	
}

