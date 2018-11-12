//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.07.01 时间 06:28:04 PM CST 
//


package com.ectrip.ticket.cyt.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * 【OTO通提供】【订单】验证PMS数据（checkPmsData）
 * 
 * <p>CheckPmsDataRequestBody complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CheckPmsDataRequestBody">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tour.ectrip.com/2014/QMRequestDataSchema}RequestBody">
 *       &lt;sequence>
 *         &lt;element name="pmsDatas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pmsData" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="money" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CheckPmsDataRequestBody", propOrder = {
    "pmsDatas"
})
public class CheckPmsDataRequestBody
    extends RequestBody
{

    @XmlElement(required = true)
    protected PmsDatas pmsDatas;

    //construct////////////////////////////////////////////////////////////////

    public CheckPmsDataRequestBody() {
        this.pmsDatas = new PmsDatas();
    }

    /**
     * 获取pmsDatas属性的值。
     * 
     * @return
     *     possible object is
     *     {@link com.ectrip.cyt.model.request.CheckPmsDataRequestBody.PmsDatas }
     *     
     */
    public PmsDatas getPmsDatas() {
        return pmsDatas;
    }

    /**
     * 设置pmsDatas属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link com.ectrip.cyt.model.request.CheckPmsDataRequestBody.PmsDatas }
     *     
     */
    public void setPmsDatas(PmsDatas value) {
        this.pmsDatas = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="pmsData" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="money" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "pmsData"
    })
    public static class PmsDatas {

        protected List<PmsData> pmsData;

        //construct////////////////////////////////////////////////////////////

        public PmsDatas() {
            this.pmsData = new ArrayList<PmsData>();
        }

        /**
         * Gets the value of the pmsData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pmsData property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPmsData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.ectrip.cyt.model.request.CheckPmsDataRequestBody.PmsDatas.PmsData }
         * 
         * 
         */
        public List<PmsData> getPmsData() {
            if (pmsData == null) {
                pmsData = new ArrayList<PmsData>();
            }
            return this.pmsData;
        }

        public void setPmsData(List<PmsData> pmsData) {
            this.pmsData = pmsData;
        }

        /**
         * <p>anonymous complex type的 Java 类。
         * 
         * <p>以下模式片段指定包含在此类中的预期内容。
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="money" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "type",
            "quantity",
            "money"
        })
        public static class PmsData {

            @XmlElement(required = true)
            protected String type;
            @XmlElement(required = true)
            protected String quantity;
            @XmlElement(required = true)
            protected String money;

            //construct////////////////////////////////////////////////////////

            public PmsData() {
                this.type = "";
                this.quantity = "";
                this.money = "";
            }

            /**
             * 获取type属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * 设置type属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * 获取quantity属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQuantity() {
                return quantity;
            }

            /**
             * 设置quantity属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQuantity(String value) {
                this.quantity = value;
            }

            /**
             * 获取money属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMoney() {
                return money;
            }

            /**
             * 设置money属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMoney(String value) {
                this.money = value;
            }

        }

    }

}
