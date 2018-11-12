package com.ectrip.ec.report.system.datereport.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IReportsListDAO extends IGenericDao{
	/**
	 * ��������ƾ֤������
	 * Describe:
	 * @auth:huangyuqi
	 * return:void
	 * Date:2011-12-17
	 */
	public void updateSaleTicketList(String dates);
	/**
	 * �����Ʊ��ʷ����(����Ʊ��Ʊ�ӱ�õ�������Դ)
	 * Describe:
	 * @auth:huangyuqi
	 * @param datenums ��ǰ����
	 * return:void
	 * 
	 * Date:2011-12-17
	 */
	public void updateCheckTicketList(String dates,int datenums );
	
	/**
	 * ��ʷ�������ݲ�ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @param dates��������
	 * return:void
	 * Date:2012-3-30
	 */
	public List queryReportList(String dates);
	/**
	 * ����ƾ֤�Ų�ѯ��ˮ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param dates��������
	 * return:void
	 * Date:2012-3-30
	 */
	public List querySzsalesvoucherno(Long isalesvoucherid);
	/**
	 * �ж������Ƿ���ڣ����������ɾ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablename
	 * @param colsname
	 * @param dates
	 * return:void
	 * Date:2012-3-30
	 */
	public void deleteReportListDates(String tablename,String colsname,Long dates);
	/**
	 * ɾ����ӡ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablename
	 * @param colsname
	 * @param dates
	 * return:void
	 * Date:2012-3-30
	 */
	public void deleteSalePrint(String tablename,String colsname,String dates);
	/**
	 *  ��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param colsname������
	 * @param tablename�����飨���ݱ��ڵ�һ������ ���ݵı��ڵڶ��������ϸ�ִ�У����������쳣��
	 * @param cols Ҫ�������ݵĲ�ѯ����
	 * @param dates Ҫ����ֵ
	 * return:void
	 * Date:2012-3-30
	 */
	public void addReportListDates(String[] colsname,String[] tablename,String cols,Long dates);
		

}

