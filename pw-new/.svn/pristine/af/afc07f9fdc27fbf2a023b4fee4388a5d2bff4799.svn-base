package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.model.provider.Esbticketstationtab;

public interface ITicketStationService extends IGenericService{

	/**
	 * 售票点列表
	 */
	public List<Esbticketstationtab> TicketStationViewList();
	
	/**
	 * 售票点列表(分页显示)
	 */
	public List<Esbticketstationtab> getTicketStationPage(int pageSize,int startIndex,String url);
	
	/**
	 * 根据编号查询站点信息
	 * 
	 */
	public Esbticketstationtab viewTiketStation(Long iticketstationid);
	
	/**
	 * 增加售票点(景区{点}编号)
	 */
	public String addTicketStation();
	
	  /**
	   * 删除售票点(售票点编号)
	   */
	public void delTicketStation(Long iticketstationid);
	
	  /**
	   * 修改售票点(售票点编号)
	   */
	public void editTiketStation(Long iticketstationid);
			
		/**
		 * 保存操作(售票点编号)
		 */
	public void saveTiketStation();
	
	
       /**
        * 查询售票点(根据查询条件)
        */
	public List searchTiketStation();
}
