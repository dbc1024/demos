package com.ectrip.ticket.service.model;

import java.io.Serializable;
import java.util.Vector;


public class HistoryOrder  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String iticketstationid;//售票点
	private String ihandler;//用户
	private String isalesvoucherid;//销售凭证id
	private String iaccountreceivable;//交易金额
	private String szsalesvoucherno;//订单号
	private String corpname;
	private String lname;
	private String dtmakedate;


	public HistoryOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HistoryOrder(String iticketstationid, String ihandler,
						String isalesvoucherid, String iaccountreceivable,
						String szsalesvoucherno,String corpname,String lname ,String dtmakedate) {
		super();
		this.iticketstationid = iticketstationid;
		this.ihandler = ihandler;
		this.isalesvoucherid = isalesvoucherid;
		this.iaccountreceivable = iaccountreceivable;
		this.szsalesvoucherno = szsalesvoucherno;
		this.corpname = corpname;
		this.lname = lname;
		this.dtmakedate = dtmakedate;
	}
	public String getIticketstationid() {
		return iticketstationid;
	}
	public void setIticketstationid(String iticketstationid) {
		this.iticketstationid = iticketstationid;
	}
	public String getIhandler() {
		return ihandler;
	}
	public void setIhandler(String ihandler) {
		this.ihandler = ihandler;
	}
	public String getIsalesvoucherid() {
		return isalesvoucherid;
	}
	public void setIsalesvoucherid(String isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}
	public String getIaccountreceivable() {
		return iaccountreceivable;
	}
	public void setIaccountreceivable(String iaccountreceivable) {
		this.iaccountreceivable = iaccountreceivable;
	}
	public String getSzsalesvoucherno() {
		return szsalesvoucherno;
	}
	public void setSzsalesvoucherno(String szsalesvoucherno) {
		this.szsalesvoucherno = szsalesvoucherno;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDtmakedate() {
		return dtmakedate;
	}
	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Vector<String> getTableModelData(){
		Vector<String> v = new Vector<String>();
		v.add(isalesvoucherid==null?"":isalesvoucherid);
		v.add(szsalesvoucherno==null?"":szsalesvoucherno);
		v.add(corpname==null?"":corpname);
		v.add(lname==null?"":lname);
		v.add(dtmakedate==null?"":dtmakedate);
		v.add(iaccountreceivable==null?"":iaccountreceivable);
		return v;
	}


}
