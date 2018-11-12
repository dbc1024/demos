package com.ectrip.ticket.afcset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.service.IEsbaccessequiptabService;
import com.ectrip.ticket.afcset.service.IEsbgardengatetabService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.provider.service.ITicketTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created BY Jzhenhua,Time 2011-09-27 09:46 园门Action
 *
 * @author lenovo
 */
@RestController
@RequestMapping("gardengate")
@Api(tags = "票务管理-票务检票设置-园门管理相关接口")
public class EsbgardengatetabController {

	private static final Logger LOGGER = LogManager.getLogger(EsbgardengatetabController.class);
	
	@Autowired
	private SysService sysService;
	
	@Autowired
	private IEsbgardengatetabService esbgardengatetabService;
	@Autowired
	private IEsbaccessequiptabService esbaccessequiptabService;  //准进设备 调用
	@Autowired
	private ITicketTypeService tickettypeService;
	
	/**
     * 获得园门列表
     *
     * @return
     */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "园门列表")
    public ResponseBean gardenGateViewList(@RequestParam(required=false) Long providerId, @RequestParam(required=false) Long isgardengateid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
    	
    	Map<String, Object> data = new HashedMap();//返回结果封装
    	
    	
        try {
            // 获取当前登录用户
        	Esfemployeetab employee = sysService.currentUser();
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    return new ResponseBean(200, "esfemployeetab.scenics.required");
                }
            }

            // 获取当前登录用户所管理的服务商
//            List providers = this.esbaccessequiptabService.findListesbticket(scenics);
//            getRequest().setAttribute("prdlist", providers);

            if (pageSize == null) {
    			pageSize = 20;
    		}
    		if (page == null) {
    			page = 1;
    		}
            
            // 获取所有园门信息
    		PaginationSupport ps = this.esbgardengatetabService.getGaredenGatePage(providerId, isgardengateid, employee.getScenics(), pageSize,page, null);
    		
//    		data.put("prdlist", providers);
    		data.put("ps", ps);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取园门列表异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "获取园门列表异常", "异常信息："+ e.getMessage());
        }
        
        return new ResponseBean(200, "操作成功", data);
    }
	
	
	/**
     * 获取当前登录用户所管理的服务商
     *
     * @return
     */
	@GetMapping(value = "v1/getChargedProviders")
	@ApiOperation(value = "获取当前登录用户所管理的服务商下拉列表")
    public ResponseBean getChargedProviders() {
        try {
        	 // 获取当前登录用户
        	Esfemployeetab employee = sysService.currentUser();
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    return new ResponseBean(200, "esfemployeetab.scenics.required");
                }
            }

            // 获取当前登录用户所管理的服务商
            List providers = this.esbaccessequiptabService.findListesbticket(scenics);
            
            return new ResponseBean(200, "操作成功", providers);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取当前登录用户所管理的服务商列表异常："+ StringUtil.toString_02(e));
            return new ResponseBean(400, "获取当前登录用户所管理的服务商列表异常", "异常信息："+ e.getMessage());
        }
       
    }
	
	/**
	 * 根据景区id获取园门下拉列表
	 */
	@GetMapping(value = "v1/gateSelect/{providerId}")
	@ApiOperation(value = "根据景区id获取园门下拉列表")
    public ResponseBean getGardenGateSelect(@PathVariable Long providerId) {
        try {
        	List list = esbgardengatetabService.getGardenGateSelect(providerId);
            return new ResponseBean(200, "操作成功", list);
            
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取园门下拉列表异常："+ StringUtil.toString_02(e));
            return new ResponseBean(400, "获取园门下拉列表异常", "异常信息："+ e.getMessage());
        }
       
    }
	
	/**
     * 查获取园门详情
     *
     * @return
     */
	@GetMapping(value = "v1/get/{igardengateid}")
	@ApiOperation(value = "根据园门id获取园门详情")
    public ResponseBean gardenGateDetail(@PathVariable Long igardengateid) {
        try {

        	Esbgardengatetab gardenGate = this.esbgardengatetabService.getGardenGateById(igardengateid);
        	return new ResponseBean(200, "操作成功", gardenGate);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取园门详情异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "获取园门详情异常", "异常信息："+ e.getMessage());
        }
    }

	
	
	/**
     * 新增园门
     *
     * @return
     */
	@PostMapping(value = "v1/add")
	@ApiOperation(value = "新增园门")
    public ResponseBean addGardenGate(@RequestBody Esbgardengatetab esbgardengatetab) {
        try {
        	// 获取当前登录用户
        	Esfemployeetab employee = sysService.currentUser();
            Syslog syslog = new Syslog();

            syslog.setIemployeeid(employee.getIemployeeid());
            syslog.setSzemployeename(employee.getSzemployeename());

            esbgardengatetab.getId().setIgardengateid(this.esbgardengatetabService.getMaxId());
            esbgardengatetabService.addGardenGate(esbgardengatetab,syslog);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("新增园门异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "新增园门异常", "异常信息："+ e.getMessage());
        }
        
        return new ResponseBean(200, "操作成功");
    }
    
    
    /**
     * 修改园门
     *
     * @return
     */
	@PostMapping(value = "v1/update")
	@ApiOperation(value = "修改园门信息")
    public ResponseBean updateGardenGate(@RequestBody Esbgardengatetab esbgardengatetab) {
        try {
        	// 获取当前登录用户
        	Esfemployeetab employee = sysService.currentUser();
            Syslog syslog = new Syslog();

            syslog.setIemployeeid(employee.getIemployeeid());
            syslog.setSzemployeename(employee.getSzemployeename());
            
            this.esbgardengatetabService.updateGardenGate(esbgardengatetab,syslog);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改园门异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "修改园门异常", "异常信息："+ e.getMessage());
        }
        return new ResponseBean(200, "修改成功");
    }
	
	/**
     * 删除园门
     *
     * @return
     */
	@GetMapping(value = "v1/delete")
	@ApiOperation(value = "删除园门信息")
    public ResponseBean delGardenGate(@RequestParam Long igardengateid, @RequestParam Long iscenicid) {
        try {

        	// 获取当前登录用户
        	Esfemployeetab employee = sysService.currentUser();
            Syslog syslog = new Syslog();

            syslog.setIemployeeid(employee.getIemployeeid());
            syslog.setSzemployeename(employee.getSzemployeename());
            
            
            EsbgardengatetabId id = new EsbgardengatetabId(igardengateid, iscenicid);
            Esbgardengatetab esbgardengatetab = new Esbgardengatetab();
            esbgardengatetab.setId(id);
            
            this.esbgardengatetabService.deleteGardenGate(esbgardengatetab, syslog);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除园门异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "删除园门异常", "异常信息："+ e.getMessage());
        }
        return new ResponseBean(200, "操作成功");
    }
	
	/**
     * 关联园门预备信息查询
     *
     * @return
     */
	@GetMapping(value = "v1/addLinkPre/{igardengateid}")
	@ApiOperation(value = "关联园门预备信息接口")
	public ResponseBean addGardenGateLinkPre(@PathVariable Long igardengateid){
        
		Map<String, Object> data = new HashMap<>();
		
		try {
			Esbgardengatetab esbgardengatetab = this.esbgardengatetabService.getGardenGateById(igardengateid);
	        List gardenGateList = this.esbgardengatetabService.queryOtherGarden(esbgardengatetab.getId().getIscenicid(), esbgardengatetab.getId().getIgardengateid());
	        //getRequest().setAttribute("gardenGateList", gardenGateList);
	        data.put("gardenGateList", gardenGateList);
	        
	        //园门关联类型
	        //List linkTypeList = this.sysparService.retrieve("GDLK");
	        //getRequest().setAttribute("linkTypeList", linkTypeList);
	        List linkTypeList = sysService.queryByPmkyPmcds("GDLK"," sys.id.pmcd not like'%*%' and isvalue=1 order by pmcd");
	        data.put("linkTypeList", linkTypeList);
	        
	        
	        //产品列表
	        List productList = tickettypeService.getTickettypeListSelect(esbgardengatetab.getId().getIscenicid(),"01");
	        //getRequest().setAttribute("productList",productList);
	        data.put("productList", productList);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("关联园门预备信息查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "关联园门预备信息查询异常", "异常信息："+ e.getMessage());
		}
        
        return new ResponseBean(200, "操作成功", data);
    }
	
	/**
     * 关联园门
     *
     * @return
     */
	@PostMapping(value = "v1/addLink")
	@ApiOperation(value = "关联园门接口")
	public ResponseBean addGardenGateLink(@RequestBody Gardengatelink gardengatelink, @RequestParam Long igardengateid, @RequestParam Long iscenicid) {
		
		try {
			
			Syslog syslog = new Syslog();
	        List linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),0L);
	        if (linkList != null && linkList.size() > 0) {
	        	return new ResponseBean(400, "该园门已关联所有产品!");
	        	
	        }else{
	        	
	            if(gardengatelink.getInoteger1() != null && gardengatelink.getInoteger1() != 0L){
	            	
	                linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),gardengatelink.getInoteger1());
	                if (linkList != null && linkList.size() > 0) {
	                	return new ResponseBean(400, "园门已关联该产品!");
	                }
	            }else{
	                linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),null);
	                if (linkList != null && linkList.size() > 0) {
	                    return new ResponseBean(400, "园门已存在关联数据，不可再关联所有产品!");
	                }
	            }
	        }
	        Esbgardengatetab gate = this.esbgardengatetabService.showGate(gardengatelink.getLigardengateid());
	        if (gate == null) {
	            return new ResponseBean(400, "子园门不存在!");
	        }
	        gardengatelink.setIgardengateid(igardengateid);
	        gardengatelink.setIscenicid(iscenicid);
	        gardengatelink.setLigardengateid(gate.getId().getIgardengateid());
	        gardengatelink.setLiscenicid(gate.getId().getIscenicid());
	        
	        this.esbgardengatetabService.addGradeLink(gardengatelink, syslog);
	       
	        return new ResponseBean(200, "操作成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("关联园门异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "关联园门异常", "异常信息："+ e.getMessage());
		}
    }
	
	/**
     * 修改关联园门信息
     *
     * @return
     */
	@PostMapping(value = "v1/updateLink")
	@ApiOperation(value = "修改关联园门接口")
	public ResponseBean updateGardenGateLink(@RequestBody Gardengatelink gardengatelink, @RequestParam Long igardengateid, @RequestParam Long iscenicid) {
		
		try {
			Syslog syslog = new Syslog();
	        List linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),0L);
	        if (linkList != null && linkList.size() > 0) {
	            if (gardengatelink.getLinkid() != ((Gardengatelink) linkList.get(0)).getLinkid()) {
	                return new ResponseBean(400, "改园门已关联所有产品!");
	            }
	        }else{
	            if(gardengatelink.getInoteger1() != null && gardengatelink.getInoteger1() != 0L){
	                linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),gardengatelink.getInoteger1());
	                if (linkList != null && linkList.size() > 0) {
	                    if (gardengatelink.getLinkid() != ((Gardengatelink) linkList.get(0)).getLinkid()) {
	                        return new ResponseBean(400, "园门已关联该产品!");
	                    }
	                }
	            }else{
	                linkList = this.esbgardengatetabService.showGradeLinkList(igardengateid, gardengatelink.getLigardengateid(),null);
	                if (linkList != null && linkList.size() > 0) {
	                    return new ResponseBean(400, "园门已存在关联数据，不可再关联所有产品!");
	                }
	            }
	        }
	        Esbgardengatetab gate = this.esbgardengatetabService.showGate(gardengatelink.getLigardengateid());
	        if (gate == null) {
	            return new ResponseBean(400, "子园门不存在!");
	        }
	        gardengatelink.setIgardengateid(igardengateid);
	        gardengatelink.setIscenicid(iscenicid);
	        gardengatelink.setLigardengateid(gate.getId().getIgardengateid());
	        gardengatelink.setLiscenicid(gate.getId().getIscenicid());
	        
	        this.esbgardengatetabService.updateGradeLink(gardengatelink, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("修改关联园门异常"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "修改关联园门异常", "异常信息："+ e.getMessage());
		}
        
		return new ResponseBean(200, "操作成功");
    }
	
	/**
     * 删除关联园门
     *
     * @return
     */
	@GetMapping(value = "v1/deleteLink")
	@ApiOperation(value = "删除关联园门接口")
	public ResponseBean delGateLink(@RequestParam Long linkid) {
		try {
			Syslog syslog = new Syslog();
	        this.esbgardengatetabService.delLink(linkid, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除关联园门失败"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "删除关联园门失败", "异常信息："+ e.getMessage());
		}
		return new ResponseBean(200, "操作成功");
    }
	
	/**
     * 关联园门详情
     *
     * @return
     */
	@GetMapping(value = "v1/gateLinkDetail")
	@ApiOperation(value = "关联园门详情接口")
	public ResponseBean gateLinkDetail(@RequestParam Long linkid){
		Map<String, Object> data = new HashMap<>();
		
		try {
			Gardengatelink gardengatelink = (Gardengatelink) esbgardengatetabService.get(Gardengatelink.class, linkid);
			data.put("gardengatelink", gardengatelink);
			
	        if (gardengatelink != null) {
	        	Esbgardengatetab esbgardengatetab = this.esbgardengatetabService.getGardenGateById(gardengatelink.getIgardengateid());
	            
	            //List linkTypeList = this.sysparService.retrieve("GDLK");
	        	List linkTypeList = sysService.queryByPmkyPmcds("GDLK"," sys.id.pmcd not like'%*%' and isvalue=1 order by pmcd");
	            data.put("linkTypeList", linkTypeList);
	            
	            List gardenGateList = this.esbgardengatetabService.queryOtherGarden(esbgardengatetab.getId().getIscenicid(), esbgardengatetab.getId().getIgardengateid());
	            data.put("gardenGateList", gardenGateList);
	            
	            //产品列表
	            List productList = tickettypeService.getTickettypeListSelect(esbgardengatetab.getId().getIscenicid(),"01");
	            data.put("productList", productList);
	            
	            
	        }
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("关联园门详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "关联园门详情查询异常", "异常信息："+ e.getMessage());
		}
		
        
		return new ResponseBean(200, "操作成功", data);
    }
	
	/**
     * 关联园门列表
     *
     * @return
     */
	@GetMapping(value = "v1/linkList")
	@ApiOperation(value = "关联园门列表接口")
	public ResponseBean showLinkList(@RequestParam Long igardengateid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
       
		if (pageSize == null) {
			pageSize = 20;
		}
		if (page == null) {
			page = 1;
		}
        
		try {
			PaginationSupport ps = this.esbgardengatetabService.showLinkListByid(igardengateid, pageSize, page, null);
	        return new ResponseBean(200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("关联园门列表查询异常"+ StringUtil.toString_02(e));
            return new ResponseBean(400, "关联园门列表查询异常", "异常信息："+ e.getMessage());
		}
        
    }
	
}
