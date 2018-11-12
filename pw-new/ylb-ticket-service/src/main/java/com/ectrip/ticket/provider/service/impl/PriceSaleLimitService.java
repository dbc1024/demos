package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.IPriceSaleLimitDao;
import com.ectrip.ticket.provider.service.IPriceSaleLimitService;
@Service
public class PriceSaleLimitService extends GenericService implements
		IPriceSaleLimitService {
	private IPriceSaleLimitDao priceSaleLimitDao;

	public IPriceSaleLimitDao getPriceSaleLimitDao() {
		return priceSaleLimitDao;
	}

	public void setPriceSaleLimitDao(IPriceSaleLimitDao priceSaleLimitDao) {
		this.priceSaleLimitDao = priceSaleLimitDao;
	}
	
	/**
	 * 
	 * Describe:根据价格ID查看产品信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * @throws Exception
	 * return:Edmcrowdkindpricetab
	 * Date:2013-7-23
	 */
	public Edmcrowdkindpricetab findprice(Long icrowdkindpriceid) throws Exception{
		return this.priceSaleLimitDao.findprice(icrowdkindpriceid);
	}
	/**
	 * 
	 * Describe:根据价格ID查找销售权限信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List querySaleLimitList(Long icrowdkindpriceid){
		return this.priceSaleLimitDao.querySaleLimitList(icrowdkindpriceid);
	}
	/**
	 * 
	 * Describe:保存员工销售权限
	 * @author:chenxinhao
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2013-7-23
	 */
	public void insertSaleLimit(Ospsaleslimitstab salelimits,Syslog syslog){
		this.priceSaleLimitDao.insertSaleLimit(salelimits, syslog);
	}
	/**
	 * 
	 * Describe:获取服务商列表
	 * @author:chenxinhao
	 * @param scenics	服务商编号，以,隔开，例如1,2,3
	 * @param scenictype	服务商类别
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List sceniclist(String scenics, String scenictype){
		return this.priceSaleLimitDao.sceniclist(scenics, scenictype);
	}
	/**
	 * 
	 * Describe:根据服务商获取窗口列表
	 * @author:chenxinhao
	 * @param iscenics
	 * @return
	 * @throws Exception
	 * return:List
	 * Date:2013-7-23
	 */
	public List queryWinList(String iscenics) throws Exception{
		return this.priceSaleLimitDao.queryWinList(iscenics);
	}
	/**
	 * 
	 * Describe:根据价格ID查找窗口销售权限信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List queryWinLimitList(Long icrowdkindpriceid){
		return this.priceSaleLimitDao.queryWinLimitList(icrowdkindpriceid);
	}
	/**
	 * 
	 * Describe:保存窗口销售权限
	 * @author:chenxinhao
	 * @param winlimits
	 * @param syslog
	 * return:void
	 * Date:2013-7-23
	 */
	public void insertWinLimit(Ospticketwinlimitstab winlimits,Syslog syslog){
		this.priceSaleLimitDao.insertWinLimit(winlimits, syslog);
	}
}

