package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;


/**
 * Rbankdaytab entity. @author MyEclipse Persistence Tools
 * �����ձ���
 */

public class Rbankdaytab  implements java.io.Serializable {


    // Fields    

     private Long id;//����id
     private String rdate;//��
     private String rmonth;//��
     private String ryear;//��
     private String times;//����
     private String bank;//���б��
     private String bankname;//��������
     private Double zfmont;//֧�����
     private String szmemo;//��ע
     private Long isa;
     private Long isb;
     private Double dua;
     private Double dub;
     private String notea;
     private String noteb;


    // Constructors

    /** default constructor */
    public Rbankdaytab() {
    }

	/** minimal constructor */
    public Rbankdaytab(Long id, String rdate, String rmonth, String ryear, String times) {
        this.id = id;
        this.rdate = rdate;
        this.rmonth = rmonth;
        this.ryear = ryear;
        this.times = times;
    }
    
    /** full constructor */
    public Rbankdaytab(Long id, String rdate, String rmonth, String ryear, String times, String bank, String bankname, Double zfmont, String szmemo, Long isa, Long isb, Double dua, Double dub, String notea, String noteb) {
        this.id = id;
        this.rdate = rdate;
        this.rmonth = rmonth;
        this.ryear = ryear;
        this.times = times;
        this.bank = bank;
        this.bankname = bankname;
        this.zfmont = zfmont;
        this.szmemo = szmemo;
        this.isa = isa;
        this.isb = isb;
        this.dua = dua;
        this.dub = dub;
        this.notea = notea;
        this.noteb = noteb;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getRdate() {
        return this.rdate;
    }
    
    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRmonth() {
        return this.rmonth;
    }
    
    public void setRmonth(String rmonth) {
        this.rmonth = rmonth;
    }

    public String getRyear() {
        return this.ryear;
    }
    
    public void setRyear(String ryear) {
        this.ryear = ryear;
    }

    public String getTimes() {
        return this.times;
    }
    
    public void setTimes(String times) {
        this.times = times;
    }

    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankname() {
        return this.bankname;
    }
    
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Double getZfmont() {
        return this.zfmont;
    }
    
    public void setZfmont(Double zfmont) {
        this.zfmont = zfmont;
    }

    public String getSzmemo() {
        return this.szmemo;
    }
    
    public void setSzmemo(String szmemo) {
        this.szmemo = szmemo;
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

    public Double getDua() {
        return this.dua;
    }
    
    public void setDua(Double dua) {
        this.dua = dua;
    }

    public Double getDub() {
        return this.dub;
    }
    
    public void setDub(Double dub) {
        this.dub = dub;
    }

    public String getNotea() {
        return this.notea;
    }
    
    public void setNotea(String notea) {
        this.notea = notea;
    }

    public String getNoteb() {
        return this.noteb;
    }
    
    public void setNoteb(String noteb) {
        this.noteb = noteb;
    }
   








}