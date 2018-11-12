package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.report.system.datereport.dao.idao.ILxsDao;

public class LxsDao extends GenericDao implements ILxsDao {
	/**
	 * 
	 * Describe:��������
	 * 
	 * @author:chenxinhao
	 * @return return:List Date:2013-6-8
	 */
	public List findzLxs() {
		String sql = " from Custom c where c.ustp='01' and c.ttlb='01' and c.lgtp='02' and c.status='01' and c.ibusinessid=2 order by c.corpname";
		return this.find(sql);
	}

	/**
	 * 
	 * Describe:��������ͷ���
	 * 
	 * @author:chenxinhao
	 * @return return:List Date:2013-6-8
	 */
	public List findzfLxs() {
		List list = findzLxs();
		Custom custom = null;
		StringBuffer usids = new StringBuffer();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(c.usid as usid,c.corpname as corpname) from Custom c ");
		hsql.append(" where c.ustp in ('01','02') and c.ttlb = '01' and c.lgtp = '02' and c.status='01'  ");
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				custom = (Custom) list.get(i);
				if (i == list.size() - 1) {
					usids.append("'" + custom.getUsid() + "'");
				} else {
					usids.append("'" + custom.getUsid() + "',");
				}
			}
			hsql.append(" and (c.susid in (" + usids.toString()
					+ ") or c.susid is null ) ");
		}
		hsql.append("  order by c.corpname");
		return this.find(hsql.toString());
	}

	/**
	 * �������ҵ������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:List Date:2011-9-30
	 */
	public List businessother() {
		List list = new ArrayList();
		String hsql = " from Edmbusinesstab where byisuse=1 and ibusinessid not in (0,1)";
		list = this.find(hsql);
		return list;
	}

	public String showcustom(Long ibusinessid) {
		StringBuffer json = new StringBuffer();

		String sql = null;

		json.append("[");
		List<Custom> gates = this
				.find("from Custom c where  c.status='01'  and c.lgtp='02' and ustp='01' and ttlb='01' and c.ibusinessid="
						+ ibusinessid + " and usdj in (0,1) order by corpname");
		if (gates == null || gates.size() < 1) {
			json.append("{\"usid\":\"" + -1 + "\",\"corpname\":\"" + "��"
					+ "\"}");
		} else {
			String rusid = "0000";
			String rcorpname = "�������пͻ�";
			json.append("{\"usid\":\"" + rusid + "\",\"corpname\":\""
					+ rcorpname + "\"},");
			for (Custom custom : gates) {
				json.append("{\"usid\":\"" + custom.getUsid()
						+ "\",\"corpname\":\"" + custom.getCorpname() + "\"}");
				if (gates.iterator().hasNext()) {
					json.append(",");
				}
			}
		}
		json.append("]");
		// System.out.println("json:"+json);
		return json.toString();
	}

	public List findcustombyibusinessid(Long ibusinessid) {
		String sql = " from Custom c where c.status='01' and c.ibusinessid="
				+ ibusinessid + " order by c.corpname";
		return this.find(sql);
	}
	
	
	/**
	 * ��ѯ��Ʊ��ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usid
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport querySaleAllList(String rzti,String ldti,String usids,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(t.usid as usid,cus.corpname as corpname,t.szsalesvoucherno as orid,t.dtmakedate as notec,t.ihandler as isc,em.szemployeename as szemployeename,yl.itickettypeid as itickettypeid ,et.sztickettypename as sztickettypename, kind.szcrowdkindname as szcrowdkindname,yl.mactualsaleprice as pric ,sum(yl.iticketplayer) as numb,sum(yl.meventmoney) as mont )  from Stssalesvouchertab t,Stssalesvoucherdetailstab yl,Custom cus,Edpcrowdkindtab kind,Edmtickettypetab et,Esfemployeetab em,Edmcrowdkindpricetab ep");
		hsql.append(" where substr(t.dtmakedate,1,10)>='"+rzti+"' and substr(t.dtmakedate,1,10)<='"+rzti+"'  and   t.bysalesvouchertype='01' ");
		hsql.append(" and t.usid  in ("+usids+")");
		hsql.append(" and t.id.isalesvoucherid=yl.id.isalesvoucherid and t.id.iticketstationid=yl.id.iticketstationid and t.usid = cus.usid  and kind.icrowdkindid = ep.icrowdkindid and ep.icrowdkindpriceid=yl.icrowdkindpriceid and yl.itickettypeid=et.itickettypeid  and t.ihandler=em.iemployeeid  group by cus.bname,t.usid,cus.corpname,t.szsalesvoucherno,t.dtmakedate,t.ihandler,em.szemployeename,yl.itickettypeid,et.sztickettypename, kind.szcrowdkindname,yl.mactualsaleprice order by t.usid,t.szsalesvoucherno") ;			
		ps = this.findPage(hsql.toString(), pageSize, page, url);	
		return ps;
	}
	
	public List findonezLxs() {
		String sql = " from Custom c where c.ustp='01' and c.ttlb='01' and c.lgtp='02' and c.status='01' and c.ibusinessid=2 and c.usdj in (0,1) order by c.corpname";
		return this.find(sql);
	}
	public List findsonLxs(String usid) {
		String sql = " from Custom c where susid='"+usid+"' order by c.corpname";
		return this.find(sql);
	}
}
