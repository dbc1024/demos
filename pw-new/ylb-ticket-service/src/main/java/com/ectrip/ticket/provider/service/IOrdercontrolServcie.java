package com.ectrip.ticket.provider.service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.model.provider.Proordercontroltab;

public interface IOrdercontrolServcie extends IGenericService{

	Proordercontroltab showProordercontroltab(Long iscenicid);

	void saveOrupdateProordercontroltab(Proordercontroltab ordercontrol);

}
