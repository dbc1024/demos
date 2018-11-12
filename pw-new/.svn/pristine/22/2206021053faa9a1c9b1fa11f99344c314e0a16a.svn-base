package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.permitenter.Discountcoupondetail;
import com.ectrip.ticket.permitenter.dao.IDiscountcoupondetailDAO;

public class DiscountcoupondetailDAO extends GenericDao implements
		IDiscountcoupondetailDAO {

	/**
	 * Describe:ɾ���Ż݄���ϸ auth:shenzhixiong
	 * 
	 * @param discountcoupondetail
	 * @param syslog
	 *            return void Date 2014-4-1
	 */
	public void deleteDiscountcoupondetail(String code,Long sum, Syslog syslog) {
		String sql = "from Discountcoupondetail dcd  where dcd.szcode like '"
				+ code + "%' and dcd.byisuser = 0 order by dcd.dcdid desc";
		List list = this.find(sql);
		for (int i = 0; i <  sum; i++) {
			Discountcoupondetail dcd = (Discountcoupondetail) list.get(i);
			this.delete(dcd);
		}

//		syslog.setStlg("0277");
//		syslog.setBrief("ɾ���Ż݄���ϸ��Ϣ��" + code);
//		syslog.setNote("ɾ���Ż݄���ϸ��Ϣ��" + code);
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);

	}

	/**
	 * Describe:�����Ż݄������ѯ�Ż݄���ϸ��Ϣ
	 * 
	 * @auth:shenzhixiong
	 * @param dtmakedate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return list Date:2014-4-1
	 */
	public PaginationSupport getdiscountcoupondetail(Long dcid,
			int pageSize, int startIndex, String url) {
		PaginationSupport ps=null;
		String sql = "select new map (dcd.dcdid as dcdid,dcd.szcode as szcode,dcd.usid as usid,dcd.byisuser as byisuser,dcd.dcdmode as dcdmode ,dcd.orid as orid,dcd.dcdmoney,dcd.dcdmoney,dcd.dcdreleasedate as dcdreleasedate,dcd.dcdusedate as dcdusedate)from Discountcoupondetail dcd where dcd.dcid="+dcid+" order by dcd.szcode asc";
		ps=this.findPage(sql.toString(), pageSize, startIndex, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				if(map.get("byisuser")!=null){
					String hsql=" from Sysparv5 sys where sys.id.pmky='YHMX' and sys.id.pmcd='"+map.get("byisuser")+"' ";
					Sysparv5 sys1=(Sysparv5)this.find(hsql).get(0);
					map.put("pmva", sys1.getPmva());
				}
			}
		}
		return ps;
	}

	/**
	 * Describe:����Ż݄���ϸ��Ϣ
	 * 
	 * @auth:shenzhixiong
	 * @param list
	 * @param syslog
	 *            return void Date:2014-4-1
	 */
	public void insertDiscountcoupondetail(List<Discountcoupondetail> list,
			Syslog syslog) {

		for (int i = 0; i < list.size(); i++) {
			Long maxId = this.getMaxPk("dcdid", "Discountcoupondetail");
			list.get(i).setDcdid(maxId + 1);
			this.save(list.get(i));
		}
//		syslog.setStlg("0278");
//		syslog.setBrief("����Ż݄���ϸ��Ϣ��" + list.get(list.size() - 1).getDcdid());
//		syslog.setNote("����Ż݄���ϸ��Ϣ��" + list.get(list.size() - 1).getDcdid());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		

	}

	/**
	 * Describe:�޸��Ż݄���ϸ��Ϣ
	 * 
	 * @auth:shenzhixiong
	 * @param discountcoupondetail
	 * @param syslog
	 *            return void Date:2014-4-1
	 */

	public void updateDiscountcoupondetail(
			Discountcoupondetail discountcoupondetail, Syslog syslog) {

		Discountcoupondetail dcd = (Discountcoupondetail) this.get(
				Discountcoupondetail.class, discountcoupondetail.getDcdid());
		if (discountcoupondetail.getUsid() != null
				&& !discountcoupondetail.getUsid().equals("")) {
			dcd.setUsid(discountcoupondetail.getUsid());
			this.update(dcd);
		} else if (discountcoupondetail.getByisuser() != null
				&& !discountcoupondetail.getByisuser().equals("")) {
			dcd.setByisuser(discountcoupondetail.getByisuser());
			this.update(dcd);
		} else if (discountcoupondetail.getDcdusedate() != null
				&& !discountcoupondetail.getDcdusedate().equals("")) {
			dcd.setDcdusedate(discountcoupondetail.getDcdusedate());
			this.update(dcd);
		}

		syslog.setStlg("");
		syslog.setBrief("�Ż݄���ϸ��" + dcd.getDcdid());
		syslog.setNote("�޸��Ż݄���ϸ��" + dcd.getDcdid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	/**
	 * Describe������״̬�õ��ͱ���õ���ʹ�õ����� 
	 * auth:shenzhiiong
	 * @param szcode
	 * return Integer; Date:2014-4-4
	 */

	public int findDiscountcoupondetailBySzcodeAndByisuser(Long dcid,
			boolean b) {
		StringBuffer sql = new StringBuffer(
				"from Discountcoupondetail dcd where dcd.dcid="+dcid);

		if (b) {
			sql.append("and dcd.byisuser not in (0)");
		}

		List list = this.find(sql.toString());
		return list.size();
	}

	/**
	 * Describe: �����Żݾ����õ��Żݾ���ϸ auth:shenzhixiong
	 * 
	 * @param szcode
	 *            return discountcoupondetail Date:2014-4-4
	 * @throws Exception
	 */

	public Discountcoupondetail findDiscountcoupondetailBySzcode(String szcode)
			throws Exception {
		String sql = "from Discountcoupondetail dcd where dcd.szcode like '"
				+ szcode + "%'";
		List<Discountcoupondetail> list = this.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			return list.get(list.size() - 1);
		}
	}

}
