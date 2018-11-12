package com.ectrip.ticket.venuemarketing.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.venuemarketing.dao.IPullSeatDAO;

public class PullSeatDAO extends GenericDao implements IPullSeatDAO {

	public Set pullSeat(Long ivenueid, Long ivenueareaid) {
		String seatids = venueSeats(ivenueid, ivenueareaid);
		Set set = null;
		String[] strs = seatids.split(",");
		if (strs != null && strs.length > 0) {
			set = new HashSet();
			for (int i = 0; i < strs.length; i++) {
				set.add(Long.parseLong(strs[i]));
			}
		}
		return set;
	}

	private String venueSeats(Long ivenueid, Long ivenueareaid) {
		String hql = "select new map(seq as seq,ivenueseatsid as ivenueseatsid,ivenueareaid as ivenueareaid,szvenueseatscode as  szvenueseatscode,szvenueseatsname as szvenueseatsname,irowserialnum as irowserialnum,icolumnserialnum as icolumnserialnum,byisuse as byisuse)  from Venueseats seat where ivenueid="
				+ ivenueid + " and ivenueareaid=" + ivenueareaid + " and byisuse=1 order by to_number(irowserialnum),to_number(icolumnserialnum)";
		StringBuffer sb = new StringBuffer();
		List list = find(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				sb.append(map.get("ivenueseatsid").toString());
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/*public List<TZorderlist> getOrderFilmInfo(String orid) {
		String hql = "from TZorderlist where id.orid='" + orid + "' and ivenueseatsid>0";
		return find(hql);
	}*/

	public Set getSaledSeat(String startdate, Long ivenueid, Long ivenueareaid, Long tripid) {
		String queryString = "select new Map(iseatid as iseatid) from Seatstatustab where id.startdate='" + startdate + "' and id.ivenueid=" + ivenueid + " and id.ivenueareaid=" + ivenueareaid
				+ " and id.tripid=" + tripid + "";
		List list = find(queryString);
		Set saled = null;
		if (list != null && list.size() > 0) {
			saled = new HashSet();
			for (int i = 0; i < list.size(); i++) {
				Map seat = (Map) list.get(i);
				saled.add(Long.parseLong(seat.get("iseatid").toString()));
			}
		}
		return saled;
	}

}
