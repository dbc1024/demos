package com.ectrip.ticket.sale.service.card.model;

import java.util.List;

public class Timekeeping {
	private String unit;//��λ
	private int unitscale;//��ΪunitpriceԪ/ÿunitscale unit
	private double unitprice;//
	private double minexpense;//��С���ѽ��
	private int upperlimit;//����ʱʱ��
	private int delayed;//��ʱʱ��
	private List<Discountprice>  discountprices;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getUnitscale() {
		return unitscale;
	}
	public void setUnitscale(int unitscale) {
		this.unitscale = unitscale;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getMinexpense() {
		return minexpense;
	}
	public void setMinexpense(double minexpense) {
		this.minexpense = minexpense;
	}
	public int getUpperlimit() {
		return upperlimit;
	}
	public void setUpperlimit(int upperlimit) {
		this.upperlimit = upperlimit;
	}
	public int getDelayed() {
		return delayed;
	}
	public void setDelayed(int delayed) {
		this.delayed = delayed;
	}
	public List<Discountprice> getDiscountprices() {
		return discountprices;
	}
	public void setDiscountprices(List<Discountprice> discountprices) {
		this.discountprices = discountprices;
	}
	
}

