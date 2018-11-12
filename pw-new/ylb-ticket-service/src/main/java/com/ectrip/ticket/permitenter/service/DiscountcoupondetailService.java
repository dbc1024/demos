package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Discountcoupondetail;
import com.ectrip.ticket.permitenter.dao.IDiscountcoupondetailDAO;
import com.ectrip.ticket.permitenter.service.iservice.IDiscountcoupondetailService;

public class DiscountcoupondetailService implements IDiscountcoupondetailService{

	IDiscountcoupondetailDAO discountcoupondetailDAO;
	
	public IDiscountcoupondetailDAO getDiscountcoupondetailDAO() {
		return discountcoupondetailDAO;
	}

	public void setDiscountcoupondetailDAO(
			IDiscountcoupondetailDAO discountcoupondetailDAO) {
		this.discountcoupondetailDAO = discountcoupondetailDAO;
	}

	/**
	 * Describe:ɾ���Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param code
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public void deleteDiscountcoupondetail(String code,Long sum, Syslog syslog) {
		discountcoupondetailDAO.deleteDiscountcoupondetail(code,sum, syslog);
	}

	/**
	 * Describe:�����Ż݄������ѯ�Ż݄���ϸ��Ϣ
	 * @auth:shenzhixiong
	 * @param dtmakedate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return list
	 * Date:2014-4-1
	 */
	public PaginationSupport getdiscountcoupondetail(Long dcid,int pageSize, int startIndex, String url)
	{
		return discountcoupondetailDAO.getdiscountcoupondetail(dcid, pageSize, startIndex, url);
	}
	
	
	/**
	 * Describe������״̬�õ��ͱ���õ���ʹ�õ�����
	 * auth:shenzhiiong
	 * @param szcode
	 * @param b 
	 * return Integer;
	 * Date:2014-4-4
	 */
	
	public int findDiscountcoupondetailBySzcodeAndByisuser(Long dcid,boolean b)
	{
		return discountcoupondetailDAO.findDiscountcoupondetailBySzcodeAndByisuser(dcid,b);
	}
	
	/**
	 * Describe: �����Żݾ����õ��Żݾ���ϸ
	 * auth:shenzhixiong
	 * @param szcode
	 * return discountcoupondetail
	 * Date:2014-4-4
	 * @throws Exception 
	 */
	
	public Discountcoupondetail findDiscountcoupondetailBySzcode(String szcode) throws Exception
	{
		return discountcoupondetailDAO.findDiscountcoupondetailBySzcode(szcode);
	}
	
	
	
	/**
	 * Describe:����Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param list
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public void insertDiscountcoupondetail(List<Discountcoupondetail> list,
			Syslog syslog) {
		discountcoupondetailDAO.insertDiscountcoupondetail(list, syslog);
	}

	/**
	 * Describe:�޸��Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param discountcoupondetail
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public void updateDiscountcoupondetail(
			Discountcoupondetail discountcoupondetail, Syslog syslog) {
		discountcoupondetailDAO.updateDiscountcoupondetail(discountcoupondetail, syslog);
	}

	
	 
 
}
