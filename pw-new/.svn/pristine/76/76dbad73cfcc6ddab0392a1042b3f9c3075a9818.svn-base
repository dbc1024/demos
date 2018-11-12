package com.ectrip.sys.bank.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.sys.bank.dao.idao.IBankDAO;

@Repository
public class BankDAO extends GenericDao implements IBankDAO{
	
	public int updateOrderStatus(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String usid,String note) throws Exception{
		String hql1=" from TOrder where id.orid='"+orid+"'";
		String hql2=" from YOrder where id.orid='"+orid+"'";
		try{
			Long isa=null;
			if(note.equals("1")){
				isa=1l;
			}
			MOrder morder=(MOrder) this.get(MOrder.class, orid);
			morder.setZfusid(usid);
			morder.setNotea(ddzt);
			morder.setIsa(isa);
			morder.setDdzt(ddzt);
			morder.setBank(bank);
			morder.setPayorid(payid);
			morder.setZffs(zffs);
			morder.setBankdata(Tools.getDays());
			morder.setBanktime(Tools.getNowTime());
			morder.setZfmont(Double.parseDouble(mont));
			this.update(morder);
			List list=this.find(hql1);//torder
			TOrder torder=null;
			for(int i=0;i<list.size();i++){
				torder=(TOrder) list.get(i);
				torder.setDdzt(ddzt);
				morder.setNotea(ddzt);
				morder.setIsa(isa);
				torder.setZfmont(torder.getMont()-torder.getYhamnt());
				this.update(torder);
			}
			List list1=this.find(hql2);//yorder
			YOrder yorder=null;
			for(int j=0;j<list1.size();j++){
				yorder=(YOrder) list1.get(j);
				yorder.setDdzt(ddzt);
				morder.setNotea(ddzt);
				morder.setIsa(isa);
				torder=(TOrder)this.get(TOrder.class, new TOrderId(yorder.getId().getOrid(),yorder.getId().getIscenicid()));
				yorder.setZfmont(torder.getMont()-torder.getYhamnt());
				this.update(yorder);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	/**
	 * ΢�Ŵ��������ӿ�ר�÷���
	 *
	 * @author: luoxin
	 * @date: 2014-09-22
	 */
	public int updateOrderStatusWx(String orid,String payid,String mont, String bank, int isok, String ddzt,
			String orderType,String zffs,String usid,String note) throws Exception{
		String hql1=" from TOrder where id.orid='"+orid+"'";
		String hql2=" from YOrder where id.orid='"+orid+"'";
		try{
			Long isa=null;
			if(note.equals("1")){
				isa=1l;
			}
			MOrder morder=(MOrder) this.get(MOrder.class, orid);
			morder.setZfusid(usid);
			morder.setNotea(ddzt);
			morder.setIsa(isa);
			morder.setDdzt(ddzt);
			morder.setBank(bank);
			morder.setPayorid(payid);
			morder.setZffs(zffs);
			morder.setBankdata(Tools.getDays());
			morder.setBanktime(Tools.getNowTime());
			morder.setZfmont(Double.valueOf(Double.parseDouble(mont) - morder.getYhamnt().doubleValue()));
			this.update(morder);
			List list=this.find(hql1);//torder
			TOrder torder=null;
			for(int i=0;i<list.size();i++){
				torder=(TOrder) list.get(i);
				torder.setDdzt(ddzt);
				if(torder.getNotea()==null || "".equals(torder.getNotea()) && "02".equals(torder.getOrfl())){
					if(torder.getOrhm()!=null && !"".equals(torder.getOrhm()) && torder.getOrhm().length()==18){
						torder.setNotea(torder.getOrhm().substring(6, 14));
					}else{
						torder.setNotea("00000000");
					}
				}
				//���º� 2013-12-20 ��Ϊ�Ƶ궨��֧��ʱ��֧�����!=�ܽ��-�Żݽ�� 
				//torder.setZfmont(torder.getMont()-torder.getYhamnt());
				this.update(torder);
			}
			List list1=this.find(hql2);//yorder
			YOrder yorder=null;
			for(int j=0;j<list1.size();j++){
				yorder=(YOrder) list1.get(j);
				yorder.setDdzt(ddzt);
				//���º� 2013-12-20 ��Ϊ�Ƶ궨��֧��ʱ��֧�����!=�ܽ��-�Żݽ�� 
				//yorder.setZfmont(torder.getMont()-torder.getYhamnt());
				this.update(yorder);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
}

