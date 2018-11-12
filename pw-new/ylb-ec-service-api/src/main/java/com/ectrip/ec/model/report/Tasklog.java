package com.ectrip.ec.model.report;

import java.util.Date;

/**
 * Tasklog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tasklog implements java.io.Serializable {

	// Fields

	private Long logno;
	private String employeeid;//������id
	private String logcontent;//��־����
	private String logtime;//����ʱ��
	private String employeename;//����������
	private String taskname;//��������
	private String taskservicename;//�����������

	// Constructors

	/** default constructor */
	public Tasklog() {
	}

	/** full constructor */
	public Tasklog(Long logno, String employeeid, String logcontent,
			String logtime, String employeename, String taskname,
			String taskservicename) {
		this.logno = logno;
		this.employeeid = employeeid;
		this.logcontent = logcontent;
		this.logtime = logtime;
		this.employeename = employeename;
		this.taskname = taskname;
		this.taskservicename = taskservicename;
	}

	// Property accessors

	public Long getLogno() {
		return this.logno;
	}

	public void setLogno(Long logno) {
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

}