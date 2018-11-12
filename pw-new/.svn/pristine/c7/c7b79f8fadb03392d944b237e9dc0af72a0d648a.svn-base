package com.ectrip.ticket.checkticket.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DesUtil;
import com.ectrip.base.util.HttpUtil;
import com.ectrip.base.util.ListToResultBean;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.EcServiceApi;
import com.ectrip.ec.model.order.Changecheckticket;
import com.ectrip.ec.model.order.Checkcount;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfdepttab;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.checkticket.service.iservice.IPassCardService;
import com.ectrip.ticket.checkticket.service.iservice.IVerifycheckService;
import com.ectrip.ticket.common.checkUtils.CommonUtil;
import com.ectrip.ticket.common.service.iservice.IReserveorderService;
import com.ectrip.ticket.cyt.client.v1.CYTClient;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.model.CYTDto;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.SysService;
//import com.ectrip.ticket.cyt.service.CYTTask;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Esbaccessstatustab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Esbgardengatetickettab;
import com.ectrip.ticket.model.afcset.Fingerprintrandom;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.centersale.T_zorderlist;
import com.ectrip.ticket.model.centersale.Trealname;
import com.ectrip.ticket.model.order.Seatsaletab;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvoucherdetailstabId;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.StssoldticketattesttabId;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.order.Ticketchecklist;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.reserveorder.ReserveDate;
import com.ectrip.ticket.model.reserveorder.ReserveInfo;
import com.ectrip.ticket.model.reserveorder.Reserveorderinfo;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeefingerprinttab;
import com.ectrip.ticket.model.salesmanager.Opcempployeecardcheck;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.warehouse.Icofflinechecktab;
import com.ectrip.ticket.sale.countList;
import com.ectrip.ticket.sale.service.SaleService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.upload.model.Upfile;

/**
 * 检票所有服务
 *
 * @author lijin 2012-08-07 修改了检票数据 在CHECKTICKETLIST 表中，增加了 note1，note2的数据保存。
 *
 */

@Service
public class CheckService extends GenericService implements ICheckService {
//    static CYTTask task;
	
    private ICheckDao checkDao;
    @Autowired
	public void setCheckDao(ICheckDao checkDao) {
		this.checkDao = checkDao;
		setGenericDao(checkDao);
	}
	@Autowired
    private EcServiceApi ecService;//电商服务API
	@Autowired
	private SysService sysService;//远程调用系统服务API
	@Autowired
	private IVerifycheckService verifycheckService;
	
	@Autowired
	private IPassCardService passCardService;
	
	@Autowired
	private ISaleCenterService saleCenterService;
	
	@Autowired
	private ConstUtils constUtils;

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private CYTClient cytClient;

    public ResultBean getzwcs() {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(3);
        rs.setColumnNames(new String[] { "zwmc", "ywmc", "zwcs" });
        List zwlist = checkDao
                .find("from Sysparv5 v5 where v5.id.pmky='ZWCS' and systp=1");
        Sysparv5 v5 = null;
        if (zwlist == null || zwlist.size() == 0) {
            rs.addRow(new String[] { "0", "0", "0" });// 没有对应闸机
        } else {
            for (int i = 0; i < zwlist.size(); i++) {
                v5 = (Sysparv5) zwlist.get(i);
                rs.addRow(new String[] { v5.getPmva(), v5.getPmvb(),
                        v5.getPmvc() });
            }
        }
        return rs;
    }

    public ResultBean getxpcs() {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(3);
        rs.setColumnNames(new String[] { "xp", "xpmc", "xpmc", "xpcs" });
        List zwlist = checkDao
                .find("from Sysparv5 v5 where v5.id.pmky='XPCS' and systp=1 and isa=1");
        Sysparv5 v5 = null;
        if (zwlist == null || zwlist.size() == 0) {
            rs.addRow(new String[] { "0", "0", "0" });// 没有对应闸机
        } else {
            for (int i = 0; i < zwlist.size(); i++) {
                v5 = (Sysparv5) zwlist.get(i);
                rs.addRow(new String[] { v5.getPmva(), v5.getPmvb(),
                        v5.getPmvc() });// 没有对应闸机
            }
        }
        return rs;
    }

    public ResultBean getMyID(String ip) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Esbaccessequiptab acc = null;
        ip = ip.toUpperCase();
        List cjlist = checkDao
                .find("from Esbaccessequiptab where szipaddress='" + ip + "'");
        if (cjlist == null || cjlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 没有对应闸机
            rs.addRow(new String[] { "-1" });// 没有对应闸机
            rs.addRow(new String[] { "-1" });// 没有对应闸机
            rs.addRow(new String[] { "-1" });// 没有对应闸机
            rs.addRow(new String[] { "0" });// 没有对应闸机
        } else {
            acc = (Esbaccessequiptab) cjlist.get(0);
            rs.addRow(new String[] { acc.getId().getIgardengateid().toString() });
            rs.addRow(new String[] { acc.getId().getIaccessequipid().toString() });
            rs.addRow(new String[] { acc.getId().getIscenicid().toString() });
            EsbgardengatetabId eid = new EsbgardengatetabId();
            eid.setIgardengateid(acc.getId().getIgardengateid());
            eid.setIscenicid(acc.getId().getIscenicid());
            Esbgardengatetab esbgardengatetab = (Esbgardengatetab) checkDao
                    .get(Esbgardengatetab.class, eid);

            if (esbgardengatetab == null) {
                rs.addRow(new String[] { "-1" });// 没有对应闸机
                rs.addRow(new String[] { "0" });// 没有对应闸机
            } else {
                if (esbgardengatetab.getBygardengateindex() == null) {
                    rs.addRow(new String[] { "-1" });// 没有对应闸机
                } else {
                    rs.addRow(new String[] { esbgardengatetab
                            .getBygardengateindex().toString() });
                }
                if (esbgardengatetab.getByiscont() == null) {
                    rs.addRow(new String[] { "0" });// 没有对应闸机
                } else {
                    rs.addRow(new String[] { esbgardengatetab.getByiscont()
                            .toString() });// 没有对应闸机
                }
                if (esbgardengatetab.getSzgardengatename() == null) {
                    rs.addRow(new String[] { "0" });
                } else {
                    rs.addRow(new String[] { esbgardengatetab
                            .getSzgardengatename().toString() });
                }
            }
        }
        return rs;
    }

    public Esbaccessequiptab getEsbaccessequiptab(Long iaccessequipid) {
        Esbaccessequiptab e = null;
        List cjlist = checkDao
                .find("from Esbaccessequiptab e where e.id.iaccessequipid="
                        + iaccessequipid);
        e = (Esbaccessequiptab) cjlist.get(0);
        return e;
    }

    /**
     * * Describe:总体对外检票方法 ticketstr="园门ID(闸机)?,票号,检票时间，服务器编号" 根据票号返回检票规则
     *
     * @see ICheckService#OffLineCheckTicketTrans(String,
     *      String)
     * @param ticketstr
     * @param md5str
     *            准确性校验方法
     * @return
     * @throws Exception
     * @author yangguang Date:2012-10-8
     */
    public ResultBean OffLineCheckTicketTrans(String ticketstr, String md5str,String url)
            throws Exception {
    	if(url==null || url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
        System.out.println("---------------------------->>>>>>>离线检票");
        String[] datastr = ticketstr.split(",");
        String accid = datastr[0];
        String szticketprintno = datastr[1];
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        // 读取闸机信息
        String seq = "";
        // System.out.println(" in accid === " + accid);
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // System.out.println("changeCheckTicket1");
        // 查询售出门票表
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where szticketprintno='"
                        + szticketprintno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid order by s.dtmakedate desc ");
        Stssoldtickettab stss = null;
        stss = new Stssoldtickettab();
        // StssoldtickettabId stssid = new StssoldtickettabId();
        BeanUtils.populate(stss, (Map) sstlist.get(0));
        // 查询园门和票务对应的售出门票子票表
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.iticketstationid="
                        + stss.getIticketstationid()
                        + "   and id.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and id.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(0);
        if (stssz.getTripid() > 0) {
            // 数量控制中查询竹筏票的趟次是否在使用
            Prdtripvenuemanage ptv = new Prdtripvenuemanage();
            /*if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                try {
                    javax.xml.rpc.Service service = null;
                    java.net.URL endpointURL = new java.net.URL("http://"
                            + url
                            + "/services/centersaleService?wsdl");

                    CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                            endpointURL, service);
                    ssl.setMaintainSession(true);
                    com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                            .getPrdtripvenuemanage(stssz.getIztickettypeid(),
                                    stssz.getTripid(), stssz.getDtbegindate()
                                            .substring(0, 10));

                    if (cano.getRowsCount() > 0) {
                        ptv = new Prdtripvenuemanage();
                        ptv.setItickettypeid(stssz.getIztickettypeid());
                        ptv.setTripid(stssz.getTripid());

                        ptv.setStarttime(cano.getResult(0, "STARTTIME"));
                        ptv.setEndtime(cano.getResult(0, "ENDTIME"));
                        ptv.setIadvanceminute(new Long(cano.getResult(0,
                                "IADVANCEMINUTE").toString()));
                        ptv.setIlagminute(new Long(cano.getResult(0,
                                "ILAGMINUTE").toString()));
                        ptv.setIvenueareaid(new Long(cano.getResult(0,
                                "IVENUEAREAID").toString()));
                        ptv.setIvenueid(new Long(cano.getResult(0, "IVENUEID")
                                .toString()));
                    } else {

                        List plist = checkDao
                                .find(" from Prdtripvenuemanage where tripid="
                                        + stssz.getTripid()
                                        + " and itickettypeid="
                                        + stssz.getIztickettypeid()
                                        + " and startdata<='"
                                        + stssz.getDtbegindate().substring(0,
                                        10)
                                        + "' and enddata>='"
                                        + stssz.getDtbegindate().substring(0,
                                        10) + "'");
                        ptv = (Prdtripvenuemanage) plist.get(0);
                    }
                } catch (Exception e1) {
                    List plist = checkDao
                            .find(" from Prdtripvenuemanage where tripid="
                                    + stssz.getTripid() + " and itickettypeid="
                                    + stssz.getIztickettypeid()
                                    + " and startdata<='"
                                    + stssz.getDtbegindate().substring(0, 10)
                                    + "' and enddata>='"
                                    + stssz.getDtbegindate().substring(0, 10)
                                    + "'");
                    ptv = (Prdtripvenuemanage) plist.get(0);
                }
            } else {*/
                System.out.println("读取本地趟次时间");
                List plist = checkDao
                        .find(" from Prdtripvenuemanage where tripid="
                                + stssz.getTripid() + " and itickettypeid="
                                + stssz.getIztickettypeid()
                                + " and startdata<='"
                                + stssz.getDtbegindate().substring(0, 10)
                                + "' and enddata>='"
                                + stssz.getDtbegindate().substring(0, 10) + "'");
                ptv = (Prdtripvenuemanage) plist.get(0);
//            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                // 跟新趟次具体时间
                Date d1 = df.parse(stssz.getDtbegindate().substring(0, 10)
                        + " " + ptv.getStarttime() + ":00");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d1);
                calendar.add(Calendar.MINUTE, -1
                        * ptv.getIadvanceminute().intValue());
                stssz.setDtbegindate(Tools.getDayTimes(calendar));
                Date d2 = df.parse(stssz.getDtbegindate().substring(0, 10)
                        + " " + ptv.getEndtime() + ":00");
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(d2);
                calendar1.add(Calendar.MINUTE, ptv.getIlagminute().intValue());
                stssz.setDtenddate(Tools.getDayTimes(calendar1));
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        System.out.println("xxxxxxxxxx1111");
        // 根据园门/产品/子产品检票设置信息
        List opwwicketsettablist = checkDao
                .find("from Opwwicketsettab where itickettypeid="
                        + stssz.getItickettypeid() + " and izticktypeid="
                        + stssz.getIztickettypeid() + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);

        Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
        String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
        if (lastcheckdate != null && !lastcheckdate.equals("")) {
            // 判断检票时间间隔
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date d1 = df.parse(lastcheckdate);
            calendar.setTime(d1);
            calendar.add(Calendar.SECOND, itimeinterval.intValue());
        }
        System.out.println("xxxxxxxxxx222222");
        String tripname = "";
        if (stssz.getTripid() > 0) {

            seq = checkDao.getZhuFaMaxNo(stssz.getTripid().toString());
            seq = String.valueOf(Long.parseLong(seq));

            Trip t = (Trip) checkDao.get(Trip.class, stssz.getTripid());
            tripname = t.getTripname();
        }
        String daytime = Tools.getDayTimes();
        // 验票方式
        // 检票流水

        // 只验票，更新售出门票
        // 一票一人
        for (int c = 0; c < zsstlist.size(); c++) {
            stssz = (Stssoldticketsubtab) zsstlist.get(c);
            stssz.setIpassedtimes(stssz.getIpassedtimes()
                    + stssz.getMpasstimes());
            stssz.setDtlastcheckdate(daytime);
            stssz.setDtmakedate(daytime);
            stssz.setByisout(new Long(1));
            checkDao.update(stssz);
        }
        Ticketchecklist checkt = new Ticketchecklist();

        List<Map> iserialnumlist = new ArrayList();
        iserialnumlist = checkDao
                .findBySqlToMapnocolsesession("select check_id.nextval  from dual");
        Long checkid = new Long(
                (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());

        checkt.setCheckid(checkid);
        checkt.setIgardengateid(acc.getId().getIgardengateid());
        checkt.setIaccessequipid(acc.getId().getIaccessequipid());
        checkt.setIscenicid(acc.getId().getIscenicid());
        checkt.setIsalesvoucherid(stss.getIsalesvoucherid());
        checkt.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
        checkt.setIticketstationid(stss.getIticketstationid());
        checkt.setSzsoldticketid(stss.getSzsoldticketid());
        checkt.setDtmakedate(daytime);

        if (stssz.getTripid() > 0) {
            checkt.setZfseq(seq);
        }
        checkDao.save(checkt);
        if (stssz.getTripid() > 0) {
            checkDao.updateraftcheck(stssz.getTripid(), stssz.getDtbegindate()
                    .substring(0, 10), new Long(seq),url);
        }
        rs.addRow(new String[] { "3" });// 检票成功,放行
        rs.addRow(new String[] { stss.getSztickettypename() });
        rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
        rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
        rs.addRow(new String[] { stss.getSzcrowdkindname() });
        rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
        rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
        rs.addRow(new String[] { "" });
        rs.addRow(new String[] { "" });
        rs.addRow(new String[] { stss.getSzmemo() });
        rs.addRow(new String[] { seq });
        rs.addRow(new String[] { tripname });
        List cklist = checkDao
                .findBySqlToMap("select g.szgardengatename,s.dtmakedate,s.zfseq  from Ticketchecklist s,Esbgardengatetab g where s.iticketstationid="
                        + stss.getIticketstationid()
                        + " and s.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and s.szsoldticketid="
                        + stss.getSzsoldticketid()
                        + " and s.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and s.igardengateid=g.igardengateid and s.iscenicid=g.iscenicid order by dtmakedate　desc");
        if (cklist.size() >= 9) {
            for (int i = 0; i < 9; i++) {
                Map map1 = (Map) cklist.get(i);
                rs.addRow(new String[] { map1.get("SZGARDENGATENAME") + "  "
                        + map1.get("DTMAKEDATE") });
            }
        } else {
            for (int i = 0; i < cklist.size(); i++) {
                Map map1 = (Map) cklist.get(i);
                rs.addRow(new String[] { map1.get("SZGARDENGATENAME")
                        .toString()
                        + "  "
                        + map1.get("DTMAKEDATE").toString().substring(2) });
            }
            for (int i = 0; i < 9 - cklist.size(); i++) {
                rs.addRow(new String[] { " " });
            }
        }
        // Stssoldtickettab
        return rs;

    }

    /**
     * 总体对外检票方法
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return 2012-7-28 日 李进进行重大修改 ,String ...OtherString 增加了第三个参数
     */
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public ResultBean changeCheckTicket(String accid, String szticketprintno,String checkType,
                                        String... OtherString) throws Exception {
        // System.out.println(" in changeCheckTicket");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        // 读取闸机信息

        String tickettypename = "";// 票名称
        String mactualsaleprice = "";// 单价
        String icrowdkindid = "";// 人群id
        String szcrowdkindname = "";// 人群名称
        String dtbegindate = "";// 开始时间
        String dtenddate = "";// 截至时间
        String bsfilebinary = "";// 指纹信息
        String szimagepath = "";// 相片路径
        String szmemo = "";// 语音路径
        String seq = "";// 竹筏流水
        String tripname = "";// 趟次名称

        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效闸机";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);
            return rs;
        }
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,et.note3 as note3,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign,s.dtmakedate as dtmakedate,s.myzj as myzj,s.name1 as name1) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where s.szticketprintno='"
                        + szticketprintno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid order by s.dtmakedate desc");
        Stssoldtickettab stss = null;

        if (sstlist == null || sstlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效检票设备";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            return rs;
        } else {
            stss = new Stssoldtickettab();
            BeanUtils.populate(stss, (Map) sstlist.get(0));

            List stsudlist=checkDao.find(" from Stssoldticketsubtab st where st.id.szsoldticketid="+stss.getSzsoldticketid()+"  and st.id.isalesvoucherdetailsid="+stss.getIsalesvoucherdetailsid()+" and st.id.isalesvoucherid="+stss.getIsalesvoucherid()+" and st.id.iticketstationid="+stss.getIticketstationid()+" and st.igardengateid="
                    + acc.getId().getIgardengateid()
                    + " and st.isvalid=1 order by st.dtmakedate desc");
            if (stsudlist == null || stsudlist.size() == 0){
                rs.addRow(new String[] { "-1" });// 无效票
                tickettypename = "无效检票设备";
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                return rs;
            }

            Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) this.checkDao
                    .get(Stssalesvoucherdetailstab.class,
                            new StssalesvoucherdetailstabId(stss
                                    .getIsalesvoucherdetailsid(), stss
                                    .getIsalesvoucherid(), stss
                                    .getIticketstationid()));
            Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.checkDao.get(
                    Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
            tickettypename = stss.getSztickettypename();
            mactualsaleprice = stss.getMactualsaleprice().toString();
            icrowdkindid = stss.getIcrowdkindid().toString();
            szcrowdkindname = stss.getSzcrowdkindname();
            if (ep.getNote2() != null && !ep.getNote2().equals("")) {
                szmemo = ep.getNote2();
            } else {
                szmemo = stss.getSzmemo();
            }

            if (stss.getByvalidity().equals("01")) {
                rs.addRow(new String[] { "-2" });// 票已退订或挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss);
                return rs;
            }
            if (stss.getByvalidity().equals("02")) {
                rs.addRow(new String[] { "-99" });// 已挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                return rs;
            }
        }

        Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                Edmtickettypetab.class, stss.getItickettypeid());

        // 查询园门和票务对应的售出门票子票表
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss.getIticketstationid()
                        + "   and  id.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and isvalid not in (-1,0)");
        if (zsstlist == null || zsstlist.size() == 0) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        // 更新有效期
        int yztime = yzchuciyanpiaotime(stss);
        if (yztime == 0) {
            rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        this.updateyouxiaoqi(stss);
        now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
         
        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(0);
        System.out.println("检票事前验证");
        int vi = verifycheckService.Verifyticket(stssz);
        if (vi != 0) {
            rs.addRow(new String[] { String.valueOf(vi) });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        
        System.out.println("检票事前验证2");
        dtbegindate = stssz.getDtbegindate().substring(2);
        dtenddate = stssz.getDtenddate().substring(2);
        List opwwicketsettablist = checkDao
				.find("from Opwwicketsettab where itickettypeid="
						+ stssz.getItickettypeid() + " and izticktypeid="
						+ stssz.getIztickettypeid() + " and igardengateid="
						+ stssz.getIgardengateid() + " and byisuse=1");
		Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);
        String jpnumb1 = "1";
        if (stssz.getMpasstimes() != null) {
            jpnumb1 = stssz.getMpasstimes().toString();
        }
        if (stssz.getTripid() > 0) {

            // 场馆票验证时间
            List comlist = checkDao
                    .find(" from Stscomticketsalesdetailstab where id.isalesvoucherid="
                            + stssz.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz.getId().getIsalesvoucherdetailsid()
                            + " and iztickettypeid="
                            + stssz.getIztickettypeid());
            Stscomticketsalesdetailstab stscom = (Stscomticketsalesdetailstab) comlist
                    .get(0);
            List seatlist = checkDao
                    .find(" from Seatsaletab where id.isalesvoucherid="
                            + stssz.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz.getId().getIsalesvoucherdetailsid()
                            + " and id.szsoldticketid="
                            + stssz.getId().getSzsoldticketid()
                            + " and id.icomticketsalesdetailsid="
                            + stscom.getId().getIcomticketsalesdetailsid());

            Seatsaletab seatsale = (Seatsaletab) seatlist.get(0);
            Long itripprdcontrolid = seatsale.getItripprdcontrolid();
            List triplist = checkDao
                    .find(" from Tripprdcontroldetailtab where itripprdcontrolid="
                            + itripprdcontrolid
                            + " and itripid="
                            + stssz.getTripid());
            Tripprdcontroldetailtab tripdetail = (Tripprdcontroldetailtab) triplist
                    .get(0);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(stssz.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar begcalendar = Calendar.getInstance();
            begcalendar.setTime(d1);
            begcalendar.add(Calendar.MINUTE, -1
                    * tripdetail.getIadvanceminute().intValue());

            Date d2 = df.parse(stssz.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar endcalendar = Calendar.getInstance();
            endcalendar.setTime(d2);
            endcalendar.add(Calendar.MINUTE, tripdetail.getIlagminute().intValue());
            
            if (now.before(begcalendar)) {
                rs.addRow(new String[] { "-4" });// 未到检票开始时间
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

            if (now.after(endcalendar)) {
                rs.addRow(new String[] { "-5" });// 已过有效期
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

            //判断是否有预约通道
            String status = checkReserve(Long.parseLong(accid),szticketprintno);
            if(status != null && !"".equals(status)){
                rs.addRow(new String[] { status });
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }
        } else {
            try {
                // 验证有效时间
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(stssz.getDtbegindate());
                Calendar begcalendar = Calendar.getInstance();
                begcalendar.setTime(d1);
                Date d2 = df.parse(stssz.getDtenddate());
                Calendar endcalendar = Calendar.getInstance();
                endcalendar.setTime(d2);
                if (now.before(begcalendar)) {
                    rs.addRow(new String[] { "-4" });// 未到检票开始时间
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }

                if (now.after(endcalendar)) {
                    rs.addRow(new String[] { "-5" });// 已过有效期
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }
            	
                //判断是否有预约通道
                String status = checkReserve(Long.parseLong(accid),szticketprintno);
                if(status != null && !"".equals(status)){
                    rs.addRow(new String[] { status });
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                            bsfilebinary, szimagepath, szmemo, seq, tripname);
                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }

                // 读取竹筏票seq
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        System.out.println("检票事前验证3");
        if (stssz.getIpasstimes() > 0) {
            if (stssz.getIpasstimes() - stssz.getIpassedtimes() <= 0) {
                //新增人脸识别放行
                if(!StringUtil.isEmpty(checkType) && checkType.equals("04") && opww.getByregfingerprinttype().equals("01"))
                {

                }else
                {
                    rs.addRow(new String[] { "-9" });// 已检过

                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                            bsfilebinary, szimagepath, szmemo, seq, tripname);
                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { "0" });
                    return rs;
                }
            }
        }
        System.out.println("检票事前验证4");
        if (eticket.getByusage() == 0) {
            /*List opwwicketsettablist = checkDao
                    .find("from Opwwicketsettab where itickettypeid="
                            + stssz.getItickettypeid() + " and izticktypeid="
                            + stssz.getIztickettypeid() + " and igardengateid="
                            + acc.getId().getIgardengateid() + " and byisuse=1");
            Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);*/

            Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
            String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
            if (lastcheckdate != null && !lastcheckdate.equals("")) {
                // 判断检票时间间隔
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                Date d1 = df.parse(lastcheckdate);
                calendar.setTime(d1);
                calendar.add(Calendar.SECOND, itimeinterval.intValue());
                if (now.before(calendar)) {
                    System.out.println("changeCheckTicket:-10");
                    rs.addRow(new String[] { "-10" });//
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }
            }

            String daytime = Tools.getDayTimes();
            // 验票方式
            // 检票流水
            System.out.println("opww.getBywicketconsumetype()1="
                    + opww.getBywicketconsumetype());
            if (opww.getBywicketconsumetype().equals("03")) {
                System.out.println("一单一检");

                for (int c = 0; c < zsstlist.size(); c++) {
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    stssz.setIpassedtimes(stssz.getIpassedtimes()
                            + stssz.getMpasstimes());
                    stssz.setDtlastcheckdate(daytime);
                    stssz.setDtmakedate(daytime);
                    stssz.setByisout(new Long(1));
                    this.update(stssz);
                }

                Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                        daytime, 1L, OtherString);
                this.save(checkt);

                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            } else {
                System.out.println("opww.getByregfingerprinttype()1="
                        + opww.getByregfingerprinttype());
                if (opww.getByregfingerprinttype().equals("00")) {

                    // 只验票，更新售出门票
                    // 一票一人

                    for (int c = 0; c < zsstlist.size(); c++) {
                        stssz = (Stssoldticketsubtab) zsstlist.get(c);
                        stssz.setIpassedtimes(stssz.getIpassedtimes()
                                + stssz.getMpasstimes());
                        stssz.setDtlastcheckdate(daytime);
                        stssz.setDtmakedate(daytime);
                        stssz.setByisout(new Long(1));
                        checkDao.update(stssz);
                    }

                    Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                            daytime, 1L, OtherString);

                    this.save(checkt);

                    rs.addRow(new String[] { "3" });// 检票成功,放行
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });

                    return rs;
                } else if (opww.getByregfingerprinttype().equals("01")) {//人脸识别 (指纹)
                    StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                    sttsid.setIsalesvoucherdetailsid(stss
                            .getIsalesvoucherdetailsid());
                    sttsid.setIsalesvoucherid(stss.getIsalesvoucherid());
                    sttsid.setIticketstationid(stss.getIticketstationid());
                    sttsid.setSzsoldticketid(stss.getSzsoldticketid());
                    Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                            .get(Stssoldticketattesttab.class, sttsid);
                    if (stset == null) {
                        if (stss.getIpartitionsign() == 1) {

                            rs.addRow(new String[] { "99" });// 放行卡放行
                            rs = getcanshu11(rs, tickettypename,
                                    mactualsaleprice, icrowdkindid,
                                    szcrowdkindname, dtbegindate, dtenddate,
                                    bsfilebinary, szimagepath, szmemo, seq,
                                    tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        } else {
                            rs.addRow(new String[] { "1" });// 验票登记指纹
                            rs = getcanshu11(rs, tickettypename,
                                    mactualsaleprice, icrowdkindid,
                                    szcrowdkindname, dtbegindate, dtenddate,
                                    bsfilebinary, szimagepath, szmemo, seq,
                                    tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        }
                    } else {
                        bsfilebinary = stset.getBsfilebinary();
                        szimagepath = stset.getSzimagepath();
                        rs.addRow(new String[] { "2" });
                        rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                                icrowdkindid, szcrowdkindname, dtbegindate,
                                dtenddate, bsfilebinary, szimagepath, szmemo,
                                seq, tripname);

                        rs = getchecklist(rs, stss);
                        rs.addRow(new String[] { jpnumb1 });
                        return rs;
                    }
                    // 指纹
                } else if (opww.getByregfingerprinttype().equals("02")) {
                    // 身份证认
                    rs.addRow(new String[] { "6" });// 检票成功,放行
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });

                    return rs;
                } else if (opww.getByregfingerprinttype().equals("03")) {
                    // 二次入园验证指纹
                    StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                    sttsid.setIsalesvoucherdetailsid(stss
                            .getIsalesvoucherdetailsid());
                    sttsid.setIsalesvoucherid(stss.getIsalesvoucherid());
                    sttsid.setIticketstationid(stss.getIticketstationid());
                    sttsid.setSzsoldticketid(stss.getSzsoldticketid());
                    Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                            .get(Stssoldticketattesttab.class, sttsid);

                    if (stset == null) {
                        stssz = (Stssoldticketsubtab) zsstlist.get(0);
                        if (stssz.getIpassedtimes() != 0) {

                            rs.addRow(new String[] { "-9" });// 检票成功,放行
                            rs = getcanshu11(rs, tickettypename,
                                    mactualsaleprice, icrowdkindid,
                                    szcrowdkindname, dtbegindate, dtenddate,
                                    bsfilebinary, szimagepath, szmemo, seq,
                                    tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });

                            return rs;
                        }
                        for (int c = 0; c < zsstlist.size(); c++) {
                            stssz = (Stssoldticketsubtab) zsstlist.get(c);
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }

                        Ticketchecklist checkt = this.getTicketchecklist(acc,
                                stss, daytime, 1L, OtherString);

                        checkDao.save(checkt);

                        rs.addRow(new String[] { "3" });// 检票成功,放行
                        rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                                icrowdkindid, szcrowdkindname, dtbegindate,
                                dtenddate, bsfilebinary, szimagepath, szmemo,
                                seq, tripname);

                        rs = getchecklist(rs, stss);
                        rs.addRow(new String[] { jpnumb1 });

                        return rs;
                    } else {
                        bsfilebinary = stset.getBsfilebinary();
                        szimagepath = stset.getSzimagepath();
                        rs.addRow(new String[] { "2" });// 验票验证指纹
                        rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                                icrowdkindid, szcrowdkindname, dtbegindate,
                                dtenddate, bsfilebinary, szimagepath, szmemo,
                                seq, tripname);

                        rs = getchecklist(rs, stss);
                        rs.addRow(new String[] { jpnumb1 });
                        return rs;
                    }
                } else if (opww.getByregfingerprinttype().equals("04")) {
                    // 指纹识别+身份证认证
                } else if (opww.getByregfingerprinttype().equals("05")) {
                    // 指纹识别+人像抓拍
                    // 判断指纹数据是否存在
                    // Edmtickettypetab
                    // eticket=(Edmtickettypetab)checkDao.get(Edmtickettypetab.class,
                    // stss.getItickettypeid());
                    if (eticket.getByuselimit() == 1) {
                        // 长期有效票
						/*for (int c = 0; c < zsstlist.size(); c++) {
							stssz = (Stssoldticketsubtab) zsstlist.get(c);
							stssz.setIpassedtimes(stssz.getIpassedtimes() + 1);
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
						Ticketchecklist checkt = this.getTicketchecklist(acc,
								stss, daytime, 1L, OtherString);

						// 李进修改结束
						checkDao.save(checkt);

						rs.addRow(new String[] { "3" });
						rs = getcanshu11(rs, tickettypename, mactualsaleprice,
								icrowdkindid, szcrowdkindname, dtbegindate,
								dtenddate, bsfilebinary, szimagepath, szmemo,
								seq, tripname);
						rs = getchecklist(rs, stss);
						rs.addRow(new String[] { jpnumb1 });
						return rs;*/

                        StssoldticketattesttabId sttsid = new
                                StssoldticketattesttabId();
                        sttsid.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
                        sttsid.setIsalesvoucherid(stss.getIsalesvoucherid());
                        sttsid.setIticketstationid(stss.getIticketstationid());
                        sttsid.setSzsoldticketid(stss.getSzsoldticketid());
                        Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao.get(Stssoldticketattesttab.class, sttsid);

                        if (stset == null) {
                            if (stss.getIpartitionsign() == 1) {
                                rs.addRow(new String[]{"99"});// 放行卡放行 rs =
                                getcanshu11(rs, tickettypename, mactualsaleprice, icrowdkindid, szcrowdkindname, dtbegindate, dtenddate, bsfilebinary, szimagepath, szmemo, seq, tripname);

                                rs = getchecklist(rs, stss);
                                rs.addRow(
                                        new String[]{jpnumb1});
                                return rs;
                            } else {
                                rs.addRow(new String[]{"1"});
                                // 验票登记指纹并抓拍人像
                                rs = getcanshu11(rs, tickettypename, mactualsaleprice, icrowdkindid, szcrowdkindname, dtbegindate, dtenddate, bsfilebinary, szimagepath, szmemo, seq, tripname);

                                rs = getchecklist(rs, stss);
                                rs.addRow(new String[]{jpnumb1});
                                return rs;
                            }
                        } else {
                            bsfilebinary = stset.getBsfilebinary();
                            szimagepath = stset.getSzimagepath();
                            rs.addRow(new String[]{"2"});// 验票验证指纹
                            rs = getcanshu11(rs, tickettypename, mactualsaleprice, icrowdkindid, szcrowdkindname, dtbegindate, dtenddate, bsfilebinary, szimagepath, szmemo, seq, tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[]{jpnumb1});
                            return rs;
                        }

                    } else {
                        Sysparv5 v5 = (Sysparv5) checkDao.get(Sysparv5.class,
                                new Sysparv5Id("YZCS", "00"));
                        if (v5.getPmvb().equals("0")) {

                            // 指纹验证全局变量 为0 所有票务不验证指纹 不用登记指纹也不用验证指纹 放行
                            for (int c = 0; c < zsstlist.size(); c++) {
                                stssz = (Stssoldticketsubtab) zsstlist.get(c);
                                stssz.setIpassedtimes(stssz.getIpassedtimes() + 1);
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                            Ticketchecklist checkt = this.getTicketchecklist(
                                    acc, stss, daytime, 1L, OtherString);
                            System.out
                                    .println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                            // 李进修改结束
                            checkDao.save(checkt);

                            rs.addRow(new String[] { "3" });// 检票成功,放行
                            rs = getcanshu11(rs, tickettypename,
                                    mactualsaleprice, icrowdkindid,
                                    szcrowdkindname, dtbegindate, dtenddate,
                                    bsfilebinary, szimagepath, szmemo, seq,
                                    tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFF");
                            return rs;
                        } else {
                            // 判断票务的业务属性
                            if (stss.getIbusinessid() == 1) {
                                // 散客
                                Sysparv5 v51 = (Sysparv5) checkDao.get(
                                        Sysparv5.class, new Sysparv5Id("YZCS",
                                                "01"));
                                if (v51.getPmvb().equals("0")) {
                                    // 为0 散客不用验证指纹，直接检票通过
                                    for (int c = 0; c < zsstlist.size(); c++) {
                                        stssz = (Stssoldticketsubtab) zsstlist
                                                .get(c);
                                        stssz.setIpassedtimes(stssz
                                                .getIpassedtimes() + 1);
                                        stssz.setDtlastcheckdate(daytime);
                                        stssz.setDtmakedate(daytime);
                                        stssz.setByisout(new Long(1));
                                        checkDao.update(stssz);
                                    }
                                    Ticketchecklist checkt = this
                                            .getTicketchecklist(acc, stss,
                                                    daytime, 1L, OtherString);

                                    checkDao.save(checkt);

                                    rs.addRow(new String[] { "3" });// 检票成功,放行
                                    rs = getcanshu11(rs, tickettypename,
                                            mactualsaleprice, icrowdkindid,
                                            szcrowdkindname, dtbegindate,
                                            dtenddate, bsfilebinary,
                                            szimagepath, szmemo, seq, tripname);

                                    rs = getchecklist(rs, stss);
                                    rs.addRow(new String[] { jpnumb1 });
                                    return rs;
                                } else {
                                    // 散客要验证指纹
                                    StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                                    sttsid.setIsalesvoucherdetailsid(stss
                                            .getIsalesvoucherdetailsid());
                                    sttsid.setIsalesvoucherid(stss
                                            .getIsalesvoucherid());
                                    sttsid.setIticketstationid(stss
                                            .getIticketstationid());
                                    sttsid.setSzsoldticketid(stss
                                            .getSzsoldticketid());
                                    Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                                            .get(Stssoldticketattesttab.class,
                                                    sttsid);

                                    if (stset == null) {
                                        // 不存在指纹数据

                                        if (stss.getIpartitionsign() == 1) {

                                            rs.addRow(new String[] { "99" });// 放行卡放行
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        } else {
                                            rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        }
                                    } else {
                                        bsfilebinary = stset.getBsfilebinary();
                                        szimagepath = stset.getSzimagepath();
                                        // 存在指纹数据
                                        rs.addRow(new String[] { "2" });// 验票验证指纹
                                        rs = getcanshu11(rs, tickettypename,
                                                mactualsaleprice, icrowdkindid,
                                                szcrowdkindname, dtbegindate,
                                                dtenddate, bsfilebinary,
                                                szimagepath, szmemo, seq,
                                                tripname);

                                        rs = getchecklist(rs, stss);
                                        rs.addRow(new String[] { jpnumb1 });
                                        return rs;
                                    }
                                }
                            } else if (stss.getIbusinessid() == 2) {
                                // 团队
                                Sysparv5 v52 = (Sysparv5) checkDao.get(
                                        Sysparv5.class, new Sysparv5Id("YZCS",
                                                "02"));
                                if (v52.getPmvb().equals("0")) {
                                    // 全部不验证指纹
                                    for (int c = 0; c < zsstlist.size(); c++) {
                                        stssz = (Stssoldticketsubtab) zsstlist
                                                .get(c);
                                        stssz.setIpassedtimes(stssz
                                                .getIpassedtimes() + 1);
                                        stssz.setDtlastcheckdate(daytime);
                                        stssz.setDtmakedate(daytime);
                                        stssz.setByisout(new Long(1));
                                        checkDao.update(stssz);
                                    }
                                    Ticketchecklist checkt = this
                                            .getTicketchecklist(acc, stss,
                                                    daytime, 1L, OtherString);

                                    // 李进修改结束

                                    checkDao.save(checkt);

                                    rs.addRow(new String[] { "3" });// 检票成功,放行
                                    rs = getcanshu11(rs, tickettypename,
                                            mactualsaleprice, icrowdkindid,
                                            szcrowdkindname, dtbegindate,
                                            dtenddate, bsfilebinary,
                                            szimagepath, szmemo, seq, tripname);

                                    rs = getchecklist(rs, stss);
                                    rs.addRow(new String[] { jpnumb1 });
                                    return rs;
                                } else if (v52.getPmvb().equals("1")) {
                                    // 全部验证指纹

                                    StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                                    sttsid.setIsalesvoucherdetailsid(stss
                                            .getIsalesvoucherdetailsid());
                                    sttsid.setIsalesvoucherid(stss
                                            .getIsalesvoucherid());
                                    sttsid.setIticketstationid(stss
                                            .getIticketstationid());
                                    sttsid.setSzsoldticketid(stss
                                            .getSzsoldticketid());
                                    Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                                            .get(Stssoldticketattesttab.class,
                                                    sttsid);
                                    if (stset == null) {
                                        if (stss.getIpartitionsign() == 1) {

                                            rs.addRow(new String[] { "99" });// 放行卡放行
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        } else {
                                            rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        }
                                    } else {
                                        bsfilebinary = stset.getBsfilebinary();
                                        szimagepath = stset.getSzimagepath();
                                        rs.addRow(new String[] { "2" });// 验票验证指纹
                                        rs = getcanshu11(rs, tickettypename,
                                                mactualsaleprice, icrowdkindid,
                                                szcrowdkindname, dtbegindate,
                                                dtenddate, bsfilebinary,
                                                szimagepath, szmemo, seq,
                                                tripname);

                                        rs = getchecklist(rs, stss);
                                        rs.addRow(new String[] { jpnumb1 });
                                        return rs;
                                    }
                                } else if (v52.getPmvb().equals("2")) {
                                    // 抽样验证指纹

                                    StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                                    sttsid.setIsalesvoucherdetailsid(stss
                                            .getIsalesvoucherdetailsid());
                                    sttsid.setIsalesvoucherid(stss
                                            .getIsalesvoucherid());
                                    sttsid.setIticketstationid(stss
                                            .getIticketstationid());
                                    sttsid.setSzsoldticketid(stss
                                            .getSzsoldticketid());
                                    Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                                            .get(Stssoldticketattesttab.class,
                                                    sttsid);
                                    if (stset == null) {
                                        if (stss.getIpartitionsign() == 1) {

                                            rs.addRow(new String[] { "99" });// 放行卡放行
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        } else {
                                            rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
                                            rs = getcanshu11(rs,
                                                    tickettypename,
                                                    mactualsaleprice,
                                                    icrowdkindid,
                                                    szcrowdkindname,
                                                    dtbegindate, dtenddate,
                                                    bsfilebinary, szimagepath,
                                                    szmemo, seq, tripname);

                                            rs = getchecklist(rs, stss);
                                            rs.addRow(new String[] { jpnumb1 });
                                            return rs;
                                        }
                                    } else {
                                        // 读取随机数

                                        List fglist = checkDao
                                                .find(" from Fingerprintrandom where isalesvoucherid="
                                                        + stss.getIsalesvoucherid()
                                                        + " and iticketstationid="
                                                        + stss.getIticketstationid()
                                                        + " and endtime>='"
                                                        + daytime + "'");
                                        if (fglist == null
                                                || fglist.size() == 0) {
                                            // 删除前面数据

                                            List fgqlist = checkDao
                                                    .find(" from Fingerprintrandom where isalesvoucherid="
                                                            + stss.getIsalesvoucherid()
                                                            + " and iticketstationid="
                                                            + stss.getIticketstationid()
                                                            + " and endtime<'"
                                                            + daytime + "'");
                                            if (fgqlist != null
                                                    && fgqlist.size() > 0) {
                                                for (int i = 0; i < fgqlist
                                                        .size(); i++) {
                                                    Fingerprintrandom finger = (Fingerprintrandom) fgqlist
                                                            .get(i);
                                                    checkDao.delete(finger);
                                                }
                                            }

                                            // 没有随机数
                                            // 读出该订单中的该园门检票总数量

                                            List szsstlist = checkDao
                                                    .findBySqlToMap(" select distinct szsoldticketid from Stssoldticketsubtab where  iticketstationid="
                                                            + stss.getIticketstationid()
                                                            + "   and isalesvoucherid="
                                                            + stss.getIsalesvoucherid()
                                                            + "   and igardengateid="
                                                            + acc.getId()
                                                            .getIgardengateid());

                                            // 编号组成string
                                            String randnumber = "";
                                            Long dtPercentage = Long
                                                    .parseLong(v52.getPmvc());// 团队百分比
                                            // 计算出产生几个随机数
                                            Long numb = szsstlist.size()
                                                    * dtPercentage / 100;
                                            if (numb == 0) {
                                                numb = new Long(1);
                                            }

                                            Random rd = new Random();

                                            for (int i = 1; i <= numb; i++) {
                                                int cnumb = rd
                                                        .nextInt(szsstlist
                                                                .size());

                                                if (cnumb == 0) {
                                                    cnumb = 1;
                                                }
                                                if (cnumb < 0) {
                                                    cnumb = -cnumb;
                                                }
                                                if (i == 0) {
                                                    // 将随机数保存到randnumber
                                                    randnumber = "," + cnumb
                                                            + ",";
                                                } else {
                                                    if (randnumber.indexOf(","
                                                            + cnumb + ",") == -1) {
                                                        // randnumber没有新生成的随机数
                                                        randnumber = randnumber
                                                                + cnumb + ",";
                                                    }
                                                }

                                            }

                                            Fingerprintrandom finger = new Fingerprintrandom();
                                            finger.setIbusinessid(new Long(2));
                                            finger.setIgardengateid(stssz
                                                    .getIgardengateid());
                                            finger.setIsalesvoucherid(stss
                                                    .getIsalesvoucherid());
                                            finger.setIticketstationid(stss
                                                    .getIticketstationid());
                                            finger.setStarttime(daytime);
                                            String jgtime = "30";
                                            if (v52.getPmvd() != null
                                                    && !v52.getPmvd()
                                                    .equals("")) {
                                                jgtime = v52.getPmvd();
                                            }

                                            SimpleDateFormat df = new SimpleDateFormat(
                                                    "yyyy-MM-dd HH:mm:ss");
                                            Calendar calendar = Calendar
                                                    .getInstance();
                                            Date d1 = df.parse(daytime);
                                            calendar.setTime(d1);
                                            calendar.add(
                                                    Calendar.SECOND,
                                                    Integer.parseInt(jgtime) * 60);
                                            finger.setEndtime(Tools
                                                    .getDayTimes(calendar));
                                            if (randnumber.length() > 3900) {
                                                randnumber.substring(0, 3900);
                                            }
                                            finger.setRandomnumber(randnumber);

                                            if (randnumber.indexOf(",1,") == -1) {
                                                // 已检一张票

                                                finger.setJnumber(new Long(1));
                                                checkDao.save(finger);
                                                for (int c = 0; c < zsstlist
                                                        .size(); c++) {
                                                    stssz = (Stssoldticketsubtab) zsstlist
                                                            .get(c);
                                                    stssz.setIpassedtimes(stssz
                                                            .getIpassedtimes() + 1);
                                                    stssz.setDtlastcheckdate(daytime);
                                                    stssz.setDtmakedate(daytime);
                                                    stssz.setByisout(new Long(1));
                                                    checkDao.update(stssz);
                                                }

                                                Ticketchecklist checkt = this
                                                        .getTicketchecklist(
                                                                acc, stss,
                                                                daytime, 1L,
                                                                OtherString);

                                                // 李进修改结束
                                                checkDao.save(checkt);

                                                rs.addRow(new String[] { "3" });// 检票成功,放行
                                                rs = getcanshu11(rs,
                                                        tickettypename,
                                                        mactualsaleprice,
                                                        icrowdkindid,
                                                        szcrowdkindname,
                                                        dtbegindate, dtenddate,
                                                        bsfilebinary,
                                                        szimagepath, szmemo,
                                                        seq, tripname);

                                                rs = getchecklist(rs, stss);
                                                rs.addRow(new String[] { jpnumb1 });
                                                return rs;
                                            } else {
                                                System.out.println("抽样10");
                                                // 需验证指纹，一张票没有检
                                                finger.setJnumber(new Long(0));
                                                checkDao.save(finger);
                                                rs.addRow(new String[] { "2" });
                                                bsfilebinary = stset
                                                        .getBsfilebinary();
                                                szimagepath = stset
                                                        .getSzimagepath();
                                                rs = getcanshu11(rs,
                                                        tickettypename,
                                                        mactualsaleprice,
                                                        icrowdkindid,
                                                        szcrowdkindname,
                                                        dtbegindate, dtenddate,
                                                        bsfilebinary,
                                                        szimagepath, szmemo,
                                                        seq, tripname);

                                                rs = getchecklist(rs, stss);
                                                rs.addRow(new String[] { jpnumb1 });
                                                return rs;
                                            }
                                        } else {
                                            // 有随机数
                                            Fingerprintrandom finger = (Fingerprintrandom) fglist
                                                    .get(0);
                                            String randnumber = finger
                                                    .getRandomnumber();
                                            Long jnumber = finger.getJnumber();
                                            jnumber = jnumber + 1;
                                            int flag = randnumber.indexOf(","
                                                    + jnumber + ",");
                                            if (flag == -1) {
                                                // 不检验
                                                finger.setJnumber(jnumber);
                                                checkDao.update(finger);
                                                for (int c = 0; c < zsstlist
                                                        .size(); c++) {
                                                    stssz = (Stssoldticketsubtab) zsstlist
                                                            .get(c);
                                                    stssz.setIpassedtimes(stssz
                                                            .getIpassedtimes() + 1);
                                                    stssz.setDtlastcheckdate(daytime);
                                                    stssz.setDtmakedate(daytime);
                                                    stssz.setByisout(new Long(1));
                                                    checkDao.update(stssz);
                                                }
                                                Ticketchecklist checkt = this
                                                        .getTicketchecklist(
                                                                acc, stss,
                                                                daytime, 1L,
                                                                OtherString);

                                                // 李进修改结束
                                                checkDao.save(checkt);

                                                rs.addRow(new String[] { "3" });// 检票成功,放行
                                                rs = getcanshu11(rs,
                                                        tickettypename,
                                                        mactualsaleprice,
                                                        icrowdkindid,
                                                        szcrowdkindname,
                                                        dtbegindate, dtenddate,
                                                        bsfilebinary,
                                                        szimagepath, szmemo,
                                                        seq, tripname);

                                                rs = getchecklist(rs, stss);
                                                rs.addRow(new String[] { jpnumb1 });
                                                return rs;
                                            } else {
                                                bsfilebinary = stset
                                                        .getBsfilebinary();
                                                szimagepath = stset
                                                        .getSzimagepath();
                                                rs.addRow(new String[] { "2" });// 验票验证指纹
                                                rs = getcanshu11(rs,
                                                        tickettypename,
                                                        mactualsaleprice,
                                                        icrowdkindid,
                                                        szcrowdkindname,
                                                        dtbegindate, dtenddate,
                                                        bsfilebinary,
                                                        szimagepath, szmemo,
                                                        seq, tripname);

                                                rs = getchecklist(rs, stss);
                                                rs.addRow(new String[] { jpnumb1 });
                                                return rs;
                                            }
                                        }
                                    }
                                }

                            } else {
                                // 接待
                                StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
                                sttsid.setIsalesvoucherdetailsid(stss
                                        .getIsalesvoucherdetailsid());
                                sttsid.setIsalesvoucherid(stss
                                        .getIsalesvoucherid());
                                sttsid.setIticketstationid(stss
                                        .getIticketstationid());
                                sttsid.setSzsoldticketid(stss
                                        .getSzsoldticketid());
                                Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                                        .get(Stssoldticketattesttab.class,
                                                sttsid);
                                if (stset == null) {
                                    if (stss.getIpartitionsign() == 1) {

                                        rs.addRow(new String[] { "99" });// 放行卡放行
                                        rs = getcanshu11(rs, tickettypename,
                                                mactualsaleprice, icrowdkindid,
                                                szcrowdkindname, dtbegindate,
                                                dtenddate, bsfilebinary,
                                                szimagepath, szmemo, seq,
                                                tripname);

                                        rs = getchecklist(rs, stss);
                                        rs.addRow(new String[] { jpnumb1 });
                                        return rs;
                                    } else {
                                        rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
                                        rs = getcanshu11(rs, tickettypename,
                                                mactualsaleprice, icrowdkindid,
                                                szcrowdkindname, dtbegindate,
                                                dtenddate, bsfilebinary,
                                                szimagepath, szmemo, seq,
                                                tripname);

                                        rs = getchecklist(rs, stss);
                                        rs.addRow(new String[] { jpnumb1 });
                                        return rs;
                                    }
                                } else {
                                    bsfilebinary = stset.getBsfilebinary();
                                    szimagepath = stset.getSzimagepath();
                                    rs.addRow(new String[] { "2" });// 验票验证指纹
                                    rs = getcanshu11(rs, tickettypename,
                                            mactualsaleprice, icrowdkindid,
                                            szcrowdkindname, dtbegindate,
                                            dtenddate, bsfilebinary,
                                            szimagepath, szmemo, seq, tripname);

                                    rs = getchecklist(rs, stss);
                                    rs.addRow(new String[] { jpnumb1 });
                                    return rs;
                                }

                            }
                        }
                    }
                }/*else if(opww.getByregfingerprinttype().equals("06"))//新增二次入园人脸识别  lizhaodong 2017-08-29
                {
                    List<Stssoldtickettab> stList = checkDao.find(" from Stssoldtickettab where szticketprintno='"+szticketprintno+"'");
                    List<Stssoldticketsubtab> stSubList = checkDao.find(" from Stssoldticketsubtab s where  s.id.szsoldticketid="+stList.get(0).getId().getSzsoldticketid()+" and s.id.isalesvoucherid="+stList.get(0).getId().getIsalesvoucherid());
                    Long passTimes=-1L;
                    Long passedTimes=0L;
                    if(stSubList!=null && !stSubList.isEmpty())
                    {
                        passTimes=stSubList.get(0).getIpasstimes();
                        passedTimes=stSubList.get(0).getIpassedtimes();
                    }
                    if(passTimes==0 || passTimes>1)
                    {
                        CommonSaleWebService commonSaleService = (CommonSaleWebService) SpringUtil
                                .getBean("commonSaleWebService");
                        ResultBean resultBean=commonSaleService.checkRegisterPhoto(szticketprintno,"","");
                        String resultStr=resultBean.getResult(0, 0);
                        for (int c = 0; c < zsstlist.size(); c++) {
                            stssz = (Stssoldticketsubtab) zsstlist
                                    .get(c);
                            stssz.setIpassedtimes(stssz
                                    .getIpassedtimes() + 1);
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                        Ticketchecklist checkt = this
                                .getTicketchecklist(acc, stss,
                                        daytime, 1L, OtherString);

                        checkDao.save(checkt);
                        if(resultStr.equals("2")) {//正常检票
                            if(passTimes-passedTimes>0 || passTimes==0)
                            {
                                rs.addRow(new String[] { "3" });// 检票成功,放行
                                rs = getcanshu11(rs, tickettypename,
                                        mactualsaleprice, icrowdkindid,
                                        szcrowdkindname, dtbegindate,
                                        dtenddate, bsfilebinary,
                                        szimagepath, szmemo, seq, tripname);

                                rs = getchecklist(rs, stss);
                                rs.addRow(new String[] { jpnumb1 });
                                return rs;
                            }else
                            {

                            }
                        }else
                        {
                            rs.addRow(new String[] { "1" });// 人脸识别需注册人脸
                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { "1" });
                            return rs;
                        }
                    }
                }*/
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { "1" });
                return rs;
            }
        } else if (eticket.getByusage() == 1) {
            // 一票多人销售
            /*List opwwicketsettablist = checkDao
                    .find("from Opwwicketsettab where itickettypeid="
                            + stssz.getItickettypeid() + " and izticktypeid="
                            + stssz.getIztickettypeid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);*/
            Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
            String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
            if (lastcheckdate != null && !lastcheckdate.equals("")) {
                // 判断检票时间间隔
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                Date d1 = df.parse(lastcheckdate);
                calendar.setTime(d1);
                calendar.add(Calendar.SECOND, itimeinterval.intValue());

                if (now.before(calendar)) {
                    // 当前时间在间隔时间内
                    if (stssz.getMsingletimes().longValue() == stssz
                            .getMsingledtimes().longValue()) {
                        // 单次 票数一检完
                        rs.addRow(new String[] { "-10" });//
                        rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                                icrowdkindid, szcrowdkindname, dtbegindate,
                                dtenddate, bsfilebinary, szimagepath, szmemo,
                                seq, tripname);

                        rs = getchecklist(rs, stss);
                        return rs;
                    }
                }
            }

            String daytime = Tools.getDayTimes();
            if (opww.getBywicketconsumetype().equals("01")) {
                // 一检一人
                // stssz.getIpasstimes() > 0表示有次数限制 stssz.getIpasstimes() = 0
                // 不限制次数

                // 验票方式
                // 检票流水
                // if (opww.getByregfingerprinttype().equals("00")) {
                // 只检票
                for (int c = 0; c < zsstlist.size(); c++) {
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    if (stssz.getIpasstimes() > 0) {
                        // 限定次数的检票 不需要判断时间 间隔
                        if (stssz.getMpasstimes() == null) {
                            stssz.setMpasstimes(1L);
                        }
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内

                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMsingledtimes()
                                        + stssz.getMpasstimes());
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            } else {
                                // 间隔时间外
                                stssz = (Stssoldticketsubtab) zsstlist.get(c);
                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMpasstimes());
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                        } else {
                            // 第一次检票
                            stssz = (Stssoldticketsubtab) zsstlist.get(c);
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    } else if (stssz.getIpasstimes() == 0) {
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内

                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMsingledtimes()
                                        + stssz.getMpasstimes());
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            } else {
                                // 间隔时间外
                                stssz = (Stssoldticketsubtab) zsstlist.get(c);
                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMpasstimes());
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                        } else {
                            // 第一次检票
                            stssz = (Stssoldticketsubtab) zsstlist.get(c);
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    }
                }
                Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                        daytime, stssz.getMpasstimes(), OtherString);

                checkDao.save(checkt);

                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { "1" });
                return rs;
                // }else{
                // 其他检票方式
                // }
            } else if (opww.getBywicketconsumetype().equals("02")) {
                // 一检多人
                // if (opww.getByregfingerprinttype().equals("00")) {
                // 只检票
                Long jpnumb = new Long(0);
                for (int c = 0; c < zsstlist.size(); c++) {
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    if (stssz.getMpasstimes() == null) {
                        stssz.setMpasstimes(1L);
                    }
                    Long synumb = stssz.getMsingletimes()
                            - stssz.getMsingledtimes();
                    if (stssz.getIpasstimes() > 0) {
                        // 限定次数的检票 不需要判断时间 间隔
                        // 剩余可检数量
                        synumb = stssz.getIpasstimes()
                                - stssz.getIpassedtimes();
                        if (synumb > stssz.getMpasstimes()) {
                            // 剩余可检数>每次检票数
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        } else {
                            jpnumb = synumb;
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + synumb);
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + synumb);
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    } else if (stssz.getIpasstimes() == 0) {
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内
                                if (synumb > stssz.getMpasstimes()) {
                                    jpnumb = stssz.getMpasstimes();
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                } else {
                                    jpnumb = synumb;
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes() + synumb);
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes() + synumb);
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                }
                            } else {
                                // 间隔时间外
                                jpnumb = stssz.getMpasstimes();
                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMpasstimes());
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                        } else {
                            // 第一次检票
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    }
                }
                Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                        daytime, jpnumb, OtherString);

                checkDao.save(checkt);

                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb.toString() });
                return rs;

                // }else{
                // 其他检票方式
                // }
            } else if (opww.getBywicketconsumetype().equals("03")) {
                System.out.println("一单一检");
                // 一检多人
                // if (opww.getByregfingerprinttype().equals("00")) {
                // 只检票
                Long jpnumb = new Long(0);
                for (int c = 0; c < zsstlist.size(); c++) {
                    if (stssz.getMpasstimes() == null) {
                        stssz.setMpasstimes(1L);
                    }
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    Long synumb = stssz.getMsingletimes()
                            - stssz.getMsingledtimes();
                    if (stssz.getIpasstimes() > 0) {
                        // 限定次数的检票 不需要判断时间 间隔
                        // 剩余可检数量
                        if (synumb > stssz.getMpasstimes()) {
                            // 剩余可检数>每次检票数
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        } else {
                            jpnumb = synumb;
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + synumb);
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + synumb);
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    } else if (stssz.getIpasstimes() == 0) {
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内
                                if (synumb > stssz.getMpasstimes()) {
                                    jpnumb = stssz.getMpasstimes();
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                } else {
                                    jpnumb = synumb;
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes() + synumb);
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes() + synumb);
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                }
                            } else {
                                // 间隔时间外
                                jpnumb = stssz.getMpasstimes();
                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMpasstimes());
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                        } else {
                            // 第一次检票
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    }
                }
                Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                        daytime, jpnumb, OtherString);

                checkDao.save(checkt);

                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb.toString() });
                return rs;

                // }else{
                // 其他检票方式
                // }

            } else if (opww.getBywicketconsumetype().equals("04")) {
                // 一检多人可暂停
                // if (opww.getByregfingerprinttype().equals("00")) {
                // 只检票
                Long jpnumb = new Long(0);
                for (int c = 0; c < zsstlist.size(); c++) {
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    Long synumb = stssz.getMsingletimes()
                            - stssz.getMsingledtimes();
                    if (stssz.getIpasstimes() > 0) {
                        // 限定次数的检票 不需要判断时间 间隔
                        // 剩余可检数量
                        synumb = stssz.getIpasstimes()
                                - stssz.getIpassedtimes();
                        if (synumb > stssz.getMpasstimes()) {
                            // 剩余可检数>每次检票数
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        } else {
                            jpnumb = synumb;
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + synumb);
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + synumb);
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    } else if (stssz.getIpasstimes() == 0) {
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内
                                if (synumb > stssz.getMpasstimes()) {
                                    jpnumb = stssz.getMpasstimes();
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes()
                                            + stssz.getMpasstimes());
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                } else {
                                    jpnumb = synumb;
                                    stssz.setIpassedtimes(stssz
                                            .getIpassedtimes() + synumb);
                                    stssz.setMsingledtimes(stssz
                                            .getMsingledtimes() + synumb);
                                    stssz.setDtmakedate(daytime);
                                    stssz.setByisout(new Long(1));
                                    checkDao.update(stssz);
                                }
                            } else {
                                // 间隔时间外
                                jpnumb = stssz.getMpasstimes();
                                stssz.setIpassedtimes(stssz.getIpassedtimes()
                                        + stssz.getMpasstimes());
                                stssz.setMsingledtimes(stssz.getMpasstimes());
                                stssz.setDtlastcheckdate(daytime);
                                stssz.setDtmakedate(daytime);
                                stssz.setByisout(new Long(1));
                                checkDao.update(stssz);
                            }
                        } else {
                            // 第一次检票
                            jpnumb = stssz.getMpasstimes();
                            stssz.setIpassedtimes(stssz.getIpassedtimes()
                                    + stssz.getMpasstimes());
                            stssz.setMsingledtimes(stssz.getMsingledtimes()
                                    + stssz.getMpasstimes());
                            stssz.setDtlastcheckdate(daytime);
                            stssz.setDtmakedate(daytime);
                            stssz.setByisout(new Long(1));
                            checkDao.update(stssz);
                        }
                    }
                }
                Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
                        daytime, jpnumb, OtherString);

                checkDao.save(checkt);

                rs.addRow(new String[] { "4" });// 检票成功,放行
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb.toString() });

                return rs;

                // }else{
                // 其他检票方式
                // }
            }
        }
        System.out.println("rs" + rs.getResult(0, 0));
        return rs;
    }


    /**
     * 新增方法调用
     * @param accid
     * @return
     * @throws Exception
     */
    public ResultBean changeCheckTicket(String accid) throws Exception {
        return changeCheckTicket(accid,"","","");
    }


    /**
     * 新增方法调用
     * @param accid
     * @param szticketprintno
     * @return
     * @throws Exception
     */
    public ResultBean changeCheckTicket(String accid, String szticketprintno) throws Exception {
        return changeCheckTicket(accid,szticketprintno,"","");
    }

    /**
     *
     * Describe:
     * @author:chenxinhao
     * @param accid  闸机ID
     * @param szticketprintno 票号
     * @return
     * return:String
     * Date:2015-11-19
     */
    public String checkReserve(Long accid,String szticketprintno) throws ParseException {
        //判断是否有预约通道
        List channels = checkDao.find("from Reservechannel where channelid ="+accid);
        if(channels != null && !channels.isEmpty()){
            IReserveorderService reserveorderService = (IReserveorderService) SpringUtil.getBean("reserveorderService");
            //查看票号预约数据
            Reserveorderinfo reserveorderinfo = reserveorderService.selectOneByOrid(szticketprintno);
            if(reserveorderinfo == null){
                //查看订单预约时间
                List slist = checkDao.find("select new map(s.szsalesvoucherno as orid) from Stssalesvouchertab s,Stssoldtickettab st where " +
                        "s.id.isalesvoucherid = st.id.isalesvoucherid and s.id.iticketstationid = st.id.iticketstationid " +
                        "and st.szticketprintno = '"+szticketprintno+"' ");
                if(slist != null && !slist.isEmpty()){
                    Map map = (Map) slist.get(0);
                    String orid = map.get("orid").toString();
                    reserveorderinfo = reserveorderService.selectOneByOrid(orid);
                }
            }
            if(reserveorderinfo != null){
                String now = Tools.getDayTimes();

                String dataJson = reserveorderinfo.getDatajson();
                ReserveInfo reserveInfo = JSON.parseObject(dataJson, ReserveInfo.class);
                List<ReserveDate> reserveDates = reserveInfo.getDates();
                if(reserveDates != null && !reserveDates.isEmpty()){
                    for(ReserveDate reserveDate : reserveDates){
                        //预约时间判断
                        String rzti = reserveDate.getBeginDate()+" "+reserveDate.getBeginTime();
                        String ldti = reserveDate.getBeginDate()+" "+reserveDate.getEndTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        if(sdf.parse(now).before(sdf.parse(rzti))){
                            return "-4";
                        }
                        if(sdf.parse(now).after(sdf.parse(ldti))){
                            return "-3";
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * 向服务上传指纹数据
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return
     */
    public int changeticketPassIdintput(String accid, String ticketno,
                                        String md5str) throws Exception {

        List sstlist = checkDao
                .find("from Stssoldtickettab where ( szticketprintno='"
                        + ticketno + "' or myzj='" + ticketno
                        + "') and byvalidity='00' order by dtmakedate desc");
        Stssoldtickettab stss = (Stssoldtickettab) sstlist.get(0);
        String daytime = Tools.getDayTimes();
        List fglist = checkDao
                .find(" from Fingerprintrandom where isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and iticketstationid="
                        + stss.getId().getIticketstationid() + " and endtime>'"
                        + daytime + "'");
        if (fglist.size() > 0) {
            Fingerprintrandom f = (Fingerprintrandom) fglist.get(0);
            f.setJnumber(f.getJnumber() + 1);
            checkDao.update(f);
        }

        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        Long igardengateid = acc.getId().getIgardengateid();// 园门ID
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where Isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and iticketstationid="
                        + stss.getId().getIticketstationid()
                        + " and isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and szsoldticketid="
                        + stss.getId().getSzsoldticketid()
                        + " and igardengateid=" + igardengateid);

        // 更新随机表中的数据

        for (int i = 0; i < zsstlist.size(); i++) {
            Stssoldticketsubtab st = (Stssoldticketsubtab) zsstlist.get(i);
            st.setIpassedtimes(st.getIpassedtimes() + 1);
            st.setDtlastcheckdate(daytime);
            st.setByisout(new Long(1));
            st.setDtmakedate(daytime);
            checkDao.update(st);
        }

        Ticketchecklist checkt;
        String[] ortherstring = new String[] {};
        try {
            stss.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
            stss.setIsalesvoucherdetailsid(stss.getId()
                    .getIsalesvoucherdetailsid());
            stss.setIticketstationid(stss.getId().getIticketstationid());
            stss.setSzsoldticketid(stss.getId().getSzsoldticketid());
            checkt = this.getTicketchecklist(acc, stss, daytime, 1L,
                    ortherstring);
            checkDao.save(checkt);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * *上传指纹,更新闸机数据 Describe:
     *
     * @see ICheckService#ticketZwintput(String,
     *      String, String, String,
     *      String, String)
     * @param accid
     *            闸机ID
     * @param ticketno
     *            票号
     * @param ziwenno
     *            指纹数据
     * @param szidcard
     *            身份证号码
     * @param szimagepath图片路径
     * @param md5str
     * @return
     * @throws Exception
     * @author yuanchengjun Date:2011-11-8
     */

    public int changeticketZwintput(String accid, String ticketno,
                                    String ziwenno, String szidcard, String szimagepath, String md5str) {
        // 根据票号找到对应的出票数据

        String daytime = Tools.getDayTimes();
        List sstlist = checkDao
                .find("from Stssoldtickettab where  ( szticketprintno='"
                        + ticketno + "' or myzj='" + ticketno
                        + "' ) and byvalidity='00' order by dtmakedate desc");
        Stssoldtickettab stss = (Stssoldtickettab) sstlist.get(0);
        // 根据出票数据保存有对应的身份验证信息
        StssoldticketattesttabId id = new StssoldticketattesttabId();
        id.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
        id.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
        id.setIticketstationid(stss.getId().getIticketstationid());
        id.setSzsoldticketid(stss.getId().getSzsoldticketid());
        List stsist = checkDao
                .find("from Stssoldticketattesttab where id.isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + "and id.isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and id.iticketstationid="
                        + stss.getId().getIticketstationid()
                        + " and id.szsoldticketid="
                        + stss.getId().getSzsoldticketid());

        if (stsist == null || stsist.size() == 0) {

            Stssoldticketattesttab sts = new Stssoldticketattesttab();
            sts.setId(id);
            sts.setBsfilebinary(ziwenno);
            sts.setByfactregtype("05");
            sts.setSzidcard(szidcard);
            sts.setSzimagepath(szimagepath);
            sts.setIpartitionsign(new Long(0));
            sts.setDtmakedate(daytime);
            sts.setByisout(new Long(1));
            checkDao.save(sts);
        } else {
            Stssoldticketattesttab sts = (Stssoldticketattesttab) stsist.get(0);
            sts.setBsfilebinary(ziwenno);
            sts.setByfactregtype("05");
            sts.setSzidcard(szidcard);
            sts.setSzimagepath(szimagepath);
            sts.setIpartitionsign(new Long(0));
            sts.setDtmakedate(daytime);
            sts.setByisout(new Long(1));
            checkDao.update(sts);
        }
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where Isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and iticketstationid="
                        + stss.getId().getIticketstationid()
                        + " and isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and szsoldticketid="
                        + stss.getId().getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid());

        for (int i = 0; i < zsstlist.size(); i++) {
            Stssoldticketsubtab st = (Stssoldticketsubtab) zsstlist.get(i);
            st.setIpassedtimes(st.getIpassedtimes() + 1);
            st.setDtlastcheckdate(daytime);
            st.setDtmakedate(daytime);
            st.setByisout(new Long(1));
            checkDao.update(st);
        }

        // 检票流水

        Ticketchecklist checkt;
        String[] ortherstring = new String[] {};
        stss.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
        stss.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
        stss.setIticketstationid(stss.getId().getIticketstationid());
        stss.setSzsoldticketid(stss.getId().getSzsoldticketid());
        try {
            checkt = this.getTicketchecklist(acc, stss, daytime, 1L,
                    ortherstring);
            checkDao.save(checkt);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 1;
    }

    public ResultBean changeCheckDaoyou(String accid, String carno,String newUrl)
            throws Exception {
    	
    	//判断是否有传入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
    	
    	
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // 查询是否导游
        System.out.println(" from Opcemployeecardtab where upper(icardno)='"
                + carno.toUpperCase()
                + "' and  byisdaoyou=1 and byticketstate=0");
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase()
                        + "' and  byisdaoyou=1 and byticketstate=0");

        if (dlist == null || dlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效证件
            for (int i = 0; i < 23; i++) {
                rs.addRow(new String[] { "" });
            }

            return rs;
        } else {

            Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
            Custom c = (Custom) checkDao.get(Custom.class, op.getUsid());
            System.out.println("status=" + c.getStatus());
            if (!c.getStatus().equals("01")) {
                rs.addRow(new String[] { "-1" });// 无效证件
                for (int i = 0; i < 23; i++) {
                    rs.addRow(new String[] { "" });
                }

                return rs;
            }
            // 根据园门ID和卡证ID读取可进园门数据
            // 导游
            List dzlist = checkDao
                    .find(" from Opcemployeecardsubtab where iemployeecardid="
                            + op.getIemployeecardid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            System.out
                    .println(" from Opcemployeecardsubtab where iemployeecardid="
                            + op.getIemployeecardid()
                            + " and igardengateid="
                            + acc.getId().getIgardengateid());
            if (dzlist == null || dzlist.size() == 0) {
                rs.addRow(new String[] { "-2" });// 该导游不能通过该园门
                for (int i = 0; i < 23; i++) {
                    rs.addRow(new String[] { "" });
                }
            } else {
                System.out.println("11111111111111111");
                List zwlist = checkDao
                        .find(" from Opcemployeefingerprinttab where iemployeecardid="
                                + op.getIemployeecardid() + " and byisuse=1");
                if (zwlist == null || zwlist.size() == 0) {
                    rs.addRow(new String[] { "1" });// 登记指纹
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                } else {
                    rs.addRow(new String[] { "2" });// 验证指纹
                    for (int i = 0; i < zwlist.size(); i++) {
                        Opcemployeefingerprinttab ops = (Opcemployeefingerprinttab) zwlist
                                .get(i);
                        rs.addRow(new String[] { ops.getSzfeatures() });// 指纹特征值
                    }
                    for (int i = 0; i < 10 - zwlist.size(); i++) {
                        rs.addRow(new String[] { "" });
                    }
                }

            }

            if (c.getInote1() != null && c.getInote1() > 0) {
                Upfile u = (Upfile) checkDao.get(Upfile.class, c.getInote1());
                String uppath = "http://" + newUrl
                        + "/" + u.getUpadder() + u.getUpfilename();
                rs.addRow(new String[] { uppath });
            } else {
                rs.addRow(new String[] { "" });
            }
        }

        rs.addRow(new String[] { "21" });// 导游
        return rs;
    }

    public ResultBean changeDaoyouzwinput(String accid, String carno,
                                          String ziwenno) throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        String today = Tools.getDayTimes();
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase() + "'");
        Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
        // 保存指纹信息
        Opcemployeefingerprinttab opc = new Opcemployeefingerprinttab();
        opc.setIemployeecardid(op.getIemployeecardid());
        opc.setUsid(op.getUsid());
        opc.setIfingersid(new Long(1));
        opc.setSzfeatures(ziwenno);
        opc.setByisuse(new Long(1));
        opc.setIversion(new Long(0));
        Long iemployeefingerprintid = checkDao.getMaxPk(
                "iemployeefingerprintid", "Opcemployeefingerprinttab");
        opc.setIemployeefingerprintid(iemployeefingerprintid + 1);
        opc.setDtmakedate(today);
        checkDao.save(opc);
        // 保存记录
        Opcempployeecardcheck opce = new Opcempployeecardcheck();
        opce.setIaccessequipid(acc.getId().getIaccessequipid());
        opce.setIgardengateid(acc.getId().getIgardengateid());
        opce.setIemployeecardid(op.getIemployeecardid());
        opce.setIcardno(carno);
        opce.setUsid(op.getUsid());

        opce.setSzdtime(today);
        opce.setIyear(new Long(today.substring(0, 4)));
        opce.setImonth(new Long(today.substring(5, 7)));
        opce.setIday(new Long(today.substring(8, 10)));
        opce.setDtmakedate(today);
        Long checkid = checkDao.getMaxPk("checkid", "Opcempployeecardcheck");
        opce.setCheckid(checkid + 1);
        checkDao.save(opce);

        List dzlist = checkDao
                .find(" from Opcemployeecardsubtab where iemployeecardid="
                        + op.getIemployeecardid() + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) dzlist.get(0);
        ops.setIpassedtimes(ops.getIpassedtimes() + ops.getMsingletimes());
        ops.setDtmakedate(today);
        ops.setDtlastcheckdate(today);
        checkDao.update(ops);

        rs.addRow(new String[] { "1" });
        return rs;
    }

    public ResultBean changeEmployeezwinput(String accid, String carno,
                                            String ziwenno) throws Exception {
        System.out.println("zwinput1");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        String today = Tools.getDayTimes();
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase() + "'");

        Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
        // 保存指纹信息
        System.out.println("zwinput3");
        Opcemployeefingerprinttab opc = new Opcemployeefingerprinttab();
        opc.setIemployeecardid(op.getIemployeecardid());
        opc.setIemployeeid(op.getIemployeeid());
        opc.setIfingersid(new Long(1));
        opc.setSzfeatures(ziwenno);
        opc.setByisuse(new Long(1));
        opc.setIversion(new Long(0));
        Long iemployeefingerprintid = checkDao.getMaxPk(
                "iemployeefingerprintid", "Opcemployeefingerprinttab");
        opc.setIemployeefingerprintid(iemployeefingerprintid + 1);
        opc.setDtmakedate(today);
        checkDao.save(opc);
        // 保存记录
        System.out.println("zwinput4");
        Opcempployeecardcheck opce = new Opcempployeecardcheck();
        opce.setIaccessequipid(acc.getId().getIaccessequipid());
        opce.setIgardengateid(acc.getId().getIgardengateid());
        opce.setIemployeecardid(op.getIemployeecardid());
        opce.setIemployeeid(op.getIemployeeid());
        opce.setIcardno(carno);
        opce.setUsid(op.getUsid());

        opce.setSzdtime(today);
        opce.setIyear(new Long(today.substring(0, 4)));
        opce.setImonth(new Long(today.substring(5, 7)));
        opce.setIday(new Long(today.substring(8, 10)));
        opce.setDtmakedate(today);
        Long checkid = checkDao.getMaxPk("checkid", "Opcempployeecardcheck");
        opce.setCheckid(checkid + 1);
        checkDao.save(opce);
        System.out.println("zwinput5");
        List dzlist = checkDao
                .find(" from Opcemployeecardsubtab where iemployeecardid="
                        + op.getIemployeecardid() + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) dzlist.get(0);
        ops.setIpassedtimes(ops.getIpassedtimes() + ops.getMsingletimes());
        ops.setDtmakedate(today);
        ops.setDtlastcheckdate(today);
        checkDao.update(ops);
        System.out.println("zwinput6");
        rs.addRow(new String[] { "1" });
        return rs;
    }

    public ResultBean changeDaoyoupass(String accid, String carno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase() + "'");
        Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
        // 保存记录
        Opcempployeecardcheck opce = new Opcempployeecardcheck();
        opce.setIaccessequipid(acc.getId().getIaccessequipid());
        opce.setIgardengateid(acc.getId().getIgardengateid());
        opce.setIemployeecardid(op.getIemployeecardid());
        opce.setIcardno(carno);
        opce.setUsid(op.getUsid());
        String todaytime = Tools.getDayTimes();
        opce.setSzdtime(todaytime);
        opce.setIyear(new Long(todaytime.substring(0, 4)));
        opce.setImonth(new Long(todaytime.substring(5, 7)));
        opce.setIday(new Long(todaytime.substring(8, 10)));
        opce.setDtmakedate(todaytime);
        Long checkid = checkDao.getMaxPk("checkid", "Opcempployeecardcheck");
        opce.setCheckid(checkid + 1);
        checkDao.save(opce);
        List dzlist = checkDao
                .find(" from Opcemployeecardsubtab where iemployeecardid="
                        + op.getIemployeecardid() + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) dzlist.get(0);
        ops.setIpassedtimes(ops.getIpassedtimes() + ops.getMsingletimes());
        ops.setDtlastcheckdate(todaytime);
        ops.setDtmakedate(todaytime);
        checkDao.update(ops);
        rs.addRow(new String[] { "1" });
        return rs;
    }

    public ResultBean changeEmployeepass(String accid, String carno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase() + "'");
        Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
        // 保存记录
        Opcempployeecardcheck opce = new Opcempployeecardcheck();
        opce.setIaccessequipid(acc.getId().getIaccessequipid());
        opce.setIgardengateid(acc.getId().getIgardengateid());
        opce.setIemployeecardid(op.getIemployeecardid());
        opce.setIcardno(carno);
        opce.setIemployeeid(op.getIemployeeid());
        String todaytime = Tools.getDayTimes();
        opce.setSzdtime(todaytime);
        opce.setIyear(new Long(todaytime.substring(0, 4)));
        opce.setImonth(new Long(todaytime.substring(5, 7)));
        opce.setIday(new Long(todaytime.substring(8, 10)));
        opce.setDtmakedate(todaytime);
        Long checkid = checkDao.getMaxPk("checkid", "Opcempployeecardcheck");
        opce.setCheckid(checkid + 1);
        checkDao.save(opce);
        List dzlist = checkDao
                .find(" from Opcemployeecardsubtab where iemployeecardid="
                        + op.getIemployeecardid() + " and igardengateid="
                        + acc.getId().getIgardengateid());
        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) dzlist.get(0);
        ops.setIpassedtimes(ops.getIpassedtimes() + ops.getMsingletimes());
        ops.setDtlastcheckdate(todaytime);
        ops.setDtmakedate(todaytime);
        checkDao.update(ops);
        rs.addRow(new String[] { "1" });
        return rs;
    }

    public ResultBean getstation(Long iscenicid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "id", "code", "name" });
        List cjlist = checkDao
                .find("from Esbticketstationtab where  iscenicid=" + iscenicid);
        for (int i = 0; i < cjlist.size(); i++) {
            Esbticketstationtab acc = (Esbticketstationtab) cjlist.get(i);
            rs.addRow(new String[] { acc.getIticketstationid().toString(),
                    acc.getSzstationcode(), acc.getSzstationname() });
        }
        return rs;
    }

    public ResultBean getscenic(Long iscenicid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(4);
        rs.setColumnNames(new String[] { "id", "code", "name", "meet" });
        List cjlist = checkDao.find("from Esbscenicareatab where  ( iscenicid="
                + iscenicid + " or irootid=" + iscenicid + " ) and isjd=0");
        for (int i = 0; i < cjlist.size(); i++) {
            Esbscenicareatab acc = (Esbscenicareatab) cjlist.get(i);
            Hotelprovider hotelpder = (Hotelprovider) checkDao.get(
                    Hotelprovider.class, acc.getIscenicid());
            String meet = "1";
            if (hotelpder.getMeet() != null) {
                meet = hotelpder.getMeet();
            }
            rs.addRow(new String[] { acc.getIscenicid().toString(),
                    acc.getSzsceniccode(), acc.getSzscenicname(), meet });
        }
        return rs;
    }

    /**
     * *根据服务商读取对应的产品 Describe:
     *
     * @see ISaleCenterService#getTicket(Long)
     * @param iscenicid
     * @return
     * @author yuanchengjun Date:2011-10-27
     */
    public ResultBean getTicket(Long iscenicid) {
        String sql = "select e.itickettypeid,e.sztickettypecode,e.sztickettypename from edmtickettypetab e,esbscenicareatab es where  e.iscenicid=es.iscenicid and (es.iscenicid=? or es.irootid=?)";
        List<Map> list = new ArrayList();
        try {
            list = checkDao.findBySqlToMapnocolsesession(sql, iscenicid,
                    iscenicid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return MapToResultBean.toResultBean(list);
    }

    public ResultBean changeemployeeinput(String accid, String carno,
                                          String printno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });

        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);

        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase()
                        + "' and ( byisdaoyou=0 or  byisdaoyou is null) ");
        Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
        // 保存记录
        Opcempployeecardcheck opce = new Opcempployeecardcheck();
        opce.setIaccessequipid(acc.getId().getIaccessequipid());
        opce.setIgardengateid(acc.getId().getIgardengateid());
        opce.setIemployeecardid(op.getIemployeecardid());
        opce.setIcardno(carno);
        opce.setIemployeeid(op.getIemployeeid());
        String today = Tools.getDayTimes();
        opce.setSzdtime(today);
        opce.setIyear(new Long(today.substring(0, 4)));
        opce.setImonth(new Long(today.substring(5, 7)));
        opce.setIday(new Long(today.substring(8, 10)));
        Long checkid = checkDao.getMaxPk("checkid", "Opcempployeecardcheck");
        opce.setCheckid(checkid + 1);
        opce.setSzticketprintno(printno);
        opce.setDtmakedate(today);
        checkDao.save(opce);
        List list = checkDao
                .find(" from Stssoldtickettab where ( szticketprintno='"
                        + printno + "' or  myzj='" + printno
                        + "' ) and byvalidity='00'  order by dtmakedate  desc");
        Stssoldtickettab sticket = (Stssoldtickettab) list.get(0);
        sticket.setIpartitionsign(new Long(1));
        checkDao.update(sticket);
        // 更新园门检票数量

        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.iticketstationid="
                        + sticket.getId().getIticketstationid()
                        + "   and id.isalesvoucherid="
                        + sticket.getId().getIsalesvoucherid()
                        + " and id.isalesvoucherdetailsid="
                        + sticket.getId().getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid="
                        + sticket.getId().getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid());

        for (int c = 0; c < zsstlist.size(); c++) {
            Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(c);
            stssz.setIpassedtimes(stssz.getIpassedtimes() + 1);
            stssz.setDtlastcheckdate(today);
            stssz.setDtmakedate(today);
            stssz.setByisout(new Long(1));
            checkDao.update(stssz);
        }
        // 保存检票流水

        Ticketchecklist checkt = new Ticketchecklist();
        List<Map> iserialnumlist = new ArrayList();
        try {
            iserialnumlist = checkDao
                    .findBySqlToMapnocolsesession("select check_id.nextval  from dual");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Long checklid = new Long(
                (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
        checkt.setCheckid(checklid);
        checkt.setIgardengateid(acc.getId().getIgardengateid());
        checkt.setIaccessequipid(acc.getId().getIaccessequipid());
        checkt.setIscenicid(acc.getId().getIscenicid());
        checkt.setIsalesvoucherid(sticket.getId().getIsalesvoucherid());
        checkt.setIsalesvoucherdetailsid(sticket.getId()
                .getIsalesvoucherdetailsid());
        checkt.setIticketstationid(sticket.getId().getIticketstationid());
        checkt.setSzsoldticketid(sticket.getId().getSzsoldticketid());
        checkt.setDtmakedate(today);
        checkDao.save(checkt);

        rs.addRow(new String[] { "1" });
        return rs;
    }

    public ResultBean changeCheckEmployee(String accid, String carno,String newUrl) {
    	
    	
    	//判断是否传入url
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
    	
        System.out.println("check2");
        boolean isVIP = false;
        if(carno.startsWith("VIP")){
            isVIP = true;
        }
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // 查询是员工卡
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase()
                        + "' and (byisdaoyou=0 or byisdaoyou is null) and byticketstate=0");

        if (dlist == null || dlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 不是准进卡
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });

        } else {

            Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
            Esfemployeetab emp = (Esfemployeetab) checkDao.get(
                    Esfemployeetab.class, op.getIemployeeid());
            if (emp.getByisuse() == 0) {//启用状态
                rs.addRow(new String[] { "-2" });// 该准进卡不能通过该园门
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
            }
            List dzlist = checkDao
                    .find(" from Opcemployeecardsubtab where iemployeecardid="
                            + op.getIemployeecardid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            if (dzlist == null || dzlist.size() == 0) {//园门判断
                rs.addRow(new String[] { "-2" });// 该准进卡不能通过该园门
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
            } else {

                if (op.getIserialnum().equals("1")) {//落杠标识
                    rs.addRow(new String[] { "4" });// 落杠
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                } else if (op.getByconsumetype().equals("1")) {//是否入园
                    Sysparv5 sv5 = (Sysparv5) checkDao.get(Sysparv5.class,new Sysparv5Id("YGZW", "****"));
                    /*if (sv5.getPmvb().equals("1") && !isVIP) {//是否登记指纹
                        List zwlist = checkDao
                                .find(" from Opcemployeefingerprinttab where iemployeecardid="
                                        + op.getIemployeecardid()
                                        + " and byisuse=1");
                        if (zwlist == null || zwlist.size() == 0) {
                            rs.addRow(new String[] { "1" });// 登记指纹
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                        } else {
                            rs.addRow(new String[] { "2" });// 验证指纹
                            for (int i = 0; i < zwlist.size(); i++) {
                                Opcemployeefingerprinttab ops = (Opcemployeefingerprinttab) zwlist
                                        .get(i);
                                rs.addRow(new String[] { ops.getSzfeatures() });// 指纹特征值
                            }
                            for (int i = 0; i < 10 - zwlist.size(); i++) {
                                rs.addRow(new String[] { "" });
                            }
                        }
                    }*/ 
                    if(false){
                    	//暂时取消指纹验证
                    }else {
                        boolean isOK = true;
                        if(isVIP) {//VIP卡还需判断有效期和使用次数
                            if (!StringUtils.isBlank(op.getByticketstate())
                                    && !"0".equalsIgnoreCase(op.getByticketstate())) {
                                rs.addRow(new String[] { "-1" });
                            }
                            String rzti = op.getDtstartdate();
                            String ldti = op.getDtenddate();
                            String now = Tools.getDays();
                            if (rzti.compareToIgnoreCase(now) > 0) {//未到使用时间
                                rs.addRow(new String[]{"-6"});
                                isOK = false;
                            } else if (ldti.compareToIgnoreCase(now) < 0) {//无效
                                rs.addRow(new String[]{"-7"});
                                isOK = false;
                            } else {
                                if (op.getJpnumbs() != null && op.getJpnumbs() > 0) {
                                    long cha = op.getJpnumbs().longValue() - op.getYjnumbs().longValue();//剩余检票次数
                                    if (cha < 1) {
                                        rs.addRow(new String[]{"-8"});
                                        isOK = false;
                                    }
                                }
                            }
                        }
                        if(!isOK){
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "22" });//员工卡
                            return rs;
                        }
                        rs.addRow(new String[] { isVIP == true ? "6" : "5" });// 用工入园
                        rs.addRow(new String[] { op.getSzemployname() });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        if(isVIP && !StringUtils.isBlank(op.getMemoyy())){//语音播报
                            rs.addRow(new String[] { op.getMemoyy() });
                        }else{
                            rs.addRow(new String[] { "" });
                        }
                        rs.addRow(new String[] { "" });
                        Opcempployeecardcheck opce = new Opcempployeecardcheck();
                        opce.setIaccessequipid(acc.getId().getIaccessequipid());
                        opce.setIgardengateid(acc.getId().getIgardengateid());
                        opce.setIemployeecardid(op.getIemployeecardid());
                        opce.setIcardno(carno);
                        opce.setIemployeeid(op.getIemployeeid());
                        String todaytime = Tools.getDayTimes();
                        opce.setSzdtime(todaytime);
                        opce.setIyear(new Long(todaytime.substring(0, 4)));
                        opce.setImonth(new Long(todaytime.substring(5, 7)));
                        opce.setIday(new Long(todaytime.substring(8, 10)));
                        opce.setDtmakedate(todaytime);
                        Long checkid = checkDao.getMaxPk("checkid",
                                "Opcempployeecardcheck");
                        opce.setCheckid(checkid + 1);
                        checkDao.save(opce);

                        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) dzlist
                                .get(0);
                        ops.setIpassedtimes(ops.getIpassedtimes()
                                + ops.getMsingletimes());
                        ops.setDtlastcheckdate(todaytime);
                        ops.setDtmakedate(todaytime);
                        checkDao.update(ops);

                        if(isVIP){
                            op.setYjnumbs(op.getYjnumbs()+1L);
                            checkDao.update(op);
                        }
                    }
                } else {
                    rs.addRow(new String[] { "-98" });// 放行卡不能单独使用
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                }
            }

            List syslist = checkDao
                    .find(" from Sysparv5 where id.pmky='YGGP' and id.pmcd!='****' and isvalue =1 order by pmvc");
            if (syslist != null && syslist.size() > 0 && !isVIP) {
                for (int i = 0; i < syslist.size(); i++) {
                    Sysparv5 v5 = (Sysparv5) syslist.get(i);
                    if (v5.getPmvb().equals("szemployeename")) {
                        rs.addRow(new String[] { v5.getPmva() + " "
                                + emp.getSzemployeename() });
                    }
                    if (v5.getPmvb().equals("szcardid")) {
                        rs.addRow(new String[] { v5.getPmva() + " "
                                + emp.getSzcardid() });
                    }
                    if (v5.getPmvb().equals("strupid")) {
                        System.out.println("upid=" + emp.getPhotoupid());
                        if (emp.getPhotoupid() > 0) {
                            Upfile u = (Upfile) checkDao.get(Upfile.class,
                                    emp.getPhotoupid());
                            if (u == null) {
                                rs.addRow(new String[] { "" });
                            } else {
                                String uppath = "http://"
                                        + newUrl
                                        + "/" + u.getUpadder()
                                        + u.getUpfilename();
                                rs.addRow(new String[] { uppath });
                            }
                        } else {
                            rs.addRow(new String[] { "" });
                        }
                    }
                    if (v5.getPmvb().equals("szcompanyinfoname")) {
                        Galcompanyinfotab g = (Galcompanyinfotab) checkDao.get(
                                Galcompanyinfotab.class,
                                emp.getIcompanyinfoid());
                        rs.addRow(new String[] { v5.getPmva() + " "
                                + g.getSzcompanyname() });
                    }
                    if (v5.getPmvb().equals("szdeptname")) {
                        Esfdepttab e = (Esfdepttab) checkDao.get(
                                Esfdepttab.class, emp.getIdeptid());
                        rs.addRow(new String[] { v5.getPmva() + " "
                                + e.getSzdeptname() });
                    }
                    if (v5.getPmvb().equals("szstrpost")) {
                        // 职务
                        Sysparv5 s = (Sysparv5) checkDao.get(Sysparv5.class,
                                new Sysparv5Id("ZWTP", emp.getSzpost()));
                        rs.addRow(new String[] { v5.getPmva() + " "
                                + s.getPmva() });
                    }
                }
                if (syslist.size() < 5) {
                    for (int a = syslist.size(); a < 5; a++) {
                        rs.addRow(new String[] { "" });
                    }
                }
            } else {
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
            }
        }
        rs.addRow(new String[] { "22" });// 员工卡
        return rs;
    }
    /**
     * 获取闸机对应的ftp服务器信息
     */
    public ResultBean getFtp(String accid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(3);
        rs.setColumnNames(new String[] { "url", "dukou", "usid", "password" });
        /*List zwlist = checkDao
                .find("from Sysparv5 v5 where v5.id.pmky='FTTP' and v5.id.pmcd='"
                        + accid + "' ");*/
        Sysparv5 v5 = sysService.findOne("FTTP", accid);
        if (v5 != null) {
        	//获取对应闸机信息ftp服务器信息 host port username password
        	rs.addRow(new String[] { v5.getPmva(), v5.getPmvb(),v5.getPmvc(), v5.getPmvd() });
        } else {
        	rs.addRow(new String[] { "0", "0", "0", "0" });// 没有对应闸机
        }
        return rs;
    }

    public ResultBean findEsbaccessequiptab(Long iaccessequipid) {
        return ListToResultBean.ToResultBean(checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + iaccessequipid));
    }

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public ResultBean getCheckcount(Long iaccessequipid, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		String today = Tools.getTodayString();
		List acclist = checkDao.find(" from Esbaccessequiptab where id.iaccessequipid=" + iaccessequipid);
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

		String cs = checkDao.updatecheckcount(acc.getId().getIscenicid());
		
		saleCenterService.UpdateCheckcount(cs);
		
		ResultBean rs = new ResultBean();
		rs.setColumnCount(4);
		rs.setColumnNames(new String[] { "igardengateid", "numb", "cnumb" });

		List coulist = countList.getCoulist();
		boolean isg = false;
		for (int i = 0; i < coulist.size(); i++) {
			Checkcount c = (Checkcount) coulist.get(i);
			if (c.getIgardengateid().longValue() == acc.getId().getIgardengateid().longValue()) {
				isg = true;
			}
			if (c.getIgardengateid().longValue() == 0
					|| c.getIgardengateid().longValue() == acc.getId().getIgardengateid().longValue()) {
				rs.addRow(new String[] { String.valueOf(c.getIgardengateid()), String.valueOf(c.getNumb()),
						String.valueOf(c.getCnumb()) });
			}
		}
		if (!isg) {
			rs.addRow(new String[] { "0", "0", "0" });

		}

		return rs;
	}

    public ResultBean changeemployeechangeraft(String accid, String carno,
                                               String printno,String url) {
    	if(url==null || url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        // 根据当前时间查询对应趟次
        Changecheckticket ckt = new Changecheckticket();
        String today = Tools.getDayTimes();
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase()
                        + "' and ( byisdaoyou=0 or  byisdaoyou is null) ");

        if (dlist == null || dlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 不是准进卡
            return rs;
        } else {
            Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
            if (op.getByisdaoyou() == 1) {
                rs.addRow(new String[] { "-1" });// 不是准进卡
                return rs;
            } else {
                if (op.getIagentno().equals("1")) {
                    // 根据园门ID和卡证ID读取可进园门数据
                    List dzlist = checkDao
                            .find(" from Opcemployeecardsubtab where iemployeecardid="
                                    + op.getIemployeecardid()
                                    + " and igardengateid="
                                    + acc.getId().getIgardengateid());
                    if (dzlist == null || dzlist.size() == 0) {
                        rs.addRow(new String[] { "-2" });// 该准进卡不能通过该园门
                        return rs;
                    } else {
                        ckt.setIemployeeid(op.getIemployeeid());
                        ckt.setIaccessequipid(acc.getId().getIaccessequipid());
                        ckt.setIgardengateid(acc.getId().getIgardengateid());
                        // 取出该票数据
                        List list = checkDao
                                .find(" from Stssoldtickettab where szticketprintno='"
                                        + printno
                                        + "' order by dtmakedate desc");
                        Stssoldtickettab sticket = (Stssoldtickettab) list
                                .get(0);
                        // 查询当前趟次
                        String times = Tools.getNowTime().substring(0, 5);
                        // 取出该票园门检票数量
                        List zsstlist = checkDao
                                .find("from Stssoldticketsubtab where  id.iticketstationid="
                                        + sticket.getId().getIticketstationid()
                                        + "   and id.isalesvoucherid="
                                        + sticket.getId().getIsalesvoucherid()
                                        + " and id.isalesvoucherdetailsid="
                                        + sticket.getId()
                                        .getIsalesvoucherdetailsid()
                                        + " and id.szsoldticketid="
                                        + sticket.getId().getSzsoldticketid()
                                        + " and igardengateid="
                                        + acc.getId().getIgardengateid());
                        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist
                                .get(0);

                        Long ntrip = new Long(0);
                        String nstime = "";
                        String netime = "";

//                        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
//                            // 更新远程竹筏趟次控制数据
//                            try {
//                                javax.xml.rpc.Service service = null;
//                                java.net.URL endpointURL = new java.net.URL(
//                                        "http://"
//                                                + url
//                                                + "/services/centersaleService?wsdl");
//                                // System.out.println("WebContant.GetKeyValue(CenterUrl)="+WebContant.GetKeyValue("CenterUrl"));
//                                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
//                                        endpointURL, service);
//                                ssl.setMaintainSession(true);
//
//                                com.ectrip.ticket.centersale.client.ResultBean rs1 = ssl
//                                        .updateChangecheckticket(stssz
//                                                .getIztickettypeid()
//                                                + ","
//                                                + today
//                                                + ","
//                                                + stssz.getDtbegindate()
//                                                .substring(0, 10)
//                                                + ","
//                                                + stssz.getTripid());
//
//                                if (rs1.getResult(0, 0).equals("-1")) {
//                                    rs.addRow(new String[] { "-3" });
//                                    rs.addRow(new String[] { "本趟次的竹筏量已是用完不能改签" });
//                                    return rs;
//                                }
//
//                                if (rs1.getResult(0, 0).equals("-2")) {
//
//                                    rs.addRow(new String[] { "-4" });
//                                    rs.addRow(new String[] { "目前时间没有趟次" });
//                                    return rs;
//                                }
//                                if (Integer.parseInt(rs1.getResult(0, 0)) > 0) {
//
//                                    ntrip = new Long(rs1.getResult(0, 0));
//                                    nstime = rs1.getResult(1, 0);
//                                    netime = rs1.getResult(2, 0);
//                                }
//                            } catch (Exception e1) {
//                                System.out.println(e1.getMessage());
//                                rs.addRow(new String[] { "-5" });
//                                rs.addRow(new String[] { "更新竹筏趟次数据失败" });
//                                return rs;
//                            }
//                        } else {
                            // 更新竹筏趟次控制数据
                            ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                                    .getBean("saleCenterService");
                            ResultBean rs1 = saleCenterService
                                    .UpdateChangecheckticket(stssz
                                            .getIztickettypeid()
                                            + ","
                                            + today
                                            + ","
                                            + stssz.getDtbegindate().substring(
                                            0, 10)
                                            + ","
                                            + stssz.getTripid());
                            if (rs1.getResult(0, 0).equals("-1")) {
                                rs.addRow(new String[] { "-3" });
                                rs.addRow(new String[] { "本趟次的竹筏量已是用完不能改签" });
                                return rs;
                            }

                            if (rs1.getResult(0, 0).equals("-2")) {
                                rs.addRow(new String[] { "-4" });
                                rs.addRow(new String[] { "目前时间没有趟次" });
                                return rs;
                            }
                            if (Integer.parseInt(rs1.getResult(0, 0)) > 0) {

                                ntrip = new Long(rs1.getResult(0, 0));
                                nstime = rs1.getResult(1, 0);
                                netime = rs1.getResult(2, 0);
                            }
//                        }
                        // 保存竹筏改签记录
                        ckt.setNdate(today.substring(0, 10));
                        ckt.setNtripid(ntrip);
                        ckt.setYdate(stssz.getDtbegindate().substring(0, 10));

                        ckt.setYtripid(stssz.getTripid());
                        ckt.setSzticketprintno(printno);
                        ckt.setDtmakedate(today);
                        Long cktid = checkDao.getMaxPk("changeid",
                                "Changecheckticket");
                        ckt.setChangeid(cktid + 1);
                        checkDao.save(ckt);
                        // 跟新原售出门票子表对应竹筏园门的竹筏趟次及时间
                        stssz.setDtbegindate(Tools.getTodayString() + " "
                                + nstime + ":00");
                        stssz.setDtenddate(Tools.getTodayString() + " "
                                + netime + ":00");
                        stssz.setTripid(ntrip);
                        stssz.setIpassedtimes(stssz.getIpassedtimes() + 1);
                        stssz.setDtlastcheckdate(today);
                        stssz.setDtmakedate(today);
                        stssz.setByisout(new Long(1));
                        checkDao.update(stssz);
                        // 保存放行记录
                        Opcempployeecardcheck opce = new Opcempployeecardcheck();
                        opce.setIaccessequipid(acc.getId().getIaccessequipid());
                        opce.setIgardengateid(acc.getId().getIgardengateid());
                        opce.setIemployeecardid(op.getIemployeecardid());
                        opce.setIcardno(carno);
                        opce.setIemployeeid(op.getIemployeeid());
                        opce.setSzdtime(today);
                        opce.setIyear(new Long(today.substring(0, 4)));
                        opce.setImonth(new Long(today.substring(5, 7)));
                        opce.setIday(new Long(today.substring(8, 10)));
                        Long checkid = checkDao.getMaxPk("checkid",
                                "Opcempployeecardcheck");
                        opce.setCheckid(checkid + 1);
                        opce.setSzticketprintno(printno);
                        opce.setDtmakedate(today);
                        checkDao.save(opce);
                        // 更新员工放行园门数据
                        List ddzlist = checkDao
                                .find(" from Opcemployeecardsubtab where iemployeecardid="
                                        + op.getIemployeecardid()
                                        + " and igardengateid="
                                        + acc.getId().getIgardengateid());
                        Opcemployeecardsubtab ops = (Opcemployeecardsubtab) ddzlist
                                .get(0);
                        ops.setIpassedtimes(ops.getIpassedtimes()
                                + ops.getMsingletimes());
                        ops.setDtlastcheckdate(today);
                        ops.setDtmakedate(today);
                        checkDao.update(ops);
                        // 更新检票记录
                        Ticketchecklist checkt = new Ticketchecklist();
                        List<Map> iserialnumlist = new ArrayList();
                        try {
                            iserialnumlist = checkDao
                                    .findBySqlToMapnocolsesession("select check_id.nextval  from dual");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Long checklid = new Long(
                                (((Map) iserialnumlist.get(0)).get("NEXTVAL"))
                                        .toString());

                        checkt.setCheckid(checklid);
                        checkt.setIgardengateid(acc.getId().getIgardengateid());
                        checkt.setIaccessequipid(acc.getId()
                                .getIaccessequipid());
                        checkt.setIscenicid(acc.getId().getIscenicid());
                        checkt.setIsalesvoucherid(sticket.getId()
                                .getIsalesvoucherid());
                        checkt.setIsalesvoucherdetailsid(sticket.getId()
                                .getIsalesvoucherdetailsid());
                        checkt.setIticketstationid(sticket.getId()
                                .getIticketstationid());
                        checkt.setSzsoldticketid(sticket.getId()
                                .getSzsoldticketid());
                        checkt.setDtmakedate(today);
                        String seq = "";
                        Long sseq = new Long(0);
                        try {
                            // 读取竹筏小票
                            seq = checkDao.getZhuFaMaxNo(ntrip.toString());

                            sseq = Long.parseLong(seq) - 1;

                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        checkt.setZfseq(sseq.toString());
                        checkDao.save(checkt);

                        checkDao.updateraftcheck(ntrip, Tools.getTodayString(),
                                sseq,url);
                        Trip trip = (Trip) checkDao.get(Trip.class, ntrip);
                        rs.addRow(new String[] { "1" });
                        rs.addRow(new String[] { String.valueOf(sseq) });
                        rs.addRow(new String[] { Tools.getTodayString()
                                .substring(2) + " " + nstime + ":00" });
                        rs.addRow(new String[] { trip.getTripname() });
                        rs.addRow(new String[] { "改签成功" });
                        return rs;
                    }
                } else {
                    rs.addRow(new String[] { "-1" });// 不是准进卡
                    return rs;
                }
            }
        }

    }

    /**
     * 李进修改，检票方法 ，增加 OtherString 参数，
     *
     * @param accid
     * @param printno
     * @param OtherString
     * @return
     * @throws Exception
     */
    public ResultBean changescCheckTicket(String accid, String printno,
                                          int icjp,String newUrl,String... OtherString) throws Exception {
    	//判断是否输入url
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
   //----------------------------增加检票前验证，防止误出票-------------------------------------------------//
        if(printno.startsWith("Time")){
        	printno = printno.substring(5);
        	
            if(printno.startsWith("HQYT_")){
            	System.out.println(constUtils.getHQYTKEY());
           	printno = DesUtil.decrypt(printno.substring(5),constUtils.getHQYTKEY());
           }else if (printno.startsWith("CYT_")) {
           	printno = DesUtil.decrypt(printno.substring(4),constUtils.getKEY());
           }
            try {
            	IPassCardService passCardService = (IPassCardService) SpringUtil
                        .getBean("passCardService");
            	if (printno.length()==18 || printno.substring(0, 8).toUpperCase().equals("EMPLOYEE")) {
            		
            		String str = passCardService.CheckPassCrad(accid, printno);
                    System.out.println("str=" + str);
                    if (str.equals("1")) { // 导游
                        return changeCheckDaoyou(accid, printno,newUrl);
                    } else if (str.equals("2")) { // 员工
                        return changeCheckEmployee(accid, printno,newUrl);
                    } else if (str.equals("-1")) { // 一卡多票
                        rs.addRow(new String[] { "98" });// 无效票
                        rs.addRow(new String[] { "该身份证不止一张票，不能用身份证检票" });
                        return rs;
                    }/* else if (str.equals("0")) { // 找不到
                        System.out.println("身份证订单出票检票");
                        ResultBean r3 = checktorder(accid, printno);
                        r3.addRow(new String[] { "0" });
                        r3.addRow(new String[] { "23" });
                        return r3;
                    }*/else {

                        String requestParam=printno;
                        List list = checkDao.find("from Esbaccessequiptab where id.iaccessequipid = " + accid);
                        if (list != null && !list.isEmpty()) {
                            Esbaccessequiptab access = (Esbaccessequiptab) list.get(0);
                            requestParam=access.getId().getIscenicid()+"&"+printno;
                        }
                        String url = "http://"+newUrl+"/orderpay/findOridByIdcard.action";
                        String trealnameListJSON = HttpUtil.httpPost(url, "aaa",requestParam);
                        CYTDto dto = JSON.parseObject(trealnameListJSON, CYTDto.class);
                        if(dto!=null && dto.morder!=null)
                        {
                            printno = dto.morder.getOrid();
                        }else
                        {
                           /* if(printno.length()==18)
                            {
                                String tourcardHql = "from MOrder m,TOrder t where m.ordersource='lykgp' " +
                                        "and m.orid=t.orid and t.orhm='" + printno + "' and stdt='" + Tools.getDays() + "'";
                                List tourcardlist = checkDao
                                        .find(tourcardHql);
                                if (tourcardlist != null && !tourcardlist.isEmpty())//旅游卡订单
                                {
                                    printno=((Map)tourcardlist.get(0)).get("orid").toString();
                                    String tourcardCode=((Map)tourcardlist.get(0)).get("notee").toString();;
                                    //查询旅游卡有效期
                                    String tourcardDetailHql = "from TourCardDetail td " +
                                            "where td.CODE='" + tourcardCode + "";
                                    List tcNums = checkDao
                                            .find(tourcardDetailHql);
                                    if(tcNums==null || tcNums.isEmpty())
                                    {
                                        TourCardDetail tcd= (TourCardDetail) tcNums.get(0);
                                        if(tcd.getPeriodStartDate().compareTo(Tools.getDays())>0)
                                        {
                                            rs.addRow(new String[]{"-330"});// 旅游卡
                                            rs.addRow(new String[]{"该旅游卡还未到开始使用日期"});
                                        }
                                        if(tcd.getPeriodEndDate().compareTo(Tools.getDays())<0)
                                        {
                                            rs.addRow(new String[]{"-331"});// 旅游卡
                                            rs.addRow(new String[]{"该旅游卡已经过期"});
                                        }
                                    }else
                                    {
                                        rs.addRow(new String[] { "-333" });// 旅游卡
                                        rs.addRow(new String[] { "不存在此旅游卡" });
                                    }
                                } else//调用中心系统的查询
                                {

                                }
                            }*/
                        }
					}
				}
            	
            	String url = "http://"+newUrl+"/orderpay/searchTOrderList.action";
            	String tOrderListJSON = HttpUtil.httpPost(url, "aaa", printno);
                List<TOrderlist> parseArray=null;
            	if(!StringUtil.isEmpty(tOrderListJSON))
                {
                    parseArray= JSON.parseArray(tOrderListJSON, TOrderlist.class);
                }
                //lizhaodong 新增人脸识别的订单需先到线下出票
            	if(parseArray!=null && !parseArray.isEmpty())
                {
                    String ticketId="";
                    if((parseArray.size()==1 && parseArray.get(0)!=null && parseArray.get(0).getNumb()!=null && parseArray.get(0).getNumb()>1) || parseArray.size()>1)
                    {
                        for(TOrderlist tOrderlist:parseArray)
                        {
                            ticketId+=tOrderlist.getItickettypeid()+",";
                        }
                        if(ticketId.length()>1)
                        {
                            ticketId=ticketId.substring(0,ticketId.length()-1);
                        }
                        String hql="from Opwwicketsettab where itickettypeid in ("
                                + ticketId+") and  byregfingerprinttype='01'";
                        System.out.println(hql);
                        List opwwicketsettablist = checkDao
                                .find(hql);
                        if(opwwicketsettablist!=null && !opwwicketsettablist.isEmpty())
                        {
                            rs.addRow(new String[] { "-24" });// 人脸识别订单需先线下出票
                            rs.addRow(new String[] {"null"});
                            return rs;
                        }
                    }




                    TOrderlist orderlist = (TOrderlist) parseArray.get(0);
                    List opwwicketsettablist = checkDao
                            .find("from Opwwicketsettab where itickettypeid="
                                    + orderlist.getItickettypeid());
                    Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);


                    //检票设备判断
                    List acclist = checkDao
                            .find(" from Esbaccessequiptab where id.iaccessequipid="
                                    + accid + " and id.igardengateid=" +opww.getIgardengateid());

                    if (acclist.size()== 0) {
                        rs.addRow(new String[] {"-3"});
                        rs.addRow(new String[] {"null"});
                        return rs;
                    }

                    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));


                    // 验证有效时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    DateFormat format=new SimpleDateFormat("HH:mm");
                    //有效开始时间
                    Date d1 = df.parse(orderlist.getDtstartdate()+" 00:00:00");
                    Calendar begcalendar = Calendar.getInstance();
                    begcalendar.setTime(d1);
                    //有效结束时间
                    Date d2 = df.parse(orderlist.getDtenddate()+" 23:59:59");
                    Calendar endcalendar = Calendar.getInstance();
                    endcalendar.setTime(d2);
                    System.out.println("分时预约时间验证开始");

                    //---------------------新增分时预约检票时段判断------------------------//
                    //分时预约检票设置：0为默认值，表示不是分时预约票，1检票时间不可提前不可延后，2检票时间可提前但不能延后
                    //			  3检票时间不可提前但可延后，4检票时间可提前可延后
                    long checkSet = opww.getIstimeticket();

                    Date date = new Date();
                    long  newtime = format.parse(format.format(date)).getTime();//得到当前时分秒的毫秒数
                    if (now.before(begcalendar)) {

                        rs.addRow(new String[] { "-4" });// 无效票
                        rs.addRow(new String[] {"null"});//
                        return rs;//未到检票开始时间
                    }else if (now.after(endcalendar)) {
                        rs.addRow(new String[] { "-5" });// 无效票
                        rs.addRow(new String[] {"null"});
                        return rs;//已过检票时间
                    }else {
                        if(checkSet!=0){
                            try {
                                //预约开始时间
                                long stDate = format.parse(orderlist.getStarttime()).getTime();
                                //预约结束时间
                                long enDate = format.parse(orderlist.getEndtime()).getTime();
                                if ((newtime<stDate)&&(checkSet==1||checkSet==3)) {
                                    rs.addRow(new String[] { "-4" });// 无效票
                                    rs.addRow(new String[] {orderlist.getStarttime(),});
                                    return rs;//未到检票开始时间
                                }else if ((newtime>enDate)&&(checkSet==1||checkSet==2)) {
                                    rs.addRow(new String[] { "-5" });// 无效票
                                    rs.addRow(new String[] {"null"});
                                    return rs;//已过检票时间
                                }
                            } catch (Exception e) {
                                System.out.println("不是分时预约的票，检票设置为分时预约");
                            }

                        }
                    }
                }
      		} catch (Exception e) {

      			rs.addRow(new String[] {"-1"});
  				rs.addRow(new String[] {"null"});
  				return rs;
      		}
              
            rs.addRow(new String[] {"1"});
            return rs;
        }
//-------------------------------------------------END-------------------------------------------------------------------// 
        
        
        System.out.println("changescCheckTicket   666666666666666==" + printno);
//        IPassCardService passCardService = (IPassCardService) SpringUtil
//                .getBean("passCardService");
        boolean b = false;
        if (printno.length() == 17) {
            String date = printno.substring(0, 4) + "-"
                    + printno.substring(4, 6) + "-" + printno.substring(6, 8);
            System.out.println("身份证检票没有数据 ，查询 网上订单 是否有可以直接出票的订单");
            Pattern p = Pattern
                    .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
            b = p.matcher(date).matches();
        }
        if (b) {
            ResultBean r3 = checktorder(accid, printno,newUrl);
            r3.addRow(new String[] { "0" });
            r3.addRow(new String[] { "23" });
            return r3;
        }
        if (printno.length() == 18
                || printno.substring(0, 8).toUpperCase().equals("EMPLOYEE")) {// 员工卡、身份证
            String str = passCardService.CheckPassCrad(accid, printno);
            System.out.println("str=" + str);
            if (str.equals("1")) { // 导游
                return changeCheckDaoyou(accid, printno,newUrl);
            } else if (str.equals("2")) { // 员工
                return changeCheckEmployee(accid, printno,newUrl);
            } else if (str.equals("-1")) { // 一卡多票
                rs.addRow(new String[] { "98" });// 无效票
                rs.addRow(new String[] { "该身份证不止一张票，不能用身份证检票" });
                return rs;
            } else if (str.equals("0")) { // 找不到
                System.out.println("身份证订单出票检票");
                ResultBean r3 = checktorder(accid, printno,newUrl);
                r3.addRow(new String[] { "0" });
                r3.addRow(new String[] { "23" });
                return r3;
            } else {

                if (icjp == 0) {
                    System.out.println("icjp=0");
                    ResultBean rs2 = querychecktype(accid, str);
                    System.out.println("rs2=" + rs2.getResult(0, 0));
                    if (rs2.getResult(0, 0).equals("100")) {
                        ResultBean rs1 = changeCheckTicket(accid, str);
                        System.out.println("rs1=" + rs1.getResult(0, 0));
                        if (rs1.getResult(0, 0).equals("3")) {
                            savechecklink(accid, str);
                        }
                        System.out.println("rs1111212121212="
                                + rs1.getResult(0, 0));
                        if (rs1.getRowsCount() < 22) {
                            for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                                rs1.addRow(new String[] { "" });
                            }
                        }
                        if (!rs1.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = getmyzj(accid, str);
                            rs1.addRow(new String[] { stss.getMyzj() });
                            rs1.addRow(new String[] { stss.getName1() });
                        }

                        return rs1;
                    } else if (rs2.getResult(0, 0).equals("101")) {
                        // 一单一检
                        ResultBean rs1 = changeCheckTicket(accid, str);
                        if (rs1.getResult(0, 0).equals("3")) {
                            savechecklink(accid, str);
                            int jpnumb = 0;
                            jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                            System.out.println("jpnumb0=" + jpnumb);
                            List printnolist = queryprintnolist(accid, str);
                            if (printnolist != null && printnolist.size() > 0) {
                                for (int i = 0; i < printnolist.size(); i++) {
                                    Map map1 = (Map) printnolist.get(i);
                                    String szprintno = map1.get(
                                            "SZTICKETPRINTNO").toString();
                                    if (!szprintno.equals("")
                                            && !szprintno.equals(str)) {
                                        ResultBean rs3 = changeCheckTicket(
                                                accid, szprintno);
                                        if (rs3.getResult(0, 0).equals("3")) {
                                            jpnumb = jpnumb
                                                    + Integer.parseInt(rs3
                                                    .getResult(21, 0));
                                            System.out.println("jpnumb="
                                                    + jpnumb);
                                            savechecklink(accid, szprintno);
                                        }
                                    }
                                }
                            }
                            System.out.println("jpnumb111=" + jpnumb);
                            rs1.removeRow(21);
                            rs1.addRow(new String[] { String.valueOf(jpnumb) });
                            if (!rs1.getResult(0, 0).equals("-1")) {
                                Stssoldtickettab stss = getmyzj(accid, str);
                                rs1.addRow(new String[] { stss.getMyzj() });
                                rs1.addRow(new String[] { stss.getName1() });
                            }
                            //rs1.addRow(new String[] { String.valueOf(jpnumb) });
                            return rs1;
                        } else {
                            if (rs1.getRowsCount() < 22) {
                                for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                                    rs1.addRow(new String[] { "" });
                                }
                            }
                            if (!rs1.getResult(0, 0).equals("-1")) {
                                Stssoldtickettab stss = getmyzj(accid, str);
                                rs1.addRow(new String[] { stss.getMyzj() });
                                rs1.addRow(new String[] { stss.getName1() });
                            }
                            return rs1;
                        }
                    } else {
                        if (rs2.getResult(0, 0).equals("-1")
                                && printno.length() == 18) {
                            // 身份证检票没有数据 ，查询 网上订单 是否有可以直接出票的订单
                            ResultBean r3 = checktorder(accid, printno,newUrl);

                            return r3;
                        } else {
                            if (rs2.getRowsCount() < 22) {
                                for (int a = 0; a <= 22 - rs2.getRowsCount(); a++) {
                                    rs2.addRow(new String[] { "" });
                                }
                            }

                            return rs2;
                        }
                    }
                } else {
//                    return readCheckTicket(accid, printno);传输传递错误！应该传票号，而不是身份证号
                    return readCheckTicket(accid, str,newUrl);
                }
            }
        } else {// 正常检票
            if (icjp == 0) {
                ResultBean rs2 = querychecktype(accid, printno);
                System.out
                        .println("rs2.getResult(0, 0)=" + rs2.getResult(0, 0));
                if (rs2.getResult(0, 0).equals("100")) {
                    ResultBean rs1 = changeCheckTicket(accid, printno);
                    /**
                     * 调用关联园门检票
                     */
                    if (rs1.getResult(0, 0).equals("3")) {
                        savechecklink(accid, printno);
                    }
                    /**
                     * 吞卡回收程序
                     */
                    if (rs1.getRowsCount() < 22) {
                        for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                            rs1.addRow(new String[] { "" });
                        }
                    }
                    if (!rs1.getResult(0, 0).equals("-1")) {
                        Stssoldtickettab stss = getmyzj(accid, printno);
                        rs1.addRow(new String[] { stss.getMyzj() });
                        rs1.addRow(new String[] { stss.getName1() });
                    }

                    return rs1;
                } else if (rs2.getResult(0, 0).equals("101")) {
                    ResultBean rs1 = changeCheckTicket(accid, printno);
                    if (rs1.getResult(0, 0).equals("3")) {
                        savechecklink(accid, printno);
                        int jpnumb = 0;
                        jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                        List printnolist = queryprintnolist(accid, printno);
                        if (printnolist != null && printnolist.size() > 0) {
                            for (int i = 0; i < printnolist.size(); i++) {
                                Map map1 = (Map) printnolist.get(i);
                                String szprintno = map1.get("SZTICKETPRINTNO")
                                        .toString();
                                if (!szprintno.equals("")
                                        && !szprintno.equals(printno)) {

                                    ResultBean rs3 = changeCheckTicket(accid,
                                            szprintno);

                                    if (rs3.getResult(0, 0).equals("3")) {

                                        jpnumb = jpnumb
                                                + Integer.parseInt(rs3
                                                .getResult(21, 0));

                                        savechecklink(accid, szprintno);

                                    }

                                }
                            }
                        }

                        rs1.removeRow(21);
                        rs1.addRow(new String[] { String.valueOf(jpnumb) });
                        if (!rs1.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = getmyzj(accid, printno);
                            rs1.addRow(new String[] { stss.getMyzj() });
                            rs1.addRow(new String[] { stss.getName1() });
                        }
                        return rs1;
                    } else {
                        if (rs1.getRowsCount() < 22) {
                            for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                                rs1.addRow(new String[] { "" });
                            }
                        }
                        if (!rs1.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = getmyzj(accid, printno);
                            rs1.addRow(new String[] { stss.getMyzj() });
                            rs1.addRow(new String[] { stss.getName1() });
                        }
                        return rs1;
                    }
                } else {
                    if (rs2.getRowsCount() < 22) {
                        for (int a = 0; a <= 22 - rs2.getRowsCount(); a++) {
                            rs2.addRow(new String[] { "" });
                        }
                    }
                    if (!rs2.getResult(0, 0).equals("-1")) {
                        Stssoldtickettab stss = getmyzj(accid, printno);
                        rs2.addRow(new String[] { stss.getMyzj() });
                        rs2.addRow(new String[] { stss.getName1() });
                    }

                    return rs2;
                }
            } else {
                return readCheckTicket(accid, printno,newUrl);
            }
        }
    }

    /**
     * * Describe:线下检票 保存信息
     *
     * @see ICheckService#LoclOffLineCheckTicket(String,
     *      String)
     * @param gateid
     *            闸机ID
     * @param printno
     *            票号
     * @return
     * @author chenxinhao Date:2012-9-7
     */
    public ResultBean LoclOffLineCheckTicket(String accid, String printno,
                                             String checktime, Long srid, Long igrardid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        try {
            System.out.println("officard check start");
            long seq = this.checkDao.getSequenceId("iccard_sequence");
            Icofflinechecktab icoff = new Icofflinechecktab();
            icoff.setSeq(seq);
            icoff.setIgardengateid(igrardid);
            // icoff.setDtmakedate(checktime);
            icoff.setSzticketprintno(printno);
            icoff.setIwicketsetid(Long.parseLong(accid));
            icoff.setDtmakedate(Tools.getDayTimes());
            icoff.setIstrans(0L);
            icoff.setErrlogs("未同步");
            System.out.println("officard check end");
            this.checkDao.save(icoff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs.addRow(new String[] { "null" });
        return rs;
    }

    public static void main(String[] args) {
        int[] x = new int[] { 1, 2, 3, 4, 5, 6 };
        System.out.println(Arrays.toString(x));
    }

    public ResultBean getzhiwen(String carno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where szticketprintno='"
                        + carno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid");
        if (sstlist == null || sstlist.size() == 0) {
            rs.addRow(new String[] { "" });// 本地无票务信息
        } else {
            Stssoldtickettab stss = new Stssoldtickettab();
            try {
                BeanUtils.populate(stss, (Map) sstlist.get(0));
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
            sttsid.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
            sttsid.setIsalesvoucherid(stss.getIsalesvoucherid());
            sttsid.setIticketstationid(stss.getIticketstationid());
            sttsid.setSzsoldticketid(stss.getSzsoldticketid());
            Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
                    .get(Stssoldticketattesttab.class, sttsid);
            if (stset == null) {
                rs.addRow(new String[] { "" });// 本地无指纹信息
            } else {
                rs.addRow(new String[] { stset.getBsfilebinary() });
            }
        }
        return rs;
    }

    /**
     * 总体对外检票方法
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return 2012-7-28 日 李进进行重大修改 ,String ...OtherString 增加了第三个参数
     */

    public ResultBean readCheckTicket(String accid, String szticketprintno,String url,
                                      String... OtherString) throws Exception {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        // System.out.println(" in changeCheckTicket");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        // 读取闸机信息

        String seq = "";
        // System.out.println(" in accid === " + accid);
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            return rs;
        }
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // System.out.println("changeCheckTicket1");
        // 查询售出门票表
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where szticketprintno='"
                        + szticketprintno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid order by s.dtmakedate desc");
        Stssoldtickettab stss = null;

        if (sstlist == null || sstlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            rs.addRow(new String[] { "无效检票设备" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            return rs;
        } else {
            stss = new Stssoldtickettab();
            BeanUtils.populate(stss, (Map) sstlist.get(0));
            if (stss.getByvalidity().equals("01")
                    || stss.getByvalidity().equals("02")) {
                rs.addRow(new String[] { "-2" });// 票已退订或挂失
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs = getchecklist(rs, stss);
                return rs;
            }
        }

        // 查询园门和票务对应的售出门票子票表
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.iticketstationid="
                        + stss.getIticketstationid()
                        + "   and id.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and id.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid());
        if (zsstlist == null || zsstlist.size() == 0) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs.addRow(new String[] { stss.getSztickettypename() });
            rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
            rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
            rs.addRow(new String[] { stss.getSzcrowdkindname() });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { stss.getSzmemo() });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs = getchecklist(rs, stss);
            return rs;
        }
        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(0);
        // if (stssz.getByconsumemode().equals("00")) {
        String jpnumb1 = "1";
        if (stssz.getMsingletimes() != null) {
            jpnumb1 = stssz.getMsingletimes().toString();
        }
        System.out.println("jpnumb1=" + jpnumb1);
        // 按次数验票
        if (stssz.getIsvalid() == -1) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs.addRow(new String[] { stss.getSztickettypename() });
            rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
            rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
            rs.addRow(new String[] { stss.getSzcrowdkindname() });
            rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
            rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { stss.getSzmemo() });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { "" });
            rs = getchecklist(rs, stss);
            rs.addRow(new String[] { jpnumb1 });
            return rs;
        }
        if (stssz.getIpasstimes() > 0) {
            // stssz.getIpasstimes() > 0表示有次数限制 stssz.getIpasstimes() = 0 不限制次数

            if (stssz.getIpasstimes() - stssz.getIpassedtimes() == 0) {
                rs.addRow(new String[] { "-9" });// 已检过
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
                rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }
        }

        try {
            // 验证有效时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(stssz.getDtbegindate());
            Calendar begcalendar = Calendar.getInstance();
            begcalendar.setTime(d1);
            Date d2 = df.parse(stssz.getDtenddate());
            Calendar endcalendar = Calendar.getInstance();
            endcalendar.setTime(d2);

            if (now.before(begcalendar)) {
                System.out.println("changeCheckTicket:-4");
                rs.addRow(new String[] { "-4" });// 未到检票开始时间
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
                rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

            if (now.after(endcalendar)) {
                rs.addRow(new String[] { "-5" });// 已过有效期
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
                rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }
            // 读取竹筏票seq
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                Edmtickettypetab.class, stss.getItickettypeid());
        if (eticket.getByusage() == 0) {
            if (stssz.getTripid() > 0) {/*
                // 数量控制中查询竹筏票的趟次是否在使用
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                        javax.xml.rpc.Service service = null;
                        java.net.URL endpointURL = new java.net.URL("http://"
                                + url
                                + "/services/centersaleService?wsdl");
                        CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                                endpointURL, service);
                        ssl.setMaintainSession(true);
                        com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                                .getProductcontrol(stssz.getIztickettypeid(),
                                        stssz.getTripid(),
                                        stssz.getDtbegindate().substring(0, 10));
                        if (cano.getRowsCount() == 0) {
                            rs.addRow(new String[] { "-7" });// 竹筏停排
                            rs.addRow(new String[] { stss.getSztickettypename() });
                            rs.addRow(new String[] { stss.getMactualsaleprice()
                                    .toString() });
                            rs.addRow(new String[] { stss.getIcrowdkindid()
                                    .toString() });
                            rs.addRow(new String[] { stss.getSzcrowdkindname() });
                            rs.addRow(new String[] { stssz.getDtbegindate()
                                    .substring(2) });
                            rs.addRow(new String[] { stssz.getDtenddate()
                                    .substring(2) });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { stss.getSzmemo() });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        } else {
                            Long Bystate = new Long(
                                    cano.getResult(0, "BYSTATE"));
                            if (Bystate < 1) {
                                rs.addRow(new String[] { "-7" });// 竹筏停排
                                rs.addRow(new String[] { stss
                                        .getSztickettypename() });
                                rs.addRow(new String[] { stss
                                        .getMactualsaleprice().toString() });
                                rs.addRow(new String[] { stss.getIcrowdkindid()
                                        .toString() });
                                rs.addRow(new String[] { stss
                                        .getSzcrowdkindname() });
                                rs.addRow(new String[] { stssz.getDtbegindate()
                                        .substring(2) });
                                rs.addRow(new String[] { stssz.getDtenddate()
                                        .substring(2) });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { stss.getSzmemo() });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { "" });
                                rs = getchecklist(rs, stss);
                                rs.addRow(new String[] { jpnumb1 });
                                return rs;
                            }
                        }
                    } catch (Exception e) {
                        List productcontrollist = checkDao
                                .find("select bystate from Productcontrol where stdata='"
                                        + stssz.getDtbegindate().substring(0,
                                        10)
                                        + "' and itickettypeid="
                                        + stssz.getIztickettypeid()
                                        + " and tripid="
                                        + stssz.getTripid()
                                        + " and controltype='03'");
                        if (productcontrollist == null
                                || productcontrollist.size() == 0) {
                            rs.addRow(new String[] { "-7" });// 竹筏停排
                            rs.addRow(new String[] { stss.getSztickettypename() });
                            rs.addRow(new String[] { stss.getMactualsaleprice()
                                    .toString() });
                            rs.addRow(new String[] { stss.getIcrowdkindid()
                                    .toString() });
                            rs.addRow(new String[] { stss.getSzcrowdkindname() });
                            rs.addRow(new String[] { stssz.getDtbegindate()
                                    .substring(2) });
                            rs.addRow(new String[] { stssz.getDtenddate()
                                    .substring(2) });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { stss.getSzmemo() });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        } else {
                            Long Bystate = (Long) productcontrollist.get(0);
                            if (Bystate < 1) {
                                rs.addRow(new String[] { "-7" });// 竹筏停排
                                rs.addRow(new String[] { stss
                                        .getSztickettypename() });
                                rs.addRow(new String[] { stss
                                        .getMactualsaleprice().toString() });
                                rs.addRow(new String[] { stss.getIcrowdkindid()
                                        .toString() });
                                rs.addRow(new String[] { stss
                                        .getSzcrowdkindname() });
                                rs.addRow(new String[] { stssz.getDtbegindate()
                                        .substring(2) });
                                rs.addRow(new String[] { stssz.getDtenddate()
                                        .substring(2) });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { stss.getSzmemo() });
                                rs.addRow(new String[] { "" });
                                rs.addRow(new String[] { "" });
                                rs = getchecklist(rs, stss);
                                rs.addRow(new String[] { jpnumb1 });
                                return rs;
                            }
                        }
                    }
                } else {
                    System.out.println("读取本地趟次控制");
                    List productcontrollist = checkDao
                            .find("select bystate from Productcontrol where stdata='"
                                    + stssz.getDtbegindate().substring(0, 10)
                                    + "' and itickettypeid="
                                    + stssz.getIztickettypeid()
                                    + " and tripid="
                                    + stssz.getTripid()
                                    + " and controltype='03'");
                    if (productcontrollist == null
                            || productcontrollist.size() == 0) {
                        System.out.println("changeCheckTicket:-7");
                        rs.addRow(new String[] { "-7" });// 竹筏停排
                        rs.addRow(new String[] { stss.getSztickettypename() });
                        rs.addRow(new String[] { stss.getMactualsaleprice()
                                .toString() });
                        rs.addRow(new String[] { stss.getIcrowdkindid()
                                .toString() });
                        rs.addRow(new String[] { stss.getSzcrowdkindname() });
                        rs.addRow(new String[] { stssz.getDtbegindate()
                                .substring(2) });
                        rs.addRow(new String[] { stssz.getDtenddate()
                                .substring(2) });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { stss.getSzmemo() });
                        rs.addRow(new String[] { "" });
                        rs.addRow(new String[] { "" });
                        rs = getchecklist(rs, stss);
                        rs.addRow(new String[] { jpnumb1 });
                        return rs;
                    } else {
                        Long Bystate = (Long) productcontrollist.get(0);
                        if (Bystate < 1) {
                            System.out.println("changeCheckTicket:-7");
                            rs.addRow(new String[] { "-7" });// 竹筏停排
                            rs.addRow(new String[] { stss.getSztickettypename() });
                            rs.addRow(new String[] { stss.getMactualsaleprice()
                                    .toString() });
                            rs.addRow(new String[] { stss.getIcrowdkindid()
                                    .toString() });
                            rs.addRow(new String[] { stss.getSzcrowdkindname() });
                            rs.addRow(new String[] { stssz.getDtbegindate()
                                    .substring(2) });
                            rs.addRow(new String[] { stssz.getDtenddate()
                                    .substring(2) });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { stss.getSzmemo() });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        }
                    }
                }
                Prdtripvenuemanage ptv = new Prdtripvenuemanage();
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                        javax.xml.rpc.Service service = null;
                        java.net.URL endpointURL = new java.net.URL("http://"
                                + url
                                + "/services/centersaleService?wsdl");

                        CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                                endpointURL, service);
                        ssl.setMaintainSession(true);
                        com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                                .getPrdtripvenuemanage(stssz
                                                .getIztickettypeid(),
                                        stssz.getTripid(),
                                        stssz.getDtbegindate().substring(0, 10));

                        if (cano.getRowsCount() > 0) {
                            ptv = new Prdtripvenuemanage();
                            ptv.setItickettypeid(stssz.getIztickettypeid());
                            ptv.setTripid(stssz.getTripid());

                            ptv.setStarttime(cano.getResult(0, "STARTTIME"));
                            ptv.setEndtime(cano.getResult(0, "ENDTIME"));
                            ptv.setIadvanceminute(new Long(cano.getResult(0,
                                    "IADVANCEMINUTE").toString()));
                            ptv.setIlagminute(new Long(cano.getResult(0,
                                    "ILAGMINUTE").toString()));
                            ptv.setIvenueareaid(new Long(cano.getResult(0,
                                    "IVENUEAREAID").toString()));
                            ptv.setIvenueid(new Long(cano.getResult(0,
                                    "IVENUEID").toString()));
                        } else {

                            List plist = checkDao
                                    .find(" from Prdtripvenuemanage where tripid="
                                            + stssz.getTripid()
                                            + " and itickettypeid="
                                            + stssz.getIztickettypeid()
                                            + " and startdata<='"
                                            + stssz.getDtbegindate().substring(
                                            0, 10)
                                            + "' and enddata>='"
                                            + stssz.getDtbegindate().substring(
                                            0, 10) + "'");
                            ptv = (Prdtripvenuemanage) plist.get(0);
                        }
                    } catch (Exception e1) {
                        List plist = checkDao
                                .find(" from Prdtripvenuemanage where tripid="
                                        + stssz.getTripid()
                                        + " and itickettypeid="
                                        + stssz.getIztickettypeid()
                                        + " and startdata<='"
                                        + stssz.getDtbegindate().substring(0,
                                        10)
                                        + "' and enddata>='"
                                        + stssz.getDtbegindate().substring(0,
                                        10) + "'");
                        ptv = (Prdtripvenuemanage) plist.get(0);
                    }
                } else {
                    System.out.println("读取本地趟次时间");
                    List plist = checkDao
                            .find(" from Prdtripvenuemanage where tripid="
                                    + stssz.getTripid() + " and itickettypeid="
                                    + stssz.getIztickettypeid()
                                    + " and startdata<='"
                                    + stssz.getDtbegindate().substring(0, 10)
                                    + "' and enddata>='"
                                    + stssz.getDtbegindate().substring(0, 10)
                                    + "'");
                    ptv = (Prdtripvenuemanage) plist.get(0);
                }
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                try {
                    // 跟新趟次具体时间
                    Date d1 = df.parse(stssz.getDtbegindate().substring(0, 10)
                            + " " + ptv.getStarttime() + ":00");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(d1);
                    calendar.add(Calendar.MINUTE, -1
                            * ptv.getIadvanceminute().intValue());
                    stssz.setDtbegindate(Tools.getDayTimes(calendar));
                    Date d2 = df.parse(stssz.getDtbegindate().substring(0, 10)
                            + " " + ptv.getEndtime() + ":00");
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(d2);
                    calendar1.add(Calendar.MINUTE, ptv.getIlagminute()
                            .intValue());
                    stssz.setDtenddate(Tools.getDayTimes(calendar1));
                } catch (ParseException e1) {
                    e1.printStackTrace();

                }
            */}

            // 根据园门/产品/子产品检票设置信息
            List opwwicketsettablist = checkDao
                    .find("from Opwwicketsettab where itickettypeid="
                            + stssz.getItickettypeid() + " and izticktypeid="
                            + stssz.getIztickettypeid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);

            Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
            String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
            if (lastcheckdate != null && !lastcheckdate.equals("")) {
                // 判断检票时间间隔
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                Date d1 = df.parse(lastcheckdate);
                calendar.setTime(d1);
                calendar.add(Calendar.SECOND, itimeinterval.intValue());

                if (now.before(calendar)) {
                    System.out.println("changeCheckTicket:-10");
                    rs.addRow(new String[] { "-10" });//
                    rs.addRow(new String[] { stss.getSztickettypename() });
                    rs.addRow(new String[] { stss.getMactualsaleprice()
                            .toString() });
                    rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                    rs.addRow(new String[] { stss.getSzcrowdkindname() });
                    rs.addRow(new String[] { stssz.getDtbegindate()
                            .substring(2) });
                    rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs.addRow(new String[] { "" });
                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }
            }

            String tripname = "";
            if (stssz.getTripid() > 0) {

                seq = checkDao.getZhuFaMaxNo(stssz.getTripid().toString());
                seq = String.valueOf(Long.parseLong(seq));

                Trip t = (Trip) checkDao.get(Trip.class, stssz.getTripid());
                tripname = t.getTripname();
            }
            String daytime = Tools.getDayTimes();
            // 验票方式
            // 检票流水

            // 只验票，更新售出门票
            // 一票一人
            String reserveDate = checkReserveDate(Long.parseLong(accid), szticketprintno);
            rs.addRow(new String[] { "3" });// 检票成功,放行
            rs.addRow(new String[] { stss.getSztickettypename() });
            rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
            rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
            rs.addRow(new String[] { stss.getSzcrowdkindname() });
            rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
            rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
            rs.addRow(new String[] { reserveDate });
            rs.addRow(new String[] { "" });
            rs.addRow(new String[] { stss.getSzmemo() });
            rs.addRow(new String[] { seq });
            rs.addRow(new String[] { tripname });

            rs = getchecklist(rs, stss);
            rs.addRow(new String[] { jpnumb1 });
            return rs;
        } else if (eticket.getByusage() == 1) {
            // 一票多人销售

            List opwwicketsettablist = checkDao
                    .find("from Opwwicketsettab where itickettypeid="
                            + stssz.getItickettypeid() + " and izticktypeid="
                            + stssz.getIztickettypeid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);
            Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
            String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
            if (lastcheckdate != null && !lastcheckdate.equals("")) {
                // 判断检票时间间隔
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                Date d1 = df.parse(lastcheckdate);
                calendar.setTime(d1);
                calendar.add(Calendar.SECOND, itimeinterval.intValue());
                if (stssz.getIpasstimes() == 0) {

                    if (now.before(calendar)) {
                        // 当前时间在间隔时间内
                        if (stssz.getMsingletimes() == stssz.getMsingledtimes()) {
                            // 单次 票数一检完
                            rs.addRow(new String[] { "-10" });//
                            rs.addRow(new String[] { stss.getSztickettypename() });
                            rs.addRow(new String[] { stss.getMactualsaleprice()
                                    .toString() });
                            rs.addRow(new String[] { stss.getIcrowdkindid()
                                    .toString() });
                            rs.addRow(new String[] { stss.getSzcrowdkindname() });
                            rs.addRow(new String[] { stssz.getDtbegindate()
                                    .substring(2) });
                            rs.addRow(new String[] { stssz.getDtenddate()
                                    .substring(2) });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs.addRow(new String[] { "" });
                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        }
                    }
                }
            }
            String daytime = Tools.getDayTimes();
            if (opww.getBywicketconsumetype().equals("01")) {
                String reserveDate = checkReserveDate(Long.parseLong(accid), szticketprintno);
                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
                rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                rs.addRow(new String[] { reserveDate });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs = getchecklist(rs, stss);

                rs.addRow(new String[] { jpnumb1 });
                return rs;

            } else if (opww.getBywicketconsumetype().equals("02")) {
                // 一检多人
                // 只检票
                Long jpnumb = new Long(0);
                for (int c = 0; c < zsstlist.size(); c++) {
                    stssz = (Stssoldticketsubtab) zsstlist.get(c);
                    Long synumb = stssz.getMsingletimes()
                            - stssz.getMsingledtimes();
                    if (stssz.getIpasstimes() > 0) {
                        // 限定次数的检票 不需要判断时间 间隔
                        // 剩余可检数量
                        if (synumb > stssz.getMpasstimes()) {
                            // 剩余可检数>每次检票数
                            jpnumb = stssz.getMpasstimes();

                        } else {
                            jpnumb = synumb;

                        }
                    } else if (stssz.getIpasstimes() == 0) {
                        if (lastcheckdate != null && !lastcheckdate.equals("")) {
                            // 判断检票时间间隔
                            SimpleDateFormat df = new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss");
                            Calendar calendar = Calendar.getInstance();
                            Date d1 = df.parse(lastcheckdate);
                            calendar.setTime(d1);
                            calendar.add(Calendar.SECOND,
                                    itimeinterval.intValue());
                            if (now.before(calendar)) {
                                // 间隔时间内
                                if (synumb > stssz.getMpasstimes()) {
                                    jpnumb = stssz.getMpasstimes();

                                } else {
                                    jpnumb = synumb;

                                }
                            } else {
                                // 间隔时间外
                                jpnumb = stssz.getMpasstimes();

                            }
                        } else {
                            // 第一次检票
                            jpnumb = stssz.getMpasstimes();

                        }
                    }
                }
                String reserveDate = checkReserveDate(Long.parseLong(accid), szticketprintno);
                rs.addRow(new String[] { "3" });// 检票成功,放行
                rs.addRow(new String[] { stss.getSztickettypename() });
                rs.addRow(new String[] { stss.getMactualsaleprice().toString() });
                rs.addRow(new String[] { stss.getIcrowdkindid().toString() });
                rs.addRow(new String[] { stss.getSzcrowdkindname() });
                rs.addRow(new String[] { stssz.getDtbegindate().substring(2) });
                rs.addRow(new String[] { stssz.getDtenddate().substring(2) });
                rs.addRow(new String[] { reserveDate });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { stss.getSzmemo() });
                rs.addRow(new String[] { "" });
                rs.addRow(new String[] { "" });
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb.toString() });
                return rs;

            }
        }

        return rs;
    }

    public String checkReserveDate(Long accid,String szticketprintno) throws ParseException {
        //判断是否有预约通道
        List channels = checkDao.find("from Reservechannel where channelid ="+accid);
        if(channels != null && !channels.isEmpty()){
            IReserveorderService reserveorderService = (IReserveorderService) SpringUtil.getBean("reserveorderService");
            //查看票号预约数据
            Reserveorderinfo reserveorderinfo = reserveorderService.selectOneByOrid(szticketprintno);
            if(reserveorderinfo == null){
                //查看订单预约时间
                List slist = checkDao.find("select new map(s.szsalesvoucherno as orid) from Stssalesvouchertab s,Stssoldtickettab st where " +
                        "s.id.isalesvoucherid = st.id.isalesvoucherid and s.id.iticketstationid = st.id.iticketstationid " +
                        "and st.szticketprintno = '"+szticketprintno+"' ");
                if(slist != null && !slist.isEmpty()){
                    Map map = (Map) slist.get(0);
                    String orid = map.get("orid").toString();
                    reserveorderinfo = reserveorderService.selectOneByOrid(orid);
                }
            }
            if(reserveorderinfo != null){
                String dataJson = reserveorderinfo.getDatajson();
                ReserveInfo reserveInfo = JSON.parseObject(dataJson, ReserveInfo.class);
                List<ReserveDate> reserveDates = reserveInfo.getDates();
                if(reserveDates != null && !reserveDates.isEmpty()){
                    for(ReserveDate reserveDate : reserveDates){
                        //预约时间判断
                        return reserveDate.getBeginDate()+" "+reserveDate.getBeginTime()+"至"+reserveDate.getEndTime();

                    }
                }
            }
        }
        return "";
    }

    public boolean isyw(String printno) {

        boolean flag = true;
        List stslist = checkDao
                .find(" from Stssoldtickettab where szticketprintno='"
                        + printno + "' order by dtmakedate desc");
        Stssoldtickettab stss = (Stssoldtickettab) stslist.get(0);
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss.getId().getIticketstationid()
                        + "    and id.isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid="
                        + stss.getId().getSzsoldticketid() + "  and isvalid=1");

        for (int i = 0; i < zsstlist.size(); i++) {
            Stssoldticketsubtab zstss = (Stssoldticketsubtab) zsstlist.get(i);

            if (zstss.getIpasstimes().longValue() == 0) {
                // 有无限次入园
                flag = false;
            } else {
                if (zstss.getIpasstimes().longValue() > zstss.getIpassedtimes()
                        .longValue()) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public ResultBean changeCheckfanxin(String accid, String carno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist == null || acclist.size() == 0) {
            rs.addRow(new String[] { "-1" });
        }
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // 查询是员工卡
        List dlist = checkDao
                .find(" from Opcemployeecardtab where upper(icardno)='"
                        + carno.toUpperCase()
                        + "' and (byisdaoyou=0 or byisdaoyou is null) and byticketstate=0");
        if (dlist == null || dlist.size() == 0) {

            rs.addRow(new String[] { "-1" });// 不是准进卡

        } else {

            Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);

            List dzlist = checkDao
                    .find(" from Opcemployeecardsubtab where iemployeecardid="
                            + op.getIemployeecardid() + " and igardengateid="
                            + acc.getId().getIgardengateid());
            if (dzlist == null || dzlist.size() == 0) {
                rs.addRow(new String[] { "-2" });// 该准进卡不能通过该园门
            } else {

                if (op.getIagentno().equals("1")) {
                    System.out.println("放行");
                    rs.addRow(new String[] { "3" });// 放行
                } else {
                    System.out.println("没有放行权限");
                    rs.addRow(new String[] { "-2" });// 没有放行权限
                }
            }
        }

        rs.addRow(new String[] { "22" });// 员工卡
        return rs;
    }

    public void updatestatus(Long accid, String typestatus, Long byisuse) {
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

        List stulist = checkDao
                .find(" from Esbaccessstatustab where  istatusgardid="
                        + acc.getId().getIaccessequipid() + " and  iscenicid="
                        + acc.getId().getIscenicid() + " and istatuswinid="
                        + acc.getId().getIgardengateid()
                        + " and statusbtype='01' and typestatus='" + typestatus
                        + "'");
        if (stulist == null || stulist.size() == 0) {
            Esbaccessstatustab esbstatus = new Esbaccessstatustab();
            Long seq = checkDao.getMaxPk("seq", "Esbaccessstatustab");
            esbstatus.setSeq(seq + 1);
            esbstatus.setByisuse(byisuse);
            esbstatus.setIscenicid(acc.getId().getIscenicid());
            esbstatus.setIstatusgardid(acc.getId().getIaccessequipid());
            esbstatus.setIstatuswinid(acc.getId().getIgardengateid());
            esbstatus.setStatusbtype("01");
            esbstatus.setTypestatus(typestatus);
            esbstatus.setStatusdtime(Tools.getDayTimes());
            checkDao.save(esbstatus);
        } else {
            Esbaccessstatustab esbstatus = (Esbaccessstatustab) stulist.get(0);
            esbstatus.setByisuse(byisuse);
            esbstatus.setStatusdtime(Tools.getDayTimes());
            checkDao.update(esbstatus);
        }
    }

    public List getprintno(Long accid) {
        String printno = "";
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List printnolist = new ArrayList();
        try {
            printnolist = checkDao
                    .findBySqlToMap("select * from (select s.szticketprintno from stssoldtickettab s,Edmtickettypetab t,stssoldticketsubtab st where s.dtstartdate<='"
                            + Tools.getTodayString()
                            + "' and s.dtenddate>='"
                            + Tools.getTodayString()
                            + "' and s.itickettypeid=t.itickettypeid and ( t.bymaketicketway='00'  or ( t.bymaketicketway='01' and t.bymediatype='00')) and s.isalesvoucherid=st.isalesvoucherid and s.iticketstationid=st.iticketstationid and s.szsoldticketid=st.szsoldticketid and st.igardengateid="
                            + acc.getId().getIgardengateid()
                            + " and st.isvalid=1 and   ( st.ipasstimes>st.ipassedtimes or (st.ipasstimes=0 and st.ipassedtimes=0 )) order by s.isalesvoucherid)  where rownum<100 ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return printnolist;
    }

	public void savechecklink(String accid, String printno) {
		List acclist = checkDao.find(" from Esbaccessequiptab where id.iaccessequipid=" + accid);

		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

		List sstlist = checkDao.find(
				"select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where ( szticketprintno='"
						+ printno + "'  or myzj='" + printno
						+ "') and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid 	order by s.dtmakedate desc");
		Stssoldtickettab stss = null;

		if (sstlist != null || sstlist.size() > 0) {
			stss = new Stssoldtickettab();
			try {
				BeanUtils.populate(stss, (Map) sstlist.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(Edmtickettypetab.class, stss.getItickettypeid());
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

		String daytime = Tools.getDayTimes();

		List linklist = checkDao.find(" from Gardengatelink where isvalid=1 and iscenicid=" + acc.getId().getIscenicid()
				+ " and igardengateid=" + acc.getId().getIgardengateid());
		for (int l = 0; l < linklist.size(); l++) {
			Gardengatelink glink = (Gardengatelink) linklist.get(l);

			StringBuffer sql = new StringBuffer("from Stssoldticketsubtab where  id.isalesvoucherid="
					+ stss.getIsalesvoucherid() + " and id.iticketstationid=" + stss.getIticketstationid()
					+ "   and  id.isalesvoucherdetailsid=" + stss.getIsalesvoucherdetailsid()
					+ " and id.szsoldticketid=" + stss.getSzsoldticketid() + " and igardengateid="
					+ glink.getLigardengateid() + " and isvalid not in (0,-1)");

			if (glink.getInoteger1() != null && glink.getInoteger1() != 0L) {
				sql.append(" and iztickettypeid=" + glink.getInoteger1());
			}

			List lzsstlist = checkDao.find(sql.toString());
			boolean b = true;
			for (int c = 0; c < lzsstlist.size(); c++) {
				Stssoldticketsubtab stssz = (Stssoldticketsubtab) lzsstlist.get(c);
				if (stssz.getIpasstimes() == 0) {
					b = false;
				}
			}
			if (b) {
				if (glink.getLinktype().equals("01")) {
					// 互斥
					for (int c = 0; c < lzsstlist.size(); c++) {
						Stssoldticketsubtab stssz = (Stssoldticketsubtab) lzsstlist.get(c);
						stssz.setIsvalid(0L);
						checkDao.update(stssz);
					}
				}
				if (glink.getLinktype().equals("02")) {
					// 同检
					if (eticket.getByusage() != -1)/*
													 * { // 销售方式是一票一人 for (int c = 0; c < lzsstlist.size(); c++) {
													 * Stssoldticketsubtab stssz = (Stssoldticketsubtab) lzsstlist
													 * .get(c); stssz.setIpassedtimes(stssz.getIpassedtimes() +
													 * stssz.getMpasstimes()); stssz.setDtlastcheckdate(daytime);
													 * stssz.setDtmakedate(daytime); stssz.setByisout(new Long(1));
													 * checkDao.update(stssz); } } else
													 */ {
						// 销售方式是一票多人
						if (lzsstlist != null && lzsstlist.size() > 0) {
							Stssoldticketsubtab stssz = (Stssoldticketsubtab) lzsstlist.get(0);
							List opwwicketsettablist = checkDao.find("from Opwwicketsettab where itickettypeid="
									+ stssz.getItickettypeid() + " and izticktypeid=" + stssz.getIztickettypeid()
									+ " and igardengateid=" + glink.getLigardengateid());
							Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);

							Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
							String lastcheckdate = stssz.getDtlastcheckdate();
							if (opww.getBywicketconsumetype().equals("01")) {
								// 一检一人
								for (int c = 0; c < lzsstlist.size(); c++) {
									stssz = (Stssoldticketsubtab) lzsstlist.get(c);
									if (stssz.getIpasstimes() > 0) {
										// 限定次数的检票 不需要判断时间 间隔
										stssz = (Stssoldticketsubtab) lzsstlist.get(c);
										stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
										stssz.setMsingledtimes(stssz.getMsingledtimes() + +stssz.getMpasstimes());
										stssz.setDtlastcheckdate(daytime);
										stssz.setDtmakedate(daytime);
										stssz.setByisout(new Long(1));
										checkDao.update(stssz);

									} else if (stssz.getIpasstimes() == 0) {
										if (lastcheckdate != null && !lastcheckdate.equals("")) {
											// 判断检票时间间隔
											SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Calendar calendar = Calendar.getInstance();
											Date d1;
											try {
												d1 = df.parse(lastcheckdate);

												calendar.setTime(d1);
											} catch (ParseException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											calendar.add(Calendar.SECOND, itimeinterval.intValue());
											if (now.before(calendar)) {
												// 间隔时间内

												stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
												stssz.setMsingledtimes(
														stssz.getMsingledtimes() + stssz.getMpasstimes());
												stssz.setDtmakedate(daytime);
												stssz.setByisout(new Long(1));
												checkDao.update(stssz);
											} else {
												// 间隔时间外
												stssz = (Stssoldticketsubtab) lzsstlist.get(c);
												stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
												stssz.setMsingledtimes(stssz.getMpasstimes());
												stssz.setDtlastcheckdate(daytime);
												stssz.setDtmakedate(daytime);
												stssz.setByisout(new Long(1));
												checkDao.update(stssz);
											}
										} else {
											// 第一次检票
											stssz = (Stssoldticketsubtab) lzsstlist.get(c);
											stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
											stssz.setMsingledtimes(stssz.getMsingledtimes() + stssz.getMpasstimes());
											stssz.setDtlastcheckdate(daytime);
											stssz.setDtmakedate(daytime);
											stssz.setByisout(new Long(1));
											checkDao.update(stssz);
										}
									}
								}
							} else if (opww.getBywicketconsumetype().equals("02")) {
								// 一检多人
								// if (opww.getByregfingerprinttype().equals("00"))
								// {
								// 只检票
								Long jpnumb = new Long(0);
								for (int c = 0; c < lzsstlist.size(); c++) {
									stssz = (Stssoldticketsubtab) lzsstlist.get(c);
									Long synumb = stssz.getMsingletimes() - stssz.getMsingledtimes();
									if (stssz.getIpasstimes() > 0) {
										// 限定次数的检票 不需要判断时间 间隔
										// 剩余可检数量
										if (synumb > stssz.getMpasstimes()) {
											// 剩余可检数>每次检票数
											jpnumb = stssz.getMpasstimes();
											stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
											stssz.setMsingledtimes(stssz.getMsingledtimes() + stssz.getMpasstimes());
											stssz.setDtlastcheckdate(daytime);
											stssz.setDtmakedate(daytime);
											stssz.setByisout(new Long(1));
											checkDao.update(stssz);
										} else {
											jpnumb = synumb;
											stssz.setIpassedtimes(stssz.getIpassedtimes() + synumb);
											stssz.setMsingledtimes(stssz.getMsingledtimes() + synumb);
											stssz.setDtlastcheckdate(daytime);
											stssz.setDtmakedate(daytime);
											stssz.setByisout(new Long(1));
											checkDao.update(stssz);
										}
									} else if (stssz.getIpasstimes() == 0) {
										if (lastcheckdate != null && !lastcheckdate.equals("")) {
											// 判断检票时间间隔
											SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Calendar calendar = Calendar.getInstance();
											Date d1;
											try {
												d1 = df.parse(lastcheckdate);

												calendar.setTime(d1);
											} catch (ParseException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											calendar.add(Calendar.SECOND, itimeinterval.intValue());
											if (now.before(calendar)) {
												// 间隔时间内
												if (synumb > stssz.getMpasstimes()) {
													jpnumb = stssz.getMpasstimes();
													stssz.setIpassedtimes(
															stssz.getIpassedtimes() + stssz.getMpasstimes());
													stssz.setMsingledtimes(
															stssz.getMsingledtimes() + stssz.getMpasstimes());
													stssz.setDtmakedate(daytime);
													stssz.setByisout(new Long(1));
													checkDao.update(stssz);
												} else {
													jpnumb = synumb;
													stssz.setIpassedtimes(stssz.getIpassedtimes() + synumb);
													stssz.setMsingledtimes(stssz.getMsingledtimes() + synumb);
													stssz.setDtmakedate(daytime);
													stssz.setByisout(new Long(1));
													checkDao.update(stssz);
												}
											} else {
												// 间隔时间外
												jpnumb = stssz.getMpasstimes();
												stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
												stssz.setMsingledtimes(stssz.getMpasstimes());
												stssz.setDtlastcheckdate(daytime);
												stssz.setDtmakedate(daytime);
												stssz.setByisout(new Long(1));
												checkDao.update(stssz);
											}
										} else {
											// 第一次检票
											jpnumb = stssz.getMpasstimes();
											stssz.setIpassedtimes(stssz.getIpassedtimes() + stssz.getMpasstimes());
											stssz.setMsingledtimes(stssz.getMsingledtimes() + stssz.getMpasstimes());
											stssz.setDtlastcheckdate(daytime);
											stssz.setDtmakedate(daytime);
											stssz.setByisout(new Long(1));
											checkDao.update(stssz);
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

    /**
     * 根据票号判断闸机是否能检该票 100 非一单一检 101 一单一检 其他是不能检票返回的数据
     */

    public ResultBean checktorder(String accid, String printno,String url)
            throws Exception {
    	//判断域名
    	if(url==null || url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        System.out.println("checktorder1");
        CYTPojo pojo = new CYTPojo();

        pojo.isCYT = false;
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            rs.addRow(new String[] { "-3" });// 此设备不能检该票
            return rs;
        }
        System.out.println("checktorder2");
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        Long iscenicid = acc.getId().getIscenicid();
        Long igardengateid = acc.getId().getIgardengateid();
        Hotelprovider prdsvc = (Hotelprovider) checkDao.get(
                Hotelprovider.class, iscenicid);
        if (prdsvc != null && prdsvc.getInoteger3() != null
                && prdsvc.getInoteger3().intValue() == 1) {
            // 允许在线出票
        } else {
            rs.addRow(new String[] { "-1" });// 无效票
            return rs;
        }
        System.out.println("checktorder3");
        T_order t_order = null;
        List<T_orderlist> listorder = new ArrayList<T_orderlist>();
        List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
        List<Trealname> Trealnamelist = new ArrayList<Trealname>();
        try {
//            javax.xml.rpc.Service service = null;
//
//            java.net.URL endpointURL = new java.net.URL("http://"
//                    + url
//                    + "/services/centersaleService?wsdl");
//            CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
//                    endpointURL, service);
//            ssl.setMaintainSession(true);
//            //这里待修改
//            com.ectrip.ticket.centersale.client.ResultBean cano = ssl.getT_orderauto(
//                    printno, iscenicid);
        	ResultBean cano = saleCenterService.getT_orderauto(printno, iscenicid);
        	
            System.out.println("cano.getRowsCount()=" + cano.getRowsCount());
            if (cano.getRowsCount() == 1) {
                for (int i = 0; i < cano.getRowsCount(); i++) {

                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));

                    }
                    t_order =  (T_order) saleCenterService.convertMap(
                            T_order.class, returnMap);
                    if (!Calendar.getInstance().getTime().after(new SimpleDateFormat("yyyy-MM-dd").parse(t_order.getDtstartdate()))) {
                        System.out.println("只有在首日浏览日期之后才能在闸机上出票检票");
                        rs.addRow(new String[] { "-4" });// 未到检票时间
                        return rs;
                    }
                    if (t_order.getOrid().matches("^\\d{8}888\\d{6}")
                            || t_order.getOrid().matches("^\\d{8}999\\d{6}")) {// 核销

                        pojo = commonUtil.findCYTPojo(t_order.getOrid(),url);

                        if (true) {

                            //cano = ssl.cythexiao(t_order.getOrid());
                        	cano = this.cythexiao(t_order.getOrid(),null);
                            if (cano == null
                                    || !"true".equalsIgnoreCase(cano.getResult(
                                    0, "returnstats"))) {
                                rs.addRow(new String[] { "false",
                                        cano.getResult(0, "message") });
                                return rs;
                            }
                        }
                    }
                }
            } else {
                rs.addRow(new String[] { "-1" });// 无效票
                return rs;
            }

           // cano = ssl.chupiaoT_orderlist(t_order.getOrid(), iscenicid);
            cano = saleCenterService.ChupiaoT_orderlist(t_order.getOrid(), iscenicid);
            System.out.println("cano.getRowsCount()2=" + cano.getRowsCount());
            if (cano.getRowsCount() >= 1) {
                for (int i = 0; i < cano.getRowsCount(); i++) {

                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));

                    }

                    T_orderlist t1 = (T_orderlist) saleCenterService
                            .convertMap(T_orderlist.class, returnMap);

                    Edmtickettypetab eticket = saleCenterService
                            .getEdmtickettypetab(new Long(t1.getItickettypeid()));

                    if (eticket == null) {
                        System.out.println("eticket==null");
                    }

                    t1.setBymaketicketway(eticket.getBymaketicketway());

                    listorder.add(t1);

                }
            } else {
                rs.addRow(new String[] { "-1" });// 无效票
                return rs;
            }

//            cano = ssl.chupiaoT_zorderlist(t_order.getOrid(), iscenicid);
            cano = saleCenterService.ChupiaoT_zorderlist(t_order.getOrid(), iscenicid);
            System.out.println("cano.getRowsCount()3=" + cano.getRowsCount());
            if (cano.getRowsCount() > 0) {
                for (int i = 0; i < cano.getRowsCount(); i++) {
                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));
                    }
                    T_zorderlist t2 = (T_zorderlist) saleCenterService
                            .convertMap(T_zorderlist.class, returnMap);
                    if (Tools.getDayTimes().compareTo(t2.getDtenddate()) > 0) {
                        rs.addRow(new String[] { "-1" });
                        return rs;
                    }
                    listzorder.add(t2);
                }
            } else {
                rs.addRow(new String[] { "-1" });// 无效票
                return rs;
            }
            System.out.println("cano.getRowsCount()4=" + cano.getRowsCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rs.addRow(new String[] { "-1" });// 无效票
            return rs;
        }
        
        
        List<Esbticketwintab> es = null;
        if(pojo.isCYT) {
        	es = checkDao
                    .find("from Esbticketwintab e where exists(select s.iticketstationid from Esbticketstationtab s where s.iscenicid=? and s.byisuse=1 and e.iticketstationid=s.iticketstationid) and e.byisuse=1 and e.szticketwinname like ?",
                            new Object[]{iscenicid, "%CYT%"});
        }else {
        	es = checkDao
            .find("from Esbticketwintab e where exists(select s.iticketstationid from Esbticketstationtab s where s.iscenicid=? and s.byisuse=1 and e.iticketstationid=s.iticketstationid) and e.byisuse=1 ",
                    new Object[]{iscenicid});
        }
        if (es == null || es.size() == 0) {
            rs.addRow(new String[]{"-1"});// 无效票
            return rs;
        }
        Esbticketwintab win = es.get(0);
        List<Esfemployeetab> ees =  null;
        if(pojo.isCYT) {
//        	ees = checkDao
//                    .find("from Esfemployeetab e where e.byisuse=1 and e.szemployeename like ? and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))",
//                            new Object[]{"%CYT%", iscenicid});
        	ees = sysService.getEsfemployeeByGalcompanyScenicids(iscenicid, "%CYT%");
        }else {
        	ees = sysService.getEsfemployeeByGalcompanyScenicid(iscenicid);
//        	ees = checkDao
//            .find("from Esfemployeetab e where e.byisuse=1 and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))",
//                    new Object[]{iscenicid});
        }
        if (ees == null || ees.size() == 0) {
            rs.addRow(new String[]{"-1"});// 无效票
            return rs;
        }
        Esfemployeetab emp = ees.get(0);

        Long iticketwinid = win.getIticketwinid();
        Long iemployeeid = emp.getIemployeeid();

        Long maxid = saleCenterService.updatevouchersequence();
        ResultBean r = saleCenterService.checksavetorder(t_order, listorder,
                listzorder, iemployeeid, iticketwinid, maxid, igardengateid,
                printno,url);

        if ("3".equalsIgnoreCase(r.getResult(0,0))
                && pojo.isCYT) {
            try {
                addCYTPojo(pojo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(t_order!=null){
            if(!StringUtil.isEmpty(t_order.getOrhm()) && !StringUtil.isEmpty(t_order.getOrnm())){
                r.addRow(new String[]{t_order.getOrhm()});
                r.addRow(new String[]{t_order.getOrnm()});
            }
        }
        return r;
    }


    public ResultBean querychecktype(String accid, String printno) throws Exception
    {
        return querychecktype(accid,printno,"");
    }

    /**
     * 根据票号判断闸机是否能检该票 100 非一单一检 101 一单一检 其他是不能检票返回的数据
     */

    public ResultBean querychecktype(String accid, String printno,String checkType)
            throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        // 读取闸机信息
        String tickettypename = "";// 票名称
        String mactualsaleprice = "";// 单价
        String icrowdkindid = "";// 人群id
        String szcrowdkindname = "";// 人群名称
        String dtbegindate = "";// 开始时间
        String dtenddate = "";// 截至时间
        String bsfilebinary = "";// 指纹信息
        String szimagepath = "";// 相片路径
        String szmemo = "";// 语音路径
        String seq = "";// 竹筏流水
        String tripname = "";// 趟次名称
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效闸机";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);
            return rs;
        }

        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        // System.out.println("changeCheckTicket1");
        // 查询售出门票表
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign,s.dtmakedate as dtmakedate) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where szticketprintno='"
                        + printno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid 	order by s.dtmakedate desc");
        Stssoldtickettab stss = null;

        if (sstlist == null || sstlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效票";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            return rs;
        } else {
            stss = new Stssoldtickettab();
            try {
                BeanUtils.populate(stss, (Map) sstlist.get(0));
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tickettypename = stss.getSztickettypename();
            mactualsaleprice = stss.getMactualsaleprice().toString();
            icrowdkindid = stss.getIcrowdkindid().toString();
            szcrowdkindname = stss.getSzcrowdkindname();
            szmemo = stss.getSzmemo();

            if (stss.getByvalidity().equals("01")) {
                rs.addRow(new String[] { "-2" });// 票已退订或挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                return rs;
            }
            if (stss.getByvalidity().equals("02")) {
                rs.addRow(new String[] { "-99" });// 已挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                return rs;
            }
        }

        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss.getIticketstationid()
                        + "   and  id.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and isvalid not in (-1,0)");
        if (zsstlist == null || zsstlist.size() == 0) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }

        Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                Edmtickettypetab.class, stss.getItickettypeid());



        // 验证首次 进门与买票的间隔时间
        System.out.println("验证首次 进门与买票的间隔时间");
        int yztime = yzchuciyanpiaotime(stss);
        System.out.println("yztime=" + yztime);
        if (yztime == 0) {
            rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
//		updateyouxiaoqiwys(stss);
        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(0);
//        IVerifycheckService verifycheckService = (IVerifycheckService) SpringUtil
//                .getBean("verifycheckService");
        System.out.println("检票事前验证");
        int vi = verifycheckService.Verifyticket(stssz);
        if (vi != 0) {
            rs.addRow(new String[] { String.valueOf(vi) });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        System.out.println("检票事前验证1");
        dtbegindate = stssz.getDtbegindate().substring(2);
        dtenddate = stssz.getDtenddate().substring(2);
        String jpnumb1 = "1";
        if (stssz.getMpasstimes() != null) {
            jpnumb1 = stssz.getMpasstimes().toString();
        }
        System.out.println("检票事前验证2");
        List opwwicketsettablist = checkDao
                .find("from Opwwicketsettab where itickettypeid="
                        + stssz.getItickettypeid() + " and izticktypeid="
                        + stssz.getIztickettypeid() + " and igardengateid="
                        + acc.getId().getIgardengateid() + " and byisuse=1");
        Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);
        System.out.println("检票事前验证3");
        if (stssz.getIpasstimes() > 0) {
            if (stssz.getIpasstimes() - stssz.getIpassedtimes() <= 0) {
                //新增人脸识别放行
                if(!StringUtil.isEmpty(checkType) && checkType.equals("04") && opww.getByregfingerprinttype().equals("01"))
                {

                }else {
                    rs.addRow(new String[]{"-9"});// 已检过

                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                            bsfilebinary, szimagepath, szmemo, seq, tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[]{"0"});
                    return rs;
                }
            }
        } else {
            System.out.println("检票事前验证4");

            if (eticket.getByusage() == 0) {
                Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
                String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
                
                if (lastcheckdate != null && !lastcheckdate.equals("")) {
                    // 判断检票时间间隔
                    SimpleDateFormat df = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    Date d1 = df.parse(lastcheckdate);
                    calendar.setTime(d1);
                    calendar.add(Calendar.SECOND, itimeinterval.intValue());
                    if (now.before(calendar)) {
                        System.out.println("changeCheckTicket:-10");
                        rs.addRow(new String[] { "-10" });//
                        rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                                icrowdkindid, szcrowdkindname, dtbegindate,
                                dtenddate, bsfilebinary, szimagepath, szmemo,
                                seq, tripname);

                        rs = getchecklist(rs, stss);
                        rs.addRow(new String[] { jpnumb1 });
                        return rs;
                    }
                }

            } else if (eticket.getByusage() == 1) {
                Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
                String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
                if (lastcheckdate != null && !lastcheckdate.equals("")) {
                    // 判断检票时间间隔
                    SimpleDateFormat df = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    Date d1 = df.parse(lastcheckdate);
                    calendar.setTime(d1);
                    calendar.add(Calendar.SECOND, itimeinterval.intValue());

                    if (now.before(calendar)) {
                        // 当前时间在间隔时间内
                        if (stssz.getMsingletimes() == stssz.getMsingledtimes()) {
                            // 单次 票数一检完
                            rs.addRow(new String[] { "-10" });//
                            rs = getcanshu11(rs, tickettypename,
                                    mactualsaleprice, icrowdkindid,
                                    szcrowdkindname, dtbegindate, dtenddate,
                                    bsfilebinary, szimagepath, szmemo, seq,
                                    tripname);

                            rs = getchecklist(rs, stss);
                            rs.addRow(new String[] { jpnumb1 });
                            return rs;
                        }
                    }

                }
            }
            //
        }
        List xgjplist = checkDao
                .find(" from Esbgardengatetickettab where igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and ( itickettypeoneid="
                        + eticket.getItickettypeid() + " or itickettypetwoid="
                        + eticket.getItickettypeid() + ")");
        if (xgjplist != null && xgjplist.size() > 0) {
            rs.addRow(new String[] { "-8" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        System.out.println("检票事前验证5");
        System.out.println("opww.getBywicketconsumetype()="
                + opww.getBywicketconsumetype());
        if (opww.getBywicketconsumetype() != null
                && opww.getBywicketconsumetype().equals("03")) {
            // 返回101标识一单一检
            rs.addRow(new String[] { "101" });
            return rs;
        } else {
            // 不是一单一检
            System.out.println("检票事前验证6");
            rs.addRow(new String[] { "100" });
            return rs;
        }
    }

    public ResultBean getcanshu11(ResultBean rs, String tickettypename,
                                  String mactualsaleprice, String icrowdkindid,
                                  String szcrowdkindname, String dtbegindate, String dtenddate,
                                  String bsfilebinary, String szimagepath, String szmemo, String seq,
                                  String tripname) throws Exception {
        rs.addRow(new String[] { tickettypename });
        rs.addRow(new String[] { mactualsaleprice });
        rs.addRow(new String[] { icrowdkindid });
        rs.addRow(new String[] { szcrowdkindname });
        rs.addRow(new String[] { dtbegindate });
        rs.addRow(new String[] { dtenddate });
        rs.addRow(new String[] { bsfilebinary });
        rs.addRow(new String[] { szimagepath });
        rs.addRow(new String[] { szmemo });
        rs.addRow(new String[] { seq });
        rs.addRow(new String[] { tripname });
        return rs;

    }

    public ResultBean getchecklist(ResultBean rs, Stssoldtickettab stss)
            throws Exception {
        List cklist = checkDao
                .findBySqlToMap("select * from ( select g.szgardengatename,s.dtmakedate,s.zfseq  from Ticketchecklist s,Esbgardengatetab g where s.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and  s.iticketstationid="
                        + stss.getIticketstationid()
                        + " and s.szsoldticketid="
                        + stss.getSzsoldticketid()
                        + " and s.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and s.igardengateid=g.igardengateid and s.iscenicid=g.iscenicid order by dtmakedate　desc ) where rownum<10");
        if (cklist.size() >= 9) {
            for (int i = 0; i < 9; i++) {
                Map map1 = (Map) cklist.get(i);
                rs.addRow(new String[] { map1.get("SZGARDENGATENAME") + "  "
                        + map1.get("DTMAKEDATE").toString().substring(2) });
            }
        } else {
            for (int i = 0; i < cklist.size(); i++) {
                Map map1 = (Map) cklist.get(i);
                rs.addRow(new String[] { map1.get("SZGARDENGATENAME") + "  "
                        + map1.get("DTMAKEDATE").toString().substring(2) });
            }
            for (int i = 0; i < 9 - cklist.size(); i++) {
                rs.addRow(new String[] { " " });
            }
        }
        return rs;
    }

    /**
     * 检票内部方法
     *
     * @param acc
     * @param stss
     * @param daytime
     * @param OtherString
     * @return
     * @throws Exception
     */
    public Ticketchecklist getTicketchecklist(Esbaccessequiptab acc,
                                              Stssoldtickettab stss, String daytime, Long mpasstimes,
                                              String... OtherString) throws Exception {
        Ticketchecklist checkt = new Ticketchecklist();

        List<Map> iserialnumlist = new ArrayList();
        iserialnumlist = checkDao
                .findBySqlToMapnocolsesession("select check_id.nextval  from dual");
        Long checkid = new Long(
                (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());

        checkt.setCheckid(checkid);
        checkt.setIgardengateid(acc.getId().getIgardengateid());
        checkt.setIaccessequipid(acc.getId().getIaccessequipid());
        checkt.setIscenicid(acc.getId().getIscenicid());
        checkt.setIsalesvoucherid(stss.getIsalesvoucherid());
        checkt.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
        checkt.setIticketstationid(stss.getIticketstationid());
        checkt.setSzsoldticketid(stss.getSzsoldticketid());
        checkt.setDtmakedate(daytime);
        checkt.setInt1(mpasstimes);
        checkt.setInt2(0L);
        checkt.setAccounttime("1");
        // 李进修改开始

        if (OtherString.length > 1) {
            checkt.setChuanhao(OtherString[0]);
        }
        if (OtherString.length > 2) {
            checkt.setTongxingzhi(OtherString[1]);
        }

        if (OtherString.length > 3) {
            checkt.setNote1(OtherString[2]);
        }
        if (OtherString.length > 4) {
            checkt.setNote2(OtherString[3]);
        }

        return checkt;
    }

    /**
     * 根据闸机id和票号查询同一园门下该订单所有能通过这个园门的所有票号
     *
     * @param accid
     * @param printno
     * @return
     * @throws Exception
     */
    public List queryprintnolist(String accid, String printno) throws Exception {
        List printnolist = new ArrayList();
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List stsslsit = checkDao
                .find(" from Stssoldtickettab s where s.szticketprintno='"
                        + printno
                        + "' and s.byvalidity='00' order by s.dtmakedate desc");
        Stssoldtickettab stss = (Stssoldtickettab) stsslsit.get(0);
        printnolist = checkDao
                .findBySqlToMap("select distinct s.szticketprintno  from stssoldtickettab s,stssoldticketsubtab st where s.isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and s.iticketstationid="
                        + stss.getId().getIticketstationid()
                        + " and  st.igardengateid="
                        + acc.getId().getIgardengateid()
                        + "  and  s.isalesvoucherid=st.isalesvoucherid and s.isalesvoucherdetailsid=st.isalesvoucherdetailsid and s.iticketstationid=st.iticketstationid and s.szsoldticketid=st.szsoldticketid");

        return printnolist;
    }

    /**
     * 根据票信息读取Edmticketproduct的是否首次激活更新有效期=1 更新所有该票号的所有园门有效期
     *
     * @param stss
     */
    public void updateyouxiaoqi(Stssoldtickettab stss) {
        // 查询

        Edmticketproduct ep = (Edmticketproduct) this.checkDao.get(
                Edmticketproduct.class, stss.getItickettypeid());
        Edmtickettypetab ticket = (Edmtickettypetab) this.checkDao.get(Edmtickettypetab.class,
                stss.getItickettypeid());
        boolean b = false;//套票中子票有效期设置  false:按套票算   true:按子票算
        if(ticket.getInt1() == null || ticket.getInt1().longValue() == 0L){
            if (ep != null && ep.getInoteger2() != null && ep.getInoteger2() == 1) {
                if(ticket.getBycategorytype().equals("0010")){
                    if(ep.getInoteger1() != null && ep.getInoteger1().longValue() == 1L){
                        b = true;
                    }
                }
                List szsstlist = checkDao
                        .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                                + stss.getIsalesvoucherid()
                                + " and id.iticketstationid="
                                + stss.getIticketstationid()
                                + "   and  id.isalesvoucherdetailsid="
                                + stss.getIsalesvoucherdetailsid()
                                + " and id.szsoldticketid="
                                + stss.getSzsoldticketid() + " and ipassedtimes>0");
                if (szsstlist == null || szsstlist.size() == 0) {
                    // 表示一次票没有检过

                    String startdate = Tools.getTodayString();
                    Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                            Edmtickettypetab.class, stss.getItickettypeid());
                    StssoldtickettabId id = new StssoldtickettabId();
                    id.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
                    id.setIsalesvoucherid(stss.getIsalesvoucherid());
                    id.setIticketstationid(stss.getIticketstationid());
                    id.setSzsoldticketid(stss.getSzsoldticketid());
                    Stssoldtickettab st = (Stssoldtickettab) this.checkDao.get(
                            Stssoldtickettab.class, id);
                    String enddate = Tools.getDate(startdate, eticket
                            .getValidityday().intValue() - 1);
                    st.setDtstartdate(startdate);
                    st.setDtenddate(enddate);

                    this.checkDao.update(st);

                    List ssubtlist = checkDao
                            .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                                    + stss.getIsalesvoucherid()
                                    + " and id.iticketstationid="
                                    + stss.getIticketstationid()
                                    + "   and  id.isalesvoucherdetailsid="
                                    + stss.getIsalesvoucherdetailsid()
                                    + " and id.szsoldticketid="
                                    + stss.getSzsoldticketid()
                                    + "  and isvalid not in (-1,0)");
                    for (int a = 0; a < ssubtlist.size(); a++) {

                        Stssoldticketsubtab stsub = (Stssoldticketsubtab) ssubtlist
                                .get(a);
                        if(b){
                            Edmtickettypetab tt = (Edmtickettypetab) this.checkDao.get(Edmtickettypetab.class,
                                    stsub.getIztickettypeid());
                            stsub.setDtbegindate(Tools.getDayTimes());
                            stsub.setDtenddate(Tools.getDate(startdate, tt
                                    .getValidityday().intValue() - 1) + " 23:59:59");
                        }else{
                            stsub.setDtbegindate(Tools.getDayTimes());
                            stsub.setDtenddate(enddate + " 23:59:59");
                        }

                        this.checkDao.update(stsub);
                    }

                }
            }
        }
    }

    public void updateyouxiaoqiwys(Stssoldtickettab stss) {
        // 查询

        Edmtickettypetab ep = (Edmtickettypetab) this.checkDao.get(
                Edmtickettypetab.class, stss.getItickettypeid());
        if (ep != null && ep.getInt1() != null && ep.getInt1() == 1) {

            List szsstlist = checkDao
                    .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                            + stss.getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stss.getIticketstationid()
                            + "   and  id.isalesvoucherdetailsid="
                            + stss.getIsalesvoucherdetailsid()
                            + " and id.szsoldticketid="
                            + stss.getSzsoldticketid() + " and ipassedtimes>0");
            if (szsstlist == null || szsstlist.size() == 0) {
                // 表示一次票没有检过

                String startdate = Tools.getTodayString();
                Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                        Edmtickettypetab.class, stss.getItickettypeid());
                StssoldtickettabId id = new StssoldtickettabId();
                id.setIsalesvoucherdetailsid(stss.getIsalesvoucherdetailsid());
                id.setIsalesvoucherid(stss.getIsalesvoucherid());
                id.setIticketstationid(stss.getIticketstationid());
                id.setSzsoldticketid(stss.getSzsoldticketid());
                Stssoldtickettab st = (Stssoldtickettab) this.checkDao.get(
                        Stssoldtickettab.class, id);
                String enddate = Tools.getDate(startdate, eticket
                        .getValidityday().intValue() - 1);
                st.setDtstartdate(startdate);
                st.setDtenddate(enddate);

                this.checkDao.update(st);

                List ssubtlist = checkDao
                        .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                                + stss.getIsalesvoucherid()
                                + " and id.iticketstationid="
                                + stss.getIticketstationid()
                                + "   and  id.isalesvoucherdetailsid="
                                + stss.getIsalesvoucherdetailsid()
                                + " and id.szsoldticketid="
                                + stss.getSzsoldticketid()
                                + "  and isvalid not in (-1,0)");
                for (int a = 0; a < ssubtlist.size(); a++) {

                    Stssoldticketsubtab stsub = (Stssoldticketsubtab) ssubtlist
                            .get(a);
                    // stsub.setDtbegindate(Tools.getTodayString()+" 00:00:00");
                    stsub.setDtbegindate(startdate + " 00:00:00");
                    stsub.setDtenddate(enddate + " 23:59:59");
                    this.checkDao.update(stsub);
                }

            }
        }

    }

    /**
     * 根据票信息读取Edmticketproduct的是否首次激活更新有效期=1 更新所有该票号的所有园门有效期
     *
     * @param stss
     */
    public int yzchuciyanpiaotime(Stssoldtickettab stss) throws Exception {
        // 查询
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Edmticketproduct ep = (Edmticketproduct) this.checkDao.get(
                Edmticketproduct.class, stss.getItickettypeid());

        if (ep != null && ep.getInoteger3() != null && ep.getInoteger3() > 0) {

            List szsstlist = checkDao
                    .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                            + stss.getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stss.getIticketstationid()
                            + "   and  id.isalesvoucherdetailsid="
                            + stss.getIsalesvoucherdetailsid()
                            + " and id.szsoldticketid="
                            + stss.getSzsoldticketid() + " and ipassedtimes>0");
            if (szsstlist == null || szsstlist.size() == 0) {
                // 表示一次票没有检过

                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                Date d1 = df.parse(stss.getDtmakedate());
                calendar.setTime(d1);
                calendar.add(Calendar.MINUTE, ep.getInoteger3().intValue());

                if (now.before(calendar)) {

                    return 1;
                } else {

                    return 0;
                }

            } else {
                return 1;
            }

        } else {
            return 1;
        }

    }

    /**
     * 根据票号判断闸机是否能检该票 100 非一单一检 101 一单一检 其他是不能检票返回的数据
     */
    /**
     * 读取配置文件
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getWebContant(String xmlkey) {
        return WebContant.GetKeyValue(xmlkey);

    }

    public int stopCheckTicket(Long accid, String szprintno, Long synumb) {

        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            return 0;
        }
        System.out.println("调用暂停方法1");
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List sstlist = checkDao
                .find(" from Stssoldtickettab s where ( szticketprintno='"
                        + szprintno + "' or myzj='" + szprintno
                        + "')  	order by s.dtmakedate desc");
        Stssoldtickettab stss = (Stssoldtickettab) sstlist.get(0);
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss.getId().getIticketstationid()
                        + "   and  id.isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid="
                        + stss.getId().getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and isvalid not in (-1,0)");
        System.out.println("调用暂停方法2");
        for (int i = 0; i < zsstlist.size(); i++) {
            System.out.println("调用暂停方法3");
            Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(i);
            stssz.setMsingledtimes(stssz.getMsingledtimes() - synumb);
            stssz.setIpassedtimes(stssz.getIpassedtimes() - synumb);
            System.out.println("调用暂停方法4");
            checkDao.update(stssz);
        }
        List checklist = checkDao
                .find("from Ticketchecklist where  isalesvoucherid="
                        + stss.getId().getIsalesvoucherid()
                        + "  and iticketstationid="
                        + stss.getId().getIticketstationid()
                        + "   and  isalesvoucherdetailsid="
                        + stss.getId().getIsalesvoucherdetailsid()
                        + " and szsoldticketid="
                        + stss.getId().getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " order by dtmakedate desc");

        if (checklist != null && checklist.size() > 0) {
            Ticketchecklist check = (Ticketchecklist) checklist.get(0);
            check.setInt1(check.getInt1() - synumb);
            checkDao.update(check);

        }
        return 1;
    }

    // ////////////////畅游同专用//当售检票服务器时要传入服务商id,否则为null/////////////////////////////////////
    public CYTDto findCYTDto(String orderid,String url) {
        return findCYTDto(orderid, null,url);
    }

    public CYTDto findCYTDto(String orderid, Long iscenicid,String urls) {
    	if(urls==null||urls.length()<0){
    		urls=WebContant.GetKeyValue("CenterUrl");
    	}
        CYTDto dto = new CYTDto();
//        dto.isGoOn = true;
        
       if(orderid.length()==18||orderid.length()==17){
    	   
    	   try {
    		    String url = "http://"+urls+"/orderpay/findOridByIdcard.action";
                String requestParam=orderid;
    		    if(iscenicid!=null && iscenicid!=0L)
                {
                    requestParam= iscenicid+"&"+orderid;
                }
                //String trealnameListJSON = HttpUtil.httpPost(url, "aaa",requestParam);
    		    //dto = JSON.parseObject(trealnameListJSON, CYTDto.class);
    		    dto=  this.getRealnemeList(String.valueOf(iscenicid), orderid);
          		
          		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	   if(dto.torder==null){
    		   try {  
        	   List realnames = checkDao.findBySqlToMap(
        			   "select s.szsalesvoucherno from Stssoldtickettab t,Stssalesvouchertab s where t.myzj = '"+orderid+"' and"
        			   + " t.isalesvoucherid = s.isalesvoucherid order by s.szsalesvoucherno desc",new Object[]{});
        	   String orid = ((Map)realnames.get(0)).get("SZSALESVOUCHERNO").toString();
        	   orderid = orid;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
          
            
            
           Object ps[] = new Object[] { orderid };
//            List<TOrderlist> tll = checkDao.find(
//                    "from TOrderlist t where t.id.orid=?", ps);
            List<TOrderlist> tll = ecService.getTOrderlistByOrid(orderid);
            if (tll != null && tll.size() == 1)
                dto.tOrderlist = tll.get(0);
            // if(dto.isGoOn){
            // Edmtickettypetab ticket = (Edmtickettypetab)
            // checkDao.get(Edmtickettypetab.class,
            // dto.tOrderlist.getItickettypeid());
            // //畅游通必须满足以下条件，否则不能在闸机上出
            // dto.isGoOn = (ticket != null && ticket.getByusage() == 1 &&
            // "00".equals(ticket.getBymaketicketway()) && ticket.getByisuse() ==
            // 1);
            // }
//            dto.sysparv5 = (Sysparv5) checkDao.get(Sysparv5.class, new Sysparv5Id(
//                    "NPTK", "01"));
            dto.sysparv5 = sysService.findOneById("NPTK", "01");
            // if (dto.isGoOn)
            // dto.isGoOn = ((dto.esbticketwintab = (Esbticketwintab) checkDao
            // .get(Esbticketwintab.class,
            // Long.parseLong(dto.sysparv5.getPmva()))) != null);
            if (dto.isGoOn && tll != null) {
            	List<MOrder> ml = ecService.getMorderListInfo(orderid);
    			if (ml != null && ml.size() > 0) {
    				dto.morder = ml.get(0);
    				List<TOrder> tl =  ecService.getTOrdersList(orderid);
    				if (tl != null && tl.size() > 0)
    					dto.torder = tl.get(0);
    			}
            }
           
    	   }
    	   
    	   if (dto.torder != null || iscenicid != null) {
           	dto.Galcompanyinfotab = sysService.getGalcompanyinfo(dto.torder.getId().getIscenicid());
           }
       }
        return dto;
    }

    
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void addCYTPojo(CYTPojo pojo) {
        checkDao.save(pojo);
    }

    @SuppressWarnings("unchecked")
    public List<CYTPojo> findNotCallbackCYTPojo() {
        return checkDao.find("from CYTPojo c where c.state = 0");
    }
    
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void updatePojo(CYTPojo pojo) {
        checkDao.update(pojo);
    }

    public Stssoldtickettab getmyzj(String accid, String szticketprintno) {
        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);

        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign,s.dtmakedate as dtmakedate,s.myzj as myzj,s.name1 as name1) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw where szticketprintno='"
                        + szticketprintno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid 	order by s.dtmakedate desc");
        Stssoldtickettab stss = null;
        stss = new Stssoldtickettab();
        try {
            BeanUtils.populate(stss, (Map) sstlist.get(0));
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stss;
    }

    /**
     * 总体对外检票方法
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return 2012-7-28 日 李进进行重大修改 ,String ...OtherString 增加了第三个参数
     */

    public ResultBean changeCheckTicket(String accid, String szticketprintno,
                                        String szticketprintno2) throws Exception {
        // System.out.println(" in changeCheckTicket");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        // 读取闸机信息

        String tickettypename = "";// 票名称
        String mactualsaleprice = "";// 单价
        String icrowdkindid = "";// 人群id
        String szcrowdkindname = "";// 人群名称
        String dtbegindate = "";// 开始时间
        String dtenddate = "";// 截至时间
        String bsfilebinary = "";// 指纹信息
        String szimagepath = "";// 相片路径
        String szmemo = "";// 语音路径
        String seq = "";// 竹筏流水
        String tripname = "";// 趟次名称

        List acclist = checkDao
                .find(" from Esbaccessequiptab where id.iaccessequipid="
                        + accid);
        if (acclist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效闸机";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);
            return rs;
        }
        Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
        List sstlist = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign,s.dtmakedate as dtmakedate,s.myzj as myzj,s.name1 as name1) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw,Stssoldticketsubtab st where szticketprintno='"
                        + szticketprintno
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid and s.id.szsoldticketid=st.id.szsoldticketid  and s.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and s.id.isalesvoucherid=st.id.isalesvoucherid	and s.id.iticketstationid=st.id.iticketstationid and st.igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and  st.isvalid not in (-1,0)  order by s.dtmakedate desc");
        Stssoldtickettab stss = null;

        if (sstlist == null || sstlist.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效检票设备";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            return rs;
        } else {
            stss = new Stssoldtickettab();
            BeanUtils.populate(stss, (Map) sstlist.get(0));
            Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) this.checkDao
                    .get(Stssalesvoucherdetailstab.class,
                            new StssalesvoucherdetailstabId(stss
                                    .getIsalesvoucherdetailsid(), stss
                                    .getIsalesvoucherid(), stss
                                    .getIticketstationid()));
            Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.checkDao.get(
                    Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
            tickettypename = stss.getSztickettypename();
            mactualsaleprice = stss.getMactualsaleprice().toString();
            icrowdkindid = stss.getIcrowdkindid().toString();
            szcrowdkindname = stss.getSzcrowdkindname();
            if (ep.getNote2() != null && !ep.getNote2().equals("")) {
                szmemo = ep.getNote2();
            } else {
                szmemo = stss.getSzmemo();
            }

            if (stss.getByvalidity().equals("01")) {
                rs.addRow(new String[] { "-2" });// 票已退订或挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss);
                return rs;
            }
            if (stss.getByvalidity().equals("02")) {
                rs.addRow(new String[] { "-99" });// 已挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                return rs;
            }
        }
        System.out.println("changeCheckTicket22");
        if (szticketprintno.equals(szticketprintno2)) {
            rs.addRow(new String[] { "-20" });// 已挂失
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        System.out.println("changeCheckTicket223");
        Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
                Edmtickettypetab.class, stss.getItickettypeid());
        List sstlist1 = checkDao
                .find("select new map( s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.mactualsaleprice as mactualsaleprice,s.byvalidity as byvalidity ,edw.szcrowdkindname as szcrowdkindname,edw.szmemo as szmemo,s.icrowdkindid as icrowdkindid, et.sztickettypename as sztickettypename,s.itickettypeid as itickettypeid,s.ibusinessid as ibusinessid,s.ipartitionsign as ipartitionsign,s.dtmakedate as dtmakedate,s.myzj as myzj,s.name1 as name1) from Stssoldtickettab s ,Edmtickettypetab et,Edpcrowdkindtab edw,Stssoldticketsubtab st where szticketprintno='"
                        + szticketprintno2
                        + "' and et.itickettypeid=s.itickettypeid and edw.icrowdkindid=s.icrowdkindid and s.id.szsoldticketid=st.id.szsoldticketid  and s.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and s.id.isalesvoucherid=st.id.isalesvoucherid	and s.id.iticketstationid=st.id.iticketstationid and st.igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and  st.isvalid not in (-1,0)  order by s.dtmakedate desc");
        Stssoldtickettab stss1 = null;

        if (sstlist1 == null || sstlist1.size() == 0) {
            rs.addRow(new String[] { "-1" });// 无效票
            tickettypename = "无效检票设备";
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            return rs;
        } else {
            stss1 = new Stssoldtickettab();
            BeanUtils.populate(stss1, (Map) sstlist1.get(0));
            Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) this.checkDao
                    .get(Stssalesvoucherdetailstab.class,
                            new StssalesvoucherdetailstabId(stss1
                                    .getIsalesvoucherdetailsid(), stss1
                                    .getIsalesvoucherid(), stss1
                                    .getIticketstationid()));
            Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.checkDao.get(
                    Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
            tickettypename = stss1.getSztickettypename();
            mactualsaleprice = stss1.getMactualsaleprice().toString();
            icrowdkindid = stss1.getIcrowdkindid().toString();
            szcrowdkindname = stss1.getSzcrowdkindname();
            if (ep.getNote2() != null && !ep.getNote2().equals("")) {
                szmemo = ep.getNote2();
            } else {
                szmemo = stss1.getSzmemo();
            }

            if (stss1.getByvalidity().equals("01")) {
                rs.addRow(new String[] { "-2" });// 票已退订或挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss1);
                return rs;
            }
            if (stss1.getByvalidity().equals("02")) {
                rs.addRow(new String[] { "-99" });// 已挂失
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss1);
                return rs;
            }
        }

        Edmtickettypetab eticket1 = (Edmtickettypetab) checkDao.get(
                Edmtickettypetab.class, stss1.getItickettypeid());
        System.out.println("eticket"+eticket.getItickettypeid());
        System.out.println("eticket1"+eticket1.getItickettypeid());
        if (eticket.getItickettypeid().longValue() == eticket1
                .getItickettypeid().longValue()) {
            rs.addRow(new String[] { "-20" });// 已挂失
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);
            rs = getchecklist(rs, stss1);
            return rs;
        }
        boolean b = false;
        List xgjplist = checkDao
                .find(" from Esbgardengatetickettab where igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and  (itickettypeoneid="
                        + eticket.getItickettypeid() + " or itickettypetwoid="
                        + eticket.getItickettypeid() + ")");
        for (int a = 0; a < xgjplist.size(); a++) {
            Esbgardengatetickettab esbgt = (Esbgardengatetickettab) xgjplist
                    .get(a);
            if (esbgt.getItickettypeoneid().longValue() == eticket1
                    .getItickettypeid().longValue()
                    || esbgt.getItickettypetwoid().longValue() == eticket1
                    .getItickettypeid().longValue()) {
                b = true;
            }

        }
        System.out.println("changeCheckTicket224");
        if (b == false) {
            rs.addRow(new String[] { "-20" });// 已挂失
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);
            rs = getchecklist(rs, stss1);
            return rs;
        }
        // 查询园门和票务对应的售出门票子票表
        System.out.println("changeCheckTicket225");
        List zsstlist = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss.getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss.getIticketstationid()
                        + "   and  id.isalesvoucherdetailsid="
                        + stss.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and isvalid not in (-1,0)");
        if (zsstlist == null || zsstlist.size() == 0) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        // 更新有效期
        int yztime = yzchuciyanpiaotime(stss);
        if (yztime == 0) {
            rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss);
            return rs;
        }
        this.updateyouxiaoqi(stss);
        now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Stssoldticketsubtab stssz = (Stssoldticketsubtab) zsstlist.get(0);

        dtbegindate = stssz.getDtbegindate().substring(2);
        dtenddate = stssz.getDtenddate().substring(2);
        String jpnumb1 = "1";
        if (stssz.getMpasstimes() != null) {
            jpnumb1 = stssz.getMpasstimes().toString();
        }
        if (stssz.getTripid() > 0) {

            // 场馆票验证时间
            List comlist = checkDao
                    .find(" from Stscomticketsalesdetailstab where id.isalesvoucherid="
                            + stssz.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz.getId().getIsalesvoucherdetailsid()
                            + " and iztickettypeid="
                            + stssz.getIztickettypeid());
            Stscomticketsalesdetailstab stscom = (Stscomticketsalesdetailstab) comlist
                    .get(0);
            List seatlist = checkDao
                    .find(" from Seatsaletab where id.isalesvoucherid="
                            + stssz.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz.getId().getIsalesvoucherdetailsid()
                            + " and id.szsoldticketid="
                            + stssz.getId().getSzsoldticketid()
                            + " and id.icomticketsalesdetailsid="
                            + stscom.getId().getIcomticketsalesdetailsid());

            Seatsaletab seatsale = (Seatsaletab) seatlist.get(0);
            Long itripprdcontrolid = seatsale.getItripprdcontrolid();
            List triplist = checkDao
                    .find(" from Tripprdcontroldetailtab where itripprdcontrolid="
                            + itripprdcontrolid
                            + " and itripid="
                            + stssz.getTripid());
            Tripprdcontroldetailtab tripdetail = (Tripprdcontroldetailtab) triplist
                    .get(0);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(stssz.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar begcalendar = Calendar.getInstance();
            begcalendar.setTime(d1);
            begcalendar.add(Calendar.MINUTE, -1
                    * tripdetail.getIadvanceminute().intValue());

            Date d2 = df.parse(stssz.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar endcalendar = Calendar.getInstance();
            endcalendar.setTime(d2);
            endcalendar.add(Calendar.MINUTE, tripdetail.getIlagminute()
                    .intValue());
            if (now.before(begcalendar)) {
                rs.addRow(new String[] { "-4" });// 未到检票开始时间
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

            if (now.after(endcalendar)) {
                rs.addRow(new String[] { "-5" });// 已过有效期
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

        } else {
            try {
                // 验证有效时间
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(stssz.getDtbegindate());
                Calendar begcalendar = Calendar.getInstance();
                begcalendar.setTime(d1);
                Date d2 = df.parse(stssz.getDtenddate());
                Calendar endcalendar = Calendar.getInstance();
                endcalendar.setTime(d2);

                if (now.before(begcalendar)) {
                    rs.addRow(new String[] { "-4" });// 未到检票开始时间
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }

                if (now.after(endcalendar)) {
                    rs.addRow(new String[] { "-5" });// 已过有效期
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }
                // 读取竹筏票seq
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        System.out.println("检票事前验证3");
        if (stssz.getIpasstimes() > 0) {
            if (stssz.getIpasstimes() - stssz.getIpassedtimes() <= 0) {
                rs.addRow(new String[] { "-9" });// 已检过

                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { "0" });
                return rs;
            }
        }

        List opwwicketsettablist = checkDao
                .find("from Opwwicketsettab where itickettypeid="
                        + stssz.getItickettypeid() + " and izticktypeid="
                        + stssz.getIztickettypeid() + " and igardengateid="
                        + acc.getId().getIgardengateid() + " and byisuse=1");
        Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);

        Long itimeinterval = opww.getItimeinterval();// 检票间隔时间
        String lastcheckdate = stssz.getDtlastcheckdate();// 最后检票时间
        if (lastcheckdate != null && !lastcheckdate.equals("")) {
            // 判断检票时间间隔
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date d1 = df.parse(lastcheckdate);
            calendar.setTime(d1);
            calendar.add(Calendar.SECOND, itimeinterval.intValue());
            if (now.before(calendar)) {
                System.out.println("changeCheckTicket:-10");
                rs.addRow(new String[] { "-10" });//
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }
        }

        // 查询园门和票务对应的售出门票子票表
        List zsstlist1 = checkDao
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + stss1.getIsalesvoucherid()
                        + " and id.iticketstationid="
                        + stss1.getIticketstationid()
                        + "   and  id.isalesvoucherdetailsid="
                        + stss1.getIsalesvoucherdetailsid()
                        + " and id.szsoldticketid=" + stss1.getSzsoldticketid()
                        + " and igardengateid="
                        + acc.getId().getIgardengateid()
                        + " and isvalid not in (-1,0)");
        if (zsstlist1 == null || zsstlist1.size() == 0) {
            rs.addRow(new String[] { "-3" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss1);
            return rs;
        }
        // 更新有效期
        yztime = yzchuciyanpiaotime(stss1);
        if (yztime == 0) {
            rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
            rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                    icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                    bsfilebinary, szimagepath, szmemo, seq, tripname);

            rs = getchecklist(rs, stss1);
            return rs;
        }
        this.updateyouxiaoqi(stss1);
        now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Stssoldticketsubtab stssz1 = (Stssoldticketsubtab) zsstlist1.get(0);

        dtbegindate = stssz1.getDtbegindate().substring(2);
        dtenddate = stssz1.getDtenddate().substring(2);
        jpnumb1 = "1";
        if (stssz1.getMpasstimes() != null) {
            jpnumb1 = stssz1.getMpasstimes().toString();
        }
        if (stssz1.getTripid() > 0) {
            // 场馆票验证时间
            List comlist = checkDao
                    .find(" from Stscomticketsalesdetailstab where id.isalesvoucherid="
                            + stssz1.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz1.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz1.getId().getIsalesvoucherdetailsid()
                            + " and iztickettypeid="
                            + stssz1.getIztickettypeid());
            Stscomticketsalesdetailstab stscom = (Stscomticketsalesdetailstab) comlist
                    .get(0);
            List seatlist = checkDao
                    .find(" from Seatsaletab where id.isalesvoucherid="
                            + stssz1.getId().getIsalesvoucherid()
                            + " and id.iticketstationid="
                            + stssz1.getId().getIticketstationid()
                            + " and id.isalesvoucherdetailsid="
                            + stssz1.getId().getIsalesvoucherdetailsid()
                            + " and id.szsoldticketid="
                            + stssz1.getId().getSzsoldticketid()
                            + " and id.icomticketsalesdetailsid="
                            + stscom.getId().getIcomticketsalesdetailsid());

            Seatsaletab seatsale = (Seatsaletab) seatlist.get(0);
            Long itripprdcontrolid = seatsale.getItripprdcontrolid();
            List triplist = checkDao
                    .find(" from Tripprdcontroldetailtab where itripprdcontrolid="
                            + itripprdcontrolid
                            + " and itripid="
                            + stssz1.getTripid());
            Tripprdcontroldetailtab tripdetail = (Tripprdcontroldetailtab) triplist
                    .get(0);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(stssz1.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar begcalendar = Calendar.getInstance();
            begcalendar.setTime(d1);
            begcalendar.add(Calendar.MINUTE, -1
                    * tripdetail.getIadvanceminute().intValue());

            Date d2 = df.parse(stssz1.getDtbegindate().substring(0, 10) + " "
                    + tripdetail.getStarttime() + ":00");
            Calendar endcalendar = Calendar.getInstance();
            endcalendar.setTime(d2);
            endcalendar.add(Calendar.MINUTE, tripdetail.getIlagminute()
                    .intValue());
            if (now.before(begcalendar)) {
                rs.addRow(new String[] { "-4" });// 未到检票开始时间
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss1);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

            if (now.after(endcalendar)) {
                rs.addRow(new String[] { "-5" });// 已过有效期
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss1);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }

        } else {
            try {
                // 验证有效时间
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date d1 = df.parse(stssz1.getDtbegindate());
                Calendar begcalendar = Calendar.getInstance();
                begcalendar.setTime(d1);
                Date d2 = df.parse(stssz1.getDtenddate());
                Calendar endcalendar = Calendar.getInstance();
                endcalendar.setTime(d2);

                if (now.before(begcalendar)) {
                    rs.addRow(new String[] { "-4" });// 未到检票开始时间
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss1);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }

                if (now.after(endcalendar)) {
                    rs.addRow(new String[] { "-5" });// 已过有效期
                    rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                            icrowdkindid, szcrowdkindname, dtbegindate,
                            dtenddate, bsfilebinary, szimagepath, szmemo, seq,
                            tripname);

                    rs = getchecklist(rs, stss1);
                    rs.addRow(new String[] { jpnumb1 });
                    return rs;
                }
                // 读取竹筏票seq
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        System.out.println("检票事前验证3");
        if (stssz1.getIpasstimes() > 0) {
            if (stssz1.getIpasstimes() - stssz1.getIpassedtimes() <= 0) {
                rs.addRow(new String[] { "-9" });// 已检过

                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);
                rs = getchecklist(rs, stss1);
                rs.addRow(new String[] { "0" });
                return rs;
            }
        }

        List opwwicketsettablist1 = checkDao
                .find("from Opwwicketsettab where itickettypeid="
                        + stssz1.getItickettypeid() + " and izticktypeid="
                        + stssz1.getIztickettypeid() + " and igardengateid="
                        + acc.getId().getIgardengateid() + " and byisuse=1");
        Opwwicketsettab opww1 = (Opwwicketsettab) opwwicketsettablist1.get(0);

        Long itimeinterval1 = opww1.getItimeinterval();// 检票间隔时间
        String lastcheckdate1 = stssz1.getDtlastcheckdate();// 最后检票时间
        if (lastcheckdate1 != null && !lastcheckdate1.equals("")) {
            // 判断检票时间间隔
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date d1 = df.parse(lastcheckdate1);
            calendar.setTime(d1);
            calendar.add(Calendar.SECOND, itimeinterval1.intValue());
            if (now.before(calendar)) {
                System.out.println("changeCheckTicket:-10");
                rs.addRow(new String[] { "-10" });//
                rs = getcanshu11(rs, tickettypename, mactualsaleprice,
                        icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
                        bsfilebinary, szimagepath, szmemo, seq, tripname);

                rs = getchecklist(rs, stss1);
                rs.addRow(new String[] { jpnumb1 });
                return rs;
            }
        }
        String daytime = Tools.getDayTimes();

        for (int c = 0; c < zsstlist1.size(); c++) {
            stssz1 = (Stssoldticketsubtab) zsstlist1.get(c);
            stssz1.setIpassedtimes(stssz1.getIpassedtimes()
                    + stssz1.getMpasstimes());
            stssz1.setDtlastcheckdate(daytime);
            stssz1.setDtmakedate(daytime);
            stssz1.setByisout(new Long(1));
            checkDao.update(stssz1);
        }
        for (int c = 0; c < zsstlist.size(); c++) {
            stssz = (Stssoldticketsubtab) zsstlist.get(c);
            stssz.setIpassedtimes(stssz.getIpassedtimes()
                    + stssz.getMpasstimes());
            stssz.setDtlastcheckdate(daytime);
            stssz.setDtmakedate(daytime);
            stssz.setByisout(new Long(1));
            checkDao.update(stssz1);
        }
        Ticketchecklist checkt = this.getTicketchecklist(acc, stss, daytime,
                1L, "");

        checkDao.save(checkt);
        Ticketchecklist checkt1 = this.getTicketchecklist(acc, stss1, daytime,
                1L, "");
        checkDao.save(checkt1);
        rs.addRow(new String[] { "3" });// 检票成功,放行
        rs = getcanshu11(rs, tickettypename, mactualsaleprice, icrowdkindid,
                szcrowdkindname, dtbegindate, dtenddate, bsfilebinary,
                szimagepath, szmemo, seq, tripname);

        rs = getchecklist(rs, stss1);
        rs.addRow(new String[] { jpnumb1 });

        return rs;

    }

    public void addCheckList(String gardenId,String accid,Long numb) {
        Ticketchecklist ticketchecklist = new Ticketchecklist();
        ticketchecklist.setCheckid(1L);
        ticketchecklist.setSzsoldticketid(0L);
        ticketchecklist.setIsalesvoucherid(0L);
        ticketchecklist.setIsalesvoucherdetailsid(0L);
        ticketchecklist.setIticketstationid(0L);
        ticketchecklist.setIgardengateid(Long.parseLong(gardenId));
        ticketchecklist.setIscenicid(0L);
        ticketchecklist.setDtmakedate(Tools.getDayTimes());
        ticketchecklist.setIaccessequipid(Long.parseLong(accid));
        ticketchecklist.setInt1(numb);
        checkDao.save(ticketchecklist);
    }

    public void updateCheckList(String gardenId,String accid,Long numb) {
        String sql = "from Ticketchecklist where isalesvoucherid = 0 and " +
                "substr(dtmakedate,1,10)='"+Tools.getDays()+"' ";
        if(!StringUtils.isBlank(gardenId)){
            sql += " and igardengateid = "+Long.parseLong(gardenId);
        }
        if(!StringUtils.isBlank(accid)){
            sql += " and iaccessequipid = "+Long.parseLong(accid);
        }
        List list = checkDao.find(sql);
        if(list != null && !list.isEmpty()) {
            int nowCheckNum =getScenicCheckNum(accid);
            Ticketchecklist check =(Ticketchecklist)list.get(0);
            if(nowCheckNum>=numb)
            {
                check.setInt1(numb);
            }else
            {
                check.setInt1((long)nowCheckNum);
            }
            checkDao.update(check);
        }else
        {
            addCheckList(gardenId,accid,numb);
        }

    }

    public int getScenicCheckNum(String accid){
        String sql = "select new map(nvl(sum(int1),0) as numb) from Ticketchecklist where " +
                "isalesvoucherid != 0 and " +
                "substr(dtmakedate,1,10)='"+Tools.getDays()+"' ";

        String equipSql="from Esbaccessequiptab where iaccessequipid="+Long.parseLong(accid);
        List equipList=checkDao.find(equipSql);
        Long iscenicId=0L;
        if(equipList!=null && equipList.size()>0)
        {
            Esbaccessequiptab equip= (Esbaccessequiptab) equipList.get(0);
            iscenicId=equip.getId().getIscenicid();
        }

        if(iscenicId!=0L) {
            sql += " and iscenicid = " +iscenicId;
        }
        List list = checkDao.find(sql);
        if(list != null && !list.isEmpty()){
            Map map = (Map) list.get(0);
            return Integer.parseInt(map.get("numb").toString());
        }
        return 0;
    }

    public int getEquipCheckNum(String gardenId, String accid){
        String sql = "select new map(nvl(sum(int1),0) as numb) from Ticketchecklist where isalesvoucherid > 0 and " +
                "substr(dtmakedate,1,10)='"+Tools.getDays()+"' ";
        if(!StringUtils.isBlank(gardenId)){
            sql += " and igardengateid = "+Long.parseLong(gardenId);
        }
        if(!StringUtils.isBlank(accid)){
            sql += " and iaccessequipid = "+Long.parseLong(accid);
        }
        List list = checkDao.find(sql);
        if(list != null && !list.isEmpty()){
            Map map = (Map) list.get(0);
            return Integer.parseInt(map.get("numb").toString());
        }
        return 0;
    }

    public int getCheckOutNum(String gardenId, String accid){
        String sql = "select new map(nvl(sum(int1),0) as numb) from Ticketchecklist where isalesvoucherid = 0 and " +
                "substr(dtmakedate,1,10)='"+Tools.getDays()+"' ";
        if(!StringUtils.isBlank(gardenId)){
            sql += " and igardengateid = "+Long.parseLong(gardenId);
        }
        if(!StringUtils.isBlank(accid)){
            sql += " and iaccessequipid = "+Long.parseLong(accid);
        }
        List list = checkDao.find(sql);
        if(list != null && !list.isEmpty()){
            Map map = (Map) list.get(0);
            return Integer.parseInt(map.get("numb").toString());
        }
        return 0;
    }

    public String findOrderByCard(String accid,String card){
        String sql = "from Esbaccessequiptab where id.iaccessequipid="+Long.parseLong(accid);
        List list = this.checkDao.find(sql);
        if(list != null && !list.isEmpty()){
            Esbaccessequiptab acc = (Esbaccessequiptab) list.get(0);
            Long providerId = acc.getId().getIscenicid();
           return ecService.getTOrderByIdcard(providerId, card);
        }
        return null;
    }
    
    
    public CYTDto getRealnemeList(String iscenicid,String idcard) {
		CYTDto dto = new CYTDto();
		try {
			// TODO Auto-generated method stub
			//
			
			//Long iscenicid = null;
			List realnemeList = ecService.getRealnemeList(iscenicid,idcard);
			String orderid = null;
			Object ps[] = new Object[] { idcard };
			
			if (realnemeList != null && realnemeList.size() > 0) {
				orderid = ((Map)realnemeList.get(0)).get("orid").toString();
				dto = this.getCYTDto(dto, orderid);
				
				//将身份证号作为游客证件号时，判断该票是否已检过，如果已检过，再将身份证号作为领票人证件号继续查询
				List<Stssalesvouchertab> stssalesvouchertabs = this.find(" from Stssalesvouchertab s  where s.szsalesvoucherno='"+orderid+"' order by s.dtmakedate desc ");
				if (stssalesvouchertabs !=null && stssalesvouchertabs.size()>0) {
					List<Stssoldticketsubtab> stssoldticketsubtabs = this.find(" from Stssoldticketsubtab t where t.id.isalesvoucherid = '"+stssalesvouchertabs.get(0).getId().getIsalesvoucherid()+"'  order by t.dtmakedate desc ");
					if (stssoldticketsubtabs.get(0).getIpasstimes() - stssoldticketsubtabs.get(0).getIpassedtimes() == 0) {
						dto = new CYTDto();
					}
				}
				
				
			}
			
			if(dto.morder == null || orderid == null) {
				//将身份证号作为领票人证件号
				if(idcard.length()==18){
					List<TOrder> lists = ecService.findTorderValues(idcard);
					if(lists==null||lists.size()<=0){
						return dto;
					}else {
						dto.torder = lists.get(0);
						orderid = lists.get(0).getId().getOrid();
					}
				}else{
					orderid = idcard;
				}
				dto = this.getCYTDto(dto, orderid);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			return dto;
		}
		
	}
    private CYTDto  getCYTDto(CYTDto dto,String orderid) {
    	dto.isGoOn = true;
		
		List<TOrderlist> tll = ecService.getTOrderlistByOrid(orderid);//this.find(" from TOrderlist t where t.id.orid=?", new Object[] {orderid});
		if (tll != null && tll.size() == 1)
			dto.tOrderlist = tll.get(0);
		
		dto.sysparv5 = sysService.findOneById("NPTK", "01");//(Sysparv5) this.get(Sysparv5.class, new Sysparv5Id("NPTK", "01"));
		
		if (dto.isGoOn && tll != null) {
			List<MOrder> ml = ecService.getMorderListInfo(orderid);//this.find(" from MOrder m where m.orid=? and (m.ddzt='02' or m.ddzt='11' or (m.ddzt='00' and m.zffs='09'))",new Object[] {orderid});
			if (ml != null && ml.size() > 0) {
				dto.morder = ml.get(0);
				List<TOrder> tl =  ecService.getTOrdersList(orderid);//this.find(" from TOrder t where t.id.orid=? ", new Object[] {orderid});
				if (tl != null && tl.size() > 0)
					dto.torder = tl.get(0);
			}
		}
		if (dto.torder != null) {
//			List<Companyscenic> csl = this.find(" from Companyscenic c where c.id.iscenicid=? and c.cytonly='1'",
//					new Object[] {dto.torder.getId().getIscenicid() });
//			if (csl != null && csl.size() > 0) {
//				dto.Galcompanyinfotab = (Galcompanyinfotab) this.get(Galcompanyinfotab.class,
//						csl.get(0).getId().getIcompanyinfoid());
//			}
			
			dto.Galcompanyinfotab = sysService.getGalcompanyinfo(dto.torder.getId().getIscenicid());
		}
		return dto;
    }
    
    /**
	 * Describe:畅游通订单核销
	 * 
	 * @author liujianwen
	 * @param orid
	 * @return return:ResultBean Date:2014-7-3
	 */
	public ResultBean cythexiao(String orid,String url) {
		CYTPojo pojo = new CYTPojo();
		pojo.isCYT = true;
//		ICheckService checkService = (ICheckService) SpringUtil
//				.getBean("checkService");
//		pojo.cytdto = checkService.findCYTDto(orid,url);
		
		pojo.cytdto = this.findCYTDto(orid,url);
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		String value = "true", message = "";
		pojo.orid = orid;
		pojo.oto_code = pojo.cytdto.Galcompanyinfotab.getSzcompanycode();
		pojo.iscenicid = pojo.cytdto.torder.getId().getIscenicid();

//		IOrderService orderService = (IOrderService) SpringUtil.getBean("orderService");
//		List<Esbaccessequiptab> es = esbaccessequiptabService.find(
//				"from Esbaccessequiptab e where e.id.iscenicid= ? and e.byisuse = 1 and e.szaccessequipname like ?", 
//				new Object[]{pojo.iscenicid,"%CYT%"});
		
		List<Esbaccessequiptab> es = this.find(
				"from Esbaccessequiptab e where e.id.iscenicid= ? and e.byisuse = 1 and e.szaccessequipname like ?", 
				new Object[]{pojo.iscenicid,"%CYT%"});
		if(es != null && es.size() != 0)
			pojo.posid = es.get(0).getId().getIaccessequipid().toString();

		pojo.orderuserid = pojo.cytdto.torder.getUsid();
		pojo.orderQuantity = String.valueOf(pojo.cytdto.tOrderlist.getNumb());
		pojo.useQuantity = pojo.orderQuantity;
		// 如果是需要核销的订单
//		if (pojo.posid == null || !cytClient.verifyConsume(pojo.orderuserid, pojo.oto_code,
//				pojo.cytdto.morder.getNoteh(), pojo.orid,
//				String.valueOf(pojo.cytdto.tOrderlist.getNumb()),
//				pojo.cytdto.morder.getNotei(), pojo.posid + pojo.oto_code)) {
//			value = "false";
//			message = "核销失败，无法出票!";
//		}
		rs.addRow(new String[] { value, message });
		return rs;
	
	}

}
