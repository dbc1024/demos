package com.ectrip.base.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
/**
 * 天气预报处理类
 * @author lijin
 *
 */
public class Weather {

	private static  String CITY =  WebContant.GetKeyValue("CITYNAME");
	// 省里面各个市的天气
	/*private static final String XML_URL = WebContant.GetKeyValue("WeatherXML")
			+ WebContant.GetKeyValue("CITYNAME") + ".xml";
*/
   private static final String XML_URL = "http://flash.weather.com.cn/wmaps/xml/aba.xml";
   // private static String AREANAME =  WebContant.GetKeyValue("AREANAME");
	
    
    private static String AREANAME =  "阿坝";
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Weather w = new Weather();
			String realPath = "e:/tmp";
			w.getWeather(realPath);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void getWeather(String realPath) {
		SAXBuilder builder = new SAXBuilder();
		try {
		//	CITY = AREANAME;
			builder.setEntityResolver(new NoOpEntityResolver());
			 System.out.println("XML_URL="+XML_URL);
			Document doc = builder.build(XML_URL);
			Element weather = doc.getRootElement();
			Element city = null;
			List list = weather.getChildren();
			for (int i = 0; i < list.size(); i++) {
				city = (Element) list.get(i);
				if (city.getAttributeValue("cityname").equals(AREANAME)) {
					break;
				}
			}
			Element root = new Element("weather");
			Document document = new Document(root);
			Attribute update = new Attribute("update", Tools.getDayTimes());
			root.setAttribute(update);
			// city
			Element newcity = new Element("city");
			newcity.setAttribute("name", AREANAME);

			Element date = city;
			Element newdate = new Element("date");
			if (date.getAttributeValue("cityX") != null) {
				newdate.setAttribute("cityX", date.getAttributeValue("cityX"));
			}
			if (date.getAttributeValue("cityY") != null) {
				newdate.setAttribute("cityY", date.getAttributeValue("cityY"));
			}
			if (date.getAttributeValue("state1") != null) {
				newdate.setAttribute("state1", date.getAttributeValue("state1"));
			}
			if (date.getAttributeValue("state2") != null) {
				newdate.setAttribute("state2", date.getAttributeValue("state2"));
			}
			if (date.getAttributeValue("stateDetailed") != null) {
				newdate.setAttribute("stateDetailed", date.getAttributeValue("stateDetailed"));
			}
			if (date.getAttributeValue("tem1") != null) {
				newdate.setAttribute("tem1", date.getAttributeValue("tem1"));
			}
			if (date.getAttributeValue("tem2") != null) {
				newdate.setAttribute("tem2", date.getAttributeValue("tem2"));
			}
			if (date.getAttributeValue("temNow") != null) {
				newdate.setAttribute("temNow", date.getAttributeValue("temNow"));
			}
			if (date.getAttributeValue("windState") != null) {
				newdate.setAttribute("windState", date.getAttributeValue("windState"));
			}
			if (date.getAttributeValue("windDir") != null) {
				newdate.setAttribute("windDir", date.getAttributeValue("windDir"));
			}
			if (date.getAttributeValue("windPower") != null) {
				newdate.setAttribute("windPower", date.getAttributeValue("windPower"));
			}
			if (date.getAttributeValue("humidity") != null) {
				newdate.setAttribute("humidity", date.getAttributeValue("humidity"));
			}
			if (date.getAttributeValue("url") != null) {
				newdate.setAttribute("url", date.getAttributeValue("url"));
			}
			
			newcity.addContent(newdate);
			root.addContent(newcity);
			XMLOutputter XMLOut = new XMLOutputter();
			try {
				XMLOut.output(document, new FileOutputStream(realPath + "/weather.xml"));

				// 读取未来几天天气,生成weatherjson.js
				URL url = new URL(WebContant.GetKeyValue("WeatherXMLData") + date.getAttributeValue("url") + ".html");
				//System.out.println(WebContant.GetKeyValue("WeatherXMLData") + date.getAttributeValue("url") + ".html");
			//	URL url = new URL("http://m.weather.com.cn/data/" + date.getAttributeValue("url") + ".html");
				URLConnection c = url.openConnection();
				c.connect();
				InputStreamReader read = new InputStreamReader(c.getInputStream(), "utf-8");
				BufferedReader bfread = new BufferedReader(read);
				String sCurrentLine = "";
				String sTotalString = "";
				while ((sCurrentLine = bfread.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(realPath + "/weatherjson.js"),
						"utf-8");
				write.write(sTotalString);
				write.close();
				
				// 2014-03-15 新加李进
				System.out.println("生成 /weatherList.xml 开始");
				URL xmlurl= new URL(XML_URL);
				System.out.println("生成 /weatherList.xml 开始1");
				URLConnection cMore = xmlurl.openConnection();
				
				System.out.println("生成 /weatherList.xml 开始2");
				cMore.connect();
				System.out.println("生成 /weatherList.xml 开始3");
				InputStreamReader readMore = new InputStreamReader(cMore.getInputStream(), "utf-8");
				System.out.println("生成 /weatherList.xml 开始4");
				BufferedReader bfreadMore  = new BufferedReader(readMore);
				
				System.out.println("生成 /weatherList.xml 开始5");
				String sCurrentLineMore = "";
				System.out.println("生成 /weatherList.xml 开始6");
				String sTotalStringMore = "";
				while ((sCurrentLineMore = bfreadMore.readLine()) != null) {
				  sTotalStringMore = sTotalStringMore + sCurrentLineMore;
					System.out.println("生成 /weatherList.xml 开始7");
				}
				
				System.out.println(realPath + "/weatherList.xml");
				
				OutputStreamWriter writeMore = new OutputStreamWriter(new FileOutputStream(realPath + "/weatherList.xml"), 
				  "utf-8");
				System.out.println("生成 /weatherList.xml 开始7");
				System.out.println("生成 /weatherList.xml 开始" + sTotalStringMore);
				writeMore.write(sTotalStringMore);
				writeMore.close();

				// 2014-03-15 新加结束
				
			} catch (Exception e) {
				System.out.println("url: 不对,不能访问 m.weather.com.cn");
				System.out.println("url: 请配置 ectrip.xml 中  WeatherXML 和 WeatherXMLData");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
