package com.ectrip.sys.other.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.AnnualCardOption;

/**
 * Created by cxh on 2016/05/23.
 */
public interface IAnnualCardService extends IGenericService {

    public PaginationSupport queryAnnualCardByOption(AnnualCardOption option, int page, int pageSize, String url);

    public List queryAnnualCardByOption(AnnualCardOption option);
}
