package com.ectrip.sys.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.sys.message.service.iservice.IMbMessageService;

@RestController
public class MbMessageController {
	
	@Autowired
	private IMbMessageService mbmessageService;
	
	/*@Autowired
	private SendDao sendDao;*/
	
	@RequestMapping("/message/sendMessageNew")
	public void sendMessageNew(@RequestParam("telno") String telno,@RequestParam("type") String type,@RequestParam("content") String content){
		String[] contents = content.split(",");
		
		mbmessageService.sendMessageNew(telno, type, contents);
	}
	
	@RequestMapping("/message/dbSendInfo")
	public int DbSendInfo() throws Exception{
//		return sendDao.DbSendInfo();
		return 0;
	}

}
