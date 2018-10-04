package com.its.sims.services;

import java.util.List;
import java.util.Map;

/**
 * Created by csz on 2017/6/6.
 */
public interface IBaseService<T> {

    void add(T t);

    T query(Long id);

    void update(T t);

    void delete(Long id);

    List<T> queryAll(Map<String, Object> query);

    Integer getTotalCounts(Map<String, Object> query);

}
