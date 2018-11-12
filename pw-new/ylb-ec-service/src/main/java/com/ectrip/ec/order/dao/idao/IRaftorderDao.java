package com.ectrip.ec.order.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;

public interface IRaftorderDao extends IGenericDao {
	public PaginationSupport querysqraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url);
	public PaginationSupport querytfraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url);
	public PaginationSupport queryraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url);
	public PaginationSupport querycpraftlist(Long iscenicid, Long itickettypeid,
			String usid, String ornm, String zfzt, String stdt, String zfdata,
			Long tripid, int pageSize, int startIndex, String url) ;
	public PaginationSupport queryoridraftlist(Long iscenicid, Long itickettypeid,
			String orid, int pageSize, int startIndex, String url);
}

