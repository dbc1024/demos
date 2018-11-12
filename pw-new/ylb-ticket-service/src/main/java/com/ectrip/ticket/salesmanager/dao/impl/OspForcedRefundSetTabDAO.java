package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospforcedrefundsettab;
import com.ectrip.ticket.salesmanager.dao.IOspForcedRefundSetTabDAO;
/**
 * Created by Jzhenhua,Time 2011-10-05 10:14<br>
 * 强制退票
 *
 * @author lenovo
 */
public class OspForcedRefundSetTabDAO extends GenericDao implements
		IOspForcedRefundSetTabDAO {

	/**
	 * 添加强制退票
	 */
	public void addOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab, Syslog syslog) {
		this.save(ospforcedrefundsettab);

		// 添加日志信息
		syslog.setStlg("0115");
		syslog.setBrief("强行退票操作：" + ospforcedrefundsettab.getIforcedrefundid());
		syslog.setNote("新增强行退票信息：" + ospforcedrefundsettab.getSzforcedrefundname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 标记删除强制退票信息
	 */
	public void delOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab, Syslog syslog) {
		this.delete(ospforcedrefundsettab);

		// 添加日志信息
		syslog.setStlg("0117");
		syslog.setBrief("强行退票操作：" + ospforcedrefundsettab.getIforcedrefundid());
		syslog.setNote("删除强行退票信息：" + ospforcedrefundsettab.getSzforcedrefundname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 修改强制退票信息
	 */
	public void editOspForcedRefundSet(
			Ospforcedrefundsettab ospforcedrefundsettab, Syslog syslog) {
		this.update(ospforcedrefundsettab);

		// 添加日志信息
		syslog.setStlg("0116");
		syslog.setBrief("强行退票操作：" + ospforcedrefundsettab.getIforcedrefundid());
		syslog.setNote("修改强行退票信息：" + ospforcedrefundsettab.getSzforcedrefundname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 获取所有没有标记删除的强制退票信息
	 */
	public PaginationSupport getAllOspforcedrefundset(int pageSize,
													  int startIndex, String url) {
		String hsql = "FROM Ospforcedrefundsettab";
		return this.findPage(hsql, pageSize, startIndex, url);
	}

	/**
	 * 获得最大ID
	 */
	public Long getMaxId() {
		String hsql = "SELECT MAX(o.iforcedrefundid) FROM Ospforcedrefundsettab o";
		List list = this.find(hsql);

		Long b = new Long(0);

		if (list.get(0) != null) {
			b = Long.parseLong(list.get(0).toString());
		}
		return b + 1;
	}

	/**
	 * 根据编号查询
	 */
	public Ospforcedrefundsettab getOspforcedrefundsetById(Long id) {
		return (Ospforcedrefundsettab) this
				.get(Ospforcedrefundsettab.class, id);
	}

	/**
	 * 查询
	 */
	public PaginationSupport searchOspforcedrefundset(
			Ospforcedrefundsettab ospforcedrefundsettab, int pageSize,
			int startIndex, String url) {
		StringBuffer hsql = new StringBuffer(
				"FROM Ospforcedrefundsettab o WHERE o.bisdelete");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

}
