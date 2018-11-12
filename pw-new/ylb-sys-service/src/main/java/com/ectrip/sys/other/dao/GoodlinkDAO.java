package com.ectrip.sys.other.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.other.Goodlink;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.other.dao.idao.IGoodlinkDAO;
import com.ectrip.upload.model.Upfile;
import org.springframework.stereotype.Repository;

/**
 * <li>Description:��������DAO</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-07</li>
 */
@Repository
public class GoodlinkDAO extends GenericDao implements IGoodlinkDAO {

	/**
	 * <li>Description:������������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@param goodlink ��������</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void addGoodlink(Goodlink goodlink, Syslog syslog) throws Exception {
		if(goodlink.getPicid()==null||goodlink.getPicid().equals("")){
			goodlink.setPicid(new Long(0));
		}
		this.save(goodlink);

		// �����־
		syslog.setStlg("0141");
		syslog.setBrief("�������ӣ�" + goodlink.getSeq());
		syslog.setNote("����������ӣ�" + goodlink.getCorpname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:�޸���������</li><br>
	 * <li>@param goodlink �޸�����</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void updateGoodlink(Goodlink goodlink, Syslog syslog)
			throws Exception {
		if(goodlink.getPicid()==null||goodlink.getPicid().equals("")){
			goodlink.setPicid(new Long(0));
		}
		this.update(goodlink);

		// �����־
		syslog.setStlg("0142");
		syslog.setBrief("�������ӣ�" + goodlink.getSeq());
		syslog.setNote("�޸��������ӣ�" + goodlink.getCorpname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:ɾ����������</li><br>
	 * <li>@param goodlink ɾ������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void deleteGoodlink(Goodlink goodlink, Syslog syslog)
			throws Exception {
		this.delete(goodlink);

		// �����־
		syslog.setStlg("0143");
		syslog.setBrief("�������ӣ�" + goodlink.getSeq());
		syslog.setNote("ɾ���������ӣ�" + goodlink.getCorpname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * <li>Description:���ݱ�Ż�ȡ������������</li><br>
	 * <li>@param goodlink ɾ������</li><br>
	 * <li>@return com.ectrip.model.other.Goodlink</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	@SuppressWarnings("unchecked")
	public Goodlink getGoodlinkById(Long id) throws Exception {
//		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note,sys.pmva as strptype,CONCAT(up.upadder,up.upfilename) as picurl) from Goodlink link,Sysparv5 sys,Upfile up where link.picid=up.upid and link.ptype=sys.id.pmcd and sys.id.pmky='WZTP' and link.seq="
//				+ id;
//		Goodlink goodlink = new Goodlink();
//		BeanUtils.populate(goodlink, (Map) this.find(sql).get(0));
		//���º��޸�		2012-09-03
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note,link.picid as picid,sys.pmva as strptype) from Goodlink link,Sysparv5 sys where link.ptype=sys.id.pmcd and sys.id.pmky='WZTP' and link.seq="
			+ id;
		List list = this.find(sql);
		Goodlink goodlink = new Goodlink();
		if(list!=null&&list.size()>0){
			Map map = (Map) list.get(0);
			Long picid = (Long)map.get("picid");
			Upfile upfile=(Upfile) get(Upfile.class, Long.parseLong(map.get("picid").toString()));
			if(upfile!=null){
			    map.put("picurl", upfile.getUpadder()+upfile.getUpfilename());
			}
			BeanUtils.populate(goodlink, map);
		}
		return goodlink;
	}

	/**
	 * <li>Description:��ȡ���������б�</li><br>
	 * <li>@param pageSize ҳ��С</li><br>
	 * <li>@param page ��ǰҳ</li><br>
	 * <li>@param String</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@return com.ectrip.base.PaginationSupport</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public PaginationSupport getGoodlinkListView(int pageSize, int page,
			String url) throws Exception {
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note,sys.pmva as strptype) from Goodlink link,Sysparv5 sys where link.ptype=sys.id.pmcd and sys.id.pmky='WZTP' order by link.orderid ";
		PaginationSupport ps = this.findPage(sql, pageSize, page, url);
		if(ps.getItems()!=null&&ps.getItems().size()>0){
		    for(int i=0;i<ps.getItems().size();i++){
		    	Map map=(Map) ps.getItems().get(i);
				if(map.get("picid")!=null){
					Upfile upfile=(Upfile) get(Upfile.class, Long.parseLong(map.get("picid").toString()));
					if(upfile!=null){
					    map.put("picurl", upfile.getUpadder()+upfile.getUpfilename());
					}
				}
		    }
		}
		return ps;

	}

	/**
	 * *
	 * Describe:ǰ̨��ʾ���е�����������ַ������վLOGO�ģ�
	 * @see com.ectrip.system.other.service.iservice.IGoodlinkService#getLinkmoreviewlist()
	 * @return
	 * @author lijingrui
	 * Date:Nov 17, 2011
	 */
	public List getLinkmoreviewlist() {
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note,CONCAT(up.upadder,up.upfilename) as picurl) from Goodlink link,Upfile up where link.picid=up.upid and link.isvalid=1 and link.picid>0 order by link.orderid ";
		List lst=this.find(sql);
		return lst;
	}
	
	public List getLinkmoreviewlist(int top) {
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note,CONCAT(up.upadder,up.upfilename) as picurl) from Goodlink link,Upfile up where link.picid=up.upid and link.isvalid=1 and link.picid>0 order by link.orderid ";
		List lst=this.findTopNumb(sql,top);
		return lst;
	}
	
	/**
	 * *
	 * Describe:ǰ̨��ʾ���е�����������ַ(û����վLOGO��)
	 * @see com.ectrip.system.other.dao.idao.IGoodlinkDAO#getupfileviewlink()
	 * @return
	 * @author lijingrui
	 * Date:Dec 1, 2011
	 */
	public List getupfileviewlink(){
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note) from Goodlink link where link.isvalid=1 and link.picid=0 order by link.orderid ";
		List lst=this.find(sql);
		return lst;
	}
	
	public List getupfileviewlink(int top){
		String sql = "select new map(link.seq as seq,link.corpname as corpname,link.linkname as linkname,link.picid as picid,link.orderid as orderid,link.ptype as ptype,link.isvalid as isvalid,link.note as note) from Goodlink link where link.isvalid=1 and link.picid=0 order by link.orderid ";
		List lst=this.findTopNumb(sql,top);
		return lst;
	}
}
