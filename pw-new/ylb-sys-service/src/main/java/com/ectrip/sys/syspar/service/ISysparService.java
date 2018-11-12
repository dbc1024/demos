package com.ectrip.sys.syspar.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.exception.LogicException;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.model.syspar.Adzone;


/**
 * Copy Right Information : Ectrip Package : com.ectrip.syspar.service.iservice ClassName :ISysparService.java JDK version used : jdk1.5 User : likai Version :
 * Modification history :2009-3-22 08:56:45
 */

public interface ISysparService extends IGenericService {

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
	 * @use: 查系统参数
	 * 
	 */
	public List findAll(String pmky, String systp, String type, boolean isvalue, boolean isAll);

	public void insert(Sysparv5 syspar) throws LogicException;

	public Sysparv5 findOne(String pmky, String pmcd);

	public List findAllQuery(String pmky, String systp, String type, boolean isvalue, boolean isAll) throws Exception;

	public String returnType(String pdtp);

	public List findEsyspar(String pmky, String spmcd);

	// 联动
	public List findOneList(String pmky, String spmcd);

	public Map findDoubleList(String pmky, String spmcd);
	
	/**
	 * 
	 * Describe:
	 * @author: huying
	 * @param pmky
	 * @param pmcd
	 * @param path
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-6-24
	 */
    public PaginationSupport sysList(String pmky,String pmcd,String path,int page,int pagesize,String url);
	public PaginationSupport SysparQueryList(String pmky,String pmcd,String path,int page,int pagesize,String url);

	public List retrieve(String pmky);
	public List query(String pmky,String pmcdwhere);
	/**
	 * 查看产品种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdtp服务商类别
	 * @param type票形式（01基本票，02套票）
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
	public Sysparv5 findSysOne(String pmky, String pmva) ;
	public PaginationSupport getSeatList(String orid, int page,
			int pageSize, String url);

	public List retrieve(String string, Long type);

	public JSONObject validationObject(Object object, int type) ;

	public void makeAdJS(long zoneid) throws IOException;

	public void save(Adzone adzone);
	
	public List getSysparamsByPmcds(String pmcds);

	List getSysparamsByPmkyAndPmcds(String pmky, String pmcds);

	public List getSysparamsByParms(String pmky, String pmcd, String pmvbs, String spmcd);
	
	public List<Sysparv5> findSysparBypmky(String pmky, String pmcd);

	public Sysparv5 getValueById(String key, String code);


	public List find(String query);

}
