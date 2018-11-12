/*package com.ectrip.system.salesmanager.action;


import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.salesmanager.Ospbankpayeesettab;
import com.ectrip.model.syspar.Sysparv5;
import com.ectrip.system.salesmanager.service.iservice.IOSPBankPayeeSetTabService;
import com.ectrip.system.syspar.service.iservice.ISysparService;

public class OSPBankPayeeSetTabAction extends BaseAction{
	private IOSPBankPayeeSetTabService bankPayeeService;
	
	private List bankList;//�����б�
	private Ospbankpayeesettab payeeBank;
	private String flag;
	private String queryId;
	private String queryMess;
	private ISysparService sysparService;   //ϵͳ����
	
	private PaginationSupport ps;

	private String page;// ҳ��

	private String url = "";// ���ʵ�ַ

	private int pageSize = PaginationSupport.PAGESIZE;// ÿҳ��ʾ��
	
	
	*//**
	 * 
	 * Describe:�б���ʾ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String  showPayeeBanks(){
		url = getRequest().getContextPath() + getRequest().getServletPath();

		if (getPage() == null || getPage().equals("")) {// ҳ��
			setPage("1");
		}
		if(flag==null||flag.equals("")){
			flag="0";
		}
		
		url+="?flag=0";
		
		ps=bankPayeeService.findPage(pageSize, Integer.valueOf(getPage()), url);
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:ģ����ѯ 
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String queryBankInfo(){
		url = getRequest().getContextPath() + getRequest().getServletPath();

		if (getPage() == null || getPage().equals("")) {// ҳ��
			setPage("1");
		}
		if(flag!=null){
			if(flag.equals("0")){
				queryMess="";
				url+="flag=0&queryId="+queryId;
			}else if(flag.equals("1")){
				queryId="";
				url+="flag=1&queryMess="+queryMess;
			}
		}
		
		ps=bankPayeeService.findPage2(queryId, queryMess, pageSize, Integer.valueOf(getPage()), url);
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����y���U����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String performAddBank(){
		this.strutsAction=ADD;
		
		List bankList=this.sysparService.retrieve("BANK");
		Sysparv5 syspar=new Sysparv5();
		syspar.setPmcd("100");
		syspar.setPmva("��ѡ��");
		bankList.add(0, syspar);
		getRequest().setAttribute("bankList", bankList);
		
		payeeBank=new Ospbankpayeesettab();
		payeeBank.setByisuse(1L);
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�޸�
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String performEdit(){
		this.strutsAction=EDIT;
		if(payeeBank==null||payeeBank.getIbankid().equals("")){
			return INPUT;
		}
		List bankList=this.sysparService.retrieve("BANK");
		Sysparv5 syspar=new Sysparv5();
		syspar.setPmcd("100");
		syspar.setPmva("��ѡ��");
		bankList.add(0, syspar);
		getRequest().setAttribute("bankList", bankList);
		
		payeeBank=(Ospbankpayeesettab)this.genericService.get(Ospbankpayeesettab.class, payeeBank.getIbankid());
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String saveBankInfo(){
		if(this.strutsAction==ADD){
			if(payeeBank==null){
				return INPUT;
			}
			
			boolean isuse = this.genericService.volidateSole(new String[]{"byisuse"},new Long[]{1L},new String[]{"szbankname"}, new String[]{payeeBank.getSzbankname()}, "Ospbankpayeesettab");
			if(!isuse){
				this.addActionError("�˽ɿ������Ѵ���,������ѡ��!");
				return INPUT;
			}
			
			List list=bankPayeeService.getCountByCou(payeeBank.getSzbankaccount());
			if(list.size()>0){
				this.addActionError(getText("���ʺ��Ѵ��ڣ�"));
			}else{
				if(payeeBank.getSzbankaccount().length()<16||payeeBank.getSzbankaccount().length()>19){
					this.addActionError(getText("���ʺ��쳣��"));
				}
			}
			
			bankPayeeService.addNewBank(payeeBank);//���
			
		}else if(this.strutsAction==EDIT){
			Ospbankpayeesettab payset=(Ospbankpayeesettab)this.genericService.get(Ospbankpayeesettab.class, payeeBank.getIbankid());
			if(!payeeBank.getSzbankaccount().equals(payset.getSzbankaccount())){
				boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szbankaccount"}, new String[]{payeeBank.getSzbankaccount()}, "Ospbankpayeesettab");
				if(!isuse){
					this.addActionError("���ʺ��Ѵ���,����������!");
					return INPUT;
				}
			}
			
			if(!payeeBank.getSzbankname().equals(payset.getSzbankname())||(!payeeBank.getByisuse().equals(payset.getByisuse())&&payeeBank.getByisuse()==1)){
				boolean isuse = this.genericService.volidateSole(new String[]{"byisuse"},new Long[]{1L},new String[]{"szbankname"}, new String[]{payeeBank.getSzbankname()}, "Ospbankpayeesettab");
				if(!isuse){
					this.addActionError("�˽ɿ������Ѵ���,������ѡ��!");
					return INPUT;
				}
			}
			
			bankPayeeService.updateBankInfo(payeeBank);//�޸�
			
		}
		return SUCCESS;
	}
	*//**
	 * 
	 * Describe:��֤
	 * @auth:lijingrui
	 * return:void
	 * Date:2013-3-2
	 *//*
	public void validateSaveBankInfo(){
		if(getPayeeBank()==null){
			this.addActionError(getText("errors.payeebank.bankinfo.reqiured"));
		}else{
			//��֤��Ϣ
			if(payeeBank.getSzbankname()==null||payeeBank.getSzbankname().equals("")||payeeBank.getSzbankname().equals("100")){
				this.addActionError(getText("��ѡ������"));
			}
			
			if(payeeBank.getSzbankname()==null||payeeBank.getSzbankname().equals("")){
				this.addActionError(getText("errors.payeebank.bankname.reqiured"));
			}
			
			if(payeeBank.getSzbankaccount()==null||payeeBank.getSzbankaccount().equals("")){
				this.addActionError(getText("errors.payeebank.bankcount.reqiured"));
			}
			
			if(payeeBank.getSzbankcode()==null||payeeBank.getSzbankcode().equals("")){
				this.addActionError(getText("errors.payeebank.bankcode.reqiured"));
			}else{
				if(payeeBank.getSzbankcode().length()>=10){
					this.addActionError(getText("���д��������"));
				}
			}
			
			if(payeeBank.getSzmemo()==null||payeeBank.getSzmemo().equals("")){
				this.addActionError(getText("errors.payeebank.bankmemo.reqiured"));
			}else{
				if(payeeBank.getSzmemo().length()>250){
					this.addActionError(getText("��ע���ݹ�����"));
				}
			}
		}
	}
	
	*//**
	 * 
	 * Describe:ɾ��
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String performDeleteBank(){
		this.strutsAction=DELETE;
		if(payeeBank==null||payeeBank.getIbankid().equals("")){
			return INPUT;
		}
		payeeBank=(Ospbankpayeesettab)this.genericService.get(Ospbankpayeesettab.class, payeeBank.getIbankid());
		
		Sysparv5 syspar=sysparService.findOne("BANK",payeeBank.getSzbankname());
		payeeBank.setSzbankname(syspar.getPmva());
		
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:ɾ������
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String deleteBank(){
		if(payeeBank==null||payeeBank.getIbankid().equals("")){
			return INPUT;
		}
		Ospbankpayeesettab pbank=(Ospbankpayeesettab)this.genericService.get(Ospbankpayeesettab.class, payeeBank.getIbankid());
		if(pbank==null){
			return INPUT;
		}
		this.genericService.delete(pbank);
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�鿴���еľ�����Ϣ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-3-2
	 *//*
	public String viewBankInfo(){
		this.strutsAction=VIEW;
		if(payeeBank==null||payeeBank.getIbankid().equals("")){
			return INPUT;
		}
		
		payeeBank=(Ospbankpayeesettab)this.genericService.get(Ospbankpayeesettab.class, payeeBank.getIbankid());
		Sysparv5 syspar=sysparService.findOne("BANK",payeeBank.getSzbankname());
		payeeBank.setSzbankname(syspar.getPmva());
		
		return SUCCESS;
	}
	
	public IOSPBankPayeeSetTabService getBankPayeeService() {
		return bankPayeeService;
	}
	public void setBankPayeeService(IOSPBankPayeeSetTabService bankPayeeService) {
		this.bankPayeeService = bankPayeeService;
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
	
	public Ospbankpayeesettab getPayeeBank() {
		return payeeBank;
	}
	public void setPayeeBank(Ospbankpayeesettab payeeBank) {
		this.payeeBank = payeeBank;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	public String getQueryMess() {
		return queryMess;
	}
	public void setQueryMess(String queryMess) {
		this.queryMess = queryMess;
	}
	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}
	
}
*/