package com.ectrip.ec.order.common.transaction;

import java.lang.reflect.Proxy;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.HotelOrderCombin;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.order.common.proxy.HotelOrderPriceHandler;
import com.ectrip.ec.order.common.proxy.OrderSchemaHandler;
import com.ectrip.ec.order.common.proxy.OrderUpdateHandler;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;

public class HotelOrderTransImpl implements IOrderSaveTrans {
	private IOrderBusiness orderBusiness;
	public boolean SaveOrder(OrderCombinDTO dto) {
		try {
			OrderCombin combin = new HotelOrderCombin(dto);// ���ݴ��ݵ��û�������Ϣ��װ����
			// ��װ�Ż���Ϣ���ɴ���
			OrderSchemaHandler schemaHandler = new OrderSchemaHandler(orderBusiness);
			IOrderBusiness schemaproxy = (IOrderBusiness) Proxy.newProxyInstance(orderBusiness.getClass().getClassLoader(), orderBusiness.getClass().getInterfaces(), schemaHandler);
			// ��װ�۸���Ϣ���ɴ���
			HotelOrderPriceHandler pricehandler = new HotelOrderPriceHandler(schemaproxy);
			IOrderBusiness priceproxy = (IOrderBusiness) Proxy.newProxyInstance(schemaproxy.getClass().getClassLoader(), schemaproxy.getClass().getInterfaces(), pricehandler);
			// ��װ��������
			OrderUpdateHandler savehandler = new OrderUpdateHandler(priceproxy);
			IOrderBusiness ordersaveProxy = (IOrderBusiness) Proxy.newProxyInstance(priceproxy.getClass().getClassLoader(), priceproxy.getClass().getInterfaces(), savehandler);
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
