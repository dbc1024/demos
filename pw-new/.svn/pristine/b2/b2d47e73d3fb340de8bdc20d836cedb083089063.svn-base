package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Roamingcard;
import com.ectrip.ticket.provider.dao.IRoamingCardDao;
import com.ectrip.ticket.provider.service.IRoamingCardService;

@Service
public class RoamingCardService extends GenericService implements
		IRoamingCardService {
	IRoamingCardDao roamingCardDao;

	public IRoamingCardDao getRoamingCardDao() {
		return roamingCardDao;
	}

	public void setRoamingCardDao(IRoamingCardDao roamingCardDao) {
		this.roamingCardDao = roamingCardDao;
	}
	
	public PaginationSupport showlistCrad(String usid,String cardno,String idcard,String username,Long radiobutton,String rzti,String ldti,int pageSize, int startInt, String url){
		return this.roamingCardDao.showlistCrad(usid, cardno, idcard, username, radiobutton, rzti, ldti, pageSize, startInt, url);
	}
	
	public void insertRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		this.roamingCardDao.insertRoamingCard(roamingcard, syslog);
	}
	
	public void updateRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		this.roamingCardDao.updateRoamingCard(roamingcard, syslog) ;
	}
	
	public void deleteRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		this.roamingCardDao.deleteRoamingCard(roamingcard, syslog);
	}
	
	public Roamingcard findCardByCardno(String cardno,Long seq,Long itickettypeid,String type) throws Exception{
		return this.roamingCardDao.findCardByCardno(cardno, seq, itickettypeid, type);
	}
	
	public List showAllticket(){
		return this.roamingCardDao.showAllticket();
	}
	
	public List showPic(Long seq){
		return this.roamingCardDao.showPic(seq);
	}
	
	public List showRoamingEmp(){
		return this.roamingCardDao.showRoamingEmp();
	}
}

