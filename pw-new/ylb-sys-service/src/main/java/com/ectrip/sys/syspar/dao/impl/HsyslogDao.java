package com.ectrip.sys.syspar.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Hcustomlog;
import com.ectrip.sys.model.syspar.Horderlog;
import com.ectrip.sys.model.syspar.Hsyslog;
import com.ectrip.sys.syspar.dao.IHsyslogDao;

public class HsyslogDao extends GenericDao implements IHsyslogDao {
	/**
	 * *
	 * Describe:显示后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#showlisthsyslog(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param stlg
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthsyslog(Long iemployeeid, String stlg,String rzti,String ldti,int flag, int pageSize, int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(logs.logid as logid,emp.szemployeename as szemployeename,logs.stlg as stlg,logs.brief as brief,logs.note as note,logs.logdatetime as logdatetime,sys1.pmva as pmva)  from Hsyslog logs,Esfemployeetab emp,Sysparv5 sys1 where logs.iemployeeid=emp.iemployeeid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG'" );
		if(flag==0){
			sql.append(" and emp.iemployeeid="+iemployeeid);
		}
		if(flag==1){
			sql.append(" and logs.stlg='"+stlg+"'" );
		}
		sql.append(" and substr(logs.logdatetime,1,10) between '"+rzti+"' and '"+ldti+"' " );
		sql.append(" order by logs.logdatetime desc ");
		System.out.println(sql.toString());
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#getlogids(java.lang.Long)
	 * @param logid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Hsyslog getlogids(Long logid) throws Exception {
		String sql="select new map(logs.logid as logid,emp.szemployeename as szemployeename,logs.brief as brief,logs.note as note,logs.logdatetime as logdatetime,sys1.pmva as stlg)  from Hsyslog logs,Esfemployeetab emp,Sysparv5 sys1 where logs.iemployeeid=emp.iemployeeid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and logs.logid="+logid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Hsyslog ts=new Hsyslog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	/**
	 * *
	 * Describe:显示前台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#showlisthcustomlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthcustomlog(String usid, String rzti,String ldti, int flag, int pageSize, int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(logs.sysid as sysid,logs.usid as usid,logs.stlg as stlg,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as pmva) from Hcustomlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' " );
		if(flag==0&&usid!=null){
			sql.append(" and logs.usid='"+usid+"'" );
		}
		sql.append(" and substr(logs.logdatetime,1,10) between '"+rzti+"' and '"+ldti+"' " );
		sql.append(" order by logs.logdatetime desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看前台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#getsysidview(java.lang.Long)
	 * @param sysid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Hcustomlog getsysidview(Long sysid) throws Exception{
		String sql="select new map(logs.sysid as sysid,logs.usid as usid,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as stlg) from Hcustomlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and logs.sysid="+sysid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Hcustomlog ts=new Hcustomlog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	/**
	 * *
	 * Describe:显示订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#showlisthorderlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthorderlog(String usid, String rzti,String ldti, int flag, int pageSize, int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(logs.logid as logid,logs.stlg as stlg,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as pmva,ct.usid) from Horderlog logs,Sysparv5 sys1,Custom ct where ct.usid=logs.usid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG'" );
		if(flag==1&&usid!=null){
			sql.append(" and logs.usid='"+usid+"'" );
		}
		sql.append(" and substr(logs.logdatetime,1,10) between '"+rzti+"' and '"+ldti+"' " );
		sql.append(" order by logs.logdatetime desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.IHsyslogDao#getlogidhorderview(java.lang.Long)
	 * @param logid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Horderlog getlogidhorderview(Long logid) throws Exception {
		Horderlog log=(Horderlog) this.get(Horderlog.class,logid);
		String sql="";
		if(log.getLogtype()==0){
			sql="select new map(logs.logid as logid,logs.orid as orid,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as stlg,cm.lname as auname) from Horderlog logs,Sysparv5 sys1,Custom cm where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and cm.usid=logs.usid and logs.logid="+logid;
		}else{
			sql="select new map(logs.logid as logid,logs.orid as orid,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as stlg,emp.szemployeename as szemployeename) from Horderlog logs,Sysparv5 sys1,Esfemployeetab emp where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and emp.iemployeeid=logs.iemployeeid and logs.logid="+logid;
		}
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Horderlog ts=new Horderlog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}

}

