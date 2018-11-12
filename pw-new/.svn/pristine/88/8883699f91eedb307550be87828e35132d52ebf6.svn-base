package com.ectrip.ec.model.cyt;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 畅游通专用消费通知model
 * @author liujianwen
 */
@Entity
@Table
public class CYTPojo implements Serializable{

	/**
	 * CYTPojo.java
	 * liujianwen
	 * 20142014-5-8
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public String orid;//订单id
	public String printNum;//票号
	public String orderuserid;//下单用户
	public String  orderQuantity;//数量
	public String useQuantity;//使用数量
	public long iscenicid;//服务商id
	public String posid;//机具id
	public String oto_code;//企业编码
	public String sign;
	public String consumedate;
	public String version;
	public int state = 0;//状态，0已未通知，1已经通知
	
	@Transient
	public boolean isCYT = false;//非数据库字段
	@Transient
	public boolean isCallback = false;//非数据库字段
	@Transient
	public CYTDto cytdto;//非数据库字段
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public String getPrintNum() {
		return printNum;
	}
	public void setPrintNum(String printNum) {
		this.printNum = printNum;
	}
	public String getOrderuserid() {
		return orderuserid;
	}
	public void setOrderuserid(String orderuserid) {
		this.orderuserid = orderuserid;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getUseQuantity() {
		return useQuantity;
	}
	public void setUseQuantity(String useQuantity) {
		this.useQuantity = useQuantity;
	}
	public long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOto_code() {
		return oto_code;
	}
	public void setOto_code(String oto_code) {
		this.oto_code = oto_code;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getConsumedate() {
		return consumedate;
	}
	public void setConsumedate(String consumedate) {
		this.consumedate = consumedate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
