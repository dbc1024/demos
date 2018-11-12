package com.ectrip.ec.order.service.iservice;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.user.Custom;

public interface ISeatorderService extends IGenericService {
    public List gettorderseatlist(String orid,Long iscenicid,Long orderlistid);
    public Seatordertab getSeatordertab(String orid,Long iscenicid,Long iseatid);
    public YZorderlist getsxfYZorderlist(YZorderlist oldtdlist, String usid)
			throws ParseException;
    public Map saveeditseat(Custom com,MOrder mor,TOrder torder,List torderlistlist,String neworid)throws Exception;
}
