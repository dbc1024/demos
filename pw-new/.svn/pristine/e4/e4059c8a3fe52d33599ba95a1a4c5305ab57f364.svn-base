package com.ectrip.ec.custom.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;

public interface IJourneyDAO extends IGenericDao {
	/**
	 * ��ѯ��ʼ԰�Ż���ͷ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneryTop();
	/**
	 * ��ѯ�����г̵�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryEsbtripequiptabListAll();
	/**
	 * ������ʼ���ѯ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param tripId ��ʼ��
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryListTripequiepById(Long tripId);
	/**
	 * �����г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void addEsbjourney(List esbjouneyList);
	/**
	 * �޸��г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void updateEsbjourney(List esbjouneyList);
		
	/**
	 * ɾ���г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public void deleteEsbjourney(String orid,Long isby);
	/**
	 * ��ѯ�г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public List queryJourList(String orid,Long isby);
	/**
	 * ��ѯ�����г���ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneyList(String orid);
	/**
	 * �ж��Ƿ��ǳ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param isceniaId
	 * @return
	 * return:List
	 * Date:2012-2-25
	 */
	public List queryIsFristJourney(Long isceniaId);
	/**
	 * ��������֮���������ʱ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param issceniaid
	 * @param isscenibid
	 * @return
	 * return:Long
	 * Date:2012-2-27
	 */
	public Esbtripequiptab queryJourneryTimes(Long issceniaid,Long isscenibid);
	/**
	 * �õ�������Ч����
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:int
	 * Date:2012-3-2
	 */
	public int queryOrderDates(String orid);
	/**
	 * �ж϶������Ƿ�����Ʊ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:boolean
	 * Date:2012-3-6
	 */
	public boolean queryIsTickets(String orid);
}

