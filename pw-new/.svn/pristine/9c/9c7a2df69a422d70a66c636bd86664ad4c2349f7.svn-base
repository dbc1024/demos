package com.ectrip.ticket.stocks.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocksticketwarebycustomtab;
import com.ectrip.ticket.model.stock.Stocksticketwaretab;
import com.ectrip.ticket.stocks.dao.idao.IStocksProductDao;
import com.ectrip.ticket.stocks.service.iservice.IStocksProductService;

public class StocksProductService implements IStocksProductService {


	private IStocksProductDao stocksProductDao;

	public IStocksProductDao getStocksProductDao() {
		return stocksProductDao;
	}

	public void setStocksProductDao(IStocksProductDao stocksProductDao) {
		this.stocksProductDao = stocksProductDao;
	}

	public PaginationSupport initInfo(int pageSize, int startInt, String url) {
		return stocksProductDao.initInfo(pageSize, startInt, url);
	}

	public void addProductStock(Stocksticketwaretab stocksticketwaretab,
								Syslog syslog) {
		stocksProductDao.addProductStock(stocksticketwaretab, syslog);

	}

	public void delProductStock(Stocksticketwaretab stocksticketwaretab,
								Syslog syslog) {
		stocksProductDao.delProductStock(stocksticketwaretab, syslog);

	}

	public void updateProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog) {
		stocksProductDao.updateProductStock(stocksticketwaretab,syslog);
	}

	public PaginationSupport selectProductStockFF(int pageSize, int startInt,
												  String url, String strNameF) {
		return stocksProductDao.selectProductStockFF(pageSize, startInt, url,strNameF);
	}


	public PaginationSupport selectProductStockCC(int pageSize, int startInt,
												  String url, String strNameC) {
		return stocksProductDao.selectProductStockCC(pageSize, startInt, url,strNameC);
	}

	public boolean checkProductStockIsExist(Long pid,long obitype) {
		return stocksProductDao.checkProductStockIsExist(pid,obitype);
	}

	public Stocksticketwaretab alterProductStock(Long seq) {
		return stocksProductDao.alertProductStock(seq);
	}

	public List selectProductStockForF(String sql) {
		return stocksProductDao.selectProductStockForF(sql);
	}


	public List selectProductStockForC(String sql) {
		return stocksProductDao.selectProductStockForC(sql);
	}

	public boolean checkProductStockF(long pid) {
		// TODO Auto-generated method stub
		return stocksProductDao.checkProductStockF(pid);
	}

	public boolean checkProductStockC(long pid) {
		// TODO Auto-generated method stub
		return stocksProductDao.checkProductStockC(pid);
	}

	/**
	 *
	 * Describe:查询出所有客户库存表
	 * @author:huxingwei
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-12-17
	 */
	public PaginationSupport getAllStockProductCustom(int pageSize, int startInt, String url){
		return stocksProductDao.getAllStockProductCustom(pageSize, startInt, url);
	}

	/**
	 *
	 * Describe:查询所有旅行社分社
	 * @author:huxingwei
	 * @return
	 * return:List
	 * Date:2014-12-17
	 */
	public PaginationSupport getAllLxsfs(String query,int pageSize,int startIndex,String url){
		return stocksProductDao.getAllLxsfs(query, pageSize, startIndex, url);
	}

	/**
	 *
	 * Describe:客户库存增加
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void addCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		stocksProductDao.addCustomProductStock(stocksticketwarebycustomtab, syslog);
	}

	/**
	 *
	 * Describe:客户库存删除
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void delCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		stocksProductDao.delCustomProductStock(stocksticketwarebycustomtab, syslog);
	}

	/**
	 *
	 * Describe:客户库存修改
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void updateCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		stocksProductDao.updateCustomProductStock(stocksticketwarebycustomtab, syslog);
	}


	/**
	 * *
	 * Describe:判断用户库存是否存在，当是天库存时，需判断日期段
	 * @see com.ectrip.system.stocks.service.iservice.IStocksProductService#checkCustomProductStockIsExist(com.ectrip.model.stocks.Stocksticketwarebycustomtab, java.lang.String)
	 * @param stock
	 * @param type
	 * @return
	 * @author lijingrui
	 * Date:2014-12-19
	 */
	public String checkCustomProductStockIsExist(Stocksticketwarebycustomtab stock,String type ){
		return stocksProductDao.checkCustomProductStockIsExist(stock, type);
	}

	/**
	 *
	 * Describe:根据id查询客户库存信息
	 * @author:huxingwei
	 * @param seq
	 * @return
	 * return:Stocksticketwaretab
	 * Date:2014-12-18
	 */
	public Stocksticketwarebycustomtab getCustomProductStockById(Long seq){
		return stocksProductDao.getCustomProductStockById(seq);
	}

	public PaginationSupport selectCustomProductStockFF(int pageSize, int startInt,String url, Long strNameF,String objtype,String corpname) {
		return stocksProductDao.selectCustomProductStockFF(pageSize, startInt, url, strNameF, objtype,corpname);
	}

}
