package com.ectrip.ec.balance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.balance.service.iservice.IBalanceCenterService;
import com.ectrip.ec.model.balance.Useryfk;

/**
 * ��������
 * @author huangyuqi
 */
@Service
public class BalanceCenterService extends GenericService implements IBalanceCenterService {
	
	private IBalanceCenterDAO balancecenterDao;

	public IBalanceCenterDAO getBalancecenterDao() {
		return balancecenterDao;
	}

	@Autowired
	public void setBalancecenterDao(IBalanceCenterDAO balancecenterDao) {
		this.balancecenterDao = balancecenterDao;
		setGenericDao(balancecenterDao);
	}


	/**
	 * �����û���Ԥ�������
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:float
	 * Date:2011-11-11
	 */
	public float getsumMoney(String usid){
		return balancecenterDao.getsumMoney(usid);
	}
	/**
	 * ��ȡ�������ֵ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:int
	 * Date:2011-11-16
	 */
	public int getMaxSeq(String tablename,String column) {
		return balancecenterDao.getMaxSeq(tablename, column);
	}
	
	/**
	 * Ԥ����棬�����������������������ά��Ԥ����ϸ�����һ��
	 */
	public int useryfksave(Useryfk yfk){
		return balancecenterDao.useryfksave(yfk);
	}
	
	/**
	 * Ԥ�����
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid������
	 * @param typesԤ�������(1���ӣ�-1����)
	 * @param yfkfsԤ������Դ(01��ֵԤ����02�˶�תԤ����03����Ԥ����04Ԥ����ת�ֽ�05��������תԤ����15����תԤ����)
	 * @param mont���
	 * @param note��ע
	 * return:void
	 * Date:2011-12-8
	 */
	public void saveUseryfk(String orid,int types,String yfkfs,Double mont,String note){
		balancecenterDao.saveUseryfk(orid, types, yfkfs, mont,note);
	}
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
	public void saveUseryfk(String usid,String orid,int types,String yfkfs,Double mont,String note){
		balancecenterDao.saveUseryfk(usid, orid, types, yfkfs, mont, note);
	}
	/**
	 * ���ݶ����Ų�����һ���û�Ԥ������Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:Useryfk
	 * Date:2011-12-8
	 */
	public Useryfk queryUseryfkByOrid(String orid){
		return balancecenterDao.queryUseryfkByOrid(orid);
	}
}

