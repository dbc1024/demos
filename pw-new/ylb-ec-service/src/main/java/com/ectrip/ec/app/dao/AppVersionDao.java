package com.ectrip.ec.app.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.app.dao.idao.IAppVersionDao;
import com.ectrip.ec.app.model.AppVersion;

public class AppVersionDao extends GenericDao implements IAppVersionDao {
	public AppVersion getLatestAppVersion(String id, String clientType) {
		Long versionId = StringUtil.isEmpty(id) ? 0L: Long.valueOf(id);
		String sql = " from AppVersion where id>? and clientType = ? and rownum=1 order by id desc ";
		List<AppVersion> list = this.find(sql, new Object[]{versionId, Long.valueOf(clientType)});
		return list == null || list.isEmpty() ? null : list.get(0);
	}
}
