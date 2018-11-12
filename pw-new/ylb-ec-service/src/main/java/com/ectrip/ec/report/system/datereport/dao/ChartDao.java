package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.sales.Rsalecounttab;
import com.ectrip.ec.report.system.datereport.dao.idao.IChartDao;

public class ChartDao extends GenericDao implements IChartDao {
	/**
	 * *
	 * Describe:������ͳ��ͼ����
	 * @see com.ectrip.report.system.datereport.dao.idao.IChartDao#showlxschart(java.lang.String)
	 * @param type
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2013-1-22
	 */
	public List<Map> showlxschart(String type,int radiobutton1,String rzyear,String ldyear,String rzmonth,String ldmonth,String rzti,String ldti) throws Exception{
		StringBuffer hsql = new StringBuffer();
		if(type.equals("01")){
			hsql.append(" select c.usid as usid,c.corpname as corpname,sum(case when (r.notea='02' or r.notea='07') then -(r.numb-r.isf) else r.numb-r.isf end) as numb");
			hsql.append(" from rcustomgrouptab r, custom c ");
			hsql.append(" where r.chiefuser = c.usid and ");
		}else{
			hsql.append(" select c.usid as usid,c.corpname as corpname,sum(case when (r.notea='02' or r.notea='07') then -(r.mont-r.duf) else (r.mont-r.duf) end)+sum(nvl(r.dua,0)) as mont");
			hsql.append(" from rcustomgrouptab r, custom c ");
			hsql.append(" where r.chiefuser = c.usid and ");
		}
		if (1 == radiobutton1) {// ��
			hsql.append(" r.titype='03' and r.ryear>='" + rzyear
					+ "' and r.ryear<='" + ldyear + "' ");
		}
		if (2 == radiobutton1) {// ��
			if (rzmonth.subSequence(0, 4).equals(ldmonth.subSequence(0, 4))) {
				hsql.append("   r.titype='02' and r.rmonth>='"
						+ rzmonth.substring(5, 7) + "' and  r.ryear ='"
						+ rzmonth.substring(0, 4) + "' and r.rmonth<='"
						+ ldmonth.substring(5, 7) + "' and  r.ryear ='"
						+ ldmonth.substring(0, 4) + "' ");
			} else {
				hsql.append("   r.titype='02' and r.rmonth>='"
						+ rzmonth.substring(5, 7) + "' and  r.ryear ='"
						+ rzmonth.substring(0, 4) + "' or r.rmonth<='"
						+ ldmonth.substring(5, 7) + "' and  r.ryear ='"
						+ ldmonth.substring(0, 4) + "' ");

			}
		}
		if (3 == radiobutton1) {// ��
			hsql.append("  r.titype='01' and r.times>='" + rzti
					+ "' and r.times<='" + ldti + "' ");
		}
		if(type.equals("01")){
			hsql.append(" group by c.usid,c.corpname order by  numb desc ");
		}else{
			hsql.append(" group by c.usid,c.corpname order by  mont desc ");
		}
		System.out.println(hsql.toString());
		return this.findBySqlToMap(hsql.toString());
	}
	/**
	 * *
	 * Describe:���ۻ���ͬ�ڶԱ�ͳ��ͼ����
	 * @see com.ectrip.report.system.datereport.dao.idao.IChartDao#showempchart(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, com.ectrip.model.report.sales.Rsalecounttab)
	 * @param type
	 * @param radiobutton1
	 * @param rzyear
	 * @param rzmonth
	 * @param rzti
	 * @param salecount
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2013-1-22
	 */
	public List<Map> showempchart(String type,int radiobutton1,String rzyear,String rzmonth,String rzti,Rsalecounttab salecount) throws Exception{
		StringBuffer sql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		StringBuffer where1 = new StringBuffer();
		StringBuffer where2 = new StringBuffer();
		StringBuffer where3 = new StringBuffer();
		if(type.equals("01")){
			sql.append(" select nvl(sum(case when sale.notea = '02' then -sale.numb else sale.numb end),0) as numb from Rsalecounttab sale ");
		}else{
			sql.append(" select abs(nvl(sum(case when sale.notea = '02' then -(sale.mont-sale.duf) else (sale.mont-sale.duf) end) + sum(sale.dub),0)) as mont from Rsalecounttab sale ");
		}
		if(radiobutton1==0){
			where1.append(" where sale.titype='01' and sale.times='"+Tools.getDate(Tools.getDays(), -1)+"' ");
			String[] str = Tools.getDate(Tools.getDays(), -1).split("-");
			String date2 = String.valueOf((Long.parseLong(str[0])-1))+"-"+str[1]+"-"+str[2];
			String date3 = String.valueOf((Long.parseLong(str[0])-2))+"-"+str[1]+"-"+str[2];
			where2.append(" where sale.titype='01' and sale.times='"+date2+"' ");
			where3.append(" where sale.titype='01' and sale.times='"+date3+"' ");
		}
		if(radiobutton1==1){
			where1.append(" where sale.titype='03' and sale.ryear='"+ rzyear + "' ");
			where2.append(" where sale.titype='03' and sale.ryear='"
					+ String.valueOf(Long.parseLong(rzyear)-1) + "' ");
			where3.append(" where sale.titype='03' and sale.ryear='"
					+ String.valueOf(Long.parseLong(rzyear)-2) + "' ");
		}
		if(radiobutton1==2){
			where1.append(" where sale.titype='02' and sale.rmonth='"
					+ rzmonth.substring(5, 7) + "' and  sale.ryear ='"
					+ rzmonth.substring(0, 4) + "' ");
			where2.append(" where sale.titype='02' and sale.rmonth='"
					+ rzmonth.substring(5, 7) + "' and  sale.ryear ='"
					+ String.valueOf(Long.parseLong(rzmonth.substring(0, 4))-1) + "' ");
			where3.append(" where sale.titype='02' and sale.rmonth='"
					+ rzmonth.substring(5, 7) + "' and  sale.ryear ='"
					+ String.valueOf(Long.parseLong(rzmonth.substring(0, 4))-2) + "' ");
		}
		if(radiobutton1==3){
			where1.append(" where sale.titype='01' and sale.times='"
					+ rzti + "' ");
			String[] rstr = rzti.split("-");
			String rdate2 = String.valueOf((Long.parseLong(rstr[0])-1))+"-"+rstr[1]+"-"+rstr[2];
			String rdate3 = String.valueOf((Long.parseLong(rstr[0])-2))+"-"+rstr[1]+"-"+rstr[2];
			where2.append(" where sale.titype='01' and sale.times='"
					+ rdate2 + "' ");
			where3.append(" where sale.titype='01' and sale.times='"
					+ rdate3 + "' ");
		}
		if(salecount!=null){
			if(salecount.getIsb()!=null&&salecount.getIsb().longValue()!=0l){
				where.append(" and sale.isb="+salecount.getIsb());
			}
			if(salecount.getEmpid()!=null&&!salecount.getEmpid().equals("")&&!salecount.getEmpid().equals("00")){
				where.append(" and sale.empid='"+salecount.getEmpid()+"' ");
			}
			if(salecount.getNotea()!=null&&!salecount.getNotea().equals("00")){
				where.append(" and sale.notea='"+salecount.getNotea()+"' ");
			}
			if(salecount.getItickettypeid()!=null&&salecount.getItickettypeid().longValue()!=0l){
				where.append(" and sale.itickettypeid="+salecount.getItickettypeid());
			}
			if(salecount.getSkfs()!=null&&!salecount.getSkfs().equals("100")){
				where.append(" and sale.skfs='"+salecount.getSkfs()+"' ");
			}
			if(salecount.getIsd()!=null&&salecount.getIsd().longValue()!=0l){
				where.append(" and sale.isd="+salecount.getIsd());
			}
			if(salecount.getNotef()!=null&&!salecount.getNotef().equals("")&&!salecount.getNotef().equals("0")){
				where.append(" and sale.notef='"+salecount.getNotef()+"' ");
			}
			if(salecount.getIsf()!=null){
				where.append(" and sale.isf="+salecount.getIsf());
			}
		}
		String sql1 = sql.toString()+where1.toString()+where.toString();
		String sql2 = sql.toString()+where2.toString()+where.toString();
		String sql3 = sql.toString()+where3.toString()+where.toString();
		System.out.println("sql1:"+sql1);
		System.out.println("sql2:"+sql2);
		System.out.println("sql3:"+sql3);
		List<Map> list = new ArrayList<Map>();
		List<Map> list1 = this.findBySqlToMap(sql1);
		List<Map> list2 = this.findBySqlToMap(sql2);
		List<Map> list3 = this.findBySqlToMap(sql3);
		if(type.equals("01")){
			if(list3!=null&&list3.size()>0){
				list.add(list3.get(0));
			}else{
				Map map = new HashMap();
				map.put("NUMB", 0);
				list.add(map);
			}
			if(list2!=null&&list2.size()>0){
				list.add(list2.get(0));
			}else{
				Map map = new HashMap();
				map.put("NUMB", 0);
				list.add(map);
			}
			if(list1!=null&&list1.size()>0){
				list.add(list1.get(0));
			}else{
				Map map = new HashMap();
				map.put("NUMB", 0);
				list.add(map);
			}
		}else{
			if(list3!=null&&list3.size()>0){
				list.add(list3.get(0));
			}else{
				Map map = new HashMap();
				map.put("MONT", 0);
				list.add(map);
			}
			if(list2!=null&&list2.size()>0){
				list.add(list2.get(0));
			}else{
				Map map = new HashMap();
				map.put("MONT", 0);
				list.add(map);
			}
			if(list1!=null&&list1.size()>0){
				list.add(list1.get(0));
			}else{
				Map map = new HashMap();
				map.put("MONT", 0);
				list.add(map);
			}
		}
		return list;
	}
}

