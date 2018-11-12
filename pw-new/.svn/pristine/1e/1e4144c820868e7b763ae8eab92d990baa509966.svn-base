package com.ectrip.ticket.warehouse.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.warehouse.dao.idao.IStocksbillQueryDao;

public class StocksbillQueryDao extends GenericDao implements IStocksbillQueryDao{

	/**
	 * Describe:根据输入条件进行单据查询
	 * @see com.ectrip.system.warehouse.service.iservice.IStocksbillQueryService#showStocksbill(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param bystockswayid
	 * @param ihandler
	 * @param istationinid
	 * @param istationoutid
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author aozhuozu
	 * Date:2012-8-28
	 */
	public PaginationSupport showStocksbill(Long bystockswayid, Long ihandler, Long istationinid, Long istationoutid, String startDate, String endDate,int page,int pageSize,String url){
		PaginationSupport ps = null;
		if(bystockswayid==1L){
			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.istationinid="+istationinid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
//			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
			ps = this.findPage(sql, pageSize, page, url);
			System.out.println(">>>>>>size:"+ps.getItems().size());
		}else if(bystockswayid==3L){
			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.istationoutid="+istationoutid+" and st.istationinid="+istationinid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
			ps = this.findPage(sql, pageSize, page, url);
		}else if(bystockswayid==6L){
			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.ihandler="+ihandler+" and st.istationoutid="+istationinid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
			ps = this.findPage(sql, pageSize, page, url);
		}else if(bystockswayid==5L){
			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.ihandler="+ihandler+" and st.istationinid="+istationinid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
			ps = this.findPage(sql, pageSize, page, url);
		}else if(bystockswayid==4L){
			String sql="select new map(std.itickettypeid as itickettypeid,std.szstartticketcode as szstartticketcode,std.szendticketcode as szendticketcode,std.iamount as iamount,et.sztickettypename as sztickettypename) from Edmtickettypetab et,Iomstocksbill st,Iomstocksbilldetails std where st.bystockswayid="+bystockswayid+" and st.istationoutid="+istationinid+" and st.dtmakedate>='"+startDate+"' and st.dtmakedate<='"+endDate+"' and st.szstocksbillid=std.szstocksbillid and std.itickettypeid=et.itickettypeid";
			ps = this.findPage(sql, pageSize, page, url);
		}
		return ps;
	}


	/**
	 * Describe:保存仓库初始化
	 * @auth:aozhuozu
	 * @param syslog
	 * return:void
	 * Date:2012-8-26
	 */
	public void saveInit(Syslog syslog,String initseason){
//		String hsql="delete from Iomstocksbilldetails,Iomstocksbill,Iompersonalticketdetails,Iomstationticketdetails,Iomwarehouseincome";

		this.bulkUpdate("delete from Iomstocksbilldetails");
		this.bulkUpdate("delete from Iomstocksbill");
		this.bulkUpdate("delete from Iompersonalticketdetails");
		this.bulkUpdate("delete from Iomstationticketdetails");
		this.bulkUpdate("delete from Iomwarehouseincome");
		this.bulkUpdate("delete from Rwswarehouseday where int1=1");
		this.bulkUpdate("delete from Rwswarehousemonth where int1=1");
		this.bulkUpdate("delete from Rwswarehouseyear where int1=1");
		this.bulkUpdate("delete from Rwspersonalday where int2=1");

		syslog.setLogdatetime(Tools.getDayTimes());
		syslog.setStlg("0240");
		syslog.setBrief("仓库初始化：" );
		syslog.setNote("仓库初始化理由：" +initseason+" 制单日期"+Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

}

