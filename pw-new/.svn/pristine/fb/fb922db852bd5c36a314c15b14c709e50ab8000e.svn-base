package com.ectrip.ticket.model.provider;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.upload.model.Upfile;

/**
 * Linetravel entity. @author MyEclipse Persistence Tools
 */

public class Linetravel  implements java.io.Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long linetravelid;//
	private Long itickettypeid;
	private String linetravelname;//行程名
	private String hotelname;//入住酒店
	private String note;//说明
	private String dtmakedate;//操作时间
	private String lineprod;//可选服务项
	private String linedress;//行程区域
	private String hoteluct;//入住地区
	private Integer sort;//排序

	//非数据库字段
	private String[] upids;//图片
	private List<Upfile> list = new ArrayList<Upfile>();//图库列表

	// Constructors

	/** default constructor */
	public Linetravel() {
	}


	/** full constructor */
	public Linetravel(Long linetravelid, String linetravelname, String hotelname, String note, Long linepictureid, String dtmakedate, String lineprod, String linedress, String hoteluct) {
		this.linetravelid = linetravelid;
		this.linetravelname = linetravelname;
		this.hotelname = hotelname;
		this.note = note;
		this.dtmakedate = dtmakedate;
		this.lineprod = lineprod;
		this.linedress = linedress;
		this.hoteluct = hoteluct;
	}


	// Property accessors



	public void setLinetravelid(Long linetravelid) {
		this.linetravelid = linetravelid;
	}



	public String getLinetravelname() {
		return linetravelname;
	}


	public void setLinetravelname(String linetravelname) {
		this.linetravelname = linetravelname;
	}


	public Long getLinetravelid() {
		return linetravelid;
	}


	public String getHotelname() {
		return this.hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getLineprod() {
		return this.lineprod;
	}

	public void setLineprod(String lineprod) {
		this.lineprod = lineprod;
	}

	public String getLinedress() {
		return this.linedress;
	}

	public void setLinedress(String linedress) {
		this.linedress = linedress;
	}

	public String getHoteluct() {
		return this.hoteluct;
	}

	public void setHoteluct(String hoteluct) {
		this.hoteluct = hoteluct;
	}

	public String[] getUpids() {
		return upids;
	}

	public void setUpids(String[] upids) {
		this.upids = upids;
	}


	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}


	public List<Upfile> getList() {
		return list;
	}


	public void setList(List<Upfile> list) {
		this.list = list;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}



}