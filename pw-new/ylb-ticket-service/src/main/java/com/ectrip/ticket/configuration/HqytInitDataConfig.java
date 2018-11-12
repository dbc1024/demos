package com.ectrip.ticket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @ClassName: HqytConfig  
* @Description: TODO 初始化hqyt.properties
* @author jiyong  
* @date 2018年5月12日  
*/
@Component
@ConfigurationProperties(prefix = "hqyt")
public class HqytInitDataConfig {
	private String URL; //老接口
	private String NEWURL;//新结算接口访问
	private String KEY; //HQYTECTRIP1234&!@
	private String APP_DOWNLOAD_URL;// 电商app下载地址
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getNEWURL() {
		return NEWURL;
	}
	public void setNEWURL(String nEWURL) {
		NEWURL = nEWURL;
	}
	public String getKEY() {
		return KEY;
	}
	public void setKEY(String kEY) {
		KEY = kEY;
	}
	public String getAPP_DOWNLOAD_URL() {
		return APP_DOWNLOAD_URL;
	}
	public void setAPP_DOWNLOAD_URL(String aPP_DOWNLOAD_URL) {
		APP_DOWNLOAD_URL = aPP_DOWNLOAD_URL;
	}
}
