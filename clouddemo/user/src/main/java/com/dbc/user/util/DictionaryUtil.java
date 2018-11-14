package com.dbc.user.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dbc.user.annotation.DictionaryCode;
import com.dbc.user.sys.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: CSZ
 * @date: 2018/11/14 10:52
 */
@Component
public class DictionaryUtil {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    private static DictionaryUtil util;


    @PostConstruct
    public void init(){
        util = this;
        util.dictionaryMapper = this.dictionaryMapper;
    }


    private static Map<String, List<Map<String, String>>> code2KeyValueList = new HashMap<>(16);
    /**
     * 若查询结果集中包含数据字典相关内容，需要进行数据字典键值对转换，可调用此方法。
     * 只需把查询结果集当做入参传入，即可自动完成数据字典键值对转换。
     *
     * 此工具类重载了四个dicKeyValueHandle()方法，
     * 分别用于处理map封装结果集的分页查询、详情查询
     * 以及相应实体对象封装结果集的分页查询、详情查询
     * @param page
     */
    public static void keyValueHandle(IPage page){
        boolean isContainDic = false;

        List records = page.getRecords();
        if(records == null || records.size()<1){
            return;
        }

        try {
            Object one = records.get(0);
            Field[] oneFields = one.getClass().getDeclaredFields();
            for (Field field : oneFields) {
                field.setAccessible(true);

                Object value = field.get(one);
                if(field.isAnnotationPresent(DictionaryCode.class) && value != null){

                    DictionaryCode dictionaryCode = field.getAnnotation(DictionaryCode.class);

                    //利用缓存数据，避免重复查库
                    if(!code2KeyValueList.containsKey(dictionaryCode.value())){
                        List<Map<String, String>> keyValueList = util.dictionaryMapper.getKeyValueListByCode(dictionaryCode.value());
                        code2KeyValueList.put(dictionaryCode.value(), keyValueList);
                    }

                    isContainDic = true;
                }
            }

            if (!isContainDic){
                return;
            }

            for (Object obj: records) {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);

                    Object value = field.get(obj);
                    if(field.isAnnotationPresent(DictionaryCode.class) && value != null){
                        DictionaryCode dictionaryCode = field.getAnnotation(DictionaryCode.class);

                        List<Map<String, String>> keyValueList = code2KeyValueList.get(dictionaryCode.value());
                        for (Map<String, String> keyValue: keyValueList) {
                            String dictValue = keyValue.get("value");

                            if(dictValue.equals(value.toString())){
                                field.set(obj, keyValue.get("name"));
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void keyValueHandle(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);

                Object value = field.get(obj);
                if(field.isAnnotationPresent(DictionaryCode.class) && value != null){

                    DictionaryCode dictionaryCode = field.getAnnotation(DictionaryCode.class);

                    List<Map<String, String>> keyValueList;
                    //利用缓存数据，避免重复查库
                    if(code2KeyValueList.containsKey(dictionaryCode.value())){
                        keyValueList = code2KeyValueList.get(dictionaryCode.value());
                    }else {
                        keyValueList = util.dictionaryMapper.getKeyValueListByCode(dictionaryCode.value());
                        code2KeyValueList.put(dictionaryCode.value(), keyValueList);
                    }

                    for (Map<String, String> keyValue: keyValueList) {
                        String dictValue = keyValue.get("value");

                        if(dictValue.equals(value.toString())){
                            field.set(obj, keyValue.get("name"));
                            break;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data
     * @return
     */
    public static void keyValueHandle(List<Map<String, String>> data){
        boolean isContainDic = false;

        if (data==null || data.size()==0){
            return;
        }

        Map<String, String> oneMap = data.get(0);
        for (Map.Entry<String, String> entry : oneMap.entrySet()) {

            String key = entry.getKey();
            if (key.contains("-")){

                //利用缓存数据，避免重复查库
                if(!code2KeyValueList.containsKey(key.split("-")[1])){
                    List<Map<String, String>> keyValueList = util.dictionaryMapper.getKeyValueListByCode(key.split("-")[1]);
                    code2KeyValueList.put(key.split("-")[1], keyValueList);
                }
                isContainDic = true;
            }
        }

        if (!isContainDic){
            return;
        }

        for (Map<String, String> map : data) {
            for (Map.Entry<String, String> entry : map.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (key.contains("-") && value!=null){
                    List<Map<String, String>> keyValueList = code2KeyValueList.get(key.split("-")[1]);

                    for (Map<String, String> keyValue: keyValueList) {
                        String dictValue = keyValue.get("value");

                        if(dictValue.equals(value.toString())){
                            entry.setValue(keyValue.get("name"));
                            break;
                        }
                    }
                }
            }
        }

        //将不规则key值还原成原始key值
        for (Map<String, String> map : data) {
            Map<String, String> primaryKeyMap = new LinkedHashMap<>();

            for (Map.Entry<String, String> entry : map.entrySet()) {

                String key = entry.getKey();
                if (key.contains("-")){
                    primaryKeyMap.put(key.split("-")[0], entry.getValue());
                }
            }

            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                if(key.contains("-")){
                    it.remove();
                }
            }

            for (Map.Entry<String, String> entry : primaryKeyMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }

    }


    public static void keyValueHandle(Map<String, String> data){
        boolean isContainDic = false;

        if (data==null || data.size()==0){
            return;
        }

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.contains("-") && value!=null){
                isContainDic = true;

                List<Map<String, String>> keyValueList;
                //利用缓存数据，避免重复查库
                if(code2KeyValueList.containsKey(key.split("-")[1])){
                    keyValueList = code2KeyValueList.get(key.split("-")[1]);
                }else {
                    keyValueList = util.dictionaryMapper.getKeyValueListByCode(key.split("-")[1]);
                    //新数据加入缓存
                    code2KeyValueList.put(key.split("-")[1], keyValueList);
                }

                for (Map<String, String> keyValue: keyValueList) {
                    String dictValue = keyValue.get("value");

                    if(dictValue.equals(value.toString())){
                        entry.setValue(keyValue.get("name"));
                        break;
                    }
                }
            }
        }

        if (!isContainDic){
            return;
        }

        //将不规则key值还原成原始key值
        Map<String, String> primaryKeyMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {

            String key = entry.getKey();
            if (key.contains("-")){
                primaryKeyMap.put(key.split("-")[0], entry.getValue());
            }
        }

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            if(key.contains("-")){
                it.remove();
            }
        }

        for (Map.Entry<String, String> entry : primaryKeyMap.entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }
    }


}
