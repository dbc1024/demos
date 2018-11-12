package com.ectrip.ec.articlemanager.controller;

import java.util.List;

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

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.articlemanager.service.iservice.IArticleManagerService;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.home.service.iservice.IHomeService;
import com.ectrip.ec.model.articlemanager.Articlemanagertab;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("articleManager")
@Api(tags = "电商管理-网站信息管理-文章管理相关接口")
public class ArticleManagerController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(ArticleManagerController.class);
	
	@Autowired
	private SysparService sysService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IHomeService homeService;
	
	@Autowired
	private IArticleManagerService articleManagerService;
	/**
	 * 
	 * 文章列表查询
	 */
	@GetMapping("v1/list")
    @ApiOperation("文章列表查询接口")
	public ResponseBean showAllarticles(@RequestParam(required=false) Long cmid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		if(null==cmid) {cmid = 0L;}
		
		PaginationSupport ps = null;
		try {
			Domain domain = null;
			List<Domain> listDomain = homeService.getDomain(request.getServerName(), "1");
			if (listDomain != null && listDomain.size() > 0) {
				
				domain = listDomain.get(0);
				Long groupId = null;
				if (1 != domain.getSeq() && 2 != domain.getSeq()) {
					groupId = Long.parseLong(domain.getGroupId());
				}
				
				ps = articleManagerService.showlistArticlesByGroupid(groupId, cmid, pageSize, page, null);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("文章列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "文章列表查询异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
	}
	
	
	/**
	 * 文章详情查询
	 */
	@GetMapping("v1/detail/{amid}")
    @ApiOperation(value = "文章详情查询接口")
	public ResponseBean articleDetail(@PathVariable Long amid) {
		
		Articlemanagertab article = null;
		try {
			article = articleManagerService.viewArticle(amid);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("文章详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "文章详情查询异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功", article);
	}
	
	
	/**
     * 新增文章
     */
    @PostMapping("v1/add")
    @ApiOperation(value = "新增文章接口")
    public ResponseBean addArticle(@RequestBody Articlemanagertab article) {
    	
    	try {
    		//获取域名去查询企业对应的id
    		String url = request.getServerName();
    		Domain domain = null;
    		List<Domain> listDomain = homeService.getDomain(url,"1");
    		if(listDomain!=null && listDomain.size()>0){
    			domain = listDomain.get(0);
    		}
    		//在文章中添加企业id
    		article.setGroupid(domain.getGroupId());
    		Syslog syslog = new Syslog();
    		
    		Esfemployeetab esfemployeetab = sysService.currentUser();
    		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
    		
    		articleManagerService.insertArticle(article, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增文章异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增文章异常", "异常信息："+ e.getMessage());
		}
    	
		
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    	
    }
    
    /**
     * 修改文章
     */
    @PostMapping("v1/update")
    @ApiOperation(value = "修改文章接口")
    public ResponseBean updateArticle(@RequestBody Articlemanagertab article) {
    	
    	try {
    		//获取域名去查询企业对应的id
    		String url = request.getServerName();
    		Domain domain = null;
    		List<Domain> listDomain = homeService.getDomain(url,"1");
    		if(listDomain!=null && listDomain.size()>0){
    			domain = listDomain.get(0);
    		}
    		//在文章中添加企业id
    		article.setGroupid(domain.getGroupId());
    		Syslog syslog = new Syslog();
    		
    		Esfemployeetab esfemployeetab = sysService.currentUser();
    		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
    		
    		articleManagerService.updateArticle(article, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改文章异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改文章异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
    
    /**
	 * 删除文章
	 */
	@GetMapping("v1/delete/{amid}")
    @ApiOperation(value = "删除文章接口")
	public ResponseBean deleteArticle(@PathVariable Long amid) {
		
		try {
    		//获取域名去查询企业对应的id
    		String url = request.getServerName();
    		Domain domain = null;
    		List<Domain> listDomain = homeService.getDomain(url,"1");
    		if(listDomain!=null && listDomain.size()>0){
    			domain = listDomain.get(0);
    		}
    		
    		
    		Articlemanagertab article = new Articlemanagertab();
    		article.setAmid(amid);
    		
    		Syslog syslog = new Syslog();
    		
    		Esfemployeetab esfemployeetab = sysService.currentUser();
    		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
    		
    		articleManagerService.deleteArticle(article, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除文章异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除文章异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
    
}
