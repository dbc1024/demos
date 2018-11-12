package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;

public interface IDeldbService extends IGenericService {
	public void delDB(List sqllist);
}

