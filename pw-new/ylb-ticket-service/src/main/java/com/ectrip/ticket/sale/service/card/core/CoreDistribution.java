package com.ectrip.ticket.sale.service.card.core;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.WebContant;

/**
 * 接口分配中心
 * 
 * @author liujianwen
 */
public class CoreDistribution {
	private static final Map<Class<?>, Object> CLASSMAP = new HashMap<Class<?>, Object>();

	public static ResultBean distributionResultBean(String data) {
		ResultBean rs = new ResultBean();
		rs.setColumnNames(new String[] { "returnstats", "message" });
		rs.addRow(new String[] { "true", distribution(data) });
		return rs;
	}

	public static String distribution(String data) {
		System.out.println("receiveData:" + data);
		JsonData out = new JsonData();//返回的对象
		out.setSignType("MD5");
		String rdata;//out的data
		String key = "ECTRIP" + WebContant.GetKeyValue("SKISALEKEY") + "DINGYOU";
		try {
			JsonData in = JSON.parseObject(data, JsonData.class);
			Request sonData;
			System.out.println("in.getData():+" + in.getData().toString());
			if (in.getSign() != null && in.getSign().equals(CoreUtil.signMD5(in.getData() + key))) {
				String sdata = CoreUtil.decodeBase64(in.getData());
				System.out.println("sonData:" + sdata);
				if (in.getData() != null && (sonData = JSON.parseObject(sdata, Request.class)) != null) {
					sonData.setTargetClass("com.ectrip.ticket.sale.service.OnlinePayment.webservice.OnlinePaymentWebService");
					//此行有问题；
					Object obj = createObject(sonData.getTargetClass());

					if (obj instanceof ICoreService) {
						obj = ((ICoreService) obj).work(sonData.getMethod(), sdata);
					} else {
						//要运行方法
						obj = obj.getClass().getDeclaredMethod(sonData.getMethod(), String.class).invoke(obj, sdata);
					}
					System.out.println("responseData:" + obj);
					if (obj instanceof String) {
						rdata = (obj == null ? null : CoreUtil.encodeBase64((String) obj));
					} else
						rdata = CoreUtil.encodeBase64(JSON.toJSONString(obj));
				} else {
					rdata = CoreUtil.packageDataBase64("2003", "数据格式不正确");
				}
			} else {
				rdata = CoreUtil.packageDataBase64("2003", "校验失败");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			rdata = CoreUtil.packageDataBase64("2003", "数据格式不正确");
		}
		out.setData(rdata);
		out.setSign(CoreUtil.signMD5(out.getData() + key));
		return JSON.toJSONString(out);
	}

	private static Object createObject(String className) throws Exception {
		Class<?> c = Class.forName(className);
		if (!CLASSMAP.containsKey(c)) {
			Object obj = c.newInstance();
			if (obj instanceof ACoreService) {
				ACoreService ass = (ACoreService) obj;
				if (ass.getInvocationHandler() != null) {
					obj = (ICoreService) Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(),
							ass.getInvocationHandler());
				}
			}
			CLASSMAP.put(c, obj);
		}
		return CLASSMAP.get(c);
	}

	public static void main(String[] args) {
		String str = "\"ddd\"}";
		System.out.println(str.matches("[{].*[}]"));
	}
}
