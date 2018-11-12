package com.ectrip.sys.syspar.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.syspar.dao.ISyslogDao;


@Repository
public class SyslogDAO  extends GenericDao implements ISyslogDao{
	
	/**
	 * *
	 * Describe:
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlistsyslog(com.ectrip.model.syspar.Syslog, int, int, java.lang.String)
	 * @param syslog
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 * 
	 * 修改chenxinhao	Date:2012-08-07
	 */
	public PaginationSupport getlistsyslog(Long iemployeeid,String stlg,String date,int flag,int pageSize, int startIndex,String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(logs.logid as logid,emp.szemployeename as szemployeename,logs.stlg as stlg,logs.brief as brief,logs.note as note,logs.logdatetime as logdatetime,sys1.pmva as pmva)  from Syslog logs,Esfemployeetab emp,Sysparv5 sys1 where logs.iemployeeid=emp.iemployeeid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG'" );
		if(flag==0){
			sql.append(" and emp.iemployeeid="+iemployeeid);
		}
		if(flag==1){
			sql.append(" and logs.stlg='"+stlg+"'" );
		}
		sql.append(" and substr(logs.logdatetime,1,10)='"+date+"'");
		sql.append(" order by logs.logdatetime desc");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:显示前台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlistcustomlog(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, int, int, java.lang.String)
	 * @param usid
	 * @param stlg
	 * @param start
	 * @param end
	 * @param radio1
	 * @param radio2
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 * 
	 * 修改chenxinhao	Date:2012-08-07
	 */
	public PaginationSupport getlistcustomlog(String usid,String date,int flag,int pageSize, int startIndex,String url){
		StringBuffer hql=new StringBuffer();
		hql.append("select new map(logs.sysid as sysid,logs.usid as usid,logs.stlg as stlg,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as pmva) from Customlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' " );
		if(flag==0){
			hql.append(" and logs.usid='"+usid+"'" );
		}
		hql.append(" and substr(logs.logdatetime,1,10)='"+date+"'");
		hql.append(" order by logs.logdatetime desc");
		return this.findPage(hql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:显示订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlistorderlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, int, java.lang.String)
	 * @param stlg
	 * @param start
	 * @param end
	 * @param radio1
	 * @param radio2
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 * 
	 * 修改chenxinhao	Date:2012-08-07
	 */
	public PaginationSupport getlistorderlog(String usid,String rzti,String ldti,int flag,int pageSize, int startIndex,String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(logs.logid as logid,logs.stlg as stlg,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as pmva,ct.usid) from Orderlog logs,Sysparv5 sys1,Custom ct where ct.usid=logs.usid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG'" );
		if(flag==1&&usid!=null){
			sql.append(" and logs.usid='"+usid+"'" );
		}
		sql.append(" and substr(logs.logdatetime,1,10) between '"+rzti+"' and '"+ldti+"' " );
		sql.append(" order by logs.logdatetime desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:添加后台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertSyslog(com.ectrip.model.syspar.Syslog)
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public void insertSyslog(Syslog syslog) {
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:添加前台用户操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertTomlog(com.ectrip.model.syspar.Customlog)
	 * @param customlog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public void insertTomlog(Customlog customlog) {
		customlog.setLogdatetime(Tools.getDayTimes());
		Long sysid=getMaxPk("sysid", "Customlog");
		customlog.setSysid(sysid+1);
		this.save(customlog);
	}
	
	/**
	 * *
	 * Describe:添加订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#insertOrderlog(com.ectrip.sys.model.syspar.model.syspar.Orderlog)
	 * @param orderlog
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public void insertOrderlog(Orderlog orderlog) {
		orderlog.setLogdatetime(Tools.getDayTimes());
		Long logid=getMaxPk("logid", "Orderlog");
		orderlog.setLogid(logid+1);
		this.save(orderlog);
	}
	
	/**
	 * *
	 * Describe:显示所有的用户
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowemployee()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public List<Esfemployeetab> getAllshowemployee() {
		String sql=" from Esfemployeetab";
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:显示所有的操作功能
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowsysparv5()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 */
	public List<Sysparv5> getAllshowsysparv5() {
		String sql=" from Sysparv5 where id.pmky='STLG' and systp=2 and isvalue = 1";
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:显示所有的前台用户
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getAllshowscustom()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public List<Custom> getAllshowscustom(){
		String sql=" from Custom order by usid";
		return this.find(sql);
	}
	/**
	 * *
	 * Describe:
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlogids(java.lang.Long)
	 * @param logid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-6
	 * @throws Exception 
	 */
	public Syslog getlogids(Long logid) throws Exception{
		String sql="select new map(logs.logid as logid,emp.szemployeename as szemployeename,logs.brief as brief,logs.note as note,logs.logdatetime as logdatetime,sys1.pmva as stlg)  from Syslog logs,Esfemployeetab emp,Sysparv5 sys1 where logs.iemployeeid=emp.iemployeeid and logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and logs.logid="+logid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Syslog ts=new Syslog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:查看前台操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getsysidview(java.lang.Long)
	 * @param sysid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public Customlog getsysidview(Long sysid) throws Exception{
		String sql="select new map(logs.sysid as sysid,logs.usid as usid,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as stlg) from Customlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and logs.sysid="+sysid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Customlog ts=new Customlog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:查看订单操作日志
	 * @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getlogidorderview(java.lang.Long)
	 * @param logid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-7
	 */
	public Orderlog getlogidorderview(Long logid) throws Exception{
		Orderlog log=(Orderlog) this.get(Orderlog.class,logid);
		String sql="";
		if(log.getLogtype()==0){
			sql="select new map(logs.logid as logid,logs.orid as orid,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as stlg,cm.lname as auname) from Orderlog logs,Sysparv5 sys1,Custom cm where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and cm.usid=logs.usid and logs.logid="+logid;
		}else{
			sql="select new map(logs.logid as logid,logs.orid as orid,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,sys1.pmva as stlg,emp.szemployeename as szemployeename) from Orderlog logs,Sysparv5 sys1,Esfemployeetab emp where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' and emp.iemployeeid=logs.iemployeeid and logs.logid="+logid;
		}
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Orderlog ts=new Orderlog();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
/**
 * (非 Javadoc) 
* <p>Title: getListOrderLogByType</p> 
* <p>Description:查询订单操作日志 type 0 前台  1 后台 </p> 
* @param type
* @param usid
* @param start
* @param end
* @param pageSize
* @param startIndex
* @param url
* @return 
* @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getListOrderLogByType(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
 */
	public PaginationSupport getListOrderLogByType(String type, String usid,String orid,
			 int pageSize, int startIndex, String url,String groupId) {
		StringBuffer sql=new StringBuffer();
		StringBuffer where = new StringBuffer();
		sql.append("select new map(logs.logid as logid,logs.stlg as stlg,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,s.pmva as pmva,com.usid) from Orderlog logs,Sysparv5 s,Custom com " );
		/*if(groupId !=null && groupId.length()>0){
			sql.append(" ,Galcompanyinfotab gal,Companyscenic c,Esbscenicareatab provider,MOrder mor,TOrder tor ");
		}*/
		where.append(" where com.usid=logs.usid and logs.stlg=s.id.pmcd and s.id.pmky='STLG' ");
		/*if(groupId !=null && groupId.length()>0){
    		where.append(" and c.id.icompanyinfoid = gal.icompanyinfoid and c.id.iscenicid = provider.iscenicid and gal.icompanyinfoid = '"+groupId+"' and s.id.pmcd=mor.ddzt and tor.id.iscenicid=provider.iscenicid and tor.id.orid=mor.orid "); 
    	}*/
		where.append(" and logs.usid='"+usid+"'" );
		if(orid!=null){
			where.append(" and logs.orid='"+orid+"'");
		}
		where.append(" order by logs.logdatetime desc");
		System.out.println("==>"+sql.toString() + where.toString());
		return this.findPage(sql.toString() + where.toString(), pageSize, startIndex, url);
		 
	}
/**
 * (非 Javadoc) 
* <p>Title: getListCustomLog</p> 
* <p>Description: 前台用户操作日志- 用户中心</p> 
* @param usid
* @param pageSize
* @param startIndex
* @param url
* @return 
* @see com.ectrip.system.syspar.dao.idao.ISyslogDao#getListCustomLog(java.lang.String, int, int, java.lang.String)
 */
public PaginationSupport getListCustomLog(String usid, int pageSize,
		int startIndex, String url) {
	StringBuffer hql=new StringBuffer();
	hql.append("select new map(logs.sysid as sysid,logs.usid as usid,logs.stlg as stlg,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as pmva) from Customlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' " );
	if(usid  != null){
		hql.append(" and logs.usid='"+usid+"'" );
	}
	hql.append(" order by logs.logdatetime desc");
	 
	return this.findPage(hql.toString(), pageSize, startIndex, url);
}
	/**
	 * 
	 * Describe:前台用户历史操作日志- 用户中心
	 * @author:chenxinhao
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-11
	 */
	public PaginationSupport getListHcustomLog(String usid,int pageSize,int startIndex, String url){
		StringBuffer hql=new StringBuffer();
		hql.append("select new map(logs.sysid as sysid,logs.usid as usid,logs.stlg as stlg,logs.logdatetime as logdatetime,logs.brief as brief,logs.note as note,sys1.pmva as pmva) from Hcustomlog logs,Sysparv5 sys1 where logs.stlg=sys1.id.pmcd and sys1.id.pmky='STLG' " );
		if(usid  != null){
			hql.append(" and logs.usid='"+usid+"'" );
		}
		hql.append(" order by logs.logdatetime desc");
		 
		return this.findPage(hql.toString(), pageSize, startIndex, url);
	}
	/**
	 * 
	 * Describe:前台查询订单历史操作日志 
	 * @author:chenxinhao
	 * @param type
	 * @param usid
	 * @param orid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-11
	 */
	public PaginationSupport getListhOrderLogByType(String usid,String orid,int pageSize, int startIndex, String url,String groupId) {
		StringBuffer sql=new StringBuffer();
		StringBuffer where = new StringBuffer();
		sql.append("select new map(logs.logid as logid,logs.stlg as stlg,logs.brief as brief,logs.logtype as logtype,logs.usid as usid,logs.iemployeeid as iemployeeid,logs.logdatetime as logdatetime,logs.note as note,s.pmva as pmva,com.usid) from Horderlog logs,Sysparv5 s,Custom com " );
		/*if(groupId !=null && groupId.length()>0){
			sql.append(" ,Galcompanyinfotab gal,Companyscenic c,Esbscenicareatab provider,MOrder mor,TOrder tor ");
		}*/
		where.append(" where com.usid=logs.usid and logs.stlg=s.id.pmcd and s.id.pmky='STLG' ");
		/*if(groupId !=null && groupId.length()>0){
    		where.append(" and c.id.icompanyinfoid = gal.icompanyinfoid and c.id.iscenicid = provider.iscenicid and gal.icompanyinfoid = '"+groupId+"' and s.id.pmcd=mor.ddzt and tor.id.iscenicid=provider.iscenicid and tor.id.orid=mor.orid "); 
    	}*/
		where.append(" and logs.usid='"+usid+"'" );
		if(orid!=null){
			where.append(" and logs.orid='"+orid+"'");
		}
		where.append(" order by logs.logdatetime desc");
		return this.findPage(sql.toString()+where.toString(), pageSize, startIndex, url);
	}
}

