package com.ectrip.ec.custom.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.custom.dao.idao.ICustomCountDao;
import com.ectrip.ec.custom.service.iservice.ICustomCountService;

public class CustomCountService extends GenericService implements ICustomCountService {
	ICustomCountDao customCountDao;
	
	public ICustomCountDao getCustomCountDao() {
		return customCountDao;
	}

	public void setCustomCountDao(ICustomCountDao customCountDao) {
		this.customCountDao = customCountDao;
	}
	/**
	 * *
	 * Describe:ϵͳ�û�
	 * @see com.ectrip.custom.service.iservice.ICustomCountService#customlist(java.lang.String, java.lang.String, long, java.lang.String, java.lang.String)
	 * @param lgtp
	 * @param ttlb
	 * @param ibusinessid
	 * @param rzti
	 * @param ldti
	 * @return
	 * @author chenxinhao
	 * Date:2012-11-2
	 */
	public List customlist(String lgtp, String ttlb, long ibusinessid,
			String rzti, String ldti) {
		return this.customCountDao.customlist(lgtp, ttlb, ibusinessid, rzti, ldti);
	}
	
}

