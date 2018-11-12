package com.ectrip.sys.employee.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.employee.Esppostsbtndutytab;
import com.ectrip.sys.model.employee.Esppostsdutytab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;

public interface IESPPostsDutyTabService extends IGenericService{
	/**
	 * lijingrui
	 * 保存岗位 职责关联
	 * @param postsduty
	 */
	public void updates(Esppoststab post,Esppostsdutytab postduty,String[] idutyids,Syslog syslog);  //修改职责
	
	/**
	 * 显示所有岗位职责信息
	 * @return
	 */
	public List<Espdutytab> getAllpost(String posttype);  
	
	/**
	 * 某个岗位的所有职责
	 * @param ipostsid
	 * @return
	 */
	public List<Espdutytab> reviterId(Long ipostsid);
	
	/**
	 * 显示与岗位 关联的职责信息
	 * @param ipostsid
	 * @return
	 */
	public List<Esppostsdutytab> revisterRegid(Long ipostsid);
	
	/**
	 * 描述：获取旅游卡按钮权限
	 * @return
	 * @throws RuntimeException
	 */
	public List<Espdutytab> getTourcardBtnDuty() throws RuntimeException;
	
	/**
	 * 描述:通过岗位id获取旅游卡按钮权限
	 * @param ipostsid
	 * @return
	 * @throws RuntimeException
	 */
	public List<Espdutytab> reviterTourcardId(Long ipostsid) throws RuntimeException;
	
	public List<Esppostsbtndutytab> revisterTourcardRegid(Long ipostsid) throws RuntimeException;

}
