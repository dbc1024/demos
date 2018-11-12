package com.ectrip.tourcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 旅游卡服务启动入口
 *
 */
@SpringBootApplication
@EntityScan(basePackages = "com.ectrip.tourcard.model")
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={    
	      JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean  
	        })
@EnableFeignClients
@EnableTransactionManagement
public class TourcardApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(TourcardApplication.class, args);
    }
}
