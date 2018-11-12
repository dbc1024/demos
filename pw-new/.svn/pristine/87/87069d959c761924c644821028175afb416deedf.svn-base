package com.ectrip.ec.order.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.order.dao.idao.IRaftorderDao;

public class RaftorderDao extends GenericDao implements IRaftorderDao {
	public PaginationSupport querysqraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) {
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,m.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,YOrder y,YZorderlist yz,Custom c,Sysparv5 v5 where m.isa=1 and y.id.iscenicid="
						+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

		if (stdt != null && !stdt.equals("")) {
			hsql.append(" and m.stdt='" + stdt + "'");
		}
		if (usid != null && !usid.equals("")) {
			hsql.append(" and m.usid='" + usid + "'");
		}
		if (ornm != null && !ornm.equals("")) {
			hsql.append(" and y.ornm='" + ornm + "'");
		}
		if (!zfzt.equals("28")) {
			hsql.append(" and m.notea='" + zfzt + "'");
		}
		if (zfdata != null && !zfdata.equals("")) {
			hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
			if (tripid > 0) {
				hsql.append(" and yz.tripid=" + tripid);
			}
		}
		hsql
				.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.notea group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,m.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public PaginationSupport querytfraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,y.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz ,Custom c,Sysparv5 v5 where  y.id.iscenicid="
						+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);
		if (stdt != null && !stdt.equals("")) {
			hsql.append(" and m.stdt='" + stdt + "'");
		}
		if (usid != null && !usid.equals("")) {
			hsql.append(" and m.usid='" + usid + "'");
		}
		if (ornm != null && !ornm.equals("")) {
			hsql.append(" and y.ornm='" + ornm + "'");
		}
		if (!zfzt.equals("52")) {
			hsql.append(" and m.notea='" + zfzt + "'");
		} else {
			hsql.append(" and m.notea in ('49','50','51')");
		}
		if (zfdata != null && !zfdata.equals("")) {
			hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
			if (tripid > 0) {
				hsql.append(" and yz.tripid=" + tripid);
			}
		}
		hsql
				.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.notea group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,y.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
		System.out.println(hsql.toString());
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public PaginationSupport queryraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) {
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,y.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz ,Custom c,Sysparv5 v5 where  y.id.iscenicid="
						+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

		if (stdt != null && !stdt.equals("")) {
			hsql.append(" and m.stdt='" + stdt + "'");
		}
		if (usid != null && !usid.equals("")) {
			hsql.append(" and m.usid='" + usid + "'");
		}
		if (ornm != null && !ornm.equals("")) {
			hsql.append(" and y.ornm='" + ornm + "'");
		}
		hsql.append(" and m.notea='" + zfzt + "'");

		if (zfdata != null && !zfdata.equals("")) {
			hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
			if (tripid > 0) {
				hsql.append(" and yz.tripid=" + tripid);
			}
		}

		hsql
				.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.notea group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,y.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
    System.out.println(hsql.toString());
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public PaginationSupport querycpraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) {

		StringBuffer hsql = new StringBuffer();
		if (ornm != null && !ornm.equals("")) {
			hsql
					.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,y.ddzt as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz ,Custom c,Sysparv5 v5 where  y.id.iscenicid="
							+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

			if (stdt != null && !stdt.equals("")) {
				hsql.append(" and m.stdt='" + stdt + "'");
			}
			if (usid != null && !usid.equals("")) {
				hsql.append(" and m.usid='" + usid + "'");
			}
			if (ornm != null && !ornm.equals("")) {
				hsql.append(" and y.ornm='" + ornm + "'");
			}
			hsql.append(" and y.ddzt='" + zfzt + "'");

			if (zfdata != null && !zfdata.equals("")) {
				hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
				if (tripid > 0) {
					hsql.append(" and yz.tripid=" + tripid);
				}
			}

			hsql
					.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='DDZT' and v5.id.pmcd=y.ddzt group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,y.ddzt,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
		} else if (usid != null && !usid.equals("") && !usid.equals("guest")) {
			hsql
					.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,y.ddzt as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz ,Custom c,Sysparv5 v5 where  y.id.iscenicid="
							+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

			if (stdt != null && !stdt.equals("")) {
				hsql.append(" and m.stdt='" + stdt + "'");
			}
			if (usid != null && !usid.equals("")) {
				hsql.append(" and m.usid='" + usid + "'");
			}
			if (ornm != null && !ornm.equals("")) {
				hsql.append(" and y.ornm='" + ornm + "'");
			}
			hsql.append(" and y.ddzt='" + zfzt + "'");

			if (zfdata != null && !zfdata.equals("")) {
				hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
				if (tripid > 0) {
					hsql.append(" and yz.tripid=" + tripid);
				}
			}

			hsql
					.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='DDZT' and v5.id.pmcd=y.ddzt group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,y.ddzt,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");

		} else {
			hsql
					.append("select  m.szsalesvoucherno as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,substr(m.dtmakedate,1,10) as orda5,substr(m.dtmakedate,10) as orti6,substr(m.dtmakedate,1,10) as stdt7,'11' as notea8,'�ѳ�Ʊ' as strnotea9,d.lname as ornm10,d.zjhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.isplitamount) as znumb13 from Stssalesvouchertab m,Stscomticketsalesdetailstab yz ,Custom c,Custom d where  m.bysalesvouchertype='01' and m.iscenicid="
							+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

			if (stdt != null && !stdt.equals("")) {
				hsql.append(" and substr(m.dtmakedate,1,10)='" + stdt + "'");
			}

			if (zfdata != null && !zfdata.equals("")) {
				hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
				if (tripid > 0) {
					hsql.append(" and yz.tripid=" + tripid);
				}
			}

			hsql.append(" and m.id.isalesvoucherid=yz.id.isalesvoucherid and m.id.iticketstationid=yz.id.iticketstationid and m.usid=c.usid and m.dyusid=d.usid group by m.szsalesvoucherno,m.usid,c.corpname ,c.lname,c.lgtp,m.dtmakedate,substr(m.dtmakedate,1,10),substr(m.dtmakedate,10),d.lname,d.zjhm,yz.dtstartdate order by m.dtmakedate desc");

		}
	
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public PaginationSupport querycptpraftlist(Long iscenicid,
			Long itickettypeid, String usid, String ornm, String zfzt, String stdt,
			String zfdata, Long tripid, int pageSize, int startIndex, String url) {
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select  m.szsalesvoucherno as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,substr(m.dtmakedate,1,10) as orda5,substr(m.dtmakedate,10) as orti6,substr(m.dtmakedate,1,10) as stdt7,'11' as notea8,'�ѳ�Ʊ' as strnotea9,d.lname as ornm10,d.zjhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.isplitamount) as znumb13 from Stssalesvouchertab m,Stscomticketsalesdetailstab yz ,Custom c,Custom d where  m.bysalesvouchertype='02' and m.iscenicid="
						+ iscenicid + " and yz.iztickettypeid=" + itickettypeid);

		if (stdt != null && !stdt.equals("")) {
			hsql.append(" and substr(m.dtmakedate,1,10)='" + stdt + "'");
		}

		if (zfdata != null && !zfdata.equals("")) {
			hsql.append(" and substr(yz.dtstartdate,1,10)='" + zfdata + "'");
			if (tripid > 0) {
				hsql.append(" and yz.tripid=" + tripid);
			}
		}

		hsql.append(" and m.id.isalesvoucherid=yz.id.isalesvoucherid and m.id.iticketstationid=yz.id.iticketstationid and m.usid=c.usid and m.dyusid=d.usid group by m.szsalesvoucherno,m.usid,c.corpname ,c.lname,c.lgtp,m.dtmakedate,substr(m.dtmakedate,1,10),substr(m.dtmakedate,10),d.lname,d.zjhm,yz.dtstartdate order by m.dtmakedate desc");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public PaginationSupport queryoridraftlist(Long iscenicid,
			Long itickettypeid, String orid, int pageSize, int startIndex, String url) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,m.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz,Custom c,Sysparv5 v5 where m.orid='"
						+ orid
						+ "' and y.id.iscenicid="
						+ iscenicid
						+ " and yz.iztickettypeid=" + itickettypeid);
		hsql.append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.notea group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,m.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}
}
