package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Jgfz;
import com.ectrip.ticket.provider.dao.IJgfzDAO;

@Repository
public class JgfzDAO extends GenericDao implements IJgfzDAO{
	@Autowired
	private SysService sysService;
	/**
	 * *
	 * Describe:显示出服务商价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#checkUpJgfz(java.lang.Long, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param iscenicid
	 * @param flag
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-4-14
	 */
	public PaginationSupport checkUpJgfz(Long iscenicid,String userIds,int pageSize,int startIndex,String url){
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(gz.seq as seq,gz.iscenicid as iscenicid,esb.szscenicname as szscenicname,gz.pmcd as pmcd,gz.pmky as pmky,gz.usid as usid,gz.byisuse as byisuse) from Jgfz gz,Esbscenicareatab esb where gz.iscenicid=esb.iscenicid");
		if(iscenicid!=null&&iscenicid!=0){
			hsql.append(" and gz.iscenicid = "+iscenicid);
		}
		if(StringUtil.isNotEmpty(userIds)){
			hsql.append(" and gz.usid in "+userIds);
		}
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:新增价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#addJgfz(com.ectrip.model.provider.Jgfz, com.ectrip.model.syspar.Syslog)
	 * @param jgfz
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void addJgfz(Jgfz jgfz,Syslog syslog){
		Long maxid=this.getMaxPk("seq", "Jgfz");
		jgfz.setSeq(maxid+1L);
		jgfz.setPmky("JSFZ");
		this.save(jgfz);
		
		syslog.setStlg("0275");
		syslog.setBrief("服务商价格分组：" + jgfz.getSeq());
		syslog.setNote("服务商价格分组：" + jgfz.getSeq()+" 服务商："+jgfz.getIscenicid()+" 用户："+jgfz.getUsid()+" 价格分组："+jgfz.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sysService.saveSysLog(syslog);
		
	}
	
	/**
	 * *
	 * Describe:模糊查询用户
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#checkCustominfo(java.lang.String, int, int, java.lang.String)
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-4-16
	 */
	public PaginationSupport checkCustominfo(String query,int pageSize,int startIndex,String url){
		StringBuffer hsql = new StringBuffer("select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) from  Custom c where c.lgtp = '02' and c.ttlb='01' and c.ustp='01' ");
		if(query!=null&&!query.equals("")){
			hsql.append(" and c.corpname like '%"+query+"%'");
		}
		hsql.append(" order by corpname,status, ldate desc, c.usid ");
		
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:修改价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#editJgfz(com.ectrip.model.provider.Jgfz, com.ectrip.model.syspar.Syslog)
	 * @param jgfz
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void editJgfz(Jgfz jgfz,Syslog syslog){
		Jgfz fz=(Jgfz)this.get(Jgfz.class, jgfz.getSeq());
		fz.setPmcd(jgfz.getPmcd());
		fz.setByisuse(jgfz.getByisuse());
		this.update(fz);
		
		syslog.setStlg("0276");
		syslog.setBrief("服务商价格分组：" + jgfz.getSeq());
		syslog.setNote("服务商价格分组：" + jgfz.getSeq()+" 服务商："+jgfz.getIscenicid()+" 用户："+jgfz.getUsid()+" 价格分组："+fz.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sysService.saveSysLog(syslog);
	}
	
	/**
	 * *
	 * Describe:删除价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#delJgfz(java.lang.Long, com.ectrip.model.syspar.Syslog)
	 * @param seq
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void delJgfz(Long seq,Syslog syslog){
		Jgfz jgfz=(Jgfz)this.get(Jgfz.class, seq);
		
		syslog.setStlg("0277");
		syslog.setBrief("服务商价格分组：" + jgfz.getSeq());
		syslog.setNote("服务商价格分组：" + jgfz.getSeq()+" 服务商："+jgfz.getIscenicid()+" 用户："+jgfz.getUsid()+" 价格分组："+jgfz.getPmcd());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sysService.saveSysLog(syslog);
		
		this.delete(jgfz);
	}
	
	/**
	 * *
	 * Describe:查看价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#viewJgfz(java.lang.Long)
	 * @param seq
	 * @return
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public Jgfz viewJgfz(Long seq) throws Exception {
		String hsql="select new map(gz.seq as seq,gz.iscenicid as iscenicid,esb.szscenicname as szscenicname,gz.pmcd as pmcd,gz.pmky as pmky,sys1.pmva as pmva,gz.usid as usid,ct.corpname as corpname,ct.lname as lname,gz.byisuse as byisuse) from Jgfz gz,Esbscenicareatab esb,Sysparv5 sys1,Custom ct where gz.iscenicid=esb.iscenicid and ct.usid=gz.usid and gz.pmcd=sys1.id.pmcd and gz.pmky=sys1.id.pmky and gz.seq="+seq;
		List lst=this.find(hsql);
		if(lst!=null&&lst.size()>0){
			Jgfz jgfz=new Jgfz();
			BeanUtils.populate(jgfz, (Map)lst.get(0));
			return jgfz;
		}
		return null;
	}
	
}

