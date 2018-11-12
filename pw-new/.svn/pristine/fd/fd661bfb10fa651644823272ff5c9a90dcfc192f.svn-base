package com.ectrip.ticket.stocks.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocktab;

public interface IkcustomproducDao extends IGenericDao {

	public List selectFWS(Esfemployeetab esfemployeetab);//查询服务商列表



	public List selectCP(Esfemployeetab esfemployeetab);





	public List SureCustomKC(Stocktab s, QueryOrder order);

	public void addproductKCType(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab stocktab, String productId, QueryOrder Radiobutton2);


	public void addfwstKCType(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab stocktab, String fwsId, QueryOrder order);


	public PaginationSupport selectProductStockFWS(int pageSize, int startInt, String url, QueryOrder order, String fwsId, String rzyear, String ldyear);



	public PaginationSupport selectProductStockCP(int pageSize, int startInt, String url, QueryOrder order, String productId, String rzyear, String ldyear);

	public Stocktab editKcuprodStock(Stocktab s);

	public String selectfwsOrpro(Stocktab s);

	public String selectfwsOrproT(Stocktab s);

	public List SureCustomKCT(Stocktab s);//修改前查询数据库同一个服务商或者产品在一段时间内库存是否重复
	public void updateKCStock(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab s);

	public void delKCproductstock(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab s);//删除库存
}

