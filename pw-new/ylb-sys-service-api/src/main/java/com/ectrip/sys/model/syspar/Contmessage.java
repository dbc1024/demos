package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Contmessage implements java.io.Serializable {

	// Fields

	@Id
	private Long conid;        // ID 
	private String controlpoin; //发送控制点
	private String templates;   //内容模板
	private Long byisuse;       //是否使用
	private Long inote1;
	private Long inote2;
	private String snote1;
	private String snote2;

	// Constructors

	/** default constructor */
	public Contmessage() {
	}

	/** minimal constructor */
	public Contmessage(Long conid, String controlpoin, Long byisuse) {
		this.conid = conid;
		this.controlpoin = controlpoin;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Contmessage(Long conid, String controlpoin, String templates,
			Long byisuse, Long inote1, Long inote2,
			String snote1, String snote2) {
		this.conid = conid;
		this.controlpoin = controlpoin;
		this.templates = templates;
		this.byisuse = byisuse;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.snote1 = snote1;
		this.snote2 = snote2;
	}

	// Property accessors

	public Long getConid() {
		return this.conid;
	}

	public void setConid(Long conid) {
		this.conid = conid;
	}

	public String getControlpoin() {
		return this.controlpoin;
	}

	public void setControlpoin(String controlpoin) {
		this.controlpoin = controlpoin;
	}

	public String getTemplates() {
		return this.templates;
	}

	public void setTemplates(String templates) {
		this.templates = templates;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public Long getInote1() {
		return this.inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Long getInote2() {
		return this.inote2;
	}

	public void setInote2(Long inote2) {
		this.inote2 = inote2;
	}

	public String getSnote1() {
		return this.snote1;
	}

	public void setSnote1(String snote1) {
		this.snote1 = snote1;
	}

	public String getSnote2() {
		return this.snote2;
	}

	public void setSnote2(String snote2) {
		this.snote2 = snote2;
	}

}
