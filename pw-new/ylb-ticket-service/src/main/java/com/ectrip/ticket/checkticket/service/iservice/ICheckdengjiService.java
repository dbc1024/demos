package com.ectrip.ticket.checkticket.service.iservice;

import com.ectrip.base.util.ResultBean;

public interface ICheckdengjiService {
    /**
     * 查询对应票的类别 （导游、员工、票）
     * Describe:
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return
     * return:String
     * Date:2012-12-20
     */
    public String CheckPassCrad(String szticketprintno);
    /**
     * 导游验证 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     * @param carno
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-14
     */
    public ResultBean changeCheckDaoyou(String carno) throws Exception;
    /**
     * 员工登记指纹
     * Describe:
     * @auth:yuanchengjun
     * @param carno
     * @return
     * return:ResultBean
     * Date:2012-12-20
     */
    public ResultBean changeCheckEmployee(String carno);
    /**
     * 票务登记指纹
     * Describe:
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return
     * return:ResultBean
     * Date:2012-12-20
     */


    public ResultBean changeCheckTicket(String szticketprintno);

    public int saveZwdengji(String type, String ticketno, String ziwenno);
}

