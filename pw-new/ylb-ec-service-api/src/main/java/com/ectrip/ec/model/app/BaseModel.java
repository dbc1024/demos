package com.ectrip.ec.model.app;

import java.util.List;
import java.util.Map;

public class BaseModel {
	
	protected String usid;
	
	protected String pwd;
	
	protected String logonstatus;
	
	protected String msgtp;
	
	protected String username;
	
	protected Object object=null;//要是map
	
	protected String payUrl;
	
	
	
	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	protected  List<Map> nodes=null;
	
	

	public String getMsgtp() {
		return msgtp;
	}

	public void setMsgtp(String msgtp) {
		this.msgtp = msgtp;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getLogonstatus() {
		return logonstatus;
	}

	public void setLogonstatus(String logonstatus) {
		this.logonstatus = logonstatus;
	}

	public List<Map> getNodes() {
		return nodes;
	}

	public void setNodes(List<Map> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
