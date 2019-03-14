package com.hqyt;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author: CSZ 991587100@qq.com
 * @date: 2019/3/14 14:56
 */
public class RSATest {
                                               //MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI69qmNlD0suZoc0mt56pTidRoSUpoPBsETxv9QLVBah70xEYW4YKnIn3ey+CAUShAQNJ3CH7rJdOApFJH7lkrtlhqGkywpD/Mgq9oSwz7XTeDZeeqgaQkFcCNflyA1Ld+5iZhlpZQwN150SRSUeQYkcrPEIsgk0/8i/Vm7uiFP5AgMBAAECgYBCGByvjXn5a/DZpkgXwF6Eyn5+oX5BAAyov+/9NYmoOS9e90LOAa6V0VP3bT61ao9qeNTMTIUAXh2207FEePTp0fwpE+FEBiNjCWj+mDXZ4ZFP4EtknyZCCw3RRbkZ7p0D4EslROEDK1w+5WA4yxubUjoh/K+YBbdnPeL2KQbkiQJBAN0v8rf0jIXrvZOrcBoIB0jpuazDU6BQP99Zo7hxD99tj86H3w9kkeP+ebrrtphCMc8P4f0knclTq23F6UgP968CQQClNPbEH2VvUAx1AktUJf71XRcgrE4G0dq82Z4R9AnCB4w0ndkIgeK3cY7nZMkDlvvOrDDzesDMktCxDwudWbDXAkEAuuyfifjAnRjZ0KcsiMNKLMNhJ/C2hp/rKr0dkGz0RhTYWGkZCHAsnupbQ62aVVhzsZKxoHZGZnMwSAfemwjrfwJAZL1OODuY4+6hGfyjKJXeyYnRX4gSMFBpHL1qiTIRfTKTCUazoF9re0cQyAMKHFlGBTm0w9O3m0dvYHh3MaUOkwJBAMPumEvZuCUASAS3JK1H0KOmh0BU/r5MF6BKb77ttpyn64iqLjPmQngHhBd1aJRicF//9VH7pyKMuqlJ9MN3VnY=
    private static final String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ7yhvn2pBlCYo9nsSg2y7S88/Be7QruTimEnjBQTepDkcEal56+BNo6jnygNttZsPlbIWTNmbSFaCqMZt7OOgm1hkQQml01meT2O8/s712T2vJoOaIAT33pQStikusgOAxW65N4c+eH7dvkc945FHD6daRrhihjRm+mPAGTIP7pAgMBAAECgYEAkUBs3Kzp1TN3QjmuCWPZ0DcEbVsyH9UalJJexSP9r+FDp0YX3LclVBBC+dynhNh4A8elwqqolfF7+bYIo/+txJDDzcPPblZXLAm6Qc5T3y/Lxlv8YAT1ADgNQtSZEwlRciTalnsZLMMZlvVNHd7qoY+YdCHhGszR3lkw5y6cIWkCQQDqd3xtsBX4l9UnDSkBRM7rkTOXGnuL5CknlVBzCDaAj6QUrctVvSbkcEYqBrXwiBYrWj6dvlJnd2/Hb9X1cqFHAkEArYuEIGRxZPoMulJHGc59pXIYlRzxzeW44MQqMbP28fcye/YKlp9ucFLBDQA87dmGSc1jn1ZNWo3rYeH1z4H2TwJBALQIf2Bx/szdOOw9bKjuvrueyLBTw13NGdStEBLj0YWxxdmjASiajwyFMTr/4CALPoqXRtE03G1aBlhrBSzske0CQGd25i7pszEgYrYyl3ErSn14qG898mtuqepXCVDYmkFnvxFofqeOJamRrMazfZ/QL9+QM/Sq3zM9qTeSH5q3QMECQAlDsd9f6Nq31WbPEZRijf8iQ0IYMejSpFN9eCxXs+P0VdSD3hAlm4NnZqfhOiF+2L3KprqI35mFmx9oqkWrDVo=";
    private static final String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCe8ob59qQZQmKPZ7EoNsu0vPPwXu0K7k4phJ4wUE3qQ5HBGpeevgTaOo58oDbbWbD5WyFkzZm0hWgqjGbezjoJtYZEEJpdNZnk9jvP7O9dk9ryaDmiAE996UErYpLrIDgMVuuTeHPnh+3b5HPeORRw+nWka4YoY0ZvpjwBkyD+6QIDAQAB";


    /**
     * 获取公钥私钥
     */
    @Test
    public void getKey() throws UnsupportedEncodingException {

        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey aPrivate = pair.getPrivate();
        PublicKey aPublic = pair.getPublic();


        byte[] privateByte = aPrivate.getEncoded();
        byte[] publicByte = aPublic.getEncoded();

        String privateStr = Base64.getEncoder().encodeToString(privateByte);
        System.out.println(privateStr);

        System.out.println("----------------------------");

        String publicStr = Base64.getEncoder().encodeToString(publicByte);
        System.out.println(publicStr);


    }


    /**
     * 获取公钥私钥
     */
    @Test
    public void getKey2() {

        RSA rsa = new RSA();

        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println(privateKeyBase64);


        String publicKeyBase64 = rsa.getPublicKeyBase64();
        System.out.println(publicKeyBase64);


    }


    /**
     * 非对称加密测试
     */
    @Test
    public void testRsa() throws UnsupportedEncodingException {

        RSA rsaKey = new RSA();

        String privateKeyBase64 = rsaKey.getPrivateKeyBase64();
        System.out.println(privateKeyBase64);
        String publicKeyBase64 = rsaKey.getPublicKeyBase64();
        System.out.println(publicKeyBase64);


        String mingWen = "hello";

        RSA rsaEncrypt = new RSA(null, publicKeyBase64);
        byte[] encrypt = rsaEncrypt.encrypt(mingWen.getBytes("UTF-8"), KeyType.PublicKey);

        RSA rsaDecrypt = new RSA(privateKeyBase64, null);
        byte[] decrypt = rsaDecrypt.decrypt(encrypt, KeyType.PrivateKey);

        String jiemi = new String(decrypt, "UTF-8");
        System.out.println(jiemi);

    }


}
