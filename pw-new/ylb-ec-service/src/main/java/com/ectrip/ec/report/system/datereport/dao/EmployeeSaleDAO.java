package com.ectrip.ec.report.system.datereport.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.datereport.dao.idao.IEmployeeSaleDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;

public class EmployeeSaleDAO extends GenericDao implements IEmployeeSaleDAO {

	/**
	 * ��ƱԱÿ����Ʊ��ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId��ƱԱ���
	 * @param type�������01��Ʊ��02��Ʊ��
	 * @param rzti����
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-6
	 */
	public PaginationSupport queryEmpSaleDate(String emps,Long employeeId,String type,String rzti,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if(0L==employeeId){
			hsql.append(" select new map(sale.id.isalesvoucherid as isalesvoucherid,sale.dtmakedate as dtmakedate,sale.szsalesvoucherno as szsalesvoucherno,sale.bysalesvouchertype as bysalesvouchertype,sale.id.iticketstationid as iticketstationid,sale.usid as usid,cop.szcompanyname as szcompanyname,sale.ihandler as ihandler,emp.szemployeename as  szemployeename,sale.mhandcharge as mhandcharge,sale.forcedrefund as forcedrefund) from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop where  substr(sale.dtmakedate,1,10)='"+rzti+"' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.bysalesvouchertype='"+type+"' ");
			hsql.append(" and sale.ihandler in ("+emps+")  order by sale.id.isalesvoucherid desc ");
		}else{
			hsql.append(" select new map(sale.id.isalesvoucherid as isalesvoucherid,sale.dtmakedate as dtmakedate,sale.szsalesvoucherno as szsalesvoucherno,sale.bysalesvouchertype as bysalesvouchertype,sale.id.iticketstationid as iticketstationid,sale.usid as usid,cop.szcompanyname as szcompanyname,sale.ihandler as ihandler,emp.szemployeename as  szemployeename,sale.mhandcharge as mhandcharge,sale.forcedrefund as forcedrefund) from Stssalesvouchertab sale,Esfemployeetab emp,Galcompanyinfotab cop where  substr(sale.dtmakedate,1,10)='"+rzti+"' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.bysalesvouchertype='"+type+"' and sale.ihandler="+employeeId +" order by sale.id.isalesvoucherid desc");
		}
		System.out.println(hsql.toString());
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map)list.get(i);
				if(map.get("isalesvoucherid")!=null){
					//����ƾ֤ID
					Long isalesvoucherid = Long.parseLong(map.get("isalesvoucherid").toString());
					String hsql2 = "select new map(salde.itickettypeid as itickettypeid,pro.sztickettypename as sztickettypename,salde.mactualsaleprice as mactualsaleprice,sum(salde.iticketnum) as iticketnum, sum(salde.meventmoney) as meventmoney,sum(salde.iplayerperticket*salde.iticketnum) as iticketplayer ,nvl(sum(salde.mderatemoney),0) as mderatemoney,nvl(sum(salde.ideratenums),0) as ideratenums) from Stssalesvoucherdetailstab salde,Edmtickettypetab pro where salde.id.isalesvoucherid ="+isalesvoucherid+" and pro.itickettypeid = salde.itickettypeid group by salde.itickettypeid,pro.sztickettypename, salde.mactualsaleprice " ;			
					System.out.println(hsql2);
					List prolist = this.find(hsql2);
					if(prolist!=null && prolist.size()>=1){
						map.put("proList", prolist);
					}
				}
			}
		}
		return ps;
	}
	/**
	 * ��ƱԱ��ʷ��Ʊ��ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId��ƱԱ���
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-6
	 */
	public PaginationSupport queryEmpSaleHository(String emps,Long employeeId,String type,String rzti,String ldti,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if(0L==employeeId){
			hsql.append(" select new map(sale.id.isalesvoucherid as isalesvoucherid,sale.dtmakedate as dtmakedate,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,cop.szcompanyname as szcompanyname,sale.ihandler as ihandler,emp.szemployeename as  szemployeename,sale.mhandcharge as mhandcharge,sale.forcedrefund as forcedrefund) from Stssalesvouchertablist sale,Esfemployeetab emp,Galcompanyinfotab cop where  substr(sale.dtmakedate,1,10)>='"+rzti+"' and substr(sale.dtmakedate,1,10)<='"+ldti+"' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.bysalesvouchertype='"+type+"' ");
			hsql.append(" and sale.ihandler in ("+emps+") order by sale.id.isalesvoucherid desc ");
		}else{
			hsql.append(" select new map(sale.id.isalesvoucherid as isalesvoucherid,sale.dtmakedate as dtmakedate,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,cop.szcompanyname as szcompanyname,sale.ihandler as ihandler,emp.szemployeename as  szemployeename,sale.mhandcharge as mhandcharge,sale.forcedrefund as forcedrefund) from Stssalesvouchertablist sale,Esfemployeetab emp,Galcompanyinfotab cop where  substr(sale.dtmakedate,1,10)>='"+rzti+"' and substr(sale.dtmakedate,1,10)<='"+ldti+"' and emp.iemployeeid = sale.ihandler and cop.icompanyinfoid = emp.icompanyinfoid and sale.bysalesvouchertype='"+type+"' and sale.ihandler="+employeeId+" order by sale.id.isalesvoucherid desc" );
		}
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map)list.get(i);
				if(map.get("isalesvoucherid")!=null){
					//����ƾ֤ID
					Long isalesvoucherid = Long.parseLong(map.get("isalesvoucherid").toString());
					String hsql2 = "select new map(salde.itickettypeid as itickettypeid,pro.sztickettypename as sztickettypename,salde.mactualsaleprice as mactualsaleprice,sum(salde.iticketnum) as iticketnum, sum(salde.meventmoney) as meventmoney,sum(salde.iticketplayer*salde.iticketnum) as iticketplayer,nvl(sum(salde.mderatemoney),0) as mderatemoney,nvl(sum(salde.ideratenums),0) as ideratenums) from Stssalesvoucherdetailstablist salde,Edmtickettypetab pro where salde.id.isalesvoucherid ="+isalesvoucherid+" and pro.itickettypeid = salde.itickettypeid group by salde.itickettypeid,pro.sztickettypename, salde.mactualsaleprice ";
					List prolist = this.find(hsql2);
					if(prolist!=null && prolist.size()>=1){
						map.put("proList", prolist);
					}
				}
			}
		}
		return ps;
	}
	
	/**
	 * �����統�ճ�Ʊ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployee
	 * @param usid
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-20
	 */
	public PaginationSupport getLxsSaleTicket(Esfemployeetab esfemployee,String rzti,String ldti,String usid,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map( sale.iscenicid as iscenicid,sale.id.iticketstationid as iticketstationid,sale.iticketwinid as iticketwinid,sale.usid as usid,cus.corpname as corpname,sale.ihandler as ihandler,salde.itickettypeid as itickettypeid,sum(salde.iuseablenessnum) as numb, sum(salde.meventmoney) as mont ) from Stssalesvouchertab sale,Stssalesvoucherdetailstab salde, Custom cus,Edmtickettypetab ti where substr(sale.dtmakedate,1,10)>='"+rzti+"' and substr(sale.dtmakedate,1,10)<='"+ldti+"' and sale.bysalesvouchertype='01' and cus.lgtp='02' and cus.ttlb='01' and sale.id.isalesvoucherid = salde.id.isalesvoucherid  and cus.usid = sale.usid  and salde.itickettypeid = ti.itickettypeid   ");
		if(usid!=null && !"".equals(usid)){
			hsql.append(" and sale.usid in ("+usid+") ");
		}
		
		// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
		if (esfemployee.getCompanytype().equals("02")) {
			String scenics = esfemployee.getScenics();
			hsql.append(" and sale.iscenicid  in ("+scenics+") ");
		}
		
		hsql.append(" group by sale.iscenicid,sale.id.iticketstationid,sale.iticketwinid,sale.usid,salde.itickettypeid,sale.ihandler,cus.corpname ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				//������
				if(map.get("iscenicid")!=null && !"".equals(map.get("iscenicid"))){
					Esbscenicareatab scenic = (Esbscenicareatab)this.get(Esbscenicareatab.class, Long.parseLong(map.get("iscenicid").toString()));
					if(scenic!=null){
						map.put("szscenicname", scenic.getSzscenicname());
					}
				}
				//վ��
				if(map.get("iticketstationid")!=null && !"".equals(map.get("iticketstationid"))){
					Esbticketstationtab station = (Esbticketstationtab)this.get(Esbticketstationtab.class, Long.parseLong(map.get("iticketstationid").toString()));
					if(station!=null){
						map.put("szstationname", station.getSzstationname());
					}
				}
				//����
				if(map.get("iticketwinid")!=null && !"".equals(map.get("iticketwinid"))){
					Esbticketwintab win = (Esbticketwintab)this.get(Esbticketwintab.class, Long.parseLong(map.get("iticketwinid").toString()));
					if(win!=null){
						map.put("szticketwinname", win.getSzticketwinname());
					}
				}
				//Ա��
				if(map.get("ihandler")!=null && !"".equals(map.get("ihandler"))){
					Esfemployeetab emp = (Esfemployeetab)this.get(Esfemployeetab.class, Long.parseLong(map.get("ihandler").toString()));
					if(emp!=null){
						map.put("szemployeename", emp.getSzemployeename());
					}
				}
			}
		}
		return ps;
	}

}

