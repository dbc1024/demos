package com.ectrip.ticket.afcset.service.impl;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbcomputerserverstabDAO;
import com.ectrip.ticket.afcset.service.IEsbcomputerserverstabService;
import com.ectrip.ticket.model.afcset.Esbcomputerserverstab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public class EsbcomputerserverstabService extends GenericService implements
		IEsbcomputerserverstabService {
	private IEsbcomputerserverstabDAO esbcomputerserverstabDAO;

	public IEsbcomputerserverstabDAO getEsbcomputerserverstabDAO() {
		return esbcomputerserverstabDAO;
	}

	public void setEsbcomputerserverstabDAO(
			IEsbcomputerserverstabDAO esbcomputerserverstabDAO) {
		this.esbcomputerserverstabDAO = esbcomputerserverstabDAO;
	}

	public void insert(Esbcomputerserverstab esbcomputerserverstab, Syslog syslog) {

		Long id = getMaxPk("iserverid", "Esbcomputerserverstab");
		esbcomputerserverstab.setIserverid(id + 1);

		this.save(esbcomputerserverstab);
		Esbscenicareatab esbscenicareatab=(Esbscenicareatab)this.esbcomputerserverstabDAO.get(Esbscenicareatab.class, esbcomputerserverstab.getIscenicid());
		syslog.setStlg("0009");
		syslog.setBrief("服务商："
				+ esbscenicareatab.getSzscenicname()
				+ "增加服务器：" + esbcomputerserverstab.getSzservername());
		syslog.setNote("服务商："
				+ esbscenicareatab.getSzscenicname() + "("
				+ esbcomputerserverstab.getIscenicid()
				+ ")增加服务器：" + esbcomputerserverstab.getSzservername() + "("
				+ esbcomputerserverstab.getIserverid() + ")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);

		this.save(syslog);
	}

	public void update(Esbcomputerserverstab esbcomputerserverstab, Syslog syslog) {

		syslog.setStlg("0010");
		Esbscenicareatab esbscenicareatab=(Esbscenicareatab)this.esbcomputerserverstabDAO.get(Esbscenicareatab.class, esbcomputerserverstab.getIscenicid());

		syslog.setBrief("服务商："
				+ esbscenicareatab.getSzscenicname()
				+ "增加服务器：" + esbcomputerserverstab.getSzservername());
		syslog.setNote("服务商："
				+ esbscenicareatab + "("
				+ esbcomputerserverstab.getIscenicid()
				+ ")修改服务商：" + esbcomputerserverstab.getSzservername() + "("
				+ esbcomputerserverstab.getIserverid() + ")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		this.update(esbcomputerserverstab);
	}

	public void delete(Esbcomputerserverstab esbcomputerserverstab, Syslog syslog) {
		Esbscenicareatab esbscenicareatab=(Esbscenicareatab)this.esbcomputerserverstabDAO.get(Esbscenicareatab.class, esbcomputerserverstab.getIscenicid());

		syslog.setStlg("0011");
		syslog.setBrief("删除服务商："
				+ esbscenicareatab.getSzscenicname()
				+ "服务器：" + esbcomputerserverstab.getSzservername());
		syslog.setNote("删除服务商："
				+ esbscenicareatab.getSzscenicname() + "("
				+ esbcomputerserverstab.getIscenicid() + ")服务器："
				+ esbcomputerserverstab.getSzservername() + "("
				+ esbcomputerserverstab.getIserverid() + ")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		this.delete(esbcomputerserverstab);
	}

	public Esbcomputerserverstab findoneserver(Long iserverid) {
		Esbcomputerserverstab esbcomputerserverstab = (Esbcomputerserverstab) esbcomputerserverstabDAO
				.get(Esbcomputerserverstab.class, iserverid);
		Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.get(
				Esbscenicareatab.class, esbcomputerserverstab.getIscenicid());
		esbcomputerserverstab.setSzscenicname(esbscenicareatab.getSzscenicname());
		return esbcomputerserverstab;
	}
}
