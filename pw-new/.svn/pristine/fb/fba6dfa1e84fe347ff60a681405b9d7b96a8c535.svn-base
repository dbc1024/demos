package com.ectrip.ticket.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ectrip.ticket.webservice.check.ICheckWebService;
import com.ectrip.ticket.webservice.sale.IOnlinePaymentWebService;
import com.ectrip.ticket.webservice.sale.ISaleWebService;

/**
 * 售票窗口webService接口发布
 * @author jiyong
 * @date 2018-05-07
 */
@Configuration
public class WebServiceCFXConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ISaleWebService saleWebService;
    
    @Autowired
    private ICheckWebService checkWebService;
    
    @Autowired
    private IOnlinePaymentWebService onlinePaymentWebService;

    /**
     * 发布售票服务接口
     * @return
     */
    @Bean
    public Endpoint saleWebServiceEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, saleWebService);
        endpoint.publish("/saleWebService");
        return endpoint;
    }
    /**
     * 发布检票服务接口
     * @return
     */
    @Bean
    public Endpoint checkWebServiceEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, checkWebService);
        endpoint.publish("/checkWebService");
        return endpoint;
    }
    
    /**
     * 发布在线支付服务接口
     * @return
     */
    @Bean
    public Endpoint onlinePayWebServiceEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, onlinePaymentWebService);
        endpoint.publish("/onlinePayWebService");
        return endpoint;
    }
}
