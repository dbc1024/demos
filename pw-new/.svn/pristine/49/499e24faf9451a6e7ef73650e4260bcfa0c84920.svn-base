package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Syslog;

public interface ISourceRegionService extends IGenericService {
	
	/**
	 * 查出根据的客源地列表
	 * @param prentid
	 * @param lever
	 * @param next
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 */
	public PaginationSupport listAllPage(Long prentid,String lever,String next,int page,int pagesize,String url);
	/**
	 * 根据客源地编号得到对象
	 * @param sourceid
	 * @return
	 */
	public Galsourceregiontab querySource(Long sourceid);
	
	/**
	 * 修改层序号
	 * @param ileverseq
	 */
	public void updateIleverseq(Long ilever,Long ileverseq,Long sourceid);
	/**
	 * 获取最大客源地编号
	 * @return
	 */
	public Long getMaxObjectId(String tablename,String colums);
	/**
	 * 获敢最大的层序号
	 * @return
	 */
	public Long getMaxIleverSeq(String tablename,String colums,String ilever);
	/**
	 * 判断它是否是最大层级
	 * Describe:
	 * @auth:huangyuqi
	 * @param soureid客源地编号
	 * @return
	 * return:boolean
	 * Date:2011-9-27
	 */
	public boolean retiveSource(Long soureid);
	/**
	 * 查询所有客源
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-27
	 */
	public List getAllSourceRegion();
	/**
	 * 根据上级查询下级客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param regionid
	 * @return
	 * return:List
	 * Date:2011-9-27
	 */
	public List SourceRegionJson(Long regionid);
	/**
	 * 增加客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param source
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void insertGalSoureceRegion(Galsourceregiontab source,Syslog syslog);
	
	/**
	 * 修改客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param source
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void updateGalSourceRegion(Galsourceregiontab source,Syslog syslog);
	/**
	 * 删除客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param soureceRegionId
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteGalSouceRegion(Long soureceRegionId,Syslog syslog);
	
	public List getAllAreas();
	public List getFourLeavel();
}
