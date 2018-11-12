package com.ectrip.ticket.cyt.service.iservice;

import java.lang.reflect.InvocationTargetException;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.cyt.model.request.CheckPmsDataRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateComplexOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateOTOOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.PushOrderRequestBody;


public interface ICytOrderService extends IGenericService{

    /**
     * 校验PMS订单数据
     * @param body
     * @return
     */
    public String checkPmsData(CheckPmsDataRequestBody body);
	/**
	 * 
	 * Describe:创建剧场订单
	 * @author:chenxinhao
	 * @param body
	 * @param custom_userid
	 * @param maxNostr
	 * @return
	 * @throws Exception
	 * return:String
	 * Date:2015-7-21
	 */
	public String createComplexOrder(CreateComplexOrderRequestBody body, String custom_userid, String maxNostr) throws Exception;
	
	/**
	 * 
	 * Describe:创建订单
	 * @author:zhangwubin
	 * @param body
	 * @param custom
	 * @param maxNostr
	 * @return
	 * @throws Exception
	 * return:String
	 * Date:2014-4-18
	 */
	public String addOrder(CreateOTOOrderRequestBody body, String custom_userid, String maxNostr) throws Exception;
	
	/**
	 * 
	 * Describe:是否可以退票
	 * @author:zhangwubin
	 * @param refundrulelist
	 * @return
	 * return:boolean
	 * Date:2014-3-17
	 * @throws java.text.ParseException
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public boolean refund(String orid, int refundquantity, double tdsx);

	/**
	 *
	 * Describe:部分退订
	 * @author:zhangwubin
	 * @param orid
	 * @param custom
	 * @param iscenicid
	 * @param tpsxmonth
	 * @param icompanyinfoid
	 * @param torderlists
	 * @return
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * return:boolean
	 * Date:2014-3-31
	 */
	public boolean cancelOrder(String orid, String iscenicid, Custom custom, Double tpsxmonth, Long icompanyinfoid, int refundquantity) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 
	 * Describe:退订
	 * @author:zhangwubin
	 * @param orid
	 * @param iscenicid
	 * @param custom
	 * @throws Exception 
	 */
	public boolean cancelOrder(String orid, String iscenicid, Custom custom, Double tpsxmonth, Long icompanyinfoid) throws Exception;
	/**
	 * Describe:同步订单
	 * @author liujianwen
	 * @param pofbpsrb
	 * @return
	 * return:boolean
	 * Date:2014-5-7
	 */
	public boolean synchronOrder(PushOrderRequestBody pofbpsrb);
}

