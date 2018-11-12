package com.ectrip.ticket.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @ClassName: EctripInitDataConfig  
* @Description: TODO 初始化原ectrip.xml文件数据
* @author jiyong  
* @date 2018年5月12日  
*
 */
@Component
@ConfigurationProperties(prefix = "ectrip")
public class EctripInitDataConfig {
	
	/**
	 * 将数据注入Map中
	 */
	private Map<String,Object> systemMap = new HashMap<String,Object>();

	public Map<String, Object> getSystemMap() {
		return systemMap;
	}
	public void setSystemMap(Map<String, Object> systemMap) {
		this.systemMap = systemMap;
	}
}
