package com.ectrip.ec.report.system.ticketsale.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.order.Ticketchecklist;

public interface ITicketCheckupDAO extends IGenericDao{
	
	/**
	 * Describe: ���ݴ��š�ͨ��֤�š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @auth:ChaoYuHang
	 * @param chuanhao ����
	 * @param tongxingzhi ͨ��֤
	 * @param date  ����
	 * @param ticketCode  Ʊ��
	 * @return ���������ϵ�List
	 * return:List
	 * Date:2012-8-1
	 */
	public PaginationSupport findByProperties(String chuanhao,String tongxingzhi,String date,String ticketCode,int pageSize,int startIndex, String url);

	/**
	 * Describe: ���ݹ۹⳵�š�˾�����š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @auth:ChaoYuHang 
	 * @param sightseeingNo �۹⳵��
	 * @param driverNo ˾������
	 * @param date ����
	 * @param ticketCode  Ʊ��
	 * @return
	 * return:List
	 * Date:2012-8-7
	 */
	public PaginationSupport findByNote(String sightseeingNo,String driverNo,String date,String ticketCode,int pageSize,int startIndex, String url);

	/**
	 * Describe: ���ݼ�Ʊ�б��ʶ����
	 * @auth:ChaoYuHang
	 * @param id ��Ʊ�б��ʶ
	 * @return
	 * return:Ticketchecklist
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
	 * @param ticketchecklists ��Ʊ�б����List
	 * return:void
	 * Date:2012-8-17
	 */
	public void saveAccount(List<Ticketchecklist> ticketchecklists);
}

