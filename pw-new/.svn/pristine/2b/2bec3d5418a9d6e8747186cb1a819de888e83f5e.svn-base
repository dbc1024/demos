package com.ectrip.ticket.warehouse.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Iomwarehouse;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.warehouse.Kcpersonalticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstationticketdetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilldetailstab;
import com.ectrip.ticket.model.warehouse.Kcstocksbilltab;
import com.ectrip.ticket.model.warehouse.Kcwarehouseincometab;
import com.ectrip.ticket.warehouse.dao.idao.IKcpersonHouseDAO;

public class KcpersonHouseDAO extends GenericDao implements IKcpersonHouseDAO{

	/**
	 * *
	 * Describe:显示出所有的票类信息
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#showAlledmticket(java.lang.String)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-3
	 */
	public List showAlledmticket(Long iwarehouseid) {
		Iomwarehouse house=(Iomwarehouse) this.get(Iomwarehouse.class, iwarehouseid);
		//介质类型bymediatype-- CKFS  -00一维条码  01 二维条码
		StringBuffer sql=new StringBuffer();
		sql.append(" from Edmtickettypetab where byisuse=1 and bymediatype not in ('00','01') ");
		if(house.getIscenicid()!=null&&!"".equals(house.getIscenicid())&&!"0".equals(house.getIscenicid())&&house.getIscenicid()!=0){
			sql.append(" and iscenicid="+house.getIscenicid());
		}
		List list = this.find(sql.toString());

		return list;
	}

	/**
	 * *
	 * Describe:保存 IC卡初始入库
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#insertKcstock(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param stocks
	 * @param datailList
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-4
	 */
	public void insertKcstock(Kcstocksbilltab stocks,
							  List<Kcstocksbilldetailstab> datailList, Syslog syslog) {
		String msql="select szstocksbillid from Kcstocksbilltab order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		stocks.setBybillstate(1L);  //0 禁用  1 启用
		stocks.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		stocks.setBystockswayid(1L);  //操作方式
		String sql="select max(im.szstocksbillcode) from Kcstocksbilltab im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
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

		List<Kcstocksbilldetailstab> stationList=new ArrayList<Kcstocksbilldetailstab>();
		if(datailList!=null&&datailList.size()>0){
			for(int i=0;i<datailList.size();i++){
				Kcstocksbilldetailstab billdeta=datailList.get(i);
				billdeta.setSzstocksbillid(stocks.getSzstocksbillid());  //单据信息

				String tsql="select szstocksbilldetailsid from Kcstocksbilldetailstab order by to_number(szstocksbilldetailsid) desc";
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
		syslog.setStlg("0211");
		syslog.setBrief("初始入库：" + house.getSzwarehousename());
		syslog.setNote("初始入库：" +house.getSzwarehousename()+" 制单日期"+stocks.getDtmakedate() );

		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

	}

	/**
	 *
	 * Describe:合并数量
	 * @auth:lijingrui
	 * @param datailList
	 * return:void
	 * Date:2012-9-4
	 */
	public void addHousedetail(List<Kcstocksbilldetailstab> datailList){
		if(datailList!=null&&datailList.size()>0){
			for(int x=0;x<datailList.size();x++){
				Kcstocksbilldetailstab detail=datailList.get(x);
				//操作初始入库汇总表
				String hsql=" from Kcwarehouseincometab ware where ware.itickettypeid="+detail.getItickettypeid();
				List houselist=this.findTopNumb(hsql, 1);
				//获取该产品下的数据  如果没有 则新增   如果有  则判断数据是否有联系 合并
				if(houselist!=null&&houselist.size()>0){
					Kcwarehouseincometab iomware=(Kcwarehouseincometab) houselist.get(0);
					iomware.setIamount(iomware.getIamount()+detail.getIamount());
					this.update(iomware);
				}else{
					Kcwarehouseincometab houseincome=new Kcwarehouseincometab();
					Long maxid=this.getMaxPk("idetailsid", "Kcwarehouseincometab")+1;
					houseincome.setIdetailsid(maxid);
					houseincome.setItickettypeid(detail.getItickettypeid());
					houseincome.setIamount(detail.getIamount());
					this.save(houseincome);
				}


				//操作  仓库结存明细表
				Kcstocksbilltab bill=(Kcstocksbilltab) this.get(Kcstocksbilltab.class, detail.getSzstocksbillid());
				String msql=" from Kcstationticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationinid();
				List perlist=this.findTopNumb(msql, 1);
				if(perlist!=null&&perlist.size()>0){
					Kcstationticketdetailstab station=(Kcstationticketdetailstab) perlist.get(0);
					station.setIamount(station.getIamount()+detail.getIamount());
					this.update(station);

				}else{
					Kcstationticketdetailstab personal=new Kcstationticketdetailstab();
					Long msnids=this.getMaxPk("idetailsid", "Kcstationticketdetailstab")+1;
					personal.setIdetailsid(msnids);
					personal.setItickettypeid(detail.getItickettypeid());
					personal.setIwarehouseid(bill.getIstationinid());
					personal.setIamount(detail.getIamount());
					this.save(personal);
				}

			}
		}

	}

	/**
	 * *
	 * Describe:根据仓库ID查找仓库有的票类型
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#getListTickByihouse(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-4
	 */
	public List getListTickByihouse(Long iwarehouseid){
		StringBuffer hql=new StringBuffer();
		//查询仓库中有的单据类型的hql
		hql.append("select distinct new map(ioms.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename) " +
				"from Kcstationticketdetailstab ioms ,Edmtickettypetab edm where ioms.iwarehouseid="+iwarehouseid
				+" and edm.itickettypeid=ioms.itickettypeid");
		List list_Type = this.find(hql.toString());
		return list_Type;
	}

	/**
	 * *
	 * Describe:根据仓库ID、产品ID显示仓库结存明细信息
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#findAllStationticket(long, long)
	 * @param istationoutid
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-9-5
	 */
	public List findAllStationticket(long istationoutid, long itickettypeid) {
		String hsql = "from Kcstationticketdetailstab where itickettypeid="+itickettypeid+" and iwarehouseid="+istationoutid;
		List list = this.find(hsql);
		return list;
	}

	/**
	 * *
	 * Describe:领用出库保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcstation(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-5
	 */
	public void addKcstation(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		String msql="select szstocksbillid from Kcstocksbilltab order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		kcstockill.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		kcstockill.setBystockswayid(6L);  //操作方式
		String sql="select max(im.szstocksbillcode) from Kcstocksbilltab im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
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
		kcstockill.setSzstocksbillcode(stockcode.toString()); //单据编号
		kcstockill.setBybillstate(1L);
		this.save(kcstockill);		//保存单据信息

		List<Kcstocksbilldetailstab> stockList=new ArrayList<Kcstocksbilldetailstab>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Kcstationticketdetailstab stadeta = list.get(i);
				Kcstocksbilldetailstab billdeta = new Kcstocksbilldetailstab();
				billdeta.setSzstocksbillid(kcstockill.getSzstocksbillid());  //单据信息
				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setIamount(stadeta.getIamount());
				String tsql="select szstocksbilldetailsid from Kcstocksbilldetailstab order by to_number(szstocksbilldetailsid) desc";
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


			if(stockList!=null&&stockList.size()>0){
				for(int x=0;x<stockList.size();x++){
					Kcstocksbilldetailstab detail=stockList.get(x);

					//操作  仓库结存明细表
					Kcstocksbilltab bill=(Kcstocksbilltab) this.get(Kcstocksbilltab.class, detail.getSzstocksbillid());
					String hsql=" from Kcstationticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationoutid();
					List perlist=this.findTopNumb(hsql, 1);
					if(perlist!=null&&perlist.size()>0){
						Kcstationticketdetailstab station=(Kcstationticketdetailstab) perlist.get(0);
						if(station.getIamount()>detail.getIamount()){
							station.setIamount(station.getIamount()-detail.getIamount());
							this.update(station);

						}else if(station.getIamount().intValue()==detail.getIamount().intValue()){
							this.delete(station);
						}
					}

					//个人明细表票段整理
					String psql = "from Kcpersonalticketdetailstab per where per.itickettypeid="+detail.getItickettypeid()+" and per.ireceiverid="+bill.getIhandler();
					List kcList = this.find(psql);
					if(kcList!=null&&kcList.size()>0){
						Kcpersonalticketdetailstab person=(Kcpersonalticketdetailstab)kcList.get(0);
						person.setIamount(person.getIamount()+detail.getIamount());
						this.update(person);

					}else{
						Kcpersonalticketdetailstab personal=new Kcpersonalticketdetailstab();
						Long mdetid=this.getMaxPk("idetailsid", "Kcpersonalticketdetailstab");
						personal.setIdetailsid(mdetid+1L);
						personal.setIreceiverid(bill.getIhandler());
						personal.setItickettypeid(detail.getItickettypeid());
						personal.setIamount(detail.getIamount());
						this.save(personal);
					}

				}
			}


			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(kcstockill.getIstationoutid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, kcstockill.getIstationoutid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0215");
			syslog.setBrief("领用出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+kcstockill.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}

	}

	/**
	 * *
	 * Describe:仓库调拨 保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcDbchang(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-7
	 */
	public void addKcDbchang(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		String msql="select szstocksbillid from Kcstocksbilltab order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		kcstockill.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		kcstockill.setBystockswayid(3L);  //操作方式
		String sql="select max(im.szstocksbillcode) from Kcstocksbilltab im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
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
		kcstockill.setSzstocksbillcode(stockcode.toString()); //单据编号
		kcstockill.setBybillstate(1L);
		this.save(kcstockill);		//保存单据信息

		List<Kcstocksbilldetailstab> stockList=new ArrayList<Kcstocksbilldetailstab>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Kcstationticketdetailstab stadeta = list.get(i);
				Kcstocksbilldetailstab billdeta = new Kcstocksbilldetailstab();
				billdeta.setSzstocksbillid(kcstockill.getSzstocksbillid());  //单据信息
				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setIamount(stadeta.getIamount());
				String tsql="select szstocksbilldetailsid from Kcstocksbilldetailstab order by to_number(szstocksbilldetailsid) desc";
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


			if(stockList!=null&&stockList.size()>0){
				for(int x=0;x<stockList.size();x++){
					Kcstocksbilldetailstab detail=stockList.get(x);

					//操作  仓库结存明细表
					Kcstocksbilltab bill=(Kcstocksbilltab) this.get(Kcstocksbilltab.class, detail.getSzstocksbillid());
					String hsql=" from Kcstationticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationoutid();
					List perlist=this.findTopNumb(hsql, 1);
					if(perlist!=null&&perlist.size()>0){
						Kcstationticketdetailstab station=(Kcstationticketdetailstab) perlist.get(0);
						if(station.getIamount()>detail.getIamount()){
							station.setIamount(station.getIamount()-detail.getIamount());
							this.update(station);

						}else if(station.getIamount().intValue()==detail.getIamount().intValue()){
							this.delete(station);
						}
					}

					String bsql=" from Kcstationticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationinid();
					List personlist=this.findTopNumb(bsql, 1);
					if(personlist!=null&&personlist.size()>0){
						Kcstationticketdetailstab station=(Kcstationticketdetailstab) personlist.get(0);
						station.setIamount(station.getIamount()+detail.getIamount());
						this.update(station);
					}else{
						Kcstationticketdetailstab detailstab=new Kcstationticketdetailstab();
						Long maxidsts=this.getMaxPk("idetailsid", "Kcstationticketdetailstab");
						detailstab.setIdetailsid(maxidsts+1L);
						detailstab.setItickettypeid(detail.getItickettypeid());
						detailstab.setIwarehouseid(bill.getIstationinid());
						detailstab.setIamount(detail.getIamount());

						this.save(detailstab);
					}
				}
			}


			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(kcstockill.getIstationoutid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, kcstockill.getIstationoutid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0214");
			syslog.setBrief("出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+kcstockill.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}


	}



	/**
	 * *
	 * Describe:作废出库 保存
	 * @see com.ectrip.system.warehouse.service.iservice.IKcpersonHouseService#addKcDbchang(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockill
	 * @param list
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-9-7
	 */
	public void addKczfcksonls(Kcstocksbilltab kcstockill,List<Kcstationticketdetailstab> list,Syslog syslog){
		String msql="select szstocksbillid from Kcstocksbilltab order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		kcstockill.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		kcstockill.setBystockswayid(4L);  //操作方式
		String sql="select max(im.szstocksbillcode) from Kcstocksbilltab im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
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
		kcstockill.setSzstocksbillcode(stockcode.toString()); //单据编号
		kcstockill.setBybillstate(1L);
		this.save(kcstockill);		//保存单据信息

		List<Kcstocksbilldetailstab> stockList=new ArrayList<Kcstocksbilldetailstab>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Kcstationticketdetailstab stadeta = list.get(i);
				Kcstocksbilldetailstab billdeta = new Kcstocksbilldetailstab();
				billdeta.setSzstocksbillid(kcstockill.getSzstocksbillid());  //单据信息
				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setIamount(stadeta.getIamount());
				String tsql="select szstocksbilldetailsid from Kcstocksbilldetailstab order by to_number(szstocksbilldetailsid) desc";
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


			if(stockList!=null&&stockList.size()>0){
				for(int x=0;x<stockList.size();x++){
					Kcstocksbilldetailstab detail=stockList.get(x);

					//操作  仓库结存明细表
					Kcstocksbilltab bill=(Kcstocksbilltab) this.get(Kcstocksbilltab.class, detail.getSzstocksbillid());
					String hsql=" from Kcstationticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.iwarehouseid="+bill.getIstationoutid();
					System.out.println(hsql);
					List perlist=this.findTopNumb(hsql, 1);
					if(perlist!=null&&perlist.size()>0){
						Kcstationticketdetailstab station=(Kcstationticketdetailstab) perlist.get(0);
						if(station.getIamount()>detail.getIamount()){
							station.setIamount(station.getIamount()-detail.getIamount());
							this.update(station);

						}else if(station.getIamount().intValue()==detail.getIamount().intValue()){
							this.delete(station);
						}
					}

					String bsql = " from Kcwarehouseincometab ware where ware.itickettypeid="+detail.getItickettypeid();
					List personlist=this.findTopNumb(bsql, 1);
					if(personlist!=null&&personlist.size()>0){
						Kcwarehouseincometab kcware=(Kcwarehouseincometab) personlist.get(0);
						if(kcware.getIamount()>detail.getIamount()){
							kcware.setIamount(kcware.getIamount()-detail.getIamount());
							this.update(kcware);

						}else if(kcware.getIamount().intValue()==detail.getIamount().intValue()){
							this.delete(kcware);
						}

					}
				}
			}


			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(kcstockill.getIstationoutid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, kcstockill.getIstationoutid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0214");
			syslog.setBrief("出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+kcstockill.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}


	}


	/**
	 * Describe:余票入库  根据个人明细ID查找个人明细所有的票类型
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#getListTickByperson(java.lang.Long)
	 * @param ihandler
	 * @return
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public List getListTickByperson(Long ihandler){
		StringBuffer hql=new StringBuffer();
		//查询仓库中有的单据类型的hql
		hql.append("select distinct new map(ioms.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename) " +
				"from Kcpersonalticketdetailstab ioms ,Edmtickettypetab edm where ioms.ireceiverid="+ihandler
				+" and edm.itickettypeid=ioms.itickettypeid");
		List list_Type = this.find(hql.toString());
		return list_Type;
	}

	/**
	 * Describe:余票入库  根据个人结存明细ID、产品ID显示个人结存明细信息
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#findAllPersonalticket(java.lang.Long, java.lang.Long)
	 * @param ihandler
	 * @param itickettypeid
	 * @return
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public List findAllPersonalticket(Long ihandler, Long itickettypeid){
		Edmtickettypetab e = (Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String hsql = "from Kcpersonalticketdetailstab where itickettypeid="+itickettypeid+" and ireceiverid="+ihandler;
		List list=null;
		try {
			list = this.find(hsql);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	}

	/**
	 * Describe:余票入库  保存
	 * @see com.ectrip.system.warehouse.dao.idao.IKcpersonHouseDAO#addKcpersonal(com.ectrip.model.warehouse.Kcstocksbilltab, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param kcstockbill
	 * @param perlist
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-9-10
	 */
	public void addKcpersonal(Kcstocksbilltab kcstockbill,
							  List<Kcpersonalticketdetailstab> perlist, Syslog syslog){
		String msql="select szstocksbillid from Kcstocksbilltab order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		kcstockbill.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		kcstockbill.setBystockswayid(5L);  //操作方式
		String sql="select max(im.szstocksbillcode) from Kcstocksbilltab im where im.szstocksbillcode like '%"+Tools.getDay()+"%' ";
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
		kcstockbill.setSzstocksbillcode(stockcode.toString()); //单据编号
		kcstockbill.setBybillstate(1L);
		this.save(kcstockbill);		//保存单据信息

		List<Kcstocksbilldetailstab> stockList=new ArrayList<Kcstocksbilldetailstab>();
		if(perlist!=null&&perlist.size()>0){
			for(int i=0;i<perlist.size();i++){
				Kcpersonalticketdetailstab stadeta = perlist.get(i);
				Kcstocksbilldetailstab billdeta = new Kcstocksbilldetailstab();
				billdeta.setSzstocksbillid(kcstockbill.getSzstocksbillid());  //单据信息
				billdeta.setItickettypeid(stadeta.getItickettypeid());
				billdeta.setIamount(stadeta.getIamount());
				String tsql="select szstocksbilldetailsid from Kcstocksbilldetailstab order by to_number(szstocksbilldetailsid) desc";
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


			if(stockList!=null&&stockList.size()>0){
				for(int x=0;x<stockList.size();x++){
					Kcstocksbilldetailstab detail=stockList.get(x);

					//操作  仓库结存明细表
					Kcstocksbilltab bill=(Kcstocksbilltab) this.get(Kcstocksbilltab.class, detail.getSzstocksbillid());
					String hsql=" from Kcpersonalticketdetailstab person where person.itickettypeid="+detail.getItickettypeid()+" and person.ireceiverid="+bill.getIhandler();
					List personlist=this.findTopNumb(hsql, 1);
					if(personlist!=null&&personlist.size()>0){
						Kcpersonalticketdetailstab station=(Kcpersonalticketdetailstab) personlist.get(0);
						if(station.getIamount()>detail.getIamount()){
							station.setIamount(station.getIamount()-detail.getIamount());
							this.update(station);
						}else if(station.getIamount().intValue()==detail.getIamount().intValue()){
							this.delete(station);
						}
					}

					//个人明细表票段整理
					String psql = "from Kcstationticketdetailstab per where per.itickettypeid="+detail.getItickettypeid()+" and per.iwarehouseid="+bill.getIstationinid();
					List kcList = this.find(psql);
					if(kcList!=null&&kcList.size()>0){
						Kcstationticketdetailstab person=(Kcstationticketdetailstab)kcList.get(0);
						person.setIamount(person.getIamount()+detail.getIamount());
						this.update(person);

					}else{
						Kcstationticketdetailstab personal=new Kcstationticketdetailstab();
						Long mdetid=this.getMaxPk("idetailsid", "Kcstationticketdetailstab");
						personal.setIdetailsid(mdetid+1L);
						personal.setIwarehouseid(bill.getIstationinid());
						personal.setItickettypeid(detail.getItickettypeid());
						personal.setIamount(detail.getIamount());
						this.save(personal);
					}

				}
			}


			//日志
			Iomwarehouse ware=new Iomwarehouse();
			if(kcstockbill.getIstationinid()!=null){
				ware=(Iomwarehouse) this.get(Iomwarehouse.class, kcstockbill.getIstationinid());
			}
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setStlg("0214");
			syslog.setBrief("余票入库：" + ware.getSzwarehousename());
			syslog.setNote("余票入库：" +ware.getSzwarehousename()+" 制单日期"+kcstockbill.getDtmakedate() );
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}
	}
}
