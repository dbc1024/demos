package com.ectrip.ticket.permitenter.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.order.Seatsaletab;
import com.ectrip.ticket.model.order.SeatsaletabId;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.StscomticketsalesdetailstabId;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.StssalessettlementtabId;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvoucherdetailstabId;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;
import com.ectrip.ticket.model.order.Stsschecktab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.StssoldticketsubtabId;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.order.Ticketprintlist;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Seatlockdetail;
import com.ectrip.ticket.model.venuemarketing.Seatlocktab;
import com.ectrip.ticket.model.venuemarketing.Seatstatustab;
import com.ectrip.ticket.model.venuemarketing.SeatstatustabId;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.warehouse.Iompersonalticketdetails;
import com.ectrip.ticket.model.warehouse.Kcpersonalticketdetailstab;
import com.ectrip.ticket.permitenter.dao.IVoucherDAO;
import com.ectrip.ticket.sale.service.SaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaveVenueService;
import com.ectrip.ticket.stocks.dao.idao.IStocksWareDAO;
import com.ectrip.ticket.stocks.service.iservice.IStocksWareService;

public class VoucherDAO extends GenericDao implements IVoucherDAO {
	
	private static long dunum = 0;

	/**
	 * * Describe:根据服务商查询可现场销售的产品
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showAllticket(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui Date:2012-10-23
	 */
	public List showAllticket(Long iscenicid, Long ibusinessid, String startdate) {
		StringBuffer sql = new StringBuffer();
		if (startdate == null || startdate.equals("")) {
			startdate = Tools.getDays();
		}
		sql.append(" from Edmtickettypetab edm where edm.byisuse=1 and edm.itickettypeid in(select itickettypeid from Edmcrowdkindpricetab where isscene=1 and ibusinessid="
				+ ibusinessid
				+ " and byisuse=1 and startdata<='"
				+ startdate
				+ "' and enddata>='" + startdate + "' )");
		if (iscenicid != null && !"".equals(iscenicid)) {
			sql.append(" and edm.iscenicid=" + iscenicid);
		}
		List list = this.find(sql.toString());

		return list;
	}

	/**
	 * * Describe:查出所有业务类型
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#businessList()
	 * @return
	 * @author lijingrui Date:2012-10-26
	 */
	public List businessList(Long ibusinessid) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer(
				" from Edmbusinesstab where byisuse=1 "); // and szbusinesscode
															// in ('01','03')
		if (ibusinessid != null && !"".equals(ibusinessid)) {
			hsql.append(" and ibusinessid=" + ibusinessid);
		}
		hsql.append(" order by ibusinessid ");
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * * Describe:显示某产品某业务的价格
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showpriceviewup(java.lang.Long,
	 *      java.lang.Long)
	 * @param itickettypeid
	 * @param ibusinessid
	 * @return
	 * @author lijingrui Date:2012-10-26
	 * @throws Exception
	 */
	public List showpriceviewup(Long iscenicid, Long itickettypeid,
			Long ibusinessid, String startdate) throws Exception {
		if (itickettypeid == null) {
			List lst = showAllticket(iscenicid, ibusinessid, startdate);
			Edmtickettypetab edmtick = (Edmtickettypetab) lst.get(0);
			itickettypeid = edmtick.getItickettypeid();
		}
		if (startdate == null || startdate.equals("")) {
			startdate = Tools.getDays();
		}
		String sql = " from Edmcrowdkindpricetab crow where crow.itickettypeid="
				+ itickettypeid
				+ " and crow.ibusinessid="
				+ ibusinessid
				+ " and crow.startdata<='"
				+ startdate
				+ "' and crow.enddata>='" + startdate + "' ";
		List list = this.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			return list;
		}
		// Edmcrowdkindpricetab crowkind = (Edmcrowdkindpricetab) list.get(0);
		// return list;
	}

	/**
	 * * Describe:根据业务类型获取相应的用户
	 * 
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherService#getCustomview(java.lang.Long)
	 * @param ibusinessid
	 * @return
	 * @author lijingrui Date:2012-11-6
	 */
	public List getCustomview(Long ibusinessid) {
		List<Map> list = new ArrayList();
		if (ibusinessid.intValue() == 2) {
			try {
				list = this
						.find(" from Custom where ttlb='01' and  ibusinessid="
								+ ibusinessid
								+ " and status='01' and ustp!='01' order by bname");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		} else if(ibusinessid.intValue()== 99 ){
			try {
				list = this
						.find(" from Custom where ttlb='99' and  ibusinessid= 2 and status='01' and ustp ='01' order by bname");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}else {
			try {
				list = this.find(" from Custom where  ibusinessid="
						+ ibusinessid + " and status='01'");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	}

	/**
	 * 
	 * Describe:获取销售凭证
	 * 
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param iemployeeid
	 * @param jsprice
	 * @param esfemployeetab 系统登录用户
	 * @return return:String Date:2012-10-26
	 */
	public String showSalevouchers(Long iscenicid, Long iemployeeid,
			Long ibusinessid, Double jsprice, String usid, String zzlb, Esfemployeetab esfemployeetab) {
		// salesvouchers="iscenicid&iticketwinid&ibusinessid&iemployeeid&iaccountreceivable&iacceptmoney&igivechange&usid&shouQuanId&pzlb&zzlb";
		// 服务商&窗口ID&业务ID&出票员ID&实收&应收&找零&客户名&授权id&凭证类别&结算方式代码

		StringBuffer voucher = new StringBuffer();
		voucher.append(iscenicid);
		
//		String sql = " from Esbticketwintab esb where esb.iscenicid="
//				+ iscenicid + " order by esb.iticketwinid ";
		String sql = " from Esbticketwintab esb where esb.szticketwincode='CZ' order by esb.iticketwinid ";
		List lst=this.find(sql);
		if(lst==null||lst.size()<1){
			sql= " from Esbticketwintab esb order by esb.iticketwinid desc";
		}
		Esbticketwintab ticketwin = (Esbticketwintab) this.find(sql).get(0);
		voucher.append("&" + ticketwin.getIticketwinid());
		voucher.append("&" + ibusinessid + "&" + iemployeeid + "&" + jsprice
				+ "&" + jsprice + "&0");

		voucher.append("&" + usid);

		// 凭证类别 01 售票 02 补录 结算方式代码 00现金 01 网上
		/*Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext
				.getContext().getSession().get("employee");*/
		voucher.append("&" + esfemployeetab.getIemployeeid() + "&01&");
		if (zzlb != null && !zzlb.equals("")) {
			voucher.append(zzlb);
		} else {
			voucher.append("00");
		}

		return voucher.toString();
	}

	/**
	 * * Describe:销售凭证明细显示
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showSalevoucherDetail(java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.String)
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * @author lijingrui Date:2012-10-29
	 */
	public String showSalevoucherDetail(Long itickettypeid,
			Long icrowdkindpriceid, Long numble, String startdate) {
		// salesvoucherdetails="isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iticketnum&dtstartdate&dtenddate";
		// 明细ID&价格ID&产品ID&数量&开始日期&截至日期&出票方式&出票介质&初始票号&漫游卡号&证件号码
		StringBuffer detail = new StringBuffer();
		detail.append("1&" + icrowdkindpriceid + "&" + itickettypeid + "&"
				+ numble + "&" + startdate + "&" + startdate);
		detail.append("&00&00&&&");
		return detail.toString();
	}

	/**
	 * * Describe:子票明细显示
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#showComticketsaleDetail(java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.String)
	 * @param itickettypeid
	 * @param icrowdkindpriceid
	 * @param numble
	 * @param startdate
	 * @return
	 * @author lijingrui Date:2012-10-29
	 */
	public String showComticketsaleDetail(Long itickettypeid,
			Long icrowdkindpriceid, Long numble, String startdate) {
			StringBuffer detail = new StringBuffer();
		Edmtickettypetab et = (Edmtickettypetab) this.get(
				Edmtickettypetab.class, itickettypeid);
		String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid="
				+ icrowdkindpriceid;
		
		List list = this.find(sql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Edmticketcomposepricetab e = (Edmticketcomposepricetab) list
						.get(i);
				detail.append("1&"
						+ icrowdkindpriceid + "&" + itickettypeid + "&"
						+ e.getItickettypeid() + "&" + numble);

				String enddate = Tools.getDate(startdate, et.getValidityday()
						.intValue() - 1);
				detail.append("&0&0&0&" + startdate + "&"
						+ enddate);

				if (i != list.size() - 1) {
					detail.append(":");
				}
			}
		}

		return detail.toString();
	}

	/**
	 * * Describe:冲正凭证保存
	 * 
	 * @see com.ectrip.system.permitenter.dao.idao.IVoucherDAO#saveOrder(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @param startdate
	 * @return
	 * @author lijingrui Date:2012-11-6
	 */
	public ResultBean saveOrder(String salesvouchers,
			String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols, String startdate, Syslog syslog) {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		if (salesvouchers == null || salesvouchers.equals("")
				|| salesvoucherdetails == null
				|| salesvoucherdetails.equals("")
				|| comticketsalesdetails == null
				|| comticketsalesdetails.equals("")) {
			rs.addRow(new String[] { "false", "数据不完整，不能保存" });
			return rs;
		}
		String[] salesvoucher = salesvouchers.split("&");

		Long iticketwinid = new Long(salesvoucher[1]);

		try {
			ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
					.getBean("saleCenterService");

			Long maxid = saleCenterService.updatevouchersequence();
			Esbticketwintab esbticketwintab = saleCenterService
					.getEsbticketwintab(iticketwinid);
			int ticketstation = esbticketwintab.getIticketstationid()
					.intValue();
			String szticketstation = "";
			if (0 < ticketstation && ticketstation < 10) {
				szticketstation = "00" + ticketstation;
			} else if (ticketstation >= 10 && ticketstation < 100) {
				szticketstation = "0" + ticketstation;
			} else {
				szticketstation = "" + ticketstation;
			}
			String szsalesvoucherno = "";
			szsalesvoucherno = saleCenterService.updateMaxNo(szticketstation);
			return this.savebendiorder(salesvouchers, salesvoucherdetails,
					comticketsalesdetails, productcontrols, maxid,
					szsalesvoucherno, startdate, syslog);

		} catch (Exception e) {
			ResultBean rb = new ResultBean();
			rb.setColumnCount(2);
			rb.setColumnNames(new String[] { "returnstats", "message" });
			rb.addRow(new String[] { "false", e.getMessage() });
			return rb;
		}

	}

	/**
	 * 
	 * Describe:保存
	 * 
	 * @auth:lijingrui
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @param maxid
	 * @param szsalesvoucherno
	 * @param startdate
	 * @return
	 * @throws Exception
	 *             return:ResultBean Date:2012-11-6
	 */
	public ResultBean savebendiorder(String salesvouchers,
			String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols, Long maxid, String szsalesvoucherno,
			String startdate, Syslog syslog) throws Exception {

		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		String[] salesvoucher = salesvouchers.split("&");
		Long iscenicid = new Long(salesvoucher[0]);
		Long iticketwinid = new Long(salesvoucher[1]);
		Long ibusinessid = new Long(salesvoucher[2]);
		Long iemployeeid = new Long(salesvoucher[3]);// 出票员
		String usid = salesvoucher[7];
		Double iaccountreceivable = new Double(salesvoucher[5]);
		Double iacceptmoney = new Double(salesvoucher[4]);
		if (iacceptmoney < iaccountreceivable) {
			throw new Exception("实收款应该大于等于应收金额");
		}

		Double igivechange = new Double(salesvoucher[6]);

		Long forceemid = new Long(salesvoucher[8]);
		String pzlb = salesvoucher[9];// 默认 ０１ 销售 ０４ 补入
		String zffs = "00";
		if (salesvoucher.length >= 11) {
			zffs = salesvoucher[10];// 支付方式
		}
		Stssalesvouchertab s = new Stssalesvouchertab();
		Esbscenicareatab scenic = (Esbscenicareatab) this.get(
				Esbscenicareatab.class, iscenicid);
		s.setIscenicid(iscenicid);
		s.setIticketwinid(iticketwinid);
		s.setIbusinessid(ibusinessid);
		s.setIhandler(iemployeeid);
		s.setIpayeer(iemployeeid);
		s.setImaker(forceemid);
		s.setIauditor(forceemid);
		s.setIaccountreceivable(iaccountreceivable);
		s.setIacceptmoney(iacceptmoney);
		s.setIgivechange(igivechange);
		String today = startdate;
		String daytime = startdate + " " + Tools.getNowTime();
		s.setIyear(new Long(today.substring(0, 4)));
		s.setImonth(new Long(today.substring(5, 7)));
		s.setIday(new Long(today.substring(8, 10)));
		s.setDtmakedate(daytime);
		s.setDtauditdate(today);
		s.setUsid(usid);
		s.setDyusid("daoyou");
		s.setBisintegral(new Long(0));
		s.setByprintinvoice(new Long(0));
		s.setBysplitway(new Long(2));
		s.setBisreturn(new Long(1));
		s.setBysalesvouchertype(pzlb);
		s.setBypostrecord(new Long(1));
		s.setBysalesvoucherstate(new Long(1));
		s.setBispay(new Long(0));
		s.setBispayee(new Long(0));
		s.setForcedrefund("");
		Esbticketwintab e = (Esbticketwintab) this.get(Esbticketwintab.class,
				s.getIticketwinid());
		StssalesvouchertabId id = new StssalesvouchertabId();
		id.setIticketstationid(e.getIticketstationid());
		id.setIsalesvoucherid(maxid);
		s.setId(id);
		Esbticketstationtab esbticketstation = (Esbticketstationtab) this.get(
				Esbticketstationtab.class, e.getIticketstationid());

		s.setSzsalesvoucherno(szsalesvoucherno);
		// 目前结算只有一种方式 （现金）直接生成结算数据
		Stssalessettlementtab st = new Stssalessettlementtab();
		StssalessettlementtabId sid = new StssalessettlementtabId();
		sid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
		sid.setIticketstationid(s.getId().getIticketstationid());
		sid.setIsalessettlementid(new Long(1));
		st.setId(sid);
		st.setSettlementdata(today);
		System.out.println("++++++>>>>>>>>>>" + daytime.substring(11));
		st.setSettlementtime(daytime.substring(11));
		st.setIsettlementid(zffs);
		st.setMsettlementmoney(iacceptmoney);
		st.setIversion(new Long(1));
		st.setDtmakedate(daytime);

		List detaillist = new ArrayList();
		List cdetaillist = new ArrayList();
		String[] salesvoucherdetail = salesvoucherdetails.split(":");
		double smont = 0;
		Long szsoldticketid = new Long(1);

		for (int i = 0; i < salesvoucherdetail.length; i++) {
			String isalesvoucherdetail = salesvoucherdetail[i];
			System.out.println("isalesvoucherdetail=" + isalesvoucherdetail);
			String[] detail = isalesvoucherdetail.split("&");
			long isalesvoucherdetailsid = new Long(detail[0]);
			Long icrowdkindpriceid = new Long(detail[1]);
			Long itickettypeid = new Long(detail[2]);
			Long iticketnum = new Long(detail[3]);
			String dtstartdate = detail[4];
			String dtenddate = detail[5];
			String manyouno = "";
			String myzj = "";
			Edmtickettypetab eticket = (Edmtickettypetab) this.get(
					Edmtickettypetab.class, itickettypeid);
			// 添加售出门票表
			if (e.getBywintype().equals("0003")) {
				// 窗口为漫游卡窗口
				System.out.println("判断漫游卡选项1");
				if (eticket.getBycategorytype().equals("0014")) {
					System.out.println("判断漫游卡选项2");
					if (iticketnum > 1) {
						throw new RuntimeException(
								eticket.getSztickettypename() + "只能单销售");
					}
				} else {
					throw new RuntimeException("慢游卡窗口只能办理慢游卡业务");

				}
				System.out.println("判断漫游卡选项3");
				System.out.println("detail.length=" + detail.length);
				if (detail.length < 11) {
					System.out.println("判断漫游卡选项4");
					throw new RuntimeException("漫游卡办理应填写漫游卡号和有效证件");
				} else {
					manyouno = detail[9];
					myzj = detail[10];
					if (manyouno.equals("") || myzj.equals("")) {
						throw new RuntimeException("漫游卡办理应填写漫游卡号和有效证件");
					}
				}

			}

			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) this
					.get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
			Double mactualsaleprice = edmcrowdkindpricetab
					.getMactualsaleprice();
			Double meventmoney = new Double(mactualsaleprice.doubleValue()
					* iticketnum.doubleValue());
			smont = smont + meventmoney;
			Stssalesvoucherdetailstab sd = new Stssalesvoucherdetailstab();
			StssalesvoucherdetailstabId sdid = new StssalesvoucherdetailstabId();
			sdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
			sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
			sdid.setIticketstationid(s.getId().getIticketstationid());
			sd.setId(sdid);
			sd.setIticketwinid(s.getIticketwinid());
			sd.setIcrowdkindpriceid(icrowdkindpriceid);
			sd.setItickettypeid(itickettypeid);
			sd.setIplayerperticket(new Long(1));// 人/张
			sd.setIticketnum(iticketnum);// 张数
			sd.setIticketplayer(iticketnum);// 人次
			sd.setDtstartdate(dtstartdate);
			sd.setDtenddate(dtenddate);
			sd.setIstartid(new Long(0));
			sd.setIendid(new Long(0));
			sd.setSzstartserial("0");
			sd.setSzendserial("0");
			sd.setIoffersschemeid(new Long(0));
			sd.setIamount(iticketnum);
			sd.setIpresentnums(new Long(0));
			sd.setIderatenums(new Long(0));
			sd.setIfactnum(new Long(0));
			sd.setIuseablenessnum(iticketnum);// 使用数量
			sd.setMactualsaleprice(mactualsaleprice);// 实际售价
			sd.setMeventmoney(meventmoney);// 实际发生金额
			sd.setMderatemoney(new Double(0));// 减免金额
			sd.setMpresentmoney(new Double(0));// 赠送金额
			sd.setMnominalfee(new Double(0));// 工本费
			sd.setMdeposit(new Double(0));
			sd.setMhandcharge(new Double(0));
			sd.setByconsumetype("00");
			sd.setIconsumenum(new Double(0));
			sd.setMtotalamount(meventmoney);
			sd.setItotalnumber(iticketnum);
			sd.setItotalminutes(new Long(0));
			sd.setByisout(new Long(1));
			sd.setDtmakedate(daytime);
			sd.setIversion(new Long(0));
			detaillist.add(sd);

			for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
				Stssoldtickettab stsv = new Stssoldtickettab();
				StssoldtickettabId stsvid = new StssoldtickettabId();
				stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
				stsvid.setIticketstationid(sd.getId().getIticketstationid());
				stsvid.setIsalesvoucherdetailsid(sd.getId()
						.getIsalesvoucherdetailsid());
				stsvid.setSzsoldticketid(szsoldticketid);
				szsoldticketid = szsoldticketid + 1;
				stsv.setId(stsvid);
				stsv.setIscenicid(s.getIscenicid());
				stsv.setIcrowdkindid(edmcrowdkindpricetab.getIcrowdkindid());
				stsv.setItickettypeid(sd.getItickettypeid());
				stsv.setUsid(s.getUsid());
				stsv.setIbusinessid(s.getIbusinessid());
				stsv.setDyusid(s.getDyusid());
				stsv.setIplayerperticket(sd.getIplayerperticket());// 人次数
				stsv.setDtstartdate(sd.getDtstartdate());
				stsv.setDtenddate(sd.getDtenddate());
				stsv.setMhandcharge(new Double(0));
				stsv.setByvalidity("00");
				stsv.setDtmakedate(daytime);
				Long iserialnum = this.getSequenceId("ticketid_sequence");
				stsv.setIserialnum(iserialnum);
				String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
				StringBuffer printno = new StringBuffer();
				printno.append(esbticketstation.getSzstationcode());
				printno.append(scenic.getSzsceniccode());
				printno.append(eticket.getSztickettypecode());
				if (newmaxorno.length() < 6) {
					for (int b = 0; b < 6 - newmaxorno.length(); b++) {
						printno.append("0");
					}
				}
				printno.append(newmaxorno);
				String szprintno = Tools.ticketMakeMd5(printno.toString());
				stsv.setSzticketprintno(szprintno);
				stsv.setMremainmoney(new Double(0));
				stsv.setMpresentmoney(new Double(0));
				stsv.setMactualsaleprice(sd.getMactualsaleprice());
				stsv.setIpresentnum(new Long(0));
				stsv.setIremainnum(new Long(1));// 剩余数量
				stsv.setMnominalfee(new Double(0));
				stsv.setMdeposit(new Double(0));
				stsv.setByticketpurpose("00");
				stsv.setBisrefundbalance(new Long(1));
				stsv.setByactivation("02");
				stsv.setIvaliditynum(new Long(0));
				stsv.setByvalidityunits(new Long(0));
				stsv.setByconsumetype("00");
				if (e.getBywintype().equals("0003")) {
					// 窗口为漫游卡窗口
					stsv.setManyouno(manyouno);
					stsv.setMyzj(myzj);
				}
				cdetaillist.add(stsv);
			}
		}

		if (iaccountreceivable != smont) {
			throw new RuntimeException("明细金额之和" + smont + "与总金额"
					+ iaccountreceivable + "不等");
		}

		String[] comticketsalesdetail = comticketsalesdetails.split(":");
		List zdetaillist = new ArrayList();
		List cdzetaillist = new ArrayList();
		long n = 0;
		Long ss = new Long(0);
		Long oisplitamount = new Long(0);
		long yisalsvoucherdetailid = 0;

		for (int i = 0; i < comticketsalesdetail.length; i++) {
			String[] zdetail = comticketsalesdetail[i].split("&");
			Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
			StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
			Long isalesvoucherdetailsid = new Long(zdetail[0]);
			Long icrowdkindpriceid = new Long(zdetail[1]);
			Edmcrowdkindpricetab edprice = (Edmcrowdkindpricetab) this.get(
					Edmcrowdkindpricetab.class, icrowdkindpriceid);
			Long itickettypeid = new Long(zdetail[2]);
			Long iztickettypeid = new Long(zdetail[3]);
			Long isplitamount = new Long(zdetail[4]);
			Long tripid = new Long(zdetail[5]);
			Long ivenueareaid = new Long(zdetail[6]);
			Long ivenueseatsid = new Long(zdetail[7]);
			String dtstartdate = zdetail[8];
			String dtenddate = zdetail[9];
			if (itickettypeid.longValue() == iztickettypeid.longValue()
					&& tripid.longValue() > 0) {
				String hsql = " from Productcontrol where itickettypeid="
						+ itickettypeid + " and tripid=" + tripid
						+ " and stdata='" + dtstartdate
						+ "' and controltype='03'";
				List plist = this.find(hsql);
				Productcontrol p2 = (Productcontrol) plist.get(0);
				if (p2.getIsxianjin() == null || p2.getIsxianjin() == 0) {

					throw new RuntimeException("现金窗口目前不能销售" + dtstartdate + "第"
							+ tripid + "趟的单竹筏票");

				}
			}

			zstdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
			zstdid.setIticketstationid(s.getId().getIticketstationid());
			zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
			zstd.setIcrowdkindpriceid(icrowdkindpriceid);
			zstd.setItickettypeid(itickettypeid);
			zstd.setIztickettypeid(iztickettypeid);
			zstd.setMhandcharge(new Double(0));
			zstd.setDtmakedate(daytime);
			Prdtripvenuemanage p = new Prdtripvenuemanage();
			if (tripid.longValue() > 0) {
				List plist = this.find(" from Prdtripvenuemanage where tripid="
						+ tripid + " and itickettypeid=" + iztickettypeid
						+ " and startdata<='" + dtstartdate
						+ "' and enddata>='" + dtstartdate + "'");

				p = (Prdtripvenuemanage) plist.get(0);
				zstd.setTripid(tripid);
				zstd.setDtstartdate(dtstartdate + " " + p.getStarttime()
						+ ":00");
				zstd.setDtenddate(dtstartdate + " " + p.getEndtime() + ":00");
				// 判断竹筏时间是否过期
				Calendar now = Calendar.getInstance(TimeZone
						.getTimeZone("GMT+08:00"));
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				try {
					Date d2 = df.parse(zstd.getDtenddate());
					Calendar endcalendar = Calendar.getInstance();
					endcalendar.setTime(d2);
					// 补入不用判断竹筏是否过期
					if (pzlb.equals("01")) {
						if (now.after(endcalendar)) {
							// rs.addRow(new String[] { "false", "竹筏趟次过期，不能销售"
							// });
							throw new RuntimeException("竹筏趟次过期，不能销售");
						}
					}
				} catch (ParseException e1) {
					throw new RuntimeException("竹筏趟次过期，不能销售");
				}

				if (ivenueareaid == 0) {
					zstd.setIvenueareaid(p.getIvenueareaid());
					zstd.setIvenueid(p.getIvenueid());
				} else {
					zstd.setIvenueareaid(ivenueareaid);
					zstd.setIvenueid(p.getIvenueid());
				}
				zstd.setIvenueseatsid(ivenueseatsid);
			} else {
				zstd.setTripid(tripid);
				zstd.setIvenueareaid(new Long(0));
				zstd.setIvenueid(new Long(0));
				zstd.setIvenueseatsid(new Long(0));
				zstd.setDtstartdate(dtstartdate + " 00:00:00");
				zstd.setDtenddate(dtenddate + " 23:59:59");
			}

			zstd.setIversion(new Long(0));
			List list = this
					.find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
							+ icrowdkindpriceid
							+ " and itickettypeid='"
							+ iztickettypeid + "'");
			if (list == null || list.size() == 0) {
				throw new RuntimeException("票价编号为" + icrowdkindpriceid
						+ "无子票价格");
			} else {
				Edmticketcomposepricetab edt = (Edmticketcomposepricetab) list
						.get(0);
				zstdid.setIcomticketsalesdetailsid(edt.getId()
						.getIticketcomposepriceid());
				zstd.setId(zstdid);
				zstd.setIsplitamount(isplitamount * edt.getNumb());
				zstd.setMsplitprice(edt.getMactualsaleprice());
				zstd.setMsplitmoney(edt.getMactualsaleprice() * edt.getNumb()
						* isplitamount);
				zdetaillist.add(zstd);
				List opwwlist = this
						.find(" from Opwwicketsettab where itickettypeid="
								+ itickettypeid + " and izticktypeid="
								+ iztickettypeid);
				if (yisalsvoucherdetailid == 0) {
					yisalsvoucherdetailid = isalesvoucherdetailsid.longValue();
					oisplitamount = isplitamount;
				} else {
					if (yisalsvoucherdetailid != isalesvoucherdetailsid
							.longValue()) {
						ss = ss + oisplitamount;
						oisplitamount = isplitamount;
						yisalsvoucherdetailid = isalesvoucherdetailsid
								.longValue();
					}
				}
				if (opwwlist.size() == 0) {
					Edmtickettypetab edticket = (Edmtickettypetab) this.get(
							Edmtickettypetab.class, itickettypeid);
					throw new RuntimeException(edticket.getSztickettypename()
							+ "的检票园门数据不全");

				}

				for (int k = 0; k < opwwlist.size(); k++) {
					Opwwicketsettab opww = (Opwwicketsettab) opwwlist.get(k);
					for (int m = 1; m <= isplitamount; m++) {
						Stssoldticketsubtab stss = new Stssoldticketsubtab();
						StssoldticketsubtabId stssid = new StssoldticketsubtabId();
						stssid.setIticketstationid(s.getId()
								.getIticketstationid());
						stssid.setSzsoldticketid(new Long(ss + m));
						stssid.setSzsoldticketsubid(new Long(n + 1));
						n = n + 1;
						stssid.setIsalesvoucherid(s.getId()
								.getIsalesvoucherid());
						stssid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
						stss.setId(stssid);
						stss.setIgardengateid(opww.getIgardengateid());
						stss.setIscenicid(s.getIscenicid());
						stss.setItickettypeid(itickettypeid);
						stss.setIztickettypeid(iztickettypeid);
						stss.setBychecktype(new Long(0));
						stss.setByconsumemode(opww.getByconsumemode());
						stss.setIpasstimes(opww.getIlimittotaltimes());
						stss.setIpassedtimes(new Long(0));
						stss.setMsingletimes(opww.getMsingletimes());
						stss.setMlimitconsume(new Double(0));
						stss.setMsingleconsume(new Double(0));
						stss.setMconsumed(new Double(0));
						stss.setByisout(new Long(1));
						stss.setIsvalid(new Long(1));
						stss.setDtmakedate(daytime);
						stss.setBylastcheckdir(new Long(0));
						stss.setIcrowdkindid(edprice.getIcrowdkindid());
						if (tripid.longValue() > 0
								&& opww.getIlimittotaltimes() == 1) {
							stss.setTripid(tripid);
							stss.setDtbegindate(dtstartdate + " "
									+ p.getStarttime() + ":00");
							stss.setDtenddate(dtenddate + " " + p.getEndtime()
									+ ":00");
						} else {
							stss.setTripid(new Long(0));
							stss.setDtbegindate(dtstartdate + " " + "00:00:00");
							stss.setDtenddate(dtenddate + " " + "23:59:59");
						}
						cdzetaillist.add(stss);
					}
				}

			}
		}

		this.save(s);
		this.save(st);
		for (int i = 0; i < detaillist.size(); i++) {
			Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
					.get(i);
			this.save(sd);
		}
		Sysparv5 sv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id(
				"PRCS", "01"));
		String printcs = "0";
		if (sv5 != null) {
			printcs = sv5.getPmva();
		}

		for (int i = 0; i < cdetaillist.size(); i++) {
			Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist.get(i);
			this.save(stsv);

			if (pzlb.equals("01")) {

				if (printcs.equals("1")) {
					Ticketprintlist t = new Ticketprintlist();
					t.setSzsalesvoucherno(s.getSzsalesvoucherno());
					t.setIemployeeid(iemployeeid);
					t.setPrinttype("01");
					t.setSzticketprintno(stsv.getSzticketprintno());
					t.setPrinttime(daytime);
					t.setIsok(new Long(0));
					try {

						Long printid = this.getSequenceId("PRINT_ID");
						t.setPrintid(printid);
						this.save(t);
					} catch (Exception e1) {
						e1.printStackTrace();
						System.out.println(e1.getMessage());
					}
				}
			}

		}

		for (int i = 0; i < zdetaillist.size(); i++) {
			Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
					.get(i);
			this.save(zstd);
		}

		for (int i = 0; i < cdzetaillist.size(); i++) {
			Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
					.get(i);
			this.save(stss);
		}

		syslog.setBrief(syslog.getBrief() + "  订单号：" + szsalesvoucherno);
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		rs.addRow(new String[] { "true",
				s.getId().getIsalesvoucherid().toString() });
		return rs;

	}

	/**
	 * 销售凭证:salesvouchers=
	 * "iscenicid&iticketwinid&ibusinessid&iemployeeid&iaccountreceivable&iacceptmoney&igivechange&usid&forceemid&pzlb"
	 * ;服务商&窗口ID&业务ID&出票员ID&实收&应收&找零&用户名 当业务ID=1 散客业务 usid='guest',其他业务时
	 * usid是选择的用户usid 销售凭证明细:salesvoucherdetails=
	 * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iticketnum&dtstartdate&dtenddate&bymaketicketway&bymediatype&szticketprintno"
	 * ;明细ID&价格ID&产品ID&数量&开始日期&截至日期&出票方式&出票介质&初始票号 子票明细: comticketsalesdetails=
	 * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iztickettypeid&isplitamount&tripid&ivenueareaid&ivenueseatsid&dtstartdate&dtenddate"
	 * 明细ID&价格ID&产品ID&子产品ID&数量&竹筏趟次ID&码头ID&座位ID&开始日期&截至日期
	 * 产品控制数量:productcontrols=
	 * "itickettypeid&controltype&tripid&stdata&soldnumber"
	 * 产品编号&控制方式&趟次&日期&销售数量:产品编号&控制方式&趟次&日期&销售数量 Describe:
	 * 
	 * @auth:yuanchengjun
	 * @param salesvouchers
	 * @param salesvoucherdetails
	 * @param comticketsalesdetails
	 * @param productcontrols
	 * @return return:ResultBean Date:2011-10-29
	 */
	public ResultBean saveorder41(String salesvouchers,
			String salesvoucherdetails, String comticketsalesdetails,
			String productcontrols, String startdate, Syslog syslog,String url) {
		if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		Debug.print("My is saveorder41");
		rs.setColumnNames(new String[] { "returnstats", "message" });
		// System.out.println("saveorder41  Hi is ok ");
		String returnstats = "true";
		String message = "";
		int haveseat = 0;
		try {
			ResultBean cano = null;

			if (salesvouchers == null || salesvouchers.equals("")
					|| salesvoucherdetails == null
					|| salesvoucherdetails.equals("")
					|| comticketsalesdetails == null
					|| comticketsalesdetails.equals("")) {
				rs.addRow(new String[] { "false", "数据不完整，不能保存" });
				return rs;
			}
			String[] salesvoucher = salesvouchers.split("&");

			Long iticketwinid = new Long(salesvoucher[1]);
			Long empid = new Long(salesvoucher[3]); // 出票员ID
			Long ibusinessid = new Long(salesvoucher[2]);
			String usid = salesvoucher[7];

			ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
					.getBean("saleCenterService");
			ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
					.getBean("saveVenueService");
			// 判断售出的预制票不能重复 2013-04-15 lijingrui
			String[] salesvoucherdetail = salesvoucherdetails.split(":");
			Edmtickettypetab edmtick = null;
			List<Object[]> jectlist = new ArrayList<Object[]>();
			StringBuffer codelist = new StringBuffer();
			if (salesvoucherdetail != null && salesvoucherdetail.length > 0) {
				for (int x = 0; x < salesvoucherdetail.length; x++) {
					String isalesvoucherdetail = salesvoucherdetail[x];
					String[] voudeta = isalesvoucherdetail.split("&");
					Long itickettypeid = new Long(voudeta[2]);
					Long amount = new Long(voudeta[3]);
					String bymaketicketway = "00";
					String szticketprintno = "";
					edmtick = (Edmtickettypetab) saleCenterService
							.getEdmtickettypetab(itickettypeid);
					if (voudeta.length > 6) {
						bymaketicketway = voudeta[6];
						if (bymaketicketway.equals("01")) {

							// 介质类型为 一维条码 二维条码的 是预制票
							if (edmtick.getBymediatype().equals("00")
									|| edmtick.getBymediatype().equals("01")) {

								szticketprintno = voudeta[8];
								String[] sperialnum = szticketprintno
										.split("[|]");
								if (sperialnum != null && sperialnum.length > 0) {
									for (int i = 0; i < sperialnum.length; i++) {
										boolean b = saleCenterService
												.isExistcode(empid,
														itickettypeid,
														sperialnum[i]
																.toString());
										if (!b) {
											rs.addRow(new String[] { "false",
													"手中无此票号，已出售或者未领用!!!" });
											return rs;
										}

										codelist.append(sperialnum[i] + ",");
									}
								}

							}
						}
					}

					Object[] o = new Object[] { edmtick.getIscenicid(),
							itickettypeid, amount, voudeta[4].toString(),
							voudeta[5].toString() };
					jectlist.add(o);

				}

				IStocksWareService stockswareService = (IStocksWareService) SpringUtil
						.getBean("stockswareService");
				if (ibusinessid == 1) {
					// 判断库存是否超量
					int kc = stockswareService.checkTicketware(jectlist);
					if (kc == 0) {
						rs.addRow(new String[] { "false", "您购买的票务数量超过库存,请重新选择!" });
						return rs;
					}
				} else {
					Custom stom = saleCenterService.queryCustom(usid);

					int yhkc = stockswareService.checkTicketwareCustom(
							jectlist, stom.getUsid());
					if (yhkc == 0) {
						rs.addRow(new String[] { "false", "您购买的票务数量超过库存,请重新选择!" });
						return rs;
					} else if (yhkc == 2) {
						rs.addRow(new String[] { "false", "接待单位需配置库存量才允许销售!" });
						return rs;
					} else {
						if (stom.getIbusinessid() != 3) {
							int kcpd = stockswareService
									.checkTicketware(jectlist);
							if (kcpd == 0) {
								rs.addRow(new String[] { "false",
										"您购买的票务数量超过库存,请重新选择!" });
								return rs;
							}
						}
					}

				}

				String[] code = codelist.toString().split(",");
				for (int i = 0; i < code.length; i++) {
					String printcode = (String) code[i];
					for (int j = i + 1; j < code.length; j++) {
						String endcode = (String) code[j];
						if (printcode.equals(endcode)) {
							rs.addRow(new String[] {
									"false",
									"同一凭证下" + edmtick.getSztickettypename()
											+ "售出的预制票号不能重复!" });
							return rs;
						}
					}
				}

			}

			String orderseat = "";
			Debug.print("验证票数与座位数");
			long tiknumb = 0;
			String[] comticketsalesdetail = comticketsalesdetails.split(":");
			for (int i = 0; i < comticketsalesdetail.length; i++) {
				String[] zdetail = comticketsalesdetail[i].split("&");
				System.out.println("zdetail.length=" + zdetail.length);
				Long isplitamount = new Long(zdetail[4]);

				Long iprogramid = 0L;
				if (zdetail.length >= 11) {
					iprogramid = new Long(zdetail[10]);
				}
				if (zdetail.length >= 12) {
					if (!zdetail[10].equals("null")
							&& !zdetail[10].equals("NULL")) {

						Venueprogram v = saveVenueService
								.getprogram(iprogramid);
						if (v.getBycashsaletype() == 0) {
							rs.addRow(new String[] { "false", "该节目不能现金售票" });
							return rs;
						}

						String seats = zdetail[11];
						if (iprogramid > 0) {
							// 存在剧院票
							tiknumb = tiknumb + isplitamount.longValue();
							if (seats != null) {
								String[] seat = seats.split(">");
								haveseat = haveseat + seat.length;
								if (seat.length != isplitamount.longValue()) {
									rs.addRow(new String[] { "false",
											"预定数量与座位数不等" });
									return rs;
								}
							}
						}

					}
				}
			}
			
			if (returnstats.equals("false")) {
				rs.addRow(new String[] { "false", cano.getResult(0, 1) });
				return rs;
			} else {/*
				try {
					
					Long maxid = saleCenterService.updatevouchersequence();
					Esbticketwintab esbticketwintab = saleCenterService
							.getEsbticketwintab(iticketwinid);
					int ticketstation = esbticketwintab.getIticketstationid()
							.intValue();
					String szticketstation = "";
					if (0 < ticketstation && ticketstation < 10) {
						szticketstation = "00" + ticketstation;
					} else if (ticketstation >= 10 && ticketstation < 100) {
						szticketstation = "0" + ticketstation;
					} else {
						szticketstation = "" + ticketstation;
					}
					String szsalesvoucherno = "";
					szsalesvoucherno = saleCenterService
							.updateMaxNo(szticketstation);
					rs = saveSaleorder41(salesvouchers, salesvoucherdetails,
							comticketsalesdetails, maxid, szsalesvoucherno,
							startdate, syslog,url);

					returnstats = rs.getResult(0, 0);
					// /System.out.println("returnstats= "+returnstats);
					// System.out.println("正常返回"+returnstats);
					if (returnstats.equals("true")) {
						return rs;
					} else {
						try {
							javax.xml.rpc.Service service = null;
							java.net.URL endpointURL = new java.net.URL(
									"http://"
											+ url
											+ "/services/centersaleService?wsdl");

							CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
									endpointURL, service);
							ssl.setMaintainSession(true);
							if (ibusinessid == 3) {
								cano = ssl.updatecancelProductreserve(
										productcontrols, usid);
							} else {
								cano = ssl
										.updatecancelProductcontrol(productcontrols);
							}
						} catch (Exception e1) {
							message = "保存失败";
						}
						rs.addRow(new String[] { returnstats, message });
						return rs;
					}

				} catch (Exception e) {
					message = "数据库提交出错，有可能该票已经卖过，退过的不能再卖。";
					// System.out.println("Exception= "+e);
					// System.out.println("正常返回Exception"+returnstats);
					returnstats = rs.getResult(0, 0);
					if (returnstats.equals("true")) {
						rs.addRow(new String[] { "false", e.toString() });
						return rs;
					}
					try {
						javax.xml.rpc.Service service = null;
						java.net.URL endpointURL = new java.net.URL("http://"
								+ url
								+ "/services/centersaleService?wsdl");

						CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
								endpointURL, service);
						ssl.setMaintainSession(true);
						if (ibusinessid == 3) {
							cano = ssl.updatecancelProductreserve(
									productcontrols, usid);
						} else {
							cano = ssl
									.updatecancelProductcontrol(productcontrols);
						}
					} catch (Exception e1) {
						message = "保存失败" + ",可售量已扣除";
					}
					rs.addRow(new String[] { returnstats, message });
					return rs;
				}
			*/
				return rs;
				}
		} catch (Exception e) {
			System.out.println("returnstats= " + returnstats);
			rs.addRow(new String[] { "fale", "保存失败11" });
			return rs;
		}

	}

	/**
	 * 最新的认单保存系统
	 */
	public ResultBean saveSaleorder41(String salesvouchers,
			String salesvoucherdetails, String comticketsalesdetails,
			Long maxid, String szsalesvoucherno, String startdate, Syslog syslog,String url, String... param)
			throws SQLException {
		if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
		// 开始保存订单

		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		Long seatlockid = 0L;
		String seatids = "";
		boolean haveseat1=false;
		try {
			String[] salesvoucher = salesvouchers.split("&");
			Long iscenicid = new Long(salesvoucher[0]);

			Esbscenicareatab scenic = (Esbscenicareatab) this.get(
					Esbscenicareatab.class, iscenicid);

			Long iticketwinid = new Long(salesvoucher[1]);
			Esbticketwintab e = (Esbticketwintab) this.get(
					Esbticketwintab.class, iticketwinid);

			Esbticketstationtab esbticketstation = (Esbticketstationtab) this
					.get(Esbticketstationtab.class, e.getIticketstationid());
			Double iaccountreceivable = new Double(salesvoucher[5]);

			Double iacceptmoney = new Double(salesvoucher[4]);

			if (iacceptmoney < iaccountreceivable) {
				System.out.println("实收款应该大于等于应收金额 ");
				rs.addRow(new String[] { "false", "实收款应该大于等于应收金额" });
				return rs;
			}
			// 将订单信息参数转换成map对像
			System.out.println("最新的订单保存saveorder421 ");
			Stssalesvouchertab s = new Stssalesvouchertab();

			if (param != null && param.length > 0) {
				System.out.println(param[0]);
				if (param[0] != null && !param[0].equals("")) {
					String[] ordercs = param[0].split("&");
					Map returnMap = new HashMap();
					for (int i = 0; i < ordercs.length; i++) {
						if (!ordercs.equals("")) {
							String[] ss = ordercs[i].split(":");
							System.out.println(ss.length);
							if (ss.length == 2) {
								returnMap.put(ss[0].toLowerCase(), ss[1]);
							}
						}
					}
					s = (Stssalesvouchertab) saleCenterService.convertMap(
							Stssalesvouchertab.class, returnMap);
				}
				if (param != null && param.length > 1) {
					System.out.println("param[1]" + param[1]);
					seatlockid = new Long(param[1]);
				}
			}

			// System.out.println("1111111111111111111111");
			// 生成凭证
			int sjcpnumb = 0;

			System.out.println("最新的订单保存saveorder422 ");
			s = saveStssalesvouchertab(salesvouchers, maxid,
					szsalesvoucherno, s);
			Custom cum = null;
			if (s.getIbusinessid() != 1) {
				cum = (Custom) this.get(Custom.class, s.getUsid());
			}
			String[] comticketsalesdetail = comticketsalesdetails.split(":");
			for (int i = 0; i < comticketsalesdetail.length; i++) {
				String[] zdetail = comticketsalesdetail[i].split("&");
				if (zdetail.length >= 12) {
					if (zdetail[10] != null && !zdetail[10].equalsIgnoreCase("null")
							&& !zdetail[10].equals("")) {
						haveseat1=true;
					}

				}
				Long isalesvoucherdetailsid = new Long(zdetail[0]);
				Long icrowdkindpriceid = new Long(zdetail[1]);
				Long itickettypeid = new Long(zdetail[2]);
				Long iztickettypeid = new Long(zdetail[3]);
				Long isplitamount = new Long(zdetail[4]);
				// Long tripid = new Long(zdetail[5]);
				String dtstartdate = zdetail[8];
				String dtenddate = zdetail[9];
				Prdtripvenuemanage p = null;

				if (s.getIbusinessid() != 1) {
					Edmcrowdkindpricetab emt = (Edmcrowdkindpricetab) this
							.get(Edmcrowdkindpricetab.class, icrowdkindpriceid);

					Edmtickettypetab edticket = (Edmtickettypetab) this
							.get(Edmtickettypetab.class, itickettypeid);
					// 获取价格分组
					String groupno = this.searchJgfz(s.getUsid(),
							edticket.getIscenicid());

					if (!emt.getNote1().equals(groupno)) {
						rs.addRow(new String[] {
								"false",
								"编号" + emt.getIcrowdkindpricecode()
								+ "产品有不属于对应用户价格组中的票务" });
						return rs;
					}
				}
				List list = this
						.find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
								+ icrowdkindpriceid
								+ " and itickettypeid='"
								+ iztickettypeid + "'");
				if (list == null || list.size() == 0) {
					rs.addRow(new String[] { "false",
							"票价编号为" + icrowdkindpriceid + "无子票价格" });
					return rs;
				} else {
					List opwwlist = this
							.find(" from Opwwicketsettab where itickettypeid="
									+ itickettypeid + " and izticktypeid="
									+ iztickettypeid + " and byisuse=1");

					if (opwwlist.size() == 0) {
						Edmtickettypetab edticket = (Edmtickettypetab) this
								.get(Edmtickettypetab.class, itickettypeid);
						rs.addRow(new String[] { "false",
								edticket.getSztickettypecode() + "的检票园门数据不全" });
						return rs;
					}
				}
			}
			String zffs = "00";
			if (salesvoucher.length >= 11) {
				zffs = salesvoucher[10];// 支付方式
			}

			// 目前结算只有一种方式，结算凭证
			Stssalessettlementtab st = saleCenterService
					.saveStssalessettlementtab(s, zffs);
			System.out.println("最新的订单保存saveorder423 ");
			List detaillist = new ArrayList();
			List cdetaillist = new ArrayList();
			List zdetaillist = new ArrayList();
			List cdzetaillist = new ArrayList();
			List cdchecklist = new ArrayList();
			List cseatlist = new ArrayList();
			String[] salesvoucherdetail = salesvoucherdetails.split(":");
			double smont = 0;
			List zjlist = new ArrayList();
			long maxnumb = 2;// 同一种票同一游览日期最多预定人数
			Sysparv5 pv5 = (Sysparv5) this.get(Sysparv5.class,
					new Sysparv5Id("SMSL", "01"));
			if (pv5 != null) {
				maxnumb = Long.parseLong(pv5.getPmva());
			}
			System.out.println("最新的订单保存saveorder424 ");
			List<Object[]> jectlist = new ArrayList<Object[]>();

			for (int i = 0; i < salesvoucherdetail.length; i++) {
				System.out.println("最新的订单保存saveorder4241 ");
				String isalesvoucherdetail = salesvoucherdetail[i];
				String[] detail = isalesvoucherdetail.split("&");
				System.out.println("最新的订单保存saveorder4242 ");
				Long itickettypeid = new Long(detail[2]);
				Long icrowdkindpriceid = new Long(detail[1]);
				Edmtickettypetab eticket = (Edmtickettypetab) this.get(
						Edmtickettypetab.class, itickettypeid);
				Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) this
						.get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
				// 验证该价格是否能销售
				System.out.println("最新的订单保存saveorder4243 ");
				List winlimit = this
						.find(" from Ospticketwinlimitstab s where s.iticketwinid="
								+ s.getIticketwinid()
								+ " and s.icrowdkindpriceid="
								+ icrowdkindpriceid);
				if (winlimit == null || winlimit.size() == 0) {

					rs.addRow(new String[] {
							"false",
							eticket.getSztickettypename()
							+ "的价格为"
							+ edmcrowdkindpricetab
							.getIcrowdkindpricecode()
							+ "在本窗口不能销售" });
					return rs;
				}
				System.out.println("最新的订单保存saveorder4245 ");
				List slimitst = this
						.find(" from Ospsaleslimitstab s where s.iemployeeid="
								+ s.getIhandler() + " and s.icrowdkindpriceid="
								+ icrowdkindpriceid);
				if (slimitst == null || slimitst.size() == 0) {
					
					rs.addRow(new String[] {
							"false",
							eticket.getSztickettypename()
							+ "的价格为"
							+ edmcrowdkindpricetab
							.getIcrowdkindpricecode()
							+ "在您不能销售" });
					return rs;
				}
				System.out.println("最新的订单保存saveorder4246 ");
				String bymaketicketway = "00";
				String bymediatype = "00";
				String szticketprintno = "";
				Long piserialnum = new Long(0);
				Long iticketnum = new Long(detail[3]);
				sjcpnumb = sjcpnumb + iticketnum.intValue();
				if (detail.length > 6) {
					bymaketicketway = detail[6];// 判断现场激活还是现场打印
					bymediatype = detail[7];// 判断介质
				}
				System.out.println("最新的订单保存saveorder4247 ");
				if (bymaketicketway.equals("01")) {
					// 李经锐修改 2012-09-05 增加判断 预制票 现场激活 还要判断介质类型
					if (eticket.getBymediatype().equals("00")
							|| eticket.getBymediatype().equals("01")) {
						szticketprintno = detail[8];
					}

					// piserialnum = new Long(szticketprintno.substring(6, 12));
				}
				System.out.println("最新的订单保存saveorder4248 ");
				String manyouno = "";
				String myzj = "";
				String myname = "";
				String mytelno = "";
				if (e.getBywintype().equals("0003")) {
					// 窗口为年卡窗口
					if (eticket.getBycategorytype().equals("0014")) {
						if (iticketnum > 1) {

							rs.addRow(new String[] { "false",
									eticket.getSztickettypename() + "只能单票销售" });
							return rs;
						}
					} else {

						rs.addRow(new String[] { "false", "年卡窗口只能办理年卡业务" });
						return rs;

					}
					if (detail.length < 13) {

						manyouno = detail[9];
						myzj = detail[10];

						if (myzj.equals("")) {

							rs.addRow(new String[] { "false", "年卡办理应填写有效证件" });
							return rs;
						}
					} else {
						manyouno = detail[9];
						myzj = detail[10];
						myname = detail[11];
						mytelno = detail[12];
						if (myzj.equals("")) {

							rs.addRow(new String[] { "false", "年卡办理应填写有效证件" });
							return rs;
						}
						if (mytelno.equals("")) {

							rs.addRow(new String[] { "false", "年卡办理应填写电话号码" });
							return rs;
						}
						if (myname.equals("")) {

							rs.addRow(new String[] { "false", "年卡办理应填写持卡人姓名" });
							return rs;
						}
					}

				} else {
					if (edmcrowdkindpricetab.getIpeoplenumrange().longValue() == 1) {
						if (detail.length >= 12) {
							myzj = detail[10];
							myname = detail[11];
							if (myzj.equals("")) {

								rs.addRow(new String[] { "false", "实名制票务需输入证件号" });
								return rs;
							}
							if (myname.equals("")) {

								rs.addRow(new String[] { "false", "实名制票务填写姓名" });
								return rs;
							}
						}
					}
				}
				System.out.println("最新的订单保存saveorder4249 ");
				Stssalesvoucherdetailstab sd = saleCenterService
						.saveStssalesvoucherdetailstab(s, isalesvoucherdetail,
								szticketprintno);
				smont = smont + sd.getMeventmoney() - sd.getMderatemoney();

				sd.setManyouno(manyouno);
				sd.setMyzj(myzj);
				sd.setName1(myname);
				sd.setZjno1(mytelno);
				sd.setStritickettypeid(eticket.getSztickettypename());

				if (edmcrowdkindpricetab.getIpeoplenumrange().longValue() == 1) {
					if (!sd.getMyzj().equals("")) {
						boolean b = false;
						for (int a = 0; a < zjlist.size(); a++) {
							Stssoldtickettab zjstsv = (Stssoldtickettab) zjlist
									.get(a);
							if (zjstsv.getMyzj().equals(sd.getMyzj())) {
								if (zjstsv.getItickettypeid().longValue() == sd
										.getItickettypeid()) {
									if (zjstsv.getDtstartdate().equals(
											sd.getDtstartdate())) {
										b = true;
										zjstsv.setIplayerperticket(zjstsv
												.getIplayerperticket()
												+ sd.getIticketnum());
										if (zjstsv.getIplayerperticket()
												.longValue() > maxnumb) {
											rs.addRow(new String[] {
													"false",
													"一张身份证只能购买同一天的"
															+ maxnumb
															+ "张"
															+ sd.getStritickettypeid() });
											return rs;
										}
										if (zjstsv.getIplayerperticket()
												.longValue() <= maxnumb) {
											String hsql = "select distinct new map(e.szticketprintno) from Stssoldtickettab e,Stssoldticketsubtab st where e.itickettypeid="
													+ sd.getItickettypeid()
													+ " and e.dtstartdate='"
													+ sd.getDtstartdate()
													+ "' and e.myzj='"
													+ sd.getMyzj()
													+ "' and st.id.isalesvoucherid=e.id.isalesvoucherid and st.id.iticketstationid=e.id.iticketstationid and st.id.szsoldticketid=e.id.szsoldticketid and e.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and st.isvalid=1 ";
											List plist = this.find(hsql);
											if (plist != null
													&& plist.size() > maxnumb
													- sd.getIplayerperticket()
													.longValue()) {

												rs.addRow(new String[] {
														"false",
														"一张身份证只能购买同一天的"
																+ maxnumb
																+ "张"
																+ sd.getStritickettypeid() });
												return rs;
											}
										}
									}
								}
							}
						}
						if (b == false) {
							// 没有匹配的数据
							Stssoldtickettab zjstsv = new Stssoldtickettab();
							zjstsv.setItickettypeid(sd.getItickettypeid());
							zjstsv.setDtstartdate(sd.getDtstartdate());
							zjstsv.setMyzj(sd.getMyzj());
							zjstsv.setIplayerperticket(new Long(1));
							zjlist.add(zjstsv);
							String hsql = "select distinct new map(e.szticketprintno) from Stssoldtickettab e,Stssoldticketsubtab st where e.itickettypeid="
									+ sd.getItickettypeid()
									+ " and e.dtstartdate='"
									+ sd.getDtstartdate()
									+ "' and e.myzj='"
									+ sd.getMyzj()
									+ "' and st.id.isalesvoucherid=e.id.isalesvoucherid and st.id.iticketstationid=e.id.iticketstationid and st.id.szsoldticketid=e.id.szsoldticketid and e.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and st.isvalid=1 ";

							List plist = this.find(hsql);
							if (plist != null && plist.size() > maxnumb - 1) {

								rs.addRow(new String[] {
										"false",
										"一张身份证只能购买同一天的" + maxnumb + "张"
												+ sd.getStritickettypeid() });
								return rs;
							}
						}
					}
				}
				System.out.println("最新的订单保存saveorder42410 ");
				if (eticket.getByusage() == 0) {

					sd.setIticketnum(iticketnum);
					sd.setIticketplayer(iticketnum);
				} else {
					sd.setIticketplayer(iticketnum);
					sd.setIplayerperticket(iticketnum);// 人/张

				}
				detaillist.add(sd);
				// 凭证明细子表
				System.out.println("子票数据");
				zdetaillist = SaveStscomticketsalesdetailstab(zdetaillist, s,
						sd, comticketsalesdetails);
				// 座位数据保存列表
				System.out.println("子票座位数据");
				cseatlist = this.SaveSeatsaletab(cseatlist, sd, zdetaillist,
						eticket.getByusage());
				// 添加售出门票表
				if (eticket.getByusage() == 0) {

					// 一票一人
					if (bymaketicketway.equals("00")) {
						// 现场打 印
						cdetaillist = saleCenterService
								.saveStssoldtickettabug0make00(cdetaillist, sd,
										s, esbticketstation.getSzstationcode(),
										scenic.getSzsceniccode(),
										eticket.getSztickettypecode(),
										edmcrowdkindpricetab.getIcrowdkindid());

					} else if (bymaketicketway.equals("01")) {
						// 现场激活
						cdetaillist = saleCenterService
								.saveStssoldtickettabug0make01(cdetaillist, sd,
										s, szticketprintno,
										edmcrowdkindpricetab.getIcrowdkindid());
					} else if (bymaketicketway.equals("02")) {
						// 身份证出票

						cdetaillist = saleCenterService
								.saveStssoldtickettabug0make00(cdetaillist, sd,
										s, esbticketstation.getSzstationcode(),
										scenic.getSzsceniccode(),
										eticket.getSztickettypecode(),
										edmcrowdkindpricetab.getIcrowdkindid());
					}
				} else if (eticket.getByusage() == 1) {
					// 一票多人
					if (bymaketicketway.equals("00")
							|| bymaketicketway.equals("02")||bymaketicketway.equals("01")) {
						// 现场打印

						cdetaillist = saleCenterService
								.saveStssoldtickettabug1make00(cdetaillist, sd,
										s, esbticketstation.getSzstationcode(),
										scenic.getSzsceniccode(),
										eticket.getSztickettypecode(),
										edmcrowdkindpricetab.getIcrowdkindid());
					} else  {
						
						// 现场激活
						rs.addRow(new String[] {
								"false",
								"产品" + eticket.getSztickettypename()
								+ "票，不支持一票多人" });
						return rs;
					}
				}
				System.out.println("门票子表添加数据");
				// 添加售出门票子表
				if (eticket.getByusage() == 0) {
					// 一票一人
					cdzetaillist = saleCenterService
							.SaveStssoldticketsubtabug0(cdzetaillist,
									zdetaillist, s, sd,
									edmcrowdkindpricetab.getIcrowdkindid());
					cdchecklist = saleCenterService.SaveStsschecktabug0(
							cdchecklist, zdetaillist, cdetaillist, s, sd,
							edmcrowdkindpricetab.getIcrowdkindid());
				} else if (eticket.getByusage() == 1) {
					// 一票多人
					Debug.print("添加售出门票子表");
					cdzetaillist = saleCenterService
							.SaveStssoldticketsubtabug1(cdzetaillist,
									zdetaillist, s, sd,
									edmcrowdkindpricetab.getIcrowdkindid());
					cdchecklist = saleCenterService.SaveStsschecktabug1(
							cdchecklist, zdetaillist, cdetaillist, s, sd,
							edmcrowdkindpricetab.getIcrowdkindid());
				}

				// 库存数据
				Object[] o = new Object[] { eticket.getIscenicid(),
						itickettypeid, iticketnum, detail[4].toString(),
						detail[5].toString() };
				jectlist.add(o);

			}
			System.out.println("最新的订单保存saveorder425 ");
			if (smont != iaccountreceivable) {
				rs.addRow(new String[] { "false", "实收款金额不对" });
				return rs;
			}

			// 预制票 售票时 操作个人结存明细信息
			boolean des = saleCenterService.addPersonaldetails(s.getIhandler(),
					salesvoucherdetail);
			if (des) {
				rs.addRow(new String[] { "false", "售票员手中没售出票号或者售出票号与售票员手中票号冲突" });
				return rs;
			}
			Seatlocktab seatlock = null;
			System.out.println("最新的订单保存saveorder426 ");
			if (seatlockid > 0) {
				
				seatlock = (Seatlocktab) this.get(Seatlocktab.class,
						seatlockid);
				if (!seatlock.getStatus().equals("01")) {
					rs.addRow(new String[] { "false",
							"编号" + seatlockid + "的锁定单不是锁定状态,不能售票" });
				}

				if (sjcpnumb > seatlock.getSeatcounts()) {
					rs.addRow(new String[] { "false", "销售数量不能大于锁定座位数量" });
					return rs;
				}
				
			} else {
				if(haveseat1){
					System.out.println("最新的订单保存saveorder427 ");
				}
			}
			System.out.println("开始保存数据1");

			this.saveorder(s, st, detaillist, cdetaillist, cseatlist,
					zdetaillist, cdzetaillist, cdchecklist, seatlockid, seatids);

			IStocksWareDAO stockswareDao=(IStocksWareDAO) SpringUtil
					.getBean("stockswareDao");;
			
			// 添加库存销量信息 lijingrui
			if(s.getIbusinessid()!=1){
				//判断用户库存
				Custom cstm=(Custom)this.get(Custom.class, s.getUsid());
				//下订单用户是操作员,需要获取分社信息
				String stockusid=cstm.getUsid();
				if(cstm.getLgtp().equals("02")&&cstm.getTtlb().equals("01")&&cstm.getUstp().equals("02")&&cstm.getUsqx().equals("01110000000000000000")){
					if(s.getIbusinessid()==3){
						Custom tom=(Custom)this.get(Custom.class, cstm.getSusid());
						stockusid=tom.getSusid();
					}else{
						stockusid=cstm.getSusid();
					}
				}
				if(s.getIbusinessid()==3){
					if(cstm.getLgtp().equals("02")&&cstm.getTtlb().equals("01")&&cstm.getUstp().equals("02")&&cstm.getUsqx().equals("11110000000000000000")){
						stockusid=cstm.getSusid();
					}
				}
				
				boolean b=stockswareDao.saveStockVolumeCustom(jectlist, stockusid);
				if(!b){
					if(s.getIbusinessid()!=3){
						stockswareDao.saveStockvolum(jectlist);
					}
				}
			}else if(s.getIbusinessid()==1){
				stockswareDao.saveStockvolum(jectlist);
			}

			System.out.println("开始保存数据8");
			rs.addRow(new String[] { "true",
					s.getId().getIsalesvoucherid().toString() });

		} catch (Exception e) {
			if (seatlockid > 0) {
				if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {/*

					try {
						javax.xml.rpc.Service service = null;
						java.net.URL endpointURL = new java.net.URL("http://"
								+url
								+ "/services/centersaleService?wsdl");
						CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
								endpointURL, service);
						ssl.setMaintainSession(true);
						// 预留量更新
						ssl.updatehfseatlock(seatlockid);

					} catch (Exception e11) {
						throw new RuntimeException(e11.getMessage());
					}
				*/}
			} else {
				if(haveseat1){/*
					if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

						try {
							javax.xml.rpc.Service service = null;
							java.net.URL endpointURL = new java.net.URL("http://"
									+ url
									+ "/services/centersaleService?wsdl");
							CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
									endpointURL, service);
							ssl.setMaintainSession(true);
							// 预留量更新
							if(!seatids.equals("")){
								ssl.uphfshuijiseat(comticketsalesdetails, seatids);
							}
						} catch (Exception e11) {
							throw new RuntimeException(e11.getMessage());
						}
					}
				*/}
				System.out.println("save42,Exception" + e.toString());
				throw new RuntimeException(e.getMessage());
			}
		}
		return rs;
	}

	/**
	 * 
	 * Describe:售票保存时 操作个人结存明细表
	 * 
	 * @auth:lijingrui
	 * @param empid
	 *            售票员ID
	 * @param salesvoucherdetail
	 *            销售凭证明细 return:void Date:2012-8-15
	 */
	public void addPersonaldetails(Long empid, String[] salesvoucherdetail) {
		for (int x = 0; x < salesvoucherdetail.length; x++) {
			String isalesvoucherdetail = salesvoucherdetail[x];
			String[] voudeta = isalesvoucherdetail.split("&");
			Long itickettypeid = new Long(voudeta[2]);
			Long amount = new Long(voudeta[3]);
			String bymaketicketway = "00";
			String szticketprintno = "";
			if (voudeta.length > 6) {
				bymaketicketway = voudeta[6];
				if (bymaketicketway.equals("01")) {
					Edmtickettypetab edmtick = (Edmtickettypetab) this.get(
							Edmtickettypetab.class, itickettypeid);
					// 介质类型为 一维条码 二维条码的 是预制票
					if (edmtick.getBymediatype().equals("00")
							|| edmtick.getBymediatype().equals("01")) {

						szticketprintno = voudeta[8];
						String[] sperialnum = szticketprintno.split("[|]");
						String startcode = sperialnum[0];
						String endcode = sperialnum[sperialnum.length - 1];

						// 取出票号规则
						String sql = " from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="
								+ edmtick.getIscenicid();
						List list = this.find(sql);
						String startserial = null;
						String endticket = null;
						Long startnum = null;
						Long endnum = null;
						if (list != null && list.size() > 0) {
							Edmticketnoruletab ticketrule = (Edmticketnoruletab) list
									.get(0);
							// 取出参数起始流水号
							startserial = startcode.substring(
									ticketrule.getIntons2()
											+ ticketrule
													.getItickettypecodepos(),
									ticketrule.getIntons2()
											+ ticketrule
													.getItickettypecodepos()
											+ ticketrule.getIserialnolen());
							// 取出参数截止流水号
							endticket = endcode.substring(
									ticketrule.getIntons2()
											+ ticketrule
													.getItickettypecodepos(),
									ticketrule.getIntons2()
											+ ticketrule
													.getItickettypecodepos()
											+ ticketrule.getIserialnolen());

							if (ticketrule.getIntons1() != null
									&& ticketrule.getIntons1() == 1) {
								startnum = Long.parseLong(startserial);
								endnum = Long.parseLong(endticket);
							} else {
								startnum = Tools.Text36ToConvert(startserial);
								endnum = Tools.Text36ToConvert(endticket);
							}
						}

						// 取出个人结存明细列表
						String msql = " from Iompersonalticketdetails where itickettypeid="
								+ itickettypeid
								+ " and ireceiverid="
								+ empid
								+ " and istartserial<="
								+ startnum
								+ " and iendserial>=" + endnum;
						List perDetail = this.find(msql);
						if (perDetail != null && perDetail.size() > 0) {
							boolean b = false;
							// 循环个人结存明细列表，判断传参进来的起始票号
							for (int i = 0; i < perDetail.size(); i++) {
								Iompersonalticketdetails staDetail = (Iompersonalticketdetails) perDetail
										.get(i);
								if (startcode.equals(staDetail
										.getSzstartticketcode())) { // 个人明细起始票号与参数起始票号做比较
									if (endcode.equals(staDetail
											.getSzendticketcode())) { // 个人明细截止票号与参数截止票号做比较
										this.delete(staDetail);
										b = true;
										break;
									} else {
										staDetail.setSzstartticketcode(this
												.showViewendcode(itickettypeid,
														endcode, 2L, empid));
										staDetail.setIstartserial(endnum + 1L);
										staDetail.setIamount(staDetail
												.getIamount() - amount);
										this.update(staDetail);
										b = true;
										break;
									}
								} else if (endcode.equals(staDetail
										.getSzendticketcode())) { // 个人明细截止票号与参数截止票号做比较
									if (startcode.equals(staDetail
											.getSzstartticketcode())) { // 个人明细起始票号与参数起始票号做比较
										this.delete(staDetail);
										b = true;
										break;
									} else {
										staDetail.setSzendticketcode(this
												.showViewendcode(itickettypeid,
														startcode, 0L, empid));
										staDetail.setIendserial(startnum - 1L);
										staDetail.setIamount(staDetail
												.getIamount() - amount);
										this.update(staDetail);
										b = true;
										break;
									}
								}
								if (!b) {
									Iompersonalticketdetails detail = new Iompersonalticketdetails();
									detail.setSzstartticketcode(this
											.showViewendcode(itickettypeid,
													endcode, 2L, empid));

									Long maxid = this.getMaxPk("idetailsid",
											"Iompersonalticketdetails") + 1L;
									detail.setIdetailsid(maxid);
									detail.setItickettypeid(itickettypeid);
									detail.setIreceiverid(empid);
									detail.setSzendticketcode(staDetail
											.getSzendticketcode());
									Long serial = endnum;
									detail.setIstartserial(serial + 1L);
									detail.setIendserial(staDetail
											.getIendserial());
									detail.setIamount(staDetail.getIendserial()
											- serial);
									this.save(detail);

									staDetail.setSzendticketcode(this
											.showViewendcode(itickettypeid,
													startcode, 0L, empid));
									Long startial = startnum;
									staDetail.setIendserial(startial - 1L);
									staDetail.setIamount(startial
											- staDetail.getIstartserial());
									this.update(staDetail);
									break;
								}
							}
						}

					} else {// IC卡

						Hotelprovider provider = (Hotelprovider) this.get(
								Hotelprovider.class, edmtick.getIscenicid());
						if (provider != null && provider.getInoteger5() == 1) {
							String sql = " from Kcpersonalticketdetailstab person where person.ireceiverid="
									+ empid
									+ " and person.itickettypeid="
									+ itickettypeid;
							Kcpersonalticketdetailstab kcperson = (Kcpersonalticketdetailstab) this
									.find(sql).get(0);
							Long mount = kcperson.getIamount() - amount;
							if (mount > 0) {
								kcperson.setIamount(mount);
								this.update(kcperson);
							} else if (mount == 0) {
								this.delete(kcperson);
							}
						}

					}

				} else {
					break;
				}
			}

		}

	}

	/**
	 * Describe:根据票ID、起始票号和数量计算出截止票号
	 * 
	 * @auth:aozhuozu
	 * @param itickettypeid
	 *            选择的需售的票产品
	 * @param ticketcode
	 *            起始票号
	 * @param iamount
	 *            数量
	 * @param empid
	 *            登录售票软件的员工ID
	 * @return return:List Date:2012-7-31
	 */
	public String showViewendcode(Long itickettypeid, String ticketcode,
			Long iamount, Long empid) {
		// List perDetail=findAllPersonalticket(empid,itickettypeid);
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(
				Edmtickettypetab.class, itickettypeid);
		StringBuffer endcode = new StringBuffer();
		if (edmticket != null) {
			String sql = " from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="
					+ edmticket.getIscenicid();
			List lst = this.find(sql);
			if (lst != null && lst.size() > 0) {
				Edmticketnoruletab ticketrule = (Edmticketnoruletab) lst.get(0);
				// 获取票号前缀
				String start = ticketcode.substring(0, ticketrule.getIntons2()
						+ ticketrule.getItickettypecodepos());
				// 获取票号后缀
				String end = ticketcode.substring(
						ticketcode.length()
								- Integer.parseInt(ticketrule
										.getSzticketnosuffix()),
						ticketcode.length());
				// 获取开始票号的流水号
				String lsh = ticketcode.substring(
						ticketrule.getIntons2()
								+ ticketrule.getItickettypecodepos(),
						ticketrule.getIntons2()
								+ ticketrule.getItickettypecodepos()
								+ ticketrule.getIserialnolen());

				Long istartserial = null;
				if (ticketrule.getIntons1() != null
						&& ticketrule.getIntons1() == 1) {
					istartserial = Long.parseLong(lsh);
				} else {
					istartserial = Tools.Text36ToConvert(lsh);
				}
				// Long istartserial = Tools.Text36ToConvert(lsh);
				Long szcodeend = null;
				Long num = null;
				String hsql = " from Iompersonalticketdetails where itickettypeid="
						+ itickettypeid
						+ " and ireceiverid="
						+ empid
						+ " and iendserial>="
						+ istartserial
						+ " order by istartserial";
				List perDetail = this.find(hsql);
				for (int i = 0; i < perDetail.size(); i++) {
					Iompersonalticketdetails details = (Iompersonalticketdetails) perDetail
							.get(i);
					if (istartserial >= details.getIstartserial()
							&& istartserial <= details.getIendserial()) {
						int sign = i + 1;
						num = details.getIendserial() - istartserial + 1;
						if (num >= iamount) {
							szcodeend = istartserial + iamount - 1L;
						} else {
							iamount -= num;
							for (; sign < perDetail.size(); sign++) {
								Iompersonalticketdetails detailss = (Iompersonalticketdetails) perDetail
										.get(sign);
								if (iamount <= detailss.getIamount()) {
									szcodeend = detailss.getIstartserial()
											+ iamount - 1;
									break;
								} else {
									iamount -= detailss.getIamount();
								}
							}
						}
						break;
					}
				}
				// 截止票号
				StringBuffer szcodes = new StringBuffer();
				if (szcodeend.toString().length() < ticketrule
						.getIserialnolen()) {
					for (int b = 0; b < ticketrule.getIserialnolen()
							- szcodeend.toString().length(); b++) {
						szcodes.append("0");
					}
				}
				szcodes.append(szcodeend);

				StringBuffer endcodesg = new StringBuffer();
				// String szflse =
				// Tools.ConvertTo36Text(Long.parseLong(szcodes.toString()), 0);
				// if(szflse.length()<ticketrule.getIserialnolen()){
				// for (int b = 0; b <ticketrule.getIserialnolen()
				// -szflse.length(); b++) {
				// endcodesg.append("0");
				// }
				// }
				// endcodesg.append(szflse);
				if (ticketrule.getIntons1() != null
						&& ticketrule.getIntons1() == 0) {
					String szflse = Tools.ConvertTo36Text(
							Long.parseLong(szcodes.toString()), 0);
					if (szflse.length() < ticketrule.getIserialnolen()) {
						for (int b = 0; b < ticketrule.getIserialnolen()
								- szflse.length(); b++) {
							endcodesg.append("0");
						}
					}
					endcodesg.append(szflse);
				} else {
					endcodesg.append(szcodes);
				}

				endcode.append(start);
				endcode.append(endcodesg);
				endcode.append(end);
			}
		}
		return endcode.toString();
	}
	
	/**
	 * 凭证明细表
	 * 
	 * @param zdetaillist
	 *            凭证明细表中票号
	 * @param s
	 * @param sd
	 * @param comticketsalesdetails
	 *            s * @return
	 */
	public List SaveStscomticketsalesdetailstab(List zdetaillist,
			Stssalesvouchertab s, Stssalesvoucherdetailstab sd,
			String comticketsalesdetails) {
		String[] comticketsalesdetail = comticketsalesdetails.split(":");

		// 2014-06-28 lijingrui修改 是否允许自定义价格
		Double mactualsaleprice = sd.getMactualsaleprice();
		Double price = 0D; // 子票价格
		boolean b = false;
		Edmcrowdkindpricetab edmprice = null;
		Edmticketproduct tp = (Edmticketproduct) this.get(
				Edmticketproduct.class, sd.getItickettypeid());
		if (tp != null) {
			if (tp.getInoteger4() != null && tp.getInoteger4() != 0) {
				b = true;
				edmprice = (Edmcrowdkindpricetab) this.get(
						Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
			}
		}

		for (int i = 0; i < comticketsalesdetail.length; i++) {
			String[] zdetail = comticketsalesdetail[i].split("&");
			Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
			StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
			Long isalesvoucherdetailsid = new Long(zdetail[0]);
			if (isalesvoucherdetailsid.longValue() == sd.getId()
					.getIsalesvoucherdetailsid()) {
				Long icrowdkindpriceid = new Long(zdetail[1]);
				Long itickettypeid = new Long(zdetail[2]);
				Long iztickettypeid = new Long(zdetail[3]);
				Long isplitamount = new Long(zdetail[4]);
				Long tripid = new Long(zdetail[5]);
				Long ivenueid = new Long(zdetail[6]);
				Long ivenueareaid = new Long(zdetail[7]);
				String dtstartdate = zdetail[8];
				String dtenddate = zdetail[9];
				Long iprogramid = 0L;
				String seats = "";
				System.out.println("zdetail.length=" + zdetail.length);
				if (zdetail.length >= 11) {
					iprogramid = new Long(zdetail[10]);
				}
				if (zdetail.length >= 12) {
					seats = zdetail[11];
				}
				/**
				 * 判断产品是按子产品自己有效期还是套票有效期
				 */
				Edmticketproduct et = (Edmticketproduct) this.get(
						Edmticketproduct.class, itickettypeid);
				if (et == null) {
					dtstartdate = sd.getDtstartdate();
					dtenddate = sd.getDtenddate();
				} else {
					if (et.getInoteger1() == null || et.getInoteger1() == 0) {
						dtstartdate = sd.getDtstartdate();
						dtenddate = sd.getDtenddate();
					}
				}
				zstdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
				zstdid.setIticketstationid(s.getId().getIticketstationid());
				zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
				zstd.setIcrowdkindpriceid(icrowdkindpriceid);
				zstd.setItickettypeid(itickettypeid);
				zstd.setIztickettypeid(iztickettypeid);
				zstd.setMhandcharge(new Double(0));
				zstd.setDtmakedate(Tools.getDayTimes());
				zstd.setTripid(tripid);
				zstd.setIvenueareaid(ivenueareaid);
				zstd.setIvenueid(ivenueid);
				zstd.setIvenueseatsid(iprogramid);
				zstd.setSeatsid(seats);

				// 根据节目 场馆 趟次 查找排班数据，读取 节目演出时间
				if (iprogramid > 0) {
					try {
						List<Map> tlist = this
								.findBySqlToMap("select td.starttime,td.endtime,td.iadvanceminute,td.ilagminute,t.itripprdcontrolid from Tripprdcontroltab t,Tripprdcontroldetailtab td where t.iprogramid="
										+ iprogramid
										+ " and t.ivenueid="
										+ ivenueid
										+ "  and  t.itripprdcontrolid=td.itripprdcontrolid and t.startdata<='"
										+ dtstartdate
										+ "' and t.enddata>='"
										+ dtstartdate
										+ "' and td.itripid= "
										+ tripid);
						Map map = tlist.get(0);
						dtstartdate = sd.getDtstartdate();
						dtenddate = sd.getDtenddate();

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Calendar startTime = Calendar.getInstance();
						startTime.setTime(sdf.parse(dtstartdate + " "
								+ map.get("STARTTIME") + ":00"));
						/*startTime.add(
								Calendar.MINUTE,
								Integer.parseInt(map.get("IADVANCEMINUTE")
										.toString()) * -1);*/
						zstd.setDtstartdate(sdf.format(startTime.getTime()));

						Calendar endTime = Calendar.getInstance();
						endTime.setTime(sdf.parse(dtenddate + " "
								+ map.get("ENDTIME") + ":59"));
						/*endTime.add(Calendar.MINUTE, Integer.parseInt(map.get(
								"ILAGMINUTE").toString()));*/
						zstd.setDtenddate(sdf.format(endTime.getTime()));

						zstd.setItripprdcontrolid(new Long(map.get(
								"ITRIPPRDCONTROLID").toString()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					if (Tools.getTodayString().equals(dtstartdate)) {
						Edmtickettypetab eticket = (Edmtickettypetab) this
								.get(Edmtickettypetab.class, itickettypeid);
						if (eticket.getInt1()!=null&&eticket.getInt1() == 1) {
							// 有效开始时间已当前时间开始
							String nowtime=Tools.getNowTime();
							zstd.setDtstartdate(dtstartdate+" "+nowtime);
							zstd.setDtenddate(Tools.getDate(dtenddate, 1) + " "
									+ nowtime);
							
							
						}else{
							zstd.setDtstartdate(dtstartdate + " 00:00:00");
							zstd.setDtenddate(dtenddate + " 23:59:59");
						}
					} else {
						zstd.setDtstartdate(dtstartdate + " 00:00:00");
						zstd.setDtenddate(dtenddate + " 23:59:59");
					}
				}
				zstd.setIversion(new Long(0));
				List list = this
						.find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
								+ icrowdkindpriceid
								+ " and itickettypeid='"
								+ iztickettypeid + "'");
				Edmticketcomposepricetab edt = (Edmticketcomposepricetab) list
						.get(0);
				zstdid.setIcomticketsalesdetailsid(edt.getId()
						.getIticketcomposepriceid());
				zstd.setId(zstdid);
				zstd.setIsplitamount(isplitamount * edt.getNumb());

				// 允许自定义价格 2014-06-28
				if (b) {
					// 根据子票价格所占百分比来获取更改后的子票价格
					Double jiage = 0D;
					if (i == comticketsalesdetail.length - 1) {
						jiage = sd.getMactualsaleprice() - price;
					} else {
						jiage = Double.parseDouble(String.format(
								"%.2f",
								edt.getMactualsaleprice()
								/ edmprice.getMactualsaleprice()
								* sd.getMactualsaleprice()));
						;
						price += jiage;
					}

					zstd.setMsplitprice(jiage);
					zstd.setMsplitmoney(jiage * edt.getNumb() * isplitamount);
					zstd.setMderatemoney(sd.getIderatenums() * jiage);

				} else {
					zstd.setMsplitprice(edt.getMactualsaleprice());
					zstd.setMsplitmoney(edt.getMactualsaleprice()
							* edt.getNumb() * isplitamount);
					zstd.setMderatemoney(sd.getIderatenums()
							* edt.getMactualsaleprice());
				}

				zstd.setIderatenums(sd.getIderatenums());

				zdetaillist.add(zstd);
			}
		}
		return zdetaillist;
	}

	/**
	 * 凭证明细表
	 * 
	 * @param zdetaillist
	 *            凭证明细表中票号
	 * @param s
	 * @param sd
	 * @param comticketsalesdetails
	 *            s * @return
	 */
	public List SaveSeatsaletab(List cseatlist, Stssalesvoucherdetailstab sd,
			List zdetaillist, Long byusage) {
		if (byusage == 0) {
			// 一票一人
			for (int i = 0; i < zdetaillist.size(); i++) {
				Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
						.get(i);
				if (zstd.getId().getIsalesvoucherdetailsid().longValue() == sd
						.getId().getIsalesvoucherdetailsid().longValue()) {
					if (zstd.getIvenueseatsid() > 0) {
						// 保存有节目
						String seats = zstd.getSeatsid();
						if (seats != null && !seats.equals("")) {
							String[] seatids = seats.split(">");
							for (int j = 1; j <= zstd.getIsplitamount(); j++) {
								Seatsaletab seat = new Seatsaletab();
								SeatsaletabId seatid = new SeatsaletabId();
								seatid.setSeq(new Long(j));
								seatid.setSzsoldticketid(new Long(j));
								seatid.setIsalesvoucherid(zstd.getId()
										.getIsalesvoucherid());
								seatid.setIticketstationid(zstd.getId()
										.getIticketstationid());
								seatid.setIsalesvoucherdetailsid(zstd.getId()
										.getIsalesvoucherdetailsid());
								seatid.setIcomticketsalesdetailsid(zstd.getId()
										.getIcomticketsalesdetailsid());
								seat.setId(seatid);
								seat.setIprogramid(zstd.getIvenueseatsid());
								seat.setItripid(zstd.getTripid());
								seat.setIvenueid(zstd.getIvenueid());
								seat.setIvenueareaid(zstd.getIvenueareaid());
								seat.setIseatid(new Long(seatids[j - 1]));
								seat.setDtmakedate(Tools.getDayTimes());
								seat.setIsvalid(1L);
								seat.setItripprdcontrolid(zstd
										.getItripprdcontrolid());
								seat.setStartdate(zstd.getDtstartdate()
										.substring(0, 10));
								cseatlist.add(seat);
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < zdetaillist.size(); i++) {
				Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
						.get(i);
				if (zstd.getId().getIsalesvoucherdetailsid().longValue() == sd
						.getId().getIsalesvoucherdetailsid().longValue()) {
					if (zstd.getIvenueseatsid() > 0) {
						// 保存有节目
						String seats = zstd.getSeatsid();
						if (seats != null && !seats.equals("")) {
							String[] seatids = seats.split(">");
							for (int j = 1; j <= zstd.getIsplitamount(); j++) {
								Seatsaletab seat = new Seatsaletab();
								SeatsaletabId seatid = new SeatsaletabId();
								seatid.setSeq(new Long(j));
								seatid.setSzsoldticketid(new Long(1));
								seatid.setIsalesvoucherid(zstd.getId()
										.getIsalesvoucherid());
								seatid.setIticketstationid(zstd.getId()
										.getIticketstationid());
								seatid.setIsalesvoucherdetailsid(zstd.getId()
										.getIsalesvoucherdetailsid());
								seatid.setIcomticketsalesdetailsid(zstd.getId()
										.getIcomticketsalesdetailsid());
								seat.setId(seatid);
								seat.setIprogramid(zstd.getIvenueseatsid());
								seat.setItripid(zstd.getTripid());
								seat.setIvenueid(zstd.getIvenueid());
								seat.setIvenueareaid(zstd.getIvenueareaid());
								seat.setIseatid(new Long(seatids[j - 1]));
								seat.setDtmakedate(Tools.getDayTimes());
								seat.setItripprdcontrolid(zstd
										.getItripprdcontrolid());
								seat.setStartdate(zstd.getDtstartdate()
										.substring(0, 10));
								cseatlist.add(seat);

							}
						}
					}
				}
			}
		}
		System.out.println(cseatlist.size());
		return cseatlist;
	}

	/**
	 * * Describe:获取某用户的价格分组 服务商id可为空
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#searchJgfz(java.lang.String,
	 *      java.lang.Long)
	 * @param usid
	 * @param iscenicid
	 * @return
	 * @author lijingrui Date:2014-4-16
	 */
	public String searchJgfz(String usid, Long iscenicid) {
		StringBuffer result = new StringBuffer();
		StringBuffer hsql = new StringBuffer(
				"select gz.usid as usid,gz.iscenicid as iscenicid,gz.pmcd as pmcd from Jgfz gz,(select connect_by_root usid as susid,connect_by_root corpname as scorpname,connect_by_root bname as bname,connect_by_root ibusinessid as ibusinessid,usid from custom c  START WITH c.lgtp = '02' and ttlb = '01' and ustp='01' connect by prior c.usid = c.susid) c where c.susid=gz.usid and gz.byisuse=1 and c.usid='"
						+ usid + "' ");
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
	
	public synchronized boolean saveorder(Stssalesvouchertab s,
			Stssalessettlementtab st, List detaillist, List cdetaillist,
			List cseatlist, List zdetaillist, List cdzetaillist,
			List cdchecklist, Long seatlockid, String seatids) throws Exception {
		System.out.println("保存数据开始");

		this.save(s);
		this.save(st);
		System.out.println("开始保存数据2");
		for (int i = 0; i < detaillist.size(); i++) {
			Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
					.get(i);
			this.save(sd);
		}
		System.out.println("开始保存数据3");
		Sysparv5 sv5 = (Sysparv5) this.get(Sysparv5.class,
				new Sysparv5Id("PRCS", "01"));
		String printcs = "0";
		if (sv5 != null) {
			printcs = sv5.getPmva();
		}
		System.out.println("开始保存数据4");
		for (int i = 0; i < cdetaillist.size(); i++) {
			Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist.get(i);
			this.save(stsv);
			if (stsv.getBymaketicketway().equals("00")) {
				if (s.getBysalesvouchertype().equals("01")) {
					if (printcs.equals("1")) {
						Ticketprintlist t = new Ticketprintlist();
						t.setSzsalesvoucherno(s.getSzsalesvoucherno());
						t.setIemployeeid(s.getIhandler());
						t.setPrinttype("01");
						t.setSzticketprintno(stsv.getSzticketprintno());
						t.setPrinttime(Tools.getDayTimes());
						t.setIsok(new Long(0));
						try {
							Long printid = this.getSequenceId("PRINT_ID");
							t.setPrintid(printid);
							this.save(t);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println(e1.getMessage());
						}
					}
				}
			}
		}
		System.out.println("开始保存数据5");
		for (int i = 0; i < zdetaillist.size(); i++) {
			Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
					.get(i);
			this.save(zstd);

		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("0")) {
			// 没有中心服务器 数据直接保存
			try {
				for (int i = 0; i < cseatlist.size(); i++) {
					Seatsaletab cseat = (Seatsaletab) cseatlist.get(i);
					Seatstatustab stst = new Seatstatustab();
					SeatstatustabId ststid = new SeatstatustabId();
					ststid.setIvenueid(cseat.getIvenueid());
					ststid.setIvenueareaid(cseat.getIvenueareaid());
					ststid.setItripid(cseat.getItripid());
					ststid.setIseatid(cseat.getIseatid());
					ststid.setStartdate(cseat.getStartdate());
					stst.setStatus(1L);
					stst.setId(ststid);
					stst.setDtmakedate(Tools.getDayTimes());
					this.save(stst);

				}
				if (seatlockid == 0) {
					try {
						boolean flag = this.saveshuijiseat(zdetaillist);
						if (!flag) {
							throw new RuntimeException("座位数不够");
						}
					} catch (Exception e1) {
						System.out.println("save42,Exception1" + e1.toString());
						throw new RuntimeException(e1.getMessage());
					}
				} else {
					try {
						this.saveshuijiseat(zdetaillist, seatlockid);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						throw new RuntimeException(e1.getMessage());
					}
				}
			} catch (Exception e1) {
				System.out.println("save42,Exception" + e1.toString());
				throw new RuntimeException("有座位已售出，请重新预定");

			}
		} else {

			this.saveshuijiseat(zdetaillist, seatids);

		}
		System.out.println("保存座位数据");
		for (int i = 0; i < cseatlist.size(); i++) {
			Seatsaletab cseat = (Seatsaletab) cseatlist.get(i);
			System.out.println("座位号：" + cseat.getIseatid());
			this.save(cseat);
		}
		System.out.println("开始保存数据6");
		for (int i = 0; i < cdzetaillist.size(); i++) {

			Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
					.get(i);

			this.save(stss);
		}
		for (int i = 0; i < cdchecklist.size(); i++) {

			Stsschecktab stss = (Stsschecktab) cdchecklist.get(i);

			this.save(stss);
		}

		System.out.println("保存数据结束");
		return true;
	}
	
	public boolean saveshuijiseat(List zdetaillist) throws Exception {
		System.out.println("开始保存座位1");

		try {
			String sjseatid = "";
			for (int i = 0; i < zdetaillist.size(); i++) {
				Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
						.get(i);
				String sql = "";
				String paixu = "";
				if (dunum % 2 == 0) {
					paixu = " asc";
				} else {
					paixu = " asc";
				}
				// dunum++;
				if (zstd.getIvenueseatsid() > 0) {
					if (zstd.getSeatsid() == null
							|| zstd.getSeatsid().equals("")) {
						Edmtickettypetab eticket = (Edmtickettypetab) this
								.get(Edmtickettypetab.class,
										zstd.getItickettypeid());
						if (sjseatid.equals("")) {
							sql = "select * from ( select s.ivenueseatsid from Venueseats s where  s.ivenueid="
									+ zstd.getIvenueid()
									+ " and ivenueareaid="
									+ zstd.getIvenueareaid()
									+ " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
									+ zstd.getIvenueid()
									+ " and st.ivenueareaid="
									+ zstd.getIvenueareaid()
									+ " and itripid="
									+ zstd.getTripid()
									+ " and startdate='"
									+ zstd.getDtstartdate().substring(0, 10)
									+ "') order by s.ivenueseatsid "
									+ paixu
									+ " ) where rownum<= "
									+ zstd.getIsplitamount();
						} else {
							sql = "select * from (select s.ivenueseatsid from Venueseats s where  s.ivenueid="
									+ zstd.getIvenueid()
									+ " and ivenueareaid="
									+ zstd.getIvenueareaid()
									+ " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
									+ zstd.getIvenueid()
									+ " and st.ivenueareaid="
									+ zstd.getIvenueareaid()
									+ " and itripid="
									+ zstd.getTripid()
									+ " and startdate='"
									+ zstd.getDtstartdate().substring(0, 10)
									+ "') and ivenueseatsid not in ("
									+ sjseatid
									+ ") order by s.ivenueseatsid "
									+ paixu
									+ " ) where rownum<="
									+ zstd.getIsplitamount();
						}

						List list = this.findBySqlToMap(sql);

						if (list.size() < zstd.getIsplitamount()) {
							return false;
						}

						for (int j = 1; j <= zstd.getIsplitamount(); j++) {
							Seatsaletab seat = new Seatsaletab();
							SeatsaletabId seatid = new SeatsaletabId();
							seatid.setSeq(new Long(j));
							if (eticket.getByusage() == 0) {
								seatid.setSzsoldticketid(new Long(j));
							} else {
								seatid.setSzsoldticketid(new Long(1));
							}
							seatid.setIsalesvoucherid(zstd.getId()
									.getIsalesvoucherid());
							seatid.setIticketstationid(zstd.getId()
									.getIticketstationid());
							seatid.setIsalesvoucherdetailsid(zstd.getId()
									.getIsalesvoucherdetailsid());
							seatid.setIcomticketsalesdetailsid(zstd.getId()
									.getIcomticketsalesdetailsid());
							seat.setId(seatid);
							seat.setIprogramid(zstd.getIvenueseatsid());
							seat.setItripid(zstd.getTripid());
							seat.setIvenueid(zstd.getIvenueid());
							seat.setIvenueareaid(zstd.getIvenueareaid());
							// 取一个座位保存一个座位

							Map map = (Map) list.get(j - 1);
							seat.setIseatid(new Long(map.get("IVENUESEATSID")
									.toString()));
							if (sjseatid.equals("")) {
								sjseatid = map.get("IVENUESEATSID").toString();
							} else {
								sjseatid = sjseatid + ","
										+ map.get("IVENUESEATSID").toString();
							}

							seat.setDtmakedate(Tools.getDayTimes());
							seat.setIsvalid(1L);
							seat.setItripprdcontrolid(zstd
									.getItripprdcontrolid());
							seat.setStartdate(zstd.getDtstartdate().substring(
									0, 10));

							this.save(seat);
							Seatstatustab stst = new Seatstatustab();
							SeatstatustabId ststid = new SeatstatustabId();
							ststid.setIvenueid(seat.getIvenueid());
							ststid.setIvenueareaid(seat.getIvenueareaid());
							ststid.setItripid(seat.getItripid());
							ststid.setIseatid(seat.getIseatid());
							ststid.setStartdate(seat.getStartdate());
							stst.setStatus(1L);
							stst.setId(ststid);
							stst.setDtmakedate(Tools.getDayTimes());
							this.save(stst);
						}
					}
				}
			}
			System.out.println("开始保存座位2");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("保存座位失败");
		}
	}
	
	public boolean saveshuijiseat(List zdetaillist, Long iseatlockid) {
		// 读取未受座位
		try {
			Seatlocktab seatlock = (Seatlocktab) this.get(
					Seatlocktab.class, iseatlockid);
			List seatlist = this
					.find(" from Seatlockdetail where id.iseatlockid="
							+ iseatlockid);
			int sknum = 0;
			Stscomticketsalesdetailstab zzstd = (Stscomticketsalesdetailstab) zdetaillist
					.get(0);
			for (int i = 0; i < zdetaillist.size(); i++) {
				Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
						.get(i);
				if (zstd.getIvenueseatsid() > 0) {
					if (zstd.getSeatsid() == null
							|| zstd.getSeatsid().equals("")) {
						Edmtickettypetab eticket = (Edmtickettypetab) this
								.get(Edmtickettypetab.class,
										zstd.getItickettypeid());

						for (int j = 1; j <= zstd.getIsplitamount(); j++) {
							Seatsaletab seat = new Seatsaletab();
							SeatsaletabId seatid = new SeatsaletabId();
							seatid.setSeq(new Long(j));
							if (eticket.getByusage() == 0) {
								seatid.setSzsoldticketid(new Long(j));
							} else {
								seatid.setSzsoldticketid(new Long(1));
							}
							seatid.setIsalesvoucherid(zstd.getId()
									.getIsalesvoucherid());
							seatid.setIticketstationid(zstd.getId()
									.getIticketstationid());
							seatid.setIsalesvoucherdetailsid(zstd.getId()
									.getIsalesvoucherdetailsid());
							seatid.setIcomticketsalesdetailsid(zstd.getId()
									.getIcomticketsalesdetailsid());
							seat.setId(seatid);
							seat.setIprogramid(zstd.getIvenueseatsid());
							seat.setItripid(zstd.getTripid());
							seat.setIvenueid(zstd.getIvenueid());
							seat.setIvenueareaid(zstd.getIvenueareaid());
							Seatlockdetail sld = (Seatlockdetail) seatlist
									.get(sknum);
							sknum = sknum + 1;
							seat.setIseatid(sld.getIseatid());
							sld.setStatus("02");
							this.update(sld);
							seat.setDtmakedate(Tools.getDayTimes());
							seat.setIsvalid(1L);
							seat.setItripprdcontrolid(zstd
									.getItripprdcontrolid());
							seat.setStartdate(zstd.getDtstartdate().substring(
									0, 10));

							this.save(seat);

						}
					}
				}

			}

			if (sknum < seatlock.getSeatcounts().longValue()) {
				for (int i = sknum; i < seatlock.getSeatcounts(); i++) {
					SeatstatustabId ststid = new SeatstatustabId();
					ststid.setIvenueid(seatlock.getIvenueid());
					ststid.setIvenueareaid(seatlock.getIvenueareaid());
					ststid.setItripid(seatlock.getItripid());

					ststid.setStartdate(seatlock.getStartdate());
					Seatlockdetail sld = (Seatlockdetail) seatlist.get(i);
					sld.setStatus("00");
					this.update(sld);
					ststid.setIseatid(sld.getIseatid());
					Seatstatustab sts = (Seatstatustab) this.get(
							Seatstatustab.class, ststid);
					this.delete(sts);
				}
			}
			seatlock.setStatus("02");
			seatlock.setIsalesvoucherid(zzstd.getId().getIsalesvoucherid());
			seatlock.setIticketstationid(zzstd.getId().getIticketstationid());
			this.update(seatlock);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存座位失败");
		}
	}
	
	public boolean saveshuijiseat(List zdetaillist, String seatids) {
		// 读取未受座位
		try {
			String[] seatidd = seatids.split(",");
			int sknum = 0;
			Stscomticketsalesdetailstab zzstd = (Stscomticketsalesdetailstab) zdetaillist
					.get(0);
			for (int i = 0; i < zdetaillist.size(); i++) {
				Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
						.get(i);
				if (zstd.getIvenueseatsid() > 0) {
					if (zstd.getSeatsid() == null
							|| zstd.getSeatsid().equals("")) {
						Edmtickettypetab eticket = (Edmtickettypetab) this
								.get(Edmtickettypetab.class,
										zstd.getItickettypeid());

						for (int j = 1; j <= zstd.getIsplitamount(); j++) {
							Seatsaletab seat = new Seatsaletab();
							SeatsaletabId seatid = new SeatsaletabId();
							seatid.setSeq(new Long(j));
							if (eticket.getByusage() == 0) {
								seatid.setSzsoldticketid(new Long(j));
							} else {
								seatid.setSzsoldticketid(new Long(1));
							}
							seatid.setIsalesvoucherid(zstd.getId()
									.getIsalesvoucherid());
							seatid.setIticketstationid(zstd.getId()
									.getIticketstationid());
							seatid.setIsalesvoucherdetailsid(zstd.getId()
									.getIsalesvoucherdetailsid());
							seatid.setIcomticketsalesdetailsid(zstd.getId()
									.getIcomticketsalesdetailsid());
							seat.setId(seatid);
							seat.setIprogramid(zstd.getIvenueseatsid());
							seat.setItripid(zstd.getTripid());
							seat.setIvenueid(zstd.getIvenueid());
							seat.setIvenueareaid(zstd.getIvenueareaid());
							seat.setIseatid(new Long(seatidd[sknum]));
							sknum = sknum + 1;

							seat.setDtmakedate(Tools.getDayTimes());
							seat.setIsvalid(1L);
							seat.setItripprdcontrolid(zstd
									.getItripprdcontrolid());
							seat.setStartdate(zstd.getDtstartdate().substring(
									0, 10));

							this.save(seat);

						}
					}
				}

			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存座位失败");
		}
	}
	
	// 凭证表
		public Stssalesvouchertab saveStssalesvouchertab(String salesvouchers,
				Long maxid, String szsalesvoucherno, Stssalesvouchertab s) {
			String[] salesvoucher = salesvouchers.split("&");
			Long iscenicid = new Long(salesvoucher[0]);
			Long iticketwinid = new Long(salesvoucher[1]);
			Long ibusinessid = new Long(salesvoucher[2]);
			Long iemployeeid = new Long(salesvoucher[3]);// 出票员
			Double iaccountreceivable = new Double(salesvoucher[5]);
			Double iacceptmoney = new Double(salesvoucher[4]);
			Double igivechange = new Double(salesvoucher[6]);
			String usid = salesvoucher[7];
			Long forceemid = new Long(salesvoucher[8]);
			String pzlb = salesvoucher[9];// 默认 ０１ 销售 ０４ 补入

			s.setIscenicid(iscenicid);
			s.setIticketwinid(iticketwinid);
			s.setIbusinessid(ibusinessid);
			s.setIhandler(iemployeeid);
			s.setIpayeer(iemployeeid);
			s.setImaker(forceemid);
			s.setIauditor(iemployeeid);
			s.setIaccountreceivable(iaccountreceivable);
			s.setIacceptmoney(iacceptmoney);
			s.setIgivechange(igivechange);

			String today = Tools.getDays();
			String daytime = Tools.getDayTimes();
			s.setIyear(new Long(today.substring(0, 4)));
			s.setImonth(new Long(today.substring(5, 7)));
			s.setIday(new Long(today.substring(8, 10)));
			s.setDtmakedate(daytime);
			s.setDtauditdate(today);
			s.setUsid(usid);
			if (salesvoucher.length >= 12) {
				if (salesvoucher[11].equals("")) {
					s.setDyusid("daoyou");
				} else {
					s.setDyusid(salesvoucher[11]);
				}
			} else {
				s.setDyusid("daoyou");
			}
			if (salesvoucher.length >= 13) {
				if (salesvoucher[12].equals("")) {
					s.setIregionalid(new Long(1));
				} else {
					s.setIregionalid(new Long(salesvoucher[12]));
				}
			} else {
				s.setIregionalid(new Long(1));
			}

			s.setBisintegral(new Long(0));
			s.setByprintinvoice(new Long(0));
			s.setBysplitway(new Long(2));
			s.setBisreturn(new Long(1));
			s.setBysalesvouchertype(pzlb);
			s.setBypostrecord(new Long(1));
			s.setBysalesvoucherstate(new Long(1));
			s.setBispay(new Long(0));
			s.setBispayee(new Long(0));
			Esbticketwintab e = (Esbticketwintab) this.get(
					Esbticketwintab.class, s.getIticketwinid());
			StssalesvouchertabId id = new StssalesvouchertabId();
			id.setIticketstationid(e.getIticketstationid());
			id.setIsalesvoucherid(maxid);
			s.setId(id);
			s.setSzsalesvoucherno(szsalesvoucherno);
			return s;
		}
}
