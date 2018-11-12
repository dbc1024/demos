package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Esbrefundrules;
import com.ectrip.ticket.permitenter.dao.IEsbrefundrulesDAO;

public class EsbrefundrulesDAO extends GenericDao implements IEsbrefundrulesDAO{
	
	/**
	 * *
	 * Describe:��ȡ���з�������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#checkListRefundrule(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public PaginationSupport checkListRefundrule(String scenics,int pageSize,int startIndex, String url){
		StringBuffer hsql=new StringBuffer("select new map(rs.seq as seq,rs.iscenicid as iscenicid,rs.ibusinessid as ibusinessid,esb.szscenicname as szscenicname,rs.tptype as tptype,rs.tptj as tptj,rs.tdfl as tdfl,rs.byisuse as byisuse,rs.szmemo as szmemo) from Esbrefundrules rs,Esbscenicareatab esb where esb.iscenicid=rs.iscenicid ");
		if(scenics!=null&&!scenics.equals("")){
			hsql.append(" and rs.iscenicid in ("+scenics+") ");
		}
		
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:���� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#insertRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void insertRefundrule(Esbrefundrules refundrule,Syslog syslog){
		Long maxid=this.getMaxPk("seq", "Esbrefundrules");
		refundrule.setSeq(maxid+1);
		
		this.save(refundrule);
		
		syslog.setStlg("0520");
		syslog.setBrief("��������Ʊ����" + refundrule.getSeq());
		syslog.setNote("��ӷ�������Ʊ����" + refundrule.getSeq()+"  �����̣�"+refundrule.getIscenicid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	
	/**
	 * *
	 * Describe:�޸� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#updateRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void updateRefundrule(Esbrefundrules refundrule,Syslog syslog){
		Esbrefundrules t=(Esbrefundrules) this.get(Esbrefundrules.class, refundrule.getSeq());
		t.setIscenicid(refundrule.getIscenicid());
		t.setIbusinessid(refundrule.getIbusinessid());
		t.setTptype(refundrule.getTptype());
		t.setTptj(refundrule.getTptj());
		t.setTdfl(refundrule.getTdfl());
		t.setByisuse(refundrule.getByisuse());
		t.setSzmemo(refundrule.getSzmemo());
		this.update(t);
		
		syslog.setStlg("0521");
		syslog.setBrief("��������Ʊ����" + refundrule.getSeq());
		syslog.setNote("�޸ķ�������Ʊ����" + refundrule.getSeq());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:ɾ�� ��������Ʊ����
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbrefundrulesService#delRefundrule(com.ectrip.model.permitenter.Esbrefundrules, com.ectrip.model.syspar.Syslog)
	 * @param refundrule
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public void delRefundrule(Esbrefundrules refundrule,Syslog syslog){
		Esbrefundrules t=(Esbrefundrules) this.get(Esbrefundrules.class, refundrule.getSeq());
		
		syslog.setStlg("0522");
		syslog.setBrief("��������Ʊ����" + refundrule.getSeq());
		syslog.setNote("ɾ����������Ʊ����" + refundrule.getSeq());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(t);
	}
	
	/**
	 * *
	 * Describe:�鿴 ��������Ʊ����
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbrefundrulesDAO#viewRefundrule(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-3-17
	 */
	public Esbrefundrules viewRefundrule(Long seq)throws Exception{
		StringBuffer hsql=new StringBuffer("select new map(rs.seq as seq,rs.iscenicid as iscenicid,rs.ibusinessid as ibusinessid,esb.szscenicname as szscenicname,rs.tptype as tptype,rs.tptj as tptj,rs.tdfl as tdfl,rs.byisuse as byisuse,rs.szmemo as szmemo) from Esbrefundrules rs,Esbscenicareatab esb where esb.iscenicid=rs.iscenicid and rs.seq="+seq);
		List list = super.find(hsql.toString());
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbrefundrules ts=new Esbrefundrules();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
		
	}

}

