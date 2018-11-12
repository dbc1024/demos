package com.ectrip.ec.order.dao.idao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.YOrder;
/**
 * 
* @ClassName: IYOrderDAO 
* @Description: 网上预定服务商订票 
* @author Dicky
* @date Oct 11, 2011 2:41:56 PM 
*
 */
public interface IYOrderDAO extends IGenericDao{
   public void saveYOrder(YOrder yorder);
   public List getYOrderList(String orid);
   public List getYOrderChildList(String orid);
   public YOrder getYOrderInfoById(String orId,Long iscenicid) throws Exception;
}
