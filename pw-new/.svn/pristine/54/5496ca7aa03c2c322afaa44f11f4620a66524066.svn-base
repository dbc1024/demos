package com.ectrip.ticket.sale.service.commonSale.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.ticket.model.applyorder.Ordertickettourist;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.CheckListPojo;
import com.ectrip.ticket.sale.service.commonSale.model.request.CheckFingerPrintRequest;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckFingerPrintResponse;

/**
 * Created by chenxinhao on 2017/3/13.
 */
public interface ICommonSaleDao extends IGenericDao{

    List<Ordertickettourist> findTourists(String orid, String providerId, String productId, String groupId, String credentials);

    Opcemployeecardtab findEmp(String ticketNo);

    Stssoldtickettab findStsso(String ticketNo);

    List findStssticket(String ticketNo);

    List<String> findGardenids(StssoldtickettabId id);

    List<String> findEmpGardenids();

    AuthCode getAuthCode(String codeType, String orginId);

    CheckFingerPrintResponse findFingerPrints(CheckFingerPrintRequest request);

    public List<CheckListPojo> getchecklist(Stssoldtickettab stss);
}
