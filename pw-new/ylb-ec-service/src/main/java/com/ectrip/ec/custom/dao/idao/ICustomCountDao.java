package com.ectrip.ec.custom.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface ICustomCountDao extends IGenericDao {
	/**
	 * 
	 * Describe:ϵͳ�û�
	 * @author:chenxinhao
	 * @param lgtp
	 * @param ttlb
	 * @param ibusinessid
	 * @param rzti
	 * @param ldti
	 * @return
	 * return:List
	 * Date:2012-11-2
	 */
	public List customlist(String lgtp,String ttlb,long ibusinessid,String rzti,String ldti);
}

