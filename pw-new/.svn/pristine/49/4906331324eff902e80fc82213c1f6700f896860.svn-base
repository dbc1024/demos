package com.ectrip.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DesEncryptUtil {
    private static Logger logger = LoggerFactory.getLogger(DesEncryptUtil.class);

    private static final String DES_KEY = "DESKEY_ENCTRIP_PWD_USING";//24位秘钥

    public static String encrypt(String str){
        return encrypt(DES_KEY, str);
    }

    public static String encrypt(String key, String str) {
        try {
            if (StringUtil.isEmpty(str)) {
                logger.warn("加密字符串为空！");
                return "";
            }
            byte[] bt = EncryptUtil.DES3Encrypt(key, str);
            return EncryptUtil.BASE64Encrypt(bt);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public static String decrypt(String str) {
        return decrypt(DES_KEY, str);
    }

    public static String decrypt(String key, String str) {
        try {
            if (StringUtil.isEmpty(str)) {
                logger.warn("解密字符串为空！");
                return "";
            }
            byte[] bt = EncryptUtil.BASE64Decrypt(str);
            return EncryptUtil.DES3Decrypt(bt, key);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}
