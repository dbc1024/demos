package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.salesmanager.dao.IOpcemployeecardsubtabDAO;

/**
 * @author Jzhenhua,Time 2011-10-07
 */
public class OpcemployeecardsubtabDAO extends GenericDao implements
		IOpcemployeecardsubtabDAO {

	/**
	 * 添加
	 */
	public void addPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.save(opcemployeecardsubtab);
	}

	/**
	 * 删除
	 */
	public void delPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.delete(opcemployeecardsubtab);
	}

	/**
	 * 获取最大ID
	 */
	public List getMaxId() {
		String hsql = "SELECT max(o.iemployeecardsubid) FROM Opcemployeecardsubtab o";
		return this.find(hsql);
	}

	/**
	 * 根据员工卡证ID查询子表列表
	 */
	public PaginationSupport getPloyeecardSubByCardId(Long ployeecardId,
													  int pageSize, int startIndex, String url) {
		String hsql="select new map(pc.iemployeecardsubid as iemployeecardsubid,pc.iemployeecardid as iemployeecardid,pc.igardengateid as igardengateid,pc.dtlastcheckdate as dtlastcheckdate,pc.bylastcheckdir as bylastcheckdir,pc.ipasstimes as ipasstimes,pc.msingletimes as msingletimes,pc.ipassedtimes as ipassedtimes,pc.mlimitconsume as mlimitconsume,pc.msingleconsume as msingleconsume,pc.mconsumed as mconsumed,pc.szwicketsetinfo as szwicketsetinfo,pc.szmemo as szmemo,"+
				"emp.szemployeename as szemployeename,esb.szgardengatename as szgardengatename) from Opcemployeecardsubtab pc,Opcemployeecardtab op,Esfemployeetab emp,Esbgardengatetab esb where pc.iemployeecardid=op.iemployeecardid and op.iemployeeid=emp.iemployeeid and pc.igardengateid=esb.id.igardengateid and pc.iemployeecardid="+ ployeecardId;
		return this.findPage(hsql, pageSize, startIndex, url);
	}

	/**
	 * 根据ID获取子表信息
	 * @throws Exception
	 */
	public Opcemployeecardsubtab getPloyeecardSubById(Long id) throws Exception {
		String hql="select new map(pc.iemployeecardsubid as iemployeecardsubid,pc.iemployeecardid as iemployeecardid,pc.igardengateid as igardengateid,pc.dtlastcheckdate as dtlastcheckdate,pc.bylastcheckdir as bylastcheckdir,pc.ipasstimes as ipasstimes,pc.msingletimes as msingletimes,pc.ipassedtimes as ipassedtimes,pc.mlimitconsume as mlimitconsume,pc.msingleconsume as msingleconsume,pc.mconsumed as mconsumed,pc.szwicketsetinfo as szwicketsetinfo,pc.szmemo as szmemo,"+
				"emp.szemployeename as szemployeename,esb.szgardengatename as szgardengatename) from Opcemployeecardsubtab pc,Opcemployeecardtab op,Esfemployeetab emp,Esbgardengatetab esb where pc.iemployeecardid=op.iemployeecardid and op.iemployeeid=emp.iemployeeid and pc.igardengateid=esb.id.igardengateid and pc.iemployeecardsubid="+id;
		List list = super.find(hql.toString());
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Opcemployeecardsubtab ts=new Opcemployeecardsubtab();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}

	/**
	 * 更新
	 */
	public void updatePloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab) {
		this.update(opcemployeecardsubtab);
	}

	/**
	 * *
	 * Describe:在某一员工卡证下，子表中园门不能重复添加
	 * @see com.ectrip.system.salesmanager.dao.idao.IOpcemployeecardsubtabDAO#getPloyeecardSubGard(java.lang.Long, java.lang.Long)
	 * @param igardengateid
	 * @param iemployeecardid
	 * @return
	 * @author lijingrui
	 * Date:Nov 15, 2011
	 */
	public boolean getPloyeecardSubGard(Long igardengateid,Long iemployeecardid){
		String sql=" from Opcemployeecardsubtab pc where pc.igardengateid="+igardengateid+" and pc.iemployeecardid="+iemployeecardid;
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}

	}

}
