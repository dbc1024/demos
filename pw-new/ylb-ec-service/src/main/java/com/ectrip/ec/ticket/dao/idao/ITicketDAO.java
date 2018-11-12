package com.ectrip.ec.ticket.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
public interface ITicketDAO extends IGenericDao {

    /**
     * Describe:根据日期获取产品价格列表
     *
     * @param itickettypeid
     * @param ibusinessid
     * @param date
     * @return return:List Date:2012-2-6
     * @auth:yangguang
     */
    public List getPriceList(String itickettypeid, String ibusinessid, String date, String groupno);

    /**
     * Describe:查找订单中是否含有竹筏信用度为正数的趟次
     *
     * @param orid
     * @return return:boolean Date:2012-4-20
     * @auth:yangguang
     */
    public boolean queryCreditNumber(String orid);

    /**
     * Describe:根据当前用户所属业务类型获取现可售的票得名称,并按照票得名称进行分组
     *
     * @param ibusinessid
     * @param pageSize
     * @param startIndex
     * @param url
     * @return return:PaginationSupport Date:2011-10-9
     * @auth:yangguang
     */
    public List getTicketList(String ibusinessid);


    /**
     * @param ibusinessid
     * @param iscenicid
     * @return
     * @deprecated 价格分组前 废弃
     */
    public List getTicketList(String ibusinessid, String iscenicid);

    /**
     * 价格分组
     *
     * @param ibusinessid
     * @param iscenicid
     * @return
     */
    public List getTicketList(String ibusinessid, String iscenicid, String groupno);

    public List getTicketList(String ibusinessid, String iscenicid, String groupno, String date);

    /**
     * Describe:根据票ID获取票的针对人群种类的价格列表,要使用此方法必须传入有效的产品ID,暂时不对外开放
     *
     * @param itickettypeid
     * @return return:List Date:2011-10-9
     * @auth:yangguang
     */
    public List getPriceList(String itickettypeid, String ibussinessid);

    /**
     * Describe:获取竹筏趟次信息 根据产品ID,乘坐时间
     *
     * @param itickettypeid 产品编号
     * @param data          查询日期
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException return:List Date:2011-10-11
     * @auth:yangguang
     */
    public List getRafttripInfoList(String itickettypeid, String date, String ziticketid) throws IllegalAccessException,
            InvocationTargetException;

    public List getRafttripInfoList(String itickettypeid, String date, String ziticketid, String usid) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * Describe:根据产品编号、业务种类、人群编号、游览日期 获取产品价格
     *
     * @param itickettypeid     产品编号
     * @param tourDate          游览日期
     * @param icrowdkindpriceid 人群种类
     * @param ibusinessid       业务类型
     * @return return:double Date:2011-10-27
     * @auth:yangguang
     */
    public double getTicketPrice(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid)
            throws RuntimeException;

    /**
     * Describe:根据产品编号 日期 判断是否有受限数据
     *
     * @param iticketid
     * @param date
     * @return false:没有 true:有 return:boolean Date:2011-10-29
     * @auth:yangguang
     */
    public boolean isHaveTicketRestrictedData(String iticketid, String date);

    /**
     * Describe:根据时间、产品、控制模式获取产品数量控制数据
     *
     * @param iticketid
     * @param date
     * @param controlltype 00：总数控制 01:日控制 02:趟次控制 03:趟次区域控制 04：座位控制
     * @return return:List Date:2011-10-29
     * @auth:yangguang
     */
    public List getNumberControllData(String iticketid, String date, String controlltype);

    /**
     * Describe:根据时间、产品、获取产品数量控制数据
     *
     * @param iticketid
     * @param tripid
     * @param date
     * @return return:List Date:2011-10-29
     * @auth:yangguang
     */
    public List getNumberControllData(String iticketid, int tripid, String date);

    /**
     * Describe:根据时间、产品编号获取产品数量控制数据
     *
     * @param iticketid
     * @param date
     * @return return:List Date:2011-10-29
     * @auth:yangguang
     */
    public List getNumberControllData(String iticketid, String date);

    /**
     * Describe:查询子票
     *
     * @param iticketid
     * @return return:List Date:2011-10-31
     * @auth:yangguang
     */
    public List<OrderPojo> getSonTicketList(String iticketid);

    /**
     * Describe:根据套票的传过来的参数获取子票列表
     *
     * @param sonticket 子票的趟次编号和日期
     * @param prices    数量和价格编号
     * @param iticketid 套票编号
     * @param firstdate 首次进入日期
     * @param numb      套票数量
     * @return return:List<TZorderlist> Date:2011-11-3
     * @auth:yangguang
     */
    public List<TZorderlist> getNestTicketSplit(Long orderlistid, String orid, Long iscenicid, List<OrderPojo> sonticket, OrderPojo price,
                                                String iticketid, String firstdate, int numb) throws IllegalAccessException, InvocationTargetException;

    /**
     * Describe:根据价格编号获取价格
     *
     * @param icrowdkindpriceid 价格编号
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException return:Edmcrowdkindpricetab Date:2011-11-8
     * @auth:yangguang
     */
    public Edmcrowdkindpricetab getPrice(Long icrowdkindpriceid) throws IllegalAccessException, InvocationTargetException;

    public List getTicketPricelist(Long ticketid, Long icrowdkindid, String tourdate, String ibusinessid);

    public List getTicketPricelist(Long ticketid, Long icrowdkindid, String tourdate, String ibusinessid, String groupno);

    /**
     * Describe:根据价格编号获取产品价格组成列表
     *
     * @param icrowkondpriceid
     * @return return:List Date:2012-2-7
     * @auth:yangguang
     */
    public List getSonticketlist(Long icrowkondpriceid);

    /**
     * Describe:
     *
     * @param tripid
     * @param ivenueid
     * @param ivenueareaid
     * @param tourdate
     * @return return:Productcontrol Date:2012-2-8
     * @auth:yangguang
     */
    public Productcontrol getNumberControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate);

    public Prdtripvenuemanage getTripInfo(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate, String iscenicid,
                                          String itickettypeid);

    public Productcontrol getTripControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate);

    /**
     * Describe:判断是否积分产品
     *
     * @param ticketid
     * @param iscenicid
     * @return return:boolean Date:2012-5-23
     * @auth:yangguang
     */
    public boolean isJfproduct(Long ticketid, String iscenicid);

    public Numjifenset getNumjifenset(String iscenicid);

    /**
     * 价格分组前的,废弃掉
     *
     * @param itickettypeid
     * @param ibusinessid
     * @param date
     * @return
     * @deprecated
     */
    public List getPriceListByDate(String itickettypeid, String ibusinessid, String date);


    public List getPriceListByDate(String itickettypeid, String ibusinessid, String date, String groupno);

    public List getJingquProviderList(List list);

    public List getJingquProviderListbyiscenicid(Long iscenicid);

    public List getsonJIngquProviderByPid(Long iscenicid);

    public List getTicketList(String ibusinessid, Long iscenicid);

    public Edmcrowdkindpricetab getProductPrice(String itickettypeid, String ibusinessid, String date, String icrowkindid, String groupno) throws IllegalAccessException, InvocationTargetException;

    /**
     * Describe:获取订单参数信息
     *
     * @param ibusinessid
     * @return return:List
     * Date:2013-4-7
     * @auth:lijingrui
     */
    public List getListordercs(String ibusinessid);

    /**
     * 查询价格，价格分组版
     *
     * @param itickettypeid
     * @param tourDate
     * @param icrowdkind
     * @param ibusinessid
     * @param groupno
     * @return
     * @throws RuntimeException
     */
    public double getTicketPrice(String itickettypeid, String tourDate, String icrowdkind, String ibusinessid, String groupno) throws RuntimeException;

    /**
     * @param itickettypeid
     * @param tourDate
     * @param icrowdkindpriceid
     * @param ibusinessid
     * @param groupno
     * @return 验证价格是否实名制
     * @throws RuntimeException
     */
    public boolean volidationRealname(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid, String groupno) throws RuntimeException;

    /**
     * Describe:获取产品优惠信息
     *
     * @param iscenicid
     * @param ibusinessid
     * @param icrowkind
     * @param itickettypeid
     * @param startdate
     * @return return:Edpofferschemetab
     * Date:2014-4-1
     * @author:lijingrui
     */
    public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid, String startdate);

    /**
     * Describe:获取服务商优惠信息
     *
     * @param iscenicid
     * @param startdate
     * @return return:Edpofferschemetab
     * Date:2014-4-1
     * @author:lijingrui
     */
    public Edpofferschemetab checkEdpschemet(Long iscenicid, String startdate, Long ibusinessid);

    public Map getSchemeConditions(List<OrderPojo> ticketlist, Edpofferschemetab pscheme, String iscenicid, String date, long ibusinessid, String groupno) throws IllegalAccessException, InvocationTargetException;

    public Productcontrol getProductcontrol(Long iticketid, String date, Long tripid);

    /**
     * Describe:获取某用户的价格分组  服务商id可为空
     *
     * @param usid
     * @param iscenicid
     * @return return:String
     * Date:2014-4-16
     * @author:lijingrui
     */
    public String searchJgfz(String usid, Long iscenicid);

    public Edmcrowdkindpricetab getProductPrice(String itickettypeid, String ibusinessid, String date, Long icrowkindid) throws IllegalAccessException, InvocationTargetException;

    public Map getCheckOutNum();

	public List getTimeStock(String code, String palydate);
}
