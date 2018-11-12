package com.ectrip.sys.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.sys.bank.dao.idao.IBankDAO;
import com.ectrip.sys.bank.service.iservice.IBankService;
import com.ectrip.sys.bank.service.iservice.IPullSeatService;

@RestController
public class PullSeatController {
	
	@Autowired
	private IPullSeatService pullSeatService;
	
	@PostMapping("/pullSeat/pullSeat")
	List<Seatordertab> pullSeat(@RequestParam("orid")String orid) throws Exception{
		
		return pullSeatService.pullSeat(orid);
	}
	

	
}
