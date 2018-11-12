package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;


/**
 * Rbankyeartab entity. @author MyEclipse Persistence Tools
 * �����걨��
 */

public class Rbankyeartab  implements java.io.Serializable {


    // Fields    

     private Long id;//����
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
    public Rbankyeartab() {
    }

	/** minimal constructor */
    public Rbankyeartab(Long id, String ryear, String times) {
        this.id = id;
        this.ryear = ryear;
        this.times = times;
    }
    
    /** full constructor */
    public Rbankyeartab(Long id, String ryear, String times, String bank, String bankname, Double zfmont, String szmemo, Long isa, Long isb, Double dua, Double dub, String notea, String noteb) {
        this.id = id;
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