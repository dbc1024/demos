package com.ectrip.ticket.model.provider;

/**
 * Netusermessage entity. @author MyEclipse Persistence Tools
 */

public class Netusermessage implements java.io.Serializable {

	// Fields

	private Long netid;//主键ID
	private Long productid;//产品ID
	private String messtitle;//留言标题
	private String netusername;//留言人的姓名
	private String netuserphone;//留言人的电话
	private String message;//留言内容
	private String createdate;//留言时间
	private String status;//状态   00 -- 未审合     01 -- 审合通过  02 ---审合不能过
	private String noted1;//审核时间
	private String noted2;//-------备用字段,此字段改为留言回复
	private String noted3;//-------备用字段,此字段改为留言回复状态，00表示未回复，01表示已回复
	private String noted4;//-------备用字段
	private Long spare1;//审核人
	private Long spare2;//此备用字段改为服务商，可以对服务商留言，如果产品ID为空，则是对服务商留言
	private Long spare3;//---------备用字段
	private Long spare4;//---------备用字段

	// Constructors

	/** default constructor */
	public Netusermessage() {
	}

	/** minimal constructor */
	public Netusermessage(Long netid, Long productid,
						  String messtitle, String message, String status) {
		this.netid = netid;
		this.productid = productid;
		this.messtitle = messtitle;
		this.message = message;
		this.status = status;
	}

	/** full constructor */
	public Netusermessage(Long netid, Long productid,
						  String messtitle, String netusername, String netuserphone,
						  String message, String createdate, String status, String noted1,String noted3,
						  String noted4,String noted2, Long spare1, Long spare2, Long spare3, Long spare4) {
		this.netid = netid;
		this.productid = productid;
		this.messtitle = messtitle;
		this.netusername = netusername;
		this.netuserphone = netuserphone;
		this.message = message;
		this.createdate = createdate;
		this.status = status;
		this.noted1 = noted1;
		this.noted2 = noted2;
		this.noted3 = noted3;
		this.noted4 = noted4;
		this.spare1 = spare1;
		this.spare2 = spare2;
		this.spare3 = spare3;
		this.spare4 = spare4;
	}

	// Property accessors



	public String getMesstitle() {
		return this.messtitle;
	}

	public Long getNetid() {
		return netid;
	}

	public void setNetid(Long netid) {
		this.netid = netid;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public void setMesstitle(String messtitle) {
		this.messtitle = messtitle;
	}

	public String getNetusername() {
		return this.netusername;
	}

	public void setNetusername(String netusername) {
		this.netusername = netusername;
	}

	public String getNetuserphone() {
		return this.netuserphone;
	}

	public void setNetuserphone(String netuserphone) {
		this.netuserphone = netuserphone;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoted1() {
		return this.noted1;
	}

	public void setNoted1(String noted1) {
		this.noted1 = noted1;
	}

	public String getNoted2() {
		return this.noted2;
	}

	public void setNoted2(String noted2) {
		this.noted2 = noted2;
	}

	public Long getSpare1() {
		return spare1;
	}

	public void setSpare1(Long spare1) {
		this.spare1 = spare1;
	}

	public Long getSpare2() {
		return spare2;
	}

	public void setSpare2(Long spare2) {
		this.spare2 = spare2;
	}

	public String getNoted3() {
		return noted3;
	}

	public void setNoted3(String noted3) {
		this.noted3 = noted3;
	}

	public String getNoted4() {
		return noted4;
	}

	public void setNoted4(String noted4) {
		this.noted4 = noted4;
	}

	public Long getSpare3() {
		return spare3;
	}

	public void setSpare3(Long spare3) {
		this.spare3 = spare3;
	}

	public Long getSpare4() {
		return spare4;
	}

	public void setSpare4(Long spare4) {
		this.spare4 = spare4;
	}



}