package com.ectrip.ticket.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ticket.provider.service.ITimeSharingService;

@RestController
public class TimeSharingController {
	
	@Autowired
	private ITimeSharingService timeSharingService;
	
	@PostMapping("/timeSharing/UpdateStock")
	public void UpdateStock(@RequestParam("timeId")Long timeId,@RequestParam("productId")String productId,@RequestParam("count")int count,@RequestParam("flag")String flag) throws Exception{
		timeSharingService.UpdateStock(timeId, productId, count, flag);
	}

}
