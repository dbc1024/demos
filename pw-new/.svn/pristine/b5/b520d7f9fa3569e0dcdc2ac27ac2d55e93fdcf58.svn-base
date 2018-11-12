package com.ectrip.ec.line.dao;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.line.dao.idao.ILineDao;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public class LineDao extends GenericDao implements ILineDao {
	/*
	 * ��ҳ(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.line.dao.idao.ILineDao#findByLineType(java.lang.String,
	 * boolean)
	 */
	public List findByLineType(String type, boolean isImage,String condition) {
		StringBuffer sql = new StringBuffer();
		if (!isImage) {
			sql
					.append("select edm.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,esb.iscenicid as iscenicid,lp.startingmethod as startingmethod,lp.startingdata as startingdata from Edmtickettypetab edm inner join Sysparv5 s");
			sql.append("  on s.pmcd = edm.bycategorytype ");
			sql.append(" inner join Esbscenicareatab esb");
			sql.append(" on esb.iscenicid = edm.iscenicid");
			sql
					.append(" inner join Lineproduct lp on lp.itickettypeid = edm.itickettypeid ");
			//sql.append(" inner join Edmcrowdkindpricetab edmc");
			//sql.append(" on edmc.itickettypeid = edm.itickettypeid");
			//sql.append(" where edmc.startdata = ?  and edmc.isnet = 1 and edmc.byisuse = 1 and edmc.enddata = ? and //edmc.inote4>0 and edmc.icrowdkindid=1");
			sql
					.append(" where  s.pmky = 'PRTP'  and s.spmcd = '07' ");
			sql.append(" and esb.byisuse = 1 and edm.byisuse = 1");
			if (condition!=null&&condition.length()>0&&!"".equals(condition.trim())) {
				sql.append(" and edm.itickettypeid not in ("+condition+")");
			}
			sql
					.append(" and edm.bycategorytype = ? and rownum<=5  order by esb.iordernumber,edm.isequence desc");
		} else {
			sql
					.append("select  edm.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,esb.iscenicid as iscenicid,lp.startingmethod as startingmethod,lp.startingdata as startingdata from Edmtickettypetab edm inner join Sysparv5 s");
			sql
					.append(" on s.pmcd = edm.bycategorytype inner join Esbscenicareatab esb ");
			sql.append(" on esb.iscenicid = edm.iscenicid ");
					//"inner join Edmcrowdkindpricetab edmc");
			//sql.append(" on edmc.itickettypeid = edm.itickettypeid ");
			sql
					.append(" inner join Lineproduct lp  on lp.itickettypeid = edm.itickettypeid");
			//sql.append(" where  edmc.startdata = ? and edmc.inote4>0 and edmc.isnet = 1 and edmc.enddata = ? and //edmc.icrowdkindid=1");
			sql
					.append(" where s.pmky = 'PRTP' and s.spmcd = '07' ");
			sql
					.append(" and esb.byisuse = 1 and edm.byisuse = 1 ");
			//sql.append("  and edmc.byisuse = 1");
			sql
					.append(" and edm.bycategorytype = ? and rownum<=3 order by esb.iordernumber,edm.isequence desc");
		}
		try {
			List list = findBySqlToMap(sql.toString(),type);
			if (isImage) {//��ͼƬ��
				sql.setLength(0);
				sql.append("select up.upid as upid,up.upadder as upadder,up.upfilename as upfilename from (select sec.upid, sec.itickettypeid from Secenicpicture sec  order by sec.isecenicpictureid) s,Upfile up where rownum = 1 and s.upid = up.upid and  s.itickettypeid=?");
				if (list!=null&&list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
						List imglist = findBySqlToMap(sql.toString(), itickettypeid);
						if (imglist!=null&&imglist.size()>0) {
							Map img = (Map) imglist.get(0);
							map.putAll(img);
						}
					}
				}
			}
			
			//��ѯ�۸�
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long itickettypeid = Long.valueOf(map.get("itickettypeid".toUpperCase()).toString());
					double price = getPrice(itickettypeid, Tools.getDays());
					map.put("listingprice".toUpperCase(), price);
				}
			}
			
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	/*@Override
	public List findBySqlToMap(String sql, Object... params) {
		List list = null;
		try {
			Query query = getSession().createSQLQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

	/*
	 * ������ҳ(non-Javadoc)
	 * 
	 * @see com.ectrip.book.line.dao.idao.ILineDao#searchLine(java.lang.String,
	 * java.lang.String, java.lang.String, int, java.lang.String,
	 * java.lang.String)
	 */
	public PaginationSupport searchLine(String searchKey, String searchString,
			String title, int pageSize, String startIndex, String url) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(lp.linenotb as linenotb, edm.bycategorytype as bycategorytype,lp.startingdata  as startingdata,lp.prdstatus  as numbers,esb.iscenicid  as iscenicid,lp.destination  as destination,lp.datanumber   as datanumber,edm.sztickettypename as sztickettypename,edm.itickettypeid  as itickettypeid,lp.departure   as departure,lp.startingmethod  as startingmethod) from Edmtickettypetab edm ,Sysparv5 s ,Esbscenicareatab esb  ,Lineproduct lp ");
		//sql.append(" where  edmc.startdata = '" + Tools.getDays()
		//		+ "' and edmc.enddata = '" + Tools.getDays()
		//		+ "' and edmc.icrowdkindid=1  and edmc.byisuse = 1 and edmc.isnet = 1");
		sql
				.append(" where s.id.pmky = 'PRTP' and s.spmcd = '07'  ");
		sql
				.append(" and esb.byisuse = 1 and edm.byisuse = 1 ");
		sql
				.append(" and edm.bycategorytype= s.id.pmcd and edm.itickettypeid = lp.itickettypeid");
		//sql.append(" and edm.itickettypeid = edmc.itickettypeid  and edmc.inote4>0");
		sql.append(" and edm.iscenicid = esb.iscenicid  ");
		List list = null;
		StringBuffer condition = new StringBuffer();
		String[] types = (searchString == null || searchString.trim()
				.equals("")) ? null : searchString.split(",");
		String[] days = (title == null || title.trim().equals("")) ? null
				: title.split(",");
		String type = "";
		String day = "";
		if (types != null) {
			for (int i = 0; i < types.length; i++) {
				if (i != 0) {
					type += ",";
				}
				type += "'" + types[i] + "'";
			}
		}
		if (type != null && !type.trim().equals("")) {
			condition.append("  edm.bycategorytype in (" + type + ") ");
		}
		if (days != null) {
			for (int i = 0; i < days.length; i++) {
				if (i != 0) {
					day += ",";
				}
				day += "'" + days[i] + "'";
			}
		}
		if (day != null && !day.trim().equals("")) {
			if (!condition.toString().trim().equals("")) {
				condition.append(" and lp.datanumber in (" + day + ") ");
			} else {
				condition.append("  lp.datanumber in (" + day + ") ");
			}
		}
		if (searchKey != null && !searchKey.trim().equals("")) {
			if (!condition.toString().trim().equals("")) {
				condition.append(" and ( lp.linenotb like '%" + searchKey
						+ "%' or lp.destination like '%" + searchKey
						+ "%' or edm.sztickettypename like  '%" + searchKey
						+ "%'  )");
			} else {
				condition.append("  lp.linenotb like '%" + searchKey
						+ "%' or lp.destination like '%" + searchKey
						+ "%'  or edm.sztickettypename like  '%" + searchKey
						+ "%'  ");
			}
		}
		if (!condition.toString().trim().equals("")) {
			sql.append(" and (" + condition.toString() + ")");
		}
		sql.append("   order by esb.iordernumber,edm.isequence desc");
		System.out.println(sql.toString());
		PaginationSupport ps = findPage(sql.toString(), pageSize, Integer
				.parseInt(startIndex), url);

		if (ps.getItems().size() > 0) {
			List itmes = ps.getItems();

			for (int i = 0; i < itmes.size(); i++) {
				Map item = (Map) itmes.get(i);
				//ͼƬ
				Long itickettypeid = (Long) item.get("itickettypeid");
				String hsq = "select up.upid as upid,up.upadder as upadder,up.upfilename as upfilename from (select sec.upid, sec.itickettypeid from Secenicpicture sec  order by sec.isecenicpictureid) s,Upfile up where rownum = 1 and s.upid = up.upid and  s.itickettypeid=?";
				List img;
				try {
					img = findBySqlToMap(hsq,itickettypeid);
					if (img.size() > 0) {
						Map imamge =  (Map) img.get(0);
						item.putAll(imamge);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//��ѯ�۸�
				double price = getPrice(itickettypeid, Tools.getDays());
				item.put("youngprice", price);
			}
		}

		return ps;
	}

	/*
	 * ��ѯͼƬ(non-Javadoc)
	 * 
	 * @see com.ectrip.book.line.dao.idao.ILineDao#findImg(java.lang.Long)
	 */
	public List findImg(Long itickettypeid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(sec.iscenicid as iscenicid,sec.itickettypeid as itickettypeid,up.upid as upid,up.upfilename as upfilename,up.upname as upname,up.upadder as upadder) from Secenicpicture sec,Upfile up where sec.itickettypeid=? and sec.upid = up.upid and rownum<=5 order by sec.upid  ");
		return find(sql.toString(), new Object[] { itickettypeid });
	}

	/*
	 * ����(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.line.dao.idao.ILineDao#findByItickettypeid(java.lang.
	 * Long)
	 */
	public List findByItickettypeid(Long itickettypeid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(lp.linenotb as linenotb,lp.daynum as daynum, lp.startingdata as startingdata,lp.prdstatus as numbers, esb.iscenicid as iscenicid,lp.destination as destination,lp.datanumber as datanumber , edm.sztickettypename as sztickettypename,edm.itickettypeid as itickettypeid, lp.deposit as deposit,lp.status as cukuway,lp.deposittype as zffs,lp.departure as departure,s.pmva as linetype,lp.startingmethod as startingmethod,lp.audiorum as pricedescript,lp.note as linedescript,lp.desnomn as desnomn ) from Edmtickettypetab edm,Esbscenicareatab esb,Lineproduct lp,Sysparv5 s");
		sql
				.append(" where  s.id.pmky = 'PRTP' and s.spmcd = '07' and esb.byisuse = 1");
		sql.append(" and edm.byisuse = 1");
		sql
				.append(" and edm.bycategorytype= s.id.pmcd and edm.itickettypeid = lp.itickettypeid");
		sql.append(" and edm.iscenicid = esb.iscenicid");
		sql.append(" and edm.itickettypeid=?");
		List list = find(sql.toString(), new Object[] { itickettypeid });

		for (int i = 0; i < list.size(); i++) {
			try {
				Map map = (Map) list.get(i);
				Blob linedescript = (Blob) map.get("linedescript");
				if (linedescript != null) {

					String string = Tools.getStrByBlob(linedescript);
					map.put("linedescript", string);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	/*
	 * �Ƽ�(non-Javadoc)
	 * 
	 * @see com.ectrip.book.line.dao.idao.ILineDao#tJLine()
	 */
	public List tJLine() {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(lp.startingdata as startingdata,lp.prdstatus as numbers, esb.iscenicid as iscenicid,lp.destination as destination,lp.datanumber as datanumber , edm.sztickettypename as sztickettypename,edm.itickettypeid as itickettypeid, lp.deposit as deposit,lp.deposittype as cukuway,lp.status as zffs,lp.departure as departure,s.pmva as linetype,lp.startingmethod as startingmethod,edmc.mactualsaleprice as youngprice,edmc.weekendprice as roomcha,edmc.inote4 as youngcount,lp.audiorum as pricedescript,lp.note as linedescript,lp.desnomn as desnomn ) from Edmtickettypetab edm,Esbscenicareatab esb,Edmcrowdkindpricetab edmc,Lineproduct lp,Sysparv5 s");
		sql
				.append(" where edmc.startdata = ? and edmc.enddata = ? and s.id.pmky = 'PRTP' and s.spmcd = '07' and esb.byisuse = 1");
		sql
				.append(" and edm.byisuse = 1 and edmc.isnet = 1 and edmc.byisuse = 1  and edmc.icrowdkindid=1 ");
		sql
				.append(" and edm.bycategorytype= s.id.pmcd and edm.itickettypeid = lp.itickettypeid");
		sql
				.append(" and edm.itickettypeid = edmc.itickettypeid and edmc.inote4>0");
		sql
				.append(" and edm.iscenicid = esb.iscenicid and lp.linenota=1 and rownum<=5");
		return find(sql.toString(), new Object[] { Tools.getDays(),
				Tools.getDays() });
	}

	/**
	 * ��ѯ�۸�
	 */
	public List findPriceByDate(Long itickettypeid, String date) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(edm.itickettypeid as itickettypeid, edmc.icrowdkindid as icrowdkindid,edmc.mactualsaleprice as youngprice,edmc.weekendprice as roomcha,edmc.inote4 as youngcount) from Edmtickettypetab edm,Esbscenicareatab esb,Edmcrowdkindpricetab edmc,Lineproduct lp,Sysparv5 s");
		sql
				.append(" where edmc.startdata = ? and edmc.enddata = ? and s.id.pmky = 'PRTP' and s.spmcd = '07' and esb.byisuse = 1");
		sql
				.append(" and edm.byisuse = 1 and edmc.isnet = 1 and edmc.byisuse = 1");
		sql
				.append(" and edm.bycategorytype= s.id.pmcd and edm.itickettypeid = lp.itickettypeid");
		sql.append(" and edm.itickettypeid = edmc.itickettypeid ");
		sql.append(" and edm.iscenicid = esb.iscenicid");
		sql.append(" and edm.itickettypeid=?");
		List list = find(sql.toString(), new Object[] { date, date,
				itickettypeid });
		return list;
	}

	/*
	 * �޸Ŀ��(non-Javadoc)
	 * 
	 * @see com.ectrip.book.line.dao.idao.ILineDao#updateLineNumb(int, int,
	 * java.lang.Long, java.lang.String)
	 */
	public void updateLineNumb(int num, Long icrowdkindpriceid,final String orid) {
		Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) get(
				Edmcrowdkindpricetab.class, icrowdkindpriceid);
		final String m_orid = orid;
		final Long oldnumb = edmcrowdkindpricetab.getInote4();
		//���ɶ�ʱ�����¿�����
		/*new java.util.Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				//������
				List list = find("select new map(m.isi as isi,m.isjl as isjl,m.orid as orid,m.usid as usid,m.ddzt as ddzt,m.mont as mont,m.zfmont as zfmont,m.orda as orda,m.orti as orti,s.id.pmcd as pmcd,s.pmva as pmva,m.tpmont as tpmont,m.tpsx as tpsx,m.yhamnt as yhamnt) from MOrder m,Sysparv5 s where s.id.pmky='DDZT' and m.ddzt='00' and s.id.pmcd=m.ddzt and m.orid='"+ m_orid + "'");
				//�Ӷ�����ϸ
				List<TOrderlist> tOrderlists = find("from TOrderlist t where t.id.orid='"+orid+"'");
				
				
				if (list!=null&&list.size()>0) {
						Map old_morder = (Map) list.get(0);
						//����δ����
						if (old_morder.get("ddzt").toString().equals("00")) {
							if (tOrderlists!=null&&tOrderlists.size()>0) {
								for (TOrderlist tOrderlist : tOrderlists) {
										//���ؿ��
									Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) get(Edmcrowdkindpricetab.class, tOrderlist.getIcrowdkindpriceid());
									edmcrowdkindpricetab.setInote4(edmcrowdkindpricetab.getInote4()+tOrderlist.getNumb());
										//����
									update(edmcrowdkindpricetab);
								}
							}
							
							//����������
							MOrder mOrder = (MOrder) get(MOrder.class, orid);
							mOrder.setDdzt("98");//����
							//����������
							update(mOrder);
					
					}
				}
				System.out.println("��涨ʱ�޸����.............");
			}
		},1000*60*60);//һСʱ��ִ��
*/		Long newnumb = oldnumb - num;
		edmcrowdkindpricetab.setInote4(newnumb);
		update(edmcrowdkindpricetab);

	}
	/*
	 * ֧���ɹ��޸Ķ���(non-Javadoc)
	 * @see com.ectrip.book.line.dao.idao.ILineDao#updateLineNumbByPay(java.lang.String)
	 */
	public boolean updateLineNumbByPay(String orid) {
		try {
			//�Ӷ�����ϸ
			List<TOrderlist> tOrderlists = find("from TOrderlist t where t.id.orid='"+orid+"'");
			if (tOrderlists!=null&&tOrderlists.size()>0) {
				for (TOrderlist tOrderlist : tOrderlists) {
					//������
					Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, tOrderlist.getIscenicid());
					if (esb.getScenictype().equals("07")) {//��·�Ž���һ�²���
						//�޸�����
						Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) get(Edmcrowdkindpricetab.class, tOrderlist.getIcrowdkindpriceid());
						edmcrowdkindpricetab.setInote4(edmcrowdkindpricetab.getInote4()-tOrderlist.getNumb());
						update(edmcrowdkindpricetab);
					}
				}
				return true;
			}
		} catch (Exception e) {
			Tools.printException(e);
			return false;
		}
		return false;
	}

	public boolean checkLineNumbByPay(String orid) {
		boolean status = false;
		try {
			//�Ӷ�����ϸ
			List<TOrderlist> tOrderlists = find("from TOrderlist t where t.id.orid='"+orid+"'");
			if (tOrderlists!=null&&tOrderlists.size()>0) {
				for (TOrderlist tOrderlist : tOrderlists) {
					//������
					Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, tOrderlist.getIscenicid());
					if (esb.getScenictype().equals("07")) {//��·�Ž���һ�²���
						//�޸�����
						Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) get(Edmcrowdkindpricetab.class, tOrderlist.getIcrowdkindpriceid());
						if (edmcrowdkindpricetab.getInote4()!=null&&edmcrowdkindpricetab.getInote4().intValue()>tOrderlist.getNumb().intValue()) {
							status = true;
						}else {
							status = false;//���ڿ�治�㣬����ѭ��
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			Tools.printException(e);
			return false;
		}
		return status;
	}


	public List getJsonTJLine(String top,String keyword) {
		StringBuffer sql = new StringBuffer();
		List list = new ArrayList();
		sql.append("select new map(esb.iscenicid as iscenicid,edm.sztickettypename as sztickettypename,edm.itickettypeid as itickettypeid,lp.departure as departure,s.pmva as linetype) from Edmtickettypetab edm,Esbscenicareatab esb ,Lineproduct lp,Sysparv5 s");
		sql.append(" where  s.id.pmky = 'PRTP' and s.spmcd = '07' and esb.byisuse = 1");
		sql.append(" and edm.byisuse = 1  ");
		sql.append(" and edm.bycategorytype= s.id.pmcd and edm.itickettypeid = lp.itickettypeid");
		//sql.append(" and edm.itickettypeid = edmc.itickettypeid and edmc.inote4>0");
		sql.append(" and edm.iscenicid = esb.iscenicid and lp.linenota=1 ");
		if (top!=null || !top.equals("")) {
			sql.append(" and rownum<="+top);
		}else {
			sql.append(" and rownum<=5");
		}
		
		String [] keywords = null;
		
		if (keyword!=null&&!keyword.equals("")) {
			keywords = keyword.split(",");
		}
		
		if (keyword!=null&&!keyword.equals("")) {
			sql.append(" and (");
			for (int i = 0; i < keywords.length; i++) {
				if (i!=0) {
					sql.append(" or ");
				}
				//��ؼ���
				sql.append("lp.linenotb like '%" + keywords[i]
					+ "%' or lp.destination like '%" + keywords[i]
					+ "%' or edm.sztickettypename like  '%" + keywords[i]
					+ "%'  ");
			}
			sql.append(" )");
		}
		list = find(sql.toString());
		// ͼƬ
		if (list!=null&&list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				Map item = (Map) list.get(i);
				//ͼƬ
				Long itickettypeid = (Long) item.get("itickettypeid");
				String hsq = "select up.upid as upid,up.upadder as upadder,up.upfilename as upfilename from (select sec.upid, sec.itickettypeid from Secenicpicture sec  order by sec.isecenicpictureid) s,Upfile up where rownum = 1 and s.upid = up.upid and  s.itickettypeid=?";
				List img;
				try {
					img = findBySqlToMap(hsq,itickettypeid);
					if (img.size() > 0) {
						Map imamge =  (Map) img.get(0);
						item.putAll(imamge);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//�۸�
				double price = getPrice(itickettypeid, Tools.getDays());
				item.put("youngprice", price);
			}
		}
		
		return list;
	}
	
	/**
	 * ��·�۸�ͨ�ò�ѯ
	 */
	public double getPrice(Long itickettypeid,String rzti){
		// ���Ҽ۸�
		String hsql3 = " from Edmcrowdkindpricetab pri where  pri.byisuse=1 and pri.icrowdkindid =1 and pri.isnet=1  and pri.itickettypeid="+itickettypeid+" and pri.startdata='"+ rzti+ "' and pri.enddata='" + rzti + "' ";
		List priceList = this.find(hsql3);
		if (priceList!=null&&priceList.size()>0) {
			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) priceList.get(0);
			return edmcrowdkindpricetab.getMactualsaleprice();
		}
		return 0;
	}
}
