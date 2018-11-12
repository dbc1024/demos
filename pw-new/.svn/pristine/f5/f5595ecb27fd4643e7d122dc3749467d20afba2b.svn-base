package com.ectrip.ticket.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 已售票信息
 * @author LiuJianwen
 * 每个私有属性的顺序必须与构造方法保持一致
 *
 */
public class Stssoldtickettab{

	private static final long serialVersionUID = 1L;

	private String icrowdkindid;
	private String szcrowdkindname;//人类种群名称
	private String itickettypeid;
	private String ibusinessid;
	private String szbusinessname;//业务类
	private String sztickettypename;//产品名称
	private String dtenddate;
	private String dtstartdate;
	private String szticketprintno;//打印编号
	private String iserialnum;//打印流水号
	private String szsoldticketid;
	private String isalesvoucherdetailsid;//销售凭证明细ID
	private String isalesvoucherid;////销售凭证id,取票id
	private String iticketstationid;
	private String iplayerperticket;//人/张
	private String zdail;//(sztickettypename&tripname&dtstartdate&dtenddate)//带竹筏的子票信息
	private String mactualsaleprice;//价格

	/**
	 * 退票附加属性
	 */
	private String  usid;
	private String szemployeename;
	private String szsalesvoucherno;//销售凭证编号
	private String ihandler;//经手人ID
	private String byvalidity;//00 - 有效票 01 - 已退 02 - 挂失  03 - 部分退订，当为01,02时不可退

	/**
	 * 重新打印附加属性
	 */
	private String szregionalname;//客源地
	private String ornm;//导游姓名
	private String corpname;//公司名称

	private String isok;//是否完成打印
	private String bymaketicketway ="00";//出票方式00为现场打印,01为激活，无需打印
	private String strmaketype="";//出票方式名称
	///////////////////////////////////////////////
	boolean printed = false;//是否已经打印
	///////////////////////////////////////////////
	boolean isSelected = true;//是否选择,默认选择
	//////////////////////////////////////展示 退票成功后信息///////////////////////////////////////
	String poundage;//手续费
	///////////////////////////////////子票///////////////////////////////////////////////
	List<RefundTicket> childs = new ArrayList<RefundTicket>();
	/////////////////////////////////////打印tablemodel///////////////////////////////////////////////////////////
	Vector<Object> tableData = new Vector<Object>();

	public Stssoldtickettab(){}

	public Stssoldtickettab(String icrowdkindid, String szcrowdkindname,
							String itickettypeid, String ibusinessid, String szbusinessname,
							String sztickettypename, String dtenddate, String dtstartdate,
							String szticketprintno, String iserialnum, String szsoldticketid,
							String isalesvoucherdetailsid, String isalesvoucherid,
							String iticketstationid, String iplayerperticket, String zdail
			,String mactualsaleprice,String usid, String szemployeename,
							String szsalesvoucherno, String ihandler,String byvalidity,
							String szregionalname,String ornm, String corpname, String isok,
							String bymaketicketway,String strmaketype) {
		super();
		this.ornm = ornm;
		this.corpname = corpname;
		this.szregionalname = szregionalname;
		this.icrowdkindid = icrowdkindid;
		this.szcrowdkindname = szcrowdkindname;
		this.itickettypeid = itickettypeid;
		this.ibusinessid = ibusinessid;
		this.szbusinessname = szbusinessname;
		this.sztickettypename = sztickettypename;
		this.dtenddate = dtenddate;
		this.dtstartdate = dtstartdate;
		this.szticketprintno = szticketprintno;
		this.iserialnum = iserialnum;
		this.szsoldticketid = szsoldticketid;
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
		this.isalesvoucherid = isalesvoucherid;
		this.iticketstationid = iticketstationid;
		this.iplayerperticket = iplayerperticket;
		this.zdail = zdail;
		this.mactualsaleprice = mactualsaleprice;
		this.szemployeename = szemployeename;
		this.usid = usid;
		this.szsalesvoucherno = szsalesvoucherno;
		this.ihandler = ihandler;
		this.byvalidity = byvalidity;
		this.isok = isok;
		this.bymaketicketway = bymaketicketway;
		this.strmaketype = strmaketype;
	}
	/**
	 * 打印tablemodel
	 * @return
	 */
	public Vector<Object> getTableData() {
		tableData.clear();
		tableData.add(this.iserialnum);//添加流水号
		tableData.add(this.sztickettypename);//添加 票名
		tableData.add(this.strmaketype);//出票方式
		tableData.add(this.dtstartdate);//添加生效日期
		tableData.add(this.dtenddate);//添加截止日期
		tableData.add(isSelected);
		return tableData;
	}


	public List<RefundTicket> getChilds() {
		childs.clear();
		RefundTicket refundTicket;
		try {
			String[] cs = this.zdail.split("#");
			for (int i = 0; i < cs.length; i ++) {
				refundTicket = new RefundTicket();
				String[] cells = cs[i].split("&");
				refundTicket.setType(this.sztickettypename);
				refundTicket.setItickettypeid(cells[0]);//票id
				refundTicket.setCutOffDate(cells[3]);//截止日期
				refundTicket.setSzticketprintno(this.szticketprintno);//条码
				refundTicket.setSztickettypename(cells[1]);//票名
				refundTicket.setUsedTimes(cells[6]);//使用次数
				refundTicket.setIserialnum(this.iserialnum);
				childs.add(refundTicket);
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return childs;
	}
//	/**
//	 * 已退子票
//	 * @return
//	 */
//	public List<RefundTicket> getRefundedChilds() {
//		childs.clear();
//		RefundTicket refundTicket;
//		try {
//			String[] cs = this.zdail.split("#");
//			System.out.println(Arrays.toString(cs));
//			for (int i = 0; i < cs.length; i ++) {
//				refundTicket = new RefundTicket();
//				String[] cells = cs[i].split("&");
//				refundTicket.setType(cs.length==1?"单票":this.sztickettypename);
//				refundTicket.setItickettypeid(cells[0]);//票id
//				refundTicket.setCutOffDate(cells[3]);//截止日期
//				refundTicket.setSzticketprintno(this.szticketprintno);//条码
//				refundTicket.setSztickettypename(cells[1]);//票名
//				refundTicket.setUsedTimes(cells[6]);//使用次数
//				childs.add(refundTicket);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return childs;
//	}




	/**
	 * 重写equals方法
	 * 如果两条码号相等,则判断其相等
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((szticketprintno == null) ? 0 : szticketprintno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stssoldtickettab other = (Stssoldtickettab) obj;
		if (szticketprintno == null) {
			if (other.szticketprintno != null)
				return false;
		} else if (!szticketprintno.equals(other.szticketprintno))
			return false;
		return true;
	}








	public String getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getSzcrowdkindname() {
		return szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public String getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(String ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public String getSztickettypename() {
		return sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getDtenddate() {
		return dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public String getDtstartdate() {
		return dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getSzticketprintno() {
		return szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public String getIserialnum() {
		return iserialnum;
	}

	public void setIserialnum(String iserialnum) {
		this.iserialnum = iserialnum;
	}

	public String getSzsoldticketid() {
		return szsoldticketid;
	}

	public void setSzsoldticketid(String szsoldticketid) {
		this.szsoldticketid = szsoldticketid;
	}

	public String getIsalesvoucherdetailsid() {
		return isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(String isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
	}

	public String getIsalesvoucherid() {
		return isalesvoucherid;
	}

	public void setIsalesvoucherid(String isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}

	public String getIticketstationid() {
		return iticketstationid;
	}

	public void setIticketstationid(String iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public String getIplayerperticket() {
		return iplayerperticket;
	}

	public void setIplayerperticket(String iplayerperticket) {
		this.iplayerperticket = iplayerperticket;
	}

	public String getZdail() {
		return zdail;
	}

	public void setZdail(String zdail) {
		this.zdail = zdail;
	}

	public String getMactualsaleprice() {
		return mactualsaleprice;
	}

	public void setMactualsaleprice(String mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public String getSzsalesvoucherno() {
		return szsalesvoucherno;
	}

	public void setSzsalesvoucherno(String szsalesvoucherno) {
		this.szsalesvoucherno = szsalesvoucherno;
	}

	public String getIhandler() {
		return ihandler;
	}

	public void setIhandler(String ihandler) {
		this.ihandler = ihandler;
	}

	public String getByvalidity() {
		return byvalidity;
	}

	public void setByvalidity(String byvalidity) {
		this.byvalidity = byvalidity;
	}



	public void setTableData(Vector<Object> tableData) {
		this.tableData = tableData;
	}

	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}

	public String getSzregionalname() {
		return szregionalname;
	}

	public void setSzregionalname(String szregionalname) {
		this.szregionalname = szregionalname;
	}

	public String getOrnm() {
		return ornm;
	}

	public void setOrnm(String ornm) {
		this.ornm = ornm;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getIsok() {
		return isok;
	}

	public void setIsok(String isok) {
		this.isok = isok;
	}

	public String getBymaketicketway() {
		return bymaketicketway;
	}

	public void setBymaketicketway(String bymaketicketway) {
		this.bymaketicketway = bymaketicketway;
	}

	public String getStrmaketype() {
		return strmaketype;
	}

	public void setStrmaketype(String strmaketype) {
		this.strmaketype = strmaketype;
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

}



