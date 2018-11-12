/*package com.ectrip.system.afcset.action;

import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.afcset.Esbgardemployee;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService;
import com.ectrip.system.employee.service.iservice.IEsfEmployeeTabService;
import com.opensymphony.xwork2.ActionContext;

public class EsbgardEmployeeAction extends BaseAction{
	
	private IEsbgardEmployeeService gardemployeeService;
	
	private Esbgardemployee esbgardemp;
	
	private PaginationSupport ps;
	
	private StringBuffer url;  					 // ���ڷ�ҳ��url

	private int pageSize = PaginationSupport.PAGESIZE;   	// ��ҳҳ��С

	private String page = "";             	// ��ȡ��ҳҳ��
	
	private String[] gardenes;

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public StringBuffer getUrl() {
		return url;
	}

	public void setUrl(StringBuffer url) {
		this.url = url;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Esbgardemployee getEsbgardemp() {
		return esbgardemp;
	}

	public void setEsbgardemp(Esbgardemployee esbgardemp) {
		this.esbgardemp = esbgardemp;
	}

	public String[] getGardenes() {
		return gardenes;
	}

	public void setGardenes(String[] gardenes) {
		this.gardenes = gardenes;
	}

	public void setGardemployeeService(IEsbgardEmployeeService gardemployeeService) {
		this.gardemployeeService = gardemployeeService;
	}

	*//**
	 * 
	 * Describe:��ʾ��Ա��԰��Ȩ�޹���
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2015-3-12
	 *//*
	public String showAllEsbgardEmployee(){
		url=new StringBuffer();
		url.append(getRequest().getContextPath() + getRequest().getServletPath());
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
		
		//��ȡԱ��
		List employeelist = gardemployeeService.getEmployeeAllList();
		getRequest().setAttribute("employeelist", employeelist);
		
		Long iemployeeid=null;
		
		if(esbgardemp!=null&&esbgardemp.getIemployeeid()!=null&&!esbgardemp.getIemployeeid().equals("")){
			url.append("?esbtickemp.iemployeeid="+esbgardemp.getIemployeeid());
			iemployeeid=esbgardemp.getIemployeeid();
		}else{
			esbgardemp=new Esbgardemployee();
			if(employeelist!=null&&employeelist.size()>0){
				Esfemployeetab esf =(Esfemployeetab)employeelist.get(0);
				esbgardemp.setIemployeeid(esf.getIemployeeid());
			}
			
		}
		
		ps=gardemployeeService.queryListempGarden(iemployeeid, pageSize, Integer.parseInt(page), url.toString());
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2015-3-12
	 *//*
	public String preformAddEsbgardemp(){
		this.strutsAction=ADD;
		
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext().getSession().get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
			if(scenics==null||scenics.equals("")){
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}
		
		if(esbgardemp.getIemployeeid()==null||esbgardemp.getIemployeeid().equals("")){
			this.addActionError("��ѡ��Ա��!");
			return INPUT;
		}
		
		//��ȡԱ��
		List employeelist = gardemployeeService.getEmployeeAllList();
		getRequest().setAttribute("employeelist", employeelist); 
		
		List gardlist=gardemployeeService.findListGardengates(scenics);
		getRequest().setAttribute("gardlist", gardlist); 
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�޸�
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2015-3-12
	 *//*
	public String preformEditEsbgardemp(){
		this.strutsAction=EDIT;
		
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext().getSession().get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
			if(scenics==null||scenics.equals("")){
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}
		
		esbgardemp=(Esbgardemployee)this.genericService.get(Esbgardemployee.class, esbgardemp.getSeq());
		
		//��ȡԱ��
		List employeelist = gardemployeeService.getEmployeeAllList();
		getRequest().setAttribute("employeelist", employeelist); 
		
		List gardlist=gardemployeeService.findListGardengates(scenics);
		getRequest().setAttribute("gardlist", gardlist); 
		
		List lst=gardemployeeService.checkListGardengates(esbgardemp.getIemployeeid());
		getRequest().setAttribute("lst", lst); 
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:ɾ��
	 * @author:lijingrui
	 * @return
	 * @throws Exception
	 * return:String
	 * Date:2015-3-12
	 *//*
	public String preformDelEsbgardemp() throws Exception{
		this.strutsAction=DELETE;
		esbgardemp=gardemployeeService.getviewEsbgardEmp(esbgardemp.getSeq());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�鿴
	 * @author:lijingrui
	 * @return
	 * @throws Exception
	 * return:String
	 * Date:2015-3-12
	 *//*
	public String preformViewEsbgardemp() throws Exception{
		this.strutsAction=VIEW;
		esbgardemp=gardemployeeService.getviewEsbgardEmp(esbgardemp.getSeq());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2015-2-6
	 *//*
	public String saveEsbgardemp(){
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		
		if(this.strutsAction==ADD||this.strutsAction==EDIT){
			if(this.strutsAction==ADD){
				if(esbgardemp.getIemployeeid()==null||esbgardemp.getIemployeeid().equals("")){
					this.addActionError("��ѡ��Ա��!");
					return INPUT;
				}
			}
			
			if(gardenes==null||gardenes.length<1){
				this.addActionError("��ѡ��԰��!");
				return INPUT;
			}
		}
		
		if(this.strutsAction==ADD){
			boolean isuse = this.genericService.volidateSole(new String[]{"iemployeeid"},new Long[]{esbgardemp.getIemployeeid()},new String[]{}, new String[]{}, "Esbgardemployee");
			if(!isuse){
				this.addActionError(getText("��ѡ���Ա���Ѵ���԰��Ȩ��,������ѡ��Ա�����޸�!"));
				return INPUT;
			}
			
			this.gardemployeeService.insertEsbgardEmp(esbgardemp, gardenes, syslog);
			this.addActionMessage(getText("����Ա��԰��Ȩ�޳ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==EDIT){
			this.gardemployeeService.updateEsbgardEmp(esbgardemp, gardenes, syslog);
			this.addActionMessage(getText("�޸�Ա��԰��Ȩ�޳ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==DELETE){
			this.gardemployeeService.deleteEsbgardEmp(esbgardemp, syslog);
			this.addActionMessage(getText("ɾ��Ա��԰��Ȩ�޳ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		
		return SUCCESS;
	}

}

*/