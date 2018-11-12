package com.ectrip.ec.balance.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.balance.Useryfk;

public interface IBalanceCenterDAO extends IGenericDao {
	
	/**
	 * 计算用户的预付款余额
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * @return
	 * return:float
	 * Date:2011-11-11
	 */
	public float getsumMoney(String usid);
	/**
	 * 获取主键最大值
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:int
	 * Date:2011-11-16
	 */
	public int getMaxSeq(String tablename,String column) ;
	
	/**
	 * 预付款保存，必须调用这个方法，这个方法维护预计明细与余额一至
	 */
	public int useryfksave(Useryfk yfk);
	/**
	 * 预付款保存，必须调用这个方法，这个方法维护预计明细与余额一至
	 */
	public int useryfksave(Useryfk yfk,Double tpmont);

	
	/**
	 * 预付款保存
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid订单号
	 * @param types预付款类别
	 * @param yfkfs预付款来源(01充值预付款02退订转预付款03消费预付款04预付款转现金05订单消费转预付款15积分转预付款)
	 * @param mont金额
	 * @param note备注
	 * return:void
	 * Date:2011-12-8
	 */
	public void saveUseryfk(String orid,int types,String yfkfs,Double mont,String note);
	/**
	 * 预付款保存
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid 要改变预付款的用户
	 * @param orid订单号
	 * @param types预付款类别（1增加-1减少）
	 * @param yfkfs预付款来源(01充值预付款02退订转预付款03消费预付款04预付款转现金05订单消费转预付款15积分转预付款)
	 * @param mont金额
	 * @param note备注
	 * return:void
	 * Date:2011-12-8
	 */
	public void saveUseryfk(String usid,String orid,int types,String yfkfs,Double mont,String note);
	/**
	 * 根据订单号查出最近一条用户预付款信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:Useryfk
	 * Date:2011-12-8
	 */
	public Useryfk queryUseryfkByOrid(String orid);
	
}

