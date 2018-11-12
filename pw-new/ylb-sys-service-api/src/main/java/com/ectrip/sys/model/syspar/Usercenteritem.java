package com.ectrip.sys.model.syspar;

/**
 * Usercenteritem entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Usercenteritem implements java.io.Serializable {

	// Fields

	private Long itemid;
	private String itemname;
	private String itemurl;
	private Long itemparent;
	private String itemstatus;
	private String itemauthority;
	private String itemmark;

	// Constructors

	/** default constructor */
	public Usercenteritem() {
	}

	/** minimal constructor */
	public Usercenteritem(Long itemid) {
		this.itemid = itemid;
	}

	/** full constructor */
	public Usercenteritem(Long itemid, String itemname, String itemurl,
			Long itemparent, String itemstatus, String itemmark) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.itemurl = itemurl;
		this.itemparent = itemparent;
		this.itemstatus = itemstatus;
		this.itemmark = itemmark;
	}

	// Property accessors

	public Long getItemid() {
		return this.itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemurl() {
		return this.itemurl;
	}

	public void setItemurl(String itemurl) {
		this.itemurl = itemurl;
	}

	public Long getItemparent() {
		return this.itemparent;
	}

	public void setItemparent(Long itemparent) {
		this.itemparent = itemparent;
	}

	public String getItemstatus() {
		return this.itemstatus;
	}

	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}

	public String getItemmark() {
		return this.itemmark;
	}

	public void setItemmark(String itemmark) {
		this.itemmark = itemmark;
	}

	public String getItemauthority() {
		return itemauthority;
	}

	public void setItemauthority(String itemauthority) {
		this.itemauthority = itemauthority;
	}

}