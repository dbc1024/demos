package com.ectrip.ec.book.rentrl.news.model;
/**
 * �⳵����model
 * 
 * CarTypeModel
 * 
 * hejiahua
 * hejiahua
 * 2013-12-30 ����09:34:28
 * 
 * @version 1.0.0
 *
 */
public class CarTypeModel {
	public String linename;//·������
	public String cartypename;//��������
	public Integer seating; //��λ��
	public Integer hasperson;//��������(6������)
	public Integer reservenumb;//Ԥ������
	public String startdate;//�ó�����
	public Double price;//�۸�
	public Double payprice;//֧�����
	public Double ding_payprice;//����
	public String market;//��ע

	// ��Ʒid
	private String itickettypeid;
	// ������id
	private Long iscenicid;
	// ҵ������
	private Long ibusinessid;
	// ��Ⱥ����
	private Long icrowdkindid;
	// ����
	private String note1 = "0000";// �۸����
	
	
	public Double getDing_payprice() {
		return ding_payprice;
	}

	public void setDing_payprice(Double dingPayprice) {
		ding_payprice = dingPayprice;
	}

	public CarTypeModel(){};
	
	public CarTypeModel(String itickettypeid,Long iscenicid,Long icrowdkindid,String note1,String linename,String cartypename,Integer hasperson,Integer seating,Integer reservenumb,
			String startdate,Double price,Double payprice,String market){
		this.itickettypeid = itickettypeid;
		this.iscenicid = iscenicid;
		this.icrowdkindid = icrowdkindid;
		this.note1 = note1;
		this.linename = linename;
		this.cartypename = cartypename;
		this.seating = seating;
		this.hasperson = hasperson;
		this.reservenumb = reservenumb;
		this.startdate = startdate;
		this.price = price;
		this.payprice = payprice;
		this.market = market;
	}
	
	public String getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(String itickettypeid) {
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

	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getCartypename() {
		return cartypename;
	}
	public void setCartypename(String cartypename) {
		this.cartypename = cartypename;
	}
	public Integer getSeating() {
		return seating;
	}
	public void setSeating(Integer seating) {
		this.seating = seating;
	}
	public Integer getHasperson() {
		return hasperson;
	}
	public void setHasperson(Integer hasperson) {
		this.hasperson = hasperson;
	}
	public Integer getReservenumb() {
		return reservenumb;
	}
	public void setReservenumb(Integer reservenumb) {
		this.reservenumb = reservenumb;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPayprice() {
		return payprice;
	}
	public void setPayprice(Double payprice) {
		this.payprice = payprice;
	}
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
}
