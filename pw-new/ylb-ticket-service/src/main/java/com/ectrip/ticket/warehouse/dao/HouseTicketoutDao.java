package com.ectrip.ticket.warehouse.dao;

import java.util.ArrayList;
import java.util.List;

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
import com.ectrip.ticket.warehouse.dao.idao.IHouseTicketoutDao;

public class HouseTicketoutDao  extends GenericDao implements IHouseTicketoutDao {
	/**
	 *
	 * Describe:所有仓库结存明细信息
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#findAllStationticket(long, long)
	 * @param istationoutid	仓库ID
	 * @param itickettypeid	票类型ID
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-13
	 */
	public List findAllStationticket(long istationoutid, long itickettypeid) {
		Edmtickettypetab e = (Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String hsql = "from Iomstationticketdetails where itickettypeid="+itickettypeid+" and iwarehouseid="+istationoutid;
		List list = this.find(hsql);
		return list;
	}
	/**
	 *
	 * Describe:检查票号是否在该票段范围内
	 * @author:chenxinhao
	 * @param ticketid	   票ID
	 * @param ticketCode 待检查票号
	 * @param idetailsid 仓库结存明细ID
	 * @return
	 * return:boolean
	 * Date:2012-7-17
	 */
	public boolean checkTicketcodeRange(long ticketid,String ticketCode,long idetailsid){
		boolean b = false;
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, ticketid);
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
		//修改
		Long ticketserial = 0L;
		if(ticketrule.getIntons1()==1){
			ticketserial = Long.parseLong(ticketCode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}else{
			ticketserial = Tools.Text36ToConvert(ticketCode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}
		Iomstationticketdetails stadetails =  (Iomstationticketdetails) this.get(Iomstationticketdetails.class,idetailsid);
		if(ticketserial>=stadetails.getIstartserial()&&ticketserial<=stadetails.getIendserial()){
			b = true;
		}
		return b;
	}
	/**
	 *
	 * Describe:计算票数量
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#showiamount(long, java.lang.String, java.lang.String)
	 * @param ticketid	票ID
	 * @param startcode	起始票号
	 * @param endcode	截止票号
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-19
	 */
	public long showiamount(long ticketid,String startcode, String endcode) {
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, ticketid);
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
		//修改
		Long startserial = 0L;
		Long endserial = 0L;
		if(ticketrule.getIntons1()==1){
			startserial = Long.parseLong(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			endserial = Long.parseLong(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}else{
			startserial = Tools.Text36ToConvert(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			endserial = Tools.Text36ToConvert(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
		}
		return endserial-startserial+1L;
	}

	/**
	 * *
	 * Describe:判断仓库结存明细表中是否存在该票段
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#retriedStadetails(com.ectrip.model.warehouse.Iomstationticketdetails, long)
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public boolean retriedStadetails(Iomstationticketdetails stadeta,long istationoutid) {
		boolean b = false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stadeta.getItickettypeid());
		String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
		Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
		String istartserial=stadeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
		String iendserial=stadeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
		//修改
		if(ticketrule.getIntons1()==1){
			stadeta.setIstartserial(Long.parseLong(istartserial));
			stadeta.setIendserial(Long.parseLong(iendserial));
		}else{
			stadeta.setIstartserial(Tools.Text36ToConvert(istartserial));
			stadeta.setIendserial(Tools.Text36ToConvert(iendserial));
		}

		String sql = "from Iomstationticketdetails details where details.itickettypeid="+stadeta.getItickettypeid()+" and details.iwarehouseid="+istationoutid+" and " +
				" details.istartserial<="+stadeta.getIstartserial()+" and details.iendserial>="+stadeta.getIendserial()+"";

		List list = this.find(sql);
		if(list!=null&&list.size()>=1){
			b = true;	//票段存在
		}else{
			b = false;
		}
		return b;
	}
	/**
	 *
	 * Describe:领票出库 保存
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstationticketdetails> detailList, Syslog syslog) throws Exception{
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
		stocks.setBystockswayid(6L);  //操作方式
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
		this.save(stocks);		//保存单据信息
		List<Iomstocksbilldetails> stockList=new ArrayList<Iomstocksbilldetails>();
		if(detailList!=null&&detailList.size()>0){
			for(int i=0;i<detailList.size();i++){
				Iomstationticketdetails stadeta = detailList.get(i);
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
				//修改
				if(ticketrule.getIntons1()==1){
					billdeta.setIstartserial(Long.parseLong(istartserial));
					billdeta.setIendserial(Long.parseLong(iendserial));
				}else{
					billdeta.setIstartserial(Tools.Text36ToConvert(istartserial));
					billdeta.setIendserial(Tools.Text36ToConvert(iendserial));
				}
				billdeta.setIamount(stadeta.getIamount());

				//判断票段是否存在
				if(!retriedStadetails(stadeta, stocks.getIstationoutid())){
					return true;	//不存在
				}

				String tsql="select szstocksbilldetailsid from Iomstocksbilldetails order by to_number(szstocksbilldetailsid) desc";
				List splist=this.find(tsql);
				String ksdetaid="0";
				if(splist!=null&&splist.size()>0){
					ksdetaid=(String) splist.get(0);
				}
				Long dp=new Long(ksdetaid)+1L;
				billdeta.setSzstocksbilldetailsid(dp.toString());

				stockList.add(billdeta);
				this.save(billdeta);
			}
			//票段整理
			addPersonaldetails(stockList);
			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(stocks.getIstationoutid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, stocks.getIstationoutid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0215");
			syslog.setBrief("领用出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}
		return b;
	}
	/**
	 *
	 * Describe:仓库明细表 个人明细表 添加和票段整理
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#addPersonaldetails(java.util.List)
	 * @param stockList
	 * @author chenxinhao
	 * Date:2012-7-20
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList) {
		if(stockList!=null&&stockList.size()>0){
			for(int x=0;x<stockList.size();x++){

				//仓库结存明细表票段整理
				Iomstocksbilldetails stockDetail = stockList.get(x);
				Iomstocksbill stockBill = (Iomstocksbill) this.get(Iomstocksbill.class, stockDetail.getSzstocksbillid());
				String sql = " from Iomstationticketdetails sta where sta.itickettypeid="+stockDetail.getItickettypeid()+" and sta.iwarehouseid="+stockBill.getIstationoutid();
				List staList = this.find(sql);
				if(staList!=null&&staList.size()>0){
					boolean b = false;
					for(int i=0;i<staList.size();i++){
						Iomstationticketdetails staDetail = (Iomstationticketdetails) staList.get(i);
						if(stockDetail.getIstartserial()-staDetail.getIstartserial()>=0&&staDetail.getIendserial()-stockDetail.getIendserial()>=0){
							if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//仓库明细起始票号与单据明细起始票号做比较
								if(stockDetail.getIendserial()-staDetail.getIendserial()==0){	//仓库明细截止票号与单据明细截止票号做比较
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
							}else if(stockDetail.getIendserial()-staDetail.getIendserial()==0){	//仓库明细截止票号与单据明细截止票号做比较
								if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//仓库明细起始票号与单据明细起始票号做比较
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
								Iomstationticketdetails detail = new Iomstationticketdetails();
								Long maxid = this.getMaxPk("idetailsid", "Iomstationticketdetails")+1L;
								detail.setIdetailsid(maxid);
								detail.setItickettypeid(stockDetail.getItickettypeid());
								detail.setIwarehouseid(stockBill.getIstationoutid());
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

				//个人明细表票段整理
				String psql = "from Iompersonalticketdetails per where per.itickettypeid="+stockDetail.getItickettypeid()+" and per.ireceiverid="+stockBill.getIhandler();
				List perList = this.find(psql);
				if(perList!=null&&perList.size()>0){
					boolean b = false;
					for(int y=0;y<perList.size();y++){
						Iompersonalticketdetails iomPerdetail = (Iompersonalticketdetails) perList.get(y);
						if(stockDetail.getIstartserial()-iomPerdetail.getIendserial()==1){	//单据表的起始流水号与个人明细表的截止流水号做比较
							boolean sp = false;
							for(int s=y+1;s<perList.size();s++){
								Iompersonalticketdetails iomPertic = (Iompersonalticketdetails) perList.get(s);
								if(iomPertic.getIstartserial()-stockDetail.getIendserial()==1){	//单据表的截止流水号与个人明细表的起始流水号做比较
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
						}else if(iomPerdetail.getIstartserial()-stockDetail.getIendserial()==1){	//个人明细表的开始流水号与单据表截止流水号做比较
							boolean sp = false;
							for(int s=y+1;s<perList.size();s++){
								Iompersonalticketdetails iomPertic = (Iompersonalticketdetails) perList.get(s);
								if(iomPertic.getIstartserial()-iomPerdetail.getIendserial()==1){
									iomPertic.setSzstartticketcode(stockDetail.getSzstartticketcode());
									iomPertic.setIstartserial(stockDetail.getIstartserial());
									iomPertic.setIamount(iomPertic.getIamount()+iomPerdetail.getIamount()+stockDetail.getIamount());
									this.update(iomPertic);
									sp = true;
									break;
								}else if(stockDetail.getIstartserial()-iomPertic.getIstartserial()==1){
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
						Iompersonalticketdetails personaldetail = new Iompersonalticketdetails();
						Long maxid = this.getMaxPk("idetailsid", "Iompersonalticketdetails")+1;
						personaldetail.setIdetailsid(maxid);
						personaldetail.setIreceiverid(stockBill.getIhandler());
						personaldetail.setItickettypeid(stockDetail.getItickettypeid());
						personaldetail.setSzstartticketcode(stockDetail.getSzstartticketcode());
						personaldetail.setSzendticketcode(stockDetail.getSzendticketcode());
						personaldetail.setIstartserial(stockDetail.getIstartserial());
						personaldetail.setIendserial(stockDetail.getIendserial());
						personaldetail.setIamount(stockDetail.getIamount());
						this.save(personaldetail);
					}
				}else{
					Iompersonalticketdetails personaldetail = new Iompersonalticketdetails();
					Long maxid = this.getMaxPk("idetailsid", "Iompersonalticketdetails")+1;
					personaldetail.setIdetailsid(maxid);
					personaldetail.setIreceiverid(stockBill.getIhandler());
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
	 *
	 * Describe:获取截止票号
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#showViewupendcode(java.lang.String, java.lang.Long, java.lang.Long)
	 * @param ticketcode
	 * @param iamount
	 * @param itickettypeid
	 * @return
	 * @author chenxinhao
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
				//获取票号前缀和票代码
				String start=ticketcode.substring(0,ticketrule.getIntons2()+ticketrule.getItickettypecodepos());
				//获取票号后缀
				String end=ticketcode.substring(ticketcode.length()-Integer.parseInt(ticketrule.getSzticketnosuffix()),ticketcode.length());
				//获取开始票号的流水号
				String lsh=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				//截止票号流水号
				Long szcodeend=null;
				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
					szcodeend=Long.parseLong(lsh)+iamount-1L;
				}else{
					szcodeend=Tools.Text36ToConvert(lsh)+iamount-1L;
				}

				StringBuffer szcodes=new StringBuffer();

				if(szcodeend.toString().length()<ticketrule.getIserialnolen()){
					for (int b = 0; b <ticketrule.getIserialnolen() -szcodeend.toString().length(); b++) {
						szcodes.append("0");
					}
				}
				szcodes.append(szcodeend);

				StringBuffer endcodesg=new StringBuffer();
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


				endcode.append(start);
				endcode.append(endcodesg);
				endcode.append(end);
			}
		}
		return endcode.toString();
	}

	/**
	 *
	 * Describe:通过服务商id查找子票信息
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#showAllzticket(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-1
	 */
	public List showAllzticket(Long iscenicid) {
		StringBuffer hsql = new StringBuffer();
		hsql = hsql.append("from Edmtickettypetab ttype ");
		hsql = hsql.append(" where ttype.iscenicid="+iscenicid+" and ttype.bycategorytype <> '0010'");
		List list = this.find(hsql.toString());
		return list;
	}

}

