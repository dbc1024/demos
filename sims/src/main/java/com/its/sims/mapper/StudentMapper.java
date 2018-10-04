package com.its.sims.mapper;

import com.its.sims.model.Student;

import java.util.List;

/**
 * Created by csz on 2017/6/5.
 */
public interface StudentMapper extends BaseMapper<Student> {

    Student queryByPhone(String phone);

    Student queryByName(String name);

    int batchInsert(List<Student> list);

}
