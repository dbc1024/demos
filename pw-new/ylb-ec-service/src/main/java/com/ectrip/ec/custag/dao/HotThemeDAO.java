package com.ectrip.ec.custag.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;
import com.ectrip.ticket.model.provider.Hotelprovider;

public class HotThemeDAO extends GenericDao implements IHotThemeDAO {

	public List getHotTheme(String type) {
		String hql = "select new map(topics as topics) from Esbscenicareatab where scenictype=?";
		return find(hql, new Object[] { type });
	}

	public List getHotArea(String type, String top) {
		String hql = "select * from (select szregionalid,szregionalname from (select p.szregionalid as szregionalid,s.szregionalname as szregionalname from Esbscenicareatab p,Galsourceregiontab s where p.scenictype=? and p.szregionalid=s.iregionalid order by p.iordernumber desc) h group by h.szregionalid,h.szregionalname order by h.szregionalid) where rownum<="
				+ top + "";
		try {
			return findBySqlToMap(hql, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List getHotArea(String type) {
		String hql = "select  szregionalid,szregionalname from (select p.szregionalid as szregionalid,s.szregionalname as szregionalname from Esbscenicareatab p,Galsourceregiontab s where scenictype=? and p.szregionalid=s.iregionalid order by p.iordernumber desc) h group by h.szregionalid,h.szregionalname";
		try {
			return findBySqlToMap(hql, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List getHotSale(String type, String top) {
		String month = Tools.getMonth();
		String sql = "select * from (select product.*, rownum as rn, uf.upfilename, uf.upadder,price.listingprice,price.mactualsaleprice,price.icrowdkindid from (select sum(tl.numb) as salenum,tl.itickettypeid as productid, pc.sztickettypename as productname,p.iscenicid as iscenicid from T_Order          t,T_Orderlist      tl,Esbscenicareatab p,Edmtickettypetab pc"
				+ " where tl.orid = t.orid and t.iscenicid = p.iscenicid and p.scenictype = ? and t.ddzt in ('02', '11') and tl.itickettypeid = pc.itickettypeid and tl.dtstartdate>=? and tl.dtenddate <=? and tl.iscenicid = p.iscenicid group by p.iscenicid,tl.itickettypeid, pc.sztickettypename) product"
				+ " left join (select sc.itickettypeid as itickettypeid, max(sc.upid) as upid from Secenicpicture sc group by sc.itickettypeid) pic on pic.itickettypeid = product.productid left join upfile uf on uf.upid = pic.upid left join edmcrowdkindpricetab price on price.itickettypeid=product.productid where to_char(sysdate, 'yyyy-mm-dd') between price.startdata and price.enddata and price.icrowdkindid = 1 and price.ibusinessid = 1 and price.isnet=1 order by product.salenum desc) where rn <=?";
		try {
			List list = findBySqlToMap(sql, type, month + "-01", month + "-31",
					top);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List getHotHotel(String top) {
		String hsql = " select new map(e.iscenicid as iscenicid,e.szscenicname as szscenicname)from Esbscenicareatab e,Hotelprovider p where e.iscenicid = p.iscenicid and p.inoteger1 = 0 and e.scenictype='06' and e.byisuse=1 order by e.iordernumber desc";
		return this.findTopNumb(hsql, Integer.parseInt(top));
	}

	public List getNearByProvider(String type, String iscenicid) {
		Hotelprovider hotelprovider = (Hotelprovider) this.get(
				Hotelprovider.class, Long.parseLong(iscenicid));
		if (hotelprovider != null) {
			String proids = "";
			if (type.equals("01")) {// �ܱ߾���
				proids = hotelprovider.getNoted6();
			} else if (type.equals("06")) {// �ܱ߾���
				proids = hotelprovider.getNoted7();
			}
			if(proids!=null&&!proids.equals("")){
				String sql = "";
				if(type.equals("06")){
					sql = "select s.iscenicid as iscenicid,s.szscenicname as szscenicname, sy.pmva as grade,uf.upadder as upadder,uf.upfilename as upfilename,sy2.pmva as strzxjb from Hotelprovider hp,Sysparv5 sy2,Sysparv5 sy,Esbscenicareatab s left join (select sc.iscenicid as iscenicid, min(sc.upid) as upid from Secenicpicture sc group by sc.iscenicid) pic on pic.iscenicid = s.iscenicid left join upfile uf on uf.upid = pic.upid where s.iscenicid in ("+proids+") and sy2.pmky = 'HOTP' and sy2.pmcd = hp.zxjb and hp.iscenicid=s.iscenicid and sy.pmky = 'DENJ' and sy.pmcd = s.szgrade and 1=?";
				}else{
					sql = "select s.iscenicid as iscenicid,s.szscenicname as szscenicname, sy.pmva as grade,uf.upadder as upadder,uf.upfilename as upfilename from Sysparv5 sy,Esbscenicareatab s left join (select sc.iscenicid as iscenicid, min(sc.upid) as upid from Secenicpicture sc group by sc.iscenicid) pic on pic.iscenicid = s.iscenicid left join upfile uf on uf.upid = pic.upid where s.iscenicid in ("+proids+") and sy.pmky = 'DENJ' and sy.pmcd = s.szgrade and 1=?";
				}
				try {
					List list = findBySqlToMap(sql, 1);
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
}
