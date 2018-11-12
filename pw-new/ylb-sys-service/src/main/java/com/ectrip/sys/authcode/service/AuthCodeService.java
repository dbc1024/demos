package com.ectrip.sys.authcode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.authcode.client.AuthCodeClient;
import com.ectrip.sys.authcode.dao.idao.IAuthCodeDao;
import com.ectrip.sys.authcode.dto.AuthCodeInputDto;
import com.ectrip.sys.authcode.service.iservice.IAuthCodeService;
import com.ectrip.sys.baidu.client.BaiduClient;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.sys.model.authcode.CodeType;
import com.ectrip.sys.model.authcode.JsonMessage;
import com.ectrip.sys.model.authcode.OtherCodePojo;
import com.ectrip.sys.model.baidu.pojo.Device;
import com.ectrip.sys.model.baidu.pojo.DeviceGroup;
import com.ectrip.sys.model.baidu.pojo.HttpStatusType;
import com.ectrip.sys.model.baidu.response.Response;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenxinhao on 2017/2/23.
 */
@Service
public class AuthCodeService extends GenericService implements IAuthCodeService {

    @Autowired
    private IAuthCodeDao authCodeDao;

    public AuthCode getAuthCode(String codeType, String orginId) {
        return authCodeDao.getAuthCode(codeType, orginId);
    }

    public List<AuthCode> findAuthCodes(String codeType, String baiduStatus) {
        return authCodeDao.find("from AuthCode where codeType = '" + codeType + "' and baiduStatus = '" + baiduStatus + "' ");
    }

    public PaginationSupport searchAuthCodes(AuthCodeInputDto dto, int page, int pageSize, String url) {
        PaginationSupport ps = null;
        if (CodeType.SYSTEM.getCode().equals(dto.getCodeType())) {//ϵͳ
            AuthCode authCode = authCodeDao.getAuthCode(dto.getCodeType(), null);
            if (authCode != null) {
                Map map = new HashMap();
                map.put("id", authCode.getId());
                map.put("code", 1);
                try {
                    JsonMessage message = JSON.parseObject(Tools.getStrByBlob(authCode.getJsonMessage()), JsonMessage.class);
                    map.put("name", message.getDeployname());
                    map.put("codeTypeName", CodeType.SYSTEM.getName());
                    map.put("authCode", authCode.getAuthCode());
                    map.put("status", 1);
                    map.put("dtmakedate", authCode.getDtmakedate());
                    map.put("name", message.getSystemname());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List list = new ArrayList();
                list.add(map);
                ps = new PaginationSupport(list, 1, page, pageSize, url);
            }
        }
        if (CodeType.PROVIDER.getCode().equals(dto.getCodeType())) {//������
            ps = authCodeDao.searchProviderAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.PRODUCT.getCode().equals(dto.getCodeType())) {//��Ʒ
            ps = authCodeDao.searchProductAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.PRICE.getCode().equals(dto.getCodeType())) {//�۸�
            ps = authCodeDao.searchPriceAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.GARDEN.getCode().equals(dto.getCodeType())) {//԰��
            ps = authCodeDao.searchGardenAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.CHECKMACHINE.getCode().equals(dto.getCodeType())) {//׼���豸
            ps = authCodeDao.searchCheckMachineAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.STATION.getCode().equals(dto.getCodeType())) {//��Ʊվ��
            ps = authCodeDao.searchStationAuthCode(dto, page, pageSize, url);
        }
        if (CodeType.SALEMACHINE.getCode().equals(dto.getCodeType())) {//��Ʊ����
            ps = authCodeDao.searchSaleMachineAuthCode(dto, page, pageSize, url);
        }
        return ps;
    }

    public void saveSystemCode(JsonMessage message) {
        AuthCode authCode = new AuthCode();
        authCode.setId(authCodeDao.getMaxPk("id", "AuthCode") + 1L);
        authCode.setCodeType(CodeType.SYSTEM.getCode());
        authCode.setOrginId("1");
        authCode.setAuthCode(message.getSystemcode());
        authCode.setBaiduStatus("00");
        try {
            String signKey = AuthCodeClient.createSystemCode(message);
            message.setSignkey(signKey);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        authCode.setJsonMessage(Tools.getBlobByStr(JSON.toJSONString(message)));
        authCode.setRegionCode(authCode.getAuthCode());
        authCode.setDtmakedate(Tools.getDayTimes());
        authCodeDao.save(authCode);
    }

    public List searchNoAuthItems(String codeType) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        if (CodeType.PROVIDER.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchProvider(codeType);
        }
        if (CodeType.PRODUCT.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchProduct(codeType);
        }
        if (CodeType.PRICE.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchPrice(codeType);
        }
        if (CodeType.GARDEN.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchGarden(codeType);
        }
        if (CodeType.CHECKMACHINE.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchCheckMachine(codeType);
        }
        if (CodeType.STATION.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchStation(codeType);
        }
        if (CodeType.SALEMACHINE.getCode().equalsIgnoreCase(codeType)) {
            list = authCodeDao.searchSaleMachine(codeType);
        }
        return list;
    }

    public void saveOtherCode(String systemCode, String signKey, OtherCodePojo pojo) {
        try {
            String code = AuthCodeClient.createOtherCode(systemCode, signKey, pojo);
            if (!StringUtils.isBlank(code)) {
                AuthCode authCode = new AuthCode();
                authCode.setId(authCodeDao.getMaxPk("id", "AuthCode") + 1L);
                authCode.setCodeType(pojo.getCodetype());
                authCode.setOrginId(pojo.getMake());
                authCode.setAuthCode(code);
                authCode.setRegionCode(code);
                authCode.setBaiduStatus("00");
                authCode.setDtmakedate(Tools.getDayTimes());
                authCodeDao.save(authCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * ����ע��ٶ��豸
     *
     * @param systemCode
     * @param authCodes
     */
    public void registerCheckMachine(String systemCode, List<AuthCode> authCodes) {
        List<Device> devices = new ArrayList<Device>();
        for (int i = 0; i < authCodes.size(); i++) {
            AuthCode authCode = authCodes.get(i);
            Esbaccessequiptab check = authCodeDao.findCheckMachine(Long.parseLong(authCode.getOrginId()));
            Device device = new Device();
            if (authCode.getAuthCode().length() == 7) {//�����豸��
                device.setDeviceId(systemCode + "_" + authCode.getAuthCode() + "_" + authCode.getOrginId());
            } else {//�ٶ��豸��
                device.setDeviceId(authCode.getAuthCode());
            }
            device.setDeviceName(check.getSzaccessequipname());
            device.setCreateTime(Tools.getDayTimes());
            device.setDeviceVersion("1.0.0");
            devices.add(device);
        }
        Response response = BaiduClient.addDevice(devices);
        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
            if ("1000".equals(response.getCode())) {
                for (int i = 0; i < authCodes.size(); i++) {
                    AuthCode authCode = authCodes.get(i);
                    authCode.setBaiduStatus("01");
                    authCodeDao.update(authCode);
                }
            } else {
                throw new RuntimeException(response.getMessage());
            }
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * ע��ٶ��豸
     *
     * @param systemCode
     * @param authCode
     */
    public void registerCheckMachine(String systemCode, AuthCode authCode) {
        List<Device> devices = new ArrayList<Device>();
        Esbaccessequiptab check = authCodeDao.findCheckMachine(Long.parseLong(authCode.getOrginId()));
        AuthCode gardenCode = authCodeDao.getAuthCode(CodeType.GARDEN.getCode(), check.getId().getIgardengateid().toString());
        if (gardenCode == null) {
            throw new RuntimeException("�豸����԰��δע��");
        }
        Device device = new Device();
        if (authCode.getAuthCode().length() == 7) {//�����豸��
            device.setDeviceId(systemCode + "_" + authCode.getAuthCode() + "_" + authCode.getOrginId());
        } else {//�ٶ��豸��
            device.setDeviceId(authCode.getAuthCode());
        }
        device.setDeviceName(check.getSzaccessequipname());
        device.setCreateTime(Tools.getDayTimes());
        device.setDeviceVersion("1.0.0");
        devices.add(device);
        Response response = BaiduClient.addDevice(devices);
        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
            if ("1000".equals(response.getCode())) {
                authCode.setBaiduStatus("01");
                authCodeDao.update(authCode);
            } else {
                throw new RuntimeException(response.getMessage());
            }
            try {
                BaiduClient.addGroupDevice(systemCode + "_" + gardenCode.getAuthCode() + "_" + check.getId().getIgardengateid().toString(), new String[]{device.getDeviceId()});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void cancelCheckMachine(String systemCode, AuthCode authCode) {
        //ɾ���豸��󶨵��豸
        Esbaccessequiptab check = authCodeDao.findCheckMachine(Long.parseLong(authCode.getOrginId()));
        AuthCode gardenCode = authCodeDao.getAuthCode(CodeType.GARDEN.getCode(), check.getId().getIgardengateid().toString());
        Response response = BaiduClient.deleteGroupDevice(systemCode + "_" + gardenCode.getAuthCode() + "_" + check.getId().getIgardengateid().toString(), new String[]{authCode.getAuthCode()});
        if(!"1000".equals(response.getCode())){
            throw new RuntimeException(response.getMessage());
        }
        //ɾ���豸
        List<String> list = new ArrayList<String>();
        list.add(authCode.getAuthCode());
        response = BaiduClient.deleteDevices(list);
        if(!"1000".equals(response.getCode())){
            throw new RuntimeException(response.getMessage());
        }
        //���²���
        authCode.setAuthCode(authCode.getRegionCode());
        authCode.setBaiduStatus("00");
        authCodeDao.update(authCode);
    }

    public void registerGarden(String systemCode, List<AuthCode> authCodes) {
        List<DeviceGroup> groups = new ArrayList<DeviceGroup>();
        for (int i = 0; i < authCodes.size(); i++) {
            AuthCode authCode = authCodes.get(i);
            Esbgardengatetab garden = authCodeDao.findGarden(Long.parseLong(authCode.getOrginId()));
            DeviceGroup group = new DeviceGroup();
            group.setGroupId(systemCode + "_" + authCode.getAuthCode() + "_" + authCode.getOrginId());
            group.setGroupName(garden.getSzgardengatename());
            groups.add(group);
        }
        Response response = BaiduClient.addDeviceGroups(groups);
        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
            if ("1000".equals(response.getCode())) {
                for (int i = 0; i < authCodes.size(); i++) {
                    AuthCode authCode = authCodes.get(i);
                    authCode.setBaiduStatus("01");
                    authCodeDao.update(authCode);
                }
            } else {
                throw new RuntimeException(response.getMessage());
            }
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void bindMachine(String systemCode, List<AuthCode> authCodes) {
        for (int i = 0; i < authCodes.size(); i++) {
            AuthCode gardenAuthCode = authCodes.get(i);
            String groupId = systemCode + "_" + gardenAuthCode.getAuthCode() + "_" + gardenAuthCode.getOrginId();
            //����԰��ID���Ҽ�Ʊ�豸
            List<Esbaccessequiptab> checkMachines = authCodeDao.findCheckMachines(Long.parseLong(gardenAuthCode.getOrginId()));
            List<AuthCode> checkMachineAuthCodes = new ArrayList<AuthCode>();

            if (checkMachines != null && !checkMachines.isEmpty()) {
                for (int j = 0; j < checkMachines.size(); j++) {
                    Esbaccessequiptab checkMachine = checkMachines.get(j);
                    AuthCode checkMachineAuthCode = authCodeDao.getAuthCode(CodeType.CHECKMACHINE.getCode(), checkMachine.getId().getIaccessequipid().toString());
                    if (checkMachineAuthCode != null && "01".equalsIgnoreCase(checkMachineAuthCode.getBaiduStatus())) {
                        checkMachineAuthCodes.add(checkMachineAuthCode);
                    }
                }
            }
            if (checkMachineAuthCodes != null && !checkMachineAuthCodes.isEmpty()) {
                String[] deviceIds = new String[checkMachineAuthCodes.size()];
                for (int j = 0; j < checkMachineAuthCodes.size(); j++) {
                    AuthCode checkMachineAuthCode = checkMachineAuthCodes.get(j);
                    if (checkMachineAuthCode.getAuthCode().length() == 7) {//�����Զ������豸��
                        deviceIds[j] = systemCode + "_" + checkMachineAuthCode.getAuthCode() + "_" + checkMachineAuthCode.getOrginId();
                    } else {//�ٶ��豸��
                        deviceIds[j] = checkMachineAuthCode.getAuthCode();
                    }
                }
                Response response = BaiduClient.addGroupDevice(groupId, deviceIds);
                if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
                    if (!"1000".equals(response.getCode())) {
                        throw new RuntimeException(response.getMessage());
                    }
                } else {
                    throw new RuntimeException(response.getMessage());
                }
            }
        }
    }

    public Esbaccessequiptab findCheckMachine(Long accid) {
        return authCodeDao.findCheckMachine(accid);
    }
}
