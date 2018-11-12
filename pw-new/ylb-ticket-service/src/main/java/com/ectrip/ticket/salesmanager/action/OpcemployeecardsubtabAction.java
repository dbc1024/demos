/*package com.ectrip.system.salesmanager.action;

import java.util.regex.Pattern;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.model.salesmanager.Opcemployeecardtab;
import com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardsubtabService;
import com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService;

*//**
 * @author Jzhenhua,Created Time 2011-10-07
 *//*
public class OpcemployeecardsubtabAction extends BaseAction {

	private IOpcemployeecardsubtabService opcemployeecardsubtabService;
	private IOpcemployeecardtabService opcemployeecardtabService;
	private Opcemployeecardtab opcemployeecardtab;
	private Opcemployeecardsubtab opcemployeecardsubtab;
	private PaginationSupport ps;
	private int pageSize = 10;
	private int startIndex = 1;
	private String url;

	public IOpcemployeecardsubtabService getOpcemployeecardsubtabService() {
		return opcemployeecardsubtabService;
	}

	public void setOpcemployeecardsubtabService(
			IOpcemployeecardsubtabService opcemployeecardsubtabService) {
		this.opcemployeecardsubtabService = opcemployeecardsubtabService;
	}

	public IOpcemployeecardtabService getOpcemployeecardtabService() {
		return opcemployeecardtabService;
	}

	public void setOpcemployeecardtabService(
			IOpcemployeecardtabService opcemployeecardtabService) {
		this.opcemployeecardtabService = opcemployeecardtabService;
	}

	public Opcemployeecardtab getOpcemployeecardtab() {
		return opcemployeecardtab;
	}

	public void setOpcemployeecardtab(Opcemployeecardtab opcemployeecardtab) {
		this.opcemployeecardtab = opcemployeecardtab;
	}

	public Opcemployeecardsubtab getOpcemployeecardsubtab() {
		return opcemployeecardsubtab;
	}

	public void setOpcemployeecardsubtab(
			Opcemployeecardsubtab opcemployeecardsubtab) {
		this.opcemployeecardsubtab = opcemployeecardsubtab;
	}

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	*//**
	 * ��ת������Ϣ�б�
	 * 
	 * @return
	 * @throws Exception 
	 *//*
	public String ployeeCardSubListView() throws Exception {
		url = getRequest().getContextPath() + getRequest().getServletPath();
		this.ps = this.opcemployeecardsubtabService.getPloyeecardSubByCardId(this.opcemployeecardtab.getIemployeecardid(), pageSize, startIndex, url);
		
		this.opcemployeecardtab = this.opcemployeecardtabService.getPloyeeCardById(this.opcemployeecardtab.getIemployeecardid());
		
		return SUCCESS;
	}

	*//**
	 * �����Ϣ
	 * 
	 * @return
	 *//*
	public String addPloyeeCardSub() {
		strutsAction = ADD;
		
		return SUCCESS;
	}

	*//**
	 * �޸�
	 * 
	 * @return
	 * @throws Exception 
	 *//*
	public String editPloyeeCardSub() {
		strutsAction = EDIT;
		this.opcemployeecardsubtab =(Opcemployeecardsubtab) this.genericService.get(Opcemployeecardsubtab.class, opcemployeecardsubtab.getIemployeecardsubid());
		return SUCCESS;
	}

	*//**
	 * ����
	 * 
	 * @return
	 *//*
	public String savePloyeeCardSub() {
		if (strutsAction == ADD) {
			//��ĳһԱ����֤�£��ӱ���԰�Ų����ظ����
			if(this.opcemployeecardsubtabService.getPloyeecardSubGard(opcemployeecardsubtab.getIgardengateid(), opcemployeecardsubtab.getIemployeecardid())){
				this.addActionError(getText("opcemployeecardsubtab.igardengateid.iemployeecardid.required"));
			}
			
			this.opcemployeecardsubtab.setIemployeecardsubid(this.opcemployeecardsubtabService
							.getMaxId());
			opcemployeecardsubtab.setDtmakedate(Tools.getDayTimes());
			this.opcemployeecardsubtabService
					.addPloyeecardSub(this.opcemployeecardsubtab);
		} else if (strutsAction == EDIT) {
			this.opcemployeecardsubtabService
					.updatePloyeecardSub(this.opcemployeecardsubtab);
		}
		return SUCCESS;
	}

	*//**
	 * ��ת��ɾ��ҳ��
	 * 
	 * @return
	 * @throws Exception 
	 *//*
	public String preFromDelPloyeeCardSub() throws Exception {
		strutsAction = DELETE;
		this.opcemployeecardsubtab = this.opcemployeecardsubtabService
		.getPloyeecardSubById(this.opcemployeecardsubtab
				.getIemployeecardsubid());
		return SUCCESS;
	}

	*//**
	 * ��ת���鿴ҳ��
	 * 
	 * @return
	 * @throws Exception 
	 *//*
	public String viewPloyeeCardSub() throws Exception {
		strutsAction = VIEW;

		this.opcemployeecardsubtab = this.opcemployeecardsubtabService
		.getPloyeecardSubById(this.opcemployeecardsubtab
				.getIemployeecardsubid());
		return SUCCESS;
	}

	*//**
	 * ɾ��
	 * 
	 * @return
	 *//*
	public String delPloyeeCardSub() {
		this.opcemployeecardsubtabService
		.delPloyeecardSub(opcemployeecardsubtab);
		return SUCCESS;
	}

	*//**
	 * ��֤
	 *//*
	public void validateSavePloyeeCardSub() {
		if (this.opcemployeecardsubtab == null) {
			this.addActionError(getText("opcemployeecardsubtab.required"));
		} else {
			if (null == opcemployeecardsubtab.getDtlastcheckdate()
					|| "".equals(opcemployeecardsubtab.getDtlastcheckdate())) {
				this.addActionError(getText("�����Ʊ���ڲ���Ϊ��"));
			}

			if (null != opcemployeecardsubtab.getDtlastcheckdate()
					&&!"".equals(opcemployeecardsubtab.getDtlastcheckdate())) {
				Pattern p = Pattern.compile("^(( \\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
				boolean b = p.matcher(opcemployeecardsubtab.getDtlastcheckdate()).matches();
				if (b == false) {
					this.addActionError(getText("ʱ���ʽ����ȷ,������ѡ��"));//�ж�ʱ��ĸ�ʽ
				}
			}
			
			if (null == opcemployeecardsubtab.getBylastcheckdir()
					|| "".equals(opcemployeecardsubtab.getBylastcheckdir())) {
				this.addActionError(getText("��ѡ�������Ʊ����"));
			}

			if (null == opcemployeecardsubtab.getIpasstimes()
					|| "".equals(opcemployeecardsubtab.getIpasstimes())) {
				this.addActionError(getText("�������ͨ������"));
			}
			if (null != opcemployeecardsubtab.getIpasstimes()
					&&!"".equals(opcemployeecardsubtab.getIpasstimes())) {
				Pattern p = Pattern.compile("^[0-9]*$");
				boolean b = p.matcher(opcemployeecardsubtab.getIpasstimes().toString()).matches();
				if (b == false) {
					this.addActionError(getText("��ͨ������ΪΪ�Ǹ���,����������!"));   //��ͨ������Ϊ�Ǹ���
				}
				
			}

			if (null == opcemployeecardsubtab.getMsingletimes()
					|| "".equals(opcemployeecardsubtab.getMsingletimes())) {
				this.addActionError(getText("�����뵥�����Ѵ���"));
			}
			
			if (null != opcemployeecardsubtab.getMsingletimes()
					&&!"".equals(opcemployeecardsubtab.getMsingletimes())) {
				Pattern p = Pattern.compile("^[0-9]*$");
				boolean b = p.matcher(opcemployeecardsubtab.getMsingletimes().toString()).matches();
				if (b == false) {
					this.addActionError(getText("�������Ѵ���ΪΪ�Ǹ���,����������!"));   //�������Ѵ���Ϊ�Ǹ���
				}
			}
			
			if (null != opcemployeecardsubtab.getIpassedtimes()
					&&!"".equals(opcemployeecardsubtab.getIpassedtimes())) {
				Pattern p = Pattern.compile("^[0-9]*$");
				boolean b = p.matcher(opcemployeecardsubtab.getIpassedtimes().toString()).matches();
				if (b == false) {
					this.addActionError(getText("��ͨ������ΪΪ�Ǹ���,����������!"));   //��ͨ������Ϊ�Ǹ���
				}
			}

			if (null == opcemployeecardsubtab.getMlimitconsume()
					|| "".equals(opcemployeecardsubtab.getMlimitconsume())) {
				this.addActionError(getText("����������ѽ��"));
			}

			if (null != opcemployeecardsubtab.getMlimitconsume()
					&&! "".equals(opcemployeecardsubtab.getMlimitconsume())) {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(opcemployeecardsubtab.getMlimitconsume().toString()).matches();
				if (b == false) {
					this.addActionError(getText("�����ѽ���ʽ����ȷ�����ɾ�ȷ��С�������λ"));///�����ѽ�� ��ʽ����ȷ�����ɾ�ȷ��С�������λ
				}
			}
			
			if (null == opcemployeecardsubtab.getMsingleconsume()
					|| "".equals(opcemployeecardsubtab.getMsingleconsume())) {
				this.addActionError(getText("�����뵥�����ѽ��"));
			}
			
			if (null != opcemployeecardsubtab.getMsingleconsume()
					&&! "".equals(opcemployeecardsubtab.getMsingleconsume())) {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(opcemployeecardsubtab.getMsingleconsume().toString()).matches();
				if (b == false) {
					this.addActionError(getText("�������ѽ���ʽ����ȷ�����ɾ�ȷ��С�������λ"));///�������ѽ�� ��ʽ����ȷ�����ɾ�ȷ��С�������λ
				}
			}

			if (null != opcemployeecardsubtab.getMconsumed()
					&&!"".equals(opcemployeecardsubtab.getMconsumed())) {
				Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
				boolean b = p.matcher(opcemployeecardsubtab.getMconsumed().toString()).matches();
				if (b == false) {
					this.addActionError(getText("�����ѽ���ʽ����ȷ�����ɾ�ȷ��С�������λ"));///�����ѽ�� ��ʽ����ȷ�����ɾ�ȷ��С�������λ
				}
			}
			
		}
	}
}
*/