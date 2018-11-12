package com.ectrip.ticket.permitenter.service.iservice;

import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IVoucherService {
	
	/**
	 * 
	 * Describe:���ݷ����̲�ѯ���ֳ����۵Ĳ�Ʒ
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2012-10-23
	 */
	public List showAllticket(Long iscenicid,Long ibusinessid,String startdate);
	
	/**
	 * 
	 * Describe:�������ҵ������
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2012-10-26
	 */
	public List businessList(Long ibusinessid);
	
	/**
	 * 
	 * Describe:��ʾĳ��Ʒĳҵ��ļ۸�
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
	 * Describe:����ҵ�����ͻ�ȡ��Ӧ���û�
	 * @auth:lijingrui
	 * @param ibusinessid
	 * @return
	 * return:List
	 * Date:2012-11-6
	 */
	public List getCustomview(Long ibusinessid);
	
	/**
	 * 
	 * Describe:��ȡ����ƾ֤
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
	 * Describe:����ƾ֤��ϸ��ʾ
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
	 * Describe:��Ʊ��ϸ��ʾ
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
	 * 
	 * Describe:����ƾ֤����
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
	 * Describe:����
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

