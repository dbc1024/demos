package com.ectrip.sys.model.syspar;

/**
 * Horderlog entity. @author MyEclipse Persistence Tools
 */

public class Horderlog  implements java.io.Serializable {


    // Fields    

     private Long logid;
     private String orid;
     private String stlg;
     private String brief;
     private Long logtype;
     private String usid;
     private Long iemployeeid;
     private String logdatetime;
     private String note;
     private String dtmakedate;

   //�����ݱ��ֶ�
 	private String auname;			
 	private String szemployeename;
    // Constructors

    /** default constructor */
    public Horderlog() {
    }

	/** minimal constructor */
    public Horderlog(Long logid, String orid, String stlg, String brief, Long logtype) {
        this.logid = logid;
        this.orid = orid;
        this.stlg = stlg;
        this.brief = brief;
        this.logtype = logtype;
    }
    
    /** full constructor */
    public Horderlog(Long logid, String orid, String stlg, String brief, Long logtype, String usid, Long iemployeeid, String logdatetime, String note, String dtmakedate) {
        this.logid = logid;
        this.orid = orid;
        this.stlg = stlg;
        this.brief = brief;
        this.logtype = logtype;
        this.usid = usid;
        this.iemployeeid = iemployeeid;
        this.logdatetime = logdatetime;
        this.note = note;
        this.dtmakedate = dtmakedate;
    }

   
    // Property accessors

    public Long getLogid() {
        return this.logid;
    }
    
    public void setLogid(Long logid) {
        this.logid = logid;
    }

    public String getOrid() {
        return this.orid;
    }
    
    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getStlg() {
        return this.stlg;
    }
    
    public void setStlg(String stlg) {
        this.stlg = stlg;
    }

    public String getBrief() {
        return this.brief;
    }
    
    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Long getLogtype() {
        return this.logtype;
    }
    
    public void setLogtype(Long logtype) {
        this.logtype = logtype;
    }

    public String getUsid() {
        return this.usid;
    }
    
    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Long getIemployeeid() {
        return this.iemployeeid;
    }
    
    public void setIemployeeid(Long iemployeeid) {
        this.iemployeeid = iemployeeid;
    }

    public String getLogdatetime() {
        return this.logdatetime;
    }
    
    public void setLogdatetime(String logdatetime) {
        this.logdatetime = logdatetime;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    public String getDtmakedate() {
        return this.dtmakedate;
    }
    
    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

	public String getAuname() {
		return auname;
	}

	public void setAuname(String auname) {
		this.auname = auname;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}
   








}