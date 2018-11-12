package com.ectrip.sys.model.syspar;

public class Esyspar {

	private String pmky;
	private String pmcd;
	private String spmcd;
	private String pmva;
	private Long haveson;
	private Long hasnext;
	
	
	

	public Long getHasnext() {
	    return hasnext;
	}

	public void setHasnext(Long hasnext) {
	    this.hasnext = hasnext;
	}

	public String getPmky() {
		return pmky;
	}

	public void setPmky(String pmky) {
		this.pmky = pmky;
	}

	public String getPmcd() {
		return pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public String getSpmcd() {
		return spmcd;
	}

	public void setSpmcd(String spmcd) {
		this.spmcd = spmcd;
	}

	public String getPmva() {
		return pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

	public Long getHaveson() {
		return haveson;
	}

	public void setHaveson(Long haveson) {
		this.haveson = haveson;
	}

}
