package com.ectrip.ec.order.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.common.CommonUtil;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.Seatyordertab;
import com.ectrip.ec.model.order.SeatyordertabId;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ec.model.usernumjf.Usernumjflist;
import com.ectrip.ec.model.usernumjf.UsernumjflistId;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.ec.order.dao.idao.ISeatorderDAO;
import com.ectrip.ec.order.service.iservice.ISeatorderService;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.RefundbillsRequest;
import com.ectrip.hqyt.model.response.JSONRefundBill;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.venuemarketing.Seatstatustab;
import com.ectrip.ticket.model.venuemarketing.SeatstatustabId;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.ticket.model.venuemarketing.Venueseats;

public class SeatorderService extends GenericService implements
		ISeatorderService {
	private ISeatorderDAO seatorderDAO;
	public IMOrderDAO morderdao;
	
	@Autowired
    private TicketService ticketService;
//	private IStatisicsDAO statisicsDao;;
	private IBalanceCenterDAO balancecenterDao;

	public YZorderlist getsxfYZorderlist(YZorderlist oldtdlist, String usid)
			throws ParseException {
		Custom custom = (Custom) seatorderDAO.get(Custom.class, usid);
		Changebackrate fl = null;
		boolean flag = true;
		Tripprdcontroldetailtab tdcd = null;
		if (oldtdlist.getTripid() > 0 && oldtdlist.getIvenueseatsid() > 0) {
			// 场馆票
			Long itripprdcontrolid = oldtdlist.getIse();
			Tripprdcontroltab tdc = (Tripprdcontroltab) seatorderDAO.get(
					Tripprdcontroltab.class, itripprdcontrolid);
			if (tdc.getByisuse() == 0) {
				flag = false;
			} else {
				List tdcdlist = seatorderDAO
						.find(" from Tripprdcontroldetailtab where itripprdcontrolid="
								+ tdc.getItripprdcontrolid()
								+ " and itripid="
								+ oldtdlist.getTripid());
				tdcd = (Tripprdcontroldetailtab) tdcdlist.get(0);
				if (tdcd.getByisuse() == 0) {
					flag = false;
				}
			}
		}
		double tdfl = 0.0;
		if (flag) {// 没有竹筏控制或者竹筏状态正常时
			Ticketxgz tdrule = morderdao.ticketbackrule(oldtdlist
					.getIztickettypeid(), oldtdlist.getId().getIscenicid(),
					custom.getLgtp());

			if (tdrule != null) {// 如果退订规则不为空
				if (tdrule.getJsfs().equals("0001")) {// 按小时

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date d1 = null;
					Date d2 = null;
					if (tdcd != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
						d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
								10)
								+ " " + tdcd.getStarttime() + ":00");
						d2 = df.parse(Tools.getNowString());
					} else {
						d1 = df.parse(oldtdlist.getDtenddate());
						d2 = df.parse(Tools.getNowString());
					}
					if (d1.before(d2)) {
						tdfl = tdrule.getXyrate2();
					} else {
						long diff = d1.getTime() - d2.getTime();
						long hours = diff / (1000 * 60 * 60);
						fl = morderdao.getflByTime(tdrule.getGzid(),
								String.valueOf(hours), "0001");
						if (fl == null) {
							tdfl = 0.0;
						} else {
							tdfl = fl.getTdfl();
						}
					}
				} else if (tdrule.getJsfs().equals("0002")) {// 按天
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date d1 = null;
					Date d2 = null;
					if (tdcd != null) {
						d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
								10)
								+ " " + tdcd.getStarttime() + ":00");
						d2 = df.parse(Tools.getNowString());
					} else {
						d1 = df.parse(oldtdlist.getDtenddate());
						d2 = df.parse(Tools.getNowString());
					}
					if (d1.before(d2)) {
						tdfl = tdrule.getXyrate2();
					} else {
						long diff = d1.getTime() - d2.getTime();
						long days = diff / (1000 * 60 * 60 * 24);
						fl = morderdao.getflByTime(tdrule.getGzid(),
								String.valueOf(days), "0002");
						if (fl == null) {
							tdfl = 0.0;
						} else {
							tdfl = fl.getTdfl();
						}
					}
				} else {// 常年
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date d1 = null;
					Date d2 = null;
					if (tdcd != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
						d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
								10)
								+ " " + tdcd.getStarttime() + ":00");
						d2 = df.parse(Tools.getNowString());
					} else {
						d1 = df.parse(oldtdlist.getDtenddate());
						d2 = df.parse(Tools.getNowString());
					}
					if (d1.before(d2)) {
						tdfl = tdrule.getXyrate2();
					} else {
						fl = morderdao
								.getflByTime(tdrule.getGzid(), "", "0003");
						tdfl = fl.getTdfl();
					}
				}

			}
		}
		oldtdlist.setTdfl(tdfl);
		oldtdlist
				.setMhandcharge((oldtdlist.getZnumb() - oldtdlist.getZyhnumb())
						* oldtdlist.getZpric() * tdfl);
		// tdmont = oldtdlist.getZpric() * tdfl * cnum;
		return oldtdlist;
	}

	public Map saveeditseat(Custom com, MOrder mor, TOrder torder,
			List torderlistlist, String neworid) throws Exception {
		Map returnmap = new HashMap();
		Numjifenset set = ticketService.getNumjifenset(torder.getId()
				.getIscenicid().toString());
		long yearjf = 0;// 年积分
		long monthjf = 0;// 月积分
		double tpmont = 0;// 退款金额
		double tpyhmont = 0;
		double sxf = 0;// 手续费
		List yorderlistlist = new ArrayList();
		for (int i = 0; i < torderlistlist.size(); i++) {
			TOrderlist t = (TOrderlist) torderlistlist.get(i);
			if (t.getIoffersschemeid() > 0) {
				Edpofferschemetab ef = (Edpofferschemetab) seatorderDAO.get(
						Edpofferschemetab.class, t.getIoffersschemeid());
				// 计算新的优惠数量
				Long tdhyhnumb = ((t.getNumb() - t.getNnumb()) / ef
						.getImultiples()) * ef.getIoffernum();
				t.setTdyhnumb(t.getYhnumb() - tdhyhnumb);
			} else {
				t.setTdyhnumb(0L);
			}
			t.setNumb(t.getNumb() - t.getNnumb());
			t.setAmnt(t.getNumb() * t.getPric());
			t.setYhnumb(t.getYhnumb() - t.getTdyhnumb());
			t.setYhamnt(t.getYhnumb() * t.getPric());
			YOrderlist y = new YOrderlist();
			YOrderlistId yid = new YOrderlistId();

			yid.setOrid(neworid);
			yid.setIscenicid(t.getId().getIscenicid());
			yid.setOrderlistid(t.getId().getOrderlistid());
			y.setId(yid);
			y.setItickettypeid(t.getItickettypeid());
			y.setIcrowdkindid(t.getIcrowdkindid());
			y.setIcrowdkindpriceid(t.getIcrowdkindpriceid());
			y.setDtstartdate(t.getDtstartdate());
			y.setDtenddate(t.getDtenddate());
			y.setNumb(t.getNnumb());
			y.setPric(t.getPric());
			y.setAmnt(t.getNnumb() * t.getPric());
			y.setYhnumb(t.getTdyhnumb());
			y.setYhamnt(t.getTdyhnumb() * t.getPric());
			y.setIoffersschemeid(t.getIoffersschemeid());
			y.setMhandcharge(0D);
			List<Seatordertab> seatlist = t.getSlist();
			y.setSlist(seatlist);
			/** 计算积分 **/
			if (set != null) {
				Numjifensetlist detail = ticketService.getSalesRule(
						t.getItickettypeid(), set.getNid(),
						t.getIcrowdkindid(), torder.getIbusinessid(),
						t.getDtstartdate());
				if (detail != null) {
					long jf = 0;
					if ((t.getNnumb() - t.getNnumb()) % detail.getIint3() > 0) {
						jf = (t.getNnumb() - y.getNumb()) / detail.getIint3()
								* detail.getIint4() + detail.getIint4();
					} else {
						jf = (t.getNnumb() - y.getNumb()) / detail.getIint3()
								* detail.getIint4();
					}
					if (detail.getXffs().equals("03")) {// 月积分
						jf = t.getIsi() - jf;// 一定会是0或者负数
						monthjf = monthjf + jf;
						t.setIsi(t.getIsi() - jf);
						y.setIsi(-1 * jf);

					} else {
						jf = t.getIsh() - jf;
						yearjf = yearjf + jf;
						t.setIsh(t.getIsh() - jf);
						y.setIsh(-1 * jf);

					}

				}
			}
			List tzlistlist = seatorderDAO
					.find(" from TZorderlist  where id.orid='"
							+ t.getId().getOrid() + "' and id.orderlistid="
							+ t.getId().getOrderlistid()
							+ " and id.iscenicid= " + t.getId().getIscenicid());
			List yzlistlist = new ArrayList();
			for (int j = 0; j < tzlistlist.size(); j++) {
				TZorderlist tz = (TZorderlist) tzlistlist.get(j);
				tz.setZnumb(t.getNumb());
				tz.setZamnt(tz.getZnumb() * tz.getZpric());
				tz.setZyhnumb(t.getYhnumb());
				tz.setZyhamnt(tz.getZyhnumb() * tz.getZpric());
				YZorderlist yz = new YZorderlist();
				YZorderlistId yzid = new YZorderlistId();
				yzid.setOrid(neworid);
				yzid.setOrderlistid(tz.getId().getOrderlistid());
				yzid.setIscenicid(tz.getId().getIscenicid());
				yzid.setZorderlistid(tz.getId().getZorderlistid());
				yz.setId(yzid);
				yz.setIcrowdkindid(tz.getIcrowdkindid());
				yz.setIcrowdkindpriceid(tz.getIcrowdkindpriceid());
				yz.setDtstartdate(tz.getDtstartdate());
				yz.setDtenddate(tz.getDtenddate());
				yz.setItickettypeid(tz.getItickettypeid());
				yz.setIztickettypeid(tz.getIztickettypeid());
				yz.setIvenueid(tz.getIvenueid());
				yz.setIvenueareaid(tz.getIvenueareaid());
				yz.setIvenueseatsid(tz.getIvenueseatsid());
				yz.setTripid(tz.getTripid());
				yz.setZnumb(y.getNumb());
				yz.setZpric(tz.getZpric());
				yz.setZamnt(yz.getZnumb() * tz.getZpric());
				yz.setZyhnumb(y.getYhnumb());
				yz.setZyhamnt(y.getYhnumb() * tz.getZpric());
				yz.setIse(tz.getIse());
				yz = this.getsxfYZorderlist(yz, com.getUsid());
				tpmont = tpmont + yz.getZamnt();
				tpyhmont = tpyhmont + yz.getZyhamnt();
				sxf = sxf + yz.getMhandcharge();
				y.setMhandcharge(y.getMhandcharge() + yz.getMhandcharge());
				// 计算手续费
				yzlistlist.add(yz);
			}
			t.setZorderlist(tzlistlist);
			y.setYzorderlistlist(yzlistlist);
			yorderlistlist.add(y);
			// 计算积分返回
		}
		// 更新积分
		Map map = getMonthDate(Integer.parseInt(mor.getStdt().substring(0, 4)),
				Integer.parseInt(mor.getStdt().substring(5, 7)));
		if (monthjf > 0) {
			this.savejf(monthjf, "03", neworid, mor.getZfusid(), torder.getId()
					.getIscenicid(), "02", set.getNid(), map);
		}
		if (yearjf > 0) {
			this.savejf(yearjf, "04", neworid, mor.getZfusid(), torder.getId()
					.getIscenicid(), "02", set.getNid(), map);
		}

		// 更新原订单
		MOrder om = (MOrder) seatorderDAO.get(MOrder.class, mor.getOrid());

		for (int i = 0; i < torderlistlist.size(); i++) {
			TOrderlist t = (TOrderlist) torderlistlist.get(i);
			List tzlistlist = t.getZorderlist();
			for (int j = 0; j < tzlistlist.size(); j++) {
				TZorderlist tz = (TZorderlist) tzlistlist.get(j);
				if(tz.getZnumb()==0L){
					seatorderDAO.delete(tz);
				}else{
					seatorderDAO.update(tz);
				}
			}
			if(t.getNumb()==0L){
				seatorderDAO.delete(t);
			}else{
				seatorderDAO.update(t);
			}
			List seatlist = t.getSlist();
			for (int j = 0; j < seatlist.size(); j++) {
				Seatordertab s = (Seatordertab) seatlist.get(j);
				seatorderDAO.delete(s);

				SeatstatustabId sttid = new SeatstatustabId();
				sttid.setIseatid(s.getIseatid());
				sttid.setItripid(s.getItripid());
				sttid.setIvenueid(s.getIvenueid());
				sttid.setIvenueareaid(s.getIvenueareaid());
				sttid.setStartdate(s.getStartdate().substring(0, 10));
				Seatstatustab stt = (Seatstatustab) seatorderDAO.get(
						Seatstatustab.class, sttid);
				seatorderDAO.delete(stt);
			}

		}
		if (om.getTpmont() == null) {
			om.setTpmont(0D);
		}
		if (om.getTpsx() == null) {
			om.setTpsx(0D);
		}
		if (om.getIsi() == null) {
			om.setIsi(0l);
		}
		if (om.getIsh() == null) {
			om.setIsh(0l);
		}
		om.setIsi(om.getIsi() - monthjf);
		om.setIsh(om.getIsh() - yearjf);
		if(om.getTpmont()!=null && !"".equals(om.getTpmont())){
			om.setTpmont(tpmont + om.getTpmont());
		}else{
			om.setTpmont(tpmont);
		}
		if(om.getTpsx()!=null && !"".equals(om.getTpsx())){
			om.setTpsx(om.getTpsx() + sxf);
		}else{
			om.setTpsx(sxf);
		}

		
		TOrder ot = (TOrder) seatorderDAO.get(TOrder.class, torder.getId());
		YOrder yt = (YOrder) seatorderDAO.get(YOrder.class, new YOrderId(torder.getId().getOrid(), torder.getId().getIscenicid()));
		ot.setMont(ot.getMont()-tpmont);
		ot.setZfmont(ot.getZfmont()-tpmont);
		ot.setYhamnt(ot.getYhamnt()-tpyhmont);
		if(yt.getTpmont()!=null && !"".equals(yt.getTpmont())){
			yt.setTpmont(tpmont+yt.getTpmont());
		}else{
			yt.setTpmont(tpmont);
		}
		if(yt.getTpsx()!=null && !"".equals(yt.getTpsx())){
			yt.setTpsx(sxf+yt.getTpsx());
		}else{
			yt.setTpsx(sxf);
		}
		if (ot.getIsi() == null) {
			ot.setIsi(0l);
		}
		if (ot.getIsh() == null) {
			ot.setIsh(0l);
		}
		ot.setIsi(om.getIsi() - monthjf);
		ot.setIsh(om.getIsh() - yearjf);
		
		int numb = 0;
		List oldTorderlist = this.seatorderDAO.getTorderList(torder.getId().getOrid(), torder.getId().getIscenicid());
		if(oldTorderlist!=null &&!oldTorderlist.isEmpty()){
			for(int j=0;j<oldTorderlist.size();j++){
				TOrderlist tls = (TOrderlist) oldTorderlist.get(j);
				numb+=tls.getNumb().intValue();
			}
		}
		
		if (numb == 0) {
			ot.setDdzt("11");
			yt.setDdzt("11");
		}
		seatorderDAO.update(ot);
		seatorderDAO.update(yt);
		if (numb == 0) {
			om.setDdzt("11");
		}
		seatorderDAO.update(om);
		// 新增退订单
		MOrder newm = new MOrder();
		newm.setOrid(neworid);
		newm.setOrtp("02");// 订单类型 03：增量订单
		newm.setUsid(mor.getUsid());
		newm.setOrda(Tools.getDays());
		newm.setOrti(Tools.getNowString().substring(11,
				Tools.getNowString().length()));
		newm.setIsallcp(0l);// 是否全部出票
		newm.setZffs("06");// 支付方式
		newm.setBank("92");// 支付银行
		newm.setZfusid(mor.getZfusid());// 订单支付人
		newm.setPayorid("");// 支付订单号
		newm.setBankdata(Tools.getDays());// 支付日期
		newm.setBanktime(Tools.getNowTimeString());// 支付时间
		newm.setSrid(mor.getOrid());// 对应原订单号
		if (mor.getIsjl() != null && mor.getIsjl().intValue() == 1) {
			newm.setIsjl(1l);
		} else {
			newm.setIsjl(0l);
		}
		newm.setTpfs("02");
		newm.setYhamnt(tpyhmont);// 优惠金额
		newm.setMont(tpmont);
		newm.setZfmont(tpmont - tpyhmont);// 支付的金额
		seatorderDAO.save(newm);
		YOrder yorder = new YOrder();
		yorder.setId(new YOrderId(newm.getOrid(), torder.getId().getIscenicid()));
		// 根据编号找服务商类型
		Esbscenicareatab esb = (Esbscenicareatab) this.get(
				Esbscenicareatab.class, torder.getId().getIscenicid());
		yorder.setScenictype(esb.getScenictype());// 服务商类别 01 景区 06酒店 07 旅行社
													// 08 超市 09 租赁公司
		yorder.setUsid(newm.getUsid());// 游客编号
		yorder.setIbusinessid(com.getIbusinessid());// 业务id
		yorder.setSztravelbillno("");// 行程单号
		yorder.setIregionalid(0l);// 客源地id
		yorder.setTdlx("");// 团队性质
		yorder.setDtstartdate(torder.getDtstartdate());// 游览开始日期
		yorder.setDtenddate(torder.getDtenddate());// 游览结束日期
		yorder.setDyusid(torder.getDyusid());// 导游id
		yorder.setOrnm(torder.getOrnm());// 领票人姓名
		yorder.setOrzj(torder.getOrzj());// 领票人证件类别
		yorder.setOrhm(torder.getOrhm());// 领票人证件号码
		yorder.setFaxno(torder.getFaxno());// 传真号
		yorder.setOrph(torder.getOrph());// 领票人电话
		yorder.setMont(tpmont);
		yorder.setYhamnt(0.0);// 优惠金额
		yorder.setZfmont(tpmont - tpyhmont);// 支付的金额
		yorder.setYoufei(tpyhmont);// 邮费
		yorder.setTpfs("02");
		yorder.setTpmont(tpmont - tpyhmont);
		yorder.setTpsx(sxf);
		seatorderDAO.save(yorder);
		for (int i = 0; i < yorderlistlist.size(); i++) {
			YOrderlist y = (YOrderlist) yorderlistlist.get(i);
			seatorderDAO.save(y);
			List ylistlist = y.getYzorderlistlist();
			for (int j = 0; j < ylistlist.size(); j++) {
				YZorderlist yz = (YZorderlist) ylistlist.get(j);
				seatorderDAO.save(yz);
			}
			List slist = y.getSlist();
			if (slist != null && slist.size() > 0) {
				for (int j = 0; j < slist.size(); j++) {
					Seatordertab s = (Seatordertab) slist.get(j);
					Seatyordertab sy = new Seatyordertab();
					SeatyordertabId syid = new SeatyordertabId();
					syid.setIscenicid(s.getId().getIscenicid());
					syid.setOrderlistid(s.getId().getOrderlistid());
					syid.setOrid(neworid);
					syid.setZorderlistid(s.getId().getZorderlistid());
					syid.setSeq(s.getId().getSeq());
					sy.setId(syid);
					sy.setIprogramid(s.getIprogramid());
					sy.setItripid(s.getItripid());
					sy.setItripprdcontrolid(s.getItripprdcontrolid());
					sy.setIvenueid(s.getIvenueid());
					sy.setIvenueareaid(s.getIvenueareaid());
					sy.setStartdate(s.getStartdate());
					sy.setDtmakedate(Tools.getDayTime());
					sy.setIseatid(s.getIseatid());
					sy.setIsvalid(1L);
					seatorderDAO.save(sy);
				}
			}
		}
		boolean hqyt = CommonUtil.isHqyt();
		if(hqyt){
			if(StringUtils.isBlank(om.getNoteh())){
				throw new RuntimeException("支付号不存在");
			}
			if(hqyt){
				MOrder morder = (MOrder) seatorderDAO.get(MOrder.class,neworid);
				HqytClient client = new HqytClient();
				RefundbillsRequest request = new RefundbillsRequest();
				request.setId(Long.parseLong(om.getNoteh()));
				request.setRefundMoney(tpmont - tpyhmont - sxf);
				request.setRefundOrid(neworid);
				request.setReason(com.getUsid()+"操作网上退订");
				request.setMemo("退款金额:"+(tpmont - tpyhmont)+";手续费:"+sxf);
				try{
					JSONRefundBill refundBill = client.refundbills(request);
					if(refundBill != null){
						morder.setNoteh(refundBill.getInvoice().getId().toString());
						seatorderDAO.update(morder);
					}else{
						throw new RuntimeException("退订失败:申请退订失败");
					}
				}catch (Exception e){
					throw new RuntimeException("退订失败:"+e.getMessage());
				}
			}
		}else{
			// 保存预付款
			this.minusUserYfk(com.getUsid(), mor.getZfusid(), neworid, tpmont
					- tpyhmont, sxf, "02");
		}

		// 添加日志
		Orderlog log = new Orderlog();
		log.setLogid(seatorderDAO.getMaxPk("logid", "Orderlog") + 1);
		log.setOrid(om.getOrid());
		log.setStlg("0157");
		log.setBrief("修改订单");
		log.setNote("");
		String lognote = "退订票务:";
		for (int i = 0; i < yorderlistlist.size(); i++) {
			YOrderlist y = (YOrderlist) yorderlistlist.get(i);
			Edmtickettypetab e = (Edmtickettypetab) seatorderDAO.get(
					Edmtickettypetab.class, y.getItickettypeid());
			lognote = lognote + e.getSztickettypename() + y.getNumb()
					+ "张,对应座位:";
			List slist = y.getSlist();
			for (int j = 0; j < slist.size(); j++) {
				Seatordertab s = (Seatordertab) slist.get(j);
				Venueseats vs = seatorderDAO.getvenueseats(s.getIvenueid(),
						s.getIseatid());
				if (j == (slist.size() - 1)) {
					lognote = lognote + vs.getSzvenueseatsname();
				} else {
					lognote = lognote + vs.getSzvenueseatsname() + ";";
				}
			}
		}
		log.setNote(lognote);
		log.setIemployeeid(null);
		log.setUsid(com.getUsid());
		log.setLogtype(Long.parseLong("0"));
		log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
		seatorderDAO.save(log);
		returnmap.put("result", true);
		returnmap.put("errtp", "0");
		return returnmap;
	}

	private boolean minusUserYfk(String currentusid, String zfusid,
			String orid, double mont, double tdsx, String type) {
		// 用户预付款 先增加全退订金额

		Useryfk yfk = new Useryfk();
		Integer seqs = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
		yfk.setUsid(zfusid);
		yfk.setBdate(Tools.getTodayString());
		yfk.setSzmemo("用户  [" + currentusid + "]修改订单");
		yfk.setOrderid(orid);
		yfk.setPoint(mont);
		if (type.equals("03")) {
			yfk.setYfklb(-1);
			yfk.setYfksc("03"); // 消费预付款
			yfk.setNote("订单消费");
		} else {
			yfk.setYfklb(1);// 退订转预付款
			yfk.setYfksc("02");
			yfk.setNote("退订转预付款");
		}
		yfk.setCztp(0);
		yfk.setSeq(seqs + 1);
		balancecenterDao.useryfksave(yfk);

		// 平台预付款 先从平台预付款转出
		List sysList = morderdao
				.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
		Sysparv5 sys = null;

		sys = (Sysparv5) sysList.get(0);// 取平台帐号
		Useryfk ptyfk = new Useryfk();
		ptyfk.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq") + 1);
		ptyfk.setUsid(sys.getPmva());// 用户
		ptyfk.setBdate(Tools.getTodayString());
		ptyfk.setOrderid(orid);
		ptyfk.setPoint(mont);
		if (type.equals("03")) {
			ptyfk.setYfklb(1);
			ptyfk.setYfksc("14"); // 用户消费预付款 那平台就是用户预付款转入
			ptyfk.setNote("用户订单消费");
		} else {
			ptyfk.setYfklb(-1);// 预付款转出
			ptyfk.setYfksc("13");
			ptyfk.setNote("用户退订退款");
		}
		ptyfk.setCztp(0);
		balancecenterDao.useryfksave(ptyfk);

		if (tdsx > 0) {
			// 然后收取用户手续费
			Useryfk yfk1 = new Useryfk();
			Integer seqs1 = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
			yfk1.setUsid(zfusid);
			yfk1.setBdate(Tools.getTodayString());
			yfk1.setSzmemo("用户  [" + currentusid + "]修改订单");
			yfk1.setOrderid(orid);
			yfk1.setPoint(tdsx);
			yfk1.setYfklb(-1);
			yfk1.setYfksc("17"); // 消费预付款
			yfk1.setNote("退订收取手续费");
			yfk1.setCztp(0);
			yfk1.setSeq(seqs1 + 1);
			balancecenterDao.useryfksave(yfk1);

			// 平台用户收入手续
			Useryfk ptyfk1 = new Useryfk();
			ptyfk1.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq") + 1);
			ptyfk1.setUsid(sys.getPmva());// 用户
			ptyfk1.setBdate(Tools.getTodayString());
			ptyfk1.setOrderid(orid);
			ptyfk1.setPoint(tdsx);
			ptyfk1.setYfklb(1);
			ptyfk1.setYfksc("16"); // 用户消费预付款 那平台就是用户预付款转入
			ptyfk1.setNote("退订收入手续费");
			ptyfk1.setCztp(0);
			balancecenterDao.useryfksave(ptyfk1);
		}
		return true;
	}

	public boolean savejf(long jfnumb, String type, String neworid,
			String zfusid, Long iscenicid, String ortp, Long nid, Map map) {
		Usernumjflist usernumjifenlist = new Usernumjflist();
		usernumjifenlist.setId(new UsernumjflistId(neworid, zfusid));
		usernumjifenlist.setNid(BigDecimal.valueOf(nid));
		usernumjifenlist.setJftp("03");// 月
		usernumjifenlist.setEtdt(Tools.getDays());
		usernumjifenlist.setStdt(Tools.getDays());
		usernumjifenlist.setStdt2(Tools.getDays());
		usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
		usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
		usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
		if (ortp.equals("02")) {
			usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
		} else {
			usernumjifenlist.setJflb(BigDecimal.valueOf(1));
		}
		usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(jfnumb)));
		usernumjifenlist.setStdt(Tools.getDays());
		usernumjifenlist.setZusid(zfusid);
		usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
		usernumjifenlist.setZusid(zfusid);
		this.save(usernumjifenlist);
		Usernumjf userjf = null;
		if (usernumjifenlist.getJftp().equals("03")) {// 月
			String userjfJSON = ticketService.getJifenByUser(map.get("startDate")
					.toString(), map.get("endDate").toString(), zfusid,
					new Long(1), iscenicid);
			userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
			
			
		} else {
			String userjfJSON = ticketService.getJifenByUser(map.get("startDate")
					.toString().substring(0, 4)
					+ "-01-01", map.get("endDate").toString().substring(0, 4)
					+ "-12-31", zfusid, new Long(2), iscenicid);
			
			userjf = JSON.parseObject(userjfJSON, Usernumjf.class);
		}
		if (usernumjifenlist.getJftp().equals("03")) {// 月
			userjf.setYpoint(userjf.getYpoint() + jfnumb
					* new Long(usernumjifenlist.getJflb().toString()));
		} else {
			userjf.setYpoint(userjf.getYpoint() + jfnumb
					* new Long(usernumjifenlist.getJflb().toString()));
		}
		this.update(userjf);
		return true;
	}

	public static Map getMonthDate(int year, int month) {
		Map map = new HashMap();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		String thisMonthStart = format.format(cal.getTime());// 本月开始（本月1号）;
		System.out.println("开始->" + thisMonthStart);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String thisMonthEnd = format.format(cal.getTime());
		cal.clear();
		System.out.println("结束->" + thisMonthEnd);
		map.put("startDate", thisMonthStart);
		map.put("endDate", thisMonthEnd);
		return map;
	}

	@Override
	public List gettorderseatlist(String orid, Long iscenicid, Long orderlistid) {
		return seatorderDAO.gettorderseatlist(orid, iscenicid, orderlistid);
	}

	@Override
	public Seatordertab getSeatordertab(String orid, Long iscenicid, Long iseatid) {
		return seatorderDAO.getSeatordertab(orid, iscenicid, iseatid);
	}
}
