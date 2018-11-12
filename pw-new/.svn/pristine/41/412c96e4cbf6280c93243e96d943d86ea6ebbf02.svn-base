package com.ectrip.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * 系统模块
 *
 */
@SpringBootApplication
@EntityScan(basePackages = "com.ectrip.sys.model.*")
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={    
	      JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean  
	        })
@EnableFeignClients
@EnableTransactionManagement
public class SysApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SysApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
    	
    	return new RestTemplate();
    }
}
