package com.ectrip.ec.report.system.datereport.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.datereport.Rcustomgrouptab;
import com.ectrip.ec.model.report.datereport.Rcustomyeartab;
import com.ectrip.ec.model.report.datereport.Rproviderlisttab;
import com.ectrip.ec.model.report.datereport.Rprovideryeartab;
import com.ectrip.ec.model.report.datereport.Rpzprovidertab;
import com.ectrip.ec.model.report.datereport.Rregionyeartab;
import com.ectrip.ec.model.report.datereport.Rticketyeartab;
import com.ectrip.ec.model.report.sales.Rcgroupsaleyeartab;
import com.ectrip.ec.model.report.sales.Rsalecounttab;
import com.ectrip.ec.model.report.sales.Rsaletickettab;
import com.ectrip.ec.model.report.vouter.Rwswarehouseyear;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportYearHandService;
import com.ectrip.ec.user.dao.idao.ICustomInfoDAO;

public class ReportYearHandService implements IReportYearHandService {
	private IReportDataDAO reportdataDao;
	private ICustomInfoDAO custominfoDao;

	public IReportDataDAO getReportdataDao() {
		return reportdataDao;
	}

	public void setReportdataDao(IReportDataDAO reportdataDao) {
		this.reportdataDao = reportdataDao;
	}
    
	public ICustomInfoDAO getCustominfoDao() {
		return custominfoDao;
	}

	public void setCustominfoDao(ICustomInfoDAO custominfoDao) {
		this.custominfoDao = custominfoDao;
	}

	
	public int jobrun(String dates) {
		System.out.println("�걨���ֶ����п�ʼ:" + dates+" �ֶ�ʱ��"+Tools.getDayTimes());
		
		try {
			List zproList = reportdataDao.updateOrQueryZProviderByDate("3",
					dates);
			if (zproList != null && zproList.size() >= 1) {
				for (int i = 0; i < zproList.size(); i++) {
					Object[] pro = (Object[]) zproList.get(i);
					Rpzprovidertab proday = new Rpzprovidertab();
				
					Long seq = this.reportdataDao.getMaxPk("seq", "Rpzprovidertab");
					
					proday.setSeq(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("03");
					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��					
					proday.setBysalesvouchertype(pro[1].toString());					
					proday.setIbusinessid(new Long(pro[2].toString()));
					proday.setIsettlementid(pro[3].toString());
					
					proday.setNumb(new Long(pro[4].toString()));
					proday.setYhnumb(new Long(pro[5].toString()));
					proday.setMont(new Double(pro[6].toString()));
					proday.setYhmont(new Double(pro[7].toString()));
					proday.setMhandcharge(new Double(pro[8].toString()));
					proday.setDtmakedate(Tools.getDayTimes());
					
					reportdataDao.save(proday);
				
				}
			}
			// �������걨��Rprovideryeartab��
			List proList = reportdataDao.updateOrQueryProviderByType("3", dates);
			if (proList != null && proList.size() >= 1) {
				for (int i = 0; i < proList.size(); i++) {
					Object[] pro = (Object[]) proList.get(i);

					Rprovideryeartab proday = new Rprovideryeartab();
					Long seq = reportdataDao.getMaxPk("id", "Rprovideryeartab");
					proday.setId(seq + (i+1));// ����
					proday.setTimes(dates);
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					proday.setSzscenicname(pro[1].toString());// ����������
					proday.setMont(Double.parseDouble(pro[2].toString()));// �ܽ��
					proday.setZfmont(Double.parseDouble(pro[3].toString()));// ֧�����
					proday.setYhmont(Double.parseDouble(pro[4].toString()));// �Żݽ��
					proday.setNotea(pro[5].toString());//�տʽ
					proday.setNoteb(pro[6].toString());//�տʽ����
					proday.setNotec(pro[7].toString());//���۷�ʽ
					proday.setNoted(pro[8].toString());//���۷�ʽ����
					proday.setDuf(Double.parseDouble(pro[9].toString()));//�Żݽ��
					proday.setIsf(Long.parseLong(pro[10].toString()));
					reportdataDao.save(proday);
					
				}
			}

			// ������������ϸ����RProviderlisttab��
			List providerList = reportdataDao.updateOrQueryProviderListByType("3",
					dates);
			if (proList != null && providerList.size() >= 1) {
				for (int i = 0; i < providerList.size(); i++) {
					Object[] pro = (Object[]) providerList.get(i);

					Rproviderlisttab proday = new Rproviderlisttab();
					Long seq = reportdataDao.getMaxPk("seq", "Rproviderlisttab");
					proday.setSeq(seq + (i+1));// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("03");// ʱ������
					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					proday.setSzscenicname(pro[1].toString());// ����������
					proday.setItickettypeid(Long.parseLong(pro[2].toString()));// ��Ʒ���
					proday.setTtypename(pro[3].toString());// ��Ʒ����
					proday.setIcrowdkindid(Long.parseLong(pro[4].toString()));// ��Ⱥ������
					proday.setSzcrowdkindname(pro[5].toString());// ��Ⱥ��������
					proday.setPric(Double.parseDouble(pro[6].toString()));// ����
					proday.setProtype(pro[7].toString());//  �տʽ
					proday.setProtypename(pro[8].toString());// �տʽ����
					proday.setNumb(Long.parseLong(pro[9].toString()));// ����
					proday.setMont(Double.parseDouble(pro[10].toString()));// ���
					proday.setDua(Double.parseDouble(pro[11].toString()));//�˶����
					proday.setDub(Double.parseDouble(pro[12].toString()));//������
					proday.setIsa(Long.parseLong(pro[13].toString()));//�˶�����
					proday.setNotea(pro[14].toString());
					proday.setNoteb(pro[15].toString());
					proday.setDuf(Double.parseDouble(pro[16].toString()));
					reportdataDao.save(proday);
					
				}
			}

			// �ο���Դ�걨��(Rregionyeartab)
			List sourceList = reportdataDao.updateOrQueryRegionByType("3", dates);
			if (sourceList != null && sourceList.size() >= 1) {
				for (int i = 0; i < sourceList.size(); i++) {
					Object[] pro = (Object[]) sourceList.get(i);

					Rregionyeartab regday = new Rregionyeartab();
					Long seq = reportdataDao.getMaxPk("id", "Rregionyeartab");
					regday.setId(seq + (i+1));// ����
					regday.setTimes(dates);// ����
					regday.setRyear(dates.substring(0, 4));// ��
					regday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					regday.setSzscenicname(pro[1].toString());// ����������
					regday.setIregionalid(Long.parseLong(pro[2].toString()));// ��Դ�ر��
					regday.setSzregionalname(pro[3].toString());// ��Դ������
					regday.setNumb(Long.parseLong(pro[4].toString()));// ����
					// �������
					this.reportdataDao.save(regday);
					
				}
			}

			// �ο������걨��(Rcustomyeartab)
			List customCountList = reportdataDao.updateOrQueryCustomCountByType("3",
					dates);
			if (customCountList != null && customCountList.size() >= 1) {
				for (int j = 0; j < customCountList.size(); j++) {
					Object[] pro = (Object[]) customCountList.get(j);

					Rcustomyeartab cusday = new Rcustomyeartab();
					Long seq = reportdataDao.getMaxPk("id", "Rcustomyeartab");
					cusday.setId(seq + (j+1));
					cusday.setTimes(dates);// ����
					cusday.setRyear(dates.substring(0, 4));// ��
					cusday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					cusday.setSzscenicname(pro[1].toString());// ����������
					cusday.setNumb(Long.parseLong(pro[2].toString()));// ����

					// �������
					this.reportdataDao.save(cusday);
					
				}

			}
			// ��ƱԱ��Ʊ(Rsalecounttab)
			List saleCountList = reportdataDao.updateOrQuerySaleCountByType("3", "01",
					dates);
			if (saleCountList != null && saleCountList.size() >= 1) {
				for (int i = 0; i < saleCountList.size(); i++) {
					Object[] pro = (Object[]) saleCountList.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = reportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("03");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����
					salecount.setSkfs(pro[2].toString());// �տʽ
					salecount.setSkfsname(pro[3].toString());// ֧����ʽ����

					salecount.setEmpid(pro[4].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[5].toString());// ��ƱԱ����
					salecount.setItickettypeid(Long
							.parseLong(pro[6].toString()));// ��Ʒ���
					salecount.setSztickettypename(pro[7].toString());// ��Ʒ����
					salecount.setTickettype(pro[8].toString());// ��Ʒ����
					salecount.setNumb(Long.parseLong(pro[9].toString()));// ����
					salecount.setMont(Double.parseDouble(pro[10].toString()));// ���
					salecount.setIsa(Long.parseLong(pro[11].toString()));//��Ⱥ������
					salecount.setNoteb(pro[12].toString());//��Ⱥ��������
					salecount.setDua(Double.parseDouble(pro[13].toString()));//����
					salecount.setDub(0d);
					salecount.setIsb(Long.parseLong(pro[15].toString()));//վ��
					salecount.setNotec(pro[16].toString());//վ������
					
					salecount.setNotea("01");// ��Ʊ
					salecount.setIsc(Long.parseLong(pro[17].toString()));//��Ʊ����
					salecount.setIsd(Long.parseLong(pro[18].toString()));//ҵ�����
					
					if(pro[22]!=null&&!pro[22].equals("")){
						salecount.setNotef(pro[22].toString());  //�ͻ�ID
					}
					if(pro[20]!=null&&!pro[20].equals("")){
						salecount.setIsf(Long.parseLong(pro[20].toString()));//��Դ��ID
					}
					if(pro[21]!=null&&!pro[21].equals("")){
						salecount.setNotee(pro[21].toString());//��Դ������
					}
					if(pro[19]!=null&&!pro[19].equals("")){
						salecount.setIse(Long.parseLong(pro[19].toString()));//�˴�ID
					}
					salecount.setDue(Double.parseDouble(pro[23].toString()));//�Ż�����
					salecount.setDuf(Double.parseDouble(pro[24].toString()));//�Żݽ��
					// �������
					this.reportdataDao.save(salecount);
					
				}
			}
		// ��ƱԱ��Ʊ(Rsalecounttab)
			List PlList = reportdataDao.updateOrQuerySaleCountByType("3", "04",
					dates);
			if (PlList != null && PlList.size() >= 1) {
				for (int i = 0; i < PlList.size(); i++) {
					Object[] pro = (Object[]) PlList.get(i);
					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = reportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("03");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����
					salecount.setSkfs(pro[2].toString());// �տʽ
					salecount.setSkfsname(pro[3].toString());// ֧����ʽ����

					salecount.setEmpid(pro[4].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[5].toString());// ��ƱԱ����
					salecount.setItickettypeid(Long
							.parseLong(pro[6].toString()));// ��Ʒ���
					salecount.setSztickettypename(pro[7].toString());// ��Ʒ����
					salecount.setTickettype(pro[8].toString());// ��Ʒ����
					salecount.setNumb(Long.parseLong(pro[9].toString()));// ����
					salecount.setMont(Double.parseDouble(pro[10].toString()));// ���
					salecount.setIsa(Long.parseLong(pro[11].toString()));//��Ⱥ������
					salecount.setNoteb(pro[12].toString());//��Ⱥ��������
					salecount.setDua(Double.parseDouble(pro[13].toString()));//����
					salecount.setDub(0d);
					salecount.setIsb(Long.parseLong(pro[15].toString()));//վ��
					salecount.setNotec(pro[16].toString());//վ������
					
					salecount.setNotea("04");// ����
					salecount.setIsc(Long.parseLong(pro[17].toString()));//��Ʊ����
					salecount.setIsd(Long.parseLong(pro[18].toString()));//ҵ�����
					
					if(pro[22]!=null&&!pro[22].equals("")){
						salecount.setNotef(pro[22].toString());  //�ͻ�ID
					}
					if(pro[20]!=null&&!pro[20].equals("")){
						salecount.setIsf(Long.parseLong(pro[20].toString()));//��Դ��ID
					}
					if(pro[21]!=null&&!pro[21].equals("")){
						salecount.setNotee(pro[21].toString());//��Դ������
					}
					if(pro[19]!=null&&!pro[19].equals("")){
						salecount.setIse(Long.parseLong(pro[19].toString()));//�˴�ID
					}
					salecount.setDue(Double.parseDouble(pro[23].toString()));//�Ż�����
					salecount.setDuf(Double.parseDouble(pro[24].toString()));//�Żݽ��
					// �������
					this.reportdataDao.save(salecount);
					
				}
			}

			// ��ƱԱ��Ʊ(Rsalecounttab)
			List TuiDingsaleCountList = reportdataDao.updateOrQuerySaleCountByType("3",
					"02", dates);
			if (TuiDingsaleCountList != null
					&& TuiDingsaleCountList.size() >= 1) {
				for (int i = 0; i < TuiDingsaleCountList.size(); i++) {
					Object[] pro = (Object[]) TuiDingsaleCountList.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = reportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("03");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����
					salecount.setSkfs(pro[2].toString());// �տʽ
					salecount.setSkfsname(pro[3].toString());// ֧����ʽ����

					salecount.setEmpid(pro[4].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[5].toString());// ��ƱԱ����
					salecount.setItickettypeid(Long
							.parseLong(pro[6].toString()));// ��Ʒ���
					salecount.setSztickettypename(pro[7].toString());// ��Ʒ����
					salecount.setTickettype(pro[8].toString());// ��Ʒ����
					salecount.setNumb(Long.parseLong(pro[9].toString()));// ����
					salecount.setMont(Double.parseDouble(pro[10].toString()));// ���
					salecount.setNotea("02");// ��Ʊ
					salecount.setIsa(Long.parseLong(pro[11].toString()));//��Ⱥ������
					salecount.setNoteb(pro[12].toString());//��Ⱥ��������
					salecount.setDua(Double.parseDouble(pro[13].toString()));//����
					salecount.setDub(Double.parseDouble(pro[14].toString()));
					salecount.setIsb(Long.parseLong(pro[15].toString()));//վ��
					salecount.setNotec(pro[16].toString());//վ������
					// �������
					salecount.setIsc(Long.parseLong(pro[17].toString()));//��Ʊ����
					salecount.setIsd(Long.parseLong(pro[18].toString()));//ҵ�����
					
					if(pro[22]!=null&&!pro[22].equals("")){
						salecount.setNotef(pro[22].toString());  //�ͻ�ID
					}
					if(pro[20]!=null&&!pro[20].equals("")){
						salecount.setIsf(Long.parseLong(pro[20].toString()));//��Դ��ID
					}
					if(pro[21]!=null&&!pro[21].equals("")){
						salecount.setNotee(pro[21].toString());//��Դ������
					}
					if(pro[19]!=null&&!pro[19].equals("")){
						salecount.setIse(Long.parseLong(pro[19].toString()));//�˴�ID
					}
					salecount.setDue(Double.parseDouble(pro[23].toString()));//�Ż�����
					salecount.setDuf(Double.parseDouble(pro[24].toString()));//�Żݽ��
					this.reportdataDao.save(salecount);
					
				}
			}

			// �������Ʊ����
			List lxssaleList = reportdataDao.updateOrQueryLxsSaleByDate("3", dates);
			if (lxssaleList != null && lxssaleList.size() >= 1) {
				for (int i = 0; i < lxssaleList.size(); i++) {
					Map map = (Map) lxssaleList.get(i);
					Rcustomgrouptab customgroup = new Rcustomgrouptab();
					Long seq = reportdataDao.getMaxPk("seq", "Rcustomgrouptab");
					customgroup.setSeq(seq + 1);// ����
					customgroup.setTimes(dates);// ����
					customgroup.setRdate(dates.substring(8, 10));// ��
					customgroup.setRmonth(dates.substring(5, 7));// ��
					customgroup.setRyear(dates.substring(0, 4));// ��
					customgroup.setTitype("03");// ��������
					customgroup.setTickettype(map.get("tickettype").toString());// Ʊ����
					customgroup.setTtypename(map.get("ttypename").toString());// Ʊ��������
					customgroup.setChiefuser(map.get("chiefuser").toString());// ����
					customgroup.setSonuser(map.get("sonuser").toString());// ����
					customgroup.setGrandsonuser(map.get("grandsonuser").toString());// ����Ա
					customgroup.setNumb(Long.parseLong(map.get("numb").toString()));// Ʊ��
					customgroup.setMont(Double.parseDouble(map.get("mont").toString()));// �ܽ��
					customgroup.setIsf(Long.parseLong(map.get("isf").toString()));
					customgroup.setDuf(Double.parseDouble(map.get("duf").toString()));
					customgroup.setIsf(Long.parseLong(map.get("isf").toString()));// �Ż�����
					customgroup.setIsb(Long.parseLong(map.get("isb").toString()));//��Ʒ���
					customgroup.setIsc(Long.parseLong(map.get("isc").toString()));//������
					customgroup.setIsd(Long.parseLong(map.get("isd").toString()));//��Ⱥ���
					customgroup.setDub(Double.parseDouble(map.get("dub").toString()));//��Ʒ����
					customgroup.setNotea(map.get("notea").toString());//��������˶�
					customgroup.setNoteb(map.get("noteb").toString());//֧������
					reportdataDao.save(customgroup);
				}
			}

			//��Ʊ����Rsaletickettab
			List saleticket = reportdataDao.updateOrquerySalebyType("3", dates);
			if(saleticket!=null && saleticket.size()>=1){
				for (int i = 0; i < saleticket.size(); i++) {
					Object[] pro = (Object[])saleticket.get(i);
					Rsaletickettab sale = new Rsaletickettab();
					
					//��ȡ����
					Long seq = reportdataDao.getMaxPk("seq", "Rsaletickettab");
					sale.setSeq(seq+1);
					sale.setRdate(dates.substring(8, 10));// ��
					sale.setRmonth(dates.substring(5, 7));// ��
					sale.setRyear(dates.substring(0, 4));// ��
					sale.setTimes(dates);//����
					sale.setTitype("03");//��������
					
					Long iscneicid = Long.parseLong(pro[0].toString());//������
					sale.setIscenicid(iscneicid);
					sale.setSzscenicname(pro[1].toString());//����������
					Long itickettypeid = Long.parseLong(pro[2].toString());//��Ʒ���	
					sale.setItickettypeid(itickettypeid);
					sale.setSztickettypename(pro[3].toString());//��Ʒ����
					sale.setNumb(Long.parseLong(pro[4].toString()));//����
					sale.setMont(Double.parseDouble(pro[5].toString()));//��� 
					
					sale.setIsa(Long.parseLong(pro[6].toString()));//��Ⱥ������
					sale.setNotea(pro[7].toString());//��Ⱥ��������
					sale.setDua(Double.parseDouble(pro[8].toString()));//����	
					sale.setIsb(Long.parseLong(pro[9].toString()));//վ����
					sale.setNoteb(pro[10].toString());//վ������
					sale.setIsf(Long.parseLong(pro[11].toString()));
					sale.setDuf(Double.parseDouble(pro[12].toString()));
					reportdataDao.save(sale);		
					
					
				}
			}
			
			//Ʊ�����ۣ�Rticketyeartab��
			List ticketsalelist = reportdataDao.updateOrQueryTicketSaleByType("3", dates); 
			if(ticketsalelist!=null && ticketsalelist.size()>=1){
				for (int i = 0; i < ticketsalelist.size(); i++) {
					Object[] obj = (Object[])ticketsalelist.get(i);
					
					Rticketyeartab tisale = new Rticketyeartab();
					Long seq = reportdataDao.getMaxPk("id", "Rticketyeartab");
					tisale.setId(seq+1);//����
					
					tisale.setTimes(dates);
					tisale.setRyear(dates.substring(0, 4));// ��
					
					tisale.setItickettypeid(Long.parseLong(obj[0].toString()));//��Ʒid
					tisale.setSztickettypename(obj[1].toString());//��Ʒ����
					tisale.setTickettype(obj[2].toString());//��Ʒ����	
					tisale.setIsa(Long.parseLong(obj[3].toString()));
					tisale.setNotea(obj[4].toString());//ҵ�����
					tisale.setNumb(Long.parseLong(obj[5].toString()));//����
					tisale.setMont(Double.parseDouble(obj[6].toString()));//�ܽ��
					
					tisale.setIsb(Long.parseLong(obj[7].toString()));//��Ⱥ������
					tisale.setNoteb(obj[8].toString());//��Ⱥ��������
					tisale.setDua(Double.parseDouble(obj[9].toString()));//����	
					if(obj[10]!=null){
						tisale.setNotec(obj[10].toString());
						}
					tisale.setIsc(new Long(obj[11].toString()));
					tisale.setIsd(new Long(obj[12].toString()));
					tisale.setIsf(Long.parseLong(obj[13].toString()));
					tisale.setDuf(Double.parseDouble(obj[14].toString()));
					reportdataDao.save(tisale);
					
				}
			}
			//ǰ̨���������ۻ������Rcgroupsaleyeartab)
			List tuantisalecountlist = reportdataDao.updateOrQueryCusGroupSaleByType("3", dates);
			if(tuantisalecountlist!=null && tuantisalecountlist.size()>=1){
				for (int i = 0; i < tuantisalecountlist.size(); i++) {
					Object[] obj = (Object[])tuantisalecountlist.get(i);
					
					Rcgroupsaleyeartab group = new Rcgroupsaleyeartab();
					Long seq = reportdataDao.getMaxPk("seq", "Rcgroupsaleyeartab");
					group.setSeq(seq+(i+1));//����
					
					group.setTimes(dates);
					group.setRyear(dates.substring(0, 4));// ��
					
					group.setItickettypeid(Long.parseLong(obj[0].toString()));//��Ʒid
					group.setSztickettypename(obj[1].toString());//��Ʒ����
					group.setUsid(obj[2].toString());//�û���
					if(obj[3]!=null && !"".equals(obj[3])){
						group.setCorpname(obj[3].toString());//��˾����
					}
					group.setNumb(Long.parseLong(obj[4].toString()));//����
					group.setMont(Double.parseDouble(obj[5].toString()));//�ܽ��
					
					group.setIsa(Long.parseLong(obj[6].toString()));//��Ⱥ������
					group.setNotea(obj[7].toString());//��Ⱥ��������
					group.setDua(Double.parseDouble(obj[8].toString()));//����				
					group.setNoteb(obj[9].toString());
					group.setIsb(new Long(obj[10].toString()));//ҵ��ID
					group.setIsc(new Long(obj[11].toString()));//�۸�ID
					group.setIsd(new Long(obj[12].toString()));//�۸�ID
					group.setIsf(Long.parseLong(obj[13].toString()));//�Ż�����
					group.setDuf(Double.parseDouble(obj[14].toString()));//�Żݽ��
					group.setNotec(obj[15].toString());
					group.setDue(Double.parseDouble(obj[16].toString()));//������
					reportdataDao.save(group);
					
				}
			}

			//�����ͳ���걨��
			String today=dates.substring(0,4).replaceAll("-", "");
			System.out.println("==��=====today:"+today);
			List rwslist=this.reportdataDao.checkRwswarehouse(today, 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
			if(rwslist!=null&&rwslist.size()>0){
				for (int x=0;x<rwslist.size();x++){
					Rwswarehouseyear rws=(Rwswarehouseyear) rwslist.get(x);
					this.reportdataDao.delete(rws);
				}
			}
			List houseList=this.reportdataDao.showAllHouseware(today, 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
			if(houseList!=null&&houseList.size()>0){
				for(int i=0;i<houseList.size();i++){

					Object[] obj = (Object[]) houseList.get(i);
					
					//ĳ�ֿ���������
					List houselist=this.reportdataDao.showIamoutcheckin(Long.parseLong(obj[0].toString()), today, 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
					//ĳ�ֿ�ĳ�������
					List warelise=this.reportdataDao.showIamoutcheckOut(Long.parseLong(obj[0].toString()), today, 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
					if((houselist!=null&&houselist.size()>0)||(warelise!=null&&warelise.size()>0)){
						if(houselist!=null&&houselist.size()>0){
							for (int x=0;x<houselist.size();x++){
								Rwswarehouseyear rwsware=new Rwswarehouseyear();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehouseyear");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(today));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1].toString()));
								rwsware.setNumin(Long.parseLong(ject[3].toString()));
								Long st=Long.parseLong(today)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(ject[1].toString()),st.toString(), 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
								if(recenter!=null){
									rwsware.setRecentremain(recenter);
								}else{
									rwsware.setRecentremain(0L);
								}
								
								if(warelise!=null&&warelise.size()>0){
									for(int y=0;y<warelise.size();y++){
										Object[] object = (Object[]) warelise.get(y);
										if(ject[1].toString().equals(object[1].toString())){
											rwsware.setNumout(Long.parseLong(object[0].toString()));
										}else{
											if(y==warelise.size()-1){
												if(rwsware.getNumout()==null){
													rwsware.setNumout(0l);
												}
											}
										}
									}
								}else{
									rwsware.setNumout(0L);
								}
								
								rwsware.setEndremain(rwsware.getRecentremain()+rwsware.getNumin()-rwsware.getNumout());
								rwsware.setInt1(1L);  //1��Ԥ��Ʊ�� 2��IC��
								reportdataDao.save(rwsware);
							}
					}else{
						if(warelise!=null&&warelise.size()>0){
							for(int y=0;y<warelise.size();y++){
								Rwswarehouseyear rwsware=new Rwswarehouseyear();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehouseyear");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(today));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] object = (Object[]) warelise.get(y);
								rwsware.setNumout(Long.parseLong(object[0].toString()));
								rwsware.setSztickettypename(object[2].toString());
								rwsware.setItickettypeid(Long.parseLong(object[1].toString()));
								
								rwsware.setNumin(0L);
								Long st=Long.parseLong(today)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(object[1].toString()),st.toString(), 3L,1L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
								if(recenter!=null){
									rwsware.setRecentremain(recenter);
								}else{
									rwsware.setRecentremain(0L);
								}
								
								rwsware.setEndremain(rwsware.getRecentremain()+rwsware.getNumin()-rwsware.getNumout());
								rwsware.setInt1(1L);  //1��Ԥ��Ʊ�� 2��IC��
								reportdataDao.save(rwsware);
							}
						}
					}
					
				}else{
					continue;
				}
				}
			}
			
			
			//IC�� �����ͳ���걨��
			String kctoday=dates.substring(0,4).replaceAll("-", "");
			List kcrwslist=this.reportdataDao.checkRwswarehouse(kctoday, 3L,2L);//3����ͳ�ƣ�  2��IC��
			if(kcrwslist!=null&&kcrwslist.size()>0){
				for (int x=0;x<kcrwslist.size();x++){
					Rwswarehouseyear rws=(Rwswarehouseyear) kcrwslist.get(x);
					this.reportdataDao.delete(rws);
				}
			}
			List kchouseList=this.reportdataDao.showAllHouseware(kctoday, 3L,2L);//3����ͳ�ƣ�  2��IC��
			if(kchouseList!=null&&kchouseList.size()>0){
				for(int i=0;i<kchouseList.size();i++){

					Object[] obj = (Object[]) kchouseList.get(i);
					
					//ĳ�ֿ���������
					List houselist=this.reportdataDao.showIamoutcheckin(Long.parseLong(obj[0].toString()), kctoday, 3L,2L);//3����ͳ�ƣ�  2��IC��
					//ĳ�ֿ�ĳ�������
					List warelise=this.reportdataDao.showIamoutcheckOut(Long.parseLong(obj[0].toString()), kctoday, 3L,2L);//3����ͳ�ƣ�  2��IC��
					if((houselist!=null&&houselist.size()>0)||(warelise!=null&&warelise.size()>0)){
						if(houselist!=null&&houselist.size()>0){
							for (int x=0;x<houselist.size();x++){
								Rwswarehouseyear rwsware=new Rwswarehouseyear();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehouseyear");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(kctoday));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1].toString()));
								rwsware.setNumin(Long.parseLong(ject[3].toString()));
								Long st=Long.parseLong(kctoday)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(ject[1].toString()),st.toString(), 3L,2L);//3����ͳ�ƣ�  2��IC��
								if(recenter!=null){
									rwsware.setRecentremain(recenter);
								}else{
									rwsware.setRecentremain(0L);
								}
								
								if(warelise!=null&&warelise.size()>0){
									for(int y=0;y<warelise.size();y++){
										Object[] object = (Object[]) warelise.get(y);
										if(ject[1].toString().equals(object[1].toString())){
											rwsware.setNumout(Long.parseLong(object[0].toString()));
										}else{
											if(y==warelise.size()-1){
												if(rwsware.getNumout()==null){
													rwsware.setNumout(0l);
												}
											}
										}
									}
								}else{
									rwsware.setNumout(0L);
								}
								
								rwsware.setEndremain(rwsware.getRecentremain()+rwsware.getNumin()-rwsware.getNumout());
								rwsware.setInt1(2L);  //1��Ԥ��Ʊ�� 2��IC��
								reportdataDao.save(rwsware);
							}
					}else{
						if(warelise!=null&&warelise.size()>0){
							for(int y=0;y<warelise.size();y++){
								Rwswarehouseyear rwsware=new Rwswarehouseyear();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehouseyear");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(kctoday));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] object = (Object[]) warelise.get(y);
								rwsware.setNumout(Long.parseLong(object[0].toString()));
								rwsware.setSztickettypename(object[2].toString());
								rwsware.setItickettypeid(Long.parseLong(object[1].toString()));
								
								rwsware.setNumin(0L);
								Long st=Long.parseLong(kctoday)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(object[1].toString()),st.toString(), 3L,2L);//3����ͳ�ƣ� 1��Ԥ��Ʊ
								if(recenter!=null){
									rwsware.setRecentremain(recenter);
								}else{
									rwsware.setRecentremain(0L);
								}
								
								rwsware.setEndremain(rwsware.getRecentremain()+rwsware.getNumin()-rwsware.getNumout());
								rwsware.setInt1(2L);  //1��Ԥ��Ʊ�� 2��IC��
								reportdataDao.save(rwsware);
							}
						}
					}
					
				}else{
					continue;
				}
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("�걨���ֶ����г����쳣");
		}

		System.out.println("�걨������ֶ�ִ��" + Tools.getDayTimes());

		return 1;
	}

}

