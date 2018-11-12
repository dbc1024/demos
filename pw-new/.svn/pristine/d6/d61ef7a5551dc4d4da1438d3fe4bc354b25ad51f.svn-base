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
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.provider.dao.IProductAttributeDAO;
@Repository
public class ProductAttributeDAO extends GenericDao implements IProductAttributeDAO{

	/**
	 * 获取产品属性列表
	 * @param pid 产品ID
	 * @return
	 * @author  YangYang
	 * @date  2012-7-2
	 *
	 */
	public List getProductAllAttributes(Long pid) {
		// TODO Auto-generated method stub
		String hsql = "select new map(ht.itickettypeid as itickettypeid,ty.sztickettypename as sztickettypename,ht.noted1 as noted1,ht.noted2 as noted2,ht.noted3 as noted3,ty.byisuse as byisuse) "+ 
					  "from Hotelproduct ht,Edmtickettypetab ty where ht.itickettypeid=ty.itickettypeid and ht.itickettypeid="+pid;
		List list = this.find(hsql);
		return list;
	}
	
	/**
	 * 保存产品属性
	 * @param product
	 * @author  YangYang
	 * @date  2012-7-3
	 *
	 */
	public void saveProductAttribute(Hotelproduct product) {
		// TODO Auto-generated method stub
	
		this.saveOrUpdate(product);
	}
	
	/**
	 * 获取已/未审合留言列表
	 * @param status   状态
	 * @return
	 * @author  YangYang
	 * @date  2012-7-4
	 *
	 */
	public PaginationSupport getIsCheckedNetmessList(String status,int pageSize,int page,String url) {
		// TODO Auto-generated method stub
		PaginationSupport ps =null;
		
		StringBuffer sbf = new StringBuffer();
		String hsql = "select new map(net.netid as netid,net.productid as productid,sc.szscenicname as scname,net.messtitle as messtitle,net.netusername as netusername,"
					+" net.netuserphone as netuserphone,net.message as message,net.createdate as createdate,net.status as status,net.noted2 as noted2,net.noted3 as noted3) "
					+" from Netusermessage net,Esbscenicareatab sc where net.spare2=sc.iscenicid ";
		sbf.append(hsql);
		if(status !=null && !status.equals("")){
			if(status.equals("00")){//未审核的
				sbf.append(" and status='"+status+"'");
			}else{//审核过的或是审核没通过的
				sbf.append(" and status!='00'");
			}
		}
		sbf.append(" order by createdate desc");
		ps = this.findPage(sbf.toString(), pageSize, page, url);
		System.out.println("system message:"+sbf.toString());
		return ps;
	}
	
	/**
	 * 保存网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void saveNetMessage(Netusermessage mess) {
		// TODO Auto-generated method stub
		try {
			this.saveOrUpdate(mess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除网友留言
	 * @param mess
	 * @author  YangYang
	 * @date  2012-7-6
	 *
	 */
	public void deleteNetMessage(Netusermessage mess) {
		// TODO Auto-generated method stub
//		Netusermessage netmess = (Netusermessage) this.get(Netusermessage.class, mess.getNetid());
		this.delete(mess);
//		this.deleteByKey(Netusermessage.class, mess.getNetid());
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> findExpire(Esfemployeetab esfemployeetab) {
		// TODO 查询所有的产品跟服务商
		Sysparv5 sysparv5 = (Sysparv5) this.find("from Sysparv5 s where s.id.pmky='TYCS' and s.id.pmcd='0001'").get(0);
		//获取价格
		int interval = Integer.parseInt(sysparv5.getPmvb());//价格
		StringBuffer hsql = new StringBuffer();
		hsql.append("select distinct new map(esb.iscenicid as iscenicid,edm.itickettypeid as itickettypeid, esb.szscenicname  as szscenicname, edm.sztickettypename as sztickettypename,esb.scenictype as scenictype) from Esbscenicareatab  esb, Edmtickettypetab  edm, Edmcrowdkindpricetab edmc where esb.byisuse = 1 and edm.byisuse = 1 and edmc.byisuse = 1 and edmc.isnet = 1 and esb.iscenicid = edm.iscenicid and  edmc.enddata<'"+Tools.getDate(Tools.getDays(), interval)+"' and edm.itickettypeid = edmc.itickettypeid ");
		// 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
		if (esfemployeetab != null) {
			if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
				String scenics = esfemployeetab.getScenics();
				hsql.append(" and esb.iscenicid in (" + scenics + ")  ");
			}
		}
		hsql.append(" order by esb.scenictype,esb.iscenicid,edm.itickettypeid");
		List<Map> list = this.find(hsql.toString());
		List<Map> resultList = null;//返回结果
		int yiguoqi = 0;//已过期
		int jguoqi = 0;//即将过期
		if (list!=null&&list.size()>0) {
			resultList = new ArrayList<Map>();
			for (Map map : list) {
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				String scenictype = map.get("scenictype").toString();
				List<Map> dateList = null;
				String nowday = Tools.getDays();//当前日期
				String endDate ="";//结束日期
				int chadate=0;//过期时间
				if (scenictype.equals("07")) {//线路
					dateList = this.find("select new map(max(edmc.enddata) as enddata) from Edmcrowdkindpricetab edmc  where edmc.itickettypeid = '"+itickettypeid+"'");
					if (dateList!=null&&dateList.size()>0) {
						for (Map dateMap : dateList) {
							endDate = dateMap.get("enddata").toString();
							chadate = Tools.getDayNumbCha(nowday, endDate);
							if (chadate<=interval && chadate>=0) {//即将过期
								map.put("statusstr", "价格将在"+chadate+"后过期。");
								map.put("status", "01");
								jguoqi++;
							}else if (chadate<0) {
								map.put("statusstr", "价格已过期");
								map.put("status", "02");
								yiguoqi++;
							}else {
								map.put("status", "03");
							}
						}
					}
				}else {//其它的
					dateList = this.find("select new map(edmc.icrowdkindpriceid as icrowdkindpriceid,max(edmc.enddata) as enddata) from Edmcrowdkindpricetab edmc  where edmc.itickettypeid = '"+itickettypeid+"' group by icrowdkindpriceid");
					if (dateList!=null&&dateList.size()>0) {
						for (Map dateMap : dateList) {
							Long icrowdkindpriceid = Long.valueOf(dateMap.get("icrowdkindpriceid").toString());
							endDate = dateMap.get("enddata").toString();
							chadate = Tools.getDayNumbCha(nowday, endDate);
							if (chadate<=interval && chadate>=0) {//即将过期
								map.put("statusstr", "价格将在"+chadate+"后过期。");
								map.put("status", "01");
								jguoqi++;
							}else if (chadate<0) {
								map.put("statusstr", "价格已过期");
								map.put("status", "02");
								yiguoqi++;
							}else {
								map.put("status", "03");
							}
							map.put("icrowdkindpriceid", icrowdkindpriceid);
						}
					}
				}
				resultList.add(map);
			}
		}
		Map<String, Object> flag = new HashMap<String, Object>();
		flag.put("产品价格已过期", yiguoqi);
		flag.put("产品价格即将过期", jguoqi);
		if (resultList!=null) {
			resultList.add(flag);
		}
		return resultList;
	}

	public static void main(String[] args) {
		System.out.println(Tools.getDayNumbCha("2014-04-08", "2014-02-07"));
	}
}
