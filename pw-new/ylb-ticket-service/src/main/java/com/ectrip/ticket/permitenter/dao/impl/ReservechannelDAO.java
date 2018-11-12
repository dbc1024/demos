package com.ectrip.ticket.permitenter.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.permitenter.Reservechannel;
import com.ectrip.ticket.permitenter.dao.IReservechannelDAO;

public class ReservechannelDAO extends GenericDao implements IReservechannelDAO {
	/**
	 * ��ʾ��������Ϣ
	 */
	public List findListesbticket(String scenics) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from Esbscenicareatab where isjd=0 and scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or sys1.spmcd='01'))");
		if (scenics != null && !scenics.equals("")) {
			sql.append(" and iscenicid in (" + scenics + ")");
		}
		sql.append(" order by iscenicid");
		return this.find(sql.toString());
	}

	/**
	 * ��ȡԤԼͨ��
	 */
	public PaginationSupport getReservechannel(Reservechannel reservechannel,
			String scenics, int pageSize, int page, String url) {
		StringBuffer hsql = new StringBuffer(
				"select new map(channel.id as id,channel.channelid as channelid,channel.iscenicid as iscenicid,channel.igardengateid as igardengateid,channel.dtmakedate as dtmakedate, gardengate.szgardengatename as strigardengateid,scenicarea.szscenicname as striscenicid,accessequip.szaccessequipname as striaccessequipid) from Reservechannel channel,Esbaccessequiptab accessequip,Esbgardengatetab gardengate,Esbscenicareatab scenicarea WHERE channel.channelid=accessequip.id.iaccessequipid and channel.iscenicid=scenicarea.iscenicid and channel.igardengateid=gardengate.id.igardengateid and accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid ");
		if (scenics != null && !scenics.equals("")) {
			hsql.append(" AND channel.iscenicid in (" + scenics + ")");
		}
		// �ж��Ƿ�ѡ������̲�ѯ
		if (null != reservechannel) {
			if (null != reservechannel.getIscenicid()
					&& !"".equals(reservechannel.getIscenicid())) {
				hsql.append(" AND channel.iscenicid="
						+ reservechannel.getIscenicid());
			}

		}
		return this.findPage(hsql.toString(), pageSize, page, url);
	}

	public List getEsbaccessequip(Long igardengateid) {
		// TODO Auto-generated method stub
		return this.find(" FROM Esbaccessequiptab e where e.id.igardengateid= "
				+ igardengateid);
	}

	/**
	 * ����԰�Ż�ȡ׼���豸
	 */
	public String igetAllEsbaccessequiptabJSON(Long id) throws Exception {
		StringBuffer hsql = new StringBuffer(
				"FROM Esbaccessequiptab e WHERE 1=1 ");

		if (id != null) {
			hsql.append("AND e.id.igardengateid = " + id);
		}

		List<Esbaccessequiptab> list = this.find(hsql.toString());
		StringBuffer JSON = new StringBuffer();
		JSON.append("[");

		for (int i = 0; i < list.size(); i++) {
			Esbaccessequiptab esbaccessequiptab = list.get(i);
			JSON.append("{\"iaccessequipid\":\""
					+ esbaccessequiptab.getId().getIaccessequipid()
					+ "\",\"szaccessequipname\":\""
					+ esbaccessequiptab.getSzaccessequipname() + "\"}");

			if (i != list.size() - 1) {
				JSON.append(",");
			}
		}

		JSON.append("]");

		return JSON.toString();
	}

	/**
	 * ��õ�ǰ���ID
	 */
	public Long getMaxId() throws Exception {
		String hsql = "SELECT MAX(e.id) FROM Reservechannel e";
		List list = this.find(hsql);
		Long b = new Long(0);
		if (list.get(0) != null) {
			b = Long.parseLong(list.get(0).toString());
		}

		return b + 1;
	}

	public String igetGardengateJSON(Long id) throws Exception {
		StringBuffer hsql = new StringBuffer(
				"FROM Esbgardengatetab e WHERE 1=1 ");

		if (id != null) {
			hsql.append("AND e.id.iscenicid = " + id);
		}

		List<Esbgardengatetab> list = this.find(hsql.toString());
		StringBuffer JSON = new StringBuffer();
		JSON.append("[");

		for (int i = 0; i < list.size(); i++) {
			Esbgardengatetab gardengate = list.get(i);
			JSON.append("{\"igardengateid\":\""
					+ gardengate.getId().getIgardengateid()
					+ "\",\"szgardengatename\":\""
					+ gardengate.getSzgardengatename() + "\"}");

			if (i != list.size() - 1) {
				JSON.append(",");
			}
		}

		JSON.append("]");
		return JSON.toString();
	}

	// ����ԤԼͨ��
	public void addReservechannel(Reservechannel reservechannel) {
		reservechannel.setId(this.getMaxPk("id", "Reservechannel") + 1L);
		this.save(reservechannel);
	}

	// ����ԤԼͨ��id��ѯԤԼͨ������
	public Reservechannel getReservechannelById(Long id) {
		String hsql = "select new map(channel.id as id,channel.channelid as channelid,channel.iscenicid as iscenicid,channel.igardengateid as igardengateid,channel.dtmakedate as dtmakedate, gardengate.szgardengatename as strigardengateid,scenicarea.szscenicname as striscenicid,accessequip.szaccessequipname as striaccessequipid) from Reservechannel channel,Esbaccessequiptab accessequip,Esbgardengatetab gardengate,Esbscenicareatab scenicarea WHERE channel.channelid=accessequip.id.iaccessequipid and channel.iscenicid=scenicarea.iscenicid and channel.igardengateid=gardengate.id.igardengateid and accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid "
				+ "and channel.id=" + id;
		List list = super.find(hsql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Reservechannel ts = new Reservechannel();
			try {
				BeanUtils.populate(ts, (Map) list.get(0));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ts;
		}
	}

	public void delReservechannel(Reservechannel reservechannel) {
		String sql=" from Reservechannel where id="+reservechannel.getId()+" and iscenicid="+reservechannel.getIscenicid()+" and igardengateid="+reservechannel.getIgardengateid();
		Reservechannel es=(Reservechannel) this.find(sql).get(0);
		this.delete(es);		
	}

	public boolean checkReservechannel(Reservechannel reservechannel) {
		String sql = " from Reservechannel  where iscenicid="+reservechannel.getIscenicid()+" and igardengateid="+reservechannel.getIgardengateid()+" and channelid="+reservechannel.getChannelid();
		
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
		
	}

}
