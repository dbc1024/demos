package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esplxqytab;
import com.ectrip.ticket.provider.dao.IEsplxqytabDAO;
@Repository
public class EsplxqytabDAO extends GenericDao implements IEsplxqytabDAO {

	public void addEsplxqytab(Esplxqytab esplxqytab, Syslog syslog) {
		Long maxid = this.getMaxPk("seq", "Esplxqytab");

		esplxqytab.setSeq(maxid + 1L);
		esplxqytab.setPmky("LXQY");
		esplxqytab.setBycatetype(1L);
		this.save(esplxqytab);

		syslog.setStlg("0279");
		syslog.setBrief("旅行社区域：" + esplxqytab.getSeq());
		syslog.setNote("旅行社区域：" + esplxqytab.getSeq() + " 服务商："
				+ esplxqytab.getIscenicid() + " 用户：" + esplxqytab.getUsid()
				+ " 旅行社区域：" + esplxqytab.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	public void deleteEsplxqytab(Long seq, Syslog syslog) {
		Esplxqytab esplxqytab = (Esplxqytab) this.get(Esplxqytab.class, seq);
		this.delete(esplxqytab);
	
		syslog.setStlg("0280");
		syslog.setBrief("旅行社区域：" + esplxqytab.getSeq());
		syslog.setNote("旅行社区域：" + esplxqytab.getSeq() + " 服务商："
				+ esplxqytab.getIscenicid() + " 用户：" + esplxqytab.getUsid()
				+ " 旅行社区域：" + esplxqytab.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	public Esplxqytab getEsplxqytabfindBySeq(Long seq) throws Exception{
		String hsql="select new map(et.seq as seq,et.iscenicid as iscenicid,esb.szscenicname as szscenicname,et.pmcd as pmcd,et.pmky as pmky,sys1.pmva as pmva,et.usid as usid,ct.corpname as corpname,ct.lname as lname,et.bycatetype as bycatetype) from Esplxqytab et,Esbscenicareatab esb,Sysparv5 sys1,Custom ct where et.iscenicid=esb.iscenicid and ct.usid=et.usid and et.pmcd=sys1.id.pmcd and et.pmky=sys1.id.pmky and et.seq="+seq;
		List list=this.find(hsql);
		if(list!=null&&list.size()>0){
			Esplxqytab esplxqyta = new Esplxqytab();
			BeanUtils.populate(esplxqyta, (Map)list.get(0));
			return esplxqyta;
		}
		return null;
	}

	public PaginationSupport showEsplxqytabList(Long iscenicid,String flag,String query, int pageSize,
			int startIndex, String url) {
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(et.seq as seq,et.iscenicid as iscenicid,esb.szscenicname as szscenicname,et.pmcd as pmcd,et.pmky as pmky,sys1.pmva as pmva,et.usid as usid,ct.corpname as corpname,ct.lname as lname,et.bycatetype as bycatetype) from Esplxqytab et,Esbscenicareatab esb,Sysparv5 sys1,Custom ct where et.iscenicid=esb.iscenicid and ct.usid=et.usid and et.pmcd=sys1.id.pmcd and et.pmky=sys1.id.pmky");
		if(iscenicid!=null&&iscenicid!=0){
			hsql.append(" and et.iscenicid="+iscenicid);
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("0")){
				if(query!=null&&!query.equals("")){
					hsql.append(" and ct.corpname like '%"+query+"%'");
				}
			}else if(flag.equals("1")){
				if(query!=null&&!query.equals("")){
					hsql.append(" and sys1.pmva like '%"+query+"%'");
				}
			}
		}
		
		
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	public void updateEsplxqytab(Esplxqytab esplxqytab, Syslog syslog) {
		
		Esplxqytab et = (Esplxqytab) this.get(Esplxqytab.class,esplxqytab.getSeq());
		et.setPmcd(esplxqytab.getPmcd());
		this.update(et);
	
		syslog.setStlg("0281");
		syslog.setBrief("旅行社区域：" + esplxqytab.getSeq());
		syslog.setNote("旅行社区域：" + esplxqytab.getSeq() + " 服务商："
				+ esplxqytab.getIscenicid() + " 用户：" + esplxqytab.getUsid()
				+ " 旅行社区域：" + esplxqytab.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
}
