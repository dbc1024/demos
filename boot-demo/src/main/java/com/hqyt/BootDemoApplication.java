package com.hqyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.hqyt.mapper")
@ServletComponentScan	//使用 @ServletComponentScan注解后，Servlet、Filter、Listener可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
public class BootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}
}
