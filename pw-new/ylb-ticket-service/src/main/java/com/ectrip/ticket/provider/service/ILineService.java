package com.ectrip.ticket.provider.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.Linetravel;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.model.provider.PriceModel;
import com.ectrip.ticket.model.provider.Provider;


public interface ILineService extends IGenericService{
	
	public PaginationSupport findLinetravelByTickettypeid(Long itickettypeid,String linename,int page,int pagesize,String url);
	public void saveOrUpdateLinetravel(Linetravel linetravel);
	public Linetravel findLinetravelById(Long linetravelid);
	public void deleteLinetravelById(Long linetravelid);
	public PaginationSupport findLineProductPage(int page,int pagesize,String url,Long iscenicid,String title,String starttime,String to,String from,String bycategorytype) ;
	public Edmtickettypetab findLinedetail(Long itickettypeid) throws ParseException;
	public List<Provider> findLXS(String pdtp);
	public PaginationSupport queryHscomment(String type,String ptype,Long itickettypeid,int page,int pageSize,String url);
	public void saveNetMessage(Netusermessage netMessage);
	public PaginationSupport queryNetusermessage(String status, Long productid,Long iscenicid,int page,int pageSize, String url) ;
	public Edmtickettypetab findEdmtickettypetabAndPic(Long itickettypeid);
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
	public boolean saveCrowdkindprices(Edmcrowdkindpricetab edmcrowdkindpricetab,Map<String, PriceModel> priceModels,Syslog syslog,List<Edmcrowdkindpricetab> list,Lineproduct line);
	public void deleteCrowdKindPirce(Long crowdkindpriceId, Syslog syslog,
			List<Edmcrowdkindpricetab> crowdkindpriceIds);
	
	/**
	 * 目的地自动填充查询
	 * autoFindRegion(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @auth hejiahua
	 * @param likeStr
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-28 下午04:13:10
	 * @since  1.0.0
	 */
	public List autoFindRegion(String likeStr);
	
	public void updateLineOrder(String orid,String ddzt,Long iticketId,Syslog syslog,Orderlog orderlog);
}

