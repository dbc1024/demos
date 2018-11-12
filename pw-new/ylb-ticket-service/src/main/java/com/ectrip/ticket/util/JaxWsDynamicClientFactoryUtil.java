package com.ectrip.ticket.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class JaxWsDynamicClientFactoryUtil {
	
	/**
	 * 
	 * @param url host:port
	 * @param serviceName webService 标识
	 * @return Cfx 客户端实例
	 */
	public static Client getCfxClientInstance(String url,String serviceName) {
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://"+url+"/services/"+serviceName+"?wsdl");
        return client;
	}
}
