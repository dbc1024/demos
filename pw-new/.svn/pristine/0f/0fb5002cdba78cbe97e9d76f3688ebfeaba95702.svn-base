package com.ectrip.ec.app.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.app.BaseModel;
import com.ectrip.ec.model.order.Vcitable;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

public interface ISearchDAO extends IGenericDao{
	
	public String login(String usid,String pwd);
	
	public Map findUser(String usid,String pwd);

	public Map appGetProvider(String url, String iscenicid);

	@SuppressWarnings("rawtypes")
	public List getProviderList(String url,String keyword);
	
	/**
	 * 获取服务商添加分页
	 * @param url
	 * @param pageSize
	 * @param cPage
	 * @return
	 */
	public PaginationSupport getProviderList(String url,Integer pageSize,Integer cPage);
	
	public List getTicketList(String ibusinessid, long iscenicid,String groupno);
	
	
	public Map saveOrder(BaseModel model,String orid,String urls) throws Exception;
	
	public String sendMessage(String revmbno);
	
	public List getPriceListByDate(long itickettypeid, String ibusinessid, String date,String groupno) ;
	
	
	public List getOridList(String date,String usid);
	
	
	public List orderDetail(String orid,String iscenicid);
	
	public List showAllArticles(Long cmid,int num);
	
	public Map viewArticle(Long amid) throws Exception;
	
	public String getTicketPicAdder(Long itickettypeid);
	
	public String saveZhuCeUser(String revmbno, String password,
			String password2, String random, boolean flag);
	public List productList2(String usid, String scenicid, String datetime,String url);
	
	public StringBuffer showRetivere(String usid, String pwd);
	
	public List getTOrderList(String orid);

	public Map searchProductForOrderDetail(Long iscenicid);

	public List searchProduct(Long iscenicid, String rzti,String url);
	
	public List searchBaseProduct(Long iscenicid, String rzti);
	
	public List searchTicketByProid(Long iscenicid, String rzti,
			Long ibusinessid,String url) throws Exception;
	
	/**
	 * 
	 * Describe:查看某服务商的产品信息
	 * 
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return return:Esbscenicareatab Date:Feb 15, 2012
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti,String ldti, Long ibusinessid) throws Exception;
	/**
	 * 读取  服务商GPS信息
	 * @return
	 */
	public Object searchBaseProviderGpsInfo();
	/**
	 * 读取  服务商GPS信息
	 * @return
	 */
	public Object searchBaseUrlInfo(String key);

	//获取验证码
	public Vcitable getValidate(String revmbno);

	//获取用户旅游卡信息
	public List<TourCard> getTourCard(String userId);
	public Map<String, Object> appGetSenice(String iscenicid);
	/**
	 * 获取景区信息
	 * @param senice 景区id 1,2,3
	 * @return
	 */
	public List<Map<String, Object>> getSenice(Object senice);
	//获取景区门票详细信息
	public Map<String, Object> getTicketInfo(String scienid);
	//获取景区图片
	List<Map<String, Object>> getScenceImg(String scienid);
	public Map<String, Object> getLykOrderList(String itickettypeid ,Long icrowdkindid ,Long isceniceId, String playDate, String userId);
	public Map findUserByPwd(String usid, String pwd);
	//根据id获取用户信息
//	public Userbank getUserBankByID(String id);
	//根据用户id和景区id获取旅游卡
	public List<Map<String, Object>> getUserCard(String userId,String isecienceId);
	public Map findUserByPhone(String usid, String pwd);
	public String valilogin(String usid, String pwd);
	public TourCardDetail getTourCarDetail(Object carNo);
	public Map<String, Object> getTourCardInfo(Object carNo);
	public boolean getHoliday(String date);
	public Custom getCustomByPhone(String phone);
	public void useTourTicket(String outNumber,String userid)  throws Exception;
	public void refuseTourTicket(String outNumber,String userid)  throws Exception;
	public List getOrderLists(String sql);
	public  Map<String, Object> getUserInfoByPhone(String phone);
}
