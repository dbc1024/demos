package com.ectrip.ticket.salesmanager.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.salesmanager.dao.IOpcemployeecardtabDAO;
import com.ectrip.ticket.salesmanager.service.IOpcemployeecardtabService;

/**
 * @author Jzhenhua,Created Time 2011-10-06
 */
@Service
public class OpcemployeecardtabService implements IOpcemployeecardtabService {

	@Autowired
    private IOpcemployeecardtabDAO opcemployeecardtabDao;

    /**
     * 添加
     */
    public void addOpcemployeecard(Opcemployeecardtab opcemployeecardtab) {
        this.opcemployeecardtabDao.addOpcemployeecard(opcemployeecardtab);
    }

    /**
     * 删除
     */
    public void delOpcemployeecard(Opcemployeecardtab opcemployeecardtab) {
        this.opcemployeecardtabDao.delOpcemployeecard(opcemployeecardtab);
    }

    /**
     * 获取所有员工JSON
     */
    public String getAllEmployee() {
        return this.opcemployeecardtabDao.getAllEmployee();
    }

    /**
     * 获取所有员工卡证
     */
    public PaginationSupport getOpcemployeecard(int flag, String queryString, String iscenicids,
                                                String icompanyid, int pageSize, int startIndex, String url) {
        return this.opcemployeecardtabDao.getOpcemployeecard(flag, queryString, iscenicids,
                icompanyid, pageSize, startIndex, url);
    }

    /**
     * 查询
     */
    public PaginationSupport searchOpcemployeecard(Opcemployeecardtab opcemployeecardtab,
                                                   int pageSize, int startIndex, String url) {
        return null;
    }

    /**
     * 修改
     */
    public void updateOpcemlopyeecard(Opcemployeecardtab opcemployeecardtab) {
        this.opcemployeecardtabDao.updateOpcemlopyeecard(opcemployeecardtab);
    }

    /**
     * 根据编号查询卡证信息
     */
    public Opcemployeecardtab getPloyeeCardById(Long id) throws Exception {
        return this.opcemployeecardtabDao.getPloyCardById(id);
    }

    /**
     * 获得所有票务类型JSON
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List getAllTicketType(String iscenicids) throws IllegalAccessException, InvocationTargetException {
        return this.opcemployeecardtabDao.getAllTicketType(iscenicids);

    }





    /**
     * 获取最大ID
     */
    public Long getMaxId() {
        return this.opcemployeecardtabDao.getMaxId();
    }

    /**
     * 查询是否存在该员工
     */
    public boolean getPloyee(Long id) {
        List list = this.opcemployeecardtabDao.getPloyee(id);
        if (list.get(0) == null)
            return true;
        else
            return false;
    }






    /**
     *
     * Describe:根据员工编号获取此员工可管理的服务商编号
     *
     * @auth:yangguang
     * @param iemployeeid
     * @return return:String Date:2011-10-17
     */
    public String getControlProvider(String iemployeeid) {
        return this.opcemployeecardtabDao.getControlProvider(iemployeeid);
    }

    public IOpcemployeecardtabDAO getOpcemployeecardtabDao() {
        return opcemployeecardtabDao;
    }

    public void setOpcemployeecardtabDao(IOpcemployeecardtabDAO opcemployeecardtabDao) {
        this.opcemployeecardtabDao = opcemployeecardtabDao;
    }

    /**
     * *
     * Describe:根据所管辖的服务商获取可使用的员工卡产品
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#getListticketlookup(java.lang.String)
     * @param iscenicids
     * @return
     * @author lijingrui
     * Date:Nov 14, 2011
     */
    public String getListticketlookup(String iscenicids) throws Exception{
        return opcemployeecardtabDao.getListticketlookup(iscenicids);
    }

    /**
     * *
     * Describe:判断某个员工是否添加了员工卡证
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#shoplookemployee(java.lang.Long)
     * @param iemployeeid
     * @return
     * @author lijingrui
     * Date:Nov 30, 2011
     */
    public boolean shoplookemployee(Long iemployeeid) {
        return opcemployeecardtabDao.shoplookemployee(iemployeeid);
    }


    /**
     *
     * Describe:获取所有员工卡证信息
     * @author:lijingrui
     * @return
     * return:List
     * Date:2015-2-11
     */
    public List checkOpcemployee(){
        return opcemployeecardtabDao.checkOpcemployee();
    }

    /**
     *
     * Describe:删除员工卡证的指纹信息
     * @author:lijingrui
     * @param iemployeecardid
     * return:void
     * Date:2015-2-11
     */
    public void delOpcemployeePrint(Long iemployeecardid){
        opcemployeecardtabDao.delOpcemployeePrint(iemployeecardid);
    }

    /**
     * *
     * Describe:删除导游验证指纹
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#delStssoldPrint(java.lang.String)
     * @param myzj
     * @author lijingrui
     * Date:2015-3-13
     */
    public void delStssoldPrint(String myzj){
        opcemployeecardtabDao.delStssoldPrint(myzj);
    }

    /**
     * *
     * Describe:查询VIP卡信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#checkListEmployeeCard(java.lang.String, java.lang.String, int, int, java.lang.String)
     * @param flag
     * @param queryString
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @author lijingrui
     * Date:2015-11-21
     */
    public PaginationSupport checkListEmployeeCard(String flag,String queryString,int pageSize,int startIndex, String url){
        return opcemployeecardtabDao.checkListEmployeeCard(flag, queryString, pageSize, startIndex, url);
    }

    /**
     * *
     * Describe:新增VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#insertVipCardemp(com.ectrip.model.salesmanager.Opcemployeecardtab, com.ectrip.model.syspar.Syslog)
     * @param opcemployeecardtab
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void insertVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog){
        opcemployeecardtabDao.insertVipCardemp(opcemployeecardtab, syslog);
    }

    /**
     * *
     * Describe:修改VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#editVipCardemp(com.ectrip.model.salesmanager.Opcemployeecardtab, com.ectrip.model.syspar.Syslog)
     * @param opcemployeecardtab
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void editVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog){
        opcemployeecardtabDao.editVipCardemp(opcemployeecardtab, syslog);
    }

    /**
     * *
     * Describe:删除VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#delVipCardemp(java.lang.Long, com.ectrip.model.syspar.Syslog)
     * @param iemployeecardid
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void delVipCardemp(Long iemployeecardid,Syslog syslog){
        opcemployeecardtabDao.delVipCardemp(iemployeecardid, syslog);
    }

    /**
     * *
     * Describe:查看VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#viewVipCardemp(java.lang.Long)
     * @param iemployeecardid
     * @return
     * @throws Exception
     * @author lijingrui
     * Date:2015-11-21
     */
    public Opcemployeecardtab viewVipCardemp(Long iemployeecardid) throws Exception{
        return opcemployeecardtabDao.viewVipCardemp(iemployeecardid);
    }
}
