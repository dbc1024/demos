package com.ectrip.ticket.warehouse.dao;

import java.util.List;
import java.util.regex.Pattern;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.warehouse.Iomstocksbilldetails;
import com.ectrip.ticket.warehouse.dao.idao.IHouseDAO;

public class HouseDAO extends GenericDao implements IHouseDAO {

	/**
	 * *
	 * Describe:获取票号前缀和票代码
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#ticketRuleView(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public String ticketRuleView(Long itickettypeid){
		StringBuffer b=new StringBuffer();
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		if(edmticket!=null){
			String sql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				if(ticketrule.getIntons2()!=null&&ticketrule.getIntons2()!=0){
					String start=ticketrule.getSzticketnoprefix();
					b.append(start);
				}

				StringBuffer code=new StringBuffer();
				code.append(edmticket.getSzticketprintcode());
				Integer zc=ticketrule.getItickettypecodepos();
				if(edmticket.getSzticketprintcode().length()<zc.intValue()){
					for (int x = 0; x <zc - edmticket.getSzticketprintcode().length(); x++) {
						code.append("0");
					}
				}

				b.append(code);
			}
		}
		return b.toString();
	}

	/**
	 * *
	 * Describe:判断票号输入是否正确
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#getShowtickrule(java.lang.String, java.lang.Long)
	 * @param ticketcode  票号
	 * @param itickettypeid  产品ID
	 * @return
	 * @author lijingrui
	 * Date:2012-10-16
	 */
	public boolean reShowtickrule(String ticketcode, Long itickettypeid) {
		if(ticketcode!=null&&ticketcode!=""&&itickettypeid!=null){
			Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
			if(edmticket!=null){
				String sql=" from Edmticketnoruletab rule where rule.byisuse=1 and rule.iscenicid="+edmticket.getIscenicid();
				List lst=this.find(sql);
				if(lst!=null&&lst.size()>0){
					Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
					if(ticketcode.length()!=ticketrule.getIticketnolen()){
						return true; //输入的票总长度不对
					}else{
						//获取票号前缀
						if(ticketrule.getIntons2()!=null&&ticketrule.getIntons2()!=0){
							String start=ticketcode.substring(0, ticketrule.getIntons2());
							if(!ticketrule.getSzticketnoprefix().equals(start)){
								return true;
							}
						}

						StringBuffer code=new StringBuffer();
						code.append(edmticket.getSzticketprintcode());
						//获取票号中的票代码信息，并比较
						String tkcode=ticketcode.substring(ticketrule.getIntons2(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos());
						Integer zc=ticketrule.getItickettypecodepos();
						if(edmticket.getSzticketprintcode().length()<zc.intValue()){
							for (int b = 0; b <zc - edmticket.getSzticketprintcode().length(); b++) {
								code.append("0");
							}
						}else if(edmticket.getSzticketprintcode().length()>zc.intValue()){
							return true;//票号规则与编码冲突
						}
						if(code.toString().equals(tkcode)){
							String lsh=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
							Long serials=null;
							if(ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1){
								Pattern p = Pattern.compile("^[0-9]*$");
								boolean b = p.matcher(lsh).matches();
								if (b == false) {
									return true;//您输入的流水号有误
								}
								serials=Long.parseLong(lsh);
							}else{
								serials=Tools.Text36ToConvert(lsh);
							}

							if(serials==null){
								return true;
							}else{
								Pattern p = Pattern.compile("^[0-9]*$");
								boolean b = p.matcher(serials.toString()).matches();
								if (b == false) {
									return true;//您输入的流水号有误
								}else{
									//判断后缀
									if(ticketrule.getSzticketnosuffix()!=null&&!"".equals(ticketrule.getSzticketnosuffix())&&!"0".equals(ticketrule.getSzticketnosuffix())){
										String endup=ticketcode.substring(ticketcode.length()-Integer.parseInt(ticketrule.getSzticketnosuffix()),ticketcode.length() );
										if(ticketrule.getSztron1().equals(endup)){
											return false;//票号输入正确
										}else{
											return true;
										}
									}else{
										return false;//票号输入正确
									}

								}

							}
						}else{
							return true;//输入的票号中票代码有误
						}


					}
				}else{
					return true; //服务商下的票号规则未设置
				}

			}else{
				return true;  //产品ID不能为空
			}
		}else{
			return true;
		}

	}

	/**
	 * *
	 * Describe:判断票号是否存在
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseDAO#retrieWarehouse(com.ectrip.model.warehouse.Iomstocksbilldetails)
	 * @param stocks
	 * @return
	 * @author lijingrui
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


	/**
	 * *
	 * Describe:获取截止票号
	 * @see com.ectrip.system.warehouse.service.iservice.IWarehouseService#getviewupendcode(java.lang.String, java.lang.Long)
	 * @param ticketcode
	 * @param iamount
	 * @return
	 * @author lijingrui
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