package com.ectrip.ticket.common.dao;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.common.dao.idao.IReserveorderDao;
import com.ectrip.ticket.model.reserveorder.ReserveDate;
import com.ectrip.ticket.model.reserveorder.ReserveInfo;
import com.ectrip.ticket.model.reserveorder.Reserveorderinfo;

public class ReserveorderDao extends GenericDao implements IReserveorderDao {

	public void saveReserveorderinfo(Reserveorderinfo reserveorderinfo){
        ReserveInfo reserveInfo = JSON.parseObject(reserveorderinfo.getDatajson(), ReserveInfo.class);
        List<ReserveDate> dates = reserveInfo.getDates();
        ReserveDate date = dates.get(0);
        reserveorderinfo.setYydate(date.getBeginDate());
        reserveorderinfo.setRztime(date.getBeginTime());
        reserveorderinfo.setLdtime(date.getEndTime());
		this.save(reserveorderinfo);
	}
	
	public void updateReserveorderinfo(Reserveorderinfo reserveorderinfo){
        ReserveInfo reserveInfo = JSON.parseObject(reserveorderinfo.getDatajson(), ReserveInfo.class);
        List<ReserveDate> dates = reserveInfo.getDates();
        ReserveDate date = dates.get(0);
        reserveorderinfo.setYydate(date.getBeginDate());
        reserveorderinfo.setRztime(date.getBeginTime());
        reserveorderinfo.setLdtime(date.getEndTime());
        this.update(reserveorderinfo);
	}
	
	public Reserveorderinfo selectOneByOrid(String orid){
		return (Reserveorderinfo) this.get(Reserveorderinfo.class, orid);
	}
	
	public void deleteReserveorderinfo(String orid){
		Reserveorderinfo reserveorderinfo = (Reserveorderinfo) this.get(Reserveorderinfo.class, orid);
		if(reserveorderinfo != null){
			this.delete(reserveorderinfo);
		}
	}
}

