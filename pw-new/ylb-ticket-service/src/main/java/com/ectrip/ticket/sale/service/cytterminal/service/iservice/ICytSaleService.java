package com.ectrip.ticket.sale.service.cytterminal.service.iservice;


import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.cytterminal.CombineOrderInfo;
import com.ectrip.ec.model.cytterminal.ConsumeOrderInfo;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.sale.service.cytterminal.model.Cyttasktab;

public interface ICytSaleService {
	
	public Sysparv5 findSyspar(String pmky,String pmcd);
	public String findOTOCode(Long iscenicid);
	public Cyttasktab findCytTask(String orid);
	public Object get(Class<?> cls,Object obj);
	public void saveOrUpdate(Object obj);
	public Long getSequence(String sequenceName) throws Exception;
	public List<Edmticketcomposepricetab> finSonPrice(Long priceId);
	public List findOTOOrder(String srid);
	public ResultBean consumeOrder(int saleType,ConsumeOrderInfo consumeOrderInfo,CombineOrderInfo combineOrderInfo,Long iscenicid,Long iticketwinid, 
			Long iemployeeid,String... param) throws Exception;
	public Esbscenicareatab queryProviderByPosId(String posId);
}

