package com.ectrip.sys.model.syspar;

import java.math.BigDecimal;


/**
 * Htasklog entity. @author MyEclipse Persistence Tools
 */

public class Htasklog  implements java.io.Serializable {


    // Fields    

     private BigDecimal logno;
     private String employeeid;
     private String logcontent;
     private String logtime;
     private String employeename;
     private String taskname;
     private String taskservicename;
     private String dtmakedate;


    // Constructors

    /** default constructor */
    public Htasklog() {
    }

	/** minimal constructor */
    public Htasklog(BigDecimal logno) {
        this.logno = logno;
    }
    
    /** full constructor */
    public Htasklog(BigDecimal logno, String employeeid, String logcontent, String logtime, String employeename, String taskname, String taskservicename, String dtmakedate) {
        this.logno = logno;
        this.employeeid = employeeid;
        this.logcontent = logcontent;
        this.logtime = logtime;
        this.employeename = employeename;
        this.taskname = taskname;
        this.taskservicename = taskservicename;
        this.dtmakedate = dtmakedate;
    }

   
    // Property accessors

    public BigDecimal getLogno() {
        return this.logno;
    }
    
    public void setLogno(BigDecimal logno) {
        this.logno = logno;
    }

    public String getEmployeeid() {
        return this.employeeid;
    }
    
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getLogcontent() {
        return this.logcontent;
    }
    
    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent;
    }

    public String getLogtime() {
        return this.logtime;
    }
    
    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public String getEmployeename() {
        return this.employeename;
    }
    
    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getTaskname() {
        return this.taskname;
    }
    
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskservicename() {
        return this.taskservicename;
    }
    
    public void setTaskservicename(String taskservicename) {
        this.taskservicename = taskservicename;
    }

    public String getDtmakedate() {
        return this.dtmakedate;
    }
    
    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }
   








}