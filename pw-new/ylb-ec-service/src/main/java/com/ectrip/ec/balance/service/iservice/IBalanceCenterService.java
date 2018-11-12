package com.ectrip.ec.balance.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.balance.Useryfk;

/**
 * ��������
 * @author huangyuqi
 */
public interface IBalanceCenterService extends IGenericService{

	/**
	 * �����û���Ԥ�������
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:float
	 * Date:2011-11-11
	 */
	public float getsumMoney(String usid);
	
	/**
	 * ��ȡ�������ֵ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:int
	 * Date:2011-11-16
	 */
	public int getMaxSeq(String tablename,String column) ;

	
	/**
	 * Ԥ����棬�����������������������ά��Ԥ����ϸ�����һ��
	 */
	public int useryfksave(Useryfk yfk);
	/**
	 * Ԥ�����
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid������
	 * @param typesԤ�������1����-1���٣�
	 * @param yfkfsԤ������Դ(01��ֵԤ����02�˶�תԤ����03����Ԥ����04Ԥ����ת�ֽ�05��������תԤ����15����תԤ����)
	 * @param mont���
	 * @param note��ע
	 * return:void
	 * Date:2011-12-8
	 */
	public void saveUseryfk(String orid,int types,String yfkfs,Double mont,String note);
	/**
	 * Ԥ�����
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid Ҫ�ı�Ԥ������û�
	 * @param orid������
	 * @param typesԤ�������1����-1���٣�
	 * @param yfkfsԤ������Դ(01��ֵԤ����02�˶�תԤ����03����Ԥ����04Ԥ����ת�ֽ�05��������תԤ����15����תԤ����)
	 * @param mont���
	 * @param note��ע
	 * return:void
	 * Date:2011-12-8
	 */
	public void saveUseryfk(String usid,String orid,int types,String yfkfs,Double mont,String note);
	/**
	 * ���ݶ����Ų�����һ���û�Ԥ������Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:Useryfk
	 * Date:2011-12-8
	 */
	public Useryfk queryUseryfkByOrid(String orid);
}

