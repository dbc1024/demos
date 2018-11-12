package com.ectrip.ticket.sale.service.commonSale.service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.ticket.model.log.ExceptionLog;
import com.ectrip.ticket.model.log.InterfaceLog;
import com.ectrip.ticket.model.log.InterfaceType;
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
import com.ectrip.ticket.sale.service.commonSale.service.iservice.ICommonSaleService;

/**
 * Created by chenxinhao on 2017/3/13.
 */
public class CommonSaleWebService {

    private static ICommonSaleService commonSaleService;

    static {
        if (commonSaleService == null) {
            commonSaleService = (ICommonSaleService) SpringUtil.getBean("commonSaleService");
        }
    }

    public Object findTourists(String data) {
        FindTouristResponse res;
        try {
            res = commonSaleService.findTourists(JSON.parseObject(data, FindTouristRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查询订单实名制信息");
            interfaceLog.setInterfaceMethod("findTourists");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (Exception e) {
            e.printStackTrace();
            res = new FindTouristResponse();
            res.setCode("2001");
            res.setDescribe("查询实名制信息失败");
            ExceptionLog.printExceptionLog("查询实名制信息失败", e);
        }
        return res;
    }

    public Object findEmpPhoto(String data) {
        Response res;
        try {
            res = commonSaleService.findEmpPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查找员工头像信息");
            interfaceLog.setInterfaceMethod("findPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new Response();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("查找员工头像信息失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new Response();
            res.setCode("2001");
            res.setDescribe("查找员工头像信息失败");
            ExceptionLog.printExceptionLog("查找员工头像信息失败", e);
        }
        return res;
    }

    public Object findPhoto(String data) {
        Response res;
        try {
            res = commonSaleService.findPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查找头像信息");
            interfaceLog.setInterfaceMethod("findPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new Response();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("查找头像信息失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new Response();
            res.setCode("2001");
            res.setDescribe("查找头像信息失败");
            ExceptionLog.printExceptionLog("查找头像信息失败", e);
        }
        return res;
    }

    public Object sendEmpPhoto(String data) {
        Response res;
        try {
            res = commonSaleService.sendEmpPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("员工上传头像");
            interfaceLog.setInterfaceMethod("sendPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new Response();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("员工上传头像失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new Response();
            res.setCode("2001");
            res.setDescribe("员工上传头像失败");
            ExceptionLog.printExceptionLog("员工上传头像失败", e);
        }
        return res;
    }

    public Object sendPhoto(String data) {
        Response res;
        try {
            res = commonSaleService.sendPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("上传头像");
            interfaceLog.setInterfaceMethod("sendPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new Response();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("上传图片失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new Response();
            res.setCode("2001");
            res.setDescribe("上传图片失败");
            ExceptionLog.printExceptionLog("上传图片失败", e);
        }
        return res;
    }

    public Object checkEmpPhoto(String data) {
        CheckPhotoResponse res;
        try {
            res = commonSaleService.checkEmpPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("员工信息查询");
            interfaceLog.setInterfaceMethod("checkPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new CheckPhotoResponse();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("员工信息查询失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new CheckPhotoResponse();
            res.setCode("2001");
            res.setDescribe("员工信息查询失败");
            ExceptionLog.printExceptionLog("员工信息查询失败", e);
        }
        return res;
    }

    public Object checkPhoto(String data) {
        CheckPhotoResponse res;
        try {
            res = commonSaleService.checkPhoto(JSON.parseObject(data, SendPhotoRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("票号查询");
            interfaceLog.setInterfaceMethod("checkPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            res = new CheckPhotoResponse();
            res.setCode("2001");
            res.setDescribe(e1.getMessage());
            ExceptionLog.printExceptionLog("票号查询失败", e1);
        } catch (Exception e) {
            e.printStackTrace();
            res = new CheckPhotoResponse();
            res.setCode("2001");
            res.setDescribe("票号查询失败");
            ExceptionLog.printExceptionLog("票号查询失败", e);
        }
        return res;
    }

    public ResultBean checkRegisterPhoto(String ticketNo,String str2,String str3) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        try {
            CheckRegisterPhotoResponse res = commonSaleService.checkRegisterPhoto(ticketNo);
            if ("0000".equalsIgnoreCase(res.getCode())) {
                rs.addRow(new String[]{"2"});
                rs.addRow(new String[]{JSON.toJSONString(res)});
            } else if ("0001".equalsIgnoreCase(res.getCode())) {
                rs.addRow(new String[]{"1"});
                rs.addRow(new String[]{JSON.toJSONString(res)});
            }
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.CHECK);
            interfaceLog.setRequestData(ticketNo);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("闸机人脸注册");
            interfaceLog.setInterfaceMethod("checkRegisterPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        } catch (RuntimeException e1) {
            rs.addRow(new String[]{"-1"});
            rs.addRow(new String[]{e1.getMessage()});
            ExceptionLog.printExceptionLog("票号查询失败", e1);
        } catch (Exception e) {
            rs.addRow(new String[]{"-1"});
            rs.addRow(new String[]{"注册查询异常"});
            ExceptionLog.printExceptionLog("票号查询失败", e);
        }
        return rs;
    }

    public ResultBean saveRegisterPhoto(String ticketNo,String visitorId){
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        try{
            Response res = commonSaleService.saveRegisterPhoto(ticketNo,visitorId);
            if ("0000".equalsIgnoreCase(res.getCode())) {
                rs.addRow(new String[]{"1"});
            }
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.CHECK);
            interfaceLog.setRequestData(ticketNo);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("保存闸机人脸注册结果成功");
            interfaceLog.setInterfaceMethod("checkRegisterPhoto");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionLog.printExceptionLog("保存闸机头像注册接口失败", e);
            rs.addRow(new String[]{"-1"});
            rs.addRow(new String[]{"保存闸机人脸注册结果失败"});
        }
        return rs;
    }

    public Object checkFingerPrint(String data){
        CheckFingerPrintResponse res;
        try{
            res = commonSaleService.checkFingerPrint(JSON.parseObject(data,CheckFingerPrintRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.SALE);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("指纹查询");
            interfaceLog.setInterfaceMethod("checkFingerPrint");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new CheckFingerPrintResponse();
            res.setCode("2001");
            res.setDescribe("指纹查询失败");
            ExceptionLog.printExceptionLog("指纹查询失败", e);
        }
        return res;
    }

    public Object findCheckList(String data){
        FindCheckListResponse res;
        try{
            res = commonSaleService.findCheckList(JSON.parseObject(data, FindCheckListRequest.class));
        }catch (Exception e){
            e.printStackTrace();
            res = new FindCheckListResponse();
            res.setCode("2001");
            res.setDescribe("查询检票记录失败");
        }
        return res;
    }
}
