package com.ectrip.ec.ticket.service.iservice;

import java.util.List;
import java.util.Map;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.upload.model.Upfile;

public interface IFilmService {
	public List searchVenueprogram();
	public List searchFilm(String date, Long iscenicid);
	public Map showVenueprogram(Long iprogramid) throws Exception;
	public List searchfilmTrip(String date,Long ivenueid,Long iprogramid);
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate);
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate,String groupno);
	public List filmProvider(String date);

	public List filmTripList(String date, Long iprogramid);
	public boolean checkSeatStatus(List filmproduct);
	public List filmSeatSaleStatus(String date, Long ivenueid, Long tripid);
	public List filmSeatSaleStatus(String date, Long ivenueid,Long ivenueareaid, Long tripid);
	public List getFilmSeatPrice(Long productid,Long ivenueareaid,Long itripprdcontrolid,String usid,String date);
	public Upfile getVenuePicture(Long ivenueid);
	public Long getFilmProduct(Long ivenueareaid,Long itripprdcontrolid);
	
	public OrderCombinDTO combinationOrder(String orid, List filmproduct, Custom custom, LprPojo lpr);
	public List<?> autoChooseRandomSeat(String date, Long ivenueid, Long ivenueareaid, Long tripid, Long bookNum);
	public List venuAreaList(Long ivenueid);
}

