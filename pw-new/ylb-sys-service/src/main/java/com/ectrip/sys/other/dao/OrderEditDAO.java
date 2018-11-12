package com.ectrip.sys.other.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.other.dao.idao.IOrderEditDAO;

public class OrderEditDAO extends GenericDao implements IOrderEditDAO {

	private String SYSPAR_HSQL = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where ";

	// ����
	public List findOneList(String pmky, String spmcd) {
		String hsql = SYSPAR_HSQL + "sys.id.pmky=? and sys.spmcd=? and sys.isvalue=1 order by sys.id.pmcd";
		return find(hsql, new Object[] { pmky, spmcd });
	}

	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
		PaginationSupport ps = null;

		StringBuffer hsql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		hsql.append("select distinct new map( yor.id.orid as orid,yor.usid as usid,us.corpname as corpname,mor.orda as orda,yor.id.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,yor.ornm as ornm,mor.payorid as payorid ,yor.orhm as orhm,yor.orph as orph,yor.ddzt as ddzt,v5.pmva as strddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx ) from YOrder yor,MOrder mor,Custom us,Sysparv5 v5,Esbscenicareatab es ");
		where.append(" where mor.ortp='01' ");
		if (0 == order.getRadiobutton3()) {// ������
			if (order.getOrid() != null && !"".equals(order.getOrid())) {
				where.append(" and mor.orid='" + order.getOrid() + "' ");
			}
		} else if (1 == order.getRadiobutton3()) {// ��ϵ������
			if (order.getStrornm() != null && !"".equals(order.getStrornm())) {
				where.append(" and yor.ornm like '%" + order.getStrornm() + "%' ");
			}
		} else if (2 == order.getRadiobutton3()) {// ��ϵ��֤������
			if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
				where.append(" and lower(yor.orhm) = lower('" + order.getOrhm() + "') ");
			}
		} else if (3 == order.getRadiobutton3()) {// ����δ�충��
			where.append(" and yor.dtstartdate<'" + Tools.getDays() + "' and yor.ddzt='02' and yor.mont>0 ");
		} else if (4 == order.getRadiobutton3()) {// �г̵���
			if (order.getSztravelbillno() != null && !"".equals(order.getSztravelbillno())) {
				where.append(" and yor.sztravelbillno ='" + order.getSztravelbillno() + "' ");
			}
		} else if (5 == order.getRadiobutton3()) { // ֧��������
			if (order.getPayorid() != null && !"".equals(order.getPayorid())) {
				where.append(" and mor.payorid='" + order.getPayorid() + "' ");
			}
		}
		where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
		where.append(" order by yor.id.orid desc ");
		System.out.println(hsql.toString() + where.toString());
		ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);

		List list = ps.getItems();
		if (list.size() >= 1) {
			Map map = null;
			String orid = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("orid") != null) {// ������
					orid = map.get("orid").toString();
					Long cenicid = new Long(map.get("iscenicid").toString());
					// String
					String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,tor.dtstartdate as dtstartdate,prd.sztickettypename as sztickettypename,tzl.pric as pric,tzl.numb as numb,tzl.amnt as amnt,tzl.isj as isj )from TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor  where  ( mor.orid='"
							+ orid
							+ "' or mor.srid='"
							+ orid
							+ "') and mor.orid=tor.id.orid and tor.id.iscenicid="
							+ cenicid
							+ " and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid order by tzl.isj desc";
					List torderList = this.find(sql);
					if (torderList.size() >= 1) {
						for (int j = 0; j < torderList.size(); j++) {
							Map zmap = (Map) torderList.get(j);
							if (null != zmap.get("isj")) {
								if (Long.parseLong(zmap.get("isj").toString()) == -1) {
									zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
								}
							}
						}
						map.put("torderlists", torderList);
					}

				}
			}
		}
		return ps;
	}
}
