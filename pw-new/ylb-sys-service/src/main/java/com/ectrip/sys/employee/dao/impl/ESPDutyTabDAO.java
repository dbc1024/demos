package com.ectrip.sys.employee.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.employee.dao.IESPDutyTabDao;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.employee.Esppostsdutytab;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class ESPDutyTabDAO extends GenericDao implements IESPDutyTabDao {

	/**
	 * 删除职责 如果有下级 不让删除
	 */
	public void deleteduty(Espdutytab espduty, Syslog syslog) {
		super.delete(espduty);

		String sql = " from Esppostsdutytab where idutyid="
				+ espduty.getIdutyid();
		List lst = this.find(sql);
		if (lst != null && lst.size() > 0) {
			for (int i = 0; i < lst.size(); i++) {
				Esppostsdutytab es = (Esppostsdutytab) lst.get(i);
				this.delete(es);
			}
		}

		syslog.setStlg("0026");
		syslog.setBrief("职责：" + espduty.getIdutyid());
		syslog.setNote("删除职责：" + espduty.getSzdutyname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *根据父ID获取子列表 Describe:
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IESPDutyTabDao#findPage(java.lang.Long,
	 *      int, int, java.lang.String)
	 * @param idutyid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author yangguang Date:2011-9-17
	 */
	public PaginationSupport findPage(Long idutyid, String szdutyname,String dutype,
			int pageSize, int startIndex, String url) {
		StringBuffer hql = new StringBuffer();
		hql
				.append("select new map(estduty.idutyid as idutyid,estduty.irootid as irootid,estduty.iparentid as iparentid,estduty.ilevel as ilevel,estduty.ilevelsequence as ilevelsequence,estduty.bisleaf as bisleaf,estduty.szinnerid as szinnerid,estduty.szinnercode as szinnercode,estduty.szinnername as szinnername,estduty.dutype as dutype,estduty.szdutycode as szdutycode,estduty.szdutyname as szdutyname,estduty.bisshowimage as bisshowimage,estduty.szimagesrc as szimagesrc,estduty.szgotopage as szgotopage,estduty.iversion as iversion,estduty.bypurviewtype as bypurviewtype,estduty.bycategorytype as bycategorytype,estduty.byisuse as byisuse,estduty.szloadresource as szloadresource,estduty.szorderby as szorderby,sys.pmva as strdutype,sys1.pmva as strbypurviewtype,sys2.pmva as strbycategorytype) from Espdutytab estduty,Sysparv5 sys,Sysparv5 sys1,Sysparv5 sys2 where sys.id.pmky='DDTP' and sys.id.pmcd=estduty.dutype and sys1.id.pmky='ZZTP' and sys1.id.pmcd=estduty.bycategorytype and sys2.id.pmky='QXTP' and sys2.id.pmcd=estduty.bypurviewtype and estduty.iparentid="
						+ idutyid + "");
		if (szdutyname != null && !szdutyname.equals("")) {
			hql.append(" and estduty.szdutyname like '%" + szdutyname + "%'");
		}
		if (dutype != null && !dutype.equals("")) {
			if(!dutype.equals("00")){
				hql.append(" and estduty.dutype='"+dutype+"'");
			}
			
		}
		hql.append(" order by estduty.dutype,estduty.szorderby");
		System.out.println(hql);
		return super.findPage(hql.toString(), pageSize, startIndex, url);
	}

	/**
	 * 添加职责
	 */
	public void insertduty(Espdutytab duty, Espdutytab parentduty,
			String[] dutypes, Syslog syslog) {
		if (dutypes != null) {
			for (int x = 0; x < dutypes.length; x++) {
				Espdutytab espduty=new Espdutytab();
				espduty.setSzdutycode(duty.getSzdutycode());
				espduty.setSzdutyname(duty.getSzdutyname());
				espduty.setSzgotopage(duty.getSzgotopage());
				espduty.setBypurviewtype(duty.getBypurviewtype().toString());
				espduty.setBycategorytype(duty.getBycategorytype());
				espduty.setByisuse(duty.getByisuse());
				espduty.setSzorderby(duty.getSzorderby());
				espduty.setBisleaf(duty.getBisleaf());
				espduty.setSzmemo(duty.getSzmemo());
				espduty.setIversion(duty.getIversion());
				espduty.setIssellticket(duty.getIssellticket());
				espduty.setIparentid(duty.getIparentid());
				
				espduty.setDutype(dutypes[x]);
				
				
				//何安洲新加,如果新增职责管理时上传点击的图片，则在此处判断
				//如果不上传，则其图片id都是null，则赋值为1L
				//如果上传，则图片id就是上传时自动生成的id
				//2017-05-25
				if(duty.getPicidf() == null ||"".equals(duty.getPicidf())){
					espduty.setPicidf(1l);
				}else{
					espduty.setPicidf(duty.getPicidf());
				}
				
				
				if(duty.getPicids()==null || "".equals(duty.getPicids())){
					espduty.setPicids(1l);
				}else{
					espduty.setPicids(duty.getPicids());
				}
				
				
				

				// 如果 是第0层 也就是 父节点是虚拟出来的 那么 第一层的根节点就为父节点的编号
				if ((parentduty.getIrootid() == 0 && parentduty.getIlevel() == 0)
						|| (parentduty.getIrootid() == 0 && parentduty
								.getIlevel() == 0)) {
					espduty.setIrootid(parentduty.getIdutyid());
				} else {// 其他时候 所有子节点的根节点都等于父节点的根节点ID
					espduty.setIrootid(parentduty.getIrootid());
					if ((parentduty.getIrootid() == 0 && parentduty.getIlevel() == 0)) {
						espduty.setSzinnerid("");
						espduty.setSzinnername("");
						espduty.setSzinnercode("");
					} else {
						// 职责ID
						if (parentduty.getSzinnerid() != null
								&& !parentduty.getSzinnerid().equals("")) {
							espduty.setSzinnerid(parentduty.getSzinnerid()
									+ ","
									+ String.valueOf(parentduty.getIdutyid()));
						} else {
							espduty.setSzinnerid(String.valueOf(parentduty
									.getIdutyid()));
						}
						// 职责名称
						if (parentduty.getSzinnerid() != null
								&& !parentduty.getSzinnerid().equals("")) {
							espduty.setSzinnername(parentduty.getSzinnername()
									+ ","
									+ String.valueOf(parentduty
											.getSzinnername()));
						} else {
							espduty.setSzinnername(String.valueOf(parentduty
									.getSzdutyname()));
						}
						// 职责代码
						if (parentduty.getSzinnerid() != null
								&& !parentduty.getSzinnerid().equals("")) {
							espduty.setSzinnercode(parentduty.getSzinnercode()
									+ ","
									+ String.valueOf(parentduty
											.getSzinnercode()));
						} else {
							espduty.setSzinnercode(String.valueOf(parentduty
									.getSzdutycode()));
						}
					}
				}
				espduty.setIlevel(parentduty.getIlevel() + 1);

				// 层级序号
				Long iduty = getMaxPk("idutyid", "Espdutytab");
				espduty.setIlevelsequence(iduty + 1);
				espduty.setIlevel(espduty.getIlevel());
				// TODO 页面上增加选项
				espduty.setIdutyid(iduty + 1);
				// TODO 是否显示图标
				espduty.setBisshowimage("0");
				// 图标地址
				espduty.setSzimagesrc("0");
				// UI 加载项 TODO 不知道什么意思
				espduty.setSzloadresource("0");

				this.save(espduty);

				
			}
		}
//		syslog.setStlg("0024");
//		syslog.setBrief("职责：" + espduty.getIdutyid());
//		syslog.setNote("添加职责：" + espduty.getSzdutyname());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
	}

	/**
	 * 根据编号查看职责基本信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Espdutytab retrieve(Long idutyid) throws IllegalAccessException,
			InvocationTargetException {
		String hql = "select new map(estduty.idutyid as idutyid,estduty.irootid as irootid,estduty.iparentid as iparentid,estduty.ilevel as ilevel,estduty.ilevelsequence as ilevelsequence,estduty.bisleaf as bisleaf,estduty.szinnerid as szinnerid,estduty.szinnercode as szinnercode,estduty.szinnername as szinnername,estduty.dutype as dutype,estduty.szdutycode as szdutycode,estduty.iversion as iversion,estduty.szdutyname as szdutyname,estduty.bisshowimage as bisshowimage,estduty.szimagesrc as szimagesrc,estduty.szgotopage as szgotopage,estduty.bypurviewtype as bypurviewtype,estduty.bycategorytype as bycategorytype,estduty.byisuse as byisuse,estduty.szloadresource as szloadresource,estduty.szorderby as szorderby,estduty.issellticket as issellticket,estduty.szmemo as szmemo,sys.pmva as strdutype,sys1.pmva as strbypurviewtype,sys2.pmva as strbycategorytype) from Espdutytab estduty,Sysparv5 sys,Sysparv5 sys1,Sysparv5 sys2 where sys.id.pmky='DDTP' and sys.id.pmcd=estduty.dutype and sys1.id.pmky='ZZTP' and sys1.id.pmcd=estduty.bycategorytype and sys2.id.pmky='QXTP' and sys2.id.pmcd=estduty.bypurviewtype and estduty.idutyid="
				+ idutyid + "";
		List list = super.find(hql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Espdutytab duty = new Espdutytab();
			BeanUtils.populate(duty, (Map) list.get(0));
			return duty;
		}
	}

	/**
	 * 修改职责 如果有子节点 就不让修改
	 */
	public void updateduty(Espdutytab espduty, Syslog syslog) {
		Espdutytab dutytab = (Espdutytab) super.get(Espdutytab.class, espduty
				.getIdutyid());
		dutytab.setSzdutyname(espduty.getSzdutyname());
		dutytab.setSzdutycode(espduty.getSzdutycode());
		dutytab.setSzgotopage(espduty.getSzgotopage());
		dutytab.setBypurviewtype(espduty.getBypurviewtype().toString());
		dutytab.setBycategorytype(espduty.getBycategorytype());
//		dutytab.setDutype(espduty.getDutype());
		dutytab.setByisuse(espduty.getByisuse());
		dutytab.setSzorderby(espduty.getSzorderby());
		dutytab.setBisleaf(espduty.getBisleaf());
		dutytab.setSzmemo(espduty.getSzmemo());
		dutytab.setIversion(espduty.getIversion());
		dutytab.setIssellticket(espduty.getIssellticket());
		
		
		//何安洲新加 修改菜单图片时，同时修改图片id
		if(espduty.getPicidf() != null){
			dutytab.setPicidf(espduty.getPicidf());
		}else{
			dutytab.setPicidf(131L);
		}
		
		if(espduty.getPicids() != null){
			dutytab.setPicids(espduty.getPicids());
		}else{
			dutytab.setPicids(131L);
		}
		
		
		
		
		this.update(dutytab);

		syslog.setStlg("0025");
		syslog.setBrief("职责：" + dutytab.getIdutyid());
		syslog.setNote("修改职责：" + dutytab.getSzdutyname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * * Describe:判断是否含有子节点
	 * 
	 * @see com.ectrip.system.employee.dao.idao.IESPDutyTabDao#ishaveson(java.lang.Long)
	 * @param idutyid
	 * @return true 表示有子集 false表示无
	 * @author yangguang Date:2011-9-17
	 */
	public boolean ishaveson(Long idutyid) {
		String hql = "select count(idutyid) from Espdutytab espduty where espduty.iparentid="
				+ idutyid + "";
		List list = super.find(hql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 读取下级职责
	 */
	public List retrivesonduty(Long idutyid) {
		String hql = " from Espdutytab espduty where espduty.iparentid="
				+ idutyid + "";
		List list = super.find(hql);
		return list;

	}
	
	/**
	 * *
	 * Describe:查询出1-2层中的职责信息
	 * @see com.ectrip.system.employee.dao.idao.IESPDutyTabDao#queryListduty()
	 * @return
	 * @author lijingrui
	 * Date:2013-1-11
	 */
	public List queryListduty(){
		String hql = "select es.idutyid as idutyid,es.iparentid  as iparentid,es.szdutyname as szdutyname,es.ilevel as ilevel from Espdutytab es where es.byisuse = 1 and es.ilevel<3 start with es.idutyid in (select ed.idutyid from Espdutytab ed where ed.ilevel = 1 and ed.byisuse = 1) connect by prior es.idutyid = es.iparentid ";
		return this.getSessionFactory().getCurrentSession().createSQLQuery(hql).list();
	}

	/**
	 * *
	 * Describe:解析职责XML信息
	 * @see com.ectrip.system.employee.service.iservice.IESPDutyTabService#showelementList()
	 * @return
	 * @author lijingrui
	 * Date:2013-1-15
	 */
	public Map showelementList(){
		 SAXBuilder builder=new SAXBuilder();	//解析器
		 //文件路径需要从controller层传入
		 String path = null;//ServletActionContext.getServletContext().getRealPath("/WEB-INF/espduty.xml");
		 Map map=new HashMap();	
		 try {		
				Document docment=builder.build(path);//xml文本对象
				Element root=docment.getRootElement();	//根节点	
				List<Element> names=root.getChildren("row");//得到row节点列表
				int i=1;
				for(Element element:names){			
					int j=1;
					List lst=new ArrayList();
					if(element.getChildTextTrim("sopid").equals("0")){
						for(Element mentls:names){
							if(element.getChildTextTrim("opid").equals(mentls.getChildTextTrim("sopid"))&&!mentls.getChildTextTrim("sopid").equals("0")){
								lst.add(mentls);
							}
							j++;
						}
						
						map.put(element.getChildTextTrim("opid"), lst);
					}
					
					i++;				
				}
				
/*				Set<Map.Entry<String, List>> set = map.entrySet();
			    for (Iterator<Map.Entry<String, List>> it = set.iterator(); it.hasNext();) {
			        Map.Entry<String, List> entry = (Map.Entry<String, List>) it.next();
			        System.out.println(entry.getKey() + "--->" + entry.getValue());
			        List list=entry.getValue();
			        for(int x=0;x<list.size();x++){
			        	Element obj=(Element)list.get(x);
			        	System.out.println(obj.getChildTextTrim("opname"));
			        }
			    }*/
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return map;
	
	}
	
}
