package com.ectrip.tourcard.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourCard;

public interface ITourCardDAO extends IGenericDao{

	public void insertTourCard(TourCard tourCard,Syslog syslog);
	
	public void updateTourCard(TourCard tourCard,Syslog syslog);
	
	public TourCard getTourCard(Long cardId);
	
	public PaginationSupport getTourCardList(CardQuery query, int page,int pageSize,String url);
	
	public List<Map> getAllAreasAndSceience();

	public TourCard getTourCardByCardCode(String code);
	
	public List<TourCard> getTourCardByName(String name);
	
	public List<TourCard> getTourCardByProfitNum(String profitNum);
}
