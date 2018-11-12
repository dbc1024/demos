package com.ectrip.ticket.stocks.dao.idao;


import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.stock.Stocksticketwarebycustomtab;
import com.ectrip.ticket.model.stock.Stocksticketwaretab;

public interface IStocksProductDao {

	/**
	 * 说明: 获取所有产品库存信息列表
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return PaginationSupport
	 * @author zengzhe
	 * date:2013-08-26
	 */
	PaginationSupport initInfo(int pageSize, int startInt, String url);


	/**
	 * 说明: 产品库存模块   增加库存
	 * @param stocksticketwaretab
	 * @param syslog
	 * @return void
	 * @author zengzhe
	 * date:2013-08-26
	 */
	void addProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog);

	/**
	 * 说明: 产品库存模块  删除库存
	 * @param stocksticketwaretab
	 * @param syslog
	 * @param seq
	 * @return void
	 * @author zengzhe
	 * date:2013-08-26
	 */
	void delProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog);

	/**
	 * 说明 :产品库存模块  更新库存
	 * @param seq
	 * @return Stocksticketwaretab
	 * @author zengzhe
	 * date:2013-08-26
	 */
	void updateProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog);

	/**
	 * 说明： 产品库存模块  根据条件查询库存(服务商)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return PaginationSupport
	 * @author zengzhe
	 * date:2013-08-26
	 */
	PaginationSupport selectProductStockFF(int pageSize, int startInt,String url, String strNameF);


	/**
	 * 说明： 产品库存模块  根据条件查询库存(产品)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return PaginationSupport
	 * @author zengzhe
	 * date:2013-08-26
	 */
	PaginationSupport selectProductStockCC(int pageSize, int startInt,String url, String strNameC);


	/**
	 * 说明：在新增产品库存时，判断是否已经存在
	 * @param seq
	 * @return boolean
	 * @author zengzhe
	 * date:2013-08-26
	 */
	boolean checkProductStockIsExist(Long pid,long obitype);


	/**
	 * 说明:判断在新增产品库存的时候，要么是服务商库存要么是产品库存
	 * @return boolean
	 * @author zengzhe
	 */
	public boolean checkProductStockF(long pid);


	/**
	 * 说明:判断在新增产品库存的时候，要么是服务商库存要么是产品库存
	 * @return boolean
	 * @author zengzhe
	 */
	public boolean checkProductStockC(long pid);


	/**
	 * 说明: 选择一条数据进行修改或者删除
	 * @param seq
	 * @return Stocksticketwaretab
	 * @author zengzhe
	 */
	Stocksticketwaretab alertProductStock(Long seq);

	/**
	 * 说明：指定查询语句（sql）来返回服务商表的主键
	 * @param sql
	 * @return list
	 * @author zengzhe
	 */
	List selectProductStockForF(String sql);


	/**
	 * 说明：指定查询语句（sql）来返回产品表的主键
	 * @param sql
	 * @return list
	 * @author zengzhe
	 */
	List selectProductStockForC(String sql);

	/**
	 *
	 * Describe:查询出所有客户库存表
	 * @author:huxingwei
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-12-17
	 */
	PaginationSupport getAllStockProductCustom(int pageSize, int startInt, String url);

	/**
	 *
	 * Describe:查询所有旅行社分社
	 * @author:huxingwei
	 * @return
	 * return:List
	 * Date:2014-12-17
	 */
	public PaginationSupport getAllLxsfs(String query,int pageSize,int startIndex,String url);


	/**
	 *
	 * Describe:客户库存增加
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	void addCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog);

	/**
	 *
	 * Describe:客户库存删除
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	void delCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog);

	/**
	 *
	 * Describe:客户库存修改
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	void updateCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog);

	/**
	 *
	 * Describe:判断用户库存是否存在，当是天库存时，需判断日期段
	 * @author:lijingrui
	 * @param stock
	 * @param type
	 * @return
	 * return:boolean
	 * Date:2014-12-19
	 */
	public String checkCustomProductStockIsExist(Stocksticketwarebycustomtab stock,String type );

	/**
	 *
	 * Describe:根据id查询客户库存信息
	 * @author:huxingwei
	 * @param seq
	 * @return
	 * return:Stocksticketwaretab
	 * Date:2014-12-18
	 */
	public Stocksticketwarebycustomtab getCustomProductStockById(Long seq);


	public PaginationSupport selectCustomProductStockFF(int pageSize, int startInt,String url, Long strNameF,String objtype,String corpname) ;

}
