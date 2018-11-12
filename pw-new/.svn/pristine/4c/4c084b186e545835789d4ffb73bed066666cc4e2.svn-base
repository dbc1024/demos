package com.ectrip.ticket.webservice.check;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.ectrip.base.util.ResultBean;

/**
 * 处理检票接口
 * @author jy
 *
 */
@WebService
public interface ICheckWebService {
	
    /**
     * 根据匝机IP+服务商ID读取匝机信息 Describe:
     *
     * @param ip
     * @param scenic
     * @return return:ResultBean Date:2011-12-8
     * @auth:yuanchengjun
     */
    public String getMyID(@WebParam(name="ip") String ip);

    public String CheckTicket(@WebParam(name="ticketstr") String ticketstr,@WebParam(name="md5str") String md5str,@WebParam(name="OtherString") String... OtherString);
    /**
     * Describe:把原来的方法原封不动移动到了checkTicket(小写),本方法调用checkTicket，不影响原的检票代码,
     * 只新增支持畅游通
     *
     * @param ticketstr
     * @param md5str1
     * @return
     * @throws Exception return:ResultBean Date:2014-4-30
     * @author liujianwen
     */
    @WebMethod(operationName = "CheckTicketByCheckType")
    public String CheckTicketByCheckType(String ticketstr, String md5str1, String checkType);
    
    /**
     * 测试调用的值
     *
     * @return
     * @throws Exception
     */
    public String getResultBean();


    /**
     * 读取售票点信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */

    public String getstation(Long iscenicid) ;

    /**
     * 景区代码信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */
    public String getscenic(@WebParam(name="iscenicid")Long iscenicid);

    /**
     * 读取票务代码信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */
    public String getTicket(Long iscenicid);

    /**
     * 验证放行卡 Describe:
     *
     * @param accid
     * @param carno
     * @return return:ResultBean Date:2011-12-24
     * @auth:yuanchengjun
     */
    public String CheckEmployee(String accid, String carno);


    /**
     * 读取服务器时间
     */
    public String getDayTimes();
    
    /**
     * 
    * @Title: getFtp  
    * @Description: TODO 根据设备Id获取ftp信息
    * @param @param accid
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
     */
    public String getFtp(@WebParam(name="accid")String accid);
    /**
     * 李进修改，根据通ID取值
     *
     * @param iaccessequipid
     * @return
     */
    public String getEsbaccessequiptab(@WebParam(name="iaccessequipid") Long iaccessequipid);

    /**
     * 获取园门总票数和园门总人数
     * @param iaccessequipid
     * @return
     */
    @WebMethod(operationName = "getCheckcount")
    public String getCheckcount(@WebParam(name="iaccessequipid") Long iaccessequipid);

    /**
     * 总体对外检票方法 ticketstr="园门ID,票号" 根据票号返回检票规则
     *
     * @param ticketstr 检票用的票号
     * @param md5str    准确性校验方法
     * @return
     */
    public String CheckscTicket(String ticketstr,String md5str);

    /**
     * 测试调用的值
     *
     * @return
     * @throws Exception
     */
    public void updatestatus(Long accid, String typestatus, Long byisuse);

    /**
     * 读取配置文件
     *
     * @return return:String Date:2011-12-12
     * @auth:yuanchengjun
     */
    public String getWebContant(String xmlkey);
    
    public String dealWithOTOCard(String ticketstr,String url);

    public String autoMethod(@WebParam(name="method")  String method,@WebParam(name="gardenId")  String gardenId,@WebParam(name="accid")  String accid,@WebParam(name="jsonInfo")  String jsonInfo );

}
