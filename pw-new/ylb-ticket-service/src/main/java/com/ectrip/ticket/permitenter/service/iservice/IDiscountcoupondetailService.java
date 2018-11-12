package com.ectrip.ticket.permitenter.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Discountcoupondetail;

public interface IDiscountcoupondetailService {

	
	/**
	 * Describe:����Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param list
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	
	public void insertDiscountcoupondetail(List<Discountcoupondetail> list,Syslog syslog);
	
	
	/**
	 * Describe:ɾ���Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param code
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	
	public void deleteDiscountcoupondetail(String  code,Long sum,Syslog syslog);
	
	/**
	 * Describe:�޸��Ż݄���ϸ
	 * @auth:shenzhixiong
	 * @param discountcoupondetail
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	
	public void updateDiscountcoupondetail(Discountcoupondetail discountcoupondetail,Syslog syslog);
	
	
	
	
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
	public PaginationSupport getdiscountcoupondetail(Long dcid,int pageSize, int startIndex, String url);
	
	
	/**
	 * Describe������״̬�õ��ͱ���õ�����
	 * auth:shenzhiiong
	 * @param szcode
	 * @param b
	 * return Integer;
	 * Date:2014-4-4
	 */
	
	public int findDiscountcoupondetailBySzcodeAndByisuser(Long dcid,boolean b);
	
	
	/**
	 * Describe: �����Żݾ����õ��Żݾ���ϸ
	 * auth:shenzhixiong
	 * @param szcode
	 * return discountcoupondetail
	 * Date:2014-4-4
	 */
	
	public Discountcoupondetail findDiscountcoupondetailBySzcode(String szcode) throws Exception;
	
	
	
	
}
