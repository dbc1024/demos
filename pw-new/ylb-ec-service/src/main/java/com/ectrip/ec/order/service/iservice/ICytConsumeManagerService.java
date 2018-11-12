package com.ectrip.ec.order.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.cyt.CYTPojo;
import com.ectrip.ec.model.cyt.CytConsumeOption;

/**
 * Created by cxh on 2016/10/08.
 */
public interface ICytConsumeManagerService extends IGenericService {

    public PaginationSupport queryCytPojos(CytConsumeOption option, int pageSize, int page, String url);

    public void updateCytpojo(CYTPojo cytPojo);
}
