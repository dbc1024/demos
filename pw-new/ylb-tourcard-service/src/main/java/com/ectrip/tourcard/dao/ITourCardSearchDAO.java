package com.ectrip.tourcard.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

public interface ITourCardSearchDAO extends IGenericDao{

	/**
	 * 读取旅游卡列表
	 * @param userId
	 * @return
	 */
	public List<Map> getTourCardList(String userId);
	
	//复制于ISearchDAO中getTourCard，用于替代原方法
	public List<TourCard> getTourCard(String userId);
	
	//复制于ISearchDAO中getTourCarDetail，用于替代原方法
	public TourCardDetail getTourCarDetail(Object carNo);

}
