package com.ectrip.ec.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;
import com.ectrip.ec.order.dao.idao.ILorderDAO;
@Repository
public class LorderDAO extends GenericDao implements ILorderDAO {
	@Override
	public List<LOrder> getLorderByOrid(String orid) {
		if(orid!=null&&!("").equals(orid)) {
			 List<LOrder> find = this.find("from LOrder where id.orid='"+orid+"' ");
			 return find;
		}
		return null;
	}

}
