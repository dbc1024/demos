package com.ectrip.ticket.model.venuemarketing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Description：场地场次营销.
 *
 * @author Jzhenhua,Date 2011-10-17
 */
@Entity
@Table(name="trip")
public class Trip implements java.io.Serializable {

	// 数据库字段
	@Id
	private Long tripid; // 场次ID
	@Column(name="ISCENICID")
	private Long iscenicid; // 服务商编号
	@Column(name="TRIPCODE")
	private String tripcode; // 场次代码
	@Column(name="TRIPNAME")
	private String tripname; // 场次名字
	@Column(name="TRIPTYPE")
	private String triptype; // 场次类别,'01':景区,'02':景区观光车,'03':竹筏(漂流),'04':剧院,'05':索道
	@Column(name="STARTDATA")
	private String startdata; // 开始日期
	@Column(name="ENDDATA")
	private String enddata; // 结束日期
	@Column(name="STARTTIME")
	private String starttime; // 开始时间
	@Column(name="ENDTIME")
	private String endtime; // 结束时间
	@Column(name="IADVANCEMINUTE")
	private Long iadvanceminute; // 提前时间
	@Column(name="ILAGMINUTE")
	private Long ilagminute; // 滞后时间
	@Column(name="BYCYCLETYPE")
	private String bycycletype; // 周期类型,0:日,1:周,2:月
	@Column(name="ISKEEPMINUTE")
	private Long iskeepminute; // 预留量保留距场次结束时间
	@Column(name="LOOPFACTOR")
	private String loopfactor; // 周期数
	@Column(name="BYISUSE")
	private Long byisuse; // 使用状态,0:禁用,1:激活
	@Column(name="IVENUEID")
	private Long ivenueid;

	// 非数据库字段
	@Transient
	private String venueidname;
	@Transient
	private String striscenicid; // 服务商名
	@Transient
	private boolean ischecked;//是否选中
	@Transient
	private String beginhours;
	@Transient
	private String endhours;
	@Transient
	private String beginminute;
	@Transient
	private String endminute;
	// Constructors

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	/** default constructor */
	public Trip() {
	}

	/** minimal constructor */
	public Trip(Long tripid, String tripcode, String tripname, String triptype,
				String startdata, String enddata, String starttime, String endtime,
				Long iadvanceminute, Long ilagminute, String bycycletype,
				Long iskeepminute, Long byisuse) {
		this.tripid = tripid;
		this.tripcode = tripcode;
		this.tripname = tripname;
		this.triptype = triptype;
		this.startdata = startdata;
		this.enddata = enddata;
		this.starttime = starttime;
		this.endtime = endtime;
		this.iadvanceminute = iadvanceminute;
		this.ilagminute = ilagminute;
		this.bycycletype = bycycletype;
		this.iskeepminute = iskeepminute;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Trip(Long tripid, Long iscenicid, String tripcode, String tripname,
				String triptype, String startdata, String enddata,
				String starttime, String endtime, Long iadvanceminute,
				Long ilagminute, String bycycletype, Long iskeepminute,
				String loopfactor, Long byisuse) {
		this.tripid = tripid;
		this.iscenicid = iscenicid;
		this.tripcode = tripcode;
		this.tripname = tripname;
		this.triptype = triptype;
		this.startdata = startdata;
		this.enddata = enddata;
		this.starttime = starttime;
		this.endtime = endtime;
		this.iadvanceminute = iadvanceminute;
		this.ilagminute = ilagminute;
		this.bycycletype = bycycletype;
		this.iskeepminute = iskeepminute;
		this.loopfactor = loopfactor;
		this.byisuse = byisuse;
	}

	// Property accessors

	public String getTripcode() {
		return this.tripcode;
	}

	public Long getTripid() {
		return tripid;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIadvanceminute() {
		return iadvanceminute;
	}

	public void setIadvanceminute(Long iadvanceminute) {
		this.iadvanceminute = iadvanceminute;
	}

	public Long getIlagminute() {
		return ilagminute;
	}

	public void setIlagminute(Long ilagminute) {
		this.ilagminute = ilagminute;
	}

	public Long getIskeepminute() {
		return iskeepminute;
	}

	public void setIskeepminute(Long iskeepminute) {
		this.iskeepminute = iskeepminute;
	}

	public Long getByisuse() {
		return byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public void setTripcode(String tripcode) {
		this.tripcode = tripcode;
	}

	public String getTripname() {
		return this.tripname;
	}

	public void setTripname(String tripname) {
		this.tripname = tripname;
	}

	public String getTriptype() {
		return this.triptype;
	}

	public void setTriptype(String triptype) {
		this.triptype = triptype;
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

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getBycycletype() {
		return this.bycycletype;
	}

	public void setBycycletype(String bycycletype) {
		this.bycycletype = bycycletype;
	}

	public String getLoopfactor() {
		return this.loopfactor;
	}

	public void setLoopfactor(String loopfactor) {
		this.loopfactor = loopfactor;
	}

	public String getBeginhours() {
		return beginhours;
	}

	public void setBeginhours(String beginhours) {
		this.beginhours = beginhours;
	}

	public String getEndhours() {
		return endhours;
	}

	public void setEndhours(String endhours) {
		this.endhours = endhours;
	}

	public String getBeginminute() {
		return beginminute;
	}

	public void setBeginminute(String beginminute) {
		this.beginminute = beginminute;
	}

	public String getEndminute() {
		return endminute;
	}

	public void setEndminute(String endminute) {
		this.endminute = endminute;
	}

	public Long getIvenueid() {
		return ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public String getVenueidname() {
		return venueidname;
	}

	public void setVenueidname(String venueidname) {
		this.venueidname = venueidname;
	}


}