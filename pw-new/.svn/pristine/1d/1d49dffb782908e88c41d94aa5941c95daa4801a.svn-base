package com.ectrip.ticket.sale.service.cytterminal.dao.idao;


import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.sale.service.cytterminal.model.Cyttasktab;

public interface ICytSaleDao extends IGenericDao {

	public Sysparv5 findSyspar(String pmky,String pmcd);
	public String findOTOCode(Long iscenicid);
	public Cyttasktab findCytTask(String orid);
	public List<Edmticketcomposepricetab> finSonPrice(Long priceId);
	public List ChupiaoT_order(String orid, Long iscenicid);
	public List ChupiaoT_orderlist(String orid, Long iscenicid);
	public List ChupiaoT_zorderlist(String orid, Long iscenicid);
	public Esbscenicareatab queryProviderByPosId(String posId);
	public List findOTOOrder(String srid);
}

