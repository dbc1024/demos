package com.ectrip.tourcard.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.TourCardDetail;

public interface ITourCardDetailDAO extends IGenericDao{

	public void insertTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog);
	
	public void updateTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog);
	
	public TourCardDetail getTourCardDetail(Long cardDetailId);
	
	public PaginationSupport getTourCardDetailList(CardDetailQuery query, int page,int pageSize,String url);

	public TourCardDetail getTourCardDetail(String cardCode, String idNum);

	public List<TourCardDetail> getUserTourCardDetails(String userId);

	public List<TourCardDetail> getUserExpiredTourCardDetails(String userId);

	public List<TourCardDetail> getUserAllTourCardDetails(String userId);

	public List<TourCardDetail> getDetailsByIdnumAndCode(String idNum, String code);

	public List<TourCardDetail> getHistoryTourCardDetails(String idNum);
	public List<TourCardDetail> getTourCardDetailConsume();
	
	public List getListAll(String cardNumber, String cardName, String profitNum);
	
	public List getCardDetailsByCardNumbers(String cardNumbers);
}
