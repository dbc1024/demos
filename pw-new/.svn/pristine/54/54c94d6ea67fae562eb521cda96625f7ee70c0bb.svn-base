package com.ectrip.ticket.provider.dao.impl;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.model.provider.Proordercontroltab;
import com.ectrip.ticket.provider.dao.IOrdercontrolDao;
@Repository
public class OrdercontrolDao extends GenericDao implements IOrdercontrolDao{

	public Proordercontroltab showProordercontroltab(Long iscenicid) {
		// TODO Auto-generated method stub
		return (Proordercontroltab) this.get(Proordercontroltab.class, iscenicid);
	}

	public void saveOrupdateProordercontroltab(Proordercontroltab ordercontrol) {
		// TODO Auto-generated method stub
		this.saveOrUpdate(ordercontrol);
	}

}
