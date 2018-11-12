package com.ectrip.ec.ticket.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.ticket.ProviderPojo;
import com.ectrip.ec.model.ticket.TicketComparator;
import com.ectrip.ec.model.ticket.TicketPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ec.ticket.service.iservice.ITicketService;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Ordercs;
import com.ectrip.ticket.model.provider.Proordercontroltab;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.model.venuemarketing.Venuearea;

public class TicketService extends GenericService implements ITicketService {
	ITicketDAO ticketDao;
	
	@Autowired
    private  com.ectrip.ec.feign.service.TicketService ticketService;
//	IStatisicsDAO statisicsDao;
	

	/**
	 * 
	 * Describe:获取票名列表
	 * 
	 * @auth:yangguang
	 * @param ibusinessid
	 * @return return:List Date:2012-2-3
	 */
	public List getTicketName(String ibusinessid) {
		return ticketDao.getTicketList(ibusinessid);
	}

	/**
	 * * Describe:根据服务商读取票种
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#getTicketName(java.lang.String,
	 *      java.lang.String)
	 * @param ibusinessid
	 * @param iscenicid
	 * @return
	 * @author yangguang Date:2012-2-4
	 */
	public List getTicketName(String ibusinessid, String iscenicid,
			String groupno) {
		List list = ticketDao.getTicketList(ibusinessid, iscenicid, groupno);
		return list;
	}

	/**
	 * 
	 * Describe:根据日期获取产品价格列表
	 * 
	 * @auth:yangguang
	 * @param itickettypeid
	 * @param ibusinessid
	 * @param date
	 * @return return:List Date:2012-2-6
	 */
	public List getPriceList(String itickettypeid, String ibusinessid,
			String date, String groupno) {
		return ticketDao
				.getPriceList(itickettypeid, ibusinessid, date, groupno);
	}

	/**
	 * 查询产品及价格
	 * 
	 * @param ibussiness
	 *            业务编号
	 * @param date
	 *            日期
	 * @param usid
	 *            登录用户
	 * @return
	 */
	public List<ProviderPojo> getProviderListByGroupId(String ibussiness, String date,
			String usid,List listall) {
		List<ProviderPojo> list = null;
		try {
			list = ticketDao.getJingquProviderList(listall);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {// 获取服务商列表
					ProviderPojo prd = new ProviderPojo();
					BeanUtils.copyProperties(prd, list.get(i));
					Esbscenicareatab p = (Esbscenicareatab) ticketDao.get(Esbscenicareatab.class,prd.getIscenicid());
					if(p.getSzlasttime().compareToIgnoreCase(Tools.getDayTimes().substring(11,16))<=0){
						prd.setMinDay(-1);
					}else{
						prd.setMinDay(0);
					}
					prd.setMaxDay(p.getImaxdata());
					Numjifenset jfset = ticketService.getNumjifenset(prd
							.getIscenicid().toString());
					// 获取价格分组
					String groupno = ticketDao.searchJgfz(usid,	prd.getIscenicid());

					List productlist = ticketDao.getTicketList(ibussiness, prd
							.getIscenicid().toString(), groupno, date);
					Edpofferschemetab proscheme = ticketDao.checkEdpschemet(
							prd.getIscenicid(), date,
							Long.parseLong(ibussiness));
					if (proscheme != null) {
						prd.setScheme(1L);
						prd.setBasnum(proscheme.getImultiples());
						prd.setScnum(proscheme.getIoffernum());
						prd.setSchemetype(proscheme.getBycategorytype());
					}
					
//					List time=null;
					if (productlist != null && productlist.size() > 0) {// 产品列表
						for (int x = 0; x < productlist.size(); x++) {
							TicketPojo ticket = new TicketPojo();
							BeanUtils.populate(ticket, (Map) productlist.get(x));
							Long ticketid =Long.valueOf(ticket.getTicketid().toString());
							Edmtickettypetab ttp=(Edmtickettypetab)ticketDao.get(Edmtickettypetab.class, ticketid);
							if(ttp.getInt3()!=null && ttp.getInt3()==1){
								TimeSharingTicketTab timesharingtickettab = new TimeSharingTicketTab();
								List time = ticketDao.getTimeStock(ttp.getSztickettypecode(),date);
								timesharingtickettab.setTimes(time);
								if(time!=null&&!"".equals(time)){
									ticket.setTime(JSON.toJSONString(timesharingtickettab));
									
								}
							}
							
							
							
							// 价格列表
							ticket.setPricelist(ticketDao.getPriceListByDate(
									ticket.getTicketid().toString(),
									ibussiness, date, groupno));
							if (ticket.getPricelist() == null
									|| ticket.getPricelist().size() < 1) {// 价格列表为空则删除产品
								productlist.remove(x);
								x -= 1;
							} else {
								if (ticket.getBycategorytype().equals("0010")) {// 如果是套票，则给它添加子产品列表
									ticket.setSonTicket(ticketDao
											.getSonTicketList(ticket
													.getTicketid().toString()));
								}
								for (int j = 0; j < ticket.getPricelist()
										.size(); j++) {// 设置积分
									Map map = (Map) ticket.getPricelist()
											.get(j);
									if (jfset != null) {
										Numjifensetlist detail = ticketService
												.getSalesRule(
														Long.parseLong(ticket
																.getTicketid()
																.toString()),
														jfset.getNid(),
														Long.parseLong(map.get(
																"icrowdkindid")
																.toString()),
														Long.parseLong(ibussiness),
														date);
										if (detail != null) {
											map.put("type", detail.getXffs());
											map.put("isyh", 1);
											map.put("point", detail.getIint4());
											map.put("tnum", detail.getIint3());
										}
									}
									// TOOD 获取优惠设置,指定优惠政策

									Edpofferschemetab scheme = ticketDao
											.getScheme(Long.parseLong(ticket
													.getIscenicid()), Long
													.parseLong(ibussiness),
													Long.parseLong(map.get(
															"icrowdkindid")
															.toString()),
													Long.parseLong(ticket
															.getTicketid()
															.toString()), date);
									if (scheme != null) {
										map.put("scheme", 1);
										map.put("basnum",
												scheme.getImultiples());
										map.put("schemetype",
												scheme.getBycategorytype());
										map.put("scnum", scheme.getIoffernum());
										// 指定优惠对象不建议做,如非要做可在这里继续扩展
									}
								}
								productlist.set(x, ticket);// 更新产品
							}
						}
						prd.setTicketlist(productlist);// 产品列表
						
						/*for (int x = 0; x < productlist.size(); x++) {
							TicketPojo ticket = new TicketPojo();
							BeanUtils.populate(ticket, (Map) productlist.get(x));
							Integer ticketid =ticket.getTicketid();
							List time=ticketDao.getTimeStock(ticketid);
							prd.setTimelist(time);
						}*/
						
					}

					// 2014-04-01 lijingrui 获取服务商优惠
					Edpofferschemetab edpoffer = ticketDao.checkEdpschemet(
							prd.getIscenicid(), date,
							Long.parseLong(ibussiness));
					if (edpoffer != null) {
						prd.setScheme(1L);
						prd.setBasnum(edpoffer.getImultiples());
						prd.setSchemetype(edpoffer.getBycategorytype());
						prd.setScnum(edpoffer.getIoffernum());
					}

					list.set(i, prd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=====获取票务信息出错");
		}
		return list;
	}

	/**
	 * 价格分组前的 ，废弃.
	 * 
	 * @deprecated
	 */
	public List<ProviderPojo> getProviderList(String ibussiness, String date) {
		List<ProviderPojo> list = null;
		try {
			list = ticketDao.getJingquProviderList(null);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {// 获取服务商列表
					ProviderPojo prd = new ProviderPojo();
					BeanUtils.copyProperties(prd, list.get(i));
					Numjifenset jfset = ticketService.getNumjifenset(prd
							.getIscenicid().toString());
					List productlist = ticketDao.getTicketList(ibussiness, prd
							.getIscenicid().toString());
					if (productlist != null && productlist.size() > 0) {// 产品列表
						for (int x = 0; x < productlist.size(); x++) {
							TicketPojo ticket = new TicketPojo();
							BeanUtils
									.populate(ticket, (Map) productlist.get(x));
							// 价格列表
							ticket.setPricelist(ticketDao.getPriceListByDate(
									ticket.getTicketid().toString(),
									ibussiness, date));
							if (ticket.getPricelist() == null
									|| ticket.getPricelist().size() < 1) {// 价格列表为空则删除产品
								productlist.remove(x);
								x -= 1;
							} else {
								if (ticket.getBycategorytype().equals("0010")) {// 如果是套票，则给它添加子产品列表
									ticket.setSonTicket(ticketDao
											.getSonTicketList(ticket
													.getTicketid().toString()));
								}
								if (jfset != null) {
									for (int j = 0; j < ticket.getPricelist()
											.size(); j++) {// 设置积分
										Map map = (Map) ticket.getPricelist()
												.get(j);
										Numjifensetlist detail = ticketService
												.getSalesRule(
														Long.parseLong(ticket
																.getTicketid()
																.toString()),
														jfset.getNid(),
														Long.parseLong(map.get(
																"icrowdkindid")
																.toString()),
														Long.parseLong(ibussiness),
														date);
										if (detail != null) {
											map.put("type", detail.getXffs());
											map.put("isyh", 1);
											map.put("point", detail.getIint4());
										}
									}
								}
								productlist.set(x, ticket);// 更新产品
							}
						}
						prd.setTicketlist(productlist);// 产品列表
					}
					/**
					 * 子服务商含有产品的 暂不支持. List sonList =
					 * ticketDao.getsonJIngquProviderByPid(prd.getIscenicid()
					 * .longValue()); for (int j = 0; j < sonList.size(); j++) {
					 * ProviderPojo sprovider = new ProviderPojo();
					 * BeanUtils.copyProperties(sprovider, sonList.get(j)); List
					 * sonticketlist = ticketDao.getTicketList(ibussiness,
					 * sprovider .getIscenicid().toString()); if (sonticketlist
					 * != null && sonticketlist.size() > 0) { for (int y = 0; y
					 * < sonticketlist.size(); y++) { TicketPojo ticket = new
					 * TicketPojo(); BeanUtils.populate(ticket, (Map)
					 * sonticketlist.get(y));
					 * ticket.setPricelist(ticketDao.getPriceListByDate(ticket
					 * .getTicketid().toString(), ibussiness, date)); if
					 * (ticket.getPricelist() == null ||
					 * ticket.getPricelist().size() < 1) {
					 * sonticketlist.remove(y); y -= 1; } else { if
					 * (ticket.getBycategorytype().equals("0010")) {
					 * ticket.setSonTicket(ticketDao.getSonTicketList(ticket
					 * .getTicketid().toString())); } sonticketlist.set(y,
					 * ticket); } } sonList.set(j, sprovider); if (sonticketlist
					 * == null || sonticketlist.size() < 1) {
					 * sprovider.setTicketlist(null); } else {
					 * sprovider.setTicketlist(sonticketlist); } } else {
					 * sonList.remove(j); j -= 1; } } if (sonList == null ||
					 * sonList.size() < 1) { prd.setSonprdlist(null); } else {
					 * prd.setSonprdlist(sonList); }
					 **/
					list.set(i, prd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=====获取票务信息出错");
		}
		return list;
	}

	/**
	 * * Describe:获取票务信息，并封装价格列表
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#getTickInfoList(java.lang.String,
	 *      int, int, java.lang.String)
	 * @param ibusinessid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author yangguang Date:2011-10-9
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List getTickInfoList(String ibusinessid)
			throws IllegalAccessException, InvocationTargetException {
		List list = ticketDao.getTicketList(ibusinessid);
		TicketPojo ticket = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ticket = new TicketPojo();
				BeanUtils.populate(ticket, (Map) list.get(i));
				ticket.setPricelist(ticketDao.getPriceList(ticket.getTicketid()
						.toString(), ibusinessid));
				Numjifenset jfset = ticketService.getNumjifenset(ticket
						.getIscenicid());
				Numjifensetlist detail = ticketService.getSalesRule(ticket
						.getTicketid().toString(), jfset.getNid().toString());
				if (detail != null) {
					ticket.setIsyh("1");
					ticket.setPoint(jfset.getPoint().toString());
				}
				if (ticket.getBycategorytype() != null
						&& !ticket.getBycategorytype().equals("")
						&& ticket.getBycategorytype().equals("0010")) {
					ticket.setSonTicket(ticketDao.getSonTicketList(ticket
							.getTicketid().toString()));
				}
				list.set(i, ticket);
			}
		}
		return list;
	}

	public List getTickInfoListByDate(String ibusinessid, String date)
			throws IllegalAccessException, InvocationTargetException {
		List list = ticketDao.getTicketList(ibusinessid);
		TicketPojo ticket = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ticket = new TicketPojo();
				BeanUtils.populate(ticket, (Map) list.get(i));
				ticket.setPricelist(ticketDao.getPriceListByDate(ticket
						.getTicketid().toString(), ibusinessid, date));
				Numjifenset jfset = ticketService.getNumjifenset(ticket
						.getIscenicid());
				if (jfset != null) {
					Numjifensetlist detail = ticketService.getSalesRule(ticket
							.getTicketid().toString(), jfset.getNid()
							.toString());
					if (detail != null) {
						ticket.setIsyh("1");
						ticket.setPoint(jfset.getPoint().toString());
					}
				}
				if (ticket.getBycategorytype() != null
						&& !ticket.getBycategorytype().equals("")
						&& ticket.getBycategorytype().equals("0010")) {
					ticket.setSonTicket(ticketDao.getSonTicketList(ticket
							.getTicketid().toString()));
				}
				list.set(i, ticket);
			}
		}
		return list;
	}

	public List getSonTicket(String ticketid) {
		return ticketDao.getSonTicketList(ticketid);
	}

	/**
	 * 
	 * Describe:获取竹筏趟次信息 根据产品ID, 乘坐时间
	 * 
	 * @auth:yangguang
	 * @param itickettypeid
	 *            产品ID
	 * @param data
	 *            查询日期
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             return:List Date:2011-10-12
	 */
	public List getRafttripInfoList(String itickettypeid, String date,
			String ziticketid) throws IllegalAccessException,
			InvocationTargetException {
		return ticketDao.getRafttripInfoList(itickettypeid, date, ziticketid);
	}

	/**
	 * * Describe:获取价格
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#getTicketPrice(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 * @param itickettypeid
	 * @param tourDate
	 * @param icrowdkindpriceid
	 * @param ibusinessid
	 * @return
	 * @author yangguang Date:2011-10-27
	 */
	public double getTicketPrice(String itickettypeid, String tourDate,
			String icrowdkindpriceid, String ibusinessid) {
		return ticketDao.getTicketPrice(itickettypeid, tourDate,
				icrowdkindpriceid, ibusinessid);
	}

	public boolean volidationRealname(String itickettypeid, String tourDate,
			String icrowdkindpriceid, String ibusinessid, String groupno) {
		return ticketDao.volidationRealname(itickettypeid, tourDate,
				icrowdkindpriceid, ibusinessid, groupno);
	}

	/**
	 * 
	 * Describe:判断日控制
	 * 
	 * @param 传入基础票集合
	 *            或者已将日期合并之后的套票列表
	 * @auth:yangguangp
	 * @param list
	 * @return return:List Date:2011-11-1
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List dayControll(List<OrderPojo> list)
			throws IllegalAccessException, InvocationTargetException {
		OrderPojo orderPojo = null;
		OrderPojo son = null;
		Edmtickettypetab ticket = null;
		List productCotrolList = null;
		List<OrderPojo> exceptionlist = new ArrayList<OrderPojo>();
		Productcontrol numControl = null;
		for (int i = 0; i < list.size(); i++) {
			orderPojo = list.get(i);
			ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class,
					new Long(orderPojo.getItickettypeid()));
			// 判断是否数量受限, 套票是否受数量限制
			if (ticket.getIssale().intValue() == 1) {// 竹筏票
				if (ticket.getBycategorytype().equals("0010")) {// 套票
					son = orderPojo.getSonTicket().get(0);
					son.setItickettypeid(son.getParentticketid());
					productCotrolList = ticketDao.getNumberControllData(
							son.getParentticketid(), son.getTourdate());
				} else {
					productCotrolList = ticketDao.getNumberControllData(
							orderPojo.getItickettypeid(),
							orderPojo.getTourdate());
				}
				if (productCotrolList == null || productCotrolList.size() == 0) {// 如果受限数据为空且设置不可销售
					if (ticket.getIscontrolsale() == 0) {
						orderPojo.setStatus("-1");// 不让卖
						if (son != null) {
							orderPojo.setTourdate(son.getTourdate());
						}
						orderPojo.setTicketname(ticket.getSztickettypename());
						// 填充数量控制日期
						if (orderPojo.getTourdate() == null
								|| orderPojo.getTourdate().equals("")) {
							orderPojo.setTourdate(son.getTourdate());
						}
						exceptionlist.add(orderPojo);
					}
				} else {// 数据不为空
					for (int x = 0; x < productCotrolList.size(); x++) {// 一般来说这里只有一条记录
						numControl = new Productcontrol();
						BeanUtils.populate(numControl,
								(Map) productCotrolList.get(x));
						if (numControl.getControltype().equals("02")) {// 日控制
							// 日控制 判断剩余量
							if (numControl.getSalablenumber()
									- numControl.getSoldnumber() < Integer
										.parseInt(orderPojo.getTotalnum())) {
								if (orderPojo.getIsenough()) {// 只要有一个产品 的日控制
																// 已售完 则表示此票不足
									// 一般来说这里的值为true
									orderPojo.setStatus("-2");// 不让卖
									orderPojo.setTicketname(ticket
											.getSztickettypename());
									if (orderPojo.getTourdate() == null
											|| orderPojo.getTourdate().equals(
													"")) {
										orderPojo
												.setTourdate(son.getTourdate());
									}
									exceptionlist.add(orderPojo);// 获取含有异常状态的套票
								}
							}
						}
					}
				}
			}
		}
		return exceptionlist;
	}

	/**
	 * 
	 * Describe:趟次控制
	 * 
	 * @auth:yangguang
	 * @param list
	 *            基础票列表 或者合并了同产品 同日期 同趟次的套票列表
	 * @return return:List Date:2011-11-1
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List tripControll(List<OrderPojo> list)
			throws IllegalAccessException, InvocationTargetException {
		OrderPojo orderPojo = null;
		Edmtickettypetab ticket = null;
		List productCotrolList = null;
		Productcontrol numControl = null;
		List exceptionlist = new ArrayList();

		// 遍历预定的列表
		for (int i = 0; i < list.size(); i++) {
			orderPojo = list.get(i);
			ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class,
					new Long(orderPojo.getItickettypeid()));
			// 判断是否数量受限, 套票是否受数量限制
			if (ticket.getIscontrol() == 1) {// 受限制
				productCotrolList = ticketDao.getNumberControllData(
						orderPojo.getItickettypeid(), orderPojo.getTourdate(),
						"03");
				if (productCotrolList == null || productCotrolList.size() == 0) {// 如果受限数据为空
					if (!ticket.getIscontrolsale().equals("1")) {// 设置在无数据时可卖
						orderPojo.setStatus("2");// 趟次不可卖
						exceptionlist.add(orderPojo);
					}
				} else {// 数据不为空
					for (int x = 0; x < productCotrolList.size(); x++) {
						numControl = new Productcontrol();
						BeanUtils.populate(numControl,
								(Map) productCotrolList.get(x));
						if (ticket.getBycategorytype().equals("0010")) {// 套票
							for (OrderPojo sonticket : orderPojo.getSonTicket()) {
								if (numControl.getProductcontrolid().equals(
										sonticket.getProductcontrolid())
										&& numControl.getStdata().equals(
												sonticket.getTourdate())) {
									if (!orderPojo.getIsenough()) {
										if (Integer.parseInt(orderPojo
												.getTotalnum()) > numControl
												.getSalablenumber()
												- numControl.getSoldnumber()) {
											orderPojo.setIsenough(false);
											orderPojo.setStatus("3");// 趟次控制不足
											exceptionlist.add(orderPojo);
										}
									}
								}
							}
						} else {// 基础票
							if (numControl.getProductcontrolid() == Integer
									.parseInt(orderPojo.getProductcontrolid())
									&& numControl.getStdata().equals(
											orderPojo.getTourdate())) {
								if (Integer.parseInt(orderPojo.getTotalnum()) > numControl
										.getSalablenumber()
										- numControl.getSoldnumber()) {
									orderPojo.setIsenough(false);
									orderPojo.setStatus("03");// 趟次控制不足
									exceptionlist.add(orderPojo);
								}
							}
						}
					}
				}
			}
		}
		return exceptionlist;
	}

	// TODO 明天开始返回验证页面 及保存订单
	@SuppressWarnings("unchecked")
	public List<OrderPojo> returnExceptionList(List<OrderPojo> list)
			throws IllegalAccessException, InvocationTargetException {
		// 此列表中存放的是3个 map 第一个是所有的基础票 第二个是所有的套票 第三个是合并了所有同日期同产品的套票
		Map map = splitAllTicketList(list);
		// 获取基础票
		List<OrderPojo> basetickets = (List<OrderPojo>) map.get("baseticket");
		// 获取合并后的套票
		List<OrderPojo> mergekickets = (List<OrderPojo>) map.get("mergekicket");
		// 为合并同日起的套票
		List<OrderPojo> nesttickets = (List<OrderPojo>) map.get("nestticket");
		// 合并基础票与合并过日期的套票
		basetickets.addAll(mergekickets);
		// 日控制
		List<OrderPojo> exceptionList = dayControll(basetickets);
		List<OrderPojo> tripticket = null;
		if (exceptionList == null || exceptionList.size() < 1) {
			// 趟次控制
			tripticket = getNestTicketTripTicketList(nesttickets,
					getTripTicketList(basetickets));
			exceptionList = tripControll(tripticket);
		}
		return exceptionList;
	}

	public boolean hasJfProduct(List<OrderPojo> list) {
		boolean hasyh = false;
		for (int i = 0; i < list.size(); i++) {
			OrderPojo ticket = list.get(i);
			if (ticketDao.isJfproduct(
					Long.parseLong(ticket.getItickettypeid()),
					ticket.getIscenicid())) {
				List<OrderPojo> pricelist = ticket.getPrice();
				if (pricelist != null && pricelist.size() > 0) {
					for (OrderPojo price : pricelist) {
						if (price.getNumb() != null
								&& !"".equals(price.getNumb())
								&& Integer.parseInt(price.getNumb()) > 0) {
							hasyh = true;
							break;
						}
					}
					if (hasyh) {
						break;
					}
				}
			}
		}
		return hasyh;
	}

	public boolean hasnormalproduct(List<OrderPojo> list) {
		boolean hasnormal = false;
		for (int i = 0; i < list.size(); i++) {
			OrderPojo ticket = list.get(i);
			if (!ticketDao.isJfproduct(
					Long.parseLong(ticket.getItickettypeid()),
					ticket.getIscenicid())) {
				List<OrderPojo> pricelist = ticket.getPrice();
				if (pricelist != null && pricelist.size() > 0) {
					for (OrderPojo price : pricelist) {
						if (price.getNumb() != null
								&& !"".equals(price.getNumb())
								&& Integer.parseInt(price.getNumb()) > 0) {
							hasnormal = true;
							break;
						}
					}
					if (hasnormal) {
						break;
					}
				}
			}
		}
		return hasnormal;
	}

	public List putYhInfo(List<OrderPojo> list, Long ibusiness) {
		Map map = new HashMap();
		HashSet providerset = splitProvider(list);
		Iterator ite = providerset.iterator();
		DecimalFormat format = new DecimalFormat("0.00");
		while (ite.hasNext()) {
			Object obj = ite.next();
			Numjifenset set = ticketService.getNumjifenset(obj.toString());
			for (int j = 0; j < list.size(); j++) {
				OrderPojo ticket = list.get(j);
				List<OrderPojo> pricelist = ticket.getPrice();
				if (pricelist != null && pricelist.size() > 0
						&& ticket.getIscenicid().equals(obj.toString())) {
					for (OrderPojo price : pricelist) {
						if (price.getNumb() != null
								&& !"".equals(price.getNumb())
								&& Integer.parseInt(price.getNumb()) > 0) {
							Numjifensetlist detail = ticketService.getSalesRule(
									Long.parseLong(ticket.getItickettypeid()),
									set.getNid(),
									Long.parseLong(price.getIcrowdkindid()),
									ibusiness, ticket.getTourdate());
							price.setIsyh("1");
							price.setJflb(detail.getXffs());
							price.setSmall(String.valueOf(price
									.getMactualsaleprice()
									* Integer.parseInt(price.getNumb())));
							price.setPoint(String.valueOf(detail.getIint4()
									.intValue()));
							price.setBasnum(detail.getIint3().intValue());
							if (detail.getXffs().equals("03")) {
								if (Long.parseLong(price.getNumb())
										% detail.getIint3() > 0) {
									long jf = Long.parseLong(price.getNumb())
											/ detail.getIint3()
											* detail.getIint4()
											+ detail.getIint4();
									ticket.setMonthpoint(String.valueOf(jf));// 积分小计
								} else {
									long jf = Long.parseLong(price.getNumb())
											/ detail.getIint3()
											* detail.getIint4();
									ticket.setMonthpoint(String.valueOf(jf));// 积分小计
								}
							} else {
								if (Long.parseLong(price.getNumb())
										% detail.getIint3() > 0) {
									long jf = Long.parseLong(price.getNumb())
											/ detail.getIint3()
											* detail.getIint4()
											+ detail.getIint4();
									ticket.setYearpoint(String.valueOf(jf));// 积分小计
								} else {
									long jf = Long.parseLong(price.getNumb())
											/ detail.getIint3()
											* detail.getIint4();
									ticket.setYearpoint(String.valueOf(jf));// 积分小计
								}
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * @deprecated
	 */
	public Map isEnougfJifen(List<OrderPojo> list, String stdt, String usid) {
		Map map = new HashMap();
		List errlist = new ArrayList();
		HashSet providerset = splitProvider(list);
		Map month = DateUtils.getMonthDate(
				Integer.parseInt(stdt.substring(0, 4)),
				Integer.parseInt(stdt.substring(5, 7)));
		ArrayList<OrderPojo> plist = new ArrayList<OrderPojo>();
		Collections.sort(list);
		plist.addAll(list);
		Iterator ite = providerset.iterator();
		Custom custom = (Custom) ticketDao.get(Custom.class, usid);
		DecimalFormat format = new DecimalFormat("0.00");
		while (ite.hasNext()) {
			Object obj = ite.next();
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, Long.parseLong(obj.toString()));
			String usermonthJSON = ticketService.getJifenByUser(
					month.get("startDate").toString(), month.get("endDate")
							.toString(), usid, Long.parseLong("1"), Long
							.parseLong(obj.toString()));
			Usernumjf usermonth = JSON.parseObject(usermonthJSON, Usernumjf.class);
			
			
			String useryearJSON = ticketService.getJifenByUser(
					stdt.substring(0, 4) + "-01-01", stdt.substring(0, 4)
							+ "-12-31", usid, Long.parseLong("1"),
					Long.parseLong(obj.toString()));
			Usernumjf useryear = JSON.parseObject(useryearJSON, Usernumjf.class);
			
			Numjifenset set = ticketService.getNumjifenset(obj.toString());
			// 积分产品数量
			double yearjf = 0;
			double monthjf = 0;
			for (int j = 0; j < plist.size(); j++) {
				OrderPojo ticket = plist.get(j);
				if (!obj.toString().equals(ticket.getIscenicid())) {
					break;
				} else {
					List<OrderPojo> pricelist = ticket.getPrice();
					if (pricelist != null && pricelist.size() > 0
							&& ticket.getIscenicid().equals(obj.toString())) {
						for (OrderPojo price : pricelist) {
							Numjifensetlist detail = ticketService.getSalesRule(
									Long.parseLong(ticket.getItickettypeid()),
									set.getNid(),
									Long.parseLong(price.getIcrowdkindid()),
									custom.getIbusinessid(), stdt);
							if (price.getNumb() != null
									&& !"".equals(price.getNumb())
									&& Integer.parseInt(price.getNumb()) > 0) {
								double num = Double
										.parseDouble(price.getNumb());
								if (detail.getXffs().equals("03")) {
									monthjf += Double.parseDouble(format
											.format(num
													/ detail.getIint3()
															.intValue()
													* detail.getIint4()));
								} else {
									yearjf += Double.parseDouble(format
											.format(num
													/ detail.getIint3()
															.intValue()
													* detail.getIint4()));
								}
							}
						}
					}
					plist.remove(ticket);
					j--;
				}
			}
			// 验证月积分
			if (usermonth == null) {
				Map errmap = new HashMap();
				errmap.put("providername", provider.getSzscenicname());
				errmap.put("expense", monthjf);
				errmap.put("surplus", 0);
				errmap.put("type", 1);// 月积分
				errlist.add(errmap);
			} else {
				if (usermonth.getPoint() < monthjf) {
					Map errmap = new HashMap();
					errmap.put("providername", provider.getSzscenicname());
					errmap.put("expense", monthjf);
					errmap.put("surplus", usermonth.getPoint());
					errmap.put("type", 1);
					errlist.add(errmap);
				}
			}
			// 验证年积分
			if (useryear == null) {
				Map errmap = new HashMap();
				errmap.put("providername", provider.getSzscenicname());
				errmap.put("expense", yearjf);
				errmap.put("surplus", 0);
				errmap.put("type", 2);
				errlist.add(errmap);
			} else {
				if (useryear.getPoint() < monthjf) {
					Map errmap = new HashMap();
					errmap.put("providername", provider.getSzscenicname());
					errmap.put("expense", yearjf);
					errmap.put("surplus", usermonth.getPoint());
					errmap.put("type", 2);// 年积分
					errlist.add(errmap);
				}
			}
		}
		map.put("errmsg", errlist);
		return map;
	}

	/**
	 * 验证积分是否足够购买当前物品
	 */
	public Map isEnougfJifen(List<OrderPojo> list, List<LprPojo> lprs,
			String usid) throws IllegalAccessException,
			InvocationTargetException {
		Map map = new HashMap();
		List errlist = new ArrayList();
		HashSet providerset = splitProvider(list);
		ArrayList<OrderPojo> plist = new ArrayList<OrderPojo>();
		Collections.sort(list);
		plist.addAll(list);
		Iterator ite = providerset.iterator();
		Custom custom = (Custom) ticketDao.get(Custom.class, usid);
		while (ite.hasNext()) {
			Object obj = ite.next();
			LprPojo prdlpr = null;
			Esbscenicareatab provider = (Esbscenicareatab) this.get(
					Esbscenicareatab.class, Long.parseLong(obj.toString()));
			for (LprPojo lpr : lprs) {
				if (lpr.getIscenicid().equals(obj.toString())
						|| lpr.getIscenicid().equals(
								provider.getIparentid().toString())) {
					prdlpr = new LprPojo();
					BeanUtils.copyProperties(prdlpr, lpr);
				}
			}
			Map month = DateUtils.getMonthDate(
					Integer.parseInt(prdlpr.getRzti().substring(0, 4)),
					Integer.parseInt(prdlpr.getRzti().substring(5, 7)));
			String usermonthJSON = ticketService.getJifenByUser(
					month.get("startDate").toString(), month.get("endDate")
							.toString(), usid, Long.parseLong("1"), Long
							.parseLong(obj.toString()));
			Usernumjf usermonth = JSON.parseObject(usermonthJSON, Usernumjf.class);
			
			String useryearJSON = ticketService.getJifenByUser(prdlpr.getRzti()
					.substring(0, 4) + "-01-01",
					prdlpr.getRzti().substring(0, 4) + "-12-31", usid,
					Long.parseLong("2"), Long.parseLong(obj.toString()));
			Usernumjf useryear = JSON.parseObject(useryearJSON, Usernumjf.class);
			
			
			Numjifenset set = ticketService.getNumjifenset(obj.toString());
			// 积分产品数量
			long yearjf = 0;
			long monthjf = 0;
			for (int j = 0; j < plist.size(); j++) {
				OrderPojo ticket = plist.get(j);
				if (!obj.toString().equals(ticket.getIscenicid())) {// 如果不是此服务商的直接跳出循环,因为此集合时排过序
					break;
				} else {
					List<OrderPojo> pricelist = ticket.getPrice();
					if (pricelist != null && pricelist.size() > 0
							&& ticket.getIscenicid().equals(obj.toString())) {
						for (OrderPojo price : pricelist) {
							Numjifensetlist detail = ticketService.getSalesRule(
									Long.parseLong(ticket.getItickettypeid()),
									set.getNid(),
									Long.parseLong(price.getIcrowdkindid()),
									custom.getIbusinessid(), prdlpr.getRzti());
							if (price.getNumb() != null
									&& !"".equals(price.getNumb())
									&& Integer.parseInt(price.getNumb()) > 0) {
								double num = Double
										.parseDouble(price.getNumb());
								if (detail.getXffs().equals("03")) {
									if (num % detail.getIint3().intValue() > 0) {
										monthjf += num
												/ detail.getIint3().intValue()
												* detail.getIint4()
												+ detail.getIint4();
									} else {
										monthjf += num
												/ detail.getIint3().intValue()
												* detail.getIint4();
									}
								} else {
									if (num % detail.getIint3().intValue() > 0) {
										yearjf += num
												/ detail.getIint3().intValue()
												* detail.getIint4()
												+ detail.getIint4();
									} else {
										yearjf += num
												/ detail.getIint3().intValue()
												* detail.getIint4();
									}
								}
							}
						}
					}
					plist.remove(ticket);
					j--;
				}
			}
			// 验证月积分
			if (monthjf > 0) {
				if (usermonth == null) {
					Map errmap = new HashMap();
					errmap.put("providername", provider.getSzscenicname());
					errmap.put("expense", monthjf);
					errmap.put("surplus", 0);
					errmap.put("type", 1);// 月积分
					errlist.add(errmap);
				} else {
					if (usermonth.getPoint() - usermonth.getYpoint() < monthjf) {
						Map errmap = new HashMap();
						errmap.put("providername", provider.getSzscenicname());
						errmap.put("expense", monthjf);
						errmap.put("surplus",
								usermonth.getPoint() - usermonth.getYpoint());
						errmap.put("type", 1);// 月积分
						errlist.add(errmap);

					}
				}
			}
			// 验证年积分
			if (yearjf > 0) {
				if (useryear == null) {
					Map errmap = new HashMap();
					errmap.put("providername", provider.getSzscenicname());
					errmap.put("expense", yearjf);
					errmap.put("surplus", 0);
					errmap.put("type", 2);// 月积分
					errlist.add(errmap);
				} else {
					if (useryear.getPoint() - useryear.getYpoint() < yearjf) {
						Map errmap = new HashMap();
						errmap.put("providername", provider.getSzscenicname());
						errmap.put("expense", yearjf);
						errmap.put("surplus",
								useryear.getPoint() - useryear.getYpoint());
						errmap.put("type", 2);// 月积分
						errlist.add(errmap);
					}
				}
			}
		}
		map.put("errmsg", errlist);
		return map;
	}

	public HashSet splitProvider(List<OrderPojo> list) {
		HashSet set = new HashSet();
		for (int i = 0; i < list.size(); i++) {
			OrderPojo orderpojo = list.get(i);
			set.add(orderpojo.getIscenicid());
		}
		return set;
	}

	public void removeEmptyProduct(List<OrderPojo> list) {
		for (int i = 0; i < list.size(); i++) {
			OrderPojo ticket = list.get(i);
			for (int j = 0; j < ticket.getPrice().size(); j++) {
				OrderPojo price = ticket.getPrice().get(j);
				if (price.getNumb() == null || price.getNumb().equals("")) {
					ticket.getPrice().remove(j);
					j -= 1;
				}
			}
			if (ticket.getPrice() == null || ticket.getPrice().size() < 1) {
				list.remove(i);
				i -= 1;
			}
		}
	}

	/**
	 * 
	 * Describe:把从页面上获取的数据拆分成套票和基础票
	 * 
	 * @auth:yangguang
	 * @param list
	 * @return return:List Date:2011-10-31
	 */
	public Map splitAllTicketList(List<OrderPojo> list) {
		Map map = new HashMap();
		try {
			// 这里可以使用mergeSameTicket方法 把所有产品、日期、趟次的合并
			List<OrderPojo> baseticket = new ArrayList<OrderPojo>();// 基础票
			List<OrderPojo> nestTicket = new ArrayList<OrderPojo>();// 套票
			List<OrderPojo> nestTicket1 = new ArrayList<OrderPojo>();// 合并过同产品、日期的套票
			List<OrderPojo> nestTicket2 = new ArrayList<OrderPojo>();// 合并过同产品、日期、同趟次的套票
			TicketComparator compare = new TicketComparator();
			Edmtickettypetab mk = null;
			for (int i = 0; i < list.size(); i++) {// 最初从页面获取的票信息
				OrderPojo ticket = list.get(i);
				Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
						Esbscenicareatab.class,
						Long.parseLong(ticket.getIscenicid()));
				if (provider.getIparentid() != null
						&& provider.getIparentid().intValue() != 0) {
					ticket.setIparentScenicid(provider.getIparentid()
							.toString());
				}
				mk = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class,
						new Long(ticket.getItickettypeid()));// 根据编号获取产品对象
				if (!mk.getBycategorytype().equals("0010")) {// 非套票
					if (baseticket.size() < 1) {// 要比较的集合 如果为空则先加一条
						OrderPojo ticket1 = new OrderPojo();
						BeanUtils.copyProperties(ticket1, ticket);
						ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
						for (OrderPojo price : ticket.getPrice()) {
							OrderPojo copyprice = new OrderPojo();
							BeanUtils.copyProperties(copyprice, price);
							pricelist.add(copyprice);
						}
						ticket1.setPrice(pricelist);
						baseticket.add(ticket1);
					} else {// 集合里面有值
						for (int k = 0; k < baseticket.size(); k++) {// 循环要比较的对象
							OrderPojo t = baseticket.get(k);
							if (t.getItickettypeid().equals(
									ticket.getItickettypeid())) {// 相同的票
								for (int x = 0; x < ticket.getPrice().size(); x++) {// 因为产品相同
									OrderPojo price1 = ticket.getPrice().get(x);
									for (int b = 0; b < t.getPrice().size(); b++) {// 循环不同的票价
																					// 和
																					// 所对应的数量
										OrderPojo sprice = t.getPrice().get(b);
										// 所以需要嵌套循环拿到同类价格的数量
										if (price1.getIcrowdkindpriceid()
												.equals(// 相同价格
												sprice.getIcrowdkindpriceid())) {
											// 数量叠加
											sprice.setNumb(String.valueOf(Integer
													.parseInt(sprice.getNumb())
													+ Integer.parseInt(price1
															.getNumb())));
											break;
										} else {
											if (b == t.getPrice().size() - 1) {
												OrderPojo price2 = new OrderPojo();
												BeanUtils.copyProperties(
														price2, price1);
												t.getPrice().add(price2);
												break;
											}
										}
									}
								}
								break;
							} else {
								if (k == baseticket.size() - 1) {
									OrderPojo ticket1 = new OrderPojo();
									BeanUtils.copyProperties(ticket1, ticket);
									ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
									for (OrderPojo price : ticket.getPrice()) {
										OrderPojo copyprice = new OrderPojo();
										BeanUtils.copyProperties(copyprice,
												price);
										pricelist.add(copyprice);
									}
									ticket1.setPrice(pricelist);
									baseticket.add(ticket1);
									break;
								}
							}
						}
					}
				} else {
					// 合并同产品同日期的套票
					if (nestTicket1 == null || nestTicket1.size() < 1) {
						if (ticket.getPrice() != null
								&& ticket.getPrice().size() > 0) {
							OrderPojo ticket1 = new OrderPojo();
							BeanUtils.copyProperties(ticket1, ticket);
							ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
							for (OrderPojo price : ticket.getPrice()) {
								OrderPojo copyprice = new OrderPojo();
								BeanUtils.copyProperties(copyprice, price);
								pricelist.add(copyprice);
							}
							if (ticket.getSonTicket() != null
									&& ticket.getSonTicket().size() > 0) {
								ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
								for (OrderPojo sonticket : ticket
										.getSonTicket()) {
									OrderPojo copyson = new OrderPojo();
									BeanUtils
											.copyProperties(copyson, sonticket);
									sonlist.add(copyson);
								}
								ticket1.setSonTicket(sonlist);
							}
							ticket1.setPrice(pricelist);
							nestTicket1.add(ticket1);// 为了合并相同的票
														// 这个集合为空时需要添加第一条作为对比
														// 合并过同产品、日期的套票
						}
					} else {
						for (int x = 0; x < nestTicket1.size(); x++) {// 合并了同日起的票
							OrderPojo t = nestTicket1.get(x);
							if (t.getItickettypeid().equals(
									ticket.getItickettypeid())) {// 同产品
								if (ticket.getSonTicket() != null
										&& ticket.getSonTicket().size() > 0
										&& t.getSonTicket() != null
										&& t.getSonTicket().size() > 0) {
									Collections.sort(ticket.getSonTicket(),
											compare);
									// 对sonticket集合进行排序 (对比的)
									Collections.sort(t.getSonTicket(), compare);
									if (compare.SonticketDaysEquals(
											t.getSonTicket(),
											ticket.getSonTicket())) {// 比较是否同产品同日起
										// 因为是按照日期合并的只需要关心数量不需要关心价格类型 所以直接数量相加
										// totalnum是从页面上使用js算出的作为隐藏域传递的
										t.setTotalnum(String.valueOf(Integer
												.parseInt(t.getTotalnum())
												+ Integer.parseInt(ticket
														.getTotalnum())));
										break;
									} else {
										if (x == nestTicket1.size() - 1) {
											OrderPojo ticket1 = new OrderPojo();
											BeanUtils.copyProperties(ticket1,
													ticket);
											ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
											for (OrderPojo price : ticket
													.getPrice()) {
												OrderPojo copyprice = new OrderPojo();
												BeanUtils.copyProperties(
														copyprice, price);
												pricelist.add(copyprice);
											}
											if (ticket.getSonTicket() != null
													&& ticket.getSonTicket()
															.size() > 0) {
												ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
												for (OrderPojo sonticket : ticket
														.getSonTicket()) {
													OrderPojo copyson = new OrderPojo();
													BeanUtils.copyProperties(
															copyson, sonticket);
													sonlist.add(copyson);
												}
												ticket1.setSonTicket(sonlist);
											}
											ticket1.setPrice(pricelist);
											nestTicket1.add(ticket1);
											break;
										}
									}
								} else {
									for (int m = 0; m < ticket.getPrice()
											.size(); m++) {
										OrderPojo price2 = ticket.getPrice()
												.get(m);
										for (int b = 0; b < t.getPrice().size(); b++) {
											OrderPojo sprice = t.getPrice()
													.get(b);
											if (price2
													.getIcrowdkindpriceid()
													.equals(sprice
															.getIcrowdkindpriceid())) {
												sprice.setNumb(String.valueOf(Integer
														.parseInt(sprice
																.getNumb())
														+ Integer
																.parseInt(price2
																		.getNumb())));
												break;
											} else {
												if (b == t.getPrice().size() - 1) {
													OrderPojo price = new OrderPojo();
													BeanUtils.copyProperties(
															price, price2);
													t.getPrice().add(price);// 合并过同产品、日期的票,如果有趟次也合并
													break;
												}
											}
										}
									}
									break;
								}
							} else {
								if (x == nestTicket1.size() - 1) {
									OrderPojo ticket1 = new OrderPojo();
									BeanUtils.copyProperties(ticket1, ticket);
									ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
									for (OrderPojo price : ticket.getPrice()) {
										OrderPojo copyprice = new OrderPojo();
										BeanUtils.copyProperties(copyprice,
												price);
										pricelist.add(copyprice);
									}
									if (ticket.getSonTicket() != null
											&& ticket.getSonTicket().size() > 0) {
										ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
										for (OrderPojo sonticket : ticket
												.getSonTicket()) {
											OrderPojo copyson = new OrderPojo();
											BeanUtils.copyProperties(copyson,
													sonticket);
											sonlist.add(copyson);
										}
										ticket1.setSonTicket(sonlist);
									}
									ticket1.setPrice(pricelist);
									nestTicket1.add(ticket1);
									break;
								}
							}
						}
					}
					if (nestTicket2 == null || nestTicket2.size() < 1) {
						OrderPojo ticket1 = new OrderPojo();
						BeanUtils.copyProperties(ticket1, ticket);
						ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
						for (OrderPojo price : ticket.getPrice()) {
							OrderPojo copyprice = new OrderPojo();
							BeanUtils.copyProperties(copyprice, price);
							pricelist.add(copyprice);
						}
						if (ticket.getSonTicket() != null
								&& ticket.getSonTicket().size() > 0) {
							ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
							for (OrderPojo sonticket : ticket.getSonTicket()) {
								OrderPojo copyson = new OrderPojo();
								BeanUtils.copyProperties(copyson, sonticket);
								sonlist.add(copyson);
							}
							ticket1.setSonTicket(sonlist);
						}
						ticket1.setPrice(pricelist);
						nestTicket2.add(ticket1);// 合并过同产品、日期、同趟次的套票 没有趟次的也合并在里面
					} else {
						// TODO 合并同产品同日期 同趟次的套票
						for (int n = 0; n < nestTicket2.size(); n++) {
							OrderPojo nestsonticket = nestTicket2.get(n);
							if (nestsonticket.getItickettypeid().equals(
									ticket.getItickettypeid())) {
								if (nestsonticket.getSonTicket() != null
										&& nestsonticket.getSonTicket().size() > 0
										&& ticket.getSonTicket() != null
										&& ticket.getSonTicket().size() > 0) {// 如果子产品不为空
									// 对sonticket集合进行排序 查询的票(从页面获取的)
									Collections.sort(ticket.getSonTicket(),
											compare);
									// 对sonticket集合进行排序 (对比的)
									Collections.sort(
											nestsonticket.getSonTicket(),
											compare);
									if (compare.SonticketEquals(
											nestsonticket.getSonTicket(),
											ticket.getSonTicket())) {// 如果子产品列表相等
										// 说明日期趟次都相等只需要相加数量
										for (int b = 0; b < nestsonticket
												.getPrice().size(); b++) {
											OrderPojo sprice = nestsonticket
													.getPrice().get(b);
											for (int m = 0; m < ticket
													.getPrice().size(); m++) {
												OrderPojo price2 = ticket
														.getPrice().get(m);
												if (price2
														.getIcrowdkindpriceid()
														.equals(sprice
																.getIcrowdkindpriceid())) {
													sprice.setNumb(String.valueOf(Integer
															.parseInt(sprice
																	.getNumb())
															+ Integer
																	.parseInt(price2
																			.getNumb())));
													break;
												} else {
													if (n == nestsonticket
															.getPrice().size() - 1) {
														OrderPojo price = new OrderPojo();
														BeanUtils
																.copyProperties(
																		price,
																		price2);
														nestsonticket
																.getPrice()
																.add(price);// 合并过同产品、日期的票,如果有趟次也合并
														break;
													}
												}
											}
										}
										break;
									} else {
										if (n == nestTicket2.size() - 1) {
											OrderPojo ticket1 = new OrderPojo();
											BeanUtils.copyProperties(ticket1,
													ticket);
											ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
											for (OrderPojo price : ticket
													.getPrice()) {
												OrderPojo copyprice = new OrderPojo();
												BeanUtils.copyProperties(
														copyprice, price);
												pricelist.add(copyprice);
											}
											if (ticket.getSonTicket() != null
													&& ticket.getSonTicket()
															.size() > 0) {
												ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
												for (OrderPojo sonticket : ticket
														.getSonTicket()) {
													OrderPojo copyson = new OrderPojo();
													BeanUtils.copyProperties(
															copyson, sonticket);
													sonlist.add(copyson);
												}
												ticket1.setSonTicket(sonlist);
											}
											nestTicket2.add(ticket1);// 合并过同产品、日期的票,如果有趟次也合并
											break;
										}
									}
								} else {
									// 没有趟次的值需要判断票,价格是否一致,一致则合并
									for (int m = 0; m < ticket.getPrice()
											.size(); m++) {
										OrderPojo price2 = ticket.getPrice()
												.get(m);
										for (int b = 0; b < nestsonticket
												.getPrice().size(); b++) {
											OrderPojo sprice = nestsonticket
													.getPrice().get(b);
											if (price2
													.getIcrowdkindpriceid()
													.equals(sprice
															.getIcrowdkindpriceid())) {
												sprice.setNumb(String.valueOf(Integer
														.parseInt(sprice
																.getNumb())
														+ Integer
																.parseInt(price2
																		.getNumb())));
												break;
											} else {
												if (b == nestsonticket
														.getPrice().size() - 1) {
													OrderPojo price = new OrderPojo();
													BeanUtils.copyProperties(
															price, price2);
													nestsonticket.getPrice()
															.add(price);// 合并过同产品、日期的票,如果有趟次也合并
													break;
												}
											}
										}
									}
									break;
								}
							} else {
								if (n == nestTicket2.size() - 1) {
									OrderPojo ticket1 = new OrderPojo();
									BeanUtils.copyProperties(ticket1, ticket);
									ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
									for (OrderPojo price : ticket.getPrice()) {
										OrderPojo copyprice = new OrderPojo();
										BeanUtils.copyProperties(copyprice,
												price);
										pricelist.add(copyprice);
									}
									if (ticket.getSonTicket() != null
											&& ticket.getSonTicket().size() > 0) {
										ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
										for (OrderPojo sonticket : ticket
												.getSonTicket()) {
											OrderPojo copyson = new OrderPojo();
											BeanUtils.copyProperties(copyson,
													sonticket);
											sonlist.add(copyson);
										}
										ticket1.setSonTicket(sonlist);
									}
									nestTicket2.add(ticket1);// 合并过同产品、日期的票,如果有趟次也合并
									break;
								}
							}
						}
					}
					OrderPojo ticket1 = new OrderPojo();
					BeanUtils.copyProperties(ticket1, ticket);
					ArrayList<OrderPojo> pricelist = new ArrayList<OrderPojo>();
					for (OrderPojo price : ticket.getPrice()) {
						OrderPojo copyprice = new OrderPojo();
						BeanUtils.copyProperties(copyprice, price);
						pricelist.add(copyprice);
					}
					if (ticket.getSonTicket() != null
							&& ticket.getSonTicket().size() > 0) {
						ArrayList<OrderPojo> sonlist = new ArrayList<OrderPojo>();
						for (OrderPojo sonticket : ticket.getSonTicket()) {
							OrderPojo copyson = new OrderPojo();
							BeanUtils.copyProperties(copyson, sonticket);
							sonlist.add(copyson);
						}
						ticket1.setSonTicket(sonlist);
					}
					ticket1.setPrice(pricelist);
					nestTicket.add(ticket1);
				}
			}
			map.put("baseticket", baseticket);// 基础票
			map.put("nestticket", nestTicket);// 所有套票
			map.put("mergekicket", nestTicket1);// 合并的套票不含趟次的
			map.put("samenestticket", nestTicket2);// 合并的套票含趟次的
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("====>>>>预定合并产品异常" + e.getMessage());
		}
		return map;
	}

	// 获取基础票中有 趟次的票
	public List<OrderPojo> getTripTicketList(List<OrderPojo> baseTicketList) {
		Edmtickettypetab mk = null;
		List<OrderPojo> tripTicketList = new ArrayList<OrderPojo>();
		for (OrderPojo ticket : baseTicketList) {
			mk = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class,
					new Long(ticket.getItickettypeid()));
			if (mk.getIssale() == 1) {
				tripTicketList.add(ticket);
			}
		}
		return tripTicketList;
	}

	// 获取套票中有趟次的子票 合并到基础票中有趟次的票
	public List<OrderPojo> getNestTicketTripTicketList(
			List<OrderPojo> nestticketlist, List<OrderPojo> tripticketlist) {
		List<OrderPojo> alltriptickets = new ArrayList<OrderPojo>();// 所有基础票(包含套票的子票)
		List<OrderPojo> sontickets = null;
		Edmtickettypetab ticket = null;
		for (OrderPojo nestticket : nestticketlist) {// 套票列表
			sontickets = nestticket.getSonTicket();
			if (sontickets != null && sontickets.size() > 0) {// 如果有子票说明含有竹筏
				for (OrderPojo sonticket : sontickets) {// 子票列表
					ticket = (Edmtickettypetab) ticketDao.get(
							Edmtickettypetab.class,
							new Long(sonticket.getTicketid()));
					sonticket.setItickettypeid(sonticket.getTicketid()
							.toString());// 为了方便程序
											// 这里重复设置下
					if (ticket.getIssale() == 1) {// 受限制 这里其实判断的是竹筏 也可根据票的种类来判断
													// 0003
						if (tripticketlist != null && tripticketlist.size() > 0) {
							for (OrderPojo basetripticket : tripticketlist) {// 基础票含有趟次列表
								if (basetripticket.getItickettypeid().equals(
										sonticket.getTicketid().toString())
										&& basetripticket.getProductcontrolid()
												.equals(sonticket
														.getProductcontrolid())
										&& basetripticket.getTourdate().equals(
												sonticket.getTourdate())) {// 同日起同趟次同产品则数量想家
									basetripticket
											.setTotalnum(String.valueOf(Integer
													.parseInt(basetripticket
															.getTotalnum())
													+ Integer
															.parseInt(nestticket
																	.getTotalnum())));

								} else {// 不同则加入
									sonticket.setTotalnum(nestticket
											.getTotalnum());
									alltriptickets.add(sonticket);
								}
							}
						} else {
							tripticketlist = new ArrayList<OrderPojo>();
							sonticket.setTotalnum(nestticket.getTotalnum());
							tripticketlist.add(sonticket);
						}

					} else {
						sonticket.setTotalnum(nestticket.getTotalnum());
						alltriptickets.add(sonticket);
					}
				}
			}
		}
		alltriptickets.addAll(tripticketlist);
		return alltriptickets;
	}

	/**
	 * 
	 * Describe:合并相同的票
	 * 
	 * @auth:yangguang
	 * @param ticketlist
	 * @return return:List<OrderPojo> Date:2011-11-1
	 */
	public List<OrderPojo> mergeSameTicket(List<OrderPojo> ticketlist) {
		List<OrderPojo> tickets = new ArrayList<OrderPojo>();// 合并之后的集合
		TicketComparator compare = new TicketComparator();
		// boolean ishavenumber = false;
		for (int i = 0; i < ticketlist.size(); i++) {
			OrderPojo ticket = ticketlist.get(i);// 原始的
			if (Integer.parseInt(ticket.getTotalnum()) > 0) {
				if (tickets == null || tickets.size() < 1) {
					tickets.add(ticket);
				} else {
					for (int j = 0; j < tickets.size(); j++) {// 合并的集合
						OrderPojo ticket1 = tickets.get(j);// 要查找的相同的
						// 如果没有数量直接删除
						if (ticket1.getItickettypeid().equals(
								ticket.getItickettypeid())) {// 如果有
							if (ticket.getSonTicket() == null) {
								tickets.add(ticket);
							} else {
								for (int a = 0; a < ticket1.getPrice().size(); a++) {// 数量
									OrderPojo price = ticket1.getPrice().get(a);
									for (int b = 0; b < ticket.getPrice()
											.size(); b++) {// 原来的数量
										OrderPojo price1 = ticket.getPrice()
												.get(b);
										if (ticket1.getSonTicket() != null
												&& ticket1.getSonTicket()
														.size() > 0) {// 套票
											Collections.sort(
													ticket.getSonTicket(),
													compare);
											if (ticket1.getSonTicket().equals(
													ticket.getSonTicket())) {

											}
											price.setNumb(price.getNumb()
													+ price1.getNumb());
											// 合并数量
											ticket1.getPrice().set(a, price);
											tickets.set(j, ticket1);
										} else if (ticket1.getItickettypeid()
												.equals(ticket
														.getItickettypeid())) {// 基础票
											if (ticket.getTourdate() != null
													&& !ticket.getTourdate()
															.equals("")
													&& ticket
															.getProductcontrolid()
															.equals(ticket1
																	.getProductcontrolid())) {
												price.setNumb(price.getNumb()
														+ price1.getNumb());
												// 合并数量
												ticket1.getPrice()
														.set(a, price);
												tickets.set(j, ticket1);
											}
										} else {
											tickets.add(ticket);
										}
									}
								}

							}
						} else {
							tickets.add(ticket);
						}
					}
				}
			} else {
				ticketlist.remove(ticket);
				i -= 1;
			}
		}
		return tickets;
	}

	/**
	 * 
	 * Describe:封装订单
	 * 
	 * @auth:yangguang
	 * @param tickets
	 *            合并了同产品、同日起、同趟次
	 * @return return:List Date:2011-11-2
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public Map combinationOrder(Custom custom, List<OrderPojo> tickets,
			String firstdate, String dyid, String password, String note,
			String mobile, String orhm, String dyname, String szregionalid,
			int isjl) throws IllegalAccessException, InvocationTargetException,
			SQLException {
		Map ordermap = new HashMap();
		MOrder morder = new MOrder();
		List<TZorderlist> tzorderlist = new ArrayList<TZorderlist>();
		List<TOrder> torders = new ArrayList<TOrder>();
		List<TOrderlist> torderlist = new ArrayList<TOrderlist>();
		List<YOrder> yorders = new ArrayList<YOrder>();
		HashSet groups = new HashSet();
		List<YOrderlist> yorderlist = new ArrayList<YOrderlist>();
		String ddzt = "00";
		double totalmont = 0.0;
		if (note != null && note.equals("1")) {// 申请单 未支付 订单状态为24
			ddzt = "24";
		}
		morder.setStdt(firstdate);
		morder.setOrtp("01");// 预订单
		morder.setUsid(custom.getUsid());
		morder.setOrda(Tools.getDays());
		morder.setOrti(Tools.getNowTime());
		morder.setIsjl(new Long(isjl));// 非奖励
		morder.setDdzt(ddzt);
		morder.setIsa(0l);
		morder.setIsb(0l);
		morder.setIsc(0l);
		morder.setIsd(0l);
		morder.setIse(0l);
		morder.setIsf(0l);
		morder.setIsg(0l);
		morder.setIsh(0l);
		morder.setIsi(0l);
		morder.setIsj(0l);
		morder.setNotea(ddzt);
		for (int x = 0; x < tickets.size(); x++) {// 根据服务商分组
			OrderPojo tic = tickets.get(x);
			groups.add(tic.getIscenicid());
		}
		Custom daoyou = null;
		if (dyid != null && !dyid.equals("")) {
			daoyou = (Custom) ticketDao.get(Custom.class, dyid);
		}
		// 处理分组Iterator ite = myLeafSet.iterator(); ite.hasNext();
		Iterator ite = groups.iterator();
		while (ite.hasNext()) {
			Object obj = ite.next();
			System.out.println(obj.toString());
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, new Long(obj.toString()));
			TOrder torder = new TOrder();
			torder.setIregionalid(new Long(szregionalid));
			torder.setId(new TOrderId(null, provider.getIscenicid()));
			torder.setDdzt(morder.getDdzt());
			torder.setUsid(custom.getUsid());
			torder.setOrfl("02");
			torder.setIbusinessid(custom.getIbusinessid());
			torder.setSzscenicname(provider.getSzscenicname());
			torder.setScenictype(provider.getScenictype());
			torder.setDtstartdate(firstdate);
			torder.setYhamnt(0.0);
			torder.setDtenddate(firstdate);
			torder.setIsa(0l);
			torder.setIsb(0l);
			torder.setIsc(0l);
			torder.setIsjfjf(new Long(isjl));
			torder.setIsd(0l);
			torder.setIse(0l);
			torder.setIsf(0l);
			torder.setIsg(0l);
			torder.setIsh(0l);
			torder.setIsi(0l);
			torder.setIsj(0l);
			if (dyid != null && !dyid.equals("")) {
				torder.setDyusid(daoyou.getUsid());
				torder.setOrnm(daoyou.getLname());
				torder.setOrzj("01");
				torder.setOrhm(daoyou.getZjhm());
				torder.setOrph(daoyou.getMobile());
			} else {
				torder.setOrnm(dyname);
				torder.setNotea(password);
				torder.setOrzj("01");
				torder.setOrhm(orhm);
				torder.setOrph(mobile);
			}
			double tordermont = 0.0;
			int ii = 1;
			int zid = 1;
			for (OrderPojo ticket : tickets) {// 循环合并过的票 这里是合并了同产品、同日起的票
				Edmtickettypetab tt = (Edmtickettypetab) ticketDao.get(
						Edmtickettypetab.class,
						new Long(ticket.getItickettypeid()));
				if (tt.getIscenicid().intValue() == provider.getIscenicid()
						.intValue()) {
					for (OrderPojo price : ticket.getPrice()) {
						double signprice = ticketDao.getTicketPrice(ticket
								.getItickettypeid(), firstdate, price
								.getIcrowdkindpriceid().toString(), custom
								.getIbusinessid().toString());
						double tpric = signprice;
						TOrderlist tlist = new TOrderlist();
						tlist.setId(new TOrderlistId(new Long(ii++), null,
								provider.getIscenicid()));
						tlist.setIscenicid(obj.toString());
						tlist.setItickettypeid(new Long(ticket
								.getItickettypeid()));
						tlist.setIcrowdkindpriceid(new Long(price
								.getIcrowdkindpriceid()));
						tlist.setPric(signprice);
						tlist.setDtstartdate(firstdate);
						tlist.setDtenddate(Tools.getDate(firstdate, Integer
								.parseInt(tt.getValidityday().toString()) - 1));
						tlist.setNumb(new Long(price.getNumb()));
						tlist.setYhamnt(0.0);
						tlist.setYhnumb(new Long(0));
						tlist.setIoffersschemeid(new Long(0));
						tlist.setIcrowdkindid(new Long(price.getIcrowdkindid()));
						tlist.setSztickettypename(tt.getSztickettypename());
						tlist.setAmnt(tpric * tlist.getNumb());
						tordermont += tlist.getAmnt();
						if (tt.getBycategorytype().equals("0010")) {
							tlist.setZorderlist(ticketDao.getNestTicketSplit(
									tlist.getId().getOrderlistid(), tlist
											.getId().getOrid(), new Long(
											provider.getIscenicid()), ticket
											.getSonTicket(), price, ticket
											.getItickettypeid(), firstdate,
									Integer.parseInt(price.getNumb())));
						} else {
							TZorderlist zorderlist = new TZorderlist();
							zorderlist.setId(new TZorderlistId(new Long(zid++),
									tlist.getId().getOrderlistid(), null,
									provider.getIscenicid()));
							zorderlist.setItickettypeid(tt.getItickettypeid());// 主票ID
							zorderlist.setIztickettypeid(tt.getItickettypeid());// 子票ID
							zorderlist.setIcrowdkindpriceid(new Long(price
									.getIcrowdkindpriceid()));// 票价ID
							zorderlist.setIcrowdkindid(new Long(price
									.getIcrowdkindid()));// 人群种类
							if (ticket.getProductcontrolid() != null
									&& !ticket.getProductcontrolid().equals("")) {
								Productcontrol control = (Productcontrol) ticketDao
										.get(Productcontrol.class, new Long(
												ticket.getProductcontrolid()));
								Prdtripvenuemanage trip = ticketDao
										.getTripInfo(control.getTripid(),
												control.getIvenueid(), control
														.getIvenueareaid(),
												control.getStdata(), control
														.getIscenicid()
														.toString(), control
														.getItickettypeid()
														.toString());
								// Prdtripvenuemanage trip =
								// (Prdtripvenuemanage)
								// ticketDao.get(Prdtripvenuemanage.class, new
								// Long(ticket
								// .getProductmanageid()));
								zorderlist.setDtstartdate(firstdate + " "
										+ trip.getStarttime() + ":00");
								zorderlist.setDtenddate(Tools.getDate(
										firstdate,
										Integer.parseInt(tt.getValidityday()
												.toString()) - 1)
										+ " " + trip.getEndtime() + ":00");
								zorderlist.setTripid(control.getTripid());
								zorderlist.setIvenueid(control.getIvenueid());
								zorderlist.setIvenueareaid(control
										.getIvenueareaid());
								zorderlist.setIvenueseatsid(new Long(0));
							} else {
								zorderlist.setDtstartdate(firstdate + " "
										+ "00:00:00");
								zorderlist.setDtenddate(Tools.getDate(
										firstdate,
										Integer.parseInt(tt.getValidityday()
												.toString()) - 1)
										+ " " + "23:59:59");
								zorderlist.setTripid(new Long(0));
								zorderlist.setIvenueid(new Long(0));
								zorderlist.setIvenueareaid(new Long(0));
								zorderlist.setIvenueseatsid(new Long(0));
							}
							zorderlist.setIsa(0l);
							zorderlist.setIsb(0l);
							zorderlist.setIsc(0l);
							zorderlist.setIsd(0l);
							zorderlist.setIse(0l);
							zorderlist.setIsf(0l);
							zorderlist.setIsg(0l);
							zorderlist.setIsh(0l);
							zorderlist.setIsi(0l);
							zorderlist.setIsj(0l);
							zorderlist.setZyhamnt(0.0);
							zorderlist.setZyhnumb(new Long(0));
							zorderlist.setZpric(signprice);
							zorderlist.setZnumb(tlist.getNumb());
							zorderlist.setZamnt(signprice
									* zorderlist.getZnumb());
							List sontickets = new ArrayList();
							sontickets.add(zorderlist);
							tlist.setZorderlist(sontickets);
						}
						tlist.setIsa(0l);
						tlist.setIsb(0l);
						tlist.setIsc(0l);
						tlist.setIsd(0l);
						tlist.setIse(0l);
						tlist.setIsf(0l);
						tlist.setIsg(0l);
						tlist.setIsh(0l);
						tlist.setIsi(0l);
						tlist.setIsj(0l);
						torderlist.add(tlist);
						YOrderlist ylist = new YOrderlist();
						ylist.setId(new YOrderlistId(tlist.getId()
								.getOrderlistid(), morder.getOrid(), provider
								.getIscenicid()));
						ylist.setItickettypeid(tt.getItickettypeid());
						ylist.setIcrowdkindpriceid(new Long(price
								.getIcrowdkindpriceid()));
						ylist.setIcrowdkindid(new Long(price.getIcrowdkindid()));
						ylist.setDtstartdate(tlist.getDtstartdate());
						ylist.setDtenddate(tlist.getDtenddate());
						ylist.setPric(signprice);
						ylist.setNumb(tlist.getNumb());
						ylist.setAmnt(tlist.getAmnt());
						ylist.setYhamnt(0.0);
						ylist.setYhnumb(new Long(0));
						ylist.setIsa(0l);
						ylist.setIsb(0l);
						ylist.setIsc(0l);
						ylist.setIsd(0l);
						ylist.setIse(0l);
						ylist.setIsf(0l);
						ylist.setIsg(0l);
						ylist.setIsh(0l);
						ylist.setIsi(0l);
						ylist.setIsj(0l);
						yorderlist.add(ylist);
					}
				}
			}
			// torder.setSzscenicname(tt.getSztickettypename());// TODO
			torder.setMont(tordermont);
			torder.setZfmont(torder.getMont() - torder.getYhamnt());
			torders.add(torder);
			YOrder yorder = new YOrder();
			yorder.setId(new YOrderId(morder.getOrid(), torder.getId()
					.getIscenicid()));
			yorder.setScenictype(provider.getScenictype());
			yorder.setUsid(custom.getUsid());
			yorder.setDdzt(torder.getDdzt());
			yorder.setIbusinessid(custom.getIbusinessid());
			yorder.setIregionalid(torder.getIregionalid());
			yorder.setTdlx(custom.getTtlb());
			yorder.setDtstartdate(torder.getDtstartdate());
			yorder.setDtenddate(torder.getDtenddate());
			yorder.setMont(tordermont);
			yorder.setYhamnt(0.0);
			yorder.setOrnm(torder.getOrnm());
			yorder.setOrzj(torder.getOrzj());
			if (torder.getDyusid() != null && !torder.getDyusid().equals("")) {
				yorder.setDyusid(torder.getDyusid());
			}
			yorder.setOrhm(torder.getOrhm());
			yorder.setOrph(torder.getOrph());
			yorder.setFaxno(torder.getFaxno());
			yorder.setZfmont(torder.getMont() - torder.getYhamnt());
			yorder.setTpfs("00");
			yorder.setIsj(0l);
			yorders.add(yorder);
			totalmont += torder.getMont();
			if (morder.getYhamnt() == null) {
				morder.setYhamnt(torder.getYhamnt());
			} else {
				morder.setYhamnt(morder.getYhamnt() + torder.getYhamnt());
			}
		}
		morder.setTpfs("00");
		morder.setMont(totalmont);
		morder.setIsallcp(new Long(0));
		morder.setZfmont(totalmont);
		ordermap.put("morder", morder);
		ordermap.put("torder", torders);
		ordermap.put("torderlist", torderlist);
		ordermap.put("yorder", yorders);
		ordermap.put("yorderlist", yorderlist);
		return ordermap;
	}

	public Map combinationOrder(Custom custom, List<OrderPojo> tickets,
			String note, List<LprPojo> lprlist, int isjl)
			throws IllegalAccessException, InvocationTargetException,
			SQLException, ParseException {
		Map ordermap = new HashMap();
		MOrder morder = new MOrder();
		List<TZorderlist> tzorderlist = new ArrayList<TZorderlist>();
		List<TOrder> torders = new ArrayList<TOrder>();
		List<TOrderlist> torderlist = new ArrayList<TOrderlist>();
		List<YOrder> yorders = new ArrayList<YOrder>();
		HashSet groups = new HashSet();
		List<YOrderlist> yorderlist = new ArrayList<YOrderlist>();
		String ddzt = "00";
		double totalmont = 0.0;
		if (note != null && note.equals("1")) {// 申请单 未支付 订单状态为24
			ddzt = "24";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String mindate = Tools.getDays();
		for (LprPojo lpr : lprlist) {
			Date d1 = sdf.parse(lpr.getRzti());
			Date d2 = sdf.parse(mindate);
			if (d1.before(d2)) {
				mindate = lpr.getRzti();
			}
		}
		morder.setStdt(mindate);
		morder.setOrtp("01");// 预订单
		morder.setUsid(custom.getUsid());
		morder.setOrda(Tools.getDays());
		morder.setOrti(Tools.getNowTime());
		morder.setIsjl(new Long(isjl));// 非奖励
		morder.setDdzt(ddzt);
		morder.setIsa(0l);
		morder.setIsb(0l);
		morder.setIsc(0l);
		morder.setIsd(0l);
		morder.setIse(0l);
		morder.setIsf(0l);
		morder.setIsg(0l);
		morder.setIsh(0l);
		morder.setIsi(0l);
		morder.setIsj(0l);
		morder.setNotea(ddzt);
		for (int x = 0; x < tickets.size(); x++) {// 根据服务商分组
			OrderPojo tic = tickets.get(x);
			groups.add(tic.getIscenicid());
		}
		// 处理分组Iterator ite = myLeafSet.iterator(); ite.hasNext();
		Iterator ite = groups.iterator();
		while (ite.hasNext()) {
			LprPojo lprpojo = null;
			Object obj = ite.next();
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, new Long(obj.toString()));
			for (LprPojo lpr : lprlist) {
				if (lpr.getIscenicid().equals(obj.toString())
						|| lpr.getIscenicid().equals(
								provider.getIparentid().toString())) {
					lprpojo = new LprPojo();
					BeanUtils.copyProperties(lprpojo, lpr);
				}
			}

			// 判断服务商优惠
			boolean isProviderScheme = false;// 服务商是否优惠
			boolean isyh = false;// 是否达到服务商优惠条件
			List<Map> priceList = new ArrayList<Map>();// 优惠的价格ID列表
			Edpofferschemetab pscheme = this.ticketDao.checkEdpschemet(
					provider.getIscenicid(), lprpojo.getRzti(),
					custom.getIbusinessid());
			if (pscheme != null) {
				isProviderScheme = true;
				// 获取价格分组
				String groupno = ticketDao.searchJgfz(custom.getUsid(),
						provider.getIscenicid());
				Map map = this.ticketDao.getSchemeConditions(tickets, pscheme,
						provider.getIscenicid().toString(), lprpojo.getRzti(),
						custom.getIbusinessid(), groupno);
				isyh = (Boolean) map.get("isyh");
				priceList = (List<Map>) map.get("priceList");
			}

			TOrder torder = new TOrder();
			torder.setIregionalid(new Long(lprpojo.getSzregionalid()));
			torder.setId(new TOrderId(null, provider.getIscenicid()));
			torder.setDdzt(morder.getDdzt());
			torder.setUsid(custom.getUsid());
			torder.setOrfl("02");
			torder.setIbusinessid(custom.getIbusinessid());
			torder.setSzscenicname(provider.getSzscenicname());
			torder.setScenictype(provider.getScenictype());
			torder.setDtstartdate(lprpojo.getRzti());
			torder.setDtenddate(lprpojo.getRzti());
			torder.setYhamnt(0.0);
			torder.setIsa(0l);
			torder.setIsb(0l);
			torder.setIsc(0l);
			torder.setIsjfjf(new Long(isjl));
			torder.setIsd(0l);
			torder.setIse(0l);
			torder.setIsf(0l);
			torder.setIsg(0l);
			torder.setIsh(0l);
			torder.setIsi(0l);
			torder.setIsj(0l);
			if (lprpojo.getDaoyouid() != null
					&& !lprpojo.getDaoyouid().equals("")) {
				Custom daoyou = (Custom) ticketDao.get(Custom.class,
						lprpojo.getDaoyouid());
				torder.setDyusid(daoyou.getUsid());
				torder.setOrnm(daoyou.getLname());
				torder.setOrzj("01");
				torder.setOrhm(daoyou.getZjhm());
				torder.setOrph(daoyou.getMobile());
			} else {
				torder.setOrnm(lprpojo.getDaoyou());
				torder.setNotea(lprpojo.getPassword());
				torder.setOrzj("01");
				torder.setOrhm(lprpojo.getZjhm());
				torder.setOrph(lprpojo.getMobile());
			}

			String sql = " from Ordercs where byisuse=1 and ibusinessid="
					+ custom.getIbusinessid() + " order by isequence ";
			List lst = ticketDao.find(sql);
			if (lst != null && lst.size() > 0) {
				for (int k = 0; k < lst.size(); k++) {
					Ordercs ordercs = (Ordercs) lst.get(k);
					if (ordercs.getEcs().equals("ornote1")) {
						if (lprpojo.getOrnote1() != null
								&& !lprpojo.getOrnote1().equals("")) {
							torder.setOrnote1(lprpojo.getOrnote1());
						}
					} else if (ordercs.getEcs().equals("ornote2")) {
						if (lprpojo.getOrnote2() != null
								&& !lprpojo.getOrnote2().equals("")) {
							torder.setOrnote2(lprpojo.getOrnote2());
						}
					} else if (ordercs.getEcs().equals("ornote3")) {
						if (lprpojo.getOrnote3() != null
								&& !lprpojo.getOrnote3().equals("")) {
							torder.setOrnote3(lprpojo.getOrnote3());
						}
					} else if (ordercs.getEcs().equals("ornote4")) {
						if (lprpojo.getOrnote4() != null
								&& !lprpojo.getOrnote4().equals("")) {
							torder.setOrnote4(lprpojo.getOrnote4());
						}
					} else if (ordercs.getEcs().equals("ornote5")) {
						if (lprpojo.getOrnote5() != null
								&& !lprpojo.getOrnote5().equals("")) {
							torder.setOrnote5(lprpojo.getOrnote5());
						}
					} else if (ordercs.getEcs().equals("ornote6")) {
						if (lprpojo.getOrnote6() != null
								&& !lprpojo.getOrnote6().equals("")) {
							torder.setOrnote6(lprpojo.getOrnote6());
						}
					} else if (ordercs.getEcs().equals("ornote7")) {
						if (lprpojo.getOrnote7() != null
								&& !lprpojo.getOrnote7().equals("")) {
							torder.setOrnote7(lprpojo.getOrnote7());
						}
					} else if (ordercs.getEcs().equals("ornote8")) {
						if (lprpojo.getOrnote8() != null
								&& !lprpojo.getOrnote8().equals("")) {
							torder.setOrnote8(lprpojo.getOrnote8());
						}
					} else if (ordercs.getEcs().equals("ornote9")) {
						if (lprpojo.getOrnote9() != null
								&& !lprpojo.getOrnote9().equals("")) {
							torder.setOrnote9(lprpojo.getOrnote9());
						}
					} else if (ordercs.getEcs().equals("ornote10")) {
						if (lprpojo.getOrnote10() != null
								&& !lprpojo.getOrnote10().equals("")) {
							torder.setOrnote10(lprpojo.getOrnote10());
						}
					}

				}
			}
			double tordermont = 0.0;
			int ii = 1;
			int zid = 1;
			for (OrderPojo ticket : tickets) {// 循环合并过的票 这里是合并了同产品、同日起的票
				Edmtickettypetab tt = (Edmtickettypetab) ticketDao.get(
						Edmtickettypetab.class,
						new Long(ticket.getItickettypeid()));
				if (tt.getIscenicid().intValue() == provider.getIscenicid()
						.intValue()) {
					for (OrderPojo price : ticket.getPrice()) {
						Edpofferschemetab scheme = ticketDao.getScheme(
								Long.parseLong(obj.toString()),
								custom.getIbusinessid(),
								Long.parseLong(price.getIcrowdkindid()),
								Long.parseLong(ticket.getTicketid()),
								lprpojo.getRzti());
						// 获取价格分组
						String groupno = ticketDao.searchJgfz(custom.getUsid(),
								provider.getIscenicid());

						double signprice = ticketDao.getTicketPrice(ticket
								.getItickettypeid(), lprpojo.getRzti(), price
								.getIcrowdkindid(), custom.getIbusinessid()
								.toString(), groupno);
						double tpric = signprice;
						TOrderlist tlist = new TOrderlist();
						tlist.setId(new TOrderlistId(new Long(ii++), null,
								provider.getIscenicid()));
						tlist.setIscenicid(obj.toString());
						tlist.setItickettypeid(new Long(ticket
								.getItickettypeid()));
						tlist.setIcrowdkindpriceid(new Long(price
								.getIcrowdkindpriceid()));
						tlist.setPric(signprice);
						tlist.setDtstartdate(lprpojo.getRzti());
						tlist.setDtenddate(Tools.getDate(
								lprpojo.getRzti(),
								Integer.parseInt(tt.getValidityday().toString()) - 1));
						tlist.setNumb(new Long(price.getNumb()));
						
						//ADD BY KOKA ON 20170728
						tlist.setStarttime(ticket.getFsstarttime());//分时预约开始时间
						tlist.setEndtime(ticket.getFsendtime());//分时预约结束时间
						tlist.setTimeid(ticket.getFstimeid());//分时预约时间段ID
						// 服务商优惠
						if (isProviderScheme) {
							if (isyh) {
								Edmcrowdkindpricetab eprice = ticketDao
										.getProductPrice(ticket.getTicketid(),
												custom.getIbusinessid()
														.toString(), lprpojo
														.getRzti(), price
														.getIcrowdkindid(),
												groupno);
								boolean flag = false;// 价格优惠标识
								for (int z = 0; z < priceList.size(); z++) {
									Map pmap = priceList.get(z);
									String priceid = pmap.get("priceid")
											.toString();
									long yhnum = Long.parseLong(pmap.get("num")
											.toString());
									if (priceid.equals(eprice
											.getIcrowdkindpriceid().toString())) {
										tlist.setIoffersschemeid(pscheme
												.getIoffersschemeid());
										tlist.setYhnumb(yhnum);
										tlist.setYhamnt(tlist.getYhnumb()
												.intValue() * signprice);
										flag = true;
										break;
									}
								}
								if (!flag) {
									tlist.setYhamnt(0.0);
									tlist.setYhnumb(new Long(0));
								}
							} else {
								tlist.setYhamnt(0.0);
								tlist.setYhnumb(new Long(0));
							}
						} else {
							if (scheme != null) {
								tlist.setIoffersschemeid(scheme
										.getIoffersschemeid());
								tlist.setYhnumb(new Long(Integer.parseInt(price
										.getNumb())
										/ scheme.getImultiples()
										* scheme.getIoffernum()));
								tlist.setYhamnt(tlist.getYhnumb().intValue()
										* signprice);
							} else {
								tlist.setYhamnt(0.0);
								tlist.setYhnumb(new Long(0));
							}
						}
						tlist.setIcrowdkindid(new Long(price.getIcrowdkindid()));
						tlist.setSztickettypename(tt.getSztickettypename());
						tlist.setAmnt(tpric * tlist.getNumb());
						tlist.setSzscenicname(provider.getSzscenicname());// 为添加实名制信息加的--
						tlist.setSzcrowdkindname(price.getSzcrowdkindname());// 为添加实名制信息加的--
						torder.setYhamnt(torder.getYhamnt() + tlist.getYhamnt());// 给torder添加优惠信息
						tordermont += tlist.getAmnt();
						// 判断套票或者基础票
						if (tt.getBycategorytype().equals("0010")) {
							tlist.setZorderlist(ticketDao.getNestTicketSplit(
									tlist.getId().getOrderlistid(), tlist
											.getId().getOrid(), new Long(
											provider.getIscenicid()), ticket
											.getSonTicket(), price, ticket
											.getItickettypeid(), lprpojo
											.getRzti(), Integer.parseInt(price
											.getNumb())));
							for (TZorderlist z : tlist.getZorderlist()) {
								z.setZyhnumb(tlist.getYhnumb());
								z.setZyhamnt(z.getZpric()
										* z.getZyhnumb().intValue());
							}
						} else {
							TZorderlist zorderlist = new TZorderlist();
							zorderlist.setId(new TZorderlistId(new Long(zid++),
									tlist.getId().getOrderlistid(), null,
									provider.getIscenicid()));
							zorderlist.setItickettypeid(tt.getItickettypeid());// 主票ID
							zorderlist.setIztickettypeid(tt.getItickettypeid());// 子票ID
							zorderlist.setIcrowdkindpriceid(new Long(price
									.getIcrowdkindpriceid()));// 票价ID
							zorderlist.setIcrowdkindid(new Long(price
									.getIcrowdkindid()));// 人群种类
							if (ticket.getProductcontrolid() != null
									&& !ticket.getProductcontrolid().equals("")) {
								Productcontrol control = (Productcontrol) ticketDao
										.get(Productcontrol.class, new Long(
												ticket.getProductcontrolid()));
								Prdtripvenuemanage trip = ticketDao
										.getTripInfo(control.getTripid(),
												control.getIvenueid(), control
														.getIvenueareaid(),
												control.getStdata(), control
														.getIscenicid()
														.toString(), control
														.getItickettypeid()
														.toString());
								zorderlist.setDtstartdate(lprpojo.getRzti()
										+ " " + trip.getStarttime() + ":00");
								zorderlist.setDtenddate(Tools.getDate(lprpojo
										.getRzti(), Integer.parseInt(tt
										.getValidityday().toString()) - 1)
										+ " " + trip.getEndtime() + ":00");
								zorderlist.setTripid(control.getTripid());
								zorderlist.setIvenueid(control.getIvenueid());
								zorderlist.setIvenueareaid(control
										.getIvenueareaid());
								zorderlist.setIvenueseatsid(new Long(0));
							} else {
								zorderlist.setDtstartdate(lprpojo.getRzti()
										+ " " + "00:00:00");
								zorderlist.setDtenddate(Tools.getDate(lprpojo
										.getRzti(), Integer.parseInt(tt
										.getValidityday().toString()) - 1)
										+ " " + "23:59:59");
								zorderlist.setTripid(new Long(0));
								zorderlist.setIvenueid(new Long(0));
								zorderlist.setIvenueareaid(new Long(0));
								zorderlist.setIvenueseatsid(new Long(0));
							}
							zorderlist.setIsa(0l);
							zorderlist.setIsb(0l);
							zorderlist.setIsc(0l);
							zorderlist.setIsd(0l);
							zorderlist.setIse(0l);
							zorderlist.setIsf(0l);
							zorderlist.setIsg(0l);
							zorderlist.setIsh(0l);
							zorderlist.setIsi(0l);
							zorderlist.setIsj(0l);
							zorderlist.setZnumb(tlist.getNumb());
							zorderlist.setZamnt(signprice
									* zorderlist.getZnumb());
							zorderlist.setZpric(signprice);
							if (isProviderScheme) {
								if (isyh) {
									zorderlist.setZyhamnt(tlist.getYhamnt());
									zorderlist.setZyhnumb(tlist.getYhnumb());
								} else {
									zorderlist.setZyhamnt(0.0);
									zorderlist.setZyhnumb(new Long(0));
								}
							} else {
								if (scheme != null) {
									zorderlist.setZyhnumb(new Long(zorderlist
											.getZnumb().intValue()
											/ scheme.getImultiples()
											* scheme.getIoffernum()));
									zorderlist.setZyhamnt(zorderlist
											.getZyhnumb().intValue()
											* signprice);
								} else {
									zorderlist.setZyhamnt(0.0);
									zorderlist.setZyhnumb(new Long(0));
								}
							}
							List sontickets = new ArrayList();
							sontickets.add(zorderlist);
							tlist.setZorderlist(sontickets);
						}
						tlist.setIsa(0l);
						tlist.setIsb(0l);
						tlist.setIsc(0l);
						tlist.setIsd(0l);
						tlist.setIse(0l);
						tlist.setIsf(0l);
						tlist.setIsg(0l);
						tlist.setIsh(0l);
						tlist.setIsi(0l);
						tlist.setIsj(0l);
						torderlist.add(tlist);
						YOrderlist ylist = new YOrderlist();
						ylist.setId(new YOrderlistId(tlist.getId()
								.getOrderlistid(), morder.getOrid(), provider
								.getIscenicid()));
						ylist.setItickettypeid(tt.getItickettypeid());
						ylist.setIcrowdkindpriceid(new Long(price
								.getIcrowdkindpriceid()));
						ylist.setIcrowdkindid(new Long(price.getIcrowdkindid()));
						ylist.setDtstartdate(tlist.getDtstartdate());
						ylist.setDtenddate(tlist.getDtenddate());
						ylist.setPric(signprice);
						ylist.setNumb(tlist.getNumb());
						ylist.setAmnt(tlist.getAmnt());
						if (isProviderScheme) {
							if (isyh) {
								ylist.setYhamnt(tlist.getYhamnt());
								ylist.setYhnumb(tlist.getYhnumb());
							} else {
								ylist.setYhamnt(0.0);
								ylist.setYhnumb(new Long(0));
							}
						} else {
							if (scheme != null) {
								ylist.setYhamnt(tlist.getYhamnt());
								ylist.setYhnumb(tlist.getYhnumb());
							} else {
								ylist.setYhamnt(0.0);
								ylist.setYhnumb(new Long(0));
							}
						}
						ylist.setIsa(0l);
						ylist.setIsb(0l);
						ylist.setIsc(0l);
						ylist.setIsd(0l);
						ylist.setIse(0l);
						ylist.setIsf(0l);
						ylist.setIsg(0l);
						ylist.setIsh(0l);
						ylist.setIsi(0l);
						ylist.setIsj(0l);
						
						//add by koka on 20170728
						ylist.setStarttime(ticket.getFsstarttime());//分时预约开始时间
						ylist.setEndtime(ticket.getFsendtime());//分时预约结束时间
						yorderlist.add(ylist);
					}
				}
			}
			// torder.setSzscenicname(tt.getSztickettypename());// TODO
			torder.setMont(tordermont);
			torder.setZfmont(torder.getMont() - torder.getYhamnt());
			torders.add(torder);
			YOrder yorder = new YOrder();
			yorder.setId(new YOrderId(morder.getOrid(), torder.getId()
					.getIscenicid()));
			yorder.setScenictype(provider.getScenictype());
			yorder.setUsid(custom.getUsid());
			yorder.setDdzt(torder.getDdzt());
			yorder.setIbusinessid(custom.getIbusinessid());
			yorder.setIregionalid(torder.getIregionalid());
			yorder.setTdlx(custom.getTtlb());
			yorder.setDtstartdate(torder.getDtstartdate());
			yorder.setDtenddate(torder.getDtenddate());
			yorder.setMont(tordermont);
			yorder.setYhamnt(torder.getYhamnt());
			yorder.setZfmont(yorder.getMont() - yorder.getYhamnt());
			System.out.println("Iscenicid=" + yorder.getId().getIscenicid());
			System.out.println("zfmont=" + yorder.getZfmont());
			yorder.setOrnm(torder.getOrnm());
			yorder.setOrzj(torder.getOrzj());
			if (torder.getDyusid() != null && !torder.getDyusid().equals("")) {
				yorder.setDyusid(torder.getDyusid());
			}
			yorder.setOrhm(torder.getOrhm());
			yorder.setOrph(torder.getOrph());
			yorder.setFaxno(torder.getFaxno());
			yorder.setTpfs("00");
			yorder.setIsj(0l);
			yorders.add(yorder);
			totalmont += torder.getMont();
			if (morder.getYhamnt() == null) {
				morder.setYhamnt(torder.getYhamnt());
			} else {
				morder.setYhamnt(morder.getYhamnt() + torder.getYhamnt());
			}
		}
		morder.setTpfs("00");
		morder.setMont(totalmont);
		morder.setIsallcp(new Long(0));
		morder.setZfmont(totalmont - morder.getYhamnt());
		ordermap.put("morder", morder);
		ordermap.put("torder", torders);
		ordermap.put("torderlist", torderlist);
		ordermap.put("yorder", yorders);
		ordermap.put("yorderlist", yorderlist);
		return ordermap;
	}

	/**
	 * 
	 * Describe:移除数量为空的 封装优惠(免票)信息
	 * 
	 * @auth:yangguang
	 * @param ticketlist
	 * @return return:List<OrderPojo> Date:2011-11-8
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Map removeEmptyTicket(List<OrderPojo> ticketlist)
			throws IllegalAccessException, InvocationTargetException {
		double total = 0.0;
		for (int i = 0; i < ticketlist.size(); i++) {
			OrderPojo ticket = ticketlist.get(i);
			Edmtickettypetab product = (Edmtickettypetab) ticketDao
					.get(Edmtickettypetab.class,
							new Long(ticket.getItickettypeid()));
			ticket.setTicketname(product.getSztickettypename());
			ticket.setIssale(Integer.parseInt(product.getIssale().toString()));
			if (product.getBycategorytype().equals("0010")) {
				if (ticket.getSonTicket() != null
						&& ticket.getSonTicket().size() > 0) {
					for (int x = 0; x < ticket.getSonTicket().size(); x++) {
						OrderPojo son = ticket.getSonTicket().get(x);
						if (son.getProductcontrolid() == null
								|| son.getProductcontrolid().equals("")) {
							continue;
						} else {
							Productcontrol control = (Productcontrol) ticketDao
									.get(Productcontrol.class,
											new Long(son.getProductcontrolid()));
							Venuearea wharf = (Venuearea) ticketDao.get(
									Venuearea.class,
									new Long(control.getIvenueareaid()));
							Prdtripvenuemanage trip = ticketDao.getTripInfo(
									control.getTripid(), control.getIvenueid(),
									control.getIvenueareaid(), control
											.getStdata(), control
											.getIscenicid().toString(), control
											.getItickettypeid().toString());
							Trip mytrip = (Trip) ticketDao.get(Trip.class,
									trip.getTripid());
							son.setIssale(1);
							son.setStarttime(trip.getStarttime());
							son.setTripname(mytrip.getTripname());
							son.setWharf(wharf.getIvenueareaname());
							ticket.getSonTicket().set(x, son);
						}
					}
				}
			} else {
				if (ticket.getProductcontrolid() != null
						&& !ticket.getProductcontrolid().equals("")) {
					Productcontrol control = (Productcontrol) ticketDao.get(
							Productcontrol.class,
							new Long(ticket.getProductcontrolid()));
					Prdtripvenuemanage trip = ticketDao.getTripInfo(control
							.getTripid(), control.getIvenueid(), control
							.getIvenueareaid(), control.getStdata(), control
							.getIscenicid().toString(), control
							.getItickettypeid().toString());
					ticket.setStarttime(trip.getStarttime());
					Trip mytrip = (Trip) ticketDao.get(Trip.class,
							trip.getTripid());
					ticket.setTripname(mytrip.getTripname());
					Venuearea wharf = (Venuearea) ticketDao.get(
							Venuearea.class,
							new Long(control.getIvenueareaid()));
					ticket.setWharf(wharf.getIvenueareaname());
				}
			}
			for (int j = 0; j < ticket.getPrice().size(); j++) {
				OrderPojo price = ticket.getPrice().get(j);
				if (price.getNumb() != null && !price.getNumb().equals("")) {
					Edmcrowdkindpricetab eprice = ticketDao.getPrice(new Long(
							price.getIcrowdkindpriceid()));
					price.setMactualsaleprice(eprice.getMactualsaleprice());
					if (ticket.getSmall() != null
							&& !ticket.getSmall().equals("")) {
						ticket.setSmall(String.valueOf(Double
								.parseDouble(ticket.getSmall())
								+ (eprice.getMactualsaleprice().doubleValue() * Integer
										.parseInt(price.getNumb()))));
					} else {
						ticket.setSmall(String.valueOf(eprice
								.getMactualsaleprice()
								* Integer.parseInt(price.getNumb())));
					}
					price.setSzcrowdkindname(eprice.getStrcrowdkind());

					ticket.getPrice().set(j, price);
				} else {
					ticket.getPrice().remove(price);
					j -= 1;
				}
			}
			total += Double.parseDouble(ticket.getSmall());
			ticketlist.set(i, ticket);
		}
		Map map = new HashMap();
		map.put("list", ticketlist);
		map.put("total", total);
		return map;
	}

	public Map packagingScheme(List<OrderPojo> ticketlist, long ibusinessid,
			String usid, List<LprPojo> lprlist) throws IllegalAccessException,
			InvocationTargetException {
		double total = 0.0;
		for (int a = 0; a < lprlist.size(); a++) {
			// 获取领票人信息,其实就是为了取到游览日期
			LprPojo lpr = lprlist.get(a);
			String startdate = lpr.getRzti();

			// 判断服务商优惠
			boolean isProviderScheme = false;// 服务商是否优惠
			boolean isyh = false;// 是否达到服务商优惠条件
			List<Map> priceList = new ArrayList<Map>();// 优惠的价格ID列表
			Edpofferschemetab pscheme = this.ticketDao.checkEdpschemet(
					Long.parseLong(lpr.getIscenicid()), startdate, ibusinessid);

			// 获取某服务商下的价格分组
			String groupno = ticketDao.searchJgfz(usid,
					Long.parseLong(lpr.getIscenicid()));
			if (pscheme != null) {
				isProviderScheme = true;
				Map map = this.ticketDao.getSchemeConditions(ticketlist,
						pscheme, lpr.getIscenicid(), startdate, ibusinessid,
						groupno);
				isyh = (Boolean) map.get("isyh");
				priceList = (List<Map>) map.get("priceList");
			}

			for (int i = 0; i < ticketlist.size(); i++) {
				OrderPojo ticket = ticketlist.get(i);
				if (!ticket.getIscenicid().equals(lpr.getIscenicid())) {
					continue;
				}
				Edmtickettypetab product = (Edmtickettypetab) ticketDao.get(
						Edmtickettypetab.class,
						new Long(ticket.getItickettypeid()));
				ticket.setTicketname(product.getSztickettypename());
				ticket.setIssale(Integer.parseInt(product.getIssale()
						.toString()));
				if (product.getBycategorytype().equals("0010")) {
					if (ticket.getSonTicket() != null
							&& ticket.getSonTicket().size() > 0) {
						for (int x = 0; x < ticket.getSonTicket().size(); x++) {
							OrderPojo son = ticket.getSonTicket().get(x);
							if (son.getProductcontrolid() == null
									|| son.getProductcontrolid().equals("")) {
								continue;
							} else {
								Productcontrol control = (Productcontrol) ticketDao
										.get(Productcontrol.class,
												new Long(son
														.getProductcontrolid()));
								Venuearea wharf = (Venuearea) ticketDao.get(
										Venuearea.class,
										new Long(control.getIvenueareaid()));
								Prdtripvenuemanage trip = ticketDao
										.getTripInfo(control.getTripid(),
												control.getIvenueid(), control
														.getIvenueareaid(),
												control.getStdata(), control
														.getIscenicid()
														.toString(), control
														.getItickettypeid()
														.toString());
								Trip mytrip = (Trip) ticketDao.get(Trip.class,
										trip.getTripid());
								son.setIssale(1);
								son.setStarttime(trip.getStarttime());
								son.setTripname(mytrip.getTripname());
								son.setWharf(wharf.getIvenueareaname());
								ticket.getSonTicket().set(x, son);
							}
						}
					}
				} else {
					if (ticket.getProductcontrolid() != null
							&& !ticket.getProductcontrolid().equals("")) {
						Productcontrol control = (Productcontrol) ticketDao
								.get(Productcontrol.class,
										new Long(ticket.getProductcontrolid()));
						Prdtripvenuemanage trip = ticketDao.getTripInfo(control
								.getTripid(), control.getIvenueid(), control
								.getIvenueareaid(), control.getStdata(),
								control.getIscenicid().toString(), control
										.getItickettypeid().toString());
						ticket.setStarttime(trip.getStarttime());
						Trip mytrip = (Trip) ticketDao.get(Trip.class,
								trip.getTripid());
						ticket.setTripname(mytrip.getTripname());
						Venuearea wharf = (Venuearea) ticketDao.get(
								Venuearea.class,
								new Long(control.getIvenueareaid()));
						ticket.setWharf(wharf.getIvenueareaname());
					}
				}
				for (int j = 0; j < ticket.getPrice().size(); j++) {
					OrderPojo price = ticket.getPrice().get(j);
					if (price.getNumb() != null && !price.getNumb().equals("")) {
						Edmcrowdkindpricetab eprice = ticketDao
								.getProductPrice(ticket.getTicketid(),
										String.valueOf(ibusinessid), startdate,
										price.getIcrowdkindid(), groupno);
						eprice = ticketDao.getPrice(eprice
								.getIcrowdkindpriceid());
						price.setMactualsaleprice(eprice.getMactualsaleprice());
						// 获取优惠
						if (isProviderScheme) {// 服务商优惠
							if (isyh) {
								// TODO
								boolean flag = false;// 价格优惠标识
								for (int z = 0; z < priceList.size(); z++) {
									Map pmap = priceList.get(z);
									String priceid = pmap.get("priceid")
											.toString();
									int yhnum = Integer.parseInt(pmap
											.get("num").toString());
									if (priceid.equals(eprice
											.getIcrowdkindpriceid().toString())) {
										if (ticket.getSmall() != null
												&& !ticket.getSmall()
														.equals("")) {
											ticket.setSmall(String.valueOf(Double
													.parseDouble(ticket
															.getSmall())
													+ (eprice
															.getMactualsaleprice()
															.doubleValue() * (Integer
															.parseInt(price
																	.getNumb()) - yhnum))));
										} else {
											ticket.setSmall(String.valueOf(eprice
													.getMactualsaleprice()
													* (Integer.parseInt(price
															.getNumb()) - yhnum)));
										}
										price.setScheme(1);
										price.setSchemetype(pscheme
												.getBycategorytype().toString());
										price.setBasnum(pscheme.getImultiples()
												.intValue());
										price.setScnum(pscheme.getIoffernum()
												.intValue());
										price.setFreenum(yhnum);
										flag = true;
										break;
									}
								}
								if (!flag) {
									if (ticket.getSmall() != null
											&& !ticket.getSmall().equals("")) {
										ticket.setSmall(String.valueOf(Double
												.parseDouble(ticket.getSmall())
												+ (eprice.getMactualsaleprice()
														.doubleValue() * Integer
														.parseInt(price
																.getNumb()))));
									} else {
										ticket.setSmall(String.valueOf(eprice
												.getMactualsaleprice()
												* Integer.parseInt(price
														.getNumb())));
									}
								}
							} else {
								if (ticket.getSmall() != null
										&& !ticket.getSmall().equals("")) {
									ticket.setSmall(String.valueOf(Double
											.parseDouble(ticket.getSmall())
											+ (eprice.getMactualsaleprice()
													.doubleValue() * Integer
													.parseInt(price.getNumb()))));
								} else {
									ticket.setSmall(String.valueOf(eprice
											.getMactualsaleprice()
											* Integer.parseInt(price.getNumb())));
								}
							}
						} else {// 产品优惠
							Edpofferschemetab scheme = ticketDao.getScheme(
									Long.parseLong(ticket.getIscenicid()),
									ibusinessid,
									Long.parseLong(price.getIcrowdkindid()),
									Long.parseLong(ticket.getTicketid()),
									startdate);
							if (scheme != null) {
								int freenum = Integer.parseInt(price.getNumb())
										/ scheme.getImultiples().intValue()
										* scheme.getIoffernum().intValue();
								if (ticket.getSmall() != null
										&& !ticket.getSmall().equals("")) {
									ticket.setSmall(String.valueOf(Double
											.parseDouble(ticket.getSmall())
											+ (eprice.getMactualsaleprice()
													.doubleValue() * (Integer
													.parseInt(price.getNumb()) - freenum))));
								} else {
									ticket.setSmall(String.valueOf(eprice
											.getMactualsaleprice()
											* (Integer.parseInt(price.getNumb()) - freenum)));
								}
								price.setScheme(1);
								price.setSchemetype(scheme.getBycategorytype()
										.toString());
								price.setBasnum(scheme.getImultiples()
										.intValue());
								price.setScnum(scheme.getIoffernum().intValue());
								price.setFreenum(freenum);
							} else {
								if (ticket.getSmall() != null
										&& !ticket.getSmall().equals("")) {
									ticket.setSmall(String.valueOf(Double
											.parseDouble(ticket.getSmall())
											+ (eprice.getMactualsaleprice()
													.doubleValue() * Integer
													.parseInt(price.getNumb()))));
								} else {
									ticket.setSmall(String.valueOf(eprice
											.getMactualsaleprice()
											* Integer.parseInt(price.getNumb())));
								}
							}
						}

						price.setSzcrowdkindname(eprice.getStrcrowdkind());
						ticket.getPrice().set(j, price);
					} else {
						ticket.getPrice().remove(price);
						j -= 1;
					}
				}
				total += Double.parseDouble(ticket.getSmall());
				ticketlist.set(i, ticket);
			}
		}
		Map map = new HashMap();
		map.put("list", ticketlist);
		map.put("total", total);
		return map;

	}

	public String getFsusid(String usid) throws Exception {
		Custom custom = null;
		Custom pcustom = null;
		String returnusid = "";
		while (true) {
			custom = (Custom) ticketDao.get(Custom.class, usid);
			if (custom.getSusid() == null || custom.getSusid().equals("")
					|| custom.getUsid().equals(custom.getSusid())) {
				throw new Exception("数据异常,请检查用户[" + usid + "]的信息是否正确");
			} else {
				pcustom = (Custom) ticketDao.get(Custom.class,
						custom.getSusid());
				if (custom.getLgtp().equals("02")
						&& custom.getUstp().equals("02")
						&& pcustom.getUstp().equals("01")) {
					returnusid = custom.getUsid();
					break;
				} else {
					usid = pcustom.getUsid();
					continue;
				}
			}
		}
		return returnusid;
	}

	public List getRafttripInfoList(String itickettypeid, String date,
			String zticketid, String usid) throws IllegalAccessException,
			InvocationTargetException {
		return ticketDao.getRafttripInfoList(itickettypeid, date, zticketid,
				usid);
	}

	public void removeNoProductLpr(List<LprPojo> lprs, List<OrderPojo> tickets) {
		HashSet providerset = splitProvider(tickets);
		for (int i = 0; i < lprs.size(); i++) {
			LprPojo lpr = lprs.get(i);
			boolean have = false;
			Iterator ite = providerset.iterator();
			while (ite.hasNext()) {
				Object obj = ite.next();
				LprPojo prdlpr = null;
				Esbscenicareatab provider = (Esbscenicareatab) this
						.get(Esbscenicareatab.class,
								Long.parseLong(obj.toString()));
				if (lpr.getIscenicid().equals(obj.toString())
						|| lpr.getIscenicid().equals(
								provider.getIparentid().toString())) {
					have = true;
					break;
				}
			}
			if (!have) {
				lprs.remove(i);
				i -= 1;
			}
		}

	}

	/***
	 * * Describe:验证领票人信息及服务商首次游览日期
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#validationLpr(java.util.List)
	 * @param lprs
	 *            传入领票人泛型集合
	 * @return 返回验证信息 若长度小于1则表示通过
	 * @author yangguang Date:2012-8-7
	 * @throws ParseException
	 */
	public Map validationLpr(List<LprPojo> lprs, String ibussiness)
			throws ParseException {
		Map errMsg = new HashMap();
		int x = 0;
		for (int i = 0; i < lprs.size(); i++) {
			LprPojo lpr = lprs.get(i);
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, Long.parseLong(lpr.getIscenicid()));

			if (lpr.getRzti() == null || lpr.getRzti().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]首次游览日期不能为空");
				x += 1;
			}
			// 获取服务商信息中设置的可提前预定日期
			String providerDay = Tools.getDate(Tools.getDays(), provider
					.getImaxdata().intValue());
			// 获取服务商当天最晚预定时间
			String providetime = provider.getSzlasttime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 验证首次游览日期是否满足服务商所设置的最大提前预定日期
			String today = Tools.getDays();
			Date d1 = sdf.parse(today);// 当天
			Date d2 = sdf.parse(providerDay);// 可以最大提前预定的日期
			if (!d1.before(d2)) {
				errMsg.put(x, "[" + provider.getSzscenicname()
						+ "]的首次游览日期已超过最大提前预定时间");
				x += 1;
			} else {
				if (lpr.getRzti() != null && !lpr.getRzti().equals("")) {
					SimpleDateFormat sdf1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					StringBuffer endtime = new StringBuffer(lpr.getRzti() + " "
							+ provider.getSzlasttime() + ":00");
					Date d3 = sdf1.parse(endtime.toString());
					Date d4 = sdf1.parse(Tools.getDayTimes());
					if (d4.after(d3)) {
						errMsg.put(x, "已超过[" + provider.getSzscenicname()
								+ "]的当天最晚预定时间");
						x += 1;
					}
				}
			}
			if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]导游名称不能为空");
				x += 1;
			}
			if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]联系方式不能为空");
				x += 1;
			}
			if (lpr.getSzregionalid() == null
					|| lpr.getSzregionalid().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]客源地不能为空");
				x += 1;
			}
			if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]证件号码不能为空");
				x += 1;
			} else {
//				Match match = new Match();
				Pattern p = Pattern.compile("^\\d{17}([0-9]|X|x)$");
				boolean b = p.matcher(lpr.getZjhm()).matches();
				if (!b) {
					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]证件号码格式不对");
				}
			}
			if (lpr.getDaoyouid() == null || lpr.getDaoyouid().equals("")) {
				if (lpr.getPassword() == null || lpr.getPassword().equals("")) {
					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]取票密码不能为空");
					x += 1;
				}
			}
			/**
			 * String
			 * sql=" from Ordercs where byisuse=1 and ibusinessid="+ibussiness
			 * +" order by isequence "; List lst=ticketDao.find(sql);
			 * if(lst!=null&&lst.size()>0){ for(int k=0;k<lst.size();k++){
			 * Ordercs ordercs=(Ordercs)lst.get(k);
			 * if(ordercs.getEcs().equals("ornote1")){
			 * if(lpr.getOrnote1()==null||lpr.getOrnote1().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote2")){
			 * if(lpr.getOrnote2()==null||lpr.getOrnote2().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote3")){
			 * if(lpr.getOrnote3()==null||lpr.getOrnote3().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote4")){
			 * if(lpr.getOrnote4()==null||lpr.getOrnote4().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote5")){
			 * if(lpr.getOrnote5()==null||lpr.getOrnote5().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote6")){
			 * if(lpr.getOrnote6()==null||lpr.getOrnote6().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote7")){
			 * if(lpr.getOrnote7()==null||lpr.getOrnote7().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote8")){
			 * if(lpr.getOrnote8()==null||lpr.getOrnote8().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote9")){
			 * if(lpr.getOrnote9()==null||lpr.getOrnote9().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }else
			 * if(ordercs.getEcs().equals("ornote10")){
			 * if(lpr.getOrnote10()==null||lpr.getOrnote10().equals("")){
			 * errMsg.put(x, "[" + provider.getSzscenicname() +
			 * "]"+ordercs.getZcs()+"不能为空"); x += 1; } }
			 * 
			 * 
			 * } }
			 **/

		}
		return errMsg;
	}

	/***
	 * * Describe:验证领票人信息及服务商首次游览日期
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#validationLpr(java.util.List)
	 * @param lprs
	 *            传入领票人泛型集合
	 * @return 返回验证信息 若长度小于1则表示通过
	 * @author yangguang Date:2012-8-7
	 * @throws ParseException
	 */
	public Map validationLpr(List<LprPojo> lprs, String ibussiness, String usid)
			throws ParseException {
		Map errMsg = new HashMap();
		int x = 0;
		for (int i = 0; i < lprs.size(); i++) {
			LprPojo lpr = lprs.get(i);
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, Long.parseLong(lpr.getIscenicid()));
			if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]导游名称不能为空");
				x += 1;
			}
			if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]联系方式不能为空");
				x += 1;
			}
			if (lpr.getSzregionalid() == null
					|| lpr.getSzregionalid().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]客源地不能为空");
				x += 1;
			}
			if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
				errMsg.put(x, "[" + provider.getSzscenicname() + "]证件号码不能为空");
				x += 1;
			} else {
//				Match match = new Match();
				Pattern p = Pattern.compile("^\\d{17}([0-9]|X|x)$");
				boolean b = p.matcher(lpr.getZjhm()).matches();
				if (!b) {
					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]证件号码格式不对");
				}
			}
			if (lpr.getDaoyouid() == null || lpr.getDaoyouid().equals("")) {
				if (lpr.getPassword() == null || lpr.getPassword().equals("")) {
					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]取票密码不能为空");
					x += 1;
				} else if (lpr.getPassword().length() < 6
						|| lpr.getPassword().length() > 10) {

					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]取票密码必须大于6位小于10位");
					x += 1;
				} else if (!Tools.isnum(lpr.getPassword())) {
					errMsg.put(x, "[" + provider.getSzscenicname()
							+ "]取票密码必须是数字");
					x += 1;

				}
			}

			Proordercontroltab control = (Proordercontroltab) ticketDao.get(
					Proordercontroltab.class,
					Long.parseLong(lpr.getIscenicid()));
			if (control != null) {
				Long num = control.getIspaperscontrol();
				if (num != null && num > 0L) {
					String hsql = "select distinct m.orid from MOrder m,TOrder t,TOrderlist tl where m.orid = t.id.orid and t.id.orid = tl.id.orid and t.dtstartdate='"
							+ lpr.getRzti()
							+ "' and upper(t.orhm)='"
							+ lpr.getZjhm().toUpperCase()
							+ "' and m.ddzt in ('00','01','02','11') ";
					List list = this.ticketDao.find(hsql);
					if (list != null && !list.isEmpty()) {
						int ordernum = list.size();
						if (ordernum >= num.intValue()) {
							errMsg.put(x, "该证件号码在[" + lpr.getRzti() + "]已绑定"
									+ num + "个订单，已达上限。");
							x += 1;
						}
					}
				}
			}

		}
		return errMsg;
	}

	public Map validationPrice(List<OrderPojo> tickets, List<LprPojo> lprs,
			String ibusinessid, String usid) throws IllegalAccessException,
			InvocationTargetException {
		Map map = new HashMap();
		int i = 0;
		for (OrderPojo ticket : tickets) {
			Edmtickettypetab yticket = (Edmtickettypetab) ticketDao.get(
					Edmtickettypetab.class,
					Long.parseLong(ticket.getItickettypeid()));
			LprPojo plpr = null;
			for (LprPojo lpr : lprs) {
				if (yticket.getIscenicid().intValue() == Integer.parseInt(lpr
						.getIscenicid())) {
					plpr = new LprPojo();
					BeanUtils.copyProperties(plpr, lpr);
					break;
				}
			}
			// 获取价格分组
			String groupno = ticketDao.searchJgfz(usid, yticket.getIscenicid());

			for (OrderPojo price : ticket.getPrice()) {
				Edmcrowdkindpricetab yprice = ticketDao.getProductPrice(
						ticket.getItickettypeid(), ibusinessid, plpr.getRzti(),
						price.getIcrowdkindid(), groupno);
				Edpcrowdkindtab humtype = (Edpcrowdkindtab) ticketDao.get(
						Edpcrowdkindtab.class,
						Long.parseLong(price.getIcrowdkindid()));
				if (yprice == null
						|| price.getIcrowdkindpriceid().intValue() != yprice
								.getIcrowdkindpriceid().intValue()) {
					map.put(i,
							yticket.getSztickettypename() + "["
									+ humtype.getSzcrowdkindname()
									+ "]无价格或价格异常");
					i += 1;
				}
			}
			ticket.setTourdate(plpr.getRzti());// 设置首次游览日期
		}
		return map;

	}

	public void fillLprInfo(List<LprPojo> lprs) {
		for (LprPojo lpr : lprs) {
			Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
					Esbscenicareatab.class, Long.parseLong(lpr.getIscenicid()));
			lpr.setProvidername(provider.getSzscenicname());
			if (provider.getIparentid() != null
					&& provider.getIparentid().intValue() != 0) {
				lpr.setIparentid(provider.getIparentid().toString());
			}
			Galsourceregiontab from = (Galsourceregiontab) ticketDao.get(
					Galsourceregiontab.class,
					Long.parseLong(lpr.getSzregionalid()));
			System.out.println("========>1:" + from.getSzregionalname());
			System.out.println("========>1:" + from.getSzinnername());
			if (from.getSzinnername() == null
					|| from.getSzinnername().trim().equals("")) {
				lpr.setAddress(from.getSzregionalname());
			} else {
				lpr.setAddress(from.getSzinnername());
			}

			if (lpr.getDaoyouid() != null && !lpr.getDaoyouid().equals("")) {
				Custom cus = (Custom) ticketDao.get(Custom.class,
						lpr.getDaoyouid());
				lpr.setDaoyou(cus.getLname());
			}
		}
	}

	/**
	 * * Describe:获取订单参数信息
	 * 
	 * @see com.ectrip.ticket.service.iservice.ITicketService#getListordercs(java.lang.String)
	 * @param ibusinessid
	 * @return
	 * @author lijingrui Date:2013-4-7
	 */
	public List getListordercs(String ibusinessid) {
		return ticketDao.getListordercs(ibusinessid);
	}

	public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid,
			Long icrowkind, Long itickettypeid, String startdate) {
		return ticketDao.getScheme(iscenicid, ibusinessid, icrowkind,
				itickettypeid, startdate);
	}

	/**
	 * * Describe:获取服务商优惠信息
	 * 
	 * @see com.ectrip.ticket.dao.idao.ITicketDAO#checkEdpschemet(java.lang.Long,
	 *      java.lang.String)
	 * @param iscenicid
	 * @param startdate
	 * @return
	 * @author lijingrui Date:2014-4-1
	 */
	public Edpofferschemetab checkEdpschemet(Long iscenicid, String startdate,
			Long ibusinessid) {
		return ticketDao.checkEdpschemet(iscenicid, startdate, ibusinessid);
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
		return ticketDao.searchJgfz(usid, iscenicid);
	}

}
