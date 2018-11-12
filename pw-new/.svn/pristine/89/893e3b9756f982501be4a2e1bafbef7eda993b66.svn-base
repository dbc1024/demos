package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Bankorder;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IBankOrderSetDAO;

@Repository
public class BankOrderSetDAO extends GenericDao implements IBankOrderSetDAO {
	
	/**
	 * 获取银行参数列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-3-28
	 */
	public List queryBankList(){
		List list = new ArrayList();
		String hsql ="select new map(s.id.pmcd as pmcd ,s.pmva as pmva) from Sysparv5 s where s.id.pmky='BANK' and s.id.pmcd not like '%*%' and s.id.pmcd not in(select bankid from Bankorder )";
		list = this.find(hsql);
		return list;
	}

	/**
	 * 获取银行排序列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-28
	 */
	public PaginationSupport queryBankOrderList(int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(b.id.rwid as rwid,b.id.colid as colid,b.bankid as bankid,b.bankname as bankname) from Bankorder b  order by b.id.rwid,b.id.colid");
		
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}
	
	/**
	 * 增加银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void addBankOrder(Bankorder bankorder,Syslog syslog){
		
		this.save(bankorder);
		
		syslog.setStlg("0201");
		syslog.setBrief("银行排序管理：" + bankorder.getId().getRwid()+"["+bankorder.getId().getColid()+"]");
		syslog.setNote("银行排序管理增加：" +"第"+ bankorder.getId().getRwid()+"行,第"+ bankorder.getId().getColid()+"列,银行编号"+bankorder.getBankid()+",银行名称"+bankorder.getBankname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
				
	}
	/**
	 * 修改银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void updateBankOrder(Bankorder bankorder,Syslog syslog){
		
		
		
		String hsql = "from Bankorder where bankid = '"+bankorder.getBankid()+"'";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			Bankorder bank = (Bankorder)list.get(0);
			this.delete(bank);
		}	
		this.save(bankorder);
		
		syslog.setStlg("0202");
		syslog.setBrief("银行排序管理：" + bankorder.getId().getRwid()+"["+bankorder.getId().getColid()+"]");
		syslog.setNote("银行排序管理修改：" +"第"+ bankorder.getId().getRwid()+"行,第"+ bankorder.getId().getColid()+"列,银行编号"+bankorder.getBankid()+",银行名称"+bankorder.getBankname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * 删除银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankorder
	 * return:void
	 * Date:2012-3-28
	 */
	public void deleteBankOrder(Bankorder bankorder,Syslog syslog){
		this.delete(bankorder);
		
		syslog.setStlg("0203");
		syslog.setBrief("银行排序管理：" + bankorder.getId().getRwid()+"["+bankorder.getId().getColid()+"]");
		syslog.setNote("银行排序管理删除：" +"第"+ bankorder.getId().getRwid()+"行,第"+ bankorder.getId().getColid()+"列,银行编号"+bankorder.getBankid()+",银行名称"+bankorder.getBankname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * 查看银行排序管理
	 * Describe:
	 * @auth:huangyuqi
	 * @param bankid
	 * return:void
	 * Date:2012-3-28
	 */
	public Bankorder getBankOrder(String bankid){
		Bankorder bankorder = null;
		String hsql = "from Bankorder where bankid = '"+bankid+"'";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			bankorder = (Bankorder)list.get(0);
		}
		return bankorder;
	}
	
	/**
	 * 判断银行排序管理是事已经存在
	 * Describe:
	 * @auth:huangyuqi
	 * @param rowid行号
	 * @param colsid列号
	 * return:void
	 * Date:2012-3-28
	 */
	public boolean queryBankOrderIsUse(Long rowid,Long colsid){
		boolean isuse = false;
		String hsql = " from Bankorder where id.rwid = "+rowid+" and id.colid="+colsid;
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			isuse = true;
		}
		return isuse;
	}

}

