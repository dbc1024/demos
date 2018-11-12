package com.ectrip.tourcard.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.tourcard.dao.ITourCardOrderDAO;
import com.ectrip.tourcard.feign.service.EcService;
import com.ectrip.tourcard.model.TourCardOrder;

@Repository
public class TourCardOrderDAO extends GenericDao implements ITourCardOrderDAO{

	@Autowired
	private EcService ecService;
	

	public PaginationSupport findOrderList(TourCardOrder order, int pageSize, int startIndex, String url) {
		PaginationSupport ps =null;
		StringBuffer hsql=new StringBuffer();
		
		//进行服务拆分后，TourCardOrder、CustomRealName、Custom不在一个服务下，不能进行联表查询。
//		hsql.append("select distinct new map(tcorder.orderID as orderID,tcorder.createDate as createDate," +
//				" tcorder.userId as userId,tcorder.tourCommission as tourCommission," +
//				" tcorder.cardCode as cardCode,tcorder.cardName as cardName,tcorder.cardNum as cardNum," +
//				" tcorder.profitNum as profitNum,cn.realName as userName,cn.idNumber as identityNum," +
//				" c.mobile as mobileNum,tcorder.periodStartDate as periodStartDate,tcorder.periodEndDate as periodEndDate," +
//				" tcorder.price as price,tcorder.payStatus as payStatus," +
//				" tcorder.openingStatus as openingStatus,tcorder.isSenicids as isSenicids)" +
//				" from TourCardOrder tcorder,CustomRealName cn,Custom c " +
//				" where tcorder.userId=cn.userId and tcorder.userId=c.usid ");
		
		//cn.realName as userName,cn.idNumber as identityNum,c.mobile as mobileNum,
		//此服务先只查询TourCardOrder信息。然后在其他服务查询CustomRealName，Custom来补充
		hsql.append("select distinct new map(tcorder.orderID as orderID, tcorder.createDate as createDate," +
				" tcorder.userId as userId, tcorder.tourCommission as tourCommission," +
				" tcorder.cardCode as cardCode, tcorder.cardName as cardName, tcorder.cardNum as cardNum," +
				" tcorder.profitNum as profitNum," +
				" tcorder.periodStartDate as periodStartDate, tcorder.periodEndDate as periodEndDate," +
				" tcorder.price as price, tcorder.payStatus as payStatus," +
				" tcorder.openingStatus as openingStatus, tcorder.isSenicids as isSenicids)" +
				" from TourCardOrder tcorder where 1=1");
		
		List<String> params=new ArrayList<String>();
		if(order!=null){
			params.clear();
			if(!StringUtils.isEmpty(order.getOrderID()))
			{
				hsql.append(" and tcorder.orderID like '%$%'");
				params.add(order.getOrderID());
			}
			if(!StringUtils.isEmpty(order.getCardName()))
			{
				hsql.append(" and tcorder.cardName like '%$%'");
				params.add(order.getCardName());
			}

			if(!StringUtils.isEmpty(order.getUserId()))
			{
				hsql.append(" and tcorder.userId like '%$%'");
				params.add(order.getUserId());
			}
			if(!StringUtils.isEmpty(order.getCardNum()))
			{
				hsql.append(" and tcorder.cardNum like '%$%'");
				params.add(order.getCardNum());
			}
			if(!StringUtils.isEmpty(order.getProfitNum()))
			{
				hsql.append(" and tcorder.profitNum like '%$%'");
				params.add(order.getProfitNum());
			}
			if(!StringUtils.isEmpty(order.getUserName()))
			{
				hsql.append(" and tcorder.userName like '%$%'");
				params.add(order.getUserName());
			}
			if(!StringUtils.isEmpty(order.getIdentityNum()))
			{
				hsql.append(" and tcorder.identityNum = '$'");//身份证号采用精确查找
				params.add(DesEncryptUtil.encrypt(order.getIdentityNum()));
			}
			if(order.getOpeningStatus()!=null && order.getOpeningStatus()!=-99)
			{
				hsql.append(" and tcorder.openingStatus=$");
				params.add(order.getOpeningStatus()+"");
			}
			if(order.getPayStatus()!=null)
			{
				if(order.getPayStatus()==-99)//全部
				{
					if(order.getCsti()!=null)
					{
						hsql.append(" and (substr(tcorder.periodStartDate, 0, 10)>='$' or tcorder.periodStartDate is null )");
						params.add(order.getCsti());
					}
					if(order.getCeti()!=null)
					{
						hsql.append(" and (substr(tcorder.periodEndDate, 0, 10)<='$' or tcorder.periodEndDate is null)  ");
						params.add(order.getCeti());
					}
				}else
				{
					hsql.append(" and tcorder.payStatus=$");
					params.add(order.getPayStatus()+"");
					if(order.getCsti()!=null && (order.getPayStatus()!=null && order.getPayStatus()==1))
					{
						hsql.append(" and substr(tcorder.periodStartDate, 0, 10)>='$'");
						params.add(order.getCsti());
					}
					if(order.getCeti()!=null && (order.getPayStatus()!=null && order.getPayStatus()==1))
					{
						hsql.append(" and substr(tcorder.periodEndDate, 0, 10)<='$'");
						params.add(order.getCeti());
					}
				}
			}

			if(order.getFsti()!=null)
			{
				hsql.append(" and tcorder.createDate>=to_date('$','yyyy-mm-dd hh24:mi:ss')");
				params.add(order.getFsti());
			}
			if(order.getFeti()!=null)
			{
				hsql.append(" and tcorder.createDate<to_date('$','yyyy-mm-dd hh24:mi:ss')+1");
				params.add(order.getFeti());
			}
		}else
		{
			params.clear();
			/*hsql.append(" and (substr(tcorder.periodStartDate, 0, 10)>='$' or tcorder.periodStartDate is null) ");
			params.add(Tools.getDays());
			hsql.append(" and (substr(tcorder.periodEndDate, 0, 10)<='$' or tcorder.periodStartDate is null)");
			params.add(Tools.getDays());*/
			hsql.append(" and tcorder.createDate>=to_date('$','yyyy-mm-dd hh24:mi:ss')");
			params.add(Tools.getDays());
			hsql.append(" and tcorder.createDate<to_date('$','yyyy-mm-dd hh24:mi:ss')+1");
			params.add(Tools.getDays());
		}
		String[] strParams=params.toArray(new String[params.size()]);
		hsql.append(" order by tcorder.createDate desc");
		ps=this.findPage(hsql.toString(),strParams, pageSize, startIndex+"", url);
		
		List<Map> itemList = ps.getItems();
		if(itemList != null && !itemList.isEmpty()){
			
			
			String userIds = "";
			for (int i=0; i<itemList.size(); i++) {
				
				Map map = itemList.get(i);
				String userId = (String)map.get("userId");
				if(i==0) {
					userIds = userIds + userId;
				}else {
					userIds = userIds +"," +userId;
				}
			}
			
			//根据用户ID集合获取用户信息列表,userIds数据形式userIds="12,541,625,51"
			List<Map> custom_List = ecService.getCustomsByUserIds(userIds);
			for (Map custom : custom_List) {
				String userId = (String)custom.get("usid");
				String mobileNum = (String)custom.get("mobile");
				
				for(Map item : itemList){
					String itemUserId = (String)item.get("userId");
					if(userId.equals(itemUserId)) {
						item.put("mobileNum", mobileNum);
					}
					
				}
			}
			
			
			//根据实名用户ID集合获取实名信息列表,userIds数据形式userIds="12,541,625,51"
			List<Map> customRealName_List = ecService.getRealByUserIds(userIds);
			for (Map custom : customRealName_List) {
				String userId = (String)custom.get("userId");
				String realName = (String)custom.get("realName");
				String idNumber = (String)custom.get("idNumber");
				
				//cn.realName as userName,cn.idNumber as identityNum
				for(Map item : itemList){
					String itemUserId = (String)item.get("userId");
					
					if(userId.equals(itemUserId)) {
						item.put("userName", realName);
						item.put("identityNum", StringUtil.markIdNumber(DesEncryptUtil.decrypt(idNumber)));
					}
				}
			}
			
			return ps;
		}
		
//		List<Map> list = ps.getItems();
//		if(list != null && !list.isEmpty()){
//			for(Map map : list){
//				String identityNum = (String)map.get("identityNum");
//				if(StringUtil.isNotEmpty(identityNum)){//格式化身份证号码显示
//					map.put("identityNum", StringUtil.markIdNumber(DesEncryptUtil.decrypt(identityNum)));
//				}
//			}
//		}
		
		
		
		return ps;
	}

	public TourCardOrder findOrderByID(String orderID){
		return (TourCardOrder)this.get(TourCardOrder.class, orderID);
	}
	
	public List<TourCardOrder> getOrderByCode(String code){
		
		List<TourCardOrder> list = this.find("from TourCardOrder where cardCode=?", new Object[]{code});
		return list;
	}

	/**
	 * 获取用户未支付的有效订单
	 * @param userId
	 * @param cardCode
	 * @return
	 */
	public TourCardOrder getUnpayOrder(String userId, String cardCode){
		List<TourCardOrder> list = this.find("from TourCardOrder where userId=? and cardCode=? and createDate>? and payStatus=0", new Object[]{userId, cardCode, DateUtils.addMinute(new Date(), -30)});
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else {
			return null;
		}
	}
}
