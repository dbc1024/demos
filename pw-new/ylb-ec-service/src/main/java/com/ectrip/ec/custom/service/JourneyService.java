package com.ectrip.ec.custom.service;

import java.util.List;

import com.ectrip.ec.custom.dao.idao.IJourneyDAO;
import com.ectrip.ec.custom.service.iservice.IJourneyService;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;

public class JourneyService implements IJourneyService {

	private IJourneyDAO journeyDao;
	
	public IJourneyDAO getJourneyDao() {
		return journeyDao;
	}
	public void setJourneyDao(IJourneyDAO journeyDao) {
		this.journeyDao = journeyDao;
	}
	/**
	 * ��ѯ��ʼ԰�Ż���ͷ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneryTop(){
		return journeyDao.queryJourneryTop();
	}
	/**
	 * ��ѯ�����г̵�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryEsbtripequiptabListAll(){
		return journeyDao.queryEsbtripequiptabListAll();
	}
	/**
	 * ������ʼ���ѯ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param tripId ��ʼ��
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryListTripequiepById(Long tripId){
		return journeyDao.queryListTripequiepById(tripId);
	}
	/**
	 * �����г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void addEsbjourney(List esbjouneyList){
		journeyDao.addEsbjourney(esbjouneyList);
	}
	/**
	 * �޸��г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void updateEsbjourney(List esbjouneyList){
		journeyDao.updateEsbjourney(esbjouneyList);
	}
		
	/**
	 * ɾ���г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public void deleteEsbjourney(String orid,Long isby){
		journeyDao.deleteEsbjourney(orid,isby);
	}
	/**
	 * ��ѯ�г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public List queryJourList(String orid,Long isby){
		return journeyDao.queryJourList(orid, isby);
	}
	/**
	 * ��ѯ�����г���ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneyList(String orid){
		return journeyDao.queryJourneyList(orid);
	}
	/**
	 * �ж��Ƿ��ǳ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param isceniaId
	 * @return
	 * return:List
	 * Date:2012-2-25
	 */
	public List queryIsFristJourney(Long isceniaId){
		return journeyDao.queryIsFristJourney(isceniaId);
	}
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
	public Esbtripequiptab queryJourneryTimes(Long issceniaid,Long isscenibid){
		return journeyDao.queryJourneryTimes(issceniaid, isscenibid);
	}
	/**
	 * �õ�������Ч����
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:int
	 * Date:2012-3-2
	 */
	public int queryOrderDates(String orid){
		return journeyDao.queryOrderDates(orid);
	}
	/**
	 * �ж϶������Ƿ�����Ʊ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:boolean
	 * Date:2012-3-6
	 */
	public boolean queryIsTickets(String orid){
		return journeyDao.queryIsTickets(orid);
	}
}

