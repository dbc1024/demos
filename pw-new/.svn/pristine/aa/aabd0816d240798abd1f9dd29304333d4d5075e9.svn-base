package com.ectrip.base.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
/**
 * Copy Right Information : Ectrip Package : com.ectrip.common.service ClassName :GenericService.java JDK version used :
 * jdk1.5 User : likai Version : Modification history :2009-3-22 上午08:43:51
 */
public class GenericService implements IGenericService {

	IGenericDao genericDao;

	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}
	/**
	 * -------------------- 基本检索、增加、修改、删除操作 -------------------- 根据主键获取实体 若无实体返回 null
	 */
	//
	public Object get(Class entityClass, Object id) {
		return genericDao.get(entityClass, id);
	}

	/**
	 * 更新实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void update(Object entity) {
		genericDao.update(entity);
	}

	/**
	 * 存储实体到数据库
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void save(Object entity) {
		genericDao.save(entity);
	}

	/**
	 * 增加或更新实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveOrUpdate(Object entity) {
		genericDao.saveOrUpdate(entity);
	}

	/**
	 * 增加或更新集合中的全部实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveOrUpdateAll(Collection<Object> entities) {
		genericDao.saveOrUpdateAll(entities);
	}

	/**
	 * 删除指定的实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void delete(Object entity) {
		genericDao.delete(entity);
	}

	/**
	 * 根据主键删除指定实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteByKey(Class entityClass, Object id) {
		genericDao.deleteByKey(entityClass, id);
	}

	/**
	 * 删除集合中的全部实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteAll(Collection<Object> entities) {
		genericDao.deleteAll(entities);
	}

	/**
	 * -------------------- HSQL -------------------- 使用HSQL语句直接增加、更新、删除实体
	 */
	//
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public int bulkUpdate(String queryString) {
		return genericDao.bulkUpdate(queryString);
	}

	/**
	 * 使用带参数的HSQL语句增加、更新、删除实体
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public int bulkUpdate(String queryString, Object[] values) {
		return genericDao.bulkUpdate(queryString, values);
	}

	/**
	 * 使用HSQL语句检索数据
	 */
	public List find(String queryString) {
		return genericDao.find(queryString);
	}

	/**
	 * 使用带参数的HSQL语句检索数据
	 */
	public List find(String queryString, Object[] values) {
		return genericDao.find(queryString, values);
	}

	/**
	 * 使用exampleEntity
	 */
	public List findByExample(Object exampleEntity) {
		return genericDao.findByExample(exampleEntity);
	}

	/**
	 * 使用HSQL语句检索前多少条TOPNUMB数据 queryString hsql语句 topNumb 取多少条记录
	 */
	public List findTopNumb(final String queryString, final int topNumb) {
		return genericDao.findTopNumb(queryString, topNumb);
	}

	/**
	 * 
	 * -------------------- Pagination -------------------- 动态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url
	 */
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String url) {
		return genericDao.findPage(hsql, pageSize, startIndex, url);
	}

	/**
	 * 
	 * 动态 hsql hsql语句 parameter 替换hsql中的$ pageSize 每页多少条记录 startIndex 第几页 url 链接url
	 */
	public PaginationSupport findPage(String hsql, String[] parameter, int pageSize, String startIndex, String url) {
		return genericDao.findPage(hsql, parameter, pageSize, startIndex, url);
	}

	/**
	 * 
	 *静态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url folder 文件夹 filename 文件名 filetype 文件类型
	 * 
	 */
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String folder,
			final String filename, final String filetype) {
		return genericDao.findPage(hsql, pageSize, startIndex, folder, filename, filetype);
	}

	/**
	 * 直接取数据为联接
	 */
	public Connection getConnection() {
		return genericDao.getConnection();
	}

	/**
	 * 直接关闭数据联接
	 */
	public void Close(Connection conn) {
		genericDao.Close(conn);
	}

	/**
	 * 获取字段最大ID Describe:
	 * 
	 * @auth:yangguang
	 * @param columnName
	 * @param tableName
	 * @return return:Long Date:2011-9-16
	 */
	public Long getMaxPk(String columnName, String tableName) {
		return genericDao.getMaxPk(columnName, tableName);
	}

	/**
	 * 获取服务商列表
	 * 
	 * @param strproviderIds
	 *            服务商ID 以,隔开 如:1,2,3
	 * @return 返回服务商列表
	 */
	/*public List getProviderList(String strproviderIds) {
		return genericDao.getProviderList(strproviderIds);
	}*/

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
	public boolean volidateSole(String[] intKeys, Long[] intValues, String[] keys, String[] values, String tableName) {
		return genericDao.volidateSole(intKeys, intValues, keys, values, tableName);
	}

	/**
	 * 
	 * Describe:根据员工获取此员工可管理的服务商编号
	 * 
	 * @auth:yangguang
	 * @param iemployeeid
	 *            员工编号
	 * @return return:String Date:2011-10-17
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	/*public String getControlProvider(String iemployeeid) throws IllegalAccessException, InvocationTargetException {
		return genericDao.getControlProvider(iemployeeid);
	}*/

	/**
	 * 
	 * Describe:获取组织架构书
	 * 
	 * @auth:yangguang
	 * @param id
	 *            节点ID
	 * @param type
	 *            节点类型 0:公司 1:部门 2:岗位
	 * @param icompanyid
	 *            公司ID 只在查询员工时用到
	 * @param deptid
	 *            部门ID 只在查询员工时用到
	 * @return return:List Date:2011-10-22
	 */
	/*public List getCompanyTree(String id, String type, String icompanyid, String deptid) {
		// 1 如果是平台企业则 显示全部 企业
		// 2 如果是景区企业则显示企业及下级企业以及部门
		StringBuffer hql = new StringBuffer();
		StringBuffer hql1 = new StringBuffer();
		StringBuffer hql2 = new StringBuffer();
		StringBuffer hql3 = new StringBuffer();
		List list = null;
		List list1 = null;
		List list2 = null;
		List list3 = null;
		if (type.equals("0")) {// 企业
			hql
					.append("select new map(icompanyinfoid as id,szcompanyname as name,0 as type,'true' as hasnext,'icon01' as iconSkin) from Galcompanyinfotab where 1=1");
			if (!id.equals("0")) {
				Galcompanyinfotab company = (Galcompanyinfotab) genericDao.get(Galcompanyinfotab.class, new Long(id));
				if (company != null) {
					hql.append(" and szinfocomid=" + id);
				} else {
					hql.append(" and icompanyinfoid=" + id);
				}
			} else {
				hql.append(" and szinfocomid=0");
			}
			// 查询企业
			list = genericDao.find(hql.toString());

			if (list != null && list.size() > 0) {
				Map dateMap = null;
				for (int i = 0; i < list.size(); i++) {
					hql1.delete(0, hql1.length());
					hql2.delete(0, hql2.length());
					hql3.delete(0, hql3.length());
					dateMap = (Map) list.get(i);
					// 查询子企业
					hql1
							.append("select new map(icompanyinfoid as id,szcompanyname as name,szinfocomid as pid,0 as type,'true' as hasnext,'icon01' as iconSkin) from Galcompanyinfotab where 1=1 and szinfocomid="
									+ Integer.parseInt(dateMap.get("id").toString()));

					// 查询部门
					hql2
							.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) from Esfdepttab where bisdelete=0 and  iparentid=0 and byisuse=1 and icompanyinfoid="
									+ Integer.parseInt(dateMap.get("id").toString()));
					// 部门列表
					list2 = genericDao.find(hql2.toString());
					// 企业列表
					list1 = genericDao.find(hql1.toString());

					// 如果不是第一级,需要把查询出来的子企业和部门合并 他们属于同一级别
					if (!id.equals("0")) {
						if (list2 != null && list2.size() > 0) {
							list.addAll(list2);
						}
					}
				}
			}
			hql1.delete(0, hql1.length());
			hql1
					.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) from Esfdepttab where bisdelete=0  and  iparentid=0 and byisuse=1 and icompanyinfoid="
							+ Integer.parseInt(id.toString()));
			list1 = genericDao.find(hql1.toString());
			// 如果部门不为空 则循环判断部门下的岗位
			if (list1 != null) {
				list.addAll(list1);
			}
		} else if (type.equals("1")) {// 部门
			// 根据企业编号查询部门
			hql
					.append("select new map(ideptid as id,szdeptname as name,1 as type,icompanyinfoid as icompanyid,'true' as hasnext,'icon02' as iconSkin) from  Esfdepttab where bisdelete=0 and byisuse=1 and iparentid="
							+ id);
			// 部门列表
			list = genericDao.find(hql.toString());
			// 如果查询的是一级部门 因为是根据公司ID获取的,所以不应该查询岗位列表
			Esfdepttab dept = (Esfdepttab) genericDao.get(Esfdepttab.class, new Long(id));

			// 根据部门编号查询岗位列表
			hql1
					.append("select new map(post.ipostsid as id,post.szpostsname as name,3 as type,dp.icompanyinfoid as icompanyid,dp.ideptid as deptid,'true' as hasnext,'icon02' as iconSkin) from Esfdeptpoststab  dept,Esppoststab  post,Esfdepttab dp where dp.ideptid=dept.ideptid and dept.ipostsid=post.ipostsid and dp.ideptid="
							+ id);
			// 岗位列表
			list1 = genericDao.find(hql1.toString());
			// hql2.append("select new map(ideptid as ideptid,szdeptname as szdeptname,1 as type) from Esfdepttab where isdelete=0 and byisuse=1 and iparentid=0 and icompanyinfoid="+id);
			// 如果部门下有岗位则加上去
			if (list1 != null && list1.size() > 0) {
				list.addAll(list1);
			}
		} else {// 员工
			// 根据岗位编号 公司编号 部门编号获取员工列表
			hql1
					.append("select new map(emp.iemployeeid as id,emp.szemployeename as name,'icon03' as iconSkin) from Esfemployeetab emp,Esfemployeepoststab empost,Esfdeptpoststab deptpost where emp.icompanyinfoid="
							+ icompanyid
							+ " and emp.ideptid="
							+ deptid
							+ " and emp.ideptid=deptpost.ideptid and empost.iemployeeid=emp.iemployeeid and deptpost.ipostsid=empost.ipostsid and empost.ipostsid="
							+ id);
			list = genericDao.find(hql1.toString());
		}
		return list;
	}*/

	/***
	 * Describe:查询需要的值通过传过来的selectKeys以 ,间隔,还需要传入查询时条件字段名和条件字段值 分数值字段和字符字段
	 * 
	 * @auth:yangguang
	 * @param selectKey
	 *            要查询的字段名以,隔开
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
	public List getJsonDate(String[] keys, String[] longKeys, String[] longValues, String[] stringKeys,
			String[] stringValues, String tableName) {
		StringBuffer hql = new StringBuffer();
		if (keys != null && keys.length > 0) {
			hql.append("select new map(");
			for (int j = 0; j < keys.length; j++) {
				hql.append("" + keys[j] + " as " + keys[j] + "");
				if (j < keys.length - 1) {
					hql.append(",");
				}
			}
			hql.append(") ");
		}
		hql.append(" from " + tableName + " where 1=1");
		if (stringValues != null && stringValues.length > 0) {
			for (int i = 0; i < stringValues.length; i++) {
				if (stringValues[i] != null && !stringValues[i].equals("")) {
					hql.append(" and " + stringKeys[i] + "='" + stringValues[i] + "'");
				}
			}
		}
		if (longValues != null && longValues.length > 0) {
			for (int i = 0; i < longValues.length; i++) {
				if (longKeys[i] != null && !longValues[i].equals("")) {
					hql.append(" and " + longKeys[i] + "=" + longValues[i] + "");
				}
			}
		}
		List list = genericDao.find(hql.toString());
		return list;
	}

	/**
	 * 取最大支付号
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public String getMaxNo(String Saleid) throws SQLException {
		return genericDao.getMaxNo(Saleid);
	}

	/**
	 * 取订单最大编号　
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*public String getZhifuMaxNo() throws SQLException {
		return genericDao.getZhifuMaxNo();
	}*/

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
			String tableName) {
		// TODO Auto-generated method stub
		return genericDao.getMaxPk(columnName, intKeys, intValues, keys, values, tableName);
	}

	/**
	 * 
	 * 根据SequenceId取ID,必须要建立Sequence
	 * @throws Exception
	 *            
	 */
	
	public Long getSequenceId(String SequenceName) throws Exception {
		// TODO Auto-generated method stub
		
		
		return genericDao.getSequenceId(SequenceName);
	}

	/**
	 * 读取数据库类型
	 */
	
	public String DataBaseType() {
		// TODO Auto-generated method stub
		return genericDao.DataBaseType();
	}
    /**
     * 运行SQL ，关闭SESSION
     * 
     */
	public List<Map> findBySqlToMapCloseSession(String sql, Object... params)
			throws Exception {
		// TODO Auto-generated method stub
		return genericDao.findBySqlToMapnocolsesession(sql, params);
	}

	@Override
	public List<Map> findBySqlToMap(String sql, Object... params) throws Exception {
		// TODO Auto-generated method stub
		return genericDao.findBySqlToMap(sql, params);
	}

	@Override
	public String getZhifuMaxNo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
