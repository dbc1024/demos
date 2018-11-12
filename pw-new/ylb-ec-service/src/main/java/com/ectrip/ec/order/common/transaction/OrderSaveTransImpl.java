package com.ectrip.ec.order.common.transaction;

import java.lang.reflect.Proxy;

import org.springframework.stereotype.Service;

import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.order.common.proxy.OrderPriceHandler;
import com.ectrip.ec.order.common.proxy.OrderRealNameHandler;
import com.ectrip.ec.order.common.proxy.OrderSchemaHandler;
import com.ectrip.ec.order.common.proxy.OrderTripHandler;
import com.ectrip.ec.order.common.proxy.OrderUpdateHandler;
import com.ectrip.ec.order.common.transaction.inter.IOrderSaveTrans;
@Service
public class OrderSaveTransImpl implements IOrderSaveTrans {
	private IOrderBusiness orderBusiness;
	public IOrderBusiness getOrderBusiness() {
		return orderBusiness;
	}

	public void setOrderBusiness(IOrderBusiness orderBusiness) {
		this.orderBusiness = orderBusiness;
	}
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
            
			OrderRealNameHandler realname = new OrderRealNameHandler(ordersaveProxy, dto.getRealnames());
			IOrderBusiness bussiness = (IOrderBusiness) Proxy.newProxyInstance(ordersaveProxy.getClass().getClassLoader(), ordersaveProxy.getClass().getInterfaces(), realname);
			bussiness.saveOrder(combin);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��������ʧ��" + e.getMessage());
		}
		return true;
	}



}
