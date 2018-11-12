package com.ectrip.ec.book.rentrl.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.rentrl.Carticketrecordtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public interface IRentrlService extends IGenericService{
	/**
	 * ���ݷ��������ͻ�÷����̿�Դ��
	 * getRentrlSourceList(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param pdtp
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-11-2 ����02:35:07
	 * @since  1.0.0
	 */
	public List getRentrlSourceList(String pdtp) ;
	/**
	 * ��ѯ�������̲�Ʒ(�⳵)
	 * getrentrlProductList(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return 
	 * PaginationSupport
	 * @exception 
	 * @date:2013-11-2 ����02:36:25
	 * @since  1.0.0
	 */
	public PaginationSupport getRentrlProductList(String rzti, String ldti, String lgtp, int page, int pageSize, String url);
	/**
	 * ��ѯ��ĳ�����µķ����̲�Ʒ
	 * Describe:
	 * @auth:hejiahua
	 * @param regionId��Դ��
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2013��11��2�� 14:37:12
	 */
	public PaginationSupport getRentrlProductList(Long regionId,String rzti,String ldti,String lgtp,int page,int pageSize,String url);
	/**
	 * ��ѯ�⳵
	 * Describe:
	 * @auth:hejiahua
	 * @param provider
	 * @param price
	 * @param product
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2013��11��2�� 14:41:45
	 */
	public PaginationSupport getRentrlProductSearchList(Esbscenicareatab provider,String rzti,String ldti,String lgtp,int page,int pageSize,String url);
	
	/**
	 * 
	 * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * @auth:hejiahua
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return
	 * return:Esbscenicareatab
	 * Date:2013��11��2�� 14:41:38
	 */
	public Esbscenicareatab getRentrlTicketduct(Long iscenicid,String rzti,String ldti,String lgtp)throws Exception;
	
	/**
	 * 
	 * Describe:�⳵�����еĳ����Ƽ�
	 * @auth:hejiahua
	 * @param szregionalid
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2013��11��2�� 14:43:34
	 */
	public PaginationSupport shopAllticketcenter(Long szregionalid,String lgtp,int page,int pageSize,String url);
	
	/**
	 * ��鳵���Ƿ��п��
	 * checkRentrl(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param tickettypeid ��Ʒid
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-11-18 ����02:33:18
	 * @since  1.0.0
	 */
	public boolean checkRentrl(Long tickettypeid);
	
	/**
	 * ����Ԥ��������¼
	 * saveCarRecord(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param carticketrecordtab 
	 * void
	 * @exception 
	 * @date:2013-11-18 ����03:36:57
	 * @since  1.0.0
	 */
	public void saveCarRecord(Carticketrecordtab carticketrecordtab);
	/**
	 * ��ť״̬��ѯ
	 * btncheck(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-11-19 ����09:10:17
	 * @since  1.0.0
	 */
	public boolean btncheck(Long itickettypeid);
	/**
	 * ��ѯ����
	 * findAvgPrice(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid ��Ʒid
	 * @param startDate ��ʼ����
	 * @param endDate ��������
	 * @return ����
	 * double
	 * @exception 
	 * @date:2013-11-26 ����02:14:46
	 * @since  1.0.0
	 */
	public double findAvgPrice(Long itickettypeid,String startDate,String endDate);
	/**
	 * ��ѯ�۸��Ƿ����
	 * findPrice(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid ��Ʒid
	 * @param date ����
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2013-11-26 ����03:11:10
	 * @since  1.0.0
	 */
	public boolean findPrice(Long itickettypeid,String date);
}
