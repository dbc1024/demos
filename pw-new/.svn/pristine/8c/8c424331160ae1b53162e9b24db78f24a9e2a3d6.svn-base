package com.ectrip.ec.report.system.datereport.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.report.sales.Defindsbalance;
import com.ectrip.ec.model.report.sales.Definedwarrants;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;

public interface IDefinedWarantService {
	
	/**
	 * 
	 * Describe:��ʾ��ƱԱ�ɿ���Ϣ
	 * @auth:lijingrui
	 * @param stdate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-11-30
	 */
	public PaginationSupport showListdefinedant(Long empid,String stdate,Long icompanyinfoid,int pageSize,int startIndex, String url);

	/**
	 * 
	 * Describe:��ȡĳ��������ƱԱ�����۽��
	 * @auth:lijingrui
	 * @param zffs
	 * @param empid
	 * @param stdate
	 * @return
	 * return:Double
	 * Date:2013-5-2
	 */
	public Double lookCheckviewmoney(String zffs,Long empid,String stdate);
	
	/**
	 * 
	 * Describe:��ѯ���ɿ������б���Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2013-3-2
	 */
	public List getListospbankpay();

	/**
	 * 
	 * Describe:��ѯ���ɿ�������Ϣ�µ��˺�
	 * @auth:lijingrui
	 * @param bankname
	 * @return
	 * return:Ospbankpayeesettab
	 * Date:2013-3-2
	 */
	public Ospbankpayeesettab showUpbankcount(String bankname);
	
	/**
	 * 
	 * Describe:��ѯ����¼��������ҵ�µ�����Ա��
	 * @auth:lijingrui
	 * @param companyid
	 * @return
	 * return:List
	 * Date:2012-11-30
	 */
	public List getListemployee(Long iemployeeid);
	
	/**
	 * 
	 * Describe:�����ƱԱ�ɿ���Ϣ
	 * @auth:lijingrui
	 * @param definedwants
	 * @param syslog
	 * return:void
	 * Date:2012-11-30
	 */
	public void addDefinedwarant(Definedwarrants definedwants,Syslog syslog);
	
	/**
	 * 
	 * Describe:�鿴��ƱԱ�ɿ���Ϣ
	 * @auth:lijingrui
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Definedwarrants
	 * Date:2012-11-30
	 */
	public Definedwarrants viewDefinedwarant(Long seq) throws Exception;
	
	/**
	 * 
	 * Describe:��ѯ��ƱԱ��ĳʱ���ǰ�Ƿ���δ����Ľɿ��¼
	 * @author:chenxinhao
	 * @param empid		��ƱԱID�����ǵ�����ƱԱID��Ҳ������ƱԱ���ID����1,2,3,4
	 * @param date		ʱ��
	 * @return			������ƱԱ���ƺ�δ������·�
	 * return:List
	 * Date:2012-12-7
	 */
	public List checkdefindate(String empid,String date);
	
	/**
	 * 
	 * Describe:��ʾĳ�µĽɿ���Ϣ
	 * @auth:lijingrui
	 * @param empid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-12-10
	 */
	public PaginationSupport checkListdefinedant(Long empid,String stdate,Long icompanyinfoid,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:��ѯ�����Ƿ����
	 * @author:chenxinhao
	 * @param empid
	 * @param date
	 * @return
	 * return:List
	 * Date:2012-12-10
	 */
	public List defindantcheckin(String empid,String date);
	
	/**
	 * 
	 * Describe:��ѯ�� �ɿ��ܽ��
	 * @auth:lijingrui
	 * @param empid
	 * @param date
	 * @return
	 * return:Double
	 * Date:2012-12-10
	 */
	public Double getBalancemony(String empid,String date);
	
	/**
	 * 
	 * Describe:��ѯ�� �����ܽ��
	 * @auth:lijingrui
	 * @param empid
	 * @param date
	 * @return
	 * return:Double
	 * Date:2012-12-10
	 */
	public Double getBalancexsmony(String empid,String date);
	
	/**
	 * 
	 * Describe:�жϴ���ƱԱ��ĳ���Ƿ�ɹ���
	 * @auth:lijingrui
	 * @param empid
	 * @param date
	 * @return
	 * return:boolean
	 * Date:2012-12-11
	 */
	public boolean getViewfindwarant(Long empid,String date);
	
	/**
	 * 
	 * Describe:�ɿ�����¼����
	 * @auth:lijingrui
	 * @param balance
	 * @param list
	 * @param syslog
	 * return:void
	 * Date:2012-12-11
	 */
	public void getSavedefindbalance(Defindsbalance balance,Syslog syslog);
	
	/**
	 * 
	 * Describe:�鿴�ɿ�����¼
	 * @auth:lijingrui
	 * @param empid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-12-12
	 */
	public PaginationSupport getListbalance(Long empid,String stdate,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:�鿴�ɿ������ϸ��Ϣ
	 * @auth:lijingrui
	 * @param ibalanceid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-12-12
	 */
	public PaginationSupport getViewbalanceListab(Long ibalanceid,String startdate,String enddate,int pageSize,int startIndex, String url);

	/**
	 * 
	 * Describe:�õ����е�Ա���б�
	 * @auth:lijingrui
	 * @param employeeId
	 * @return
	 * return:List
	 * Date:2013-5-3
	 */
	public List getEmployeeAllList(Long employeeId);
	
	/**
	 * 
	 * Describe:�жϴ���ƱԱ��ĳ���ڶ����Ƿ�ɹ���
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * return:boolean
	 * Date:2013-5-22
	 */
	public boolean getViewfindwarant(Long empid,String startdate,String enddate);
	
	/**
	 * 
	 * Describe:��ѯ��ƱԱ��ĳʱ���ǰ�Ƿ���δ����Ľɿ��¼
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * return:List
	 * Date:2013-5-22
	 */
	public List checkdefindate(String empid,String startdate,String enddate);
	
	/**
	 * 
	 * Describe:��ѯ�� �ɿ��ܽ��
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * return:Double
	 * Date:2013-5-22
	 */
	public Double getBalancemony(String empid,String startdate,String enddate);
	
	/**
	 * 
	 * Describe:��ѯ�� �����ܽ��
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * return:Double
	 * Date:2013-5-22
	 */
	public Double getBalancexsmony(String empid,String startdate,String enddate);
	
	/**
	 * 
	 * Describe:��ʾĳ���ڶ��ڵĽɿ���Ϣ
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2013-5-22
	 */
	public PaginationSupport checkListdefinedant(Long empid,String startdate,String enddate,Long icompanyinfoid,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:��ѯĳ���ڶ��Ƿ����
	 * @auth:lijingrui
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * return:List
	 * Date:2013-5-23
	 */
	public List checkFindbalance(String empid,String startdate,String enddate);
	
}

