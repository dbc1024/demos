package com.ectrip.ec.model.cytterminal;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.user.Custom;

public class CombineOrderInfo {

	private String orid;
	private Long iscenicid;
	private Long numb;
	private Custom custom;
	private OrderInfo orderInfo;
	private MOrder morder;
	private TOrder torder;
	private YOrder yorder;
	private List<TOrderlist> tlist;
	private List<TZorderlist> tzlist;
	private List<YOrderlist> ylist;
	private List<YZorderlist> yzlist;
	
	public CombineOrderInfo() {
		super();
		morder = new MOrder();
		torder = new TOrder();
		yorder = new YOrder();
		tlist = new ArrayList<TOrderlist>();
		tzlist = new ArrayList<TZorderlist>();
		ylist = new ArrayList<YOrderlist>();
		yzlist = new ArrayList<YZorderlist>();
	}
	
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public Long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public TOrder getTorder() {
		return torder;
	}
	public void setTorder(TOrder torder) {
		this.torder = torder;
	}
	public List<TOrderlist> getTlist() {
		return tlist;
	}
	public void setTlist(List<TOrderlist> tlist) {
		this.tlist = tlist;
	}
	public List<TZorderlist> getTzlist() {
		return tzlist;
	}
	public void setTzlist(List<TZorderlist> tzlist) {
		this.tzlist = tzlist;
	}

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Long getNumb() {
		return numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public MOrder getMorder() {
		return morder;
	}

	public void setMorder(MOrder morder) {
		this.morder = morder;
	}

	public YOrder getYorder() {
		return yorder;
	}

	public void setYorder(YOrder yorder) {
		this.yorder = yorder;
	}

	public List<YOrderlist> getYlist() {
		return ylist;
	}

	public void setYlist(List<YOrderlist> ylist) {
		this.ylist = ylist;
	}

	public List<YZorderlist> getYzlist() {
		return yzlist;
	}

	public void setYzlist(List<YZorderlist> yzlist) {
		this.yzlist = yzlist;
	}
	
	
}

