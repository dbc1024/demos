package com.ectrip.ec.report.system.datereport.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.report.system.datereport.dao.idao.IEmpCardCheckDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;

public class EmpCardCheckDAO extends GenericDao implements IEmpCardCheckDAO {

    /**
     * ��ȡ���е�Ա�� Describe:
     *
     * @auth:huangyuqi
     * @param esfemployee��ǰ��¼��
     * @return return:List Date:2011-12-26
     */
    public List employeeOfCardList(Esfemployeetab esfemployee) {
        List list = new ArrayList();
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select new map( op.iemployeeid as iemployeeid,emp.szemployeename as szemployeename ) from Opcemployeecardtab op,Esfemployeetab emp where op.iagentno='1' and  op.iemployeeid = emp.iemployeeid ");
        // Ϊ������ҵԱ��
        if ("02".equals(esfemployee.getCompanytype())) {
            StringBuffer companyids = new StringBuffer();
            // �õ����й�˾���
            companyids.append(esfemployee.getIcompanyinfoid());
            List emplist = this.getemp(esfemployee.getIcompanyinfoid());

            if (emplist != null && emplist.size() >= 1) {
                for (int i = 0; i < emplist.size(); i++) {
                    Galcompanyinfotab company = (Galcompanyinfotab) emplist.get(i);
                    companyids.append("," + company.getIcompanyinfoid());
                }
            }
            hsql.append(" and emp.icompanyinfoid in (" + companyids + ")");
        }
        System.out.println(hsql.toString());
        list = this.find(hsql.toString());
        List list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Esfemployeetab employee = new Esfemployeetab();
            try {
                BeanUtils.populate(employee, (Map) list.get(i));
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            list1.add(employee);
        }
        return list1;
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
        List list = new ArrayList();
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select distinct new map( op.iemployeeid as iemployeeid,emp.szemployeename as szemployeename ) from Opcemployeecardtab op,Esfemployeetab emp where op.byconsumetype='1' and  op.iemployeeid = emp.iemployeeid ");
        // Ϊ������ҵԱ��
        if ("02".equals(esfemployee.getCompanytype())) {
            StringBuffer companyids = new StringBuffer();
            // �õ����й�˾���
            companyids.append(esfemployee.getIcompanyinfoid());
            List emplist = this.getemp(esfemployee.getIcompanyinfoid());

            if (emplist != null && emplist.size() >= 1) {
                for (int i = 0; i < emplist.size(); i++) {
                    Galcompanyinfotab company = (Galcompanyinfotab) emplist.get(i);
                    companyids.append("," + company.getIcompanyinfoid());
                }
            }
            hsql.append(" and emp.icompanyinfoid in (" + companyids + ")");
        }

        list = this.find(hsql.toString());
        List list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Esfemployeetab employee = new Esfemployeetab();
            try {
                BeanUtils.populate(employee, (Map) list.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            list1.add(employee);
        }
        return list1;
    }

    public List getemp(Long icompanyid) {
        List list = new ArrayList();
        // �����ϼ���ŵõ��¼�����б�
        String sql = " from Galcompanyinfotab where szinfocomid = " + icompanyid;
        list = this.find(sql);
        return list;
    }

    /**
     * ���ݵ�¼�˵õ�Ա������ַ������磺1,2,3 Describe:
     *
     * @auth:huangyuqi
     * @param esfemployee
     * @return return:String Date:2011-12-26
     */
    public String getEmployeeIds(Esfemployeetab esfemployee) {
        StringBuffer companyids = new StringBuffer();
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select ps.iemployeeid from Opcempployeecardcheck ps,Esfemployeetab emp where ps.iemployeeid is not null and  ps.iemployeeid=emp.iemployeeid  ");
        // Ϊ������ҵԱ��
        if ("02".equals(esfemployee.getCompanytype())) {
            companyids.append(esfemployee.getIcompanyinfoid());
            List emplist = this.getemp(esfemployee.getIcompanyinfoid());

            if (emplist != null && emplist.size() >= 1) {
                for (int i = 0; i < emplist.size(); i++) {
                    Galcompanyinfotab company = (Galcompanyinfotab) emplist.get(i);
                    companyids.append("," + company.getIcompanyinfoid());
                }
            }
            hsql.append(" where emp.icompanyinfoid in (" + companyids + ") ");
        }

        List list = this.find(hsql.toString());
        if (list != null && list.size() >= 1) {
            for (int i = 0; i < list.size(); i++) {
                // Esfemployeetab emp = (Esfemployeetab)list.get(i);

                long iemployeeid = (Long) list.get(i);

                if (i == list.size() - 1) {
                    companyids.append(iemployeeid);
                } else {
                    companyids.append(iemployeeid + ",");

                }
                System.out.println("companyids=" + companyids);
            }
        }

        return companyids.toString();
    }

    /**
     * �õ����� Describe:
     *
     * @auth:huangyuqi
     * @return return:List Date:2011-12-26
     */
    public List getdaoYouList() {
        List list = new ArrayList();
        String hsql = " select new map(cus.usid as usid,cus.lname as lname) from Opcemployeecardtab opc,Custom cus where byisdaoyou=1 and opc.usid=cus.usid order by cus.lname";
        list = this.find(hsql);
        List list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Custom c = new Custom();
            try {
                BeanUtils.populate(c, (Map) list.get(i));
                list1.add(c);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return list1;
    }

    /**
     * ��ѯԱ��������ϸ Describe:
     *
     * @auth:huangyuqi
     * @param employeeId
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-12-26
     */
    public PaginationSupport getEmployeeCardList(Esfemployeetab esfemployee,
                                                 Long employeeId, String rzti, String ldti, int page, int pageSize,
                                                 String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select new map(grd.szgardengatename as szgardengatename,card.iemployeeid as iemployeeid,emp.szemployeename as szemployeename,card.icardno as icardno,card.szdtime as szdtime,acc.szaccessequipname as  szaccessequipname,card.szticketprintno as szticketprintno) from Opcempployeecardcheck card,Esfemployeetab emp,Esbgardengatetab grd,Esbaccessequiptab acc,Opcemployeecardtab op where op.byisdaoyou=0 and substr(card.szdtime,1,10)>='"
                        + rzti + "' and substr(card.szdtime,1,10) <='" + ldti + "' and op.iagentno='1' ");

        if (0L == employeeId) {// ����

        } else {
            hsql.append(" and card.iemployeeid=" + employeeId);
        }

        hsql.append(" and  op.iemployeecardid=card.iemployeecardid and op.iemployeeid=card.iemployeeid and op.iemployeeid=emp.iemployeeid and card.iemployeeid = emp.iemployeeid and card.igardengateid = grd.id.igardengateid  and acc.id.iaccessequipid = card.iaccessequipid and (card.szticketprintno  is not null or trim(card.szticketprintno)!='') ");
        hsql.append(" order by emp.empid, card.szdtime desc");
        ps = this.findPage(hsql.toString(), pageSize, page, url);
        return ps;
    }

    public List getEmployeeCardList(Esfemployeetab esfemployee,
                                    Long employeeId, String rzti, String ldti) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select new map(grd.szgardengatename as szgardengatename,card.iemployeeid as iemployeeid,emp.szemployeename as szemployeename,card.icardno as icardno,card.szdtime as szdtime,acc.szaccessequipname as  szaccessequipname,card.szticketprintno as szticketprintno) from Opcempployeecardcheck card,Esfemployeetab emp,Esbgardengatetab grd,Esbaccessequiptab acc,Opcemployeecardtab op where op.byisdaoyou=0 and substr(card.szdtime,1,10)>='"
                        + rzti + "' and substr(card.szdtime,1,10) <='" + ldti + "' and op.iagentno='1' ");

        if (0L != employeeId) {// ����
            hsql.append(" and card.iemployeeid=" + employeeId);
        }

        hsql.append(" and  op.iemployeecardid=card.iemployeecardid and op.iemployeeid=card.iemployeeid and op.iemployeeid=emp.iemployeeid and card.iemployeeid = emp.iemployeeid and card.igardengateid = grd.id.igardengateid  and acc.id.iaccessequipid = card.iaccessequipid and (card.szticketprintno  is not null or trim(card.szticketprintno)!='') ");
        hsql.append(" order by emp.empid, card.szdtime desc");
        return this.find(hsql.toString());
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
    public PaginationSupport getEmployeeByconstypeList(Esfemployeetab esfemployee,
                                                       Long employeeId, String rzti, String ldti, int page, int pageSize,
                                                       String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select new map(grd.szgardengatename as szgardengatename,card.iemployeeid as iemployeeid,emp.szemployeename as szemployeename,card.icardno as icardno,card.szdtime as szdtime,acc.szaccessequipname as  szaccessequipname,card.szticketprintno as szticketprintno) from Opcempployeecardcheck card,Esfemployeetab emp,Esbgardengatetab grd,Esbaccessequiptab acc,Opcemployeecardtab op where op.byisdaoyou=0 and substr(card.szdtime,1,10)>='"
                        + rzti + "' and substr(card.szdtime,1,10) <='" + ldti + "' and op.byconsumetype='1' ");

        if (0L == employeeId) {// ����

        } else {
            hsql.append(" and card.iemployeeid=" + employeeId);
        }

        hsql.append(" and  op.iemployeecardid=card.iemployeecardid and op.iemployeeid=card.iemployeeid and op.iemployeeid=emp.iemployeeid and card.iemployeeid = emp.iemployeeid and card.igardengateid = grd.id.igardengateid  and acc.id.iaccessequipid = card.iaccessequipid ");
        hsql.append(" order by emp.empid, card.szdtime desc");
        ps = this.findPage(hsql.toString(), pageSize, page, url);
        return ps;
    }

    public List getEmployeeByconstypeList(Esfemployeetab esfemployee,Long employeeId, String rzti, String ldti) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select new map(grd.szgardengatename as szgardengatename,card.iemployeeid as iemployeeid,emp.szemployeename as szemployeename,card.icardno as icardno,card.szdtime as szdtime,acc.szaccessequipname as  szaccessequipname,card.szticketprintno as szticketprintno) from Opcempployeecardcheck card,Esfemployeetab emp,Esbgardengatetab grd,Esbaccessequiptab acc,Opcemployeecardtab op where op.byisdaoyou=0 and substr(card.szdtime,1,10)>='"
                        + rzti + "' and substr(card.szdtime,1,10) <='" + ldti + "' and op.byconsumetype='1' ");

        if (0L != employeeId) {// ����
            hsql.append(" and card.iemployeeid=" + employeeId);
        }

        hsql.append(" and  op.iemployeecardid=card.iemployeecardid and op.iemployeeid=card.iemployeeid and op.iemployeeid=emp.iemployeeid and card.iemployeeid = emp.iemployeeid and card.igardengateid = grd.id.igardengateid  and acc.id.iaccessequipid = card.iaccessequipid ");
        hsql.append(" order by emp.empid, card.szdtime desc");
        return this.find(hsql.toString());
    }

    /**
     * ��ѯ���ι�������ϸ Describe:
     *
     * @auth:huangyuqi
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-12-26
     */
    public PaginationSupport getDaoYouCardList(int radiobutton, String usid,
                                               String lname, String usids, String rzti, String ldti, Long iscenicid,
                                               int page, int pageSize, String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select new map(t.dyusid as dyusid,c1.lname as lname,c2.usid as usid,c2.corpname as corpname,t.id.orid as orid,sum(tz.znumb) as numb) from TOrder t,TZorderlist tz,Custom c1,Custom c2,Edmtickettypetab ed where t.ddzt='11' and ");
        hsql.append(" t.dtstartdate>='" + rzti + "' and t.dtstartdate<='" + ldti
                + "'");
        if (radiobutton == 1) {
            hsql.append(" and c1.lname like '%" + lname + "%'");
        } else {
            if (!usid.equals("0000")) {
                hsql.append(" and t.dyusid='" + usid + "'");
            }
        }
        if (!usids.equals("")) {
            hsql.append(" and t.usid in (" + usids + ")");
        }
        if (iscenicid==0L) {
            hsql.append(" and t.usid=c2.usid and t.dyusid=c1.usid and t.id.orid=tz.id.orid and t.id.iscenicid=tz.id.iscenicid and ed.itickettypeid=tz.iztickettypeid and tz.icrowdkindid=1 and ed.bispersontimestat=1  ");
        }else {
            hsql.append(" and t.usid=c2.usid and t.dyusid=c1.usid and t.id.orid=tz.id.orid and t.id.iscenicid=tz.id.iscenicid and ed.itickettypeid=tz.iztickettypeid and tz.icrowdkindid=1 and ed.bispersontimestat=1 and ed.iscenicid="
                    + iscenicid + " ");
        }
        hsql.append(" group by t.dyusid,c1.lname,c2.usid,c2.corpname,t.id.orid order by t.id.orid");
        ps = this.findPage(hsql.toString(), pageSize, page, url);
        List list = ps.getItems();
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String orid = map.get("orid").toString();
            String hsql1 = "select sum(tz.znumb) as numb from MOrder m,MOrder tm,TZorderlist tz,Edmtickettypetab ed where m.orid='"
                    + orid
                    + "' and tm.srid=m.orid and tm.orid=tz.id.orid and ed.itickettypeid=tz.iztickettypeid and tz.icrowdkindid=1 and ed.bispersontimestat=1 and ed.iscenicid="
                    + iscenicid;
            List list2 = this.find(hsql1);
            Long numb = new Long(0);
            if (list2.size() > 0) {
                if (list2.get(0) != null) {
                    numb = new Long(list2.get(0).toString());
                }
            }
            map.put("numb", Long.parseLong(map.get("numb").toString()) - numb);

        }
        System.out.println("111111:"+hsql);
        return ps;
    }

    /**
     * * Describe:��ʾ���õ���
     *
     * @see com.ectrip.report.system.datereport.service.iservice.IEmpCardCheckService#getDaoyouview(java.lang.String)
     * @param usid
     * @return
     * @author lijingrui Date:Mar 13, 2012
     */
    public List getDaoyouview(String usid) {
        String hsql = "  FROM Custom d  WHERE d.usid  in (select trim(a.id.dyusid) from Daoyou a where a.id.usid='"
                + usid + "') and d.lgtp='02' and d.ttlb='02' and d.status='01'";
        return this.find(hsql);
    }

    /**
     * �����Լ���ѯ���ι�������ϸ Describe:
     *
     * @auth:huangyuqi
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-12-26
     */
    public PaginationSupport getDaoYouCardListbydyusid(String usid, String rzti,
                                                       String ldti, Long iscenicid, int page, int pageSize, String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select new map(t.dyusid as dyusid,c1.lname as lname,c2.usid as usid,c2.corpname as corpname,t.id.orid as orid,sum(tz.znumb) as numb) from TOrder t,TZorderlist tz,Custom c1,Custom c2,Edmtickettypetab ed where t.ddzt='11' and ");
        hsql.append(" t.dtstartdate>='" + rzti + "' and t.dtstartdate<='" + ldti
                + "'");
        hsql.append(" and t.dyusid='" + usid + "'");
        hsql
                .append(" and t.usid=c2.usid and t.dyusid=c1.usid and t.id.orid=tz.id.orid and t.id.iscenicid=tz.id.iscenicid and ed.itickettypeid=tz.iztickettypeid and tz.icrowdkindid=1 and ed.bispersontimestat=1 and ed.iscenicid="
                        + iscenicid + " ");
        hsql
                .append(" group by t.dyusid,c1.lname,c2.usid,c2.corpname,t.id.orid order by t.id.orid");

        ps = this.findPage(hsql.toString(), pageSize, page, url);

        List list = ps.getItems();

        for (int i = 0; i < list.size(); i++) {

            Map map = (Map) list.get(i);

            String orid = map.get("orid").toString();

            String hsql1 = "select sum(tz.znumb) as numb from MOrder m,MOrder tm,TZorderlist tz,Edmtickettypetab ed where m.orid='"
                    + orid
                    + "' and tm.srid=m.orid and tm.orid=tz.id.orid and ed.itickettypeid=tz.iztickettypeid and tz.icrowdkindid=1 and ed.bispersontimestat=1 and ed.iscenicid="
                    + iscenicid;

            List list2 = this.find(hsql1);

            Long numb = new Long(0);

            if (list2.size() > 0) {
                if (list2.get(0) != null) {
                    numb = new Long(list2.get(0).toString());
                }
            }

            map.put("numb", Long.parseLong(map.get("numb").toString()) - numb);

        }
        return ps;
    }

}
