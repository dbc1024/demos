package com.ectrip.ticket.model.applyorder;

/**
 * Ordertickettourist entity. @author MyEclipse Persistence Tools
 * 申报产品关联游客信息表
 */

public class Ordertickettourist  implements java.io.Serializable {


    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1887715818734149932L;

    private Long ottseq;
    private String aorid;//订单号
    private Long orderlistid;//产品明细ID
    private Long iscenicid;//服务商ID
    private Long itickettypeid;//产品ID
    private Long icrowdkindid;//人群ID
    private Long tiseq;//游客信息ＩＤ
    private Long notea;//1、已稽核
    private Long noteb;
    private String noteh;
    private String notei;
    private String notej;
    private String dtmakedate;
    private String lname;//姓名
    private String orzj;//证件类型
    private String zjhm;//证件号码
    private String mobile;//手机号
    private Long status;//状态  0:未使用 -1:已退订 1:已出票

    // Constructors

    /** default constructor */
    public Ordertickettourist() {
    }

    /** minimal constructor */
    public Ordertickettourist(String aorid, Long orderlistid, Long iscenicid, Long itickettypeid,Long icrowdkindid) {
        this.aorid = aorid;
        this.orderlistid = orderlistid;
        this.iscenicid = iscenicid;
        this.itickettypeid = itickettypeid;
        this.icrowdkindid = icrowdkindid;
    }

    /** full constructor */
    public Ordertickettourist(String aorid, Long orderlistid, Long iscenicid, Long itickettypeid, Long icrowdkindid, Long tiseq, Long notea, Long noteb, String noteh, String notei, String notej, String dtmakedate) {
        this.aorid = aorid;
        this.orderlistid = orderlistid;
        this.iscenicid = iscenicid;
        this.itickettypeid = itickettypeid;
        this.icrowdkindid = icrowdkindid;
        this.tiseq = tiseq;
        this.notea = notea;
        this.noteb = noteb;
        this.noteh = noteh;
        this.notei = notei;
        this.notej = notej;
        this.dtmakedate = dtmakedate;
    }


    // Property accessors

    public Long getOttseq() {
        return this.ottseq;
    }

    public void setOttseq(Long ottseq) {
        this.ottseq = ottseq;
    }

    public String getAorid() {
        return this.aorid;
    }

    public void setAorid(String aorid) {
        this.aorid = aorid;
    }

    public Long getOrderlistid() {
        return this.orderlistid;
    }

    public void setOrderlistid(Long orderlistid) {
        this.orderlistid = orderlistid;
    }

    public Long getIscenicid() {
        return this.iscenicid;
    }

    public void setIscenicid(Long iscenicid) {
        this.iscenicid = iscenicid;
    }

    public Long getItickettypeid() {
        return this.itickettypeid;
    }

    public void setItickettypeid(Long itickettypeid) {
        this.itickettypeid = itickettypeid;
    }

    public Long getIcrowdkindid() {
        return this.icrowdkindid;
    }

    public void setIcrowdkindid(Long icrowdkindid) {
        this.icrowdkindid = icrowdkindid;
    }

    public Long getTiseq() {
        return this.tiseq;
    }

    public void setTiseq(Long tiseq) {
        this.tiseq = tiseq;
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

    public String getDtmakedate() {
        return this.dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getOrzj() {
        return orzj;
    }

    public void setOrzj(String orzj) {
        this.orzj = orzj;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}