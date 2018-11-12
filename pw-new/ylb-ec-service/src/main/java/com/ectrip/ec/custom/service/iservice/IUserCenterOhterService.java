package com.ectrip.ec.custom.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;

public interface IUserCenterOhterService {
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
	public PaginationSupport queryAllCreditList(String usid,int page,int pageSize,String url);
	
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
	public PaginationSupport queryCreditListByDates(String usid,String ctype,int page,int pageSize,String url,List list);
	
	
	public List getFsusid(String usid) throws Exception ;
	
	public PaginationSupport getUserJifenDetail(String usid,int page,int pageSize,String url,String starttime,String endtime);

}

