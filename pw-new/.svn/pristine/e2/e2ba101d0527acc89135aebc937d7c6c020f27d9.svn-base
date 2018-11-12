package com.ectrip.ticket.sale.service.card.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class ACoreService implements ICoreService{
	public abstract InvocationHandler getInvocationHandler();
	public final Object work(String method, String data) throws Exception{
			if("work".equalsIgnoreCase(method))
				return CoreUtil.packageDataBase64("2001", "�ӿڳ���");
			Method m;
			m = this.getClass().getDeclaredMethod(method, String.class);
			return m.invoke(this, data);
	}
}

