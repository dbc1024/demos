/*package com.ectrip.system.salesmanager.action;

import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.order.Ordercs;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.provider.service.iservice.IBusinessService;
import com.ectrip.system.salesmanager.service.iservice.IOrdercsService;
import com.ectrip.system.syspar.service.iservice.ISysparService;
import com.opensymphony.xwork2.ActionContext;

public class OrdercsAction extends BaseAction {
	private IOrdercsService ordercsService;
	private ISysparService sysparService;
	private IBusinessService businessService;

	private String page;
	private PaginationSupport ps;
	private int pageSize = PaginationSupport.PAGESIZE;
	private String url;

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

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}

	private Ordercs ordercs;

	public Ordercs getOrdercs() {
		return ordercs;
	}

	public void setOrdercs(Ordercs ordercs) {
		this.ordercs = ordercs;
	}

	public IOrdercsService getOrdercsService() {
		return ordercsService;
	}

	public void setOrdercsService(IOrdercsService ordercsService) {
		this.ordercsService = ordercsService;
	}

	public String ordercsquery() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		List businessList = businessService.businessList();
		getRequest().getSession().setAttribute("businesslist", businessList);
		url = getRequest().getContextPath() + getRequest().getServletPath();
		// ҳ��
		if (getPage() == null || "".equals(getPage())) {
			setPage("1");
		}

		ps = ordercsService.getordercsviewlist(Integer.parseInt(page),
				pageSize, url);
		return SUCCESS;
	}

	public String ordercslist() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		List businessList = businessService.businessList();
		getRequest().getSession().setAttribute("businesslist", businessList);

		url = getRequest().getContextPath() + getRequest().getServletPath();
		// ҳ��
		if (getPage() == null || "".equals(getPage())) {
			setPage("1");
		}
		if(ordercs==null){
			ordercs=new Ordercs();
			ordercs.setIbusinessid(new Long(1));
		}
		url += "?ordercs.ibusinessid=" + ordercs.getIbusinessid();
		ps = ordercsService.getordercsList(ordercs.getIbusinessid(),
				Integer.parseInt(page), pageSize, url);
		return SUCCESS;
	}

	public String ordercsadd() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		this.strutsAction = ADD;
		List esclist = sysparService.retrieve("ORCS");
		getRequest().setAttribute("esclist", esclist);
		List businessList = businessService.businessList();
		getRequest().setAttribute("businesslist", businessList);
		if(ordercs==null){
			ordercs=new Ordercs();
		}
		ordercs.setByisuse(new Long(1));
		return SUCCESS;
	}

	public String ordercsedit() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		this.strutsAction = EDIT;
		List esclist = sysparService.retrieve("ORCS");
		getRequest().setAttribute("esclist", esclist);
		List businessList = businessService.businessList();
		getRequest().setAttribute("businesslist", businessList);
		ordercs = ordercsService.queryone(ordercs.getSeq());
		return SUCCESS;
	}

	public String ordercsview() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		this.strutsAction = VIEW;

		ordercs = ordercsService.queryone(ordercs.getSeq());
		return SUCCESS;
	}

	public String ordercsdelete() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		// ���ҵ������
		this.strutsAction = DELETE;

		ordercs = ordercsService.queryone(ordercs.getSeq());
		return SUCCESS;
	}

	public String ordercssave() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession()
						.get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());// ��̨������
		if (strutsAction == ADD) {
			if(ordercs.getZcs().trim()==null||ordercs.getZcs().trim().equals("")){
				this.addActionError("����д���Ĳ���");
				return INPUT;
			}
			if(ordercs.getIsequence()==null||ordercs.getIsequence().equals("")){
				this.addActionError("����д����˳��");
				return INPUT;
			}else{
				if(ordercs.getIsequence()<0){
					this.addActionError("����˳��Ӧ����0");
					return INPUT;
				}
			}
			List list = ordercsService.queryordercs(ordercs.getIbusinessid(),
					ordercs.getEcs());
			if (list != null && list.size() > 0) {
				this.addActionError("�ö�Ӧ�ֶ�"+ordercs.getEcs()+"�ڸ�ҵ������ʹ�ã��������");
				return INPUT;
			}
			Long maxseq = this.genericService.getMaxPk("seq", "Ordercs");
			ordercs.setSeq(maxseq + 1);

			ordercsService.insertordercs(ordercs, syslog);
		}
		if (strutsAction == EDIT) {
			if(ordercs.getZcs().trim()==null||ordercs.getZcs().trim().equals("")){
				this.addActionError("����д���Ĳ���");
				return INPUT;
			}
			if(ordercs.getIsequence()==null||ordercs.getIsequence().equals("")){
				this.addActionError("����д����˳��");
				return INPUT;
			}else{
				if(ordercs.getIsequence()<0){
					this.addActionError("����˳��Ӧ����0");
					return INPUT;
				}
			}
			List list = ordercsService.queryeditordercs(
					ordercs.getIbusinessid(), ordercs.getEcs(),
					ordercs.getSeq());
			if (list != null && list.size() > 0) {
				this.addActionError("�ö�Ӧ�ֶ�"+ordercs.getEcs()+"�ڸ�ҵ������ʹ�ã��������");
				return INPUT;
			}

			ordercsService.updateordercs(ordercs, syslog);
		}
		if (strutsAction == DELETE) {
			ordercs =ordercsService.queryone(ordercs.getSeq());
			ordercsService.deleteordercs(ordercs, syslog);
		}
		return SUCCESS;
	}
}
*/