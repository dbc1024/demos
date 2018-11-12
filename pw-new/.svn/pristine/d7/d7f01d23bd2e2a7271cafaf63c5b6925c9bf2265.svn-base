package com.ectrip.ec.report.system.order.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;
import com.ectrip.ec.report.system.order.service.iservice.ILxsticketsaleService;
import com.ectrip.sys.model.employee.Esfemployeetab;

public class LxsticketsaleService implements ILxsticketsaleService{
	
	private IReportDataDAO reportdataDao;
	
	public IReportDataDAO getReportdataDao() {
		return reportdataDao;
	}

	public void setReportdataDao(IReportDataDAO reportdataDao) {
		this.reportdataDao = reportdataDao;
	}
	
	/**
	 * 
	 * Describe:��ȡ������ķ������µ���������
	 * @author:lijingrui
	 * @param esfemployee
	 * @return
	 * return:List
	 * Date:2014-4-30
	 */
	public List showListLxsqy(Esfemployeetab esfemployee){
		StringBuffer sql=new StringBuffer();
		sql.append(" from Sysparv5 sys1 where sys1.id.pmky='LXQY' and sys1.id.pmcd not like'%*%' ");
		if(esfemployee!=null){
			if (esfemployee.getCompanytype().equals("02")) {
				sql.append(" and (sys1.id.pmcd in ('0000')  or sys1.id.pmcd in (select esp.pmcd from Esplxqytab esp where esp.iscenicid in ("+esfemployee.getScenics()+") ) )");
			}else{
				sql.append(" and (sys1.id.pmcd in ( select esp.pmcd from Esplxqytab esp ) or sys1.id.pmcd in ('0000') )");
			}
		}else{
			sql.append(" and (sys1.id.pmcd in ( select esp.pmcd from Esplxqytab esp ) or sys1.id.pmcd in ('0000') )");
		}
		sql.append(" order by sys1.id.pmcd ");
		
		return reportdataDao.find(sql.toString());
	}
	
	/**
	 * 
	 * Describe:��ȡĳ���������µ�������
	 * @author:lijingrui
	 * @param pmcd
	 * @return
	 * return:List
	 * Date:2014-4-30
	 */
	public List showListcustom(Esfemployeetab esfemployee,String pmcd){
		List lst=new ArrayList();
		String sql="";
		if(pmcd!=null&&pmcd.equals("1000")){
			sql=" from Custom where lgtp = '02' and ustp = '01' and ttlb = '01'";
			lst=reportdataDao.find(sql);
		}else{
			if(esfemployee!=null){
				if (esfemployee.getCompanytype().equals("02")) {
					sql=" from Custom where usid in ( select esp.usid from Esplxqytab esp where esp.pmcd='"+pmcd+"' and esp.iscenicid in ("+esfemployee.getScenics()+") )";
				}else{
					sql=" from Custom where usid in ( select esp.usid from Esplxqytab esp where esp.pmcd='"+pmcd+"' )";
				}
			}
			lst=reportdataDao.find(sql);
			
			if(pmcd!=null&&pmcd.equals("0000")){
				String hql="";
				if(esfemployee!=null){
					if (esfemployee.getCompanytype().equals("02")) {
						hql=" from Custom where usid not in ( select esp.usid from Esplxqytab esp where esp.iscenicid in ("+esfemployee.getScenics()+") )";
					}else{
						hql=" from Custom where usid not in ( select esp.usid from Esplxqytab esp )";
					}
				}
				List list=reportdataDao.find(hql);
				lst.addAll(list);
			}
		}
		
		return lst;
	}

}

