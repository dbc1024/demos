package com.its.sims.services.impl;

import com.its.sims.mapper.BaseMapper;
import com.its.sims.services.IBaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by csz on 2017/6/6.
 */
public abstract class BaseImpl<T> implements IBaseService<T> {

    public abstract BaseMapper<T> getMapper();

    @Override
    public void add(T t) {
        getMapper().add(t);
    }

    @Override
    public T query(Long id) {
        return getMapper().query(id);
    }

    @Override
    public void update(T t) {
        getMapper().update(t);
    }

    @Override
    public void delete(Long id) {
        getMapper().delete(id);
    }

    @Override
    public List<T> queryAll(Map<String, Object> query) {
        return getMapper().queryAll(query);
    }

    @Override
    public Integer getTotalCounts(Map<String, Object> query) {
        return getMapper().getTotalCounts(query);
    }
}
