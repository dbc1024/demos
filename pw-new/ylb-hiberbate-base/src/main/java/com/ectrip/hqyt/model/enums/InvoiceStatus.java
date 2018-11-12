/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ectrip.hqyt.model.enums;

/**   
* @Title: InvoiceStatus.java 
* @Package com.hqyatu.enums
* @Description: 失效；退款撤单；待付款；交易成功；交易结束不可再操作。
* @author LIXIANKUO 
* @date 2016年7月15日 下午2:07:14 
* @version V1.0   
*/
public enum InvoiceStatus {
	
	OUT_DATED("失效"),
	REFUNDED("退款撤单"),
	PENDING("待付款"), //支付中  --商户中心展示
	SUCCESS("交易成功"),//支付完成 --商户中心展示
 	CLOSE("交易结束不可再操作"),//取消并关闭 --商户中心展示
	FALSE("交易失败"),
	TOBECONFIRM("已付款待确认");
	
	InvoiceStatus(String text) {
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}