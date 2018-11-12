package com.ectrip.sys.syspar.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IHolidaydetaDao;


public class HolidaydetaDAO extends GenericDao implements IHolidaydetaDao{
	/**
	 * *
	 * Describe:显示所有的节假日明细信息
	 * @see com.ectrip.system.syspar.dao.idao.IHolidaydetaDao#listviewHolidaydeta(java.lang.Long, int, int, java.lang.String)
	 * @param holidayid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public PaginationSupport listviewHolidaydeta(Long holidayid, int pageSize,
			int startIndex, String url) {
		String sql="select new map(hd.holidaydetailid as holidaydetailid,hd.holidayid as holidayid,hd.startdata as startdata,hd.enddata as enddata) from Holidaydetail hd where hd.holidayid="+holidayid;
		return findPage(sql, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:添加节假日明细
	 * @see com.ectrip.system.syspar.dao.idao.IHolidaydetaDao#insertHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void insertHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		String hql="select max(holidaydetailid) from Holidaydetail";
		List lst = this.find(hql);
		Long hid = null;
		if (lst != null && lst.size() >= 1 && lst.get(0) != null) {
			hid = (Long) lst.get(0) + 1;
		} else {
			hid = new Long(1);
		}
		hodaydeta.setHolidaydetailid(hid);
		this.save(hodaydeta);
		
		

		syslog.setStlg("0043");
		syslog.setBrief("节假日明细：" +hodaydeta.getHolidaydetailid() );
		syslog.setNote("添加节假日明细：" +hodaydeta.getHolidayid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * *
	 * Describe:修改节假日明细
	 * @see com.ectrip.system.syspar.dao.idao.IHolidaydetaDao#updateHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void updateHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		Holidaydetail hy=(Holidaydetail) super.get(Holidaydetail.class, hodaydeta.getHolidaydetailid());
		hy.setStartdata(hodaydeta.getStartdata());
		hy.setEnddata(hodaydeta.getEnddata());
		this.update(hy);
		
		syslog.setStlg("0044");
		syslog.setBrief("节假日明细：" +hodaydeta.getHolidaydetailid() );
		syslog.setNote("修改节假日明细：" +hodaydeta.getHolidayid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * *
	 * Describe:删除节假日明细
	 * @see com.ectrip.system.syspar.dao.idao.IHolidaydetaDao#deleteHolidaydeta(com.ectrip.model.syspar.Holidaydetail, com.ectrip.model.syspar.Syslog)
	 * @param hodaydeta
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void deleteHolidaydeta(Holidaydetail hodaydeta, Syslog syslog) {
		Holidaydetail hy=(Holidaydetail) super.get(Holidaydetail.class, hodaydeta.getHolidaydetailid());
		this.delete(hy);
		
		syslog.setStlg("0045");
		syslog.setBrief("节假日明细：" +hodaydeta.getHolidaydetailid() );
		syslog.setNote("删除节假日明细：" +hodaydeta.getHolidayid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 
	 * Describe:判断节假日明细是否相同
	 * @auth:lijingrui
	 * @param holidayid
	 * @param hodaydeta
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean getHolidaydetaless(Long holidayid,Holidaydetail hodaydeta) {
		String sql="select count(hd.holidaydetailid) from Holidaydetail hd where hd.startdata='"+hodaydeta.getStartdata()+"'  and hd.enddata='"+hodaydeta.getEnddata()+"' and hd.holidayid="+holidayid;
		List list = super.find(sql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	} 
}

