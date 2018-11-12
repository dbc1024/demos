package com.ectrip.ticket.stocks.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.stock.Stocksalevolumebycustomtab;
import com.ectrip.ticket.model.stock.Stocksalevolumetab;
import com.ectrip.ticket.model.stock.Stocksticketwarebycustomtab;
import com.ectrip.ticket.model.stock.Stocksticketwaretab;
import com.ectrip.ticket.stocks.dao.idao.IStocksProductDao;

public class StocksProductDao extends GenericDao implements IStocksProductDao {


	/**
	 * 说明: 产品库存模块初始化信息
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return PaginationSupport
	 * @author zengzhe
	 * 李进 2014-1--20 日修改
	 */
	public PaginationSupport initInfo(int pageSize, int startInt, String url) {
		StringBuffer sbSQL=new StringBuffer();
		PaginationSupport ps=null;
		sbSQL.append("select new map(st.seq as seq,st.sttype as sttype,st.objtype as objtype,st.pid as pid,st.stocknumber as stocknumber,st.notea as notea,sys1.pmva as pmva) from Stocksticketwaretab st,Sysparv5 sys1 where st.sttype=sys1.id.pmcd and sys1.id.pmky='KCLX' ");
		ps=this.findPage(sbSQL.toString(), pageSize, startInt, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long objtype=Long.parseLong(map.get("objtype").toString());
				Long pid=Long.parseLong(map.get("pid").toString());
				if(objtype!=null&&objtype==0){
					Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, pid);

					if ( esbscenic != null )
					{
						map.put("szproductname", esbscenic.getSzscenicname());
					}else
					{
						map.put("szproductname", "无效服务商");
					}
				}else if(objtype==1){

					Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, pid);
					if ( edmticket != null )
					{
						map.put("szproductname",edmticket.getSztickettypename());
					}
					else
					{
						map.put("szproductname","无效产品");
					}
					//System.out.println("Edmtickettypetab="+edmticket);

				}

				String sql=new String();
				if(map.get("sttype").toString().equals("0001")){  //永久
					sql=" from Stocksalevolumetab where seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0002")){   //天库存
					sql=" from Stocksalevolumetab where dtmakedate='"+Tools.getDays()+"' and seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0003")){   //年库存
					sql=" from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+map.get("seq").toString();
				}

				List volumlist=this.find(sql);
				if(volumlist!=null&&volumlist.size()>0){
					Stocksalevolumetab stock=(Stocksalevolumetab)volumlist.get(0);

					map.put("stocknumb",stock.getStocknumb());
				}else{
					map.put("stocknumb",0);
				}
			}
		}
		return ps;
	}



	/**
	 * 说明: 产品库存模块   增加库存
	 * @return void
	 * @author zengzhe
	 */
	public void addProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog) {
		Long maxid=this.getMaxPk("seq", "Stocksticketwaretab");
		stocksticketwaretab.setSeq((maxid+1));
		this.save(stocksticketwaretab);

		syslog.setStlg("0328");
		syslog.setBrief("新增产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setNote("新增产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}



	/**
	 * 说明: 产品库存模块  删除库存
	 * @return void
	 * @author zengzhe
	 */
	public void delProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog) {
		Stocksticketwaretab stockTable=(Stocksticketwaretab)this.get(Stocksticketwaretab.class, stocksticketwaretab.getSeq());
		this.delete(stockTable);

		syslog.setStlg("0330");
		syslog.setBrief("删除产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setNote("删除产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}



	/**
	 * 说明 :产品库存模块   更新库存
	 * @return Stocksticketwaretab
	 * @author zengzhe
	 */
	public void updateProductStock(Stocksticketwaretab stocksticketwaretab,Syslog syslog) {

		Stocksticketwaretab stocksTable=(Stocksticketwaretab)this.get(Stocksticketwaretab.class, stocksticketwaretab.getSeq());
		stocksTable.setSttype(stocksticketwaretab.getSttype());
		stocksTable.setStocknumber(stocksticketwaretab.getStocknumber());
		stocksTable.setNotea(stocksticketwaretab.getNotea());
		this.update(stocksTable);

		syslog.setStlg("0329");
		syslog.setBrief("修改产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setNote("修改产品库存信息:"+stocksticketwaretab.getSeq());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);


	}



	/**
	 * 说明： 产品库存模块  根据条件查询库存(服务商)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return PaginationSupport
	 * @author zengzhe
	 */
	public PaginationSupport selectProductStockFF(int pageSize, int startInt,String url, String strNameF) {
		StringBuffer sbSQL=new StringBuffer();
		PaginationSupport ps=null;
		sbSQL.append(" select new map(b.seq as seq,b.sttype as sttype,b.objtype as objtype,b.pid as pid,b.stocknumber as stocknumber,b.notea as notea,sys1.pmva as pmva) from Esbscenicareatab a,Stocksticketwaretab b,Sysparv5 sys1  where a.iscenicid=b.pid and b.objtype=0 and b.sttype=sys1.id.pmcd and sys1.id.pmky='KCLX'  ");
		if(strNameF!=null&&!"".equals(strNameF))
		{
			sbSQL.append(" and a.szscenicname like '%"+strNameF+"%' ");
		}
		ps=this.findPage(sbSQL.toString(), pageSize, startInt, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long objtype=Long.parseLong(map.get("objtype").toString());
				Long pid=Long.parseLong(map.get("pid").toString());
				if(objtype!=null&&objtype==0){
					Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, pid);
					map.put("szproductname", esbscenic.getSzscenicname());
				}else if(objtype==1){
					Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, pid);
					map.put("szproductname",edmticket.getSztickettypename());
				}

				String sql=new String();
				if(map.get("sttype").toString().equals("0001")){  //永久
					sql=" from Stocksalevolumetab where seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0002")){   //天库存
					sql=" from Stocksalevolumetab where dtmakedate='"+Tools.getDays()+"' and seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0003")){   //年库存
					sql=" from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+map.get("seq").toString();
				}

				List volumlist=this.find(sql);
				if(volumlist!=null&&volumlist.size()>0){
					Stocksalevolumetab stock=(Stocksalevolumetab)volumlist.get(0);

					map.put("stocknumb",stock.getStocknumb());
				}else{
					map.put("stocknumb",0);
				}
			}
		}
		return ps;
	}


	/**
	 * 说明： 产品库存模块  根据条件查询库存(产品)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return PaginationSupport
	 * @author zengzhe
	 */
	public PaginationSupport selectProductStockCC(int pageSize, int startInt,String url, String strNameC) {
		StringBuffer sbSQL=new StringBuffer();
		PaginationSupport ps=null;
		sbSQL.append(" select new map(b.seq as seq,b.sttype as sttype,b.objtype as objtype,b.pid as pid,b.stocknumber as stocknumber,b.notea as notea,sys1.pmva as pmva) from Edmtickettypetab e,Stocksticketwaretab b,Sysparv5 sys1 where e.itickettypeid=b.pid and b.objtype=1 and b.sttype=sys1.id.pmcd and sys1.id.pmky='KCLX' ");
		if(strNameC!=null&&!"".equals(strNameC)){
			sbSQL.append(" and e.sztickettypename like '%"+strNameC+"%' ");
		}
		ps=this.findPage(sbSQL.toString(), pageSize, startInt, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long objtype=Long.parseLong(map.get("objtype").toString());
				Long pid=Long.parseLong(map.get("pid").toString());
				if(objtype!=null&&objtype==0){
					Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, pid);
					map.put("szproductname", esbscenic.getSzscenicname());
				}else if(objtype==1){
					Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, pid);
					map.put("szproductname",edmticket.getSztickettypename());
				}

				String sql=new String();
				if(map.get("sttype").toString().equals("0001")){  //永久
					sql=" from Stocksalevolumetab where seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0002")){   //天库存
					sql=" from Stocksalevolumetab where dtmakedate='"+Tools.getDays()+"' and seq="+map.get("seq").toString();
				}else if(map.get("sttype").toString().equals("0003")){   //年库存
					sql=" from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+map.get("seq").toString();
				}

				List volumlist=this.find(sql);
				if(volumlist!=null&&volumlist.size()>0){
					Stocksalevolumetab stock=(Stocksalevolumetab)volumlist.get(0);

					map.put("stocknumb",stock.getStocknumb());
				}else{
					map.put("stocknumb",0);
				}
			}
		}
		return ps;
	}



	/**
	 * 说明: 选择一条数据进行修改或者删除或者查看
	 * @param seq
	 * @return Stocksticketwaretab
	 * @author zengzhe
	 */
	public Stocksticketwaretab alertProductStock(Long seq) {
		Stocksticketwaretab stock=(Stocksticketwaretab)this.get(Stocksticketwaretab.class, seq);
		if(stock.getObjtype()!=null&&stock.getObjtype()==0){
			Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, stock.getPid());
			stock.setSzproductname(esbscenic.getSzscenicname());
		}else{
			Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stock.getPid());
			stock.setSzproductname(edmticket.getSztickettypename());
		}
		String sql=" from Sysparv5 sys1 where sys1.id.pmky='KCLX' and sys1.id.pmcd='"+stock.getSttype()+"'";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Sysparv5 syspar=(Sysparv5)list.get(0);
			stock.setStpmva(syspar.getPmva());
		}

		return stock;
	}




	/**
	 * 说明：在新增库产品存时，判断是否已经存在
	 * @param pid
	 * @return boolean
	 * @author zengzhe
	 */
	public boolean checkProductStockIsExist(Long pid,long obitype) {
		String sql=" from Stocksticketwaretab s where s.pid="+pid+" and s.objtype="+obitype+"";
		List myList=this.find(sql);
		if(myList.size()>0&&myList!=null)
		{
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 说明:判断新增产品库存要么是服务商库存要么是产品库存
	 * @return boolean
	 * @author zengzhe
	 */
	public boolean checkProductStockF(long pid)
	{
		String sql="from Stocksticketwaretab sto where sto.pid in (select edm.iscenicid from  Edmtickettypetab edm where edm.itickettypeid="+pid+")";
		List myList=this.find(sql);
		if(myList.size()>0&&myList!=null)
		{
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 说明:判断新增产品库存要么是服务商库存要么是产品库存
	 * @return boolean
	 * @author zengzhe
	 */
	public boolean checkProductStockC(long pid)
	{
		String sql="from Stocksticketwaretab sto where sto.pid in (select edm.itickettypeid from  Edmtickettypetab edm where edm.iscenicid="+pid+")";
		List myList=this.find(sql);
		if(myList.size()>0&&myList!=null)
		{
			return true;
		}else {
			return false;
		}
	}


	/**
	 * 说明：指定查询语句（sql）来返回服务商表的主键
	 * @param sql
	 * @return list
	 * @author zengzhe
	 */
	public List selectProductStockForF(String sql) {
		//String SQL="select iscenicid from Esbscenicareatab";
		List myListF=this.find(sql);
		return myListF;
	}



	/**
	 * 说明：指定查询语句（sql）来返回产品表的主键
	 * @param sql
	 * @return list
	 * @author zengzhe
	 */
	public List selectProductStockForC(String sql) {

		//String SQL="select iscenicid from Edmtickettypetab";
		List myListC=this.find(sql);
		return myListC;
	}

	/**
	 *
	 * Describe:查询出所有客户库存表
	 * @author:huxingwei
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2014-12-17
	 */
	public PaginationSupport getAllStockProductCustom(int pageSize, int startInt, String url){
		StringBuffer sbSQL=new StringBuffer();
		PaginationSupport ps=null;
		sbSQL.append("select new map(st.seq as seq,st.usid as usid,st.sttype as sttype,st.objtype as objtype,st.pid as pid,st.stocknumber as stocknumber,st.notea as notea,sys1.pmva as pmva,st.starttime as starttime,st.endtime as endtime) from Stocksticketwarebycustomtab st,Sysparv5 sys1 where st.sttype=sys1.id.pmcd and sys1.id.pmky='KCLX' ");
		ps=this.findPage(sbSQL.toString(), pageSize, startInt, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long objtype=Long.parseLong(map.get("objtype").toString());
				Long pid=Long.parseLong(map.get("pid").toString());
				if(objtype!=null&&objtype==0){
					Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, pid);
					if(esbscenic!=null){
						map.put("szproductname", esbscenic.getSzscenicname());
					}else{
						map.put("szproductname", "");
					}

				}else if(objtype==1){
					Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, pid);
					if(edmticket!=null){
						map.put("szproductname",edmticket.getSztickettypename());
					}else{
						map.put("szproductname","");
					}

				}

				String usid=map.get("usid").toString();
				if(usid!=null&&!usid.equals("")){
					Custom custom=(Custom) this.get(Custom.class, usid);
					if(custom!=null){
						map.put("corpname",custom.getCorpname());
						map.put("lname",custom.getLname());
					}else{
						map.put("corpname",null);
						map.put("lname",null);
					}

				}

				String sql=new String();
				if(map.get("sttype").toString().equals("0001")){  //永久
					sql=" from Stocksalevolumebycustomtab where seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}else if(map.get("sttype").toString().equals("0002")){   //天库存
					sql=" from Stocksalevolumebycustomtab where dtmakedate='"+Tools.getDays()+"' and seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}else if(map.get("sttype").toString().equals("0003")){   //年库存
					sql=" from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}

				List volumlist=this.find(sql);
				if(volumlist!=null&&volumlist.size()>0){
					Stocksalevolumebycustomtab stock=(Stocksalevolumebycustomtab)volumlist.get(0);
					map.put("stocknumb",stock.getStocknumb());
				}else{
					map.put("stocknumb",0);
				}

			}
		}
		return ps;

	}

	/**
	 *
	 * Describe:查询所有旅行社分社
	 * @author:huxingwei
	 * @return
	 * return:List
	 * Date:2014-12-17
	 */
	public PaginationSupport getAllLxsfs(String query,int pageSize,int startIndex,String url){
		StringBuffer hsql = new StringBuffer("select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) from  Custom c where ((c.lgtp = '02' and c.ttlb='01' and c.ustp='02' and c.usqx='11110000000000000000' and c.ibusinessid=2) or (c.lgtp = '02' and c.ttlb='01' and c.ustp='01' and c.ibusinessid=3 ))  ");
		if(query!=null&&!query.equals("")){
			hsql.append(" and c.corpname like '%"+query+"%'");
		}
		hsql.append(" order by corpname,status, ldate desc, c.usid ");

		return this.findPage(hsql.toString(), pageSize, startIndex, url);

	}
	/**
	 *
	 * Describe:客户库存增加
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void addCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		Long maxid=this.getMaxPk("seq", "Stocksticketwarebycustomtab");
		stocksticketwarebycustomtab.setSeq((maxid+1));
		this.save(stocksticketwarebycustomtab);

		syslog.setStlg("0508");
		syslog.setBrief("新增客户库存信息:"+stocksticketwarebycustomtab.getSeq()+" 用户："+stocksticketwarebycustomtab.getUsid());
		syslog.setNote("新增客户库存信息:"+stocksticketwarebycustomtab.getSeq()+" 用户："+stocksticketwarebycustomtab.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 *
	 * Describe:客户库存删除
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void delCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		Stocksticketwarebycustomtab stockTable=(Stocksticketwarebycustomtab)this.get(Stocksticketwarebycustomtab.class, stocksticketwarebycustomtab.getSeq());
		this.delete(stockTable);

		syslog.setStlg("0510");
		syslog.setBrief("删除客户库存信息:"+stocksticketwarebycustomtab.getUsid());
		syslog.setNote("删除客户库存信息:"+stocksticketwarebycustomtab.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 *
	 * Describe:客户库存修改
	 * @author:huxingwei
	 * @param stocksticketwarebycustomtab
	 * @param syslog
	 * return:void
	 * Date:2014-12-17
	 */
	public void updateCustomProductStock(Stocksticketwarebycustomtab stocksticketwarebycustomtab,Syslog syslog){
		Stocksticketwarebycustomtab stocksTable=(Stocksticketwarebycustomtab)this.get(Stocksticketwarebycustomtab.class, stocksticketwarebycustomtab.getSeq());
		stocksTable.setSttype(stocksticketwarebycustomtab.getSttype());
		stocksTable.setStocknumber(stocksticketwarebycustomtab.getStocknumber());
		stocksTable.setNotea(stocksticketwarebycustomtab.getNotea());
		stocksTable.setStarttime(stocksticketwarebycustomtab.getStarttime());
		stocksTable.setEndtime(stocksticketwarebycustomtab.getEndtime());
		this.update(stocksTable);

		syslog.setStlg("0509");
		syslog.setBrief("修改产品库存信息:"+stocksticketwarebycustomtab.getSeq()+"  用户："+stocksticketwarebycustomtab.getUsid());
		syslog.setNote("修改产品库存信息:"+stocksticketwarebycustomtab.getSeq()+"  用户："+stocksticketwarebycustomtab.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 说明：在新增库产品存时，判断是否已经存在
	 * @param pid
	 * @return boolean
	 * @author zengzhe
	 */
	public String checkCustomProductStockIsExist(Stocksticketwarebycustomtab stock,String type) {
		String hsql="";
		List myList=new ArrayList();
		Edmtickettypetab edmticket=null;
		if(stock.getObjtype() == 1){
			edmticket=(Edmtickettypetab)this.get(Edmtickettypetab.class, stock.getPid());
		}
		if(type!=null&&type.equals("1")){
			hsql=" from Stocksticketwarebycustomtab st where st.pid="+stock.getPid()+" and st.objtype="+stock.getObjtype()+" and st.usid='"+stock.getUsid()+"' and st.sttype='"+stock.getSttype()+"'  and ((st.starttime<='"+stock.getStarttime()+"' and st.endtime>='"+stock.getStarttime()+"') or  (st.starttime<='"+stock.getEndtime()+"' and st.endtime>='"+stock.getEndtime()+"' ) or (st.starttime>'"+stock.getStarttime()+"' and st.endtime<'"+stock.getEndtime()+"')or (st.starttime<'"+stock.getStarttime()+"' and st.endtime>'"+stock.getEndtime()+"'))";
			myList=this.find(hsql);
			if(myList!=null&&myList.size()>0){
				return "1";
			}else{
				String sql="";
				String msql="";
				//产品类型
				if(stock.getObjtype() == 1){
					sql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and st.sttype in ('0001','0003') and ((st.objtype=1 and st.pid="+stock.getPid()+") or (st.objtype=0 and st.pid="+edmticket.getIscenicid()+"))";
					msql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and st.sttype='0002' and ((st.objtype=1 and st.pid="+stock.getPid()+" and ((st.starttime<='"+stock.getStarttime()+"' and st.endtime>='"+stock.getStarttime()+"') or  (st.starttime<='"+stock.getEndtime()+"' and st.endtime>='"+stock.getEndtime()+"' ) or (st.starttime>'"+stock.getStarttime()+"' and st.endtime<'"+stock.getEndtime()+"')or (st.starttime<'"+stock.getStarttime()+"' and st.endtime>'"+stock.getEndtime()+"'))) or (st.objtype=0 and st.pid="+edmticket.getIscenicid()+" and ((st.starttime<='"+stock.getStarttime()+"' and st.endtime>='"+stock.getStarttime()+"') or  (st.starttime<='"+stock.getEndtime()+"' and st.endtime>='"+stock.getEndtime()+"' ) or (st.starttime>'"+stock.getStarttime()+"' and st.endtime<'"+stock.getEndtime()+"')or (st.starttime<'"+stock.getStarttime()+"' and st.endtime>'"+stock.getEndtime()+"'))))";
				}else{//服务商类别
					sql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and st.sttype in ('0001','0003') and ((st.objtype=1 ) or (st.objtype=0 and st.pid="+stock.getPid()+"))";
					msql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and st.sttype='0002' and ((st.objtype=1 and ((st.starttime<='"+stock.getStarttime()+"' and st.endtime>='"+stock.getStarttime()+"') or  (st.starttime<='"+stock.getEndtime()+"' and st.endtime>='"+stock.getEndtime()+"' ) or (st.starttime>'"+stock.getStarttime()+"' and st.endtime<'"+stock.getEndtime()+"')or (st.starttime<'"+stock.getStarttime()+"' and st.endtime>'"+stock.getEndtime()+"'))) or (st.objtype=0 and st.pid="+stock.getPid()+" and ((st.starttime<='"+stock.getStarttime()+"' and st.endtime>='"+stock.getStarttime()+"') or  (st.starttime<='"+stock.getEndtime()+"' and st.endtime>='"+stock.getEndtime()+"' ) or (st.starttime>'"+stock.getStarttime()+"' and st.endtime<'"+stock.getEndtime()+"')or (st.starttime<'"+stock.getStarttime()+"' and st.endtime>'"+stock.getEndtime()+"'))))";
				}
				List lst=this.find(sql);
				if(lst!=null&&lst.size()>0){
					return "2";
				}

				List mlst=this.find(msql);
				if(mlst!=null&&mlst.size()>0){
					return "3";
				}
			}

		}else{
			hsql=" from Stocksticketwarebycustomtab st where st.pid="+stock.getPid()+" and st.objtype="+stock.getObjtype()+" and st.usid='"+stock.getUsid()+"' and st.sttype='"+stock.getSttype()+"' ";
			myList=this.find(hsql);
			if(myList!=null&&myList.size()>0){
				return "4";
			}else{
				String sql="";
				//产品类型
				if(stock.getObjtype() == 1){
					sql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and ((st.objtype=1 and st.pid="+stock.getPid()+") or (st.objtype=0 and st.pid="+edmticket.getIscenicid()+"))";
				}else{//服务商类别
					sql=" from Stocksticketwarebycustomtab st where st.usid='"+stock.getUsid()+"' and ((st.objtype=1 ) or (st.objtype=0 and st.pid="+stock.getPid()+"))";
				}
				List lst=this.find(sql);
				if(lst!=null&&lst.size()>0){
					return "5";
				}

			}
		}

		return "0";
	}

	/**
	 *
	 * Describe:根据id查询客户库存信息
	 * @author:huxingwei
	 * @param seq
	 * @return
	 * return:Stocksticketwaretab
	 * Date:2014-12-18
	 */
	public Stocksticketwarebycustomtab getCustomProductStockById(Long seq) {
		Stocksticketwarebycustomtab stock=(Stocksticketwarebycustomtab)this.get(Stocksticketwarebycustomtab.class, seq);
		if(stock.getObjtype()!=null&&stock.getObjtype()==0){
			Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, stock.getPid());
			stock.setSzproductname(esbscenic.getSzscenicname());
		}else{
			Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stock.getPid());
			stock.setSzproductname(edmticket.getSztickettypename());
		}
		String sql=" from Sysparv5 sys1 where sys1.id.pmky='KCLX' and sys1.id.pmcd='"+stock.getSttype()+"'";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Sysparv5 syspar=(Sysparv5)list.get(0);
			stock.setStpmva(syspar.getPmva());
		}

		String usid=stock.getUsid();
		if(usid!=null&&!usid.equals("")){
			Custom custom=(Custom) this.get(Custom.class, usid);
			stock.setUsername(custom.getLname());

		}

		return stock;
	}

	/**
	 * 说明：客户 产品库存模块  根据条件查询库存(服务商)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return PaginationSupport
	 * @author zengzhe
	 */
	public PaginationSupport selectCustomProductStockFF(int pageSize, int startInt,String url, Long strNameF,String objtype,String corpname) {
		StringBuffer sbSQL=new StringBuffer();
		PaginationSupport ps=null;
		sbSQL.append(" select new map(b.seq as seq,b.usid as usid,b.sttype as sttype,b.objtype as objtype,b.pid as pid,b.stocknumber as stocknumber,b.notea as notea,sys1.pmva as pmva,b.starttime as starttime,b.endtime as endtime) from Stocksticketwarebycustomtab b,Sysparv5 sys1,Custom ct  where  b.sttype=sys1.id.pmcd and sys1.id.pmky='KCLX' and ct.usid=b.usid ");
		if(corpname!=null&&!corpname.equals("")){
			sbSQL.append(" and ct.corpname like '%"+corpname+"%'");
		}else{
			if(objtype!=null&&!objtype.equals("")){
				sbSQL.append(" and b.objtype = "+objtype+" ");
			}
			if(strNameF!=null&&!"".equals(strNameF)&&!"0".equals(strNameF)&&strNameF!=0){
				sbSQL.append(" and b.pid = "+strNameF+" ");
			}
		}

		ps=this.findPage(sbSQL.toString(), pageSize, startInt, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long pid=Long.parseLong(map.get("pid").toString());
				String obj=map.get("objtype").toString();
				if(obj!=null&&obj.equals("0")){
					Esbscenicareatab esbscenic=(Esbscenicareatab) this.get(Esbscenicareatab.class, pid);
					map.put("szproductname", esbscenic.getSzscenicname());
				}else if(obj.equals("1")){
					Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, pid);
					map.put("szproductname",edmticket.getSztickettypename());
				}
				String usid=map.get("usid").toString();
				if(usid!=null&&!usid.equals("")){
					Custom custom=(Custom) this.get(Custom.class, usid);
					map.put("corpname",custom.getCorpname());
					map.put("lname",custom.getLname());

				}

				String sql=new String();
				if(map.get("sttype").toString().equals("0001")){  //永久
					sql=" from Stocksalevolumebycustomtab where seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}else if(map.get("sttype").toString().equals("0002")){   //天库存
					sql=" from Stocksalevolumebycustomtab where dtmakedate='"+Tools.getDays()+"' and seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}else if(map.get("sttype").toString().equals("0003")){   //年库存
					sql=" from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+map.get("seq").toString()+" and usid='"+usid+"'";
				}

				List volumlist=this.find(sql);
				if(volumlist!=null&&volumlist.size()>0){
					Stocksalevolumebycustomtab stock=(Stocksalevolumebycustomtab)volumlist.get(0);
					map.put("stocknumb",stock.getStocknumb());
				}else{
					map.put("stocknumb",0);
				}
			}
		}
		return ps;
	}

}
