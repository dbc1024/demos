package com.ectrip.ec.articlemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.articlemanager.dao.idao.IArticleManagerDao;
import com.ectrip.ec.articlemanager.service.iservice.IArticleManagerService;
import com.ectrip.ec.model.articlemanager.Articlemanagertab;
import com.ectrip.sys.model.syspar.Syslog;


@Service
public class ArticleManagerService extends GenericService implements IArticleManagerService {
	private IArticleManagerDao articleManagerDao;
	
	@Autowired
	public void setArticleManagerDao(IArticleManagerDao articleManagerDao) {
		this.articleManagerDao = articleManagerDao;
	}
	/**
	 * *
	 * Describe:显示所有栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#showAllColumns()
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List showAllColumns() {
		return this.articleManagerDao.showAllColumns();
	}
	/**
	 * *
	 * Describe:显示所有文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#showlistArticles(int, int, java.lang.String)
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public PaginationSupport showlistArticles(Long cmid,int pageSize, int startInt, String url) {
		return this.articleManagerDao.showlistArticles(cmid,pageSize, startInt, url);
	}
	public PaginationSupport showlistArticlesByGroupid(Long groupid,Long cmid,int pageSize, int startInt, String url){
		return this.articleManagerDao.showlistArticlesByGroupid(groupid, cmid, pageSize, startInt, url);
	}
	/**
	 * 主页搜索
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param str
	 * @return
	 */
	public PaginationSupport findlistArticles(Long cmid,int pageSize, int startInt, String url,String str) {
		return this.articleManagerDao.findlistArticles(cmid,pageSize, startInt, url,str);
	}
	/**
	 * *
	 * Describe:添加文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#insertArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void insertArticle(Articlemanagertab article, Syslog syslog) {
		this.articleManagerDao.insertArticle(article, syslog);
	}
	/**
	 * *
	 * Describe:删除文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#deleteArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void deleteArticle(Articlemanagertab article, Syslog syslog) {
		this.articleManagerDao.deleteArticle(article, syslog);
	}
	/**
	 * *
	 * Describe:修改文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#updateArticle(com.ectrip.model.articlemanager.Articlemanagertab, com.ectrip.model.syspar.Syslog)
	 * @param article
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updateArticle(Articlemanagertab article, Syslog syslog) {
		this.articleManagerDao.updateArticle(article, syslog);
	}
	/**
	 * *
	 * Describe:查看文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#viewArticle(java.lang.Long)
	 * @param amid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab viewArticle(Long amid) throws Exception{
		return this.articleManagerDao.viewArticle(amid);
	}
	/**
	 * *
	 * Describe:根据栏目ID查找文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#showAllArticles(java.lang.Long)
	 * @param cmid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List showAllArticles(Long cmid) {
		return this.articleManagerDao.showAllArticles(cmid);
	}
	
	/**
	 * *
	 * Describe:查找前几条文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#showAllArticles(java.lang.Long, int)
	 * @param cmid
	 * @param num
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List showAllArticles(Long cmid, int num) {
		return this.articleManagerDao.showAllArticles(cmid, num);
	}

	/**
	 * *
	 * Describe:查找前一篇文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#preArticle(java.lang.Long)
	 * @param amid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab preArticle(Long amid) throws Exception {
		return this.articleManagerDao.preArticle(amid);
	}
	/**
	 * *
	 * Describe:查找后一篇文章
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#nextArticle(java.lang.Long)
	 * @param amid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Articlemanagertab nextArticle(Long amid) throws Exception {
		return this.articleManagerDao.nextArticle(amid);
	}

	/**
	 * *
	 * Describe:查询指定栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IArticleManagerService#showAllColumns()
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public List queryColumn(String cmzhtopic) {
		return this.articleManagerDao.queryColumn(cmzhtopic);
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
		return this.articleManagerDao.getArticlesByGroupid(groupid,cmid,pageSize,startInt, url);
	}
	
	public List getArticlesByGroupid(Long groupid,Long cmid,String url){
		return this.articleManagerDao.getArticlesByGroupid(groupid, cmid, url);
	}
	
}	

