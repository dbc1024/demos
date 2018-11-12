/*package com.ectrip.system.salesmanager.action;

import java.util.List;
import java.util.regex.Pattern;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.order.Stssoldtickettab;
import com.ectrip.model.order.StssoldtickettabId;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.salesmanager.service.iservice.IReserveDateService;
import com.opensymphony.xwork2.ActionContext;

public class ReserveDateAction extends BaseAction{
	
	private IReserveDateService reservedateServcie;
	
	private PaginationSupport ps;
	
	private Stssoldtickettab saleinfo;
	
	private int pageSize =PaginationSupport.PAGESIZE;
	
	private String url;
	
	private String page;//��ҳ��ȡҳ��
	
	private Long itickettypeid;
	
	private String stdate;
	
	private String[] isalesvoucherids;    //��ȡID����
	
	private String manyouno;//����
	private String myzj;//֤������
	private String name1;//����
	private	String szticketprintno;//Ʊ��

	public Stssoldtickettab getSaleinfo() {
		return saleinfo;
	}

	public void setSaleinfo(Stssoldtickettab saleinfo) {
		this.saleinfo = saleinfo;
	}

	public String getMyzj() {
		return myzj;
	}

	public void setMyzj(String myzj) {
		this.myzj = myzj;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getSzticketprintno() {
		return szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public void setReservedateServcie(IReserveDateService reservedateServcie) {
		this.reservedateServcie = reservedateServcie;
	}

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getStdate() {
		return stdate;
	}

	public void setStdate(String stdate) {
		this.stdate = stdate;
	}

	public String[] getIsalesvoucherids() {
		return isalesvoucherids;
	}

	public void setIsalesvoucherids(String[] isalesvoucherids) {
		this.isalesvoucherids = isalesvoucherids;
	}

	public String getManyouno() {
		return manyouno;
	}

	public void setManyouno(String manyouno) {
		this.manyouno = manyouno;
	}

	*//**
	 * 
	 * Describe:�꿨�������ڲ�ѯ����
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2012-12-27
	 *//*
	public String showtickettypeList(){
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext().getSession().get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
			if(scenics==null||scenics.equals("")){
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}
		
		List ticketList=reservedateServcie.getListedmticketype(scenics);
		getRequest().setAttribute("ticketList", ticketList);
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:��ʾ���۳�ĳ��Ʒ����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2012-12-27
	 *//*
	public String showListedmticket(){
		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (page == null || page.equals("")) {
			page = "1";
		}
		
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext().getSession().get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
			if(scenics==null||scenics.equals("")){
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}
		
		List ticketList=reservedateServcie.getListedmticketype(scenics);
		getRequest().setAttribute("ticketList", ticketList);
		
		if(itickettypeid!=null&&!itickettypeid.equals("")){
			Pattern p = Pattern.compile("^[0-9]*$");
			boolean b = p.matcher(itickettypeid.toString()).matches();
			if (b == false) {
				this.addActionError("������ѡ��Ʊ��!");   
				return INPUT;
			}
			
		}else{
			this.addActionError("������ѡ��Ʊ��!");
			return INPUT;
		}
		url+="?itickettypeid="+itickettypeid;
		
		ps=reservedateServcie.showAllvoucherticket(itickettypeid,Integer.parseInt(page),10,url,manyouno);
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:���ڱ���
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2012-12-27
	 *//*
	public String insertReserveDate(){
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		if(isalesvoucherids==null||isalesvoucherids.length<=0){
			this.addActionError("��ѡ�������ڵ��꿨!");
			return INPUT;
		}
		if(stdate==null||stdate.equals("")){
			this.addActionError("��ѡ����������!");
			return INPUT;
		}else{
			Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
			boolean b = p.matcher(stdate).matches();
			if (b == false) {
				this.addActionError("�������ڸ�ʽ����,������ѡ��!");//�ж�ʱ��ĸ�ʽ
				return INPUT;
			}
		}
		
		reservedateServcie.insertupReserve(isalesvoucherids, stdate,syslog);
		
		return SUCCESS;
	}
	
	public String showCradInfo(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (page == null || page.equals("")) {
			page = "1";
		}
		if((name1!=null && !"".equals(name1)) || (myzj!=null && !"".equals(myzj)) || (manyouno!=null && !"".equals(manyouno)) || (szticketprintno!=null && !"".equals(szticketprintno))){
			url+="?name1="+name1+"&myzj="+myzj+"&manyouno"+manyouno+"&szticketprintno="+szticketprintno;
			ps=this.reservedateServcie.showCradInfo(myzj, name1, manyouno, szticketprintno, Integer.parseInt(page), pageSize, url);
		}
		return SUCCESS;
	}
	
	public String editIDCard(){
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}
		saleinfo = (Stssoldtickettab) this.genericService.get(Stssoldtickettab.class, new StssoldtickettabId(saleinfo.getId().getSzsoldticketid(), saleinfo.getId().getIsalesvoucherdetailsid(), saleinfo.getId().getIsalesvoucherid(), saleinfo.getId().getIticketstationid()));
		System.out.println(saleinfo.getId().getIsalesvoucherdetailsid());
		return SUCCESS;
	}
	
	public String editIDCardSave(){
		System.out.println(saleinfo.getId().getSzsoldticketid()+","+saleinfo.getId().getIsalesvoucherdetailsid()+","+saleinfo.getId().getIsalesvoucherid()+","+saleinfo.getId().getIticketstationid());

		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		if(saleinfo.getName1()==null || "".equals(saleinfo.getName1())){
			this.addActionError("��������Ϊ��");
			return INPUT;
		}
		if(saleinfo.getMyzj()==null || "".equals(saleinfo.getMyzj())){
			this.addActionError("֤�����벻��Ϊ��");
			return INPUT;
		}
		this.reservedateServcie.editIDCard(saleinfo, syslog);
		return SUCCESS;
	}
}

*/