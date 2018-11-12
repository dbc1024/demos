package com.ectrip.ec.model.ticket;

public class FilmbookModel {
	private Long iprogramid;//��Ŀ���
	private String szprogramname;//��Ŀ����
	private Long iticketid;
	private Long icrowdkindpriceid;
	private Long icrowkindid;
	private double listingprice;//���Ƽ�
	private double mactualsaleprice;//ʵ�����ۼ۸�
	private int numb;//����
	private Long seatid;//��λ���
	private String tourdate;
	private Long itripid;//���
	private Long ivenueid;//���ݱ��
	private Long ivenueareaid;//����������
	private String venuname;//��������
	private String crowkindname;//��Ⱥ��������
	private int rownum;
	private int colnum;
	private int seatseq;//��λ����ID
	private String seatids="";
	private String triptime;//ʱ��
	private Long iscenicid;
	private String szvenueseatsname;
	public String getSzvenueseatsname() {
		return szvenueseatsname;
	}
	public void setSzvenueseatsname(String szvenueseatsname) {
		this.szvenueseatsname = szvenueseatsname;
	}
	public String getTriptime() {
		return triptime;
	}
	public void setTriptime(String triptime) {
		this.triptime = triptime;
	}
	public int getSeatseq() {
		return seatseq;
	}
	public void setSeatseq(int seatseq) {
		this.seatseq = seatseq;
	}
	public String getCrowkindname() {
		return crowkindname;
	}
	public void setCrowkindname(String crowkindname) {
		this.crowkindname = crowkindname;
	}
	public String getVenuname() {
		return venuname;
	}
	public void setVenuname(String venuname) {
		this.venuname = venuname;
	}
	public Long getIticketid() {
		return iticketid;
	}
	public void setIticketid(Long iticketid) {
		this.iticketid = iticketid;
	}
	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public double getListingprice() {
		return listingprice;
	}
	public void setListingprice(double listingprice) {
		this.listingprice = listingprice;
	}
	public double getMactualsaleprice() {
		return mactualsaleprice;
	}
	public void setMactualsaleprice(double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}
	
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}

	
	public Long getSeatid() {
		return seatid;
	}
	public void setSeatid(Long seatid) {
		this.seatid = seatid;
	}
	public String getTourdate() {
		return tourdate;
	}
	public void setTourdate(String tourdate) {
		this.tourdate = tourdate;
	}
	public Long getItripid() {
		return itripid;
	}
	public void setItripid(Long itripid) {
		this.itripid = itripid;
	}
	public Long getIvenueid() {
		return ivenueid;
	}
	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}
	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}
	public Long getIprogramid() {
		return iprogramid;
	}
	public void setIprogramid(Long iprogramid) {
		this.iprogramid = iprogramid;
	}
	public String getSzprogramname() {
		return szprogramname;
	}
	public void setSzprogramname(String szprogramname) {
		this.szprogramname = szprogramname;
	}
	
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getColnum() {
		return colnum;
	}
	public void setColnum(int colnum) {
		this.colnum = colnum;
	}
	@Override
	public String toString() {
		return this.iprogramid+this.tourdate+this.ivenueid+this.itripid+this.seatid;
	}
	
	public String sameFilmtrip(){
		return this.iprogramid+this.tourdate+this.ivenueid+this.itripid;
	}
	
	public String sameFilmtripCrow(){
		return this.iprogramid+this.tourdate+this.ivenueid+this.itripid+this.icrowdkindpriceid;
	}
	
	public Long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getSeatids() {
		return seatids;
	}
	public void setSeatids(String seatids) {
		this.seatids = seatids;
	}
	public Long getIcrowkindid() {
		return icrowkindid;
	}
	public void setIcrowkindid(Long icrowkindid) {
		this.icrowkindid = icrowkindid;
	}
	
	
}
