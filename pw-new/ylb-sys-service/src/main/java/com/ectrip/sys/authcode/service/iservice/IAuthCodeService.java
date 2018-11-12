package com.ectrip.sys.authcode.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.authcode.dto.AuthCodeInputDto;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.sys.model.authcode.JsonMessage;
import com.ectrip.sys.model.authcode.OtherCodePojo;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;

/**
 * Created by chenxinhao on 2017/2/23.
 */
public interface IAuthCodeService extends IGenericService {

    AuthCode getAuthCode(String codeType, String orginId);

    List<AuthCode> findAuthCodes(String codeType, String baiduStatus);

    PaginationSupport searchAuthCodes(AuthCodeInputDto dto, int page, int pageSize, String url);

    void saveSystemCode(JsonMessage message);

    List searchNoAuthItems(String codeType);

    void saveOtherCode(String systemCode, String signKey, OtherCodePojo pojo);

    void registerCheckMachine(String systemCode, List<AuthCode> authCodes);

    /**
     * ע��ٶ��豸
     * @param systemCode
     * @param authCode
     */
    void registerCheckMachine(String systemCode, AuthCode authCode);

    void cancelCheckMachine(String systemCode, AuthCode authCode);

    void registerGarden(String systemCode, List<AuthCode> authCodes);

    void bindMachine(String systemCode, List<AuthCode> authCodes);

    Esbaccessequiptab findCheckMachine(Long accid);
}
