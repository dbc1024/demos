package com.ectrip.ec.report.system.datereport.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.datereport.Rcustomdaytab;
import com.ectrip.ec.model.report.datereport.Rcustomgrouptab;
import com.ectrip.ec.model.report.datereport.Rproviderdaytab;
import com.ectrip.ec.model.report.datereport.Rproviderfzdaytab;
import com.ectrip.ec.model.report.datereport.Rproviderfznumbdaytab;
import com.ectrip.ec.model.report.datereport.Rproviderlisttab;
import com.ectrip.ec.model.report.datereport.Rpzprovidertab;
import com.ectrip.ec.model.report.datereport.Rregiondaytab;
import com.ectrip.ec.model.report.datereport.Rticketdaytab;
import com.ectrip.ec.model.report.sales.Rcgroupsaledaytab;
import com.ectrip.ec.model.report.sales.Rsalecounttab;
import com.ectrip.ec.model.report.sales.Rsaletickettab;
import com.ectrip.ec.model.report.vouter.Rwswarehouseday;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.report.system.datereport.dao.idao.IHisReportDataDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportDateHandService;
import com.ectrip.ec.user.dao.idao.ICustomInfoDAO;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;

public class ReportDataHandService implements IReportDateHandService {

	private IHisReportDataDAO hisreportdataDao;
	private ICustomInfoDAO custominfoDao;

	public IHisReportDataDAO getHisreportdataDao() {
		return hisreportdataDao;
	}

	public void setHisreportdataDao(IHisReportDataDAO hisreportdataDao) {
		this.hisreportdataDao = hisreportdataDao;
	}

	public ICustomInfoDAO getCustominfoDao() {
		return custominfoDao;
	}

	public void setCustominfoDao(ICustomInfoDAO custominfoDao) {
		this.custominfoDao = custominfoDao;
	}

	public int jobrun(String dates) {

		System.out.println("�ձ����ֶ���ʼִ��" + Tools.getDayTimes());

		try {

			List empfznumbList = hisreportdataDao
					.updateOrQueryempfznumbByDate(dates);
			if (empfznumbList != null && empfznumbList.size() >= 1) {
				for (int i = 0; i < empfznumbList.size(); i++) {
					Object[] pro = (Object[]) empfznumbList.get(i);
					Rproviderfznumbdaytab fzday = new Rproviderfznumbdaytab();
					Long id = hisreportdataDao.getSequenceId("fznumsequence");
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
					hisreportdataDao.save(fzday);

				}
			}

			List empfzList = hisreportdataDao.updateOrQueryempfzByDate(dates);
			if (empfzList != null && empfzList.size() >= 1) {
				for (int i = 0; i < empfzList.size(); i++) {
					Object[] pro = (Object[]) empfzList.get(i);
					Rproviderfzdaytab fzday = new Rproviderfzdaytab();
					Long id = hisreportdataDao.getSequenceId("fzsequence");
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
					if(pro[11]==null||pro[11].equals("")){
						pro[11]="0";
					}
					fzday.setMsplitmoney(new Double(pro[11].toString()));
					fzday.setIderatenums(new Long(pro[12].toString()));
					fzday.setMderatemoney(new Double(pro[13].toString()));
					fzday.setMhandcharge(new Double(pro[14].toString()));
					hisreportdataDao.save(fzday);

				}
			}
			List zproList = hisreportdataDao.updateOrQueryZProviderByDate("1",
					dates);

			if (zproList != null && zproList.size() >= 1) {
				for (int i = 0; i < zproList.size(); i++) {

					Object[] pro = (Object[]) zproList.get(i);
					Rpzprovidertab proday = new Rpzprovidertab();
					Long seq = hisreportdataDao.getMaxPk("seq", "Rpzprovidertab");

					proday.setSeq(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("01");

					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��

					if (pro[1].toString().equals("04")) {
						proday.setBysalesvouchertype("01");
					} else {
						proday.setBysalesvouchertype(pro[1].toString());
					}

					proday.setIbusinessid(new Long(pro[2].toString()));

					proday.setIsettlementid(pro[3].toString());

					proday.setNumb(new Long(pro[4].toString()));

					proday.setYhnumb(new Long(pro[5].toString()));

					proday.setMont(new Double(pro[6].toString()));

					proday.setYhmont(new Double(pro[7].toString()));

					if (pro[8] == null || pro[8].equals("")) {
						proday.setMhandcharge(0D);
					} else {
						proday.setMhandcharge(new Double(pro[8].toString()));
					}

					proday.setDtmakedate(Tools.getDayTimes());
					hisreportdataDao.save(proday);

				}
			}
			// �������ձ���Rproviderdaytab��

			List proList = hisreportdataDao
					.updateOrQueryProviderByDate("1", dates);
			if (proList != null && proList.size() >= 1) {
				for (int i = 0; i < proList.size(); i++) {
					Object[] pro = (Object[]) proList.get(i);
					Rproviderdaytab proday = new Rproviderdaytab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rproviderdaytab");
					proday.setId(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					proday.setSzscenicname(pro[1].toString());// ����������

					if (!"99".equals(pro[5].toString())) {
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmky("PZLX");
						sysid.setPmcd(pro[6].toString());// ���۷�ʽ
						Sysparv5 sys = (Sysparv5) hisreportdataDao.get(
								Sysparv5.class, sysid);
						String sysname = "";
						if (sys != null) {
							sysname = sys.getPmva();
						}
						proday.setNoted(sysname);
						proday.setNotec(pro[6].toString());
						Sysparv5Id sysid1 = new Sysparv5Id();
						sysid1.setPmky("ZFFS");
						sysid1.setPmcd(pro[5].toString());// ���۷�ʽ
						Sysparv5 sys1 = (Sysparv5) hisreportdataDao.get(
								Sysparv5.class, sysid1);
						String sysname1 = "";
						if (sys1 != null) {
							sysname1 = sys1.getPmva();
						}
						proday.setNotea(pro[5].toString());
						proday.setNoteb(sysname1);
						if ("02".equals(pro[6].toString())) {// �˶�
							proday.setMont(Double.parseDouble(pro[3].toString()));// ������
																					// �����Żݵ�
							proday.setZfmont(Double.parseDouble(pro[3]
									.toString())
									- Double.parseDouble(pro[7].toString()));// ʵ�ʷ����Ľ��
																				// �������Ż�
							proday.setYhmont(Double.parseDouble(pro[4]
									.toString()));// ������
							proday.setDuf(Double.parseDouble(pro[7].toString()));// �Żݽ��
							proday.setIsf(Long.parseLong(pro[8].toString()));
						} else {
							proday.setMont(Double.parseDouble(pro[2].toString()));// ������
							proday.setZfmont(Double.parseDouble(pro[3]
									.toString())
									- Double.parseDouble(pro[7].toString()));// ֧�����
							proday.setYhmont(0d);// ������
							proday.setDuf(Double.parseDouble(pro[7].toString()));// �Żݽ��
							proday.setIsf(Long.parseLong(pro[8].toString()));
						}
					}

					if ("99".equals(pro[5].toString())) {
						proday.setNotea("01");
						proday.setNoteb("����֧��");
						proday.setMont(0d);// ������
						proday.setZfmont(0d);// ֧�����
						if (pro[4] == null || "".equals(pro[4])) {
							proday.setYhmont(0d);
						} else {
							proday.setYhmont(Double.parseDouble(pro[4]
									.toString()));// ������
						}

						proday.setNotec("07");
						proday.setNoted("δ��Ʊ�˶�");

					}

					hisreportdataDao.save(proday);

				}
			}
			// ������������ϸ����RProviderlisttab��
			List providerList = hisreportdataDao.updateOrQueryProviderList("1",
					dates);
			if (proList != null && providerList.size() >= 1) {
				for (int i = 0; i < providerList.size(); i++) {
					Object[] pro = (Object[]) providerList.get(i);

					Rproviderlisttab proday = new Rproviderlisttab();
					Long seq = hisreportdataDao
							.getMaxPk("seq", "Rproviderlisttab");
					proday.setSeq(seq + 1);// ����
					proday.setTimes(dates);
					proday.setRdate(dates.substring(8, 10));// ��
					proday.setRmonth(dates.substring(5, 7));// ��
					proday.setRyear(dates.substring(0, 4));// ��
					proday.setTitype("01");// ʱ������

					proday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					proday.setSzscenicname(pro[1].toString());// ����������
					proday.setItickettypeid(Long.parseLong(pro[2].toString()));// ��Ʒ���

					Edmtickettypetab ticket = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class,
									Long.parseLong(pro[2].toString()));
					String ticketname = "";
					if (ticket != null) {
						ticketname = ticket.getSztickettypename();// ��Ʒ����
					}
					proday.setTtypename(ticketname);// ��Ʒ����

					proday.setIcrowdkindid(Long.parseLong(pro[3].toString()));// ��Ⱥ������
					Edpcrowdkindtab kind = (Edpcrowdkindtab) hisreportdataDao.get(
							Edpcrowdkindtab.class,
							Long.parseLong(pro[3].toString()));
					String szkindname = "";
					if (kind != null) {
						szkindname = kind.getSzcrowdkindname();// ��Ⱥ��������
					}
					proday.setSzcrowdkindname(szkindname);// /��Ⱥ��������
					proday.setPric(Double.parseDouble(pro[4].toString()));// ����

					if (!"99".equals(pro[9].toString())) {
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmky("PZLX");
						sysid.setPmcd(pro[11].toString());
						Sysparv5 sys = (Sysparv5) hisreportdataDao.get(
								Sysparv5.class, sysid);
						String sysname = "";
						if (sys != null) {
							sysname = sys.getPmva();
						}
						proday.setNoteb(sysname);
						proday.setNotea(pro[11].toString());
						Sysparv5Id sysid1 = new Sysparv5Id();
						sysid1.setPmky("ZFFS");
						sysid1.setPmcd(pro[9].toString());
						Sysparv5 sys1 = (Sysparv5) hisreportdataDao.get(
								Sysparv5.class, sysid1);
						String sysname1 = "";
						if (sys1 != null) {
							sysname1 = sys1.getPmva();
						}
						proday.setProtype(pro[9].toString());
						proday.setProtypename(sysname1);
						if ("02".equals(pro[11].toString())) {// �˶�
							proday.setNumb(0L);// ����
							proday.setMont(Double.parseDouble(pro[7].toString()));// ���
							proday.setDuf(Double.parseDouble(pro[13].toString()));// �Żݽ��
							proday.setDua(0d);// ֧�����
							proday.setIsf(Long.parseLong(pro[12].toString()));
							proday.setDub(Double.parseDouble(pro[8].toString()));// ������
							proday.setIsa(Long.parseLong(pro[10].toString()));// �˶�����
						} else {
							proday.setNumb(Long.parseLong(pro[5].toString()));// ����
							proday.setMont(Double.parseDouble(pro[6].toString()));// ���
							proday.setDuf(Double.parseDouble(pro[13].toString()));
							proday.setIsf(Long.parseLong(pro[12].toString()));
							proday.setDua(0d);// ֧�����
							proday.setDub(0d);// ������
							proday.setIsa(0L);// �˶�����
						}
					}

					if ("99".equals(pro[9].toString())) {// ����
						proday.setProtype("01");
						proday.setProtypename("����֧��");

						proday.setNumb(0L);// ����
						proday.setMont(0d);// ���
						proday.setDua(0d);// ֧�����
						if (pro[8] == null || "".equals(pro[8])) {
							proday.setDub(0d);
						} else {
							proday.setDub(Double.parseDouble(pro[8].toString()));// ������
						}
						proday.setIsa(Long.parseLong(pro[10].toString()));// �˶�����
						proday.setNotea("07");
						proday.setNoteb("δ��Ʊ�˶�");
					}
					hisreportdataDao.save(proday);

				}
			}
			// �ο���Դ(Rregiondaytab)
			List sourceList = hisreportdataDao.updateOrQueryRegionByDate("1",
					dates);
			if (sourceList != null && sourceList.size() >= 1) {
				for (int i = 0; i < sourceList.size(); i++) {
					Object[] pro = (Object[]) sourceList.get(i);

					Rregiondaytab regday = new Rregiondaytab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rregiondaytab");
					regday.setId(seq + 1);// ����
					regday.setTimes(dates);// ����
					regday.setRdate(dates.substring(8, 10));// ��
					regday.setRmonth(dates.substring(5, 7));// ��
					regday.setRyear(dates.substring(0, 4));// ��
					regday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					regday.setSzscenicname(pro[1].toString());// ����������
					regday.setIregionalid(Long.parseLong(pro[2].toString()));// ��Դ�ر��
					regday.setSzregionalname(pro[3].toString());// ��Դ������
					regday.setNumb(Long.parseLong(pro[4].toString()));// ����
					// �������
					this.hisreportdataDao.save(regday);

				}
			}
			// �ο�����(Rcustomdaytab)
			List customCountList = hisreportdataDao
					.updateOrQueryCustomCountByDate("1", dates);
			if (customCountList != null && customCountList.size() >= 1) {
				for (int j = 0; j < customCountList.size(); j++) {
					Object[] pro = (Object[]) customCountList.get(j);

					Rcustomdaytab cusday = new Rcustomdaytab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rcustomdaytab");
					cusday.setId(seq + 1);
					cusday.setTimes(dates);// ����
					cusday.setRdate(dates.substring(8, 10));// ��
					cusday.setRmonth(dates.substring(5, 7));// ��
					cusday.setRyear(dates.substring(0, 4));// ��
					cusday.setIscenicid(Long.parseLong(pro[0].toString()));// �����̱��
					cusday.setSzscenicname(pro[1].toString());// ����������
					cusday.setNumb(Long.parseLong(pro[2].toString()));// ����

					// �������
					this.hisreportdataDao.save(cusday);

				}

			}

			// ��ƱԱ��Ʊ(Rsalecounttab)
			List saleCountList = hisreportdataDao.updateOrQuerySaleCountByDate(
					"1", "01", dates);
			if (saleCountList != null && saleCountList.size() >= 1) {
				for (int i = 0; i < saleCountList.size(); i++) {
					Object[] pro = (Object[]) saleCountList.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("01");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����
					salecount.setSkfs(pro[2].toString());// �տʽ

					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmky("ZFFS");// ֧����ʽ
					sysid.setPmcd(pro[2].toString());
					Sysparv5 sysparv5 = (Sysparv5) this.hisreportdataDao.get(
							Sysparv5.class, sysid);
					if (sysparv5 != null) {
						salecount.setSkfsname(sysparv5.getPmva());// ֧����ʽ����
					}

					salecount.setEmpid(pro[3].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[4].toString());// ��ƱԱ����
					salecount.setMont(Double.parseDouble(pro[5].toString()));// ���
					Long itityeid = Long.parseLong(pro[6].toString());// ��Ʒ���
					salecount.setItickettypeid(itityeid);// ��Ʒ���

					Edmtickettypetab tickettype = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itityeid);
					if (tickettype != null) {
						salecount.setSztickettypename(tickettype
								.getSztickettypename());// ��Ʒ����
						salecount.setTickettype(tickettype.getBycategorytype());// ��Ʒ����
					}

					salecount.setNumb(Long.parseLong(pro[7].toString()));// ����
					salecount.setDua(Double.parseDouble(pro[8].toString()));// ����

					Long kindId = Long.parseLong(pro[9].toString());// �۸���
					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindId);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					salecount.setIsa(kindId);// ��Ⱥ����
					salecount.setNoteb(szcrowdkindname);
					salecount.setNotea("01");// ��Ʊ
					salecount.setDub(0d);

					Long istationid = Long.parseLong(pro[11].toString());
					Esbticketstationtab station = (Esbticketstationtab) hisreportdataDao
							.get(Esbticketstationtab.class, istationid);
					String stationname = "";
					if (station != null) {
						stationname = station.getSzstationname();
					}
					salecount.setIsb(istationid);
					salecount.setNotec(stationname);
					Long iticketwinid = Long.parseLong(pro[12].toString());
					salecount.setIsc(iticketwinid);
					Long ibusinessid = Long.parseLong(pro[13].toString());
					salecount.setIsd(ibusinessid);

					salecount.setNotef(pro[14].toString()); // �ͻ�ID
					Object o = pro[15];
					String szregionalname = "";
					if (o != null && !o.equals("")) {
						Galsourceregiontab sourcer = (Galsourceregiontab) hisreportdataDao
								.get(Galsourceregiontab.class,
										Long.parseLong(o.toString()));
						if (sourcer != null) {
							szregionalname = sourcer.getSzregionalname();
						}
						salecount.setIsf(Long.parseLong(o.toString()));// ��Դ��ID
						salecount.setNotee(szregionalname);// ��Դ������
					}
					salecount.setIse(Long.parseLong(pro[16].toString()));// Ʊ��
					salecount.setDue(Double.parseDouble(pro[17].toString()));// �Ż�����
					salecount.setDuf(Double.parseDouble(pro[18].toString()));// �Żݽ��
					// �������
					this.hisreportdataDao.save(salecount);
				}
			}
			// ��ƱԱ����(Rsalecounttab)
			List pllist = hisreportdataDao.updateOrQuerySaleCountByDate("1", "04",
					dates);
			if (pllist != null && pllist.size() >= 1) {
				for (int i = 0; i < pllist.size(); i++) {
					Object[] pro = (Object[]) pllist.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("01");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����
					salecount.setSkfs(pro[2].toString());// �տʽ

					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmky("ZFFS");// ֧����ʽ
					sysid.setPmcd(pro[2].toString());
					Sysparv5 sysparv5 = (Sysparv5) this.hisreportdataDao.get(
							Sysparv5.class, sysid);
					if (sysparv5 != null) {
						salecount.setSkfsname(sysparv5.getPmva());// ֧����ʽ����
					}

					salecount.setEmpid(pro[3].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[4].toString());// ��ƱԱ����
					salecount.setMont(Double.parseDouble(pro[5].toString()));// ���
					Long itityeid = Long.parseLong(pro[6].toString());// ��Ʒ���
					salecount.setItickettypeid(itityeid);// ��Ʒ���

					Edmtickettypetab tickettype = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itityeid);
					if (tickettype != null) {
						salecount.setSztickettypename(tickettype
								.getSztickettypename());// ��Ʒ����
						salecount.setTickettype(tickettype.getBycategorytype());// ��Ʒ����
					}

					salecount.setNumb(Long.parseLong(pro[7].toString()));// ����
					salecount.setDua(Double.parseDouble(pro[8].toString()));// ����

					Long kindId = Long.parseLong(pro[9].toString());// �۸���
					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindId);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					salecount.setIsa(kindId);// ��Ⱥ����
					salecount.setNoteb(szcrowdkindname);
					salecount.setNotea("04");// ����
					salecount.setDub(0d);

					Long istationid = Long.parseLong(pro[11].toString());
					Esbticketstationtab station = (Esbticketstationtab) hisreportdataDao
							.get(Esbticketstationtab.class, istationid);
					String stationname = "";
					if (station != null) {
						stationname = station.getSzstationname();
					}
					salecount.setIsb(istationid);
					salecount.setNotec(stationname);
					Long iticketwinid = Long.parseLong(pro[12].toString());
					salecount.setIsc(iticketwinid);
					Long ibusinessid = Long.parseLong(pro[13].toString());
					salecount.setIsd(ibusinessid);

					salecount.setNotef(pro[14].toString()); // �ͻ�ID
					Object o = pro[15];
					String szregionalname = "";
					if (o != null && !o.equals("")) {
						Galsourceregiontab sourcer = (Galsourceregiontab) hisreportdataDao
								.get(Galsourceregiontab.class,
										Long.parseLong(o.toString()));
						if (sourcer != null) {
							szregionalname = sourcer.getSzregionalname();
						}
						salecount.setIsf(Long.parseLong(o.toString()));// ��Դ��ID
						salecount.setNotee(szregionalname);// ��Դ������
					}
					salecount.setIse(Long.parseLong(pro[16].toString()));// �˴�ID
					salecount.setDue(Double.parseDouble(pro[17].toString()));// �Ż�����
					salecount.setDuf(Double.parseDouble(pro[18].toString()));// �Żݽ��
					// �������
					this.hisreportdataDao.save(salecount);
				}
			}

			// ��ƱԱ��Ʊ(Rsalecounttab)
			List TuiDingsaleCountList = hisreportdataDao
					.updateOrQuerySaleCountByDate("1", "02", dates);
			if (TuiDingsaleCountList != null
					&& TuiDingsaleCountList.size() >= 1) {
				for (int i = 0; i < TuiDingsaleCountList.size(); i++) {
					Object[] pro = (Object[]) TuiDingsaleCountList.get(i);

					Rsalecounttab salecount = new Rsalecounttab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rsalecounttab");
					salecount.setId(seq + 1);// ����
					salecount.setTimes(dates);// ����
					salecount.setRdate(dates.substring(8, 10));// ��
					salecount.setRmonth(dates.substring(5, 7));// ��
					salecount.setRyear(dates.substring(0, 4));// ��
					salecount.setTitype("01");// ��������
					salecount.setIscenicid(Long.parseLong(pro[0].toString()));// ��˾���
					salecount.setSzscenicname(pro[1].toString());// ��˾����

					salecount.setSkfs(pro[2].toString());// �տʽ

					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmky("ZFFS");// ֧����ʽ
					sysid.setPmcd(pro[2].toString());
					Sysparv5 sysparv5 = (Sysparv5) this.hisreportdataDao.get(
							Sysparv5.class, sysid);
					if (sysparv5 != null) {
						salecount.setSkfsname(sysparv5.getPmva());// ֧����ʽ����
					}

					salecount.setEmpid(pro[3].toString());// ��ƱԱemp
					salecount.setSzemployeename(pro[4].toString());// ��ƱԱ����
					salecount.setMont(Double.parseDouble(pro[5].toString()));// ���
					Long itityeid = Long.parseLong(pro[6].toString());// ��Ʒ���
					salecount.setItickettypeid(itityeid);// ��Ʒ���

					Edmtickettypetab tickettype = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itityeid);
					if (tickettype != null) {
						salecount.setSztickettypename(tickettype
								.getSztickettypename());// ��Ʒ����
						salecount.setTickettype(tickettype.getBycategorytype());// ��Ʒ����
					}

					salecount.setNumb(Long.parseLong(pro[7].toString()));// ����
					salecount.setDua(Double.parseDouble(pro[8].toString()));// ����
					Long kindId = Long.parseLong(pro[9].toString());// ��Ⱥ������
					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindId);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					salecount.setIsa(kindId);// ��Ⱥ����
					salecount.setNoteb(szcrowdkindname);// ��Ⱥ��������

					salecount.setDub(Double.parseDouble(pro[10].toString()));// ������
					Long istationid = Long.parseLong(pro[11].toString());// վ��
					Esbticketstationtab station = (Esbticketstationtab) hisreportdataDao
							.get(Esbticketstationtab.class, istationid);
					String stationname = "";
					if (station != null) {
						stationname = station.getSzstationname();
					}
					salecount.setIsb(istationid);
					salecount.setNotec(stationname);
					Long iticketwinid = Long.parseLong(pro[12].toString());
					salecount.setIsc(iticketwinid);
					Long ibusinessid = Long.parseLong(pro[13].toString());
					salecount.setIsd(ibusinessid);
					salecount.setNotea("02");// ��Ʊ

					salecount.setNotef(pro[14].toString()); // �ͻ�ID
					Object o = pro[15];
					String szregionalname = "";
					if (o != null && !o.equals("")) {
						Galsourceregiontab sourcer = (Galsourceregiontab) hisreportdataDao
								.get(Galsourceregiontab.class,
										Long.parseLong(o.toString()));
						if (sourcer != null) {
							szregionalname = sourcer.getSzregionalname();
						}
						salecount.setIsf(Long.parseLong(o.toString()));// ��Դ��ID
						salecount.setNotee(szregionalname);// ��Դ������
					}
					salecount.setIse(Long.parseLong(pro[16].toString()));// �˴�ID
					salecount.setDue(Double.parseDouble(pro[17].toString()));// �Ż�����
					salecount.setDuf(Double.parseDouble(pro[18].toString()));// �Żݽ��
					// �������
					this.hisreportdataDao.save(salecount);

				}
			}
			// �������Ʊ����
			List lxssaleList = hisreportdataDao.updateOrQueryLxsSaleByDate("1",
					dates);
			if (lxssaleList != null && lxssaleList.size() >= 1) {
				for (int i = 0; i < lxssaleList.size(); i++) {
					Map map = (Map) lxssaleList.get(i);

					Rcustomgrouptab customgroup = new Rcustomgrouptab();
					Long seq = hisreportdataDao.getMaxPk("seq", "Rcustomgrouptab");
					customgroup.setSeq(seq + (i + 1));// ����
					customgroup.setTimes(dates);// ����
					customgroup.setRdate(dates.substring(8, 10));// ��
					customgroup.setRmonth(dates.substring(5, 7));// ��
					customgroup.setRyear(dates.substring(0, 4));// ��
					customgroup.setTitype("01");// ��������

					String usid = map.get("usid").toString();// ��Ʊ�û�
					Custom custom = (Custom) hisreportdataDao.get(Custom.class,
							usid);
					if ("01".equals(custom.getUstp())) {
						customgroup.setChiefuser(usid);// ����
					} else if ("02".equals(custom.getUstp())) {
						String susid = custominfoDao.queryParentUsid(usid);
						customgroup.setChiefuser(susid);// ����
						customgroup.setSonuser(usid);// ����
					} else if ("02".equals(custom.getUstp())) {
						String susid = custominfoDao.queryParentUsid(usid);
						String rootusid = "";
						if (susid != null && !"".equals(susid)) {
							rootusid = custominfoDao.queryParentUsid(susid);
						}
						customgroup.setChiefuser(rootusid);// ����
						customgroup.setSonuser(susid);// ����
						customgroup.setGrandsonuser(usid);// ����Ա
					}

					String iticket = map.get("bycategorytype").toString();// Ʊ����
					String typename = "";
					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmky("PRTP");
					sysid.setPmcd(iticket);
					Sysparv5 sysparv5 = (Sysparv5) hisreportdataDao.get(
							Sysparv5.class, sysid);
					if (sysparv5 != null) {
						typename = sysparv5.getPmva();
					}
					customgroup.setTickettype(iticket);// Ʊ����
					customgroup.setTtypename(typename);// Ʊ��������
					customgroup.setNumb(Long.parseLong(map.get("numb")
							.toString()));// Ʊ��
					customgroup.setMont(Double.parseDouble(map.get("mont")
							.toString()));// �ܽ��
					customgroup.setDuf(Double.parseDouble(map.get(
							"mderatemoney").toString()));// �Żݽ��
					customgroup.setIsf(Long.parseLong(map.get("ideratenums")
							.toString()));// �Ż�����
					customgroup.setIsb(Long.parseLong(map.get("itickettypeid")
							.toString()));// ��Ʒ���
					customgroup.setIsc(Long.parseLong(map.get("iscenicid")
							.toString()));// ������
					customgroup.setIsd(Long.parseLong(map.get("icrowdkindid")
							.toString()));// ��Ⱥ���
					customgroup.setDub(Double.parseDouble(map.get(
							"mactualsaleprice").toString()));// ��Ʒ����
					customgroup.setNotea(map.get("bysalesvouchertype")
							.toString());// ��������˶�
					customgroup.setNoteb(map.get("isettlementid").toString());// ֧������
					hisreportdataDao.save(customgroup);

				}
			}
			// ��Ʊ����Rsaletickettab
			List saleticket = hisreportdataDao.updateOrquerySale("1", dates);
			if (saleticket != null && saleticket.size() >= 1) {
				for (int i = 0; i < saleticket.size(); i++) {
					Object[] pro = (Object[]) saleticket.get(i);
					Rsaletickettab sale = new Rsaletickettab();

					// ��ȡ����
					Long seq = hisreportdataDao.getMaxPk("seq", "Rsaletickettab");
					sale.setSeq(seq + 1);
					sale.setRdate(dates.substring(8, 10));// ��
					sale.setRmonth(dates.substring(5, 7));// ��
					sale.setRyear(dates.substring(0, 4));// ��
					sale.setTimes(dates);// ����
					sale.setTitype("01");// ��������

					Long iscneicid = Long.parseLong(pro[0].toString());// ������
					Long itickettypeid = Long.parseLong(pro[1].toString());// ��Ʒ���

					String szscenicname = "";
					String sztickettypename = "";
					Esbscenicareatab provider = (Esbscenicareatab) hisreportdataDao
							.get(Esbscenicareatab.class, iscneicid);
					if (provider != null) {
						szscenicname = provider.getSzscenicname();
					}
					Edmtickettypetab product = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itickettypeid);
					if (product != null) {
						sztickettypename = product.getSztickettypename();
					}
					sale.setIscenicid(iscneicid);// ������
					sale.setItickettypeid(itickettypeid);// ��Ʒ���
					sale.setSzscenicname(szscenicname);// ����������
					sale.setSztickettypename(sztickettypename);// ��Ʒ����
					sale.setNumb(Long.parseLong(pro[2].toString()));// ����
					sale.setMont(Double.parseDouble(pro[3].toString()));// ���
					sale.setDua(Double.parseDouble(pro[4].toString()));// ����
					sale.setDuf(Double.parseDouble(pro[7].toString()));// �Żݽ��
					sale.setIsf(Long.parseLong(pro[8].toString()));// �Ż�����
					Long kindId = Long.parseLong(pro[5].toString());// ��Ⱥ������
					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindId);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					sale.setIsa(kindId);// ��Ⱥ����
					sale.setNotea(szcrowdkindname);// ��Ⱥ��������

					Long istationid = Long.parseLong(pro[6].toString());// վ��
					Esbticketstationtab station = (Esbticketstationtab) hisreportdataDao
							.get(Esbticketstationtab.class, istationid);
					String stationname = "";
					if (station != null) {
						stationname = station.getSzstationname();
					}
					sale.setIsb(istationid);
					sale.setNoteb(stationname);

					hisreportdataDao.save(sale);

				}
			}
			// Ʊ�����ۣ�Rticketdaytab��
			System.out.println("����Ʊ�������ձ�");
			List ticketsalelist = hisreportdataDao.updateOrQueryTicketSale("1",
					dates);
			// ��ѯ���񷤵�

			if (ticketsalelist != null && ticketsalelist.size() >= 1) {
				for (int i = 0; i < ticketsalelist.size(); i++) {
					Object[] obj = (Object[]) ticketsalelist.get(i);
					Long itickettypeid = Long.parseLong(obj[1].toString());// ��Ʒid
					Long ibussnessid = Long.parseLong(obj[0].toString());// ҵ������Id
					Edmtickettypetab ticket = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itickettypeid);
					String szticketname = "";
					String tickettype = "";
					if (ticket != null) {
						szticketname = ticket.getSztickettypename();
						tickettype = ticket.getBycategorytype();
					}
					Rticketdaytab tisale = new Rticketdaytab();
					Long seq = hisreportdataDao.getMaxPk("id", "Rticketdaytab");
					tisale.setId(seq + 1);// ����
					tisale.setIsc(new Long(obj[7].toString()));// �۸���
					tisale.setTimes(dates);
					tisale.setRdate(dates.substring(8, 10));
					tisale.setRmonth(dates.substring(5, 7));// ��
					tisale.setRyear(dates.substring(0, 4));// ��
					Edmbusinesstab business = (Edmbusinesstab) hisreportdataDao
							.get(Edmbusinesstab.class, ibussnessid);
					String szbusiness = "";
					if (business != null) {
						szbusiness = business.getSzbusinessname();
					}
					tisale.setIsa(ibussnessid);
					tisale.setNotea(szbusiness);// ҵ�����
					tisale.setItickettypeid(itickettypeid);// ��Ʒid
					tisale.setSztickettypename(szticketname);// ��Ʒ����
					tisale.setTickettype(tickettype);// ��Ʒ����
					tisale.setNumb(Long.parseLong(obj[2].toString()));// ����
					tisale.setMont(Double.parseDouble(obj[3].toString()));// �ܽ��
					tisale.setDua(Double.parseDouble(obj[4].toString()));// ����
					tisale.setDuf(Double.parseDouble(obj[9].toString()));// �Żݽ��
					tisale.setIsf(Long.parseLong(obj[10].toString()));// �Ż�����
					Long kindId = Long.parseLong(obj[5].toString());// ��Ⱥ������

					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindId);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					tisale.setIsb(kindId);// ��Ⱥ����
					tisale.setNoteb(szcrowdkindname);// ��Ⱥ��������
					if (obj[6].toString().equals("04")) {
						tisale.setNotec("01");
					} else {
						tisale.setNotec(obj[6].toString());
					}

					Double price = Double.parseDouble(obj[8].toString());
					if (obj[6].toString().equals("02")) {
						if (tickettype.equals("0010")) {
							if (price.doubleValue() == tisale.getDua()
									.doubleValue()) {
								tisale.setIsd(new Long(0));// ȫ��
							} else {

								tisale.setIsd(new Long(2));// ������

							}
							System.out.println(tisale.getIsd());
						} else {
							tisale.setIsd(new Long(0));
						}
					} else {
						tisale.setIsd(new Long(0));// ����
					}

					hisreportdataDao.save(tisale);

				}
			}
			// ǰ̨���������ۻ����ձ�Rcgroupsaledaytab)
			List tuantisalecountlist = hisreportdataDao.updateOrQueryCusGroupSale(
					"1", dates);
			if (tuantisalecountlist != null && tuantisalecountlist.size() >= 1) {
				for (int i = 0; i < tuantisalecountlist.size(); i++) {
					Object[] obj = (Object[]) tuantisalecountlist.get(i);
					Rcgroupsaledaytab group = new Rcgroupsaledaytab();
					Long seq = hisreportdataDao.getMaxPk("seq",
							"Rcgroupsaledaytab");

					group.setSeq(seq + 1);// ����
					group.setIsb(new Long(obj[7].toString()));// ҵ��ID
					group.setTimes(dates);
					group.setRdate(dates.substring(8, 10));
					group.setRmonth(dates.substring(5, 7));// ��
					group.setRyear(dates.substring(0, 4));// ��
					String usid = obj[0].toString();// �û���
					Custom custom = (Custom) hisreportdataDao.get(Custom.class,
							usid);
					String corpname = "";
					if (custom != null) {
						corpname = custom.getCorpname();
					}
					group.setUsid(obj[0].toString());
					group.setCorpname(corpname);
					Long itickettypeid = Long.parseLong(obj[1].toString());// ��Ʒid
					Edmtickettypetab ticket = (Edmtickettypetab) hisreportdataDao
							.get(Edmtickettypetab.class, itickettypeid);
					String szticketname = "";
					String tickettype = "";
					if (ticket != null) {
						szticketname = ticket.getSztickettypename();
					}
					group.setItickettypeid(itickettypeid);// ��Ʒid
					group.setSztickettypename(szticketname);// ��Ʒ����
					group.setNumb(Long.parseLong(obj[2].toString()));// ����
					if (obj[3] == null || obj[3].equals("")) {
						group.setMont(new Double(0));
					} else {
						group.setMont(Double.parseDouble(obj[3].toString()));// �ܽ��
					}
					group.setDua(Double.parseDouble(obj[4].toString()));// ����
					Long kindid = Long.parseLong(obj[5].toString());// ��Ⱥ������
					if (obj[6].toString().equals("04")) {
						group.setNoteb("01");// ƾ֤���
					} else {
						group.setNoteb(obj[6].toString());
					}
					group.setIsc(new Long(obj[8].toString()));// �۸�ID
					Edpcrowdkindtab crowd = (Edpcrowdkindtab) hisreportdataDao
							.get(Edpcrowdkindtab.class, kindid);
					String szcrowdkindname = "";
					if (crowd != null) {
						szcrowdkindname = crowd.getSzcrowdkindname();// ��Ⱥ��������
					}
					group.setIsa(kindid);// ��Ⱥ����
					group.setNotea(szcrowdkindname);
					group.setDuf(Double.parseDouble(obj[10].toString()));// �Żݽ��
					group.setNotec(obj[12].toString());// ֧����ʽ
					group.setIsf(Long.parseLong(obj[11].toString()));// �Ż�����
					group.setDue(Double.parseDouble(obj[13].toString()));// ������
					group.setIsd(Long.parseLong(obj[14].toString()));// ��Ʊ����ID
					hisreportdataDao.save(group);

				}
			}

			// �����ͳ���ձ���
			List rwslist = this.hisreportdataDao.checkRwswarehouse(
					dates.replaceAll("-", ""), 1L, 1L);// 1:��ͳ�ƣ� 1��Ԥ��Ʊ
			if (rwslist != null && rwslist.size() > 0) {
				for (int x = 0; x < rwslist.size(); x++) {
					Rwswarehouseday rws = (Rwswarehouseday) rwslist.get(x);
					this.hisreportdataDao.delete(rws);
				}
			}
			List houseList = this.hisreportdataDao.showAllHouseware(
					dates.replaceAll("-", ""), 1L, 1L);// 1:��ͳ�ƣ� 1��Ԥ��Ʊ
			if (houseList != null && houseList.size() > 0) {
				for (int i = 0; i < houseList.size(); i++) {

					Object[] obj = (Object[]) houseList.get(i);

					// ĳ�ֿ���������
					List houselist = this.hisreportdataDao.showIamoutcheckin(
							Long.parseLong(obj[0].toString()),
							dates.replaceAll("-", ""), 1L, 1L);
					// ĳ�ֿ�ĳ�������
					List warelise = this.hisreportdataDao.showIamoutcheckOut(
							Long.parseLong(obj[0].toString()),
							dates.replaceAll("-", ""), 1L, 1L);
					if ((houselist != null && houselist.size() > 0)
							|| (warelise != null && warelise.size() > 0)) {
						if (houselist != null && houselist.size() > 0) {
							for (int x = 0; x < houselist.size(); x++) {
								Rwswarehouseday rwsware = new Rwswarehouseday();
								Long seq = hisreportdataDao.getMaxPk("statid",
										"Rwswarehouseday");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(dates
										.replaceAll("-", "")));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0]
										.toString()));

								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1]
										.toString()));
								rwsware.setNumin(Long.parseLong(ject[3]
										.toString()));
								Long recenter = this.hisreportdataDao
										.checkRecenterout(Long.parseLong(obj[0]
												.toString()), Long
												.parseLong(ject[1].toString()),
												Tools.getDate(dates, -1)
														.replaceAll("-", ""),
												1L, 1L);
								if (recenter != null) {
									rwsware.setRecentremain(recenter);
								} else {
									rwsware.setRecentremain(0L);
								}

								if (warelise != null && warelise.size() > 0) {
									for (int y = 0; y < warelise.size(); y++) {
										Object[] object = (Object[]) warelise
												.get(y);
										if (ject[1].toString().equals(
												object[1].toString())) {
											rwsware.setNumout(Long
													.parseLong(object[0]
															.toString()));
										} else {
											if (y == warelise.size() - 1) {
												if (rwsware.getNumout() == null) {
													rwsware.setNumout(0l);
												}
											}
										}
									}
								} else {
									rwsware.setNumout(0L);
								}

								rwsware.setEndremain(rwsware.getRecentremain()
										+ rwsware.getNumin()
										- rwsware.getNumout());
								rwsware.setInt1(1L); // 1��Ԥ��Ʊ�� 2��IC��
								hisreportdataDao.save(rwsware);
							}
						} else {
							if (warelise != null && warelise.size() > 0) {
								for (int y = 0; y < warelise.size(); y++) {
									Rwswarehouseday rwsware = new Rwswarehouseday();
									Long seq = hisreportdataDao.getMaxPk("statid",
											"Rwswarehouseday");
									rwsware.setStatid(seq + 1);// ����
									rwsware.setStatdate(Long.parseLong(dates
											.replaceAll("-", "")));
									rwsware.setSzwarehousename(obj[1]
											.toString());
									rwsware.setIwarehouseid(Long
											.parseLong(obj[0].toString()));

									Object[] object = (Object[]) warelise
											.get(y);
									rwsware.setNumout(Long.parseLong(object[0]
											.toString()));
									rwsware.setSztickettypename(object[2]
											.toString());
									rwsware.setItickettypeid(Long
											.parseLong(object[1].toString()));

									rwsware.setNumin(0L);
									Long recenter = this.hisreportdataDao
											.checkRecenterout(Long
													.parseLong(obj[0]
															.toString()), Long
													.parseLong(object[1]
															.toString()), Tools
													.getDate(dates, -1)
													.replaceAll("-", ""), 1L,
													1L);
									if (recenter != null) {
										rwsware.setRecentremain(recenter);
									} else {
										rwsware.setRecentremain(0L);
									}

									rwsware.setEndremain(rwsware
											.getRecentremain()
											+ rwsware.getNumin()
											- rwsware.getNumout());
									rwsware.setInt1(1L); // 1��Ԥ��Ʊ�� 2��IC��
									hisreportdataDao.save(rwsware);
								}
							}
						}

					} else {
						continue;
					}
				}
			}

			// IC�� �����ͳ���ձ���
			List kcrwslist = this.hisreportdataDao.checkRwswarehouse(
					dates.replaceAll("-", ""), 1L, 2L);// 1:��ͳ�ƣ� 2��IC��
			if (kcrwslist != null && kcrwslist.size() > 0) {
				for (int x = 0; x < kcrwslist.size(); x++) {
					Rwswarehouseday rws = (Rwswarehouseday) kcrwslist.get(x);
					this.hisreportdataDao.delete(rws);
				}
			}
			List kchouseList = this.hisreportdataDao.showAllHouseware(
					dates.replaceAll("-", ""), 1L, 2L);// 1:��ͳ�ƣ� 2��IC��
			if (kchouseList != null && kchouseList.size() > 0) {
				for (int i = 0; i < kchouseList.size(); i++) {

					Object[] obj = (Object[]) kchouseList.get(i);

					// ĳ�ֿ���������
					List houselist = this.hisreportdataDao.showIamoutcheckin(
							Long.parseLong(obj[0].toString()),
							dates.replaceAll("-", ""), 1L, 2L);// 1:��ͳ�ƣ� 2��IC��
					// ĳ�ֿ�ĳ�������
					List warelise = this.hisreportdataDao.showIamoutcheckOut(
							Long.parseLong(obj[0].toString()),
							dates.replaceAll("-", ""), 1L, 2L);// 1:��ͳ�ƣ� 2��IC��
					if ((houselist != null && houselist.size() > 0)
							|| (warelise != null && warelise.size() > 0)) {
						if (houselist != null && houselist.size() > 0) {
							for (int x = 0; x < houselist.size(); x++) {
								Rwswarehouseday rwsware = new Rwswarehouseday();
								Long seq = hisreportdataDao.getMaxPk("statid",
										"Rwswarehouseday");
								rwsware.setStatid(seq + 1);// ����
								rwsware.setStatdate(Long.parseLong(dates
										.replaceAll("-", "")));
								rwsware.setSzwarehousename(obj[1].toString());
								rwsware.setIwarehouseid(Long.parseLong(obj[0]
										.toString()));

								Object[] ject = (Object[]) houselist.get(x);
								rwsware.setSztickettypename(ject[2].toString());
								rwsware.setItickettypeid(Long.parseLong(ject[1]
										.toString()));
								rwsware.setNumin(Long.parseLong(ject[3]
										.toString()));
								Long recenter = this.hisreportdataDao
										.checkRecenterout(Long.parseLong(obj[0]
												.toString()), Long
												.parseLong(ject[1].toString()),
												Tools.getDate(dates, -1)
														.replaceAll("-", ""),
												1L, 2L);// 1:��ͳ�ƣ� 2��IC��
								if (recenter != null) {
									rwsware.setRecentremain(recenter);
								} else {
									rwsware.setRecentremain(0L);
								}

								if (warelise != null && warelise.size() > 0) {
									for (int y = 0; y < warelise.size(); y++) {
										Object[] object = (Object[]) warelise
												.get(y);
										if (ject[1].toString().equals(
												object[1].toString())) {
											rwsware.setNumout(Long
													.parseLong(object[0]
															.toString()));
										} else {
											if (y == warelise.size() - 1) {
												if (rwsware.getNumout() == null) {
													rwsware.setNumout(0l);
												}
											}
										}
									}
								} else {
									rwsware.setNumout(0L);
								}

								rwsware.setEndremain(rwsware.getRecentremain()
										+ rwsware.getNumin()
										- rwsware.getNumout());
								rwsware.setInt1(2L); // 1��Ԥ��Ʊ�� 2��IC��
								hisreportdataDao.save(rwsware);
							}
						} else {
							if (warelise != null && warelise.size() > 0) {
								for (int y = 0; y < warelise.size(); y++) {
									Rwswarehouseday rwsware = new Rwswarehouseday();
									Long seq = hisreportdataDao.getMaxPk("statid",
											"Rwswarehouseday");
									rwsware.setStatid(seq + 1);// ����
									rwsware.setStatdate(Long.parseLong(dates
											.replaceAll("-", "")));
									rwsware.setSzwarehousename(obj[1]
											.toString());
									rwsware.setIwarehouseid(Long
											.parseLong(obj[0].toString()));

									Object[] object = (Object[]) warelise
											.get(y);
									rwsware.setNumout(Long.parseLong(object[0]
											.toString()));
									rwsware.setSztickettypename(object[2]
											.toString());
									rwsware.setItickettypeid(Long
											.parseLong(object[1].toString()));

									rwsware.setNumin(0L);
									Long recenter = this.hisreportdataDao
											.checkRecenterout(Long
													.parseLong(obj[0]
															.toString()), Long
													.parseLong(object[1]
															.toString()), Tools
													.getDate(dates, -1)
													.replaceAll("-", ""), 1L,
													2L);// 1:��ͳ�ƣ� 2��IC��
									if (recenter != null) {
										rwsware.setRecentremain(recenter);
									} else {
										rwsware.setRecentremain(0L);
									}

									rwsware.setEndremain(rwsware
											.getRecentremain()
											+ rwsware.getNumin()
											- rwsware.getNumout());
									rwsware.setInt1(2L); // 1��Ԥ��Ʊ�� 2��IC��
									hisreportdataDao.save(rwsware);
								}
							}
						}

					} else {
						continue;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("�ձ����ֶ����г����쳣");
		}

		System.out.println("�ձ�������ֶ�ִ��" + Tools.getDayTimes());

		return 1;
	}
}
