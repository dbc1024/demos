package com.ectrip.ticket.provider.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.provider.service.IProviderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "provider")
@Api(tags = "票务服务-票务信息设置-服务商信息管理")
public class ProviderController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(ProviderController.class);
	@Autowired
	private IProviderService providerService;
	
	@Autowired
	private EcService ecService;
	
	@Autowired
	private SysService sysService;
	//根据城市id获得该城市及下级地区所有景区json
	@GetMapping(value = "v1/getScenicesByCityId/{cityId}")
	@ApiOperation(value = "根据城市ID获取景区")
	public ResponseBean citySceniceJson(@PathVariable Long cityId){
		
		//根据父级获取子级客源地
		ResponseBean sourceRegionJson = sysService.sourceRegionJson(cityId);
		
		List<Map> sourceRegionMap = (List<Map>)sourceRegionJson.getData();
	
		String ids = "" + cityId;
		for (Map idMap : sourceRegionMap) {
			String szregionalid = idMap.get("szregionalid").toString();
			ids = ids + "," + szregionalid;
		}

		List cityScenice;
		try {
			cityScenice = providerService.getScenicListByAreaIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseBean(400, "景区获取失败", "错误信息：" + e.getMessage());
		}
		return new ResponseBean(200, "操作成功", cityScenice);
	}
	
	
	
	
	/**
     * 列表
     *
     * @return
     */
	@GetMapping("/v1/queryProviderList")
	@ApiOperation(value = "获取景区服务商列表")
    public ResponseBean providerViewList(@RequestParam(name="provider") Esbscenicareatab provider,@RequestParam int page,@RequestParam int pageSize,@RequestParam String path) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
        try {
        	//查询员工信息
        	Esfemployeetab esfemployeetab = sysService.currentUser();
        	String pdtp = provider.getScenictype();
            if (provider.getIsjd() == null || "".equals(provider.getIsjd()+"")) {
                provider.setIsjd(0);
            }
            if (provider.getIsjd() == 1) {// 表示景点
               
                ps = providerService.queryAllList(provider.getIparentid(),
                        page, pageSize, null, path);

            } else { // 表示服务商
                ps = providerService.queryAllList(provider.getIparentid(),
                        pdtp, page, pageSize, null, path,
                        esfemployeetab.getIemployeeid());

                if (provider.getIparentid() != null
                        && !"".equals(provider.getIparentid()+"")
                        && 0 != provider.getIparentid()) {
                    Esbscenicareatab providers = (Esbscenicareatab) this.providerService
                            .get(Esbscenicareatab.class,
                                    provider.getIparentid());
                    if ("up".equals(path)) {
                        provider.setIparentid(providers.getIparentid());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = 400;
    		msg = "接口异常";
    		LOGGER.info("获取服务商列表异常", e.getCause());
        }
        result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
        return result;
    }
	
	/**
	 *  获取景区服务商上开户信息
	 * @param iscenicid
	 * @return
	 */
	@GetMapping("/v1/queryProviderCompany")
	@ApiOperation(value = "获取景区服务商开户信息")
	public ResponseBean queryProviderCompany(Long iscenicid){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		ProviderCompany providerCompany = null;
		try {
			providerCompany = providerService.queryProviderCompany(iscenicid);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("获取景区服务商上开户信息异常", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(providerCompany);
		return result;
	}
	
	@GetMapping("/v1/findpmcd")
	public String findpmcd(@RequestParam String pmky,@RequestParam String pmva) {
		return providerService.findpmcd(pmky, pmva);
	}
	
	/**
     * 根据服务商id查询服务商信息
     * Describe:
     *
     * @param scenicId
     * @return return:Esbscenicareatab
     * Date:2011-9-24
     * @auth:huangyuqi
     */
	@GetMapping("/provider/query")
    public Esbscenicareatab query(@RequestParam Long scenicId) {
		return providerService.query(scenicId);
    	
    }
	
	/**
	 * 查询景区服务商信息
	 * @param radiobuttom 条件类型
	 * @param condition 条件内容
	 * @param page 当前页数
	 * @param pageSize 当前也大小
	 * @param pdtp 服务商类型
	 * @return
	 */
	@GetMapping("/v1/providerQueryList")
	@ApiOperation(value = "查询景区服务商信息")
    public ResponseBean providerQueryList(Principal user,@ApiParam(value = "条件类型") @RequestParam int radiobuttom,@ApiParam(value = "条件内容") @RequestParam String condition,@RequestParam int page,@RequestParam int pageSize,@ApiParam(value = "服务商类型") @RequestParam String pdtp) {
		//查询员工信息
    	Esfemployeetab esfemployeetab = sysService.currentUser();
        ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
        try {
            if (1 == radiobuttom) {// 根据服务商编号查询
                if (condition != null && !"".equals(condition)) {
                	Pattern p1 = Pattern.compile("^[0-9A-Z]+$");
                    boolean c = p1.matcher(condition).matches();
                    if (c == false) {
                        //"编码由数字或大写字母组成,请重新输入!"
                    }
                }
                ps = providerService.queryList(page,
                        pageSize, null, null, radiobuttom, condition, pdtp,
                        esfemployeetab.getIemployeeid(), 0);
            }
            if (2 == radiobuttom) {// 根据服务商名称模糊查询
                if (condition != null && !"".equals(condition)) {
                    
                }
                ps = providerService.queryList(page,
                        pageSize, null, null, radiobuttom, condition, pdtp,
                        esfemployeetab.getIemployeeid(), 0);
            }
            if (3 == radiobuttom) {// 查询无效的服务商
                
                ps = providerService.queryList(page,
                        pageSize, null, null, radiobuttom, "", pdtp,
                        esfemployeetab.getIemployeeid(), 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = 400;
    		msg = "接口异常";
    		LOGGER.info("查询景区服务商信息异常", e.getCause());
        }
        result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
        return result;
    }
	
	@GetMapping(value = "v1/listByIds")
	@ApiOperation(value = "根据服务商ID集合获取服务商信息列表")
	public List getProvidersByIds(@ApiParam("ids数据格式[12,541,625,51]") @RequestParam String ids) {
		return providerService.getProvidersByIds(ids);
	}
	
	 /**
     * 根据登录人得到服务商列表
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
	@GetMapping(value = "v1/getScenicList")
	@ApiOperation(value = "根据登录人得到服务商列表")
    public List getScenicList(@RequestParam String esfemployeetab, @RequestParam String scenictype, 
    							@RequestParam int isjd, @RequestParam String isonlyjq, @RequestParam String groupid, @RequestParam boolean isHqyatu) {
    	
		Esfemployeetab esfemployeeTab = JSONObject.parseObject(esfemployeetab, Esfemployeetab.class);
		
		return providerService.getScenicList(esfemployeeTab, scenictype, isjd, isonlyjq, groupid, isHqyatu);
    }
	/**
	 * 根据请求路径及登录用户获取景区服务商信息
	 * 服务商下拉列表通用接口
	 * @param request
	 * @return 
	 */
	@GetMapping(value = "v1/getScenicProviderByServerName")
	@ApiOperation(value = "服务商下拉列表通用接口")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseBean getScenicProviderByServerName(HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			//查询员工信息
			Esfemployeetab esfemployeetab = sysService.currentUser();
			//获取域名
			String url = request.getServerName();
			List<Domain> listDomain = ecService.getDomain(url,"1");
			List listScenic = null;
			boolean isHqyatu = false;
			Domain domain = listDomain.get(0);
			//中心景区
			if(1==domain.getSeq() || 2 == domain.getSeq()){
				isHqyatu = true;
				List rproviderlist = providerService.getScenicList(esfemployeetab,
						null, 0, "01",domain.getGroupId(),isHqyatu);
				Esbscenicareatab provider = new Esbscenicareatab();
				provider.setIscenicid(0L);
				provider.setSzscenicname("所有服务商");
				rproviderlist.add(0, provider);
				responseBean.setData(rproviderlist);
			}else{
				//景区下的单个景点
				listScenic = sysService.getComscenicsId(Long.parseLong(domain.getGroupId()));
				responseBean.setData(listScenic);
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	/**
	 * 酒店、景区下拉列表
	 */
	@GetMapping(value = "v1/providerSelect")
	@ApiOperation(value = "酒店、景区下拉列表接口")
	public ResponseBean providerSelect(@RequestParam String providerType) {
		
		try {
			
			List list = providerService.getScenicListSelect(providerType);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", list);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("酒店、景区下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "酒店、景区下拉列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
}
