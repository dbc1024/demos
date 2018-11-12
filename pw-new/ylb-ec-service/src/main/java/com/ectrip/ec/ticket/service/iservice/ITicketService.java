package com.ectrip.ec.ticket.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.ticket.ProviderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edpofferschemetab;

public interface ITicketService {

    public List getTicketName(String ibusinessid, String iscenicid,String groupno);

    public List getSonTicket(String ticketid);

    /**
     * 
     * Describe: 获取票务列表
     * 
     * @auth:yangguang
     * @param ibusinessid
     *            业务种类 例如：散客 旅行社等
     * @param pageSize
     *            页数
     * @param startIndex
     *            页大小
     * @param url
     *            分页url
     * @return return:PaginationSupport Date:2011-10-9
     */
    public List getTickInfoList(String ibusinessid) throws IllegalAccessException, InvocationTargetException;

    /**
     * 
     * Describe:根据日期获取产品价格列表
     * 
     * @auth:yangguang
     * @param itickettypeid
     * @param ibusinessid
     * @param date
     * @return return:List Date:2012-2-6
     */
    public List getPriceList(String itickettypeid, String ibusinessid, String date,String groupno);

    /**
     * 
     * Describe:获取竹筏趟次列表
     * 
     * @auth:yangguang
     * @param itickettypeid
     *            产品编号
     * @param data
     *            日期
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:List Date:2011-10-12
     */
    public List getRafttripInfoList(String itickettypeid, String date, String zticketid) throws IllegalAccessException,
	    InvocationTargetException;

    public List getRafttripInfoList(String itickettypeid, String date, String zticketid, String usid) throws IllegalAccessException,
	    InvocationTargetException;

    /**
     * 
     * Describe:根据产品编号、业务种类、人群编号、游览日期 获取产品价格
     * 
     * @auth:yangguang
     * @param itickettypeid
     *            产品编号
     * @param tourDate
     *            游览日期
     * @param icrowdkindpriceid
     *            人群种类
     * @param ibusinessid
     *            业务类型
     * @return return:double Date:2011-10-27
     * @deprecated
     */
    public double getTicketPrice(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid);

    
    
    /**
     * 
     * Describe:合并相同的票
     * 
     * @auth:yangguang
     * @param ticketlist
     * @return return:List<OrderPojo> Date:2011-11-1
     */
    public List<OrderPojo> mergeSameTicket(List<OrderPojo> ticketlist);

    // 获取套票中有趟次的子票 合并到基础票中有趟次的票
    public List<OrderPojo> getNestTicketTripTicketList(List<OrderPojo> nestticketlist, List<OrderPojo> tripticketlist);

    // 获取基础票中有 趟次的票
    public List<OrderPojo> getTripTicketList(List<OrderPojo> baseTicketList);

    /**
     * 
     * Describe:把从页面上获取的数据拆分成套票和基础票
     * 
     * @auth:yangguang
     * @param list
     * @return return:List Date:2011-10-31
     */
    public Map splitAllTicketList(List<OrderPojo> list);

    // TODO 明天开始返回验证页面 及保存订单
    @SuppressWarnings("unchecked")
    public List<OrderPojo> returnExceptionList(List<OrderPojo> list) throws IllegalAccessException, InvocationTargetException;

    // 验证基础票 验证单个票得 暂时没用
    // public List volidateBaseTicket(List<OrderPojo> basetickets);

    // 验证套票 验证单个票得 暂时没用
    // public List validateNestTicket(List<OrderPojo> list);

    /**
     * 
     * Describe:趟次控制
     * 
     * @auth:yangguang
     * @param list
     *            基础票列表 或者合并了同产品 同日期 同趟次的套票列表
     * @return return:List Date:2011-11-1
     */
    public List tripControll(List<OrderPojo> list) throws IllegalAccessException, InvocationTargetException;

    /**
     * 
     * Describe:判断日控制
     * 
     * @param 传入基础票集合
     *            或者已将日期合并之后的套票列表
     * @auth:yangguang
     * @param list
     * @return return:List Date:2011-11-1
     */
    public List dayControll(List<OrderPojo> list) throws IllegalAccessException, InvocationTargetException;

    /**
     * 
     * Describe:获取存储morder torder torderlist yorder yorderlisr的map
     * 
     * @auth:yangguang
     * @param custom
     *            用户
     * @param tickets
     *            票列表
     * @param firstdate
     *            首次游览日期
     * @param dyid
     *            导游ID
     * @param password
     *            密码或者证件号码
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:Map Date:2011-11-3
     */
    public Map combinationOrder(Custom custom, List<OrderPojo> tickets, String firstdate, String dyid, String password, String note,
	    String mobile, String orhm, String dyname, String szregionalid, int isjl) throws IllegalAccessException,
	    InvocationTargetException, SQLException;

    public Map removeEmptyTicket(List<OrderPojo> ticketlist) throws IllegalAccessException, InvocationTargetException;

    
    /**
     * 移除数量为空的数据，并封装优惠信息
     * @param ticketlist
     * @param ibusinessid
     * @param startdate
     * @param groupno
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map packagingScheme(List<OrderPojo> ticketlist,long ibusinessid,String usid,List<LprPojo> lprlist) throws IllegalAccessException, InvocationTargetException;
    
    
    public String getFsusid(String usid) throws Exception;

    public boolean hasJfProduct(List<OrderPojo> list);

    public boolean hasnormalproduct(List<OrderPojo> list);

    /**
     * 
     * Describe:验证预定积分产品 积分是否足够
     * 
     * @auth:yangguang
     * @param list
     * @param stdt
     * @param usid
     * @return return:boolean Date:2012-5-23
     * @deprecated
     */
    public Map isEnougfJifen(List<OrderPojo> list, String stdt, String usid);

    /**
     * 
     * Describe:计算积分并填充
     * 
     * @auth:yangguang
     * @param list
     * @param stdt
     * @param usid
     * @return return:List Date:2012-5-28
     */
    public List putYhInfo(List<OrderPojo> list,Long ibusinessid);

    public List getTickInfoListByDate(String ibusinessid, String date) throws IllegalAccessException, InvocationTargetException;

    public void removeEmptyProduct(List<OrderPojo> list);

    
    /**
     * 价格分组前查询价格列表方法，废弃
     * @param ibussiness
     * @param date
     * @return
     * @deprecated
     */
    public List<ProviderPojo> getProviderList(String ibussiness, String date);
    
    
//    public List<ProviderPojo> getProviderList(String ibussiness, String date,String usid);
    
    public List<ProviderPojo> getProviderListByGroupId(String ibussiness, String date,String usid,List list);

    public Map validationLpr(List<LprPojo> lprs,String ibussiness) throws ParseException;
    
    public Map validationLpr(List<LprPojo> lprs,String ibussiness,String usid) throws ParseException;

    public Map isEnougfJifen(List<OrderPojo> list, List<LprPojo> lprs, String usid) throws IllegalAccessException,
	    InvocationTargetException;

    public Map combinationOrder(Custom custom, List<OrderPojo> tickets, String note, List<LprPojo> lprlist, int isjl)
	    throws IllegalAccessException, InvocationTargetException, SQLException, ParseException;
    
    public void fillLprInfo(List<LprPojo> lprs);
    public void removeNoProductLpr(List<LprPojo> lprs, List<OrderPojo> tickets);
    /**
     * 验证价格
     * @param tickets 票列表
     * @param lpr 领票人
     * @param ibusinessid 业务编号
     * @param usid 登录用户
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map validationPrice(List<OrderPojo> tickets,List<LprPojo> lpr,String ibusinessid,String usid)throws IllegalAccessException, InvocationTargetException;

    /**
     * 
     * Describe:获取订单参数信息
     * @auth:lijingrui
     * @param ibusinessid
     * @return
     * return:List
     * Date:2013-4-7
     */
    public List getListordercs(String ibusinessid);
    
    
    public boolean volidationRealname(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid,String groupno);

    public Edpofferschemetab getScheme(Long iscenicid,Long ibusinessid,Long icrowkind,Long itickettypeid,String startdate);
    
    /**
     * 
     * Describe:获取服务商优惠信息
     * @author:lijingrui
     * @param iscenicid
     * @param startdate
     * @return
     * return:Edpofferschemetab
     * Date:2014-4-1
     */
    public Edpofferschemetab checkEdpschemet(Long iscenicid,String startdate,Long ibusinessid);
    
    /**
     * 
     * Describe:获取某用户的价格分组  服务商id可为空
     * @author:lijingrui
     * @param usid
     * @param iscenicid
     * @return
     * return:String
     * Date:2014-4-16
     */
    public String searchJgfz(String usid,Long iscenicid);
}
