//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.28 at 08:17:31 ���� CST 
//


package com.ectrip.ticket.cyt.model.response;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ectrip.tour._2014.qmresponseschema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ectrip.tour._2014.qmresponseschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ResponseBody }
     * 
     */
    public ResponseBody createResponseBody() {
        return new ResponseBody();
    }

    /**
     * Create an instance of {@link VerifyConsumeResponseBody }
     * 
     */
    public VerifyConsumeResponseBody createVerifyConsumeResponseBody() {
        return new VerifyConsumeResponseBody();
    }

    /**
     * Create an instance of {@link CheckPmsDataResponseBody }
     *
     */
    public CheckPmsDataResponseBody createCheckPmsDataResponseBody() {
        return new CheckPmsDataResponseBody();
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link QueryOrderResponseBody.OrderInfo }
     * 
     */
    public QueryOrderResponseBody.OrderInfo createQueryOrderResponseBodyOrderInfo() {
        return new QueryOrderResponseBody.OrderInfo();
    }

    /**
     * Create an instance of {@link PayOrderResponseBody }
     * 
     */
    public PayOrderResponseBody createPayOrderResponseBody() {
        return new PayOrderResponseBody();
    }

    /**
     * Create an instance of {@link QueryOrderResponseBody }
     * 
     */
    public QueryOrderResponseBody createQueryOrderResponseBody() {
        return new QueryOrderResponseBody();
    }

    /**
     * Create an instance of {@link CreateOTOOrderResponseBody }
     * 
     */
    public CreateOTOOrderResponseBody createCreateOrderResponseBody() {
        return new CreateOTOOrderResponseBody();
    }

    /**
     * Create an instance of {@link PayOrderResponseBody.OrderInfo }
     * 
     */
    public PayOrderResponseBody.OrderInfo createPayOrderResponseBodyOrderInfo() {
        return new PayOrderResponseBody.OrderInfo();
    }

    /**
     * Create an instance of {@link CancelOrderResponseBody }
     * 
     */
    public CancelOrderResponseBody createCancelOrderResponseBody() {
        return new CancelOrderResponseBody();
    }

    /**
     * Create an instance of {@link NoticeOrderConsumedResponseBody }
     * 
     */
    public NoticeOrderConsumedResponseBody createNoticeOrderConsumedResponseBody() {
        return new NoticeOrderConsumedResponseBody();
    }

    /**
     * Create an instance of {@link CreateOTOOrderResponseBody.OrderInfo }
     * 
     */
    public CreateOTOOrderResponseBody.OrderInfo createCreateOrderResponseBodyOrderInfo() {
        return new CreateOTOOrderResponseBody.OrderInfo();
    }

}
