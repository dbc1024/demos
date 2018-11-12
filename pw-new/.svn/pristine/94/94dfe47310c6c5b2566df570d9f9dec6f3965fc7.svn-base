package com.ectrip.sys.employee.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public interface IGALCompanyInfoService extends IGenericService{
	
	//删除企业
	public void deletecompany(Galcompanyinfotab company);
	
	public List select();
	
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
	public List getComscenicsId(Long comid);
	public List getHotelCom(Long comid,String domainname);//酒店获取景区服务商
	public List getComscenicsName(Long comid);
	/**
	 * 获取景区服务商
	 * @param comid
	 * @return
	 */
	public List getComsName(Long comid);

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
	 * 判断是否有部门
	 * Describe:
	 * @auth:yuanchengjun
	 * @param icompanyinfoid
	 * @return
	 * return:boolean
	 * Date:2011-10-12
	 */
	public boolean ishavedep(String icompanyinfoid);
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
	
	//根据企业id获取景区id列表
	public List getIscenicidsByIcompanyinfoid(Long icompanyinfoid);
	
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
	public List getCompanyTree(String id, String type, String icompanyid, String deptid);
	
	public Galcompanyinfotab getGalcompanyinfo( Long iscenicid ) ;
}
