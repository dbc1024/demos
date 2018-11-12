package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Ticketvenues;
import com.ectrip.ticket.permitenter.dao.ITicketvenuesDAO;

public class TicketvenuesDAO extends GenericDao implements ITicketvenuesDAO{

	/**
	 * *
	 * Describe:��ѯ��ͷƱ������
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#searchListTicket(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pmcd
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public PaginationSupport searchListTicket(String scenics,String pmcd,int pageSize,int startIndex, String url){
		PaginationSupport ps=null;
		String hsql=" select new map(tv.seq as seq,tv.pmcd as pmcd,sys1.pmva as pmva,tv.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename) from Ticketvenues tv,Sysparv5 sys1,Edmtickettypetab edm where sys1.id.pmky='PWKS' and sys1.id.pmcd=tv.pmcd and edm.itickettypeid=tv.itickettypeid ";
		if(scenics!=null&&!scenics.equals("")){
			hsql+=" and edm.iscenicid in ('"+scenics+"')";
		}
		if(pmcd!=null&&!pmcd.equals("")){
			hsql+=" and tv.pmcd='"+pmcd+"'";
		}
		ps=this.findPage(hsql, pageSize, startIndex, url);
		
		return ps;
	} 
	
	/**
	 * *
	 * Describe:����
	 * @see com.ectrip.system.permitenter.dao.idao.ITicketvenuesDAO#insertTicketvenues(java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param ticketList
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void insertTicketvenues(List ticketList,Syslog syslog){
		
		Ticketvenues ticket = (Ticketvenues)ticketList.get(0);
		
		String sql=" from Ticketvenues where pmcd='"+ticket.getPmcd()+"'";
		List salelist = this.find(sql);
		if(salelist.size()>=1){
			Ticketvenues venue=null;
			for (int i = 0; i < salelist.size(); i++) {
				venue =(Ticketvenues) salelist.get(i);
				this.delete(venue);
			}
		}
		
		for (int i = 0; i < ticketList.size(); i++) {
			Ticketvenues tv = (Ticketvenues)ticketList.get(i);
			
			long maxsale = this.getMaxPk("seq", "Ticketvenues");
			tv.setSeq(maxsale+1);	
			tv.setDtmakedate(Tools.getDayTimes());
			this.save(tv);
			
			syslog.setStlg("0402");
			syslog.setBrief("��ͷƱ��" + tv.getSeq());
			syslog.setNote("��ͷƱ�����ӣ�" +  tv.getSeq() +",��ͷ��"+tv.getPmcd()+",Ʊ���ţ�"+tv.getItickettypeid());
			syslog.setLogdatetime(Tools.getDayTimes());
			long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
			
		}
		
	}
	
	/**
	 * *
	 * Describe:�޸�
	 * @see com.ectrip.system.permitenter.dao.idao.ITicketvenuesDAO#updateTicketvenues(java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param ticketList
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void updateTicketvenues(List ticketList,Syslog syslog){
		Ticketvenues ticket = (Ticketvenues)ticketList.get(0);
		
		String sql=" from Ticketvenues where pmcd='"+ticket.getPmcd()+"'";
		List salelist = this.find(sql);
		if(salelist.size()>=1){
			Ticketvenues venue=null;
			for (int i = 0; i < salelist.size(); i++) {
				venue =(Ticketvenues) salelist.get(i);
				this.delete(venue);
			}
		}
		
		for (int i = 0; i < ticketList.size(); i++) {
			Ticketvenues tv = (Ticketvenues)ticketList.get(i);
			
			long maxsale = this.getMaxPk("seq", "Ticketvenues");
			tv.setSeq(maxsale+1);	
			tv.setDtmakedate(Tools.getDayTimes());
			this.save(tv);
			
			syslog.setStlg("0403");
			syslog.setBrief("��ͷƱ��" + tv.getSeq());
			syslog.setNote("��ͷƱ���޸ģ�" +  tv.getSeq() +",��ͷ��"+tv.getPmcd()+",Ʊ���ţ�"+tv.getItickettypeid());
			syslog.setLogdatetime(Tools.getDayTimes());
			long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}
		
	}
	
	/**
	 * *
	 * Describe:ɾ��
	 * @see com.ectrip.system.permitenter.dao.idao.ITicketvenuesDAO#delTicketvenues(java.lang.Long, com.ectrip.model.syspar.Syslog)
	 * @param seq
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-7-25
	 */
	public void delTicketvenues(Long seq,Syslog syslog){
		Ticketvenues tv=(Ticketvenues)this.get(Ticketvenues.class, seq);
		
		syslog.setStlg("0404");
		syslog.setBrief("��ͷƱ��" + tv.getSeq() );
		syslog.setNote("��ͷƱ��" +  tv.getSeq() +",��ͷ��"+tv.getPmcd()+",Ʊ���ţ�"+tv.getItickettypeid());
		syslog.setLogdatetime(Tools.getDayTimes());
		long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(tv);
	}
	
	/**
	 * *
	 * Describe:��ѯ���Ѵ��ڵ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#checkListTicket(java.lang.String)
	 * @param pmcd
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public List checkListTicket(String pmcd){
		String sql="  from Ticketvenues tv where tv.pmcd='"+pmcd+"' ";
		List lst=this.find(sql);
		return lst;
	}
	
	/**
	 * *
	 * Describe:��ȡ���е�Ʊ����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.ITicketvenuesService#checkListedmTicket(java.lang.String, java.lang.String)
	 * @param scenicids
	 * @param scentype
	 * @return
	 * @author lijingrui
	 * Date:2014-7-24
	 */
	public List checkListedmTicket(String scenicids,String scentype){
		StringBuffer sql = new StringBuffer();
		sql.append(" from Edmtickettypetab edm where edm.byisuse=1 and edm.iscenicid in( select esb.iscenicid from Esbscenicareatab esb where esb.scenictype='01' )");
		if (scenicids != null && !"".equals(scenicids)) {
			sql.append(" and edm.iscenicid in ('"+scenicids+"') ");
		}
		List list = this.find(sql.toString());
		
		return list;
	}
	
}

