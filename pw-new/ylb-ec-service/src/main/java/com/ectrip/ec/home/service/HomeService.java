package com.ectrip.ec.home.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.home.dao.idao.IHomeDao;
import com.ectrip.ec.home.service.iservice.IHomeService;
import com.ectrip.ec.model.home.GoodsCategory;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

/**
 * 
 * @ClassName: HomeService
 * @Description: 首页相关- 业务处理接口实现类
 * @author Dicky
 * @date 2012-2-14 下午04:20:17
 * 
 */
@Service
public class HomeService extends GenericService implements IHomeService {

	public IHomeDao homeDao;
    @Autowired
    public void setHomeDao(IHomeDao homeDao) {
    	this.homeDao = homeDao;
    	super.setGenericDao(homeDao);
    }
	
	@Autowired
    private SysparService sysService;

    /**
     * (非 Javadoc)
     * <p>
     * Title: getProductViewList
     * </p>
     * <p>
     * Description: 网站首页搜索功能
     * </p>
     * 
     * @param keyWord
     *            搜索关键字
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getProductViewList(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getProductViewList(String keyWord, int pageSize, int startIndex, String url) {
	return homeDao.getProductViewList(keyWord, pageSize, startIndex, url);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getHotelRecomment
     * </p>
     * <p>
     * Description: 酒店推荐
     * </p>
     * 
     * @param topSum
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getHotelRecomment(java.lang.String)
     */
    public List getHotelRecomment(String topSum,Long ibusinessid) {
	List list = homeDao.getHotelRecommend(topSum,ibusinessid);
	if (list != null && list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		Map map = (Map) list.get(i);
		List plist = homeDao.getHotelPictures(map.get("iscenicid").toString(), "", 1);
		if(plist!=null&&plist.size()>0){
		    Map pmap = (Map) plist.get(0);
		    map.put("picaddress", pmap.get("upadder").toString());
		    map.put("picname",pmap.get("upfilename").toString());
		}else{
		    map.put("picname","");
		    map.put("picaddress", "");
		}
	    }
	}
	return list;
    }
    
    public List getHotelRecommentIndex(String topSum,Long ibusinessid,String hotelIds) {
	List list = homeDao.getHotelRecommendIndex(topSum,ibusinessid,hotelIds);
	if (list != null && list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		Map map = (Map) list.get(i);
		List plist = homeDao.getHotelPictures(map.get("iscenicid").toString(), "", 1);
		if(plist!=null&&plist.size()>0){
		    Map pmap = (Map) plist.get(0);
		    map.put("picaddress", pmap.get("upadder").toString());
		    map.put("picname",pmap.get("upfilename").toString());
		}else{
		    map.put("picname","");
		    map.put("picaddress", "");
		}
	    }
	}
	return list;
    }
    
    /**
     * *
     * Describe:推荐
     * @see com.ectrip.home.service.iservice.IHomeService#searchListScenicid(java.lang.String, java.lang.String, java.lang.Long)
     * @param topSum
     * @param pdtp
     * @param ibusinessid
     * @return
     * @author lijingrui
     * Date:2014-8-23
     */
	public List searchListScenicid(String topSum,String pdtp,Long ibusinessid){
		List list = homeDao.searchListScenicid(topSum,pdtp,ibusinessid);
		if (list != null && list.size() > 0) {
		    for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			List plist = homeDao.getHotelPictures(map.get("iscenicid").toString(), "", 1);
			if(plist!=null&&plist.size()>0){
			    Map pmap = (Map) plist.get(0);
			    map.put("picaddress", pmap.get("upadder").toString());
			    map.put("picname",pmap.get("upfilename").toString());
			}else{
			    map.put("picname","");
			    map.put("picaddress", "");
			}
		    }
		}
		return list;
	    
	}

    /**
     * (非 Javadoc)
     * <p>
     * Title: getHotelPicture
     * </p>
     * <p>
     * Description: 获取所有服务商图片
     * </p>
     * 
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getHotelPicture()
     */
    public List getHotelPicture() {
	return homeDao.getHotelPictures();
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getTravelAgency
     * </p>
     * <p>
     * Description: 获取推荐旅行社
     * </p>
     * 
     * @param topSum
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getTravelAgency(java.lang.String)
     */
    public List getTravelAgency(String topSum) {
	return homeDao.getTravelAgency(topSum);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getAttractions
     * </p>
     * <p>
     * Description: 武夷山景点
     * </p>
     * 
     * @param topSum
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getAttractions(java.lang.String)
     */
    public List getAttractions(String rootid) {
	return homeDao.getAttractions(rootid);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getScenices
     * </p>
     * <p>
     * Description: 武夷山景区
     * </p>
     * 
     * @param topSum
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getScenices(java.lang.String)
     */
    public List getScenices(String topSum) {
	return homeDao.getScenices(topSum);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getAllCategory
     * </p>
     * <p>
     * Description:商品分类
     * </p>
     * 
     * @param scenictype
     * @param isjd
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getAllCategory(java.lang.String, java.lang.String)
     */
    public List getAllCategory(String scenictype, String isjd) {
	return homeDao.getAllCategory(scenictype, isjd);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getAllCategoryOfHotel
     * </p>
     * <p>
     * Description: 商品分类 of 酒店 按照星级
     * </p>
     * 
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getAllCategoryOfHotel()
     */
    public List getAllCategoryOfHotel() {
	return homeDao.getAllCategoryOfHotel();
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getHotelStars
     * </p>
     * <p>
     * Description: 获取酒店星级
     * </p>
     * 
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getHotelStars()
     */
    public List getHotelStars() {
	return homeDao.getHotelStars();
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getEdmtickettypetab
     * </p>
     * <p>
     * Description:单个产品
     * </p>
     * 
     * @param id
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getEdmtickettypetab(java.lang.String)
     */
    public Edmtickettypetab getEdmtickettypetab(String id) {
	return homeDao.getEdmtickettypetab(id);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getEsbscenicareatab
     * </p>
     * <p>
     * Description: 获取服务商
     * </p>
     * 
     * @param id
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getEsbscenicareatab(java.lang.String)
     */
    public Esbscenicareatab getEsbscenicareatab(String id) {
	return homeDao.getEsbscenicareatab(id);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getHotelRecommend
     * </p>
     * <p>
     * Description: 推荐酒店 more
     * </p>
     * 
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getHotelRecommend(int, int, java.lang.String)
     */
    public PaginationSupport getHotelRecommend(int pageSize, int startIndex, String url) {
	return homeDao.getHotelRecommend(pageSize, startIndex, url);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getTravelAgency
     * </p>
     * <p>
     * Description: 推荐旅行社 more
     * </p>
     * 
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getTravelAgency(int, int, java.lang.String)
     */
    public PaginationSupport getTravelAgency(int pageSize, int startIndex, String url) {
	return homeDao.getTravelAgency(pageSize, startIndex, url);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getScenices
     * </p>
     * <p>
     * Description: 武夷山景区 more
     * </p>
     * 
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getScenices(int, int, java.lang.String)
     */
    public PaginationSupport getScenices(int pageSize, int startIndex, String url) {
	return homeDao.getScenices(pageSize, startIndex, url);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getAttractions
     * </p>
     * <p>
     * Description: 景区下景点
     * </p>
     * 
     * @param rootid
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.service.iservice.IHomeService#getAttractions(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getAttractions(String rootid, int pageSize, int startIndex, String url) {
	return homeDao.getAttractions(rootid, pageSize, startIndex, url);
    }

    public List getTopTravelLine(int topnum, int daynum) {
	return homeDao.getTopTravelLine(topnum, daynum);
    }

    public List getTopCommnet(int topnum) {
	List list=homeDao.getTopCommnet(topnum);
	if(list!=null&&list.size()>0){
	    for(int i=0;i<list.size();i++){
		Map map=(Map) list.get(i);
		if(map.get("ptype").toString().equals("01")){//服务商
		    Esbscenicareatab provider=(Esbscenicareatab) homeDao.get(Esbscenicareatab.class, Long.parseLong(map.get("oeid").toString()));
		    map.put("pcname", provider.getSzscenicname());
		    map.put("pctype",provider.getScenictype());
		}else if(map.get("ptype").toString().equals("02")){
		    Edmtickettypetab product=(Edmtickettypetab) homeDao.get(Edmtickettypetab.class, Long.parseLong(map.get("oeid").toString()));
		    map.put("pcname", product.getSztickettypename());
		    Sysparv5 sys=(Sysparv5) homeDao.get(Sysparv5.class, new Sysparv5Id("PRTP",product.getBycategorytype()));
		    map.put("pctype", sys.getPmvb());
		}
	    }   
	}
	 
	return list;
    }

    public List<GoodsCategory> getTopLevel() {
	List<GoodsCategory> categroylist = new ArrayList<GoodsCategory>();
	StringBuffer where = new StringBuffer();
	where.append(" spmcd='****' and pmcd='08' and (isvalue is null or isvalue!=0)");
	List list = sysService.queryByPmkyPmcds("PRTP", where.toString());
	if (list != null && list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		Map map = (Map) list.get(i);
		GoodsCategory category = new GoodsCategory();
		category.setCategoryId(map.get("pmcd").toString());
		category.setCategoryName(map.get("pmva").toString());
		category.setNextCategory(sysService.queryByPmkyPmcds("PRTP", " spmcd='" + category.getCategoryId() + "' and (isvalue is null or isvalue!=0)"));
		categroylist.add(category);
	    }
	}
	return categroylist;
    }
    
    public List getTopGoods(String topSum) {
	List list = homeDao.getTopGoods(Integer.parseInt(topSum));
	if (list != null && list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		Map map = (Map) list.get(i);
		List plist = homeDao.getHotelPictures(map.get("iscenicid").toString(), map.get("productid").toString(), 1);
		if(plist!=null&&plist.size()>0){
		    Map pmap = (Map) plist.get(0);
		    map.put("picaddress", pmap.get("upadder").toString());
		    map.put("picname", pmap.get("upfilename").toString());
		}else{
		    map.put("picname","");
		    map.put("picaddress", "");
		}
	    }
	}
	return list;
    }
/**
 * 读服务商
 * 
 */
	public List getTypeScenices(String topSum, String type) {
		// TODO Auto-generated method stub
		return  homeDao.getTypeScenices(topSum,type);
	}

	public List<Domain> getDomain(String url,String type) {
		List list = null;
		try {
			list = homeDao.findBySqlToMap("select seq as seq,domainUrl as domainUrl,groupId as groupId,logoMark as logoMark,logoPic as logoPic,type as type "
					+ " from Domain where domainUrl=? and type=? order by seq", url,type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Domain>  listDomain = new ArrayList<Domain>();
		if(list != null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Domain d = new Domain();
				d.setSeq(Long.parseLong(map.get("SEQ").toString()));
				d.setDomainUrl(map.get("DOMAINURL").toString());
				d.setGroupId(map.get("GROUPID").toString());
				if(map.get("LOGOMARK") != null){
					d.setLogoMark(map.get("LOGOMARK").toString());	
				}
				if(map.get("LOGOPIC")!= null){
					d.setLogoPic(map.get("LOGOPIC").toString());	
				}
				
				listDomain.add(d);
			}
			return listDomain;			
		}
		return null;
	}

	public List getHotelProvider(List scenicsList,boolean isHqyatu) {
		return homeDao.getHotelProvider(scenicsList,isHqyatu);
	}
	

}
