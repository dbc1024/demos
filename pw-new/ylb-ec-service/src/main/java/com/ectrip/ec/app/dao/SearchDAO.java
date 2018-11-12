package com.ectrip.ec.app.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.EctripMd5;
import com.ectrip.base.util.Encrypt;
import com.ectrip.base.util.MD5Util;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.app.dao.idao.ISearchDAO;
import com.ectrip.ec.app.model.MapPointBean;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.model.app.AndroidStateCode;
import com.ectrip.ec.model.app.BaseModel;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.Vcitable;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Userjf;
import com.ectrip.ec.model.user.Userjflist;
import com.ectrip.ec.model.user.Userjfset;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Contmessage;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

public class SearchDAO extends GenericDao implements ISearchDAO {
	
	SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
	
	private ITicketDAO ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
	
	@Autowired
	private SysparServiceApi sysparServiceApi;
	

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2013-8-1");

		System.out.println(sdf.format(date));
	}
/**
 * 用户登录，用于 移动APP
 * 2014-11-14 修改正确，不要随便修改登录类。
 */
	public String login(String usid, String pwd) {
		String islogin = "0";
		Custom custom = (Custom) get(Custom.class, usid);
		// 判断黑名单
		if (custom == null) {
			// 判断手机号码或者邮箱登陆
			if (Tools.isEmail(usid)) {
				String sql = " from Custom where email='" + usid
						+ "' and status='01'";
				List lst = this.find(sql);
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						Custom cutm = (Custom) lst.get(0);
						if (cutm.getLgtp().equals("02")) { // 旅行社不能用邮箱登陆
							return "1";
						} else {
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								Encrypt ec = new Encrypt();
								String passwd = cutm.getPassword();
								System.out.println("passwd="+passwd);
								
								if(!ec.isPass(pwd, passwd)){
									islogin = "2";
								}
							}
						}

					} else {
						String hsql = " from Custom c where c.email = '" + usid
								+ "' and c.note3='1' and c.status='01' ";
						List list = this.find(hsql);
						if (list != null && list.size() == 1) {
							Custom cutm = (Custom) list.get(0);
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								Encrypt ec = new Encrypt();
								String passwd = cutm.getPassword();
								if(!ec.isPass(pwd, passwd)){
									islogin = "2";
								}
							}

						} else {
							return "1";
						}
					}

				}
			} else {
				// 判断手机登陆
				String sql = " from Custom where mobile='" + usid
						+ "' and status='01'";
				List lst = this.find(sql);
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						Custom cutm = (Custom) lst.get(0);
						if (cutm.getLgtp().equals("02")) { // 旅行社不能用邮箱登陆
							return "1";
						} else {
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								Encrypt ec = new Encrypt();
								String passwd = cutm.getPassword();
								if(!ec.isPass(pwd, passwd)){
									return"2";
								}else {
									return "0";
								}
							}
						}

					} else {
						String hsql = " from Custom c where c.mobile = '"
								+ usid + "' and c.note4='1' and c.status='01' ";
						List list = this.find(hsql);
						if (list != null && list.size() == 1) {
							Custom cutm = (Custom) list.get(0);
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								Encrypt ec = new Encrypt();
								String passwd = cutm.getPassword();
								if(!ec.isPass(pwd, passwd)){
									return "2";
								}else{
									return "0";
								}
							}

						} else {
							return "1";
						}
					}

				}

				islogin = "1";
			}

			return islogin;
		}
		// 查打数据库
		if (custom != null) {
			// System.out.println(33);
			// 比较密码 判断用户状态
			String ls_passwd = "用户状态不对!";
			// 李进于 2013-11-14 修改，禁目集在用户登录
			// 用户状态不对，非正常状态
			if (!custom.getStatus().equals("01")) {
				// System.out.println(44444);
				// 用户注册状态 00未审核01正常02无效03审核未通过04删除标识

				return "1";
			} else {
				if (custom.getLgtp().equals("02")) { // 旅行社不能登陆
					return "1";
				}

				// 口令为空登录不成功
				// System.out.println(5555);
				if (pwd == null || pwd.equals("")) {
					System.out.println(6666666);
					return "2";

				} else {
					// 判断密码
					// System.out.println(777777777);
					Encrypt ec = new Encrypt();
					ls_passwd = custom.getPassword();
					
					if(!ec.isPass(pwd, ls_passwd)){
						islogin = "2";
					}else{
						return "0";
					}
					
					
					
					// System.out.println("ib_true="+islogin);
				}
			}
		}
		// System.out.println("ib_true="+islogin);
		// 登录结束
		return islogin;
	}
	

	public Map findUser(String usid, String pwd) {
		Map map = null;
		StringBuffer hsql = new StringBuffer(
				"select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb,c.note9 as note9) from Custom c where c.usid='"
						+ usid
						+ "' and c.status='01' and substr(c.usqx,2,1)='1'");
		List list = this.find(hsql.toString());
		System.out.println("list="+list.size());
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
			//System.out.println("map="+map);
		} else {
			//System.out.println("usid="+usid);
			if (Tools.isEmail(usid)) {
				System.out.println("111usid="+usid);
				String msql = "select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb) from Custom c where c.email='"
						+ usid
						+ "'  and c.status='01' and substr(c.usqx,2,1)='1'";
				List lst = this.find(msql);
			//	System.out.println("lst="+lst.size());
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						map = (Map) lst.get(0);
						System.out.println("lstmap="+map);
					} else {
						msql = "select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb) from Custom c where c.email='"
								+ usid
								+ "' and c.note3='1'  and c.status='01' and substr(c.usqx,2,1)='1'";
						List lst2 = this.find(msql);
						if (lst2 != null && lst2.size() == 1) {
							map = (Map) lst2.get(0);
						} 
					}

				}
			} else {//手机号
				String msql = "select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb,c.note9 as note9) from Custom c where c.mobile='"
						+ usid
						+ "' and c.note4='1' and c.status='01' and substr(c.usqx,2,1)='1'";
				List lst = this.find(msql);
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						map = (Map) lst.get(0);
					} else {
						msql = "select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb) from Custom c where c.email='"
								+ usid
								+ "' and c.note4='1'  and c.status='01' and substr(c.usqx,2,1)='1'";
						List lst2 = this.find(msql);
						if (lst2 != null && lst2.size() == 1) {
							map = (Map) lst2.get(0);
						}
					}
				}
			}
		}
		if(map!=null){
			List listvipb = this.find("select v.point as balance from Vipbalance v where v.usid='"+map.get("usid").toString()+"'");
		    if(listvipb!=null&&listvipb.size()>0){
		    	map.put("balance", listvipb.get(0));
		    }else{
		    	map.put("balance", "0.00");
		    }

//		    //获取用户头像
//			String sql = "select c.note6 as names, (case c.inote3 when 2 then '女' else '男' end) as sex, c.note7 as birthday, c.mobile, u.upfilename, u.upadder from custom c left join upfile u  on c.inote1 = u.upid where c.usid = ?";
//			try {
//				List list1 = this.findBySqlToMap(hsql.toString(), new String[]{map.get("usid").toString()});
//				if(list!=null&&list.size()>0){
//					Map<String, Object> resultMap = (Map<String, Object>)list.get(0);
//					map.putAll(resultMap);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//
		}

		return map;
	}

	public Map findUserByPwd(String usid, String pwd) {
		Map map = null;
		StringBuffer hsql = new StringBuffer(
				"select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb,c.note9 as note9, c.inote1 as inote1) from Custom c where c.mobile=? and c.status='01' and substr(c.usqx,2,1)='1' and password=?");
		List list = this.find(hsql.toString(), new Object[]{usid, pwd});
		System.out.println("list="+list.size());
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
		}

		if(map!=null){
			List listvipb = this.find("select v.point as balance from Vipbalance v where v.usid=?", new Object[]{usid});
			if(listvipb!=null&&listvipb.size()>0){
				map.put("balance", listvipb.get(0));
			}else{
				map.put("balance", "0.00");
			}
		}
		return map;
	}

	public String valilogin(String usid, String pwd) {
		String islogin = "0";
		Custom custom = (Custom) get(Custom.class, usid);
		// 判断黑名单
		if (custom == null) {
			// 判断手机号码或者邮箱登陆
			if (Tools.isEmail(usid)) {
				String sql = " from Custom where email='" + usid
						+ "' and status='01'";
				List lst = this.find(sql);
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						Custom cutm = (Custom) lst.get(0);
						if (cutm.getLgtp().equals("02")) { // 旅行社不能用邮箱登陆
							return "1";
						} else {
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								String passwd = cutm.getPassword();
								System.out.println("passwd="+passwd);

								if(!pwd.equals(passwd)){
									islogin = "2";
								}
							}
						}

					} else {
						String hsql = " from Custom c where c.email = '" + usid
								+ "' and c.note3='1' and c.status='01' ";
						List list = this.find(hsql);
						if (list != null && list.size() == 1) {
							Custom cutm = (Custom) list.get(0);
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								String passwd = cutm.getPassword();
								if(pwd.equals(passwd)){
									islogin = "2";
								}
							}

						} else {
							return "1";
						}
					}

				}
			} else {
				// 判断手机登陆
				String sql = " from Custom where mobile='" + usid
						+ "' and status='01'";
				List lst = this.find(sql);
				if (lst != null && lst.size() > 0) {
					if (lst.size() == 1) {
						Custom cutm = (Custom) lst.get(0);
						if (cutm.getLgtp().equals("02")) { // 旅行社不能用邮箱登陆
							return "1";
						} else {
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								String passwd = cutm.getPassword();
								if(!pwd.equals(passwd)){
									return"2";
								}else {
									return "0";
								}
							}
						}

					} else {
						String hsql = " from Custom c where c.mobile = '"
								+ usid + "' and c.note4='1' and c.status='01' ";
						List list = this.find(hsql);
						if (list != null && list.size() == 1) {
							Custom cutm = (Custom) list.get(0);
							if (pwd == null || pwd.equals("")) {
								return "2";
							} else {
								// 判断密码
								String passwd = cutm.getPassword();
								if(!pwd.equals(passwd)){
									return "2";
								}else{
									return "0";
								}
							}

						} else {
							return "1";
						}
					}

				}

				islogin = "1";
			}

			return islogin;
		}
		// 查打数据库
		if (custom != null) {
			// System.out.println(33);
			// 比较密码 判断用户状态
			String ls_passwd = "用户状态不对!";
			// 李进于 2013-11-14 修改，禁目集在用户登录
			// 用户状态不对，非正常状态
			if (!custom.getStatus().equals("01")) {
				// System.out.println(44444);
				// 用户注册状态 00未审核01正常02无效03审核未通过04删除标识

				return "1";
			} else {
				if (custom.getLgtp().equals("02")) { // 旅行社不能登陆
					return "1";
				}

				// 口令为空登录不成功
				// System.out.println(5555);
				if (pwd == null || pwd.equals("")) {
					System.out.println(6666666);
					return "2";

				} else {
					// 判断密码
					// System.out.println(777777777);
					Encrypt ec = new Encrypt();
					ls_passwd = custom.getPassword();

					if(!ec.isPass(pwd, ls_passwd)){
						islogin = "2";
					}else{
						return "0";
					}



					// System.out.println("ib_true="+islogin);
				}
			}
		}
		// System.out.println("ib_true="+islogin);
		// 登录结束
		return islogin;
	}

	/**
	 * 根据电话号码获取用户
	 * @param usid
	 * @param pwd
	 * @return
	 */
	public Map findUserByPhone(String usid, String pwd) {
		Map map = null;
		StringBuffer hsql = new StringBuffer(
				"select new  map(c.usid as usid,c.lname as lname,c.lgtp as lgtp,c.ibusinessid as ibusinessid,c.note2 as groupno,c.ttlb as ttlb,c.note9 as note9) from Custom c where c.mobile=? and c.status='01' and substr(c.usqx,2,1)='1' and password=?");
		List list = this.find(hsql.toString(), new Object[]{usid, pwd});
		System.out.println("list="+list.size());
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
		}

		return map;
	}

	/**
	 * 316 普通门票详情
	 * @param url 系统自动获取（不用传）
	 * @param iscenicid 景区id
	 * @return
	 */
	public Map appGetProvider(String url, String iscenicid){
		return (Map) getProviderList(url, null, iscenicid).get(0);
	}

	public List getProviderList(String url, String keyword){
		return getProviderList(url, keyword, null);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getProviderList(String url, String keyword, String iscenicid) {
		if(url==null || url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		StringBuffer sb = new StringBuffer("select distinct new map(esb.iordernumber as iordernumber,esb.iscenicid as iscenicid,esb.szscenicname as szscenicname,esb.szgrade as szgrade,esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription,esb.szlasttime as szlasttime,esb.imaxdata as imaxdata,esb.szqjaddr as szqjaddr,esb.sznetaddr as sznetaddr,esb.szaddress as szaddress,esb.longitude as longitude,esb.szphone as szphone) from Esbscenicareatab esb,Edmtickettypetab pro,Edmcrowdkindpricetab prc where ");
		sb.append(" esb.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01' or spmcd='01')) and prc.ibusinessid=1 and esb.isjd=0 ");
		sb.append(" and esb.byisuse=1 and pro.byisuse=1 and esb.isjd=0 and prc.isnet=1 and prc.byisuse=1 and esb.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid ");
		
		if(!"".equals(keyword) && !" ".equals(keyword) && !"null".equals(keyword) && keyword != null){
			sb.append(" and (esb.szscenicname like '%"+ keyword+ "%' or esb.szsimpleintroduction like '%"+ keyword+ "%') ");
		}
		if(StringUtil.isNotEmpty(iscenicid)){
			sb.append(" and esb.iscenicid='" + iscenicid + "' ");
		}
		sb.append(" order by esb.iordernumber desc");
		
		System.out.println(sb.toString());
		List list = find(sb.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				
			//	System.out.println(map.get("iscenicid"));
				// 根据服务商ID查找最低票价
				StringBuffer hsql = new StringBuffer("select distinct new map(prc.icrowdkindid as icrowdkindid,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid,pro.isequence as isequence,prc.listingprice as listingprice,pro.sztickettypename as sztickettypename,pro.itickettypeid as itickettypeid) from Edmtickettypetab pro, Edmcrowdkindpricetab prc where prc.startdata <= '"
								+ Tools.getDays()+ "' and prc.enddata >= '"+ Tools.getDate(Tools.getDays(), 1) + "' and pro.iscenicid="+ Long.parseLong(map.get("iscenicid").toString())
				                + " and pro.bycategorytype not in( '0013','120','119')  and  pro.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and pro.itickettypeid = prc.itickettypeid and (pro.int3 is null or pro.int3=0)");
				             hsql.append(" and prc.ibusinessid=1 and ( prc.note3 is null or prc.note3='0') ");//排除旅游卡专享门票
				             hsql.append("group by prc.icrowdkindid,pro.itickettypeid,pro.sztickettypename,prc.listingprice,prc.mactualsaleprice,prc.icrowdkindpriceid,pro.isequence");
				             hsql.append(" order by prc.mactualsaleprice asc");//pro.isequence desc,
			//	System.out.println(hsql);
				List list2 = this.find(hsql.toString());
				if (!list2.isEmpty()) {
					map.put("price", ((Map) list2.get(0)).get("mactualsaleprice"));
				}
				StringBuffer picstr = new StringBuffer(
						"select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
								+ new Long(map.get("iscenicid").toString())+ " and p.upid = u.upid and p.itickettypeid=0");
				List piclist = find(picstr.toString());
				
				
				ArrayList arrayList = new ArrayList();
				if (piclist != null && piclist.size() > 0){
					for (int j = 0; j < piclist.size(); j++) {
						Map picmap = (Map) piclist.get(j);
						arrayList.add("http://"
								+ url
								+ picmap.get("upadder").toString()
								+ picmap.get("upfilename").toString());
					}
				}
				map.put("picaddrList",arrayList );
				
				if (piclist != null && piclist.size() > 0) {
					Map picmap = (Map) piclist.get(0);
					map.put("picaddr", "http://"
							+ WebContant.GetKeyValue("CenterUrl")
							+ picmap.get("upadder").toString()
							+ picmap.get("upfilename").toString());
				} else {
					map.put("picaddr", "");
				}
				map.put("url", "http://" + url
						+ "/book/ticketdetail.action?providerbook.iscenicid="
						+ map.get("iscenicid"));
				try {
					Esbscenicareatab esbscenicareatab = (Esbscenicareatab)this.get(Esbscenicareatab.class,map.get("iscenicid"));
					map.put("strbookdescription", Tools.getStrByBlob(esbscenicareatab.getBookdescription()));
				}catch (Exception e){
					logger.error(e.getMessage(), e);
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaginationSupport getProviderList(String url,Integer pageSize,Integer cPage) {
		if(url==null || url.length()<0){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		StringBuffer sb = new StringBuffer("select distinct new map(esb.iscenicid as iscenicid,esb.szscenicname as szscenicname,esb.szgrade as szgrade,esb.szsimpleintroduction as szsimpleintroduction,esb.szbookdescription as szbookdescription,esb.szlasttime as szlasttime,esb.imaxdata as imaxdata,esb.szqjaddr as szqjaddr,esb.sznetaddr as sznetaddr,esb.szaddress as szaddress,esb.longitude as longitude,esb.szlasttime as szlasttime) from Esbscenicareatab esb,Edmtickettypetab pro,Edmcrowdkindpricetab prc where ");
		sb.append(" esb.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01' or spmcd='01')) and prc.ibusinessid=1 and esb.isjd=0 ");
		sb.append(" and esb.byisuse=1 and pro.byisuse=1 and esb.isjd=0 and prc.isnet=1 and prc.byisuse=1 and esb.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid ");
		System.out.println(sb.toString());
//		List list = find(sb.toString());
		//修改增加服务商分页信息,当pageSize为空时默认为10 cPage默认为1
		if(pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		if(cPage == null || cPage == 0) {
			pageSize = 1;
		}
		PaginationSupport ps = findPage(sb.toString(), pageSize, cPage, url);
		if(ps != null) {
		List list = ps.getItems();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				
			//	System.out.println(map.get("iscenicid"));
				// 根据服务商ID查找最低票价
				StringBuffer hsql = new StringBuffer("select distinct new map(prc.icrowdkindid as icrowdkindid,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid,pro.isequence as isequence,prc.listingprice as listingprice,pro.sztickettypename as sztickettypename,pro.itickettypeid as itickettypeid) from Edmtickettypetab pro, Edmcrowdkindpricetab prc where prc.startdata <= '"
								+ Tools.getDays()+ "' and prc.enddata >= '"+ Tools.getDate(Tools.getDays(), 1) + "' and pro.iscenicid="+ Long.parseLong(map.get("iscenicid").toString())
				                + " and pro.bycategorytype not in( '0013','120','119')  and  pro.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and pro.itickettypeid = prc.itickettypeid and (pro.int3 is null or pro.int3=0)");
				             hsql.append(" and prc.ibusinessid=1 ");
				             hsql.append("group by prc.icrowdkindid,pro.itickettypeid,pro.sztickettypename,prc.listingprice,prc.mactualsaleprice,prc.icrowdkindpriceid,pro.isequence");
				             hsql.append(" order by prc.mactualsaleprice asc");//pro.isequence desc,
			//	System.out.println(hsql);
				List list2 = this.find(hsql.toString());
				if (!list2.isEmpty()) {
					map.put("price", ((Map) list2.get(0)).get("mactualsaleprice"));
				}
				StringBuffer picstr = new StringBuffer(
						"select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
								+ new Long(map.get("iscenicid").toString())+ " and p.upid = u.upid and p.itickettypeid=0");
				List piclist = find(picstr.toString());
				
				
				ArrayList arrayList = new ArrayList();
				if (piclist != null && piclist.size() > 0){
					for (int j = 0; j < piclist.size(); j++) {
						Map picmap = (Map) piclist.get(j);
						arrayList.add("http://"
								+ url
								+ picmap.get("upadder").toString()
								+ picmap.get("upfilename").toString());
					}
				}
				map.put("picaddrList",arrayList );
				
				if (piclist != null && piclist.size() > 0) {
					Map picmap = (Map) piclist.get(0);
					map.put("picaddr", "http://"
							+ WebContant.GetKeyValue("CenterUrl")
							+ picmap.get("upadder").toString()
							+ picmap.get("upfilename").toString());
				} else {
					map.put("picaddr", "");
				}
				map.put("url", "http://" + url
						+ "/book/ticketdetail.action?providerbook.iscenicid="
						+ map.get("iscenicid"));
			}
		}
		}
		return ps;
	}
	private Map getProviderByid(long iscenicid,String url) {
		if(url==null || url.length()<1){
			url = WebContant.GetKeyValue("CenterUrl");
		}
		StringBuffer sb = new StringBuffer(
				"select new map(iscenicid as iscenicid,szscenicname as szscenicname,szgrade as szgrade,szsimpleintroduction as szsimpleintroduction,szbookdescription as szbookdescription,szlasttime as szlasttime,imaxdata as imaxdata,szqjaddr as szqjaddr,sznetaddr as sznetaddr,szaddress as szaddress,longitude as longitude,scenictype as scenictype) from Esbscenicareatab where isjd=0 and scenictype='01' and byisuse=1 and iscenicid="
						+ iscenicid + "");
		List list = find(sb.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				StringBuffer picstr = new StringBuffer("select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ new Long(map.get("iscenicid").toString()) + " and p.upid = u.upid ");
				List piclist = find(picstr.toString());
				if (piclist != null && piclist.size() > 0) {
					//System.out.println(piclist.size());
					Map picmap = (Map) piclist.get(0);
					map.put("picaddr", url + picmap.get("upadder").toString() + picmap.get("upfilename").toString());
				} else {
					map.put("picaddr", "");
				}
			}
			return (Map) list.get(0);
		} else {
			return null;
		}
	}

	public List getTicketList(String ibusinessid, long iscenicid, String groupno) {
		String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,ticket.szmemo as szmemo)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where ticket.issale=0 and provider.iscenicid="
				+ iscenicid
				+ " and  price.ibusinessid="
				+ ibusinessid
				+ " and price.note1='"
				+ groupno
				+ "' and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and systp='1') and price.startdata<='"
				+ Tools.getDays()
				+ "' and price.enddata>='"
				+ Tools.getDays()
				+ "' group by ticket.szmemo,ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.bycategorytype desc, ticket.issale desc,ticket.isequence desc";
		return find(hql);
	}

	public Map getTicket(String ibusinessid, long iscenicid, String groupno, long iticketid) {
		String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,ticket.szmemo as szmemo)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where ticket.issale=0 and provider.iscenicid="
				+ iscenicid
				+ " and  price.ibusinessid="
				+ ibusinessid
				+ " and price.note1='"
				+ groupno
				+ "' and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and systp='1') and price.startdata<='"
				+ Tools.getDays()
				+ "' and price.enddata>='"
				+ Tools.getDays()
				+ "' group by ticket.szmemo,ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday order by ticket.bycategorytype desc, ticket.issale desc,ticket.isequence desc";
		List list = find(hql);
		System.out.println("pricelist=====>" + hql);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		} else {
			return null;
		}
	}

	public List getPriceListByDate(long itickettypeid, String ibusinessid, String date, String groupno) {
		String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid,ticket.itickettypeid as itickettypeid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
				+ date
				+ "' and price.enddata>='"
				+ date
				+ "'  and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
				+ ibusinessid
				+ "  and ticket.byisuse=1 and  humantype.byisuse=1  and  price.isnet=1 and  ticket.itickettypeid="
				+ itickettypeid
				+ " and price.note1='"
				+ groupno
				+ "' order by price.icrowdkindpriceid";
		//System.out.println("pricelist=====>" + hql);
		return find(hql);
	}

	public List getPriceListByDate(long itickettypeid, String ibusinessid, String date, String groupno, long icrowkindid) {
		String hql = "select new map(humantype.szcrowdkindname as szcrowdkindname,price.icrowdkindpriceid as icrowdkindpriceid,price.ipeoplenumrange as ipeoplenumrange,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,price.icrowdkindid as icrowdkindid,ticket.itickettypeid as itickettypeid) from Edmcrowdkindpricetab price,Edpcrowdkindtab humantype,Edmtickettypetab ticket where  price.startdata<='"
				+ date
				+ "' and price.enddata>='"
				+ date
				+ "'  and  price.icrowdkindid=humantype.icrowdkindid and price.byisuse=1 and price.itickettypeid=ticket.itickettypeid and price.ibusinessid="
				+ ibusinessid
				+ "  and ticket.byisuse=1 and  humantype.byisuse=1  and  price.isnet=1 and  ticket.itickettypeid="
				+ itickettypeid
				+ " and price.note1='"
				+ groupno
				+ "' "
				+ " and  humantype.icrowdkindid=" + icrowkindid + "" + " order by price.icrowdkindpriceid";
		//System.out.println("pricelist=====>" + hql);
		return find(hql);
	}

	/**
	 * 根据价格找到子票
	 */
	public List getSonTicket(long icrowdkindpriceid) {
		StringBuffer hql = new StringBuffer(
				"select new map(chai.id.iticketcomposepriceid as iticketcomposepriceid, chai.id.icrowdkindpriceid as icrowdkindpriceid ,chai.itickettypeid as itickettypeid,chai.mactualsaleprice as mactualsaleprice,chai.numb as numb) from Edmticketcomposepricetab chai where chai.id.icrowdkindpriceid="
						+ icrowdkindpriceid + "");
		return find(hql.toString());
	}

	public Map saveOrder(BaseModel model, String orid,String urls) {
		Map returnmap = new HashMap();
		try {
			Map user = findUser(model.getUsid(), model.getPwd());
			MOrder morder = new MOrder();
			morder.setOrid(orid);
			morder.setOrtp("01");// 预订单
			morder.setUsid(model.getUsid());
			morder.setOrda(Tools.getDays());
			morder.setOrti(Tools.getNowTime());
			morder.setIsjl(0l);// 非奖励
			morder.setDdzt("02");
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
			morder.setNotea("02");
			morder.setMont(0.0);
			morder.setZfmont(0.0);
			morder.setIsallcp(0l);
//			morder.setOrdersource("a_ds");
			save(morder);// 保存morder
			Map node = model.getNodes().get(0);
			String iscenicid = node.get("iscenicid").toString();
			Map provider = getProviderByid(Long.parseLong(iscenicid),urls);
			String lpr = node.get("holdername").toString();
			String telphone = node.get("telphone").toString();
			String printpwd = node.get("printpwd").toString();
			TOrder torder = new TOrder();
			torder.setDtstartdate(Tools.getDays());
			torder.setDtenddate(Tools.getDays());
			torder.setDyusid("");
			torder.setOrnm(lpr);
			torder.setOrzj("");
			torder.setOrhm(node.get("cardno").toString());
			torder.setOrph(telphone);
			torder.setNotea(printpwd);
			torder.setMont(0.0);
			torder.setZfmont(0.0);
			torder.setIregionalid(1l);
			torder.setId(new TOrderId(orid, Long.parseLong(iscenicid)));
			torder.setDdzt(morder.getDdzt());
			torder.setUsid(model.getUsid());
			String scenictype = (String) provider.get("scenictype");
			if ("01".equals(scenictype) || "02".equals(scenictype)
    				|| "03".equals(scenictype) || "04".equals(scenictype)
    				|| "05".equals(scenictype)) {
				torder.setOrfl("02");// 定单分类(票务)
    		} else if ("06".equals(scenictype)) {
    			torder.setOrfl("01");// 定单分类（酒店）
    		} else if ("07".equals(scenictype)) {
    			torder.setOrfl("03");// 定单分类（线路）
    		} else if ("08".equals(scenictype)) {
    			torder.setOrfl("04");// 定单分类（商品）
    		}else{
    			//默认值
    			torder.setOrfl("02");// 定单分类(票务)
    		}
			torder.setIbusinessid(Long.parseLong(user.get("ibusinessid").toString()));
			torder.setSzscenicname(provider.get("szscenicname").toString());
			torder.setScenictype(provider.get("scenictype").toString());
			torder.setYhamnt(0.0);
			torder.setIsa(0l);
			torder.setIsb(0l);
			torder.setIsc(0l);
			torder.setIsjfjf(morder.getIsjl());
			torder.setIsd(0l);
			torder.setIse(0l);
			torder.setIsf(0l);
			torder.setIsg(0l);
			torder.setIsh(0l);
			torder.setIsi(0l);
			torder.setIsj(0l);
			save(torder);// 保存torder
			YOrder yorder = new YOrder();
			yorder.setId(new YOrderId(morder.getOrid(), torder.getId().getIscenicid()));
			yorder.setZfmont(0.0);
			yorder.setMont(0.0);
			yorder.setDtenddate(Tools.getDays());
			yorder.setDtstartdate(Tools.getDays());
			yorder.setScenictype(torder.getScenictype());
			yorder.setUsid(torder.getUsid());
			yorder.setDdzt(torder.getDdzt());
			yorder.setIbusinessid(torder.getIbusinessid());
			yorder.setTdlx(user.get("ttlb").toString());
			save(yorder);// 保存yorder
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String mindate = "";
			String maxdate = "";
			List<Map> tickets = model.getNodes();
			for (int i = 0; i < tickets.size(); i++) {
				Map ticket = tickets.get(i);
				TOrderlist torderlist = new TOrderlist();
				torderlist.setId(new TOrderlistId(new Long(i + 1), orid, Long.parseLong(iscenicid)));
				torderlist.setIscenicid(iscenicid);
				torderlist.setItickettypeid(Long.parseLong(ticket.get("ticketid").toString()));
				Map price = (Map) getPriceListByDate(Long.parseLong(ticket.get("ticketid").toString()), user.get("ibusinessid").toString(), ticket.get("date").toString(),
						user.get("groupno").toString(), Long.parseLong(ticket.get("icrowdkindid").toString())).get(0);
				Map ticketInfo = getTicket(user.get("ibusinessid").toString(), Long.parseLong(iscenicid), user.get("groupno").toString(), Long.parseLong(ticket.get("ticketid").toString()));
				torderlist.setIcrowdkindpriceid(Long.parseLong(price.get("icrowdkindpriceid").toString()));
				torderlist.setPric(Double.parseDouble(price.get("mactualsaleprice").toString()));
				torderlist.setDtstartdate(ticket.get("date").toString());
				torderlist.setDtenddate(Tools.getDate(ticket.get("date").toString(), Integer.parseInt(ticketInfo.get("validityday").toString()) - 1));
				torderlist.setNumb(Long.parseLong(ticket.get("numb").toString()));
				torderlist.setIoffersschemeid(0l);
				torderlist.setYhnumb(0l);
				torderlist.setYhamnt(0.0);
				torderlist.setIcrowdkindid(Long.parseLong(ticket.get("icrowdkindid").toString()));
				torderlist.setSztickettypename(ticketInfo.get("ticketname").toString());
				torderlist.setAmnt(torderlist.getPric() * torderlist.getNumb().intValue());
				torderlist.setIsa(0l);
				torderlist.setIsb(0l);
				torderlist.setIsc(0l);
				torderlist.setIsd(0l);
				torderlist.setIse(0l);
				torderlist.setIsf(0l);
				torderlist.setIsg(0l);
				torderlist.setIsh(0l);
				torderlist.setIsi(0l);
				torderlist.setIsj(0l);
				save(torderlist);// 保存torderlist
				YOrderlist ylist = new YOrderlist();
				ylist.setId(new YOrderlistId(torderlist.getId().getOrderlistid(), torderlist.getId().getOrid(), torderlist.getId().getIscenicid()));
				ylist.setItickettypeid(torderlist.getItickettypeid());
				ylist.setIcrowdkindpriceid(new Long(torderlist.getIcrowdkindpriceid()));
				ylist.setIcrowdkindid(new Long(torderlist.getIcrowdkindid()));
				ylist.setDtstartdate(torderlist.getDtstartdate());
				ylist.setDtenddate(torderlist.getDtenddate());
				ylist.setPric(torderlist.getPric());
				ylist.setNumb(torderlist.getNumb());
				ylist.setAmnt(torderlist.getAmnt());
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
				save(ylist);// 保存yorderlist
				if (mindate.equals("")) {
					mindate = torderlist.getDtstartdate();
				} else {
					Date d1 = sdf.parse(mindate);
					Date d2 = sdf.parse(torderlist.getDtenddate());
					if (d2.before(d1)) {
						mindate = torderlist.getDtenddate();
					}
				}
				if (maxdate.equals("")) {
					maxdate = torderlist.getDtstartdate();
				} else {
					Date d1 = sdf.parse(maxdate);
					Date d2 = sdf.parse(torderlist.getDtenddate());
					if (d1.before(d2)) {
						mindate = torderlist.getDtenddate();
					}
				}
				// 组装子票
				List sons = getSonTicket(torderlist.getIcrowdkindpriceid());
				for (int x = 0; x < sons.size(); x++) {
					Map sonticket = (Map) sons.get(x);
					TZorderlist zorderlist = new TZorderlist();
					zorderlist.setId(new TZorderlistId(new Long(x + 1), torderlist.getId().getOrderlistid(), orid, Long.parseLong(iscenicid)));
					zorderlist.setItickettypeid(torderlist.getItickettypeid());// 主票ID
					zorderlist.setIztickettypeid(Long.parseLong(sonticket.get("itickettypeid").toString()));// 子票ID
					zorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());// 票价ID
					zorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());// 人群种类
					zorderlist.setDtstartdate(torderlist.getDtstartdate() + " " + "00:00:00");
					Map sticketInfo = getTicket(user.get("ibusinessid").toString(), Long.parseLong(iscenicid), user.get("groupno").toString(),
							Long.parseLong(sonticket.get("itickettypeid").toString()));
					zorderlist.setDtenddate(Tools.getDate(torderlist.getDtstartdate(), Integer.parseInt(sticketInfo.get("validityday").toString()) - 1) + " " + "23:59:59");
					zorderlist.setTripid(new Long(0));
					zorderlist.setIvenueid(new Long(0));
					zorderlist.setIvenueareaid(new Long(0));
					zorderlist.setIvenueseatsid(new Long(0));
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
					zorderlist.setZnumb(torderlist.getNumb());
					zorderlist.setZpric(Double.parseDouble(sonticket.get("mactualsaleprice").toString()));
					zorderlist.setZamnt(zorderlist.getZpric() * zorderlist.getZnumb());
					zorderlist.setZyhamnt(0.0);
					zorderlist.setZyhnumb(new Long(0));
					save(zorderlist);// 保存tzorderlist
					YZorderlist y_zordderlist = new YZorderlist();
					y_zordderlist.setId(new YZorderlistId(zorderlist.getId().getZorderlistid(), zorderlist.getId().getOrderlistid(), zorderlist.getId().getOrid(), zorderlist.getId().getIscenicid()));
					y_zordderlist.setIcrowdkindpriceid(zorderlist.getIcrowdkindpriceid());
					y_zordderlist.setIcrowdkindid(zorderlist.getIcrowdkindid());
					y_zordderlist.setItickettypeid(zorderlist.getItickettypeid());
					y_zordderlist.setIztickettypeid(zorderlist.getIztickettypeid());
					y_zordderlist.setDtstartdate(zorderlist.getDtstartdate());
					y_zordderlist.setDtenddate(zorderlist.getDtenddate());
					y_zordderlist.setTripid(zorderlist.getTripid());
					y_zordderlist.setIvenueid(zorderlist.getIvenueid());
					y_zordderlist.setIvenueareaid(zorderlist.getIvenueareaid());
					y_zordderlist.setIvenueseatsid(zorderlist.getIvenueseatsid());
					y_zordderlist.setZpric(zorderlist.getZpric());
					y_zordderlist.setZnumb(zorderlist.getZnumb());
					y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
					y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
					y_zordderlist.setZamnt(zorderlist.getZamnt());
					y_zordderlist.setJsprice(zorderlist.getJsprice());
					y_zordderlist.setIsa(0l);
					y_zordderlist.setIsb(0l);
					y_zordderlist.setIsc(0l);
					y_zordderlist.setIsd(0l);
					y_zordderlist.setIse(0l);
					y_zordderlist.setIsf(0l);
					y_zordderlist.setIsg(0l);
					y_zordderlist.setIsh(0l);
					y_zordderlist.setIsi(0l);
					y_zordderlist.setIsj(0l);
					save(y_zordderlist);// 保存yorderlist
				}
				morder.setMont(morder.getMont() + torderlist.getAmnt());
				morder.setZfmont(morder.getZfmont() + torderlist.getAmnt());
				torder.setMont(torder.getMont() + torderlist.getAmnt());
				torder.setZfmont(torder.getZfmont() + torderlist.getAmnt());
				torder.setDtstartdate(mindate);
				torder.setDtenddate(maxdate);
				yorder.setMont(yorder.getMont() + ylist.getAmnt());
				yorder.setZfmont(yorder.getZfmont() + ylist.getAmnt());
				yorder.setDtstartdate(mindate);
				yorder.setDtenddate(maxdate);
			}
			Vipbalance balance = (Vipbalance) get(Vipbalance.class, model.getUsid());
			if (balance.getPoint() < torder.getZfmont()) {
				returnmap.put("errtp", AndroidStateCode.BOOK_BALANCE);
				returnmap.put("status", false);
			} else {
				morder.setStdt(mindate);
				yorder.setMont(torder.getMont());
				yorder.setYhamnt(torder.getYhamnt());
				yorder.setZfmont(torder.getMont() - torder.getYhamnt());
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
				morder.setNoteb("1");
				update(morder);// 更新morder
				update(torder);// 更新torder
				update(yorder);// 更新yorder
				// TODO 扣除预付款 增加消费记录
				String md51 = orid + model.getUsid() + morder.getZfmont() + "ectripmb";
				EctripMd5 em = new EctripMd5(md51);
				em.calc();
				String ls_md5 = em.toString();
				String url = "http://pay" + WebContant.GetKeyValue("rootdomain") + "/userusidzhifu.action?orid=" + orid + "&usid=" + model.getUsid() + "&amount=" + morder.getZfmont() + "&md5str=" + ls_md5;
				//System.out.println(url);
				HttpClient httpclient = new HttpClient();
				HttpMethod method = new GetMethod(url);
				httpclient.executeMethod(method);
				//System.out.println(method.getStatusLine());
				//System.out.println(method.getResponseBodyAsString());
				returnmap.put("orid", orid);
				returnmap.put("mont", morder.getZfmont());
				returnmap.put("status", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnmap.put("status", false);
			returnmap.put("errtp", AndroidStateCode.BOOK_FAILTURE);
		}
		return returnmap;
	}

	public List getOridList(String date, String usid) {
		String hql = "select new map(m.orid as orid,m.zfmont as zfmont,m.orda as orda,m.orti as orti,t.id.iscenicid as  iscenicid,e.szscenicname as szscenicname) from MOrder m,TOrder t,Esbscenicareatab e where m.usid='"
				+ usid + "'  and m.stdt='" + date + "' and m.orid=t.id.orid and t.id.iscenicid=e.iscenicid order by m.orda,m.orti";
		System.out.println(hql);
		return find(hql);
	}

	public List orderDetail(String orid, String iscenicid) {
		MOrder morder = (MOrder) get(MOrder.class, orid);
		TOrder torder = (TOrder) get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
		String hsql = "select new map(t.itickettypeid as itickettypeid,t.icrowdkindid as icrowdkindid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.yhnumb as yhnumb,t.amnt as amnt,t.yhamnt as yhamnt,t.ish as ish,t.isi as isi,e.sztickettypename as sztickettypename,c.szcrowdkindname as szcrowdkindname) from TOrderlist t,Edmtickettypetab e ,Edpcrowdkindtab c where orid='"
				+ orid + "' and t.itickettypeid=e.itickettypeid and t.icrowdkindid=c.icrowdkindid";
		List list = find(hsql);
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			map.put("zfmont", torder.getZfmont());
			map.put("iscenicid", iscenicid);
			map.put("usid", morder.getUsid());
			map.put("holdername", torder.getOrnm());
			map.put("telphone", torder.getOrph());
			map.put("cardno", torder.getOrhm());
			map.put("orid", morder.getOrid());
		}
		return list;
	}
	
	public List showAllArticles(Long cmid,int num){
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(amid as amid,cmid as cmid,amtopicf as amtopicf,amdesc as amdesc) from Articlemanagertab art where art.byisuse = 1");
		if(cmid!=null&&cmid!=0){
			sql.append(" and art.cmid ="+cmid);
		}
		sql.append(" order by art.dtmakedate desc");
		List list = this.findTopNumb(sql.toString(), num);
		return list;
	}
	
	
	public Map viewArticle(Long amid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select new map(am.amid as amid,am.amtopicf as amtopicf,am.amtopics as amtopics,am.amdesc as amdesc,am.amnote as amnote,am.byisuse as byisuse,am.dtmakedate as dtmakedate,am.cmid as cmid) from Articlemanagertab am,Columnmanagertab cm where am.cmid = cm.cmid");
		sql.append(" and am.amid="+amid);
		List list = this.find(sql.toString());
		if(list!=null&&list.size()>0){
			return (Map) list.get(0);
		}else{
			return null;
		}	
	}

	public String getTicketPicAdder(Long itickettypeid) {
		String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
				+ itickettypeid +"";
		List piclist = find(hsqls);
		if(piclist!=null&&piclist.size()>0){
			Map map=(Map) piclist.get(0);
			return map.get("upadder").toString()+map.get("upfilename");
		}else{
			return "";
		}
	}
	
	public String saveZhuCeUser(String revmbno, String password,
			String password2, String random, boolean flag) {

		List<Vcitable> list = this.find("from Vcitable where username='"+ revmbno + "'");
		StringBuffer result = new StringBuffer();
		Vcitable vcitable = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!flag) {
			//如果不是手机号，就不发短信了；
			if ( revmbno.matches("[0-9]") )  
				//如果手机号才发短信，不是将不发短信
			{
				Mbmessage mbmessage = new Mbmessage();
//				IMbMessageService mbmessageService = new MbMessageService();
				// Long seq = this.getMaxPk("seq", "Mbmessage");
				Long seq = this.getMaxPk("seq", new String[] {}, new String[] {},
						"Mbmessage");
				mbmessage.setSeq(seq + 1);
				mbmessage.setRevmbno(revmbno);
				mbmessage.setDtime(Tools.getNowString());
				mbmessage.setIsok(0);
				mbmessage.setNote("您好！感谢您的预订，您的初始用户名为：" + revmbno + ",初始密码为："
						+ password);
				mbmessage.setQuantity(1);
				// mbmessageService.insertMessage(mbmessage);
				this.save(mbmessage);
				return createCustom(revmbno, password, password2);
			}
		}
		if (list.size() > 0 || !list.isEmpty()) {// 验证码是否发送成功了
			vcitable = list.get(0);
			if (!vcitable.getCode().equals(random)) {// 验证激活码不正确
				return result.append(
						"<ectrip><menu><result>4</result></menu></ectrip>")
						.toString();
			} else {
				Long db_time = vcitable.getUsetime();
				String make_time = vcitable.getDtmakedate();

				try {
					//System.out.println(Tools.getNowString() + "," + db_time);
					Long date = df.parse(Tools.getNowString()).getTime()
							- df.parse(make_time).getTime();
					if (date / 1000 >= db_time) {// 判断验证码是否超时 30分钟
						return result
								.append(
										"<ectrip><menu><result>6</result></menu></ectrip>")
								.toString();// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
					}
				} catch (Exception e) {
					throw new RuntimeException("时间格式不对  yyyy-MM-dd HH:mm:ss");
				}
			}
			if (!password.equals(password2)) {// 两次密码不一致
				return result.append(
						"<ectrip><menu><result>5</result></menu></ectrip>")
						.toString();// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
			}
			Pattern p = Pattern.compile("^\\S+$"); // 匹配任何非空白字符
			boolean c = p.matcher(password).matches();// 检查是否有空白字符
			if (!isMobile(revmbno) || "".equals(revmbno) || revmbno == null) {// 手机号验证
				return result.append(
						"<ectrip><menu><result>3</result></menu></ectrip>")
						.toString();// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
			} else if (password == null || "".equals(password) || !c) {
				return result.append(
						"<ectrip><menu><result>3</result></menu></ectrip>")
						.toString();// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
			}
			List<Custom> listc = this.find("from Custom where mobile='"
					+ revmbno + "'");
			if (listc.size() > 0 || !listc.isEmpty()) {// 验证手机号是否已经注册过了
				return result.append(
						"<ectrip><menu><result>2</result></menu></ectrip>")
						.toString();
			} else {
				// 注册
				String  regiestResult=createCustom(revmbno, password, password2);
				/*return result.append(
						"<ectrip><menu><result>1</result></menu></ectrip>")
						.toString();*/
				return regiestResult;
			}
		}
		return result.append(
				"<ectrip><menu><result>-1</result></menu></ectrip>").toString();
	}
	
	private String createCustom(String revmbno, String password,
			String password2) {
		try {
			String note9 = this.getCustomvipno();
			// 注册
			Custom custom = new Custom();
			Customlog customlog = new Customlog();
			UUID uuid = UUID.randomUUID();
			String usid = uuid.toString().replace("-", "");
			custom.setUsid("P" + ShortText(usid)[0]);
			custom.setIbusinessid(1L);
			custom.setNote3("0");
			custom.setNote4("1");
			custom.setNote5("02");
			custom.setLgtp("01");
			custom.setPassword(Tools.md5Encode(password));
			custom.setPassword2(Tools.md5Encode(password2));
			custom.setUsqx("01110000000000000000");
			custom.setTtlb("00");
			custom.setLdate(Tools.getTodayString());// 注册日期
			custom.setLpdate(Tools.getTodayString());// 最后修改密码时间
			custom.setLmdate(Tools.getTodayString());// 用户最后修改注册信息时间
			custom.setLogtimes("1");// 共登录次数
			custom.setLastdate(Tools.getTodayString());// 上次登录时间
			custom.setAutofapiao(0);
			custom.setUstp("01");
			custom.setMobile(revmbno);
			custom.setSusid("");
			custom.setUsdj(0);// 用户等级
            custom.setNote9(note9);
			// customlog日志
			customlog.setUsid(custom.getUsid());
			customlog.setStlg("0081");
			customlog.setBrief("手机用户注册: 用户名：" + custom.getUsid() + "手机号："
					+ custom.getMobile());

			if (custom.getLgtp().equals("01")) {// 散客
				custom.setStatus("01");
				custom.setIbusinessid(1L);
				custom.setNote2("0000");
				customlog.setNote("前台用户注册：增加用户: " + custom.getUsid() + "，注册类别："
						+ custom.getLgtp());
			}
			// ICustomService cusService = new CustomService();
			// // 增加
			// cusService.insertCustom(custom, customlog);

			this.save(custom);
			// B2BCustomService b2bCustomService = new B2BCustomService();
			// boolean b = b2bCustomService.addCustom(custom, ""
			// + custom.getPassword());
			// if (b) {
			// custom.setInote10(1);
			// this.update(custom);
			// } else {
			// custom.setInote10(0);
			// this.update(custom);
			// }
			// 李进修改，增加注册时赠送积分
			addUserJf(custom.getUsid(), "04", new Long(1));

			customlog.setLogdatetime(Tools.getDayTimes());
			Long sysid = getMaxPk("sysid", "Customlog");
			customlog.setSysid(sysid + 1);
			this.save(customlog);

			/* 增加VIPBALANCE */
			Vipbalance vipbalance = new Vipbalance();
			vipbalance.setUsid(custom.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(0.0);
			this.save(vipbalance);
			return "<ectrip><menu><result>1</result><user>"+custom.getUsid()+"</user></menu></ectrip>";
		} catch (Exception e) {
			return "<ectrip><menu><result>异常</result></menu></ectrip>";
		}
	}
	
	/**
	 * 
	 * Describe:生成随机USID
	 * 
	 * @author:huangdonyu
	 * @param string
	 * @return return:String[] Date:2014-4-16
	 */
	private String[] ShortText(String string) {
		String[] chars = new String[] { // 要使用生成URL的字符
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		String hex = Tools.md5Encode(string);
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;
		String[] ShortStr = new String[4];

		for (int i = 0; i < subHexLen; i++) {
			String outChars = "";
			int j = i + 1;
			String subHex = hex.substring(i * 8, j * 8);
			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			for (int k = 0; k < 6; k++) {
				int index = (int) (Long.valueOf("0000003D", 16) & idx);
				outChars += chars[index];
				idx = idx >> 5;
			}
			ShortStr[i] = outChars;
		}

		return ShortStr;
	}
	
	private boolean addUserJf(String usid, String jfcode, Long amount) {

		String sql = " from Userjfset ut where ut.jfcode = '" + jfcode
				+ "' and ut.isvalid = 1";
		String hsql = " from Userjf uf where uf.usid = '" + usid + "'";
		List jfsetList = this.find(sql); // 查看积分规则
		List jflist = this.find(hsql); // 查看积分用户是否存在
		if (jfsetList != null && jfsetList.size() > 0) { // 判断积分规则是否存在
			Userjfset userjfset = (Userjfset) jfsetList.get(0);

			Long points = amount / Long.parseLong(userjfset.getBasepoint()) * Long.parseLong(userjfset.getPoints()); // 根据消费数量计算应增加的积分数量
			Userjflist userjflist = new Userjflist();
			Long maxid = this.getMaxPk("seq", "Userjflist");
			userjflist.setSeq(maxid + 1);
			userjflist.setUsid(usid);
			userjflist.setJflb(1L);
			userjflist.setJfgz(userjfset.getJfgzid());
			userjflist.setSdate(Tools.getDayTimes());
			userjflist.setPoints(points);
			userjflist.setDtmakedate(Tools.getDayTimes());
			this.save(userjflist); // 保存积分明细表

			if (jflist != null && jflist.size() > 0) { // 判断积分用户是否存在
				Userjf userjf = (Userjf) jflist.get(0);
				userjf.setPoints(userjf.getPoints() + points);
				this.update(userjf); // 更新用户的积分
			} else {
				Userjf userjf = new Userjf();
				Long id = this.getMaxPk("seq", "Userjf");
				userjf.setSeq(id + 1);
				userjf.setUsid(usid);
				userjf.setPoints(points);
				userjf.setIsvalid(1L);
				userjf.setDtmakedate(Tools.getDayTimes());
				this.save(userjf); // 保存积分用户
			}
			return true;
		} else {
			return false; // 积分规则不存在
		}

	}
	
	public String getCustomvipno() throws Exception{
		String maxorno = "";
		Long no = this.getSequenceId("customvipno_sequence");
		if(no==null || no == 0L){
			maxorno = "1";
		}else{
			maxorno = no.toString();
		}
		maxorno = "000000000".substring(maxorno.length())+maxorno;
		return maxorno;
	}
	
	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	private boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8,9][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	
	public String sendMessage(String revmbno) {
		String result = "";
		try {
		 System.out.println(revmbno);
         // "<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><ns1:getMessInfoResponse soapenv:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:ns1=\"http://w.jowong.com/\"><ns2:result xmlns:ns2=\"http://www.w3.org/2003/05/soap-rpc\">getMessInfoReturn</ns2:result><getMessInfoReturn xsi:type=\"ns3:string\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		String date = Tools.getNowString();
		List<Custom> listc = this.find("from Custom where mobile='" + revmbno
				+ "'");
		if (listc.size() > 0 || !listc.isEmpty()) {// 验证手机号是否已经注册过了
			return result="<ectrip><menu><result>2</result></menu></ectrip>";// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
		}
		if (!isMobile(revmbno) || "".equals(revmbno) || revmbno == null) {
			return result = "<ectrip><menu><result>3</result></menu></ectrip>";// </getMessInfoReturn></ns1:getMessInfoResponse></soapenv:Body></soapenv:Envelope>").toString();
		}
		Mbmessage mbmessage = null;
		Vcitable vcitable = null;
		String random = getFixLenthString(4);
		List<Mbmessage> list = this.find("from Mbmessage where revmbno='"+ revmbno + "'");
		if (list.size() > 0 || !list.isEmpty()) {
			mbmessage = list.get(0);
			// System.out.println(list.get(0) + "," + mbmessage.getSeq() + ","
			// + list.isEmpty());
		}
		List<Vcitable> listvc = this.find("from Vcitable where usid='"+ revmbno + "'");
		if (listvc.size() > 0 || !listvc.isEmpty()) {
			vcitable = listvc.get(0);
			// System.out.println(list.get(0) + "," + mbmessage.getSeq() + ","
			// + list.isEmpty());
		}
		//IMbMessageDAO mbmessageDao = new MbMessageDAO();
		
		String hql = " from Contmessage where controlpoin='0010' and byisuse=1 and inote1 is null";
		StringBuffer info=new StringBuffer();
		List contlist = this.find(hql);
		if (contlist != null && contlist.size() > 0) {
		    Contmessage templates = (Contmessage) contlist.get(0);
		    String note=templates.getTemplates().replace("@", random);
		    info.append(note);
		    
		}else{
			info.append("欢迎您注册成为手机会员，您的验证码是：" + random + "，请保存好验证码！并在30分钟内使用！");
		}
		
		if (mbmessage != null) {
			 System.out.println("<<<<<<<<<<<<<<<<<<<<重发验证码" + random+">>>>>>>>>>>>>>>>>>>");
			String ran = random;
			mbmessage.setRevmbno(revmbno);
			mbmessage.setIsok(0);// 未发送
			mbmessage.setNote(info.toString());
			mbmessage.setDtime(date);
			mbmessage.setQuantity(1);
			// System.out.println(mbmessageService);
			this.update(mbmessage);

			result = "<ectrip><menu><result>1</result></menu></ectrip>";
		} else {
			// System.out.println("发送验证码" + random);
			mbmessage = new Mbmessage();
			Long seq = this.getMaxPk("seq", "Mbmessage");
			mbmessage.setSeq(seq + 1);
			mbmessage.setRevmbno(revmbno);
			mbmessage.setDtime(date);
			mbmessage.setIsok(0);
			mbmessage.setNote(info.toString());
			mbmessage.setQuantity(1);
			this.save(mbmessage);

			result = "<ectrip><menu><result>1</result></menu></ectrip>";
		}
		// 保存验证码信息
		if (vcitable != null) {
			vcitable.setCode(random);
			vcitable.setDtmakedate(Tools.getDayTimes());
			vcitable.setType("02");
			vcitable.setUsetime(30 * 60L);
			vcitable.setUsername(revmbno);
			vcitable.setDtmakedate(Tools.getNowString());
			vcitable.setUsid(revmbno);
			this.update(vcitable);
		} else {
			vcitable = new Vcitable();
			vcitable.setCode(random);
			vcitable.setDtmakedate(Tools.getDayTimes());
			vcitable.setType("02");
			vcitable.setUsetime(30 * 60L);
			vcitable.setUsername(revmbno);
			vcitable.setDtmakedate(Tools.getNowString());
			vcitable.setUsid(revmbno);
			this.save(vcitable);
		}
		
//		SendDao sendDao = (SendDao) SpringUtil.getBean("sendDao");
		
//			sendDao.DbSendInfo();
			sysparServiceApi.DbSendInfo();
		} catch (Exception e) {
			e.printStackTrace();
			result = "<ectrip><menu><result>4</result></menu></ectrip>";
		}
		return result;

	}
	
	/*
	 * 生成激活码 返回长度为【strLength】的随机数，在前面补0
	 */
	private String getFixLenthString(int strLength) {

		Random rm = new Random();

		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);

		// 返回固定的长度的随机数
		return fixLenthString.substring(1, strLength + 1);
	}
	
	// * Describe:修改对应服务商图片
	// * @author huangdonyu
	public List productList2(String usid, String scenicid, String datetime,String url) {
		if(url==null||url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		String lgtp = "";
		String note = "0000";

		StringBuffer hsql = new StringBuffer(
				"select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as szcrowdkindname,prc.icrowdkindid as icrowdkindid,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prc.note1 as note1,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Edmcrowdkindpricetab edm,Sysparv5 s where prc.startdata <= '"
						+ datetime+ "' and prc.enddata >= '"+ datetime
						+ "' and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in( '0004','0013','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid and prd.itickettypeid = edm.itickettypeid and prc.note4='01' and prd.note2='01' and prc.note1='0000' "
						+ " and prd.iscenicid="+ Long.parseLong(scenicid));
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			hsql.append(" and prc.ibusinessid=2 ");
		} else {
			hsql.append(" and prc.ibusinessid=1 ");
		}
		hsql.append(" order by s.pmvc asc ");
		System.out.println(hsql);
		List list = this.find(hsql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				// 产品图库
				String hsqls = "select new map(up.upfilename as upfilename,up.upadder as upadder,pic.iscenicid as iscenicid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.iscenicid="
						+ Long.parseLong(scenicid);
				List prodlist = this.find(hsqls);
				if (prodlist != null && prodlist.size() > 0) {
					Map prodmap = (Map) prodlist.get(0);
					map.put("prodaddr", url
							+ prodmap.get("upadder").toString()
							+ prodmap.get("upfilename").toString());
				} else {
					map.put("prodaddr", null);
				}
			}
			return list;
		} else {
			return null;
		}
    }
	
	public List searchTicketByProid(Long iscenicid, String rzti,
			Long ibusinessid,String url) throws Exception {
		if(url==null||url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class,
				iscenicid);
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// 判断日期是否超过当天最晚预定
			int hour = time.compareTo(esb.getSzlasttime());
			if (hour >= 0) {// 大于等于0表示。超过了
				rzti = Tools.getDate(rzti, 1);
			}
		}
		//服务商门票列表
		hsql2.append("select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as pmva,prd.itickettypeid as itickettypeid," +
				"prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype," +
				"prd.isequence as isequence,prc.note1 as note1,prc.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname," +
				"prc.listingprice as listingprice,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid,prc.ipeoplenumrange as ipeoplenumrange) " +
				"from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s,Edpcrowdkindtab c " +
				"where prd.iscenicid="
				+ iscenicid
				+ " and prc.startdata<='"
				+ rzti
				+ "' and prc.enddata>='"
				+ rzti
				+ "' and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not " +
				"in( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1 " +
				"and prd.itickettypeid = prc.itickettypeid and prc.icrowdkindid=c.icrowdkindid");

		hsql2.append(" and prc.ibusinessid=" + ibusinessid+" and (prd.int3 is null or prd.int3=0)");
		hsql2.append(" and (prc.note3 !='1' or prc.note3 is null)");//旅游卡门票不显示
		
		//hsql2.append(" group by prd.sztickettypename");
		hsql2.append(" order by prd.sztickettypename,pmvc, prd.isequence desc");
		 System.out.println(hsql2.toString());
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();
		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Map map = (Map) productList.get(i);
				// 产品图库
				String hsqls = "select new map(up.upfilename as upfilename,up.upadder as upadder,pic.iscenicid as iscenicid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.iscenicid="+iscenicid;
				List prodlist = this.find(hsqls);
				// 产品图库
				if (prodlist != null && prodlist.size() > 0) {
					Map prodmap = (Map) prodlist.get(0);
					map.put("prodaddr", url
							+ prodmap.get("upadder").toString()
							+ prodmap.get("upfilename").toString());
				} else {
					map.put("prodaddr", null);
				}
				Date date1 = new Date();
				TimeZone tZone1 = TimeZone.getTimeZone("Asia/Shanghai");
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				format.setTimeZone(tZone);
				String time1 = format.format(date);
				//线下售票数量： 获取当前产品今日售出票数
//				String numSql = "SELECT new map(NVL(SUM(sd.iticketnum),0) as num) FROM Stssalesvouchertab s,Stssalessettlementtab st,Stssalesvoucherdetailstab sd\r\n" +
//						"WHERE\r\n" +
//						"SUBSTR(s.dtmakedate,1,10)='+time1+'" +
//						"AND s.id.isalesvoucherid=st.id.isalesvoucherid\r\n" +
//						"AND s.id.iticketstationid=st.id.iticketstationid\r\n" +
//						"AND s.id.isalesvoucherid=sd.id.isalesvoucherid\r\n" +
//						"AND s.id.iticketstationid=sd.id.iticketstationid\r\n" +
//						"and s.iscenicid = "+map.get("iscenicid")+" and sd.itickettypeid = "+map.get("itickettypeid")+" and  sd.icrowdkindpriceid = "+map.get("icrowdkindpriceid");
//				List find = this.find(numSql);
//				if(find != null && find.size()>0) {
//					Map numMap = (Map)(find.get(0));
//					map.put("salenum", numMap.get("num"));
//				}else {
//					map.put("salenum", 0);
//				}
				//线上售票数量
				String numSql = "select new map(NVL(SUM(tlist.numb),0) as num) from TOrderlist tlist,MOrder m where tlist.id.iscenicid = ? and tlist.itickettypeid=? and m.orid = tlist.id.orid and m.ddzt in ('02','11')";
				List<Map> findBySqlToMap = this.find(numSql, new Object[] {iscenicid, map.get("itickettypeid")});
				if(findBySqlToMap != null && findBySqlToMap.size()>0) {
					Map map2 = findBySqlToMap.get(0);
					Object num = map2.get("num");
					if(num == null) {
						num = 0;
					}
					map.put("salenum", num);
				}

				String szlasttime = esb.getSzlasttime();
				map.put("szlasttime", szlasttime);

				//用户须知地址
				map.put("bookingUrl","http://"+WebContant.GetKeyValue("DOMAIN")+"/android/scenicBook.html?icrowdkindid="+map.get("icrowdkindpriceid"));

				productAll.add(map);
			}
		}
		return productAll;
	}
	
	/**
	 * 
	 * Describe:获取证件类型
	 * 
	 * @author:lijingrui
	 * @return return:StringBuffer Date:2014-3-14
	 */
	public StringBuffer showRetivere(String usid, String pwd) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ectrip>");

		String hsql = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where sys.id.pmky='ZJTP' and sys.id.pmcd not like'%*%' order by pmcd";
		List list = this.find(hsql);
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				sb.append("<menu>");
				sb.append("<pmcd>");
				sb.append(map.get("pmcd").toString());
				sb.append("</pmcd>");
				sb.append("<pmva>");
				sb.append(map.get("pmva").toString());
				sb.append("</pmva>");
				sb.append("</menu>");
			}
		} else {
			sb.append("<menu><error>false</error></menu>");
		}
		sb.append("</ectrip>");

		return sb;
	}
	
	public List getTOrderList(String orid) {
	    String hql="select new map(t.isi as isi,t.ish as ish,t.id.orid as orid,t.id.iscenicid as iscenicid,t.usid as usid,t.ddzt as ddzt,t.dyusid as dyusid,t.scenictype as scenictype,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm,t.orzj as orzj,t.noted as noted,t.notee as notee,t.notea as notea,t.yhamnt  as yhamnt) from TOrder t,Esbscenicareatab e where t.id.orid='"
						+ orid + "' and t.id.iscenicid=e.iscenicid";
	    return find(hql);
	}

	/**
	 * 订单详情里面的产品（服务商）信息
	 * @param iscenicid
	 * @return
	 */
	public Map searchProductForOrderDetail(Long iscenicid){
		Map map = null;
		String sql = "select new map(e.szaddress as szaddress, u.upadder||u.upfilename as upadder)" +
				" from Esbscenicareatab e, Secenicpicture s, Upfile u" +
				" where e.iscenicid=s.iscenicid and s.itickettypeid=0 and s.upid=u.upid" +
				" and e.iscenicid = ? ";
		List<Map> list= this.find(sql, new Object[]{iscenicid});
		if(list != null && !list.isEmpty()){
			map = list.get(0);
			map.put("upadder", map.get("upadder"));
		}
		return map;
	}

	public List searchProduct(Long iscenicid, String rzti,String url) {
		if(url==null||url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		Hotelprovider hotelprovider = (Hotelprovider) this.get(
				Hotelprovider.class, iscenicid);
		if (hotelprovider != null) {

			StringBuffer hsql = new StringBuffer();
			hsql
					.append("select new map(s.iscenicid as iscenicid,s.scenictype as scenictype,t.itickettypeid as itickettypeid,t.sztickettypename as sztickettypename,s.szphone as szphone,product.bedtype as bedtype,product.breakfasttype as breakfasttype,product.widebandtype as widebandtype,product.numter1 as numter1) from Edmtickettypetab t, Esbscenicareatab s,Hotelproduct product ");
			hsql
					.append(" where s.scenictype = '06' and t.itickettypeid = product.itickettypeid and s.byisuse=1 and t.byisuse=1 and s.isjd =0 and s.iscenicid = t.iscenicid and s.iscenicid="
							+ iscenicid);
			//System.out.println("hsql:" + hsql.toString());
			List list = this.find(hsql.toString());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					//用户须知地址
					map.put("bookingUrl","http://"+WebContant.GetKeyValue("DOMAIN")+"/android/hotelBook.html?itickettypeid="+map.get("itickettypeid"));

					// 图片
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
							+ Long.parseLong(map.get("itickettypeid")
									.toString())
							+ " and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.find(sql);
					if (piclist != null && !piclist.isEmpty()) {
						Map map2 = (Map) piclist.get(0);
						/*map.put("upadder", map2.get("upadder").toString());
						map
								.put("upfilename", map2.get("upfilename")
										.toString());
						map.put("upname", map2.get("upname").toString());*/
						map.put("upadder","http://"+
						url+
						map2.get("upadder").toString()+
						map2.get("upfilename").toString());
						
					} else {
						sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
								+ Long.parseLong(map.get("iscenicid")
										.toString())
								+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
						piclist = this.findTopNumb(sql, 1);
						if (piclist != null && !piclist.isEmpty()) {
							Map map2 = (Map) piclist.get(0);
							map.put("upadder", map2.get("upadder").toString());
							map.put("upfilename", map2.get("upfilename")
									.toString());
							map.put("upname", map2.get("upname").toString());
						}
					}
					// 价格
					String sqlp = "select new map(p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.icrowdkindpriceid as icrowdkindpriceid,p.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab p where  p.note1 = '0000' and p.icrowdkindid = 1 and p.ibusinessid = 1 and p.itickettypeid="
							+ Long.parseLong(map.get("itickettypeid")
									.toString())
							+ " and p.startdata <= '"
							+ rzti + "' and p.enddata>='" + rzti + "'";
					System.out.println(sqlp);
					List pirList = this.find(sqlp);

					if (pirList != null && !pirList.isEmpty()) {
						Map map3 = (Map) pirList.get(0);
						map.put("listingprice", map3.get("listingprice").toString());
						map.put("mactualsaleprice", map3.get("mactualsaleprice").toString());
						map.put("icrowdkindpriceid", map3.get("icrowdkindpriceid").toString());
					} else {
						map.put("listingprice", 0);
						map.put("mactualsaleprice", 0);
						map.put("icrowdkindpriceid", 0);
						list.remove(i);
						i--;
					}
				}
				return list;
			}
		}

		return null;
	}

	
	/**
	 * 读取普通产品，最基础的
	 * @param iscenicid
	 * @param rzti
	 * @return
	 */
	public List searchBaseProduct(Long iscenicid, String rzti) {
		

			StringBuffer hsql = new StringBuffer();
			hsql
					.append("select new map(s.iscenicid as iscenicid,s.scenictype as scenictype,t.itickettypeid as itickettypeid,t.sztickettypename as sztickettypename) from Edmtickettypetab t, Esbscenicareatab s ");
			hsql
					.append(" where  s.byisuse=1 and t.byisuse=1 and s.isjd =0 and s.iscenicid = t.iscenicid and s.iscenicid="
							+ iscenicid);
			System.out.println("hsql:" + hsql.toString());
			List list = this.find(hsql.toString());
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					// 图片
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
							+ Long.parseLong(map.get("itickettypeid")
									.toString())
							+ " and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.find(sql);
					if (piclist != null && !piclist.isEmpty()) {
						Map map2 = (Map) piclist.get(0);
						map.put("upadder", map2.get("upadder").toString());
						map
								.put("upfilename", map2.get("upfilename")
										.toString());
						map.put("upname", map2.get("upname").toString());
					} else {
						sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
								+ Long.parseLong(map.get("iscenicid")
										.toString())
								+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
						piclist = this.findTopNumb(sql, 1);
						if (piclist != null && !piclist.isEmpty()) {
							Map map2 = (Map) piclist.get(0);
							map.put("upadder", map2.get("upadder").toString());
							map.put("upfilename", map2.get("upfilename")
									.toString());
							map.put("upname", map2.get("upname").toString());
						}
					}
					// 价格
					String sqlp = "select new map(p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.icrowdkindpriceid as icrowdkindpriceid,p.icrowdkindid as icrowdkindid) from Edmcrowdkindpricetab p where  p.note1 = '0000' and p.icrowdkindid = 1 and p.ibusinessid = 1 and p.itickettypeid="
							+ Long.parseLong(map.get("itickettypeid")
									.toString())
							+ " and p.startdata <= '"
							+ rzti + "' and p.enddata>='" + rzti + "'";
					System.out.println(sqlp);
					List pirList = this.find(sqlp);

					if (pirList != null && !pirList.isEmpty()) {
						Map map3 = (Map) pirList.get(0);
						map.put("listingprice", map3.get("listingprice").toString());
						map.put("mactualsaleprice", map3.get("mactualsaleprice").toString());
						map.put("icrowdkindpriceid", map3.get("icrowdkindpriceid").toString());
					} else {
						map.put("listingprice", 0);
						map.put("mactualsaleprice", 0);
						map.put("icrowdkindpriceid", 0);
						list.remove(i);
						i--;
					}
				}
				return list;
			}
		

		return null;
	}
	/**
	 * 
	 * Describe:查看某服务商的产品信息
	 * 
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return return:Esbscenicareatab Date:Feb 15, 2012
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti,
			String ldti, Long ibusinessid) throws Exception {
		// 查看服务商
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.longitude as longitude) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and pro.iscenicid="+ iscenicid;
		List list = this.find(hsql);
		Map map = null;
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
			if (map.get("iscenicid") != null) {
				String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="+ iscenicid
						+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="+ ibusinessid
						+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="+ ibusinessid
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
						+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="+ ibusinessid
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
						+ ldti + "' )  order by prd.sztickettypename";
				List productList = this.find(hsql2);
				if (productList != null && productList.size() >= 1) {
					for (int j = 0; j < productList.size(); j++) {
						Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
						String[] names = product.getSztickettypename().split("-");
						product.setSztickettypename(names[0]);
						if (names.length > 1) {
							product.setBname(names[1]);
						}
						// 酒店产品其它属性
						Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class, product.getItickettypeid());
						if (hotelproduct != null) {
							Sysparv5Id sys = new Sysparv5Id();
							sys.setPmcd(hotelproduct.getBedtype());
							sys.setPmky("BETP");
							Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
							hotelproduct.setStrbedtype(syspar.getPmva());
							product.setHotelproduct(hotelproduct);
						}
						// /判断选择的入住日期是否都有价格
						long num = getDayNumb(rzti, ldti);
						double totalprice = 0;
						List pricelist = new ArrayList();
						Date enddate = null;
						Edmcrowdkindpricetab price = null;
						double singleprice = 0;
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						for (int x = 0; x < num; x++) {
							Map mapp = new HashMap();
							if (enddate != null&& enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
								singleprice = price.getMactualsaleprice();
								if (hotelproduct != null) {
									switch (hotelproduct.getWeektype()) {
									case 0:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 1:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
											singleprice = price.getWeekendprice();
										break;
									case 2:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 3:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									default:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									}
								}
								totalprice += singleprice;
								mapp.put("singleprice", singleprice);
								mapp.put("icrowdkindpriceid", price.getIcrowdkindpriceid());
								mapp.put("icrowdkindid", price.getIcrowdkindid());
								pricelist.add(mapp);
							} else {
								String hsqlend = "select new map(pri.icrowdkindpriceid as icrowdkindpriceid,pri.icrowdkindid as icrowdkindid,pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ ibusinessid+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ ibusinessid+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()+ ") <='"
										+ rzti+ "' "+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="+ ibusinessid
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="+ product.getItickettypeid()+ "))>='"
										+ ldti+ "' and pri.itickettypeid="+ product.getItickettypeid()+ "  and pri.startdata<='"+ Tools.getDate(rzti, x)
										+ "' and pri.enddata>='"+ Tools.getDate(rzti, x) + "' ";
								List lst = this.find(hsqlend);
								if (lst != null && lst.size() > 0) {
									Map cmap = (Map) lst.get(0);
									price = new Edmcrowdkindpricetab();
									BeanUtils.populate(price, cmap);
									enddate = sf.parse(price.getEnddata());
									singleprice = price.getMactualsaleprice();
									if (hotelproduct != null) {
										switch (hotelproduct.getWeektype()) {
										case 0:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 1:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
												singleprice = price.getWeekendprice();
											break;
										case 2:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 3:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										default:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										}
									}
									totalprice += singleprice;
									mapp.put("singleprice", singleprice);
									mapp.put("icrowdkindpriceid", price.getIcrowdkindpriceid());
									mapp.put("icrowdkindid", price.getIcrowdkindid());
									pricelist.add(mapp);
								}
							}

						}
						product.setPriceList(pricelist);
						double jprice = Math.ceil(totalprice / num);
						product.setJprice(jprice);
						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
								+ product.getItickettypeid()
								+ " and p.upid = u.upid order by p.isecenicpictureid";
						List piclist = this.find(sql);
						product.setList(piclist);
					}
				}
				map.put("productList", productList);
				// 查询酒店属性
				Hotelprovider hotelprovider = new Hotelprovider();
				hotelprovider = (Hotelprovider) this.get(Hotelprovider.class,iscenicid);
				if (hotelprovider != null) {
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class,new Sysparv5Id("HOTP", hotelprovider.getZxjb()));
					hotelprovider.setStrzxjb(sysparv5.getPmva());
				}
				map.put("hotelprovider", hotelprovider);

				// 查询服务商图片
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.find(sql);
				map.put("piclist", piclist);
			}

		} else {// 查询不到。
			return null;
		}
		Esbscenicareatab esbscen = new Esbscenicareatab();
		BeanUtils.populate(esbscen, map);

		this.bulkUpdate("update Esbscenicareatab s set s.popupoint = s.popupoint+1 where s.iscenicid = "
				+ esbscen.getIscenicid());

		return esbscen;

	}
	
	public long getDayNumb(String rzti, String ldti) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(rzti);
		Date enddate = sdf.parse(ldti);
		long time = enddate.getTime() - startdate.getTime();
		long time2 = 24 * 3600 * 1000;
		return time / time2;
	}
	/**
	 * 读取服务商GPS信息
	 */
	public Object searchBaseProviderGpsInfo() {
		// TODO 读取服务商GPS信息
		String hsql = "select new map( p.iscenicid as iscenicid,p.szscenicname as szscenicname,p.longitude as longitude,p.scenictype as scenictype,p.szsimpleintroduction as szsimpleintroduction,p.szqjaddr as szqjaddr) from Esbscenicareatab p)";
		List gpslist = this.find(hsql);
		 
		 List mpblist  = new ArrayList(); //存放反回值
		 List rclist  = new ArrayList(); //存放反回值 
		 for ( int i = 0; i < gpslist.size();i++)
		 {
			 MapPointBean mpb = new MapPointBean();
			 Map map = (Map) gpslist.get(i);
			 //简价
			 mpb.setMapdesc(map.get("szsimpleintroduction") == null ?  "": map.get("szsimpleintroduction") .toString());
			 //名称
			 mpb.setMapname(map.get("szscenicname")== null ? "":map.get("szscenicname").toString());
			 //解说词
			 //李进修改，2014-10-10
			 mpb.setMapvideo(map.get("szqjaddr")== null ? "":map.get("szqjaddr").toString());
			
			 // mpb.setMapvideo("http://"+WebContant.GetKeyValue("ANDROIDURL")+"/jieshuo/"+map.get("iscenicid").toString() + ".mp3");
			 //服务商类别
			 mpb.setMaptype(map.get("scenictype").toString());
			 
			 //服务商ID
			 mpb.setMapid(map.get("iscenicid").toString());
			 //服务商X坐标
			 String obj  = map.get("longitude") == null ?  "0,0": map.get("longitude").toString() ;
			 
			if (obj.indexOf(",") > 0) {
				String xy[] = obj.split(",");

				mpb.setMapx(xy[0]);
				// 服务商Y坐标
				mpb.setMapy(xy[1]);
			} else {
				mpb.setMapx("0");
				// 服务商Y坐标
				mpb.setMapy("0");
			}
			
			
			
			 
			 mpblist.add(mpb);
		 }
		 //
		 
		 for (int i = 0; i <mpblist.size(); i++ )
		 {
			 MapPointBean mpb = (MapPointBean) mpblist.get(i);
			 Map rcmap  = new HashMap();
			 rcmap.put("mapid",mpb.getMapid() );
			 rcmap.put("mapname",mpb.getMapname() );
			 rcmap.put("mapx",mpb.getMapx() );
			 rcmap.put("mapy",mpb.getMapy() );
			 rcmap.put("maplng",mpb.getMaplng());
			 rcmap.put("mapvideo",mpb.getMapvideo() );
			 rcmap.put("mapdesc",mpb.getMapdesc() );
			 rcmap.put("maptype",mpb.getMaptype() );
			 rclist.add(rcmap) ;
		 }
		 
		
		 
		
		 return rclist;
	}
	
	
	/**
	 * 读取指定的特殊URL
	 * URL存放于SYSPARV5中的AURL KEY 中；
	 * @return
	 */
	public Object searchBaseUrlInfo(String key) {
		// TODO Auto-generated method stub
		
	    String hsql  = "  from Sysparv5 s1 where s1.id.pmky='AURL'";
	    List rclist  = new ArrayList(); //存放反回值   
	    List list  = this.find(hsql);
	    Map rcmap  = new HashMap();
		
	    if ( list == null || list.size() == 0)
	    {  //当没有找到，AURL时，返回数量为0
	    	 rcmap.put("AURL","" );
			 rcmap.put("AURLNUM","0");	
			 rcmap.put("AURLKEY",key);	
			 System.out.println("AURL 后台没有设置,请到商务网的后台系统参数表中设置");
	    }
		
		if ( key == null || key.trim() .equals(""))
		{
		  for (int i = 0; i<list.size(); i++)
		  {
			  Sysparv5 sysv5 = (Sysparv5)list.get(i);
			  if ( sysv5.getPmva().equalsIgnoreCase(key))
			  {
				     rcmap.put("AURL",sysv5.getPmvb());
					 rcmap.put("AURLNUM","1");
					 rcmap.put("AURLKEY",key);	
					 rclist.add(rcmap);		 
			  }
		  }
		  
		  return rclist;
		}
		else
      {

			for (int i = 0; i < list.size(); i++) {

				rcmap = new HashMap();
				Sysparv5 sysv5 = (Sysparv5) list.get(i);
				rcmap.put("AURL", sysv5.getPmvb());
				rcmap.put("AURLNUM", "1");
				rcmap.put("AURLKEY", key);

				rclist.add(rcmap);
			}
			return rclist;
		}

		}


	/**
	 *
	 * @param revmbno 手机号
	 * @return
	 */
	public Vcitable getValidate(String revmbno){
		List<Vcitable> list = this.find("from Vcitable where username=? order by DTMAKEDATE desc", new Object[]{revmbno});
		Vcitable vcitable = list.get(0);
		return vcitable;

	}

	//保存用户实名信息
//	public void saveUserBank(Userbank userbank){
//		this.save(userbank);
//	}
//
//	public Userbank getUserBank(String userId){
//		List<Userbank> list = this.find("from Userbank where USERID=?", new Object[]{userId});
//		if(list!=null&&list.size()>0){
//			Userbank userbank = list.get(0);
//			return userbank;
//		}else {
//			return null;
//		}
//
//	}

	public  List<TourCard> getTourCard(String userId){
		String sql = "select distinct c.*" +
				"  from TOURCARDDETAIL t, TOURCARD c" +
				" where" +
				"   t.CODE = c.CODE" +
				"   AND t.STATUS = '1'" +//0未开通；1正常；-1已过期
				"   AND (t.EFFECTIVETIMES  >=t.USEDTIMES OR t.EFFECTIVETIMES IS NULL)" +
				"   AND to_date(t.PERIODSTARTDATE, 'yyyy-mm-dd') <= to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')" +
				"   AND to_date(t.PERIODENDDATE,'yyyy-mm-dd') >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')"+
				"   AND t.USERID=? ";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(TourCard.class);
			sqlQuery.setParameter(0, userId);
			return sqlQuery.list();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	/**
	 * 317 旅游卡专享门票详情
	 * @param iscenicid 第三个参数景区id
	 * @return
	 */
	public Map<String, Object> appGetSenice(String iscenicid){
		return (Map)getSenice(iscenicid).get(0);
	}

	/**
	 * 获取景区信息
	 * @param senice 景区id 1,2,3
	 * @return
	 */
	public List<Map<String, Object>> getSenice(Object senice){
		StringBuffer sb = new StringBuffer("select e.SZSCENICNAME,e.SZGRADE, e.ISCENICID, d.LISTINGPRICE, t.SZTICKETTYPENAME, e.SZLASTTIME, e.SZGRADE,e.LONGITUDE,e.SZADDRESS," +
				"  (select u.upadder from Secenicpicture s,upfile u where s.upid=u.upid and s.iscenicid=e.ISCENICID and rownum=1) as upadder," +
				"   (select u.upfilename from Secenicpicture s,upfile u where s.upid=u.upid and s.iscenicid=e.ISCENICID and rownum=1) as upfilename" +
				"  from ESBSCENICAREATAB e, EDMCROWDKINDPRICETAB d, EDMTICKETTYPETAB t" +
				" where e.iscenicid = t.iscenicid" +
				"   and d.itickettypeid = t.itickettypeid" +
				"   and d.note3=1  and d.ISNET=1  and d.IPEOPLENUMRANGE=1 " );//1;旅游卡购票  是否网上售票  是否实名制

		if(senice!=null||!"".equals(senice)){
			sb.append("   and e.ISCENICID in (");
			String[] senices = senice.toString().split(",");
			for(int i=0;i<senices.length;i++){
				String senicev = senices[i];
				if(senicev!=null&&!"".equals(senicev)){
					sb.append(senicev);
					if(i<senices.length-1){
						sb.append(" ,");
					}
				}
			}
			sb.append(" )");
		}
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try {
			SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			return sqlQuery.list();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	/**
	 * 获取景区门票详细信息
	 * @param scienid
	 * @return
	 */
	public Map<String, Object> getTicketInfo(String scienid){
		String sql = "select distinct d.ICROWDKINDID,d.ITICKETTYPEID,d.ICROWDKINDPRICEID,e.ISCENICID,e.SZLASTTIME, e.IMAXDATA, g.SZINNERNAME, e.SZPHONE, e.SZSIMPLEINTRODUCTION, t.SZTICKETTYPENAME, d.LISTINGPRICE  " +
				"     from ESBSCENICAREATAB     e," +
				"          EDMCROWDKINDPRICETAB d," +
				"          EDMTICKETTYPETAB     t," +
				"          GALSOURCEREGIONTAB   g" +
				"   where g.IREGIONALID = e.SZREGIONALID" +
				"   and e.iscenicid = t.iscenicid" +
				"   and d.itickettypeid = t.itickettypeid" +
				"   and d.note3=1 " +//1;旅游卡购票
				"   and d.IPEOPLENUMRANGE=1 " +//是否实名制
				"   and d.ISNET=1 " +//是否网上销售
				"   and e.ISCENICID=?" ;

//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();

		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setInteger(0, Integer.parseInt(scienid));
			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List list = sqlQuery.list();
			if(list!=null&&list.size()>0){
				for(int i= 0;i<list.size();i++){
					((Map<String, Object>) list.get(i)).put("CADRTYPE", "(旅游卡)");
				}
				return (Map<String, Object>) list.get(0);
			}else{
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return  null;
		}finally {
			session.close();
		}


	}

	/**
	 * 获取景区图片
	 * @param scienid
	 * @return
	 */
	public List<Map<String, Object>> getScenceImg(String scienid){
		String sql = "select u.upadder,u.upfilename from Secenicpicture s,upfile u where s.upid=u.upid and s.iscenicid=?";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setInteger(0, Integer.parseInt(scienid));
			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List list = sqlQuery.list();

			if(list!=null&&list.size()>0){
				return list;
			}else{
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return  null;
		}finally {
			session.close();
		}
	}

	/**
	 * 获取实名制购买
	 * @param isceniceId
	 * @param playDate
	 * @return
	 */
	public Map<String, Object> getLykOrderList(String itickettypeid ,Long icrowdkindid ,Long isceniceId, String playDate, String userId){
		Map<String, Object> map = new HashMap<String, Object>();
		Custom custom = (Custom) ticketDao.get(Custom.class, userId);

		Edmcrowdkindpricetab corwdkindprice = null;
		try {
			String note2 = ticketDao.searchJgfz(custom.getUsid(), isceniceId);

			corwdkindprice = ticketDao.getProductPrice(itickettypeid , custom
					.getIbusinessid().toString(), playDate, icrowdkindid
					.toString(), note2);
			if(corwdkindprice==null){
				map.put("status", 0);
				map.put("message", "没找到产品信息！");
				return map;
			}


		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("message", "查找产品信息出错！");
			return map;
		}

		String sql = "select t.ORID from t_orderlist t,M_ORDER m,t_realname r WHERE t.ORID=m.ORID and r.orid=t.orid and t.iscenicid="+isceniceId+" AND m.ddzt not in('98', '27', '30', '31', '06', '17') AND m.STDT='"+playDate+"' and m.USID='"+userId+"'  and t.icrowdkindpriceid="+corwdkindprice.getIcrowdkindpriceid();
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try{
			List listValue = session.createSQLQuery(sql).list();
			if(listValue.size()>0){
				map.put("status", 0);
				map.put("message", "游玩时间"+playDate+"已有门票，请重新选择游玩时间!");
				return map;
			}
		}catch (Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("message", "查找产品信息出错！");
			return map;
		}finally {
			session.close();
		}

		map.put("status", 1);
		map.put("data", "可以购票");

		return map;
	}

	/**
	 * 根据省份证号或获取用户信息
	 * @param id
	 * @return
	 */
//	public Userbank getUserBankByID(String id){
//		List<Userbank> userbank =  this.find("from Userbank where ubid=?", new Long[]{Long.parseLong(id)});
//		if(userbank!=null&&userbank.size()>0){
//			return userbank.get(0);
//		}else{
//			return null;
//		}
//
//	}

	/**
	 * 获取用户所拥有的旅游卡
	 * @param userId 用户id
	 * @param isecienceId 景区id
	 * @return
	 */
	public List<Map<String, Object>> getUserCard(String userId,String isecienceId){
		String sql = "SELECT t.CARDNUMBER,c.name,t.PERIODSTARTDATE, t.PERIODENDDATE FROM TOURCARDDETAIL t,TOURCARD c WHERE t.code=c.code " +
				"   AND t.STATUS = '1'" +//0未开通；1正常；-1已过期
				"   AND (t.EFFECTIVETIMES  >=t.USEDTIMES OR t.EFFECTIVETIMES IS NULL)" +
				"   AND to_date(t.PERIODSTARTDATE, 'yyyy-mm-dd') <= to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')" +
				"   AND to_date(t.PERIODENDDATE,'yyyy-mm-dd') >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')"+
				"   AND t.USERID=? AND c.ISCENICIDS LIKE ? ";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString(0, userId);
			sqlQuery.setString(1, "%,"+isecienceId+",%");

			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List list = sqlQuery.list();
			if(list!=null&&list.size()>0){
				return list;
			}else{
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	/**
	 * 获取用户旅游卡
	 * @param carNo 旅游卡卡号
	 * @return
	 */
	public TourCardDetail getTourCarDetail(Object carNo){
		String hql = " from TourCardDetail where cardNumber=?";
		try{
			List list =  this.find(hql, new Object[]{carNo});
			if(list==null||list.size()<=0){
				return null;
			}else {
				return (TourCardDetail) list.get(0);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取旅游卡信息
	 * @param carNo
	 * @return
	 */
	public Map<String, Object> getTourCardInfo(Object carNo){
		String sql = "select t.timesFlag,t.useperiod,d.* from TOURCARD t,TOURCARDDETAIL d where d.cardnumber=? and d.code=t.code";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try {
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString(0, String.valueOf(carNo));
			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List list = sqlQuery.list();
			if(list==null||list.size()<=0){
				return null;
			}else{
				return (Map<String, Object>) list.get(0);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}

		return null;
	}

	/**
	 * 是否为节假日
	 * @param date 是true
	 * @return
	 */
	public boolean getHoliday(String date){
		String sql = "select t.HOLIDAYDATE from HOLIDAY t where t.holidaydate=?";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString(0, String.valueOf(date));
			sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List list = sqlQuery.list();
			if(list==null||list.size()<=0){
				return false;
			}else{
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}

		return false;
	}

	/**
	 * 根据电话号码获取用户
	 * @param phone
	 * @return
	 */
	public Custom getCustomByPhone(String phone){

		List<Custom> listc = this.find("from Custom where mobile=? and  ibusinessid=1",new String[]{phone});
		if(listc!=null&&listc.size()>0){
			return listc.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 跟新使用旅游卡次数(增加)
	 * @param outNumber
	 */
	public void useTourTicket(String outNumber,String userid) throws Exception{
		String sql = "select t.* from TOURCARD t ,TOURCARDDETAIL c where c.cardnumber=? and c.USERID=? and t.code=c.code ";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(TourCard.class);
			sqlQuery.setString(0, outNumber);
			sqlQuery.setString(1, userid);
			TourCard tourCard = (TourCard) sqlQuery.list().get(0);

			//获取次数信息
			Long timesFlag = tourCard.getTimesFlag();
			if(timesFlag==0){
				String sql1 = "update TOURCARDDETAIL set USEDTIMES=USEDTIMES+1, LEAVETIMES=LEAVETIMES-1 WHERE CARDNUMBER='"+outNumber+"' and USERID='"+userid+"'";
				SQLQuery sqlQuery1 = session.createSQLQuery(sql1);
				sqlQuery1.executeUpdate();
				tran.commit();
			}
		}catch (Exception e){
			tran.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			session.close();
		}
	}


	/**
	 * 跟新使用旅游卡次数（减少）
	 * @param outNumber
	 */
	public void refuseTourTicket(String outNumber,String userid) throws Exception{
		String sql = "select t.* from TOURCARD t ,TOURCARDDETAIL c where c.cardnumber=? and c.USERID=? and t.code=c.code ";
//		Session session = this.getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try {
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(TourCard.class);
			sqlQuery.setString(0, outNumber);
			sqlQuery.setString(1, userid);
			TourCard tourCard = (TourCard) sqlQuery.list().get(0);

			//获取次数信息
			Long timesFlag = tourCard.getTimesFlag();
			if(timesFlag==0){
				String sql1 = "update TOURCARDDETAIL set USEDTIMES=USEDTIMES-1, LEAVETIMES=LEAVETIMES+1 where CARDNUMBER='"+outNumber+"' and USERID='"+userid+"' and USEDTIMES>0";
				SQLQuery sqlQuery1 = session.createSQLQuery(sql1);
				sqlQuery1.executeUpdate();
				tran.commit();
			}
		}catch (Exception e){
			tran.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			session.close();
		}

	}

	/**
	 * 获取银行卡信息
	 * @param userId
	 * @return
	 */
//	public List<Userbank> getUSerBank(String userId){
//		String sql = "from Userbank t where t.userid=?";
//		try{
//			List<Userbank> list = this.find(sql,new String[]{userId});
//			return list;
//		}catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	/**
	 * 根据银行卡号获取信息
	 * @param cardnumber
	 * @return
	 */
//	public List<Userbank> getUSerBankByNo(String cardnumber){
//		String sql = "from Userbank t where t.cardnumber=?";
//		try{
//			List<Userbank> list = this.find(sql,new String[]{cardnumber});
//			return list;
//		}catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	/**
	 *
	 * @param cardnumber
	 * @return
	 */
//	public List<Userbank> getUSerBankById(String cardnumber){
//		String sql = "from Userbank t where t.cardnumber=?";
//		try{
//			List<Userbank> list = this.find(sql,new String[]{cardnumber});
//			return list;
//		}catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	/**
	 * 获取订单列表
	 * @param sql
	 * @return
	 */
	public List getOrderLists(String sql){
		List resultList = new ArrayList();
		try {
			List<Map> list = this.findBySqlToMap(sql);
			//字段变成小写、未支付订单添加支付url
			for(int i=0;i<list.size();i++){
				Map map = list.get(i);
				Iterator entries = map.entrySet().iterator();
				Map<String, Object> resultMap = new HashMap<String, Object>();

				String payUrl = "";
				Map<String, String> merchant = sysparServiceApi.findMerchant();
				SortedMap<String, Object> params = new TreeMap<String, Object>();
				params.put("merchantId", merchant.get("merchantId"));
				String totalFee = "";//单价
				String orid = "";//订单id
				String ddzt = "";//订单状态
				String scenictype = "";//01门票  06酒店

				while (entries.hasNext()) {
					Map.Entry entry = (Map.Entry) entries.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					resultMap.put(String.valueOf(key).toLowerCase(), value);
					if ("ORID".equals(key)) orid = String.valueOf(value);
					if ("DDZT".equals(key)) ddzt = String.valueOf(value);
					if ("PRIC".equals(key)) totalFee = String.valueOf(value);
					if ("SCENICTYPE".equals(key)) scenictype = String.valueOf(value);
				}

				//支付地址
				if("00".equals(ddzt)){
					params.put("outTradeNo",orid);
					//回调地址
					if("01".equals(scenictype)){
						params.put("returnUrl",  "http://"+WebContant.GetKeyValue("DOMAIN")+"/alpay/orderAppView.action");
					}else{
						params.put("returnUrl", sysparServiceApi.findReturnUrle());
					}

					params.put("totalFee", totalFee);
					String sign = MD5Util.createSign(params, merchant.get("key"), "UTF-8");
					params.put("sign", sign);
					//访问链接
					payUrl =  sysparServiceApi.findPayUrld() + "/wapcounter?";


					List<String> keys = new ArrayList<String>(params.keySet());
					for (int j = 0; j < keys.size(); j++) {
						String key = keys.get(j);
						String value = String.valueOf(params.get(key));

						//拼接时，不包括最后一个&字符
						if (j == keys.size() - 1) {
							payUrl = payUrl + key + "=" + value;
						} else {
							payUrl = payUrl + key + "=" + value + "&";
						}
					}
				}

				resultMap.put("payUrl", payUrl);
				resultMap.put("producttype", "01");
				resultList.add(resultMap);
			}

		} catch ( Exception e){
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * 获取用户信息
	 * @param phone
	 * @return
	 */
	public  Map<String, Object> getUserInfoByPhone(String phone){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hsql = new StringBuffer(
				"select c.note6 as names,(case c.inote3 when 2 then '女' else '男' end) as sex,c.note7 as birthday,c.mobile,u.upfilename,u.upadder,g.szinnername from custom c left join upfile u on c.inote1=u.upid left join galsourceregiontab g on c.iregionalid=g.iregionalid where c.mobile=?");
		List list = null;
		try {
			list = this.findBySqlToMap(hsql.toString(), new String[]{phone});
			if(list!=null&&list.size()>0){
				return (Map<String, Object>)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}



}
