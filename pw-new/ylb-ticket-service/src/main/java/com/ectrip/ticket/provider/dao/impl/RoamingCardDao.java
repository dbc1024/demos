package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Roamingcard;
import com.ectrip.ticket.model.provider.Roamingcardpicture;
import com.ectrip.ticket.provider.dao.IRoamingCardDao;

@Repository
public class RoamingCardDao extends GenericDao implements IRoamingCardDao {
	/**
	 * *
	 * Describe:查看漫游卡信息列表
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#showlistCrad(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param cardno
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2013-4-10
	 */
	public PaginationSupport showlistCrad(String usid,String cardno,String idcard,String username,Long radiobutton,String rzti,String ldti,int pageSize, int startInt, String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(rc.seq as seq,rc.cardno as cardno,rc.idcard as idcard,rc.mobilephone as mobilephone,rc.usid as usid,rc.telphone as telphone,rc.address as address,rc.email as email,rc.note as note,rc.byisuse as byisuse,rc.itickettypyid as itickettypyid,rc.username as username,substr(rc.dtmakedate,1,10) as dtmakedate,ti.sztickettypename as sztickettypename,es.szemployeename as empname) from Roamingcard rc,Edmtickettypetab ti,Esfemployeetab es where rc.itickettypyid = ti.itickettypeid and es.empid = rc.usid ");
		if(usid!=null&&!usid.equals("")){
			hsql.append(" and rc.usid = '"+usid+"' ");
		}
		if(radiobutton==0){
			if(cardno!=null&&!cardno.equals("")){
				hsql.append(" and upper(rc.cardno) = '"+cardno.toUpperCase()+"' ");
			}
			if(username!=null&&!username.equals("")){
				hsql.append(" and rc.username like '%"+username+"%' ");
			}
			if(idcard!=null&&!idcard.equals("")){
				hsql.append(" and rc.idcard = '"+idcard+"' ");
			}
		}
		if(radiobutton==1){
			if(rzti!=null&&!rzti.equals("")){
				hsql.append(" and substr(rc.dtmakedate,1,10)>= '"+rzti+"' ");
			}
			if(ldti!=null&&!ldti.equals("")){
				hsql.append(" and substr(rc.dtmakedate,1,10)<= '"+ldti+"' ");
			}
		}
		hsql.append(" order by rc.dtmakedate desc");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:添加漫游卡信息
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#insertRoamingCard(com.ectrip.model.provider.Roamingcard, com.ectrip.model.syspar.Syslog)
	 * @param roamingcard
	 * @param syslog
	 * @author chenxinhao
	 * Date:2013-4-10
	 * @throws Exception 
	 */
	public void insertRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		try{
			Long maxid = this.getMaxPk("seq", "Roamingcard");
			roamingcard.setSeq(maxid+1);
			roamingcard.setDtmakedate(Tools.getDayTimes());
			roamingcard.setCardno(roamingcard.getCardno().toUpperCase());
			roamingcard.setByisuse(0L);
			this.save(roamingcard);
			if(roamingcard.getUpids()!=null){
				for(int i = 0;i<roamingcard.getUpids().length;i++){
					Roamingcardpicture pic = new Roamingcardpicture();
					pic.setCardno(roamingcard.getCardno().toUpperCase());
					pic.setSeq(maxid+1);
					pic.setUpid(Long.parseLong(roamingcard.getUpids()[i]));
					Long maxseq = this.getMaxPk("pictureid", "Roamingcardpicture");
					pic.setPictureid(maxseq+1);
					pic.setDtmakedate(Tools.getDayTimes());
					this.save(pic);
				}
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	/**
	 * *
	 * Describe:更新漫游卡信息
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#updateRoamingCard(com.ectrip.model.provider.Roamingcard, com.ectrip.model.syspar.Syslog)
	 * @param roamingcard
	 * @param syslog
	 * @author chenxinhao
	 * Date:2013-4-10
	 * @throws Exception 
	 */
	public void updateRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		try{
			Roamingcard card = (Roamingcard) this.get(Roamingcard.class, roamingcard.getSeq());
			if(roamingcard.getUpids()!=null){
				List picList = this.find(" from Roamingcardpicture pic where pic.seq = "+card.getSeq());
				if(picList!=null&&picList.size()>0){
					for(int i = 0;i<picList.size();i++){
						Roamingcardpicture pic = (Roamingcardpicture) picList.get(i);
						this.delete(pic);
					}
				}
				for(int i = 0;i<roamingcard.getUpids().length;i++){
					Roamingcardpicture pic = new Roamingcardpicture();
					pic.setCardno(roamingcard.getCardno().toUpperCase());
					pic.setSeq(card.getSeq());
					pic.setUpid(Long.parseLong(roamingcard.getUpids()[i]));
					Long maxseq = this.getMaxPk("pictureid", "Roamingcardpicture");
					pic.setPictureid(maxseq+1);
					pic.setDtmakedate(Tools.getDayTimes());
					this.save(pic);
				}
			}
			
			card.setCardno(roamingcard.getCardno().toUpperCase());
			card.setUsername(roamingcard.getUsername());
			card.setItickettypyid(roamingcard.getItickettypyid());
			card.setIdcard(roamingcard.getIdcard());
			card.setMobilephone(roamingcard.getMobilephone());
			card.setUsid(roamingcard.getUsid());
			card.setTelphone(roamingcard.getTelphone());
			card.setAddress(roamingcard.getAddress());
			card.setEmail(roamingcard.getEmail());
			card.setNote(roamingcard.getNote());
			card.setByisuse(0L);
			this.update(card);
		
		}catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}
		
	}
	/**
	 * *
	 * Describe:删除漫游卡信息
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#deleteRoamingCard(com.ectrip.model.provider.Roamingcard, com.ectrip.model.syspar.Syslog)
	 * @param roamingcard
	 * @param syslog
	 * @author chenxinhao
	 * Date:2013-4-10
	 * @throws Exception 
	 */
	public void deleteRoamingCard(Roamingcard roamingcard,Syslog syslog) throws Exception{
		try{
			Roamingcard card = (Roamingcard) this.get(Roamingcard.class, roamingcard.getSeq());	
			List picList = this.find(" from Roamingcardpicture pic where pic.seq = "+card.getSeq());
			if(picList!=null&&picList.size()>0){
				for(int i = 0;i<picList.size();i++){
					Roamingcardpicture pic = (Roamingcardpicture) picList.get(i);
					this.delete(pic);
				}
			}
			this.delete(card);
		}catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	/**
	 * *
	 * Describe:查找漫游卡信息
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#findCardByCardno(java.lang.String, java.lang.Long, java.lang.String)
	 * @param cardno	卡号
	 * @param seq	主键
	 * @param type	01--新增修改判断是否重复
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2013-4-10
	 */
	public Roamingcard findCardByCardno(String cardno,Long seq,Long itickettypeid,String type) throws Exception{
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(rc.seq as seq,rc.cardno as cardno,rc.idcard as idcard,rc.mobilephone as mobilephone,rc.usid as usid,rc.telphone as telphone,rc.address as address,rc.email as email,rc.note as note,rc.byisuse as byisuse,rc.itickettypyid as itickettypyid,rc.username as username,ti.sztickettypename as sztickettypename,es.szemployeename as empname) from Roamingcard rc,Edmtickettypetab ti,Esfemployeetab es where rc.itickettypyid = ti.itickettypeid and es.empid = rc.usid ");
		if(cardno!=null&&!cardno.equals("")){
			hsql.append(" and upper(rc.cardno) = '"+cardno+"' ");
		}
		if(itickettypeid!=0){
			hsql.append(" and rc.itickettypyid = "+itickettypeid);
		}
		if(type.equals("01")){
			if(seq!=0){
				hsql.append(" and rc.seq !="+seq);
				hsql.append(" and rc.byisuse !='-1' ");
			}else{
				hsql.append(" and rc.byisuse !='-1' ");
			}
		}else{
			if(seq!=0){
				hsql.append(" and rc.seq ="+seq);
			}
		}
		System.out.println(hsql.toString());
		List list = this.find(hsql.toString());
		if(list!=null&&list.size()>0){
			Roamingcard card = new Roamingcard();
			BeanUtils.populate(card,(Map) list.get(0));
			if(cardno!=null&&!cardno.equals("")){
				String hsqls="select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.pictureid as pictureid) from Roamingcardpicture pic,Upfile up where up.upid = pic.upid and pic.seq="+card.getSeq();
				List list2 = this.find(hsqls);
				card.setList(list2);
			}
			return card;
		}
		return null;
	}
	/**
	 * *
	 * Describe:可代销的漫游卡产品
	 * @see com.ectrip.system.provider.dao.idao.IRoamingCardDao#showAllticket()
	 * @return
	 * @author chenxinhao
	 * Date:2013-4-10
	 */
	public List showAllticket(){
//		String hsql = "select new map(ti.itickettypeid as itickettypeid,ti.sztickettypename as sztickettypename) from Edmtickettypetab ti where ti.byisuse = 1 and ti.bycategorytype = '0014' and ti.isdm = 1";
		String hsql = "select new map(ti.itickettypeid as itickettypeid,ti.sztickettypename as sztickettypename) from Edmtickettypetab ti where ti.byisuse = 1 and ti.bycategorytype = '0014'";
		return this.find(hsql);
	}
	
	public List showPic(Long seq){
		String sql = " from Upfile where upid in (select upid from Roamingcardpicture where seq = "+seq+") ";
		List list = this.find(sql);
		return list;
	}
	
	public List showRoamingEmp(){
		String sql = " select new map(em.iemployeeid as iemployeeid,em.empid as empid,em.szemployeename as szemployeename) from Esfemployeetab em,Esfemployeepoststab ep where em.iemployeeid = ep.iemployeeid and ep.ipostsid = 42";
		return this.find(sql);
	}
}

