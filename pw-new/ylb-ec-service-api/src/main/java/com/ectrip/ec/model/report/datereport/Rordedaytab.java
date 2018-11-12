package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;


/**
 * Rordedaytab entity. @author MyEclipse Persistence Tools
 *�����걨��
 */

public class Rordedaytab  implements java.io.Serializable {


    // Fields    

     private Long id;//����
     private String orid;//�û����
     private String rdate;//��
     private String rmonth;//��
     private String ryear;//��
     private String times;//����
     private Double mont;//�ܽ��
     private Double zfmont;//֧�����
     private Double yhmont;//�Żݽ��
     private String szmemo;//��ע
     private Long numb;//����
     private Long isa;//�����ֶ�
     private Long isb;
     private Long isc;
     	private Long isd;
     	private Long ise;
     	private Long isf;
     	private Double dua;
     	private Double dub;
     	private Double duc;
     	private Double dud;
     	private Double due;
     	private Double duf;
     	private String notea;
     	private String noteb;
     	private String notec;
     	private String noted;
     	private String notee;
     	private String notef;


    // Constructors

    /** default constructor */
    public Rordedaytab() {
    }

	/** minimal constructor */
    public Rordedaytab(Long id, String rdate, String rmonth, String ryear, String times) {
        this.id = id;
        this.rdate = rdate;
        this.rmonth = rmonth;
        this.ryear = ryear;
        this.times = times;
    }
    
    /** full constructor */
   
   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public Rordedaytab(Long id, String orid, String rdate, String rmonth,
			String ryear, String times, Double mont, Double zfmont,
			Double yhmont, String szmemo, Long numb, Long isa, Long isb,
			Long isc, Long isd, Long ise, Long isf, Double dua, Double dub,
			Double duc, Double dud, Double due, Double duf, String notea,
			String noteb, String notec, String noted, String notee, String notef) {
		super();
		this.id = id;
		this.orid = orid;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.mont = mont;
		this.zfmont = zfmont;
		this.yhmont = yhmont;
		this.szmemo = szmemo;
		this.numb = numb;
		this.isa = isa;
		this.isb = isb;
		this.isc = isc;
		this.isd = isd;
		this.ise = ise;
		this.isf = isf;
		this.dua = dua;
		this.dub = dub;
		this.duc = duc;
		this.dud = dud;
		this.due = due;
		this.duf = duf;
		this.notea = notea;
		this.noteb = noteb;
		this.notec = notec;
		this.noted = noted;
		this.notee = notee;
		this.notef = notef;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getOrid() {
        return this.orid;
    }
    
    public void setOrid(String orid) {
        this.orid = orid;
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

    public Double getMont() {
        return this.mont;
    }
    
    public void setMont(Double mont) {
        this.mont = mont;
    }

    public Double getZfmont() {
        return this.zfmont;
    }
    
    public void setZfmont(Double zfmont) {
        this.zfmont = zfmont;
    }

    public Double getYhmont() {
        return this.yhmont;
    }
    
    public void setYhmont(Double yhmont) {
        this.yhmont = yhmont;
    }

    public String getSzmemo() {
        return this.szmemo;
    }
    
    public void setSzmemo(String szmemo) {
        this.szmemo = szmemo;
    }

    public Long getNumb() {
        return this.numb;
    }
    
    public void setNumb(Long numb) {
        this.numb = numb;
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

	public Long getIsc() {
		return isc;
	}

	public void setIsc(Long isc) {
		this.isc = isc;
	}

	public Long getIsd() {
		return isd;
	}

	public void setIsd(Long isd) {
		this.isd = isd;
	}

	public Long getIse() {
		return ise;
	}

	public void setIse(Long ise) {
		this.ise = ise;
	}

	public Long getIsf() {
		return isf;
	}

	public void setIsf(Long isf) {
		this.isf = isf;
	}

	public Double getDuc() {
		return duc;
	}

	public void setDuc(Double duc) {
		this.duc = duc;
	}

	public Double getDud() {
		return dud;
	}

	public void setDud(Double dud) {
		this.dud = dud;
	}

	public Double getDue() {
		return due;
	}

	public void setDue(Double due) {
		this.due = due;
	}

	public Double getDuf() {
		return duf;
	}

	public void setDuf(Double duf) {
		this.duf = duf;
	}

	public String getNotec() {
		return notec;
	}

	public void setNotec(String notec) {
		this.notec = notec;
	}

	public String getNoted() {
		return noted;
	}

	public void setNoted(String noted) {
		this.noted = noted;
	}

	public String getNotee() {
		return notee;
	}

	public void setNotee(String notee) {
		this.notee = notee;
	}

	public String getNotef() {
		return notef;
	}

	public void setNotef(String notef) {
		this.notef = notef;
	}
   








}