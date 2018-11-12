package com.ectrip.ticket.salesmanager.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.salesmanager.dao.IOpcemployeecardsubtabDAO;
import com.ectrip.ticket.salesmanager.service.IOpcemployeecardsubtabService;

/**
 * @author Jzhenhua,Created Time 2011-10-07
 */
public class OpcemployeecardsubtabService implements
		IOpcemployeecardsubtabService {

	private IOpcemployeecardsubtabDAO opcemployeecardsubtabDao;


	public IOpcemployeecardsubtabDAO getOpcemployeecardsubtabDao() {
		return opcemployeecardsubtabDao;
	}

	public void setOpcemployeecardsubtabDao(
			IOpcemployeecardsubtabDAO opcemployeecardsubtabDao) {
		this.opcemployeecardsubtabDao = opcemployeecardsubtabDao;
	}

	/**
	 * 添加
	 */
	public void addPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.opcemployeecardsubtabDao.addPloyeecardSub(opcemployeecardsubtab);
	}

	/**
	 * 删除
	 */
	public void delPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.opcemployeecardsubtabDao.delPloyeecardSub(opcemployeecardsubtab);
	}

	/**
	 * 获取最大ID
	 */
	public Long getMaxId() {
		List list = this.opcemployeecardsubtabDao.getMaxId();
		Long id = new Long(0);
		if (list.get(0) != null) {
			id = Long.parseLong(list.get(0).toString());
		}

		return id + 1;
	}

	/**
	 * 获取相对应的子表信息
	 */
	public PaginationSupport getPloyeecardSubByCardId(Long ployeecardId,
													  int pageSize, int startIndex, String url) {
		return this.opcemployeecardsubtabDao.getPloyeecardSubByCardId(
				ployeecardId, pageSize, startIndex, url);
	}

	/**
	 * 根据编号获取
	 */
	public Opcemployeecardsubtab getPloyeecardSubById(Long id) throws Exception {
		return this.opcemployeecardsubtabDao.getPloyeecardSubById(id);
	}

	/**
	 * 修改编号
	 */
	public void updatePloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.opcemployeecardsubtabDao
				.updatePloyeecardSub(opcemployeecardsubtab);
	}

	/**
	 * *
	 * Describe:在某一员工卡证下，子表中园门不能重复添加
	 * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardsubtabService#getPloyeecardSubGard(java.lang.Long, java.lang.Long)
	 * @param igardengateid
	 * @param iemployeecardid
	 * @return
	 * @author lijingrui
	 * Date:Nov 15, 2011
	 */
	public boolean getPloyeecardSubGard(Long igardengateid,Long iemployeecardid){
		return opcemployeecardsubtabDao.getPloyeecardSubGard(igardengateid, iemployeecardid);
	}


}
