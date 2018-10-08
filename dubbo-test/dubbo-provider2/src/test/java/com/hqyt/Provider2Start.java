package com.hqyt;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider2Start {

	public static void main(String[] args) throws Exception {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		context.start();
		
		System.out.println("Dubbo provider2 start...");
		
		try {
			System.in.read();	// 按任意键退出
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
}
