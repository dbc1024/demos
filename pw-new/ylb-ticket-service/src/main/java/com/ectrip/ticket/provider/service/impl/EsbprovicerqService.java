package com.ectrip.ticket.provider.service.impl;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.provider.dao.IEsbprovicerqDAO;
import com.ectrip.ticket.provider.service.IEsbprovicerqService;
@Service
public  class EsbprovicerqService extends GenericService implements IEsbprovicerqService {

	private IEsbprovicerqDAO esbprovicerqDAO;
	public IEsbprovicerqDAO getEsbprovicerqDAO() {
		return esbprovicerqDAO;
	}
	public void setEsbprovicerqDAO(IEsbprovicerqDAO esbprovicerqDAO) {
		this.esbprovicerqDAO = esbprovicerqDAO;
	}
	
	/**
	 * *
	 * Describe:显示所有设置信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#checkListEsbprovicerq(java.lang.Long, int, int, java.lang.String)
	 * @param iscenicid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author huying
	 * Date:2015-3-26
	 */
	public PaginationSupport checkListEsbprovicerq(Long iscenicid,String scenics,int pageSize,int startIndex,String url){
		return esbprovicerqDAO.checkListEsbprovicerq(iscenicid, scenics,pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:新增
	 * @see com.ectrip.system.provider.dao.idao.IEsbprovicerqDAO#insertEsbprovicerq(com.ectrip.model.provider.Esbprovicerq, com.ectrip.model.syspar.Syslog)
	 * @param esbprovicer
	 * @param syslog
	 * @author huying
	 * Date:2015-3-27
	 */
	public void insertEsbprovice(Esbprovicerq esbprovice, Syslog syslog) {
		// TODO Auto-generated method stub
		Long seq = esbprovicerqDAO.getMaxPk("seq", "Esbprovicerq");
		esbprovice.setSeq(seq+1);
		esbprovicerqDAO.save(esbprovice);
		syslog.setStlg("0057");
		syslog.setBrief("业务类型编号：" + esbprovice.getIbusinessid());
		syslog.setNote("新增：" + esbprovice.getIscenicid() +"("+ esbprovice.getIbusinessid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = esbprovicerqDAO.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		esbprovicerqDAO.save(syslog);
	}

	/**
	 * *
	 * Describe:删除
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#deleteEsbprovice(java.lang.Long, com.ectrip.model.syspar.Syslog)
	 * @param iscenicid
	 * @param syslog
	 * @author huying
	 * Date:2015-3-28
	 */
	public void deleteEsbprovice(Long iscenicid, Syslog syslog) {
			// TODO Auto-generated method stub
			Esbprovicerq esbprovice=(Esbprovicerq)esbprovicerqDAO.get(Esbprovicerq.class, iscenicid);
			syslog.setStlg("0057");
			syslog.setBrief("业务类型编号：" + esbprovice.getIbusinessid());
			syslog.setNote("删除：" + esbprovice.getIscenicid() +"("+ esbprovice.getIbusinessid()+")");
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = esbprovicerqDAO.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			esbprovicerqDAO.save(syslog);
			esbprovicerqDAO.deleteByKey(Esbprovicerq.class, iscenicid);
	}
	
	/**
	 * *
	 * Describe:修改
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#updateEsbprovice(com.ectrip.model.provider.Esbprovicerq, com.ectrip.model.syspar.Syslog)
	 * @param esbprovice
	 * @param syslog
	 * @author huying
	 * Date:2015-3-28
	 */
	public void updateEsbprovice(Esbprovicerq esbprovice, Syslog syslog) {
		// TODO Auto-generated method stub
		
		esbprovicerqDAO.update(esbprovice);
		syslog.setStlg("0523");
		syslog.setBrief("业务类型编号：" + esbprovice.getIbusinessid() );
		syslog.setNote("修改：" + esbprovice.getIscenicid() +"("+ esbprovice.getIbusinessid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = esbprovicerqDAO.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		esbprovicerqDAO.save(syslog);
		
	}
	
	/**
	 * *
	 * Describe:判断是否存在
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#esbproviceIsuse(java.lang.Long, java.lang.Long)
	 * @param scenicid
	 * @param businessId
	 * @return
	 * @author huying
	 * Date:2015-3-28
	 */
	public boolean esbproviceIsuse(Long scenicid, Long businessId) {
		// TODO Auto-generated method stub
		return esbprovicerqDAO.esbproviceIsuse(scenicid, businessId);
	}
	
	/**
	 * *
	 * Describe:根据主键查询
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#queryEsbproviceById(java.lang.Long)
	 * @param seqLong
	 * @return
	 * @author huying
	 * Date:2015-3-28
	 * @throws Exception 
	 */
	public Esbprovicerq queryEsbproviceById(Long seqLong) throws Exception {
		// TODO Auto-generated method stub
		return esbprovicerqDAO.queryEsbproviceById(seqLong);
	}
}
