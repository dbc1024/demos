package com.ectrip.ticket.service.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.employee.Espdutytab;

public interface IApiDao extends IGenericDao {
	// 读取　职责表
	public List<Espdutytab> getEspdutytab() throws Exception;

}

