package com.its.sims.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csz on 2017/9/25.
 */
@Controller
@RequestMapping("/getData")
public class Restful {

    Logger logger = Logger.getLogger(Restful.class);

    // http://localhost:8080/getData?userName=sun 方式的调用
    @ResponseBody
    @RequestMapping
    public Map<String, String> printDa(@RequestParam(value="userName", defaultValue="User") String name) {
        String msg = "Welcome "+ name;

        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("mag", msg);

        return msgMap;

    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> printData1(@RequestParam(value="userName", defaultValue="User") String name) {

        String msg = "Welcome "+ name;

        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("mag", msg);

        logger.info(msg);
        return msgMap;

    }




    // http://localhost:8080/getData/json?item=0 方式的调用
    @ResponseBody
    @RequestMapping(value = "/json",method = RequestMethod.GET)
    public Map<String, String> printData3(@RequestParam(value="item", defaultValue="0") String item) {

        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("mag", item);

        return msgMap;
    }


    //http://localhost:8080/getData/json/1 方式的调用
    @ResponseBody
    @RequestMapping(value = "/json/{item}")
    public Map<String, String> printData4(@PathVariable String item) {

        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("mag", item);

        return msgMap;
    }


    // http://localhost:8080/getData/Sun/Royi 方式的调用
    @ResponseBody
    @RequestMapping(value = "/{firstName}/{lastName}")
    public Map<String, String> printData2(@PathVariable String firstName, @PathVariable String lastName) {

        String msg = "Welcome "+ firstName + " " + lastName;

        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("mag", msg);

        return msgMap;
    }






}
