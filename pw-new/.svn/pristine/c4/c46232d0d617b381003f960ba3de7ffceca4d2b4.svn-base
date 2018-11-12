/*package com.ectrip.base.action;

import com.ectrip.model.employee.Esfemployeetab;
//import com.opensymphony.xwork2.ActionContext;
*//**
 * 后台ACTION中所有的ACTION必须继承的类主类，里面有权限判段
 * @author lijin
 *
 *//*
public class BackBaseAction extends BaseAction {
	*//**
	 * 后台根据读取的服务商判断该用户是否有管理该服务商的信息
	 * @param scince
	 * @return
	 *//*
	public boolean Judgescenic(Long scince) {
		boolean judge = false;
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		if (esfemployeetab.getCompanytype().equals("02")) {
			String scenics = esfemployeetab.getScenics();
			if (scenics != null && !scenics.equals("")) {
				String[] scenicss = scenics.split(",");
				for (int i = 0; i < scenics.length(); i++) {
					if (Long.parseLong(scenicss[i]) == scince) {
						judge = true;
						break;
					}
				}
			}
		} else {
			judge = true;
		}
		
		return judge;
	}
}
*/