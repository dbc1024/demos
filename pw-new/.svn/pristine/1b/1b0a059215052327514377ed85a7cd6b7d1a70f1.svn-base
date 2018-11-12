package com.ectrip.ticket.common.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.common.dao.idao.IAppVersionDao;
import com.ectrip.ticket.common.service.iservice.IAppVersionService;
import com.ectrip.ticket.model.appversion.AppVersion;

public class AppVersionService extends GenericService implements
		IAppVersionService {

	private IAppVersionDao appVersionDao;

	public IAppVersionDao getAppVersionDao() {
		return appVersionDao;
	}

	public void setAppVersionDao(IAppVersionDao appVersionDao) {
		this.appVersionDao = appVersionDao;
	}

	public AppVersion getLatestAppVersion(String id, String clientType){
		return appVersionDao.getLatestAppVersion(id, clientType);
	}
}

