package com.sims.sso.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	
	

	public static JSONObject httpClient(String httpUrl) {
		StringBuffer response = new StringBuffer();
		
		try {
			URL url = new URL(httpUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			String inputLine;
		    while ((inputLine = in.readLine()) != null){
		    	response.append(inputLine);
		    }
		  
		    in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject parseObject = JSONObject.parseObject(response.toString());
		
		return parseObject;
	}
	
	
	
}
