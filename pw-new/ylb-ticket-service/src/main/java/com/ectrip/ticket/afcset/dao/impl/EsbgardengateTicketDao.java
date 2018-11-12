package com.ectrip.ticket.afcset.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ticket.afcset.dao.IEsbgardengateTicketDao;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbgardengatetickettab;
@Repository
public class EsbgardengateTicketDao extends GenericDao implements IEsbgardengateTicketDao{

	@Autowired
	private SysService sysService;
	
	/**
	 * 园门同检票分页列表
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#showGardenGateTicketViewList(java.lang.Long, int, int, java.lang.String)
	 * @param igardengateid
	 * @param pageSize
	 * @param page
	 * @param url
	 * @return
	 * @author luoxin
	 * Date:2015-8-28
	 */
	public PaginationSupport showGardenGateTicketViewList(Long igardengateid,
														  int pageSize, int page, String url) {
		// TODO Auto-generated method stub
		String hsql = "select new map(ggtt.seq as seq, ggtt.igardengateid as igardengateid, ggtt.itickettypeoneid as itickettypeoneid, ggtt.itickettypetwoid as itickettypetwoid, ggtt.dtmakedate as dtmakedate" +
				" , gardengate.szgardengatename as szgardengatename, ticketone.sztickettypename as sztickettypeonename, tickettwo.sztickettypename as sztickettypetwoname) " +
				" from Esbgardengatetickettab ggtt, Esbgardengatetab gardengate, Edmtickettypetab ticketone, Edmtickettypetab tickettwo " +
				" where ggtt.igardengateid="+igardengateid+" and ggtt.igardengateid=gardengate.id.igardengateid and ggtt.itickettypeoneid=ticketone.itickettypeid and ggtt.itickettypetwoid=tickettwo.itickettypeid " +
				" order by ggtt.seq ";
		return this.findPage(hsql, pageSize, page, url);
	}

	/**
	 * 获取产品
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#queryTicket(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, int, int, java.lang.String)
	 * @param iscenicid
	 * @param bycategorytype
	 * @param byusage
	 * @param sztickettypename
	 * @param pageSize
	 * @param page
	 * @param url
	 * @return
	 * @author luoxin
	 * Date:2015-8-28
	 */
	public PaginationSupport queryTicket(Long iscenicid, String bycategorytype, Long byusage, String sztickettypename, int pageSize, int page, String url) {
		StringBuffer hsql = new StringBuffer();
		
//		hsql.append("select new map("
//				+ " ticket.itickettypeid as itickettypeid, ticket.sztickettypename as sztickettypename, ticket.bycategorytype as bycategorytype, ticket.byusage as byusage, ticket.byisuse as byisuse,"
//				+ " sys.pmva as strbycategorytype"
//				+ " )"
//				+ " from Edmtickettypetab ticket, Sysparv5 sys"
//				+ " where sys.id.pmcd=ticket.bycategorytype and sys.id.pmky='PRTP' and ticket.iscenicid="+ iscenicid);
		
		hsql.append("select new map("
				+ " ticket.itickettypeid as itickettypeid, ticket.sztickettypename as sztickettypename, ticket.bycategorytype as bycategorytype, ticket.byusage as byusage, ticket.byisuse as byisuse"
				+ " )"
				+ " from Edmtickettypetab ticket"
				+ " where ticket.iscenicid="+ iscenicid);
		
		
		if(null!=sztickettypename && !"".equals(sztickettypename) && null!=sztickettypename.trim() && !"".equals(sztickettypename.trim())){
			hsql.append(" and ticket.sztickettypename like '%"+sztickettypename+"%'");
		}
		
		if(null!=bycategorytype && !"".equals(bycategorytype)){
			hsql.append(" and ticket.bycategorytype='"+bycategorytype+"' ");
		}
		
		hsql.append(" order by itickettypeid ");
		
		PaginationSupport ps = this.findPage(hsql.toString(), pageSize, page, url);
		List<Map> items = ps.getItems();
		
		StringBuilder bycategorytypeS = new StringBuilder();
		if(items!=null && items.size()>0) {
			for (Map item : items) {
				String ticket_bycategorytype = item.get("bycategorytype").toString();
				bycategorytypeS.append(ticket_bycategorytype+ ",");
			}
			bycategorytypeS.deleteCharAt(bycategorytypeS.length() - 1);
			
			List<Map> sysparams_bycategorytypeS = sysService.getSysparamsByPmkyAndPmcds("PRTP", bycategorytypeS.toString());
			for (Map item : items) {
				String ticket_bycategorytype = item.get("bycategorytype").toString();
				for (Map sysparam : sysparams_bycategorytypeS) {
					String pmcd = sysparam.get("pmcd").toString();
					if(ticket_bycategorytype.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
            			item.put("strbycategorytype", pmva);
					}
				}
			}
		}
		
		return ps;
	}

	/**
	 * 保存园门同检票
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#saveGardenGateTicket(com.ectrip.model.afcset.Esbgardengatetickettab)
	 * @param gardengateticket
	 * @author luoxin
	 * Date:2015-8-28
	 */
	public void saveGardenGateTicket(Esbgardengatetickettab gardengateticket) {
		// TODO Auto-generated method stub
		gardengateticket.setDtmakedate(Tools.getDayTimes());
		Long maxid = this.getMaxPk("seq", "Esbgardengatetickettab");
		gardengateticket.setSeq(maxid+1L);
		this.save(gardengateticket);
	}

	/**
	 * 搜索同检产品
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#searchGardengateTicket(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param igardengateid
	 * @param itickettypeoneid
	 * @param itickettypetwoid
	 * @return
	 * @author luoxin
	 * Date:2015-8-28
	 */
	public List searchGardengateTicket(Long seq, Long igardengateid,
									   Long itickettypeoneid, Long itickettypetwoid) {
		// TODO Auto-generated method stub
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(ggtt.seq as seq, ggtt.igardengateid as igardengateid, ggtt.itickettypeoneid as itickettypeoneid, ggtt.itickettypetwoid as itickettypetwoid) from Esbgardengatetickettab ggtt where ggtt.igardengateid="+igardengateid);
		hsql.append(" and ((ggtt.itickettypeoneid="+itickettypeoneid+" and ggtt.itickettypetwoid="+itickettypetwoid+") or (ggtt.itickettypeoneid="+itickettypetwoid+" and ggtt.itickettypetwoid="+itickettypeoneid+"))");
		if(null!=seq && !"".equals(seq)){
			hsql.append(" and ggtt.seq!="+seq);
		}
		System.out.println(hsql.toString());
		return this.find(hsql.toString());
	}

	/**
	 * 修改园门同检票
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#updateGardenGateTicket(com.ectrip.model.afcset.Esbgardengatetickettab)
	 * @param gardengateticket
	 * @author luoxin
	 * Date:2015-8-28
	 */
	public void updateGardenGateTicket(Esbgardengatetickettab gardengateticket) {
		// TODO Auto-generated method stub
		this.update(gardengateticket);
	}

	/**
	 * 获取园门同检票
	 * Describe:
	 * @see com.ectrip.system.afcset.dao.idao.IEsbgardengateTicketDao#getGardenGateTicket(java.lang.Long)
	 * @param seq
	 * @return
	 * @author luoxin
	 * Date:2015-8-28
	 * @throws Exception
	 */
	public Esbgardengatetickettab getGardenGateTicket(Long seq) throws Exception {
		// TODO Auto-generated method stub
		Esbgardengatetickettab gardengateticket = new Esbgardengatetickettab();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(ggtt.seq as seq, ggtt.igardengateid as igardengateid, ggtt.itickettypeoneid as itickettypeoneid, ggtt.itickettypetwoid as itickettypetwoid, ggtt.dtmakedate as dtmakedate");
		hsql.append(" , t1.sztickettypename as sztickettypeonename, t2.sztickettypename as sztickettypetwoname, scen.iscenicid as iscenicid, scen.szscenicname as szscenicname, ggt.szgardengatename as szgardengatename)");
		hsql.append(" from Esbgardengatetickettab ggtt, Edmtickettypetab t1, Edmtickettypetab t2, Esbgardengatetab ggt, Esbscenicareatab scen");
		hsql.append(" where ggtt.seq = "+seq+" and ggtt.itickettypeoneid=t1.itickettypeid and ggtt.itickettypetwoid=t2.itickettypeid and t1.iscenicid=scen.iscenicid and ggt.id.iscenicid=t1.iscenicid and ggt.id.igardengateid=ggtt.igardengateid");
		List ls = this.find(hsql.toString());
		if(null!=ls && !ls.isEmpty()){
			BeanUtils.populate(gardengateticket,(Map) ls.get(0));
		}
		return gardengateticket;
	}

}

