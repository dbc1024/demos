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
* @Date: 2018-08-27 09:39:13
*/
@Component
@ConfigurationProperties(prefix = "wx")//这里的hello对应的就是my.properties里的属性前缀
@PropertySource("classpath:test.properties")//这是属性文件路径
public class ConstantWX {

	public static String payUrl;
	
	public static String notityUrl;

	public static String getPayUrl() {
		return payUrl;
	}

	public static void setPayUrl(String payUrl) {
		ConstantWX.payUrl = payUrl;
	}

	public static String getNotityUrl() {
		return notityUrl;
	}

	public static void setNotityUrl(String notityUrl) {
		ConstantWX.notityUrl = notityUrl;
	}
	
}
