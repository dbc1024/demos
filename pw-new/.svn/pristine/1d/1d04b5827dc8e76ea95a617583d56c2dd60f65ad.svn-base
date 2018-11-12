package com.ectrip.ticket.warehouse.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Iomwarehouse;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.warehouse.Iomstationticketdetails;
import com.ectrip.ticket.model.warehouse.Iomstocksbill;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.model.warehouse.Iomwarehouseincome;
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseObsoleteDAO;

public class WarehouseObsoleteDAO extends GenericDao implements IWarehouseObsoleteDAO {

	/**
	 *
	 * Describe:计算票数量
	 * @see com.ectrip.system.warehouse.dao.idao.IHouseTicketoutDao#showiamount(long, java.lang.String, java.lang.String)
	 * @param ticketid	票ID
	 * @param startcode	起始票号
	 * @param endcode	截止票号
	 * @return
	 * @author wangling
	 * Date:2012-7-19
	 */
	public long showiamount(long ticketid,String startcode, String endcode) {
		Edmtickettypetab edmticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, ticketid);
		Long startserial=null;
		Long endserial=null;
		if(edmticket!=null){
			String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);

			if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
				startserial = Long.parseLong(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
				endserial = Long.parseLong(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			}else{
				startserial = Tools.Text36ToConvert(startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
				endserial = Tools.Text36ToConvert(endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()));
			}
			return endserial-startserial+1L;
		}

		return 0L;
	}
	/**
	 *
	 * Describe:仓库明细表
	 * @author:wangling
	 * @param stockList
	 * return:void
	 * Date:2012-7-23
	 */
	public void addPersonaldetails(List<Iomstocksbilldetails> stockList) {
		boolean panDuan = false;
		boolean b = false;
		if(stockList!=null&&stockList.size()>0){
			for(int x=0;x<stockList.size();x++){
				//仓库结存明细表票段整理
				Iomstocksbilldetails stockDetail = stockList.get(x);
				Iomstocksbill stockBill = (Iomstocksbill) this.get(Iomstocksbill.class, stockDetail.getSzstocksbillid());
				String sql = " from Iomstationticketdetails sta where sta.itickettypeid="+stockDetail.getItickettypeid()+" and sta.iwarehouseid="+stockBill.getIstationoutid();
				List staList = this.find(sql);

				if(stockList != null&&staList.size()>0){
					for(int i=0;i<staList.size();i++){
						//初始入库表的数据字段
						Iomstationticketdetails st = (Iomstationticketdetails) staList.get(i);
						String hql = " from Iomwarehouseincome ware where ware.itickettypeid="+stockDetail.getItickettypeid();
						List ware = this.find(hql);
						for(int j=0;j<ware.size();j++){

							Iomwarehouseincome house = (Iomwarehouseincome) ware.get(j);
							if(stockDetail.getIstartserial()-house.getIstartserial()>=0&&house.getIendserial()-stockDetail.getIendserial()>=0){
								if(stockDetail.getIstartserial()-house.getIstartserial()==0){
									if(stockDetail.getIendserial()-house.getIendserial()==0){
										this.delete(house);
										panDuan=true;
										break;
									}else{
										house.setIstartserial(stockDetail.getIendserial()+1L);
										house.setIamount(house.getIamount()-stockDetail.getIamount());
										this.update(house);
										panDuan=true;
										break;
									}
								}else {
									if(stockDetail.getIendserial()-house.getIendserial()==0){
										house.setIendserial(stockDetail.getIstartserial()-1L);
										house.setIamount(house.getIamount()-stockDetail.getIamount());
										this.update(house);
										panDuan=true;
										break;
									}else{
										//在原有的上面更改
										Long end  = house.getIendserial();
										house.setIendserial(stockDetail.getIstartserial()-1L);
										house.setIamount(house.getIendserial()-house.getIstartserial()+1L);
										this.update(house);
										String hsql = "select idetailsid from Iomwarehouseincome  order by idetailsid desc";
										List id = this.find(hsql);
										Long houseId = 0L;
										if(id !=null&&id.size()>0){
											houseId = (Long)id.get(0);
										}
										//添加一条数据
										Iomwarehouseincome house2 = new Iomwarehouseincome();
										house2.setIdetailsid(houseId+1L);
										house2.setItickettypeid(house.getItickettypeid());
										house2.setIstartserial(stockDetail.getIendserial()+1L);
										house2.setIendserial(end);
										house2.setIamount(end-stockDetail.getIendserial());
										this.save(house2);
										panDuan=true;
										break;
									}
								}
							}
						}
					}
				}

				if(staList!=null&&staList.size()>0){

					for(int i=0;i<staList.size();i++){
						Iomstationticketdetails staDetail = (Iomstationticketdetails) staList.get(i);
						if(stockDetail.getIstartserial()-staDetail.getIstartserial()>=0&&staDetail.getIendserial()-stockDetail.getIendserial()>=0){
							if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//仓库明细起始票号与单据明细起始票号做比较
								if(stockDetail.getIendserial()-staDetail.getIendserial()==0){	//仓库明细起始票号与单据明细起始票号做比较
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
								if(stockDetail.getIstartserial()-staDetail.getIstartserial()==0){	//仓库明细截止票号与单据明细截止票号做比较
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
			}
		}
	}

	/**
	 * *
	 * Describe:判断仓库结存明细表中是否存在该票段
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseObsoleteDAO#retriedStadetails(com.ectrip.model.warehouse.Iomstationticketdetails, long)
	 * @param stadeta
	 * @param istationoutid
	 * @return
	 * @author wangling
	 * Date:2012-7-24
	 */
	public boolean retriedStadetails(Iomstationticketdetails stadeta,long istationoutid) {
		boolean b = false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stadeta.getItickettypeid());
		if(edmticket!=null){
			String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);

			String istartserial=stadeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			String iendserial=stadeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

			if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
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
		}

		return b;
	}
	/**
	 * *
	 * Describe:保存单据信息操作
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseObsoleteDAO#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param detailList
	 * @param syslog
	 * @return
	 * @author wangling
	 * Date:2012-7-24
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstationticketdetails> detailList, Syslog syslog) {
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
		stocks.setBystockswayid(4L);  //操作方式
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

				String istartserial=stadeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());//开始流水号
				String iendserial=stadeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());//结束流水号

				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setSzstartticketcode(stadeta.getSzstartticketcode());
				billdeta.setSzendticketcode(stadeta.getSzendticketcode());

				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
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
			//票段整理并判断是否出入仓库和仓库明细是否都保存成功
			addPersonaldetails(stockList);
			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(stocks.getIstationoutid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, stocks.getIstationoutid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0214");
			syslog.setBrief("出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}
		return b;
	}

	/**
	 *
	 * Describe:获取截止票号
	 * @auth:lijingrui
	 * @param ticketcode
	 * @param iamount
	 * @param itickettypeid
	 * @return
	 * return:String
	 * Date:2012-10-16
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

}

