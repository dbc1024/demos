package com.ectrip.sys.syspar.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Hicopertionlog;
import com.ectrip.sys.model.syspar.Icopertionlog;
import com.ectrip.sys.syspar.dao.IIccardlogDao;

public class IccardlogDao extends GenericDao implements IIccardlogDao {
	/**
	 * *
	 * Describe:IC卡操作当日日志
	 * @see com.ectrip.system.syspar.dao.idao.IIccardlogDao#showlisticcardlog(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param cardno	卡号
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisticcardlog(String cardno, int pagesize, int startIndex, String url) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct new map(ic.seq as seq,ic.szticketprintno as szticketprintno,ic.optype as optype,ic.equipmentid as equipmentid,ic.icblock as icblock,ic.iccontent as iccontent,ic.dtdatemake as dtdatemake,(case when ic.optype = 1 then et.szticketwinname when ic.optype = 2 then eq.szaccessequipname else '' end) as equipmentname) from Icopertionlog ic,Esbticketwintab et, Esbaccessequiptab eq");
		sql.append(" where(case when ic.optype = 1 then et.iticketwinid when ic.optype = 2 then eq.id.iaccessequipid else 0 end)=ic.equipmentid");
		if(!cardno.equals("")){
			sql.append(" and ic.szticketprintno = '"+cardno+"'");
		}
		sql.append(" and substr(ic.dtdatemake,1,10) = '"+Tools.getDays()+"'");
		sql.append(" order by ic.dtdatemake desc");
		return this.findPage(sql.toString(), pagesize, startIndex, url);
	}
	/**
	 * *
	 * Describe:根据日志编号查找日志信息
	 * @see com.ectrip.system.syspar.dao.idao.IIccardlogDao#findiclogInfo(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public Icopertionlog findiclogInfo(Long seq) throws Exception{
		List list = this.find(" select distinct new map(ic.seq as seq,ic.szticketprintno as szticketprintno,ic.optype as optype,ic.equipmentid as equipmentid,ic.icblock as icblock,ic.iccontent as iccontent,ic.dtdatemake as dtdatemake,(case when ic.optype = 1 then et.szticketwinname else eq.szaccessequipname end) as equipmentname) from Icopertionlog ic,Esbticketwintab et, Esbaccessequiptab eq " +
				"where(case when ic.optype = 1 then et.iticketwinid when ic.optype = 2 then eq.id.iaccessequipid else 0 end)=ic.equipmentid and ic.seq = "+seq);
		Icopertionlog iclog = new Icopertionlog();
		if(list!=null&&list.size()>0){
			BeanUtils.populate(iclog, (Map) list.get(0));
		}
		return iclog;
	}
	/**
	 * *
	 * Describe:IC卡操作历史日志
	 * @see com.ectrip.system.syspar.dao.idao.IIccardlogDao#showlisthiccardlog(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param cardno
	 * @param rzti
	 * @param ldti
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisthiccardlog(String cardno, String rzti, String ldti, int pagesize, int startIndex, String url){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct new map(hic.seq as seq,hic.szticketprintno as szticketprintno,hic.optype as optype,hic.equipmentid as equipmentid,hic.icblock as icblock,hic.iccontent as iccontent,hic.dtdatemake as dtdatemake,(case when hic.optype = 1 then et.szticketwinname else eq.szaccessequipname end) as equipmentname) from Hicopertionlog hic,Esbticketwintab et, Esbaccessequiptab eq");
		sql.append(" where(case when hic.optype = 1 then et.iticketwinid when hic.optype = 2 then eq.id.iaccessequipid else 0 end)=hic.equipmentid");
		if(!cardno.equals("")){
			sql.append(" and hic.szticketprintno = '"+cardno+"'");
		}
		sql.append(" and substr(hic.dtdatemake,1,10) between '"+rzti+"' and '"+ldti+"'");
		sql.append(" order by hic.dtdatemake desc");
		return this.findPage(sql.toString(), pagesize, startIndex, url);
	}
	/**
	 * *
	 * Describe:根据日志编号查找历史日志信息
	 * @see com.ectrip.system.syspar.dao.idao.IIccardlogDao#findhiclogInfo(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public Hicopertionlog findhiclogInfo(Long seq) throws Exception {
		List list = this.find(" select distinct new map(hic.seq as seq,hic.szticketprintno as szticketprintno,hic.optype as optype,hic.equipmentid as equipmentid,hic.icblock as icblock,hic.iccontent as iccontent,hic.dtdatemake as dtdatemake,(case when hic.optype = 1 then et.szticketwinname else eq.szaccessequipname end) as equipmentname) from Hicopertionlog hic,Esbticketwintab et, Esbaccessequiptab eq " +
				"where(case when hic.optype = 1 then et.iticketwinid when hic.optype = 2 then eq.id.iaccessequipid else 0 end)=hic.equipmentid and hic.seq = "+seq);
		Hicopertionlog hiclog = new Hicopertionlog();
		if(list!=null&&list.size()>0){
			BeanUtils.populate(hiclog, (Map) list.get(0));
		}
		return hiclog;
	}
}

