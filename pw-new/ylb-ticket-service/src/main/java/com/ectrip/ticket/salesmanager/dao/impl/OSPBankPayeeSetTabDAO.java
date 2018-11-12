package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;
import com.ectrip.ticket.salesmanager.dao.IOSPBankPayeeSetTabDAO;

/**
 * @author  yangyang
 * @version 银行缴款设置操作类
 */
public class OSPBankPayeeSetTabDAO extends GenericDao implements IOSPBankPayeeSetTabDAO {

	public PaginationSupport findPage(int pageSize,int startIndex, String url) {
		String sql="select new map(osp.ibankid as ibankid,osp.szbankcode as szbankcode,sys1.pmva as szbankname,osp.szbankaccount as szbankaccount,osp.byisuse as byisuse,osp.szmemo as szmemo)  from Ospbankpayeesettab osp,Sysparv5 sys1 where osp.szbankname=sys1.id.pmcd and sys1.id.pmky='BANK' ";
		return this.findPage(sql, pageSize, startIndex, url);
	}

	//模糊查询
	public PaginationSupport findPage2(String queryId, String queryMess,
									   int pageSize, int startIndex, String url) {
		String sql="select new map(osp.ibankid as ibankid,osp.szbankcode as szbankcode,sys1.pmva as szbankname,osp.szbankaccount as szbankaccount,osp.byisuse as byisuse,osp.szmemo as szmemo)  from Ospbankpayeesettab osp,Sysparv5 sys1 where osp.szbankname=sys1.id.pmcd and sys1.id.pmky='BANK' ";
		StringBuffer sbf=new StringBuffer(sql);
		if(queryId!=null&&!queryId.equals("")){
			sbf.append(" and osp.ibankid="+queryId);
		}
		if(queryMess!=null&&!queryMess.equals("")){
			sbf.append(" and sys1.pmva like '%"+queryMess+"%'");
		}
		return this.findPage(sbf.toString(), pageSize, startIndex, url);
	}

	//添加新的银行信息
	public void addNewBank(Ospbankpayeesettab bank) {
		if(bank!=null){
			bank.setIbankid(this.getMaxPk("ibankid", "Ospbankpayeesettab")+1);
			bank.setIversion(new Long(1));
			this.save(bank);
		}
	}

	//修改银行信息
	public void updateBankInfo(Ospbankpayeesettab bank) {
		if(bank!=null){
			Ospbankpayeesettab pb=(Ospbankpayeesettab)this.get(Ospbankpayeesettab.class, bank.getIbankid());
			pb.setSzbankname(bank.getSzbankname());
			pb.setSzbankaccount(bank.getSzbankaccount());
			pb.setSzbankcode(bank.getSzbankcode());
			pb.setByisuse(bank.getByisuse());
			pb.setIversion(pb.getIversion());
			pb.setSzmemo(bank.getSzmemo());

			this.update(pb);
		}
	}

	//判断是否有已存在帐号
	public List getCountByCou(String count) {
		String sql=" from Ospbankpayeesettab where szbankaccount='"+count+"'";
		return this.find(sql);
	}

}
