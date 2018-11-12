package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.report.sales.Rwspersonalday;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;

public class ReportDataDAO extends GenericDao implements IReportDataDAO {

	/**
	 * ����ʱ���ѯ���������ս����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date
	 * @return return:List Date:2011-11-30
	 */
	public List updateOrQueryProviderByDate(String type, String date) {
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rproviderdaytab");// �ս�
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rprovidermonthtab");// �½�
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rprovideryeartab");// ���
		}

		List list = new ArrayList();
		String hsql = "";
		String hsql2 = "";

		if ("1".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid, pro.szscenicname as szscenicname,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sale.bysalesvouchertype,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums  from Stssalesvouchertab sale,Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl where substr(sale.dtmakedate, 1, 10) = '"
					+ date

					+ "' and pro.iscenicid = sale.iscenicid and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid   and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid  group by sale.iscenicid,pro.szscenicname,sm.isettlementid,sale.bysalesvouchertype";

		} else if ("2".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid, pro.szscenicname as szscenicname,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sale.bysalesvouchertype,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl where substr(sale.dtmakedate, 1, 4) = '"
					+ date.substring(1, 4)
					+ "' and substr(sale.dtmakedate, 6, 2) = '"
					+ date.substring(5, 7)
					+ "'  and pro.iscenicid = sale.iscenicid and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid group by sale.iscenicid,pro.szscenicname,sm.isettlementid,sale.bysalesvouchertype";
		} else if ("3".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid, pro.szscenicname as szscenicname,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid ,sale.bysalesvouchertype,nvl(sum(sl.mderatemoney),0),nvl(sum(sl.ideratenums),0) as ideratenums as mderatemoney from Stssalesvouchertab sale,Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl where substr(sale.dtmakedate, 1, 4) = '"
					+ date.substring(1, 4)
					+ "'  and pro.iscenicid = sale.iscenicid and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid group by sale.iscenicid,pro.szscenicname,sm.isettlementid,sale.bysalesvouchertype";
		}

		String hsql3 = "";
		if ("1".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab  pro where m.orda='"
					+ date
					+ "' and  m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,pro.szscenicname ";
		} else if ("2".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab  pro where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(m.orda,6,2)='"
					+ date.substring(5, 7)
					+ "' and  m.ortp='02' and m.tpfs='02'  and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,pro.szscenicname ";
		} else if ("3".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab  pro where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and  m.ortp='02' and m.tpfs='02'  and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,pro.szscenicname ";
		}

		list = this.find(hsql);
		List list3 = this.find(hsql3);

		if (list != null && list.size() >= 1) {
			if (list3 != null && list3.size() >= 1) {
				for (int i = 0; i < list3.size(); i++) {
					list.add(list3.get(i));
				}
			}
			return list;
		} else {
			return list3;
		}

	}

	/**
	 * ��ȡ�������¡��걨������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQueryProviderByType(String type, String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rprovidermonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rprovideryeartab");
		}

		if ("2".equals(type)) {
			hsql.append(" select iscenicid as iscenicid,szscenicname as szscenicname,sum(mont) as mont,sum(zfmont) as zfmont,sum(yhmont) as yhmont,notea,noteb,notec,noted,nvl(sum(duf),0)  as duf,nvl(sum(isf),0)  as isf from Rproviderdaytab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid ,szscenicname,notea,noteb,notec,noted ");
		} else if ("3".equals(type)) {
			hsql.append(" select iscenicid as iscenicid,szscenicname as szscenicname,sum(mont) as mont,sum(zfmont) as zfmont,sum(yhmont) as yhmont,notea,noteb,notec,noted,nvl(sum(duf),0)  as duf,nvl(sum(isf),0)  as isf from Rprovidermonthtab where ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid ,szscenicname,notea,noteb,notec,noted");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ����ʱ���ѯ��������������ϸ�б� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-8
	 */
	public List updateOrQueryProviderList(String type, String date) {

		if ("1".equals(type)) {
			this.deleteDatesByTable(date, "1", "Rproviderlisttab");
		} else if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rproviderlisttab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rproviderlisttab");
		}

		List list = new ArrayList();
		String hsql = "";
		// ��Ʊ��
		if ("1".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid,pro.szscenicname as szscenicname, sl.itickettypeid as itickettypeid, kind.icrowdkindid  as icrowdkindid ,sl.mactualsaleprice as pric,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid as isettlementid,sum(sl.iamount) as tdnum,sale.bysalesvouchertype,nvl(sum(sl.ideratenums),0) as ideratenums,nvl(sum(sl.mderatemoney),0)  as mderatemoney from Stssalesvouchertab sale, Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl, Edmcrowdkindpricetab kind  where substr(sale.dtmakedate, 1, 10) = '"
					+ date

					+ "'  and pro.iscenicid = sale.iscenicid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid  and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid and sl.icrowdkindpriceid = kind.icrowdkindpriceid  group by sale.iscenicid, pro.szscenicname,  sl.itickettypeid,kind.icrowdkindid,  sl.mactualsaleprice, sm.isettlementid,sale.bysalesvouchertype ";

		} else if ("2".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid,pro.szscenicname as szscenicname, sl.itickettypeid as itickettypeid, kind.icrowdkindid  as icrowdkindid ,sl.mactualsaleprice as pric,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sum(sl.iamount) as tdnum,sale.bysalesvouchertype,nvl(sum(sl.ideratenums),0) as ideratenums,nvl(sum(sl.mderatemoney),0)  as mderatemoney from Stssalesvouchertab sale, Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl, Edmcrowdkindpricetab kind where substr(sale.dtmakedate, 1, 4) = '"
					+ date.substring(0,4)

					+ "'  and substr(sale.dtmakedate, 6, 2) = '"
					+ date.substring(5, 7)

					+ "'  and pro.iscenicid = sale.iscenicid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid  and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid  and sl.icrowdkindpriceid = kind.icrowdkindpriceid  group by sale.iscenicid, pro.szscenicname,  sl.itickettypeid, kind.icrowdkindid, sl.mactualsaleprice, sm.isettlementid,sale.bysalesvouchertype ";

		} else if ("3".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid,pro.szscenicname as szscenicname, sl.itickettypeid as itickettypeid, kind.icrowdkindid  as icrowdkindid ,sl.mactualsaleprice as pric,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sum(sl.iamount) as tdnum,sale.bysalesvouchertype,nvl(sum(sl.ideratenums),0) as ideratenums,nvl(sum(sl.mderatemoney),0)  as mderatemoney from Stssalesvouchertab sale, Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl, Edmcrowdkindpricetab kind where substr(sale.dtmakedate, 1, 4) = '"
					+ date.substring(0, 4)

					+ "'  and pro.iscenicid = sale.iscenicid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid  and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid and sl.icrowdkindpriceid = kind.icrowdkindpriceid  group by sale.iscenicid, pro.szscenicname,  sl.itickettypeid, kind.icrowdkindid, sl.mactualsaleprice, sm.isettlementid ,sale.bysalesvouchertype";

		}

		list = this.find(hsql);

		String hsql3 = "";
		if ("1".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,yl.itickettypeid as itickettypeid,yl.icrowdkindid as icrowdkindid,yl.pric as pric,0 as numb,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,sum(yl.numb) as tdnum,'02' as fs  from MOrder m,YOrderlist yl, Esbscenicareatab pro where m.orda='"
					+ date
					+ "' and m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,yl.itickettypeid ,yl.icrowdkindid, pro.szscenicname,yl.pric ";
		} else if ("2".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,yl.itickettypeid as itickettypeid,yl.icrowdkindid as icrowdkindid,yl.pric as pric,0 as numb,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,sum(yl.numb) as tdnum,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab pro where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(m.orda,6,2)='"
					+ date.substring(5, 7)
					+ "' and m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,yl.itickettypeid ,yl.icrowdkindid, pro.szscenicname,yl.pric ";
		} else if ("3".equals(type)) {
			hsql3 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,yl.itickettypeid as itickettypeid,yl.icrowdkindid as icrowdkindid,yl.pric as pric,0 as numb,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,sum(yl.numb) as tdnum,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab pro where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,yl.itickettypeid ,yl.icrowdkindid, pro.szscenicname,yl.pric ";
		}

		List list3 = this.find(hsql3);

		if (list != null && list.size() >= 1) {
			if (list3 != null && list3.size() >= 1) {
				for (int i = 0; i < list3.size(); i++) {
					list.add(list3.get(i));
				}
			}
			return list;
		} else {
			return list3;
		}

	}

	/**
	 * ������������ϸ�¡����б��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQueryProviderListByType(String type, String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rproviderlisttab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rproviderlisttab");
		}

		if ("2".equals(type)) {
			hsql.append("select iscenicid,szscenicname,itickettypeid,ttypename,icrowdkindid,szcrowdkindname,pric,protype,protypename,sum(numb) as numb,sum(mont),sum(dua),sum(dub),sum(isa),notea,noteb,nvl(sum(duf),0)  as duf,nvl(sum(isf),0)  as isf from Rproviderlisttab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' and titype='01' group by iscenicid,szscenicname,itickettypeid,ttypename,icrowdkindid,szcrowdkindname,pric,protype,protypename,notea,noteb");
		} else if ("3".equals(type)) {
			hsql.append("select iscenicid,szscenicname,itickettypeid,ttypename,icrowdkindid,szcrowdkindname,pric,protype,protypename,sum(numb) as numb,sum(mont),sum(dua),sum(dub),sum(isa),notea,noteb,nvl(sum(duf),0)  as duf,nvl(sum(isf),0)  as isf from Rproviderlisttab where ryear='"
					+ date.substring(0, 4)
					+ "' and titype='02' group by iscenicid,szscenicname,itickettypeid,ttypename,icrowdkindid,szcrowdkindname,pric,protype,protypename,notea,noteb");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ����ʱ���ѯ����Դ������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-1
	 */
	public List updateOrQueryRegionByDate(String type, String date) {
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rregiondaytab");
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rregionmonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rregionyeartab");
		}
		List list = new ArrayList();

		String hsql = "";
		if ("1".equals(type)) {// ��
			hsql = " select t.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,t.iregionalid as iregionalid,re.szregionalname as szregionalname,sum(list.numb) as counts from MOrder m,TOrder t,Galsourceregiontab re,Esbscenicareatab pro,TOrderlist list where m.orda='"
					+ date
					+ "' and m.ddzt in('02','01','11') and  t.id.orid = m.orid and list.id.orid = t.id.orid and  re.iregionalid = t.iregionalid  and t.id.iscenicid = pro.iscenicid group by t.id.iscenicid,pro.szscenicname,t.iregionalid,re.szregionalname";
		} else if ("2".equals(type)) {// ��
			hsql = " select t.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,t.iregionalid as iregionalid,re.szregionalname as szregionalname,sum(list.numb) as counts from MOrder m,TOrder t,Galsourceregiontab re,Esbscenicareatab pro,TOrderlist list where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(m.orda,6,2)='"
					+ date.substring(5, 7)
					+ "' and m.ddzt in('02','01','11') and  t.id.orid = m.orid and list.id.orid = t.id.orid and  re.iregionalid = t.iregionalid  and t.id.iscenicid = pro.iscenicid group by t.id.iscenicid,pro.szscenicname,t.iregionalid,re.szregionalname";

		} else if ("3".equals(type)) {// ��
			hsql = " select t.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,t.iregionalid as iregionalid,re.szregionalname as szregionalname,sum(list.numb) as counts from MOrder m,TOrder t,Galsourceregiontab re,Esbscenicareatab pro,TOrderlist list where substr(m.orda,1,4)='"
					+ date.substring(0, 4)
					+ "' and m.ddzt in('02','01','11') and  t.id.orid = m.orid and list.id.orid = t.id.orid and  re.iregionalid = t.iregionalid  and t.id.iscenicid = pro.iscenicid group by t.id.iscenicid,pro.szscenicname,t.iregionalid,re.szregionalname";

		}
		list = this.find(hsql);
		return list;
	}

	/**
	 * ��ѯ����Դ���¡��걨������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQueryRegionByType(String type, String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rregionmonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rregionyeartab");
		}

		if ("2".equals(type)) {
			hsql.append("select iscenicid,szscenicname,iregionalid,szregionalname,sum(numb) from Rregiondaytab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid,szscenicname,iregionalid,szregionalname");
		} else if ("3".equals(type)) {
			hsql.append("select iscenicid,szscenicname,iregionalid,szregionalname,sum(numb) from Rregionmonthtab where ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid,szscenicname,iregionalid,szregionalname");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ����ʱ���ѯ���ο��������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-1
	 */
	public List updateOrQueryCustomCountByDate(String type, String date) {
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rcustomdaytab");
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rcustommonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rcustomyeartab");
		}

		List list = new ArrayList();

		String hsql = "";
		if ("1".equals(type)) {// ��
			// orfl ��ʾ�������࣬02��ʾ��Ʊ
			hsql = " select tor.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,sum(lis.numb) as numbs from TOrder tor,TOrderlist lis,Esbscenicareatab pro  where tor.dtstartdate='"
					+ date
					+ "' and tor.ddzt in('01','02','11')  and tor.orfl='02' and  tor.id.orid = lis.id.orid  and  tor.id.iscenicid=pro.iscenicid and ( lis.numb >0) group by tor.id.iscenicid ,pro.szscenicname ";
		} else if ("2".equals(type)) {// ��
			hsql = " select tor.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,sum(lis.numb) as numbs from TOrder tor,TOrderlist lis,Esbscenicareatab pro  where substr(tor.dtstartdate,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(tor.dtstartdate,6,2)='"
					+ date.substring(5, 7)
					+ "' and tor.ddzt in('01','02','11')  and tor.orfl='02' and  tor.id.orid = lis.id.orid  and  tor.id.iscenicid=pro.iscenicid and ( lis.numb >0) group by tor.id.iscenicid ,pro.szscenicname ";

		} else if ("3".equals(type)) {// ��
			hsql = " select tor.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,sum(lis.numb) as numbs from TOrder tor,TOrderlist lis,Esbscenicareatab pro  where substr(tor.dtstartdate,1,4)='"
					+ date.substring(0, 4)
					+ "' and tor.ddzt in('01','02','11')  and tor.orfl='02' and tor.id.orid = lis.id.orid  and  tor.id.iscenicid=pro.iscenicid and ( lis.numb >0) group by tor.id.iscenicid ,pro.szscenicname ";

		}
		list = this.find(hsql);
		return list;
	}

	/**
	 * ��ѯ���ο������¡��걨������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQueryCustomCountByType(String type, String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rcustommonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rcustomyeartab");
		}

		if ("2".equals(type)) {
			hsql.append("select iscenicid,szscenicname,sum(numb)  from Rcustomdaytab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid,szscenicname");
		} else if ("3".equals(type)) {
			hsql.append("select iscenicid,szscenicname,sum(numb)  from Rcustommonthtab where ryear='"
					+ date.substring(0, 4)
					+ "' group by iscenicid,szscenicname");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ����ʱ���ѯ����ƱԱ��Ʊ\��Ʊ���ݣ������ձ��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param cztype������ʽ
	 *            ��01��Ʊ02��Ʊ��
	 * @param date
	 * @return return:List Date:2011-12-1
	 */
	public List updateOrQuerySaleCountByDate(String type, String cztype,
			String date) {

		if ("1".equals(type)) {
			this.deleteDatesByEmpTable(date, "1", cztype, "Rsalecounttab");
		} else if ("2".equals(type)) {
			this.deleteDatesByEmpTable(date, "2", cztype, "Rsalecounttab");
		} else if ("3".equals(type)) {
			this.deleteDatesByEmpTable(date, "3", cztype, "Rsalecounttab");
		}

		List list = new ArrayList();

		String hsql = "";
		if ("1".equals(type)) {// ��
			hsql = "select cop.icompanyinfoid as icompanyinfoid,cop.szcompanyname as szcompanyname,js.isettlementid as zffs,emp.empid as empid,emp.szemployeename as empname ,sum(salde.meventmoney) as mont,salde.itickettypeid as itickettypeid,sum(salde.iamount) as nums,salde.mactualsaleprice as mactualsaleprice,pri.icrowdkindid as icrowdkindid,sum(salde.mhandcharge) as mhandcharge, salde.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.ibusinessid as ibusinessid,sale.usid as usid,sale.iregionalid as iregionalid,sum(salde.iticketnum) as iticketplayer,nvl(sum(salde.ideratenums),0) as ideratenums,nvl(sum(salde.mderatemoney),0) as mderatemoney from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop,Stssalessettlementtab js,Stssalesvoucherdetailstab salde,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' and sale.bysalesvouchertype='"
					+ cztype
					+ "' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.id.isalesvoucherid = js.id.isalesvoucherid and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = js.id.iticketstationid and sale.id.iticketstationid = salde.id.iticketstationid  and salde.icrowdkindpriceid = pri.icrowdkindpriceid  group by cop.icompanyinfoid,cop.szcompanyname,emp.empid,emp.szemployeename,js.isettlementid,salde.itickettypeid,salde.mactualsaleprice, pri.icrowdkindid ,salde.id.iticketstationid,sale.iticketwinid,sale.ibusinessid,sale.usid,sale.iregionalid";
		} else if ("2".equals(type)) {// ��
			hsql = "select cop.icompanyinfoid as icompanyinfoid,cop.szcompanyname as szcompanyname,js.isettlementid as zffs,emp.empid as empid,emp.szemployeename as empname ,sum(salde.meventmoney) as mont,salde.itickettypeid as itickettypeid,sum(salde.itotalnumber) as nums,salde.mactualsaleprice as mactualsaleprice,pri.icrowdkindid as icrowdkindid,sum(salde.mhandcharge) as mhandcharge, salde.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.ibusinessid as ibusinessid,sale.usid as usid,sale.iregionalid as iregionalid,sum(salde.iticketnum) as iticketplayer,nvl(sum(salde.ideratenums),0) as ideratenums,nvl(sum(salde.mderatemoney),0) as mderatemoney from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop,Stssalessettlementtab js,Stssalesvoucherdetailstab salde,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(sale.dtmakedate,6,2)='"
					+ date.substring(5, 7)
					+ "' and sale.bysalesvouchertype='"
					+ cztype
					+ "' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.id.isalesvoucherid = js.id.isalesvoucherid and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = js.id.iticketstationid and sale.id.iticketstationid = salde.id.iticketstationid  and salde.icrowdkindpriceid = pri.icrowdkindpriceid  group by cop.icompanyinfoid,cop.szcompanyname,emp.empid,emp.szemployeename,js.isettlementid,salde.itickettypeid,salde.mactualsaleprice, pri.icrowdkindid,salde.id.iticketstationid ,sale.iticketwinid,sale.ibusinessid,sale.usid,sale.iregionalid  ";

		} else if ("3".equals(type)) {// ��
			hsql = "select cop.icompanyinfoid as icompanyinfoid,cop.szcompanyname as szcompanyname,js.isettlementid as zffs,emp.empid as empid,emp.szemployeename as empname ,sum(salde.meventmoney) as mont,salde.itickettypeid as itickettypeid,sum(salde.itotalnumber) as nums,salde.mactualsaleprice as mactualsaleprice,pri.icrowdkindid as icrowdkindid,sum(salde.mhandcharge) as mhandcharge, salde.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.ibusinessid as ibusinessid,sale.usid as usid,sale.iregionalid as iregionalid,,sum(salde.iticketnum) as iticketplayer,nvl(sum(salde.ideratenums),0) as ideratenums,nvl(sum(salde.mderatemoney),0) as mderatemoney from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop,Stssalessettlementtab js,Stssalesvoucherdetailstab salde,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "' and sale.bysalesvouchertype='"
					+ cztype
					+ "' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.id.isalesvoucherid = js.id.isalesvoucherid and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = js.id.iticketstationid and sale.id.iticketstationid = salde.id.iticketstationid  and salde.icrowdkindpriceid = pri.icrowdkindpriceid  group by cop.icompanyinfoid,cop.szcompanyname,emp.empid,emp.szemployeename,js.isettlementid,salde.itickettypeid,salde.mactualsaleprice, pri.icrowdkindid,salde.id.iticketstationid ,sale.iticketwinid,sale.ibusinessid,sale.usid,sale.iregionalid ";

		}
		System.out.println("------->>>>" + hsql);
		list = this.find(hsql);

		return list;
	}

	/**
	 * ��ѯ����ƱԱ��Ʊ\��Ʊ�¡��걨������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param cztype������ʽ
	 *            ��01��Ʊ02��Ʊ��
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQuerySaleCountByType(String type, String cztype,
			String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();

		if ("2".equals(type)) {// ��
			this.deleteDatesByEmpTable(date, "2", cztype, "Rsalecounttab");
		} else if ("3".equals(type)) {// ��
			this.deleteDatesByEmpTable(date, "3", cztype, "Rsalecounttab");
		}

		if ("2".equals(type)) {
			hsql.append("select iscenicid,szscenicname,skfs,skfsname,empid,szemployeename,itickettypeid,sztickettypename,tickettype,sum(numb),sum(mont),isa,noteb,dua,sum(dub),isb,notec,isc,isd,sum(ise),isf,notee,notef,nvl(sum(due),0) as due,nvl(sum(duf),0) as duf from Rsalecounttab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' and notea='"
					+ cztype
					+ "' and titype='01' group by iscenicid,szscenicname,empid,szemployeename,itickettypeid,sztickettypename,tickettype,skfs,skfsname,isa,noteb,dua,isb,notec,isc,isd,isf,notee,notef ");
		} else if ("3".equals(type)) {
			hsql.append("select iscenicid,szscenicname,skfs,skfsname,empid,szemployeename,itickettypeid,sztickettypename,tickettype,sum(numb),sum(mont),isa,noteb,dua,sum(dub),isb,notec,isc,isd,sum(ise),isf,notee,notef,nvl(sum(due),0) as due,nvl(sum(duf),0) as duf from Rsalecounttab where ryear='"
					+ date.substring(0, 4)
					+ "' and notea='"
					+ cztype
					+ "' and titype='02' group by iscenicid,szscenicname,empid,szemployeename,itickettypeid,sztickettypename,tickettype,skfs,skfsname,isa,noteb,dua,isb,notec,isc,isd,isf,notee,notef ");
		}
		list = this.find(hsql.toString());
		System.out.println("----emp moth sale report:" + hsql);
		return list;
	}

	/**
	 * ����ʱ���ѯ����������Ʊ���ݣ������ձ��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-1
	 */
	public List updateOrQueryLxsSaleByDate(String type, String date) {
		if ("1".equals(type)) {
			this.deleteDatesByTable(date, "1", "Rcustomgrouptab");
		} else if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rcustomgrouptab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rcustomgrouptab");
		}

		List list = new ArrayList();
		String hsql = "";
		if ("1".equals(type)) {// ��
			hsql = "select new map(sale.usid as usid,ti.bycategorytype as bycategorytype,sum(salde.iamount) as numb,sum(salde.meventmoney) as mont,nvl(sum(salde.mderatemoney), 0) as mderatemoney,nvl(sum(salde.ideratenums), 0) as ideratenums,price.icrowdkindid as icrowdkindid,"
					+ "salde.itickettypeid as itickettypeid,sale.iscenicid as iscenicid,salde.mactualsaleprice as mactualsaleprice,saletype.isettlementid as isettlementid,sale.bysalesvouchertype  as bysalesvouchertype) from Stssalesvouchertab  sale,Stssalesvoucherdetailstab salde,Custom  cus,Edmtickettypetab  ti,Edmcrowdkindpricetab      price,"
					+ "Stssalessettlementtab saletype where sale.id.isalesvoucherid = saletype.id.isalesvoucherid and sale.id.iticketstationid = saletype.id.iticketstationid and price.icrowdkindpriceid = salde.icrowdkindpriceid and cus.ibusinessid != 1 and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid and cus.usid = sale.usid and salde.itickettypeid = ti.itickettypeid"
					+ " and substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' group by sale.usid,ti.bycategorytype,saletype.isettlementid,salde.mactualsaleprice, sale.iscenicid,salde.itickettypeid,price.icrowdkindid,sale.bysalesvouchertype";
		} else if ("2".equals(type)) {// ��
			hsql = "select new map(tickettype as tickettype,ttypename as ttypename,nvl(chiefuser,' ') as chiefuser,nvl(sonuser,' ') as sonuser,nvl(grandsonuser,' ') as grandsonuser,nvl(sum(numb), 0) as numb,nvl(sum(mont), 0) as mont,nvl(sum(duf), 0) as duf,nvl(sum(isf), 0) as isf,isb as isb,isc as isc,isd as isd,dub as dub,notea as notea,noteb as noteb)  from Rcustomgrouptab where ryear = '"
					+ date.substring(0, 4)
					+ "' and rmonth = '"
					+ date.substring(5, 7)
					+ "' and titype='01' group by chiefuser,sonuser,grandsonuser,isb,isc,isd,dub,notea,noteb,tickettype,ttypename)";
		} else if ("3".equals(type)) {// ��
			hsql = "select new map(tickettype as tickettype,ttypename as ttypename,nvl(chiefuser,' ') as chiefuser,nvl(sonuser,' ') as sonuser,nvl(grandsonuser,' ') as grandsonuser,nvl(sum(numb), 0) as numb,nvl(sum(mont), 0) as mont,nvl(sum(duf), 0) as duf,nvl(sum(isf), 0) as isf,isb as isb,isc as isc,isd as isd,dub as dub,notea as notea,noteb as noteb)  from Rcustomgrouptab where ryear = '"
					+ date.substring(0, 4)
					+ "' and titype='02' group by chiefuser,sonuser,grandsonuser,isb,isc,isd,dub,notea,noteb,tickettype,ttypename)";
		}
		list = this.find(hsql);
		return list;
	}

	/**
	 * ��ѯ���������Ʊ�¡��걨������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-16
	 */
	public List updateOrQueryLxsSaleByType(String type, String date) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();

		if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rcustomgrouptab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rcustomgrouptab");
		}

		if ("2".equals(type)) {
			hsql.append("select tickettype,ttypename,chiefuser,sonuser,grandsonuser,sum(numb),sum(mont),nvl(sum(isf),0) as  isf,nvl(sum(duf),0) as  duf from Rcustomgrouptab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' and titype='01' group by tickettype,ttypename,chiefuser,sonuser,grandsonuser");
		} else if ("3".equals(type)) {
			hsql.append("select tickettype,ttypename,chiefuser,sonuser,grandsonuser,sum(numb),sum(mont),nvl(sum(isf),0) as  isf,nvl(sum(duf),0) from Rcustomgrouptab where  ryear='"
					+ date.substring(0, 4)
					+ "' and titype='02' group by tickettype,ttypename,chiefuser,sonuser,grandsonuser");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ��Ʊ���������ձ��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type��������
	 *            ��1�գ�2�£�3�꣩
	 * @param date����
	 * @return return:List Date:2011-12-21
	 */
	public List updateOrquerySale(String type, String date) {
		List list = new ArrayList();
		if ("1".equals(type)) {
			this.deleteDatesByTable(date, "1", "Rsaletickettab");
		} else if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rsaletickettab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rsaletickettab");
		}
		String hsql = "";
		if ("1".equals(type)) {
			hsql = "select sale.iscenicid as iscenicid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sl.id.iticketstationid as iticketstationid,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' and sale.bysalesvouchertype='01'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid  and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.iscenicid,sl.itickettypeid, sl.mactualsaleprice,pri.icrowdkindid,sl.id.iticketstationid";
		} else if ("2".equals(type)) {

			hsql = "select sale.iscenicid as iscenicid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid, sl.id.iticketstationid as iticketstationid,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "' and substr(sale.dtmakedate,6,2)='"
					+ date.substring(5, 7)
					+ "' and sale.bysalesvouchertype='01'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.iscenicid,sl.itickettypeid, sl.mactualsaleprice,pri.icrowdkindid,sl.id.iticketstationid";
		} else if ("3".equals(type)) {

			hsql = "select sale.iscenicid as iscenicid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid, sl.id.iticketstationid as iticketstationid,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "' and sale.bysalesvouchertype='01'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.iscenicid,sl.itickettypeid, sl.mactualsaleprice,pri.icrowdkindid,sl.id.iticketstationid";
		}

		list = this.find(hsql);

		return list;
	}

	/**
	 * ��Ʊ�¡��걨�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type��������
	 *            ��2�£�3�꣩
	 * @param date����
	 * @return return:List Date:2011-12-21
	 */
	public List updateOrquerySalebyType(String type, String date) {
		List list = new ArrayList();

		if ("2".equals(type)) {
			this.deleteDatesByTable(date, "2", "Rsaletickettab");
		} else if ("3".equals(type)) {
			this.deleteDatesByTable(date, "3", "Rsaletickettab");
		}
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			hsql.append("select iscenicid,szscenicname,itickettypeid,sztickettypename,sum(numb),sum(mont),isa,notea,dua,isb,noteb,nvl(sum(isf),0) as isf,nvl(sum(duf),0) as duf from Rsaletickettab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' and titype='01' group by iscenicid,szscenicname,itickettypeid,sztickettypename,isa,notea,dua,isb,noteb  ");
		} else if ("3".equals(type)) {
			hsql.append("select iscenicid,szscenicname,itickettypeid,sztickettypename,sum(numb),sum(mont),isa,notea,dua,isb,noteb,nvl(sum(isf),0) as isf,nvl(sum(duf),0) as duf from Rsaletickettab where  ryear='"
					+ date.substring(0, 4)
					+ "' and titype='02' group by iscenicid,szscenicname,itickettypeid,sztickettypename,isa,notea,dua,isb,noteb ");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ���ۻ��ܣ�����Ʒ��������ɢ�ͣ����壨ÿ��) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-30
	 */
	public List updateOrQueryTicketSale(String type, String date) {
		List list = new ArrayList();
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rticketdaytab");
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rticketmonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rticketyeartab");
		}
		StringBuffer hsql = new StringBuffer();
		if ("1".equals(type)) {
			hsql.append(" select sale.ibusinessid as ibusinessid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum, sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
					+ date
					+ "'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.ibusinessid,sl.itickettypeid,sl.mactualsaleprice, pri.icrowdkindid,sale.bysalesvouchertype,sl.icrowdkindpriceid,pri.mactualsaleprice");
		} else if ("2".equals(type)) {

			hsql.append(" select sale.ibusinessid as ibusinessid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum, sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where  substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "'  and substr(sale.dtmakedate,6,2)='"
					+ date.substring(5, 7)
					+ "'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.ibusinessid,sl.itickettypeid,sl.mactualsaleprice, pri.icrowdkindid,sale.bysalesvouchertype,sl.icrowdkindpriceid,pri.mactualsaleprice");

		} else if ("3".equals(type)) {
			hsql.append(" select sale.ibusinessid as ibusinessid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum, sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.ibusinessid,sl.itickettypeid,sl.mactualsaleprice, pri.icrowdkindid,sale.bysalesvouchertype,sl.icrowdkindpriceid,pri.mactualsaleprice");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ���۱����¡��걨�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-30
	 */
	public List updateOrQueryTicketSaleByType(String type, String date) {
		List list = new ArrayList();
		if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rticketmonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rticketyeartab");
		}
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			hsql.append(" select r.itickettypeid,r.sztickettypename,r.tickettype,r.isa,r.notea,sum(r.numb),sum(r.mont),isb,noteb,dua,notec,isc,isd,nvl(sum(isf),0) as  isf,nvl(sum(duf),0) as  duf  from Rticketdaytab r where r.rmonth='"
					+ date.substring(5, 7)
					+ "' and r.ryear='"
					+ date.substring(0, 4)
					+ "' group by r.itickettypeid,r.sztickettypename,r.tickettype,r.isa,r.notea,isb,noteb,dua,notec,isc,isd");
		} else if ("3".equals(type)) {
			hsql.append(" select r.itickettypeid,r.sztickettypename,r.tickettype,r.isa,r.notea,sum(r.numb),sum(r.mont),isb,noteb,dua,notec,isc,isd,nvl(sum(isf),0) as  isf,nvl(sum(duf),0) as  duf  from Rticketmonthtab r where r.ryear='"
					+ date.substring(0, 4)
					+ "' group by r.itickettypeid,r.sztickettypename,r.tickettype,r.isa,r.notea,isb,noteb,dua,notec,isc,isd ");
		}
		list = this.find(hsql.toString());

		return list;
	}

	/**
	 * ǰ̨�������������۱����������ձ��� Describe:
	 * 
	 * @auth:���ۺ��˶� ���ÿ�������������
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-31
	 */
	public List updateOrQueryCusGroupSale(String type, String date) {
		List list = new ArrayList();
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rcgroupsaledaytab");
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rcgroupsalemonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rcgroupsaleyeartab");
		}
		StringBuffer hsql = new StringBuffer();
		StringBuffer hsql2 = new StringBuffer();
		if ("1".equals(type)) {
			hsql.append(" select sale.usid as usid ,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum,  sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid as ibusinessid,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums,st.isettlementid as zffs,nvl(sum(sl.mhandcharge),0) as mhandcharge,sale.iscenicid as iscenicid from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Stssalessettlementtab st,Custom cust,Edmcrowdkindpricetab pri where  substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' and sale.bysalesvouchertype in ('01','02','04')  and sale.ibusinessid!=1  and cust.usid = sale.usid  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid  and sale.id.isalesvoucherid = st.id.isalesvoucherid and sale.id.iticketstationid = st.id.iticketstationid  and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.usid,sl.itickettypeid,sl.mactualsaleprice,pri.icrowdkindid,sale.bysalesvouchertype,sale.ibusinessid,sl.icrowdkindpriceid,pri.mactualsaleprice,st.isettlementid,sale.iscenicid");
			hsql2.append("select y.usid as usid,yl.itickettypeid as iztickettypeid,0 as iticketnum,0 as mont,yl.pric as mactualsaleprice,yl.icrowdkindid as icrowdkindid,'02' as bysalesvouchertype,c.ibusinessid as ibusinessid,yl.icrowdkindpriceid as icrowdkindpriceid,yl.pric as pric,0 as mderatemoney,0 as ideratenums,y.zffs as zffs,sum(yl.mhandcharge) as mhandcharge,yl.id.iscenicid as iscenicid  from MOrder y ,YOrderlist yl,Custom c where y.orda='"
					+ date
					+ "' and ddzt='06' and y.orid=yl.id.orid and y.usid=c.usid and yl.mhandcharge>0  and y.ortp='02' and y.tpfs='02'  group by y.usid,yl.itickettypeid,yl.pric,yl.icrowdkindid,c.ibusinessid,yl.icrowdkindpriceid,y.zffs,yl.id.iscenicid");
		} else if ("2".equals(type)) {

			hsql.append(" select sale.usid as usid ,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum,  sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid as ibusinessid,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums,st.isettlementid as zffs,nvl(sum(sl.mhandcharge),0) as mhandcharge,sale.iscenicid as iscenicid from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Stssalessettlementtab st,Custom cust,Edmcrowdkindpricetab pri where  substr(sale.dtmakedate,1,7)='"
					+ date.substring(1, 7)
					+ "' and sale.bysalesvouchertype in ('01','02','04')  and sale.ibusinessid!=1  and cust.usid = sale.usid  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid  and sale.id.isalesvoucherid = st.id.isalesvoucherid and sale.id.iticketstationid = st.id.iticketstationid  and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.usid,sl.itickettypeid,sl.mactualsaleprice,pri.icrowdkindid,sale.bysalesvouchertype,sale.ibusinessid,sl.icrowdkindpriceid,pri.mactualsaleprice,st.isettlementid,sale.iscenicid");
			hsql2.append("select y.usid as usid,yl.itickettypeid as iztickettypeid,0 as iticketnum,0 as mont,yl.pric as mactualsaleprice,yl.icrowdkindid as icrowdkindid,'02' as bysalesvouchertype,c.ibusinessid as ibusinessid,yl.icrowdkindpriceid as icrowdkindpriceid,yl.pric as pric,0 as mderatemoney,0 as ideratenums,y.zffs as zffs,sum(yl.mhandcharge) as mhandcharge,yl.id.iscenicid as iscenicid  from MOrder y ,YOrderlist yl,Custom c where substr(y.orda,1,7)='"
					+ date.substring(1, 7)
					+ "' and ddzt='06' and y.orid=yl.id.orid and y.usid=c.usid and yl.mhandcharge>0  and y.ortp='02'  and y.tpfs='02' group by y.usid,yl.itickettypeid,yl.pric,yl.icrowdkindid,c.ibusinessid,yl.icrowdkindpriceid,y.zffs,yl.id.iscenicid");

		} else if ("3".equals(type)) {

			hsql.append(" select sale.usid as usid ,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum,  sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid as ibusinessid,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums,st.isettlementid as zffs,nvl(sum(sl.mhandcharge),0) as mhandcharge,sale.iscenicid as iscenicid from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Stssalessettlementtab st,Custom cust,Edmcrowdkindpricetab pri where   substr(sale.dtmakedate,1,4)='"
					+ date.substring(0, 4)
					+ "' and sale.bysalesvouchertype in ('01','02','04')  and sale.ibusinessid!=1  and cust.usid = sale.usid  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid  and sale.id.isalesvoucherid = st.id.isalesvoucherid and sale.id.iticketstationid = st.id.iticketstationid  and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.usid,sl.itickettypeid,sl.mactualsaleprice,pri.icrowdkindid,sale.bysalesvouchertype,sale.ibusinessid,sl.icrowdkindpriceid,pri.mactualsaleprice,st.isettlementid,sale.iscenicid");
			hsql2.append("select y.usid as usid,yl.itickettypeid as iztickettypeid,0 as iticketnum,0 as mont,yl.pric as mactualsaleprice,yl.icrowdkindid as icrowdkindid,'02' as bysalesvouchertype,c.ibusinessid as ibusinessid,yl.icrowdkindpriceid as icrowdkindpriceid,yl.pric as pric,0 as mderatemoney,0 as ideratenums,y.zffs as zffs,sum(yl.mhandcharge) as mhandcharge,yl.id.iscenicid as iscenicid  from MOrder y ,YOrderlist yl,Custom c where  substr(y.orda,1,4)=='"
					+ date.substring(0, 4)
					+ "' and ddzt='06' and y.orid=yl.id.orid and y.usid=c.usid and yl.mhandcharge>0 and y.ortp='02'  and y.tpfs='02' group by y.usid,yl.itickettypeid,yl.pric,yl.icrowdkindid,c.ibusinessid,yl.icrowdkindpriceid,y.zffs,yl.id.iscenicid");

		}
		list = this.find(hsql.toString());
		List list2 = new ArrayList();
		list2 = this.find(hsql2.toString());
		for (int i = 0; i < list2.size(); i++) {
			list.add(list2.get(i));
		}
		return list;
	}

	/**
	 * ǰ̨�������������۱����������£��걨�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param type
	 * @param date
	 * @return return:List Date:2011-12-31
	 */
	public List updateOrQueryCusGroupSaleByType(String type, String date) {
		List list = new ArrayList();
		if ("1".equals(type)) {
			this.deleteDates(date, "1", "Rcgroupsaledaytab");
		} else if ("2".equals(type)) {
			this.deleteDates(date, "2", "Rcgroupsalemonthtab");
		} else if ("3".equals(type)) {
			this.deleteDates(date, "3", "Rcgroupsaleyeartab");
		}
		StringBuffer hsql = new StringBuffer();
		if ("2".equals(type)) {
			hsql.append(" select r.itickettypeid,r.sztickettypename,r.usid,r.corpname,sum(r.numb),sum(r.mont),r.isa,r.notea,r.dua,r.noteb,r.isb,r.isc,r.isd,nvl(sum(r.isf),0) as isf,nvl(sum(r.duf),0) as duf,r.notec as notec,sum(due) as due from Rcgroupsaledaytab r where r.rmonth='"
					+ date.substring(5, 7)
					+ "' and r.ryear='"
					+ date.substring(0, 4)
					+ "' group by r.itickettypeid,r.sztickettypename,r.usid,r.corpname,r.isa,r.notea,r.dua,r.noteb,r.isb,r.isc,r.duc,r.isd ,r.notec ");
		} else if ("3".equals(type)) {
			hsql.append(" select r.itickettypeid,r.sztickettypename,r.usid,r.corpname,sum(r.numb),sum(r.mont),r.isa,r.notea,r.dua,r.noteb,r.isb,r.isc,r.isd,nvl(sum(r.isf),0) as isf,nvl(sum(r.duf),0) as duf,r.notec as notec,sum(due) as due from Rcgroupsalemonthtab r where  r.ryear='"
					+ date.substring(0, 4)
					+ "' group by r.itickettypeid,r.sztickettypename,r.usid,r.corpname,r.isa,r.notea,r.dua,r.noteb,r.isb,r.isc,r.isd,r.notec ");
		}
		list = this.find(hsql.toString());

		return list;
	}

	/**
	 * ɾ������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 *            return:void Date:2011-12-15
	 */
	public void deleteDates(String date, String type, String table) {
		System.out.println("����ɾ����������� ");
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from " + table + " where ");
		if ("1".equals(type)) {
			hsql.append(" rdate='" + date.substring(8, 10) + "' and rmonth='"
					+ date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "'");
		} else if ("2".equals(type)) {
			hsql.append(" rmonth='" + date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "' ");
		} else if ("3".equals(type)) {
			hsql.append(" ryear='" + date.substring(0, 4) + "' ");
		}
		System.out.println(hsql.toString());
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {

			for (int i = 0; i < list.size(); i++) {
				Object a = (Object) list.get(i);
				this.delete(a);
			}
			// this.deleteAll(list);
		}
	}

	/**
	 * ɾ�����ϱ����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 *            return:void Date:2011-12-15
	 */
	public void deleteDatesByTable(String date, String type, String table) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from " + table + " where ");
		if ("1".equals(type)) {
			hsql.append(" rdate='" + date.substring(8, 10) + "' and rmonth='"
					+ date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "' and titype='01' ");
		} else if ("2".equals(type)) {
			hsql.append(" rmonth='" + date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "' and titype='02' ");
		} else if ("3".equals(type)) {
			hsql.append(" ryear='" + date.substring(0, 4)
					+ "' and titype='03' ");
		}

		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Object a = (Object) list.get(i);
				this.delete(a);
			}
			// this.deleteAll(list);
		}
	}

	/**
	 * ɾ����ƱԱ����Ʊ������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date����
	 * @param typeʱ������
	 * @param table����
	 *            return:void Date:2011-12-15
	 */
	public void deleteDatesByEmpTable(String date, String type,
			String saletype, String table) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from " + table + " where ");
		if ("1".equals(type)) {
			hsql.append(" rdate='" + date.substring(8, 10) + "' and rmonth='"
					+ date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "' and titype='01' and notea='"
					+ saletype + "' ");
		} else if ("2".equals(type)) {
			hsql.append(" rmonth='" + date.substring(5, 7) + "' and ryear ='"
					+ date.substring(0, 4) + "' and titype='02' and notea='"
					+ saletype + "' ");
		} else if ("3".equals(type)) {
			hsql.append(" ryear='" + date.substring(0, 4)
					+ "' and titype='03' and notea='" + saletype + "' ");
		}

		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Object a = (Object) list.get(i);
				this.delete(a);
			}
			// this.deleteAll(list);
		}
	}

	/**
	 * * Describe:�������ڲ�ѯ��ʾ�������Ĳֿ���Ϣ
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showAllHouseware(java.lang.String,
	 *      java.lang.Long)
	 * @param statdate����
	 * @param type
	 *            �������� �ձ���1 �±���2 �����걨��3
	 * @return
	 * @author lijingrui Date:2012-8-27
	 */
	public List showAllHouseware(String statdate, Long type, Long sign) {
		StringBuffer sql = new StringBuffer();
		if (sign != null && sign == 1L) {
			sql.append("select distinct ware.iwarehouseid as iwarehouseid,ware.szwarehousename as szwarehousename from Iomwarehouse ware,Iomstocksbill st where (ware.iwarehouseid=st.istationinid or ware.iwarehouseid=st.istationoutid) ");
		} else if (sign != null && sign == 2L) {
			sql.append("select distinct ware.iwarehouseid as iwarehouseid,ware.szwarehousename as szwarehousename from Iomwarehouse ware,Kcstocksbilltab st where (ware.iwarehouseid=st.istationinid or ware.iwarehouseid=st.istationoutid) ");
		}

		if (type != null && type == 1L) {
			sql.append(" and substr(st.szstocksbillcode,1,8)='" + statdate
					+ "' ");
		} else if (type == 2L) {
			sql.append(" and substr(st.szstocksbillcode,1,6)='" + statdate
					+ "' ");
		} else if (type == 3L) {
			sql.append(" and substr(st.szstocksbillcode,1,4)='" + statdate
					+ "' ");
		}
		List lst = this.find(sql.toString());
		if (lst != null && lst.size() > 0) {
			return lst;
		} else {
			return null;
		}

	}

	/**
	 * * Describe:�����������ĳ�ֿ���������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showIamoutcheckin(java.lang.Long,
	 *      java.lang.String, java.lang.Long)
	 * @param iwarehouseid
	 *            �ֿ�ID
	 * @param statdate
	 *            ����
	 * @param type
	 *            �������� �ձ���1 �±���2 �����걨��3
	 * @param sign
	 *            Ʊ���� 1��Ԥ��Ʊ�� 2��IC��
	 * @return
	 * @author lijingrui Date:2012-8-27
	 */
	public List showIamoutcheckin(Long iwarehouseid, String statdate,
			Long type, Long sign) {
		StringBuffer sql = new StringBuffer();
		if (sign != null && sign == 1L) {
			sql.append("select st.istationinid as istationinid,sd.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,sum(sd.iamount) as iamount from Iomstocksbill st,Iomstocksbilldetails sd,Edmtickettypetab edm where sd.itickettypeid=edm.itickettypeid and st.szstocksbillid=sd.szstocksbillid and st.bystockswayid in (1,3,5) and st.istationinid="
					+ iwarehouseid);
		} else if (sign != null && sign == 2L) {
			sql.append("select st.istationinid as istationinid,sd.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,sum(sd.iamount) as iamount from Kcstocksbilltab st,Kcstocksbilldetailstab sd,Edmtickettypetab edm where sd.itickettypeid=edm.itickettypeid and st.szstocksbillid=sd.szstocksbillid and st.bystockswayid in (1,3,5) and st.istationinid="
					+ iwarehouseid);
		}

		if (type != null && type == 1L) {
			sql.append(" and substr(st.szstocksbillcode,1,8)='" + statdate
					+ "' ");
		} else if (type == 2L) {
			sql.append(" and substr(st.szstocksbillcode,1,6)='" + statdate
					+ "' ");
		} else if (type == 3L) {
			sql.append(" and substr(st.szstocksbillcode,1,4)='" + statdate
					+ "' ");
		}

		sql.append("group by st.istationinid,sd.itickettypeid,edm.sztickettypename");
		List lst = this.find(sql.toString());
		if (lst != null && lst.size() > 0) {
			return lst;
		} else {
			return null;
		}

	}

	/**
	 * * Describe:�����������ĳ�ֿ�ĳ�������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showIamoutcheckOut(java.lang.Long,
	 *      java.lang.String, java.lang.Long)
	 * @param iwarehouseid
	 *            �ֿ�ID
	 * @param statdate
	 *            ����
	 * @param type
	 *            �������� �ձ���1 �±���2 �����걨��3
	 * @param sign
	 *            Ʊ���� 1��Ԥ��Ʊ�� 2��IC��
	 * @return
	 * @author lijingrui Date:2012-8-27
	 */
	public List showIamoutcheckOut(Long iwarehouseid, String statdate,
			Long type, Long sign) {
		StringBuffer sql = new StringBuffer();
		if (sign != null && sign == 1L) {
			sql.append("select sum(sd.iamount) as iamount,sd.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename from Iomstocksbill st,Iomstocksbilldetails sd,Edmtickettypetab edm where sd.itickettypeid=edm.itickettypeid and st.szstocksbillid=sd.szstocksbillid and st.bystockswayid in (4,3,6) and st.istationoutid="
					+ iwarehouseid);
		} else if (sign != null && sign == 2L) {
			sql.append("select sum(sd.iamount) as iamount,sd.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename from Kcstocksbilltab st,Kcstocksbilldetailstab sd,Edmtickettypetab edm where sd.itickettypeid=edm.itickettypeid and st.szstocksbillid=sd.szstocksbillid and st.bystockswayid in (4,3,6) and st.istationoutid="
					+ iwarehouseid);
		}

		if (type != null && type == 1L) {
			sql.append(" and substr(st.szstocksbillcode,1,8)='" + statdate
					+ "' ");
		} else if (type == 2L) {
			sql.append(" and substr(st.szstocksbillcode,1,6)='" + statdate
					+ "' ");
		} else if (type == 3L) {
			sql.append(" and substr(st.szstocksbillcode,1,4)='" + statdate
					+ "' ");
		}
		sql.append(" group by sd.itickettypeid,edm.sztickettypename ");
		List lst = this.find(sql.toString());
		if (lst != null && lst.size() > 0) {
			return lst;
		} else {
			return null;
		}

	}

	/**
	 * * Describe:�������ڲ�ѯ����ĩ��������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#checkRecenterout(java.lang.String,
	 *      java.lang.Long)
	 * @param statdate
	 * @param type
	 * @param sign
	 *            Ʊ���� 1��Ԥ��Ʊ�� 2��IC��
	 * @return
	 * @author lijingrui Date:2012-8-28
	 */
	public Long checkRecenterout(Long iwarehouseid, Long itickettypeid,
			String statdate, Long type, Long sign) {
		StringBuffer sql = new StringBuffer();
		if (type != null && type == 1L) {
			sql.append("select endremain from Rwswarehouseday where itickettypeid="
					+ itickettypeid
					+ " and iwarehouseid="
					+ iwarehouseid
					+ " and int1=" + sign + " order by statdate desc ");
		} else if (type == 2L) {
			sql.append("select endremain from Rwswarehousemonth where itickettypeid="
					+ itickettypeid
					+ " and iwarehouseid="
					+ iwarehouseid
					+ " and int1=" + sign + " order by statdate desc ");
		} else if (type == 3L) {
			sql.append("select endremain from Rwswarehouseyear where itickettypeid="
					+ itickettypeid
					+ " and iwarehouseid="
					+ iwarehouseid
					+ " and int1=" + sign + " order by statdate desc ");
		}

		List list = this.find(sql.toString());
		if (list != null && list.size() > 0) {
			Long endremain = (Long) list.get(0);
			return endremain;
		} else {
			return null;
		}
	}

	/**
	 * * Describe:�ж�ĳ���Ƿ�������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#checkRwswarehouse(java.lang.String,
	 *      java.lang.Long)
	 * @param statdate
	 * @param type
	 * @param sign
	 *            Ʊ���� 1��Ԥ��Ʊ�� 2��IC��
	 * @return
	 * @author lijingrui Date:2012-8-28
	 */
	public List checkRwswarehouse(String statdate, Long type, Long sign) {
		StringBuffer sql = new StringBuffer();
		if (type != null && type == 1L) {
			sql.append(" from Rwswarehouseday where statdate="
					+ Long.parseLong(statdate) + " and int1=" + sign);
		} else if (type == 2L) {
			sql.append(" from Rwswarehousemonth where statdate="
					+ Long.parseLong(statdate) + " and int1=" + sign);
		} else if (type == 3L) {
			sql.append(" from Rwswarehouseyear where statdate="
					+ Long.parseLong(statdate) + " and int1=" + sign);
		}

		List list = this.find(sql.toString());
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * * Describe:��ʾ��ĳ�����ó������ƱԱ
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showEmployeetab(java.lang.String)
	 * @param statdate
	 * @return
	 * @author lijingrui Date:2012-8-29
	 */
	public List showEmployeetab(String statdate, Long sign) {
		String date = statdate.replaceAll("-", "");
		String hsql = " from Rwspersonalday where statdate="
				+ Long.parseLong(date) + " and int2=" + sign;
		List list = this.find(hsql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Rwspersonalday rwsperson = (Rwspersonalday) list.get(i);
				this.delete(rwsperson);
			}
		}
		String msql = null;
		if (sign != null && sign == 1L) {
			// msql=" select distinct esf.iemployeeid,esf.szemployeename,stk.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Iompersonalticketdetails stk,Esfemployeetab esf,Edmtickettypetab edm where esf.iemployeeid=stk.ireceiverid  and edm.itickettypeid=stk.itickettypeid";
			// msql =
			// "select distinct esf.iemployeeid,esf.szemployeename,std.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Iomstocksbill st,Iomstocksbilldetails std,Esfemployeetab esf,Edmtickettypetab edm where st.szstocksbillid=std.szstocksbillid and st.ihandler=esf.iemployeeid and std.itickettypeid=edm.itickettypeid and st.bystockswayid in (5,6) and  substr(st.szstocksbillcode,1,8)='"+
			// statdate+"'";
			msql = "select esf.iemployeeid as iemployeeid,dp.id.empid as empid, dp.id.empname as empname,sum(dp.id.nums) as nums,dp.id.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename from Deasplitsalecount dp,Edmtickettypetab edm,Esfemployeetab esf where dp.id.empid=esf.empid and dp.id.itickettypeid=edm.itickettypeid and edm.bymaketicketway='01' and edm.bymediatype in ('00','01') and substr(dp.id.dtmakedate,1,10) = '"
					+ statdate
					+ "'  group by esf.iemployeeid,dp.id.empid, dp.id.empname,dp.id.itickettypeid,edm.sztickettypename ";
		} else if (sign != null && sign == 2L) {
			// msql=" select distinct esf.iemployeeid,esf.szemployeename,stk.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Kcpersonalticketdetailstab stk,Esfemployeetab esf,Edmtickettypetab edm where esf.iemployeeid=stk.ireceiverid and edm.itickettypeid=stk.itickettypeid";
			// msql =
			// " select distinct esf.iemployeeid,esf.szemployeename,std.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Kcstocksbilltab st,Kcstocksbilldetailstab std,Esfemployeetab esf,Edmtickettypetab edm where st.szstocksbillid=std.szstocksbillid and st.ihandler=esf.iemployeeid and std.itickettypeid=edm.itickettypeid and st.bystockswayid in (5,6) and substr(st.szstocksbillcode,1,8)='"+
			// statdate+"'";
			msql = "select esf.iemployeeid as iemployeeid,dp.id.empid as empid, dp.id.empname as empname,sum(dp.id.nums) as nums,dp.id.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename from Deasplitsalecount dp,Edmtickettypetab edm,Esfemployeetab esf where dp.id.empid=esf.empid and dp.id.itickettypeid=edm.itickettypeid and edm.bymaketicketway='01' and edm.bymediatype not in ('00','01') and substr(dp.id.dtmakedate,1,10) = '"
					+ statdate
					+ "'  group by esf.iemployeeid,dp.id.empid, dp.id.empname,dp.id.itickettypeid,edm.sztickettypename ";
		}
		List lst = this.find(msql);
		if (lst != null && lst.size() > 0) {
			return lst;
		} else {
			msql = null;
			if (sign != null && sign == 1L) {
				// msql=" select distinct esf.iemployeeid,esf.szemployeename,stk.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Iompersonalticketdetails stk,Esfemployeetab esf,Edmtickettypetab edm where esf.iemployeeid=stk.ireceiverid  and edm.itickettypeid=stk.itickettypeid";
				msql = "select distinct esf.iemployeeid,esf.empid as empid,esf.szemployeename,0 as nums, std.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Iomstocksbill st,Iomstocksbilldetails std,Esfemployeetab esf,Edmtickettypetab edm where st.szstocksbillid=std.szstocksbillid and st.ihandler=esf.iemployeeid and std.itickettypeid=edm.itickettypeid and st.bystockswayid in (5,6) and  substr(st.szstocksbillcode,1,8)='"
						+ statdate + "'";
			} else if (sign != null && sign == 2L) {
				// msql=" select distinct esf.iemployeeid,esf.szemployeename,stk.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Kcpersonalticketdetailstab stk,Esfemployeetab esf,Edmtickettypetab edm where esf.iemployeeid=stk.ireceiverid and edm.itickettypeid=stk.itickettypeid";
				msql = " select distinct esf.iemployeeid,esf.empid as empid,esf.szemployeename,0 as nums,std.itickettypeid  as itickettypeid,edm.sztickettypename as sztickettypename from Kcstocksbilltab st,Kcstocksbilldetailstab std,Esfemployeetab esf,Edmtickettypetab edm where st.szstocksbillid=std.szstocksbillid and st.ihandler=esf.iemployeeid and std.itickettypeid=edm.itickettypeid and st.bystockswayid in (5,6) and substr(st.szstocksbillcode,1,8)='"
						+ statdate + "'";
			}
			lst = this.find(msql);
			return lst;
		}

	}

	/**
	 * * Describe:��ʾ��ĳ�����ó�����ƱԱ���õ�����
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showEmpidIamount(java.lang.Long,
	 *      java.lang.String)
	 * @param epmpid
	 * @param statdate
	 * @return
	 * @author lijingrui Date:2012-8-29
	 */
	public Long showEmpidIamount(Long epmpid, Long itickettypeid,
			String statdate, Long sign) {
		String sql = null;
		if (sign != null && sign == 1L) {
			sql = "select sum(sd.iamount) as iamount from Iomstocksbill stk,Iomstocksbilldetails sd,Edmtickettypetab edm where edm.itickettypeid=sd.itickettypeid and sd.szstocksbillid=stk.szstocksbillid and stk.bystockswayid=6 and stk.ihandler="
					+ epmpid
					+ " and substr(stk.szstocksbillcode,1,8)='"
					+ statdate
					+ "' and sd.itickettypeid="
					+ itickettypeid
					+ " group by sd.itickettypeid,edm.sztickettypename";
		} else if (sign != null && sign == 2L) {
			sql = "select sum(sd.iamount) as iamount from Kcstocksbilltab stk,Kcstocksbilldetailstab sd,Edmtickettypetab edm where edm.itickettypeid=sd.itickettypeid and sd.szstocksbillid=stk.szstocksbillid and stk.bystockswayid=6 and stk.ihandler="
					+ epmpid
					+ " and substr(stk.szstocksbillcode,1,8)='"
					+ statdate
					+ "' and sd.itickettypeid="
					+ itickettypeid
					+ " group by sd.itickettypeid,edm.sztickettypename";
		}
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long iamount = (Long) lst.get(0);
				if (iamount == null) {
					return 0L;
				}
				return iamount;
			}
		}
		return 0L;
	}

	/**
	 * * Describe:��ʾ����ƱԱĳ����Ʊ������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showIompersonhouse(java.lang.Long,
	 *      java.lang.Long, java.lang.String)
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * @author lijingrui Date:2012-8-29
	 */
	public Long showIompersonhouse(Long epmpid, Long itickettypeid,
			String statdate, Long sign) {
		String sql = sql = "select sum(salde.iticketnum) as nums  from Stssalesvouchertab sale,Stssalesvoucherdetailstab salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype in ( '01' ,'04')  and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid ";
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long iamount = (Long) lst.get(0);
				if (iamount == null) {
					return 0L;
				}
				return iamount;
			}

		}
		return 0L;
	}

	/**
	 * * Describe:��ʾ����ƱԱĳ����Ʊ������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#checkUpnumsHouse(java.lang.Long,
	 *      java.lang.Long, java.lang.String)
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * @author lijingrui Date:2012-11-15
	 */
	public Long checkUpnumsHouse(Long epmpid, Long itickettypeid,
			String statdate) {
		String sql = sql = "select sum(salde.iticketnum) as nums  from Stssalesvouchertab sale,Stssalesvoucherdetailstab salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype='02' and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid";
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long iamount = (Long) lst.get(0);
				if (iamount == null) {
					return 0L;
				}
				return iamount;
			}

		}
		return 0L;
	}

	/**
	 * * Describe:�������ڲ����ƱԱ��Ʊ��������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showStockhouse(java.lang.Long,
	 *      java.lang.Long, java.lang.String, java.lang.Long)
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @param sign
	 * @return
	 * @author lijingrui Date:2012-10-30
	 */
	public Long showStockhouse(Long epmpid, Long itickettypeid,
			String statdate, Long sign) {
		String sql = null;
		if (sign != null && sign == 1L) {
			sql = "select sum(sd.iamount) as iamount from Iomstocksbill stk,Iomstocksbilldetails sd where sd.szstocksbillid=stk.szstocksbillid and stk.bystockswayid=5 and stk.ihandler="
					+ epmpid
					+ " and substr(stk.szstocksbillcode,1,8)='"
					+ statdate + "' and sd.itickettypeid=" + itickettypeid;
		} else if (sign != null && sign == 2L) {
			sql = "select sum(sd.iamount) as iamount from Kcstocksbilltab stk,Kcstocksbilldetailstab sd where sd.szstocksbillid=stk.szstocksbillid and stk.bystockswayid=5 and stk.ihandler="
					+ epmpid
					+ " and substr(stk.szstocksbillcode,1,8)='"
					+ statdate + "' and sd.itickettypeid=" + itickettypeid;
		}
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long iamouint = (Long) lst.get(0);
				if (iamouint == null) {
					return 0L;
				}
				return iamouint;
			}
		}
		return 0L;
	}

	/**
	 * * Describe:��������\��ƱԱ��ѯ����ĩ��������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#showRwspersonal(java.lang.Long,
	 *      java.lang.Long, java.lang.String)
	 * @param epmpid
	 * @param itickettypeid
	 * @param statdate
	 * @return
	 * @author lijingrui Date:2012-8-29
	 */
	public Long showRwspersonal(Long epmpid, Long itickettypeid,
			String statdate, Long sign) {
		String sql = "select psn.endremain as endremain from Rwspersonalday psn where psn.personalid="
				+ epmpid
				+ " and psn.itickettypeid="
				+ itickettypeid
				+ " and psn.statdate<="
				+ statdate
				+ " and psn.int2="
				+ sign
				+ " order by psn.statdate desc";
		List lst = this.findTopNumb(sql, 1);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long endremain = (Long) lst.get(0);
				if (endremain != null) {
					return endremain;
				}
			}

		}
		return 0L;
	}

	/**
	 * * Describe:��ѯ����ƱԱ����ʣ��Ԥ��Ʊ������
	 * 
	 * @see com.ectrip.report.system.datereport.dao.idao.IReportDataDAO#searchPersonDetails(java.lang.Long,
	 *      java.lang.Long)
	 * @param epmpid
	 * @param itickettypeid
	 * @return
	 * @author lijingrui Date:2012-11-15
	 */
	public Long searchPersonDetails(Long epmpid, Long itickettypeid, Long sign) {
		String sql = null;
		if (sign != null && sign == 1L) {
			sql = "select sum(ps.iamount) as iamount from Iompersonalticketdetails ps where ps.ireceiverid="
					+ epmpid + " and ps.itickettypeid=" + itickettypeid;
		} else if (sign != null && sign == 2L) {
			sql = "select sum(ps.iamount) as iamount from Kcpersonalticketdetailstab ps where ps.ireceiverid="
					+ epmpid + " and ps.itickettypeid=" + itickettypeid;
		}
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			if (lst.get(0) == null) {
				return 0L;
			} else {
				Long iamouint = (Long) lst.get(0);
				if (iamouint == null) {
					return 0L;
				}
				return iamouint;
			}
		}
		return 0L;
	}

	/**
	 * ����ʱ���ѯ���������ս����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date
	 * @return return:List Date:2011-11-30
	 */
	public List updateOrQueryZProviderByDate(String type, String date) {

		this.deleteDatesByTable(date, type, "Rpzprovidertab");// �ս�

		List list = new ArrayList();
		String hsql = "";

		if ("1".equals(type)) {
			hsql = "select et.iscenicid as iscenicid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid,sm.isettlementid  as isettlementid,sum(sl.isplitamount) as numb,nvl(sum(sl.ideratenums),0) as ideratenums,sum(sl.msplitmoney) as mont,nvl(sum(sl.mderatemoney),0) as mderatemoney,sum(sl.mhandcharge) as mhandcharge  from Stssalesvouchertab sale,Stssalessettlementtab sm,Stscomticketsalesdetailstab sl,Edmtickettypetab et where substr(sale.dtmakedate, 1, 10) = '"
					+ date
					+ "' and sm.id.iticketstationid=sale.id.iticketstationid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sale.id.iticketstationid=sl.id.iticketstationid and et.itickettypeid=sl.iztickettypeid group by et.iscenicid,sale.ibusinessid,sm.isettlementid,sale.bysalesvouchertype";
		} else if ("2".equals(type)) {
			hsql = "select iscenicid,bysalesvouchertype,ibusinessid,isettlementid,sum(numb) as numb,sum(yhnumb) as yhnumb,sum(mont) as mont,sum(yhmont) as yhmont,sum(mhandcharge) as mhandcharge from Rpzprovidertab where rmonth='"
					+ date.substring(5, 7)
					+ "' and ryear='"
					+ date.substring(0, 4)
					+ "' and titype='01' group by iscenicid,ibusinessid,isettlementid,bysalesvouchertype";
		} else if ("3".equals(type)) {
			hsql = "select iscenicid,bysalesvouchertype,ibusinessid,isettlementid,sum(numb) as numb,sum(yhnumb) as yhnumb,sum(mont) as mont,sum(yhmont) as yhmont,sum(mhandcharge) as mhandcharge from Rpzprovidertab where  ryear='"
					+ date.substring(0, 4)
					+ "' and titype='02' group by iscenicid,ibusinessid,isettlementid,bysalesvouchertype";
		}
		list = this.find(hsql);
		String hsql3 = "";
		if ("1".equals(type)) {
			hsql3 = "select et.iscenicid as iscenicid,'99' as bysalesvouchertype,c.ibusinessid,'00' as isettlementid,0 as numb,0 as yhnumb,0 as mont,0 as yhmont,sum(yl.mhandcharge) as mhandcharge from MOrder m,YZorderlist yl, Edmtickettypetab et,Custom c where m.orda='"
					+ date
					+ "' and  m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.iztickettypeid=et.itickettypeid and c.usid=m.usid   group by et.iscenicid,c.ibusinessid  ";
			List list3 = this.find(hsql3);

			if (list != null && list.size() >= 1) {
				if (list3 != null && list3.size() >= 1) {
					for (int i = 0; i < list3.size(); i++) {
						list.add(list3.get(i));
					}
				}
				return list;
			} else {
				return list3;
			}
		} else {
			return list;
		}

	}

	/**
	 * ����ʱ���ѯ���������ս����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date
	 * @return return:List Date:2011-11-30
	 */
	public List updateOrQueryempfzByDate(String date) {

		this.deleteDates(date, "1", "Rproviderfzdaytab");// �ս�

		List list = new ArrayList();
		String hsql = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice,nvl(sum(stcom.isplitamount),0),nvl(sum(stcom.msplitmoney),0),nvl(sum(stcom.ideratenums),0),nvl(sum(stcom.mderatemoney),0),nvl(sum(stcom.mhandcharge),0) from Stssalesvouchertab s,Stssalessettlementtab st,Stscomticketsalesdetailstab stcom,Edmcrowdkindpricetab ep where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=stcom.id.isalesvoucherid and s.id.iticketstationid=stcom.id.iticketstationid and stcom.icrowdkindpriceid=ep.icrowdkindpriceid  group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice";

		list = this.find(hsql);

		return list;

	}

	public List updateOrQueryempfzBymonth(String date) {

		this.deleteDates(date, "2", "Rproviderfzmonthtab");// �ս�

		List list = new ArrayList();
		String hsql = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,s.isettlementid,s.iticketstationid,s.iemployeeid,s.itickettypeid,s.iztickettypeid,s.icrowdkindid,s.msplitprice,sum(s.iticketplayer),sum(s.msplitmoney),sum(s.ideratenums),sum(s.mderatemoney),sum(s.mhandcharge) from Rproviderfzdaytab s where rmonth='"
				+ date.substring(5, 7)
				+ "' and ryear ='"
				+ date.substring(0, 4)
				+ "'  group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,s.isettlementid,s.iticketstationid,s.iemployeeid,s.itickettypeid,s.iztickettypeid,s.icrowdkindid,s.msplitprice";

		list = this.find(hsql);

		return list;

	}

	public List updateOrQueryempfznumbByDate(String date) {

		this.deleteDates(date, "1", "Rproviderfznumbdaytab");// �ս�

		List list = new ArrayList();
		String hsql = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid,sum(sd.iticketnum),sum(sd.iticketplayer),sum(sd.ideratenums) from Stssalesvouchertab s,Stssalessettlementtab st,Stssalesvoucherdetailstab sd  where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=sd.id.isalesvoucherid and s.id.iticketstationid=sd.id.iticketstationid   group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid ";

		list = this.find(hsql);

		return list;

	}

	public List updateOrQueryempfznumbBymonth(String date) {

		this.deleteDates(date, "2", "Rproviderfznumbmonthtab");// �½�

		List list = new ArrayList();
		String hsql = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,s.isettlementid,s.iticketstationid,s.iemployeeid,s.itickettypeid,sum(s.iticketnum),sum(s.iticketplayer),sum(s.ideratenums) from Rproviderfznumbdaytab s  where rmonth='"
				+ date.substring(5, 7)
				+ "' and ryear ='"
				+ date.substring(0, 4)
				+ "'   group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,s.isettlementid,s.iticketstationid,s.iemployeeid,s.itickettypeid ";

		list = this.find(hsql);

		return list;

	}

}
