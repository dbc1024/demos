package com.ectrip.ec.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.order.dao.idao.ISeatorderDAO;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.venuemarketing.Venueseats;

public class SeatorderDAO extends GenericDao implements ISeatorderDAO {
	public List gettorderseatlist(String orid, Long iscenicid, Long orderlistid) {
		String tsql = " from TOrderlist where id.orid='" + orid
				+ "' and id.iscenicid=" + iscenicid;
		if (orderlistid != 0) {
			tsql = tsql + " and id.orderlistid=" + orderlistid;
		}
		List list1 = new ArrayList();
		List list = this.find(tsql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TOrderlist t = (TOrderlist) list.get(i);
				String sql = "select new map(seat.iseatid as iseatid,vp.szprogramname as szprogramname,td.starttime as starttime,seat.startdate as startdate,vs.szvenueseatsname as szvenueseatsname,v.venueidname as venueidname,ve.ivenueareaname as ivenueareaname,t.tripname as tripname) from Seatordertab seat,Tripprdcontroldetailtab td,Venueprogram vp,Venueseats vs,Venue v,Venuearea  ve,Trip t where seat.id.orid='"
						+ orid
						+ "' and seat.id.iscenicid="
						+ iscenicid
						+ " and seat.id.orderlistid="
						+ t.getId().getOrderlistid()
						+ " and vs.ivenueid=seat.ivenueid and vs.ivenueareaid=seat.ivenueareaid and vs.ivenueseatsid=seat.iseatid and vp.iprogramid=seat.iprogramid and td.itripprdcontrolid=seat.itripprdcontrolid and td.itripid=seat.itripid and v.ivenueid=seat.ivenueid and ve.ivenueid=seat.ivenueid and ve.ivenueareaid=seat.ivenueareaid and t.tripid=seat.itripid and seat.isvalid!=-1";
				List selist = this.find(sql);
				if (selist != null && selist.size() > 0) {
					Edmtickettypetab et = (Edmtickettypetab) this.get(
							Edmtickettypetab.class, t.getItickettypeid());
					t.setSztickettypename(et.getSztickettypename());
					Edpcrowdkindtab ec = (Edpcrowdkindtab) this.get(
							Edpcrowdkindtab.class, t.getIcrowdkindid());
					t.setSzcrowdkindname(ec.getSzcrowdkindname());
					List slist = new ArrayList();
					for (int j = 0; j < selist.size(); j++) {
						Seatordertab st = new Seatordertab();
						try {
							BeanUtils.populate(st, (Map) selist.get(j));
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						slist.add(st);
						if (j == 0) {
							t.setWharfdate(st.getStartdate().substring(0, 10));
							t.setWharftime(st.getStarttime());
							t.setWharfname(st.getVenueidname());
							t.setFilmname(st.getSzprogramname());
						}
					}
					t.setSlist(slist);
					list1.add(t);
				}

			}

		}
		return list1;

	}

	public Seatordertab getSeatordertab(String orid, Long iscenicid,
			Long iseatid) {
		Seatordertab s = new Seatordertab();
		String tsql = " from Seatordertab where id.orid='" + orid
				+ "' and id.iscenicid=" + iscenicid + " and iseatid=" + iseatid;
		List selist = this.find(tsql);
		s = (Seatordertab) selist.get(0);
		return s;
	}

	public Venueseats getvenueseats(long ivenueid, Long iseatid) {
		Venueseats s = new Venueseats();
		String sql = " from Venueseats where ivenueid=" + ivenueid
				+ " and ivenueseatsid=" + iseatid;
		List selist = this.find(sql);
		s = (Venueseats) selist.get(0);
		return s;
	}
	
	public List getTorderList(String orid,Long iscenicid){
		String hsql = "from TOrderlist t where t.id.orid='"+orid+"' and t.id.iscenicid="+iscenicid;
		return this.find(hsql);
	}
}
