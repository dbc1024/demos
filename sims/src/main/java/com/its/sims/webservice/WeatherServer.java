package com.its.sims.webservice;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

/**
 * Created by csz on 2017/9/20.
 */
public class WeatherServer {

    public static void main(String[] args) {
        //参数1：服务发布ur
        //参数2：SEI实现类对象

        String address = "http://127.0.0.1:12345/weather";

        WeatherInterfaceImpl impl = new WeatherInterfaceImpl();
        Endpoint.publish(address,impl);

        System.out.println("发布成功！！！");
    }


}
