package com.ectrip.ec.ticket.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.upload.model.Upfile;

public interface IFilmDAO extends IGenericDao{
	public List searchVenueprogram();
	public List searchFilm(String date, Long iscenicid);
	public Map showVenueprogram(Long iprogramid) throws Exception;
	public List searchfilmTrip(String date,Long ivenueid,Long iprogramid);
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate);
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate,String groupno);
	public List filmProviderList(String date);
	
	public List filmTripList(String date, Long iprogramid);
	
	public List filmSeatSaleStatu(String date, Long ivenueid, Long tripid); 
	public List filmSeatSaleStatu(String date, Long ivenueid,
			Long ivenueareaid, Long tripid);
	public List venueSeats(Long ivenueid);
	public List venueSeats(Long ivenueid,Long ivenueareaid);
	
	public Long getFilmProduct(Long ivenueareaid,Long itripprdcontrolid);
	
	public Tripprdcontroltab filmTripControl(String date, Long iprogramid,Long ivenuid);
	
	public Tripprdcontroldetailtab getFilmControlTrip(Long itripprdcontrolid,Long tripid);
	
	public List venuAreaList(Long ivenueid);
	public Upfile getVenuePicture(Long ivenueid);
}
