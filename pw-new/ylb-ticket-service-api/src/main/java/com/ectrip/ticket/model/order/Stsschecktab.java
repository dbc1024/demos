package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Stsschecktab entity.
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Stsschecktab implements java.io.Serializable {

	// Fields


	@Id
	private StsschecktabId id;
	private Long igardengateid;
	private Long iscenicid;
	private String szscenicname;//服务商名称
	private Long icrowdkindid;
	private String szcrowdkindname;//人类种群名称 
	private Long itickettypeid;
	private String sztickettypename;//产品名称
	private Long iztickettypeid;
	private Long bychecktype;
	private String dtlastcheckdate;
	private Long bylastcheckdir;
	private String byconsumemode;
	private Long ipasstimes;
	private Long msingletimes;
	private Long ipassedtimes;
	private Double mlimitconsume;
	private Double msingleconsume;
	private Double mconsumed;
	private Long ipartitionsign;
	private Long iversion;
	private String dtbegindate;
	private String dtenddate;
	private String szwicketsetinfo;
	private Long byisout;
	private Long isvalid;
	private Long tripid;
	private String dtmakedate;
	private Long mpasstimes;//每次通过次数
	private Long msingledtimes;//单次已通过次数
	private String szticketprintno;
	private Long ibusinessid;
	private Double mactualsaleprice;
  
	private String manyouno;	
	private String myzj;
	private String name1;
	private String zjno1;
	private Long byusage;//使用人群（0 -- 常规票, 1-- 员工卡,2 -- 居民卡,3 -- 充值票,4 -- 结算卡,5 -- 贵宾票,6 -- 贵宾票+ 结算）
	private Long byuselimit;//使用限制（0一次性，1长期性）
	private Long isfristtimaeyz;//首次检票间隔时间
	private Long isfristactive;//是否首次检票激活
	private Long validityday;//有效天数
	
	

	private String bywicketconsumetype;				//检票通行方式 01:一检 一人 02:一检多人 03:一单一检 04：一次扣完  5:落杆检票
	private String byregfingerprinttype;			//身份识别类型 00:无 05：指纹识别+人像抓拍
	private Long itimeinterval;						//检票间隔时间(秒)
	private String szmemo;//备注
	
	
	private Long isa;
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;
	private Long isg;
	private Long ish;
	private Long isi;
	private Long isj;
	private String notea;	
	private String noteb;
	private String notec;
	private String noted;
	private String notee;
	private String notef;
	private String noteg;
	private String noteh;
	private String notei;
	private String notej;
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
	
	// Constructors

	/** default constructor */
	public Stsschecktab() {
	}

	/** minimal constructor */
	public Stsschecktab(StsschecktabId id, Long igardengateid,
			Long iscenicid, Long icrowdkindid, Long itickettypeid,
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
	}

	/** full constructor */
	public Stsschecktab(StsschecktabId id, Long igardengateid,
			Long iscenicid, Long icrowdkindid, Long itickettypeid,
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
	}

	// Property accessors

	public StsschecktabId getId() {
		return this.id;
	}

	public void setId(StsschecktabId id) {
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
	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getSzticketprintno() {
		return szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public String getManyouno() {
		return manyouno;
	}

	public void setManyouno(String manyouno) {
		this.manyouno = manyouno;
	}

	public String getMyzj() {
		return myzj;
	}

	public void setMyzj(String myzj) {
		this.myzj = myzj;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getZjno1() {
		return zjno1;
	}

	public void setZjno1(String zjno1) {
		this.zjno1 = zjno1;
	}

	public Long getByusage() {
		return byusage;
	}

	public void setByusage(Long byusage) {
		this.byusage = byusage;
	}

	public Long getByuselimit() {
		return byuselimit;
	}

	public void setByuselimit(Long byuselimit) {
		this.byuselimit = byuselimit;
	}

	public Long getIsfristtimaeyz() {
		return isfristtimaeyz;
	}

	public void setIsfristtimaeyz(Long isfristtimaeyz) {
		this.isfristtimaeyz = isfristtimaeyz;
	}

	public Long getIsfristactive() {
		return isfristactive;
	}

	public void setIsfristactive(Long isfristactive) {
		this.isfristactive = isfristactive;
	}

	public Long getValidityday() {
		return validityday;
	}

	public void setValidityday(Long validityday) {
		this.validityday = validityday;
	}

	public String getBywicketconsumetype() {
		return bywicketconsumetype;
	}

	public void setBywicketconsumetype(String bywicketconsumetype) {
		this.bywicketconsumetype = bywicketconsumetype;
	}

	public String getByregfingerprinttype() {
		return byregfingerprinttype;
	}

	public void setByregfingerprinttype(String byregfingerprinttype) {
		this.byregfingerprinttype = byregfingerprinttype;
	}

	public Long getItimeinterval() {
		return itimeinterval;
	}

	public void setItimeinterval(Long itimeinterval) {
		this.itimeinterval = itimeinterval;
	}

	public String getSzmemo() {
		return szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}
	public Double getMactualsaleprice() {
		return mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}
	  public Long getIbusinessid() {
			return ibusinessid;
		}

		public void setIbusinessid(Long ibusinessid) {
			this.ibusinessid = ibusinessid;
		}
		public Long getIsa() {
			return isa;
		}

		public void setIsa(Long isa) {
			this.isa = isa;
		}

		public Long getIsb() {
			return isb;
		}

		public void setIsb(Long isb) {
			this.isb = isb;
		}

		public Long getIsc() {
			return isc;
		}

		public void setIsc(Long isc) {
			this.isc = isc;
		}

		public Long getIsd() {
			return isd;
		}

		public void setIsd(Long isd) {
			this.isd = isd;
		}

		public Long getIse() {
			return ise;
		}

		public void setIse(Long ise) {
			this.ise = ise;
		}

		public Long getIsf() {
			return isf;
		}

		public void setIsf(Long isf) {
			this.isf = isf;
		}

		public Long getIsg() {
			return isg;
		}

		public void setIsg(Long isg) {
			this.isg = isg;
		}

		public Long getIsh() {
			return ish;
		}

		public void setIsh(Long ish) {
			this.ish = ish;
		}

		public Long getIsi() {
			return isi;
		}

		public void setIsi(Long isi) {
			this.isi = isi;
		}

		public Long getIsj() {
			return isj;
		}

		public void setIsj(Long isj) {
			this.isj = isj;
		}

		public String getNotea() {
			return notea;
		}

		public void setNotea(String notea) {
			this.notea = notea;
		}

		public String getNoteb() {
			return noteb;
		}

		public void setNoteb(String noteb) {
			this.noteb = noteb;
		}

		public String getNotec() {
			return notec;
		}

		public void setNotec(String notec) {
			this.notec = notec;
		}

		public String getNoted() {
			return noted;
		}

		public void setNoted(String noted) {
			this.noted = noted;
		}

		public String getNotee() {
			return notee;
		}

		public void setNotee(String notee) {
			this.notee = notee;
		}

		public String getNotef() {
			return notef;
		}

		public void setNotef(String notef) {
			this.notef = notef;
		}

		public String getNoteg() {
			return noteg;
		}

		public void setNoteg(String noteg) {
			this.noteg = noteg;
		}

		public String getNoteh() {
			return noteh;
		}

		public void setNoteh(String noteh) {
			this.noteh = noteh;
		}

		public String getNotei() {
			return notei;
		}

		public void setNotei(String notei) {
			this.notei = notei;
		}

		public String getNotej() {
			return notej;
		}

		public void setNotej(String notej) {
			this.notej = notej;
		}
}