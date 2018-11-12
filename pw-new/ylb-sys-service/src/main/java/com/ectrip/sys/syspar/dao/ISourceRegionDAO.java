package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Galsourceregiontab;

public interface ISourceRegionDAO extends IGenericDao {
	
	public PaginationSupport listAllPage(Long prentid,String lever,String next,int page,int pagesize,String url);

	public Galsourceregiontab querySource(Long sourceid);
	public void updateIleverseq(Long ilever,Long ileverseq,Long sourceid);
	public Long getMaxObjectId(String tablename,String colums);
	public Long getMaxIleverSeq(String tablename,String colums,String ilever);
	public boolean retiveSource(Long soureid);
	public List getAllSourceRegion();
	public List SourceRegionJson(Long regionid);
	
	public List getAllAreas();
	public List getFourLeavel();
}
