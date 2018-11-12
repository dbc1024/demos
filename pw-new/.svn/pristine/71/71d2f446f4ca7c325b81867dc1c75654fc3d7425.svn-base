package com.ectrip.sys.model.syspar;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mbmessage entity. @author MyEclipse Persistence Tools
 * 短信发送表
 */

@Entity
@Table
public class Mbmessage implements java.io.Serializable {

    // Fields

	@Id
    private Long seq;//流水号
    private String revmbno;//手机号
    private String note;//短信内容
    private Integer isok;//是否发送成功
    private String dtime;//短信生成时间
    private String stime;//短信发送时间
    private Integer quantity;//发送次数
    private String twobarcode;//二维护码
    private String ip;//IP地址
    private String type;//短信类型

    // Constructors

    /** default constructor */
    public Mbmessage() {
    }

    /** minimal constructor */
    public Mbmessage(Long seq, String revmbno, String note,
                     Integer isok, String dtime, String stime, Integer quantity) {
        this.seq = seq;
        this.revmbno = revmbno;
        this.note = note;
        this.isok = isok;
        this.dtime = dtime;
        this.stime = stime;
        this.quantity = quantity;
    }

    /** full constructor */
    public Mbmessage(Long seq, String revmbno, String note,
                     Integer isok, String dtime, String stime, Integer quantity,
                     String twobarcode) {
        this.seq = seq;
        this.revmbno = revmbno;
        this.note = note;
        this.isok = isok;
        this.dtime = dtime;
        this.stime = stime;
        this.quantity = quantity;
        this.twobarcode = twobarcode;
    }

    public Mbmessage(Long seq, String revmbno, String note, Integer isok, String dtime, String stime, Integer quantity,
                     String twobarcode, String ip, String type) {
        this.seq = seq;
        this.revmbno = revmbno;
        this.note = note;
        this.isok = isok;
        this.dtime = dtime;
        this.stime = stime;
        this.quantity = quantity;
        this.twobarcode = twobarcode;
        this.ip = ip;
        this.type = type;
    }

    // Property accessors

    public Long getSeq() {
        return this.seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getRevmbno() {
        return this.revmbno;
    }

    public void setRevmbno(String revmbno) {
        this.revmbno = revmbno;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsok() {
        return this.isok;
    }

    public void setIsok(Integer isok) {
        this.isok = isok;
    }

    public String getDtime() {
        return this.dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getStime() {
        return this.stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTwobarcode() {
        return this.twobarcode;
    }

    public void setTwobarcode(String twobarcode) {
        this.twobarcode = twobarcode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}