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
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseDAO;

public class WarehouseDAO extends GenericDao implements IWarehouseDAO{

	/**
	 * *
	 * Describe:显示仓库信息
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getListIomware(java.lang.String, java.lang.String)
	 * @param scenics 服务商ID
	 * @param isonlyby 是否显示总库   01显示总库和分库  02分库  03 总库
	 * @return
	 * @author lijingrui
	 * Date:2012-7-10
	 */
	public List showListIomware(String scenics, String isonlyby) {
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(wh.iwarehouseid as iwarehouseid,wh.iscenicid as iscenicid,wh.szwarehousecode as szwarehousecode,wh.szwarehousename as szwarehousename,wh.szcontact as szcontact,wh.szaddress as szaddress,wh.byisuse as byisuse,wh.bisdefault as bisdefault,wh.szmemo as szmemo) from Iomwarehouse wh where wh.byisuse=1 ");
		if (scenics != null &&! "".equals(scenics)) {
			hsql.append(" and wh.iscenicid in (" + scenics + ")");
		}
		if(isonlyby!=null&&!"".equals(isonlyby)){
			if("02".equals(isonlyby)){
				hsql.append(" and wh.bisdefault=0 ");
			}else if("03".equals(isonlyby)){
				hsql.append(" and wh.bisdefault=1 ");
			}
		}
		hsql.append(" order by wh.szwarehousecode ");
		return this.find(hsql.toString());
	}

	/**
	 * *
	 * Describe:根据仓库ID来查询某服务商下的产品信息
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getListticket(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-10
	 */
	public List showListticket(Long iwarehouseid) {
		Iomwarehouse house=(Iomwarehouse) this.get(Iomwarehouse.class, iwarehouseid);
		// 出票方式bymaketicketway--CPFS -01现场激活，02打印激活
		//介质类型bymediatype-- CKFS  -00一维条码  01 二维条码
		StringBuffer sql=new StringBuffer();
		sql.append(" from Edmtickettypetab where byisuse=1 and bymaketicketway in ('01','02') and bymediatype in ('00','01') ");
		if(house.getIscenicid()!=null&&!"".equals(house.getIscenicid())&&!"0".equals(house.getIscenicid())&&house.getIscenicid()!=0){
			sql.append(" and iscenicid="+house.getIscenicid());
		}
		sql.append(" order by itickettypeid ");
		List list = this.find(sql.toString());
		return list;
	}



	/**
	 * *
	 * Describe:保存 初始入库信息
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#insertstockbill(com.ectrip.model.warehouse.Iomstocksbill, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-7-12
	 * @throws Exception
	 */
	public boolean insertstockbill(Iomstocksbill stocks,List<Iomstocksbilldetails> datailList,Syslog syslog,Long bystockswayid) throws Exception{
		boolean b=false;
		String msql="select szstocksbillid from Iomstocksbill order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		stocks.setBybillstate(1L);  //0 禁用  1 启用
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

		this.save(stocks);

		List<Iomstocksbilldetails> stationList=new ArrayList<Iomstocksbilldetails>();
		if(datailList!=null&&datailList.size()>0){
			for(int i=0;i<datailList.size();i++){
				Iomstocksbilldetails billdeta=datailList.get(i);
				Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, billdeta.getItickettypeid());
				String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);

				billdeta.setSzstocksbillid(stocks.getSzstocksbillid());  //单据信息

				String istartserial=billdeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String iendserial=billdeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
					billdeta.setIstartserial(Long.parseLong(istartserial));
					billdeta.setIendserial(Long.parseLong(iendserial));
				}else{
					billdeta.setIstartserial(Tools.Text36ToConvert(istartserial));
					billdeta.setIendserial(Tools.Text36ToConvert(iendserial));
				}


				//判断票号是否存在
				if(retrieWarehouse(billdeta)){
					return true;
				}

				//票号加密
//				Encrypt encrypt = new Encrypt();
//				billdeta.setSzstartticketcode(encrypt.passwordEncrypt(billdeta.getSzstartticketcode()));
//				billdeta.setSzendticketcode(encrypt.passwordEncrypt(billdeta.getSzendticketcode()));

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

		addHousedetail(stationList);  //合并数据


		//日志
		Iomwarehouse house=(Iomwarehouse) this.get(Iomwarehouse.class, stocks.getIstationinid());
		Iomwarehouse ware=new Iomwarehouse();
		if(stocks.getIstationoutid()!=null){
			ware=(Iomwarehouse) this.get(Iomwarehouse.class, stocks.getIstationoutid());
		}

		syslog.setLogdatetime(Tools.getDayTimes());
		if(stocks.getBystockswayid()==1){
			syslog.setStlg("0211");
			syslog.setBrief("初始入库：" + house.getSzwarehousename());
			syslog.setNote("初始入库：" +house.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}else if(stocks.getBystockswayid()==3){
			syslog.setStlg("0212");
			syslog.setBrief("调拨：" + house.getSzwarehousename());
			syslog.setNote("调拨：入库仓库" +house.getSzwarehousename()+",出库仓库:"+ware.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}else if(stocks.getBystockswayid()==4){
			syslog.setStlg("0213");
			syslog.setBrief("作废出库：" + ware.getSzwarehousename());
			syslog.setNote("作废出库：" +ware.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}else if(stocks.getBystockswayid()==5){
			syslog.setStlg("0214");
			syslog.setBrief("余票入库：" + house.getSzwarehousename());
			syslog.setNote("余票入库：" +house.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}else if(stocks.getBystockswayid()==6){
			syslog.setStlg("0215");
			syslog.setBrief("领用出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}else{
			syslog.setStlg("0211");
			syslog.setBrief("初始入库：" + house.getSzwarehousename());
			syslog.setNote("初始入库：" +house.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );
		}
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);


		return b;
	}


	/**
	 *
	 * Describe:汇总表  明细表 数据合并
	 * @auth:lijingrui
	 * @param datailList
	 * return:void
	 * Date:2012-7-18
	 */
	public void addHousedetail(List<Iomstocksbilldetails> datailList){
		if(datailList!=null&&datailList.size()>0){
			for(int x=0;x<datailList.size();x++){
				Iomstocksbilldetails detail=datailList.get(x);
				//操作初始入库汇总表
				String hsql=" from Iomwarehouseincome ware where ware.itickettypeid="+detail.getItickettypeid();
				List houselist=this.find(hsql);
				//获取该产品下的数据  如果没有 则新增   如果有  则判断数据是否有联系 合并
				if(houselist!=null&&houselist.size()>0){
					boolean b=false;

					for(int y=0;y<houselist.size();y++){
						Iomwarehouseincome iomware=(Iomwarehouseincome) houselist.get(y);
						//单据明细开始流水号与汇总表中的 截止流水号作比较
						if(detail.getIstartserial()-iomware.getIendserial()==1){
							boolean sp=false;
							for(int s=y+1;s<houselist.size();s++){
								Iomwarehouseincome come=(Iomwarehouseincome) houselist.get(s);
								if(come.getIstartserial()-detail.getIendserial()==1){
									come.setIstartserial(iomware.getIstartserial());
									come.setIamount(come.getIamount()+iomware.getIamount()+detail.getIamount());
									this.update(come);

									sp=true;
									break;
								}else if(iomware.getIstartserial()-come.getIendserial()==1){
									iomware.setIendserial(detail.getIendserial());
									iomware.setIamount(iomware.getIamount()+detail.getIamount()+come.getIamount());

									this.update(come);

									sp=true;
									break;
								}

							}
							if(sp){
								this.delete(iomware);
							}else{
								iomware.setIendserial(detail.getIendserial());
								iomware.setIamount(iomware.getIamount()+detail.getIamount());

								this.update(iomware);
							}

							b=true;
							break;
						}else if(iomware.getIstartserial()-detail.getIendserial()==1){  //汇总表开始流水号与单据明细截止流水号作比较

							boolean sp=false;
							for(int h=y+1;h<houselist.size();h++){
								Iomwarehouseincome come2=(Iomwarehouseincome) houselist.get(h);
								if(come2.getIstartserial()-iomware.getIendserial()==1){
									come2.setIstartserial(detail.getIstartserial());
									come2.setIamount(come2.getIamount()+iomware.getIamount()+detail.getIamount());
									this.update(come2);

									sp=true;
									break;
								}else if(detail.getIstartserial()-come2.getIendserial()==1){
									come2.setIendserial(iomware.getIendserial());
									come2.setIamount(come2.getIamount()+iomware.getIamount()+detail.getIamount());
									this.update(come2);

									sp=true;
									break;
								}
							}
							if(sp){
								this.delete(iomware);
							}else{
								iomware.setIstartserial(detail.getIstartserial());
								iomware.setIamount(iomware.getIamount()+detail.getIamount());

								this.update(iomware);
							}

							b=true;
							break;
						}
					}

					if(!b){
						Iomwarehouseincome houseincome=new Iomwarehouseincome();
						Long maxid=this.getMaxPk("idetailsid", "Iomwarehouseincome")+1;
						houseincome.setIdetailsid(maxid);
						houseincome.setItickettypeid(detail.getItickettypeid());
						houseincome.setIstartserial(detail.getIstartserial());
						houseincome.setIendserial(detail.getIendserial());
						houseincome.setIamount(detail.getIamount());
						this.save(houseincome);
					}

				}else{

					Iomwarehouseincome houseincome=new Iomwarehouseincome();
					Long maxid=this.getMaxPk("idetailsid", "Iomwarehouseincome")+1;
					houseincome.setIdetailsid(maxid);
					houseincome.setItickettypeid(detail.getItickettypeid());
					houseincome.setIstartserial(detail.getIstartserial());
					houseincome.setIendserial(detail.getIendserial());
					houseincome.setIamount(detail.getIamount());
					this.save(houseincome);
				}


				//操作  仓库结存明细表
				Iomstocksbill bill=(Iomstocksbill) this.get(Iomstocksbill.class, detail.getSzstocksbillid());
				String msql=" from Iomstationticketdetails person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationinid();
				List perlist=this.find(msql);
				if(perlist!=null&&perlist.size()>0){

					boolean bs=false;

					for(int v=0;v<perlist.size();v++){
						Iomstationticketdetails station=(Iomstationticketdetails) perlist.get(v);
						//单据明细开始流水号与汇总表中的 截止流水号作比较
						if(detail.getIstartserial()-station.getIendserial()==1){
							boolean sz=false;
							for(int z=v+1;z<perlist.size();z++){
								Iomstationticketdetails tick=(Iomstationticketdetails) perlist.get(z);
								if(tick.getIstartserial()-detail.getIendserial()==1){
									tick.setSzstartticketcode(station.getSzstartticketcode());
									tick.setIstartserial(station.getIstartserial());
									tick.setIamount(tick.getIamount()+station.getIamount()+detail.getIamount());
									this.update(tick);

									sz=true;
									break;
								}else if(station.getIstartserial()-tick.getIendserial()==1){
									tick.setSzendticketcode(detail.getSzendticketcode());
									tick.setIendserial(detail.getIendserial());
									tick.setIamount(tick.getIamount()+station.getIamount()+detail.getIamount());
									this.update(tick);

									sz=true;
									break;
								}
							}
							if(sz){
								this.delete(station);
							}else{
								station.setSzendticketcode(detail.getSzendticketcode());
								station.setIendserial(detail.getIendserial());
								station.setIamount(station.getIamount()+detail.getIamount());

								this.update(station);
							}

							bs=true;
							break;
						}else if(station.getIstartserial()-detail.getIendserial()==1){

							boolean st=false;
							for(int n=v+1;n<perlist.size();n++){
								Iomstationticketdetails tick2=(Iomstationticketdetails) perlist.get(n);
								if(tick2.getIstartserial()-station.getIendserial()==1){
									tick2.setSzstartticketcode(detail.getSzstartticketcode());
									tick2.setIstartserial(detail.getIstartserial());
									tick2.setIamount(tick2.getIamount()+station.getIamount()+detail.getIamount());
									this.update(tick2);

									st=true;
									break;
								}else if(detail.getIstartserial()-tick2.getIendserial()==1){
									tick2.setSzendticketcode(station.getSzendticketcode());
									tick2.setIendserial(station.getIendserial());
									tick2.setIamount(station.getIamount()+detail.getIamount()+tick2.getIamount());

									this.update(tick2);

									st=true;
									break;
								}
							}
							if(st){
								this.delete(station);
							}else{
								station.setSzstartticketcode(detail.getSzstartticketcode());
								station.setIstartserial(detail.getIstartserial());
								station.setIamount(station.getIamount()+detail.getIamount());

								this.update(station);
							}

							bs=true;
							break;
						}
					}

					if(!bs){
						Iomstationticketdetails personal=new Iomstationticketdetails();
						Long msnids=this.getMaxPk("idetailsid", "Iomstationticketdetails")+1;
						personal.setIdetailsid(msnids);
						personal.setItickettypeid(detail.getItickettypeid());
						personal.setIwarehouseid(bill.getIstationinid());
						personal.setSzstartticketcode(detail.getSzstartticketcode());
						personal.setSzendticketcode(detail.getSzendticketcode());
						personal.setIstartserial(detail.getIstartserial());
						personal.setIendserial(detail.getIendserial());
						personal.setIamount(detail.getIamount());
						this.save(personal);
					}



				}else{
					Iomstationticketdetails personal=new Iomstationticketdetails();
					Long msnids=this.getMaxPk("idetailsid", "Iomstationticketdetails")+1;
					personal.setIdetailsid(msnids);
					personal.setItickettypeid(detail.getItickettypeid());
					personal.setIwarehouseid(bill.getIstationinid());
					personal.setSzstartticketcode(detail.getSzstartticketcode());
					personal.setSzendticketcode(detail.getSzendticketcode());
					personal.setIstartserial(detail.getIstartserial());
					personal.setIendserial(detail.getIendserial());
					personal.setIamount(detail.getIamount());
					this.save(personal);
				}

			}
		}

	}


	/**
	 * *
	 * Describe:初始入库时 判断添加相同产品时 输入票号是否重复
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#LookstartorendCode(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param szstartticketcode
	 * @param szendticketcode
	 * @param startcode
	 * @param endcode
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-19
	 */
	public boolean LookstartorendCode(String szstartticketcode,
									  String szendticketcode, String startcode, String endcode,
									  Long itickettypeid) {
		boolean b=false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		if(edmticket!=null){
			String sql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);

				String startticket=szstartticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String endticket=szendticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

				String startstock=startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String endstock=endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

				if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
					if(Long.parseLong(startticket)>Long.parseLong(endstock)||Long.parseLong(startstock)>Long.parseLong(endticket)){
						b=true;
					}else{
						b=false;
					}
				}else{
					if(Tools.Text36ToConvert(startticket)>Tools.Text36ToConvert(endstock)||Tools.Text36ToConvert(startstock)>Tools.Text36ToConvert(endticket)){
						b=true;
					}else{
						b=false;
					}
				}


			}
		}

		return b;
	}

	/**
	 *
	 * Describe:判断票号是否存在
	 * @auth:lijingrui
	 * @param stocks
	 * @return
	 * return:boolean
	 * Date:2012-10-16
	 */
	public boolean retrieWarehouse(Iomstocksbilldetails stocks){
		boolean b=false;
		if(stocks!=null){
			Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, stocks.getItickettypeid());
			String hsql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
			String istartserial=stocks.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			String iendserial=stocks.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

			if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
				stocks.setIstartserial(Long.parseLong(istartserial));
				stocks.setIendserial(Long.parseLong(iendserial));
			}else{
				stocks.setIstartserial(Tools.Text36ToConvert(istartserial));
				stocks.setIendserial(Tools.Text36ToConvert(iendserial));
			}

			String sql=" from Iomwarehouseincome detail where detail.itickettypeid="+stocks.getItickettypeid()+"  and ( "+
					"((detail.istartserial<="+stocks.getIstartserial()+" and detail.iendserial>="+stocks.getIstartserial()+") or (detail.istartserial<="+stocks.getIendserial()+" and detail.iendserial>="+stocks.getIendserial()+") ) or" +
					"(detail.istartserial>"+stocks.getIstartserial()+" and detail.iendserial<"+stocks.getIendserial()+") )";

			List list=this.find(sql);
			if(list!=null&&list.size()>=1){
				b=true;
			}else{
				b=false;
			}
			return b;
		}else{
			return b;
		}

	}
}

