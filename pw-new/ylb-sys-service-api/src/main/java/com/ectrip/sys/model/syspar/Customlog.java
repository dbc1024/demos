package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Customlog implements java.io.Serializable {

	// Fields
	@Id
	private Long sysid;//����
	private String usid;//������
	private String stlg;//������ʶ�����̨ϵͳ���������� ��  
	//0081 ����ǰ̨�û�    0082 �޸�ǰ̨�û�   0083 ɾ��ǰ̨�û�
	private String logdatetime;//����ʱ��
	private String brief;//��Ҫ˵��
	private String note;//��ϸ˵��

	// Constructors

	/** default constructor */
	public Customlog() {
	}

	/** minimal constructor */
	public Customlog(Long sysid, String stlg, String brief) {
		this.sysid = sysid;
		this.stlg = stlg;
		this.brief = brief;
	}

	/** full constructor */
	public Customlog(Long sysid, String usid, String stlg, String logdatetime,
			String brief, String note) {
		this.sysid = sysid;
		this.usid = usid;
		this.stlg = stlg;
		this.logdatetime = logdatetime;
		this.brief = brief;
		this.note = note;
	}

	// Property accessors

	public Long getSysid() {
		return this.sysid;
	}

	public void setSysid(Long sysid) {
		this.sysid = sysid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getStlg() {
		return this.stlg;
	}

	public void setStlg(String stlg) {
		this.stlg = stlg;
	}

	public String getLogdatetime() {
		return this.logdatetime;
	}

	public void setLogdatetime(String logdatetime) {
		this.logdatetime = logdatetime;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}