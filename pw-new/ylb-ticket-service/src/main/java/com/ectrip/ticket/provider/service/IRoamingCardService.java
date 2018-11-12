package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Roamingcard;

public interface IRoamingCardService extends IGenericService {

	public PaginationSupport showlistCrad(String usid,String cardno,String idcard,String username,Long radiobutton,String rzti,String ldti,int pageSize, int startInt, String url);
	
	public void insertRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception;
	
	public void updateRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception;
	
	public void deleteRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception;
	
	public Roamingcard findCardByCardno(String cardno,Long seq,Long itickettypeid,String type) throws Exception;
	
	public List showAllticket();
	
	public List showPic(Long seq);
	
	public List showRoamingEmp();
}

