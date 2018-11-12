package com.ectrip.tourcard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.dao.ITourCardDetailDAO;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.TourCardDetail;
import com.ectrip.tourcard.service.ITourCardDetailService;

@Service
public class TourCardDetailService extends GenericService implements ITourCardDetailService{
	
	private ITourCardDetailDAO tourCardDetailDao;
	
	@Autowired
	public void setTourCardDetailDao(ITourCardDetailDAO tourCardDetailDao) {
		this.tourCardDetailDao = tourCardDetailDao;
		super.setGenericDao(tourCardDetailDao);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog) {
		tourCardDetailDao.insertTourCardDetail(tourCardDetail, syslog);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog) {
		tourCardDetailDao.updateTourCardDetail(tourCardDetail, syslog);
	}

	public TourCardDetail getTourCardDetail(Long cardDetailId) {
		
		return tourCardDetailDao.getTourCardDetail(cardDetailId);
	}

	public PaginationSupport getTourCardDetailList(CardDetailQuery query, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		return tourCardDetailDao.getTourCardDetailList(query, page, pageSize, url);
	}
	public List<TourCardDetail> getDetailsByIdnumAndCode(String idNum, String code) {
		// TODO Auto-generated method stub
		return tourCardDetailDao.getDetailsByIdnumAndCode(idNum, code);
	}

	@Override
	public List getListAll(String cardNumber, String cardName, String profitNum) {
		// TODO Auto-generated method stub
		return tourCardDetailDao.getListAll(cardNumber, cardName, profitNum);
	}

	@Override
	public List getCardDetailsByCardNumbers(String cardNumbers) {
		// TODO Auto-generated method stub
		return tourCardDetailDao.getCardDetailsByCardNumbers(cardNumbers);
	}

}
