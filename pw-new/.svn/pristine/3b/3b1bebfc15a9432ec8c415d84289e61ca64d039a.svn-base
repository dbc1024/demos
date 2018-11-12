package com.ectrip.ticket.stocks.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocktab;
import com.ectrip.ticket.stocks.dao.idao.IStocksUserDao;
import com.ectrip.ticket.stocks.service.iservice.IStocksUserService;

public class StocksUserService implements IStocksUserService{
  private IStocksUserDao stocksUserDao;

public IStocksUserDao getStocksUserDao() {
	return stocksUserDao;
}

public void setStocksUserDao(IStocksUserDao stocksUserDao) {
	this.stocksUserDao = stocksUserDao;
}

public List selectUser() {
	
	return stocksUserDao.selectUser();
}

public List selectUserStockForF() {
	
	return stocksUserDao.selectUserStockForF();
}



public PaginationSupport initInfo(int pageSize, int startInt, String url) {
	
	return stocksUserDao.initInfo(pageSize, startInt, url);
}

public void addUserStock(Syslog syslog, Stocktab stocktab) {
	 stocksUserDao.addUserStock(syslog, stocktab);
	
}

public Stocktab selectUserBySeq(Long seq) {
	return stocksUserDao.selectUserBySeq(seq);
}



public PaginationSupport selectUserStock(Stocktab stocktab, int pageSize,
		int page, String url) {
	
	return stocksUserDao.selectUserStock(stocktab, pageSize, page, url);
}

public List searchUserStock(Stocktab stocktab) {
	
	return stocksUserDao.searchUserStock(stocktab);
}



  
  
}
