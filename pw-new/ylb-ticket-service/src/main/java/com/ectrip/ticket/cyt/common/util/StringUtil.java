package com.ectrip.ticket.cyt.common.util;

import java.util.*;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 预防空字符串
     * @param str
     * @return
     */
    public static String denull(String str){
        return str == null ? "" : str;
    }

    /**
     * SQL关键字过滤
     * @param content
     * @param replaceStr
     * @return
     */
    public static String filterSQLKeyword(String content, String replaceStr){
        return denull(content).replaceAll("(?i)(select|update|insert|delete|from|create|drop|alter)", replaceStr);
    }

    /**
     * SQL关键字过滤
     * @param content
     * @return
     */
    public static String filterSQLKeyword(String content){
        return filterSQLKeyword(content, "");
    }


    /**
     *  格式化身份证号保密显示
     * @param idNumber 身份证号
     * @param startNum 显示前几位数
     * @param endNum 显示最后几位数
     * @return
     */
    public static String markIdNumber(String idNumber, int startNum, int endNum){
        if(isEmpty(idNumber)){
            return "";
        }
        String markStr = "******************";
        return idNumber.substring(0, startNum) + markStr.substring(0, idNumber.length()- startNum - endNum) + idNumber.substring(idNumber.length() - endNum);
    }

    /**
     * 格式化身份证号保密显示
     * @param idNumber 身份证号
     * @return
     */
    public static String markIdNumber(String idNumber){
        return markIdNumber(idNumber, 3, 4);
    }

    public static void main(String[] args){
        System.out.println(markIdNumber("511011123456789123", 0, 0));
    }


    /**
     * SQL语句查询出来的key与数据库字段大小写一致，现数据字段都是大写，可通过此方法转换key值为小写
     * @param list
     * @param specialKeys 某些字段可指定大小写（为兼容一些不小写不一致的SQL语句）
     * @return
     */
    public static List<Map> keyConvertToLowerCase(List<Map> list, String ... specialKeys){
        if(list == null || list.isEmpty()){
            return list;
        }else{
            List returnList = new ArrayList();
            Map specialKeyMap = new HashMap();
            if(specialKeys != null && specialKeys.length >0){
                for(String key : specialKeys) {
                    specialKeyMap.put(key.toUpperCase(), key);
                }
            }
            for(Map map : list){
                Iterator<String> iterator = map.keySet().iterator();
                Map newMap = new HashMap();
                Set specialKeySet = specialKeyMap.keySet();
                String upperCaseKey = "";
                while(iterator.hasNext()){
                    String key = iterator.next();
                    upperCaseKey = key.toUpperCase();
                    newMap.put(!specialKeySet.isEmpty() && specialKeySet.contains(upperCaseKey) ? specialKeyMap.get(upperCaseKey) : key.toLowerCase(), map.get(key));
                }
                returnList.add(newMap);
            }
            return returnList;
        }
    }

}
