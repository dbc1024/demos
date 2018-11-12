package com.ectrip.ec.job;

import com.ectrip.ec.job.ijob.TaskLogService;

public class ReportEngineObject extends TaskLogService{

	synchronized public int jobrun(){
		try{
		    System.out.println("this is implement ReportEngine   .........");
		    return 0;
		}catch(Exception se){
		   return -1;
		}
		
	}
	
}
  
