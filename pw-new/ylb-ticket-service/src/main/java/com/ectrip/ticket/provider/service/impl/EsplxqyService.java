package com.ectrip.ticket.provider.service.impl;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esplxqytab;
import com.ectrip.ticket.provider.dao.IEsplxqytabDAO;
import com.ectrip.ticket.provider.service.IEsplxqytabService;

@Service
public class EsplxqyService implements IEsplxqytabService{

	private IEsplxqytabDAO esplxqytabDAO;
	
	
	public IEsplxqytabDAO getEsplxqytabDAO() {
		return esplxqytabDAO;
	}

	public void setEsplxqytabDAO(IEsplxqytabDAO esplxqytabDAO) {
		this.esplxqytabDAO = esplxqytabDAO;
	}

	public void addEsplxqytab(Esplxqytab esplxqytab, Syslog syslog) {
		esplxqytabDAO.addEsplxqytab(esplxqytab, syslog);
	}

	public void deleteEsplxqytab(Long seq, Syslog syslog) {
		esplxqytabDAO.deleteEsplxqytab(seq, syslog);
	}

	public Esplxqytab getEsplxqytabfindBySeq(Long seq) throws Exception {
		return esplxqytabDAO.getEsplxqytabfindBySeq(seq);
	}

	public PaginationSupport showEsplxqytabList(Long iscenicid,String flag,String query, int pageSize,
			int startIndex, String url) {
		return esplxqytabDAO.showEsplxqytabList(iscenicid,flag,query, pageSize, startIndex, url);
	}

	public void updateEsplxqytab(Esplxqytab esplxqytab, Syslog syslog) {
		esplxqytabDAO.updateEsplxqytab(esplxqytab, syslog);
	}

	
}
