package com.ectrip.sys.model.syspar;

/**
 * Holidaydetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Holidaydetail implements java.io.Serializable {

	// Fields

	private Long holidaydetailid;    //��ϸID
	private Long holidayid;			//����ID
	private String startdata;		//��ʼʱ��
	private String enddata;			//����ʱ��

	// Constructors

	/** default constructor */
	public Holidaydetail() {
	}

	/** full constructor */
	public Holidaydetail(Long holidaydetailid, Long holidayid,
			String startdata, String enddata) {
		this.holidaydetailid = holidaydetailid;
		this.holidayid = holidayid;
		this.startdata = startdata;
		this.enddata = enddata;
	}

	// Property accessors

	public Long getHolidaydetailid() {
		return this.holidaydetailid;
	}

	public void setHolidaydetailid(Long holidaydetailid) {
		this.holidaydetailid = holidaydetailid;
	}

	public Long getHolidayid() {
		return this.holidayid;
	}

	public void setHolidayid(Long holidayid) {
		this.holidayid = holidayid;
	}

	public String getStartdata() {
		return this.startdata;
	}

	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}

	public String getEnddata() {
		return this.enddata;
	}

	public void setEnddata(String enddata) {
		this.enddata = enddata;
	}

}