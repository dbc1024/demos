package com.ectrip.ticket.cyt.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {

    /**
     * 得到大写中文首字母
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str)
    {
        String convert = "";
        for (int j = 0; j < str.length(); j++)
        {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null)
            {
                convert += pinyinArray[0].charAt(0);
            } else
            {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 得到中文前两个大写首字母
     * @param str
     * @return
     */
    public static String getFirst2PinYinHeadChar(String str)
    {
        String tmp = getPinYinHeadChar(str);
        return tmp.length() >= 2 ? tmp.substring(0,2) : tmp;
    }
}
