package com.ectrip.ec.comment.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.comment.dao.idao.ICommentDAO;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

public class CommentDAO extends GenericDao implements ICommentDAO {
	/**
	 * ��ȡ�����б� 
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 */
	public PaginationSupport queryCommentList(String type,int page,int pageSize,String url){
		PaginationSupport ps =null;
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		
		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.effect as effect,h.createdate as createdate,h.remarknum as remarknum,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype,c.cmzhtopic as type,a.amtopicf as title ) from Hscomment h,Sysparv5 sys,Articlemanagertab a,Columnmanagertab c where h.ptype = sys.id.pmcd and a.amid=h.oeid and a.cmid = c.cmid and sys.id.pmky='PLTP' ");
		
		if("00".equals(type)){//δ���
			hsql.append(" and h.status ='00' ");
		}else if("01".equals(type)){//���ͨ��
			hsql.append(" and h.status ='01' ");
		}else if("02".equals(type)){//��� ��ͨ��
			hsql.append(" and h.status ='02' ");
		}else{
			hsql.append(" and h.status in ('01','02') ");
		}
		hsql.append(" order by h.seq desc ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {				
				map = (Map)list.get(i);
				
				if(map.get("shusid")!=null && !"".equals(map.get("shusid"))){//�����
					Long iemployeeid = Long.parseLong(map.get("shusid").toString());
					Esfemployeetab emp = (Esfemployeetab)this.get(Esfemployeetab.class, iemployeeid);
					if(emp!=null){
						map.put("empid", emp.getEmpid());
						map.put("szemployeename", emp.getSzemployeename());
					}
				}
				if(map.get("note")!=null && !"".equals(map.get("note"))){//��������
					String notes = map.get("note").toString();						
					String noHTMLString = notes.replaceAll(
							"\\<script.*?script\\>", "");
					notes = noHTMLString.replaceAll("\\<.*?\\>", "")
							.replaceAll("&nbsp;", "").replaceAll(" ", "")
							.replaceAll("��", "");
					if (notes.length() > 20) {
						notes = notes.substring(0, 20) + "...";
					}
					map.put("strnote", notes);
				}
				if(map.get("ctitle")!=null && !"".equals(map.get("ctitle"))){//���۱���
					String title = map.get("ctitle").toString();						
					String noHTMLString = title.replaceAll(
							"\\<script.*?script\\>", "");
					title = noHTMLString.replaceAll("\\<.*?\\>", "")
							.replaceAll("&nbsp;", "").replaceAll(" ", "")
							.replaceAll("��", "");
					if (title.length() > 20) {
						title = title.substring(0, 20) + "...";
					}
					map.put("strctitle", title);
				}
				 if(map.get("effect")!=null && !"".equals(map.get("effect"))){
				    	String[] effects = map.get("effect").toString().split("��");
				    	Map comlist = new HashMap();
				    	for (int j = 0; j < effects.length; j++) {
							String htype = effects[j];
							Sysparv5Id sysid = new Sysparv5Id();
							sysid.setPmcd(htype);
							sysid.setPmky("COMY");
							Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
							if(sys!=null){
								comlist.put(sys.getPmva(),sys.getPmva());
							}
						}
				    	map.put("effectList", comlist);
				    }
			 if(map.get("usid")!=null && !"".equals(map.get("usid"))){
			    	String usid = map.get("usid").toString();
			    	int isuse = usid.indexOf(".");
			    	if(isuse>0){
			    		map.put("usid", "****");
			    	}
			 }
				
			}
		}
		return ps;
	}
	/**
	 * �������۱�Ų�ѯ���۶���
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Hscomment
	 * Date:2011-12-13
	 */
	public Hscomment getHsCommentBySeq(Long seq){
		Hscomment comment = new Hscomment();
		comment = (Hscomment)this.get(Hscomment.class, seq);
		if(comment!=null){
			
			if(comment.getShusid()!=null && !"".equals(comment.getShusid())){//�����
				Long iemployeeid = comment.getShusid();
				Esfemployeetab emp = (Esfemployeetab)this.get(Esfemployeetab.class, iemployeeid);
				if(emp!=null){
					comment.setEmpid(emp.getEmpid());
					comment.setSzemployeename(emp.getSzemployeename());
				}
			}
			
			if(comment.getNote()!=null && !"".equals(comment.getNote())){//��������
				String notes = comment.getNote();						
				String noHTMLString = notes.replaceAll(
						"\\<script.*?script\\>", "");
				notes = noHTMLString.replaceAll("\\<.*?\\>", "")
						.replaceAll("&nbsp;", "").replaceAll(" ", "")
						.replaceAll("��", "");
				if (notes.length() > 20) {
					notes = notes.substring(0, 20) + "...";
				}
				comment.setStrnote(notes);
			}
			if(comment.getCtitle()!=null && !"".equals(comment.getCtitle())){//���۱���
				String title = comment.getCtitle();					
				String noHTMLString = title.replaceAll(
						"\\<script.*?script\\>", "");
				title = noHTMLString.replaceAll("\\<.*?\\>", "")
						.replaceAll("&nbsp;", "").replaceAll(" ", "")
						.replaceAll("��", "");
				if (title.length() > 20) {
					title = title.substring(0, 20) + "...";
				}
				comment.setStrctitle(title);
			}
			
			if(comment.getPtype()!=null && !"".equals(comment.getPtype())){//��������
				Sysparv5Id sysid = new Sysparv5Id();
				sysid.setPmcd(comment.getPtype());
				sysid.setPmky("PLTP");
				Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, sysid);
				if(sysparv5!=null){
					comment.setStrptype(sysparv5.getPmva());
				}
			}
			
			 if(comment.getEffect()!=null && !"".equals(comment.getEffect())){
			    	String[] effects =comment.getEffect().split("��");
			    	Map comlist = new HashMap();
			    	for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmcd(htype);
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
			    	comment.setEffectList(comlist);
			    	
			 }
				
		    if(comment.getUsid()!=null && !"".equals(comment.getUsid())){
		    	String usid =comment.getUsid();
		    	int isuse = usid.indexOf(".");
		    	if(isuse>0){
		    		comment.setUsid("****");
		    	}
		    }
		}
		return comment;
	}
	/**
	 * ��̨�������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment���۶���
	 * @param syslog��־
	 * return:void
	 * Date:2011-12-13
	 */
	public void saveHscomment(Hscomment hscomment,Syslog syslog){
		
		this.update(hscomment);
		
		String strptype="";
		if(hscomment.getPtype()!=null && !"".equals(hscomment.getPtype())){//��������
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd(hscomment.getPtype());
			sysid.setPmky("PLTP");
			Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, sysid);
			if(sysparv5 !=null){
				strptype = sysparv5.getPmva();
			}
		}
		String strstatus="";
		if("01".equals(hscomment.getStatus())){
			strstatus="���ͨ��";
			String hsql = "select new map(sum(h.seq) as numb,sum(h.remarknum) as point) from Hscomment h where h.ptype='"+hscomment.getPtype()+"' and h.oeid="+hscomment.getOeid()+" and h.status='01' ";
			List list = this.find(hsql);
//			if(list!=null&&!list.isEmpty()){
//				Map map = (Map) list.get(0);
//				Long numb = 0L;
//				Double point = 0D;
//				if(map.get("numb")!=null||!"".equals(map.get("numb"))){
//					numb = Long.parseLong(map.get("numb").toString());
//				}
//				if(map.get("point")!=null||!"".equals(map.get("point"))){
//					point = Double.parseDouble(map.get("point").toString());
//				}
//				String result = String.format("%.2f", point/numb);
//				Esbscenicareatab es = (Esbscenicareatab) this.get(Esbscenicareatab.class, hscomment.getOeid());
//				Double dtest = Double.parseDouble(result);
//				es.setCommentpoint(dtest);
//				this.update(es);
//			}
		}else if("02".equals(hscomment.getStatus())){
			strstatus="��˲�ͨ��";
		}
		
		
		
		syslog.setStlg("0178");
		syslog.setBrief("������ۣ�"+hscomment.getSeq()  );
		syslog.setNote("������ۣ����"+hscomment.getSeq()+"���������ͣ�"+strptype+",���۶�����"+hscomment.getOeid()+"��� �˱��: "+hscomment.getShusid()+",���ʱ�䣺"+hscomment.getShdate()+",���״̬��"+strstatus);
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	/**
	 * ɾ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq���۱��
	 * @param syslog������־
	 * return:void
	 * Date:2011-12-13
	 */
	public void deleteHscomment(Long seq,Syslog syslog){
		
		Hscomment hscomment = (Hscomment)this.get(Hscomment.class, seq);
		String strptype="";
		if(hscomment.getPtype()!=null && !"".equals(hscomment.getPtype())){//��������
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd(hscomment.getPtype());
			sysid.setPmky("PLTP");
			Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, sysid);
			if(sysparv5 !=null){
				strptype = sysparv5.getPmva();
			}
		}
		
		syslog.setStlg("0179");
		syslog.setBrief("ɾ�����ۣ�"+hscomment.getSeq()  );
		syslog.setNote("ɾ�����ۣ����"+hscomment.getSeq()+"���������ͣ�"+strptype+",���۶�����"+hscomment.getOeid()+"�����۱��⣺"+hscomment.getCtitle());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(hscomment);
	}
	/**
	 * ǰ̨��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment
	 * return:void
	 * Date:2011-12-13
	 */
	public void SaveHscommentByPage(Hscomment hscomment){
		
		//�ж��Ƿ�Ϊ�����޸�Ϊ0�Ķ���
		/*String orid = hscomment.getOrid();
		MOrder mOrder = (MOrder) this.get(MOrder.class, orid);
		if (mOrder.getZfmont().intValue()==0 || mOrder.getMont().intValue()==0) {
			throw new RuntimeException("�˶������޷����ۡ�");
		}*/
		
		Long seq = this.getMaxPk("seq", new String[]{}, new Long[]{}, new String[]{}, new String[]{}, "Hscomment");
		hscomment.setSeq(seq+1);
		hscomment.setStatus("00");//δ���
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		hscomment.setCreatedate(sdf.format(new Date()));//����ʱ��
		
		//��������
		this.save(hscomment);
		
	}
	/**
	 * ǰ̨��ʾ�б�(�з�ҳ)
	 * Describe:
	 * @auth:huangyuqi
	 * @param oeid //���۶�����
	 * @param ptype //�������ͣ�01�����̣�02��Ʒ��03���£�04������
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 */
	public PaginationSupport queryHscommentByPage(Long oeid,String ptype,int page,int pageSize,String url){
		PaginationSupport ps = null;
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		
		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.createdate as createdate,h.remarknum as remarknum,h.fenjingnum as fenjingnum,h.xinjianum as xinjianum,h.effect as effect ,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype ) from Hscomment h,Sysparv5 sys where h.ptype = sys.id.pmcd and sys.id.pmky='PLTP' ");
		if(oeid!=null && !"".equals(oeid) && 0L!=oeid){
			hsql.append(" and h.oeid="+oeid);
		}
		if(ptype!=null && !"".equals(ptype)){
			hsql.append(" and h.ptype ='"+ptype+"' ");
		}
		hsql.append(" and h.status ='01' ");
		hsql.append(" order by h.seq desc ");
		
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {				
				map = (Map)list.get(i);
				if(map.get("effect")!=null && !"".equals(map.get("effect"))){
			    	String[] effects = map.get("effect").toString().split("��");
			    	Map comlist = new HashMap();
			    	for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmcd(htype);
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
			    	map.put("effectList", comlist);
			    }
				    if(map.get("usid")!=null && !"".equals(map.get("usid"))){
				    	String usid = map.get("usid").toString();
				    	int isuse = usid.indexOf(".");
				    	if(isuse>0){
				    		map.put("usid", "****");
				    	}
				    }
				
			}
		}
		return ps;
	}
	
	/**
	 * ǰ̨��ʾ�б�(�޷�ҳ)
	 * Describe:
	 * @auth:huangyuqi
	 * @param oeid //���۶�����
	 * @param ptype //�������ͣ�01�����̣�02��Ʒ��03���£�04������
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public List queryHscomment(Long oeid,String ptype) throws IllegalAccessException, InvocationTargetException{
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		
		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.createdate as createdate,h.remarknum as remarknum,h.fenjingnum as fenjingnum,h.xinjianum as xinjianum,h.effect as effect,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype) from Hscomment h,Sysparv5 sys where h.ptype = sys.id.pmcd and sys.id.pmky='PLTP' ");
		if(oeid!=null && !"".equals(oeid) && 0L!=oeid){
			hsql.append(" and h.oeid="+oeid);
		}
		if(ptype!=null && !"".equals(ptype)){
			hsql.append(" and h.ptype ='"+ptype+"' ");
		}
		hsql.append(" and h.status ='01' ");
		hsql.append(" order by h.seq desc ");
		
		List list =this.findTopNumb(hsql.toString(),5);		
		
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {	
			    map = (Map)list.get(i);
			    if(map.get("effect")!=null && !"".equals(map.get("effect"))){
			    	String[] effects = map.get("effect").toString().split(",");
			    	Map comlist = new HashMap();
			    	Sysparv5Id sysid;
			    	for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						sysid = new Sysparv5Id();
						sysid.setPmcd(htype.trim());
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class,sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
			    	map.put("effectList", comlist);
			    }
				
			    if(map.get("usid")!=null && !"".equals(map.get("usid"))){
			    	String usid = map.get("usid").toString();
			    	int isuse = usid.indexOf(".");
			    	if(isuse>0){
			    		map.put("usid", "****");
			    	}
			    }
			}
		}
		return list;
	}
	
	public boolean findComment(String usid,String orid,Long iscenicid,String ptype){
		List list = this.find("from Hscomment s  where s.usid ='"+usid+"' and s.orid = '"+orid+"' and s.oeid ='"+iscenicid+"' and s.ptype = '"+ptype+"'");
		if(list!=null&&!list.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	public List findComments(String usid,Long oeid,String ptype,String orid){
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		
		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.createdate as createdate,h.remarknum as remarknum,h.fenjingnum as fenjingnum,h.xinjianum as xinjianum,h.effect as effect ,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype,h.notea as notea) from Hscomment h,Sysparv5 sys where h.ptype = sys.id.pmcd and sys.id.pmky='PLTP' ");
		if(oeid!=null && !"".equals(oeid) && 0L!=oeid){
			hsql.append(" and h.oeid="+oeid);
		}
		if(ptype!=null && !"".equals(ptype)){
			hsql.append(" and h.ptype ='"+ptype+"' ");
		}
		if(orid!=null&&!orid.equals("")){
			hsql.append(" and h.orid='"+orid+"' ");
		}
		if(usid!=null&&!usid.equals("")){
			hsql.append("and (h.status ='01' or (h.status ='00' and h.usid='"+usid+"')) ");
		}else{
			hsql.append(" and h.status ='01' ");
		}

		hsql.append(" order by h.seq desc ");
		System.out.println(">>>>:"+hsql.toString());
		List list = this.find(hsql.toString());
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {				
				map = (Map)list.get(i);
				if(map.get("effect")!=null && !"".equals(map.get("effect"))){
			    	String[] effects = map.get("effect").toString().split("��");
			    	Map comlist = new HashMap();
			    	for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmcd(htype);
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
			    	map.put("effectList", comlist);
			    }
				    if(map.get("notea")!=null && !"".equals(map.get("notea"))){
				    	String username = map.get("notea").toString();
				    	int isuse = username.indexOf(".");
				    	if(isuse>0){
				    		map.put("notea", "****");
				    	}
				    }
				
			}
		}
		return list;
	}
	public PaginationSupport findCommentsByPage(String usid,Integer pageSize,Integer page,Long oeid,String ptype,String orid,String url){
		PaginationSupport ps = null;
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		
		hsql.append(" select new map(h.seq as seq,h.oeid as oeid,h.ptype as ptype,h.usid as usid,h.orid as orid,h.ctitle as ctile,h.note as note,h.createdate as createdate,h.remarknum as remarknum,h.fenjingnum as fenjingnum,h.xinjianum as xinjianum,h.effect as effect ,h.ip as ip,h.status as status,h.shusid as shusid,h.shdate as shdate,sys.pmva as strptype,h.notea as notea) from Hscomment h,Sysparv5 sys,Custom c where h.ptype = sys.id.pmcd and h.usid=c.usid and c.status = '01' and sys.id.pmky='PLTP' ");
		if(oeid!=null && !"".equals(oeid) && 0L!=oeid){
			hsql.append(" and h.oeid="+oeid);
		}
		if(ptype!=null && !"".equals(ptype)){
			hsql.append(" and h.ptype ='"+ptype+"' ");
		}
		if(orid!=null&&!orid.equals("")){
			hsql.append(" and h.orid='"+orid+"' ");
		}
		if(usid!=null&&!usid.equals("")){
			hsql.append("and (h.status ='01' or (h.status ='00' and h.usid='"+usid+"')) ");
		}else{
			hsql.append(" and h.status ='01' ");
		}
		
		hsql.append(" order by h.seq desc ");
		System.out.println(">>>>:"+hsql.toString());
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {				
				map = (Map)list.get(i);
				if(map.get("effect")!=null && !"".equals(map.get("effect"))){
					String[] effects = map.get("effect").toString().split("��");
					Map comlist = new HashMap();
					for (int j = 0; j < effects.length; j++) {
						String htype = effects[j];
						Sysparv5Id sysid = new Sysparv5Id();
						sysid.setPmcd(htype);
						sysid.setPmky("COMY");
						Sysparv5 sys = (Sysparv5)this.get(Sysparv5.class, sysid);
						if(sys!=null){
							comlist.put(sys.getPmva(),sys.getPmva());
						}
					}
					map.put("effectList", comlist);
				}
				if(map.get("notea")!=null && !"".equals(map.get("notea"))){
					String username = map.get("notea").toString();
					int isuse = username.indexOf(".");
					if(isuse>0){
						map.put("notea", "****");
					}
				}
				
			}
		}
		return ps;
	}
	public List getTypeAndTitleList(Long oeid) {
		//��ѯ���
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(a.amtopicf as title,c.cmzhtopic as type) from Articlemanagertab a , Columnmanagertab c where a.amid = '"+oeid+"' and a.cmid = c.cmid");
		List list = this.find(hsql.toString());
		return list;
	}
}

