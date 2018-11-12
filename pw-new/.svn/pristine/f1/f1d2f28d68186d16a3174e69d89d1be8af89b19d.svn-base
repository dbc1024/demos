package com.ectrip.ticket.provider.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.PriceModel;
/**
 * 线路产品基础属性dao
 * @author liujianwen
 */
public interface ILineproductDao extends IGenericDao {
	/**
	 * 得到价格
	 * getCrowdkindprices(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-1-17 上午11:07:23
	 * @since  1.0.0
	 */
	public List getCrowdkindprices(Long itickettypeid,String startDate,String endDate);
	/**
	 * 批量保存价格
	 * saveCrowdkindprices(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param edmcrowdkindpricetab
	 * @param priceModels
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2014-1-22 下午02:20:50
	 * @since  1.0.0
	 */
	public boolean saveCrowdkindprices(Edmcrowdkindpricetab edmcrowdkindpricetab,Map<String, PriceModel> priceModels,Syslog syslog,Lineproduct line);
	
	public void deleteCrowdKindPirce(Long crowdkindpriceId,Syslog syslog,List<Edmcrowdkindpricetab> crowdkindpriceIds);
	
	public void updateLineOrder(String orid,String ddzt,Long iticketId,Syslog syslog,Orderlog orderlog);
}
