package com.ectrip.ec.articlemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.articlemanager.dao.idao.IColumnManagerDao;
import com.ectrip.ec.articlemanager.service.iservice.IColumnManagerService;
import com.ectrip.ec.model.articlemanager.Columnmanagertab;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class ColumnManagerService extends GenericService implements IColumnManagerService {
	
	private IColumnManagerDao columnManagerDao;

	@Autowired
	public void setColumnManagerDao(IColumnManagerDao columnManagerDao) {
		this.columnManagerDao = columnManagerDao;
		super.setGenericDao(columnManagerDao);
	}
	/**
	 * *
	 * Describe:显示所有栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IColumnManagerService#showlistColumns(int, int, java.lang.String)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public PaginationSupport showlistColumns(int pageSize, int startInt,String url) {
		return this.columnManagerDao.showlistColumns(pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:添加栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IColumnManagerService#insertColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void insertColumn(Columnmanagertab column, Syslog syslog) {
		this.columnManagerDao.insertColumn(column, syslog);
	}
	/**
	 * *
	 * Describe:修改栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IColumnManagerService#updateColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void updateColumn(Columnmanagertab column, Syslog syslog) {
		this.columnManagerDao.updateColumn(column, syslog);
	}
	/**
	 * *
	 * Describe:删除栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IColumnManagerService#deleteColumn(com.ectrip.model.articlemanager.Columnmanagertab, com.ectrip.model.syspar.Syslog)
	 * @param column
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void deleteColumn(Columnmanagertab column, Syslog syslog) {
		this.columnManagerDao.deleteColumn(column, syslog);
	}
	/**
	 * *
	 * Describe:查看栏目
	 * @see com.ectrip.system.articlemanager.service.iservice.IColumnManagerService#viewColumn(java.lang.Long)
	 * @param cmid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-16
	 */
	public Columnmanagertab viewColumn(Long cmid){
		return this.columnManagerDao.viewColumn(cmid);
	}

	/**
	 * 获取栏目
	 * @return
	 */
	public Columnmanagertab getcolum(String topic){
		return this.columnManagerDao.getcolum(topic);
	}
	
}

