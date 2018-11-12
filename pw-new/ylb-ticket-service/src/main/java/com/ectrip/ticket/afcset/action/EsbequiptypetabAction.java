/*package com.ectrip.system.afcset.action;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Esbequiptypetab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService;
import com.ectrip.system.syspar.service.iservice.ISysparService;
import com.opensymphony.xwork2.ActionContext;

*//**
 * �豸����
 * @author lijingrui
 *//*
public class EsbequiptypetabAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private IEsbequiptypetabService esbequiptypetabService;
	
	private Esbequiptypetab esbequiptypetab;
	
	private PaginationSupport ps;
	
	private ISysparService sysparService;   //ϵͳ����
	
	private String url = "";  					 // ���ڷ�ҳ��url

	private int pageSize = PaginationSupport.PAGESIZE;   	// ��ҳҳ��С

	private String page = "";             	// ��ȡ��ҳҳ��
	
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

	public int getPageSize() {
		return pageSize;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IEsbequiptypetabService getEsbequiptypetabService() {
		return esbequiptypetabService;
	}

	public void setEsbequiptypetabService(
			IEsbequiptypetabService esbequiptypetabService) {
		this.esbequiptypetabService = esbequiptypetabService;
	}

	public Esbequiptypetab getEsbequiptypetab() {
		return esbequiptypetab;
	}

	public void setEsbequiptypetab(Esbequiptypetab esbequiptypetab) {
		this.esbequiptypetab = esbequiptypetab;
	}
	
	*//**
	 * 
	 * Describe:��ʾ���е��豸������Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 *//*
	public String showAllListesbequitype() {
		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (getPage() == null || getPage().equals("")) {
			setPage("1");
		}
		Esfemployeetab esfemployeetab= (Esfemployeetab) ActionContext.getContext().getSession()
		.get("employee");
		if (esfemployeetab != null) {
			String scenics = "";
			if (esfemployeetab.getCompanytype().equals("02")) {
				scenics = esfemployeetab.getScenics();
			}
			ps = this.esbequiptypetabService.showListprdcontrol(scenics, pageSize, Integer.parseInt(page),url);
		}
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����豸������Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 *//*
	public String preformAddEsbequitype(){
		this.strutsAction=ADD;
		//������
		Esfemployeetab esfemployeetab= (Esfemployeetab) ActionContext.getContext().getSession()
		.get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
		}	
		getRequest().setAttribute("scenlist", this.esbequiptypetabService.getListscenicar(scenics));
		//ͨѶ��ʽ
		getRequest().setAttribute("cmselist", this.sysparService.retrieve("CMSE"));
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�޸��豸������Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 *//*
	public String preformEditEsbequitype(){
		this.strutsAction=EDIT;
		
		esbequiptypetab=(Esbequiptypetab) this.genericService.get(Esbequiptypetab.class, esbequiptypetab.getIequiptypeid());
		//������
		Esfemployeetab esfemployeetab= (Esfemployeetab) ActionContext.getContext().getSession()
		.get("employee");
		String scenics = "";
		if (esfemployeetab.getCompanytype().equals("02")) {
			scenics = esfemployeetab.getScenics();
		}	
		getRequest().setAttribute("scenlist", this.esbequiptypetabService.getListscenicar(scenics));
		//ͨѶ��ʽ
		getRequest().setAttribute("cmselist", this.sysparService.retrieve("CMSE"));
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:ɾ���豸������Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 * @throws Exception 
	 *//*
	public String preformDelEsbequitype() throws Exception{
		this.strutsAction=DELETE;
		
		esbequiptypetab=this.esbequiptypetabService.getviewequiptype(esbequiptypetab.getIequiptypeid());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�鿴�豸������Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 * @throws Exception 
	 *//*
	public String preformViewEsbequitype() throws Exception{
		this.strutsAction=VIEW;
		esbequiptypetab=this.esbequiptypetabService.getviewequiptype(esbequiptypetab.getIequiptypeid());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-11-1
	 *//*
	public String saveEsbequitype(){
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		if(this.strutsAction==ADD){
			boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szequiptypemodel"}, new String[]{esbequiptypetab.getSzequiptypemodel()}, "Esbequiptypetab");
			if(!isuse){
				this.addActionError(getText("esbequiptypetab.szequiptypemodel.ishouvon"));
				return INPUT;
			}
			this.esbequiptypetabService.insertesbequiptype(esbequiptypetab, syslog);
			this.addActionMessage(getText("success.esbequiptypetab.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==EDIT){
			Esbequiptypetab esb=(Esbequiptypetab) this.genericService.get(Esbequiptypetab.class, esbequiptypetab.getIequiptypeid());
			if(!esbequiptypetab.getSzequiptypemodel().equals(esb.getSzequiptypemodel())){
				boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szequiptypemodel"}, new String[]{esbequiptypetab.getSzequiptypemodel()}, "Esbequiptypetab");
				if(!isuse){
					this.addActionError(getText("esbequiptypetab.szequiptypemodel.ishouvon"));
					return INPUT;
				}
			}
			this.esbequiptypetabService.updateesbequiptype(esbequiptypetab, syslog);
			this.addActionMessage(getText("success.esbequiptypetab.edit")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==DELETE){
			this.esbequiptypetabService.deleteesbequiptype(esbequiptypetab, syslog);
			this.addActionMessage(getText("success.esbequiptypetab.del")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:��֤
	 * @auth:lijingrui
	 * return:void
	 * Date:2011-11-1
	 *//*
	public void validateSaveEsbequitype(){
		if(this.strutsAction==ADD||this.strutsAction==EDIT){
			if(esbequiptypetab.getSzequiptypemodel()==null||esbequiptypetab.getSzequiptypemodel().equals("")){
				this.addActionError(getText("errors.esbequiptypetab.szequiptypemodel.required"));        //�豸�����ͺ�
			}
			if(esbequiptypetab.getSzequiptypemodel()!=null&&!esbequiptypetab.getSzequiptypemodel().equals("")){
				if(esbequiptypetab.getSzequiptypemodel().length()>15){
					this.addActionError(getText("errors.esbequiptypetab.szequiptypemodel.length"));    //�豸�����ͺŵĳ���
				}
			}
			
			if(esbequiptypetab.getSzequiptypename()==null||esbequiptypetab.getSzequiptypename().equals("")){
				this.addActionError(getText("errors.esbequiptypetab.szequiptypename.required"));      //�豸��������
			}
			if(esbequiptypetab.getSzequiptypename()!=null&&!esbequiptypetab.getSzequiptypename().equals("")){
				if(esbequiptypetab.getSzequiptypename().length()>25){
					this.addActionError(getText("errors.esbequiptypetab.szequiptypename.length"));    //�豸�������Ƶĳ���
				}
			}
			
			
		}
	}
}

*/