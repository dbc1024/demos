//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.22 at 02:06:16 下午 CST
//

package com.ectrip.ticket.cyt.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://tour.ectrip.com/2014/QMRequestDataSchema}RequestHeader"/>
 *         &lt;element name="body" type="{http://tour.ectrip.com/2014/QMRequestDataSchema}RequestBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "header",
        "body"
})
@XmlRootElement(name = "request")
public class Request {

    @XmlElement(required = true)
    protected RequestHeader header;
    @XmlElement(required = true)
    protected RequestBody body;

    /**
     * Gets the value of the header property.
     *
     * @return
     *     possible object is
     *     {@link RequestHeader }
     *
     */
    public RequestHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     *
     * @param value
     *     allowed object is
     *     {@link RequestHeader }
     *
     */
    public void setHeader(RequestHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     *
     * @return
     *     possible object is
     *     {@link RequestBody }
     *
     */
    public RequestBody getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     *
     * @param value
     *     allowed object is
     *     {@link RequestBody }
     *
     */
    public void setBody(RequestBody value) {
        this.body = value;
    }

}
