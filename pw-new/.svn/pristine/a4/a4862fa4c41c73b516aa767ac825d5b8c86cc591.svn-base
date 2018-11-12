package com.ectrip.ticket.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.statistics.service.iservice.IStatisticsService;

@RestController
public class StatisicsController {
	
	@Autowired
	private IStatisticsService statisticsService;

	@PostMapping("/statisics/getNumjifenset")
	public Numjifenset getNumjifenset(@RequestParam("nid")String iscenicid) {
		return statisticsService.getNumjifenset(iscenicid);
		
	}
	
	@PostMapping("/statisics/getSalesRule")
	public Numjifensetlist getSalesRule(@RequestParam("nid")Long itickettypeid, @RequestParam("nid")Long nid,@RequestParam("nid")Long icrowid,@RequestParam("nid")Long ibusinessid,@RequestParam("nid")String date) {
		return statisticsService.getSalesRule(itickettypeid, nid, icrowid, ibusinessid, date);
		
	}
	
	@PostMapping("/statisics/getSalesRule2")
	public Numjifensetlist getSalesRule(@RequestParam("itickettypeid")String itickettypeid,@RequestParam("nid")String nid) {
		return statisticsService.getSalesRule(itickettypeid, nid);
	}
	
	
	@PostMapping("/statisics/getJifenByUser")
	public Usernumjf getJifenByUser(@RequestParam("nid")String starttime,@RequestParam("nid")String endtime,@RequestParam("nid")String usid,@RequestParam("nid")Long jflb,@RequestParam("nid")Long isecnicid) {
		return statisticsService.getJifenByUser(starttime, endtime, usid, jflb, isecnicid);
	}
	
}
