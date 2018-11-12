package com.ectrip.ticket.permitenter.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;

public interface IEsbtripequiptabService {
	/**
	 * 
	 * Describe:�б���ʾ�����еľ����г���Ϣ
	 * @auth:lijingrui
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Dec 23, 2011
	 */
	public PaginationSupport getlistesbtripequip(int pageSize,int startIndex, String url); 
	
	/**
	 * 
	 * Describe:����г���Ϣ
	 * @auth:lijingrui
	 * @param esbtrip
	 * @param syslog
	 * return:void
	 * Date:Dec 24, 2011
	 */
	public void addesbtripequip(Esbtripequiptab esbtrip,Syslog syslog);
	
	/**
	 * 
	 * Describe:�޸��г���Ϣ
	 * @auth:lijingrui
	 * @param esbtrip
	 * @param syslog
	 * return:void
	 * Date:Dec 24, 2011
	 */
	public void editesbtripequip(Esbtripequiptab esbtrip,Syslog syslog);
	
	/**
	 * 
	 * Describe:ɾ���г���Ϣ
	 * @auth:lijingrui
	 * @param esbtrip
	 * @param syslog
	 * return:void
	 * Date:Dec 24, 2011
	 */
	public void delesbtripequip(Esbtripequiptab esbtrip,Syslog syslog);
	
	/**
	 * 
	 * Describe:�鿴�г���Ϣ
	 * @auth:lijingrui
	 * @param itripid
	 * @return
	 * @throws Exception
	 * return:Esbtripequiptab
	 * Date:Dec 24, 2011
	 */
	public Esbtripequiptab getviewesbtripid(Long itripid) throws Exception ;
	
	/**
	 * 
	 * Describe:��ʾ����A��Ϣ
	 * @auth:lijingrui
	 * @param scenics
	 * @return
	 * return:List
	 * Date:Dec 24, 2011
	 */
	public String getListequip(String scenics,Long byisstend);
	
	/**
	 * 
	 * Describe:��ʾ����B��Ϣ
	 * @auth:lijingrui
	 * @param scenics
	 * @param byisvenue
	 * @return
	 * return:String
	 * Date:Dec 26, 2011
	 */
	public String getListgardip(String scenics,Long byisvenue);
	
	/**
	 * 
	 * Describe:��ʾ����������ʱ
	 * @auth:lijingrui
	 * @param scenicname
	 * @return
	 * return:String
	 * Date:Dec 26, 2011
	 */
	public String getViewszdate(Long scenicid);
	
	/**
	 * 
	 * Describe:�ж϶������ʱ�Ƿ����
	 * @auth:lijingrui
	 * @param iscenicaid
	 * @param iscenicbid
	 * @return
	 * return:boolean
	 * Date:Dec 24, 2011
	 */
	public boolean getshopbeantak(Long iscenicaid,Long iscenicbid);
}

