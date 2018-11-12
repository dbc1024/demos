package com.ectrip.ec.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.dao.idao.ITZOrderListDAO;
import com.ectrip.ec.order.dao.idao.IYOrderListDAO;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;

@Repository
public class TZOrderListDAO extends GenericDao implements ITZOrderListDAO {

	@Override
	public List<TZorderlist> getTZorderlist(Long orderlistid, String orid, Long iscenicid) {
		String hql=" from TZorderlist where id.orderlistid="+orderlistid+" and id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		return find(hql);
	}
	
	
	
}
