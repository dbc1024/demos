package com.hqyt;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: CSZ 991587100@qq.com
 * @date: 2019/2/13 19:54
 */
public class LinkHashMapTest {

    /**
     * 访问排序测试
     */
    @Test
    public void accessOrderTest() {

        LinkedHashMap<String, String> map = new LinkedHashMap<>(10,0.75f,true);

        for(int i=0; i<10; i++) {
            map.put("key" + i, i+"");
        }

        String s = map.get("key2");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        System.out.println("-------------------------");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        map.put("key10", "10");

        System.out.println("-------------------------");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }
}
