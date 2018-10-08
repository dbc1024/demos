package stringTest;

import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class stringTest {

	@Test
	public void stringIsNumber() {

		String allNum= "123456789";
		String notAllNum= "1234567shu89";
		
		//��ʽһ
		Pattern pattern = Pattern.compile("[0-9]*");
		System.out.println(pattern.matcher(allNum).matches());
		System.out.println(pattern.matcher(notAllNum).matches());
		
		//��ʽ��
		System.out.println(allNum.matches("^[0-9]*$"));
		System.out.println(notAllNum.matches("^[0-9]*$"));

	}
	
	
	@Test
	public void splitTest() {

		String str = "18782918171|029124EDC5E180631627E3AE44108724|1";
		String[] strArr = str.split("|");
		System.out.println(strArr[0]);
		
		String[] strArr2 = str.split("[|]");
		System.out.println(strArr2[0]);
		
		
		
		String strPoint = "99.60";
//		String[] pointArr = strPoint.split(".");//pointArr==null
//		System.out.println(pointArr[0]);
		
		String[] pointArr2 = strPoint.split("\\.");
		System.out.println(pointArr2[0]);
	}

	
	@Test
	public void subTest() {
		String domain = "sso.rtzhisheng.com";
		int indexOf = domain.indexOf(".");
		System.out.println(indexOf);
		String substring = domain.substring(indexOf);
		System.out.println(substring);
		
		
		String str = "sso.rtzhisheng.com";
		String substring2 = str.substring(str.indexOf("."));
		System.out.println(substring2);
	}
	
	@Test
	public void jsonStr() {
		
		String hotelListQuery = "{'userid':'myID','pwd':'123','gowhere':'null','keyword':null,'rzdate':'[123,254]','lddate':'','grate':'ok','price':'ok'}";
//		String hotelListQuery = "{}";
		JSONObject query = JSONObject.parseObject(hotelListQuery);
		System.out.println(query.get("userid"));
		System.out.println(query.get("password") == null);
		System.out.println(query.get("gowhere").equals("null"));
		System.out.println(query.get("keyword") == null);
		
		
		System.out.println(query.get("rzdate"));
		
		
		
//		String[] rzdate = (String[])query.get("rzdate");//������ôת���ᱨ��
//		System.out.println(rzdate[0]);
		
		JSONArray parseArray1 = JSONArray.parseArray((String)query.get("rzdate"));//������Ҫ�����Ű�����
		System.out.println(parseArray1.get(1));
		
		String str= "['123','254']";
		JSONArray parseArray = JSONArray.parseArray(str);
		System.out.println(parseArray.get(0));
	}
	
	@Test
	public void split1111() {
		
		String str = "111";
		String[] split = str.split(",");
		System.out.println(split);
		
	}
	
	
	@Test
	public void split2222() {
		
		String[] strs = new String[]{"123","555"};
		String str = new String("123");
		String jsonString = JSON.toJSONString(strs);
		System.out.println(jsonString);
		
		String[] parseObject = JSON.parseObject(jsonString, String[].class);
		System.out.println(parseObject);
	}
	
	@Test
	public void split123() {
		
		System.out.println(3*0.1==0.3);
		
		
		double i = 0.3;
		double j = 3*0.1;
		System.out.println(j);
		
		System.out.println(i==j);
		
		double a = 0.3; 
		double b = 3*0.1; 
		BigDecimal data1 = new BigDecimal(a); 
		BigDecimal data2 = new BigDecimal(b); 
//		data1.compareTo(data2);
		System.out.println(data1.compareTo(data2));
	}
	
	
	@Test
	public void xiegangzhuanyi() {
		String str = "\\/";
		System.out.println(str);
		
		String str1 = "//";
		System.out.println(str1);
		
		String url = "https://qr.alipay.com/bax07701nk5hxkfbwqrl200d";
		
		boolean contains1 = url.contains("\\/");
		System.out.println(contains1);
		
		boolean contains2 = url.contains("//");
		System.out.println(contains2);
		
		url = Base64.encodeBase64String(url.getBytes());
		System.out.println(url);
		
	}
	
	//LinkedHashMap<String, Object> root=JSON.parseObject(
	
	@SuppressWarnings("unchecked")
	@Test
	public void jsonObjectTest() {
		
		String str = "123456789987654321";
		System.out.println("长度："+str.length());
		System.out.println("2所在的位置："+str.indexOf("2"));
		System.out.println("最后一个2所在的位置："+str.lastIndexOf("2"));
		
		str = str.substring(1, str.length()-1);
		System.out.println(str);
		
//		String response = str.substring(str.indexOf("{"), str.indexOf("}")+1);
//		System.out.println(response);
//		
//		str = str.substring(0, str.length() - 1);
//		System.out.println(str);
//		
//		String sign = str.substring(str.lastIndexOf("\"")+1);
//		System.out.println(sign);
		


	}
	
	
	@Test
	public void encodeTest() throws UnsupportedEncodingException {
//		URLDecoder("");
		String str = "gmt_create=2018-08-20+18%3A19%3A46&charset=UTF-8&seller_email=zfbtest25%40service.aliyun.com&subject=QingFengPaper"
				+ "&sign=ewmZzXqR8oy24L1RlOg0DnGyrcxJhMvY5%2B3GEB1loBvT5jMPa2v8%2Bm5vQLmMo87gAjauwxZsz4OSIOMCilawwvTelVd1uVGz8Zh8xnu0v9SW9iku3mg8KDK5tnErn9jLeLD2HKDM0CU98yJGHFLGEPWaOietbnx%2Fr90kbZ3fnPFKg97%2BPIeQEy3Ix2gldnUqMeGP0fhpNGzKEZ7V7jNXw6CbUEDct%2B4EQzIKE2rK%2Ff0jFJzXhoucGI%2FL6pN547Bg7YBCRxPBhA4hapAC2VbRc%2BZRPgg%2Fcr62Bk78Ow38b4F66hS96A6r2x7AHuEyB6N3rLidFQC%2FoeSjLIlp%2FiPquw%3D%3D"
				+ "&buyer_id=2088502174676347&invoice_amount=0.01&notify_id=14dbcb323b58bc2bfba1bc9732cfc8diml&fund_bill_list=%5B%7B%22amount%22%3A%220.01%22%2C%22fundChannel%22%3A%22ALIPAYACCOUNT%22%7D%5D&notify_type=trade_status_sync&trade_status=TRADE_SUCCESS&receipt_amount=0.01&app_id=2016091700534528&buyer_pay_amount=0.01&sign_type=RSA2&seller_id=2088911212416201&gmt_payment=2018-08-20+18%3A20%3A13&notify_time=2018-08-20+18%3A34%3A16&version=1.0&out_trade_no=20180818000027&total_amount=0.01&trade_no=822018082021001004340584810490&auth_app_id=2015051100069170&buyer_logon_id=hua***%40sina.com&point_amount=0.00";
		String decode = URLDecoder.decode(str, "UTF-8");
		System.out.println(decode);
		
		String[] paraArr = decode.split("&");
		System.out.println(paraArr);
		
		TreeMap<String, String> sortData = new TreeMap<String, String>();
		for (String key_value : paraArr) {
			String[] key_value_arr = key_value.split("=");
			sortData.put(key_value_arr[0], key_value_arr[1]);
		}
		
		sortData.remove("sign");
		sortData.remove("sign_type");
		
		System.out.println(sortData);
		
	}
	
	
	@Test
	public void longStringTest() {
		String price = "1";
//		long parseLong = Long.parseLong(price);
		Long valueOf = Long.valueOf(price);
		System.out.println(valueOf);
		
		String string = BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
		System.out.println(string);
	}
}
