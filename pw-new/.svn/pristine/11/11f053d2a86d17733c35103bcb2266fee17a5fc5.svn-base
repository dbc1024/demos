package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;

public class OrderSchemaHandler implements InvocationHandler {

	private IOrderBusiness target;
	private ITicketDAO ticketDao;

	public OrderSchemaHandler(IOrderBusiness target) {
		super();
		this.target = target;
		this.ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("OrderSchemaHandler invoke������");
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		Custom custom = (Custom) ticketDao.get(Custom.class, combin.getMorder().getUsid());
		List<TOrder> ts = combin.getTorders();
		for (TOrder t : ts) {
			for (TOrderlist tl : t.getTorderlists()) {
				//����ɢ�Ͳ��Ż�
//				Edpofferschemetab scheme = ticketDao.getScheme(tl.getId().getIscenicid(), custom.getIbusinessid(), tl.getIcrowdkindid(), tl.getItickettypeid(), tl.getDtstartdate());
//				if (scheme != null) {
//					tl.setIoffersschemeid(scheme.getIoffersschemeid());
//					tl.setYhnumb(tl.getNumb() / scheme.getImultiples() * scheme.getIoffernum());
//					//yorder
//					tl.getYorderlist().setYhnumb(tl.getYhnumb());
//				} else {
					tl.setYhnumb(new Long(0));
					//yorder
					tl.getYorderlist().setYhnumb(0l);
//				}
				for (TZorderlist tz : tl.getZorderlist()) {
					tz.setZyhnumb(tl.getYhnumb());
					//yorder
					tz.getYzorderlist().setZyhnumb(tl.getYhnumb());
				}
			}
			
		}
		return result;
	}

}
