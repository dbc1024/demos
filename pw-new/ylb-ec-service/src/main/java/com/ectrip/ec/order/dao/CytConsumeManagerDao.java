package com.ectrip.ec.order.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.cyt.CytConsumeOption;
import com.ectrip.ec.order.dao.idao.ICytConsumeManagerDao;

/**
 * Created by cxh on 2016/10/08.
 */
@Repository
public class CytConsumeManagerDao extends GenericDao implements ICytConsumeManagerDao{

    public PaginationSupport queryCytPojos(CytConsumeOption option, int pageSize, int page, String url) {
        StringBuffer hsql = new StringBuffer();
        hsql.append("from CYTPojo where substr(consumedate,1,10) >= '"+option.getRzti()+"' and substr(consumedate,1,10) <= '"+option.getLdti()+"' ");
        url += "?option.rzti="+option.getRzti()+"&option.ldti="+option.getLdti();
        if(!StringUtils.isBlank(option.getOrid())){
            hsql.append(" and orid = '"+option.getOrid()+"' ");
            url += "&option.orid="+option.getOrid();
        }
        if(!StringUtils.isBlank(option.getStatus())){
            hsql.append(" and state = " +Long.parseLong(option.getStatus()));
            url += "&option.status="+option.getStatus();
        }
        hsql.append(" and substr(orid,9,3) = '999' order by orid desc");
        return this.findPage(hsql.toString(),pageSize, page,url);
    }
}
