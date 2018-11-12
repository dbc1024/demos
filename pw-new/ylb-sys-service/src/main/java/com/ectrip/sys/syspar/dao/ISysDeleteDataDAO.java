package com.ectrip.sys.syspar.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Syslog;

public interface ISysDeleteDataDAO extends IGenericDao {
	/**
	 * 删除表数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablename
	 * @param syslog
	 * return:void
	 * Date:2011-12-24
	 */
	public void deleteTableData(String[] tablename,Syslog syslog);
	
	/**
	 * 根据表类型删除数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param tabletype
	 * @param syslog
	 * return:void
	 * Date:2011-12-24
	 */
	public void deleteTableDataByType(String tabletype,Syslog syslog);
}

