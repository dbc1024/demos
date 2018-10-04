package com.its.sims.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by csz on 2017/8/30.
 */
public class TaskTest {


    public void doTask(){

        SimpleDateFormat simpleDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("定时任务执行,当前时间：" + simpleDF.format(new Date()));
    }
}
