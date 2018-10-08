package language;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Article {
	
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
	
	/**
	 * @Title:	What is the meaning of life?
	 * @Author:	Joker
	 * @Date:	2018-05-03
	 * @Description: Why do we feel that life is meaningless?
	 
	 	          其实啊，我们要回答为什么，首先我们应该问一句是不是。所以，活着是不是有意义？
	 	          
			我想，活着本身大概并没有意义。
			
			假如，某一天你迷迷糊糊醒来，发现自己在一个荒岛上。你不知道发生了什么事情，你
		不知道自己为什么在这儿，所以你自然不知道在这儿的意义是什么，甚至连意义都谈不上。
		
			其实这像极了我们的人生，我们没有主动选择参与到这个世界中，我们是被迫来到这个
		世界的。甚至我们连被谁逼迫，被什么原因逼迫都不知道。以这样的一种方式来到世界上，
		你告诉我，你觉得继续呆下去有意义吗？
		
			其实，到这里，不管对不对，我想已经切题回答了这个问题。但其实大部分问这个问题
		的人都还很年轻，大家并不是真正地觉得生活没有意义了，大家只是遇到了人生阶段性的困难。
		辛苦付出，收获甚少，生活困苦，看不到希望。
		
			所以，可以把这个问题稍稍引申一下。虽然活着本身没有意义，但很不幸的是，我们被迫来到
		这个世界的时候还被赋予了一样东西————好奇心。因为好奇心的存在，我们不可避免地产生了探
		索这个世界地欲望。在探索的过程中，我们无法避免地对这个世界产生了更多的欲望。比如：你想
		吃那块巴掌大的棒棒糖；你想得到班上最多的小红花；你想考个好成绩让父母给你买台电脑；
		突然你想谈恋爱了，但你还在上高中，但你还是谈了；突然你还是想上个好大学，所以你又每天
		认真学习了;突然就该找工作了，所以你想着怎么把简历编得好看些；突然又该考虑结婚买房买车
		了，所以你每天认真地应付着工作。
		
			所以，发现了吗？我们之所以这么不知疲倦地奔走着，只是因为我们产生了某些欲望，从而
		催生出了我们需要一步一步去完成的目标。说得更有温度些就是：我们的生活还有盼头。
		
			所以，冷静的想一想。你想不想从这个世界带走点什么，或者想不想给这个世界留下点什么。
		如果想，那么生活对于你就还是有意义的。
		
			如果这个世界还有你想去体验的，还有你想去感受的东西；对这个世界还有你想去分享，你
		想去表达，甚至你想去反抗的东西。那就不要轻易为了眼前的困难而去怀疑生活的意义。
		
			来都来了，你就不好奇吗？说不定你是花了高价钱才买到的困难模式来到这个世界的。
	 */
	public String findMeaningOfLife() {
		
		return "Sorry,little guy. There is no meaning of life.";
	}
	
	/**
	 * 《小巷》
	 * 
	 * 小巷
	 * 
	 * 又弯又长
	 * 
	 * 没有门
	 * 
	 * 没有窗                          
	 * 
	 * 我拿把旧钥匙
	 * 
	 * 敲着厚厚的墙
	 * 
	 * 
	 */

}
