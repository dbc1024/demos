package com.ectrip.ec.userxfjf.dao.idao;

import java.util.List;

import com.ectrip.ec.model.user.Userjfxfgz;



public interface IUserjfxfgzDAO {

	public List getAlljfgz();
	
	public void deljfgz(int seq);
	
	public void updatejfgz(Userjfxfgz jfxfgz);
	
	public void addjfgz(Userjfxfgz jfxfgz);
	
	public List getMaxSeq();
	
	public Userjfxfgz getBySeqJfxfgz(int seq);
	
	public List isRepNumb(int stnumb,int ednumb);
	public List isRepNumb(int stnumb,int ednumb,int seq);
}
