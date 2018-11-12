package com.ectrip.ticket.provider.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Jgfz;
import com.ectrip.ticket.provider.dao.IJgfzDAO;
import com.ectrip.ticket.provider.service.IJgfzService;

@Service
public class JgfzService extends GenericService implements IJgfzService{
	
	private IJgfzDAO jgfzDao;
	@Autowired
	public void setJgfzDao(IJgfzDAO jgfzDao) {
		this.jgfzDao = jgfzDao;
		setGenericDao(jgfzDao);
	}
	@Autowired
	private SysService sysService;
	@Autowired
	private EcService ecService;
	/**
	 * *
	 * Describe:显示出服务商价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#checkUpJgfz(java.lang.Long, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param iscenicid
	 * @param flag
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-4-14
	 */
	@SuppressWarnings("unchecked")
	public PaginationSupport checkUpJgfz(Long iscenicid,String flag,String query,int pageSize,int startIndex,String url){
		//用户信息参数
		Custom customParam = new Custom();
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("0")){
				if(query!=null&&!query.equals("")){
					customParam.setLname(query);
				}
			}else if(flag.equals("1")){
				if(query!=null&&!query.equals("")){
					
					customParam.setCorpname(query);
				}
			}
		}
		//获取用户Id
		List<?> customList = ecService.getCustomByCondition(customParam);
		//拼接用户id
		String userIds = null;
		if(customList != null && !customList.isEmpty()) {
			StringBuffer userIdsSb = new StringBuffer("(");
			for (int i = 0;i < customList.size();i++) {
				Custom _Custom = (Custom)customList.get(i);
				if(i == (customList.size()-1)) {
					userIdsSb.append(_Custom.getUsdj());
				}else {
					userIdsSb.append(_Custom.getUsdj()+",");
				}
			}
			userIdsSb.append(")");
			userIds = userIdsSb.toString();
		}
		PaginationSupport checkUpJgfz = jgfzDao.checkUpJgfz(iscenicid,userIds, pageSize, startIndex, url);
		//获取分页对象中list数据
		List<?> items = checkUpJgfz.getItems();
		if(items != null && !items.isEmpty()) {
			for (Object object : items) {
				Map<Object,Object> map = (Map<Object,Object>)object;
				String pmcd = (map.get("pmcd") == null)?null:map.get("pmcd").toString();
				String pmky = (map.get("pmky") == null)?null:map.get("pmky").toString();
				//远程查询系统服务系统参数信息
				Sysparv5 sysparv5 = sysService.findOne(pmky, pmcd);
				if(sysparv5 != null) {
					String pmva = sysparv5.getPmva();
					map.put("pmva", pmva);
				}
				String usid = (map.get("usid") == null)?null:map.get("usid").toString();
				//远程查询电商服务电商用户信息
				Custom custom = ecService.getCustomByUserId(usid);
				if(custom != null) {
					String corpname = custom.getCorpname();
					String lname = custom.getLname();
					map.put("corpname", corpname);
					map.put("lname", lname);
				}
			}
		}
		return checkUpJgfz;
	}

	/**
	 * *
	 * Describe:新增价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#addJgfz(com.ectrip.model.provider.Jgfz, com.ectrip.model.syspar.Syslog)
	 * @param jgfz
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void addJgfz(Jgfz jgfz,Syslog syslog){
		jgfzDao.addJgfz(jgfz, syslog);
	}
	
	/**
	 * *
	 * Describe:模糊查询用户
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#checkCustominfo(java.lang.String, int, int, java.lang.String)
	 * @param query
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2014-4-16
	 */
	public PaginationSupport checkCustominfo(String query,int pageSize,int startIndex,String url){
		return jgfzDao.checkCustominfo(query, pageSize, startIndex, url);
	}

	
	/**
	 * *
	 * Describe:修改价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#editJgfz(com.ectrip.model.provider.Jgfz, com.ectrip.model.syspar.Syslog)
	 * @param jgfz
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void editJgfz(Jgfz jgfz,Syslog syslog){
		jgfzDao.editJgfz(jgfz, syslog);
	}
	
	/**
	 * *
	 * Describe:删除价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#delJgfz(java.lang.Long, com.ectrip.model.syspar.Syslog)
	 * @param seq
	 * @param syslog
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public void delJgfz(Long seq,Syslog syslog){
		jgfzDao.delJgfz(seq,syslog);
	}
	
	/**
	 * *
	 * Describe:查看价格分组
	 * @see com.ectrip.system.provider.service.iservice.IJgfzService#viewJgfz(java.lang.Long)
	 * @param seq
	 * @return
	 * @author lijingrui
	 * Date:2014-4-15
	 */
	public Jgfz viewJgfz(Long seq) throws Exception {
		return jgfzDao.viewJgfz(seq);
	}
	
}

