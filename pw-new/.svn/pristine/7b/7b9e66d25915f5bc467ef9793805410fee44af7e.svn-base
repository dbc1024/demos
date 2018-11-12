package com.ectrip.sys.other.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Vote;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.other.dao.idao.IVoteDAO;
import com.ectrip.sys.other.service.iservice.IVoteService;

/**
 * <li>Description:ͶƱ����Service</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-08</li>
 */
public class VoteService implements IVoteService {

	private IVoteDAO voteDao;

	public IVoteDAO getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(IVoteDAO voteDao) {
		this.voteDao = voteDao;
	}

	/**
	 * <li>Description:����ͶƱ����</li><br>
	 * <li>@param vote ����ͶƱ��������</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li><br>
	 */
	public void addVote(Vote vote, Syslog syslog) throws Exception {
		this.voteDao.addVote(vote, syslog);
	}

	/**
	 * <li>Description:ɾ��ͶƱ����</li><br>
	 * <li>@param vote ɾ��ͶƱ����</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li>
	 */
	public void deleteVote(Vote vote, Syslog syslog) throws Exception {
		this.voteDao.deleteVote(vote, syslog);
	}

	/**
	 * <li>Description:�޸�ͶƱ����</li><br>
	 * <li>@param vote �޸�ͶƱ����</li><br>
	 * <li>@param syslog ������־</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li>
	 */
	public void updateVote(Vote vote, Syslog syslog) throws Exception {
		this.voteDao.updateVote(vote, syslog);
	}

	/**
	 * <li>Description:����ͶƱ�����Ų�ѯͶƱ����</li><br>
	 * <li>@param ivoteid ͶƱ������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-08</li><br>
	 * <li>@return</li><br>
	 * <li>@author Jzhenhua</li><br>
	 */
	public Vote getVoteById(Long ivoteid) throws Exception {
		return this.voteDao.getVoteById(ivoteid);
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
		return this.voteDao.getVoteListView(page, pageSize, url);
	}

}
