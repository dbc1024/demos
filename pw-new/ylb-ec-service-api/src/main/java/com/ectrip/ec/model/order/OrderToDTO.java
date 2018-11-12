package com.ectrip.ec.model.order;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.order.common.ProductTripAttr;
import com.ectrip.ec.model.ticket.LprPojo;


/*
 * 将预定信息集合转换为订单DTO对象
 */
public class OrderToDTO {
	/**
	 * 将预定集合转换为DTO对象 toOrderCombinDTO(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
	 * 
	 * @auth hejiahua
	 * @param orderInfos
	 *            订单集合
	 * @param products
	 *            产品集合
	 * @param usid
	 *            用户编号
	 * @param askOrder
	 *            是否申请单
	 * @param orid  
	 * 			     订单编号
	 * @param realnames
	 * @param triplist
	 * @param lprs
	 *            领票人集合
	 * @return OrderCombinDTO
	 * @exception
	 * @date:2013-10-24 上午08:53:02
	 * @since 1.0.0
	 */
	public static OrderCombinDTO toOrderCombinDTO(List<OrderInfo> orderInfos,
			String askOrder, String usid,
			List<TRealname> realnames, List<ProductTripAttr> triplist,
			List<LprPojo> lprs,String orid) {
		OrderCombinDTO orderCombinDTO = new OrderCombinDTO();
		List<OrderProduct> OrderProduct = new ArrayList<OrderProduct>(); //产品集合
		if (orderInfos.size() == 0 || orid==null || orid.trim().equals("") || lprs.size()==0 || usid==null || usid.trim().equals("")) {
			throw new NullPointerException(); //抛出空异常
		}
		orderCombinDTO.setLprs(lprs);// 领票人集合
		orderCombinDTO.setGroupno(orderInfos.get(0).getNote1());// 价格分组
		orderCombinDTO.setIbusiness(orderInfos.get(0).getIbusinessid());// 业务
		orderCombinDTO.setOrid(orid);// 订单编号
		orderCombinDTO.setUsid(usid);// 用户编号
		for (int i = 0; i < orderInfos.size(); i++) {
			//把数量不存在的去除  修正日期：2014-03-26 10:09:35
			if (orderInfos.get(i).getNum()==null||orderInfos.get(i).getNum().intValue()==0) {
				continue;
			}
			
			OrderProduct products = new OrderProduct(); //产品
			products.setIscenicid(orderInfos.get(i).getIscenicid()); //服务商
			products.setCrowdkindid(orderInfos.get(i).getIcrowdkindid()); //人群种类
			products.setStartdate(orderInfos.get(i).getPlaytime()); //开始时间
			products.setEnddate(orderInfos.get(i).getEnddate());//结束时间
			products.setStarttime(orderInfos.get(i).getStarttime());//分时开始时间
			products.setEndtime(orderInfos.get(i).getEndtime());// 分时结束时间
			products.setTimeid(orderInfos.get(i).getTimeid());// 分时时间段ID
			products.setIticketid(orderInfos.get(i).getItickettypeid()); //产品编号
			products.setNum(orderInfos.get(i).getNum()); //数量
			OrderProduct.add(products); 

		}
		orderCombinDTO.setProducts(OrderProduct);// 产品集合
		orderCombinDTO.setTriplist(triplist);
		orderCombinDTO.setRealnames(realnames);
		
		orderCombinDTO.setAskOrder(askOrder);// 是否申请单
		return orderCombinDTO;
	}
}
