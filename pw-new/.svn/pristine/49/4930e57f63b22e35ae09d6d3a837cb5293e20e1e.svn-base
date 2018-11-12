package com.ectrip.ticket.sale.service.card.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.balance.Useryfktype;
import com.ectrip.sys.model.balance.Vipbalance;
import com.ectrip.sys.model.balance.Vipbalancetype;
import com.ectrip.sys.model.syspar.Contmessage;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.card.Bindingprice;
import com.ectrip.ticket.model.card.Icconsumerecord;
import com.ectrip.ticket.model.card.Icrechargerecord;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.sale.service.card.core.CoreUtil;
import com.ectrip.ticket.sale.service.card.dao.idao.IOneCardDao;
import com.ectrip.ticket.sale.service.card.model.GetCardsRequest;
import com.ectrip.ticket.sale.service.card.model.GetICItemsRequest;

/**
 * Created by cxh on 2016/03/31.
 */
public class OneCardDao extends GenericDao implements IOneCardDao {
	/**
	 * 
	 * Describe:��ȡ��Ʊ���ѵ�Ĭ��������Ŀ,�ü۸��������֡���Ʊ������ĿΪPXF
	 * @author liujianwen
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2016-10-18
	 */
	public Edmcrowdkindpricetab getTicketConsumePrice(String icrowdkindpricecode){
		String hql = "select e from Edmcrowdkindpricetab e ,Edmtickettypetab t " +
				"where e.itickettypeid = t.itickettypeid and t.bycategorytype='0101' and e.byisuse=1 and t.byisuse=1 and e.icrowdkindpricecode=?";
		List<?> list = this.find(hql, new Object[]{icrowdkindpricecode});
		if(list.size() > 0){
			return (Edmcrowdkindpricetab) list.get(0);
		}
		return null;
	}
	public Custom getOnecardCustom(String usid) {
		String hql = "select c from Custom c,Edmcrowdkindpricetab e ,Edmtickettypetab t " +
				"where c.inote10 = e.icrowdkindpriceid and e.itickettypeid = t.itickettypeid and t.bycategorytype='0100' and c.usid=?";
		System.out.println(hql);
		List<?> list = this.find(hql, new Object[]{usid});
		if(list.size() > 0){
			return (Custom) list.get(0);
		}
		return null;
	}

	/**
	 * Describe:����բ��id��ȡ������Ŀ�б�
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getConsumePrices(com.ectrip.sale.service.card.model.GetCardsRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-13
	 */
	public List<?> getConsumePrices(Long iaccessequipid, String date) {
		String sql = "select distinct new Map("
				+ "bp.sortnum as  sortnum,a.icrowdkindpricecode as icrowdkindpricecode ,a.icrowdkindpriceid as icrowdkindpriceid,a.icrowdkindpricecode as icrowdkindpricecode,"
				+ "a.itickettypeid as itickettypeid,a.ipeoplenumrange as ipeoplenumrange ,a.mactualsaleprice as mactualsaleprice,b.sztickettypecode as sztickettypecode,"
				+ "b.sztickettypename as sztickettypename,b.issale as issale,b.iscansale as iscansale,b.iscontrol as iscontrol,"
				+ "b.iscontrolsale as iscontrolsale,b.validityday as validityday,b.byuselimit as byuselimit,b.bymaketicketway as bymaketicketway,"
				+ "b.bycategorytype as bycategorytype,v55.pmva as strcategory,a.icrowdkindid as icrowdkindid,"
				+ "c.szcrowdkindcode as szcrowdkindcode,c.szcrowdkindname as szcrowdkindname,a.ibusinessid as ibusinessid,"
				+ "d.szbusinesscode as szbusinesscode,d.szbusinessname as szbusinessname"
				+ ",v5.pmva as strmaketype,b.bymediatype as bymediatype,v555.pmva as strmediatype,"
				+"v5JZTP.id.pmcd as jztp, v5JZTP.pmva as jztpStr,"
				+ "a.note1,a.inote1,b.note5 ,a.szmemo as szmemo," +
				"(select etp.inoteger4 from Edmticketproduct etp where etp.itickettypeid=b.itickettypeid) as inoteger4)  "
				+ "from Edmcrowdkindpricetab a ,Edpcrowdkindtab c,Edmbusinesstab d,"
				+ "Sysparv5 v5,Sysparv5 v55,Sysparv5 v555,Sysparv5 v5JZTP,Edmtickettypetab b,"
				+ "Esbscenicareatab pd,Esbaccessequiptab acc, Esbgardengatetab gar ,Bindingprice bp "
				+ "where c.icrowdkindid = a.icrowdkindid and d.ibusinessid = a.ibusinessid and "
				+ "a.byisuse=1 and  a.isscene=1 and "
				+ "a.startdata<= ? and a.enddata>=? "
				+ "and v5.id.pmky='CPFS' "
				+"and v5JZTP.id.pmky='JZTP' and v5JZTP.id.pmcd=bp.jztp "
				+ "and v5.id.pmcd=b.bymaketicketway  and v555.id.pmky='CKFS' and v555.id.pmcd=b.bymediatype "
				+ "and v55.id.pmky='PRTP' and v55.id.pmcd=b.bycategorytype and a.itickettypeid = b.itickettypeid "
				+ "and b.byisuse=1 and pd.iscenicid=b.iscenicid and pd.byisuse=1 and pd.iscenicid=acc.id.iscenicid and acc.id.iaccessequipid=?  " +
				"and acc.id.igardengateid=gar.id.igardengateid and bp.icrowdkindpriceid=a.icrowdkindpriceid and gar.id.igardengateid=bp.igardengateid" +
				"  and gar.byisuse=1 and acc.byisuse=1" 
				+ " and b.bycategorytype	='0101' order by bp.sortnum";
		System.out.println(sql);
		return this.find(
				sql,
				new Object[] { date,date,iaccessequipid});
	}

	public List<?> getCards(GetCardsRequest request) {
		String sql = "select distinct new Map("
				+ "a.isequence as isequence,a.icrowdkindpricecode as icrowdkindpricecode ,a.icrowdkindpriceid as icrowdkindpriceid,a.icrowdkindpricecode as icrowdkindpricecode,"
				+ "a.itickettypeid as itickettypeid,a.ipeoplenumrange as ipeoplenumrange ,a.mactualsaleprice as mactualsaleprice,b.sztickettypecode as sztickettypecode,"
				+ "b.sztickettypename as sztickettypename,b.issale as issale,b.iscansale as iscansale,b.iscontrol as iscontrol,"
				+ "b.iscontrolsale as iscontrolsale,b.validityday as validityday,b.byuselimit as byuselimit,b.bymaketicketway as bymaketicketway,"
				+ "b.bycategorytype as bycategorytype,v55.pmva as strcategory,a.icrowdkindid as icrowdkindid,"
				+ "c.szcrowdkindcode as szcrowdkindcode,c.szcrowdkindname as szcrowdkindname,a.ibusinessid as ibusinessid,"
				+ "d.szbusinesscode as szbusinesscode,d.szbusinessname as szbusinessname"
				+ ",v5.pmva as strmaketype,b.bymediatype as bymediatype,v555.pmva as strmediatype,"
				+ "a.note1,a.inote1,b.note5 ,a.szmemo as szmemo, b.mnominalfee as mnominalfee)  "
				+ "from Edmcrowdkindpricetab a ,Edpcrowdkindtab c,Edmbusinesstab d,Esbticketwintab win,"
				+ "Ospticketwinlimitstab ow,Ospsaleslimitstab oe,Sysparv5 v5,Sysparv5 v55,Sysparv5 v555,Edmtickettypetab b,"
				+ "Esbscenicareatab pd "
				+ "where   c.icrowdkindid = a.icrowdkindid and d.ibusinessid = a.ibusinessid and "
				+ "a.byisuse=1 and  a.isscene=1 and "
				+ "a.startdata<= ? and a.enddata>=? and ow.iticketwinid=? and ow.iticketwinid = win.iticketwinid "
				+ "and ow.icrowdkindpriceid=a.icrowdkindpriceid and oe.iemployeeid=?  "
				+ "and oe.icrowdkindpriceid=a.icrowdkindpriceid and v5.id.pmky='CPFS' "
				+ "and v5.id.pmcd=b.bymaketicketway  and v555.id.pmky='CKFS' and v555.id.pmcd=b.bymediatype "
				+ "and v55.id.pmky='PRTP' and v55.id.pmcd=b.bycategorytype and a.itickettypeid = b.itickettypeid "
				+ "and b.byisuse=1 and pd.iscenicid=b.iscenicid and pd.byisuse=1 and pd.iscenicid=win.iscenicid"
				+ " and b.bycategorytype	='0100' order by a.isequence";
		System.out.println(sql);
		return this.find(
				sql,
				new Object[] { request.getDate(), request.getDate(),
						Long.parseLong(request.getIticketwinid()),
						Long.parseLong(request.getIemployeeid()) });
	}

	public List<?> getICItems(String date,Long icrowdkindpriceid) {
		String sql = "select distinct new Map(i.icitemid as icitemid,i.accpoint as accpoint,i.accpointzs as accpointzs,i.acctypename as acctypename, i.acctypedesc as acctypedesc,i.acctype as acctype, i.startdate as startdate,"
				+ " i.enddate as enddate, i.icrowdkindpriceid as icrowdkindpriceid,  i.status as status,  i.sortnum as sortnum,  i.note1 as note1, i.note2 as note2, i.note3 as note3, i.note4 as note4,  i.inote1 as inote1,"
				+ " i.inote2 as inote2, i.inote3 as inote3, i.inote4 as inote4)"
				+ " from Icitem i, Edmcrowdkindpricetab e, Edmtickettypetab t"
				+ " where i.icrowdkindpriceid = e.icrowdkindpriceid  and e.itickettypeid = t.itickettypeid and e.byisuse = 1 and t.byisuse = 1"
				+ " and i.status = 1  and i.enddate >= ?  and i.startdate <= ? and i.icrowdkindpriceid=? order by i.sortnum";
		System.out.println(sql);
		return this.find(
				sql,
				new Object[] { date,date,
						icrowdkindpriceid });
	}

	public void saveUseryfk(Useryfk yfk) {
		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class,
				yfk.getUsid());
		if (vipbalance == null) {
			// Debug.print("insert  usid 777 ");
			double ye = getUseryfkBalance(yfk.getUsid());
			vipbalance = new Vipbalance();
			// Debug.print("Useryfk usid 777 ="+yfk.getUsid());
			vipbalance.setUsid(yfk.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
			// Debug.print("Useryfk usid 775557 ="+yfk.getUsid());
		} 
		vipbalance.setPoint(CoreUtil.sswr2(vipbalance.getPoint() + yfk.getPoint()
				* yfk.getYfklb()));
		saveOrUpdate(vipbalance);
		save(yfk);
	}

	public void saveUseryfktype(Useryfktype yfk) {
		Vipbalancetype vipbalance = (Vipbalancetype) get(Vipbalancetype.class,
				yfk.getUsid());
		if (vipbalance == null) {
			// Debug.print("insert  usid 777 ");
			double ye = getUseryfktypeBalance(yfk.getUsid());
			vipbalance = new Vipbalancetype();
			// Debug.print("Useryfk usid 777 ="+yfk.getUsid());
			vipbalance.setUsid(yfk.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
			// Debug.print("Useryfk usid 775557 ="+yfk.getUsid());
		} 
		vipbalance.setPoint(CoreUtil.sswr2(vipbalance.getPoint() + yfk.getPoint()
				* yfk.getYfklb()));
		saveOrUpdate(vipbalance);
		save(yfk);

	}

	@SuppressWarnings("rawtypes")
	public double getUseryfkBalance(String usid) {
		String hql = " select new map( sum ( yfk.point * yfk.yfklb ) as sumjifen ) From Useryfk yfk  where yfk.usid = '"
				+ usid + "'";
		List list = this.find(hql);
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		double sum = 0;
		if (list.size() > 0) {
			// һ����¼�����е��ֶ�ֵ����map���һ��Ԫ��,key���ַ���0,1,2,3....��value���ֶ�ֵ
			// �����hql��Ϊ��String hql =
			// " select new map(name as username,passwd as password) from Users";,��ôkey�������ַ���0,1,2...�ˣ�����"username","password"��
			Map map = (Map) list.get(0);
			Double sumjifen = (Double) map.get("sumjifen"); // ȡ���˶������
			if(sumjifen != null) sum = sumjifen;
		} 
		return CoreUtil.sswr2(sum);
	}
	@SuppressWarnings("rawtypes")
	public double getUseryfktypeBalance(String usid) {
		String hql = " select new map( sum ( yfk.point * yfk.yfklb ) as sumjifen ) From Useryfktype yfk  where yfk.usid = '"
				+ usid + "'";
		List list = this.find(hql);
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		double sum = 0;
		if (list.size() > 0) {
			// һ����¼�����е��ֶ�ֵ����map���һ��Ԫ��,key���ַ���0,1,2,3....��value���ֶ�ֵ
			// �����hql��Ϊ��String hql =
			// " select new map(name as username,passwd as password) from Users";,��ôkey�������ַ���0,1,2...�ˣ�����"username","password"��
			Map map = (Map) list.get(0);
			Double sumjifen = (Double) map.get("sumjifen"); // ȡ���˶������
			if(sumjifen != null) sum = sumjifen;
		} 
		return CoreUtil.sswr2(sum);
	}

	public List<?> getCustoms(GetICItemsRequest request, int count) {
		String hql = "select distinct new Map(c.lname as lname,c.ldate as ldate,c.usid as usid, c.mobile as mobile, c.note10 as note10,c.zjhm as zjhm,c.inote10 as inote10  ) " +
				"From Custom c,Edmcrowdkindpricetab e where c.lgtp='01' and c.inote10 = e.icrowdkindpriceid and (c.lname like '%"+request.getKey()+"%' or c.usid like '%"+request.getKey()+"%' or c.mobile like '%"+request.getKey()+"%' or c.note10 like '%"+request.getKey()+"%' or c.zjhm like '%"+request.getKey()+"%') order by c.ldate desc";
		return this.findTopNumb(hql,count);
	}
	public List<?> getRechargeHistoryByUsid(String usid, String startDate,
			String endDate) {
		String hql =
				"select new Map(r.orid as orid,"+
						" v1.pmva as cztpStr,"+
						" sum(r.amount) as amount,"+
						" sum(r.accpointzs) as accpointzs,"+
						"c.usid as usid,"+
						"c.note10 as cardNum,"+
						"win.szticketwinname as szticketwinname,"+
						"emp.szemployeename as szemployeename,"+
						"r.rechargedate as rechargedate) "+
						"from Icrechargerecord r,"+
						"Custom c,"+
						"Sysparv5 v1,"+
						"Esbticketwintab  win,"+
						"Esfemployeetab  emp "+
						"where r.szticketprintno = c.usid "+
						"and r.cztp = v1.id.pmcd "+
						"and v1.id.pmky = 'CZTP' "+
						"and win.iticketwinid = r.iticketwinid "+
						"and r.iemployeeid = emp.iemployeeid "+
						"and c.usid=? and r.rechargedate>=? and r.rechargedate<=? "+
						"group by (r.orid, v1.pmva, c.usid, c.note10, r.rechargedate,"+
						"win.szticketwinname, emp.szemployeename) "+
						"order by r.orid desc";
		System.out.println(hql);
		return this.find(hql, new Object[]{usid,startDate,endDate});
	}
	public List<?> getConsumeHistoryByUsid(String usid, String startDate,
			String endDate) {
		String hql = "select new Map(con.orid as orid, "+
				"v1.pmva as cztpStr,"+
				"sum(con.consumeamount) as consumeamount, "+
				"c.usid as usid, "+
				"c.note10 as cardNum, "+
				"gar.szgardengatename as szgardengatename, "+
				"acc.szaccessequipname as szaccessequipname, "+
				" con.consumedate as consumedate )"+
				"from Icconsumerecord con,"+
				"Esbgardengatetab gar,"+
				" Esbaccessequiptab acc,"+
				" Custom c,"+
				"Sysparv5 v1 "+
				" where con.usid = c.usid "+
				"and con.cztp = v1.id.pmcd "+
				"and v1.id.pmky = 'CZTP' "+
				"and con.iaccessequipid=acc.id.iaccessequipid "+
				"and acc.id.igardengateid=gar.id.igardengateid "+
				"and con.usid=? and con.consumedate>=?  and con.consumedate<=?"+
				"group by (con.orid, v1.pmva, c.usid, c.note10, "+
				"gar.szgardengatename,acc.szaccessequipname,con.consumedate) "+
				" order by con.orid desc";
		System.out.println(hql);
		return this.find(hql, new Object[]{usid,startDate, endDate});
	}

	public List<?> getTopConsumeByUsid(String usid, int count) {
		String hql = "select distinct new Map(c.icconsumerecordid as icconsumerecordid,c.orid as orid,c.icrowdkindpriceid as icrowdkindpriceid, " +
				"c.consumeamount as consumeamount, c.usid as usid,c.iaccessequipid as iaccessequipid,c.xffs as xffs," +
				"t.sztickettypename as sztickettypename,c.xfzt as xfzt, v2.pmva as xfztStr, v1.pmva as xffsStr, g.szgardengatename as szgardengatename,"+
				"c.consumedate as consumedate, c.checkoutdate as checkoutdate,c.note as note)" +
				"From Icconsumerecord c,Esbaccessequiptab dev,Esbgardengatetab g,Sysparv5 v1,Sysparv5 v2,Edmcrowdkindpricetab p,Edmtickettypetab t where " +
				"c.usid='"+usid+"' and c.iaccessequipid = dev.id.iaccessequipid and  dev.id.igardengateid = g.id.igardengateid and "+
				"v2.id.pmky='XFZT' and v2.id.pmcd=c.xfzt and "+
				"v1.id.pmky='XFFS' and v1.id.pmcd = c.xffs and c.icrowdkindpriceid = p.icrowdkindpriceid and p.itickettypeid = t.itickettypeid "+
				"order by c.icconsumerecordid desc";
		System.out.println(hql);
		return this.findTopNumb(hql,count);
	}
	/**
	 * Describe:
	 * @author liujianwen
	 * @param orid
	 * @param isSumRefund �Ƿ��ѯ�ѳ������
	 * @return
	 * return:List<?>
	 * Date:2016-5-30
	 */
	public List<?> getConsumeByOrid(String orid, boolean isSumRefund) {
		String hql = "select distinct new Map(kind.szcrowdkindname as szcrowdkindname,bus.szbusinessname as szbusinessname,cus.usid as usid,c.icconsumerecordid as icconsumerecordid,c.orid as orid,c.icrowdkindpriceid as icrowdkindpriceid, " +
				"c.consumeamount as consumeamount, c.usid as usid,c.iaccessequipid as iaccessequipid,c.xffs as xffs,c.cztp as cztp,v2.pmva as cztpStr," +
				"t.sztickettypename as sztickettypename, v1.pmva as xffsStr, g.szgardengatename as szgardengatename,"+
				"c.consumedate as time, c.note as note,dev.szaccessequipname as szaccessequipname," +
				"(case c.cztp when '03' then 'true' when '06' then 'true' when '07' then 'true' else 'false' end) as isCanRefund,"+
				"cus.note10 as cardNum,cus.lname as lname,t1.sztickettypename as cardName,dev.szaccessequipname as szaccessequipname," +
				"(select emp1.szemployeename from Esfemployeetab emp1 where emp1.iemployeeid=c.piemployeeid) as sqname," +
				"(select emp2.szemployeename from Esfemployeetab emp2 where emp2.iemployeeid=c.iemployeeid) as czname," +
				"(select c1.orid from Icconsumerecord c1 where c1.icconsumerecordid=c.picconsumerecordid) as porid" +
				(isSumRefund?",(select sum(con1.consumeamount)  From Icconsumerecord con1 where con1.cztp='04' and con1.picconsumerecordid=c.icconsumerecordid) as refundamount":"")+
				")" +
				"From Icconsumerecord c,Esbaccessequiptab dev,Esbgardengatetab g,Sysparv5 v1,Sysparv5 v2," +
				"Edmcrowdkindpricetab p,Edmtickettypetab t," +
				"Edpcrowdkindtab kind,Edmbusinesstab bus," +
				"Custom cus,Edmcrowdkindpricetab p1,Edmtickettypetab t1 where " +
				"p.ibusinessid = bus.ibusinessid and p.icrowdkindid = kind.icrowdkindid and " +
				"c.orid=? and c.iaccessequipid = dev.id.iaccessequipid and  dev.id.igardengateid = g.id.igardengateid and "+
				"v1.id.pmky='XFFS' and v1.id.pmcd = c.xffs and v2.id.pmky='CZTP' and v2.id.pmcd=c.cztp and c.icrowdkindpriceid = p.icrowdkindpriceid and p.itickettypeid = t.itickettypeid "+
				"and c.usid=cus.usid and cus.inote10= p1.icrowdkindpriceid and p1.itickettypeid = t1.itickettypeid order by c.icconsumerecordid desc";
		System.out.println(hql);
		return this.find(hql,new Object[]{orid});
	}
	/**
	 * Describe:
	 * @author liujianwen
	 * @param orid
	 * @param isSumRefund �Ƿ��ѯ�Ѿ������Ľ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-30
	 */
	public List<?> getRechargeByOrid(String orid, boolean isSumRefund) {
		String hql = "select distinct new Map(cus.usid as usid,c.icrechargerecordid as icrechargerecordid,c.orid as orid,c.iemployeeid as iemployeeid, " +
				"emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,v1.pmva as cztpStr,v2.pmva as zffsStr, "+
				"c.iticketwinid as iticketwinid, c.szticketprintno as szticketprintno,c.cardno as cardno,c.oldcardno as oldcardno," +
				"c.oldszticketprintno as oldszticketprintno, c.cztp as cztp," +
				"c.amount as amount,c.accpointzs as accpointzs,c.zffs as zffs,c.rechargedate as time, c.note as note," +
				"cus.note10 as cardNum,cus.lname as lname,t1.sztickettypename as cardName," +
				"(select emp1.szemployeename from Esfemployeetab emp1 where emp1.iemployeeid=c.piemployeeid) as sqname," +
				"(select c1.orid from Icrechargerecord c1 where c1.icrechargerecordid=c.picrechargerecordid) as porid" +
				(isSumRefund?",(select sum (con1.amount) From Icrechargerecord con1 where con1.cztp='05' and con1.picrechargerecordid=c.icrechargerecordid) " +
						"as refundamount,(select sum (con2.accpointzs) From Icrechargerecord con2 where con2.cztp='05' and con2.picrechargerecordid=c.icrechargerecordid) as refundaccpointzs":"")+
						") " +
						"From Icrechargerecord c,Esfemployeetab emp,Esbticketwintab win,Sysparv5 v1,Sysparv5 v2," +
						"Custom cus,Edmcrowdkindpricetab p1,Edmtickettypetab t1 where " +
						"c.orid=? and c.iemployeeid = emp.iemployeeid and  c.iticketwinid = win.iticketwinid and "+
						"v1.id.pmky='CZTP' and v1.id.pmcd = c.cztp and v2.id.pmky='ZFFS' and v2.id.pmcd = c.zffs "+
						"and emp.iemployeeid = c.iemployeeid and c.szticketprintno=cus.usid and cus.inote10= p1.icrowdkindpriceid and p1.itickettypeid = t1.itickettypeid order by c.icrechargerecordid desc";
		System.out.println(hql);
		return this.find(hql,new Object[]{orid});
	}


	public List<?> getTopRechargeByUsid(String usid, int count) {
		String hql = "select distinct new Map(c.icrechargerecordid as icrechargerecordid,c.orid as orid,c.iemployeeid as iemployeeid, " +
				"emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,v1.pmva as cztpStr,v2.pmva as zffsStr, "+
				"c.iticketwinid as iticketwinid, c.szticketprintno as szticketprintno,c.cardno as cardno,c.oldcardno as oldcardno," +
				"c.oldszticketprintno as oldszticketprintno, c.cztp as cztp," +
				"c.amount as amount,c.accpointzs as accpointzs,c.zffs as zffs,c.rechargedate as rechargedate, c.note as note ) " +
				"From Icrechargerecord c,Esfemployeetab emp,Esbticketwintab win,Sysparv5 v1,Sysparv5 v2 where " +
				"c.szticketprintno='"+usid+"' and c.iemployeeid = emp.iemployeeid and  c.iticketwinid = win.iticketwinid and "+
				"v1.id.pmky='CZTP' and v1.id.pmcd = c.cztp and v2.id.pmky='ZFFS' and v2.id.pmcd = c.zffs "+
				"order by c.icrechargerecordid desc";
		System.out.println(hql);
		return this.findTopNumb(hql,count);
	}
	/**
	 * Describe:������Ʊ����ԭ�û�������漰����������,�µ�Ʊ�Ž��洢��Icrechargecord��
	 * @author liujianwen
	 * @param usid ���ڲ�����
	 * @return ���Ϊnull����δ��������
	 * return:String 
	 * Date:2016-5-11
	 */
	@SuppressWarnings("unchecked")
	public Custom getBukaUsid(String usid){
		String hql = "from Icrechargerecord i where i.cztp='01' and i.newszticketprintno='"+usid+"' order by i.icrechargerecordid desc";
		System.out.println(hql);
		List<Icrechargerecord> list = this.findTopNumb(hql,1);
		if(list.size() > 0) {
			System.out.println(hql);
			hql = "from Icrechargerecord i where i.cztp='01' and i.szticketprintno='"+ list.get(0).getSzticketprintno()+"' order by i.icrechargerecordid desc";
			list = this.findTopNumb(hql,1);
			if(list.size() > 0 &&  usid.equals( list.get(0).getNewszticketprintno())) {
				return (Custom) this.get(Custom.class, list.get(0).getSzticketprintno());
			}
		}
		return null;
	}

	/**
	 * Describe:�ж�ʦ�Ƿ񲹹���
	 * @author liujianwen
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2016-5-12
	 */
	public boolean isReplaceCard(String usid){
		String hql = "from Icrechargerecord i where i.cztp='01' and i.szticketprintno='"+usid+"' order by i.icrechargerecordid desc";
		System.out.println(hql);
		@SuppressWarnings("unchecked")
		List<Icrechargerecord> list = this.findTopNumb(hql,1);
		return list.size() > 0;
	}
	/**
	 * Describe:���ݴ���id�������豸id
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getEsbaccessequiptabByIemployeeid(Long)
	 * @param iticketwinid
	 * @return
	 * @author liujianwen
	 * Date:2016-5-13
	 */
	public Esbaccessequiptab getEsbaccessequiptabByIticketwinid(Long iticketwinid) {
		String hql = "select acc from Esbticketwintab e ,Esbaccessequiptab acc where e.iscenicid=acc.id.iscenicid " +
				"and e.iticketwinid = ? and e.szipaddress= acc.szipaddress and acc.byisuse=1";
		System.out.println(hql);
		List<?> list = this.find(hql, new Object[]{iticketwinid});
		if(list.size() > 0){
			return (Esbaccessequiptab) list.get(0);
		}
		return null;
	}

	/**
	 * Describe:����������Ŀ
	 * @author liujianwen
	 * @param icrowdkindpriceid
	 * @param iaccessequipid
	 * @return
	 * return:Esbaccessequiptab
	 * Date:2016-5-13
	 */
	public Edmcrowdkindpricetab getConsumeEdmcrowdkindpricetab(Long icrowdkindpriceid, Long iaccessequipid) {
		String hql = "select e from Edmcrowdkindpricetab e ,Edmtickettypetab t,Esbaccessequiptab acc,Bindingprice bp " +
				"where e.itickettypeid=t.itickettypeid and t.iscenicid=acc.id.iscenicid and bp.icrowdkindpriceid = e.icrowdkindpriceid and " +
				"acc.id.igardengateid = bp.igardengateid and " +
				"e.byisuse = 1 and acc.byisuse=1 and t.byisuse=1 and e.icrowdkindpriceid=? and acc.id.iaccessequipid=?";
		System.out.println(hql);
		List<?> list = this.find(hql, new Object[]{icrowdkindpriceid,iaccessequipid});
		if(list.size() > 0){
			return (Edmcrowdkindpricetab) list.get(0);
		}
		return null;
	}

	/**
	 * Describe:
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getBindingprice(Long, Long)
	 * @param icrowdkindpriceid
	 * @param igardengateid
	 * @return
	 * @author liujianwen
	 * Date:2016-5-14
	 */
	public Bindingprice getBindingprice(Long icrowdkindpriceid,
			Long iaccessequipid) {
		String hql = "select bp from Edmcrowdkindpricetab e ,Esbgardengatetab gar,Esbaccessequiptab acc,Bindingprice bp " +
				"where bp.icrowdkindpriceid = e.icrowdkindpriceid and " +
				"gar.id.igardengateid = bp.igardengateid and gar.id.igardengateid=acc.id.igardengateid and " +
				"e.byisuse = 1 and acc.byisuse=1 and bp.icrowdkindpriceid=? and acc.id.iaccessequipid=?";
		System.out.println(hql);
		List<?> list = this.find(hql, new Object[]{icrowdkindpriceid,iaccessequipid});
		if(list.size() > 0){
			return (Bindingprice) list.get(0);
		}
		return null;

	}

	public Edmcrowdkindpricetab getFirstConsumeEdmcrowdkindpricetab(
			Long iaccessequipid) {
		String hql = "select e from Edmcrowdkindpricetab e ,Edmtickettypetab t ,Esbaccessequiptab acc,Bindingprice bp " +
				"where e.itickettypeid = t.itickettypeid and t.iscenicid=acc.id.iscenicid and bp.icrowdkindpriceid = e.icrowdkindpriceid and " +
				"acc.id.igardengateid = bp.igardengateid and " +
				"e.byisuse = 1 and acc.byisuse=1 and acc.id.iaccessequipid=? order by bp.sortnum";
		System.out.println(hql);
		List<?> list = this.find(hql, new Object[]{iaccessequipid});
		if(list.size() > 0){
			return (Edmcrowdkindpricetab) list.get(0);
		}
		return null;

	}

	public double getRefundConsumeMoney(Long icconsumerecordid) {
		String hql = " select new map( sum (con.consumeamount) as jine ) From Icconsumerecord con where con.cztp='04' and con.picconsumerecordid=?";
		List<?> list = this.find(hql,new Object[]{icconsumerecordid});
		double sum = 0;
		if (list.size() > 0) {
			Map<?, ?> map = (Map<?, ?>) list.get(0);
			Double sumjifen = (Double) map.get("jine"); // ȡ���˶������
			if(sumjifen != null) sum = sumjifen;
		} 
		return CoreUtil.sswr2(sum);
	}

	@SuppressWarnings("unchecked")
	public Map<String,Double> getRefundRechargeMoney(Long icrechargerecordid) {
		String hql = " select new map( sum (con.amount) as amount, sum (con.accpointzs) as accpointzs ) From Icrechargerecord con where con.cztp='05' and con.picrechargerecordid=?";
		List<?> list = this.find(hql,new Object[]{icrechargerecordid});
		Map<String,Double> sum ;
		if (list.size() > 0){
			sum = (Map<String,Double>) list.get(0);
			if(sum.get("amount") == null) sum.put("amount", 0d);
			if(sum.get("accpointzs") == null) sum.put("accpointzs", 0d);
		}else {
			sum = new HashMap<String,Double>();
			sum.put("amount", 0d);
			sum.put("accpointzs", 0d);
		}
		return sum;
	}
	/**
	 * Describe:����������Ŀ���û�,��ȡ�����һ�����Ѽ�¼
	 * @author liujianwen
	 * @return
	 * return:Icconsumerecord
	 * Date:2016-5-31
	 */
	@SuppressWarnings("unchecked")
	public Icconsumerecord getRecentlyIcconsumerecord(Long icrowdkindpriceid,String usid){
		String hql = "from Icconsumerecord con where con.cztp='03' and con.icrowdkindpriceid="+icrowdkindpriceid+" and con.usid='"+usid+"' order by con.icconsumerecordid desc";
		List<Icconsumerecord> list = this.findTopNumb(hql,1);
		if(list != null && list.size() > 0) return list.get(0);
		return null;

	}

	@SuppressWarnings("rawtypes")
	public void saveMbmessage(long seq,String phone, String mesg, String... values) {
		String hql = " from Contmessage where controlpoin=? and byisuse=1  and inote1 is null";
		List contlist = this.find(hql, new Object[]{mesg});
		if (contlist != null && contlist.size() > 0) {
			Contmessage templates = (Contmessage) contlist.get(0);
			Mbmessage mbmessage = new Mbmessage();
			mbmessage.setSeq(seq);
			mbmessage.setRevmbno(phone);
			mbmessage.setDtime(Tools.getNowString());
			mbmessage.setIsok(0);
			String note = templates.getTemplates();
			if(values != null && values.length > 0){
				for(int i = 0; i < values.length; i ++){
					note = note.replaceFirst("\\{"+(i+1)+"\\}", values[i]);
				}
			}
			mbmessage.setNote(note);
			mbmessage.setQuantity(1);
			this.save(mbmessage);
		}
	}
	/**
	 * *
	 * Describe: ����δ���˽��(��ʱֻ�������)
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getUseryfkBalance(String)
	 * @param usid
	 * @return
	 * @author liujianwen
	 * Date:2016-7-2
	 */
	@SuppressWarnings("rawtypes")
	public double getUncheckoutMoney(String usid) {
		String hql = " select new map( sum (con.consumeamount) as money ) From Icconsumerecord con  " +
				"where con.usid = ? and con.xfzt = '01'";
		List list = this.find(hql, new Object[]{usid});
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		double sum = 0;
		if (list.size() > 0) {
			// һ����¼�����е��ֶ�ֵ����map���һ��Ԫ��,key���ַ���0,1,2,3....��value���ֶ�ֵ
			// �����hql��Ϊ��String hql =
			// " select new map(name as username,passwd as password) from Users";,��ôkey�������ַ���0,1,2...�ˣ�����"username","password"��
			Map map = (Map) list.get(0);
			Double sumjifen = (Double) map.get("money"); // ȡ���˶������
			if(sumjifen != null) sum = sumjifen;
		} 
		return CoreUtil.sswr2(sum);
	}

	public List<?> getUncheckoutList(String usid, boolean onlyUncheckout) {
		String hql = "select distinct new Map(c as self,kind.szcrowdkindname as szcrowdkindname,bus.szbusinessname as szbusinessname,cus.usid as usid,c.icconsumerecordid as icconsumerecordid,c.orid as orid,c.icrowdkindpriceid as icrowdkindpriceid, " +
				"c.consumeamount as consumeamount, c.usid as usid,c.iaccessequipid as iaccessequipid,c.xffs as xffs,c.cztp as cztp,v2.pmva as cztpStr," +
				"c.xfzt as xfzt,v3.pmva as xfztStr,c.timekeeping as timekeeping," +
				"t.sztickettypename as sztickettypename, v1.pmva as xffsStr, g.szgardengatename as szgardengatename,"+
				"c.consumedate as consumedate, c.note as note,dev.szaccessequipname as szaccessequipname," +
				"cus.note10 as cardNum,cus.lname as lname,t1.sztickettypename as cardName,dev.szaccessequipname as szaccessequipname," +
				"(select emp1.szemployeename from Esfemployeetab emp1 where emp1.iemployeeid=c.piemployeeid) as sqname," +
				"(select emp2.szemployeename from Esfemployeetab emp2 where emp2.iemployeeid=c.iemployeeid) as czname," +
				"(select c1.orid from Icconsumerecord c1 where c1.icconsumerecordid=c.picconsumerecordid) as porid) " +
				"From Icconsumerecord c,Esbaccessequiptab dev,Esbgardengatetab g,Sysparv5 v1,Sysparv5 v2,Sysparv5 v3," +
				"Edmcrowdkindpricetab p,Edmtickettypetab t," +
				"Edpcrowdkindtab kind,Edmbusinesstab bus," +
				"Custom cus,Edmcrowdkindpricetab p1,Edmtickettypetab t1 where " +
				"c.usid = ? and " +(onlyUncheckout?"c.xfzt <>'00' and ":"")+
				"p.ibusinessid = bus.ibusinessid and p.icrowdkindid = kind.icrowdkindid and " +
				"c.iaccessequipid = dev.id.iaccessequipid and  dev.id.igardengateid = g.id.igardengateid and "+
				"v1.id.pmky='XFFS' and v1.id.pmcd = c.xffs and v2.id.pmky='CZTP' and v2.id.pmcd=c.cztp " +
				"and v3.id.pmky='XFZT' and c.xfzt=v3.id.pmcd and c.icrowdkindpriceid = p.icrowdkindpriceid and p.itickettypeid = t.itickettypeid "+
				"and c.usid=cus.usid and cus.inote10= p1.icrowdkindpriceid and p1.itickettypeid = t1.itickettypeid order by c.icconsumerecordid desc";
		System.out.println(hql);
		return this.find(hql,new Object[]{usid});
	}
	/**
	 * 
	 * Describe:��ȡ�û�Ѻ��
	 * @author liujianwen
	 * @return
	 * return:double
	 * Date:2016-9-22
	 */
	public double findDeposit(String usid){
		String hql = "from Icrechargerecord r where r.szticketprintno=? and r.cztp = '09' and r.inote1 = 1";//Ѻ��
		List list = this.find(hql, new Object[]{usid});
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		double sum = 0;
		for (Object object : list) {
			Icrechargerecord record = (Icrechargerecord) object;
			sum += record.getAmount();
		}
		return CoreUtil.sswr2(sum);
	}
	
	/**
	 * 
	 * Describe:����Ѻ��
	 * @author liujianwen
	 * @param usid
	 * @return
	 * return:double
	 * Date:2016-9-22
	 */
	public void finishDeposit(String usid){
		String hql = "from Icrechargerecord r where r.szticketprintno=" + usid + " and r.cztp = '09' and r.inote1 = 1";//Ѻ��
		List<Icrechargerecord> list = this.find(hql);
        if(list != null && !list.isEmpty()){
            for (Icrechargerecord icrechargerecord : list){
                icrechargerecord.setInote1(0L);
                this.update(icrechargerecord);
            }
        }
	}
	public Icconsumerecord getTicketConsumeByThirdpartyOrid(
			String thirdpartyOrid) {
		String hql = "from Icconsumerecord r where r.note1=?";//Ѻ��
		List list =  this.find(hql,new Object[]{thirdpartyOrid});
		if(list != null && list.size() > 0) return (Icconsumerecord) list.get(0);
		return null;
	}
}
