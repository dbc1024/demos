package com.ectrip.ec.ticket.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Customvip;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;

public interface IVipTicketService {
	
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
	  * Describe:�޸Ķ�������
	  * @auth:lijingrui
	  * @param orderlistInfo
	  * @param neworderlist
	  * @param orids
	  * @param oldorid
	  * @param iscenicid
	  * @param stdt
	  * @param ibusiness
	  * @param usid
	  * @return
	  * @throws Exception
	  * return:Map
	  * Date:Apr 26, 2012
	  */
	 public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
			    String iscenicid, String stdt, String ibusiness, String usid) throws Exception ;
	 
	 /**
	  * 
	  * Describe:
	  * @auth:lijingrui
	  * @param orid
	  * @param orderInfo
	  * @param newInfo
	  * @param usid
	  * @param stdt
	  * @param ibusinessid
	  * @param iscenicid
	  * @return
	  * @throws IllegalAccessException
	  * @throws InvocationTargetException
	  * return:Map
	  * Date:Apr 26, 2012
	  */
	 public Map validateOrderInfo(String orid, List orderInfo, List newInfo, String usid, String stdt, String ibusinessid, String iscenicid)
	    throws IllegalAccessException, InvocationTargetException ;
	 
	 /**
	  * 
	  * Describe:��ʾVIP������Ϣ
	  * @auth:lijingrui
	  * @return
	  * return:Customvip
	  * Date:Apr 27, 2012
	  */
	 public Customvip getViewcustomvip(String usid) throws Exception;
	 
	 /**
	  * 
	  * Describe:��ʾ��Ʊ�л���Ʊ�е���Ʊ�۸�
	  * @auth:lijingrui
	  * @param itickettypeid
	  * @param ibusinessid
	  * @return
	  * @throws Exception
	  * return:Edmticketcomposepricetab
	  * Date:Apr 27, 2012
	  */
	 public Edmticketcomposepricetab getViewpricetab(Long itickettypeid,Long ibusinessid,String stdate)throws Exception;
	 
	 /**
	  * 
	  * Describe:��ȡƱ����Ϣ������װ�۸��б�
	  * @auth:lijingrui
	  * @param ibusinessid
	  * @return
	  * @throws IllegalAccessException
	  * @throws InvocationTargetException
	  * return:List
	  * Date:Apr 28, 2012
	  */
	 public List getTickInfoList(String ibusinessid) throws IllegalAccessException, InvocationTargetException;
	 
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
	  * Describe:�ж�VIP�û��Ƿ���
	  * @auth:lijingrui
	  * @param usid
	  * @return
	  * return:boolean
	  * Date:Apr 28, 2012
	  */
	 public boolean getTickdate(String usid);
	 
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
	 
	 /**
	  * 
	  * Describe:�ж�VIP�û���ĳ��ֻ��Ԥ��һ��
	  * @auth:lijingrui
	  * @param usid
	  * @param data
	  * @return
	  * return:boolean
	  * Date:Apr 29, 2012
	  */
	 public boolean QuickvipDate(String usid,String data);
	 
}

