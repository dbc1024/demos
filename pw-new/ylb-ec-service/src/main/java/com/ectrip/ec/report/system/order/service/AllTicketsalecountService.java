package com.ectrip.ec.report.system.order.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.ec.report.system.datereport.dao.idao.IReportDataDAO;
import com.ectrip.ec.report.system.order.service.iservice.IAllTicketsalecountService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class AllTicketsalecountService implements IAllTicketsalecountService{
	
	private IReportDataDAO reportdataDao;
	
	public IReportDataDAO getReportdataDao() {
		return reportdataDao;
	}

	public void setReportdataDao(IReportDataDAO reportdataDao) {
		this.reportdataDao = reportdataDao;
	}

	/**
	 * *
	 * Describe:��Ⱥ����
	 * @see com.ectrip.report.system.order.service.iservice.IAllTicketsalecountService#getCrowdKindList()
	 * @return
	 * @author lijingrui
	 * Date:2013-5-26
	 */
	public List getCrowdKindList(){
		List list =new ArrayList();
		String hsql=" from Edpcrowdkindtab where byisuse=1 order by ilevelsequence ";
		list = this.reportdataDao.find(hsql);
		return list;
	}
	
	/**
	 * *
	 * Describe:ҵ������
	 * @see com.ectrip.report.system.order.service.iservice.IAllTicketsalecountService#getBusinessList()
	 * @return
	 * @author lijingrui
	 * Date:2013-5-26
	 */
	public List getBusinessList(){
		List list = new ArrayList();
		String hsql=" from Edmbusinesstab where byisuse=1 order by szbusinesscode ";
		list = this.reportdataDao.find(hsql);
		return list;
	}
	
	/**
	 * *
	 * Describe:Э�鵥λ
	 * @see com.ectrip.report.system.order.service.iservice.IAllTicketsalecountService#getCustomList()
	 * @return
	 * @author lijingrui
	 * Date:2013-5-26
	 */
	public List getCustomList(Long ibusinessid){
		String sql=" from Custom where lgtp='02' and ttlb='01' and ibusinessid="+ibusinessid+" and ustp='01' and status='01' order by bname ";
		return this.reportdataDao.find(sql);
	}
	
	/**
	 * *
	 * Describe:��ȡ���Ʊ�е���·
	 * @see com.ectrip.report.system.order.service.iservice.IAllTicketsalecountService#getAllticket()
	 * @return
	 * @author lijingrui
	 * Date:2013-5-26
	 */
	public String getAllticket(){
		StringBuffer seq=new StringBuffer();
		String sql="from Edmtickettypetab where bycategorytype='0010' and itickettypeid in (select itickettypeid from Edmticketproduct where inoteger5=0) and byisuse=1";
		List list=this.reportdataDao.find(sql);
		if(list!=null&&list.size()>0){
			
		}else{
			String hsql=" from Edmtickettypetab where bycategorytype='0010'";
			list=this.reportdataDao.find(hsql);
		}
		
		for(int i=0;i<list.size();i++){
			Edmtickettypetab edmticket=(Edmtickettypetab)list.get(i);
			if(i==list.size()-1){
				seq.append(edmticket.getItickettypeid());
			}else{
				seq.append(edmticket.getItickettypeid()+",");
			}
		}
		
		return seq.toString();
	}

	/**
	 * *
	 * Describe:��ȡ������ķ������µ����Ʊ
	 * @see com.ectrip.report.system.order.service.iservice.IAllTicketsalecountService#getListedmticketes(com.ectrip.model.employee.Esfemployeetab, java.lang.String)
	 * @param esfemployee
	 * @param type  0 ��ȡ��Ʒ����������Ʊ����Ĳ�Ʒ   1 ��ȡ�������Ʊ
	 * @param bycate  0 ����   1 ����    2���Ʊ
	 * @return
	 * @author lijingrui
	 * Date:2013-5-29
	 */
	public List getListedmticketes(Esfemployeetab esfemployee,String type,String bycate){
		StringBuffer sql=new StringBuffer();
		sql.append(" from Edmtickettypetab where byisuse=1 ");
/*		if(esfemployee!=null){
			if (esfemployee.getCompanytype().equals("02")) {
				sql.append(" and iscenicid in (select esb.iscenicid from Companyscenic c,Esbscenicareatab esb where c.id.iscenicid=esb.iscenicid and c.id.icompanyinfoid="+esfemployee.getIcompanyinfoid()+" and esb.isjd=0 and esb.scenictype='01' and esb.byisuse=1)");
			}else{
				sql.append(" and iscenicid in (select esb.iscenicid from Esbscenicareatab esb where esb.isjd=0 and esb.scenictype='01' and esb.byisuse=1)");
			}
		}*/
		
		if(bycate!=null&&!bycate.equals("")){
			if(bycate.equals("1")){
				sql.append(" and bycategorytype!='0010'");
				
				if(esfemployee!=null){
					if (esfemployee.getCompanytype().equals("02")) {
						sql.append(" and iscenicid in (select esb.iscenicid from Companyscenic c,Esbscenicareatab esb where c.id.iscenicid=esb.iscenicid and c.id.icompanyinfoid="+esfemployee.getIcompanyinfoid()+" and esb.isjd=0 and esb.scenictype='01' and esb.byisuse=1)");
					}else{
						sql.append(" and iscenicid in (select esb.iscenicid from Esbscenicareatab esb where esb.isjd=0 and esb.scenictype='01' and esb.byisuse=1)");
					}
				}
				
			}else if(bycate.equals("2")){
				sql.append(" and bycategorytype='0010' and iscenicid in (select esb.iscenicid from Esbscenicareatab esb where esb.isjd=0 and esb.scenictype='01' and esb.byisuse=1) ");
			}
		}
		
		if(type!=null&&!type.equals("")){
			if(type.equals("0")){
				sql.append(" and itickettypeid in (select itickettypeid from Edmticketproduct where inoteger5=0) ");
			}
		}
		
		return this.reportdataDao.find(sql.toString());
	}
	
}

