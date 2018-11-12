package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Banner;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.provider.dao.IBannerDAO;
import com.ectrip.ticket.provider.service.IBannerService;
import com.ectrip.ticket.provider.service.IProviderService;

@Service
public class BannerService extends GenericService implements IBannerService{
	
	private IBannerDAO bannerDao;
	
	@Autowired
	private IProviderService providerService;

	@Autowired
	public void setBannerDao(IBannerDAO bannerDao) {
		this.bannerDao = bannerDao;
		super.setGenericDao(bannerDao);
	}
	
	

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void insertBanner(Banner banner) {
		// TODO Auto-generated method stub
		bannerDao.insertBanner(banner);
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void deleteBanner(Long id) {
		// TODO Auto-generated method stub
		bannerDao.deleteBanner(id);
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updateBanner(Banner banner) {
		// TODO Auto-generated method stub
		bannerDao.updateBanner(banner);
	}

	public Banner queryBanner(Long id) {
		// TODO Auto-generated method stub
		
		Banner banner = bannerDao.queryBanner(id);
		
		Esbscenicareatab provider = providerService.query(banner.getProviderId());
		banner.setProviderName(provider.getSzscenicname());
		banner.setProviderType(provider.getScenictype());
		
		return banner;
	}

	public PaginationSupport queryBannerList(int page, int pageSize, String url, String providerType) {
		// TODO Auto-generated method stub
		return bannerDao.queryBannerList(page, pageSize, url, providerType);
	}

	public List queryBannerListForApp(String newUrl) {
		// TODO Auto-generated method stub
		return bannerDao.queryBannerListForApp(newUrl);
	}

}
