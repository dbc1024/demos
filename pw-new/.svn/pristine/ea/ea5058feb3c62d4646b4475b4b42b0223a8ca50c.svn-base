package com.ectrip.upload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Upfile entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Upfilev5")
public class Upfilev5 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@Id
	private Long upid;
	@Column(name="upname")
	private String upname;
	@Column(name="upfilename")
	private String upfilename;
	@Column(name="upadder")
	private String upadder;
	@Column(name="filetype")
	private String filetype;
	@Column(name="filesize")
	private String filesize;
	@Column(name="updatetime")
	private String updatetime;
	@Column(name="upfrom")
	private String upfrom;
	@Column(name="note")
	private String note;
	@Column(name="author")
	private String author;
	@Column(name="utype")
	private String utype;

	// Constructors

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	/** default constructor */
	public Upfilev5() {
	}

	/** minimal constructor */
	public Upfilev5(String upfilename, String upadder, String updatetime) {
		this.upfilename = upfilename;
		this.upadder = upadder;
		this.updatetime = updatetime;
	}

	/** full constructor */
	public Upfilev5(String upname, String upfilename, String upadder,
			String filetype, String filesize, String updatetime,String upfrom,String note,String author,String utype) {
		this.upname = upname;
		this.upfilename = upfilename;
		this.upadder = upadder;
		this.filetype = filetype;
		this.filesize = filesize;
		this.updatetime = updatetime;
		this.upfrom = upfrom;
		this.note = note;
		this.author = author;
		this.utype=utype;
	}

	// Property accessors

	public Long getUpid() {
		return this.upid;
	}

	public void setUpid(Long upid) {
		this.upid = upid;
	}

	public String getUpname() {
		return this.upname;
	}

	public void setUpname(String upname) {
		this.upname = upname;
	}

	public String getUpfilename() {
		return this.upfilename;
	}

	public void setUpfilename(String upfilename) {
		this.upfilename = upfilename;
	}

	public String getUpadder() {
		return this.upadder;
	}

	public void setUpadder(String upadder) {
		this.upadder = upadder;
	}

	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilesize() {
		return this.filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpfrom() {
		return upfrom;
	}

	public void setUpfrom(String upfrom) {
		this.upfrom = upfrom;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}