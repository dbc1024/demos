package com.ectrip.ticket.warehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Iomwarehouse;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.warehouse.Iompersonalticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseTicketInDao;

public class WarehouseTicketInDao extends GenericDao implements IWarehouseTicketInDao{


	/**
	 * Describe:根据经手人来查询某服务商下的产品信息
	 * @param ihandler  经手人
	 * @return
	 * return:List
	 * @author aozhuozu
	 * Date:2012-7-13
	 */
	public List showListticket(Long ihandler) {
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct new map(iom.itickettypeid as itickettypeid) from Iompersonalticketdetails iom where iom.ireceiverid="+ihandler+" order by iom.itickettypeid");
		List list = this.find(sql.toString());
		List eList = new ArrayList();
		if(list!=null||list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map=(Map) list.get(i);
				Edmtickettypetab e = (Edmtickettypetab) this.get(Edmtickettypetab.class, map.get("itickettypeid"));
				eList.add(e);
			}
		}
		return eList;
	}


	/**
	 * Describe:显示个人结存明细表
	 * @param ihandler 经手人    itickettypeid  票信息键值
	 * @auth:aozhuozu
	 * @return
	 * return:List
	 * Date:2012-7-16
	 */
	public List findAllPersonalticket(long ihandler,long itickettypeid){
		Edmtickettypetab e = (Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String hsql = "from Iompersonalticketdetails where itickettypeid="+itickettypeid+" and ireceiverid="+ihandler;
		List list=null;
		try {
			list = this.find(hsql);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	}


	/**
	 * Describe:界面层判断票号是否在指定票段内
	 * @param ticketid 票信息键值    ticketCode 起始票号     idetailsid 个人明细表主键
	 * @auth:aozhuozu
	 * @return
	 * return:Iomstocksbill
	 * Date:2012-7-18
	 */
	public boolean checkTicketcodeRange(long ticketid,String ticketCode,long idetailsid){
		boolean b = false;
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, ticketid);
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
//		Long ticketserial =  Tools.Text36ToConvert(ticketCode.substring(ticketrule.getIserialnopos()-1, ticketrule.getIserialnopos()+ticketrule.getIserialnolen()-1));
		Long ticketserial=0L;
		if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
			ticketserial = Long.parseLong(ticketCode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}else{
			ticketserial =  Tools.Text36ToConvert(ticketCode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}

		Iompersonalticketdetails stadetails =  (Iompersonalticketdetails) this.get(Iompersonalticketdetails.class,idetailsid);
		if(ticketserial>=stadetails.getIstartserial()&&ticketserial<=stadetails.getIendserial()){
			b = true;
		}
		return b;
	}


	/**
	 * Describe:界面层获取起始票号修改后的票数量
	 * @param ticketid 票信息键值    startcode 起始票号     endcode 截止票号
	 * @auth:aozhuozu
	 * @return
	 * return:long
	 * Date:2012-7-20
	 */
	public long showiamount(long ticketid,String startcode, String endcode) {
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, ticketid);
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
		Long startserial = 0L;
		Long endserial = 0L;
		if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
			startserial=Long.parseLong(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			endserial=Long.parseLong(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}else{
			startserial=Tools.Text36ToConvert(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			endserial=Tools.Text36ToConvert(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}

		return endserial-startserial+1L;
	}


	/**
	 * Describe:根据个人明细表主键ID得到对应记录
	 * @param idetailsid 个人明细表主键
	 * @auth:aozhuozu
	 * @return
	 * return:List
	 * Date:2012-7-19
	 */
	public List findPersonalticket(Long idetailsid){
		Iompersonalticketdetails e = (Iompersonalticketdetails) this.get(Iompersonalticketdetails.class, idetailsid);
		List list=new ArrayList();
		list.add(e);
		return list;
	}


	/**
	 * Describe:判断票号是否已经存在                                            是否还要判断对应的仓库ID？？？？？？？
	 * @param staDetails 一条个人明细记录    ihandler 经手人
	 * @auth:aozhuozu
	 * @return
	 * return:boolean
	 * Date:2012-7-20
	 */
	public boolean retrieWarehouse(Iompersonalticketdetails staDetails,long ihandler){
		System.out.println("ID:="+ihandler);
		boolean b=false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, staDetails.getItickettypeid());
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
		String istartserial=staDetails.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
		String iendserial=staDetails.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
//		staDetails.setIstartserial(Tools.Text36ToConvert(istartserial));
//		staDetails.setIendserial(Tools.Text36ToConvert(iendserial));
		if(ticketrule.getIntons1()==1){
			staDetails.setIstartserial(Long.parseLong(istartserial));
			staDetails.setIendserial(Long.parseLong(iendserial));
		}else{
			staDetails.setIstartserial(Tools.Text36ToConvert(istartserial));
			staDetails.setIendserial(Tools.Text36ToConvert(iendserial));
		}
//		String sql = "from Iompersonalticketdetails details where details.itickettypeid="+staDetails.getItickettypeid()+" and details.idetailsid="+ihandler+" and " +
//		" details.istartserial<="+staDetails.getIstartserial()+" and details.iendserial>="+staDetails.getIendserial()+"";
		String sql = "from Iompersonalticketdetails details where details.itickettypeid="+staDetails.getItickettypeid()+" and details.ireceiverid="+ihandler+" and " +
				" details.istartserial<="+staDetails.getIstartserial()+" and details.iendserial>="+staDetails.getIendserial()+"";
		System.out.println(">>>>>>>>>>>>>>>>sql:"+sql);
		List list=this.find(sql);
		if(list!=null&&list.size()>=1){
			b=true;
		}else{
			b=false;
		}
		return b;
	}


	/**
	 * Describe:保存
	 * @param stocks 单据信息属性   datailList 单据明细信息属性   syslog 日志    bystockswayid 操作方式  入库
	 * @auth:aozhuozu
	 * @return
	 * return:boolean
	 * Date:2012-7-23
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iompersonalticketdetails> datailList,Syslog syslog,Long bystockswayid) throws Exception{
		boolean b=false;
		String msql="select szstocksbillid from Iomstocksbill order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		stocks.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		stocks.setBystockswayid(bystockswayid);  //操作方式
		String sql="select max(im.szstocksbillcode) from Iomstocksbill im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
		List lst=this.find(sql);
		StringBuffer stockcode=new StringBuffer();

		String maxidst=(String) lst.get(0);
		if(maxidst==null||"".equals(maxidst)){
			stockcode.append(Tools.getDay());
			stockcode.append("0001");
		}else{
			Long md=new Long(maxidst)+1L;
			stockcode.append(md.toString());
		}
		stocks.setSzstocksbillcode(stockcode.toString()); //单据编号
		stocks.setBybillstate(1L);
		this.save(stocks);
		List<Iomstocksbilldetails> stationList=new ArrayList<Iomstocksbilldetails>();
		if(datailList!=null&&datailList.size()>0){
			for(int i=0;i<datailList.size();i++){
				Iompersonalticketdetails stadeta = datailList.get(i);
				Iomstocksbilldetails billdeta = new Iomstocksbilldetails();
				Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stadeta.getItickettypeid());
				String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);

				billdeta.setSzstocksbillid(stocks.getSzstocksbillid());  //单据信息

				String istartserial=stadeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String iendserial=stadeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setSzstartticketcode(stadeta.getSzstartticketcode());
				billdeta.setSzendticketcode(stadeta.getSzendticketcode());
//				billdeta.setIstartserial(Tools.Text36ToConvert(istartserial));
//				billdeta.setIendserial(Tools.Text36ToConvert(iendserial));
				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
					billdeta.setIstartserial(Long.parseLong(istartserial));
					billdeta.setIendserial(Long.parseLong(iendserial));
				}else{
					billdeta.setIstartserial(Tools.Text36ToConvert(istartserial));
					billdeta.setIendserial(Tools.Text36ToConvert(iendserial));
				}
				billdeta.setIamount(stadeta.getIamount());
				//判断票号是否存在
//				if(retrieWarehouse(stadeta,stocks.getIstationinid())){
//					return true;
//				}
				if(!retrieWarehouse(stadeta,stocks.getIhandler())){
					return true;
				}

				String tsql="select szstocksbilldetailsid from Iomstocksbilldetails order by to_number(szstocksbilldetailsid) desc";
				List splist=this.find(tsql);
				String ksdetaid="0";
				if(splist!=null&&splist.size()>0){
					ksdetaid=(String) splist.get(0);
				}
				Long dp=new Long(ksdetaid)+1L;
				billdeta.setSzstocksbilldetailsid(dp.toString());

				stationList.add(billdeta);

				this.save(billdeta);
			}
		}

		//合并数据
		addPersonaldetails(stationList);

		//日志
		Iomwarehouse house=(Iomwarehouse) this.get(Iomwarehouse.class, stocks.getIstationinid());
		syslog.setLogdatetime(Tools.getDayTimes());
		syslog.setStlg("0214");
		syslog.setBrief("余票入库：" + house.getSzwarehousename());
		syslog.setNote("余票入库：" +house.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		try {
			this.save(syslog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}


	/**
	 * Describe:合并整理
	 * @param stockList 单据明细信息属性
	 * @auth:aozhuozu
	 * @return
	 * Date:2012-7-23
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList) {
		if(stockList!=null&&stockList.size()>0){
			for(int x=0;x<stockList.size();x++){
				//个人结存明细表票段整理
				Iomstocksbilldetails stockDetail = stockList.get(x);
				Iomstocksbill stockBill = (Iomstocksbill) this.get(Iomstocksbill.class, stockDetail.getSzstocksbillid());
				String sql = " from Iompersonalticketdetails sta where sta.itickettypeid="+stockDetail.getItickettypeid()+" and sta.ireceiverid="+stockBill.getIhandler();
				List staList = this.find(sql);
				if(staList!=null&&staList.size()>0){
					boolean b = false;
					for(int i=0;i<staList.size();i++){
						Iompersonalticketdetails staDetail = (Iompersonalticketdetails) staList.get(i);
						if(stockDetail.getIstartserial()-staDetail.getIstartserial()>=0&&staDetail.getIendserial()-stockDetail.getIendserial()>=0){
							if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//个人明细起始票号与单据明细起始票号做比较
								if(stockDetail.getIendserial()-staDetail.getIendserial()==0){	//个人明细截止票号与单据明细截止票号做比较
									this.delete(staDetail);
									b = true;
									break;
								}else{
									staDetail.setIstartserial(stockDetail.getIendserial()+1L);
									staDetail.setSzstartticketcode(showViewupendcode(stockDetail.getSzendticketcode(), 2L, stockDetail.getItickettypeid()));
									staDetail.setIamount(staDetail.getIamount()-stockDetail.getIamount());
									this.update(staDetail);
									b = true;
									break;
								}
							}else if(stockDetail.getIendserial()-staDetail.getIendserial()==0){	//个人明细截止票号与单据明细截止票号做比较
								if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//个人明细截止票号与单据明细截止票号做比较
									this.delete(staDetail);
									b = true;
									break;
								}else{
									staDetail.setIendserial(stockDetail.getIstartserial()-1L);
									staDetail.setSzendticketcode(showViewupendcode(stockDetail.getSzstartticketcode(), 0L, stockDetail.getItickettypeid()));
									staDetail.setIamount(staDetail.getIamount()-stockDetail.getIamount());
									this.update(staDetail);
									b = true;
									break;
								}
							}
							if(!b){
								Iompersonalticketdetails detail = new Iompersonalticketdetails();
								Long maxid = this.getMaxPk("idetailsid", "Iompersonalticketdetails")+1L;
								detail.setIdetailsid(maxid);
								detail.setItickettypeid(stockDetail.getItickettypeid());
								detail.setIreceiverid(stockBill.getIhandler());
								detail.setSzstartticketcode(showViewupendcode(stockDetail.getSzendticketcode(), 2L, stockDetail.getItickettypeid()));
								detail.setSzendticketcode(staDetail.getSzendticketcode());
								detail.setIstartserial(stockDetail.getIendserial()+1L);
								detail.setIendserial(staDetail.getIendserial());
								detail.setIamount(staDetail.getIendserial()-stockDetail.getIendserial());
								this.save(detail);

								staDetail.setSzendticketcode(showViewupendcode(stockDetail.getSzstartticketcode(), 0L, stockDetail.getItickettypeid()));
								staDetail.setIendserial(stockDetail.getIstartserial()-1L);
								staDetail.setIamount(stockDetail.getIstartserial()-staDetail.getIstartserial());
								this.update(staDetail);
								break;
							}
						}else{
							continue;
						}
					}
				}

				//仓库明细表票段整理
				String psql = "from Iomstationticketdetails per where per.itickettypeid="+stockDetail.getItickettypeid()+" and per.iwarehouseid="+stockBill.getIstationinid();
				List perList = this.find(psql);
				if(perList!=null&&perList.size()>0){
					boolean b = false;
					for(int y=0;y<perList.size();y++){
						Iomstationticketdetails iomPerdetail = (Iomstationticketdetails) perList.get(y);
						if(stockDetail.getIstartserial()-iomPerdetail.getIendserial()==1){	//单据表的起始流水号与仓库明细表的截止流水号做比较
							boolean sp = false;
							for(int s=y+1;s<perList.size();s++){
								Iomstationticketdetails iomPertic = (Iomstationticketdetails) perList.get(s);
								if(iomPertic.getIstartserial()-stockDetail.getIendserial()==1){	//单据表的截止流水号与仓库明细表的起始流水号做比较
									iomPertic.setSzstartticketcode(iomPerdetail.getSzstartticketcode());
									iomPertic.setIstartserial(iomPerdetail.getIstartserial());
									iomPertic.setIamount(iomPertic.getIamount()+iomPerdetail.getIamount()+stockDetail.getIamount());
									this.update(iomPertic);
									sp = true;
									break;
								}else{
									sp = false;
									continue;
								}
							}
							if(sp){
								this.delete(iomPerdetail);
							}else{
								iomPerdetail.setSzendticketcode(stockDetail.getSzendticketcode());
								iomPerdetail.setIendserial(stockDetail.getIendserial());
								iomPerdetail.setIamount(iomPerdetail.getIamount()+stockDetail.getIamount());
								this.update(iomPerdetail);
							}
							b=true;
							break;
						}else if(iomPerdetail.getIstartserial()-stockDetail.getIendserial()==1){	//仓库明细表的开始流水号与单据表截止流水号做比较
							boolean sp = false;
							for(int s=y+1;s<perList.size();s++){
								Iomstationticketdetails iomPertic = (Iomstationticketdetails) perList.get(s);
								if(iomPertic.getIstartserial()-iomPerdetail.getIendserial()==1){
									iomPertic.setSzstartticketcode(stockDetail.getSzstartticketcode());
									iomPertic.setIstartserial(stockDetail.getIstartserial());
									iomPertic.setIamount(iomPertic.getIamount()+iomPerdetail.getIamount()+stockDetail.getIamount());
									this.update(iomPertic);
									sp = true;
									break;
								}else if(stockDetail.getIstartserial()-iomPertic.getIendserial()==1){
									iomPertic.setSzendticketcode(iomPerdetail.getSzendticketcode());
									iomPertic.setIendserial(iomPerdetail.getIendserial());
									iomPertic.setIamount(iomPertic.getIamount()+iomPerdetail.getIamount()+stockDetail.getIamount());
									this.update(iomPertic);
									sp = true;
									break;
								}else{
									continue;
								}
							}
							if(sp){
								this.delete(iomPerdetail);
							}else{
								iomPerdetail.setSzstartticketcode(stockDetail.getSzstartticketcode());
								iomPerdetail.setIstartserial(stockDetail.getIstartserial());
								iomPerdetail.setIamount(iomPerdetail.getIamount()+stockDetail.getIamount());
								this.update(iomPerdetail);
							}

							b = true;
							break;
						}else{
							b = false;
							continue;
						}
					}
					if(!b){
						Iomstationticketdetails personaldetail = new Iomstationticketdetails();
						Long maxid = this.getMaxPk("idetailsid", "Iomstationticketdetails")+1;
						personaldetail.setIdetailsid(maxid);
						personaldetail.setIwarehouseid(stockBill.getIstationinid());
						personaldetail.setItickettypeid(stockDetail.getItickettypeid());
						personaldetail.setSzstartticketcode(stockDetail.getSzstartticketcode());
						personaldetail.setSzendticketcode(stockDetail.getSzendticketcode());
						personaldetail.setIstartserial(stockDetail.getIstartserial());
						personaldetail.setIendserial(stockDetail.getIendserial());
						personaldetail.setIamount(stockDetail.getIamount());
						this.save(personaldetail);
					}
				}else{
					Iomstationticketdetails personaldetail = new Iomstationticketdetails();
					Long maxid = this.getMaxPk("idetailsid", "Iomstationticketdetails")+1;
					personaldetail.setIdetailsid(maxid);
					personaldetail.setIwarehouseid(stockBill.getIstationinid());
					personaldetail.setItickettypeid(stockDetail.getItickettypeid());
					personaldetail.setSzstartticketcode(stockDetail.getSzstartticketcode());
					personaldetail.setSzendticketcode(stockDetail.getSzendticketcode());
					personaldetail.setIstartserial(stockDetail.getIstartserial());
					personaldetail.setIendserial(stockDetail.getIendserial());
					personaldetail.setIamount(stockDetail.getIamount());
					this.save(personaldetail);
				}
			}
		}
	}


	/**
	 * Describe:获取截止票号
	 * @param ticketcode    iamount    itickettypeid 票信息键值
	 * @auth:aozhuozu
	 * @return
	 * return:String
	 * Date:2012-7-23
	 */
	public String showViewupendcode(String ticketcode, Long iamount,Long itickettypeid) {
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		StringBuffer endcode=new StringBuffer();
		if(edmticket!=null){
			String sql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				//获取票号前缀
				String start=ticketcode.substring(0,ticketrule.getIntons2()+ticketrule.getItickettypecodepos());
				//获取票号后缀
				String end=ticketcode.substring(ticketcode.length()-Integer.parseInt(ticketrule.getSzticketnosuffix()),ticketcode.length());
				//获取开始票号的流水号
				String lsh=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				//截止票号
//				Long szcodeend=Tools.Text36ToConvert(lsh)+iamount-1L;
				Long szcodeend=0L;
				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
					szcodeend=Long.parseLong(lsh)+iamount-1L;
				}else{
					szcodeend=Tools.Text36ToConvert(lsh)+iamount-1L;
				}
				StringBuffer szcodes=new StringBuffer();
				if(szcodeend.toString().length()<ticketrule.getIserialnolen()){
					for (int b = 0; b <ticketrule.getIserialnolen() - szcodeend.toString().length(); b++) {
						szcodes.append("0");
					}
				}
				szcodes.append(szcodeend);

				StringBuffer endcodesg=new StringBuffer();
//				String szflse = Tools.ConvertTo36Text(Long.parseLong(szcodes.toString()), 0);
//				if(szflse.length()<ticketrule.getIserialnolen()){
//					for (int b = 0; b <ticketrule.getIserialnolen() -szflse.length(); b++) {
//						endcodesg.append("0");
//					}
//				}
				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==0){
					String szflse = Tools.ConvertTo36Text(Long.parseLong(szcodes.toString()), 0);
					if(szflse.length()<ticketrule.getIserialnolen()){
						for (int b = 0; b <ticketrule.getIserialnolen() -szflse.length(); b++) {
							endcodesg.append("0");
						}
					}
					endcodesg.append(szflse);
				}else{
					endcodesg.append(szcodes);
				}
//				String szflse = Tools.ConvertTo36Text(Long.parseLong(szcodes.toString()), 0);
//				endcodesg.append(szflse);
				endcode.append(start);
				endcode.append(endcodesg);
				endcode.append(end);
			}
		}
		return endcode.toString();
	}

}