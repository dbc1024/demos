package com.ectrip.tourcard.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.tourcard.dao.ITourcardBindUserDAO;
import com.ectrip.tourcard.model.TourcardBindUserQuery;

@Repository
public class TourcardBindUserDAO extends GenericDao implements ITourcardBindUserDAO{

	/**
	 * 分页查询旅游卡绑定用户简要信息
	 */
	public PaginationSupport showlistTourcardBindUser(int pageSize, int startInt, String url,TourcardBindUserQuery query) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select distinct new map(t.account as account,t.usId as usId,t.userName as userName,t.identityNum as identityNum,t.mobile as mobile,t.num as num) from TourcardBindUser t where 1=1 ");
		if(query.getAccount() != null) {
			hsql.append(" and account = '"+query.getAccount()+"'");
		}
		if(query.getUserName() != null) {
			hsql.append(" and userName = '"+query.getUserName()+"'");
		}
		if(StringUtil.isNotEmpty(query.getIdentityNum())) {
			hsql.append(" and identityNum = '"+DesEncryptUtil.encrypt(query.getIdentityNum())+"'");
		}
		PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startInt, url);
		List<Map> list = ps.getItems();
		if(list != null && !list.isEmpty()){
			for(Map map : list){
				String identityNum = (String)map.get("identityNum");
				if(StringUtil.isNotEmpty(identityNum)){//格式化身份证号码显示
					map.put("identityNum", StringUtil.markIdNumber(DesEncryptUtil.decrypt(identityNum)));
				}
			}
		}
		return ps;
	}
	
	/**
	 * 描述：查询用户信息
	 */
	@SuppressWarnings("rawtypes")
	public List findTourcardBindUserInfo(String userId) throws RuntimeException,Exception {
		String sql = "select c.NOTE6 as account,t.USERNAME as userName,t.IDENTITYNUM as identityNum,"
				+ "c.MOBILE as mobile,t.IDENTITYAREA as identityArea from Custom c join Tourcarddetail t "
				+ "on c.USID = t.USERID and c.USID = ?";
		return this.findBySqlToMap(sql, new Object[] {userId});
	}
	
	/**
	 * 查询旅游卡明细信息
	 */
	@SuppressWarnings("rawtypes")
	public List findTourcardDetailInfoByUserId(String userId) throws RuntimeException,Exception {
		String hql = "select t.id as id,t.code as code,t.name as name,t.profitNum as profitNum, "
				+ "t.createTime as createTime,t.bankCardNum as bankCardNum,t.cardNumber as cardNumber,"
				+ "t.price as price,t.periodStartDate as periodStartDate,t.periodEndDate as periodEndDate,"
				+ "t.effectiveTimes as effectiveTimes,t.usedTimes as usedTimes,t.leaveTimes as leaveTimes,"
				+ "t.status as status,t.scenics as scenics "
				+ "from Custom c join Tourcarddetail t on c.USID = t.USERID and c.USID = ?";
		return this.findBySqlToMap(hql, new Object[] {userId});
	}

	public List<?> findTourcardBindUserInfoByMutiCondition(List<String> list) throws RuntimeException, Exception {
//		select new map(t.account as account,t.usId as usId,t.userName as userName,t.identityNum as identityNum,t.mobile as mobile,t.num as num) from TourcardBindUser t
		return null;
	}
		
	
}
