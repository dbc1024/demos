package com.ectrip.ec.ticket.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IVipticketDAO extends IGenericDao{
	
	/**
	 * 
	 * Describe:��ȡ���˴���Ϣ ���ݲ�ƷID,����ʱ��
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param date
	 * @param ziticketid
	 * @param usid
	 * @return
	 * @throws Exception
	 * return:List
	 * Date:Apr 25, 2012
	 */
	 public List getRafttripInfoList(String itickettypeid, String date, String ziticketid,String usid) throws Exception;
	 
	 /**
	  * 
	  * Describe:����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������
	  * @auth:lijingrui
	  * @param iticketid
	  * @param date
	  * @param controlltype
	  * @return
	  * return:List
	  * Date:Apr 25, 2012
	  */
	 public List getNumberControllData(String iticketid, String date, String controlltype);
	 
	 /**
	  * 
	  * Describe:���ݵ�ǰ�û�����ҵ�����ͻ�ȡ�ֿ��۵�Ʊ������,������Ʊ�����ƽ��з���
	  * @auth:lijingrui
	  * @param ibusinessid
	  * @return
	  * return:List
	  * Date:Apr 28, 2012
	  */
	 public List getTicketList(String ibusinessid);
	 
	 /**
	  * 
	  * Describe:��ȡ���϶��������̳�Ʊ��ϸ
	  * @auth:lijingrui
	  * @param orid
	  * @param iscenicid
	  * @return
	  * @throws IllegalAccessException
	  * @throws InvocationTargetException
	  * return:List
	  * Date:Apr 28, 2012
	  */
	 public List getTOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException;
	 
}

