package com.ectrip.ec.order.dao.idao;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Ticketxgz;

/**
 * 
 * @ClassName: IMOrderDAO
 * @Description: 网上预定订票
 * @author Dicky
 * @date Oct 11, 2011 2:41:21 PM
 *
 */
public interface IMOrderDAO extends IGenericDao {
	public void removeevit(Object obj);

	public void saveMOrder(MOrder morder);

	public PaginationSupport getOrderViewList(String usid, QueryOrder query, int pageSize, int startIndex, String url);

	// 新增根据企业id分类
	public PaginationSupport getOrderViewListByGroupId(String usid, QueryOrder query, int pageSize, int startIndex,
			String url, String groupId);

	public List getMOrderList(String orid);

	public MOrder getMOrder(String orid);

	public MOrder getRefundMOrder(String srid);

	public void updateMOrder(MOrder morder);

	public List getOrderLogByOrid(String orid);

	public Ticketxgz ticketbackrule(Long itickettypeid, Long iscenicid, String lgtp);

	public Changebackrate getflByTime(Long gzid, String time, String jsfs);

	public List daoyoulist(String usid);

	public PaginationSupport queryAllMsOrder(String usid, QueryOrder order, int page, int pageSize, String url);

	public PaginationSupport queryAllMsOrderByGroupId(String usid, QueryOrder order, int page, int pageSize, String url,
			String groupId);

	public PaginationSupport getOrderMoneyChangeViewlist(String usid, QueryOrder order, int page, int pageSize,
			String url);

	public PaginationSupport getOrderMoneyChangeViewlistByGroupid(String usid, QueryOrder order, int page, int pageSize,
			String url, String groupid);

	public boolean updateNotea(String orid, String notea);

	// 獲取需要核銷的訂單
	public List getNeedConsumeMOrderList(String today);

	public List getNeedConsumeLOrderList(String today);
	
	public List<MOrder> getMorderListInfo(String orid);
	
	/**
	* @Title: getOrderListInfo  
	* @Description: TODO 查询订单信息通过   
	* @param @param param 电话号码或身份证号或订单号
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	public List<Map> getOrderListInfo(String param,String msql) throws Exception;

	public void updateOrderDdzt(String orid, String ddzt);

}
