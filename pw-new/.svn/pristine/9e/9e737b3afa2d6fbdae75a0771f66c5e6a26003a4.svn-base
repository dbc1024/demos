package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.ISourceRegionDAO;
import com.ectrip.sys.syspar.service.ISourceRegionService;

@Service
public class SoureceRegionService extends GenericService implements ISourceRegionService{
	
	private ISourceRegionDAO sourceRegionDao;
	@Autowired
	public void setSourceRegionDao(ISourceRegionDAO sourceRegionDao) {
		this.sourceRegionDao = sourceRegionDao;
		super.setGenericDao(sourceRegionDao);
	}
	
	
	
	public PaginationSupport listAllPage(Long prentid,String lever,String next,int page,int pagesize,String url){
		return sourceRegionDao.listAllPage( prentid, lever, next, page, pagesize, url);		
	}
	public Galsourceregiontab querySource(Long sourceid){
		return sourceRegionDao.querySource(sourceid);
	}
	
	public void updateIleverseq(Long ilever,Long ileverseq,Long sourceid){
		sourceRegionDao.updateIleverseq(ilever,ileverseq,sourceid);
	}
	
	
	public Long getMaxObjectId(String tablename,String colums){
		return sourceRegionDao.getMaxObjectId(tablename,colums);
	}
	
	/**
	 * 获敢最大的层序号
	 * @return
	 */
	public Long getMaxIleverSeq(String tablename,String colums,String ilever){
		return sourceRegionDao.getMaxIleverSeq(tablename,colums,ilever);
	}
	/**
	 * 判断它是否是最大层级*
	 * Describe:
	 * @see com.ectrip.system.syspar.service.iservice.ISourceRegionService#retiveSource(java.lang.Long)
	 * @param soureid
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public boolean retiveSource(Long soureid){
		
		return sourceRegionDao.retiveSource(soureid);
	}
	/**
	 * 得到客源地*
	 * Describe:
	 * @see com.ectrip.system.syspar.service.iservice.ISourceRegionService#getAllSourceRegion()
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public List getAllSourceRegion(){
		return sourceRegionDao.getAllSourceRegion();
	}
	/**
	 * 根据上级编号得到下级客源地*
	 * Describe:
	 * @see com.ectrip.system.syspar.service.iservice.ISourceRegionService#getSourceRegionJson(java.lang.Long)
	 * @param regionid
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public List SourceRegionJson(Long regionid){
		return sourceRegionDao.SourceRegionJson(regionid);
	}
	
	public List getAllAreas() {
		return sourceRegionDao.getAllAreas();
	}
	public List getFourLeavel() {
		// TODO Auto-generated method stub
		return sourceRegionDao.getFourLeavel();
	}
	
	/**
	 * 增加客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param source
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void insertGalSoureceRegion(Galsourceregiontab source,Syslog syslog){
		sourceRegionDao.save(source);
		
		syslog.setStlg("0032");
		syslog.setBrief("客源地：" + source.getSzregionalname() );
		syslog.setNote("客源地增加：" + source.getSzregionalname() +"("+source.getIregionalid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = sourceRegionDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sourceRegionDao.save(syslog);
		
	}
	
	/**
	 * 修改客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param source
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void updateGalSourceRegion(Galsourceregiontab source,Syslog syslog){
		sourceRegionDao.update(source);
		
		syslog.setStlg("0033");
		syslog.setBrief("客源地：" + source.getSzregionalname() );
		syslog.setNote("客源地修改：" + source.getSzregionalname() +"("+source.getIregionalid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = sourceRegionDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sourceRegionDao.save(syslog);
	}
	/**
	 * 删除客源地
	 * Describe:
	 * @auth:huangyuqi
	 * @param soureceRegionId
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteGalSouceRegion(Long soureceRegionId,Syslog syslog){
		
		Galsourceregiontab source = (Galsourceregiontab)sourceRegionDao.get(Galsourceregiontab.class, soureceRegionId);
		
		syslog.setStlg("0034");
		syslog.setBrief("客源地：" + source.getSzregionalname() );
		syslog.setNote("客源地删除：" + source.getSzregionalname() +"("+source.getIregionalid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = sourceRegionDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		sourceRegionDao.save(syslog);
		
		sourceRegionDao.delete(source);
		
	}
}
