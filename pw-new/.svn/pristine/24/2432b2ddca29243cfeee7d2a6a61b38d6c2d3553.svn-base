package com.ectrip.ec.order.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;

public interface IRealnameDao extends IGenericDao {
	/**
	 * 
	 * Describe:实名制列表
	 * @author:chenxinhao
	 * @param orid
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-10-23
	 */
	public PaginationSupport realnameList(String orid,String usid,int pageSize, int startIndex, String url);
	/**
	 * 
	 * Describe:删除实名制信息
	 * @author:chenxinhao
	 * @param seq
	 * return:void
	 * Date:2012-10-23
	 */
	public void delRealname(Long seq);
	/**
	 * 
	 * Describe:显示实名制信息
	 * @author:chenxinhao
	 * @param seq
	 * @return
	 * return:Map
	 * Date:2012-10-23
	 */
	public Map showRealname(Long seq);
	/**
	 * 
	 * Describe:更新实名制
	 * @author:chenxinhao
	 * @param trealname
	 * return:void
	 * Date:2012-10-23
	 */
	public void updateRealname(TRealname trealname);
	/**
	 * 
	 * Describe:计算需实名制的票数量
	 * @author:chenxinhao
	 * @param orid
	 * @param usid
	 * @return
	 * return:Long
	 * Date:2012-10-23
	 */
	public Long countTickets(String orid,String usid);
	/**
	 * 
	 * Describe:计算已实名制的票数量
	 * @author:chenxinhao
	 * @param orid
	 * @param usid
	 * @param iscenicid
	 * @return
	 * return:Long
	 * Date:2012-10-23
	 */
	public Long countRealnames(String orid,String usid,Long iscenicid);
	/**
	 * 
	 * Describe:获取未实名制的列表
	 * @author:chenxinhao
	 * @param orid
	 * @param usid
	 * @return
	 * return:List<TOrderlist>
	 * Date:2012-10-23
	 */
	public List<TOrderlist> newRealnames(String orid,String usid);
	/**
	 * 
	 * Describe:保存实名制
	 * @author:chenxinhao
	 * @param list
	 * return:void
	 * Date:2012-10-23
	 */
	public void saveRealname(List<TRealname> list);
	/**
	 * 
	 * Describe:订单中已实名制的信息
	 * @author:chenxinhao
	 * @param orid
	 * @param itickettypeid
	 * @param iscenicid
	 * @param icrowdkindid
	 * @return
	 * return:List<TRealname>
	 * Date:2012-11-26
	 */
	public List<TRealname> realnameList(String orid,Long itickettypeid,Long iscenicid,Long icrowdkindid);
	/**
	 * 
	 * Describe:2次支付保存实名制
	 * @author:chenxinhao
	 * @param list
	 * @param orid
	 * return:void
	 * Date:2012-11-26
	 */
	public void saveOrderRealname(List<TRealname> list,String orid);
}

