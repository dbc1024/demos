package com.ectrip.ticket.model.order;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Stssoldticketsubtab entity.
 * 售出门票子表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Stssoldticketsubtab")
public class Stssoldticketsubtab implements java.io.Serializable {

	// Fields

	@EmbeddedId
	private StssoldticketsubtabId id;
	@Column(name="IGARDENGATEID")
	private Long igardengateid;
	@Column(name="ISCENICID")
	private Long iscenicid;
	@Column(name="ICROWDKINDID")
	private Long icrowdkindid;
	@Column(name="ITICKETTYPEID")
	private Long itickettypeid;
	@Column(name="IZTICKETTYPEID")
	private Long iztickettypeid;
	@Column(name="BYCHECKTYPE")
	private Long bychecktype;
	@Column(name="DTLASTCHECKDATE")
	private String dtlastcheckdate;
	@Column(name="BYLASTCHECKDIR")
	private Long bylastcheckdir;
	@Column(name="BYCONSUMEMODE")
	private String byconsumemode;
	@Column(name="IPASSTIMES")
	private Long ipasstimes;
	@Column(name="MSINGLETIMES")
	private Long msingletimes;
	@Column(name="IPASSEDTIMES")
	private Long ipassedtimes;
	@Column(name="MLIMITCONSUME")
	private Double mlimitconsume;
	@Column(name="MSINGLECONSUME")
	private Double msingleconsume;
	@Column(name="MCONSUMED")
	private Double mconsumed;
	@Column(name="IPARTITIONSIGN")
	private Long ipartitionsign;
	@Column(name="IVERSION")
	private Long iversion;
	@Column(name="DTBEGINDATE")
	private String dtbegindate;
	@Column(name="DTENDDATE")
	private String dtenddate;
	@Column(name="SZWICKETSETINFO")
	private String szwicketsetinfo;
	@Column(name="BYISOUT")
	private Long byisout;
	@Column(name="ISVALID")
	private Long isvalid;
	@Column(name="TRIPID")
	private Long tripid;
	@Column(name="DTMAKEDATE")
	private String dtmakedate;
	@Column(name="MPASSTIMES")
	private Long mpasstimes;//每次通过次数
	@Column(name="MSINGLEDTIMES")
	private Long msingledtimes;//单次已通过次数
	//此对象的联合主键
	@Transient
	private Long szsoldticketsubid;
	@Transient
	private Long szsoldticketid;
	@Transient
	private Long isalesvoucherdetailsid;
	@Transient
	private Long isalesvoucherid;
	@Transient
	private Long iticketstationid;
	
	@Column(name="TIMESTART")
	private String timeStart;//分时时段开始时间
	@Column(name="TIMEEND")
	private String timeEnd;//分时时段结束时间
	@Column(name="TIMEID")
	Long timeId;//分时时段ID

	// Constructors

	/** default constructor */
	public Stssoldticketsubtab() {
	}

	/** minimal constructor */
	public Stssoldticketsubtab(StssoldticketsubtabId id, Long igardengateid,
			Long iscenicid, Long icrowdkindid, Long itickettypeid,String timeStart,String timeEnd,
			Long iztickettypeid, Long bychecktype, Long bylastcheckdir,
			String byconsumemode, Long ipasstimes, Long msingletimes,
			Long ipassedtimes, Double mlimitconsume, Double msingleconsume,
			Double mconsumed, String dtbegindate, String dtenddate, Long byisout,
			Long tripid) {
		this.id = id;
		this.igardengateid = igardengateid;
		this.iscenicid = iscenicid;
		this.icrowdkindid = icrowdkindid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.bychecktype = bychecktype;
		this.bylastcheckdir = bylastcheckdir;
		this.byconsumemode = byconsumemode;
		this.ipasstimes = ipasstimes;
		this.msingletimes = msingletimes;
		this.ipassedtimes = ipassedtimes;
		this.mlimitconsume = mlimitconsume;
		this.msingleconsume = msingleconsume;
		this.mconsumed = mconsumed;
		this.dtbegindate = dtbegindate;
		this.dtenddate = dtenddate;
		this.byisout = byisout;
		this.tripid = tripid;
		this.timeStart=timeStart;
		this.timeEnd=timeEnd;
	}

	/** full constructor */
	public Stssoldticketsubtab(StssoldticketsubtabId id, Long igardengateid,
			Long iscenicid, Long icrowdkindid, Long itickettypeid,String timeStart,String timeEnd,
			Long iztickettypeid, Long bychecktype, String dtlastcheckdate,
			Long bylastcheckdir, String byconsumemode, Long ipasstimes,
			Long msingletimes, Long ipassedtimes, Double mlimitconsume,
			Double msingleconsume, Double mconsumed, Long ipartitionsign,
			Long iversion, String dtbegindate, String dtenddate,
			String szwicketsetinfo, Long byisout, Long tripid,Long mpasstimes,Long msingledtimes) {
		this.id = id;
		this.igardengateid = igardengateid;
		this.iscenicid = iscenicid;
		this.icrowdkindid = icrowdkindid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.bychecktype = bychecktype;
		this.dtlastcheckdate = dtlastcheckdate;
		this.bylastcheckdir = bylastcheckdir;
		this.byconsumemode = byconsumemode;
		this.ipasstimes = ipasstimes;
		this.msingletimes = msingletimes;
		this.ipassedtimes = ipassedtimes;
		this.mlimitconsume = mlimitconsume;
		this.msingleconsume = msingleconsume;
		this.mconsumed = mconsumed;
		this.ipartitionsign = ipartitionsign;
		this.iversion = iversion;
		this.dtbegindate = dtbegindate;
		this.dtenddate = dtenddate;
		this.szwicketsetinfo = szwicketsetinfo;
		this.byisout = byisout;
		this.tripid = tripid;
		this.mpasstimes=mpasstimes;
		this.msingledtimes=msingledtimes;
		this.timeStart=timeStart;
		this.timeEnd=timeEnd;
	}

	// Property accessors

	public StssoldticketsubtabId getId() {
		return this.id;
	}

	public void setId(StssoldticketsubtabId id) {
		this.id = id;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIztickettypeid() {
		return this.iztickettypeid;
	}

	public void setIztickettypeid(Long iztickettypeid) {
		this.iztickettypeid = iztickettypeid;
	}

	public Long getBychecktype() {
		return this.bychecktype;
	}

	public void setBychecktype(Long bychecktype) {
		this.bychecktype = bychecktype;
	}

	public String getDtlastcheckdate() {
		return this.dtlastcheckdate;
	}

	public void setDtlastcheckdate(String dtlastcheckdate) {
		this.dtlastcheckdate = dtlastcheckdate;
	}

	public Long getBylastcheckdir() {
		return this.bylastcheckdir;
	}

	public void setBylastcheckdir(Long bylastcheckdir) {
		this.bylastcheckdir = bylastcheckdir;
	}

	public String getByconsumemode() {
		return this.byconsumemode;
	}

	public void setByconsumemode(String byconsumemode) {
		this.byconsumemode = byconsumemode;
	}

	public Long getIpasstimes() {
		return this.ipasstimes;
	}

	public void setIpasstimes(Long ipasstimes) {
		this.ipasstimes = ipasstimes;
	}

	public Long getMsingletimes() {
		return this.msingletimes;
	}

	public void setMsingletimes(Long msingletimes) {
		this.msingletimes = msingletimes;
	}

	public Long getIpassedtimes() {
		return this.ipassedtimes;
	}

	public void setIpassedtimes(Long ipassedtimes) {
		this.ipassedtimes = ipassedtimes;
	}

	public Double getMlimitconsume() {
		return this.mlimitconsume;
	}

	public void setMlimitconsume(Double mlimitconsume) {
		this.mlimitconsume = mlimitconsume;
	}

	public Double getMsingleconsume() {
		return this.msingleconsume;
	}

	public void setMsingleconsume(Double msingleconsume) {
		this.msingleconsume = msingleconsume;
	}

	public Double getMconsumed() {
		return this.mconsumed;
	}

	public void setMconsumed(Double mconsumed) {
		this.mconsumed = mconsumed;
	}

	public Long getIpartitionsign() {
		return this.ipartitionsign;
	}

	public void setIpartitionsign(Long ipartitionsign) {
		this.ipartitionsign = ipartitionsign;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public String getDtbegindate() {
		return this.dtbegindate;
	}

	public void setDtbegindate(String dtbegindate) {
		this.dtbegindate = dtbegindate;
	}

	public String getDtenddate() {
		return this.dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public String getSzwicketsetinfo() {
		return this.szwicketsetinfo;
	}

	public void setSzwicketsetinfo(String szwicketsetinfo) {
		this.szwicketsetinfo = szwicketsetinfo;
	}

	public Long getByisout() {
		return this.byisout;
	}

	public void setByisout(Long byisout) {
		this.byisout = byisout;
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getSzsoldticketsubid() {
		return szsoldticketsubid;
	}

	public void setSzsoldticketsubid(Long szsoldticketsubid) {
		this.szsoldticketsubid = szsoldticketsubid;
	}

	public Long getSzsoldticketid() {
		return szsoldticketid;
	}

	public void setSzsoldticketid(Long szsoldticketid) {
		this.szsoldticketid = szsoldticketid;
	}

	public Long getIsalesvoucherdetailsid() {
		return isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
	}

	public Long getIsalesvoucherid() {
		return isalesvoucherid;
	}

	public void setIsalesvoucherid(Long isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}

	public Long getIticketstationid() {
		return iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public Long getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getMpasstimes() {
		return mpasstimes;
	}

	public void setMpasstimes(Long mpasstimes) {
		this.mpasstimes = mpasstimes;
	}

	public Long getMsingledtimes() {
		return msingledtimes;
	}

	public void setMsingledtimes(Long msingledtimes) {
		this.msingledtimes = msingledtimes;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}