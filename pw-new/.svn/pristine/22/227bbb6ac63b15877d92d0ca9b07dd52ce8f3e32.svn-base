package com.ectrip.ec.order.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.dao.idao.IOrderCheckDAO;
import com.ectrip.sys.model.balance.Jslist;
import com.ectrip.sys.model.balance.JslistId;
import com.ectrip.sys.model.balance.Jszb;
import com.ectrip.sys.model.balance.Vipbalance;
import com.ectrip.sys.model.syspar.Sysparv5;

public class OrderCheckDAO extends GenericDao implements IOrderCheckDAO {
	
	SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
	
	
	/**
	 * 根据订单--财务确认
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param titype
	 * @return
	 * @throws Exception
	 * return:int
	 * Date:2011-11-14
	 */
	public int ProviderYfkOK(String orid,String titype)throws Exception {
//		Session session = (Session) getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			MOrder morder = (MOrder) this.get(MOrder.class, orid);
			if (!morder.getDdzt().equals("22")) {
				throw new Exception("订单状态不对,必须是已经首次确认");
			}
			String hql = "from YOrder sc where sc.id.orid =? ";
			List<YOrder> Mylist = this.find(hql, new Object[] { orid });
	
			for (YOrder mylist : Mylist) {
				if (!mylist.getDdzt().equals("22")) {
					throw new Exception("订单状态不对,必须是已经首次确认");
				}
			}
	
			hql = "from TOrder sc where sc.id.orid =? ";
			List<TOrder> Myorder = this.find(hql, new Object[] { orid });
	
			for (TOrder mylist : Myorder) {
				if (!mylist.getDdzt().equals("22")) {
					throw new Exception("订单状态不对,必须是已经首次确认");
				}
			}
		
			hql = "from Jslist jl where jl.status='22' and jl.id.orid='" + orid + "'";
			List<Jslist> jsmxlist = this.find(hql);
	
			for (Jslist mylist : jsmxlist) {
				if (!"22".equals(mylist.getStatus())) {
					throw new Exception("订单状态不对,必须是已经首次确认");
				}
			}
	
			if ("0".equals(titype)) {
				titype = "01";
			}
			if ("1".equals(titype)) {
				titype = "02";
			}
			String jsql = " select j.seq,j.mont from Jszb j,Jslist jl where j.seq = jl.jszbseq and j.status='22' and jl.id.orid='"
					+ orid + "' and j.titype='" + titype + "'";
			List jslist = this.find(jsql);
	
			if (jslist != null && jslist.size() > 0) {
				for (int i = 0; i < jslist.size(); i++) {
					Object[] jsobj = (Object[]) jslist.get(i);
					Integer seq = Integer.valueOf(jsobj[0].toString()); // 编号
	
					String jshsql = " from Jszb j where j.status='22' and j.seq=" + seq;
					List<Jszb> jszblist = this.find(jshsql);
	
	
					List sysList = this.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
					Sysparv5 sys = null;
					sys=(Sysparv5) sysList.get(0);
					
					for (Jszb mylist : jszblist) {
						if (!mylist.getStatus().equals("22")) {
							throw new Exception("订单状态不对,必须是已经首次确认");
						}
						if ("22".equals(mylist.getStatus())) {
							mylist.setStatus("21");
							update(mylist);
						}
	
						// 计算预付款(途马）
						Useryfk yfk = new Useryfk();
						
						int seqs=0;
						seqs = getMaxSeq("Useryfk", "seq");
	
						yfk.setUsid(sys.getPmva());	
						yfk.setBdate(Tools.getTodayString());
						yfk.setNote("后台服务商结算");
						yfk.setOrderid(orid);
						yfk.setPoint(mylist.getTmont());
						yfk.setYfklb(1);
						yfk.setYfksc("05"); // 订单消费转预付款
						yfk.setCztp(0);
						yfk.setSeq(seqs+1);
	
						this.useryfksave(yfk);
	
						// 计算预付款(服务商）
						seqs = getMaxSeq("Useryfk", "seq");
						yfk = new Useryfk();
						yfk.setUsid(mylist.getPdno());
						yfk.setBdate(Tools.getTodayString());
						yfk.setNote("后台服务商结算");
						yfk.setOrderid(orid);
						yfk.setPoint(mylist.getPmont());
						yfk.setYfklb(1);
						yfk.setYfksc("05"); // 订单消费转预付款
						yfk.setCztp(0);
						yfk.setSeq(seqs+1);
	
						this.useryfksave(yfk);
	
					}
	
				}
			}
	
			String ddztjs = "21";
			morder.setDdzt(ddztjs);
			for (TOrder mylist : Myorder) {
				mylist.setDdzt(ddztjs);
				update(mylist);
			}
			for (YOrder mylist : Mylist) {
				mylist.setDdzt(ddztjs);
				update(mylist);
			}
			update(morder);
	
			for (Jslist mylist : jsmxlist) {
				mylist.setStatus("21");
				update(mylist);
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}finally{
			session.close();
		}
		
		return 1;
		
	}
	/**
	 * 根据服务商--财务确认
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param rzti
	 * @param ldti
	 * @param titype
	 * @return
	 * @throws Exception
	 * return:int
	 * Date:2011-11-14
	 */
	public int ProviderYfkOKByPdno(Long pdno,String rzti,String ldti,String titype){
//		Session session = (Session) getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
		String msql = "";
		if ("0".equals(titype)) {// 按下单时间
			msql = "select m.orid from MOrder m,TOrder t where m.orid = t.id.orid and t.ddzt='22' and m.orda>='" + rzti
					+ "' and m.orda<='" + ldti + "' and t.id.iscenicid=" + pdno;
		} else if ("1".equals(titype)) {// 按消费时间
			msql = "select m.orid from MOrder m,TOrder t where m.orid = t.id.orid and t.ddzt='22' and t.dtstartdate>='" + rzti
					+ "' and t.dtstartdate<='" + ldti + "' and t.id.iscenicid=" + pdno;
		}

		List mlist = this.find(msql);

		if (mlist != null && mlist.size() > 0) {

			for (int i = 0; i < mlist.size(); i++) {

				String morder = (String) mlist.get(i);// 订单号
				if (morder != null && !"".equals(morder)) {

					String morid = morder;// 订单号

					String tsql = " from TOrder t where t.ddzt='22' and t.id.orid='" + morid + "' and t.id.iscenicid="
							+ pdno;
					List<TOrder> tlist = this.find(tsql);

					if (tlist != null && tlist.size() > 0) {
						for (TOrder mylist : tlist) {
							if ("22".equals(mylist.getDdzt())) {
								mylist.setDdzt("21");
								this.update(mylist);// 保存更改
							}

						}

					}

					// 根据订单号查询非此服务商的订单信息
					String strsql = " from TOrder t where t.id.orid='" + morid + "' and t.id.iscenicid <> " + pdno;
					List strlist = this.find(strsql);

					boolean flag = false;
					if (strlist != null && strlist.size() > 0) {

						for (int j = 0; j < strlist.size(); j++) {
							TOrder order = (TOrder) strlist.get(j);
							if ("22".equals(order.getDdzt())) {
								flag = true;
							} else {
								flag = false;
							}
						}
					}
					// 说明：如果T_order中存在此订单的所有状态都末为22时，则不改变M_order中的订单状态，否则改变
					if (flag) {
						String msqls = " from MOrder m where m.ddzt='22' and m.orid='" + morid + "'";
						List<MOrder> mlists = this.find(msqls);
						if (mlists != null && mlists.size() > 1) {

							for (MOrder mylist : mlists) {

								if ("22".equals(mylist.getDdzt())) {
									mylist.setDdzt("21");
									update(mylist);
								}
							}

						}
					}

				}
			}
		}

		String hsql = " from YOrder h where h.ddzt='22' and h.dtstartdate>='" + rzti + "' and h.dtstartdate<='" + rzti
				+ "' and h.id.iscenicid=" + pdno;
		List<YOrder> hlist = this.find(hsql);
		if (hlist != null && hlist.size() > 0) {
			for (YOrder mylist : hlist) {

				if ("22".equals(mylist.getDdzt())) {
					mylist.setDdzt("21");
					update(mylist);
				}
			}
		}

		String jsmxhql = " from Jslist jl where jl.status='22' and jl.id.pdno=" + pdno;
		List<Jslist> jsmxlist = this.find(jsmxhql);

		for (Jslist mylist : jsmxlist) {
			if (!"22".equals(mylist.getStatus())) {
				throw new Exception("订单状态不对,必须是已经首次确认");
			}
		}

		if ("0".equals(titype)) {
			titype = "01";
		}
		if ("1".equals(titype)) {
			titype = "02";
		}
		String jsql = " select j.seq,jl.id.orid,j.mont from Jszb j,Jslist jl where j.seq = jl.jszbseq and j.status='22' and jl.id.pdno="
				+ pdno + " and j.titype='" + titype + "'";
		List jslist = this.find(jsql);

		if (jslist != null && jslist.size() > 0) {
			for (int i = 0; i < jslist.size(); i++) {
				Object[] jsobj = (Object[]) jslist.get(i);
				Integer seq = Integer.valueOf(jsobj[0].toString()); // 编号
				String strorid = jsobj[1].toString(); // 订单号

				String jshsql = " from Jszb j where j.status='22' and j.seq=" + seq;
				List<Jszb> jszblist = this.find(jshsql);

				for (Jszb mylist : jszblist) {
					if (!mylist.getStatus().equals("22")) {
						throw new Exception("订单状态不对,必须是已经首次确认");
					}
					if ("22".equals(mylist.getStatus())) {
						mylist.setStatus("21");
						update(mylist);
					}

					// 计算预付款(途马）
					Useryfk yfk = new Useryfk();


					List sysList = this.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
					Sysparv5 sys = null;
					sys=(Sysparv5) sysList.get(0);
					
					int seqs=0;
					seqs = getMaxSeq("Useryfk", "seq");
					
					
					yfk.setUsid(sys.getPmva());
					yfk.setBdate(Tools.getTodayString());
					yfk.setNote("后台服务商结算");
					yfk.setOrderid(strorid);
					yfk.setPoint(mylist.getTmont());
					yfk.setYfklb(1);
					yfk.setYfksc("05"); // 商户
					yfk.setCztp(0);
					yfk.setSeq(seqs+1);
					this.useryfksave(yfk);

					// 计算预付款(服务商）
					seqs = getMaxSeq("Useryfk", "seq");
					
					yfk = new Useryfk();
					yfk.setUsid(mylist.getPdno());
					yfk.setBdate(Tools.getTodayString());
					yfk.setNote("后台服务商结算");
					yfk.setOrderid(strorid);
					yfk.setPoint(mylist.getPmont());
					yfk.setYfklb(1);
					yfk.setYfksc("05"); // 商户
					yfk.setCztp(0);
					yfk.setSeq(seqs+1);

					this.useryfksave(yfk);

				}

			}
		}

		for (Jslist mylist : jsmxlist) {
			mylist.setStatus("21");
			update(mylist);
		}
		tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}finally{
			session.close();
		}
		return 1;
		
	}
	/**
	 * 根据订单首次确认
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param stdt
	 * @param etdt
	 * @param titype
	 * return:void
	 * Date:2011-11-14
	 */
	public int updateOrder(String orid,String stdt,String etdt,String titype){
//		Session session = (Session) getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			// 更改订单的状态为22（订单需后台二次审核）
			String msql = " from MOrder m where m.ddzt='11' and m.orid='" + orid + "'";
			List mlist = this.find(msql);
			if (mlist != null && mlist.size() > 0) {
				for (int i = 0; i < mlist.size(); i++) {
					MOrder morder = (MOrder) mlist.get(i);
					if (!"11".equals(morder.getDdzt())) {
						throw new Exception("订单状态不对，必需是已出票或已经对帐");
					}
					if ("11".equals(morder.getDdzt())) {
						morder.setDdzt("22");
						session.update(morder);// 保存更改

					}
				}
			}

			String hsql = " from YOrder h where h.ddzt='11' and h.id.orid='" + orid + "'";
			List hlist = this.find(hsql);
			if (hlist != null && hlist.size() > 0) {
				for (int i = 0; i < hlist.size(); i++) {
					YOrder yorder = (YOrder) hlist.get(i);
					if (!"11".equals(yorder.getDdzt())) {
						throw new Exception("订单状态不对，必需是已出票或已经对帐");
					}
					if ("11".equals(yorder.getDdzt())) {
						yorder.setDdzt("22");
						session.update(yorder);// 保存更改
					}
				}
			}
			String tsql = " from TOrder t where t.ddzt='11' and t.id.orid='" + orid + "'";
			List tlist = this.find(tsql);
			if (tlist != null && tlist.size() > 0) {
				for (int i = 0; i < tlist.size(); i++) {
					TOrder torder = (TOrder) tlist.get(i);
					if (!"11".equals(torder.getDdzt())) {
						throw new Exception("订单状态不对，必需是已出票或已经对帐");
					}
					if ("11".equals(torder.getDdzt())) {
						torder.setDdzt("22");
						session.update(torder);// 保存更改

						// 向结算总表中加入数据
						String jszbhsql = "select tor.id.iscenicid,sum(tor.mont) as mont from TOrder tor,MOrder mor where tor.id.orid =mor.orid and tor.id.orid='"
								+ orid
								+ "' and tor.id.iscenicid="
								+ torder.getId().getIscenicid()
								+ " and tor.ddzt='11' group by tor.id.iscenicid";
						
						List jszblist = this.find(jszbhsql);
						if (jszblist != null && jszblist.size() > 0) {

							for (int k = 0; k < jszblist.size(); k++) {
								Object[] jszbs = (Object[]) jszblist.get(k);
								// 得到总表的编号最大值
								Long seq = 0L;
								seq= this.getMaxPk("seq", "Jszb");
								
								Jszb jszb = new Jszb();
								jszb.setSeq(seq + 1);// 编号
								jszb.setPdno(jszbs[0].toString());// 服务商编号

								jszb.setMont(Double.valueOf(jszbs[1].toString()));// 总金额

								String sql = "select ord.id.orid, ord.jsprice as jsprice,ord.numb as sumnumb from TOrderlist ord,TOrder tor where ord.id.orid=tor.id.orid and ord.id.iscenicid=tor.id.iscenicid and tor.ddzt='11' and tor.id.orid='"
										+ orid + "' and tor.id.iscenicid=" + jszbs[0];

								List jspricelist = this.find(sql);

								double jsmont = 0;
								int sumnumb = 0;
								if (jspricelist != null && jspricelist.size() > 0) {
									for (int j = 0; j < jspricelist.size(); j++) {
										Object[] js = (Object[])jspricelist.get(j);
										double jspr = Double.valueOf(js[1].toString())*Double.valueOf(js[2].toString());//单条记录结算价格
										jsmont+=jspr;
									}									
									
								}
								jszb.setJsmont(jsmont);// 结算金额

								jszb.setTmont(Double.valueOf(jszbs[1].toString()) - jsmont);// 平台金额(总订单金额-结算金额)
								jszb.setPmont(jsmont);// 服务商金额

								jszb.setRzti(stdt);// 开始日期
								jszb.setLdti(etdt);// 结束日期
								jszb.setQrti(Tools.getTodayString());// 确认日期
								jszb.setStatus("22");// 状态
								if ("0".equals(titype)) {
									jszb.setTitype("01");// 日期类型(按下间日期）
								}
								if ("1".equals(titype)) {
									jszb.setTitype("02");// 日期类型（按消费日期）
								}
								session.save(jszb);// 保存总表数据
								String jsmxsql = "";

								jsmxsql = "select  tor.id.orid as orid,tor.mont as mont from TOrder tor,MOrder mor where  mor.orid = tor.id.orid  and  tor.ddzt='11' and tor.id.orid='"
										+ orid + "' and tor.id.iscenicid=" + jszbs[0];

								List jsmxlist = this.find(jsmxsql);
								if (jsmxlist != null && jsmxlist.size() > 0) {
									for (int j = 0; j < jsmxlist.size(); j++) {

										Object[] mxorder = (Object[]) jsmxlist.get(j);

										// 向结算明细中加入数据
										Jslist jslist = new Jslist();
										JslistId jslistId = new JslistId();
										jslistId.setOrid(orid);// 订单号
										jslistId.setPdno(jszbs[0].toString());// 服务商编号
										jslist.setId(jslistId);
										jslist.setMont(Double.valueOf(jszbs[1].toString()));// 金额
										jslist.setJszbseq(seq + 1);// 结算总表编号
										jslist.setStatus("22");// 状态

										session.save(jslist);
									}
								}
							}
						}

					}
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}finally{
			session.close();
		}
		return 1;
	}
	/**
	 * 根据服务商首先确认
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdno
	 * @param stdt
	 * @param etdt
	 * @param titype
	 * return:void
	 * Date:2011-11-14
	 */
	public int  updateOrderProvider(Long pdno,String stdt,String etdt,String titype){
//		Session session = (Session) getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			// 更改订单的状态为22（订单需后台二次审核）
			String msql = "";
			if ("0".equals(titype)) {// 按下单时间
				msql = "select m.orid from MOrder m,TOrder t where m.orid = t.id.orid and m.ddzt='11' and m.orda>='"
						+ stdt + "' and m.orda<='" + etdt + "' and t.id.iscenicid=" + pdno;
			} else if ("1".equals(titype)) {// 按消费时间
				msql = "select m.orid from MOrder m,TOrder t where m.orid = t.id.orid and m.ddzt='11' and t.dtstartdate>='"
						+ stdt + "' and t.dtstartdate<='" + etdt + "' and t.id.iscenicid=" + pdno;
			}
			List mlist = this.find(msql);
			if (mlist != null && mlist.size() > 0) {
				for (int i = 0; i < mlist.size(); i++) {
					String morder = (String) mlist.get(i);
					if (morder != null && !"".equals(morder)) {
						String morid = morder;// 订单号

						String tsql = " from TOrder t where t.ddzt='11' and t.id.orid='" + morid + "'and t.id.iscenicid="
								+ pdno;
						List tlist = this.find(tsql);
						if (tlist != null && tlist.size() > 0) {
							for (int k = 0; k < tlist.size(); k++) {
								TOrder torder = (TOrder) tlist.get(k);
								if (!"11".equals(torder.getDdzt())) {
									throw new Exception("订单状态不对，必有是已出票或已经对帐");
								}
								if ("11".equals(torder.getDdzt())) {
									torder.setDdzt("22");
									session.update(torder);// 保存更改

									String jszbhsql = "";
									// 向结算总表中加入数据
									if ("0".equals(titype)) {// 按下单时间
										jszbhsql = "select tor.id.iscenicid ,sum(tor.mont) as mont from TOrder tor,MOrder mor where tor.id.orid =mor.orid and tor.id.iscenicid="
												+ pdno
												+ " and tor.id.orid='"
												+ torder.getId().getOrid()
												+ "' and tor.ddzt='11' and mor.orda>='"
												+ stdt
												+ "' and mor.orda<='"
												+ etdt + "' group by tor.id.iscenicid";
									}
									if ("1".equals(titype)) {//按消费时间
										jszbhsql = "select tor.id.iscenicid ,sum(tor.mont) as mont from TOrder tor,MOrder mor where tor.id.orid =mor.orid and tor.id.iscenicid="
												+ pdno
												+ " and tor.id.orid='"
												+ torder.getId().getOrid()
												+ "' and tor.ddzt='11' and tor.dtstartdate>='"
												+ stdt
												+ "' and tor.dtstartdate<='"
												+ etdt + "' group by tor.id.iscenicid";
									}
									List jszblist = this.find(jszbhsql);

									if (jszblist != null && jszblist.size() > 0) {
										for (int j = 0; j < jszblist.size(); j++) {

											Object[] jszbs = (Object[]) jszblist.get(j);

											// 得到总表的编号最大值
											Long seq = 0L;
											seq = this.getMaxPk("seq", "Jszb");
											Jszb jszb = new Jszb();

											jszb.setSeq(seq + 1);// 编号
											jszb.setPdno(jszbs[0].toString());// 服务商编号
											jszb.setMont(Double.valueOf(jszbs[1].toString()));// 总金额
											String sql = "";
											if ("0".equals(titype)) {
												sql = "select ord.id.orid,ord.jsprice as jsprice,ord.numb as sumnumb from TOrderlist ord,TOrder tor,MOrder mor where ord.id.orid=tor.id.orid and  ord.id.iscenicid=tor.id.iscenicid and mor.orid = tor.id.orid and mor.orid = ord.id.orid and  tor.ddzt='11' and tor.id.iscenicid="
														+ pdno
														+ " and mor.orda='"
														+ stdt
														+ "' and mor.orda<='"
														+ etdt
														+ "' and ord.id.orid ='"+torder.getId().getOrid()+"' ";

											}
											if ("1".equals(titype)) {
												sql = "select ord.id.orid,ord.jsprice as jsprice,ord.numb as sumnumb from TOrderlist ord,TOrder tor,MOrder mor where ord.id.orid=tor.id.orid and  ord.id.iscenicid=tor.id.iscenicid and mor.orid = tor.id.orid and mor.orid = ord.id.orid and  tor.ddzt='11' and tor.id.iscenicid="
														+ pdno
														+ " and tor.dtstartdate='"
														+ stdt
														+ "' and tor.dtstartdate<='"
														+ etdt
														+ "' and ord.id.orid ='"+torder.getId().getOrid()+"' ";
											}
											List jspricelist = this.find(sql);
											double jsmont = 0;
											int sumnumb = 0;
											if (jspricelist != null && jspricelist.size() > 0) {
												for (int l = 0; l < jspricelist.size(); l++) {
													Object[] js = (Object[])jspricelist.get(l);
													double jspr = Double.valueOf(js[1].toString())*Double.valueOf(js[2].toString());//单条记录结算价格
													jsmont+=jspr;
													
												}
												
											}											
											jszb.setJsmont(jsmont);// 结算金额
											jszb.setTmont(Double.valueOf(jszbs[1].toString()) - jsmont);// 途马金额
											jszb.setPmont(jsmont);// 服务商金额

											jszb.setRzti(stdt);// 开始日期
											jszb.setLdti(etdt);// 结束日期
											jszb.setQrti(Tools.getTodayString());// 确认日期
											jszb.setStatus("22");// 状态
											if ("0".equals(titype)) {
												jszb.setTitype("01");// 日期类型(按下单日期）
											}
											if ("1".equals(titype)) {
												jszb.setTitype("02");// 日期类型（按消费日期）
											}
											this.save(jszb);// 保存总表数据

											String jsmxsql = "";
											if ("0".equals(titype)) {
												jsmxsql = "select  tor.id.orid as orid,tor.mont as mont from TOrder tor,MOrder mor where  mor.orid = tor.id.orid  and  tor.ddzt='11' and tor.id.iscenicid="
														+ pdno
														+ " and tor.id.orid='"
														+ torder.getId().getOrid()
														+ "' and mor.orda>='" + stdt + "' and mor.orda<='" + etdt + "'";
											}
											if ("1".equals(titype)) {
												jsmxsql = "select  tor.id.orid as orid,tor.mont as mont  from TOrder tor,MOrder mor where  mor.orid = tor.id.orid  and  tor.ddzt='11' and tor.id.iscenicid="
														+ pdno
														+ " and tor.id.orid='"
														+ torder.getId().getOrid()
														+ "' and tor.dtstartdate>='" + stdt + "' and tor.dtstartdate<='" + etdt + "'";

											}
											List jsmxlist = this.find(jsmxsql);
											if (jsmxlist != null && jsmxlist.size() > 0) {
												for (int m = 0; m < jsmxlist.size(); m++) {

													Object[] mxorder = (Object[]) jsmxlist.get(m);

													// 向结算明细中加入数据
													Jslist jslist = new Jslist();
													JslistId jslistId = new JslistId();
													jslistId.setOrid(mxorder[0].toString());// 订单号

													jslistId.setPdno(jszbs[0].toString());// 服务商编号
													jslist.setId(jslistId);
													jslist.setMont(Double.valueOf(mxorder[1].toString()));// 金额
													jslist.setJszbseq(seq + 1);// 结算总表编号
													jslist.setStatus("22");// 状态

													session.save(jslist); // 保存结算明细数据
												}
											}

										}
									}

								}
							}
						}

						// 根据订单号查询非此服务商的订单信息
						String strsql = " from TOrder t where t.id.orid='" + morid + "' and t.id.iscenicid <> " + pdno;
						List strlist = this.find(strsql);

						boolean flag = false;
						if (strlist != null && strlist.size() > 0) {

							for (int j = 0; j < strlist.size(); j++) {
								TOrder order = (TOrder) strlist.get(j);
								if ("22".equals(order.getDdzt())) {
									flag = true;
								} else {
									flag = false;
								}
							}
						} else {
							flag = true;
						}

						// 说明：如果T_order中存在此订单的所有状态都末为22时，则不改变M_order中的订单状态，否则改变
						if (flag) {
							String msqls = " from MOrder m where m.ddzt='11' and m.orid='" + morid + "'";
							List<MOrder> mlists = this.find(msqls);

							if (mlists != null && mlists.size() > 0) {
								for (MOrder morders : mlists) {
									if (!"11".equals(morders.getDdzt())) {
										throw new Exception("订单状态不对，必需是已出票或已经对帐");
									}
									if ("11".equals(morders.getDdzt())) {
										morders.setDdzt("22");
										session.update(morders);// 保存更改

									}
								}

							}
						}

					}
				}
			}

			String hsql = " from YOrder h where h.ddzt='11' and h.dtstartdate>='" + stdt + "' and h.dtstartdate<='" + etdt
					+ "' and h.id.iscenicid=" + pdno;
			List hlist = this.find(hsql);
			if (hlist != null && hlist.size() > 0) {
				for (int i = 0; i < hlist.size(); i++) {
					YOrder yorder = (YOrder) hlist.get(i);
					if (!"11".equals(yorder.getDdzt())) {
						throw new Exception("订单状态不对，必需是已出票或已经对帐");
					}
					if ("11".equals(yorder.getDdzt())) {
						yorder.setDdzt("22");
						session.update(yorder);// 保存更改
					}
				}

			}
		tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}
		return 1;
	}
	/**
	 * 获取主键最大值
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:int
	 * Date:2011-11-16
	 */
	public int getMaxSeq(String tablename,String column) {
		int seq = 0;
		String sql = "select max(h."+column+") from "+tablename+" h";
		List seqList = this.find(sql);
		if (seqList.size() > 0) {
			if (seqList.get(0) == null) {
				seq = 0;
			} else {
				seq = Integer.parseInt(seqList.get(0) == null ? "0" : seqList.get(0).toString());
			}
		}
		return seq;
	}
	/**
	 * 预付款保存，必须调用这个方法，这个方法维护预计明细与余额一至
	 */
	public int useryfksave(Useryfk yfk) {
//		Session session = (Session) getSession();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			// 计算当前余额
			Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, yfk.getUsid());
			if (vipbalance == null) {

				double ye = getInitsumjifen(yfk.getUsid());
				vipbalance = new Vipbalance();
				vipbalance.setUsid(yfk.getUsid());
				vipbalance.setAcctype("01");
				vipbalance.setPoint(ye);
			}
			vipbalance.setPoint(vipbalance.getPoint() + yfk.getPoint() * yfk.getYfklb());

			save(yfk);
			this.saveOrUpdate(vipbalance);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			session.close();
			return 0;
		}
		return 1;
	}
	
	/** 取用户的预付款余额 */
	public float getsumjifen(String usid) {
		// 计算当前余额
		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, usid);

		if (vipbalance == null) {
			double ye = getInitsumjifen(usid);
			vipbalance = new Vipbalance();
			vipbalance.setUsid(usid);
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
			save(vipbalance);
		}
		// 默认查询出来的list里存放的是一个Object数组，但是在这里list里存放的不再是默认的Object数组了，而是Map集合了
		float rc_float = vipbalance.getPoint().floatValue();

		// 除去提现未审核的金额
		List list = this.find("from Yfktoxj where ddzt='97' and usid='" + usid + "'");
		if (list != null && list.size() > 0) {
			list = this.find("select sum(mont) as mont from Yfktoxj where ddzt='97' and usid='" + usid + "'");
			rc_float = rc_float
					- (list != null && list.size() > 0 ? ((Double) list.get(0)).floatValue() : new Float(0));
		}

		return rc_float;

	}
	
	/** 取用户的预付款余额 */
	public float getInitsumjifen(String usid) {
		String hql = " select new map( sum ( yfk.point * yfk.yfklb ) as sumjifen ) From Useryfk yfk  where yfk.usid =  '"
				+ usid + "'";
		List list = this.find(hql);
		// 默认查询出来的list里存放的是一个Object数组，但是在这里list里存放的不再是默认的Object数组了，而是Map集合了
		float rc_float = 0;
		if (list.size() > 0) {
			// 一条记录里所有的字段值都是map里的一个元素,key是字符串0,1,2,3....，value是字段值
			// 如果将hql改为：String hql =
			// " select new map(name as username,passwd as password) from Users";,那么key将不是字符串0,1,2...了，而是"username","password"了
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				HashMap map = (HashMap) iterator2.next();
				Double sumjifen = (Double) map.get("sumjifen"); // 取到退订单编号
				if (sumjifen == null) {
					sumjifen = new Double(0D);
				} else {
					rc_float = sumjifen.floatValue();
				}
			}
		} else {
			rc_float = 0;
		}
		return rc_float;
	}
	
	
}

