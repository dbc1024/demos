package com.ectrip.ec.book.shopcart.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.shopcart.dao.idao.IShopCartOtherDAO;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venuearea;

public class ShopCartOtherDAO extends GenericDao implements IShopCartOtherDAO {

	/**
	 * ���ݲ�Ʒ��������ڲ�ѯ�۸� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tikcettypeId
	 * @param rzti
	 * @param ldti
	 * @return return:Edmcrowdkindpricetab Date:2012-2-2
	 */
	public Edmcrowdkindpricetab queryPriceByTicketId(Long tickettypeId, String rzti, String ldti,
			String lgtp) {
		Edmcrowdkindpricetab price = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmcrowdkindpricetab p where p.itickettypeid=" + tickettypeId + " ");
		hsql.append("  and p.byisuse=1 and p.isnet=1 ");
		String strsql = "";
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			strsql = " and ibusinessid=2 ";
		} else {
			strsql = " and ibusinessid=1 ";
		}
		hsql.append(" and p.startdata<='" + rzti + "' and p.enddata>='" + ldti + "'  ");
		hsql.append(strsql);
		hsql.append(" order by p.startdata ");
		System.out.println("--------------->>>price query sql:"+hsql.toString());
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			price = (Edmcrowdkindpricetab) list.get(0);
			System.out.println("----------->>>dao ʵ���ۼ�:"+price.getMactualsaleprice());
		}
		return price;
	}
	
	/**
	 * ���ݲ�Ʒ��������ڲ�ѯ�۸� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tikcettypeId
	 * @param rzti
	 * @param ldti
	 * @return return:Edmcrowdkindpricetab Date:2012-2-2
	 */
	public Edmcrowdkindpricetab queryPriceByTicketId(Long tickettypeId, String rzti, String ldti,
			Long  ibusinessid) {
		Edmcrowdkindpricetab price = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmcrowdkindpricetab p where p.itickettypeid=" + tickettypeId + " ");
		hsql.append("  and p.byisuse=1 and p.isnet=1 ");
		String strsql = "";
		
			strsql = " and ibusinessid="+ ibusinessid;
		
		hsql.append(" and p.startdata<='" + rzti + "' and p.enddata>='" + ldti + "'  ");
		hsql.append(strsql);
		hsql.append(" order by p.startdata ");
		System.out.println("--------------->>>price query sql:"+hsql.toString());
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			price = (Edmcrowdkindpricetab) list.get(0);
			System.out.println("----------->>>dao ʵ���ۼ�:"+price.getMactualsaleprice());
		}
		return price;
	}
	
	
	/**
	 * ���ݲ�Ʒ���  ��ʼ���� ��ֹ���� �û�������Ⱥ�����ȡ�۸�����
	 * @param tickettypeId
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param icrowkindid ��Ⱥ����
	 * @return
	 */
	public Edmcrowdkindpricetab queryPriceByTicketId(Long tickettypeId, String rzti, String ldti,
			String lgtp,String icrowkindid) {
		Edmcrowdkindpricetab price = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmcrowdkindpricetab p where p.itickettypeid=" + tickettypeId + " ");
		hsql.append("  and p.byisuse=1 and p.isnet=1 and p.icrowdkindid="+icrowkindid+"");
		String strsql = "";
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			strsql = " and ibusinessid=2 ";
		} else {
			strsql = " and ibusinessid=1 ";
		}
		hsql.append(" and p.startdata<='" + rzti + "' and p.enddata>='" + ldti + "'  ");
		hsql.append(strsql);
		hsql.append(" order by p.startdata ");
		System.out.println("--------------->>>price query sql:"+hsql.toString());
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			price = (Edmcrowdkindpricetab) list.get(0);
			System.out.println("----------->>>dao ʵ���ۼ�:"+price.getMactualsaleprice());
		}
		return price;
	}

	/**
	 * ���ݼ۸��ж��Ƿ�����Ʊ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tickettypeid
	 * @param rzti
	 * @param ldti
	 * @return return:boolean Date:2012-2-2
	 */
	public boolean queryProductOfIsTaoPao(Long priceId) {
		boolean isuse = false;
		String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid=" + priceId;
		List list = this.find(sql);
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Edmticketcomposepricetab edmcrowdkindpricetab = (Edmticketcomposepricetab) list
						.get(i);
				Edmtickettypetab edmtickettypetab = (Edmtickettypetab) this.get(
						Edmtickettypetab.class, edmcrowdkindpricetab.getItickettypeid());
				if ("0003".equals(edmtickettypetab.getBycategorytype())) {
					isuse = true;
				}
			}
		}
		return isuse;
	}

	/**
	 * �жϲ�Ʒ�Ƿ��Ѿ��ڹ��ﳵ�д���
	 * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	 * 
	 * @auth:huangyuqi
	 * @param iticketypeId
	 * @param userId
	 * @param cookieId
	 * @param rzti
	 * @param ldti
	 * @return return:List Date:2012-2-3
	 */
	public List queryProductIsShopCart(Long iticketypeId, String userId, String cookieId,
			Long icrowdkindpriceid) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Shopcart where itickettypeid=" + iticketypeId);
		if (userId != null && !"".equals(userId)) {
			hsql.append(" and usid='" + userId + "' ");
		}
		if (cookieId != null && !"".equals(cookieId)) {
			hsql.append(" and cookeid='" + cookieId + "' ");
		}
		if (icrowdkindpriceid != null && !"".equals(icrowdkindpriceid)) {
			hsql.append(" and icrowdkindpriceid=" + icrowdkindpriceid);
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * �жϲ�Ʒ��ĳʱ�����Ƿ��Ѿ��ڹ��ﳵ�д���
	 * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	 * 
	 * @auth:huangyuqi
	 * @param iticketypeId
	 * @param userId
	 * @param cookieId
	 * @param rzti���ѿ�ʼʱ��
	 *            ������Ϊ�գ�
	 * @param ldti���ѽ���ʱ��
	 *            ������Ϊ�գ�
	 * @return return:List Date:2012-2-3
	 */
	public List queryProductIsShopCartByTime(Long iticketypeId, String userId, String cookieId,
			String rzti, String ldti, Long icrowdkindpriceid) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Shopcart where itickettypeid=" + iticketypeId);
		if (userId != null && !"".equals(userId)) {
			hsql.append(" and usid='" + userId + "' ");
		}
		if (cookieId != null && !"".equals(cookieId)) {
			hsql.append(" and cookeid='" + cookieId + "' ");
		}
		if (icrowdkindpriceid != null && !"".equals(icrowdkindpriceid)) {
			hsql.append(" and icrowdkindpriceid=" + icrowdkindpriceid);
		}
		hsql.append(" and (((rzti<= '" + rzti + "' and ldti> '" + ldti + "') or (rzti< '" + rzti
				+ "' and ldti>= '" + ldti + "')) or ((rzti>= '" + rzti + "' and ldti <= '" + ldti
				+ "')))");
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ��ѯ����δ֧���Ķ��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:PaginationSupport Date:2012-2-7
	 */
	public List queryOrderList(String usid) {
		List orderList = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select distinct new map(m.orid as orid,m.zfmont as zfmont,m.orda as orda,t.ornm as ornm ) from MOrder m,TOrder t   ");
		if (usid != null && !"".equals(usid)) {
			hsql.append(" where m.usid ='" + usid + "' and m.ddzt='00' and t.dtstartdate<='"
					+ Tools.getDays() + "' and m.orid = t.id.orid ");
		} else {
			hsql.append(" where m.ddzt='00' and t.dtstartdate<='" + Tools.getDays()
					+ "' and m.orid = t.id.orid ");
		}
		hsql.append(" order by m.orid desc ");
		orderList = this.findTopNumb(hsql.toString(), 3);
		if (orderList != null && orderList.size() >= 1) {
			Map map = null;
			for (int i = 0; i < orderList.size(); i++) {
				map = (Map) orderList.get(i);
				if (map.get("orid") != null) {
					List list = queryProductList(map.get("orid").toString());
					map.put("productlist", list);
				}
			}
		}
		return orderList;
	}

	/**
	 * �õ�δ֧�������Ĳ�Ʒ��Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param orid
	 * @return return:List Date:2012-2-7
	 */
	public List queryProductList(String orid) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select distinct new map(tl.id.iscenicid as iscenicid,tl.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,pri.szscenicname as szscenicname) from TOrderlist tl,Edmtickettypetab prd,Esbscenicareatab pri ");
		if (orid != null && !"".equals(orid)) {
			hsql.append(" where tl.id.orid='"
					+ orid
					+ "' and tl.id.iscenicid = pri.iscenicid and tl.itickettypeid=prd.itickettypeid and pri.iscenicid = prd.iscenicid ");
		} else {
			hsql.append(" where tl.id.iscenicid = pri.iscenicid and tl.itickettypeid=prd.itickettypeid and pri.iscenicid = prd.iscenicid ");
		}
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ��ȡ��Ʊ���б� Describe:
	 * ������Ʊ�� ���������Ϣ��װΪ��Ʊ��
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:List Date:2012-2-8
	 */
	public List queryLingPiaoUser(String usid) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(l.seq as seq,l.usid as usid,l.username as username,l.mobile as mobile,l.zjlb as zjlb,l.zjhm as zjhm,l.faxno as faxno,l.isfirst as isfirst ) from Lingpiaouser l where l.usid='"
				+ usid + "' ");
		list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("zjlb") != null && !"".equals(map.get("zjlb"))) {
					String zjlb = map.get("zjlb").toString();
					Sysparv5Id id = new Sysparv5Id();
					id.setPmcd(zjlb);
					id.setPmky("ZJTP");
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, id);
					if (sysparv5 != null) {
						map.put("strzjlb", sysparv5.getPmva());
					}
				}
			}
		} else {
			StringBuffer sql = new StringBuffer();
			sql.append("select new map( 0 as seq,c.usid as usid,c.lname as username,c.mobile as mobile,c.zjlb as zjlb,c.zjhm as zjhm,c.faxno as faxno) from Custom c where c.usid='"
					+ usid + "' ");
			list = this.find(sql.toString());
			if (list != null && list.size() >= 1) {
				Map map = null;
				for (int i = 0; i < list.size(); i++) {
					map = (Map) list.get(i);
					if (map.get("zjlb") != null && !"".equals(map.get("zjlb"))) {
						String zjlb = map.get("zjlb").toString();
						Sysparv5Id id = new Sysparv5Id();
						id.setPmcd(zjlb);
						id.setPmky("ZJTP");
						Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, id);
						if (sysparv5 != null) {
							map.put("strzjlb", sysparv5.getPmva());
						}
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * ��ȡ��Ʊ����Ϣ
	 * ����������
	 */
	public List queryLingPiao(String usid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(l.seq as seq,l.usid as usid,l.username as username,l.mobile as mobile,l.zjlb as zjlb,l.zjhm as zjhm,l.faxno as faxno,l.isfirst as isfirst ) from Lingpiaouser l where l.usid='"
				+ usid + "' order by l.isfirst desc");
		List list  = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("zjlb") != null && !"".equals(map.get("zjlb"))) {
					String zjlb = map.get("zjlb").toString();
					Sysparv5Id id = new Sysparv5Id();
					id.setPmcd(zjlb);
					id.setPmky("ZJTP");
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, id);
					if (sysparv5 != null) {
						map.put("strzjlb", sysparv5.getPmva());
					}
				}
			}
		} 
		return list;
	}

	/**
	 * ��ȡ��ƱƱ���˴���Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettype��Ʒ���
	 * @param rzti�������
	 * @param zfdate������
	 * @return return:List Date:2012-2-9
	 */
	public List queryRafttripInfoList(Long itickettypeId, String rzti, String zfdate, String lgtp)
			throws IllegalAccessException, InvocationTargetException {
		// ����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������
		List list = getNumberControllData(itickettypeId, zfdate, "02");

		StringBuffer hql = new StringBuffer(
				"select new map(numcontroll.productcontrolid as productcontrolid, tm.productmanageid as productmanageid,trip.tripname as tripname,tm.starttime as starttime,tm.endtime as endtime,tm.startdata as startdata,tm.enddata as enddata,wharf.ivenueareaname as ivenueareaname,trip.tripid as tripid,");
		if (list == null || list.size() < 1) {
			// hql.append("numcontroll.salablenumber as salablenumber,");
			hql.append("(numcontroll.salablenumber-numcontroll.soldnumber) as salablenumber,");
		} else {
			Productcontrol pc = new Productcontrol();
			BeanUtils.populate(pc, (Map) list.get(0));
			int raftnumber = pc.getSalablenumber().intValue() - pc.getSoldnumber().intValue();
			// hql
			// .append("(select (case when p.salablenumber>"
			// + pc.getSalablenumber()
			// + " then "
			// + pc.getSalablenumber()
			// +
			// "  else p.salablenumber end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");
			//
			hql.append("(select (case when (p.salablenumber-p.soldnumber)>"
					+ raftnumber
					+ " then "
					+ raftnumber
					+ "  else (p.salablenumber-p.soldnumber) end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");

		}
		StringBuffer zfticketid = new StringBuffer();
		List sonproList = getSonProductList(itickettypeId, rzti, rzti, lgtp, null);
		if (sonproList != null && sonproList.size() >= 1) {
			for (int j = 0; j < sonproList.size(); j++) {
				Edmtickettypetab pro = (Edmtickettypetab) sonproList.get(j);
				if (pro != null) {
					if ("0003".equals(pro.getBycategorytype())) {
						if (zfticketid != null && !"".equals(zfticketid)
								&& !"".equals(zfticketid.toString())) {
							zfticketid.append("," + pro.getItickettypeid());
						} else {
							zfticketid.append(pro.getItickettypeid());
						}

					}
				}
			}
		}

		if (zfticketid == null || "".equals(zfticketid) || "".equals(zfticketid.toString())) {
			zfticketid.append("0");
		}

		hql.append(" ticket.iscenicid as iscenicid) from Prdtripvenuemanage tm,Edmtickettypetab ticket,Trip trip,Venuearea wharf,Productcontrol numcontroll"
				+ " where tm.itickettypeid = ticket.itickettypeid and numcontroll.bystate=1   and trip.iscenicid = tm.iscenicid and tm.tripid = trip.tripid and ticket.itickettypeid in ("
				+ zfticketid
				+ ") and ticket.byisuse=1 and   trip.byisuse=1  and tm.startdata <= '"
				+ zfdate
				+ "' and tm.enddata >= '"
				+ zfdate
				+ "' and tm.iscenicid=wharf.iscenicid and tm.ivenueareaid=wharf.ivenueareaid and tm.iscenicid=numcontroll.iscenicid and ticket.itickettypeid=numcontroll.itickettypeid and trip.tripid=numcontroll.tripid and tm.ivenueareaid=numcontroll.ivenueareaid and tm.ivenueid=numcontroll.ivenueid and numcontroll.stdata='"
				+ zfdate + "'");
		if (Tools.getDayNumb(zfdate, Tools.getDays()) == 1) {
			hql.append(" and tm.starttime>='" + Tools.getNowTime().substring(0, 5) + "'");
		}
		hql.append(" group by numcontroll.salablenumber,numcontroll.productcontrolid,tm.productmanageid,trip.tripname,tm.starttime,tm.endtime,tm.startdata,tm.enddata,wharf.ivenueareaname,ticket.iscenicid,ticket.itickettypeid,trip.tripid order by tm.starttime");
		return find(hql.toString());
	}

	/**
	 * ����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param iticketid
	 * @param date
	 * @param controlltype
	 *            00���������� 01:�տ��� 02:�˴ο��� 03:�˴�������� 04����λ����
	 * @return return:List Date:2012-2-9
	 */
	public List getNumberControllData(Long iticketid, String date, String controlltype) {
		String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.controltype as controltype) from Productcontrol pc where itickettypeid="
				+ iticketid
				+ " and stdata='"
				+ date
				+ "' and byisuse=1 and bystate=1 and pc.controltype='" + controlltype + "'";
		if (controlltype.equals("02")) {
			hql += " and tripid is null";
		}
		return find(hql);
	}

	/**
	 * ��ѯ�Ӳ�Ʒ��Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettypeId��Ʒ���
	 * @param rzti����
	 * @param ldti����
	 * @return return:List Date:2012-2-3
	 */
	public List getSonProductList(Long itickettypeId, String rzti, String ldti, String lgtp,
			Long icrowdkindpriceid) {
		List proList = new ArrayList();
		try {

			StringBuffer hsql = new StringBuffer();
			hsql.append("from Edmcrowdkindpricetab p where p.itickettypeid=" + itickettypeId);

			hsql.append("  and p.byisuse=1 and p.isnet=1 ");
			String strsql = "";
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				strsql = " and ibusinessid=2 ";
			} else {
				strsql = " and ibusinessid=1 ";
			}
			hsql.append(" and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.byisuse=1 "
					+ strsql
					+ " and edm.isnet=1 and edm.itickettypeid=p.itickettypeid) <='"
					+ rzti
					+ "'  and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where  edm.byisuse=1 "
					+ strsql
					+ " and edm.isnet=1 and edm.itickettypeid=p.itickettypeid))>='"
					+ ldti
					+ "' ");
			hsql.append(" and (p.startdata<='" + rzti + "' and p.enddata>='" + rzti
					+ "' or p.startdata<='" + ldti + "' and p.enddata>='" + ldti + "') ");
			hsql.append(strsql);
			if (icrowdkindpriceid != null) {
				hsql.append(" and p.icrowdkindpriceid=" + icrowdkindpriceid);
			}

			List list = this.find(hsql.toString());
			if (list != null && list.size() >= 1) {
				for (int i = 0; i < list.size(); i++) {
					Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) list.get(i);
					if (price != null) {
						String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid="
								+ price.getIcrowdkindpriceid();
						List pricelist = this.find(sql);
						if (pricelist != null && pricelist.size() >= 1) {
							for (int j = 0; j < pricelist.size(); j++) {
								Edmticketcomposepricetab edmticketcomposepricetab = (Edmticketcomposepricetab) pricelist
										.get(j);
								Edmtickettypetab edmtickettypetab = (Edmtickettypetab) this.get(
										Edmtickettypetab.class,
										edmticketcomposepricetab.getItickettypeid());
								proList.add(edmtickettypetab);
							}
						}
					}
				}
			}

			return proList;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * ���ݲ�ƷID������Id,ʱ������ѯ�������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tripid����id
	 * @param iticketyIdƱID
	 * @param stdt����
	 * @return return:boolean Date:2012-2-10
	 */
	public List queryRaftIsShop(Long tripid, Long iticketypeId, String stdt) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();

		hsql.append("select new map(p.salablenumber as salablenumber,p.soldnumber as soldnumber,pm.starttime as starttime )  from Productcontrol p,Prdtripvenuemanage pm where p.itickettypeid="
				+ iticketypeId + " and p.stdata= '" + stdt + "' ");
		if (tripid != null && !"".equals(tripid) && 0L != tripid) {
			hsql.append(" and p.tripid=" + tripid);
		}
		hsql.append(" and p.byisuse=1 and p.bystate=1 and p.tripid is not null ");
		hsql.append(" and p.tripid = pm.tripid and pm.startdata<='" + stdt + "' and pm.enddata>='"
				+ stdt + "' ");
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * ��ѯ���� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerId�����̱��
	 * @param venuetype�������
	 * @return return:Venue Date:2012-2-14
	 */
	public Venue queryVenue(Long providerId, String venuetype) {
		Venue venue = null;
		String sql = " from Venue where iscenicid = " + providerId + " and venuetype='" + venuetype
				+ "' ";
		List list = this.find(sql);
		if (list != null && list.size() >= 1) {
			venue = (Venue) list.get(0);
		}
		return venue;
	}

	/**
	 * ��ѯ�������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param venueId���ر��
	 * @return return:Venuearea Date:2012-2-14
	 */
	public Venuearea queryVenueArea(Long venueId) {
		Venuearea venuearea = null;
		String sql = " from Venuearea where ivenueid = " + venueId;
		List list = this.find(sql);
		if (list != null && list.size() >= 1) {
			venuearea = (Venuearea) list.get(0);
		}
		return venuearea;
	}

	/**
	 * �����˴Ρ���Ʒ�������ڲ�ѯ��Ʒ�˴������ϵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tripid�˴�
	 * @param itickettypeid
	 *            ��ƷID
	 * @param zfdate��������
	 * @return return:Prdtripvenuemanage Date:2012-2-15
	 */
	public List quereyPrdtripvenuemanage(Long tripid, Long itickettypeid, String zfdate) {
		List list = new ArrayList();
		Prdtripvenuemanage prdtripvenuemanage = new Prdtripvenuemanage();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Prdtripvenuemanage where startdata<='" + zfdate + "' and enddata>='"
				+ zfdate + "' and itickettypeid=" + itickettypeid);
		if (tripid != null && !"".equals(tripid) && 0L != tripid) {
			hsql.append(" and tripid = " + tripid);
		}
		list = this.find(hsql.toString());
		return list;
	}

}
