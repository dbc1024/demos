package com.ectrip.oauth.utils;

import java.io.*;
/**
 * 口令加密算法
 * @author lijin
 *
 */
public class Encrypt {
    public static void main(String[] args) {

        Encrypt my = new Encrypt();
        System.out.println("12345678" + my.passwordEncrypt("12345678"));
        System.out.println(my.isPass("123456",
                                     "D8E423A9D5EB97DA9E2D58CD57B92808"));

    }

    /**********************************************
     *解密方法:
     *参数: str_pwds 用户录入的密码
     *      str_passwd 从数据库查查出的用户加密的口令文本
     *
     *返回: boolean TRUE 正确 FALSE 为失败
     *
     ************************************************/

    private boolean checkPassword(String str_pwds, String str_passwd) {
        try {
            String myinfo = str_pwds + "ectrip"; //自定义的MD5
            java.security.MessageDigest alga = java.security.MessageDigest.
                getInstance("MD5");

            alga.update(myinfo.getBytes());

            byte[] digesta = alga.digest();
            String ls_str = byte2hex(digesta);

            return ls_str.equals(str_passwd);
        }
        catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("没有这个加密算法请检查JDK版本");
        }
        return false;
    }

    /**********************************************
     *
     *将加密后的数据(二进制转为16进制的字符)
     *
     ************************************************/

    private String byte2hex(byte[] b) { //二行制转字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs;
            }
        }
        return hs.toUpperCase();
    }

    /**********************************************
     *解密方法:
     *参数: str_pwds 要加密的明文
     *
     *
     *返回: 加密过的密文
     *
     ************************************************/

    private String addPwd(String str_pwds) {
        try {
            String myinfo = str_pwds + "ectrip";
            java.security.MessageDigest alga = java.security.MessageDigest.
                getInstance("MD5");
            alga.update(myinfo.getBytes());

            byte[] digesta = alga.digest();
            String ls_str = byte2hex(digesta);

            return ls_str;
        }
        catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("没有这个加密算法请检查JDK版本");
        }
        return null;
    }

    /**********************************************
     *加密口令:
     *参数: str_pwds 要加密的明文
     *
     *
     *返回: 加密过的密文
     *
     ************************************************/
    public String passwordEncrypt(String str_pwds) {
        return addPwd(str_pwds);
    }

    /**********************************************
     *检查口令是否相等:
     *参数: str_pwds 口令的明文
     *      str_passwd 口令的密文在数据库在保存
     *
     *返回: 加密过的密文
     *
     ************************************************/

    public boolean isPass(String str_pwds, String str_passwd) {
        return checkPassword(str_pwds, str_passwd);
    }

    public static byte[] base64Encode(byte[] byteData) {
        if (byteData == null) {
            return null;
        }
        int iSrcIdx; // index into source (byteData)
        int iDestIdx; // index into destination (byteDest)
        byte byteDest[] = new byte[ ( (byteData.length + 2) / 3) * 4];

        for (iSrcIdx = 0, iDestIdx = 0; iSrcIdx < byteData.length - 2;
             iSrcIdx += 3) {
            byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx] >>> 2) & 077);
            byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx + 1] >>> 4) & 017 |
                                           (byteData[iSrcIdx] << 4) & 077);
            byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx + 2] >>> 6) & 003 |
                                           (byteData[iSrcIdx + 1] << 2) & 077);
            byteDest[iDestIdx++] = (byte) (byteData[iSrcIdx + 2] & 077);
        }

        if (iSrcIdx < byteData.length) {
            byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx] >>> 2) & 077);
            if (iSrcIdx < byteData.length - 1) {
                byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx + 1] >>> 4) &
                                               017 |
                                               (byteData[iSrcIdx] << 4) & 077);
                byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx + 1] << 2) &
                                               077);
            }
            else {
                byteDest[iDestIdx++] = (byte) ( (byteData[iSrcIdx] << 4) & 077);
            }
        }

        for (iSrcIdx = 0; iSrcIdx < iDestIdx; iSrcIdx++) {
            if (byteDest[iSrcIdx] < 26) {
                byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + 'A');
            }
            else if (byteDest[iSrcIdx] < 52) {
                byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + 'a' - 26);
            }
            else if (byteDest[iSrcIdx] < 62) {
                byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + '0' - 52);
            }
            else if (byteDest[iSrcIdx] < 63) {
                byteDest[iSrcIdx] = (byte) '+';
            }
            else {
                byteDest[iSrcIdx] = (byte) '/';
            }
        }

        for (; iSrcIdx < byteDest.length; iSrcIdx++) {
            byteDest[iSrcIdx] = (byte) '=';

        }
        return byteDest;
    }
    
    
    public String MD5Encrypt(String str_pwds) {
        return md5(str_pwds);
    }
    private String md5(String str_pwds) {
        try {
            String myinfo = str_pwds;
            java.security.MessageDigest alga = java.security.MessageDigest.
                getInstance("MD5");
            alga.update(myinfo.getBytes());

            
            byte[] digesta = alga.digest();
            String ls_str = byte2hexold(digesta);

            return ls_str;
        }
        catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("没有这个加密算法请检查JDK版本");
        }
        return null;
    }
    /**
     * 加密函数
     *
     * @param strInput
     * @return
     */
    public static String base64Encode(String strInput) {
        if (strInput == null) {
            return null;
        }
        return base64Encode(strInput, "GB2312");
    }

    public final static String base64Encode(String strInput, String charSet) {
        if (strInput == null) {
            return null;
        }
        String strOutput = null;
        byte byteData[] = new byte[strInput.length()];
        try {

            byteData = strInput.getBytes(charSet);
            strOutput = new String(base64Encode(byteData), charSet);

        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
        return strOutput;
    }
    /**********************************************
    *
    *将加密后的数据(二进制转为16进制的字符)
    *
    ************************************************/

   private String byte2hexold(byte[] b) { //二行制转字符串
       String hs = "";
       String stmp = "";
       for (int n = 0; n < b.length; n++) {
           stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
           if (stmp.length() == 1) {
               hs = hs + "0" + stmp;
           }
           else {
               hs = hs + stmp;
           }
           if (n < b.length - 1) {
               hs = hs;
           }
       }
       return hs;
   }
    /**
     * 解密函数
     *
     * @param inputString
     * @return
     */
    public static String Base64Decode(String inputString) {
        try {

            byte[] ls_value = null;
            ls_value = decode(inputString.toCharArray());
            String s2 = new String(ls_value, "GB2312");

            return s2;
        }
        catch (Exception e) {
            return "";
        }
    }

    static private byte[] codes = new byte[256];
    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
//echange.debug.Debug.print(i+"&"+codes[i]+" ");
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            codes[i] = (byte) (i - 'A');
//echange.debug.Debug.print(i+"&"+codes[i]+" ");
        }

        for (int i = 'a'; i <= 'z'; i++) {
            codes[i] = (byte) (26 + i - 'a');
//echange.debug.Debug.print(i+"&"+codes[i]+" ");
        }
        for (int i = '0'; i <= '9'; i++) {
            codes[i] = (byte) (52 + i - '0');
//echange.debug.Debug.print(i+"&"+codes[i]+" ");
        }
        codes['+'] = 62;
        codes['/'] = 63;
    }

    /**
     * Decodes a BASE-64 encoded stream to recover the original
     * data. White space before and after will be trimmed away,
     * but no other manipulation of the input will be performed.
     *
     * As of version 1.2 this method will properly handle input
     * containing junk characters (newlines and the like) rather
     * than throwing an error. It does this by pre-parsing the
     * input and generating from that a count of VALID input
     * characters.
     **/
    static public byte[] decode(char[] data) {
        // as our input could contain non-BASE64 data (newlines,
        // whitespace of any sort, whatever) we must first adjust
        // our count of USABLE data so that...
        // (a) we don't misallocate the output array, and
        // (b) think that we miscalculated our data length
        //     just because of extraneous throw-away junk

        int tempLen = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            if ( (data[ix] > 255) || codes[data[ix]] < 0) {
                --tempLen; // ignore non-valid chars and padding
            }
        }
// calculate required length:
// -- 3 bytes for every 4 valid base64 chars
// -- plus 2 bytes if there are 3 extra base64 chars,
// or plus 1 byte if there are 2 extra.

        int len = (tempLen / 4) * 3;
        if ( (tempLen % 4) == 3) {
            len += 2;
        }
        if ( (tempLen % 4) == 2) {
            len += 1;

        }
        byte[] out = new byte[len];

        int shift = 0; // # of excess bits stored in accum
        int accum = 0; // excess bits
        int index = 0;

// we now go through the entire array (NOT using the 'tempLen' value)
        for (int ix = 0; ix < data.length; ix++) {
            int value = (data[ix] > 255) ? -1 : codes[data[ix]];

            if (value >= 0) { // skip over non-code
                accum <<= 6; // bits shift up by 6 each time thru
                shift += 6; // loop, with new bits being put in
                accum |= value; // at the bottom.
                if (shift >= 8) { // whenever there are 8 or more shifted in,
                    shift -= 8; // write them out (from the top, leaving any
                    out[index++] = // excess at the bottom for next iteration.
                        (byte) ( (accum >> shift) & 0xff);
                }
            }
            // we will also have skipped processing a padding null byte ('=') here;
            // these are used ONLY for padding to an even length and do not legally
            // occur as encoded data. for this reason we can ignore the fact that
            // no index++ operation occurs in that special case: the out[] array is
            // initialized to all-zero bytes to start with and that works to our
            // advantage in this combination.
        }

        // if there is STILL something wrong we just have to throw up now!
        if (index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index +
                            " instead of " + out.length + ")");
        }

        return out;
    }

//
// code characters for values 0..63
//
    static private char[] alphabet =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
        .toCharArray();
}
