package com.ectrip.ticket.afcset.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbgardEmployeeDAO;
import com.ectrip.ticket.model.afcset.Esbgardemployee;

public class EsbgardEmployeeDAO extends GenericDao implements IEsbgardEmployeeDAO{

	/**
	 * *
	 * Describe:获取所有的员工
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#getEmployeeAllList()
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List getEmployeeAllList(){
		String sql="  from Esfemployeetab where byisuse=1 order by iemployeeid";
		return this.find(sql);
	}

	/**
	 * *
	 * Describe:显示出员工园门权限信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#queryListempGarden(java.lang.Long, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public PaginationSupport queryListempGarden(Long iemployeeid,int pageSize,int startIndex, String url){
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(ep.seq as seq,ep.iemployeeid as iemployeeid,esf.szemployeename as szemployeename,ep.igardengateid as igardengateid,esb.szgardengatename as szgardengatename,ep.szmemo as szmemo) from Esbgardemployee ep,Esbgardengatetab esb,Esfemployeetab esf where ep.igardengateid=esb.id.igardengateid and ep.iemployeeid=esf.iemployeeid and esb.byisuse=1 ");
		if(iemployeeid!=null&&!iemployeeid.equals("")){
			hsql.append(" and ep.iemployeeid="+iemployeeid);
		}
		hsql.append(" order by ep.iemployeeid ");
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:新增员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#insertEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void insertEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog){

		if(gardenes!=null&&gardenes.length>0){
			for(int i=0;i<gardenes.length;i++){
				Esbgardemployee esbemp=new Esbgardemployee();

				Long maxid = this.getMaxPk("seq", "Esbgardemployee");
				esbemp.setSeq(maxid+1L);
				esbemp.setIemployeeid(esbgardemp.getIemployeeid());
				esbemp.setIgardengateid(new Long(gardenes[i]));

				this.save(esbemp);
			}
		}

		syslog.setStlg("0516");
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esbgardemp.getIemployeeid());

		syslog.setBrief("员工园门："+esf.getSzemployeename());
		syslog.setNote("新增员工园门权限：" +esf.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	/**
	 * *
	 * Describe:修改员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#updateEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void updateEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog){

		String hsql=" from Esbgardemployee where iemployeeid="+esbgardemp.getIemployeeid();
		List lst=this.find(hsql);
		if(lst!=null&&lst.size()>0){
			for(int x=0;x<lst.size();x++){
				Esbgardemployee es=(Esbgardemployee)lst.get(x);

				this.delete(es);
			}
		}

		if(gardenes!=null&&gardenes.length>0){
			for(int i=0;i<gardenes.length;i++){
				Esbgardemployee esbemp=new Esbgardemployee();

				Long maxid = this.getMaxPk("seq", "Esbgardemployee");
				esbemp.setSeq(maxid+1L);
				esbemp.setIemployeeid(esbgardemp.getIemployeeid());
				esbemp.setIgardengateid(new Long(gardenes[i]));

				this.save(esbemp);
			}
		}

		syslog.setStlg("0517");
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esbgardemp.getIemployeeid());

		syslog.setBrief("员工园门："+esf.getSzemployeename());
		syslog.setNote("修改员工园门：" +esf.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *
	 * Describe:删除员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#deleteEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void deleteEsbgardEmp(Esbgardemployee esbgardemp,Syslog syslog){
		Esbgardemployee esb=(Esbgardemployee)this.get(Esbgardemployee.class, esbgardemp.getSeq());

		syslog.setStlg("0518");
		Esfemployeetab esf =(Esfemployeetab)this.get(Esfemployeetab.class, esb.getIemployeeid());

		syslog.setBrief("员工园门：" + esb.getSeq()+"  员工："+esf.getSzemployeename());
		syslog.setNote("删除员工园门：" + esb.getSeq()+"  员工："+esf.getSzemployeename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		this.delete(esb);
	}

	/**
	 * *
	 * Describe:查看员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#getviewEsbgardEmp(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public Esbgardemployee getviewEsbgardEmp(Long seq) throws Exception{
		String sql="select new map(ep.seq as seq,ep.iemployeeid as iemployeeid,esf.szemployeename as szemployeename,ep.igardengateid as igardengateid,esb.szgardengatename as szgardengatename,ep.szmemo as szmemo) from Esbgardemployee ep,Esbgardengatetab esb,Esfemployeetab esf where ep.igardengateid=esb.id.igardengateid and ep.iemployeeid=esf.iemployeeid and esb.byisuse=1 and ep.seq="+seq;
		List list=this.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbgardemployee ts = new Esbgardemployee();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}

	/**
	 * *
	 * Describe:获取所有园门信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#findListGardengates(java.lang.String)
	 * @param scenicids
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List findListGardengates(String scenicids){
		StringBuffer hsql=new StringBuffer();
		hsql.append(" from Esbgardengatetab where byisuse=1  ");
		if(scenicids!=null&&!scenicids.equals("")){
			hsql.append(" and iscenicid in ("+scenicids+") ");
		}
		return this.find(hsql.toString());
	}

	/**
	 * *
	 * Describe:根据员工查找园门信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#checkListGardengates(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List checkListGardengates(Long iemployeeid){
		String sql=" from Esbgardemployee where iemployeeid="+iemployeeid;
		return this.find(sql);
	}

	/**
	 * *
	 * Describe:根据类型来获取园门
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#showAllesbgardenGate(java.lang.String, java.lang.Long, java.lang.String)
	 * @param type   1-根据登录人所管理的服务商来获取园门   2-根据员工园门权限来获取
	 * @param iemployeeid
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List showAllesbgardenGate(String type,Long iemployeeid,String scenics){
		List list =new ArrayList();
		if(type!=null&&!type.equals("")){
			if(type.equals("1")){
				String sql = " from Esbgardengatetab where byisuse=1";
				if(scenics!=null&&!scenics.equals("")){
					sql+=" and iscenicid in ("+scenics+")";
				}
				list = this.find(sql);
			}else{
				String hsql=" from Esbgardengatetab where byisuse=1 and  id.igardengateid in (select igardengateid from Esbgardemployee where iemployeeid="+iemployeeid+")";
				list = this.find(hsql);
			}
		}else{
			String sql = " from Esbgardengatetab where byisuse=1 ";
			if(scenics!=null&&!scenics.equals("")){
				sql+=" and iscenicid in ("+scenics+")";
			}
			list = this.find(sql);
		}
		return list;
	}

}

