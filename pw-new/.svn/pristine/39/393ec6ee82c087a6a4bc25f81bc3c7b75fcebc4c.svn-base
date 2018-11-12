package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.ProviderCompany;
import org.springframework.stereotype.Repository;

public interface IProviderService extends IGenericService {
	
	public List getScenicListByAreaIds(String ids) throws Exception;


    public int getProviderCount();


    
    
    /**
     * 根据服务商id查询服务商信息
     * Describe:
     *
     * @param scenicId
     * @return return:Esbscenicareatab
     * Date:2011-9-24
     * @auth:huangyuqi
     */
    public Esbscenicareatab query(Long scenicId);

    /**
     * 更改层序号
     *
     * @param ilever       层
     * @param ileverseq层序号
     * @param objectId对象id
     * @param tablename表名
     */
    public void updateIleverseq(int ilever, int ileverseq, Long objectId, String tablename);

    /**
     * 得到当前登录用户的服务商信息
     * Describe:如果登录用户为平台用户，则读出所有服务商，如为景区企业用，则读出读企业下的服务
     *
     * @param iprentid
     * @param pdtp
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param iemployeeid后台登录用户
     * @return return:PaginationSupport
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public PaginationSupport queryAllList(Long iprentid, String pdtp, int page, int pagesize, String url, String path, Long employeeid);

    /**
     * 得到景点列表
     * Describe:
     *
     * @param iprentid
     * @param pdtp
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param iemployeeid后台登录用户
     * @return return:PaginationSupport
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public PaginationSupport queryAllList(Long iprentid, int page, int pagesize, String url, String path);

    /**
     * 查询服务商信息
     * Describe:如果登录用户为平台用户，则读出所有服务商，如为景区企业用，则读出读企业下的服务
     *
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param buttontype
     * @param condition
     * @param pdtp
     * @param iemployeeid后台登录用户
     * @param isjd是否是景点
     * @return return:PaginationSupport
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public PaginationSupport queryList(int page, int pagesize, String url, String path, int buttontype, String condition, String pdtp, Long employeeid, int isjd);

    /**
     * 判断订单中存在数据
     * Describe:
     *
     * @param scenicid
     * @return return:int
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public int retiveOrder(Long scenicid);

    /**
     * 判断它是否是最大层级
     * Describe:
     *
     * @param scencid
     * @return return:boolean
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public boolean boolMaxpro(Long scencid);

    /**
     * 查询服务名称
     * Describe:
     *
     * @param providerId
     * @return return:String
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public String QueryProviderName(Long providerId);

    /**
     * 服务商保存
     * Describe:
     *
     * @param esbscenicareatab
     * return:void
     * Date:2011-9-24
     * @auth:huangyuqi
     */
    public void insertProvider(Esbscenicareatab esbscenicareatab, Syslog syslog) throws Exception;

    /**
     * 修改服务商
     * Describe:
     *
     * @param esbscenicareatab return:void
     *                         Date:2011-9-24
     * @auth:huangyuqi
     */
    public void updateProvider(Esbscenicareatab esbscenicareatab, Syslog syslog);

    /**
     * 删除服务商
     * Describe:
     *
     * @param providerId return:void
     *                   Date:2011-9-24
     * @auth:huangyuqi
     */
    public void deletePorvider(Long providerId, Syslog syslog);

    /**
     * 根据登录人得到服务商列表
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
    public List getScenicList(Esfemployeetab esfemployeetab, String scenictype, int isjd, String isonlyjq,String groupid,boolean isHqyatu);

    /**
     * 根据得到服务商列表(前台专用)
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
    public List getScenicListByPage(String scenictype, int isjd, String isonlyjq);

    /**
     * 根据酒店编号查询出酒店属性
     * Describe:
     *
     * @param iscenicid
     * @return return:Hotelprovider
     * Date:2011-12-12
     * @auth:huangyuqi
     */
    public Hotelprovider queryHotelProvider(Long iscenicid);

    /**
     * 保存酒店服务商属性
     * Describe:
     *
     * @param hotelprovier return:void
     *                     Date:2011-12-12
     * @auth:huangyuqi
     */
    public void saveHotelProvider(Hotelprovider hotelprovier);

    /**
     * 根据服务商编号查出景区属性
     * Describe:
     *
     * @param iscenicid
     * @return return:Hotelprovider
     * Date:2011-12-12
     * @auth:aozhuozu
     */
    public Hotelprovider queryScenicProvider(Long iscenicid);

    /**
     * 保存服务商-景区属性
     * Describe:
     *
     * @param hotelprovier return:void
     *                     Date:2011-12-12
     * @auth:huangyuqi
     */
    public void saveScenicprovider(Hotelprovider hotelprovier);

    /**
     * *
     * Describe:搜索服务商
     *
     * @param type       服务商类别
     * @param notinIds   不包含的服务商ID,以逗号隔开
     * @param regionalid 客源地ID
     * @param inIds      包含的服务商ID,以逗号隔开
     * @return
     * @author chenxinhao
     * Date:2013-12-9
     * @see com.ectrip.system.provider.dao.idao.IProviderDAO#searchNearbyProvider(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    public List searchNearbyProvider(String type, String notinIds, Long regionalid, String inIds);

    /**
     * Describe:搜索产品
     *
     * @param type       服务商类别
     * @param notinIds   为选择产品ID
     * @param inIds      以选择产品ID
     * @param regionalid 客源地
     * @param radio      0为未选择列表  1为已选择列表
     * @return return:List
     * Date:2013-12-9
     * @author:chenxinhao
     */
    public List searchProduct(String type, String notinIds, String inIds,
                              Long regionalid, int radio);

    public List searchAddress(Long regionalid);

    public ProviderCompany queryProviderCompany(Long iscenicid);

    public void saveProviderCompany(ProviderCompany pc);
    
    public List getScenicListSelect(String providerType);
    
    public List getProviderList(String providerType, String keyword);

    public String findpmcd(String pmky, String pmva);
    
    public List getProvidersByIds(String ids);
}
