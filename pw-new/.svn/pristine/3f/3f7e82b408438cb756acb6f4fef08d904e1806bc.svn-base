package com.ectrip.ticket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: ReportServerConfig  
* @Description: 由于报表无法拆分成单独的服务，只是单独配置报表的服务器地址  
* @author jiyong  
* @date 2018年5月12日  
*
 */
@Component
@ConfigurationProperties(prefix = "reportServer")
public class ReportServerConfig {
	private String host;
	private int port;
	private String contextPath;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}
