/*package com.ectrip.system.afcset.action;

import com.ectrip.base.action.BaseAction;
import com.ectrip.model.syspar.Sysparv5;
import com.ectrip.model.syspar.Sysparv5Id;

public class ZhiwenVerificSetAction extends BaseAction {
	private Long qjcs;// ȫ�ֲ���
	private Long skcs;// ɢ�Ͳ���
	private Long dtcs;// �ŶӲ���
	private Long dtPercentage;// �ŶӰٷֱ�
	private Long jgtime;// �ŶӼ��ʱ��

	public Long getQjcs() {
		return qjcs;
	}

	public void setQjcs(Long qjcs) {
		this.qjcs = qjcs;
	}

	public Long getSkcs() {
		return skcs;
	}

	public void setSkcs(Long skcs) {
		this.skcs = skcs;
	}

	public Long getDtcs() {
		return dtcs;
	}

	public void setDtcs(Long dtcs) {
		this.dtcs = dtcs;
	}

	public Long getDtPercentage() {
		return dtPercentage;
	}

	public void setDtPercentage(Long dtPercentage) {
		this.dtPercentage = dtPercentage;
	}

	public Long getJgtime() {
		return jgtime;
	}

	public void setJgtime(Long jgtime) {
		this.jgtime = jgtime;
	}

	public String zwsetedit() {
		this.strutsAction=EDIT;
		Sysparv5 v5 = (Sysparv5) this.genericService.get(Sysparv5.class,
				new Sysparv5Id("YZCS", "00"));
		qjcs = new Long(v5.getPmvb());
		v5 = (Sysparv5) this.genericService.get(Sysparv5.class, new Sysparv5Id(
				"YZCS", "01"));
		skcs = new Long(v5.getPmvb());
		v5 = (Sysparv5) this.genericService.get(Sysparv5.class, new Sysparv5Id(
				"YZCS", "02"));
		dtcs = new Long(v5.getPmvb());
		dtPercentage = new Long(v5.getPmvc());
		jgtime = new Long(v5.getPmvd());
		return SUCCESS;
	}
	public String zwsetsave() {
		this.strutsAction=VIEW;
		Sysparv5 v5 = (Sysparv5) this.genericService.get(Sysparv5.class,
				new Sysparv5Id("YZCS", "00"));
		v5.setPmvb(qjcs.toString());
		this.genericService.update(v5);
	
		Sysparv5 v51 = (Sysparv5) this.genericService.get(Sysparv5.class, new Sysparv5Id(
				"YZCS", "01"));
		v51.setPmvb(skcs.toString());
		this.genericService.update(v51);
		Sysparv5 v52 = (Sysparv5) this.genericService.get(Sysparv5.class, new Sysparv5Id(
				"YZCS", "02"));
		v52.setPmvb(dtcs.toString());
		v52.setPmvc(dtPercentage.toString());
		v52.setPmvd(jgtime.toString());
		this.genericService.update(v52);
		return SUCCESS;
	}
}
*/