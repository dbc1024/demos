package com.ectrip.ec.custom.service.iservice;



import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Lingpiaouser;
import com.ectrip.sys.model.syspar.Customlog;

public interface ILingPiaoUserService {
	/**
	 * ������Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog�û���־
	 * return:void
	 * Date:2012-2-8
	 */
	public void addLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog);
	/**
	 * �޸���Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void updateLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog);
	/**
	 * ɾ����Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void deleteLingPiaoUser(Long seq,Customlog customlog);
	/**
	 * �鿴
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Lingpiaouser
	 * Date:2012-2-8
	 */
	public Lingpiaouser queryLingPiaoUser(Long seq);
	/**
	 * ��ѯ��Ʊ��ϵ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-2-8
	 */
	public PaginationSupport queryLingPiaoUserList(String userId,int page,int pageSize,String url);
	/**
	 * �����û���ѯ���е���ϵ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:List
	 * Date:2012-2-14
	 */
	public List queryLingPiaoUserList(String usid);
	/**
	 * ������ϵ��ΪĬ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * return:void
	 * Date:2012-2-15
	 */
	public void updateLingPiaoFirst(Lingpiaouser lingpiaouser);
}

