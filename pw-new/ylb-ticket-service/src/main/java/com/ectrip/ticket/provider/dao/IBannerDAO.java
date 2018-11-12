package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Banner;

public interface IBannerDAO extends IGenericDao{
	
	public void insertBanner(Banner banner);
	
	public void deleteBanner(Long id);
	
	public void updateBanner(Banner banner);
	
	public Banner queryBanner(Long id);
	
	public PaginationSupport queryBannerList(int page, int pageSize, String url, String providerType);
	
	public List queryBannerListForApp(String newUrl);

}
