package com.ectrip.ec.userxfjf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.user.Userjfxfgz;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfxfgzDAO;
@Repository
public class UserjfxfgzDAO extends GenericDao implements IUserjfxfgzDAO {

	public List getAlljfgz() {
		String hql="from Userjfxfgz";
		return this.find(hql);
	}

	public void addjfgz(Userjfxfgz jfxfgz) {
		 this.save(jfxfgz);
		
	}

	public void deljfgz(int seq) {
		 String hql="delete from Userjfxfgz where seq=?";
		 this.bulkUpdate(hql,new Object[]{seq});
	}

	public void updatejfgz(Userjfxfgz jfxfgz) {
		this.update(jfxfgz);
	}

	public List getMaxSeq(){
		String hql="select max(seq) from Userjfxfgz";
		return this.find(hql);
	}
	
	public Userjfxfgz getBySeqJfxfgz(int seq){
		return (Userjfxfgz)this.get(Userjfxfgz.class, seq);
	}

	public List isRepNumb(int stnumb,int ednumb) {
		String hql="from Userjfxfgz where isvalid='1' and ((( stnumb  < "+stnumb+"  and  ednumb > "+stnumb+")OR ( stnumb < "+ednumb+" AND  ednumb > "+ednumb+")) or (( stnumb > "+stnumb+" and  ednumb  <"+ednumb+")))";
		System.out.println("hql==="+hql);
		return this.find(hql);
	}
	public List isRepNumb(int stnumb,int ednumb,int seq) {
		String hql="from Userjfxfgz where isvalid='1' and ((( stnumb  < "+stnumb+"  and  ednumb > "+stnumb+")OR ( stnumb < "+ednumb+" AND  ednumb > "+ednumb+")) or (( stnumb > "+stnumb+" and  ednumb  <"+ednumb+"))) and seq<> "+seq;
		System.out.println("hql==="+hql);
		return this.find(hql);
	}
	
	
}
