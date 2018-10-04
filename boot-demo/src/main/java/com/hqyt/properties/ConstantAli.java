/**
 * 
 */
package com.hqyt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* @Description: TODO
* @Author: CSZ
* @Date: 2018-08-27 09:19:39
*/
@Component
@ConfigurationProperties(prefix = "ali")//这里的hello对应的就是my.properties里的属性前缀
@PropertySource("classpath:test.properties")//这是属性文件路径
public class ConstantAli {
	
	private String payUrl;
	
	private String notityUrl;

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getNotityUrl() {
		return notityUrl;
	}

	public void setNotityUrl(String notityUrl) {
		this.notityUrl = notityUrl;
	}
	
	

}
