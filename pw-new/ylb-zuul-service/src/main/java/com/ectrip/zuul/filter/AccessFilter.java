/*package com.ectrip.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.ectrip.zuul.entity.ResponseBean;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
 
import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
 
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
 
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
 
    @Override
    public int filterOrder() {
        return 0;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
       
        StringBuffer requestURL = request.getRequestURL();
        String tempRequestURL = requestURL.toString();
    	if(tempRequestURL.contains("/services")) {
    		 logger.info("获取token信息");
    		ctx.setSendZuulResponse(true);// 对该请求进行路由  
            ctx.setResponseStatusCode(200);  
            ctx.set("isSuccess", true);
            
    		return null;
    	}else {
//	        Object token = request.getParameter("token");
	        String token = request.getHeader("token");
	        //校验token
	        if (token == null || "".equals(token)) {
	            logger.info("token为空，禁止访问!");
	            ctx.setSendZuulResponse(false);
	            ctx.setResponseStatusCode(400);
	            ctx.setResponseBody(JSON.toJSONString(new ResponseBean(400, "token为空，禁止访问!", null)));
	            return null;
	            
	        }else{
	        	logger.info("");
	        	ctx.setSendZuulResponse(true);// 对该请求进行路由  
	            ctx.setResponseStatusCode(200);  
	            ctx.set("isSuccess", true);
	            ctx.set("Authentication", token);
	    		return null;
	        }
        }
    }
}
*/