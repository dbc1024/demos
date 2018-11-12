package com.ectrip.ec.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.order.dao.idao.ILZorderlistDAO;
@Repository
public class LZorderlistDAO extends GenericDao implements ILZorderlistDAO {

	@Override
	public List getLZorderlistByParam(String orid, Long iscenicid, Long orderlistid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from LZorderlist ");
		StringBuffer where = new StringBuffer();
		if(orid!=null&&!("").equals(orid)) {
			where.append(" and id.orid= '"+orid+"'");
		}
		if(null!=iscenicid) {
			where.append(" and id.iscenicid= "+iscenicid);
		}
		if(null!=orderlistid) {
			where.append(" and id.orderlistid= "+iscenicid);
		}
		String newWhere = where.toString().replaceFirst("and", "where");
		hql.append(newWhere);
		List result = this.find(hql.toString());
		return result;
	}

}
