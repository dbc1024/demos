package com.ectrip.ec.job;

public class TestJob {
	 public void execute(){  
	        try{  
	           System.out.println( new java.util.Date());
	         }catch(Exception ex){  
	             ex.printStackTrace();  
	         }  
	     }  
}
