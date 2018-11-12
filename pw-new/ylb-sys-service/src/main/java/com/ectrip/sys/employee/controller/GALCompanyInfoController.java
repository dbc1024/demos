package com.ectrip.sys.employee.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IEsfEmployeeTabService;
import com.ectrip.sys.employee.service.IGALCompanyInfoService;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.service.ISysparService;
import com.ectrip.upload.model.Upfile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "系统模块相关-企业信息管理相关接口")
@RequestMapping(value = "company")
public class GALCompanyInfoController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(GALCompanyInfoController.class);
	@Autowired
	private IGALCompanyInfoService companyInfoService;
	
	@Autowired
	private ISysparService sysparService;
	
	@Autowired
	private IEsfEmployeeTabService esfEmployeeTabService;
	
	/**
	 * 查询企业信息列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/v1/showAllCompanyInfo")
	@ApiOperation(value = "分页查看企业信息")
	public ResponseBean showAllCompanyInfo(@RequestParam(name = "comMark", required = false) String comMark,@RequestParam(name = "icompanyinfoid",required = false) Long icompanyinfoid, @RequestParam int page,@RequestParam int pageSize,Principal user) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			String loginName = user.getName();
			Esfemployeetab esfemployeetab = esfEmployeeTabService.getEmployeeInfoByEmpId(loginName);
			if (esfemployeetab.getEmptype() == null) {
				esfemployeetab.setEmptype(0L);
			}
			Long emptype = esfemployeetab.getEmptype();
			if (comMark == null || comMark.equals("")) {
				comMark = "next";
			}
			Galcompanyinfotab company = null;
			if (icompanyinfoid == null || icompanyinfoid == 0) {
				company = new Galcompanyinfotab();
				company.setIcompanyinfoid(new Long(0));
				company.setSzinfocomid(new Long(0));
			} else {
				company = (Galcompanyinfotab) this.companyInfoService.get(Galcompanyinfotab.class, icompanyinfoid);
			}
			if (emptype == 1) {
				ps = companyInfoService.companyQueryListbycomid(comMark, esfemployeetab.getIcompanyinfoid(), pageSize,
						Integer.valueOf(page), null);
			} else {

				if (comMark.equals("next")) {
					ps = companyInfoService.companyQueryList(comMark, company.getIcompanyinfoid(), pageSize,
							Integer.valueOf(page), null);
				} else {

					ps = companyInfoService.companyQueryList(comMark, company.getSzinfocomid(), pageSize,
							Integer.valueOf(page), null);
				}
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("分页查询企业信息异常", e.getCause());
			e.printStackTrace();
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}

	/**
	 * 查看企业信息
	 * @return
	 */
	@GetMapping(value = "/v1/viewCompanyInfo")
	@ApiOperation(value = "查看企业信息详情")
	public ResponseBean viewCompanyInfo(@RequestParam Long icompanyinfoid) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Galcompanyinfotab company = (Galcompanyinfotab) this.companyInfoService.get(
					Galcompanyinfotab.class, icompanyinfoid);
			if (company == null) {
				code = 400;
				msg = "接口异常";
				result.setCode(code);
				result.setMsg(msg);
				return result;
			}
			Sysparv5 v5 = (Sysparv5) this.companyInfoService.get(Sysparv5.class,
					new Sysparv5Id("CMTP", company.getCompanytype()));
			company.setStrcomtype(v5.getPmva());
			if (company.getCompanytype().equals("02")) {
				List sclist = companyInfoService.getComscenics(company
						.getIcompanyinfoid());
			}
			if(company.getSzcompanyemblem() != null){
				Upfile upfile = (Upfile) this.companyInfoService.get(Upfile.class, Long.parseLong(company.getSzcompanyemblem()));
				if(upfile!=null){
					company.setImgUrl(upfile.getUpadder()+upfile.getUpfilename());
				}
			}
			result.setCode(code);
			result.setMsg(msg);
			result.setData(company);
		return result;
	}

	/**
	 * 根据条件查询企业信息
	 * @param flag
	 * @param querycomid
	 * @param querycomname
	 * @return
	 */
	@GetMapping(value = "/v1/queryCompany")
	@ApiOperation(value = "根据条件查询企业信息")
	public ResponseBean queryCompany(@RequestParam String flag,@RequestParam String querycomid,@RequestParam String querycomname) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		if (flag != null) {
			if (flag.equals("0")) {
				if (querycomid != null) {
					querycomname = "";
				} else if (flag.equals("1")) {
					querycomid = "";
				}
			}
		}
		PaginationSupport ps = companyInfoService.companyQueryListTJ(flag, 0, querycomid,
				querycomname, 15, Integer.valueOf(1), null);
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}

	
	/**
	 * 设置企业管理的景区
	 * @param icompanyinfoid
	 * @return
	 */
	@PutMapping(value = "/v1/setCompanyScenics")
	@ApiOperation(value = "设置企业关联的景区服务商")
	public ResponseBean setCompanyScenics(@RequestParam String icompanyinfoid) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Galcompanyinfotab company = (Galcompanyinfotab) this.companyInfoService.get(
					Galcompanyinfotab.class, icompanyinfoid);
			if (company != null) {
				List scenicsList = companyInfoService.getScenicsByOneid(company
						.getSzinfocomid());
				List<Companyscenic> comscenisc = companyInfoService.getSelectedComscenics(company.getIcompanyinfoid());
				for (int i = 0; i < comscenisc.size(); i++) {
					company.getIscenicids()[i] = ((Companyscenic) comscenisc.get(i))
							.getIscenicid();
				}
			}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(company);
		return result;
	}

	@PutMapping(value = "/v1/saveCompanyScenics")
	@ApiOperation(value = "保存企业关联的景区服务商")
	public ResponseBean saveCompanyScenics(@RequestBody Galcompanyinfotab company) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		if (company.getIscenicids() == null
				|| company.getIscenicids().length == 0) {
			code = 400;
			msg = "未设置景区服务商";
		}
		companyInfoService.saveCompanyScenics(company);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	@GetMapping(value = "v1/getComscenicsId")
	@ApiOperation(value = "根据comid获取iscenicid列表(供其他服务调用)")
	public List getComscenicsId(@RequestParam("comid")Long comid) {
		
		return companyInfoService.getComscenics(comid);
	}
	
	
	@ApiOperation(value = "根据企业id获取景区id列表(供其他服务调用)")
	@GetMapping(value = "v1/getIscenicidsByIcompanyinfoid")
	public List getIscenicidsByIcompanyinfoid(@RequestParam(value="icompanyinfoid", required=false)Long icompanyinfoid) {
		
		return companyInfoService.getIscenicidsByIcompanyinfoid(icompanyinfoid);
	}
	
	

	/**
	 * 	获取公司组织架构树
	 *  公司架构树处理方法
	 * @author lijin
	 * 主要处理公司架构的，选择公司人员，组织架构等
	 */
	@GetMapping(value = "v1/comapntTree")
	@ApiOperation(value = "获取公司组织架构树")
	public ResponseBean getComapntTree(@RequestParam(required=false) String id,@RequestParam(required=false) String type, 
			@RequestParam(required=false) String icompanyid, @RequestParam(required=false) String deptid,  Principal user){
		
		
		try {
			Esfemployeetab employee = esfEmployeeTabService.getEmployeeInfoByEmpId(user.getName());
			
			Galcompanyinfotab company=(Galcompanyinfotab) companyInfoService.get(Galcompanyinfotab.class, employee.getIcompanyinfoid());
			
			if( (id==null || id.equals("")) && company.getCompanytype().equals("01") ){
				if(id==null||id.equals("")){
					id="0";
				}else{
					id=employee.getIcompanyinfoid().toString();
				}
			}
			
			if(type==null || type.equals("")){
				type="0";
			}
			
			List companyTree = companyInfoService.getCompanyTree(id,type,icompanyid,deptid);
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", companyTree);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("获取公司组织架构树异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "获取公司组织架构树异常", "异常信息："+ e.getMessage());
		}
		
	}
	
//	@PostMapping(value = "/company/v1/getCompanyscenic")
//	public Companyscenic getCompanyscenic(@RequestParam("iscenicid") Long iscenicid) {
//		
//	}
	
	@GetMapping("/getGalcompanyinfo")
	public Galcompanyinfotab getGalcompanyinfo(@RequestParam("iscenicid") Long iscenicid ) {
		return companyInfoService.getGalcompanyinfo(iscenicid);
	}
	
}
