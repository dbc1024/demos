package com.ectrip.ticket.service.model;



public class Custom  implements Comparable<Custom>{

	private static final long serialVersionUID = 1L;
	private String usid;//客服id
	private String corpname;//名称
	public Custom(String usid, String corpname) {
		super();
		this.usid = usid;
		this.corpname = corpname;
	}
	public Custom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	@Override
	public String toString() {
		return  corpname;
	}
	public int compareTo(Custom c) {
		return corpname.length() - c.getCorpname().length();
	}


}
