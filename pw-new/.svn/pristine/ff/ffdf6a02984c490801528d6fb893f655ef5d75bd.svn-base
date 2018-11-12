package com.ectrip.ec.balance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.ec.balance.dao.idao.IOrderCheckDAO;
import com.ectrip.ec.balance.service.iservice.IOrderCheckService;
@Service
public class OrderCheckService implements IOrderCheckService{
	@Autowired
	private IOrderCheckDAO ordercheckDao;
	

	public IOrderCheckDAO getOrdercheckDao() {
		return ordercheckDao;
	}
	public void setOrdercheckDao(IOrderCheckDAO ordercheckDao) {
		this.ordercheckDao = ordercheckDao;
	}
	/**
	 * ���ݶ���--����ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param titype
	 * @return
	 * @throws Exception
	 * return:int
	 * Date:2011-11-14
	 */
	public int ProviderYfkOK(String orid,String titype) throws Exception{
		return ordercheckDao.ProviderYfkOK(orid, titype);
	}
	/**
	 * ���ݷ�����--����ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param rzti
	 * @param ldti
	 * @param titype
	 * @return
	 * @throws Exception
	 * return:int
	 * Date:2011-11-14
	 */
	public int ProviderYfkOKByPdno(Long pdno,String rzti,String ldti,String titype) throws Exception {
		return ordercheckDao.ProviderYfkOKByPdno(pdno, rzti, ldti, titype);
	}
	/**
	 * ���ݶ����״�ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param stdt
	 * @param etdt
	 * @param titype
	 * return:void
	 * Date:2011-11-14
	 */
	public int updateOrder(String orid,String stdt,String etdt,String titype){
		return ordercheckDao.updateOrder(orid, stdt, etdt, titype);
	}
	/**
	 * ���ݷ������״�ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param stdt
	 * @param etdt
	 * @param titype
	 * return:void
	 * Date:2011-11-14
	 */
	public int updateOrderProvider(Long pdno,String stdt,String etdt,String titype){
		return ordercheckDao.updateOrderProvider(pdno, stdt, etdt, titype);
	}
	
}

