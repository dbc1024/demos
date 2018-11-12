package com.ectrip.ec.book.rentrl.news.custag.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IHotCarTypeDao extends IGenericDao {
	/**
	 * ���ų���
	 * findHotCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param top
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-19 ����08:59:30
	 * @since  1.0.0
	 */
	public List findHotCarType(String top);
	/**
	 * �����⳵·��
	 * findJDCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param top
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-19 ����08:59:39
	 * @since  1.0.0
	 */
	public List findJDCarType(String top);
	/**
	 * �Ƽ��⳵·��
	 * findTJCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param top
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-19 ����08:59:52
	 * @since  1.0.0
	 */
	public List findTJCarType(String top);
}
