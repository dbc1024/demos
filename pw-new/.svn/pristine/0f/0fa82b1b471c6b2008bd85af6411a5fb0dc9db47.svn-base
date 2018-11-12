package com.ectrip.ec.report.system.order.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.Reservecontrol;
import com.ectrip.ec.model.order.Reserversale;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.ec.report.system.order.dao.idao.IQueryOrderDAO;
import com.ectrip.ec.report.system.order.service.iservice.IQueryOrderService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

@Service
public class QueryOrderService implements IQueryOrderService {
	
	@Autowired
    private IQueryOrderDAO queryorderDao;
    
    @Autowired
    private SysparService sysparService;
//    private IMbMessageDAO mbmessageDao;
    private IBalanceCenterDAO balancecenterDao;
    private IMOrderDAO morderDao;
    


    /**
     * 查询逾期未领的订单数 Describe:
     * 
     * @auth:huangyuqi
     * @param esfemployeetab
     * @return return:int Date:2011-10-31
     */
    public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab) {
	return queryorderDao.queryYuQiOrderNumb(esfemployeetab);
    }

    /**
     * 根据条件查询订单 Describe:
     * 
     * @auth:huangyuqi
     * @param esfemployeetab登录人信息
     * @param order订单查询条件类
     * @param page页码
     * @param pageSize毎页显示数
     * @param url访问地址
     * @return return:PaginationSupport Date:2011-10-31
     */
    public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
	return queryorderDao.queryAllOrder(esfemployeetab, order, page, pageSize, url);
    }
    public List queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order){
    	return queryorderDao.queryAllOrder(esfemployeetab, order);
    }
    /**
     * 根据条件查询退订单 Describe:
     * 
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-11-1
     */
    public PaginationSupport queryAllMsOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
	return queryorderDao.queryAllMsOrder(esfemployeetab, order, page, pageSize, url);
    }

    /**
     * 查询停排订单 Describe:
     * 
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-11-4
     */
    public PaginationSupport queryTingPaiOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
	return queryorderDao.queryTingPaiOrder(esfemployeetab, order, page, pageSize, url);
    }

    /**
     * 
     * Describe:获取全部申请单[已支付]
     * 
     * @auth:yangguang
     * @param pagesize
     * @param startindex
     * @param url
     * @return return:PaginationSupport Date:2012-1-7
     */
    public PaginationSupport queryAllAuditOrder(String iscenicid,int pagesize, int startindex, String url, QueryOrder order) {
	return queryorderDao.queryAllAuditOrder(iscenicid,pagesize, startindex, url, order);
    }

    /**
     * * Describe:
     * 
     * @see com.ectrip.report.system.order.service.iservice.IQueryOrderService#queryOrderDetail(java.lang.String)
     * @param orid
     * @return
     * @author yangguang Date:2012-1-7
     */
    public Map queryOrderDetail(String orid, String iscenicid) {
	Map orderMap = new HashMap();
	orderMap.put("Morder", queryorderDao.get(MOrder.class, orid));
	orderMap.put("Torder", queryorderDao.getTOrderDetail(orid, iscenicid));
	orderMap.put("TOrderlist", queryorderDao.queryTOrderlistDetail(orid, iscenicid));
	return orderMap;
    }

    public List getTripNumberByOrid(String orid, String iscenicid) {
	List triplist = queryorderDao.getTripNumberByOrid(orid, iscenicid);
	MOrder morder=(MOrder) queryorderDao.get(MOrder.class, orid);
	Custom custom=(Custom) queryorderDao.get(Custom.class, morder.getUsid());
	if (triplist == null || triplist.size() < 1) {
	    return null;
	} else {
	    Productcontrol control = null;
	    String triptime = "";
	    for (int i = 0; i < triplist.size(); i++) {
		Map map = (Map) triplist.get(i);
		control = queryorderDao.getProductControlByTripInfo(map.get("dtstartdate").toString().substring(0, 10), map.get("tripid")
			.toString(), map.get("ticketid").toString());
		Reservecontrol reserver=queryorderDao.getLxsreserve(custom.getIbusinessid(), "", control.getProductcontrolid());
		triptime = map.get("dtstartdate").toString();
		map.remove("dtstartdate");
		map.put("date", triptime.substring(0, 10));
		map.put("time", triptime.substring(11, triptime.length()));
		if(reserver!=null){
		    map.put("surplusNumber", reserver.getReservednumber()-reserver.getReservedsalenumber());
		}else{
		    map.put("surplusNumber", 0);
		}
		
	    }
	    return triplist;
	}
    }

    public boolean volidationTripnumber(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
	MOrder morder=(MOrder) queryorderDao.get(MOrder.class, orid);
	Custom custom=(Custom) queryorderDao.get(Custom.class, morder.getUsid());
	List triplist = queryorderDao.getTripNumberByOrid(orid, iscenicid);
	boolean result = true;
	if (triplist == null || triplist.size() < 1) {
	    result = false;
	} else {
	    Productcontrol control = null;
	    for (int i = 0; i < triplist.size(); i++) {
		Map map = (Map) triplist.get(i);
		control = queryorderDao.getProductControlByTripInfo(map.get("dtstartdate").toString().substring(0, 10), map.get("tripid")
			.toString(), map.get("ticketid").toString());
		Reservecontrol reserver=queryorderDao.getLxsreserve(custom.getIbusinessid(), "", control.getProductcontrolid());
		if(reserver==null){
		    result = false;
		    break;
		}
		if (Integer.parseInt(map.get("num").toString()) > reserver.getReservednumber() - reserver.getReservedsalenumber()) {
		    result = false;
		    break;
		}
	    }
	}
	return result;
    }

    public void updateReservednumber(String orid, String iscenicid, Long employeeid) throws Exception {
	MOrder morder = (MOrder) queryorderDao.get(MOrder.class, orid);
	Custom custom=(Custom) queryorderDao.get(Custom.class, morder.getUsid());
	List triplist = queryorderDao.getTripNumberByOrid(orid, iscenicid);
	Productcontrol control = null;
	Reserversale resale = null;
	for (int i = 0; i < triplist.size(); i++) {
	    Map map = (Map) triplist.get(i);
	    control = queryorderDao.getProductControlByTripInfo(map.get("dtstartdate").toString().substring(0, 10), map.get("tripid")
		    .toString(), map.get("ticketid").toString());
	    int number = Integer.parseInt(map.get("num").toString());
	    Reservecontrol reserver=queryorderDao.getLxsreserve(custom.getIbusinessid(), "", control.getProductcontrolid());
	    reserver.setReservedsalenumber(reserver.getReservedsalenumber()+number);
	    queryorderDao.update(reserver);
	    control.setReservedsalenumber(control.getReservedsalenumber() + number);
	    queryorderDao.update(control);
	    resale = new Reserversale();
	    resale.setMaxid(queryorderDao.getMaxPk("maxid", "Reserversale") + 1);
	    resale.setIvenueid(Long.parseLong(map.get("ivenueid").toString()));
	    resale.setIvenueareaid(Long.parseLong(map.get("ivenueareaid").toString()));
	    resale.setIemployeeid(employeeid);
	    resale.setOrid(orid);
	    resale.setRafttime(map.get("dtstartdate").toString().substring(11, map.get("dtstartdate").toString().length()));
	    resale.setXftime(map.get("dtstartdate").toString().substring(0, 10));
	    resale.setAuditdate(Tools.getDayTimes());
	    resale.setSalenumber(new Long(number));
	    resale.setTripid(Long.parseLong(map.get("tripid").toString()));
	    queryorderDao.save(resale);
	}
	queryorderDao.updateAuditOrderDdzt(orid, iscenicid, employeeid, "02", "");
	List list = queryorderDao.getTOrderDetail(orid, iscenicid);
	TOrder torder = new TOrder();
	BeanUtils.populate(torder, (Map) list.get(0));
	if (torder.getOrph() != null && !torder.getOrph().equals("")) {
		sysparService.sendMessageNew(torder.getOrph(), "0008", orid);
	}
    }

    public void updateFailtureOrderDdzt(String orid, String iscenicid, Long employeeid, String tporid) throws Exception {
	MOrder morder = (MOrder) queryorderDao.get(MOrder.class, orid);
	queryorderDao.updateAuditOrderDdzt(orid, iscenicid, employeeid, "26", tporid);
	List list = queryorderDao.getTOrderDetail(orid, iscenicid);
	TOrder torder = new TOrder();
	BeanUtils.populate(torder, (Map) list.get(0));
	if (torder.getOrph() != null && !torder.getOrph().equals("")) {
		sysparService.sendMessageNew(torder.getOrph(), "0009",orid);
	}
	Useryfk yfk = new Useryfk();
	Integer seqs= this.balancecenterDao.getMaxSeq("Useryfk", "seq");
	yfk.setUsid(morder.getZfusid());
	yfk.setBdate(Tools.getTodayString());
	yfk.setSzmemo("申请单审核失败");
	yfk.setOrderid(orid);
	yfk.setPoint(morder.getMont());
	yfk.setYfklb(1);// 退订转预付款
	yfk.setYfksc("02");
	yfk.setNote("退订转预付款");
	yfk.setCztp(0);
	yfk.setSeq(seqs+1);
	balancecenterDao.useryfksave(yfk);
	//平台预付款 先从平台预付款转出
        List sysList = balancecenterDao
        	.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;
        
        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq")+ 1);
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(morder.getMont());
        ptyfk.setYfksc("13");
        ptyfk.setCztp(0);
        ptyfk.setNote("申请单审核失败,退还预付款");
        balancecenterDao.useryfksave(ptyfk);
//	balancecenterDao.saveUseryfk(morder.getZfusid(), morder.getOrid(), 1, "26", morder.getMont(), "申请订单审核失败,退还预付款");
    }

    public PaginationSupport getOrderByRaft(String orid, String itickettypeid, String date, Long tripid, String iscenicid, int pageSize,
	    int starIndex, String url, String zfzt) {
	return queryorderDao.getOrderByRaftTrip(orid, itickettypeid, date, tripid, iscenicid, pageSize, starIndex, url, zfzt);
    }

    public void updatebackOrder(String[] enforce, String iscenicid,String[] tdorids,Long iemployeeid) throws Exception{
	String orid = "";
	for (int i = 0; i < enforce.length; i++) {
	    orid = enforce[i];
	    updateOrder(orid,iscenicid,tdorids[i],iemployeeid);
	}
    }

    private void updateOrder(String orid, String iscenicid, String tdorid,Long iemployeeid) throws Exception{
	 // 此订单所有的含有趟次的子票
	    List grouptzorderlistByRaftDay = queryorderDao.getTZorderlist(orid);
	    // 产品 趟次 分组
	    List tzorderlist = queryorderDao.getTZorderlistByOrid(orid);
	    TZorderlist zt = null;
	    // 更新库存
	    if (tzorderlist != null && tzorderlist.size() > 0) {// 趟次控制
		for (int i = 0; i < tzorderlist.size(); i++) {
		    zt = new TZorderlist();
		    BeanUtils.populate(zt, (Map) tzorderlist.get(i));
		    queryorderDao.updateStock(orid, zt);
		}
	    }
	    if (grouptzorderlistByRaftDay != null && grouptzorderlistByRaftDay.size() > 0) {
		grouptzorderlistByRaftDay = mergeDayRaft(grouptzorderlistByRaftDay);
		for (int j = 0; j < grouptzorderlistByRaftDay.size(); j++) {// 日控制
		    zt = new TZorderlist();
		    zt = (TZorderlist) grouptzorderlistByRaftDay.get(j);
		    queryorderDao.updateDayControl(orid, zt.getDtstartdate().substring(0, 10), zt.getItickettypeid().toString(), zt.getId()
			    .getIscenicid().toString(), zt.getZnumb().intValue());
		}
	    }
	List tlist = queryorderDao.getTOrderDetail(orid, iscenicid);
	TOrder torder = null;
	if (tlist != null && tlist.size() > 0) {
	    double totaltdsx=0.0;
	    MOrder morder = (MOrder) queryorderDao.get(MOrder.class, orid);
	    Custom custom = (Custom) queryorderDao.get(Custom.class, morder.getUsid());
	    MOrder tdmorder = new MOrder();
	    tdmorder.setOrid(tdorid);
	    tdmorder.setOrtp("02");
	    tdmorder.setOrti(Tools.getNowTime());
	    tdmorder.setZffs("06");
	    tdmorder.setOrda(Tools.getDays());
	    tdmorder.setIsjl(0l);
	    tdmorder.setYhamnt(0.0);
	    tdmorder.setUsid(morder.getUsid());
	    tdmorder.setIsallcp(morder.getIsallcp());
	    tdmorder.setBank(morder.getBank());
	    tdmorder.setZfusid(morder.getZfusid());
	    tdmorder.setBanktime(Tools.getNowTime());
	    tdmorder.setBankdata(Tools.getDays());
	    tdmorder.setBanktime(Tools.getNowTime());
	    tdmorder.setSrid(morder.getOrid());
	    tdmorder.setTpdate(Tools.getDays()+" "+Tools.getNowTime());
	    tdmorder.setTpfs("02");
	    tdmorder.setTpsx(0.0);
	    tdmorder.setTpfl(0.0);
	    tdmorder.setStdt(morder.getStdt());
	    tdmorder.setNote("系统强制退订");
	    tdmorder.setMont(0.0);
	    tdmorder.setZfmont(0.0);
	    tdmorder.setNotef("07");
	    tdmorder.setDdzt("06");
	    tdmorder.setIsc(iemployeeid);
	    Changebackrate fl = null;
	    queryorderDao.save(tdmorder);
	    for (int i = 0; i < tlist.size(); i++) {
		torder = new TOrder();
		BeanUtils.populate(torder, (Map)tlist.get(i));
		torder = (TOrder) queryorderDao.get(TOrder.class, new TOrderId(torder.getOrid(), Long.parseLong(torder.getIscenicid())));
		YOrder oldyorder=(YOrder) queryorderDao.get(YOrder.class, new YOrderId(torder.getId().getOrid(),torder.getId().getIscenicid()));
		YOrder yorder = assemblingYorder(torder, torder.getUsid(), "6");
		yorder.setId(new YOrderId(tdorid, torder.getId().getIscenicid()));
		yorder.setMont(torder.getMont());
		queryorderDao.save(yorder);
		List torderlists = queryorderDao.queryTOrderlistDetail(torder.getId().getOrid(), torder.getId().getIscenicid().toString());
		for (int j = 0; j < torderlists.size(); j++) {
		    double tdmont = 0.0;
		    TOrderlist tolist = new TOrderlist();
		    BeanUtils.populate(tolist, (Map)torderlists.get(j));
		    tolist=(TOrderlist) queryorderDao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(tolist.getOrderlistid()),tolist.getOrid(),Long.parseLong(tolist.getIscenicid())));
		    YOrderlist yolist = new YOrderlist();
		    yolist = assemblingYorderlist(tolist);
		    yolist.setId(new YOrderlistId(new Long(j + 1), tdorid, tolist.getId().getIscenicid()));
		    tolist.setAmnt(0.0);
		    tolist.setNumb(0l);
		    tolist.setJsprice(0.0);
		    queryorderDao.update(tolist);
		    queryorderDao.save(yolist);
		    List tzlists = queryorderDao.getTzorderlists(tolist.getId().getIscenicid(), tolist.getId().getOrid(), tolist.getId()
			    .getOrderlistid());
		    for (int x = 0; x < tzlists.size(); x++) {
			TZorderlist tzlist = (TZorderlist) tzlists.get(x);
			YZorderlist yzlist = new YZorderlist(); 
			yzlist = assemblingYZorderlist(tzlist);
			yzlist.setId(new YZorderlistId(new Long(x + 1), new Long(j + 1), tdorid, tzlist.getId()
				.getIscenicid()));
			Ticketxgz tdrule = morderDao.ticketbackrule(tzlist.getIztickettypeid(), torder.getId().getIscenicid(), custom.getLgtp());
			if (tdrule != null) {// 如果退订规则不为空
			    if (tdrule.getJsfs().equals("0001")) {// 按小时
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = df.parse(tzlist.getDtenddate());
				Date d2 = df.parse(Tools.getNowString());
				long diff = d1.getTime() - d2.getTime();
				long hours = diff / (1000 * 60 * 60);
				fl = morderDao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
			    } else if (tdrule.getJsfs().equals("0002")) {// 按天
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = df.parse(tzlist.getDtenddate());
				Date d2 = df.parse(Tools.getNowString());
				long diff = d1.getTime() - d2.getTime();
				long days = diff / (1000 * 60 * 60 * 24);
				fl = morderDao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
			    } else {// 常年
				fl = morderDao.getflByTime(tdrule.getGzid(), "", "0003");
			    }
			    if(fl!=null){
				yzlist.setMhandcharge(tzlist.getZpric() * fl.getTdfl() * tzlist.getZnumb());
				tdmont += tzlist.getZpric() * fl.getTdfl() * tzlist.getZnumb();
			    }
			}
			tzlist.setZamnt(0.0);
			tzlist.setZnumb(0l);
			tzlist.setZyhamnt(0.0);
			tzlist.setZyhnumb(0l);
			queryorderDao.update(tzlist);
			queryorderDao.save(yzlist);
			tzlist=null;
		    }
		    yolist.setMhandcharge(tdmont);
		    queryorderDao.update(yolist);
		    totaltdsx+=tdmont;
		}
		// TODO 全单退的话 是否还保留原订单信息
//		morder.setDdzt("06");
		morder.setTpmont(morder.getTpmont() + torder.getMont());
		morder.setTpsx(morder.getTpsx()+Math.abs(totaltdsx));
		morder.setIsf(1l);
		yorder.setIsc(iemployeeid);
		yorder.setDdzt("06");
		yorder.setNotef("07");
		yorder.setTpsx(Math.abs(totaltdsx));
		if(oldyorder.getTpmont()!=null){
		    oldyorder.setTpmont(oldyorder.getTpmont()+torder.getMont());
		}else{
		    oldyorder.setTpmont(torder.getMont());
		}
		if(oldyorder.getTpsx()!=null){
		    oldyorder.setTpsx(oldyorder.getTpsx()+Math.abs(totaltdsx));
		}else{
		    oldyorder.setTpsx(Math.abs(totaltdsx));
		}
		oldyorder.setIsf(1l);
		if(tdmorder.getTpmont()!=null){
		    tdmorder.setTpmont(morder.getTpmont() + torder.getMont() - totaltdsx);
		}else{
		    tdmorder.setTpmont(torder.getMont() - totaltdsx);
		}
		if(tdmorder.getTpsx()!=null){
		    tdmorder.setTpsx(tdmorder.getTpsx()+Math.abs(totaltdsx));
		}else{
		    tdmorder.setTpsx(Math.abs(totaltdsx));
		}
//		torder.setDdzt("06");
		torder.setMont(0.0);
		torder.setYhamnt(0.0);
		torder.setZfmont(0.0);
		queryorderDao.update(tdmorder);
		queryorderDao.update(torder);
		queryorderDao.update(oldyorder);
	    }
	    queryorderDao.update(morder);
	    minusUserYfk(morder.getZfusid(), morder.getOrid(), torder.getMont()-totaltdsx,totaltdsx);
	    if(torder.getOrph()!=null&&!torder.getOrph().equals("")){
	    	sysparService.sendMessageNew(torder.getOrph(), "0006", torder.getId().getOrid());
	    }
	}
    }

    private List mergeDayRaft(List tzorderlists) throws IllegalAccessException, InvocationTargetException {
	TZorderlist z = null;
	TZorderlist z1 = null;
	List afterMerge = new ArrayList();
	for (int i = 0; i < tzorderlists.size(); i++) {
	    z = (TZorderlist) tzorderlists.get(i);
	    if (afterMerge.size() < 1) {
		z1 = new TZorderlist();
		BeanUtils.copyProperties(z1, z);
		afterMerge.add(z1);
	    } else {
		for (int j = 0; j < afterMerge.size(); j++) {
		    z1 = (TZorderlist) afterMerge.get(j);
		    if (z1.getDtstartdate().substring(0, 10).equals(z.getDtstartdate().subSequence(0, 10))
			    && z1.getItickettypeid().intValue() == z.getItickettypeid().intValue()) {
			z1.setZnumb(z1.getZnumb().intValue() + z.getZnumb());
			break;
		    } else {
			if (j == afterMerge.size() - 1) {
			    afterMerge.add(z);
			    break;
			}
		    }
		}
	    }
	}
	return afterMerge;
    }

    private boolean minusUserYfk(String zfusid, String orid, double mont, double tdsx) {
	//用户预付款 先增加全退订金额
	Useryfk yfk = new Useryfk();
	Integer seqs= this.balancecenterDao.getMaxSeq("Useryfk", "seq");
	yfk.setUsid(zfusid);
	yfk.setBdate(Tools.getTodayString());
	yfk.setSzmemo("后台强制退订");
	yfk.setOrderid(orid);
	yfk.setPoint(mont);
	yfk.setYfklb(1);// 退订转预付款
	yfk.setYfksc("02");
	yfk.setNote("退订转预付款");	
	yfk.setCztp(0);
	yfk.setSeq(seqs+1);
	balancecenterDao.useryfksave(yfk,0.0);
	//平台预付款 先从平台预付款转出
        List sysList = balancecenterDao
        	.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;
        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq")+ 1);
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(mont);
	ptyfk.setYfklb(-1);// 预付款转出
	ptyfk.setYfksc("13");
	ptyfk.setNote("用户退订退款");
        ptyfk.setCztp(0);
        balancecenterDao.useryfksave(ptyfk,0.0);
        if(tdsx>0){
            //然后收取用户手续费
	    Useryfk yfk1 = new Useryfk();
	    Integer seqs1= this.balancecenterDao.getMaxSeq("Useryfk", "seq");
	    yfk1.setUsid(zfusid);
	    yfk1.setBdate(Tools.getTodayString());
	    yfk1.setSzmemo("后台强制退订");
	    yfk1.setOrderid(orid);
	    yfk1.setPoint(tdsx);
	    yfk1.setYfklb(-1);
	    yfk1.setYfksc("17"); // 消费预付款
	    yfk1.setNote("退订收取手续费");
	    yfk1.setCztp(0);
	    yfk1.setSeq(seqs1+1);
	    balancecenterDao.useryfksave(yfk1,0.0);
	    //平台用户收入手续
	    Useryfk ptyfk1 = new Useryfk();
	    ptyfk1.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq")+ 1);
	    ptyfk1.setUsid(sys.getPmva());// 用户
	    ptyfk1.setBdate(Tools.getTodayString());
	    ptyfk1.setOrderid(orid);
	    ptyfk1.setPoint(tdsx);
	    ptyfk1.setYfklb(1);
	    ptyfk1.setYfksc("16"); // 用户消费预付款  那平台就是用户预付款转入
	    ptyfk1.setNote("退订收入手续费");
	    ptyfk1.setCztp(0);
	    balancecenterDao.useryfksave(ptyfk1,0.0);
	}
	return true;
    }

    private YZorderlist assemblingYZorderlist(TZorderlist target) {
	YZorderlist yzorderlist = new YZorderlist();
	yzorderlist.setIcrowdkindpriceid(target.getIcrowdkindid());
	yzorderlist.setIcrowdkindid(target.getIcrowdkindid());
	yzorderlist.setItickettypeid(target.getItickettypeid());
	yzorderlist.setIztickettypeid(target.getIztickettypeid());
	yzorderlist.setDtstartdate(target.getDtstartdate());
	yzorderlist.setDtenddate(target.getDtenddate());
	yzorderlist.setTripid(target.getTripid());
	yzorderlist.setIvenueid(target.getIvenueid());
	yzorderlist.setIvenueareaid(target.getIvenueareaid());
	yzorderlist.setIvenueseatsid(target.getIvenueseatsid());
	yzorderlist.setZpric(target.getZpric());
	yzorderlist.setZnumb(target.getZnumb());
	yzorderlist.setZyhnumb(target.getZyhnumb());
	yzorderlist.setZyhamnt(target.getZyhamnt());
	yzorderlist.setZamnt(target.getZamnt());
	yzorderlist.setJsprice(target.getJsprice());
	yzorderlist.setSqnumber(target.getZnumb());
	yzorderlist.setTdfl(0.0);
	return yzorderlist;
    }

    public YOrder assemblingYorder(TOrder target, String usid, String ddzt) {
	YOrder yorder = new YOrder();
	yorder.setScenictype("01");
	yorder.setUsid(usid);
	yorder.setIbusinessid(target.getIbusinessid());
	yorder.setIregionalid(target.getIregionalid());
	yorder.setSztravelbillno(target.getSztravelbillno());
	yorder.setTdlx(target.getTdlx());
	yorder.setDdzt(ddzt);
	yorder.setDtstartdate(target.getDtstartdate());
	yorder.setDtenddate(target.getDtenddate());
	yorder.setDyusid(target.getDyusid());
	yorder.setOrzj(target.getOrzj());
	yorder.setOrnm(target.getOrnm());
	yorder.setOrph(target.getOrph());
	yorder.setOrhm(target.getOrhm());
	yorder.setFaxno(target.getFaxno());
	yorder.setOrzj(target.getOrzj());
	yorder.setMont(target.getMont());
	yorder.setZfmont(target.getZfmont());
	yorder.setTpdate(Tools.getDays() + " " + Tools.getNowTime());
	yorder.setNotef("07");
	yorder.setTpfs("02");
	return yorder;
    }

    public YOrderlist assemblingYorderlist(TOrderlist target) {
	YOrderlist yorderlist = new YOrderlist();
	yorderlist.setItickettypeid(target.getItickettypeid());
	yorderlist.setIcrowdkindpriceid(target.getIcrowdkindpriceid());
	yorderlist.setIcrowdkindid(target.getIcrowdkindid());
	yorderlist.setDtstartdate(target.getDtstartdate());
	yorderlist.setDtenddate(target.getDtenddate());
	yorderlist.setNumb(target.getNumb());
	yorderlist.setYhamnt(target.getYhamnt());
	yorderlist.setYhnumb(target.getYhnumb());
	yorderlist.setPric(target.getPric());
	yorderlist.setAmnt(target.getAmnt());
	yorderlist.setMhandcharge(0.0);
	return yorderlist;
    }

    public List getAuditNum(QueryOrder order) {
	// TODO Auto-generated method stub
	return queryorderDao.getAuditNum(order);
    }

    public List getAllbussiness() {
	return queryorderDao.getAllbussiness();
    }
    
    /**
     * *
     * Describe:现场支付订单查询
     * @see com.ectrip.report.system.order.service.iservice.IQueryOrderService#xczfQueryAllOrder(com.ectrip.model.employee.Esfemployeetab, com.ectrip.model.order.QueryOrder, int, int, java.lang.String)
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return
     * @author lijingrui
     * Date:2014-3-11
     */
	public PaginationSupport xczfQueryAllOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url){
		return queryorderDao.xczfQueryAllOrder(esfemployeetab, order, page, pageSize, url);
	}
	/**
	 * 
	 * Describe:订单人数统计
	 * @author:zhangwubin
	 * @param esfemployeetab
	 * @param order
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-1-28
	 */
	public PaginationSupport orderNumbQuery(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url){
		return queryorderDao.orderNumbQuery(esfemployeetab, order, page, pageSize, url);
	}
	
	/**
	 * 导出excel操作，获取订单信息
	 * Describe:
	 * @author:xiaogaoxiang
	 * @param esfemployeetab
	 * @param order
	 * @return
	 * return:List
	 * Date:2014-10-6
	 */
	public List getAllOrderInfo(Esfemployeetab esfemployeetab, QueryOrder order) {
		// TODO Auto-generated method stub
		return queryorderDao.getAllOrderInfo(esfemployeetab, order);
		
		
		
	}
}
