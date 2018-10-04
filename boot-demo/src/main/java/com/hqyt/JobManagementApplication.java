/**
 * 
 */
package com.hqyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* @Description: 打war包时使用这个启动类，并且屏蔽另一个启动类
* @Author: CSZ
* @Date: 2018-09-04 11:37:17
*/
//@SpringBootApplication
//@ServletComponentScan
//@EnableSwagger2
//@MapperScan("com.hqyt.mapper")
public class JobManagementApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(JobManagementApplication.class);
        }
    public static void main(String[] args) {
        SpringApplication.run(JobManagementApplication.class, args);
    }
}
