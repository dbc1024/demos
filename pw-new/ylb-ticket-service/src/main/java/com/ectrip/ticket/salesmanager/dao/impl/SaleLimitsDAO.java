package com.ectrip.ticket.salesmanager.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.salesmanager.dao.ISaleLimitsDAO;


@Repository
public class SaleLimitsDAO extends GenericDao implements ISaleLimitsDAO {
	@Autowired
	private SysService sysService;
	/**
	 * 查看销售权限列表
	 * @param icompanyinfoid:企业id
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public PaginationSupport getSaleLimitsList(Long icompanyinfoid,Long iemployeeid,int page,int pageSize,String url) throws IllegalAccessException, InvocationTargetException{
		PaginationSupport ps =null;
//		StringBuffer hsql=new StringBuffer("select new map(sal.isaleslimitsid as isaleslimitsid,sal.icrowdkindpriceid as icrowdkindpriceid,sal.iemployeeid as iemployeeid,emp.szemployeename as stremployee) from Ospsaleslimitstab sal,Edmcrowdkindpricetab pri,Esfemployeetab emp where sal.icrowdkindpriceid = pri.icrowdkindpriceid and sal.iemployeeid=emp.iemployeeid ");
		StringBuffer hsql=new StringBuffer("select new map(sal.isaleslimitsid as isaleslimitsid,sal.icrowdkindpriceid as icrowdkindpriceid,sal.iemployeeid as iemployeeid) from Ospsaleslimitstab sal,Edmcrowdkindpricetab pri where sal.icrowdkindpriceid = pri.icrowdkindpriceid ");
		if(iemployeeid!=null){
			hsql.append(" and sal.iemployeeid="+iemployeeid);
		}
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List<Map> items = ps.getItems();
		StringBuffer sb = new StringBuffer();
		if(iemployeeid==null) {
			TreeSet<String> treeSet = new TreeSet<>();//iemployeeid去重
			for (int i =0;i<items.size();i++) {
				String employeeid = items.get(i).get("iemployeeid").toString();
				treeSet.add(employeeid);
			}
			Object[] array = treeSet.toArray();
			for (int i = 0; i < array.length; i++) {
				if(i==(array.length-1)) {
					sb.append(array[i]);
				}else {
					sb.append(array[i]+",");
				}
			}
		}else {
			sb.append(iemployeeid);
		}
		List<Map> employeeListByIemployeeid=null;
		if(icompanyinfoid!=null){
			//TODO
			ResponseBean viewCompanyInfo = sysService.viewCompanyInfo(icompanyinfoid);
			Galcompanyinfotab company =new Galcompanyinfotab();
			Map data = (Map) viewCompanyInfo.getData();
			BeanUtils.populate(company,data);
			if("01".equals(company.getCompanytype())){//平台管理
				employeeListByIemployeeid=sysService.getEmployeeListByIemployeeid(sb.toString(),null);
			}
			if("02".equals(company.getCompanytype())){//景区企业
				employeeListByIemployeeid = sysService.getEmployeeListByIemployeeid(sb.toString(),icompanyinfoid);
			}
		}else {
			employeeListByIemployeeid=sysService.getEmployeeListByIemployeeid(sb.toString(),null);
		}
		for (Map employeeList : employeeListByIemployeeid) {
			String iemployeeid1 = employeeList.get("iemployeeid").toString();
			for (Map item : items) {
				String iemployeeid2 = item.get("iemployeeid").toString();
				if(iemployeeid1.equals(iemployeeid2)) {
					item.put("stremployee", employeeList.get("szemployeename"));
				}
			}
		}
		//服务拆分 ：select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01')
		List<Map> sysparamsByParms = sysService.getSysparamsByParms("PDTP", "01", null, "01");
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < sysparamsByParms.size(); i++) {
			sb1.append(sysparamsByParms.get(i).get("pmcd").toString());
			if((sysparamsByParms.size()-1)!=i) {
				sb1.append(",");
			}
		}
		//服务拆分 ：select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in()
		List<Map> sysparamsByParms2 = sysService.getSysparamsByParms("PRTP", null, sb1.toString(), null);
		StringBuffer sb2 = new StringBuffer();
		for (int i = 0; i < sysparamsByParms.size(); i++) {
			sb2.append(sysparamsByParms.get(i).get("pmcd").toString());
			if((sysparamsByParms.size()-1)!=i) {
				sb2.append(",");
			}
		}
		List list = ps.getItems();
		if (list != null && list.size() > 0) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if( map.get("icrowdkindpriceid")!=null){//售价编号
					Object actcontent = map.get("icrowdkindpriceid");
//					String hsqls="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
//							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
//							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+actcontent;
					String hsqls="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in ("+sb2.toString()+") and pri.icrowdkindpriceid="+actcontent;
					List strlist = this.find(hsqls);
					if(strlist.size()>=1){
						for (int j = 0; j< strlist.size(); j++) {
							Object[] a =(Object[]) strlist.get(j);
							map.put("strcrowdkindprice", a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//销售权限组合名称
						}
					}
				}
			}
		}
		return ps;
	}



	/**
	 * 根据销售权限编号得到销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限编号
	 * @return
	 * return:Ospsaleslimitstab
	 * Date:2011-10-5
	 */
	public List  querySaleLimitsList(Long salelimitsId){
		List salelimitlist=new ArrayList();
		Ospsaleslimitstab salelimits = null;
		salelimits = (Ospsaleslimitstab)this.get(Ospsaleslimitstab.class, salelimitsId);
		//服务拆分 ：select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01')
		List<Map> sysparamsByParms = sysService.getSysparamsByParms("PDTP", "01", null, "01");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sysparamsByParms.size(); i++) {
			sb.append(sysparamsByParms.get(i).get("pmcd").toString());
			if((sysparamsByParms.size()-1)!=i) {
				sb.append(",");
			}
		}
		//服务拆分 ：select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in()
		List<Map> sysparamsByParms2 = sysService.getSysparamsByParms("PRTP", null, sb.toString(), null);
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < sysparamsByParms.size(); i++) {
			sb1.append(sysparamsByParms.get(i).get("pmcd").toString());
			if((sysparamsByParms.size()-1)!=i) {
				sb1.append(",");
			}
		}
				if(salelimits!=null){
					//员工信息
					List employeeListByIemployeeinfo = sysService.getEmployeeListByIemployeeid(salelimits.getIemployeeid().toString(), null);
					Map employeeinfo = (Map) employeeListByIemployeeinfo.get(0);
					Esfemployeetab employee=null;
					if(employeeinfo!=null) {
						employee=new Esfemployeetab();
						employee.setSzemployeename(employeeinfo.get("szemployeename").toString());
					}
					if(employee!=null){
						salelimits.setStremployee(employee.getSzemployeename());//员工名称
					}
//					String hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
//							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
//							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+salelimits.getIcrowdkindpriceid() ;
					String hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in ("+sb1.toString()+") and pri.icrowdkindpriceid="+salelimits.getIcrowdkindpriceid() ;
					List list = this.find(hsql);
					if(list.size()>=1){
						for (int j = 0; j < list.size(); j++) {
							Object[] a =(Object[]) list.get(j);
							salelimits.setStrcrowdkindprice(a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//销售权限组合名称
						}
					}
					salelimitlist.add(salelimits);
				}
		return salelimitlist;
	}


	/**
	 * 新增销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void insertSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog){
		String[] crowdkindpriceids = icrowdkindpriceids.split(",");
		Ospsaleslimitstab sale =null;
		for (int i = 0; i < crowdkindpriceids.length; i++) {
			String crowdkindpriceid = crowdkindpriceids[i];
			long maxsale = this.getMaxPk("isaleslimitsid", "Ospsaleslimitstab");
			sale=new Ospsaleslimitstab();
			sale.setIsaleslimitsid(maxsale+1);
			sale.setIcrowdkindpriceid(Long.valueOf(crowdkindpriceid));
			sale.setIemployeeid(iemployeeId);
			String hsql=" from Ospsaleslimitstab where icrowdkindpriceid="+crowdkindpriceid+" and iemployeeid="+iemployeeId;
			List salelist = this.find(hsql);
			if(salelist!=null && salelist.size()>=1){
				continue;//已存在就不处理
			}else{
				this.save(sale);//不存在就保存
				syslog.setIemployeeid(iemployeeId);
				syslog.setStlg("0073");
				syslog.setBrief("销售权限：" + sale.getIsaleslimitsid() );
				syslog.setNote("销售权限增加：" +  sale.getIsaleslimitsid() +",价格编号："+sale.getIcrowdkindpriceid()+",员工编号："+sale.getIemployeeid());
				sysService.insertSyslog(syslog);
			}
		}
	}
	
	/**
	 * 修改销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void updateSalelimit(Long iemployeeId,String icrowdkindpriceids,Syslog syslog){
		String[] crowdkindpriceids = icrowdkindpriceids.split(",");
		String sql=" from Ospsaleslimitstab where iemployeeid="+iemployeeId;
		List salelist = this.find(sql);
		//先删除
		if(salelist.size()>=1){
			Ospsaleslimitstab salelimit=null;
			for (int i = 0; i < salelist.size(); i++) {
				salelimit =(Ospsaleslimitstab) salelist.get(i);
				this.delete(salelimit);
			}
		}
		//再保存	
		Ospsaleslimitstab sale = null;
		for (int i = 0; i < crowdkindpriceids.length; i++) {
			String crowdkindpriceid = crowdkindpriceids[i];
			sale = new Ospsaleslimitstab();
			long maxsale = this.getMaxPk("isaleslimitsid", "Ospsaleslimitstab");
			sale.setIsaleslimitsid(maxsale+1);
			sale.setIcrowdkindpriceid(Long.valueOf(crowdkindpriceid));
			sale.setIemployeeid(iemployeeId);
			this.save(sale);//保存
			syslog.setIemployeeid(iemployeeId);
			syslog.setStlg("0074");
			syslog.setBrief("销售权限：" + sale.getIsaleslimitsid() );
			syslog.setNote("销售权限修改：" +  sale.getIsaleslimitsid() +",价格编号："+sale.getIcrowdkindpriceid()+",员工编号："+sale.getIemployeeid());
			sysService.insertSyslog(syslog);
		}
	}
	/**
	 * 删除销售权限
	 * Describe:
	 * @auth:huangyuqi
	 * @param salelimitsId销售权限id
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void deleteSalelimit(Long iemployeeId,Long icrowdkindpriceid,Syslog syslog){
		String sql=" from Ospsaleslimitstab where iemployeeid="+iemployeeId;
		List salelist = this.find(sql);
		Ospsaleslimitstab salelimit=null;
		if(salelist.size()>=1){
			for (int i = 0; i < salelist.size(); i++) {
				salelimit =(Ospsaleslimitstab) salelist.get(i);
				if(salelimit.getIcrowdkindpriceid()==icrowdkindpriceid) {
					this.delete(salelimit);
				}
			}
		}
		syslog.setIemployeeid(iemployeeId);
		syslog.setStlg("0075");
		syslog.setBrief("销售权限：" + icrowdkindpriceid );
		syslog.setNote("销售权限删除：" + icrowdkindpriceid);
		sysService.insertSyslog(syslog);
	}
}

