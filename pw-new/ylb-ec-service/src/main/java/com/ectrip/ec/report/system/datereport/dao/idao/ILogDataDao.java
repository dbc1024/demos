package com.ectrip.ec.report.system.datereport.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface ILogDataDao extends IGenericDao {
	/**
	 * 
	 * Describe:��־ת�Ʋ���
	 * @author:chenxinhao
	 * @param date
	 * @param type
	 * @return
	 * return:List
	 * Date:2012-8-8
	 */
	public List updateOrQueryLog(String date,String type);
	/**
	 * ɾ��ԭ��־������
	 * Describe:
	 * @author:chenxinhao
	 * @param date
	 * @param table
	 * return:void
	 * Date:2012-8-8
	 */
	public void deleteDates(String date,String table);
}

