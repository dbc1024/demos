package com.ectrip.tourcard.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.PassPeople;

public interface IPassPeopleDAO extends IGenericDao{
	
	public void insertPassPeople(PassPeople people);
	
	public void updatePassPeople(PassPeople people);
	
	public PassPeople getPassPeople(Long id);
	
	public PaginationSupport getPassPeopleList(PassPeople query, int page,int pageSize,String url);
	
	public PassPeople getPassPeopleByIdentityNum(String identityNum);

	public PassPeople getPassPeopleByIdAndCode(String identityNum, String code);

}
