package com.ectrip.ec.order.common.transaction;

import java.lang.reflect.Proxy;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.LineOrderCombin;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.order.common.proxy.LineOrderPriceHandler;
import com.ectrip.ec.order.common.proxy.OrderSchemaHandler;
import com.ectrip.ec.order.common.proxy.OrderUpdateHandler;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;

public class LineorderSaveTrans implements IOrderSaveTrans{
	
	private IOrderBusiness orderBusiness;
	
	public IOrderBusiness getOrderBusiness() {
		return orderBusiness;
	}

	public void setOrderBusiness(IOrderBusiness orderBusiness) {
		this.orderBusiness = orderBusiness;
	}

	public boolean SaveOrder(OrderCombinDTO dto) {
		try {
			OrderCombin combin = new LineOrderCombin(dto);// ���ݴ��ݵ��û�������Ϣ��װ����
			// ��װ�Ż���Ϣ���ɴ���
			OrderSchemaHandler schemaHandler = new OrderSchemaHandler(orderBusiness);
			IOrderBusiness schemaproxy = (IOrderBusiness) Proxy.newProxyInstance(orderBusiness.getClass().getClassLoader(), orderBusiness.getClass().getInterfaces(), schemaHandler);
			// ��װ�۸���Ϣ���ɴ���
			LineOrderPriceHandler pricehandler = new LineOrderPriceHandler(schemaproxy);
			IOrderBusiness priceproxy = (IOrderBusiness) Proxy.newProxyInstance(schemaproxy.getClass().getClassLoader(), schemaproxy.getClass().getInterfaces(), pricehandler);
			// ��װ��������
			OrderUpdateHandler savehandler = new OrderUpdateHandler(priceproxy);
			IOrderBusiness ordersaveProxy = (IOrderBusiness) Proxy.newProxyInstance(priceproxy.getClass().getClassLoader(), priceproxy.getClass().getInterfaces(), savehandler);
			//ʵ����
			//OrderRealNameHandler realname = new OrderRealNameHandler(ordersaveProxy, dto.getRealnames());
			//IOrderBusiness bussiness = (IOrderBusiness) Proxy.newProxyInstance(ordersaveProxy.getClass().getClassLoader(), ordersaveProxy.getClass().getInterfaces(), realname);
			//��������
			boolean b = ordersaveProxy.saveOrder(combin);
			if(!b){
				throw new RuntimeException("��������ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��������ʧ��" + e.getMessage());
		}
		return true;
	}

}
