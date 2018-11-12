package com.ectrip.ticket.salesmanager.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;

/**
 * @author Jzhenhua,Created Time 2011-10-06
 */
public interface IOpcemployeecardtabService {
    /**
     * 添加员工卡证信息
     *
     * @param opcemployeecard
     */
    public void addOpcemployeecard(Opcemployeecardtab opcemployeecardtab);

    /**
     * 删除员工卡证信息
     *
     * @param opcemployeecardtab
     */
    public void delOpcemployeecard(Opcemployeecardtab opcemployeecardtab);

    /**
     * 修改员工卡证信息
     *
     * @param opcemployeecardtab
     */
    public void updateOpcemlopyeecard(Opcemployeecardtab opcemployeecardtab);

    /**
     * 获得所有的员工卡证信息
     *
     * @return
     */
    public PaginationSupport getOpcemployeecard(int flag,String queryString,String iscenicids,String icompanyid,int pageSize, int startIndex,
                                                String url);

    /**
     * 搜索员工卡证信息
     *
     * @param opcemployeecardtab
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     */
    public PaginationSupport searchOpcemployeecard(
            Opcemployeecardtab opcemployeecardtab, int pageSize,
            int startIndex, String url);

    /**
     * 获取所有员工信息
     * @return 所有员工信息的Json
     */
    public String getAllEmployee();

    /**
     * 根据编号查询卡证信息
     * @param id
     * @return
     */
    public Opcemployeecardtab getPloyeeCardById(Long id) throws Exception;


    /**
     *
     * Describe:根据所管辖的服务商获取可使用的员工卡产品
     * @auth:yangguang
     * @param iscenicids
     * @return
     * return:List
     * Date:2011-10-14
     */
    public List getAllTicketType(String iscenicids)throws IllegalAccessException, InvocationTargetException;

    /**
     *
     * Describe:根据所管辖的服务商获取可使用的员工卡产品
     * @auth:lijingrui
     * @param iscenicids
     * @return
     * return:String
     * Date:Nov 14, 2011
     */
    public String getListticketlookup(String iscenicids) throws Exception;
    /**
     * 获取最大ID
     * @return
     */
    public Long getMaxId();

    /**
     * 查询是否存在该员工
     * @param id
     * @return
     */
    public boolean getPloyee(Long id);


    /**
     *
     * Describe:根据员工编号获取此员工可管理的服务商编号
     * @auth:yangguang
     * @param iemployeeid
     * @return
     * return:String
     * Date:2011-10-17
     */
    public String getControlProvider(String iemployeeid);

    /**
     *
     * Describe:判断某个员工是否添加了员工卡证
     * @auth:lijingrui
     * @param iemployeeid
     * @return
     * return:boolean
     * Date:Nov 30, 2011
     */
    public boolean shoplookemployee(Long iemployeeid);

    /**
     *
     * Describe:获取所有员工卡证信息
     * @author:lijingrui
     * @return
     * return:List
     * Date:2015-2-11
     */
    public List checkOpcemployee();

    /**
     *
     * Describe:删除员工卡证的指纹信息
     * @author:lijingrui
     * @param iemployeecardid
     * return:void
     * Date:2015-2-11
     */
    public void delOpcemployeePrint(Long iemployeecardid);

    /**
     *
     * Describe:删除导游验证指纹
     * @author:lijingrui
     * @param myzj
     * return:void
     * Date:2015-3-13
     */
    public void delStssoldPrint(String myzj);

    /**
     *
     * Describe:查询VIP卡信息
     * @author:lijingrui
     * @param flag
     * @param queryString
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2015-11-21
     */
    public PaginationSupport checkListEmployeeCard(String flag,String queryString,int pageSize,int startIndex, String url);

    /**
     *
     * Describe:新增VIP信息
     * @author:lijingrui
     * @param opcemployeecardtab
     * @param syslog
     * return:void
     * Date:2015-11-21
     */
    public void insertVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog);

    /**
     *
     * Describe:修改VIP信息
     * @author:lijingrui
     * @param opcemployeecardtab
     * @param syslog
     * return:void
     * Date:2015-11-21
     */
    public void editVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog);

    /**
     *
     * Describe:删除VIP信息
     * @author:lijingrui
     * @param iemployeecardid
     * @param syslog
     * return:void
     * Date:2015-11-21
     */
    public void delVipCardemp(Long iemployeecardid,Syslog syslog);

    /**
     *
     * Describe:查看VIP信息
     * @author:lijingrui
     * @param iemployeecardid
     * return:void
     * Date:2015-11-21
     */
    public Opcemployeecardtab viewVipCardemp(Long iemployeecardid) throws Exception;
}
