package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.provider.dao.IProviderDAO;
import com.ectrip.upload.model.Upfile;

@Repository
public class ProviderDAO extends GenericDao implements IProviderDAO {
	
	@Autowired
	private SysService sysService;//系统服务API

    public Esbscenicareatab query(Long scenicId) {
        List returnList = new ArrayList();
        Esbscenicareatab scenic = new Esbscenicareatab();
        try {
            scenic = (Esbscenicareatab) this.get(Esbscenicareatab.class,
                    scenicId);
            Sysparv5Id sysparid = new Sysparv5Id();
            Sysparv5 sysparv5 = null;

            if (scenic.getScenictype() != null
                    && !"".equals(scenic.getScenictype())) {// 服务商种类
                sysparid.setPmcd(scenic.getScenictype());
                sysparid.setPmky("PDTP");
                sysparv5 = (Sysparv5) this.get(Sysparv5.class, sysparid);
                if (sysparv5 != null) {
                    scenic.setStrpdtyp(sysparv5.getPmva().toString());
                }
            }
            if (scenic.getSzgrade() != null && !"".equals(scenic.getSzgrade())) {// 等级
                sysparid.setPmcd(scenic.getSzgrade());
                sysparid.setPmky("DENJ");
                sysparv5 = (Sysparv5) this.get(Sysparv5.class, sysparid);
                if (sysparv5 != null) {
                    scenic.setStrgrade(sysparv5.getPmva().toString());
                }
            }
            // 所属地
            if (scenic.getSzregionalid() != null
                    && !"".equals(scenic.getSzregionalid())
                    && 0L != scenic.getSzregionalid()) {
                Galsourceregiontab galsource = (Galsourceregiontab) this.get(
                        Galsourceregiontab.class,
                        new Long(scenic.getSzregionalid()));
                if (galsource != null) {
                    if(galsource.getIparentid()!=0){
                        scenic.setStrregion(galsource.getSzinnername().substring(3));
                    }else{
                        scenic.setStrregion(galsource.getSzinnername());
                    }
                }
            }
            if (scenic.getIparentid() != null
                    && !"".equals(scenic.getIparentid())
                    && 0 != scenic.getIparentid()) {
                Esbscenicareatab scenicparent;
                scenicparent = (Esbscenicareatab) this.get(
                        Esbscenicareatab.class, scenic.getIparentid());
                if (scenicparent != null) {
                    scenic.setStriparentname(scenicparent.getSzscenicname());
                }
            }

            // 图库
            String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid=0 and pic.iscenicid="
                    + scenic.getIscenicid()+" order by pic.isecenicpictureid";
            List list = this.find(hsqls);
            scenic.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenic;
    }

    /**
     * 更改层序号
     *
     * @param ilever
     *            层
     * @param ileverseq层序号
     * @param objectId对象id
     * @param tablename表名
     */
    public void updateIleverseq(int ilever, int ileverseq, Long sourceid,
                                String tablename) {

        // 先判断此层序号是否已经被用，如果有，则更改些层以上的所有数据
        List isuse = this.getIleverseqList(ilever, ileverseq, sourceid,
                tablename);

        if (isuse.size() >= 1) {// 表示已经存在

            String hsql = " from " + tablename + " where ilevel=" + ilever
                    + " and ilevelsequence>=" + ileverseq;
            List list = super.find(hsql);
            if (list.size() >= 1) {
                for (int i = 0; i < list.size(); i++) {
                    Esbscenicareatab source = (Esbscenicareatab) list.get(0);
                    source.setIlevelsequence(source.getIlevelsequence() + 1);
                    super.update(source);
                }
            }
        }
    }

    public List getIleverseqList(int ilever, int ilerverseq, Long sourceid,
                                 String tablename) {
        String sql = " from " + tablename + " where ilevel=" + ilever
                + " and ilevelsequence=" + ilerverseq;
        List list = this.find(sql);
        if (list.size() >= 1) {
            Esbscenicareatab source = (Esbscenicareatab) list.get(0);
            if (sourceid == source.getIscenicid()) {
                list.remove(0);
            }
        }

        return list;
    }

    /**
     * 得到当前登录用户的服务商信息 Describe:如果登录用户为平台用户，则读出所有服务商，如为景区企业用，则读出读企业下的服务
     *
     * @auth:huangyuqi
     * @param iprentid
     * @param pdtp
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param iemployeeid后台登录用户
     * @return return:PaginationSupport Date:2011-10-19
     */
    public PaginationSupport queryAllList(Long iprentid, String pdtp, int page,
                                          int pagesize, String url, String path, Long employeeid) {

        String hsql = "select new map(pro.iscenicid as iscenicid,pro.iparentid as iparentid,pro.ilevel as ilevel,pro.szscenicname as szscenicname,pro.szsceniccode as szsceniccode,pro.szlasttime as szlasttime,pro.imaxdata as imaxdata,pro.iscanreturn as iscanreturn,pro.icanmodify as icanmodify,pro.byisuse as byisuse,pro.iordernumber as iordernumber,pro.scenictype as scenictype,pro.isjd as isjd,sys2.pmva as strpdtyp ) from Esbscenicareatab pro,Sysparv5 sys2  where  pro.scenictype=sys2.id.pmcd and sys2.id.pmky='PDTP' and pro.isjd=0 ";

        String sql = " from Galcompanyinfotab pany where pany.icompanyinfoid = (select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
                + employeeid + ")";
        List companylist = this.find(sql);
        if (companylist.size() >= 1) {
            Galcompanyinfotab company = (Galcompanyinfotab) companylist.get(0);
            if ("02".equals(company.getCompanytype())) {// 景区企业
                hsql += " and pro.iscenicid in (select c.id.iscenicid from Companyscenic c where c.id.icompanyinfoid ="
                        + company.getIcompanyinfoid() + ") ";
            }
        }

        if (iprentid == null || "".equals(iprentid) || 0 == iprentid) {
            hsql += "  and pro.ilevel=1";
            if (pdtp != null && !"".equals(pdtp)) {
                if ("01".equals(pdtp)) {
                    hsql += " and pro.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
                            + pdtp + "'or spmcd='" + pdtp + "'))";
                } else {
                    hsql += " and pro.scenictype='" + pdtp + "' ";
                }
            }
        } else {

            if ("up".equals(path)) {// 上一级

                Esbscenicareatab provider = (Esbscenicareatab) this.get(
                        Esbscenicareatab.class, iprentid);
                if (provider.getIlevel() == 1) {
                    hsql += " and  pro.iparentid=" + provider.getIparentid();
                    if (pdtp != null && !"".equals(pdtp)) {
                        hsql += " and pro.scenictype='" + pdtp + "' ";
                    }
                } else {
                    hsql += " and  pro.iparentid=" + provider.getIparentid();
                }

            } else {// 下一级
                hsql += " and pro.iparentid=" + iprentid;
            }
        }

        hsql += " order by pro.iordernumber desc,pro.scenictype,pro.iscenicid ";

        System.out.println(hsql);
        PaginationSupport ps = this.findPage(hsql, pagesize, page, url);
        if(ps != null && !CollectionUtils.isEmpty(ps.getItems())){
            List list = ps.getItems();
            for (int i = 0;i < list.size();i++){
                Map map = (Map) list.get(i);
                ProviderCompany pc = queryProviderCompany(Long.parseLong(map.get("iscenicid").toString()));
                if(pc != null){
                    map.put("pc",pc.getStatus());
                }else{
                    map.put("pc",0);
                }
            }
        }
        return ps;
    }


    public ProviderCompany queryProviderCompany(Long iscenicid){
//        String hsql = "from ProviderCompany where type='02' and iscenicid = "+iscenicid;
//        List list = this.find(hsql);
    	String hsql = "from ProviderCompany where type='02'";
    	StringBuffer sb = new StringBuffer(hsql);
    	if(iscenicid!=0 && iscenicid!=null){
    		sb.append(" and iscenicid = " + iscenicid);
    	}
    	List list = this.find(sb.toString());
    	if(list != null && !list.isEmpty()){
            return (ProviderCompany) list.get(0);
        }
        return null;
    }
    /**
     * 根据登录人得到服务商列表 Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param scenictype
     *            服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd
     *            是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq
     *            是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List Date:2011-10-28
     */
    public List getScenicList(Esfemployeetab esfemployeetab, String scenictype,
                              int isjd, String isonlyjq,String groupid,boolean isHqyatu) {
        List list = new ArrayList();
        StringBuffer hsql2 = new StringBuffer();
        if (!isHqyatu) {
//        	hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Companyscenic c,Esbscenicareatab es,Galcompanyinfotab pany where c.id.icompanyinfoid = pany.icompanyinfoid AND c.id.iscenicid = es.iscenicid  and"
//            		+ " es.byisuse =1 and pany.icompanyinfoid = "+groupid);
        	
        	/**
        	 * 【项目重构修改】
        	 * groupid即为Galcompanyinfotab的icompanyinfoid
        	 * 在中间表Companyscenic中icompanyinfoid与iscenicid是多对多关系
        	 * 所以，可以直接根据groupid去Companyscenic中查询对应的iscenicid集合
        	 */
        	
        	//1.根据groupid去Companyscenic中查询对应的iscenicid集合
        	List<Map> companyscenicList = sysService.getIscenicidsByIcompanyinfoid(Long.valueOf(groupid));
        	StringBuilder iscenicids = new StringBuilder();
        	for (Map companyscenic : companyscenicList) {
        		String iscenicid = companyscenic.get("iscenicid").toString();
        		
        		iscenicids.append(iscenicid);
        		iscenicids.append(",");
			}
        	iscenicids.deleteCharAt(iscenicids.length() - 1);//去除最后一个逗号
        	
        	//2.根据iscenicid集合查询Esbscenicareatab列表
        	hsql2.append("select new map(es.iscenicid as iscenicid, es.szscenicname as szscenicname) from Esbscenicareatab es where es.iscenicid in ("+ iscenicids +")");
        	
        } else {
//        	hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Companyscenic c,Esbscenicareatab es,Galcompanyinfotab pany where c.id.icompanyinfoid = pany.icompanyinfoid AND c.id.iscenicid = es.iscenicid  and"
//            		+ " es.byisuse =1");
        	
        	/**
        	 * 【项目重构修改】
        	 * 这条sql连表既没有取值也没有带条件，但连表本身就是一种条件(中间表需要有此数据才能成立)
        	 * 由于没有条件，可以简化一下，只用中间表即可
        	 */
        	
        	//1.查出Companyscenic中所有的的iscenicid
        	List<Map> companyscenicList = sysService.getIscenicidsByIcompanyinfoid(null);//不传即查所有
        	StringBuilder iscenicids = new StringBuilder();
        	for (Map companyscenic : companyscenicList) {
        		String iscenicid = companyscenic.get("iscenicid").toString();
        		
        		iscenicids.append(iscenicid);
        		iscenicids.append(",");
			}
        	iscenicids.deleteCharAt(iscenicids.length() - 1);//去除最后一个逗号
        	
        	//2.根据iscenicid集合查询Esbscenicareatab列表
        	hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) Esbscenicareatab es where es.byisuse =1 and es.iscenicid in ("+ iscenicids +")");
        	
        }
        if (0 == isjd) {// 服务商
            hsql2.append(" and  es.isjd=0 ");
        } else if (1 == isjd) {// 景点

            if ("".equals(isjd)) {
                hsql2.append(" and  es.isjd=0 ");
            } else if ("01".equals(isjd)) {
                hsql2.append(" and  es.isjd=1 ");
            }
        }
        // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
        if (esfemployeetab != null) {
            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
                String scenics = esfemployeetab.getScenics();
                if (scenics == null || scenics.equals("")) {
                    return list;
                }
                hsql2.append(" and es.iscenicid in (" + scenics + ")  ");
            }
        }
        hsql2.append(" group by es.iscenicid,es.szscenicname");
        list = this.find(hsql2.toString());
        return list;

    }

    /**
     * 根据得到服务商列表(前台专用) Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param scenictype
     *            服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd
     *            是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq
     *            是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List Date:2011-10-28
     */
    public List getScenicListByPage(String scenictype, int isjd, String isonlyjq) {
        List list = new ArrayList();
        StringBuffer hsql2 = new StringBuffer();
        if (scenictype == null || "".equals(scenictype)) {
            hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1  ");
        } else {
            if (isonlyjq != null && !"".equals(isonlyjq)) {
                if ("01".equals(isonlyjq)) {
                    hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
                            + scenictype + "'or spmcd='" + scenictype + "')) ");
                } else if ("02".equals(isonlyjq)) {
                    hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype ='01' ");
                } else {
                    hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
                            + scenictype + "'or spmcd='" + scenictype + "')) ");
                }
            } else {
                hsql2.append("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
                        + scenictype + "'or spmcd='" + scenictype + "')) ");
            }
        }
        if (0 == isjd) {// 服务商
            hsql2.append(" and  es.isjd=0 ");
        } else if (1 == isjd) {// 景点

            if ("".equals(isjd)) {
                hsql2.append(" and  es.isjd=0 ");
            } else if ("01".equals(isjd)) {
                hsql2.append(" and  es.isjd=1 ");
            }
        }

        hsql2.append(" order by es.scenictype, es.iscenicid ");
        list = this.find(hsql2.toString());
        return list;

    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScenicListByAreaIds(String ids) throws Exception{
    	List<Map> list = new ArrayList();
    	List<Map> newList = new ArrayList();
    	
    	String hsql = "select es.iscenicid as szregionalid,es.szscenicname as szregionalname,case es.szsceniccode when '0' then 'true' else 'false' end  as hasnext from Esbscenicareatab es where es.szregionalid in(" + ids + ") and es.scenictype='01'";
    	list = this.findBySqlToMap(hsql, new Object[]{});
    	
    	for (Map map : list) {
    		Map<String,Object> newMap = new HashMap<String, Object>();
    		Set keySet = map.keySet();
    		for (Object object : keySet) {
    			newMap.put(object.toString().toLowerCase(), map.get(object));
			}
    		newList.add(newMap);
		}
    	
		return newList;
	}

    /**
     * 获取景点列表 Describe:
     *
     * @auth:huangyuqi
     * @param iprentid
     * @param pdtp
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param iemployeeid后台登录用户
     * @return return:PaginationSupport Date:2011-10-19
     */
    public PaginationSupport queryAllList(Long iprentid, int page,
                                          int pagesize, String url, String path) {
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(pro.iscenicid as iscenicid,pro.iparentid as iparentid,pro.ilevel as ilevel,pro.isjd as isjd,pro.szscenicname as szscenicname,pro.szsceniccode as szsceniccode,pro.szlasttime as szlasttime,pro.imaxdata as imaxdata,pro.iscanreturn as iscanreturn,pro.icanmodify as icanmodify,pro.byisuse as byisuse,pro.iordernumber as iordernumber,pro.scenictype as scenictype,sys2.pmva as strpdtyp ) from Esbscenicareatab pro,Sysparv5 sys2  where  pro.scenictype=sys2.id.pmcd and sys2.id.pmky='PDTP' ");

        hsql.append(" and pro.scenictype='01' and pro.isjd=1 ");

        if ("up".equals(path)) {// 上一级

            Esbscenicareatab provider = (Esbscenicareatab) this.get(
                    Esbscenicareatab.class, iprentid);
            if (provider.getIlevel() == 1) {
                hsql.append(" and  pro.iparentid=" + provider.getIparentid());
            } else {
                hsql.append(" and  pro.iparentid=" + provider.getIparentid());
            }

        } else {// 下一级
            hsql.append(" and pro.iparentid=" + iprentid);
        }

        hsql.append(" order by pro.iordernumber desc,pro.scenictype,pro.iscenicid ");
        PaginationSupport ps = this.findPage(hsql.toString(), pagesize, page,
                url);
        if (ps.getItems() != null && ps.getItems().size() > 0) {
            List list = ps.getItems();
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                Upfile picture = getProviderPic(Integer.parseInt(map.get(
                        "iscenicid").toString()));
                if(picture==null){
                    map.put("upadder", null);
                    map.put("upfilename", null);
                }else{
                    map.put("upadder", picture.getUpadder());
                    map.put("upfilename", picture.getUpfilename());
                }

            }
        }
        return ps;
    }

    public int getProviderCount() {
        String sql = "select count(*) from Esbscenicareatab where isjd=0 and ilevel=1";
        List list = find(sql);
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return Integer.parseInt(list.get(0).toString());
        } else {
            return 0;
        }
    }

    /**
     * 查询服务商信息 Describe:如果登录用户为平台用户，则读出所有服务商，如为景区企业用，则读出读企业下的服务
     *
     * @auth:huangyuqi
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param buttontype
     * @param condition
     * @param pdtp
     * @param iemployeeid后台登录用户
     * @param isjd是否是景点
     * @return return:PaginationSupport Date:2011-10-19
     */
    public PaginationSupport queryList(int page, int pagesize, String url,
                                       String path, int buttontype, String where, String pdtp,
                                       Long employeeid, int isjd) {

        StringBuffer hsql = new StringBuffer();

        hsql.append("select new map(pro.iscenicid as iscenicid,pro.iparentid as iparentid,pro.ilevel as ilevel,pro.szscenicname as szscenicname,pro.szsceniccode as szsceniccode,pro.szlasttime as szlasttime,pro.imaxdata as imaxdata,pro.iscanreturn as iscanreturn,pro.icanmodify as icanmodify,pro.byisuse as byisuse,pro.iordernumber as iordernumber,pro.scenictype as scenictype,pro.isjd as isjd,sys2.pmva as strpdtyp ) from Esbscenicareatab pro,Sysparv5 sys2  where  pro.scenictype=sys2.id.pmcd and sys2.id.pmky='PDTP'  ");

        String sql = " from Galcompanyinfotab pany where pany.icompanyinfoid = (select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="
                + employeeid + ")";
        List companylist = this.find(sql);
        if (companylist.size() >= 1) {
            Galcompanyinfotab company = (Galcompanyinfotab) companylist.get(0);
            if ("02".equals(company.getCompanytype())) {// 景区企业
                hsql.append(" and pro.iscenicid in (select c.id.iscenicid from Companyscenic c where c.id.icompanyinfoid ="
                        + company.getIcompanyinfoid() + ") ");
            }
        }

        if (1 == buttontype) {
            hsql.append(" and pro.szsceniccode= '" + where + "' ");
        }
        if (2 == buttontype) {
            hsql.append(" and pro.szscenicname like '%" + where + "%' ");
        }
        if (3 == buttontype) {
            hsql.append(" and pro.byisuse=0 ");
        }

        if (isjd == 1) {// 表示景点
            hsql.append(" and pro.isjd=1 and pro.scenictype='01' ");
        } else {// 服务商
            hsql.append(" and pro.isjd=0");

            if (pdtp != null && !"".equals(pdtp)) {
                if ("01".equals(pdtp)) {
                    hsql.append(" and pro.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
                            + pdtp + "'or spmcd='" + pdtp + "'))");
                } else {
                    hsql.append(" and pro.scenictype='" + pdtp + "' ");
                }
            }

        }
        hsql.append(" order by pro.iordernumber,pro.scenictype,pro.iscenicid ");

        PaginationSupport ps;
        ps = this.findPage(hsql.toString(), pagesize, page, url);
        
        if(ps != null && !CollectionUtils.isEmpty(ps.getItems())){
            List list = ps.getItems();
            for (int i = 0;i < list.size();i++){
                Map map = (Map) list.get(i);
                ProviderCompany pc = queryProviderCompany(Long.parseLong(map.get("iscenicid").toString()));
                if(pc != null){
                    map.put("pc",pc.getStatus());
                }else{
                    map.put("pc",0);
                }
            }
        }
        return ps;
    }

    /**
     * 判断此服务商是否有订单存在
     */
    public int retiveOrder(Long scenicid) {
        int num = 0;
        // 判断网上预定服务商订单明细
        String hsql1 = " from YOrder where iscenicid = " + scenicid;
        List list1 = this.find(hsql1);
        if (list1 != null && list1.size() >= 1) {
            num = num + 1;
        } else {
            num = num + 0;
        }
        // 判断网上预定服务商订单明细
        String hsql2 = " from YOrderlist where id.iscenicid = " + scenicid;
        List list2 = this.find(hsql2);
        if (list2 != null && list2.size() >= 1) {
            num = num + 1;
        } else {
            num = num + 0;
        }
        // 判断网上预定服务商订单明细
        String hsql3 = " from TOrderlist where id.iscenicid = " + scenicid;
        List list3 = this.find(hsql3);
        if (list3 != null && list3.size() >= 1) {
            num = num + 1;
        } else {
            num = num + 0;
        }
        // 判断订单服务商表
        String hsql4 = " from TOrder where iscenicid = " + scenicid;
        List list4 = this.find(hsql4);
        if (list4 != null && list4.size() >= 1) {
            num = num + 1;
        } else {
            num = num + 0;
        }
        // 判断订单服务商表
        String hsql5 = " from TZorderlist where id.iscenicid = " + scenicid;
        List list5 = this.find(hsql5);
        if (list5 != null && list5.size() >= 1) {
            num = num + 1;
        } else {
            num = num + 0;
        }

        return num;
    }

    // 判断它是否是最大层级
    public boolean boolMaxpro(Long scencid) {
        boolean isusie = false;
        String hsql = " from Esbscenicareatab where iparentid=" + scencid;
        List list = this.find(hsql);
        if (list.size() > 0) {
            isusie = true;
        }
        return isusie;
    }

    // 查询服务商名称
    public String QueryProviderName(Long providerId) {
        String name = "";
        String hsql = "select szscenicname from Esbscenicareatab where iscenicid="
                + providerId;
        List list = this.find(hsql);
        if (list.size() > 0) {
            name = (String) list.get(0);
        }
        return name;
    }

    /**
     * 删除与服务商关联系的数据 Describe:
     *
     * @auth:huangyuqi
     * @param providerId
     *            return:void Date:2011-9-29
     */
    public void deleteOhters(Long providerId) {
        String hsql = " from Companyscenic c where c.id.iscenicid="
                + providerId;
        List list = this.find(hsql);
        if (list != null && list.size() >= 1) {
        	this.deleteAll(list);
            /*for (int i = 0; i < list.size(); i++) {
                Companyscenic company = (Companyscenic) list.get(i);
                this.delete(company);
            }*/
        }

    }

    /**
     * 删除其它数据 Describe:
     *
     * @auth:huangyuqi
     * @param providerId
     *            return:void Date:2011-10-4
     */
    public void deleteProvider(Long providerId) {
        String hsql = "";
        List list = new ArrayList();
        // 删除价格拆分表数据
        hsql = " from Edmticketcomposepricetab where id.icrowdkindpriceid in(select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid in (select itickettypeid from Edmtickettypetab where iscenicid="
                + providerId + "))";
        list = this.find(hsql);
        if (list != null && list.size() >= 1) {
            for (int i = 0; i < list.size(); i++) {
                Edmticketcomposepricetab pricecompose = (Edmticketcomposepricetab) list
                        .get(i);
                this.delete(pricecompose);
            }
        }

        // 删除价格表数据
        hsql = " from Edmcrowdkindpricetab pri where pri.itickettypeid in (select prd.itickettypeid from Edmtickettypetab prd where prd.iscenicid="
                + providerId + ")";
        list = this.find(hsql);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) list.get(i);
                delete(price);
            }
        }

        // 删除景点
        hsql = " from Esbscenicareatab where irootid=" + providerId
                + " and isjd=1 ";
        list = this.find(hsql);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Esbscenicareatab provider = (Esbscenicareatab) list.get(i);
                delete(provider);
            }
        }

        // 删除酒店属性数据
        hsql = " from Hotelprovider where iscenicid = " + providerId;
        List hotelproviderlist = this.find(hsql);
        if (hotelproviderlist != null) {
            for (int i = 0; i < hotelproviderlist.size(); i++) {
                Hotelprovider hotelprovider = (Hotelprovider) hotelproviderlist
                        .get(i);
                this.delete(hotelprovider);
            }
        }

        // 删除酒店服务数据
        // hsql=" from Hotelsvc where id.iscenicid = "+providerId;
        // List hotelsvclist = this.find(hsql);
        // if(hotelsvclist!=null){
        // for (int i = 0; i < hotelsvclist.size(); i++) {
        // Hotelsvc hotelsvc = (Hotelsvc)hotelsvclist.get(i);
        // this.delete(hotelsvc);
        // }
        // }

    }

    /**
     * 根据酒店编号查询出酒店属性 Describe:
     *
     * @auth:huangyuqi
     * @param iscenicid
     * @return return:Hotelprovider Date:2011-12-12
     */
    public Hotelprovider queryHotelProvider(Long iscenicid) {
        // 酒店属性
        Hotelprovider hotelprovider = new Hotelprovider();
        hotelprovider = (Hotelprovider) this
                .get(Hotelprovider.class, iscenicid);

        if (hotelprovider == null) {
            hotelprovider = new Hotelprovider();
            // 服务商名称
            String scenicname = QueryProviderName(iscenicid);
            hotelprovider.setSzscenicname(scenicname);
            hotelprovider.setIscenicid(iscenicid);
        } else {
            // 服务商名称
            String scenicname = QueryProviderName(iscenicid);
            hotelprovider.setSzscenicname(scenicname);
        }
        // 设施
        if (hotelprovider.getHotelservice() != null
                && !hotelprovider.getHotelservice().equals("")) {
            String[] services = hotelprovider.getHotelservice().split(" ");
            String[] serviceids = new String[services.length];
            for (int i = 0; i < services.length; i++) {
                serviceids[i] = findpmcd("HTSS", services[i]);
            }
            hotelprovider.setServiceids(serviceids);
        }
        //位置
        if (hotelprovider.getNoted1() != null
                && !hotelprovider.getNoted1().equals("")) {
            String[] sqids = hotelprovider.getNoted1().split(" ");
            hotelprovider.setSqids(sqids);
        }
        // 品牌
        if (hotelprovider.getNoted2() != null
                && !hotelprovider.getNoted2().equals("")) {
            String str = findpmcd("HTPP", hotelprovider.getNoted2());
            hotelprovider.setNoted2(str);
        } else {
            hotelprovider.setNoted2("99");
        }
        return hotelprovider;
    }

    public String findpmcd(String pmky, String pmva) {
        String sql = "from Sysparv5 sys where sys.id.pmky='" + pmky
                + "' and sys.pmva like '%" + pmva + "%'";
        List list = this.find(sql);
        if (list != null && !list.isEmpty()) {
            Sysparv5 sysparv5 = (Sysparv5) list.get(0);
            return sysparv5.getId().getPmcd();
        }
        return "";
    }

    /**
     * 保存酒店服务商属性 Describe:
     *
     * @auth:huangyuqi
     * @param hotelprovier
     *            return:void Date:2011-12-12
     */
    public void saveHotelProvider(Hotelprovider hotelprovier) {
        // 保存酒店属性
        this.saveOrUpdate(hotelprovier);
    }

    /**
     * 根据服务商编号查询出酒店属性 Describe:
     *
     * @auth:aozhuozu
     * @param iscenicid
     * @return return:Hotelprovider Date:2012-08-20
     */
    public Hotelprovider queryScenicProvider(Long iscenicid) {
        // 景区属性
        Hotelprovider hotelprovider = new Hotelprovider();
        hotelprovider = (Hotelprovider) this
                .get(Hotelprovider.class, iscenicid);
        if (hotelprovider == null) {
            hotelprovider = new Hotelprovider();
            // 服务商名称
            String scenicname = QueryProviderName(iscenicid);
            hotelprovider.setSzscenicname(scenicname);
            hotelprovider.setIscenicid(iscenicid);
        } else {
            // 服务商名称
            String scenicname = QueryProviderName(iscenicid);
            hotelprovider.setSzscenicname(scenicname);
        }
        return hotelprovider;
    }

    /**
     * 保存服务商-景区属性 Describe:
     *
     * @auth:huangyuqi
     * @param hotelprovier
     *            return:void Date:2012-08-20
     */
    public void saveScenicprovider(Hotelprovider hotelprovier) {
        // 保存景区属性
        this.saveOrUpdate(hotelprovier);

//		StringBuffer sql = new StringBuffer();
//		sql.append(" from Edmcrowdkindpricetab crow where crow.itickettypeid in ( select edm.itickettypeid from Edmtickettypetab edm where edm.iscenicid=1 )");
//		Long ipeoplenumrange = null;
//		// 不是实名制
//		if (hotelprovier.getInoteger2() == 0) {
//			sql.append(" and crow.ibusinessid in (1,2)");
//			ipeoplenumrange = 0L;
//		} else if (hotelprovier.getInoteger2() == 1) {// 散客实名制
//			sql.append(" and crow.ibusinessid=1 ");
//			ipeoplenumrange = 1L;
//		} else if (hotelprovier.getInoteger2() == 2) {// 团队散客实名制
//			sql.append(" and crow.ibusinessid in (1,2)");
//			ipeoplenumrange = 1L;
//		}
//
//		List lst = this.find(sql.toString());
//		if (lst != null && lst.size() > 0) {
//			for (int i = 0; i < lst.size(); i++) {
//				Edmcrowdkindpricetab crowkind = (Edmcrowdkindpricetab) lst
//						.get(i);
//				crowkind.setIpeoplenumrange(ipeoplenumrange);
//				this.update(crowkind);
//			}
//		}
    }

    private Upfile getProviderPic(int iscenicid) {
        String hql = "select new map(max(up.upid) as upid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid=0 and pic.iscenicid="
                + iscenicid;
        List list = find(hql);
        Upfile upfile = null;
        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            if (map.get("upid") != null) {
                upfile = (Upfile) get(Upfile.class,
                        Long.parseLong(map.get("upid").toString()));
            }
        }
        return upfile;
    }

    /**
     * * Describe:搜索服务商
     *
     * @see com.ectrip.system.provider.dao.idao.IProviderDAO#searchNearbyProvider(java.lang.String,
     *      java.lang.String, java.lang.Long, java.lang.String)
     * @param type
     *            服务商类别
     * @param notinIds
     *            不包含的服务商ID,以逗号隔开
     * @param regionalid
     *            客源地ID
     * @param inIds
     *            包含的服务商ID,以逗号隔开
     * @return
     * @author chenxinhao Date:2013-12-9
     */
    public List searchNearbyProvider(String type, String notinIds,
                                     Long regionalid, String inIds) {
        List resultList = new ArrayList();
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,g.iregionalid as iregionalid,g.szregionalname as szregionalname) from Esbscenicareatab s,Galsourceregiontab g where s.byisuse =1 and s.isjd=0 and s.szregionalid=g.iregionalid ");
        if (type != null && !type.equals("")) {
            hsql.append(" and s.scenictype='" + type + "' ");
        }
        if (notinIds != null && !notinIds.equals("")) {
            hsql.append(" and s.iscenicid not in (" + notinIds + ") ");
        }
        if (inIds != null && !inIds.equals("")) {
            hsql.append(" and s.iscenicid in (" + inIds + ") ");
        }
        if (regionalid != null && !"".equals(regionalid) && regionalid != 0) {
            hsql.append(" and s.szregionalid=" + regionalid);
        }
        if(inIds != null && !inIds.equals("")){
            StringBuffer order = new StringBuffer();
            String[] str = inIds.split(",");
            for(int i=0;i<str.length;i++){
                order.append(str[i]+","+(i+1)+",");
            }
            String orders = order.toString().substring(0,
                    order.toString().length() - 1);
            hsql.append(" order by decode(s.iscenicid," + orders + ") ");
        }else{
            hsql.append(" order by s.szregionalid,s.iscenicid");
        }
        List list = this.find(hsql.toString());
        if (notinIds!=null&&!notinIds.equals("")) {
            if (list != null && !list.isEmpty()) {
                String tip = "";
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String iregionalid = map.get("iregionalid").toString();
                    if (tip.equals(iregionalid)) {
                        map.put("szscenicname",
                                "——" + map.get("szscenicname").toString());
                        resultList.add(map);
                    } else {
                        tip = iregionalid;
                        Map smap = new HashMap();
                        smap.put("iscenicid", "");
                        smap.put("szscenicname", map.get("szregionalname")
                                .toString());
                        map.put("szscenicname",
                                "——" + map.get("szscenicname").toString());
                        resultList.add(smap);
                        resultList.add(map);
                    }
                }
            }
            return resultList;
        }
        return list;
    }

    /**
     *
     * Describe:搜索产品
     * @author:chenxinhao
     * @param type	服务商类别
     * @param notinIds	为选择产品ID
     * @param inIds	以选择产品ID
     * @param regionalid	客源地
     * @param radio 0为未选择列表  1为已选择列表
     * @return
     * return:List
     * Date:2013-12-9
     */
    public List searchProduct(String type, String notinIds, String inIds,
                              Long regionalid,int radio) {
        List resultList = new ArrayList();
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,t.itickettypeid as itickettypeid,t.sztickettypename as sztickettypename) ");
        hsql.append(" from Edmtickettypetab t, Esbscenicareatab s where t.iscenicid = s.iscenicid and t.byisuse = 1 ");
        if (type != null && !type.equals("")) {
            hsql.append(" and s.scenictype in (" + type + ") ");
        }
        if (notinIds != null && !notinIds.equals("")) {
            hsql.append(" and t.itickettypeid not in (" + notinIds + ") ");
        }
        if (inIds != null && !inIds.equals("")) {
            hsql.append(" and t.itickettypeid in (" + inIds + ") ");
        }
        if (regionalid != null && !"".equals(regionalid) && regionalid != 0) {
            hsql.append(" s.szregionalid=" + regionalid);
        }
        if(inIds != null && !inIds.equals("")){
            StringBuffer order = new StringBuffer();
            String[] str = inIds.split(",");
            for(int i=0;i<str.length;i++){
                order.append(str[i]+","+(i+1)+",");
            }
            String orders = order.toString().substring(0,
                    order.toString().length() - 1);
            hsql.append(" order by decode(t.itickettypeid," + orders + ") ");
        }else{
            hsql.append(" order by s.scenictype,s.iscenicid,t.itickettypeid");
        }
        List list = this.find(hsql.toString());
        if (radio==0) {
            if (list != null && !list.isEmpty()) {
                String tip = "";
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String iscenicid = map.get("iscenicid").toString();
                    if (tip.equals(iscenicid)) {
                        map.put("sztickettypename",
                                "——" + map.get("sztickettypename").toString());
                        resultList.add(map);
                    } else {
                        tip = iscenicid;
                        Map smap = new HashMap();
                        smap.put("itickettypeid", "");
                        smap.put("sztickettypename", map.get("szscenicname")
                                .toString());
                        map.put("sztickettypename",
                                "——" + map.get("sztickettypename").toString());
                        resultList.add(smap);
                        resultList.add(map);
                    }
                }
            }
            return resultList;
        }
        return list;
    }

    public List searchAddress(Long regionalid){
        List resultList = new ArrayList();
        String sql = "select new map(a.address as address) from Addresslink a where a.regionalid="+regionalid;
        List list = this.find(sql);
        if(list==null||list.isEmpty()){
            sql = "select new map(a.address as address) from Addresslink a,Galsourceregiontab g where a.regionalid=g.iparentid and g.iregionalid="+regionalid;
            list = this.find(sql);
        }
        if(list!=null&&!list.isEmpty()){
            Map map = (Map) list.get(0);
            String[] address = map.get("address").toString().split("\\s+");
            for(int i=0;i<address.length;i++){
                Map map2 = new HashMap();
                map2.put("address", address[i]);
                resultList.add(map2);
            }
        }
        return resultList;
    }

	public List getScenicListSelect(String providerType) {
		// TODO Auto-generated method stub "select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.byisuse =1  "
		
		String hql = "select new map(es.iscenicid as id,es.szscenicname as name) from Esbscenicareatab es where es.byisuse=1 and es.scenictype="+ providerType;
		
		List scenicList = this.find(hql);
		
		return scenicList;
	}

    
	public List getProviderList(String providerType, String keyword) {

		String hql = "select new map(pro.iscenicid as iscenicid, pro.szscenicname as szscenicname, pro.szgrade as szgrade)"+
						"from Esbscenicareatab pro where pro.byisuse=1 and pro.scenictype=" + providerType;
		
		if(!"".equals(keyword) && keyword != null){
			hql = hql + " and pro.szscenicname like '%" + keyword + "%' ";
		}
		
		
		List providerList = this.find(hql);
		
		 Sysparv5Id sysparid = new Sysparv5Id();
         Sysparv5 sysparv5 = null;
		
		for (Object object : providerList) {
			Map provider = (Map)object;
			
			if(provider.get("szgrade") != null && !"".equals(provider.get("szgrade"))){
	            sysparid.setPmcd(provider.get("szgrade").toString());
	            sysparid.setPmky("DENJ");
	            sysparv5 = (Sysparv5) this.get(Sysparv5.class, sysparid);
	            if (sysparv5 != null) {
	                provider.put("strgrade", sysparv5.getPmva().toString());
	            }
				
			}
			
			try {
				String string = provider.get("iscenicid").toString();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			// 图库
	        String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid=0 and pic.iscenicid="
	                + provider.get("iscenicid")+" order by pic.isecenicpictureid";
	        
	        List piclist = this.find(hsqls);
	        
	        String picUrl = "";
	        if (piclist != null && !piclist.isEmpty()) {
	        	Map map = (Map) piclist.get(0);
	        	
	        	picUrl = map.get("upadder").toString() + map.get("upfilename").toString();
			}
	        
	        provider.put("picUrl", picUrl);
		}
		
		return providerList;
	}

	@Override
	public List getProvidersByIds(String ids) {
		
		String sql = "select distinct new map(es.iscenicid as iscenicid, es.szscenicname AS szscenicname, es.scenictype AS scenictype) from Esbscenicareatab es where es.iscenicid in("+ ids +")";
		List list = this.find(sql);
		
		return list;
	}
	
}
