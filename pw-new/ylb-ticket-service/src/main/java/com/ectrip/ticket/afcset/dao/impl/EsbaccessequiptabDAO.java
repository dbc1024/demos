package com.ectrip.ticket.afcset.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.SBLX;
import com.ectrip.base.enums.SBZT;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Esbequiptypetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbaccessequiptabDAO;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.EsbaccessequiptabId;
import com.ectrip.ticket.model.afcset.Esbaccessstatustab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;

/**
 * Created By Jzhenhua,Time 2011-10-04 09:57
 *
 * @author lenovo
 *
 */

@Repository
public class EsbaccessequiptabDAO extends GenericDao implements
        IEsbaccessequiptabDAO {

	@Autowired
	private SysService sysService;
	
	
    /**
     * 添加准进设备
     */
    public void addAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
            throws Exception {
        esbaccessequip.setSzgateway("255.255.255.0");
        esbaccessequip.setIversion(new Long(57));
        this.save(esbaccessequip);

        // 添加日志信息
//        syslog.setStlg("0111");
//        syslog.setBrief("准进设备操作：" + esbaccessequip.getId().getIaccessequipid());
//        syslog.setNote("新增准进设备信息：" + esbaccessequip.getSzaccessequipname());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    /**
     * 删除准进设备
     */
    public void delAccessequip(Long iaccessequipid,Syslog syslog)
            throws Exception {
//        String sql=" from Esbaccessequiptab where id.iaccessequipid="+esbaccessequip.getIaccessequipid()+" and id.iscenicid="+esbaccessequip.getIscenicid()+" and id.igardengateid="+esbaccessequip.getIgardengateid();
        
    	String sql=" from Esbaccessequiptab where id.iaccessequipid="+ iaccessequipid;
        
        Esbaccessequiptab es=(Esbaccessequiptab) this.find(sql).get(0);
        this.delete(es);

        // 添加日志信息
//        syslog.setStlg("0113");
//        syslog.setBrief("准进设备操作：" + esbaccessequip.getIaccessequipid());
//        syslog.setNote("删除准进设备信息：" + esbaccessequip.getSzaccessequipname());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    /**
     * 获取所有准进设备
     *
     * @param Esbccessequiptab
     *            查询信息
     * @param fws
     *            登录用户所管理服务商
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @author Jzhenhua,2011-10-14
     */
    public PaginationSupport getAccessequip(Long iscenicid, Long igardengateid, String scenics, int pageSize, int startIndex, String url) throws Exception {
        StringBuffer hsql = new StringBuffer();
//        hsql.append("select new map("
//        		+ " accessequip.id.iaccessequipid as iaccessequipid, accessequip.id.iscenicid as iscenicid, accessequip.id.igardengateid as igardengateid, accessequip.iequiptypeid as iequiptypeid,"
//        		+ " accessequip.szaccessequipcode as szaccessequipcode, accessequip.szaccessequipname as szaccessequipname, accessequip.szfactorycode as szfactorycode, accessequip.szipaddress as szipaddress,"
//        		+ " accessequip.szserverip as szserverip, accessequip.sznetmask as sznetmask, accessequip.szgateway as szgateway, accessequip.szdnsip as szdnsip,"
//        		+ " accessequip.byisuse as byisuse, accessequip.szmemo as szmemo, accessequip.isa as isa, accessequip.iversion as iversion,"
//        		+ " sys1.pmva as bycoretype, sys2.pmva as byctrldir, sys3.pmva as byworkmode, sys4.pmva as bynetworkmode,"
//        		+ " gardengate.szgardengatename as strigardengateid, "
//        		+ " scenicarea.szscenicname as striscenicid"
//        		+ " )"
//        		+ " from Esbaccessequiptab accessequip, Esbgardengatetab gardengate, Esbscenicareatab scenicarea, Sysparv5 sys1, Sysparv5 sys2, Sysparv5 sys3, Sysparv5 sys4 "
//        		+ " WHERE accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid "
//        		+ " and accessequip.bycoretype=sys1.id.pmcd and sys1.id.pmky='JSLX' "
//        		+ " and accessequip.byctrldir=sys2.id.pmcd and sys2.id.pmky='KZFX'  "
//        		+ " and accessequip.byworkmode=sys3.id.pmcd and sys3.id.pmky='GZMS' "
//        		+ " and sys4.id.pmcd=accessequip.bynetworkmode and sys4.id.pmky='WLFS' "
//        		);
        
	      hsql.append("select new map("
			+ " accessequip.id.iaccessequipid as iaccessequipid, accessequip.id.iscenicid as iscenicid, accessequip.id.igardengateid as igardengateid, accessequip.iequiptypeid as iequiptypeid,"
			+ " accessequip.szaccessequipcode as szaccessequipcode, accessequip.szaccessequipname as szaccessequipname, accessequip.szfactorycode as szfactorycode, accessequip.szipaddress as szipaddress,"
			+ " accessequip.szserverip as szserverip, accessequip.sznetmask as sznetmask, accessequip.szgateway as szgateway, accessequip.szdnsip as szdnsip,"
			+ " accessequip.byisuse as byisuse, accessequip.szmemo as szmemo, accessequip.isa as isa, accessequip.iversion as iversion,"
			
			+ " accessequip.bycoretype as bycoretype, accessequip.byctrldir as byctrldir, accessequip.byworkmode as byworkmode, accessequip.bynetworkmode as bynetworkmode,"
			
			+ " gardengate.szgardengatename as strigardengateid, "
			+ " scenicarea.szscenicname as striscenicid"
			+ " )"
			+ " from Esbaccessequiptab accessequip, Esbgardengatetab gardengate, Esbscenicareatab scenicarea"
			+ " WHERE accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid "
			);
        
        
        if(scenics!=null&&!scenics.equals("")){
            hsql.append(" AND accessequip.id.iscenicid in ("+scenics+")");
        }

        // 判断是否选择服务商查询
        if (null != iscenicid) {
            hsql.append(" AND accessequip.id.iscenicid="+ iscenicid);
        }

        if (null != igardengateid) {
            hsql.append(" AND accessequip.id.igardengateid="+ igardengateid);
        }
        
        PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startIndex, url);
        List<Map> items = ps.getItems();
        
        if(items!=null && items.size()>0) {
        	StringBuilder bycoretypeS = new StringBuilder();
        	StringBuilder byctrldirS = new StringBuilder();
        	StringBuilder byworkmodeS = new StringBuilder();
        	StringBuilder bynetworkmodeS = new StringBuilder();
        	
        	for (Map item : items) {
        		String bycoretype = item.get("bycoretype").toString();
        		bycoretypeS.append(bycoretype+ ",");
        		
        		String byctrldir = item.get("byctrldir").toString();
        		byctrldirS.append(byctrldir+ ",");
        		
        		String byworkmode = item.get("byworkmode").toString();
        		byworkmodeS.append(byworkmode+ ",");
        		
        		String bynetworkmode = item.get("bynetworkmode").toString();
        		bynetworkmodeS.append(bynetworkmode+ ",");
			}
        	bycoretypeS.deleteCharAt(bycoretypeS.length() - 1);
        	byctrldirS.deleteCharAt(byctrldirS.length() - 1);
        	byworkmodeS.deleteCharAt(byworkmodeS.length() - 1);
        	bynetworkmodeS.deleteCharAt(bynetworkmodeS.length() - 1);
        	
        	List<Map> bycoretype_sysparams = sysService.getSysparamsByPmkyAndPmcds("JSLX", bycoretypeS.toString());
        	List<Map> byctrldir_sysparams = sysService.getSysparamsByPmkyAndPmcds("KZFX", byctrldirS.toString());
        	List<Map> byworkmode_sysparams = sysService.getSysparamsByPmkyAndPmcds("GZMS", byworkmodeS.toString());
        	List<Map> bynetworkmode_sysparams = sysService.getSysparamsByPmkyAndPmcds("WLFS", bynetworkmodeS.toString());
        	
        	for (Map item : items) {
        		String bycoretype = item.get("bycoretype").toString();
        		for (Map sysparam : bycoretype_sysparams) {
        			String pmcd = sysparam.get("pmcd").toString();
        			if(bycoretype.equals(pmcd)) {
        				String pmva = sysparam.get("pmva").toString();
        				item.put("bycoretype", pmva);
        			}
				}
        		
        		String byctrldir = item.get("byctrldir").toString();
        		for (Map sysparam : byctrldir_sysparams) {
        			String pmcd = sysparam.get("pmcd").toString();
        			if(byctrldir.equals(pmcd)) {
        				String pmva = sysparam.get("pmva").toString();
        				item.put("byctrldir", pmva);
        			}
				}
        		
        		String byworkmode = item.get("byworkmode").toString();
        		for (Map sysparam : byworkmode_sysparams) {
        			String pmcd = sysparam.get("pmcd").toString();
        			if(byworkmode.equals(pmcd)) {
        				String pmva = sysparam.get("pmva").toString();
        				item.put("byworkmode", pmva);
        			}
				}
        		
        		String bynetworkmode = item.get("bynetworkmode").toString();
        		for (Map sysparam : bynetworkmode_sysparams) {
        			String pmcd = sysparam.get("pmcd").toString();
        			if(bynetworkmode.equals(pmcd)) {
        				String pmva = sysparam.get("pmva").toString();
        				item.put("bynetworkmode", pmva);
        			}
				}
        		
        		
			}
        	
        }

        return ps;
    }

    /**
     * 根据编号获取准进设备
     */
    public Esbaccessequiptab getAccessequipById(Long iaccessequipid) throws Exception {
//        String hsql = "select new map("
//        		+ " accessequip.id.iaccessequipid as iaccessequipid, accessequip.id.iscenicid as iscenicid, accessequip.id.igardengateid as igardengateid,"
//        		+ " accessequip.iequiptypeid as iequiptypeid, accessequip.szaccessequipcode as szaccessequipcode, accessequip.szaccessequipname as szaccessequipname,"
//        		+ " accessequip.szfactorycode as szfactorycode, accessequip.szipaddress as szipaddress, accessequip.szserverip as szserverip, accessequip.sznetmask as sznetmask,"
//        		+ " accessequip.szgateway as szgateway, accessequip.szdnsip as szdnsip, accessequip.byisuse as byisuse, accessequip.szmemo as szmemo, accessequip.iversion as iversion,"
//        		+ " sys1.pmva as bycoretype,"
//        		+ " sys2.pmva as byctrldir,"
//        		+ " sys3.pmva as byworkmode,"
//        		+ " sys4.pmva as bynetworkmode,"
//        		+ " gardengate.szgardengatename as strigardengateid,"
//        		+ " scenicarea.szscenicname as striscenicid,"
//        		+ " type.szequiptypename as striequiptypeid"
//        		+ " )"
//        		+ " from Esbaccessequiptab accessequip, Esbgardengatetab gardengate, Esbscenicareatab scenicarea, Esbequiptypetab type, Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 "
//        		+ " WHERE accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid and accessequip.iequiptypeid=eq.iequiptypeid "
//        		+ " and accessequip.bycoretype=sys1.id.pmcd and sys1.id.pmky='JSLX' "
//        		+ " and accessequip.byctrldir=sys2.id.pmcd and sys2.id.pmky='KZFX' "
//        		+ " and accessequip.byworkmode=sys3.id.pmcd and sys3.id.pmky='GZMS' "
//        		+ " and sys4.id.pmcd=accessequip.bynetworkmode and sys4.id.pmky='WLFS' "
//        		+ " and accessequip.id.iaccessequipid="+ iaccessequipid;
    	
    	String hsql = "select new map("
        		+ " accessequip.id.iaccessequipid as iaccessequipid, accessequip.id.iscenicid as iscenicid, accessequip.id.igardengateid as igardengateid,"
        		+ " accessequip.iequiptypeid as iequiptypeid, accessequip.szaccessequipcode as szaccessequipcode, accessequip.szaccessequipname as szaccessequipname,"
        		+ " accessequip.szfactorycode as szfactorycode, accessequip.szipaddress as szipaddress, accessequip.szserverip as szserverip, accessequip.sznetmask as sznetmask,"
        		+ " accessequip.szgateway as szgateway, accessequip.szdnsip as szdnsip, accessequip.byisuse as byisuse, accessequip.szmemo as szmemo, accessequip.iversion as iversion,"
        		
        		+ " accessequip.bycoretype as bycoretype, accessequip.byctrldir as byctrldir, accessequip.byworkmode as byworkmode, accessequip.bynetworkmode as bynetworkmode,"
        		+ " gardengate.szgardengatename as strigardengateid,"
        		+ " scenicarea.szscenicname as striscenicid,"
        		+ " type.szequiptypename as striequiptypeid"
        		+ " )"
        		+ " from Esbaccessequiptab accessequip, Esbgardengatetab gardengate, Esbscenicareatab scenicarea, Esbequiptypetab type"
        		+ " WHERE accessequip.id.igardengateid=gardengate.id.igardengateid and accessequip.id.iscenicid=scenicarea.iscenicid and accessequip.iequiptypeid=type.iequiptypeid "
        		+ " and accessequip.id.iaccessequipid="+ iaccessequipid;
        
        
        List list = super.find(hsql);
        if (list == null || list.size() < 1) {
            return null;
        } else {
        	
        	Map accessequipMap = (Map)list.get(0);
        	
        	String bycoretype = accessequipMap.get("bycoretype").toString();
        	List<Map> bycoretype_sysparams = sysService.getSysparamsByPmkyAndPmcds("JSLX", bycoretype);
        	if(bycoretype_sysparams!=null && bycoretype_sysparams.size()>0) {
        		String pmva = bycoretype_sysparams.get(0).get("pmva").toString();
        		accessequipMap.put("bycoretype", pmva);
        	}
        	
        	String byctrldir = accessequipMap.get("byctrldir").toString();
        	List<Map> byctrldir_sysparams = sysService.getSysparamsByPmkyAndPmcds("KZFX", byctrldir);
        	if(byctrldir_sysparams!=null && byctrldir_sysparams.size()>0) {
        		String pmva = byctrldir_sysparams.get(0).get("pmva").toString();
        		accessequipMap.put("byctrldir", pmva);
        	}
        	
        	String byworkmode = accessequipMap.get("byworkmode").toString();
        	List<Map> byworkmode_sysparams = sysService.getSysparamsByPmkyAndPmcds("GZMS", byworkmode);
        	if(byworkmode_sysparams!=null && byworkmode_sysparams.size()>0) {
        		String pmva = byworkmode_sysparams.get(0).get("pmva").toString();
        		accessequipMap.put("byworkmode", pmva);
        	}
        	
        	String bynetworkmode = accessequipMap.get("bynetworkmode").toString();
        	List<Map> bynetworkmode_sysparams = sysService.getSysparamsByPmkyAndPmcds("WLFS", bynetworkmode);
        	if(bynetworkmode_sysparams!=null && bynetworkmode_sysparams.size()>0) {
        		String pmva = bynetworkmode_sysparams.get(0).get("pmva").toString();
        		accessequipMap.put("bynetworkmode", pmva);
        	}
        	
        	
            Esbaccessequiptab ts=new Esbaccessequiptab();
//            BeanUtils.populate(ts, (Map) list.get(0));
            BeanUtils.populate(ts, accessequipMap);
            return ts;
        }
    }

    /**
     * Description：获取所有当前登录用户所属服务商管理园门
     *
     * @param List
     *            fws 当前登录用户所管理服务商
     * @return String 所有当前登录用户所管理的园门JSON
     * @author Jzhenhua Date 2011-10-14
     */
    public String getGardenGateJSON(Long id) throws Exception {
        StringBuffer hsql = new StringBuffer(
                "FROM Esbgardengatetab e WHERE 1=1 ");

        if (id != null) {
            hsql.append("AND e.id.iscenicid = " + id);
        }

        List<Esbgardengatetab> list = this.find(hsql.toString());
        StringBuffer JSON = new StringBuffer();
        JSON.append("[");

        for (int i = 0; i < list.size(); i++) {
            Esbgardengatetab gardengate = list.get(i);
            JSON.append("{\"igardengateid\":\""
                    + gardengate.getId().getIgardengateid()
                    + "\",\"szgardengatename\":\""
                    + gardengate.getSzgardengatename() + "\"}");

            if (i != list.size() - 1) {
                JSON.append(",");
            }
        }

        JSON.append("]");

        return JSON.toString();
    }

    /**
     * 获得当前最大ID
     */
    public Long getMaxId() throws Exception {
        String hsql = "SELECT MAX(e.id.iaccessequipid) FROM Esbaccessequiptab e";
        List list = this.find(hsql);
        Long b = new Long(0);
        if (list.get(0) != null) {
            b = Long.parseLong(list.get(0).toString());
        }

        return b + 1;
    }

    /**
     * 修改准进设备
     */
    public void updateAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
            throws Exception {
        this.update(esbaccessequip);

        // 添加日志信息
//        syslog.setStlg("0112");
//        syslog.setBrief("准进设备操作：" + esbaccessequip.getId().getIaccessequipid());
//        syslog.setNote("修改准进设备信息：" + esbaccessequip.getSzaccessequipname());
//        syslog.setLogdatetime(Tools.getDayTimes());
//        Long logid = getMaxPk("logid", "Syslog");
//        syslog.setLogid(logid + 1);
//        this.save(syslog);
    }

    /**
     * 查询
     */
    public PaginationSupport searchAccessequip(EsbaccessequiptabId id,
                                               int pageSize, int startIndex, String url) throws Exception {

        StringBuffer hsql = new StringBuffer("FROM Esbaccessequiptab e ");

        hsql.append("WHERE e.id.igardengateid = " + id.getIgardengateid());

        return this.findPage(hsql.toString(), pageSize, startIndex, url);
    }

    /**
     * 查询所有属于id编号服务商的园门
     *
     * @param id
     * @return
     */
    public List getGardengateByPreId(Long id) {
        return this.find(" FROM Esbgardengatetab e WHERE e.id.iscenicid= " + id);
    }

    /**
     * *
     * Describe:判断在同一服务商下，售票窗口与准进设备的IP不能重复
     * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#getGardsxipaddress(java.lang.Long, java.lang.String)
     * @param iscenicid
     * @param szipaddress
     * @return
     * @author lijingrui
     * Date:Nov 8, 2011
     */
    public boolean getGardsxipaddress(Long iscenicid, String szipaddress) {
//		String sql=" from Esbticketwintab esb where  esb.iscenicid="+iscenicid+" and esb.szipaddress='"+szipaddress+"' ";
        String hql=" from Esbaccessequiptab eac where eac.id.iscenicid="+iscenicid+" and eac.szipaddress='"+szipaddress+"'";
//		List lst=this.find(sql);
        List wst=this.find(hql);
        if(wst!=null&&wst.size()>0){
            return true;
        }else{
            return false;
        }

    }

    /**
     *
     * Describe:根据联合主键来查看准进设备
     * @auth:lijingrui
     * @param iaccessequipid
     * @param iscenicid
     * @param igardengateid
     * @return
     * return:Esbaccessequiptab
     * Date:Nov 8, 2011
     */
    public Esbaccessequiptab getviewquiptab(Long iaccessequipid,Long iscenicid,Long igardengateid){
        String sql=" from Esbaccessequiptab where id.iaccessequipid="+iaccessequipid+" and id.iscenicid="+iscenicid+" and id.igardengateid="+igardengateid;
        return (Esbaccessequiptab) this.find(sql).get(0);
    }

    /**
     * *
     * Describe:获得某个服务商的设备类型
     * @see com.ectrip.system.afcset.dao.idao.IEsbaccessequiptabDAO#getesbequiptypeJSON(java.lang.Long)
     * @param iscenicid
     * @return
     * @author lijingrui
     * Date:Nov 9, 2011
     */
    public List getesbequiptypeJSON(Long iscenicid) {
    	StringBuilder hql = new StringBuilder();
    	hql.append("select new map(type.iequiptypeid as iequiptypeid, type.szequiptypename as szequiptypename) from Esbequiptypetab type where type.iscenicid="+ iscenicid);
        

        List list = this.find(hql.toString());
       
        return list;
    }


    /**
     * *
     * Describe:查询出某个服务商的设备类型
     * @see com.ectrip.system.afcset.dao.idao.IEsbaccessequiptabDAO#getAllEsbequitype(java.lang.Long)
     * @param iscenicid
     * @return
     * @author lijingrui
     * Date:Nov 9, 2011
     */
    public List getAllEsbequitype(Long iscenicid){
        StringBuffer hsql = new StringBuffer(" from Esbequiptypetab e where 1=1 ");

        if (iscenicid != null) {
            hsql.append("and e.iscenicid = " + iscenicid);
        }
        return this.find(hsql.toString());
    }

    /**
     * *
     * Describe:显示服务商信息
     * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#reviters()
     * @return
     * @author lijingrui
     * Date:2011-9-30
     */
    public List findListesbticket(String scenics) {
        StringBuffer sql=new StringBuffer();
        
//        sql.append(" from Esbscenicareatab where isjd=0 and scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or sys1.spmcd='01'))");
        
        List<Map> sysparList = sysService.queryByPmkyPmcds("PDTP", "(sys.id.pmcd='01'or sys.spmcd='01')");
        StringBuilder pmcds = new StringBuilder();
        
        for (Map syspar : sysparList) {
        	String pmcd = syspar.get("pmcd").toString();
        	pmcds.append("'"+ pmcd +"',");
		}
        pmcds.deleteCharAt(pmcds.length() - 1);
        //select new map(pro.iscenicid as iscenicid,pro.iparentid as iparentid,pro.ilevel as ilevel,pro.szscenicname as szscenicname) from
        sql.append("select new map(pro.iscenicid as iscenicid, pro.szscenicname as szscenicname) from Esbscenicareatab pro where pro.isjd=0 and pro.scenictype in ("+ pmcds +")");
        
        
        
        
        if(scenics!=null&&!scenics.equals("")){
            sql.append(" and iscenicid in ("+scenics+")");
        }
        sql.append(" order by iscenicid");
        return this.find(sql.toString());
    }
    /**
     *
     * Describe:判断该服务商下准进设备IP是否唯一
     * @author:chenxinhao
     * @param acc
     * @return
     * return:boolean
     * Date:2012-11-29
     */
    public boolean checkipAddress(Esbaccessequiptab acc){
        String sql = " from Esbaccessequiptab where szipaddress = '"+acc.getSzipaddress()+"' and id.iaccessequipid != "+acc.getId().getIaccessequipid()+" and id.iscenicid !="+acc.getId().getIscenicid()+" and id.igardengateid !="+acc.getId().getIgardengateid();
        List list = this.find(sql);
        if(list!=null&&list.size()>0){
            return false;
        }
        return true;
    }

    /**
     * *
     * Describe:列表显示出设备状态信息
     * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#searchListAccessstatus(java.lang.String, java.lang.String, int, int, java.lang.String)
     * @param scenicids
     * @param btype
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @author lijingrui
     * Date:2013-2-21
     */
    public PaginationSupport searchListAccessstatus(String scenicids,String btype,int pageSize, int startIndex, String url){
        PaginationSupport ps=null;
        StringBuffer hsql=new StringBuffer();
        
        if(btype!=null&&btype.equals("01")){
//            hsql.append("select new map("
//            		+ " est.seq as seq, est.iscenicid as iscenicid, est.istatuswinid as istatuswinid, est.istatusgardid as istatusgardid"
//            		+ " est.statusbtype as statusbtype, est.typestatus as typestatus, est.byisuse as byisuse, est.statusdtime as statusdtime, "
//            		+ " esb.szscenicname as scenicidname,"
//            		+ " gd.szgardengatename as statuswinname,"
//            		+ " eas.szaccessequipname as statusgardname,"
//            		+ " sys1.pmva as btype, "
//            		+ " sys2.pmva as status "
//            		+ " ) "
//            		+ " from Esbaccessstatustab est, Esbscenicareatab esb, Esbgardengatetab gd, Esbaccessequiptab eas, Sysparv5 sys1, Sysparv5 sys2 "
//            		+ " where est.iscenicid=esb.iscenicid and est.istatuswinid=gd.id.igardengateid and est.istatusgardid=eas.id.iaccessequipid "
//            		+ " and sys1.id.pmky='SBLX' and sys1.id.pmcd=est.statusbtype "
//            		+ " and sys2.id.pmky='SBZT' and sys2.id.pmcd=est.typestatus ");
        	
        	hsql.append("select new map("
            		+ " est.seq as seq, est.iscenicid as iscenicid, est.istatuswinid as istatuswinid, est.istatusgardid as istatusgardid"
            		+ " est.statusbtype as statusbtype, est.typestatus as typestatus, est.byisuse as byisuse, est.statusdtime as statusdtime, "
            		+ " esb.szscenicname as scenicidname,"
            		+ " gd.szgardengatename as statuswinname,"
            		+ " eas.szaccessequipname as statusgardname"
            		+ " ) "
            		+ " from Esbaccessstatustab est, Esbscenicareatab esb, Esbgardengatetab gd, Esbaccessequiptab eas"
            		+ " where est.iscenicid=esb.iscenicid and est.istatuswinid=gd.id.igardengateid and est.istatusgardid=eas.id.iaccessequipid ");
        
            
            
            
        }else{
        	
            hsql.append("select new map("
            		+ " est.seq as seq, est.iscenicid as iscenicid, est.istatuswinid as istatuswinid, est.istatusgardid as istatusgardid, "
            		+ " est.statusbtype as statusbtype, est.typestatus as typestatus, est.byisuse as byisuse, est.statusdtime as statusdtime, "
            		+ " esb.szscenicname as scenicidname, "
            		+ " std.szstationname as statuswinname, "
            		+ " ewt.szticketwinname as statusgardname"
            		+ " ) "
            		+ " from Esbaccessstatustab est, Esbscenicareatab esb, Esbticketstationtab std, Esbticketwintab ewt "
            		+ " where est.iscenicid=esb.iscenicid and est.istatuswinid=std.iticketstationid and est.istatusgardid=ewt.iticketwinid ");
        }

        if(scenicids!=null&&!scenicids.equals("")){
            hsql.append(" and est.iscenicid in ("+scenicids+")");
        }
        if(btype!=null&&!btype.equals("")){
            hsql.append(" and est.statusbtype='"+btype+"'");
        }

        hsql.append(" order by est.statusdtime desc ");

        ps=this.findPage(hsql.toString(), pageSize, startIndex, url);
        

        
        List list = ps.getItems();
        if (list != null && list.size() >= 1) {
            Map map = null;
            for (int i = 0; i < list.size(); i++) {
                Esbaccessstatustab esbstatus=new Esbaccessstatustab();
                map = (Map) list.get(i);
                
//        		+ " sys1.pmva as btype, "
//        		+ " sys2.pmva as status "
                
//        		+ " and sys1.id.pmky='SBLX' and sys1.id.pmcd=est.statusbtype "
//        		+ " and sys2.id.pmky='SBZT' and sys2.id.pmcd=est.typestatus ");
                
                String statusbtype = map.get("statusbtype").toString();
                String pmva_statusbtype = SBLX.getPmvaByPmcd(statusbtype);
                map.put("btype", pmva_statusbtype);
                
                String typestatus = map.get("typestatus").toString();
                String pmva_typestatus = SBZT.getPmvaByPmcd(typestatus);
                map.put("status", pmva_typestatus);
                
                
                try {
                    BeanUtils.populate(esbstatus, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(Tools.getDayTimes().compareTo(esbstatus.getStatusdtime())>0.5){
                    if(esbstatus.getByisuse()!=null&&esbstatus.getByisuse()!=2){
                        esbstatus.setByisuse(2L);

                        this.update(esbstatus);
                    }

                }
            }
        }

        return ps;
    }

}
