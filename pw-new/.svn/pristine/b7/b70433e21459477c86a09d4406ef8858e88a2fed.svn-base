package com.ectrip.tourcard.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.dao.ITourCardDetailDAO;
import com.ectrip.tourcard.feign.service.SysService;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.TourCardDetail;

@Repository
public class TourCardDetailDAO extends GenericDao implements ITourCardDetailDAO{
	
	@Autowired
	private SysService sysService;

	public void insertTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog) {
		this.save(tourCardDetail);

		syslog.setStlg("hello");
		syslog.setBrief("旅游卡明细");
		syslog.setNote("导入旅游卡明细{旅游卡卡号:"+ tourCardDetail.getCardNumber() +",旅游卡名称:"+ tourCardDetail.getName() +"旅游卡代码:"+ tourCardDetail.getCode()+"}");
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
		sysService.insertSyslog(syslog);
	}

	public void updateTourCardDetail(TourCardDetail tourCardDetail, Syslog syslog) {
		this.update(tourCardDetail);

		syslog.setStlg("hello");
		syslog.setBrief("旅游卡明细");
		syslog.setNote("删除旅游卡明细{旅游卡卡号:"+ tourCardDetail.getCardNumber() +",旅游卡名称:"+ tourCardDetail.getName() +"旅游卡代码:"+ tourCardDetail.getCode()+"}");
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
		sysService.insertSyslog(syslog);
	}

	public TourCardDetail getTourCardDetail(Long cardDetailId) {
		TourCardDetail tourCardDetail = (TourCardDetail)this.get(TourCardDetail.class, cardDetailId);
		
		return tourCardDetail;
	}

	public PaginationSupport getTourCardDetailList(CardDetailQuery query, int page, int pageSize, String url) {
		
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		
		hsql.append("select distinct new map(detail.id as id, detail.code as code, detail.cardNumber as cardNumber, card.name as name, card.timesFlag as timesFlag, detail.historyCardFlag as historyCardFlag, detail.bankCardNum as bankCardNum, card.profitNum as profitNum, detail.username as username, detail.userId as userId, detail.identityNum as identityNum, card.price as price, detail.organization as organization, detail.workNum as workNum, detail.periodStartDate as periodStartDate, detail.periodEndDate as periodEndDate, card.effectiveTimes as effectiveTimes, detail.usedTimes as usedTimes, detail.leaveTimes as leaveTimes, detail.identityArea as identityArea, card.scenics as scenics, card.descpt as descpt, detail.status as status, detail.createTime as createTime, detail.createMan as createMan) from TourCardDetail detail,TourCard card");
		where.append(" where detail.code=card.code and detail.status != -1");
		
		if(query != null){
			if(!"".equals(query.getCardNumber()) && query.getCardNumber() != null){
				where.append(" and detail.cardNumber='"+ query.getCardNumber() +"'");
			}
			if(!"".equals(query.getBankCardNum()) && query.getBankCardNum() != null){
				where.append(" and detail.bankCardNum='"+ query.getBankCardNum() +"'");
			}
			if(!"".equals(query.getName()) && query.getName() != null){
				where.append(" and card.name like '%"+ query.getName() +"%'");
			}
			if(!"".equals(query.getProfitNum()) && query.getProfitNum() != null){
				where.append(" and card.profitNum='"+ query.getProfitNum() +"'");
			}
			if(!"".equals(query.getUsername()) && query.getUsername() != null){
				where.append(" and detail.username like '%"+ query.getUsername() +"%'");
			}
			if(!"".equals(query.getIdentityNum()) && query.getIdentityNum() != null){
				where.append(" and detail.identityNum='"+ DesEncryptUtil.encrypt(query.getIdentityNum()) +"'");
			}
			if(!"".equals(query.getStatus()) && query.getStatus() != null && query.getStatus() != 3){
				where.append(" and detail.status="+ query.getStatus());
			}
			if(!"".equals(query.getPeriodStartDate()) && query.getPeriodStartDate() != null){
				where.append(" and detail.periodStartDate>='"+ query.getPeriodStartDate() +"'");
			}
			if(!"".equals(query.getPeriodEndDate()) && query.getPeriodEndDate() != null){
				where.append(" and detail.periodEndDate<='"+ query.getPeriodEndDate() +"'");
			}
			
			where.append(" and substr(detail.createTime,1,10)>= '"+ query.getStartCreateTime() +"' and substr(detail.createTime,1,10)<= '"+ query.getEndCreateTime() +"'");
			where.append(" order by createTime desc");
		}

		System.out.println(hsql.toString() + where.toString());

		ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
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
	 * 是否存在历史旅游卡
	 * @param cardCode 旅游卡代码
	 * @param idNum 用户身份证号码
	 * @return
	 */
	public TourCardDetail getTourCardDetail(String cardCode, String idNum){
		List<TourCardDetail> list = this.find("from TourCardDetail where code=? and identityNum=? and historyCardFlag=1", new Object[]{cardCode, idNum});
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

    /**
     * 获取用户已开通的旅游卡列表 包含已过期的（可续费）
     * @param userId 用户ID
     * @return
     */
	public List<TourCardDetail> getUserTourCardDetails(String userId){
        return this.find("from TourCardDetail where userId=? and status in (1, 2)", new Object[]{userId});
    }

	/**
	 * 获取已过期不能再续费的旅游卡列表
	 * @param userId 用户ID
	 * @return
	 */
	public List<TourCardDetail> getUserExpiredTourCardDetails(String userId){
		return this.find("from TourCardDetail where userId=? and status in (3, 4)", new Object[]{userId});
	}

	/**
	 * 获取用户所有的旅游卡列表
	 * @param userId 用户ID
	 * @return
	 */
	public List<TourCardDetail> getUserAllTourCardDetails(String userId){
		return this.find("from TourCardDetail where userId=? and status in (1, 2, 3, 4) order by status ASC, createTime DESC", new Object[]{userId});
	}


	//根据身份证及旅游卡代码查询明细是否存在
	public List<TourCardDetail> getDetailsByIdnumAndCode(String idNum, String code){
		List<TourCardDetail> list = this.find("from TourCardDetail where status !=-1 and identityNum=? and code=?", new Object[]{idNum, code});

		return list;

	}

	/**
	 * 存在的可绑定历史旅游卡列表
	 * @param idNum 用户身份证号码
	 * @return
	 */
	public List<TourCardDetail> getHistoryTourCardDetails(String idNum){
		List<TourCardDetail> list = this.find("select tcd from TourCardDetail tcd, TourCard tc " +
				"where tc.code=tcd.code and tcd.identityNum=? and tcd.historyCardFlag=1 and tcd.userId is null ",
				new Object[]{idNum});
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	/**
	 * 获取没有核销或者核销失败的旅游卡
	 * @return
	 */
	public List<TourCardDetail> getTourCardDetailConsume(){
		List<TourCardDetail> list = this.find("select tcd from TourCardDetail tcd where tcd.status=1 and tcd.jsid is not null and (tcd.writeoffstatus is null or tcd.writeoffstatus='2')");
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	@Override
	public List getListAll(String cardNumber, String cardName, String profitNum) {
		
		StringBuffer hsql = new StringBuffer();
        hsql.append("select distinct new map(detail.cardNumber as cardNumber, detail.name as name, detail.profitNum as profitNum) from TourCardDetail detail where 1=1");
		
		
		if(cardNumber != null && !"".equals(cardNumber)) {
			hsql.append(" and detail.cardNumber like '%"+cardNumber+"%'");
        }
        if(cardName != null && !"".equals(cardName)) {
        	hsql.append(" and detail.name like '%"+cardName+"%'");
        }
        if(profitNum != null && !"".equals(profitNum)) {
        	hsql.append(" and detail.profitNum like '%"+profitNum+"%'");
        }
        
        List list = this.find(hsql.toString());
		
		return list;
	}

	@Override
	public List getCardDetailsByCardNumbers(String cardNumbers) {
		String[] split = cardNumbers.split(",");
		
		StringBuilder newNumbers = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			
			newNumbers.append("'"+ split[i]+ "',");
		}
		newNumbers.deleteCharAt(newNumbers.length() - 1);
		
		List list = this.find("select distinct new map(detail.cardNumber as cardNumber, detail.name as name, detail.profitNum as profitNum)"
				+ " from TourCardDetail detail where detail.cardNumber in("+ newNumbers +")" );
		
		return list;
	}

}
