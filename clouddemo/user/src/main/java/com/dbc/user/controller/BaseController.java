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

}
