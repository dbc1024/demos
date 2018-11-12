package com.ectrip.ec.book.rentrl.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.book.rentrl.dao.idao.IRentrlDao;
import com.ectrip.ec.book.rentrl.service.iservice.IRentrlService;
import com.ectrip.ec.model.rentrl.Carticketrecordtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public class RentrlService extends GenericService implements IRentrlService{
	private IRentrlDao rentrlDao;
	public IRentrlDao getRentrlDao() {
		return rentrlDao;
	}

	public void setRentrlDao(IRentrlDao rentrlDao) {
		this.rentrlDao = rentrlDao;
	}

	public PaginationSupport getRentrlProductList(String rzti, String ldti,
			String lgtp, int page, int pageSize, String url) {
		return rentrlDao.getRentrlProductList(rzti, ldti, lgtp, page, pageSize, url);
	}

	public PaginationSupport getRentrlProductList(Long regionId, String rzti,
			String ldti, String lgtp, int page, int pageSize, String url) {
		return rentrlDao.getRentrlProductList(regionId, rzti, ldti, lgtp, page, pageSize, url);
	}

	public PaginationSupport getRentrlProductSearchList(
			Esbscenicareatab provider, String rzti, String ldti, String lgtp,
			int page, int pageSize, String url) {
		return rentrlDao.getRentrlProductSearchList(provider, rzti, ldti, lgtp, page, pageSize, url);
	}

	public List getRentrlSourceList(String pdtp) {
		return rentrlDao.getRentrlSourceList(pdtp);
	}

	public Esbscenicareatab getRentrlTicketduct(Long iscenicid, String rzti,
			String ldti, String lgtp) throws Exception {
		return rentrlDao.getRentrlTicketduct(iscenicid, rzti, ldti, lgtp);
	}

	public PaginationSupport shopAllticketcenter(Long szregionalid,
			String lgtp, int page, int pageSize, String url) {
		return rentrlDao.shopAllticketcenter(szregionalid, lgtp, page, pageSize, url);
	}

	public boolean checkRentrl(Long tickettypeid) {
		return rentrlDao.checkRentrl(tickettypeid);
	}

	public void saveCarRecord(Carticketrecordtab carticketrecordtab) {
		rentrlDao.saveCarRecord(carticketrecordtab);
	}
	public boolean btncheck(Long itickettypeid) {
		return rentrlDao.btncheck(itickettypeid);
	}

	public double findAvgPrice(Long itickettypeid, String startDate,
			String endDate) {
		return rentrlDao.findAvgPrice(itickettypeid, startDate, endDate);
	}

	public boolean findPrice(Long itickettypeid, String date) {
		return rentrlDao.findPrice(itickettypeid, date);
	}

}
