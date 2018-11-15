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
        String sortName = params.get("sortName");
        String sortType = params.get("sortType");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination
                .Page(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        if(sortName == null){
            page.setAsc("id");
        }else {
            sortName = camelToUnderline(sortName);
            if(sortType == null || "asc".equals(sortType)){
                page.setAsc(sortName);
            }else {
                page.setDesc(sortName);
            }
        }

        return page;
    }


    /**
     * 将驼峰转换成"_"(userId:user_id)
     * @param param 带驼峰的参数
     * @return 带下划线的参数
     */
    public String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将"_"转成驼峰(user_id:userId)
     * @param param 带下划线的参数
     * @return 带驼峰的参数
     */
    public String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c=='_'){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
