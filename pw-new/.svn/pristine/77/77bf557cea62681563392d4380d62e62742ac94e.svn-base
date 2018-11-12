package com.ectrip.ticket.sale.service.iservice;

import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.provider.Esbticketwintab;

public interface ISaleAutoService {

    public ResultBean saveorder(String message,Long iticketwinid,Long iemployeeid,String orid,Long iscenicid,String url);
    public ResultBean payOrder (String orderno,Long iscenicid,Double yingshou, Double shiShou, Double zhaoling, Long iticketwinid,
                                Long iemployeeid,String zffs,Long maxid);
    public ResultBean getAutoT_orderlist(String orid, Long iscenicid);
    public Esbticketwintab getEsbticketwintab(Long iscenicid,String mac);
    public Esfemployeetab isyuxiao(String md5str, String userid,String password);
    public ResultBean savetorder(T_order t_order, List listorder,
                                 List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,List Trealnamelist) throws Exception;
    public ResultBean savebenditorder(String orid,Long iscenicid, Long iemployeeid, Long iticketwinid, Long maxid)
            throws Exception;
    public ResultBean cancelorder(String orid,String url);
    
    public ResultBean autoSaveorder(String message,Long iticketwinid,Long iemployeeid,String orid,Long iscenicid,String timeParam,String url);
}

