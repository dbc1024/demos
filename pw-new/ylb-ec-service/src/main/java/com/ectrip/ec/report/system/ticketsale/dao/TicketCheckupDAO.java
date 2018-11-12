package com.ectrip.ec.report.system.ticketsale.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketCheckupDAO;
import com.ectrip.ticket.model.order.Ticketchecklist;

public class TicketCheckupDAO extends GenericDao implements ITicketCheckupDAO{

	/***
	 * Describe: ���ݴ��š�ͨ��֤�š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @see com.ectrip.project.lijiang.dao.idao.ITicketCheckDAO#findByProperties(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param chuanhao ����
	 * @param tongxingzhi ͨ��֤
	 * @param date ����
	 * @param ticketCode Ʊ��
	 * @return  ���������ϵ�List
	 * @author ChaoYuHang
	 * Date:2012-8-1
	 */
	public PaginationSupport findByProperties(String chuanhao, String tongxingzhi,String date, String ticketCode,int pageSize,int startIndex, String url) {
		//��ʼ��sql��䣬Ĭ�����������ϲ�ѯ
		String temp="select new map(ticket.chuanhao as chuanhao,ticket.tongxingzhi as tongxingzhi,ticket.dtmakedate as dtmakedate,tickettype.sztickettypename as sztickettypename,soldticket.szticketprintno as szticketprintno) from Ticketchecklist ticket ,Edmtickettypetab tickettype ,Stssoldtickettab soldticket";
		StringBuffer hql=new StringBuffer(temp);
		hql.append(" where ticket.szsoldticketid = soldticket.id.szsoldticketid  and ticket.isalesvoucherdetailsid = soldticket.id.isalesvoucherdetailsid  and ticket.isalesvoucherid = soldticket.id.isalesvoucherid  and soldticket.itickettypeid = tickettype.itickettypeid");
		
		//���ݴ��Ų�ѯ
		if (chuanhao!=null&&!"".equals(chuanhao)) {
			hql.append(" and ticket.chuanhao like '%"+chuanhao+"%'");
		}
		//����ͨ��֤�Ų�ѯ
		if (tongxingzhi!=null&&!"".equals(tongxingzhi)) {
			hql.append(" and ticket.tongxingzhi like '%"+tongxingzhi+"%'");
		}
		//�������ڲ�ѯ
		if (date!=null&&!"".equals(date)) {
			hql.append(" and ticket.dtmakedate like '"+date+"%'");
		}
		//����Ʊ�Ų�ѯ
		if (ticketCode!=null&&!"".equals(ticketCode)) {
			hql.append(" and soldticket.szticketprintno like '%"+ticketCode+"%'");
		}
		return this.findPage(hql.toString(), pageSize, startIndex, url);
	}

	/***
	 * Describe:���ݹ۹⳵�š�˾�����š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @see com.ectrip.project.lijiang.dao.idao.ITicketCheckDAO#findByNote(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param sightseeingNo �۹⳵��
	 * @param driverNo ˾������
	 * @param date ����
	 * @param ticketCode Ʊ��
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-7
	 */
	public PaginationSupport findByNote(String sightseeingNo, String driverNo,String date,String ticketCode,int pageSize,int startIndex, String url) {
		//��ʼ��sql�Z���ԃ��Ĭ�ϲ�ѯ
		String temp="select new map( ticket.note1 as note1,ticket.note2 as note2,ticket.dtmakedate as dtmakedate,tickettype.sztickettypename as sztickettypename,soldticket.szticketprintno as szticketprintno) from Ticketchecklist ticket ,Edmtickettypetab tickettype ,Stssoldtickettab soldticket";
		StringBuffer hql=new StringBuffer(temp);
		hql.append(" where ticket.szsoldticketid = soldticket.id.szsoldticketid  and ticket.isalesvoucherdetailsid = soldticket.id.isalesvoucherdetailsid  and ticket.isalesvoucherid = soldticket.id.isalesvoucherid  and soldticket.itickettypeid = tickettype.itickettypeid");
		
		//���ݹ۹⳵�Ų�ѯ
		if (sightseeingNo!=null&&!"".equals(sightseeingNo)) {
			hql.append(" and ticket.note1 like '%"+sightseeingNo+"%'");
			return  this.findPage(hql.toString(), pageSize, startIndex, url);
		}
		
		//����˾�����Ų�ѯ
		if (driverNo!=null&&!"".equals(driverNo)) {
			hql.append(" and ticket.note2 like '%"+driverNo+"%'");
			return  this.findPage(hql.toString(), pageSize, startIndex, url);
		}		
		//�������ڲ�ѯ
		if (date!=null&&!"".equals(date)) {
			hql.append(" and ticket.dtmakedate like '"+date+"%'");
		}
		//����Ʊ�Ų�ѯ
		if (ticketCode!=null&&!"".equals(ticketCode)) {
			hql.append(" and soldticket.szticketprintno like '%"+ticketCode+"%'");
		}
		return  this.findPage(hql.toString(), pageSize, startIndex, url);
	}
	
	/***
	 * Describe: ���ݼ�Ʊ�б��ʶ����
	 * @see com.ectrip.project.lijiang.dao.idao.ITicketCheckDAO#findById(java.lang.Long)
	 * @param id ��Ʊ�б��ʶ
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 */
	public Ticketchecklist findById(Long id){
		return (Ticketchecklist) this.get(Ticketchecklist.class, id);
	}
	
	/***
	 * Describe: ���ݴ��źͼ�Ʊ�������Ͳ���
	 * @see com.ectrip.project.lijiang.dao.idao.ITicketCheckDAO#findByChuanHao(java.lang.String, int)
	 * @param shipNo ����
	 * @param type   ��Ʊ��������
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 */
	public List findByChuanHao(String shipNo,int type){
		StringBuffer hql=new StringBuffer();
		//hqlĬ�ϲ�ѯ���
		hql.append("select new map(ticketchecklist.checkid as checkid,stssoldtickettab.szticketprintno as szticketprintno," +
					"ticketchecklist.int1 as isaccount,ticketchecklist.accounttime as accounttime) ");
		hql.append(" from Ticketchecklist ticketchecklist,Stssoldtickettab stssoldtickettab");
		hql.append(" where stssoldtickettab.id.szsoldticketid=ticketchecklist.szsoldticketid " +
					" and stssoldtickettab.id.isalesvoucherdetailsid=ticketchecklist.isalesvoucherdetailsid");
		hql.append(" and stssoldtickettab.id.isalesvoucherid=ticketchecklist.isalesvoucherid");
		//���ݴ��Ų�ѯ
		if (shipNo!=null&&!"".equals(shipNo)) {
			hql.append(" and ticketchecklist.chuanhao='"+shipNo+"'");
		}
		//����Ʊ�������Ͳ�ѯ
		if (type==0||type==1) {
			hql.append(" and ticketchecklist.int1="+type);
		}
		//�����ָ����������ʱ�����н��
		if (type==2) {
			hql.append(" order by ticketchecklist.int1 desc ");
		}
		return this.find(hql.toString());
	}
	
	/***
	 * Describe: �����Ʊ����
	 * @see com.ectrip.project.lijiang.dao.idao.ITicketCheckDAO#saveAccount(java.util.List)
	 * @param ticketchecklists  ��Ʊ�б����List
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 */
	public void saveAccount(List<Ticketchecklist> ticketchecklists){
		for (Ticketchecklist ticketchecklist : ticketchecklists) {
			this.update(ticketchecklist);
		}
	}
}

