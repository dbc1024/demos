package com.ectrip.ec.order.service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.order.dao.idao.IRaftorderDao;
import com.ectrip.ec.order.service.iservice.IRaftorderService;

public class RaftorderService extends GenericService implements
		IRaftorderService {
	private IRaftorderDao  raftorderDao;

	public IRaftorderDao getRaftorderDao() {
		return raftorderDao;
	}

	public void setRaftorderDao(IRaftorderDao raftorderDao) {
		this.raftorderDao = raftorderDao;
	}
	public PaginationSupport querysqraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url){
		return raftorderDao.querysqraftlist(iscenicid, itickettypeid, usid, ornm, zfzt, stdt, zfdata, tripid, pageSize, startIndex, url);
	}
	public PaginationSupport queryraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url){
		return raftorderDao.queryraftlist(iscenicid, itickettypeid, usid, ornm, zfzt, stdt, zfdata, tripid, pageSize, startIndex, url);

	}
	public PaginationSupport querytfraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url){
		return raftorderDao.querytfraftlist(iscenicid, itickettypeid, usid, ornm, zfzt, stdt, zfdata, tripid, pageSize, startIndex, url);

	}
	public PaginationSupport querycpraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) {
		return raftorderDao.querycpraftlist(iscenicid, itickettypeid, usid, ornm, zfzt, stdt, zfdata, tripid, pageSize, startIndex, url);

	}
	public PaginationSupport queryoridraftlist(Long iscenicid, Long itickettypeid,
			String orid, int pageSize, int startIndex, String url){
		return raftorderDao.queryoridraftlist(iscenicid, itickettypeid, orid, pageSize, startIndex, url);
	}
}

