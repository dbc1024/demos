package com.ectrip.sys.baidu.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ectrip.sys.baidu.util.HttpUtils;
import com.ectrip.sys.model.baidu.pojo.DataTran;
import com.ectrip.sys.model.baidu.pojo.Device;
import com.ectrip.sys.model.baidu.pojo.DeviceGroup;
import com.ectrip.sys.model.baidu.pojo.HttpStatusType;
import com.ectrip.sys.model.baidu.pojo.Ticket;
import com.ectrip.sys.model.baidu.pojo.Visitor;
import com.ectrip.sys.model.baidu.request.DeviceGroupIdRequest;
import com.ectrip.sys.model.baidu.request.DeviceGroupsRequest;
import com.ectrip.sys.model.baidu.request.DeviceIdRequest;
import com.ectrip.sys.model.baidu.request.DeviceRequest;
import com.ectrip.sys.model.baidu.request.TicketRequest;
import com.ectrip.sys.model.baidu.response.Response;

/**
 * Created by chenxinhao on 2017/3/1.
 */
public class BaiduClient {

    /**
     * �����豸
     * @param devices
     * @return
     */
    public static Response addDevice(List<Device> devices){
        Response response = new Response();
        DeviceRequest request = new DeviceRequest();
        request.setDevices(devices);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/device/add", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �����豸
     * @param devices
     * @return
     */
    public static Response updateDevice(List<Device> devices){
        Response response = new Response();
        DeviceRequest request = new DeviceRequest();
        request.setDevices(devices);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/device/update", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ɾ���豸
     * @param deviceIds
     * @return
     */
    public static Response deleteDevices(List<String> deviceIds){
        Response response = new Response();
        DeviceIdRequest request = new DeviceIdRequest();
        request.setDeviceIds(deviceIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/device/delete", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ��ѯ�豸
     * @param deviceIds
     * @return
     */
    public static Response findDevices(List<String> deviceIds){
        Response response = new Response();
        DeviceIdRequest request = new DeviceIdRequest();
        request.setDeviceIds(deviceIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/device/find", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �����豸��
     * @param groups
     * @return
     */
    public static Response addDeviceGroups(List<DeviceGroup> groups){
        Response response = new Response();
        DeviceGroupsRequest request = new DeviceGroupsRequest();
        request.setGroups(groups);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/add", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �޸��豸��
     * @param groups
     * @return
     */
    public static Response updateDeviceGroups(List<DeviceGroup> groups){
        Response response = new Response();
        DeviceGroupsRequest request = new DeviceGroupsRequest();
        request.setGroups(groups);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/update", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ɾ���豸��
     * @param groups
     * @return
     */
    public static Response deleteDeviceGroups(List<String> groupIds){
        Response response = new Response();
        DeviceGroupIdRequest request = new DeviceGroupIdRequest();
        request.setGroupIds(groupIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/delete", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ��ѯ�豸��
     * @param groups
     * @return
     */
    public static Response findDeviceGroups(List<String> groupIds){
        Response response = new Response();
        DeviceGroupIdRequest request = new DeviceGroupIdRequest();
        request.setGroupIds(groupIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/find", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �����豸��
     * @param groups
     * @return
     */
    public static Response copyDeviceGroups(String fromGroupId, String toGroupId){
        Response response = new Response();
        Map<String,String> map = new HashMap<String, String>();
        map.put("fromGroupId",fromGroupId);
        map.put("toGroupId",toGroupId);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/copy", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �豸����豸
     * @param groupId
     * @param deviceIds
     * @return
     */
    public static Response addGroupDevice(String groupId, String[] deviceIds){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("groupId",groupId);
        map.put("deviceIds",deviceIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/addDevice", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ɾ���豸��󶨵��豸
     * @param groupId
     * @param deviceIds
     * @return
     */
    public static Response deleteGroupDevice(String groupId, String[] deviceIds){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("groupId",groupId);
        map.put("deviceIds",deviceIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/deleteDevice", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * ��ѯ�豸����豸��Ϣ
     * @param groupId
     * @param deviceIds
     * @return
     */
    public static Response findGroupDevice(String groupId, String[] deviceIds){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("groupId",groupId);
        map.put("deviceIds",deviceIds);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/deviceGroup/findDevice", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �ο͹�Ʊ
     * @param visitor
     * @param orderId
     * @param ticket
     * @return
     */
    public static Response addTicket(Visitor visitor,String orderId,Ticket ticket){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("visitor",visitor);
        map.put("orderId",orderId);
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(ticket);
        map.put("tickets",tickets);
        System.out.println(JSON.toJSONString(map));
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/ticket/add", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    public static Response deleteTicket(List<String> tickets){
        Response response = new Response();
        TicketRequest request = new TicketRequest();
        request.setTicketIds(tickets);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/ticket/delete", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    public static Response deleteVisitorTicket(String visitorId,List<String> tickets){
        Response response = new Response();
        TicketRequest request = new TicketRequest();
        request.setVisitorId(visitorId);
        request.setTicketIds(tickets);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/ticket/delete", JSON.toJSONString(request));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    /**
     * �οͲ�ѯ��ǰӵ�е�Ʊ
     * @param visitorId
     * @return
     */
    public static Response findTicket(String visitorId){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("visitorId",visitorId);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/visitor/findTicket", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    public static Response checkVisitorPhoto(String visitorId){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("visitorId",visitorId);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/visitor/checkVisitorPhoto", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    public static Response findVisitor(String visitorId,Boolean showPhoto){
        Response response = new Response();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("visitorId",visitorId);
        map.put("showPhoto",showPhoto);
        DataTran dataTran = HttpUtils.invokeHttpPost("/api/v1/visitor/find", JSON.toJSONString(map));
        if(HttpStatusType.SUCCESS.getCode() == dataTran.getCode()){
            response = JSON.parseObject(dataTran.getData(),Response.class);
            response.setHttpStatus(dataTran.getCode());
        }else{
            response.setHttpStatus(dataTran.getCode());
            response.setMessage(dataTran.getData());
        }
        return response;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
//        list.add("717327FD723581B80A1B2876D27420AB_1W1A20080H5XF6");
        list.add("450721199009105314");
//        list.add("W0101203AGXM43");
//        list.add("W0101203AGXN04");
//        list.add("W0101203AGXS25");
//        list.add("W0101203AGXT2E");
//        findGroupDevice("717327FD723581B80A1B2876D27420AB_EojCumZ_1",new String[]{"8B5D-6FC7-7D76-B9FB"});
//        deleteGroupDevice("5D1BDB2E5E72E5868D248com.ectrip.sale.service.commonSale.service7409851EC95_E53kzRC_3",new String[]{"2792-7130-31A6-8958"});
//        deleteDevices(list);
//        findDevices(list);
//        deleteDevices(list);
        findTicket("asdfghjklasdfghjklasdfghjklasdfg_C1020603BDJ79D");
//        deleteVisitorTicket("0CAC6735140E18878076AE73ADA32917_450721199009105314",list);
//        checkVisitorPhoto("asdfghjklasdfghjklasdfghjklasdfg_C1020603BDJ79D");
//        findVisitor("0CAC6735140E18878076AE73ADA32917_450721199009105314",true);
    }
}
