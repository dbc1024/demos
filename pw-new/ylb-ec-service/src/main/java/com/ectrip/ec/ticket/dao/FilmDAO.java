package com.ectrip.ec.ticket.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.ticket.dao.idao.IFilmDAO;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.ticket.model.venuemarketing.Tripprdsaletab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.venuemarketing.Venueprogrampic;
import com.ectrip.upload.model.Upfile;

public class FilmDAO extends GenericDao implements IFilmDAO {
	/**
	 * * Describe:�鿴���еĽ�Ŀ
	 * 
	 * @see com.ectrip.ticket.dao.idao.IFilmDAO#searchVenueprogram()
	 * @return
	 * @author chenxinhao Date:2014-8-13
	 */
	public List searchVenueprogram() {
		String hsql = "select new map(v.iprogramid as iprogramid,v.iscenicid as iscenicid,v.szprogramname as szprogramname,v.szprogramdeac as szprogramdeac,v.szprogramdetaildesc as szprogramdetaildesc,v.bywebsaletype as bywebsaletype,v.bycashsaletype as bycashsaletype,s.szscenicname as szscenicname,s.szaddress as szaddress,s.businesshours as businesshours) from Venueprogram v,Esbscenicareatab s where v.iscenicid = s.iscenicid and s.byisuse=1 and v.byisuse=1 and v.bywebsaletype!=0 ";
		List list = this.find(hsql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (!getFilmPicadder(
						Long.parseLong(map.get("iprogramid").toString()))
						.equals("")) {
					map.put("picadder", getFilmPicadder(Long.parseLong(map.get(
							"iprogramid").toString())));
				}
			}
		}
		return list;
	}

	/**
	 * * Describe:���ݽ�Ŀ�������Ű���Ϣ�ĳ���
	 * 
	 * @see com.ectrip.ticket.dao.idao.IFilmDAO#showVenueprogram(java.lang.Long)
	 * @param iprogramid
	 * @return
	 * @throws Exception
	 * @author chenxinhao Date:2014-8-15
	 */
	public Map showVenueprogram(Long iprogramid) throws Exception {
		String hsql = "select new map(v.iprogramid as iprogramid,v.iscenicid as iscenicid,v.szprogramname as szprogramname,v.szprogramdeac as szprogramdeac,v.szprogramdetaildesc as szprogramdetaildesc,v.bywebsaletype as bywebsaletype,v.bycashsaletype as bycashsaletype,s.szscenicname as szscenicname,s.szaddress as szaddress,s.businesshours as businesshours) from Venueprogram v,Esbscenicareatab s where v.iscenicid = s.iscenicid and s.byisuse=1 and v.byisuse=1 and v.iprogramid="
				+ iprogramid;
		List list = this.find(hsql);
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			if (!getFilmPicadder(
					Long.parseLong(map.get("iprogramid").toString()))
					.equals("")) {
				map.put("picadder", getFilmPicadder(Long.parseLong(map.get(
						"iprogramid").toString())));
			}
			String sql = "select distinct new map(tr.ivenueid as ivenueid,v.venueidname as venueidname) from Tripprdcontroltab tr,Venue v where tr.ivenueid=v.ivenueid and tr.enddata>='"
					+ Tools.getDays()
					+ "' and tr.iprogramid="
					+ Long.parseLong(map.get("iprogramid").toString());
			List tlist = this.find(sql);
			List venueList = new ArrayList();
			if (tlist != null && !tlist.isEmpty()) {
				for (int i = 0; i < tlist.size(); i++) {
					Tripprdcontroltab tripprdcontroltab = new Tripprdcontroltab();
					BeanUtils.populate(tripprdcontroltab, (Map) tlist.get(i));
					System.out.println(tripprdcontroltab.getIvenueid() + ","
							+ tripprdcontroltab.getVenueidname());
					venueList.add(tripprdcontroltab);
				}
			}
			map.put("venueList", venueList);
			return map;
		}
		return null;
	}

	// �����Ű�
	public List searchfilmTrip(String date, Long ivenueid, Long iprogramid) {
		Map resultMap = null;
		List resultList = new ArrayList();
		String xinqi = "";
		int tip = 1;
		String hql = "select new map(t.itripprdcontrolid as itripprdcontrolid,t.startdata as startdata,t.enddata as enddata) from Tripprdcontroltab t where t.ivenueid="
				+ ivenueid
				+ " and t.iprogramid="
				+ iprogramid
				+ " and t.enddata>='"
				+ date
				+ "' and t.byisuse=1 order by t.startdata";
		List list = find(hql);
		Venue venue = (Venue) this.get(Venue.class, ivenueid);
		Esbscenicareatab esb = (Esbscenicareatab) this.get(
				Esbscenicareatab.class, venue.getIscenicid());
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String startdata = map.get("startdata").toString();
			if (Tools.getDayNumb(startdata, Tools.getDays()) > 1) {
				startdata = Tools.getDays();
			}
			String enddata = map.get("enddata").toString();
			int daynum = Tools.getDayNumb(startdata, enddata);
			for (int j = 0; j < daynum; j++) {
				resultMap = new HashMap();
				resultMap.put("itripprdcontrolid", map.get("itripprdcontrolid")
						.toString());
				resultMap.put("startdata", Tools.getDate(startdata, j));
				switch (Tools.getDayOfWeek(Tools.getDate(startdata, j))) {
				case 1:
					xinqi = "������";
					break;
				case 2:
					xinqi = "����һ";
					break;
				case 3:
					xinqi = "���ڶ�";
					break;
				case 4:
					xinqi = "������";
					break;
				case 5:
					xinqi = "������";
					break;
				case 6:
					xinqi = "������";
					break;
				case 7:
					xinqi = "������";
					break;
				}
				resultMap.put("xinqi", xinqi);
				List details = filmTripDetail(Long.parseLong(map.get(
						"itripprdcontrolid").toString()));
				if (details != null) {
					if(Tools.getDate(startdata, j).compareToIgnoreCase(Tools.getDays())==0){
						for(int x=0;x<details.size();x++){
							Tripprdcontroldetailtab control = (Tripprdcontroldetailtab) details.get(x);
							if(control.getStarttime().compareToIgnoreCase(Tools.getDayTimes().substring(11, 16))<=0){
								details.remove(x);
								x--;
							}
						}
					}
					resultMap.put("tripdetail", details);
				}
				tip++;
				resultList.add(resultMap);
				if (tip > esb.getImaxdata()) {
					break;
				}
			}
		}
		return resultList;
	}
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate) {
		String hsql = "";
		Venueprogram program = (Venueprogram) this.get(Venueprogram.class, iprogramid);
		if(program.getBywebsaletype()==1){
			hsql = "select new map(v.ivenueareaid as ivenueareaid,v.ivenueareaname as ivenueareaname,p.mactualsaleprice as price,p.icrowdkindpriceid as icrowdkindpriceid) from Venuearea v,Tripprdsaletab t,Edmcrowdkindpricetab p where v.ivenueid="
					+ ivenueid
					+ " and v.byisuse=1"
					+ " and v.ivenueareaid=t.ivenueareaid and t.itripprdcontrolid="
					+ itripprdcontrolid
					+ " and t.iproductid=p.itickettypeid and p.byisuse=1 and startdata<='"
					+ startdate
					+ "' and enddata>='"
					+ startdate
					+ "' and p.icrowdkindid=1 and p.note1='0000' and p.isnet=1 and p.ibusinessid="
					+ ibusinessid + " order by v.ivenueareaid";
		}else if(program.getBywebsaletype()==2){
			hsql = "select new map(v.ivenueareaid as ivenueareaid,v.ivenueareaname as ivenueareaname,p.mactualsaleprice as price,p.icrowdkindpriceid as icrowdkindpriceid) from Venuearea v,Tripprdsaletab t,Edmcrowdkindpricetab p where v.ivenueid="
					+ ivenueid
					+ " and v.byisuse=1"
					+ " and v.ivenueareaid=t.ivenueareaid and t.itripprdcontrolid="
					+ itripprdcontrolid
					+ " and t.iproductid=p.itickettypeid and p.byisuse=1 and startdata<='"
					+ startdate
					+ "' and enddata>='"
					+ startdate
					+ "' and p.note1='0000' and p.isnet=1 and p.ibusinessid="
					+ ibusinessid + " order by v.ivenueareaid";
		}
		System.out.println(hsql);
		List list = this.find(hsql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String sql = "select seq from Venueseats where ivenueareaid="
						+ Long.parseLong(map.get("ivenueareaid").toString())
						+ " and byisuse=1";
				List slist = this.find(sql);
				int total = 0;
				if (slist != null && !slist.isEmpty()) {
					total = slist.size();
				}
				sql = "select s.id.iseatid from Seatstatustab s where s.id.ivenueid="
						+ ivenueid
						+ " and s.id.ivenueareaid="
						+ Long.parseLong(map.get("ivenueareaid").toString())
						+ " and s.id.itripid="
						+ itripid
						+ " and s.id.startdate='" + startdate + "' ";
				slist = this.find(sql);
				int salenum = 0;
				if (slist != null && !slist.isEmpty()) {
					salenum = slist.size();
				}
				map.put("total", total);
				map.put("salenum", salenum);
			}
		}
		return list;
	}
	
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate,String groupno){
		String hsql = "";
		Venueprogram program = (Venueprogram) this.get(Venueprogram.class, iprogramid);
//		if(program.getBywebsaletype()==1){
			hsql = "select new map(v.ivenueareaid as ivenueareaid,v.ivenueareaname as ivenueareaname,p.mactualsaleprice as price,p.icrowdkindpriceid as icrowdkindpriceid) from Venuearea v,Tripprdsaletab t,Edmcrowdkindpricetab p where v.ivenueid="
					+ ivenueid
					+ " and v.byisuse=1"
					+ " and v.ivenueareaid=t.ivenueareaid and t.itripprdcontrolid="
					+ itripprdcontrolid
					+ " and t.iproductid=p.itickettypeid and p.byisuse=1 and startdata<='"
					+ startdate
					+ "' and enddata>='"
					+ startdate
					+ "' and p.note1='"+groupno+"' and p.isnet=1 and p.ibusinessid="
					+ ibusinessid + " order by v.ivenueareaid,p.mactualsaleprice desc";
//		}else if(program.getBywebsaletype()==2){
//			hsql = "select new map(v.ivenueareaid as ivenueareaid,v.ivenueareaname as ivenueareaname,p.mactualsaleprice as price,p.icrowdkindpriceid as icrowdkindpriceid,p.szmemo as szmemo) from Venuearea v,Tripprdsaletab t,Edmcrowdkindpricetab p where v.ivenueid="
//					+ ivenueid
//					+ " and v.byisuse=1"
//					+ " and v.ivenueareaid=t.ivenueareaid and t.itripprdcontrolid="
//					+ itripprdcontrolid
//					+ " and t.iproductid=p.itickettypeid and p.byisuse=1 and startdata<='"
//					+ startdate
//					+ "' and enddata>='"
//					+ startdate
//					+ "' and p.note1='"+groupno+"' and p.isnet=1 and p.ibusinessid="
//					+ ibusinessid + " order by v.ivenueareaid";
//		}
		System.out.println(hsql);
		List list = this.find(hsql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String sql = "select seq from Venueseats where ivenueareaid="
						+ Long.parseLong(map.get("ivenueareaid").toString())
						+ " and byisuse=1";
				List slist = this.find(sql);
				int total = 0;
				if (slist != null && !slist.isEmpty()) {
					total = slist.size();
				}
				sql = "select s.id.iseatid from Seatstatustab s where s.id.ivenueid="
						+ ivenueid
						+ " and s.id.ivenueareaid="
						+ Long.parseLong(map.get("ivenueareaid").toString())
						+ " and s.id.itripid="
						+ itripid
						+ " and s.id.startdate='" + startdate + "' ";
				slist = this.find(sql);
				int salenum = 0;
				if (slist != null && !slist.isEmpty()) {
					salenum = slist.size();
				}
				map.put("total", total);
				map.put("salenum", salenum);
			}
		}
		return list;
	}
	
	public List searchFilm(String date, Long iscenicid) {
		String hql = "select new map (t.iprogramid as iprogramid,v.szprogramname as szprogramname,v.szprogramdeac as szprogramdeac) from Tripprdcontroltab t,  Venueprogram v where t.iprogramid=v.iprogramid and t.startdata<='"
				+ date
				+ "'  and t.enddata>='"
				+ date
				+ "' and t.byisuse=1 and v.iscenicid="
				+ iscenicid
				+ " and v.bywebsaletype =1 group by t.iprogramid,v.szprogramname,v.szprogramdeac";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (!getFilmPicadder(
						Long.parseLong(map.get("iprogramid").toString()))
						.equals("")) {
					map.put("picadder", getFilmPicadder(Long.parseLong(map.get(
							"iprogramid").toString())));
				}
			}
		}
		return list;
	}

	public List filmProviderList(String date) {
		String hql = "select new map (v.iscenicid as iscenicid,e.	szscenicname as szscenicname) from Tripprdcontroltab t,  Venueprogram v,Tripprdcontroldetailtab d,Esbscenicareatab e where t.iprogramid=v.iprogramid and t.startdata<='"
				+ date
				+ "'  and t.enddata>='"
				+ date
				+ "' and t.byisuse=1 and  v.bywebsaletype =1 and d.itripprdcontrolid=t.itripprdcontrolid and v.iscenicid=e.iscenicid group by v.iscenicid,e.szscenicname";
		return find(hql);
	}

	public String getFilmPicadder(Long iprogramid) {
		String hql = "from Venueprogrampic where iprogramid=" + iprogramid + "";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			Venueprogrampic pic = (Venueprogrampic) list.get(0);
			Upfile picadd = (Upfile) get(Upfile.class, pic.getUpid());
			return picadd.getUpadder() + picadd.getUpfilename();
		} else {
			return "";
		}
	}

	public List filmTripDetail(Long itripprdcontrolid) {
		String hql = " from Tripprdcontroldetailtab where itripprdcontrolid="
				+ itripprdcontrolid + " order by starttime";
		return find(hql);
	}

	public Tripprdcontroldetailtab getFilmControlTrip(Long itripprdcontrolid,
			Long tripid) {
		String hql = "from Tripprdcontroldetailtab where itripprdcontrolid="
				+ itripprdcontrolid + " and itripid=" + tripid + "";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Tripprdcontroldetailtab) list.get(0);
		} else {
			return null;
		}
	}

	// �����Ű�
	public List filmTripList(String date, Long iprogramid) {
		String hql = "select new map(t.ivenueid as ivenueid,v.venueidname as venueidname,t.itripprdcontrolid as itripprdcontrolid) from Tripprdcontroltab t,Venue v where t.startdata<='"
				+ date
				+ "' and t.enddata>='"
				+ date
				+ "' and t.iprogramid="
				+ iprogramid + " and t.byisuse=1 and t.ivenueid=v.ivenueid";
		List list = find(hql);
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			List details = filmTripDetail(Long.parseLong(map.get(
					"itripprdcontrolid").toString()));
			if (details != null) {
				map.put("tripdetail", details);
			}
		}
		return list;
	}

	public Tripprdcontroltab filmTripControl(String date, Long iprogramid,
			Long ivenuid) {
		String hql = "from Tripprdcontroltab where startdata<='" + date
				+ "' and enddata>='" + date + "' and iprogramid=" + iprogramid
				+ " and  byisuse=1 and ivenueid=" + ivenuid;
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Tripprdcontroltab) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            �ۿ�����
	 * @param ivenueid
	 *            ���ݱ��
	 * @param tripid
	 *            �˴�
	 * @return
	 */
	public List filmSeatSaleStatu(String date, Long ivenueid, Long tripid) {
		String hql = "select new map (id.startdate as startdate,id.ivenueid as ivenueid,id.itripid as itripid,id.ivenueareaid as ivenueareaid,id.iseatid as iseatid,status as status) from Seatstatustab where id.startdate='"
				+ date
				+ "'  and id.ivenueid="
				+ ivenueid
				+ " and itripid="
				+ tripid + "";
		return find(hql);
	}
	/**
	 * 
	 * @param date
	 *            �ۿ�����
	 * @param ivenueid
	 *            ���ݱ��
	 * @param ivenueareaid
	 *            ������
	 * @param tripid
	 *            �˴�
	 * @return
	 */
	public List filmSeatSaleStatu(String date, Long ivenueid,
			Long ivenueareaid, Long tripid) {
		String hql = "select new map (id.startdate as startdate,id.ivenueid as ivenueid,id.itripid as itripid,id.ivenueareaid as ivenueareaid,id.iseatid as iseatid,status as status) from Seatstatustab where id.startdate='"
				+ date
				+ "'  and id.ivenueid="
				+ ivenueid
				+ " and id.ivenueareaid="
				+ ivenueareaid
				+ "and itripid="
				+ tripid + "";
		return find(hql);
	}
	
	public List venueSeats(Long ivenueid, Long ivenueareaid) {
		String hql = "select new map(seq as seq,ivenueseatsid as ivenueseatsid,ivenueareaid as ivenueareaid,szvenueseatscode as  szvenueseatscode,szvenueseatsname as szvenueseatsname,irowserialnum as irowserialnum,icolumnserialnum as icolumnserialnum,byisuse as byisuse)  from Venueseats seat where ivenueid="
				+ ivenueid
				+ " and ivenueareaid="
				+ ivenueareaid
				+ "and byisuse=1 order by to_number(irowserialnum),to_number(icolumnserialnum)";
		return find(hql);
	}
	public List venueSeats(Long ivenueid) {
		String hql = "select new map(seq as seq,ivenueseatsid as ivenueseatsid,ivenueareaid as ivenueareaid,szvenueseatscode as  szvenueseatscode,szvenueseatsname as szvenueseatsname,irowserialnum as irowserialnum,icolumnserialnum as icolumnserialnum,byisuse as byisuse)  from Venueseats seat where ivenueid="
				+ ivenueid
				+ " and byisuse=1 order by to_number(irowserialnum),to_number(icolumnserialnum)";
		return find(hql);
	}

	public Long getFilmProduct(Long ivenueareaid, Long itripprdcontrolid) {
		String hql = "from Tripprdsaletab where itripprdcontrolid="
				+ itripprdcontrolid + " and ivenueareaid=" + ivenueareaid;
		List list = find(hql);
		if (list != null && list.size() > 0) {
			Tripprdsaletab product = (Tripprdsaletab) list.get(0);
			return product.getIproductid();
		} else {
			return null;
		}
	}

	public List venuAreaList(Long ivenueid) {
		String queryString = " select new map(ivenueareaid as ivenueareaid,ivenueareaname as ivenueareaname) from Venuearea where ivenueid="
				+ ivenueid +" and byisuse=1";
		return find(queryString);
	}
	public Upfile getVenuePicture(Long ivenueid) {
		// TODO Auto-generated method stub
		Long upid = 0L;
		List list = this.find("select new map(p.ivenueid as ivenueid, p.ivenueictureid as ivenueictureid, p.upid as upid, p.dtmakedate as dtmakedate) from  Venuepicture p where p.ivenueid="+ivenueid);
		if(null!=list&&list.size()>0){
			upid = (Long) ((Map)list.get(0)).get("upid");
		}
		if(upid>0){
			return (Upfile) this.get(Upfile.class, upid);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String date = "2015-05-15 16:00:00";
		System.out.println(date.substring(11, 16));
	}
}
