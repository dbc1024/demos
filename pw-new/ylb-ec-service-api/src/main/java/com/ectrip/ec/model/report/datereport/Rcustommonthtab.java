package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;


/**
 * Rcustommonthtab entity. @author MyEclipse Persistence Tools
 * �ο��±���
 */

public class Rcustommonthtab  implements java.io.Serializable {


    // Fields    

     private Long id;//����
     private String times;//����
     private String usid;//�û����
     private String szname;//�û�����
     private Long numb;//����
     private String szmemo;//��ע
     private String rmonth;//��
     private String ryear;//��
     private Long iscenicid;//�����̱��
     private String szscenicname;//����������
     private Long isa;
     private Long isb;
     private Double dua;
     private Double dub;
     private String notea;
     private String noteb;


    // Constructors

    /** default constructor */
    public Rcustommonthtab() {
    }

	/** minimal constructor */
    public Rcustommonthtab(Long id, String times, String rmonth, String ryear) {
        this.id = id;
        this.times = times;
        this.rmonth = rmonth;
        this.ryear = ryear;
    }

	public Rcustommonthtab(Long id, String times, String usid, String szname,
			Long numb, String szmemo, String rmonth, String ryear,
			Long iscenicid, String szscenicname, Long isa, Long isb,
			Double dua, Double dub, String notea, String noteb) {
		super();
		this.id = id;
		this.times = times;
		this.usid = usid;
		this.szname = szname;
		this.numb = numb;
		this.szmemo = szmemo;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
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

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getSzname() {
		return szname;
	}

	public void setSzname(String szname) {
		this.szname = szname;
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

	public String getRmonth() {
		return rmonth;
	}

	public void setRmonth(String rmonth) {
		this.rmonth = rmonth;
	}

	public String getRyear() {
		return ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
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
    
   







}