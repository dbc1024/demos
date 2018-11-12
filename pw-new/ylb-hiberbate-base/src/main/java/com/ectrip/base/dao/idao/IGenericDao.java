package com.ectrip.base.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ectrip.base.util.PaginationSupport;

/**
 * Copy Right Information : Ectrip 
 * Package : com.ectrip.common.dao.idao 
 * ClassName :IGenericDao.java JDK version used :
 * jdk1.5 User : 李进 
 * Version : Modification history :2009-3-22 上午08:43:05 
 * 基类中I接口 
 * 整个基类中都是按这个接口进行执行的，在SERVICE中只能通过接口进行调用，不能直接实例化DAO类
 * 充分利用SPRING的管理机制。
 */
public interface IGenericDao{

	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	// 根据主键获取实体 若无实体返回 null

	public Object get(Class entityClass, Object id);

	// 更新实体
	public void update(Object entity);

	// 存储实体到数据库
	public void save(Object entity);

	// 增加或更新实体
	public void saveOrUpdate(Object entity);

	// 增加或更新集合中的全部实体
	public void saveOrUpdateAll(Collection<Object> entities);

	// 删除指定的实体
	public void delete(Object entity);

	// 根据主键删除指定实体
	public void deleteByKey(Class entityClass, Object id);

	// 删除集合中的全部实体
	public void deleteAll(Collection<Object> entities);

	// -------------------- HSQL --------------------
	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate(String queryString);

	// 使用带参数的HSQL语句增加、更新、删除实体
	public int bulkUpdate(String queryString, Object[] values);

	// 使用HSQL语句检索数据
	public List find(String queryString);

	// 使用带参数的HSQL语句检索数据
	public List find(String queryString, Object[] values);

	// 使用exampleEntity
	public List findByExample(Object exampleEntity);

	// 使用HSQL语句检索前多少条TOPNUMB数据 queryString hsql语句 topNumb 取多少条记录
	public List findTopNumb(final String queryString, final int topNumb);

	// -------------------- Pagination --------------------
	// 动态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String url);

	// 动态 hsql hsql语句 parameter 替换hsql中的$ pageSize 每页多少条记录 startIndex 第几页 url
	// 链接url
	public PaginationSupport findPage(String hsql, String[] parameter, int pageSize, String startIndex, String url);

	// 静态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url folder 文件夹
	// filename 文件名 filetype 文件类型
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String folder,
			final String filename, final String filetype);

	/**
	 * 
	 * Describe:获取session 这个是个bug 记得关闭session哦 -_-!!!
	 * 
	 * @auth:yangguang
	 * @return return:Session Date:2011-9-28
	 */

	/**
	 * 获取字段最大ID Describe:
	 * 
	 * @auth:yangguang
	 * @param tableName
	 * @param columnName
	 * @return return:Long Date:2011-9-16
	 */

	public Long getMaxPk(String columnName, String tableName);

	/**
	 * 返回服务商列表
	 * 
	 * @param strproviderIds
	 * @return 服务商列表
	 */
//	public List getProviderList(String strproviderIds);

	/**
	 * 根据SQL语句以及参数,返回列表
	 * 
	 * @param hql
	 *            传的SQL语句,HIBNATE格式
	 * @param params
	 *            参数
	 * @return 返回相应的LIST
	 * @throws Exception
	 */
	public List findparams(String hql, Object... params);

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

	/**
	 * 
	 * Describe:获取表中有联合主键的唯一标识的值
	 * 
	 * @auth:yangguang
	 * @param columnName
	 *            要获取最大值得字段
	 * @param keys
	 *            联合主键字段名数组
	 * @param values
	 *            联合主键字段值
	 * @param tableName
	 *            表名
	 * @return return:Long Date:2011-10-10
	 */
	public Long getMaxPk(String columnName, String[] keys, String[] values, String tableName);

	/**
	 * Describe:验证字段值是否唯一 根据必须要匹配的列名
	 * 
	 * @auth:yangguang
	 * @param intKeys
	 *            int型字段的字段名
	 * @param intValues
	 *            int型字段的值
	 * @param keys
	 *            字符串型字段的字段名
	 * @param values
	 *            字符串型字段的值
	 * @param tableName
	 *            表名
	 * @return return:boolean Date:2011-10-17
	 */
	public boolean volidateSole(String[] intKeys, Long[] intValues, String[] keys, String[] values, String tableName);

	/**
	 * 
	 * Describe:根据员工获取此员工可管理的服务商编号
	 * 
	 * @auth:yangguang
	 * @param iemployeeid
	 *            员工编号
	 * @return return:String Date:2011-10-17
	 */
//	public String getControlProvider(String iemployeeid) throws IllegalAccessException, InvocationTargetException;

	/**
	 * 取数据连接,一般情况不要使用
	 * 
	 * @return
	 */
	public Connection getConnection();

	/**
	 * 这个方法与 getConnection 成对使用,不然将会出一链接没有关闭
	 * 
	 * @param conn
	 */
	public void Close(Connection conn);

	/**
	 * *传入sql语句和参数查询（不是hql语句） Describe: 与其他语句一起运行 ，不能关闭session,不能放在最后调用,后面的方法会关闭session
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#findBySqlToMap(java.lang.String, java.lang.Object[])
	 * @param sql
	 * @param params
	 * @return
	 * @author 袁成军 Date:2011-10-11
	 */
	public List<Map> findBySqlToMapnocolsesession(String sql, Object... params) throws Exception;

	/**
	 * 取最大支付号
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String getZhifuMaxNo() throws SQLException;

	/**
	 * 取订单最大编号　
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String getMaxNo(String Saleid) throws SQLException;

	/**
	 * 取最大的36进制编号
	 * 
	 * @param Saleid
	 *            传的字符串
	 * @return
	 * @throws SQLException
	 */
//	public String get36MaxNo(String Saleid) throws SQLException;

	/**
	 * 取竹筏检票序号
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String getZhuFaMaxNo(String tc) throws SQLException;

	/**
	 *那包中还加一个方法，getMaxPk 获取最大主键 Describe:
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
			String tableName);

	/**
	 * 根据HSQL 取 SQL
	 * 
	 * @param hsql
	 * @return
	 */
	public String getHsqlToSql(String hsql);

	/**
	 * 根据SequenceName 取ID
	 * 
	 * @param SequenceName
	 *            ,这个方法如果 Sequence 不存在会报错,如果存在就执行.
	 * @return
	 */
	public Long getSequenceId(String SequenceName) throws Exception;

	/**
	 * 新建 Sequence
	 * 如果 Sequence不存就会新建
	 * @param sequence_name 要创建Sequence的名称。
	 * @throws Exception
	 */
	public void makeRestSequence(String sequence_name) throws Exception;

	/**
	 * 每日会归1，
	 * 
	 * @param SequenceName
	 * @return
	 * @throws Exception
	 */
	public Long getDateRestSequenceId(String SequenceName) throws Exception;
	
	/**
	 * 读取数据库类型
	 * @return
	 */
	public String DataBaseType();
}