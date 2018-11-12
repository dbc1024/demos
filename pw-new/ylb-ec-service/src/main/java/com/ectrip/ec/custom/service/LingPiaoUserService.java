package com.ectrip.ec.custom.service;



import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.custom.dao.idao.ILingPiaoUserDAO;
import com.ectrip.ec.custom.service.iservice.ILingPiaoUserService;
import com.ectrip.ec.model.user.Lingpiaouser;
import com.ectrip.sys.model.syspar.Customlog;

public class LingPiaoUserService implements ILingPiaoUserService {
	private ILingPiaoUserDAO lingpiaouserDao;
	
	
	public ILingPiaoUserDAO getLingpiaouserDao() {
		return lingpiaouserDao;
	}
	public void setLingpiaouserDao(ILingPiaoUserDAO lingpiaouserDao) {
		this.lingpiaouserDao = lingpiaouserDao;
	}
	/**
	 * ������Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog�û���־
	 * return:void
	 * Date:2012-2-8
	 */
	public void addLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog){
		lingpiaouserDao.addLingPiaoUser(lingpiaouser, customlog);
	}
	/**
	 * �޸���Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void updateLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog){
		lingpiaouserDao.updateLingPiaoUser(lingpiaouser, customlog);
	}
	/**
	 * ɾ����Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void deleteLingPiaoUser(Long seq,Customlog customlog){
		lingpiaouserDao.deleteLingPiaoUser(seq, customlog);
	}
	/**
	 * �鿴
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Lingpiaouser
	 * Date:2012-2-8
	 */
	public Lingpiaouser queryLingPiaoUser(Long seq){
		return lingpiaouserDao.queryLingPiaoUser(seq);
	}
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
	public PaginationSupport queryLingPiaoUserList(String userId,int page,int pageSize,String url){
		return lingpiaouserDao.queryLingPiaoUserList(userId, page, pageSize, url);
	}
	/**
	 * �����û���ѯ���е���ϵ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:List
	 * Date:2012-2-14
	 */
	public List queryLingPiaoUserList(String usid){
		return lingpiaouserDao.queryLingPiaoUserList(usid);
	}

	/**
	 * ������ϵ��ΪĬ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * return:void
	 * Date:2012-2-15
	 */
	public void updateLingPiaoFirst(Lingpiaouser lingpiaouser){
		lingpiaouserDao.updateLingPiaoFirst(lingpiaouser);
	}
}

