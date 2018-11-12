/*package com.ectrip.ticket.salesmanager.action;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Edmcrowdkindpricetab;
import com.ectrip.model.salesmanager.Osppostsaleslimitstab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.employee.service.iservice.IESPPostsTabService;
import com.ectrip.system.provider.service.iservice.ICrowdKindPriceService;
import com.ectrip.system.salesmanager.service.iservice.IPostSalesLimitsService;
import com.opensymphony.xwork2.ActionContext;
*//**
 * ��λ����Ȩ��
 * @author huangyuqi
 *//*
public class PostSaleLimitsAction extends BaseAction {
	private  Osppostsaleslimitstab postsalelimits;
	private String page;
	private PaginationSupport ps;
	private String url;
	private int pageSize = PaginationSupport.PAGESIZE;
	
	private IESPPostsTabService esppostService;
	private ICrowdKindPriceService crowdkindpriceService;
	private IPostSalesLimitsService postsalelimitsService;
	
	public Osppostsaleslimitstab getPostsalelimits() {
		return postsalelimits;
	}
	public void setPostsalelimits(Osppostsaleslimitstab postsalelimits) {
		this.postsalelimits = postsalelimits;
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
	
	public IESPPostsTabService getEsppostService() {
		return esppostService;
	}
	public void setEsppostService(IESPPostsTabService esppostService) {
		this.esppostService = esppostService;
	}
	public ICrowdKindPriceService getCrowdkindpriceService() {
		return crowdkindpriceService;
	}
	public void setCrowdkindpriceService(
			ICrowdKindPriceService crowdkindpriceService) {
		this.crowdkindpriceService = crowdkindpriceService;
	}
	public IPostSalesLimitsService getPostsalelimitsService() {
		return postsalelimitsService;
	}
	public void setPostsalelimitsService(
			IPostSalesLimitsService postsalelimitsService) {
		this.postsalelimitsService = postsalelimitsService;
	}
	*//**
	 * ��λȨ������
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleAdd(){
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
		
		//��ȡ��λ
		List postlist = esppostService.getAllPost(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("postlist", postlist);
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(esfemployeetab.getIemployeeid());
		List salepricelist = new ArrayList();
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Osppostsaleslimitstab postlimit  = new Osppostsaleslimitstab();//��������Ȩ�ޱ�
				
				postlimit.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				postlimit.setStrcrowdkindprice(price.getStrpricename());//�۸��������
				salepricelist.add(postlimit);	
					
			}
		}
		
		getRequest().setAttribute("pricelist", salepricelist);
		
		return SUCCESS;
	}
	*//**
	 * ��λȨ���޸�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleEdit(){
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
		if(postsalelimits.getIpostsaleslimitsid()==null || "".equals(postsalelimits.getIpostsaleslimitsid())){
			this.addActionError(getText("postsalelimits.id.requried"));//��λ����Ȩ�޲���Ϊ��
			return INPUT;
		}
		postsalelimits = postsalelimitsService.queryPostSaleLimits(postsalelimits.getIpostsaleslimitsid());
		List postlimitlist = postsalelimitsService.queryPostSaleLimitsList(postsalelimits.getIpostsaleslimitsid());
		
		//��ȡ��λ
		List postlist = esppostService.getAllPost(esfemployeetab.getIemployeeid());
		getRequest().setAttribute("postlist", postlist);
		//��ȡ�۸��б�
		List pricelist = crowdkindpriceService.getLimitsPriceList(esfemployeetab.getIemployeeid());
		
		List salepricelist = new ArrayList();
		
		if(pricelist.size()>=1){
			for (int i = 0; i < pricelist.size(); i++) {
				Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
				price=(Edmcrowdkindpricetab)pricelist.get(i) ;//ת���ɼ۸����
				
				Osppostsaleslimitstab postlimit  = new Osppostsaleslimitstab();//Ա������Ȩ�ޱ�
				
				postlimit.setIcrowdkindpriceid(price.getIcrowdkindpriceid());//�۸��� 
				postlimit.setStrcrowdkindprice(price.getStrpricename());//�۸��������				
				
					for (int m = 0; m < postlimitlist.size(); m++) {
						Osppostsaleslimitstab postlimits =(Osppostsaleslimitstab)postlimitlist.get(m);						
						if(price.getIcrowdkindpriceid().equals(postlimits.getIcrowdkindpriceid())){	
							postlimit.setIcrowkindpriceTemp(true);
							break;
						}else {
							postlimit.setIcrowkindpriceTemp(false);
						}
						
					}	
					salepricelist.add(postlimit);						
			}			
		}		
		getRequest().setAttribute("pricelist", salepricelist);		
		return SUCCESS;
	}
	*//**
	 * ��λȨ��ɾ��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleDel(){
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
		if(postsalelimits.getIpostsaleslimitsid()==null || "".equals(postsalelimits.getIpostsaleslimitsid())){
			this.addActionError(getText("postsalelimits.id.requried"));//��λ����Ȩ�޲���Ϊ��
			return INPUT;
		}
		postsalelimits = postsalelimitsService.queryPostSaleLimits(postsalelimits.getIpostsaleslimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ��λȨ�޲鿴
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleView(){
		this.strutsAction=VIEW;//�鿴
		if(postsalelimits.getIpostsaleslimitsid()==null || "".equals(postsalelimits.getIpostsaleslimitsid())){
			this.addActionError(getText("postsalelimits.id.requried"));//��λ����Ȩ�޲���Ϊ��
			return INPUT;
		}
		postsalelimits = postsalelimitsService.queryPostSaleLimits(postsalelimits.getIpostsaleslimitsid());
		
		return SUCCESS;
	}
	*//**
	 * ����
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleSave(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		List postlimitsList = new ArrayList();
		if(strutsAction==ADD || strutsAction==EDIT){
			if(strutsAction==EDIT){
				if(postsalelimits.getIpostsaleslimitsid()==null || "".equals(postsalelimits.getIpostsaleslimitsid())){
					this.addActionError(getText("postsalelimits.id.requried"));//��λ����Ȩ�޲���Ϊ��
				}
			}
			if(postsalelimits.getIpostsid()==null || "".equals(postsalelimits.getIpostsid())){
				this.addActionError(getText("error.szpostscode.id.required"));//��λ��Ų���Ϊ��
				return INPUT;
			}
			
			
			Long[] zprno = postsalelimits.getIcrowdkindpriceids();//�۸�����
			if(zprno==null || "".equals(zprno)){
				this.addActionError("crowdkindprice.priceid.required");//�۸��Ų���Ϊ��
				return INPUT;
			}
			
				for (int i = 0; i < zprno.length; i++) {
					Osppostsaleslimitstab post = new Osppostsaleslimitstab();
					post.setIpostsid(postsalelimits.getIpostsid());
					post.setIcrowdkindpriceid(zprno[i]);
					postlimitsList.add(post);
				}
		}
		if(strutsAction==DELETE){
			if(postsalelimits.getIpostsaleslimitsid()==null || "".equals(postsalelimits.getIpostsaleslimitsid())){
				this.addActionError(getText("postsalelimits.id.requried"));//��λ����Ȩ�޲���Ϊ��
			}
		}
		if(hasActionErrors()){
			return INPUT;
		}
		
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());//������Ա
		
		if(strutsAction==ADD){//����
			long maxpostsale = this.genericService.getMaxPk("ipostsaleslimitsid", "Osppostsaleslimitstab");
			postsalelimits.setIpostsaleslimitsid(maxpostsale+1);
			
			//����
			postsalelimitsService.insertPostSaleLimits(postlimitsList, syslog);
			this.addActionMessage(getText("success.postsalelimits.add")+WebContant.DOMAINAME);//��λ����Ȩ�����ӳɹ�
		}
		
		if(strutsAction==EDIT){//�޸�
			postsalelimitsService.updatePostSaleLimits(postlimitsList, syslog);
			this.addActionMessage(getText("success.postsalelimits.edit")+WebContant.DOMAINAME);//��λ����Ȩ���޸ĳɹ�
		}
		
		if(strutsAction==DELETE){//ɾ��
			postsalelimitsService.deletePostSaleLimits(postsalelimits.getIpostsaleslimitsid(), syslog);
			this.addActionMessage(getText("success.postsalelimits.del")+WebContant.DOMAINAME);//��λ����Ȩ��ɾ���ɹ�
		}
		
		return SUCCESS;
	}
	*//**
	 * ��λȨ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-10-5
	 *//*
	public String postSaleViewList(){
		//���ʵ�ַ
		url = getRequest().getContextPath()+getRequest().getServletPath();
		//ҳ��
		if(getPage()==null || "".equals(getPage())){
			setPage("1");
		}
		ps = postsalelimitsService.getPostSaleLimitsList(Integer.parseInt(page), pageSize, url);
		return SUCCESS;
	}
	
	

}

*/