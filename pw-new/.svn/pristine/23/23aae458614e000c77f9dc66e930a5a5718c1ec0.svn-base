package com.ectrip.ec.home.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.home.dao.idao.IHomeDao;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
/**
*
* @ClassName: HomeDao
* @Description: 首页相关-数据库连接接口实现类
* @author Dicky
* @date 2012-2-14 下午04:19:41
*
*/
@Repository
public class HomeDao extends GenericDao implements IHomeDao {

    /**
     * (非 Javadoc)
     * <p>Title: getProductViewList</p>
     * <p>Description: 网站首页搜索功能</p>
     * @param keyWord 搜索关键字
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getProductViewList(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getProductViewList(String keyWord, int pageSize,
                                                int startIndex, String url) {
        String hsql="select new map(ed.itickettypeid as itickettypeid,ed.sztickettypename as sztickettypename,ed.sztickettypecode as sztickettypecode,ed.bycategorytype as bycategorytype,ed.byuselimit as byuselimit,ed.byisuse as byisuse,es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Edmtickettypetab ed,Esbscenicareatab es where ed.iscenicid=es.iscenicid and ed.sztickettypename like '%"+keyWord+"%' or es.szscenicname like '%"+keyWord+"%'";
        return this.findPage(hsql, pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getHotelRecommend</p>
     * <p>Description: 推荐酒店 topSum </p>
     * @param topSum  显示几条信息
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#(int)
     */
    public List getHotelRecommend(String topSum,Long ibusinessid) {
        //查询酒店推荐  传入null则查全部
//		String sql="select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szscenicname as szscenicname,h.zxjb as zxjb,s.pmva as pmva) from Esbscenicareatab es,Hotelprovider h,Sysparv5 s  where es.iscenicid=h.iscenicid and s.id.pmcd=h.zxjb and s.id.pmky='HOTP' and es.scenictype='06' and h.inoteger1='0'";
        String sql="select new map(min(pric.mactualsaleprice) as price,prd.iscenicid as iscenicid,prd.szscenicname as szscenicname,prd.szaddress as address,s.pmva as pmva,prd.iordernumber as iordernumber) from Sysparv5 s,Edmcrowdkindpricetab pric,Edmtickettypetab prc,Esbscenicareatab prd where  prd.szgrade=s.id.pmcd and s.id.pmky='DENJ' and  prd.iscenicid=prc.iscenicid and prc.itickettypeid=pric.itickettypeid and pric.startdata <= '"+Tools.getDays()+"' and pric.enddata>='"+Tools.getDays()+"' and prd.scenictype='06' and pric.mactualsaleprice>0 and ibusinessid="+ibusinessid+" ";
        if(topSum==null || topSum.equals("")){
            topSum=String.valueOf(PaginationSupport.SIXSIZE);
        }
        sql+="group by prd.iscenicid,prd.szscenicname,prd.szaddress,s.pmva,prd.iordernumber order by prd.iordernumber desc";
        return this.findTopNumb(sql, Integer.parseInt(topSum));
    }

    public List getHotelRecommendIndex(String topSum,Long ibusinessid,String hotelIds) {
    	if("false".equals(hotelIds)){
    		return null;
    	}
        //查询酒店推荐  传入null则查全部
//		String sql="select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szscenicname as szscenicname,h.zxjb as zxjb,s.pmva as pmva) from Esbscenicareatab es,Hotelprovider h,Sysparv5 s  where es.iscenicid=h.iscenicid and s.id.pmcd=h.zxjb and s.id.pmky='HOTP' and es.scenictype='06' and h.inoteger1='0'";
        String sql="select new map(min(pric.mactualsaleprice) as price,prd.iscenicid as iscenicid,prd.szscenicname as szscenicname,prd.szaddress as address,s.pmva as pmva,prd.iordernumber as iordernumber) "
        		+ "from Sysparv5 s,Edmcrowdkindpricetab pric,Edmtickettypetab prc,Esbscenicareatab prd,Hotelproduct pd,ProviderCompany pro "
        		+ " where prc.itickettypeid = pd.itickettypeid AND  prd.szgrade=s.id.pmcd and s.id.pmky='DENJ' and pd.inoteger1 = 1 and prc.itickettypeid=pric.itickettypeid and prd.scenictype='06' "
        		+ " and pro.iscenicid=prc.iscenicid and  pro.type='02' and pro.status='1' "
        		+ " and pric.startdata <= '"+Tools.getDays()+"' and pric.enddata>='"+Tools.getDays()+"'"
        		+ " and pric.mactualsaleprice>0 and ibusinessid="+ibusinessid;
        if(hotelIds != null && !"".equals(hotelIds)){
        	sql += " and prd.iscenicid in ("+hotelIds+") ";
        }
        if(topSum==null || topSum.equals("")){
            topSum=String.valueOf(PaginationSupport.SIXSIZE);
        }
        sql+=" group by prd.iscenicid,prd.szscenicname,prd.szaddress,s.pmva,prd.iordernumber order by prd.iordernumber desc";
        return this.findTopNumb(sql, Integer.parseInt(topSum));
    }
    
    
    /**
     * *
     * Describe:推荐
     * @see com.ectrip.home.dao.idao.IHomeDao#searchListScenicid(java.lang.String, java.lang.String, java.lang.Long)
     * @param topSum
     * @param pdtp
     * @param ibusinessid
     * @return
     * @author lijingrui
     * Date:2014-8-23
     */
    public List searchListScenicid(String topSum,String pdtp,Long ibusinessid){
        String sql="select new map(min(pric.mactualsaleprice) as price,prd.iscenicid as iscenicid,prd.szscenicname as szscenicname,prd.szaddress as address,s.pmva as pmva,prd.iordernumber as iordernumber) from Sysparv5 s,Edmcrowdkindpricetab pric,Edmtickettypetab prc,Esbscenicareatab prd where  prd.szgrade=s.id.pmcd and s.id.pmky='DENJ' and  prd.iscenicid=prc.iscenicid and prc.itickettypeid=pric.itickettypeid and pric.startdata <= '"+Tools.getDays()+"' and pric.enddata>='"+Tools.getDays()+"' and prd.scenictype='"+pdtp+"' and pric.mactualsaleprice>0 and prd.byisuse=1 and ibusinessid="+ibusinessid+" ";
        if(pdtp!=null&&pdtp.equals("01")){
            sql+=" and prd.isjd=0 ";
        }
        if(topSum==null || topSum.equals("")){
            topSum=String.valueOf(PaginationSupport.SIXSIZE);
        }
        sql+="group by prd.iscenicid,prd.szscenicname,prd.szaddress,s.pmva,prd.iordernumber order by prd.iordernumber desc";
        return this.findTopNumb(sql, Integer.parseInt(topSum));

    }

    /**
     * (非 Javadoc)
     * <p>Title: getHotelPictures</p>
     * <p>Description: 获取所有服务商图片</p>
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getHotelPictures()
     */
    public List getHotelPictures() {
        String sql  = "select  new map(u.upadder as upadder,u.upfilename as upfilename,p.iscenicid as iscenicid,p.itickettypeid as itickettypeid)  from Upfile u,Secenicpicture p where  p.upid = u.upid and p.itickettypeid='0'   order by p.isecenicpictureid";
        return this.find(sql);
    }

    public List getHotelPictures(String iscenicid,String itickettypeid,int topnum) {
        String sql  = "select  new map(u.upadder as upadder,u.upfilename as upfilename,p.iscenicid as iscenicid,p.itickettypeid as itickettypeid)  from Upfile u,Secenicpicture p where  p.upid = u.upid";
        if(itickettypeid!=null&&!itickettypeid.equals("")){
            sql+=" and p.itickettypeid='"+itickettypeid+"'";
        }
        sql+=" and p.iscenicid="+iscenicid+"    order by p.isecenicpictureid";

        return this.findTopNumb(sql,topnum);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getTravelAgency</p>
     * <p>Description: 获取推荐旅行社 </p>
     * @param topSum
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getTravelAgency(java.lang.String)
     */
    public List getTravelAgency(String topSum) {
        StringBuffer sql=new StringBuffer("select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname)from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP' and es.scenictype='07'");
        sql.append(" order by  iordernumber asc");
        return this.findTopNumb(sql.toString(),Integer.parseInt(topSum));
    }
    /**
     * (非 Javadoc)
     * <p>Title: getAttractions</p>
     * <p>Description:武夷山景点 </p>
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#()
     */
    public List getAttractions(String rootid) {
        String sql="select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from Esbscenicareatab es where es.scenictype='01' and es.irootid='"+rootid+"' and es.isjd= '1'";
        return this.find(sql);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getScenices</p>
     * <p>Description: 武夷山景区</p>
     * @param topSum
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getScenices(java.lang.String)
     */
    public List getScenices(String topSum) {
        String sql="select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname,s.pmva as pmva) from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP'and es.scenictype='01' and es.isjd= '0' and es.ilevel='1' and es.byisuse = 1 order by es.iordernumber desc";
        if(topSum!=null && !topSum.equals("")){
            return this.findTopNumb(sql,Integer.parseInt(topSum));
        }
        return this.find(sql);
    }

    /**
     * (非 Javadoc)
     * <p>Title: getScenices</p>
     * <p>Description: 武夷山景区</p>
     * @param topSum
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getScenices(java.lang.String)
     */
    public List getTypeScenices(String topSum,String type) {
        List list = new ArrayList();
        String sql="select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname,s.pmva as pmva) from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP'and es.scenictype='"+type+"' and es.isjd= '0' and es.ilevel='1' and es.byisuse = 1 order by es.iordernumber desc";
        if(topSum!=null && !topSum.equals("")){
            list = this.findTopNumb(sql,Integer.parseInt(topSum));
        }else{
            list=this.find(sql);
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                List plist = this.getHotelPictures(map.get("iscenicid").toString(),null, 1);
                if(plist!=null&&plist.size()>0){
                    Map pmap = (Map) plist.get(0);
                    map.put("picaddress", pmap.get("upadder").toString());
                    map.put("picname", pmap.get("upfilename").toString());
                }else{
                    map.put("picname","");
                    map.put("picaddress", "");
                }
            }
        }
        return list;
    }
    /**
     * (非 Javadoc)
     * <p>Title: getAllCategory</p>
     * <p>Description:商品分类 </p>
     * @param scenictype
     * @param isjd
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getAllCategory(java.lang.String, java.lang.String)
     */
    public List getAllCategory(String scenictype, String isjd) {
        String sql="select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname,s.pmva as pmva) from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP'and es.scenictype='"+scenictype+"'";
        if(isjd!=null && !isjd.equals("")){
            sql+= " and es.isjd = '"+isjd+"'";
        }
        return this.find(sql);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getAllCategoryOfHotel</p>
     * <p>Description: 商品分类 of  酒店 按照星级</p>
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getAllCategoryOfHotel()
     */
    public List getAllCategoryOfHotel() {
//		String sql="select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,s.pmva as pmva) from Esbscenicareatab es,Sysparv5 s where es.szgrade=s.id.pmcd and s.id.pmky='DENJ'and es.scenictype='06'";
        String sql="select new map(min(pric.mactualsaleprice) as price,prd.iscenicid as iscenicid,prd.szscenicname as szscenicname,prd.szaddress as address,s.pmva as pmva) from Sysparv5 s,Edmcrowdkindpricetab pric,Edmtickettypetab prc,Esbscenicareatab prd where  prd.szgrade=s.id.pmcd and s.id.pmky='DENJ' and  prd.iscenicid=prc.iscenicid and prc.itickettypeid=pric.itickettypeid and pric.startdata <= '"+Tools.getDays()+"' and pric.enddata>='"+Tools.getDays()+"' and prd.scenictype='06' and pric.mactualsaleprice>0 group by prd.iscenicid,prd.szscenicname,prd.szaddress,s.pmva";
        return this.find(sql);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getHotelStars</p>
     * <p>Description: 获取酒店星级</p>
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getHotelStars()
     */
    public List getHotelStars() {
        String sql="select distinct new map(s.pmva as pmva,s.id.pmcd as pmcd) from Esbscenicareatab es,Sysparv5 s where es.szgrade=s.id.pmcd and s.id.pmky='DENJ'and es.scenictype='06'";
        return this.find(sql);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getEdmtickettypetab</p>
     * <p>Description:搜索后单个产品信息显示 </p>
     * @param id
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getEdmtickettypetab(java.lang.String)
     */
    public Edmtickettypetab getEdmtickettypetab(String id) {
        return (Edmtickettypetab) this.get(Edmtickettypetab.class, Long.parseLong(id));
    }
    /**
     * (非 Javadoc)
     * <p>Title: getEsbscenicareatab</p>
     * <p>Description: 获取服务商</p>
     * @param id
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getEsbscenicareatab(java.lang.String)
     */
    public Esbscenicareatab getEsbscenicareatab(String id) {
        Esbscenicareatab esbscen=(Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(id));
        // 查询服务商图片
        String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
                + id + " and p.itickettypeid=0 and p.upid = u.upid ";
        List piclist = this.find(sql);
        esbscen.setPiclist(piclist);
        return esbscen;
    }
    /**
     * (非 Javadoc)
     * <p>Title: getHotelRecommend</p>
     * <p>Description: 酒店推荐 more </p>
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getHotelRecommend(int, int, java.lang.String)
     */
    public PaginationSupport getHotelRecommend(int pageSize, int startIndex,
                                               String url) {
        String sql="select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szsceniccode as szsceniccode,es.szphone as szphone,es.szcontact as szcontact,es.szsimpleintroduction as szsimpleintroduction,es.szscenicname as szscenicname,h.zxjb as zxjb,s.pmva as pmva) from Esbscenicareatab es,Hotelprovider h,Sysparv5 s  where es.iscenicid=h.iscenicid and s.id.pmcd=h.zxjb and s.id.pmky='HOTP' and es.scenictype='06' and h.inoteger1='0'";
        return this.findPage(sql, pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getTravelAgency</p>
     * <p>Description: 旅行社推荐 more </p>
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getTravelAgency(int, int, java.lang.String)
     */
    public PaginationSupport getTravelAgency(int pageSize, int startIndex,
                                             String url) {
        StringBuffer sql=new StringBuffer("select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szsceniccode as szsceniccode,es.szphone as szphone,es.szcontact as szcontact,es.szsimpleintroduction as szsimpleintroduction,es.szscenicname as szscenicname,es.szaddress as szaddress,s.pmva as pmva)from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP' and es.scenictype='07'");
        sql.append(" order by  iordernumber asc");
        return this.findPage(sql.toString(), pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getScenices</p>
     * <p>Description: 武夷山景区 more</p>
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getScenices(int, int, java.lang.String)
     */
    public PaginationSupport getScenices(int pageSize, int startIndex,
                                         String url) {
        String sql="select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szsceniccode as szsceniccode,es.szphone as szphone,es.szcontact as szcontact,es.szsimpleintroduction as szsimpleintroduction,es.szscenicname as szscenicname,es.szaddress as szaddress,s.pmva as pmva) from Esbscenicareatab es,Sysparv5 s where es.scenictype=s.id.pmcd and s.id.pmky='PDTP'and es.scenictype='01' and es.isjd= '0' and es.ilevel='1'";
        return this.findPage(sql.toString(), pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getAttractions</p>
     * <p>Description: 景区下景点信息</p>
     * @param rootid
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.home.dao.idao.IHomeDao#getAttractions(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getAttractions(String rootid, int pageSize,
                                            int startIndex, String url) {
        String sql="select new map(es.iscenicid as iscenicid,es.scenictype as scenictype,es.szsceniccode as szsceniccode,es.szphone as szphone,es.szcontact as szcontact,es.szsimpleintroduction as szsimpleintroduction,es.szscenicname as szscenicname,es.szaddress as szaddress) from Esbscenicareatab es where es.scenictype='01' and es.irootid='"+rootid+"' and es.isjd= '1'";
        return this.findPage(sql.toString(), pageSize, startIndex, url);
    }



    public List getTopTravelLine(int topnum,int daynum){
        String hql="select new map(proc.itickettypeid as lineid,proc.sztickettypename as linename,lp.datanumber as daynum,min(pric.mactualsaleprice) as price,lp.destination as destination,lp.departure as departure) from Edmtickettypetab proc,Lineproduct lp,Edmcrowdkindpricetab pric where proc.bycategorytype = '0007' and proc.byisuse = 1 and lp.datanumber = "+daynum+" and proc.itickettypeid = pric.itickettypeid and lp.itickettypeid = pric.itickettypeid and pric.startdata <='"+Tools.getDays()+"' and pric.enddata >= '"+Tools.getDays()+"' and pric.isnet=1 group by proc.sztickettypename, lp.datanumber, proc.itickettypeid,lp.destination,lp.departure";
        return this.findTopNumb(hql, topnum);
    }


    public List getTopCommnet(int topnum){
        String hql="select new map(cm.usid as usid,cm.oeid as oeid,cm.ctitle as ctitle,cm.note as note,cm.createdate as createdate,cm.fenjingnum as fenjingnum,cm.xinjianum as xinjianum,cm.createdate as commenttime,cm.createdate as createdate,cm.remarknum as remarknum,cm.ptype as ptype) from Hscomment cm  where cm.status='01' and cm.ptype in ('01','02') order by cm.createdate desc";
        return findTopNumb(hql, topnum);
    }


    public List getTopGoods(int top){
        String hql="select new map(proc.itickettypeid as productid,proc.sztickettypename as goodsname, pric.mactualsaleprice as price,pric.listingprice as listingprice,proc.iscenicid as iscenicid) from Edmtickettypetab proc,Edmcrowdkindpricetab pric where proc.bycategorytype in(select id.pmcd  from Sysparv5 where pmvb='08') and proc.byisuse = 1 and  proc.itickettypeid = pric.itickettypeid and pric.startdata <='"+Tools.getDays()+"' and pric.enddata >= '"+Tools.getDays()+"' and pric.isnet=1 and pric.ibusinessid='01' order by proc.isequence desc";
        return findTopNumb(hql, top);
    }


    public List getGoodsType(String spmcd,String pmvb,String pmcd,String pmky){
        StringBuffer hql=new StringBuffer("select new map(id.pmky as pmky,id.pmcd as pmcd,spmcd as spmcd,systp as systp,pmva as pmva,pmvb as pmvb) from sysparv5 where  pmky='"+pmky+"'");
        if(spmcd!=null&&!spmcd.equals("")){
            hql.append(" and spmcd='"+spmcd+"'");
        }
        if(pmvb!=null&&!pmvb.equals("")){
            hql.append(" and pmvb='"+pmvb+"'");
        }

        if(pmcd!=null&&!pmcd.equals("")){
            hql.append(" and id.pmcd='"+pmcd+"'");
        }
        return find(hql.toString());
    }

    public List getHotelProvider(List listScenics,boolean isHqyatu) {
        String sql="select s.iscenicid as iscenicid from Hotelprovider  s,ProviderCompany pro where 1=1 and pro.iscenicid=s.iscenicid and pro.type='02' and pro.status='1'";
        StringBuffer buffer = new StringBuffer();
        if(!isHqyatu){
        	for (Object obj : listScenics) {
            	buffer.append(" and  (s.noted6='"+obj+"' or s.noted6 like '%"+obj+",%' or s.noted6 like '%,"+obj+"%' ) " );        	
    		}
        }
        sql += buffer.toString();
        return this.find(sql);              
    }


}
