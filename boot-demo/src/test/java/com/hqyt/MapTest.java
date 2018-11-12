package com.hqyt;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: CSZ
 * @date: 2018/10/26 16:37
 * @description:
 */
public class MapTest {

    @Test
    public void testMapKey() {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a-pay_type", "11111");
        map.put("a-bank_info", "222222");
        map.put("name", "Lily");
        map.put("age", "29");

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"------->"+entry.getValue());
            
        }

        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            if(key.contains("-")){
                it.remove();
            }
        }

        System.out.println("-------------------------------------------------");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"------->"+entry.getValue());

        }

    }



    @Test
    public void testSplit() {
        String str = "111-333";
        System.out.println(str.split("-")[1]);

    }

}
