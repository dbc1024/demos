/*package com.ectrip.system.salesmanager.action;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.salesmanager.Ospforcedrefundsettab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.salesmanager.service.iservice.IOspForcedRefundSetTabService;

*/ /**
 * ǿ����Ʊ Created By Jzhenhua,Time 2011-10-05 10:41
 *//*
public class OspforcedrefundsettabAction extends BaseAction {

	private IOspForcedRefundSetTabService ospforcedrefundsettabService;
	private PaginationSupport ps;
	private Ospforcedrefundsettab ospforcedrefundsettab;
	private int page;
	private int pageSize = PaginationSupport.PAGESIZE;
	private String url;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public IOspForcedRefundSetTabService getOspforcedrefundsettabService() {
		return ospforcedrefundsettabService;
	}

	public void setOspforcedrefundsettabService(
			IOspForcedRefundSetTabService ospforcedrefundsettabService) {
		this.ospforcedrefundsettabService = ospforcedrefundsettabService;
	}

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public Ospforcedrefundsettab getOspforcedrefundsettab() {
		return ospforcedrefundsettab;
	}

	public void setOspforcedrefundsettab(
			Ospforcedrefundsettab ospforcedrefundsettab) {
		this.ospforcedrefundsettab = ospforcedrefundsettab;
	}

	*//**
	 * �������ǿ����Ʊ��Ϣ
	 * 
	 * @return
	 *//*
	public String ospforcedredfundViewList() {

		url = getRequest().getContextPath();
		try {

			if (page == 0) {
				page = 1;
			}

			this.ps = this.ospforcedrefundsettabService
					.getAllOspforcedrefundset(pageSize, page, url);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ��ת�����ҳ��
	 * 
	 * @return
	 *//*
	public String addOspforcedrefund() {
		strutsAction = ADD;

		if (this.ospforcedrefundsettab == null) {
			this.ospforcedrefundsettab = new Ospforcedrefundsettab();
		}

		this.ospforcedrefundsettab.setIversion(new Long(57));
		this.ospforcedrefundsettab.setBishistory(new Long(1));
		this.ospforcedrefundsettab.setByisuse(new Long(1));

		return SUCCESS;
	}

	*//**
	 * ��ת���༭ҳ��
	 * 
	 * @return
	 *//*
	public String editOspforcedrefund() {
		strutsAction = EDIT;
		try {

			if (null == ospforcedrefundsettab.getIforcedrefundid()
					|| "".equals(ospforcedrefundsettab.getIforcedrefundid())) {
				this.addActionError(getText("action.loaderror"));
			}

			if (hasActionErrors()) {
				return INPUT;
			}

			this.ospforcedrefundsettab = this.ospforcedrefundsettabService
					.getOspforcedrefundsetById(ospforcedrefundsettab
							.getIforcedrefundid());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ����
	 * 
	 * @return
	 *//*
	public String saveOspforcedrefund() {
		try {
			// ��ȡ��ǰ��¼�û�
			Esfemployeetab employee = (Esfemployeetab) getRequest()
					.getSession().getAttribute("employee");
			Syslog syslog = new Syslog();

			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());

			if (strutsAction == ADD) {
				// ��ʼ����Ϊδɾ��
				this.ospforcedrefundsettab.setBisdelete(new Long(1));
				this.ospforcedrefundsettab
						.setIforcedrefundid(this.ospforcedrefundsettabService
								.getMaxId());
				this.ospforcedrefundsettabService.addOspForcedRefundSet(
						ospforcedrefundsettab, syslog);
			} else if (strutsAction == EDIT) {
				this.ospforcedrefundsettabService.editOspForcedRefundSet(
						ospforcedrefundsettab, syslog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ��ת��ɾ��
	 * 
	 * @return
	 *//*
	public String preformDelOspforcedrefund() {
		strutsAction = DELETE;

		try {

			// �ж��Ƿ���յ�����
			if (null == ospforcedrefundsettab.getIforcedrefundid()
					|| "".equals(ospforcedrefundsettab.getIforcedrefundid())) {
				this.addActionError(getText("action.loaderror"));
			}

			if (hasActionErrors()) {
				return INPUT;
			}

			// ��ȡҪɾ���Ķ�������
			this.ospforcedrefundsettab = this.ospforcedrefundsettabService
					.getOspforcedrefundsetById(ospforcedrefundsettab
							.getIforcedrefundid());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * Description:��ת���鿴
	 * 
	 * @author Jzhenhua Date 2011-10-14
	 *//*
	public String viewOspforcedrefund() {
		strutsAction = VIEW;

		try {

			// �ж��Ƿ���յ�����
			if (null == ospforcedrefundsettab.getIforcedrefundid()
					|| "".equals(ospforcedrefundsettab.getIforcedrefundid())) {
				this.addActionError(getText("action.loaderror"));
			}

			if (hasActionErrors()) {
				return INPUT;
			}

			this.ospforcedrefundsettab = this.ospforcedrefundsettabService
					.getOspforcedrefundsetById(ospforcedrefundsettab
							.getIforcedrefundid());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * Description��ɾ������
	 * 
	 * @author Jzhenhua Date 2011-10-14
	 *//*
	public String delOspforcedrefund() {
		try {
			// ��ȡ��ǰ��¼�û�
			Esfemployeetab employee = (Esfemployeetab) getRequest()
					.getSession().getAttribute("employee");
			Syslog syslog = new Syslog();

			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());

			if (null == ospforcedrefundsettab) {
				this.addActionError("action.deleteerror");
			}

			if (hasActionErrors()) {
				return INPUT;
			}

			this.ospforcedrefundsettabService.delOspForcedRefundSet(
					ospforcedrefundsettab, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	*//**
	 * ��֤
	 *//*
	public void validateSaveOspforcedrefund() {
		if (this.ospforcedrefundsettab == null) {
			this.addActionError(getText("ospforcedrefundsettab.requuired"));
		} else {
			if (null == ospforcedrefundsettab.getSzforcedrefundcode()
					|| "".equals(ospforcedrefundsettab.getSzforcedrefundcode()
							.trim())) {
				this
						.addActionError(getText("ospforcedrefundsettab.szforcedrefundcode.null"));
			} else if (ospforcedrefundsettab.getSzforcedrefundcode().trim()
					.length() * 2 > 24) {
				this
						.addActionError(getText("ospforcedrefundsettab.szforcedrefundcode.solong"));
			}

			if (null == ospforcedrefundsettab.getSzforcedrefundname()
					|| "".equals(ospforcedrefundsettab.getSzforcedrefundname()
							.trim())) {
				this
						.addActionError(getText("ospforcedrefundsettab.szforcedrefundname.null"));
			} else if (ospforcedrefundsettab.getSzforcedrefundname().trim()
					.length() * 2 > 24) {
				this
						.addActionError(getText("ospforcedrefundsettab.szforcedrefundname.solong"));
			}

			if (null == ospforcedrefundsettab.getSzmemo()
					|| "".equals(ospforcedrefundsettab.getSzmemo().trim())) {
				this
						.addActionError(getText("ospforcedrefundsettab.szmemo.null"));
			} else if (ospforcedrefundsettab.getSzmemo().trim().length() * 2 > 520) {
				this
						.addActionError(getText("ospforcedrefundsettab.szmemo.solong"));
			}

			if (null == ospforcedrefundsettab.getIversion()
					|| "".equals(ospforcedrefundsettab.getIversion())) {
				this
						.addActionError(getText("ospforcedrefundsettab.iversion.null"));
			}

			if (null == ospforcedrefundsettab.getByisuse()
					|| "".equals(ospforcedrefundsettab.getByisuse())) {
				this
						.addActionError(getText("ospforcedrefundsettab.byisuse.null"));
			}

			if (null == ospforcedrefundsettab.getBishistory()
					|| "".equals(ospforcedrefundsettab.getBishistory())) {
				this
						.addActionError(getText("ospforcedrefundsettabbishitory.null"));
			}

			// ���ش��벻Ϊ�յ������
			if (ospforcedrefundsettab.getSzforcedrefundcode() != null
					&& !""
							.equals(ospforcedrefundsettab
									.getSzforcedrefundcode())) {
				// ���ֱ����֤���ݿ��Ƿ���ڸô���
				if (strutsAction == ADD) {
					if (!this.genericService.volidateSole(new String[] {},
							new Long[] {},
							new String[] { "szforcedrefundcode" },
							new String[] { ospforcedrefundsettab
									.getSzforcedrefundcode() },
							"Ospforcedrefundsettab")) {
						this
								.addActionError(getText("Ospforcedrefundsettab.szforcedrefundcode.exists"));
					}
				} else if (strutsAction == EDIT) {
					// �޸����ж��Ƿ�Ķ�������
					Ospforcedrefundsettab myvenue = this.ospforcedrefundsettabService
							.getOspforcedrefundsetById(ospforcedrefundsettab
									.getIforcedrefundid());
					if (!myvenue.getSzforcedrefundcode().equals(
							this.ospforcedrefundsettab.getSzforcedrefundcode())) {
						if (!this.genericService.volidateSole(new String[] {},
								new Long[] {},
								new String[] { "szforcedrefundcode" },
								new String[] { ospforcedrefundsettab
										.getSzforcedrefundcode() },
								"Ospforcedrefundsettab")) {
							this
									.addActionError(getText("Ospforcedrefundsettab.szforcedrefundcode.exists"));
						}
					}
				}
			}
		}
	}
}
*/