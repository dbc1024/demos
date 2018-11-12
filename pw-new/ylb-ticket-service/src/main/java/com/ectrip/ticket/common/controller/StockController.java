package com.ectrip.ticket.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;

@RestController
public class StockController {
	
	@Autowired
	private IStockService stockService;
	
	/**
     * 保存库存信息
     * @param stockOrderInfos 订单数据列表
     */
	@PostMapping("/stock/saveStockDetails")
	public void saveStockDetails(@RequestParam("stockOrderInfos") String stockOrderInfos,@RequestParam("isCheck") boolean isCheck) {
		stockService.saveStockDetails(JSON.parseArray(stockOrderInfos, StockOrderInfo.class), isCheck);
	}
    
	/**
     * 删除不存的库存数据
     * @param orid  订单号
     * @param providerid    服务商ID
     * @param seq   主键--可不填
     * @param noInclude 不包含的数据--可不填
     */
	@PostMapping("/stock/deleteStockDetails")
    public void deleteStockDetails(@RequestParam("orid") String orid, @RequestParam("providerid") Long providerid, @RequestParam("seq") Long seq, @RequestParam("noInclude") String noInclude) {
    	stockService.deleteStockDetails(orid, providerid, seq, noInclude);
    }
    
	/**
     * 检查用户库存
     * @param stockOrderInfos
     * @return
     */
	@PostMapping("/stock/checkCustomStock")
    public String checkCustomStock(@RequestParam("stockOrderInfos") String stockOrderInfos) {
		return stockService.checkCustomStock(JSON.parseArray(stockOrderInfos, StockOrderInfo.class));
    }
    
    /**
     * 检查库存信息
     * @param stockOrderInfos
     * @return
     */
	@PostMapping("/stock/checkStock")
    public String checkStock(@RequestParam("stockOrderInfos") String stockOrderInfos) {
		return stockService.checkStock(JSON.parseArray(stockOrderInfos, StockOrderInfo.class));
    	
    }
	
	@PostMapping("/stock/selectStockDetail")
	public Stockdetailtab selectStockDetail(@RequestBody StockOrderInfo stockOrderInfo) {
		return stockService.selectStockDetail(stockOrderInfo);
		
	}
    
	/**
	 * 
	 * deleteStockDetails
	 * selectStockDetail
	 */

}
