package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ticketchecklist entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Ticketchecklist implements java.io.Serializable {

	// Fields
	@Id
	private Long checkid;
	private Long szsoldticketid;
	private Long isalesvoucherdetailsid;
	private Long isalesvoucherid;
	private Long iticketstationid;
	private Long igardengateid;
	private Long iscenicid;
	private String dtmakedate;
	private Long iaccessequipid;
	private String zfseq;
	private String chuanhao;
	private String tongxingzhi;
	private String note1;
	private String note2;
	private Long int1;
	private Long int2;
	private String empid;
	private String accounttime;
	
	public String getChuanhao() {
		return chuanhao;
	}


	
	public void setChuanhao(String chuanhao) {
		this.chuanhao = chuanhao;
	}

	

	// Constructors

	

	public String getTongxingzhi() {
	    return tongxingzhi;
	}	
	public void setTongxingzhi(String tongxingzhi) {
	    this.tongxingzhi = tongxingzhi;
	}

	public String getNote1() {
	    return note1;
	}

	public void setNote1(String note1) {
	    this.note1 = note1;
	}

	public String getNote2() {
	    return note2;
	}

	public void setNote2(String note2) {
	    this.note2 = note2;
	}

	public Long getInt1() {
	    return int1;
	}

	public void setInt1(Long int1) {
	    this.int1 = int1;
	}

	public Long getInt2() {
	    return int2;
	}

	public void setInt2(Long int2) {
	    this.int2 = int2;
	}

	/** default constructor */
	public Ticketchecklist() {
	}

	/** minimal constructor */
	public Ticketchecklist(Long checkid, Long szsoldticketid,
			Long isalesvoucherdetailsid, Long isalesvoucherid, Long iticketstationid,
			Long igardengateid, Long iscenicid, String dtmakedate) {
		this.checkid = checkid;
		this.szsoldticketid = szsoldticketid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
		this.igardengateid = igardengateid;
		this.iscenicid = iscenicid;
		this.dtmakedate = dtmakedate;
	}

	/** full constructor */
	public Ticketchecklist(Long checkid, Long szsoldticketid,
			Long isalesvoucherdetailsid, Long isalesvoucherid, Long iticketstationid,
			Long igardengateid, Long iscenicid, String dtmakedate,
			Long iaccessequipid, String zfseq,String empid,String accounttime) {
		this.checkid = checkid;
		this.szsoldticketid = szsoldticketid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
		this.igardengateid = igardengateid;
		this.iscenicid = iscenicid;
		this.dtmakedate = dtmakedate;
		this.iaccessequipid = iaccessequipid;
		this.zfseq = zfseq;
		this.empid=empid;
		this.accounttime=accounttime;
	}

	// Property accessors

	public Long getCheckid() {
		return this.checkid;
	}

	public void setCheckid(Long checkid) {
		this.checkid = checkid;
	}

	public Long getSzsoldticketid() {
		return this.szsoldticketid;
	}

	public void setSzsoldticketid(Long szsoldticketid) {
		this.szsoldticketid = szsoldticketid;
	}

	public Long getIsalesvoucherdetailsid() {
		return this.isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
	}

	public Long getIsalesvoucherid() {
		return this.isalesvoucherid;
	}

	public void setIsalesvoucherid(Long isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}

	public Long getIticketstationid() {
		return this.iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIaccessequipid() {
		return this.iaccessequipid;
	}

	public void setIaccessequipid(Long iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public String getZfseq() {
		return this.zfseq;
	}

	public void setZfseq(String zfseq) {
		this.zfseq = zfseq;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getAccounttime() {
		return accounttime;
	}

	public void setAccounttime(String accounttime) {
		this.accounttime = accounttime;
	}

}