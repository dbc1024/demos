package com.ectrip.base.util;

import java.security.Key;
import java.security.Security;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

public class ValidateUtil {
	
	/**
	 * 
	 * Describe:验证邮箱
	 * @author:chenxinhao
	 * @param email
	 * @return
	 * return:boolean
	 * Date:2015-2-2
	 */
	public static boolean isEmail(String email){
		if(email!=null && !"".equals(email)){
			Pattern p = Pattern.compile("^([\\w-\\.]+)@[\\w-.]+(\\.?[a-zA-Z]{2,4}$)");
			boolean b = p.matcher(email).matches();
			return b;
		}
		return false;
	}
	/**
	 * 
	 * Describe:登录用户名
	 * @author:chenxinhao
	 * @param applicationName
	 * @return
	 * return:boolean
	 * Date:2017-6-14
	 */
	public static boolean isApplicationName(String applicationName){
		if(applicationName!=null && !"".equals(applicationName)){
			Pattern p = Pattern.compile("^[a-zA-Z]\\w{4,32}$");
			boolean b = p.matcher(applicationName).matches();
			return b;
		}
		return false;
	}
	
	/**
	 * 
	 * Describe:验证身份证
	 * @author:chenxinhao
	 * @param corporateIdentity
	 * @return
	 * return:boolean
	 * Date:2017-6-14
	 * @throws ParseException 
	 */
	public static boolean isCorporateIdentity(String corporateIdentity){
		if(corporateIdentity!=null && !"".equals(corporateIdentity)){
			String result="";
			try {
				result = MatcherID.IDCardValidate(corporateIdentity);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(result.equals(""))return true;
			else return false;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * Describe:验证网址格式
	 * @author:chenxinhao
	 * @param corporateIdentity
	 * @return
	 * return:boolean
	 * Date:2017-6-14
	 */
	public static boolean isWebsite(String website){
		if(website!=null && !"".equals(website)){
			Pattern p = Pattern.compile("^([hH][tT]{2}[pP]:\\/\\/|[hH][tT]{2}[pP][sS]:\\/\\/)(([A-Za-z0-9-~]+)\\.)+([A-Za-z0-9-~\\/])+$");
			boolean b = p.matcher(website).matches();
			return b;
		}
		return false;
	}
	
	/**
	 * 
	 * Describe:验证手机号码
	 * @author:chenxinhao
	 * @param mobile
	 * @return
	 * return:boolean
	 * Date:2015-2-2
	 */
	public static boolean isMobile(String mobile){
		if(mobile!=null && !"".equals(mobile)){
			Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
			boolean b = p.matcher(mobile).matches();
			return b;
		}
		return false;
	}
	
	/**
	 * 
	 * Describe:验证联系电话
	 * @author:chenxinhao
	 * @param telephone
	 * @return
	 * return:boolean
	 * Date:2015-2-2
	 */
	public static boolean isTelephone(String telephone){
		if(telephone!=null && !"".equals(telephone)){
			Pattern p = Pattern.compile("^ \\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{8}$");
			boolean b = p.matcher(telephone).matches();
			return b;
		}
		return false;
	}
	
	/**
	 * 
	 * Describe:判断是否为空
	 * @author:chenxinhao
	 * @param obj
	 * @return
	 * return:boolean
	 * Date:2015-2-2
	 */
	public static boolean isNull(Object obj){
		if(obj==null || "".equals(obj.toString()) || "null".equals(obj)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Describe:判断字符串是否超过长度
	 * @author:chenxinhao
	 * @param str
	 * @param size
	 * @return
	 * return:boolean
	 * Date:2015-2-2
	 */
	public static boolean validateLength(String str,int size){
		if(str!=null && !"".equals(str)){
			if(str.length()>size){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 加密字符窜
	 * @Title: encrypt  
	 * @Description: 
	 * @author zyj
	 * @param @param strIn
	 * @param @param setkey
	 * @param @return
	 * @param @throws Exception    
	 * @return String   
	 * @throws
	 */
	public static String encrypt(String strIn,String setkey) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes(),setkey));
    }
	
	/**
	 * 加密字节
	 * @Title: encrypt  
	 * @Description: 
	 * @author zyj
	 * @return byte[]   
	 * @throws
	 */
	public static byte[] encrypt(byte[] arrB,String setkey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(setkey.getBytes());

        Cipher encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        return encryptCipher.doFinal(arrB);
    }
	
	 /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }
    
    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     *
     * @param arrB
     *            需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }
    
    /**
     * 解密字符串
     *
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String strIn,String setkey) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn),setkey));
    }
    
    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     *
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
    /**
     * 解密字节数组
     *
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public static byte[] decrypt(byte[] arrB,String setkey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(setkey.getBytes());
        Cipher decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        return decryptCipher.doFinal(arrB);
    }
}

