package com.ectrip.sys.backuplogin.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 * ��ȡOpeation
 * 
 * @author LiJin
 * 
 */
public interface IOperationDao {
	// 读取所有的operation
	public List getAllOperation() throws Exception;

	// 根据用户读取operation
	public List getAllEmployeeOperation(String empid) throws Exception;

	// 根据用户读取菜单
	public List findbyempid(String empid, BigDecimal popid);

	// 根据用户读取菜单,LIC方法 
	public List findbyempid(String empid, BigDecimal popid,String licid);
}
