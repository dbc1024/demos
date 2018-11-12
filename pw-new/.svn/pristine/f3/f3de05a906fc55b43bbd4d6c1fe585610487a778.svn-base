package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;

public class OrderRealNameHandler implements InvocationHandler {

	private IOrderBusiness target;
	private List<TRealname> realname;
	private IGenericDao genericDao;

	public OrderRealNameHandler(IOrderBusiness target, List<TRealname> realname) {
		super();
		this.target = target;
		this.realname = realname;
		this.genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		//System.out.println("OrderRealNameHandler invoke������");
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		String orid = combin.getMorder().getOrid();
		if (realname != null) {
			for (int i = 0; i < realname.size(); i++) {
				try {
					TRealname tRealname = new TRealname();
					tRealname = (TRealname) realname.get(i);
					Long seq = genericDao.getSequenceId("realname_sequence");
					tRealname.setOrid(orid);
					tRealname.setSeq(seq);
					genericDao.save(tRealname);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
