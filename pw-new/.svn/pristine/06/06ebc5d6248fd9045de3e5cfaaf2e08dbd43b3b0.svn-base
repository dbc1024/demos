package com.ectrip.ec.model.cytterminal;


import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.Base64_2;
import com.ectrip.base.util.EncryptUtil;
/**
 * 数据转换工具类
 * @author KITHO
 */
public class DataTool {

	/**
	 * 通过signkey和data验证数据,验证通过会返回true,未通过返回false
	 * 不建议外部调用此方法
	 * @param signkey
	 * @param data
	 * @param signed
	 * @return
	 */
	public static boolean checkData(String signkey,String data,String signed){
		return signed.equalsIgnoreCase(getSign(signkey,data));
	}
	/**
	 * 通过signkey和实体验证数据, 验证通过返回该实体, 未通过返回null
	 * 不建议外部调用此方法
	 * @param dataTrans
	 * @return
	 */
	protected static DataTrans checkData(String signkey,DataTrans dataTrans){
		return checkData(signkey,dataTrans.getData(),dataTrans.getSigned())==true?dataTrans:null;
	}

	/**
	 * 通过dataTransJsonStr字符串验证数据, 验证通过返回该字符串对应的实体, 未通过返回null
	 * 在接收到字符串时可以使用此方法, 建议验证时调用此方法
	 * 此方法会给request或response塞值
	 * @param dataTransJsonStr
	 * @return
	 */
	public static DataTrans checkData(String signkey,String dataTransJsonStr,Class<?> dataType){
		DataTrans dataTrans=checkData(signkey,(DataTrans)JSON.parseObject(dataTransJsonStr, DataTrans.class));
		if(null!=dataTrans){
			if(Request.class.isAssignableFrom(dataType)){
				dataTrans.request=(Request)JSON.parseObject(dataTrans.getData(), dataType);
			}else if(Response.class.isAssignableFrom(dataType)){
				dataTrans.response=(Response)JSON.parseObject(dataTrans.getData(), dataType);
			}
		}
		return dataTrans;
	};
	public static DataTrans checkData(String signkey,String dataTransJsonStr){
		return checkData(signkey,(DataTrans)JSON.parseObject(dataTransJsonStr, DataTrans.class));
	};
	/**
	 * 签名
	 * @param signkey
	 * @param data
	 * @return
	 */
	public static String getSign(String signkey,String data){
		//签名: md5(分配的密匙+base64(data))
		byte[] responseByte = null;
		try {
			String signData = signkey + data;
			responseByte = signData.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("OTA Service ： getErrorResponseJson : String to xml UTF-8 Error !");
		}
		String responseData = Base64_2.encode(responseByte);
		String responseSign = EncryptUtil.MD5Hex(responseData);
		return responseSign;
	}
	/**
	 * 给对象 赋 签名, 返回带有签名的完整对象, 在对象需要赋值签名并且该对象还要继续被调用时 使用此方法
	 * @param datain
	 * @return
	 */
	public static DataTrans envelopeData(String signkey,DataTrans datain){
		datain.setSigned(getSign(signkey,datain.getData()));
		return datain;
	}
	/**
	 * 给对象 赋 签名, 返回带有签名的完整字符串, 在把对象封装成json字符串时使用此方法
	 * @param datain
	 * @param signkey
	 * @return
	 */
	public static String envelopeData(Object requestOrResponse,String signkey,String identity){
		String data=JSON.toJSONString(requestOrResponse);
		String returnData = JSON.toJSONString(new DataTrans(data, getSign(signkey, data), identity));
		return returnData;
	}

	public static String envelopeDatal(String requestOrResponse,String signkey,String identity){
		String returnData = JSON.toJSONString(new DataTrans(requestOrResponse, getSign(signkey, requestOrResponse), identity));
		return returnData;
	}
}
