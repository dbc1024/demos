package com.ectrip.ec.report.system.ticketsale.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.order.Ticketchecklist;

/**
 * ��Ʊ��ѯҵ���߼���ӿ�
 * @author ChaoYuHang
 */
public interface ITicketCheckupService {

	/**
	 * Describe: ���ݴ��š�ͨ��֤�š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @auth:ChaoYuHang
	 * @param chuanhao ����
	 * @param tongxingzhi ͨ��֤
	 * @param date ����
	 * @param ticketCode Ʊ��
	 * @return ���������ϵ�List
	 * return:List
	 * Date:2012-8-1
	 */
	public PaginationSupport findByProperties(String chuanhao, String tongxingzhi,String date, String ticketCode,int pageSize,int startIndex, String url);

	/**
	 * Describe: ���� �۹⳵�š�˾������ ���Խ��м�Ʊ��ѯ
	 * @auth:ChaoYuHang
	 * @param sightseeingNo  �۹⳵��
	 * @param driverNo   ˾������
	 * @param date  ����
	 * @param ticketCode  Ʊ��
	 * @return
	 * return:List
	 * Date:2012-8-7
	 */
	public PaginationSupport findByNote(String sightseeingNo,String driverNo,String date,String ticketCode,int pageSize,int startIndex, String url);
	
	/**
	 * Describe: ���ݴ��ź�ͨ��֤�����ɱ���
	 * @auth:ChaoYuHang
	 * @param ticketchecklist ��Ʊ��ϸ��Ϣ����
	 * @param date  ����
	 * @param user  �û���
	 * @param corpname ���Ʊ���λ��
	 * @return
	 * @throws Exception
	 * return:ReportDefine
	 * Date:2012-8-8
	 */
//	public ReportDefine reportTicketByShipno(Ticketchecklist ticketchecklist,String date,String user,String corpname)throws Exception;
	
	/**
	 * Describe: ���ݹ۹⳵�ź�˾���������ɱ���
	 * @auth:ChaoYuHang
	 * @param ticketchecklist ��Ʊ��ϸ��Ϣ����
	 * @param date ����
	 * @param user �û���
	 * @param corpname ���Ʊ���λ��
	 * @return
	 * @throws Exception
	 * return:ReportDefine
	 * Date:2012-8-8
	 */
//	public ReportDefine reportTicketByNote(Ticketchecklist ticketchecklist,String date,String user,String corpname) throws Exception;

	/**
	 * Describe: ���ݼ�Ʊ��ʶ����
	 * @auth:ChaoYuHang
	 * @param id ��Ʊ��ʶ
	 * @return
	 * return:Ticketchecklist ��Ʊ����
	 * Date:2012-8-17
	 */
	public Ticketchecklist findById(Long id);
	
	/**
	 * Describe: ���ݴ��źͼ�Ʊ�������Ͳ���
	 * @auth:ChaoYuHang
	 * @param shipNo ����
	 * @param type ��Ʊ��������
	 * @return
	 * return:List
	 * Date:2012-8-17
	 */
	public List findByChuanHao(String shipNo,int type);
	
	/**
	 * Describe: �����Ʊ����
	 * @auth:ChaoYuHang
	 * @param checkId ��Ʊ��ʶ
	 * @param empId ��ǰ�û�ID
	 * return:void
	 * Date:2012-8-17
	 */
	public void saveAccount(String[] checkId,String empId);
}

