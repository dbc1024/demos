package com.ectrip.ticket.provider.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.provider.dao.ICrowdKindPriceDAO;
import com.ectrip.ticket.provider.service.ICrowdKindPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrowdKindPriceService extends GenericService implements ICrowdKindPriceService {

	
	private ICrowdKindPriceDAO crowdkindpriceDao;
	
	@Autowired
	public void setCrowdkindpriceDao(ICrowdKindPriceDAO crowdkindpriceDao) {
		this.crowdkindpriceDao = crowdkindpriceDao;
		setGenericDao(crowdkindpriceDao);
	}

	/**
	 * 查看产品价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId产品编号
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url访问地址
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-30
	 */
	public PaginationSupport getCrowskindPriceList(Long productId,int page,int pageSize,String url){
		return crowdkindpriceDao.getCrowskindPriceList(productId, page, pageSize, url);
	}
	
	/**
	 * 增加售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmcrowdkindprice
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void insertCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog){
	
		crowdkindpriceDao.insertCrowdKindPirce(edmcrowdkindprice,xpList,syslog);

		
	}
	/**
	 * 修改售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmcrowdkindprice
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void updateCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog){
		crowdkindpriceDao.updateCrowdKindPirce(edmcrowdkindprice,xpList,syslog);
	}
	/**
	 * 删除售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindpriceId售价编号
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void deleteCrowdKindPirce(Long crowdkindpriceId,Syslog syslog){
		
		 crowdkindpriceDao.deleteCrowdKindPirce(crowdkindpriceId,syslog);
	}
	
	/**
	 * 查询价格信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2011-10-4
	 */
	public Edmcrowdkindpricetab queryCrowdKindPrice(Long priceId){
		return crowdkindpriceDao.queryCrowdKindPrice(priceId);
	}
	/**
	 * 得到所有有效价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getPriceList(){
		return crowdkindpriceDao.getPriceList();
	}
	/**
	 * 根据登录人获取景区的产品的价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeid登录人id
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getLimitsPriceList(Long employeeId){
		return crowdkindpriceDao.getLimitsPriceList(employeeId);
	}
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean retriePriceIsuse(Edmcrowdkindpricetab crowdkindprice){
		return crowdkindpriceDao.retriePriceIsuse(crowdkindprice);
	}
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean updatePriceIsuse(Edmcrowdkindpricetab crowdkindprice){
		return crowdkindpriceDao.updatePriceIsuse(crowdkindprice);
	}
	/**
	 * 读出某产品的服务商下与下级服务商的所有票（不含套票）
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId产品id
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyProviderProductList(Long iticketId){
		return crowdkindpriceDao.QureyProviderProductList(iticketId);
	}
	/**
	 * 读出某产品的服务商下与下级服务商的所有票（不含套票），并不含有当前价格
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId 票类型编号
	 * @param priceid 价格编号
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyProviderProductList(Long iticketId,Long priceid){
		return crowdkindpriceDao.QureyProviderProductList(iticketId,priceid);
	}
	/**
	 * 读出产品价格拆分数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyComposePriceList(Long priceId){
		return crowdkindpriceDao.QureyComposePriceList(priceId);
	}
	
	/**
	 * 根据产品编号读出价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId
	 * @return
	 * return:List
	 * Date:2011-10-27
	 */
	public List getpriceListbyprno(Long iticketId){
		return crowdkindpriceDao.getpriceListbyprno(iticketId);
	}

	/**
	 * *
	 * Describe:判断售价编号是否重复
	 * @see com.ectrip.system.provider.service.iservice.ICrowdKindPriceService#getcrowpricecode(java.lang.String)
	 * @param icrowdkindpricecode
	 * @return
	 * @author lijingrui
	 * Date:Nov 7, 2011
	 */
	public boolean getcrowpricecode(String icrowdkindpricecode) {
		return crowdkindpriceDao.getcrowpricecode(icrowdkindpricecode);
	}
	
	/**
	 * *
	 * Describe:判断售价编号是否重复
	 * @see com.ectrip.system.provider.dao.idao.ICrowdKindPriceDAO#getcrowpricecode(java.lang.String)
	 * @param icrowdkindpricecode价格编号
	 * @param iscenicid 服务商编号
	 * @return
	 * @author lijingrui
	 * Date:Nov 7, 2011
	 */
	public boolean getcrowpricecode(String icrowdkindpricecode,Long iscenicid ){
		return crowdkindpriceDao.getcrowpricecode(icrowdkindpricecode, iscenicid);
	}
	
	/**
	 * 判断订单中是否存在数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId
	 * @return
	 * return:boolean
	 * Date:2011-11-24
	 */
	public boolean queryPriceIsUse(Long priceId){
		return crowdkindpriceDao.queryPriceIsUse(priceId);
	}

	/**
	 *
	 * @param ticketId
	 * @return
	 */
	public List<Map> queryImgByticketId(Long ticketId){
		String sql = "select t.itickettypeid,u.upadder,u.upfilename from SECENICPICTURE t,UPFILE u where t.upid=u.upid and t.itickettypeid=? and rownum=1";
		List<Map> resultMap = new ArrayList<Map>();
		try{
			resultMap = crowdkindpriceDao.findBySqlToMap(sql, new Long[]{ticketId});
		}catch (Exception e){
			e.printStackTrace();
		}

		return resultMap;
	}
	
}

