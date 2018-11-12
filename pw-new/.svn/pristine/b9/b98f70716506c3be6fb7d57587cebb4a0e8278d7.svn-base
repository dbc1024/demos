package com.ectrip.ticket.webservice.sale;

import java.lang.reflect.InvocationTargetException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.ectrip.base.util.ResultBean;




/**
 * @desc 窗口售票接口
 * @author jy
 *
 */

@WebService
public interface ISaleWebService {
	
    /**
     * 售票员修改密码
     *
     * @param userid
     * @param password
     * @return -1 无效用户 -2 所属公司未绑定服务商 -3 你所在公司不能在该售票窗口售票 -4 密码错误 -5
     *         今日您已经登陆错误次数超过5次 0 您不具有售票权限，不能登陆 1 登陆成功
     */

    public int changepassword(@WebParam(name="userid")String userid, @WebParam(name="oldpassword")String oldpassword,
    		@WebParam(name="newpassword")String newpassword) throws Exception;

    /**
     * 强制退订授权登录 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param userid
     * @param password
     * @return
     * @throws Exception
     *             return:int Date:2012-1-8
     */
    public String empztlogin(@WebParam(name="iscenicid") Long iscenicid, @WebParam(name="userid") String userid, @WebParam(name="password") String password)
            throws Exception ;

    public String getEmployee(@WebParam(name="userid") String userid) ;

    /**
     * 测试String
     *
     * @return
     * @throws Exception
     */
    public String getResultBean() throws Exception ;

    /**
     * 根据ID、byisuse查找售票点
     *
     * @param id
     * @return
     */

    public String getEsbticketstationtabByID(@WebParam(name="id")Long id, @WebParam(name="byisuse") Long byisuse) ;

    /**
     * 根据景区ID（iscenicid） IP、byisuse查找售票窗口
     *
     * @param ip
     * @return
     */

    public String getEsbticketwintabByIP(@WebParam(name="iscenicid") Long iscenicid, @WebParam(name = "ip") String ip,@WebParam(name="byisuse") Long byisuse) ;

    /**
     * 取出景区类型的服务商
     */
    public String getscenic();

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param ibusinessid
     * @return return:String Date:2012-3-26
     */
    public String getprintmanage(@WebParam(name="iscenicid")Long iscenicid, @WebParam(name="ibusinessid")Long ibusinessid) ;

    /**
     * 取出所有业务
     */
    @WebMethod
    public String getbusiness() ;
    
    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    @WebMethod(operationName = "getTicketPriceByJsfz")
    public String getTicketPriceByJsfz(@WebParam(name="iticketwinid") Long iticketwinid, @WebParam(name="iemployeeid") Long iemployeeid,
		    @WebParam(name="ibusinessid") Long ibusinessid, @WebParam(name="stdt") String stdt) ;
   
    /**
     * 自助机获取票信息
     * @param iticketwinid 窗口Id
     * @param iemployeeid 员工Id
     * @param stdt  游览日期
     * @return
     */
    @WebMethod(operationName = "getTicketPriceBySelf")
    public String getTicketPriceBySelf(@WebParam(name="iticketwinid")  Long iticketwinid,@WebParam(name="iemployeeid") Long iemployeeid,@WebParam(name="stdt") String stdt);
    
    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    public String getTicketPrice(@WebParam(name="iticketwinid") Long iticketwinid, @WebParam(name="iemployeeid") Long iemployeeid,
    		@WebParam(name="ibusinessid") Long ibusinessid, @WebParam(name="stdt") String stdt, @WebParam(name="jsfz") String jsfz);

    /**
     * 取出该出票员所在窗口的所售的指定服务商的票的价格
     * getTicketPrice
     */
    public String getTicketPricebyiscenicid(@WebParam(name="iticketwinid") Long iticketwinid, @WebParam(name="iemployeeid") Long iemployeeid,
    		@WebParam(name="ibusinessid") Long ibusinessid, @WebParam(name="stdt") String stdt, @WebParam(name="iscenicid") Long iscenicid) ;

    /**
     * 取出该出票员所在窗口的所售的指定服务商的票的价格
     */
    @WebMethod(operationName = "getTicketPricebyiscenicidByJsfz")
    public String getTicketPricebyiscenicidByJsfz(@WebParam(name="iticketwinid") Long iticketwinid, 
								    		@WebParam(name="iemployeeid") Long iemployeeid,
								    		@WebParam(name="ibusinessid") Long ibusinessid,
								    		@WebParam(name="stdt") String stdt, 
								    		@WebParam(name="iscenicid") Long iscenicid,
								    		@WebParam(name="jsfz") String jsfz);

    /**
     * 读取竹筏数量控制数量信息 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param stdt
     * @return return:String Date:2011-10-11
     */
    public String getTripcontrol(@WebParam(name="iscenicid") Long iscenicid, @WebParam(name="stdt") String stdt,String url) ;

    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    public String getTicket(@WebParam(name="iscenicid")Long iscenicid) ;

    /**
     * 根据业务读取用户 Describe:
     *
     * @auth:yuanchengjun
     * @param ibusinessid
     * @return return:String Date:2011-10-27
     */
    @WebMethod
    public String getcustom(@WebParam(name="ibusinessid")Long ibusinessid);
    
    public String getemployeecard(@WebParam(name="iscenicid") Long iscenicid) ;

    public String getTrip(@WebParam(name="itickettypeid") Long itickettypeid,@WebParam(name="stdt") String stdt,@WebParam(name="url") String url) ;

    public String getAllTrip(@WebParam(name="iscenicid")Long iscenicid,@WebParam(name="stdt") String stdt,@WebParam(name="url") String url) ;

    /**
     *
     * 销售受限产品的产品,趟次和日期读取该产品趟次数量控制:
     *
     * @see com.ectrip.sale.service.iservice.ISaleCenterService#getProductcontrol(java.lang.Long,java.lang.Long,
     *      java.lang.String)
     * @param iscenicid
     * @param stdt
     * @return byisduty=1 现场销售数量控制 0 现场和网上销售同时数量控制
     * @author yuanchengjun Date:2011-10-27 返回数据为空时
     *
     */
    public String getProductcontrol(@WebParam(name="itickettypeid") Long itickettypeid,@WebParam(name="tripid") Long tripid,@WebParam(name="stdt")String stdt,@WebParam(name="url")String url) ;

    /**
     *
     * 销售受限产品的产品,趟次和日期读取该产品趟次数量控制和日控制数量:
     *
     * @see com.ectrip.sale.service.iservice.ISaleCenterService#getProductcontrol(java.lang.Long,java.lang.Long,
     *      java.lang.String)
     * @param iscenicid
     * @param stdt
     * @return byisduty=1 现场销售数量控制 0 现场和网上销售同时数量控制
     * @author yuanchengjun Date:2011-10-27 返回数据为空时
     *
     */
    public String getProductdatacontrol(@WebParam(name="itickettypeid")Long itickettypeid,@WebParam(name="stdt") String stdt,@WebParam(name="url")String url) ;

    public String getAllcustom() ;

    /**
     * 根据售票凭证id和售票点读取所有票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:String Date:2011-12-6 返回打印信息
     */
    public String soldticketlist(@WebParam(name="isalesvoucherid")Long isalesvoucherid, @WebParam(name="iticketstationid")Long iticketstationid) ;

    /**
     * 根据退订单凭证id和售票点读取所有票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:String Date:2011-12-6
     */
    public String salezdetaillist(@WebParam(name="isalesvoucherid")Long isalesvoucherid,@WebParam(name="iticketstationid")Long iticketstationid) ;
    /**
     * 根据身份证和服务商编号读取服务商网上已付款的订单信息
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:String Date:2011-11-7
     */
    public String getT_order(@WebParam(name="carno")String carno,@WebParam(name="iscenicid") Long iscenicid,String url);
    
    public String getT_orderOne(@WebParam(name="orid") String orid);
    /**
     * 根据身份证和服务商编号读取服务商网上已付款的订单信息
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:String Date:2011-11-7
     */
    public String getT_orderbyorid(@WebParam(name="orid")String orid,@WebParam(name="iscenicid") Long iscenicid,String url);

    public String getT_orderlist(@WebParam(name="orid") String orid,@WebParam(name="iscenicid")  Long iscenicid,String url) ;

    public String getT_zorderlistbyorderlistid(Long orderlistid,
                                                   String orid, Long iscenicid,String url);

    /**
     * *根据服务商,趟次和日期,接待用户usid读取该服务商产品的趟次预留量数据 Describe: p.byisuse=1 数量控制已经启用
     *
     * @param iscenicid
     * @param tripid
     * @param usid
     * @param stdt
     *
     * @return
     * @author yuanchengjun Date:2011-10-27
     */
    public String getReservecontrol(Long itickettypeid, String usid,
                                        String stdt, Long tripid,String url);

    /**
     * 根据票号读取该票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     *            票号
     * @return return:String Date:2011-11-18
     */
    public String getTicketmesssage(@WebParam(name="szticketprintno")String szticketprintno);

    /**
     * 根据一张票号读取同一订单中所有票数据 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:String Date:2011-11-22
     */
    public String getOrderTicketmesssage(String szticketprintno);

    /**
     * 根据票号(多票号为&连接),退订子票类,产生退订销售记录 Describe:退订同一个销售单号里面的同一种票(销售日期/竹筏趟次)等数据都相等的票
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @param iztickettypeid
     * @return return:String Date:2011-11-22
     */
    public String savereturnticket(String szticketprintnos,
                                       String iztickettypeids, Long newiticketwinid, Long iemployeeid,String url);

    /**
     * 根据票号(多票号为&连接),退订子票类,产生退订销售记录 Describe:退订同一个销售单号里面的同一种票(销售日期/竹筏趟次)等数据都相等的票
     * //"票号#产品&产品:票号#产品&产品"
     *
     * @auth:yuanchengjun
     * @param tickets
     * @param iztickettypeid
     * @return return:String Date:2011-11-22
     */
    public String saveorderreturnticket(@WebParam(name="tickets")String tickets,@WebParam(name="newiticketwinid")Long newiticketwinid,
    									@WebParam(name="iemployeeid")Long iemployeeid, @WebParam(name="isqt")Long isqt, 
    									@WebParam(name="forceemid")Long forceemid,@WebParam(name="forcenote")String forcenote,@WebParam(name="issx")Long issx) ;

    /**
     * 根据退订凭证ID+售票点ID 读取退票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:String 列数:3 1：mont 退订金额 2 settlementid 退款类别 3
     *             message 信息: 票号,票名,单价,金额,手续费&子票名称,开始时间,截至时间,实际售价
     *             ,数量,金额,手续费#子票名称,开始时间,截至时间,
     *             实际售价,数量,金额,手续费@票号,票名,单价,金额,手续费&子票名称,开始时间,
     *             截至时间,实际售价,数量,金额,手续费#子票名称,开始时间,截至时间,实际售价,数量,金额,手续费
     *             AWYA150008G6CB,团队三联票,100.0,100.0,40.0&竹筏票,2011-12-02
     *             17:00:00,2011-12-02
     *             17:30:00,100.0,1,100.0,40.0@AWYA150008G729,团队三联票
     *             ,100.0,100.0,40.0&竹筏票,2011-12-02 17:00:00,2011-12-02
     *             17:30:00,100.0,1,100.0,40.0 Date:2011-12-2
     */
    public String getStssalesvouchertab(Long isalesvoucherid,
                                            Long iticketstationid) throws IllegalAccessException,InvocationTargetException ;

    /**
     * 售票员登录
     *
     * @param userid
     * @param password
     * @return -1 无效用户 -2 所属公司未绑定服务商 -3 你所在公司不能在该售票窗口售票 -4 密码错误 -5
     *         今日您已经登陆错误次数超过5次 0 您不具有售票权限，不能登陆 1 登陆成功
     */

    public String emplogin(@WebParam(name="iscenicid")Long iscenicid, @WebParam(name="userid")String userid, @WebParam(name="password")String password) throws Exception;

    public String getDayTimes();

    /**
     * 票务重新打印 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintnos
     * @return return:String Date:2011-12-6
     */
    public String ticketreprint(String iserialnums, Long iscenicid,String url) ;

    public String getcancelprint(Long isalesvoucherid, Long iticketstationid,String url) ;

    /**
     * message itickettypeid,icrowdkindpriceid,pric,dtstartdate,dtenddate#
     * iztickettypeid ,znumb,dtstartdate,dtenddate&
     * iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     * &iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     *
     * @itickettypeid,icrowdkindpriceid,pric,dtstartdate,dtenddate#iztickettypeid,znumb,dtstartdate,dtenddate,tripid&iztickettypeid,znumb,dtstartdate,dtenddate,tripid&iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     *                                                                                                                                                                                                                   Describe
     *                                                                                                                                                                                                                   :
     * @auth:yuanchengjun
     * @param orid
     * @param iscenicid
     * @param mont
     *            新订单总金额
     * @param newiticketwinid
     * @param iemployeeid
     * @param message
     * @return return:String Date:2011-12-13
     */

    public String cancelt_order(String orid, Long iscenicid, Double mont,
                                    Long newiticketwinid, Long iemployeeid, String message, Long isqt,
                                    Long forceemid,String url);

    /**
     * 网上订单出票密码控制 Describe:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getPasswordcontrol();

    /**
     * 停排退订是否能出票口退订:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String gettptd() ;

    /**
     * 出票口是否可以退换票:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getcptd();

    /**
     * 出票口是否可以过期退订:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getgqptd() ;

    /**
     * 读取用户预付款
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:String Date:2011-11-7
     */
    public double getsumjifen(String usid,String url) ;

    public String cancelstopraftorder(String orid, Long iscenicid,
                                          Long newiticketwinid, Long iemployeeid,String url) ;

    /**
     * 根据售票员读取日期段的销售记录 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param stdt
     * @param eddt
     * @return return:String Date:2011-12-31
     */
    public String getStssalesvouchertablist(Long iemployeeid, String stdt,
                                                String eddt) ;

    /**
     * 重打印时根据售票凭证id和售票点读取所有票信息:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:String Date:2011-12-6
     */
    public String resoldticketlist(Long isalesvoucherid,
                                       Long iticketstationid, String szsalesvoucherno, Long iscenicid,String url);

    /**
     * 出票口注册售票点 Describe:
     *
     * @auth:yuanchengjun
     * @param mac
     * @param iscenicid
     * @return return:String Date:2012-1-7
     */
    public String zhuceticketwin(@WebParam(name = "mac") String mac, @WebParam(name="ip") String ip, @WebParam(name="iscenicid") Long iscenicid);

    /**
     * 取服务器上是FTP配置程序
     */

    public String getFtp(@WebParam(name = "iticketstationid") String iticketstationid);

    public String getForce() ;

    /**
     * 根据票号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:String Date:2011-11-22
     */
    public String getcheckTicketmesssage(@WebParam(name="szticketprintno")String szticketprintno,String url) ;

    /**
     * 根据票号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:String Date:2011-11-22
     */
    public String getoridbyticketprintno(String szticketprintno);

    /**
     * 根据票号,订单号，打印模式更新打印表: Describe:
     *
     * @auth:yuanchengjun
     * @param szsalesvoucherno
     * @param szticketprintno
     * @param printtype
     *            当printtype="03" 时 不用传 szsalesvoucherno
     * @return return:int Date:2012-3-21
     */
    @WebMethod(operationName="updateprintbyprintnoAndnewprintno")
    public int updateprintbyprintnoAndnewprintno(String szsalesvoucherno,
                                    String szticketprintno, String printtype, Long iemployeeid,String newprintno);
    public int updateprintbyprintno(@WebParam(name="szsalesvoucherno")String szsalesvoucherno,@WebParam(name="szticketprintno")String szticketprintno,
    								@WebParam(name="printtype")String printtype,@WebParam(name="iemployeeid") Long iemployeeid) ;

    public String updateprintbyprintno2(String szsalesvoucherno,
                                            String szticketprintno, String printtype, Long iemployeeid,String newprintno);

    /**
     * prcs Describe:
     *
     * @auth:yuanchengjun
     * @param
     * @param
     * @return return:int Date:2012-3-21
     */
    public String getsysparcs(@WebParam(name="pmky")String pmky, @WebParam(name="pmcd")String pmcd) ;

    /**
     * 登录后根据ticketprintlist中读取未能打印完成的打印任务显示出来 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @return return:String Date:2012-3-21
     */
    public String querynotprint(Long iemployeeid,String url) ;

    /**
     * 提取系统版本号
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getclientversion();

    /**
     * 读取在WebContant控制的数据 Describe:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2012-3-27
     */
    public String getWebContant() ;

    /**
     * 凭证保证
     *
     * @param salesvouchers
     *            凭证主表
     * @param salesvoucherdetails
     *            凭证 细
     * @param comticketsalesdetails
     *            检票明细
     * @param productcontrols
     *            打印制制
     * @param version
     *            版本
     * @param param
     *            参数
     * @return
     */
    public String saveorder(@WebParam(name="salesvouchers")String salesvouchers,
    		@WebParam(name="salesvoucherdetails")String salesvoucherdetails, @WebParam(name="comticketsalesdetails")String comticketsalesdetails,
    		@WebParam(name="productcontrols")String productcontrols, @WebParam(name="version")String version,String url, @WebParam(name="param")String... param) ;
    /**
     * 自助机保存订单状态
     * @param message
     * @param iticketwinid
     * @param iemployeeid
     * @param url
     * @return
     */
//    public String saveorder(@WebParam(name="message")String message,@WebParam(name="iticketwinid") Long iticketwinid,@WebParam(name="iemployeeid")Long iemployeeid,@WebParam(name="url")String url);
    
    public String savetorder(@WebParam(name="orid")String orid, @WebParam(name="iscenicid")Long iscenicid,
    						@WebParam(name="iticketwinid")Long iticketwinid, @WebParam(name="iemployeeid")Long iemployeeid,
    						@WebParam(name="version")String version,String url,@WebParam(name="param")String... param) ;

    
    

    public String updatenojinquT_order(String orid, Long iscenicid,
                                           Long iemployeeid, Double mont) ;

    public void updatestsprint(@WebParam(name="isalesvoucherid") Long isalesvoucherid,@WebParam(name="iticketstationid") Long iticketstationid) ;
    public String reprintbyorid(String szsalesvoucherno, Long iscenicid,String url);

    /**
     * 根据订单号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:String Date:2011-11-22
     */
    public String getorderbyorid(@WebParam(name="orid")String orid,@WebParam(name="iscenicid") Long iscenicid) ;

    /**
     * 返回售票员当前最小库存票号
     *
     * @param iscenicid
     *            服务商编号
     * @param ticketid
     *            票类
     * @param userid
     *            售票员
     * @param tourDate
     *            日 期
     * @param otherParm
     *            其它参数
     * @return
     */
    public String checkingFirstBarcodet(Long iscenicid, Long ticketpriceid,
                                            Long userid, String tourDate, String... otherParm) ;

    /*public String checkingFirstBarcodet(Long iscenicid, Long ticketpriceid,
                                            Long userid, String tourDate, int num, String... otherParm) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.checkingFirstBarcodet(iscenicid,
                ticketpriceid, userid, tourDate, num, otherParm);
    }*/

    /**
     * 返回当前用户的所售票类的数量组合 按段划分
     *
     * @param iscenicid
     *            服务商编号
     * @param firstBarcode
     *            初始条码
     * @param count
     *            数量
     * @param tourDate
     *            日期
     * @param userid
     *            编号
     * @param otherParm
     *            其它需要的参数
     * @return
     */
    public String checkingPrefabricateTicket(@WebParam(name="iscenicid")Long iscenicid,@WebParam(name="ticketpriceid") Long ticketpriceid,
    										@WebParam(name="firstBarcode")String firstBarcode,@WebParam(name="count") int count,
    										@WebParam(name="tourDate") String tourDate, @WebParam(name="userid")Long userid, 
    										@WebParam(name="otherParm")String... otherParm) ;

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param iserialnums
     * @param iscenicid
     * @return return:String Date:2012-8-10
     */
    public String ticketreprintbylb(@WebParam(name="cdcs")String cdcs, @WebParam(name="iserialnums")String iserialnums,
    								@WebParam(name="iscenicid")Long iscenicid,@WebParam(name="param")String param,String url) ;

    // 李进增加方法 getICID
    public String getICID(String typeid, Long id) ;

    /**
     *
     * Describe:判断IC库存数量
     *
     * @auth:lijingrui
     * @param iscenicid
     * @param ticketpriceid
     * @param count
     * @param tourDate
     * @param userid
     * @param otherParm
     * @return return:String Date:2012-8-31
     */
    public String personCheckoutiaMount(Long iscenicid, Long ticketpriceid,
                                        int count, String tourDate, Long userid, String... otherParm);

    /**
     * 增加写IC卡日志的类
     *
     * @param szTicketPrintNo
     *            票号
     * @param EquipmentID
     *            机器编号
     * @param ICBLOCK
     *            块编号
     * @param ICContent
     *            内容
     * @return
     */
    public int IcSaleWriteLog(String szTicketPrintNo, int EquipmentID,
                              String ICBLOCK, String ICContent) ;

    /**
     * 读取写入IC卡的内容
     *
     * @param szTicketPrintNo
     *            卡号
     * @param type
     *            1--当天日志 2--历史日志
     * @return
     */

    public String GetICData(String szTicketPrintNo, int type) ;

    /**
     * * Describe:显示圆门和检票次数
     *
     * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getGateTicketCount(java.lang.Long,
     *      java.lang.Long)
     * @param iscenicid
     *            服务商ID
     * @param iticktypeid
     *            产品ID
     * @param tickettypeid
     *            子产品ID
     * @return
     * @author lijingrui Date:2012-9-5
     */
    public String getGateTicketCount(Long iscenicid, Long iticktypeid,
                                         Long tickettypeid) ;

    /**
     *
     * Describe:对IC卡票 修改售出门票表中的票号 并激活
     *
     * @auth:lijingrui
     * @param iscenicid
     *            服务商
     * @param oldszticketno
     *            旧票号
     * @param newszticketno
     *            新票号
     * @return return:boolean Date:2012-9-13
     */
    public boolean getVaildcheckCodeup(Long iscenicid, String oldszticketno,
                                       String newszticketno, Long iscz) ;

    /**
     *
     * Describe: 副卷打印设置
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param ibusinessid
     * @return return:String Date:2012-11-01
     */
    public String getSodeprintmanage(@WebParam(name="iscenicid")Long iscenicid, @WebParam(name="ibusinessid") Long ibusinessid) ;

    /**
     * 挂失票务 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param szticketprintno
     * @return return:String Date:2012-12-11
     */
    public String guashi(Long iemployeeid, String szticketprintno) ;

    /**
     * 解挂票务 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param szticketprintno
     * @return return:String Date:2012-12-11
     */
    public String jiegua(Long iemployeeid, String szticketprintno) ;

    public String yanqi(Long iemployeeid, Long iscenicid,
                            Long iticketwinid, String szticketprintno,
                            double iaccountreceivable, double iacceptmoney, double igivechange,
                            String zffs) ;

    public String gettickmont(Long icrowdkindpriceid, Long numb, String stdt) ;

    /*public String gettickmont(Long icrowdkindpriceid, Long numb,
                                  String stdt, String... otherParm) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.gettickmont(icrowdkindpriceid, numb, stdt,
                otherParm);
    }*/

    /**
     * 根据导游信息 Describe:
     *
     * @auth:yuanchengjun
     * @param ibusinessid
     * @return return:String Date:2011-10-27
     */
    public String getDaoyou(@WebParam(name="lname") String lname) ;

    public void updatestatus(@WebParam(name="accid") Long accid, @WebParam(name="typestatus") String typestatus, @WebParam(name="byisuse") Long byisuse);
    
    public String getordercs(@WebParam(name="ibusinessid") Long ibusinessid) ;

    /**
     * prcs Describe:
     *
     * @auth:yuanchengjun
     * @param
     * @param
     * @return return:int Date:2012-3-21
     */
    /*public String getsysparcs(String pmky) {

        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.getsysparcs(pmky);
    }*/

    public String getGalsourceregiontab() ;

    /**
     * 打印回执
     *
     * @param iscenicid
     * @param isalevocherid
     * @param type
     *            1:重打印,0默认  2退票
     * @return
     */
    public String getSalevoucher(String iscenicid, String isalevocherid,
                                 int type) ;

    public String getordermessageyedy(String iscenicid, String orid, int type) ;

    public String getICcardType() ;

    public String getfapiao(Long isalesvoucherid, Long iticketstationid,
                                Long iemployeeid, Long iticketwinid, String corpname, String fpno,
                                String... param) ;

    /*public String getfapiao(String orid, Long iscenicid, Long iemployeeid,
                                String corpname, String fpno, String... param) {
        orid = orderDecode(orid);
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.getfapiao(orid, iscenicid, iemployeeid,
                corpname, fpno);
    }*/

    public void updatefapiaobyorid(String orid, Long iscenicid) ;

    public void updatefapiao(Long isalesvoucherid, Long iticketstationid) ;

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param type
     *            类别
     * @param ticketno
     *            票号
     * @param ziwenno
     *            指纹信息
     *
     *
     * @return
     * @throws Exception
     *             return:int Date:2011-11-14
     */

    public int ticketZwdengji(String type, String ticketno, String ziwenno) ;
    /**
     * 最新售票软件总的服务方法入口
     * @param md5str      加密串
     * @param userid      用户ID
     * @param password    密码
     * @param method      方法编号
     * @param parameters  参数
     * @return            OBJECT
     * @throws NumberFormatException 
     * @throws Exception
     */
    public String saleautoservice(@WebParam(name="md5str")String md5str, @WebParam(name="userid")String userid,
						    		@WebParam(name="password")String password, @WebParam(name="method")Long method,
						    		 @WebParam(name="parameters")String... parameters) throws NumberFormatException, Exception;

    public String findTicketPrinNo(String ticketPrintNo,Long tickettypeid,Long numb) ;

    public void saveSaleLog(@WebParam(name="userid")String userid,@WebParam(name="winid")String winid,@WebParam(name="logType")String logType,@WebParam(name="note")String note);

    /**
     *
     * Describe:导游通知单查询
     *r
     * @author:chenxinhao
     * @param szsalesvoucherno
     * @param daoyouname
     * @param cardno
     * @return
     * @throws Exception
     *             return:String Date:2015-3-18
     */
    public String getGuidenitices(String szsalesvoucherno,
                                      String daoyouname, String cardno) throws Exception ;

    public String ticketchupiao(@WebParam(name="isalesvoucherid") Long isalesvoucherid,@WebParam(name="iticketstationid") Long iticketstationid,@WebParam(name="rePrint") Long rePrint) ;

    public String ticketreprintjxzh(String cdcs, String iserialnums,
                                        Long iscenicid) ;

    /**
     *
     * Describe:导游派遣单保存数据
     *
     * @author:chenxinhao
     * @param parameters
     *            [0]销售凭证 [1]导游姓名 [2]证件号码 [3]人数 [4]游客类型
     * @return
     * @throws Exception
     *             return:String Date:2015-3-18
     */
    public String saveGuidenitices(String[] parameters) throws Exception ;

    /**
     *
     * Describe:导游结算单保存数据
     *
     * @author:chenxinhao
     * @param parameters
     *            [0]销售凭证 [1]窗口ID [2]员工ID
     * @return
     * @throws Exception
     *             return:String Date:2015-3-18
     */
    public String saveGuideniticesJs(String[] parameters) throws Exception ;

    /**
     *
     * Describe:根据订单号(身份证),服务商,窗口查询订单
     *
     * @author:chenxinhao
     * @param carno
     *            订单号,身份证
     * @param iscenicid
     *            服务商
     * @param winId
     *            窗口
     * @return return:String Date:2015-2-27
     */
    public String getT_orderByWin(@WebParam(name="carno") String carno,@WebParam(name="iscenicid") Long iscenicid,@WebParam(name="winid") Long winid,String url) ;

    /**
     * 身份证出票激活
     *
     * @param iscenicid
     * @param szticketno
     * @param name
     * @param zjno
     * @return
     */
    public boolean jihuobyzjno(@WebParam(name="iscenicid") Long iscenicid, @WebParam(name="szticketno") String szticketno,
    							@WebParam(name="name") String name,@WebParam(name="zjno") String zjno) ;

    public String getempordermassage(@WebParam(name="iemployeeid") Long iemployeeid, @WebParam(name="date") String date,String url);

   
    public String getseat(@WebParam(name="ivenueid") Long ivenueid);
    
    
    public String Getareapricve(@WebParam(name="Itripprdcontrolid") Long Itripprdcontrolid,@WebParam(name="stdt") String stdt,
    							@WebParam(name="groupid") String groupid, @WebParam(name="ibusinessid") Long ibusinessid) ;
    @WebMethod(operationName="GetareapricveByIvenueareaid")
    public String GetareapricveByIvenueareaid(@WebParam(name="Itripprdcontrolid") Long Itripprdcontrolid,@WebParam(name="stdt") String stdt,
			@WebParam(name="groupid") String groupid, @WebParam(name="ibusinessid") Long ibusinessid, @WebParam(name="ivenueareaid") Long ivenueareaid) ;

    public String Getareaseatstusts(@WebParam(name="ivenueid") Long ivenueid, @WebParam(name="ivenueareaid") Long ivenueareaid,
    								@WebParam(name="stdt") String stdt,@WebParam(name="tripid") Long tripid,
    								String url) ;

    public String Getseatlocklist(@WebParam(name="date") String date,String url) ;

    public String checkEdpofferschemetab(@WebParam(name="offerschme") String offerschme) ;

    /**
     *
     * Describe:获取接待用户的控制量
     *
     * @author:lijingrui
     * @param usid
     * @param iscenicid
     * @return return:String Date:2015-1-5
     */
    public String queryStockWarenumb(@WebParam(name="usid") String usid, @WebParam(name="iscenicid") String iscenicid);

    /**
     *
     * Describe:根据票号获取指纹信息
     *
     * @author:lijingrui
     * @return return:String Date:2015-1-15
     */
    public String checkTicketzhiwe(@WebParam(name="szticketprintno") String szticketprintno) ;

    /**
     *
     * Describe:根据身份证号码获取导游计时票信息
     *
     * @author:lijingrui
     * @param zjhm
     * @return return:String Date:2015-2-4
     */
    public String queryListzjhmPrint(@WebParam(name="zjhm") String zjhm) ;

    /**
     *
     * Describe:保存导游截止日期 或者录入指纹
     *
     * @author:lijingrui
     * @param szsoldticketid
     * @param isalesvoucherid
     * @param isalesvoucherdetailsid
     * @param iticketstationid
     * @param zjhm
     * @param bsfilebinary
     * @return return:String Date:2015-2-10
     */
    public String checkSaveprintStssold(@WebParam(name="szsoldticketid") String szsoldticketid,@WebParam(name="isalesvoucherid")String isalesvoucherid,
    									@WebParam(name="isalesvoucherdetailsid")String isalesvoucherdetailsid,@WebParam(name="iticketstationid")String iticketstationid,
    									@WebParam(name="zjhm")String zjhm, @WebParam(name="bsfilebinary")String bsfilebinary) ;

    public String queryCytOrder(@WebParam(name="queryMessage") String queryMessage,@WebParam(name="iscenicid") Long iscenicid);

    public String queryCytOrderDetail(@WebParam(name="orid")String orid,@WebParam(name="iscenicid") Long iscenicid) throws Exception;

    public String consumeOrder(@WebParam(name="orid") String orid, @WebParam(name="iscenicid") Long iscenicid,
							   @WebParam(name="iticketwinid")Long iticketwinid,@WebParam(name="iemployeeid") Long iemployeeid,
							   @WebParam(name="sign") String sign,@WebParam(name="numb") Long numb,
							   @WebParam(name="yzp") String yzp, @WebParam(name="cash") String cash);
    /**
     * Describe: String不适合Android,新增统一接口
     * @author liujianwen
     * @param data
     * @return
     * return:String
     * Date:2016-4-29
     */
    public String work(@WebParam(name="data")String data);
    /**
      * 取票前部分退订
	  * @param type  类型
	  * @param torderlists 原订单列表
	  * @param newticketinfo 新订单列表
	  * @param user 用户ID
	  * @param orid 订单号
	  * @param iscenicid 景区服务商
	  * @author lizhaodong  2017-04-27 添加
	  * @return
	  */
    public String debookTicket(@WebParam(name="type") String type,@WebParam(name="torderlistStr") String torderlistStr,@WebParam(name="newticketinfoStr") String newticketinfoStr,@WebParam(name="user") String user,@WebParam(name="orid") String orid,@WebParam(name="iscenicid") String iscenicid,String url);
    
   
    /**
     *  取票前全部退订
	  * @param type  类型
	  * @param user 用户ID
	  * @param orid 订单号
	  * @param iscenicid 景区服务商
	  * @author lizhaodong  2017-04-27 添加
	  * @editor  lizhaodong  2017-05-03 修改
	  * @return
	  */
   public String debookAllTicket(@WebParam(name="type") String type,@WebParam(name="user") String user,@WebParam(name="orid") String orid,@WebParam(name="iscenicid") String iscenicid,String url);
   
   /**
    * 前台售票当前最后一条售票数据
    * @auth:lizd
    * @param type   售票软件重打印搜索时的类型
    * @param iscenicid
    *            服务商
    * @param winId
    * @param empid  售票软件登陆用户
    *            窗口
    * Date:2017-05-24
    */
   public String searchLastSaleTicket(@WebParam(name="type") String type,@WebParam(name="iscenicid") String iscenicid, @WebParam(name="iticketwinid") String iticketwinid,@WebParam(name="iemployeeid") String iemployeeid);
   
   /**
    * 获取分时预约库存和时段信息
    * @auth:lizd
    * @param productCode   产品code
    * @param choiceDate  选中日期
    * Date:2017-07-31
    */
   public String getTicketTimeCount(@WebParam(name="productCode") String productCode,@WebParam(name="choiceDate") String choiceDate);


    /**
     * 查询身份证当天是否已经预约
     * @auth:lizd
     * @param IDCard   产品code
     * @param choiceDate 选中日期
     * Date:2017-07-31
     */
    public String isExistIDcardToday(@WebParam(name="iscenicID") String iscenicID,@WebParam(name="IDCard") String IDCard,@WebParam(name="choiceDate") String choiceDate,String url);
    
	/**
	 * 客户端模式获取token信息
	 * 
	 * @return
	 */
	public String getTokenByClient();
	
	/**
	 * @param iscenicid
	 * @param mac
	 * @return
	 */
	public String getWinAndStationInfo(@WebParam(name="iscenicid") Long iscenicid, @WebParam(name="mac") String mac);
	/**
	 * 
	* @Title: senseServiceStatu  
	* @Description: TODO 自主机检测服务端状态 
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String senseServiceStatu();
	/**
	 * 取消订单
	 * @param orid
	 * @param url
	 * @return
	 */
	public String cancelorder(@WebParam(name="orid") String orid,@WebParam(name="url") String url);
	/**
	 * 生成二维码图片
	 * @param orid
	 * @param isdx
	 * @param orph
	 * @param newUrl
	 * @return
	 */
	public String makeTwoBarcode(@WebParam(name="orid") String orid,@WebParam(name="isdx") Long isdx,@WebParam(name="orph") String orph,@WebParam(name="newUrl")String newUrl);
	/**
	 * 售票报表
	 * @param iemployeeid
	 * @param rzti
	 * @param ldti
	 * @return
	 */
	public String saleReport(@WebParam(name="iemployeeid") Long iemployeeid,@WebParam(name="rzti") String rzti,@WebParam(name="ldti") String ldti);
	/**
	 * 保存订单
	 * @param message
	 * @param iticketwinid
	 * @param iemployeeid
	 * @param timeParam
	 * @param url
	 * @return
	 */
	public String autoSaveorder(@WebParam(name="message")String message,@WebParam(name="iticketwinid") Long iticketwinid,@WebParam(name="iemployeeid") Long iemployeeid,@WebParam(name="timeParam")String timeParam,@WebParam(name="url")String url);
	/**
	 * 支付订单
	 * @param orderno
	 * @param yingshou
	 * @param shishou
	 * @param zhaoling
	 * @param iticketwinid
	 * @param iemployeeid
	 * @param zffs
	 * @return
	 */
	public String payOrder(@WebParam(name="orderno")String orderno,@WebParam(name="yingshou") Double yingshou,@WebParam(name="shishou") Double shishou,@WebParam(name="zhaoling")Double zhaoling,@WebParam(name="iticketwinid") Long iticketwinid,@WebParam(name="iemployeeid") Long iemployeeid,@WebParam(name="zffs") String zffs);
}
