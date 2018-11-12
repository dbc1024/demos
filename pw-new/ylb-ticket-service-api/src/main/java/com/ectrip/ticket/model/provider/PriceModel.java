package com.ectrip.ticket.model.provider;
/**
 * 每日价格
 *
 * PriceModel
 *
 * hejiahua
 * hejiahua
 * 2014-1-17 上午10:57:13
 *
 * @version 1.0.0
 *
 */
public class PriceModel {
	private String date;//日期

	private double youngPrice;//成人价

	private int youngcount;//成人名额

	private Integer childcount;//儿童名额

	private Double childPrice;//儿童价

	private Double roomcha;//单房差

	public int getYoungcount() {
		return youngcount;
	}

	public void setYoungcount(int youngcount) {
		this.youngcount = youngcount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getYoungPrice() {
		return youngPrice;
	}

	public void setYoungPrice(double youngPrice) {
		this.youngPrice = youngPrice;
	}



	public Integer getChildcount() {
		return childcount;
	}

	public void setChildcount(Integer childcount) {
		this.childcount = childcount;
	}

	public Double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(Double childPrice) {
		this.childPrice = childPrice;
	}

	public Double getRoomcha() {
		return roomcha;
	}

	public void setRoomcha(Double roomcha) {
		this.roomcha = roomcha;
	}

	public PriceModel(){};

	public PriceModel(String date,double youngPrice,Double childPrice,Double roomcha,int youngcount,Integer childcount){
		this.date = date;
		this.youngPrice = youngPrice;
		this.childPrice = childPrice;
		this.roomcha = roomcha;
		this.childcount = childcount;
		this.youngcount = youngcount;
	}
}
