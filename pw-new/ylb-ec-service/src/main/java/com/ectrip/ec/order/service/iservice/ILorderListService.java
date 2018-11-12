package com.ectrip.ec.order.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.LOrderlist;

public interface ILorderListService extends IGenericService{

	void saveLorderList(LOrderlist lorderList);

	List<LOrderlist> getLOrderlistByids(String orid, Long iscenicid);

}
