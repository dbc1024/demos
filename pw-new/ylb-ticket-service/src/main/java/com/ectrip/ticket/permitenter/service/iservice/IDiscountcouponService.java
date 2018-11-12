package com.ectrip.ticket.permitenter.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Discountcoupon;

public interface IDiscountcouponService {
	
	
	/**
	 * Describe:�����Żݾ�
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return:void
	 * Date:2014-3-31
	 */
	
	public Discountcoupon insertDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog);
	
	
	/**
	 * Describe:��ѯ�Żݾ�
	 * @auth:shenzhixiong
	 * @param iscenicid
	 * @param dcstartdate
	 * @param dcenddate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * return:PaginationSupport
	 * Date:2014-4-1
	 */
	
	public PaginationSupport discountcouponList(Long iscenicid,String dcstartdate,String dcenddate,int pageSize, int startIndex, String url);
	
	
	/**
	 * Describe:����id��ѯ�Żݾ�
	 * @auth:shenzhixiong
	 * @param dcid
	 * return:discountcoupon
	 * Date:2014-4-2
	 * @throws Exception 
	 */
	public Discountcoupon getDiscountcouponFindByID(Long dcid) throws Exception;
	
	/**
	 * Describe:ɾ���Żݾ�
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return:void
	 * Date:2014-3-31
	 */
	
	public void deleteDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog);
	
	
	/**
	 * Describe:�޸��Żݾ���Ϣ
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return void
	 * Date:2014-3-31
	 */
	public void updateDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog);
	
	
	public int verifyDisountcoupon(Discountcoupon discountcoupon,boolean b);

}
