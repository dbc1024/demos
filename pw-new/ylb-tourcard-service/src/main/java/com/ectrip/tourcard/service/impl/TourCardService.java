package com.ectrip.tourcard.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.dao.ITourCardDAO;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.service.ITourCardService;

@Service
public class TourCardService extends GenericService implements ITourCardService{
	
	private ITourCardDAO tourCardDao;
	@Autowired
	public void setTourCardDao(ITourCardDAO tourCardDao) {
		this.tourCardDao = tourCardDao;
		super.setGenericDao(tourCardDao);   
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertTourCard(TourCard tourCard, Syslog syslog) {
		tourCardDao.insertTourCard(tourCard,syslog);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateTourCard(TourCard tourCard, Syslog syslog) {
		tourCardDao.updateTourCard(tourCard, syslog);
	}
	public TourCard getTourCard(Long cardId) {
		
		return tourCardDao.getTourCard(cardId);
	}
	public PaginationSupport getTourCardList(CardQuery query, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		return tourCardDao.getTourCardList(query, page, pageSize, url);
	}
	
	public List<Map> getAllAreasAndSceience() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TourCard getTourCardByCode(String code) {
		
		return tourCardDao.getTourCardByCardCode(code);
	}
	
	public List<TourCard> getTourCardByName(String name){
		return tourCardDao.getTourCardByName(name);
	}
	public List<TourCard> getTourCardByProfitNum(String profitNum) {
		// TODO Auto-generated method stub
		return tourCardDao.getTourCardByProfitNum(profitNum);
	}
	
}
