package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Receiptmanager;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IReceiptManagerDao;
import com.ectrip.sys.syspar.service.IReceiptManagerService;

public class ReceiptManagerService extends GenericService implements
		IReceiptManagerService {

	private IReceiptManagerDao receiptManagerDao;

	public IReceiptManagerDao getReceiptManagerDao() {
		return receiptManagerDao;
	}

	public void setReceiptManagerDao(IReceiptManagerDao receiptManagerDao) {
		this.receiptManagerDao = receiptManagerDao;
	}
	
	public List sceniclist(String scenics, String scenictype){
		return this.receiptManagerDao.sceniclist(scenics, scenictype);
	}
	
	public PaginationSupport showReceiptManagerList(Long iscenicid,int pageSize, int startIndex, String url){
		return this.receiptManagerDao.showReceiptManagerList(iscenicid, pageSize, startIndex, url);
	}

	public List showContenttypeList() {
		return this.receiptManagerDao.showContenttypeList();
	}

	public List showPrintnoList() {
		return this.receiptManagerDao.showPrintnoList();
	}

	public List showPrinttypeList() {
		return this.receiptManagerDao.showPrinttypeList();
	}
	
	public List<Receiptmanager> receiptManagerList(Long iscenicid) throws Exception{
		return this.receiptManagerDao.receiptManagerList(iscenicid);
	}
	
	public void addReceiptManager(Receiptmanager receipt,Syslog syslog){
		this.receiptManagerDao.addReceiptManager(receipt, syslog);
	}
	
	public void updateReceiptManager(List<Receiptmanager> lst,Receiptmanager receiptmanager,Syslog syslog){
		try {
			List<Receiptmanager> receiptmanagers = receiptManagerDao.receiptManagerList(receiptmanager.getIscenicid());
			if(receiptmanagers!=null&&!receiptmanagers.isEmpty()){
				for(int i=0;i<receiptmanagers.size();i++){
					Receiptmanager receipt = receiptmanagers.get(i);
					System.out.println(receipt.getSeq());
					this.receiptManagerDao.delete(receipt);
				}
			}
			this.receiptManagerDao.updateReceiptManager(lst, receiptmanager, syslog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Receiptmanager findReceiptManager(Long seq) throws Exception{
		return this.receiptManagerDao.findReceiptManager(seq);
	}
	
	public void deleteReceiptManager(Long seq,Syslog syslog){
		this.receiptManagerDao.deleteReceiptManager(seq,syslog);
	}
}

