package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Linetravel;
import com.ectrip.ticket.model.provider.Linetravelpic;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.model.provider.Provider;
import com.ectrip.ticket.provider.dao.ILinetravelDao;
import com.ectrip.upload.model.Upfile;

@Repository
public class LinetravelDao extends GenericDao implements ILinetravelDao {
	public static final String findByTickettypeid = "from Linetravel l where l.itickettypeid = ? order by l.sort";

	/**
	 * 根据产品id查找行程*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ILinetravelDao#findByTickettypeid(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author liujianwen
	 * Date:2012-7-2
	 */
	@SuppressWarnings("unchecked")
	public List<Linetravel> findByTickettypeid(Long itickettypeid) {
		return this.find(findByTickettypeid, new Long[]{itickettypeid});
	}
	/**
	 * 根据产品id找行程
	 * Describe:
	 * @author liujianwen
	 * @param itickettypeid
	 * @return
	 * return:List<Linetravel>
	 * Date:2012-7-2
	 */
	public PaginationSupport findLinetravelByTickettypeid(Long itickettypeid,String linename,int page,int pagesize,String url) {
		PaginationSupport ps;
		if(linename==null||linename.equals("")){
			ps = this.findPage("select new Map(l.linetravelid as linetravelid,l.itickettypeid as itickettypeid,l.linetravelname as linetravelname,l.hotelname as hotelname,l.note,l.dtmakedate as dtmakedate,l.lineprod as lineprod,l.linedress as linedress,l.hoteluct as hoteluct) from Linetravel l where l.itickettypeid = "+itickettypeid +" order by l.sort", pagesize, page, url);
		}else{
			ps = this.findPage("select new Map(l.linetravelid as linetravelid,l.itickettypeid as itickettypeid,l.linetravelname as linetravelname,l.hotelname as hotelname,l.note,l.dtmakedate as dtmakedate,l.lineprod as lineprod,l.linedress as linedress,l.hoteluct as hoteluct) from Linetravel l where l.itickettypeid = "+itickettypeid +" and l.linetravelname like '%"+linename+"%' order by l.sort", pagesize, page, url);
		}
		return ps;		
	}
	/**
	 * 根据id查找行程
	 * Describe:
	 * @see com.ectrip.system.provider.service.iservice.ILineService#findLinetravelById(java.lang.Long)
	 * @param linetravelid
	 * @return
	 * @author liujianwen
	 * Date:2012-7-5
	 */
	public Linetravel findLinetravelById(Long linetravelid) {
		Linetravel linetravel = (Linetravel) this.get(Linetravel.class, linetravelid);
		if(linetravel != null){
			List<Upfile> upfiles = new ArrayList<Upfile>();
			Upfile upfile; 
			for(Object pic : this.find("from Linetravelpic where linetravelid = ?",new Long[]{linetravel.getLinetravelid()})){
				upfile = (Upfile) this.get(Upfile.class, ((Linetravelpic)pic).getUpid());
				if(upfile != null) upfiles.add(upfile);
			};

			linetravel.setList(upfiles);
		}
		return linetravel;
	}
	/**
	 * 
	 * Describe:根据行程id找图片中间表
	 * @author liujianwen
	 * @param linetravelid
	 * @return
	 * return:List
	 * Date:2012-7-10
	 */
	public List findLinetravelpicByLinetravelid(Long linetravelid){
		return this.find("from Linetravelpic where linetravelid = ?",new Long[]{linetravelid});
	}
	/**
	 * 
	 * Describe:
	 * @author liujianwen
	 * @param linetravelid
	 * return:void
	 * Date:2012-7-5
	 */
	public void delteLinetravelById(Long linetravelid){
		this.deleteLinetravelpicByLinetravelid(linetravelid);
		this.deleteByKey(Linetravel.class,linetravelid);
	}

	/**
	 * 
	 * Describe:删除行程图片
	 * @author liujianwen
	 * @param linetravelid
	 * return:void
	 * Date:2012-7-6
	 */
	public void deleteLinetravelpicByLinetravelid(Long linetravelid){
		this.deleteAll(this.find("from Linetravelpic l where l.linetravelid = ?",new Long[]{linetravelid}));
	}

	/**
	 *查询线路价格
	 * Describe:
	 * @author liujianwen
	 * @param itickettypeid
	 * @return
	 * return:List<Linetravel>
	 * Date:2012-7-2
	 */
	//	public PaginationSupport findLineprice(int page,int pagesize,String url,String title,String starttime,String to,String from) {
	//		PaginationSupport ps;
	//		String 	now = (starttime==null || "".equals(starttime)?SaleUtil.format2.format(new Date()):starttime);
	//		String hsql=
	//				"select new Map(" +
	//						"ep.icrowdkindpriceid as icrowdkindpriceid,"+//价格id
	//						"ep.itickettypeid as itickettypeid,"+//产品id
	//						"ep.icrowdkindid as icrowdkindid,"+//人群种类id
	//						"ep.ibusinessid as ibusinessid, "+//业务id
	//						"ep.ipricecategoryid as ipricecategoryid,"+
	//						"ep.startdata as startdata,"+
	//						"ep.enddata as enddata,"+
	//						"ep.ipeoplenumrange as ipeoplenumrange,"+
	//						"ep.listingprice as listingprice,"+
	//						"ep.weekendprice as weekendprice,"+
	//						"ep.mactualsaleprice as mactualsaleprice,"+
	//						"ep.isequence as isequence,"+
	//						"ep.byisuse as byisuse,"+
	//						"ep.szmemo as szmemo,"+
	//						"ep.isnet as isnet,"+
	//						"ep.isscene as isscene,"+
	//						"ep.icrowdkindpricecode as icrowdkindpricecode,"+
	//						"ep.jsprice as jsprice,"+
	//						"eb.szbusinessname as szbusinessname,"+//业务名称
	//						"eb.businesstype as businesstype,"+
	//						"lp.datanumber as datanumber,"+//行程天数
	//						"lp.departure as departure,"+//出发地
	//						"lp.destination as destination,"+//目的地
	//						"lp.deposittype as deposittype,"+//定金方式
	//						"lp.startingmethod as startingmethod,"+//出发方式
	//						"lp.startingdata as startingdata,"+//出发日期
	//						"lp.deposit as deposit,"+//定金
	//						"lp.daynum as daynum,"+//提前预定天数
	//						"lp.status as status,"+//审核状态
	//						"lp.prdstatus as prdstatus,"+//线路属性
	//						"lp.linenota as linenota,"+//费用包含
	//						"lp.linenotb as linenotb,"+//费用不包含
	//						"lp.linemmqt as linemmqt,"+//可选服务项
	//						"lp.desnomn as desnomn,"+//预定须知
	//						"lp.audiorum as audiorum,"+//温馨提示
	//						"lp.note as note, "+//线路简介
	//						"ek.szinnername as szinnername,"+//人群种类名称
	//						"ek.szinnercode as szinnercode,"+//人群种类代码
	//						"et.sztickettypename as sztickettypename,"+//产品名称
	//						"et.sztickettypecode as sztickettypecode,"+//产品编号
	//						"es.szinnercode as szinnercode,"+//服务商标号
	//						"es.szinnername as szinnername"+//服务商名称
	//						") from Edmcrowdkindpricetab ep ,  Edmbusinesstab eb, Lineproduct lp , Edpcrowdkindtab ek, Edmtickettypetab et, Esbscenicareatab es " +
	//						" where ep.itickettypeid = et.itickettypeid and ek.icrowdkindid  = ep.icrowdkindid  and lp.itickettypeid = ep.itickettypeid  and "+
	//						"eb.ibusinessid = ep.ibusinessid  and es.iscenicid = et.iscenicid and "+
	//						((title==null ||"".equals(title))?" ":("et.sztickettypename like '"+title+"' and ")) +
	//						((to==null ||"".equals(to))?" ":("lp.destination = '"+to+"' and ")) +
	//						((from==null ||"".equals(from))?" ":("lp.departure = '"+from+"' and ")) +
	//						"es.scenictype ='07' "+// 旅行社服务商
	//						"and ep.isnet = 1 "+//是否网上销售
	//						"and et.byisuse = 1 "+//是否禁用
	//						"and ep.byisuse = 1 "+
	//						"and ek.byisuse = 1 "+
	//						"and es.byisuse =1 "+
	//						"and eb.byisuse = 1 "+
	//						"and ep.startdata<= '"+now+"' and ep.enddata>= '"+now+"' "+
	//						"order by ep.isequence";
	//		ps = this.findPage(hsql, pagesize, page, url);
	//		return ps;		
	//	}
	/**
	 * 
	 * Describe:
	 * @author liujianwen
	 * @param page
	 * @param pagesize
	 * @param url
	 * @param iscenicid  服务商id 为null时 查询所有服务商
	 * @param title
	 * @param starttime
	 * @param to
	 * @param from
	 * @return
	 * return:PaginationSupport
	 * Date:2012-7-18
	 */
	public PaginationSupport findLineProductPage(int page,int pagesize,String url,Long iscenicid,String title,String starttime,String to,String from,String bycategorytype) {
		PaginationSupport ps;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new Map(" );
		hsql.append("s.pmva as bycategorytype,et.itickettypeid as itickettypeid,");//产品id
		hsql.append("lp.datanumber as datanumber,");//行程天数
		hsql.append("lp.departure as departure,");//出发地
		hsql.append("lp.destination as destination,");//目的地
		hsql.append("lp.deposittype as deposittype,");//定金方式
		hsql.append("lp.startingmethod as startingmethod,");//出发方式
		hsql.append("lp.startingdata as startingdata,");//出发日期
		hsql.append("lp.deposit as deposit,");//定金
		hsql.append("lp.daynum as daynum,");//提前预定天数
		hsql.append("lp.status as status,");//审核状态
		hsql.append("lp.prdstatus as prdstatus,");//线路属性
		hsql.append("lp.linenota as linenota,");//费用包含
		hsql.append("lp.linenotb as linenotb,");//费用不包含
		hsql.append("lp.linemmqt as linemmqt,");//可选服务项
		hsql.append("lp.desnomn as desnomn,");//预定须知
		hsql.append("lp.audiorum as audiorum,");//温馨提示
		hsql.append("lp.note as note, ");//线路简介
		hsql.append("et.sztickettypename as sztickettypename,");//产品名称
		hsql.append("et.sztickettypecode as sztickettypecode");//产品编号
		hsql.append(	") from  Lineproduct lp, Edmtickettypetab et, Esbscenicareatab es,Sysparv5 s" );
		hsql.append(" where s.id.pmky='PRTP' and s.id.pmcd = et.bycategorytype and  lp.itickettypeid = et.itickettypeid  and ");
		hsql.append(((title==null ||"".equals(title))?" ":("et.sztickettypename like '%"+title+"%' and ")));
		hsql.append(((to==null ||"".equals(to))?" ":("lp.destination like '%"+to+"%' and ")) );
		hsql.append(((from==null ||"".equals(from))?" ":("lp.departure like '%"+from+"%' and ")));
		hsql.append("es.iscenicid = et.iscenicid and ");
		hsql.append("es.scenictype ='07' ");// 旅行社服务商
		hsql.append("and et.byisuse = 1 ");//是否禁用
		hsql.append("and es.byisuse =1 ");
		hsql.append("and es.byisuse =1 ");
		hsql.append(iscenicid==null?"":" and es.iscenicid="+iscenicid);
		hsql.append(bycategorytype==null?"":" and et.bycategorytype='"+bycategorytype+"'");
		hsql.append(" order by et.isequence");
		System.out.println(hsql);
		ps = this.findPage(hsql.toString(), pagesize, page, url);
		return ps;		
	}
	/**
	 * 根据产品找价格
	 * Describe:
	 * @author liujianwen
	 * @return
	 * return:List<Edmcrowdkindpricetab>
	 * Date:2012-7-11
	 */
	public List<Edmcrowdkindpricetab> findLinePriceByItickettypeid(Long itickettypeid){
//		String now = SaleUtil.format3.format(new Date());
		String now = Tools.getDayTimes();
		return this.find("select price from Edmcrowdkindpricetab price,Edmbusinesstab eb where price.itickettypeid= "+itickettypeid+" and  (price.enddata >= '"+now+"'  or price.startdata>='"+now+"' )   and eb.ibusinessid = price.ibusinessid and eb.businesstype ='0001' and price.byisuse = 1 order by price.enddata");
	}
	/**
	 * *
	 * Describe:查找路线产品图片
	 * @see com.ectrip.system.provider.dao.idao.ILinetravelDao#findLinerPic()
	 * @return
	 * @author liujianwen
	 * Date:2012-7-10
	 */
	public List<Upfile> findLinerPic(Long itickettypeid ) {
		return  this.find("select u from Upfile u,Secenicpicture s where s.itickettypeid = ? and s.upid = u.upid",new Long[]{itickettypeid});
	}
	/**
	 * 
	 * Describe:
	 * @author liujianwen
	 * @param pdtp 服务商类型
	 * @return
	 * return:List<Provider>
	 * Date:2012-7-14
	 */
	public List<Provider> findLXS(String pdtp){
		return this.find("from Provider p where p.pdtp = ? and p.isvalid = 1",new String[]{pdtp});
	}
	/**
	 * Describe:查询评论
	 * @author liujianwen
	 * @param type 是否已审核
	 * @param ptype 评论类型 （01服务商，02产品，03文章，04订单）
	 * @param oeid 评论对象id
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-7-17
	 */
	public PaginationSupport queryHscomment(String type,String ptype,Long oeid, int page,
			int pageSize, String url) {

		PaginationSupport ps =null;
		//查询语句
		StringBuffer hsql = new StringBuffer();

		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.effect as effect,h.createdate as createdate,h.remarknum as remarknum,h.fenjingnum as fenjingnum,h.xinjianum as xinjianum,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype ) from Hscomment h,Sysparv5 sys where h.ptype = sys.id.pmcd and sys.id.pmky='PLTP' ");

		if("00".equals(type)){//未审核
			hsql.append(" and h.status ='00' ");
		}else if("01".equals(type)){//审核通过
			hsql.append(" and h.status ='01' ");
		}else if("02".equals(type)){//审核 不通过
			hsql.append(" and h.status ='02' ");
		}else{
			//	hsql.append(" and h.status in ('01','02',) ");
		}

		hsql.append(" and h.oeid =" + oeid);
		hsql.append(" and h.ptype ='" + ptype+"'");
		hsql.append(" order by h.shdate desc ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {				
				map = (Map)list.get(i);

				if(map.get("shusid")!=null && !"".equals(map.get("shusid"))){//审核人
					Long iemployeeid = Long.parseLong(map.get("shusid").toString());
					Esfemployeetab emp = (Esfemployeetab)this.get(Esfemployeetab.class, iemployeeid);
					if(emp!=null){
						map.put("empid", emp.getEmpid());
						map.put("szemployeename", emp.getSzemployeename());
					}
				}
				if(map.get("note")!=null && !"".equals(map.get("note"))){//评论内容
					String notes = map.get("note").toString();						
					String noHTMLString = notes.replaceAll(
							"\\<script.*?script\\>", "");
					notes = noHTMLString.replaceAll("\\<.*?\\>", "")
							.replaceAll("&nbsp;", "").replaceAll(" ", "")
							.replaceAll("　", "");
					if (notes.length() > 20) {
						notes = notes.substring(0, 20) + "...";
					}
					map.put("strnote", notes);
				}
				if(map.get("ctitle")!=null && !"".equals(map.get("ctitle"))){//评论标题
					String title = map.get("ctitle").toString();						
					String noHTMLString = title.replaceAll(
							"\\<script.*?script\\>", "");
					title = noHTMLString.replaceAll("\\<.*?\\>", "")
							.replaceAll("&nbsp;", "").replaceAll(" ", "")
							.replaceAll("　", "");
					if (title.length() > 20) {
						title = title.substring(0, 20) + "...";
					}
					map.put("strctitle", title);
				}
				if(map.get("effect")!=null && !"".equals(map.get("effect"))){
					String[] effects = map.get("effect").toString().split(",");
					Map comlist = new HashMap();
					for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmcd(htype.trim());
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
					map.put("effectList", comlist);
				}
				if(map.get("usid")!=null && !"".equals(map.get("usid"))){
					String usid = map.get("usid").toString();
					int isuse = usid.lastIndexOf(".");
					if(isuse>0){
						map.put("usid", "****");
					}else{

					}
				}

			}
		}
		System.out.println("size:"+ps.getItems().size());
		return ps;

	}
	/**
	 * 保存网友留言
	 * @param message
	 * @date  2012-7-4
	 *
	 */
	public void saveNetMessage(Netusermessage message) {
		this.saveOrUpdate(message);
	}
	/**
	 * 
	 * Describe: 查询留言
	 * @author liujianwen
	 * @param status 审核状态 00未审核，01审核
	 * @param productid 产品id
	 * @param iscenicid 服务商id
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-7-17
	 */
	public PaginationSupport queryNetusermessage(String status, Long productid,Long iscenicid,int page,
			int pageSize, String url) {
		PaginationSupport ps =null;
		//查询语句
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new Map(n.netid  as netid,n.productid as productid,n.productid as productid, n.messtitle as messtitle,n.netusername as netusername,n.netuserphone as netuserphone,n.message as message,n.createdate as createdate,n.status as status,n.noted1 as noted1,n.noted2 as noted2,n.spare1 as spare1,n.spare2 as spare2) from Netusermessage n where ");
		if(status != null){
			hsql.append("n.status ='" );
			hsql.append(status);
			hsql.append("' and");
		}
		if(productid != null){
			hsql.append(" n.productid = " );
			hsql.append(productid);
		}else{
			if(iscenicid != null){
				hsql.append(" n.iscenicid = " );
				hsql.append(iscenicid);
			}
		}
		hsql.append(" order by n.createdate desc" );
		System.out.println("line message "+hsql.toString());
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}
	/**
	 * 
	 * Describe: 查询评论数
	 * @author liujianwen
	 * @param status 评论状态
	 * @param ptype 评论类型
	 * @param oeid 评论对象
	 * @return
	 * return:List
	 * Date:2012-7-18
	 */
	public List findCountHscomment(String status,String ptype,Long oeid){
		StringBuffer hsql = new StringBuffer();
		hsql.append("select count(h) from Hscomment h  where h.ptype='");
		hsql.append(ptype);
		hsql.append("' ");
		hsql.append("and  h.oeid =");
		hsql.append(oeid);
		hsql.append(status == null ?"":(" and h.status='"+status+"'"));
		return this.find(hsql.toString());
	}
	/**
	 * 
	 * Describe: 查询平均分
	 * @author liujianwen
	 * @param status 评论状态
	 * @param ptype 评论类型
	 * @param oeid 评论对象
	 * @return
	 * return:List
	 * Date:2012-7-18
	 */
	public List findAvgHscomment(String status,String ptype,Long oeid){
		StringBuffer hsql = new StringBuffer();
		hsql.append("select avg(h.remarknum) from Hscomment h  where h.ptype='");
		hsql.append(ptype);
		hsql.append("' ");
		hsql.append("and  h.oeid =");
		hsql.append(oeid);
		hsql.append(status == null ?"":(" and h.status='"+status+"'"));
		return this.find(hsql.toString());
	}
}
