package com.ectrip.sys.syspar.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Sysparv5;


/**
 * Copy Right Information : Ectrip Package : com.ectrip.syspar.dao.idao ClassName :ISysparDao.java JDK version used : jdk1.5 User : likai Version : Modification
 * history :2009-3-21 ����12:53:58
 */
public interface ISysparDao extends IGenericDao {

	/**
	 * 
	 * Describe:查询打印
	 * @author: huying
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2015-6-23
	 */
	public List queryPrintList(Long icrowdkindpriceid);
	
	/**
	 * @author: likai
	 * @param pmky
	 *            参数标示
	 * @param systp
	 *            第几级
	 * @param type
	 *            A B C D E F
	 * @param isvalue
	 *            是否启用 true isvalue=1;false 无
	 * @param isAll
	 *            是否查询全部 true 是 false 否
	 * @return List
	 * @use: 系统参数查询
	 * 
	 */
	public List findAll(String pmky, String systp, String type, boolean isvalue, boolean isAll);

	public Sysparv5 findSysparOne(String pmky, String pmcd, String type);

	// 联动
	public List findOneList(String pmky, String spmcd);

	public Map findDoubleList(String pmky, String spmcd);
	public PaginationSupport sysList(String pmky,String pmcd,String path,int page,int pagesize,String url);
	public PaginationSupport sysparQueryList(String pmky,String pmcd,String path,int page,int pagesize,String url);
	public List retrieve(String pmky);
	public List query(String pmky,String pmcdwhere);
	/**
	 * 查看产品种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdtp服务商类别
	 * @param type票类别（01基础类，02套票）
	 * @return
	 * return:List
	 * Date:2011-9-23
	 */
	public List querypdtpList(String pdtp,String type);
	/**
	 * 根据类型，名称查询系统参数
	 * Describe:
	 * @auth:huangyuqi
	 * @param pmky
	 * @param pmva
	 * @return
	 * return:Sysparv5
	 * Date:2011-10-10
	 */
	public Sysparv5 findSysOne(String pmky,String pmva);
	public PaginationSupport getSeatList(String orid, int page, int pageSize,
			String url);

	public List retrieve(String pmky, Long type);
	
	public List getSysparamsByPmcds(String pmcds);

	public List getSysparamsByPmkyAndPmcds(String pmky, String pmcds);

	public List getSysparamsByParms(String pmky, String pmcd, String pmvbs, String spmcd);

	public List<Sysparv5> findSysparBypmky(String pmky, String pmcd);
}
