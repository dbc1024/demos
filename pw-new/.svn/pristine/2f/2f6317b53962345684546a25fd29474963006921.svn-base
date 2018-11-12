/*package com.ectrip.sys.message.controller;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.message.service.iservice.IContMessageService;
import com.ectrip.model.permitenter.Contmessage;
import com.ectrip.system.syspar.service.iservice.ISysparService;

public class ContMessageAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private Contmessage message;
	
	private IContMessageService contmessageService;
	
	private ISysparService sysparService;   //ϵͳ����
	
	private PaginationSupport ps;
	
	private String url = "";  		 // ���ڷ�ҳ��url
	
	private String page;//ҳ��
	
	private int pageSize = PaginationSupport.PAGESIZE;//ÿҳ��ʾ��

	public Contmessage getMessage() {
		return message;
	}

	public void setMessage(Contmessage message) {
		this.message = message;
	}

	public IContMessageService getContmessageService() {
		return contmessageService;
	}

	public void setContmessageService(IContMessageService contmessageService) {
		this.contmessageService = contmessageService;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
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
	
	*//**
	 * 
	 * Describe:��ʾ���еĶ��ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 *//*
	public String showAllContMessage(){
		
		url = getRequest().getContextPath() + getRequest().getServletPath();
		
		if (page == null || page.equals("")) {
			page = "1";
		}
		
		ps=this.contmessageService.showALLContMessage(Integer.parseInt(page), pageSize, url);
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�������ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 *//*
	public String prefromAddContMessage(){
		this.strutsAction=ADD;
		getRequest().setAttribute("contList", this.sysparService.retrieve("MESG"));
		message=new Contmessage();
		message.setByisuse(new Long(1));
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�޸Ķ��ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 *//*
	public String prefromEditContMessage(){
		this.strutsAction=EDIT;
		message=(Contmessage) this.genericService.get(Contmessage.class, message.getConid());
		getRequest().setAttribute("contList", this.sysparService.retrieve("MESG"));
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:ɾ�����ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 * @throws Exception 
	 *//*
	public String prefromDelContMessage() throws Exception{
		this.strutsAction=DELETE;
		message=this.contmessageService.viewContMessage(message.getConid());
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�鿴���ŷ��Ϳ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 * @throws Exception 
	 *//*
	public String prefromViewContMessage() throws Exception{
		this.strutsAction=VIEW;
		message=this.contmessageService.viewContMessage(message.getConid());
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:���� 
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:Mar 12, 2012
	 *//*
	public String saveContMessage(){
		if(this.strutsAction==ADD){
			boolean isuse=this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"controlpoin"}, new String[]{message.getControlpoin()}, "Contmessage");
			if(!isuse){
				this.addActionError(getText("message.controlpoin.ishouvon"));
				return INPUT;
			}
			this.contmessageService.addContMessage(message);
			this.addActionMessage(getText("success.message.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==EDIT){
			Contmessage cont=(Contmessage) this.genericService.get(Contmessage.class, message.getConid());
			if(!cont.getControlpoin().equals(message.getControlpoin())){
				boolean isuse=this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"controlpoin"}, new String[]{message.getControlpoin()}, "Contmessage");
				if(!isuse){
					this.addActionError(getText("message.controlpoin.ishouvon"));
					return INPUT;
				}
			}
			
			this.contmessageService.editContMessage(message);
			this.addActionMessage(getText("success.message.edit")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(this.strutsAction==DELETE){
			this.contmessageService.delContMessage(message.getConid());
			this.addActionMessage(getText("success.message.del")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:��֤
	 * @auth:lijingrui
	 * return:void
	 * Date:Mar 12, 2012
	 *//*
	public void validateSaveContMessage(){
		if(this.strutsAction==ADD||this.strutsAction==EDIT){
			//���ŷ��Ϳ������Ʋ���Ϊ��
			if(message.getControlpoin()==null||message.getControlpoin().equals("")){
				this.addActionError(getText("message.controlpoin.isnull.required"));
			}
			
			//����ģ�岻��Ϊ��
			if(message.getTemplates()==null||message.getTemplates().equals("")){
				this.addActionError(getText("message.templates.required"));
			}
		}
	}
	
}

*/