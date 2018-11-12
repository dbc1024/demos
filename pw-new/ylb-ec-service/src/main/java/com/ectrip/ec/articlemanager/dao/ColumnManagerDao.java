package com.ectrip.ec.articlemanager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.articlemanager.dao.idao.IColumnManagerDao;
import com.ectrip.ec.model.articlemanager.Columnmanagertab;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class ColumnManagerDao extends GenericDao implements IColumnManagerDao {
	/**
	 * *
	 * Describe:显示所有栏目信息
	 * @see com.ectrip.system.articlemanager.dao.idao.IColumnManagerDao#showlistColumns(int, int, java.lang.String)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-15
	 */
	public PaginationSupport showlistColumns(int pageSize, int startInt,
			String url) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Columnmanagertab ");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:添加栏目
	 * @see com.ectrip.system.articlemanager.dao.idao.IColumnManagerDao#insertColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void insertColumn(Columnmanagertab column,Syslog syslog){
		Long maxid = this.getMaxPk("cmid", "Columnmanagertab");
		column.setCmid(maxid+1);
		column.setDtmakedate(Tools.getDayTimes());
		this.save(column);
		
//		syslog.setStlg("0225");
//		syslog.setBrief("新增栏目名称："+column.getCmzhtopic());
//		syslog.setNote("新增栏目名称："+column.getCmzhtopic()+",时间："+column.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:修改栏目
	 * @see com.ectrip.system.articlemanager.dao.idao.IColumnManagerDao#updateColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void updateColumn(Columnmanagertab column,Syslog syslog){
		Columnmanagertab col = (Columnmanagertab) this.get(Columnmanagertab.class, column.getCmid());
		col.setCmzhtopic(column.getCmzhtopic());
		col.setCmentopic(column.getCmentopic());
		col.setCmdesc(column.getCmdesc());
		col.setByisuse(column.getByisuse());
		col.setDtmakedate(Tools.getDayTimes());
		this.update(col);
		
//		syslog.setStlg("0227");
//		syslog.setBrief("修改栏目名称："+col.getCmzhtopic());
//		syslog.setNote("修改栏目名称："+col.getCmzhtopic()+",时间："+col.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:删除栏目
	 * @see com.ectrip.system.articlemanager.dao.idao.IColumnManagerDao#deleteColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void deleteColumn(Columnmanagertab column,Syslog syslog){
		Columnmanagertab col = (Columnmanagertab) this.get(Columnmanagertab.class, column.getCmid());
		this.delete(col);
		
//		syslog.setStlg("0226");
//		syslog.setBrief("删除栏目名称："+col.getCmzhtopic());
//		syslog.setNote("删除栏目名称："+col.getCmzhtopic()+",时间："+col.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:根据栏目编号查看栏目信息
	 * @see com.ectrip.system.articlemanager.dao.idao.IColumnManagerDao#viewColumn(java.lang.Long)
	 * @param cmid	栏目编号
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-15
	 */
	public Columnmanagertab viewColumn(Long cmid){
		String sql = " from Columnmanagertab co where co.cmid = "+cmid;
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Columnmanagertab column = (Columnmanagertab) list.get(0);
			return column;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取栏目
	 */
	public Columnmanagertab getcolum(String topic){
		String sql = " from Columnmanagertab co where co.cmentopic = '"+topic+"'";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Columnmanagertab column = (Columnmanagertab) list.get(0);
			return column;
		}else{
			return null;
		}
	}
	
}

