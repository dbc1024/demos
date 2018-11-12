package com.ectrip.ec.order.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;

public interface IOrderCheckDAO extends IGenericDao {
	/**
	 * ���ݶ���--����ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param titype
	 * @throws Exception
	 * return:void
	 * Date:2011-11-14
	 */
	public int ProviderYfkOK(String orid,String titype) throws Exception ; 
	/**
	 * ���ݷ�����--����ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param rzti
	 * @param ldti
	 * @param titype
	 * @return void
	 * @throws Exception
	 * return:void
	 * Date:2011-11-14
	 */
	public int ProviderYfkOKByPdno(Long pdno,String rzti,String ldti,String titype) throws Exception ;
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
	public int updateOrder(String orid,String stdt,String etdt,String titype);
	/**
	 * ���ݷ���������ȷ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param stdt
	 * @param etdt
	 * @param titype
	 * return:void
	 * Date:2011-11-14
	 */
	public int updateOrderProvider(Long pdno,String stdt,String etdt,String titype);

}

