package com.ectrip.base.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.spi.FilterTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.dao.idao.IGenericJdbcDao;
import com.ectrip.base.model.Maxorno;
import com.ectrip.base.model.Zfmaxorno;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
/**
 * 基类 IGenericDao接口的实现类，
 * Copy Right Information : 
 * Package : com.ectrip.common.dao ClassName :
 * GenericDao.java JDK version used : jdk1.5
 * User : 李进 
 * Version : Modification history :2009-3-22 上午08:42:55
 * 修改说明，增加了当了SEQ后，每日取号后，删除3天前的编号；
 * 
 */
@SuppressWarnings("unchecked")
public class GenericDao extends HibernateDaoSupport implements IGenericDao {

	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	@Resource
    public void setSessionFactoryOverride(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	// 根据主键获取实体 若无实体返回 null
	public Object get(Class entityClass, Object id) {
		return getHibernateTemplate().get(entityClass, (Serializable) id);
	}

	// 更新实体
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	// 存储实体到数据库
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	// 增加或更新实体
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// 增加或更新集合中的全部实体
	@Deprecated
	public void saveOrUpdateAll(Collection<Object> entities) {
		getHibernateTemplate().saveOrUpdate(entities);
	}

	// 删除指定的实体
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	// 根据主键删除指定实体
	public void deleteByKey(Class entityClass, Object id) {
		this.delete(this.get(entityClass, id));
	}

	// 删除集合中的全部实体
	public void deleteAll(Collection<Object> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	// -------------------- HSQL --------------------
	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate(String queryString) {
		return getHibernateTemplate().bulkUpdate(queryString);
	}

	// 使用带参数的HSQL语句增加、更新、删除实体
	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	// 使用HSQL语句检索数据
	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	// 使用带参数的HSQL语句检索数据
	public List find(String queryString, Object[] values) {
		System.out.println("template:" + getHibernateTemplate() + "values:" + values);
		return getHibernateTemplate().find(queryString, values);
	}

	// 使用exampleEntity
	public List findByExample(Object exampleEntity) {
		return getHibernateTemplate().findByExample(exampleEntity);
	}

	// 使用HSQL语句检索前多少条TOPNUMB数据 queryString hsql语句 topNumb 取多少条记录
	@SuppressWarnings("deprecation")
	public List findTopNumb(final String queryString, final int topNumb) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(queryString);
				query.setFetchSize(0);
				query.setMaxResults(topNumb);
				List list = query.list();
				return list;
			}
		});
	}

	// -------------------- Pagination --------------------
	// 动态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url
	@SuppressWarnings("deprecation")
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String url) {
		return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				// 2011-12-16 modify
				int totalCount = 0;

				// int fromlen = hsql.toLowerCase().indexOf(" from ");
				// int fromlen1 = hsql.toLowerCase().indexOf("from ");
				//
				// fromlen = fromlen != 0 ? fromlen : fromlen1;
				//
				// String sql = hsql.substring(fromlen1);

				SessionFactoryImpl sfi = (SessionFactoryImpl) getHibernateTemplate().getSessionFactory();
				QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
				FilterTranslator qt = qtf.createFilterTranslator(hsql, hsql, Collections.EMPTY_MAP, sfi);
				qt.compile(Collections.EMPTY_MAP, false);
				 System.out.println(qt.getSQLString());
				//求出整个SQL语句中的所有行数据，不能通过行滚动来求总行数。
				Query query1 = session.createSQLQuery("select   count(*) as  totalCount from (" + qt.getSQLString()
						+ ")  a");
				List items1 = query1.list();
				if (items1 != null && items1.size() > 0) {
					if (items1.size() == 0) {
						totalCount = 0;
					} else {
						totalCount = Integer.parseInt(items1.get(0).toString());
					}
				} else {
					totalCount = 0;
				}

				Query query = session.createQuery(hsql);

				/*
				 * // System.out.println("  query.getQueryString()"); ScrollableResults scrollableResults =
				 * query.scroll(); // long totalResults = 0; scrollableResults.last();
				 * 
				 * if (scrollableResults.getRowNumber() >= 0) { totalCount = scrollableResults.getRowNumber() + 1; }
				 * else { totalCount = 0; }
				 */
				// totalCount = totalResults;
				query.setFirstResult(pageSize * (startIndex - 1));

				query.setMaxResults(pageSize);
				List items = query.list();

				PaginationSupport ps = new PaginationSupport(items, totalCount, startIndex, pageSize, url);
				return ps;
			}
		});
	}

	// 动态 hsql hsql语句 parameter 替换hsql中的$ pageSize 每页多少条记录 startIndex 第几页 url
	// 链接url
	public PaginationSupport findPage(String hsql, String[] parameter, int pageSize, String startIndex, String url) {
		int count = 0, start = 0;
		while (start != hsql.length()) {
			int i = hsql.indexOf("$", start);
			if (i != -1) {
				count++;
				start = i + 1;
			} else {
				break;
			}
		}
		if (count == 0) {
			return this.findPage(hsql, pageSize, Integer.parseInt(startIndex), url);
		} else {
			if (parameter == null) {
				return null;
			} else {
				if (count == parameter.length) {
					for (int i = 0; i < parameter.length; i++) {
						hsql = Tools.replaceFirst(hsql, "$", parameter[i]);
					}
					return this.findPage(hsql, pageSize, Integer.parseInt(startIndex), url);
				} else {
					return null;
				}
			}
		}
	}

	// 静态 hsql hsql语句 pageSize 每页多少条记录 startIndex 第几页 url 链接url folder 文件夹
	// filename 文件名 filetype 文件类型
	@SuppressWarnings("deprecation")
	public PaginationSupport findPage(final String hsql, final int pageSize, final int startIndex, final String folder,
			final String filename, final String filetype) {
		return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hsql);
				int totalCount = 0;
				SessionFactoryImpl sfi = (SessionFactoryImpl) getHibernateTemplate().getSessionFactory();
				QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
				FilterTranslator qt = qtf.createFilterTranslator(hsql, hsql, null, sfi);

				qt.compile(null, false);

				Query query1 = session.createSQLQuery("select   count(*) as  totalCount from (" + qt.getSQLString()
						+ ")  a");

				// System.out.println(qt.getSQLString());

				List items1 = query1.list();
				if (items1 != null && items1.size() > 0) {
					if (items1.size() == 0) {
						totalCount = 0;
					} else {
						totalCount = Integer.parseInt(items1.get(0).toString());
					}
				} else {
					totalCount = 0;
				}

				query.setFirstResult(pageSize * (startIndex - 1));
				query.setMaxResults(pageSize);
				List items = query.list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, startIndex, pageSize, folder, filename,
						filetype);
				return ps;
			}
		});
	}
	/**
	 * 根据SQL取出对应的SQL的条数
	 * @param session
	 * @param hsql
	 * @return
	 */
	@SuppressWarnings("unused")
	private long getTotalCount(Session session,String hsql)
	{
		int totalCount = 0;
		SessionFactoryImpl sfi = (SessionFactoryImpl) getHibernateTemplate().getSessionFactory();
		QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
		FilterTranslator qt = qtf.createFilterTranslator(hsql, hsql, null, sfi);
	
		qt.compile(null, false);
	
		Query query1 = session.createSQLQuery("select   count(*) as  totalCount from (" + qt.getSQLString()
				+ ")  a");
	
		// System.out.println(qt.getSQLString());
	
		List items1 = query1.list();
		if (items1 != null && items1.size() > 0) {
			if (items1.size() == 0) {
				totalCount = 0;
			} else {
				totalCount = Integer.parseInt(items1.get(0).toString());
			}
		} else {
			totalCount = 0;
		}
		return totalCount;
	}
	/**
	 * 
	 * Describe:获取表中某列最大值
	 * 
	 * @auth:yangguang
	 * @param tableName
	 * @param columnName
	 * @return return:Long Date:2011-9-16
	 */
	public Long getMaxPk(String columnName, String tableName) {
		String hql = "select new map( max(" + columnName + ") as "+columnName+") from " + tableName + "";
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			if (list.get(0) == null) {
				return new Long(0);
			} else {
				Map columnToValue = (HashMap)list.get(0);
				Object object = columnToValue.get(columnName);
				if(object==null) {
					return 0L;
				}
				return (Long) columnToValue.get(columnName);
			}
		} else {
			return new Long(0);
		}
	}

	@SuppressWarnings("deprecation")
	public Connection getConnection() {
		
		try {
			return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void Close(Connection conn) {
		try {
			if (conn == null) {
				return;
			} else if (conn.isClosed() == false) {
				conn.close();
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * *多参数和hql查询 Describe:
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#findparams(java.lang.String, java.lang.Object[])
	 * @param hql
	 * @param params
	 * @return
	 * @author yangguang Date:2011-10-11
	 */
	public List findparams(final String hql, final Object... params) {

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// String querySentence = "FROM Employee_sort_info";
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}

				return query.list();
			}
		});

	}

	/**
	 * * Describe: 获取服务商列表
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#getProviderList(java.lang.String)
	 * @param strproviderIds
	 *            服务商ID列表 如:1,2,3
	 * @return
	 * @author yangguang Date:2011-9-29
	 */
	public List getProviderList(String strproviderIds) {
		String hql = "select new map(provider.iscenicid as iscenicid,provider.irootid as irootid,provider.iparentid as iparentid,provider.ilevelsequence as ilevelsequence,provider.bisleaf as bisleaf,provider.szinnerid as szinnerid,provider.szinnercode as szinnercode,provider.szinnername as szinnername,provider.szsceniccode as szsceniccode,provider.szregionalid as szregionalid,provider.scenictype as scenictype,provider.szscenicname as szscenicname,provider.szgrade as szgrade) from Esbscenicareatab provider where 1=1";
		if (strproviderIds != null && !strproviderIds.equals("")) {
			hql += " and provider.iscenicid in(" + strproviderIds + ")";
		}
		return find(hql);
	}

	/**
	 * *传入sql语句和参数查询（不是hql语句） Describe:
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#findBySqlToMap(java.lang.String, java.lang.Object[])
	 * @param sql
	 * @param params
	 * @return
	 * @author yangguang Date:2011-10-11
	 */
	public List<Map> findBySqlToMap(final String sql, final Object... params) throws Exception {

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// String querySentence = "FROM Employee_sort_info";
				Query query = session.createSQLQuery(sql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				// System.out.println(list.size());
				return list;
			}
		});
	}
	
	
	/**
	 * *传入sql语句和参数查询（不是hql语句） Describe:
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#findBySqlToMapCloseSession
	 * (java.lang.String, java.lang.Object[])
	 * @param sql
	 * @param params
	 * @return
	 * 关闭 SESSION
	 * @author yangguang Date:2014-07-15
	 */
	public List<Map> findBySqlToMapCloseSession(final String sql, final Object... params) throws Exception {

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// String querySentence = "FROM Employee_sort_info";
				Query query = session.createSQLQuery(sql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				// System.out.println(list.size());
				session.close();  //关闭数据链接
				
				return list;
			}
		});
	}

	/**
	 * * Describe:联合主键获取最大编号值
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#getMaxPk(java.lang.String, java.lang.String[], java.lang.String[],
	 *      java.lang.String)
	 * @param columnName
	 * @param keys
	 * @param values
	 * @param tableName
	 * @return
	 * @author yangguang Date:2011-10-10
	 * 李进修改；
	 */
	public Long getMaxPk(String columnName, String[] keys, String[] values, String tableName) {
		String hql = "select max(" + columnName + ") from " + tableName + " where   1=1 ";
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				hql += " and " + keys[i] + "=" + values[i] + "";
			}
		}
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			if (list.get(0) == null) {
				return new Long(0);
			} else {
				return (Long) list.get(0);
			}
		} else {
			return new Long(0);
		}
	}

	/**
	 * 
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
		String hql = "from " + tableName + " where 1=1";
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				hql += " and " + keys[i] + "='" + values[i] + "'";
			}
		}
		if (intKeys != null && intKeys.length > 0) {
			for (int i = 0; i < intKeys.length; i++) {
				hql += " and " + intKeys[i] + "=" + intValues[i] + "";
			}
		}
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * *传入sql语句和参数查询（不是hql语句） 
	 * Describe: 与其他语句一起运行 ，不能关闭session,不能放在最后调用,后面的方法会关闭session
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#findBySqlToMap(java.lang.String, java.lang.Object[])
	 * @param sql
	 * @param params
	 * @return
	 * @author 袁成军 Date:2011-10-11
	 */
	public List<Map> findBySqlToMapnocolsesession(String sql, Object... params) throws Exception {
		return this.findBySqlToMap(sql, params);
	}

	/***
	 * * Describe:根据员工获取此员工可管理的服务商编号
	 * 
	 * @see com.ectrip.base.dao.idao.IGenericDao#getControlProvider(java.lang.String)
	 * @param iemployeeid
	 *            员工编号
	 * @return
	 * @author yangguang Date:2011-10-17
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	/*public String getControlProvider(String iemployeeid) throws IllegalAccessException, InvocationTargetException {
		Esfemployeetab employee = (Esfemployeetab) get(Esfemployeetab.class, new Long(iemployeeid));
		List comlist = find("from Companyscenic csc,Esbscenicareatab provider where csc.id.iscenicid=provider.iscenicid and provider.byisuse=1 and  csc.id.icompanyinfoid="
				+ employee.getIcompanyinfoid());
		String scenics = "";
		Companyscenic c = null;
		for (int i = 0; i < comlist.size(); i++) {
			c = new Companyscenic();
			Object[] o = (Object[]) comlist.get(i);
			c = (Companyscenic) o[0];
			// BeanUtils.populate(c, (Companyscenic)o[0]);
			if (i == 0) {
				scenics = c.getId().getIscenicid().toString();
			} else {
				scenics = scenics + "," + c.getId().getIscenicid().toString();
			}
		}

		employee.setScenics(scenics);
		return employee.getScenics();
	}*/

	/**
	 * 取最大支付号
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized String getZhifuMaxNo() throws SQLException {

		String orda = Tools.getTodayString();
		String sql = "  from Zfmaxorno where orda='" + orda + "'";
		String orid = "", newmaxorno = "";
		System.out.println("  new zhifau ");
		try {

			List list = this.find(sql);

			String maxorno = "";
			if (list.size() < 1) {
				maxorno = "1";
				Zfmaxorno zmaxorno = new Zfmaxorno();
				zmaxorno.setOrda(orda);
				zmaxorno.setOrno(maxorno);
				this.save(zmaxorno);
				// 初始化 Sequence,取支付用的
				// MakeOridsequence.makeZhuFuSequence();
				makeRestSequence("zhifu_sequence");

				return "ZF" + orda.substring(2, 4) + orda.substring(5, 7) + orda.substring(8) + "000001";
			} else {

				List<Map> iserialnumlist = findBySqlToMapnocolsesession("select zhifu_sequence.nextval  from dual");
				Long iserialnum = new Long((((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());

				// 如果大于 1000000
				newmaxorno = Tools.ConvertTo10Text(iserialnum, 0);
				// 转在36进制
				if (newmaxorno.length() > 6) {
					// 如果36进制大于 6 位,系统不支持.
					throw new Exception("超过当日最大单号");
				} else {
					// 如果36进行小于等于 6 ,前面补0
					newmaxorno = "000000".substring(newmaxorno.length()) + newmaxorno;
				}
				orid = newmaxorno;
				if (newmaxorno.equals("")) {
					throw new Exception("超过当日最大支付号");
				} else {
					return "ZF" + orda.substring(2, 4) + orda.substring(5, 7) + orda.substring(8) + newmaxorno;

				}

			}
		} catch (Exception sqle) {
			System.out.println("SQLException" + sqle.getMessage());

		}
		return orid;

	}

	/**
	 * 取最大支付号，10进制
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*public synchronized String getZhifuMaxNo10() throws SQLException {

		String orda = Tools.getTodayString();
		String sql = "  from Zfmaxorno where orda='" + orda + "'";
		String orid = "";
		try {

			List list = this.find(sql);

			String maxorno = "";
			if (list.size() < 1) {
				maxorno = "000001";
				Zfmaxorno zmaxorno = new Zfmaxorno();
				zmaxorno.setOrda(orda);
				zmaxorno.setOrno(maxorno);

				this.save(zmaxorno);
				return "ZF" + orda.substring(2, 4) + orda.substring(5, 7) + orda.substring(8) + "000001";
			} else {
				Zfmaxorno zmaxorno = (Zfmaxorno) list.get(0);
				if (zmaxorno == null) {
					maxorno = "000001";
					zmaxorno = new Zfmaxorno();
					zmaxorno.setOrda(orda);
					zmaxorno.setOrno(maxorno);

					this.save(zmaxorno);
					return "ZF" + orda.substring(2, 4) + orda.substring(5, 7) + orda.substring(8) + "000001";

				} else {
					maxorno = zmaxorno.getOrno();
					String newmaxorno = "";
					int intmaxorno = Integer.parseInt(maxorno);
					intmaxorno = intmaxorno + 1;
					if (intmaxorno < 10) {
						newmaxorno = "00000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 10 && intmaxorno < 100) {
						newmaxorno = "0000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 100 && intmaxorno < 1000) {
						newmaxorno = "000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 1000 && intmaxorno < 10000) {
						newmaxorno = "00" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 10000 && intmaxorno < 100000) {
						newmaxorno = "0" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 100000 && intmaxorno < 1000000) {
						newmaxorno = String.valueOf(intmaxorno);
					} else {
						return "";
					}

					zmaxorno.setOrno(newmaxorno);
					this.update(zmaxorno);

					if (newmaxorno.equals("")) {
						throw new Exception("超过当日最大支付单号");
					} else {

						orid = "ZF" + orda.substring(2, 4) + orda.substring(5, 7) + orda.substring(8) + newmaxorno;

					}

				}

			}
		} catch (Exception sqle) {
			System.out.println("SQLException" + sqle.getMessage());

		}
		return orid;

	}*/

	/**
	 * 取订单最大编号　36进制
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*public synchronized String get36MaxNo(String Saleid) throws SQLException {

		String orda = Tools.getTodayString();
		String sql = "  from Maxorno where orda='" + orda + "'";
		String orid = "";
		try {

			List list = this.find(sql);

			String maxorno = "";
			if (list ==null || list.size() < 1) {
				maxorno = "000001";
				Maxorno zmaxorno = new Maxorno();
				zmaxorno.setOrda(orda);
				zmaxorno.setOrno(maxorno);

				this.save(zmaxorno);
				return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + "000001";
			} else {
				Maxorno zmaxorno = (Maxorno) list.get(0);
				if (zmaxorno == null) {
					maxorno = "000001";
					zmaxorno = new Maxorno();
					zmaxorno.setOrda(orda);
					zmaxorno.setOrno(maxorno);

					this.save(zmaxorno);
					return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + "000001";

				} else {
					maxorno = zmaxorno.getOrno();
					String newmaxorno = "";
					int intmaxorno = Integer.parseInt(maxorno);
					intmaxorno = intmaxorno + 1;
					if (intmaxorno < 10) {
						newmaxorno = "00000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 10 && intmaxorno < 100) {
						newmaxorno = "0000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 100 && intmaxorno < 1000) {
						newmaxorno = "000" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 1000 && intmaxorno < 10000) {
						newmaxorno = "00" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 10000 && intmaxorno < 100000) {
						newmaxorno = "0" + String.valueOf(intmaxorno);
					} else if (intmaxorno >= 100000 && intmaxorno < 1000000) {
						newmaxorno = String.valueOf(intmaxorno);
					} else {
						return "";
					}

					zmaxorno.setOrno(newmaxorno);
					this.update(zmaxorno);

					if (newmaxorno.equals("")) {
						throw new Exception("超过当日最大支付单号");
					} else {

						return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + newmaxorno;

					}

				}

			}
		} catch (Exception sqle) {
			System.out.println("SQLException" + sqle.getMessage());

		}
		return orid;

	}*/

	/**
	 * 取订单最大编号　
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized String getMaxNo1(String Saleid) throws SQLException {

		String orda = Tools.getTodayString();
		String sql = "  from Maxorno where orda='" + orda + "'";
		String orid = "";
		String newmaxorno = "";
		try {

			List list = this.find(sql);

			String maxorno = "";
			if (list.size() < 1) {
				maxorno = "1";
				Maxorno zmaxorno = new Maxorno();
				zmaxorno.setOrda(orda);
				zmaxorno.setOrno(maxorno);
				this.save(zmaxorno);

				return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + "000001";
			} else {
				Maxorno zmaxorno = (Maxorno) list.get(0);
				if (zmaxorno == null) {
					maxorno = "1";
					zmaxorno = new Maxorno();
					zmaxorno.setOrda(orda);
					zmaxorno.setOrno(maxorno);

					this.save(zmaxorno);
					return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + "000001";

				} else {
					maxorno = zmaxorno.getOrno();

					Long tmplong = Tools.Text36ToConvert(maxorno);
					long intmaxorno = tmplong.longValue();
					// 当取出来的数据大于 999999 时,先转在 10 进制再进行加1

					// 单号加1
					intmaxorno = intmaxorno + 1; // 自加1

					// 如果大于 1000000
					newmaxorno = Tools.ConvertTo36Text(intmaxorno, 0);
					// 转在36进制
					if (newmaxorno.length() > 6) {
						// 如果36进制大于 6 位,系统不支持.
						throw new Exception("超过当日最大单号");
					} else {
						// 如果36进行小于等于 6 ,前面补0
						newmaxorno = "000000".substring(newmaxorno.length()) + newmaxorno;
					}

					// 保存最大编号
					zmaxorno.setOrno(newmaxorno);
					this.update(zmaxorno);
					// 更新

					if (newmaxorno.equals("")) {
						throw new Exception("超过当日最大单号");
					} else {

						return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + newmaxorno;

					}

				}

			}
		} catch (Exception sqle) {
			System.out.println("SQLException" + sqle.getMessage());

		}
		return orid;

	}

	/**
	 * 取订单最大编号　
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized String getMaxNo(String Saleid) throws SQLException {

		String orda = Tools.getTodayString();
		String sql = "  from Maxorno where orda='" + orda + "'";
		String orid = "";
		String newmaxorno = "";
		try {

			List list = this.find(sql);
			String datatypestr = WebContant.DatabaseType; //数据库类型
			
			String maxorno = "";
			String sequencename = "orid_sequence" ;
			System.out.println(" datatypestr=  " + datatypestr);
			if (list == null || list.size() < 1) {
				maxorno = "1";
				Maxorno zmaxorno = new Maxorno();
				zmaxorno.setOrda(orda);
				zmaxorno.setOrno(maxorno);
				this.save(zmaxorno);
				//System.out.println(" orid_sequence 1  " );
				// 初始化 Sequence,这里面的程序是用 JDBC
				

				 if ( datatypestr.equalsIgnoreCase("oracle"))
				 {
					   //System.out.println(" oracle= 运行 ORACLE" );
				       makeRestSequence(sequencename);
				 }
				 if ( datatypestr.equalsIgnoreCase("mysql"))
				 {  //新的一天，先删除表，再新建
					 //System.out.println(" = 运行 MYSQL" );
				       makeMysqlRestSequence(sequencename);
				 }
				//System.out.println("  orid_sequence2 ");
				return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + "000001";
			} else {
				//System.out.println(" orid_sequence 4  " );
				Long iserialnum = 1L;
				
				List<Map> iserialnumlist = null ;
				//findBySqlToMapnocolsesession("select "+sequencename+".nextval  from dual");
				
				 if ( datatypestr.equalsIgnoreCase("oracle"))
				 {
					 iserialnumlist =  findBySqlToMapnocolsesession("select "+sequencename+".nextval  from dual");
				 }
				 if ( datatypestr.equalsIgnoreCase("mysql"))
				 {       
					    String sql1      =  " insert  into  "+sequencename+" ( abcd)  value (1)" ;
						IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
					//	String dialect = ((SessionImpl) getSession()).getFactory().getDialect().
						//this.getHibernateTemplate().getSessionFactory().openSession().connection().getTypeMap().
						iGenericJdbcDao.execute(sql1);        //插入数据
					    iserialnumlist =  findBySqlToMapnocolsesession("select max("+sequencename+")  as NEXTVAL from "+sequencename );
				 }
				 
				
				iserialnum = new Long((((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());

				// 如果大于 1000000

				newmaxorno = Tools.ConvertTo10Text(iserialnum, 0);
				// 转在36进制
				if (newmaxorno.length() > 6) {
					// 如果36进制大于 6 位,系统不支持.
					throw new Exception("超过当日最大单号");
				} else {
					// 如果36进行小于等于 6 ,前面补0
					newmaxorno = "000000".substring(newmaxorno.length()) + newmaxorno;
				}

				if (newmaxorno.equals("")) {
					throw new Exception("超过当日最大单号");
				} else {

					return orda.substring(0, 4) + orda.substring(5, 7) + orda.substring(8) + Saleid + newmaxorno;

				}

			}

		} catch (Exception sqle) {
			System.out.println("SQLException" + sqle.getMessage());

		}
		
	//	System.out.println(" orid 1  " +orid);
		return orid;

	}

	
	/**
	 * 取订单最大编号,用MYSQL数据库　
	 * 
	 * @return
	 * @throws SQLException
	 */
	 /*synchronized String getMysqlMaxNo(String Saleid) throws SQLException {
		 WebContant.DatabaseType = "mysql";
		 //设置为 MYSQL，请用通用方法
	      return getMaxNo(Saleid);

	}*/
	/**
	 * 取竹筏检票序号
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized String getZhuFaMaxNo(String tc) throws SQLException {
		String orid = "";
		String seqname = "ZHUFA_SEQ_" + tc;
		try {
			
			long id = getDateRestSequenceId(seqname);
			orid = id + "";
		} catch (Exception e) {
			throw new SQLException(" 取" + seqname);
		}
		return orid;

	}

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
		String hql = "select max(" + columnName + ") from " + tableName + " where 1=1";
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				hql += " and " + keys[i] + "='" + values[i] + "'";
			}
		}
		if (intKeys != null && intKeys.length > 0) {
			for (int i = 0; i < intKeys.length; i++) {
				hql += " and " + intKeys[i] + "=" + intValues[i] + "";
			}
		}
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			if (list.get(0) == null) {
				return new Long(0);
			} else {
				return (Long) list.get(0);
			}
		} else {
			return new Long(0);
		}
	}

	/**
	 * 根据HSQL 取 SQL
	 * 
	 * @param hsql
	 * @return
	 */
	public String getHsqlToSql(String hsql) {
		SessionFactoryImpl sfi = (SessionFactoryImpl) getSessionFactory();
		QueryTranslatorFactory qtf = sfi.getSettings().getQueryTranslatorFactory();
		FilterTranslator qt = qtf.createFilterTranslator(hsql, hsql, null, sfi);

		qt.compile(null, false);

		return qt.getSQLString();

	}

	/**
	 * 
	 * 根据SequenceId取ID,
	 * 必须要建立Sequence，本方法可以兼容所有数据库
	 * 以后业务系统中取最双编号必须统一从这个方法中取。
	 * 
	 * @throws Exception
	 * 
	 */

	public Long getSequenceId(String SequenceName) throws Exception {
		// TODO Auto-generated method stub

		List<Map> iserialnumlist = findBySqlToMapnocolsesession("select " + SequenceName + ".nextval  from dual");
		Long iserialnum = new Long((((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
		return iserialnum;
	}

	
	/**
	 * 删除 SEQUENCE，重新生成，
	 * 以便序号回归0
	 * 
	 * @throws Exception
	 */
	public void makeMysqlRestSequence(String sequence_name) throws Exception {

		
		//对于MYSQL， sequence_name 就是表名；
		
		sequence_name = sequence_name.toUpperCase();
	
		
		String dropsql   =  " DROP TABLE IF EXISTS  "+sequence_name+"" ;
		String sql1      =  " insert  into  "+sequence_name+" ( abcd)  value (1)" ;
		String sql2      =  " delete from "+sequence_name+" where abcd = 1 " ;
		String sql3      =  " select * from "+sequence_name+ "";
		String mysql1    =  " create table if not exists "+sequence_name+" (  "+sequence_name+"  int primary key not null auto_increment,abcd int )" ;

		
		

		try {
			IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
			//System.out.println("dropsql="+dropsql );
            iGenericJdbcDao.execute(dropsql);	 //每天删除一次
			iGenericJdbcDao.execute(mysql1);     //建表
			//System.out.println("mysql1="+mysql1 );
		    iGenericJdbcDao.execute(sql1);       //插入数据
		    //System.out.println("sql1="+sql1 );
            iGenericJdbcDao.execute(sql2);        //删除一条数据
            //System.out.println("sql2="+sql2 );
            
            List list  =   iGenericJdbcDao.select(sql3) ;  //插入数据
            
			if (list.size() > 0) {
				System.out.println(  sequence_name+"正在查询"); 
			}
			

		} catch (Exception e) {
			throw e;
		}

	}
	
	/**
	 * 删除 SEQUENCE，重新生成，
	 * 以便序号回归0
	 * 
	 * @throws Exception
	 */
	public void makeRestSequence(String sequence_name) throws Exception {

		sequence_name = sequence_name.toUpperCase();
		String ishsql = " select   *   from   user_sequences  where sequence_name ='" + sequence_name + "'";
		String hsql1 = " drop  SEQUENCE " + sequence_name;

		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();

		sb.append(" CREATE SEQUENCE " + sequence_name);
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");

		String qsql = " select " + sequence_name + ".nextval  from dual";

		try {
			IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
           //  System.out.println("iGenericJdbcDao = " + iGenericJdbcDao);
			List list = iGenericJdbcDao.select(ishsql);
			//System.out.print("iGenericJdbcDao=" + list.size());
			if (list.size() > 0) {
				// 删除旧的
				iGenericJdbcDao.execute(hsql1);
			}
			// 新建 sequence_name
			iGenericJdbcDao.execute(sb.toString());

			// 先运行一把
			iGenericJdbcDao.select(qsql);

			sb = null;

		} catch (Exception e) {
			throw e;
		}

	}

	
	/**
	 * 删除 SEQUENCE，重新生成，
	 * 以便序号回归0
	 * 
	 * @throws Exception
	 */
	public void makeOneSequence(String sequence_name) throws Exception {

		sequence_name = sequence_name.toUpperCase();
		String ishsql = " select   *   from   user_sequences  where sequence_name ='" + sequence_name + "'";
		String hsql1 = " drop  SEQUENCE " + sequence_name;

		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();

		sb.append(" CREATE SEQUENCE " + sequence_name);
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");

		String qsql = " select " + sequence_name + ".nextval  from dual";

		try {
			IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
           //  System.out.println("iGenericJdbcDao = " + iGenericJdbcDao);
			List list = iGenericJdbcDao.select(ishsql);
			//System.out.print("iGenericJdbcDao=" + list.size());
			if (list.size() > 0) {
				// 不用删除，没有新建
				//iGenericJdbcDao.execute(hsql1);
			} else
            {
				// 新建 sequence_name
				iGenericJdbcDao.execute(sb.toString());

				// 先运行一把
				iGenericJdbcDao.select(qsql);
			}
			sb = null;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 删除 SEQUENCE，重新生成，以便序号回归0
	 * 
	 * @throws Exception
	 */
	public void makeDateRestSequence(String sequence_name) throws Exception {

		sequence_name = sequence_name.toUpperCase();
		String todayinit = "select * from SEQMAXNO where SEQNAME ='" + sequence_name
				+ "' and orda = to_char(sysdate,'YYYY-MM-DD')";
		String ishsql = " select   *   from   user_sequences  where sequence_name ='" + sequence_name + "'";
		String hsql1 = " drop  SEQUENCE " + sequence_name;

		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();

		sb.append(" CREATE SEQUENCE " + sequence_name);
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");
		//取编号
		String qsql = " select " + sequence_name + ".nextval  from dual";
        //删除当日3天前的编号
		String todayDeldatenum = "DELETE FROM SEQMAXNO WHERE SEQNAME ='"+sequence_name+"' AND orda < to_char(sysdate  - 3 ,'YYYY-MM-DD')";
		
		try {
			//System.out.println("makeDateRestSequence111111111111111");
			IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
			// 查询这个 sequence_name 是否存在，如果存就删除
			//System.out.println("makeDateRestSequence11111111122231");
			if (IsTableExits("SEQMAXNO") == 0) {
				//System.out.println("makeDateRestSequence111111133331");
				// 如果表不存在，就新建一个表
				iGenericJdbcDao
						.execute("create table SEQMAXNO  (SEQNAME  VARCHAR2(50)   not null,ORDA CHAR(10)  not null, constraint PK_SEQMAXNO primary key (SEQNAME, ORDA))");
			}
			//判段这天
			//System.out.println("makeDateRestSequence111111111144444");
			//System.out.println("todayinit111111111111111="+todayinit);
			List list1 = iGenericJdbcDao.select(todayinit);
			// 如果当日已经有这个 SEQUENCE
			//System.out.println("list1="+list1.size());
			if (list1== null || list1.size() < 1) { // 当时没有就删除从建重没有就删除重建
				//System.out.println("makeDateRestSequence11111111114455555");
				String insrseq = "insert into  SEQMAXNO ( SEQNAME,ORDA) values ('"+sequence_name+"',to_char(sysdate,'YYYY-MM-DD'))";
				iGenericJdbcDao.execute(insrseq);
				List list = iGenericJdbcDao.select(ishsql);
				// System.out.print("iGenericJdbcDao=" + list.size());
				// 检测 SEQUENCE 是否存在，存就删除
				if (list.size() > 0) {
					// 删除旧的
					iGenericJdbcDao.execute(hsql1);
				}
				// 新建 sequence_name
				// 
			
				//iGenericJdbcDao.select(qsql);
				iGenericJdbcDao.execute(sb.toString());  //放在当日没有中删除，不要啥时都运行
			}
			// 先运行一把
			//删除当前，前一天的SEQ
			iGenericJdbcDao.execute(todayDeldatenum);
			//System.out.println("makeDateRestSequence11111111114666666");
			sb = null;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 检查表名是否存在。
	 * @param TableName
	 * @return
	 */
	private int IsTableExits(String TableName) {
		IGenericJdbcDao iGenericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
		List list1=null;
		try {
			list1 = iGenericJdbcDao.select("select * from user_tables where table_name = '" + TableName
					+ "'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 如果当日已经有这个 SEQUENCE
		return list1.size();
	}

	/**
	 * 每日 回归 重新生成，以便序号回归0
	 * 
	 * @throws Exception
	 */
	public Long getDateRestSequenceId(String SequenceName) throws Exception {
		makeDateRestSequence(SequenceName);
		return getSequenceId(SequenceName);
	}
	
	/**
	 * 取当前数据联接类型，
	 * ORACLE，DB2
	 * @return
	 */
	public String DataBaseType() {
		return (String) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				   String dialect =  ((SessionImpl) currentSession()).getFactory().getDialect().getClass().getName();
				   return dialect ;
			}
		});
	}
}
