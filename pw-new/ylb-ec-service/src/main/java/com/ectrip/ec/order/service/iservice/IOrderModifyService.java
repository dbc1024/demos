package com.ectrip.ec.order.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;

@SuppressWarnings("rawtypes")
public interface IOrderModifyService extends IGenericService {
	/**
	 * ��ѯ���������Ķ��� queryOrderModify(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param tOrder
	 *            ��ѯ����
	 * @return List �������
	 * @exception
	 * @date:2014-6-5 ����3:10:37
	 * @since 1.0.0
	 */
	public List queryOrderModify(TOrder tOrder);

	/**
	 * ��ѯ������ϸ queryOrderList(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param orid
	 * @return List<TOrderlist>
	 * @exception
	 * @date:2014-6-9 ����1:52:36
	 * @since 1.0.0
	 */
	public List<TOrderlist> queryOrderList(String orid, Long iscenicid);

	/**
	 * ���¶���״̬ updateOrderStatus(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param ddzt
	 * @param orid
	 * @param iscenicid
	 * @return String
	 * @exception
	 * @date:2014-6-10 ����3:46:42
	 * @since 1.0.0
	 */
	public String updateOrderStatus(String ddzt, String orid, Long iscenicid,
			Long iemployeeid, String empid);

	/**
	 * ���¶�����Ϣ updateOrderInfo(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param objects
	 *            (���ǿɱ����) ��һ������ Morder �ڶ������� Torder ������������ Torderlist
	 * @return String
	 * @exception
	 * @date:2014-6-10 ����3:45:21
	 * @since 1.0.0
	 */
	public String updateOrderInfo(Long iemployeeid, Object... objects) throws Exception;
}
