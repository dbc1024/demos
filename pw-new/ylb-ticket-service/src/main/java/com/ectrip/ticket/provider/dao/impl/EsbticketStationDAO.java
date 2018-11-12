package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.provider.dao.IEsbticketStationDAO;

@Repository
public class EsbticketStationDAO extends GenericDao implements IEsbticketStationDAO {
	
	@Autowired
	private SysService sysService;
	/**
	 * 
	 * Describe:显示所有的门票站点信息 并根据名称查询
	 * 
	 * @auth:lijingrui
	 * @param szstationname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return return:PaginationSupport Date:2011-9-29
	 */
	public PaginationSupport getlistEsbticket(String szstationname,
			String scenics, int pageSize, int startIndex, String url) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select new map(et.iticketstationid as iticketstationid,et.szstationname as szstationname,es.szscenicname as szscenicname,et.szcontact as szcontact,et.szphone as szphone,et.byisuse as byisuse,et.szaddress as szaddress,et.szmemo as szmemo) from Esbticketstationtab et,Esbscenicareatab es where es.iscenicid=et.iscenicid and es.isjd=0 ");
		if (szstationname != null && !szstationname.equals("")) {
			sql.append(" and et.szstationname like '%" + szstationname + "%' ");
		}
		if (scenics != null && !scenics.equals("")) {
			sql.append(" and es.iscenicid in(" + scenics + ")");
		}
		sql.append(" order by et.iticketstationid ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * 
	 * Describe:添加门票站点
	 * 
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 *          return:void Date:2011-9-29
	 */
	public void insertEsbticket(Esbticketstationtab esbticket, Syslog syslog) {
		Long maxid = this.getMaxPk("iticketstationid", "Esbticketstationtab");
		esbticket.setIticketstationid(maxid + 1);
		esbticket.setIversion(new Long(57));
		this.save(esbticket);

		syslog.setStlg("0047");
		syslog.setBrief("售票点：" + esbticket.getIticketstationid());
		syslog.setNote("添加售票点：" + esbticket.getSzstationname());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		sysService.insertSyslog(syslog);
	}

	/**
	 * 
	 * Describe:修改门票站点
	 * 
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 *          return:void Date:2011-9-29
	 */
	public void updateEsbticket(Esbticketstationtab esbticket, Syslog syslog) {
		Esbticketstationtab ticket = (Esbticketstationtab) this.get(
				Esbticketstationtab.class, esbticket.getIticketstationid());
		ticket.setSzstationcode(esbticket.getSzstationcode());
		ticket.setSzstationname(esbticket.getSzstationname());
		ticket.setIscenicid(esbticket.getIscenicid());
		ticket.setSzcontact(esbticket.getSzcontact());
		ticket.setSzphone(esbticket.getSzphone());
		ticket.setSzaddress(esbticket.getSzaddress());
		ticket.setByisuse(esbticket.getByisuse());
		ticket.setSzmemo(esbticket.getSzmemo());
		this.update(ticket);

		syslog.setStlg("0048");
		syslog.setBrief("售票点：" + ticket.getIticketstationid());
		syslog.setNote("修改售票点：" + ticket.getSzstationname());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = sysService.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		sysService.insertSyslog(syslog);
	}

	/**
	 * 
	 * Describe:删除门票站点
	 * 
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 *          return:void Date:2011-9-29
	 */
	public void deleteEsbticket(Esbticketstationtab esbticket, Syslog syslog) {
		Esbticketstationtab ticket = (Esbticketstationtab) this.get(
				Esbticketstationtab.class, esbticket.getIticketstationid());
		this.delete(ticket);

		syslog.setStlg("0049");
		syslog.setBrief("售票点：" + esbticket.getIticketstationid());
		syslog.setNote("删除售票点：" + ticket.getSzstationname());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		sysService.insertSyslog(syslog);
	}

	/**
	 * 
	 * Describe:根据ID查看门票站点
	 * 
	 * @auth:lijingrui
	 * @return return:Esbticketstationtab Date:2011-9-29
	 * @throws Exception
	 */
	public Esbticketstationtab getviewEsbticket(Long iticketstationid)
			throws Exception {
		String sql = "select new map(et.iticketstationid as iticketstationid,et.szstationcode as szstationcode,et.szstationname as szstationname,es.szscenicname as szscenicname,et.szcontact as szcontact,et.szphone as szphone,et.byisuse as byisuse,et.szaddress as szaddress,et.szmemo as szmemo) from Esbticketstationtab et,Esbscenicareatab es where es.iscenicid=et.iscenicid and et.iticketstationid="
				+ iticketstationid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbticketstationtab ts = new Esbticketstationtab();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}

	/**
	 * * 根据登录人得到服务商列表 Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#findListesbticket(com.ectrip.model.employee.Esfemployeetab,
	 *      java.lang.String, int, java.lang.String)
	 * @param esfemployeetab
	 * @param scenictype
	 *          服务商类型（如要查询所有服务商，此参数为空）
	 * @param isjd
	 *          是否含景点（0为服务商，1为景点，2为服务商与景点）
	 * @param isonlyjq
	 *          是否包含所属于些服务商类型下的所有服务商（01是，02否）
	 * @return
	 * @author lijingrui Date:2011-9-30
	 */
	public List findListesbticket(Esfemployeetab esfemployeetab,
			String scenictype, int isjd, String isonlyjq) {
		List list = new ArrayList();
		StringBuffer hsql2 = new StringBuffer();
		if (scenictype == null || "".equals(scenictype)) {
			hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1  ");
		} else {
			if (isonlyjq != null && !"".equals(isonlyjq)) {
				if ("01".equals(isonlyjq)) {//所有包含子服务商
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
									+ scenictype + "'or spmcd='" + scenictype + "')) ");
				} else if ("02".equals(isonlyjq)) {//主服务商包含景点
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype ='01' ");
				}else if ("03".equals(isonlyjq)) {//酒店及下级酒店
					hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
							+ scenictype + "' or spmcd='" + scenictype + "' or sys1.id.pmcd='06')) ");
				}else if ("04".equals(isonlyjq)) {
					hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype !='01' ");
				}else if ("05".equals(isonlyjq)) {//根据服务商类型查询
				    hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
							+ scenictype + "' or spmcd='" + scenictype + "')) ");
				}else {
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
									+ scenictype + "'or spmcd='" + scenictype + "')) ");
				}
			} else {
				hsql2
						.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
								+ scenictype + "'or spmcd='" + scenictype + "')) ");
			}
		}
		if (0 == isjd) {// 服务商
			hsql2.append(" and  es.isjd=0 ");
		} else if (1 == isjd) {// 景点

			if ("".equals(isjd)) {
				hsql2.append(" and  es.isjd=0 ");
			} else if ("01".equals(isjd)) {
				hsql2.append(" and  es.isjd=1 ");
			}
		}
		// 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
		if (esfemployeetab != null) {
			if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
				String scenics = esfemployeetab.getScenics();
				if (scenics == null || scenics.equals("")) {
					return list;
				}
				hsql2.append(" and es.iscenicid in (" + scenics + ")  ");
			}
		}
		hsql2.append(" order by es.scenictype, es.iscenicid ");
		System.out.println("酒店sql:"+hsql2);
		list = this.find(hsql2.toString());
		return list;
	}

	
	/**
	 * * 根据登录人得到服务商列表 Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种 非景区类的
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#findListesbticket(com.ectrip.model.employee.Esfemployeetab,
	 *      java.lang.String, int, java.lang.String)
	 * @param esfemployeetab
	 * @param scenictype
	 *          服务商类型（如要查询所有服务商，此参数为空）
	 * @param isjd
	 *          是否含景点（0为服务商，1为景点，2为服务商与景点）
	 * @param isonlyjq
	 *          是否包含所属于些服务商类型下的所有服务商（01是，02否）
	 * @return
	 * @author lijingrui Date:2011-9-30
	 */
	public List findListesbticketNotscenic(Esfemployeetab esfemployeetab,
			String scenictype, int isjd, String isonlyjq) {
		List list = new ArrayList();
		StringBuffer hsql2 = new StringBuffer();
		if (scenictype == null || "".equals(scenictype)) {
			hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1  ");
		} else {
			if (isonlyjq != null && !"".equals(isonlyjq)) {
				if ("01".equals(isonlyjq)) {
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype  in ( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and sys1.id.pmcd!='"
									+ scenictype + "') ");
				} else if ("02".equals(isonlyjq)) {
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype !='01' ");
				}else if ("03".equals(isonlyjq)) {//酒店及下级酒店
					hsql2
					.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
							+ scenictype + "' or spmcd='" + scenictype + "' or sys1.id.pmcd='06')) ");
				} else {
					hsql2
							.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype  in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and sys1.id.pmcd!='"
									+ scenictype + "') ");
				}
			} else {
				hsql2
						.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and sys1.id.pmcd!='"
								+ scenictype + "') ");
			}
		}
		if (0 == isjd) {// 服务商
			hsql2.append(" and  es.isjd=0 ");
		} else if (1 == isjd) {// 景点
			if ("".equals(isjd)) {
				hsql2.append(" and  es.isjd=0");
			} else if ("01".equals(isjd)) {
				hsql2.append(" and  es.isjd=1 ");
			}
		}
		// 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
		if (esfemployeetab != null) {
			if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
				String scenics = esfemployeetab.getScenics();
				if (scenics == null || scenics.equals("")) {
					return list;
				}
				hsql2.append(" and es.iscenicid in (" + scenics + ")  ");
			}
		}
		hsql2.append(" order by es.scenictype, es.iscenicid ");
		list = this.find(hsql2.toString());
		return list;
	}
	
	/**
	 * * Describe:检查售票站点下是否含有售票窗口
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#getlistEsbticketwin(java.lang.Long)
	 * @param iticketstationid
	 * @return
	 * @author lijingrui Date:2011-10-14
	 */
	public boolean getlistEsbticketwin(Long iticketstationid) {
		boolean b = false;
		String sql = " from Esbticketwintab where iticketstationid="
				+ iticketstationid;
		List list = this.find(sql);
		if (list != null && list.size() > 0) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	/**
	 * * Describe:显示所有的门票站点信息
	 * 
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#showAllesbticket()
	 * @return
	 * @author lijingrui Date:2011-9-30
	 */
	public List showAllesbticket() {
		String sql = " from Esbticketstationtab where byisuse=1";
		List list = this.find(sql);
		return list;

	}

	public List showAllesbticket(String scenics) {
		String sql = " from Esbticketstationtab where byisuse=1";
		if(scenics!=null&&!scenics.equals("")){
			sql+=" and iscenicid in ("+scenics+")";
		}
		List list = this.find(sql);
		return list;

	}
	
	public List showAllesbticketwin() {
		String sql = " from Esbticketwintab where byisuse=1";
		List list = this.find(sql);
		return list;

	}

	public List getTicketWinbycode(String szstationcode) {

		String sql = " from Esbticketstationtab where szstationcode='"
				+ szstationcode + "'";
		List list = this.find(sql);
		return list;
	}

}
