package com.ectrip.ticket.stocks.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.stock.Stocksalevolumebycustomtab;
import com.ectrip.ticket.model.stock.Stocksalevolumetab;
import com.ectrip.ticket.model.stock.Stocksticketwarebycustomtab;
import com.ectrip.ticket.model.stock.Stocksticketwaretab;
import com.ectrip.ticket.stocks.dao.idao.IStocksWareDAO;
import org.springframework.stereotype.Repository;

@Repository
public class StocksWareDAO  extends GenericDao implements IStocksWareDAO{

	/**
	 * *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @see com.ectrip.system.stocks.dao.idao.IStocksWareDAO#checkTicketware(java.util.List)
	 * @param list
	 * @return
	 * @author lijingrui
	 * Date:2013-9-3
	 */
	public int checkTicketware(List<Object[]> list){
		try{
			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String hsql=" from Stocksticketwaretab s where s.objtype=0 and s.pid="+iscenicid;
					List lst=this.find(hsql);
					if(lst!=null&&lst.size()>0){
						//服务商库存类型   先获取某服务商下的要销售的所有产品数量
						Long sumnumber=numb;
						for(int y=x+1;y<list.size();y++){
							Object [] c=(Object [])list.get(y);
							Long scenid=Long.parseLong(c[0].toString());
							Long shuliang=Long.parseLong(c[2].toString());
							if(scenid==iscenicid&&startdata.equals(c[3].toString())){
								sumnumber+=shuliang;
							}
						}

						if(x>0){  //循环下已经判断的同一服务商同游览日期的产品数量 跳过
							boolean b=false;
							for(int z=0;z<list.size();z++){
								Object [] c=(Object [])list.get(z);
								Long scenid=Long.parseLong(c[0].toString());
								if(scenid==iscenicid&&startdata.equals(c[3].toString())){
									b=true;
									break;
								}
							}
							if(b){
								continue;
							}
						}

						for(int i=0;i<lst.size();i++){
							Stocksticketwaretab stockticket=(Stocksticketwaretab)lst.get(i);
							String sql=new String();
							if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where seq="+stockticket.getSeq();
								//	continue;
							}else if(stockticket.getSttype().equals("0002")){   //天库存
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();

							}else if(stockticket.getSttype().equals("0003")){   //年库存
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
							}
							List salelist=this.find(sql);
							if(salelist!=null&&salelist.size()>0){
								Long ticknumb=Long.parseLong(this.find(sql).get(0).toString());
								Long nums=stockticket.getStocknumber()-(sumnumber+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
								if(nums<0){  //库存数量不足
									return 0;
								}
							}else{
								return 1;
							}

						}

					}else{
						String msql=" from Stocksticketwaretab s where s.objtype=1 and s.pid="+itickettypeid;
						List ticketList=this.find(msql);
						if(ticketList!=null&&ticketList.size()>0){
							for(int i=0;i<ticketList.size();i++){
								Stocksticketwaretab stockticket=(Stocksticketwaretab)ticketList.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where seq="+stockticket.getSeq();
									//	continue;
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();

								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
								}

								List salelist=this.find(sql);
								if(salelist!=null&&salelist.size()>0){
									Long ticknumb=Long.parseLong(this.find(sql).get(0).toString());
									Long nums=stockticket.getStocknumber()-(numb+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
									if(nums<0){  //库存数量不足
										return 0;
									}
								}else{
									return 1;
								}

							}
						}
					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return 1;
	}

	/**
	 * *
	 * Describe:保存当日库存销售信息
	 * @see com.ectrip.system.stocks.dao.idao.IStocksWareDAO#saveStockvolum(java.util.List)
	 * @param list
	 * @return
	 * @author lijingrui
	 * Date:2013-9-2
	 */
	public void saveStockvolum(List<Object[]> list){
		try{

			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String hsql=" from Stocksticketwaretab s where s.objtype=0 and s.pid="+iscenicid;
					List lst=this.find(hsql);
					if(lst!=null&&lst.size()>0){
						//服务商库存类型

						for(int i=0;i<lst.size();i++){
							Stocksticketwaretab stockticket=(Stocksticketwaretab)lst.get(i);
							String sql=new String();
							if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
								sql=" from Stocksalevolumetab where seq="+stockticket.getSeq();
							}else if(stockticket.getSttype().equals("0002")){   //天库存
								sql=" from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();
							}else if(stockticket.getSttype().equals("0003")){   //年库存
								sql=" from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
							}

							List volumlist=this.find(sql);
							if(volumlist!=null&&volumlist.size()>0){
								Stocksalevolumetab stock=(Stocksalevolumetab)volumlist.get(0);
								stock.setStocknumb(stock.getStocknumb()+numb);
								this.update(stock);
							}else{
								Stocksalevolumetab stock=new Stocksalevolumetab();
								Long maxid=this.getMaxPk("sid", "Stocksalevolumetab");
								stock.setSid(maxid+1);
								stock.setSeq(stockticket.getSeq());
								stock.setDtmakedate(startdata);
								stock.setStocknumb(numb);
								this.save(stock);
							}
						}

					}else{
						String msql=" from Stocksticketwaretab s where s.objtype=1 and s.pid="+itickettypeid;
						List ticketList=this.find(msql);
						if(ticketList!=null&&ticketList.size()>0){
							for(int i=0;i<ticketList.size();i++){
								Stocksticketwaretab stockticket=(Stocksticketwaretab)ticketList.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql=" from Stocksalevolumetab where seq="+stockticket.getSeq();
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql=" from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();
								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql=" from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
								}

								List volumlist=this.find(sql);
								if(volumlist!=null&&volumlist.size()>0){
									Stocksalevolumetab stock=(Stocksalevolumetab)volumlist.get(0);
									stock.setStocknumb(stock.getStocknumb()+numb);
									this.update(stock);
								}else{
									Stocksalevolumetab stock=new Stocksalevolumetab();
									Long maxid=this.getMaxPk("sid", "Stocksalevolumetab");
									stock.setSid(maxid+1);
									stock.setSeq(stockticket.getSeq());
									stock.setDtmakedate(startdata);
									stock.setStocknumb(numb);
									this.save(stock);
								}

							}
						}
					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * *
	 * Describe:旅行社用户判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#checkTicketwareCustom(java.util.List, java.lang.String)
	 * @param list  服务商   产品    数量     开始日期     截止日期
	 * @param usid  下单用户或者操作员所在的分社
	 * @return
	 * @author lijingrui
	 * Date:2014-12-26
	 */
	public int checkTicketwareCustom(List<Object[]> list,String usid){
		//接待用户是否可超量销售   isa   1 可以  0 不可以
		//接待用户不配库存是否允许销售  isb  1 可以  0 不可以
		Sysparv5 sys1=(Sysparv5)this.get(Sysparv5.class, new Sysparv5Id("JDKC", "0001"));
		Custom stom=(Custom)this.get(Custom.class, usid);

		try{
			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String tsql=" from Stocksticketwarebycustomtab s where s.usid='"+usid+"' ";
					List tList=this.find(tsql);
					boolean tk=false;
					//判断用户是否存有库存，如果有库存判断是何库存类型，如果是天库存 需要判断日期段
					if(tList!=null&&tList.size()>0){
						Stocksticketwarebycustomtab customstock=(Stocksticketwarebycustomtab)tList.get(0);
						if(customstock!=null&&customstock.getSttype().equals("0002")){  //天库存
							tk=true;
						}

						String hsql=" from Stocksticketwarebycustomtab s where s.objtype=0 and s.usid='"+usid+"' and s.pid="+iscenicid;
						if(tk){
							hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
						}
						List lst=this.find(hsql);
						if(lst!=null&&lst.size()>0){
							if(stom.getIbusinessid()==3){
								if(sys1.getIsa()==1){
									continue;
								}
							}

							//服务商库存类型   先获取某服务商下的要销售的所有产品数量
							Long sumnumber=numb;
							for(int y=x+1;y<list.size();y++){
								Object [] c=(Object [])list.get(y);
								Long scenid=Long.parseLong(c[0].toString());
								Long shuliang=Long.parseLong(c[2].toString());
								if(scenid==iscenicid&&startdata.equals(c[3].toString())){
									sumnumber+=shuliang;
								}
							}

							if(x>0){  //循环下已经判断的同一服务商同游览日期的产品数量 跳过
								boolean b=false;
								for(int z=0;z<list.size();z++){
									Object [] c=(Object [])list.get(z);
									Long scenid=Long.parseLong(c[0].toString());
									if(scenid==iscenicid&&startdata.equals(c[3].toString())){
										b=true;
										break;
									}
								}
								if(b){
									continue;
								}
							}

							for(int i=0;i<lst.size();i++){
								Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)lst.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";

								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}
								List salelist=this.find(sql);
								if(salelist!=null&&salelist.size()>0){
									Long ticknumb=Long.parseLong(this.find(sql).get(0).toString());
									Long nums=stockticket.getStocknumber()-(sumnumber+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
									if(nums<0){  //库存数量不足
										return 0;
									}

								}else{
									return 1;
								}

							}

						}else{
							String msql=" from Stocksticketwarebycustomtab s where s.objtype=1 and s.usid='"+usid+"' and s.pid="+itickettypeid;
							if(tk){
								hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
							}
							List ticketList=this.find(msql);
							if(ticketList!=null&&ticketList.size()>0){
								if(stom.getIbusinessid()==3){
									if(sys1.getIsa()==1){
										continue;
									}
								}

								for(int i=0;i<ticketList.size();i++){
									Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)ticketList.get(i);
									String sql=new String();
									if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";

									}else if(stockticket.getSttype().equals("0002")){   //天库存
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";

									}else if(stockticket.getSttype().equals("0003")){   //年库存
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
									}

									List salelist=this.find(sql);
									if(salelist!=null&&salelist.size()>0){
										Long ticknumb=Long.parseLong(this.find(sql).get(0).toString());
										Long nums=stockticket.getStocknumber()-(numb+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
										if(nums<0){  //库存数量不足
											return 0;
										}
									}else{
										return 1;
									}

								}
							}else{

								if(stom.getIbusinessid()==3){
									if(sys1.getIsb()==1){  //可以销售
										return 1;
									}else{
										return 2;  //不允许销售
									}
								}

							}
						}

					}else{
						if(stom.getIbusinessid()==3){
							if(sys1.getIsb()==1){  //可以销售
								return 1;
							}else{
								return 2;  //不允许销售
							}
						}
					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return 1;
	}

	/**
	 * *
	 * Describe:保存旅行社用户库存销售信息
	 * @see com.ectrip.system.stocks.service.iservice.IStocksWareService#saveStockVolumeCustom(java.util.List, java.lang.String)
	 * @param list
	 * @param usid  下单用户或者操作员所在的分社
	 * @author lijingrui
	 * Date:2014-12-26
	 */
	public boolean saveStockVolumeCustom(List<Object[]> list,String usid){
		boolean b=false;
		try{

			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String tsql=" from Stocksticketwarebycustomtab s where s.usid='"+usid+"' ";
					List tList=this.find(tsql);
					boolean tk=false;
					//判断用户是否存有库存，如果有库存判断是何库存类型，如果是天库存 需要判断日期段
					if(tList!=null&&tList.size()>0){
						Stocksticketwarebycustomtab customstock=(Stocksticketwarebycustomtab)tList.get(0);
						if(customstock!=null&&customstock.getSttype().equals("0002")){  //永久
							tk=true;
						}

						String hsql=" from Stocksticketwarebycustomtab s where s.objtype=0 and s.usid='"+usid+"' and s.pid="+iscenicid;
						if(tk){
							hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
						}
						List lst=this.find(hsql);
						if(lst!=null&&lst.size()>0){
							//服务商库存类型
							for(int i=0;i<lst.size();i++){
								Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)lst.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql=" from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql=" from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql=" from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}

								List volumlist=this.find(sql);
								if(volumlist!=null&&volumlist.size()>0){
									Stocksalevolumebycustomtab stock=(Stocksalevolumebycustomtab)volumlist.get(0);
									stock.setStocknumb(stock.getStocknumb()+numb);
									this.update(stock);
								}else{
									Stocksalevolumebycustomtab stock=new Stocksalevolumebycustomtab();
									Long maxid=this.getMaxPk("sid", "Stocksalevolumebycustomtab");
									stock.setSid(maxid+1);
									stock.setSeq(stockticket.getSeq());
									stock.setDtmakedate(startdata);
									stock.setUsid(usid);
									stock.setStocknumb(numb);
									this.save(stock);
								}
							}

							b=true;
						}else{
							String msql=" from Stocksticketwarebycustomtab s where s.objtype=1 and s.usid='"+usid+"' and s.pid="+itickettypeid;
							if(tk){
								hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
							}
							List ticketList=this.find(msql);
							if(ticketList!=null&&ticketList.size()>0){
								for(int i=0;i<ticketList.size();i++){
									Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)ticketList.get(i);
									String sql=new String();
									if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
										sql=" from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";
									}else if(stockticket.getSttype().equals("0002")){   //天库存
										sql=" from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
									}else if(stockticket.getSttype().equals("0003")){   //年库存
										sql=" from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
									}

									List volumlist=this.find(sql);
									if(volumlist!=null&&volumlist.size()>0){
										Stocksalevolumebycustomtab stock=(Stocksalevolumebycustomtab)volumlist.get(0);
										stock.setStocknumb(stock.getStocknumb()+numb);
										this.update(stock);
									}else{
										Stocksalevolumebycustomtab stock=new Stocksalevolumebycustomtab();
										Long maxid=this.getMaxPk("sid", "Stocksalevolumebycustomtab");
										stock.setSid(maxid+1);
										stock.setSeq(stockticket.getSeq());
										stock.setDtmakedate(startdata);
										stock.setStocknumb(numb);
										stock.setUsid(usid);
										this.save(stock);
									}

								}

								b=true;
							}
						}

					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return b;
	}

	/**
	 *
	 * Describe:旅行社用户判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @param usid 下单用户或者操作员所在的分社
	 * @return
	 * return:int
	 * Date:2014-12-26
	 */
	public Map checkTicketwareCustom2(List<Object[]> list,String usid){
		Map map=new HashMap();

		//接待用户是否可超量销售   isa   1 可以  0 不可以
		//接待用户不配库存是否允许销售  isb  1 可以  0 不可以
		Sysparv5 sys1=(Sysparv5)this.get(Sysparv5.class, new Sysparv5Id("JDKC", "0001"));
		Custom stom=(Custom)this.get(Custom.class, usid);

		try{
			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String tsql=" from Stocksticketwarebycustomtab s where s.usid='"+usid+"' ";
					List tList=this.find(tsql);
					boolean tk=false;
					//判断用户是否存有库存，如果有库存判断是何库存类型，如果是天库存 需要判断日期段
					if(tList!=null&&tList.size()>0){
						Stocksticketwarebycustomtab customstock=(Stocksticketwarebycustomtab)tList.get(0);
						if(customstock!=null&&customstock.getSttype().equals("0002")){  //天库存
							tk=true;
						}

						String hsql=" from Stocksticketwarebycustomtab s where s.objtype=0 and s.usid='"+usid+"' and s.pid="+iscenicid;
						if(tk){
							hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
						}
						List lst=this.find(hsql);
						if(lst!=null&&lst.size()>0){

							Esbscenicareatab esbscens=(Esbscenicareatab)this.get(Esbscenicareatab.class, iscenicid);

							if(stom.getIbusinessid()==3){
								if(sys1.getIsa()==1){
									continue;
								}
							}

							//服务商库存类型   先获取某服务商下的要销售的所有产品数量
							Long sumnumber=numb;
							for(int y=x+1;y<list.size();y++){
								Object [] c=(Object [])list.get(y);
								Long scenid=Long.parseLong(c[0].toString());
								Long shuliang=Long.parseLong(c[2].toString());
								if(scenid==iscenicid&&startdata.equals(c[3].toString())){
									sumnumber+=shuliang;
								}
							}

							if(x>0){  //循环下已经判断的同一服务商同游览日期的产品数量 跳过
								boolean b=false;
								for(int z=0;z<list.size();z++){
									Object [] c=(Object [])list.get(z);
									Long scenid=Long.parseLong(c[0].toString());
									if(scenid==iscenicid&&startdata.equals(c[3].toString())){
										b=true;
										break;
									}
								}
								if(b){
									continue;
								}
							}

							for(int i=0;i<lst.size();i++){
								Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)lst.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";

								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
								}
								List salelist=this.find(sql);
								if(salelist!=null&&salelist.size()>0){
									Long ticknumb=Long.parseLong(salelist.get(0).toString());
									Long nums=stockticket.getStocknumber()-(sumnumber+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
									if(nums<0){  //库存数量不足
										map.put("type", 0);
										Long synumb=stockticket.getStocknumber()-ticknumb;
										map.put("result", esbscens.getSzscenicname()+"的剩余库存量为"+synumb);
										return map;
									}

								}else{
									map.put("type", 1);
									map.put("result", null);
									return map;
								}

							}

						}else{
							String msql=" from Stocksticketwarebycustomtab s where s.objtype=1 and s.usid='"+usid+"' and s.pid="+itickettypeid;
							if(tk){
								hsql+=" and s.starttime<='"+startdata+"' and s.endtime>='"+startdata+"' ";
							}
							List ticketList=this.find(msql);
							if(ticketList!=null&&ticketList.size()>0){
								if(stom.getIbusinessid()==3){
									if(sys1.getIsa()==1){
										continue;
									}
								}

								Edmtickettypetab edmticket=(Edmtickettypetab)this.get(Edmtickettypetab.class, itickettypeid);

								for(int i=0;i<ticketList.size();i++){
									Stocksticketwarebycustomtab stockticket=(Stocksticketwarebycustomtab)ticketList.get(i);
									String sql=new String();
									if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where seq="+stockticket.getSeq()+" and usid='"+usid+"'";

									}else if(stockticket.getSttype().equals("0002")){   //天库存
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";

									}else if(stockticket.getSttype().equals("0003")){   //年库存
										sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumebycustomtab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq()+" and usid='"+usid+"'";
									}

									List salelist=this.find(sql);
									if(salelist!=null&&salelist.size()>0){
										Long ticknumb=Long.parseLong(salelist.get(0).toString());
										Long nums=stockticket.getStocknumber()-(numb+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
										if(nums<0){  //库存数量不足
											map.put("type", 0);
											Long synumb=stockticket.getStocknumber()-ticknumb;
											map.put("result", edmticket.getSztickettypename()+"的剩余库存量为"+synumb);
											return map;
										}
									}else{
										map.put("type", 1);
										map.put("result", null);
										return map;
									}

								}
							}else{

								if(stom.getIbusinessid()==3){
									if(sys1.getIsb()==1){  //可以销售
										map.put("type", 1);
										map.put("result", "接待用户不配库存允许销售");
										return map;
									}else{
										map.put("type", 2);
										map.put("result", "接待用户不配库存不允许销售");
										return map;
									}
								}

							}
						}

					}else{
						if(stom.getIbusinessid()==3){
							if(sys1.getIsb()==1){  //可以销售
								map.put("type", 1);
								map.put("result", "接待用户不配库存允许销售");
								return map;
							}else{
								map.put("type", 2);
								map.put("result", "接待用户不配库存不允许销售");
								return map;
							}
						}
					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		map.put("type", 1);
		map.put("result", null);

		return map;
	}

	/**
	 *
	 * Describe:判断网上订单或者窗口售票时销售的产品数量是否超量
	 * @author:lijingrui
	 * @param list 服务商   产品    数量     开始日期     截止日期
	 * @return
	 * return:int
	 * Date:2013-8-30
	 */
	public Map checkTicketware2(List<Object[]> list){
		Map map=new HashMap();

		try{
			if(list!=null&&list.size()>0){
				for(int x=0;x<list.size();x++){
					Object [] obj=(Object [])list.get(x);
					Long iscenicid=Long.parseLong(obj[0].toString());
					Long itickettypeid=Long.parseLong(obj[1].toString());
					Long numb=Long.parseLong(obj[2].toString());
					String startdata=obj[3].toString();
					String enddata=obj[4].toString();

					String hsql=" from Stocksticketwaretab s where s.objtype=0 and s.pid="+iscenicid;
					List lst=this.find(hsql);
					if(lst!=null&&lst.size()>0){

						Esbscenicareatab esbscens=(Esbscenicareatab)this.get(Esbscenicareatab.class, iscenicid);

						//服务商库存类型   先获取某服务商下的要销售的所有产品数量
						Long sumnumber=numb;
						for(int y=x+1;y<list.size();y++){
							Object [] c=(Object [])list.get(y);
							Long scenid=Long.parseLong(c[0].toString());
							Long shuliang=Long.parseLong(c[2].toString());
							if(scenid==iscenicid&&startdata.equals(c[3].toString())){
								sumnumber+=shuliang;
							}
						}

						if(x>0){  //循环下已经判断的同一服务商同游览日期的产品数量 跳过
							boolean b=false;
							for(int z=0;z<list.size();z++){
								Object [] c=(Object [])list.get(z);
								Long scenid=Long.parseLong(c[0].toString());
								if(scenid==iscenicid&&startdata.equals(c[3].toString())){
									b=true;
									break;
								}
							}
							if(b){
								continue;
							}
						}

						for(int i=0;i<lst.size();i++){
							Stocksticketwaretab stockticket=(Stocksticketwaretab)lst.get(i);
							String sql=new String();
							if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where seq="+stockticket.getSeq();
								//	continue;
							}else if(stockticket.getSttype().equals("0002")){   //天库存
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();

							}else if(stockticket.getSttype().equals("0003")){   //年库存
								sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
							}
							List salelist=this.find(sql);
							if(salelist!=null&&salelist.size()>0){
								Long ticknumb=Long.parseLong(salelist.get(0).toString());
								Long nums=stockticket.getStocknumber()-(sumnumber+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
								if(nums<0){  //库存数量不足
									map.put("type", 0);
									Long synumb=stockticket.getStocknumber()-ticknumb;
									map.put("result", esbscens.getSzscenicname()+"的剩余库存量为"+synumb);
									return map;
								}
							}else{
								map.put("type", 1);
								map.put("result", null);
								return map;
							}

						}

					}else{
						String msql=" from Stocksticketwaretab s where s.objtype=1 and s.pid="+itickettypeid;
						List ticketList=this.find(msql);
						if(ticketList!=null&&ticketList.size()>0){

							Edmtickettypetab edmticket=(Edmtickettypetab)this.get(Edmtickettypetab.class, itickettypeid);

							for(int i=0;i<ticketList.size();i++){
								Stocksticketwaretab stockticket=(Stocksticketwaretab)ticketList.get(i);
								String sql=new String();
								if(stockticket!=null&&stockticket.getSttype().equals("0001")){  //永久
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where seq="+stockticket.getSeq();
									//	continue;
								}else if(stockticket.getSttype().equals("0002")){   //天库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where dtmakedate='"+startdata+"' and seq="+stockticket.getSeq();

								}else if(stockticket.getSttype().equals("0003")){   //年库存
									sql="select nvl(sum(stocknumb),0) as numb from Stocksalevolumetab where substr(dtmakedate,1,4)='"+Tools.getDays().substring(0, 4)+"' and seq="+stockticket.getSeq();
								}

								List salelist=this.find(sql);
								if(salelist!=null&&salelist.size()>0){
									Long ticknumb=Long.parseLong(this.find(sql).get(0).toString());
									Long nums=stockticket.getStocknumber()-(numb+ticknumb); //库存数量-(已销售的数量和正准备销售的数量）
									if(nums<0){  //库存数量不足
										map.put("type", 0);
										Long synumb=stockticket.getStocknumber()-ticknumb;
										map.put("result", edmticket.getSztickettypename()+"的剩余库存量为"+synumb);
										return map;
									}
								}else{
									map.put("type", 1);
									map.put("result", null);
									return map;
								}

							}
						}
					}

				}
			}

		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		map.put("type", 1);
		map.put("result", null);

		return map;
	}

}
