package com.sims.loginSystem1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Example {
	
	@RequestMapping("/home")  
    String home() {
		
        return "home";  
    }

	@RequestMapping("/")
	@ResponseBody
    String hello() {
		System.out.println("Hello system piaowu");
		
        return "Hello system piaowu!";  
    }  
      
    @RequestMapping("/hello/{myName}")
    @ResponseBody
    String index(@PathVariable String myName) {  
    	System.out.println("Hello "+myName+"!!!");
    	
        return "Hello "+myName+"!!!";  
    }  
}
