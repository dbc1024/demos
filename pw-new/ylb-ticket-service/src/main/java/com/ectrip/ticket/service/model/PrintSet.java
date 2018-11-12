package com.ectrip.ticket.service.model;


/**
 * 打印票设置
 * @author LiuJianwen
 *
 */
public class PrintSet {
	private static final long serialVersionUID = 1L;
	private String szbusinessname;
	private String szprintno;//打印名称
	private String ordernum;
	private String ibusinessid;
	private String szscenicname;
	private String printno;//打印代表编号
	private String iscenicid;



	public PrintSet(String szbusinessname, String szprintno, String ordernum,
					String ibusinessid, String szscenicname, String printno,
					String iscenicid) {
		super();
		this.szbusinessname = szbusinessname;
		this.szprintno = szprintno;
		this.ordernum = ordernum;
		this.ibusinessid = ibusinessid;
		this.szscenicname = szscenicname;
		this.printno = printno;
		this.iscenicid = iscenicid;
	}

	public PrintSet(){}

	public String getSzbusinessname() {
		return szbusinessname;
	}
	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}
	public String getSzprintno() {
		return szprintno;
	}
	public void setSzprintno(String szprintno) {
		this.szprintno = szprintno;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getIbusinessid() {
		return ibusinessid;
	}
	public void setIbusinessid(String ibusinessid) {
		this.ibusinessid = ibusinessid;
	}
	public String getSzscenicname() {
		return szscenicname;
	}
	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}
	public String getPrintno() {
		return printno;
	}
	public void setPrintno(String printno) {
		this.printno = printno;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}


}
