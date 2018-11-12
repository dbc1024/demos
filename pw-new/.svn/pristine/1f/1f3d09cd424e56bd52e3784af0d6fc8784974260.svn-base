package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketemployee;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.provider.dao.IEsbticketEmployeeDAO;
@Repository
public class EsbticketEmployeeDAO extends GenericDao implements IEsbticketEmployeeDAO{

	
	/**
	 * *
	 * Describe:获取所有的员工
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#getEmployeeAllList()
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List getEmployeeAllList(){
		String sql="  from Esfemployeetab where byisuse=1 order by iemployeeid";
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:显示出员工售票点权限信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#queryListempTicket(java.lang.Long, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public PaginationSupport queryListempTicket(Long iemployeeid,int pageSize,int startIndex, String url){
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(ep.seq as seq,ep.iemployeeid as iemployeeid,esf.szemployeename as szemployeename,ep.iticketstationid as iticketstationid,esb.szstationname as szstationname) from Esbticketemployee ep,Esbticketstationtab esb,Esfemployeetab esf where esf.iemployeeid=ep.iemployeeid and esb.iticketstationid=ep.iticketstationid ");
		if(iemployeeid!=null&&!iemployeeid.equals("")){
			hsql.append(" and ep.iemployeeid="+iemployeeid);
		}
		hsql.append(" order by ep.iemployeeid ");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:新增员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#insertEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param stationes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void insertEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog){
		
		if(stationes!=null&&stationes.length>0){
			for(int i=0;i<stationes.length;i++){
				Esbticketemployee esbemp=new Esbticketemployee();
				
				Long maxid = this.getMaxPk("seq", "Esbticketemployee");
				esbemp.setSeq(maxid+1L);
				esbemp.setIemployeeid(esbtickemp.getIemployeeid());
				esbemp.setIticketstationid(new Long(stationes[i]));
				
				this.save(esbemp);
			}
		}
		
		syslog.setStlg("0512");
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esbtickemp.getIemployeeid());
		
		syslog.setBrief("员工售票点："+esf.getSzemployeename());
		syslog.setNote("新增员工售票点：" +esf.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	
	/**
	 * *
	 * Describe:修改员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#updateEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param stationes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void updateEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog){
		
		String hsql=" from Esbticketemployee where iemployeeid="+esbtickemp.getIemployeeid();
		List lst=this.find(hsql);
		if(lst!=null&&lst.size()>0){
			for(int x=0;x<lst.size();x++){
				Esbticketemployee es=(Esbticketemployee)lst.get(x);
				
				this.delete(es);
			}
		}
		
		if(stationes!=null&&stationes.length>0){
			for(int i=0;i<stationes.length;i++){
				Esbticketemployee esbemp=new Esbticketemployee();
				
				Long maxid = this.getMaxPk("seq", "Esbticketemployee");
				esbemp.setSeq(maxid+1L);
				esbemp.setIemployeeid(esbtickemp.getIemployeeid());
				esbemp.setIticketstationid(new Long(stationes[i]));
				
				this.save(esbemp);
			}
		}
		
		syslog.setStlg("0513");
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esbtickemp.getIemployeeid());
		
		syslog.setBrief("员工售票点："+esf.getSzemployeename());
		syslog.setNote("修改员工售票点：" +esf.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:删除员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#deleteEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void deleteEsbticketEmp(Esbticketemployee esbtickemp,Syslog syslog){
		Esbticketemployee esb=(Esbticketemployee)this.get(Esbticketemployee.class, esbtickemp.getSeq());
		
		syslog.setStlg("0514");
		Esbticketstationtab station=(Esbticketstationtab)this.get(Esbticketstationtab.class, esb.getIticketstationid());
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esb.getIemployeeid());
		
		syslog.setBrief("员工售票点：" + esb.getSeq()+"  员工："+esf.getSzemployeename());
		syslog.setNote("删除员工售票点：" + esb.getSeq()+"  员工："+esf.getSzemployeename()+"   售票点："+station.getSzstationname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(esb);
	}
	
	/**
	 * *
	 * Describe:查看员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#getviewEsbticketEmp(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public Esbticketemployee getviewEsbticketEmp(Long seq) throws Exception{
		String sql="select new map(ep.seq as seq,ep.iemployeeid as iemployeeid,esf.szemployeename as szemployeename,ep.iticketstationid as iticketstationid,esb.szstationname as szstationname) from Esbticketemployee ep,Esbticketstationtab esb,Esfemployeetab esf where esf.iemployeeid=ep.iemployeeid and esb.iticketstationid=ep.iticketstationid and ep.seq="+seq;
		List list=this.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbticketemployee ts = new Esbticketemployee();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:获取所有售票点信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#findListemptick(java.lang.String)
	 * @param scenicids
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List findListemptick(String scenicids){
		String sql=" from Esbticketstationtab esb where esb.byisuse=1 ";
		if(scenicids!=null&&!scenicids.equals("")){
			sql+=" and esb.iscenicid in ("+scenicids+")";
		}
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:根据员工查找售票点信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#checkListemptick(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List checkListemptick(Long iemployeeid){
		String sql=" from Esbticketemployee where iemployeeid="+iemployeeid;
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:根据类型来获取售票点
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#showAllesbticket(java.lang.String, java.lang.String)
	 * @param type  1-根据登录人所管理的服务商来获取售票点   2-根据员工售票点权限来获取
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List showAllesbticket(String type,Long iemployeeid,String scenics){
		List list =new ArrayList();
		if(type!=null&&!type.equals("")){
			if(type.equals("1")){
				String sql = " from Esbticketstationtab where byisuse=1";
				if(scenics!=null&&!scenics.equals("")){
					sql+=" and iscenicid in ("+scenics+")";
				}
				list = this.find(sql);
			}else{
				String hsql=" from Esbticketstationtab where byisuse=1 and  iticketstationid in (select iticketstationid from Esbticketemployee where iemployeeid="+iemployeeid+")";
				list = this.find(hsql);
			}
		}else{
			String sql = " from Esbticketstationtab where byisuse=1";
			if(scenics!=null&&!scenics.equals("")){
				sql+=" and iscenicid in ("+scenics+")";
			}
			list = this.find(sql);
		}
		return list;
	}
}

