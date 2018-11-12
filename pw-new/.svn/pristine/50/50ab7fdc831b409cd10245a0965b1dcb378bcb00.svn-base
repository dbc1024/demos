package com.ectrip.ticket.afcset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbaccessequiptabDAO;
import com.ectrip.ticket.afcset.service.IEsbaccessequiptabService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.EsbaccessequiptabId;

/**
 * Created By JZhenhua,Time 2011-10-04 10:17
 * 
 * @author lenovo
 * 
 */
@Service
public class EsbaccessequiptabService extends GenericService implements IEsbaccessequiptabService {

	private IEsbaccessequiptabDAO esbaccessequiptabDAO;

	@Autowired
	public void setEsbaccessequiptabDAO(IEsbaccessequiptabDAO esbaccessequiptabDAO) {
		this.esbaccessequiptabDAO = esbaccessequiptabDAO;
		super.setGenericDao(esbaccessequiptabDAO);
	}

	/**
	 * 添加准进设备
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void addAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
			throws Exception {
		this.esbaccessequiptabDAO.addAccessequip(esbaccessequip,syslog);
	}

	/**
	 * 删除准进设备
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void delAccessequip(Long iaccessequipid,Syslog syslog)
			throws Exception {
		this.esbaccessequiptabDAO.delAccessequip(iaccessequipid,syslog);
	}

	/**
	 * 获取准进设备列表
	 */
	public PaginationSupport getAccessequip(
			Long iscenicid, Long igardengateid, String scenics, int pageSize,
			int startIndex, String url) throws Exception {
		return this.esbaccessequiptabDAO.getAccessequip(iscenicid, igardengateid, scenics,
				pageSize, startIndex, url);
	}

	/**
	 * 根据编号获取准进设备
	 */
	public Esbaccessequiptab getAccessequipById(Long iaccessequipid)
			throws Exception {
		return this.esbaccessequiptabDAO.getAccessequipById(iaccessequipid);
	}

	/**
	 * 获得所有园门JSON
	 */
	public String getGardenGateJSON(Long id) throws Exception {
		return this.esbaccessequiptabDAO.getGardenGateJSON(id);
	}

	/**
	 * 获取当前准进设备最大ID
	 */
	public Long getMaxId() throws Exception {
		return this.esbaccessequiptabDAO.getMaxId();
	}

	/**
	 * 更新准进设备
	 * 
	 * @param esbaccessequip
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
			throws Exception{
		this.esbaccessequiptabDAO.updateAccessequip(esbaccessequip,syslog);
	}

	/**
	 * 查询准进设备
	 */
	public PaginationSupport searchAccessequip(EsbaccessequiptabId id,
			int pageSize, int startIndex, String url) throws Exception {
		return this.esbaccessequiptabDAO.searchAccessequip(id, pageSize,
				startIndex, url);
	}
	
	/**
	 * 查询所有属于id编号服务商的园门
	 * 
	 * @param id
	 * @return
	 */
	public List getGardengateByPreId(Long id) {
		return this.esbaccessequiptabDAO.getGardengateByPreId(id);
	}

	/**
	 * *
	 * Describe:判断在同一服务商下，售票窗口与准进设备的IP不能重复
	 * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#getGardsxipaddress(java.lang.Long, java.lang.String)
	 * @param iscenicid
	 * @param szipaddress
	 * @return
	 * @author lijingrui
	 * Date:Nov 8, 2011
	 */
	public boolean getGardsxipaddress(Long iscenicid, String szipaddress) {
		return esbaccessequiptabDAO.getGardsxipaddress(iscenicid, szipaddress);
	}

	/**
	 * *
	 * Describe:根据联合主键来查看准进设备
	 * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#getviewquiptab(java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param iaccessequipid
	 * @param iscenicid
	 * @param igardengateid
	 * @return
	 * @author lijingrui
	 * Date:Nov 8, 2011
	 */
	public Esbaccessequiptab getviewquiptab(Long iaccessequipid,
			Long iscenicid, Long igardengateid) {
		return esbaccessequiptabDAO.getviewquiptab(iaccessequipid, iscenicid, igardengateid);
	}

	/**
	 * *
	 * Describe:获得某个服务商的设备类型
	 * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#getesbequiptypeJSON(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Nov 9, 2011
	 */
	public List getesbequiptypeJSON(Long iscenicid) {
		return esbaccessequiptabDAO.getesbequiptypeJSON(iscenicid);
	}
	
	/**
	 * *
	 * Describe:查询出某个服务商的设备类型
	 * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#getAllEsbequitype(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Nov 9, 2011
	 */
	public List getAllEsbequitype(Long iscenicid) {
		return esbaccessequiptabDAO.getAllEsbequitype(iscenicid);
	}

	/**
	 * *
	 * Describe:显示服务商信息
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketStationDAO#reviters()
	 * @return
	 * @author lijingrui
	 * Date:2011-9-30
	 */
	public List findListesbticket(String scenics) {
		return esbaccessequiptabDAO.findListesbticket(scenics);
	}
	/**
	 * 
	 * Describe:判断该服务商下准进设备IP是否唯一
	 * @author:chenxinhao
	 * @param acc
	 * @return
	 * return:boolean
	 * Date:2012-11-29
	 */
	public boolean checkipAddress(Esbaccessequiptab acc){
		return esbaccessequiptabDAO.checkipAddress(acc);
	}
	
	/**
	 * *
	 * Describe:列表显示出设备状态信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService#searchListAccessstatus(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param scenicids
	 * @param btype
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-2-21
	 */
	public PaginationSupport searchListAccessstatus(String scenicids,String btype,int pageSize, int startIndex, String url){
		return esbaccessequiptabDAO.searchListAccessstatus(scenicids, btype, pageSize, startIndex, url);
	}

}
