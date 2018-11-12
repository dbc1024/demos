package com.ectrip.oauth;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口认证
 * @author jiyong
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class OAuthApplication {
	
	@GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(OAuthApplication.class);
	}

}
