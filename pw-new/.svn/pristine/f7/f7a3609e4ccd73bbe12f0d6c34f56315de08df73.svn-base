package com.ectrip.ec.book.rentrl.news.custag.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.rentrl.news.custag.dao.idao.IHotCarTypeDao;

public class HotCarTypeDao extends GenericDao implements IHotCarTypeDao {

	public List findHotCarType(String top) {
		String sql = "select esb.iscenicid    as iscenicid, esb.scenictype   as scenictype,  esb.szscenicname as szscenicname from esbscenicareatab esb where esb.scenictype = '10' and esb.szsceniccode != 'BX' and esb.byisuse = 1 and rownum < ? order by esb.iordernumber";
		List list;
		try {
			list = findBySqlToMap(sql, top);

			if (list != null && list.size() > 0) {
				sql = "select * from (select sec.upid, sec.itickettypeid,sec.iscenicid from Secenicpicture sec  order by sec.isecenicpictureid) s,  Upfile up where rownum = 1  and s.upid = up.upid  and s.itickettypeid = 0 and s.iscenicid = ?";
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long iscenicid = Long.valueOf(map.get("ISCENICID")
							.toString());
					List imgList = findBySqlToMap(sql, iscenicid);
					if (imgList != null && imgList.size() > 0) {
						Map img = (Map) imgList.get(0);
						map.putAll(img);
					}
				}
			}

			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List findJDCarType(String top) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		sql
				.append("select * from (select esbs.scenictype as scenictype ,esbs.szgrade as szgrade,esbs.szdata as szdata, edm.itickettypeid  as itickettypeid, edm.sztickettypename as sztickettypename, edm.iscenicid as iscenicid,");
		sql
				.append(" edm.bycategorytype as bycategorytype, edm.mnominalfee  as mnominalfee, esbs.szscenicname  as szscenicname, edmpr.mactualsaleprice as jsprice,");
		sql
				.append(" up.upfilename as upfilename, up.upadder as upadder,  edm.isequence from Edmtickettypetab edm inner join");
		sql
				.append(" (select * from Esbscenicareatab esb) esbs on esbs.iscenicid = edm.iscenicid inner join (select * from Edmcrowdkindpricetab edmp) edmpr on edmpr.itickettypeid = edm.itickettypeid  left join (select se.iscenicid as iscenicid, max(se.upid) as upid  from Secenicpicture se");
		sql
				.append(" where se.itickettypeid = 0 group by se.iscenicid) sec on sec.iscenicid = edm.iscenicid  left join (select * from upfile u) up");
		sql
				.append(" on up.upid = sec.upid  where esbs.scenictype = '10' and esbs.byisuse = 1 and edm.byisuse = 1 and edmpr.byisuse = 1");
		sql
				.append(" and edm.bycategorytype != '232' and edmpr.enddata >= ? and edmpr.startdata <= ? and edmpr.icrowdkindid = 1  and edmpr.ibusinessid = 1  and edm.byuselimit = 0 and edmpr.isnet = 1   order by edm.isequence desc) t1");
		sql
				.append(" where rownum< ? and t1.itickettypeid in (select min(t2.itickettypeid) from (select esbs.scenictype as scenictype,  esbs.szgrade as szgrade,esbs.szdata as szdata,  edm.itickettypeid as itickettypeid,");
		sql
				.append(" edm.sztickettypename as sztickettypename,edm.iscenicid as iscenicid, edm.bycategorytype as bycategorytype, edm.mnominalfee as mnominalfee,");
		sql
				.append(" esbs.szscenicname as szscenicname,edmpr.mactualsaleprice as jsprice,up.upfilename as upfilename,up.upadder as upadder,  edm.isequence");
		sql
				.append(" from Edmtickettypetab edm inner join (select * from Esbscenicareatab esb) esbs  on esbs.iscenicid = edm.iscenicid");
		sql
				.append(" inner join (select * from Edmcrowdkindpricetab edmp) edmpr on edmpr.itickettypeid = edm.itickettypeid left join ");
		sql
				.append(" (select se.iscenicid as iscenicid,max(se.upid) as upid  from Secenicpicture se  where se.itickettypeid = 0  group by se.iscenicid) sec");
		sql
				.append(" on sec.iscenicid = edm.iscenicid left join (select * from upfile u) up on up.upid = sec.upid where esbs.scenictype = '10'");
		sql
				.append(" and esbs.byisuse = 1 and esbs.szsceniccode!='BX' and edm.byisuse = 1 and edmpr.byisuse = 1 and edm.bycategorytype != '232' and edmpr.enddata >= ? ");
		sql
				.append(" and edmpr.startdata <= ?  and edmpr.icrowdkindid = 1 and edmpr.ibusinessid = 1 and edm.byuselimit = 0");
		sql
				.append(" and edmpr.isnet = 1   order by edm.isequence desc) t2 where rownum< ? and t1.bycategorytype = t2.bycategorytype)");
		System.out.println(sql.toString());
		try {
			list = findBySqlToMap(sql.toString(), Tools.getDays(), Tools
					.getDays(), top, Tools.getDays(), Tools.getDays(), top);

			if (list != null && list.size() > 0) {
				sql.setLength(0);
				sql
						.append("select * from (select sec.upid, sec.itickettypeid,sec.iscenicid from Secenicpicture sec  order by sec.isecenicpictureid) s,  Upfile up where rownum = 1  and s.upid = up.upid  and s.itickettypeid = ? ");
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long iscenicid = Long.valueOf(map.get("ITICKETTYPEID")
							.toString());
					List imgList = findBySqlToMap(sql.toString(), iscenicid);
					if (imgList != null && imgList.size() > 0) {
						Map img = (Map) imgList.get(0);
						map.putAll(img);
					}
				}
			}

			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	} 
	public List findTJCarType(String top) {
		String sql = "select * from (select edm.itickettypeid as itickettypeid, edm.sztickettypename as sztickettypename, edm.iscenicid as iscenicid, edm.bycategorytype as bycategorytype, edm.mnominalfee as mnominalfee,  esbs.scenictype as scenictype, esbs.szscenicname as szscenicname, car.region as region,  esbs.szgrade as szgrade,  esbs.szdata as szdata,  car.mileage as mileage,  edmpr.mactualsaleprice as jsprice  from Edmtickettypetab edm inner join (select * from Esbscenicareatab esb) esbs on esbs.iscenicid = edm.iscenicid inner join (select * from Edmcrowdkindpricetab edmp) edmpr  on edmpr.itickettypeid = edm.itickettypeid inner join (select * from Carlinedetailtab ca) car   on car.pmcd=edm.bycategorytype  where esbs.scenictype = '10' and esbs.szsceniccode!='BX'  and esbs.byisuse = 1  and edm.byisuse = 1  and edmpr.byisuse = 1  and edmpr.enddata >= ?  and edmpr.startdata <= ?  and edmpr.icrowdkindid = 1  and edmpr.ibusinessid = 1 and edm.bycategorytype!='232'  and edmpr.isnet = 1  order by  car.rentaltype, esbs.iordernumber desc) tjline where rownum<=?";
		try {
			return findBySqlToMap(sql, Tools.getDays(), Tools.getDays(), top);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * @Override public List findBySqlToMap(String sql,Object...params){ List
	 * list = null; try { Query query = getSession().createSQLQuery(sql); for
	 * (int i = 0; i < params.length; i++) { query.setParameter(i, params[i]); }
	 * list =
	 * query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list(); }
	 * catch (Exception e) { e.printStackTrace(); } return list; }
	 */
}
