package com.ectrip.ec.order.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
/**
 * 
* @ClassName: IYOrderListDAO 
* @Description: ����Ԥ�������̶�Ʊ��ϸ 
* @author Dicky
* @date Oct 11, 2011 2:42:18 PM 
*
 */
public interface IYOrderListDAO extends IGenericDao{
   public void saveYOrderList(YOrderlist yorderlist);
   public List getYOrderListList(String orid,String iscenicid)throws IllegalAccessException, InvocationTargetException;
   public List getYOrderListChildList(String orid,String iscenicid);
   public int getLastYOrderListId();
   public Custom queryDaoyouByOrhm(String orhm) throws IllegalAccessException, InvocationTargetException;
   public Prdtripvenuemanage getPrdtripvenuemanage(Long iscenicid,Long tripid,Long itickettypeid,String date);
   public List getOrderOpearHistory(String orid);
   public List<Edmticketcomposepricetab> getSonPrice(Long icrowkindpriceid,Long itckettypeid);
}
