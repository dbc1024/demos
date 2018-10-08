package httpTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {

	@Test
	public void test1() {
		//6216613100020468529
		String urlstr = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=6221886712000985403&cardBinCheck=true";
		StringBuffer response = new StringBuffer();
		
		try {
			URL url = new URL(urlstr);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			String inputLine;
		    while ((inputLine = in.readLine()) != null){
		    	response.append(inputLine);
		    }
		  
		    in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(response);
		
		
		JSONObject parseObject = JSONObject.parseObject(response.toString());
		System.out.println(parseObject);
		System.out.println(parseObject.get("bank"));
		
	}
	
	
	
	@Test
	public void test2(){
		
		String urlstr = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=9558822010005085629&cardBinCheck=true";
		
		StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(urlstr);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  

            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
           e.printStackTrace();
        }  
        
        System.out.println(buffer);
        
	}
	
	
	@Test
	public void test3(){
		
		String urlstr = "http://192.168.0.111:8082/uaa/oauth/token?username=admin&password=12345678"
				+ "&grant_type=password&scope=app&client_id=android&client_secret=android";
//		String urlUser = "http://192.168.0.111:8082/sys/employee/v1/currentUser?access_token=69cc860a-79a6-4b04-acce-a444e367955e";
		StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(urlstr);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("POST");  
            httpUrlConn.connect();  

            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
           e.printStackTrace();
        }  
        
        System.out.println(buffer);
        
	}
	
	@Test
	public void test4(){
		
		String urlstr = "http://w1.test.com/ledScreen/data.jsp";
		
		StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(urlstr);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("POST");  
            httpUrlConn.connect();  

            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
           e.printStackTrace();
        }  
        
        System.out.println(buffer);
        
	}


}
