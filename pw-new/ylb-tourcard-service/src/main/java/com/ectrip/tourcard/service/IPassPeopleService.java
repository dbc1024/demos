package com.ectrip.tourcard.service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.model.PassPeople;

public interface IPassPeopleService extends IGenericService{

	public void insertPassPeople(PassPeople people);
	
	public void updatePassPeople(PassPeople people);
	
	public void deletePassPeople(PassPeople people);
	
	public PassPeople getPassPeople(Long id);
	
	public PaginationSupport getPassPeopleList(PassPeople query, int page,int pageSize,String url);
	
	public PassPeople getPassPeopleByIdentityNum(String identityNum);
	
	public PassPeople getPassPeopleByIdAndCode(String identityNum, String code);
}
