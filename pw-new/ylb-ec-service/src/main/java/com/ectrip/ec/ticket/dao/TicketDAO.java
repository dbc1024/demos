package com.ectrip.ec.ticket.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Credit;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
@Repository
public class TicketDAO extends GenericDao implements ITicketDAO {

    public List getsonJIngquProviderByPid(Long iscenicid) {
        String hql = "from Esbscenicareatab where iparentid=" + iscenicid + " and isjd=0 and byisuse=1";
        return find(hql);
    }

    public List getJingquProviderList(List list) {
    	StringBuffer hql = new StringBuffer();
        	hql.append("from Esbscenicareatab where scenictype='01' and byisuse=1 and isjd=0 and ilevel=1 and iscenicid in " );
            hql.append("(select distinct edm.iscenicid from Edmtickettypetab edm where edm.itickettypeid in (select ec.itickettypeid  from Edmcrowdkindpricetab ec where ec.isnet=1 ))");        	
            if(null != list && list.size()>0){
            	StringBuffer buffer = new StringBuffer();
            	for (Object obj : list) {
					buffer.append(" '" + obj + "',");
				}
            	if(buffer.length()>1){
            		hql.append(" and iscenicid in ("+buffer.substring(0,buffer.length()-1)+") ");	
            	}
        		
        	}
                
        return find(hql.toString());
    }

    public List getJingquProviderListbyiscenicid(Long iscenicid) {
        String hql = "from Esbscenicareatab where iscenicid=" + iscenicid + " and scenictype='01' and byisuse=1 and isjd=0 and ilevel=1 and iscenicid in " +
                "(select distinct edm.iscenicid from Edmtickettypetab edm where edm.itickettypeid in (select ec.itickettypeid  from Edmcrowdkindpricetab ec where ec.isnet=1 ))";
        return find(hql);
    }

    /**
     * Describe:根据当前用户所属业务类型获取现可售的票得名称,并按照票得名称进行分组
     *
     * @param ibusinessid
     * @return return:PaginationSupport Date:2011-10-9
     * @auth:yangguang
     */
    public List getTicketList(String ibusinessid, Long iscenicid) {
        String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where provider.iscenicid="
                + iscenicid
                + " and  price.ibusinessid="
                + ibusinessid
                + "  and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and systp='1') and price.startdata<='"
                + Tools.getDays()
                + "' and price.enddata>='"
                + Tools.getDays()
                + "' group by ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.isequence";
        return find(hql);
    }

    /**
     * Describe:根据当前用户所属业务类型获取现可售的票得名称,并按照票得名称进行分组 所有的票
     *
     * @param ibusinessid
     * @return return:PaginationSupport Date:2011-10-9
     * @auth:yangguang
     */
    public List getTicketList(String ibusinessid) {
        String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where  price.ibusinessid="
                + ibusinessid
                + "  and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and systp='1') and price.startdata<='"
                + Tools.getDays()
                + "' and price.enddata>='"
                + Tools.getDays()
                + "' group by ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.isequence";
        return find(hql);
    }

    /**
     * Describe:根据当前用户所属业务类型获取现可售的票得名称,并按照票得名称进行分组 不包含 优惠票
     *
     * @param ibusinessid
     * @return return:PaginationSupport Date:2011-10-9
     * @auth:yangguang
     * @deprecated
     */
    public List getTicketList(String ibusinessid, String iscenicid) {
        String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,ticket.szmemo as szmemo)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where provider.iscenicid="
                + iscenicid
                + " and  price.ibusinessid="
                + ibusinessid
                + "  and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and sys.id.pmcd!='0004' and sys.spmcd='01') and price.startdata<='"
                + Tools.getDays()
                + "' and price.enddata>='"
                + Tools.getDays()
                + "' group by ticket.szmemo,ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.bycategorytype desc, ticket.issale desc,ticket.isequence desc";
        System.out.println("----------get ticket-->>>.sql:" + hql);
        return find(hql);
    }

    public List getTicketList(String ibusinessid, String iscenicid, String groupno) {
        System.out.println("groupno" + groupno);
        String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,ticket.szmemo as szmemo)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where provider.iscenicid="
                + iscenicid
                + " and  price.ibusinessid="
                + ibusinessid
                + " and price.note1 = '" + groupno + "' and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and sys.id.pmcd!='0004' and sys.spmcd='01' ) and price.startdata<='"
                + Tools.getDays()
                + "' and price.enddata>='"
                + Tools.getDays()
                + "' group by ticket.szmemo,ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.bycategorytype desc, ticket.issale desc,ticket.isequence desc";
        System.out.println("hql=" + hql);
        return find(hql);
    }

    public List getTicketList(String ibusinessid, String iscenicid, String groupno, String date) {
        System.out.println("groupno" + groupno);
        String hql = "select new map(ticket.sztickettypename as ticketname,ticket.sztickettypecode as sztickettypecode,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,ticket.szmemo as szmemo,ticket.int3 as int3)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where provider.iscenicid="
                + iscenicid
                + " and  price.ibusinessid="
                + ibusinessid
                + " and price.note1 = '" + groupno + "' and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and sys.id.pmcd!='0004' and sys.spmcd='01' ) and price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "' group by ticket.szmemo,ticket.int3,ticket.sztickettypename,ticket.sztickettypecode,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.bycategorytype desc, ticket.issale desc,ticket.isequence desc";
        System.out.println("hql=" + hql);
        return find(hql);
    }

    /**
     * Describe:根据票ID获取票的针对人群种类的价格列表,要使用此方法必须传入有效的产品ID,使用须谨慎
     *
     * @param ticketname
     * @param ibussinessid
     * @return return:List Date:2011-10-9
     * @auth:yangguang
     */
    public List getPriceList(String itickettypeid, String ibusinessid) {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + Tools.getDays()
                + "' and price.enddata>='"
                + Tools.getDays()
                + "'  and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid
                + "  and ticket.byisuse=1 and  humantype.byisuse=1  and  price.isnet=1 and  ticket.itickettypeid=" + itickettypeid + " order by price.icrowdkindpriceid";
        return find(hql);
    }

    public List getPriceListByDate(String itickettypeid, String ibusinessid, String date, String groupno) {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "'  and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid + "  and ticket.byisuse=1 and  humantype.byisuse=1  and  price.isnet=1 and  ticket.itickettypeid=" + itickettypeid + " and price.note1 = '" + groupno + "' order by price.icrowdkindpriceid";
        return find(hql);
    }

    /**
     * 价格分组前的 废弃
     *
     * @deprecated
     */
    public List getPriceListByDate(String itickettypeid, String ibusinessid, String date) {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "'  and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid + "  and ticket.byisuse=1 and  humantype.byisuse=1  and  price.isnet=1 and  ticket.itickettypeid=" + itickettypeid + " order by price.icrowdkindpriceid";
        return find(hql);
    }

    /**
     * Describe:根据日期获取产品价格列表
     *
     * @param itickettypeid
     * @param ibusinessid
     * @param date
     * @return return:List Date:2012-2-6
     * @auth:yangguang
     */
    public List getPriceList(String itickettypeid, String ibusinessid, String date, String groupno) {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid,ticket.iscenicid as iscenicid,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "'  and  price.note1 ='" + groupno + "' and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid + "  and ticket.byisuse=1 and  humantype.byisuse=1 and price.isnet=1  and  ticket.itickettypeid=" + itickettypeid + " order by price.icrowdkindpriceid";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                Edpofferschemetab schema = getScheme(Long.parseLong(map.get("iscenicid").toString()), Long.parseLong(ibusinessid), Long.parseLong(map.get("icrowdkindid").toString()), Long.parseLong(itickettypeid), date);
                if (schema != null) {
                    map.put("szcrowdkindname", map.get("szcrowdkindname").toString() + "(" + schema.getImultiples() + "免" + schema.getIoffernum() + ")");
                    map.put("basenum", schema.getImultiples());
                    map.put("offernum", schema.getIoffernum());
                }
            }
        }
        return list;
    }

    public Edmcrowdkindpricetab getProductPrice(String itickettypeid, String ibusinessid, String date, String icrowkindid, String groupno) throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "' and price.note1 ='" + groupno + "' and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid
                + "  and ticket.byisuse=1 and  humantype.byisuse=1 and price.isnet=1  and  ticket.itickettypeid="
                + itickettypeid
                + " and humantype.icrowdkindid="
                + icrowkindid
                + " order by price.icrowdkindpriceid";
        System.out.println(hql);
        List list = find(hql);
        if (list != null && list.size() > 0) {
            Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
            BeanUtils.populate(price, (Map) list.get(0));
            return price;
        } else {
            return null;
        }
    }

    /**
     * Describe:获取竹筏票票务趟次信息
     *
     * @param itickettypeid 产品ID
     * @param data          乘坐日期
     * @return return:List Date:2011-10-11
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @auth:yangguang
     */
    public List getRafttripInfoList(String itickettypeid, String date, String ziticketid) throws IllegalAccessException, InvocationTargetException {
        List list = getNumberControllData(itickettypeid, date, "02");
        StringBuffer hql = new StringBuffer();
        if (list == null || list.size() < 1) {
            return null;
        } else {
            hql.append("select new map(numcontroll.productcontrolid as productcontrolid, tm.productmanageid as productmanageid,trip.tripname as tripname,tm.starttime as starttime,tm.endtime as endtime,tm.startdata as startdata,tm.enddata as enddata,wharf.ivenueareaname as ivenueareaname,trip.tripid as tripid,");
            if (list == null || list.size() < 1) {
                hql.append("(numcontroll.salablenumber-numcontroll.soldnumber) as salablenumber,");
            } else {
                Productcontrol pc = new Productcontrol();
                BeanUtils.populate(pc, (Map) list.get(0));
                int raftnumber = pc.getSalablenumber().intValue() - pc.getSoldnumber().intValue();
                hql.append("(select (case when (p.salablenumber-p.soldnumber)>" + raftnumber + " then " + raftnumber
                        + "  else (p.salablenumber-p.soldnumber) end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");
            }
            hql.append(" ticket.iscenicid as iscenicid) from Prdtripvenuemanage tm,Edmtickettypetab ticket,Trip trip,Venuearea wharf,Productcontrol numcontroll"
                    + " where tm.itickettypeid = ticket.itickettypeid and numcontroll.bystate=1  and numcontroll.byisuse=1 and trip.iscenicid = tm.iscenicid and tm.tripid = trip.tripid and ticket.itickettypeid ="
                    + ziticketid
                    + " and ticket.byisuse=1 and   trip.byisuse=1  and tm.startdata <= '"
                    + date
                    + "' and tm.enddata >= '"
                    + date
                    + "' and tm.iscenicid=wharf.iscenicid and tm.ivenueareaid=wharf.ivenueareaid and tm.iscenicid=numcontroll.iscenicid and ticket.itickettypeid=numcontroll.itickettypeid and trip.tripid=numcontroll.tripid and tm.ivenueareaid=numcontroll.ivenueareaid and tm.ivenueid=numcontroll.ivenueid and numcontroll.stdata='"
                    + date + "'");
            if (Tools.getDayNumb(date, Tools.getDays()) == 1) {
                hql.append(" and tm.starttime>='" + Tools.getNowTime().substring(0, 5) + "'");
            }
            hql.append(" group by numcontroll.salablenumber,numcontroll.productcontrolid,tm.productmanageid,trip.tripname,tm.starttime,tm.endtime,tm.startdata,tm.enddata,wharf.ivenueareaname,ticket.iscenicid,ticket.itickettypeid,trip.tripid order by tm.starttime");

        }
        return find(hql.toString());
    }

    public List getRafttripInfoList(String itickettypeid, String date, String ziticketid, String usid) throws IllegalAccessException, InvocationTargetException {
        Credit credit = (Credit) get(Credit.class, usid);
        Sysparv5 sys = (Sysparv5) get(Sysparv5.class, new Sysparv5Id("YDJF", "02"));
        List list = getNumberControllData(itickettypeid, date, "02");
        StringBuffer hql = new StringBuffer();
        if (list == null || list.size() < 1) {
            return null;
        } else {
            hql.append("select new map(numcontroll.productcontrolid as productcontrolid, tm.productmanageid as productmanageid,trip.tripname as tripname,tm.starttime as starttime,tm.endtime as endtime,tm.startdata as startdata,tm.enddata as enddata,wharf.ivenueareaname as ivenueareaname,trip.tripid as tripid,");
            if (list == null || list.size() < 1) {
                hql.append("(numcontroll.salablenumber-numcontroll.soldnumber) as salablenumber,");
            } else {
                Productcontrol pc = new Productcontrol();
                BeanUtils.populate(pc, (Map) list.get(0));
                int raftnumber = pc.getSalablenumber().intValue() - pc.getSoldnumber().intValue();
                hql.append("(select (case when (p.salablenumber-p.soldnumber)>" + raftnumber + " then " + raftnumber
                        + "  else (p.salablenumber-p.soldnumber) end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");
            }
            hql.append(" ticket.iscenicid as iscenicid) from Prdtripvenuemanage tm,Edmtickettypetab ticket,Trip trip,Venuearea wharf,Productcontrol numcontroll"
                    + " where tm.itickettypeid = ticket.itickettypeid and numcontroll.bystate=1  and numcontroll.byisuse=1 and trip.iscenicid = tm.iscenicid and tm.tripid = trip.tripid and ticket.itickettypeid ="
                    + ziticketid
                    + " and ticket.byisuse=1 and   trip.byisuse=1  and tm.startdata <= '"
                    + date
                    + "' and tm.enddata >= '"
                    + date
                    + "' and tm.iscenicid=wharf.iscenicid and tm.ivenueareaid=wharf.ivenueareaid and tm.iscenicid=numcontroll.iscenicid and ticket.itickettypeid=numcontroll.itickettypeid and trip.tripid=numcontroll.tripid and tm.ivenueareaid=numcontroll.ivenueareaid and tm.ivenueid=numcontroll.ivenueid and numcontroll.stdata='"
                    + date + "'");
            if (credit.getCreditnumb().intValue() >= Integer.parseInt(sys.getPmvb())) {
                hql.append(" and tm.ishot<=0");
            }
            if (Tools.getDayNumb(date, Tools.getDays()) == 1) {
                hql.append(" and tm.starttime>='" + Tools.getNowTime().substring(0, 5) + "'");
            }
            hql.append(" group by numcontroll.salablenumber,numcontroll.productcontrolid,tm.productmanageid,trip.tripname,tm.starttime,tm.endtime,tm.startdata,tm.enddata,wharf.ivenueareaname,ticket.iscenicid,ticket.itickettypeid,trip.tripid order by tm.starttime");

        }
        return find(hql.toString());
    }

    /**
     * * Describe:根据产品编号、业务种类、人群编号、游览日期 获取产品价格
     * (此方法能不能尽量不要用,要用也要确保价格编号是通过后台查询而获取到的)
     *
     * @param itickettypeid
     * @param tourDate
     * @param icrowdkindpriceid
     * @param ibusinessid
     * @return
     * @author yangguang Date:2011-10-27
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getTicketPrice(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    public double getTicketPrice(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid) throws RuntimeException {
        double price = 0;
        StringBuffer hql = new StringBuffer();
        hql.append("select new map(price.mactualsaleprice as price) from Edmcrowdkindpricetab price where price.itickettypeid=" + itickettypeid + " and price.startdata<='" + tourDate
                + "' and price.enddata>='" + tourDate + "' and price.byisuse=1 and price.icrowdkindpriceid=" + icrowdkindpriceid + " and price.ibusinessid=" + ibusinessid);
        List list = find(hql.toString());
        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            price = (Double) map.get("price");
        } else {
            throw new RuntimeException("=========================>>>>>产品无价格 com.ectrip.ticket.dao.TicketDAO.java method-->getTicketPrice");
        }
        return price;
    }


    public boolean volidationRealname(String itickettypeid, String tourDate, String icrowdkindpriceid, String ibusinessid, String groupno) throws RuntimeException {
        Edmcrowdkindpricetab price = null;
        StringBuffer hql = new StringBuffer();
        hql.append(" from Edmcrowdkindpricetab price where price.itickettypeid=" + itickettypeid + " and price.startdata<='" + tourDate
                + "' and price.enddata>='" + tourDate + "' and price.icrowdkindpriceid=" + icrowdkindpriceid + " and price.ibusinessid=" + ibusinessid + " and note1 in (" + groupno + ") ");
        List list = find(hql.toString());
        System.out.println("====>>sql:" + hql.toString());
        if (list != null && list.size() > 0) {
            price = (Edmcrowdkindpricetab) list.get(0);
        } else {
            throw new RuntimeException("=========================>>>>>产品无价格 com.ectrip.ticket.dao.TicketDAO.java method-->getTicketPrice");
        }
        if (price == null || price.getIpeoplenumrange().intValue() == 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 根据条件查询价格(价格分组)
     *
     * @param itickettypeid
     * @param tourDate
     * @param icrowdkind
     * @param ibusinessid
     * @param groupno
     * @return
     * @throws RuntimeException
     */
    public double getTicketPrice(String itickettypeid, String tourDate, String icrowdkind, String ibusinessid, String groupno) throws RuntimeException {
        double price = 0;
        StringBuffer hql = new StringBuffer();
        hql.append("select new map(price.mactualsaleprice as price) from Edmcrowdkindpricetab price where price.itickettypeid=" + itickettypeid + " and price.startdata<='" + tourDate
                + "' and price.enddata>='" + tourDate + "' and price.byisuse=1 and  price.note1 = '" + groupno + "'   and  price.icrowdkindid=" + icrowdkind + " and price.ibusinessid=" + ibusinessid);
        List list = find(hql.toString());
        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            price = (Double) map.get("price");
        } else {
            throw new RuntimeException("=========================>>>>>产品无价格 com.ectrip.ticket.dao.TicketDAO.java method-->getTicketPrice");
        }
        return price;
    }

    /**
     * * Describe:根据产品编号 日期 判断是否有受限数据
     *
     * @param iticketid
     * @param date
     * @return false:没有 true:有
     * @author yangguang Date:2011-10-29
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#isHaveTicketRestrictedData(java.lang.String,
     * java.lang.String)
     */
    public boolean isHaveTicketRestrictedData(String iticketid, String date) {
        String hql = "select count(productmanageid) from Prdtripvenuemanage as prdm where itickettypeid=" + iticketid + " and startdate<='" + date + "' and enddate>='" + date + "'";
        List list = find(hql);
        if (list == null || list.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * * Describe:根据时间、产品、控制模式获取产品数量控制数据
     *
     * @param iticketid
     * @param date
     * @param controlltype 01：总数控制 02:日控制 03:趟次控制 04:趟次区域控制 05：座位控制
     * @return
     * @author yangguang Date:2011-10-29
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getNumberControllData(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public List getNumberControllData(String iticketid, String date, String controlltype) {
        String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.controltype as controltype) from Productcontrol pc where itickettypeid="
                + iticketid + " and stdata='" + date + "' and byisuse=1 and bystate=1 and pc.controltype='" + controlltype + "'";
        if (controlltype.equals("02")) {
            hql += " and tripid is null";
        }
        return find(hql);
    }

    /**
     * * Describe:根据时间、产品、班次编号获取此班的排班信息
     *
     * @param iticketid
     * @param date
     * @author yangguang Date:2011-10-29
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getNumberControllData(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public Productcontrol getProductcontrol(Long iticketid, String date, Long tripid) {
        String hql = "from Productcontrol  where itickettypeid="
                + iticketid + " and stdata='" + date + "' and byisuse=1 and bystate=1 and pc.controltype='03' and tripid=" + tripid + "";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (Productcontrol) list.get(0);
        } else {
            return null;
        }
    }


    /**
     * Describe:根据时间、产品、获取产品数量控制数据
     *
     * @param iticketid
     * @param tripid
     * @param date
     * @return return:List Date:2011-10-29
     * @auth:yangguang
     */
    public List getNumberControllData(String iticketid, int productcontrolid, String date) {
        String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.iadvanceminute as iadvanceminute,pc.ilagminute as ilagminute,pc.iskeepminute as iskeepminute) from Productcontrol pc where tripid is null and itickettypeid="
                + iticketid + " and stdata='" + date + "' and byisuse=1 and bystate=1  and pc.productcontrolid=" + productcontrolid + "";
        return find(hql);
    }

    /**
     * * Describe:根据时间、产品获取产品数量控制数据
     *
     * @param iticketid
     * @param date
     * @return
     * @author yangguang Date:2011-10-29
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getNumberControllData(java.lang.String,
     * java.lang.String)
     */
    public List getNumberControllData(String iticketid, String date) {
        String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.controltype as controltype) from Productcontrol pc where tripid is null and itickettypeid="
                + iticketid + " and stdata='" + date + "'";
        return find(hql);
    }

    /**
     * Describe:查询子票 只能查出竹筏的请勿乱用
     *
     * @param iticketid
     * @return return:List Date:2011-10-31
     * @auth:yangguang
     */
    public List<OrderPojo> getSonTicketList(String iticketid) {
        String hql = "select new map(ticket.itickettypeid as itickettypeid,ticket.sztickettypename as sonticketname, ticket.issale as sonissale) from Edmtickettypetab ticket where ticket.itickettypeid in (select chai.itickettypeid from Edmticketcomposepricetab chai, Edmcrowdkindpricetab price where price.itickettypeid = "
                + iticketid + " and price.icrowdkindpriceid = chai.id.icrowdkindpriceid group by chai.itickettypeid) and ticket.issale=1";
        return find(hql);
    }

    public Edmcrowdkindpricetab getPrice(Long icrowdkindpriceid) throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(humantype.szcrowdkindname as strcrowdkind,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and  humantype.byisuse=1 and price.icrowdkindpriceid="
                + icrowdkindpriceid + "";
        Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
        BeanUtils.populate(price, (Map) find(hql).get(0));
        return price;
    }

    /**
     * Describe:根据套票的传过来的参数获取子票列表
     *
     * @param sonticket 子票的趟次编号和日期
     * @param prices    数量和价格编号
     * @param iticketid 套票编号
     * @param firstdate 首次进入日期
     * @param numb      套票数量
     * @return return:List<TZorderlist> Date:2011-11-3
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @auth:yangguang
     */
    @Deprecated
    public List<TZorderlist> getNestTicketSplit(Long orderlistid, String orid, Long iscenicid, List<OrderPojo> sonticket, OrderPojo price, String iticketid, String firstdate, int numb)
            throws IllegalAccessException, InvocationTargetException {
        List<TZorderlist> sonticketlist = new ArrayList<TZorderlist>();
        StringBuffer hql = new StringBuffer(
                "select new map(chai.id.iticketcomposepriceid as iticketcomposepriceid, chai.id.icrowdkindpriceid as icrowdkindpriceid ,chai.itickettypeid as itickettypeid,chai.mactualsaleprice as mactualsaleprice,chai.numb as numb) from Edmticketcomposepricetab chai where chai.id.icrowdkindpriceid="
                        + price.getIcrowdkindpriceid() + "");
        int x = 1;
        List privateSonTickets = find(hql.toString());
        for (int i = 0; i < privateSonTickets.size(); i++) {
            Edmticketcomposepricetab chai = new Edmticketcomposepricetab();
            BeanUtils.populate(chai, (Map) privateSonTickets.get(i));
            // TODO 封装子票列表
            TZorderlist zorderlist = new TZorderlist();
            zorderlist.setId(new TZorderlistId(new Long(x++), orderlistid, null, iscenicid));
            zorderlist.setItickettypeid(new Long(iticketid));// 主票ID
            zorderlist.setIztickettypeid(chai.getItickettypeid());// 子票ID

            zorderlist.setIcrowdkindpriceid(chai.getIcrowdkindpriceid());// 票价ID
            zorderlist.setIcrowdkindid(new Long(price.getIcrowdkindid()));// 人群种类
            zorderlist.setZyhamnt(0.0);
            zorderlist.setZyhnumb(new Long(0));
            Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, chai.getItickettypeid());
            if (sonticket != null && sonticket.size() > 0) {
                for (OrderPojo son : sonticket) {
                    if (son.getTicketid().toString().equals(chai.getItickettypeid().toString())) {
                        if (son.getProductcontrolid() != null && !son.getProductcontrolid().equals("")) {
                            Productcontrol control = (Productcontrol) this.get(Productcontrol.class, new Long(son.getProductcontrolid()));
                            Prdtripvenuemanage trip = getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), control.getStdata(), control.getIscenicid().toString(),
                                    control.getItickettypeid().toString());
                            // Prdtripvenuemanage trip = (Prdtripvenuemanage)
                            // this.get(Prdtripvenuemanage.class, new
                            // Long(son.getProductmanageid()));
                            zorderlist.setDtstartdate(son.getTourdate() + " " + trip.getStarttime() + ":00");
                            zorderlist.setDtenddate(son.getTourdate() + " " + trip.getEndtime() + ":00");
                            zorderlist.setTripid(control.getTripid());
                            zorderlist.setIvenueid(control.getIvenueid());
                            zorderlist.setIvenueareaid(control.getIvenueareaid());
                            zorderlist.setIvenueseatsid(new Long(0));
                        } else {
                            zorderlist.setDtstartdate(firstdate + " " + "00:00:00");
                            zorderlist.setDtenddate(Tools.getDate(firstdate, Integer.parseInt(ticket.getValidityday().toString()) - 1) + " " + "23:59:59");
                            zorderlist.setTripid(new Long(0));
                            zorderlist.setIvenueareaid(new Long(0));
                            zorderlist.setIvenueid(new Long(0));
                            zorderlist.setIvenueseatsid(new Long(0));
                        }
                    } else {
                        zorderlist.setDtstartdate(firstdate + " " + "00:00:00");
                        zorderlist.setDtenddate(Tools.getDate(firstdate, Integer.parseInt(ticket.getValidityday().toString()) - 1) + " " + "23:59:59");
                        zorderlist.setTripid(new Long(0));
                        zorderlist.setIvenueareaid(new Long(0));
                        zorderlist.setIvenueid(new Long(0));
                        zorderlist.setIvenueseatsid(new Long(0));
                    }
                }
            } else {
                zorderlist.setDtstartdate(firstdate + " " + "00:00:00");
                zorderlist.setDtenddate(Tools.getDate(firstdate, Integer.parseInt(ticket.getValidityday().toString()) - 1) + " " + "23:59:59");
                zorderlist.setTripid(new Long(0));
                zorderlist.setIvenueareaid(new Long(0));
                zorderlist.setIvenueid(new Long(0));
                zorderlist.setIvenueseatsid(new Long(0));
            }
            zorderlist.setZpric(chai.getMactualsaleprice());
            zorderlist.setZnumb(chai.getNumb() * numb);
            zorderlist.setZamnt(chai.getMactualsaleprice() * zorderlist.getZnumb());
            sonticketlist.add(zorderlist);
        }
        return sonticketlist;
    }


    /**
     * Describe:根据产品、人群种类、旅游日期、业务类型获取价格
     *
     * @param ticketid
     * @param icrowdkindid
     * @param tourdate
     * @param ibusinessid
     * @return return:List Date:2012-2-7
     * @auth:yangguang
     */
    public List getTicketPricelist(Long ticketid, Long icrowdkindid, String tourdate, String ibusinessid) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Edmcrowdkindpricetab price where price.itickettypeid=" + ticketid + " and price.startdata<='" + tourdate + "' and price.enddata>='" + tourdate + "' and price.icrowdkindid="
                + icrowdkindid + " and price.ibusinessid=" + ibusinessid);
        return find(hql.toString());
    }


    public List getTicketPricelist(Long ticketid, Long icrowdkindid, String tourdate, String ibusinessid, String groupno) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Edmcrowdkindpricetab price where price.itickettypeid=" + ticketid + " and price.startdata<='" + tourdate + "' and price.enddata>='" + tourdate + "' and price.icrowdkindid="
                + icrowdkindid + " and price.ibusinessid=" + ibusinessid + " and price.note1 = '" + groupno + "' ");
        return find(hql.toString());
    }

    public List getSonticketlist(Long icrowkondpriceid) {
        String hql = " from Edmticketcomposepricetab where id.icrowdkindpriceid=" + icrowkondpriceid + "";
        return find(hql);
    }

    /**
     * Describe:根据趟次 票 码头 场地 日期 趟次 获取 趟次时间
     *
     * @param tripid
     * @param ivenueid
     * @param ivenueareaid
     * @param tourdate
     * @param iscenicid
     * @param itickettypeid
     * @return return:Prdtripvenuemanage Date:2012-2-16
     * @auth:yangguang
     */
    public Prdtripvenuemanage getTripInfo(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate, String iscenicid, String itickettypeid) {
        String hql = " from Prdtripvenuemanage where tripid=" + tripid + " and iscenicid=" + iscenicid + " and itickettypeid=" + itickettypeid + " and startdata<='" + tourdate + "' and enddata>='"
                + tourdate + "' and ivenueid=" + ivenueid + " and ivenueareaid=" + ivenueareaid + "";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (Prdtripvenuemanage) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * Describe:根据趟次 场地 趟次 游览日期 获取趟次信息
     *
     * @param tripid
     * @param ivenueid
     * @param ivenueareaid
     * @param tourdate
     * @return return:Productcontrol Date:2012-2-8
     * @auth:yangguang
     */
    public Productcontrol getNumberControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate) {
        String hql = " from Productcontrol where tripid=" + tripid + " and ivenueid=" + ivenueid + " and ivenueareaid=" + ivenueareaid + " and stdata='" + tourdate
                + "' and byisuse=1 and bystate=1 and controltype='03'";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            Productcontrol control = (Productcontrol) list.get(0);
            return control;
        } else {
            return null;
        }
    }

    /**
     * Describe:根据趟次 场地 趟次 游览日期 获取趟次信息
     *
     * @param tripid
     * @param ivenueid
     * @param ivenueareaid
     * @param tourdate
     * @return return:Productcontrol Date:2012-2-8
     * @auth:yangguang
     */
    public Productcontrol getTripControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate) {
        String hql = " from Productcontrol where tripid=" + tripid + " and ivenueid=" + ivenueid + " and ivenueareaid=" + ivenueareaid + " and stdata='" + tourdate + "' and controltype='03'";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            Productcontrol control = (Productcontrol) list.get(0);
            return control;
        } else {
            return null;
        }
    }

    public boolean queryCreditNumber(String orid) {
        boolean credit = false;
        List tzlist = find(" from TZorderlist where id.orid='" + orid + "'");
        for (int i = 0; i < tzlist.size(); i++) {
            TZorderlist tz = (TZorderlist) tzlist.get(i);
            if (tz.getTripid().intValue() > 0) {
                List pvlist = find(" from Prdtripvenuemanage where itickettypeid=" + tz.getIztickettypeid() + " and tripid=" + tz.getTripid() + " and startdata<='"
                        + tz.getDtstartdate().substring(0, 10) + "' and enddata>='" + tz.getDtstartdate().substring(0, 10) + "'");
                Prdtripvenuemanage pv = (Prdtripvenuemanage) pvlist.get(0);
                if (pv.getIshot() > 0) {
                    credit = true;
                }
            }
        }
        return credit;
    }

    /**
     * Describe:验证此产品是否是优惠产品
     *
     * @param ticketid
     * @param date
     * @param iscenicid
     * @return return:boolean Date:2012-5-22
     * @auth:yangguang
     */
    public boolean isJfproduct(Long ticketid, String iscenicid) {
        String hql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=-1";
        List list1 = find(hql);
        if (list1 != null && list1.size() > 0) {
            Numjifenset set = (Numjifenset) list1.get(0);
            String hql2 = "from Numjifensetlist where id.nid=" + set.getNid() + "  and isvalid=1 and itickettypeid=" + ticketid + "";
            List list2 = find(hql2);
            if (list2 != null && list2.size() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Numjifenset getNumjifenset(String iscenicid) {
        String hql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=-1";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (Numjifenset) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * *
     * Describe:获取订单参数信息
     *
     * @param ibusinessid
     * @return
     * @author lijingrui
     * Date:2013-4-7
     * @see com.ectrip.ticket.service.iservice.ITicketService#getListordercs(java.lang.String)
     */
    public List getListordercs(String ibusinessid) {
        String sql = " from Ordercs where byisuse=1 and ibusinessid=" + ibusinessid + " order by isequence ";
        List lst = this.find(sql);
        return lst;
    }


    public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid, String startdate) {
        String hql = "from Edpofferschemetab where  icrowdkindid=" + icrowkind + "  and  iscenicid=" + iscenicid + " and ibusinessid=" + ibusinessid + " and itickettypeid=" + itickettypeid + " and startdata<='" + startdate + "' and enddata>='" + startdate + "' and byisuse=1 and ioffertype=0 ";
        System.out.println("-===>>" + hql);
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (Edpofferschemetab) list.get(0);
        } else {
/*		   String sql="from Edpofferschemetab where iscenicid="+iscenicid+" and startdata<='"+startdate+"' and enddata>='"+startdate+"' and byisuse=1 and ioffertype=1 ";
           List lst=this.find(sql);
		   if(lst!=null&&lst.size()>0){
			   return (Edpofferschemetab)lst.get(0);
		   }*/
            return null;
        }
    }

    /**
     * *
     * Describe:获取服务商优惠信息
     *
     * @param iscenicid
     * @param startdate
     * @return
     * @author lijingrui
     * Date:2014-4-1
     * @see com.ectrip.ticket.dao.idao.ITicketDAO#checkEdpschemet(java.lang.Long, java.lang.String)
     */
    public Edpofferschemetab checkEdpschemet(Long iscenicid, String startdate, Long ibusinessid) {
        String sql = "from Edpofferschemetab where iscenicid=" + iscenicid + " and ibusinessid=" + ibusinessid + " and startdata<='" + startdate + "' and enddata>='" + startdate + "' and byisuse=1 and ioffertype=1 ";
        List lst = this.find(sql);
        if (lst != null && lst.size() > 0) {
            return (Edpofferschemetab) lst.get(0);
        }
        return null;
    }

    public Map getSchemeConditions(List<OrderPojo> ticketlist, Edpofferschemetab pscheme, String iscenicid, String date, long ibusinessid, String groupno) throws IllegalAccessException, InvocationTargetException {
        List<Map> priceList = new ArrayList<Map>();
        List<Map> resultList = new ArrayList<Map>();
        Map returnMap = new HashMap();
        boolean isyh = false;//是否符合优惠条件
        String priceid = "";//优惠价格ID
        int yhnum = 0;//优惠数量
        int totalnum = 0;//产品数量
        String type = "0";//服务商优惠类型   0 最高价   1 最低价
        Hotelprovider hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, Long.parseLong(iscenicid));
        if (hotelprovider != null && hotelprovider.getInoteger8() != null && !"".equals(hotelprovider.getInoteger8())) {
            type = hotelprovider.getInoteger8().toString();
        }
        for (int i = 0; i < ticketlist.size(); i++) {
            OrderPojo ticket = ticketlist.get(i);
            if (!ticket.getIscenicid().equals(iscenicid)) {
                continue;
            }
            for (int j = 0; j < ticket.getPrice().size(); j++) {
                OrderPojo price = ticket.getPrice().get(j);
                if (price.getNumb() != null && !price.getNumb().equals("")) {
                    Map map = new HashMap();
                    Edmcrowdkindpricetab eprice = getProductPrice(ticket.getTicketid(), String.valueOf(ibusinessid), date, price.getIcrowdkindid(), groupno);
                    totalnum += Integer.parseInt(price.getNumb());
                    map.put("priceid", eprice.getIcrowdkindpriceid());
                    map.put("price", eprice.getMactualsaleprice());
                    map.put("num", price.getNumb());
                    priceList.add(map);
                } else {
                    ticket.getPrice().remove(price);
                    j -= 1;
                }
            }
        }
        if (totalnum >= pscheme.getImultiples()) {
            isyh = true;
            yhnum = (int) (totalnum / pscheme.getImultiples() * pscheme.getIoffernum());
            if ("1".equals(type)) {
                Collections.sort(priceList, new Comparator<Map>() {
                    public int compare(Map o1, Map o2) {
                        double price1 = Double.parseDouble(o1.get("price").toString());
                        double price2 = Double.parseDouble(o2.get("price").toString());
                        if (price1 > price2) {
                            return 1;
                        } else if (price1 == price2) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });
            }
            if ("0".equals(type)) {
                Collections.sort(priceList, new Comparator<Map>() {
                    public int compare(Map o1, Map o2) {
                        double price1 = Double.parseDouble(o1.get("price").toString());
                        double price2 = Double.parseDouble(o2.get("price").toString());
                        if (price2 > price1) {
                            return 1;
                        } else if (price2 == price1) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });
            }
            for (int x = 0; x < priceList.size(); x++) {
                Map map = priceList.get(x);
                int num = Integer.parseInt(map.get("num").toString());
                if (num > yhnum) {
                    map.put("num", yhnum);
                }
                resultList.add(map);
                yhnum -= num;
                if (yhnum <= 0) {
                    break;
                }
            }
        }
        returnMap.put("isyh", isyh);
        returnMap.put("priceList", resultList);
        return returnMap;
    }

    /**
     * *
     * Describe:获取某用户的价格分组  服务商id可为空
     *
     * @param usid
     * @param iscenicid
     * @return
     * @author lijingrui
     * Date:2014-4-16
     * @see com.ectrip.ticket.service.iservice.ITicketService#searchJgfz(java.lang.String, java.lang.Long)
     */
    public String searchJgfz(String usid, Long iscenicid) {

        //2015-02-05 任先平修改，如果是分销商，就直接返回分销商的价格分组 c.lgtp = '02' and ttlb = '01' and ustp='01'
        Custom custom = (Custom) this.get(Custom.class, usid);
        if (custom != null) {
            //ttlb为99的是分销商
            if (custom.getLgtp().equals("02") && custom.getTtlb().equals("99") && custom.getUstp().equals("01")) {
                System.out.println("分销商：" + custom.getUsid() + " 价格分组：" + custom.getNote2());
                return custom.getNote2();
            }
        }

        StringBuffer result = new StringBuffer();
        StringBuffer hsql = new StringBuffer("select gz.usid as usid,gz.iscenicid as iscenicid,gz.pmcd as pmcd from Jgfz gz,(select connect_by_root usid as susid,connect_by_root corpname as scorpname,connect_by_root bname as bname,connect_by_root ibusinessid as ibusinessid,usid from custom c  START WITH c.lgtp = '02' and ttlb = '01' and ustp='01' connect by prior c.usid = c.susid) c where c.susid=gz.usid and gz.byisuse=1 and c.usid='" + usid + "' ");
        if (iscenicid != null && !iscenicid.equals("")) {
            hsql.append(" and gz.iscenicid=" + iscenicid);
        }
        List<Map> lst = new ArrayList<Map>();
        try {
            lst = this.findBySqlToMap(hsql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lst != null && lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++) {
                Map map = (Map) lst.get(i);
                if (map.get("PMCD") != null) {
                    if (i == 0) {
                        result.append(map.get("PMCD").toString());
                    } else {
                        result.append("," + map.get("PMCD").toString());
                    }

                }
            }
            return result.toString();
        } else {
            result.append("0000");
            return result.toString();
        }

    }

    public Edmcrowdkindpricetab getProductPrice(String itickettypeid, String ibusinessid, String date, Long icrowkindid) throws IllegalAccessException, InvocationTargetException {
        String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
                + date
                + "' and price.enddata>='"
                + date
                + "' and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
                + ibusinessid
                + " and price.icrowdkindid="
                + icrowkindid
                + "  and ticket.byisuse=1 and  humantype.byisuse=1 and price.isnet=1  and  ticket.itickettypeid="
                + itickettypeid
                + " order by price.icrowdkindpriceid";
        List list = find(hql);
        System.out.println(itickettypeid);
        System.out.println(hql);
        if (list != null && list.size() > 0) {
            Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
            BeanUtils.populate(price, (Map) list.get(0));
            return price;
        } else {
            return null;
        }
    }

    public Map getCheckOutNum() {
        String sql = "select new map(nvl(sum(int1),0) as numb) from Ticketchecklist where isalesvoucherid = 0 and " +
                "substr(dtmakedate,1,10)='" + Tools.getDays() + "' ";
        Map ckmap = new HashMap();
        int out = 0;
        List list = find(sql);
        if (list != null && !list.isEmpty()) {
            Map map = (Map) list.get(0);
            out = Integer.parseInt(map.get("numb").toString());
            ckmap.put("out", "出园人数：" + map.get("numb").toString());
        } else {
            ckmap.put("out", "出园人数：0");
        }
        sql = "select new map(nvl(sum(int1),0) as numb) from Ticketchecklist where isalesvoucherid != 0 and " +
                "substr(dtmakedate,1,10) = '" + Tools.getDays() + "' ";

        int in = 0;
        List list1 = find(sql);
        if (list1 != null && !list1.isEmpty()) {
            Map map = (Map) list1.get(0);
            in = Integer.parseInt(map.get("numb").toString());
            ckmap.put("in", "进园人数：" + map.get("numb").toString());
        } else {
            ckmap.put("in", "进园人数：0");
        }
        ckmap.put("you", "在园人数：" + (in - out));
        return ckmap;
    }

	public List getTimeStock(String productId,String palydate) {
		Date date = new Date();
        TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setTimeZone(tZone);
        String time = format.format(date);
        String today = Tools.getDate(Tools.getDays(), 0);
		try {
			//String sql = "select new map(tst.id as id,ett.iscenicid as iscenicid,tst.startDate as startdate,tst.endDate as enddate,tst.startDate||'-'||tst.endDate as date,tst.tatalStock as tatalstock,tst.currStock as currstock,tst.saleStock as salestock,tst.productId as productid,tst.id as id,ett.itickettypeid as itickettypeid) from TimeSharingTicketTab tst,Edmtickettypetab ett where tst.productId=ett.sztickettypecode ";
			String sql = " from TimeSharingTicketTab tst where 1=1 ";
			if(productId!=null && !"".equals(productId)){
				sql+=" and  tst.productId='"+productId+"'"  ;
			}
			//sql+=" and tst.currStock>0";
			//sql+=" and tst.endDate>='"+time+"'";
			sql+=" and tst.dayTime='"+palydate+"'";
			sql+=" order by tst.id";
			//String sql = " select new map(iscenicid as iscenicid)from Edmtickettypetab where 1=1";
			List list = this.find(sql);
			if(list!=null&&list.size()>0){
				
				return list;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
