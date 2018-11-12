package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.ec.report.system.datereport.dao.idao.IReportsListDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportListNoService;

public class ReportListNoService implements IReportListNoService {

	private IReportsListDAO reportslistDao;

	public IReportsListDAO getReportslistDao() {
		return reportslistDao;
	}

	public void setReportslistDao(IReportsListDAO reportslistDao) {
		this.reportslistDao = reportslistDao;
	}

	synchronized public int jobrun(Long isalesvoucherid) {

		try {

			// ���ݷ�ʽ����ÿ����Ʊƾ֤�ɣġ��ȱ�����Ʊƾ֤��ʷ�� Stssalesvouchertablist,
			// ƾ֤������ʷ��
			// Stssalessettlementtablist��ƾ֤��ϸ��ʷ��Stssalesvoucherdetailstablist��
			// ƾ֤��ϸ�۸������ʷ��
			// Stscomticketsalesdetailstabls�������۳���Ʊ����ʷ��Stssoldtickettablist��
			// �۳���Ʊ�ӱ���ʷ��Stssoldticketsubtablist

			// ɾ����ʽ����ɾ����Ʊ��¼Ticketchecklist����ӡ��¼Ticketprintlist,���۳���Ʊ�����֤�ӱ�Stssoldticketattesttab����
			// Ȼ��ɾ��Stssoldticketsubtab��Stssoldtickettab��Stscomticketsalesdetailstab��Stssalesvoucherdetailstab��
			// Stssalessettlementtab��Stssalesvouchertab

			// ��Ʊƾ֤��ʷ�� Stssalesvouchertablist
			reportslistDao.deleteReportListDates("Stssalesvouchertablist",
					"isalesvoucherid", isalesvoucherid);
			String[] colsname = new String[] { "isalesvoucherid",
					"iticketstationid", "iscenicid", "iticketwinid",
					"szsalesvoucherno", "ibusinessid", "bisintegral",
					"byprintinvoice", "bysplitway", "bisreturn",
					"bysalesvouchertype", "isticketstationid",
					"issalesvoucherid", "forcedrefund", "sztravelbillno",
					"isalesmanid", "iregionalid", "usid", "tdlx", "dyusid",
					"bypostrecord", "mhandcharge", "iaccountreceivable",
					"iacceptmoney", "igivechange", "ihandler", "ipayeer",
					"imaker", "iauditor", "iyear", "imonth", "iday",
					"dtmakedate", "dtauditdate", "bysalesvoucherstate",
					"bispay", "bispayee", "ornote1", "ornote2", "ornote3",
					"ornote4", "ornote5", "ornote6", "ornote7", "ornote8",
					"ornote9", "ornote10" };
			String[] tablename = new String[] { "Stssalesvouchertablist",
					"Stssalesvouchertab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// ƾ֤������ʷ�� Stssalessettlementtablist
			reportslistDao.deleteReportListDates("Stssalessettlementtablist",
					"isalesvoucherid", isalesvoucherid);
			colsname = new String[] { "isalessettlementid", "isalesvoucherid",
					"iticketstationid", "settlementdata", "settlementtime",
					"isettlementid", "msettlementmoney", "iversion" };
			tablename = new String[] { "Stssalessettlementtablist",
					"Stssalessettlementtab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// ƾ֤��ϸ��ʷ��Stssalesvoucherdetailstablist
			reportslistDao.deleteReportListDates(
					"Stssalesvoucherdetailstablist", "isalesvoucherid",
					isalesvoucherid);
			colsname = new String[] { "isalesvoucherdetailsid",
					"isalesvoucherid", "iticketstationid", "icrowdkindpriceid",
					"itickettypeid", "iticketwinid", "iplayerperticket",
					"iticketnum", "iticketplayer", "dtstartdate", "dtenddate",
					"istartid", "iendid", "szstartserial", "szendserial",
					"ioffersschemeid", "iamount", "ipresentnums",
					"ideratenums", "ifactnum", "iuseablenessnum",
					"mactualsaleprice", "meventmoney", "mderatemoney",
					"mpresentmoney", "mnominalfee", "mdeposit", "mhandcharge",
					"byconsumetype", "iconsumenum", "iversion", "mtotalamount",
					"itotalnumber", "itotalminutes", "byisout", "dtmakedate" };
			tablename = new String[] { "Stssalesvoucherdetailstablist",
					"Stssalesvoucherdetailstab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// ƾ֤��ϸ�۸������ʷ�� Stscomticketsalesdetailstabls
			reportslistDao.deleteReportListDates(
					"Stscomticketsalesdetailstabls", "isalesvoucherid",
					isalesvoucherid);
			colsname = new String[] { "icomticketsalesdetailsid",
					"isalesvoucherdetailsid", "isalesvoucherid",
					"iticketstationid", "icrowdkindpriceid", "itickettypeid",
					"iztickettypeid", "tripid", "ivenueid", "ivenueareaid",
					"ivenueseatsid", "dtstartdate", "dtenddate",
					"isplitamount", "msplitmoney", "iversion", "msplitprice",
					"mhandcharge", "mderatemoney", "ideratenums" };
			tablename = new String[] { "Stscomticketsalesdetailstabls",
					"Stscomticketsalesdetailstab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// �۳���Ʊ����ʷ��Stssoldtickettablist��
			reportslistDao.deleteReportListDates("Stssoldtickettablist",
					"isalesvoucherid", isalesvoucherid);
			colsname = new String[] { "szsoldticketid",
					"isalesvoucherdetailsid", "isalesvoucherid",
					"iticketstationid", "itickettypeid", "icrowdkindid",
					"iscenicid", "usid", "ibusinessid", "dyusid",
					"iplayerperticket", "dtenddate", "dtstartdate",
					"mhandcharge", "ipartitionsign", "iagentno", "icardno",
					"szticketprintno", "iserialnum", "byvalidity",
					"mremainmoney", "mpresentmoney", "mactualsaleprice",
					"ipresentnum", "iremainnum", "mnominalfee", "mdeposit",
					"byticketpurpose", "mrefundamount", "mforcerefundamount",
					"bisrefundbalance", "mreservedmoney", "byactivation",
					"bisactivation", "ivaliditynum", "byvalidityunits",
					"byconsumetype", "byisout", "dtmakedate", "manyouno",
					"myzj", "name1", "zjno1", "name2", "zjno2", "name3",
					"zjno3" };
			tablename = new String[] { "Stssoldtickettablist",
					"Stssoldtickettab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// �۳���Ʊ�ӱ���ʷ��Stssoldticketsubtablist
			reportslistDao.deleteReportListDates("Stssoldticketsubtablist",
					"isalesvoucherid", isalesvoucherid);
			colsname = new String[] { "szsoldticketsubid", "szsoldticketid",
					"isalesvoucherdetailsid", "isalesvoucherid",
					"iticketstationid", "igardengateid", "iscenicid",
					"icrowdkindid", "itickettypeid", "iztickettypeid",
					"tripid", "bychecktype", "dtlastcheckdate",
					"bylastcheckdir", "byconsumemode", "ipasstimes",
					"msingletimes", "ipassedtimes", "mlimitconsume",
					"msingleconsume", "mconsumed", "ipartitionsign",
					"iversion", "dtbegindate", "dtenddate", "szwicketsetinfo",
					"byisout", "isvalid", "dtmakedate" };
			tablename = new String[] { "Stssoldticketsubtablist",
					"Stssoldticketsubtab" };
			reportslistDao.addReportListDates(colsname, tablename,
					"isalesvoucherid", isalesvoucherid);

			// ɾ��ԭʼ����
			reportslistDao.deleteReportListDates("Ticketchecklist",
					"isalesvoucherid", isalesvoucherid);

			List nolist = reportslistDao.querySzsalesvoucherno(isalesvoucherid);
			if (nolist != null && nolist.size() >= 1) {
				for (int j = 0; j < nolist.size(); j++) {
					String saleno = nolist.get(j).toString();
					reportslistDao.deleteSalePrint("Ticketprintlist",
							"szsalesvoucherno", saleno);
				}
			}
			reportslistDao.deleteReportListDates("Stssoldticketattesttab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stssoldticketsubtab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stssoldtickettab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stscomticketsalesdetailstab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stssalesvoucherdetailstab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stssalessettlementtab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Stssalesvouchertab",
					"isalesvoucherid", isalesvoucherid);
			reportslistDao.deleteReportListDates("Ticketchecklist",
					"isalesvoucherid", isalesvoucherid);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��ʷ������쳣");
		}

		return 0;
	}

}
