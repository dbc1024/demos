package com.ectrip.ec.order.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface ILZorderlistDAO extends IGenericDao{

	List getLZorderlistByParam(String orid, Long iscenicid, Long orderlistid);

}
