package com.ectrip.sys.syspar.service.impl;

import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.ISysDeleteDataDAO;
import com.ectrip.sys.syspar.service.ISysDeleteDataService;

public class SysDeleteDataService implements ISysDeleteDataService {

	private ISysDeleteDataDAO sysdeletedataDao;
	
	
	public ISysDeleteDataDAO getSysdeletedataDao() {
		return sysdeletedataDao;
	}

	public void setSysdeletedataDao(ISysDeleteDataDAO sysdeletedataDao) {
		this.sysdeletedataDao = sysdeletedataDao;
	}


	/**
	 * 删除表数据*
	 * Describe:
	 * @see com.ectrip.system.syspar.service.iservice.ISysDeleteDataService#deleteTableData(java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param tablename
	 * @param syslog
	 * @author huangyuqi
	 * Date:2011-12-24
	 */
	public void deleteTableData(String[] tablename,Syslog syslog){
		sysdeletedataDao.deleteTableData(tablename, syslog);
	}
	/**
	 * 根据表类型删除数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param tabletype
	 * @param syslog
	 * return:void
	 * Date:2011-12-24
	 */
	public void deleteTableDataByType(String tabletype,Syslog syslog){
		sysdeletedataDao.deleteTableDataByType(tabletype, syslog);
	}
}

