package com.ectrip.base.dao.idao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * 
 *JDBC DAO层接口 在纯属SPRING中运行SQL
 *的处理接口，只要在SERVICE使用这个接口，将可以直接运行SQL
 *运行SQL时返加ROWSET，不是返加ResultSet
 *
 * 
 * @author 李进
 * 
 * @since 2006-4-30 8:40:56
 * 
 * @version 0.10a
 **/

public interface IGenericJdbcDao {

	/**
	 * 运行SQL语句
	 * 
	 * @param how
	 */
	public void execute(String how);

	/**
	 * 根据条件选择数据
	 */

	public List select(String where);

	/**
	 * 返回C++ Buillder 相关的数据接口；
	 */
	public String selectBc(String where) throws Exception;

	/**
	 * 执行UPDATE语句
	 * 
	 * @param how
	 */
	public void update(String how);

	/**
	 * 执行增加语句
	 * 
	 * @param sql
	 */
	public void insert(String sql);

	/**
	 * SqlRowSet 根据条件返回 SqlRowSet
	 * 
	 * @param where
	 * @return
	 */
	public SqlRowSet getSqlRowSet(String where);
}
