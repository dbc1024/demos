package com.its.sims;

import com.its.sims.model.OrderNumber;
import com.its.sims.model.Student;
import com.its.sims.services.IOrderKeyService;
import com.its.sims.services.IStudentService;
import com.its.sims.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by csz on 2017/6/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class StudentTest {

    @Autowired
    IStudentService studentService;

    @Autowired
    IOrderKeyService orderKeyService;

    @Test
    public void query(){

        System.out.println(studentService.query(1L));
    }

    @Test
    public void queryAll(){

        Map<String, Object> query = new HashMap<>();
        query.put("keyword","四");
//        query.put("gender",null);
//        query.put("age",null);
//        query.put("grade",null);
//        query.put("classes",null);
//        query.put("emergencyContact",null);
//        query.put("emergencyContactPhone",null);

        query.put("begin",0);
        query.put("end",7);


        System.out.println(studentService.queryAll(query));
        System.out.println(studentService.getTotalCounts(query));
    }

    @Test
    public void add(){

        Student student = new Student();
        student.setName("许巍");
        student.setAge(22);
        student.setAddress("远方");
        student.setGender("男");
        student.setGrade("三");
        student.setClasses("七");
        student.setEmergencyContact("老赵");
        student.setEmergencyContactPhone("18526356235");

        studentService.add(student);

    }

    @Test
    public void update(){

        Student student = new Student();
        student.setId(2L);
        student.setName("小青");
        student.setAge(22);
        student.setAddress("四川-宜宾");
        student.setGender("男");
        student.setGrade("三");
        student.setClasses("七");
        student.setEmergencyContact("大青");
        student.setEmergencyContactPhone("18526356235");

        studentService.update(student);

        this.queryAll();
    }

    @Test
    public void updateTransaction(){

        studentService.updateTransaction(25,27,0);
    }

    @Test
    public void delete(){

        studentService.delete(2L);
        this.queryAll();
    }


    @Test
    public void dateTest(){

        String today = DateUtil.getToday();

//        today = "20170626";

        int count = orderKeyService.containOtherDay(today);
        if(count>0){
            orderKeyService.trancateKeys();
        }

        OrderNumber orderNumber = new OrderNumber(today);
        orderKeyService.addKey(orderNumber);

        System.out.println(orderNumber.getId());

        String orderNum = today+"777"+"00000"+orderNumber.getId();

        System.out.println(orderNum);


    }

}
