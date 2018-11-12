package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Receiptmanager;
import com.ectrip.sys.model.syspar.Syslog;

public interface IReceiptManagerDao extends IGenericDao {

	public List sceniclist(String scenics, String scenictype);
	
	public PaginationSupport showReceiptManagerList(Long iscenicid,int pageSize, int startIndex, String url);
	
	public List showPrintnoList();
	
	public List showContenttypeList();
	
	public List showPrinttypeList();
	
	public List<Receiptmanager> receiptManagerList(Long iscenicid) throws Exception;
	
	public void addReceiptManager(Receiptmanager receipt,Syslog syslog);
	
	public void updateReceiptManager(List<Receiptmanager> lst,Receiptmanager receiptmanager,Syslog syslog);
	
	public Receiptmanager findReceiptManager(Long seq) throws Exception;
	
	public void deleteReceiptManager(Long seq,Syslog syslog);
}

