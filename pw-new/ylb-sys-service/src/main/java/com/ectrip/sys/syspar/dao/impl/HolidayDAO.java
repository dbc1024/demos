package com.ectrip.sys.syspar.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Holiday;
import com.ectrip.sys.model.syspar.Holidaydetail;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IHolidayDao;
public class HolidayDAO extends GenericDao implements IHolidayDao {

	/**
	 * *
	 * Describe:显示所有的假期信息  根据假期名称查询
	 * @see com.ectrip.base.dao.GenericDao#findPage(java.lang.String, int, int, java.lang.String)
	 * @param holidayname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-9-27
	 */
	public PaginationSupport getlistholiday(String holidayname, int pageSize,
			int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select new map(hd.holidayid as holidayid,hd.holidayname as holidayname)  from Holiday hd" );
		if(holidayname!=null&&!holidayname.equals("")){
			sql.append(" where hd.holidayname like '%"+holidayname+"%'" );
		}
		sql.append(" order by hd.holidayid " );
		return findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:添加节假日
	 * @see com.ectrip.system.syspar.dao.idao.IHolidayDao#insertHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void insertHoliday(Holiday hoday,Syslog syslog) {
		String sql="select max(holidayid) from Holiday";
		List list = this.find(sql);
		Long id = null;
		if (list != null && list.size() >= 1 && list.get(0) != null) {
			id = (Long) list.get(0) + 1;
		} else {
			id = new Long(1);
		}
		hoday.setHolidayid(id);
		this.save(hoday);
		
		syslog.setStlg("0040");
		syslog.setBrief("节假日：" +hoday.getHolidayid() );
		syslog.setNote("添加节假日：" +hoday.getHolidayname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:修改假期
	 * @see com.ectrip.system.syspar.dao.idao.IHolidayDao#updateHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void updateHoliday(Holiday hoday,Syslog syslog) {
		Holiday hd=(Holiday) super.get(Holiday.class, hoday.getHolidayid());
		hd.setHolidayname(hoday.getHolidayname());
		this.update(hd);

		
		syslog.setStlg("0041");
		syslog.setBrief("节假日：" +hoday.getHolidayid() );
		syslog.setNote("修改节假日：" +hoday.getHolidayname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:删除假期
	 * @see com.ectrip.system.syspar.dao.idao.IHolidayDao#deleteHoliday(com.ectrip.model.syspar.Holiday, com.ectrip.model.syspar.Syslog)
	 * @param hoday
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public void deleteHoliday(Holiday hoday,Syslog syslog) {
		Holiday hd=(Holiday) super.get(Holiday.class, hoday.getHolidayid());
		this.delete(hd);
		String sql=" from Holidaydetail hs where hs.holidayid="+hoday.getHolidayid();
		List lst=this.find(sql);
		if(lst.size()>0&&lst!=null){
			for(int i=0;i<lst.size();i++){
				Holidaydetail esf=(Holidaydetail) lst.get(i);
				this.delete(esf);
			}
		}
		
		syslog.setStlg("0042");
		syslog.setBrief("节假日：" +hoday.getHolidayid() );
		syslog.setNote("删除节假日：" +hoday.getHolidayname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 
	 * Describe:判断节假日名称是否相同
	 * @auth:lijingrui
	 * @param holidayname
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean genHolidayname(String holidayname) {
		String sql="select count(es.holidayid) from Holiday es where es.holidayname='"+holidayname+"'";
		List list = super.find(sql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * *
	 * Describe:显示某个节假日的明细信息
	 * @see com.ectrip.system.syspar.dao.idao.IHolidayDao#getHolidaydetaliview(java.lang.Long)
	 * @param holidayid
	 * @return
	 * @author lijingrui
	 * Date:2011-9-29
	 */
	public List<Holidaydetail> getHolidaydetaliview(Long holidayid) {
		String sql=" from Holidaydetail hs where hs.holidayid="+holidayid;
		
		return this.find(sql);
	}
}

