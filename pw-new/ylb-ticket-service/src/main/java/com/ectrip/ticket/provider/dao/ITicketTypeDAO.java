package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.TicketPrintNo;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
/**
 * 产品
 * @author huangyuqi
 */
public interface ITicketTypeDAO extends IGenericDao {
    /**
     * 查询某服务商下所有产品列表
     * Describe:
     * @auth:huangyuqi
     * @param providerId服务商编号
     * @param page页码
     * @param pagesize每面显示数
     * @param url访问地址
     * @param type 票类型（基础、套票）
     * @return
     * return:PaginationSupport
     * Date:2011-9-22
     */
    public PaginationSupport getTickettypeList(String searchkey,Long providerId,int page,int pagesize,String url,String type);
    /**
     * 查询某服务商下所有产品列表
     * Describe:
     * @auth:chenyidong
     * @param providerId服务商编号
     * @param type 票类型（基础、套票）
     * @return
     * return:PaginationSupport
     * Date:2011-03-22
     */
    public List getTickettypeListSelect(Long providerId,String type);

    /**
     *根据产品编号查询产品信息
     * Describe:
     * @auth:huangyuqi
     * @param tickettypeId
     * @return
     * return:Edmtickettypetab
     * Date:2011-9-22
     */
    public Edmtickettypetab queryTickettype(Long tickettypeId);
    /**
     * 判断产品在订单中是否存在
     * Describe:
     * @auth:huangyuqi
     * @param tickettypeId
     * @return
     * return:boolean
     * Date:2011-9-22
     */
    public boolean querytickettypeByOrder(Long tickettypeId);

    /**
     * 查询产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param productId产品Id
     * @param providerpdtp服务商类型
     * @return
     * return:Object
     * Date:2011-9-27
     */
    public Object getOthersProduct(Long productId,String providerpdtp);

    /**
     * 根据服务商编号删除产品
     * Describe:
     * @auth:huangyuqi
     * @param providerId
     * return:void
     * Date:2011-9-27
     */
    public void deleteProduct(Long providerId);
    /**
     * 根据产品编号删除其它数据
     * Describe:
     * @auth:huangyuqi
     * @param productid
     * return:void
     * Date:2011-10-4
     */
    public void deleteProductOhter(Long productid);
    /**
     * 删除产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param productId
     * @param providerPdtp
     * return:void
     * Date:2011-9-27
     */
    public void deletOtherProduct(Long productId,String providerPdtp);
    /**
     * 根据服务商删除产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param providerId服务商编号
     * @param providerPdtp服务商类型
     * return:void
     * Date:2011-9-27
     */
    public void deleteOtherProductByPdno(Long providerId,String providerPdtp);

    /**
     *
     * Describe:显示出某服务商下的产品信息（显示的产品的产品类别为产品附加服务）
     * @auth:lijingrui
     * @param iscenicid
     * @return
     * return:List
     * Date:Feb 9, 2012
     */
    public List quckListedmticket(Long iscenicid);

    /**
     *
     * Describe:显示出某产品的附加产品信息(修改时)
     * @auth:lijingrui
     * @param itickettypeid
     * @return
     * return:List
     * Date:Feb 9, 2012
     */
    public List showAllhotelduct(Long itickettypeid);

    /**
     *
     * Describe:显示出某产品的以附加产品的信息（查看）
     * @auth:lijingrui
     * @param itickettypeid
     * @return
     * return:List
     * Date:Feb 10, 2012
     */
    public List quickviewhotelticket(Long itickettypeid);

    public PaginationSupport queryPrintno(Long itickettypeid,String printno,String serialnumber,int page, int pagesize,String url);

    public void addPrintNo(TicketPrintNo printNo,Syslog syslog);

    public void editPrintNo(TicketPrintNo printNo,Syslog syslog);
    
    public List<Edmticketcomposepricetab> getSonPrice(Long icrowkindid,Long itckettypeid);
    
    public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid, String startdate);
    
    public Productcontrol getTripControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate);
    
    //根据票种id集合，查询票种信息
    public List getTicketTypeListByIds(String itickettypeids);
    
    public List getTicketTypeSelect(String scenics);
    
    public List getSonTicketTypeSelect(Long itickettypeid);
}

