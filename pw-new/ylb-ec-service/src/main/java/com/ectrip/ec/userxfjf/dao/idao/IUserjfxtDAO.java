package com.ectrip.ec.userxfjf.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.user.Userjfxt;



public interface IUserjfxtDAO extends IGenericDao{
	
	//�����û�����
	public void addUserjf(Userjfxt userjf);
	
	//����û����ڻ���
	public void clearUserjf(String usid);
	
	//�����û�id��ȡ�û�����
	public List getByUsidUserjf(String usid);
	
	//�����û�Id��ȡ�û���������
	public List getUserjfNumb(String usid);
	
	//��ȡ�������ֵ
	public List getMaxSeq();
	
	//�����û����ֻ�ȡ�������ѱ���
	public List getblByjf(int userjf );	
	
	//����û�����
	public void delUserjfByUsid(String usid);
	/**
	 * �����û��������û����ֵ�״̬
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * return:void
	 * Date:2011-12-8
	 */
	public void updateUserjfByUsid(String usid);
	 
}
