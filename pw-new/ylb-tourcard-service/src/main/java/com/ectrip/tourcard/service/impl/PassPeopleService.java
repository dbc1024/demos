package com.ectrip.tourcard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.dao.IPassPeopleDAO;
import com.ectrip.tourcard.model.PassPeople;
import com.ectrip.tourcard.service.IPassPeopleService;

@Service
public class PassPeopleService extends GenericService implements IPassPeopleService{
	
	
	private IPassPeopleDAO passPeopleDao;
	
	@Autowired
	public void setPassPeopleDao(IPassPeopleDAO passPeopleDao) {
		this.passPeopleDao = passPeopleDao;
		super.setGenericDao(passPeopleDao);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertPassPeople(PassPeople people) {
		passPeopleDao.insertPassPeople(people);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updatePassPeople(PassPeople people) {
		passPeopleDao.updatePassPeople(people);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deletePassPeople(PassPeople people) {
		passPeopleDao.delete(people);
	}

	public PassPeople getPassPeople(Long id) {
		// TODO Auto-generated method stub
		return passPeopleDao.getPassPeople(id);
	}

	public PaginationSupport getPassPeopleList(PassPeople query, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		return passPeopleDao.getPassPeopleList(query, page, pageSize, url);
	}
	public PassPeople getPassPeopleByIdentityNum(String identityNum) {
		// TODO Auto-generated method stub
		return passPeopleDao.getPassPeopleByIdentityNum(identityNum);
	}
	public PassPeople getPassPeopleByIdAndCode(String identityNum, String code) {
		// TODO Auto-generated method stub
		return passPeopleDao.getPassPeopleByIdAndCode(identityNum, code);
	}

}
