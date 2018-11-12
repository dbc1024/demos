package com.ectrip.ticket.permitenter.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.ResultBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IVoucherDAO extends IGenericDao{
	
	/**
	 * 
	 * Describe:根据服务商查询可现场销售的产品
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2012-10-23
	 */
	public List showAllticket(Long iscenicid,Long ibusinessid,String startdate);
	
	/**
	 * 
	 * Describe:查出所有业务类型
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2012-10-26
	 */
	public List businessList(Long ibusinessid);
	
	/**
	 * 
	 * Describe:显示某产品某业务的价格
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param ibusinessid
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2012-10-26
	 */
	public List showpriceviewup(Long iscenicid,Long itickettypeid,Long ibusinessid,String startdate) throws Exception;
	
	/**
	 * 
	 * Describe:根据业务类型获取相应的用户
	 * @auth:lijingrui
	 * @param ibusinessid
	 * @return
	 * return:List
	 * Date:2012-11-6
	 */
	public List getCustomview(Long ibusinessid);
	
	/**
	 * 
	 * Describe:获取销售凭证
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param iemployeeid
	 * @param jsprice
	 * @return
	 * return:String
	 * Date:2012-10-26
	 */
	public String showSalevouchers(Long iscenicid,Long iemployeeid,Long ibusinessid,Double jsprice,String usid,String zzlb,Esfemployeetab esfemployeetab);
	
	/**
	 * 
	 * Describe:销售凭证明细显示
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * return:String
	 * Date:2012-10-29
	 */
	public String showSalevoucherDetail(Long itickettypeid,Long icrowdkindpriceid,Long numble,String startdate);
	
	/**
	 * 
	 * Describe:子票明细显示
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * return:String
	 * Date:2012-10-29
	 */
	public String showComticketsaleDetail(Long itickettypeid,Long icrowdkindpriceid,Long numble,String startdate);
	
	/**
	 * 武夷山
	 * Describe:冲正凭证保存
	 * @auth:lijingrui
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @param startdate
	 * @return
	 * return:ResultBean
	 * Date:2012-11-6
	 */
	public ResultBean saveOrder(String salesvouchers, String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols,String startdate,Syslog syslog);
	
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
			String productcontrols,String startdate,Syslog syslog,String url);
	
}

