package com.ectrip.ec.book.ticket.model;

public class SearchKey {
	private String area;// ��������
	private String strarea;
	private String theme;// ��������
	private String grade;
	private String price;
	private String scenicname;// ��������
	private String strgrade;
	private String lowprice;
	private String height;
	private String orderby;//1:�۸� �ӵ͵��� 2:�۸� �Ӹߵ��� 3:���� �ӵ͵���  4:���� �Ӹߵ���
	private String style;//pic:��ͼ list:�б�
	private String clear;
	private String autosearch;//������ƥ��
	private String priceid;
	private String type;
	private String strtype;
	private String[] gradeids;
	private String address;
	public String getAutosearch() {
		return autosearch;
	}

	public void setAutosearch(String autosearch) {
		this.autosearch = autosearch;
	}

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getStrarea() {
		return strarea;
	}

	public void setStrarea(String strarea) {
		this.strarea = strarea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
		if (price != null&&!price.equals("")) {
			String[] prices = price.split("-");
			lowprice = prices[0];
			height = prices[1];
		}
	}

	public String getScenicname() {
		return scenicname;
	}

	public void setScenicname(String scenicname) {
		this.scenicname = scenicname;
	}

	public String getStrgrade() {
		return strgrade;
	}

	public void setStrgrade(String strgrade) {
		this.strgrade = strgrade;
	}

	public String getLowprice() {
		return lowprice;
	}

	public String getHeight() {
		return height;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setLowprice(String lowprice) {
		this.lowprice = lowprice;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStrtype() {
		return strtype;
	}

	public void setStrtype(String strtype) {
		this.strtype = strtype;
	}

	public String[] getGradeids() {
		return gradeids;
	}

	public void setGradeids(String[] gradeids) {
		this.gradeids = gradeids;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
