package com.ectrip.ec.home.controller;

import com.ectrip.ec.home.service.iservice.IHomeService;
import com.ectrip.ec.model.user.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private IHomeService homeService;
	
	@RequestMapping("/getDomain")
	public List<Domain> getDomain(@RequestParam String url, @RequestParam String s){
		return homeService.getDomain(url,s);
	}
}
