package com.ectrip.ticket.sale.service.card.core;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.Tools;

public class CoreUtil {

	/**
	 * 
	 * Describe: ����MD5
	 * @author:
	 * @param in
	 * @param charSet �ַ���
	 * @return
	 * return:String
	 * Date:2014-9-16
	 */
	public static String signMD5(String in,String charSet) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] bytes = md.digest(in.getBytes(charSet)); 
			StringBuilder sb = new StringBuilder(); 
			for (int i = 0; i < bytes.length; i++) { 
				String hex = Integer.toHexString(0xff & bytes[i]); 
				if (hex.length() == 1) 
					sb.append('0'); 
				sb.append(hex); 
			} 
			return sb.toString(); 
		} catch (Exception e) { 
			return null; 
		
		} 
	}
	
	/**
	 * 	
	 * Describe: ����MD5
	 * @author:
	 * @param in
	 * @return
	 * return:String
	 * Date:2014-9-16
	 */
	public static String signMD5(String in) { 
		return signMD5(in,"UTF-8");
	}

	/**
	 * 
	 * Describe: base64���ܺ��ַ�������
	 * @author:
	 * @param base64
	 * @return
	 * return:String
	 * Date:2014-9-16
	 */
	public static String decodeBase64(String base64){
		try {
			return new String(Base64.decodeBase64(base64.getBytes("UTF-8")),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Describe: �ַ�������base64����
	 * @author:
	 * @param str
	 * @return
	 * return:String
	 * Date:2014-9-16
	 */
	public static String encodeBase64(String str){
		try {
			return new String(Base64.encodeBase64(str.getBytes("UTF-8")),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public static boolean checkPassword(String str_pwds, String str_passwd) {
		try {
			String myinfo = str_pwds + "ectrip"; //�Զ����MD5
			MessageDigest alga = MessageDigest.
					getInstance("MD5");
			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			String ls_str = byte2hex(digesta);
			return ls_str.equals(str_passwd);
		}catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("û����������㷨����JDK�汾");
		}
		return false;
	}
	
	public static String byte2hex(byte[] b) { //������ת�ַ���
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			}
			else {
				hs = hs + stmp;
			}
			//            if (n < b.length - 1) {
			////                hs = hs;
			//            }
		}
		return hs.toUpperCase();
	}
	
	/**
	 * Describe:���·�����װСƱ
	 * @author liujianwen
	 * @param list
	 * @param arr
	 * return:void
	 * Date:2014-8-13
	 */
	public static void sp3(List<String> list,String[] arr){
		for(int j = 0; j < arr.length;j ++){
			switch (j) {
			case 1:
				list.add(addSpace(arr[j],18,true));
				break;
			case 5:
				list.add("");
				list.add(arr[j]);
				list.add("");
				break;
			default:
				list.add(arr[j]);
				break;
			}
		}
	}
	
	
	public static void sp2(List<String> list,String[] arr){
		StringBuilder sb;
		for(int j = 0; j < arr.length;j ++){
			if((j+1)%3==0){
				list.add(arr[j-2]);
				sb = new StringBuilder();
				sb.append(addSpace(arr[j-1], 10,true));
				sb.append(addSpace(arr[j], 12-arr[j-1].length()-1,true));
				list.add(sb.toString());
			}
		}
		list.add("------------------------------");
	}
	
	
	public static void sp1(List<String> list,String[] arr){
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < arr.length;j ++){
			switch (j) {
			case 7:
				list.add(arr[j]);
				list.add("");
				break;
			case 8:
				sb.append(addSpace(arr[j],6,false));
				break;
			case 9:
				sb.append(addSpace(arr[j],4,false));
				break;
			case 10:
				sb.append(arr[j]);
				break;
			default:
				list.add(arr[j]);
			}
		}
		list.add(sb.toString());
		list.add("------------------------------");
	}
	
	
	public static String addSpace(String source,int count,boolean isbefore){
		StringBuilder sb = new StringBuilder();
		sb.append(isbefore?"":source);
		for(int i = 0 ; i < count ; i ++){
			sb.append(" ");
		}
		sb.append(isbefore?source:"");
		return sb.toString();
	}
	
	
	public static List<String> packageReceipt(String receipt){
		if(receipt == null || "".equals(receipt)) return null;
		try {
			List<String> list = new ArrayList<String>();
			String [] arr,arrs = receipt.split("&");
			arr = arrs[0].split(",");
			System.out.println("arr0.length:"+arr.length);
			sp1(list,arr);
			arr = arrs[1].split(",");
			System.out.println("arr1.length:"+arr.length);
			sp2(list,arr);
			arr = arrs[2].split(",");
			System.out.println("arr2.length:"+arr.length);
			sp3(list,arr);
			return list;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
		
	public static void main(String[] args) {
		System.out.println(makeRandomPwd());
//		String str = "�����Ļ���lll";
//		String ff = encodeBase64(str);
//		System.out.println(ff);
//		System.out.println(decodeBase64(ff));
//		String sq = "��Ʊ��ִ(��Ʊ��Ʊ�ɹ�),POSվ��:���δ���POS��1,�û����:lxs02,�û�����:ffsf,����ƾ֤��:4148053,��λ����:efewfwe_,����Ա:ljw,��Ʊվ��:������Ʊվ��,,��Ʒ      ����X����    ���,------------------------------,���η羰����Ʊ(����Ʊ),          23.0��2     46.0,------------------------------,,                  �ϼ�:46.0,�����û�:ljw,�����ص�:������Ʊվ��,��ӡʱ��:2014-08-13 18:18:14,,��Ʊ��ǩ��:,,��ӭʹ�ö���OTO��Ʊϵͳ,��ַ:null,��ע,��ܰ��ʾ";
////		String sq = "�ش���Ʊ��ִ(�ѳ�Ʊ),POSվ��:���δ���POS��1,�û����:lxs02,�û�����:ffsf,����ƾ֤��:4148035,��λ����:efewfwe_,����Ա:ljw,��Ʊվ��:������Ʊվ��,��Ʒ,����X����,���,&���η羰����Ʊ(����Ʊ),23.0��2,46.0,���δ�ӡƱ��һƱ���ˣ�(����Ʊ),30.0��3,90.0&,�ϼ�:136.0,�����û�:ljw,�����ص�:������Ʊվ��,��ӡʱ��:2014-08-13 14:01:52,��Ʊ��ǩ��:,��ӭʹ�ö���OTO��Ʊϵͳ,��ַ:null,��ע,��ܰ��ʾ";
//		List<String> list = packageReceipt(sq);
//		StringBuilder sb = new StringBuilder();
//		for (String string : list) {
//			System.out.println(string);
//			sb.append(string);
//			sb.append(",");
//		}
//		System.out.println(sb);
//		String  o = decodeInsideData("",String.class);
	}
	/**
	 * Describe:��װֻ����Ϣ����Ϣ��������Ӧ
	 * @author liujianwen
	 * @param code
	 * @param describe
	 * @return
	 * return:String
	 * Date:2014-9-18
	 */
	
	public static String packageResponse(String code, String describe){
		JsonData sjd = new JsonData();
		sjd.setData(packageDataBase64(code, describe));
		return JSON.toJSONString(sjd);
	}
	/**
	 * Describe:��װ����base64�����ֻ����Ϣ������JSON����
	 * @author liujianwen
	 * @param code
	 * @param describe
	 * @return
	 * return:String
	 * Date:2014-9-18
	 */
	public static String packageDataBase64(String code, String describe) {
		Response sr = new Response();
		sr.setCode(code);
		sr.setDescribe(describe);
		return encodeBase64(JSON.toJSONString(sr));
	}
	
	/**
	 * Describe:��ȡCoreJsonData�е�data���ݶ���
	 * @author liujianwen
	 * @param CoreJsonData
	 * @param cls
	 * @return
	 * return:T
	 * Date:2014-9-18
	 */
	public static <T> T decodeInsideData(String CoreJsonData,Class<T> cls){
		JsonData skj = JSON.parseObject(CoreJsonData, JsonData.class);
		T t = JSON.parseObject(decodeBase64(skj.getData()), cls);
		return t;
	}
	/**
	 * Describe:����5��2λС��
	 * @author liujianwen
	 * @param f
	 * @return
	 * return:double
	 * Date:2016-5-4
	 */
	public static double sswr2(double f){
		BigDecimal  b  =   new   BigDecimal(f);  
		return  b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
	/**
	 * Describe:���������������
	 * @author liujianwen
	 * @return
	 * return:String
	 * Date:2016-5-5
	 */
	public static String makeRandomPwd(){
		Random r = new Random();
		r.nextInt(10);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 6; i ++){
			sb.append(r.nextInt(10));
		}
		return  sb.toString();
//		return Tools.md5Encode(sb.toString());
		
	}
}

