package com.ectrip.sys.employee.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
public interface IGALCompanyInfoTabDAO extends IGenericDao{
	
	

	//删除企业
	public void deletecompany(Galcompanyinfotab company);

	
	//显示 
	public PaginationSupport companyQueryList(String mark,Long comid,int page,int pagesize,String url) throws Exception;
	public PaginationSupport companyQueryListbycomid(String mark,Long comid,int page,int pagesize,String url);

	
	public PaginationSupport companyQueryListTJ(String flag,int parentid,String queryid,String queryMess,int page,int pagesize,String url);

	
	//修改公司信息
	public void updateCompany(Galcompanyinfotab comp);
	
	//新增公司
	public void addNewCompany(Galcompanyinfotab comp);
	/**
	 * 查询企业服务商
	 */

	public List getCompanyScenics(List scids);
	public List getComscenics(Long comid);
	public List getHotelCom(Long comid,String domainname);//酒店获取景区服务商
	public List<Companyscenic> getSelectedComscenics(Long comid);
	public List getScenicsByOneid(Long parentid);
	//保存企业与服务商信息
	public void saveCompanyScenics(Galcompanyinfotab comp);
	/**
	 * 判断是否平台企业
	 * @param icompanyinfoid
	 * @return 不是平台企业 true 平台企业
	 */
	public boolean isPlatform(String icompanyinfoid);
	
	/**
	 * 判断是否有子级 
	 * @param icompanyinfoid
	 * @return false 有子级 true 没子级
	 */
	public boolean isHaveSon(String icompanyinfoid);
	/**
	 * Describe: 服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iscenicid
	 * @return Esbscenicareatab
	 */
	public Esbscenicareatab getScenic(Long iscenicid);
	/**
	 * Describe: 用户关联的服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iemployee
	 * @return list
	 */
	public List getEmployeeScenicbyIemployeeid(Long iemployeeid);
	/**
	 * 根据ID获取公司信息
	 * @param icompanyinfoid
	 * @return
	 */
	public Galcompanyinfotab getCompanyById(Long icompanyinfoid)throws IllegalAccessException, InvocationTargetException;
	/**
	 * 根据企业查询服务商编号
	 * @param comid
	 * @return
	 */
	public List getComscenicsId(Long comid) ;
	
	/**
	 * 根据企业查询查询企业下所有景区
	 * @param comid
	 * @return
	 */
	public List getComscenicsName(Long comid);
	/**
	 * 获取首页景区服务商
	 * @param comid
	 * @return
	 */
	public List getComsName(Long comid);
	//根据企业id获取景区id列表
	public List getIscenicidsByIcompanyinfoid(Long icompanyinfoid);
}