package com.ectrip.ec.report.system.datereport.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IEmpCardCheckService {
    /**
     * ��ȡԱ��
     * Describe:
     * @auth:huangyuqi
     * @param esfemployee��ǰ��¼��
     * @return
     * return:List
     * Date:2011-12-26
     */
    public List employeeOfCardList(Esfemployeetab esfemployee);

    /**
     * ��ȡ��԰��Ա��
     * Describe:
     * @auth:huangyuqi
     * @param esfemployee��ǰ��¼��
     * @return
     * return:List
     * Date:2011-12-26
     */
    public List employeeByconstypeList(Esfemployeetab esfemployee);

    /**
     * ���ݵ�¼�˵õ�Ա������ַ������磺1,2,3
     * Describe:
     * @auth:huangyuqi
     * @param esfemployee
     * @return
     * return:String
     * Date:2011-12-26
     */
    public String getEmployeeIds(Esfemployeetab esfemployee);

    /**
     * �õ�����
     * Describe:
     * @auth:huangyuqi
     * @return
     * return:List
     * Date:2011-12-26
     */
    public List getdaoYouList();
    /**
     * ��ѯԱ��������ϸ
     * Describe:
     * @auth:huangyuqi
     * @param employee
     * @param employeeId
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2011-12-26
     */
    public PaginationSupport getEmployeeCardList(Esfemployeetab employee,Long employeeId,String rzti,String ldti,int page,int pageSize,String url);
    public List getEmployeeCardList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti);
    /**
     *
     * Describe:��ѯԱ����԰��ϸ
     * @auth:lijingrui
     * @param esfemployee
     * @param employeeId
     * @param rzti
     * @param ldti
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2012-12-18
     */
    public PaginationSupport getEmployeeByconstypeList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti, int page, int pageSize,String url);
    public List getEmployeeByconstypeList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti);

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
    public PaginationSupport getDaoYouCardList(int radiobutton,String usid,String lname,String usids,String rzti,String ldti,Long iscenicid,int page,int pageSize,String url);

    /**
     *
     * Describe:��ʾ���õ���
     * @auth:lijingrui
     * @param usid
     * @return
     * return:List
     * Date:Mar 13, 2012
     */
    public List getDaoyouview(String usid);
    /**
     *
     * Describe:
     * @auth:yuanchengjun
     * @param radiobutton
     * @param usid
     * @param rzti
     * @param ldti
     * @param iscenicid
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2012-4-8
     */

    public PaginationSupport getDaoYouCardListbydyusid(String usid,String rzti, String ldti, Long iscenicid,
                                                       int page, int pageSize, String url);
}

