package com.ectrip.ec.model.order;
public class LOrderpeople  implements java.io.Serializable{
	private LOrderpeopleId id;
	private String pname;
	private String pzjhm;
	public LOrderpeopleId getId() {
		return id;
	}
	public void setId(LOrderpeopleId id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPzjhm() {
		return pzjhm;
	}
	public void setPzjhm(String pzjhm) {
		this.pzjhm = pzjhm;
	}

}

