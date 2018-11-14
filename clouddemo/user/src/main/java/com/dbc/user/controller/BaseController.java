package com.dbc.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dbc.user.annotation.DictionaryCode;
import com.dbc.user.sys.mapper.DictionaryMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: CSZ
 * @date: 2018/11/13 11:31
 */
public class BaseController {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 分页查询预处理方法
     */
    public Page<Object> prePage(Map<String, String> params){
        String pageNum = params.get("pageNum")==null?"1":params.get("pageNum");
        String pageSize = params.get("pageSize")==null?"10":params.get("pageSize");

        Page<Object> page = PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        return page;
    }

    /**
     * 分页查询预处理方法
     */
    public IPage prePagePlus(Map<String, String> params){
        String pageNum = params.get("pageNum")==null?"1":params.get("pageNum");
        String pageSize = params.get("pageSize")==null?"10":params.get("pageSize");

        IPage page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        return page;
    }



    /**
     * 数据字典键值对转换
     * @param page
     */
    Map<String, List<Map<String, String>>> code2KeyValueList = new HashMap<>();
    public void dicKeyValueHandle(IPage page){
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
                        List<Map<String, String>> keyValueList = dictionaryMapper.getKeyValueListByCode(dictionaryCode.value());
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

    public void dicKeyValueHandle(Object obj){
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
                        keyValueList = dictionaryMapper.getKeyValueListByCode(dictionaryCode.value());
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




}
