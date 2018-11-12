package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Banner;

public interface IBannerService extends IGenericService{

	public void insertBanner(Banner banner);
	
	public void deleteBanner(Long id);
	
	public void updateBanner(Banner banner);
	
	public Banner queryBanner(Long id);
	
	public PaginationSupport queryBannerList(int page, int pageSize, String url, String providerType);
	
	public List queryBannerListForApp(String newUrl);
}
