package com.ectrip.ec.report.system.datereport.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;

import java.util.List;

/**
 * Created by cxh on 2016/05/09.
 */
public interface ISaleDataTransferService extends IGenericService {

    public List findSaleId(String date);

    public void transfer(Long isalesvoucherid, Long iticketstationid);

    public List findOrder(String rzti, String ldti, boolean iscyt) throws Exception;

    public void orderTransfer(String orid, Long iscenicid, Long iticketwinid, Long maxid);
}
