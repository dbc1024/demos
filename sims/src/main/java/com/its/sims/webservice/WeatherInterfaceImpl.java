package com.its.sims.webservice;

import javax.jws.WebService;

/**
 * Created by csz on 2017/9/20.
 * SEI实现类
 */
@WebService
public class WeatherInterfaceImpl implements WeatherInterface{
    @Override
    public String getWeatherByCity(String city) {

        System.out.println("接收到客户端发送的城市名称"+ city);

        String weather = "今天有点热.......";
        System.out.println("返回天气信息："+weather);


        return weather;
    }
}
