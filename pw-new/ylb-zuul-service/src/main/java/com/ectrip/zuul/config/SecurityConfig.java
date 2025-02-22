package com.ectrip.zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	.antMatchers("/services/**")
    	.permitAll().and()
    	.csrf().disable();
    }
}
