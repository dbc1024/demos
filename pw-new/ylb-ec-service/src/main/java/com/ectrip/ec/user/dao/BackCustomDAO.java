package com.ectrip.ec.user.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Backcustom;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.user.dao.idao.IBackCustomDAO;
import com.ectrip.sys.model.syspar.Syslog;

public class BackCustomDAO extends GenericDao implements IBackCustomDAO {

	/**
	 * Describe:��ȡ�������б�
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#showlistBackcustom(int, int, java.lang.String)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public PaginationSupport showlistBackcustom(int pageSize, int startInt,String url) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Backcustom ");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}

	/**
	 * Describe:��ѯ�������б�
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#searchlistBackcustom(int, int, java.lang.String, java.lang.String)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public PaginationSupport searchlistBackcustom(int pageSize, int startInt,
			String url, String usid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Backcustom b where b.usid='"+usid+"'");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}

	/**
	 * Describe:�޸ĺ�����
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#viewBackcustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public Backcustom viewBackcustom(String usid) {
		String sql = " from Backcustom b where b.usid ='"+usid+"'";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Backcustom backcustom = (Backcustom) list.get(0);
			return backcustom;
		}else{
			return null;
		}
	}

	/**
	 * Describe:ɾ��������
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#deleteBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void deleteBackcustom(Backcustom backcustom, Syslog syslog) {
		Backcustom back = (Backcustom) this.get(Backcustom.class, backcustom.getUsid());
		this.delete(back);
		
		syslog.setStlg("0196");
		syslog.setBrief("ɾ���������û���"+back.getUsid());
		syslog.setNote("ɾ���������û���"+back.getUsid()+",ʱ�䣺"+back.getDatemake());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Describe:���������
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#insertBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void insertBackcustom(Backcustom backcustom, Syslog syslog) {
//		String maxid = this.getMaxPk("usid", "Backcustom").toString();
//		backcustom.setUsid(maxid+1);
		backcustom.setDatemake(Tools.getDayTimes());
		this.save(backcustom);
		
		syslog.setStlg("0195");
		syslog.setBrief("�����������û���"+backcustom.getUsid());
		syslog.setNote("�����������û���"+backcustom.getUsid()+",ʱ�䣺"+backcustom.getDatemake());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Describe:���º�����
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#updateBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void updateBackcustom(Backcustom backcustom, Syslog syslog) {
		Backcustom back = (Backcustom) this.get(Backcustom.class, backcustom.getUsid());
		back.setUsid(backcustom.getUsid());
		back.setNote(backcustom.getNote());
		back.setDatemake(Tools.getDayTimes());
		this.update(back);
		
		syslog.setStlg("0197");
		syslog.setBrief("�޸ĺ������û���Ϣ��"+back.getUsid());
		syslog.setNote("�޸ĺ������û���Ϣ��"+back.getUsid()+",ʱ�䣺"+back.getDatemake());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Describe:�����û��Ƿ�����ں�������
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#checkBackcustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public boolean checkBackcustom(String usid) {
		String sql = " from Backcustom b where b.usid ='"+usid+"'";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Backcustom backcustom = (Backcustom) list.get(0);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Describe:�����û��Ƿ��������ע���û���
	 * @see com.ectrip.system.user.dao.idao.IBackCustomDAO#checkCustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public boolean checkCustom(String usid) {
		String sql = " from Custom c where c.usid ='"+usid+"'";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Custom custom = (Custom) list.get(0);
			return false;
		}else{
			return true;
		}
	}

}

