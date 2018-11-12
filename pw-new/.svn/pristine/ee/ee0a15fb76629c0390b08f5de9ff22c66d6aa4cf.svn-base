package com.ectrip.sys.backuplogin.service;

import java.math.BigDecimal;
import java.util.List;

import org.jdom.Document;

import com.ectrip.base.service.iservice.IGenericService;
/**
 * 读取职的SERVICE，从数据库中读取。
 * @author lijin
 *
 */
public interface IOperationService   extends IGenericService {
	/**
	 * 读取Opeation 
	 * @author LiJin
	 *
	 */
	
	
		//读取所有的operation 
		public List getAllOperation() throws Exception ;
		//根据用户读取operation 
		public List getAllEmployeeOperation(String empid) throws Exception;
		//读取菜单下级
		public Document createXMLByBackColumn(String srcpath, String empid,
				BigDecimal popid);
		//读取菜单下级,增加了LIC解板
		
		public Document createXMLByBackColumnLic(String srcpath, String empid,
				BigDecimal popid,String licstr);
		//读取用户及下级
		public List findbyempid(String empid, BigDecimal popid);
		//读取用户及下级
		public List findbyempid(String empid, BigDecimal popid, String licid) ;

}
