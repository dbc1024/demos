package com.ectrip.sys.model.syspar;
/**
 * Receiptmanager entity. @author MyEclipse Persistence Tools
 */

public class Receiptmanager implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long iscenicid;
	private String printno;
	private String contenttype;
	private String printtype;
	private String content;
	private Long colnum;
	private Long ordernum;

	//�����ݿ��ֶ�
	private String szscenicname;
	private String printnoname;
	private String contenttypename;
	private String printtypename;
	private Long isb;
	// Constructors

	/** default constructor */
	public Receiptmanager() {
	}

	/** minimal constructor */
	public Receiptmanager(Long seq, Long iscenicid, String printno,
			String contenttype, String printtype, Long ordernum) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.printno = printno;
		this.contenttype = contenttype;
		this.printtype = printtype;
		this.ordernum = ordernum;
	}

	/** full constructor */
	public Receiptmanager(Long seq, Long iscenicid, String printno,
			String contenttype, String printtype, String content,
			Long colnum, Long ordernum) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.printno = printno;
		this.contenttype = contenttype;
		this.printtype = printtype;
		this.content = content;
		this.colnum = colnum;
		this.ordernum = ordernum;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getPrintno() {
		return this.printno;
	}

	public void setPrintno(String printno) {
		this.printno = printno;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getPrinttype() {
		return this.printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getColnum() {
		return this.colnum;
	}

	public void setColnum(Long colnum) {
		this.colnum = colnum;
	}

	public Long getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getPrintnoname() {
		return printnoname;
	}

	public void setPrintnoname(String printnoname) {
		this.printnoname = printnoname;
	}

	public String getContenttypename() {
		return contenttypename;
	}

	public void setContenttypename(String contenttypename) {
		this.contenttypename = contenttypename;
	}

	public String getPrinttypename() {
		return printtypename;
	}

	public void setPrinttypename(String printtypename) {
		this.printtypename = printtypename;
	}

	public Long getIsb() {
		return isb;
	}

	public void setIsb(Long isb) {
		this.isb = isb;
	}

}