package com.ectrip.ticket.sale.service.card.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.sale.service.card.model.GetEsbscenicareatabResponse;
import com.ectrip.ticket.sale.service.card.model.PosLoginRequest;
import com.ectrip.ticket.sale.service.card.model.PosLoginResponse;

public interface IPosWebService extends IGenericService{
	
	/**
	 * ��ȡ�������б�
	 * @param request
	 * @return GetEsbscenicareatabResponse res
	 */
	public GetEsbscenicareatabResponse getScenicList();
	
	/**
	 * POS����½
	 * @param request
	 * @return PosLoginResponse res
	 */
	public PosLoginResponse posLogin(PosLoginRequest request);
}
