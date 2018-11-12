package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Linetravel;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.model.provider.Provider;
import com.ectrip.upload.model.Upfile;

public interface ILinetravelDao extends IGenericDao{
	
	/**
	 * 根据id查找行程
	 * Describe:
	 * @author liujianwen
	 * @return
	 * return:List<Linetravel>
	 * Date:2012-7-2
	 */
	public List<Linetravel> findByTickettypeid(Long itickettypeid);
	public PaginationSupport findLinetravelByTickettypeid(Long itickettypeid,String linename,int page,int pagesize,String url);
	public Linetravel findLinetravelById(Long linetravelid);
	public void delteLinetravelById(Long linetravelid);
	public void deleteLinetravelpicByLinetravelid(Long linetravelid);
//	public PaginationSupport findLineprice(int page,int pagesize,String url,String title,String starttime,String to,String from);
	public List<Upfile> findLinerPic(Long itickettypeid);
	public List findLinetravelpicByLinetravelid(Long linetravelid);
	public List<Edmcrowdkindpricetab> findLinePriceByItickettypeid(Long itickettypeid);
	public PaginationSupport findLineProductPage(int page,int pagesize,String url,Long iscenicid,String title,String starttime,String to,String from,String bycategorytype);
	public List<Provider> findLXS(String pdtp);
	public PaginationSupport queryHscomment(String type,String ptype,Long itickettypeid, int page,
			int pageSize, String url);
	public void saveNetMessage(Netusermessage message);
	public PaginationSupport queryNetusermessage(String status, Long productid,Long iscenicid,int page,
			int pageSize, String url) ;
	public List findCountHscomment(String status,String ptype,Long oeid);
	public List findAvgHscomment(String status,String ptype,Long oeid);
}

