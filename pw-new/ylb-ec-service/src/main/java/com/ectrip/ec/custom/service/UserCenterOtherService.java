package com.ectrip.ec.custom.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.custom.dao.idao.IUserCenterOtherDAO;
import com.ectrip.ec.custom.service.iservice.IUserCenterOhterService;

public class UserCenterOtherService implements IUserCenterOhterService {
	private IUserCenterOtherDAO usercenterotherDao;
	

	public IUserCenterOtherDAO getUsercenterotherDao() {
		return usercenterotherDao;
	}

	public void setUsercenterotherDao(IUserCenterOtherDAO usercenterotherDao) {
		this.usercenterotherDao = usercenterotherDao;
	}

	/**
	 * ��ѯ�û��������͵����ö�
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url ��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-13
	 */
	public PaginationSupport queryAllCreditList(String usid,int page,int pageSize,String url){
		return usercenterotherDao.queryAllCreditList(usid, page, pageSize, url);
	}
	
	/**
	 * ��������ѯ��Ӧ��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param ctype ���ö����
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-13
	 */
	public PaginationSupport queryCreditListByDates(String usid,String ctype,int page,int pageSize,String url,List list){
		return usercenterotherDao.queryCreditListByDates(usid, ctype, page, pageSize, url,list);
	}
	
	
	
	public List getFsusid(String usid) throws Exception {
	    List list=usercenterotherDao.findFsUsid(usid);
	    return list; 
	}
	
	public PaginationSupport getUserJifenDetail(String usid,int page,int pageSize,String url,String starttime,String endtime){
	    return usercenterotherDao.getUserJifenDetail(usid, page, pageSize, url,starttime,endtime); 
	}
	
	
}

