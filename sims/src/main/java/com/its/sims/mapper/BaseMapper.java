package com.its.sims.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by csz on 2017/6/3.
 */
public interface BaseMapper<T> {

    void add(T t);

    T query(Long id);

    void update(T t);

    void delete(Long id);

    List<T> queryAll(@Param("query") Map<String, Object> query);

    Integer getTotalCounts(@Param("query") Map<String, Object> query);


}
