package com.ectrip.ticket.model.salesmanager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cxh on 2015/12/30.
 */
@Table
@Entity
public class SaleLog {
	@Id
	private Long logId;// 主键

	private String logType;// 01:常规数据 02：操作日志

	private Long winId;// 窗口ID

	private String userId;// 售票员账号

	private String note;// 日志

	private String dtmakedate;// 操作时间

	public SaleLog() {

	}

	public SaleLog(Long logId, String logType, Long winId, String userId, String note, String dtmakedate) {
		this.logId = logId;
		this.logType = logType;
		this.winId = winId;
		this.userId = userId;
		this.note = note;
		this.dtmakedate = dtmakedate;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Long getWinId() {
		return winId;
	}

	public void setWinId(Long winId) {
		this.winId = winId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}
}
