package com.ectrip.sys.model.syspar;

/**
 * Tablepar entity. @author MyEclipse Persistence Tools
 */

public class Tablepar implements java.io.Serializable {

	// Fields

	private Long seq;                    //���
	private String tablename;			//����
	private String tablenamechn;		//����������
	private String note;				//��ע
//	private Set columnpars = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tablepar() {
	}

	/** minimal constructor */
	public Tablepar(Long seq, String tablename, String tablenamechn) {
		this.seq = seq;
		this.tablename = tablename;
		this.tablenamechn = tablenamechn;
	}

	/** full constructor */
	public Tablepar(Long seq, String tablename, String tablenamechn,
			String note) {
		this.seq = seq;
		this.tablename = tablename;
		this.tablenamechn = tablenamechn;
		this.note = note;
	}

	// Property accessors


	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablenamechn() {
		return this.tablenamechn;
	}

	public void setTablenamechn(String tablenamechn) {
		this.tablenamechn = tablenamechn;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}