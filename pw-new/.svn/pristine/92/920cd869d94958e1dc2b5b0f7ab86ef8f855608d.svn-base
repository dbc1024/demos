package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Receiptmanager;
import com.ectrip.sys.model.syspar.Syslog;

public interface IReceiptManagerService extends IGenericService {
	
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

