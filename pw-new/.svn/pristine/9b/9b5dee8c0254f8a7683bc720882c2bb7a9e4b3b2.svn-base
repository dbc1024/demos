package com.ectrip.sys.syspar.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Columnpar;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Tablepar;

public interface IReportsParDAO extends IGenericDao {
	/**
	 * 获取表字典列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-17
	 */
	public PaginationSupport getTableParList(Tablepar tablepar,int page,int pageSize,String url);
	
	/**
	 * 获取列字典列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-17
	 */
	public PaginationSupport getColumnParList(Columnpar columnpar,int page,int pageSize,String url);
	
	/**
	 * 增加表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void addTablePar(Tablepar tablepar,Syslog syslog);
	/**
	 * 修改表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void updateTablePar(Tablepar tablepar,Syslog syslog);
	
	/**
	 * 删除表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void deleteTablePar(Long seq,Syslog syslog);
	
	/**
	 * 增加列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void addColumnPar(Columnpar columnpar,Syslog syslog);
	/**
	 * 修改列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void updateColumnPar(Columnpar columnpar,Syslog syslog);
	
	/**
	 * 删除列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void deleteColumnPar(Columnpar columnpar,Syslog syslog);

	/**
	 * 修改时判断表名是否唯一
	 * Describe:只能用作修改，增加时不可用
	 * @auth:huangyuqi
	 * @param tablename
	 * @return
	 * return:boolean
	 * Date:2011-11-18
	 */
	public boolean queryTableIsUse(String tablename);
	/**
	 * 修改时判断列名是否已经唯一
	 * Describe:只能用作修改，增加时不可用
	 * @auth:huangyuqi
	 * @param tablename
	 * @param columnname
	 * @return
	 * return:boolean
	 * Date:2011-11-18
	 */
	public boolean queryColumnIsUse(Long seq,String columnname);

}

