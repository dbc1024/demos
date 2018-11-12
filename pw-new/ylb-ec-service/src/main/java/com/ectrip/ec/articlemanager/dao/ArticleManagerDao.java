package com.ectrip.ec.articlemanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.articlemanager.dao.idao.IArticleManagerDao;
import com.ectrip.ec.model.articlemanager.Articlemanagertab;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class ArticleManagerDao extends GenericDao implements IArticleManagerDao {
	/**
	 * *
	 * Describe:显示所有栏目
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#showAllColumns()
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List showAllColumns(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select new map(co.cmid as cmid,co.cmzhtopic as cmzhtopic) from Columnmanagertab co where co.byisuse = 1");
		List list = this.find(sql.toString());
		return list;
	}
	/**
	 * *
	 * Describe:显示所有文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#showlistArticles(Long,int, int, java.lang.String)
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 * ChaoYuHang 2012-08-30 Edit
	 * 增加分页功能
	 */
	public PaginationSupport showlistArticles(Long cmid,int pageSize, int startInt, String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(am.amid as amid,am.amtopicf as amtopicf,am.amtopics as amtopics,am.amdesc as amdesc,cm.cmentopic as cmentopic," +
				" am.byisuse as byisuse,am.dtmakedate as dtmakedate,am.groupid as groupid,cm.cmzhtopic as cmzhtopic,am.cmid as cmid) " +
				" from Articlemanagertab am,Columnmanagertab cm where am.cmid = cm.cmid");
		if(cmid!=0){
			hsql.append(" and am.cmid="+cmid);
		}
		hsql.append(" order by am.dtmakedate desc");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	/**
	 * 文章管理根据域名过滤文章
	 */
	public PaginationSupport showlistArticlesByGroupid(Long groupid, Long cmid, int pageSize, int startInt, String url){
		
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map("
				+ " am.amid as amid, am.amtopicf as amtopicf, am.amtopics as amtopics, am.amdesc as amdesc,"
				+ " am.byisuse as byisuse, am.dtmakedate as dtmakedate, am.groupid as groupid, am.cmid as cmid, "
				+ " cm.cmzhtopic as cmzhtopic,  cm.cmentopic as cmentopic,"
				+ " dm.domainUrl as domainurl"
				+ " )"
				+ " from Articlemanagertab am, Columnmanagertab cm, Domain dm "
				+ " where am.cmid=cm.cmid and am.groupid=dm.groupId and dm.type='1' ");
		
		if(null != groupid){
			hsql.append(" and am.groupid="+groupid);
		}
		if(cmid!=0){
			hsql.append(" and am.cmid="+cmid);
		}
		hsql.append(" order by am.dtmakedate desc");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	
	/**
	 * 首页搜索
	 */
	public PaginationSupport findlistArticles(Long cmid,int pageSize, int startInt, String url,String str){
		StringBuffer hsql = new StringBuffer();
		
		System.out.println(str);
		
		hsql.append(" select distinct new map(am.amid as amid,am.amtopicf as amtopicf,am.amtopics as amtopics,am.amdesc as amdesc,cm.cmentopic as cmentopic," +
				" am.byisuse as byisuse,am.dtmakedate as dtmakedate,cm.cmzhtopic as cmzhtopic,am.cmid as cmid) " +
				" from Articlemanagertab am,Columnmanagertab cm where am.cmid = cm.cmid");
		if(str.length()!= 0){
			hsql.append(" and ( am.amtopicf like '%"+str+"%'"+" or am.amnote like '%"+str+"%') and (cm.cmzhtopic='旅游资讯' or cm.cmzhtopic='旅游资讯推荐')");
		}
		System.out.println(hsql);
//		hsql.append(" GROUP BY am.amtopicf");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:添加文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#insertArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article	
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void insertArticle(Articlemanagertab article,Syslog syslog){
		Long maxid = this.getMaxPk("amid", "Articlemanagertab");
		article.setAmid(maxid+1);
		article.setDtmakedate(Tools.getDayTimes());
		this.save(article);
		
//		syslog.setStlg("0229");
//		syslog.setBrief("新增文章名称："+article.getAmtopicf());
//		syslog.setNote("新增文章名称："+article.getAmtopicf()+",时间："+article.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:删除文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#deleteArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void deleteArticle(Articlemanagertab article,Syslog syslog){
		Articlemanagertab art = (Articlemanagertab) this.get(Articlemanagertab.class, article.getAmid());
		this.delete(art);
		
//		syslog.setStlg("0230");
//		syslog.setBrief("删除文章名称："+article.getAmtopicf());
//		syslog.setNote("删除文章名称："+article.getAmtopicf()+",时间："+article.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:修改文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#updateArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public void updateArticle(Articlemanagertab article,Syslog syslog){
		Articlemanagertab art = (Articlemanagertab) this.get(Articlemanagertab.class, article.getAmid());
		art.setAmtopicf(article.getAmtopicf());
		art.setAmtopics(article.getAmtopics());
		art.setCmid(article.getCmid());
		art.setAmdesc(article.getAmdesc());
		art.setAmnote(article.getAmnote());
		art.setByisuse(article.getByisuse());
		art.setDtmakedate(Tools.getDayTimes());
		this.update(art);
		
//		syslog.setStlg("0231");
//		syslog.setBrief("修改文章名称："+article.getAmtopicf());
//		syslog.setNote("修改文章名称："+article.getAmtopicf()+",时间："+article.getDtmakedate());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	/**
	 * *
	 * Describe:查看文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#viewArticle(java.lang.Long)
	 * @param amid	文章编号
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab viewArticle(Long amid) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select new map("
				+ " am.amid as amid, am.amtopicf as amtopicf, am.amtopics as amtopics, am.amdesc as amdesc, am.amnote as amnote,"
				+ " am.byisuse as byisuse, am.dtmakedate as dtmakedate, am.cmid as cmid, "
				+ " cm.cmzhtopic as cmzhtopic, cm.cmentopic as cmentopic"
				+ " )"
				+ " from Articlemanagertab am, Columnmanagertab cm "
				+ " where am.cmid=cm.cmid and am.amid="+ amid);

		
		List list = this.find(sql.toString());
		if(list!=null&&list.size()>0){
			Articlemanagertab article = new Articlemanagertab();
			BeanUtils.populate(article,(Map) list.get(0));
			return article;
		}else{
			return null;
		}	
	}
	/**
	 * *
	 * Describe:根据栏目ID查找文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#showAllArticles(java.lang.Long)
	 * @param cmid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List showAllArticles(Long cmid){
		StringBuffer sql = new StringBuffer();
		sql.append(" from Articlemanagertab art where art.byisuse = 1");
		if(cmid!=null&&cmid!=0){
			sql.append(" and art.cmid ="+cmid);
		}
		sql.append(" order by art.dtmakedate desc");
		List list = this.find(sql.toString());
		return list;
	}
	/**
	 * 
	 * Describe:查找前几条记录
	 * @author:chenxinhao
	 * @param cmid
	 * @param num
	 * @return
	 * return:List
	 * Date:2012-8-16
	 * ChaoYuHang 2012-08-30 Edit
	 * 增加日期显示
	 */
	public List showAllArticles(Long cmid,int num){
		StringBuffer sql = new StringBuffer();
		sql.append(" from Articlemanagertab art where art.byisuse = 1");
		if(cmid!=null&&cmid!=0){
			sql.append(" and art.cmid ="+cmid);
		}
		sql.append(" order by art.dtmakedate desc");
		List list = this.findTopNumb(sql.toString(), num);
		return list;
	}
	/**
	 * *
	 * Describe:查找前一篇文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#preArticle(java.lang.Long)
	 * @param amid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab preArticle(Long amid)throws Exception{
		Articlemanagertab article = (Articlemanagertab) this.get(Articlemanagertab.class, amid);
		String sql = " select max(art.amid) from Articlemanagertab art where art.amid <"+amid+" and art.cmid= "+article.getCmid()+" and art.byisuse = 1 order by art.amid";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Long id = (Long) list.get(0);
			Articlemanagertab art = new Articlemanagertab();
			art = this.viewArticle(id);
			return art;
		}else{
			return null;
		}
	}
	/**
	 * *
	 * Describe:查找后一篇文章
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#nextArticle(java.lang.Long)
	 * @param amid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab nextArticle(Long amid)throws Exception{
		Articlemanagertab article = (Articlemanagertab) this.get(Articlemanagertab.class, amid);
		String sql = " select min(art.amid) from Articlemanagertab art where art.amid >"+amid+" and art.cmid= "+article.getCmid()+" and art.byisuse = 1 order by art.amid";
		List list = this.find(sql);
		if(list!=null&&list.size()>0){
			Long id = (Long) list.get(0);
			Articlemanagertab art = new Articlemanagertab();
			art = this.viewArticle(id);
			return art;
		}else{
			return null;
		}
	}


	/**
	 * *
	 * Describe:查询制定栏目
	 * @see com.ectrip.system.articlemanager.dao.idao.IArticleManagerDao#queryColumn(String)
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List queryColumn(String cmzhtopic){
		StringBuffer sql = new StringBuffer();
		sql.append(" select new map(co.cmid as cmid,co.cmzhtopic as cmzhtopic) from Columnmanagertab co where co.cmzhtopic='"+cmzhtopic+"'");
		List list = this.find(sql.toString());
		return list;
	}
	
	/**
	 * 根据groupid查询所有资讯+攻略
	 * @param groupid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 */
	public PaginationSupport getArticlesByGroupid(Long groupid,Long cmid,int pageSize, int startInt, String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(am.amid as amid,am.amtopicf as amtopicf,am.amtopics as amtopics,am.amdesc as amdesc,cm.cmentopic as cmentopic," +
				" am.byisuse as byisuse,am.amnote as amnote,am.dtmakedate as dtmakedate,cm.cmzhtopic as cmzhtopic,am.cmid as cmid) " +
				" from Articlemanagertab am,Columnmanagertab cm where am.cmid = cm.cmid");
		if(null != groupid){
			hsql.append(" and am.groupid="+groupid);
		}
		if(null != cmid){
			hsql.append(" and am.cmid="+cmid);
		}
		hsql.append(" order by am.dtmakedate desc");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	
	public List getArticlesByGroupid(Long groupid,Long cmid,String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(am.amid as amid,am.amtopicf as amtopicf,am.amtopics as amtopics,am.amdesc as amdesc,cm.cmentopic as cmentopic," +
				" am.amnote as amnote ,am.byisuse as byisuse,am.dtmakedate as dtmakedate,cm.cmzhtopic as cmzhtopic,am.cmid as cmid) " +
				" from Articlemanagertab am,Columnmanagertab cm where am.cmid = cm.cmid");
		if(null != groupid){
			hsql.append(" and am.groupid="+groupid);
		}
		if(null != cmid){
			hsql.append(" and am.cmid="+cmid);
		}
		hsql.append(" order by am.dtmakedate desc");
		List list = this.find(hsql.toString());
		return list;
	}
}

