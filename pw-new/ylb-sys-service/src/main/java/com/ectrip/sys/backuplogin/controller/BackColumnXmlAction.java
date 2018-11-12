package com.ectrip.sys.backuplogin.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.sys.backuplogin.service.IOperationService;
import com.ectrip.sys.model.backupadmin.Operation;
import com.ectrip.sys.model.backupadmin.OperationId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(tags = "系统模块-菜单相关接口")
public class BackColumnXmlAction{
	/**
	 * 
	 */
	@Autowired
	private IOperationService operationService;
	
	Operation operation;
	Document doc;

	/**
	 * 后台XML菜单数据返回
	 * 后台菜单项进行修改，以前不能多选，就是两个岗位如果有重复的
	 * 职责时，会发生重复的现象。2012-07-11进行修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String makemxl(HttpServletResponse response) throws Exception {
		try {
			
				
				String empid = null;//usernameAndPassword.get("empId").toString();
				if (operation == null) {
					operation = new Operation();
					OperationId opid = new OperationId();
					opid.setPopid(new BigDecimal(0));
					operation.setId(opid);
				}
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");
				/*ActionMapping mapping = ServletActionContext.getActionMapping();

				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setContentType("text/xml");
				response.setCharacterEncoding("utf-8");*/
				// if ( operationService == null)
				// operationService = SpringUtil.getBean(")
				//李进修改，增加对LIC对菜单的控制
				//System.out.println("adminlogin.emptuy =" + adminlogin.getEmptuy() );
			//	System.out.println("adminlogin.emptuy =" + adminlogin.getEmptuy() );

				/*if (adminlogin.getEmptuy() == null || adminlogin.getEmptuy().equals("") ||  adminlogin.getEmptuy().equalsIgnoreCase("no") )
				{
					 //System.out.println("adminlogi 1" );
				     doc = operationService.createXMLByBackColumn(mapping.getName(), empid, operation.getId().getPopid());
				}
				else
				{
					// System.out.println("adminlogi 2" );
						//System.out.println("operationService =" + operationService );
					 //doc = operationService.createXMLByBackColumnLic(mapping.getName(), empid, operation.getId().getPopid(),adminlogin.getEmptuy());	

					doc=operationService.createXMLByBackColumn(mapping.getName(), empid, operation.getId().getPopid());
					System.out.println("doc的值是"+" "+doc);
				}*/
				//李进2013-01-18 修改结束
				//System.out.print("权限licid="+3);
				doc=operationService.createXMLByBackColumn("", empid, operation.getId().getPopid());
				Format format = Format.getCompactFormat();
				format.setEncoding("utf-8");
				format.setIndent(" ");
				XMLOutputter outputter = new XMLOutputter(format);
				outputter.output(doc, response.getWriter());
				
		} catch (IOException e) {
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/xml");
			response.getWriter().print("<tree>");
			response.getWriter().print("<login/>");
			response.getWriter().print("</tree>");
			return null;

		}

		return null;
	}
	@PostMapping("/v1/getMenuInfo")
	@ApiOperation(value = "获取菜单信息")
	public List<?> getMenuInfo(@RequestParam String empId,@RequestParam BigDecimal popid){
		if(popid == null) {
			popid = new BigDecimal(0);
		}
		List<?> findbyempid = operationService.findbyempid(empId, popid);
		return findbyempid;
	}
}
