package com.ectrip.sys.authcode.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.authcode.dto.AuthCodeInputDto;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;

/**
 * Created by chenxinhao on 2017/2/23.
 */
public interface IAuthCodeDao extends IGenericDao {

    PaginationSupport searchProviderAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchProductAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchPriceAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchGardenAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchCheckMachineAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchStationAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    PaginationSupport searchSaleMachineAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url);

    AuthCode getAuthCode(String codeType, String orginId);

    List searchProvider(String codeType);

    List searchProduct(String codeType);

    List searchPrice(String codeType);

    List searchGarden(String codeType);

    List searchCheckMachine(String codeType);

    List searchStation(String codeType);

    List searchSaleMachine(String codeType);

    Esbaccessequiptab findCheckMachine(Long accid);

    Esbgardengatetab findGarden(Long gardenId);

    List<Esbaccessequiptab> findCheckMachines(Long gardenId);
}
