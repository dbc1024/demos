/*package com.ectrip.system.salesmanager.action;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Edmcrowdkindpricetab;
import com.ectrip.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService;
import com.ectrip.system.provider.service.iservice.ICrowdKindPriceService;
import com.ectrip.system.salesmanager.service.iservice.ISaleLimitsService;
import com.opensymphony.xwork2.ActionContext;
*//**
 * Ա������Ȩ��
 * @author huangyuqi
 *//*
public class SaleLimitsAction extends BaseAction {
	private Ospsaleslimitstab salelimits;
	private String page;
	private int pageSize=PaginationSupport.PAGESIZE;
	private PaginationSupport ps;
	private String url;
	
	private ISaleLimitsService salelimitsService;
	private ICrowdKindPriceService crowdkindpriceService;

	private IEsfEmployeeTabService employeeService;

	public Ospsaleslimitstab getSalelimits() {
		return salelimits;
	}

	public void setSalelimits(Ospsaleslimitstab salelimits) {
		this.salelimits = salelimits;
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

	public ISaleLimitsService getSalelimitsService() {
		return salelimitsService;
	}

	public void setSalelimitsService(ISaleLimitsService salelimitsService) {
		this.salelimitsService = salelimitsService;
	}

	public ICrowdKindPriceService getCrowdkindpriceService() {
		return crowdkindpriceService;
	}

	public void setCrowdkindpriceService(
			ICrowdKindPriceService crowdkindpriceService) {
		this.crowdkindpriceService = crowdkindpriceService;
	}

	public IEsfEmployeeTabService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEsfEmployeeTabService employeeService) {
		this.employeeService = employeeService;
	}
	
	*//**
	 * Ա������Ȩ�޵�����
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitAdd(){
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
		
		if(salelimits.getIemployeeid()==null||salelimits.getIemployeeid().equals("")){
			this.addActionError(getText("����ѡ��Ա��!"));
			return INPUT;
		}
		
		
		//��ȡԱ��
		List employeelist = employeeService.getEmployeeAllList(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("employeelist", employeelist);
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(salelimits.getIemployeeid());
		List salepricelist = new ArrayList();
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Ospsaleslimitstab sale  = new Ospsaleslimitstab();//Ա������Ȩ�ޱ�
				
				sale.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				sale.setStrcrowdkindprice(price.getStrpricename());//�۸��������
				salepricelist.add(sale);	
					
			}
		}
		
		getRequest().setAttribute("pricelist", salepricelist);		
		
		return SUCCESS;
	}
	*//**
	 *Ա�� ����Ȩ�޵��޸�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitEdit(){
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
		if(salelimits.getIsaleslimitsid()==null || "".equals(salelimits.getIsaleslimitsid())){
			this.addActionError(getText("salelimits.id.requried"));//����Ȩ�޲���Ϊ��
			return INPUT;
		}
		salelimits = salelimitsService.querySaleLimits(salelimits.getIsaleslimitsid());
		
		List salelist = salelimitsService.querySaleLimitsList(salelimits.getIsaleslimitsid());
		List salepricelist = new ArrayList();
		//��ȡԱ��
		List employeelist = employeeService.getEmployeeAllList(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("employeelist", employeelist);
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(salelimits.getIemployeeid());
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Ospsaleslimitstab sale  = new Ospsaleslimitstab();//Ա������Ȩ�ޱ�
				
				sale.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				sale.setStrcrowdkindprice(price.getStrpricename());//�۸��������
				
				
					for (int m = 0; m < salelist.size(); m++) {
						Ospsaleslimitstab limits =(Ospsaleslimitstab)salelist.get(m);
						
						if(price.getIcrowdkindpriceid().equals(limits.getIcrowdkindpriceid())){	
							sale.setIcrowkindpriceTemp(true);
							break;
						}else {
							sale.setIcrowkindpriceTemp(false);
						}
						
					}	
					salepricelist.add(sale);						
			}
			
		}
		
		getRequest().setAttribute("pricelist", salepricelist);	
		
		
		
		
		
		
		return SUCCESS;
	}
	*//**
	 * ����Ȩ�޵�ɾ��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitDel(){
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
		if(salelimits.getIsaleslimitsid()==null || "".equals(salelimits.getIsaleslimitsid())){
			this.addActionError(getText("salelimits.id.requried"));//����Ȩ�޲���Ϊ��
			return INPUT;
		}
		salelimits = salelimitsService.querySaleLimits(salelimits.getIsaleslimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ����Ȩ�޵Ĳ鿴
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitView(){

		this.strutsAction=VIEW;//�鿴
		if(salelimits.getIsaleslimitsid()==null || "".equals(salelimits.getIsaleslimitsid())){
			this.addActionError(getText("salelimits.id.requried"));//����Ȩ�޲���Ϊ��
			return INPUT;
		}
		salelimits = salelimitsService.querySaleLimits(salelimits.getIsaleslimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ����Ȩ�޵ı���
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitSave(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		List salelist = new ArrayList();
		if(strutsAction==ADD || strutsAction==EDIT){
			if(strutsAction==EDIT){
				if(salelimits.getIsaleslimitsid()==null || "".equals(salelimits.getIsaleslimitsid())){
					this.addActionError(getText("salelimits.id.requried"));//����Ȩ�ޱ�Ų���Ϊ��
					return INPUT;
				}
			}
			
			if(salelimits.getIemployeeid()==null || "".equals(salelimits.getIemployeeid())){
				this.addActionError(getText("error.employee.iempoyeeid.required"));//Ա����Ų���Ϊ��
				return INPUT;
			}
			
			Long[] zprno = salelimits.getIcrowdkindpriceids();//�۸�����
			if(zprno==null || "".equals(zprno)){
				this.addActionError("������ѡ��һ����Ʒ");//�۸��Ų���Ϊ��
				return INPUT;
			}
			
				for (int i = 0; i < zprno.length; i++) {
					Ospsaleslimitstab sale = new Ospsaleslimitstab();
					sale.setIemployeeid(salelimits.getIemployeeid());
					sale.setIcrowdkindpriceid(zprno[i]);
					salelist.add(sale);
				}
		}
		if(strutsAction==DELETE){
			if(salelimits.getIsaleslimitsid()==null || "".equals(salelimits.getIsaleslimitsid())){
				this.addActionError(getText("salelimits.id.requried"));//����Ȩ�ޱ�Ų���Ϊ��
				return INPUT;
			}
		}
		if(hasActionErrors()){
			return INPUT;
		}
		
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());//������Ա
		
		if(strutsAction==ADD){//����
			
			//����
			salelimitsService.insertSalelimit(salelist, syslog);
			this.addActionMessage(getText("success.salelimits.add")+WebContant.DOMAINAME);//����Ȩ�����ӳɹ�
		}
		
		if(strutsAction==EDIT){//�޸�
			salelimitsService.updateSalelimit(salelist, syslog);
			this.addActionMessage(getText("success.salelimits.edit")+WebContant.DOMAINAME);//����Ȩ���޸ĳɹ�
		}
		
		if(strutsAction==DELETE){//ɾ��
			salelimitsService.deleteSalelimit(salelimits.getIsaleslimitsid(), syslog);
			this.addActionMessage(getText("success.salelimits.delete")+WebContant.DOMAINAME);//����Ȩ��ɾ���ɹ�
		}
		
		return SUCCESS;
	}
	*//**
	 * ����Ȩ�޵��б�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String saleLimitViewList(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		//���ʵ�ַ
		url=getRequest().getContextPath()+getRequest().getServletPath();
		if(getPage()==null || "".equals(getPage())){
			setPage("1");
		}
		
		//��ȡԱ��
		List employeelist = employeeService.getEmployeeAllList(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("employeelist", employeelist);
		
		if(employeelist!=null&&employeelist.size()>0){
			Esfemployeetab employee=(Esfemployeetab)employeelist.get(0);
			getRequest().setAttribute("empid", employee.getIemployeeid());
		}
		
		ps = salelimitsService.getSaleLimitsList(esfemployeetab.getIcompanyinfoid(),Integer.parseInt(page), pageSize, url);
		return SUCCESS;
	}
	
	
	*//**
	 * ����Ȩ�޵Ĳ�ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String searcherLimitViewList(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		//���ʵ�ַ
		url=getRequest().getContextPath()+getRequest().getServletPath();
		if(getPage()==null || "".equals(getPage())){
			setPage("1");
		}
		
		//��ȡԱ��
		List employeelist = employeeService.getEmployeeAllList(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("employeelist", employeelist);
		
		if(salelimits!=null){
			if(salelimits.getIemployeeid()!=null&&!salelimits.getIemployeeid().equals("")){
				url+="?salelimits.iemployeeid="+salelimits.getIemployeeid();
			}
		}else{
			salelimits=new Ospsaleslimitstab();
		}
		getRequest().setAttribute("empid", salelimits.getIemployeeid());
		
		ps = salelimitsService.searchSaleLimitsList(esfemployeetab.getIcompanyinfoid(),salelimits.getIemployeeid(),Integer.parseInt(page), pageSize, url);
		return SUCCESS;
	}
	
}

*/