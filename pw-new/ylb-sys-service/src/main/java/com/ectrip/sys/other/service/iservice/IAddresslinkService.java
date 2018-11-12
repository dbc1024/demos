package com.ectrip.sys.other.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Addresslink;

public interface IAddresslinkService extends IGenericService {

	public PaginationSupport showAddresslinkList(int pageSize, int page,
			String url);
	
	public Addresslink getAddresslink(Long linkid) throws Exception;
	
	public void addAddresslink(Addresslink address);
	
	public void updateAddresslink(Addresslink address);
	
	public void deleteAddresslink(Addresslink address);
}

