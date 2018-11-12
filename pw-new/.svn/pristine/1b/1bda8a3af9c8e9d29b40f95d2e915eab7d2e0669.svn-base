package com.ectrip.ec.userxfjf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Userjf;
import com.ectrip.ec.model.user.Userjflist;
import com.ectrip.ec.model.user.Userjfset;
import com.ectrip.ec.model.user.Userjfxfgz;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfDao;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class UserjfDao extends GenericDao implements IUserjfDao {
	/**
	 * *
	 * Describe:添加用户积分
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#insertUserjf(com.ectrip.model.user.Userjf, com.ectrip.model.syspar.Syslog)
	 * @param userjf
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public void insertUserjf(Userjf userjf, Long points, Syslog syslog) {
		Userjf user = (Userjf) this.get(Userjf.class, userjf.getSeq());
		user.setJftype(userjf.getJftype());
		user.setJfyear(userjf.getJfyear());
		user.setIsvalid(userjf.getIsvalid());
		user.setPoints(user.getPoints()+points);
		this.update(user);
		if(points!=0L){
			Userjflist userjflist = new Userjflist();
			Long id = this.getMaxPk("seq", "Userjflist");
			userjflist.setSeq(id+1);
			userjflist.setUsid(user.getUsid());
			userjflist.setJflb(0L);
			userjflist.setJfgz(0L);
			userjflist.setSdate(Tools.getDayTimes());
			userjflist.setPoints(points);
			userjflist.setDtmakedate(Tools.getDayTimes());
			this.save(userjflist);	//保存积分明细表
		}
		syslog.setStlg("0237");
		syslog.setBrief("新增用户积分：用户名称："+userjf.getUsid());
		syslog.setNote("用户名称："+userjf.getUsid()+",积分："+points+",时间："+Tools.getDayTimes());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	/**
	 * *
	 * Describe:查看所有用户积分
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#showlistUserjf(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public PaginationSupport showlistUserjf(String usid,int pageSize, int startInt,String url) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Userjf us");
		if(!usid.equals("")){
			hsql.append(" where us.usid='"+usid+"'");
		}
		hsql.append(" order by us.seq");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:查看用户积分
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#viewUserjf(java.lang.Long)
	 * @param seq
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(Long seq) {
		String sql = " from Userjf us where us.seq = "+seq;
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Userjf user = (Userjf)list.get(0);
			return user;
		}
		return null;
	}
	/**
	 * *
	 * Describe:显示用户积分表里的所有用户
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#showUserList()
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public List showUserList() {
		String sql = " from Userjf";
		List list = this.find(sql);
		return list;
	}
	/**
	 * *
	 * Describe:根据用户名称查找用户积分
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#viewUserjf(java.lang.String)
	 * @param usid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(String usid) {
		String sql = " from Userjf us where us.usid = '"+usid+"'";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Userjf user = (Userjf)list.get(0);
			return user;
		}
		return null;
	}
	
	/**
	 * *
	 * Describe:根据用户名称查看用户积分详细
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#showUserjflist(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public PaginationSupport showUserjflist(String usid, int pageSize,int startInt, String url) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(us.seq as seq,us.usid as usid,us.jflb as jflb,us.sdate as sdate,us.points as points,us.jfgz as jfgz) from Userjflist us where us.usid = '"+usid+"'");
		sql.append(" order by us.sdate desc");
		PaginationSupport ps = this.findPage(sql.toString(), pageSize, startInt, url);
		List list = ps.getItems();
		for(int i=0;i<list.size();i++){
			Map map = (Map) list.get(i);
			if(Integer.parseInt(map.get("jflb").toString())==1){	//积分类别为1时，查询积分规则表
				int jfgz = Integer.parseInt(map.get("jfgz").toString());
				List lst = this.find(" select ut.jfname as jfname from Userjflist us,Userjfset ut where ut.jfgzid="+jfgz);
				map.put("jfname", lst.get(0));	//插入规则名称
			}
			if(Integer.parseInt(map.get("jflb").toString())==-1){	//积分类别为-1时，查询积分消费规则表
				int jfgz = Integer.parseInt(map.get("jfgz").toString());
				List lst = this.find(" select uz.xfname as jfname from Userjflist us,Userjfxfgz uz where uz.seq="+jfgz);
				map.put("jfname", lst.get(0));	//插入消费规则名称
			}
			if(Integer.parseInt(map.get("jflb").toString())==0){	//积分类别为0时，为后台增加积分
				map.put("jfname", "后台增加积分");	//插入消费规则名称
				map.put("jfgz", "后台增加积分");
			}
		}
		return ps;
	}
	/**
	 * *
	 * Describe:根据积分规则增加用户积分
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#addUserJf(java.lang.String, java.lang.String, java.lang.Long)
	 * @param usid	用户名称
	 * @param jfcode	积分规则代码
	 * @param amount	消费数量(不是积分数量)
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public boolean addUserJf(String usid, String jfcode, Long amount) {
		String sql = " from Userjfset ut where ut.jfcode = '"+jfcode+"' and ut.isvalid = 1";
		String hsql = " from Userjf uf where uf.usid = '"+usid+"'";
		List jfsetList = this.find(sql);	//查看积分规则
		List jflist = this.find(hsql);		//查看积分用户是否存在
		if(jfsetList!=null&&jfsetList.size()>0){	//判断积分规则是否存在
			Userjfset userjfset = (Userjfset) jfsetList.get(0);
			
			Long points = amount/Long.parseLong(userjfset.getBasepoint())*Long.parseLong(userjfset.getPoints());	//根据消费数量计算应增加的积分数量
			Userjflist userjflist = new Userjflist();
			Long maxid = this.getMaxPk("seq", "Userjflist");
			userjflist.setSeq(maxid+1);
			userjflist.setUsid(usid);
			userjflist.setJflb(1L);
			userjflist.setJfgz(userjfset.getJfgzid());
			userjflist.setSdate(Tools.getDayTimes());
			userjflist.setPoints(points);
			userjflist.setDtmakedate(Tools.getDayTimes());
			this.save(userjflist);	//保存积分明细表
			
			if(jflist!=null&&jflist.size()>0){	//判断积分用户是否存在
				Userjf userjf = (Userjf) jflist.get(0);
				userjf.setPoints(userjf.getPoints()+points);
				this.update(userjf);	//更新用户的积分
			}else{
				Userjf userjf = new Userjf();
				Long id = this.getMaxPk("seq", "Userjf");
				userjf.setSeq(id+1);
				userjf.setUsid(usid);
				userjf.setPoints(points);
				userjf.setIsvalid(1L);
				userjf.setDtmakedate(Tools.getDayTimes());
				this.save(userjf);	//保存积分用户
			}
			return true;
		}else{
			return false;	//积分规则不存在
		}
		
	}
	/**
	 * *
	 * Describe:积分转预付款
	 * @see com.ectrip.system.userxfjf.dao.idao.IUserjfDao#changejftoyfk(java.lang.String, java.lang.String, java.lang.Long)
	 * @param usid
	 * @param xfcode
	 * @param jf
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-27
	 */
	public Useryfk changejftoyfk(String usid, String xfcode, Long jf){
		String sql = " from Userjfxfgz uz where uz.xfcode='"+xfcode+"' and uz.isvalid = 1";
		String hsql = " from Userjf uf where uf.usid = '"+usid+"'";
		List xfList = this.find(sql);		//查看积分消费规则
		List jflist = this.find(hsql);		//查看积分用户是否存在
		Useryfk useryfk = new Useryfk();
		if(xfList!=null&&xfList.size()>0){
			Userjfxfgz xfgz = (Userjfxfgz) xfList.get(0);

			Double yfk = jf*Double.parseDouble(xfgz.getXfbl())/100;//根据积分计算预付款		
			Userjflist userjflist = new Userjflist();
			Long maxid = this.getMaxPk("seq", "Userjflist");
			userjflist.setSeq(maxid+1);
			userjflist.setUsid(usid);
			userjflist.setJflb(-1L);
			userjflist.setJfgz(new Long(xfgz.getSeq()));
			userjflist.setSdate(Tools.getDayTimes());
			userjflist.setPoints(jf);
			userjflist.setDtmakedate(Tools.getDayTimes());
			this.save(userjflist);	//保存积分明细表
			
			Userjf userjf = (Userjf) jflist.get(0);
			userjf.setPoints(userjf.getPoints()-jf);
			this.update(userjf);	//更新用户的积分
			
			useryfk.setUsid(usid);
			useryfk.setBdate(Tools.getTodayString());
			useryfk.setSzmemo("积分转预付款");
			useryfk.setNote("积分转预付款");
			useryfk.setOrderid(xfgz.getSeq().toString());
			useryfk.setPoint(yfk);
			useryfk.setYfklb(1);
			useryfk.setYfksc("15");
			useryfk.setCztp(0);
			
			useryfk.setSeq(0);
		}
		return useryfk;
	}
}

