package com.hqyt;

import com.hqyt.model.User;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: CSZ
 * @date: 2018/12/8 10:55
 */
public class BeanChangeCompareUtil {

    public static void main(String[] args) {

        Student oldStudent = new Student();
        oldStudent.setId(1);
        oldStudent.setName("肖沉志");
        oldStudent.setSex("0");
        oldStudent.setAge(26);

        Student newStudent = new Student();
        newStudent.setId(1);
        newStudent.setName("肖成志");
        newStudent.setSex("1");
        newStudent.setAge(25);


//        BeanChangeCompareUtil<Student> util = new BeanChangeCompareUtil<>();
        String compareResult = BeanChangeCompareUtil.compare(oldStudent, newStudent);
        if (compareResult.equals("")) {
            System.out.println("未有改变");
        } else {
            System.out.println(compareResult);
        }


        /**
         * 经验证，BeanUtils.copyProperties(Object source, Object target)
         * 可以在不同类型的对象之间进行属性copy。
         * 只要属性名称相同就会进行属性值copy，不同的属性就忽略。
         *
         * 只有一个小小的问题需要注意，
         * 有些相同的属性值根据业务需要可能不应该复制，例如：id，createTime。
         *
         * 处理方式：只需先将不应该改动的数据从目标对象中获取出来，如id，createTime。
         * 然后进行BeanUtils.copyProperties(Object source, Object target)，
         * 最后将少数不需要改动的值重新赋值回目标对象。
         */
        User user = new User();
        user.setId(2);
        user.setAge(100);

        //获取不需要改变的属性值
        Integer targetId = user.getId();

        //进行属性copy
        BeanUtils.copyProperties(newStudent, user);

        //将不需要改变的属性值重新赋值到目标对象
        user.setId(targetId);

        System.out.println(user);

    }

    public static <T> String compare(T oldBean, T newBean) {
        StringBuilder compareResult = new StringBuilder();

        try {
            // 通过反射获取类的类型及字段信息
            Class clazz = oldBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 排除序列化属性
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                // 获取对应属性值
                Method getMethod = pd.getReadMethod();
                Object oldValue = getMethod.invoke(oldBean);
                Object newValue = getMethod.invoke(newBean);

                if(oldValue != null && newValue != null){
                    if (!oldValue.toString().equals(newValue.toString())) {
                        compareResult.append("[" + field.getName() + ":" + oldValue + "-->" + newValue + "],");
                    }
                }else if (oldValue == null && newValue == null) {
                    continue;
                }else if (oldValue != null && newValue == null){
                    compareResult.append("[" + field.getName() + ":" + oldValue + "-->" + newValue + "],");
                }else if (oldValue == null && newValue != null){
                    compareResult.append("[" + field.getName() + ":" + oldValue + "-->" + newValue + "],");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(compareResult.length() != 0){
            compareResult.deleteCharAt(compareResult.lastIndexOf(","));
        }

        return compareResult.toString();
    }

}
