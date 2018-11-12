package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportsListDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportListHandService;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportListNoService;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

public class ReportListHandService implements IReportListHandService {

	private IReportsListDAO reportslistDao;
	private IGenericDao genericDao;
	private IReportListNoService reportlistnoService;
	
	public IReportsListDAO getReportslistDao() {
		return reportslistDao;
	}
	public void setReportslistDao(IReportsListDAO reportslistDao) {
		this.reportslistDao = reportslistDao;
	}
	public IGenericDao getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}
	
	
	public IReportListNoService getReportlistnoService() {
		return reportlistnoService;
	}
	public void setReportlistnoService(IReportListNoService reportlistnoService) {
		this.reportlistnoService = reportlistnoService;
	}
	public int jobrun(String dates) {
		
		System.out.println("��ʷ����ʼִ��"+Tools.getDayTimes());
		
		try{
			//��ȡ����
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd("01");
			sysid.setPmky("DAYS");
			Sysparv5 sysparv5 = (Sysparv5)genericDao.get(Sysparv5.class, sysid);
			int datenumb=0;
			if(sysparv5!=null){
				datenumb = Integer.parseInt(sysparv5.getPmva());
			}else{
				datenumb = 10;
			}		
			String strdate = Tools.getDate(dates, -datenumb);
			
			//���ݷ�ʽ����ÿ����Ʊƾ֤ID���ȱ�����Ʊƾ֤��ʷ��  Stssalesvouchertablist,
			//ƾ֤������ʷ��  Stssalessettlementtablist��ƾ֤��ϸ��ʷ��Stssalesvoucherdetailstablist��
			//ƾ֤��ϸ�۸������ʷ��   Stscomticketsalesdetailstabls�������۳���Ʊ����ʷ��Stssoldtickettablist��
			//�۳���Ʊ�ӱ���ʷ��Stssoldticketsubtablist
			
			
			//ɾ����ʽ����ɾ����Ʊ��¼Ticketchecklist����ӡ��¼Ticketprintlist,���۳���Ʊ�����֤�ӱ�Stssoldticketattesttab����
			//Ȼ��ɾ��Stssoldticketsubtab��Stssoldtickettab��Stscomticketsalesdetailstab��Stssalesvoucherdetailstab��
			//Stssalessettlementtab��Stssalesvouchertab
			
			//�õ�Ҫ���ݵ�����
			List list = reportslistDao.queryReportList(strdate);
			System.out.println("list size"+list.size());
			if(list!=null && list.size()>=1){
				for (int i = 0; i < list.size(); i++) {
					Long isalesvoucherid = (Long)list.get(i);
					System.out.println("===============>>>saleid:"+isalesvoucherid);
					reportlistnoService.jobrun(isalesvoucherid);				
				
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��ʷ������쳣");
		}
		
		System.out.println("��ʷ�������ִ��"+Tools.getDayTimes());
		
		return 1;
	}

}

