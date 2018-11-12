package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;

public interface ICrowdKindPriceDAO extends IGenericDao {
	
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
	public PaginationSupport getCrowskindPriceList(Long productId,int page,int pageSize,String url);
	/**
	 * 查询价格信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2011-10-4
	 */
	public Edmcrowdkindpricetab queryCrowdKindPrice(Long priceId);
	/**
	 * 增加价格
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmcrowdkindprice
	 * @param xpList
	 * @param syslog
	 * return:void
	 * Date:2011-10-11
	 */
	public  void insertCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog);
	/**
	 * 修改售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmcrowdkindprice
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void updateCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog);
	/**
	 * 删除售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindpriceId售价编号
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void deleteCrowdKindPirce(Long crowdkindpriceId,Syslog syslog);
	
	
	/**
	 * 得到所有有效价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getPriceList();
	/**
	 * 根据登录人获取景区的产品的价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeid登录人id
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getLimitsPriceList(Long employeeId);
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param  crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean retriePriceIsuse(Edmcrowdkindpricetab crowdkindprice);
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean updatePriceIsuse(Edmcrowdkindpricetab crowdkindprice);
	
	/**
	 * 读出某产品的服务商下与下级服务商的所有票（不含套票）
	 * Describe:
	 * @auth:huangyuqi
	 *  @param iticketId产品id
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyProviderProductList(Long iticketId);
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
	public List QureyProviderProductList(Long iticketId,Long priceid);
	/**
	 * 读出产品价格拆分数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyComposePriceList(Long priceId);
	/**
	 * 根据产品编号读出价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId
	 * @return
	 * return:List
	 * Date:2011-10-27
	 */
	public List getpriceListbyprno(Long iticketId);
	
	/**
	 * 
	 * Describe:判断售价编号是否重复
	 * @auth:lijingrui
	 * @param icrowdkindpricecode
	 * @return
	 * return:boolean
	 * Date:Nov 7, 2011
	 */
	public boolean getcrowpricecode(String icrowdkindpricecode);
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
	public boolean getcrowpricecode(String icrowdkindpricecode,Long iscenicid );
	/**
	 * 判断订单中是否存在数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId
	 * @return
	 * return:boolean
	 * Date:2011-11-24
	 */
	public boolean queryPriceIsUse(Long priceId);
}

