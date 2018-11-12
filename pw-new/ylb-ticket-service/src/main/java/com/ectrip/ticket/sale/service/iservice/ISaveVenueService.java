package com.ectrip.ticket.sale.service.iservice;

import java.util.List;

import com.ectrip.base.util.ResultBean;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.venuemarketing.Seatlocktab;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;

public interface ISaveVenueService {
	public Venueprogram getprogram(Long iprogramid);
	public ResultBean getprogram(Long iscenicid, String stdt,String url);
	public ResultBean Getseat(Long ivenueid);
	public ResultBean Getseatstusts(Long ivenueid, String stdt, Long tripid);
	public ResultBean Getvenue(Long ivenueid);
	public ResultBean Getareapricve(Long Itripprdcontrolid, String stdt,String groupid, Long ibusinessid);
	public ResultBean Getareapricve(Long Itripprdcontrolid, String stdt,String groupid, Long ibusinessid,Long ivenueareaid);
	public ResultBean updateseatstuts(String comticketsalesdetail)throws Exception;
	public ResultBean updatehfseatstuts(String comticketsalesdetails);
	public ResultBean saveorder42(String salesvouchers,
			String salesvoucherdetails, String comticketsalesdetails,
			Long maxid, String szsalesvoucherno,String url,String...param) throws Exception;
	public ResultBean updatetdseatstuts(String Seatstatustablists);
	public ResultBean getprogrambyproductid(Long iproductid, String stdt,String url);
	public ResultBean Getareaseatstusts(Long ivenueid,Long ivenueareaid, String stdt, Long tripid);
	public ResultBean savetorder42(T_order t_order, List listorder,
			List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,
			List trlist,String param1) throws Exception;
	public ResultBean Getseatlocklist(String date);
	public ResultBean getVenueseatsalecount(Long employeeid, String date);
	public ResultBean updateseatlock(Long iseatlockid,Long iticknumb);
	public Seatlocktab Getseatlockbyid(Long iseatlockid);
	public ResultBean updatehfseatlock(Long iseatlockid);
	public ResultBean upshuijiseat(String comticketsalesdetails);
	public ResultBean checkEdpofferschemetab(String offerschme);
	public ResultBean uphfshuijiseat(String comticketsalesdetails,
			String seatids);
	public ResultBean getVenueseatsalecountbyiscenicid(String scenic, String date);
	public String  GetEmployeesenic(Long employeeid);
	
	public ResultBean queryStockWarenumb(String usid,String iscenicid);
	
	public ResultBean checkTicketzhiwe(String szticketprintno);
	
	public ResultBean queryListzjhmPrint(String zjhm);
	public Sysparv5  Getsyspar(String pmky,String pmcd);
	
	public ResultBean checkSaveprintStssold(String szsoldticketid,String isalesvoucherid,String isalesvoucherdetailsid,String iticketstationid,String zjhm,String bsfilebinary);
    
}
