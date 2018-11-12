package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.report.sales.Rwspersonalday;
import com.ectrip.ec.report.system.datereport.dao.idao.IHisReportDataDAO;

public class HisReportDataDAO extends GenericDao implements IHisReportDataDAO {

	/**
	 * ����ʱ���ѯ���������ս����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param date
	 * @return return:List Date:2011-11-30
	 */
	public List updateOrQueryProviderByDate(String type, String date) {
		this.deleteDates(date, "1", "Rproviderdaytab");// �ս�
		List list = new ArrayList();
		String hsql1 = "select sale.iscenicid as iscenicid, pro.szscenicname as szscenicname,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sale.bysalesvouchertype,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums  from Stssalesvouchertab sale,Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl where substr(sale.dtmakedate, 1, 10) = '"
				+ date
				+ "' and pro.iscenicid = sale.iscenicid and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid   and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid  group by sale.iscenicid,pro.szscenicname,sm.isettlementid,sale.bysalesvouchertype";

		String hsql2 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,'02' as fs from MOrder m,YOrderlist yl, Esbscenicareatab  pro where m.orda='"
				+ date
				+ "' and  m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,pro.szscenicname ";

		String hsql3 = "select sale.iscenicid as iscenicid, pro.szscenicname as szscenicname,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid  as isettlementid,sale.bysalesvouchertype,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums  from Stssalesvouchertablist sale,Esbscenicareatab pro,Stssalessettlementtablist sm,Stssalesvoucherdetailstablist sl where substr(sale.dtmakedate, 1, 10) = '"
				+ date
				+ "' and pro.iscenicid = sale.iscenicid and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid   and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid  group by sale.iscenicid,pro.szscenicname,sm.isettlementid,sale.bysalesvouchertype";
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		List list3 = this.find(hsql3);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		if (list3 != null && !list3.isEmpty()) {
			for(int i=0;i<list3.size();i++){
				list.add(list3.get(i));
			}
		}
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
		this.deleteDatesByTable(date, "1", "Rproviderlisttab");

		List list = new ArrayList();
		String hsql1 = "select sale.iscenicid as iscenicid,pro.szscenicname as szscenicname, sl.itickettypeid as itickettypeid, kind.icrowdkindid  as icrowdkindid ,sl.mactualsaleprice as pric,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid as isettlementid,sum(sl.iamount) as tdnum,sale.bysalesvouchertype,nvl(sum(sl.ideratenums),0) as ideratenums,nvl(sum(sl.mderatemoney),0)  as mderatemoney from Stssalesvouchertab sale, Esbscenicareatab pro,Stssalessettlementtab sm,Stssalesvoucherdetailstab sl, Edmcrowdkindpricetab kind  where substr(sale.dtmakedate, 1, 10) = '"
				+ date
				+ "'  and pro.iscenicid = sale.iscenicid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid  and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid and sl.icrowdkindpriceid = kind.icrowdkindpriceid  group by sale.iscenicid, pro.szscenicname,  sl.itickettypeid,kind.icrowdkindid,  sl.mactualsaleprice, sm.isettlementid,sale.bysalesvouchertype ";

		String hsql2 = "select yl.id.iscenicid as iscenicid, pro.szscenicname as szscenicname,yl.itickettypeid as itickettypeid,yl.icrowdkindid as icrowdkindid,yl.pric as pric,0 as numb,0 as mont,0 as tdmont,sum(yl.mhandcharge) as mhandcharge,'99' as isettlementid,sum(yl.numb) as tdnum,'02' as fs  from MOrder m,YOrderlist yl, Esbscenicareatab pro where m.orda='"
				+ date
				+ "' and m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.id.iscenicid = pro.iscenicid group by yl.id.iscenicid ,yl.itickettypeid ,yl.icrowdkindid, pro.szscenicname,yl.pric ";

		String hsql3 = "select sale.iscenicid as iscenicid,pro.szscenicname as szscenicname, sl.itickettypeid as itickettypeid, kind.icrowdkindid  as icrowdkindid ,sl.mactualsaleprice as pric,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sum(sl.meventmoney) as tdmont,sum(sl.mhandcharge) as mhandcharge,sm.isettlementid as isettlementid,sum(sl.iamount) as tdnum,sale.bysalesvouchertype,nvl(sum(sl.ideratenums),0) as ideratenums,nvl(sum(sl.mderatemoney),0)  as mderatemoney from Stssalesvouchertablist sale, Esbscenicareatab pro,Stssalessettlementtablist sm,Stssalesvoucherdetailstablist sl, Edmcrowdkindpricetab kind  where substr(sale.dtmakedate, 1, 10) = '"
				+ date
				+ "'  and pro.iscenicid = sale.iscenicid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid  and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sm.id.iticketstationid = sale.id.iticketstationid and sl.id.iticketstationid = sale.id.iticketstationid and sl.icrowdkindpriceid = kind.icrowdkindpriceid  group by sale.iscenicid, pro.szscenicname,  sl.itickettypeid,kind.icrowdkindid,  sl.mactualsaleprice, sm.isettlementid,sale.bysalesvouchertype ";
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		List list3 = this.find(hsql3);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		if (list3 != null && !list3.isEmpty()) {
			for(int i=0;i<list3.size();i++){
				list.add(list3.get(i));
			}
		}
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
		this.deleteDates(date, "1", "Rregiondaytab");
		List list = new ArrayList();

		String hsql = " select t.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,t.iregionalid as iregionalid,re.szregionalname as szregionalname,sum(list.numb) as counts from MOrder m,TOrder t,Galsourceregiontab re,Esbscenicareatab pro,TOrderlist list where m.orda='"
				+ date
				+ "' and m.ddzt in('02','01','11') and  t.id.orid = m.orid and list.id.orid = t.id.orid and  re.iregionalid = t.iregionalid  and t.id.iscenicid = pro.iscenicid group by t.id.iscenicid,pro.szscenicname,t.iregionalid,re.szregionalname";

		list = this.find(hsql);
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
		this.deleteDates(date, "1", "Rcustomdaytab");
		List list = new ArrayList();

		String hsql = " select tor.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,sum(lis.numb) as numbs from TOrder tor,TOrderlist lis,Esbscenicareatab pro  where tor.dtstartdate='"
				+ date
				+ "' and tor.ddzt in('01','02','11')  and tor.orfl='02' and  tor.id.orid = lis.id.orid  and  tor.id.iscenicid=pro.iscenicid and ( lis.numb >0) group by tor.id.iscenicid ,pro.szscenicname ";

		list = this.find(hsql);
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
		this.deleteDatesByEmpTable(date, "1", cztype, "Rsalecounttab");

		List list = new ArrayList();

		String hsql1 = "select cop.icompanyinfoid as icompanyinfoid,cop.szcompanyname as szcompanyname,js.isettlementid as zffs,emp.empid as empid,emp.szemployeename as empname ,sum(salde.meventmoney) as mont,salde.itickettypeid as itickettypeid,sum(salde.iamount) as nums,salde.mactualsaleprice as mactualsaleprice,pri.icrowdkindid as icrowdkindid,sum(salde.mhandcharge) as mhandcharge, salde.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.ibusinessid as ibusinessid,sale.usid as usid,sale.iregionalid as iregionalid,sum(salde.iticketnum) as iticketplayer,nvl(sum(salde.ideratenums),0) as ideratenums,nvl(sum(salde.mderatemoney),0) as mderatemoney from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop,Stssalessettlementtab js,Stssalesvoucherdetailstab salde,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' and sale.bysalesvouchertype='"
				+ cztype
				+ "' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.id.isalesvoucherid = js.id.isalesvoucherid and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = js.id.iticketstationid and sale.id.iticketstationid = salde.id.iticketstationid  and salde.icrowdkindpriceid = pri.icrowdkindpriceid  group by cop.icompanyinfoid,cop.szcompanyname,emp.empid,emp.szemployeename,js.isettlementid,salde.itickettypeid,salde.mactualsaleprice, pri.icrowdkindid ,salde.id.iticketstationid,sale.iticketwinid,sale.ibusinessid,sale.usid,sale.iregionalid";

		String hsql2 = "select cop.icompanyinfoid as icompanyinfoid,cop.szcompanyname as szcompanyname,js.isettlementid as zffs,emp.empid as empid,emp.szemployeename as empname ,sum(salde.meventmoney) as mont,salde.itickettypeid as itickettypeid,sum(salde.iamount) as nums,salde.mactualsaleprice as mactualsaleprice,pri.icrowdkindid as icrowdkindid,sum(salde.mhandcharge) as mhandcharge, salde.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.ibusinessid as ibusinessid,sale.usid as usid,sale.iregionalid as iregionalid,sum(salde.iticketnum) as iticketplayer,nvl(sum(salde.ideratenums),0) as ideratenums,nvl(sum(salde.mderatemoney),0) as mderatemoney from Stssalesvouchertablist sale,Esfemployeetab emp,Galcompanyinfotab cop,Stssalessettlementtablist js,Stssalesvoucherdetailstablist salde,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' and sale.bysalesvouchertype='"
				+ cztype
				+ "' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.id.isalesvoucherid = js.id.isalesvoucherid and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = js.id.iticketstationid and sale.id.iticketstationid = salde.id.iticketstationid  and salde.icrowdkindpriceid = pri.icrowdkindpriceid  group by cop.icompanyinfoid,cop.szcompanyname,emp.empid,emp.szemployeename,js.isettlementid,salde.itickettypeid,salde.mactualsaleprice, pri.icrowdkindid ,salde.id.iticketstationid,sale.iticketwinid,sale.ibusinessid,sale.usid,sale.iregionalid";

		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
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
		this.deleteDatesByTable(date, "1", "Rcustomgrouptab");

		List list = new ArrayList();
		String hsql1 = "select new map(sale.usid as usid,ti.bycategorytype as bycategorytype,sum(salde.iamount) as numb,sum(salde.meventmoney) as mont,nvl(sum(salde.mderatemoney), 0) as mderatemoney,nvl(sum(salde.ideratenums), 0) as ideratenums,price.icrowdkindid as icrowdkindid,"
				+ "salde.itickettypeid as itickettypeid,sale.iscenicid as iscenicid,salde.mactualsaleprice as mactualsaleprice,saletype.isettlementid as isettlementid,sale.bysalesvouchertype  as bysalesvouchertype) from Stssalesvouchertab  sale,Stssalesvoucherdetailstab salde,Custom  cus,Edmtickettypetab  ti,Edmcrowdkindpricetab      price,"
				+ "Stssalessettlementtab saletype where sale.id.isalesvoucherid = saletype.id.isalesvoucherid and sale.id.iticketstationid = saletype.id.iticketstationid and price.icrowdkindpriceid = salde.icrowdkindpriceid and cus.ibusinessid != 1 and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid and cus.usid = sale.usid and salde.itickettypeid = ti.itickettypeid"
				+ " and substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' group by sale.usid,ti.bycategorytype,saletype.isettlementid,salde.mactualsaleprice, sale.iscenicid,salde.itickettypeid,price.icrowdkindid,sale.bysalesvouchertype";
		
		String hsql2 = "select new map(sale.usid as usid,ti.bycategorytype as bycategorytype,sum(salde.iamount) as numb,sum(salde.meventmoney) as mont,nvl(sum(salde.mderatemoney), 0) as mderatemoney,nvl(sum(salde.ideratenums), 0) as ideratenums,price.icrowdkindid as icrowdkindid,"
				+ "salde.itickettypeid as itickettypeid,sale.iscenicid as iscenicid,salde.mactualsaleprice as mactualsaleprice,saletype.isettlementid as isettlementid,sale.bysalesvouchertype  as bysalesvouchertype) from Stssalesvouchertablist  sale,Stssalesvoucherdetailstablist salde,Custom  cus,Edmtickettypetab  ti,Edmcrowdkindpricetab      price,"
				+ "Stssalessettlementtablist saletype where sale.id.isalesvoucherid = saletype.id.isalesvoucherid and sale.id.iticketstationid = saletype.id.iticketstationid and price.icrowdkindpriceid = salde.icrowdkindpriceid and cus.ibusinessid != 1 and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid and cus.usid = sale.usid and salde.itickettypeid = ti.itickettypeid"
				+ " and substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' group by sale.usid,ti.bycategorytype,saletype.isettlementid,salde.mactualsaleprice, sale.iscenicid,salde.itickettypeid,price.icrowdkindid,sale.bysalesvouchertype";

		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
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
		this.deleteDatesByTable(date, "1", "Rsaletickettab");

		String hsql1 = "select sale.iscenicid as iscenicid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sl.id.iticketstationid as iticketstationid,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' and sale.bysalesvouchertype='01'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid  and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.iscenicid,sl.itickettypeid, sl.mactualsaleprice,pri.icrowdkindid,sl.id.iticketstationid";
		
		String hsql2 = "select sale.iscenicid as iscenicid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as numb,sum(sl.meventmoney) as mont,sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sl.id.iticketstationid as iticketstationid,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertablist sale,Stssalesvoucherdetailstablist sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' and sale.bysalesvouchertype='01'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid  and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.iscenicid,sl.itickettypeid, sl.mactualsaleprice,pri.icrowdkindid,sl.id.iticketstationid";
		
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}

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
		this.deleteDates(date, "1", "Rticketdaytab");
		
		String hsql1 = " select sale.ibusinessid as ibusinessid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum, sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
					+ date
					+ "'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.ibusinessid,sl.itickettypeid,sl.mactualsaleprice, pri.icrowdkindid,sale.bysalesvouchertype,sl.icrowdkindpriceid,pri.mactualsaleprice";
		
		String hsql2 = " select sale.ibusinessid as ibusinessid,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum, sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums from Stssalesvouchertablist sale,Stssalesvoucherdetailstablist sl,Edmcrowdkindpricetab pri where substr(sale.dtmakedate,1,10)='"
				+ date
				+ "'  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.ibusinessid,sl.itickettypeid,sl.mactualsaleprice, pri.icrowdkindid,sale.bysalesvouchertype,sl.icrowdkindpriceid,pri.mactualsaleprice";
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
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
		this.deleteDates(date, "1", "Rcgroupsaledaytab");

		StringBuffer hsql1 = new StringBuffer();
		StringBuffer hsql2 = new StringBuffer();
		StringBuffer hsql3 = new StringBuffer();
		hsql1.append(" select sale.usid as usid ,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum,  sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid as ibusinessid,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums,st.isettlementid as zffs,nvl(sum(sl.mhandcharge),0) as mhandcharge,sale.iscenicid as iscenicid from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl,Stssalessettlementtab st,Custom cust,Edmcrowdkindpricetab pri where  substr(sale.dtmakedate,1,10)='"
					+ date
					+ "' and sale.bysalesvouchertype in ('01','02','04')  and sale.ibusinessid!=1  and cust.usid = sale.usid  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid  and sale.id.isalesvoucherid = st.id.isalesvoucherid and sale.id.iticketstationid = st.id.iticketstationid  and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.usid,sl.itickettypeid,sl.mactualsaleprice,pri.icrowdkindid,sale.bysalesvouchertype,sale.ibusinessid,sl.icrowdkindpriceid,pri.mactualsaleprice,st.isettlementid,sale.iscenicid");
		hsql2.append("select y.usid as usid,yl.itickettypeid as iztickettypeid,0 as iticketnum,0 as mont,yl.pric as mactualsaleprice,yl.icrowdkindid as icrowdkindid,'02' as bysalesvouchertype,c.ibusinessid as ibusinessid,yl.icrowdkindpriceid as icrowdkindpriceid,yl.pric as pric,0 as mderatemoney,0 as ideratenums,y.zffs as zffs,sum(yl.mhandcharge) as mhandcharge,yl.id.iscenicid as iscenicid  from MOrder y ,YOrderlist yl,Custom c where y.orda='"
					+ date
					+ "' and ddzt='06' and y.orid=yl.id.orid and y.usid=c.usid and yl.mhandcharge>0  and y.ortp='02' and y.tpfs='02'  group by y.usid,yl.itickettypeid,yl.pric,yl.icrowdkindid,c.ibusinessid,yl.icrowdkindpriceid,y.zffs,yl.id.iscenicid");
		hsql3.append(" select sale.usid as usid ,sl.itickettypeid as itickettypeid,sum(sl.iamount) as iticketnum,  sum(sl.meventmoney) as mont, sl.mactualsaleprice as mactualsaleprice, pri.icrowdkindid as icrowdkindid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid as ibusinessid,sl.icrowdkindpriceid as icrowdkindpriceid,pri.mactualsaleprice as price,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.ideratenums),0) as ideratenums,st.isettlementid as zffs,nvl(sum(sl.mhandcharge),0) as mhandcharge,sale.iscenicid as iscenicid from Stssalesvouchertablist sale,Stssalesvoucherdetailstablist sl,Stssalessettlementtablist st,Custom cust,Edmcrowdkindpricetab pri where  substr(sale.dtmakedate,1,10)='"
				+ date
				+ "' and sale.bysalesvouchertype in ('01','02','04')  and sale.ibusinessid!=1  and cust.usid = sale.usid  and sale.id.isalesvoucherid = sl.id.isalesvoucherid and sale.id.iticketstationid = sl.id.iticketstationid  and sale.id.isalesvoucherid = st.id.isalesvoucherid and sale.id.iticketstationid = st.id.iticketstationid  and sl.icrowdkindpriceid = pri.icrowdkindpriceid  group by sale.usid,sl.itickettypeid,sl.mactualsaleprice,pri.icrowdkindid,sale.bysalesvouchertype,sale.ibusinessid,sl.icrowdkindpriceid,pri.mactualsaleprice,st.isettlementid,sale.iscenicid");
		List list1 = this.find(hsql1.toString());
		List list2 = this.find(hsql2.toString());
		List list3 = this.find(hsql3.toString());
		if (list1 != null && !list1.isEmpty()) {
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if (list2 != null && !list2.isEmpty()) {
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		if (list3 != null && !list3.isEmpty()) {
			for(int i=0;i<list3.size();i++){
				list.add(list3.get(i));
			}
		}
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
		String sql1 = "select sum(salde.iticketnum) as nums  from Stssalesvouchertab sale,Stssalesvoucherdetailstab salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype in ( '01' ,'04')  and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid ";
		
		String sql2 = "select sum(salde.iticketnum) as nums  from Stssalesvouchertablist sale,Stssalesvoucherdetailstablist salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype in ( '01' ,'04')  and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid ";
		List list1 = this.find(sql1);
		List list2 = this.find(sql2);
		Long amount = 0L;
		if (list1 != null && list1.size() > 0 && list1.get(0)!=null) {
			Long iamount = (Long) list1.get(0);
			if (iamount != null) {
				amount+= iamount;
			}

		}
		if (list2 != null && list2.size() > 0 && list2.get(0)!=null) {
			Long iamount = (Long) list2.get(0);
			if (iamount != null) {
				amount+= iamount;
			}

		}
		return amount;
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
		String sql1 = "select sum(salde.iticketnum) as nums  from Stssalesvouchertab sale,Stssalesvoucherdetailstab salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype='02' and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid";
		
		String sql2 = "select sum(salde.iticketnum) as nums  from Stssalesvouchertablist sale,Stssalesvoucherdetailstablist salde where substr(sale.dtmakedate, 1, 10) = '"
				+ statdate
				+ "' and sale.bysalesvouchertype='02' and sale.ihandler="
				+ epmpid
				+ "  and salde.itickettypeid="
				+ itickettypeid
				+ "  and sale.id.isalesvoucherid = salde.id.isalesvoucherid and sale.id.iticketstationid = salde.id.iticketstationid";
		List list1 = this.find(sql1);
		List list2 = this.find(sql2);
		Long amount = 0L;
		if (list1 != null && list1.size() > 0 && list1.get(0)!=null) {
			Long iamount = (Long) list1.get(0);
			if (iamount != null) {
				amount+= iamount;
			}

		}
		if (list2 != null && list2.size() > 0 && list2.get(0)!=null) {
			Long iamount = (Long) list2.get(0);
			if (iamount != null) {
				amount+= iamount;
			}

		}
		return amount;
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
		String hsql1 = "select et.iscenicid as iscenicid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid,sm.isettlementid  as isettlementid,sum(sl.isplitamount) as numb,nvl(sum(sl.ideratenums),0) as ideratenums,sum(sl.msplitmoney) as mont,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.mhandcharge),0) as mhandcharge  from Stssalesvouchertab sale,Stssalessettlementtab sm,Stscomticketsalesdetailstab sl,Edmtickettypetab et where substr(sale.dtmakedate, 1, 10) = '"
					+ date
					+ "' and sm.id.iticketstationid=sale.id.iticketstationid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sale.id.iticketstationid=sl.id.iticketstationid and et.itickettypeid=sl.iztickettypeid group by et.iscenicid,sale.ibusinessid,sm.isettlementid,sale.bysalesvouchertype";
		String hsql2 = "select et.iscenicid as iscenicid,'99' as bysalesvouchertype,c.ibusinessid,'00' as isettlementid,0 as numb,0 as yhnumb,0 as mont,0 as yhmont,sum(yl.mhandcharge) as mhandcharge from MOrder m,YZorderlist yl, Edmtickettypetab et,Custom c where m.orda='"
				+ date
				+ "' and  m.ortp='02' and m.tpfs='02' and m.orid = yl.id.orid and yl.iztickettypeid=et.itickettypeid and c.usid=m.usid   group by et.iscenicid,c.ibusinessid  ";
		String hsql3 = "select et.iscenicid as iscenicid,sale.bysalesvouchertype as bysalesvouchertype,sale.ibusinessid,sm.isettlementid  as isettlementid,sum(sl.isplitamount) as numb,nvl(sum(sl.ideratenums),0) as ideratenums,sum(sl.msplitmoney) as mont,nvl(sum(sl.mderatemoney),0) as mderatemoney,nvl(sum(sl.mhandcharge),0) as mhandcharge  from Stssalesvouchertablist sale,Stssalessettlementtablist sm,Stscomticketsalesdetailstabls sl,Edmtickettypetab et where substr(sale.dtmakedate, 1, 10) = '"
				+ date
				+ "' and sm.id.iticketstationid=sale.id.iticketstationid  and sm.id.isalesvoucherid = sale.id.isalesvoucherid and sl.id.isalesvoucherid = sale.id.isalesvoucherid and sale.id.iticketstationid=sl.id.iticketstationid and et.itickettypeid=sl.iztickettypeid group by et.iscenicid,sale.ibusinessid,sm.isettlementid,sale.bysalesvouchertype";
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		List list3 = this.find(hsql3);
		if(list1!=null && !list1.isEmpty()){
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if(list2!=null && !list2.isEmpty()){
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		if(list3!=null && !list3.isEmpty()){
			for(int i=0;i<list3.size();i++){
				list.add(list3.get(i));
			}
		}
		return list;
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
		String hsql1 = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice,nvl(sum(stcom.isplitamount),0),nvl(sum(stcom.msplitmoney),0),nvl(sum(stcom.ideratenums),0),nvl(sum(stcom.mderatemoney),0),nvl(sum(stcom.mhandcharge),0) from Stssalesvouchertab s,Stssalessettlementtab st,Stscomticketsalesdetailstab stcom,Edmcrowdkindpricetab ep where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=stcom.id.isalesvoucherid and s.id.iticketstationid=stcom.id.iticketstationid and stcom.icrowdkindpriceid=ep.icrowdkindpriceid  group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice";
		String hsql2 = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice,nvl(sum(stcom.isplitamount),0),nvl(sum(stcom.msplitmoney),0),nvl(sum(stcom.ideratenums),0),nvl(sum(stcom.mderatemoney),0),nvl(sum(stcom.mhandcharge),0) from Stssalesvouchertablist s,Stssalessettlementtablist st,Stscomticketsalesdetailstabls stcom,Edmcrowdkindpricetab ep where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=stcom.id.isalesvoucherid and s.id.iticketstationid=stcom.id.iticketstationid and stcom.icrowdkindpriceid=ep.icrowdkindpriceid  group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,stcom.itickettypeid,stcom.iztickettypeid,ep.icrowdkindid,stcom.msplitprice";
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if(list1!=null && !list1.isEmpty()){
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if(list2!=null && !list2.isEmpty()){
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		return list;
	}

	public List updateOrQueryempfznumbByDate(String date) {

		this.deleteDates(date, "1", "Rproviderfznumbdaytab");// �ս�

		List list = new ArrayList();
		String hsql1 = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid,nvl(sum(sd.iticketnum),0),nvl(sum(sd.iticketplayer),0),nvl(sum(sd.ideratenums),0) from Stssalesvouchertab s,Stssalessettlementtab st,Stssalesvoucherdetailstab sd  where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=sd.id.isalesvoucherid and s.id.iticketstationid=sd.id.iticketstationid   group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid ";

		String hsql2 = "select s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid,nvl(sum(sd.iticketnum),0),nvl(sum(sd.iticketplayer),0),nvl(sum(sd.ideratenums),0) from Stssalesvouchertablist s,Stssalessettlementtablist st,Stssalesvoucherdetailstablist sd  where  substr(s.dtmakedate,1,10)='"
				+ date
				+ "' and s.id.isalesvoucherid=st.id.isalesvoucherid and s.id.iticketstationid=st.id.iticketstationid and s.id.isalesvoucherid=sd.id.isalesvoucherid and s.id.iticketstationid=sd.id.iticketstationid   group by s.bysalesvouchertype,s.iscenicid,s.ibusinessid,st.isettlementid,s.id.iticketstationid,s.ihandler,sd.itickettypeid ";
		
		List list1 = this.find(hsql1);
		List list2 = this.find(hsql2);
		if(list1!=null && !list1.isEmpty()){
			for(int i=0;i<list1.size();i++){
				list.add(list1.get(i));
			}
		}
		if(list2!=null && !list2.isEmpty()){
			for(int i=0;i<list2.size();i++){
				list.add(list2.get(i));
			}
		}
		return list;
	}

}
