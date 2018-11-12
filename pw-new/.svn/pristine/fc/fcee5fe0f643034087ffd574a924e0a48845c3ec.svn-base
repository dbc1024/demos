package com.ectrip.sys;

import java.util.List;

import javax.ws.rs.POST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SysApplication.class)
public class AdminLoginTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。    
    
    @Autowired    
    private WebApplicationContext wac; // 注入WebApplicationContext 
    
	@Before // 在测试开始前初始化工作    
    public void setup() {    
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();    
    }    
    
    @Test    
    public void testGetToken() throws Exception {    
        /*Map<String, Object> map = new HashMap<>();  
        map.put("address", "合肥");  
          
        MvcResult result = mockMvc.perform(post("/q1?address=合肥").content(JSONObject.toJSONString(map)))  
                .andExpect(status().isOk())// 模拟向testRest发送get请求    
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn();*/// 返回执行请求的结果    
//    	mockMvc.perform(POST())
//        System.out.println(result.getResponse().getContentAsString());    
    } 
}
