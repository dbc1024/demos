package com.ectrip.ticket.warehouse.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;

public interface IHouseDAO extends IGenericDao{

	/**
	 *
	 * Describe:获取票号前缀和票代码
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @return
	 * return:String
	 * Date:2012-10-16
	 */
	public String ticketRuleView(Long itickettypeid);

	/**
	 *
	 * Describe:判断票号输入是否正确
	 * @auth:lijingrui
	 * @param ticketcode 票号
	 * @param itickettypeid 产品ID
	 * @return
	 * return:boolean
	 * Date:2012-10-16
	 */
	public boolean reShowtickrule(String ticketcode,Long itickettypeid);

	/**
	 *
	 * Describe:判断票号是否存在
	 * @auth:lijingrui
	 * @param stocks
	 * @return
	 * return:boolean
	 * Date:2012-10-16
	 */
	public boolean retrieWarehouse(Iomstocksbilldetails stocks);

	/**
	 *
	 * Describe:获取截止票号
	 * @auth:lijingrui
	 * @param ticketcode
	 * @param iamount
	 * @return
	 * return:String
	 * Date:2012-10-16
	 */
	public String showViewupendcode(String ticketcode,Long iamount,Long itickettypeid);

}

