package com.ectrip.ticket.sale.service.commonSale.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.commonSale.model.request.CheckFingerPrintRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.FindCheckListRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.FindTouristRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.SendPhotoRequest;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckFingerPrintResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckPhotoResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckRegisterPhotoResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.FindCheckListResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.FindTouristResponse;

/**
 * Created by chenxinhao on 2017/3/13.
 */
public interface ICommonSaleService extends IGenericService{

    FindTouristResponse findTourists(FindTouristRequest request);

    Response sendPhoto(SendPhotoRequest request);

    Response findPhoto(SendPhotoRequest request);

    CheckPhotoResponse checkPhoto(SendPhotoRequest request);

    Response sendEmpPhoto(SendPhotoRequest request);

    Response findEmpPhoto(SendPhotoRequest request);

    CheckPhotoResponse checkEmpPhoto(SendPhotoRequest request);

    CheckRegisterPhotoResponse checkRegisterPhoto(String ticketNo);

    Response saveRegisterPhoto(String ticketNo, String visitorId);

    CheckFingerPrintResponse checkFingerPrint(CheckFingerPrintRequest request);

    public FindCheckListResponse findCheckList(FindCheckListRequest request);
}
