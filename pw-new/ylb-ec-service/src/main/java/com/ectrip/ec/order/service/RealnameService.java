package com.ectrip.ec.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.order.dao.idao.IRealnameDao;
import com.ectrip.ec.order.service.iservice.IRealnameService;

@Service
public class RealnameService extends GenericService implements IRealnameService {
	
	IRealnameDao realnameDao;
	@Autowired
	public void setRealnameDao(IRealnameDao realnameDao) {
		this.realnameDao = realnameDao;
		super.setGenericDao(realnameDao);
	}
	/**
	 * *
	 * Describe:实名制列表
	 * @see com.ectrip.order.service.iservice.IRealnameService#realnameList(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param orid
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public PaginationSupport realnameList(String orid,String usid,int pageSize, int startIndex, String url){
		return this.realnameDao.realnameList(orid, usid, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:删除实名制信息
	 * @see com.ectrip.order.service.iservice.IRealnameService#delRealname(java.lang.Long)
	 * @param seq
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public void delRealname(Long seq){
		this.realnameDao.delRealname(seq);
	}
	/**
	 * *
	 * Describe:显示实名制信息
	 * @see com.ectrip.order.service.iservice.IRealnameService#showRealname(java.lang.Long)
	 * @param seq
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Map showRealname(Long seq) {
		return this.realnameDao.showRealname(seq);
	}
	/**
	 * *
	 * Describe:更新实名制
	 * @see com.ectrip.order.service.iservice.IRealnameService#updateRealname(com.ectrip.model.order.TRealname)
	 * @param trealname
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public void updateRealname(TRealname trealname) {
		this.realnameDao.updateRealname(trealname);
	}
	/**
	 * *
	 * Describe:计算需实名制的票数量
	 * @see com.ectrip.order.service.iservice.IRealnameService#countRealnames(java.lang.String, java.lang.String, java.lang.Long)
	 * @param orid
	 * @param usid
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Long countRealnames(String orid,String usid,Long iscenicid) {
		return this.realnameDao.countRealnames(orid,usid,iscenicid);
	}
	/**
	 * *
	 * Describe:计算已实名制的票数量
	 * @see com.ectrip.order.service.iservice.IRealnameService#countTickets(java.lang.String, java.lang.String)
	 * @param orid
	 * @param usid
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Long countTickets(String orid,String usid) {
		return this.realnameDao.countTickets(orid,usid);
	}
	/**
	 * *
	 * Describe:获取未实名制的列表
	 * @see com.ectrip.order.service.iservice.IRealnameService#newRealnames(java.lang.String, java.lang.String)
	 * @param orid
	 * @param usid
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public List<TOrderlist> newRealnames(String orid, String usid) {
		return this.realnameDao.newRealnames(orid, usid);
	}
	/**
	 * *
	 * Describe:保存实名制
	 * @see com.ectrip.order.service.iservice.IRealnameService#saveRealname(java.util.List)
	 * @param list
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public void saveRealname(List<TRealname> list) {
		this.realnameDao.saveRealname(list);
	}
	/**
	 * *
	 * Describe:订单中已实名制的信息
	 * @see com.ectrip.order.service.iservice.IRealnameService#realnameList(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param orid
	 * @param itickettypeid
	 * @param iscenicid
	 * @param icrowdkindid
	 * @return
	 * @author chenxinhao
	 * Date:2012-11-26
	 */
	public List<TRealname> realnameList(String orid,Long itickettypeid,Long iscenicid,Long icrowdkindid){
		return this.realnameDao.realnameList(orid, itickettypeid, iscenicid, icrowdkindid);
	}
	/**
	 * *
	 * Describe:2次支付保存实名制
	 * @see com.ectrip.order.service.iservice.IRealnameService#saveOrderRealname(java.util.List, java.lang.String)
	 * @param list
	 * @param orid
	 * @author chenxinhao
	 * Date:2012-11-26
	 */
	public void saveOrderRealname(List<TRealname> list,String orid){
		this.realnameDao.saveOrderRealname(list, orid);
	}
}

