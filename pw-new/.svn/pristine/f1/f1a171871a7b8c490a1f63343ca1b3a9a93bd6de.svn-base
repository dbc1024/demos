package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.salesmanager.dao.ITicketWinLimitsDAO;

public class TicketWinLimitsDAO extends GenericDao implements
		ITicketWinLimitsDAO {
	/**
	 * 查询所有窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-6
	 */
	public PaginationSupport getTicketWinLimitsList(int page,int pageSize,String url){
		PaginationSupport ps =null;
		String hsql=" select new map(lim.iticketwinlimitsid as iticketwinlimitsid,lim.iticketwinid as iticketwinid,lim.icrowdkindpriceid as icrowdkindpriceid,win.szticketwinname as strtwinname) from Ospticketwinlimitstab lim ,Edmcrowdkindpricetab pri,Esbticketwintab win where lim.iticketwinid=win.iticketwinid and lim.icrowdkindpriceid = pri.icrowdkindpriceid ";
		ps = this.findPage(hsql, pageSize, page, url);
		List list = ps.getItems();

		if (list != null && list.size() > 0) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(map.get("iticketwinid")!=null){//窗口id
					String actcontent = map.get("iticketwinid").toString();//窗口id
					String strwinname=map.get("strtwinname").toString();//窗口名称
					//窗口信息
					Esbticketwintab ticketwin = (Esbticketwintab)this.get(Esbticketwintab.class,Long.parseLong(actcontent));
					if(ticketwin!=null){
						//根据窗口id得到站点信息
						Esbticketstationtab station =(Esbticketstationtab)this.get(Esbticketstationtab.class,ticketwin.getIticketstationid());
						if(station!=null){
							map.put("strticketwin", station.getSzstationname()+"_"+strwinname);
						}
					}else{
						map.put("strticketwin", strwinname);//窗口名称
					}



				}
				if( map.get("icrowdkindpriceid")!=null){//售价编号
					String actcontent = map.get("icrowdkindpriceid").toString();

					String hsqls="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+actcontent ;
					List strlist = this.find(hsqls);
					if(strlist.size()>=1){
						for (int j = 0; j< strlist.size(); j++) {
							Object[] a =(Object[]) strlist.get(j);
							map.put("strcrowdkindprice", a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//价格组合名称
						}
					}
				}
			}
		}


		return ps;
	}
	/**
	 * 查询所有窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-6
	 */
	public PaginationSupport getTicketWinLimitsList(Long winid,int page,int pageSize,String url){
		PaginationSupport ps =null;
		String hsql=" select new map(lim.iticketwinlimitsid as iticketwinlimitsid,lim.iticketwinid as iticketwinid,lim.icrowdkindpriceid as icrowdkindpriceid,win.szticketwinname as strtwinname) from Ospticketwinlimitstab lim ,Edmcrowdkindpricetab pri,Esbticketwintab win where lim.iticketwinid=win.iticketwinid and lim.icrowdkindpriceid = pri.icrowdkindpriceid and lim.iticketwinid= "+winid;
		ps = this.findPage(hsql, pageSize, page, url);
		List list = ps.getItems();

		if (list != null && list.size() > 0) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(map.get("iticketwinid")!=null){//窗口id
					String actcontent = map.get("iticketwinid").toString();//窗口id
					String strwinname=map.get("strtwinname").toString();//窗口名称
					//窗口信息
					Esbticketwintab ticketwin = (Esbticketwintab)this.get(Esbticketwintab.class,Long.parseLong(actcontent));
					if(ticketwin!=null){
						//根据窗口id得到站点信息
						Esbticketstationtab station =(Esbticketstationtab)this.get(Esbticketstationtab.class,ticketwin.getIticketstationid());
						if(station!=null){
							map.put("strticketwin", station.getSzstationname()+"_"+strwinname);
						}
					}else{
						map.put("strticketwin", strwinname);//窗口名称
					}



				}
				if( map.get("icrowdkindpriceid")!=null){//售价编号
					String actcontent = map.get("icrowdkindpriceid").toString();

					String hsqls="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+actcontent ;
					List strlist = this.find(hsqls);
					if(strlist.size()>=1){
						for (int j = 0; j< strlist.size(); j++) {
							Object[] a =(Object[]) strlist.get(j);
							map.put("strcrowdkindprice", a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//价格组合名称
						}
					}
				}
			}
		}


		return ps;
	}
	/**
	 * 根据编号查询窗口销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public Ospticketwinlimitstab queryTicketWinLimits(Long ticketwinId){
		Ospticketwinlimitstab ticketwinlimits = null;
		ticketwinlimits = (Ospticketwinlimitstab)this.get(Ospticketwinlimitstab.class, ticketwinId);
		if(ticketwinlimits!=null){
			//窗口
			Esbticketwintab ticketwin = (Esbticketwintab)this.get(Esbticketwintab.class, ticketwinlimits.getIticketwinid());
			if(ticketwin!=null){
				//站点
				Esbticketstationtab station =(Esbticketstationtab) this.get(Esbticketstationtab.class, ticketwin.getIticketstationid());
				if(station!=null){
					ticketwinlimits.setStrticketwin(station.getSzstationname()+"_"+ticketwin.getSzticketwinname());//窗口组合名称
				}else{
					ticketwinlimits.setStrticketwin(ticketwin.getSzticketwinname());//窗口名称
				}

			}
			String hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
					"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
					" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+ticketwinlimits.getIcrowdkindpriceid() ;

			List list = this.find(hsql);
			if(list.size()>=1){
				for (int i = 0; i < list.size(); i++) {
					Object[] a =(Object[]) list.get(i);
					ticketwinlimits.setStrcrowdkindprice(a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//价格组合名称
				}
			}
		}
		return ticketwinlimits;

	}
	/**
	 * 根据窗口编号查询窗口销售权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinId窗口编号
	 * @return
	 * return:Ospticketwinlimitstab
	 * Date:2011-10-6
	 */
	public List queryTicketWinLimitsList(Long ticketwinId){
		List list = new ArrayList();
		String sql=" from Ospticketwinlimitstab where iticketwinid="+ticketwinId;
		list = this.find(sql);

		return list;
	}

	/**
	 * 窗口销售权限增加
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void insertTicketWinLimits(List ticketwinlimitslist,Syslog syslog){


		for (int i = 0; i < ticketwinlimitslist.size(); i++) {

			Ospticketwinlimitstab winlimitstab = (Ospticketwinlimitstab)ticketwinlimitslist.get(i);

			long maxsale = this.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
			winlimitstab.setIticketwinlimitsid(maxsale+1);

			String hsql=" from Ospticketwinlimitstab where icrowdkindpriceid="+winlimitstab.getIcrowdkindpriceid()+" and iticketwinid="+winlimitstab.getIticketwinid();
			List winlist = this.find(hsql);
			if(winlist!=null && winlist.size()>=1){
				continue;
			}else{
				this.save(winlimitstab);//保存

				Syslog syslogs = new Syslog();
				syslogs.setIemployeeid(syslog.getIemployeeid());
				syslogs.setStlg("0070");
				syslogs.setBrief("窗口销售权限：" + winlimitstab.getIticketwinlimitsid() );
				syslogs.setNote("窗口销售权限增加：" +  winlimitstab.getIticketwinlimitsid() +",价格编号："+winlimitstab.getIcrowdkindpriceid()+",窗口编号："+winlimitstab.getIticketwinid());
				syslogs.setLogdatetime(Tools.getDayTimes());
				Long logid = this.getMaxPk("logid", "Syslog");
				syslogs.setLogid(logid + 1);
				this.save(syslogs);
			}


		}
	}
	/**
	 * 窗口销售权限修改
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void updateTicketWinLimits(List ticketwinlimitslist,Syslog syslog){
		Ospticketwinlimitstab win = (Ospticketwinlimitstab)ticketwinlimitslist.get(0);

		String sql=" from Ospticketwinlimitstab where iticketwinid="+win.getIticketwinid();
		List winlist = this.find(sql);
		if(winlist.size()>=1){
			Ospticketwinlimitstab winlimit=null;
			for (int i = 0; i < winlist.size(); i++) {
				winlimit =(Ospticketwinlimitstab) winlist.get(i);
				this.delete(winlimit);
			}
		}

		for (int i = 0; i < ticketwinlimitslist.size(); i++) {

			Ospticketwinlimitstab winlimitstab = (Ospticketwinlimitstab)ticketwinlimitslist.get(i);

			long maxsale = this.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
			winlimitstab.setIticketwinlimitsid(maxsale+1);
			this.save(winlimitstab);//保存

			Syslog syslogs = new Syslog();
			syslogs.setIemployeeid(syslog.getIemployeeid());
			syslogs.setStlg("0071");
			syslogs.setBrief("窗口销售权限：" + winlimitstab.getIticketwinlimitsid() );
			syslogs.setNote("窗口销售权限修改：" +  winlimitstab.getIticketwinlimitsid() +",价格编号："+winlimitstab.getIcrowdkindpriceid()+",窗口编号："+winlimitstab.getIticketwinid());
			syslogs.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslogs.setLogid(logid + 1);
			this.save(syslogs);
		}
	}
	/**
	 * 窗口销售权限删除
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketwinlimitsId
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	public void deleteTicketWinLimits(Long ticketwinlimitsId,Syslog syslog){
		Ospticketwinlimitstab ticketwinlimits = (Ospticketwinlimitstab)this.get(Ospticketwinlimitstab.class, ticketwinlimitsId);
		syslog.setStlg("0072");
		syslog.setBrief("窗口销售权限：" + ticketwinlimits.getIticketwinlimitsid() );
		syslog.setNote("窗口销售权限删除：" +  ticketwinlimits.getIticketwinlimitsid() );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		this.delete(ticketwinlimits);
	}
}

