package com.ectrip.sys.other.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.AnnualCardOption;
import com.ectrip.sys.other.dao.idao.IAnnualCardDao;
import com.ectrip.sys.other.service.iservice.IAnnualCardService;

/**
 * Created by cxh on 2016/05/23.
 */
public class AnnualCardService extends GenericService implements IAnnualCardService {

    private IAnnualCardDao annualCardDao;

    public IAnnualCardDao getAnnualCardDao() {
        return annualCardDao;
    }

    public void setAnnualCardDao(IAnnualCardDao annualCardDao) {
        this.annualCardDao = annualCardDao;
    }

    public PaginationSupport queryAnnualCardByOption(AnnualCardOption option,int page,int pageSize,String url){
        return annualCardDao.queryAnnualCardByOption(option, page, pageSize, url);
    }

    public List queryAnnualCardByOption(AnnualCardOption option){
        return annualCardDao.queryAnnualCardByOption(option);
    }
}
