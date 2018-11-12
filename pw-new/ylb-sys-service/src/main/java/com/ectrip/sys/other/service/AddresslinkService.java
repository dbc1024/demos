package com.ectrip.sys.other.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Addresslink;
import com.ectrip.sys.other.dao.idao.IAddresslinkDao;
import com.ectrip.sys.other.service.iservice.IAddresslinkService;

public class AddresslinkService extends GenericService implements
		IAddresslinkService {

	private IAddresslinkDao addresslinkDao;
		
	public IAddresslinkDao getAddresslinkDao() {
		return addresslinkDao;
	}

	public void setAddresslinkDao(IAddresslinkDao addresslinkDao) {
		this.addresslinkDao = addresslinkDao;
	}

	public PaginationSupport showAddresslinkList(int pageSize, int page,
			String url){
		return this.addresslinkDao.showAddresslinkList(pageSize, page, url);
	}
	
	public Addresslink getAddresslink(Long linkid) throws Exception{
		return this.addresslinkDao.getAddresslink(linkid);
	}

	public void addAddresslink(Addresslink address) {
		this.addresslinkDao.addAddresslink(address);
	}

	public void updateAddresslink(Addresslink address) {
		this.addresslinkDao.updateAddresslink(address);
	}

	public void deleteAddresslink(Addresslink address) {
		this.addresslinkDao.deleteAddresslink(address);
	}
	
}

