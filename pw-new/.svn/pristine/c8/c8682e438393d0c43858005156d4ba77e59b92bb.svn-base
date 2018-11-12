package com.ectrip.sys.other.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.other.Vote;
import com.ectrip.sys.model.other.Voteoption;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.other.dao.idao.IVoteDAO;

/**
 * <li>Description:ͶƱ����DAO</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-08</li>
 */
public class VoteDAO extends GenericDao implements IVoteDAO {

	/**
	 * <li>Description:����ͶƱ����</li><br>
	 * <li>@param vote ����ͶƱ��������</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li><br>
	 */
	public void addVote(Vote vote, Syslog syslog) throws Exception {
		this.save(vote);

		// ���ѡ����
		if (null != vote.getVoteoptions() && vote.getVoteoptions().size() != 0) {
			// ѭ��������ѡ��浽���ݿ�
			for (int i = 0; i < vote.getVoteoptions().size(); i++) {
				// ��ȡҳ������������
				Voteoption voteoption = vote.getVoteoptions().get(i);
				if (null != voteoption && null != voteoption.getContent()
						&& !"".equals(voteoption.getContent())) {

					// ����ID
					voteoption.setIvoteoptionid(this.getMaxPk("ivoteoptionid",
							"Voteoption") + 1);
					if (null == voteoption.getIsumnum()
							|| "".equals(voteoption.getIsumnum())) {
						voteoption.setIsumnum(new Long(0));
					}
					voteoption.setIvoteid(vote.getIvoteid());

					// ����
					this.save(voteoption);
				}
			}
		}

		// ��־����
		syslog.setStlg("0152");
		syslog.setBrief("ͶƱ���������" + vote.getIvoteid());
		syslog.setNote("����ͶƱ���⣺" + vote.getSzvotename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:ɾ��ͶƱ����</li><br>
	 * <li>@param vote ɾ��ͶƱ����</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li>
	 */
	@SuppressWarnings("unchecked")
	public void deleteVote(Vote vote, Syslog syslog) throws Exception {
		// ɾ�����и�ͶƱ�����ѡ��
		this.deleteAll(this.find("from Voteoption v where v.ivoteid="
				+ vote.getIvoteid()));

		this.delete(vote);

		// ��־����
		syslog.setStlg("0153");
		syslog.setBrief("ͶƱ���������" + vote.getIvoteid());
		syslog.setNote("ɾ��ͶƱ���⣺" + vote.getSzvotename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:�޸�ͶƱ����</li><br>
	 * <li>@param vote �޸�ͶƱ����</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li>
	 */
	@SuppressWarnings("unchecked")
	public void updateVote(Vote vote, Syslog syslog) throws Exception {
		this.update(vote);

		// ɾ�����и�ͶƱ�����ѡ��
		this.deleteAll(this.find("from Voteoption v where v.ivoteid="
				+ vote.getIvoteid()));

		// ����
		// ���ѡ����
		if (null != vote.getVoteoptions() && vote.getVoteoptions().size() != 0) {
			// ѭ��������ѡ��浽���ݿ�
			for (int i = 0; i < vote.getVoteoptions().size(); i++) {
				// ��ȡҳ������������
				Voteoption voteoption = vote.getVoteoptions().get(i);
				if (null != voteoption && null != voteoption.getContent()
						&& !"".equals(voteoption.getContent())) {

					// ����ID
					voteoption.setIvoteoptionid(this.getMaxPk("ivoteoptionid",
							"Voteoption") + 1);

					voteoption.setIvoteid(vote.getIvoteid());
					if (null == voteoption.getIsumnum()
							|| "".equals(voteoption.getIsumnum())) {
						voteoption.setIsumnum(new Long(0));
					}

					// ����
					this.save(voteoption);
				}
			}
		}

		// ��־����
		syslog.setStlg("0154");
		syslog.setBrief("ͶƱ���������" + vote.getIvoteid());
		syslog.setNote("�޸�ͶƱ���⣺" + vote.getSzvotename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:����ͶƱ�����Ų�ѯͶƱ����</li><br>
	 * <li>@param ivoteid ͶƱ������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li><br>
	 * <li>@return</li><br>
	 * <li>@author Jzhenhua</li><br>
	 */
	@SuppressWarnings("unchecked")
	public Vote getVoteById(Long ivoteid) throws Exception {

		// ��ȡͶƱ����
		Vote vote = (Vote) this.get(Vote.class, ivoteid);

		// ���ͶƱѡ����浽ͶƱ������
		vote
				.setVoteoptions(this
						.find("from Voteoption option where option.ivoteid="
								+ ivoteid));

		return vote;
	}

	/**
	 * <li>Description:��ѯͶƱ������ʾ�б�</li><br>
	 * <li>@param page ��ǰҳ</li><br>
	 * <li>@param pageSize ��ǰҳ��С</li><br>
	 * <li>@param url��ǰ��ҳURL</li><br>
	 * <li>@return��ҳ��ʾ�б�</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li><br>
	 */
	public PaginationSupport getVoteListView(int page, int pageSize, String url)
			throws Exception {
		String sql = "select distinct new map(vote.ivoteid as ivoteid,vote.szvotename as szvotename,vote.iselecttp as iselecttp,vote.begindate as begindate,vote.enddate as enddate,vote.istate as istate,vote.ireward as ireward,vote.isurvey as isurvey,(select sum(o.isumnum) from Voteoption o where vote.ivoteid=o.ivoteid group by o.ivoteid) as count) from Vote vote,Voteoption option where vote.ivoteid=option.ivoteid ";

		return this.findPage(sql, pageSize, page, url);
	}
}
