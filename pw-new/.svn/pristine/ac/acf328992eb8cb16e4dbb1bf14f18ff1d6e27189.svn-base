/*package com.ectrip.system.salesmanager.action;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Edmcrowdkindpricetab;
import com.ectrip.model.provider.Esbticketwintab;
import com.ectrip.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.provider.service.iservice.ICrowdKindPriceService;
import com.ectrip.system.provider.service.iservice.IEsbticketWinService;
import com.ectrip.system.salesmanager.service.iservice.ITicketWinLimitsService;
import com.opensymphony.xwork2.ActionContext;
*//**
 * ��������Ȩ��
 * @author huangyuqi
 *//*
public class TicketWinLimitsAction extends BaseAction {
	private Ospticketwinlimitstab ticketwinlimits;
	private Esbticketwintab esbwin;
	private String page;
	private PaginationSupport ps;
	private int pageSize = PaginationSupport.PAGESIZE;
	private String url;
	
	
	private ICrowdKindPriceService crowdkindpriceService;
	private ITicketWinLimitsService ticketwinlimitsServcie;
	private IEsbticketWinService esbwinService;
	
	public Ospticketwinlimitstab getTicketwinlimits() {
		return ticketwinlimits;
	}
	public void setTicketwinlimits(Ospticketwinlimitstab ticketwinlimits) {
		this.ticketwinlimits = ticketwinlimits;
	}
	public Esbticketwintab getEsbwin() {
		return esbwin;
	}
	public void setEsbwin(Esbticketwintab esbwin) {
		this.esbwin = esbwin;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public PaginationSupport getPs() {
		return ps;
	}
	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}
	public ICrowdKindPriceService getCrowdkindpriceService() {
		return crowdkindpriceService;
	}
	public void setCrowdkindpriceService(
			ICrowdKindPriceService crowdkindpriceService) {
		this.crowdkindpriceService = crowdkindpriceService;
	}
	public ITicketWinLimitsService getTicketwinlimitsServcie() {
		return ticketwinlimitsServcie;
	}
	public void setTicketwinlimitsServcie(
			ITicketWinLimitsService ticketwinlimitsServcie) {
		this.ticketwinlimitsServcie = ticketwinlimitsServcie;
	}
	public IEsbticketWinService getEsbwinService() {
		return esbwinService;
	}
	public void setEsbwinService(IEsbticketWinService esbwinService) {
		this.esbwinService = esbwinService;
	}
	*//**
	 * ���Ӵ�������Ȩ��	
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitAdd(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction=ADD;//����
		
		//��ȡ����
//		List winlist = esbwinService.getAllTicketWinList(esfemployeetab.getIemployeeid());
//		getRequest().setAttribute("winlist", winlist);
		
		
		List salelist = ticketwinlimitsServcie.queryTicketWinLimitsList(ticketwinlimits.getIticketwinid());
		
		
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(esfemployeetab.getIemployeeid());
		
		List salepricelist = new ArrayList();
	
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Ospticketwinlimitstab win  = new Ospticketwinlimitstab();//Ա������Ȩ�ޱ�
				
				win.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				win.setStrcrowdkindprice(price.getStrpricename());//�۸��������				
				
					for (int m = 0; m < salelist.size(); m++) {
						Ospticketwinlimitstab winlimits =(Ospticketwinlimitstab)salelist.get(m);						
						if(price.getIcrowdkindpriceid().equals(winlimits.getIcrowdkindpriceid())){	
							win.setIcrowkindpriceTemp(true);
							break;
						}else {
							win.setIcrowkindpriceTemp(false);
						}
						
					}	
					salepricelist.add(win);						
			}			
		}		
		
		
		getRequest().setAttribute("pricelist", salepricelist);		
		
			
		return SUCCESS;
	}
	*//**
	 * ��������Ȩ���޸�	
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitEdit(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction=EDIT;//�޸�
		
		if(ticketwinlimits.getIticketwinlimitsid()==null || "".equals(ticketwinlimits.getIticketwinlimitsid())){
			this.addActionError(getText("error.ticketwinlimits.id.requried"));//��������Ȩ�ޱ�Ų���Ϊ��
			return INPUT;
		}
		
		ticketwinlimits = ticketwinlimitsServcie.queryTicketWinLimits(ticketwinlimits.getIticketwinlimitsid());
		
		List salelist = ticketwinlimitsServcie.queryTicketWinLimitsList(ticketwinlimits.getIticketwinid());
		
		
		//��ȡ����
//		List winlist = esbwinService.getAllTicketWinList(esfemployeetab.getIemployeeid());
//		getRequest().setAttribute("winlist", winlist);
		
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(esfemployeetab.getIemployeeid());
		
		List salepricelist = new ArrayList();
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Ospticketwinlimitstab win  = new Ospticketwinlimitstab();//Ա������Ȩ�ޱ�
				
				win.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				win.setStrcrowdkindprice(price.getStrpricename());//�۸��������				
				
					for (int m = 0; m < salelist.size(); m++) {
						Ospticketwinlimitstab winlimits =(Ospticketwinlimitstab)salelist.get(m);						
						if(price.getIcrowdkindpriceid().equals(winlimits.getIcrowdkindpriceid())){	
							win.setIcrowkindpriceTemp(true);
							break;
						}else {
							win.setIcrowkindpriceTemp(false);
						}
						
					}	
					salepricelist.add(win);						
			}			
		}		
		getRequest().setAttribute("pricelist", salepricelist);	
		return SUCCESS;
	}
	*//**
	 * ��������Ȩ��ɾ��	
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitDel(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction=DELETE;//ɾ��
		
		if(ticketwinlimits.getIticketwinlimitsid()==null || "".equals(ticketwinlimits.getIticketwinlimitsid())){
			this.addActionError(getText("error.ticketwinlimits.id.requried"));//��������Ȩ�ޱ�Ų���Ϊ��
			return INPUT;
		}
		
		ticketwinlimits = ticketwinlimitsServcie.queryTicketWinLimits(ticketwinlimits.getIticketwinlimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ��������Ȩ�޲鿴	
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitView(){	
		
		this.strutsAction=VIEW;//�鿴
		
		if(ticketwinlimits.getIticketwinlimitsid()==null || "".equals(ticketwinlimits.getIticketwinlimitsid())){
			this.addActionError(getText("error.ticketwinlimits.id.requried"));//��������Ȩ�ޱ�Ų���Ϊ��
			return INPUT;
		}
		
		ticketwinlimits = ticketwinlimitsServcie.queryTicketWinLimits(ticketwinlimits.getIticketwinlimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ��������Ȩ�ޱ���	
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitSave(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		List winList = new ArrayList();
		if(strutsAction==ADD || strutsAction==EDIT){
			if(strutsAction==EDIT){
				if(ticketwinlimits.getIticketwinlimitsid()==null || "".equals(ticketwinlimits.getIticketwinlimitsid())){
					this.addActionError(getText("error.ticketwinlimits.id.requried"));//��������Ȩ�ޱ�Ų���Ϊ��
					return INPUT;
				}
			}
			if(ticketwinlimits.getIticketwinid()==null || "".equals(ticketwinlimits.getIticketwinid())){
				this.addActionError(getText("error.esbwin.ipostwinid.required"));//���ڱ�Ų���Ϊ��
				return INPUT;
			}
			
			Long[] zprno = ticketwinlimits.getIcrowdkindpriceids();//�۸�����
			if(zprno==null || "".equals(zprno)){
				this.addActionError("������ѡ��һ����Ʒ");//�۸��Ų���Ϊ��
				return INPUT;
			}
			
				for (int i = 0; i < zprno.length; i++) {
					Ospticketwinlimitstab win = new Ospticketwinlimitstab();
					win.setIticketwinid(ticketwinlimits.getIticketwinid());
					win.setIcrowdkindpriceid(zprno[i]);
					winList.add(win);
				}
		}
		if(strutsAction==DELETE){
			if(ticketwinlimits.getIticketwinlimitsid()==null || "".equals(ticketwinlimits.getIticketwinlimitsid())){
				this.addActionError(getText("error.ticketwinlimits.id.requried"));//��������Ȩ�ޱ�Ų���Ϊ��
				return INPUT;
			}
		}
		if(hasActionErrors()){
			return INPUT;
		}
		
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());//������Ա
		
		if(strutsAction==ADD){//����
			long maxwin = this.genericService.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
			ticketwinlimits.setIticketwinlimitsid(maxwin+1);
			
			//����
			ticketwinlimitsServcie.insertTicketWinLimits(winList, syslog);
			this.addActionMessage(getText("success.ticketwinlimits.add")+WebContant.DOMAINAME);//��������Ȩ�����ӳɹ�
		}
		
		if(strutsAction==EDIT){//�޸�
			ticketwinlimitsServcie.updateTicketWinLimits(winList, syslog);
			this.addActionMessage(getText("success.ticketwinlimits.edit")+WebContant.DOMAINAME);//��������Ȩ���޸ĳɹ�
		}
		
		if(strutsAction==DELETE){//ɾ��
			ticketwinlimitsServcie.deleteTicketWinLimits(ticketwinlimits.getIticketwinlimitsid(), syslog);
			this.addActionMessage(getText("success.ticketwinlimits.delete")+WebContant.DOMAINAME);//��������Ȩ��ɾ���ɹ�
		}
		return SUCCESS;
	}
	*//**
	 * ��������Ȩ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-6
	 *//*
	public String winLimitViewList(){
		if( esbwin.getIticketwinid()==null || "".equals( esbwin.getIticketwinid())){
			this.addActionError(getText("error.esbwin.ipostwinid.required"));//���ڱ�Ų���Ϊ��
			return INPUT;
		}
		
		//���ʵ�ַ
		url=getRequest().getContextPath()+getRequest().getServletPath()+"?esbwin.iticketwinid="+ esbwin.getIticketwinid();
		if(getPage()==null || "".equals(getPage())){
			setPage("1");
		}
		ps = ticketwinlimitsServcie.getTicketWinLimitsList(esbwin.getIticketwinid(),Integer.parseInt(page), pageSize, url);
		return SUCCESS;
	}
}

*/