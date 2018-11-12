package com.ectrip.ticket.stocks.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocktab;
import com.ectrip.ticket.stocks.dao.idao.IkcustomproducDao;
import com.ectrip.ticket.stocks.service.iservice.IkcustomproducService;

public class KcustomproducService implements IkcustomproducService {
	private IkcustomproducDao kcustomproducDao;


	public void setKcustomproducDao(IkcustomproducDao kcustomproducDao) {
		this.kcustomproducDao = kcustomproducDao;
	}

	//查询服务商
	public List selectFWS(Esfemployeetab esfemployeetab){
		return kcustomproducDao.selectFWS(esfemployeetab);
	}

	//查询产品列表
	public List selectCP(Esfemployeetab esfemployeetab){
		return kcustomproducDao.selectCP(esfemployeetab);
	}



	//判断数据库是否已经有该段时间内库存
	public List SureCustomKC(Stocktab s, QueryOrder order){
		return  kcustomproducDao.SureCustomKC( s,  order);
	}

	//新增产品库存
	public void addproductKCType(Esfemployeetab esfemployeetab,Syslog syslog,Stocktab stocktab,String productId,QueryOrder order){
		kcustomproducDao.addproductKCType(esfemployeetab,syslog, stocktab,productId,order);
	}

	//新增服务商库存
	public void addfwstKCType(Esfemployeetab esfemployeetab,Syslog syslog, Stocktab stocktab,
							  String fwsId,QueryOrder order){
		kcustomproducDao.addfwstKCType(esfemployeetab,syslog, stocktab, fwsId, order);
	}


	//查询服务商上库存列表
	public PaginationSupport selectProductStockFWS(int pageSize, int startInt,String url, QueryOrder order,String fwsId,String rzyear,String ldyear){
		return kcustomproducDao.selectProductStockFWS(pageSize, startInt, url, order, fwsId,rzyear,ldyear);
	}


	//查询产品库存列表
	public PaginationSupport selectProductStockCP(int pageSize, int startInt,String url, QueryOrder order,String productId,String rzyear,String ldyear){
		return kcustomproducDao.selectProductStockCP(pageSize, startInt, url, order, productId,rzyear,ldyear);
	}
	// 修改/删除/查看库存之前查询改条库存各种信息
	public Stocktab editKcuprodStock(Stocktab s){
		return kcustomproducDao.editKcuprodStock(s);
	}

	// 修改库存之前查询服务商名称或者产品名称
	public String selectfwsOrpro(Stocktab s){
		return kcustomproducDao.selectfwsOrpro(s);
	}

	public String selectfwsOrproT(Stocktab s){
		return kcustomproducDao.selectfwsOrproT(s);
	}
	public void updateKCStock(Esfemployeetab esfemployeetab,Syslog syslog,Stocktab s){
		kcustomproducDao.updateKCStock(esfemployeetab,syslog, s);
	}

	public List SureCustomKCT(Stocktab s){//修改前查询数据库同一个服务商或者产品在一段时间内库存是否重复
		return kcustomproducDao.SureCustomKCT(s);

	}
	public void delKCproductstock(Esfemployeetab esfemployeetab,Syslog syslog,Stocktab s){
		kcustomproducDao.delKCproductstock(esfemployeetab,syslog, s);
	}
}

