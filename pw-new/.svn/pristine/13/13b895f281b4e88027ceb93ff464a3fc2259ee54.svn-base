package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.report.system.datereport.dao.idao.ILogDataDao;

public class LogDataDao extends GenericDao implements ILogDataDao {
	/**
	 * *
	 * Describe:��־ת�Ʋ���
	 * @see com.ectrip.report.system.datereport.dao.idao.ILogDataDao#updateOrQueryLog(java.lang.String, java.lang.String)
	 * @param date
	 * @param type
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public List updateOrQueryLog(String date,String type) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if("1".equals(type)){
			hsql.append(" select iemployeeid as iemployeeid,stlg as stlg,brief as brief,note as note,logdatetime as logdatetime from Syslog where substr(logdatetime,1,10) = '"+
					date+"'");
		}else if("2".equals(type)){
			hsql.append(" select usid as usid,stlg as stlg,logdatetime as logdatetime,brief as brief,note as note from Customlog where substr(logdatetime,1,10) = '"+
					date+"'");
		}else if("3".equals(type)){
			hsql.append(" select orid as orid,stlg as stlg,brief as brief,logtype as logtype,usid as usid,iemployeeid as iemployeeid,logdatetime as logdatetime,note as note from Orderlog where substr(logdatetime,1,10)='"+
					date+"'");
		}else if("4".equals(type)){
			hsql.append(" select szticketprintno as szticketprintno,optype as optype,equipmentid as equipmentid,icblock as icblock,iccontent as iccontent,dtdatemake as dtdatemake from Icopertionlog where substr(dtdatemake,1,10)='"+
					date+"'");
		}
		list = this.find(hsql.toString());
		if("1".equals(type)){
			this.deleteDates(date, "Syslog");
		}else if("2".equals(type)){
			this.deleteDates(date,"Customlog");
		}else if("3".equals(type)){
			this.deleteDates(date, "Orderlog");
		}else if("4".equals(type)){
			this.deleteDates(date, "Icopertionlog");
		}		
		return list;
	}
	/**
	 * *
	 * Describe:ɾ��ԭ��־������
	 * @see com.ectrip.report.system.datereport.dao.idao.ILogDataDao#deleteDates(java.lang.String, java.lang.String)
	 * @param date
	 * @param table
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public void deleteDates(String date, String table) {
		System.out.println("����ɾ����־������ ");
		StringBuffer hsql = new StringBuffer();
		if(table.equals("Icopertionlog")){
			System.out.println("IC��������־ɾ��");
			hsql.append(" from "+ table +" where substr(dtdatemake,1,10)='"+date+"'");
		}else{
			hsql.append(" from "+ table +" where substr(logdatetime,1,10)='"+date+"'");
		}
		List list = this.find(hsql.toString());
		if(list!=null&&list.size()>=1){
			for(int i = 0;i < list.size();i++){
				Object o = (Object)list.get(i);
				this.delete(o);
			}
		}
	}
	
}

