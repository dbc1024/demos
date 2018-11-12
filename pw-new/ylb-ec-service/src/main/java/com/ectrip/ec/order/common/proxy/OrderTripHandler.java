package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.SeatordertabId;
import com.ectrip.ec.model.order.Seatyordertab;
import com.ectrip.ec.model.order.SeatyordertabId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.common.ProductTripAttr;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.ticket.dao.idao.IFilmDAO;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;

public class OrderTripHandler implements InvocationHandler {

	private List<ProductTripAttr> tripattr;

	private IOrderBusiness target;

	private ITicketDAO ticketDao;

	private IFilmDAO filmDao;

	public OrderTripHandler(List<ProductTripAttr> tripattr, IOrderBusiness target) {
		super();
		this.tripattr = tripattr;
		this.target = target;
		this.ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
		this.filmDao = (IFilmDAO) SpringUtil.getBean("filmDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		//System.out.println("OrderTripHandler invoke������"); 
		if (tripattr != null && tripattr.size() > 0) {
			OrderCombin combin = (OrderCombin) args[0];
			List<TZorderlist> ztlist = combin.getZorderlists();
			for (TZorderlist z : ztlist) {
				for (int i = 0; i < tripattr.size(); i++) {
					ProductTripAttr trip = tripattr.get(i);
					if (trip.getProduct().getIticketid().intValue() == z.getItickettypeid() && trip.getProduct().getStartdate().equals(z.getDtstartdate().substring(0, 10))
							&& trip.getTripticketid().intValue() == z.getIztickettypeid().intValue() && z.getIcrowdkindid().intValue() == trip.getProduct().getCrowdkindid().intValue()) {
						Edmtickettypetab ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, z.getIztickettypeid());
						z.setTripid(trip.getTripid());
						z.setIvenueid(trip.getIvenueid());
						z.setIvenueareaid(trip.getIvenueareaid());
						// yorder
						z.getYzorderlist().setTripid(trip.getTripid());
						z.getYzorderlist().setIvenueid(trip.getIvenueid());
						z.getYzorderlist().setIvenueareaid(trip.getIvenueareaid());
						if (trip.getFilmid() != null && trip.getFilmid().intValue() != 0) {
							Tripprdcontroltab filmcontrol = filmDao.filmTripControl(trip.getTripdate(), trip.getFilmid(), trip.getIvenueid());
							Tripprdcontroldetailtab detail = filmDao.getFilmControlTrip(filmcontrol.getItripprdcontrolid(), trip.getTripid());
							z.setTripid(trip.getTripid());
							z.setIvenueid(trip.getIvenueid());
							z.setIvenueareaid(z.getIvenueareaid());
							z.setIse(filmcontrol.getItripprdcontrolid());
							z.setIvenueseatsid(trip.getFilmid());
							z.setDtstartdate(trip.getTripdate() + " " + detail.getStarttime() + ":00");
							z.setDtenddate(Tools.getDate(trip.getTripdate(), ticket.getValidityday().intValue()) + " " + detail.getEndtime() + ":00");
							// yorder
							z.getYzorderlist().setIse(filmcontrol.getItripprdcontrolid());
							z.getYzorderlist().setTripid(trip.getTripid());
							z.getYzorderlist().setIvenueid(trip.getIvenueid());
							z.getYzorderlist().setIvenueareaid(z.getIvenueareaid());
							z.getYzorderlist().setIvenueseatsid(trip.getFilmid());
							z.getYzorderlist().setDtstartdate(trip.getTripdate() + " " + detail.getStarttime() + ":00");
							z.getYzorderlist().setDtenddate(Tools.getDate(trip.getTripdate(), ticket.getValidityday().intValue()) + " " + detail.getEndtime() + ":00");
							if (trip.getSeatids() != null && !trip.getSeatids().equals("")) {
								String[] seatid = trip.getSeatids().split(",");
								int seq = 1;
								int yseq = 1;
								for (String s : seatid) {
									Seatordertab seat = new Seatordertab();
									seat.setId(new SeatordertabId(new Long(seq++), z.getId().getOrid(), z.getId().getIscenicid(), z.getId().getOrderlistid(), z.getId().getZorderlistid()));
									seat.setIprogramid(trip.getFilmid());
									seat.setItripid(trip.getTripid());
									seat.setItripprdcontrolid(filmcontrol.getItripprdcontrolid());
									seat.setIvenueid(trip.getIvenueid());
									seat.setIvenueareaid(trip.getIvenueareaid());
									seat.setIseatid(Long.parseLong(s));
									seat.setStartdate(z.getDtstartdate());
									seat.setDtmakedate(Tools.getDayTimes());
									seat.setIsvalid(1l);
									ticketDao.save(seat);
									Seatyordertab yseat = new Seatyordertab();
									yseat.setId(new SeatyordertabId(new Long(yseq++), z.getId().getOrid(), z.getId().getIscenicid(), z.getId().getOrderlistid(), z.getId().getZorderlistid()));
									yseat.setIprogramid(trip.getFilmid());
									yseat.setItripid(trip.getTripid());
									yseat.setItripprdcontrolid(filmcontrol.getItripprdcontrolid());
									yseat.setIvenueid(trip.getIvenueid());
									yseat.setIvenueareaid(trip.getIvenueareaid());
									yseat.setIseatid(Long.parseLong(s));
									yseat.setStartdate(z.getDtstartdate());
									yseat.setDtmakedate(Tools.getDayTimes());
									yseat.setIsvalid(1l);
									ticketDao.save(yseat);
								}
							}
						} else {
							Prdtripvenuemanage tripinfo = ticketDao.getTripInfo(trip.getTripid(), trip.getIvenueid(), trip.getIvenueareaid(), trip.getTripdate(), z.getId().getIscenicid().toString(),
									trip.getTripticketid().toString());
							z.setTripid(tripinfo.getTripid());
							z.setIvenueid(tripinfo.getIvenueid());
							z.setIvenueareaid(tripinfo.getIvenueareaid());
							z.setIvenueseatsid(0l);
							z.setDtstartdate(trip.getTripdate() + " " + tripinfo.getStarttime() + ":00");
							z.setDtenddate(Tools.getDate(trip.getTripdate(), ticket.getValidityday().intValue()) + " " + tripinfo.getEndtime() + ":00");
							// yorder
							z.getYzorderlist().setTripid(tripinfo.getTripid());
							z.getYzorderlist().setIvenueid(tripinfo.getIvenueid());
							z.getYzorderlist().setIvenueareaid(tripinfo.getIvenueareaid());
							z.getYzorderlist().setIvenueseatsid(0l);
							z.getYzorderlist().setDtstartdate(trip.getTripdate() + " " + tripinfo.getStarttime() + ":00");
							z.getYzorderlist().setDtenddate(Tools.getDate(trip.getTripdate(), ticket.getValidityday().intValue()) + " " + tripinfo.getEndtime() + ":00");
						}
						tripattr.remove(i);
						break;
					}
				}

			}
		}
		return result;
	}
}
