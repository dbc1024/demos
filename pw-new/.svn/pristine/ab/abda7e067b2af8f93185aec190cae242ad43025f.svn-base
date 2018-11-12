package com.ectrip.ticket.sale.service.cytterminal.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.sale.service.cytterminal.dao.idao.ICytSaleDao;
import com.ectrip.ticket.sale.service.cytterminal.model.Cyttasktab;
@SuppressWarnings("unchecked")
@Repository
public class CytSaleDao extends GenericDao implements ICytSaleDao {

	/**
	 * *
	 * Describe:获取系统参数
	 * @see com.ectrip.sale.dao.idao.ICytSaleDao#findSyspar(java.lang.String, java.lang.String)
	 * @param pmky
	 * @param pmcd
	 * @return
	 * @author chenxinhao
	 * Date:2015-10-29
	 */
	public Sysparv5 findSyspar(String pmky,String pmcd){
		String hql = "from Sysparv5 where id.pmky = ? and id.pmcd = ? and isvalue = 1 ";
		List list = this.find(hql,new Object[] {pmky,pmcd});
		if(list != null && !list.isEmpty()){
			return (Sysparv5) list.get(0);
		}
		return null;
	}

	/**
	 *
	 * Describe:获取企业编码
	 * @author:chenxinhao
	 * @param iscenicid
	 * @return
	 * return:String
	 * Date:2015-10-29
	 */
	public String findOTOCode(Long iscenicid){
		String otoCode = "";
		List<Companyscenic> csl = this.find("from Companyscenic c where c.id.iscenicid=? and c.cytonly='1'",
				new Object[] { iscenicid });
		if(csl != null && !csl.isEmpty()){
			Galcompanyinfotab company = (Galcompanyinfotab) this.get(
					Galcompanyinfotab.class, csl.get(0).getId()
							.getIcompanyinfoid());
			otoCode = company.getSzcompanycode();
		}
		return otoCode;
	}

	/**
	 * *
	 * Describe:获取订单未完成任务
	 * @see com.ectrip.sale.service.cytterminal.dao.idao.ICytSaleDao#findCytTask(java.lang.String)
	 * @param orid
	 * @return
	 * @author chenxinhao
	 * Date:2015-10-30
	 */
	public Cyttasktab findCytTask(String orid){
		List list = this.find("from Cyttasktab where id.orid='"+orid+"' and status = 0 ");
		if(list != null && !list.isEmpty()){
			return (Cyttasktab) list.get(0);
		}
		return null;
	}

	public List<Edmticketcomposepricetab> finSonPrice(Long priceId){
		String hql = "from Edmticketcomposepricetab where id.icrowdkindpriceid = "+priceId;
		return this.find(hql);
	}

	public List ChupiaoT_order(String orid, Long iscenicid) {
		String sql = " select new map(t.id.orid as orid,t.id.iscenicid as iscenicid,t.scenictype as scenictype,t.orfl as orfl,t.usid as usid,t.ibusinessid as ibusinessid,t.sztravelbillno as sztravelbillno," +
				"t.iregionalid as iregionalid,t.tdlx as tdlx,t.ddzt as ddzt,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.dyusid as dyusid,t.ornm as ornm,t.orzj as orzj,t.orhm as orhm," +
				"t.orph as orph,t.faxno as faxno,t.mont as mont,t.yhamnt as yhamnt,t.zfmont as zfmont,t.youfei as youfei,t.isjfjf as isjfjf,t.ischupiao as ischupiao,t.fempid as fempid,t.forcedrefund as forcedrefund," +
				"t.isa as isa,t.isb as isb,t.isc as isc,t.isd as isd,t.ise as ise,t.isf as isf,t.isg as isg,t.ish as ish,t.isi as isi,t.isj as isj,t.notea as notea,t.notej as notej,t.notei as notei,t.noteh as noteh,t.noteg as noteg,t.notef as notef,t.notee as notee," +
				"t.noted as noted,t.notec as notec,t.noteb as noteb,t.ornote1 as ornote1,t.ornote2 as ornote2,t.ornote3 as ornote3,t.ornote4 as ornote4,t.ornote5 as ornote5,t.ornote6 as ornote6," +
				"t.ornote7 as ornote7,t.ornote8 as ornote8,t.ornote9 as ornote9,t.ornote10 as ornote10 ,m.orda||' '||m.orti as dtmakedate) " +
				"from TOrder t,MOrder m where  t.id.orid='"+orid+"' and t.id.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid = "+iscenicid+") or iscenicid = "+iscenicid+")) and m.orid=t.id.orid";
		return this.find(sql);

	}

	public List ChupiaoT_orderlist(String orid, Long iscenicid) {
		String sql = " select new map(id.orid as orid,id.orderlistid as orderlistid,id.iscenicid as iscenicid,itickettypeid as itickettypeid,icrowdkindpriceid as icrowdkindpriceid,icrowdkindid as icrowdkindid," +
				"dtstartdate as dtstartdate,dtenddate as dtenddate,starttime as starttime,endtime as endtime ,pric as pric,numb as numb,yhnumb as yhnumb,amnt as amnt,yhamnt as yhamnt,ioffersschemeid as ioffersschemeid,isa as isa,isb as isb," +
				"isc as isc,isd as isd,ise as ise,isf as isf,isg as isg,ish as ish,isi as isi,isj as isj,notea as notea,notej as notej,notei as notei,noteh as noteh,noteg as noteg,notef as notef," +
				"notee as notee,noted as noted,notec as notec,noteb as noteb,jsprice as jsprice,'00' as bymaketicketway,'00' as szticketprintno) from TOrderlist t where  orid='"+orid+"'" +
				"  and id.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid ="+iscenicid+" ) or iscenicid = "+iscenicid+")) and numb>0 order by id.orderlistid";
		return this.find(sql);

	}

	public List ChupiaoT_zorderlist(String orid, Long iscenicid) {
		// 读取子票信息前，对竹筏时间进行更新
		String hsql = " from TZorderlist where id.orid='"
				+ orid
				+ "' and id.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid ="
				+ iscenicid + ") or iscenicid = " + iscenicid
				+ ")) and tripid>0";
		List zlist = find(hsql);
		Long iztickettypeid = new Long(0);
		Long tripid = new Long(0);
		String stdt = "";

		for (int i = 0; i < zlist.size(); i++) {
			TZorderlist t = (TZorderlist) zlist.get(i);
			Prdtripvenuemanage p = null;
			if (iztickettypeid != t.getIztickettypeid()
					|| tripid != t.getTripid()
					|| !stdt.equals(t.getDtstartdate().substring(0, 10))) {
				iztickettypeid = t.getIztickettypeid();
				tripid = t.getTripid();
				stdt = t.getDtstartdate().substring(0, 10);
				String hsql1 = "from Prdtripvenuemanage where tripid=" + tripid
						+ " and itickettypeid=" + iztickettypeid
						+ " and startdata<='" + stdt + "' and enddata>='"
						+ stdt + "'";
				List plist = find(hsql1);
				if (plist != null && plist.size() > 0) {
					System.out.println("t_zorderlist4");
					p = (Prdtripvenuemanage) plist.get(0);
				}
			}
			System.out.println("t_zorderlist5");
			if (p != null && p.getStartdata() != null
					&& !p.getStartdata().equals("")) {
				t.setDtstartdate(stdt + " " + p.getStarttime() + ":00");
				t.setDtenddate(stdt + " " + p.getEndtime() + ":00");
				update(t);
			}
		}
		String sql = " select new map(id.orid as orid,id.zorderlistid as zorderlistid,id.orderlistid as orderlistid,id.iscenicid as iscenicid,icrowdkindpriceid as icrowdkindpriceid,icrowdkindid as icrowdkindid,itickettypeid as itickettypeid," +
				"iztickettypeid as iztickettypeid,dtstartdate as dtstartdate,dtenddate as dtenddate,tripid as tripid,ivenueid as ivenueid,ivenueareaid as ivenueareaid,ivenueseatsid as ivenueseatsid,zpric as zpric," +
				"znumb as znumb,zyhnumb as zyhnumb,zyhamnt as zyhamnt,zamnt as zamnt,isa as isa,isb as isb,isc as isc,isd as isd,ise as ise,isf as isf,isg as isg,ish as ish,isi as isi,isj as isj," +
				"notea as notea,notej as notej,notei as notei,noteh as noteh,noteg as noteg,notef as notef,notee as notee,noted as noted,notec as notec,noteb as noteb,jsprice as jsprice) from TZorderlist  " +
				"where  id.orid='"+orid+"'  and id.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid ="+iscenicid+" ) or iscenicid = "+iscenicid+")) and znumb>0 order by id.orderlistid,id.zorderlistid";
		List<Map> list = new ArrayList();
		try {
			list = find(sql);
			for (Map map : list) {
				String seats = "";
				if (!map.get("ivenueseatsid").toString().equals("null")
						&& !map.get("ivenueseatsid").toString().equals("NULL")
						&& !map.get("ivenueseatsid").toString().equals("0")) {
					Long zorderlistid = new Long(map.get("zorderlistid")
							.toString());
					Long orderlistid = new Long(map.get("orderlistid")
							.toString());
					Long ziscenicid = new Long(map.get("iscenicid").toString());
					List seatlist = find(" from Seatordertab where id.orid='" + orid
							+ "' and id.orderlistid=" + orderlistid
							+ " and id.iscenicid=" + ziscenicid
							+ " and id.zorderlistid=" + zorderlistid);

					for (int i = 0; i < seatlist.size(); i++) {
						Seatordertab seat = (Seatordertab) seatlist.get(i);
						if (i == 0) {
							seats = seat.getIseatid().toString();
						} else {
							seats = seats + ">" + seat.getIseatid().toString();
						}
					}
				}
				map.put("seats", seats);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public Esbscenicareatab queryProviderByPosId(String posId){
		String hql = "from Esbaccessequiptab where id.iaccessequipid="+Long.parseLong(posId);
		List list = this.find(hql);
		if(list != null && !list.isEmpty()){
			Esbaccessequiptab esbaccessequiptab = (Esbaccessequiptab) list.get(0);
			return (Esbscenicareatab) this.get(Esbscenicareatab.class, esbaccessequiptab.getId().getIscenicid());
		}
		return null;
	}

	public List findOTOOrder(String srid){
		String hql = "select new map(m.orid as orid,tl.numb as numb,t.id.iscenicid as iscenicid) from MOrder m,TOrder t,TOrderlist tl where m.orid = t.id.orid and t.id.orid = tl.id.orid " +
				"and t.id.iscenicid = tl.id.iscenicid and m.srid = '"+srid+"' ";
		return this.find(hql);
	}
}

