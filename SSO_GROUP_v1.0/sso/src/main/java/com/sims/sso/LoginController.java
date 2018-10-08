package com.sims.sso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	List<String> tokenList = new ArrayList<String>();

	@RequestMapping("/checkLogin")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	
		HttpSession session = request.getSession();
		String domain = request.getParameter("domain");
		
		
		//如果不为空，说明已经有系统登录，那么检测此次请求系统是否已登录，未登录则注册系统并登录。
		if(session.getAttribute("loginUser") != null){
			
			
			String domains = (String)session.getAttribute("domains");
			String[] domainArr = domains.split(",");
			
			boolean flag = false;//此次访问系统是否登录标志
			
			for (int i = 0; i < domainArr.length; i++) {
				if(domain.equals(domainArr[i])){
					
					flag = true;
					break;
				}
			}
			
			if(!flag){
				
				//为了预防用户的非法操作，还可添加的优化逻辑：将所有跟sso关联的域名存起来，对比当前传入域名是否在在这些域名中。在，才存入session中。
				domains = domains + domain + ",";
				session.setAttribute("domains", domains);
				Long token = System.currentTimeMillis();
				//session.setAttribute("token", token);//放入session中，后面的取存在问题
				tokenList.add(token.toString());
				
				String username = (String)session.getAttribute("loginUser");
				
				
				return "redirect:http://"+ domain +"/login/receiveToken?token="+ token + "&loginUser="+ username;
				
			}
			
			return "redirect:http://"+ domain +"/login.html";
			
		}else{
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if("123".equals(username) && "666666".equals(password)){
				
				session.setAttribute("loginUser", username);
				
				//注册已登录系统(保存已登录系统，注销时需要知道哪些系统已经登录)
				String domains = domain + ",";
				session.setAttribute("domains", domains);
				
				Long token = System.currentTimeMillis();
				//session.setAttribute("token", token);//放入session中，后面的取存在问题
				tokenList.add(token.toString());
				
				return "redirect:http://"+ domain +"/login/receiveToken?token="+ token + "&loginUser="+ username;
			}
			
			return "redirect:http://"+ domain +"/login.html";
		}
	}
	
	@RequestMapping("/verifyToken")
	@ResponseBody
	public Map<String, Object> verifyToken(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		//待验证的token
		String token = request.getParameter("token");
		if(token == null && "".equals(token)){
			result.put("success", false);
			result.put("message", "token is blank");
			
			return result;
		}
		
		//与已生成的token一一比较
		int index = -1;
		for (int i = 0; i < tokenList.size(); i++) {
			
			if(token.equals(tokenList.get(i))){
				index = i;
			}
		}
		
		//比较成功则删除对应token，去除安全隐患。
		if(index == -1){
			result.put("success", false);
			result.put("message", "error token");
			
		}else {
			tokenList.remove(index);
			
			result.put("success", true);
			result.put("message", "verify token success");
		}
		
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		String domain = request.getParameter("domain");
		
		if(session.getAttribute("loginUser") == null){
			
			return "redirect:http://"+ domain +"/login.html";
		}
		
		String domains = (String)session.getAttribute("domains");
		String[] domainArr = domains.split(",");
		List<String> domainList = Arrays.asList(domainArr);//不支持remove方法，进行如下转换
		domainList = new ArrayList<String>(domainList);
		
		int size = domainList.size();
		
		//循环注销所有子系统
		if(size>0 && !"".equals(domains)){
			String outDomain = domainList.get(0);
			domainList.remove(0);
			
			domains = "";
			for (String leaveDomain : domainList) {
				domains = domains + leaveDomain + ",";
			}
			session.setAttribute("domains", domains);
			
			//记录手动发起注销的系统
			Object firstOutDomain = session.getAttribute("firstOutDomain");
			if(firstOutDomain == null){
				session.setAttribute("firstOutDomain", domain);
			}
			
			return "redirect:http://"+ outDomain +"/login/ssoLogout";
		}else {
			
			String firstOutDomain = (String)session.getAttribute("firstOutDomain");
			
			//所有子系统注销完成后，注销sso
			session.invalidate();
			
			//跳转到发起注销系统的登录页
			return "redirect:http://"+ firstOutDomain +"/login.html";
		}
		
	}
}
