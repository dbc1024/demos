package com.ectrip.ticket.afcset.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Esbequiptypetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbequiptypetabDAO;

public class EsbequiptypetabDAO extends GenericDao implements IEsbequiptypetabDAO {

	/**
	 * *
	 * Describe:列表显示设备类型信息
	 * @see com.ectrip.system.afcset.dao.idao.IEsbequiptypetabDAO#showListprdcontrol(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-11-01
	 */
	public PaginationSupport showListprdcontrol(String scenics,int pageSize, int startIndex, String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(esb.iequiptypeid as iequiptypeid,esb.iscenicid as iscenicid,esb.szequiptypemodel as szequiptypemodel,esb.szequiptypename as szequiptypename,sys1.pmva as szcommmode,esb.bycategorytype as bycategorytype,esb.byisuse as byisuse,es.szscenicname as szscenicname) from Esbequiptypetab esb,Esbscenicareatab es,Sysparv5 sys1 where esb.iscenicid=es.iscenicid and esb.szcommmode=sys1.id.pmcd and sys1.id.pmky='CMSE' ");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and esb.iscenicid in ("+scenics+")");
		}
		sql.append(" order by esb.iequiptypeid ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:添加设备类型信息
	 * @see com.ectrip.system.afcset.dao.idao.IEsbequiptypetabDAO#insertesbequiptype(com.ectrip.model.provider.Esbequiptypetab, com.ectrip.model.syspar.Syslog)
	 * @param esbequip
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-11-01
	 */
	public void insertesbequiptype(Esbequiptypetab esbequip,Syslog syslog){
		Long maxid=this.getMaxPk("iequiptypeid", "Esbequiptypetab");
		esbequip.setIequiptypeid(maxid+1);
		this.save(esbequip);

		syslog.setStlg("0103");
		syslog.setBrief("设备类型信息：" + esbequip.getIequiptypeid());
		syslog.setNote("添加设备类型信息：" + esbequip.getSzequiptypename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *
	 * Describe:修改设备类型信息
	 * @see com.ectrip.system.afcset.dao.idao.IEsbequiptypetabDAO#updateesbequiptype(com.ectrip.model.provider.Esbequiptypetab, com.ectrip.model.syspar.Syslog)
	 * @param esbequip
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-11-01
	 */
	public void updateesbequiptype(Esbequiptypetab esbequip,Syslog syslog){
		Esbequiptypetab esb=(Esbequiptypetab) this.get(Esbequiptypetab.class, esbequip.getIequiptypeid());
		esb.setSzequiptypemodel(esbequip.getSzequiptypemodel());
		esb.setSzequiptypename(esbequip.getSzequiptypename());
		esb.setIscenicid(esbequip.getIscenicid());
		esb.setSzcommmode(esbequip.getSzcommmode());
		esb.setBycategorytype(esbequip.getBycategorytype());
		esb.setByisuse(esbequip.getByisuse());
		esb.setSzmemo(esbequip.getSzmemo());

		this.update(esb);

		syslog.setStlg("0104");
		syslog.setBrief("设备类型信息：" + esbequip.getIequiptypeid());
		syslog.setNote("修改设备类型信息：" + esbequip.getSzequiptypename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 *
	 * Describe:删除设备类型信息
	 * @auth:lijingrui
	 * @param esbequip
	 * @param syslog
	 * return:void
	 * Date:2011-11-01
	 */
	public void deleteesbequiptype(Esbequiptypetab esbequip,Syslog syslog){
		Esbequiptypetab esb=(Esbequiptypetab) this.get(Esbequiptypetab.class, esbequip.getIequiptypeid());
		this.delete(esb);

		syslog.setStlg("0105");
		syslog.setBrief("设备类型信息：" + esbequip.getIequiptypeid());
		syslog.setNote("删除设备类型信息：" + esbequip.getSzequiptypename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 *
	 * Describe:根据ID查看设备类型信息
	 * @auth:lijingrui
	 * @param iequiptypeid
	 * @return
	 * return:Esbequiptypetab
	 * Date:2011-11-01
	 * @throws Exception
	 */
	public Esbequiptypetab getviewequiptype(Long iequiptypeid) throws Exception{
		String sql="select new map(esb.iequiptypeid as iequiptypeid,esb.iscenicid as iscenicid,esb.szequiptypemodel as szequiptypemodel,esb.szequiptypename as szequiptypename,sys1.pmva as szcommmode,esb.bycategorytype as bycategorytype,esb.byisuse as byisuse,es.szscenicname as szscenicname) from Esbequiptypetab esb,Esbscenicareatab es,Sysparv5 sys1 where esb.iscenicid=es.iscenicid and esb.szcommmode=sys1.id.pmcd and sys1.id.pmky='CMSE' and esb.iequiptypeid="+iequiptypeid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbequiptypetab ts=new Esbequiptypetab();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}

	/**
	 *
	 * Describe:显示登录人所在企业管理的服务商
	 * @auth:lijingrui
	 * @param scenics
	 * @return
	 * return:List
	 * Date:2011-11-01
	 */
	public List getListscenicar(String scenics){
		StringBuffer sql=new StringBuffer();
		sql.append(" from Esbscenicareatab where isjd=0 and scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or sys1.spmcd='01'))");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and iscenicid in ("+scenics+")");
		}
		sql.append(" order by iscenicid");
		return this.find(sql.toString());
	}
}

