/**
 * 
 */
package com.hqyt.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* @Description: TODO
* @Author: CSZ
* @Date: 2018-08-27 09:39:13
*/

@Component
//@ConfigurationProperties(prefix = "hqyt")
//这是属性文件路径,classpath指的是发布时classes文件的路径，resource更目录下的文件都会发布到classes路径下
@PropertySource("classpath:/properties/test2.properties")
public class ConstantHqyt {
	
	public static String payUrl;
	
	public static String notityUrl;
	
	public static String PASSWORD;

	public static String getPayUrl() {
		return payUrl;
	}

	//set方法不要用static修饰，否则无法注入值
	@Value("${hqyt.payUrl}")  
	public static void setPayUrl(String payUrl) {
		ConstantHqyt.payUrl = payUrl;
	}

	public static String getNotityUrl() {
		return notityUrl;
	}

	@Value("${hqyt.notityUrl}")
	public void setNotityUrl(String notityUrl) {
		ConstantHqyt.notityUrl = notityUrl;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	@Value("${hqyt.password}")
	public void setPASSWORD(String pASSWORD) {
		ConstantHqyt.PASSWORD = pASSWORD;
	}
	
	
}
