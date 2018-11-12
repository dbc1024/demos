package com.ectrip.ec.order.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface IPayOrderService {
    /**
     *
     * Describe:�������� ���ݶ�����Ϣ
     *
     * @auth:yangguang
     * @param orid
     * @param payid
     * @param mont
     * @param bank
     * @param isok
     * @param ddzt
     * @param orderType
     * @param zffs
     * @param note
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:int Date:2011-11-18
     */
    public int updateOrderStatus(String orid, String payid, String mont, String bank, int isok, String ddzt, String orderType, String zffs,
                                 String note, String zhusid) throws Exception;

    /**
     * ɢ���û����Ķ���״̬ Describe:
     *
     * @auth:huangyuqi
     * @param orid
     * @param payid
     * @param mont
     * @param bank
     * @param isok
     * @param ddzt
     * @param orderType
     * @param zffs
     * @param note
     * @param zhusid
     * @return
     * @throws Exception
     *             return:int Date:2012-2-23
     */
    public int updateOrderByShanKe(String orid, String payid, String mont, String bank, int isok, String ddzt, String orderType,
                                   String zffs, String note, String zhusid) throws Exception;

    /**
     *
     * Describe:���ݶ�����Ϣ ��֤ �տ���
     *
     * @auth:yangguang
     * @param orid
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:List Date:2011-11-18
     */
    public List volidateTicketDayControl(String orid) throws IllegalAccessException, InvocationTargetException;

    /**
     *
     * Describe:���ݶ��������֤�˴ο���
     *
     * @auth:yangguang
     * @param orid
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:List Date:2011-12-1
     */
    public List volidateTicketTripControl(String orid) throws IllegalAccessException, InvocationTargetException, ParseException;

    public void payFailture(String orid, String zfusid, String errorype);

    public int volidationCredit(String usid, String orid);


    /**
     *
     * Describe:
     * @auth:yangguang
     * @param orid
     * @return
     * return:List
     * Date:2012-5-25
     */
    public List validUserjifen(String orid,String zfusid);


    /**
     *
     * Describe:��������
     * @auth:yangguang
     * @param orid
     * @return
     * return:List
     * Date:2012-5-29
     */
    public List orderJifen(String orid,String zfusid);

    public void addPaybackInfo(String orid, String backurl, String usid);

    public void saveSplitOrder(String oldOrid,String[] orids,String zfusid,String url) throws Exception;
}
