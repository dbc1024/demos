package com.dbc.user.util;

import com.dbc.user.sys.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author: CSZ
 * @date: 2018/11/14 10:52
 */
public class DictionaryUtil {

    @Autowired
    private static DictionaryMapper dictionaryMapper;

    public static void keyValueHandle(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    System.out.println(field.getName() + ":" + field.get(obj) );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Object value = field.get(obj);
                if(value != null){
                    String strValue = value.toString();
                    if(strValue.startsWith("-")){

                        List<Map<String, String>> keyValueList = dictionaryMapper.getKeyValueListByCode(strValue.substring(1));

                        field.set(obj, keyValueList.get(0).get("name"));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
