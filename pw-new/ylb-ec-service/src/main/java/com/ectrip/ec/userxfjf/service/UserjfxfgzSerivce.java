package com.ectrip.ec.userxfjf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.ec.model.user.Userjfxfgz;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfxfgzDAO;
import com.ectrip.ec.userxfjf.service.iservice.IUserjfxfgzSerivce;
@Service
public class UserjfxfgzSerivce implements IUserjfxfgzSerivce {

	private IUserjfxfgzDAO userjfxfgzDao;

	public IUserjfxfgzDAO getUserjfxfgzDao() {
		return userjfxfgzDao;
	}

	public void setUserjfxfgzDao(IUserjfxfgzDAO userjfxfgzDao) {
		this.userjfxfgzDao = userjfxfgzDao;
	}

	public List getAlljfxfgz() {

		return this.userjfxfgzDao.getAlljfgz();
	}

	public List getMaxSeq() {

		return this.userjfxfgzDao.getMaxSeq();
	}

	public void addjfxfgz(Userjfxfgz jfxfgz) {
		this.userjfxfgzDao.addjfgz(jfxfgz);

	}

	public Userjfxfgz getBySeqJfxfgz(int seq) {
		return this.userjfxfgzDao.getBySeqJfxfgz(seq);
	}

	public void deljfxfgz(int seq) {
		this.userjfxfgzDao.deljfgz(seq);

	}

	public void updatejfxfgz(Userjfxfgz jfxfgz) {
		this.userjfxfgzDao.updatejfgz(jfxfgz);
	}

	public List isRepNumb(int stnumb, int ednumb) {
		return this.userjfxfgzDao.isRepNumb(stnumb, ednumb);
	}

	public List isRepNumb(int stnumb, int ednumb, int seq) {
		return this.userjfxfgzDao.isRepNumb(stnumb, ednumb, seq);
	}

}
