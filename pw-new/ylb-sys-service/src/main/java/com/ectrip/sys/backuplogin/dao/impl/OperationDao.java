package com.ectrip.sys.backuplogin.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.backuplogin.dao.IOperationDao;
import com.ectrip.sys.model.backupadmin.Operation;
import com.ectrip.sys.model.backupadmin.OperationId;

/**
 * 读取operation 视图中的所有数据
 * 
 * @author LiJin
 * 
 */
@Repository
public class OperationDao extends GenericDao implements IOperationDao {
	
	@Resource
    public void setSessionFactoryOverride(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	/**
	 * 根据用户读取用户的权限
	 */
	public List getAllEmployeeOperation(String empid) throws Exception {
		// TODO Auto-generated method stub
		return null;

	}

	/**
	 * 根据用户读取用户的权限
	 * 
	 */
	public List getAllOperation() throws Exception {
		// TODO Auto-generated method stub
		String sql = " from Operation order by ordernum";

		List list = this.find(sql);
		return list;

	}

	/**
	 * 根据上上级菜单ID读到下级菜单ID
	 */
	public List findbyempid(String empid, BigDecimal popid) {
		// TODO Auto-generated method stub

		return this.findbyempid(empid, popid, "");
	}
	// select es.* from Esfemployeepoststab e,operation es,Esfemployeetab emp where emp.empid ='admin' and
	// e.iemployeeid= emp.iemployeeid and e.ipostsid=es.opid;

	public List findbyempid(String empid, BigDecimal popid, String licid) {
		// TODO Auto-generated method stub

		String systemid = WebContant.GetKeyValue("systemid", "0");
		List<Map> maplist = null;
		List list = new ArrayList();
		//System.out.print("权限licid="+licid);
		try {
			String sql = "";
		
			if (  licid.equals(""))
			{
				
				sql = "select  distinct  o.*,et.picidf,et.picids,u.upadder||u.upfilename as picurlf ,uu.upadder||uu.upfilename as picurls  from  OPERATIONR o,espdutytab et,upfile uu,upfile u where o.popid = " + popid
				+ " and o.iversion = " + systemid + " and  o.empid = '" + empid +"' and o.opid=et.idutyid and u.upid=et.picidf and uu.upid=et.picids order by o.ordernum" ;
				
				/*sql="select b.*,uu.upadder||uu.upfilename as picurls from ( select a.*,u.upadder||u.upfilename as picurlf from (select distinct o.*,et.picidf,et.picids from OPERATIONR o,espdutytab et where o.popid ="
					+ popid + " and o.iversion = " + systemid + " and  o.empid = '" + empid + "' and o.opid=et.idutyid ) a left join upfile u on a.picidf =u.upid) b left join upfile uu on b.picids=uu.upid order by b.ordernum;";*/
				
				/*sql = "select  distinct  *  from  OPERATIONR o where o.popid = " + popid
			+ " and o.iversion = " + systemid + " and  o.empid = '" + empid + "'   order by o.ordernum" ;*/
				
				
		     // Debug.print(sql);
			}
			else
			{
				//去掉最后一个“，”；
				licid = licid.replaceAll(",,",",");
				if ( licid.substring(licid.length() - 1) .equals(","))
				{
					licid = licid.substring(0,licid.length() - 1);
				}
				
				sql = "select  distinct  o.*,et.picidf,et.picids,u.upadder||u.upfilename as picurlf ,uu.upadder||uu.upfilename as picurls  from  OPERATIONR o,espdutytab et,upfile uu,upfile u where " + "( o.opid in ("+licid+") or o.opjb > 2 )  and  o.popid = " + popid
						+ " and o.iversion = " + systemid + " and  o.empid = '" + empid +"' and o.opid=et.idutyid and u.upid=et.picidf and uu.upid=et.picids order by o.ordernum" ;
				
				
				/*sql = "select  distinct  *  from  OPERATIONR o where " + "( o.opid in ("+licid+") or o.opjb > 2 )  and  o.popid = " + popid
				+ " and o.iversion = " + systemid + " and  o.empid = '" + empid + "'   order by o.ordernum" ;*/
			}
			//Debug.print("权限="+sql);
			maplist = findBySqlToMap(sql);

			for (Map m : maplist) {
				
				
				int	columnCount = m.keySet().size();
				
			    String[] arr = (String[]) m.keySet().toArray(new String[0]);
				
				//Debug.print(" m="+ m);
				Operation operation = new Operation();
				OperationId id = new OperationId();
				
			//	OPJB=1, GRTP=01, OPURL=javascript:void(0), EMPID=dingyou, IVERSION=0, ISVALID=1, ORDERNUM=0, OPID=1, OPNOTE=safsafasfas, OPNAME=组织架构管理, POPID=0}
			
				id.setEmpid(m.get("EMPID")+"");
				id.setGrtp(m.get("GRTP")+"");
				id.setIsvalid( BigDecimal.valueOf(  Long .valueOf(m.get("ISVALID")+"")));
				id.setIversion(BigDecimal.valueOf(  Long .valueOf(m.get("IVERSION")+"")));
				id.setOpid(BigDecimal.valueOf(  Long .valueOf(m.get("OPID")+"")));
				id.setOpjb(BigDecimal.valueOf(  Long .valueOf(m.get("OPJB")+"")));
			
				id.setOpname(m.get("OPNAME")+"") ;
				id.setOpnote(m.get("OPNOTE")+"");
				id.setOpurl(m.get("OPURL")+"") ;
				id.setOrdernum(BigDecimal.valueOf(  Long .valueOf(m.get("ORDERNUM")+""))) ;
				id.setPopid(BigDecimal.valueOf(  Long .valueOf(m.get("POPID")+"")));
				//何安洲新加
				id.setPicurlf(m.get("PICURLF")+"");
				id.setPicurls(m.get("PICURLS")+"");
			
				operation.setId(id);
				list.add(operation); // 这个操作在当前方法的上下文是合法的。
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 旧的读取后台菜单的方法 0.2 版 
		 * String sql = "  from Operation o where o.id.popid=" + popid + " and o.id.iversion =" +
		 * systemid + " and  o.id.empid = '" + empid + "'  order by ordernum  "; 这是 0.1 版 // String sql =
		 * " from operation es,Esfemployeepoststab e,Esfemployeetab emp where emp.empid ='" +empid+ //
		 * "' and e.iemployeeid= emp.iemployeeid  and e.ipostsid=es.opid";
		 * 
		 * //List list = this.find(sql);
		 */

		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Operation.class);
		// detachedCriteria.setProjection(Projections.distinct(Projections.id().))
		// criteria.setProjection(Projections.distinct(Projections.id()));
		// detachedCriteria.setProjection(projection)
		// detachedCriteria.setProjection((Projection)
		// detachedCriteria.setProjection(Projections.distinct(Projections.id())));

		// List list = getHibernateTemplate().findByCriteria(detachedCriteria);

		// return result;

		return list;
	}
}
