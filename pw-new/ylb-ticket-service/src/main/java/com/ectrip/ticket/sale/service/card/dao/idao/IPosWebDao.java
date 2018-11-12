package com.ectrip.ticket.sale.service.card.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.sale.service.card.model.PosLoginRequest;
import com.ectrip.ticket.sale.service.card.model.PosLoginResponse;

public interface IPosWebDao extends IGenericDao{
	
	/**
	 * ��ȡ�������б�
	 * @param request
	 * @return GetEsbscenicareatabResponse res
	 */
	public List<?> getScenicList();
	
	/**
	 * POS����½
	 * @param request
	 * @return PosLoginResponse res
	 */
	public PosLoginResponse posLogin(PosLoginRequest request);
}
