package com.ectrip.ticket.model.applyorder;

/**
 * 用户绑定团队类型表
 * @author lijingrui
 */
public class Custeamtype implements java.io.Serializable {

	private Long id;//主键(流水)
	private Long teamtypeid;//团队类型ID
	private String usid;//旅行社usid
	private String dtmaketime;//时间
	private String note1;//备用字段
	private String note2;//备用字段
	private Long inote6;//备用字段
	private Long inote7;//备用字段


	public Custeamtype() {
		super();
	}

	public Custeamtype(Long id, Long teamtypeid, String usid,
					   String dtmaketime, String note1, String note2, Long inote6,
					   Long inote7) {
		super();
		this.id = id;
		this.teamtypeid = teamtypeid;
		this.usid = usid;
		this.dtmaketime = dtmaketime;
		this.note1 = note1;
		this.note2 = note2;
		this.inote6 = inote6;
		this.inote7 = inote7;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeamtypeid() {
		return teamtypeid;
	}

	public void setTeamtypeid(Long teamtypeid) {
		this.teamtypeid = teamtypeid;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getDtmaketime() {
		return dtmaketime;
	}

	public void setDtmaketime(String dtmaketime) {
		this.dtmaketime = dtmaketime;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public Long getInote6() {
		return inote6;
	}

	public void setInote6(Long inote6) {
		this.inote6 = inote6;
	}

	public Long getInote7() {
		return inote7;
	}

	public void setInote7(Long inote7) {
		this.inote7 = inote7;
	}


}
