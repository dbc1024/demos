package com.its.sims.controller;

import com.its.sims.annotation.SystemLogger;
import com.its.sims.model.AjaxResult;
import com.its.sims.model.Student;
import com.its.sims.model.input.StudentInput;
import com.its.sims.services.IStudentService;
import com.its.sims.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by csz on 2017/6/19.
 */

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    // 显示列表页面
    @RequestMapping("/list")
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView("student/list");

        Map<String, Object> query = new HashMap<>();
        query.put("begin",0);
        query.put("end",7);
        List<Student> students = studentService.queryAll(query);

        mv.addObject("students",students);

        return mv;
    }

    // 主页
    @RequestMapping("/query/{id}")
    public ModelAndView query(@PathVariable("id") Long studentId) {
        ModelAndView mv = new ModelAndView("student/index");

        Student student = studentService.query(studentId);

        mv.addObject("student",student);

        return mv;

    }

    @RequestMapping("/edit/{id}")
    public ModelAndView queryForEdit(@PathVariable("id") Long studentId) {
        ModelAndView mv = new ModelAndView("student/edit");

        Student student = studentService.query(studentId);

        mv.addObject("student",student);

        return mv;

    }

    @SystemLogger
    @ResponseBody()
    @RequestMapping("/edit")
    public AjaxResult edit(StudentInput studentInput) {
        AjaxResult result = new AjaxResult();

        Student student = studentInput.getStudent();
        student.setUpdateTime(new Date());
        studentService.update(student);

        result.setData(student.getId());

        return result;

    }

    @RequestMapping("/getStudentJson")
    @ResponseBody()
    public Student getAll(){

        Student student = studentService.query(1L);
        return student;
    }


    @RequestMapping("/downloadExcel")
    public ModelAndView downloadExcel(int page, int pagesize) {

        Map<String, Object> query = new HashMap<>();
        query.put("begin",page-1);
        query.put("end",pagesize);
        List<Student> students = studentService.queryAll(query);


        //将数据源组装成需要数据结构

        //文件名称
        String filename = "学生信息.xls";

        //表头数据
        List<String> thList = new ArrayList<>();
        thList.add("姓名");
        thList.add("性别");
        thList.add("年龄");
        thList.add("班级");
        thList.add("家庭住址");
        thList.add("紧急联系人");
        thList.add("紧急联系人电话");

        //信息数据
        List<List<String>> studentList = new ArrayList<>();
        for (Student student:students) {

            List<String> studentValues = new ArrayList<>();

            studentValues.add(student.getName());
            studentValues.add(student.getGender());
            studentValues.add(student.getAge().toString());
            studentValues.add(student.getGrade()+"-"+student.getClasses());
            studentValues.add(student.getAddress());
            studentValues.add(student.getEmergencyContact());
            studentValues.add(student.getEmergencyContactPhone());

            studentList.add(studentValues);

        }

        //组装map
        Map<String, Object> excelDataMap = new HashMap<>();
        excelDataMap.put("filename", filename);
        excelDataMap.put("thList", thList);
        excelDataMap.put("dataList", studentList);


        ExcelUtil ve = new ExcelUtil();
        return new ModelAndView(ve,excelDataMap);
    }


    //
    @RequestMapping("/importExcel")
    public ModelAndView importExcel(MultipartFile file) {

        ModelAndView mv = new ModelAndView("student/list");

        ExcelUtil ve = new ExcelUtil();
        List<List<String>> studentList = ve.importExcel(file);

        List<Student> students = new ArrayList<>();
        for(int i=0; i<studentList.size(); i++){
            List<String> studentValues = studentList.get(i);

            Student stu = new Student();
            for(int m=0; m<studentValues.size(); m++){
                stu.setName(studentValues.get(0));
                stu.setGender(studentValues.get(1));
                stu.setAge(Integer.parseInt(studentValues.get(2)));
                stu.setGrade(studentValues.get(3).split("-")[0]);
                stu.setClasses(studentValues.get(3).split("-")[1]);
                stu.setAddress(studentValues.get(4));
                stu.setEmergencyContact(studentValues.get(5));
                stu.setEmergencyContactPhone(studentValues.get(6));
                stu.setPassword(studentValues.get(6));//设置默认密码为电话号码
                stu.setUpdateTime(new Date());
            }

            students.add(stu);
        }

        studentService.batchInsert(students);

        mv.addObject("students",students);

        return mv;
    }

}
