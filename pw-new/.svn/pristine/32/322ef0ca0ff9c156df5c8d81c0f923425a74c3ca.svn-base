package com.ectrip.ticket.sale.service.card.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.sale.service.card.dao.idao.IPosWebDao;
import com.ectrip.ticket.sale.service.card.model.GetEsbscenicareatabResponse;
import com.ectrip.ticket.sale.service.card.model.PosLoginRequest;
import com.ectrip.ticket.sale.service.card.model.PosLoginResponse;
import com.ectrip.ticket.sale.service.card.service.iservice.IPosWebService;

public class PosWebService extends GenericService implements IPosWebService {
	
	@Autowired
	private IPosWebDao posWebDao;

	public GetEsbscenicareatabResponse getScenicList() {
		GetEsbscenicareatabResponse res = new GetEsbscenicareatabResponse();
		res.setCode("0000");
		res.setDescribe("�ɹ�");
		res.setScenicareas(posWebDao.getScenicList());
		return res;
	}
	
	public PosLoginResponse posLogin(PosLoginRequest request){
		
		PosLoginResponse res = posWebDao.posLogin(request);
		if(res.getEmployee() == null || res.getEsbticketwintab() == null || res.getZffses().size() == 0){
			if(res.getEmployee() == null){
				res.setCode("2001");
				res.setDescribe("��ȡ��ƱԱ��Ϣʧ��");
			}
			if(res.getEsbticketwintab() == null){
				res.setCode("2001");
				res.setDescribe("��ȡ������Ϣʧ��");
			}
			if(res.getZffses() == null){
				res.setCode("2001");
				res.setDescribe("��ȡ֧����ʽ�б�ʧ��");
			}
		}else{
			res.setCode("0000");
			res.setDescribe("�ɹ�");
		}
		return res;
	}

	public IPosWebDao getPosWebDao() {
		return posWebDao;
	}
	@Autowired
	public void setPosWebDao(IPosWebDao posWebDao) {
		this.posWebDao = posWebDao;
	}
}
