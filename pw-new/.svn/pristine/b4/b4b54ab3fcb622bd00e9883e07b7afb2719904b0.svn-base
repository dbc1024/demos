package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.EdmticketcomposepricetabId;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.salesmanager.Osppostsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.ICrowdKindPriceDAO;
import org.springframework.stereotype.Repository;


@Repository
public class CrowdKindPriceDAO extends GenericDao implements ICrowdKindPriceDAO {
	/**
	 * 查看产品价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId产品编号
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url访问地址
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-30
	 */
	public PaginationSupport getCrowskindPriceList(Long productId,int page,int pageSize,String url){
		PaginationSupport ps =null;
		String hsql=" select new map( prc.icrowdkindpriceid as icrowdkindpriceid,prc.icrowdkindpricecode as icrowdkindpricecode, prc.note3 as note3, prc.ipricecategoryid as ipricecategoryid, prc.startdata as startdata, prc.enddata as enddata, prc.ipeoplenumrange as ipeoplenumrange, prc.listingprice as listingprice, prc.weekendprice as weekendprice, prc.mactualsaleprice as mactualsaleprice, prc.jsprice as jsprice,prc.byisuse as byisuse, prc.szmemo as szmemo, prc.isequence as isequence,prd.sztickettypename as sztickettypename,bui.szbusinessname as szbusinessname,ck.szcrowdkindname as szcrowdkindname,prc.isnet as isnet,prc.isscene as isscene,prc.isscancode as isscancode ,sys1.pmva as note1) from Edmcrowdkindpricetab prc,Edmtickettypetab prd,Edmbusinesstab bui,Edpcrowdkindtab ck,Sysparv5 sys1  where prc.itickettypeid="+productId+" and prc.itickettypeid = prd.itickettypeid and prc.ibusinessid = bui.ibusinessid and prc.icrowdkindid = ck.icrowdkindid and sys1.id.pmky='JSFZ' and sys1.id.pmcd=prc.note1 order by prc.icrowdkindpriceid";
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	/**
	 * 增加价格*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ICrowdKindPriceDAO#insertCrowdKindPirce(com.ectrip.model.provider.Edmcrowdkindpricetab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param edmcrowdkindprice
	 * @param xpList
	 * @param syslog
	 * @author huangyuqi
	 * Date:2011-10-11
	 */
	public void insertCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog) {
				this.save(edmcrowdkindprice);
				
				
				if(xpList.size()>=1){
					for (int i = 0; i < xpList.size(); i++) {
						Edmticketcomposepricetab composeprice = new Edmticketcomposepricetab();
						composeprice =(Edmticketcomposepricetab)xpList.get(i);
						
						EdmticketcomposepricetabId composepriceid = new EdmticketcomposepricetabId();
						// 得到最大主键
						long priceid = this.getMaxPk("id.iticketcomposepriceid", new String[]{"id.icrowdkindpriceid"}, new String[]{edmcrowdkindprice.getIcrowdkindpriceid().toString()}, "Edmticketcomposepricetab");
						composepriceid.setIticketcomposepriceid(priceid + 1);
						composepriceid.setIcrowdkindpriceid(edmcrowdkindprice.getIcrowdkindpriceid());//售价id
						composeprice.setId(composepriceid);
						
						this.save(composeprice);
						
					}
				}
				
				Edmcrowdkindpricetab price = this.queryCrowdKindPrice(edmcrowdkindprice.getIcrowdkindpriceid());
				
				syslog.setStlg("0059");
				syslog.setBrief("售价：" + edmcrowdkindprice.getIcrowdkindpriceid() );
				syslog.setNote("售价增加：售价编号：" + edmcrowdkindprice.getIcrowdkindpriceid()+",产品名称："+price.getStrtickettype()+"，业务名称："+price.getStrbusiness()+",人群种类："+price.getStrcrowdkind());
				syslog.setLogdatetime(Tools.getDayTimes());
				Long logid = this.getMaxPk("logid", "Syslog");
				syslog.setLogid(logid + 1);
				this.save(syslog);
	}
	/**
	 * 修改售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmcrowdkindprice
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void updateCrowdKindPirce(Edmcrowdkindpricetab edmcrowdkindprice,List xpList,Syslog syslog){
			this.update(edmcrowdkindprice);		
			Edmcrowdkindpricetab price = this.queryCrowdKindPrice(edmcrowdkindprice.getIcrowdkindpriceid());
			
			
			
			if(xpList.size()>=1){				
				//先删除价格拆分表中的相应数据再增加
				String sql=" from Edmticketcomposepricetab where id.icrowdkindpriceid="+edmcrowdkindprice.getIcrowdkindpriceid() ;
				List composepricelist = this.find(sql);
				if(composepricelist.size()>=1){
					for (int i = 0; i <composepricelist.size(); i++) {
						Edmticketcomposepricetab composeprice = (Edmticketcomposepricetab)composepricelist.get(i);
						//删除价格组合拆分表
						delete(composeprice);
					}
				}
				
				for (int i = 0; i < xpList.size(); i++) {	
					Edmticketcomposepricetab composeprice = new Edmticketcomposepricetab();
					composeprice =(Edmticketcomposepricetab)xpList.get(i);
					
						EdmticketcomposepricetabId composepriceid = new EdmticketcomposepricetabId();
						// 得到最大主键
						long priceid = this.getMaxPk("id.iticketcomposepriceid", new String[]{"id.icrowdkindpriceid"}, new String[]{edmcrowdkindprice.getIcrowdkindpriceid().toString()}, "Edmticketcomposepricetab");
						composepriceid.setIticketcomposepriceid(priceid + 1);
						composepriceid.setIcrowdkindpriceid(edmcrowdkindprice.getIcrowdkindpriceid());//售价id
						composeprice.setId(composepriceid);					
						this.save(composeprice);
				}					
			}
			
			syslog.setStlg("0060");
			syslog.setBrief("售价：" + edmcrowdkindprice.getIcrowdkindpriceid() );		
			syslog.setNote("售价修改：售价编号：" + edmcrowdkindprice.getIcrowdkindpriceid()+",产品名称："+price.getStrtickettype()+"，业务名称："+price.getStrbusiness()+",人群种类："+price.getStrcrowdkind());
			
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
	}
	/**
	 * 删除售价
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindpriceId售价编号
	 * @param syslog
	 * return:void
	 * Date:2011-9-30
	 */
	public void deleteCrowdKindPirce(Long crowdkindpriceId,Syslog syslog){
			Edmcrowdkindpricetab edmcrowdkindprice =(Edmcrowdkindpricetab)this.get(Edmcrowdkindpricetab.class, crowdkindpriceId);
			
			syslog.setStlg("0061");
			syslog.setBrief("售价：" + edmcrowdkindprice.getIcrowdkindpriceid() );
			syslog.setNote("售价删除：售价编号：" + edmcrowdkindprice.getIcrowdkindpriceid() );
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
			
			
			//删除价格组合拆分表
			String sql=" from Edmticketcomposepricetab where id.icrowdkindpriceid="+crowdkindpriceId ;
			List composepricelist = this.find(sql);
			if(composepricelist.size()>=1){
				for (int i = 0; i <composepricelist.size(); i++) {
					Edmticketcomposepricetab composeprice = (Edmticketcomposepricetab)composepricelist.get(i);
					delete(composeprice);
				}
			}
			
			//删除员工销售权限表数据
			
			String sql2 = " from Ospsaleslimitstab where icrowdkindpriceid="+crowdkindpriceId;
			List salelimitlist = this.find(sql2);
			if(salelimitlist!=null && salelimitlist.size()>=1){
				for (int j = 0; j < salelimitlist.size(); j++) {
					Ospsaleslimitstab salelimit = (Ospsaleslimitstab)salelimitlist.get(j);
					this.delete(salelimit);
				}				
			}
			
			//删除岗位销售权限表数据
			String sql3 = " from Osppostsaleslimitstab where icrowdkindpriceid="+crowdkindpriceId;
			List postlimitlist = this.find(sql3);
			if(postlimitlist!=null && postlimitlist.size()>=1){
				for (int j = 0; j < postlimitlist.size(); j++) {
					Osppostsaleslimitstab postlimit = (Osppostsaleslimitstab)postlimitlist.get(j);
					this.delete(postlimit);
				}				
			}
			//删除窗口销售权限表数据
			String sql4 = " from Ospticketwinlimitstab where icrowdkindpriceid="+crowdkindpriceId;
			List winlimitlist = this.find(sql4);
			if(winlimitlist!=null && winlimitlist.size()>=1){
				for (int j = 0; j < winlimitlist.size(); j++) {
					Ospticketwinlimitstab winlimit = (Ospticketwinlimitstab)winlimitlist.get(j);
					this.delete(winlimit);
				}				
			}
			
			this.delete(edmcrowdkindprice);
		
	}
	
	
	/**
	 * 查询价格信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2011-10-4
	 */
	public Edmcrowdkindpricetab queryCrowdKindPrice(Long priceId){
		Edmcrowdkindpricetab price=null;
		//得到价格信息
		price = (Edmcrowdkindpricetab)this.get(Edmcrowdkindpricetab.class, priceId);
		if(price!=null){
			//得到人群种类
			Edpcrowdkindtab crowdkind =(Edpcrowdkindtab)this.get(Edpcrowdkindtab.class, price.getIcrowdkindid());
			if(crowdkind!=null){
				price.setStrcrowdkind(crowdkind.getSzcrowdkindname());//人群种类名称
			}
			 //得到业务类型
			Edmbusinesstab business = (Edmbusinesstab)this.get(Edmbusinesstab.class, price.getIbusinessid());
			if(business!=null){
				price.setStrbusiness(business.getSzbusinessname());//业务类型名称
			}
			//得到票类型（产品）
			Edmtickettypetab ticket = (Edmtickettypetab)this.get(Edmtickettypetab.class, price.getItickettypeid());
			if(ticket!=null){
				price.setStrtickettype(ticket.getSztickettypename());//产品名称
			}
		
		}
		return price;
	}
	
	/**
	 * 得到所有有效价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
	public List getPriceList(){
		List list = new ArrayList();
		String hsql=" from Edmcrowdkindpricetab where byisuse=1";
		list = this.find(hsql);		
		return list;
	}
	
	/**
	 * 根据登录人获取景区的产品的价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeid登录人id
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getLimitsPriceList(Long employeeId){
		StringBuffer pr = new StringBuffer();
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		
		String sql=" from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="+employeeId+")";
		String hsql="";
		List companylist = this.find(sql);
		if(companylist.size()>=1){
			Galcompanyinfotab company = (Galcompanyinfotab)companylist.get(0);
			if("01".equals(company.getCompanytype())){//平台管理
				hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
				"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
				" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP'" +
				" and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) order by prd.iscenicid,prd.itickettypeid,bui.ibusinessid,cro.icrowdkindid " ;
		
			}
			if("02".equals(company.getCompanytype())){//景区企业
				hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
				"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
				" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP'" +
				" and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and prd.iscenicid in(select id.iscenicid from Companyscenic where id.icompanyinfoid="+company.getIcompanyinfoid()+") order by prd.iscenicid,prd.itickettypeid,bui.ibusinessid,cro.icrowdkindid ";
			}
		}
		list = this.find(hsql);
		
		if(list.size()>=1){
			for (int i = 0; i < list.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				Object[] a =(Object[]) list.get(i);
				price.setIcrowdkindpriceid(Long.parseLong(a[0].toString()));
				price.setStrpricename(a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);
				list1.add(price);
			}
			
		}
		
		return list1;
	}
	
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean retriePriceIsuse(Edmcrowdkindpricetab crowdkindprice){
		boolean isuse=false;
		String hsql=" from Edmcrowdkindpricetab where itickettypeid="+crowdkindprice.getItickettypeid()+
				" and (((startdata<='"+crowdkindprice.getStartdata()+"' and enddata>='"+crowdkindprice.getStartdata()+"') or " +
						" (startdata<='"+crowdkindprice.getEnddata()+"' and enddata>='"+crowdkindprice.getEnddata()+"' )) or " +
								"(startdata>'"+crowdkindprice.getStartdata()+"' and enddata<'"+crowdkindprice.getEnddata()+"'))";
		
		
		List list = this.find(hsql);
		
		
		if(list.size()>=1){
			for (int i = 0; i < list.size(); i++) {
				Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)list.get(i);
				
				if( price.getIcrowdkindid().equals(crowdkindprice.getIcrowdkindid())
					&& price.getIbusinessid().equals(crowdkindprice.getIbusinessid())
					&& price.getItickettypeid().equals(crowdkindprice.getItickettypeid())
					&&price.getNote1()!=null&& price.getNote1().equals(crowdkindprice.getNote1())){
					
					isuse=true;
					break;
				}
			}
			
		}
		return isuse;
	}
	
	/**
	 * 判断产品价格日期是否有重复
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindprice价格
	 * @return
	 * return:boolean
	 * Date:2011-10-6
	 */
	public boolean updatePriceIsuse(Edmcrowdkindpricetab crowdkindprice){
		boolean isuse=false;
		
		String hsql=" from Edmcrowdkindpricetab where itickettypeid="+crowdkindprice.getItickettypeid()+
		" and (((startdata<='"+crowdkindprice.getStartdata()+"' and enddata>='"+crowdkindprice.getStartdata()+"') or " +
				" (startdata<='"+crowdkindprice.getEnddata()+"' and enddata>='"+crowdkindprice.getEnddata()+"' )) or " +
						"(startdata>'"+crowdkindprice.getStartdata()+"' and enddata<'"+crowdkindprice.getEnddata()+"')) and icrowdkindpriceid <> "+crowdkindprice.getIcrowdkindpriceid();
		
		List list = this.find(hsql);
		if(list.size()>=1){
			
			for (int i = 0; i < list.size(); i++) {
				Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)list.get(i);
				if( price.getIcrowdkindid().equals(crowdkindprice.getIcrowdkindid())
						&& price.getIbusinessid().equals(crowdkindprice.getIbusinessid())
						&& price.getItickettypeid().equals(crowdkindprice.getItickettypeid())
						&&price.getNote1()!=null&& price.getNote1().equals(crowdkindprice.getNote1())){
						
					isuse=true;
					break;
				}
			}
			
		}
		return isuse;
	}
	
	/**
	 * 读出某产品的服务商下与下级服务商的所有票（不含套票）
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketI产品id
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyProviderProductList(Long iticketId){
		List list = new ArrayList();
		//根据产品编号查询产品编号
		Edmtickettypetab product = (Edmtickettypetab) this.get(
				Edmtickettypetab.class, iticketId);
		
		String sql=" from Edmcrowdkindpricetab pri where pri.itickettypeid = "+iticketId;
		List pricelist = this.find(sql);
		
		String hsql="";
		if(pricelist.size()>=1){	
			//价格
			Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)pricelist.get(0);
			//根据价格编号得到价格组合拆分数据
			String pricesql=" from Edmticketcomposepricetab pricom where pricom.id.icrowdkindpriceid="+price.getIcrowdkindpriceid();
			List pricecompselist = this.find(pricesql);
			StringBuffer pdno = new StringBuffer(); 
			
			if(pricecompselist.size()>=1){
				for (int i = 0; i < pricecompselist.size(); i++) {
					//价格组合拆分
					Edmticketcomposepricetab edmticketcomposepricetab =(Edmticketcomposepricetab) pricecompselist.get(i);				
					if(i==pricecompselist.size()-1){
						pdno.append(edmticketcomposepricetab.getItickettypeid());
					}else{
						pdno.append(edmticketcomposepricetab.getItickettypeid()+",");
					}
				}
			}
			
			hsql="select new map( e.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,s.iscenicid as iscenicid," +
					"s.szscenicname as szscenicname ) from Edmtickettypetab e ,Esbscenicareatab s  where  e.iscenicid = s.iscenicid and  e.itickettypeid in("+pdno.toString()+")";
			
		}else{//and (s.iparentid="+product.getIscenicid()+" or s.iscenicid="+product.getIscenicid()+")
			hsql="select new map( e.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,s.iscenicid as iscenicid,s.szscenicname as szscenicname ) from Edmtickettypetab e,Sysparv5 v , Esbscenicareatab s  where  e.iscenicid = s.iscenicid  and s.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where (sys1.id.pmcd='01' or sys1.spmcd='01') and sys1.id.pmky='PDTP') and e.bycategorytype=v.id.pmcd and v.id.pmky='PRTP' and e.bycategorytype<>'0010' order by s.iscenicid,e.itickettypeid ";
		}
			
		list = this.find(hsql);
		
		return list;
	}
	/**
	 * 读出某产品的服务商下与下级服务商的所有票（不含套票），并不含有当前价格
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId 票类型编号
	 * @param priceid 价格编号
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyProviderProductList(Long iticketId,Long priceid){
		List list = new ArrayList();
		//根据产品编号查询产品编号
		Edmtickettypetab product = (Edmtickettypetab) this.get(
				Edmtickettypetab.class, iticketId);
		
		String sql=" from Edmcrowdkindpricetab pri where pri.itickettypeid = "+iticketId+" and pri.icrowdkindpriceid<>"+priceid;
		List pricelist = this.find(sql);
		
		String hsql="";
		if(pricelist.size()>=1){	
			//价格
			Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)pricelist.get(0);
			//根据价格编号得到价格组合拆分数据
			String pricesql=" from Edmticketcomposepricetab pricom where pricom.id.icrowdkindpriceid="+price.getIcrowdkindpriceid();
			List pricecompselist = this.find(pricesql);
			StringBuffer pdno = new StringBuffer(); 
			
			if(pricecompselist.size()>=1){
				for (int i = 0; i < pricecompselist.size(); i++) {
					//价格组合拆分
					Edmticketcomposepricetab edmticketcomposepricetab =(Edmticketcomposepricetab) pricecompselist.get(i);				
					if(i==pricecompselist.size()-1){
						pdno.append(edmticketcomposepricetab.getItickettypeid());
					}else{
						pdno.append(edmticketcomposepricetab.getItickettypeid()+",");
					}
				}
			}
			
			hsql="select new map( e.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,s.iscenicid as iscenicid," +
					"s.szscenicname as szscenicname ) from Edmtickettypetab e ,Esbscenicareatab s  where  e.iscenicid = s.iscenicid and  e.itickettypeid in("+pdno.toString()+")";
			
		}else{//and (s.iparentid="+product.getIscenicid()+" or s.iscenicid="+product.getIscenicid()+") 
			hsql="select new map( e.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,s.iscenicid as iscenicid,s.szscenicname as szscenicname ) from Edmtickettypetab e,Sysparv5 v , Esbscenicareatab s  where  e.iscenicid = s.iscenicid and s.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where (sys1.id.pmcd='01' or sys1.spmcd='01') and sys1.id.pmky='PDTP') and e.bycategorytype=v.id.pmcd and v.id.pmky='PRTP' and e.bycategorytype<>'0010' order by s.iscenicid,e.itickettypeid ";
		}
			
		list = this.find(hsql);
		
		return list;
	}
	/**
	 * 读出产品价格拆分数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId价格编号
	 * @param ProductId产品编号
	 * @return
	 * return:List
	 * Date:2011-10-10
	 */
	public List QureyComposePriceList(Long priceId){
		String hsql=" from Edmticketcomposepricetab where id.icrowdkindpriceid=?";
		List list = null;
		list = this.find(hsql, new Object[]{priceId} );
		return list;
	}
	
	/**
	 * 根据产品编号读出价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param iticketId
	 * @return
	 * return:List
	 * Date:2011-10-27
	 */
	public List getpriceListbyprno(Long iticketId){
		
		String sql=" from Edmcrowdkindpricetab pri where pri.itickettypeid = "+iticketId;
		List pricelist = this.find(sql);
		return pricelist;
	}
	
	/**
	 * *
	 * Describe:判断售价编号是否重复
	 * @see com.ectrip.system.provider.dao.idao.ICrowdKindPriceDAO#getcrowpricecode(java.lang.String)
	 * @param icrowdkindpricecode
	 * @return
	 * @author lijingrui
	 * Date:Nov 7, 2011
	 */
	public boolean getcrowpricecode(String icrowdkindpricecode){
		String sql=" from Edmcrowdkindpricetab where icrowdkindpricecode='"+icrowdkindpricecode+"'";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * *
	 * Describe:判断售价编号是否重复
	 * @see com.ectrip.system.provider.dao.idao.ICrowdKindPriceDAO#getcrowpricecode(java.lang.String)
	 * @param icrowdkindpricecode价格编号
	 * @param iscenicid 服务商编号
	 * @return
	 * @author lijingrui
	 * Date:Nov 7, 2011
	 */
	public boolean getcrowpricecode(String icrowdkindpricecode,Long iscenicid ){
		String sql=" from Edmcrowdkindpricetab where icrowdkindpricecode='"+icrowdkindpricecode+"' and itickettypeid in(select itickettypeid from Edmtickettypetab where iscenicid = "+iscenicid+") ";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 判断订单中是否存在数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId
	 * @return
	 * return:boolean
	 * Date:2011-11-24
	 */
	public boolean queryPriceIsUse(Long priceId){
		boolean isuse = false;
		int num = 0;
		//判断网上订单服务商出票明细
		String hsql=" from TOrderlist where icrowdkindpriceid = "+priceId;
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			num=num+1;
		}else{
			num = num+0;
		}
		//判断网上出票服务商子票明细
		String hsql1=" from TZorderlist where icrowdkindpriceid = "+priceId;
		List list1 = this.find(hsql1);
		if(list1!=null && list1.size()>=1){
			num=num+1;
		}else{
			num = num+0;
		}
		//判断网上预定服务商订单明细
		String hsql2=" from YOrderlist where icrowdkindpriceid = "+priceId;
		List list2 = this.find(hsql2);
		if(list2!=null && list2.size()>=1){
			num=num+1;
		}else{
			num = num+0;
		}
		
		//判断子票销售明细表
		String hsql3=" from Stscomticketsalesdetailstab where icrowdkindpriceid = "+priceId;
		List list3 = this.find(hsql3);
		if(list3!=null && list3.size()>=1){
			num=num+1;
		}else{
			num = num+0;
		}
		
		//判断销售凭证明细表
		String hsql4=" from Stssalesvoucherdetailstab where icrowdkindpriceid = "+priceId;
		List list4 = this.find(hsql4);
		if(list4!=null && list4.size()>=1){
			num=num+1;
		}else{
			num = num+0;
		}
		
		
		if(num==0){
			isuse = false;
		}else{
			isuse = true;
		}		
		return isuse;
	}
}

