package com.ectrip.ticket.afcset.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.EsbaccessequiptabId;

public interface IEsbaccessequiptabService extends IGenericService{
	/**
	 * 添加准进设备
	 *
	 * @param esbaccessequip
	 */
	public void addAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
			throws Exception;

	/**
	 * 删除准进设备
	 *
	 * @param esbaccessequip
	 */
	public void delAccessequip(Long iaccessequipid,Syslog syslog)
			throws Exception;

	/**
	 * 更新准进设备
	 *
	 * @param esbaccessequip
	 */
	public void updateAccessequip(Esbaccessequiptab esbaccessequip,Syslog syslog)
			throws Exception;

	/**
	 * 根据ID获取准进设备
	 *
	 * @param id
	 * @return
	 */
	public Esbaccessequiptab getAccessequipById(Long iaccessequipid)
			throws Exception;

	/**
	 * 获取所有准进设备
	 *
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport getAccessequip(
			Long iscenicid, Long igardengateid, String scenics, int pageSize,
			int startIndex, String url) throws Exception;

	/**
	 * 获取最大ID
	 *
	 * @return 当前ID+1
	 */
	public Long getMaxId() throws Exception;

	/**
	 * 获得所有园门信息
	 *
	 * @return
	 */
	public String getGardenGateJSON(Long id) throws Exception;

	/**
	 * 查询准进设备
	 *
	 * @param id
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport searchAccessequip(EsbaccessequiptabId id,
											   int pageSize, int startIndex, String url) throws Exception;

	/**
	 * 查询所有属于id编号服务商的园门
	 *
	 * @param id
	 * @return
	 */
	public List getGardengateByPreId(Long id);

	/**
	 *
	 * Describe:查询出某个服务商的设备类型
	 * @auth:lijingrui
	 * @param iscenicid
	 * @return
	 * return:List
	 * Date:Nov 9, 2011
	 */
	public List getAllEsbequitype(Long iscenicid);

	/**
	 *
	 * Describe:判断在同一服务商下，售票窗口与准进设备的IP不能重复
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param szipaddress
	 * @return
	 * return:boolean
	 * Date:Nov 8, 2011
	 */
	public boolean getGardsxipaddress(Long iscenicid,String szipaddress);

	/**
	 *
	 * Describe:根据联合主键来查看准进设备
	 * @auth:lijingrui
	 * @param iaccessequipid
	 * @param iscenicid
	 * @param igardengateid
	 * @return
	 * return:Esbaccessequiptab
	 * Date:Nov 8, 2011
	 */
	public Esbaccessequiptab getviewquiptab(Long iaccessequipid,Long iscenicid,Long igardengateid);

	/**
	 *
	 * Describe:获得某个服务商的设备类型
	 * @auth:lijingrui
	 * @param iscenicid
	 * @return
	 * return:String
	 * Date:Nov 9, 2011
	 */
	public List getesbequiptypeJSON(Long iscenicid);

	/**
	 *
	 * Describe:显示服务商信息
	 * @auth:lijingrui
	 * @param scenics
	 * @return
	 * return:List
	 * Date:Nov 9, 2011
	 */
	public List findListesbticket(String scenics);
	/**
	 *
	 * Describe:判断该服务商下准进设备IP是否唯一
	 * @author:chenxinhao
	 * @param acc
	 * @return
	 * return:boolean
	 * Date:2012-11-29
	 */
	public boolean checkipAddress(Esbaccessequiptab acc);

	/**
	 *
	 * Describe:列表显示出设备状态信息
	 * @auth:lijingrui
	 * @param scenicids
	 * @param btype
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2013-2-21
	 */
	public PaginationSupport searchListAccessstatus(String scenicids,String btype,int pageSize, int startIndex, String url);

}
