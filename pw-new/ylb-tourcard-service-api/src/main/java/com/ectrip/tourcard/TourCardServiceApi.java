package com.ectrip.tourcard;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TourCardServiceApi {
	
	/**
	 * 根据条件查询所有满足条件旅游卡明细
	 * @param cardNumber
	 * @param cardName
	 * @param profitNum
	 * @return
	 */
	@PostMapping("/tourCardDetail/v1/listAll")
	public List getCardDetailListAll(@RequestParam("cardNumber") String cardNumber, @RequestParam("cardName") String cardName ,@RequestParam("profitNum") String profitNum);
	
	
	/**
	 * 根据旅游卡号集合查询所有旅游卡明细
	 * @param cardNumbers
	 * @return
	 */
	@GetMapping(value = "/tourCardDetail/v1/getCardDetailsByCardNumbers")
	public List getCardDetailsByCardNumbers(@RequestParam("cardNumbers") String cardNumbers);

}
