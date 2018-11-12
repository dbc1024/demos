package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ticket.model.provider.Banner;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.provider.dao.IBannerDAO;
import com.ectrip.ticket.provider.dao.IProviderDAO;
import com.ectrip.ticket.provider.service.IProviderService;
@Repository
public class BannerDAO extends GenericDao implements IBannerDAO{
	
	@Autowired
	private IProviderDAO providerDao;
//	private ISearchDAO searchDao =(ISearchDAO) SpringUtil.getBean("searchDao");
	@Autowired
	private IProviderService providerService;
	

	public void insertBanner(Banner banner) {
		// TODO Auto-generated method stub
		this.save(banner);
	}

	public void deleteBanner(Long id) {
		// TODO Auto-generated method stub
		Banner banner = this.queryBanner(id);
		this.delete(banner);
	}

	public void updateBanner(Banner banner) {
		// TODO Auto-generated method stub
		this.update(banner);
	}

	public Banner queryBanner(Long id) {
		// TODO Auto-generated method stub
		Banner banner = (Banner) this.get(Banner.class, id);
		
		return banner;
	}

	public PaginationSupport queryBannerList(int page, int pageSize, String url, String providerType) {
		// TODO Auto-generated method stub
		String hql = "select distinct new map(banner.id as id, banner.providerId as providerId, banner.title as title, banner.url as url, es.szscenicname as providerName, es.scenictype as providerType) from Banner banner, Esbscenicareatab es where banner.providerId=es.iscenicid and es.byisuse=1 and es.scenictype="+ providerType;
		
		PaginationSupport ps = this.findPage(hql.toString(), pageSize, page, url);
		
		
		return ps;
	}
	
	
	public List queryBannerListForApp(String newUrl){
		
		String hql = "select distinct new map(banner.id as id, banner.providerId as providerId, banner.title as title, banner.url as url, es.szscenicname as providerName, es.scenictype as providerType) from Banner banner, Esbscenicareatab es where banner.providerId=es.iscenicid and es.byisuse=1";
		
		List bannerList = this.find(hql);
		
		for (Object object : bannerList) {
			Map<String, Object> banner = (Map<String, Object>)object;
			String providerId = banner.get("providerId").toString();
			
			Esbscenicareatab provider = providerDao.query(Long.valueOf(providerId));
			List piclist = provider.getList();
			ArrayList arrayList = new ArrayList();
			if (piclist != null && piclist.size() > 0){
				for (int j = 0; j < piclist.size(); j++) {
					Map picmap = (Map) piclist.get(j);
					arrayList.add("http://"
							+ newUrl
							+ picmap.get("upadder").toString()
							+ picmap.get("upfilename").toString());
				}
			}
			
			banner.put("providerPicList", arrayList);//banner对应景区的图片列表
			banner.put("providerIntroduction", provider.getSzsimpleintroduction());
			banner.put("areaname", provider.getSzaddress());//szgrade
			banner.put("szgrade", provider.getSzgrade());
			banner.put("szlocation", provider.getSzlocation());//szbookdescription szregionalid iordernumber longitude szphone
			banner.put("szbookdescription", provider.getSzbookdescription());
			banner.put("szregionalid", provider.getSzregionalid());
			banner.put("iordernumber", provider.getIordernumber());
			banner.put("longitude", provider.getLongitude());
			banner.put("szphone", provider.getSzphone());
			
			
			String providerType = banner.get("providerType").toString();
			//酒店信息转换
			if("06".equals(providerType)){
				//酒店列表返回信息（获取不能直接在服务商对象中获取的字段）
				StringBuffer hsql = new StringBuffer();
				hsql.append(" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as providername,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as areaname,pro.iordernumber as iordernumber, s1.pmva as strgrade,pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude,hp.zxjb as zxjb,hp.hotelservice as hotelservice,s2.pmva as strzxjb) " +
						     "from  Esbscenicareatab pro,Hotelprovider hp,Sysparv5 s1,Sysparv5 s2,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.iscenicid = hp.iscenicid and pro.byisuse=1 and pro.isjd =0 and s1.id.pmcd=pro.szgrade and s1.id.pmky='DENJ' and s2.id.pmcd=hp.zxjb and s2.id.pmky='HOTP' and pro.iscenicid in (" + providerId + ") ");
				
				List list = providerDao.find(hsql.toString());
				if(list != null && list.size() > 0){
					Map<String, Object> holtel = (Map<String, Object>)list.get(0);
					
					banner.put("strgrade", holtel.get("strgrade").toString());
					banner.put("strzxjb", holtel.get("strzxjb").toString());
					banner.put("zxjb", holtel.get("zxjb").toString());
					
					// 酒店设施
					if (StringUtil.isNotEmpty(holtel.get("hotelservice").toString())) {
						String[] services = holtel.get("hotelservice").toString().split(" ");
						String[] serviceids = new String[services.length];
						for (int m = 0; m < services.length; m++) {
							serviceids[m] = providerService.findpmcd("HTSS", services[m]);
						}
						banner.put("serviceids", serviceids);
					}
				}
			}
			
		}
		
		return bannerList;
	}

}
