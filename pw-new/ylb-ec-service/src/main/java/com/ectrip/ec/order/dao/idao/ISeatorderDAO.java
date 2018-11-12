package com.ectrip.ec.order.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ticket.model.venuemarketing.Venueseats;

public interface ISeatorderDAO extends IGenericDao {
	 public List gettorderseatlist(String orid,Long iscenicid,Long orderlistid);
	 public Seatordertab getSeatordertab(String orid,Long iscenicid,Long iseatid);
	 public Venueseats getvenueseats(long ivenueid,Long iseatid);
	 public List getTorderList(String orid,Long iscenicid);
}
