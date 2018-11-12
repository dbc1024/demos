package com.ectrip.sys.model.other;

import java.math.BigDecimal;
import java.util.List;

/**
 * ͶƱ����. @author MyEclipse Persistence Tools
 */
public class Vote implements java.io.Serializable {

	// Fields

	private Long ivoteid; // ͶƱ����
	private String szvotename; // ͶƱ��������
	private Long iselecttp; // ѡ������0:��ѡ,1:��ѡ
	private String begindate; // ��Ч��ʼʱ��
	private String enddate; // ��Ч����ʱ��
	private Long istate; // ����״̬0:����,1:����
	private Long ireward; // �Ƿ��н�Ʒ0��û�У�1����
	private String rewardmessage; // ��Ʒ��Ϣ
	private Long isurvey; // �Ƿ�μ���ҳ����0�����μӣ�1���μ�

	// not database fields
	private List<Voteoption> voteoptions; // ѡ����
	private Long count; // ͶƱ����

	// Constructors

	/** default constructor */
	public Vote() {
	}

	/** minimal constructor */
	public Vote(Long ivoteid, String szvotename, Long iselecttp, Long istate,
			Long ireward) {
		this.ivoteid = ivoteid;
		this.szvotename = szvotename;
		this.iselecttp = iselecttp;
		this.istate = istate;
		this.ireward = ireward;
	}

	/** full constructor */
	public Vote(Long ivoteid, String szvotename, Long iselecttp,
			String begindate, String enddate, Long istate, Long ireward,
			String rewardmessage, Long isurvey) {
		this.ivoteid = ivoteid;
		this.szvotename = szvotename;
		this.iselecttp = iselecttp;
		this.begindate = begindate;
		this.enddate = enddate;
		this.istate = istate;
		this.ireward = ireward;
		this.rewardmessage = rewardmessage;
		this.isurvey = isurvey;
	}

	// Property accessors

	public Long getIvoteid() {
		return this.ivoteid;
	}

	public void setIvoteid(Long ivoteid) {
		this.ivoteid = ivoteid;
	}

	public String getSzvotename() {
		return this.szvotename;
	}

	public void setSzvotename(String szvotename) {
		this.szvotename = szvotename;
	}

	public Long getIselecttp() {
		return this.iselecttp;
	}

	public void setIselecttp(Long iselecttp) {
		this.iselecttp = iselecttp;
	}

	public String getBegindate() {
		return this.begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getIstate() {
		return this.istate;
	}

	public void setIstate(Long istate) {
		this.istate = istate;
	}

	public Long getIreward() {
		return this.ireward;
	}

	public void setIreward(Long ireward) {
		this.ireward = ireward;
	}

	public String getRewardmessage() {
		return this.rewardmessage;
	}

	public void setRewardmessage(String rewardmessage) {
		this.rewardmessage = rewardmessage;
	}

	public Long getIsurvey() {
		return this.isurvey;
	}

	public void setIsurvey(Long isurvey) {
		this.isurvey = isurvey;
	}

	public List<Voteoption> getVoteoptions() {
		return voteoptions;
	}

	public void setVoteoptions(List<Voteoption> voteoptions) {
		this.voteoptions = voteoptions;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}