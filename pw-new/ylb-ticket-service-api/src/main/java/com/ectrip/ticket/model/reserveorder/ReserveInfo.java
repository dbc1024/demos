package com.ectrip.ticket.model.reserveorder;

import java.util.List;

public class ReserveInfo {

	private String type;// 01:web端,02：移动端,03：后台
	private String userId;//用户ID或者售票员ID  type=03,填写售票员ID
	private List<ReserveDate> dates;//日期集合

	public ReserveInfo() {
		super();
	}

	public ReserveInfo(String type, String userId, List<ReserveDate> dates) {
		super();
		this.type = type;
		this.userId = userId;
		this.dates = dates;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ReserveDate> getDates() {
		return dates;
	}
	public void setDates(List<ReserveDate> dates) {
		this.dates = dates;
	}


}

