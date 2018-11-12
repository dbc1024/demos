package com.ectrip.ticket.cyt.model.request;

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.16 at 03:01:04 PM CST 
//

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 【畅游通提供】【订单】OTO退订订单（refundOTOOrder）
 *
 * <p>Java class for RefundOTOOrderRequestBody complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RefundOTOOrderRequestBody">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tour.ectrip.com/2014/QMRequestDataSchema}RequestBody">
 *       &lt;sequence>
 *         &lt;element name="orderInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="partnerorderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="refundSeq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="orderPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="orderQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="refundQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="refundPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="orderRefundPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="orderRefundCharge" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="refundExplain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="refundInfo">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="note1" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note2" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note3" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note4" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note5" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note6" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note7" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                             &lt;element name="note8" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefundOTOOrderRequestBody", propOrder = {
        "orderInfo"
})
public class RefundOTOOrderRequestBody
        extends RequestBody
{

    @XmlElement(required = true)
    protected OrderInfo orderInfo;

    public RefundOTOOrderRequestBody() {
        super();
        this.orderInfo = new OrderInfo();
    }

    /**
     * Gets the value of the orderInfo property.
     *
     * @return
     *     possible object is
     *     {@link com.ectrip.tour.service.model.schema.ectripoto.v1.request.RefundOTOOrderRequestBody.OrderInfo }
     *
     */
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * Sets the value of the orderInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link com.ectrip.tour.service.model.schema.ectripoto.v1.request.RefundOTOOrderRequestBody.OrderInfo }
     *
     */
    public void setOrderInfo(OrderInfo value) {
        this.orderInfo = value;
    }


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
     *         &lt;element name="partnerorderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="refundSeq" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="orderPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="orderQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="refundQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="refundPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="orderRefundPrice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="orderRefundCharge" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="refundExplain" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="refundInfo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="note1" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note2" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note3" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note4" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note5" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note6" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note7" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                   &lt;element name="note8" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
            "partnerorderId",
            "refundSeq",
            "orderPrice",
            "orderQuantity",
            "refundQuantity",
            "refundPrice",
            "orderRefundPrice",
            "orderRefundCharge",
            "refundExplain",
            "refundInfo"
    })
    public static class OrderInfo {

        @XmlElement(required = true)
        protected String partnerorderId;
        @XmlElement(required = true)
        protected String refundSeq;
        @XmlElement(required = true)
        protected String orderPrice;
        @XmlElement(required = true)
        protected String orderQuantity;
        @XmlElement(required = true)
        protected String refundQuantity;
        @XmlElement(required = true)
        protected String refundPrice;
        @XmlElement(required = true)
        protected String orderRefundPrice;
        @XmlElement(required = true)
        protected String orderRefundCharge;
        @XmlElement(required = true)
        protected String refundExplain;
        @XmlElement(required = true)
        protected RefundInfo refundInfo;

        public OrderInfo() {
            super();
            this.partnerorderId = "";
            this.refundSeq = "";
            this.orderPrice = "";
            this.orderQuantity = "";
            this.refundQuantity = "";
            this.refundPrice = "";
            this.orderRefundPrice = "";
            this.orderRefundCharge = "";
            this.refundExplain = "";
            this.refundInfo = new RefundInfo();
        }

        /**
         * Gets the value of the partnerorderId property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPartnerorderId() {
            return partnerorderId;
        }

        /**
         * Sets the value of the partnerorderId property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPartnerorderId(String value) {
            this.partnerorderId = value;
        }

        /**
         * Gets the value of the refundSeq property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRefundSeq() {
            return refundSeq;
        }

        /**
         * Sets the value of the refundSeq property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRefundSeq(String value) {
            this.refundSeq = value;
        }

        /**
         * Gets the value of the orderPrice property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOrderPrice() {
            return orderPrice;
        }

        /**
         * Sets the value of the orderPrice property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOrderPrice(String value) {
            this.orderPrice = value;
        }

        /**
         * Gets the value of the orderQuantity property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOrderQuantity() {
            return orderQuantity;
        }

        /**
         * Sets the value of the orderQuantity property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOrderQuantity(String value) {
            this.orderQuantity = value;
        }

        /**
         * Gets the value of the refundQuantity property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRefundQuantity() {
            return refundQuantity;
        }

        /**
         * Sets the value of the refundQuantity property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRefundQuantity(String value) {
            this.refundQuantity = value;
        }

        /**
         * Gets the value of the refundPrice property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRefundPrice() {
            return refundPrice;
        }

        /**
         * Sets the value of the refundPrice property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRefundPrice(String value) {
            this.refundPrice = value;
        }

        /**
         * Gets the value of the orderRefundPrice property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOrderRefundPrice() {
            return orderRefundPrice;
        }

        /**
         * Sets the value of the orderRefundPrice property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOrderRefundPrice(String value) {
            this.orderRefundPrice = value;
        }

        /**
         * Gets the value of the orderRefundCharge property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOrderRefundCharge() {
            return orderRefundCharge;
        }

        /**
         * Sets the value of the orderRefundCharge property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOrderRefundCharge(String value) {
            this.orderRefundCharge = value;
        }

        /**
         * Gets the value of the refundExplain property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRefundExplain() {
            return refundExplain;
        }

        /**
         * Sets the value of the refundExplain property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRefundExplain(String value) {
            this.refundExplain = value;
        }

        /**
         * Gets the value of the refundInfo property.
         *
         * @return
         *     possible object is
         *     {@link com.ectrip.tour.service.model.schema.ectripoto.v1.request.RefundOTOOrderRequestBody.OrderInfo.RefundInfo }
         *
         */
        public RefundInfo getRefundInfo() {
            return refundInfo;
        }

        /**
         * Sets the value of the refundInfo property.
         *
         * @param value
         *     allowed object is
         *     {@link com.ectrip.tour.service.model.schema.ectripoto.v1.request.RefundOTOOrderRequestBody.OrderInfo.RefundInfo }
         *
         */
        public void setRefundInfo(RefundInfo value) {
            this.refundInfo = value;
        }


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
         *         &lt;element name="note1" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note2" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note3" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note4" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note5" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note6" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note7" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
         *         &lt;element name="note8" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
                "note1",
                "note2",
                "note3",
                "note4",
                "note5",
                "note6",
                "note7",
                "note8"
        })
        public static class RefundInfo {

            @XmlElement(required = true)
            protected String note1;
            @XmlElement(required = true)
            protected String note2;
            @XmlElement(required = true)
            protected String note3;
            @XmlElement(required = true)
            protected String note4;
            @XmlElement(required = true)
            protected String note5;
            @XmlElement(required = true)
            protected String note6;
            @XmlElement(required = true)
            protected String note7;
            @XmlElement(required = true)
            protected String note8;

            public RefundInfo() {
                super();
                this.note1 = "";
                this.note2 = "";
                this.note3 = "";
                this.note4 = "";
                this.note5 = "";
                this.note6 = "";
                this.note7 = "";
                this.note8 = "";
            }

            /**
             * Gets the value of the note1 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote1() {
                return note1;
            }

            /**
             * Sets the value of the note1 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote1(String value) {
                this.note1 = value;
            }

            /**
             * Gets the value of the note2 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote2() {
                return note2;
            }

            /**
             * Sets the value of the note2 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote2(String value) {
                this.note2 = value;
            }

            /**
             * Gets the value of the note3 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote3() {
                return note3;
            }

            /**
             * Sets the value of the note3 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote3(String value) {
                this.note3 = value;
            }

            /**
             * Gets the value of the note4 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote4() {
                return note4;
            }

            /**
             * Sets the value of the note4 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote4(String value) {
                this.note4 = value;
            }

            /**
             * Gets the value of the note5 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote5() {
                return note5;
            }

            /**
             * Sets the value of the note5 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote5(String value) {
                this.note5 = value;
            }

            /**
             * Gets the value of the note6 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote6() {
                return note6;
            }

            /**
             * Sets the value of the note6 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote6(String value) {
                this.note6 = value.replace("Ectrip", "yilvbao");
            }

            /**
             * Gets the value of the note7 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote7() {
                return note7;
            }

            /**
             * Sets the value of the note7 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote7(String value) {
                this.note7 = value;
            }

            /**
             * Gets the value of the note8 property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNote8() {
                return note8;
            }

            /**
             * Sets the value of the note8 property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNote8(String value) {
                this.note8 = value;
            }

        }

    }

}
