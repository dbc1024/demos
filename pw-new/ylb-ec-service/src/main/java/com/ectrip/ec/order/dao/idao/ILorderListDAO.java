package com.ectrip.ec.order.dao.idao;

import java.util.List;

import com.ectrip.ec.model.order.LOrderlist;

public interface ILorderListDAO {

	void saveLorderList(LOrderlist lorderList);

	List<LOrderlist> getLOrderlistByids(String orid, Long iscenicid);

}
