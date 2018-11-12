package com.ectrip.ec.model.order;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_ORDERLIST")
public class TOrderlist implements java.io.Serializable {

    // Fields
	@Id
    private TOrderlistId id;
	@Transient
    private String orderlistid;// 从主键中提取出来的
	@Transient
    private String orid;// 从主键中提取出来的
	@Transient
    private String iscenicid;
	@Transient
    private String sztickettypename;// 产品名称
    private Long itickettypeid;// 产品ID
    private Long icrowdkindpriceid;// 售价ID
    private Long icrowdkindid;// 人群种类ID
    private String dtstartdate;// 启用日期
    private String dtenddate;// 失效日期
    private Double pric;// 单价PRIC
    private Double jsprice;// 结算价格
    private Long numb;// 数量NUMB
    @Transient
    private Long nnumb;// 新增数量 不对应数据库
    private Long yhnumb;// 优惠数量
    private Double amnt;// 金额AMNT
    private Double yhamnt;// 优惠金额
    private Long ioffersschemeid;// 优惠方案ID
    private Long timeid;// 預約開始時間段ID
    private String starttime;// 預約開始時間
    private String endtime;// 預約結算時間
    private Long isa;
    private Long isb;
    private Long isc;
    private Long isd;
    private Long ise;
    private Long isf;
    private Long isg;
    private Long ish;//消费年积分
    private Long isi;//消费月积分
    private Long isj;
    private String notea;
    private String notej;
    private String notei;
    private String noteh;
    private String noteg;
    private String notef;
    private String notee;
    private String noted;
    private String notec;
    private String noteb;
    @Transient
    private List<TZorderlist> zorderlist;// 用于预定保存用
    @Transient
    private YOrderlist yorderlist;
    @Transient
    private  List slist;
    @Transient
    private String filmname;
    @Transient
    private Long tdyhnumb ;
    
    @Transient
    private String zj;// 非数据库字段
    @Transient
    private String szcrowdkindname;// 非数据库字段 记录价格种类
    @Transient
    private Double mhandcharge;

    // 非数据库字段
    @Transient
    private String scenictype;
    @Transient
    private String wharfname;
    @Transient
    private String tripname;
    @Transient
    private String wharftime;
    @Transient
    private String wharfdate;
    @Transient
    private String szscenicname;
    @Transient
    private List<TRealname> rlist;
    @Transient
    private int basenum;
    @Transient
    private int offernum;
    @Transient
    private int hasraft;
    @Transient
    private Long pdctrolid;
    
    public Long getTdyhnumb() {
		return tdyhnumb;
	}

	public void setTdyhnumb(Long tdyhnumb) {
		this.tdyhnumb = tdyhnumb;
	}

	public String getFilmname() {
		return filmname;
	}

	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}

	public List getSlist() {
		return slist;
	}

	public void setSlist(List slist) {
		this.slist = slist;
	}

	public YOrderlist getYorderlist() {
		return yorderlist;
	}

	public void setYorderlist(YOrderlist yorderlist) {
		this.yorderlist = yorderlist;
	}

	
    // Constructors

    public Double getMhandcharge() {
	return mhandcharge;
    }

    public void setMhandcharge(Double mhandcharge) {
	this.mhandcharge = mhandcharge;
    }

    public String getZj() {
	return zj;
    }

    public String getSzcrowdkindname() {
	return szcrowdkindname;
    }

    public void setSzcrowdkindname(String szcrowdkindname) {
	this.szcrowdkindname = szcrowdkindname;
    }

    public void setZj(String zj) {
	this.zj = zj;
    }

    /** default constructor */
    public TOrderlist() {
    }

    /** minimal constructor */
    public TOrderlist(TOrderlistId id, Long itickettypeid, Long icrowdkindid, String dtstartdate, String dtenddate, Double pric, Long numb,
	    Double amnt) {
	this.id = id;
	this.itickettypeid = itickettypeid;
	this.icrowdkindid = icrowdkindid;
	this.dtstartdate = dtstartdate;
	this.dtenddate = dtenddate;
	this.pric = pric;
	this.numb = numb;
	this.amnt = amnt;
    }

    /** full constructor */
    public TOrderlist(TOrderlistId id, Long itickettypeid, Long icrowdkindpriceid, Long icrowdkindid, String dtstartdate, String dtenddate,
    		String starttime,String endtime,Long timeid,Double pric, Double jsprice, Long numb, Long yhnumb, Double amnt, Double yhamnt, Long ioffersschemeid, Long isa, Long isb,
	    Long isc, Long isd, Long ise, Long isf, Long isg, Long ish, Long isi, Long isj, String notea, String notej, String notei,
	    String noteh, String noteg, String notef, String notee, String noted, String notec, String noteb, Set TZorderlists) {
	this.id = id;
	this.itickettypeid = itickettypeid;
	this.icrowdkindpriceid = icrowdkindpriceid;
	this.icrowdkindid = icrowdkindid;
	this.dtstartdate = dtstartdate;
	this.dtenddate = dtenddate;
	this.pric = pric;
	this.jsprice = jsprice;
	this.numb = numb;
	this.yhnumb = yhnumb;
	this.amnt = amnt;
	this.yhamnt = yhamnt;
	this.ioffersschemeid = ioffersschemeid;
	this.starttime=starttime;
	this.endtime=endtime;
	this.timeid=timeid;
	this.isa = isa;
	this.isb = isb;
	this.isc = isc;
	this.isd = isd;
	this.ise = ise;
	this.isf = isf;
	this.isg = isg;
	this.ish = ish;
	this.isi = isi;
	this.isj = isj;
	this.notea = notea;
	this.notej = notej;
	this.notei = notei;
	this.noteh = noteh;
	this.noteg = noteg;
	this.notef = notef;
	this.notee = notee;
	this.noted = noted;
	this.notec = notec;
	this.noteb = noteb;

    }

    // Property accessors

    public List<TZorderlist> getZorderlist() {
	return zorderlist;
    }

    public void setZorderlist(List<TZorderlist> zorderlist) {
	this.zorderlist = zorderlist;
    }

    public TOrderlistId getId() {
	return this.id;
    }

    public void setId(TOrderlistId id) {
	this.id = id;
    }

    public Long getItickettypeid() {
	return this.itickettypeid;
    }

    public void setItickettypeid(Long itickettypeid) {
	this.itickettypeid = itickettypeid;
    }

    public Long getIcrowdkindpriceid() {
	return this.icrowdkindpriceid;
    }

    public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
	this.icrowdkindpriceid = icrowdkindpriceid;
    }

    public Long getIcrowdkindid() {
	return this.icrowdkindid;
    }

    public void setIcrowdkindid(Long icrowdkindid) {
	this.icrowdkindid = icrowdkindid;
    }

    public String getDtstartdate() {
	return this.dtstartdate;
    }

    public void setDtstartdate(String dtstartdate) {
	this.dtstartdate = dtstartdate;
    }

    public String getDtenddate() {
	return this.dtenddate;
    }

    public void setDtenddate(String dtenddate) {
	this.dtenddate = dtenddate;
    }

    public Double getPric() {
	return this.pric;
    }

    public void setPric(Double pric) {
	this.pric = pric;
    }

    public Double getJsprice() {
	return jsprice;
    }

    public void setJsprice(Double jsprice) {
	this.jsprice = jsprice;
    }

    public Long getNumb() {
	return this.numb;
    }

    public void setNumb(Long numb) {
	this.numb = numb;
    }

    public Long getYhnumb() {
	return this.yhnumb;
    }

    public void setYhnumb(Long yhnumb) {
	this.yhnumb = yhnumb;
    }

    public Double getAmnt() {
	return this.amnt;
    }

    public void setAmnt(Double amnt) {
	this.amnt = amnt;
    }

    public Double getYhamnt() {
	return this.yhamnt;
    }

    public void setYhamnt(Double yhamnt) {
	this.yhamnt = yhamnt;
    }

    public Long getIoffersschemeid() {
	return this.ioffersschemeid;
    }

    public void setIoffersschemeid(Long ioffersschemeid) {
	this.ioffersschemeid = ioffersschemeid;
    }

    public Long getIsa() {
	return this.isa;
    }

    public void setIsa(Long isa) {
	this.isa = isa;
    }

    public Long getIsb() {
	return this.isb;
    }

    public void setIsb(Long isb) {
	this.isb = isb;
    }

    public Long getIsc() {
	return this.isc;
    }

    public void setIsc(Long isc) {
	this.isc = isc;
    }

    public Long getIsd() {
	return this.isd;
    }

    public void setIsd(Long isd) {
	this.isd = isd;
    }

    public Long getIse() {
	return this.ise;
    }

    public void setIse(Long ise) {
	this.ise = ise;
    }

    public Long getIsf() {
	return this.isf;
    }

    public void setIsf(Long isf) {
	this.isf = isf;
    }

    public Long getIsg() {
	return this.isg;
    }

    public void setIsg(Long isg) {
	this.isg = isg;
    }

    public Long getIsh() {
	return this.ish;
    }

    public void setIsh(Long ish) {
	this.ish = ish;
    }

    public Long getIsi() {
	return this.isi;
    }

    public void setIsi(Long isi) {
	this.isi = isi;
    }

    public Long getIsj() {
	return this.isj;
    }

    public void setIsj(Long isj) {
	this.isj = isj;
    }

    public String getNotea() {
	return this.notea;
    }

    public void setNotea(String notea) {
	this.notea = notea;
    }

    public String getNotej() {
	return this.notej;
    }

    public void setNotej(String notej) {
	this.notej = notej;
    }

    public String getNotei() {
	return this.notei;
    }

    public void setNotei(String notei) {
	this.notei = notei;
    }

    public String getNoteh() {
	return this.noteh;
    }

    public void setNoteh(String noteh) {
	this.noteh = noteh;
    }

    public String getNoteg() {
	return this.noteg;
    }

    public void setNoteg(String noteg) {
	this.noteg = noteg;
    }

    public String getNotef() {
	return this.notef;
    }

    public void setNotef(String notef) {
	this.notef = notef;
    }

    public String getNotee() {
	return this.notee;
    }

    public void setNotee(String notee) {
	this.notee = notee;
    }

    public String getNoted() {
	return this.noted;
    }

    public void setNoted(String noted) {
	this.noted = noted;
    }

    public String getNotec() {
	return this.notec;
    }

    public void setNotec(String notec) {
	this.notec = notec;
    }

    public String getNoteb() {
	return this.noteb;
    }

    public void setNoteb(String noteb) {
	this.noteb = noteb;
    }

    public String getSztickettypename() {
	return sztickettypename;
    }

    public void setSztickettypename(String sztickettypename) {
	this.sztickettypename = sztickettypename;
    }

    public String getIscenicid() {
	return iscenicid;
    }

    public void setIscenicid(String iscenicid) {
	this.iscenicid = iscenicid;
    }

    public String getOrderlistid() {
	return orderlistid;
    }

    public void setOrderlistid(String orderlistid) {
	this.orderlistid = orderlistid;
    }

    public String getOrid() {
	return orid;
    }

    public void setOrid(String orid) {
	this.orid = orid;
    }

    public Long getNnumb() {
	return nnumb;
    }

    public void setNnumb(Long nnumb) {
	this.nnumb = nnumb;
    }

    public String getWharfname() {
	return wharfname;
    }

    public void setWharfname(String wharfname) {
	this.wharfname = wharfname;
    }

    public String getTripname() {
	return tripname;
    }

    public void setTripname(String tripname) {
	this.tripname = tripname;
    }

    public String getWharftime() {
	return wharftime;
    }

    public void setWharftime(String wharftime) {
	this.wharftime = wharftime;
    }

    public String getWharfdate() {
	return wharfdate;
    }

    public void setWharfdate(String wharfdate) {
	this.wharfdate = wharfdate;
    }

    public String getScenictype() {
	return scenictype;
    }

    public void setScenictype(String scenictype) {
	this.scenictype = scenictype;
    }

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public List<TRealname> getRlist() {
		return rlist;
	}

	public void setRlist(List<TRealname> rlist) {
		this.rlist = rlist;
	}

	public int getHasraft() {
		return hasraft;
	}

	public void setHasraft(int hasraft) {
		this.hasraft = hasraft;
	}

	public Long getPdctrolid() {
		return pdctrolid;
	}

	public void setPdctrolid(Long pdctrolid) {
		this.pdctrolid = pdctrolid;
	}

	public int getBasenum() {
		return basenum;
	}

	public void setBasenum(int basenum) {
		this.basenum = basenum;
	}

	public int getOffernum() {
		return offernum;
	}

	public void setOffernum(int offernum) {
		this.offernum = offernum;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Long getTimeid() {
		return timeid;
	}

	public void setTimeid(Long timeid) {
		this.timeid = timeid;
	}
	
}
