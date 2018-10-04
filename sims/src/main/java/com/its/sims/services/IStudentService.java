package com.its.sims.services;

import com.its.sims.model.Student;

import java.util.List;

/**
 * Created by csz on 2017/6/6.
 */
public interface  IStudentService extends IBaseService<Student>{

    Student queryByName(String name);

    Student queryByPhone(String phone);

    int batchInsert(List<Student> list);

    void updateTransaction(int age1, int age2, int bcs);

}
