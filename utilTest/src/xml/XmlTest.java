package xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class XmlTest {

	@Test
	public void test123() {
		String bookXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
						"<bookstore>" + 
						"    <book id=\"1\">" + 
						"        <name>冰与火之歌</name>" + 
						"        <author>乔治马丁</author>" + 
						"        <year>2014</year>" + 
						"        <price>89</price>" + 
						"    </book>" + 
						"    <book id=\"2\">" + 
						"        <name>安徒生童话</name>" + 
						"        <year>2004</year>" + 
						"        <price>77</price>" + 
						"        <language>English</language>" + 
						"    </book>" + 
						"</bookstore>";
		
		
		
		try {
			Document  document = DocumentHelper.parseText(bookXml);
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                Element book = (Element) it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："
                            + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                }
                System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

	


    public String map2xml(Map<String, String> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("rootElement");
        Set<String> keys = map.keySet();
        for(String key:keys) {
            root.addElement(key).addText(map.get(key));
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        return sw.toString();
    }



    @Test
    public void testMsg() {
        try {
            Map<String,String> maps = new HashMap<String, String>();
            maps.put("name", "abc");
            maps.put("age", "12");
            maps.put("desc", "<abc>ddd</abc>");
            System.out.println(map2xml(maps));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
    @Test
    public void mapToJson() {
        String str = "{\"external_id\":\"10529005999019\"," +
						"\"name\":\"国际物流公司\"," +
						"\"alias_name\":\"国务流\"," +
						"\"service_phone\":\"95188\"," +
						"\"category_id\":\"2015050700000000\"," +
						"\"source\":\"2088102170346152\"," +
						"\"contact_info\":[{" +
							"\"name\":\"95188\"," +
							"\"mobile\":\"13888888888\"," +
							"\"tag\":\"06\"," +
							"\"type\":\"LEGAL_PERSON\"}]," +
						"\"address_info\":[{\"province_code\":\"370000\",\"city_code\":\"371000\",\"district_code\":\"371002\",\"address\":\"万塘路18号黄龙时代广场B座\",\"longitude\":\"120.760001\",\"latitude\":\"60.270001\",\"type\":\"BUSINESS_ADDRESS\"}]," +
						"\"bankcard_info\":[{\"card_no\":\"62261000200030004000\",\"card_name\":\"一姜\"}],"+
						"\"pay_code_info\":[\"https://www.web.com/cashier\"]," +
						"\"logon_id\":[\"hello@domain.com\"]," +
						"\"memo\":\"memo1495004082582\"" +
						"}";
        
        System.out.println(str);
        
        
        Map mapTypes = JSON.parseObject(str);
        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
        for (Object obj : mapTypes.keySet()){
            System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
        }
        
        
        String jsonString = JSONArray.toJSONString(mapTypes);
        System.out.println(jsonString);
        
    }
    
    
  
    
    @Test
    public void mapJson() {
    	//构建请求银联支付宝支付接口的数据集合
        Map<String, String> contentData = new HashMap<String, String>();
        contentData.put("method", "ant.merchant.expand.indirect.create");
        
        //请求参数集合，除公共参数外，所有请求参数都必须放在biz_content这个参数中传递.公共参数以json字符串的形式作为value
        contentData.put("biz_content", "");
        
        Map<String, Object> biz_content = new HashMap<String, Object>();
        /** biz_content Begin******************************************************************************/
        biz_content.put("external_id", "external_id");
        biz_content.put("name", "external_id");//商户名称
        biz_content.put("alias_name", "external_id");
        biz_content.put("service_phone", "external_id");
        biz_content.put("mcc", "external_id");
        biz_content.put("source", "external_id");
        biz_content.put("org_pid", "external_id");
        biz_content.put("contact_info", "");//商户联系人信息
        
        Map<String, Object> contact_info = new HashMap<String, Object>();
        /** biz_content -> contact_info Begin***************************************************************/
        contact_info.put("name", "external_id");//商户联系人名字
        contact_info.put("tag", "external_id");//商户联系人业务标识枚举，表示商户联系人的职责.
        contact_info.put("type", "external_id");
        /** biz_content -> contact_info End*****************************************************************/
        /** biz_content End*********************************************************************************/
        biz_content.put("contact_info", contact_info);//商户联系人信息
        String biz_contentJson = JSONArray.toJSONString(biz_content);
        System.out.println(biz_contentJson);
        contentData.put("biz_content", biz_contentJson);
        
    }
    
    @Test
	public void test111() {
		String bookXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		System.out.println(bookXml);
    }
    
    @Test
   	public void test345() {
    	String str = "\"{";
    	System.out.println(str);
    }
    
    
}
