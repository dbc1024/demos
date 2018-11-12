package com.ectrip.ticket.model.applyorder;

import java.util.List;


/**
 * Applymorder entity. @author MyEclipse Persistence Tools
 * 申报订单主表  保存团队类型
 */

public class Applymorder  implements java.io.Serializable {


    // Fields

    /**
     *
     */
    private static final long serialVersionUID = -8976026914025642179L;
    private String aorid;//申报流水号
    private String stdt;//首次消费日期
    private String usid;//用户ID
    private String orda;//申报日期
    private String orti;//申报时间
    private String ddzt;//申报状态
    private String zffs;//支付方式
    private String impfs;//选择导入方式 '01':'Excel导入','02':'选择已录入信息'
    private String note;//备注
    private String teamno;//团队编号
    private String shnote;//审核意见
    private String carno;//车辆号
    private String dtmakedate;//创建时间
    private String bznote;//标识
    private String notef;//审核时间
    private String noteg;//上船码头名字
    private String noteh;//下船码头名字
    private String notei;//上船时间
    private String notej;//下船时间
    private String shipname;//船名
    private Double mont;//总金额
    private Double yhamnt;//优惠金额
    private Double zfmont;//支付金额
    private Long siemployeeid;//审核人
    private Long jiemployeeid;//稽核人
    private Long iregionalid;//所属营销区域
    private Long szregionalid;//客源地
    private Long applynumber;//申报人数
    private Long teamtype;//团队类型
    private Long needmd;//需要名单  1：需要  0： 否
    private Long needcar;//需要名单 : 1 : 需要 ，0： 否
    private Long notea;//是否包船： 1、需要，0：否
    private Long noteb;//是否导游要客服下的增单子 1：是
    private Long notec;//
    private Long noted;//
    private Long notee;//

    private Double point;//用户余额

    /**以下字段属性非数据库*/
    private Long tuanduiId;//上传xls文件用
    private String tuanduiadder;//上传xls文件用
    private String tuanduiname;//上传xls文件用
    private String pmva;//状态值
    private String szscenicname;//服务商名称
    private String md5bznote;//隐藏校验标识
    private String impsrc;//hql条件时候in用 01:1 、  02：2,3 (手机或者网页录入)
    private String szemployeename;//审核用户名
    private String igionalname;//营销区域名称
    private String szregionalname;//客源地名称
    private String teamtypename;//团类型名称
    private String zffsname;//支付方式名称
    private String auth;//权限
    private Long yksbznote;//导入或选择的名单游客
    private Long shywyks;//实际去景区游玩游客 (一个游客去多个点的算一个)
    private List<String> isneedshmsg;//需要人工审核原因
    private String carnos;//车辆id集合
    private String noteihour;//上船小时
    private String notejhour;//下船小时
    private String noteifen;//上船分
    private String notejfen;//下船分
    private Long ise;//记录出票时间限制

    private boolean isNETcP=false;
    // Constructors

    /** default constructor */
    public Applymorder() {
    }

    /** minimal constructor */
    public Applymorder(String aorid, String usid, String orda, String orti, Long applynumber, String ddzt, String zffs, String carno, String dtmakedate,String shipname) {
        this.aorid = aorid;
        this.usid = usid;
        this.orda = orda;
        this.orti = orti;
        this.applynumber = applynumber;
        this.ddzt = ddzt;
        this.zffs = zffs;
        this.carno = carno;
        this.dtmakedate = dtmakedate;
        this.shipname=shipname;
    }

    /** full constructor */
    public Applymorder(String aorid,String stdt ,Long teamtype, String usid, String orda, String orti, Long applynumber, Double mont, Double yhamnt, Double zfmont, String ddzt, String zffs, String note, String teamno, String shnote, Long iregionalid, String carno, String dtmakedate, String bznote, Long notea, Long noteb, Long notec, Long noted, Long notee, String notef, String noteg, String noteh, String notei, String notej,Double point,String shipname) {
        this.aorid = aorid;
        this.stdt = stdt;
        this.teamtype = teamtype;
        this.usid = usid;
        this.orda = orda;
        this.orti = orti;
        this.applynumber = applynumber;
        this.mont = mont;
        this.yhamnt = yhamnt;
        this.zfmont = zfmont;
        this.ddzt = ddzt;
        this.zffs = zffs;
        this.note = note;
        this.teamno = teamno;
        this.shnote = shnote;
        this.iregionalid = iregionalid;
        this.carno = carno;
        this.dtmakedate = dtmakedate;
        this.bznote = bznote;
        this.notea = notea;
        this.noteb = noteb;
        this.notec = notec;
        this.noted = noted;
        this.notee = notee;
        this.notef = notef;
        this.noteg = noteg;
        this.noteh = noteh;
        this.notei = notei;
        this.notej = notej;
        this.point=point;
        this.shipname=shipname;
    }


    // Property accessors

    public String getAorid() {
        return this.aorid;
    }

    public void setAorid(String aorid) {
        this.aorid = aorid;
    }

    public String getStdt() {
        return stdt;
    }

    public void setStdt(String stdt) {
        this.stdt = stdt;
    }

    public Long getTeamtype() {
        return this.teamtype;
    }

    public void setTeamtype(Long teamtype) {
        this.teamtype = teamtype;
    }

    public String getUsid() {
        return this.usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getOrda() {
        return this.orda;
    }

    public void setOrda(String orda) {
        this.orda = orda;
    }

    public String getOrti() {
        return this.orti;
    }

    public void setOrti(String orti) {
        this.orti = orti;
    }

    public Long getApplynumber() {
        return this.applynumber;
    }

    public void setApplynumber(Long applynumber) {
        this.applynumber = applynumber;
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

    public String getDdzt() {
        return this.ddzt;
    }

    public void setDdzt(String ddzt) {
        this.ddzt = ddzt;
    }

    public String getZffs() {
        return this.zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTeamno() {
        return this.teamno;
    }

    public void setTeamno(String teamno) {
        this.teamno = teamno;
    }

    public String getShnote() {
        return this.shnote;
    }

    public void setShnote(String shnote) {
        this.shnote = shnote;
    }

    public Long getIregionalid() {
        return this.iregionalid;
    }

    public void setIregionalid(Long iregionalid) {
        this.iregionalid = iregionalid;
    }

    public String getCarno() {
        return this.carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getDtmakedate() {
        return this.dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public String getBznote() {
        return this.bznote;
    }

    public void setBznote(String bznote) {
        this.bznote = bznote;
    }

    public Long getNotea() {
        return this.notea;
    }

    public void setNotea(Long notea) {
        this.notea = notea;
    }

    public Long getNoteb() {
        return this.noteb;
    }

    public void setNoteb(Long noteb) {
        this.noteb = noteb;
    }

    public Long getNotec() {
        return this.notec;
    }

    public void setNotec(Long notec) {
        this.notec = notec;
    }

    public Long getNoted() {
        return this.noted;
    }

    public void setNoted(Long noted) {
        this.noted = noted;
    }

    public Long getNotee() {
        return this.notee;
    }

    public void setNotee(Long notee) {
        this.notee = notee;
    }

    public String getNotef() {
        return this.notef;
    }

    public void setNotef(String notef) {
        this.notef = notef;
    }

    public String getNoteg() {
        return this.noteg;
    }

    public void setNoteg(String noteg) {
        this.noteg = noteg;
    }

    public String getNoteh() {
        return this.noteh;
    }

    public void setNoteh(String noteh) {
        this.noteh = noteh;
    }

    public String getNotei() {
        return this.notei;
    }

    public void setNotei(String notei) {
        this.notei = notei;
    }

    public String getNotej() {
        return this.notej;
    }

    public void setNotej(String notej) {
        this.notej = notej;
    }

    public Long getTuanduiId() {
        return tuanduiId;
    }

    public void setTuanduiId(Long tuanduiId) {
        this.tuanduiId = tuanduiId;
    }

    public String getTuanduiadder() {
        return tuanduiadder;
    }

    public void setTuanduiadder(String tuanduiadder) {
        this.tuanduiadder = tuanduiadder;
    }

    public String getTuanduiname() {
        return tuanduiname;
    }

    public void setTuanduiname(String tuanduiname) {
        this.tuanduiname = tuanduiname;
    }

    public Long getSiemployeeid() {
        return siemployeeid;
    }

    public void setSiemployeeid(Long siemployeeid) {
        this.siemployeeid = siemployeeid;
    }

    public Long getJiemployeeid() {
        return jiemployeeid;
    }

    public void setJiemployeeid(Long jiemployeeid) {
        this.jiemployeeid = jiemployeeid;
    }

    public String getPmva() {
        return pmva;
    }

    public void setPmva(String pmva) {
        this.pmva = pmva;
    }

    public String getSzscenicname() {
        return szscenicname;
    }

    public void setSzscenicname(String szscenicname) {
        this.szscenicname = szscenicname;
    }

    public String getMd5bznote() {
        return md5bznote;
    }

    public void setMd5bznote(String md5bznote) {
        this.md5bznote = md5bznote;
    }

    public String getImpfs() {
        return impfs;
    }

    public void setImpfs(String impfs) {
        this.impfs = impfs;
    }

    public String getSzemployeename() {
        return szemployeename;
    }

    public void setSzemployeename(String szemployeename) {
        this.szemployeename = szemployeename;
    }

    public String getSzregionalname() {
        return szregionalname;
    }

    public void setSzregionalname(String szregionalname) {
        this.szregionalname = szregionalname;
    }

    public String getTeamtypename() {
        return teamtypename;
    }

    public void setTeamtypename(String teamtypename) {
        this.teamtypename = teamtypename;
    }

    public String getZffsname() {
        return zffsname;
    }

    public void setZffsname(String zffsname) {
        this.zffsname = zffsname;
    }

    public Long getSzregionalid() {
        return szregionalid;
    }

    public void setSzregionalid(Long szregionalid) {
        this.szregionalid = szregionalid;
    }

    public Long getYksbznote() {
        return yksbznote;
    }

    public void setYksbznote(Long yksbznote) {
        this.yksbznote = yksbznote;
    }

    public Long getShywyks() {
        return shywyks;
    }

    public void setShywyks(Long shywyks) {
        this.shywyks = shywyks;
    }

    public Long getNeedmd() {
        return needmd;
    }

    public void setNeedmd(Long needmd) {
        this.needmd = needmd;
    }

    public Long getNeedcar() {
        return needcar;
    }

    public void setNeedcar(Long needcar) {
        this.needcar = needcar;
    }

    public String getImpsrc() {
        if("01".equals(getImpfs())){
            this.impsrc = "1";  //excel导入
        }else if("02".equals(getImpfs())){
            this.impsrc = "2,3";
        }
        return impsrc;
    }

    public void setImpsrc(String impsrc) {
        this.impsrc = impsrc;
    }

    public List<String> getIsneedshmsg() {
        return isneedshmsg;
    }

    public void setIsneedshmsg(List<String> isneedshmsg) {
        this.isneedshmsg = isneedshmsg;
    }

    public String getIgionalname() {
        return igionalname;
    }

    public void setIgionalname(String igionalname) {
        this.igionalname = igionalname;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getCarnos() {
        return carnos;
    }

    public void setCarnos(String carnos) {
        this.carnos = carnos;
    }

    public String getNoteihour() {
        return noteihour;
    }

    public void setNoteihour(String noteihour) {
        this.noteihour = noteihour;
    }

    public String getNotejhour() {
        return notejhour;
    }

    public void setNotejhour(String notejhour) {
        this.notejhour = notejhour;
    }

    public String getNoteifen() {
        return noteifen;
    }

    public void setNoteifen(String noteifen) {
        this.noteifen = noteifen;
    }

    public String getNotejfen() {
        return notejfen;
    }

    public void setNotejfen(String notejfen) {
        this.notejfen = notejfen;
    }

    public Long getIse() {
        return ise;
    }

    public void setIse(Long ise) {
        this.ise = ise;
    }

    public boolean isNETcP() {
        return isNETcP;
    }

    public void setNETcP(boolean isNETcP) {
        this.isNETcP = isNETcP;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

}