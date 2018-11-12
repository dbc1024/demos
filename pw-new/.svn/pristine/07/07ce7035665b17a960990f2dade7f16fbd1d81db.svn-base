package com.ectrip.ec.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.user.Userbank;
import com.ectrip.ec.user.dao.idao.IUserbankDAO;

@RestController
public class UserbankController {

	@Autowired
	private IUserbankDAO userbankDAO;
	
	@RequestMapping("/userbank/findUserBank")
	public Userbank findUserBank(String userId){
		return userbankDAO.findUserBank(userId);
	}
}
