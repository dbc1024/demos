package com.its.sims.services.impl;

import com.its.sims.mapper.BaseMapper;
import com.its.sims.mapper.StudentMapper;
import com.its.sims.model.Student;
import com.its.sims.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by csz on 2017/6/6.
 */
@Service
public class StudentImpl extends BaseImpl<Student> implements IStudentService{

    @Autowired
    StudentMapper studentMapper;

    @Override
    public BaseMapper<Student> getMapper() {
        return studentMapper;
    }

    @Override
    public Student queryByName(String name) {
        return null;
    }

    @Override
    public Student queryByPhone(String phone) {
        return studentMapper.queryByPhone(phone);
    }

    @Override
    public int batchInsert(List<Student> list) {
        return studentMapper.batchInsert(list);
    }

    @Transactional
    public void updateTransaction(int age1, int age2, int bcs){

        Student stu1 = new Student();
        stu1.setId(1L);
        stu1.setAge(age1);

        Student stu2 = new Student();
        stu2.setId(2L);
        stu2.setAge(age2);

        update(stu1);
        System.out.println("参数1更新完成");

        int r = 7/bcs;
        System.out.println("中间业务正常完成"+r);

        update(stu2);
        System.out.println("参数2更新完成");
    }
}
