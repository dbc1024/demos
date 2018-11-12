package com.ectrip.ticket.afcset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.afcset.dao.IEsbgardengateTicketDao;
import com.ectrip.ticket.afcset.service.IEsbgardengateTicketService;
import com.ectrip.ticket.model.afcset.Esbgardengatetickettab;

@Service
public class EsbgardengateTicketService extends GenericService implements IEsbgardengateTicketService{

	private IEsbgardengateTicketDao esbgardengateTicketDao;

	@Autowired
	public void setEsbgardengateTicketDao(IEsbgardengateTicketDao esbgardengateTicketDao) {
		this.esbgardengateTicketDao = esbgardengateTicketDao;
		super.setGenericDao(esbgardengateTicketDao);
	}

	public PaginationSupport showGardenGateTicketViewList(Long igardengateid,
			int pageSize, int page, String url) {
		// TODO Auto-generated method stub
		return this.esbgardengateTicketDao.showGardenGateTicketViewList(igardengateid, pageSize, page, url);
	}

	public PaginationSupport queryTicket(Long iscenicid, String bycategorytype,
			Long byusage, String sztickettypename, int pageSize,
			int page, String url) {
		// TODO Auto-generated method stub
		return this.esbgardengateTicketDao.queryTicket(iscenicid, bycategorytype, byusage, sztickettypename, pageSize, page, url);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveGardenGateTicket(Esbgardengatetickettab gardengateticket) {
		// TODO Auto-generated method stub
		this.esbgardengateTicketDao.saveGardenGateTicket(gardengateticket);
	}

	public List searchGardengateTicket(Long seq, Long igardengateid,
			Long itickettypeoneid, Long itickettypetwoid) {
		// TODO Auto-generated method stub
		return this.esbgardengateTicketDao.searchGardengateTicket(seq, igardengateid, itickettypeoneid, itickettypetwoid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateGardenGateTicket(Esbgardengatetickettab gardengateticket) {
		// TODO Auto-generated method stub
		this.esbgardengateTicketDao.updateGardenGateTicket(gardengateticket);
	}

	public Esbgardengatetickettab getGardenGateTicket(Long seq) throws Exception {
		// TODO Auto-generated method stub
		return this.esbgardengateTicketDao.getGardenGateTicket(seq);
	}
	
}

