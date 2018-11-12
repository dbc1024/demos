package com.ectrip.base.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ectrip.base.util.PaginationSupport;

/**
 * Copy Right Information : Ectrip Package : com.ectrip.common.service.iservice ClassName :IGenericService.java JDK
 * version used : jdk1.5 User : likai Version : Modification history :2009-3-22 上午08:43:21
 */
@SuppressWarnings("unchecked")
public interface IGenericService {
	// extends org.springframework.aop.framework.Advised
	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	/** 
	 * 根据主键获取实体 若无实体返回 null */
	public Object get(Class entityClass, Object id);

	/**
	 *  更新实体
	 * @param entity
	 */
	public void update(Object entity);

	/**
	 *  存储实体到数据库
	 * @param entity
	 */
	public void save(Object entity);

	/**
	 *  增加或更新实体
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);

	/**
	 *  增加或更新集合中的全部实体
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<Object> entities);

	/**
	 *  删除指定的实体
	 * @param entity
	 */
	public void delete(Object entity);

	/**
	 *  根据主键删除指定实体
	 * @param entityClass
	 * @param id
	 */
	public void deleteByKey(Class entityClass, Object id);

	/**
	 *  删除集合中的全部实体
	 * @param entities
	 */
	public void deleteAll(Collection<Object> entities);

	/** 
	 * -------------------- HSQL --------------------
	 使用HSQL语句直接增加、更新、删除实体 */
	public int bulkUpdate(String queryString);

	/** 
	 * 使用带参数的HSQL语句增加、更新、删除实体
	 * @param queryString
	 * @param values
	 * @return
	 */
	public int bulkUpdate(String queryString, Object[] values);

	/** 
	 * 使用HSQL语句检索数据
	 * @param queryString
	 * @return
	 */
	public List find(String queryString);

	/** 
	 * 使用带参数的HSQL语句检索数据
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List find(String queryString, Object[] values);

	/**
	 * 运行普通的SQL语句
	 * 
	 * @param sql
	 *            传的SQL语句
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public List<Map> findBySqlToMapCloseSession(String sql, Object... params) throws Exception;
	
	/** 
	 * 使用exampleEntity
	 * @param exampleEntity
	 * @return
	 */
	public List findByExample(Object exampleEntity);

	// 使用HSQL语句检索前多少条TOPNUMB数据 queryString hsql语句 topNumb 取多少条记录
	public List findTopNumb(final String queryString, final int topNumb);
	
	/**
	 * 运行普通的SQL语句
	 * 
	 * @param sql
	 *            传的SQL语句
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public List<Map> findBySqlToMap(String sql, Object... params) throws Exception;

	/** -------------------- Pagination --------------------
	 动态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url
	 */
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String url);

	/**
	 *  动态 hsql hsql语句 parameter 替换hsql中的$ pageSize 每页多少条记录 startIndex 第几页 url
	 * @param hsql
	 * @param parameter
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	
	public PaginationSupport findPage(String hsql, String[] parameter, int pageSize, String startIndex, String url);

	/**
	 *  静态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url folder 文件夹
	 * @param hsql
	 * @param pageSize
	 * @param startIndex
	 * @param folder
	 * @param filename
	 * @param filetype
	 * @return
	 */
	// filename 文件名 filetype 文件类型
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String folder,
			final String filename, final String filetype);

	public Connection getConnection();

	public void Close(Connection conn);

	/***
	 * 获取字段最大ID Describe:
	 * 
	 * @auth:yangguang
	 * @param tableName
	 * @param columnName
	 * @return return:Long Date:2011-9-16
	 */
	public Long getMaxPk(String columnName, String tableName);

	
	
	/***
	 * 获取服务商列表
	 * @param strproviderIds 服务商ID 以,隔开 如:1,2,3
	 * @return 返回服务商列表
	 */
//	public List getProviderList(String strproviderIds);
	
	
	
	/**
	 * Describe:验证字段值是否唯一 根据必须要匹配的列名
	 * @auth:yangguang
	 * @param intKeys int型字段的字段名
	 * @param intValues int型字段的值
	 * @param keys 字符串型字段的字段名 
	 * @param values 字符串型字段的值
	 * @param tableName 表名
	 * @return
	 * return:boolean
	 * Date:2011-10-17
	 */
	public boolean volidateSole(String[] intKeys,Long[] intValues,String[] keys, String[] values,
			String tableName);
	
	
	
	/***
	 * 
	 * Describe:根据员工获取此员工可管理的服务商编号
	 * @auth:yangguang
	 * @param iemployeeid 员工编号
	 * @return
	 * return:String
	 * Date:2011-10-17
	 */
//	public String getControlProvider(String iemployeeid) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * Describe:获取组织架构书
	 * @auth:yangguang
	 * @param id 节点ID
	 * @param type 节点类型 0:公司 1:部门  2:岗位
	 * @param icompanyid 公司ID 只在查询员工时用到
	 * @param deptid 部门ID 只在查询员工时用到
	 * @return
	 * return:List
	 * Date:2011-10-22
	 */
//	public List getCompanyTree(String id, String type, String icompanyid, String deptid);
	
	
	/***
	 * Describe:查询需要的值通过传过来的selectKeys以 ,间隔,还需要传入查询时条件字段名和条件字段值 分数值字段和字符字段
	 * 
	 * @auth:yangguang
	 * @param selectKey
	 * @param longKeys
	 *            数值型字段字段名
	 * @param longValues
	 *            数值型条件字段值
	 * @param stringKeys
	 *            字符型条件字段名
	 * @param stringValues
	 *            字符型条件字段值
	 * @param tableName
	 *            表名
	 * @return return:返回数据列表 Date:2011-10-19
	 */
	public List getJsonDate(String[] keys, String[] longKeys, String[] longValues,
			String[] stringKeys, String[] stringValues, String tableName);
	
	/**
	 * 取最大支付号
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String getZhifuMaxNo() throws SQLException ;
	/**
	 * 取订单最大编号　
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String getMaxNo(String Saleid) throws SQLException ;
	
	/**
	 * 李总，那包中还加一个方法，getMaxPk 获取最大主键 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param columnName
	 *            要获最值的列名
	 * @param intKeys
	 *            数字类型的参数
	 * @param intValues
	 *            数字类型字段的值
	 * @param keys
	 *            字符类型的参数
	 * @param values
	 *            字符类型字段的值班
	 * @param tableName
	 *            表名
	 * @return return:boolean Date:2011-11-7
	 */
	
	public Long getMaxPk(String columnName, String[] intKeys, Long[] intValues, String[] keys, String[] values,
			String tableName) ;
	
	/**
	 * 根据SequenceName 取ID
	 * @param SequenceName
	 * @return
	 */
	public Long getSequenceId(String SequenceName ) throws Exception;
	/**
	 * 读取数据库类型
	 * @return
	 */
	public String DataBaseType();
}
