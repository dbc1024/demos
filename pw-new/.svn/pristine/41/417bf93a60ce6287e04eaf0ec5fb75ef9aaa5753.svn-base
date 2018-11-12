package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.report.system.datereport.dao.idao.ILxsDao;
import com.ectrip.ec.report.system.datereport.service.iservice.ILxsService;

public class LxsService extends GenericService implements ILxsService {

	private ILxsDao lxsDao;

	public ILxsDao getLxsDao() {
		return lxsDao;
	}

	public void setLxsDao(ILxsDao lxsDao) {
		this.lxsDao = lxsDao;
	}
	
	public List findzLxs(){
		return this.lxsDao.findzLxs();
	}
	
	public List findzfLxs(){
		return this.lxsDao.findzfLxs();
	}
	
	public List findcustombyibusinessid(long ibusinessid){
		return this.lxsDao.findcustombyibusinessid(ibusinessid);
	}
	/**
	 * �����ɢ�ͺ�������ҵ�������ҵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List businessother(){	
		return lxsDao.businessother();
	}
	public String showcustom(Long ibusinessid){
		return lxsDao.showcustom(ibusinessid);
	}
	public List findonezLxs(){
		return lxsDao.findonezLxs();
	}
	public List findsonLxs(String usid){
		return lxsDao.findsonLxs(usid);
	}
}

