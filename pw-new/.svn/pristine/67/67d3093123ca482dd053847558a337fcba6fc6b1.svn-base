package com.ectrip.ec.custom.dao;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custom.dao.idao.ILingPiaoUserDAO;
import com.ectrip.ec.model.user.Lingpiaouser;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

public class LingPiaoUserDAO extends GenericDao implements ILingPiaoUserDAO {

	/**
	 * ������Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog�û���־
	 * return:void
	 * Date:2012-2-8
	 */
	public void addLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog){
		Long seq = this.getMaxPk("seq", "Lingpiaouser");
		lingpiaouser.setSeq(seq+1);
		this.save(lingpiaouser);
		
		
		Long logid = this.getMaxPk("sysid", "Customlog");
		
		customlog.setLogdatetime(Tools.getDayTimes());
		customlog.setStlg("0197");
		customlog.setBrief(lingpiaouser.getUsid()+" ������ϵ��");
		customlog.setNote("��ϵ����Ϣ������["+lingpiaouser.getUsername()+"],֤����["+lingpiaouser.getZjhm()+"],��ϵ�绰["+lingpiaouser.getMobile()+"],�����["+lingpiaouser.getFaxno()+"]");
		customlog.setSysid(logid+1);
		this.save(customlog);
		
	}
	/**
	 * �޸���Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void updateLingPiaoUser(Lingpiaouser lingpiaouser,Customlog customlog){
		
		this.update(lingpiaouser);
		
		Long logid = this.getMaxPk("sysid", "Customlog");		
		customlog.setLogdatetime(Tools.getDayTimes());
		customlog.setStlg("0198");
		customlog.setBrief(lingpiaouser.getUsid()+" �޸���ϵ��");
		customlog.setNote("��ϵ����Ϣ������["+lingpiaouser.getUsername()+"],֤����["+lingpiaouser.getZjhm()+"],��ϵ�绰["+lingpiaouser.getMobile()+"],�����["+lingpiaouser.getFaxno()+"]");
		customlog.setSysid(logid+1);
		this.save(customlog);
	}
	/**
	 * ɾ����Ʊ��ϵ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @param customlog
	 * return:void
	 * Date:2012-2-8
	 */
	public void deleteLingPiaoUser(Long seq,Customlog customlog){
		
		Lingpiaouser lingpiaouser = (Lingpiaouser)this.get(Lingpiaouser.class, seq);
		if(lingpiaouser!=null){
			Long logid = this.getMaxPk("sysid", "Customlog");
			
			customlog.setLogdatetime(Tools.getDayTimes());
			customlog.setStlg("0199");
			customlog.setBrief(lingpiaouser.getUsid()+" ɾ����ϵ��");
			customlog.setNote("��ϵ����Ϣ������["+lingpiaouser.getUsername()+"],֤����["+lingpiaouser.getZjhm()+"],��ϵ�绰["+lingpiaouser.getMobile()+"],�����["+lingpiaouser.getFaxno()+"]");
			customlog.setSysid(logid+1);
			this.save(customlog);
		}
		
		this.deleteByKey(Lingpiaouser.class, seq);
	}
	/**
	 * �鿴
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Lingpiaouser
	 * Date:2012-2-8
	 */
	public Lingpiaouser queryLingPiaoUser(Long seq){
		Lingpiaouser ling = new Lingpiaouser();
		ling = (Lingpiaouser)this.get(Lingpiaouser.class, seq);
		if(ling!=null){
			String zjlb = ling.getZjlb();
			Sysparv5Id id = new Sysparv5Id();
			id.setPmcd(zjlb);
			id.setPmky("ZJTP");
			Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, id);
			if(sysparv5!=null){
				ling.setStrzjlb(sysparv5.getPmva());
			}
		}
		return ling;
	}
	/**
	 * ��ѯ��Ʊ��ϵ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-2-8
	 */
	public PaginationSupport queryLingPiaoUserList(String userId,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(l.seq as seq,l.usid as usid,l.username as username,l.mobile as mobile,l.zjlb as zjlb,l.zjhm as zjhm,l.faxno as faxno,l.isfirst as isfirst) from Lingpiaouser l where l.usid='"+userId+"' ");
		
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map =(Map)list.get(i);
				if(map.get("zjlb")!=null && !"".equals(map.get("zjlb"))){
					String zjlb =map.get("zjlb").toString();
					Sysparv5Id id = new Sysparv5Id();
					id.setPmcd(zjlb);
					id.setPmky("ZJTP");
					Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, id);
					if(sysparv5!=null){
						map.put("strzjlb", sysparv5.getPmva());
					}
				}
			}
		}
		return ps;
	}
	/**
	 * �����û���ѯ���е���ϵ���б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:List
	 * Date:2012-2-14
	 */
	public List queryLingPiaoUserList(String usid){
		List list = new ArrayList();
		String hsql =" from Lingpiaouser where usid='"+usid+"' ";
		list = this.find(hsql);
		return list;
	}
	/**
	 * ������ϵ��ΪĬ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param lingpiaouser
	 * return:void
	 * Date:2012-2-15
	 */
	public void updateLingPiaoFirst(Lingpiaouser lingpiaouser){
		String hsql =" from Lingpiaouser where usid='"+lingpiaouser.getUsid()+"' and seq <> "+lingpiaouser.getSeq();
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			for (int j = 0; j < list.size(); j++) {
				Lingpiaouser lin = (Lingpiaouser)list.get(j);
				lin.setIsfirst(0);
				this.update(lin);
			}
			
		}
		this.update(lingpiaouser);
	}

}

