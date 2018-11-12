/*package com.ectrip.system.afcset.action;

import java.util.List;
import java.util.regex.Pattern;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.model.afcset.Esbaccessequiptab;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Esbscenicareatab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService;
import com.ectrip.system.afcset.service.iservice.IEsbgardengatetabService;
import com.ectrip.system.syspar.service.iservice.ISysparService;
import com.opensymphony.xwork2.ActionContext;

*//**
 * Created By Jzhenhua,Time 2011-10-04 10:26<br>
 * ׼���豸����Action
 * 
 * @author lenovo
 *//*
public class EsbaccessequiptabAction extends BaseAction {

	private IEsbaccessequiptabService esbaccessequiptabService;
	private IEsbgardengatetabService esbgardengatetabService;
	private Esbscenicareatab provider;
	private Esbaccessequiptab esbaccessequiptab;

	private ISysparService sysparService;

	private PaginationSupport ps;
	private String result;
	private int pageSize = PaginationSupport.PAGESIZE;
	private int page;
	private String url;
	private Long id;

	public Esbscenicareatab getProvider() {
		return provider;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}

	public void setProvider(Esbscenicareatab provider) {
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = Long.valueOf(id);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public IEsbaccessequiptabService getEsbaccessequiptabService() {
		return esbaccessequiptabService;
	}

	public void setEsbaccessequiptabService(
			IEsbaccessequiptabService esbaccessequiptabService) {
		this.esbaccessequiptabService = esbaccessequiptabService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Esbaccessequiptab getEsbaccessequiptab() {
		return esbaccessequiptab;
	}

	public void setEsbaccessequiptab(Esbaccessequiptab esbaccessequiptab) {
		this.esbaccessequiptab = esbaccessequiptab;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IEsbgardengatetabService getEsbgardengatetabService() {
		return esbgardengatetabService;
	}

	public void setEsbgardengatetabService(
			IEsbgardengatetabService esbgardengatetabService) {
		this.esbgardengatetabService = esbgardengatetabService;
	}

	*//**
	 * ��ȡ����׼���豸�б�
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public String accessequipViewList() throws Exception {
		StringBuffer sb = new StringBuffer(getRequest().getContextPath());
		// ��ȡ��ǰ��¼�û�
		Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
				.getAttribute("employee");
		String scenics = "";
		if (employee.getCompanytype().equals("02")) {
			scenics = employee.getScenics();
			if (scenics == null || scenics.equals("")) {
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}

		// ��ȡ��ǰ��¼�û�������ķ�����
		List providers = this.esbaccessequiptabService.findListesbticket(scenics);

		if (page == 0) {
			page = 1;
		}
		// ���÷����������б�
		getRequest().setAttribute("prdlist", providers);

		// �ж��Ƿ�ѡ������̲�ѯ
		if (null != esbaccessequiptab) {

			if (null != esbaccessequiptab.getId().getIscenicid()
					&& !"".equals(esbaccessequiptab.getId().getIscenicid())) {
				sb.append("?esbaccessequiptab.id.iscenicid="
						+ esbaccessequiptab.getId().getIscenicid());
			}
			if (null != esbaccessequiptab.getId().getIgardengateid()
					&& !"".equals(esbaccessequiptab.getId().getIgardengateid())) {

				sb.append("&esbaccessequiptab.id.igardengateid="
						+ esbaccessequiptab.getId().getIgardengateid());
			}
		}

		url = sb.toString();

		this.ps = this.esbaccessequiptabService.getAccessequip(
				this.esbaccessequiptab, scenics, pageSize, page, url);

		return SUCCESS;
	}

	*//**
	 * ����ѡ��ķ����̻�ȡ���Ӧ԰��
	 * 
	 * @return
	 *//*
	public String getAllGardenGate() {
		try {
			result = this.esbaccessequiptabService.getGardenGateJSON(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:���ĳ�������̵��豸����
	 * 
	 * @auth:lijingrui
	 * @return return:String Date:Nov 9, 2011
	 *//*
	public String getAllEsbequiptype() {
		result = this.esbaccessequiptabService.getesbequiptypeJSON(id);
		return SUCCESS;
	}

	*//**
	 * ���׼���豸
	 * 
	 * @return
	 *//*
	public String addAccessequip() {
		try {
			strutsAction = ADD;

			// ��ȡ��ǰ��¼�û�
			Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
					.getAttribute("employee");
			String scenics = "";
			if (employee.getCompanytype().equals("02")) {
				scenics = employee.getScenics();
				if (scenics == null || scenics.equals("")) {
					this.addActionError(getText("esfemployeetab.scenics.required"));
					return INPUT;
				}
			}

			// ��ȡ��ǰ��¼�û�������ķ�����
			List providers = this.esbaccessequiptabService.findListesbticket(scenics);

			if(providers==null||providers.size()<1){
				this.addActionError("������Ϊ��,��������׼����");
				return INPUT;
			}
			
			if (provider == null || provider.getIscenicid() == null
					|| provider.getIscenicid().equals("")) {
				if (providers != null && providers.size() > 0) {
					provider = new Esbscenicareatab();
					provider = (Esbscenicareatab) providers.get(0);
				}
			}
			// ��ȡ԰��
			List gardengate = this.esbaccessequiptabService
					.getGardengateByPreId(provider.getIscenicid());
			// ��ȡ�豸����
			getRequest().setAttribute(
					"equlist",
					this.esbaccessequiptabService.getAllEsbequitype(provider
							.getIscenicid()));

			// ���÷����������б�
			getRequest().setAttribute("prdlist", providers);
			getRequest().setAttribute("garlist", gardengate);
			// ��о����
			getRequest().setAttribute("jslist", this.sysparService.retrieve("JSLX"));
			// ���Ʒ���
			getRequest().setAttribute("kzlist", this.sysparService.retrieve("KZFX"));
			// ����ģʽ
			getRequest().setAttribute("gzlist", this.sysparService.retrieve("GZMS"));
			// ���緽ʽ
			getRequest().setAttribute("wllist", this.sysparService.retrieve("WLFS"));

			if (esbaccessequiptab == null) {
				esbaccessequiptab = new Esbaccessequiptab();
			}

			this.esbaccessequiptab.setByisuse(new Long(1));
			this.esbaccessequiptab.setBycoretype("0001");
			this.esbaccessequiptab.setByctrldir("0001");
			this.esbaccessequiptab.setByworkmode("0001");
			this.esbaccessequiptab.setBynetworkmode("0002");
			this.esbaccessequiptab.setIsa(new Long(50));
			this.esbaccessequiptab.setIsb(new Long(50));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ��ת���޸�ҳ��
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public String enitAccessequip() throws Exception {
		strutsAction = EDIT;

		// ��ȡ��ǰ��¼�û�
		Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
				.getAttribute("employee");
		String scenics = "";
		if (employee.getCompanytype().equals("02")) {
			scenics = employee.getScenics();
			if (scenics == null || scenics.equals("")) {
				this.addActionError(getText("esfemployeetab.scenics.required"));
				return INPUT;
			}
		}

		// ��ȡ��ǰ��¼�û�������ķ�����
		List providers = this.esbaccessequiptabService.findListesbticket(scenics);

		if (provider == null || provider.getIscenicid() == null
				|| provider.getIscenicid().equals("")) {
			if (providers != null && providers.size() > 0) {
				provider = (Esbscenicareatab) providers.get(0);
			}
		}
		// ��ȡ԰��
		List gardengate = this.esbaccessequiptabService
				.getGardengateByPreId(esbaccessequiptab.getId().getIscenicid());
		// ��ȡ�豸����
		getRequest().setAttribute(
				"equlist",
				this.esbaccessequiptabService
						.getAllEsbequitype(esbaccessequiptab.getId().getIscenicid()));

		// ���÷����������б�
		getRequest().setAttribute("prdlist", providers);
		getRequest().setAttribute("garlist", gardengate);

		// ��о����
		getRequest().setAttribute("jslist", this.sysparService.retrieve("JSLX"));
		// ���Ʒ���
		getRequest().setAttribute("kzlist", this.sysparService.retrieve("KZFX"));
		// ����ģʽ
		getRequest().setAttribute("gzlist", this.sysparService.retrieve("GZMS"));
		// ���緽ʽ
		getRequest().setAttribute("wllist", this.sysparService.retrieve("WLFS"));

		if (null == esbaccessequiptab.getId().getIaccessequipid()
				|| "".equals(esbaccessequiptab.getId().getIaccessequipid())) {
			return "login";
		} else {
			this.esbaccessequiptab = (Esbaccessequiptab) this.genericService.get(
					Esbaccessequiptab.class, this.esbaccessequiptab.getId());
		}
		return SUCCESS;
	}

	*//**
	 * �������
	 * 
	 * @return
	 *//*
	public String saveAccessequip() {
		try {
			// ��ȡ��ǰ��¼�û�
			Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
					.getAttribute("employee");

			Syslog syslog = new Syslog();
			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());

			if (strutsAction == ADD) {
				// �ж���ͬһ�������£���Ʊ������׼���豸��IP�����ظ�
				if (esbaccessequiptabService.getGardsxipaddress(esbaccessequiptab
						.getId().getIscenicid(), esbaccessequiptab.getSzipaddress())) {
					this
							.addActionError(getText("��MAC��ַ�Ѿ�����Ʊ���ڻ���׼���豸������ˣ�����������!"));
					return INPUT;
				}
				boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szipaddress"}, new String[]{esbaccessequiptab.getSzipaddress()}, "Esbaccessequiptab");
				if(!isuse){
					this.addActionError(getText("��MAC��ַ�Ѿ�����Ʊ���ڻ���׼���豸������ˣ�����������!"));
					return INPUT;
				}
				// ���ñ��
				esbaccessequiptab.getId().setIaccessequipid(
						this.esbaccessequiptabService.getMaxId());

				// �־ò���
				this.esbaccessequiptabService.addAccessequip(esbaccessequiptab, syslog);

			} else if (strutsAction == EDIT) {
			
				if (null == esbaccessequiptab) {
					this.addActionError(getText("Action.loaderror"));
					return INPUT;
				}
				boolean isuse = this.esbaccessequiptabService.checkipAddress(esbaccessequiptab);
				if(!isuse){
					this.addActionError(getText("��MAC��ַ�Ѿ�����Ʊ���ڻ���׼���豸������ˣ�����������!"));
					return INPUT;
				}
				Esbaccessequiptab bs = this.esbaccessequiptabService.getviewquiptab(
						esbaccessequiptab.getId().getIaccessequipid(), esbaccessequiptab
								.getId().getIscenicid(), esbaccessequiptab.getId().getIgardengateid());
			
				if (!bs.getSzipaddress().equals(esbaccessequiptab.getSzipaddress())) {
					if (esbaccessequiptabService.getGardsxipaddress(esbaccessequiptab
							.getId().getIscenicid(), esbaccessequiptab.getSzipaddress())) {
						this
								.addActionError(getText("��MAC��ַ�Ѿ�����Ʊ���ڻ���׼���豸������ˣ�����������!"));
						return INPUT;
					}
				}
			
				// �־�
				this.esbaccessequiptabService.updateAccessequip(esbaccessequiptab,
						syslog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ��ת��ɾ��ҳ��
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public String preformDelAccessequip() throws Exception {
		strutsAction = DELETE;

		if (null == esbaccessequiptab.getId().getIaccessequipid()
				|| "".equals(esbaccessequiptab.getId().getIaccessequipid())) {
			return "login";
		} else {
			this.esbaccessequiptab = this.esbaccessequiptabService
					.getAccessequipById(this.esbaccessequiptab.getId());
		}
		return SUCCESS;
	}

	*//**
	 * �鿴׼���豸
	 * 
	 * @return
	 *//*
	public String viewAccessequip() {
		strutsAction = VIEW;

		try {
			if (null == esbaccessequiptab.getId().getIaccessequipid()
					|| "".equals(esbaccessequiptab.getId().getIaccessequipid())) {
				return "login";
			} else {
				this.esbaccessequiptab = this.esbaccessequiptabService
						.getAccessequipById(this.esbaccessequiptab.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	*//**
	 * ɾ��׼���豸
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public String delAccessequip() throws Exception {

		// ��ȡ��ǰ��¼�û�
		Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
				.getAttribute("employee");

		Syslog syslog = new Syslog();
		syslog.setIemployeeid(employee.getIemployeeid());
		syslog.setSzemployeename(employee.getSzemployeename());
		this.esbaccessequiptabService.delAccessequip(esbaccessequiptab, syslog);

		return SUCCESS;
	}

	*//**
	 * ��֤
	 *//*
	public void validateSaveAccessequip() {
		if (this.esbaccessequiptab == null) {
			this.addActionError(getText("esbaccessequiptab.requuired"));
		} else {

			if (null == esbaccessequiptab.getIequiptypeid()
					|| "".equals(esbaccessequiptab.getIequiptypeid())) {
				this.addActionError(getText("esbaccessequiptab.iequiptypeid.null"));
			} else if (!Tools.isnum(esbaccessequiptab.getIequiptypeid().toString())) {
				this.addActionError(getText("esbaccessequiptab.iequiptypeid.nonum"));
			}

			if (null == esbaccessequiptab.getSzaccessequipcode()
					|| "".equals(esbaccessequiptab.getSzaccessequipcode().trim())) {
				this
						.addActionError(getText("szaccessequipcode.szaccessequipcode.null"));
			} else if (esbaccessequiptab.getSzaccessequipcode().trim().length() * 2 > 30) {
				this
						.addActionError(getText("szaccessequipcode.szaccessequipcode.solong"));
			}

			if (null == esbaccessequiptab.getSzaccessequipname()
					|| "".equals(esbaccessequiptab.getSzaccessequipname().trim())) {
				this
						.addActionError(getText("esbaccessequiptab.szaccessequipname.null"));
			} else if (esbaccessequiptab.getSzaccessequipname().trim().length() * 2 > 50) {
				this
						.addActionError(getText("esbaccessequiptab.szaccessequipname.solong"));
			}

			if (null != esbaccessequiptab.getSzfactorycode()
					&& !"".equals(esbaccessequiptab.getSzfactorycode().trim())) {
				if (esbaccessequiptab.getSzfactorycode().trim().length() * 2 > 50) {
					this
							.addActionError(getText("esbaccessequiptab.szfactorycode.solong"));
				}
			}

			if (null == esbaccessequiptab.getSzipaddress()
					|| "".equals(esbaccessequiptab.getSzipaddress().trim())) {
				this.addActionError(getText("MAC��ַ����Ϊ��"));
			} else if (null != esbaccessequiptab.getSzipaddress()
					&& !"".equals(esbaccessequiptab.getSzipaddress().trim())) {

			}

			if (null != esbaccessequiptab.getSzserverip()
					&& !"".equals(esbaccessequiptab.getSzserverip().trim())) {
				String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
				Pattern p = Pattern.compile(ip);
				boolean b = p.matcher(esbaccessequiptab.getSzserverip()).matches();
				if (b == false) {
					this
							.addActionError(getText("errors.esbaccessequiptab.szserverip.required"));
				}

				if (esbaccessequiptab.getSzserverip().trim().length() > 24) {
					this.addActionError(getText("esbaccessequiptab.szserverip.solong"));
				}
			}

			if (null != esbaccessequiptab.getSznetmask()
					&& !"".equals(esbaccessequiptab.getSznetmask())) {
				String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
				Pattern p = Pattern.compile(ip);
				boolean b = p.matcher(esbaccessequiptab.getSznetmask()).matches();
				if (b == false) {
					this
							.addActionError(getText("errors.esbaccessequiptab.sznetmask.required"));
				}

				if (esbaccessequiptab.getSznetmask().trim().length() > 24) {
					this.addActionError(getText("esbaccessequiptab.sznemask.solong"));
				}
			}

			if (null != esbaccessequiptab.getSzgateway()
					&& !"".equals(esbaccessequiptab.getSzgateway())) {
				String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
				Pattern p = Pattern.compile(ip);
				boolean b = p.matcher(esbaccessequiptab.getSzgateway()).matches();
				if (b == false) {
					this
							.addActionError(getText("errors.esbaccessequiptab.szgateway.required"));
				}

				if (esbaccessequiptab.getSzgateway().trim().length() > 24) {
					this.addActionError(getText("esbaccessquiptab.szgateway.solong"));
				}
			}

			if (null != esbaccessequiptab.getSzdnsip()
					&& !"".equals(esbaccessequiptab.getSzdnsip())) {
				String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
				Pattern p = Pattern.compile(ip);
				boolean b = p.matcher(esbaccessequiptab.getSzdnsip()).matches();
				if (b == false) {
					this
							.addActionError(getText("errors.esbaccessequiptab.szdnsip.required"));
				}

				if (esbaccessequiptab.getSzdnsip().trim().length() > 24) {
					this.addActionError(getText("esbaccessquiptab.szdnsip.solong"));
				}
			}

			if (null == esbaccessequiptab.getIsa()
					|| "".equals(esbaccessequiptab.getIsa())) {
				this.addActionError(getText("esbaccessequiptab.isa.required"));
			} else {
				if (esbaccessequiptab.getIsa() > 100 || esbaccessequiptab.getIsa() < 0) {
					this.addActionError(getText("esbaccessequiptab.isa.error"));

				}
			}
			
			 * if(esbaccessequiptab.getIsb()>100||esbaccessequiptab.getIsb()<0){
			 * this.addActionError(getText("esbaccessequiptab.isb.error"));
			 *  }
			 
			// ���ش��벻Ϊ�յ������

		}
	}
	
	*//**
	 * 
	 * Describe:�豸״̬��Ϣ��ʾ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2013-2-21
	 *//*
	public String showListstatusaccess(){

		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (page == 0) {
			page = 1;
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
		
		// ��ȡ��ǰ��¼�û�������ķ�����
		List providers = this.esbaccessequiptabService.findListesbticket(scenics);
		getRequest().setAttribute("prdlist", providers);
		
		getRequest().setAttribute("lxlist", this.sysparService.retrieve("SBLX"));
		
		if(result ==null || result.equals("")){
			result="01";
		}
		
		if(null != esbaccessequiptab && null != esbaccessequiptab.getId()&&null != esbaccessequiptab.getId().getIscenicid()
				&& !"".equals(esbaccessequiptab.getId().getIscenicid())){
			scenics=esbaccessequiptab.getId().getIscenicid().toString();
		}else{
			if(providers!=null&&providers.size()>0){
				scenics = ((Esbscenicareatab)providers.get(0)).getIscenicid().toString();
			}
		}
		url+="?result="+result+"&esbaccessequiptab.id.iscenicid="+scenics;
		ps=this.esbaccessequiptabService.searchListAccessstatus(scenics, result, pageSize, page, url);
		
		return SUCCESS;
	}
	
	
}
*/