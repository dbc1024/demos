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
@Table(name="Upfile")
public class Upfile implements java.io.Serializable {

	// Fields
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

	/**
	 * 请不要删除　李进增加
	 */
	private String mupname;

	public String getMupname() {
		return mupname;
	}

	public void setMupname(String mupname) {
		this.mupname = mupname;
	}

	public String getOupname() {
		return oupname;
	}

	public void setOupname(String oupname) {
		this.oupname = oupname;
	}

	public String getUpadd() {
		return upadd;
	}

	public void setUpadd(String upadd) {
		this.upadd = upadd;
	}

	public String getUptype() {
		return uptype;
	}

	public void setUptype(String uptype) {
		this.uptype = uptype;
	}

	public String getUpsize() {
		return upsize;
	}

	public void setUpsize(String upsize) {
		this.upsize = upsize;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	private String oupname;
	private String upadd;
	private String uptype;
	private String upsize;
	private String uptime;

	/**
	 * 请不要删除　李进结束
	 */
	// Constructors

	/** default constructor */
	public Upfile() {
	}

	/** minimal constructor */
	public Upfile(Long upid, String upfilename, String upadder, String updatetime) {
		this.upid = upid;
		this.upfilename = upfilename;
		this.upadder = upadder;
		this.updatetime = updatetime;
	}

	/** full constructor */
	public Upfile(Long upid, String upname, String upfilename, String upadder, String filetype, String filesize,
			String updatetime, String upfrom, String note, String author, String utype) {
		this.upid = upid;
		this.upname = upname;
		this.upfilename = upfilename;
		this.upadder = upadder;
		this.filetype = filetype;
		this.filesize = filesize;
		this.updatetime = updatetime;
		this.upfrom = upfrom;
		this.note = note;
		this.author = author;
		this.utype = utype;
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
		return this.upfrom;
	}

	public void setUpfrom(String upfrom) {
		this.upfrom = upfrom;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

}