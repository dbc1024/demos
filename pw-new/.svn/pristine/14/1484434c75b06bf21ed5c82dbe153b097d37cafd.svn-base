package com.ectrip.sys.authcode.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by cheng on 2017-2-22.
 */
public class SignUtil{
    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
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
            responseByte = data.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("String to xml UTF-8 Error", e);
        }
        String responseData = Base64.encode(responseByte);
        String responseSign = EncryptUtil.MD5Hex(signkey+responseData);
        return responseSign;
    }
}
