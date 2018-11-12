package com.ectrip.ec.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.user.dao.idao.ICustomViewDAO;
import com.ectrip.sys.model.syspar.Syslog;

public class CustomViewDAO extends GenericDao implements ICustomViewDAO{

	/**
	 * *
	 * Describe:
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#AllListcustom(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public PaginationSupport AllListcustom(String lname,int pageSize, int startIndex,
			String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.iregionalid as iregionalid,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno) from Custom c where c.ibusinessid=3 ");

		if (lname!=null&&!lname.equals("")) {// ����ϵ�˲�ѯ
			sql.append(" and c.lname like '%" + lname + "%' ");
		}	

		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:�鿴�Ӵ��û���Ϣ
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#Lookcustomview(java.lang.String)
	 * @param usid
	 * @throws Exception
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public Custom Lookcustomview(String usid) throws Exception {
		String sql="select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.iregionalid as iregionalid,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,ga.szinnername as szregionalname) from Custom c,Galsourceregiontab ga where ga.iregionalid=c.iregionalid and c.usid='"+usid+"' and c.ibusinessid=3";
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Custom custom=new Custom();
			BeanUtils.populate(custom, (Map) list.get(0));
			
			return custom;
		}
		
		
	}

	/**
	 * *
	 * Describe:ɾ���Ӵ��û���Ϣ
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#delcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void delcustomview(Custom custom, Syslog syslog) {
		String sql=" from Custom c where c.usid='"+custom.getUsid()+"' and c.ibusinessid=3";
		Custom tom=(Custom) this.find(sql).get(0);
		this.delete(tom);
		
		syslog.setStlg("0196");
		syslog.setBrief("�Ӵ��û���" + custom.getUsid());
		syslog.setNote("ɾ���Ӵ��û���" + custom.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *
	 * Describe:�޸ĽӴ��û���Ϣ
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#editcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void editcustomview(Custom custom, Syslog syslog) {
		String sql=" from Custom c where c.usid='"+custom.getUsid()+"' and c.ibusinessid=3";
		Custom tom=(Custom) this.find(sql).get(0);
		tom.setUsid(custom.getUsid());
		tom.setZjhm(custom.getZjhm());
		tom.setIregionalid(custom.getIregionalid());
		tom.setEmail(custom.getEmail());
		tom.setLname(custom.getLname());
		tom.setMobile(custom.getMobile());
		tom.setQq(custom.getQq());
		tom.setMsn(custom.getMsn());
		tom.setTelno(custom.getTelno());
		tom.setAddr(custom.getAddr());
		tom.setFaxno(custom.getFaxno());
		tom.setPostno(custom.getPostno());
		this.update(tom);
		
		syslog.setStlg("0195");
		syslog.setBrief("�Ӵ��û���" + custom.getUsid());
		syslog.setNote("�޸ĽӴ��û���" + custom.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *
	 * Describe:��ӽӴ��û���Ϣ
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#insertcustomview(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Feb 6, 2012
	 */
	public void insertcustomview(Custom custom, Syslog syslog) {
		custom.setIbusinessid(new Long(3));  //�Ӵ��û�
		custom.setCorpname(custom.getLname());
		this.save(custom);
		
		syslog.setStlg("0194");
		syslog.setBrief("�Ӵ��û���" + custom.getUsid());
		syslog.setNote("��ӽӴ��û���" + custom.getUsid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	
	/**
	 * *
	 * Describe:��ѯ��δ��˵��������û�
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#showChecktomustp(com.ectrip.model.user.Custom, int, int, int, java.lang.String)
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * @author lijingrui
	 * Date:2012-10-30
	 */
	public PaginationSupport showChecktomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) from Custom c  where c.ustp='01'  ");
		hsql.append(" and c.lgtp='02' and c.ttlb='01' ");
		if (radiobutton == 0 && custom.getUsid()!=null&&!custom.getUsid().equals("")) {// ���û���Ų�ѯ
			hsql.append(" and c.usid='" + custom.getUsid() + "' ");
		}
		if (radiobutton == 1) {// ����ϵ�˲�ѯ
			hsql.append(" and c.lname like '%" + custom.getLname() + "%' ");
		}
		if (radiobutton == 2) {// ����˾���Ʋ�ѯ
			hsql.append(" and c.corpname like '%" + custom.getCorpname() + "%' ");
		}
		hsql.append(" and c.status='00' order by  c.bname, c.status, c.ldate desc, c.usid ");
		System.out.println(hsql);
		ps = this.findPage(hsql.toString(), pageSize, startIndex, path);
		return ps;
	}
	
	/**
	 * *
	 * Describe:��ѯ��ͣ���û�
	 * @see com.ectrip.system.user.dao.idao.ICustomViewDAO#showTycustomustp(com.ectrip.model.user.Custom, int, int, int, java.lang.String)
	 * @param custom
	 * @param radiobutton
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return
	 * @author lijingrui
	 * Date:2012-10-30
	 */
	public PaginationSupport showTycustomustp(Custom custom,int radiobutton,int pageSize, int startIndex,String path){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) from Custom c  where 1=1  ");
		if (radiobutton == 0 && custom.getUsid()!=null&&!custom.getUsid().equals("")) {// ���û���Ų�ѯ
			hsql.append(" and c.usid='" + custom.getUsid() + "' ");
		}
		if (radiobutton == 1) {// ����ϵ�˲�ѯ
			hsql.append(" and c.lname like '%" + custom.getLname() + "%' ");
		}
		
		hsql.append(" and c.status='02' order by  c.bname, c.status, c.ldate desc, c.usid ");

		ps = this.findPage(hsql.toString(), pageSize, startIndex, path);
		
		return ps;
	
	}
	
}

