package com.ectrip.ec.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.cyt.CYTPojo;
import com.ectrip.ec.model.cyt.CytConsumeOption;
import com.ectrip.ec.order.dao.idao.ICytConsumeManagerDao;
import com.ectrip.ec.order.service.iservice.ICytConsumeManagerService;

/**
 * Created by cxh on 2016/10/08.
 */
@Service
public class CytConsumeManagerService extends GenericService implements ICytConsumeManagerService{

    
    private ICytConsumeManagerDao cytConsumeManagerDao;
    @Autowired
    public void setCytConsumeManagerDao(ICytConsumeManagerDao cytConsumeManagerDao) {
		this.cytConsumeManagerDao = cytConsumeManagerDao;
		super.setGenericDao(cytConsumeManagerDao);
	}

	public PaginationSupport queryCytPojos(CytConsumeOption option, int pageSize, int page, String url) {
        return cytConsumeManagerDao.queryCytPojos(option, pageSize, page, url);
    }

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void updateCytpojo(CYTPojo cytPojo){
        CYTPojo old = (CYTPojo) cytConsumeManagerDao.get(CYTPojo.class,cytPojo.getOrid());
        old.setPrintNum(cytPojo.getPrintNum());
        old.setState(cytPojo.getState());
        cytConsumeManagerDao.update(old);
    }
}
