package com.ectrip.ec.model.balance;

import java.math.BigDecimal;


/**
 * Jszx entity. @author MyEclipse Persistence Tools
 */

public class Jszx  implements java.io.Serializable {


    // Fields

    private Long seq;
    private String notifyurl;
    private String usid;
    private String orid;
    private Double amount;
    private String goodsname;
    private Integer goodsquantity;
    private String subject;
    private Integer paymenttype;
    private String key;
    private String signtype;
    private String inputcharset;
    private String ortime;
    private String partner;
    private String systime;
    private String payid;
    private String bankid;
    private String jsfs;
    private int isok;
    private Integer isreturn;
    private String note5;
    private String note4;
    private String note3;
    private String note;
    private String note2;
    private Double amount2;
    private Double amount3;
    private Long iint3;
    private Long iint2;
    private Long iint1;
    private Long iint0;


    //非数据库字段
    private String ddzt;
    private String isokstr;
    private String corpaname;//预付员用户名
    private String strzfusid;//支付员用户名


    // Constructors

    /** default constructor */
    public Jszx() {
    }

    /** minimal constructor */
    public Jszx(Long seq, String notifyurl, String usid, String orid, Double amount,
                String goodsname, Integer goodsquantity, String subject,
                Integer paymenttype, String partner, String systime, Double amount2, Double amount3) {
        this.seq = seq;
        this.notifyurl = notifyurl;
        this.usid = usid;
        this.orid = orid;
        this.amount = amount;
        this.goodsname = goodsname;
        this.goodsquantity = goodsquantity;
        this.subject = subject;
        this.paymenttype = paymenttype;
        this.partner = partner;
        this.systime = systime;
        this.amount2 = amount2;
        this.amount3 = amount3;
    }

    /** full constructor */
    public Jszx(Long seq, String notifyurl, String usid, String orid, Double amount,
                String goodsname, Integer goodsquantity, String subject, Integer paymenttype,
                String key, String signtype, String inputcharset, String ortime, String partner,
                String systime, String payid, String bankid, String jsfs, int isok,
                Integer isreturn, String note5, String note4, String note3, String note,
                String note2, Double amount2, Double amount3, Long iint3, Long iint2,
                Long iint1, Long iint0) {
        this.seq = seq;
        this.notifyurl = notifyurl;
        this.usid = usid;
        this.orid = orid;
        this.amount = amount;
        this.goodsname = goodsname;
        this.goodsquantity = goodsquantity;
        this.subject = subject;
        this.paymenttype = paymenttype;
        this.key = key;
        this.signtype = signtype;
        this.inputcharset = inputcharset;
        this.ortime = ortime;
        this.partner = partner;
        this.systime = systime;
        this.payid = payid;
        this.bankid = bankid;
        this.jsfs = jsfs;
        this.isok = isok;
        this.isreturn = isreturn;
        this.note5 = note5;
        this.note4 = note4;
        this.note3 = note3;
        this.note = note;
        this.note2 = note2;
        this.amount2 = amount2;
        this.amount3 = amount3;
        this.iint3 = iint3;
        this.iint2 = iint2;
        this.iint1 = iint1;
        this.iint0 = iint0;
    }


    // Property accessors

    public Long getSeq() {
        return this.seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getNotifyurl() {
        return this.notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }

    public String getUsid() {
        return this.usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getOrid() {
        return this.orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGoodsname() {
        return this.goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getGoodsquantity() {
        return this.goodsquantity;
    }

    public void setGoodsquantity(Integer goodsquantity) {
        this.goodsquantity = goodsquantity;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getPaymenttype() {
        return this.paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSigntype() {
        return this.signtype;
    }

    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }

    public String getInputcharset() {
        return this.inputcharset;
    }

    public void setInputcharset(String inputcharset) {
        this.inputcharset = inputcharset;
    }

    public String getOrtime() {
        return this.ortime;
    }

    public void setOrtime(String ortime) {
        this.ortime = ortime;
    }

    public String getPartner() {
        return this.partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSystime() {
        return this.systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public String getPayid() {
        return this.payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getBankid() {
        return this.bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getJsfs() {
        return this.jsfs;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public int getIsok() {
        return this.isok;
    }

    public void setIsok(int isok) {
        this.isok = isok;
    }

    public Integer getIsreturn() {
        return this.isreturn;
    }

    public void setIsreturn(Integer isreturn) {
        this.isreturn = isreturn;
    }

    public String getNote5() {
        return this.note5;
    }

    public void setNote5(String note5) {
        this.note5 = note5;
    }

    public String getNote4() {
        return this.note4;
    }

    public void setNote4(String note4) {
        this.note4 = note4;
    }

    public String getNote3() {
        return this.note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote2() {
        return this.note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public Double getAmount2() {
        return this.amount2;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getAmount3() {
        return this.amount3;
    }

    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }

    public Long getIint3() {
        return this.iint3;
    }

    public void setIint3(Long iint3) {
        this.iint3 = iint3;
    }

    public Long getIint2() {
        return this.iint2;
    }

    public void setIint2(Long iint2) {
        this.iint2 = iint2;
    }

    public Long getIint1() {
        return this.iint1;
    }

    public void setIint1(Long iint1) {
        this.iint1 = iint1;
    }

    public Long getIint0() {
        return this.iint0;
    }

    public void setIint0(Long iint0) {
        this.iint0 = iint0;
    }

    public String getDdzt() {
        return ddzt;
    }

    public void setDdzt(String ddzt) {
        this.ddzt = ddzt;
    }

    public String getIsokstr() {
        return isokstr;
    }

    public void setIsokstr(String isokstr) {
        this.isokstr = isokstr;
    }

    public String getCorpaname() {
        return corpaname;
    }

    public void setCorpaname(String corpaname) {
        this.corpaname = corpaname;
    }

    public String getStrzfusid() {
        return strzfusid;
    }

    public void setStrzfusid(String strzfusid) {
        this.strzfusid = strzfusid;
    }











}