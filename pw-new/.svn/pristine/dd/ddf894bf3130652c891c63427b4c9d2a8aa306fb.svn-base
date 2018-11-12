package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.provider.dao.ITicketStationDAO;
import com.ectrip.ticket.provider.service.ITicketStationService;

@Service
public class TicketStationService extends GenericService implements ITicketStationService{
	
	private ITicketStationDAO  ticketStationDao;

	public ITicketStationDAO getTicketStationDao() {
		return ticketStationDao;
	}

	public void setTicketStationDao(ITicketStationDAO ticketStationDao) {
		this.ticketStationDao = ticketStationDao;
	}

	/**
	 * 显示所有售票点
	 * Describe:
	 * @auth:fengyi
	 * @param 
	 * @return
	 * Date:2011-9-27
	 */
	public List<Esbticketstationtab> TicketStationViewList() {
		return this.ticketStationDao.TicketStationViewList();
	}

	/**
	 * 增加售票点
	 * Describe:
	 * @auth:fengyi
	 * @param 
	 * @return
	 * Date:2011-9-27
	 */
	public String addTicketStation() {
		return null;
	}

	/**
	 * 根据ID售票点
	 * Describe:
	 * @auth:fengyi
	 * @param iticketstationid售票点编号 
	 * @return
	 * Date:2011-9-27
	 */
	public void delTicketStation(Long iticketstationid) {
		
	}

	/**
	 * 显示所有售票点
	 * Describe:
	 * @auth:fengyi
	 * @param 
	 * @return
	 * Date:2011-9-27
	 */
	public void editTiketStation(Long iticketstationid) {
		
	}

	/**
	 * 显示所有售票点
	 * Describe:
	 * @auth:fengyi
	 * @param 
	 * @return
	 * Date:2011-9-27
	 */
	public void saveTiketStation() {
		
	}

	/**
	 * 显示所有售票点
	 * Describe:
	 * @auth:fengyi
	 * @param 
	 * @return
	 * Date:2011-9-27
	 */
	public List searchTiketStation() {
		return null;
	}

	/**
	 * 根据Id查询售票点
	 * Describe:
	 * @auth:fengyi
	 * @param iticketstationid售票点Id 
	 * @return 
	 * Date:2011-9-27
	 */
	
	public Esbticketstationtab viewTiketStation(Long iticketstationid) {
		return this.ticketStationDao.viewTiketStation(iticketstationid);
	}

	/**
	 * 售票点列表(分页显示)
	 */
	public List<Esbticketstationtab> getTicketStationPage(int pageSize,
			int startIndex, String url) {
		return this.ticketStationDao.getTicketStationPage(pageSize, startIndex, url);
	}
}
