package com.ectrip.ticket.permitenter.dao;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Reservechannel;

public interface IReservechannelDAO {
	List findListesbticket(String scenics);
	
	PaginationSupport getReservechannel(Reservechannel reservechannel,
                                        String scenics, int pageSize, int page, String url);
	
	List getEsbaccessequip(Long igardengateid);

	String igetAllEsbaccessequiptabJSON(Long id) throws Exception;
	
    Long getMaxId() throws Exception;
    
    String igetGardengateJSON(Long id) throws Exception;
     
	void addReservechannel(Reservechannel reservechannel);
	
	Reservechannel getReservechannelById(Long id);
	
	void delReservechannel(Reservechannel reservechannel);
	
	boolean checkReservechannel(Reservechannel reservechannel);

}
