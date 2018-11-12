package com.ectrip.ec.report.system.datereport.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IReportDataDAO extends IGenericDao {

	/**
	 * ����ʱ���ѯ������������
	 * Describe:
	 * @auth:huangyuqi
	 * @param typeʱ������
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-11-30
	 */
	public List updateOrQueryProviderByDate(String type,String date);
	/**
	 * ����ʱ���ѯ��������������ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-8
	 */
	public List updateOrQueryProviderList(String type,String date);
	/**
	 * ����ʱ���ѯ����Դ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-1
	 */
	public List updateOrQueryRegionByDate(String type,String date);
	/**
	 * ����ʱ���ѯ���ο���������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-1
	 */
	public List updateOrQueryCustomCountByDate(String type,String date);
	/**
	 * ����ʱ���ѯ����ƱԱ��Ʊ����Ʊ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param cztype������ʽ��01��Ʊ02��Ʊ��
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-1
	 */
	public List updateOrQuerySaleCountByDate(String type,String cztype,String date);
	/**
	 * ����ʱ���ѯ����������Ʊ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-1
	 */
	public List updateOrQueryLxsSaleByDate(String type,String date);
	
	/**
	 * ��ȡ�������¡��걨������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQueryProviderByType(String type,String date);
	/**
	 * ������������ϸ�¡����б���
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQueryProviderListByType(String type,String date);
	/**
	 * ��ѯ����Դ���¡��걨������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQueryRegionByType(String type,String date);
	/**
	 * ��ѯ���ο������¡��걨������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQueryCustomCountByType(String type,String date);
	/**
	 * ��ѯ����ƱԱ��Ʊ\��Ʊ�¡��걨������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param cztype������ʽ��01��Ʊ02��Ʊ��
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQuerySaleCountByType(String type,String cztype,String date);

	/**
	 * ��ѯ���������Ʊ�¡��걨������
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-16
	 */
	public List updateOrQueryLxsSaleByType(String type,String date);
	
	/**
	 * ��Ʊ���������ձ���
	 * Describe:
	 * @auth:huangyuqi
	 * @param type�������ͣ�1�գ�2�£�3�꣩
	 * @param date����
	 * @return
	 * return:List
	 * Date:2011-12-21
	 */
	public List updateOrquerySale(String type,String date);
	/**
	 * ��Ʊ�¡��걨��
	 * Describe:
	 * @auth:huangyuqi
	 * @param type�������ͣ�2�£�3�꣩
	 * @param date����
	 * @return
	 * return:List
	 * Date:2011-12-21
	 */
	public List updateOrquerySalebyType(String type,String date);
	/**
	 * ���ۻ��ܣ�����Ʒ��������ɢ�ͣ����壨ÿ��)
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date ��ʽ 2011-12-12
	 * @return
	 * return:List
	 * Date:2011-12-30
	 */
	public List updateOrQueryTicketSale(String type,String date);
	/**
	 * ���۱����¡��걨��
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date ��ʽ 2011-12-12
	 * @return
	 * return:List
	 * Date:2011-12-30
	 */
	public List updateOrQueryTicketSaleByType(String type,String date);	
	/**
	 * ǰ̨�������������۱����������ձ���
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-31
	 */
	public List updateOrQueryCusGroupSale(String type,String date);
	/**
	 * ǰ̨�������������۱����������£��걨��
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-12-31
	 */
	public List updateOrQueryCusGroupSaleByType(String type,String date);
	/**
	 * ɾ������ 
	 * Describe:
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 * return:void
	 * Date:2011-12-15
	 */
	public void deleteDates(String date,String type,String table);
	/**
	 * ɾ�����ϱ����� 
	 * Describe:
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 * return:void
	 * Date:2011-12-15
	 */
	public void deleteDatesByTable (String date,String type,String table);
	
	/**
	 * ɾ����ƱԱ����Ʊ������ 
	 * Describe:
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 * return:void
	 * Date:2011-12-15
	 */
	public void deleteDatesByEmpTable (String date,String type,String saletype,String table);
	
	/**
	 * 
	 * Describe:�������ڲ�ѯ��ʾ�������Ĳֿ���Ϣ
	 * @auth:lijingrui
	 * @param statdate����
	 * @param type	 ��������  �ձ���1  �±���2 �����걨��3
	 * @param sign		Ʊ����   1��Ԥ��Ʊ�� 2��IC�� 
	 * @return
	 * return:List
	 * Date:2012-8-27
	 */
	public List showAllHouseware(String statdate,Long type,Long sign); 
	
	/**
	 * 
	 * Describe:�����������ĳ�ֿ���������
	 * @auth:lijingrui
	 * @param iwarehouseid  �ֿ�ID
	 * @param statdate ����
	 * @param type     ��������  �ձ���1  �±���2 �����걨��3
	 * @param sign		Ʊ����   1��Ԥ��Ʊ�� 2��IC�� 
	 * @return
	 * return:List
	 * Date:2012-8-27
	 */
	public List showIamoutcheckin(Long iwarehouseid,String statdate,Long type,Long sign);
	
	/**
	 * 
	 * Describe:�����������ĳ�ֿ�ĳ�������
	 * @auth:lijingrui
	 * @param iwarehouseid  �ֿ�ID
	 * @param statdate ����
	 * @param type     ��������  �ձ���1  �±���2 �����걨��3
	 * @param sign		Ʊ����   1��Ԥ��Ʊ�� 2��IC�� 
	 * @return
	 * return:List
	 * Date:2012-8-27
	 */
	public List showIamoutcheckOut(Long iwarehouseid,String statdate,Long type,Long sign);
	
	/**
	 * 
	 * Describe:�������ڲ�ѯ����ĩ��������
	 * @auth:lijingrui
	 * @param statdate
	 * @param type  	��������  �ձ���1  �±���2 �����걨��3
	 * @param sign		Ʊ����   1��Ԥ��Ʊ�� 2��IC�� 
	 * @return
	 * return:Long
	 * Date:2012-8-28
	 */
	public Long checkRecenterout(Long iwarehouseid,Long itickettypeid,String statdate,Long type,Long sign);
	
	/**
	 * 
	 * Describe:�ж�ĳ���Ƿ�������
	 * @auth:lijingrui
	 * @param statdate
	 * @param type		��������  �ձ���1  �±���2 �����걨��3
	 * @param sign		Ʊ����   1��Ԥ��Ʊ�� 2��IC�� 
	 * @return
	 * return:List
	 * Date:2012-8-28
	 */
	public List checkRwswarehouse(String statdate,Long type,Long sign);
	
	/**
	 * 
	 * Describe:��ʾ��ĳ�����ó������ƱԱ
	 * @auth:lijingrui
	 * @param statdate
	 * @return
	 * return:List
	 * Date:2012-8-29
	 */
	public List showEmployeetab(String statdate,Long sign);
	
	/**
	 * 
	 * Describe:��ʾ��ĳ�����ó�����ƱԱ���õ�����
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @param sign
	 * @return
	 * return:Long
	 * Date:2012-12-8
	 */
	public Long showEmpidIamount(Long epmpid,Long itickettypeid,String statdate,Long sign);
	
	/**
	 * 
	 * Describe:��ʾ����ƱԱĳ����Ʊ������
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * return:Long
	 * Date:2012-8-29
	 */
	public Long showIompersonhouse(Long epmpid,Long itickettypeid,String statdate,Long sign);
	
	/**
	 * 
	 * Describe:��ʾ����ƱԱĳ����Ʊ������
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * return:Long
	 * Date:2012-11-15
	 */
	public Long checkUpnumsHouse(Long epmpid,Long itickettypeid,String statdate);
	
	/**
	 * 
	 * Describe:��������\��ƱԱ��ѯ����ĩ��������
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * return:Long
	 * Date:2012-8-29
	 */
	public Long showRwspersonal(Long epmpid,Long itickettypeid,String statdate,Long sign);
	
	/**
	 * 
	 * Describe:�������ڲ����ƱԱ��Ʊ��������
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @param sign
	 * @return
	 * return:Long
	 * Date:2012-10-30
	 */
	public Long showStockhouse(Long epmpid,Long itickettypeid,String statdate,Long sign);
	
	/**
	 * 
	 * Describe:��ѯ����ƱԱ����ʣ��Ԥ��Ʊ������
	 * @auth:lijingrui
	 * @param epmpid
	 * @param itickettypeid
	 * @return
	 * return:Long
	 * Date:2012-11-15
	 */
	public Long searchPersonDetails(Long epmpid,Long itickettypeid,Long sign);
	/**
	 * ����ʱ���ѯ������������<����Ʊͳ��>
	 * Describe:
	 * @auth:huangyuqi
	 * @param typeʱ������
	 * @param date
	 * @return
	 * return:List
	 * Date:2011-11-30
	 */
	public List updateOrQueryZProviderByDate(String type,String date);
	
	
	public List updateOrQueryempfzByDate(String date);
	public List updateOrQueryempfzBymonth(String date);
	public List updateOrQueryempfznumbByDate(String date);
	public List updateOrQueryempfznumbBymonth(String date);
}

