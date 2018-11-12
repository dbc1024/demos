package com.ectrip.sys.syspar.dao.impl;


import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Columnpar;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Tablepar;
import com.ectrip.sys.syspar.dao.IReportsParDAO;

public class ReportsParDAO extends GenericDao implements IReportsParDAO {

	/**
	 * 获取表字典列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-17
	 */
	public PaginationSupport getTableParList(Tablepar tablepar,int page,int pageSize,String url){
		PaginationSupport ps =null;
		
		StringBuffer hsql=new StringBuffer();
		
		hsql.append(" from Tablepar t where 1=1  ");
		
		if(tablepar!=null){
			if(tablepar.getTablename()!=null && !"".equals(tablepar.getTablename())){
				hsql.append(" and t.tablename ='"+tablepar.getTablename()+"' ");
			}
			if(tablepar.getTablenamechn()!=null && !"".equals(tablepar.getTablenamechn())){
				hsql.append(" and t.tablenamechn ='"+tablepar.getTablenamechn()+"' ");
				
			}
		}
		
		hsql.append(" order by  tablename ");
				
		ps = this.findPage(hsql.toString(), pageSize, page, url);
	
		return ps;
	}
	
	/**
	 * 获取列字典列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-17
	 */
	public PaginationSupport getColumnParList(Columnpar columnpar,int page,int pageSize,String url){
		PaginationSupport ps =null;
		StringBuffer hsql=new StringBuffer();
		hsql.append(" from Columnpar c where c.id.seq="+columnpar.getId().getSeq()+" ");
/*		if(columnpar!=null){
			if(columnpar.getId().getColumnname()!=null && !"".equals(columnpar.getId().getColumnname())){
				hsql.append(" and c.id.columnname ='"+columnpar.getId().getColumnname()+"' ");
			}
			if(columnpar.getColumnnamechn()!=null && !"".equals(columnpar.getColumnnamechn())){
				hsql.append(" and c.columnnamechn = '"+columnpar.getColumnnamechn()+"'");
			}
		}*/
		hsql.append(" order by  c.id.columnname ");
	//	System.out.println("====  "+hsql);
		ps = this.findPage(hsql.toString(), pageSize, page, url);	
		return ps;
	}
	
	/**
	 * 增加表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void addTablePar(Tablepar tablepar,Syslog syslog){
		Long maxid=this.getMaxPk("seq", "Tablepar");
		tablepar.setSeq(maxid+1);
		this.save(tablepar);
		
		syslog.setStlg("0162");
		syslog.setBrief("增加表名字典：("+tablepar.getTablename()+")" );
		syslog.setNote("表名字典增加：表名："+tablepar.getTablename()+"["+tablepar.getTablenamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	/**
	 * 修改表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void updateTablePar(Tablepar tablepar,Syslog syslog){
		this.update(tablepar);
		
		String sql=" from Columnpar cm where cm.id.seq="+tablepar.getSeq();
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Columnpar c=(Columnpar) lst.get(i);
				c.setTablename(tablepar.getTablename());
				this.update(c);
			}
		}
		
		syslog.setStlg("0163");
		syslog.setBrief("修改表名字典：("+tablepar.getTablename()+")" );
		syslog.setNote("表名字典修改：表名："+tablepar.getTablename()+"["+tablepar.getTablenamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 删除表字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablepar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void deleteTablePar(Long seq,Syslog syslog){
		
		Tablepar tablepar = (Tablepar)this.get(Tablepar.class, seq);
		
		String hsql=" from Columnpar c where c.id.seq="+seq;
		List list = this.find(hsql);
		if(list!=null && list.size()>0){
			for (int j = 0; j < list.size(); j++) {
				Columnpar columnpar = (Columnpar)list.get(j);
				this.delete(columnpar);
			}			
		}
		
		syslog.setStlg("0164");
		syslog.setBrief("删除表名字典：("+tablepar.getTablename()+")" );
		syslog.setNote("表名字典删除：表名："+tablepar.getTablename()+"["+tablepar.getTablenamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.deleteByKey(Tablepar.class, seq);
	}
	
	/**
	 * 增加列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void addColumnPar(Columnpar columnpar,Syslog syslog){
		
		this.save(columnpar);
		
		Tablepar tablepar = (Tablepar)this.get(Tablepar.class,columnpar.getId().getSeq() );
		
		syslog.setStlg("0165");
		syslog.setBrief("新增列名字典：("+columnpar.getId().getColumnname()+")" );
		syslog.setNote("列名字典增加：表名："+columnpar.getTablename()+"["+tablepar.getTablenamechn()+"],列名："+columnpar.getId().getColumnname()+"["+columnpar.getColumnnamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	/**
	 * 修改列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void updateColumnPar(Columnpar columnpar,Syslog syslog){
		this.update(columnpar);
		
		Tablepar tablepar = (Tablepar)this.get(Tablepar.class,columnpar.getId().getSeq() );
		
		syslog.setStlg("0166");
		syslog.setBrief("修改列名字典：("+columnpar.getId().getColumnname()+")" );
		syslog.setNote("列名字典修改：表名："+columnpar.getTablename()+"["+tablepar.getTablenamechn()+"],列名："+columnpar.getId().getColumnname()+"["+columnpar.getColumnnamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 删除列字典
	 * Describe:
	 * @auth:huangyuqi
	 * @param columnpar
	 * @param syslog
	 * return:void
	 * Date:2011-11-17
	 */
	public void deleteColumnPar(Columnpar columnpar,Syslog syslog){
		
		
		Tablepar tablepar = (Tablepar)this.get(Tablepar.class,columnpar.getId().getSeq() );		
		syslog.setStlg("0167");
		syslog.setBrief("删除列名字典：("+columnpar.getId().getColumnname()+")" );
		syslog.setNote("列名字典删除：表名："+columnpar.getTablename()+"["+tablepar.getTablenamechn()+"],列名："+columnpar.getId().getColumnname()+"["+columnpar.getColumnnamechn()+"]" );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.deleteByKey(Columnpar.class, columnpar.getId());
	}
	/**
	 * 修改时判断表名是否唯一
	 * Describe:只能用作修改，增加时不可用
	 * @auth:huangyuqi
	 * @param tablename
	 * @return
	 * return:boolean
	 * Date:2011-11-18
	 */
	public boolean queryTableIsUse(String tablename){
		String hsql=" from Tablepar where tablename ='"+tablename+"' ";
		
		List list = this.find(hsql);
		if(list!=null && list.size()>0){
			return true;
		}else{		
			return false;
		}
	}
	
	/**
	 * 修改时判断列名是否已经唯一
	 * Describe:只能用作修改，增加时不可用
	 * @auth:huangyuqi
	 * @param tablename
	 * @param columnname
	 * @return
	 * return:boolean
	 * Date:2011-11-18
	 */
	public boolean queryColumnIsUse(Long seq,String columnname){
		String hsql=" from Columnpar where id.seq = "+seq+" and id.columnname <> '"+columnname+"' ";
		List list = this.find(hsql);
		if(list!=null && list.size()>0){
			return true;
		}else{		
			return false;
		}
	}
	

}

