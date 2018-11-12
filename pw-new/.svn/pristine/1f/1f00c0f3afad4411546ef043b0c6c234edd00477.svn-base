package com.ectrip.ec.articlemanager.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.articlemanager.Articlemanagertab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IArticleManagerDao extends IGenericDao {
	/**
	 * 
	 * Describe:显示所有栏目
	 * @author:chenxinhao
	 * @return
	 * return:List
	 * Date:2012-8-16
	 */
	public List showAllColumns();
	/**
	 * 
	 * Describe:显示所有文章信息
	 * @author:chenxinhao
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-16
	 */
	public PaginationSupport showlistArticles(Long cmid,int pageSize, int startInt, String url);
	public PaginationSupport showlistArticlesByGroupid(Long groupid,Long cmid,int pageSize, int startInt, String url);
	
	/**
	 * 首页搜索
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param str
	 * @return
	 */
	public PaginationSupport findlistArticles(Long cmid,int pageSize, int startInt, String url,String str);
	/**
	 * 
	 * Describe:添加文章
	 * @author:chenxinhao
	 * @param article
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void insertArticle(Articlemanagertab article,Syslog syslog);
	/**
	 * 
	 * Describe:删除文章
	 * @author:chenxinhao
	 * @param article
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void deleteArticle(Articlemanagertab article,Syslog syslog);
	/**
	 * 
	 * Describe:修改文章
	 * @author:chenxinhao
	 * @param article
	 * @param syslog
	 * return:void
	 * Date:2012-8-16
	 */
	public void updateArticle(Articlemanagertab article,Syslog syslog);
	/**
	 * 
	 * Describe:查看文章
	 * @author:chenxinhao
	 * @param amid
	 * @return
	 * @throws Exception
	 * return:Articlemanagertab
	 * Date:2012-8-16
	 */
	public Articlemanagertab viewArticle(Long amid)throws Exception;
	/**
	 * 
	 * Describe:根据栏目ID查找文章
	 * @author:chenxinhao
	 * @param cmid
	 * @return
	 * return:List
	 * Date:2012-8-16
	 */
	public List showAllArticles(Long cmid);
	/**
	 * 
	 * Describe:查找前几条文章
	 * @author:chenxinhao
	 * @param cmid
	 * @param num
	 * @return
	 * return:List
	 * Date:2012-8-16
	 */
	public List showAllArticles(Long cmid,int num);
	/**
	 * 
	 * Describe:查找前一篇文章
	 * @author:chenxinhao
	 * @param amid	当前文章ID
	 * @return
	 * @throws Exception
	 * return:Articlemanagertab
	 * Date:2012-8-16
	 */
	public Articlemanagertab preArticle(Long amid)throws Exception;
	/**
	 * 
	 * Describe:查找后一篇文章
	 * @author:chenxinhao
	 * @param amid	当前文章ID
	 * @return
	 * @throws Exception
	 * return:Articlemanagertab
	 * Date:2012-8-16
	 */
	public Articlemanagertab nextArticle(Long amid)throws Exception;


	/**
	 *
	 * Describe:查询指定栏目
	 * @author:chenxinhao
	 * @return
	 * return:List
	 * Date:2012-8-16
	 */
	public List queryColumn(String cmzhtopic);
	
	/**
	 * 根据groupid查询所有资讯+攻略
	 * @param groupid
	 * @return
	 */
	public PaginationSupport getArticlesByGroupid(Long groupid,Long cmid,int pageSize, int startInt, String url);
	
	public List getArticlesByGroupid(Long groupid,Long cmid,String url);
}

