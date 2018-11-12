package com.ectrip.sys.syspar.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.syspar.dao.IGalsourceregiontabDAO;

public class GalsourceregiontabDAO extends GenericDao implements IGalsourceregiontabDAO {

    public List<Galsourceregiontab> findByIds(String[] ids){
    	String sql = "select g from Galsourceregiontab g where id||'' in (:ids) order by ilevel";
        List list = null;
		try {
			list = this.findBySqlToMap(sql, new Object[] {ids});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        query.setParameterList("ids", ids);
        return list;
    }

    public Galsourceregiontab findByCode(String code){
    	String sql = "select g from Galsourceregiontab g where szregionalcode=? ";
        List<Galsourceregiontab> list = this.find(sql, new Object[]{code});
        return list == null || list.isEmpty() ? null : list.get(0);
    }
}
