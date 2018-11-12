package com.ectrip.sys.other.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.other.dao.idao.IOrderEditDAO;
import com.ectrip.sys.other.service.iservice.IOrderEditService;

public class OrderEditService implements IOrderEditService {

	IOrderEditDAO ordereditDao;

	public IOrderEditDAO getOrdereditDao() {
		return ordereditDao;
	}

	public void setOrdereditDao(IOrderEditDAO ordereditDao) {
		this.ordereditDao = ordereditDao;
	}
	
	
	
	public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
		return ordereditDao.queryAllOrder(esfemployeetab, order, page, pageSize, url);
	}
	
	
	public int updateOrderStatus(String orid,Long empid,String zffs,String bank,String zfusid) throws Exception{
		String hql1=" from TOrder where id.orid='"+orid+"'";
		String hql2=" from YOrder where id.orid='"+orid+"'";
		try{
			MOrder morder=(MOrder) ordereditDao.get(MOrder.class, orid);
			morder.setZfusid(zfusid);
			morder.setNotea("02");
			morder.setIsa(0l);
			morder.setDdzt("02");
			morder.setBank(bank);
			morder.setPayorid("");
			morder.setZffs(zffs);
			morder.setBankdata(Tools.getDays());
			morder.setBanktime(Tools.getNowTime());
			morder.setZfmont(morder.getMont());
			ordereditDao.update(morder);
			List list=ordereditDao.find(hql1);//torder
			TOrder torder=null;
			for(int i=0;i<list.size();i++){
				torder=(TOrder) list.get(i);
				torder.setDdzt("02");
				morder.setNotea("02");
				morder.setIsa(0l);
				torder.setZfmont(torder.getMont()-torder.getYhamnt());
				ordereditDao.update(torder);
			}
			List list1=ordereditDao.find(hql2);//yorder
			YOrder yorder=null;
			for(int j=0;j<list1.size();j++){
				yorder=(YOrder) list1.get(j);
				yorder.setDdzt("02");
				morder.setNotea("02");
				morder.setIsa(0l);
				yorder.setZfmont(torder.getMont()-torder.getYhamnt());
				ordereditDao.update(yorder);
			}
			Orderlog log = new Orderlog();
			log.setLogid(ordereditDao.getMaxPk("logid", "Orderlog") + 1);
			log.setOrid(orid);
			log.setStlg("0176");
			log.setBrief(empid+"�޸Ķ���"+orid+"״̬Ϊ��֧��");
			log.setNote(empid+"�޸Ķ���"+orid+"״̬Ϊ��֧��");
			log.setIemployeeid(empid);
			log.setUsid(morder.getUsid());
			log.setLogtype(Long.parseLong("0"));
			log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
			ordereditDao.save(log);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public List findOneList(String pmky, String spmcd) {
		return ordereditDao.findOneList(pmky, spmcd);
	}
	
}
