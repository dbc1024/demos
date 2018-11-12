package com.ectrip.ec.order.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.dao.idao.IOrderModifyDao;
import com.ectrip.ec.order.service.iservice.IOrderModifyService;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.sys.model.balance.Zhifu;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderModifyService extends GenericService implements
		IOrderModifyService {

	@Autowired
	private IOrderModifyDao orderModifyDao;
	
	@Autowired
	private SysparService sysparService;
//	private ISyslogDao syslogDao;

	

	public List queryOrderModify(TOrder tOrder) {
		return orderModifyDao.queryOrderModify(tOrder);
	}

	public List<TOrderlist> queryOrderList(String orid, Long iscenicid) {
		return orderModifyDao.queryOrderList(orid, iscenicid);
	}

	/*
	 * 注意：所有的中文提示，以后要换成国际化。否则编码改编会乱码。
	 * 
	 * @see com.ectrip.order.modify.service.iservice.IOrderModifyService#
	 * updateOrderStatus(java.lang.String, java.lang.String, java.lang.Long)
	 */

	public String updateOrderStatus(String ddzt, String orid, Long iscenicid,
			Long iemployeeid, String username) {
		// 修改订单状态
		MOrder mOrder = (MOrder) this.orderModifyDao.get(MOrder.class, orid);
		IOrderService orderService = (IOrderService) SpringUtil
				.getBean("orderService");
		// 下单用户
		Custom custom = (Custom) this.get(Custom.class, mOrder.getUsid());
		String oldDdzt = mOrder.getDdzt();// 原来的状态
		if (ddzt.equals(oldDdzt)) {
			return "请选择新的订单状态修改@input";
		}
		Zhifu zhifu = null;
		if (ddzt.equals("02")) {
			if(oldDdzt.equals("00")){
				// 根据ORID查询支付信息
				List zhifuList = this.find("from Zhifu where orid='" + orid
						+ "' and isok=1");
				if (zhifuList != null && zhifuList.size() > 0) {
					zhifu = (Zhifu) zhifuList.get(0);
				} else {
					Vipbalance balance=(Vipbalance)this.get(Vipbalance.class,mOrder.getUsid());
					if(balance==null){
						return "预付款余额不足,不允许修改成已支付@input";
					}
				}
			}else if(oldDdzt.equals("11")){  //s
				
			}
			
		}

		
		String[] ddzts = { "01", "02", "11" };// 可修改的订单状态
		boolean isEdit = false;
		for (int i = 0; i < ddzts.length; i++) {
			if (oldDdzt.equals(ddzts[i])) {
				isEdit = true;
				break;
			}
		}
		boolean flag = false;
		if (isEdit) {
			if (oldDdzt.equals("00")) {// 未支付
				if (ddzt.equals("01") || ddzt.equals("02")) {
					flag = orderZftypeModify(iemployeeid, orid,
							iscenicid, zhifu);
				} else {
					return "未支付订单只能修改成已支付@input";
				}
			}
			if (oldDdzt.equals("01") || oldDdzt.equals("02")) {// 已支付
				if (ddzt.equals("11")) {// 出票
					updateOrderToXf(mOrder, username);
				} else if (ddzt.equals("03")) {// 退订需退款
					try {
						List<TOrderlist> torderlists = orderService
								.getTOrderlists(orid, iscenicid);
						String[] orids = new String[2];
						orids[0] = "";
						orids[1] = this.getMaxNo("000");
						if (custom.getLgtp().equals("01")) {// 散客
							flag = (Boolean) orderService.editOrderSankeCenter(
									torderlists, null, orids, orid,
									String.valueOf(iscenicid),
									mOrder.getStdt(),
									custom.getIbusinessid().toString(),
									custom.getUsid()).get("result");
						} else {// 旅行社
							flag = (Boolean) orderService.editOrderCenter(
									torderlists, null, orids, orid,
									String.valueOf(iscenicid),
									mOrder.getStdt(),
									custom.getIbusinessid().toString(),
									custom.getUsid()).get("result");
						}
						if (flag) {
							// 插入日志
							Syslog syslog = new Syslog(this.getMaxPk("logid",
									"Syslog"), iemployeeid, "0157", "修改订单",
									Tools.getDayTimes());
							sysparService.insertSyslog(syslog);
						}
					} catch (Exception e) {
						return "订单修改失败@input";
					}
				} else if (ddzt.equals("00")) {// 未支付
					updateOrderNoPay(mOrder, username);
				} else {
					return "已支付订单只能修改成未支付或已出票或退订@input";
				}
			}

			if (oldDdzt.equals("11")) {// 已出票
				if (ddzt.equals("01") || ddzt.equals("02")) {
					flag = orderZftypeModify(iemployeeid, orid,
							iscenicid, zhifu);
				} else {
					return "已出票订单只能修改成已支付@input";
				}
			}
		} else {
			return "此订单状态不能修改@input";
		}

		if (flag) {
			return "修改订单状态成功@success";
		} else {
			return "修改订单状态失败@input";
		}
	}

	public String updateOrderInfo(Long iemployeeid, Object... objects) {
		// 第一个参数Morder
		MOrder mOrder = (MOrder) objects[0];
		// 第二个参数Torder
		TOrder tOrder = (TOrder) objects[1];
		// 第三个参数是Torderlist
		List<TOrderlist> tOrderlists = (List<TOrderlist>) objects[2];
		
		MOrder m_order=null;   //修改后的订单表MOrder

		// 原来的订单数据
		MOrder oldMOrder = (MOrder) this.orderModifyDao.get(MOrder.class,
				tOrder.getId().getOrid());
		TOrder oldTOrder = (TOrder) this.orderModifyDao.get(TOrder.class,
				tOrder.getId());
		List<TOrderlist> oldTOrderlists = this.orderModifyDao.queryOrderList(
				tOrder.getId().getOrid(), tOrder.getId().getIscenicid());

		// 对不能直接修改的信息进行处理
		// 用新的订单数据跟原来的数据对比。只比较不能直接修改的字段数据(根据页面的和文档说明来处理)

		try {
			m_order = (MOrder) convertClass(mOrder, oldMOrder);
			oldTOrder = (TOrder) convertClass(tOrder, oldTOrder);
		} catch (Exception e) {
			return "数据转换出现异常，请联系开发人员@input";
		}
		
		List<TOrderlist> backlist=new ArrayList<TOrderlist>();
		
		try{
		if(oldTOrder!=null&&!oldTOrder.getDdzt().equals("11")){
			if(oldTOrderlists!=null&&oldTOrderlists.size()>0){
				for (int i = 0; i < oldTOrderlists.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
					TOrderlist oldtorderlist = new TOrderlist();
					BeanUtils.populate(oldtorderlist, (Map) oldTOrderlists.get(i));
					for (int j = 0; j < tOrderlists.size(); j++) {// 修改后的订单
						TOrderlist newtorderlist = (TOrderlist) tOrderlists.get(j);
						if (newtorderlist.getOrderlistid().equals(oldtorderlist.getOrderlistid()) && newtorderlist.getIscenicid().equals(oldtorderlist.getIscenicid())) {
							
							if (newtorderlist.getNumb().intValue() != oldtorderlist.getNumb().intValue()) {
								TOrderlist ctorderlist = new TOrderlist();
								BeanUtils.copyProperties(ctorderlist, oldtorderlist);
								// 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
								ctorderlist.setNumb(newtorderlist.getNumb() - oldtorderlist.getNumb());
								ctorderlist.setId(new TOrderlistId(Long.parseLong(oldtorderlist.getOrderlistid()), oldtorderlist.getOrid(), oldtorderlist.getId().getIscenicid()));
								ctorderlist.setAmnt(oldtorderlist.getPric() * (newtorderlist.getNumb() - oldtorderlist.getNumb()));
								ctorderlist.setYhnumb(oldtorderlist.getYhnumb());
								
								backlist.add(ctorderlist);
								
							}
						}
					}
				}
			}
		}
		}catch(Exception e){
			return "数据转换出现异常，请联系开发人员@input";
		}
		
		//判断MOrder表中的金额是否改动，如果改动了，那么就要添加价差产品以及要生成 关于价差的退订单。
	/*	Double oldmont=oldMOrder.getMont();
		Double newmont=mOrder.getMont();
		Double chajia=oldmont-newmont;
		MOrder morder2=new MOrder();
		TOrder torder2=new TOrder();
		List<TOrderlist> torderlist2=new ArrayList<TOrderlist>(); 
		if(chajia!=null&&chajia!=0){
			try {
				String neworid = this.getMaxNo("000");
			
			if(oldMOrder.getTpmont()==null||oldMOrder.getTpmont().equals("")){
				oldMOrder.setTpmont(totalmont);
			}else{
				oldMOrder.setTpmont(oldMOrder.getTpmont()+totalmont);
			}
			if(oldMOrder.getDdzt()!=null&&(oldMOrder.getDdzt().equals("95")||oldMOrder.getDdzt().equals("00"))){
				oldMOrder.setZfmont(oldMOrder.getZfmont()-totalmont);
			}
			
			this.update(oldMOrder);
			
			
			//网上预定订单
			MOrder md = new MOrder();
			md.setOrid(neworid);
			md.setSrid(oldMOrder.getOrid());
			md.setDdzt("06");//退订已退款
			md.setOrtp("02");//// 订单类型 01 预定 02 退票 03 增量订单
			md.setMont(totalmont);//订单金额
			md.setZfmont(totalmont);// 支付的金额					
			if(oldMOrder.getDdzt().equals("02")){
				md.setTpfs("01");//退订方式（ 01 出票前修改）
			}else if(oldMOrder.getDdzt().equals("11")){
				md.setTpfs("02");//退订方式（ 02 出票后修改）
			}else{
				md.setTpfs("01");
			}
			
			md.setTpmont(totalmont);//实退金额
			md.setTpdate(oldMOrder.getOrda()+" "+oldMOrder.getOrti());
			md.setIsj(1L);
			md.setNotef("07");
			
			md.setUsid(oldMOrder.getUsid());// 游客编号id
			md.setOrda(oldMOrder.getOrda());//下单日期
			md.setOrti(oldMOrder.getOrti());// 下单时间
			md.setIsjl(0L);// 奖励订单
			md.setYhamnt(0D);// 优惠金额
			md.setIsallcp(0L);// 是否全部出票
			md.setTpsx(0d);//退订手续费
			md.setTpfl(0d);//退订手续费率
			md.setStdt(oldMOrder.getStdt());//首次消费日期					
			md.setIsf(0L);
			md.setIsa(0L);
			md.setIsb(0L);
			md.setIsd(0L);
			md.setIse(0L);
			md.setIsg(0L);
			md.setIsh(0L);
			md.setIsi(0L);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		String hsql1 = " from MOrder where orid='"+orid+"' ";
		String hsql2 = " from TOrder where id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		String hsql3 = " from YOrder where id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		List morderlist = this.find(hsql1);
		List torderlist = this.find(hsql2);
		List yorderlist = this.find(hsql3);
		
		
		if(torderlist!=null && torderlist.size()>=1){
			//订单状态为已支付     出票前退订
			if(mder.getDdzt().equals("02")){
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder)torderlist.get(i);
					torder.setMont(torder.getMont()-totalmont);//订单金额
					torder.setZfmont(torder.getZfmont()-totalmont);//支付的金额
					this.update(torder);
				}
			}else if(mder.getDdzt().equals("11")){
				TOrder oder=(TOrder)torderlist.get(0);
				TOrder torder=new TOrder();
				TOrderId tdid=new TOrderId();
				tdid.setOrid(neworid);// 重新生成订单号
				tdid.setIscenicid(oder.getId().getIscenicid());
				torder.setId(tdid);
				torder.setScenictype(oder.getScenictype());//服务商类型
				torder.setOrfl(oder.getOrfl());
				torder.setUsid(oder.getUsid());//游客编号
				torder.setIbusinessid(oder.getIbusinessid());//业务类型（散客业务）
				torder.setIregionalid(oder.getIregionalid());//客源地
				torder.setDtstartdate(oder.getDtstartdate());//启用日期
				torder.setDtenddate(oder.getDtenddate());//失效日期
				torder.setDdzt(ddzts);			//退订已退款
				torder.setOrnm(oder.getOrnm());//领票人姓名
				torder.setOrzj(oder.getOrzj());//领票人证件类别
				torder.setOrhm(oder.getOrhm());//领票人证件号码
				torder.setOrph(oder.getOrph());//领票人电话
				torder.setFaxno(oder.getFaxno());//传真号
				torder.setMont(totalmont);//订单金额
				torder.setYhamnt(oder.getYhamnt());//优惠金额
				torder.setZfmont(totalmont);//支付的金额
				torder.setYoufei(oder.getYoufei());//邮费
				
				torder.setIsa(0L);
				torder.setIsb(0L);
				torder.setIsc(0L);
				torder.setIsd(0L);
				torder.setIse(0L);
				torder.setIsf(0L);
				torder.setIsg(0L);
				torder.setIsh(0L);
				torder.setIsi(0L);
				torder.setIsj(new Long(-1));
				torder.setNotej(orid);
				torder.setNotef("07"); //后台强制退订
				this.save(torder);
			}else{
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder)torderlist.get(i);
					torder.setMont(torder.getMont()-totalmont);//订单金额
					torder.setZfmont(torder.getZfmont()-totalmont);//支付的金额
					this.update(torder);
				}
			}
			
		}
		
		String tql=" from TOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" order by ts.amnt desc";
		List tdlist=this.find(tql);
		if(tdlist!=null&&tdlist.size()>=1){
			TOrderlist newTicket = (TOrderlist)tdlist.get(0);
			// 新增差价产品
			TOrderlist newproduct = new TOrderlist();
			TOrderlistId todid=new TOrderlistId();
			todid.setIscenicid(iscenicid);
			String tpsql="select max(ts.id.orderlistid) from TOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid;
			Long seq=(Long) this.find(tpsql).get(0)==null?1:(Long) this.find(tpsql).get(0)+1;
			todid.setOrderlistid(seq);
			if(mder.getDdzt().equals("02")){
				todid.setOrid(orid);
			}else if(mder.getDdzt().equals("11")){
				todid.setOrid(neworid);
			}else{
				todid.setOrid(orid);
			}
			newproduct.setId(todid);
			
			newproduct.setItickettypeid(newTicket.getItickettypeid());
			newproduct.setIcrowdkindid(newTicket.getIcrowdkindid());
			newproduct.setIcrowdkindpriceid(newTicket.getIcrowdkindpriceid());
			newproduct.setDtstartdate(newTicket.getDtstartdate());
			newproduct.setDtenddate(newTicket.getDtenddate());
			newproduct.setPric(new Double(-1*totalmont));
			newproduct.setJsprice(new Double(-1*totalmont));
			newproduct.setNumb(1L);
			newproduct.setYhnumb(0l);
			newproduct.setAmnt(new Double(-1*totalmont));
			newproduct.setYhamnt(0.0);	
			this.save(newproduct);
			
			String tzql=" from TZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newTicket.getId().getOrderlistid()+" and ts.itickettypeid="+newTicket.getItickettypeid();
			List tzlist=this.find(tzql);
			TZorderlist tzderlist=(TZorderlist) tzlist.get(0);
			TZorderlist tzorderlist = new TZorderlist();
			TZorderlistId tzderid=new TZorderlistId();
			tzderid.setIscenicid(iscenicid);
			
			if(mder.getDdzt().equals("02")){
				tzderid.setOrid(orid);
			}else if(mder.getDdzt().equals("11")){
				tzderid.setOrid(neworid);
			}else{
				tzderid.setOrid(orid);
			}
			tzderid.setOrderlistid(newproduct.getId().getOrderlistid());
			String tzpsql="select max(ts.id.zorderlistid) from TZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+"and ts.id.orderlistid="+newTicket.getId().getOrderlistid();
			Long seq2=(Long) this.find(tzpsql).get(0)==null?1:(Long) this.find(tzpsql).get(0)+1;
			tzderid.setZorderlistid(seq2);
			tzorderlist.setId(tzderid);
			
		    tzorderlist.setItickettypeid(tzderlist.getItickettypeid());
		    tzorderlist.setIcrowdkindpriceid(tzderlist.getIcrowdkindpriceid());
		    tzorderlist.setIcrowdkindid(tzderlist.getIcrowdkindid());
		    tzorderlist.setIztickettypeid(tzderlist.getIztickettypeid());
			tzorderlist.setDtstartdate(newTicket.getDtstartdate());
			tzorderlist.setDtenddate(newTicket.getDtenddate());
		    tzorderlist.setZpric(new Double(-1*totalmont));
		    tzorderlist.setJsprice(new Double(-1*totalmont));
		    tzorderlist.setZnumb(new Long(1));
		    tzorderlist.setZyhnumb(0l);
		    tzorderlist.setZyhamnt(0.0);
		    tzorderlist.setZamnt(new Double(-1*totalmont));
			this.save(tzorderlist);
		}	    
		
		
		if(yorderlist!=null && yorderlist.size()>=1){
			for (int x = 0; x < yorderlist.size(); x++) {
				YOrder yorder = (YOrder)yorderlist.get(x);
				if(yorder.getTpmont()==null||yorder.getTpmont().equals("")){
					yorder.setTpmont(totalmont);
				}else{
					yorder.setTpmont(totalmont+yorder.getTpmont());  //退款金额
				}
				yorder.setTpdate(Tools.getTodayString()); //退款时间
				this.update(yorder);
			}
			YOrder yorder = (YOrder)yorderlist.get(0);
			YOrder yder=new YOrder();
			YOrderId yid=new YOrderId();
			yid.setOrid(neworid);
			yid.setIscenicid(iscenicid);
			yder.setId(yid);
			yder.setDdzt(ddzts);
			
			yder.setOrtp("02");//// 订单类型 01 预定 02 退票 03 增量订单
			yder.setMont(totalmont);//订单金额
			yder.setZfmont(totalmont);// 支付的金额					
			yder.setTpmont(totalmont);//实退金额
			yder.setTpdate(mder.getOrda()+" "+mder.getOrti());
			yder.setIsj(1L);
			yder.setNotef("07");
			yder.setNotej(orid);
			
			yder.setScenictype(yorder.getScenictype());//服务商类型
			yder.setUsid(yorder.getUsid());//游客编号
			yder.setIbusinessid(yorder.getIbusinessid());//业务类型（散客业务）
			yder.setIregionalid(yorder.getIregionalid());//客源地
			yder.setDtstartdate(yorder.getDtstartdate());//启用日期
			yder.setDtenddate(yorder.getDtenddate());//失效日期
			yder.setOrnm(yorder.getOrnm());//领票人姓名
			yder.setOrzj(yorder.getOrzj());//领票人证件类别
			yder.setOrhm(yorder.getOrhm());//领票人证件号码
			yder.setOrph(yorder.getOrph());//领票人电话
			yder.setFaxno(yorder.getFaxno());//传真号
			yder.setYhamnt(yorder.getYhamnt());//优惠金额
			yder.setYoufei(yorder.getYoufei());//邮费
			
			yder.setIsa(yorder.getIsa());
			yder.setIsb(yorder.getIsb());
			yder.setIsc(yorder.getIsc());
			yder.setIsd(yorder.getIsd());
			yder.setIse(yorder.getIse());
			yder.setIsf(yorder.getIsf());
			yder.setIsg(yorder.getIsg());
			yder.setIsh(yorder.getIsh());
			yder.setIsi(yorder.getIsi());
			this.save(yder);
			
			String yql=" from YOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" order by ts.amnt desc";
			List ydlist=this.find(yql);
			if(ydlist!=null&&ydlist.size()>=1){
				YOrderlist newydTicket = (YOrderlist)ydlist.get(0);
				// 新增差价产品
				YOrderlist newydproduct = new YOrderlist();
				String ypsql="select max(ts.id.orderlistid) from YOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid;
				Long seq3=(Long) this.find(ypsql).get(0)==null?1:(Long) this.find(ypsql).get(0)+1;
				YOrderlistId yodstid=new YOrderlistId(seq3,neworid,newydTicket.getId().getIscenicid());
				newydproduct.setId(yodstid);
				
				newydproduct.setItickettypeid(newydTicket.getItickettypeid());
				newydproduct.setIcrowdkindid(newydTicket.getIcrowdkindid());
				newydproduct.setIcrowdkindpriceid(newydTicket.getIcrowdkindpriceid());
				newydproduct.setPric(new Double(-1*totalmont));
				newydproduct.setJsprice(new Double(-1*totalmont));
				newydproduct.setDtstartdate(newydTicket.getDtstartdate());
				newydproduct.setDtenddate(newydTicket.getDtenddate());
				newydproduct.setNumb(1L);
				newydproduct.setAmnt(new Double(-1*totalmont));
				newydproduct.setYhnumb(0l);
				newydproduct.setYhamnt(0.0);
				newydproduct.setIsj(1L);
				newydproduct.setNotef("07");
				this.save(newydproduct);
				
				String yzql=" from YZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newydTicket.getId().getOrderlistid()+" and ts.itickettypeid="+newydTicket.getItickettypeid();
				List yzlist=this.find(yzql);
				YZorderlist yzderlist=(YZorderlist) yzlist.get(0);
				
				YZorderlist yzorderlist = new YZorderlist();
				String yzpsql="select max(ts.id.zorderlistid) from YZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newydTicket.getId().getOrderlistid();
				Long seq4=(Long) this.find(yzpsql).get(0)==null?1:(Long) this.find(yzpsql).get(0)+1;
				YZorderlistId yzdid=new YZorderlistId(seq4, yzderlist.getId().getOrderlistid(), neworid, iscenicid);
				yzorderlist.setId(yzdid);
				yzorderlist.setItickettypeid(yzderlist.getItickettypeid());
				yzorderlist.setIcrowdkindpriceid(yzderlist.getIcrowdkindpriceid());
				yzorderlist.setIcrowdkindid(yzderlist.getIcrowdkindid());
				yzorderlist.setIztickettypeid(yzderlist.getIztickettypeid());
				yzorderlist.setZpric(new Double(-1*totalmont));
				yzorderlist.setJsprice(new Double(-1*totalmont));
				yzorderlist.setDtstartdate(yzderlist.getDtstartdate());
				yzorderlist.setDtenddate(yzderlist.getDtenddate());
				yzorderlist.setZnumb(new Long(1));
				yzorderlist.setZyhnumb(0l);
				yzorderlist.setZyhamnt(0.0);
				yzorderlist.setZamnt(new Double(-1*totalmont));
				yzorderlist.setIsj(1L);
				yzorderlist.setNotef("07");
				this.save(yzorderlist);
				    
			}
			
		}
		
		if(mder.getDdzt().equals("02")||mder.getDdzt().equals("11")){
			Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, usid);
			if(vipbalance!=null){
				vipbalance.setPoint(vipbalance.getPoint()+totalmont);
				this.update(vipbalance);
			}else{
				vipbalance = new Vipbalance();
				vipbalance.setUsid(usid);
				vipbalance.setAcctype("01");
				vipbalance.setPoint(totalmont);
				this.save(vipbalance);
			}
			
			String hql = "select max(h.seq) from Useryfk h";
			
			// 用户预付款 先增加全退订金额
			Useryfk yfk = new Useryfk();
			yfk.setUsid(usid);
			yfk.setBdate(Tools.getTodayString());
			yfk.setSzmemo("用户  [" + esfemployeetab.getEmpid() + "]修改订单");
			yfk.setOrderid(orid);
			yfk.setPoint(totalmont);
			yfk.setYfklb(1);// 退订转预付款
		    yfk.setYfksc("02");
		    yfk.setNote("退订转预付款");
	   
			yfk.setCztp(0);
			int seqs = 0;
			List seqsList = this.find(hql);
			if (seqsList.size() > 0) {
				if (seqsList.get(0) == null) {
					seqs = 0;
				} else {
					seqs = Integer.parseInt(seqsList.get(0) == null ? "0" : seqsList.get(0).toString());
				}
			}
			yfk.setSeq(seqs + 1);
			this.save(yfk);
			
			// 平台预付款 先从平台预付款转出
			List sysList = this.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
			Sysparv5 sys = null;

			sys = (Sysparv5) sysList.get(0);// 取平台帐号
			Useryfk ptyfk = new Useryfk();
			int seq = 0;
			List seqList = this.find(hql);
			if (seqList.size() > 0) {
				if (seqList.get(0) == null) {
					seq = 0;
				} else {
					seq = Integer.parseInt(seqList.get(0) == null ? "0" : seqList.get(0).toString());
				}
			}
			ptyfk.setSeq(seq + 1);
			ptyfk.setUsid(sys.getPmva());// 用户
			ptyfk.setBdate(Tools.getTodayString());
			ptyfk.setOrderid(orid);
			ptyfk.setPoint(totalmont);
			ptyfk.setYfklb(-1);
		    ptyfk.setYfksc("13"); // 用户消费预付款 那平台就是用户预付款转入
		    ptyfk.setNote("用户订单强制退订");
	   
			ptyfk.setCztp(1);
			this.save(ptyfk);
		}
		
		
		syslog.setStlg("0012");
		syslog.setBrief("订单强制退订，订单号："+orid   );
		syslog.setNote("订单强制退订，订单号："+orid );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	 	orderlog.setLogid(getMaxPk("logid", "Orderlog") + 1);
	    orderlog.setOrid(orid);
	    orderlog.setStlg("0209");
	    orderlog.setUsid("");
	    orderlog.setLogtype(Long.parseLong("1"));	    
	    orderlog.setLogdatetime(Tools.getDayTimes());
	    orderlog.setBrief("后台订单强制退订");
	    if(orderlog.getNote()==null||orderlog.getNote().equals("")){
	    	orderlog.setNote(orderlog.getBrief());
	    }
	    this.save(orderlog); */
	
	    
		this.orderModifyDao.update(m_order);
		this.orderModifyDao.update(oldTOrder);
		for (TOrderlist tOrderlist : oldTOrderlists) {
			this.orderModifyDao.update(tOrderlist);
		}

		return "修改订单信息成功@success";
	}

	/*
	 * 注意：这个方法只支持单个对象转换，不支持集合
	 */
	public Object convertClass(Object newObject, Object oldObject)
			throws Exception {
		Field[] newfields = newObject.getClass().getDeclaredFields();
		Field[] oldfields = oldObject.getClass().getDeclaredFields();
		for (int i = 0; i < oldfields.length; i++) {
			for (Field field : newfields) {
				if (field.getName().equals(oldfields[i].getName())) {
					field.setAccessible(true);
					if (field.get(newObject) != null
							&& !"".equals(field.get(newObject))) {
						oldfields[i].setAccessible(true);
						oldfields[i].set(oldObject, field.get(newObject));
					}
				}
			}
		}
		return oldObject;
	}

	//修改订单为已支付
		public boolean orderZftypeModify(Long iemployeeid,String orid,Long iscenicid,Zhifu zhifu){
			try {
				IOrderService orderService = (IOrderService) SpringUtil
						.getBean("orderService");
				
				String [] ss = zhifu.getBanktime().split(" ");
				MOrder morder = orderService.getMorder(orid);
				String hsql = "" ;
				
				morder.setBank(zhifu.getBank());//银行
				morder.setZffs("01");//网上支付
				morder.setUsid(zhifu.getUsid());//支付人
				morder.setPayorid(zhifu.getPayid());//支付号
				morder.setBankdata(ss[0]);// 支付日期
				morder.setBanktime(ss[1]);// 支付时间
				
				
				if ( ! Tools.float45(morder.getZfmont(), zhifu.getMont())  )
	            {
	            	throw new Exception("订单支付金额不相等！ ");	
	            }
				if ("02".equals(morder.getDdzt()) // 订单已经支付或者已经出票
						|| "11".equals(morder.getDdzt())) {
					morder.setBank(zhifu.getBank());
					morder.setPayorid(zhifu.getPayid());
					morder.setBankdata(ss[0]);// 支付日期
					morder.setBanktime(ss[1]);// 支付时间
					morder.setZffs("01");// 支付方式(网上银行支付)
					throw new Exception("订单已经支付，不能再次修改 ");
				} else {
					morder.setDdzt("02");
					/** 李进修改，2013-10-25 日修改 */
					morder.setZffs("01");// 支付方式(网上银行支付)
					morder.setZfusid(morder.getUsid());
					
					List torderList = find("from TOrder where id.orid='" + zhifu.getOrid() + "'");
					TOrder torder = null;
					for (int i = 0; i < torderList.size(); i++) {
						torder = (TOrder) torderList.get(i);
						torder.setDdzt("02");
						saveOrUpdate(torder);
						
					}

					List yordList = find("from YOrder where id.orid='" + zhifu.getOrid() + "'");
					YOrder yorder = null;

					for (int i = 0; i < yordList.size(); i++) {
						yorder = (YOrder) yordList.get(i);
						yorder.setDdzt("02");// 订单状态（已支付）
						saveOrUpdate(yorder);
					}
				update(morder);
				//插入日志
				Orderlog orderlog = new Orderlog(this.getMaxPk("logid", "Orderlog"), orid, "0157", "手动修改订单状态为已支付", 1L, String.valueOf(iemployeeid),iemployeeid, Tools.getNowString(), "更新订单状态为已支付。");
				sysparService.insertOrderlog(orderlog);
				
				//如果是B2B订单需要同步状态
				hsql = "select s.b2borid as b2borid from Ordersync s where s.b2corid='"+orid+"' ";
				List list = this.find(hsql);
				if(list!=null&&!list.isEmpty()){/*
					IB2BSyncService b2bSyncService = (IB2BSyncService) SpringUtil.getBean("b2bSyncService");
					//需要同步
					B2BOrderService service = new B2BOrderService();
					boolean b = service.zhifuSave(list.get(0).toString(),
							zhifu.getPayid());
					Syslog syslog = new Syslog();
					syslog.setIemployeeid(1L);
					b2bSyncService.savezhifuLog(orid,
							zhifu.getPayid(), syslog, b);
					*/}
				} 
			}catch (Exception e) {
				Tools.printException(e);
				return false;
			}
			return true;
		}
		/**
		 * 修改订单为已消费订单 Describe:
		 * 
		 * @auth:huangyuqi
		 * @param morder
		 * @param username
		 *            return:void Date:2011-11-11
		 */
		public int updateOrderToXf(MOrder morder, String username) {
			try {
				// 根据订单号得到订单列表
				List torderList = this.find("from TOrder where id.orid='" + morder.getOrid() + "'");
				TOrder torder = null;
				if (torderList != null && torderList.size() >= 1) {
					for (int i = 0; i < torderList.size(); i++) {
						torder = (TOrder) torderList.get(i);
						torder.setDdzt("11");
						saveOrUpdate(torder);
					}
				}

				// 根据订单号得到订单列表
				List yorderlist = this.find("from YOrder where id.orid='" + morder.getOrid() + "'");
				YOrder yorder = null;
				if (yorderlist != null && yorderlist.size() >= 1) {
					for (int i = 0; i < yorderlist.size(); i++) {
						yorder = (YOrder) yorderlist.get(i);
						yorder.setDdzt("11");
						saveOrUpdate(yorder);
					}
				}
				morder.setDdzt("11");
				update(morder);// 保存销售订单
				Long seq = this.getMaxPk("logid", new String[] {}, new Long[] {}, new String[] {}, new String[] {},
						"Syslog");

				Orderlog syslog = new Orderlog();

				String hsql1 = " from Employee where empid='" + username + "' ";
				List list = this.find(hsql1);
				Long empid = 0L;
				if (list.size() >= 1) {
			//		empid = ((Employee) list.get(0)).getIemployeeid();
				}
				syslog.setLogid(seq + 1);
				syslog.setIemployeeid(empid);// 后台操作人
				syslog.setLogdatetime(Tools.getNowString());
				syslog.setStlg("0157");
				syslog.setBrief("会计设置订单为未支付状态成功");
				syslog.setLogdatetime(Tools.getDayTimes());
				syslog.setNote("客服" + username + "设置订单,订单号" + morder.getOrid() + "的订单为未支付状态成功");
				save(syslog);
				//如果是B2B订单需要同步状态
				String hsql = "select s.b2borid as b2borid from Ordersync s where s.b2corid='"+morder.getOrid()+"' ";
				List blist = this.find(hsql);
				if(blist!=null&&!blist.isEmpty()){
					//需要同步
				}

			} catch (Exception e) {
				e.printStackTrace();
			
				return 0;
			} finally {
				
			}
			return 1;
		}
		
		public boolean updateOrderNoPay(MOrder morder, String username) {
			//如果是B2B订单需要同步状态
			String hsql = "select s.b2borid as b2borid from Ordersync s where s.b2corid='"+morder.getOrid()+"' ";
			List blist = this.find(hsql);
			if(blist!=null&&!blist.isEmpty()){/*
				//需要同步
				B2BOrderService orderService = new B2BOrderService();
				boolean flag = orderService.orderDdztModify(blist.get(0).toString(), 1);
				if (!flag) {
					return false;
				}
			*/}
			
			// 根据订单号得到订单列表
			List torderList = this.find("from TOrder where id.orid='" + morder.getOrid() + "'");
			TOrder torder = null;
			if (torderList != null && torderList.size() >= 1) {
				for (int i = 0; i < torderList.size(); i++) {
					torder = (TOrder) torderList.get(i);
					torder.setDdzt("00");
					saveOrUpdate(torder);
				}
			}

			// 根据订单号得到订单列表
			List yorderlist = this.find("from YOrder where id.orid='" + morder.getOrid() + "'");
			YOrder yorder = null;
			if (yorderlist != null && yorderlist.size() >= 1) {
				for (int i = 0; i < yorderlist.size(); i++) {
					yorder = (YOrder) yorderlist.get(i);
					yorder.setDdzt("00");
					saveOrUpdate(yorder);
				}
			}
			morder.setDdzt("00");
			update(morder);// 保存销售订单
			Long seq = this.getMaxPk("logid", new String[] {}, new Long[] {}, new String[] {}, new String[] {},
					"Syslog");

			Orderlog syslog = new Orderlog();

			String hsql1 = " from Employee where empid='" + username + "' ";
			List list = this.find(hsql1);
			Long empid = 0L;
			if (list.size() >= 1) {
		//		empid = ((Employee) list.get(0)).getIemployeeid();
			}
			syslog.setLogid(seq + 1);
			syslog.setIemployeeid(empid);// 后台操作人
			syslog.setLogdatetime(Tools.getNowString());
			syslog.setStlg("0157");
			syslog.setOrid(morder.getOrid());
			syslog.setLogtype(1L);
			syslog.setBrief("设置订单为未支付状态成功");
			syslog.setLogdatetime(Tools.getDayTimes());
			syslog.setNote("客服" + username + "设置订单,订单号" + morder.getOrid() + "的订单为未支付状态成功");
			save(syslog);

		return true;
	}
	
}
