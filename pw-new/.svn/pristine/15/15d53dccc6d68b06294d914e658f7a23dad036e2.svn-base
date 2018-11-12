package com.ectrip.ticket.permitenter.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Discountcoupon;
import com.ectrip.ticket.permitenter.dao.IDiscountcouponDAO;
import com.ectrip.ticket.permitenter.service.iservice.IDiscountcouponService;

public class DiscountcouponService implements IDiscountcouponService{

	IDiscountcouponDAO discountcouponDAO;
	
	public IDiscountcouponDAO getDiscountcouponDAO() {
		return discountcouponDAO;
	}

	public void setDiscountcouponDAO(IDiscountcouponDAO discountcouponDAO) {
		this.discountcouponDAO = discountcouponDAO;
	}

	/**
	 * Describe:ɾ���Żݾ�
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return:void
	 * Date:2014-3-31
	 */
	public void deleteDiscountcoupon(Discountcoupon discountcoupon,
			Syslog syslog) {
		discountcouponDAO.deleteDiscountcoupon(discountcoupon, syslog);
	}

	/**
	 * Describe:�����Żݾ�
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return:void
	 * Date:2014-3-31
	 */
	public Discountcoupon insertDiscountcoupon(Discountcoupon discountcoupon,
			Syslog syslog) {
		return discountcouponDAO.insertDiscountcoupon(discountcoupon, syslog);
	}

	
	/**
	 * Describe:�޸��Żݾ���Ϣ
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return void
	 * Date:2014-3-31
	 */
	public void updateDiscountcoupon(Discountcoupon discountcoupon,
			Syslog syslog) {
		discountcouponDAO.updateDiscountcoupon(discountcoupon, syslog);
	}

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
	 * Date:2014-3-31
	 */
	public PaginationSupport discountcouponList(Long iscenicid,
			String dcstartdate, String dcenddate, int pageSize, int startIndex,
			String url) {
		return discountcouponDAO.discountcouponList(iscenicid, dcstartdate, dcenddate, pageSize, startIndex, url);

	}

	/**
	 * Describe:����id��ѯ�Żݾ�
	 * @auth:shenzhixiong
	 * @param dcid
	 * return:discountcoupon
	 * Date:2014-4-2
	 * @throws Exception 
	 */
	public Discountcoupon getDiscountcouponFindByID(Long dcid) throws Exception
	{
		return discountcouponDAO.getDiscountcouponFindByID(dcid);
	}
	
	
	public int verifyDisountcoupon(Discountcoupon discountcoupon,boolean b)
	{
		return discountcouponDAO.verifyDisountcoupon(discountcoupon,b);
	}
	
}
