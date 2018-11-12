package com.ectrip.ec.book.rentrl.news.dao;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.rentrl.news.dao.idao.ICarlineDao;
import com.ectrip.ec.model.rentrl.Carlinedetailtab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;

public class CarlineDao extends GenericDao implements ICarlineDao {
	/*
	 * ͨ���⳵·�߲�ѯ����(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.rentrl.news.dao.idao.ICarlinedetailtabDao#findByLine(
	 * java.lang.Long)
	 */
	public List findByLine(String pmcd) {
		StringBuffer sql = new StringBuffer();
		List imgList = null;
		if (pmcd == null) {
			//��ȡ������·
			sql.append("select * from (select s.pmva as pmva, s.pmcd as pmcd, count(s.pmva) as cou from Edmtickettypetab edm inner join Sysparv5 s on edm.bycategorytype = s.pmcd  where s.pmky = 'PRTP' and s.spmcd = '10' and edm.byisuse = 1  and s.pmcd != '232' group by s.pmva, s.pmcd) r where r.cou > 0  order by cou desc");
			try {
				return findBySqlToMap(sql.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			//��ȡ������ID
			sql.append("select * from (select edm.iscenicid as iscenicid, esb.szscenicname as szscenicname  from Edmtickettypetab edm, Esbscenicareatab esb where edm.bycategorytype = ? and edm.iscenicid = esb.iscenicid  and esb.scenictype = '10' order by esb.iordernumber desc ) where rownum<5");
			List list = null;
			try {
				list = findBySqlToMap(sql.toString(),new Object[]{pmcd});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (list.size()>0) {
				imgList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map iscenicid = (Map) list.get(i);
					String sqlstr ="select * from (select sec.upid, sec.itickettypeid,sec.iscenicid from Secenicpicture sec  order by sec.isecenicpictureid) s,  Upfile up where rownum = 1  and s.upid = up.upid  and s.itickettypeid = 0 and s.iscenicid = ?";
					try {
						List img = findBySqlToMap(sqlstr,iscenicid.get("iscenicid".toUpperCase()));
						for (int j = 0; j < img.size(); j++) {
							Map imgmap = (Map) img.get(j);
							imgmap.put("PMCD", pmcd);
							imgmap.put("ISCENICID", iscenicid.get("iscenicid".toUpperCase()));
							imgmap.put("SZSCENICNAME",iscenicid.get("szscenicname".toUpperCase()));
						}
						if (img.size()>0) {
							imgList.addAll(img);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//��ȡ��·�����еĳ���
			//sql.append("select * from (select es.iscenicid as iscenicid, sc.upid as upid,es.szscenicname,es.bycategorytype as pmcd from (select esb.iscenicid as iscenicid,edm.bycategorytype as bycategorytype,esb.szscenicname as szscenicname from Esbscenicareatab esb left join Edmtickettypetab edm  on edm.iscenicid = esb.iscenicid  where edm.bycategorytype = ? and edm.bycategorytype!='BX'  and esb.scenictype = '10'  group by esb.iscenicid,esb.szscenicname,edm.bycategorytype) es left join (select s.iscenicid as iscenicid,min(s.isecenicpictureid) ,min(s.upid) as upid  from Secenicpicture s  where s.itickettypeid=0 group by s.iscenicid) sc  on sc.iscenicid = es.iscenicid) pic left join (select up.upid, up.upfilename as upfilename, up.upadder as upadder from Upfile up) u  on u.upid = pic.upid");
			//sql.append("select esb.iscenicid as iscenicid,up.upid as upid,esb.szscenicname as szscenicname, edm.bycategorytype as pmcd,up.upfilename as upfilename,up.upadder as upadder,sec.isecenicpictureid as isecenicpictureid from Esbscenicareatab esb inner join Edmtickettypetab edm on esb.iscenicid = edm.iscenicid inner join (select  s.iscenicid as iscenicid, s.upid as upid, max(s.isecenicpictureid) as isecenicpictureid from Secenicpicture s where s.itickettypeid=0 group by s.iscenicid,s.upid) sec on sec.iscenicid = edm.iscenicid inner join Upfile up on up.upid = sec.upid where edm.bycategorytype = ? and esb.scenictype = '10'and rownum <=(select count(esb.iscenicid) from Esbscenicareatab es inner join Edmtickettypetab ed on es.iscenicid = ed.iscenicid where es.scenictype='10' and ed.bycategorytype=?)");
			
			/*try {
				return findBySqlToMap(sql.toString(), pmcd,pmcd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}*/
			return imgList;
		}
	}

	/*
	 * �õ����е��⳵·��(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.rentrl.news.dao.idao.ICarlinedetailtabDao#findAllLine()
	 */
	public List findAllLine() {
		String sql = "select new map(s.pmcd as pmcd,s.pmva as pmva ) from Sysparv5 s where s.spmcd='10' and s.id.pmky='PRTP' and s.id.pmcd!='232'";
		return find(sql);
	}

	/*
	 * �����õ��⳵·��(non-Javadoc)
	 * 
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#searchLine()
	 */
	public List searchLine(String bycategorytype) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(c.mileage as mileage,c.usetime as usetime,c.pmcd as pmcd ,c.region as region, c.linedescription as linedescription,s.pmva as bycategorytype) from Carlinedetailtab c, Sysparv5 s where c.pmcd = ? and s.id.pmcd!='232' and s.id.pmky = 'PRTP' and s.spmcd = '10' and s.id.pmcd = c.pmcd");
		List  list = find(sql.toString(),new Object[]{bycategorytype});
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(0);
			if (map.get("linedescription").toString()!=null) {
				Blob blob = (Blob) map.get("linedescription");
				try {
					map.put("linedescription", Tools.getStrByBlob(blob));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}else {
				map.put("linedescription", "");
			}
		}
		return list;
	}
	/*
	 * ��ѯ·���µ����г���(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#searchLineByType(java.lang.String)
	 */
	public List searchLineByType(String bycategorytype) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select esb.iscenicid as iscenicid,edm.itickettypeid as itickettypeid, esb.szscenicname as szscenicname,esb.szdata as szdata,esb.szgrade as szgrade,esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription,edm.mnominalfee as mnominalfee");
		sql.append(" from Edmtickettypetab edm");
		sql.append(" inner join Esbscenicareatab esb");
		sql.append(" on esb.iscenicid = edm.iscenicid");
		sql.append(" where edm.bycategorytype = ? and edm.bycategorytype!='232'  and edm.byisuse=1 and esb.byisuse=1");
		try {
			List list = findBySqlToMap(sql.toString(), bycategorytype);
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
					double price = getPrice(itickettypeid, Tools.getDays());
					map.put("jsprice".toUpperCase(), price);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * ����ͼƬ��·��ͼƬ(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findAllImg(java.lang.Long, java.lang.Long)
	 */
	public List findAllImg(Long iscenicid, String itickettypeid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select s.iscenicid as iscenicid,up.upfilename as upfilename, up.upadder as upadder");
		sql.append(" from Secenicpicture s");
		sql.append(" left join (select u.upid as upid,");
		sql.append(" u.upfilename as upfilename,");
		sql.append(" u.upadder as upadder");
		sql.append(" from Upfile u) up");
		sql.append(" on up.upid = s.upid");
		if (itickettypeid==null) {
			//��ѯ������ͼƬ
			sql.append(" where s.iscenicid = ? and s.itickettypeid = 0");
			try {
				return findBySqlToMap(sql.toString(), iscenicid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			//·�߸�������
			sql.append(" where s.iscenicid = ?");
			sql.append(" or s.itickettypeid in (select edm.itickettypeid from Edmtickettypetab edm  where edm.iscenicid = s.iscenicid and edm.bycategorytype=?) order by s.upid desc");
			try {
				return findBySqlToMap(sql.toString(), iscenicid,itickettypeid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
	}
	/*
	 * ѡ��ͬ�ĳ��ͼ��ز�ͬ����Ϣ(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#changeCarType(java.lang.Long, java.lang.Long)
	 */
	public List changeCarType(Long iscenicid, String bycategorytype,String enddate,String stratdate) {
		StringBuffer sql = new StringBuffer();
		sql.append("select esb.iscenicid as iscenicid,edm.itickettypeid as itickettypeid,");
		sql.append(" esb.szscenicname as szscenicname,");
		sql.append(" esb.szdata as szdata,");
		sql.append(" esb.szgrade as szgrade,");
		sql.append(" edm.mnominalfee as mnominalfee,");
		sql.append(" esb.szbookdescription as szbookdescription,");
		sql.append(" esb.szsimpleintroduction as szsimpleintroduction");
		sql.append(" from Edmtickettypetab edm");
		sql.append(" inner join Esbscenicareatab esb ");
		sql.append(" on esb.iscenicid = edm.iscenicid");
        sql.append(" where edm.bycategorytype = ?");
        sql.append(" and esb.byisuse = 1");
        sql.append(" and edm.byisuse = 1");
        sql.append(" and esb.iscenicid=?");
        System.out.println(sql.toString());
		try {
			List list = findBySqlToMap(sql.toString(),bycategorytype,iscenicid);
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
					double price = getPrice(itickettypeid, Tools.getDays());
					map.put("jsprice".toUpperCase(), price);
				}
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List changeCarType(Long iscenicid, String bycategorytype,String enddate,String stratdate,Long ibusinessid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select esb.iscenicid as iscenicid,edm.itickettypeid as itickettypeid,");
		sql.append(" esb.szscenicname as szscenicname,");
		sql.append(" esb.szdata as szdata,");
		sql.append(" esb.szgrade as szgrade,");
		sql.append(" edm.mnominalfee as mnominalfee,");
		sql.append(" esb.szbookdescription as szbookdescription,");
		sql.append(" esb.szsimpleintroduction as szsimpleintroduction");
		sql.append(" from Edmtickettypetab edm");
		sql.append(" inner join Esbscenicareatab esb ");
		sql.append(" on esb.iscenicid = edm.iscenicid");
        sql.append(" where edm.bycategorytype = ?");
        sql.append(" and esb.byisuse = 1");
        sql.append(" and edm.byisuse = 1");
        sql.append(" and esb.iscenicid=?");
  
		try {
			List list = findBySqlToMap(sql.toString(),bycategorytype,iscenicid);
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
					double price = getPrice(itickettypeid, Tools.getDays(),ibusinessid);
					map.put("jsprice".toUpperCase(), price);
				}
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List carTypeDetail(Long iscenicid, Long itickettypeid) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * ��ѯ·�������еĳ���ͼƬ�ͼ۸�(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findCarType(java.lang.String)
	 */
	public List findCarType(String bycategorytype) {
		StringBuffer sql = new StringBuffer();
		sql.append("select edm.itickettypeid as itickettypeid, esb.iscenicid as iscenicid,");
		sql.append(" esb.szscenicname as szscenicname,edm.mnominalfee as mnominalfee,");
		sql.append(" up.upadder as upadder,up.upfilename as upfilename,esb.szdata as szdata,esb.szgrade as szgrade");
		sql.append(" from Esbscenicareatab esb");
		sql.append(" inner join Edmtickettypetab edm");
		sql.append(" on esb.iscenicid = edm.iscenicid");
		sql.append(" left join (select s.iscenicid as iscenicid,min(s.isecenicpictureid),min(s.upid) as upid");
		sql.append(" from Secenicpicture s where s.itickettypeid = 0 group by s.iscenicid) se");
		sql.append(" on se.iscenicid = esb.iscenicid left join Upfile up on up.upid = se.upid");
		sql.append(" where esb.scenictype = '10' and edm.bycategorytype = ?");
		sql.append(" and esb.byisuse = 1 and edm.byisuse = 1  and rownum<7");
		try {
			List list = findBySqlToMap(sql.toString(),bycategorytype);
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
					double price = getPrice(itickettypeid, Tools.getDays());
					map.put("jsprice".toUpperCase(), price);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * ��ѯ����������(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findCarType(java.lang.Long)
	 */
	public List findCarType(Long iscenicid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(esb.iscenicid as iscenicid, esb.szscenicname as szscenicname,esb.szgrade  as szgrade,esb.szdata  as szdata,esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription) from Esbscenicareatab esb where esb.iscenicid = ?");
		return find(sql.toString(),new Object[]{iscenicid});
	}
	/*
	 * ����ͼƬ(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findCarTypeImg(java.lang.Long)
	 */
	public List findCarTypeImg(Long iscenicid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(u.upadder as upadder, u.upfilename as upfilename) from Secenicpicture s, Upfile u where  s.itickettypeid = 0 and s.upid = u.upid and s.iscenicid = ?  order by s.isecenicpictureid");
		return find(sql.toString(),new Object[]{iscenicid});
	}
	/*
	 * ���·��(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findLineRelated(java.lang.Long)
	 */
	public List findLineRelated(Long iscenicid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(esb.iscenicid as iscenicid, esb.szscenicname as szscenicname,esb.szgrade  as szgrade,esb.szdata  as szdata,edm.bycategorytype as bycategorytype,edm.sztickettypename as sztickettypename, edm.itickettypeid as itickettypeid,edm.mnominalfee as mnominalfee,edm.iscenicid as iscenicid,car.region as region,car.mileage as mileage,car.usetime as usetime) from Edmtickettypetab edm, Carlinedetailtab  car,");
		sql.append(" Esbscenicareatab esb where edm.iscenicid = esb.iscenicid and edm.iscenicid = ? and edm.bycategorytype = car.pmcd  and  edm.byisuse = 1 ");
		List list = find(sql.toString(),new Object[]{iscenicid});
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				double price = getPrice(itickettypeid, Tools.getDays());
				map.put("jsprice", price);
			}
		}
		return list;
	}
	/*
	 * Ԥ��(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#reserveCar(java.lang.Long, java.lang.Long)
	 */
	public List reserveCar(Long iscenicid, String bycategorytype,String date) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(edm.mnominalfee as mnominalfee,edm.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,edm.bycategorytype as bycategorytype,esb.iscenicid as iscenicid,esb.szgrade as szgrade,");
		sql.append(" esb.szdata as szdata,esb.szscenicname as szscenicname,edmc.mactualsaleprice as jsprice) ");
        sql.append("from Edmtickettypetab edm,Esbscenicareatab esb,Edmcrowdkindpricetab edmc");
        sql.append(" where edm.itickettypeid = edmc.itickettypeid and edm.iscenicid = esb.iscenicid and edm.bycategorytype=?");
        sql.append("  and edm.iscenicid = ? and edm.byisuse = 1 and esb.byisuse = 1  and edmc.isnet = 1 and edmc.byisuse = 1 and edmc.enddata>=? and edmc.startdata<=?");
		return find(sql.toString(),new Object[]{bycategorytype,iscenicid,date,date});
	}
	/*
	 * ����Ƿ��м۸�(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#checkPrice(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List checkPrice(Long iscenicid, String bycategorytype, String date) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(edmc.mactualsaleprice as jsprice,edm.mnominalfee as mnominalfee) ");
        sql.append("from Edmtickettypetab edm,Esbscenicareatab esb,Edmcrowdkindpricetab edmc");
        sql.append(" where edm.itickettypeid = edmc.itickettypeid and edm.iscenicid = esb.iscenicid and edm.bycategorytype=?");
        sql.append("  and edm.iscenicid = ? and edm.byisuse = 1 and esb.byisuse = 1  and edmc.isnet = 1 and edmc.byisuse = 1 and edmc.enddata>=? and edmc.startdata<=?");
		List list = find(sql.toString(),new Object[]{bycategorytype,iscenicid});
		return list;
	}
	/*
	 * �õ��۸�(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#getCartypePrice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Edmcrowdkindpricetab getCartypePrice(String itickettypeid,
			String tourDate, String icrowdkind, String ibusinessid,
			String groupno) {
		Edmcrowdkindpricetab price = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmcrowdkindpricetab p where p.itickettypeid="
				+ itickettypeid + " ");
		hsql.append("  and p.byisuse=1 and p.isnet=1 and p.ibusinessid ="
				+ ibusinessid + " ");
		hsql.append(" and p.icrowdkindid =" + icrowdkind + " and p.note1='"
				+ groupno + "' ");
		hsql.append(" and p.startdata<='" + tourDate + "' and p.enddata>='"
				+ tourDate + "' ");
		hsql.append(" order by p.startdata ");
		List list = this.find(hsql.toString());
		if (list != null && list.size() > 0) {
			price = (Edmcrowdkindpricetab) list.get(0);
		}
		return price;
	}
	/*
	 * ����(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#getSafeList(java.lang.String)
	 */
	public List getSafeList(String date) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(edm.itickettypeid as itickettypeid, esb.iscenicid as iscenicid, edmc.mactualsaleprice as jsprice, edm.sztickettypename as sztickettypename, esb.szscenicname as szscenicname) from Edmtickettypetab edm, Esbscenicareatab esb,Edmcrowdkindpricetab edmc where edm.bycategorytype = '232' ");
		sql.append(" and edm.iscenicid = esb.iscenicid and edm.itickettypeid = edmc.itickettypeid and edm.byisuse = 1 and esb.byisuse = 1 and edmc.byisuse = 1 and edmc.isnet = 1 and edmc.startdata <= ? and edmc.enddata >= ?");
		if (date==null||date.trim().equals("")) {
			date = Tools.getDays();
		}
		return find(sql.toString(),new Object[]{date,date});
	}

	public List findCarList(Long iscenicid, String bycategorytype) {
		StringBuffer sql = new StringBuffer();
		List list = null;
		sql.append("select esb.iscenicid  as iscenicid, esb.szscenicname as szscenicname,esb.szgrade as szgrade,esb.szdata as szdata,");
		sql.append(" esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription,car.usetime as usetime,");
		sql.append(" car.mileage as mileage,car.linedescription as linedescription,car.region as region,edm.itickettypeid as itickettypeid,");
		sql.append(" edm.sztickettypename as sztickettypename,edm.mnominalfee as mnominalfee from Esbscenicareatab esb");
		sql.append(" inner join Edmtickettypetab edm on esb.iscenicid = edm.iscenicid ");
		sql.append(" inner join Carlinedetailtab car on edm.bycategorytype = car.pmcd where edm.bycategorytype = ? ");
		sql.append(" and esb.scenictype = '10' and esb.byisuse = 1  and edm.byisuse = 1 ");
		System.out.println(sql.toString());
		try {
			if (iscenicid!=null) {
				sql.append(" and  esb.iscenicid = ?");
				list = findBySqlToMap(sql.toString(),bycategorytype,iscenicid);
			}else {
				list = findBySqlToMap(sql.toString(),bycategorytype);
			}
			if (list.size()>0) {
				Map map = (Map) list.get(0);
				Blob blob = (Blob) map.get("linedescription".toUpperCase());
				map.put("linedescription".toUpperCase(), Tools.getStrByBlob(blob));
				//�۸�
				Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
				double price = getPrice(itickettypeid, Tools.getDays());
				map.put("jsprice".toUpperCase(), price);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Carlinedetailtab findCarlinedetailtab(String pmcd) {
		String sql = "from Carlinedetailtab c where c.pmcd=?";
		List list = find(sql,new Object[]{pmcd});
		if (list.size()>0) {
			return (Carlinedetailtab) list.get(0);
		}
		return null;
	}
	
	/*@Override
	public List findBySqlToMap(String sql,Object...params){
		List list = null;
		try {
			Query query = getSession().createSQLQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

	public List findCarListByDate(String bycategorytype, String date) {
		StringBuffer sql = new StringBuffer();
		List list = null;
		sql.append("select esb.iscenicid  as iscenicid, esb.szscenicname as szscenicname,esb.szgrade as szgrade,esb.szdata as szdata,");
		sql.append(" esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription,car.usetime as usetime,");
		sql.append(" car.mileage as mileage,car.linedescription as linedescription,car.region as region,edm.itickettypeid as itickettypeid,");
		sql.append(" edm.sztickettypename as sztickettypename,edm.mnominalfee as mnominalfee from Esbscenicareatab esb");
		sql.append(" inner join Edmtickettypetab edm on esb.iscenicid = edm.iscenicid ");
		sql.append(" inner join Carlinedetailtab car on edm.bycategorytype = car.pmcd where edm.bycategorytype = ? ");
		sql.append(" and esb.scenictype = '10' and esb.byisuse = 1  and edm.byisuse = 1 ");
		System.out.println(sql.toString());
		try {
			list = findBySqlToMap(sql.toString(),bycategorytype);
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Blob blob = (Blob) map.get("linedescription".toUpperCase());
				map.put("linedescription".toUpperCase(), Tools.getStrByBlob(blob));
				Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
				double price = getPrice(itickettypeid, Tools.getDays());
				map.put("jsprice".toUpperCase(), price);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * ��·�۸�ͨ�ò�ѯ
	 */
	public double getPrice(Long itickettypeid,String rzti){
		// ���Ҽ۸�
		String hsql3 = " from Edmcrowdkindpricetab pri where  pri.byisuse=1 and pri.icrowdkindid =1 and pri.isnet=1  and pri.itickettypeid="+itickettypeid+" and pri.startdata<='"+ rzti+ "' and pri.enddata>='" + rzti + "' ";
		List priceList = this.find(hsql3);
		if (priceList!=null&&priceList.size()>0) {
			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) priceList.get(0);
			return edmcrowdkindpricetab.getMactualsaleprice();
		}
		return 0;
	}
	public double getPrice(Long itickettypeid,String rzti,Long ibusinessid){
		// ���Ҽ۸�
		String hsql3 = " from Edmcrowdkindpricetab pri where  pri.byisuse=1 and pri.icrowdkindid =1 and pri.isnet=1  and pri.itickettypeid="+itickettypeid+" and pri.startdata<='"+ rzti+ "' and pri.enddata>='" + rzti + "' and ibusinessid="+ibusinessid;
		List priceList = this.find(hsql3);
		if (priceList!=null&&priceList.size()>0) {
			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) priceList.get(0);
			return edmcrowdkindpricetab.getMactualsaleprice();
		}
		return 0;
	}
	
	/*
	 * ���·��(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.dao.idao.ICarlineDao#findLineRelated(java.lang.Long)
	 */
	public List findLineRelatedbyibusinessid(Long iscenicid,Long ibusinessid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(esb.iscenicid as iscenicid, esb.szscenicname as szscenicname,esb.szgrade  as szgrade,esb.szdata  as szdata,edm.bycategorytype as bycategorytype,edm.sztickettypename as sztickettypename, edm.itickettypeid as itickettypeid,edm.mnominalfee as mnominalfee,edm.iscenicid as iscenicid,car.region as region,car.mileage as mileage,car.usetime as usetime) from Edmtickettypetab edm, Carlinedetailtab  car,");
		sql.append(" Esbscenicareatab esb where edm.iscenicid = esb.iscenicid and edm.iscenicid = ? and edm.bycategorytype = car.pmcd  and  edm.byisuse = 1 ");
		List list = find(sql.toString(),new Object[]{iscenicid});
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				double price = getPrice(itickettypeid, Tools.getDays(),ibusinessid);
				map.put("jsprice", price);
			}
		}
		return list;
	}
}
