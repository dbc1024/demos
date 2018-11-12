/*package com.ectrip.ec.report.system.ticketsale.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketCheckupDAO;
import com.ectrip.ec.report.system.ticketsale.service.iservice.ITicketCheckupService;
import com.ectrip.ticket.model.order.Ticketchecklist;
import com.runqian.base.module.DataSetConfig;
import com.runqian.base.util.SQLParser;
import com.runqian.report.usermodel.ReportDataSetConfigs;
import com.runqian.report.usermodel.ReportDefine;

*//**
 * ��Ʊ��ѯҵ���߼���
 * @author ChaoYuHang
 *//*
public class TicketCheckupService implements ITicketCheckupService{

	private ITicketCheckupDAO ticketCheckupDao;  //����ѯ����DAO�ӿ�
	
	public void setTicketCheckupDao(ITicketCheckupDAO ticketCheckupDao) {
		this.ticketCheckupDao = ticketCheckupDao;
	}

	*//***
	 * Describe: ���ݴ��š�ͨ��֤�š�Ʊ�� ���Խ��м�Ʊ��ѯ
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#findByProperties(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param chuanhao ����
	 * @param tongxingzhi ͨ��֤
	 * @param date ����
	 * @param ticketCode Ʊ��
	 * @return ���������ϵ�List
	 * @author ChaoYuHang
	 * Date:2012-8-1
	 *//*
	public PaginationSupport findByProperties(String chuanhao, String tongxingzhi,
			String date, String ticketCode,int pageSize,int startIndex, String url) {
		return ticketCheckupDao.findByProperties(chuanhao, tongxingzhi, date,ticketCode,pageSize,startIndex,url);
	}

	*//***
	 * Describe: ���ݹ۹⳵�š�˾�����š�Ʊ�����Խ��м�Ʊ��ѯ
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#findByNote(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param sightseeingNo �۹⳵��
	 * @param driverNo ˾������
	 * @param date ����
	 * @param ticketCode Ʊ��
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-7
	 *//*
	public PaginationSupport findByNote(String sightseeingNo,String driverNo,String date,String ticketCode,int pageSize,int startIndex, String url){
		return ticketCheckupDao.findByNote(sightseeingNo, driverNo,date,ticketCode,pageSize,startIndex,url);
	}
	
	*//***
	 * Describe: ���ݴ��ź�ͨ��֤�����ɱ���
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#reportTicketByShipno(com.ectrip.model.order.Ticketchecklist, java.lang.String, java.lang.String, java.lang.String)
	 * @param ticketchecklist ��Ʊ��ϸ��Ϣ����
	 * @param date ����
	 * @param user �û���
	 * @param corpname ���Ʊ���λ��
	 * @return
	 * @throws Exception
	 * @author ChaoYuHang
	 * Date:2012-8-8
	 *//*
	public ReportDefine reportTicketByShipno(Ticketchecklist ticketchecklist,String date,String user,String corpname)throws Exception{
		//=======��Ǭ�����ʼ��
		ReportDefine rd = null;
		StringBuffer condition=new StringBuffer();
		StringBuffer where = new StringBuffer();
		rd = new ReportDefine(WebContant.REALPATH+ "/reports/system/ticketcheck/lijiang_report.raq");
		where.append(" 1=1");
		
		ReportDataSetConfigs dscs  = rd.getDataSetConfigs();
		DataSetConfig dsc = dscs.get("ds1");
		String sql = dsc.getSQL();
		
		//========����ʹ�ò�ͬ��������ѯ���ɲ�ͬ��sql
		if ((ticketchecklist.getChuanhao()==null&&ticketchecklist.getTongxingzhi()==null)||ticketchecklist==null) {
			where.append(" and( chuanhao is not null or tongxingzhi is not null)");
			condition.append("���еĴ��ź�ͨ��֤��");
		}
		//���ݴ���
		if (ticketchecklist!=null&&!"".equals(ticketchecklist.getChuanhao())) {
			where.append(" and chuanhao = '"+ticketchecklist.getChuanhao()+"'");
			condition.append("����: "+ticketchecklist.getChuanhao());
		}
		//����ͨ��֤��
		if (ticketchecklist!=null&&!"".equals(ticketchecklist.getTongxingzhi())) {
			where.append(" and tongxingzhi= '"+ticketchecklist.getTongxingzhi()+"'");
			condition.append(" ͨ��֤�ţ�"+ticketchecklist.getTongxingzhi());
		}
		//������ڲ�Ϊ�գ����Ż���ͨ��֤�Ų�Ϊ��
		if (date!=null&&ticketchecklist!=null&&(!ticketchecklist.getChuanhao().equals("")||!ticketchecklist.getTongxingzhi().equals(""))) {
			where.append(" and tc.DTMAKEDATE like '"+date+"%'");
			condition.append(" ���ڣ�"+date);
		}
		//������ڲ�Ϊ�գ����ź�ͨ��֤Ϊ��
		if (date!=null&&(ticketchecklist==null||(ticketchecklist.getChuanhao().equals("")&&ticketchecklist.getTongxingzhi().equals("")))) {
			where.append(" and tc.DTMAKEDATE like '"+date+"%'");
			where.append(" and( chuanhao is not null or tongxingzhi is not null)");
			condition.append("���еĴ��ź�ͨ��֤��");
			condition.append(" ���ڣ�"+date);
		}
		
		//ִ�в�ѯ
		sql = SQLParser.modify(sql, SQLParser.KEY_WHERE, where.toString());
		dsc.setSQL(sql);
		
		//==========��������
		rd.setMacro("company", "", corpname);
		rd.setMacro("user", "", user);
		rd.setMacro("condition", "", condition.toString());
		
		return rd;
	}
	
	*//***
	 * Describe: ���ݹ۹⳵�ź�˾���������ɱ���
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#reportTicketByNote(com.ectrip.model.order.Ticketchecklist, java.lang.String, java.lang.String, java.lang.String)
	 * @param ticketchecklist ��Ʊ��ϸ��Ϣ����
	 * @param date ����
	 * @param user �û���
	 * @param corpname ���Ʊ���λ��
	 * @return
	 * @throws Exception
	 * @author ChaoYuHang
	 * Date:2012-8-8
	 *//*
	public ReportDefine reportTicketByNote(Ticketchecklist ticketchecklist,String date,String user,String corpname) throws Exception{
		//=======��Ǭ�����ʼ��
		ReportDefine rd = null;
		StringBuffer condition=new StringBuffer();
		StringBuffer where = new StringBuffer();
		rd = new ReportDefine(WebContant.REALPATH+ "/reports/system/ticketcheck/lijiang_report_no.raq");
		where.append(" 1=1");
		
		ReportDataSetConfigs dscs  = rd.getDataSetConfigs();
		DataSetConfig dsc = dscs.get("ds1");
		String sql = dsc.getSQL();
		
		//========����ʹ�ò�ͬ��������ѯ���ɲ�ͬ��sql
		if (ticketchecklist==null||(ticketchecklist.getNote1()==null&&ticketchecklist.getNote2()==null)) {
			where.append(" and (NOTE1 is not null OR NOTE2 is NOT null)");
			condition.append("���еĹ۹⳵�ź�˾������");
		}
		//���ݹ۹⳵��
		if (ticketchecklist!=null&&!ticketchecklist.getNote1().equals("")) {
			condition.append("�۹⳵�ţ�"+ticketchecklist.getNote1());
			where.append(" and tc.note1= '"+ticketchecklist.getNote1()+"'");
		}
		//����˾������
		if (ticketchecklist!=null&&!ticketchecklist.getNote2().equals("")) {
			condition.append("˾�����ţ�"+ticketchecklist.getNote1());
			where.append(" and tc.note2= '"+ticketchecklist.getNote2()+"'");
		}
		//������ڲ�Ϊ�գ��۹⳵�Ż���˾�����Ų�Ϊ��
		if (date!=null&&ticketchecklist!=null&&(!ticketchecklist.getNote1().equals("")||!ticketchecklist.getNote2().equals(""))) {
			condition.append(" ���ڣ�"+date);
			where.append(" and tc.DTMAKEDATE like '"+date+"%'");
		}
		//������ڲ�Ϊ�գ��۹⳵�ź�˾������Ϊ��
		if (date!=null&&(ticketchecklist==null||(ticketchecklist.getNote1().equals("")&&ticketchecklist.getNote2().equals("")))) {
			condition.append("���еĹ۹⳵�ź�˾������");
			condition.append(" ���ڣ�"+date);
			where.append(" and tc.DTMAKEDATE like '"+date+"%'");
			where.append(" and (NOTE1 is not null OR NOTE2 is NOT null)");
		}
		
		//ִ�в�ѯ
		sql = SQLParser.modify(sql, SQLParser.KEY_WHERE, where.toString());
		dsc.setSQL(sql);
		
		//==========��������
		rd.setMacro("company", "", corpname);
		rd.setMacro("user", "", user);
		rd.setMacro("condition", "", condition.toString());
		return rd;
	}
	
	*//***
	 * Describe: ���ݼ�Ʊ��ʶ����
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#findById(java.lang.Long)
	 * @param id ��Ʊ��ʶ
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 *//*
	public Ticketchecklist findById(Long id){		
		return ticketCheckupDao.findById(id);
	}
	
	*//***
	 * Describe: ���ݴ��źͼ�Ʊ�������Ͳ���
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#findByChuanHao(java.lang.String, int)
	 * @param shipNo ����
	 * @param type  ��Ʊ��������
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 *//*
	public List findByChuanHao(String shipNo,int type){
		return ticketCheckupDao.findByChuanHao(shipNo, type);
	}
	
	*//***
	 * Describe: �����Ʊ����
	 * @see com.ectrip.project.lijiang.service.iservice.ITicketCheckService#saveAccount(java.lang.String[], java.lang.String)
	 * @param checkId ��Ʊ��ʶ
	 * @param empId   ��ǰ�û�ID
	 * @author ChaoYuHang
	 * Date:2012-8-17
	 *//*
	public void saveAccount(String[] checkId,String empId){
		List<Ticketchecklist> ticketchecklists_save=new ArrayList<Ticketchecklist>(); //Ҫ����ļ�Ʊ����List
		for (int i = 0; i < checkId.length; i++) {
			Ticketchecklist ticketchecklist_tmp=this.findById(Long.valueOf(checkId[i]));  //�ҵ�Ҫ�޸ĵļ�Ʊ����
			if (ticketchecklist_tmp.getInt1()==1) {   //����Ѿ���Ʊ�����ˣ�������
				continue;
			}
			ticketchecklist_tmp.setInt1(1L);          //����Ϊ�ѽ���
			ticketchecklist_tmp.setAccounttime(Tools.getDayTimes());
			ticketchecklist_tmp.setEmpid(empId);
			ticketchecklists_save.add(ticketchecklist_tmp);
		}
		ticketCheckupDao.saveAccount(ticketchecklists_save);
	}
}

*/