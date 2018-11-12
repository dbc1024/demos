package com.ectrip.ec.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.order.dao.idao.ILorderListDAO;
@Repository
public class OrderListDAO extends GenericDao implements ILorderListDAO {

	@Override
	public void saveLorderList(LOrderlist lorderList) {
		try {
			this.save(lorderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LOrderlist> getLOrderlistByids(String orid, Long iscenicid) {
		StringBuffer hql = new StringBuffer();
		hql.append("from LOrderlist ");
		if(orid!=null&&!("").equals(orid)) {
			hql.append("where id.orid= '"+orid+"'");
			if(iscenicid!=null&&!("").equals(iscenicid)) {
				hql.append("and id.iscenicid= '"+iscenicid+"'");
			}
		}else {
			if(iscenicid!=null&&!("").equals(iscenicid)) {
				hql.append("where id.iscenicid= '"+iscenicid+"'");
			}
		}
		return this.find(hql.toString());
	}

}
