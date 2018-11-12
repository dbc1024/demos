package com.ectrip.ec.order.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TZorderlist;
public interface ITZOrderListDAO extends IGenericDao{

	List<TZorderlist> getTZorderlist(Long orderlistid, String orid, Long iscenicid);
	
}
