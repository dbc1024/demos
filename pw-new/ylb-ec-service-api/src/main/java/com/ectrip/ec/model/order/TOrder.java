package com.ectrip.ec.model.order;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;

@Entity
@Table(name="T_ORDER")
public class TOrder implements java.io.Serializable{

    // Fields

	@Id
    private TOrderId id;
	@Transient
    private String orid;
    @Transient
    private String iscenicid;
    @Transient
    private String szscenicname;//服务商名
    private String scenictype;//服务商类别 01 景区 06酒店 07 旅行社 08 超市 09 租赁公司
    private String orfl; //定单分类
    private String usid;//游客编号
    private Long ibusinessid;//业务id
    private String sztravelbillno;//行程单号
    private Long iregionalid;//客源地id
    private String tdlx;//团队性质
    private String ddzt;//子订单状态
    private String dtstartdate;//启用日期
    private String dtenddate;//失效日期
    private String dyusid;//导游id
    private String ornm;//领票人姓名
    private String orzj;//领票人证件类别
    private String orhm;//领票人证件号码
    private String orph;//领票人电话
    private String faxno;//传真号
    private Double mont;//订单金额
    private Double yhamnt;//优惠金额
    private Double zfmont;//支付的金额
    private Double youfei;//邮费YOUFEI
    private Long isjfjf;//是否已经计算积分
    private Long ischupiao;//出票锁定状态
    private String fempid;//强制退订员
    private String forcedrefund;//强行退票原因
    private Long isa;
    private Long isb;
    private Long isc;
    private Long isd;
    private Long ise;
    private Long isf;
    private Long isg;
    private Long ish;//消耗积分(年)
    private Long isi;//消耗积分(月)
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
    private Double tpmony;   //退票金额
    private String ornote1;
    private String ornote2;
    private String ornote3;
    private String ornote4;
    private String ornote5;
    private String ornote6;
    private String ornote7;
    private String ornote8;
    private String ornote9;
    private String ornote10;

    @Transient
    private List<TOrderlist> torderlists;
    @Transient
    private YOrder yorder;
    String dtmakedate;
    //非数据库字段
    @Transient
    private String upadder;//封面图片
    @Transient
    private String szaddress;//地址


    public List<TOrderlist> getTorderlists() {
        return torderlists;
    }

    public void setTorderlists(List<TOrderlist> torderlists) {
        this.torderlists = torderlists;
    }

    public YOrder getYorder() {
        return yorder;
    }

    public void setYorder(YOrder yorder) {
        this.yorder = yorder;
    }


    public String getOrnote1() {
        return ornote1;
    }

    public void setOrnote1(String ornote1) {
        this.ornote1 = ornote1;
    }

    public String getOrnote2() {
        return ornote2;
    }

    public void setOrnote2(String ornote2) {
        this.ornote2 = ornote2;
    }

    public String getOrnote3() {
        return ornote3;
    }

    public void setOrnote3(String ornote3) {
        this.ornote3 = ornote3;
    }

    public String getOrnote4() {
        return ornote4;
    }

    public void setOrnote4(String ornote4) {
        this.ornote4 = ornote4;
    }

    public String getOrnote5() {
        return ornote5;
    }

    public void setOrnote5(String ornote5) {
        this.ornote5 = ornote5;
    }

    public String getOrnote6() {
        return ornote6;
    }

    public void setOrnote6(String ornote6) {
        this.ornote6 = ornote6;
    }

    public String getOrnote7() {
        return ornote7;
    }

    public void setOrnote7(String ornote7) {
        this.ornote7 = ornote7;
    }

    public String getOrnote8() {
        return ornote8;
    }

    public void setOrnote8(String ornote8) {
        this.ornote8 = ornote8;
    }

    public String getOrnote9() {
        return ornote9;
    }

    public void setOrnote9(String ornote9) {
        this.ornote9 = ornote9;
    }

    public String getOrnote10() {
        return ornote10;
    }

    public void setOrnote10(String ornote10) {
        this.ornote10 = ornote10;
    }

    //用户名称
    @Transient
    private String orda;
    @Transient
    private String corpname;
    //支付时间
    @Transient
    private String stdt;

    //是否可直接出票
    @Transient
    private String isprint;
    // Constructors
    @Transient
    private boolean caneditday;//是否可修改首次游览日期

    @Transient
    private boolean canedit=false;//是否可修改
    @Transient
    private boolean canback=false;//是否可退
    @Transient
    private boolean canview=false;//是否可查看
    @Transient
    private boolean canBespeak = false;//是否可预约
    /** default constructor */
    public TOrder() {
    }

    /** minimal constructor */
    public TOrder(TOrderId id, String scenictype, String orfl, String usid,
                  Long ibusinessid, String ddzt, String dtstartdate,
                  String dtenddate, String ornm, String orzj, String orhm,
                  Double mont, Double zfmont) {
        this.id = id;
        this.scenictype = scenictype;
        this.orfl = orfl;
        this.usid = usid;
        this.ibusinessid = ibusinessid;
        this.ddzt = ddzt;
        this.dtstartdate = dtstartdate;
        this.dtenddate = dtenddate;
        this.ornm = ornm;
        this.orzj = orzj;
        this.orhm = orhm;
        this.mont = mont;
        this.zfmont = zfmont;
    }

    /** full constructor */
    public TOrder(TOrderId id, String scenictype, String orfl, String usid,
                  Long ibusinessid, String sztravelbillno, Long iregionalid,
                  String tdlx, String ddzt, String dtstartdate, String dtenddate,
                  String dyusid, String ornm, String orzj, String orhm, String orph,
                  String faxno, Double mont, Double yhamnt, Double zfmont,
                  Double youfei, Long isjfjf, Long ischupiao, String fempid,
                  String forcedrefund, Long isa, Long isb, Long isc, Long isd,
                  Long ise, Long isf, Long isg, Long ish, Long isi, Long isj,
                  String notea, String notej, String notei, String noteh,
                  String noteg, String notef, String notee, String noted,
                  String notec, String noteb, Set TOrderlists) {
        this.id = id;
        this.scenictype = scenictype;
        this.orfl = orfl;
        this.usid = usid;
        this.ibusinessid = ibusinessid;
        this.sztravelbillno = sztravelbillno;
        this.iregionalid = iregionalid;
        this.tdlx = tdlx;
        this.ddzt = ddzt;
        this.dtstartdate = dtstartdate;
        this.dtenddate = dtenddate;
        this.dyusid = dyusid;
        this.ornm = ornm;
        this.orzj = orzj;
        this.orhm = orhm;
        this.orph = orph;
        this.faxno = faxno;
        this.mont = mont;
        this.yhamnt = yhamnt;
        this.zfmont = zfmont;
        this.youfei = youfei;
        this.isjfjf = isjfjf;
        this.ischupiao = ischupiao;
        this.fempid = fempid;
        this.forcedrefund = forcedrefund;
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

    public TOrderId getId() {
        return this.id;
    }

    public void setId(TOrderId id) {
        this.id = id;
    }

    public String getScenictype() {
        return this.scenictype;
    }

    public void setScenictype(String scenictype) {
        this.scenictype = scenictype;
    }

    public String getOrfl() {
        return this.orfl;
    }

    public void setOrfl(String orfl) {
        this.orfl = orfl;
    }

    public String getUsid() {
        return this.usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Long getIbusinessid() {
        return this.ibusinessid;
    }

    public void setIbusinessid(Long ibusinessid) {
        this.ibusinessid = ibusinessid;
    }

    public String getSztravelbillno() {
        return this.sztravelbillno;
    }

    public void setSztravelbillno(String sztravelbillno) {
        this.sztravelbillno = sztravelbillno;
    }

    public Long getIregionalid() {
        return this.iregionalid;
    }

    public void setIregionalid(Long iregionalid) {
        this.iregionalid = iregionalid;
    }

    public String getTdlx() {
        return this.tdlx;
    }

    public void setTdlx(String tdlx) {
        this.tdlx = tdlx;
    }

    public String getDdzt() {
        return this.ddzt;
    }

    public void setDdzt(String ddzt) {
        this.ddzt = ddzt;
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

    public String getDyusid() {
        return this.dyusid;
    }

    public void setDyusid(String dyusid) {
        this.dyusid = dyusid;
    }

    public String getOrnm() {
        return this.ornm;
    }

    public void setOrnm(String ornm) {
        this.ornm = ornm;
    }

    public String getOrzj() {
        return this.orzj;
    }

    public void setOrzj(String orzj) {
        this.orzj = orzj;
    }

    public String getOrhm() {
        return this.orhm;
    }

    public void setOrhm(String orhm) {
        this.orhm = orhm;
    }

    public String getOrph() {
        return this.orph;
    }

    public void setOrph(String orph) {
        this.orph = orph;
    }

    public String getFaxno() {
        return this.faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    public Double getMont() {
        return this.mont;
    }

    public void setMont(Double mont) {
        this.mont = mont;
    }

    public Double getYhamnt() {
        return this.yhamnt;
    }

    public void setYhamnt(Double yhamnt) {
        this.yhamnt = yhamnt;
    }

    public Double getZfmont() {
        return this.zfmont;
    }

    public void setZfmont(Double zfmont) {
        this.zfmont = zfmont;
    }

    public Double getYoufei() {
        return this.youfei;
    }

    public void setYoufei(Double youfei) {
        this.youfei = youfei;
    }

    public Long getIsjfjf() {
        return this.isjfjf;
    }

    public void setIsjfjf(Long isjfjf) {
        this.isjfjf = isjfjf;
    }

    public Long getIschupiao() {
        return this.ischupiao;
    }

    public void setIschupiao(Long ischupiao) {
        this.ischupiao = ischupiao;
    }

    public String getFempid() {
        return this.fempid;
    }

    public void setFempid(String fempid) {
        this.fempid = fempid;
    }

    public String getForcedrefund() {
        return this.forcedrefund;
    }

    public void setForcedrefund(String forcedrefund) {
        this.forcedrefund = forcedrefund;
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

    public String getSzscenicname() {
        return szscenicname;
    }

    public void setSzscenicname(String szscenicname) {
        this.szscenicname = szscenicname;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getIscenicid() {
        return iscenicid;
    }

    public void setIscenicid(String iscenicid) {
        this.iscenicid = iscenicid;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getStdt() {
        return stdt;
    }

    public void setStdt(String stdt) {
        this.stdt = stdt;
    }

    public String getOrda() {
        return orda;
    }

    public void setOrda(String orda) {
        this.orda = orda;
    }

    public boolean isCaneditday() {
        return caneditday;
    }

    public void setCaneditday(boolean caneditday) {
        this.caneditday = caneditday;
    }

    public Double getTpmony() {
        return tpmony;
    }

    public void setTpmony(Double tpmony) {
        this.tpmony = tpmony;
    }

    public String getIsprint() {
        return isprint;
    }

    public void setIsprint(String isprint) {
        this.isprint = isprint;
    }

    public boolean isCanedit() {
        return canedit;
    }

    public void setCanedit(boolean canedit) {
        this.canedit = canedit;
    }

    public boolean isCanback() {
        return canback;
    }

    public void setCanback(boolean canback) {
        this.canback = canback;
    }

    public boolean isCanview() {
        return canview;
    }

    public void setCanview(boolean canview) {
        this.canview = canview;
    }

    public boolean isCanBespeak() {
        return canBespeak;
    }

    public void setCanBespeak(boolean canBespeak) {
        this.canBespeak = canBespeak;
    }

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public String getUpadder() {
        return upadder;
    }

    public void setUpadder(String upadder) {
        this.upadder = upadder;
    }

    public String getSzaddress() {
        return szaddress;
    }

    public void setSzaddress(String szaddress) {
        this.szaddress = szaddress;
    }

    @Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
