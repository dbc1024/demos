package com.ectrip.ec.book.shopcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.book.shopcart.service.iservice.IShopCartService;


@RestController
public class ShopCartController {
	
	@Autowired
	private IShopCartService shopcartService;
	
	@RequestMapping("/shopCart/updateOrderStatus")
	public void updateOrderDdzt(String orid,String ddzt){
		
		shopcartService.updateOrderDdzt(orid, ddzt);
	}
	
	/*
	 * 
	 */

}
