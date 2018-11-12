package com.ectrip.ec.model.order;
public class RaftComparepojo {
    	
	private Long iticketid;//��Ʒ���
	private Long tripid;//�˴α��
	private Long ivenueid;
	private Long ivenueareaid;
	private Long ivenueseatsid;
	private String tourdate;//��������,��ƱΪ��ʱ �����񷤵ĳ�������
	private String enddate;//��ͨƱ������ �洢ΪƱ�Ľ������� ������Ʊ�����˶�����
	private Long numb;//����
	private double price;//�۸�
	private double tdpercent;//�˶���ȡ�����Ѱٷֱ�
	private String errtp;//0:ͣ��  1:�˴ο�����������  2:�տ�����������
	private Long parentticketid;//�����ֵ��Ϊ��  ���ʾ�˱����itciketid�ĸ������
	private String ticketname;
	private String tripname;
	private Long icrowdid;
	private int yhnum;
	
	
	
	
	
	public int getYhnum() {
		return yhnum;
	}

	public void setYhnum(int yhnum) {
		this.yhnum = yhnum;
	}

	public boolean compare(Object object){
		RaftComparepojo c1=(RaftComparepojo) object;
		if(c1==null){
			return false;
		}
		if(c1.getTourdate()==null||c1.getTourdate().equals("")||!c1.getTourdate().equals(tourdate)){
			return false;
		}
		
		if(c1.getTripid()==null||c1.getTripid().equals("")||c1.getTripid().intValue()!=tripid.intValue()){
			return false;
		}
		
		if(c1.getIvenueid()==null||c1.getIvenueid().equals("")||c1.getIvenueid().intValue()!=ivenueid.intValue()){
			return false;
		}
		if(c1.getIvenueareaid()==null||c1.getIvenueareaid().equals("")||c1.getIvenueareaid().intValue()!=ivenueareaid.intValue()){
			return false;
		}
		return true;
	}
		
	public double getTdpercent() {
		return tdpercent;
	}
	
	public void setTdpercent(double tdpercent) {
		this.tdpercent = tdpercent;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getIticketid() {
		return iticketid;
	}
	public void setIticketid(Long iticketid) {
		this.iticketid = iticketid;
	}

	

	public Long getParentticketid() {
	    return parentticketid;
	}

	public void setParentticketid(Long parentticketid) {
	    this.parentticketid = parentticketid;
	}

	public String getErrtp() {
		return errtp;
	}

	public void setErrtp(String errtp) {
		this.errtp = errtp;
	}

	public Long getNumb() {
		return numb;
	}
	public void setNumb(Long numb) {
		this.numb = numb;
	}
	public Long getTripid() {
		return tripid;
	}
	public void setTripid(Long tripid) {
		this.tripid = tripid;
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
	public Long getIvenueseatsid() {
		return ivenueseatsid;
	}
	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}
	public String getTourdate() {
		return tourdate;
	}
	public void setTourdate(String tourdate) {
		this.tourdate = tourdate;
	}

	public String getTicketname() {
	    return ticketname;
	}

	public void setTicketname(String ticketname) {
	    this.ticketname = ticketname;
	}

	public String getTripname() {
	    return tripname;
	}

	public void setTripname(String tripname) {
	    this.tripname = tripname;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getIcrowdid() {
		return icrowdid;
	}

	public void setIcrowdid(Long icrowdid) {
		this.icrowdid = icrowdid;
	}
	
	
}

