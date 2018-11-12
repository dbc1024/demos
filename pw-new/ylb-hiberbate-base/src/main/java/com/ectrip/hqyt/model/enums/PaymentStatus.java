/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ectrip.hqyt.model.enums;

/**   
* @Title: PaymentStatus.java 
* @Package com.hqyatu.enums
* @Description: 未支付；部分支付；已完成支付
* @author LIXIANKUO 
* @date 2016年7月15日 下午2:22:15 
* @version V1.0   
*/
public enum PaymentStatus {

	PENDING("未支付"),
	PARTIAL_PAID("部分支付"),
	PAID("已完成支付"),
	FALSE("支付失败");
	
	PaymentStatus(String text) {
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
