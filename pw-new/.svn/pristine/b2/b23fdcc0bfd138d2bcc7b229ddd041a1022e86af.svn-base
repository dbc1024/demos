package com.ectrip.ec.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.order.dao.idao.IPayOrderDAO;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
@Repository
public class PayOrderDAO extends GenericDao implements IPayOrderDAO {

    public List getTZorderlistByOrid(String orid) {
        String hql = "select new map(sum(tzlist.znumb) as znumb,tzlist.tripid as tripid,tzlist.ivenueid as ivenueid,tzlist.ivenueareaid as ivenueareaid,tzlist.ivenueseatsid as ivenueseatsid,tzlist.id.iscenicid  as iscenicid,tzlist.id.orid as orid,tzlist.iztickettypeid as iztickettypeid,tzlist.dtstartdate as   dtstartdate) from TZorderlist tzlist where tzlist.id.orid='"
                + orid
                + "' group by  tzlist.tripid,tzlist.ivenueid,tzlist.ivenueareaid,tzlist.id.iscenicid,tzlist.id.orid,tzlist.ivenueseatsid,tzlist.iztickettypeid,tzlist.dtstartdate";
        return find(hql);
    }

    public List getTZorderlistByTicket(String orid, String iticketid) {
        String hql = "select new map(tzlist.itickettypeid as itickettypeid,tzlist.tripid as tripid,tzlist.ivenueid as ivenueid,tzlist.ivenueareaid as ivenueareaid,tzlist.ivenueseatsid as ivenueseatsid,tzlist.id.iscenicid  as iscenicid,tzlist.id.orid as orid,tzlist.iztickettypeid as iztickettypeid,tzlist.dtstartdate as   dtstartdate) from TZorderlist tzlist where tzlist.id.orid='"
                + orid
                + "' and tzlist.itickettypeid="
                + iticketid
                + " group by tzlist.itickettypeid,tzlist.tripid,tzlist.ivenueid,tzlist.ivenueareaid,tzlist.ivenueseatsid,tzlist.id.iscenicid,tzlist.id.orid,tzlist.iztickettypeid,tzlist.dtstartdate";
        return find(hql);
    }

    /**
     *
     * Describe:���������� ���й��˴μ���
     *
     * @auth:yangguang
     * @param orid
     * @param zorderlist
     * @return return:boolean Date:2011-11-15
     */
    public void updateStock(String orid, TZorderlist zorderlist) {
        StringBuffer hql = new StringBuffer();
        Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, zorderlist.getIztickettypeid());
        if (ticket.getIssale() == 1) {
            hql.append(" from Productcontrol where iscenicid=" + zorderlist.getIscenicid() + " and itickettypeid="
                    + zorderlist.getIztickettypeid() + " and stdata='" + zorderlist.getDtstartdate().substring(0, 10)
                    + "' and controltype='03'");
            if (zorderlist != null && !zorderlist.getTripid().equals("") && zorderlist.getTripid().intValue() != 0) {
                hql.append(" and tripid=" + zorderlist.getTripid() + "");
            }
            List list = find(hql.toString());
            Productcontrol p = null;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    p = (Productcontrol) list.get(i);
                    p.setSoldnumber(p.getSoldnumber() + zorderlist.getZnumb());
                    this.update(p);
                }
            }

        }
    }

    /**
     * * Describe:��ȡ���� ��Ʒ�����б� ���ϲ�ͬ��Ʒ��ͬ�۸� ��ƱֻҪ��ͬһ���������ӽ��кϲ� ���˴�֮���ʱ������ ֻҪ��ͬһ���Ҳ�ϲ� ֻҪ��ͬһ��ͬ��Ʒ��ȫ���ϲ� �������
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#getTorderlistGroup(java.lang.String)
     * @param orid
     * @return
     * @author yangguang Date:2011-11-18
     */
    public List getTZorderlist(String orid) {
        String hql = "from TZorderlist where id.orid='" + orid + "' and tripid!=0";
        return find(hql);
    }

    /**
     * * Describe:�����տ���
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#updateDayControl(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     *      int)
     * @param orid
     * @param date
     * @param itickettypeid
     * @param iscenicid
     * @param numb
     * @return
     * @author yangguang Date:2011-11-18
     */
    public void updateDayControl(String orid, String date, String itickettypeid, String iscenicid, int numb) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Productcontrol where iscenicid=" + iscenicid + " and itickettypeid=" + itickettypeid + " and stdata='" + date
                + "' and controltype='02'");
        List list = this.find(hql.toString());
        Productcontrol p = null;
        if (list != null && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                p = (Productcontrol) list.get(j);
                p.setSoldnumber(p.getSoldnumber() + numb);
                this.update(p);
            }
        }
    }

    /**
     * * Describe:����Ʊ�д��ж����ѡ��������(һ����˵����������)����Ʊ,
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#getNestTicketDate(java.lang.String, java.lang.String)
     * @param orid
     * @param itickettypeid
     * @return
     * @author yangguang Date:2011-11-18
     */
    public List getNestTicketDate(String orid, String itickettypeid) {
        String hql = "select new map(sum(zt.znumb) as znumb,zt.itickettypeid as itickettypeid,zt.dtstartdate as dtstartdate) from TZorderlist zt where zt.id.orid = '"
                + orid + "'  and zt.itickettypeid=" + itickettypeid + " and zt.tripid!=0 group by zt.itickettypeid,zt.dtstartdate";
        return find(hql);
    }

    /**
     * * Describe:��ȡ����״̬
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#getPayStatus(java.lang.String, java.lang.String, java.lang.String)
     * @param orid
     * @param mont
     * @param bank
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author yangguang Date:2011-11-18
     */
    public int getPayStatus(String orid, String mont, String bank) throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(m.zfmont as zfmont,m.ddzt as ddzt,m.zffs as zffs,m.bank as bank) from MOrder m where m.orid='" + orid
                + "'";
        List list = find(hql);
        MOrder morder = null;
        if (list == null || list.size() < 1) {// �޴˶���
            return -1;
        }
        morder = new MOrder();
        BeanUtils.populate(morder, (Map) list.get(0));
        if (morder.getDdzt().equals("02") || morder.getDdzt().equals("23")) {// ��֧��
            return -2;
        }
        if (morder.getZfmont() != Double.parseDouble(mont)) {// ֧������
            return -3;
        }
        return 1;// ����״̬
    }

    /**
     * * Describe:��֤��Ʒ�˴ο���
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#validateTripControl(com.ectrip.model.order.TZorderlist)
     * @param zorderlist
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author yangguang Date:2011-11-18
     */
    public boolean validateTripControl(TZorderlist zorderlist) throws IllegalAccessException, InvocationTargetException {
        Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, zorderlist.getIztickettypeid());
        String hql = "from Productcontrol where iscenicid=" + zorderlist.getIscenicid() + " and itickettypeid="
                + zorderlist.getIztickettypeid() + " and stdata='" + zorderlist.getDtstartdate().substring(0, 10)
                + "' and controltype='03' and tripid=" + zorderlist.getTripid() + "";
        System.out.println("===============>>.validation sql:"+hql);
        List list = find(hql);
        if (Tools.getDayNumb(zorderlist.getDtstartdate().substring(0, 10), Tools.getDays()) <= 1) {
            if (ticket.getIssale() == 1) {// ��������
                if (list == null) {// �����վ�Ϊ��
                    if (ticket.getIscansale() == 1) {// ������
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    Productcontrol control = (Productcontrol) list.get(0);
                    // BeanUtils.populate(control, (Map) list.get(0));
                    if (control.getSalablenumber() - control.getSoldnumber() >= zorderlist.getZnumb()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * * Describe:��֤��Ʒ�տ���
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#validateDayControl(java.lang.String, com.ectrip.model.order.TOrderlist)
     * @param orid
     * @param orderlist
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author yangguang Date:2011-11-18
     */

    public boolean validateDayControl(String orid, TOrderlist orderlist) throws IllegalAccessException, InvocationTargetException {
        StringBuffer hql = new StringBuffer();
        TZorderlist tz = null;
        Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, orderlist.getItickettypeid());
        List list = null;
        List zlist = getTZorderlistByTicket(orid, orderlist.getItickettypeid().toString());
        Edmtickettypetab zticket = null;
        if (zlist != null && zlist.size() > 0) {
            for (int j = 0; j < zlist.size(); j++) {
                tz = new TZorderlist();
                BeanUtils.populate(tz, (Map) zlist.get(j));
                zticket = (Edmtickettypetab) get(Edmtickettypetab.class, tz.getIztickettypeid());
                if (zticket.getIssale() == 1) {
                    if (hql.length() > 0) {
                        hql.delete(0, hql.length() - 1);
                    }
                    hql.append(" from Productcontrol where iscenicid=" + orderlist.getId().getIscenicid() + " and itickettypeid="
                            + tz.getItickettypeid() + " and stdata='" + tz.getDtstartdate().substring(0, 10) + "' and controltype='02'");
                } else {
                    continue;
                }
                list = find(hql.toString());
                if (Tools.getDayNumbCha(orderlist.getDtstartdate().substring(0, 10), Tools.getDays()) <= 0) {
                    if (ticket.getIscontrol() == 1) {// ��������
                        if (list == null || list.size() < 1) {// �����վ�Ϊ��
                            if (ticket.getIscontrolsale() == 1) {// ������
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            Productcontrol control = (Productcontrol) list.get(0);
                            if (control.getSalablenumber() - control.getSoldnumber() >= orderlist.getNumb()) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * * Describe:��ȡ��Ʒ�˴ο���
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#getProductcontrol(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     * @param itickettypeid
     * @param tourdate
     * @param ivenueid
     * @param ivenueareaid
     * @param tripid
     * @return
     * @author yangguang Date:2011-11-18
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Productcontrol getProductcontrol(String itickettypeid, String tourdate, String ivenueid, String ivenueareaid, String tripid)
            throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(c.productcontrolid as productcontrolid,c.iscenicid as iscenicid,c.itickettypeid as itickettypeid,c.tripid as tripid,c.controltype as controltype,c.stdata as stdata,c.salablenumber as salablenumber,c.soldnumber as soldnumber,c.reservedsalenumber as reservedsalenumber,c.ivenueid as ivenueid,c.ivenueareaid as ivenueareaid,c.bystate as bystate,c.itickettypeid as itickettypeid,ve.ivenueareaname as venueidname,t.tripname as tripname) from Productcontrol c, Venuearea ve, Venue v,Trip t where c.ivenueareaid = ve.ivenueareaid and c.ivenueid = v.ivenueid and c.tripid=t.tripid and c.controltype='03' and c.ivenueid="
                + ivenueid
                + " and c.ivenueareaid="
                + ivenueareaid
                + " and c.stdata='"
                + tourdate
                + "' and c.tripid="
                + tripid
                + " and c.itickettypeid=" + itickettypeid + "";
        List list = find(hql);
        Productcontrol control = null;
        if (list != null && list.size() > 0) {
            control = new Productcontrol();
            BeanUtils.populate(control, (Map) list.get(0));
        }
        return control;
    }

    /**
     * * Describe:��ȡ��Ʒ�տ���
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#getProductcontrol(java.lang.String, java.lang.String)
     * @param itickettypeid
     * @param tourdate
     * @return
     * @author yangguang Date:2011-11-18
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Productcontrol getProductcontrol(String itickettypeid, String tourdate) throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(c.productcontrolid as productcontrolid,c.iscenicid as iscenicid,c.itickettypeid as itickettypeid,c.controltype as controltype,c.stdata as stdata,c.salablenumber as salablenumber,c.soldnumber as soldnumber,c.reservedsalenumber as reservedsalenumber,c.bystate as bystate,c.itickettypeid as itickettypeid,ticket.sztickettypename as sztickettypename) from Productcontrol c,Edmtickettypetab ticket where  c.controltype='02' and c.stdata='"
                + tourdate + "' and c.itickettypeid=" + itickettypeid + " and c.itickettypeid=ticket.itickettypeid";
        List list = find(hql);
        Productcontrol control = null;
        if (list != null && list.size() > 0) {
            control = new Productcontrol();
            BeanUtils.populate(control, (Map) list.get(0));
        }
        return control;
    }

    public List getTorderByOrid(String orid) {
        String hql = "from TOrderlist where id.orid='" + orid + "'";
        return find(hql);
    }

    public List getTorder(String orid) {
        String hql = "from TOrder where id.orid='" + orid + "'";
        return find(hql);
    }

    public List getYorder(String orid) {
        String hql = "from YOrder where id.orid='" + orid + "'";
        return find(hql);
    }

    public TZorderlist getTZorderlistbyT(TOrderlistId id) {
        String hql = "from TZOrderlist where id.iscenicid=" + id.getIscenicid() + " and id.orid='" + id.getIscenicid()
                + "' and id.orderlistid=" + id.getOrderlistid() + " and tripid!=0";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (TZorderlist) list.get(0);
        } else {
            return null;
        }
    }

    public List getTorderlistByTorder(TOrder torder) {
        String hql = "from TOrderlist where id.orid='" + torder.getId().getOrid() + "' and id.iscenicid=" + torder.getId().getIscenicid()
                + "";
        return find(hql);
    }


    public List getYorderlistByTorder(TOrder torder) {
        String hql = "from YOrderlist where id.orid='" + torder.getId().getOrid() + "' and id.iscenicid=" + torder.getId().getIscenicid()
                + "";
        return find(hql);
    }
    public List getOrderFilmSeat(String orid){
        String hql=" from Seatordertab where id.orid='"+orid+"'";
        return find(hql);
    }

}
