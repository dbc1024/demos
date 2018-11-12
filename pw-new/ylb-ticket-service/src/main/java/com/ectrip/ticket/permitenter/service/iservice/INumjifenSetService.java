package com.ectrip.ticket.permitenter.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifenset;

public interface INumjifenSetService {
	/**
	 * 
	 * Describe:显示出所有的规则信息
	 * @auth:lijingrui
	 * @param jflb
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 29, 2012
	 */
	public PaginationSupport showAllviewNumjifen(String scenics,Long jflb,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:添加规则
	 * @auth:lijingrui
	 * @param numset
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void insertNumjifen(Numjifenset numset);
	
	/**
	 * 
	 * Describe:修改规则
	 * @auth:lijingrui
	 * @param numset
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void updateNumjifen(Numjifenset numset);
	
	/**
	 * 
	 * Describe:删除规则
	 * @auth:lijingrui
	 * @param nid
	 * return:void
	 * Date:Mar 29, 2012
	 */
	public void deleteNumjifen(Long nid);
	
	/**
	 * 
	 * Describe:查看规则
	 * @auth:lijingrui
	 * @param nid
	 * @return
	 * @throws Exception
	 * return:Numjifenset
	 * Date:Mar 29, 2012
	 */
	public Numjifenset viewNumjifen(Long nid) throws Exception;
}

