package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.provider.dao.IBusinessDAO;
import com.ectrip.ticket.provider.service.IBusinessService;

@Service
public class BusinessService implements IBusinessService {
	@Autowired
	private IBusinessDAO businessDao;
	/**
	 * 业务类型列表 
	 * Describe:
	 * @auth:huangyuqi
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url地址
	 * @param condition查询条件
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getBusinessAllList(int page,int pageSize,String url,String condition){
		return businessDao.getBusinessAllList(page, pageSize, url, condition);
	}
	/**
	 * 根据业务类型编号得到业务类型对象
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId
	 * @return
	 * return:Edmbusinesstab
	 * Date:2011-9-29
	 */
	public Edmbusinesstab queryBusiness(Long businessId){
		return businessDao.queryBusiness(businessId);
	}
	/**
	 *根据业务类型判断它是否被售价表用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean businessIsuse(Long businessId){
		return businessDao.businessIsuse(businessId);
	}
	
	/**
	 * 增加业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmbusinesstab
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void insertBusiness(Edmbusinesstab edmbusinesstab,Syslog syslog){
		businessDao.save(edmbusinesstab);
		
		syslog.setStlg("0055");
		syslog.setBrief("业务类型：" + edmbusinesstab.getSzbusinesscode() );
		syslog.setNote("业务类型增加：" + edmbusinesstab.getSzbusinessname() +"("+edmbusinesstab.getSzbusinesscode()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = businessDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		businessDao.save(syslog);
		
	}
	/**
	 * 修改业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmbusinesstab
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void updateBusiness(Edmbusinesstab edmbusinesstab,Syslog syslog){
		businessDao.update(edmbusinesstab);
		
		syslog.setStlg("0056");
		syslog.setBrief("业务类型：" + edmbusinesstab.getSzbusinesscode() );
		syslog.setNote("业务类型修改：" + edmbusinesstab.getSzbusinessname() +"("+edmbusinesstab.getSzbusinesscode()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = businessDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		businessDao.save(syslog);
	}
	/**
	 * 删除业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @param edmbusinesstab
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void deleteBusiness(Long businessId,Syslog syslog){
		
		Edmbusinesstab edmbusinesstab =(Edmbusinesstab)businessDao.get(Edmbusinesstab.class, businessId);
		
		syslog.setStlg("0057");
		syslog.setBrief("业务类型：" + edmbusinesstab.getSzbusinesscode() );
		syslog.setNote("业务类型删除：" + edmbusinesstab.getSzbusinessname() +"("+edmbusinesstab.getSzbusinesscode()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = businessDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		businessDao.save(syslog);
		
		businessDao.deleteByKey(Edmbusinesstab.class, businessId);
	}
	
	/**
	 * 查出所有业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List businessList(){	
		return businessDao.businessList();
	}


}

