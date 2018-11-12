package com.ectrip.ticket.sale.service.cytterminal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.cytterminal.CombineOrderInfo;
import com.ectrip.ec.model.cytterminal.ConsumeOrderInfo;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.centersale.T_zorderlist;
import com.ectrip.ticket.model.centersale.Trealname;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.sale.service.cytterminal.client.CytTerminalClient;
import com.ectrip.ticket.sale.service.cytterminal.dao.idao.ICytSaleDao;
import com.ectrip.ticket.sale.service.cytterminal.model.Cyttasktab;
import com.ectrip.ticket.sale.service.cytterminal.service.iservice.ICytSaleService;
import com.ectrip.ticket.sale.service.iservice.ISaleAutoService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaveVenueService;


@Service("cytSaleService")
public class CytSaleService implements ICytSaleService {

	@Autowired
	private ICytSaleDao cytSaleDao;
	
	@Autowired
	private CytTerminalClient cytTerminalClient;
	
	@Autowired
	private SysparServiceApi sysparServiceApi;

	public Sysparv5 findSyspar(String pmky, String pmcd) {
		return sysparServiceApi.findOne(pmky, pmcd);
		//return cytSaleDao.findSyspar(pmky, pmcd);
	}

	public String findOTOCode(Long iscenicid) {
		return cytSaleDao.findOTOCode(iscenicid);
	}

	public Cyttasktab findCytTask(String orid) {
		return cytSaleDao.findCytTask(orid);
	}

	public Object get(Class<?> cls,Object obj){
		return cytSaleDao.get(cls, obj);
	}

	public void saveOrUpdate(Object obj){
		cytSaleDao.saveOrUpdate(obj);
	}

	public List<Edmticketcomposepricetab> finSonPrice(Long priceId){
		return cytSaleDao.finSonPrice(priceId);
	}

	public Esbscenicareatab queryProviderByPosId(String posId){
		return cytSaleDao.queryProviderByPosId(posId);
	}

	public Long getSequence(String sequenceName) throws Exception{
		return cytSaleDao.getSequenceId(sequenceName);
	}

	public List findOTOOrder(String srid){
		return cytSaleDao.findOTOOrder(srid);
	}
	/**
	 *
	 * Describe:
	 * @author:chenxinhao
	 * @param consumeOrderInfo 消费通知数据
	 * @param combineOrderInfo 订单数据
	 * @param iscenicid	服务商ID
	 * @param iticketwinid 窗口ID
	 * @param iemployeeid 售票员ID
	 * @param param [0]:表示预制票信息  [1]：表示现付数据
	 * @return
	 * return:ResultBean
	 * Date:2015-10-31
	 * @throws Exception
	 */
	public ResultBean consumeOrder(int saleType,ConsumeOrderInfo consumeOrderInfo,CombineOrderInfo combineOrderInfo,Long iscenicid,Long iticketwinid,
								   Long iemployeeid,String... param) throws Exception {
		MOrder morder = combineOrderInfo.getMorder();
		TOrder torder = combineOrderInfo.getTorder();
		YOrder yorder = combineOrderInfo.getYorder();
		List<TOrderlist> tlist = combineOrderInfo.getTlist();
		List<TZorderlist> tzlist = combineOrderInfo.getTzlist();
		List<YOrderlist> ylist = combineOrderInfo.getYlist();
		List<YZorderlist> yzlist = combineOrderInfo.getYzlist();

		//保存订单数据
		this.cytSaleDao.save(morder);
		this.cytSaleDao.save(torder);
		this.cytSaleDao.save(yorder);
		for(TOrderlist tl : tlist){
			this.cytSaleDao.save(tl);
		}
		for(TZorderlist tzl : tzlist){
			this.cytSaleDao.save(tzl);
		}
		for(YOrderlist yl : ylist){
			this.cytSaleDao.save(yl);
		}
		for(YZorderlist yzl : yzlist){
			this.cytSaleDao.save(yzl);
		}
		//保存销售凭证数据
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		Long maxid = saleCenterService.updatevouchersequence();
		ResultBean rs = savetorder42(maxid,saleType,morder.getOrid(), iscenicid, iticketwinid, iemployeeid, param[0], param[1]);
		if (!rs.getResult(0, 0).equals("false")) {
			//请求畅游通
			Map<String,String> map = cytTerminalClient.consumeOrder(consumeOrderInfo);
			String code = map.get("code");
			if(!StringUtils.isBlank(code) && "1000".equals(code)){
				return rs;
			}else{
				throw new RuntimeException(map.get("describe"));
			}
		}else{
			throw new RuntimeException(rs.getResult(0, 1));
		}
	}

	@SuppressWarnings("unchecked")
	private ResultBean savetorder42(Long maxid,int saleType,String orid, Long iscenicid,
									Long iticketwinid, Long iemployeeid, String param, String param1) throws Exception {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
				.getBean("saleCenterService");
		ISaveVenueService saveVenueService = (ISaveVenueService) SpringUtil
				.getBean("saveVenueService");
		ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
				.getBean("saleAutoService");
		List printnolist = new ArrayList();// 激活票号list
		String message = "";
		Map m = new HashMap();
		if (!param.equals("")) {
			String[] pn = param.split("#");
			for (int i = 0; i < pn.length; i++) {
				String[] pzn = pn[i].split(",");
				String[] s2 = pzn[1].split("[|]");

				for (int j = 0; j < s2.length; j++) {
					if (m.containsKey(s2[j])) {
						message = "预制票号重复,请重新输入!";
						rs.addRow(new String[] { "false", message });
						return rs;
					} else {
						m.put(s2[j], null);
					}
				}

				TOrderlist t = new TOrderlist();
				t.setOrderlistid(pzn[0]);
				t.setOrid(pzn[1]);// 票号存在这个字段
				printnolist.add(t);
			}
		}
		T_order t_order = null;
		List<T_orderlist> listorder = new ArrayList<T_orderlist>();
		List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
		List<Trealname> Trealnamelist = new ArrayList<Trealname>();
		List tlist = cytSaleDao.ChupiaoT_order(orid, iscenicid);
		if (tlist != null && !tlist.isEmpty()) {
			for (int i = 0; i < tlist.size(); i++) {

				Map map = (Map) tlist.get(i);
				t_order = new T_order();
				BeanUtils.populate(t_order, map);

				String dtmakedate = t_order.getDtmakedate();
				Calendar now = Calendar.getInstance(TimeZone
						.getTimeZone("GMT+08:00"));
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date d1 = df.parse(dtmakedate);
				Calendar begcalendar = Calendar.getInstance();
				begcalendar.setTime(d1);
				Sysparv5 sys = saveVenueService.Getsyspar("CPJG", "01");
				String minutimes = "";
				if (sys != null && !sys.getPmva().equals("")) {
					minutimes = sys.getPmva();
					begcalendar.add(Calendar.MINUTE,
							Integer.parseInt(minutimes));
					if (now.before(begcalendar)) {
						System.out.println("未到出票时间,请于"
								+ Tools.getDayTimes(begcalendar) + "取票");
						message = "未到出票时间,请于"
								+ Tools.getDayTimes(begcalendar) + "后取票";
						rs.addRow(new String[] { "false", message });
						return rs;
					}
				}
				if (t_order.getDdzt().equals("11")) {
					message = "订单已出票，无需再次出票";
					rs.addRow(new String[] { "false", message });
					return rs;
				}
				if (t_order.getDdzt().equals("00")) {
					if (param1.equals("")) {
						message = "前台现付订单应传入支付方式和金额";
						rs.addRow(new String[] { "false", message });
						return rs;
					}
				}
			}

		} else {
			message = "订单不存在";
			rs.addRow(new String[] { "false", message });
			return rs;
		}
		tlist = cytSaleDao.ChupiaoT_orderlist(orid, iscenicid);
		if (tlist != null && !tlist.isEmpty()) {
			for (int i = 0; i < tlist.size(); i++) {
				Map map = (Map) tlist.get(i);
				T_orderlist t1 = new T_orderlist();
				BeanUtils.populate(t1, map);
				Edmtickettypetab eticket = saleCenterService
						.getEdmtickettypetab(new Long(t1.getItickettypeid()));
				t1.setBymaketicketway(eticket.getBymaketicketway());
				if (eticket.getBymaketicketway().equals("01")) {

					// 李经锐 2012-09-06修改 增加判断 介质类型
					if (eticket.getBymediatype().equals("00")
							|| eticket.getBymediatype().equals("01")) {
						// 现场激活 介质类型为 一维条码 二维条码的 是预制票
						boolean b = false;
						for (int k = 0; k < printnolist.size(); k++) {
							TOrderlist to = (TOrderlist) printnolist.get(k);
							if (to.getOrderlistid().equals(
									t1.getOrderlistid())) {
								t1.setSzticketprintno(to.getOrid());
								b = true;
							}
						}
						if (!b) {
							rs.addRow(new String[] { "false",
									"现场激活票必须填写初始票号" });
							return rs;
						}
					}
				}
				listorder.add(t1);

			}
		} else {
			message = "订单明细不存在";
			rs.addRow(new String[] { "false", message });
			return rs;
		}
		tlist = cytSaleDao.ChupiaoT_zorderlist(orid, iscenicid);
		if (tlist != null && !tlist.isEmpty()) {
			for (int i = 0; i < tlist.size(); i++) {
				Map map = (Map) tlist.get(i);
				T_zorderlist t2 = new T_zorderlist();
				BeanUtils.populate(t2, map);
				listzorder.add(t2);
			}
		} else {
			message = "订单子明细不存在";
			rs.addRow(new String[] { "false", message });
			return rs;
		}
		ResultBean rs1 = null;
		if(saleType == 1){
			rs1 = saveVenueService.savetorder42(t_order, listorder,
					listzorder, iemployeeid, iticketwinid, maxid,
					Trealnamelist, param1);
		}else if(saleType == 2){
			rs1 = saleAutoService.savetorder(t_order, listorder, listzorder, iemployeeid,
					iticketwinid, maxid, Trealnamelist);
		}

		if (rs1.getResult(0, 0).equals("false")) {
			rs.addRow(new String[] { rs1.getResult(0, 0),rs1.getResult(0, 1) });
			return rs;
		}else{
			ResultBean rs2 = saleCenterService.updateT_order(orid, iscenicid, iemployeeid,
					new Double(t_order.getZfmont()));
			if (rs2.getResult(0, 0).equals("false")) {

				rs.addRow(new String[] { "false",
						"修改订单状态失败" });
				return rs;
			}
		}
		return rs1;
	}
}

