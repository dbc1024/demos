/*package com.ectrip.system.afcset.action;

import java.util.List;
import java.util.Map;

import com.ectrip.base.action.BackBaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.afcset.Esbcomputerserverstab;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.afcset.service.iservice.IEsbcomputerserverstabService;
import com.opensymphony.xwork2.ActionContext;

public class EsbcomputerserverstabAction extends BackBaseAction {
	IEsbcomputerserverstabService esbcomputerserverstabService;
	Esbcomputerserverstab esbcomputerserverstab;
	private PaginationSupport ps;
	private String page;
	// ��������
	private String hsql = "";
	private String url = "";
	private int pageSize = PaginationSupport.PAGESIZE;

	

	public IEsbcomputerserverstabService getEsbcomputerserverstabService() {
		return esbcomputerserverstabService;
	}

	public void setEsbcomputerserverstabService(
			IEsbcomputerserverstabService esbcomputerserverstabService) {
		this.esbcomputerserverstabService = esbcomputerserverstabService;
	}

	public Esbcomputerserverstab getEsbcomputerserverstab() {
		return esbcomputerserverstab;
	}

	public void setEsbcomputerserverstab(
			Esbcomputerserverstab esbcomputerserverstab) {
		this.esbcomputerserverstab = esbcomputerserverstab;
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

	public String serverviewlist() {

		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (getPage() == null || getPage().equals("")) {
			setPage("1");
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");

		Map<String, Object> session = ActionContext.getContext().getSession();
		hsql = "select new map(w.iserverid as iserverid,w.iscenicid as iscenicid,p.szscenicname as szscenicname,w.szservercode as szservercode ,w.szservername as szservername,w.byisuse as byisuse,w.szipaddress as szipaddress) from Esbcomputerserverstab w,Esbscenicareatab p where p.iscenicid = w.iscenicid ";
		String hsql2 = "select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.scenictype in ('01','03') and es.isjd=0 ";
		// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
		if (esfemployeetab.getCompanytype().equals("02")) {
			String scenics = esfemployeetab.getScenics();
			if (scenics == null || scenics.equals("")) {
				this.addActionError(getText("company.scenic.Relationship"));
				return INPUT;
			}
			hsql2 = hsql2 + " and es.iscenicid in (" + scenics + ")";
			hsql = hsql + " and w.iscenicid in (" + scenics + ")";
		}
		session.put("scenictList", this.genericService.find(hsql2));

		ps = this.genericService.findPage(hsql, pageSize, Integer.parseInt(page),
				url);

		return SUCCESS;

	}

	public String searchserver() {

		url = getRequest().getContextPath() + getRequest().getServletPath();
		if (getPage() == null || getPage().equals("")) {
			setPage("1");
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		
		if(esbcomputerserverstab==null||esbcomputerserverstab.getIscenicid()==null||esbcomputerserverstab.getIscenicid().toString().equals("")){
			this.addActionError("δ��ȡ����������Ϣ!");
			return INPUT;
		}

		Map<String, Object> session = ActionContext.getContext().getSession();
		hsql = "select new map(w.iserverid as iserverid,w.iscenicid as iscenicid,p.szscenicname as szscenicname,w.szservercode as szservercode ,w.szservername as szservername,w.byisuse as byisuse,w.szipaddress as szipaddress) from Esbcomputerserverstab w, Esbscenicareatab p where p.iscenicid = w.iscenicid and w.iscenicid="
				+ esbcomputerserverstab.getIscenicid();
		String hsql2 = "select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.scenictype in ('01','03') and es.isjd=0 ";
		// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
		if (esfemployeetab.getCompanytype().equals("02")) {
			String scenics = esfemployeetab.getScenics();
			if (scenics == null || scenics.equals("")) {
				this.addActionError(getText("company.scenic.Relationship"));
				return INPUT;
			}
			hsql2 = hsql2 + " and es.iscenicid in (" + scenics + ")";
			hsql = hsql + " and w.iscenicid in (" + scenics + ")";
		}
		session.put("scenictList", this.genericService.find(hsql2));

		ps = this.genericService.findPage(hsql, pageSize, Integer.parseInt(page),
				url);

		return SUCCESS;

	}

	public String addServer() {
		this.strutsAction = ADD;
		Map<String, Object> session = ActionContext.getContext().getSession();
		String hsql2 = "select new map(es.iscenicid as iscenicid,p.szscenicname as szscenicname) from Esbscenicareatab es , Esbscenicareatab p where p.iscenicid = es.iscenicid and es.scenictype in ('01','03') and es.isjd=0 ";
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		if (esfemployeetab.getCompanytype().equals("02")) {
			String scenics = esfemployeetab.getScenics();
			if (scenics == null || scenics.equals("")) {
				this.addActionError(getText("company.scenic.Relationship"));
				return INPUT;
			}
			hsql2 = hsql2 + " and es.iscenicid in (" + scenics + ")";
		}

		session.put("scenictList", this.genericService.find(hsql2));
		esbcomputerserverstab = new Esbcomputerserverstab();
		esbcomputerserverstab.setByisuse(new Long(1));
		return SUCCESS;
	}

	public String saveServer() {
		// ���ݶ�ȡ�ľ���ID,�жϸ��û��Ƿ��й������ڷ����̵���Ϣ
		boolean judge = Judgescenic(esbcomputerserverstab.getIscenicid());
		if (!judge) {
			this.addActionError(getText("company.scenic.Crossing"));
			return INPUT;
		}
		Syslog syslog = new Syslog();

		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());

		if (strutsAction == ADD) {
			String hsql2 = "from Esbcomputerserverstab  where szservercode='"
					+ esbcomputerserverstab.getSzservercode() + "'";
			List list = this.genericService.find(hsql2);
			if (list.size() > 0) {
				this.addActionError(getText("esbcomputerserverstab.szservercode.add"));
				return INPUT;
			}
			String hsql3 = "from Esbcomputerserverstab  where szipaddress='"
					+ esbcomputerserverstab.getSzipaddress()
					+ "' and iscenicid="
					+ esbcomputerserverstab.getIscenicid();
			List list2 = this.genericService.find(hsql3);
			if (list2.size() > 0) {
				this.addActionError(getText("esbcomputerserverstab.szipaddress.add"));
				return INPUT;
			}
			esbcomputerserverstabService.insert(esbcomputerserverstab, syslog);
		}
		if (strutsAction == EDIT) {
			this.esbcomputerserverstabService.update(esbcomputerserverstab, syslog);
		}
		if (strutsAction == DELETE) {
			this.esbcomputerserverstabService.delete(esbcomputerserverstab, syslog);
		}
		return SUCCESS;
	}

	public String editServer() {
		this.strutsAction = EDIT;

		esbcomputerserverstab = (Esbcomputerserverstab) this.esbcomputerserverstabService.findoneserver(esbcomputerserverstab.getIserverid());
		return SUCCESS;
	
	}

	public String delServer() {
		this.strutsAction = DELETE;

		esbcomputerserverstab = (Esbcomputerserverstab) this.esbcomputerserverstabService.findoneserver(esbcomputerserverstab.getIserverid());
		return SUCCESS;
	}

	public String viewServer() {
		this.strutsAction = VIEW;

		esbcomputerserverstab = (Esbcomputerserverstab) this.esbcomputerserverstabService.findoneserver(esbcomputerserverstab.getIserverid());
		return SUCCESS;
	
	}
}
*/