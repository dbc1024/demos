package com.ectrip.ticket.sale.service.iservice;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.util.ResultBean;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
public interface ISaleCenterService {
    /*
     * *出票员登录接口
     */
    public ResultBean emplogin(Long iscenicid, String userid, String password)
            throws Exception;

    /*
     * 根据IP,系统设置的服务商ID查找对应窗口
     */
    public ResultBean getEsbticketwintabByIP(Long iscenicid, String ip,
                                             Long byisuse);

    /*
     * 根据窗口ID 找到对应售票点
     */
    public ResultBean getEsbticketstationtabByID(Long id, Long byisuse);

    /*
     * 根据售票点code 找到对应售票点
     */
    public Esbticketstationtab getEsbticketstationtab(String szstationcode);

    /*
     * 取出所有景区服务商
     */
    public ResultBean getscenic();

    public ResultBean saleReport(Long iemployeeid, String rzti, String ldti);

    public ResultBean getprintmanage(Long iscenicid, Long ibusinessid);

    public ResultBean getsodeprintmanage(Long iscenicid, Long ibusinessid);

    /*
     * 取出所有景区服务商
     */
    public ResultBean getbusiness();

    /**
     * 根据景区及日期读取对应的产品数量控制 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param stdt
     * @return return:ResultBean Date:2011-10-26
     */
    public ResultBean getTripcontrol(Long iscenicid, String stdt);

    /**
     * 返回票价列表 Describe:
     *
     * @auth:yuanchengjun
     * @param iticketwinid
     *            售票窗口
     * @param iemployeeid
     *            售票员
     * @param ibusinessid
     *            业务类别
     * @param stdt
     *            游览日期
     * @return return:ResultBean Date:2011-10-26
     */
    public ResultBean getTicketPrice(Long iticketwinid, Long iemployeeid,
                                     Long ibusinessid, String stdt);
    
    public ResultBean getTicketPrice(Long iticketwinid, Long iemployeeid,
                                     Long ibusinessid, String stdt,String jsfz);
    
    public ResultBean getTicket(Long iscenicid);
    
    public ResultBean getcustom(Long ibusinessid);
    
    public Custom  getcustom(String  usid);
    public ResultBean getemployeecard(Long iscenicid);

    public ResultBean getTrip(Long itickettypeid, String stdt);
    
    public ResultBean getAllTrip(Long iscenicid, String stdt);

    public ResultBean getEmployee(String userid);

    public ResultBean getAllcustom();

    public ResultBean getProductcontrol(Long itickettypeid, Long tripid,
                                        String stdt);

    public ResultBean getProductdatacontrol(Long itickettypeid, String stdt);

    public ResultBean getReservecontrol(Long itickettypeid, String usid,
                                        String stdt, Long tripid);

    public ResultBean updatereserve(List productcontrollist, String usid);

    public ResultBean updatecontrol(List productcontrollist) throws Exception;

    public ResultBean saveorder(String salesvouchers,
                                String salesvoucherdetails, String comticketsalesdetails,
                                Long maxid, String szsalesvoucherno,String url) throws Exception;


    public ResultBean saveorder41(String salesvouchers,
                                  String salesvoucherdetails, String comticketsalesdetails,
                                  Long maxid, String szsalesvoucherno,String...param) throws Exception;

    @Deprecated
    public ResultBean saveorder405(String salesvouchers,
                                   String salesvoucherdetails, String comticketsalesdetails,
                                   Long maxid, String szsalesvoucherno) throws Exception;

    public ResultBean soldticketlist(Long isalesvoucherid, Long iticketstationid);

    public ResultBean salezdetaillist(Long isalesvoucherid,
                                      Long iticketstationid);

    public ResultBean updatecancelcontrol(List productcontrollist);

    public ResultBean updatecancelReserve(List productcontrollist, String usid);
    public ResultBean getT_order(String carno, Long iscenicid);
    public ResultBean getT_orderByWin(String carno, Long iscenicid,Long winid);
    public ResultBean getT_orderauto(String carno, Long iscenicid);
    public ResultBean getM_orderzfusidstdt(String orid);

    public ResultBean getT_orderbyorid(String orid, Long iscenicid);

    public ResultBean getT_orderlist(String orid, Long iscenicid);

    public ResultBean getT_orderlistty(String orid, Long iscenicid);

    public ResultBean ChupiaoT_order(String orid, Long iscenicid);

    public ResultBean getT_zorderlist(String orid, Long iscenicid);

    public ResultBean getT_zorderlistbyorderlistid(Long orderlistid,
                                                   String orid, Long iscenicid);
    public ResultBean ChupiaoTRealname(String orid, Long iscenicid);

    public ResultBean ChupiaoT_orderlist(String orid, Long iscenicid);

    public ResultBean ChupiaoT_zorderlist(String orid, Long iscenicid);

    public ResultBean updateT_order(String orid, Long iscenicid,
                                    Long iemployeeid, Double mont);

    public ResultBean updatenojinquT_order(String orid, Long iscenicid,
                                           Long iemployeeid, Double mont);

    public ResultBean savetorder(T_order t_order, List listorder,
                                 List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,String url)
            throws Exception;

    public ResultBean savebenditorder(String orid, Long iscenicid,
                                      Long iemployeeid, Long iticketwinid, Long maxid) throws Exception;

    public ResultBean savetorder41(T_order t_order, List listorder,
                                   List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,List trlist,String param1)
            throws Exception;

    @Deprecated
    public ResultBean savetorder405(T_order t_order, List listorder,
                                    List listzorder, Long iemployeeid, Long iticketwinid, Long maxid)
            throws Exception;

    public ResultBean getTicketmesssage(String szticketprintno);

    public ResultBean getOrderTicketmesssage(String szticketprintno);

    public String getreturnticketconsole(String szticketprintnos,
                                         String iztickettypeids);

    public ResultBean savereturnticket(
            Stssalesvouchertab oldstssalesvouchertab, String szticketprintnos,
            String iztickettypeids, Long newiticketwinid, Long iemployeeid);

    public Stssalesvouchertab getStssalesvouchertabbyprintno(
            String szticketprintno);

    public ResultBean savereturntickets(
            Stssalesvouchertab oldstssalesvouchertab, List returnmodellist,
            Esbticketwintab e, Long iemployeeid, String szsalesvoucherno,
            Long maxid, Long isqt, Long forceemid, String forcenote, Long issx,
            String productcontrols,String url) throws Exception;

    public ResultBean savebendireturntickets(
            Stssalesvouchertab oldstssalesvouchertab, List returnmodellist,
            Esbticketwintab e, Long iemployeeid, String szsalesvoucherno,
            Long maxid, Long isqt, Long forceemid, String forcenote, Long issx,
            String productcontrols,String url) throws Exception;

    public ResultBean savetorderreturntickets(
            Stssalesvouchertab oldstssalesvouchertab, List returnmodellist,
            String productcontrols, Esbticketwintab e, Long iemployeeid,
            String szsalesvoucherno, Long maxid, Long isqt, Long forceemid,
            String forcenote, Long issx,String url) throws Exception;

    public ResultBean savebenditorderreturntickets(
            Stssalesvouchertab oldstssalesvouchertab, List returnmodellist,
            String productcontrols, Esbticketwintab e, Long iemployeeid,
            String szsalesvoucherno, Long maxid, Long isqt, Long forceemid,
            String forcenote, Long issx,String url) throws Exception;

    public ResultBean getreturnticketsconsole(List returnmodellist,
                                              Stssalesvouchertab stssalesvouchertab, Long isqt);

    // public Long updateMaxPk(String columnName, String[] keys, String[]
    // values,String tableName);
    public String updateMaxNo(String Saleid) throws SQLException;

    public Esbticketwintab getEsbticketwintab(Long newiticketwinid);

    public ResultBean getcancelproductcontrol(List plist);

    public ResultBean getStssalesvouchertab(Long isalesvoucherid,
                                            Long iticketstationid) throws IllegalAccessException,
            InvocationTargetException;

    public List ticketreprint(Long iserialnum, Long iticketstationid);

    public List getcancelprint(Stssalesvouchertab ts, Stssalesvouchertab ys);

    public Stssalesvouchertab queryStssalesvouchertab(Long isalesvoucherid,
                                                      Long iticketstationid);

    public Edmcrowdkindpricetab getEdmcrowdkindpricetab(Long icrowdkindpriceid);

    public ResultBean savecancelt_order(String orid, Long iscenicid,
                                        Double mont, Long iemployeeid, String szsalesvoucherno,
                                        String message, Long isqt, Long forceemid,String url) throws Exception;

    /** 取用户的预付款余额 */
    public float getsumjifen(String usid);

    public Long updatevouchersequence();

    public void saveUseryfk(String usid, String orid, int types, String yfkfs,
                            Double mont, Double tpsx, String note) throws Exception;

    public ResultBean savecancelstopraftorder(String orid, Long iscenicid,
                                              String neworid, Long iemployeeid) throws Exception;

    public ResultBean getStssalesvouchertablist(Long iemployeeid, String stdt,
                                                String eddt);

    public ResultBean resoldticketlist(Long isalesvoucherid,
                                       Long iticketstationid, String ornm, String szregionalname,
                                       String corpname);

    public ResultBean updatecancelordercontrol(List productcontrollist,
                                               String orid, String szsalesvoucherno);

    public Long savezhuceticketwin(Esbticketwintab etw);

    public List getEsbticketstationtabByiscenicid(Long iscenicid, Long byisuse);

    public ResultBean empztlogin(Long iscenicid, String userid, String password)
            throws Exception;

    public Esbticketwintab getEsbticketwintabBymac(Long iscenicid, String mac);

    public ResultBean updateflcancelcontrol(List productcontrollist);

    public ResultBean updatefhordercancelProductcontrol(
            List productcontrollist, String orid, String szsalesvoucherno);

    public int changepassword(String userid, String oldpassword,
                              String newpassword);

    public ResultBean getFtp(String iticketstationid);

    public ResultBean getForce();

    public ResultBean updatecptdorder(String productcontrols, String orid,
                                      Long iscenicid, double tpmont, double tpsx, String zfusid,
                                      String neworid, String mo, String yo, String to, String tl,
                                      String tzl) throws Exception;

    public ResultBean updatehfcptdorder(String productcontrols, String orid,
                                        Long iscenicid, double tpmont, double tpsx, String zfusid,
                                        String neworid);

    public ResultBean getcheckTicketmesssage(String szticketprintno,String url);

    public String getoridbyticketprintno(String szticketprintno);

    public int updateprintbyprintno(String szsalesvoucherno,
                                    String szticketprintno, String printtype, Long iemployeeid,String newprintno);
    public ResultBean getsysparcs(String pmky, String pmcd);
    
    public ResultBean getsysparcs(String pmky);
    
    public ResultBean querynotprint(Long iemployeeid,String url);

    public Edmtickettypetab getEdmtickettypetab(Long itickettypeid);

    public ResultBean getPrdtripvenuemanage(Long itickettypeid, Long tripid,
                                            String stdt);

    public void UpdateCheckcount(String messages);

    public void UpdateRaftcheck(String massages);

    public ResultBean UpdateChangecheckticket(String massages);

    public ResultBean updatecphfT_order(String orid, Long iscenicid)
            throws Exception;

    public ResultBean savebendiorder(String salesvouchers,
                                     String salesvoucherdetails, String comticketsalesdetails,
                                     String productcontrols, Long maxid, String szsalesvoucherno)
            throws Exception;

    public ResultBean reprintbyorid(String szsalesvoucherno, Long iscenicid,
                                    String ornm, String szregionalname, String corpname);

    public void updatestsprint(Long isalesvoucherid, Long iticketstationid);

    public Stssoldtickettab queryStssoldtickettab(String szticket);

    public ResultBean getorderbyorid(String orid, Long iscenicid);

    public List ticketreprintbylb(String cdcs, String cdz, Long iscenicid,String param);

    /**
     * Describe:获取售票员的最小票号
     *
     * @auth:aozhuozu
     * @param iscenicid
     *            服务商编号
     * @param ticketid
     *            票类
     * @param userid
     *            售票员编号
     * @param tourDate
     *            游览日期
     * @param otherParm
     * @return return:ResultBean Date:2012-8-8
     */
    public ResultBean checkingFirstBarcodet(Long iscenicid, Long ticketpriceid,
                                            Long userid, String tourDate, String... otherParm);

    /**
     *
     * Describe:判断票号是否存在售票员手中
     * @author:lijingrui
     * @param empid
     * @param itickettypeid
     * @param ticketcode
     * @return
     * return:boolean
     * Date:2014-6-24
     */
    public boolean isExistcode(Long empid, Long itickettypeid, String ticketcode);

    /**
     *
     * Describe:获取数量为num后售票员手中的最小票号
     * @auth:lijingrui
     * @param iscenicid  服务商编号
     * @param ticketpriceid  票类
     * @param userid   售票员编号
     * @param tourDate 游览日期
     * @param num		数量
     * @param otherParm
     * @return
     * return:ResultBean
     * Date:2013-4-13
     */
    public ResultBean checkingFirstBarcodet(Long iscenicid, Long ticketpriceid,
                                            Long userid, String tourDate,int num, String... otherParm);

    /**
     * Describe:取出票号组合
     *
     * @auth:aozhuozu
     * @param iscenicid
     *            服务商编号
     * @param ticketid
     *            票类
     * @param firstBarcode
     *            起始条码
     * @param count
     *            数量
     * @param tourDate
     *            游览日期
     * @param userid
     *            售票员编号
     * @param otherParm
     * @return return:ResultBean Date:2012-8-8
     */
    public ResultBean checkingPrefabricateTicket(Long iscenicid,
                                                 Long ticketpriceid, String firstBarcode, int count,
                                                 String tourDate, Long userid, String... otherParm);

    /**
     * 取出ICID
     *
     * @param typeid
     *            类型
     * @param id
     *            要转换的ID
     * @return
     */
    public ResultBean getICID(String typeid, Long id);

    /**
     *
     * Describe:判断IC库存数量
     *
     * @auth:lijingrui
     * @param iscenicid
     *            服务商ID
     * @param ticketpriceid
     *            票价格ID
     * @param count
     *            数量
     * @param tourDate
     *            日期
     * @param userid
     *            售票员编号
     * @param otherParm
     * @return return:String Date:2012-8-31
     */
    public String personCheckoutiaMount(Long iscenicid, Long ticketpriceid,
                                        int count, String tourDate, Long userid, String... otherParm);

    public ResultBean getzhiwen(String carno);

    /**
     * 增加订单操作日志
     *
     * @param orid
     */
    public void addOrderLog(String orid, Long iscenicid, Long iemployeeid,
                            String yorid);

    /**
     * 挂失票务
     *
     */
    public ResultBean saveguashi(Long iemployeeid, String szticketprintno);

    /**
     * 解挂票务
     *
     */
    public ResultBean savejiegua(Long iemployeeid, String szticketprintno);

    /**
     * 延期票务
     *
     */
    public ResultBean saveyanqi(Long iemployeeid, Long iscenicid,
                                Long iticketwinid, String szticketprintno,
                                double iaccountreceivable, double iacceptmoney, double igivechange,
                                String zffs, String szsalesvoucherno, Long maxid) throws Exception;

    /*
     * 根据IP,系统设置的服务商ID查找对应窗口
     */
    public ResultBean getWinAndStationInfo(Long iscenicid, String mac);

    /**
     * 根据价格编号 ，数量,游览日期，计算产品收款总金额,对应优惠数量
     *
     */
    public ResultBean gettickmont(Long icrowdkindpriceid, Long numb, String stdt);

    /**
     * 根据价格编号 ，数量,游览日期，计算产品收款总金额,对应优惠数量
     *
     */
    public ResultBean gettickmont(Long icrowdkindpriceid, Long numb, String stdt,String... otherParm);

    /**
     * 远程保存推订单
     */
    public ResultBean savetuiding(String mo, String yo, String to, String tl,
                                  String tzl);

    /**
     * 根据姓名模糊查询导游 Describe:
     *
     * @auth:yuanchengjun
     * @param lname
     * @return return:ResultBean Date:2013-2-23
     */
    public ResultBean getDaoyou(String lname);

    /**
     * 闸机状态上传 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     * @param typestatus
     * @param byisuse
     *            return:void Date:2013-2-21
     */
    public void updatestatus(Long accid, String typestatus, Long byisuse);

    public ResultBean savebenditorder41(String orid, Long iscenicid,
            Long iemployeeid, Long iticketwinid, Long maxid, List printnolist);

    public ResultBean getTicketPricebyiscenicid(Long iticketwinid,
                                                Long iemployeeid, Long ibusinessid, String stdt, Long iscenicid);
    public ResultBean getTicketPricebyiscenicid(Long iticketwinid,
                                                Long iemployeeid, Long ibusinessid, String stdt, Long iscenicid,String jsfz);
    public ResultBean getordercs(Long ibusinessid);

    public ResultBean getGalsourceregiontab();

    public ResultBean getICcardtypelist();
    
    public ResultBean getfapiao(Long isalesvoucherid, Long iticketstationid,
                                Long iemployeeid, Long iticketwinid,String corpname,String fpno);
    public void updatefapiao(Long isalesvoucherid, Long iticketstationid);
    public ResultBean getfapiao(String orid, Long iscenicid, Long iemployeeid,String corpname,String fpno);
    public void updatefapiaobyorid(String orid, Long iscenicid);
    public ResultBean getempordermassage(Long iemployeeid);

    public Stssalesvouchertab saveStssalesvouchertab(String salesvouchers,
                                                     Long maxid, String szsalesvoucherno, Stssalesvouchertab s);
    public Stssalessettlementtab saveStssalessettlementtab(
            Stssalesvouchertab s, String zffs);
    public Stssalesvoucherdetailstab saveStssalesvoucherdetailstab(
            Stssalesvouchertab s, String isalesvoucherdetail,
            String szticketprintno);
    public List saveStssoldtickettabug0make00(List cdetaillist,
                                              Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
                                              String szstationcode, String szsceniccode, String sztickettypecode,
                                              Long icrowdkindid);
    public List saveStssoldtickettabug1make00(List cdetaillist,
                                              Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
                                              String szstationcode, String szsceniccode, String sztickettypecode,
                                              Long icrowdkindid);
    public List saveStssoldtickettabug0make01(List cdetaillist,
                                              Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
                                              String szticketprintno, Long icrowdkindid);
    public List SaveStssoldticketsubtabug0(List cdzetaillist, List zdetaillist,
                                           Stssalesvouchertab s, Stssalesvoucherdetailstab sd,
                                           Long icrowdkindid);
    public List SaveStssoldticketsubtabug1(List cdzetaillist, List zdetaillist,
                                           Stssalesvouchertab s, Stssalesvoucherdetailstab sd,
                                           Long icrowdkindid);
    public List SaveStsschecktabug0(List cdchecklist, List zdetaillist,
                                    List cdetaillist, Stssalesvouchertab s,
                                    Stssalesvoucherdetailstab sd, Long icrowdkindid);
    public List SaveStsschecktabug1(List cdchecklist, List zdetaillist,
                                    List cdetaillist, Stssalesvouchertab s,
                                    Stssalesvoucherdetailstab sd, Long icrowdkindid);
    public boolean addPersonaldetails(Long empid, String[] salesvoucherdetail);
    public Stssalesvouchertab savetStssalesvouchertab(T_order t_order,
                                                      Long iemployeeid, Long maxid, Long iticketwinid,String param1);
    public Stssalessettlementtab savetStssalessettlementtab(
            Stssalesvouchertab s, T_order t_order,String param1);
    public Stssalesvoucherdetailstab savetStssalesvoucherdetailstab(
            Stssalesvouchertab s, T_orderlist tlist);
    public boolean checkEditIompersonHouse(Long empid, String[] iserial,
                                           Long iscenicid, Long itickettypeid, Long amount);
    public List SavetStscomticketsalesdetailstab(List zdetaillist,
                                                 Stssalesvouchertab s, T_orderlist tlist, List listzorder);
    public ResultBean checksavetorder(T_order t_order, List listorder,
                                      List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,
                                      Long igardengateid, String printno,String url) throws Exception;
    public ResultBean getT_order(String orid);

    public Custom queryCustom(String usid);

    public ResultBean getGuidenitices(String szsalesvoucherno,String daoyouname,String cardno) throws Exception;
    public ResultBean saveGuidenitices(String[] parameters) throws Exception;
    public ResultBean saveGuideniticesJs(String[] parameters) throws Exception;
    public ResultBean ticketchupiao(Long isalesvoucherid, Long iticketstationid, Long rePrint);
    public ResultBean ticketreprintjxzh(String cdcs, String iserialnums,
                                        Long iscenicid);
    public Sysparv5 getSysparv5(String pmky,String pmcd);
    public void saveSaleLog(String userid,String winid,String logType,String note);
    public ResultBean findTicketPrinNo(String ticketPrintNo,Long tickettypeid,Long numb);
    /**
     * 获取当前时间的最后一条交易数据
     * @param iscenicid
     * @param winid
     * @param iemployeeid
     * @return
     */
	public String getLastSaleData(String iscenicid, String winid,String iemployeeid);
	
	/**
	 * 获取分时预约的票的对应的时段和库存
	 * @param productCode 产品价格
	 * @param choiceDate  选中的日期
	 * @return
	 */
    public ResultBean getTicketTimeCount(String productCode,String choiceDate);


    /**
     * 查询身份证当天是否已经有订单或票信息
     * @return
     */
    public ResultBean isExistIDcardToday(String iscenicID,String idCard,String choiceDate,String url);
    
    public Object convertMap(Class type, Map map) throws Exception ;
}
