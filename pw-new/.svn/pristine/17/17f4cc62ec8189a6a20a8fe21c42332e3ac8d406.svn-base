package com.ectrip.ec.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.app.dao.idao.ISearchDAO;
import com.ectrip.ec.model.order.Vcitable;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "电商服务相关接口")
@RestController
public class SearchController {
	
//	@Autowired
	private ISearchDAO searchDao;

	@GetMapping("/search/findUserByPwd")
	@ApiOperation(value = "查询用户信息")
	public Map findUserByPwd(@RequestParam String usid, @RequestParam String pwd){
		
		return searchDao.findUserByPwd(usid, pwd);
	}
	
	@RequestMapping("/search/getValidate")
	public Vcitable getValidate(String revmbno){
		
		return searchDao.getValidate(revmbno);
	}
	
	@RequestMapping("/search/valilogin")
	public String valilogin(String usid, String pwd){
		
		return searchDao.valilogin(usid, pwd);
	}
	
	@RequestMapping("/search/findUserByPhone")
	public Map findUserByPhone(String usid, String pwd){
		
		return searchDao.findUserByPhone(usid, pwd);
	}
	
	@RequestMapping("/search/getTourCardInfo")
	public Map<String, Object> getTourCardInfo(Object carNo){
		
		return searchDao.getTourCardInfo(carNo);
	}
	
	@RequestMapping("/search/getSenice")
	public List<Map<String, Object>> getSenice(Object senice){
		
		return searchDao.getSenice(senice);
	}
	
	@RequestMapping("/search/getTicketInfo")
	public Map<String, Object> getTicketInfo(String scienid){
		
		return searchDao.getTicketInfo(scienid);
	}
	
	@RequestMapping("/search/getScenceImg")
	List<Map<String, Object>> getScenceImg(String scienid){
		
		return searchDao.getScenceImg(scienid);
	}
	
	@RequestMapping("/search/findUser")
	public Map findUser(String usid,String pwd){
		
		return searchDao.findUser(usid, pwd);
	}
	
	@RequestMapping("/search/getTourCard")
	public List<TourCard> getTourCard(String userId){
		
		return searchDao.getTourCard(userId);
	}
	
	@RequestMapping("/search/getHoliday")
	public boolean getHoliday(String date){
		
		return searchDao.getHoliday(date);
	}
	
	@RequestMapping("/search/getLykOrderList")
	public Map<String, Object> getLykOrderList(String itickettypeid ,Long icrowdkindid ,Long isceniceId, String playDate, String userId){
		return searchDao.getLykOrderList(itickettypeid, icrowdkindid, isceniceId, playDate, userId);
	}
	
	@RequestMapping("/search/getTourCarDetail")
	public TourCardDetail getTourCarDetail(Object carNo){
		return searchDao.getTourCarDetail(carNo);
		
	}
	
	@RequestMapping("/search/useTourTicket")
	public void useTourTicket(String outNumber,String userid)  throws Exception{
		searchDao.useTourTicket(outNumber, userid);
	}
	
	@RequestMapping("/search/getCustomByPhone")
	public Custom getCustomByPhone(String phone){
		return searchDao.getCustomByPhone(phone);
	}
	
	@RequestMapping("/search/getUserCard")
	public List<Map<String, Object>> getUserCard(String userId,String isecienceId){
		return searchDao.getUserCard(userId, isecienceId);
	}
	
	@RequestMapping("/search/refuseTourTicket")
	public void refuseTourTicket(String outNumber,String userid)  throws Exception{
		searchDao.refuseTourTicket(outNumber, userid);
	}
	
	@RequestMapping("/search/getOrderLists")
	public List getOrderLists(String sql){
		return searchDao.getOrderLists(sql);
	}
	
}
