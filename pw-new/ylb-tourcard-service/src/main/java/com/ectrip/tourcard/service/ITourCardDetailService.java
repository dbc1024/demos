package com.ectrip.tourcard.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.TourCardDetail;

public interface ITourCardDetailService extends IGenericService{

	public void insertTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog);
	
	public void updateTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog);
	
	public TourCardDetail getTourCardDetail(Long cardDetailId);
	
	public PaginationSupport getTourCardDetailList(CardDetailQuery query, int page,int pageSize,String url);
	
	public List<TourCardDetail> getDetailsByIdnumAndCode(String idNum, String code);
	
	public List getListAll(String cardNumber, String cardName, String profitNum);
	
	public List getCardDetailsByCardNumbers(String cardNumbers);
}
