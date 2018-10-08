package com.sims.sso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Example {
	
	@RequestMapping("/home")  
    String home() {
		
        return "home";  
    }
	
}
