package com.ectrip.ec.order.common.transaction;

import java.lang.reflect.Proxy;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.order.common.proxy.OrderPriceHandler;
import com.ectrip.ec.order.common.proxy.OrderSchemaHandler;
import com.ectrip.ec.order.common.proxy.OrderTripHandler;
import com.ectrip.ec.order.common.proxy.OrderUpdateHandler;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;

public class FilmOrderTransImpl implements IOrderSaveTrans {
	private IOrderBusiness orderBusiness;

	public boolean SaveOrder(OrderCombinDTO dto) {
		try {
			OrderCombin combin = new OrderCombin(dto);// ���ݴ��ݵ��û�������Ϣ��װ����
			OrderTripHandler triphandler = new OrderTripHandler(dto.getTriplist(), orderBusiness);// ��װ�����Ϣ
			IOrderBusiness busproxy = (IOrderBusiness) Proxy.newProxyInstance(orderBusiness.getClass().getClassLoader(), orderBusiness.getClass().getInterfaces(), triphandler);// ���ɴ���
			// ��װ�Ż���Ϣ���ɴ���
			OrderSchemaHandler schemaHandler = new OrderSchemaHandler(busproxy);
			IOrderBusiness schemaproxy = (IOrderBusiness) Proxy.newProxyInstance(busproxy.getClass().getClassLoader(), busproxy.getClass().getInterfaces(), schemaHandler);
			// ��װ�۸���Ϣ���ɴ���
			OrderPriceHandler pricehandler = new OrderPriceHandler(schemaproxy);
			IOrderBusiness priceproxy = (IOrderBusiness) Proxy.newProxyInstance(schemaproxy.getClass().getClassLoader(), schemaproxy.getClass().getInterfaces(), pricehandler);
			// ��װ��������
			OrderUpdateHandler savehandler = new OrderUpdateHandler(priceproxy);
			IOrderBusiness ordersaveProxy = (IOrderBusiness) Proxy.newProxyInstance(priceproxy.getClass().getClassLoader(), priceproxy.getClass().getInterfaces(), savehandler);
			// TODO �Ƿ񱣴���λ��Ϣ
			ordersaveProxy.saveOrder(combin);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��������ʧ��" + e.getMessage());
		}
		return true;
	}

	public IOrderBusiness getOrderBusiness() {
		return orderBusiness;
	}

	public void setOrderBusiness(IOrderBusiness orderBusiness) {
		this.orderBusiness = orderBusiness;
	}

}
