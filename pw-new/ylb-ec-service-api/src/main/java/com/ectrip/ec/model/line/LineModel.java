package com.ectrip.ec.model.line;

public class LineModel {
	public Integer childcount; //��ͯ����
	public Integer youngcount;//��������
	public String startdate;//����
	public Double youngprice;//���˼۸�
	public Double childprice;//��ͯ�۸�
	public Double payprice;//֧�����
	public Double ding_payprice;//����
	public String market;//��ע

	// ��Ʒid
	private Long itickettypeid;
	// ������id
	private Long iscenicid;
	// ҵ������
	private Long ibusinessid;
	// ��Ⱥ����
	private Long icrowdkindid;
	// ����
	private String note1 = "0000";// �۸����
	
	public LineModel() {
		// TODO Auto-generated constructor stub
	}
	
	public LineModel(Long itickettypeid,Long iscenicid,Long icrowdkindid,String note1,Integer youngcount,Integer childcount,
			String startdate,Double childprice,Double youngprice,Double payprice,String market,Double ding_payprice){
		this.itickettypeid = itickettypeid;
		this.iscenicid = iscenicid;
		this.icrowdkindid = icrowdkindid;
		this.note1 = note1;
		this.childcount = childcount;
		this.youngcount = youngcount;
		this.startdate = startdate;
		this.youngprice = youngprice;
		this.ding_payprice = ding_payprice;
		this.childprice = childprice;
		this.payprice = payprice;
		this.market = market;
	}

	public Integer getChildcount() {
		return childcount;
	}

	public void setChildcount(Integer childcount) {
		this.childcount = childcount;
	}

	public Integer getYoungcount() {
		return youngcount;
	}

	public void setYoungcount(Integer youngcount) {
		this.youngcount = youngcount;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public Double getYoungprice() {
		return youngprice;
	}

	public void setYoungprice(Double youngprice) {
		this.youngprice = youngprice;
	}

	public Double getChildprice() {
		return childprice;
	}

	public void setChildprice(Double childprice) {
		this.childprice = childprice;
	}

	public Double getPayprice() {
		return payprice;
	}

	public void setPayprice(Double payprice) {
		this.payprice = payprice;
	}

	public Double getDing_payprice() {
		return ding_payprice;
	}

	public void setDing_payprice(Double dingPayprice) {
		ding_payprice = dingPayprice;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}
	
	
}
