package com.ectrip.ticket.cyt.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.venuemarketing.Programprdmanager;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.ticket.model.venuemarketing.Tripprdsaletab;

public interface ICytOrderDataService extends IGenericService {


	public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder,
                             List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist, List<TRealname> rlist)
			throws IllegalAccessException, InvocationTargetException;
	public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder,
                             List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist)
			throws IllegalAccessException, InvocationTargetException;
	public List getMOrderList(String orid);
	public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) throws IllegalAccessException, InvocationTargetException;
	public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid, double tpsxmonth, Long icompanyinfoid) throws Exception;
	public List getYOrderListChildList(String orid, String iscenicid);
	public List<TOrderlist> getTOrderList(String orid, Long iscenicid);
	public Map validateOrderInfo(String orid, List orderInfo, List newInfo, String usid, String stdt, String ibusinessid, String iscenicid, Long icompanyinfoid) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 
	 * Describe:����ʵ����
	 * @author:zhangwubin
	 * @param list
	 * @param orid
	 * @return
	 * return:boolean
	 * Date:2014-3-29
	 */
	public boolean saveRealname(List<TRealname> list, String orid);
	/**
	 * 
	 * @Title: getTOrderList
	 * @Description: ���϶��������̳�Ʊ��Ϣ
	 * @param @param orid
	 * @param @return �趨�ļ�
	 * @return List ��������
	 * @throws
	 */
	public List getTOrderList(String orid, String iscenicid);
	
	/**
	 * 
	 * Describe:���ݶ���ID���TOrder
	 * @author:zhangwubin
	 * @param orid
	 * @return
	 * return:TOrder
	 * Date:2014-4-8
	 */
	public TOrder getTOrderByOrid(String orid);
	/**
	 * Describe:�޸��������ڣ�����ͨ�����޸�enddate
	 * @author liujianwen
	 * @param editlevel �༭���� 2:�ϸ���֤ 1:����֤ҵ��(����ͨ������ɶ�ɢ��ҵ���Ʒ) 0:����֤ҵ���Ҳ���֤��Ч��(�����Ա�����)
	 * @param orid
	 * @param stdt
	 * @param iscenicid
	 * return:void
	 * Date:2014-5-23
	 */
	public void updateProductDate(int editlevel, String orid, String stdt, String endDate, String iscenicid, String ibusinessid) ;

	/**
	 * 
	 * Describe:�����Ű���Ϣ
	 * @author:chenxinhao
	 * @param iprogramid	��ĿID
	 * @param ivenueid	����ID
	 * @param date	����
	 * @return
	 * return:Tripprdcontroltab
	 * Date:2015-7-21
	 */
	public Tripprdcontroltab getTripprdcontroltab(Long iprogramid, Long ivenueid, String date);
	/**
	 * 
	 * Describe:�����Ű���ϸ��Ϣ
	 * @author:chenxinhao
	 * @param itripprdcontrolid	�Ű�ID
	 * @param tripid	����ID
	 * @return
	 * return:Tripprdcontroldetailtab
	 * Date:2015-7-21
	 */
	public Tripprdcontroldetailtab getTripprdcontroldetailtab(Long itripprdcontrolid, Long tripid);
	/**
	 * 
	 * Describe:���Ҳ�Ʒ��Ŀ����Ϣ
	 * @author:chenxinhao
	 * @param iprogramid	��ĿID
	 * @param itickettypeid	��ƷID
	 * @return
	 * return:Programprdmanager
	 * Date:2015-7-21
	 */
	public Programprdmanager getProgramprdmanager(Long iprogramid, Long itickettypeid);
	/**
	 * 
	 * Describe:�鿴�Ű��Ʒ�����������
	 * @author:chenxinhao
	 * @param itripprdcontrolid	�Ű�ID
	 * @param iproductid	��ƷID
	 * @param ivenueareaid	����ID
	 * @return
	 * return:Tripprdsaletab
	 * Date:2015-7-21
	 */
	public Tripprdsaletab getTripprdsaletab(Long itripprdcontrolid, Long iproductid, Long ivenueareaid);
	public Map saveeditseat(Custom com, MOrder mor, TOrder torder,
                            List torderlistlist, String neworid, Double tpsxmonth) throws Exception;

    public Map<String,Object> findCreateOrderData(String date);

    public Map<String,Object> findConsumeOrderData(String date);

    public Map<String,Object> findRefundOrderData(String date);
}

