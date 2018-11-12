package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;

public class OrderUpdateHandler implements InvocationHandler {
	private IOrderBusiness target;

	private ITicketDAO ticketDao;

	public OrderUpdateHandler(IOrderBusiness target) {
		super();
		this.target = target;
		this.ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("OrderUpdateHandler invoke������");
		
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		ticketDao.saveOrUpdate(combin.getMorder());
		for (TOrder t : combin.getTorders()) {
			ticketDao.saveOrUpdate(t);
			ticketDao.saveOrUpdate(t.getYorder());
			for (TOrderlist tl : t.getTorderlists()) {
				ticketDao.saveOrUpdate(tl);
				ticketDao.saveOrUpdate(tl.getYorderlist());
				for (TZorderlist tz : tl.getZorderlist()) {
					ticketDao.saveOrUpdate(tz);
					ticketDao.saveOrUpdate(tz.getYzorderlist());
				}
			}
		}
		return result;
	}
}
