package com.ectrip.ticket.model.venuemarketing;


/**
 * Tripprdcontroltab entity. @author MyEclipse Persistence Tools
 * 排班总表
 */

public class Tripprdcontroltab  implements java.io.Serializable {


    // Fields

    private Long itripprdcontrolid;//排班ID
    private String szcontrolname;//排班名称
    private Long iprogramid;//节目ID
    private Long ivenueid;//场馆ID
    private String startdata;//开始日期
    private String enddata;//截止日期
    private Long byisuse;//使用状态
    private String dtmakedate;
    private Long int1;
    private Long int2;
    private Double du1;
    private Double du2;
    private String note1;
    private String note2;

    private String szprogramname;
    private String venueidname;
    // Constructors

    /** default constructor */
    public Tripprdcontroltab() {
    }

    /** minimal constructor */
    public Tripprdcontroltab(Long itripprdcontrolid, Long iprogramid, Long ivenueid, String startdata, String enddata, Long byisuse) {
        this.itripprdcontrolid = itripprdcontrolid;
        this.iprogramid = iprogramid;
        this.ivenueid = ivenueid;
        this.startdata = startdata;
        this.enddata = enddata;
        this.byisuse = byisuse;
    }

    /** full constructor */
    public Tripprdcontroltab(Long itripprdcontrolid, Long iprogramid, Long ivenueid, String startdata, String enddata, Long byisuse, String dtmakedate, Long int1, Long int2, Double du1, Double du2, String note1, String note2) {
        this.itripprdcontrolid = itripprdcontrolid;
        this.iprogramid = iprogramid;
        this.ivenueid = ivenueid;
        this.startdata = startdata;
        this.enddata = enddata;
        this.byisuse = byisuse;
        this.dtmakedate = dtmakedate;
        this.int1 = int1;
        this.int2 = int2;
        this.du1 = du1;
        this.du2 = du2;
        this.note1 = note1;
        this.note2 = note2;
    }


    // Property accessors

    public Long getItripprdcontrolid() {
        return this.itripprdcontrolid;
    }

    public void setItripprdcontrolid(Long itripprdcontrolid) {
        this.itripprdcontrolid = itripprdcontrolid;
    }

    public Long getIprogramid() {
        return this.iprogramid;
    }

    public void setIprogramid(Long iprogramid) {
        this.iprogramid = iprogramid;
    }

    public Long getIvenueid() {
        return this.ivenueid;
    }

    public void setIvenueid(Long ivenueid) {
        this.ivenueid = ivenueid;
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

    public Long getByisuse() {
        return this.byisuse;
    }

    public void setByisuse(Long byisuse) {
        this.byisuse = byisuse;
    }

    public String getDtmakedate() {
        return this.dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public Long getInt1() {
        return this.int1;
    }

    public void setInt1(Long int1) {
        this.int1 = int1;
    }

    public Long getInt2() {
        return this.int2;
    }

    public void setInt2(Long int2) {
        this.int2 = int2;
    }

    public Double getDu1() {
        return this.du1;
    }

    public void setDu1(Double du1) {
        this.du1 = du1;
    }

    public Double getDu2() {
        return this.du2;
    }

    public void setDu2(Double du2) {
        this.du2 = du2;
    }

    public String getNote1() {
        return this.note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return this.note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getSzprogramname() {
        return szprogramname;
    }

    public void setSzprogramname(String szprogramname) {
        this.szprogramname = szprogramname;
    }

    public String getVenueidname() {
        return venueidname;
    }

    public void setVenueidname(String venueidname) {
        this.venueidname = venueidname;
    }

    public String getSzcontrolname() {
        return szcontrolname;
    }

    public void setSzcontrolname(String szcontrolname) {
        this.szcontrolname = szcontrolname;
    }

}