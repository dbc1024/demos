package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Receiptmanager;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.IReceiptManagerDao;

public class ReceiptManagerDao extends GenericDao implements IReceiptManagerDao {

	public List sceniclist(String scenics, String scenictype) {
		List sceniclist = new ArrayList();
		String hsql = "";
		if (scenics != null && !scenics.equals("")) {
			hsql = "from Esbscenicareatab where iscenicid in (" + scenics
					+ ") and scenictype ='" + scenictype + "'";

		} else {
			hsql = "from Esbscenicareatab where  scenictype ='" + scenictype
					+ "' order by iscenicid";

		}

		sceniclist = this.find(hsql);
		return sceniclist;
	}
	
	public PaginationSupport showReceiptManagerList(Long iscenicid,int pageSize, int startIndex, String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(rm.seq as seq,rm.iscenicid as iscenicid,rm.printno as printno,rm.contenttype as contenttype,rm.printtype as printtype,rm.content as content,rm.colnum as colnum,rm.ordernum as ordernum,es.szscenicname as szscenicname,sys1.pmva as printnoname,sys2.pmva as contenttypename,sys3.pmva as printtypename) from Receiptmanager rm,Esbscenicareatab es,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 ");
		hsql.append(" where sys1.id.pmky='FPSZ' and sys1.id.pmcd=rm.printno and sys2.id.pmky='FPCC' and sys2.id.pmcd=rm.contenttype and sys3.id.pmky='FPPC' and sys3.id.pmcd=rm.printtype ");
		hsql.append(" and rm.iscenicid=es.iscenicid ");
		if(iscenicid!=null&&iscenicid!=0){
			hsql.append(" and rm.iscenicid="+iscenicid);
		}
		hsql.append(" order by rm.ordernum ");
		System.out.println(hsql.toString());
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}
	
	public List showPrintnoList(){
		return this.find("from Sysparv5 where id.pmky='FPSZ' and spmcd = '****' and isvalue = 1");
	}
	
	public List showContenttypeList(){
		return this.find("from Sysparv5 where id.pmky='FPCC' and spmcd = '****' and isvalue = 1");
	}
	
	public List showPrinttypeList(){
		return this.find("from Sysparv5 where id.pmky='FPPC' and spmcd = '****' and isvalue = 1");
	}
	
	public List<Receiptmanager> receiptManagerList(Long iscenicid) throws Exception{
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(rm.seq as seq,rm.iscenicid as iscenicid,rm.printno as printno,rm.contenttype as contenttype,rm.printtype as printtype,rm.content as content,rm.colnum as colnum,rm.ordernum as ordernum,es.szscenicname as szscenicname,sys1.pmva as printnoname,sys2.pmva as contenttypename,sys3.pmva as printtypename,sys1.isb as isb) from Receiptmanager rm,Esbscenicareatab es,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 ");
		hsql.append(" where sys1.id.pmky='FPSZ' and sys1.id.pmcd=rm.printno and sys2.id.pmky='FPCC' and sys2.id.pmcd=rm.contenttype and sys3.id.pmky='FPPC' and sys3.id.pmcd=rm.printtype ");
		hsql.append(" and rm.iscenicid=es.iscenicid ");
		if(iscenicid!=null&&iscenicid!=0){
			hsql.append(" and rm.iscenicid="+iscenicid);
		}
		hsql.append(" order by rm.ordernum ");
		List list = this.find(hsql.toString());
		List<Receiptmanager> receiptList = new ArrayList<Receiptmanager>();
		if(list==null||list.isEmpty()){
			return null;
		}else{
			for(int i=0;i<list.size();i++){
				Receiptmanager receipt = new Receiptmanager();
				BeanUtils.populate(receipt, (Map) list.get(i));
				receiptList.add(receipt);
			}
		}
		return receiptList;
	}
	
	public Receiptmanager findReceiptManager(Long seq) throws Exception{
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(rm.seq as seq,rm.iscenicid as iscenicid,rm.printno as printno,rm.contenttype as contenttype,rm.printtype as printtype,rm.content as content,rm.colnum as colnum,rm.ordernum as ordernum,es.szscenicname as szscenicname,sys1.pmva as printnoname,sys2.pmva as contenttypename,sys3.pmva as printtypename,sys1.isb as isb) from Receiptmanager rm,Esbscenicareatab es,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 ");
		hsql.append(" where sys1.id.pmky='FPSZ' and sys1.id.pmcd=rm.printno and sys2.id.pmky='FPCC' and sys2.id.pmcd=rm.contenttype and sys3.id.pmky='FPPC' and sys3.id.pmcd=rm.printtype ");
		hsql.append(" and rm.iscenicid=es.iscenicid ");
		if(seq!=null&&seq!=0){
			hsql.append(" and rm.seq="+seq);
		}
		hsql.append(" order by rm.ordernum ");
		List list = this.find(hsql.toString());
		Receiptmanager receipt = new Receiptmanager();
		if(list==null||list.isEmpty()){
			return null;
		}else{
			BeanUtils.populate(receipt, (Map) list.get(0));
		}
		return receipt;
	}
	
	public void addReceiptManager(Receiptmanager receipt,Syslog syslog){
		if(receipt!=null){
			Long maxid = this.getMaxPk("seq", "Receiptmanager");
			if(maxid==0){
				maxid = 1L;
			}else{
				maxid+=1L;
			}
			receipt.setSeq(maxid);
			this.save(receipt);
		}
	}
	
	public void updateReceiptManager(List<Receiptmanager> lst,Receiptmanager receiptmanager,Syslog syslog){
		if(lst!=null&&!lst.isEmpty()){
			for(int i=0;i<lst.size();i++){
				Receiptmanager receipt = lst.get(i);
				Long maxid = this.getMaxPk("seq", "Receiptmanager");
				receipt.setSeq(maxid+1L);
				receipt.setIscenicid(receiptmanager.getIscenicid());
				receipt.setPrintno(receipt.getPrintno().split("&")[0]);
				this.save(receipt);
			}
		}
	}
	
	public void deleteReceiptManager(Long seq,Syslog syslog){
		Receiptmanager receiptmanager = (Receiptmanager) this.get(Receiptmanager.class, seq);
		if(receiptmanager!=null){
			this.delete(receiptmanager);
		}
	}
}

