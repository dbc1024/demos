package com.ectrip.ticket.sale.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.ticket.afcset.service.IEsbaccessequiptabService;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.cyt.client.v1.CYTClient;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.Proordercontroltab;
import com.ectrip.ticket.model.stock.StockJson;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.sale.service.iservice.ISaleAutoService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaveVenueService;

public class centersaleService {
	
	@Autowired
	private EcService ecService;
	
	@Autowired
	private IEsbaccessequiptabService esbaccessequiptabService;
	@Autowired
	private CYTClient cytClient;
	
	public ResultBean getResultBean() throws Exception {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "a", "b" });
		rs.addRow(new String[] { "第一列的值", "1243" });
		rs.addRow(new String[] { "第二行列的值", "1243" });
		return rs;

	}

	/**
	 * Describe:获取订单的支付方式。如果是畅游通订单，并且是现场支付的订单，则允许修改订单以及退票
	 * 
	 * @author liujianwen
	 * @return return:String Date:2014-7-3
	 */
	public String getOrderZffs(String orid) {
		/*IOrderService os = (IOrderService) SpringUtil.getBean("orderService");
		MOrder mo = (MOrder) os.get(MOrder.class, orid);*/
		MOrder mo = ecService.getMorderInfo(orid);
		if (mo != null) {
			return mo.getZffs();
		}
		return orid;
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
		ICheckService checkService = (ICheckService) SpringUtil
				.getBean("checkService");
		pojo.cytdto = checkService.findCYTDto(orid,url);
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		String value = "true", message = "";
		pojo.orid = orid;
		pojo.oto_code = pojo.cytdto.Galcompanyinfotab.getSzcompanycode();
		pojo.iscenicid = pojo.cytdto.torder.getId().getIscenicid();

		//IOrderService orderService = (IOrderService) SpringUtil.getBean("orderService");
		List<Esbaccessequiptab> es = esbaccessequiptabService.find(
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

	/**
	 * 售票员登录
	 * 
	 * @param userid
	 * @param password
	 * @return -1 无效用户 -2 所属公司未绑定服务商 -3 你所在公司不能在该售票窗口售票 -4 密码错误 -5
	 *         今日您已经登陆错误次数超过5次 0 您不具有售票权限，不能登陆 1 登陆成功
	 */

	public ResultBean empztlogin(Long iscenicid, String userid, String password)
			throws Exception {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		ResultBean r = saleCenterService
				.empztlogin(iscenicid, userid, password);
		return r;
	}

	public ResultBean emplogin(Long iscenicid, String userid, String password)
			throws Exception {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		ResultBean r = saleCenterService.emplogin(iscenicid, userid, password);
		return r;
		// return saleCenterService.emplogin(iscenicid, userid, password);
	}

	public ResultBean getReservecontrol(Long itickettypeid, String usid,
			String stdt, Long tripid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getReservecontrol(itickettypeid, usid, stdt,
				tripid);
	}

	public ResultBean getscenic() {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getscenic();
	}

	public ResultBean getTripcontrol(Long iscenicid, String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getTripcontrol(iscenicid, stdt);
	}

	public ResultBean getProductcontrol(Long itickettypeid, Long tripid,
			String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getProductcontrol(itickettypeid, tripid, stdt);
	}

	public ResultBean getProductdatacontrol(Long itickettypeid, String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getProductdatacontrol(itickettypeid, stdt);
	}

	public ResultBean UpdateProductReserve(String productcontrols, String usid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);

			}
			ResultBean rs = saleCenterService.updatereserve(plist, usid);
			return rs;
		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
			return rs;
		}
	}

	public ResultBean UpdateProductcontrol(String productcontrols) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}
			try {
				ResultBean rs = saleCenterService.updatecontrol(plist);
				return rs;
			} catch (Exception e) {
				ResultBean rs = new ResultBean();
				rs.setColumnCount(2);
				rs.setColumnNames(new String[] { "false", e.getMessage() });
				return rs;

			}

		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
			return rs;
		}
	}

	public ResultBean getT_order(String orid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_order(orid);
	}

	/*public ResultBean getT_order(String carno, Long iscenicid) {
		System.out.println("远程调用");
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		System.out.println("远程调用2");
		return saleCenterService.getT_order(carno, iscenicid);

	}*/
	
	public ResultBean getT_orderByWin(String carno, Long iscenicid,Long winid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_orderByWin(carno, iscenicid, winid);
	} 

	public ResultBean getT_orderauto(String carno, Long iscenicid) {
		System.out.println("远程调用");
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		System.out.println("远程调用2");
		return saleCenterService.getT_orderauto(carno, iscenicid);

	}

	public ResultBean getM_orderzfusidstdt(String orid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getM_orderzfusidstdt(orid);
	}

	public void saveUseryfk(String usid, String orid, int types, String yfkfs,
			Double mont, Double tpsx, String note) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		try {
			saleCenterService.saveUseryfk(usid, orid, types, yfkfs, mont, tpsx,
					note);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ResultBean getT_orderbyorid(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_orderbyorid(orid, iscenicid);

	}

	public ResultBean chupiaoT_order(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.ChupiaoT_order(orid, iscenicid);

	}

	public ResultBean chupiaoT_orderlist(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.ChupiaoT_orderlist(orid, iscenicid);

	}

	public ResultBean ChupiaoTRealname(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.ChupiaoTRealname(orid, iscenicid);
	}

	public ResultBean updateT_order(String orid, Long iscenicid,
			Long iemployeeid, Double mont) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.updateT_order(orid, iscenicid, iemployeeid,
				mont);

	}

	public ResultBean chupiaoT_zorderlist(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.ChupiaoT_zorderlist(orid, iscenicid);

	}

	public ResultBean getT_orderlist(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_orderlist(orid, iscenicid);

	}

	public ResultBean getT_orderlistty(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_orderlistty(orid, iscenicid);

	}

	public ResultBean getT_zorderlist(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_zorderlist(orid, iscenicid);

	}

	public ResultBean getT_zorderlistbyorderlistid(Long orderlistid,
			String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getT_zorderlistbyorderlistid(orderlistid,
				orid, iscenicid);

	}

	public ResultBean UpdatecancelProductcontrol(String productcontrols) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();

			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}

			return saleCenterService.updatecancelcontrol(plist);
		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
		}
		return null;
	}

	public ResultBean UpdatefhcancelProductcontrol(String productcontrols) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}
			return saleCenterService.updatecancelcontrol(plist);
		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
		}
		return null;
	}

	public ResultBean UpdateordercancelProductcontrol(String productcontrols,
			String orid, String szsalesvoucherno) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}

			return saleCenterService.updatecancelordercontrol(plist, orid,
					szsalesvoucherno);

		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
		}
		return null;
	}

	public ResultBean updatefhordercancelProductcontrol(String productcontrols,
			String orid, String szsalesvoucherno) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}

			return saleCenterService.updatefhordercancelProductcontrol(plist,
					orid, szsalesvoucherno);

		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
		}
		return null;
	}

	public ResultBean getcancelproductcontrol(String productcontrols) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setTripid(tripid);
				p.setStdata(stdata);
				plist.add(p);
			}

			return saleCenterService.getcancelproductcontrol(plist);
		} else {
			return null;
		}

	}

	public ResultBean getTrip(Long itickettypeid, String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getTrip(itickettypeid, stdt);
	}

	public ResultBean getAllTrip(Long iscenicid, String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getAllTrip(iscenicid, stdt);

	}

	public ResultBean UpdatecancelProductreserve(String productcontrols,
			String usid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		if (productcontrols != null && !productcontrols.equals("")) {
			String[] productcontrol = productcontrols.split(":");
			List plist = new ArrayList();
			for (int i = 0; i < productcontrol.length; i++) {
				String[] control = productcontrol[i].split("&");
				Long itickettypeid = new Long(control[0]);
				String controltype = control[1];
				Long tripid = new Long(control[2]);
				String stdata = control[3];
				Long soldnumber = new Long(control[4]);
				Productcontrol p = new Productcontrol();
				p.setItickettypeid(itickettypeid);
				p.setControltype(controltype);
				p.setTripid(tripid);
				p.setStdata(stdata);
				p.setSoldnumber(soldnumber);
				plist.add(p);
			}
			System.out.println("返还预留量数据");
			ResultBean rs = saleCenterService.updatecancelReserve(plist, usid);
			System.out.println(rs.getResult(0, 0));
			System.out.println(rs.getResult(0, 1));
			System.out.println("返还完成");
			return rs;
		} else {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "true", "" });
			return rs;
		}
	}

	public ResultBean cancelt_order(String orid, Long iscenicid, Double mont,
			Long iemployeeid, String szsalesvoucherno, String message,
			Long isqt, Long forceemid,String url) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		try {
			ResultBean rs = saleCenterService.savecancelt_order(orid,
					iscenicid, mont, iemployeeid, szsalesvoucherno, message,
					isqt, forceemid,url);
			ResultBean rs1 = saleCenterService.getT_orderlist(orid, iscenicid);
			for (int i = 0; i < rs1.getRowsCount(); i++) {
				System.out.println(rs1.getResult(i, "NUMB"));
				System.out.println(rs1.getResult(i, "AMNT"));
			}
			try {
				saleCenterService.addOrderLog(szsalesvoucherno, iscenicid,
						iemployeeid, orid);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("---->>订单窗口修改日志写入失败" + e.getMessage());
			}
			return rs;
		} catch (Exception e) {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "false", e.getMessage() });
			return rs;
		}
	}

	public float getsumjifen(String usid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getsumjifen(usid);
	}

	public ResultBean cancelstopraftorder(String orid, Long iscenicid,
			String neworid, Long iemployeeid) {
		// 将orderlist信息分为一条一条

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		try {
			return saleCenterService.savecancelstopraftorder(orid, iscenicid,
					neworid, iemployeeid);
		} catch (Exception e) {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "false", e.getMessage() });
			return rs;
		}
	}

	public Long zhuceticketwin(Long iticketwinid, Long iticketstationid,
			Long iscenicid, String szticketwincode, String szticketwinname,
			String szipaddress, String dtsellstart, String dtsellend,
			String bywintype, Long byisuse, String szmemo, Long iversion,
			String ipaddress) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		Esbticketwintab etw = saleCenterService.getEsbticketwintabBymac(
				iscenicid, szipaddress);
		if (etw != null) {
			return new Long(0);
		} else {
			etw = new Esbticketwintab(iticketwinid, iticketstationid,
					iscenicid, szticketwincode, szticketwinname, szipaddress,
					dtsellstart, dtsellend, bywintype, byisuse, szmemo,
					iversion, ipaddress);
			saleCenterService.savezhuceticketwin(etw);
		}
		return iticketwinid;
	}

	public int changepassword(String userid, String oldpassword,
			String newpassword) throws Exception {
		int rc_id = 0;
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		rc_id = saleCenterService.changepassword(userid, oldpassword,
				newpassword);
		return rc_id;
	}

	public ResultBean updatecptdorder(String productcontrols, String orid,
			Long iscenicid, double tpmont, double tpsx, String zfusid,
			String neworid, String mo, String yo, String to, String tl,
			String tzl) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		try {
			ResultBean rs = saleCenterService.updatecptdorder(productcontrols,
					orid, iscenicid, tpmont, tpsx, zfusid, neworid, mo, yo, to,
					tl, tzl);
			return rs;
		} catch (Exception e) {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			System.out.println(e.getMessage());
			rs.addRow(new String[] { "false", e.getMessage() });
			return rs;
		}
	}

	public ResultBean updatehfcptdorder(String productcontrols, String orid,
			Long iscenicid, double tpmont, double tpsx, String zfusid,
			String neworid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.updatehfcptdorder(productcontrols, orid,
				iscenicid, tpmont, tpsx, zfusid, neworid);
	}

	/**
	 * 读取中心服务器参数 Describe:
	 * 
	 * @auth:yuanchengjun
	 * @param pmky
	 * @param pmcd
	 * @return return:ResultBean Date:2012-3-27
	 */

	public ResultBean getsysparcs(String pmky, String pmcd) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getsysparcs(pmky, pmcd);
	}

	public ResultBean getPrdtripvenuemanage(Long itickettypeid, Long tripid,
			String stdt) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getPrdtripvenuemanage(itickettypeid, tripid,
				stdt);
	}

	public void UpdateCheckcount(String massages) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		saleCenterService.UpdateCheckcount(massages);
	}

	public void UpdateRaftcheck(String massages) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		saleCenterService.UpdateRaftcheck(massages);
	}

	public ResultBean UpdateChangecheckticket(String massages) {

		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.UpdateChangecheckticket(massages);
	}

	public ResultBean updatecphfT_order(String orid, Long iscenicid) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		try {
			return saleCenterService.updatecphfT_order(orid, iscenicid);
		} catch (Exception e) {
			ResultBean rs = new ResultBean();
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			rs.addRow(new String[] { "false", e.getMessage() });
			return rs;
		}
	}

	public ResultBean getzhiwen(String carno) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.getzhiwen(carno);
	}

	public ResultBean savetuiding(String mo, String yo, String to, String tl,
			String tzl) {
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		return saleCenterService.savetuiding(mo, yo, to, tl, tzl);
	}

	public ResultBean getAutoT_orderlist(String orid, Long iscenicid) {
		ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
				.getBean("saleAutoService");
		return saleAutoService.getAutoT_orderlist(orid, iscenicid);

	}

	public ResultBean updatetdseatstuts(String Seatstatustablists)
			throws Exception {
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.updatetdseatstuts(Seatstatustablists);
	}

	public ResultBean updatehfseatstuts(String Seatstatustablists)
			throws Exception {
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.updatehfseatstuts(Seatstatustablists);
	}

	public ResultBean updateseatstuts(String comticketsalesdetail)
			throws Exception {
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.updateseatstuts(comticketsalesdetail);
	}

	public ResultBean Getseatstusts(Long ivenueid, String stdt, Long tripid){
		System.out.println("远程调用读取座位信息");
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.Getseatstusts(ivenueid, stdt, tripid);
	}

	public ResultBean Getareaseatstusts(Long ivenueid, Long ivenueareaid,
			String stdt, Long tripid) {
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.Getareaseatstusts(ivenueid, ivenueareaid, stdt,
				tripid);
	}

	public ResultBean Getseatlocklist(String date) {
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.Getseatlocklist(date);
	}

	public ResultBean getVenueseatsalecount(Long employeeid, String date) {
		System.out.println("Venueseatsalecount");
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.getVenueseatsalecount(employeeid, date);
	}
	public ResultBean updateseatlock(Long iseatlockid,long iticknumb){
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.updateseatlock(iseatlockid,iticknumb);
	}
	public ResultBean updatehfseatlock(Long iseatlockid){
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.updatehfseatlock(iseatlockid);
	}
	public ResultBean upshuijiseat(String comticketsalesdetails) throws Exception{
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.upshuijiseat(comticketsalesdetails);
	}
	public ResultBean uphfshuijiseat(String comticketsalesdetails,
			String seatids){
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.uphfshuijiseat(comticketsalesdetails,seatids);
	}
	public ResultBean getVenueseatsalecountbyiscenicid(String  scenic, String date){
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		return saveVenueService.getVenueseatsalecountbyiscenicid(scenic,date);
	}

    public ResultBean checkStock(String stockJson){
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
        StockJson stock = JSON.parseObject(stockJson, StockJson.class);
        String message = stockService.checkCustomStock(stock.getStocks());
        if(StringUtils.isBlank(message)){
            message = stockService.checkStock(stock.getStocks());
        }
        if(!StringUtils.isBlank(message)){
            rs.addRow(new String[] { "false", message });
        }else{
            rs.addRow(new String[] { "true", message });
        }
        return rs;
    }

    public ResultBean saveStock(String stockJson,String isCheck){
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
        StockJson stock = JSON.parseObject(stockJson, StockJson.class);
        try{
            stockService.saveStockDetails(stock.getStocks(),Boolean.parseBoolean(isCheck));
            rs.addRow(new String[] { "true", "库存保存成功" });
        }catch(Exception e){
            rs.addRow(new String[] { "false", e.getMessage() });
        }
        return rs;
    }

    public ResultBean deleteStock(String orid,Long provider){
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
        try{
            stockService.deleteStockDetails(orid,provider,null,null);
            rs.addRow(new String[] { "true", "库存删除成功" });
        }catch(Exception e){
            rs.addRow(new String[] { "false", "库存删除失败" });
        }
        return rs;
    }

    public ResultBean getCustom(String usid) {
        IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        StringBuffer sql = new StringBuffer();
        Custom c = (Custom) genericDao.get(Custom.class, usid);
        String susid = "";
        String ssusid = "";

        if (c.getSusid() != null && !c.getSusid().equals("")) {
            susid = c.getSusid();
            Custom cc = (Custom) genericDao.get(Custom.class, susid);
            if (cc.getSusid() != null && cc.getSusid().equals("")) {
                ssusid = cc.getSusid();
            } else {
                ssusid = susid;
            }
        } else {
            susid = usid;
            ssusid = usid;
        }
        sql.append("select ct.usid as usid, ct.ibusinessid as ibusinessid, ct.password as password, ct.lgtp as lgtp, ct.ustp as ustp, ct.status as status, ct.usqx as usqx, ct.autofapiao as autofapiao, ct.usdj as usdj, ct.iszhifuself as iszhifuself, ct.zhifuid as zhifuid, ct.isauditself as isauditself, ct.audusid as audusid, ct.ttlb as ttlb, ct.susid as susid, ct.corpname as corpname, ct.tourlicensecode as tourlicensecode, ct.businesslicensescode as businesslicensescode, ct.tourlicenseupid as tourlicenseupid, ct.businesslicensesupid as businesslicensesupid, ct.lname as lname, ct.zjlb as zjlb, ct.zjhm as zjhm, ct.iregionalid as iregionalid, ct.bank as bank, ct.account as account, ct.bname as bname, ct.addr as addr, ct.telno as telno, ct.mobile as mobile, ct.faxno as faxno, ct.qq as qq, ct.msn as msn, ct.postno as postno, ct.email as email, ct.ldate as ldate, ct.lastdate as lastdate, ct.logtimes as logtimes, ct.notea as notea, ct.noteb as noteb, ct.times as times, ct.lmdate as lmdate, ct.lpdate as lpdate, ct.cdate as cdate, ct.mmqt as mmqt, ct.mmda as mmda, ct.daoyouno as daoyouno, ct.note1 as note1, ct.note2 as note2, ct.note3 as note3, ct.note4 as note4, ct.note5 as note5, ct.note6 as note6, ct.note7 as note7, ct.note8 as note8, ct.note9 as note9, ct.note10 as note10, ct.inote1 as inote1, ct.inote2 as inote2, ct.inote3 as inote3, ct.inote4 as inote4, ct.inote5 as inote5, ct.inote6 as inote6, ct.inote7 as inote7, ct.inote8 as inote8, ct.inote9 as inote9, ct.inote10 as inote10 from Custom ct ");

        sql.append(" where ( ct.usid='" + susid + "' or ct.usid='" + usid
                + "' or ct.susid='" + usid + "')");

        List<Map> list = new ArrayList();
        try {
            System.out.println(sql.toString());
            list = genericDao.findBySqlToMap(sql.toString());

        } catch (Exception e) {

            e.printStackTrace();
        }
        ResultBean rb = MapToResultBean.toResultBean(list);

        return rb;
    }

	/**
	 * 取票前部分退订
	 * @param type  类型
	 * @param torderlists 原订单列表
	 * @param newticketinfo 新订单列表
	 * @param user 用户ID
	 * @param orid 订单号
	 * @param iscenicid 景区服务商
	 * @author lizhaodong  2017-04-27 添加
	 * @return
	 */
	public ResultBean debookTicket(String type,String torderlistStr,String newticketinfoStr,String user,String orid,String iscenicid,String url){
		if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
		List<TOrderlist> torderlists=new ArrayList<TOrderlist>();
		List<OrderPojo> newticketinfo = null ;//= new ArrayList<OrderPojo>();
		ResultBean rs = new ResultBean();
		torderlists=JSON.parseArray(torderlistStr,TOrderlist.class);//原订单数据以数据库为准
		//newticketinfo=JSON.parseArray(newticketinfoStr,OrderPojo.class);
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
       /* ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");*/
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {/*
				javax.xml.rpc.Service service = null;
				java.net.URL endpointURL = new java.net.URL("http://"
						+ url
						+ "/services/centersaleService?wsdl");
				CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
						endpointURL, service);
				ssl.setMaintainSession(true);

				com.ectrip.ticket.centersale.client.ResultBean cano = ssl.debookTicket(
						type, torderlistStr,newticketinfoStr,user,orid,iscenicid);

				ResultBean rb = new ResultBean();

				if (cano.getRowsCount() > 0) {

					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				
			*/
				return null;
				} catch (Exception e) {
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				System.out.print(e);
				rb.addRow(new String[] { "false", e.getMessage() });
				return rb;
			}
		}else
		{

			IGenericService genericService = (IGenericService) SpringUtil
					.getBean("genericService");
			/*IOrderService  orderService =(IOrderService) SpringUtil.
					getBean("orderService");
			ITicketService ticketService=(ITicketService)SpringUtil.
					getBean("ticketService");;
			IShopCartService shopcartService=(IShopCartService) SpringUtil.getBean("shopcartService");*/
			if(orid==null||orid.equals("")){
				rs.addRow(new String[] { "false","订单编号不能为空" });
			}
			if(iscenicid==null||iscenicid.equals("")){
				rs.addRow(new String[] { "false","服务商编号不能为空" });
			}
			MOrder morder = (MOrder)genericService.get(MOrder.class, orid);
			if(morder==null)
			{
				rs.addRow(new String[] { "false","订单不存在" });
				return rs;
			}
	   /*TOrder order = orderService.getTOrder(orid, iscenicid);
	   if(order != null && order.getIschupiao() != null && order.getIschupiao() == 1L){
            rs.addRow(new String[] { "false","窗口锁定订单不可修改，请联系工作人员解锁" });
			return rs;
        }*/
			Map  returnmap = new HashMap();
			if(type.equals("01"))//散客部分退订
			{
//				Custom custom =(Custom)orderService.get(Custom.class, morder.getUsid());
				Custom custom = ecService.getCustomByUserId(morder.getUsid());
				// returnmap 键result代表验证状态,false表示不通过,true表示通过.
				// result值为false时,errtp值表示错误类型.
				// errtp:0代表日控制不足，即票已售完,1代表生产量不足，也可以认为票数量不满足当前预定的数量.当状态为0、1时，键errlist对应的值记录着错误的详细信息
				// errtp:2代表用户剩余预付款不足以支付当前订单修改以后要支付的金额,3代表订单已经超过可修改时间
				// 4代表订单状态为不可修改状态,5代表订单无修改 6代表信誉度超过预定趟次积分为正数的排
				// errlist中对象属性errtp:0代表停排，1数量不足，2已售完，3暂时不可销售
				returnmap.clear();
				if (!morder.getDdzt().equals("02")) {
					rs.addRow(new String[] { "false","订单状态不为已支付" });
					return rs;
				} else {
					List tlist = ecService.showscenicidview(orid,iscenicid);
					if (tlist != null && tlist.size() > 0) {
						for (int i = 0; i < tlist.size(); i++) {
							TOrder td = (TOrder) tlist.get(i);
							if (td.getScenictype().equals("01")) {
								try {
						   /* List  tlist2 =orderService.getTOrderList(orid,
					                iscenicid);
					        TOrder t;
					        for (int j = 0; j < tlist2.size(); j++) {
					            t = new TOrder();
					            BeanUtils.populate(t, (Map) tlist2.get(j));
					            List tlistlist = orderService.getTOrderListList(t.getOrid(),
					                    t.getIscenicid());
					            TOrderlist tt = null;
					            for (int k = 0; k < tlistlist.size(); k++) {
					                tt = new TOrderlist();
					                BeanUtils.populate(tt, (Map) tlistlist.get(k));
					                if (tt.getIoffersschemeid() != null
					                        && !tt.getIoffersschemeid().equals("")
					                        && tt.getIoffersschemeid().intValue() > 0) {
					                    Edpofferschemetab schema = (Edpofferschemetab) orderService
					                            .get(Edpofferschemetab.class,
					                                    tt.getIoffersschemeid());
					                    tt.setBasenum(schema.getImultiples().intValue());
					                    tt.setOffernum(schema.getIoffernum().intValue());
					                }
					                torderlists.add(tt);
					            }
					        }	*/
									// 验证退订后的订单是否满足各种可修改的条件
									returnmap = ecService.validateOrderInfo(orid,torderlists, newticketinfo, morder.getZfusid(), morder.getStdt(), custom.getIbusinessid().toString(), iscenicid);
									Boolean result = (Boolean) returnmap.get("result");
									if (result.booleanValue()) {
										String[] orids = new String[2];
										orids[0] = genericService.getMaxNo("000");
										orids[1] = genericService.getMaxNo("000");
										Map<String,Object> oridsMap = new HashMap<String,Object>();
										oridsMap.put("orids", orids);
										returnmap = ecService.editOrderSankeCenter(JSON.toJSONString(torderlists), JSON.toJSONString(newticketinfo), JSON.toJSONString(oridsMap), orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), morder.getUsid());
										/*for (int j = 0; j < orids.length; j++) {
											MOrder editmorder = (MOrder) orderService.get(MOrder.class, orids[j]);// 获取修改的订单
											if (editmorder != null) {
												Orderlog log = new Orderlog();
												log.setLogid(orderService.getMaxPk("logid", "Orderlog") + 1);
												log.setOrid(orid);
												log.setStlg("0158");
												log.setBrief("网上订单出票前退订订单");
												log.setNote("");
												List editlist = orderService.getYOrderListChildList(orids[j], iscenicid);
												if (editlist != null && editlist.size() > 0) {
													if (editmorder.getOrtp().equals("02")) {
														if (log.getNote() != null && !log.getNote().trim().equals("")) {
															log.setNote("</br>");
														}
														log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
													} else if (editmorder.getOrtp().equals("03")) {
														if (log.getNote() != null && !log.getNote().trim().equals("")) {
															log.setNote("</br>");
														}
														log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
													}
													for (int x = 0; x < editlist.size(); x++) {
														Map editMap = (Map) editlist.get(x);
														System.out.println("map value" + editMap);
														log.setNote(log.getNote() + "[" + editMap.get("szscenicname").toString() + "]&nbsp;&nbsp;[" + editMap.get("sztickettypename").toString()
																+ "]&nbsp;&nbsp;[" + editMap.get("szcrowdkindname").toString() + "]&nbsp;&nbsp;" + editMap.get("numb").toString() + "张");
													}
												}
												log.setIemployeeid(null);
												log.setUsid(morder.getUsid());
												log.setLogtype(Long.parseLong("0"));
												log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
												orderService.save(log);
												
											}
										}*/
									}else
									{
										rs.addRow(new String[] { "false","退订失败" });
										return rs;
									}
								} catch (Exception e) {
									e.printStackTrace();
									rs.addRow(new String[] { "false","服务器错误" });
									return rs;
								}
							}
						}
						rs.addRow(new String[] { "true","退订成功" });
						return rs;
					}else
					{
						List list = ecService.showscenicidview(orid);
						if(list!=null)
						{
							rs.addRow(new String[] { "false","订单服务商不一致" });
							return rs;
						}else
						{
							rs.addRow(new String[] { "false","订单数据不存在" });
							return rs;
						}
					}
				}
			}else if(type.equals("02"))//旅行社部分退订
			{
				returnmap.clear();
				/*Custom custom =(Custom)orderService.get(Custom.class, morder.getUsid());
				Custom orderuser = (Custom)genericService.get(Custom.class,
						morder.getZfusid());*/
				Custom custom = ecService.getCustomByUserId(morder.getUsid());
				Custom orderuser = ecService.getCustomByUserId(morder.getUsid());
				// returnmap 键result代表验证状态,false表示不通过,true表示通过.
				// result值为false时,errtp值表示错误类型.
				// errtp:0代表日控制不足，即票已售完,1代表生产量不足，也可以认为票数量不满足当前预定的数量.当状态为0、1时，键errlist对应的值记录着错误的详细信息
				// errtp:2代表用户剩余预付款不足以支付当前订单修改以后要支付的金额,3代表订单已经超过可修改时间
				// 4代表订单状态为不可修改状态,5代表订单无修改 6代表信誉度超过预定趟次积分为正数的排7:积分不足8:
				// errlist中对象属性errtp:0代表停排，1数量不足，2已售完，3暂时不可销售
				if (!morder.getDdzt().equals("02")) {
					rs.addRow(new String[] { "false","订单状态不为已支付" });
					return rs;
				} else {

					if (newticketinfo != null && newticketinfo.size() > 0
							&& morder.getIsjl() != null
							&& morder.getIsjl().intValue() == 1) {
						rs.addRow(new String[] { "false","奖励订单不可以新增票种" });
					} else {
						// 获取价格分组
						try{
							String groupno = ecService.searchJgfz(orderuser.getUsid(),
									Long.parseLong(iscenicid));
							returnmap = ecService.validateInfoIntegrity(newticketinfo,
									orderuser.getIbusinessid().toString(), iscenicid, orid,
									groupno);

							if (returnmap != null && returnmap.size() > 0) {
								rs.addRow(new String[] {"false","退订失败"});
								return rs;
							}
							if (newticketinfo != null) {
								for (int x = 0; x < newticketinfo.size(); x++) {
									OrderPojo newproduct = newticketinfo.get(x);
									Edmtickettypetab ticket = (Edmtickettypetab)genericService
											.get(Edmtickettypetab.class, Long
													.parseLong(newproduct
															.getItickettypeid()));
									if (ticket.getIscenicid().intValue() != Integer
											.parseInt(iscenicid)) {
										rs.addRow(new String[] { "false","参数错误" });
										return rs;
									}
								}
							}
							if (newticketinfo != null) {
								for (TOrderlist t : torderlists) {
									TOrderlist orderlist = (TOrderlist) esbaccessequiptabService.get(
											TOrderlist.class,
											new TOrderlistId(Long.parseLong(t
													.getOrderlistid()), orid, Long
													.parseLong(iscenicid)));
									t.setItickettypeid(orderlist.getItickettypeid());
									t.setIcrowdkindid(orderlist.getIcrowdkindid());
									for (OrderPojo o : newticketinfo) {
										if (t.getItickettypeid().intValue() == Integer
												.parseInt(o.getItickettypeid())
												&& Integer.parseInt(o.getIcrowdkindid()) == t
												.getIcrowdkindid().intValue()) {
											if (o.getTourdate() == null
													|| o.getTourdate().equals("")) {
												t.setNumb(new Long(t.getNumb().intValue()
														+ Integer.parseInt(o.getNumb())));
												newticketinfo.remove(o);
												break;
											} else {
												List<TZorderlist> tzlist = ecService
														.getTZorderlist(Long.parseLong(t
																		.getOrderlistid()), orid,
																Long.parseLong(iscenicid));
												// 且newTicket有浏览日期(有浏览日期说明票含竹筏)
												// 且浏览日期相同
												Productcontrol tripinfo = (Productcontrol) esbaccessequiptabService
														.get(Productcontrol.class,
																Long.parseLong(o
																		.getProductcontrolid()));
												boolean isadd = false;
												//比较子 票的趟次编号和新增票的趟次编号
												for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
													if (zlist.getTripid() != null
															&& zlist.getTripid() != 0
															&& tripinfo.getTripid()
															.intValue() == zlist
															.getTripid().intValue()
															&& t.getIcrowdkindid()
															.intValue() == Integer
															.parseInt(o
																	.getIcrowdkindid())
															&& tripinfo.getStdata().equals(
															o.getTourdate())) {
														t.setNumb(t.getNumb()
																+ Long.parseLong(o
																.getNumb()));
														t.setAmnt(t.getAmnt()
																+ Integer.parseInt(o
																.getNumb())
																* orderlist.getPric());
														isadd = true;
													}
												}
												if (isadd) {
													newticketinfo.remove(o);
													break;
												}
											}
										}
									}
								}
							}

							Long ydznum = 0L; // 预订总数量
							// 判断产品库存控制
							List<Object[]> jectlist = new ArrayList<Object[]>();
							TOrder torder = (TOrder)genericService.get(TOrder.class,
									new TOrderId(orid, Long.parseLong(iscenicid)));
							for (TOrderlist t : torderlists) {
								TOrderlist orderlist = (TOrderlist) esbaccessequiptabService.get(
										TOrderlist.class,
										new TOrderlistId(
												Long.parseLong(t.getOrderlistid()), orid,
												Long.parseLong(iscenicid)));
								if(orderlist==null)
								{
									rs.addRow(new String[] { "false","请到对应服务商窗口退票" });
									return rs;
								}

								// 价格表中 是否允许成团人数控制 当为是(1)的时候 可控制
								Edmcrowdkindpricetab crowprice = (Edmcrowdkindpricetab)genericService
										.get(Edmcrowdkindpricetab.class,
												orderlist.getIcrowdkindpriceid());
								if (crowprice != null && crowprice.getInote3() != null
										&& crowprice.getInote3() == 1) {
									ydznum += t.getNumb();// 获取预订数量
								}

								if (t.getNumb() - orderlist.getNumb() == 0) {
									continue;
								}
								Object[] obj = new Object[] {
										torder.getId().getIscenicid(),
										orderlist.getItickettypeid(),
										t.getNumb() - orderlist.getNumb(),
										torder.getDtstartdate(), torder.getDtenddate() };
								jectlist.add(obj);

							}
							if (newticketinfo != null && newticketinfo.size() > 0) {
								for (int x = 0; x < newticketinfo.size(); x++) {
									OrderPojo newproduct = newticketinfo.get(x);
									Object[] obj = new Object[] {
											torder.getId().getIscenicid(),
											newproduct.getItickettypeid(),
											newproduct.getNumb(), torder.getDtstartdate(),
											torder.getDtenddate() };
									jectlist.add(obj);

									// 价格表中 是否允许成团人数控制 当为是(1)的时候 可控制
									Edmcrowdkindpricetab crowprice = (Edmcrowdkindpricetab)genericService
											.get(Edmcrowdkindpricetab.class, newproduct
													.getIcrowdkindpriceid().longValue());
									if (crowprice != null && crowprice.getInote3() != null
											&& crowprice.getInote3() == 1) {
										ydznum += Long.parseLong(newproduct.getNumb());// 获取预订数量
									}

								}
							}

							Custom cstm = (Custom) esbaccessequiptabService.get(Custom.class,
									morder.getUsid());

							List list = ecService.findList(Long.parseLong(iscenicid),
									cstm.getIbusinessid());
							Hotelprovider h = (Hotelprovider)genericService.get(
									Hotelprovider.class, Long.parseLong(iscenicid));
							if (list != null && list.size() > 0) {
								Esbprovicerq esbprovicerq = (Esbprovicerq) list.get(0);
								if (esbprovicerq.getInt1() != null
										&& esbprovicerq.getInt1() > 0) {
									Long kznum = esbprovicerq.getInt1();
									if (ydznum < kznum) {
										rs.addRow(new String[] { "false","数量不对" });
										return rs;
									}
								}
							} else {
								if (h != null && h.getInoteger10() != null
										&& h.getInoteger10() > 0) {
									Long kznum = h.getInoteger10();
									if (ydznum < kznum) {
										rs.addRow(new String[] { "false","数量不对" });
										return rs;
									}
								}
							}

							// 根据服务商判断订单修改权限 2015-01-29 陈新浩
							Proordercontroltab proorder = (Proordercontroltab)genericService
									.get(Proordercontroltab.class,
											Long.parseLong(iscenicid));
							if (proorder != null) {
								if (proorder.getIsorderscontrol() == 1) {
									int oldtotal = 0;
									List yorderlist = ecService.getYorderlists(
											orid, iscenicid);
									if (yorderlist != null && !yorderlist.isEmpty()) {
										for (int i = 0; i < yorderlist.size(); i++) {
											YOrderlist orderlist = (YOrderlist) yorderlist
													.get(i);
											oldtotal += orderlist.getNumb();
										}
									}
									if (oldtotal >= proorder.getOrdersdegree().intValue()) {
										int total = 0;
										if (torderlists != null && !torderlists.isEmpty()) {
											for (int i = 0; i < torderlists.size(); i++) {
												TOrderlist pojo = torderlists.get(i);
												total += pojo.getNumb();
											}
										}
										if (newticketinfo != null
												&& !newticketinfo.isEmpty()) {
											for (int i = 0; i < newticketinfo.size(); i++) {
												OrderPojo pojo = newticketinfo.get(i);
												total += Integer.parseInt(pojo.getNumb());
											}
										}
										double maxnum = Math.ceil(((double) proorder
												.getOrdersup() / 100 + 1) * oldtotal);
										double minnum = Math.floor((1 - (double) proorder
												.getOrdersdown() / 100) * oldtotal);
										if (total > maxnum) {
											rs.addRow(new String[] { "false","订单退订失败，超过总数量" });
											return rs;
										}
										if (total < minnum) {
											rs.addRow(new String[] { "false","订单退订失败，超过总小数量" });
											return rs;
										}
									}
								}
							}
							// 验证修改过后的订单是否满足各种可修改的条件
							returnmap = ecService.validateOrderInfo(orid, torderlists,
									newticketinfo, morder.getZfusid(), morder.getStdt(),
									custom.getIbusinessid().toString(), iscenicid);
							Boolean result = (Boolean) returnmap.get("result");
							if (result.booleanValue()) {
								String[] orids = new String[2];
								orids[0] = genericService.getMaxNo("000");
								orids[1] = genericService.getMaxNo("000");
								returnmap = ecService.editOrderCenter(torderlists,
										newticketinfo, orids, orid, iscenicid, morder
												.getStdt(), custom.getIbusinessid()
												.toString(), user);
								for (int i = 0; i < orids.length; i++) {
									MOrder editmorder = (MOrder) esbaccessequiptabService.get(
											MOrder.class, orids[i]);// 获取修改的订单 若为空则下次循环
									if (editmorder != null) {
										Orderlog log = new Orderlog();
										log.setLogid(esbaccessequiptabService.getMaxPk("logid",
												"Orderlog") + 1);
										log.setOrid(orid);
										log.setStlg("0158");
										log.setBrief("修改订单");
										log.setNote("");
										List editlist = ecService
												.getYOrderListChildList(orids[i],
														iscenicid);
										if (editlist != null && editlist.size() > 0) {
											if (editmorder.getOrtp().equals("02")) {
												if (log.getNote() != null
														&& !log.getNote().trim()
														.equals("")) {
													log.setNote(log.getNote() + "</br>");
												}
												log.setNote(log.getNote()
														+ "退票&nbsp;&nbsp;");
											} else if (editmorder.getOrtp()
													.equals("03")) {
												if (log.getNote() != null
														&& !log.getNote().trim()
														.equals("")) {
													log.setNote(log.getNote() + "</br>");
												}
												log.setNote(log.getNote()
														+ "添加&nbsp;&nbsp;");
											}
											for (int j = 0; j < editlist.size(); j++) {
												Map editMap = (Map) editlist.get(j);
												System.out.println("map value"
														+ editMap);
												log.setNote(log.getNote()
														+ "["
														+ editMap.get("szscenicname")
														.toString()
														+ "]&nbsp;&nbsp;["
														+ editMap.get(
														"sztickettypename")
														.toString()
														+ "]&nbsp;&nbsp;["
														+ editMap
														.get("szcrowdkindname")
														.toString()
														+ "]&nbsp;&nbsp;"
														+ editMap.get("numb")
														.toString() + "张");
											}
										}
										log.setIemployeeid(null);
										log.setUsid(custom.getUsid());
										log.setLogtype(Long.parseLong("0"));
										log.setLogdatetime(Tools.getDays() + " "
												+ Tools.getNowTime());
										esbaccessequiptabService.save(log);
									}
								}
							}
						} catch (Exception e) {
							returnmap = new HashMap();
							returnmap.put("result", false);
							returnmap.put("errtp", "11");
							if(!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")){
								rs.addRow(new String[] { "false","服务器错误"});
								return rs;
							}else{
								e.printStackTrace();
								rs.addRow(new String[] { "false","服务器错误"});
								return rs;
							}
						}
					}
					rs.addRow(new String[] { "true","退订成功" });
					return rs;
				}
			}else {
				rs.addRow(new String[] { "false","未知游客类型" });
				return rs;
			}
		}
	}


	/**
	 *  取票前全部退订
	 * @param type  类型
	 * @param user 用户ID
	 * @param orid 订单号
	 * @param iscenicid 景区服务商
	 * @author lizhaodong  2017-04-27 添加
	 * @editor  lizhaodong  2017-05-03 修改
	 * @return
	 */
	public ResultBean debookAllTicket(String type,String user,String orid,String iscenicid,String url){
		if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
		List<TOrderlist> torderlists=new ArrayList<TOrderlist>();
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {/*
				javax.xml.rpc.Service service = null;
				java.net.URL endpointURL = new java.net.URL("http://"
						+ url
						+ "/services/centersaleService?wsdl");
				CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
						endpointURL, service);
				ssl.setMaintainSession(true);

				com.ectrip.ticket.centersale.client.ResultBean cano = ssl.debookAllTicket(
						type, user,orid,iscenicid);

				ResultBean rb = new ResultBean();

				if (cano.getRowsCount() > 0) {

					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return rb;
			*/
				return null;
				} catch (Exception e) {
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				System.out.print(e);
				rb.addRow(new String[] { "false", e.getMessage() });
				return rb;
			}
		}else {

			/*IGenericService genericService = (IGenericService) SpringUtil
					.getBean("genericService");
			IOrderService orderService = (IOrderService) SpringUtil.
					getBean("orderService");
			ITicketService ticketService = (ITicketService) SpringUtil.
					getBean("ticketService");
			;
			IShopCartService shopcartService = (IShopCartService) SpringUtil.getBean("shopcartService");*/
			if (orid == null || orid.equals("")) {
				rs.addRow(new String[]{"false", "订单编号不能为空"});
				return rs;
			}
			if (iscenicid == null || iscenicid.equals("")) {
				rs.addRow(new String[]{"false", "服务商编号不能为空"});
				return rs;
			}
			MOrder morder = ecService.getMorderInfo(orid);
//			MOrder morder = (MOrder) genericService.get(MOrder.class, orid);
			if (morder == null) {
				rs.addRow(new String[]{"false", "订单不存在"});
				return rs;
			}
			Map returnmap = new HashMap();
			if (type.equals("01"))//散客类型
			{
				try {
//					Custom custom = (Custom) orderService.get(Custom.class, morder.getUsid());
					Custom custom = ecService.getCustomByUserId(morder.getUsid());
					List mlist = ecService.getMOrderList(orid);
					MOrder morder1 = new MOrder();
					BeanUtils.populate(morder1, (Map) mlist.get(0));
					String[] orids = new String[2];
					TOrder tds = (TOrder) esbaccessequiptabService.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
					if (tds == null) {
						List list = ecService.showscenicidview(orid);
						if (list != null) {
							rs.addRow(new String[]{"false", "订单服务商不一致"});
							return rs;
						} else {
							rs.addRow(new String[]{"false", "订单不存在"});
							return rs;
						}
					} else {
						String ddzt = tds.getDdzt();
						if (!ddzt.equals("02") && !ddzt.equals("23")) {
							rs.addRow(new String[]{"false", "只有订单状态为已支付的订单才可以退订"});
							return rs;
						}
					}
                   /*if(tds.getIschupiao() != null && tds.getIschupiao() == 1L){
                         rs.addRow(new String[] { "false","该订单已被锁定，不允许退订" });
                         return rs;
                    }*/
					torderlists = ecService.getTOrderlists(orid, Long.parseLong(iscenicid));
					orids[0] = "";
					orids[1] = esbaccessequiptabService.getMaxNo("000");
					Map<String,Object> oridsMap = new HashMap<String,Object>();
					oridsMap.put("orids", orids);
					returnmap = ecService.editOrderSankeCenter(JSON.toJSONString(torderlists), null, JSON.toJSONString(oridsMap), orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), custom.getUsid());
					for (int i = 0; i < orids.length; i++) {
						MOrder editmorder = (MOrder) esbaccessequiptabService.get(MOrder.class, orids[i]);// 获取修改的订单
						// 若为空则下次循环
						if (editmorder != null) {
							Orderlog log = new Orderlog();
							log.setLogid(esbaccessequiptabService.getMaxPk("logid", "Orderlog") + 1);
							log.setOrid(orid);
							log.setStlg("0158");
							log.setBrief("修改订单");
							log.setNote("");
							List editlist = ecService.getYOrderListChildList(orids[i], iscenicid);
							if (editlist != null && editlist.size() > 0) {
								if (editmorder.getOrtp().equals("02")) {
									if (log.getNote() != null && !log.getNote().trim().equals("")) {
										log.setNote("</br>");
									}
									log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
								} else if (editmorder.getOrtp().equals("03")) {
									if (log.getNote() != null && !log.getNote().trim().equals("")) {
										log.setNote("</br>");
									}
									log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
								}
								for (int j = 0; j < editlist.size(); j++) {
									Map editMap = (Map) editlist.get(j);
									System.out.println("map value" + editMap);
									log.setNote(log.getNote() + "[" + editMap.get("szscenicname").toString() + "]&nbsp;&nbsp;[" + editMap.get("sztickettypename").toString() + "]&nbsp;&nbsp;["
											+ editMap.get("szcrowdkindname").toString() + "]&nbsp;&nbsp;" + editMap.get("numb").toString() + "张");
								}
							}
							log.setIemployeeid(null);
							log.setUsid(custom.getUsid());
							log.setLogtype(Long.parseLong("0"));
							log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
							esbaccessequiptabService.save(log);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					rs.addRow(new String[]{"false", "服务器错误"});
					return rs;
				}
				rs.addRow(new String[]{"true", "退订成功"});
				return rs;
			} else if (type.equals("02"))//旅行社全退票
			{
				try {
					Custom custom = (Custom) esbaccessequiptabService.get(Custom.class, user);
					List mlist = ecService.getMOrderList(orid);
					MOrder morder1 = (MOrder) esbaccessequiptabService.get(MOrder.class, orid);
					BeanUtils.populate(morder1, (Map) mlist.get(0));
					if (!morder.getDdzt().equals("02") && !morder.getDdzt().equals("23")) {
						rs.addRow(new String[]{"false", "只有订单状态为已支付的订单才可以退订"});
						return rs;
					}

                   /* TOrder tor = (TOrder)genericService.get(TOrder.class, new TOrderId(orid,Long.parseLong(iscenicid)));
                    if(tor != null && tor.getIschupiao() != null && tor.getIschupiao() == 1L){
                        rs.addRow(new String[] { "false","该订单已被锁定，不允许出票" });
                        return rs;
                    }*/
					String[] orids = new String[2];
					torderlists = ecService.getTOrderlists(orid,
							Long.parseLong(iscenicid));
					orids[0] = "";
					orids[1] = esbaccessequiptabService.getMaxNo("000");
					returnmap = ecService.editOrderCenter(torderlists, null, orids,
							orid, iscenicid, morder.getStdt(), custom.getIbusinessid()
									.toString(), user);
					for (int i = 0; i < orids.length; i++) {
						MOrder editmorder = (MOrder) esbaccessequiptabService.get(MOrder.class,
								orids[i]);// 获取修改的订单
						// 若为空则下次循环
						if (editmorder != null) {
							Orderlog log = new Orderlog();
							log.setLogid(esbaccessequiptabService.getMaxPk("logid", "Orderlog") + 1);
							log.setOrid(orid);
							log.setStlg("0158");
							log.setBrief("修改订单");
							log.setNote("");
							List editlist = ecService.getYOrderListChildList(
									orids[i], iscenicid);
							if (editlist != null && editlist.size() > 0) {
								if (editmorder.getOrtp().equals("02")) {
									if (log.getNote() != null
											&& !log.getNote().trim().equals("")) {
										log.setNote("</br>");
									}
									log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
								} else if (editmorder.getOrtp().equals("03")) {
									if (log.getNote() != null
											&& !log.getNote().trim().equals("")) {
										log.setNote("</br>");
									}
									log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
								}
								for (int j = 0; j < editlist.size(); j++) {
									Map editMap = (Map) editlist.get(j);
									System.out.println("map value" + editMap);
									log.setNote(log.getNote()
											+ "["
											+ editMap.get("szscenicname").toString()
											+ "]&nbsp;&nbsp;["
											+ editMap.get("sztickettypename")
											.toString() + "]&nbsp;&nbsp;["
											+ editMap.get("szcrowdkindname").toString()
											+ "]&nbsp;&nbsp;"
											+ editMap.get("numb").toString() + "张");
								}
							}
							log.setIemployeeid(null);
							log.setUsid(custom.getUsid());
							log.setLogtype(Long.parseLong("0"));
							log.setLogdatetime(Tools.getDays() + " "
									+ Tools.getNowTime());
							esbaccessequiptabService.save(log);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					rs.addRow(new String[]{"false", "服务器错误"});
					return rs;
				}
				rs.addRow(new String[]{"true", "退订成功"});
				return rs;
			} else {
				rs.addRow(new String[]{"false", "未知游客类型"});
				return rs;
			}
		}
	}

	public ResultBean isExistIDcardToday(String iscenicID,String idCard, String choiceDate,String url) {
		GenericDao genericDao = (GenericDao) SpringUtil
				.getBean("genericDao");
		ResultBean rs=new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		try {
			String onlineHql;
			String underlineHql;
			if(StringUtils.isNotEmpty(iscenicID))
			{
				onlineHql= "select count(*) from TOrder t,MOrder m,TRealname r " +
						"where t.id.orid =m.orid and m.orid=r.orid " +
						"and (t.orhm='" + idCard + "' or r.idcard='" + idCard + "') and t.id.iscenicid="+ iscenicID+
						" and m.stdt='" + choiceDate + "'";
			}else
			{
				onlineHql= "select count(*) from TOrder t,MOrder m,TRealname r " +
						"where t.id.orid =m.orid and m.orid=r.orid " +
						"and (t.orhm='" + idCard + "' or r.idcard='" + idCard + "') " +
						" and m.stdt='" + choiceDate + "'";
			}

			List find = genericDao.find(onlineHql);
			if(StringUtils.isNotEmpty(iscenicID))
			{
				underlineHql = "select count(*) from Stssoldtickettab s where s.myzj='" + idCard
						+ "' and s.dtstartdate='" + choiceDate + "' and s.iscenicid="+iscenicID;
			}else
			{
				underlineHql = "select count(*) from Stssoldtickettab s where s.myzj='" + idCard
						+ "' and s.dtstartdate='" + choiceDate + "'";
			}
			List find2 = genericDao.find(underlineHql);
			if (
					(find != null && !find.isEmpty() && (((Long)find.get(0))>0)) ||
							(find2 != null && !find2.isEmpty() &&(((Long)find2.get(0))>0)))
			{
				rs.addRow(new String[] { "true", "该身份证当天已有票数据" });
				return rs;
			}else {
				rs.addRow(new String[] { "false", "该身份证当天不存在票数据" });
				return rs;
			}
		}catch (Exception e)
		{
			e.printStackTrace();
			rs.addRow(new String[] { "error", "服务器出错了" });
		}
		return rs;

	}

}
