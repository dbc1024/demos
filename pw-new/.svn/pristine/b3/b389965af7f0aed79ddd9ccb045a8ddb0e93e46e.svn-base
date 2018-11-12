package com.ectrip.ec.home.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.home.GoodsCategory;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import java.util.List;

/**
 * 
* @ClassName: IHomeService 
* @Description: 首页相关- 业务处理接口类
* @author Dicky
* @date 2012-2-14 下午04:19:58 
*
 */
public interface IHomeService extends IGenericService {
	public PaginationSupport getProductViewList(String keyWord, int pageSize,
			int startIndex, String url);
	public PaginationSupport getHotelRecommend(int pageSize, int startIndex,
			String url) ;
	public PaginationSupport getTravelAgency(int pageSize, int startIndex,
			String url);
	public PaginationSupport getScenices(int pageSize, int startIndex,
			String url);
	public PaginationSupport getAttractions(String rootid, int pageSize,
			int startIndex, String url) ;
	public List getHotelRecomment(String topSum,Long ibusinessid);
	/**
	 * 首页推荐酒店
	 * @return
	 */
	public List getHotelRecommentIndex(String topSum,Long ibusinessid,String hotelIds);
	public List getHotelPicture();
	public List getTravelAgency(String topSum);
	public List getAttractions(String rootid);
	public List getScenices(String topSum);
	public List  getTypeScenices(String topSum,String type); 
	public List getAllCategory(String scenictype, String isjd);
	public List getAllCategoryOfHotel();
	public List getHotelStars();
	public Edmtickettypetab getEdmtickettypetab(String id);
	public Esbscenicareatab getEsbscenicareatab(String id);
	public List getTopTravelLine(int topnum,int daynum);
	public List getTopCommnet(int topnum);
	public List<GoodsCategory> getTopLevel();
	public List getTopGoods(String topSum);
	/**
	 * 根据域名获取企业信息
	 * @param url
	 * @return
	 */
	public List<Domain> getDomain(String url,String type);
	
	public List getHotelProvider(List scenics,boolean isHqyatu);
	
	/**
	 * 
	 * Describe:推荐
	 * @author:lijingrui
	 * @param topSum
	 * @param pdtp
	 * @param ibusinessid
	 * @return
	 * return:List
	 * Date:2014-8-23
	 */
	public List searchListScenicid(String topSum,String pdtp,Long ibusinessid);
	
}
