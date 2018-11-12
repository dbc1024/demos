package com.ectrip.sys.employee.controller;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IESPDutyTabService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.service.ISysparService;
import com.ectrip.upload.model.Upfile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 职责管理相关接口
 * @author lijingrui
 */
@RestController
@Api(tags = "系统服务模块-职责管理相关接口")
@RequestMapping(value = "depts")
public class ESPDutyTabController{

	@Autowired
	private IESPDutyTabService espdutyService;
	
	@Autowired
	private ISysparService sysparService;   

	@GetMapping(value = "/v1/listEspduty")
	@ApiOperation(value = "分页查看职责信息")
	public ResponseBean listEspduty(@RequestBody(required = false) Espdutytab parentduty,@RequestParam(required = false) String szdutyname, @RequestParam(required = false) String dutype, @RequestParam int page,@RequestParam int pageSize) {
			ResponseBean result = new ResponseBean();
			int code = 200;
			String msg = "请求成功";
			PaginationSupport ps = null;
			
			if (parentduty == null || parentduty.getIdutyid() == null || parentduty.getIdutyid().equals("")||parentduty.getIdutyid()==0) {
				parentduty = new Espdutytab();
				parentduty.setIdutyid(new Long(0));
				parentduty.setIlevel(new Long(0));
				parentduty.setIparentid(new Long(0));
				parentduty.setIrootid(new Long(0));
			} else {
				parentduty = (Espdutytab) this.espdutyService.get(Espdutytab.class, parentduty.getIdutyid());
			}
			
			ps = espdutyService.findPage(parentduty.getIdutyid(), szdutyname,dutype, pageSize,page, null);
		return result;
	}

	// ���ݲ㼶��ʾְ����Ϣ �ϼ�
	/*public String upleaveDuty() {
			//��ȡ�ϼ��б� ������ڵ�ĸ��ڵ�IDΪ�� �򷵻ش���
			if (parentduty == null || parentduty.getIparentid() == null || parentduty.getIparentid().equals("")) {
				this.addActionError(getText("espduty.iparentid.error"));
				return INPUT;
			} else {
				url.append(getRequest().getContextPath() + getRequest().getServletPath());
				if (getPage() == null || getPage().equals("")) {// ҳ��
					setPage("1");
				}
				//������ڵ�ĸ��ڵ�IDΪ0������һ������
				if(parentduty.getIparentid()==0){
					parentduty = new Espdutytab();
					parentduty.setIdutyid(new Long(0));
					parentduty.setIlevel(new Long(0));
					parentduty.setIparentid(new Long(0));
					parentduty.setIrootid(new Long(0));
				}else{
					//�����ȡ���ڵ�ĸ��ڵ����
					parentduty = (Espdutytab) this.genericService.get(Espdutytab.class, parentduty.getIparentid());
					//���󲻴���
					if(parentduty==null){
						this.addActionError(getText("parentduty.required"));
						return INPUT;
					}
				}
				url.append("?parentduty.iparentid="+parentduty.getIparentid()+"");
				
				ps = espdutyService.findPage(parentduty.getIparentid(), szdutyname,dutype, pageSize, Integer
						.parseInt(page), url.toString());
			}

		return SUCCESS;
	}

	*//**
	 * ��ת������ҳ��
	 *//*
	public String preformAddDuty() {
		this.strutsAction = ADD;
		// TODO ��Ҫ����ϵͳ������ѯ �����б�
		//������ڵ�ID Ϊ��������һ�����ڵ�
		if (parentduty == null || parentduty.getIdutyid() == null || parentduty.getIdutyid().equals("")||parentduty.getIdutyid()==0) {
			parentduty = new Espdutytab();
			parentduty.setIdutyid(new Long(0));
			parentduty.setIrootid(new Long(0));
			parentduty.setIlevel(new Long(0));
			parentduty.setBisleaf(new Long(0));
		} else {
			//�����Ϊ�� ��ȡ���ڵ�
			parentduty = (Espdutytab) this.genericService.get(Espdutytab.class, parentduty.getIdutyid());
			if(parentduty==null){
				this.addActionError(getText("parentduty.required"));
				return INPUT;
			}else{
				if (parentduty.getBisleaf()==1) {//��Ҷ�ӽڵ� ��Ҷ�ӽڵ� �������
					this.addActionError(getText("espduty.bisleaf"));
					return INPUT;
				}
			}
		}
		//ְ������
		getRequest().setAttribute("zztpList", this.sysparService.retrieve("ZZTP"));
		//ְ�����
		getRequest().setAttribute("ddtpList", this.sysparService.retrieve("DDTP"));
		//Ȩ������
		getRequest().setAttribute("qxtpList", this.sysparService.retrieve("QXTP"));
		
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:�鿴ְ��
	 * 
	 * @auth:yangguang
	 * @return return:String Date:2011-9-17
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	public String viewDuty() throws IllegalAccessException, InvocationTargetException {
		this.strutsAction = VIEW;
		// TODO ��Ҫ����ϵͳ������ѯ �����б�
		if (espduty == null || espduty.getIdutyid() == null || espduty.getIdutyid().equals("")) {
			this.addActionError(getText("espduty.idutyid.required"));
			return INPUT;
		} else {
			// TODO ���������� ��ʾְ��
			espduty = (Espdutytab) this.espdutyService.retrieve(espduty.getIdutyid());
			if(espduty==null){
				this.addActionError(getText("espduty.required"));
				return INPUT;
			}
		}
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:ɾ�� �鿴ְ��
	 * 
	 * @auth:yangguang
	 * @return return:String Date:2011-9-17
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	public String deleteDuty() throws IllegalAccessException, InvocationTargetException {
		this.strutsAction = DELETE;
		// TODO ��Ҫ����ϵͳ������ѯ �����б�
		if (espduty == null || espduty.getIdutyid() == null || espduty.getIdutyid().equals("")) {
			this.addActionError(getText("espduty.idutyid.required"));
			return INPUT;
		} else {
			// TODO ���������� ��ʾְ��
			espduty = (Espdutytab) this.espdutyService.retrieve(espduty.getIdutyid());
			if(espdutyService.ishaveson(espduty.getIdutyid())){
				this.addActionError(getText("espduty.haveson"));
				return INPUT;
			}
		}
		return SUCCESS;
	}

	*//**
	 * ��ת���޸�ҳ��
	 *//*
	public String preformEditDuty() {
		this.strutsAction = EDIT;
		// TODO ��Ҫ����ϵͳ������ѯ �����б�
		if (espduty == null || espduty.getIdutyid() == null || espduty.getIdutyid().equals("")) {
			this.addActionError(getText("espduty.idutyid.required"));
			return INPUT;
		} else {
			// TODO ���������� ��ʾְ�� �Ѳ����б��syspar�в�ѯ����
			espduty = (Espdutytab) this.genericService.get(Espdutytab.class, espduty.getIdutyid());
			System.out.println("espduty"+" "+espduty);
			
			//�ΰ�����������ѯ��ʱ��
			List listf =this.genericService.find("from Upfile u where u.upid='"+espduty.getPicidf()+"'");
			if(listf!=null&&listf.size()>0){
				Upfile upfile=(Upfile)listf.get(0);
				espduty.setPicurlf(upfile.getUpadder()+upfile.getUpfilename());
			}
			
			
			
			List lists =this.genericService.find("from Upfile u where u.upid='"+espduty.getPicids()+"'");
			if(lists!=null&&lists.size()>0){
				Upfile upfile=(Upfile)lists.get(0);
				espduty.setPicurls(upfile.getUpadder()+upfile.getUpfilename());
			}
			
			//ְ������
			getRequest().setAttribute("zztpList", this.sysparService.retrieve("ZZTP"));
			//ְ�����
			getRequest().setAttribute("ddtpList", this.sysparService.retrieve("DDTP"));
			//Ȩ������
			getRequest().setAttribute("qxtpList", this.sysparService.retrieve("QXTP"));
		}
		return SUCCESS;
	}

	*//**
	 * ���� �޸� ɾ�� ���� Describe:
	 * 
	 * @auth:yangguang
	 * @return return:String Date:2011-9-17
	 *//*
	public String saveDuty() {
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		if (this.strutsAction == ADD) {
			//������ڵ�IDΪ�� ���ߵ���0 ����һ�����ڵ�
			if(parentduty==null||parentduty.getIdutyid()==null||parentduty.getIdutyid().equals("")||parentduty.getIdutyid()==0){
				parentduty = new Espdutytab();
				parentduty.setIdutyid(new Long(0));
				parentduty.setIrootid(new Long(0));
				parentduty.setIlevel(new Long(0));
				parentduty.setBisleaf(new Long(0));//���ڵ� Ĭ�ϲ���Ҷ�ӽڵ�
			}else{
				parentduty=(Espdutytab) this.genericService.get(Espdutytab.class, parentduty.getIdutyid());
				if(parentduty==null){
					this.addActionError(getText("parentduty.required"));
					return INPUT;
				}
			}
			
			boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szdutycode"}, new String[]{espduty.getSzdutycode()}, "Espdutytab");
			if(!isuse){
				//this.addActionError(getText("espduty.szdutycode.ishouvon"));
				this.addActionError(getText("��ְ������Ѵ��ڣ����������룡"));
				return INPUT;
			}			
			espdutyService.insertduty(espduty,parentduty,dutypes,syslog);
			this.addActionMessage(getText("success.espduty.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		} else if (this.strutsAction == EDIT) {
			if (espduty.getIdutyid() == null || espduty.getIdutyid().equals("")) {
				this.addActionError(getText("espduty.idutyid.required"));
				return INPUT;
			} else {
				if (espdutyService.ishaveson(espduty.getIdutyid())) {
					Espdutytab duty = (Espdutytab) this.genericService.get(Espdutytab.class, espduty.getIdutyid());
					if (!espduty.getBisleaf().equals(duty.getBisleaf())) {
						this.addActionError(getText("espduty.cantupdate"));
						return INPUT;
					}
				}
				Espdutytab es = (Espdutytab) this.genericService.get(Espdutytab.class, espduty.getIdutyid());
				if(!espduty.getSzdutycode().equals(es.getSzdutycode())){
					boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szdutycode"}, new String[]{espduty.getSzdutycode()}, "Espdutytab");
					if(!isuse){
						this.addActionError(getText("espduty.szdutycode.ishouvon"));
						return INPUT;
					}
				}
				espdutyService.updateduty(espduty,syslog);
				this.addActionMessage(getText("success.espduty.edit")+"����ӭʹ��"+WebContant.DOMAINAME);
			}
		} 
		return SUCCESS;
	}
	
	
	*//**
	 * ɾ��ְ�� ����
	 * @return
	 *//*
	public String deleteSave(){
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
		.getSession().get("employee");
		Syslog syslog = new Syslog();   //��־
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		// TODO �ж� ������ӽڵ�� ����ɾ��.
		if (espduty.getIdutyid() == null || espduty.getIdutyid().equals("")) {
			this.addActionError(getText("espduty.idutyid.required"));
			return INPUT;
		}
		espduty = (Espdutytab) this.genericService.get(Espdutytab.class, espduty.getIdutyid());
		if (espduty == null) {
			this.addActionError(getText("espduty.empty"));
			return INPUT;
		}
		if (espdutyService.ishaveson(espduty.getIdutyid())) {
			this.addActionError(getText("espduty.haveson"));
			return INPUT;
		}
		espdutyService.deleteduty(espduty,syslog);
		this.addActionMessage(getText("success.espduty.del")+"����ӭʹ��"+WebContant.DOMAINAME);
		return SUCCESS;
	}

	*//**
	 * ������֤
	 *//*
	public void validateSaveDuty() {
		if (espduty == null) {
			this.addActionError(getText("espduty.required"));
		} else {
			if (espduty.getSzdutycode() == null || espduty.getSzdutycode().equals("")) {
				this.addActionError(getText("source.szdutycode.required"));// ְ����벻Ϊ��
			}
			if (espduty.getSzdutyname() == null || espduty.getSzdutyname().equals("")) {
				this.addActionError(getText("source.szdutyname.required"));// ְ�����Ʋ�Ϊ��
			}
			if (espduty.getSzgotopage() == null || espduty.getSzgotopage().equals("")) {
				this.addActionError(getText("source.szgotopage.required"));// ְ�����Ӳ�Ϊ��
			}
			
			if (espduty.getSzdutycode() != null &&!espduty.getSzdutycode().equals("")) {
				if(espduty.getSzdutycode().length()>15){
					this.addActionError(getText("source.szdutycode.length"));// ְ����볤��
				}
			}
			if (espduty.getSzdutyname() != null &&!espduty.getSzdutyname().equals("")) {
				if(espduty.getSzdutyname().length()>25){
					this.addActionError(getText("source.szdutyname.length"));// ְ�����Ƴ���
				}
			}
			if (espduty.getSzgotopage() != null &&! espduty.getSzgotopage().equals("")) {
				if(espduty.getSzgotopage().length()>250){
					this.addActionError(getText("source.szgotopage.length"));// ְ�����ӳ���
				}
			}
			
			
			if (espduty.getBypurviewtype() == null || espduty.getBypurviewtype().equals("")) {
				this.addActionError(getText("source.bypurviewtype.required"));// Ȩ�����Ͳ�Ϊ��
			}
			if (espduty.getBycategorytype() == null || espduty.getBycategorytype().equals("")) {
				this.addActionError(getText("source.bycategorytype.required"));// ְ�����Ͳ�Ϊ��
			}
			if(espduty.getIssellticket()==null||espduty.getIssellticket().equals("")){
				this.addActionError(getText("source.issellticket.required"));  //�Ƿ�����ƱȨ��
			}
			if (espduty.getSzorderby() == null || espduty.getSzorderby().equals("")) {
				this.addActionError(getText("source.szorderby.required"));// ��ʾ˳��Ϊ��
			}
			if(strutsAction==ADD){
				if(dutypes==null||dutypes.length<=0){
					this.addActionError(getText("source.dutypes.required"));
				}
			}
			
//			if (espduty.getSzorderby() != null&& !espduty.getSzorderby().equals("")) {
//				Pattern p = Pattern.compile("^[0-9]*$");
//				boolean b = p.matcher(espduty.getSzorderby().toString()).matches();
//				if (b == false) {
//					this.addActionError(getText("espduty.szorderby.required"));   //��ʾ˳���ʽ����
//				}
//			}
			
			if (espduty.getSzmemo() == null || espduty.getSzmemo().equals("")) {
				this.addActionError(getText("source.szmemo.required"));// ְ��������Ϊ��
			}
		}
	}
	
	*//**
	 * 
	 * Describe:����ְ��XML�ļ�
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-1-11
	 * @throws Exception 
	 *//*
	public String preformDerivexml() throws Exception{
		 // �������ڵ� list;   
        Element root = new Element("ectrip");   
       // ���ڵ���ӵ��ĵ��У�   
        Document Doc = new Document(root);   
       // �˴� for ѭ�����滻�� ���� ���ݿ��Ľ��������;   
        List list=this.espdutyService.queryListduty();
       for (int i = 0; i < list.size(); i++) {
    	   Object [] obj=(Object[])list.get(i);
    	   
           // �����ڵ� row;   
           Element elements = new Element("row");   
           // �� row �ڵ�������� id;   
//           elements.setAttribute("id", "" + i);  
           // �� row �ڵ�����ӽڵ㲢��ֵ��   
           // new Element("name")�е� "name" �滻�ɱ�����Ӧ�ֶΣ�setText("xuehui")�� "xuehui �滻�ɱ��м�¼ֵ��   
           elements.addContent(new Element("opid").setText(obj[0].toString()));  
           elements.addContent(new Element("sopid").setText(obj[1].toString()));  
           elements.addContent(new Element("opname").setText(obj[2].toString()));  
           // �����ڵ�list���user�ӽڵ�;  
           root.addContent(elements);  
       } 
       	Format format = Format.getPrettyFormat();
        XMLOutputter XMLOut = new XMLOutputter(format);  
       // ��� espduty.xml �ļ���  
        String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/espduty.xml");
        XMLOut.output(Doc, new FileOutputStream(path));  
        
//        javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView(); 
//        System.out.println("����·����"+fsv.getHomeDirectory());
//        XMLOut.output(Doc, new FileOutputStream(fsv.getHomeDirectory()+"/espduty.xml"));  
        
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:����ְ��XML��Ϣ
	 * @auth:lijingrui
	 * @return
	 * @throws Exception
	 * return:String
	 * Date:2013-1-11
	 *//*
	public String showelementList() throws Exception{	
		 SAXBuilder builder=new SAXBuilder();	//������
		 String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/espduty.xml");
		 Map map=new HashMap();	
		 try {		
				Document docment=builder.build(path);//xml�ı�����
				Element root=docment.getRootElement();	//���ڵ�	
				List<Element> names=root.getChildren("row");//�õ�row�ڵ��б�
				int i=1;
				for(Element element:names){			
					int j=1;
					List lst=new ArrayList();
					if(element.getChildTextTrim("sopid").equals("0")){
						for(Element mentls:names){
							if(element.getChildTextTrim("opid").equals(mentls.getChildTextTrim("sopid"))&&!mentls.getChildTextTrim("sopid").equals("0")){
								lst.add(mentls);
							}
							j++;
						}
						
						map.put(element.getChildTextTrim("opid"), lst);
					}
					
					i++;				
				}
				
				Set<Map.Entry<String, List>> set = map.entrySet();
			    for (Iterator<Map.Entry<String, List>> it = set.iterator(); it.hasNext();) {
			        Map.Entry<String, List> entry = (Map.Entry<String, List>) it.next();
			        System.out.println(entry.getKey() + "--->" + entry.getValue());
			        List list=entry.getValue();
			        for(int x=0;x<list.size();x++){
			        	Element obj=(Element)list.get(x);
			        	System.out.println(obj.getChildTextTrim("opname"));
			        }
			    }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return SUCCESS;
	}
	

	public IESPDutyTabService getEspdutyService() {
		return espdutyService;
	}

	public void setEspdutyService(IESPDutyTabService espdutyService) {
		this.espdutyService = espdutyService;
	}

	public Espdutytab getEspduty() {
		return espduty;
	}

	public void setEspduty(Espdutytab espduty) {
		this.espduty = espduty;
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

	public String getSzdutyname() {
		return szdutyname;
	}

	public void setSzdutyname(String szdutyname) {
		this.szdutyname = szdutyname;
	}

	public Espdutytab getParentduty() {
		return parentduty;
	}

	public void setParentduty(Espdutytab parentduty) {
		this.parentduty = parentduty;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}

	public StringBuffer getUrl() {
		return url;
	}

	public void setUrl(StringBuffer url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String[] getDutypes() {
		return dutypes;
	}

	public void setDutypes(String[] dutypes) {
		this.dutypes = dutypes;
	}

	public String getDutype() {
		return dutype;
	}

	public void setDutype(String dutype) {
		this.dutype = dutype;
	}

*/}
