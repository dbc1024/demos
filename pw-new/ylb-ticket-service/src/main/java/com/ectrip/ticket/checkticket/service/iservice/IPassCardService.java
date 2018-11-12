package com.ectrip.ticket.checkticket.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;

public interface IPassCardService extends IGenericService {
	/**
	 *
	 * Describe:判断卡号类型
	 * @author:chenxinhao
	 * @param accid		闸机ID
	 * @param szticketprintno	卡号
	 * @return
	 * return:String
	 * Date:2012-9-20
	 */
	public String CheckPassCrad(String accid, String szticketprintno);


	public String CheckPassCradoneTable(String accid, String szticketprintno);
}

