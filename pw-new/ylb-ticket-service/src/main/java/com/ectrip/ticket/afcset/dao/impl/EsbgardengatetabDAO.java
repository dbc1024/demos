package com.ectrip.ticket.afcset.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbgardengatetabDAO;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

/**
 * Created By Jzhenhua Time:2011-09-27 08:58
 *
 * @author lenovo
 * @version 1.0, 2011-09-26
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class EsbgardengatetabDAO extends GenericDao implements
        IEsbgardengatetabDAO {
	
	@Autowired
	private SysService sysService;

    public void addGardenGate(Esbgardengatetab esbgardenatetad,Syslog syslog) {
        this.save(esbgardenatetad);

        // 添加日志信息
//        syslog.setStlg("0107");
//        syslog.setBrief("园门信息：" + esbgardenatetad.getId().getIgardengateid());
//        syslog.setNote("添加园门信息：" + esbgardenatetad.getSzgardengatename());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    public void delGardenGate(Esbgardengatetab esbgardenatetad,Syslog syslog) {
        this.delete(esbgardenatetad);

        // 添加日志信息
//        syslog.setStlg("0109");
//        syslog.setBrief("园门信息：" + esbgardenatetad.getId().getIgardengateid());
//        syslog.setNote("删除园门信息：" + esbgardenatetad.getSzgardengatename());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    public void updateGardenGate(Esbgardengatetab esbgardengatetad,Syslog syslog) {
        this.update(esbgardengatetad);

        // 添加日志信息
//        syslog.setStlg("0108");
//        syslog.setBrief("园门信息：" + esbgardengatetad.getId().getIgardengateid());
//        syslog.setNote("修改园门信息：" + esbgardengatetad.getSzgardengatename());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    /**
     * Description:根据编号查询园门
     *
     * @param id
     * @return
     * @author Jzhenhua<br>
     *         date 2011-10-22
     */
    public Esbgardengatetab getGardenGateById(Long igardengateid) {
        Esbgardengatetab gardenGate = new Esbgardengatetab();
        String hsql = "from Esbgardengatetab gardengate,Esbscenicareatab provider where gardengate.id.iscenicid = provider.iscenicid and gardengate.id.igardengateid="+ igardengateid;
        List list = this.find(hsql);

        Object[] object = (Object[]) list.get(0);

        gardenGate = (Esbgardengatetab) object[0];
        Esbscenicareatab provider = (Esbscenicareatab) object[1];

        gardenGate.setStriscenicid(provider.getSzscenicname());

        return gardenGate;
    }

    /**
     * Description：查询所有园门信息
     *
     * @param esbgardengatetab
     * @param fws
     * @param pageSize
     * @param startIndex
     * @param url
     * @return 所有园门信息
     * @author jzhenhua Date 2011-10-14
     */
    public PaginationSupport getGaredenGatePage(Long providerId, Long isgardengateid, String fws, int pageSize, int startIndex, String url) {
        StringBuffer hsql = new StringBuffer(
                "select new map(e.id.igardengateid as igardengateid,e.id.iscenicid as iscenicid,e.isgardengateid as isgardengateid,e.szgardengatecode as szgardengatecode,e.szgardengatename as szgardengatename,e.bygardengateindex as bygardengateindex,e.szaddress as szaddress,e.szcontact as szcontact,e.szphone as szphone,e.byisuse as byisuse,e.byiscont as byiscont,e.szmemo as szmemo,s.szscenicname as striscenicid) FROM Esbgardengatetab e,Esbscenicareatab s WHERE e.id.iscenicid = s.iscenicid ");

        if(fws!=null&&!fws.equals("")){
            hsql.append(" AND e.id.iscenicid in ("+fws+") ");
        }

        // 如果有服务商编号传入说明是查询,添加查询条件
        if (null != providerId) {
            hsql.append(" AND e.id.iscenicid="+ providerId);
        }
        
        // 如果园门父级编号不为空或者不为0则添加查询条件(表明查询子园门)
        if(null!=isgardengateid && isgardengateid.longValue()!=0) {
        	hsql.append("and e.isgardengateid="+ isgardengateid);
        }else {
        	hsql.append(" and e.isgardengateid=0 ");
		}
        
       

        return this.findPage(hsql.toString(), pageSize, startIndex, url);
    }

    public Long getMaxId() throws Exception {
        String sql = "SELECT max(e.id.igardengateid) FROM Esbgardengatetab e";
        List list = this.getHibernateTemplate().find(sql);
        Long b = new Long(0);
        if (list.get(0) != null) {

            b = new Long(list.get(0).toString());
        }
        return b + 1;
    }

    public List getSzgardengatecode(String szgardengatecode) {
        String sql = "FROM Esbgardengatetab e WHERE e.szgardengatecode='"
                + szgardengatecode + "'";
        return this.find(sql);
    }

    public void addEsbscenicareatab(Esbscenicareatab e) {
        this.save(e);
    }
    /**
     * 得到园门列表
     * Describe:
     * @auth:huangyuqi
     * @param esfemployeetab
     * @return
     * return:List
     * Date:2012-1-10
     */
    public List getGradeList(Esfemployeetab esfemployeetab){
        List list =null;
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Esbgardengatetab s where s.byisuse=1 ");

        if(esfemployeetab!=null){
            if (esfemployeetab.getCompanytype().equals("02")) {//表示景区企业
                String scenics = esfemployeetab.getScenics();
                if (scenics == null || scenics.equals("")) {
                    return list;
                }
                hsql.append(" and s.id.iscenicid in (" + scenics + ")  ");
            }
        }
        list = this.find(hsql.toString());
        return list;
    }

    public List showProductList(String iscenicids){
        List list =null;
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Edmtickettypetab where byisuse=1 ");
        if(iscenicids!=null&&!iscenicids.equals("")){
            hsql.append(" and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01 and scenic.iscenicid in "+iscenicids+")");
        }
//		System.out.println(hsql.toString());
        list = this.find(hsql.toString());
        return list;
    }

    /**
     * 根据当前园门查询该服务商下的其它园门
     * @param iscenicid 服务商ID
     * @param gateid 园门ID
     * @return
     */
    public List queryOtherGarden(Long iscenicid,Long gateid){
        String hsql = "from Esbgardengatetab s where s.byisuse=1 and s.id.igardengateid <> "+gateid +
                " and s.id.iscenicid = "+iscenicid;
        return this.find(hsql);
    }

    /**
     *
     * Describe:除本园门外的其他所有园门
     * @author:chenxinhao
     * @param esfemployeetab
     * @param gateid
     * @return
     * return:List
     * Date:2013-4-23
     */
    public List getGradeList(Esfemployeetab esfemployeetab,Long gateid){
        List list =null;
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Esbgardengatetab s where s.byisuse=1 ");

        if(esfemployeetab!=null){
            if (esfemployeetab.getCompanytype().equals("02")) {//表示景区企业
                String scenics = esfemployeetab.getScenics();
                if (scenics == null || scenics.equals("")) {
                    return list;
                }
                hsql.append(" and s.id.iscenicid in (" + scenics + ")  ");
            }
        }
        if(gateid!=null&&gateid!=0){
            hsql.append(" and s.id.igardengateid !="+gateid);
        }
        list = this.find(hsql.toString());
        return list;
    }

    public List showGradeLinkList(Long igardengateid,Long ligardengateid,Long itickettypeid){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Gardengatelink lk where 1=1 ");
        if(igardengateid!=null&&igardengateid!=0L){
            hsql.append(" and lk.igardengateid="+igardengateid);
        }
        if(ligardengateid!=null&&ligardengateid!=0L){
            hsql.append(" and lk.ligardengateid="+ligardengateid);
        }
        if(itickettypeid != null){
            hsql.append(" and lk.inoteger1 = "+itickettypeid);
        }
        return this.find(hsql.toString());
    }

    public Esbgardengatetab showGate(Long igardengateid){
        List list = this.find(" from Esbgardengatetab where id.igardengateid="+igardengateid);
        if(list==null||list.size()<0){
            return null;
        }
        return (Esbgardengatetab) list.get(0);
    }

    public void addGradeLink(Gardengatelink link,Syslog syslog){
        Long maxid = this.getMaxPk("linkid", "Gardengatelink");
        link.setLinkid(maxid+1L);
        this.save(link);
    }

    public void updateGradeLink(Gardengatelink link,Syslog syslog){
        Gardengatelink gate = (Gardengatelink) this.get(Gardengatelink.class, link.getLinkid());
        if(gate!=null){
            gate.setIgardengateid(link.getIgardengateid());
            gate.setIscenicid(link.getIscenicid());
            gate.setLigardengateid(link.getLigardengateid());
            gate.setLiscenicid(link.getLiscenicid());
            gate.setLinktype(link.getLinktype());
            gate.setInoteger1(link.getInoteger1());
            gate.setIsvalid(link.getIsvalid());
            this.update(gate);
        }
    }

    public Map viewLink(Long linkid){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" select new map(lk.linkid as linkid,lk.igardengateid as igardengateid,g1.szgardengatename as szgardengatename,lk.iscenicid as iscenicid,c1.szscenicname as szscenicname,lk.ligardengateid as ligardengateid,g2.szgardengatename as lszgardengatename,lk.liscenicid as liscenicid,c2.szscenicname as lszscenicname,lk.linktype as linktype,sys.pmva as pmva,lk.isvalid as isvalid) from Gardengatelink lk,Esbgardengatetab g1,Esbgardengatetab g2,Sysparv5 sys,Esbscenicareatab c1,Esbscenicareatab c2 ");
        hsql.append(" where lk.igardengateid = g1.id.igardengateid and lk.ligardengateid = g2.id.igardengateid and lk.linktype = sys.id.pmcd and sys.id.pmky = 'GDLK' and lk.iscenicid = c1.iscenicid and lk.liscenicid = c2.iscenicid ");
        hsql.append(" and lk.linkid="+linkid);
        return (Map)this.find(hsql.toString()).get(0);
    }

    public void delLink(Long linkid,Syslog syslog){
        Gardengatelink link = (Gardengatelink) this.get(Gardengatelink.class, linkid);
        if(link!=null){
            this.delete(link);
        }
    }

    public PaginationSupport showLinkListByid(Long igardengateid, int pageSize,int startIndex, String url){
        StringBuffer hsql = new StringBuffer();
        
//        hsql.append(" select new map(lk.linkid as linkid,lk.igardengateid as igardengateid,g1.szgardengatename as szgardengatename,lk.iscenicid as iscenicid,c1.szscenicname as szscenicname,lk.ligardengateid as ligardengateid,g2.szgardengatename as lszgardengatename,lk.liscenicid as liscenicid,c2.szscenicname as lszscenicname,lk.linktype as linktype,sys.pmva as pmva,lk.isvalid as isvalid,lk.inoteger1 as ticketid) from Gardengatelink lk,Esbgardengatetab g1,Esbgardengatetab g2,Sysparv5 sys,Esbscenicareatab c1,Esbscenicareatab c2 ");
//        hsql.append(" where lk.igardengateid = g1.id.igardengateid and lk.ligardengateid = g2.id.igardengateid and lk.linktype = sys.id.pmcd and sys.id.pmky = 'GDLK' and lk.iscenicid = c1.iscenicid and lk.liscenicid = c2.iscenicid ");
        
//        hsql.append(" select new map("
//        		+ " lk.linkid as linkid, lk.igardengateid as igardengateid, lk.iscenicid as iscenicid,lk.ligardengateid as ligardengateid,lk.liscenicid as liscenicid,lk.linktype as linktype,lk.isvalid as isvalid,lk.inoteger1 as ticketid,"
//        		+ " g1.szgardengatename as szgardengatename,"
//        		+ " g2.szgardengatename as lszgardengatename,"
//        		+ " c1.szscenicname as szscenicname,"
//        		+ " c2.szscenicname as lszscenicname,"
//        		+ " sys.pmva as pmva"
//        		+ " )"
//        		+ " from Gardengatelink lk, Esbgardengatetab g1, Esbgardengatetab g2, Sysparv5 sys, Esbscenicareatab c1, Esbscenicareatab c2 "
//        		+ " where lk.igardengateid = g1.id.igardengateid and lk.ligardengateid = g2.id.igardengateid"
//        		+ " and lk.iscenicid = c1.iscenicid and lk.liscenicid = c2.iscenicid "
//        		+ " and lk.linktype = sys.id.pmcd and sys.id.pmky = 'GDLK' ");
        
        hsql.append(" select new map("
        		+ " lk.linkid as linkid, lk.igardengateid as igardengateid, lk.iscenicid as iscenicid,lk.ligardengateid as ligardengateid,lk.liscenicid as liscenicid,lk.linktype as linktype,lk.isvalid as isvalid,lk.inoteger1 as ticketid,"
        		+ " g1.szgardengatename as szgardengatename,"
        		+ " g2.szgardengatename as lszgardengatename,"
        		+ " c1.szscenicname as szscenicname,"
        		+ " c2.szscenicname as lszscenicname"
        		+ " )"
        		+ " from Gardengatelink lk, Esbgardengatetab g1, Esbgardengatetab g2, Esbscenicareatab c1, Esbscenicareatab c2 "
        		+ " where lk.igardengateid = g1.id.igardengateid and lk.ligardengateid = g2.id.igardengateid"
        		+ " and lk.iscenicid = c1.iscenicid and lk.liscenicid = c2.iscenicid "
        		);
        
        if(igardengateid!=null&&igardengateid!=0){
            hsql.append(" and lk.igardengateid="+igardengateid);
        }
        hsql.append(" order by lk.linkid ");
        
        
        PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startIndex, url);
        if(ps != null && ps.getItems().size()>0){
            List<Map> list = ps.getItems();
            
            StringBuilder linktypeS = new StringBuilder();
            for (int i=0;i<list.size();i++){
                Map map = (Map) list.get(i);
                Long ticketid = (Long)map.get("ticketid");
                if(ticketid != null && ticketid != 0L){
                    Edmtickettypetab ticket = (Edmtickettypetab) this.get(Edmtickettypetab.class,ticketid);
                    map.put("ticketname",ticket.getSztickettypename());
                }else{
                    map.put("ticketname","所有产品");
                }
                
                
                String linktype = map.get("linktype").toString();
                linktypeS.append(linktype + ",");
            }
            linktypeS.deleteCharAt(linktypeS.length() - 1);
            
            List<Map> sysparams_linktypeS= sysService.getSysparamsByPmkyAndPmcds("GDLK", linktypeS.toString());
            for (Map item : list) {
            	String linktype = item.get("linktype").toString();
            	for (Map sysparam : sysparams_linktypeS) {
            		String pmcd = sysparam.get("pmcd").toString();
            		if(linktype.equals(pmcd)) {
            			String pmva = sysparam.get("pmva").toString();
            			item.put("pmva", pmva);
            			
            			break;
            		}
				}
            	
			}
            
            
            
        }
        return ps;
    }

	@Override
	public List getGardenGateSelect(Long iscenicid) {
		StringBuilder hsql = new StringBuilder();
		
		 hsql.append(" select new map("
	        		+ " gate.id.igardengateid as igardengateid, gate.szgardengatename as szgardengatename"
	        		+ " )"
	        		+ " from Esbgardengatetab gate"
	        		+ " where gate.id.iscenicid="+ iscenicid
	        		);
		
		
        List list = this.find(hsql.toString());
       
        return list;
	}
}
