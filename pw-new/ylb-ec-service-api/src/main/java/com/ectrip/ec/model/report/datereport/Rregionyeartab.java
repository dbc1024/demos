package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;


/**
 * Rregionyeartab entity. @author MyEclipse Persistence Tools
 * ��Դ���걨��
 */

public class Rregionyeartab  implements java.io.Serializable {


    // Fields    

     private Long id;//����
     private String times;//����
     private Long iregionalid;//��Դ�ر��
     private String szregionalname;//��Դ������
     private Long numb;//����
     private String szmemo;//��ע
     private Long iscenicid;//�����̱��
     private String szscenicname;//����������
     private String ryear;//��
     private Long isa;
     private Long isb;
     private Double dua;
     private Double dub;
     private String notea;
     private String noteb;


    // Constructors

    /** default constructor */
    public Rregionyeartab() {
    }

	/** minimal constructor */
    public Rregionyeartab(Long id, String times, String ryear) {
        this.id = id;
        this.times = times;
        this.ryear = ryear;
    }
    
    /** full constructor */
    public Rregionyeartab(Long id, String times, Long iregionalid, String szregionalname, Long numb, String szmemo, Long iscenicid, String szscenicname, String ryear, Long isa, Long isb, Double dua, Double dub, String notea, String noteb) {
        this.id = id;
        this.times = times;
        this.iregionalid = iregionalid;
        this.szregionalname = szregionalname;
        this.numb = numb;
        this.szmemo = szmemo;
        this.iscenicid = iscenicid;
        this.szscenicname = szscenicname;
        this.ryear = ryear;
        this.isa = isa;
        this.isb = isb;
        this.dua = dua;
        this.dub = dub;
        this.notea = notea;
        this.noteb = noteb;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Long getIregionalid() {
		return iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}

	public String getSzregionalname() {
		return szregionalname;
	}

	public void setSzregionalname(String szregionalname) {
		this.szregionalname = szregionalname;
	}

	public Long getNumb() {
		return numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public String getSzmemo() {
		return szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getRyear() {
		return ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
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

	public Double getDua() {
		return dua;
	}

	public void setDua(Double dua) {
		this.dua = dua;
	}

	public Double getDub() {
		return dub;
	}

	public void setDub(Double dub) {
		this.dub = dub;
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

   
    // Property accessors

    




}