package com.ectrip.sys.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.sys.employee.dao.IESPPostsDutyTabDao;
import com.ectrip.sys.employee.service.IESPPostsDutyTabService;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.employee.Esppostsbtndutytab;
import com.ectrip.sys.model.employee.Esppostsdutytab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class ESPPostsDutyTabService extends GenericService implements IESPPostsDutyTabService {
	
	private IESPPostsDutyTabDao dupsDao;
	@Autowired
	public void setDupsDao(IESPPostsDutyTabDao dupsDao) {
		this.dupsDao = dupsDao;
		setGenericDao(dupsDao);
	}

	/**
	 * 显示 岗位与职责的关联
	 */
	public List<Espdutytab> getAllpost(String posttype) {
		return dupsDao.getAllpost(posttype);
	}

	/**
	 * 保存 岗位与职责的关联
	 */
	public void updates(Esppoststab post,Esppostsdutytab postduty,String[] idutyids,Syslog syslog) {
		dupsDao.updates(post, postduty, idutyids,syslog);
	}
	
	/**
	 * 显示  某个岗位的所有职责
	 * @return
	 */
	
	public java.util.List<Espdutytab> reviterId(Long ipostsid) {
		return dupsDao.reviterId(ipostsid);
	};

	public List<Esppostsdutytab> revisterRegid(Long ipostsid) {
		return dupsDao.revisterRegid(ipostsid);
	}

	public List<Espdutytab> getTourcardBtnDuty() throws RuntimeException {
		
		return dupsDao.getTourcardBtnDuty();
	}

	public List<Espdutytab> reviterTourcardId(Long ipostsid) throws RuntimeException {
		
		return dupsDao.reviterTourcardId(ipostsid);
	}

	public List<Esppostsbtndutytab> revisterTourcardRegid(Long ipostsid) throws RuntimeException {
		
		return dupsDao.revisterTourcardRegid(ipostsid);
	}
}
