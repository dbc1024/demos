package com.ectrip.sys.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.message.dao.idao.IWebinfotabDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Webinfotab;

@Repository
public class WebinfotabDAO extends GenericDao implements IWebinfotabDAO{
	
	/**
	 * *
	 * Describe:显示所有的站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#showListwebinfos(int, int, int, int, java.lang.String)
	 * @param flag
	 * @param employeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public PaginationSupport showListwebinfos(int flag,String employeeid,int pageSize,int startIndex, String url){
		StringBuffer sql=new StringBuffer();
		
		sql.append("select new map("
				+ " wf.seq as seq, wf.iemployeeid as iemployeeid, wf.ihadder as ihadder, wf.szmemo as szmemo, wf.reminddate as reminddate,"
				+ " esf.szemployeename as szemployeename,"
				+ " sp.szemployeename as szempname"
				+ " ) "
				+ " from Webinfotab wf, Esfemployeetab esf, Esfemployeetab sp "
				+ " where wf.iemployeeid=esf.iemployeeid and wf.ihadder=sp.iemployeeid ");
		
		if(employeeid!=null&&!employeeid.equals("")){
			if(flag==0){
				sql.append(" and wf.ihadder="+employeeid);
			}else if(flag==1){
				sql.append(" and wf.iemployeeid="+employeeid);
			}
		}
		
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#addWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public void addWebinfotab(Webinfotab webinfo,Syslog syslog){
		Long maxid=this.getMaxPk("seq", "Webinfotab");
		webinfo.setSeq(maxid+1);
		webinfo.setReminddate(Tools.getDayTimes());
		this.save(webinfo);
		
//		syslog.setStlg("0262");
//		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, webinfo.getIemployeeid());
//		Esfemployeetab emp=(Esfemployeetab) this.get(Esfemployeetab.class, webinfo.getIhadder());
//		syslog.setBrief("站内短信公告：接收人："+emp.getSzemployeename()+"  发送人："+employee.getSzemployeename() );
//		syslog.setNote("添加站内短信公告：发送信息"+webinfo.getSzmemo() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:修改站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#updateWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public void updateWebinfotab(Webinfotab webinfo,Syslog syslog){
		Webinfotab info=(Webinfotab) this.get(Webinfotab.class, webinfo.getSeq());
		info.setIemployeeid(webinfo.getIemployeeid());
		info.setIhadder(webinfo.getIhadder());
		info.setSzmemo(webinfo.getSzmemo());
		this.update(info);
		
//		syslog.setStlg("0263");
//		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, webinfo.getIemployeeid());
//		Esfemployeetab emp=(Esfemployeetab) this.get(Esfemployeetab.class, webinfo.getIhadder());
//		syslog.setBrief("站内短信公告：接收人："+emp.getSzemployeename()+"  发送人："+employee.getSzemployeename() );
//		syslog.setNote("修改站内短信公告：发送信息"+webinfo.getSzmemo() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:删除站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#delWebinfotab(com.ectrip.sys.model.syspar.model.permitenter.Webinfotab, com.ectrip.model.syspar.Syslog)
	 * @param webinfo
	 * @param syslog
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public void delWebinfotab(Webinfotab webinfo,Syslog syslog){
		Webinfotab info=(Webinfotab) this.get(Webinfotab.class, webinfo.getSeq());
		
//		syslog.setStlg("0264");
//		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, info.getIemployeeid());
//		Esfemployeetab emp=(Esfemployeetab) this.get(Esfemployeetab.class, info.getIhadder());
//		syslog.setBrief("站内短信公告：接收人："+emp.getSzemployeename()+"  发送人："+employee.getSzemployeename() );
//		syslog.setNote("删除站内短信公告：发送信息"+webinfo.getSzmemo() );
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
		this.delete(info);
	}
	
	/**
	 * *
	 * Describe:查看站内短信公告
	 * @see com.ectrip.system.permitenter.service.iservice.IWebinfotabService#viewWebinfotab(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2013-1-19
	 */
	public Webinfotab viewWebinfotab(Long seq) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(wf.seq as seq,wf.iemployeeid as iemployeeid,wf.ihadder as ihadder,wf.szmemo as szmemo,wf.reminddate as reminddate,esf.szemployeename as szemployeename,sp.szemployeename as szempname) from Webinfotab wf,Esfemployeetab esf,Esfemployeetab sp where wf.iemployeeid=esf.iemployeeid and sp.iemployeeid=wf.ihadder and wf.seq="+seq);
		List list = this.find(sql.toString());
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Webinfotab ts=new Webinfotab();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	
}

