package com.ectrip.tourcard.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

public interface ITourCardSearchService extends IGenericService {
	public String getTourCardList(String userId) throws Exception;

	public String getTourCard(String userId,Long cardId) throws Exception;

	public String saveTourCardOrder(String userId, Long cardId, Long tourCardDetailId) throws Exception;

	public TourCardDetail updateTourCardOrder(String orderId) throws Exception;

	public String isAlreadyExistHisoryTourCard(String cardCode, String identityNum) throws Exception;

	public String bindingHistoryTourCard(String tourCardDetailId, String mobile) throws Exception;

	public String getUserTourCardDetails(String mobile);

	public String getTourCardByCardCode(String cardCode);

	public String isRealNameUser(String mobile);

	public String getHistoryTourCards(String mobile);

	public String getTourCardOrder(String orderId);

	public String getExpiredTourCardDetailList(String mobile);

	public String getUserAllTourCardDetails(String mobile);
	
	public List<TourCard> getTourCard(String userId);
	
	public TourCardDetail getTourCarDetail(Object carNo);
}
