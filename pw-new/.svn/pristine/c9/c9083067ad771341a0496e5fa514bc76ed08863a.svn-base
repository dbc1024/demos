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
import com.ectrip.ticket.warehouse.dao.idao.IWarehouseChangeDAO;

public class WarehouseChangeDAO extends GenericDao implements IWarehouseChangeDAO {

	/***
	 * Describe:根据仓库ID查找仓库有的票类型
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#getListTickByihouse(java.lang.Long)
	 * @param ihouseid 仓库ID
	 * @return List
	 * @author ChaoYuHang
	 * Date:2012-7-16
	 */
	public List getListTickByihouse(Long ihouseid){
		StringBuffer hql=new StringBuffer();
		StringBuffer json=new StringBuffer();
		//查询仓库中有的单据类型的hql
		hql.append("select distinct new map(ioms.itickettypeid as id,edm.sztickettypename as name) " +
				"from Iomstationticketdetails ioms ,Edmtickettypetab edm where ioms.iwarehouseid="+ihouseid
				+" and edm.itickettypeid=ioms.itickettypeid  order by ioms.itickettypeid ");
		List list_Type = this.find(hql.toString());
		return list_Type;
	}

	/***
	 * Describe:  根据仓库ID和票ID详细该票的相关信息
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#getDetailTicks(java.lang.Long, java.lang.Long)
	 * @param iwarehouseid 仓库ID
	 * @param itickettypeid 票ID
	 * @return  票对象List
	 * @author ChaoYuHang
	 * Date:2012-7-16
	 */
	public List getDetailTicks(Long iwarehouseid,Long itickettypeid) {
		StringBuffer hql=new StringBuffer();
		//查询单据详细信息的hql
		hql.append("select new map(ioms.idetailsid as idetailsid,ioms.itickettypeid as id,edm.sztickettypename as name," +
				"ioms.szstartticketcode as startcode,ioms.szendticketcode as endcode ,ioms.iamount as iamount) "
				+"from Iomstationticketdetails ioms ,Edmtickettypetab edm where ioms.iwarehouseid="+iwarehouseid+
				" and edm.itickettypeid=ioms.itickettypeid"+" and ioms.itickettypeid="+itickettypeid);
		List list_Detail=this.find(hql.toString());
		return list_Detail;
	}

	/***
	 * Describe: 获得票规则的属性
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#getTicketRule(java.lang.Long)
	 * @param itickettypeid 票的ID
	 * @return  票规则对象List
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public List getTicketRule(Long itickettypeid) {
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		StringBuffer hql=new StringBuffer();
		//查询票规则的hql
		hql.append("select new map(trule.iticketnolen as iticketnolen,trule.szticketnoprefix as szticketnoprefix,ttype.sztickettypecode as sztickettypecode,"
				+"trule.itickettypecodepos as itickettypecodepos,trule.iserialnopos as iserialnopos,trule.iserialnolen as iserialnolen,trule.intons2 as startlength)");
		hql.append(" from Edmticketnoruletab trule,Edmtickettypetab ttype where ttype.itickettypeid="+itickettypeid);
		hql.append(" and trule.iscenicid="+edmticket.getIscenicid()+" and trule.byisuse=1");
		List list_Rule = list_Rule = this.find(hql.toString());
		return list_Rule;

	}

	/***
	 * Describe: 检查流水号是否存在非数字
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#checkIserial(java.lang.String, java.lang.Long)
	 * @param ticketcode  票号
	 * @param itickettypeid 票类型ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkIserial(String ticketcode,Long itickettypeid){
		boolean b=false;
		//流水号改为36进制，该方法废除     2012.08.24
//		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
//		StringBuffer result=new StringBuffer();
//		if (edmticket!=null) {
//			//查询票所对应的服务商的票规则
//			String sql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
//			List lst=this.find(sql);
//			if(lst!=null&&lst.size()>0){
//				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
//				//起始流水号
//				String startCode=ticketcode.substring(ticketrule.getIserialnopos()-1, ticketrule.getIserialnopos()+ticketrule.getIserialnolen()-1);
//				Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
//				//判断流水号是否存在非数字
//				if(p.matcher(startCode).matches()){
//					b=false;
//				}else{
//					b=true;
//				}
//			}
//		}
		return b;
	}
	/***
	 * Describe: 根据起始票号和票数量计算票的截止票号
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#reTicketEndcode(java.lang.String, java.lang.Long, java.lang.Long)
	 * @param ticketcode 票的起始票号
	 * @param iamount    票的数量
	 * @param itickettypeid 票类型ID
	 * @return  票的截止票号
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public String reTicketEndcode(String ticketcode, Long iamount,Long itickettypeid){
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		StringBuffer result=new StringBuffer();
		if (edmticket!=null) {
			//查询票所对应的服务商的票规则
			String sql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				Long temp=0l;
				if(ticketrule.getIntons2()==null)
				{
					ticketrule.setIntons2(0);
				}
				//起始流水号
				String startCode=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				result.append(ticketcode.substring(0, ticketrule.getIntons2()+ticketrule.getItickettypecodepos()));
				//后缀
				String endCode=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				//把起始流水号从36进制转换成Long类型
				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					temp=Long.parseLong(startCode);
				}else{
					temp=Tools.Text36ToConvert(startCode);
				}

				//流水号长度
				int iserialnolen=ticketrule.getIserialnolen();
				//后缀长度(后缀暂时不管)
				//int backlen=ticketrule.getIticketnolen()-ticketrule.getIserialnopos()-ticketrule.getIserialnolen()+1;
				//后缀需要补零的数量
				//int size_zero_back=backlen-endCode.length();
				//起始流水号+数量
				Long result_number=temp+iamount-1;


				//========为流水号和后缀增加零
				int size_zero=0;
				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					size_zero=iserialnolen-String.valueOf(result_number).length();
					for (int i = 0; i < size_zero; i++) {
						result.append("0");
					}
					result.append(result_number);
				}else{
					size_zero=iserialnolen-Tools.ConvertTo36Text(Long.parseLong(result_number.toString()), 0).length();
					for (int i = 0; i < size_zero; i++) {
						result.append("0");
					}
					//十进制流水号转换成36进制的票号
					result.append(Tools.ConvertTo36Text(result_number, 0));
				}

//				for (int i = 0; i < size_zero_back; i++) {
//					result.append("0");
//				}
				result.append(endCode);
			}
		}
//		System.out.println(result);
		return result.toString();
	}

	/***
	 * Describe: 根据起始票号和截止票号计算数量
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#reIamountforStartCode(java.lang.String, java.lang.String, java.lang.Long)
	 * @param startticketcode 起始票号
	 * @param endticketcode 截止票号
	 * @param itickettypeid 票类型ID
	 * @return   票数量
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public String reIamountforStartCode(String startticketcode,String endticketcode,Long itickettypeid){
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		StringBuffer result=new StringBuffer();
		int iamount=0;
		if (edmticket!=null) {
			//查询票规则
			String sql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				if(ticketrule.getIntons2()==null)
				{
					ticketrule.setIntons2(0);
				}
				//起始流水号
				String startCode=startticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				//截止流水号
				String endCode=endticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

				//流水号从36进制转换成Long类型
				Long start=0l;
				Long end=0l;
				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					start=Long.parseLong(startCode);
					end=Long.parseLong(endCode);

				}else{
					start=Tools.Text36ToConvert(startCode);
					end=Tools.Text36ToConvert(endCode);
				}
				//数量
				iamount=(int) (end-start)+1;
			}
		}
		return iamount+"";
	}

	/***
	 * Describe: 根据仓库库存ID查找库存
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#findIomstationdetailsbyid(java.lang.Long)
	 * @param idetailsid  库存ID
	 * @return  库存对象
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public Iomstationticketdetails findIomstationdetailsbyid(Long idetailsid){
		return (Iomstationticketdetails) this.get(Iomstationticketdetails.class, idetailsid);
	}

	/***
	 * Describe: 根据单据对象检查票段是否在仓库中
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#checkTicketInihouse(com.ectrip.model.warehouse.Iomstocksbilldetails, java.lang.Long, java.lang.Long)
	 * @param iomstocksbilldetails 单据对象
	 * @param iwarehouseid 仓库ID
	 * @param iamount  票数量
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(Iomstocksbilldetails iomstocksbilldetails,Long iwarehouseid,Long iamount) {
		boolean b=false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, iomstocksbilldetails.getItickettypeid());
		//查询票规则
		String hsql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
		if (edmticket!=null) {
			Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);
			List temp = null;
			if(ticketrule.getIntons2()==null)
			{
				ticketrule.setIntons2(0);
			}
			//起始流水号和截止流水号
			String istartserial=iomstocksbilldetails.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			String iendserial=iomstocksbilldetails.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			//如果起始流水号比截止流水号大，直接返回错误
			if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
			{
				if (Long.parseLong(istartserial)>Long.parseLong(iendserial)) {
					b=false;
				}else{
					iomstocksbilldetails.setIstartserial(Long.parseLong(istartserial));
					iomstocksbilldetails.setIendserial(Long.parseLong(iendserial));
					//查询仓库中是否存在这个票段hql
					String sql="from Iomstationticketdetails ioms where ioms.istartserial<="+iomstocksbilldetails.getIstartserial()+
							" and ioms.iendserial>="+iomstocksbilldetails.getIendserial()+" and ioms.iamount>="+iamount+" and ioms.iwarehouseid="+iwarehouseid+
							" and ioms.itickettypeid="+iomstocksbilldetails.getItickettypeid();
					temp=this.find(sql);

					if (temp!=null&&temp.size()>=1) {
						b=true;
					}
					else {
						b=false;
					}
				}
			}
			else{
				if (Tools.Text36ToConvert(istartserial)>Tools.Text36ToConvert(iendserial)) {
					b=false;
				}else{
					iomstocksbilldetails.setIstartserial(Tools.Text36ToConvert(istartserial));
					iomstocksbilldetails.setIendserial(Tools.Text36ToConvert(iendserial));
					//查询仓库中是否存在这个票段hql
					String sql="from Iomstationticketdetails ioms where ioms.istartserial<="+iomstocksbilldetails.getIstartserial()+
							" and ioms.iendserial>="+iomstocksbilldetails.getIendserial()+" and ioms.iamount>="+iamount+" and ioms.iwarehouseid="+iwarehouseid+
							" and ioms.itickettypeid="+iomstocksbilldetails.getItickettypeid();
					temp=this.find(sql);

					if (temp!=null&&temp.size()>=1) {
						b=true;
					}
					else {
						b=false;
					}
				}
			}
		}
		return b;
	}

	/***
	 * Describe: 根据起始票号和截止票号检查票段是否在仓库中
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#checkTicketInihouse(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param szstartcode 起始票段
	 * @param szendcode  截止票段
	 * @param iwarehouseid 仓库ID
	 * @param itickettypeid 票类型ID
	 * @param iamount  票数量
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkTicketInihouse(String szstartcode,String szendcode,Long iwarehouseid,Long itickettypeid,Long iamount){
		boolean b=false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String hsql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
		if (edmticket!=null) {
			Edmticketnoruletab ticketrule = (Edmticketnoruletab) this.find(hsql).get(0);
			if(ticketrule.getIntons2()==null)
			{
				ticketrule.setIntons2(0);
			}
			String istartserial=szstartcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			String iendserial=szendcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
			if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
			{
				if (Long.parseLong(istartserial)>Long.parseLong(iendserial)) {
					b=false;
				}
				else{
					String sql="from Iomstationticketdetails ioms where ioms.istartserial<="+Long.parseLong(istartserial)+" and ioms.iendserial>="+Long.parseLong(iendserial)
							+" and ioms.iamount>="+iamount+" and ioms.iwarehouseid="+iwarehouseid+" and ioms.itickettypeid="+itickettypeid;
					//System.out.println(sql);
					List temp = this.find(sql);
					if (temp!=null&&temp.size()>=1) {
						b=true;
					}
					else {
						b=false;
					}
				}
			}else{
				if (Tools.Text36ToConvert(istartserial)>Tools.Text36ToConvert(iendserial)) {
					b=false;
				}
				else{
					String sql="from Iomstationticketdetails ioms where ioms.istartserial<="+Tools.Text36ToConvert(istartserial)+" and ioms.iendserial>="+Tools.Text36ToConvert(iendserial)
							+" and ioms.iamount>="+iamount+" and ioms.iwarehouseid="+iwarehouseid+" and ioms.itickettypeid="+itickettypeid;
					//System.out.println(sql);
					List temp = this.find(sql);
					if (temp!=null&&temp.size()>=1) {
						b=true;
					}
					else {
						b=false;
					}
				}
			}
		}else{
			b=false;
		}
		return b;
	}

	/***
	 * Describe:  根据流水号和票类型返回票号
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#reNomralTicket(java.lang.Long, java.lang.Long)
	 * @param ticketserial  票流水号
	 * @param itickettypeid 票类型ID
	 * @return  票号
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 * ChaoYuHang Edit
	 * 2012-8-24
	 * 修改票后缀的生成
	 */
	public String reNomralTicket(Long ticketserial,Long itickettypeid,String ticketcode){
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		StringBuffer result=new StringBuffer();
		if (edmticket!=null) {
			String sql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				if(ticketrule.getIntons2()!=null && ticketrule.getIntons2() > 0)
				{
					//票前缀
					result.append(ticketrule.getSzticketnoprefix());
				}
				//票代码
				result.append(edmticket.getSztickettypecode());
				Integer zc=ticketrule.getItickettypecodepos();
				if(edmticket.getSzticketprintcode().length()<zc.intValue()){
					for (int x = 0; x <zc - edmticket.getSzticketprintcode().length(); x++) {
						result.append("0");
					}
				}
				//根据票规则的流水号长度为输入的流水号补零
				int iserialnolen=ticketrule.getIserialnolen();
				int size_zero=0;
				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					size_zero=iserialnolen-String.valueOf(ticketserial).length();
					for (int i = 0; i < size_zero; i++) {
						result.append("0");
					}
					result.append(ticketserial);

				}else{
					size_zero=iserialnolen-Tools.ConvertTo36Text(Long.parseLong(ticketserial.toString()), 0).length();
					for (int i = 0; i < size_zero; i++) {
						result.append("0");
					}
					result.append(Tools.ConvertTo36Text(ticketserial, 0));
				}

				//String
				String endCode=ticketcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				//票后缀(暂时不管)
//				int backlen=ticketrule.getIticketnolen()-ticketrule.getIserialnopos()-ticketrule.getIserialnolen()+1;
//				int size_zero_back=backlen-endCode.length();
//				for (int i = 0; i < size_zero_back; i++) {
//					result.append("0");
//				}
				result.append(endCode);
			}
		}
		return result.toString();
	}

	/***
	 * Describe: 检查是否存在重复票段
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#checkStartcodeandEndcode(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 * @param startcode 起始票号
	 * @param endcode 截止票号
	 * @param startcodeII 起始票号II
	 * @param endcodeII  截止票号II
	 * @param itickettypeid 票类型ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean checkStartcodeandEndcode(String startcode, String endcode,String startcodeII, String endcodeII,Long itickettypeid) {
		boolean b=false;
		Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		if(edmticket!=null){
			String sql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
			List lst=this.find(sql);
			if(lst!=null&&lst.size()>0){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(0);
				if(ticketrule.getIntons2()==null)
				{
					ticketrule.setIntons2(0);
				}
				String startticket=startcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String endticket=endcode.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());

				String startstock=startcodeII.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				String endstock=endcodeII.substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen());
				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					if(Long.parseLong(startticket)>Long.parseLong(endstock)||Long.parseLong(startstock)>Long.parseLong(endticket)){
						b=true;
					}else{
						b=false;
					}
				}else{
					if(	Tools.Text36ToConvert(startticket)>Tools.Text36ToConvert(endstock)||Tools.Text36ToConvert(startstock)>Tools.Text36ToConvert(endticket)){
						b=true;
					}else{
						b=false;
					}
				}
			}
		}

		return b;
	}

	/***
	 * Describe: 保存单据和单据明细
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#insertstockbill(java.util.List, com.ectrip.model.warehouse.Iomstocksbill, com.ectrip.model.syspar.Syslog, java.lang.Long)
	 * @param datailList 单据详细对象List
	 * @param iomstocksbill 单据对象
	 * @param syslog  系统日志对象
	 * @param bystockswayid 操作方式ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public boolean insertstockbill(List<Iomstocksbilldetails> datailList,Iomstocksbill iomstocksbill,Syslog syslog,Long bystockswayid)throws Exception{
		boolean b=false;
		String msql="select szstocksbillid from Iomstocksbill order by to_number(szstocksbillid) desc";
		List sklist=this.find(msql);
		String maxid="0";
		if(sklist!=null&&sklist.size()>0){
			maxid=(String) sklist.get(0);
		}
		Long st=new Long(maxid)+1L;
		iomstocksbill.setSzstocksbillid(st.toString());
		//1 初始入库、2回收入库 3调拔 4作废出库、5余票入库 6领用出库7盘赢入库、8盘亏出库
		iomstocksbill.setBystockswayid(bystockswayid);  //操作方式
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
		iomstocksbill.setSzstocksbillcode(stockcode.toString()); //单据编号
		iomstocksbill.setDtmakedate(Tools.getDays());
		iomstocksbill.setBybillstate(1L);

		this.save(iomstocksbill); //保存单据对象

		List<Iomstocksbilldetails> stationList=new ArrayList<Iomstocksbilldetails>();
		if(datailList!=null&&datailList.size()>0){
			for(int i=0;i<datailList.size();i++){
				Iomstocksbilldetails billdeta=datailList.get(i);
				Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, billdeta.getItickettypeid());
				String hsql=" from Edmticketnoruletab trule where trule.byisuse=1 and trule.iscenicid="+edmticket.getIscenicid();
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) this.find(hsql).get(0);

				billdeta.setSzstocksbillid(iomstocksbill.getSzstocksbillid());  //单据信息的ID设置单据详细信息的ID
				String istartserial=billdeta.getSzstartticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()-1);
				String iendserial=billdeta.getSzendticketcode().substring(ticketrule.getIntons2()+ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos()+ticketrule.getIserialnolen()-1);

				if (ticketrule.getIntons1()!=null&&ticketrule.getIntons1()==1)
				{
					billdeta.setIstartserial(Long.parseLong(istartserial));			//起始流水号
					billdeta.setIendserial(Long.parseLong(iendserial));				//截止流水号
				}else{
					billdeta.setIstartserial(Tools.Text36ToConvert(istartserial));			//起始流水号
					billdeta.setIendserial(Tools.Text36ToConvert(iendserial));				//截止流水号
				}


				//判断票号是否存在在仓库
				if(!checkTicketInihouse(billdeta, iomstocksbill.getIstationoutid(),billdeta.getIamount())){
					return true;
				}

				String tsql="select szstocksbilldetailsid from Iomstocksbilldetails order by to_number(szstocksbilldetailsid) desc";
				List splist=this.find(tsql);
				String ksdetaid="0";
				if(splist!=null&&splist.size()>0){
					ksdetaid=(String) splist.get(0);
				}
				Long dp=new Long(ksdetaid)+1L;
				billdeta.setSzstocksbilldetailsid(dp.toString());  //单据详细的ID

				stationList.add(billdeta);

				this.save(billdeta);			//保存单据详细对象
			}
		}

		saveTicket(stationList, iomstocksbill); //对票段进行保存操作，拆分和整理

		//日志
		Iomwarehouse house=(Iomwarehouse) this.get(Iomwarehouse.class, iomstocksbill.getIstationinid());
		Iomwarehouse ware=new Iomwarehouse();
		if(iomstocksbill.getIstationoutid()!=null){
			ware=(Iomwarehouse) this.get(Iomwarehouse.class, iomstocksbill.getIstationoutid());
		}

		syslog.setLogdatetime(Tools.getDayTimes());
		if(iomstocksbill.getBystockswayid()==1){
			syslog.setStlg("0211");
			syslog.setBrief("初始入库：" + house.getSzwarehousename());
			syslog.setNote("初始入库：" +house.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}else if(iomstocksbill.getBystockswayid()==3){
			syslog.setStlg("0212");
			syslog.setBrief("调拨：" + house.getSzwarehousename());
			syslog.setNote("调拨：入库仓库" +house.getSzwarehousename()+",出库仓库:"+ware.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}else if(iomstocksbill.getBystockswayid()==4){
			syslog.setStlg("0213");
			syslog.setBrief("作废出库：" + ware.getSzwarehousename());
			syslog.setNote("作废出库：" +ware.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}else if(iomstocksbill.getBystockswayid()==5){
			syslog.setStlg("0214");
			syslog.setBrief("余票入库：" + house.getSzwarehousename());
			syslog.setNote("余票入库：" +house.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}else if(iomstocksbill.getBystockswayid()==6){
			syslog.setStlg("0215");
			syslog.setBrief("领用出库：" + ware.getSzwarehousename());
			syslog.setNote("领用出库：" +ware.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}else{
			syslog.setStlg("0211");
			syslog.setBrief("初始入库：" + house.getSzwarehousename());
			syslog.setNote("初始入库：" +house.getSzwarehousename()+" 制单日期"+iomstocksbill.getDtmakedate() );
		}
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		return b;
	}

	/***
	 * Describe:  对取出的票段进行分割
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#saveTicket(java.util.List, com.ectrip.model.warehouse.Iomstocksbill)
	 * @param iomstocksbilldetails  单据详细对象List
	 * @param iomstocksbill  单据对象
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public void saveTicket(List<Iomstocksbilldetails> iomstocksbilldetails,Iomstocksbill iomstocksbill){
		List<Iomstationticketdetails> insert_stationin=new ArrayList<Iomstationticketdetails>();
		for (int i = 0; i < iomstocksbilldetails.size(); i++) {
			Long maxid=getMaxPk("idetailsid", "Iomstationticketdetails");

			maxid=maxid+1L;
			//============新建需要调拨的仓库对象
			Iomstationticketdetails iomstationticketdetails=new Iomstationticketdetails();
			iomstationticketdetails.setIdetailsid(maxid);

			iomstationticketdetails.setItickettypeid(iomstocksbilldetails.get(i).getItickettypeid());
			iomstationticketdetails.setIwarehouseid(iomstocksbill.getIstationinid());
			iomstationticketdetails.setSzstartticketcode(iomstocksbilldetails.get(i).getSzstartticketcode());
			iomstationticketdetails.setSzendticketcode(iomstocksbilldetails.get(i).getSzendticketcode());
			iomstationticketdetails.setIstartserial(iomstocksbilldetails.get(i).getIstartserial());
			iomstationticketdetails.setIendserial(iomstocksbilldetails.get(i).getIendserial());
			iomstationticketdetails.setIamount(iomstocksbilldetails.get(i).getIamount());

			insert_stationin.add(iomstationticketdetails);

			//=============拆分票段操作
			String mysql="from Iomstationticketdetails ioms where ioms.istartserial<="+iomstocksbilldetails.get(i).getIstartserial()+
					" and ioms.iendserial>="+iomstocksbilldetails.get(i).getIendserial()+" and ioms.iwarehouseid="+iomstocksbill.getIstationoutid()+
					" and ioms.itickettypeid="+iomstocksbilldetails.get(i).getItickettypeid();

			Iomstationticketdetails ioms = (Iomstationticketdetails) this.find(mysql).get(0);
			if (ioms!=null) {

				//位于票段之间
				if (ioms.getIstartserial()<iomstocksbilldetails.get(i).getIstartserial()
						&&ioms.getIendserial()>iomstocksbilldetails.get(i).getIendserial()) {
					maxid+=1L;
					Iomstationticketdetails station1=new Iomstationticketdetails();
					station1.setIstartserial(ioms.getIstartserial());
					station1.setIendserial(iomstocksbilldetails.get(i).getIstartserial()-1);
					station1.setItickettypeid(ioms.getItickettypeid());
					station1.setIdetailsid(maxid);
					station1.setIwarehouseid(ioms.getIwarehouseid());
					station1.setSzstartticketcode(reNomralTicket(station1.getIstartserial(),station1.getItickettypeid(),ioms.getSzstartticketcode()));
					station1.setSzendticketcode(reNomralTicket(station1.getIendserial(), station1.getItickettypeid(),ioms.getSzstartticketcode()));
					station1.setIamount(Long.parseLong(reIamountforStartCode(station1.getSzstartticketcode(), station1.getSzendticketcode(), station1.getItickettypeid())));

					this.save(station1);

					maxid+=1L;
					Iomstationticketdetails station2=new Iomstationticketdetails();
					station2.setIstartserial(iomstocksbilldetails.get(i).getIendserial()+1);
					station2.setIendserial(ioms.getIendserial());
					station2.setItickettypeid(ioms.getItickettypeid());
					station2.setIdetailsid(maxid);
					station2.setIwarehouseid(ioms.getIwarehouseid());
					station2.setSzstartticketcode(reNomralTicket(station2.getIstartserial(), station2.getItickettypeid(),ioms.getSzstartticketcode()));
					station2.setSzendticketcode(reNomralTicket(station2.getIendserial(), station2.getItickettypeid(),ioms.getSzstartticketcode()));
					station2.setIamount(Long.parseLong(reIamountforStartCode(station2.getSzstartticketcode(), station2.getSzendticketcode(), station2.getItickettypeid())));

					this.save(station2);
				}

				//位于票段的开始部分
				else if (ioms.getIstartserial()-iomstocksbilldetails.get(i).getIstartserial()==0
						&&ioms.getIendserial()>iomstocksbilldetails.get(i).getIendserial()) {
					Iomstationticketdetails station=new Iomstationticketdetails();
					station.setIdetailsid(maxid+1L);
					station.setIstartserial(iomstocksbilldetails.get(i).getIendserial()+1);
					station.setIendserial(ioms.getIendserial());
					station.setItickettypeid(ioms.getItickettypeid());
					station.setIwarehouseid(ioms.getIwarehouseid());
					station.setSzstartticketcode(reNomralTicket(station.getIstartserial(), station.getItickettypeid(),ioms.getSzstartticketcode()));
					station.setSzendticketcode(reNomralTicket(station.getIendserial(), station.getItickettypeid(),ioms.getSzstartticketcode()));
					station.setIamount(Long.parseLong(reIamountforStartCode(station.getSzstartticketcode(), station.getSzendticketcode(), station.getItickettypeid())));
					this.save(station);
				}

				//位于票段的结束部分
				else if (ioms.getIstartserial()<iomstocksbilldetails.get(i).getIstartserial()
						&&ioms.getIendserial()-iomstocksbilldetails.get(i).getIendserial()==0) {
					Iomstationticketdetails station=new Iomstationticketdetails();
					station.setIdetailsid(maxid+1L);
					station.setIstartserial(ioms.getIstartserial());
					station.setIendserial(iomstocksbilldetails.get(i).getIstartserial()-1);
					station.setItickettypeid(ioms.getItickettypeid());
					station.setIwarehouseid(ioms.getIwarehouseid());
					station.setSzstartticketcode(reNomralTicket(station.getIstartserial(), station.getItickettypeid(),ioms.getSzstartticketcode()));
					station.setSzendticketcode(reNomralTicket(station.getIendserial(), station.getItickettypeid(),ioms.getSzstartticketcode()));
					station.setIamount(Long.parseLong(reIamountforStartCode(station.getSzstartticketcode(), station.getSzendticketcode(), station.getItickettypeid())));
					this.save(station);
				}
				this.delete(ioms);
			}
		}
		saveTicketinhouse(insert_stationin, iomstocksbill);  //整理票段操作
	}

	/***
	 * Describe: 对保存的票段进行整理
	 * @see com.ectrip.system.warehouse.dao.idao.IWarehouseChangeDAO#saveTicketinhouse(java.util.List, com.ectrip.model.warehouse.Iomstocksbill)
	 * @param iomstationticketdetails  仓库对象List
	 * @param iomstocksbill   单据对象
	 * @author ChaoYuHang
	 * Date:2012-7-24
	 */
	public void saveTicketinhouse(List<Iomstationticketdetails> iomstationticketdetails,Iomstocksbill iomstocksbill){
		Iomstationticketdetails iomstationticketdetail=null;
		boolean isNew=true;     //设置变量标识是更新新记录或新增新记录
		if (iomstationticketdetails!=null)
			for (int i = 0; i < iomstationticketdetails.size(); i++) {
				Iomstationticketdetails iomstocksbilldetail=iomstationticketdetails.get(i);
				//根据一种票类型查询仓库结存中存在的票的hql
				String sql="from Iomstationticketdetails ioms where ioms.iwarehouseid="+iomstocksbill.getIstationinid()+" and ioms.itickettypeid="+iomstocksbilldetail.getItickettypeid();
				List<Iomstationticketdetails> iomstation=this.find(sql);
				if (iomstation!=null){
					for (int j = 0; j < iomstation.size(); j++) {
						iomstationticketdetail=iomstation.get(j);

						if (iomstationticketdetail.getIstartserial()-iomstocksbilldetail.getIendserial()==1) {
							iomstationticketdetail.setIstartserial(iomstocksbilldetail.getIstartserial());
							iomstationticketdetail.setSzstartticketcode(reNomralTicket(iomstocksbilldetail.getIstartserial(), iomstationticketdetail.getItickettypeid(),iomstationticketdetail.getSzstartticketcode()));
							iomstationticketdetail.setIamount(iomstationticketdetail.getIamount()+iomstocksbilldetail.getIamount());
							for (int z = 0; z < iomstation.size(); z++) {  //再判断有和其他票段连接
								if (iomstationticketdetail.getIstartserial()-iomstation.get(z).getIendserial()==1) {
									iomstationticketdetail.setIstartserial(iomstation.get(z).getIstartserial());
									iomstationticketdetail.setSzstartticketcode(reNomralTicket(iomstationticketdetail.getIstartserial(), iomstationticketdetail.getItickettypeid(),iomstationticketdetail.getSzstartticketcode()));
									iomstationticketdetail.setIamount(iomstationticketdetail.getIamount()+iomstation.get(z).getIamount());
									this.delete(iomstation.get(z));
									break;
								}
							}
							isNew=false;
							break;
						}
						else if (iomstocksbilldetail.getIstartserial()-iomstationticketdetail.getIendserial()==1) {
							iomstationticketdetail.setIendserial(iomstocksbilldetail.getIendserial());
							iomstationticketdetail.setSzendticketcode(reNomralTicket(iomstocksbilldetail.getIendserial(), iomstationticketdetail.getItickettypeid(),iomstationticketdetail.getSzstartticketcode()));
							iomstationticketdetail.setIamount(iomstationticketdetail.getIamount()+iomstocksbilldetail.getIamount());
							for (int z = 0; z < iomstation.size(); z++) {
								Long temp=iomstation.get(z).getIstartserial()-iomstocksbilldetail.getIendserial();
								if (iomstation.get(z).getIstartserial()-iomstationticketdetail.getIendserial()==1) {
									iomstationticketdetail.setIendserial(iomstation.get(z).getIendserial());
									iomstationticketdetail.setSzendticketcode(reNomralTicket(iomstationticketdetail.getIendserial(), iomstationticketdetail.getItickettypeid(),iomstationticketdetail.getSzstartticketcode()));
									iomstationticketdetail.setIamount(iomstationticketdetail.getIamount()+iomstation.get(z).getIamount());
									this.delete(iomstation.get(z));
									break;
								}
							}
							isNew=false;
							break;
						}
					}
					//如果存在与某个票段连接，进行更新操作，否则新增记录
					if (!isNew) {
						this.update(iomstationticketdetail);
						isNew=true;
						continue;
					}
					else {
						Iomstationticketdetails iomstationticket=new Iomstationticketdetails();
						iomstationticket.setIdetailsid(getMaxPk("idetailsid", "Iomstationticketdetails")+1);
						iomstationticket.setItickettypeid(iomstocksbilldetail.getItickettypeid());
						iomstationticket.setIwarehouseid(iomstocksbill.getIstationinid());
						iomstationticket.setIstartserial(iomstocksbilldetail.getIstartserial());
						iomstationticket.setIendserial(iomstocksbilldetail.getIendserial());
						iomstationticket.setSzstartticketcode(reNomralTicket(iomstationticket.getIstartserial(), iomstationticket.getItickettypeid(),iomstocksbilldetail.getSzstartticketcode()));
						iomstationticket.setSzendticketcode(reNomralTicket(iomstationticket.getIendserial(), iomstationticket.getItickettypeid(),iomstocksbilldetail.getSzstartticketcode()));
						iomstationticket.setIamount(iomstocksbilldetail.getIamount());
						this.save(iomstationticket);
						isNew=true;
						continue;
					}
				}
			}
	}

}
