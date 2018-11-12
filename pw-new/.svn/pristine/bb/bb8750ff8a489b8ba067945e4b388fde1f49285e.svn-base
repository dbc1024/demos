package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.sales.Rwspersonalday;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IWarekcpersonService;

public class WarekcpersonService implements IWarekcpersonService{
	
	private IReportDataDAO reportdataDao;
	
	public IReportDataDAO getReportdataDao() {
		return reportdataDao;
	}

	public void setReportdataDao(IReportDataDAO reportdataDao) {
		this.reportdataDao = reportdataDao;
	}

	public int jobrun(String dates) {
		System.out.println("-------Ԥ��Ʊ/IC�������ֶ����п�ʼ:" + Tools.getDayTimes());
		try {
			
			//��ƱԱԤ��Ʊ��ͳ�Ʊ���
			List personlist=this.reportdataDao.showEmployeetab(dates,1L);
			if(personlist!=null&&personlist.size()>0){
				for(int x=0;x<personlist.size();x++){
					Object[] obj = (Object[]) personlist.get(x);
					Long empid=Long.parseLong(obj[0].toString());
					String szemployeename=obj[2].toString();
					Long itickettypeid=Long.parseLong(obj[4].toString());
					String sztickettypename=obj[5].toString();
					Long iamount=this.reportdataDao.showEmpidIamount(empid,itickettypeid, dates.replaceAll("-", ""),1L);
					
					Rwspersonalday rwsperson=new Rwspersonalday();
					Long seq = reportdataDao.getMaxPk("statid", "Rwspersonalday");
					rwsperson.setStatid(seq + 1);// ����
					rwsperson.setStatdate(Long.parseLong(dates.replaceAll("-", "")));
					rwsperson.setPersonalid(empid);
					rwsperson.setPersonalname(szemployeename);
					
					rwsperson.setItickettypeid(itickettypeid);
					rwsperson.setSztickettypename(sztickettypename);
					//��ƱԱ��Ʊ��������
					Long numinout=this.reportdataDao.showStockhouse(empid, itickettypeid, dates.replaceAll("-", ""), 1L);
					rwsperson.setNumin(iamount); //���ó�������
					rwsperson.setInt3(numinout);//��Ʊ�������
//					Long mumout=this.reportdataDao.showIompersonhouse(empid, itickettypeid, dates.replaceAll("-", ""),1L);
//					rwsperson.setNumout(mumout);
//					
//					Long endremain=this.reportdataDao.showRwspersonal(empid, itickettypeid, Tools.getDate(dates, -1).replaceAll("-", ""),1L);
//					rwsperson.setRecentremain(endremain);
//					
//					rwsperson.setEndremain(rwsperson.getRecentremain()+rwsperson.getNumin()-rwsperson.getNumout());
					
					//��һ�����ĩ����
					Long endremain=this.reportdataDao.showRwspersonal(empid, itickettypeid, Tools.getDate(dates, -1).replaceAll("-", ""),1L);
					rwsperson.setRecentremain(endremain);
					//��ƱԱ��Ʊ
					Long mumout=Long.parseLong(obj[3].toString());
					//this.reportdataDao.showIompersonhouse(empid, itickettypeid, dates,1L);
					//��Ʊ����
					Long tpmout=this.reportdataDao.checkUpnumsHouse(empid, itickettypeid, dates);
					rwsperson.setNumout(mumout); //�۳�����
					rwsperson.setInt1(tpmout);//��Ʊ����
	//				Long qmjymount=this.reportdataDao.searchPersonDetails(empid, itickettypeid, 1L);
					Long qmjymount=endremain+iamount+tpmout-mumout-numinout;
					//��һ�����ĩ����+���ó�������+��Ʊ����-�۳�����-��Ʊ�������
					rwsperson.setEndremain(qmjymount);//��ĩ��������
					rwsperson.setInt2(1L);  //1��Ԥ��Ʊ�� 2��IC��
					this.reportdataDao.save(rwsperson);
				
					
				}
			}
			
			
			//IC��  ��ƱԱԤ��Ʊ��ͳ�Ʊ���
			List kcpersonlist=this.reportdataDao.showEmployeetab(dates,2L);
			if(kcpersonlist!=null&&kcpersonlist.size()>0){
				for(int x=0;x<kcpersonlist.size();x++){
					Object[] obj = (Object[]) kcpersonlist.get(x);
					Long empid=Long.parseLong(obj[0].toString());
					String szemployeename=obj[2].toString();
					Long itickettypeid=Long.parseLong(obj[4].toString());
					String sztickettypename=obj[5].toString();
					
					Long iamount=this.reportdataDao.showEmpidIamount(empid,itickettypeid, dates.replaceAll("-", ""),2L);

					Rwspersonalday rwsperson=new Rwspersonalday();
					Long seq = reportdataDao.getMaxPk("statid", "Rwspersonalday");
					rwsperson.setStatid(seq + 1);// ����
					rwsperson.setStatdate(Long.parseLong(dates.replaceAll("-", "")));
					rwsperson.setPersonalid(empid);
					rwsperson.setPersonalname(szemployeename);
					
					rwsperson.setItickettypeid(itickettypeid);
					rwsperson.setSztickettypename(sztickettypename);
					//��ƱԱ��Ʊ��������
					Long numinout=this.reportdataDao.showStockhouse(empid, itickettypeid, dates.replaceAll("-", ""), 2L);
					rwsperson.setNumin(iamount); //���ó�������
					rwsperson.setInt3(numinout);//��Ʊ�������
//					Long mumout=this.reportdataDao.showIompersonhouse(empid, itickettypeid, dates.replaceAll("-", ""),2L);
//					rwsperson.setNumout(mumout);
//					
//					Long endremain=this.reportdataDao.showRwspersonal(empid, itickettypeid, Tools.getDate(dates, -1).replaceAll("-", ""),2L);
//					rwsperson.setRecentremain(endremain);
//					
//					rwsperson.setEndremain(rwsperson.getRecentremain()+rwsperson.getNumin()-rwsperson.getNumout());
					//��һ�����ĩ����
					Long endremain=this.reportdataDao.showRwspersonal(empid, itickettypeid, Tools.getDate(dates, -1).replaceAll("-", ""),2L);
					rwsperson.setRecentremain(endremain);
					//��ƱԱ��Ʊ
					Long mumout=Long.parseLong(obj[3].toString());
					//this.reportdataDao.showIompersonhouse(empid, itickettypeid, dates,2L);
					//��Ʊ����
					Long tpmout=this.reportdataDao.checkUpnumsHouse(empid, itickettypeid, dates);
					rwsperson.setNumout(mumout); //�۳�����
					rwsperson.setInt1(tpmout);//��Ʊ����
	//				Long qmjymount=this.reportdataDao.searchPersonDetails(empid, itickettypeid, 2L);
					Long qmjymount=endremain+iamount+tpmout-mumout-numinout;
					//��һ�����ĩ����+���ó�������+��Ʊ����-�۳�����-��Ʊ�������
					rwsperson.setEndremain(qmjymount);//��ĩ��������
					rwsperson.setInt2(2L);  //1��Ԥ��Ʊ�� 2��IC��
					this.reportdataDao.save(rwsperson);
				
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("Ԥ��Ʊ/IC�������쳣");
		}
		System.out.println("-------Ԥ��Ʊ/IC���ֶ�����ִ��" + Tools.getDayTimes());
		return 1;
	}

}

