package com.ectrip.ticket.provider.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.IEsbticketWinDAO;

/**
 * @author lizd
 *
 */
@Repository
public class EsbticketWinDAO extends GenericDao implements IEsbticketWinDAO {
	@Autowired
	private SysService sysService;

	/**
	 * * Describe:显示所有的售票窗口信息 根据窗口名称查询
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#getlistTicketWin(java.lang.String,
	 *      int, int, java.lang.String)
	 * @param szticketwinname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui Date:2011-10-4
	 */
	public PaginationSupport getlistTicketWin(String szticketwinname, String scenics, int pageSize, int startIndex,
			String url) {
		PaginationSupport ps = null;
		StringBuffer sql = new StringBuffer();
		// 服务拆分 Sysparv5不在本服务，不能进行连表查询
		/*
		 * "select new map(ew.iticketwinid as iticketwinid,ew.szticketwinname as szticketwinname,es.szstationname as szstationname,"
		 * +
		 * "eb.szscenicname as szscenicname,ew.dtsellstart as dtsellstart,ew.dtsellend as dtsellend,sys1.pmva as pmva,"
		 * +
		 * "ew.byisuse as byisuse,ew.szipaddress as szipaddress ,ew.ipaddress as ipaddress,ew.isty as isty) "
		 * +
		 * "from Esbticketwintab ew,Esbticketstationtab es,Esbscenicareatab eb,Sysparv5 sys1 "
		 * +
		 * "where ew.iticketstationid=es.iticketstationid and ew.iscenicid=eb.iscenicid and sys1.id.pmcd=ew.bywintype and "
		 * + "sys1.id.pmky='SPXH' and eb.isjd=0"
		 */
		sql.append(
				"select new map(ew.iticketwinid as iticketwinid,ew.szticketwinname as szticketwinname,es.szstationname as szstationname,"
						+ "eb.szscenicname as szscenicname,ew.dtsellstart as dtsellstart,ew.dtsellend as dtsellend,ew.bywintype as bywintype,ew.byisuse as byisuse,"
						+ "ew.szipaddress as szipaddress ,ew.ipaddress as ipaddress,ew.isty as isty) from Esbticketwintab ew,Esbticketstationtab es,"
						+ "Esbscenicareatab eb where ew.iticketstationid=es.iticketstationid and ew.iscenicid=eb.iscenicid and "
						+ "eb.isjd=0 ");
		if (szticketwinname != null && !("").equals(szticketwinname)) {
			sql.append(" and ew.szticketwinname like '%" + szticketwinname + "%' ");
		}
		if (scenics != null && !("").equals(scenics)) {
			sql.append(" and eb.iscenicid in(" + scenics + ")");
		}
		sql.append(" order by eb.iscenicid,es.iticketstationid,ew.iticketwinid ");
		ps = this.findPage(sql.toString(), pageSize, startIndex, url);

		// 获取已查询到集合中ew.bywintype的集合
		List<Map> itemsList = ps.getItems();
		StringBuffer sb = new StringBuffer();
		if (itemsList != null && !itemsList.isEmpty()) {
			for (int i = 0; i < itemsList.size(); i++) {
				if (i == 0) {
					sb.append(itemsList.get(i).get("bywintype").toString());
				} else {
					sb.append("," + itemsList.get(i).get("bywintype").toString());
				}
			}
		}
		// 调用sysservice服务获取Sysparv5表信息并添加到结果集
		List<Map> sysparv5List = sysService.getSysparamsByPmkyAndPmcds("SPXH", sb.toString());
		for (Map sysparv5 : sysparv5List) {
			Object pmva = sysparv5.get("pmva");
			Object pmcd = sysparv5.get("pmcd");
			for (Map item : itemsList) {
				if (pmcd.equals(item.get("bywintype"))) {
					item.put("pmva", pmva);
				}
			}
		}
		return ps;
	}

	/**
	 * * Describe:显示所有的售票窗口信息 根据窗口名称查询
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#getlistTicketWin(java.lang.String,
	 *      int, int, java.lang.String)
	 * @param szticketwinname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui Date:2011-10-4
	 */
	public PaginationSupport getlistTicketWinNotscenic(String szticketwinname, String scenics, int pageSize,
			int startIndex, String url) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select new map(ew.iticketwinid as iticketwinid,ew.szticketwinname as szticketwinname,es.szstationname as szstationname,eb.szscenicname as szscenicname,ew.dtsellstart as dtsellstart,ew.dtsellend as dtsellend,sys1.pmva as pmva,ew.byisuse as byisuse,ew.szipaddress as szipaddress ,ew.ipaddress as ipaddress) from Esbticketwintab ew,Esbticketstationtab es,Esbscenicareatab eb,Sysparv5 sys1 where ew.iticketstationid=es.iticketstationid and ew.iscenicid=eb.iscenicid and sys1.id.pmcd=ew.bywintype and sys1.id.pmky='SPXH' and eb.isjd=0 ");
		if (szticketwinname != null && !szticketwinname.equals("")) {
			sql.append(" and ew.szticketwinname like '%" + szticketwinname + "%' ");
		}
		if (scenics != null && !scenics.equals("")) {
			sql.append(" and eb.iscenicid in(" + scenics + ")");
		}
		sql.append(" order by ew.iticketwinid ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * * Describe:添加售票窗口
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#insertTicketWin(com.ectrip.model.provider.Esbticketwintab,
	 *      com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui Date:2011-10-4
	 */
	public void insertTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		Esbticketstationtab ticket = (Esbticketstationtab) this.get(Esbticketstationtab.class,
				ticketwin.getIticketstationid());
		ticketwin.setIscenicid(ticket.getIscenicid());
		Long maxid = this.getMaxPk("iticketwinid", "Esbticketwintab");
		ticketwin.setIticketwinid(maxid + 1);
		ticketwin.setIversion(new Long(57));

		// 陈新浩修改 时间：2012-08-29 增加icid写入
		String sql = " select max(icid) from Esbticketwintab where iscenicid=" + ticketwin.getIscenicid();
		List list = this.find(sql);
		String id = "0";
		if (list != null && list.size() > 0) {
			id = (String) list.get(0);
			if (id == null) {
				id = "0";
			}
		}
		ticketwin.setIcid(Long.toHexString(Long.valueOf(id, 16) + 1L).toUpperCase());
		this.save(ticketwin);

		syslog.setStlg("0063");
		syslog.setBrief("售票窗口：" + ticketwin.getIticketwinid());
		syslog.setNote("添加售票窗口：" + ticketwin.getSzticketwinname());
		sysService.insertSyslog(syslog);
	}

	/**
	 * * Describe:修改售票窗口
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#updateTicketWin(com.ectrip.model.provider.Esbticketwintab,
	 *      com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui Date:2011-10-4
	 */
	public void updateTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		Esbticketwintab esb = (Esbticketwintab) this.get(Esbticketwintab.class, ticketwin.getIticketwinid());
		esb.setSzticketwincode(ticketwin.getSzticketwincode());
		esb.setSzticketwinname(ticketwin.getSzticketwinname());
		esb.setIticketstationid(ticketwin.getIticketstationid());
		Esbticketstationtab ticket = (Esbticketstationtab) this.get(Esbticketstationtab.class,
				esb.getIticketstationid());
		esb.setIscenicid(ticket.getIscenicid());

		esb.setSzipaddress(ticketwin.getSzipaddress());
		esb.setDtsellstart(ticketwin.getDtsellstart());
		esb.setDtsellend(ticketwin.getDtsellend());
		esb.setBywintype(ticketwin.getBywintype());
		esb.setByisuse(ticketwin.getByisuse());
		esb.setSzmemo(ticketwin.getSzmemo());
		esb.setIpaddress(ticketwin.getIpaddress());
		esb.setIsty(ticketwin.getIsty());
		esb.setPayCode(ticketwin.getPayCode());
		this.update(esb);

		syslog.setStlg("0064");
		syslog.setBrief("售票窗口：" + esb.getIticketwinid());
		syslog.setNote("修改售票窗口：" + esb.getSzticketwinname());
		sysService.insertSyslog(syslog);
	}

	/**
	 * * Describe:删除售票窗口
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#deleteTicketWin(com.ectrip.model.provider.Esbticketwintab,
	 *      com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui Date:2011-10-4
	 */
	public void deleteTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		Esbticketwintab esb = (Esbticketwintab) this.get(Esbticketwintab.class, ticketwin.getIticketwinid());
		this.delete(esb);

		syslog.setStlg("0065");
		syslog.setBrief("售票窗口：" + esb.getIticketwinid());
		syslog.setNote("删除售票窗口：" + esb.getSzticketwinname());
		// syslog.setLogdatetime(Tools.getDayTimes());
		// Long logid = getMaxPk("logid", "Syslog");
		// syslog.setLogid(logid + 1);
		// this.save(syslog);
		sysService.insertSyslog(syslog);
	}

	/**
	 * * Describe:根据ID查看售票窗口信息
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#getviewEsbticketWin(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui Date:2011-10-4
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbticketwintab getviewEsbticketWin(Long iticketwinid) throws Exception {
		// Sysparv5在系统服务中 需要拆分
		/*
		 * String
		 * sql="select new map(ew.iticketwinid as iticketwinid,ew.szticketwinname as szticketwinname,"
		 * +
		 * "es.szstationname as szstationname,eb.szscenicname as szscenicname,ew.dtsellstart as dtsellstart,"
		 * +
		 * "ew.dtsellend as dtsellend,sys1.pmva as bywintype,ew.byisuse as byisuse,ew.szmemo as szmemo,"
		 * +
		 * "ew.szipaddress as szipaddress,ew.ipaddress as ipaddress,ew.szticketwincode as szticketwincode,"
		 * +
		 * "ew.icid as icid) from Esbticketwintab ew,Esbticketstationtab es,Esbscenicareatab eb,Sysparv5 sys1 "
		 * +
		 * "where ew.iticketstationid=es.iticketstationid and ew.iscenicid=eb.iscenicid and sys1.id.pmcd=ew.bywintype "
		 * + "and sys1.id.pmky='SPXH' and ew.iticketwinid="+iticketwinid;
		 */
		String sql = "select new map(ew.iticketwinid as iticketwinid,ew.szticketwinname as szticketwinname,ew.bywintype as bywintype,es.szstationname as szstationname,eb.szscenicname as szscenicname,ew.dtsellstart as dtsellstart,ew.dtsellend as dtsellend,ew.byisuse as byisuse,ew.szmemo as szmemo,ew.szipaddress as szipaddress,ew.ipaddress as ipaddress,ew.szticketwincode as szticketwincode,ew.icid as icid) from Esbticketwintab ew,Esbticketstationtab es,Esbscenicareatab eb where ew.iticketstationid=es.iticketstationid and ew.iscenicid=eb.iscenicid and  ew.iticketwinid="
				+ iticketwinid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbticketwintab ts = new Esbticketwintab();
			Map esbticketwintabMap = (Map) list.get(0);
			String bywintype = (String) esbticketwintabMap.get("bywintype");
			// 调用系统服务获取Sysparv5信息并设置
			List<Map> sysparv5List = sysService.getSysparamsByPmkyAndPmcds("SPXH", bywintype);
			Map map = sysparv5List.get(0);
			bywintype = (String) map.get("pmva");
			esbticketwintabMap.put("bywintype", bywintype);
			BeanUtils.populate(ts, esbticketwintabMap);
			return ts;
		}
	}

	/**
	 * * Describe:显示管理的服务商的售票站点信息
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#reviters()
	 * @return
	 * @author lijingrui Date:2011-10-4
	 */
	public List findListesbticket(String scenics) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select new map(ew.iticketwinid as iticketwinid,ew.iticketstationid as iticketstationid,ew.iscenicid as iscenicid,ew.szticketwincode as szticketwincode,ew.szticketwinname as szticketwinname,ew.szipaddress as szipaddress,ew.dtsellstart as dtsellstart,ew.dtsellend as dtsellend,ew.bywintype as bywintype,ew.byisuse as byisuse,ew.szmemo as szmemo,ew.iversion as iversion,ew.ipaddress as ipaddress,ew.icid as icid,ew.isty as isty,ew.payCode as payCode) from Esbticketwintab ew");
		if (scenics != null && !scenics.equals("")) {
			sql.append(" where ew.iscenicid in(" + scenics + ")");
		}
		return this.find(sql.toString());
	}

	/**
	 * * Describe:
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#getiiticketID(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui Date:2011-10-5
	 */
	public Esbticketwintab getszIpassword(String szipaddress) {
		String sql = " from Esbticketwintab where szipaddress=" + szipaddress;
		return (Esbticketwintab) this.find(sql).get(0);
	}

	/**
	 * * Describe:判断此售票窗口下是否有窗口设置
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getlistEsbticketequied(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui Date:2011-10-14
	 */
	public boolean getlistEsbticketequied(Long iticketwinid) {
		boolean b = false;
		String sql = " from Esbticketequiptab where iticketwinid=" + iticketwinid;
		List list = this.find(sql);
		if (list != null && list.size() > 0) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	/**
	 * 查看有效的窗口 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param employeeId后台登录人id
	 * @return return:List Date:2011-10-6
	 */
	public List getAllTicketWinList(Long employeeId) {

		List list = new ArrayList();
		List winlist = new ArrayList();
		String sql = " from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
				+ employeeId + ")";
		String hsql = "";
		List companylist = this.find(sql);
		if (companylist.size() >= 1) {
			Galcompanyinfotab company = (Galcompanyinfotab) companylist.get(0);
			if ("01".equals(company.getCompanytype())) {// 平台管理
				hsql = " from Esbticketwintab where byisuse=1";
			}
			if ("02".equals(company.getCompanytype())) {// 景区企业
				hsql = " from Esbticketwintab where byisuse=1 and iscenicid in(select iscenicid from Companyscenic where id.icompanyinfoid="
						+ company.getIcompanyinfoid() + ")";
			}
		}

		list = this.find(hsql);
		if (list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Esbticketwintab ticketwin = new Esbticketwintab();
				ticketwin = (Esbticketwintab) list.get(i);
				// 站点
				Esbticketstationtab station = (Esbticketstationtab) this.get(Esbticketstationtab.class,
						ticketwin.getIticketstationid());
				if (station != null) {
					ticketwin.setSzticketwinname(station.getSzstationname() + "_" + ticketwin.getSzticketwinname());
				}
				winlist.add(ticketwin);
			}
		}

		return winlist;
	}

	/**
	 * 获取销售权限列表 根据窗口Id查询权限
	 */
	public PaginationSupport getWinPermissionByIticketWinId(Long iticketWinId, int page, int pageSize) {
		PaginationSupport ps = null;
		String hsql = " select new map(lim.iticketwinlimitsid as iticketwinlimitsid,lim.iticketwinid as iticketwinid,lim.icrowdkindpriceid as icrowdkindpriceid,win.szticketwinname as strtwinname) from Ospticketwinlimitstab lim ,Edmcrowdkindpricetab pri,Esbticketwintab win where lim.iticketwinid=win.iticketwinid and lim.icrowdkindpriceid = pri.icrowdkindpriceid and lim.iticketwinid= "
				+ iticketWinId;
		ps = this.findPage(hsql, pageSize, page, null);
		List list = ps.getItems();
		if (list != null && list.size() > 0) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iticketwinid") != null) {// 窗口id
					String actcontent = map.get("iticketwinid").toString();// 窗口id
					String strwinname = map.get("strtwinname").toString();// 窗口名称
					// 窗口信息
					Esbticketwintab ticketwin = (Esbticketwintab) this.get(Esbticketwintab.class,
							Long.parseLong(actcontent));
					if (ticketwin != null) {
						// 根据窗口id得到站点信息
						Esbticketstationtab station = (Esbticketstationtab) this.get(Esbticketstationtab.class,
								ticketwin.getIticketstationid());
						if (station != null) {
							map.put("strticketwin", station.getSzstationname() + "_" + strwinname);
						}
					} else {
						map.put("strticketwin", strwinname);// 窗口名称
					}
				}
				if (map.get("icrowdkindpriceid") != null) {// 售价编号
					String actcontent = map.get("icrowdkindpriceid").toString();
					// 拆分服务获取系统参数信息
					List<Map> sysparamsList1 = sysService.getSysparamsByParms("PDTP", "01", null, "01");
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < sysparamsList1.size(); j++) {
							sb.append(sysparamsList1.get(j).get("pmcd"));
							if(j!=sysparamsList1.size()-1) {
								sb.append(",");
							}
//						if(j==(sysparamsList1.size()-1)) {
//							sb.append(sysparamsList1.get(j).get("pmcd"));
//						}else {
//							sb.append(","+sysparamsList1.get(j).get("pmcd"));
//						}
					}
					List<Map> sysparamsList2 = sysService.getSysparamsByParms("PRTP",null , sb.toString(), null);
					StringBuffer sb1 = new StringBuffer();
					for (int j = 0; j < sysparamsList2.size(); j++) {
							sb1.append("'"+sysparamsList2.get(j).get("pmcd")+"'");
							if(j!=(sysparamsList2.size()-1)) {
							sb1.append(",");
							}
					}
					
//					服务拆分
//					String sql="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in( select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+actcontent ;
					 

					String hsqls = "select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,"
							+ "pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, "
							+ "Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid "
							+ " and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in("+sb1.toString()+")  and pri.icrowdkindpriceid=" + actcontent;
					 List strlist = this.find(hsqls);
					if (strlist.size() >= 1) {
						for (int j = 0; j < strlist.size(); j++) {
							Object[] a = (Object[]) strlist.get(j);
							map.put("strcrowdkindprice", a[1] + "_" + a[2] + "_" + a[3] + "_" + a[4] + "_" + a[5]);// 价格组合名称
						}
					}
				}
			}
		}
		return ps;
	}

	/**
	 * 新增窗口售票权限
	 */
	public void addWinPermission(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog) {
		if(!("").equals(Icrowdkindpriceids)&& null!=Icrowdkindpriceids) {
			//获取价格数组
			String[] zprno = Icrowdkindpriceids.split(",");
			List winList = new ArrayList();
			//把每一个价格id封装成一个权限类
			for (int i = 0; i < zprno.length; i++) {
				Ospticketwinlimitstab win = new Ospticketwinlimitstab();
				win.setIticketwinid(Iticketwinid);
				win.setIcrowdkindpriceid(Long.parseLong(zprno[i]));
				winList.add(win);
			}
			for (int i = 0; i < winList.size(); i++) {
				Ospticketwinlimitstab winlimitstab = (Ospticketwinlimitstab)winList.get(i);
				long maxsale = this.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
				winlimitstab.setIticketwinlimitsid(maxsale+1);	
				String hsql=" from Ospticketwinlimitstab where icrowdkindpriceid="+winlimitstab.getIcrowdkindpriceid()+" and iticketwinid="+winlimitstab.getIticketwinid();
				List winlist = this.find(hsql);
				if(winlist!=null && winlist.size()>=1){
					continue;//存在则跳过不处理
				}else{
					this.save(winlimitstab);//保存
					syslog.setIemployeeid(syslog.getIemployeeid());
					syslog.setStlg("0070");
					syslog.setBrief("窗口销售权限：" + winlimitstab.getIticketwinlimitsid() );
					syslog.setNote("窗口销售权限增加：" +  winlimitstab.getIticketwinlimitsid() +",价格编号："+winlimitstab.getIcrowdkindpriceid()+",窗口编号："+winlimitstab.getIticketwinid());
					sysService.insertSyslog(syslog);
				}
			}
		}
	}
	/**
	 * 修改窗口售票权限
	 */
	public void updateTicketWinLimits(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog) {
		String[] zprno=null;
		if(!("").equals(Icrowdkindpriceids)&& null!=Icrowdkindpriceids) {
			//获取窗口售票权限的价格数组
			zprno = Icrowdkindpriceids.split(",");
		}
		//根据窗口id获取权限集合
		String sql=" from Ospticketwinlimitstab where iticketwinid="+Iticketwinid;
		List winlist = this.find(sql);
		//先删除窗口权限
		if(winlist.size()>=1){
			Ospticketwinlimitstab winlimit=null;
			for (int i = 0; i < winlist.size(); i++) {
				winlimit =(Ospticketwinlimitstab) winlist.get(i);
				this.delete(winlimit);
			}
		}
		List winList = new ArrayList();
		if(zprno!=null&&zprno.length>0) {
			for (int i = 0; i < zprno.length; i++) {
				Ospticketwinlimitstab win = new Ospticketwinlimitstab();
				//保存售票窗口id
				win.setIticketwinid(Iticketwinid);
				//保存价格数组id
				win.setIcrowdkindpriceid(Long.parseLong(zprno[i]));
				//把每一个窗口权限都添加到list中
				winList.add(win);
			}
			//再保存窗口权限
			for (int i = 0; i < winList.size(); i++) {
				Ospticketwinlimitstab winlimitstab = (Ospticketwinlimitstab)winList.get(i);
				long maxsale = this.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
				winlimitstab.setIticketwinlimitsid(maxsale+1);			
				this.save(winlimitstab);//保存
				syslog.setStlg("0071");
				syslog.setBrief("窗口销售权限：" + winlimitstab.getIticketwinlimitsid() );
				syslog.setNote("窗口销售权限修改：" +  winlimitstab.getIticketwinlimitsid() +",价格编号："+winlimitstab.getIcrowdkindpriceid()+",窗口编号："+winlimitstab.getIticketwinid());
				sysService.insertSyslog(syslog);
			}
		}
	}

	/**
	 *删除窗口权限
	 */
	public void deleteTicketWinLimits(Long iticketwinlimitsId, Syslog syslog) {
		if(!("").equals(iticketwinlimitsId)&&null!=iticketwinlimitsId) {
			//根据主键获取权限
			Ospticketwinlimitstab ticketwinlimit= (Ospticketwinlimitstab)this.get(Ospticketwinlimitstab.class, iticketwinlimitsId);
			syslog.setStlg("0072");
			syslog.setBrief("窗口销售权限：" + ticketwinlimit.getIticketwinlimitsid() );
			syslog.setNote("窗口销售权限删除：" +  ticketwinlimit.getIticketwinlimitsid() );
			sysService.insertSyslog(syslog);
			//删除权限
			this.delete(ticketwinlimit);
		}
	}

}
