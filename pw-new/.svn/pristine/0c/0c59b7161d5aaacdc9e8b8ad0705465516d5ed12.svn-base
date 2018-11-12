package com.ectrip.ticket.provider.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;

public interface ITimeSharingService extends IGenericService{
	
	public Map<String,Object> updateTicket(Long timeId, String productId,String timeStart,String timeEnd,int totalStock) throws Exception;
	
	public List findTimeTicket(String productId,Long timeId) throws Exception;
	
	public void UpdateStock(Long timeId,String productId,int count,String flag) throws Exception;
	
	public void saveTimes(TimeSharingTicketTab _SharingTicketTab) throws Exception;
	
	public List findTimeTicketByProductCode(String productId) throws Exception;
	
	public List findTimeTicketByProductCodeAnddate(String productId,String date) throws Exception;
	
	public List findTimeTicketByProductCode(String productId,String startTime,String endTime) throws Exception;
	
	public List findTimeTicketsByProductCode(String productId) throws Exception;

}
