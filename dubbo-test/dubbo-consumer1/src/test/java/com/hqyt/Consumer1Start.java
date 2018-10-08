package com.hqyt;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer1Start {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml" });
		context.start();
		
		TestService testService = (TestService) context.getBean("testService");
		System.out.println(testService.getServerTime());
		
		TestService testService1 = (TestService) context.getBean("testService1");
		System.out.println(testService1.getServerTime());
		/**
		 * 为了测试同一个接口，在不同服务者之间是否能有不同的实现进行调用。
		 * 
		 * 结果：(1)					(2)						(3)						(4)
		 * 		2018-07-05			1530779046093			1530779099160			2018-07-05
		 *		1530778992109		2018-07-05				1530779099392			2018-07-05
		 * 
		 * 结论：消费者并不能根据testService，testService1取到对应的实现，而是随机的。
		 *       根据结果来看，内存中确实生成了两种实现：testService对应的实现，testService1对应的实现
		 *       但猜测：testService和testService1均对应的是TestService这个接口：
		 *       		<dubbo:service interface="com.hqyt.TestService" ref="testService" />  
		 *       		<dubbo:service interface="com.hqyt.TestService" ref="testService1" />  
		 *       而接口TestService对应了两个具体的实现，所以最终无论按哪个名称来取bean，都是随机取其中一个bean：
		 *       							 			
		 *       		(testService，testService1) ------> TestService ------> (testService实现，testService1实现)
		 * 
		 * 		所以不要采用同一接口不同实现类的方法来做 
		 *   
		 */
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
