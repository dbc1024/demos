package com.ectrip.tourcard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.tourcard.dao.IPassPeopleDAO;
import com.ectrip.tourcard.model.PassPeople;

@Repository
public class PassPeopleDAO extends GenericDao implements IPassPeopleDAO{

	public void insertPassPeople(PassPeople people) {
		this.save(people);
	}

	public void updatePassPeople(PassPeople people) {
		this.update(people);
	}

	public PassPeople getPassPeople(Long id) {
		PassPeople people = (PassPeople)this.get(PassPeople.class, id);
		return people;
	}

	public PaginationSupport getPassPeopleList(PassPeople query, int page, int pageSize, String url) {
		
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		
		hsql.append("select distinct new map(people.id as id, people.cardCode as cardCode, people.name as name, people.identityNum as identityNum) from PassPeople people");
		where.append(" where people.cardCode='"+ query.getCardCode()+"'");
		
		
		if(!"".equals(query.getName()) && query.getName() != null){
			where.append(" and people.name like '%"+ query.getName() +"%'");
		}
		if(!"".equals(query.getIdentityNum()) && query.getIdentityNum() != null){
			where.append(" and people.identityNum = '"+ query.getIdentityNum()+"'");
		}
			
		
		ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
		
		return ps;
	}
	
	
	public PassPeople getPassPeopleByIdentityNum(String identityNum) {
		
		List<PassPeople> list = this.find("from PassPeople where identityNum=?", new Object[]{identityNum});
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	public PassPeople getPassPeopleByIdAndCode(String identityNum, String code) {

		List<PassPeople> list = this.find("from PassPeople where identityNum=? and cardCode=?", new Object[]{identityNum, code});
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
}
