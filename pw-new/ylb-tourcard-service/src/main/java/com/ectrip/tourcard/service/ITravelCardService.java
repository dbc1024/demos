package com.ectrip.tourcard.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2017/10/18
 **/
public interface ITravelCardService {

    public Map<String, Object> sendMessage(String revmbno);

    /**
     * unused
     * @param param
     * @return
     */
    @Deprecated
    public Map<String, Object> saveUserBank(String[] param);
    public Map<String, Object> userTravelCard(String[] param);
    public Map<String, Object> getTicketInfo(String[] param);
    public String newSaveOrder(String xml, String orid) throws Exception;
    public Map<String, Object> getUserCard(String[] param);
    public Map<String, Object> orderRefused(String[] params ) throws IllegalAccessException,
            InvocationTargetException, ParseException;
    public Map<String, Object> getUserJsInfo(String params[]);
    @Deprecated
    public Map<String, Object> getUserBankList(String params[]);
    public Map<String, Object> jiebangUserBank(String [] params);
    public Map<String, Object> getUserRealNameInfo(String[] params);//获取用户实名信息
    public Map<String, Object> getUserRealNameInfoV2(String[] params, boolean isNewVersion);
    public Map<String, Object> getUserBankInfoList(String[] params);
    public Map<String, Object> saveUserBankFlow(String params[]);
    public Map<String, Object> getbankBIn(String[] params);
    public List findOrderAll(String usid, String pwd, String orid, String iscenictype, String ddzt);
    public Map<String, Object> userTravelCardl(String[] param);
}
