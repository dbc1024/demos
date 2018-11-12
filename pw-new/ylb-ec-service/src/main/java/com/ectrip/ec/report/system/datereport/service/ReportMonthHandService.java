package com.ectrip.ec.report.system.datereport.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.datereport.Rcustomgrouptab;
import com.ectrip.ec.model.report.datereport.Rcustommonthtab;
import com.ectrip.ec.model.report.datereport.Rproviderfzmonthtab;
import com.ectrip.ec.model.report.datereport.Rproviderfznumbmonthtab;
import com.ectrip.ec.model.report.datereport.Rproviderlisttab;
import com.ectrip.ec.model.report.datereport.Rprovidermonthtab;
import com.ectrip.ec.model.report.datereport.Rpzprovidertab;
import com.ectrip.ec.model.report.datereport.Rregionmonthtab;
import com.ectrip.ec.model.report.datereport.Rticketmonthtab;
import com.ectrip.ec.model.report.sales.Rcgroupsalemonthtab;
import com.ectrip.ec.model.report.sales.Rsalecounttab;
import com.ectrip.ec.model.report.sales.Rsaletickettab;
import com.ectrip.ec.model.report.vouter.Rwswarehousemonth;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportMonthHandService;
import com.ectrip.ec.user.dao.idao.ICustomInfoDAO;

public class ReportMonthHandService implements IReportMonthHandService {

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
		System.out.println("�±����ֶ����п�ʼ:" + Tools.getDayTimes());
		
		try {
			List empfznumbList = reportdataDao.updateOrQueryempfznumbBymonth(dates);
			if (empfznumbList != null && empfznumbList.size() >= 1) {
				for (int i = 0; i < empfznumbList.size(); i++) {
					Object[] pro = (Object[]) empfznumbList.get(i);
					Rproviderfznumbmonthtab fzday = new Rproviderfznumbmonthtab();
					Long id = reportdataDao.getSequenceId("mfznumsequence");
					fzday.setId(id);
					fzday.setTimes(dates);
					fzday.setRdate(dates.substring(8, 10));// ��
					fzday.setRmonth(dates.substring(5, 7));// ��
					fzday.setRyear(dates.substring(0, 4));// ��;
					fzday.setBysalesvouchertype(pro[0].toString());
					fzday.setIscenicid(Long.parseLong(pro[1].toString()));// �����̱��
					fzday.setIbusinessid(new Long(pro[2].toString()));
					fzday.setIsettlementid(pro[3].toString());
					fzday.setIticketstationid(new Long(pro[4].toString()));
					fzday.setIemployeeid(new Long(pro[5].toString()));
					fzday.setItickettypeid(new Long(pro[6].toString()));
					fzday.setIticketnum(new Long(pro[7].toString()));
					fzday.setIticketplayer(new Long(pro[8].toString()));
					fzday.setIderatenums(new Long(pro[9].toString()));
					reportdataDao.save(fzday);

				}
			}

			List empfzList = reportdataDao.updateOrQueryempfzBymonth(dates);
			if (empfzList != null && empfzList.size() >= 1) {
				for (int i = 0; i < empfzList.size(); i++) {
					Object[] pro = (Object[]) empfzList.get(i);
					Rproviderfzmonthtab fzday = new Rproviderfzmonthtab();
					Long id = reportdataDao.getSequenceId("mfzsequence");
					fzday.setId(id);
					fzday.setTimes(dates);
					fzday.setRdate(dates.substring(8, 10));// ��
					fzday.setRmonth(dates.substring(5, 7));// ��
					fzday.setRyear(dates.substring(0, 4));// ��;
					fzday.setBysalesvouchertype(pro[0].toString());
					fzday.setIscenicid(Long.parseLong(pro[1].toString()));// �����̱��
					fzday.setIbusinessid(new Long(pro[2].toString()));
					fzday.setIsettlementid(pro[3].toString());
					fzday.setIticketstationid(new Long(pro[4].toString()));
					fzday.setIemployeeid(new Long(pro[5].toString()));
					fzday.setItickettypeid(new Long(pro[6].toString()));
					fzday.setIztickettypeid(new Long(pro[7].toString()));
					fzday.setIcrowdkindid(new Long(pro[8].toString()));
					fzday.setMsplitprice(new Double(pro[9].toString()));
					fzday.setIticketplayer(new Long(pro[10].toString()));
					fzday.setMsplitmoney(new Double(pro[11].toString()));
					fzday.setIderatenums(new Long(pro[12].toString()));
					fzday.setMderatemoney(new Double(pro[13].toString()));
					fzday.setMhandcharge(new Double(pro[14].toString()));
					reportdataDao.save(fzday);

				}
			}
			// �������±���Rprovidermonthtab��
			List zproList = reportdataDao.updateOrQueryZProviderByDate("2",
					dates);
			if (zproList != null && zproList.size() >= 1) {
				for (int i = 0; i < zproList.size(); i++) {
					Object[] pro = (Object[]) zproList.get(i);
					Rpzprovidertab proday = new Rpzprovidertab();
					Long seq = reportdataDao.getMaxPk("seq", "Rpzprovidertab");
					proday.setSeq(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("02");
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
			List proList = reportdataDao.updateOrQueryProviderByType("2", dates);

			if (proList != null && proList.size() >= 1) {
				for (int i = 0; i < proList.size(); i++) {
					Object[] pro = (Object[]) proList.get(i);

					Rprovidermonthtab proday = new Rprovidermonthtab();
					Long seq = reportdataDao.getMaxPk("id", "Rprovidermonthtab");
					proday.setId(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRmonth(dates.substring(5, 7));// ��
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
					proday.setDuf(Double.parseDouble(pro[9].toString()));
					proday.setIsf(Long.parseLong(pro[10].toString()));
					System.out.println("===========.>>>>proday duf:"+pro[9].toString());
					reportdataDao.save(proday);
					
				}
			}

			// ������������ϸ����RProviderlisttab��
			List providerList = reportdataDao.updateOrQueryProviderListByType("2",
					dates);

			if (proList != null && providerList.size() >= 1) {
				for (int i = 0; i < providerList.size(); i++) {
					Object[] pro = (Object[]) providerList.get(i);

					Rproviderlisttab proday = new Rproviderlisttab();
					Long seq = reportdataDao.getMaxPk("seq", "Rproviderlisttab");
					proday.setSeq(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("02");// ʱ������
					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					proday.setSzscenicname(pro[1].toString());// ����������
					proday.setItickettypeid(Long.parseLong(pro[2].toString()));// ��Ʒ���
					proday.setTtypename(pro[3].toString());// ��Ʒ����
					proday.setIcrowdkindid(Long.parseLong(pro[4].toString()));// ��Ⱥ������
					proday.setSzcrowdkindname(pro[5].toString());// ��Ⱥ��������
					proday.setPric(Double.parseDouble(pro[6].toString()));// ����					
					proday.setProtype(pro[7].toString());// �տʽ
					proday.setProtypename(pro[8].toString());// �տʽ����
					proday.setNumb(Long.parseLong(pro[9].toString()));// ����
					proday.setMont(Double.parseDouble(pro[10].toString()));// ���
					proday.setDua(Double.parseDouble(pro[11].toString()));//�˶����
					proday.setDub(Double.parseDouble(pro[12].toString()));//������
					proday.setIsa(Long.parseLong(pro[13].toString()));//�˶�����
					proday.setNotea(pro[14].toString());
					proday.setNoteb(pro[15].toString());
					proday.setDuf(Double.parseDouble(pro[16].toString()));
					proday.setIsf(Long.parseLong(pro[17].toString()));
					reportdataDao.save(proday);
					
				}
			}

			// �ο���Դ�±���(Rregionmonthtab)
			List sourceList = reportdataDao.updateOrQueryRegionByType("2", dates);
			if (sourceList != null && sourceList.size() >= 1) {
				for (int i = 0; i < sourceList.size(); i++) {
					Object[] pro = (Object[]) sourceList.get(i);

					Rregionmonthtab regday = new Rregionmonthtab();
					Long seq = reportdataDao.getMaxPk("id", "Rregionmonthtab");
					regday.setId(seq + 1);// ����
					regday.setTimes(dates);// ����
					regday.setRmonth(dates.substring(5, 7));// ��
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

			// �ο������±���(Rcustommonthtab)
			List customCountList = reportdataDao.updateOrQueryCustomCountByType("2",
					dates);
			if (customCountList != null && customCountList.size() >= 1) {
				for (int j = 0; j < customCountList.size(); j++) {
					Object[] pro = (Object[]) customCountList.get(j);

					Rcustommonthtab cusday = new Rcustommonthtab();
					Long seq = reportdataDao.getMaxPk("id", "Rcustommonthtab");
					cusday.setId(seq + 1);
					cusday.setTimes(dates);// ����
					cusday.setRmonth(dates.substring(5, 7));// ��
					cusday.setRyear(dates.substring(0, 4));// ��
					cusday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					cusday.setSzscenicname(pro[1].toString());// ����������
					cusday.setNumb(Long.parseLong(pro[2].toString()));// ����

					// �������
					this.reportdataDao.save(cusday);
					
				}

			}
			// ��ƱԱ��Ʊ�±���(Rsalecounttab)
			List saleCountList = reportdataDao.updateOrQuerySaleCountByType("2", "01",
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
					salecount.setTitype("02");// ��������
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
					salecount.setDub(0d);//������
					salecount.setIsb(Long.parseLong(pro[15].toString()));
					salecount.setNotec(pro[16].toString());
					salecount.setNotea("01");// ��Ʊ
				
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
			
		// ��ƱԱ��Ʊ�±���(Rsalecounttab)
			List plList = reportdataDao.updateOrQuerySaleCountByType("2", "04",
					dates);
			if (plList != null && plList.size() >= 1) {
				for (int i = 0; i < plList.size(); i++) {
					Object[] pro = (Object[]) plList.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = reportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("02");// ��������
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
					salecount.setDub(0d);//������
					salecount.setIsb(Long.parseLong(pro[15].toString()));
					salecount.setNotec(pro[16].toString());
					salecount.setNotea("04");// ��Ʊ
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

			// ��ƱԱ��Ʊ(Rsalecounttab)
			List TuiDingsaleCountList = reportdataDao.updateOrQuerySaleCountByType("2",
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
					salecount.setTitype("02");// ��������
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
					salecount.setNotea("02");// ��Ʊ
					salecount.setDub(Double.parseDouble(pro[14].toString()));//������
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
			// �������Ʊ����
						List lxssaleList = reportdataDao.updateOrQueryLxsSaleByDate("2", dates);
						if (lxssaleList != null && lxssaleList.size() >= 1) {
							for (int i = 0; i < lxssaleList.size(); i++) {
								Map map = (Map) lxssaleList.get(i);
								Rcustomgrouptab customgroup = new Rcustomgrouptab();
								Long seq = reportdataDao.getMaxPk("seq", "Rcustomgrouptab");
								customgroup.setSeq(seq + (i + 1));// ����
								customgroup.setTimes(dates);// ����
								customgroup.setRdate(dates.substring(8, 10));// ��
								customgroup.setRmonth(dates.substring(5, 7));// ��
								customgroup.setRyear(dates.substring(0, 4));// ��
								customgroup.setTitype("02");// ��������
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
			List saleticket = reportdataDao.updateOrquerySalebyType("2", dates);
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
					sale.setTitype("02");//��������
					
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
			
			//Ʊ�����ۣ�Rticketmonttab��
			List ticketsalelist = reportdataDao.updateOrQueryTicketSaleByType("2", dates); 
			if(ticketsalelist!=null && ticketsalelist.size()>=1){
				for (int i = 0; i < ticketsalelist.size(); i++) {
					Object[] obj = (Object[])ticketsalelist.get(i);
					
					Rticketmonthtab tisale = new Rticketmonthtab();
					Long seq = reportdataDao.getMaxPk("id", "Rticketmonthtab");
					tisale.setId(seq+1);//����
					
					tisale.setTimes(dates);
					tisale.setRmonth(dates.substring(5, 7));// ��
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
			
			//ǰ̨���������ۻ����±�Rcgroupsalemonthtab)
			List tuantisalecountlist = reportdataDao.updateOrQueryCusGroupSaleByType("2", dates);
			if(tuantisalecountlist!=null && tuantisalecountlist.size()>=1){
				for (int i = 0; i < tuantisalecountlist.size(); i++) {
					Object[] obj = (Object[])tuantisalecountlist.get(i);			
					Rcgroupsalemonthtab group = new Rcgroupsalemonthtab();
					Long seq = reportdataDao.getMaxPk("seq", "Rcgroupsalemonthtab");
					group.setSeq(seq+1);//����
					
					group.setTimes(dates);
					group.setRmonth(dates.substring(5, 7));// ��
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
			
			
			//�����ͳ���±���
			String today=dates.substring(0,7).replaceAll("-", "");
			System.out.println("=======today:"+today);
			List rwslist=this.reportdataDao.checkRwswarehouse(today, 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
			if(rwslist!=null&&rwslist.size()>0){
				for (int x=0;x<rwslist.size();x++){
					Rwswarehousemonth rws=(Rwswarehousemonth) rwslist.get(x);
					this.reportdataDao.delete(rws);
				}
			}
			List houseList=this.reportdataDao.showAllHouseware(today, 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
			if(houseList!=null&&houseList.size()>0){
				for(int i=0;i<houseList.size();i++){

					Object[] obj = (Object[]) houseList.get(i);
					
					//ĳ�ֿ���������
					List houselist=this.reportdataDao.showIamoutcheckin(Long.parseLong(obj[0].toString()), today, 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
					//ĳ�ֿ�ĳ�������
					List warelise=this.reportdataDao.showIamoutcheckOut(Long.parseLong(obj[0].toString()), today, 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
					if((houselist!=null&&houselist.size()>0)||(warelise!=null&&warelise.size()>0)){
						if(houselist!=null&&houselist.size()>0){
							for (int x=0;x<houselist.size();x++){
								Rwswarehousemonth rwsware=new Rwswarehousemonth();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehousemonth");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(today));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1].toString()));
								rwsware.setNumin(Long.parseLong(ject[3].toString()));
								Long st=Long.parseLong(today)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(ject[1].toString()),st.toString(), 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
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
								Rwswarehousemonth rwsware=new Rwswarehousemonth();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehousemonth");
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
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(object[1].toString()),st.toString(), 2L,1L);//2����ͳ�ƣ� 1��Ԥ��Ʊ
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
			
			
			//IC��  �����ͳ���±���
			String kctoday=dates.substring(0,7).replaceAll("-", "");
			List kcrwslist=this.reportdataDao.checkRwswarehouse(kctoday, 2L,2L);//2:��ͳ�ƣ� 2��IC��
			if(kcrwslist!=null&&kcrwslist.size()>0){
				for (int x=0;x<kcrwslist.size();x++){
					Rwswarehousemonth rws=(Rwswarehousemonth) kcrwslist.get(x);
					this.reportdataDao.delete(rws);
				}
			}
			List kchouseList=this.reportdataDao.showAllHouseware(kctoday, 2L,2L);//2:��ͳ�ƣ� 2��IC��
			if(kchouseList!=null&&kchouseList.size()>0){
				for(int i=0;i<kchouseList.size();i++){

					Object[] obj = (Object[]) kchouseList.get(i);
					
					//ĳ�ֿ���������
					List houselist=this.reportdataDao.showIamoutcheckin(Long.parseLong(obj[0].toString()), kctoday, 2L,2L);//2:��ͳ�ƣ� 2��IC��
					//ĳ�ֿ�ĳ�������
					List warelise=this.reportdataDao.showIamoutcheckOut(Long.parseLong(obj[0].toString()), kctoday, 2L,2L);//2:��ͳ�ƣ� 2��IC��
					if((houselist!=null&&houselist.size()>0)||(warelise!=null&&warelise.size()>0)){
						if(houselist!=null&&houselist.size()>0){
							for (int x=0;x<houselist.size();x++){
								Rwswarehousemonth rwsware=new Rwswarehousemonth();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehousemonth");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(kctoday));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0].toString()));
								
								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1].toString()));
								rwsware.setNumin(Long.parseLong(ject[3].toString()));
								Long st=Long.parseLong(kctoday)-1L;
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(ject[1].toString()),st.toString(), 2L,2L);//2:��ͳ�ƣ� 2��IC��
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
								Rwswarehousemonth rwsware=new Rwswarehousemonth();
								Long seq = reportdataDao.getMaxPk("statid", "Rwswarehousemonth");
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
								Long recenter=this.reportdataDao.checkRecenterout(Long.parseLong(obj[0].toString()),Long.parseLong(object[1].toString()),st.toString(), 2L,2L);//2:��ͳ�ƣ� 2��IC��
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
			throw new RuntimeException("�±����ֶ� ���г����쳣");
		}

		System.out.println("�±�������ֶ�ִ��" + Tools.getDayTimes());

		return 1;
	}

}

