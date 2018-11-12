package com.ectrip.ticket.sale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.sys.model.syspar.Hicopertionlog;
import com.ectrip.sys.model.syspar.Icopertionlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.model.syspar.Webinfotab;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.StscomticketsalesdetailstabId;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.StssalessettlementtabId;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvoucherdetailstabId;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;
import com.ectrip.ticket.model.order.Stsschecktab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.order.Ticketprintlist;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.provider.service.ITicketTypeService;
import com.ectrip.ticket.sale.service.iservice.ISaleCheckupService;
@Service("saleCheckupService")
public class SaleCheckupService implements ISaleCheckupService{

	private ITicketTypeService ticketTypeService;//产品service
	@Autowired
	public void setTicketTypeService(ITicketTypeService ticketTypeService) {
		this.ticketTypeService = ticketTypeService;
	}
	@Autowired
	private ICheckDao checkDao;
	
	/**
	 * *
	 * Describe:显示圆门和检票次数
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getGateTicketCount(java.lang.Long, java.lang.Long)
	 * @param iscenicid  服务商ID
	 * @param iticktypeid 产品ID
	 * @param tickettypeid  子产品ID
	 * @return
	 * @author lijingrui
	 * Date:2012-9-5
	 */
	public ResultBean getGateTicketCount(Long iscenicid,Long iticktypeid, Long tickettypeid) {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		String sql="select esb.bygardengateindex as bygardengateindex,op.ilimittotaltimes as ilimittotaltimes,op.byregfingerprinttype as byregfingerprinttype from Opwwicketsettab op,Esbgardengatetab esb where op.igardengateid=esb.id.igardengateid and op.iscentcid="+iscenicid+" and op.itickettypeid="+iticktypeid+" and op.izticktypeid="+tickettypeid;
		List lst=this.ticketTypeService.find(sql);
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Object [] obj=(Object[]) lst.get(i);
				String gardt=obj[0].toString();
				String limit=obj[1].toString();
				String byreg=obj[2].toString();
				StringBuffer buf=new StringBuffer();
				buf.append(limit);
				buf.append("_"+byreg);
				rs.addRow(new String[] { gardt, buf.toString()});
			}
		}else{
			rs.addRow(new String[] { "false", "此票未设置检票设置信息！！" });
		}
		return rs;
	}
	/**
	 * *
	 * Describe:增加写IC卡日志的类
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#IcSaleWriteLog(java.lang.String, int, java.lang.String, java.lang.String)
	 * @param szTicketPrintNo	票号
	 * @param Optype	1--售票，2--检票
	 * @param EquipmentID	机器编号
	 * @param ICBLOCK	块编号
	 * @param ICContent	内容
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-6
	 */
	public int IcWriteLog(String szTicketPrintNo, int Optype ,int EquipmentID, String ICBLOCK, String ICContent){
		try {
			long seq = this.ticketTypeService.getSequenceId("iccard_sequence");
			Icopertionlog iclog = new Icopertionlog();
			iclog.setSeq(seq);
			iclog.setSzticketprintno(szTicketPrintNo);
			iclog.setEquipmentid(new Long(EquipmentID));
			iclog.setIcblock(ICBLOCK);
			iclog.setOptype(new Long(Optype));
			iclog.setIccontent(ICContent);
			iclog.setDtdatemake(Tools.getDayTimes());
			this.ticketTypeService.save(iclog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	/**
	 * *
	 * Describe:读取写入IC卡的内容
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#GetICData(java.lang.String)
	 * @param szTicketPrintNo	卡号
	 * @param type	1--当天日志	2--历史日志
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-7
	 */
	public ResultBean GetICData(String szTicketPrintNo,int type) {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		if(type==1){
			List list = this.ticketTypeService.find(" from Icopertionlog ic where ic.szticketprintno = '"+szTicketPrintNo+"' order by ic.dtdatemake desc");
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Icopertionlog ic = (Icopertionlog) list.get(i);
					rs.addRow(new String[] { "szticketprintno", ic.getSzticketprintno() });
					rs.addRow(new String[] { "optype", ic.getOptype().toString() });
					rs.addRow(new String[] { "equipmentid", ic.getEquipmentid().toString() });
					rs.addRow(new String[] { "icblock", ic.getIcblock() });
					rs.addRow(new String[] { "iccontent", ic.getIccontent()});
					rs.addRow(new String[] { "dtdatemake", ic.getDtdatemake() });
				}
			}else{
				rs.addRow(new String[] { "false", "没有找到票务数据或已全部退订" });
			}
		}
		if(type==2){
			List list = this.ticketTypeService.find( " from Hicopertionlog hic where hic.szticketprintno = '"+szTicketPrintNo+"' order by hic.dtdatemake desc");
			if(list!=null&&list.size()>0){
				for(int i = 0;i<list.size();i++){
					Hicopertionlog hic =  (Hicopertionlog) list.get(0);
					rs.addRow(new String[] { "szticketprintno", hic.getSzticketprintno() });
					rs.addRow(new String[] { "optype", hic.getOptype().toString() });
					rs.addRow(new String[] { "equipmentid", hic.getEquipmentid().toString() });
					rs.addRow(new String[] { "icblock", hic.getIcblock() });
					rs.addRow(new String[] { "iccontent", hic.getIccontent()});
					rs.addRow(new String[] { "dtdatemake", hic.getDtdatemake() });
				}
			}else{
				rs.addRow(new String[] { "false", "没有找到票务数据或已全部退订" });
			}
		}
		return rs;
	}

	/**
	 * *
	 * Describe:对IC卡票 修改售出门票表中的票号 并激活
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getVaildcheckCodeup(java.lang.Long, java.lang.String, java.lang.String)
	 * @param iscenicid   服务商
	 * @param oldszticketno 旧票号
	 * @param newszticketno IC卡号
	 * @return
	 * @author lijingrui
	 * Date:2012-9-13
	 */
	public boolean getVaildcheckCodeup(Long iscenicid,String oldszticketno,String newszticketno,Long iscz){

		String sql=" from Stssoldtickettab stss where stss.iscenicid="+iscenicid+"  and stss.szticketprintno='"+oldszticketno+"' order by stss.id.isalesvoucherid desc ";
		List list=this.ticketTypeService.find(sql);
		if(list!=null&&list.size()>0){
			Stssoldtickettab stssold=(Stssoldtickettab) list.get(0);
			if(iscz.longValue()==0){
				if(stssold.getManyouno()!=null&&!stssold.getManyouno().equals("")){
					stssold.setManyouno(newszticketno);
				}
			}
			stssold.setManyouno(newszticketno);
			this.ticketTypeService.update(stssold);
			String msql=" from Stssoldticketsubtab stss where stss.id.szsoldticketid="+stssold.getId().getSzsoldticketid()+" and stss.id.isalesvoucherid="+stssold.getId().getIsalesvoucherid()+" and stss.iscenicid="+stssold.getIscenicid()+" and stss.itickettypeid="+stssold.getItickettypeid();
			List mList=this.ticketTypeService.find(msql);
			if(mList!=null&&mList.size()>0){
				for(int i=0;i<mList.size();i++){
					Stssoldticketsubtab stssoldsub=(Stssoldticketsubtab)mList.get(i);
					if(iscz.longValue()==0){
						stssoldsub.setIsvalid(new Long(1));
					}
					this.ticketTypeService.update(stssoldsub);
				}
				return true; //正确
			}else{
				return false;
			}

		}else{
			return false;
		}

	}

	/**
	 *
	 * Describe:酒店订单确认打印
	 * @auth:lijingrui
	 * @param orid
	 * @param iscenicid
	 * @param iemployeeid
	 * @param iticketwinid
	 * @param maxid
	 * @return
	 * @throws Exception
	 * return:ResultBean
	 * Date:2012-11-23
	 */
	public ResultBean savehoteltorder(String orid,Long iscenicid, Long iemployeeid, Long iticketwinid, Long maxid,String param)throws Exception{
		System.out.println("s1");
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		String message = "";
		MOrder m = (MOrder) ticketTypeService.get(MOrder.class, orid);
		TOrder t_order = (TOrder) ticketTypeService.get(TOrder.class, new TOrderId(orid,
				iscenicid));
		if (t_order == null) {
			message = "订单不存在";
			rs.addRow(new String[] { "false", message });
			return rs;
		} else {
			if (t_order.getDdzt().equals("11")) {
				message = "订单已出票，无需再次出票";
				rs.addRow(new String[] { "false", message });
				return rs;
			} else {
				if (t_order.getIbusinessid().longValue() != 3) {
					if(!m.getDdzt().equals("00")&&!m.getZffs().equals("05")){
						if (!t_order.getDdzt().equals("02")) {
							message = "订单未付款";
							rs.addRow(new String[] { "false", message });
							return rs;
						}
					}
				}
			}
		}
		List listorder = ticketTypeService.find(" from TOrderlist t where t.id.orid='"
				+ orid + "' and t.id.iscenicid=" + iscenicid
				+ " and t.numb>0 order by orderlistid");
		if (listorder == null || listorder.size() == 0) {
			message = "订单已全部退订";
			rs.addRow(new String[] { "false", message });
			return rs;
		}
		Stssalesvouchertab s = savetStssalesvouchertab(t_order, iemployeeid,
				maxid, iticketwinid,param);
		Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
				Esbscenicareatab.class, new Long(t_order.getId().getIscenicid()));

		// ticketTypeService.save(s);
		Stssalessettlementtab st = this.savetStssalessettlementtab(s, t_order,param);
		YOrder yorder = (YOrder) ticketTypeService.get(YOrder.class, new YOrderId(orid,
				iscenicid));
		t_order.setDdzt("11");
		t_order.setNotec(Tools.getNowString());
		t_order.setIsc(iemployeeid);
		ticketTypeService.update(t_order);
		yorder.setDdzt("11");
		yorder.setNotec(Tools.getNowString());
		yorder.setIsc(iemployeeid);
		ticketTypeService.update(yorder);
		List listtorder = ticketTypeService.find("from TOrder where id.orid='" + orid
				+ "'");
		boolean b1 = true;
		for (int i = 0; i < listtorder.size(); i++) {
			TOrder pt = (TOrder) listtorder.get(i);
			if (pt.getDdzt().equals("02")) {
				b1 = false;
			}
		}

		if (b1) {
			// 该订单已经全部出票

			if (m.getNotea() != null && m.getNotea().equals("50")) {
				m.setNotea("51");
			}
			if (m.getNotea() != null && m.getNotea().equals("02")) {
				m.setNotea("11");
			}
			m.setDdzt("11");
			ticketTypeService.update(m);
		}


		List listzorder = ticketTypeService.find(" from TZorderlist t where t.id.orid='"
				+ orid + "' and t.id.iscenicid=" + iscenicid
				+ " and t.znumb>0 order by orderlistid");

		Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(Esbticketwintab.class,
				s.getIticketwinid());
		Esbticketstationtab esbticketstation = (Esbticketstationtab) ticketTypeService
				.get(Esbticketstationtab.class, e.getIticketstationid());
		System.out.println("s2");
		for (int i = 0; i < listzorder.size(); i++) {
			TZorderlist zlist = (TZorderlist) listzorder.get(i);
			Edmtickettypetab edticket = (Edmtickettypetab) ticketTypeService.get(
					Edmtickettypetab.class, new Long(zlist.getItickettypeid()));
			if (Tools.getDayTimes().compareTo(zlist.getDtenddate()) > 0) {
				if (zlist.getTripid() > 0) {
					Trip trip = (Trip) ticketTypeService.get(Trip.class, zlist
							.getTripid());
					rs.addRow(new String[] {
							"false",
							"产品" + edticket.getSztickettypename() + "的竹筏的"
									+ trip.getTripname() + "已结束" });

				} else {
					rs.addRow(new String[] { "false",
							"产品号" + edticket.getSztickettypename() + "已过期" });
				}
				return rs;
			}

		}
		System.out.println("s3");
		// ticketTypeService.save(st);
		List detaillist = new ArrayList();
		List cdetaillist = new ArrayList();
		List zdetaillist = new ArrayList();
		Long szsoldticketid = new Long(1);

		for (int i = 0; i < listorder.size(); i++) {
			TOrderlist tlist = (TOrderlist) listorder.get(i);
			// 售出凭证明细
			Stssalesvoucherdetailstab sd = this.savetStssalesvoucherdetailstab(s,
					tlist);
			Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
					Edmtickettypetab.class, new Long(tlist.getItickettypeid()));

			if (eticket.getByusage() == 0) {
				sd.setIticketnum(new Long(tlist.getNumb()));
				sd.setIticketplayer(new Long(tlist.getNumb()));
			} else {
				sd.setIplayerperticket(new Long(tlist.getNumb()));
				sd.setIticketplayer(new Long(tlist.getNumb()));
			}
			detaillist.add(sd);
			// 售出凭证子明细
			// ticketTypeService.save(sd);
			// 添加售出门票表
			zdetaillist = this.SavetStscomticketsalesdetailstab(zdetaillist, s,
					tlist, listzorder);

			// 现场打 印
			cdetaillist = this.saveStssoldtickettabug0make00(cdetaillist, sd, s,
					esbticketstation.getSzstationcode(), scenic.getSzsceniccode(),
					eticket.getSztickettypecode(), new Long(tlist.getIcrowdkindid()));
			System.out.println("33333");

		}
		System.out.println("s5");
		ticketTypeService.save(s);
		ticketTypeService.save(st);
		for (int i = 0; i < detaillist.size(); i++) {
			Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
					.get(i);
			ticketTypeService.save(sd);
		}
		Sysparv5 sv5 = (Sysparv5) ticketTypeService.get(Sysparv5.class, new Sysparv5Id(
				"PRCS", "01"));
		String printcs = "0";
		if (sv5 != null) {
			printcs = sv5.getPmva();
		}
		System.out.println("s6");
		for (int i = 0; i < cdetaillist.size(); i++) {
			Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist.get(i);
			ticketTypeService.save(stsv);
			if (stsv.getBymaketicketway().equals("00")) {
				if (printcs.equals("1")) {
					Ticketprintlist t = new Ticketprintlist();
					// ticketTypeService.save(stsv);
					t.setIemployeeid(iemployeeid);
					t.setSzsalesvoucherno(s.getSzsalesvoucherno());
					t.setPrinttype("01");
					t.setSzticketprintno(stsv.getSzticketprintno());
					t.setPrinttime(Tools.getDayTimes());
					t.setIsok(new Long(0));
					Long printid = ticketTypeService.getSequenceId("PRINT_ID");
					t.setPrintid(printid);
				}
			}
		}
		System.out.println("s7");
		for (int i = 0; i < zdetaillist.size(); i++) {
			Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
					.get(i);
			ticketTypeService.save(zstd);
		}
		System.out.println("s8");


		Orderlog log = new Orderlog();
		log.setOrid(orid);
		log.setIemployeeid(iemployeeid);
		log.setStlg("0186");
		log.setLogtype(new Long(2));
		log.setLogdatetime(Tools.getDayTimes());
		log.setNote("售票口出票");
		log.setBrief("售票口出票");
		Long id = ticketTypeService.getMaxPk("logid", "Orderlog");
		log.setLogid(id + 1);
		ticketTypeService.save(log);

		rs
				.addRow(new String[] { "true",
						s.getId().getIsalesvoucherid().toString() });
		return rs;



	}

	/**
	 * *
	 * Describe:服务商信息显示
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getEsbscenicidshowup(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:2012-11-23
	 */
	public Esbscenicareatab getEsbscenicidshowup(Long iscenicid){
		Esbscenicareatab esbscenic=(Esbscenicareatab) this.ticketTypeService.get(Esbscenicareatab.class, iscenicid);
		return esbscenic;
	}

	public Stssalesvouchertab savetStssalesvouchertab(TOrder tOrder,
													  Long iemployeeid, Long maxid, Long iticketwinid,String param) {
		Stssalesvouchertab s = new Stssalesvouchertab();
		s.setIscenicid(new Long(tOrder.getId().getIscenicid()));
		s.setIticketwinid(iticketwinid);
		s.setIbusinessid(new Long(tOrder.getIbusinessid()));
		s.setIhandler(iemployeeid);
		s.setIpayeer(iemployeeid);
		s.setImaker(iemployeeid);
		s.setIauditor(iemployeeid);

		//2014-03-03 李经锐修改 网上现场支付
		if(param!=null&&!param.equals("")){
			MOrder morder=(MOrder)this.ticketTypeService.get(MOrder.class, tOrder.getId().getOrid());
			if(morder.getDdzt()!=null&&morder.getDdzt().equals("00")&&morder.getZffs().equals("05")){
				String [] pn=param.split(",");
				s.setIaccountreceivable(new Double(pn[1].toString()));// 实收
				s.setIacceptmoney(new Double(pn[2].toString()));// 应收
				s.setIgivechange(new Double(pn[3].toString()));// 找零
				s.setOrnote10("1");  //现场支付
			}else{
				s.setIaccountreceivable(new Double(tOrder.getZfmont()));// 实收
				s.setIacceptmoney(new Double(tOrder.getZfmont()));// 应收
				s.setIgivechange(new Double(0));// 找零
			}
		}else{
			s.setIaccountreceivable(new Double(tOrder.getZfmont()));// 实收
			s.setIacceptmoney(new Double(tOrder.getZfmont()));// 应收
			s.setIgivechange(new Double(0));// 找零
		}

		s.setSzsalesvoucherno(tOrder.getId().getOrid());
		s.setIyear(new Long(Tools.getDays().substring(0, 4)));
		s.setImonth(new Long(Tools.getDays().substring(5, 7)));
		s.setIday(new Long(Tools.getDays().substring(8, 10)));
		s.setDtmakedate(Tools.getDayTimes());
		s.setDtauditdate(tOrder.getDtstartdate());
		s.setUsid(tOrder.getUsid());
		s.setBisintegral(new Long(0));
		s.setByprintinvoice(new Long(0));
		s.setBysplitway(new Long(2));
		s.setBisreturn(new Long(1));
		s.setBysalesvouchertype("01");
		s.setBypostrecord(new Long(0));
		s.setBysalesvoucherstate(new Long(1));
		s.setBispay(new Long(0));
		s.setBispayee(new Long(0));
		s.setSztravelbillno(tOrder.getSztravelbillno());
		s.setIregionalid(new Long(tOrder.getIregionalid()));
		if (tOrder.getDyusid() == null || tOrder.getDyusid().equals("")) {
			s.setDyusid("daoyou");
		} else {
			s.setDyusid(tOrder.getDyusid());
		}
		s.setTdlx(tOrder.getTdlx());
		Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(Esbticketwintab.class,
				s.getIticketwinid());
		StssalesvouchertabId id = new StssalesvouchertabId();
		id.setIticketstationid(e.getIticketstationid());
		id.setIsalesvoucherid(maxid);
		s.setId(id);
		// s.getId().setIsalesvoucherid(maxid);
		return s;
	}

	public Stssalessettlementtab savetStssalessettlementtab(Stssalesvouchertab s,
															TOrder t_order,String param) {
		System.out.println("savetStssalessettlementtab11111111111111111111111111");
		Stssalessettlementtab st = new Stssalessettlementtab();
		StssalessettlementtabId sid = new StssalessettlementtabId();
		sid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
		sid.setIticketstationid(s.getId().getIticketstationid());
		sid.setIsalessettlementid(new Long(1));
		st.setId(sid);
		st.setSettlementdata(Tools.getDays());
		st.setSettlementtime(Tools.getDayTimes().substring(11));
		st.setDtmakedate(Tools.getDayTimes());
		System.out.println("ddzt="+t_order.getDdzt());
		if (t_order.getDdzt().equals("02")) {
			st.setIsettlementid("01");
		} else {
			//2014-03-03 李经锐修改  网上订单现场支付
			System.out.println("param="+param);
			if(param!=null){
				MOrder morder=(MOrder)this.ticketTypeService.get(MOrder.class, t_order.getId().getOrid());
				if(morder.getDdzt()!=null&&morder.getDdzt().equals("00")&&morder.getZffs().equals("05")){
					String [] pn=param.split(",");
					st.setIsettlementid(pn[0].toString());
				}else{
					st.setIsettlementid("00");
				}
			}else{
				st.setIsettlementid("00");
			}
		}
		System.out.println("Isettlementid="+st.getIsettlementid());
		st.setMsettlementmoney(new Double(t_order.getZfmont()));
		st.setIversion(new Long(1));
		return st;
	}

	public Stssalesvoucherdetailstab savetStssalesvoucherdetailstab(
			Stssalesvouchertab s, TOrderlist tlist) {
		Stssalesvoucherdetailstab sd = new Stssalesvoucherdetailstab();
		StssalesvoucherdetailstabId sdid = new StssalesvoucherdetailstabId();
		sdid.setIsalesvoucherdetailsid(new Long(tlist.getId().getOrderlistid()));
		sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
		sdid.setIticketstationid(s.getId().getIticketstationid());
		sd.setId(sdid);
		sd.setIticketwinid(s.getIticketwinid());
		sd.setIcrowdkindpriceid(new Long(tlist.getIcrowdkindpriceid()));
		sd.setItickettypeid(new Long(tlist.getItickettypeid()));
		sd.setIplayerperticket(new Long(1));// 人/张
		sd.setIticketnum(new Long(1));// 张数
		sd.setIticketplayer(new Long(1));// 人次
		sd.setDtstartdate(tlist.getDtstartdate());
		sd.setDtenddate(tlist.getDtenddate());
		sd.setIstartid(new Long(0));
		sd.setIendid(new Long(0));
		sd.setSzstartserial("0");
		sd.setSzendserial("0");
		sd.setIoffersschemeid(new Long(0));
		sd.setIamount(new Long(tlist.getNumb()));
		sd.setIpresentnums(new Long(0));
		sd.setIderatenums(new Long(0));
		sd.setIfactnum(new Long(0));
		sd.setIuseablenessnum(new Long(tlist.getNumb()));// 使用数量
		sd.setMactualsaleprice(new Double(tlist.getPric()));// 实际售价
		sd.setMeventmoney(new Double(tlist.getAmnt()));// 实际发生金额
		sd.setMderatemoney(new Double(0));// 减免金额
		sd.setMpresentmoney(new Double(0));// 赠送金额
		sd.setMnominalfee(new Double(0));// 工本费
		sd.setMdeposit(new Double(0));
		sd.setMhandcharge(new Double(0));
		sd.setByconsumetype("00");
		sd.setIconsumenum(new Double(0));
		sd.setMtotalamount(new Double(tlist.getAmnt()));
		sd.setItotalnumber(new Long(tlist.getNumb()));
		sd.setItotalminutes(new Long(0));
		sd.setByisout(new Long(0));
		sd.setDtmakedate(Tools.getDayTimes());
		sd.setIversion(new Long(0));
		return sd;
	}

	public List SavetStscomticketsalesdetailstab(List zdetaillist,
												 Stssalesvouchertab s, TOrderlist tlist, List listzorder) {
		for (int i = 0; i < listzorder.size(); i++) {
			TZorderlist zlist = (TZorderlist) listzorder.get(i);
			if (zlist.getId().getOrderlistid().equals(tlist.getId().getOrderlistid())) {
				Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
				StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
				zstdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
				zstdid.setIticketstationid(s.getId().getIticketstationid());
				zstdid.setIsalesvoucherdetailsid(new Long(zlist.getId().getOrderlistid()));
				zstdid.setIcomticketsalesdetailsid(new Long(zlist.getId().getZorderlistid()));
				zstd.setIcrowdkindpriceid(new Long(zlist.getIcrowdkindpriceid()));
				zstd.setItickettypeid(new Long(zlist.getItickettypeid()));
				zstd.setIztickettypeid(new Long(zlist.getIztickettypeid()));
				if(zlist.getTripid()==null||zlist.getTripid().equals("")){
					zstd.setTripid(0L);
				}else{
					zstd.setTripid(new Long(zlist.getTripid()));
				}

				zstd.setMhandcharge(new Double(0));
				zstd.setDtmakedate(Tools.getDayTimes());
				zstd.setIvenueareaid(new Long(0));
				zstd.setIvenueid(new Long(0));
				zstd.setIvenueseatsid(new Long(0));
				if (zlist.getDtstartdate().length() > 10) {
					zstd.setDtstartdate(zlist.getDtstartdate());
					zstd.setDtenddate(zlist.getDtenddate());
				} else {
					zstd.setDtstartdate(zlist.getDtstartdate() + "00:00:00");
					zstd.setDtenddate(zlist.getDtenddate() + "23:59:59");
				}
				zstd.setIversion(new Long(0));
				zstd.setIsplitamount(new Long(zlist.getZnumb()));
				zstd.setMsplitprice(new Double(zlist.getZpric()));
				zstd.setMsplitmoney(new Double(zlist.getZamnt()));
				zstd.setId(zstdid);

				zdetaillist.add(zstd);
			}
		}
		return zdetaillist;
	}

	public List saveStssoldtickettabug0make00(List cdetaillist,
											  Stssalesvoucherdetailstab sd, Stssalesvouchertab s, String szstationcode,
											  String szsceniccode, String sztickettypecode, Long icrowdkindid) {
		for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
			Stssoldtickettab stsv = new Stssoldtickettab();
			StssoldtickettabId stsvid = new StssoldtickettabId();
			stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
			stsvid.setIticketstationid(sd.getId().getIticketstationid());
			stsvid.setIsalesvoucherdetailsid(sd.getId().getIsalesvoucherdetailsid());
			stsvid.setSzsoldticketid(new Long(j));
			stsv.setId(stsvid);
			stsv.setIscenicid(s.getIscenicid());
			stsv.setIcrowdkindid(icrowdkindid);
			stsv.setItickettypeid(sd.getItickettypeid());
			stsv.setUsid(s.getUsid());
			stsv.setIbusinessid(s.getIbusinessid());
			stsv.setDyusid(s.getDyusid());
			stsv.setIplayerperticket(new Long(1));// 人次数
			stsv.setDtstartdate(sd.getDtstartdate());
			stsv.setDtenddate(sd.getDtenddate());
			stsv.setMhandcharge(new Double(0));
			stsv.setByvalidity("00");
			stsv.setDtmakedate(s.getDtmakedate());
			stsv.setBymaketicketway("00");
			List<Map> iserialnumlist = new ArrayList();
			try {
				iserialnumlist = checkDao.findBySqlToMapnocolsesession("select ticketid_sequence.nextval  from dual");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Long iserialnum = new Long((((Map) iserialnumlist.get(0)).get("NEXTVAL"))
					.toString());
			stsv.setIserialnum(iserialnum);
			String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
			StringBuffer printno = new StringBuffer();
			printno.append(szstationcode);
			printno.append(szsceniccode);
			printno.append(sztickettypecode);
			if (newmaxorno.length() < 6) {
				for (int b = 0; b < 6 - newmaxorno.length(); b++) {
					printno.append("0");
				}
			}
			printno.append(newmaxorno);
			String szprintno = Tools.ticketMakeMd5(printno.toString());
			stsv.setSzticketprintno(szprintno);
			stsv.setMremainmoney(new Double(0));
			stsv.setMpresentmoney(new Double(0));
			stsv.setMactualsaleprice(sd.getMactualsaleprice());
			stsv.setIpresentnum(new Long(0));
			stsv.setIremainnum(new Long(1));// 剩余数量
			stsv.setMnominalfee(new Double(0));
			stsv.setMdeposit(new Double(0));
			stsv.setByticketpurpose("00");
			stsv.setBisrefundbalance(new Long(1));
			stsv.setByactivation("02");
			Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
					Esbticketwintab.class, s.getIticketwinid());
			System.out.println("----<<<<>>>>" + e.getBywintype());
			if (e.getBywintype().equals("0003")) {
				// 窗口为慢游卡窗口
				stsv.setManyouno(sd.getManyouno());
				stsv.setMyzj(sd.getMyzj());
				stsv.setZjno1(sd.getZjno1());
				stsv.setName1(sd.getName1());
			} else {
				if (sd.getMyzj() != null && !sd.getMyzj().equals("")) {
					stsv.setMyzj(sd.getMyzj());
					stsv.setName1(sd.getName1());
				}
			}
			cdetaillist.add(stsv);

		}
		return cdetaillist;
	}

	/**
	 * *
	 * Describe:查询出某人的站内短信公告
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#checkListwebinfo(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2013-1-23
	 */
	public ResultBean checkListwebinfo(Long iemployeeid){
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		String sql=" from Webinfotab where (ihadder=1 or ihadder="+iemployeeid+") and rownum<=2 order by reminddate desc";
		List list=this.ticketTypeService.find(sql);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Webinfotab webinfo = (Webinfotab) list.get(i);
				rs.addRow(new String[] { "iemployeeid", webinfo.getIemployeeid().toString() });
				rs.addRow(new String[] { "ihadder", webinfo.getIhadder().toString() });
				rs.addRow(new String[] { "szmemo", webinfo.getSzmemo() });
				rs.addRow(new String[] { "reminddate", webinfo.getReminddate() });
			}
		}else{
			rs.addRow(new String[] { "false", "没有短信公告!" });
		}

		return rs;
	}

	/**
	 * * Describe:对IC卡票 修改售出门票表中的票号 并激活
	 *
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getVaildcheckCodeup(java.lang.Long,
	 *      java.lang.String, java.lang.String)
	 * @param iscenicid
	 *            服务商
	 * @param oldszticketno
	 *            旧票号
	 * @param newszticketno
	 *            IC卡号
	 * @return
	 * @author lijingrui Date:2012-9-13
	 */
	public boolean jihuobyzjno(Long iscenicid, String szticketno, String name,
							   String zjno) {
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		String sql = " from Stssoldtickettab stss where stss.iscenicid="
				+ iscenicid + "  and stss.szticketprintno='" + szticketno
				+ "' order by stss.id.isalesvoucherid desc ";
		List list = this.ticketTypeService.find(sql);
		boolean flag = false;
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbb");
		//2015-02-04 陈新浩修改，启用导游计时时，判断是否可以激活导游票
		Sysparv5 sys = (Sysparv5) this.ticketTypeService.get(Sysparv5.class, new Sysparv5Id("DYJS", "****"));
		if(sys!=null && sys.getIsvalue()==1L && sys.getIsa()==1L){
			if(sys.getPmvb()!=null && !"".equals(sys.getPmvb())){
				String tickettype = sys.getPmvb();//需要验证的产品编码
				if(list!=null && !list.isEmpty()){
					Stssoldtickettab stssold = (Stssoldtickettab) list.get(0);
					Edmtickettypetab ticket = (Edmtickettypetab) this.ticketTypeService.get(Edmtickettypetab.class, stssold.getItickettypeid());
					if(ticket.getBycategorytype().equals(tickettype)){//产品类别为导游票则进行验证
						String hsql = "select new map(stss.id.isalesvoucherid as isalesvoucherid,stss.id.iticketstationid as iticketstationid,stss.id.szsoldticketid as szsoldticketid,stss.itickettypeid as itickettypeid,stss.iscenicid as iscenicid) from Stssoldtickettab stss,Edmtickettypetab edm " +
								" where stss.iscenicid="+iscenicid+" and stss.myzj='"+zjno+"' " +
								"and stss.dtenddate >='"+stssold.getDtstartdate()+"' " +
								"and edm.itickettypeid=stss.itickettypeid " +
								"and edm.bycategorytype='"+tickettype+"' ";
						System.out.println(hsql);
						List hlist = this.ticketTypeService.find(hsql);
						if(hlist!=null && !hlist.isEmpty()){
							for(int i=0;i<hlist.size();i++){
								Map map = (Map) hlist.get(i);
								String hsql2 = " from Stssoldticketsubtab stss where stss.id.szsoldticketid="
										+ map.get("szsoldticketid").toString()
										+ " and stss.id.isalesvoucherid="
										+ map.get("isalesvoucherid").toString()
										+ " and stss.id.iticketstationid="
										+ map.get("iticketstationid").toString()
										+ " and stss.iscenicid="
										+ map.get("iscenicid").toString()
										+ " and stss.itickettypeid="
										+ map.get("itickettypeid").toString()
										+ " and stss.dtenddate >='"+Tools.getDayTimes()+"' ";
								List hlist2 = this.ticketTypeService.find(hsql2);
								if(hlist2!=null && !hlist2.isEmpty()){//已存在激活未结束订单，删除销售凭证，出票失败
									deleteSalesvoucher(stssold.getId().getIsalesvoucherid(),stssold.getId().getIticketstationid());
									return false;
								}
							}
						}
					}
				}
			}
		}


		if (list != null && list.size() > 0) {
			Stssoldtickettab stssold = (Stssoldtickettab) list.get(0);
			stssold.setName1(name);
			stssold.setMyzj(zjno);
			this.ticketTypeService.update(stssold);
			String msql = " from Stssoldticketsubtab stss where stss.id.szsoldticketid="
					+ stssold.getId().getSzsoldticketid()
					+ " and stss.id.isalesvoucherid="
					+ stssold.getId().getIsalesvoucherid()
					+ " and stss.iscenicid="
					+ stssold.getIscenicid()
					+ " and stss.itickettypeid=" + stssold.getItickettypeid();
			List mList = this.ticketTypeService.find(msql);
			if (mList != null && mList.size() > 0) {
				for (int i = 0; i < mList.size(); i++) {
					Stssoldticketsubtab stssoldsub = (Stssoldticketsubtab) mList
							.get(i);

					stssoldsub.setIsvalid(new Long(1));

					this.ticketTypeService.update(stssoldsub);
				}
			}
			System.out.println("ccccccccccccccccccc");
			String csql = " from Stsschecktab stss where stss.id.szsoldticketid="
					+ stssold.getId().getSzsoldticketid()
					+ " and stss.id.isalesvoucherid="
					+ stssold.getId().getIsalesvoucherid()
					+ " and stss.iscenicid="
					+ stssold.getIscenicid()
					+ " and stss.itickettypeid=" + stssold.getItickettypeid();
			List cList = this.ticketTypeService.find(csql);
			if (mList != null && mList.size() > 0) {
				for (int i = 0; i < cList.size(); i++) {
					Stsschecktab stssoldsub = (Stsschecktab) cList.get(i);

					stssoldsub.setIsvalid(new Long(1));
					stssoldsub.setName1(name);
					stssoldsub.setMyzj(zjno);
					this.ticketTypeService.update(stssoldsub);
				}
			}
			System.out.println("dddddddddddddddddddddddddd");
			return true;

		} else {
			return false;
		}

	}

	/**
	 * *
	 * Describe:获取订单信息
	 * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getMordercheck(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2014-3-3
	 */
	public MOrder getMordercheck(String orid){
		MOrder morder=(MOrder)this.ticketTypeService.get(MOrder.class, orid);
		return morder;
	}

	public void deleteSalesvoucher(Long isalesvoucherid,Long iticketstationid){
		String hsql = "from Stssoldticketsubtab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		List list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stssoldticketsubtab s = (Stssoldticketsubtab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
		hsql = "from Stssoldtickettab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stssoldtickettab s = (Stssoldtickettab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
		hsql = "from Stscomticketsalesdetailstab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stscomticketsalesdetailstab s = (Stscomticketsalesdetailstab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
		hsql = "from Stssalesvoucherdetailstab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stssalesvoucherdetailstab s = (Stssalesvoucherdetailstab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
		hsql = "from Stssalessettlementtab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stssalessettlementtab s = (Stssalessettlementtab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
		hsql = "from Stssalesvouchertab s where s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid;
		list = this.ticketTypeService.find(hsql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Stssalesvouchertab s = (Stssalesvouchertab) list.get(i);
				this.ticketTypeService.delete(s);
			}
		}
	}

}


