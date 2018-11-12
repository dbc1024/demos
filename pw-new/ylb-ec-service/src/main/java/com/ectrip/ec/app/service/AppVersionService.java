package com.ectrip.ec.app.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.app.dao.idao.IAppVersionDao;
import com.ectrip.ec.app.model.AppVersion;
import com.ectrip.ec.app.service.iservice.IAppVersionService;

public class AppVersionService extends GenericService implements IAppVersionService {

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
