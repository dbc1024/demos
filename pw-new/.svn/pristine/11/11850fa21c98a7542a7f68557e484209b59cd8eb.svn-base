package com.ectrip.ticket.checkticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.checkticket.dao.idao.IPassCardDao;
import com.ectrip.ticket.checkticket.service.iservice.IPassCardService;

@Service
public class PassCardService extends GenericService implements IPassCardService {
	
	@Autowired
	private IPassCardDao passCardDao;

	/**
	 * *
	 * Describe:判断卡号类型
	 * @see com.ectrip.checkticket.service.iservice.IPassCardService#CheckPassCrad(java.lang.String, java.lang.String)
	 * @param accid		闸机ID
	 * @param szticketprintno	卡号
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-20
	 */
	public String CheckPassCrad(String accid, String szticketprintno) {
		return this.passCardDao.CheckPassCrad(accid, szticketprintno);
	}
	public String CheckPassCradoneTable(String accid, String szticketprintno) {
		return this.passCardDao.CheckPassCradoneTable(accid, szticketprintno);
	}
}

