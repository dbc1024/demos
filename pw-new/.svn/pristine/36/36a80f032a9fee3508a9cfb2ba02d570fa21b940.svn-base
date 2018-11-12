package com.ectrip.ticket.sale.service.card.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Discountprice implements Comparable<Discountprice>{
	private double discountprice;
	private int startTime;
	private int endTime;//��Ϊ����ʱ��startTime��endTimeʱ��discountpriceΪ��ʱ����
	public double getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(double discountprice) {
		this.discountprice = discountprice;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int compareTo(Discountprice o) {
		return this.startTime-o.getStartTime();
	}
	public static void main(String[] args) {
		List<Discountprice> list = new ArrayList<Discountprice>();
		Discountprice d = new Discountprice();
		d.startTime=9;
		list.add(d);
		d = new Discountprice();
		d.startTime=7;
		list.add(d);
		d = new Discountprice();
		d.startTime=8;
		list.add(d);
		Collections.sort(list);
		for (Discountprice d1 : list) {
			System.out.println(d1.startTime);
		}
	}
}

