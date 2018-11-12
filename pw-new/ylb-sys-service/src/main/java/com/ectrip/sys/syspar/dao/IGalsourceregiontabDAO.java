package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Galsourceregiontab;

public interface IGalsourceregiontabDAO extends IGenericDao {

    public List<Galsourceregiontab> findByIds(String[] ids);

    public Galsourceregiontab findByCode(String code);
}
