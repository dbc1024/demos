package com.ectrip.ec.home.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import java.util.List;

/**
 * 
* @ClassName: IHomeDao 
* @Description: 首页相关-数据库连接接口类
* @author Dicky
* @date 2012-2-14 下午04:19:21 
*
 */
public interface IHomeDao extends IGenericDao {
    public PaginationSupport getProductViewList(String keyWord,int pageSize, int startIndex, String url);  
    public PaginationSupport getHotelRecommend(int pageSize,int startIndex,String url);
    public PaginationSupport getTravelAgency(int pageSize,int startIndex,String url);
    public PaginationSupport getScenices(int pageSize,int startIndex,String url);
    public PaginationSupport getAttractions(String rootid,int pageSize,int startIndex,String url);
    public List getHotelRecommend(String topSum,Long ibusinessid);
    public List getHotelRecommendIndex(String topSum,Long ibusinessid,String hotelIds);
    public List getHotelPictures();
    public List getTravelAgency(String topSum);
    public List getAttractions(String rootid);
    public List getScenices(String topSum); 
    public List  getTypeScenices(String topSum,String type); 
    public List getAllCategory(String scenictype,String isjd);
    public List getAllCategoryOfHotel();
    public List getHotelStars();
    public Edmtickettypetab getEdmtickettypetab(String id);
    public Esbscenicareatab getEsbscenicareatab(String id);
    public List getTopTravelLine(int topnum,int daynum);
    public List getTopCommnet(int topnum);
    public List getHotelPictures(String iscenicid,String itickettypeid,int topnum);
    public List getTopGoods(int top);
    public List getGoodsType(String spmcd,String pmvb,String pmcd,String pmky);
    
    public List getHotelProvider(List scenicsList,boolean isHqyatu);
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
