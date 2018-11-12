package com.ectrip.ticket.checkstssold.controller;
import com.ectrip.base.action.BaseController;
import com.ectrip.ticket.checkstssold.service.iservice.ICheckStssoldService;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.service.model.Stssoldtickettab;

/**
 * 根据票号查询检票信息
 * @author lijingrui
 */
public class CheckStssoldController extends BaseController{
	
	private Stssoldtickettab stssticket; //售出门票表
	
	private Stssalesvouchertab salesvoucher; //销售凭证表
	
	private ICheckStssoldService stssoldService;
	
	public Stssalesvouchertab getSalesvoucher() {
		return salesvoucher;
	}

	public void setSalesvoucher(Stssalesvouchertab salesvoucher) {
		this.salesvoucher = salesvoucher;
	}

	public Stssoldtickettab getStssticket() {
		return stssticket;
	}

	public void setStssticket(Stssoldtickettab stssticket) {
		this.stssticket = stssticket;
	}

	public ICheckStssoldService getStssoldService() {
		return stssoldService;
	}

	public void setStssoldService(ICheckStssoldService stssoldService) {
		this.stssoldService = stssoldService;
	}

	/**
	 * 
	 * Describe:根据票号查询检票信息
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 14, 2012
	 * @throws Exception 
	 */
	public String showViewStssoldcheck() throws Exception{/*
		
		//判断此票印刷号是否存在
		if(!stssoldService.getStssoldticketLookview(stssticket.getSzticketprintno())){
			//不存在
			this.addActionMessage(getText(stssticket.getSzticketprintno()+"stssoldService.StssoldticketLookview.required"));
			return INPUT;
		}else{
			//判断此票是否检票
			if(!stssoldService.getQuerystssold(stssticket.getSzticketprintno())){
				//未检票
				this.addActionMessage(getText(stssticket.getSzticketprintno()+"stssoldService.Querystssold.required"));
				return INPUT;
			}else{
				//显示此票的检票信息
				salesvoucher=stssoldService.showViewStssoldticket(stssticket.getSzticketprintno());
				return SUCCESS;
			}
		}
		
	*/
		return null;
	}
}