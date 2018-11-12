package com.ectrip.ec.book.shopcart.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.shopcart.dao.idao.IShopCartDAO;
import com.ectrip.ec.model.order.Shopcart;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;

public class ShopCartDAO extends GenericDao implements IShopCartDAO {
	/**
	 * 增加购物车 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 * @return return:String Date:2012-2-1
	 */
	public String addShopCart(Shopcart shopcart) {
		StringBuffer strseq = new StringBuffer();
		strseq.append(shopcart.getSeq());
		this.save(shopcart);
		return strseq.toString();
	}

	/**
	 * 修改购物车 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 *            return:void Date:2012-2-1
	 */
	public void updateShopCart(Shopcart shopcart) {
		this.update(shopcart);
	}

	/**
	 * 根据编号删除购物车 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopId要删除的产品编号数组
	 *            return:void Date:2012-2-1
	 */
	public void deleteShopCart(Long[] shopId) {
		if (shopId != null) {
			for (int i = 0; i < shopId.length; i++) {
				Long shopseq = shopId[i];
				this.deleteByKey(Shopcart.class, shopseq);
			}
		}
	}

	/**
	 * 根据用户删除购物车 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid用户名
	 * @param shopseq购物车编号
	 *            (当是删除所有时，输入0) return:void Date:2012-2-2
	 */
	public void deleteShopCartByUser(String usid, Long shopseq) {
		if (usid != null && !"".equals(usid)) {
			StringBuffer hsql = new StringBuffer();
			hsql.append(" from Shopcart where usid = '" + usid + "' ");
			if (shopseq != null && 0L != shopseq) {
				hsql.append(" and seq=" + shopseq);
			}
			List shopcartList = this.find(hsql.toString());
			if (shopcartList != null && shopcartList.size() >= 1) {
				for (int i = 0; i < shopcartList.size(); i++) {
					Shopcart shopcart = (Shopcart) shopcartList.get(i);
					this.delete(shopcart);
				}
			}
		}
	}

	/**
	 * 删除前一天购物车中的数据Describe:当用户登录时，usid不为空，cookieid为空，当未登录时，usid为空，cookieid不能为空
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 *            用户名
	 * @param cookieid
	 *            return:void Date:2012-2-2
	 */
	public void deleteShopCartByTime(String usid, String cookieid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Shopcart where substr(shpdate,0,10)<'" + Tools.getDays() + "' ");
		if (usid != null && !"".equals(usid)) {
			hsql.append(" and usid ='" + usid + "' ");
		}
		if (cookieid != null && !"".equals(cookieid)) {
			hsql.append(" and cookeid='" + cookieid + "' ");
		}
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Shopcart shopcart = (Shopcart) list.get(i);
				this.delete(shopcart);
			}
		}
	}

	/**
	 * 得到购物车列表（用户登录时） Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid用户名
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url路径
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartListByUser(String usid, int page, int pageSize, String url) {

		Custom custom = (Custom) this.get(Custom.class, usid);
		String lgtp = "";
		if (custom != null) {
			// 导游 预订时显示散客的价格 lijingrui 2012-08-02
			if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")) {
				lgtp = "01";
			} else {
				lgtp = custom.getLgtp();
			}
		}

		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(s.seq as seq,s.usid as usid,s.cookeid as cookieid,s.iscenicid as iscenicid,s.scenictype as scenictype,s.itickettypeid as itickettypeid,s.icrowdkindpriceid as icrowdkindpriceid,s.rzti as rzti,s.ldti as ldti,s.numb as numb,s.raftno as raftno,s.seatid as seatid,s.shpdate as shpdate,s.rnumb as rnumb,s.cdate as cdate,s.notea as notea ) from Shopcart s  ");
		hsql.append(" where s.usid ='" + usid + "' order by s.seq ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		if (ps != null) {
			List list = ps.getItems();
			if (list != null && list.size() >= 1) {
				Map map = null;
				for (int i = 0; i < list.size(); i++) {
					map = (Map) list.get(i);

					// 服务商编号
					if (map.get("iscenicid") != null && !"".equals(map.get("iscenicid"))) {
						Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
						Esbscenicareatab provider = (Esbscenicareatab) this.get(
								Esbscenicareatab.class, iscenicid);
						if (provider != null) {
							map.put("szscenicname", provider.getSzscenicname());
						}
					}
					// 服务商类型
					if (map.get("scenictype") != null && !"".equals(map.get("scenictype"))) {
						String scenictype = map.get("scenictype").toString();
						String sql = " from Sysparv5 where id.pmky='PDTP' and id.pmcd ='"
								+ scenictype + "' ";
						List typelist = this.find(sql);
						if (typelist != null && typelist.size() >= 1) {
							Sysparv5 sysparv5 = (Sysparv5) typelist.get(0);
							if (sysparv5 != null) {
								map.put("strscenictype", sysparv5.getPmva());
							}
						}

					}

					Edmtickettypetab tickettype = new Edmtickettypetab();
					// 产品编号
					if (map.get("itickettypeid") != null && !"".equals(map.get("itickettypeid"))) {
						Long tickettypeid = Long.parseLong(map.get("itickettypeid").toString());
						tickettype = (Edmtickettypetab) this.get(Edmtickettypetab.class,
								tickettypeid);
						//查询商品对应的图片路径
						String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
								+ tickettypeid;
						List piclist = this.find(hsqls);
						tickettype.setList(piclist);
						if (tickettype != null) {
							map.put("sztickettypename", tickettype.getSztickettypename());
							map.put("bycategorytype", tickettype.getBycategorytype());
							map.put("Validityday", tickettype.getValidityday() - 1);
							map.put("piclist", piclist);
						}
					}
					// 价格
					if (map.get("icrowdkindpriceid") != null
							&& !"".equals(map.get("icrowdkindpriceid"))) {
						Long icrowdkindpriceid = Long.parseLong(map.get("icrowdkindpriceid")
								.toString());
						Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.get(
								Edmcrowdkindpricetab.class, icrowdkindpriceid);
						if (price != null) {
							Edmcrowdkindpricetab px = new Edmcrowdkindpricetab();
							try {
								BeanUtils.copyProperties(px, price);
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (map.get("scenictype").equals("06")) {
								if (Tools.getDayOfWeek(map.get("rzti").toString()) == 6
										|| Tools.getDayOfWeek(map.get("rzti").toString()) == 7) {
									px.setMactualsaleprice(price.getWeekendprice());
								}
							}
							map.put("price", px);

							if (px.getIcrowdkindid() != null) {
								Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.get(
										Edpcrowdkindtab.class, px.getIcrowdkindid());
								map.put("szcrowdkindname", crowdkind.getSzcrowdkindname());
							}
						}
					}
					List templist = new ArrayList();
					// 酒店

					int sumdays = 0;
					int daynum = Tools.getDayNumb(map.get("rzti").toString(), map.get("ldti")
                            .toString());
					if (map.get("scenictype").equals("06")) {
						sumdays = daynum - 1;
					} else {
						sumdays = daynum;
					}

					// 循环得到酒店每天的价格
					for (int j = 0; j < sumdays; j++) {
						List plist = this.getPriceHotel(
								Long.parseLong(map.get("itickettypeid").toString()),
								map.get("scenictype").toString(),
								Tools.getDate(map.get("rzti").toString(), j),
								Tools.getDate(map.get("rzti").toString(), j), lgtp,
								Long.parseLong(map.get("icrowdkindpriceid").toString()));
						if (plist != null && plist.size() >= 1) {
							templist.add(plist);
						}
					}
					map.put("pricelist", templist);

					if (map.get("rzti") != null && !"".equals(map.get("rzti"))) {
						String stdt = map.get("rzti").toString();

						if (stdt.compareTo(Tools.getDays()) < 0) {// 比较首次浏览日期与当前日期的大小
							if ("01".equals(map.get("scenictype"))
									|| "02".equals(map.get("scenictype"))
									|| "03".equals(map.get("scenictype"))
									|| "04".equals(map.get("scenictype"))
									|| "05".equals(map.get("scenictype"))
									|| "06".equals(map.get("scenictype"))) {
								map.put("status", 1);
							}

						} else {
							if (map.get("notea") != null && !"".equals(map.get("notea"))) {// 说明有竹筏了
								String zfdate = map.get("notea").toString();// 竹筏时间
								if ("0003".equals(tickettype.getBycategorytype())) {
									if (!stdt.equals(zfdate)) {
										map.put("status", 1);
									} else {
										if (map.get("raftno") != null
												&& !"".equals(map.get("raftno"))) {
											if (!"0".equals(map.get("raftno").toString())) {
												Prdtripvenuemanage prdtrip = new Prdtripvenuemanage();
												String trhsql = " from Prdtripvenuemanage where tripid = "
														+ Long.parseLong(map.get("raftno")
																.toString())
														+ " and startdata<='"
														+ zfdate
														+ "' and enddata>='"
														+ zfdate
														+ "' and itickettypeid="
														+ tickettype.getItickettypeid();
												List prdlist = this.find(trhsql);
												if (prdlist != null && prdlist.size() >= 1) {
													prdtrip = (Prdtripvenuemanage) prdlist.get(0);
													if (prdtrip != null) {
														if (map.get("notea").toString()
																.compareTo(Tools.getDays()) <= 0) {// 比较首次浏览日期与当前日期的大小
															String triptime = prdtrip
																	.getStarttime();
															String dates = Tools.getDayTimes()
																	.substring(11, 19);
															if (triptime.compareTo(dates) < 0) {
																map.put("status", 1);
															}
														}
													}
												} else {
													map.put("status", 1);
												}

											}

										}
									}
								} else if ("0010".equals(tickettype.getBycategorytype())) {
									Integer day = Integer.parseInt(tickettype.getValidityday()
											.toString());// 有效天数
									String etdt = Tools.getDate(stdt, day - 1);
									if (zfdate.compareTo(stdt) < 0) {
										map.put("status", 1);
									}
									if (etdt.compareTo(zfdate) < 0) {
										map.put("status", 1);
									} else {
										if (map.get("raftno") != null
												&& !"".equals(map.get("raftno"))) {
											if (!"0".equals(map.get("raftno").toString())) {
												ShopCartOtherDAO shopotherdao = (ShopCartOtherDAO) SpringUtil
														.getBean("shopcartotherDao");

												List sonList = shopotherdao.getSonProductList(Long
														.parseLong(map.get("itickettypeid")
																.toString()), map.get("rzti")
														.toString(), map.get("rzti").toString(),
														lgtp, Long.parseLong(map.get(
																"icrowdkindpriceid").toString()));
												Long productId = 0L;
												if (sonList != null && sonList.size() >= 1) {
													for (int j = 0; j < sonList.size(); j++) {
														Edmtickettypetab pro = (Edmtickettypetab) sonList
																.get(j);
														if ("0003".equals(pro.getBycategorytype())) {
															productId = pro.getItickettypeid();
														}

													}
												}
												Prdtripvenuemanage prdtrip = new Prdtripvenuemanage();
												String trhsql = " from Prdtripvenuemanage where tripid = "
														+ Long.parseLong(map.get("raftno")
																.toString())
														+ " and startdata<='"
														+ zfdate
														+ "' and enddata>='"
														+ zfdate
														+ "' and itickettypeid=" + productId;
												List prdlist = this.find(trhsql);
												if (prdlist != null && prdlist.size() >= 1) {
													prdtrip = (Prdtripvenuemanage) prdlist.get(0);
													if (prdtrip != null) {
														if (map.get("notea").toString()
																.compareTo(Tools.getDays()) <= 0) {// 比较首次浏览日期与当前日期的大小
															String triptime = prdtrip
																	.getStarttime();
															String dates = Tools.getDayTimes()
																	.substring(11, 19);
															if (triptime.compareTo(dates) < 0) {
																map.put("status", 1);
															}
														}
													}
												} else {
													map.put("status", 1);
												}

											}

										}
									}
								}
							}
						}
					}

					Map raftMap = new HashMap();
					StringBuffer raftvalue = new StringBuffer();
					if (map.get("raftno") != null && !"".equals(map.get("raftno"))) {

						ShopCartOtherDAO shopotherdao = (ShopCartOtherDAO) SpringUtil
								.getBean("shopcartotherDao");
						List raftlist = new ArrayList();
						try {
							raftlist = shopotherdao.queryRafttripInfoList(tickettype
									.getItickettypeid(), map.get("rzti").toString(),
									map.get("notea").toString(), lgtp);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						if (raftlist != null && raftlist.size() >= 1) {
							Map tripmap = null;
							for (int j = raftlist.size() - 1; j >= 0; j--) {
								tripmap = (Map) raftlist.get(j);
								raftMap.put(
										tripmap.get("tripid"),
										(tripmap.get("ivenueareaname") + "/"
												+ tripmap.get("tripname") + "/"
												+ tripmap.get("starttime") + "/" + tripmap
												.get("salablenumber")));
							}
						} else {
							raftMap.put("", "发排码头/趟次/发排时间/剩余量");
						}

					} else {
						raftMap.put("", "发排码头/趟次/发排时间/剩余量");
					}

					map.put("raftlist", raftMap);
				}
			}
		}
		return ps;
	}

	/**
	 * 得到购物车列表（用户未登录时） Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopSeq
	 *            购物车编号数组
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url路径
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartList(Long[] shopSeq, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(s.seq as seq,s.usid as usid,s.cookeid as cookieid,s.iscenicid as iscenicid,s.scenictype as scenictype,s.itickettypeid as itickettypeid,s.icrowdkindpriceid as icrowdkindpriceid,s.rzti as rzti,s.ldti as ldti,s.numb as numb,s.raftno as raftno,s.seatid as seatid,s.shpdate as shpdate,s.rnumb as rnumb,s.cdate as cdate,s.notea as notea ) from Shopcart s  ");

		if (shopSeq != null && shopSeq.length >= 1) {
			StringBuffer seq = new StringBuffer();
			for (int i = 0; i < shopSeq.length; i++) {
				Long shopid = shopSeq[i];
				if (i == shopSeq.length - 1) {
					seq.append(shopid);
				} else {
					seq.append(shopid + ",");
				}

			}
			if (seq != null && !"".equals(seq)) {
				hsql.append(" where seq in(" + seq + ") ");
			}

			hsql.append(" order by s.seq ");
			ps = this.findPage(hsql.toString(), pageSize, page, url);
			if (ps != null) {
				List list = ps.getItems();
				if (list != null && list.size() >= 1) {
					Map map = null;
					for (int i = 0; i < list.size(); i++) {
						map = (Map) list.get(i);
						// 服务商编号
						if (map.get("iscenicid") != null && !"".equals(map.get("iscenicid"))) {
							Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
							Esbscenicareatab provider = (Esbscenicareatab) this.get(
									Esbscenicareatab.class, iscenicid);
							if (provider != null) {
								map.put("szscenicname", provider.getSzscenicname());
							}
						}
						// 服务商类型
						if (map.get("scenictype") != null && !"".equals(map.get("scenictype"))) {
							String scenictype = map.get("scenictype").toString();
							String sql = " from Sysparv5 where id.pmky='PRTP' and id.pmcd ='"
									+ scenictype + "' ";
							List typelist = this.find(sql);
							if (typelist != null && typelist.size() >= 1) {
								Sysparv5 sysparv5 = (Sysparv5) typelist.get(0);
								if (sysparv5 != null) {
									map.put("strscenictype", sysparv5.getPmva());
								}
							}
						}
						Edmtickettypetab tickettype = new Edmtickettypetab();
						// 产品编号
						if (map.get("itickettypeid") != null
								&& !"".equals(map.get("itickettypeid"))) {
							Long tickettypeid = Long.parseLong(map.get("itickettypeid").toString());
							tickettype = (Edmtickettypetab) this.get(Edmtickettypetab.class,
									tickettypeid);
							//查询商品对应的图片路径
							String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
									+ tickettypeid;
							List piclist = this.find(hsqls);
							tickettype.setList(piclist);
							if (tickettype != null) {
								map.put("sztickettypename", tickettype.getSztickettypename());
								map.put("bycategorytype", tickettype.getBycategorytype());
								map.put("Validityday", tickettype.getValidityday() - 1);
								map.put("piclist", piclist);
							}
						}
						// 价格
						if (map.get("icrowdkindpriceid") != null
								&& !"".equals(map.get("icrowdkindpriceid"))) {
							Long icrowdkindpriceid = Long.parseLong(map.get("icrowdkindpriceid")
									.toString());
							Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.get(
									Edmcrowdkindpricetab.class, icrowdkindpriceid);
							if (price != null) {
								Edmcrowdkindpricetab px = new Edmcrowdkindpricetab();
								try {
									BeanUtils.copyProperties(px, price);
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (map.get("scenictype").equals("06")) {
									if (Tools.getDayOfWeek(map.get("rzti").toString()) == 6
											|| Tools.getDayOfWeek(map.get("rzti").toString()) == 7) {
										px.setMactualsaleprice(price.getWeekendprice());
									}
								}
								map.put("price", px);

								if (px.getIcrowdkindid() != null) {
									Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.get(
											Edpcrowdkindtab.class, px.getIcrowdkindid());
									map.put("szcrowdkindname", crowdkind.getSzcrowdkindname());
								}

							}
						}

						List templist = new ArrayList();

						int sumdays = 0;
						int daynum = Tools.getDayNumb(map.get("rzti").toString(), map.get("ldti")
                                .toString());
						if (map.get("scenictype").equals("06")) {
							sumdays = daynum - 1;
						} else {
							sumdays = daynum;
						}

						// 循环得到酒店每天的价格
						for (int j = 0; j < sumdays; j++) {
							List plist = this.getPriceHotel(
									Long.parseLong(map.get("itickettypeid").toString()),
									map.get("scenictype").toString(),
									Tools.getDate(map.get("rzti").toString(), j),
									Tools.getDate(map.get("rzti").toString(), j), "");
							if (plist != null && plist.size() >= 1) {
								templist.add(plist);
							}
						}
						map.put("pricelist", templist);

						if (map.get("rzti") != null && !"".equals(map.get("rzti"))) {
							String stdt = map.get("rzti").toString();
							if (stdt.compareTo(Tools.getDays()) < 0) {// 比较首次浏览日期与当前日期的大小
								if ("01".equals(map.get("scenictype"))
										|| "02".equals(map.get("scenictype"))
										|| "03".equals(map.get("scenictype"))
										|| "04".equals(map.get("scenictype"))
										|| "05".equals(map.get("scenictype"))
										|| "06".equals(map.get("scenictype"))) {
									map.put("status", 1);
								}
							} else {
								if (map.get("notea") != null && !"".equals(map.get("notea"))) {// 说明有竹筏了
									String zfdate = map.get("notea").toString();// 竹筏时间
									if ("0003".equals(tickettype.getBycategorytype())) {
										if (!stdt.equals(zfdate)) {
											map.put("status", 1);
										} else {
											if (map.get("raftno") != null
													&& !"".equals(map.get("raftno"))) {
												if (!"0".equals(map.get("raftno").toString())) {
													Prdtripvenuemanage prdtrip = new Prdtripvenuemanage();
													String trhsql = " from Prdtripvenuemanage where tripid = "
															+ Long.parseLong(map.get("raftno")
																	.toString())
															+ " and startdata<='"
															+ zfdate
															+ "' and enddata>='"
															+ zfdate
															+ "' and itickettypeid="
															+ tickettype.getItickettypeid();
													List prdlist = this.find(trhsql);
													if (prdlist != null && prdlist.size() >= 1) {
														prdtrip = (Prdtripvenuemanage) prdlist
																.get(0);
														if (prdtrip != null) {
															if (map.get("notea").toString()
																	.compareTo(Tools.getDays()) <= 0) {// 比较首次浏览日期与当前日期的大小
																String triptime = prdtrip
																		.getStarttime();
																String dates = Tools.getDayTimes()
																		.substring(11, 19);
																if (triptime.compareTo(dates) < 0) {
																	map.put("status", 1);
																}
															}
														}
													} else {
														map.put("status", 1);
													}

												}

											}
										}
									} else if ("0010".equals(tickettype.getBycategorytype())) {
										Integer day = Integer.parseInt(tickettype.getValidityday()
												.toString());// 有效天数
										String etdt = Tools.getDate(stdt, day - 1);
										if (zfdate.compareTo(stdt) < 0) {
											map.put("status", 1);
										}
										if (etdt.compareTo(zfdate) < 0) {
											map.put("status", 1);
										} else {
											if (map.get("raftno") != null
													&& !"".equals(map.get("raftno"))) {
												if (!"0".equals(map.get("raftno").toString())) {
													ShopCartOtherDAO shopotherdao = (ShopCartOtherDAO) SpringUtil
															.getBean("shopcartotherDao");

													List sonList = shopotherdao.getSonProductList(
															Long.parseLong(map.get("itickettypeid")
																	.toString()), map.get("rzti")
																	.toString(), map.get("rzti")
																	.toString(), "", Long
																	.parseLong(map.get(
																			"icrowdkindpriceid")
																			.toString()));
													Long productId = 0L;
													if (sonList != null && sonList.size() >= 1) {
														for (int j = 0; j < sonList.size(); j++) {
															Edmtickettypetab pro = (Edmtickettypetab) sonList
																	.get(j);
															if ("0003".equals(pro
																	.getBycategorytype())) {
																productId = pro.getItickettypeid();
															}

														}
													}

													Prdtripvenuemanage prdtrip = new Prdtripvenuemanage();
													String trhsql = " from Prdtripvenuemanage where tripid = "
															+ Long.parseLong(map.get("raftno")
																	.toString())
															+ " and startdata<='"
															+ zfdate
															+ "' and enddata>='"
															+ zfdate
															+ "' and itickettypeid=" + productId;
													List prdlist = this.find(trhsql);
													if (prdlist != null && prdlist.size() >= 1) {
														prdtrip = (Prdtripvenuemanage) prdlist
																.get(0);
														if (prdtrip != null) {
															if (map.get("notea").toString()
																	.compareTo(Tools.getDays()) <= 0) {// 比较首次浏览日期与当前日期的大小
																String triptime = prdtrip
																		.getStarttime();
																String dates = Tools.getDayTimes()
																		.substring(11, 19);
																if (triptime.compareTo(dates) < 0) {
																	map.put("status", 1);
																}
															}
														}
													} else {
														map.put("status", 1);
													}

												}

											}
										}
									}
								}
							}
						}

						Map raftMap = new HashMap();
						StringBuffer raftvalue = new StringBuffer();
						if (map.get("raftno") != null && !"".equals(map.get("raftno"))) {
							Long tripid = Long.parseLong(map.get("raftno").toString());
							String raftsql = " select v.ivenueareaname,t.tripname,pm.starttime,p.salablenumber,p.soldnumber from Trip t ,Venuearea v,Productcontrol p,Prdtripvenuemanage pm where  t.tripid="
									+ tripid
									+ "  and p.stdata='"
									+ map.get("notea")
									+ "' and  pm.startdata<='"
									+ map.get("notea")
									+ "' and pm.enddata>='"
									+ map.get("notea")
									+ "' and p.tripid is not null and t.tripid = p.tripid and v.ivenueareaid = p.ivenueareaid and pm.tripid = t.tripid";
							List triplist = this.find(raftsql);
							if (triplist != null && triplist.size() >= 1) {
								Object[] obj = (Object[]) triplist.get(0);
								String ivenueareaname = obj[0].toString();
								String tripname = obj[1].toString();
								String starttime = obj[2].toString();
								Long salablenumber = Long.parseLong(obj[3].toString());
								Long soldnumber = Long.parseLong(obj[4].toString());
								Long num = salablenumber - soldnumber;

								raftvalue.append(ivenueareaname + "/" + tripname + "/" + starttime
										+ "/" + num);
								raftMap.put(tripid, raftvalue);
							} else {
								raftMap.put("", "发排码头/趟次/发排时间/剩余量");
							}

						} else {
							raftMap.put("", "发排码头/趟次/发排时间/剩余量");
						}

						map.put("raftlist", raftMap);

					}
				}
			}
		} else {
			ps = null;
		}
		return ps;
	}

	/**
	 * 获取购物列表 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param Seq购物车编号数组
	 *            （未登录用）
	 * @param usid用户名
	 *            （登录时用）
	 * @return return:List Date:2012-2-4
	 */
	public List queryShopList(Long[] Seq, String usid) {
		List<Shopcart> list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Shopcart s ");
		if (Seq != null && Seq.length >= 1) {// 若数组中有产品则查询购物车中的产品是否包含数组中测产品
			StringBuffer productIds = new StringBuffer();
			for (int i = 0; i < Seq.length; i++) {
				if (i == Seq.length - 1) {
					productIds.append(Seq[i]);
				} else {
					productIds.append(Seq[i] + ",");
				}
			}
			hsql.append(" where s.seq in(" + productIds + ") ");
		} else if (usid != null && !"".equals(usid)) {// 若用户已登录，则查询用户的购物车.
			hsql.append(" where s.usid='" + usid + "' ");
		} else {
			return list;
		}
		list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			for (Shopcart mylist : list) {
				
				// 服务商编号
				if (mylist.getIscenicid() != null && !"".equals(mylist.getIscenicid())) {
					Long iscenicid = mylist.getIscenicid();
					Esbscenicareatab provider = (Esbscenicareatab) this.get(Esbscenicareatab.class,
							iscenicid);
					if (provider != null) {
						mylist.setSzscenicname(provider.getSzscenicname());
						mylist.setScenictype(provider.getScenictype());
					}
				}
				// 产品编号
				if (mylist.getItickettypeid() != null && !"".equals(mylist.getItickettypeid())) {
					Long tickettypeid = mylist.getItickettypeid();
					Edmtickettypetab tickettype = (Edmtickettypetab) this.get(
							Edmtickettypetab.class, tickettypeid);
					if (tickettype != null) {
						mylist.setSztickettypename(tickettype.getSztickettypename());
					}
				}

				List templist = new ArrayList();

				int sumdays = 0;
				int daynum = Tools.getDayNumb(mylist.getRzti(), mylist.getLdti());
				if (mylist.getScenictype().equals("06")) {// 酒店
					sumdays = daynum - 1;
				} else {
					sumdays = daynum;
				}
				String lgtp = "";
				if (usid != null && !"".equals(usid)) {
					Custom custom = (Custom) this.get(Custom.class, usid);
					if (custom != null) {
						// 导游 预订时显示散客的价格 lijingrui 2012-08-02
						if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")) {
							lgtp = "01";
						} else {
							lgtp = custom.getLgtp();
						}
					}
				}

				// 循环得到每天的价格
				for (int j = 0; j < sumdays; j++) {
					List plist = this
							.getPriceHotel(mylist.getItickettypeid(), mylist.getScenictype(),
									Tools.getDate(mylist.getRzti(), j),
									Tools.getDate(mylist.getRzti(), j), lgtp,
									mylist.getIcrowdkindpriceid());
					if (plist != null && plist.size() >= 1) {
						templist.add(plist);
					}
				}

				mylist.setPricelist(JSON.toJSONString(templist));

			}
		}

		return list;
	}
	/**
	 * 获取购物列表 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param Seq购物车编号数组
	 *            （未登录用）
	 * @param usid用户名
	 *            （登录时用）
	 * @return return:List Date:2012-2-4
	 *//*
	public List queryShopList(Long[] Seq, String usid,Long id) {
		List<Shopcart> list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Shopcart s ");
		if (Seq != null && Seq.length >= 1) {// 若数组中有产品则查询购物车中的产品是否包含数组中测产品
			StringBuffer productIds = new StringBuffer();
			for (int i = 0; i < Seq.length; i++) {
				if (i == Seq.length - 1) {
					productIds.append(Seq[i]);
				} else {
					productIds.append(Seq[i] + ",");
				}
			}
			hsql.append(" where s.seq in(" + productIds + ") ");
		} else if (usid != null && !"".equals(usid)) {// 若用户已登录，则查询用户的购物车.
			hsql.append(" where s.usid='" + usid + "' ");
		} else {
			return list;
		}
		list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			for (Shopcart mylist : list) {
				//判断商品数量
				List shoplist = this.find("from Shopcart s where itickettypeid = "+id);
				if(shoplist!=null && shoplist.size()>0){
					Shopcart shop = (Shopcart)shoplist.get(0);
					if(shop.getNumb()>999){
						return new ArrayList<String>();
					}
				}
				
				// 服务商编号
				if (mylist.getIscenicid() != null && !"".equals(mylist.getIscenicid())) {
					Long iscenicid = mylist.getIscenicid();
					Esbscenicareatab provider = (Esbscenicareatab) this.get(Esbscenicareatab.class,
							iscenicid);
					if (provider != null) {
						mylist.setSzscenicname(provider.getSzscenicname());
						mylist.setScenictype(provider.getScenictype());
					}
				}
				// 产品编号
				if (mylist.getItickettypeid() != null && !"".equals(mylist.getItickettypeid())) {
					Long tickettypeid = mylist.getItickettypeid();
					Edmtickettypetab tickettype = (Edmtickettypetab) this.get(
							Edmtickettypetab.class, tickettypeid);
					if (tickettype != null) {
						mylist.setSztickettypename(tickettype.getSztickettypename());
					}
				}
				
				List templist = new ArrayList();
				
				int sumdays = 0;
				int daynum = Tools.getDayNumb(mylist.getRzti(), mylist.getLdti());
				if (mylist.getScenictype().equals("06")) {// 酒店
					sumdays = daynum - 1;
				} else {
					sumdays = daynum;
				}
				String lgtp = "";
				if (usid != null && !"".equals(usid)) {
					Custom custom = (Custom) this.get(Custom.class, usid);
					if (custom != null) {
						// 导游 预订时显示散客的价格 lijingrui 2012-08-02
						if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")) {
							lgtp = "01";
						} else {
							lgtp = custom.getLgtp();
						}
					}
				}
				
				// 循环得到每天的价格
				for (int j = 0; j < sumdays; j++) {
					List plist = this
							.getPriceHotel(mylist.getItickettypeid(), mylist.getScenictype(),
									Tools.getDate(mylist.getRzti(), j),
									Tools.getDate(mylist.getRzti(), j), lgtp,
									mylist.getIcrowdkindpriceid());
					if (plist != null && plist.size() >= 1) {
						templist.add(plist);
					}
				}
				
				mylist.setPricelist(templist);
				
			}
		}
		
		return list;
	}*/

	/**
	 * 查询酒店每天价格 (non-Javadoc) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettypeId产品编号
	 * @param providertype服务商类型
	 * @param rzti开始日期
	 * @param ldti结束日期
	 * @return return:List Date:2012-2-3
	 */
	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti,
			String lgtp, Long icrowdkindpriceid) {

		try {

			StringBuffer hql = new StringBuffer();
			if (icrowdkindpriceid == null) {
				hql.append("from Edmcrowdkindpricetab p where p.itickettypeid=" + itickettypeId
						+ "  ");
				hql.append(" and p.startdata<='" + rzti + "' and p.enddata>='" + ldti
						+ "' and p.isnet=1 and p.byisuse=1 ");
				if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
					hql.append(" and p.ibusinessid=2 ");
				} else {
					hql.append(" and p.ibusinessid=1 ");
				}
			} else {

				hql.append("from Edmcrowdkindpricetab p where p.icrowdkindpriceid="
						+ icrowdkindpriceid + "  ");
				if ("07".equals(providertype)) {
					hql.append(" and p.ibusinessid=1 ");
				} else {
					if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
						hql.append(" and p.ibusinessid=2 ");
					} else {
						hql.append(" and p.ibusinessid=1 ");
					}
				}
			}
			List ptemp = find(hql.toString());

			List rc_list = new ArrayList();
			if (ptemp != null && ptemp.size() >= 1) {
				for (int i = 0; i < ptemp.size(); i++) {
					Edmcrowdkindpricetab pp = (Edmcrowdkindpricetab) ptemp.get(i);
					Edmcrowdkindpricetab px = new Edmcrowdkindpricetab();
					BeanUtils.copyProperties(px, pp);
					if ("06".equals(providertype)) {
						if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
							px.setMactualsaleprice(pp.getWeekendprice());
						}
					}
					rc_list.add(px);
				}

			}
			return rc_list;
		} catch (Exception e) {
			return null;
		}

	}

	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti,
			String lgtp) {

		try {

			StringBuffer hql = new StringBuffer();
			hql.append("from Edmcrowdkindpricetab p where p.itickettypeid=" + itickettypeId + "  ");
			hql.append(" and p.startdata<='" + rzti + "' and p.enddata>='" + ldti
					+ "' and p.isnet=1 and p.byisuse=1 ");
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				hql.append(" and p.ibusinessid=2 ");
			} else {
				hql.append(" and p.ibusinessid=1 ");
			}
			List ptemp = find(hql.toString());
			List rc_list = new ArrayList();
			if (ptemp != null && ptemp.size() >= 1) {
				for (int i = 0; i < ptemp.size(); i++) {
					Edmcrowdkindpricetab pp = (Edmcrowdkindpricetab) ptemp.get(i);
					Edmcrowdkindpricetab px = new Edmcrowdkindpricetab();
					BeanUtils.copyProperties(px, pp);
					if ("06".equals(providertype)) {
						if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
							px.setMactualsaleprice(pp.getWeekendprice());
						}
					}
					rc_list.add(px);
				}

			}
			return rc_list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 查询子产品价格 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param priceId
	 * @param sontickettypeId
	 * @return return:Edmticketcomposepricetab Date:2012-2-20
	 */
	public Edmticketcomposepricetab querySonPrice(Long priceId, Long sontickettypeId) {
		Edmticketcomposepricetab edmticketcomposepricetab = new Edmticketcomposepricetab();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmticketcomposepricetab where id.icrowdkindpriceid=" + priceId
				+ " and itickettypeid=" + sontickettypeId);
		List list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			edmticketcomposepricetab = (Edmticketcomposepricetab) list.get(0);
		}
		return edmticketcomposepricetab;
	}

	/**
	 * * Describe:保存实名制
	 * 
	 * @see com.ectrip.book.shopcart.dao.idao.IShopCartDAO#saveRealname(java.util.List)
	 * @param list
	 * @author chenxinhao Date:2012-10-16
	 */
	public void saveRealname(List<TRealname> list) {
		for (int i = 0; i < list.size(); i++) {
			try {
				TRealname tRealname = new TRealname();
				long seq = this.getSequenceId("realname_sequence");
				tRealname = (TRealname) list.get(i);
				tRealname.setSeq(seq);
				String cardno = tRealname.getIdcard().toUpperCase();
				tRealname.setIdcard(cardno);
				this.save(tRealname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * 查询服务商(non-Javadoc)
	 * @see com.ectrip.book.shopcart.dao.idao.IShopCartDAO#checkPv(java.lang.Long, java.lang.String)
	 */
	public int checkPv(Long iscenicid, String usid) {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("from Shopcart where iscenicid!="+iscenicid);
		if(usid!=null && !usid.trim().equals("")){
			sqlString.append(" and usid='"+usid+"'");
		}
		List list = find(sqlString.toString());
		return list.size();
	}
	/*
	 * id查询产品(non-Javadoc)
	 * @see com.ectrip.book.shopcart.dao.idao.IShopCartDAO#checkOrder(java.lang.Long)
	 */
	public PaginationSupport checkOrder(Long itickettypeid) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 查询价格(non-Javadoc)
	 * @see com.ectrip.book.shopcart.dao.idao.IShopCartDAO#checkPrice(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public Double checkPrice(Long itickettypeid, Long ibusinessid,
			Long icrowdkindid, String rzti, String ldti,String note1) {
		String hsql = "from Edmcrowdkindpricetab er where er.itickettypeid=? and er.ibusinessid=? and er.icrowdkindid=? and er.startdata<? and er.enddata>? and note1=? and byisuse=1 and isnet=1";
		List list =   find(hsql, new Object[]{itickettypeid,ibusinessid,icrowdkindid,ldti,ldti,note1});
		if (list.size()!=0) {
			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) list.get(0);
			return edmcrowdkindpricetab.getMactualsaleprice();
		}
		else {
			return 0.0;
		}
	}
	
	public Double getPrice(Long itickettypeid, Long ibusinessid,
			Long icrowdkindid, String rzti, String ldti,String note1) {
		String hsql = "from Edmcrowdkindpricetab er where er.itickettypeid=? and er.ibusinessid=? and er.icrowdkindid=? and er.startdata<=? and er.enddata>=? and note1=? and byisuse=1 and isnet=1";
		List list =   find(hsql, new Object[]{itickettypeid,ibusinessid,icrowdkindid,ldti,ldti,note1});
		if (list.size()!=0) {
			Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) list.get(0);
			return edmcrowdkindpricetab.getMactualsaleprice();
		}
		else {
			return null;
		}
	}
	 public Edpofferschemetab getScheme(Long iscenicid,Long ibusinessid,Long icrowkind,Long itickettypeid,String startdate){
		   String hql="from Edpofferschemetab where  icrowdkindid="+icrowkind+"  and  iscenicid="+iscenicid+" and ibusinessid="+ibusinessid+" and itickettypeid="+itickettypeid+" and startdata<='"+startdate+"' and enddata>='"+startdate+"' and byisuse=1 and ioffertype=0 ";
		   System.out.println("-===>>"+hql);
		   List list=find(hql);
		   if(list!=null&&list.size()>0){
			   return (Edpofferschemetab) list.get(0);
		   }else{

			   return null;
		   }
	   }
	 
	public Edmcrowdkindpricetab getPriceModel(Long itickettypeid,
			Long ibusinessid, Long icrowdkindid, String rzti, String ldti,
			String note1) {
		String hsql = "from Edmcrowdkindpricetab er where er.itickettypeid=? and er.ibusinessid=? and er.icrowdkindid=? and er.startdata<=? and er.enddata>=? and note1=? and byisuse=1 and isnet=1";
		List list = find(hsql, new Object[] { itickettypeid, ibusinessid,
				icrowdkindid, ldti, ldti, note1 });
		if(list != null && !list.isEmpty()){
			return (Edmcrowdkindpricetab) list.get(0);
		}
		return null;
	}
	
}
