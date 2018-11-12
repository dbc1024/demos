package com.ectrip.ec.order.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
@SuppressWarnings("rawtypes")
public interface IOrderModifyDao extends IGenericDao{
	/**
	 * ��ѯ���������Ķ���
	 * queryOrderModify(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param tOrder ��ѯ����
	 * @return 
	 * List �������
	 * @exception 
	 * @date:2014-6-5 ����3:10:37
	 * @since  1.0.0
	 */
	public List queryOrderModify(TOrder tOrder);
	/**
	 * ��ѯ������ϸ
	 * queryOrderList(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param orid
	 * @return 
	 * List<TOrderlist>
	 * @exception 
	 * @date:2014-6-9 ����1:52:36
	 * @since  1.0.0
	 */
	public List<TOrderlist> queryOrderList(String orid,Long iscenicid);
}
