package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.datereport.dao.idao.IEmpCardCheckDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IEmpCardCheckService;
import com.ectrip.sys.model.employee.Esfemployeetab;

public class EmpCardCheckService implements IEmpCardCheckService {
    private IEmpCardCheckDAO empcardcheckDao;

    public IEmpCardCheckDAO getEmpcardcheckDao() {
        return empcardcheckDao;
    }
    public void setEmpcardcheckDao(IEmpCardCheckDAO empcardcheckDao) {
        this.empcardcheckDao = empcardcheckDao;
    }
    /**
     * ��ȡԱ��
     * Describe:
     * @auth:huangyuqi
     * @param esfemployee��ǰ��¼��
     * @return
     * return:List
     * Date:2011-12-26
     */
    public List employeeOfCardList(Esfemployeetab esfemployee){
        return empcardcheckDao.employeeOfCardList(esfemployee);
    }

    /**
     * *
     * Describe:��ȡ��԰��Ա��
     * @see com.ectrip.report.system.datereport.service.iservice.IEmpCardCheckService#employeeByconstypeList(com.ectrip.model.employee.Esfemployeetab)
     * @param esfemployee
     * @return
     * @author lijingrui
     * Date:2012-12-18
     */
    public List employeeByconstypeList(Esfemployeetab esfemployee){
        return empcardcheckDao.employeeByconstypeList(esfemployee);
    }

    /**
     * ���ݵ�¼�˵õ�Ա������ַ������磺1,2,3
     * Describe:
     * @auth:huangyuqi
     * @param esfemployee
     * @return
     * return:String
     * Date:2011-12-26
     */
    public String getEmployeeIds(Esfemployeetab esfemployee){
        return empcardcheckDao.getEmployeeIds(esfemployee);
    }

    /**
     * �õ�����
     * Describe:
     * @auth:huangyuqi
     * @return
     * return:List
     * Date:2011-12-26
     */
    public List getdaoYouList(){
        return empcardcheckDao.getdaoYouList();
    }
    /**
     * ��ѯԱ��������ϸ
     * Describe:
     * @auth:huangyuqi
     * @param employeeId
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2011-12-26
     */
    public PaginationSupport getEmployeeCardList(Esfemployeetab employee,Long employeeId,String rzti,String ldti,int page,int pageSize,String url){
        return empcardcheckDao.getEmployeeCardList(employee,employeeId, rzti, ldti, page, pageSize, url);
    }
    public List getEmployeeCardList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti){
        return empcardcheckDao.getEmployeeCardList(esfemployee, employeeId, rzti, ldti);
    }
    /**
     * *
     * Describe:��ѯԱ����԰��ϸ
     * @see com.ectrip.report.system.datereport.service.iservice.IEmpCardCheckService#getEmployeeByconstypeList(com.ectrip.model.employee.Esfemployeetab, java.lang.Long, java.lang.String, java.lang.String, int, int, java.lang.String)
     * @param esfemployee
     * @param employeeId
     * @param rzti
     * @param ldti
     * @param page
     * @param pageSize
     * @param url
     * @return
     * @author lijingrui
     * Date:2012-12-18
     */
    public PaginationSupport getEmployeeByconstypeList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti, int page, int pageSize,String url){
        return empcardcheckDao.getEmployeeByconstypeList(esfemployee, employeeId, rzti, ldti, page, pageSize, url);
    }

    public List getEmployeeByconstypeList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti){
        return empcardcheckDao.getEmployeeByconstypeList(esfemployee, employeeId, rzti, ldti);
    }

    /**
     * ��ѯ���ι�������ϸ
     * Describe:
     * @auth:huangyuqi
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2011-12-26
     */
    public PaginationSupport getDaoYouCardList(int radiobutton,String usid,String lname,String usids,String rzti,String ldti,Long iscenicid,int page,int pageSize,String url){
        return empcardcheckDao.getDaoYouCardList(radiobutton,usid,lname,usids ,rzti, ldti,iscenicid, page, pageSize, url);
    }

    /**
     * *
     * Describe:��ʾ���õ���
     * @see com.ectrip.report.system.datereport.service.iservice.IEmpCardCheckService#getDaoyouview(java.lang.String)
     * @param usid
     * @return
     * @author lijingrui
     * Date:Mar 13, 2012
     */
    public List getDaoyouview(String usid) {
        return empcardcheckDao.getDaoyouview(usid);
    }


    public PaginationSupport getDaoYouCardListbydyusid( String usid,String rzti, String ldti, Long iscenicid,
                                                        int page, int pageSize, String url){
        return empcardcheckDao.getDaoYouCardListbydyusid( usid, rzti, ldti, iscenicid, page, pageSize, url);
    }
}

